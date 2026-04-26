"""Smoke tests for the Oracle search loop.

Strategy: stub out the four parallel modules (enumerator, value, opponent
model, and the action-phase sim) so we can validate the SEARCH LOOP logic
without their real implementations.

Run from repo root:
    python -m pytest algos/oracle/oracle_core/test_search.py -v
"""
from __future__ import annotations

import time
from typing import Any, Dict, List

import pytest

from algos.oracle.oracle_core import search as search_mod
from algos.oracle.oracle_core.search import (
    ActionPlan,
    _apply_structures_inplace,
    _plan_to_deploys,
    _score_candidate,
    select_best_plan,
)


# ---------------------------------------------------------------------------
# Test fixtures
# ---------------------------------------------------------------------------

class _StubGameState:
    """Minimal stand-in for gamelib.GameState — search only needs turn_number."""

    def __init__(self, turn: int = 0):
        self.turn_number = turn


class _StubOpponentModel:
    """Returns a fixed list of opp ActionPlans on each call."""

    def __init__(self, plans: List[ActionPlan]):
        self.plans = plans
        self.call_count = 0

    def sample_actions(self, game_state, k: int) -> List[ActionPlan]:
        self.call_count += 1
        return self.plans[:k]


def _stub_adapt(game_state, *, my_player=1, turn=0) -> Dict[str, Any]:
    """Return a tiny but valid sim_rs-schema state dict."""
    return {
        "turn": int(turn),
        "p1": {"hp": 30.0, "sp": 8.0, "mp": 5.0},
        "p2": {"hp": 30.0, "sp": 8.0, "mp": 5.0},
        "structures": [],
        "mobiles": [],
    }


def _stub_sim(state_dict: Dict[str, Any], config_path: str) -> Dict[str, Any]:
    """Identity sim — returns the state untouched. Useful for testing the
    scoring path without going through real action-phase simulation."""
    return dict(state_dict)


def _make_plan(description: str, **kwargs) -> ActionPlan:
    return ActionPlan(description=description, **kwargs)


# ---------------------------------------------------------------------------
# Basic plumbing tests
# ---------------------------------------------------------------------------

def test_actionplan_default_construction():
    """ActionPlan can be constructed with just a description."""
    plan = ActionPlan(description="x")
    assert plan.description == "x"
    assert plan.structure_actions == []
    assert plan.mobile_actions == []


def test_plan_to_deploys_expands_counts():
    plan = ActionPlan(
        description="five scouts",
        mobile_actions=[("SCOUT", [13, 0], 5)],
    )
    deploys = _plan_to_deploys(plan)
    assert len(deploys) == 5
    assert all(d == ("SCOUT", [13, 0]) for d in deploys)


def test_plan_to_deploys_empty():
    plan = ActionPlan(description="empty")
    assert _plan_to_deploys(plan) == []


def test_plan_to_deploys_skips_malformed():
    plan = ActionPlan(description="bad")
    plan.mobile_actions = [("SCOUT", [0, 13], 2), ("BROKEN",)]  # type: ignore
    deploys = _plan_to_deploys(plan)
    # Bad entry skipped, valid entry still expanded.
    assert len(deploys) == 2


def test_apply_structures_adds_new_structure():
    state = {
        "p1": {"hp": 30, "sp": 8, "mp": 5},
        "p2": {"hp": 30, "sp": 8, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    _apply_structures_inplace(
        state,
        [("spawn", "WALL", [13, 13])],
        my_player=1,
    )
    assert len(state["structures"]) == 1
    s = state["structures"][0]
    assert s["xy"] == [13, 13]
    assert s["type_idx"] == 0  # WALL
    assert s["upgraded"] is False
    assert s["player"] == 1
    assert s["hp"] == 60.0  # default WALL hp


def test_apply_structures_upgrades_existing():
    state = {
        "structures": [
            {"xy": [13, 13], "type_idx": 2, "upgraded": False, "hp": 75.0,
             "uid": "1", "player": 1, "turn_start_removal": None},
        ],
        "p1": {"hp": 30, "sp": 8, "mp": 5},
        "p2": {"hp": 30, "sp": 8, "mp": 5},
    }
    _apply_structures_inplace(
        state,
        [("upgrade", "TURRET", [13, 13])],
        my_player=1,
    )
    # Should mutate in place, not append.
    assert len(state["structures"]) == 1
    assert state["structures"][0]["upgraded"] is True
    assert state["structures"][0]["hp"] >= 100.0  # upgraded turret HP


def test_apply_structures_skips_mobile_units():
    state = {
        "structures": [],
        "p1": {"hp": 30, "sp": 8, "mp": 5},
        "p2": {"hp": 30, "sp": 8, "mp": 5},
    }
    _apply_structures_inplace(
        state,
        [("spawn", "SCOUT", [13, 0])],  # mobile in structures slot — should skip
        my_player=1,
    )
    assert len(state["structures"]) == 0


# ---------------------------------------------------------------------------
# Search loop semantics
# ---------------------------------------------------------------------------

def test_empty_candidates_returns_noop():
    """If the enumerator returns an empty list, we get a no-op plan."""
    gs = _StubGameState()
    opp_model = _StubOpponentModel([])

    def empty_enum(game_state, *, max_candidates):
        return []

    def stub_eval(state_dict, my_player=1):
        return 0.0

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=2.0,
        _enumerate_fn=empty_enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    assert result is not None
    assert "no_op" in result.description


def test_picks_highest_scoring_candidate():
    """Search picks the candidate with the highest evaluator score."""
    gs = _StubGameState()
    candidates = [
        _make_plan("low_value", mobile_actions=[("SCOUT", [13, 0], 1)]),
        _make_plan("high_value", mobile_actions=[("SCOUT", [14, 0], 1)]),
        _make_plan("mid_value", mobile_actions=[("SCOUT", [12, 1], 1)]),
    ]
    # Stub evaluator: score by description.
    score_table = {"low_value": 1.0, "high_value": 100.0, "mid_value": 50.0}

    def stub_eval(state_dict, my_player=1):
        # We track which plan we're scoring by the description embedded in
        # mobile uids — but identity sim just passes through. Instead we
        # embed score information in the spawn xy.
        for m in state_dict.get("mobiles", []):
            if m["xy"] == [13, 0]:
                return 1.0
            if m["xy"] == [14, 0]:
                return 100.0
            if m["xy"] == [12, 1]:
                return 50.0
        return 0.0

    opp_model = _StubOpponentModel([])  # idle opponent

    def enum(game_state, *, max_candidates):
        return candidates

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=5.0,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    assert result.description == "high_value"


def test_time_budget_cutoff():
    """Search stops at deadline even with many candidates left."""
    gs = _StubGameState()
    # Many candidates, all scored equally (zero) — search should stop early.
    candidates = [_make_plan(f"plan_{i}") for i in range(1000)]

    n_evaluations = {"count": 0}

    def slow_eval(state_dict, my_player=1):
        # Simulate 5 ms of scoring per call.
        time.sleep(0.005)
        n_evaluations["count"] += 1
        return 0.0

    opp_model = _StubOpponentModel([])

    def enum(game_state, *, max_candidates):
        return candidates

    t0 = time.time()
    result = select_best_plan(
        gs, opp_model,
        time_budget_s=0.2,  # 200 ms budget
        k_opp_samples=1,
        _enumerate_fn=enum,
        _evaluate_fn=slow_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    elapsed = time.time() - t0
    # Must terminate within budget + 200 ms slack (a single in-flight eval
    # may overrun). It must NOT run all 1000 candidates.
    assert elapsed < 0.6, f"search overran budget: {elapsed:.2f}s"
    assert n_evaluations["count"] < 1000, (
        f"search evaluated all {n_evaluations['count']} candidates — "
        "deadline check is broken"
    )
    # Must still return a plan, not crash.
    assert result is not None


def test_opponent_model_no_data_uses_idle_opp():
    """When opp_model returns nothing, search uses an idle opp and still scores."""
    gs = _StubGameState()
    candidates = [_make_plan("only", mobile_actions=[("SCOUT", [13, 0], 1)])]

    def stub_eval(state_dict, my_player=1):
        return 42.0

    # opp_model returns no plans
    opp_model = _StubOpponentModel([])

    def enum(game_state, *, max_candidates):
        return candidates

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=2.0,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    assert result.description == "only"


def test_averages_over_opponent_samples():
    """EU is averaged over K opp samples — verify by injecting variable scores."""
    gs = _StubGameState()
    plan_a = _make_plan("A", mobile_actions=[("SCOUT", [13, 0], 1)])
    plan_b = _make_plan("B", mobile_actions=[("SCOUT", [14, 0], 1)])

    # opp_plan a has 5 mobile units, b has 1. Score = -(opp_mobiles) so
    # plans face MORE punishment when opp deploys more.
    # If opp samples are [a,b,a,b], avg punishment is (-5 + -1 + -5 + -1)/4 = -3.
    # Both candidates face the same opp distribution, so both score -3 on
    # the opp side. We add +1 if the candidate's spawn is at [14, 0] (plan_b).
    opp_a = ActionPlan(
        description="opp_heavy",
        mobile_actions=[("SCOUT", [13, 27], 5)],
    )
    opp_b = ActionPlan(
        description="opp_light",
        mobile_actions=[("SCOUT", [13, 27], 1)],
    )
    opp_model = _StubOpponentModel([opp_a, opp_b, opp_a, opp_b])

    def stub_eval(state_dict, my_player=1):
        my_mobs = sum(1 for m in state_dict.get("mobiles", [])
                      if m["player"] == 1)
        opp_mobs = sum(1 for m in state_dict.get("mobiles", [])
                       if m["player"] == 2)
        bonus = 0.0
        # Reward the candidate that picked tile [14, 0]
        for m in state_dict.get("mobiles", []):
            if m["player"] == 1 and m["xy"] == [14, 0]:
                bonus += 1.0
        return float(my_mobs) - float(opp_mobs) + bonus

    def enum(game_state, *, max_candidates):
        return [plan_a, plan_b]

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=5.0,
        k_opp_samples=4,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    # plan_b has the +1 bonus from [14, 0], same opp punishment, so it wins.
    assert result.description == "B"


def test_evaluator_exception_does_not_crash():
    """A buggy evaluator on one candidate falls through gracefully."""
    gs = _StubGameState()
    good_plan = _make_plan("good")
    bad_plan = _make_plan("bad")

    def flaky_eval(state_dict, my_player=1):
        # Fail on bad_plan — we identify by the dict's identity.
        if state_dict.get("_marker") == "bad":
            raise RuntimeError("synthetic failure")
        return 10.0

    # Mark each rollout state via a sim hook so flaky_eval can distinguish.
    def marking_sim(state_dict, config_path):
        # State dict already contains spawned mobiles for this plan.
        # We attach a marker based on first mobile's spawn xy.
        out = dict(state_dict)
        if any(m["xy"] == [13, 0] for m in out.get("mobiles", [])):
            out["_marker"] = "good"
        else:
            out["_marker"] = "bad"
        return out

    good_plan.mobile_actions = [("SCOUT", [13, 0], 1)]
    bad_plan.mobile_actions = [("SCOUT", [14, 0], 1)]

    opp_model = _StubOpponentModel([])

    def enum(game_state, *, max_candidates):
        return [bad_plan, good_plan]  # bad first so good must win after

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=2.0,
        _enumerate_fn=enum,
        _evaluate_fn=flaky_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=marking_sim,
        _config_path="dummy",
    )
    # Despite the bad_plan crashing, search picks good_plan.
    assert result.description == "good"


def test_enumerator_failure_returns_noop():
    """If enumerator itself throws, we return a no-op plan."""
    gs = _StubGameState()

    def broken_enum(game_state, *, max_candidates):
        raise ValueError("synthetic enumerator failure")

    def stub_eval(state_dict, my_player=1):
        return 0.0

    result = select_best_plan(
        gs, _StubOpponentModel([]),
        time_budget_s=2.0,
        _enumerate_fn=broken_enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    assert result is not None
    assert "failed" in result.description


def test_debug_log_called():
    """debug_log receives diagnostic strings about progress."""
    gs = _StubGameState()
    plan = _make_plan("only")
    opp_model = _StubOpponentModel([])

    log_lines: List[str] = []

    def enum(game_state, *, max_candidates):
        return [plan]

    def stub_eval(state_dict, my_player=1):
        return 1.0

    select_best_plan(
        gs, opp_model,
        time_budget_s=2.0,
        debug_log=log_lines.append,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=_stub_adapt,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    assert any("enumerated" in line for line in log_lines)
    assert any("picked" in line for line in log_lines)


def test_score_candidate_smoke():
    """Direct call to _score_candidate with hand-built state and stubs."""
    base = {
        "turn": 0,
        "p1": {"hp": 30, "sp": 8, "mp": 5},
        "p2": {"hp": 30, "sp": 8, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    plan = _make_plan(
        "test",
        structure_actions=[("spawn", "WALL", [13, 13])],
        mobile_actions=[("SCOUT", [13, 0], 2)],
    )
    opp = ActionPlan(description="opp_idle")

    def stub_eval(state_dict, my_player=1):
        # Score by our HP — strictly positive means survival
        side = "p1" if my_player == 1 else "p2"
        return float(state_dict[side]["hp"])

    avg, n = _score_candidate(
        base_state_dict=base,
        plan=plan,
        opp_samples=[opp, opp, opp],
        config_path="dummy",
        evaluate_fn=stub_eval,
        my_player=1,
        depth=1,
        deadline=time.time() + 1.0,
        sim_fn_override=_stub_sim,
    )
    assert n == 3
    assert avg == 30.0  # identity sim preserves HP


def test_base_state_unmutated_across_candidates():
    """base_state_dict must NOT be mutated by candidate scoring (deepcopy)."""
    gs = _StubGameState()

    base_seen: List[Dict[str, Any]] = []

    def adapt_capture(game_state, *, my_player=1, turn=0):
        d = _stub_adapt(game_state, my_player=my_player, turn=turn)
        base_seen.append(d)
        return d

    plan_a = _make_plan(
        "with_wall",
        structure_actions=[("spawn", "WALL", [13, 13])],
        mobile_actions=[("SCOUT", [13, 0], 1)],
    )
    plan_b = _make_plan(
        "no_wall",
        mobile_actions=[("SCOUT", [14, 0], 1)],
    )

    def enum(game_state, *, max_candidates):
        return [plan_a, plan_b]

    def stub_eval(state_dict, my_player=1):
        return 0.0

    opp_model = _StubOpponentModel([])
    select_best_plan(
        gs, opp_model,
        time_budget_s=2.0,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=adapt_capture,
        _sim_fn=_stub_sim,
        _config_path="dummy",
    )
    # The base dict captured by adapt MUST still have zero structures —
    # if it leaked the wall from plan_a we'd see it here.
    assert base_seen
    assert len(base_seen[0]["structures"]) == 0


# ---------------------------------------------------------------------------
# Integration smoke (when sim_rs is locally available)
# ---------------------------------------------------------------------------

def test_with_real_sim_rs_smoke():
    """If sim_rs is locally available, run the full search loop with it.

    This verifies the structure-application + deploy + simulate flow with
    the actual Rust sim, just using stub enumerator/evaluator/opp model.
    Skipped on hosts without sim_rs.
    """
    from algos.oracle.oracle_core.sim_eval import _get_sim_rs, _default_config_path

    if _get_sim_rs() is None:
        pytest.skip("sim_rs not available locally")

    cfg = _default_config_path()
    gs = _StubGameState(turn=3)

    # Build a candidate that walls a tile and spawns 3 scouts.
    plan = _make_plan(
        "wall_plus_3_scouts",
        structure_actions=[("spawn", "WALL", [13, 13])],
        mobile_actions=[("SCOUT", [13, 0], 3)],
        sp_cost=2.0,
        mp_cost=3.0,
    )

    def enum(game_state, *, max_candidates):
        return [plan]

    def stub_eval(state_dict, my_player=1):
        side = "p1" if my_player == 1 else "p2"
        opp_side = "p2" if my_player == 1 else "p1"
        return float(state_dict[side]["hp"]) - float(state_dict[opp_side]["hp"])

    def adapt_for_sim(game_state, *, my_player=1, turn=0):
        # Build a small but legal mid-game state.
        return {
            "turn": int(turn),
            "p1": {"hp": 30.0, "sp": 8.0, "mp": 5.0},
            "p2": {"hp": 30.0, "sp": 8.0, "mp": 5.0},
            "structures": [],
            "mobiles": [],
        }

    opp_model = _StubOpponentModel([
        ActionPlan(description="opp_idle"),
    ])

    result = select_best_plan(
        gs, opp_model,
        time_budget_s=5.0,
        _enumerate_fn=enum,
        _evaluate_fn=stub_eval,
        _adapt_fn=adapt_for_sim,
        # No _sim_fn override — exercise the real sim_rs path.
        _config_path=cfg,
    )
    assert result.description == "wall_plus_3_scouts"


if __name__ == "__main__":
    import sys
    sys.exit(pytest.main([__file__, "-v"]))
