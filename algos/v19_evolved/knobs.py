"""Tunable knobs for v19_evolved. Plain data, no imports.

Both algo_strategy.py (at runtime) and tools/evolve.py (during CMA-ES)
import from this module, so it must have zero external dependencies.

CONFIG defaults reproduce v13_second_ring exactly.
BOUNDS[(knob)] = (lo, hi, is_int) for CMA-ES parameterisation.
"""

CONFIG = {
    "SCOUT_THRESHOLD_MULT": 10,
    "DEMO_THRESHOLD_MULT": 4,
    "DEMO_FRONT_THRESHOLD": 10,
    "PRESSURE_TRIGGER_MULT": 6,
    "BUILD_T_UPGRADE_CENTER": 1,
    "BUILD_T_UPGRADE_SIDELANE": 2,
    "BUILD_T_UPGRADE_OUTER_MID": 3,
    "BUILD_T_UPGRADE_OUTER_EDGE": 5,
    "BUILD_T_UPGRADE_INNER_CORNER": 7,
    "BUILD_T_OUTER_CORNERS": 10,
    "HOARD_T_START": 5,
    "HOARD_T_UPGRADE_SECOND_RING": 8,
    "HOARD_T_THIRD_RING": 12,
    "HOARD_T_WALL_UPGRADES": 15,
    "HOARD_T_OUTER_RING": 20,
    "UPGRADE_HP_PCT": 0.6,
}

BOUNDS = {
    "SCOUT_THRESHOLD_MULT":         (6, 16, True),
    "DEMO_THRESHOLD_MULT":          (2, 6, True),
    "DEMO_FRONT_THRESHOLD":         (5, 15, True),
    "PRESSURE_TRIGGER_MULT":        (3, 9, True),
    "BUILD_T_UPGRADE_CENTER":       (0, 3, True),
    "BUILD_T_UPGRADE_SIDELANE":     (1, 4, True),
    "BUILD_T_UPGRADE_OUTER_MID":    (2, 5, True),
    "BUILD_T_UPGRADE_OUTER_EDGE":   (3, 7, True),
    "BUILD_T_UPGRADE_INNER_CORNER": (5, 9, True),
    "BUILD_T_OUTER_CORNERS":        (8, 13, True),
    "HOARD_T_START":                (3, 7, True),
    "HOARD_T_UPGRADE_SECOND_RING":  (6, 11, True),
    "HOARD_T_THIRD_RING":           (10, 15, True),
    "HOARD_T_WALL_UPGRADES":        (12, 18, True),
    "HOARD_T_OUTER_RING":           (16, 24, True),
    "UPGRADE_HP_PCT":               (0.3, 0.85, False),
}

KNOB_ORDER = list(CONFIG.keys())
