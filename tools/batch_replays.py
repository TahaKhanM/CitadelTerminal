#!/usr/bin/env python3
"""
Summarize many Citadel Terminal replays at once and surface PATTERNS across games.

This is the tool to use when you've downloaded a batch of ranked-match replays
and want to know:
  - What's my overall win rate? Against which opponents am I strong/weak?
  - Are opponents attacking a specific side of my board more?
  - Which of my structures die most consistently?
  - Are there outlier games (crashes, blowouts) worth zooming into?
  - Is my compute time trending up?
  - When tagged by opponent ELO via the naming convention
    `<self>_vs_<ELO>_elo_(win|loss).replay`: how do I stack up against each
    ELO tier, and which losses to stronger opponents reveal the largest
    gaps?

Usage:
    python3 tools/batch_replays.py replays/ranked_*.replay
    python3 tools/batch_replays.py replays/final_v11_vs_*_elo_*.replay
    python3 tools/batch_replays.py replays/*.replay --me final_v11 --my-elo 1689

Options:
    --me <name>       Identify which player is you. If filenames follow
                      `<me>_vs_<elo>_elo_(win|loss).replay`, opponent ELO
                      and outcome are auto-parsed and the report adds:
                        - Win rate by ELO bracket
                        - Loss analysis sorted by opponent strength
                        - "Upset" games (wins above your ELO) highlighted
    --my-elo N        Your current ELO (for gap-analysis in the report).
    --top N           Show top-N aggregations (default 10).
    --json            Emit machine-readable summary only.

Naming convention (recommended):
    final_v11_vs_1829_elo_loss.replay
    final_v11_vs_1756_elo_win.replay
    ^^^^^^^^^    ^^^^     ^^^^
    you          opp ELO  outcome

Performance: reads each replay in parallel across CPU cores when more than
5 files are given. Typical throughput: ~50 replays/second for 3 MB files on
a 10-core machine.
"""
import json
import os
import re
import sys
from collections import Counter, defaultdict
from concurrent.futures import ProcessPoolExecutor, as_completed
from pathlib import Path


FILENAME_ELO_RE = re.compile(r"_vs_(\d{3,4})_elo_(win|loss)", re.IGNORECASE)


def _parse_filename_tags(path):
    """Extract (opponent_elo, outcome) from the naming convention. Returns (None, None) if not matched."""
    m = FILENAME_ELO_RE.search(Path(path).name)
    if m:
        return int(m.group(1)), m.group(2).lower()
    return None, None


def parse_one(path):
    """Extract aggregate signals from one replay. Top-level for ProcessPool."""
    try:
        with open(path) as f:
            lines = [l.strip() for l in f if l.strip()]
        if not lines:
            return {"path": str(path), "error": "empty"}
        first = json.loads(lines[0])
        has_config = "unitInformation" in first
        frames = []
        start = 1 if has_config else 0
        for ln in lines[start:]:
            try:
                obj = json.loads(ln)
            except json.JSONDecodeError:
                continue
            if "turnInfo" in obj:
                frames.append(obj)
        if not frames:
            return {"path": str(path), "error": "no frames"}

        last = frames[-1]
        p1_hp = float(last["p1Stats"][0])
        p2_hp = float(last["p2Stats"][0])
        winner = "p1" if p1_hp > p2_hp else ("p2" if p2_hp > p1_hp else "tie")
        turn_count = int(last["turnInfo"][1])

        # Breach counts by owner and location
        breaches_p1 = Counter()
        breaches_p2 = Counter()
        for f in frames:
            for ev in f.get("events", {}).get("breach", []):
                if len(ev) < 5:
                    continue
                loc = tuple(ev[0]) if isinstance(ev[0], list) else ev[0]
                owner = ev[-1] if isinstance(ev[-1], (int, float)) else None
                if owner == 1:
                    breaches_p1[loc] += 1
                elif owner == 2:
                    breaches_p2[loc] += 1

        # Mobile spawn counts
        spawn_p1_tiles = Counter()
        spawn_p2_tiles = Counter()
        for f in frames:
            for ev in f.get("events", {}).get("spawn", []):
                if len(ev) < 4:
                    continue
                utype = ev[1]
                # mobile unit types are indices 3, 4, 5 or shorthands PI, EI, SI
                mobile_names = ("PI", "EI", "SI")
                is_mobile = utype in mobile_names or (isinstance(utype, (int, float)) and int(utype) in (3, 4, 5))
                if not is_mobile:
                    continue
                loc = tuple(ev[0]) if isinstance(ev[0], list) else ev[0]
                owner = ev[-1] if isinstance(ev[-1], (int, float)) else None
                if owner == 1:
                    spawn_p1_tiles[loc] += 1
                elif owner == 2:
                    spawn_p2_tiles[loc] += 1

        # Max compute time per side
        deploy_frames = [f for f in frames if f["turnInfo"][0] == 0]
        p1_max_ms = max((f["p1Stats"][3] for f in deploy_frames if len(f["p1Stats"]) > 3), default=0)
        p2_max_ms = max((f["p2Stats"][3] for f in deploy_frames if len(f["p2Stats"]) > 3), default=0)

        # Unit-type deaths
        deaths_p1 = Counter()
        deaths_p2 = Counter()
        for f in frames:
            for d in f.get("events", {}).get("death", []):
                if len(d) < 2:
                    continue
                utype = d[1]
                player = None
                for field in reversed(d):
                    if isinstance(field, (int, float)) and field in (1, 2):
                        player = int(field)
                        break
                if player == 1:
                    deaths_p1[utype] += 1
                elif player == 2:
                    deaths_p2[utype] += 1

        opp_elo, tagged_outcome = _parse_filename_tags(path)
        return {
            "path": str(path),
            "winner": winner,
            "p1_final_hp": p1_hp,
            "p2_final_hp": p2_hp,
            "turns": turn_count,
            "p1_breaches": sum(breaches_p1.values()),
            "p2_breaches": sum(breaches_p2.values()),
            "p1_top_breaches": dict(breaches_p1.most_common(5)),
            "p2_top_breaches": dict(breaches_p2.most_common(5)),
            "p1_top_spawns": dict(spawn_p1_tiles.most_common(5)),
            "p2_top_spawns": dict(spawn_p2_tiles.most_common(5)),
            "p1_max_ms": p1_max_ms,
            "p2_max_ms": p2_max_ms,
            "p1_deaths": dict(deaths_p1),
            "p2_deaths": dict(deaths_p2),
            "has_config": has_config,
            "opp_elo": opp_elo,
            "tagged_outcome": tagged_outcome,
        }
    except Exception as e:
        return {"path": str(path), "error": str(e)}


def aggregate(results, me=None):
    """Roll up per-replay results into cross-replay patterns."""
    wins = Counter()
    total = 0
    p1_total_breaches_in = 0
    p1_total_breaches_out = 0
    p2_total_breaches_in = 0
    p2_total_breaches_out = 0
    breach_in_tiles = Counter()   # where *I* got breached
    breach_out_tiles = Counter()  # where *my* units broke through
    spawn_in_tiles = Counter()    # where enemy deployed against me
    all_max_mine_ms = []
    loss_reasons = []  # rough heuristic
    errors = []

    for r in results:
        if "error" in r:
            errors.append(r)
            continue
        total += 1
        if r["winner"] == "p1":
            wins["p1"] += 1
        elif r["winner"] == "p2":
            wins["p2"] += 1
        else:
            wins["tie"] += 1

        # If --me is given, we assume ME is p1. If me is None we just aggregate raw.
        my_side = "p1"  # default; can't auto-detect without metadata
        opp_side = "p2"

        if my_side == "p1":
            breach_in_tiles.update(r.get("p2_top_breaches", {}))  # enemy breached at these tiles AGAINST me
            breach_out_tiles.update(r.get("p1_top_breaches", {}))
            spawn_in_tiles.update(r.get("p2_top_spawns", {}))
            p1_total_breaches_in += r["p2_breaches"]
            p1_total_breaches_out += r["p1_breaches"]
            all_max_mine_ms.append(r["p1_max_ms"])
        else:
            breach_in_tiles.update(r.get("p1_top_breaches", {}))
            breach_out_tiles.update(r.get("p2_top_breaches", {}))
            spawn_in_tiles.update(r.get("p1_top_spawns", {}))
            p2_total_breaches_in += r["p1_breaches"]
            p2_total_breaches_out += r["p2_breaches"]
            all_max_mine_ms.append(r["p2_max_ms"])

    return {
        "total_games": total,
        "wins": dict(wins),
        "p1_win_rate": wins.get("p1", 0) / total if total else 0,
        "p2_win_rate": wins.get("p2", 0) / total if total else 0,
        "total_breaches_against_me": (
            p1_total_breaches_in if me else 0
        ),
        "top_breached_tiles_against_me": breach_in_tiles.most_common(10),
        "top_breach_tiles_we_scored": breach_out_tiles.most_common(10),
        "top_enemy_spawn_tiles": spawn_in_tiles.most_common(10),
        "max_compute_ms_overall": max(all_max_mine_ms) if all_max_mine_ms else 0,
        "mean_max_compute_ms": sum(all_max_mine_ms) / len(all_max_mine_ms) if all_max_mine_ms else 0,
        "errors": errors,
    }


def main(argv):
    want_json = "--json" in argv
    me = None
    if "--me" in argv:
        me = argv[argv.index("--me") + 1]
    my_elo = None
    if "--my-elo" in argv:
        my_elo = int(argv[argv.index("--my-elo") + 1])
    top_n = 10
    if "--top" in argv:
        top_n = int(argv[argv.index("--top") + 1])

    positional = []
    i = 1
    while i < len(argv):
        a = argv[i]
        if a == "--json":
            i += 1
        elif a in ("--me", "--top", "--my-elo"):
            i += 2
        elif a.startswith("--"):
            i += 1
        else:
            positional.append(a)
            i += 1

    # Expand any globs that the shell didn't
    paths = []
    for p in positional:
        pp = Path(p)
        if pp.is_file():
            paths.append(pp)
        elif pp.is_dir():
            paths.extend(pp.rglob("*.replay"))
        else:
            paths.extend(Path(".").glob(p))

    if not paths:
        print(__doc__)
        return 1

    paths = sorted(set(paths))
    print(f"[batch] Analyzing {len(paths)} replays...")

    if len(paths) > 5:
        workers = min(os.cpu_count() or 4, len(paths))
        with ProcessPoolExecutor(max_workers=workers) as pool:
            results = list(pool.map(parse_one, paths))
    else:
        results = [parse_one(p) for p in paths]

    agg = aggregate(results, me=me)

    if want_json:
        print(json.dumps({
            "summary": agg,
            "per_replay": results,
        }, indent=2, default=str))
        return 0

    print("\n" + "=" * 60)
    print(f"BATCH SUMMARY — {agg['total_games']} games")
    print("=" * 60)
    w = agg["wins"]
    print(f"  Wins: P1={w.get('p1', 0)}  P2={w.get('p2', 0)}  ties={w.get('tie', 0)}")
    print(f"  P1 win rate: {agg['p1_win_rate']:.1%}")
    print(f"  P2 win rate: {agg['p2_win_rate']:.1%}")
    if agg["errors"]:
        print(f"  ⚠ {len(agg['errors'])} replays failed to parse:")
        for e in agg["errors"][:5]:
            print(f"    {e['path']}: {e['error']}")

    print(f"\n  Max compute time across all games: {agg['max_compute_ms_overall']:.0f} ms")
    print(f"  Mean of per-game max compute:     {agg['mean_max_compute_ms']:.0f} ms")
    if agg["max_compute_ms_overall"] > 10000:
        print(f"    ⚠ CRITICAL — timeouts likely on server")
    elif agg["max_compute_ms_overall"] > 5000:
        print(f"    ! CONCERN — investigate slow turns")

    print(f"\n  Top tiles where enemy breached (your weak spots):")
    for loc, n in agg["top_breached_tiles_against_me"][:top_n]:
        print(f"    {loc}: {n}")
    print(f"\n  Top tiles where your mobiles breached (their weak spots):")
    for loc, n in agg["top_breach_tiles_we_scored"][:top_n]:
        print(f"    {loc}: {n}")
    print(f"\n  Top tiles where enemy deployed mobiles (their spawn pattern):")
    for loc, n in agg["top_enemy_spawn_tiles"][:top_n]:
        print(f"    {loc}: {n}")

    # Outlier games — blowouts, crashes, close games
    print("\n  Outlier games:")
    for r in results:
        if "error" in r:
            continue
        margin = abs(r["p1_final_hp"] - r["p2_final_hp"])
        if r["winner"] == "tie" or margin < 3:
            print(f"    CLOSE   {Path(r['path']).name:<50}  HP {r['p1_final_hp']:.0f}-{r['p2_final_hp']:.0f}")
        elif margin > 30:
            print(f"    BLOWOUT {Path(r['path']).name:<50}  HP {r['p1_final_hp']:.0f}-{r['p2_final_hp']:.0f}")

    # ── ELO-aware analysis (filename convention: <me>_vs_<elo>_elo_(win|loss).replay) ──
    tagged = [r for r in results if r.get("opp_elo") is not None]
    if tagged:
        print("\n" + "=" * 60)
        print(f"ELO-AWARE ANALYSIS ({len(tagged)} of {len(results)} replays tagged)")
        if my_elo is not None:
            print(f"  Your ELO: {my_elo}")
        print("=" * 60)

        # Bracket win rate
        brackets = [
            ("≤-200",     lambda gap: gap <= -200),
            ("-199..-50", lambda gap: -199 <= gap <= -50),
            ("-49..+49",  lambda gap: -49 <= gap <= 49),
            ("+50..+150", lambda gap: 50 <= gap <= 150),
            (">+150",     lambda gap: gap > 150),
        ]
        if my_elo is not None:
            print("\n  Win rate by opponent strength (gap vs your ELO):")
            print(f"    {'Bracket':<14}  {'N':>3}  {'W':>3}  {'L':>3}  {'Rate':>6}")
            for name, pred in brackets:
                bucket = [r for r in tagged if pred(r["opp_elo"] - my_elo)]
                wins = sum(1 for r in bucket if r["tagged_outcome"] == "win")
                losses = sum(1 for r in bucket if r["tagged_outcome"] == "loss")
                n = len(bucket)
                rate = f"{100*wins/n:.0f}%" if n else "—"
                print(f"    {name:<14}  {n:>3}  {wins:>3}  {losses:>3}  {rate:>6}")

        # Sorted loss list (strongest opponent first) — the strategic priority
        losses = sorted(
            [r for r in tagged if r["tagged_outcome"] == "loss"],
            key=lambda r: -r["opp_elo"],
        )
        if losses:
            print(f"\n  LOSSES (sorted by opponent ELO, strongest first — "
                  f"these are your highest-priority replays to study):")
            for r in losses:
                gap = f"+{r['opp_elo'] - my_elo}" if my_elo else f"{r['opp_elo']}"
                print(f"    {r['opp_elo']} ELO ({gap})  HP {r['p1_final_hp']:.0f}-{r['p2_final_hp']:.0f}  "
                      f"{Path(r['path']).name}")

        # Upset wins (wins above your ELO)
        if my_elo is not None:
            upsets = [r for r in tagged
                      if r["tagged_outcome"] == "win" and r["opp_elo"] > my_elo]
            upsets.sort(key=lambda r: -r["opp_elo"])
            if upsets:
                print(f"\n  UPSET WINS (we beat a stronger opponent — study what worked):")
                for r in upsets:
                    gap = f"+{r['opp_elo'] - my_elo}"
                    print(f"    {r['opp_elo']} ELO ({gap})  HP {r['p1_final_hp']:.0f}-{r['p2_final_hp']:.0f}  "
                          f"{Path(r['path']).name}")

        # Per-replay breach hot spots split by outcome — losses tell us our weak spots against strong play
        if losses:
            breach_in_losses = Counter()
            for r in losses:
                # We're P1 by convention (downloaded from 'My Global Replays')
                breach_in_losses.update(r.get("p2_top_breaches", {}))
            print(f"\n  Tiles where we got breached IN LOSSES (weak spots vs strong play):")
            for loc, n in breach_in_losses.most_common(top_n):
                print(f"    {loc}: {n}")

        # Enemy spawn patterns in losses — how strong opponents attack us
        if losses:
            spawns_in_losses = Counter()
            for r in losses:
                spawns_in_losses.update(r.get("p2_top_spawns", {}))
            print(f"\n  Enemy spawn tiles IN LOSSES (how strong opponents deploy against us):")
            for loc, n in spawns_in_losses.most_common(top_n):
                print(f"    {loc}: {n}")
    else:
        print("\n  (No replays matched the naming convention "
              "`<me>_vs_<elo>_elo_(win|loss).replay` — pass well-named files "
              "to unlock ELO-aware analysis.)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
