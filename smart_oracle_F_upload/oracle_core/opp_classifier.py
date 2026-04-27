"""Opponent archetype classifier for smart_oracle_F.

The funnel-response components in smart_oracle_F (flank-corridor templates +
opp-sample filter) regress against multi-archetype oracle opponents because
they shift defense to the flanks and leave center exposed. They were
designed for single-archetype heuristic attackers like ameyg's funnel-rush
or aa0's python-algo killshot, where opp ONLY does the funnel.

This module classifies the opponent based on observed spawn behaviour over
a rolling window. The classifier output gates whether the funnel-response
paths fire — they only fire when the classifier is confident the opponent
is single-archetype.

Three signals (computed from `consume(frame)` opp spawn events accumulated
over `WINDOW_TURNS`):

  1. Spawn-tile concentration: fraction of opp spawn EVENTS in the window
     that occurred at the most-used spawn tile. Single-archetype attackers
     drill from one (or two adjacent) spawn tiles — concentration ≥ 0.6.
     Oracle / multi-archetype attackers spread spawns across many tiles —
     concentration ≤ 0.4.

  2. Unit-type diversity: count of distinct mobile unit types used in the
     window. Single-archetype attackers use 1-2 types; oracles use 2-3.

  3. Spawn-turn coverage: number of turns in the window with at least one
     opp spawn. Used as a sanity filter — if opp has spawned in fewer than
     `MIN_SPAWN_TURNS`, we don't have enough data to classify.

Verdict (returned by `classify()`):
  * `ARCHETYPE_SINGLE` — concentration ≥ HIGH AND distinct types ≤ 2
  * `ARCHETYPE_MULTI`  — concentration ≤ LOW  OR  distinct types ≥ 3
  * `None`             — insufficient evidence (early game or borderline)

The classifier is intentionally conservative: it returns None unless the
signal is unambiguous. Wrong-direction classification is more harmful than
no classification (it would re-introduce the IS6 regression).
"""
from __future__ import annotations

from collections import Counter, deque
from dataclasses import dataclass, field
from typing import Any, Dict, Optional

from .constants import DEMOLISHER_IDX, INTERCEPTOR_IDX, SCOUT_IDX

ARCHETYPE_SINGLE = "single_archetype"
ARCHETYPE_MULTI = "multi_archetype"

# Rolling window — turns of opp behaviour we keep in scope. Long enough to
# distinguish steady drilldown from a one-off corner attack, short enough
# to react if opp pivots strategies mid-game.
WINDOW_TURNS = 10

# Need at least this many turns with ≥1 opp spawn before classifying.
# Avoids early-game guesses based on 1-2 turns of data.
MIN_SPAWN_TURNS = 4

# Need at least this many total spawn events in the window before
# concentration % is meaningful.
MIN_SPAWN_EVENTS = 8

# Concentration thresholds (fraction of window's spawn events at top tile).
CONCENTRATION_HIGH = 0.6   # ≥ this AND ≤2 unit types → single_archetype
CONCENTRATION_LOW = 0.4    # ≤ this OR ≥3 unit types → multi_archetype

# Distinct unit-type thresholds.
TYPES_SINGLE_MAX = 2       # single-archetype uses ≤ this many types
TYPES_MULTI_MIN = 3        # multi-archetype uses ≥ this many types

_MOBILE_IDX_SET = {SCOUT_IDX, DEMOLISHER_IDX, INTERCEPTOR_IDX}


@dataclass
class _TurnSpawnSnapshot:
    """Opponent spawn record for one turn."""
    turn: int
    tiles: list = field(default_factory=list)   # list of (x, y, unit_idx)


class OpponentClassifier:
    """Per-game classifier of opp archetype.

    Lifecycle:
      classifier = OpponentClassifier(self_player_id=1)
      # In on_action_frame:
      classifier.consume_frame(frame)
      # Once per turn (after the action phase ends):
      classifier.advance_turn()
      # Before search:
      verdict = classifier.classify()  # None / ARCHETYPE_SINGLE / ARCHETYPE_MULTI
    """

    def __init__(self, self_player_id: int = 1):
        self.self_player_id = self_player_id
        self.opp_player_id = 2 if self_player_id == 1 else 1
        # Rolling window of per-turn snapshots.
        self.window: deque = deque(maxlen=WINDOW_TURNS)
        # In-progress current turn — accumulator that flushes on advance_turn().
        self._current: _TurnSpawnSnapshot = _TurnSpawnSnapshot(turn=0)
        self._last_verdict: Optional[str] = None

    def consume_frame(self, frame: Dict[str, Any]) -> None:
        """Consume one already-parsed action-frame dict. Records opp spawns."""
        if not isinstance(frame, dict):
            return
        events = frame.get("events", {}) or {}
        for sp in events.get("spawn", []) or []:
            if not sp or len(sp) < 4:
                continue
            loc, utype, _uid, owner = sp[:4]
            if int(owner) != self.opp_player_id:
                continue
            try:
                ut = int(utype)
                x, y = int(loc[0]), int(loc[1])
            except (TypeError, ValueError, IndexError):
                continue
            if ut not in _MOBILE_IDX_SET:
                continue
            self._current.tiles.append((x, y, ut))

    def advance_turn(self) -> None:
        """Flush the current turn's accumulator into the window."""
        # Always push, even if empty — so spawn-turn coverage reflects how
        # many turns opp actually spawned in.
        self.window.append(self._current)
        self._current = _TurnSpawnSnapshot(turn=self._current.turn + 1)

    def classify(self) -> Optional[str]:
        """Return the current verdict — single / multi / None."""
        # Aggregate over the window.
        all_spawns = []
        spawning_turns = 0
        for snap in self.window:
            if snap.tiles:
                spawning_turns += 1
                all_spawns.extend(snap.tiles)

        n_events = len(all_spawns)
        if n_events < MIN_SPAWN_EVENTS or spawning_turns < MIN_SPAWN_TURNS:
            self._last_verdict = None
            return None

        tile_counts = Counter((x, y) for x, y, _ in all_spawns)
        top_tile, top_count = tile_counts.most_common(1)[0]
        concentration = top_count / n_events

        type_counts = Counter(ut for _, _, ut in all_spawns)
        n_types = len(type_counts)

        # Decision rules. Spawn-tile concentration is the dominant signal.
        # Backtest discovered that M1Lite-vs-ameyg's 92%-at-(14,27) attack
        # uses 3 unit types but is unambiguously a funnel — the original
        # logic incorrectly flipped to MULTI on `n_types >= 3`. Revised:
        #   SINGLE if concentration alone is high (drilldown at one tile)
        #   MULTI  if concentration is low AND types are diverse (spread)
        # This still catches the IS6 case (conc 0.30-0.44, 3 types →
        # MULTI by both gates) while capturing multi-type funnels.
        if concentration >= CONCENTRATION_HIGH:
            verdict = ARCHETYPE_SINGLE
        elif concentration <= CONCENTRATION_LOW and n_types >= TYPES_MULTI_MIN:
            verdict = ARCHETYPE_MULTI
        else:
            # Borderline — neither clean single nor clean multi. Stay None.
            verdict = None

        self._last_verdict = verdict
        return verdict

    def stats(self) -> Dict[str, Any]:
        """Diagnostic snapshot — useful for backtest + telemetry."""
        all_spawns = []
        spawning_turns = 0
        for snap in self.window:
            if snap.tiles:
                spawning_turns += 1
                all_spawns.extend(snap.tiles)
        n_events = len(all_spawns)
        tile_counts = Counter((x, y) for x, y, _ in all_spawns)
        top_tile, top_count = tile_counts.most_common(1)[0] if tile_counts else (None, 0)
        type_counts = Counter(ut for _, _, ut in all_spawns)
        return {
            "window_size": len(self.window),
            "spawning_turns": spawning_turns,
            "n_events": n_events,
            "top_tile": top_tile,
            "top_tile_count": top_count,
            "concentration": (top_count / n_events) if n_events else 0.0,
            "n_distinct_types": len(type_counts),
            "type_breakdown": dict(type_counts),
            "verdict": self._last_verdict,
        }


__all__ = [
    "OpponentClassifier",
    "ARCHETYPE_SINGLE", "ARCHETYPE_MULTI",
    "WINDOW_TURNS", "MIN_SPAWN_TURNS", "MIN_SPAWN_EVENTS",
    "CONCENTRATION_HIGH", "CONCENTRATION_LOW",
    "TYPES_SINGLE_MAX", "TYPES_MULTI_MIN",
]
