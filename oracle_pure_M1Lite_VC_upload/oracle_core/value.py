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
from typing import Any, Dict, Sequence, Tuple

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
    # Variant C — soft signal that links observed-breach pressure to
    # our turrets' ability to threaten the breach tile. The value is
    # weight x (turret_threat x pressure_at_tile) summed over our
    # turret/tile pairs in range. See evaluate() for the formula.
    #
    # Tuning history:
    # - Initial pick: 1.0. Passed Tier A 10/10 + snorkeldink 2/2 but
    #   regressed deterministically vs M1Lite in head-to-head (0/2).
    #   Investigation showed early-game breach observations (T0-T1 from
    #   undefended openings) bias decisions later. Reduced to 0.2 to
    #   make the signal a tie-breaker only.
    # - At weight 0.2: a tile with pressure 5 makes 1 upgraded turret
    #   worth +20 score (0.2 HP equivalent). A tile with pressure 1.0
    #   makes 1 upgraded turret worth +4 score (negligible).
    "breach_pressure_coverage": 0.2,
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


# Citadel turret stats — fallbacks; sim_rs uses runtime config but the
# value function doesn't have access to it. Variant C uses these to
# compute coverage from the post-state structures list.
#
# Coverage formula: for each of our living turrets, find tiles within
# attack range that have positive breach_pressure, weight by HP fraction
# and base attack damage, sum the contributions. The sum measures
# "how much actual threat do I project at the tiles where I've been
# attacked?" Higher = better defense at hotspots.
_TURRET_BASE_ATK = 6.0
_TURRET_UPG_ATK = 20.0
_TURRET_BASE_RANGE = 2.5
_TURRET_UPG_RANGE = 3.5
_TURRET_BASE_RANGE_SQ = _TURRET_BASE_RANGE * _TURRET_BASE_RANGE
_TURRET_UPG_RANGE_SQ = _TURRET_UPG_RANGE * _TURRET_UPG_RANGE


def _coverage_value(state_dict: Dict[str, Any], my_player: int,
                    breach_pressure: Dict[Tuple[int, int], float]) -> float:
    """Sum, over our living turrets t and tiles u within t's attack range,
    of (hp_frac(t) * base_atk(t)) * pressure(u).

    Returns 0 if no pressure observed (early game / no breaches yet)."""
    if not breach_pressure:
        return 0.0
    # Pre-extract pressure tiles (we'll only consider those for distance check).
    pressure_items = [(xy, p) for xy, p in breach_pressure.items() if p > 0]
    if not pressure_items:
        return 0.0

    total = 0.0
    for s in state_dict.get("structures", []):
        if int(s.get("player", 0)) != my_player:
            continue
        if int(s.get("type_idx", -1)) != TURRET_IDX:
            continue
        upg = bool(s.get("upgraded", False))
        atk = _TURRET_UPG_ATK if upg else _TURRET_BASE_ATK
        range_sq = _TURRET_UPG_RANGE_SQ if upg else _TURRET_BASE_RANGE_SQ
        max_hp = _MAX_HP.get((TURRET_IDX, upg), 60.0)
        cur_hp = max(0.0, float(s.get("hp", 0)))
        if cur_hp <= 0:
            continue
        hp_frac = min(1.0, cur_hp / max_hp) if max_hp > 0 else 0.0
        threat = hp_frac * atk
        # Marked-for-removal turrets contribute half (they will be gone
        # next turn). Mirrors the discount in _structure_value_per_side.
        if s.get("turn_start_removal") is not None:
            threat *= 0.5
        try:
            tx, ty = s["xy"][0], s["xy"][1]
        except (KeyError, IndexError, TypeError):
            continue
        for (ux, uy), pressure in pressure_items:
            dx = tx - ux
            dy = ty - uy
            if (dx * dx + dy * dy) <= range_sq:
                total += threat * pressure
    return total


def evaluate(state_dict: Dict[str, Any], my_player: int = 1,
             *,
             weights: Dict[str, float] = None,
             prev_breaches_self: int = 0,
             prev_breaches_opp: int = 0,
             breach_pressure: Dict[Tuple[int, int], float] = None,
             ) -> float:
    """Score the post-action state from my_player's perspective.

    Args:
        state_dict: post-action state in sim_rs schema.
        my_player: 1 or 2 (which side is "me").
        weights: optional override for VALUE_WEIGHTS keys.
        prev_breaches_*: breach counts before the action phase. We use
            the DELTA in breaches to score "did I gain ground this turn?"
        breach_pressure: optional {tile -> pressure} map used by
            Variant C's coverage term. Populated from observed opponent
            breaches in algo_strategy.on_action_frame, decayed each turn.
            None or empty = no coverage term contribution (back-compat).

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

    # ---- Variant C: breach-pressure coverage (soft signal) ----
    # Reward defense plans that actually project threat at tiles where
    # the opponent has been breaching. The pressure map is observed
    # game state (from on_action_frame), decayed each turn. The term
    # is anti-symmetric: my coverage of MY hotspots minus opp's
    # coverage of THEIR hotspots is not symmetric here — we only have
    # observation of opp breaches against US, so the term only rewards
    # our defensive posture. (We don't observe opp's perspective; the
    # breach count we score on offense is already in `breach` term.)
    if breach_pressure:
        coverage_term = weights["breach_pressure_coverage"] * \
            _coverage_value(state_dict, my_player, breach_pressure)
    else:
        coverage_term = 0.0

    return float(hp_term + struct_term + sp_term + mp_term + breach_term +
                 syn_term + coverage_term)


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
