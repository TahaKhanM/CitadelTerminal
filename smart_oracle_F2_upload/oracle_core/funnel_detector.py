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

# Detection thresholds — tightened after smart_oracle_F local-bench
# regressions revealed that ZONE concentration alone is too coarse:
#
#   - Original (3 breaches, 60% zone): misfired vs IS6 at turn 6
#     (2 RIGHT + 1 CENTER). smart_F lost 30-41.
#   - Tightening A (4 breaches, 75% zone): misfired vs M1Lite at
#     turn 17 (3 RIGHT + 1 CENTER = exactly 75%). smart_F lost 15-42.
#   - Tightening B (6 breaches, 75% zone): misfired vs IS6 at turn 22
#     (3×(26,12) + (25,11) + 2×(23,9) — 6/6 RIGHT but spread across
#     3 tiles). smart_F lost 23-44.
#
# Zone-only detection treats "spread across the right flank" the same
# as "drilled at one tile." Real funnels drill ONE tile: ameyg's
# funnel-rush-v7 hit (2,11) × 56, (4,9) × 26+. Oracle-style opponents
# spread across multiple flank tiles as a side effect of how their
# spawn-diagonals route.
#
# Fix: require BOTH zone dominance AND single-tile dominance. A real
# funnel will have ONE tile receiving most of the hits; a wide opponent
# attack won't.
#
# smart_oracle_F2 — lowered MIN_BREACHES based on live-ladder loss
# analysis (4 losses: fluffffy, funnel-rush-v6, funnel-rush-v8, SwitchV4).
# Detector at MIN=10 fires too late (turns 32/68/38/63 → after we've lost
# 25+ HP). The OpponentClassifier=SINGLE gate in algo_strategy already
# prevents misfires vs multi-archetype oracles, so MIN can drop to 6
# without re-introducing the IS6 regression.
FUNNEL_LOOKBACK = 12
FUNNEL_MIN_BREACHES = 6
FUNNEL_DOMINANCE_FRAC = 0.75
# Single-tile dominance: top breach tile must hold >= this fraction of
# the lookback breaches. ameyg/funnel-rush-v7 vs M1Lite: 56/64 at (2,11)
# = 88%; vs VD: 26/50 at (4,9) = 52%. Threshold 0.4 catches both.
# Inter-oracle play (e.g. smart_F vs IS6) caps at ~3/6 = 50% top tile;
# the classifier gate (SINGLE only) guards against this case.
FUNNEL_TOP_TILE_FRAC = 0.4


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

    A funnel is declared when ALL of:
      - we have at least FUNNEL_MIN_BREACHES recent breaches,
      - at least FUNNEL_DOMINANCE_FRAC of the last FUNNEL_LOOKBACK breaches
        fall in the same zone (LEFT, RIGHT, or CENTER), AND
      - at least FUNNEL_TOP_TILE_FRAC of the lookback breaches fall on a
        SINGLE tile within that zone.

    The single-tile gate distinguishes a real funnel (drill one tile, e.g.
    ameyg hits (2,11) × 56) from a smart oracle opponent whose attacks
    happen to spread across the same-zone tiles (e.g. (26,12)+(25,11)+
    (23,9) — same RIGHT zone, different tiles, NOT a funnel).

    smart_oracle_F2 — CENTER is now a valid target (e.g. ameyg/funnel-
    rush-v6's (16,2)×20 deep-center penetration). Loss of v6 was caused
    by detector ignoring CENTER and returning None.
    """
    if not recent_breaches:
        return None
    breaches = list(recent_breaches)[-FUNNEL_LOOKBACK:]
    n = len(breaches)
    if n < FUNNEL_MIN_BREACHES:
        return None
    zone_counts = Counter(classify_tile(x, y) for x, y in breaches)
    tile_counts = Counter(breaches)
    top_tile, top_count = tile_counts.most_common(1)[0]
    top_tile_zone = classify_tile(top_tile[0], top_tile[1])
    for zone in (ZONE_LEFT, ZONE_RIGHT, ZONE_CENTER):
        if zone_counts[zone] / n < FUNNEL_DOMINANCE_FRAC:
            continue
        if top_tile_zone != zone:
            continue
        if top_count / n < FUNNEL_TOP_TILE_FRAC:
            continue
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
    # CENTER funnel (e.g. ameyg/v6 drilling (16,2)) — opp's mobiles can
    # reach center-deep tiles from any launcher. Accept all.
    ZONE_CENTER: {"center", "L_corner", "L_mid", "L_diag",
                  "R_corner", "R_mid", "R_diag"},
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
