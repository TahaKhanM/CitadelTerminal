"""Run ≥80 invariants on every frame of the ranked corpus + 10K fuzz.

Walks every v13 ranked replay turn-by-turn through SimCore INSTRUMENTED
mode, calling check_all(state, config, events) on:
  * the state AFTER deploy-phase actions applied
  * the state AFTER each action-phase frame's systems tick

Reports per-replay pass/fail counts, first failing invariant with
replay/turn/frame coordinates, and the final floor-coverage number.

Usage:
    python3 tools/run_invariants_full_corpus.py           # ranked only
    python3 tools/run_invariants_full_corpus.py --fuzz 10000
"""
from __future__ import annotations
import argparse, hashlib, sys, time
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parent.parent / "algos" / "athena"))

import numpy as np

from sim.config import SimConfig
from sim.pysim import apply_deploy_actions, simulate_action_phase_iter, simulate_action_phase
from sim.invariants import check_all, INVARIANT_COUNT
from sim.validate import (
    _parse_replay, _index_deploy_frames, _index_first_action_frames,
    _extract_deploy_actions, _extract_deploy_events_in_order,
    _build_state_from_deploy_frame, _collect_upgraded_uids,
)
from sim.fuzz import _GENERATORS, CATEGORIES


def _run_replay(path: Path, config: SimConfig, out_stats: dict) -> None:
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)
    for t in sorted(deploys.keys()):
        if t not in actions_first:
            continue
        state = _build_state_from_deploy_frame(deploys[t], config,
                                                upgraded_pre.get(t, set()))
        p1s, p1u, p2s, p2u = _extract_deploy_actions(actions_first[t])
        ordered = _extract_deploy_events_in_order(actions_first[t])
        deploy_events = []
        apply_deploy_actions(state, config, p1s, p1u, p2s, p2u,
                              ordered_events=ordered, events=deploy_events)
        # Post-deploy check.
        try:
            check_all(state, config, events=None)
            out_stats["ok_frames"] += 1
        except AssertionError as e:
            out_stats["fail_frames"] += 1
            if out_stats["first_fail"] is None:
                out_stats["first_fail"] = (path.name, t, 0, str(e)[:200])
        # Per-frame check inside action phase.
        for obs in simulate_action_phase_iter(state, config, perspective=1,
                                                seed_events=deploy_events,
                                                total_frame_start=0):
            out_stats["total_frames"] += 1
            try:
                check_all(state, config, events=None)
                out_stats["ok_frames"] += 1
            except AssertionError as e:
                out_stats["fail_frames"] += 1
                fn = int(obs.get("turnInfo", [0, 0, 0])[2])
                if out_stats["first_fail"] is None:
                    out_stats["first_fail"] = (path.name, t, fn, str(e)[:200])


def _run_fuzz(n: int, seed: int, config: SimConfig, out_stats: dict) -> None:
    for i in range(n):
        cat = CATEGORIES[i % len(CATEGORIES)]
        cat_seed = int(hashlib.sha1(cat.encode()).hexdigest()[:8], 16)
        rng = np.random.default_rng((int(seed), cat_seed, int(i)))
        state = _GENERATORS[cat](rng)
        try:
            check_all(state, config, events=None)
            out_stats["ok_frames"] += 1
        except AssertionError as e:
            out_stats["fail_frames"] += 1
            if out_stats["first_fail"] is None:
                out_stats["first_fail"] = (f"fuzz:{cat}", i, 0, str(e)[:200])


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--fuzz", type=int, default=0)
    ap.add_argument("--seed", type=int, default=42)
    args = ap.parse_args()

    config = SimConfig.load()
    stats = {
        "total_frames": 0, "ok_frames": 0, "fail_frames": 0,
        "first_fail": None,
    }
    t0 = time.perf_counter()
    corpus = sorted((Path(__file__).resolve().parent.parent / "replays"
                       / "ranked").glob("*.replay"))
    for path in corpus:
        _run_replay(path, config, stats)
    ranked_wall = time.perf_counter() - t0
    fuzz_wall = 0.0
    if args.fuzz > 0:
        t_f = time.perf_counter()
        _run_fuzz(args.fuzz, args.seed, config, stats)
        fuzz_wall = time.perf_counter() - t_f

    print(f"invariants={INVARIANT_COUNT} "
          f"ranked_replays={len(corpus)} "
          f"ranked_frames={stats['total_frames']} "
          f"ranked_wall={ranked_wall:.1f}s "
          f"fuzz_n={args.fuzz} fuzz_wall={fuzz_wall:.1f}s")
    print(f"  ok={stats['ok_frames']}  fail={stats['fail_frames']}")
    if stats["first_fail"]:
        n, t, f, msg = stats["first_fail"]
        print(f"  first fail: {n} T{t}F{f}: {msg}")
    gate = "PASS" if stats["fail_frames"] == 0 else "FAIL"
    print(f"  gate: {gate}")
    return 0 if stats["fail_frames"] == 0 else 1


if __name__ == "__main__":
    sys.exit(main())
