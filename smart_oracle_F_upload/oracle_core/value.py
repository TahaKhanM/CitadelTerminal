"""Value function for oracle_pure - VARIANT F (VA + VB).

Combines two independent improvements over M1Lite:
- VA (I3): Wasted-MP penalty via `pre_state_dict` param
- VB (I2): Trap penalty via `defense_viability` param (sim-based probe result)

VC (I4) breach-pressure coverage was REMOVED based on live ladder evidence
that it caused regression on pipmy/Redemption (VD lost where VA won, the
only difference being VC's coverage term).

Both params default to None — back-compat with M1Lite behavior.
"""
from __future__ import annotations

import math
from typing import Any, Dict, Optional, Sequence, Tuple

from .constants import (
    SUPPORT_IDX, TURRET_IDX, WALL_IDX,
)


# -- Weights ----------------------------------------------------------------

VALUE_WEIGHTS = {
    "hp": 100.0,
    "struct": 1.0,
    "resource_sp": 0.5,
    "resource_mp": 0.5,
    "breach": 25.0,
    "exposure": 5.0,
    "support_shield_synergy": 2.0,
    # VA — I3 wasted-MP penalty
    "waste_w": 5.0,
    "waste_k": 1.0,
    "waste_mp_threshold": 5.0,
    "waste_struct_dmg_coef": 0.5,
    # VB — I2 trap penalty (per missing breach in 5-scout probe)
    "trap_penalty": 50.0,
}

STRUCT_VALUE_COEF = {
    (WALL_IDX, False): 1.5,
    (WALL_IDX, True):  6.0,
    (TURRET_IDX, False): 4.0,
    (TURRET_IDX, True):  12.0,
    (SUPPORT_IDX, False): 0.5,
    (SUPPORT_IDX, True):  10.0,
}


def _structure_value_per_side(state_dict: Dict[str, Any], player: int) -> float:
    total = 0.0
    for s in state_dict.get("structures", []):
        if int(s.get("player", 0)) != player:
            continue
        idx = int(s.get("type_idx", -1))
        upg = bool(s.get("upgraded", False))
        coef = STRUCT_VALUE_COEF.get((idx, upg))
        if coef is None:
            continue
        max_hp = _MAX_HP.get((idx, upg), 60.0)
        cur_hp = max(0.0, float(s.get("hp", 0)))
        frac = min(1.0, cur_hp / max_hp) if max_hp > 0 else 0.0
        total += coef * frac
        if s.get("turn_start_removal") is not None:
            total *= 0.5
    return total


_MAX_HP = {
    (WALL_IDX, False): 60.0, (WALL_IDX, True): 200.0,
    (TURRET_IDX, False): 60.0, (TURRET_IDX, True): 100.0,
    (SUPPORT_IDX, False): 1.0, (SUPPORT_IDX, True): 40.0,
}


def _saturating_resource(amount: float, free_threshold: float = 10.0) -> float:
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
             pre_state_dict: Optional[Dict[str, Any]] = None,
             defense_viability: Optional[int] = None,
             ) -> float:
    """Score the post-action state.

    Args:
        state_dict: post-action state.
        my_player: 1 or 2.
        weights: optional override.
        prev_breaches_*: breach counts before action phase.
        pre_state_dict: VA — pre-sim state for waste-MP penalty.
        defense_viability: VB — sim-probe result (0..PROBE_SCOUT_COUNT).
    """
    if weights is None:
        weights = VALUE_WEIGHTS
    opp_player = 2 if my_player == 1 else 1
    side_key = "p1" if my_player == 1 else "p2"
    opp_key = "p2" if my_player == 1 else "p1"

    if my_player == 1:
        my_hp = float(state_dict.get("p1", {}).get("hp", 0))
        opp_hp = float(state_dict.get("p2", {}).get("hp", 0))
    else:
        my_hp = float(state_dict.get("p2", {}).get("hp", 0))
        opp_hp = float(state_dict.get("p1", {}).get("hp", 0))
    hp_term = weights["hp"] * (my_hp - opp_hp)

    my_struct = _structure_value_per_side(state_dict, my_player)
    opp_struct = _structure_value_per_side(state_dict, opp_player)
    struct_term = weights["struct"] * (my_struct - opp_struct)

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

    upg_supports = sum(
        1 for s in state_dict.get("structures", [])
        if int(s.get("player", 0)) == my_player and
        int(s.get("type_idx", -1)) == SUPPORT_IDX and
        bool(s.get("upgraded", False))
    )
    my_scouts_alive = sum(
        1 for m in state_dict.get("mobiles", [])
        if int(m.get("player", 0)) == my_player and
        int(m.get("type_idx", -1)) in (3, 4, 5)
    )
    syn_term = weights["support_shield_synergy"] * upg_supports * min(my_scouts_alive, 8)

    # VA: Wasted-MP penalty
    waste_term = 0.0
    if pre_state_dict is not None:
        pre_my_mp = float(pre_state_dict.get(side_key, {}).get("mp", 0))
        post_my_mp = my_mp
        mp_spent_self = max(0.0, pre_my_mp - post_my_mp)
        if mp_spent_self >= weights.get("waste_mp_threshold", 5.0):
            pre_opp_hp = float(pre_state_dict.get(opp_key, {}).get("hp", opp_hp))
            opp_hp_lost = max(0.0, pre_opp_hp - opp_hp)
            pre_opp_struct = _structure_value_per_side(pre_state_dict, opp_player)
            opp_struct_lost = max(0.0, pre_opp_struct - opp_struct)
            damage_caused = (
                opp_hp_lost +
                weights.get("waste_struct_dmg_coef", 0.5) * opp_struct_lost
            )
            unjustified = max(0.0, mp_spent_self -
                              weights.get("waste_k", 1.0) * damage_caused)
            waste_term = -weights.get("waste_w", 5.0) * unjustified

    # VB: Trap penalty
    trap_term = 0.0
    if defense_viability is not None:
        from .viability_probe import PROBE_SCOUT_COUNT
        missing_breaches = max(0, PROBE_SCOUT_COUNT - int(defense_viability))
        trap_term = -weights["trap_penalty"] * missing_breaches

    return float(hp_term + struct_term + sp_term + mp_term + breach_term +
                 syn_term + waste_term + trap_term)


def evaluate_diff(pre_dict: Dict[str, Any], post_dict: Dict[str, Any],
                  my_player: int = 1, **kwargs) -> float:
    return evaluate(post_dict, my_player=my_player, **kwargs) - \
        evaluate(pre_dict, my_player=my_player, **kwargs)


__all__ = [
    "evaluate", "evaluate_diff", "VALUE_WEIGHTS",
    "STRUCT_VALUE_COEF",
]
