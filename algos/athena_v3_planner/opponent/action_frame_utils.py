"""Action-frame utilities for the Athena v3 planner.

All classes here consume **raw action-frame JSON** (the dict returned by
``json.loads(turn_string)`` inside ``on_action_frame``). The Citadel
engine numbers players ``1=self, 2=opponent`` in raw frames — this is
the OPPOSITE of ``gamelib.GameState`` (``0=self, 1=opponent``). Each
utility takes ``self_player_id`` explicitly so the caller never has to
hardcode the convention.

Frame schema (relevant fields only)::

    turnInfo : [phase, turn_number, action_frame_index, ...]
       phase 0 = deploy, phase 1 = action, phase 2 = end
    p1Units, p2Units : list of 8 lists keyed by unit_type index
       indices: 0 Wall, 1 Support, 2 Turret,
                3 Scout, 4 Demolisher, 5 Interceptor,
                6 Remove (pending_removal queue),
                7 Upgrade
       Each entry is ``[x, y, hp_or_count, unit_id]``.
    p1Stats, p2Stats : [hp, sp, mp, time_used]
    events.spawn   : [[x,y], unit_type_idx, unit_id, player_id]
    events.breach  : [[x,y], damage_dealt, unit_type, unit_id, player_id]
       player_id is the player whose mobile unit dealt the damage.

Unit-type indices come from the live Citadel config snapshot at
``algos/athena_v3_planner/data/citadel_config_snapshot.json``. Mobile
unit types are {3 Scout, 4 Demolisher, 5 Interceptor}; structures are
{0 Wall, 1 Support, 2 Turret}.

Performance note: every method is O(events) and uses plain lists/dicts
plus optionally numpy. None of these utilities allocate per-frame
beyond a few small ints / tuples. Designed to run inside
``on_action_frame`` without nudging the 15s turn budget.
"""

from __future__ import annotations

from collections import deque
from dataclasses import dataclass, field
from typing import Any, Deque, Dict, List, Optional, Sequence, Set, Tuple

import numpy as np

# ---------------------------------------------------------------------------
# Shared constants — keep in sync with citadel_config_snapshot.json
# ---------------------------------------------------------------------------

# unitInformation indices from the live Citadel config
WALL_IDX: int = 0
SUPPORT_IDX: int = 1
TURRET_IDX: int = 2
SCOUT_IDX: int = 3
DEMOLISHER_IDX: int = 4
INTERCEPTOR_IDX: int = 5
REMOVE_IDX: int = 6
UPGRADE_IDX: int = 7

MOBILE_TYPES: Tuple[int, ...] = (SCOUT_IDX, DEMOLISHER_IDX, INTERCEPTOR_IDX)
STRUCTURE_TYPES: Tuple[int, ...] = (WALL_IDX, SUPPORT_IDX, TURRET_IDX)

ARENA_SIZE: int = 28

# Edge labels (top-down, "self at bottom" frame of reference)
EDGE_BL: str = "BL"  # bottom-left  — self spawn edge
EDGE_BR: str = "BR"  # bottom-right — self spawn edge
EDGE_TL: str = "TL"  # top-left     — opponent spawn edge
EDGE_TR: str = "TR"  # top-right    — opponent spawn edge


def _classify_edge(x: int, y: int) -> Optional[str]:
    """Return ``BL/BR/TL/TR`` for arena-edge coordinates.

    Breach events report a tile coordinate at the moment the unit
    crosses an opponent edge. The Citadel diamond's four edges are the
    diagonals ``y = x`` and ``y = 27 - x`` for both halves; the actual
    tiles a mobile unit can step onto are the corner-adjacent rows.
    Rather than hard-encode every (x,y) on each diagonal, classify by
    the half of the board (top vs bottom = y>=14 vs y<14) and the side
    (left vs right = x<=13 vs x>=14). This is robust to engine quirks
    around diagonal endpoints.
    """
    if y >= 14:
        return EDGE_TL if x <= 13 else EDGE_TR
    return EDGE_BL if x <= 13 else EDGE_BR


def _is_first_action_frame(turn_info: Sequence[int]) -> bool:
    """True iff this is the first frame of the action phase.

    Lostkids' batch-count read happens here: ``turnInfo[0] == 1``
    (action phase) AND ``turnInfo[2] == 0`` (frame index 0). At this
    point all of the turn's spawn events have been emitted and no unit
    has moved yet, so the spawn list reflects the deployment.
    """
    return len(turn_info) >= 3 and turn_info[0] == 1 and turn_info[2] == 0


# ---------------------------------------------------------------------------
# Task 1 — BatchCountTracker
# ---------------------------------------------------------------------------

@dataclass
class BatchCountTracker:
    """Counts opponent spawns per unit type per turn from action-frame JSON.

    Lostkids' batch-count idea: most opponents deploy in 1, 2, or 3
    distinct stacks per turn. Tracking the per-turn distribution lets
    you predict whether the next turn will be a "double" or "triple".

    Implementation: at the first action frame of every turn, scan
    ``events.spawn`` for entries whose ``player_id == opp_player_id``
    and bucket them by unit_type. Output is two parallel records:
    a ``per_turn_counts: Dict[turn -> {type_idx: count}]`` and a
    cumulative ``running_totals: Dict[type_idx -> int]``.

    Player_index flip: ``self_player_id`` defaults to 1 (action-frame
    convention). Pass ``self_player_id=2`` if you ever want to track
    your own spawns (rare; mostly for tests).
    """

    self_player_id: int = 1
    per_turn_counts: Dict[int, Dict[int, int]] = field(default_factory=dict)
    running_totals: Dict[int, int] = field(default_factory=dict)
    # Track which turns we've already counted, so repeated calls
    # within a turn (e.g. testing the same frame twice) don't
    # double-count.
    _counted_turns: set = field(default_factory=set)

    def consume_action_frame(self, frame: Dict[str, Any]) -> None:
        """Process one action-frame JSON dict.

        No-op unless this is the first action frame of a new turn.
        """
        turn_info = frame.get("turnInfo")
        if turn_info is None or not _is_first_action_frame(turn_info):
            return
        turn_number = int(turn_info[1])
        if turn_number in self._counted_turns:
            return
        self._counted_turns.add(turn_number)

        spawns = frame.get("events", {}).get("spawn", [])
        opp_id = 2 if self.self_player_id == 1 else 1
        bucket: Dict[int, int] = {}
        for ev in spawns:
            # ev shape: [[x,y], unit_type_idx, unit_id, player_id]
            try:
                unit_type = int(ev[1])
                player_id = int(ev[3])
            except (IndexError, TypeError, ValueError):
                continue
            if player_id != opp_id:
                continue
            # Filter to types we actually care about (mobile + structure
            # + remove + upgrade are all valid). Skip anything else.
            if unit_type < 0 or unit_type > 7:
                continue
            bucket[unit_type] = bucket.get(unit_type, 0) + 1

        self.per_turn_counts[turn_number] = bucket
        for type_idx, n in bucket.items():
            self.running_totals[type_idx] = self.running_totals.get(type_idx, 0) + n

    # -- queries --------------------------------------------------------

    def total(self, unit_type: int) -> int:
        """Cumulative opponent spawn count for ``unit_type``."""
        return self.running_totals.get(unit_type, 0)

    def turn_count(self, turn: int, unit_type: int) -> int:
        return self.per_turn_counts.get(turn, {}).get(unit_type, 0)

    def turn_total_mobile(self, turn: int) -> int:
        bucket = self.per_turn_counts.get(turn, {})
        return sum(bucket.get(t, 0) for t in MOBILE_TYPES)

    def n_turns_observed(self) -> int:
        return len(self.per_turn_counts)


# ---------------------------------------------------------------------------
# Task 2 — SpawnXHistogram
# ---------------------------------------------------------------------------

@dataclass
class SpawnXHistogram:
    """28-bin running histogram of enemy mobile spawn x-columns.

    Decays by ``decay_per_turn`` (default 0.95) every turn. A column
    that hasn't seen a spawn for many turns slowly fades out, letting
    the planner respond to recent behavior without forgetting old
    patterns entirely.

    The histogram counts only *mobile* enemy spawns (Scout, Demolisher,
    Interceptor). Structures don't tell us anything about attack
    direction.
    """

    self_player_id: int = 1
    decay_per_turn: float = 0.95
    counts: np.ndarray = field(
        default_factory=lambda: np.zeros(ARENA_SIZE, dtype=np.float64)
    )
    _last_turn_decayed: int = -1
    _counted_turns: set = field(default_factory=set)

    def consume_action_frame(self, frame: Dict[str, Any]) -> None:
        turn_info = frame.get("turnInfo")
        if turn_info is None or not _is_first_action_frame(turn_info):
            return
        turn_number = int(turn_info[1])

        # Apply decay once per turn boundary, even if we've already
        # counted this turn (idempotent re-entry safe).
        if turn_number != self._last_turn_decayed:
            self._apply_decay()
            self._last_turn_decayed = turn_number

        if turn_number in self._counted_turns:
            return
        self._counted_turns.add(turn_number)

        opp_id = 2 if self.self_player_id == 1 else 1
        for ev in frame.get("events", {}).get("spawn", []):
            try:
                loc = ev[0]
                unit_type = int(ev[1])
                player_id = int(ev[3])
                x = int(loc[0])
            except (IndexError, TypeError, ValueError):
                continue
            if player_id != opp_id:
                continue
            if unit_type not in MOBILE_TYPES:
                continue
            if 0 <= x < ARENA_SIZE:
                self.counts[x] += 1.0

    # -- queries --------------------------------------------------------

    def _apply_decay(self) -> None:
        if self.decay_per_turn != 1.0:
            self.counts *= self.decay_per_turn

    def spawn_x_distribution(self) -> np.ndarray:
        """Return a copy of the 28-bin histogram (raw counts)."""
        return self.counts.copy()

    def normalized_distribution(self) -> np.ndarray:
        s = float(self.counts.sum())
        if s <= 0.0:
            return np.zeros(ARENA_SIZE, dtype=np.float64)
        return self.counts / s

    def peak_spawn_columns(self, top_k: int = 3) -> List[int]:
        """Return the ``top_k`` x-columns by current weight, descending.

        Ties broken by lower x (deterministic).
        """
        if top_k <= 0:
            return []
        # argsort ascending; stable so ties favor lower x
        order = np.argsort(-self.counts, kind="stable")
        # Drop columns with zero weight — those aren't peaks
        result = [int(x) for x in order[:top_k] if self.counts[int(x)] > 0.0]
        return result


# ---------------------------------------------------------------------------
# Task 3 — WallRemovalDetector
# ---------------------------------------------------------------------------

@dataclass
class WallRemovalDetector:
    """Tracks per-tile pending_removal flags on opponent structures.

    The engine surfaces ``attempt_remove`` queue entries in the
    ``pUnits[REMOVE_IDX]`` slot (index 6) of every action frame: each
    list entry is ``[x, y, count_or_hp, unit_id]``. By scanning that
    slot once per turn we can see which tiles the opponent intends to
    vacate and therefore where they're likely to rebuild — useful for
    predicting the next-turn defense layout.

    Default sliding window: 5 turns. ``frequency(x, y)`` returns the
    number of distinct turns within the window where that tile was
    flagged for removal. ``hot_tiles(min_freq)`` returns tiles flagged
    in at least N window turns, ordered by frequency.

    Player_index flip: takes ``self_player_id`` (default 1, action-frame
    convention) and reads the OPPONENT's units (p2Units when
    self_player_id=1, p1Units when self_player_id=2).
    """

    self_player_id: int = 1
    window_size: int = 5
    # Deque of (turn_number, set_of_tiles_flagged_this_turn).
    _window: Deque[Tuple[int, Set[Tuple[int, int]]]] = field(default_factory=deque)
    _scanned_turns: Set[int] = field(default_factory=set)

    def consume_action_frame(self, frame: Dict[str, Any]) -> None:
        turn_info = frame.get("turnInfo")
        if turn_info is None or not _is_first_action_frame(turn_info):
            return
        turn_number = int(turn_info[1])
        if turn_number in self._scanned_turns:
            return
        self._scanned_turns.add(turn_number)

        opp_units_key = "p2Units" if self.self_player_id == 1 else "p1Units"
        opp_units = frame.get(opp_units_key)
        flagged: Set[Tuple[int, int]] = set()
        if isinstance(opp_units, list) and len(opp_units) > REMOVE_IDX:
            removal_queue = opp_units[REMOVE_IDX] or []
            for entry in removal_queue:
                try:
                    x = int(entry[0])
                    y = int(entry[1])
                except (IndexError, TypeError, ValueError):
                    continue
                flagged.add((x, y))

        self._window.append((turn_number, flagged))
        # Trim window
        while len(self._window) > self.window_size:
            self._window.popleft()

    # -- queries --------------------------------------------------------

    def frequency(self, x: int, y: int) -> int:
        """Number of distinct turns within the window where (x, y) was flagged."""
        return sum(1 for _, tiles in self._window if (x, y) in tiles)

    def hot_tiles(self, min_freq: int = 2) -> List[Tuple[int, int]]:
        """Tiles flagged in at least ``min_freq`` window turns, hottest first."""
        accum: Dict[Tuple[int, int], int] = {}
        for _, tiles in self._window:
            for t in tiles:
                accum[t] = accum.get(t, 0) + 1
        # sort: highest freq first, then ascending x, ascending y
        ranked = sorted(
            (t for t, n in accum.items() if n >= min_freq),
            key=lambda t: (-accum[t], t[0], t[1]),
        )
        return ranked

    def latest_flagged(self) -> List[Tuple[int, int]]:
        """Tiles flagged in the most recent observed turn (sorted)."""
        if not self._window:
            return []
        return sorted(self._window[-1][1])

    def n_turns_observed(self) -> int:
        return len(self._window)


# ---------------------------------------------------------------------------
# Task 4 — BreachLocationTracker
# ---------------------------------------------------------------------------

@dataclass
class BreachLocationTracker:
    """Accumulates breach events scored AGAINST us, with decay.

    A breach event has shape::

        [[x, y], damage_dealt, unit_type, unit_id, breaching_player_id]

    ``breaching_player_id != self_player_id`` means an opponent mobile
    unit reached our edge and dealt HP damage to us — exactly the
    events we want to learn from. We score per-tile counts and per-edge
    totals (BL/BR/TL/TR) so the planner can see where the opponent is
    actually scoring vs. just threatening.

    Decay: ``decay_per_turn`` (default 0.9) is applied at the start of
    every new turn before that turn's events are added. Per-frame
    dedupe via (turn, frame_index, event_idx) keeps re-feeding the same
    frame from being double-counted.

    Player_index flip: ``self_player_id`` defaults to 1 (action-frame
    convention, ``1 = you, 2 = opponent``). Breaches where
    ``breaching_id == self_player_id`` are OUR scoring runs and are
    deliberately ignored — this tracker is "what's hurting us".
    """

    self_player_id: int = 1
    decay_per_turn: float = 0.9
    per_tile: Dict[Tuple[int, int], float] = field(default_factory=dict)
    per_edge: Dict[str, float] = field(
        default_factory=lambda: {EDGE_BL: 0.0, EDGE_BR: 0.0, EDGE_TL: 0.0, EDGE_TR: 0.0}
    )
    total_damage: float = 0.0
    _last_turn_decayed: int = -1
    _seen_event_keys: Set[Tuple[Tuple[int, int], int]] = field(default_factory=set)

    def consume_action_frame(self, frame: Dict[str, Any]) -> None:
        turn_info = frame.get("turnInfo")
        if turn_info is None or len(turn_info) < 3:
            return
        # Decay once per turn at the first action frame of that turn.
        if _is_first_action_frame(turn_info):
            turn_number = int(turn_info[1])
            if turn_number != self._last_turn_decayed:
                self._apply_decay()
                self._last_turn_decayed = turn_number

        breaches = frame.get("events", {}).get("breach", [])
        if not breaches:
            return
        # Frame-key part of the dedupe key — a re-fed frame won't
        # double-count, but distinct events within a single frame
        # remain separate via the trailing index.
        try:
            frame_key = (int(turn_info[1]), int(turn_info[2]))
        except (TypeError, ValueError):
            frame_key = (-1, -1)

        for idx, ev in enumerate(breaches):
            try:
                loc = ev[0]
                damage = float(ev[1])
                breaching_id = int(ev[4])
                x = int(loc[0])
                y = int(loc[1])
            except (IndexError, TypeError, ValueError):
                continue
            if breaching_id == self.self_player_id:
                continue  # this is OUR breach, not against us
            ev_key = (frame_key, idx)
            if ev_key in self._seen_event_keys:
                continue
            self._seen_event_keys.add(ev_key)
            tile = (x, y)
            self.per_tile[tile] = self.per_tile.get(tile, 0.0) + damage
            edge = _classify_edge(x, y)
            if edge is not None:
                self.per_edge[edge] += damage
            self.total_damage += damage

    # -- queries --------------------------------------------------------

    def _apply_decay(self) -> None:
        if self.decay_per_turn == 1.0:
            return
        d = self.decay_per_turn
        for k in list(self.per_tile.keys()):
            self.per_tile[k] *= d
        for k in self.per_edge:
            self.per_edge[k] *= d
        self.total_damage *= d

    def edge_pressure(self) -> Dict[str, float]:
        """Snapshot of per-edge accumulated breach damage."""
        return dict(self.per_edge)

    def hot_tiles(self, top_k: int = 5) -> List[Tuple[Tuple[int, int], float]]:
        """Top breach tiles by current weighted damage, descending."""
        ranked = sorted(self.per_tile.items(), key=lambda kv: (-kv[1], kv[0]))
        return [(tile, weight) for tile, weight in ranked[:top_k] if weight > 0.0]

    def n_breaches_seen(self) -> int:
        return len(self._seen_event_keys)
