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

from dataclasses import dataclass, field
from typing import Any, Dict, List, Sequence, Tuple

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
