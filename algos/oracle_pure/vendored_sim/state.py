"""SimCore state containers.

Players: we follow the raw-JSON convention that matches how replays serialize:
  p1 = 1 (first player / bottom side)
  p2 = 2 (second player / top side)

Coordinates: diamond map, 28x28 grid. Player 1 owns y in [0, 13]; player 2
owns y in [14, 27]. Off-board tiles outside the diamond are never valid; use
sim.map.on_diamond(x, y).
"""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import Any, Dict, List, Optional, Set, Tuple

import numpy as np

from .config import (
    IDX_SUPPORT,
    IDX_TURRET,
    IDX_WALL,
    IDX_SCOUT,
    IDX_DEMOLISHER,
    IDX_INTERCEPTOR,
    SimConfig,
    StructureSpec,
    MobileSpec,
)

# Every HP/shield/SP/MP field below is a numpy.float32, matching engine's
# Java-float 32-bit representation. Python's default `float` type is a
# 64-bit double; storing these as Python floats silently widens across
# arithmetic and breaks bit-exact parity with engine.jar on accumulated
# values (scout HP after N damage ticks, support shield at Y=13, etc.).
# Dataclass type hints stay `float` because numpy.float32 isn't a
# generic-friendly type; the invariant is enforced by config.py (spec
# constants) + pysim.py (arithmetic sites) + validate.py
# (_build_state_from_deploy_frame casts at load), and guarded by
# tests/test_float32_propagation.py. Any site that reassigns one of
# these fields MUST route through numpy.float32.
FP32 = np.float32


# ---------------------------------------------------------------- units

@dataclass(slots=True)
class Structure:
    """Live structure in the simulation. Snapshot from a replay's deploy frame
    OR one created via `attempt_spawn` during SimCore deploy-replay."""
    xy: Tuple[int, int]
    type_idx: int
    upgraded: bool
    # hp: np.float32 at runtime (parity with engine.jar Java-float 32-bit).
    # Annotated Any so mypyc will compile modules that mutate this without
    # rejecting the float32 ⇄ Python-float type mixing. Runtime precision
    # is enforced by tests/test_float32_propagation.py.
    hp: Any
    uid: str               # unit_id from the replay (never re-used)
    player: int            # 1 or 2
    # Engine's RefundComponent.turnStartRemoval (see RefundComponent.java).
    # None = not pending removal; otherwise the integer turn number on which
    # the type-6 removal command was issued. The removal fires at the START
    # of turn (turn_start_removal + turns_required_to_remove), via
    # RemoveOwnUnitSystem. While pending, the replay surfaces a type-6
    # pseudo-unit in p{1,2}Units[6] with hp_slot =
    # turns_required_to_remove - (current_turn - turn_start_removal) —
    # _snapshot_units emits that from this field.
    turn_start_removal: Optional[int] = None
    # Mutated per frame
    shielded_already: Set[str] = field(default_factory=set)  # uids shielded by THIS unit (only for Supports)

    @property
    def max_hp(self) -> float:
        # Caller sets via SimState helper; max_hp is just the spec hp from config.
        raise NotImplementedError("read max_hp via SimState.spec_for(struct).hp")


@dataclass(slots=True)
class Mobile:
    """Live mobile unit mid-action-phase."""
    xy: Tuple[int, int]    # CURRENT tile
    type_idx: int
    hp: Any        # np.float32 at runtime; see Structure.hp note.
    shield: Any    # np.float32 at runtime.
    uid: str
    player: int            # 1 or 2
    # Movement bookkeeping
    spawn_xy: Tuple[int, int]
    target_edge: int       # game_map edge constant (see sim.map)
    # Step counter: number of moves completed so far.
    steps_taken: int = 0
    # Engine uses a float accumulator: `buildup += speed` per frame; move
    # fires and `buildup -= 1.0` when buildup >= 0.9999.
    move_buildup: Any = 0.0  # np.float32 at runtime.
    # `lastMove` hint passed into PathFinder.get_step() — 0=SPAWNED, 1=HORIZONTAL,
    # 2=VERTICAL. Updated after every successful move per engine semantics.
    last_move: int = 0
    # Set by system_move when pathfinder returned the same tile (delta=0).
    finished_navigating: bool = False
    # Set by system_move when finished_navigating AND xy is in nav targets.
    reached_target: bool = False
    # Set by system_breach when the mobile breaches. Engine's BreachSystem
    # calls disableGameObject (BreachSystem.java:28) → attack component
    # isEnabled==false → TargetAndAttackSystem.java:30 skips it from the
    # attacker snapshot. SDed / attack-killed mobiles are NOT disabled
    # and still fire one last shot (engine's `attackWhenDestroyed` gate);
    # only breachers must be explicitly excluded from the attacker list.
    breached: bool = False


# ------------------------------------------------------------ sim state

@dataclass(slots=True)
class PlayerStats:
    hp: Any  # np.float32 at runtime.
    sp: Any  # np.float32 at runtime.
    mp: Any  # np.float32 at runtime.


@dataclass
class SimState:
    """Full mutable state across an action-phase simulation.

    UIDS come exclusively from the replay's spawn events. SimCore never
    allocates uids; doing so would diverge from engine.jar's monotonic
    Game.idCount counter and cause every event-join column in the Phase
    1.B validator to show uid-mismatch noise. apply_deploy_actions (in
    pysim.py) takes the engine-assigned uid directly from each spawn
    event's third field (`ev[2]`) and propagates it to the created
    Structure/Mobile. See engine.jar's Game.getNewID
    (research/engine_decompiled/sources/.../Game.java:132) for the
    reference counter."""
    turn: int
    # Structures indexed by (x, y). Engine allows only one structure per tile.
    structures: Dict[Tuple[int, int], Structure]
    # Mobiles in insertion order (engine uses ArrayList → iteration order matters
    # for targeting ties; we preserve it).
    mobiles: List[Mobile]
    p1: PlayerStats
    p2: PlayerStats
    # The 4 per-edge PathFinder instances (see sim.pathfinder). Populated by
    # `init_pathfinders()` after the initial structures are in place and
    # kept in sync thereafter via put()/remove() calls from
    # apply_deploy_actions + clear_destroyed.
    pathfinders: Optional[dict] = None
    # Fast-path set for system_remove_own_unit: xy tiles whose structure has
    # turn_start_removal set. Kept in sync wherever turn_start_removal is
    # mutated (type-6 in apply_deploy_actions, upgrade reset, deploy-frame
    # reconstruction, and the refund destroy). Empty set → system_remove_own
    # _unit is a no-op immediately; avoids O(structures) scan per frame for
    # the common case of zero pending removals.
    pending_removal_xys: Set[Tuple[int, int]] = field(default_factory=set)
    # Perf: count of structural mutations (add/remove) since construction.
    # `system_attack` hot path caches a per-turret list of in-range enemy
    # structures keyed by this generation. When the generation changes,
    # the cache is invalidated and rebuilt. Saves ~70 frames × 20 turrets
    # × 54 enemy-struct iterations per sim on mid_game_108_struct_5_mob.
    _structure_gen: int = 0
    # Cache of (turret, enemy_structs_in_range) populated lazily by
    # system_attack. Rebuilt when _structure_gen changes.
    _attack_struct_cache: Any = None
    # Cache of (target_edge, xy, last_move) → next-step-tile populated
    # lazily by system_move. Pathfinder.get_step output is a pure
    # function of those keys PLUS the pathfinder's wall set, which
    # changes ONLY when structures are added/removed (tracked by
    # _structure_gen). Invalidated on gen change. Saves ~8us/call
    # over ~70 frames × ~2 moving mobiles per frame.
    _move_step_cache: Any = None
    # Cache of per-player turret & structure lists keyed on
    # _structure_gen. System_attack rebuilds these lists every frame
    # by scanning all 108 structures; the split result is invariant
    # while structures don't change. Cached here as a 5-tuple
    # (turrets_p1, turrets_p2, structs_p1, structs_p2, gen) with gen
    # being the _structure_gen at build time. Invalidation = gen
    # mismatch. Mobiles per-player still rebuild every frame (they
    # move, not just die). Saves ~200 PyObject_GetAttr calls per
    # frame on mid_game_108_struct_5_mob.
    _attack_split_cache: Any = None

    # --- helpers ---

    def struct_at(self, xy: Tuple[int, int]) -> Optional[Structure]:
        return self.structures.get(xy)

    def mobiles_at(self, xy: Tuple[int, int]) -> List[Mobile]:
        return [m for m in self.mobiles if m.xy == xy]

    def remove_structure(self, s: Structure) -> None:
        if self.structures.get(s.xy) is s:
            del self.structures[s.xy]
            self._structure_gen += 1
            if self.pathfinders is not None:
                for pf in self.pathfinders.values():
                    pf.remove(s.xy[0], s.xy[1])

    def is_occupied(self, xy: Tuple[int, int]) -> bool:
        return xy in self.structures

    def player_stats(self, player: int) -> PlayerStats:
        return self.p1 if player == 1 else self.p2

    def enemy_player(self, player: int) -> int:
        return 2 if player == 1 else 1


@dataclass
class ActionResult:
    """Returned from simulate_action_phase. All damage values are cumulative
    across the whole action phase."""
    final_state: SimState
    p1_damage_dealt: float       # HP damage dealt to p2
    p2_damage_dealt: float       # HP damage dealt to p1
    structure_damage_by_player: Dict[int, float] = field(default_factory=dict)
    # Per-frame event log — same shape as a replay's events dict but
    # accumulated across frames. Tuple entries mirror raw-JSON shapes where
    # practical so we can diff against replays directly.
    frame_events: List[Dict] = field(default_factory=list)
    frame_count: int = 0

    @property
    def winner(self) -> Optional[int]:
        if self.final_state.p1.hp <= 0 and self.final_state.p2.hp > 0:
            return 2
        if self.final_state.p2.hp <= 0 and self.final_state.p1.hp > 0:
            return 1
        if self.final_state.p1.hp > self.final_state.p2.hp:
            return 1
        if self.final_state.p2.hp > self.final_state.p1.hp:
            return 2
        return None
