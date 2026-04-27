"""Citadel constants used throughout oracle_pure.

Pre-rename shorthands (FF/EF/DF/PI/EI/SI) are what the live server
config still ships, but oracle_core code refers to units by canonical
type indices (0..5) and looks up shorthand strings at runtime. We
NEVER hardcode unit stats — those are read from game_state.config
or from data/citadel_config_snapshot.json.
"""
from __future__ import annotations

# Type-index mapping (matches sim_rs config.rs IDX_* constants and
# `_raw_unit_information` array order).
WALL_IDX = 0
SUPPORT_IDX = 1
TURRET_IDX = 2
SCOUT_IDX = 3
DEMOLISHER_IDX = 4
INTERCEPTOR_IDX = 5
REMOVE_IDX = 6
UPGRADE_IDX = 7

STRUCTURE_IDXS = (WALL_IDX, SUPPORT_IDX, TURRET_IDX)
MOBILE_IDXS = (SCOUT_IDX, DEMOLISHER_IDX, INTERCEPTOR_IDX)

# Resource indices in gamelib.GameState.get_resource(idx, player).
SP_RES = 0
MP_RES = 1

# Arena geometry.
ARENA_DIM = 28
ARENA_HALF_Y = 14  # y < 14 = bottom half (ours)

# All bottom-half spawn-edge tiles.
# BL: y == 13 - x for x in [0..13]
# BR: y == x - 14 for x in [14..27]
BOTTOM_LEFT_EDGE = [(x, 13 - x) for x in range(14)]
BOTTOM_RIGHT_EDGE = [(x, x - 14) for x in range(14, 28)]
ALL_SPAWN_EDGES = BOTTOM_LEFT_EDGE + BOTTOM_RIGHT_EDGE

# Citadel default mobile HP (read from config in practice; used as fallback).
MOBILE_HP_DEFAULT = {SCOUT_IDX: 15.0, DEMOLISHER_IDX: 5.0, INTERCEPTOR_IDX: 40.0}

# Default mobile MP cost (Citadel; verify at runtime).
MOBILE_MP_COST_DEFAULT = {SCOUT_IDX: 1.0, DEMOLISHER_IDX: 3.0, INTERCEPTOR_IDX: 1.0}

# Default structure SP cost / upgrade cost (Citadel; verify at runtime).
STRUCT_SP_COST_DEFAULT = {WALL_IDX: 1.0, SUPPORT_IDX: 4.0, TURRET_IDX: 2.0}
STRUCT_UPG_COST_DEFAULT = {WALL_IDX: 2.0, SUPPORT_IDX: 4.0, TURRET_IDX: 4.0}

# Refund coefficients (Citadel).
REFUND_BASE = 0.9
REFUND_UPGRADED = 0.8


def shorthand_for_idx(config, idx: int) -> str:
    """Return the shorthand string the engine expects for `attempt_spawn`."""
    try:
        return str(config["unitInformation"][idx]["shorthand"])
    except (KeyError, IndexError, TypeError):
        # Pre-rename Citadel default
        return ["FF", "EF", "DF", "PI", "EI", "SI"][idx]


def cost_for_idx(config, idx: int, upgrade: bool = False) -> float:
    """Return SP/MP cost for spawning or upgrading a unit, from runtime config.

    For mobile units (3..5), idx maps to MP cost (cost1).
    For structures (0..2), idx maps to SP cost (cost1) for spawn, or
    `upgrade.cost1` for upgrade.
    """
    try:
        ui = config["unitInformation"][idx]
        if upgrade:
            return float(ui.get("upgrade", {}).get("cost1", STRUCT_UPG_COST_DEFAULT.get(idx, 0)))
        # cost1 for structures, cost2 for mobiles in the Citadel config
        if "cost1" in ui:
            return float(ui["cost1"])
        if "cost2" in ui:
            return float(ui["cost2"])
    except (KeyError, IndexError, TypeError, ValueError):
        pass
    if idx in STRUCT_SP_COST_DEFAULT:
        return STRUCT_SP_COST_DEFAULT[idx]
    return MOBILE_MP_COST_DEFAULT.get(idx, 0)


def starting_hp_for_idx(config, idx: int, upgrade: bool = False) -> float:
    try:
        ui = config["unitInformation"][idx]
        if upgrade and "upgrade" in ui and "startHealth" in ui["upgrade"]:
            return float(ui["upgrade"]["startHealth"])
        return float(ui.get("startHealth", 0))
    except (KeyError, IndexError, TypeError, ValueError):
        return MOBILE_HP_DEFAULT.get(idx, 60.0)


__all__ = [
    "WALL_IDX", "SUPPORT_IDX", "TURRET_IDX",
    "SCOUT_IDX", "DEMOLISHER_IDX", "INTERCEPTOR_IDX",
    "REMOVE_IDX", "UPGRADE_IDX",
    "STRUCTURE_IDXS", "MOBILE_IDXS",
    "SP_RES", "MP_RES",
    "ARENA_DIM", "ARENA_HALF_Y",
    "BOTTOM_LEFT_EDGE", "BOTTOM_RIGHT_EDGE", "ALL_SPAWN_EDGES",
    "MOBILE_HP_DEFAULT", "MOBILE_MP_COST_DEFAULT",
    "STRUCT_SP_COST_DEFAULT", "STRUCT_UPG_COST_DEFAULT",
    "REFUND_BASE", "REFUND_UPGRADED",
    "shorthand_for_idx", "cost_for_idx", "starting_hp_for_idx",
]
