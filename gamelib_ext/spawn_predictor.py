"""2nd-order Markov predictor over opponent spawn-edge categories.

Category per observed turn is one of:
    "LEFT"  — at least one enemy mobile spawned on their TOP_LEFT edge
              (we observe it as x >= 14 at y=14,15 i.e. opponent's left
              edge from their perspective; see note below)
    "RIGHT" — enemy spawn on their TOP_RIGHT edge
    "BOTH"  — spawns on both edges in the same turn
    "NONE"  — no mobile spawn observed that turn

Note on perspective: opponent is "top" player. Their TOP_LEFT edge is the
y=27−x diagonal running to [0,14]; spawns there have low x. Their
TOP_RIGHT edge is y=x diagonal running to [27,14]; spawns there have
high x. From OUR (bottom) perspective a low-x enemy spawn attacks our
RIGHT side (because unit travels down the y=x diagonal, heading to our
BOTTOM_RIGHT). So the classifier stores what the opponent's spawn EDGE
was (LEFT / RIGHT from their viewpoint); callers that want "which of
MY sides is threatened" should map LEFT→our-right, RIGHT→our-left.

Public surface:
    sp = SpawnPredictor()
    sp.ingest(turn_n, observed)       # observed: set of {"LEFT","RIGHT"} or empty
    sp.predict() -> dict              # {"LEFT":p,"RIGHT":p,"BOTH":p,"NONE":p}
    sp.confidence                     # max probability in predict()
    sp.entropy                        # Shannon entropy, natural log

Laplace-smoothed over the 4 categories with α=1.
"""
from __future__ import annotations

import math
from collections import Counter, defaultdict
from typing import Dict, Iterable, Optional, Set

LEFT = "LEFT"
RIGHT = "RIGHT"
BOTH = "BOTH"
NONE = "NONE"
CATS = (LEFT, RIGHT, BOTH, NONE)


def _categorize(edges: Iterable[str]) -> str:
    s = set(edges)
    has_l = LEFT in s
    has_r = RIGHT in s
    if has_l and has_r:
        return BOTH
    if has_l:
        return LEFT
    if has_r:
        return RIGHT
    return NONE


class SpawnPredictor:
    def __init__(self):
        # (prev2, prev1) -> Counter[next]
        self._table: Dict[tuple, Counter] = defaultdict(Counter)
        # Last two observed categories; None means "start of game".
        self._prev2: Optional[str] = None
        self._prev1: Optional[str] = None
        self._last_turn: Optional[int] = None
        self._samples = 0

    def ingest(self, turn_n: int, observed_edges: Iterable[str]) -> None:
        """Record one observation for turn_n.

        `observed_edges` is the set (or iterable) of edges on which
        enemy mobiles were seen this turn. Empty iterable → NONE.
        Each turn should be ingested at most once; subsequent calls
        with the same turn_n are ignored.
        """
        if self._last_turn is not None and turn_n <= self._last_turn:
            return
        cat = _categorize(observed_edges)
        if self._prev2 is not None and self._prev1 is not None:
            self._table[(self._prev2, self._prev1)][cat] += 1
            self._samples += 1
        self._prev2 = self._prev1
        self._prev1 = cat
        self._last_turn = turn_n

    def predict(self) -> Dict[str, float]:
        """Laplace-smoothed predictive distribution over the 4 categories."""
        # If we haven't seen two priors yet, return uniform.
        if self._prev2 is None or self._prev1 is None:
            return {c: 0.25 for c in CATS}
        ctx = self._table.get((self._prev2, self._prev1), Counter())
        total = sum(ctx.values()) + len(CATS)  # +α·|V| with α=1
        return {c: (ctx.get(c, 0) + 1) / total for c in CATS}

    @property
    def confidence(self) -> float:
        p = self.predict()
        return max(p.values())

    @property
    def entropy(self) -> float:
        p = self.predict()
        h = 0.0
        for v in p.values():
            if v > 0.0:
                h -= v * math.log(v)
        return h

    @property
    def samples(self) -> int:
        return self._samples


__all__ = ["SpawnPredictor", "LEFT", "RIGHT", "BOTH", "NONE", "CATS"]
