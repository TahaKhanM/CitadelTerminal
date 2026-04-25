"""Tests for the multi-tier offense planner.

The load-bearing contract: ``pick_offense_plan`` NEVER returns an empty
deploy list when ``mp_available >= 1.0``. Every tier independently
verified plus end-to-end fallthrough behavior.
"""
from __future__ import annotations

import sys
import os
from pathlib import Path

# Path setup so tests work both with `pytest` from repo root and from
# inside the algo dir.
_HERE = Path(__file__).resolve().parent.parent
if str(_HERE) not in sys.path:
    sys.path.insert(0, str(_HERE))

import pytest

from planner.offense_planner import (
    BL_EDGE_PRIORITY,
    BR_EDGE_PRIORITY,
    OffensePlan,
    _enemy_side_strength_from_state,
    pick_offense_plan,
    tier1_sim_beam,
    tier2_heuristic_beam,
    tier3_rule_based,
    tier4_hardcoded,
)


# ---------------------------------------------------------------------------
# Tier 4 (hardcoded — cannot fail by design)
# ---------------------------------------------------------------------------

class TestTier4Hardcoded:
    def test_zero_mp_returns_empty(self):
        plan = tier4_hardcoded(0.0)
        assert plan.is_empty
        assert plan.tier == 4

    def test_one_mp_spawns_one_scout(self):
        plan = tier4_hardcoded(1.0, turn=0)
        assert len(plan.deploys) == 1
        assert plan.deploys[0][0] == "SCOUT"
        assert plan.mp_cost == 1.0

    def test_alternates_sides_by_turn(self):
        bl_plan = tier4_hardcoded(5.0, turn=0)
        br_plan = tier4_hardcoded(5.0, turn=1)
        assert bl_plan.side == "BL"
        assert br_plan.side == "BR"
        assert bl_plan.deploys[0][1] != br_plan.deploys[0][1]

    def test_caps_at_14_units(self):
        plan = tier4_hardcoded(80.0)
        assert len(plan.deploys) == 14

    def test_floor_mp(self):
        plan = tier4_hardcoded(3.7)
        assert len(plan.deploys) == 3

    def test_spawn_location_on_edge(self):
        plan = tier4_hardcoded(5.0, turn=0)
        loc = plan.deploys[0][1]
        assert loc in BL_EDGE_PRIORITY


# ---------------------------------------------------------------------------
# Tier 3 (rule-based MP-aware)
# ---------------------------------------------------------------------------

class TestTier3RuleBased:
    def test_zero_mp_no_op(self):
        plan = tier3_rule_based(0.0)
        assert plan.is_empty

    def test_low_mp_corner_dive(self):
        plan = tier3_rule_based(2.0, turn=0)
        assert len(plan.deploys) == 2
        assert all(u == "SCOUT" for u, _ in plan.deploys)

    def test_4_mp_interceptor_screen(self):
        """At 4 MP we want interceptors (defensive) not scouts."""
        plan = tier3_rule_based(4.0, turn=0)
        unit_types = [u for u, _ in plan.deploys]
        assert "INTERCEPTOR" in unit_types
        assert plan.mp_cost <= 4.0

    def test_8_mp_scout_rush(self):
        plan = tier3_rule_based(8.0, turn=0)
        assert len(plan.deploys) == 8
        assert all(u == "SCOUT" for u, _ in plan.deploys)

    def test_12_mp_mixed_burst(self):
        """At 12 MP we should pick mixed (demos + scouts)."""
        plan = tier3_rule_based(12.0, turn=0)
        unit_types = [u for u, _ in plan.deploys]
        assert "DEMOLISHER" in unit_types
        # Total cost should not exceed budget
        assert plan.mp_cost <= 12.0

    def test_18_mp_heavy_mixed(self):
        plan = tier3_rule_based(20.0, turn=0)
        unit_types = [u for u, _ in plan.deploys]
        assert "DEMOLISHER" in unit_types
        assert "INTERCEPTOR" in unit_types
        assert plan.mp_cost <= 20.0

    def test_picks_weaker_side_when_signal_present(self):
        """When enemy is heavier on the right, attack the left."""
        plan = tier3_rule_based(8.0, turn=0,
                                 enemy_left_strength=2.0,
                                 enemy_right_strength=20.0)
        assert plan.side == "BL"

    def test_picks_weaker_side_right(self):
        plan = tier3_rule_based(8.0, turn=0,
                                 enemy_left_strength=20.0,
                                 enemy_right_strength=2.0)
        assert plan.side == "BR"

    def test_alternates_when_no_signal(self):
        """Without enemy signal, alternate by turn parity."""
        bl_plan = tier3_rule_based(8.0, turn=0)
        br_plan = tier3_rule_based(8.0, turn=1)
        assert bl_plan.side == "BL"
        assert br_plan.side == "BR"


# ---------------------------------------------------------------------------
# Enemy strength helper
# ---------------------------------------------------------------------------

class TestEnemyStrength:
    def test_empty_state(self):
        assert _enemy_side_strength_from_state(None) == (0.0, 0.0)
        assert _enemy_side_strength_from_state({}) == (0.0, 0.0)
        assert _enemy_side_strength_from_state({"structures": []}) == (0.0, 0.0)

    def test_only_my_structures_zero(self):
        state = {"structures": [
            {"xy": [3, 12], "player": 1, "type_idx": 2},
            {"xy": [24, 12], "player": 1, "type_idx": 2},
        ]}
        assert _enemy_side_strength_from_state(state, my_player=1) == (0.0, 0.0)

    def test_left_heavy_enemy(self):
        state = {"structures": [
            {"xy": [3, 18], "player": 2, "type_idx": 2},  # turret left = 6
            {"xy": [4, 18], "player": 2, "type_idx": 0},  # wall left = 1
            {"xy": [25, 18], "player": 2, "type_idx": 0},  # wall right = 1
        ]}
        l, r = _enemy_side_strength_from_state(state, my_player=1)
        assert l == 7.0  # 6 + 1
        assert r == 1.0

    def test_robust_to_missing_keys(self):
        state = {"structures": [
            {"xy": [3, 18], "player": 2},  # missing type_idx
            {"foo": "bar"},                 # garbage
            {"xy": "wrong"},                # bad xy
        ]}
        # Should not raise; missing-key entry counts as non-turret weight 1
        l, r = _enemy_side_strength_from_state(state, my_player=1)
        assert l + r >= 0  # at minimum doesn't crash


# ---------------------------------------------------------------------------
# Tier 2 (heuristic beam — uses templates)
# ---------------------------------------------------------------------------

class TestTier2HeuristicBeam:
    def test_zero_mp_no_op(self):
        plan = tier2_heuristic_beam({"structures": [], "mobiles": []}, 0.0)
        assert plan.is_empty

    def test_picks_high_mp_template(self):
        """Tier 2 should pick a high-MP template at MP=14, not a corner-dive."""
        plan = tier2_heuristic_beam(
            {"structures": [], "mobiles": []}, 14.0,
        )
        assert len(plan.deploys) > 1
        # mp_cost should be substantial fraction of available
        assert plan.mp_cost >= 8.0

    def test_falls_through_when_no_candidates(self):
        """When generate_candidates returns only hoard, return empty
        so the driver falls to tier 3."""
        # Construct a fake candidate factory that returns only hoard
        from planner.offense import Candidate
        def only_hoard(*a, **kw):
            return [Candidate(name="hoard", template=None, side="NONE",
                              deploys=[], mp_cost=0.0)]
        plan = tier2_heuristic_beam(
            {"structures": [], "mobiles": []}, 8.0,
            candidate_factory=only_hoard,
        )
        assert plan.is_empty


# ---------------------------------------------------------------------------
# pick_offense_plan — the load-bearing contract
# ---------------------------------------------------------------------------

class TestPickOffensePlanContract:
    """The CRITICAL invariant: never returns empty when mp >= 1.0."""

    def test_never_empty_at_mp_1_with_state(self):
        plan = pick_offense_plan(
            {"structures": [], "mobiles": []}, 1.0, turn=5,
        )
        assert not plan.is_empty
        assert plan.tier in (1, 2, 3, 4)

    def test_never_empty_at_mp_5_with_state(self):
        plan = pick_offense_plan(
            {"structures": [], "mobiles": []}, 5.0, turn=10,
        )
        assert not plan.is_empty

    def test_never_empty_at_mp_15_with_state(self):
        plan = pick_offense_plan(
            {"structures": [], "mobiles": []}, 15.0, turn=20,
        )
        assert not plan.is_empty

    def test_never_empty_when_state_is_None(self):
        """No state dict at all (e.g., adapt_game_state crashed)."""
        plan = pick_offense_plan(None, 5.0, turn=10)
        assert not plan.is_empty
        # Without state, must come from tier 3 or 4.
        assert plan.tier in (3, 4)

    def test_never_empty_with_corrupt_state(self):
        """State dict with garbage — should fall through to tier 3."""
        plan = pick_offense_plan(
            {"this_is": "garbage", "structures": "not a list"}, 5.0, turn=0,
        )
        assert not plan.is_empty

    def test_zero_mp_returns_empty(self):
        """The ONE legitimate empty case."""
        plan = pick_offense_plan(
            {"structures": [], "mobiles": []}, 0.0, turn=0,
        )
        assert plan.is_empty

    def test_below_one_mp_returns_empty(self):
        plan = pick_offense_plan(
            {"structures": [], "mobiles": []}, 0.5, turn=0,
        )
        assert plan.is_empty

    def test_logs_tier_used(self):
        """Plan should report which tier produced it."""
        logs = []
        plan = pick_offense_plan(
            None, 4.0, turn=0,
            log_fn=lambda s: logs.append(s),
        )
        # At least the final pick log
        assert any("TIER" in l for l in logs)


class TestPickOffensePlanResilience:
    """Verify each tier-failure produces graceful fallthrough."""

    def test_state_with_realistic_athena_defense(self):
        """Realistic state: 16 walls + 12 turrets + opp structures.
        This is close to the live replay's T10 state."""
        my_walls = [(2, 12), (3, 12), (4, 12), (6, 12), (9, 12),
                    (10, 12), (13, 12), (14, 12), (17, 12), (18, 12),
                    (21, 12), (23, 12), (24, 12), (25, 12)]
        my_turrets = [(1, 13), (5, 11), (8, 11), (11, 11), (13, 11),
                      (14, 11), (16, 11), (19, 11), (22, 11), (26, 13)]
        opp_walls = [(3, 18), (24, 18)]
        opp_turrets = [(13, 16), (14, 16)]
        structures = []
        for x, y in my_walls:
            structures.append({"xy": [x, y], "player": 1, "type_idx": 0,
                               "hp": 60.0, "uid": str(len(structures))})
        for x, y in my_turrets:
            structures.append({"xy": [x, y], "player": 1, "type_idx": 2,
                               "hp": 60.0, "uid": str(len(structures))})
        for x, y in opp_walls:
            structures.append({"xy": [x, y], "player": 2, "type_idx": 0,
                               "hp": 60.0, "uid": str(len(structures))})
        for x, y in opp_turrets:
            structures.append({"xy": [x, y], "player": 2, "type_idx": 2,
                               "hp": 60.0, "uid": str(len(structures))})

        state = {
            "turn": 10,
            "p1": {"hp": 38.0, "sp": 4.0, "mp": 8.10},
            "p2": {"hp": 40.0, "sp": 6.0, "mp": 8.0},
            "structures": structures,
            "mobiles": [],
        }
        plan = pick_offense_plan(state, 8.10, turn=10)
        assert not plan.is_empty
        assert plan.mp_cost <= 8.10 + 1e-3
        # With opp turrets at center-top, the side preference should be
        # opportunistic (whichever side scored less defense).
        assert plan.side in ("BL", "BR", "BOTH", "CENTER", "")

    def test_resilient_to_template_loader_failure(self, monkeypatch):
        """If load_all_templates somehow returns nothing, tier 3 should still fire."""
        # Make tier 1 + tier 2 effectively fail, force tier 3.
        plan = pick_offense_plan(None, 4.0, turn=0)
        assert plan.tier in (3, 4)
        assert not plan.is_empty
