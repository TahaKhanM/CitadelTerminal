"""Per-archetype action predictor — empirical distribution lookup.

Given the current game state and a posterior over archetypes, return
``top_k_actions``: a list of ``(action_dict, probability)`` tuples
representing what the opponent is most likely to do this turn.

An "action" here is a coarse summary of one turn's opponent behavior:

    {
        "primary_mobile_type": int (3 Scout / 4 Demolisher / 5 Interceptor / -1 none),
        "primary_edge": "BL" | "BR" | "TL" | "TR" | "NONE",
        "wave_size_bucket": "0" | "1-3" | "4-7" | "8-14" | "15+",
        "spend_mp": bool,
    }

State buckets (for distribution lookup):
  - mp_bucket: "0", "1-4", "5-9", "10-14", "15+"
  - phase: "early" (turn<10), "mid" (10<=turn<25), "late" (turn>=25)

For each archetype, we accumulate per-(state_bucket -> action -> count)
frequencies from the labeled replays + a small global-fallback bucket
for state_buckets we never saw at training time.

Discounting: counts get a Laplace +1 smoothing AND a global fallback
weight; for archetype/state pairs with zero training samples the
predictor falls back to the global empirical distribution. This keeps
predictions sane on rare combinations (e.g. SUPPORT_BURST in the
"early" + "mp=15+" bucket — won't have happened in 47 replays).

Public API:
  ActionPredictor(labels_path).top_k(state, posterior, k=5)
  ActionPredictor.fit(replay_paths, labels)  -- offline (build_corpus.py)
"""
from __future__ import annotations

import json
from collections import Counter, defaultdict
from pathlib import Path
from typing import Any, Dict, Iterable, List, Optional, Sequence, Tuple

import numpy as np

from .action_frame_utils import (
    DEMOLISHER_IDX,
    INTERCEPTOR_IDX,
    MOBILE_TYPES,
    SCOUT_IDX,
    _classify_edge,
    _is_first_action_frame,
)
from .archetypes import ARCHETYPES, DEFAULT_ARCHETYPE
from .features import iter_replay_action_frames


# ---------------------------------------------------------------------------
# State bucketing
# ---------------------------------------------------------------------------

def mp_bucket(mp: float) -> str:
    if mp < 1.0:
        return "0"
    if mp < 5.0:
        return "1-4"
    if mp < 10.0:
        return "5-9"
    if mp < 15.0:
        return "10-14"
    return "15+"


def phase_bucket(turn: int) -> str:
    if turn < 10:
        return "early"
    if turn < 25:
        return "mid"
    return "late"


def wave_size_bucket(n: int) -> str:
    if n == 0:
        return "0"
    if n <= 3:
        return "1-3"
    if n <= 7:
        return "4-7"
    if n <= 14:
        return "8-14"
    return "15+"


def state_key(mp: float, turn: int) -> Tuple[str, str]:
    return (mp_bucket(mp), phase_bucket(turn))


# ---------------------------------------------------------------------------
# Action representation
# ---------------------------------------------------------------------------

ActionTuple = Tuple[int, str, str, bool]
"""(primary_mobile_type, primary_edge, wave_size_bucket, spend_mp)."""


def _action_to_dict(a: ActionTuple) -> Dict[str, Any]:
    return {
        "primary_mobile_type": int(a[0]),
        "primary_edge": str(a[1]),
        "wave_size_bucket": str(a[2]),
        "spend_mp": bool(a[3]),
    }


def _dominant_mobile_type(spawns: Iterable[Tuple[int, int, int, int]]) -> int:
    """Most-frequent mobile type in the spawn list, or -1 if none.

    Each spawn entry is (x, y, unit_type, player_id) — we filter to
    mobile types here (caller has already filtered to opp player).
    """
    counter: Counter = Counter()
    for x, y, ut, pid in spawns:
        if ut in MOBILE_TYPES:
            counter[ut] += 1
    if not counter:
        return -1
    return counter.most_common(1)[0][0]


def _dominant_edge(spawns: Iterable[Tuple[int, int, int, int]], opp_pid: int) -> str:
    counter: Counter = Counter()
    for x, y, ut, pid in spawns:
        if ut not in MOBILE_TYPES:
            continue
        edge = _classify_edge(x, y)
        if edge:
            counter[edge] += 1
    if not counter:
        return "NONE"
    return counter.most_common(1)[0][0]


# ---------------------------------------------------------------------------
# Predictor
# ---------------------------------------------------------------------------

class ActionPredictor:
    """Per-archetype empirical action distribution.

    Build from a labels file (`labels.json`) by replaying each labeled
    replay and recording one observation per opponent action turn.
    """

    def __init__(
        self,
        archetypes: Sequence[str] = ARCHETYPES,
        laplace_alpha: float = 1.0,
        opponent_player_id: int = 2,
    ) -> None:
        self.archetypes: Tuple[str, ...] = tuple(archetypes)
        self.opp_pid = opponent_player_id
        self.laplace_alpha = float(laplace_alpha)
        # arch -> state_key -> action_tuple -> count
        self._counts: Dict[str, Dict[Tuple[str, str], Counter]] = {
            a: defaultdict(Counter) for a in self.archetypes
        }
        # arch -> Counter of all observations (state-agnostic fallback)
        self._global_counts: Dict[str, Counter] = {a: Counter() for a in self.archetypes}
        # arch -> total observations (for normalization)
        self._n_obs: Dict[str, int] = {a: 0 for a in self.archetypes}
        self._fitted = False

    # -- fitting -------------------------------------------------------

    def fit_from_labels_json(self, labels_json_path: Path) -> "ActionPredictor":
        """Replay every labeled match and accumulate per-archetype counts.

        labels.json schema (subset)::

            {
              "labels": {
                 "<opp>::<match_id>": {
                    "archetype": str,
                    "match_id": int,
                    "opponent_name": str,
                    ...
                 }
              }
            }

        We need the replay file too — looked up via replay_index.json.
        """
        labels_json_path = Path(labels_json_path)
        with labels_json_path.open("r") as fh:
            label_meta = json.load(fh)
        # Find replay paths via replay_index.json (sibling-ish path)
        repo_root = labels_json_path.resolve().parents[3]
        idx_path = repo_root / "algos" / "athena_v3_planner" / "data" / "replay_index.json"
        with idx_path.open("r") as fh:
            replay_idx = {f"{r['opponent_name']}::{r['match_id']}": r for r in json.load(fh)}

        for key, info in label_meta["labels"].items():
            arch = info["archetype"]
            if arch not in self._counts:
                continue
            replay_entry = replay_idx.get(key)
            if not replay_entry:
                continue
            replay_path = repo_root / replay_entry["file"]
            if not replay_path.exists():
                continue
            self._observe_replay(replay_path, arch)

        self._fitted = True
        return self

    def _observe_replay(self, replay_path: Path, archetype: str) -> None:
        """Walk a replay and add one (state, action) observation per opp turn."""
        prev_mp: float = 1.0  # starting MP
        for frame in iter_replay_action_frames(replay_path):
            ti = frame.get("turnInfo")
            if not ti or not _is_first_action_frame(ti):
                continue
            turn_n = int(ti[1])
            stats_key = "p2Stats" if self.opp_pid == 2 else "p1Stats"
            stats = frame.get(stats_key)
            mp = 0.0
            if isinstance(stats, list) and len(stats) >= 3:
                try:
                    mp = float(stats[2])
                except (TypeError, ValueError):
                    pass

            # Pull opp spawns this turn
            spawns: List[Tuple[int, int, int, int]] = []
            for ev in frame.get("events", {}).get("spawn", []):
                try:
                    loc = ev[0]
                    ut = int(ev[1])
                    pid = int(ev[3])
                    x = int(loc[0])
                    y = int(loc[1])
                except (IndexError, TypeError, ValueError):
                    continue
                if pid == self.opp_pid:
                    spawns.append((x, y, ut, pid))

            n_mobile = sum(1 for _, _, ut, _ in spawns if ut in MOBILE_TYPES)
            action: ActionTuple = (
                _dominant_mobile_type(spawns),
                _dominant_edge(spawns, self.opp_pid),
                wave_size_bucket(n_mobile),
                n_mobile > 0,
            )

            sk = state_key(mp, turn_n)
            self._counts[archetype][sk][action] += 1
            self._global_counts[archetype][action] += 1
            self._n_obs[archetype] += 1
            prev_mp = mp

    # -- query ---------------------------------------------------------

    def archetype_distribution(
        self,
        archetype: str,
        sk: Tuple[str, str],
    ) -> List[Tuple[ActionTuple, float]]:
        """Smoothed (action -> probability) for one archetype + state.

        Falls back to the archetype's global distribution when the
        per-state bucket is empty. Falls back further to a uniform
        action over (NONE, no-spend) when the archetype has no
        observations at all (e.g. TURTLE_GRIND on this corpus).
        """
        bucket = self._counts.get(archetype, {}).get(sk)
        if bucket is None or sum(bucket.values()) == 0:
            bucket = self._global_counts.get(archetype, Counter())
        total = sum(bucket.values())
        if total == 0:
            # Last-resort uniform: a single "do nothing" action so the
            # caller still gets >=1 valid prediction.
            return [((-1, "NONE", "0", False), 1.0)]
        # Laplace smoothing
        alpha = self.laplace_alpha
        actions = list(bucket.keys())
        n_actions = len(actions)
        denom = total + alpha * n_actions
        out = [
            (a, (bucket[a] + alpha) / denom)
            for a in actions
        ]
        out.sort(key=lambda kv: -kv[1])
        return out

    def top_k(
        self,
        current_state: Dict[str, Any],
        archetype_posterior: Dict[str, float],
        k: int = 5,
    ) -> List[Tuple[Dict[str, Any], float]]:
        """Top-K most likely opponent actions for the next turn.

        ``current_state`` keys (all optional, we only use mp + turn):
            mp:    float (opp MP at start of action phase)
            turn:  int   (turn number)

        Returns ``[(action_dict, probability), ...]`` sorted by
        probability descending. Probabilities do NOT necessarily sum
        to 1 (they may exceed 1 if marginalized over both archetype
        AND smoothed action distributions); they're scores not strict
        probabilities, and the caller should treat them as such.
        """
        if not self._fitted:
            raise RuntimeError("ActionPredictor.fit_from_labels_json() not called")
        if k <= 0:
            return []
        mp = float(current_state.get("mp", 0.0))
        turn = int(current_state.get("turn", 0))
        sk = state_key(mp, turn)

        # Marginalize: sum over archetypes weighted by posterior
        marginal: Counter = Counter()
        for arch, p_arch in archetype_posterior.items():
            if arch not in self._counts or p_arch <= 0.0:
                continue
            for action, p_a in self.archetype_distribution(arch, sk):
                marginal[action] += p_arch * p_a

        if not marginal:
            return [(_action_to_dict((-1, "NONE", "0", False)), 1.0)]

        ranked = sorted(marginal.items(), key=lambda kv: -kv[1])[:k]
        return [(_action_to_dict(a), float(p)) for a, p in ranked]


__all__ = [
    "ActionPredictor",
    "mp_bucket",
    "phase_bucket",
    "wave_size_bucket",
    "state_key",
]
