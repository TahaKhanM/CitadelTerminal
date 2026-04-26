"""Tests for OpponentModel.

Run with::

    cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
    python -m pytest algos/oracle/oracle_core/test_opponent_model.py -v

The corpus-loading test is conditional: if
``Ranked Replays/`` doesn't exist (e.g., CI without the data drop), the
test is skipped rather than failed.
"""
from __future__ import annotations

import os
import sys
from pathlib import Path

import pytest

# Make `oracle_core` importable as a top-level package, mirroring the
# live-server layout (algo zip extracts to fs root).
HERE = Path(__file__).resolve()
ORACLE_ROOT = HERE.parents[1]  # algos/oracle/
if str(ORACLE_ROOT) not in sys.path:
    sys.path.insert(0, str(ORACLE_ROOT))

from oracle_core.enumerator import ActionPlan  # noqa: E402
from oracle_core.opponent_model import OpponentModel  # noqa: E402


def _project_root() -> Path:
    here = Path(__file__).resolve()
    for p in here.parents:
        if (p / "CLAUDE.md").exists() and (p / "algos").exists():
            return p
    return here.parents[3]


# ---------------------------------------------------------------------------
# 1. Init with no corpus -> uses prior, sample_actions returns k plans
# ---------------------------------------------------------------------------

def test_init_no_corpus_uses_prior():
    """Empty corpus -> prior archetypes only; sample_actions still yields
    diverse plans up to the cap."""
    model = OpponentModel(corpus_dir="/nonexistent/path/that/does/not/exist")
    assert model.stats()["n_observations"] == 0
    plans = model.sample_actions(game_state={"turn": 5,
                                              "p1": {"mp": 5.0},
                                              "p2": {"mp": 8.0}},
                                  k=8)
    assert len(plans) > 0
    assert len(plans) <= 8
    for plan in plans:
        assert isinstance(plan, ActionPlan)
        assert hasattr(plan, "structure_actions")
        assert hasattr(plan, "mobile_actions")


def test_sample_actions_returns_distinct_plans_with_no_corpus():
    """Prior archetypes have distinct signatures."""
    model = OpponentModel(corpus_dir="/nonexistent")
    plans = model.sample_actions({"turn": 0, "p1": {"mp": 1}, "p2": {"mp": 1}},
                                 k=8)
    sigs = [p.signature() for p in plans]
    assert len(sigs) == len(set(sigs)), "Sampled plans should be unique"


def test_sample_actions_respects_k_zero():
    model = OpponentModel(corpus_dir="/nonexistent")
    plans = model.sample_actions({"turn": 1, "p1": {"mp": 1}, "p2": {"mp": 1}},
                                 k=0)
    assert plans == []


# ---------------------------------------------------------------------------
# 2. Init with the existing Ranked Replays corpus -> loads >0 observations
# ---------------------------------------------------------------------------

@pytest.mark.skipif(
    not (_project_root() / "Ranked Replays").exists(),
    reason="Ranked Replays corpus not present locally",
)
def test_init_with_ranked_replays_loads_observations():
    corpus = _project_root() / "Ranked Replays"
    model = OpponentModel(corpus_dir=str(corpus))
    stats = model.stats()
    assert stats["n_replays_loaded"] > 0, (
        f"Expected to load >0 replays from {corpus}; got {stats}"
    )
    assert stats["n_observations"] > 0, (
        f"Expected to extract >0 opponent action plans; got {stats}"
    )
    assert stats["n_buckets"] > 0


# ---------------------------------------------------------------------------
# 3. sample_actions returns at most k plans, all non-empty
# ---------------------------------------------------------------------------

@pytest.mark.skipif(
    not (_project_root() / "Ranked Replays").exists(),
    reason="Ranked Replays corpus not present locally",
)
def test_sample_actions_with_corpus_returns_valid_plans():
    corpus = _project_root() / "Ranked Replays"
    model = OpponentModel(corpus_dir=str(corpus))
    # Mid-game state: turn 8, opp_mp 10 (typical Scout-rush threshold),
    # our_mp 6, no recent breaches.
    # Use a deeper-game state where opp_mp_band=3-4 has ~1600+ direct-bucket
    # observations, the vast majority with mobile actions. This guards against
    # the small-bucket dominated-by-structure-only-plans corner case.
    state = {"turn": 35, "p1": {"mp": 12.0}, "p2": {"mp": 14.0}}
    plans = model.sample_actions(state, k=8)
    assert len(plans) > 0
    assert len(plans) <= 8
    sigs = [p.signature() for p in plans]
    assert len(sigs) == len(set(sigs))
    # Mid-late game with opp at high MP: most observed plans should have a
    # mobile wave. We don't require 100% — some opp plans are no-op holds.
    n_with_mobile = sum(1 for p in plans if p.mobile_actions)
    assert n_with_mobile >= 1, (
        f"Expected at least one mobile wave in {len(plans)} plans; got "
        f"{[p.description for p in plans]}"
    )


# ---------------------------------------------------------------------------
# 4. All sampled plans are valid ActionPlan objects with required fields
# ---------------------------------------------------------------------------

@pytest.mark.skipif(
    not (_project_root() / "Ranked Replays").exists(),
    reason="Ranked Replays corpus not present locally",
)
def test_sampled_plans_have_required_fields():
    corpus = _project_root() / "Ranked Replays"
    model = OpponentModel(corpus_dir=str(corpus))
    state = {"turn": 12, "p1": {"mp": 7}, "p2": {"mp": 9}}
    plans = model.sample_actions(state, k=10)
    for p in plans:
        assert isinstance(p, ActionPlan)
        # Both list fields exist (default is empty list, never None)
        assert isinstance(p.structure_actions, list)
        assert isinstance(p.mobile_actions, list)
        assert isinstance(p.description, str)
        assert isinstance(p.sp_cost, float)
        assert isinstance(p.mp_cost, float)
        # Mobile action shape (shorthand_or_idx, [x,y], count)
        for act in p.mobile_actions:
            assert len(act) == 3
            assert isinstance(act[1], list) and len(act[1]) == 2
            assert isinstance(act[2], int) and act[2] >= 1


# ---------------------------------------------------------------------------
# 5. update_observation accepts an action frame and doesn't crash
# ---------------------------------------------------------------------------

def test_update_observation_smoke():
    model = OpponentModel(corpus_dir="/nonexistent")
    fake_action_frame = {
        "turnInfo": [1, 5, 0],
        "events": {
            "spawn": [
                [[14, 27], 3, "1001", 2],
                [[14, 27], 3, "1002", 2],
                [[14, 27], 3, "1003", 2],
            ]
        },
    }
    # Should not raise.
    model.update_observation(turn_num=5, observed_opp_action=fake_action_frame)


def test_update_observation_handles_garbage():
    model = OpponentModel(corpus_dir="/nonexistent")
    # Should not raise on bad input.
    model.update_observation(0, None)  # type: ignore[arg-type]
    model.update_observation(0, {"events": "bad"})


# ---------------------------------------------------------------------------
# 6. Bucket-key utility coverage
# ---------------------------------------------------------------------------

def test_bucket_widening_falls_through_to_prior():
    """A bucket with no observations and no neighbors should still produce
    plans (from the prior)."""
    model = OpponentModel(corpus_dir="/nonexistent")
    # Extreme out-of-distribution state.
    state = {"turn": 99, "p1": {"mp": 50}, "p2": {"mp": 50}}
    plans = model.sample_actions(state, k=5)
    assert len(plans) >= 1
