"""Watchdog / Budget manager tests (Phase 4 task 5)."""
from __future__ import annotations

import time

import pytest

from algos.athena_v3_planner.planner.budget import (
    BudgetExceeded,
    DEFAULT_COMPONENT_CAPS_MS,
    DEFAULT_TOTAL_BUDGET_MS,
    Watchdog,
)


def test_default_caps_match_spec():
    """Spec: SimCore setup 200, Opp posterior 500, Defense 1.5s, Offense 8s,
    MAP-Elites 100, GC reserve 4s. Total 13s."""
    assert DEFAULT_COMPONENT_CAPS_MS["SimCore setup"] == 200
    assert DEFAULT_COMPONENT_CAPS_MS["Opponent posterior"] == 500
    assert DEFAULT_COMPONENT_CAPS_MS["DefensePlanner"] == 1500
    assert DEFAULT_COMPONENT_CAPS_MS["OffensePlanner"] == 8000
    assert DEFAULT_COMPONENT_CAPS_MS["MAP-Elites"] == 100
    assert DEFAULT_COMPONENT_CAPS_MS["GC reserve"] == 4000
    assert DEFAULT_TOTAL_BUDGET_MS == 13000


def test_start_turn_resets_clock():
    wd = Watchdog()
    wd.start_turn()
    e1 = wd.total_elapsed_ms()
    time.sleep(0.01)
    wd.start_turn()
    e2 = wd.total_elapsed_ms()
    # After reset, e2 should be tiny relative to elapsed
    assert e2 < 5.0


def test_remaining_ms_decreases_monotonically():
    wd = Watchdog(total_budget_ms=500.0)
    wd.start_turn()
    r1 = wd.remaining_ms()
    time.sleep(0.05)
    r2 = wd.remaining_ms()
    assert r1 > r2
    assert r2 > 0


def test_check_total_budget_exceeded_raises():
    wd = Watchdog(total_budget_ms=10.0)
    wd.start_turn()
    time.sleep(0.02)
    with pytest.raises(BudgetExceeded):
        wd.check("any-component")


def test_check_per_component_cap_raises():
    wd = Watchdog(
        total_budget_ms=10000.0,
        component_caps_ms={"OffensePlanner": 30.0},
    )
    wd.start_turn()
    wd.begin("OffensePlanner")
    time.sleep(0.05)
    with pytest.raises(BudgetExceeded) as excinfo:
        wd.check("OffensePlanner")
    assert excinfo.value.component == "OffensePlanner"


def test_check_unknown_component_falls_through_to_total():
    wd = Watchdog(total_budget_ms=1000.0)
    wd.start_turn()
    # No cap for "MysteryComponent"; should NOT raise as long as total < 1s
    wd.check("MysteryComponent")


def test_force_fallback_always_raises():
    wd = Watchdog(total_budget_ms=10000.0)
    wd.start_turn()
    with pytest.raises(BudgetExceeded) as excinfo:
        wd.force_fallback()
    assert excinfo.value.component == "TOTAL"


def test_component_elapsed_uses_begin_marker():
    wd = Watchdog(total_budget_ms=10000.0)
    wd.start_turn()
    time.sleep(0.05)
    wd.begin("OffensePlanner")
    time.sleep(0.02)
    e = wd.component_elapsed_ms("OffensePlanner")
    # Should be ~20ms (since `begin`), NOT ~70ms (since start_turn)
    assert e < 50.0
