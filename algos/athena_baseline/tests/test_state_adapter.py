"""Phase 5 milestone A — state adapter smoke tests.

These tests are pure-python (no sim_rs needed) and validate the schema of
``adapt_game_state``'s output. A second test verifies sim_rs can ingest
the adapter's output without crashing.
"""
from __future__ import annotations

import json
import sys
from pathlib import Path
from typing import Any, Dict, List, Optional
from unittest.mock import MagicMock

import pytest


REPO_ROOT = Path(__file__).resolve().parents[3]
WORKTREE_ROOT = REPO_ROOT
ATHENA_ROOT = WORKTREE_ROOT / "algos" / "athena_v3_planner"
if str(WORKTREE_ROOT) not in sys.path:
    sys.path.insert(0, str(WORKTREE_ROOT))


from algos.athena_v3_planner.offense.state_adapter import (  # noqa: E402
    adapt_game_state,
    augment_snapshot_for_simcore,
)


# ---------------------------------------------------------------------------
# Mock GameState helper
# ---------------------------------------------------------------------------

def _mock_unit(unit_type: str, *, x: int, y: int, player_index: int = 0,
               health: float = 60.0, upgraded: bool = False, unit_id: str = "1",
               stationary: bool = True):
    u = MagicMock()
    u.unit_type = unit_type
    u.player_index = player_index
    u.health = health
    u.upgraded = upgraded
    u.unit_id = unit_id
    u.stationary = stationary
    return u


def _make_game_state(*, my_hp=40.0, opp_hp=40.0, my_sp=8.0, opp_sp=8.0,
                     my_mp=1.0, opp_mp=1.0, units=None):
    """Build a MagicMock that mimics the gamelib.GameState surface
    used by the adapter."""
    units = units or []
    cell_units: Dict[tuple, list] = {}
    for u in units:
        # Pull (x, y) from the MagicMock; tests pass them through _mock_unit.
        # We attach them as `_xy` for indexing.
        xy = getattr(u, "_xy", None)
        if xy is None:
            continue
        cell_units.setdefault(tuple(xy), []).append(u)

    game_map = MagicMock()
    game_map.in_arena_bounds = lambda loc: True
    def _getitem(key):
        if isinstance(key, tuple):
            xy = key
        else:
            xy = tuple(key)
        return cell_units.get(xy, [])
    game_map.__getitem__ = lambda self_, key: _getitem(key)

    gs = MagicMock()
    gs.config = {
        "unitInformation": [
            {"shorthand": "FF", "startHealth": 60.0,
             "upgrade": {"startHealth": 200.0, "cost1": 2.0}},
            {"shorthand": "EF", "startHealth": 1.0,
             "upgrade": {"startHealth": 40.0, "cost1": 2.0}},
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
    gs.game_map = game_map
    gs.my_health = my_hp
    gs.enemy_health = opp_hp
    gs.turn_number = 0

    def _get_resource(idx, player):
        if idx == 0:  # SP
            return my_sp if player == 0 else opp_sp
        else:  # MP
            return my_mp if player == 0 else opp_mp
    gs.get_resource = _get_resource
    return gs


# ---------------------------------------------------------------------------
# Tests
# ---------------------------------------------------------------------------

def test_adapt_empty_state_schema():
    gs = _make_game_state()
    out = adapt_game_state(gs, my_player=1, turn=0)
    # Top-level keys
    assert set(out.keys()) >= {"turn", "p1", "p2", "structures", "mobiles"}
    # Resources mapped correctly
    assert out["p1"]["hp"] == 40.0
    assert out["p1"]["sp"] == 8.0
    assert out["p1"]["mp"] == 1.0
    assert out["structures"] == []
    assert out["mobiles"] == []


def test_adapt_with_structures():
    wall = _mock_unit("FF", x=10, y=11, player_index=0, health=60.0)
    wall._xy = [10, 11]
    turret = _mock_unit("DF", x=10, y=12, player_index=0, health=60.0)
    turret._xy = [10, 12]
    gs = _make_game_state(units=[wall, turret])
    out = adapt_game_state(gs, my_player=1, turn=3)
    assert out["turn"] == 3
    types = sorted(s["type_idx"] for s in out["structures"])
    assert types == [0, 2]
    # Player conversion: gamelib idx 0 = self -> sim p1 (when my_player=1)
    assert all(s["player"] == 1 for s in out["structures"])
    # UIDs are present and unique
    uids = [s["uid"] for s in out["structures"]]
    assert len(uids) == len(set(uids))


def test_adapt_with_opponent_structures():
    opp_wall = _mock_unit("FF", x=10, y=16, player_index=1, health=60.0,
                          unit_id="2")
    opp_wall._xy = [10, 16]
    gs = _make_game_state(units=[opp_wall])
    out = adapt_game_state(gs, my_player=1)
    assert len(out["structures"]) == 1
    # gamelib idx 1 = opp -> sim p2 (when my_player=1)
    assert out["structures"][0]["player"] == 2


def test_adapt_with_mobile():
    scout = _mock_unit("PI", x=13, y=0, player_index=0, health=15.0,
                       stationary=False)
    scout._xy = [13, 0]
    gs = _make_game_state(units=[scout])
    out = adapt_game_state(gs, my_player=1)
    assert len(out["mobiles"]) == 1
    m = out["mobiles"][0]
    assert m["type_idx"] == 3
    assert m["player"] == 1
    # Required mobile fields all present
    for k in ("xy", "type_idx", "hp", "shield", "uid", "player", "spawn_xy",
              "target_edge", "steps_taken", "move_buildup", "last_move",
              "finished_navigating", "reached_target", "breached"):
        assert k in m, f"mobile missing key {k}"


def test_augment_snapshot_idempotent(tmp_path):
    snap = tmp_path / "config.json"
    base = {
        "unitInformation": [{"shorthand": "FF"}],
        "resources": {"bitsPerRound": 1.0},
    }
    snap.write_text(json.dumps(base))

    # First call should add SimCore keys.
    changed1 = augment_snapshot_for_simcore(str(snap))
    assert changed1
    after = json.loads(snap.read_text())
    assert "_raw_unit_information" in after
    assert "_resources_block_verbatim" in after

    # Second call should be a no-op.
    changed2 = augment_snapshot_for_simcore(str(snap))
    assert not changed2


def test_augment_snapshot_missing_file(tmp_path):
    nope = tmp_path / "nonexistent.json"
    assert not augment_snapshot_for_simcore(str(nope))


# ---------------------------------------------------------------------------
# Optional sim_rs integration smoke (skipped if wheel absent)
# ---------------------------------------------------------------------------

def test_sim_rs_round_trip_smoke():
    """End-to-end: real snapshot path + adapter output -> sim_rs accepts it."""
    try:
        import sim_rs  # type: ignore  # noqa: F401
    except ImportError:
        pytest.skip("sim_rs PyO3 wheel not installed")

    cfg_path = ATHENA_ROOT / "data" / "citadel_config_snapshot.json"
    assert cfg_path.exists(), "v3_planner snapshot missing"
    # Idempotency: ensure SimCore keys are present (Phase 5 milestone A fix).
    with cfg_path.open() as f:
        data = json.load(f)
    assert "_raw_unit_information" in data
    assert "_resources_block_verbatim" in data

    # Build a minimal but well-formed state dict via the adapter.
    gs = _make_game_state()
    state = adapt_game_state(gs, my_player=1, turn=0)
    # The adapter's output should be ingestible by sim_rs.
    import sim_rs  # type: ignore
    post = sim_rs.simulate_action_phase_py(state, str(cfg_path))
    # No crash; expected fields present.
    assert "p1" in post and "hp" in post["p1"]
    assert "p2" in post and "hp" in post["p2"]
    # No mobiles -> no breaches -> HP unchanged.
    assert post["p1"]["hp"] == 40.0
    assert post["p2"]["hp"] == 40.0
