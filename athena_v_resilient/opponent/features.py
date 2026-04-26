"""Per-replay feature extractor for the Phase 3 opponent classifier.

Given a parsed sequence of action-frame JSON objects (the dicts
emitted by the engine inside ``on_action_frame``), produce a fixed
``dict[str, float]`` of features that the Bayesian classifier and
the heuristic labeler both consume.

All features are computed by feeding the action frames through the
Phase 0.5 trackers in ``action_frame_utils`` plus a few small
in-line aggregators that don't fit any tracker (mobile-type ratio,
mean MP at spawn, breach rate, edge-asymmetry, wall-removal rate,
mean wave size). Pure stdlib + numpy — no engine.jar / sim_rs.

Action-frame convention reminder: in the raw JSON, ``1=self`` and
``2=opponent``. The Phase 0.5 trackers each take ``self_player_id``
explicitly so the player_index flip is handled at the call site.

Public API:
    extract_features(action_frames, opponent_player_id) -> dict[str, float]
    FEATURE_NAMES: tuple of all feature keys, in canonical order.
    iter_replay_action_frames(path) -> iterator over parsed JSON dicts.
"""
from __future__ import annotations

import json
import math
from pathlib import Path
from typing import Any, Dict, Iterable, Iterator, List, Sequence, Tuple

import numpy as np

from .action_frame_utils import (
    ARENA_SIZE,
    DEMOLISHER_IDX,
    INTERCEPTOR_IDX,
    MOBILE_TYPES,
    REMOVE_IDX,
    SCOUT_IDX,
    STRUCTURE_TYPES,
    BatchCountTracker,
    BreachLocationTracker,
    ResourceTracker,
    SpawnXHistogram,
    WallRemovalDetector,
    _classify_edge,
    _is_first_action_frame,
)

# ---------------------------------------------------------------------------
# Canonical feature order — keep stable; the classifier serializes by index.
# ---------------------------------------------------------------------------

FEATURE_NAMES: Tuple[str, ...] = (
    "spawn_x_entropy",          # nats; 0 = perfectly concentrated, ln(28) ~3.33 = uniform
    "spawn_x_peak_col",          # 0..27 (most-spawned column over the match)
    "spawn_x_peak_share",        # share of opp mobile spawns at the peak column
    "scout_share",               # of opp mobile spawns
    "demolisher_share",
    "interceptor_share",
    "mean_mp_at_spawn",          # opp MP at start of action phase, averaged over turns with >=1 mobile spawn
    "breach_rate_per_turn",      # breaches against us / total observed turns
    "edge_asymmetry",            # |BL_share - BR_share| over opp mobile spawns (0..1)
    "wall_removal_rate",         # # opp removal-flagged tiles / observed turns
    "mean_wave_size",            # mean opp mobile spawns per turn that *had* >=1 mobile spawn
    "support_share",             # of opp structure spawns (Wall/Support/Turret)
    "turret_share",              # of opp structure spawns
    "turns_observed",            # how long we watched (clamps for early-game inferences)
)


# ---------------------------------------------------------------------------
# Replay loader (stdlib-only)
# ---------------------------------------------------------------------------

def iter_replay_action_frames(path: Path) -> Iterator[Dict[str, Any]]:
    """Yield each line of `path` parsed as JSON, skipping blanks / non-JSON.

    Replay file format: blank line, header on line 2, then one JSON
    object per line (deploy + action frames). The last frame carries
    ``endStats``. We yield every JSON object — callers filter on
    ``turnInfo`` themselves (the trackers do this internally).
    """
    with path.open("r", encoding="utf-8") as fh:
        for raw in fh:
            raw = raw.strip()
            if not raw:
                continue
            try:
                obj = json.loads(raw)
            except json.JSONDecodeError:
                continue
            if isinstance(obj, dict):
                yield obj


# ---------------------------------------------------------------------------
# Feature extraction
# ---------------------------------------------------------------------------

def _entropy(p: np.ndarray) -> float:
    """Shannon entropy in nats. p must be >=0; sums-to-1 not enforced."""
    s = float(p.sum())
    if s <= 0.0:
        return 0.0
    q = p / s
    nz = q[q > 0.0]
    return float(-(nz * np.log(nz)).sum())


def extract_features(
    action_frames: Iterable[Dict[str, Any]],
    opponent_player_id: int = 2,
) -> Dict[str, float]:
    """Run the trackers + aggregators over `action_frames`.

    `opponent_player_id` is the raw-JSON player_id of the opponent
    (almost always 2). The "self" passed to the trackers is therefore
    `3 - opponent_player_id` (1 if opp is 2, 2 if opp is 1).

    Returns a `dict` keyed exactly by `FEATURE_NAMES` with all values
    cast to plain Python floats.
    """
    self_id = 3 - opponent_player_id
    if self_id not in (1, 2):
        raise ValueError(
            f"opponent_player_id must be 1 or 2; got {opponent_player_id}"
        )

    batch = BatchCountTracker(self_player_id=self_id)
    spawn_hist = SpawnXHistogram(self_player_id=self_id, decay_per_turn=1.0)
    breach = BreachLocationTracker(self_player_id=self_id, decay_per_turn=1.0)
    resources = ResourceTracker(self_player_id=self_id)
    walls = WallRemovalDetector(self_player_id=self_id, window_size=10**6)

    # In-line aggregators that don't fit a tracker
    edge_counts = {"BL": 0, "BR": 0, "TL": 0, "TR": 0}
    mp_at_spawn_samples: List[float] = []  # one entry per turn that has >=1 opp mobile spawn
    waves: List[int] = []  # mobile spawns per turn with >=1
    turns_seen: set = set()

    for frame in action_frames:
        ti = frame.get("turnInfo")
        if ti is None or len(ti) < 3:
            continue
        is_first_af = _is_first_action_frame(ti)

        batch.consume_action_frame(frame)
        spawn_hist.consume_action_frame(frame)
        breach.consume_action_frame(frame)
        resources.consume_action_frame(frame)
        walls.consume_action_frame(frame)

        if is_first_af:
            turn_n = int(ti[1])
            turns_seen.add(turn_n)
            # Edge classification of opp mobile spawns + mp-at-spawn + wave size
            opp_mobile_this_turn = 0
            for ev in frame.get("events", {}).get("spawn", []):
                try:
                    loc = ev[0]
                    unit_type = int(ev[1])
                    pid = int(ev[3])
                    x = int(loc[0])
                    y = int(loc[1])
                except (IndexError, TypeError, ValueError):
                    continue
                if pid != opponent_player_id:
                    continue
                if unit_type not in MOBILE_TYPES:
                    continue
                opp_mobile_this_turn += 1
                edge = _classify_edge(x, y)
                if edge in edge_counts:
                    edge_counts[edge] += 1
            if opp_mobile_this_turn > 0:
                waves.append(opp_mobile_this_turn)
                # MP at spawn = opp MP at first action frame of this turn
                stats_key = "p2Stats" if opponent_player_id == 2 else "p1Stats"
                stats = frame.get(stats_key)
                if isinstance(stats, list) and len(stats) >= 3:
                    try:
                        mp_at_spawn_samples.append(float(stats[2]))
                    except (TypeError, ValueError):
                        pass

    n_turns = max(1, len(turns_seen))

    # Mobile-unit type ratios
    total_mobile = sum(batch.total(t) for t in MOBILE_TYPES)
    if total_mobile > 0:
        scout_share = batch.total(SCOUT_IDX) / total_mobile
        demo_share = batch.total(DEMOLISHER_IDX) / total_mobile
        intercept_share = batch.total(INTERCEPTOR_IDX) / total_mobile
    else:
        scout_share = demo_share = intercept_share = 0.0

    # Structure spawn shares (Wall vs Support vs Turret)
    total_struct = sum(batch.total(t) for t in STRUCTURE_TYPES)
    if total_struct > 0:
        support_share = batch.total(1) / total_struct  # SUPPORT_IDX = 1
        turret_share = batch.total(2) / total_struct   # TURRET_IDX = 2
    else:
        support_share = turret_share = 0.0

    # Spawn-x histogram
    raw_hist = spawn_hist.spawn_x_distribution()
    if raw_hist.sum() > 0:
        peak_col = int(np.argmax(raw_hist))
        peak_share = float(raw_hist[peak_col] / raw_hist.sum())
    else:
        peak_col = -1  # sentinel; means no opp mobile spawns observed
        peak_share = 0.0
    entropy_nats = _entropy(raw_hist)

    # Edge asymmetry: only consider BL vs BR (opp's mobile units originate
    # from their edges, which from the opp's POV are TL/TR — but the
    # underlying tile coordinate matches the diamond; for opp player 2,
    # spawns are at y>=14 (TL/TR). For opp player 1, spawns are at y<14
    # (BL/BR). Use the half that actually saw the spawns.
    if opponent_player_id == 2:
        left, right = edge_counts["TL"], edge_counts["TR"]
    else:
        left, right = edge_counts["BL"], edge_counts["BR"]
    total_edge = left + right
    edge_asymmetry = abs(left - right) / total_edge if total_edge > 0 else 0.0

    # Mean MP at spawn (only over turns that actually attacked)
    mean_mp = float(np.mean(mp_at_spawn_samples)) if mp_at_spawn_samples else 0.0

    # Breach rate per turn — # opponent breaches / observed turns
    breach_rate = breach.n_breaches_seen() / n_turns

    # Wall-removal rate: # of (turn, tile) opp removal flags / observed turns
    # Sum each turn's flagged-tile count over the window (window is huge
    # so it's effectively cumulative).
    walls_total = sum(len(tiles) for _, tiles in walls._window)
    wall_removal_rate = walls_total / n_turns

    # Mean wave size (averaged over turns where opp spawned mobiles)
    mean_wave = float(np.mean(waves)) if waves else 0.0

    return {
        "spawn_x_entropy": float(entropy_nats),
        "spawn_x_peak_col": float(peak_col),
        "spawn_x_peak_share": float(peak_share),
        "scout_share": float(scout_share),
        "demolisher_share": float(demo_share),
        "interceptor_share": float(intercept_share),
        "mean_mp_at_spawn": float(mean_mp),
        "breach_rate_per_turn": float(breach_rate),
        "edge_asymmetry": float(edge_asymmetry),
        "wall_removal_rate": float(wall_removal_rate),
        "mean_wave_size": float(mean_wave),
        "support_share": float(support_share),
        "turret_share": float(turret_share),
        "turns_observed": float(len(turns_seen)),
    }


def features_to_vector(features: Dict[str, float]) -> np.ndarray:
    """Convert a feature dict to a 1-D numpy array in canonical order."""
    return np.array([features[k] for k in FEATURE_NAMES], dtype=np.float64)


def features_from_replay(
    replay_path: Path, opponent_player_id: int = 2
) -> Dict[str, float]:
    """Convenience: parse replay file -> features dict."""
    return extract_features(
        iter_replay_action_frames(replay_path),
        opponent_player_id=opponent_player_id,
    )


__all__ = [
    "FEATURE_NAMES",
    "extract_features",
    "features_from_replay",
    "features_to_vector",
    "iter_replay_action_frames",
]
