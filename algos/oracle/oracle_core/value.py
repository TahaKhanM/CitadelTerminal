"""Value function for Oracle search — score a post-action-phase state.

Used by the search loop in the Oracle algo:
  1. Enumerate ~500 candidate action plans.
  2. For each plan, sample K opponent action plans.
  3. Roll the state forward through `sim_eval.evaluate_action_phase` (sim_rs).
  4. Score the resulting state via ``evaluate(state, my_player=...)``.
  5. Pick the candidate that maximizes expected utility.

Performance contract (this module):
  - `evaluate(state)` must complete in <= 1 ms (search calls it ~10K times
    per turn). We avoid Python loops where possible and inline all hot
    summations.
  - No I/O, no sim, no opponent-model dependence. PURE function on a state.

State dict schema (matches `oracle_core.sim_eval.py_state_to_dict`):

    {
      "turn": int,
      "p1": {"hp": float, "sp": float, "mp": float},
      "p2": {"hp": float, "sp": float, "mp": float},
      "structures": [
        {"xy": [x, y], "type_idx": int, "upgraded": bool, "hp": float,
         "uid": str, "player": int, "turn_start_removal": int|None},
        ...
      ],
      "mobiles": [
        {"xy": [x, y], "type_idx": int, "hp": float, "shield": float,
         "uid": str, "player": int, "spawn_xy": [x, y], "target_edge": int,
         "steps_taken": int, "breached": bool,
         "finished_navigating": bool, "reached_target": bool},
        ...
      ],
    }

type_idx mapping:
    0 = WALL    1 = SUPPORT 2 = TURRET
    3 = SCOUT   4 = DEMOLISHER 5 = INTERCEPTOR

Default weights chosen from the published top-finalist retrospectives
(CMU13, Waterloo Team 6, GRETCHEN composite):

    HP_WEIGHT      = 100.0    # 1 HP = 100 utility (HP is the win condition)
    STRUCT_WEIGHT  = 1.0      # SP-equivalent of structure value
    RESOURCE_WEIGHT = 0.5     # MP / SP banked
    BREACH_WEIGHT  = 25.0     # mobile-pressure terms

The search loop pulls maxima of `evaluate(...)`, so the function returns
a single floating-point utility from `my_player`'s perspective.
"""
from __future__ import annotations

from typing import Any, Dict, Optional

# ---------------------------------------------------------------------------
# Type-index <-> stat tables (Citadel ruleset, verified against live config
# snapshot at algos/oracle/data/citadel_config_snapshot.json on 2026-04-26).
#
# Module-level so the value function is hot-cache friendly.
# ---------------------------------------------------------------------------

# Per-type-idx data: (cost1_base, max_hp_base, cost1_upgraded_total, max_hp_upgraded)
# Mobile units (idx 3-5) have None for SP cost (they're MP, not built into struct value).
_STRUCT_STATS = {
    # type_idx: (base_cost_sp, base_max_hp, upgraded_cost_sp, upgraded_max_hp)
    0: (1.0, 60.0, 3.0, 200.0),   # WALL    (1 SP base, +2 SP upgrade, 60/200 HP)
    1: (4.0, 1.0,  8.0, 40.0),    # SUPPORT (4 SP base, +4 SP upgrade, 1/40 HP)
    2: (2.0, 60.0, 6.0, 100.0),   # TURRET  (2 SP base, +4 SP upgrade, 60/100 HP)
}

# Refund coefficients (matches engine refund formula —
# base 0.9, upgraded 0.8 of the InitialCost * (HP_now / HP_max)).
_REFUND_BASE = 0.9
_REFUND_UPGRADED = 0.8

# MP cost per mobile unit (Citadel ruleset)
_MOBILE_MP_COST = {3: 1.0, 4: 3.0, 5: 1.0}

# Default weights — exposed so the search can scan a small grid if needed.
DEFAULT_WEIGHTS: Dict[str, float] = {
    "hp":            100.0,   # ±100 per HP point — HP is the game tiebreak
    "struct":        1.0,     # SP-equivalent value of standing structures
    "resource":      0.5,     # banked MP/SP (counted as SP-equivalents)
    "breach":        25.0,    # opponent mobiles in our half (-) / ours in theirs (+)
    "mp_value":      1.5,     # MP is more valuable than SP per point (1 MP ≈ 1.5 SP)
    "sp_value":      1.0,     # baseline SP value
    "income_lookahead": 1.0,  # weight on next-turn deterministic income
}

# Misc tunables (kept module-level to avoid dict lookups in hot loop)
_MP_DECAY = 0.75            # MP decays 25%/turn (round to 0.1 nearest IRL — fine here)
_HP_THRESHOLD_BREACH = 5.0  # mobile is "credible" if HP+shield > this
_TURRET_BONUS = 0.05        # mid-row turrets are slightly more valuable
_SUPPORT_BONUS = 0.05       # back-row supports are slightly more valuable


# ---------------------------------------------------------------------------
# Public API
# ---------------------------------------------------------------------------

def evaluate(
    state: Dict[str, Any],
    *,
    my_player: int = 1,
    weights: Optional[Dict[str, float]] = None,
) -> float:
    """Score `state` from `my_player`'s perspective. Higher = better for us.

    Default weights mirror the formula given in the project brief:

        U = (HP_self - HP_opp) * 100
          + (struct_value_self - struct_value_opp) * 1.0
          + (resource_value_self - resource_value_opp) * 0.5
          + breach_pressure_term

    All weight scalars can be overridden via the `weights` dict (any
    missing key falls back to DEFAULT_WEIGHTS).

    Returns a scalar utility. Callers (search loop) maximize it.
    """
    w = weights if weights is not None else DEFAULT_WEIGHTS
    # Pull weights up-front so the hot loop has them in locals.
    w_hp = float(w.get("hp", DEFAULT_WEIGHTS["hp"]))
    w_struct = float(w.get("struct", DEFAULT_WEIGHTS["struct"]))
    w_resource = float(w.get("resource", DEFAULT_WEIGHTS["resource"]))
    w_breach = float(w.get("breach", DEFAULT_WEIGHTS["breach"]))
    w_mp = float(w.get("mp_value", DEFAULT_WEIGHTS["mp_value"]))
    w_sp = float(w.get("sp_value", DEFAULT_WEIGHTS["sp_value"]))
    w_income = float(w.get("income_lookahead", DEFAULT_WEIGHTS["income_lookahead"]))

    # Determine self/opp side aliases once.
    if my_player == 1:
        self_key, opp_key = "p1", "p2"
    else:
        self_key, opp_key = "p2", "p1"

    self_stats = state.get(self_key) or {}
    opp_stats = state.get(opp_key) or {}

    # ------- HP differential (dominates) -------
    hp_self = float(self_stats.get("hp", 0.0))
    hp_opp = float(opp_stats.get("hp", 0.0))
    hp_term = (hp_self - hp_opp) * w_hp

    # ------- Structure value (SP-equivalent of standing defense) -------
    struct_self, struct_opp = _structure_value(state, my_player)
    struct_term = (struct_self - struct_opp) * w_struct

    # ------- Resource value (banked MP/SP + lookahead income) -------
    sp_self = float(self_stats.get("sp", 0.0))
    mp_self = float(self_stats.get("mp", 0.0))
    sp_opp = float(opp_stats.get("sp", 0.0))
    mp_opp = float(opp_stats.get("mp", 0.0))

    turn = int(state.get("turn", 0))
    # Income lookahead: deterministic next-turn gain.
    # SP: +4. MP: +(1 + turn // 5) actually engine uses bucket schedule
    # (see GAME_RULES §3.1) but for a value heuristic the linear approx
    # suffices — MP scales similarly either way.
    next_mp_gain = 1.0 + float(turn // 5)
    base_sp_gain = 4.0

    # Banked-resource value: MP is worth more than SP per point because
    # it converts directly to damage; weight by w_mp / w_sp.
    res_self = (
        mp_self * w_mp
        + sp_self * w_sp
        # 1 turn of decay applied to lookahead MP — banking past a few
        # turns wastes value (25%/turn). We just lookahead 1 turn.
        + (next_mp_gain * _MP_DECAY) * w_mp * w_income
        + base_sp_gain * w_sp * w_income
    )
    res_opp = (
        mp_opp * w_mp
        + sp_opp * w_sp
        + (next_mp_gain * _MP_DECAY) * w_mp * w_income
        + base_sp_gain * w_sp * w_income
    )
    resource_term = (res_self - res_opp) * w_resource

    # ------- Breach pressure (mobiles still on board) -------
    breach_pressure = _breach_pressure(state, my_player)
    breach_term = breach_pressure * w_breach

    return hp_term + struct_term + resource_term + breach_term


def evaluate_diff(
    state_before: Dict[str, Any],
    state_after: Dict[str, Any],
    *,
    my_player: int = 1,
    weights: Optional[Dict[str, float]] = None,
) -> float:
    """Score the CHANGE caused by a turn (or any state transition).

    Returns ``evaluate(state_after) - evaluate(state_before)``. Useful
    when the search wants to rank candidates by their *incremental*
    effect rather than absolute board strength.
    """
    return (
        evaluate(state_after, my_player=my_player, weights=weights)
        - evaluate(state_before, my_player=my_player, weights=weights)
    )


# ---------------------------------------------------------------------------
# Internal helpers (kept tight — search calls evaluate() ~10K times/turn)
# ---------------------------------------------------------------------------

def _structure_value(
    state: Dict[str, Any], my_player: int,
) -> tuple:
    """Return (self_struct_value_SP, opp_struct_value_SP).

    Each structure is valued at:
        refund_value = refund_coef * cost1 * (hp / max_hp)
        + small placement bonus

    Refund coefficient is 0.9 base / 0.8 upgraded — matches the engine's
    refund formula (so structure_value reflects "SP we could recover by
    removing right now"). HP is current; max_hp is the standing max.

    Placement bonus:
      - Supports at high Y (back row) get a small boost (they shield more).
      - Turrets in mid-rows get a small boost (better coverage).
    Bonus is small enough not to dominate HP-based valuation.
    """
    self_v = 0.0
    opp_v = 0.0
    for s in state.get("structures") or ():
        type_idx = s.get("type_idx")
        if type_idx is None or type_idx not in _STRUCT_STATS:
            continue
        cost_base, hp_base, cost_up, hp_up = _STRUCT_STATS[type_idx]
        upgraded = bool(s.get("upgraded", False))
        if upgraded:
            cost = cost_up
            max_hp = hp_up
            refund_coef = _REFUND_UPGRADED
        else:
            cost = cost_base
            max_hp = hp_base
            refund_coef = _REFUND_BASE

        hp = float(s.get("hp", max_hp))
        # Clamp to [0, 1] to guard against any sim_rs float drift.
        if max_hp <= 0.0:
            hp_frac = 0.0
        else:
            hp_frac = hp / max_hp
            if hp_frac < 0.0:
                hp_frac = 0.0
            elif hp_frac > 1.0:
                hp_frac = 1.0

        v = refund_coef * cost * hp_frac

        # Placement bonus.
        # xy may be [x, y] list or tuple.
        xy = s.get("xy") or (0, 0)
        try:
            y = int(xy[1])
        except (TypeError, IndexError):
            y = 0
        # For self (my_player=1, bottom): "back row" = high Y means y=13.
        # For self (my_player=2, top):    "back row" = low Y means y=14.
        # Translate to "distance from own back row".
        owner = int(s.get("player", 0))

        # Support: bonus for being on own back row.
        if type_idx == 1:  # SUPPORT
            if owner == 1:
                # bottom: y=13 is best
                back_dist = 13 - y
            else:
                # top: y=14 is best
                back_dist = y - 14
            # back_dist clamps: 0..13. closer-to-back gets more bonus.
            if back_dist < 0:
                back_dist = 0
            elif back_dist > 13:
                back_dist = 13
            v += _SUPPORT_BONUS * cost * (1.0 - back_dist / 13.0)

        # Turret: mid-row bonus (rows 9-12 from each side's perspective).
        if type_idx == 2:  # TURRET
            if owner == 1:
                # bottom: best around y=11-12
                mid_dist = abs(y - 11)
            else:
                # top: best around y=15-16
                mid_dist = abs(y - 16)
            if mid_dist > 13:
                mid_dist = 13
            v += _TURRET_BONUS * cost * (1.0 - mid_dist / 13.0)

        if owner == my_player:
            self_v += v
        else:
            opp_v += v
    return self_v, opp_v


def _breach_pressure(state: Dict[str, Any], my_player: int) -> float:
    """Score "mobile pressure" from `my_player`'s perspective.

    Positive when our mobiles are deep in opponent territory (about to
    score / damaging structures). Negative when opponent mobiles are in
    our half (about to hurt us).

    A mobile is "credible" if hp + shield > _HP_THRESHOLD_BREACH (default
    5 HP). Sub-threshold mobiles will likely die before they breach so we
    don't count them.

    "Their half" for player 1 (bottom): y >= 14.
    "Their half" for player 2 (top):    y < 14.

    Already-breached mobiles don't add pressure (they've left the arena).
    """
    self_advancing = 0
    opp_in_our_half = 0
    for m in state.get("mobiles") or ():
        if m.get("breached"):
            continue
        owner = int(m.get("player", 0))
        hp = float(m.get("hp", 0.0)) + float(m.get("shield", 0.0))
        if hp <= _HP_THRESHOLD_BREACH:
            continue

        xy = m.get("xy") or (0, 0)
        try:
            y = int(xy[1])
        except (TypeError, IndexError):
            continue

        # Determine if this mobile is in opponent's half (from owner perspective).
        if owner == 1:
            # owner is bottom; "opp half" is top half y >= 14.
            in_opp_half = (y >= 14)
        else:
            # owner is top; "opp half" is bottom half y < 14.
            in_opp_half = (y < 14)

        if not in_opp_half:
            continue

        if owner == my_player:
            self_advancing += 1
        else:
            opp_in_our_half += 1

    # Normalize: each "credible" mobile in the opponent half contributes
    # +1 in our favor; each opponent mobile in our half contributes -1.
    return float(self_advancing - opp_in_our_half)


__all__ = [
    "evaluate",
    "evaluate_diff",
    "DEFAULT_WEIGHTS",
]
