"""Phase 3 driver: build the labeled feature matrix for the 47-replay corpus.

Run me directly to (re)generate:
  - data/opponent_features.npz   — N x F feature matrix + replay metadata
  - opponent/labels.json         — heuristic archetype labels per replay

This is a one-shot ETL script. The classifier (`classifier.py`) and
the cross-validation runner (`cv_runner.py`) load the .npz; the
labeler heuristic in `_label_from_features` is the sole source of
training labels. Long-tail / hard-to-classify cases fall to BALANCED.

Heuristic ordering: rules fire top-down; first match wins. Order is
chosen so dominant signatures (TURTLE, SUPPORT_BURST) win over softer
mixed cases.

Usage:
    /opt/miniconda3/bin/python3.13 algos/athena_v3_planner/opponent/build_corpus.py
"""
from __future__ import annotations

import json
from pathlib import Path
from typing import Any, Dict, List

import numpy as np

from .archetypes import ARCHETYPES, DEFAULT_ARCHETYPE
from .features import (
    FEATURE_NAMES,
    features_from_replay,
    features_to_vector,
)


REPO_ROOT = Path(__file__).resolve().parents[3]
INDEX_PATH = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "replay_index.json"
FEATURE_OUT = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "opponent_features.npz"
LABEL_OUT = REPO_ROOT / "algos" / "athena_v3_planner" / "opponent" / "labels.json"


# ---------------------------------------------------------------------------
# Heuristic labeler — derived from corpus quantile inspection. Each rule's
# thresholds are chosen so the labeler hits the archetypes that actually
# appear in the v13 corpus, not abstract definitions.
# ---------------------------------------------------------------------------

def _label_from_features(f: Dict[str, float]) -> str:
    scout = f["scout_share"]
    demo = f["demolisher_share"]
    intercept = f["interceptor_share"]
    mean_mp = f["mean_mp_at_spawn"]
    mean_wave = f["mean_wave_size"]
    breach_rate = f["breach_rate_per_turn"]
    entropy = f["spawn_x_entropy"]
    edge_asym = f["edge_asymmetry"]
    peak_share = f["spawn_x_peak_share"]
    wall_rm = f["wall_removal_rate"]
    turns = f["turns_observed"]
    total_mobile_per_turn = mean_wave  # proxy

    # 1. TURTLE_GRIND: very low offense, low breach rate (almost no spawns)
    #    OR opp barely scored; long match.
    if total_mobile_per_turn < 1.5 and breach_rate < 0.10:
        return "TURTLE_GRIND"

    # 2. SUPPORT_BURST: high MP at spawn AND big wave size.
    if mean_mp >= 6.0 and mean_wave >= 5.0:
        return "SUPPORT_BURST"

    # 3. SCOUT_RUSH: scout-dominant offense (and not a support-burst).
    if scout >= 0.55 and demo <= 0.30:
        return "SCOUT_RUSH"

    # 4. DEMOLISHER_LINE: demolisher-dominant offense.
    if demo >= 0.45:
        return "DEMOLISHER_LINE"

    # 5. EDGE_FEINT: high spawn-x entropy with active wall removal.
    #    Concentrated columns w/ high asymmetry would be a single-edge
    #    push, not a feint; the feint signature is "broad spawn + walls
    #    being torn down regularly".
    if entropy >= 1.4 and wall_rm >= 0.5 and peak_share <= 0.5:
        return "EDGE_FEINT"

    # 6. fallback
    return DEFAULT_ARCHETYPE


def main() -> None:
    with INDEX_PATH.open("r") as fh:
        index = json.load(fh)

    feature_matrix: List[np.ndarray] = []
    rows: List[Dict[str, Any]] = []
    labels: Dict[str, str] = {}
    label_counts: Dict[str, int] = {a: 0 for a in ARCHETYPES}

    for entry in index:
        replay_path = REPO_ROOT / entry["file"]
        if not replay_path.exists():
            print(f"[warn] missing replay: {replay_path}")
            continue
        feats = features_from_replay(replay_path, opponent_player_id=2)
        vec = features_to_vector(feats)
        feature_matrix.append(vec)
        label = _label_from_features(feats)
        label_counts[label] = label_counts.get(label, 0) + 1
        key = f"{entry['opponent_name']}::{entry['match_id']}"
        labels[key] = {
            "archetype": label,
            "opponent_name": entry["opponent_name"],
            "match_id": entry["match_id"],
            "outcome": entry["outcome"],
            "is_boss": entry["is_boss"],
            "turns": entry["turns"],
            "features": feats,
        }
        rows.append(
            {
                "key": key,
                "opponent_name": entry["opponent_name"],
                "match_id": entry["match_id"],
                "outcome": entry["outcome"],
                "is_boss": entry["is_boss"],
                "turns": entry["turns"],
                "label": label,
                "file": entry["file"],
            }
        )

    X = np.array(feature_matrix, dtype=np.float64)
    label_strs = np.array([r["label"] for r in rows])
    opponent_strs = np.array([r["opponent_name"] for r in rows])
    keys = np.array([r["key"] for r in rows])

    np.savez(
        FEATURE_OUT,
        X=X,
        labels=label_strs,
        opponents=opponent_strs,
        keys=keys,
        feature_names=np.array(FEATURE_NAMES),
    )

    label_meta = {
        "version": 1,
        "generator": "opponent/build_corpus.py",
        "n_replays": len(rows),
        "label_counts": label_counts,
        "archetypes": list(ARCHETYPES),
        "feature_names": list(FEATURE_NAMES),
        "labels": labels,
    }
    with LABEL_OUT.open("w") as fh:
        json.dump(label_meta, fh, indent=2, sort_keys=False)

    print(f"wrote {FEATURE_OUT.relative_to(REPO_ROOT)} ({X.shape})")
    print(f"wrote {LABEL_OUT.relative_to(REPO_ROOT)} ({len(rows)} entries)")
    print("label distribution:")
    for a in ARCHETYPES:
        print(f"  {a}: {label_counts.get(a, 0)}")


if __name__ == "__main__":
    main()
