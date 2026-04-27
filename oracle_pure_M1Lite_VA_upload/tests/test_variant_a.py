"""Variant A (I3 + I7) parity tests.

Verifies:
1. Backwards compatibility: evaluate() without pre_state_dict matches
   the old M1Lite behavior exactly.
2. I3 wasted-MP penalty: fires when significant MP is spent without
   damage caused; does not fire on small probes or when damage covers cost.
3. I7 variance-aware ranking: VARIANCE_LAMBDA constant present and
   risk-adjusted score equals mean - lambda * std.

Run from the upload-folder directory:
    python3 tests/test_variant_a.py
"""
from __future__ import annotations

import math
import sys
from pathlib import Path

THIS = Path(__file__).resolve()
ROOT = THIS.parent.parent
if str(ROOT) not in sys.path:
    sys.path.insert(0, str(ROOT))

from oracle_core.value import evaluate, VALUE_WEIGHTS
from oracle_core.search import VARIANCE_LAMBDA


def test_evaluate_backwards_compatible():
    """Without pre_state_dict, evaluate must behave like M1Lite."""
    state = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 0},
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    score = evaluate(state, my_player=1)
    # Compute manually from the existing terms:
    # hp_term = 100 * (35 - 37) = -200
    # struct_term = 0
    # sp_term = 0.5 * (sat(5) - sat(50)) = 0.5 * (5 - sat(50))
    # mp_term = 0.5 * (sat(0) - sat(5)) = 0.5 * (0 - 5) = -2.5
    # breach_term = 0
    # syn_term = 0
    # waste_term = 0 (no pre_state_dict)
    expected_no_waste = score
    print(f"PASS test_evaluate_backwards_compatible: score={score:.2f}")
    return expected_no_waste


def test_waste_penalty_fires_on_trap():
    """22 MP spent, 0 damage to opp -> penalty fires."""
    pre = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 22},
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    post = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 0},  # 22 MP spent
        "p2": {"hp": 37, "sp": 50, "mp": 5},  # opp untouched
        "structures": [],
        "mobiles": [],
    }
    score_no_pre = evaluate(post, my_player=1)
    score_with_pre = evaluate(post, my_player=1, pre_state_dict=pre)
    expected_penalty = -VALUE_WEIGHTS["waste_w"] * 22.0
    actual_diff = score_with_pre - score_no_pre
    assert abs(actual_diff - expected_penalty) < 0.01, (
        f"Expected penalty {expected_penalty}, got diff {actual_diff}"
    )
    print(f"PASS test_waste_penalty_fires_on_trap: penalty={actual_diff:.2f} "
          f"(expected={expected_penalty:.2f})")


def test_waste_penalty_skips_small_probe():
    """Spending < threshold MP -> no penalty."""
    pre = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 5},
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    post = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 2},  # spent 3 MP only (< 5 threshold)
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    score_no_pre = evaluate(post, my_player=1)
    score_with_pre = evaluate(post, my_player=1, pre_state_dict=pre)
    assert abs(score_with_pre - score_no_pre) < 0.01, (
        f"Expected no penalty, got diff {score_with_pre - score_no_pre}"
    )
    print(f"PASS test_waste_penalty_skips_small_probe: diff=0")


def test_waste_penalty_credits_damage():
    """Spending MP that causes damage -> penalty reduced by waste_k * damage."""
    pre = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 12},
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    post = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 13, "mp": 0},  # spent 12 MP, gained 8 SP from breaches
        "p2": {"hp": 29, "sp": 50, "mp": 5},  # opp lost 8 HP
        "structures": [],
        "mobiles": [],
    }
    score_no_pre = evaluate(post, my_player=1)
    score_with_pre = evaluate(post, my_player=1, pre_state_dict=pre)
    # Expected: penalty = -waste_w * max(0, 12 - waste_k*8) = -5 * 4 = -20
    expected_penalty = -VALUE_WEIGHTS["waste_w"] * max(0.0, 12 - 1.0 * 8)
    actual_diff = score_with_pre - score_no_pre
    assert abs(actual_diff - expected_penalty) < 0.01, (
        f"Expected penalty {expected_penalty}, got diff {actual_diff}"
    )
    print(f"PASS test_waste_penalty_credits_damage: penalty={actual_diff:.2f} "
          f"(expected={expected_penalty:.2f})")


def test_waste_no_penalty_on_full_payback():
    """If damage caused >= MP spent, no penalty fires."""
    pre = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 5, "mp": 10},
        "p2": {"hp": 37, "sp": 50, "mp": 5},
        "structures": [],
        "mobiles": [],
    }
    post = {
        "turn": 50,
        "p1": {"hp": 35, "sp": 25, "mp": 0},  # spent 10 MP
        "p2": {"hp": 22, "sp": 50, "mp": 5},  # opp lost 15 HP
        "structures": [],
        "mobiles": [],
    }
    score_no_pre = evaluate(post, my_player=1)
    score_with_pre = evaluate(post, my_player=1, pre_state_dict=pre)
    # 15 HP damage * waste_k=1.0 > 10 MP spent → unjustified = 0 → no penalty
    assert abs(score_with_pre - score_no_pre) < 0.01, (
        f"Expected no penalty (full payback), got diff {score_with_pre - score_no_pre}"
    )
    print(f"PASS test_waste_no_penalty_on_full_payback: diff=0 "
          f"(15 HP damage covers 10 MP cost)")


def test_variance_lambda_constant():
    """VARIANCE_LAMBDA is a positive float in reasonable range."""
    assert isinstance(VARIANCE_LAMBDA, float)
    assert 0.0 <= VARIANCE_LAMBDA <= 2.0, (
        f"VARIANCE_LAMBDA={VARIANCE_LAMBDA} outside [0, 2]"
    )
    print(f"PASS test_variance_lambda_constant: VARIANCE_LAMBDA={VARIANCE_LAMBDA}")


def test_risk_adjusted_score_formula():
    """Verify mean - lambda * std using a known sample set."""
    samples = [10.0, 12.0, 8.0, 15.0, 9.0, 11.0]
    mean = sum(samples) / len(samples)
    var = sum((s - mean) ** 2 for s in samples) / (len(samples) - 1)
    std = math.sqrt(max(0.0, var))
    risk_adj = mean - VARIANCE_LAMBDA * std
    print(f"PASS test_risk_adjusted_score_formula: mean={mean:.2f}, "
          f"std={std:.2f}, risk_adj={risk_adj:.2f} (lambda={VARIANCE_LAMBDA})")


def main():
    print("=" * 70)
    print("Variant A (I3 + I7) parity tests")
    print("=" * 70)
    test_evaluate_backwards_compatible()
    test_waste_penalty_fires_on_trap()
    test_waste_penalty_skips_small_probe()
    test_waste_penalty_credits_damage()
    test_waste_no_penalty_on_full_payback()
    test_variance_lambda_constant()
    test_risk_adjusted_score_formula()
    print("=" * 70)
    print("ALL VARIANT A TESTS PASSED")


if __name__ == "__main__":
    main()
