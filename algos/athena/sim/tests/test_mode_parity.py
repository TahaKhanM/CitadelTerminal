"""Dual-mode parity test (Phase 1.B.2).

SimCore ships two execution modes:

  * FAST (`simulate_action_phase`): runs the frame loop to completion
    with no per-frame observation materialization. Events are still
    appended to a flat list for consumers who need them post-phase, but
    no dict per frame is built and no p{1,2}Units snapshot is taken.
  * INSTRUMENTED (`simulate_action_phase_iter`): yields one observation
    dict per action frame, shaped like the engine's replay action-phase
    frame. Pays snapshot cost every frame, required by the validator
    and downstream replay-style consumers.

Both modes share the exact same system-call sequence and state
mutations (pysim.py). This test runs every v13 ranked replay twice —
once per mode — and asserts that final state (HP/SP/MP, structures
set, mobiles set, upgrade flags) is byte-identical. If the two modes
ever diverge, something the instrumentation path does has mutated
state, which would quietly invalidate downstream planning.

Run:
  python3 -m algos.athena.sim.tests.test_mode_parity
Exit code:
  0 if every replay matches
  1 if any final-state delta detected
"""

from __future__ import annotations

import sys
import time
from pathlib import Path
from typing import List, Tuple

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent.parent))

from sim import pysim  # noqa: E402
from sim.config import SimConfig  # noqa: E402
from sim.pysim import (  # noqa: E402
    apply_deploy_actions,
    simulate_action_phase,
    simulate_action_phase_iter,
)
from sim.validate import (  # noqa: E402
    _parse_replay,
    _index_deploy_frames,
    _index_first_action_frames,
    _extract_deploy_actions,
    _extract_deploy_events_in_order,
    _build_state_from_deploy_frame,
    _collect_upgraded_uids,
)


def _state_snapshot(state) -> Tuple:
    """Reduce SimState to a hashable tuple for byte-equality comparison.
    Covers PlayerStats (hp/sp/mp), structures (xy, type_idx, upgraded,
    hp, uid, player), and mobiles (xy, type_idx, hp, shield, uid,
    player, steps_taken, frame_counter). Frame-transient fields
    (path cache, observers) intentionally NOT compared."""
    structs = tuple(sorted(
        (s.xy, s.type_idx, bool(s.upgraded), float(s.hp), str(s.uid), int(s.player))
        for s in state.structures.values()
    ))
    mobiles = tuple(sorted(
        (m.xy, m.type_idx, float(m.hp), float(m.shield), str(m.uid), int(m.player),
         int(getattr(m, "steps_taken", 0)), int(getattr(m, "frame_counter", 0)))
        for m in state.mobiles
    ))
    return (
        (float(state.p1.hp), float(state.p1.sp), float(state.p1.mp)),
        (float(state.p2.hp), float(state.p2.sp), float(state.p2.mp)),
        structs,
        mobiles,
    )


def _run_turn(df, af, config, upgraded_pre, mode: str):
    state = _build_state_from_deploy_frame(df, config, upgraded_pre)
    p1_sp, p1_up, p2_sp, p2_up = _extract_deploy_actions(af)
    ordered = _extract_deploy_events_in_order(af)
    deploy_events: List[dict] = []
    apply_deploy_actions(state, config, p1_sp, p1_up, p2_sp, p2_up,
                          ordered_events=ordered, events=deploy_events)
    if mode == "fast":
        simulate_action_phase(state, config)
    elif mode == "instrumented":
        # Drain the iterator — we compare final state, not per-frame dicts.
        for _ in simulate_action_phase_iter(
            state, config, perspective=1,
            seed_events=deploy_events, total_frame_start=0,
        ):
            pass
    else:
        raise ValueError(mode)
    return _state_snapshot(state)


def _walk_replay(path: Path, config: SimConfig) -> List[Tuple[int, str]]:
    """Return list of (turn, first-divergence-key) for any turn whose
    fast/instrumented snapshots differ. Empty list on clean parity."""
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)
    out: List[Tuple[int, str]] = []
    for t in sorted(deploys.keys()):
        if t not in actions_first:
            continue
        fast_snap = _run_turn(deploys[t], actions_first[t], config,
                               upgraded_pre.get(t, set()), "fast")
        inst_snap = _run_turn(deploys[t], actions_first[t], config,
                               upgraded_pre.get(t, set()), "instrumented")
        if fast_snap != inst_snap:
            # Identify first mismatching sub-tuple.
            labels = ("p1_stats", "p2_stats", "structures", "mobiles")
            divergence = "unknown"
            for i, lab in enumerate(labels):
                if fast_snap[i] != inst_snap[i]:
                    divergence = lab
                    break
            out.append((t, divergence))
    return out


def main() -> int:
    config = SimConfig.load()
    corpus = sorted((Path(__file__).resolve().parent.parent.parent.parent.parent
                     / "replays" / "ranked").glob("*.replay"))
    total_turns = 0
    failures: List[Tuple[str, int, str]] = []
    t0 = time.perf_counter()
    for path in corpus:
        diffs = _walk_replay(path, config)
        # Walk once to count turns (cheap — already walked).
        frames, _ = _parse_replay(path)
        deploys = _index_deploy_frames(frames)
        actions_first = _index_first_action_frames(frames)
        total_turns += sum(1 for t in deploys if t in actions_first)
        if diffs:
            for t, kind in diffs:
                failures.append((path.name, t, kind))
            print(f"  FAIL {path.name}: {len(diffs)} turns diverge")
        else:
            print(f"  pass {path.name}")
    wall = time.perf_counter() - t0
    print()
    print(f"Checked {total_turns} turns across {len(corpus)} replays "
          f"in {wall:.1f}s (×2 modes)")
    if failures:
        print(f"FAIL: {len(failures)} turn-level mode divergences")
        for n, t, k in failures[:10]:
            print(f"  {n} T{t}: {k}")
        return 1
    print("PASS: dual-mode byte-identical on every turn")
    return 0


if __name__ == "__main__":
    sys.exit(main())
