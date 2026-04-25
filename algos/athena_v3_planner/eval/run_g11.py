"""Phase 7 milestone Q — full G11 sweep on the 47-replay corpus.

Runs ``evaluate_replay`` against every record in
``algos/athena_v3_planner/data/replay_index.json``, aggregates the
results, and writes:

  - data/G11_RESULTS.json     (machine-readable)
  - data/G11_RESULTS.md       (human-readable summary + per-replay
                               table + gate result)

The Phase 7 gate asks: ``athena_predicted_winrate - v13_actual_winrate
>= 0.15``. We compute both rates from the same 47-replay corpus and
print the verdict.

Usage:
    /opt/miniconda3/bin/python3.13 -m algos.athena_v3_planner.eval.run_g11
"""
from __future__ import annotations

import json
import sys
import time
import traceback
from pathlib import Path
from typing import Any, Dict, List

# Module-level imports keep the same path-order tricks that replay_trace.py
# uses — load it first so its _ensure_path_order side-effect runs.
from algos.athena_v3_planner.eval.replay_trace import (  # noqa: E402
    _build_default_arbiter,
    evaluate_replay,
    _REPO_ROOT,
    _ATHENA_V3,
)


def _index_path() -> Path:
    return _ATHENA_V3 / "data" / "replay_index.json"


def _results_json_path() -> Path:
    return _ATHENA_V3 / "data" / "G11_RESULTS.json"


def _results_md_path() -> Path:
    return _ATHENA_V3 / "data" / "G11_RESULTS.md"


def _wilson_lb(wins: int, n: int, z: float = 1.96) -> float:
    """Wilson 95% lower bound on a Bernoulli mean. Returns 0.0 if n==0."""
    if n <= 0:
        return 0.0
    p = wins / n
    denom = 1.0 + z * z / n
    centre = p + z * z / (2.0 * n)
    margin = z * ((p * (1.0 - p) + z * z / (4.0 * n)) / n) ** 0.5
    return max(0.0, (centre - margin) / denom)


def run_sweep(*, max_replays: int | None = None) -> Dict[str, Any]:
    """Run the G11 sweep. Returns the aggregated results dict.

    If max_replays is set, truncates the corpus (used for Phase 7A
    partial sweeps when budget is tight).
    """
    with open(_index_path()) as f:
        records = json.load(f)
    if max_replays is not None:
        records = records[:max_replays]

    print(f"run_g11: sweeping {len(records)} replays...", file=sys.stderr)

    classifier, predictor, ctx = _build_default_arbiter()
    snapshot_path = ctx["snapshot_path"]

    results: List[Dict[str, Any]] = []
    skipped: List[Dict[str, Any]] = []
    t_start = time.perf_counter()

    for i, rec in enumerate(records):
        rel = rec["file"]
        path = _REPO_ROOT / rel
        if not path.exists():
            print(f"  [{i+1}/{len(records)}] SKIP missing: {rel}", file=sys.stderr)
            skipped.append({"file": rel, "reason": "missing"})
            continue

        t0 = time.perf_counter()
        try:
            res = evaluate_replay(
                path,
                classifier=classifier,
                predictor=predictor,
                snapshot_path=snapshot_path,
            )
            res["v13_actual_from_index"] = rec.get("outcome", "unknown")
            res["opponent_name"] = rec.get("opponent_name", "?")
            res["opponent_elo"] = rec.get("opponent_elo", 0)
            res["is_boss"] = rec.get("is_boss", False)
            res["match_id"] = rec.get("match_id")
            res["v13_actual_p1_hp"] = rec.get("p1_final_hp")
            res["v13_actual_p2_hp"] = rec.get("p2_final_hp")
            results.append(res)
        except Exception as exc:  # noqa: BLE001
            tb = traceback.format_exc()
            print(f"  [{i+1}/{len(records)}] CRASH: {rel}: {exc!r}", file=sys.stderr)
            skipped.append({"file": rel, "reason": "crash",
                            "error": f"{exc!r}\n{tb[:500]}"})
            continue

        wall = time.perf_counter() - t0
        print(
            f"  [{i+1}/{len(records)}] {rec.get('opponent_name','?'):20s} "
            f"v13={res['v13_actual']:5s} athena={res['athena_predicted']:5s} "
            f"hp(self={res['athena_hp_final']:5.1f},opp={res['opp_hp_final']:5.1f}) "
            f"turns={res['turns_simulated']:3d} t={wall:.2f}s",
            file=sys.stderr,
        )

    total_wall = time.perf_counter() - t_start

    # ---- Aggregation ----
    n_total = len(records)
    n_evaluated = len(results)
    n_skipped = len(skipped)
    v13_wins = sum(1 for r in results if r["v13_actual"] == "win")
    v13_losses = sum(1 for r in results if r["v13_actual"] == "loss")
    athena_wins = sum(1 for r in results if r["athena_predicted"] == "win")
    athena_losses = sum(1 for r in results if r["athena_predicted"] == "loss")
    athena_ties = sum(1 for r in results if r["athena_predicted"] == "tie")
    athena_crashes = sum(1 for r in results if r["athena_predicted"] == "crash")

    v13_winrate = (v13_wins / n_evaluated) if n_evaluated else 0.0
    # Athena wins counted vs the same denominator (n_evaluated). Ties/crashes
    # count as non-wins for win-rate purposes.
    athena_winrate = (athena_wins / n_evaluated) if n_evaluated else 0.0
    delta = athena_winrate - v13_winrate
    gate_pass = delta >= 0.15

    # Wilson 95% LB for context
    v13_lb = _wilson_lb(v13_wins, n_evaluated)
    athena_lb = _wilson_lb(athena_wins, n_evaluated)

    summary = {
        "n_total": n_total,
        "n_evaluated": n_evaluated,
        "n_skipped": n_skipped,
        "v13_actual_wins": v13_wins,
        "v13_actual_losses": v13_losses,
        "athena_predicted_wins": athena_wins,
        "athena_predicted_losses": athena_losses,
        "athena_predicted_ties": athena_ties,
        "athena_predicted_crashes": athena_crashes,
        "v13_actual_winrate": v13_winrate,
        "athena_predicted_winrate": athena_winrate,
        "winrate_delta": delta,
        "gate_threshold": 0.15,
        "gate_pass": gate_pass,
        "v13_wilson_lb95": v13_lb,
        "athena_wilson_lb95": athena_lb,
        "total_wall_s": total_wall,
        "results": results,
        "skipped": skipped,
    }
    return summary


def write_md(summary: Dict[str, Any]) -> None:
    md = _results_md_path()
    lines: List[str] = []
    lines.append("# G11 — Athena replay-trace eval (Phase 7)\n")
    lines.append("")
    lines.append("**Setup**: For each ranked v13 replay, replace v13 (player 1) ")
    lines.append("with Athena's offense pipeline and replay the recorded ")
    lines.append("opponent (player 2) actions verbatim through ")
    lines.append("`evaluate_action_phase`. Carry HP turn-to-turn. Declare ")
    lines.append("`athena_predicted` from final HP comparison.\n")
    lines.append("")
    lines.append("## Aggregate")
    lines.append("")
    lines.append(f"- Replays evaluated: **{summary['n_evaluated']}** of {summary['n_total']}")
    lines.append(f"- Skipped: {summary['n_skipped']}")
    lines.append(f"- v13 actual: {summary['v13_actual_wins']}W / "
                 f"{summary['v13_actual_losses']}L "
                 f"(rate {summary['v13_actual_winrate']:.3f}, "
                 f"Wilson LB95 {summary['v13_wilson_lb95']:.3f})")
    lines.append(f"- Athena predicted: {summary['athena_predicted_wins']}W / "
                 f"{summary['athena_predicted_losses']}L / "
                 f"{summary['athena_predicted_ties']}T / "
                 f"{summary['athena_predicted_crashes']}C "
                 f"(rate {summary['athena_predicted_winrate']:.3f}, "
                 f"Wilson LB95 {summary['athena_wilson_lb95']:.3f})")
    lines.append(f"- Wall clock: {summary['total_wall_s']:.1f}s")
    lines.append("")
    lines.append("## Phase 7 gate")
    lines.append("")
    lines.append("| Metric | Value |")
    lines.append("|---|---|")
    lines.append(f"| v13 actual win rate | {summary['v13_actual_winrate']:.3f} |")
    lines.append(f"| Athena predicted win rate | {summary['athena_predicted_winrate']:.3f} |")
    lines.append(f"| Delta (athena − v13) | **{summary['winrate_delta']:+.3f}** |")
    lines.append(f"| Gate threshold (Δ ≥ 0.15) | {'**PASS**' if summary['gate_pass'] else '**FAIL**'} |")
    lines.append("")
    lines.append("## Per-replay results")
    lines.append("")
    lines.append("| # | match_id | opponent | elo | boss | v13 actual | Athena pred | Athena hp_self | Athena hp_opp | turns | wall (s) |")
    lines.append("|---|---|---|---|---|---|---|---|---|---|---|")
    for i, r in enumerate(summary["results"], 1):
        boss = "Y" if r.get("is_boss") else ""
        lines.append(
            f"| {i} | {r.get('match_id','?')} | {r.get('opponent_name','?')} | "
            f"{r.get('opponent_elo',0)} | {boss} | "
            f"{r.get('v13_actual','?')} | {r.get('athena_predicted','?')} | "
            f"{r.get('athena_hp_final',0):.1f} | {r.get('opp_hp_final',0):.1f} | "
            f"{r.get('turns_simulated',0)} | {r.get('wall_clock_s',0):.2f} |"
        )
    if summary["skipped"]:
        lines.append("")
        lines.append("## Skipped replays")
        lines.append("")
        for s in summary["skipped"]:
            lines.append(f"- `{s['file']}` — {s.get('reason','?')}: {s.get('error','')}")
    lines.append("")

    md.write_text("\n".join(lines))
    print(f"wrote {md.relative_to(_REPO_ROOT)}", file=sys.stderr)


def main() -> int:
    summary = run_sweep()
    _results_json_path().write_text(
        json.dumps(summary, indent=2, default=str) + "\n",
    )
    write_md(summary)

    # Print one-line verdict to stdout (so it shows in commit messages)
    print(
        f"G11: n={summary['n_evaluated']} "
        f"v13_actual_wr={summary['v13_actual_winrate']:.3f} "
        f"athena_pred_wr={summary['athena_predicted_winrate']:.3f} "
        f"delta={summary['winrate_delta']:+.3f} "
        f"gate={'PASS' if summary['gate_pass'] else 'FAIL'}"
    )
    return 0 if summary["gate_pass"] else 1


if __name__ == "__main__":
    sys.exit(main())
