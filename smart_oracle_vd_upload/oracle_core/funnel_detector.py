"""Funnel attack detection for smart_oracle_vd.

When the opponent uses a funnel strategy, they channel attackers through a
wall corridor in their half so almost every attacker breaches the same flank
of our half. The base VD search treats every recent breach uniformly via the
breach_pressure map, so its opponent samples and defense templates remain
balanced across the board even when the opponent is plainly funnelling one
side. That is the oversight this module fixes.

Pipeline:

  1. `detect_funnel_target(recent_breaches)` inspects the recent opp breach
     tiles. If ≥`FUNNEL_DOMINANCE_FRAC` of the last `FUNNEL_LOOKBACK` breaches
     fall in the same flank zone (LEFT x≤7 / RIGHT x≥20), we declare that
     zone the funnel target. CENTER is never returned because center
     breaches typically reflect a balanced/center-rush attack, not a funnel.

  2. `plan_attacks_zone(plan, zone)` checks whether an opponent ActionPlan
     spawns from a launcher bucket consistent with attacking the funnel
     zone. The mapping is derived from the empirical funnel patterns
     documented in FUNNEL_COUNTER_PLAN.md — diagonally-routed mobiles from
     the opposite side, plus center, are the launchers that actually
     deliver attacks to a flank.

The search uses (1) to decide whether to engage the funnel branch, and
(2) to filter the opp samples it evaluates each candidate against. The
enumerator uses (1) to add zone-focused defense templates to the candidate
pool.
"""
from __future__ import annotations

from collections import Counter
from typing import Optional, Sequence, Tuple

from .opponent_model import _x_to_launcher_bucket
from .plan import ActionPlan, KIND_SPAWN_MOBILE


# Zone definitions. Match the flank-corner zone used in
# FUNNEL_COUNTER_PLAN.md (`x ≤ 7 OR x ≥ 20, y ≤ 13`). y is implicit because
# every breach tile is on our edge by construction.
ZONE_LEFT = "left"
ZONE_RIGHT = "right"
ZONE_CENTER = "center"

# Detection thresholds. The defaults are conservative enough that they will
# not fire on a single fluke breach, but will catch the empirical funnel
# pattern (3+ flank breaches across 4-5 turns) the algo loses to.
FUNNEL_LOOKBACK = 6
FUNNEL_MIN_BREACHES = 3
FUNNEL_DOMINANCE_FRAC = 0.6


def classify_tile(x: int, _y: int) -> str:
    """Map a breach tile to its flank zone."""
    if x <= 7:
        return ZONE_LEFT
    if x >= 20:
        return ZONE_RIGHT
    return ZONE_CENTER


def detect_funnel_target(recent_breaches: Sequence[Tuple[int, int]]
                         ) -> Optional[str]:
    """Return the dominant flank zone if a funnel pattern is detected, else None.

    A funnel is declared when:
      - we have at least FUNNEL_MIN_BREACHES recent breaches, AND
      - at least FUNNEL_DOMINANCE_FRAC of the last FUNNEL_LOOKBACK breaches
        fall in the same flank zone (LEFT or RIGHT).

    CENTER is intentionally excluded — a center breach is consistent with a
    balanced rush, not a funnel. The user fix is specifically for the
    flank-funnel pattern.
    """
    if not recent_breaches:
        return None
    breaches = list(recent_breaches)[-FUNNEL_LOOKBACK:]
    n = len(breaches)
    if n < FUNNEL_MIN_BREACHES:
        return None
    counts = Counter(classify_tile(x, y) for x, y in breaches)
    for zone in (ZONE_LEFT, ZONE_RIGHT):
        if counts[zone] / n >= FUNNEL_DOMINANCE_FRAC:
            return zone
    return None


# Launcher buckets that historically deliver attacks to each flank zone.
# Mapped from the empirical funnel patterns:
#   LEFT-flank breaches come from
#     - opp's center spawn (top-center scouts walk through opp's wall
#       corridor and emerge on our left), and
#     - opp's right-side launchers (R_corner / R_mid / R_diag) — diagonal
#       walk lands on our left.
#   RIGHT-flank breaches mirror: center, plus opp's L_corner / L_mid /
#     L_diag (the (3,17)→(23,9) demo funnel).
#
# An empty signature (no mobiles) is always considered relevant — it is the
# "opp does nothing" baseline the search needs for fair comparison.
_ZONE_TO_OPP_LAUNCHERS = {
    ZONE_LEFT:  {"center", "R_corner", "R_mid", "R_diag", "L_corner"},
    ZONE_RIGHT: {"center", "L_corner", "L_mid", "L_diag", "R_corner"},
}


def opp_launcher_attacks_zone(launcher: Optional[str], zone: str) -> bool:
    if launcher is None:
        return True
    return launcher in _ZONE_TO_OPP_LAUNCHERS.get(zone, set())


def plan_attacks_zone(plan: ActionPlan, zone: str) -> bool:
    """True if the opp ActionPlan's mobile spawns are consistent with
    attacking the given flank zone.

    Plans without any mobile ops (the opp-does-nothing baseline) are kept
    so the search can still evaluate "what if opp hoards MP this turn".
    """
    has_mobiles = False
    for op in plan.ops:
        if op.kind != KIND_SPAWN_MOBILE:
            continue
        has_mobiles = True
        x = int(op.xy[0])
        if opp_launcher_attacks_zone(_x_to_launcher_bucket(x), zone):
            return True
    if not has_mobiles:
        return True
    return False


__all__ = [
    "ZONE_LEFT", "ZONE_RIGHT", "ZONE_CENTER",
    "FUNNEL_LOOKBACK", "FUNNEL_MIN_BREACHES", "FUNNEL_DOMINANCE_FRAC",
    "classify_tile",
    "detect_funnel_target",
    "opp_launcher_attacks_zone",
    "plan_attacks_zone",
]
