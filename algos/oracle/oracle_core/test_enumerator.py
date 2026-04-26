"""Unit tests for oracle_core.enumerator.

Run with::
    cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
    python -m pytest algos/oracle/oracle_core/test_enumerator.py -v
"""
from __future__ import annotations

import json
import os
import sys
from pathlib import Path

import pytest


# ---------------------------------------------------------------------------
# Wire up the algo package so `import gamelib` and `from oracle_core...`
# both resolve cleanly even when pytest is invoked from the repo root.
# ---------------------------------------------------------------------------

_THIS_DIR = Path(__file__).resolve().parent          # .../algos/oracle/oracle_core
_ORACLE_DIR = _THIS_DIR.parent                        # .../algos/oracle
_REPO_ROOT = _ORACLE_DIR.parents[1]                   # .../CitadelTerminal

if str(_ORACLE_DIR) not in sys.path:
    sys.path.insert(0, str(_ORACLE_DIR))


import gamelib  # noqa: E402

from oracle_core.enumerator import (  # noqa: E402
    ActionPlan,
    DEFAULT_MAX_CANDIDATES,
    DEMOLISHER,
    INTERCEPTOR,
    SCOUT,
    SUPPORT,
    TURRET,
    WALL,
    apply_to_game_state,
    enumerate_candidates,
    _ALL_EDGE_TILES_SET,
    _is_in_bottom_half,
)


# ---------------------------------------------------------------------------
# Fixtures
# ---------------------------------------------------------------------------

@pytest.fixture(scope="session")
def live_config():
    """Load the live Citadel config snapshot shipped with the algo."""
    config_path = _ORACLE_DIR / "data" / "citadel_config_snapshot.json"
    with config_path.open() as f:
        return json.load(f)


def _make_state_dict(
    *,
    turn: int = 0,
    p1_hp: float = 40.0,
    p1_sp: float = 8.0,
    p1_mp: float = 1.0,
    p2_hp: float = 40.0,
    p2_sp: float = 8.0,
    p2_mp: float = 1.0,
    p1_units=None,
    p2_units=None,
):
    """Build a serialized state JSON in the engine's wire format."""
    p1_units = p1_units or [[], [], [], [], [], [], [], []]
    p2_units = p2_units or [[], [], [], [], [], [], [], []]
    return json.dumps({
        "turnInfo": [0, turn, 0, 0],
        "p1Stats": [p1_hp, p1_sp, p1_mp, 0.0],
        "p2Stats": [p2_hp, p2_sp, p2_mp, 0.0],
        "p1Units": p1_units,
        "p2Units": p2_units,
        "events": {},
    })


@pytest.fixture
def empty_state(live_config):
    """Turn-0 game state: 8 SP, 1 MP, 40 HP, no units placed."""
    gs = gamelib.GameState(live_config, _make_state_dict())
    gs.suppress_warnings(True)
    return gs


@pytest.fixture
def midgame_state(live_config):
    """Turn-15 game state with realistic resources and a sprinkling of
    structures + a recent breach hint. Mimics what Oracle would see ~5
    minutes into a real match.
    """
    # Starter defensive ring: turrets at (3,12), (24,12), supports at (13,4)
    # and (14,4), and walls along y=13 corners.
    p1_units = [
        # Walls (FF): index 0 — (x, y, hp, uid)
        [
            [0, 13, 60.0, "p1_wall_a"],
            [27, 13, 60.0, "p1_wall_b"],
            [3, 13, 60.0, "p1_wall_c"],
            [24, 13, 60.0, "p1_wall_d"],
        ],
        # Supports (EF): index 1
        [
            [13, 4, 1.0, "p1_supp_a"],
            [14, 4, 1.0, "p1_supp_b"],
        ],
        # Turrets (DF): index 2
        [
            [3, 12, 60.0, "p1_turret_a"],
            [24, 12, 60.0, "p1_turret_b"],
            [10, 12, 30.0, "p1_turret_dmg"],  # damaged: 30 / 60 = 50% (boundary)
            [17, 12, 25.0, "p1_turret_more_dmg"],  # 25 / 60 < 50%
        ],
        # Mobiles in the array but not on the board at deploy phase:
        [], [], [], [], [],
    ]
    p2_units = [[], [], [], [], [], [], [], []]
    gs = gamelib.GameState(
        live_config,
        _make_state_dict(
            turn=15, p1_hp=30.0, p1_sp=12.0, p1_mp=8.0,
            p2_hp=35.0, p2_sp=10.0, p2_mp=6.0,
            p1_units=p1_units, p2_units=p2_units,
        ),
    )
    gs.suppress_warnings(True)
    return gs


# ---------------------------------------------------------------------------
# Tests
# ---------------------------------------------------------------------------

def test_empty_state_returns_at_least_one_plan(empty_state):
    plans = enumerate_candidates(empty_state)
    assert len(plans) >= 1, "empty state must always yield at least the no-op plan"
    # The no-op plan should be present.
    descs = [p.description for p in plans]
    assert any("idle" in d and "hold" in d for d in descs), (
        f"expected an idle+hold no-op plan in {descs[:5]}..."
    )


def test_plans_never_exceed_available_resources(empty_state):
    sp = float(empty_state.get_resource(0, 0))
    mp = float(empty_state.get_resource(1, 0))
    plans = enumerate_candidates(empty_state)
    for p in plans:
        assert p.sp_cost <= sp + 1e-6, (
            f"plan '{p.description}' SP cost {p.sp_cost} > available {sp}"
        )
        assert p.mp_cost <= mp + 1e-6, (
            f"plan '{p.description}' MP cost {p.mp_cost} > available {mp}"
        )


def test_plans_never_exceed_resources_midgame(midgame_state):
    sp = float(midgame_state.get_resource(0, 0))
    mp = float(midgame_state.get_resource(1, 0))
    plans = enumerate_candidates(midgame_state)
    for p in plans:
        assert p.sp_cost <= sp + 1e-6, p.description
        assert p.mp_cost <= mp + 1e-6, p.description


def test_mobile_spawns_only_on_legal_edge_tiles(midgame_state):
    plans = enumerate_candidates(midgame_state)
    for p in plans:
        for unit, loc, n in p.mobile_actions:
            tup = (int(loc[0]), int(loc[1]))
            assert tup in _ALL_EDGE_TILES_SET, (
                f"plan '{p.description}' spawns {unit} at {loc} (not an edge tile)"
            )
            assert n >= 1, p.description
            # Mobile units only:
            assert unit in (SCOUT, DEMOLISHER, INTERCEPTOR), (
                f"plan '{p.description}' has non-mobile unit {unit} in mobile_actions"
            )


def test_structure_actions_have_valid_action_types(midgame_state):
    plans = enumerate_candidates(midgame_state)
    valid_actions = {"spawn", "upgrade", "remove"}
    for p in plans:
        for action_type, unit, loc in p.structure_actions:
            assert action_type in valid_actions, (
                f"plan '{p.description}' has invalid action_type {action_type!r}"
            )
            assert unit in (WALL, SUPPORT, TURRET), (
                f"plan '{p.description}' has invalid structure unit {unit!r}"
            )
            assert _is_in_bottom_half(loc), (
                f"plan '{p.description}' targets enemy half at {loc}"
            )


def test_at_least_one_scout_rush_plan(midgame_state):
    plans = enumerate_candidates(midgame_state)
    has_scout_rush = False
    for p in plans:
        for unit, loc, n in p.mobile_actions:
            if unit == SCOUT and n >= 5:
                has_scout_rush = True
                break
        if has_scout_rush:
            break
    assert has_scout_rush, "expected at least one Scout-rush plan with N>=5"


def test_at_least_one_demolisher_plan(midgame_state):
    plans = enumerate_candidates(midgame_state)
    has_demo = any(
        any(unit == DEMOLISHER for unit, _, _ in p.mobile_actions)
        for p in plans
    )
    assert has_demo, "expected at least one plan with a Demolisher offense"


def test_at_least_one_interceptor_plan(midgame_state):
    plans = enumerate_candidates(midgame_state)
    has_intc = any(
        any(unit == INTERCEPTOR for unit, _, _ in p.mobile_actions)
        for p in plans
    )
    assert has_intc, "expected at least one plan with an Interceptor screen"


def test_total_plans_bounded_by_max_candidates(midgame_state):
    plans = enumerate_candidates(midgame_state, max_candidates=50)
    assert len(plans) <= 50

    plans2 = enumerate_candidates(midgame_state, max_candidates=10)
    assert len(plans2) <= 10


def test_default_cap_is_respected(midgame_state):
    plans = enumerate_candidates(midgame_state)  # use DEFAULT_MAX_CANDIDATES
    assert len(plans) <= DEFAULT_MAX_CANDIDATES


def test_apply_no_op_plan_does_nothing(empty_state):
    sp_before = float(empty_state.get_resource(0, 0))
    mp_before = float(empty_state.get_resource(1, 0))
    plan = ActionPlan(description="defense_idle+offense_hold")
    n_actions = apply_to_game_state(empty_state, plan)
    assert n_actions == 0
    assert float(empty_state.get_resource(0, 0)) == sp_before
    assert float(empty_state.get_resource(1, 0)) == mp_before


def test_apply_scout_rush_deducts_mp(empty_state):
    """A scout rush plan should consume MP via attempt_spawn."""
    plan = ActionPlan(
        mobile_actions=[(SCOUT, [13, 0], 1)],
        description="test_scout_one",
        mp_cost=1.0,
    )
    mp_before = float(empty_state.get_resource(1, 0))
    n_spawned = apply_to_game_state(empty_state, plan)
    mp_after = float(empty_state.get_resource(1, 0))
    assert n_spawned == 1
    assert mp_after == pytest.approx(mp_before - 1.0)


def test_recent_breaches_seed_defense(midgame_state):
    """Passing recent breaches should yield turret-at-breach plan options."""
    breaches = [[10, 13]]
    plans = enumerate_candidates(midgame_state, recent_breaches=breaches)
    has_breach_response = any(
        "breach_10" in p.description for p in plans
    )
    assert has_breach_response, (
        "expected at least one defense plan that responds to a (10, 13) breach"
    )


def test_recent_breaches_via_game_state_attribute(midgame_state):
    """If game_state has a `scored_on_locations` attribute the enumerator
    should use it automatically (without an explicit kwarg)."""
    midgame_state.scored_on_locations = [[10, 13]]
    plans = enumerate_candidates(midgame_state)
    has_breach_response = any(
        "breach_10" in p.description for p in plans
    )
    assert has_breach_response


def test_enumerator_handles_zero_resources(live_config):
    """0 SP / 0 MP — should still return only the no-op plan (or some
    upgrade/remove options that cost nothing and have no targets)."""
    gs = gamelib.GameState(
        live_config,
        _make_state_dict(p1_sp=0.0, p1_mp=0.0),
    )
    gs.suppress_warnings(True)
    plans = enumerate_candidates(gs)
    assert len(plans) >= 1
    for p in plans:
        assert p.sp_cost <= 1e-6
        assert p.mp_cost <= 1e-6


def test_enumerator_produces_reasonable_count_midgame(midgame_state):
    """Sanity check: midgame should produce many distinct plans."""
    plans = enumerate_candidates(midgame_state)
    # Lower bound — we should have well over 50 distinct plans midgame.
    assert len(plans) >= 50, (
        f"midgame produced only {len(plans)} plans; expected >= 50"
    )


def test_plans_have_unique_descriptions(midgame_state):
    """Descriptions are how we trace decisions in logs — make sure
    most are unique enough to be useful."""
    plans = enumerate_candidates(midgame_state)
    descs = [p.description for p in plans]
    unique = set(descs)
    # Allow small duplication but warn if it's pervasive.
    assert len(unique) >= 0.95 * len(descs), (
        f"only {len(unique)} unique descriptions among {len(descs)} plans"
    )


def test_action_plan_signature_is_hashable(midgame_state):
    plans = enumerate_candidates(midgame_state, max_candidates=20)
    sigs = {p.signature() for p in plans}
    assert len(sigs) >= 1


def test_apply_to_game_state_is_defensive_against_bad_locations(empty_state):
    """A plan with a bogus location shouldn't crash the algo — we want
    silent no-op behavior so a single bad enumerated plan can't kill us."""
    plan = ActionPlan(
        structure_actions=[("spawn", WALL, [99, 99])],  # out of bounds
        mobile_actions=[(SCOUT, [99, 99], 1)],          # not an edge
        description="bogus",
    )
    # Should NOT raise.
    apply_to_game_state(empty_state, plan)


def test_enumerator_reads_costs_from_live_config(empty_state):
    """If the live config says wall costs 1 SP, our budget filter should
    reflect that (8 SP -> we can afford 8 walls in any single plan slot)."""
    plans = enumerate_candidates(empty_state)
    # Look for any spawn plans that include a wall — they shouldn't exceed
    # the 8 SP budget.
    for p in plans:
        wall_count = sum(
            1 for at, u, _ in p.structure_actions
            if at == "spawn" and u == WALL
        )
        # Each wall costs 1 SP per the live config, so total wall_count
        # alone should be <= 8.
        assert wall_count <= 8, p.description


if __name__ == "__main__":
    pytest.main([__file__, "-v"])
