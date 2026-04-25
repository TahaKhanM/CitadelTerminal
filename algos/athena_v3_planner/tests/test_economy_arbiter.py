"""Phase 5 milestone B — EconomyArbiter smoke tests.

Lightweight tests that exercise the arbiter's wiring (defense + offense
phases run, exceptions are caught, watchdog can fire) without spinning
up the engine.
"""
from __future__ import annotations

import sys
from pathlib import Path
from typing import Any, Dict, List
from unittest.mock import MagicMock

import pytest


WORKTREE_ROOT = Path(__file__).resolve().parents[3]
ATHENA_ROOT = WORKTREE_ROOT / "algos" / "athena_v3_planner"
if str(WORKTREE_ROOT) not in sys.path:
    sys.path.insert(0, str(WORKTREE_ROOT))


from algos.athena_v3_planner.planner.budget import BudgetExceeded, Watchdog  # noqa: E402
from algos.athena_v3_planner.planner.economy import EconomyArbiter  # noqa: E402


# ---------------------------------------------------------------------------
# GameState fixture
# ---------------------------------------------------------------------------

def _build_min_config() -> Dict[str, Any]:
    return {
        "unitInformation": [
            {"shorthand": "FF", "startHealth": 60.0,
             "upgrade": {"startHealth": 200.0, "cost1": 2.0}},
            {"shorthand": "EF", "startHealth": 1.0,
             "upgrade": {"startHealth": 40.0, "cost1": 2.0,
                         "shieldRange": 7.0, "shieldPerUnit": 1.0,
                         "shieldBonusPerY": 0.7}},
            {"shorthand": "DF", "startHealth": 60.0, "attackRange": 2.5,
             "attackDamageWalker": 6.0,
             "upgrade": {"startHealth": 100.0, "attackRange": 3.5,
                         "attackDamageWalker": 20.0, "cost1": 4.0}},
            {"shorthand": "PI", "startHealth": 15.0, "speed": 1.0,
             "attackDamageWalker": 2.0, "attackRange": 3.5},
            {"shorthand": "EI", "startHealth": 5.0, "speed": 0.5,
             "attackDamageWalker": 8.0, "attackRange": 4.5},
            {"shorthand": "SI", "startHealth": 40.0, "speed": 0.25,
             "attackDamageWalker": 20.0, "attackRange": 4.5},
        ],
        "resources": {
            "bitsPerRound": 1.0, "coresPerRound": 4.0,
            "startingHP": 40.0, "maxBits": 150.0,
            "bitDecayPerRound": 0.25, "startingCores": 8.0,
            "startingBits": 1.0, "turnIntervalForBitSchedule": 5,
            "bitGrowthRate": 1.0, "bitRampBitCapGrowthRate": 5.0,
            "roundStartBitRamp": 5,
        },
    }


def _make_game_state(*, my_sp=8.0, my_mp=5.0, opp_sp=8.0, opp_mp=5.0):
    """Build a MagicMock that supports the arbiter's calls without crashing."""
    cfg = _build_min_config()

    game_map = MagicMock()
    game_map.in_arena_bounds = lambda loc: True
    game_map.__getitem__ = lambda self_, key: []

    gs = MagicMock()
    gs.config = cfg
    gs.game_map = game_map
    gs.my_health = 40.0
    gs.enemy_health = 40.0
    gs.turn_number = 0
    gs.contains_stationary_unit = lambda loc: False
    gs.attempt_spawn = lambda *a, **kw: 1   # pretend every spawn succeeds
    gs.attempt_upgrade = lambda *a, **kw: 0
    gs.attempt_remove = lambda *a, **kw: 0
    gs.type_cost = lambda sh, upgrade=False: (
        [4.0, 0.0] if upgrade else [2.0, 0.0]
    )
    gs.game_map.add_unit = lambda *a, **kw: None
    gs.game_map.remove_unit = lambda *a, **kw: None
    gs.submit_turn = lambda: None
    gs._player_resources = [
        {"cores": my_sp, "bits": my_mp, "health": 40.0, "time": 0},
        {"cores": opp_sp, "bits": opp_mp, "health": 40.0, "time": 0},
    ]

    def _get_resource(idx, player):
        if idx == 0:  # SP
            return my_sp if player == 0 else opp_sp
        return my_mp if player == 0 else opp_mp
    gs.get_resource = _get_resource

    # turn_count attr the trackers might read
    gs.turn_count = 0
    return gs


# ---------------------------------------------------------------------------
# Tests
# ---------------------------------------------------------------------------

def test_arbiter_constructs_with_minimal_args():
    cfg = _build_min_config()
    arb = EconomyArbiter(
        config=cfg,
        archetype_path=str(ATHENA_ROOT / "defenses" / "v_funnel.json"),
        snapshot_path=str(ATHENA_ROOT / "data" / "citadel_config_snapshot.json"),
    )
    assert arb.turn_count == 0
    assert arb.watchdog is not None


def test_arbiter_skips_offense_when_low_mp():
    cfg = _build_min_config()
    captured = []
    arb = EconomyArbiter(
        config=cfg,
        archetype_path=str(ATHENA_ROOT / "defenses" / "v_funnel.json"),
        snapshot_path=str(ATHENA_ROOT / "data" / "citadel_config_snapshot.json"),
        debug_log_func=captured.append,
        offense_min_mp=1.0,
    )
    gs = _make_game_state(my_mp=0.0)  # below floor
    arb.execute(gs)
    # Should have logged something about defense at most; no offense pick.
    pick_msgs = [m for m in captured if "pick=" in m]
    assert pick_msgs == []


def test_arbiter_runs_one_turn_without_crash():
    cfg = _build_min_config()
    captured = []
    arb = EconomyArbiter(
        config=cfg,
        archetype_path=str(ATHENA_ROOT / "defenses" / "v_funnel.json"),
        snapshot_path=str(ATHENA_ROOT / "data" / "citadel_config_snapshot.json"),
        debug_log_func=captured.append,
    )
    gs = _make_game_state(my_sp=20.0, my_mp=8.0)
    # No exception should propagate.
    arb.execute(gs)
    assert arb.turn_count == 1


def test_arbiter_watchdog_total_budget_propagates_budget_exception():
    cfg = _build_min_config()
    # Watchdog with a tiny total budget; once execute() starts, the
    # post-defense check should raise.
    wd = Watchdog(total_budget_ms=1.0)
    arb = EconomyArbiter(
        config=cfg,
        archetype_path=str(ATHENA_ROOT / "defenses" / "v_funnel.json"),
        snapshot_path=str(ATHENA_ROOT / "data" / "citadel_config_snapshot.json"),
        watchdog=wd,
    )
    gs = _make_game_state(my_sp=20.0, my_mp=8.0)
    # tiny budget -> a check() call should raise BudgetExceeded which
    # the arbiter re-raises.
    import time
    time.sleep(0.005)  # ensure 1ms elapsed
    with pytest.raises(BudgetExceeded):
        arb.execute(gs)


def test_arbiter_swallows_component_exceptions():
    """A defense primitive that raises shouldn't crash the arbiter."""
    cfg = _build_min_config()
    captured = []
    arb = EconomyArbiter(
        config=cfg,
        archetype_path="/nonexistent/path.json",  # will trigger build_default_defences exception
        snapshot_path=str(ATHENA_ROOT / "data" / "citadel_config_snapshot.json"),
        debug_log_func=captured.append,
    )
    gs = _make_game_state(my_sp=8.0, my_mp=5.0)
    # Non-existent archetype causes build_default_defences to throw, but
    # the arbiter swallows it and continues with the rest of the pipeline.
    arb.execute(gs)
    # We should see a logged failure for build_default_defences.
    bd_msgs = [m for m in captured if "build_default_defences" in m]
    assert bd_msgs, "expected build_default_defences soft-fail log"
