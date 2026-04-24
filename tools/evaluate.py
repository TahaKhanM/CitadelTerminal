#!/usr/bin/env python3
"""
Standardized algo evaluation tool for the Citadel Terminal competition.

Designed to be called by an autonomous agent to benchmark every new algo
variant uniformly. For each opponent, alternates sides (half games as P1,
half as P2) and reports Wilson 95% CI win rate, mean HP delta, mean turn
count, crash rate, and mirror-balance flags. Produces both structured JSON
and a human-readable markdown summary.

Usage:
    python3 tools/evaluate.py <algo_dir> [--opponents <csv>] [--n <n>] [--out <json_path>]

Examples:
    python3 tools/evaluate.py algos/athena
    python3 tools/evaluate.py algos/athena --n 20
    python3 tools/evaluate.py algos/athena --opponents algos/extra_opp1,algos/extra_opp2 --n 6
    python3 tools/evaluate.py algos/athena --out /tmp/athena_eval.json

Reuses `tools/bestof.py`'s parallel match-runner (`run_one_match`,
`wilson_interval`, `resolve_algo`) — the match-execution code is NOT
duplicated here. Opponents that don't exist are skipped with a warning;
games that crash are logged and included in the crash count.

Output artifacts:
    1. JSON at --out (default: algos/<algo>/evaluate_report.json)
    2. Human-readable markdown to stdout

Uses stdlib only (argparse, json, os, sys, time, pathlib, statistics,
concurrent.futures).
"""
import argparse
import json
import os
import sys
import time
from concurrent.futures import ProcessPoolExecutor, as_completed
from datetime import datetime, timezone
from pathlib import Path
from statistics import mean


REPO_ROOT = Path(__file__).resolve().parent.parent
TOOLS_DIR = REPO_ROOT / "tools"
STARTER_KIT = REPO_ROOT / "C1GamesStarterKit-master"
ENGINE_JAR = STARTER_KIT / "engine.jar"
CONFIG_JSON = STARTER_KIT / "game-configs.json"

# Reuse bestof.py's match-runner primitives. This is the deliberate single
# source of truth for "how we run one Java match in a tempdir" — see
# tools/bestof.py for the implementation details (symlinked config, isolated
# worker_dir, replay parsing from the final JSONL line, timeout handling).
sys.path.insert(0, str(TOOLS_DIR))
try:
    from bestof import run_one_match, wilson_interval, resolve_algo  # type: ignore
except ImportError as e:
    print(f"FATAL: could not import tools/bestof.py ({e}). evaluate.py relies "
          f"on bestof.py's match-runner; do not delete it.", file=sys.stderr)
    sys.exit(2)


DEFAULT_OPPONENTS = [
    "algos/v13_second_ring",
    "algos/v14_support_caravan",
    "algos/v15_adaptive",
    "algos/opp_scout_rush",
    "algos/opp_demo_line",
    "algos/opp_turret_castle",
]

DEFAULT_N = 10
DEFAULT_TIMEOUT_SEC = 180
MIRROR_IMBALANCE_THRESHOLD = 0.2  # |WR_sideA - WR_sideB| above this flags imbalance


# ───────────────────────────────── helpers ──────────────────────────────────
def _resolve_opponent(name: str):
    """Resolve an opponent path; return None if missing (with stderr warning)."""
    try:
        return resolve_algo(name)
    except FileNotFoundError:
        print(f"[evaluate] WARNING: opponent '{name}' not found, skipping.",
              file=sys.stderr)
        return None


def _preflight():
    """Fail loudly and clearly if the match infrastructure is unavailable."""
    missing = []
    if not ENGINE_JAR.exists():
        missing.append(f"engine.jar at {ENGINE_JAR}")
    if not CONFIG_JSON.exists():
        missing.append(f"game-configs.json at {CONFIG_JSON}")
    if not (TOOLS_DIR / "bestof.py").exists():
        missing.append(f"tools/bestof.py at {TOOLS_DIR / 'bestof.py'}")
    if missing:
        print("FATAL: required match-running infrastructure missing:",
              file=sys.stderr)
        for m in missing:
            print(f"  - {m}", file=sys.stderr)
        sys.exit(2)


def _parse_replay_metrics(replay_path: str, algo_was_p1: bool):
    """
    Extract end-of-game metrics plus per-turn compute times from a replay.

    Returns dict with:
      - turns: final turn count
      - hp_delta: algo_hp - opponent_hp at game end (positive = algo ahead)
      - algo_hp, opp_hp
      - winner: 'algo' | 'opponent' | 'tie' | 'unknown'
      - turn_times_ms: list of algo-side compute times (deploy-phase frames)

    Uses the same frame semantics as tools/profile_turns.py:
    `turnInfo[0] == 0` = deploy frame; `p{N}Stats = [hp, sp, mp, time_ms]`.
    """
    out = {
        "turns": None,
        "hp_delta": None,
        "algo_hp": None,
        "opp_hp": None,
        "winner": "unknown",
        "turn_times_ms": [],
    }
    try:
        with open(replay_path) as f:
            lines = [ln for ln in f.readlines() if ln.strip()]
        if not lines:
            return out

        algo_key = "p1Stats" if algo_was_p1 else "p2Stats"
        opp_key = "p2Stats" if algo_was_p1 else "p1Stats"

        # Per-turn compute time from deploy-phase frames.
        max_turn = 0
        for line in lines:
            try:
                frame = json.loads(line)
            except json.JSONDecodeError:
                continue
            ti = frame.get("turnInfo")
            if not ti:
                continue
            if len(ti) >= 2 and isinstance(ti[1], (int, float)):
                max_turn = max(max_turn, int(ti[1]))
            # deploy frame (phase 0) — time_ms is authoritative for previous turn
            if ti[0] == 0 and algo_key in frame and len(frame[algo_key]) > 3:
                t_ms = frame[algo_key][3]
                if isinstance(t_ms, (int, float)):
                    out["turn_times_ms"].append(float(t_ms))

        # Final frame for HP / winner.
        try:
            final = json.loads(lines[-1])
            algo_hp = float(final[algo_key][0])
            opp_hp = float(final[opp_key][0])
            out["algo_hp"] = algo_hp
            out["opp_hp"] = opp_hp
            out["hp_delta"] = algo_hp - opp_hp
            if algo_hp > opp_hp:
                out["winner"] = "algo"
            elif opp_hp > algo_hp:
                out["winner"] = "opponent"
            else:
                out["winner"] = "tie"
            ti = final.get("turnInfo")
            if ti and len(ti) >= 2 and isinstance(ti[1], (int, float)):
                out["turns"] = int(ti[1])
            else:
                out["turns"] = max_turn or None
        except (json.JSONDecodeError, KeyError, IndexError, TypeError):
            out["turns"] = max_turn or None

    except OSError:
        pass
    return out


def _percentile(values, p):
    """p50/p95/p99 without numpy. Returns None for empty input."""
    if not values:
        return None
    vs = sorted(values)
    if len(vs) == 1:
        return vs[0]
    # linear interpolation between ranks (same as statistics.quantiles n=100)
    k = (len(vs) - 1) * (p / 100.0)
    f = int(k)
    c = min(f + 1, len(vs) - 1)
    if f == c:
        return vs[f]
    return vs[f] + (vs[c] - vs[f]) * (k - f)


# ─────────────────────────────── evaluation ─────────────────────────────────
def evaluate_matchup(algo_path: Path, opp_path: Path, n: int, out_dir: Path,
                     workers: int, timeout_sec: int):
    """
    Run 2n matches (n with algo as P1, n with algo as P2) against a single
    opponent and compute aggregated metrics. Returns a dict matching the
    per-opponent schema.
    """
    tasks = []
    # First n: algo as P1 (algo_was_p1 = True)
    for i in range(n):
        tasks.append((str(algo_path), str(opp_path), str(out_dir),
                      i + 1, timeout_sec))
    # Next n: algo as P2 (algo_was_p1 = False)
    for i in range(n):
        tasks.append((str(opp_path), str(algo_path), str(out_dir),
                      n + i + 1, timeout_sec))

    total = 2 * n
    wins = 0
    losses = 0
    ties = 0
    crashes = 0
    hp_deltas = []
    turn_counts = []
    all_turn_times = []

    wins_side_a = 0  # algo as P1
    wins_side_b = 0  # algo as P2
    games_side_a = 0
    games_side_b = 0

    game_details = []

    with ProcessPoolExecutor(max_workers=workers) as pool:
        fut_to_gid = {pool.submit(run_one_match, t): t[3] for t in tasks}
        for fut in as_completed(fut_to_gid):
            gid = fut_to_gid[fut]
            try:
                r = fut.result()
            except Exception as e:
                r = {"game_id": gid, "error": f"worker exception: {e}"}

            algo_was_p1 = (gid <= n)
            if algo_was_p1:
                games_side_a += 1
            else:
                games_side_b += 1

            if "error" in r:
                crashes += 1
                game_details.append({
                    "game_id": gid,
                    "algo_side": "P1" if algo_was_p1 else "P2",
                    "error": r["error"],
                })
                continue

            # Winner translation: in bestof.py r['winner'] is 'p1'/'p2'/'tie'
            # relative to the match's P1; we translate to algo perspective.
            if r["winner"] == "tie":
                ties += 1
                result = "tie"
            elif (r["winner"] == "p1" and algo_was_p1) or \
                 (r["winner"] == "p2" and not algo_was_p1):
                wins += 1
                result = "win"
                if algo_was_p1:
                    wins_side_a += 1
                else:
                    wins_side_b += 1
            else:
                losses += 1
                result = "loss"

            # Re-parse replay for richer metrics (hp delta, turn count,
            # per-turn compute time). bestof.py already gave us p1_hp/p2_hp
            # but we still need turn count and turn-time histogram.
            replay_path = r.get("replay")
            metrics = _parse_replay_metrics(replay_path, algo_was_p1) if replay_path else {}
            if metrics.get("hp_delta") is not None:
                hp_deltas.append(metrics["hp_delta"])
            elif "p1_hp" in r and "p2_hp" in r:
                # Fallback to bestof.py's parse
                delta = (r["p1_hp"] - r["p2_hp"]) if algo_was_p1 else (r["p2_hp"] - r["p1_hp"])
                hp_deltas.append(delta)
            if metrics.get("turns") is not None:
                turn_counts.append(metrics["turns"])
            if metrics.get("turn_times_ms"):
                all_turn_times.extend(metrics["turn_times_ms"])

            game_details.append({
                "game_id": gid,
                "algo_side": "P1" if algo_was_p1 else "P2",
                "result": result,
                "hp_delta": metrics.get("hp_delta"),
                "turns": metrics.get("turns"),
            })

    win_rate = wins / total if total else 0.0
    ci_lo, ci_hi = wilson_interval(wins, total)

    # Mirror-balance: compare win rate when algo was P1 vs. P2.
    wr_a = (wins_side_a / games_side_a) if games_side_a else 0.0
    wr_b = (wins_side_b / games_side_b) if games_side_b else 0.0
    mirror_imbalance = abs(wr_a - wr_b) > MIRROR_IMBALANCE_THRESHOLD

    return {
        "name": opp_path.name,
        "path": str(opp_path),
        "games": total,
        "wins": wins,
        "losses": losses,
        "ties": ties,
        "win_rate": round(win_rate, 4),
        "wilson_ci": [round(ci_lo, 4), round(ci_hi, 4)],
        "mean_hp_delta": round(mean(hp_deltas), 2) if hp_deltas else None,
        "mean_turns": round(mean(turn_counts), 2) if turn_counts else None,
        "crashes": crashes,
        "mirror_balance_flag": bool(mirror_imbalance),
        "wr_algo_as_p1": round(wr_a, 4),
        "wr_algo_as_p2": round(wr_b, 4),
        "turn_times_ms": all_turn_times,  # consumed by caller, stripped from final JSON
        "games_detail": game_details,
    }


# ───────────────────────────── markdown report ──────────────────────────────
def render_markdown(report: dict) -> str:
    algo = report["algo"]
    ts = report["timestamp"]
    n = report["n_per_opponent"]
    overall = report["overall"]
    opps = report["opponents"]
    compute = report["compute"]

    total_games = overall["total_games"]
    total_wins = sum(o["wins"] for o in opps)
    total_crashes = sum(o["crashes"] for o in opps)

    lines = []
    lines.append(f"# Evaluate report — `{algo}`")
    lines.append("")
    lines.append(f"_timestamp_: {ts}  •  _N per opponent_: {n} (×2 sides = {2*n} games each)")
    lines.append("")

    if total_games == 0:
        lines.append("**No games were run** (all opponents missing or skipped).")
        return "\n".join(lines)

    overall_wr_pct = 100 * overall["overall_wr"]
    crash_txt = "no crashes" if not overall["any_crashes"] else f"{total_crashes} crash(es)"
    lines.append(f"**Overall**: {total_wins}/{total_games} wins "
                 f"({overall_wr_pct:.1f}%), {crash_txt}, "
                 f"{len(opps)} opponent(s) evaluated.")
    lines.append("")

    # Per-opponent table
    lines.append("| opponent | games | W-L-T | WR | Wilson 95% CI | mean ΔHP | mean turns | crashes | flag |")
    lines.append("|---|---:|---:|---:|---|---:|---:|---:|---|")
    for o in opps:
        wr_str = f"{100 * o['win_rate']:.1f}%"
        ci_lo, ci_hi = o["wilson_ci"]
        ci_str = f"[{ci_lo:.2f}, {ci_hi:.2f}]"
        dhp = f"{o['mean_hp_delta']:+.1f}" if o["mean_hp_delta"] is not None else "—"
        turns = f"{o['mean_turns']:.1f}" if o["mean_turns"] is not None else "—"
        wlt = f"{o['wins']}-{o['losses']}-{o['ties']}"
        flags = []
        if o["mirror_balance_flag"]:
            flags.append("MIRROR")
        if o["crashes"] > 0:
            flags.append("CRASH")
        if o["ties"] > 0 and o["wins"] == 0 and o["losses"] == 0:
            flags.append("ALL-TIE")
        flag_str = ",".join(flags) if flags else "—"
        lines.append(f"| `{o['name']}` | {o['games']} | {wlt} | {wr_str} | "
                     f"{ci_str} | {dhp} | {turns} | {o['crashes']} | {flag_str} |")
    lines.append("")

    # Compute-time summary
    p50, p95, p99 = compute["p50_turn_ms"], compute["p95_turn_ms"], compute["p99_turn_ms"]
    if p50 is None:
        lines.append("_Per-turn compute time_: **unavailable** (no turn-time data parsed).")
    else:
        lines.append(f"_Per-turn compute time_ (ms, across all games): "
                     f"p50={p50:.0f}, p95={p95:.0f}, p99={p99:.0f}.")
    lines.append("")

    # Warnings
    warnings = []
    for o in opps:
        if o["crashes"] > 0:
            warnings.append(f"- `{o['name']}`: {o['crashes']}/{o['games']} games crashed")
        if o["mirror_balance_flag"]:
            warnings.append(f"- `{o['name']}`: mirror imbalance — "
                            f"WR as P1 {100*o['wr_algo_as_p1']:.0f}% vs "
                            f"WR as P2 {100*o['wr_algo_as_p2']:.0f}% "
                            f"(|Δ| > {int(MIRROR_IMBALANCE_THRESHOLD*100)} pp)")
    if "skipped" in report and report["skipped"]:
        for s in report["skipped"]:
            warnings.append(f"- skipped: `{s}` (not found)")
    if warnings:
        lines.append("## Warnings")
        lines.extend(warnings)
        lines.append("")

    return "\n".join(lines)


# ─────────────────────────────────── main ───────────────────────────────────
def main(argv=None):
    ap = argparse.ArgumentParser(
        prog="evaluate.py",
        description="Standardized benchmark for a Citadel Terminal algo "
                    "against a fixed panel of opponents. Alternates sides, "
                    "reports Wilson CI win rates, and emits JSON + markdown.",
    )
    ap.add_argument("algo", help="Path to the algo directory (e.g. algos/athena)")
    ap.add_argument("--opponents", default="",
                    help="Comma-separated list of opponent dirs. Appended to "
                         "the default panel unless --replace-opponents is set.")
    ap.add_argument("--replace-opponents", action="store_true",
                    help="Use only the opponents in --opponents, skip defaults.")
    ap.add_argument("--n", type=int, default=DEFAULT_N,
                    help=f"Games per side per opponent (total per matchup = 2N). "
                         f"Default {DEFAULT_N}.")
    ap.add_argument("--out", default=None,
                    help="JSON output path. Default: "
                         "<algo_dir>/evaluate_report.json")
    ap.add_argument("--workers", default="auto",
                    help="Parallel match workers. 'auto' = min(cpu_count, total_games). "
                         "Pass an int to override, or 1 for serial.")
    ap.add_argument("--timeout", type=int, default=DEFAULT_TIMEOUT_SEC,
                    help=f"Per-match timeout in seconds (default {DEFAULT_TIMEOUT_SEC}).")
    args = ap.parse_args(argv)

    _preflight()

    # Resolve algo
    try:
        algo_path = resolve_algo(args.algo)
    except FileNotFoundError as e:
        print(f"FATAL: {e}", file=sys.stderr)
        return 2

    # Build opponent list
    opp_names = []
    if not args.replace_opponents:
        opp_names.extend(DEFAULT_OPPONENTS)
    if args.opponents.strip():
        opp_names.extend([o.strip() for o in args.opponents.split(",") if o.strip()])

    # Deduplicate by resolved path (preserve order)
    seen = set()
    resolved_opps = []
    skipped = []
    for name in opp_names:
        p = _resolve_opponent(name)
        if p is None:
            skipped.append(name)
            continue
        # Don't pit the algo against itself from the default panel — mirror
        # balance is already measured via side-alternation. But allow it if
        # the user explicitly requests self-match.
        if p == algo_path and name in DEFAULT_OPPONENTS:
            continue
        if str(p) in seen:
            continue
        seen.add(str(p))
        resolved_opps.append((name, p))

    if not resolved_opps:
        print("FATAL: no valid opponents to evaluate against.", file=sys.stderr)
        if skipped:
            print(f"  (skipped: {', '.join(skipped)})", file=sys.stderr)
        return 2

    # Decide worker count based on total games across all opponents.
    total_games = 2 * args.n * len(resolved_opps)
    if args.workers == "auto":
        workers = min(os.cpu_count() or 4, total_games)
    else:
        try:
            workers = max(1, int(args.workers))
        except ValueError:
            print(f"FATAL: --workers must be 'auto' or an integer, got {args.workers!r}",
                  file=sys.stderr)
            return 2

    # Output paths
    ts_iso = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    ts_compact = time.strftime("%Y%m%d_%H%M%S")
    default_out = algo_path / "evaluate_report.json"
    out_json = Path(args.out).resolve() if args.out else default_out
    out_json.parent.mkdir(parents=True, exist_ok=True)

    # Replays go under replays/evaluate_<algo>_<ts>/
    replays_dir = REPO_ROOT / "replays" / f"evaluate_{algo_path.name}_{ts_compact}"
    replays_dir.mkdir(parents=True, exist_ok=True)

    # Pretty header to stderr so stdout stays clean for the markdown report.
    print(f"[evaluate] algo         = {algo_path}", file=sys.stderr)
    print(f"[evaluate] opponents    = {[n for n,_ in resolved_opps]}", file=sys.stderr)
    print(f"[evaluate] n/side       = {args.n}  (total/matchup = {2*args.n})", file=sys.stderr)
    print(f"[evaluate] total games  = {total_games}", file=sys.stderr)
    print(f"[evaluate] workers      = {workers}", file=sys.stderr)
    print(f"[evaluate] replays dir  = {replays_dir}", file=sys.stderr)
    print(f"[evaluate] json out     = {out_json}", file=sys.stderr)
    print("", file=sys.stderr)

    # Run evaluation per opponent
    wall_start = time.time()
    opponent_reports = []
    all_turn_times = []
    for opp_name, opp_path in resolved_opps:
        matchup_dir = replays_dir / f"vs_{opp_path.name}"
        matchup_dir.mkdir(parents=True, exist_ok=True)
        print(f"[evaluate] >>> {algo_path.name} vs {opp_path.name} "
              f"({2*args.n} games) ...", file=sys.stderr)
        rep = evaluate_matchup(algo_path, opp_path, args.n, matchup_dir,
                               workers=workers, timeout_sec=args.timeout)
        # Pop the turn-times list before saving — it's aggregated globally.
        tt = rep.pop("turn_times_ms", [])
        all_turn_times.extend(tt)
        # game_details is kept in the detailed JSON but excluded from the
        # top-level per-opponent section to keep the schema clean.
        details = rep.pop("games_detail", [])
        rep_with_details = dict(rep)
        rep_with_details["_games_detail"] = details
        opponent_reports.append(rep_with_details)
        wr_pct = 100 * rep["win_rate"]
        print(f"[evaluate]     W:{rep['wins']}/{rep['games']} "
              f"({wr_pct:.1f}%) "
              f"CI=[{rep['wilson_ci'][0]:.2f},{rep['wilson_ci'][1]:.2f}] "
              f"ΔHP={rep['mean_hp_delta']} crashes={rep['crashes']}",
              file=sys.stderr)

    wall_elapsed = time.time() - wall_start

    # Aggregate
    total_games_run = sum(o["games"] for o in opponent_reports)
    total_wins = sum(o["wins"] for o in opponent_reports)
    any_crashes = any(o["crashes"] > 0 for o in opponent_reports)
    overall_wr = total_wins / total_games_run if total_games_run else 0.0

    # Compute-time percentiles across all games from all opponents.
    if all_turn_times:
        p50 = round(_percentile(all_turn_times, 50), 2)
        p95 = round(_percentile(all_turn_times, 95), 2)
        p99 = round(_percentile(all_turn_times, 99), 2)
    else:
        p50 = p95 = p99 = None

    report = {
        "algo": f"algos/{algo_path.name}" if str(algo_path).startswith(str(REPO_ROOT / "algos"))
                 else str(algo_path),
        "algo_path": str(algo_path),
        "timestamp": ts_iso,
        "n_per_opponent": args.n,
        "wall_seconds": round(wall_elapsed, 2),
        "workers": workers,
        "opponents": [
            # Strip internal `_games_detail` from the clean list to match the
            # documented schema; keep it under `games_detail` below.
            {k: v for k, v in o.items() if k != "_games_detail"}
            for o in opponent_reports
        ],
        "games_detail": {
            o["name"]: o["_games_detail"] for o in opponent_reports
        },
        "overall": {
            "total_games": total_games_run,
            "overall_wr": round(overall_wr, 4),
            "any_crashes": bool(any_crashes),
        },
        "compute": {
            "p50_turn_ms": p50,
            "p95_turn_ms": p95,
            "p99_turn_ms": p99,
        },
        "skipped": skipped,
        "replays_dir": str(replays_dir),
    }

    # Write JSON artifact
    with open(out_json, "w") as f:
        json.dump(report, f, indent=2)
    print(f"[evaluate] JSON written: {out_json}", file=sys.stderr)

    # Emit markdown to stdout
    md = render_markdown(report)
    print(md)

    # Exit status: 0 on success regardless of WR; 1 only if any crashes
    # occurred AND the caller presumably wants to know. Per requirements the
    # tool is a benchmarker, not a pass/fail gate — so always exit 0 when it
    # ran to completion. Crashes surface via JSON/markdown.
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
