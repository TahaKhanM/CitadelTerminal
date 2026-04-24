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
from typing import Dict, List, Optional, Set, Tuple

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


# ---------------------------------------------------------------- units

@dataclass
class Structure:
    """Live structure in the simulation. Snapshot from a replay's deploy frame
    OR one created via `attempt_spawn` during SimCore deploy-replay."""
    xy: Tuple[int, int]
    type_idx: int
    upgraded: bool
    hp: float
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


@dataclass
class Mobile:
    """Live mobile unit mid-action-phase."""
    xy: Tuple[int, int]    # CURRENT tile
    type_idx: int
    hp: float
    shield: float
    uid: str
    player: int            # 1 or 2
    # Movement bookkeeping
    spawn_xy: Tuple[int, int]
    target_edge: int       # game_map edge constant (see sim.map)
    # Step counter: number of moves completed so far.
    steps_taken: int = 0
    # Engine uses a float accumulator: `buildup += speed` per frame; move
    # fires and `buildup -= 1.0` when buildup >= 0.9999.
    move_buildup: float = 0.0
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

@dataclass
class PlayerStats:
    hp: float
    sp: float
    mp: float


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

    # --- helpers ---

    def struct_at(self, xy: Tuple[int, int]) -> Optional[Structure]:
        return self.structures.get(xy)

    def mobiles_at(self, xy: Tuple[int, int]) -> List[Mobile]:
        return [m for m in self.mobiles if m.xy == xy]

    def remove_structure(self, s: Structure) -> None:
        if self.structures.get(s.xy) is s:
            del self.structures[s.xy]
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
