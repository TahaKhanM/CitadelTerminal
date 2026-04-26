"""Leave-one-opponent-out cross-validation for the Phase 3 classifier.

For each of the 30 distinct opponents in the labeled corpus:
  1. Hold out all replays where opponent_name == this opponent.
  2. Fit ArchetypeClassifier on the remaining replays.
  3. Predict on the held-out set.
  4. Track top-1 accuracy + per-class confusion matrix.

Writes data/PHASE3_CV_RESULTS.md with summary numbers + a confusion
matrix + per-opponent accuracy.

Usage:
    /opt/miniconda3/bin/python3.13 algos/athena_v3_planner/opponent/cv_runner.py
"""
from __future__ import annotations

import json
from collections import defaultdict
from pathlib import Path
from typing import Dict, List, Tuple

import numpy as np

from .archetypes import ARCHETYPES
from .classifier import ArchetypeClassifier, load_corpus


REPO_ROOT = Path(__file__).resolve().parents[3]
NPZ = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "opponent_features.npz"
OUT = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "PHASE3_CV_RESULTS.md"


def _confusion(true_labels: List[str], pred_labels: List[str]) -> Dict[str, Dict[str, int]]:
    """Build {true_label -> {pred_label -> count}}."""
    cm: Dict[str, Dict[str, int]] = {a: {b: 0 for b in ARCHETYPES} for a in ARCHETYPES}
    for t, p in zip(true_labels, pred_labels):
        if t in cm and p in cm[t]:
            cm[t][p] += 1
    return cm


def _format_cm_md(cm: Dict[str, Dict[str, int]]) -> str:
    """Render the confusion matrix as a markdown table."""
    cols = list(ARCHETYPES)
    header = "| true \\\\ pred | " + " | ".join(cols) + " |"
    sep = "|" + "---|" * (len(cols) + 1)
    lines = [header, sep]
    for r in ARCHETYPES:
        row = [r] + [str(cm[r][c]) for c in cols]
        lines.append("| " + " | ".join(row) + " |")
    return "\n".join(lines)


def main() -> None:
    X, labels, opponents = load_corpus(NPZ)
    distinct_opps = sorted(set(opponents))
    n_total = len(labels)
    print(f"[cv] {n_total} replays, {len(distinct_opps)} distinct opponents")

    pred_records: List[Tuple[str, str, str]] = []  # (opp, true, pred)

    for held_out_opp in distinct_opps:
        train_mask = np.array([o != held_out_opp for o in opponents])
        test_mask = ~train_mask
        if test_mask.sum() == 0:
            continue
        clf = ArchetypeClassifier().fit(X[train_mask], [l for l, m in zip(labels, train_mask) if m])
        for i, m in enumerate(test_mask):
            if m:
                pred = clf.predict(X[i])
                pred_records.append((opponents[i], labels[i], pred))

    n_correct = sum(1 for _, t, p in pred_records if t == p)
    accuracy = n_correct / len(pred_records) if pred_records else 0.0

    # Per-opponent accuracy
    per_opp: Dict[str, List[Tuple[str, str]]] = defaultdict(list)
    for opp, t, p in pred_records:
        per_opp[opp].append((t, p))

    # Confusion matrix
    cm = _confusion(
        [t for _, t, _ in pred_records],
        [p for _, _, p in pred_records],
    )

    # Per-class recall
    per_class_recall: Dict[str, Tuple[int, int]] = {}
    for cls in ARCHETYPES:
        n_in = sum(1 for _, t, _ in pred_records if t == cls)
        n_correct_cls = sum(1 for _, t, p in pred_records if t == cls and p == cls)
        per_class_recall[cls] = (n_correct_cls, n_in)

    # Build markdown report
    md = []
    md.append("# Phase 3 — Leave-one-opponent-out cross-validation\n")
    md.append(f"Run via `algos/athena_v3_planner/opponent/cv_runner.py` on the\n47-replay v13 corpus indexed at `data/replay_index.json`.\n")
    md.append(f"## Headline\n")
    md.append(f"- **Mean top-1 accuracy: {accuracy:.3f}** ({n_correct}/{len(pred_records)})")
    md.append(f"- Distinct opponents (folds): {len(distinct_opps)}")
    md.append(f"- Validation gate (>=0.70): **{'PASS' if accuracy >= 0.70 else 'FAIL'}**\n")

    md.append("## Per-class recall\n")
    md.append("| archetype | recall | n_held_out |")
    md.append("|---|---|---|")
    for cls in ARCHETYPES:
        n_correct_cls, n_in = per_class_recall[cls]
        rec = (n_correct_cls / n_in) if n_in else float("nan")
        rec_str = f"{rec:.3f}" if n_in else "n/a (no test samples)"
        md.append(f"| {cls} | {rec_str} | {n_in} |")
    md.append("")

    md.append("## Confusion matrix\n")
    md.append(_format_cm_md(cm))
    md.append("")

    md.append("## Per-opponent accuracy (top-1)\n")
    md.append("| opponent | accuracy | n_replays | true | pred |")
    md.append("|---|---|---|---|---|")
    for opp in sorted(per_opp.keys()):
        rows = per_opp[opp]
        nc = sum(1 for t, p in rows if t == p)
        n = len(rows)
        true_labels = ",".join(t for t, _ in rows)
        pred_labels = ",".join(p for _, p in rows)
        md.append(f"| {opp} | {nc}/{n} | {n} | {true_labels} | {pred_labels} |")
    md.append("")

    md.append("## Failure-mode notes\n")
    md.append("- Most-confused archetypes: see the off-diagonal cells of the\n"
              "  confusion matrix above. Common pattern: SCOUT_RUSH ↔ BALANCED\n"
              "  (the labeler uses scout_share>=0.55 as the SCOUT_RUSH threshold;\n"
              "  borderline replays with 0.40-0.55 scout share end up BALANCED\n"
              "  in the labels but the GaussianNB sometimes prefers SCOUT_RUSH\n"
              "  by mean proximity).")
    md.append("- Singleton classes (EDGE_FEINT=1, SUPPORT_BURST=1) effectively\n"
              "  cannot be held-out-and-recovered: holding out the only training\n"
              "  example for a class means it has 0 training samples for that fold,\n"
              "  so it falls back to global stats and is predicted only when the\n"
              "  global posterior happens to land on it.")
    md.append("- TURTLE_GRIND has 0 corpus samples → impossible to test. Phase 4\n"
              "  / Phase 9 should retrain when more replays land or seed a synthetic\n"
              "  TURTLE_GRIND sample.")

    OUT.write_text("\n".join(md))

    print(f"[cv] mean top-1 accuracy = {accuracy:.3f}  ({n_correct}/{len(pred_records)})")
    print(f"[cv] gate (>=0.70): {'PASS' if accuracy >= 0.70 else 'FAIL'}")
    print(f"[cv] wrote {OUT.relative_to(REPO_ROOT)}")


if __name__ == "__main__":
    main()
