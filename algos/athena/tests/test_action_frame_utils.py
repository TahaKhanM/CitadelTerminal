"""Phase 0.5 gate: action-frame utilities produce deterministic output on
known replays AND explicitly guard the player_index flip (raw JSON 1/2 vs
starter API 0/1). Run from repo root:

    python3 algos/athena/tests/test_action_frame_utils.py
"""

from __future__ import annotations

import json
import os
import sys
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent.parent.parent
sys.path.insert(0, str(REPO / "algos" / "athena"))

from opponent.action_frame_utils import (  # noqa: E402
    ActionFrameConsumer,
    AF_OPP,
    AF_SELF,
    BatchCountTracker,
    BreachLocationTracker,
    IDX_DEMOLISHER,
    IDX_SCOUT,
    IDX_INTERCEPTOR,
    IDX_WALL,
    IDX_TURRET,
    IDX_SUPPORT,
    MisdirectionDetector,
    ResourceTracker,
    SpawnXHistogram,
    WallRemovalDetector,
)


# ------------------------------------------------------------- replay loader

def load_replay_frames(path: Path) -> list:
    """Return list of dicts, one per valid JSON frame (skipping the summary line)."""
    frames = []
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s.startswith("{"):
                continue
            try:
                frames.append(json.loads(s))
            except Exception:
                continue
    return frames


# -------------------------------------------------------- synthetic fixtures

def _mk_frame(turn: int, phase: int = 1, frame_num: int = 0, sim_t: int = 0,
              spawns=None, breaches=None, p1Stats=None, p2Stats=None,
              p2Units=None) -> dict:
    """Build a minimal action-frame dict for unit tests."""
    return {
        "turnInfo": [phase, turn, frame_num, sim_t],
        "p1Stats": p1Stats if p1Stats is not None else [40.0, 10.0, 2.0, 0],
        "p2Stats": p2Stats if p2Stats is not None else [40.0, 8.0, 3.0, 0],
        "p1Units": [[], [], [], [], [], [], [], []],
        "p2Units": p2Units if p2Units is not None else [[], [], [], [], [], [], [], []],
        "events": {
            "selfDestruct": [], "breach": breaches or [], "damage": [],
            "shield": [], "move": [], "spawn": spawns or [],
            "death": [], "attack": [], "melee": [],
        },
    }


# -------------------------------------------------------------- unit tests

def test_player_index_flip_excludes_self_spawns():
    """The common bug: treating spawn[3]==1 (which is SELF in action-frame
    JSON) as 'enemy'. Our BatchCountTracker must ignore it."""
    bc = BatchCountTracker()
    frame = _mk_frame(
        turn=0,
        spawns=[
            # our own Scout spawn at [13,0], player=1 (self)
            [[13, 0], IDX_SCOUT, "s1", AF_SELF],
            # opponent Scout spawn at [14, 27], player=2 (opp)
            [[14, 27], IDX_SCOUT, "s2", AF_OPP],
            # opponent Demolisher, player=2
            [[13, 27], IDX_DEMOLISHER, "s3", AF_OPP],
        ],
    )
    bc.on_frame(frame)
    bc.finalize()
    assert bc.enemy_scouts(0) == 1, f"expected 1 enemy scout, got {bc.enemy_scouts(0)}"
    assert bc.enemy_demolishers(0) == 1, f"expected 1 enemy demo, got {bc.enemy_demolishers(0)}"
    assert bc.enemy_total_mobile(0) == 2
    print("  ✓ player-index flip: self spawns excluded, enemy counted")


def test_batch_count_across_turns():
    bc = BatchCountTracker()
    # Turn 0: 3 enemy scouts
    f1 = _mk_frame(0, spawns=[
        [[14, 27], IDX_SCOUT, "a", AF_OPP],
        [[13, 27], IDX_SCOUT, "b", AF_OPP],
        [[15, 26], IDX_SCOUT, "c", AF_OPP],
    ])
    # Turn 1: 2 enemy demos
    f2 = _mk_frame(1, spawns=[
        [[14, 27], IDX_DEMOLISHER, "d", AF_OPP],
        [[13, 27], IDX_DEMOLISHER, "e", AF_OPP],
    ])
    # End-of-game frame (no spawns) to force finalize
    f3 = _mk_frame(1, phase=0, frame_num=99)
    bc.on_frame(f1); bc.on_frame(f2); bc.on_frame(f3)
    bc.finalize()
    assert bc.enemy_scouts(0) == 3
    assert bc.enemy_demolishers(1) == 2
    assert bc.enemy_total_mobile(0) == 3
    assert bc.last_n_turn_totals(2, 1) == [3, 2]
    print("  ✓ batch count turn-boundary transitions")


def test_histogram_decay_and_hot_columns():
    h = SpawnXHistogram(decay=0.5)
    # Turn 0: 2 scouts at x=14, 1 at x=3
    h.on_frame(_mk_frame(0, spawns=[
        [[14, 27], IDX_SCOUT, "a", AF_OPP],
        [[14, 27], IDX_SCOUT, "b", AF_OPP],
        [[3, 27], IDX_SCOUT, "c", AF_OPP],
    ]))
    # Turn 1: no spawns — decay should halve bins at x=14, x=3
    h.on_frame(_mk_frame(1))
    assert abs(h.bins[14] - 1.0) < 1e-9, f"x=14 expected 1.0 after decay, got {h.bins[14]}"
    assert abs(h.bins[3] - 0.5) < 1e-9
    # Turn 2: 1 spawn at x=20
    h.on_frame(_mk_frame(2, spawns=[[[20, 27], IDX_DEMOLISHER, "d", AF_OPP]]))
    # x=14 halved again to 0.5; x=20 now 1.0
    assert abs(h.bins[14] - 0.5) < 1e-9
    assert abs(h.bins[20] - 1.0) < 1e-9
    top = h.hottest_columns(2)
    assert 20 in top and 14 in top
    print("  ✓ spawn histogram decay + hottest_columns")


def test_histogram_ignores_self_and_structures():
    h = SpawnXHistogram()
    h.on_frame(_mk_frame(0, spawns=[
        [[14, 0], IDX_SCOUT, "a", AF_SELF],       # self — ignore
        [[14, 27], IDX_WALL, "b", AF_OPP],        # structure — ignore
        [[14, 27], IDX_SCOUT, "c", AF_OPP],       # enemy mobile — count
    ]))
    assert h.bins[14] == 1.0, f"expected only 1 hit, got {h.bins[14]}"
    print("  ✓ histogram ignores self-spawns AND structures")


def test_breach_tracker_only_counts_damage_against_self():
    tr = BreachLocationTracker()
    tr.on_frame(_mk_frame(3, breaches=[
        # opponent breach on us at [16,2] for 1dmg (Scout type 3)
        [[16, 2], 1.0, IDX_SCOUT, "sc1", AF_OPP],
        # we breach opponent at [16, 25] — should be IGNORED
        [[16, 25], 1.0, IDX_SCOUT, "sc2", AF_SELF],
    ]))
    hit = tr.tiles_hit()
    assert (16, 2) in hit and hit[(16, 2)] == 1.0
    assert (16, 25) not in hit, "breach scored by SELF must not appear as 'against us'"
    print("  ✓ breach tracker filters out self-scored breaches")


def test_misdirection_detector_both_sides_on_same_turn():
    m = MisdirectionDetector()
    m.on_frame(_mk_frame(5, spawns=[
        [[3, 27], IDX_SCOUT, "l", AF_OPP],
        [[24, 27], IDX_SCOUT, "r", AF_OPP],
    ]))
    assert m.is_enemy_misdirecting(5)
    assert m.split_ratio(5) == (1, 1)
    # Single-side turn isn't misdirection
    m.on_frame(_mk_frame(6, spawns=[[[3, 27], IDX_SCOUT, "x", AF_OPP]]))
    assert not m.is_enemy_misdirecting(6)
    print("  ✓ misdirection: left+right on same turn triggers flag")


def test_resource_tracker_only_snaps_deploy_frame():
    r = ResourceTracker()
    # Deploy frame (phase=1 frame_num=0) — should snapshot
    r.on_frame(_mk_frame(2, phase=1, frame_num=0,
                         p1Stats=[40.0, 12.0, 3.5, 0],
                         p2Stats=[38.0, 9.0, 4.2, 0]))
    # Action frame mid-turn — should NOT overwrite
    r.on_frame(_mk_frame(2, phase=1, frame_num=50,
                         p1Stats=[40.0, 0.0, 0.0, 0],
                         p2Stats=[38.0, 0.0, 0.0, 0]))
    snap = r.opp[2]
    assert snap.mp == 4.2, f"resource tracker overwrote mid-turn: got mp={snap.mp}"
    assert snap.sp == 9.0
    assert r.self[2].mp == 3.5
    print("  ✓ resource tracker only snapshots phase=1 frame=0")


def test_wall_removal_detector_dedupes_across_frames():
    wr = WallRemovalDetector()
    p2_with_flagged = [
        # FF at [10,14] pending_removal=True, appearing across 3 frames
        [[10, 14, 60.0, "u1", True]],
        [], [], [], [], [], [], [],
    ]
    for fn in range(3):
        wr.on_frame(_mk_frame(4, phase=1, frame_num=fn, p2Units=p2_with_flagged))
    flagged = wr.structures_flagged_on(4)
    assert len(flagged) == 1
    assert flagged[0] == (10, 14, IDX_WALL)
    print("  ✓ wall-removal detector dedupes repeats across frames")


def test_consumer_runs_real_replay_deterministically():
    """Feed a real v13 replay twice; assert identical output."""
    replays_dir = REPO / "replays" / "ranked"
    replay_paths = list(replays_dir.glob("*.replay"))
    assert replay_paths, "no ranked replays on disk — Phase 0.4 not complete"
    path = sorted(replay_paths)[0]
    frames = load_replay_frames(path)
    assert len(frames) > 100, f"replay {path.name} only has {len(frames)} frames"

    c1 = ActionFrameConsumer(); c1.feed_iter(frames)
    c2 = ActionFrameConsumer(); c2.feed_iter(frames)

    # Deterministic: two runs produce identical turn_counts, bins, and breach list.
    assert c1.batch_count.turn_counts == c2.batch_count.turn_counts
    assert c1.spawn_histogram.bins == c2.spawn_histogram.bins
    assert [ (b.turn, b.xy, b.damage) for b in c1.breach_locations.breaches ] == \
           [ (b.turn, b.xy, b.damage) for b in c2.breach_locations.breaches ]
    print(f"  ✓ deterministic over real replay {path.name}")

    # Sanity facts: at least some enemy spawns must have been seen across the game.
    total_enemy_mobile = sum(sum(d.values()) for d in c1.batch_count.turn_counts.values())
    assert total_enemy_mobile > 0, "no enemy mobile spawns parsed — bug"
    # And some opponent MP snapshots captured
    assert len(c1.resources.opp) > 5, "resource tracker missed most turns"

    hot = c1.spawn_histogram.hottest_columns(3)
    print(f"    stats: total_enemy_mobile={total_enemy_mobile} "
          f"hot_cols={hot} opp_mp_snaps={len(c1.resources.opp)}")


def test_consumer_runs_all_23_replays():
    """Smoke-test: every ranked replay parses cleanly with no exceptions."""
    replays_dir = REPO / "replays" / "ranked"
    paths = sorted(replays_dir.glob("*.replay"))
    assert len(paths) >= 20, f"expected ≥20 ranked replays, found {len(paths)}"
    for p in paths:
        frames = load_replay_frames(p)
        c = ActionFrameConsumer()
        c.feed_iter(frames)
        # Basic invariants per replay
        assert c.spawn_histogram.bins is not None
        # Opponent MP monotone-ish: capped at 150
        for snap in c.resources.opp.values():
            assert 0 <= snap.mp <= 150
    print(f"  ✓ {len(paths)} replays all parse cleanly")


# --------------------------------------------------------------------- main

TESTS = [
    test_player_index_flip_excludes_self_spawns,
    test_batch_count_across_turns,
    test_histogram_decay_and_hot_columns,
    test_histogram_ignores_self_and_structures,
    test_breach_tracker_only_counts_damage_against_self,
    test_misdirection_detector_both_sides_on_same_turn,
    test_resource_tracker_only_snaps_deploy_frame,
    test_wall_removal_detector_dedupes_across_frames,
    test_consumer_runs_real_replay_deterministically,
    test_consumer_runs_all_23_replays,
]


def main() -> int:
    print(f"Running {len(TESTS)} action-frame util tests…\n")
    failed = 0
    for t in TESTS:
        print(t.__name__ + ":")
        try:
            t()
        except Exception as e:
            print(f"  ✗ FAIL: {e}")
            import traceback; traceback.print_exc()
            failed += 1
        print()
    if failed:
        print(f"{failed}/{len(TESTS)} FAILED")
        return 1
    print(f"All {len(TESTS)} tests passed.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
