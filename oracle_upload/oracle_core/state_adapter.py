"""gamelib.GameState -> sim_rs state-dict adapter (Phase 5 milestone A).

Phase 4 left an explicit gap: `_gamelib_to_simdict` in the offense_only
variant produced a state dict that was missing UID fidelity and used
`getattr(u, 'health', 1.0)` rather than runtime config — so feeding it
into `simulate_action_phase_py` would crash. This module closes that gap.

Public API:
    adapt_game_state(game_state, *, my_player=1, turn=0)
        -> dict in the sim_rs schema (see py_state_to_dict for shape).
    augment_snapshot_for_simcore(snapshot_path)
        -> idempotent in-place mutation that adds SimCore-required keys.

Usage in `algo_strategy.py`:
    state_dict = adapt_game_state(game_state, my_player=1, turn=self.turn_count)
    best = beam_search(state_dict, candidates, opp_actions_top_k=opps,
                       config_path=self.snapshot_path, ...)

Design notes:
- All HP/cost/etc numerics are read from `game_state.config["unitInformation"]`
  (the runtime config, authoritative). We never hardcode unit stats.
- Synthesized UIDs are unique per turn, large enough to not collide with
  any plausible engine UID for the structures we observe.
- `target_edge` for opponent mobiles is best-effort: we don't have access
  to the engine's actual destination (the spawn-edge classification only),
  so we fall back to the standard mapping per spawn tile.
"""
from __future__ import annotations

import json
import os
from pathlib import Path
from typing import Any, Dict, List, Optional


# Type-index mapping (matches sim_rs config.rs IDX_* constants and the
# `_raw_unit_information` array order: 0=FF, 1=EF, 2=DF, 3=PI, 4=EI, 5=SI).
_SHORTHAND_TO_IDX: Dict[str, int] = {
    "FF": 0, "EF": 1, "DF": 2, "PI": 3, "EI": 4, "SI": 5,
    # Doc-level renames — same indices.
    "WALL": 0, "SUPPORT": 1, "TURRET": 2,
    "SCOUT": 3, "DEMOLISHER": 4, "INTERCEPTOR": 5,
}


# Edge constants (sim/map.py convention): 0=BL, 1=BR, 2=TL, 3=TR.
_EDGE_BL = 0
_EDGE_BR = 1
_EDGE_TL = 2
_EDGE_TR = 3


def _spawn_to_target_edge(x: int, y: int, player: int) -> int:
    """Classify a mobile's spawn tile to its target edge.

    Bottom player (player=1):
      - BL spawn: y == 13 - x  -> targets TR (3)
      - BR spawn: y == x - 14  -> targets TL (2)
    Top player (player=2):
      - TL spawn: y == 41 - x  -> targets BR (1) ... actually targets BL? Wait.
      - TR spawn: y == 14 + x  -> targets BL (0)
    NOTE: the original sim_eval code uses 'y == 14 + x => BR (1)' and
    'y == 41 - x => BL (0)' for top player — keep that convention so the
    sim's pathfinder gives the same answer.
    """
    if player == 1:
        if y == 13 - x:
            return _EDGE_TR
        if y == x - 14:
            return _EDGE_TL
        return _EDGE_TR  # default
    else:
        if y == 14 + x:
            return _EDGE_BR
        if y == 41 - x:
            return _EDGE_BL
        return _EDGE_BL


def _structure_hp_default(config: Dict[str, Any], type_idx: int, upgraded: bool) -> float:
    """Read the canonical `startHealth` for this (type_idx, upgraded) from
    runtime config. Used only as a fallback when the gamelib unit doesn't
    expose a sensible `health` attribute.
    """
    try:
        ui = config["unitInformation"][type_idx]
        if upgraded and "upgrade" in ui and "startHealth" in ui["upgrade"]:
            return float(ui["upgrade"]["startHealth"])
        return float(ui["startHealth"])
    except (KeyError, IndexError, TypeError, ValueError):
        return 1.0


def adapt_game_state(
    game_state,
    *,
    my_player: int = 1,
    turn: Optional[int] = None,
) -> Dict[str, Any]:
    """Build a sim_rs-schema state dict from a live gamelib.GameState.

    Returns a dict shaped like ``offense.sim_eval.py_state_to_dict``:

        {
          "turn": int,
          "p1": {"hp": float, "sp": float, "mp": float},
          "p2": {"hp": float, "sp": float, "mp": float},
          "structures": [<one entry per structure>],
          "mobiles": [<one entry per mobile>],
        }

    The returned dict is independently mutable — beam_search will deepcopy
    it as needed.
    """
    cfg = game_state.config

    # Resources
    SP_IDX = 0
    MP_IDX = 1
    try:
        p1_sp = float(game_state.get_resource(SP_IDX, 0))
        p1_mp = float(game_state.get_resource(MP_IDX, 0))
        p2_sp = float(game_state.get_resource(SP_IDX, 1))
        p2_mp = float(game_state.get_resource(MP_IDX, 1))
    except Exception:  # noqa: BLE001
        p1_sp = p1_mp = p2_sp = p2_mp = 0.0

    # HP (gamelib exposes my_health/enemy_health; player_index 0 = self)
    try:
        my_hp = float(getattr(game_state, "my_health", 0.0))
        opp_hp = float(getattr(game_state, "enemy_health", 0.0))
    except Exception:  # noqa: BLE001
        my_hp, opp_hp = 0.0, 0.0
    if my_player == 1:
        p1_hp, p2_hp = my_hp, opp_hp
    else:
        p1_hp, p2_hp = opp_hp, my_hp

    # Walk the arena board for stationary + mobile units.
    # gamelib's GameMap is iterable via `[x, y]` per-tile lookup.
    structures: List[Dict[str, Any]] = []
    mobiles: List[Dict[str, Any]] = []

    next_uid = 1_000_000  # synth UIDs starting well above any plausible engine UID
    seen_uids: set = set()
    try:
        game_map = game_state.game_map
    except Exception:  # noqa: BLE001
        game_map = None

    if game_map is not None:
        for x in range(28):
            for y in range(28):
                try:
                    if not game_map.in_arena_bounds([x, y]):
                        continue
                    cell = game_map[x, y]
                except Exception:  # noqa: BLE001
                    continue
                if not cell:
                    continue
                for u in cell:
                    try:
                        sh = str(u.unit_type)
                        type_idx = _SHORTHAND_TO_IDX.get(sh.upper())
                        if type_idx is None:
                            # Unknown; skip rather than poison the sim.
                            continue
                        upgraded = bool(getattr(u, "upgraded", False))
                        # gamelib uses player_index 0=self, 1=opp.
                        gl_pi = int(getattr(u, "player_index", 0))
                        # Convert to sim convention (1=p1 bottom, 2=p2 top)
                        # from `my_player`'s frame of reference.
                        if my_player == 1:
                            sim_player = 1 if gl_pi == 0 else 2
                        else:
                            sim_player = 2 if gl_pi == 0 else 1
                        hp = float(getattr(u, "health", None) or _structure_hp_default(cfg, type_idx, upgraded))
                        # Stationary -> structure, else mobile
                        is_stat = bool(getattr(u, "stationary", type_idx in (0, 1, 2)))
                        engine_uid = getattr(u, "unit_id", None)
                        if (engine_uid is not None
                                and str(engine_uid).isdigit()
                                and str(engine_uid) not in seen_uids):
                            uid_str = str(engine_uid)
                        else:
                            uid_str = str(next_uid)
                            next_uid += 1
                        seen_uids.add(uid_str)
                        if is_stat:
                            structures.append({
                                "xy": [int(x), int(y)],
                                "type_idx": int(type_idx),
                                "upgraded": bool(upgraded),
                                "hp": float(hp),
                                "uid": uid_str,
                                "player": int(sim_player),
                                "turn_start_removal": None,
                            })
                        else:
                            # Default shield to 0 (will be picked up by Support
                            # phase in sim if applicable).
                            mobiles.append({
                                "xy": [int(x), int(y)],
                                "type_idx": int(type_idx),
                                "hp": float(hp),
                                "shield": 0.0,
                                "uid": uid_str,
                                "player": int(sim_player),
                                "spawn_xy": [int(x), int(y)],
                                "target_edge": _spawn_to_target_edge(int(x), int(y), int(sim_player)),
                                "steps_taken": 0,
                                "move_buildup": 0.0,
                                "last_move": 0,
                                "finished_navigating": False,
                                "reached_target": False,
                                "breached": False,
                            })
                    except Exception:  # noqa: BLE001
                        # Defensive: never crash the adapter on a single unit.
                        continue

    return {
        "turn": int(turn if turn is not None else getattr(game_state, "turn_number", 0)),
        "p1": {"hp": float(p1_hp), "sp": float(p1_sp), "mp": float(p1_mp)},
        "p2": {"hp": float(p2_hp), "sp": float(p2_sp), "mp": float(p2_mp)},
        "structures": structures,
        "mobiles": mobiles,
    }


def augment_snapshot_for_simcore(snapshot_path: str) -> bool:
    """Idempotent: ensure `_raw_unit_information` and `_resources_block_verbatim`
    keys exist in the snapshot at ``snapshot_path``.

    Safe to call at startup. If the keys already exist, no I/O is done past
    the read. Returns True if the file was modified, False otherwise.
    """
    p = Path(snapshot_path)
    if not p.exists():
        return False
    try:
        with p.open() as f:
            data = json.load(f)
    except Exception:  # noqa: BLE001
        return False
    changed = False
    if "_raw_unit_information" not in data and "unitInformation" in data:
        data["_raw_unit_information"] = data["unitInformation"]
        changed = True
    if "_resources_block_verbatim" not in data and "resources" in data:
        data["_resources_block_verbatim"] = data["resources"]
        changed = True
    if changed:
        try:
            tmp = str(p) + ".tmp"
            with open(tmp, "w") as f:
                json.dump(data, f, indent=2)
                f.write("\n")
            os.replace(tmp, str(p))
        except Exception:  # noqa: BLE001
            return False
    return changed


__all__ = [
    "adapt_game_state",
    "augment_snapshot_for_simcore",
]
