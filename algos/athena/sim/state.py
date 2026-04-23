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
    pending_removal: bool = False
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
    # Step counter: number of moves completed so far (sub-frame granularity OK).
    steps_taken: int = 0
    # Engine uses a float accumulator: `buildup += speed` per frame; move
    # fires and `buildup -= 1.0` when buildup >= 0.9999.
    move_buildup: float = 0.0
    # Cache of the full remaining path (list of (x,y)), re-computed when
    # structures change or the unit reaches end of path.
    path: List[Tuple[int, int]] = field(default_factory=list)
    # Already-applied shielders — engine uses one HashSet per Support; we
    # instead track "has been shielded by UID X" per mobile so the set lookup
    # is symmetric to the engine's model (applied on Support side).
    shielded_by: Set[str] = field(default_factory=set)
    # Set by system_move when pathfinder returned same-tile.
    finished_navigating: bool = False
    # Set by system_move when finished_navigating AND xy is in target-edge set.
    reached_target: bool = False


# ------------------------------------------------------------ sim state

@dataclass
class PlayerStats:
    hp: float
    sp: float
    mp: float


@dataclass
class SimState:
    """Full mutable state across an action-phase simulation."""
    turn: int
    # Structures indexed by (x, y). Engine allows only one structure per tile.
    structures: Dict[Tuple[int, int], Structure]
    # Mobiles in insertion order (engine uses ArrayList → iteration order matters
    # for targeting ties; we preserve it).
    mobiles: List[Mobile]
    p1: PlayerStats
    p2: PlayerStats
    # Monotonic unit-id counter for new mobile/structure ids
    _next_id: int = 1_000_000

    # --- helpers ---

    def alloc_id(self) -> str:
        i = self._next_id
        self._next_id += 1
        return f"sim{i}"

    def struct_at(self, xy: Tuple[int, int]) -> Optional[Structure]:
        return self.structures.get(xy)

    def mobiles_at(self, xy: Tuple[int, int]) -> List[Mobile]:
        return [m for m in self.mobiles if m.xy == xy]

    def remove_structure(self, s: Structure) -> None:
        if self.structures.get(s.xy) is s:
            del self.structures[s.xy]

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
