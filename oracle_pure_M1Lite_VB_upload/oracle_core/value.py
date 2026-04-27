"""Value function for oracle_pure.

Scores a post-action sim_rs state dict from `my_player`'s perspective.
Higher = better for me.

Design principles (chosen explicitly to avoid the failure modes of
the previous oracle attempt):

1. HP differential DOMINATES. The game tiebreak at turn 100 is HP, so
   anything that costs HP is enormously expensive and anything that
   gains HP is enormously valuable. Weight: 100 per HP point.

2. Structure value uses DEFENSIVE CONTRIBUTION, not refund coefficient.
   A standing turret is worth what it's protecting (its threat range +
   damage ability), not what we'd get by tearing it down. Refund value
   biases the search toward liquidation, which is exactly the wrong
   incentive in mid/late game.

3. Resources have SATURATING value. Banking SP/MP up to ~1-2 turns of
   income is fine; past that the marginal value drops fast. This
   actively DISCOURAGES hoarding past a tactical threshold.

4. Tactical pressure terms penalize OPP MP buildup (incoming rush
   warning) and reward OUR MP buildup IF we just attacked (the
   accumulated income from the bonus SP per breach).

5. Anti-symmetric: U(state, p=1) = -U(state, p=2) for the HP/struct/
   tactical components. Resources are absolute (small weight).

The function is INTENTIONALLY simple — sim_rs is what gives the
search its predictive power. The value function only needs to encode
"more HP, more structures, more resources = better."
"""
from __future__ import annotations

import math
from typing import Any, Dict, Optional, Sequence

from .constants import (
    SUPPORT_IDX, TURRET_IDX, WALL_IDX,
)


# -- Weights (pre-tuned; tweak only via VALUE_WEIGHTS dict) ------------------

VALUE_WEIGHTS = {
    "hp": 100.0,            # HP differential dominates
    "struct": 1.0,          # multiplier on structure value
    "resource_sp": 0.5,
    "resource_mp": 0.5,
    "breach": 25.0,         # bonus per breach we caused / penalty per breach against us
    "exposure": 5.0,        # penalty per recent-breach hotspot still uncovered
    "support_shield_synergy": 2.0,   # bonus for upgraded supports near our scouts
    # Variant B: penalty per probe scout that COULDN'T breach.
    # The probe (5 synthetic scouts at center launcher) measures whether
    # the post-defense state allows our offense to reach opp territory.
    # Penalty = trap_penalty × (PROBE_SCOUT_COUNT - probe_breach_count).
    # If all 5 probe scouts breach → 0 penalty.
    # If 0 of 5 breach (full trap) → 5 × 50 = 250 penalty.
    # Calibration: a successful 12-scout breach scores ~1200 (12 HP × 100/HP).
    # A trap state with 0 breach scores ~0. Penalty of 250 makes a
    # trap-creating defense (~+8 SP value × 0.5 = ~4 score bonus from struct
    # term) clearly worse than a viable alternative — but does NOT overpower
    # a genuinely dominant defense win that scores +1000+ via HP advantage.
    # See SKEPTICAL VERIFICATION §16 of CONTEXT_HANDOFF.md for why we use
    # this soft signal instead of M2's hard-rejection BFS check.
    "trap_penalty": 50.0,
}

# Per-(type, upgraded) defensive-contribution coefficient.
#
# Calibration (post turn-25 oracle-pure-vs-v13 trace, 2026-04-26):
# - Walls had to be RAISED 3x because their 1-turn sim contribution looks
#   tiny (60 HP absorbed once) but their MULTI-TURN protective role doubles
#   the lifespan of every turret behind them. Without this calibration, the
#   search rejects walls in favor of "more turrets" and our turrets get
#   killed scout-by-scout while opp turrets survive behind their wall row.
# - Upgraded supports got a small bump to reward the +shield synergy.
#
# Reasoning per type:
#   Wall: blocks paths AND absorbs scout-fire that would otherwise hit
#         turrets. 60HP base → ~30 scout-attacks absorbed = ~2 turret-turns
#         saved. 200HP upgraded → ~10 turret-turns saved (huge ROI).
#   Turret: damages mobiles. Upgraded does 20/atk + range 3.5 vs 6/atk
#         range 2.5. Base turret needs walls in front to survive a rush.
#   Support: provides shields. Base 1 HP — dies to one stray attack.
#         Upgraded 40 HP + 1+0.7y shield/scout = doubles scout HP at y=13.
STRUCT_VALUE_COEF = {
    (WALL_IDX, False): 1.5,    # walls absorb damage; 1.5 reflects ~50% bonus over raw cost
    (WALL_IDX, True):  6.0,    # 200 HP is a huge wall — durable shield
    (TURRET_IDX, False): 4.0,
    (TURRET_IDX, True):  12.0,
    (SUPPORT_IDX, False): 0.5,
    (SUPPORT_IDX, True):  10.0,   # +shield per scout = doubles offensive throughput
}


def _structure_value_per_side(state_dict: Dict[str, Any], player: int) -> float:
    """Sum defensive-contribution value for a given player's structures."""
    total = 0.0
    for s in state_dict.get("structures", []):
        if int(s.get("player", 0)) != player:
            continue
        idx = int(s.get("type_idx", -1))
        upg = bool(s.get("upgraded", False))
        coef = STRUCT_VALUE_COEF.get((idx, upg))
        if coef is None:
            continue
        # Scale by HP fraction (damaged structures are worth less).
        max_hp = _MAX_HP.get((idx, upg), 60.0)
        cur_hp = max(0.0, float(s.get("hp", 0)))
        frac = min(1.0, cur_hp / max_hp) if max_hp > 0 else 0.0
        total += coef * frac
        # Small penalty if marked for removal (the structure is going away).
        if s.get("turn_start_removal") is not None:
            total *= 0.5
    return total


# Citadel max HP per (type, upgraded). Fallbacks; sim_rs uses runtime config.
_MAX_HP = {
    (WALL_IDX, False): 60.0, (WALL_IDX, True): 200.0,
    (TURRET_IDX, False): 60.0, (TURRET_IDX, True): 100.0,
    (SUPPORT_IDX, False): 1.0, (SUPPORT_IDX, True): 40.0,
}


def _saturating_resource(amount: float, free_threshold: float = 10.0) -> float:
    """Return a saturating reward for stockpiling resources.

    Up to `free_threshold`, marginal value is 1.0 per unit.
    Beyond, marginal value decays as 1 / (1 + (amount-threshold)/threshold).
    Total at amount=20 ≈ 10 + log(2) * threshold ≈ 16.9 at threshold=10.
    Total at amount=50 ≈ 10 + log(5) * threshold ≈ 26.1.
    """
    if amount <= free_threshold:
        return float(amount)
    extra = amount - free_threshold
    return float(free_threshold + math.log1p(extra / max(free_threshold, 1.0)) *
                 free_threshold)


def evaluate(state_dict: Dict[str, Any], my_player: int = 1,
             *,
             weights: Dict[str, float] = None,
             prev_breaches_self: int = 0,
             prev_breaches_opp: int = 0,
             defense_viability: int = None,
             ) -> float:
    """Score the post-action state from my_player's perspective.

    Args:
        state_dict: post-action state in sim_rs schema.
        my_player: 1 or 2 (which side is "me").
        weights: optional override for VALUE_WEIGHTS keys.
        prev_breaches_*: breach counts before the action phase. We use
            the DELTA in breaches to score "did I gain ground this turn?"
        defense_viability: Variant B — sim-based probe result for the
            candidate's defense layout. Integer 0..PROBE_SCOUT_COUNT.
            None = no probe data (no penalty applied).
            0 = trap-prone defense (full penalty).
            PROBE_SCOUT_COUNT (5) = all probe scouts breached → no penalty.

    Returns:
        Scalar utility. Higher = better for me.
    """
    if weights is None:
        weights = VALUE_WEIGHTS
    opp_player = 2 if my_player == 1 else 1

    # ---- HP differential ----
    if my_player == 1:
        my_hp = float(state_dict.get("p1", {}).get("hp", 0))
        opp_hp = float(state_dict.get("p2", {}).get("hp", 0))
    else:
        my_hp = float(state_dict.get("p2", {}).get("hp", 0))
        opp_hp = float(state_dict.get("p1", {}).get("hp", 0))
    hp_term = weights["hp"] * (my_hp - opp_hp)

    # ---- Structure value differential ----
    my_struct = _structure_value_per_side(state_dict, my_player)
    opp_struct = _structure_value_per_side(state_dict, opp_player)
    struct_term = weights["struct"] * (my_struct - opp_struct)

    # ---- Resource differential (saturating) ----
    if my_player == 1:
        my_sp = float(state_dict.get("p1", {}).get("sp", 0))
        my_mp = float(state_dict.get("p1", {}).get("mp", 0))
        opp_sp = float(state_dict.get("p2", {}).get("sp", 0))
        opp_mp = float(state_dict.get("p2", {}).get("mp", 0))
    else:
        my_sp = float(state_dict.get("p2", {}).get("sp", 0))
        my_mp = float(state_dict.get("p2", {}).get("mp", 0))
        opp_sp = float(state_dict.get("p1", {}).get("sp", 0))
        opp_mp = float(state_dict.get("p1", {}).get("mp", 0))
    sp_term = weights["resource_sp"] * (
        _saturating_resource(my_sp) - _saturating_resource(opp_sp)
    )
    mp_term = weights["resource_mp"] * (
        _saturating_resource(my_mp) - _saturating_resource(opp_mp)
    )

    # ---- Breach delta ----
    # Count in-flight mobiles that breached (post-action breach flag) per side.
    breaches_self = sum(
        1 for m in state_dict.get("mobiles", [])
        if int(m.get("player", 0)) == my_player and bool(m.get("breached", False))
    )
    breaches_opp = sum(
        1 for m in state_dict.get("mobiles", [])
        if int(m.get("player", 0)) == opp_player and bool(m.get("breached", False))
    )
    breach_term = weights["breach"] * (
        (breaches_self - prev_breaches_self) -
        (breaches_opp - prev_breaches_opp)
    )

    # ---- Support-scout synergy (upgraded supports + mobile scouts on our side) ----
    upg_supports = sum(
        1 for s in state_dict.get("structures", [])
        if int(s.get("player", 0)) == my_player and
        int(s.get("type_idx", -1)) == SUPPORT_IDX and
        bool(s.get("upgraded", False))
    )
    my_scouts_alive = sum(
        1 for m in state_dict.get("mobiles", [])
        if int(m.get("player", 0)) == my_player and
        int(m.get("type_idx", -1)) in (3, 4, 5)  # any mobile
    )
    syn_term = weights["support_shield_synergy"] * upg_supports * min(my_scouts_alive, 8)

    # ---- Variant B: Trap penalty ----
    # If a sim-based probe was provided, penalize defense templates that
    # block our offense. PROBE_SCOUT_COUNT (5) is the default probe size.
    # missing_breaches = how many of the 5 probe scouts FAILED to breach.
    trap_term = 0.0
    if defense_viability is not None:
        from .viability_probe import PROBE_SCOUT_COUNT
        missing_breaches = max(0, PROBE_SCOUT_COUNT - int(defense_viability))
        trap_term = -weights["trap_penalty"] * missing_breaches

    return float(hp_term + struct_term + sp_term + mp_term + breach_term +
                 syn_term + trap_term)


def evaluate_diff(pre_dict: Dict[str, Any], post_dict: Dict[str, Any],
                  my_player: int = 1, **kwargs) -> float:
    """Score the DELTA — useful when you want "how much did this plan
    improve my position?" rather than the absolute value.

    Equivalent to evaluate(post) - evaluate(pre) up to a constant; the
    difference removes any state-independent baseline.
    """
    return evaluate(post_dict, my_player=my_player, **kwargs) - \
        evaluate(pre_dict, my_player=my_player, **kwargs)


__all__ = [
    "evaluate", "evaluate_diff", "VALUE_WEIGHTS",
    "STRUCT_VALUE_COEF",
]
