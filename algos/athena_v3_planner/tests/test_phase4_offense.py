"""Phase 4 offense engine tests — templates, candidate gen, beam search.

No Rust dependency: every test passes a stub `sim_evaluator` so beam
search runs in pure Python. The sim_rs path is exercised by the smoke
test in offense.sim_eval.__main__.
"""
from __future__ import annotations

import time
from pathlib import Path

import pytest

from algos.athena_v3_planner.offense.templates import (
    SPAWN_EDGE_TILES,
    SpawnGroup,
    OffenseTemplate,
    load_all_templates,
    validate_template,
)
from algos.athena_v3_planner.planner.offense import (
    Candidate,
    beam_search,
    generate_candidates,
    pick_offense_plan,
)


# ---------------------------------------------------------------------------
# Templates
# ---------------------------------------------------------------------------

def test_spawn_edge_tiles_is_28():
    assert len(SPAWN_EDGE_TILES) == 28


def test_load_all_templates_validates_clean():
    tpls = load_all_templates()
    assert len(tpls) >= 15  # spec target: 10-20
    names = {t.name for t in tpls}
    # Spec-required names must all be present
    for required in (
        "scout_rush_left", "scout_rush_right", "scout_rush_center",
        "demo_train_left", "demo_train_right",
        "mixed_burst_left", "mixed_burst_right",
        "interceptor_screen", "dual_flank",
        "corner_dive_left", "corner_dive_right",
    ):
        assert required in names, f"missing template: {required}"


def test_validate_template_rejects_bad_unit():
    bad = {"name": "x", "side": "BL", "min_mp": 5,
           "spawns": [{"unit": "BAZOOKA", "loc": [3, 10], "count": 1}]}
    with pytest.raises(ValueError):
        validate_template(bad)


def test_validate_template_rejects_off_edge_loc():
    bad = {"name": "x", "side": "BL", "min_mp": 5,
           "spawns": [{"unit": "SCOUT", "loc": [13, 13], "count": 1}]}
    with pytest.raises(ValueError):
        validate_template(bad)


def test_validate_template_rejects_zero_count():
    bad = {"name": "x", "side": "BL", "min_mp": 5,
           "spawns": [{"unit": "SCOUT", "loc": [3, 10], "count": 0}]}
    with pytest.raises(ValueError):
        validate_template(bad)


# ---------------------------------------------------------------------------
# Candidate generation
# ---------------------------------------------------------------------------

def _empty_state(mp_p1: float = 10.0):
    return {
        "turn": 5,
        "p1": {"hp": 40.0, "sp": 8.0, "mp": float(mp_p1)},
        "p2": {"hp": 40.0, "sp": 8.0, "mp": 10.0},
        "structures": [],
        "mobiles": [],
    }


def test_generate_candidates_always_includes_hoard():
    cands = generate_candidates(_empty_state(0.0), 0.0)
    assert len(cands) >= 1
    assert cands[0].name == "hoard"
    assert cands[0].mp_cost == 0.0


def test_generate_candidates_filters_min_mp():
    # mp=2 — only corner_dive_{left,right} (min_mp=1) should qualify
    cands = generate_candidates(_empty_state(2.0), 2.0)
    names = {c.name for c in cands}
    assert "hoard" in names
    assert "corner_dive_left" in names
    assert "corner_dive_right" in names
    # Big-MP templates should be filtered out
    assert "scout_flood" not in names
    assert "heavy_demo_left" not in names


def test_generate_candidates_skips_blocked_spawn():
    # Place a wall on (3, 10), the canonical scout_rush_left spawn tile
    state = _empty_state(20.0)
    state["structures"].append({
        "xy": [3, 10], "type_idx": 0, "upgraded": False, "hp": 60.0,
        "uid": "1", "player": 1, "turn_start_removal": None,
    })
    cands = generate_candidates(state, 20.0)
    names = {c.name for c in cands}
    # scout_rush_left's only spawn is (3,10) — must be filtered
    assert "scout_rush_left" not in names
    assert "scout_flood" not in names      # also (3,10)
    # scout_rush_right is unaffected
    assert "scout_rush_right" in names


# ---------------------------------------------------------------------------
# Beam search
# ---------------------------------------------------------------------------

def _stub_evaluator(state_dict, my_deploys, opp_deploys, **kw):
    """Trivial evaluator: HP dealt = #my_deploys, HP taken = 1, breaches = 0."""
    return {
        "delta_hp_self": 1.0,
        "delta_hp_opp": float(len(my_deploys)),
        "delta_sp_self": 0.0,
        "delta_sp_opp": 0.0,
        "delta_mp_self": 0.0,
        "delta_mp_opp": 0.0,
        "structures_destroyed_opp": 0,
        "structures_destroyed_self": 0,
        "breaches_self": 0,
        "breaches_opp": 0,
        "wall_clock_ms": 0.01,
    }


def test_beam_search_picks_high_utility():
    state = _empty_state(15.0)
    cands = generate_candidates(state, 15.0)
    best = beam_search(
        state, cands,
        opp_actions_top_k=[],
        sim_evaluator=_stub_evaluator,
    )
    # Stub gives HP_dealt = #units. Highest = whichever template has the
    # most units within the MP budget. 15 MP = scout_flood (14 units).
    # Note: utility = hp_opp - 1*hp_self - 0.5*mp; the BEST candidate
    # should have a positive utility.
    assert best is not None
    assert best.expected_utility > 0
    assert best.sim_count >= 1


def test_beam_search_budget_exits_gracefully():
    state = _empty_state(15.0)
    cands = generate_candidates(state, 15.0)

    # Slow stub: each call sleeps ~10ms
    def slow_eval(*args, **kw):
        time.sleep(0.01)
        return _stub_evaluator(*args, **kw)

    t0 = time.perf_counter()
    best = beam_search(
        state, cands, opp_actions_top_k=[], budget_ms=30.0,
        sim_evaluator=slow_eval,
    )
    elapsed = (time.perf_counter() - t0) * 1000.0
    # Should bail out before evaluating all candidates
    assert elapsed < 200.0, f"budget management failed: {elapsed:.1f}ms"
    # Still returns a non-None candidate
    assert best is not None


def test_beam_search_returns_hoard_when_starved():
    state = _empty_state(0.0)  # no MP
    cands = generate_candidates(state, 0.0)
    best = beam_search(state, cands, opp_actions_top_k=[],
                       sim_evaluator=_stub_evaluator)
    # Only candidate is hoard.
    assert best.name == "hoard"
    assert best.mp_cost == 0.0


def test_beam_search_aggregates_over_opp_actions():
    state = _empty_state(15.0)
    cands = generate_candidates(state, 15.0)

    counter = {"calls": 0}
    def eval_count(*args, **kw):
        counter["calls"] += 1
        return _stub_evaluator(*args, **kw)

    actions = [
        ({"primary_mobile_type": 3, "primary_edge": "TL",
          "wave_size_bucket": "4-7", "spend_mp": True}, 0.6),
        ({"primary_mobile_type": -1, "primary_edge": "NONE",
          "wave_size_bucket": "0", "spend_mp": False}, 0.4),
    ]
    beam_search(state, cands, opp_actions_top_k=actions,
                sim_evaluator=eval_count, budget_ms=8000.0)
    # Each candidate is sim'd against each opp action; should be
    # at least len(cands) * 2 calls, capped by the prune-to-50 logic.
    assert counter["calls"] >= len(cands) * 2 - 1


# ---------------------------------------------------------------------------
# pick_offense_plan
# ---------------------------------------------------------------------------

def test_pick_offense_plan_no_predictor():
    """Without an action predictor we still get a valid Candidate back."""
    state = _empty_state(10.0)
    plan = pick_offense_plan(
        state, mp_available=10.0,
        posterior=None, action_predictor=None,
        turn=5, opp_mp_estimate=5.0,
        budget_ms=8000.0,
    )
    assert plan is not None
    assert isinstance(plan, Candidate)
