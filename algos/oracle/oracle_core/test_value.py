"""Unit tests for ``oracle_core.value`` — the search value function.

Run with::

    python -m pytest algos/oracle/oracle_core/test_value.py -v

Or as a script (no pytest required)::

    python algos/oracle/oracle_core/test_value.py
"""
from __future__ import annotations

import os
import sys
import time
from copy import deepcopy

# Make ``oracle_core`` importable as a package.
_HERE = os.path.dirname(os.path.abspath(__file__))
_ORACLE_ROOT = os.path.dirname(_HERE)
if _ORACLE_ROOT not in sys.path:
    sys.path.insert(0, _ORACLE_ROOT)

from oracle_core.value import (  # noqa: E402
    DEFAULT_WEIGHTS,
    evaluate,
    evaluate_diff,
)


# ---------------------------------------------------------------------------
# Fixtures
# ---------------------------------------------------------------------------

def _initial_state(turn: int = 0) -> dict:
    """Brand-new game state — both players at 40 HP / 8 SP / 1 MP, no units."""
    return {
        "turn": turn,
        "p1": {"hp": 40.0, "sp": 8.0, "mp": 1.0},
        "p2": {"hp": 40.0, "sp": 8.0, "mp": 1.0},
        "structures": [],
        "mobiles": [],
    }


def _struct(xy, type_idx, *, upgraded=False, hp=None, player=1, uid="0"):
    """Build a structure dict with sensible HP defaults."""
    if hp is None:
        # Pull max HP from the table embedded in value.py for convenience.
        defaults_base = {0: 60.0, 1: 1.0, 2: 60.0}
        defaults_up = {0: 200.0, 1: 40.0, 2: 100.0}
        hp = (defaults_up if upgraded else defaults_base).get(type_idx, 60.0)
    return {
        "xy": list(xy),
        "type_idx": type_idx,
        "upgraded": upgraded,
        "hp": hp,
        "uid": str(uid),
        "player": player,
        "turn_start_removal": None,
    }


def _mobile(xy, type_idx, *, hp=None, shield=0.0, player=1, uid="0",
            breached=False):
    """Build a mobile dict."""
    if hp is None:
        defaults = {3: 15.0, 4: 5.0, 5: 40.0}
        hp = defaults.get(type_idx, 15.0)
    return {
        "xy": list(xy),
        "type_idx": type_idx,
        "hp": hp,
        "shield": shield,
        "uid": str(uid),
        "player": player,
        "spawn_xy": list(xy),
        "target_edge": 3,
        "steps_taken": 0,
        "breached": breached,
        "finished_navigating": False,
        "reached_target": False,
    }


# ---------------------------------------------------------------------------
# Required tests
# ---------------------------------------------------------------------------

def test_initial_state_is_neutral():
    """Empty board, both at 40 HP — value should be near 0."""
    s = _initial_state()
    v = evaluate(s, my_player=1)
    # Income lookahead/banked-resource terms are symmetric so they cancel.
    # HP cancels (40-40). Structures cancel (none).
    assert abs(v) < 1e-6, f"expected ~0, got {v}"

    # Same from p2 perspective.
    v2 = evaluate(s, my_player=2)
    assert abs(v2) < 1e-6, f"expected ~0 from p2 view, got {v2}"


def test_hp_advantage_dominates():
    """+5 HP for us should add roughly 5 * HP_WEIGHT (=500) to utility."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    advantaged = deepcopy(base)
    advantaged["p1"]["hp"] = 45.0  # +5 HP for us
    adv_v = evaluate(advantaged, my_player=1)

    delta = adv_v - base_v
    # Expect roughly +500 (HP_WEIGHT * 5). Allow ±1 for any rounding.
    assert delta > 499.0, f"expected delta >= 500, got {delta:.2f}"
    assert delta < 501.0, f"expected delta <= 501, got {delta:.2f}"


def test_opponent_extra_turret_decreases_value():
    """A turret in the opp's half they don't have us → utility goes down."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    # Add an enemy (player 2) base Turret at a sensible mid-row spot.
    s = deepcopy(base)
    s["structures"].append(_struct((13, 16), 2, player=2, uid="100"))
    new_v = evaluate(s, my_player=1)

    assert new_v < base_v, (
        f"expected new value < base ({base_v:.2f}); got {new_v:.2f}"
    )


def test_evaluate_diff_sign_matches_direction():
    """evaluate_diff > 0 iff after-state is better for us."""
    before = _initial_state()
    after_better = deepcopy(before)
    after_better["p1"]["hp"] = 41.0  # we gained 1 HP — strictly better
    diff_pos = evaluate_diff(before, after_better, my_player=1)
    assert diff_pos > 0, f"expected positive diff for HP gain, got {diff_pos}"

    after_worse = deepcopy(before)
    after_worse["p1"]["hp"] = 39.0
    diff_neg = evaluate_diff(before, after_worse, my_player=1)
    assert diff_neg < 0, f"expected negative diff for HP loss, got {diff_neg}"


def test_zero_sum_symmetry():
    """evaluate(s, p=1) should be ≈ -evaluate(s, p=2) for any state.

    Both sides see the same board; the formula is anti-symmetric in
    self/opp swap, so flipping perspective should flip the sign.
    """
    s = _initial_state()
    # Asymmetric setup: we're winning.
    s["p1"]["hp"] = 35.0
    s["p2"]["hp"] = 25.0
    s["p1"]["sp"] = 12.0
    s["p2"]["sp"] = 6.0
    s["p1"]["mp"] = 3.0
    s["p2"]["mp"] = 1.5
    # Add some structures on each side.
    s["structures"] = [
        _struct((13, 12), 2, player=1, uid="1"),  # our turret
        _struct((13, 13), 0, player=1, uid="2"),  # our wall
        _struct((13, 15), 2, player=2, upgraded=True, uid="3"),  # their up turret
    ]
    # Add mobiles
    s["mobiles"] = [
        _mobile((10, 16), 3, player=1, hp=12, uid="m1"),  # our scout, in opp half
        _mobile((4, 12), 3, player=2, hp=10, uid="m2"),   # their scout, in our half
    ]
    v1 = evaluate(s, my_player=1)
    v2 = evaluate(s, my_player=2)
    assert abs(v1 + v2) < 1e-6, (
        f"expected v1 + v2 ≈ 0 (anti-symmetric), got v1={v1:.4f}, v2={v2:.4f}"
    )


def test_breach_pressure_signs():
    """Our mobile in opp half = positive; their mobile in our half = negative."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    # Our scout in their half.
    s_our = deepcopy(base)
    s_our["mobiles"].append(_mobile((10, 18), 3, player=1, hp=12, uid="m1"))
    v_our = evaluate(s_our, my_player=1)
    assert v_our > base_v, (
        f"our mobile in opp half should help us, base={base_v:.2f}, "
        f"new={v_our:.2f}"
    )

    # Their scout in our half.
    s_them = deepcopy(base)
    s_them["mobiles"].append(_mobile((10, 8), 3, player=2, hp=12, uid="m1"))
    v_them = evaluate(s_them, my_player=1)
    assert v_them < base_v, (
        f"their mobile in our half should hurt us, base={base_v:.2f}, "
        f"new={v_them:.2f}"
    )


def test_low_hp_mobiles_dont_count_pressure():
    """A mobile with HP <= 5 + shield 0 should not contribute to pressure
    (it'll likely die before breaching)."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    # Our 4-HP scout in their half — sub-threshold.
    s = deepcopy(base)
    s["mobiles"].append(_mobile((10, 18), 3, player=1, hp=4.0, uid="m1"))
    v = evaluate(s, my_player=1)
    assert abs(v - base_v) < 1e-6, (
        f"low-HP mobile should not move utility, base={base_v}, new={v}"
    )


def test_breached_mobiles_dont_count():
    """Mobiles that have already breached have left the arena and shouldn't
    contribute to pressure."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    s = deepcopy(base)
    s["mobiles"].append(_mobile(
        (10, 18), 3, player=1, hp=12, uid="m1", breached=True,
    ))
    v = evaluate(s, my_player=1)
    assert abs(v - base_v) < 1e-6, (
        f"breached mobile should not affect pressure, base={base_v}, new={v}"
    )


def test_structure_value_self_increases_utility():
    """Adding a structure on our side should raise utility."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    s = deepcopy(base)
    s["structures"].append(_struct((13, 11), 2, player=1, uid="t1"))
    v = evaluate(s, my_player=1)
    assert v > base_v, f"adding our turret should raise value, base={base_v}, new={v}"


def test_upgraded_structure_worth_more_than_base():
    """An upgraded turret should be worth strictly more than a base one."""
    base = _initial_state()

    s_base = deepcopy(base)
    s_base["structures"].append(_struct((13, 11), 2, player=1, uid="t1"))

    s_up = deepcopy(base)
    s_up["structures"].append(_struct((13, 11), 2, player=1, upgraded=True, uid="t1"))

    assert evaluate(s_up, my_player=1) > evaluate(s_base, my_player=1)


def test_damaged_structure_worth_less_than_full_hp():
    """A 30-HP turret should be worth less than a 60-HP turret."""
    base = _initial_state()

    s_full = deepcopy(base)
    s_full["structures"].append(_struct((13, 11), 2, player=1, hp=60.0, uid="t1"))

    s_half = deepcopy(base)
    s_half["structures"].append(_struct((13, 11), 2, player=1, hp=30.0, uid="t1"))

    assert evaluate(s_full, my_player=1) > evaluate(s_half, my_player=1)


def test_resource_advantage_helps():
    """Banked MP/SP for us → utility up."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    rich = deepcopy(base)
    rich["p1"]["sp"] = 20.0
    rich["p1"]["mp"] = 5.0
    v = evaluate(rich, my_player=1)
    assert v > base_v


def test_custom_weights_override():
    """Passing weights kwargs should change the output."""
    s = _initial_state()
    s["p1"]["hp"] = 50.0  # +10 HP advantage

    v_default = evaluate(s, my_player=1)
    v_double = evaluate(s, my_player=1, weights={**DEFAULT_WEIGHTS, "hp": 200.0})
    # Doubling HP weight should roughly double the HP-driven term.
    # HP delta = 10, so default contributes 1000; double-weighted 2000.
    assert v_double > v_default + 900


# ---------------------------------------------------------------------------
# Performance
# ---------------------------------------------------------------------------

def test_evaluate_is_fast():
    """evaluate() must run in < 1 ms per call (sim_search calls it ~10K
    times/turn). We measure the average over 5K iterations on a state
    with ~30 structures and 6 mobiles (typical mid-game)."""
    s = _initial_state(turn=20)
    s["p1"]["hp"] = 35.0
    s["p2"]["hp"] = 25.0
    s["p1"]["sp"] = 8.0
    s["p2"]["sp"] = 12.0
    s["p1"]["mp"] = 6.0
    s["p2"]["mp"] = 4.0
    # 30 structures (~mid-game density).
    structs = []
    uid = 0
    # 12 walls in a row
    for x in range(2, 14):
        structs.append(_struct((x, 12), 0, player=1, uid=str(uid)))
        uid += 1
    # 6 turrets along y=11
    for x in range(4, 10):
        structs.append(_struct((x, 11), 2, player=1, uid=str(uid)))
        uid += 1
    # 4 supports back row
    for x in range(11, 15):
        structs.append(_struct((x, 13), 1, player=1, upgraded=True, uid=str(uid)))
        uid += 1
    # opp side mirrors with 8 structs
    for x in range(2, 10):
        structs.append(_struct((x, 16), 2, player=2, uid=str(uid)))
        uid += 1
    s["structures"] = structs
    # 6 mobiles in flight
    s["mobiles"] = [
        _mobile((10 + i, 16 + i), 3, player=1, hp=12, uid=f"m{i}")
        for i in range(3)
    ] + [
        _mobile((10 + i, 8 - i), 3, player=2, hp=12, uid=f"m{i+10}")
        for i in range(3)
    ]

    n = 5000
    t0 = time.perf_counter()
    for _ in range(n):
        evaluate(s, my_player=1)
    elapsed = time.perf_counter() - t0
    per_call_us = (elapsed / n) * 1e6  # microseconds
    print(f"\n[bench] evaluate() avg={per_call_us:.2f} us/call (n={n})")
    # 1 ms = 1000 us — ample headroom.
    assert per_call_us < 1000, (
        f"evaluate() took {per_call_us:.2f} us/call, must be < 1000 us"
    )


# ---------------------------------------------------------------------------
# Sanity: composite scenario
# ---------------------------------------------------------------------------

def test_clearly_winning_state_beats_initial():
    """A state where we have +HP, +structures, +resources, mobiles in
    enemy half should score WAY higher than initial state."""
    base = _initial_state()
    base_v = evaluate(base, my_player=1)

    win = _initial_state(turn=15)
    win["p1"]["hp"] = 38.0
    win["p2"]["hp"] = 12.0
    win["p1"]["sp"] = 18.0
    win["p2"]["sp"] = 5.0
    win["p1"]["mp"] = 8.0
    win["p2"]["mp"] = 2.0
    # Strong defense
    win["structures"] = [
        _struct((13, 13), 1, player=1, upgraded=True, uid="s1"),
        _struct((12, 13), 1, player=1, upgraded=True, uid="s2"),
        _struct((13, 11), 2, player=1, upgraded=True, uid="t1"),
        _struct((10, 11), 2, player=1, upgraded=True, uid="t2"),
    ]
    win["mobiles"] = [
        _mobile((13, 18), 3, player=1, hp=12, shield=20, uid="m1"),
    ]
    win_v = evaluate(win, my_player=1)
    assert win_v > base_v + 2000, (
        f"clearly-winning state should be >> initial; "
        f"base={base_v:.2f} win={win_v:.2f}"
    )


# ---------------------------------------------------------------------------
# Script entry point (run without pytest)
# ---------------------------------------------------------------------------

if __name__ == "__main__":
    failed = 0
    passed = 0
    test_funcs = [v for k, v in sorted(globals().items())
                  if k.startswith("test_") and callable(v)]
    for fn in test_funcs:
        try:
            fn()
            print(f"PASS  {fn.__name__}")
            passed += 1
        except AssertionError as e:
            print(f"FAIL  {fn.__name__}: {e}")
            failed += 1
        except Exception as e:  # noqa: BLE001
            print(f"ERROR {fn.__name__}: {type(e).__name__}: {e}")
            failed += 1
    print(f"\n{passed} passed, {failed} failed")
    sys.exit(0 if failed == 0 else 1)
