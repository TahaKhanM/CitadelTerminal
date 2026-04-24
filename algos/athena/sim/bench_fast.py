"""Benchmark harness for SimCore FAST action-phase on mid_game_turn.json.

Reports best/median sims/s over 15 samples of N runs each, using
time.perf_counter_ns for timing. Loads the Rust port's canonical fixture
so we benchmark against the same snapshot Rust does.
"""

from __future__ import annotations

import copy
import json
import statistics
import sys
import time
from pathlib import Path
from typing import List

import numpy as np

# Make `from sim.xxx` imports work when run as `python3 bench_fast.py`
# from the sim/ directory or via `python3 -m sim.bench_fast` from above.
_HERE = Path(__file__).resolve().parent
if str(_HERE.parent) not in sys.path:
    sys.path.insert(0, str(_HERE.parent))

from sim.config import SimConfig  # noqa: E402
from sim.pysim import simulate_action_phase  # noqa: E402
from sim.state import Mobile, PlayerStats, SimState, Structure  # noqa: E402


FP32 = np.float32
FIXTURE = (
    Path(__file__).resolve().parent.parent  # algos/athena/
    / "sim_rs" / "fixtures" / "mid_game_turn.json"
)


def load_fixture(path: Path = FIXTURE) -> SimState:
    raw = json.loads(path.read_text())
    structs = {}
    for s in raw["structures"]:
        xy = (int(s["xy"][0]), int(s["xy"][1]))
        structs[xy] = Structure(
            xy=xy,
            type_idx=int(s["type_idx"]),
            upgraded=bool(s["upgraded"]),
            hp=FP32(s["hp"]),
            uid=str(s["uid"]),
            player=int(s["player"]),
            turn_start_removal=s.get("turn_start_removal"),
        )
    mobiles = []
    for m in raw["mobiles"]:
        xy = (int(m["xy"][0]), int(m["xy"][1]))
        mobiles.append(Mobile(
            xy=xy,
            type_idx=int(m["type_idx"]),
            hp=FP32(m["hp"]),
            shield=FP32(m["shield"]),
            uid=str(m["uid"]),
            player=int(m["player"]),
            spawn_xy=(int(m["spawn_xy"][0]), int(m["spawn_xy"][1])),
            target_edge=int(m["target_edge"]),
            steps_taken=int(m.get("steps_taken", 0)),
            move_buildup=float(m.get("move_buildup", 0.0)),
            last_move=int(m.get("last_move", 0)),
            finished_navigating=bool(m.get("finished_navigating", False)),
            reached_target=bool(m.get("reached_target", False)),
            breached=bool(m.get("breached", False)),
        ))
    state = SimState(
        turn=int(raw["turn"]),
        structures=structs,
        mobiles=mobiles,
        p1=PlayerStats(
            hp=FP32(raw["p1"]["hp"]),
            sp=FP32(raw["p1"]["sp"]),
            mp=FP32(raw["p1"]["mp"]),
        ),
        p2=PlayerStats(
            hp=FP32(raw["p2"]["hp"]),
            sp=FP32(raw["p2"]["sp"]),
            mp=FP32(raw["p2"]["mp"]),
        ),
    )
    return state


def fast_clone(base: SimState) -> SimState:
    """Zero-overhead clone for the bench: walks the base state's known
    slots once per Structure/Mobile/PlayerStats rather than paying
    copy.deepcopy's __reduce_ex__ + memo-dict overhead.

    Assumes pathfinders is None on base (bench sets this implicitly via
    load_fixture). Each sim bootstraps its pathfinder on first use."""
    structs = {}
    for xy, s in base.structures.items():
        structs[xy] = Structure(
            xy=s.xy, type_idx=s.type_idx, upgraded=s.upgraded,
            hp=s.hp, uid=s.uid, player=s.player,
            turn_start_removal=s.turn_start_removal,
        )
    mobiles = []
    for m in base.mobiles:
        mobiles.append(Mobile(
            xy=m.xy, type_idx=m.type_idx, hp=m.hp, shield=m.shield,
            uid=m.uid, player=m.player, spawn_xy=m.spawn_xy,
            target_edge=m.target_edge, steps_taken=m.steps_taken,
            move_buildup=m.move_buildup, last_move=m.last_move,
            finished_navigating=m.finished_navigating,
            reached_target=m.reached_target, breached=m.breached,
        ))
    return SimState(
        turn=base.turn,
        structures=structs,
        mobiles=mobiles,
        p1=PlayerStats(hp=base.p1.hp, sp=base.p1.sp, mp=base.p1.mp),
        p2=PlayerStats(hp=base.p2.hp, sp=base.p2.sp, mp=base.p2.mp),
        pathfinders=None,
        pending_removal_xys=set(base.pending_removal_xys),
        _structure_gen=0,
        _attack_struct_cache=None,
        _move_step_cache=None,
        _attack_split_cache=None,
    )


def run_one(state: SimState, config: SimConfig) -> None:
    simulate_action_phase(state, config)


def bench(n_runs: int = 500, n_samples: int = 15) -> dict:
    config = SimConfig.load()
    base = load_fixture()
    # Warm up once (prime pathfinder caches etc.)
    warm = fast_clone(base)
    simulate_action_phase(warm, config)

    samples: List[float] = []
    for _ in range(n_samples):
        # Fresh state for this batch
        t_batch_start = time.perf_counter_ns()
        for _ in range(n_runs):
            s = fast_clone(base)
            simulate_action_phase(s, config)
        t_batch_end = time.perf_counter_ns()
        elapsed = (t_batch_end - t_batch_start) / 1e9
        samples.append(n_runs / elapsed)

    samples.sort(reverse=True)
    return {
        "n_runs": n_runs,
        "n_samples": n_samples,
        "best_sims_per_s": samples[0],
        "median_sims_per_s": statistics.median(samples),
        "worst_sims_per_s": samples[-1],
        "all": samples,
    }


def bench_sim_only(n_runs: int = 500, n_samples: int = 15) -> dict:
    """Time simulate_action_phase on pre-cloned states, N runs per sample.

    Pre-cloning all N states before the timed window removes clone cost
    from the measurement, which is the spec-mandated setup. A full
    gc.collect() after each sample drains gen-2 garbage from the prior
    timed window so it doesn't bleed into the next sample's
    measurement. Inside the window GC stays enabled so any mid-sim
    collection cost is counted (real throughput).
    """
    import gc

    config = SimConfig.load()
    base = load_fixture()
    # Warmup
    simulate_action_phase(fast_clone(base), config)

    samples: List[float] = []
    for _ in range(n_samples):
        clones = [fast_clone(base) for _ in range(n_runs)]
        gc.collect()
        gc.collect()
        t0 = time.perf_counter_ns()
        for s in clones:
            simulate_action_phase(s, config)
        t1 = time.perf_counter_ns()
        # Release clones before GC to prevent gen-2 thrashing across samples.
        del clones
        gc.collect()
        elapsed = (t1 - t0) / 1e9
        samples.append(n_runs / elapsed)

    samples.sort(reverse=True)
    return {
        "n_runs": n_runs,
        "n_samples": n_samples,
        "best_sims_per_s": samples[0],
        "median_sims_per_s": statistics.median(samples),
        "worst_sims_per_s": samples[-1],
        "all": samples,
    }


if __name__ == "__main__":
    import argparse
    ap = argparse.ArgumentParser()
    ap.add_argument("--n-runs", type=int, default=500)
    ap.add_argument("--n-samples", type=int, default=15)
    ap.add_argument("--mode", choices=("full", "sim-only"), default="sim-only")
    args = ap.parse_args()

    fn = bench_sim_only if args.mode == "sim-only" else bench
    r = fn(n_runs=args.n_runs, n_samples=args.n_samples)
    print(f"mode={args.mode} n_runs={r['n_runs']} n_samples={r['n_samples']}")
    print(f"  best   sims/s = {r['best_sims_per_s']:9.1f}")
    print(f"  median sims/s = {r['median_sims_per_s']:9.1f}")
    print(f"  worst  sims/s = {r['worst_sims_per_s']:9.1f}")
    print(f"  samples = {[round(x, 1) for x in r['all']]}")
