"""Phase 7 milestone R — per-opponent breakdown of G11 results.

Reads ``data/G11_RESULTS.json``, groups by opponent, prints/saves a
per-opponent W/L summary plus the top-3 improvements and top-3
regressions. Saves to ``data/G11_PER_OPPONENT.md``.

Usage:
    /opt/miniconda3/bin/python3.13 -m algos.athena_v3_planner.eval.per_opponent
"""
from __future__ import annotations

import json
import sys
from collections import defaultdict
from pathlib import Path
from typing import Any, Dict, List

# Reuse the path-order setup from replay_trace.
from algos.athena_v3_planner.eval.replay_trace import _ATHENA_V3, _REPO_ROOT  # noqa: E402


def _results_json() -> Path:
    return _ATHENA_V3 / "data" / "G11_RESULTS.json"


def _output_md() -> Path:
    return _ATHENA_V3 / "data" / "G11_PER_OPPONENT.md"


def main() -> int:
    with open(_results_json()) as f:
        summary = json.load(f)

    by_opp: Dict[str, List[Dict[str, Any]]] = defaultdict(list)
    for r in summary["results"]:
        by_opp[r.get("opponent_name", "?")].append(r)

    rows = []
    for opp, items in by_opp.items():
        n = len(items)
        v13_w = sum(1 for r in items if r["v13_actual"] == "win")
        ath_w = sum(1 for r in items if r["athena_predicted"] == "win")
        ath_l = sum(1 for r in items if r["athena_predicted"] == "loss")
        delta = (ath_w - v13_w) / n if n else 0.0
        rows.append({
            "opponent": opp,
            "n": n,
            "v13_w": v13_w,
            "v13_l": n - v13_w,
            "athena_w": ath_w,
            "athena_l": ath_l,
            "athena_t": n - ath_w - ath_l,
            "delta_per_match": delta,
            "items": items,
        })

    # Sort: improvement (positive delta) first, regressions (negative) at the
    # bottom. Tie-break by sample size desc, then opponent name.
    rows.sort(key=lambda r: (-r["delta_per_match"], -r["n"], r["opponent"]))

    # Identify top-3 improvers and top-3 regressors. With 30 opponents
    # in the corpus (per Phase 0 inventory) plus boss bots, we look at
    # the extremes.
    pos_rows = [r for r in rows if r["delta_per_match"] > 0]
    neg_rows = [r for r in rows if r["delta_per_match"] < 0]
    tie_rows = [r for r in rows if r["delta_per_match"] == 0]

    pos_top = pos_rows[:5]
    neg_top = sorted(neg_rows, key=lambda r: r["delta_per_match"])[:5]

    md_lines: List[str] = []
    md_lines.append("# G11 — Per-opponent breakdown (Phase 7 milestone R)\n")
    md_lines.append("Source: `data/G11_RESULTS.json`\n")
    md_lines.append("")
    md_lines.append(f"- Distinct opponents in corpus: **{len(rows)}**")
    n_replays = sum(r["n"] for r in rows)
    md_lines.append(f"- Total replays: {n_replays}")
    md_lines.append("")

    md_lines.append("## Per-opponent table")
    md_lines.append("")
    md_lines.append("| opponent | n | v13 W-L | Athena W-L-T | Δ/match (Athena − v13) |")
    md_lines.append("|---|---|---|---|---|")
    for r in rows:
        md_lines.append(
            f"| {r['opponent']} | {r['n']} | "
            f"{r['v13_w']}-{r['v13_l']} | "
            f"{r['athena_w']}-{r['athena_l']}-{r['athena_t']} | "
            f"{r['delta_per_match']:+.3f} |"
        )

    md_lines.append("")
    md_lines.append("## Top Athena improvements over v13")
    md_lines.append("")
    md_lines.append("| opponent | n | v13 wins | Athena wins | Δ/match |")
    md_lines.append("|---|---|---|---|---|")
    for r in pos_top:
        md_lines.append(
            f"| {r['opponent']} | {r['n']} | {r['v13_w']} | "
            f"{r['athena_w']} | {r['delta_per_match']:+.3f} |"
        )

    md_lines.append("")
    md_lines.append("## Top Athena regressions vs v13")
    md_lines.append("")
    if not neg_top:
        md_lines.append("(No opponents where Athena performed strictly worse than v13.)")
    else:
        md_lines.append("| opponent | n | v13 wins | Athena wins | Δ/match | match_ids that regressed |")
        md_lines.append("|---|---|---|---|---|---|")
        for r in neg_top:
            regressed = [
                str(it.get("match_id", "?"))
                for it in r["items"]
                if it["v13_actual"] == "win" and it["athena_predicted"] != "win"
            ]
            md_lines.append(
                f"| {r['opponent']} | {r['n']} | {r['v13_w']} | "
                f"{r['athena_w']} | {r['delta_per_match']:+.3f} | "
                f"{', '.join(regressed) if regressed else '—'} |"
            )

    md_lines.append("")
    md_lines.append("## Diagnosis: Athena's losses (v13 won, Athena did not)")
    md_lines.append("")
    md_lines.append("These are the cases where Athena strictly regressed against v13. ")
    md_lines.append("For each one we surface the offense-pick distribution from ")
    md_lines.append("`data/G11_RESULTS.json` to indicate which template Athena ")
    md_lines.append("kept reaching for.\n")

    regressions: List[Dict[str, Any]] = []
    for r in rows:
        for it in r["items"]:
            if it["v13_actual"] == "win" and it["athena_predicted"] != "win":
                regressions.append(it)

    md_lines.append(f"Total regressions: **{len(regressions)}** of "
                    f"{summary['v13_actual_wins']} v13 wins.\n")
    md_lines.append("| match_id | opponent | elo | turns | hp_self | hp_opp | top picks |")
    md_lines.append("|---|---|---|---|---|---|---|")
    from collections import Counter
    for it in regressions:
        picks = Counter(it.get("athena_offense_picks", []))
        top = ", ".join(f"{n}×{name}" for name, n in picks.most_common(3))
        md_lines.append(
            f"| {it.get('match_id','?')} | {it.get('opponent_name','?')} | "
            f"{it.get('opponent_elo',0)} | {it.get('turns_simulated',0)} | "
            f"{it.get('athena_hp_final',0):.1f} | {it.get('opp_hp_final',0):.1f} | "
            f"{top} |"
        )

    md_lines.append("")
    md_lines.append("## Hypotheses on regressions")
    md_lines.append("")
    md_lines.append(
        "1. **Multi-round opponents.** The regressions cluster on long ("
        "20+ turn) matches where Athena's HP attrition is faster than v13's. "
        "Without Athena's own defense applied (G11 setup uses v13's recorded "
        "defense board for fairness), Athena pays for offense-only divergence."
    )
    md_lines.append(
        "2. **High-pressure opponents.** Opponents like ``please-work-man-im-tired`` "
        "and ``new-strat-algo`` deploy heavy mobile pressure that v13 actually "
        "withstood through the v13_inspired defense placement we don't fully "
        "replicate in this counter-factual. Athena's offense doesn't change the "
        "opponent's MP enough to push them off optimal play."
    )
    md_lines.append(
        "3. **Pick-distribution noise.** Athena flips between 2–3 templates "
        "across the regression set (corner_dive_*, scout_rush_*, "
        "interceptor_screen). The classifier posterior is uniform until "
        "frames accumulate, so early-game picks are predictor-empty and "
        "default to the highest-MP-utilization template — sometimes the "
        "wrong call vs the actual opponent's recorded counter-spawn."
    )
    md_lines.append("")
    md_lines.append("These are HYPOTHESES — full Phase 8 final-report writeup "
                    "should validate by re-running individual regressions with "
                    "verbose Candidate.debug logs.")

    _output_md().write_text("\n".join(md_lines) + "\n")
    print(f"wrote {_output_md().relative_to(_REPO_ROOT)}", file=sys.stderr)
    print(
        f"per_opponent: {len(rows)} opponents, "
        f"{len(pos_rows)} improving, {len(neg_rows)} regressing, "
        f"{len(tie_rows)} tied"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
