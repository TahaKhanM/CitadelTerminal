"""Tests for ``athena_v3_planner.opponent.action_frame_utils``.

For every utility we assert three properties:

1. **player_index flip is handled**: a synthetic frame mixes self
   (player_id=1) and opponent (player_id=2) entries; the utility must
   ignore self entries.
2. **Determinism**: feeding the same replay twice produces identical
   final state.
3. **Corpus validation**: 5 representative replays from
   ``data/replay_index.json`` (mix of wins/losses, varied opponents,
   one boss) drive every utility end-to-end without crashing and
   produce well-formed outputs.

The test suite uses ONLY stdlib + numpy + pytest, so it runs without
sim_rs / engine.jar.
"""

from __future__ import annotations

import copy
import json
import os
import sys
from pathlib import Path
from typing import Dict, Iterable, List

import numpy as np
import pytest

REPO_ROOT = Path(__file__).resolve().parents[3]
ATHENA_PATH = REPO_ROOT / "algos" / "athena_v3_planner"
sys.path.insert(0, str(ATHENA_PATH.parent))

from athena_v3_planner.opponent import (  # noqa: E402  (sys.path tweak above)
    BatchCountTracker,
    BreachLocationTracker,
    MisdirectionDetector,
    ResourceTracker,
    SpawnXHistogram,
    WallRemovalDetector,
)
from athena_v3_planner.opponent.action_frame_utils import (  # noqa: E402
    ARENA_SIZE,
    DEMOLISHER_IDX,
    MOBILE_TYPES,
    SCOUT_IDX,
    STRUCTURE_TYPES,
    WALL_IDX,
)


# ---------------------------------------------------------------------------
# Replay corpus selection
# ---------------------------------------------------------------------------

# 5 representative replays: 2 wins + 2 losses + 1 boss, varied opponents.
CORPUS_FILES: List[str] = [
    "replays/ranked/v13_360023_m15302602_vs_gooder-maybe_1453_win.replay",
    "replays/ranked/v13_360023_m15302606_vs_python-algo-v3_1612_loss.replay",
    "replays/ranked/v13_360023_m15302609_vs_diego_v2_1486_win.replay",
    "replays/ranked/v13_360023_m15302611_vs_takedown1-algo_1644_loss.replay",
    "replays/ranked/v13_360023_m15302614_vs_R2_Infiltrator_0_win_boss.replay",
]


def _iter_action_frames(replay_path: Path) -> Iterable[Dict]:
    """Yield every action-frame dict (those carrying ``turnInfo``)."""
    with replay_path.open() as fh:
        for line in fh:
            line = line.strip()
            if not line:
                continue
            try:
                obj = json.loads(line)
            except json.JSONDecodeError:
                continue
            if isinstance(obj, dict) and "turnInfo" in obj:
                yield obj


# ---------------------------------------------------------------------------
# Synthetic frames — used by player_index flip tests
# ---------------------------------------------------------------------------


def _make_frame(
    *,
    turn: int,
    spawns=None,
    breaches=None,
    p1_units=None,
    p2_units=None,
    p1_stats=None,
    p2_stats=None,
) -> Dict:
    """Build a minimal action-frame dict for unit tests.

    All fields default to empty / safe values so each test only has to
    populate what it cares about.
    """
    return {
        "turnInfo": [1, turn, 0, 1],
        "events": {
            "spawn": list(spawns or []),
            "breach": list(breaches or []),
            "damage": [],
            "death": [],
            "attack": [],
            "move": [],
            "shield": [],
            "selfDestruct": [],
            "melee": [],
        },
        "p1Units": p1_units or [[], [], [], [], [], [], [], []],
        "p2Units": p2_units or [[], [], [], [], [], [], [], []],
        "p1Stats": p1_stats or [40.0, 8.0, 1.0, 0],
        "p2Stats": p2_stats or [40.0, 8.0, 1.0, 0],
    }


# ===========================================================================
# Player_index flip — one synthetic test per utility
# ===========================================================================


def test_batch_count_tracker_player_index_flip() -> None:
    t = BatchCountTracker(self_player_id=1)
    frame = _make_frame(
        turn=0,
        spawns=[
            [[5, 13], SCOUT_IDX, "1", 1],   # self spawn — must be ignored
            [[14, 0], SCOUT_IDX, "2", 2],   # opp spawn
            [[14, 0], SCOUT_IDX, "3", 2],
            [[10, 5], DEMOLISHER_IDX, "4", 2],
        ],
    )
    t.consume_action_frame(frame)
    assert t.total(SCOUT_IDX) == 2  # only the two opp Scouts
    assert t.total(DEMOLISHER_IDX) == 1
    assert t.turn_total_mobile(0) == 3

    # Now with self_player_id=2 (we are player 2) the same frame should
    # treat player_id=1 as the opponent.
    t2 = BatchCountTracker(self_player_id=2)
    t2.consume_action_frame(frame)
    assert t2.total(SCOUT_IDX) == 1  # only the single self->opp Scout
    assert t2.total(DEMOLISHER_IDX) == 0


def test_spawn_x_histogram_player_index_flip() -> None:
    h = SpawnXHistogram(self_player_id=1, decay_per_turn=1.0)
    frame = _make_frame(
        turn=0,
        spawns=[
            [[14, 0], SCOUT_IDX, "1", 2],  # opp
            [[14, 0], SCOUT_IDX, "2", 2],
            [[14, 0], SCOUT_IDX, "3", 1],  # self — must be filtered
            [[3, 10], DEMOLISHER_IDX, "4", 2],
        ],
    )
    h.consume_action_frame(frame)
    assert h.counts[14] == 2.0
    assert h.counts[3] == 1.0
    # Sanity: peak_spawn_columns must list 14 first, then 3
    assert h.peak_spawn_columns(top_k=3) == [14, 3]


def test_wall_removal_detector_player_index_flip() -> None:
    d = WallRemovalDetector(self_player_id=1, window_size=3)
    # p2Units[6] holds the opp's pending-removal queue → these are the
    # ones we want to track. p1Units[6] is OUR queue → must be ignored.
    frame = _make_frame(
        turn=0,
        p1_units=[[], [], [], [], [], [], [[10, 11, 0, "self_a"]], []],
        p2_units=[[], [], [], [], [], [], [[5, 14, 0, "opp_a"], [6, 14, 0, "opp_b"]], []],
    )
    d.consume_action_frame(frame)
    assert d.frequency(5, 14) == 1
    assert d.frequency(6, 14) == 1
    assert d.frequency(10, 11) == 0  # self entry not tracked


def test_breach_location_tracker_player_index_flip() -> None:
    b = BreachLocationTracker(self_player_id=1, decay_per_turn=1.0)
    frame = _make_frame(
        turn=0,
        breaches=[
            [[3, 2], 5.0, SCOUT_IDX, "a", 2],   # opp breach AGAINST us → BL
            [[16, 15], 4.0, SCOUT_IDX, "b", 1],  # OUR breach → ignored
            [[24, 1], 2.0, SCOUT_IDX, "c", 2],   # opp breach → BR
        ],
    )
    b.consume_action_frame(frame)
    pressure = b.edge_pressure()
    assert pressure["BL"] == 5.0
    assert pressure["BR"] == 2.0
    # Our own breach was on player 2's side → TL/TR should both be zero
    assert pressure["TL"] == 0.0
    assert pressure["TR"] == 0.0
    assert b.n_breaches_seen() == 2


def test_resource_tracker_player_index_flip() -> None:
    r = ResourceTracker(self_player_id=1)
    # Opp stats live in p2Stats when self_player_id=1.
    frame = _make_frame(
        turn=0,
        p1_stats=[40.0, 8.0, 1.0, 0],
        p2_stats=[37.0, 12.5, 4.25, 0],
    )
    r.consume_action_frame(frame)
    latest = r.latest()
    assert latest is not None
    turn, sp, mp, hp = latest
    assert turn == 0
    assert sp == 12.5
    assert mp == 4.25
    assert hp == 37.0

    # Flip: as player 2 we should read p1Stats instead.
    r2 = ResourceTracker(self_player_id=2)
    r2.consume_action_frame(frame)
    _, sp2, mp2, hp2 = r2.latest()
    assert sp2 == 8.0
    assert mp2 == 1.0
    assert hp2 == 40.0


def test_misdirection_detector_player_index_flip() -> None:
    m = MisdirectionDetector(self_player_id=1, window_size=3)
    # Opp wall on the right-decoy tile (27, 14), right edge looks open
    # → right-side misdirection.
    frame = _make_frame(
        turn=0,
        p1_units=[[[0, 14, 60, "self_decoy"]], [], [], [], [], [], [], []],
        p2_units=[[[27, 14, 60, "opp_decoy"]], [], [], [], [], [], [], []],
    )
    m.consume_action_frame(frame, left_edge_open=False, right_edge_open=True)
    assert m.right_confidence() == 1.0
    # We should NOT register left-side misdirection — that decoy is OURS.
    assert m.left_confidence() == 0.0


# ===========================================================================
# Determinism — feed the same replay twice, results match
# ===========================================================================


def _run_all_utilities_on_replay(replay_path: Path):
    bct = BatchCountTracker()
    sxh = SpawnXHistogram()
    wrd = WallRemovalDetector()
    blt = BreachLocationTracker()
    rt = ResourceTracker()
    md = MisdirectionDetector()
    for frame in _iter_action_frames(replay_path):
        bct.consume_action_frame(frame)
        sxh.consume_action_frame(frame)
        wrd.consume_action_frame(frame)
        blt.consume_action_frame(frame)
        rt.consume_action_frame(frame)
        md.consume_action_frame(frame, left_edge_open=True, right_edge_open=True)
    return bct, sxh, wrd, blt, rt, md


def test_determinism_same_replay_twice() -> None:
    replay = REPO_ROOT / CORPUS_FILES[0]
    if not replay.exists():
        pytest.skip(f"missing replay: {replay}")
    a = _run_all_utilities_on_replay(replay)
    b = _run_all_utilities_on_replay(replay)
    # BatchCount: per_turn_counts and running_totals must match
    assert a[0].per_turn_counts == b[0].per_turn_counts
    assert a[0].running_totals == b[0].running_totals
    # Histogram: numpy arrays must compare equal element-wise
    assert np.array_equal(a[1].counts, b[1].counts)
    # WallRemoval: latest_flagged + n_turns_observed
    assert a[2].latest_flagged() == b[2].latest_flagged()
    assert a[2].n_turns_observed() == b[2].n_turns_observed()
    # Breach: tile dict + edge dict + total damage
    assert a[3].per_tile == b[3].per_tile
    assert a[3].edge_pressure() == b[3].edge_pressure()
    assert a[3].total_damage == b[3].total_damage
    # Resource: histories
    assert a[4].sp_history == b[4].sp_history
    assert a[4].mp_history == b[4].mp_history
    assert a[4].hp_history == b[4].hp_history
    # Misdirection: confidences
    assert a[5].left_confidence() == b[5].left_confidence()
    assert a[5].right_confidence() == b[5].right_confidence()


# ===========================================================================
# Corpus validation — 5 replays, all utilities, no crashes, well-formed output
# ===========================================================================


@pytest.mark.parametrize("rel_path", CORPUS_FILES)
def test_corpus_no_crash_well_formed(rel_path: str) -> None:
    replay = REPO_ROOT / rel_path
    if not replay.exists():
        pytest.skip(f"missing replay: {replay}")
    bct, sxh, wrd, blt, rt, md = _run_all_utilities_on_replay(replay)

    # BatchCountTracker
    assert bct.n_turns_observed() > 0
    for type_idx, n in bct.running_totals.items():
        assert isinstance(type_idx, int) and 0 <= type_idx <= 7
        assert n >= 0

    # SpawnXHistogram
    dist = sxh.spawn_x_distribution()
    assert dist.shape == (ARENA_SIZE,)
    assert np.all(dist >= 0.0)
    peaks = sxh.peak_spawn_columns(top_k=3)
    assert all(0 <= x < ARENA_SIZE for x in peaks)
    assert len(peaks) <= 3

    # WallRemovalDetector — slightly more permissive: some opponents
    # never use attempt_remove, so no crash + well-formed is the bar.
    assert wrd.n_turns_observed() > 0
    for x, y in wrd.latest_flagged():
        assert 0 <= x < ARENA_SIZE
        assert 0 <= y < ARENA_SIZE

    # BreachLocationTracker
    pressure = blt.edge_pressure()
    assert set(pressure.keys()) == {"BL", "BR", "TL", "TR"}
    for v in pressure.values():
        assert v >= 0.0
    assert blt.total_damage >= 0.0

    # ResourceTracker
    latest = rt.latest()
    if latest is not None:
        turn, sp, mp, hp = latest
        assert turn >= 0
        assert sp >= 0.0
        assert mp >= 0.0
        # HP can dip into negative on a finishing blow — that's fine.

    # MisdirectionDetector
    lc = md.left_confidence()
    rc = md.right_confidence()
    assert 0.0 <= lc <= 1.0
    assert 0.0 <= rc <= 1.0
