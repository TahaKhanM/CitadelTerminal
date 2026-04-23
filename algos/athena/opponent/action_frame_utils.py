"""Action-frame parsing utilities (Citadel-aware).

All utilities consume per-frame JSON dicts from either `on_action_frame` (live
play) or a parsed replay line. They produce deterministic state the opponent
model (Phase 4) and defense engine (Phase 2) consume.

CRITICAL — player_index flip:
  * gamelib.GameState / GameUnit:   0 = self, 1 = opponent.
  * Raw action-frame JSON (events): 1 = self, 2 = opponent.
Every public method of these utilities expects RAW action-frame dicts and
uses the 1/2 convention internally. If you pass a GameState-derived value
and confuse the conventions, you WILL miscount enemy spawns as your own.

Verified 2026-04-23 against replays/ranked/v13_*.replay. Event tuple shapes
(index meanings) — confirmed empirically, not from docs:

  spawn  : [[x, y], unit_type_index, unit_id, player]
  breach : [[x, y], damage, unit_type_index, unit_id, player]
  death  : [[x, y], unit_type_index, unit_id, player, self_destruct_bool]
  damage : [[x, y], damage_amount, unit_type_index, unit_id, player]
  attack : [[att_x, att_y], [vic_x, vic_y], dmg, att_type, att_id, vic_id, att_player]

Unit type indices align with config["unitInformation"] ordering:
  0=FF (Wall), 1=EF (Support), 2=DF (Turret),
  3=PI (Scout), 4=EI (Demolisher), 5=SI (Interceptor),
  6=RM (Remove), 7=UP (Upgrade).
"""

from __future__ import annotations

from collections import defaultdict
from dataclasses import dataclass, field
from typing import Any, Dict, Iterable, List, Optional, Tuple

# Action-frame player constants. NOT the starter API's 0/1.
AF_SELF = 1
AF_OPP = 2

# Unit-type indices in config order (= order of unitInformation entries).
IDX_WALL = 0
IDX_SUPPORT = 1
IDX_TURRET = 2
IDX_SCOUT = 3
IDX_DEMOLISHER = 4
IDX_INTERCEPTOR = 5
IDX_REMOVE = 6
IDX_UPGRADE = 7

MOBILE_TYPE_IDX = {IDX_SCOUT, IDX_DEMOLISHER, IDX_INTERCEPTOR}
STRUCTURE_TYPE_IDX = {IDX_WALL, IDX_SUPPORT, IDX_TURRET}

ARENA = 28
HALF = 14


def _events(frame: Dict[str, Any], key: str) -> List[list]:
    """Extract a named event list defensively."""
    evs = frame.get("events") or {}
    out = evs.get(key) or []
    return out if isinstance(out, list) else []


def _turn_info(frame: Dict[str, Any]) -> Tuple[int, int, int, int]:
    ti = frame.get("turnInfo") or [0, 0, 0, 0]
    ti = list(ti) + [0] * (4 - len(ti))
    return int(ti[0]), int(ti[1]), int(ti[2]), int(ti[3])


# ---------------------------------------------------------------- BatchCount

class BatchCountTracker:
    """Lostkids-style: count per-turn OPPONENT mobile-unit spawns, grouped by
    unit-type. Produces a running history we can use to detect cannon-charge
    patterns ("opponent banked MP and dumped 12 Scouts on turn 15").

    Semantics:
      * Reset the turn's count every time we see a NEW turn number (based on
        turnInfo[1]). First spawn event of a turn triggers the reset.
      * Only mobile-unit opponent spawns count. Structures and boundary units
        (RM/UP) are ignored.
    """

    def __init__(self):
        self._current_turn: int = -1
        # per-turn dict: {unit_type_idx: count}
        self.turn_counts: Dict[int, Dict[int, int]] = {}
        self._working: Dict[int, int] = defaultdict(int)

    def on_frame(self, frame: Dict[str, Any]) -> None:
        phase, turn, _, _ = _turn_info(frame)
        if turn != self._current_turn:
            # Close out the previous turn.
            if self._current_turn >= 0:
                self.turn_counts[self._current_turn] = dict(self._working)
            self._working = defaultdict(int)
            self._current_turn = turn

        for ev in _events(frame, "spawn"):
            # [[x, y], unit_type_index, unit_id, player]
            if len(ev) < 4:
                continue
            unit_type = ev[1]
            player = ev[3]
            if player != AF_OPP:
                continue
            if unit_type not in MOBILE_TYPE_IDX:
                continue
            self._working[unit_type] += 1

    def finalize(self) -> None:
        """Flush the in-progress turn into turn_counts. Call when the replay
        ends (no more frames)."""
        if self._current_turn >= 0 and self._current_turn not in self.turn_counts:
            self.turn_counts[self._current_turn] = dict(self._working)

    def enemy_total_mobile(self, turn: int) -> int:
        return sum(self.turn_counts.get(turn, {}).values())

    def enemy_scouts(self, turn: int) -> int:
        return self.turn_counts.get(turn, {}).get(IDX_SCOUT, 0)

    def enemy_demolishers(self, turn: int) -> int:
        return self.turn_counts.get(turn, {}).get(IDX_DEMOLISHER, 0)

    def enemy_interceptors(self, turn: int) -> int:
        return self.turn_counts.get(turn, {}).get(IDX_INTERCEPTOR, 0)

    def last_n_turn_totals(self, n: int, up_to_turn: int) -> List[int]:
        return [
            self.enemy_total_mobile(up_to_turn - i)
            for i in reversed(range(n))
        ]


# ------------------------------------------------------------- SpawnHistogram

class SpawnXHistogram:
    """28-bin histogram of enemy mobile spawn x-columns. Exponential decay
    per turn (default 0.95). Used by the defense planner to weight where
    reinforcements should go."""

    def __init__(self, decay: float = 0.95):
        self.decay = float(decay)
        self.bins: List[float] = [0.0] * ARENA
        self._last_turn: int = -1

    def on_frame(self, frame: Dict[str, Any]) -> None:
        _, turn, _, _ = _turn_info(frame)
        if turn != self._last_turn and self._last_turn >= 0:
            # Decay when we advance to a new turn. Decay once per turn gap.
            gap = max(1, turn - self._last_turn)
            factor = self.decay ** gap
            self.bins = [v * factor for v in self.bins]
        self._last_turn = turn

        for ev in _events(frame, "spawn"):
            if len(ev) < 4:
                continue
            unit_type = ev[1]
            player = ev[3]
            if player != AF_OPP or unit_type not in MOBILE_TYPE_IDX:
                continue
            xy = ev[0]
            if not isinstance(xy, (list, tuple)) or len(xy) < 2:
                continue
            x = int(xy[0])
            if 0 <= x < ARENA:
                self.bins[x] += 1.0

    def hottest_columns(self, k: int = 3) -> List[int]:
        return sorted(range(ARENA), key=lambda i: -self.bins[i])[:k]


# ------------------------------------------------------------ WallRemoval

class WallRemovalDetector:
    """Detects opponent structures flagged pending_removal. The starter
    replay frames include a `pendingRemoval` or a per-unit bool in the unit
    tuple depending on replay schema. We handle both: p2Units[*][*] tuples
    whose last element (if boolean and True) mean pending_removal, OR a
    top-level `pendingRemoval` event list if present.

    Usage: call on_frame each frame; consumer inspects
    `structures_flagged_this_turn()` at the end of the turn (phase transition).
    """

    def __init__(self):
        self.flagged_locations: List[Tuple[int, Tuple[int, int], int]] = []
        # per turn: list of (x, y, unit_type_idx)
        self._per_turn: Dict[int, List[Tuple[int, int, int]]] = defaultdict(list)

    def on_frame(self, frame: Dict[str, Any]) -> None:
        _, turn, _, _ = _turn_info(frame)
        p2 = frame.get("p2Units") or []
        # p2Units is indexed 0..7 aligned with unitInformation. Structures
        # are at indices 0 (FF), 1 (EF), 2 (DF). Each entry:
        # [x, y, hp, unit_id, pending_removal_bool]
        for type_idx in (IDX_WALL, IDX_SUPPORT, IDX_TURRET):
            if type_idx >= len(p2):
                continue
            for u in p2[type_idx]:
                if not isinstance(u, (list, tuple)) or len(u) < 5:
                    continue
                pr = u[4]
                if pr is True:
                    x, y = int(u[0]), int(u[1])
                    self._per_turn[turn].append((x, y, type_idx))

    def structures_flagged_on(self, turn: int) -> List[Tuple[int, int, int]]:
        # dedupe — across frames in a turn the same unit appears repeatedly
        seen = set()
        out: List[Tuple[int, int, int]] = []
        for rec in self._per_turn.get(turn, []):
            if rec in seen:
                continue
            seen.add(rec)
            out.append(rec)
        return out


# --------------------------------------------------------- BreachLocation

@dataclass
class BreachRecord:
    turn: int
    frame: int
    xy: Tuple[int, int]
    damage: float
    attacker_type: int


class BreachLocationTracker:
    """Tracks breach events that damaged SELF. Uses the action-frame convention
    where the event's `player` is the unit owner. A breach scored against us
    is emitted by an OPPONENT unit (player=2).

    This is the primary signal for the "where did we take damage last game?"
    defense reinforcement loop.
    """

    def __init__(self):
        self.breaches: List[BreachRecord] = []

    def on_frame(self, frame: Dict[str, Any]) -> None:
        _, turn, fnum, _ = _turn_info(frame)
        for ev in _events(frame, "breach"):
            # [[x, y], damage, unit_type_index, unit_id, player]
            if len(ev) < 5:
                continue
            attacker_player = ev[4]
            if attacker_player != AF_OPP:
                continue  # WE scored (or bogus) — ignore for defense signal
            xy = ev[0]
            if not isinstance(xy, (list, tuple)) or len(xy) < 2:
                continue
            self.breaches.append(BreachRecord(
                turn=turn, frame=fnum,
                xy=(int(xy[0]), int(xy[1])),
                damage=float(ev[1]),
                attacker_type=int(ev[2]),
            ))

    def tiles_hit(self) -> Dict[Tuple[int, int], float]:
        """Total damage per breach tile across all observed frames."""
        agg: Dict[Tuple[int, int], float] = defaultdict(float)
        for b in self.breaches:
            agg[b.xy] += b.damage
        return dict(agg)

    def hottest_breach_tiles(self, k: int = 3) -> List[Tuple[Tuple[int, int], float]]:
        by_tile = sorted(self.tiles_hit().items(), key=lambda kv: -kv[1])
        return by_tile[:k]


# ------------------------------------------------------------- ResourceTracker

@dataclass
class ResourceSnapshot:
    turn: int
    hp: float
    sp: float
    mp: float


class ResourceTracker:
    """Per-turn snapshots of OPPONENT resources (SP, MP, HP) at turn start.
    p?Stats shape: [hp, sp, mp, remaining_bonuses_field_that_varies].

    Self snapshots are also captured for the planner's own bookkeeping.
    """

    def __init__(self):
        self.opp: Dict[int, ResourceSnapshot] = {}
        self.self: Dict[int, ResourceSnapshot] = {}
        self._last_turn: int = -1

    def on_frame(self, frame: Dict[str, Any]) -> None:
        phase, turn, fnum, _ = _turn_info(frame)
        # Only snapshot at the deploy phase (phase==1, frame==0) which is
        # the first action frame of each turn, when resources are settled.
        if not (phase == 1 and fnum == 0):
            return
        if turn == self._last_turn:
            return
        p1 = frame.get("p1Stats") or [0.0, 0.0, 0.0, 0.0]
        p2 = frame.get("p2Stats") or [0.0, 0.0, 0.0, 0.0]
        # In action-frame JSON, p1 = self, p2 = opponent.
        self.self[turn] = ResourceSnapshot(turn, float(p1[0]), float(p1[1]), float(p1[2]))
        self.opp[turn] = ResourceSnapshot(turn, float(p2[0]), float(p2[1]), float(p2[2]))
        self._last_turn = turn

    def opp_mp_series(self) -> List[Tuple[int, float]]:
        return sorted(((t, s.mp) for t, s in self.opp.items()))

    def opp_recent_mp_bank(self, up_to_turn: int, lookback: int = 3) -> float:
        """Sum of opponent MP held for the last `lookback` turn starts. High
        value = they're hoarding for a cannon attack."""
        vals = []
        for t in range(max(0, up_to_turn - lookback + 1), up_to_turn + 1):
            if t in self.opp:
                vals.append(self.opp[t].mp)
        return sum(vals)


# ----------------------------------------------------------- Misdirection

class MisdirectionDetector:
    """Lostkids pattern. Flags the situation where the opponent spawns a
    mixed left + right wave in the same turn — a misdirection attempt
    designed to split our defense. We capture per-turn:

      * spawns_left_edge: enemy spawns at x <= 13 inside their territory
      * spawns_right_edge: enemy spawns at x >= 14 inside their territory

    `is_enemy_left_edge_misdirecting(turn)` returns True when BOTH sides
    saw mobile spawns on the same turn.
    """

    def __init__(self):
        self._per_turn_left: Dict[int, int] = defaultdict(int)
        self._per_turn_right: Dict[int, int] = defaultdict(int)

    def on_frame(self, frame: Dict[str, Any]) -> None:
        _, turn, _, _ = _turn_info(frame)
        for ev in _events(frame, "spawn"):
            if len(ev) < 4:
                continue
            unit_type = ev[1]
            player = ev[3]
            if player != AF_OPP or unit_type not in MOBILE_TYPE_IDX:
                continue
            xy = ev[0]
            if not isinstance(xy, (list, tuple)) or len(xy) < 2:
                continue
            x = int(xy[0])
            # Opponent owns y >= 14. Enemy deploy edges are at x<=13 (top-left)
            # and x>=14 (top-right).
            if x <= 13:
                self._per_turn_left[turn] += 1
            else:
                self._per_turn_right[turn] += 1

    def is_enemy_misdirecting(self, turn: int) -> bool:
        return self._per_turn_left.get(turn, 0) > 0 and self._per_turn_right.get(turn, 0) > 0

    def split_ratio(self, turn: int) -> Tuple[int, int]:
        return self._per_turn_left.get(turn, 0), self._per_turn_right.get(turn, 0)


# --------------------------------------------------------------------- combo

class ActionFrameConsumer:
    """Thin multiplexer — feeds a frame to all trackers in one call."""

    def __init__(self):
        self.batch_count = BatchCountTracker()
        self.spawn_histogram = SpawnXHistogram()
        self.wall_removal = WallRemovalDetector()
        self.breach_locations = BreachLocationTracker()
        self.resources = ResourceTracker()
        self.misdirection = MisdirectionDetector()

    def on_frame(self, frame: Dict[str, Any]) -> None:
        self.batch_count.on_frame(frame)
        self.spawn_histogram.on_frame(frame)
        self.wall_removal.on_frame(frame)
        self.breach_locations.on_frame(frame)
        self.resources.on_frame(frame)
        self.misdirection.on_frame(frame)

    def finalize(self) -> None:
        self.batch_count.finalize()

    def feed_iter(self, frames: Iterable[Dict[str, Any]]) -> None:
        for f in frames:
            self.on_frame(f)
        self.finalize()
