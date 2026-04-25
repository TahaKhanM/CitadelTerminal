"""Phase 3 unit tests — opponent model.

Covers:

* feature extractor on synthetic frames, including the player_index
  flip (action-frame JSON: 1=self, 2=opponent).
* ArchetypeClassifier `fit` / `predict_proba` shape correctness.
* Classifier determinism (same input -> same output across two calls).
* ActionPredictor returns >=1 valid action per call (including when
  the posterior is concentrated on the zero-observation archetype).
* update_posterior is **monotone** for an observation that is
  arbitrarily likely under one archetype: that archetype's posterior
  must not decrease.

Pure stdlib + numpy + pytest. No engine.jar / sim_rs / replay
dependencies for the synthetic-frame tests; the action predictor test
uses the labels.json artefact already shipped by Phase 3 task 2.
"""
from __future__ import annotations

import json
from pathlib import Path

import numpy as np
import pytest

from algos.athena_v3_planner.opponent.action_frame_utils import (
    DEMOLISHER_IDX,
    SCOUT_IDX,
)
from algos.athena_v3_planner.opponent.action_predictor import (
    ActionPredictor,
    mp_bucket,
    phase_bucket,
)
from algos.athena_v3_planner.opponent.archetypes import ARCHETYPES, DEFAULT_ARCHETYPE
from algos.athena_v3_planner.opponent.classifier import (
    ArchetypeClassifier,
    load_corpus,
)
from algos.athena_v3_planner.opponent.features import (
    FEATURE_NAMES,
    extract_features,
    features_to_vector,
)


REPO_ROOT = Path(__file__).resolve().parents[3]
NPZ = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "opponent_features.npz"
LABELS_JSON = REPO_ROOT / "algos" / "athena_v3_planner" / "opponent" / "labels.json"


# ---------------------------------------------------------------------------
# Synthetic-frame helpers
# ---------------------------------------------------------------------------

def _make_first_action_frame(
    turn: int,
    *,
    spawns,
    p1_stats=(40.0, 8.0, 1.0, 0),
    p2_stats=(40.0, 8.0, 1.0, 0),
):
    """Build a minimal first-action-frame dict with the given spawn events."""
    return {
        "turnInfo": [1, turn, 0, 0],
        "p1Stats": list(p1_stats),
        "p2Stats": list(p2_stats),
        "p1Units": [[], [], [], [], [], [], [], []],
        "p2Units": [[], [], [], [], [], [], [], []],
        "events": {
            "spawn": list(spawns),
            "breach": [],
            "death": [],
            "attack": [],
            "damage": [],
            "shield": [],
            "selfDestruct": [],
            "move": [],
            "melee": [],
        },
    }


# ---------------------------------------------------------------------------
# Feature extractor
# ---------------------------------------------------------------------------

def test_feature_extractor_keys_and_shape():
    """Feature dict must have exactly FEATURE_NAMES keys and produce a
    same-length vector."""
    # Single empty frame
    f = extract_features([], opponent_player_id=2)
    assert set(f.keys()) == set(FEATURE_NAMES)
    vec = features_to_vector(f)
    assert vec.shape == (len(FEATURE_NAMES),)
    assert vec.dtype == np.float64


def test_feature_extractor_player_index_flip():
    """Same spawn events, swapped player ids, must give symmetric features.

    Build two synthetic streams:
      A: opp=2, all 6 spawns at p2 col 5  (Scouts)
      B: opp=1, all 6 spawns at p1 col 5  (Scouts)
    Both should report the same scout_share, peak_col, mean_wave_size.
    """
    # Stream A: opponent is player 2
    spawns_a = [
        [[5, 14], SCOUT_IDX, str(i), 2] for i in range(6)
    ] + [
        [[10, 13], SCOUT_IDX, "self_a", 1]
    ]
    frame_a = _make_first_action_frame(turn=3, spawns=spawns_a)
    fa = extract_features([frame_a], opponent_player_id=2)

    # Stream B: opponent is player 1
    spawns_b = [
        [[5, 13], SCOUT_IDX, str(i), 1] for i in range(6)
    ] + [
        [[10, 14], SCOUT_IDX, "self_b", 2]
    ]
    frame_b = _make_first_action_frame(turn=3, spawns=spawns_b)
    fb = extract_features([frame_b], opponent_player_id=1)

    # Both must have seen 6 opp Scouts at column 5
    assert fa["scout_share"] == 1.0
    assert fb["scout_share"] == 1.0
    assert fa["spawn_x_peak_col"] == 5.0
    assert fb["spawn_x_peak_col"] == 5.0
    assert fa["mean_wave_size"] == 6.0
    assert fb["mean_wave_size"] == 6.0
    # Crucially: the SELF spawn at col 10 must not contaminate either
    assert fa["spawn_x_peak_share"] == 1.0
    assert fb["spawn_x_peak_share"] == 1.0


def test_feature_extractor_demolisher_share():
    """Sanity: pure demolisher stream -> demolisher_share = 1.0."""
    spawns = [[[14, 14], DEMOLISHER_IDX, str(i), 2] for i in range(4)]
    frame = _make_first_action_frame(turn=5, spawns=spawns)
    f = extract_features([frame], opponent_player_id=2)
    assert f["demolisher_share"] == 1.0
    assert f["scout_share"] == 0.0


def test_feature_extractor_invalid_opp_id_raises():
    with pytest.raises(ValueError):
        extract_features([], opponent_player_id=3)


# ---------------------------------------------------------------------------
# Classifier
# ---------------------------------------------------------------------------

def test_classifier_predict_proba_shape_and_normalization():
    X, labels, _ = load_corpus(NPZ)
    clf = ArchetypeClassifier().fit(X, labels)
    p = clf.predict_proba(X[0])
    assert isinstance(p, dict)
    assert set(p.keys()) == set(ARCHETYPES)
    assert all(isinstance(v, float) for v in p.values())
    s = sum(p.values())
    assert abs(s - 1.0) < 1e-9
    # All probs in [0, 1]
    assert all(0.0 <= v <= 1.0 for v in p.values())


def test_classifier_determinism_two_calls_identical():
    """Same fit twice on the same data; same predict twice; identical."""
    X, labels, _ = load_corpus(NPZ)
    clf_a = ArchetypeClassifier().fit(X, labels)
    clf_b = ArchetypeClassifier().fit(X, labels)
    p_a = clf_a.predict_proba(X[0])
    p_b = clf_b.predict_proba(X[0])
    for k in ARCHETYPES:
        assert p_a[k] == p_b[k]
    # Same instance, two calls
    p_a2 = clf_a.predict_proba(X[0])
    for k in ARCHETYPES:
        assert p_a[k] == p_a2[k]


def test_classifier_predict_top1_consistent_with_proba():
    X, labels, _ = load_corpus(NPZ)
    clf = ArchetypeClassifier().fit(X, labels)
    for i in range(min(10, len(X))):
        p = clf.predict_proba(X[i])
        top1 = clf.predict(X[i])
        assert top1 == max(p.items(), key=lambda kv: kv[1])[0]


def test_classifier_update_posterior_monotone_for_likely_observation():
    """If an observation has very high likelihood under archetype A, the
    posterior on A must not DECREASE compared to the prior.

    Construct a synthetic situation: build a tiny labeled set where
    SCOUT_RUSH has a clear mean separated from the other classes.
    Feed an observation right at SCOUT_RUSH's mean — its posterior
    should never go down.
    """
    n_features = len(FEATURE_NAMES)
    rng = np.random.default_rng(seed=42)
    X = []
    y = []
    # SCOUT_RUSH cluster at +1.0 along all features
    for _ in range(8):
        X.append(np.ones(n_features) + 0.05 * rng.normal(size=n_features))
        y.append("SCOUT_RUSH")
    # Other archetypes at -1.0
    for cls in ("DEMOLISHER_LINE", "BALANCED"):
        for _ in range(8):
            X.append(-np.ones(n_features) + 0.05 * rng.normal(size=n_features))
            y.append(cls)
    X = np.asarray(X)
    clf = ArchetypeClassifier().fit(X, y)
    prior = clf.uniform_prior()
    obs = np.ones(n_features)  # exactly at SCOUT_RUSH mean
    post = clf.update_posterior(prior, obs)
    assert post["SCOUT_RUSH"] >= prior["SCOUT_RUSH"], (
        f"SCOUT_RUSH posterior must not decrease under a matching obs; "
        f"prior={prior['SCOUT_RUSH']}, post={post['SCOUT_RUSH']}"
    )
    # And it should actually rise meaningfully
    assert post["SCOUT_RUSH"] > 0.5, post


def test_classifier_unfitted_raises():
    clf = ArchetypeClassifier()
    with pytest.raises(RuntimeError):
        clf.predict_proba(np.zeros(len(FEATURE_NAMES)))
    with pytest.raises(RuntimeError):
        clf.update_posterior({a: 1.0 / len(ARCHETYPES) for a in ARCHETYPES},
                             np.zeros(len(FEATURE_NAMES)))


# ---------------------------------------------------------------------------
# ActionPredictor
# ---------------------------------------------------------------------------

def test_action_predictor_returns_at_least_one_action():
    ap = ActionPredictor()
    ap.fit_from_labels_json(LABELS_JSON)
    # Standard query
    state = {"mp": 8.0, "turn": 12}
    posterior = {a: 1.0 / len(ARCHETYPES) for a in ARCHETYPES}
    top = ap.top_k(state, posterior, k=5)
    assert len(top) >= 1
    for action_dict, prob in top:
        # Action dict has the four canonical keys
        assert set(action_dict.keys()) == {
            "primary_mobile_type",
            "primary_edge",
            "wave_size_bucket",
            "spend_mp",
        }
        assert isinstance(prob, float)
        assert prob >= 0.0


def test_action_predictor_zero_obs_archetype_falls_back_to_uniform_action():
    """Posterior concentrated on TURTLE_GRIND (0 obs) must still return
    >=1 valid action."""
    ap = ActionPredictor()
    ap.fit_from_labels_json(LABELS_JSON)
    posterior = {a: 0.0 for a in ARCHETYPES}
    posterior["TURTLE_GRIND"] = 1.0
    state = {"mp": 5.0, "turn": 3}
    top = ap.top_k(state, posterior, k=3)
    assert len(top) >= 1


def test_action_predictor_unfitted_raises():
    ap = ActionPredictor()
    with pytest.raises(RuntimeError):
        ap.top_k({"mp": 1.0, "turn": 0}, {a: 1.0 / len(ARCHETYPES) for a in ARCHETYPES})


def test_action_predictor_state_buckets():
    assert mp_bucket(0.5) == "0"
    assert mp_bucket(1.0) == "1-4"
    assert mp_bucket(4.99) == "1-4"
    assert mp_bucket(5.0) == "5-9"
    assert mp_bucket(15.0) == "15+"
    assert phase_bucket(0) == "early"
    assert phase_bucket(9) == "early"
    assert phase_bucket(10) == "mid"
    assert phase_bucket(24) == "mid"
    assert phase_bucket(25) == "late"
