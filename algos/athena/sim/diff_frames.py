"""Frame-by-frame diff between SimCore and a replay's action phase.

For a single turn, this runs SimCore one frame at a time (by instrumenting
simulate_action_phase) and compares the events emitted per frame against the
replay's action-frame events. Report the FIRST frame where sim diverges.

Only looks at event COUNTS (attack/death/breach/selfDestruct/shield/move)
per frame; divergence usually shows up in attack or death counts.
"""

from __future__ import annotations

import json
import sys
from collections import Counter
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig  # noqa: E402
from sim.pysim import (  # noqa: E402
    apply_deploy_actions,
    clear_destroyed,
    system_attack,
    system_breach,
    system_collision,
    system_move,
    system_self_destruct,
    system_shield_decay,
    system_shield_give,
)
from sim.state import SimState  # noqa: E402
from sim.validate import (  # noqa: E402
    _build_state_from_deploy_frame,
    _collect_upgraded_uids,
    _extract_deploy_actions,
    _index_deploy_frames,
    _index_first_action_frames,
    _parse_replay,
)


def _frame_event_counts(events: list) -> Counter:
    c = Counter()
    for e in events:
        if isinstance(e, dict):
            c[e.get("type", "?")] += 1
        elif isinstance(e, (list, tuple)):
            # replay-style event list — count as list-appearance
            c["entry"] += 1
    return c


def replay_frames_in_turn(frames: list, turn: int) -> list:
    """All phase=1 frames in the requested turn, in order."""
    out = []
    for f in frames:
        ti = f.get("turnInfo") or []
        if len(ti) >= 3 and ti[0] == 1 and ti[1] == turn:
            out.append(f)
    return out


def replay_event_counts_per_frame(replay_action_frames: list) -> list:
    """For each action frame in the replay, count events of each type."""
    rows = []
    for f in replay_action_frames:
        ev = f.get("events") or {}
        row = {k: len(v) for k, v in ev.items() if isinstance(v, list)}
        rows.append(row)
    return rows


def sim_frame_by_frame(state: SimState, config: SimConfig, max_frames: int = 300):
    """Yield per-frame event lists for one action phase."""
    f = 0
    while f < max_frames:
        pre_events = []
        clear_destroyed(state, pre_events)
        if not state.mobiles:
            return

        frame_events = []
        system_move(state, config, frame_events)
        system_collision(state)
        system_shield_decay(state, config)
        system_shield_give(state, config, frame_events)
        system_breach(state, config, frame_events)
        system_self_destruct(state, config, frame_events)
        system_attack(state, config, frame_events)
        yield f, frame_events + pre_events
        f += 1


def diff_frames(path: Path, turn: int, config: SimConfig) -> None:
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions = _index_first_action_frames(frames)
    ups = _collect_upgraded_uids(frames)
    if turn not in deploys or turn + 1 not in deploys:
        print(f"Turn {turn} not fully bracketed; skipping.")
        return

    # Build starting state + apply deploy actions
    state = _build_state_from_deploy_frame(deploys[turn], config, ups.get(turn, set()))
    p1s, p1u, p2s, p2u = _extract_deploy_actions(actions[turn])
    apply_deploy_actions(state, config, p1s, p1u, p2s, p2u)

    # Replay action-phase frames for this turn (phase=1, same turn)
    rep_frames = replay_frames_in_turn(frames, turn)
    rep_counts = replay_event_counts_per_frame(rep_frames)

    print(f"Turn {turn}: replay has {len(rep_frames)} action frames")
    print(f"{'f':>3}  {'sim_counts':<40}  {'replay_counts':<40}  divergence?")

    sim_iter = sim_frame_by_frame(state, config)
    for i, (f_idx, events) in enumerate(sim_iter):
        sim_c = _frame_event_counts(events)
        rep_c = rep_counts[i] if i < len(rep_counts) else {}
        sim_s = ", ".join(f"{k}={v}" for k, v in sorted(sim_c.items()))
        rep_s = ", ".join(f"{k}={v}" for k, v in sorted(rep_c.items()))
        # compare attack, breach, death, selfDestruct between sim and replay.
        critical = ["attack", "breach", "death", "selfDestruct"]
        divergent = False
        for k in critical:
            if sim_c.get(k, 0) != rep_c.get(k, 0):
                divergent = True
                break
        mark = "  ← DIVERGE" if divergent else ""
        print(f"{f_idx:>3}  {sim_s:<40}  {rep_s:<40}{mark}")
        if divergent and i < 5:
            # Show first divergence in detail
            print(f"     sim events[:3]: {events[:3]}")
            if i < len(rep_frames):
                rev = rep_frames[i].get("events", {})
                for key in critical:
                    if rev.get(key):
                        print(f"     replay {key}[:3]: {rev[key][:3]}")
            break


if __name__ == "__main__":
    if len(sys.argv) < 3:
        sys.exit("usage: diff_frames.py <replay> <turn>")
    diff_frames(Path(sys.argv[1]), int(sys.argv[2]), SimConfig.load())
