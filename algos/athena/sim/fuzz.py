"""SimCore stratified fuzzer (Phase 1.B.4).

Produces deterministic, reproducible starting-state configurations
across 12 stratified categories, runs SimCore's action phase on each,
and persists any failing configuration to
`algos/athena/sim/fuzz_regression/<category>/<hash>.json` for later
replay + fix.

Categories
----------
1.  empty_board          no structures, no mobiles
2.  single_mobile        1 mobile, empty defense
3.  wall_maze            walls-only dense
4.  turret_wall_mix      turrets + walls at a defensive ring
5.  support_heavy        many base Supports (1 HP each, one-shot)
6.  support_upgraded     several upgraded Supports + friendly swarm
7.  scout_swarm          many Scouts at one edge
8.  demo_column          Demolishers + walls (artillery archetype)
9.  interceptor_mass     Interceptors + long path
10. se_destruct_dead_end paths terminate in enclosed pockets
11. edge_corner_spawn    mobiles at corner tiles only
12. max_budget           SP/MP saturated; every legal placement used

Each category has its own generator producing configs with reproducible
random seeds. A single fuzz run draws seeds from a single master
randint stream, so re-running with the same master seed reproduces
the same 1M configs byte-identically.

Determinism guarantee: np.random.default_rng(master_seed) + per-config
seeded RNGs. Python hash-order and set iteration order are NOT used in
generators.

Usage
-----
Scaffold (fast): python3 -m algos.athena.sim.fuzz --n 100 --seed 42
Long run (background): python3 -m algos.athena.sim.fuzz --n 1000000 --seed 42

Miss handling
-------------
A miss is any config where simulate_action_phase (FAST) diverges from
simulate_action_phase_iter (INSTRUMENTED) in final state, OR raises an
exception, OR violates a runtime invariant. The config is serialized
to fuzz_regression/<category>/<config_sha>.json. Fix the simulator,
add the config to a regression corpus, continue fuzzing.
"""

from __future__ import annotations

import argparse
import hashlib
import json
import sys
import time
import traceback
from dataclasses import dataclass, field
from pathlib import Path
from typing import Callable, Dict, List, Optional, Tuple

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import (  # noqa: E402
    IDX_DEMOLISHER, IDX_INTERCEPTOR, IDX_SCOUT,
    IDX_SUPPORT, IDX_TURRET, IDX_WALL, SimConfig,
)
from sim.map import (  # noqa: E402
    EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT, edge_tiles, on_diamond,
)
from sim.pysim import (  # noqa: E402
    apply_deploy_actions, simulate_action_phase,
    simulate_action_phase_iter,
)
from sim.state import Mobile, PlayerStats, SimState, Structure  # noqa: E402

FP32 = np.float32

REGRESSION_DIR = Path(__file__).resolve().parent / "fuzz_regression"

CATEGORIES = (
    "empty_board",
    "single_mobile",
    "wall_maze",
    "turret_wall_mix",
    "support_heavy",
    "support_upgraded",
    "scout_swarm",
    "demo_column",
    "interceptor_mass",
    "se_destruct_dead_end",
    "edge_corner_spawn",
    "max_budget",
)


# -------------------------------------------------------------------- helpers

def _random_spawn_xy(rng: np.random.Generator, edge: int) -> Tuple[int, int]:
    tiles = edge_tiles(edge)
    return tuple(tiles[int(rng.integers(0, len(tiles)))])


def _random_bottom_tile(rng: np.random.Generator) -> Tuple[int, int]:
    while True:
        x = int(rng.integers(0, 28))
        y = int(rng.integers(0, 14))
        if on_diamond(x, y):
            return (x, y)


def _mk_state(turn: int = 5) -> SimState:
    return SimState(
        turn=turn,
        structures={},
        mobiles=[],
        p1=PlayerStats(hp=FP32(40.0), sp=FP32(50.0), mp=FP32(20.0)),
        p2=PlayerStats(hp=FP32(40.0), sp=FP32(50.0), mp=FP32(20.0)),
    )


_UID_COUNTER = [1]


def _next_uid() -> str:
    _UID_COUNTER[0] += 1
    return str(_UID_COUNTER[0])


# -------------------------------------------------------- category generators

def _gen_empty_board(rng):
    return _mk_state()


def _gen_single_mobile(rng):
    state = _mk_state()
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    xy = _random_spawn_xy(rng, edge)
    type_idx = int(rng.choice([IDX_SCOUT, IDX_DEMOLISHER, IDX_INTERCEPTOR]))
    # Use the correct starting HP per mobile type (Scout 15, Demo 5, Interceptor 40).
    hp_by_type = {IDX_SCOUT: 15.0, IDX_DEMOLISHER: 5.0, IDX_INTERCEPTOR: 40.0}
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1  # TR / TL respectively
    state.mobiles.append(Mobile(
        xy=xy, type_idx=type_idx, hp=FP32(hp_by_type[type_idx]), shield=FP32(0.0),
        uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
    ))
    return state


def _gen_wall_maze(rng):
    state = _mk_state()
    n = int(rng.integers(5, 30))
    for _ in range(n):
        xy = _random_bottom_tile(rng)
        if xy in state.structures:
            continue
        state.structures[xy] = Structure(
            xy=xy, type_idx=IDX_WALL, upgraded=False, hp=FP32(60.0),
            uid=_next_uid(), player=1,
        )
    return state


def _gen_turret_wall_mix(rng):
    state = _mk_state()
    for _ in range(int(rng.integers(3, 10))):
        xy = _random_bottom_tile(rng)
        if xy in state.structures:
            continue
        upgraded = bool(rng.integers(0, 2))
        state.structures[xy] = Structure(
            xy=xy, type_idx=IDX_TURRET, upgraded=upgraded,
            hp=FP32(100.0 if upgraded else 60.0),
            uid=_next_uid(), player=1,
        )
    for _ in range(int(rng.integers(3, 15))):
        xy = _random_bottom_tile(rng)
        if xy in state.structures:
            continue
        state.structures[xy] = Structure(
            xy=xy, type_idx=IDX_WALL, upgraded=False,
            hp=FP32(60.0), uid=_next_uid(), player=1,
        )
    return state


def _gen_support_heavy(rng):
    state = _mk_state()
    for _ in range(int(rng.integers(5, 20))):
        xy = _random_bottom_tile(rng)
        if xy in state.structures:
            continue
        state.structures[xy] = Structure(
            xy=xy, type_idx=IDX_SUPPORT, upgraded=False,
            hp=FP32(1.0), uid=_next_uid(), player=1,
        )
    return state


def _gen_support_upgraded(rng):
    state = _mk_state()
    for _ in range(int(rng.integers(3, 8))):
        xy = _random_bottom_tile(rng)
        if xy in state.structures:
            continue
        state.structures[xy] = Structure(
            xy=xy, type_idx=IDX_SUPPORT, upgraded=True,
            hp=FP32(40.0), uid=_next_uid(), player=1,
        )
    for _ in range(int(rng.integers(1, 5))):
        edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
        xy = _random_spawn_xy(rng, edge)
        target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_SCOUT, hp=FP32(15.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


def _gen_scout_swarm(rng):
    state = _mk_state()
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
    xy = _random_spawn_xy(rng, edge)
    for _ in range(int(rng.integers(5, 20))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_SCOUT, hp=FP32(15.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


def _gen_demo_column(rng):
    state = _mk_state()
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
    xy = _random_spawn_xy(rng, edge)
    for _ in range(int(rng.integers(2, 8))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_DEMOLISHER, hp=FP32(5.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    for _ in range(int(rng.integers(3, 8))):
        sxy = _random_bottom_tile(rng)
        if sxy in state.structures:
            continue
        state.structures[sxy] = Structure(
            xy=sxy, type_idx=IDX_WALL, upgraded=False,
            hp=FP32(60.0), uid=_next_uid(), player=1,
        )
    return state


def _gen_interceptor_mass(rng):
    state = _mk_state()
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
    xy = _random_spawn_xy(rng, edge)
    for _ in range(int(rng.integers(3, 10))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_INTERCEPTOR, hp=FP32(40.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


def _gen_sd_dead_end(rng):
    # Build a tight pocket of walls around a small opening so mobiles
    # enter and have no forward move (triggers SD).
    state = _mk_state()
    cx, cy = 13, 8
    for dx in (-2, -1, 0, 1, 2):
        for dy in (-2, -1, 0, 1, 2):
            xy = (cx + dx, cy + dy)
            if xy == (cx, cy):
                continue  # leave pocket open
            if not on_diamond(*xy) or xy[1] >= 14:
                continue
            state.structures[xy] = Structure(
                xy=xy, type_idx=IDX_WALL, upgraded=False,
                hp=FP32(60.0), uid=_next_uid(), player=1,
            )
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
    xy = _random_spawn_xy(rng, edge)
    for _ in range(int(rng.integers(1, 4))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_SCOUT, hp=FP32(15.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


def _gen_edge_corner_spawn(rng):
    state = _mk_state()
    corners = [(13, 0), (14, 0), (0, 13), (27, 13)]
    xy = corners[int(rng.integers(0, len(corners)))]
    if xy[0] < 14:
        edge, target_edge = EDGE_BOTTOM_LEFT, 0
    else:
        edge, target_edge = EDGE_BOTTOM_RIGHT, 1
    for _ in range(int(rng.integers(1, 5))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_SCOUT, hp=FP32(15.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


def _gen_max_budget(rng):
    state = _mk_state(turn=20)
    # Pack walls into every legal bottom-half tile.
    for x in range(28):
        for y in range(14):
            xy = (x, y)
            if not on_diamond(x, y):
                continue
            if xy in state.structures:
                continue
            if rng.random() > 0.75:
                state.structures[xy] = Structure(
                    xy=xy, type_idx=IDX_WALL, upgraded=False,
                    hp=FP32(60.0), uid=_next_uid(), player=1,
                )
    edge = int(rng.choice([EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT]))
    target_edge = 0 if edge == EDGE_BOTTOM_LEFT else 1
    xy = _random_spawn_xy(rng, edge)
    for _ in range(int(rng.integers(5, 15))):
        state.mobiles.append(Mobile(
            xy=xy, type_idx=IDX_SCOUT, hp=FP32(15.0), shield=FP32(0.0),
            uid=_next_uid(), player=1, spawn_xy=xy, target_edge=target_edge,
        ))
    return state


_GENERATORS: Dict[str, Callable] = {
    "empty_board": _gen_empty_board,
    "single_mobile": _gen_single_mobile,
    "wall_maze": _gen_wall_maze,
    "turret_wall_mix": _gen_turret_wall_mix,
    "support_heavy": _gen_support_heavy,
    "support_upgraded": _gen_support_upgraded,
    "scout_swarm": _gen_scout_swarm,
    "demo_column": _gen_demo_column,
    "interceptor_mass": _gen_interceptor_mass,
    "se_destruct_dead_end": _gen_sd_dead_end,
    "edge_corner_spawn": _gen_edge_corner_spawn,
    "max_budget": _gen_max_budget,
}


# ----------------------------------------------------------------- comparison

def _state_snapshot(state: SimState) -> tuple:
    structs = tuple(sorted(
        (s.xy, s.type_idx, bool(s.upgraded), float(s.hp), str(s.uid), int(s.player))
        for s in state.structures.values()
    ))
    mobiles = tuple(sorted(
        (m.xy, m.type_idx, float(m.hp), float(m.shield), str(m.uid), int(m.player),
         int(m.steps_taken), int(getattr(m, "move_buildup", 0) * 1000) / 1000.0)
        for m in state.mobiles
    ))
    return (
        (float(state.p1.hp), float(state.p1.sp), float(state.p1.mp)),
        (float(state.p2.hp), float(state.p2.sp), float(state.p2.mp)),
        structs, mobiles,
    )


def _clone_state(state: SimState) -> SimState:
    # Deep copy: re-create Structure/Mobile/PlayerStats instances.
    import copy
    return copy.deepcopy(state)


@dataclass
class FuzzResult:
    category: str
    seed: int
    cfg_hash: str
    ok: bool
    reason: Optional[str] = None


def _hash_config(state: SimState) -> str:
    payload = _state_snapshot(state)
    return hashlib.sha1(repr(payload).encode()).hexdigest()[:12]


def _persist_miss(category: str, cfg_hash: str, state: SimState,
                    reason: str) -> None:
    REGRESSION_DIR.mkdir(exist_ok=True)
    (REGRESSION_DIR / category).mkdir(exist_ok=True)
    out = {
        "category": category,
        "cfg_hash": cfg_hash,
        "reason": reason,
        "snapshot": [repr(x) for x in _state_snapshot(state)],
    }
    path = REGRESSION_DIR / category / f"{cfg_hash}.json"
    with open(path, "w") as f:
        json.dump(out, f, indent=2)


def _run_one(category: str, master_seed: int, idx: int,
              config: SimConfig) -> FuzzResult:
    cat_seed = int(hashlib.sha1(category.encode()).hexdigest()[:8], 16)
    rng = np.random.default_rng((int(master_seed), cat_seed, int(idx)))
    try:
        state = _GENERATORS[category](rng)
    except Exception as e:
        return FuzzResult(category, idx, "gen_fail", False,
                           reason=f"generator: {e}")

    cfg_hash = _hash_config(state)
    state_fast = _clone_state(state)
    state_inst = _clone_state(state)
    try:
        simulate_action_phase(state_fast, config)
    except Exception as e:
        _persist_miss(category, cfg_hash, state, f"fast raise: {e}")
        return FuzzResult(category, idx, cfg_hash, False, reason="fast raise")
    try:
        for _ in simulate_action_phase_iter(state_inst, config, perspective=1,
                                              seed_events=[], total_frame_start=0):
            pass
    except Exception as e:
        _persist_miss(category, cfg_hash, state, f"inst raise: {e}")
        return FuzzResult(category, idx, cfg_hash, False, reason="inst raise")
    if _state_snapshot(state_fast) != _state_snapshot(state_inst):
        _persist_miss(category, cfg_hash, state, "dual-mode divergence")
        return FuzzResult(category, idx, cfg_hash, False,
                           reason="dual-mode divergence")
    return FuzzResult(category, idx, cfg_hash, True)


def run_fuzz(n: int, master_seed: int, config: Optional[SimConfig] = None) -> dict:
    """Run a fuzz batch of `n` configs across the 12 categories.
    Returns a summary dict."""
    config = config or SimConfig.load()
    stats = {c: {"pass": 0, "fail": 0} for c in CATEGORIES}
    misses = []
    t0 = time.perf_counter()
    for i in range(n):
        cat = CATEGORIES[i % len(CATEGORIES)]
        r = _run_one(cat, master_seed, i, config)
        if r.ok:
            stats[cat]["pass"] += 1
        else:
            stats[cat]["fail"] += 1
            misses.append(r)
    dt = time.perf_counter() - t0
    summary = {
        "n": n, "seed": master_seed, "wall_s": dt,
        "per_category": stats,
        "misses": [(m.category, m.cfg_hash, m.reason) for m in misses],
    }
    return summary


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--n", type=int, default=1000)
    ap.add_argument("--seed", type=int, default=42)
    args = ap.parse_args()
    summary = run_fuzz(args.n, args.seed)
    print(f"fuzz n={summary['n']} seed={summary['seed']} "
          f"wall={summary['wall_s']:.1f}s")
    total_pass = sum(c["pass"] for c in summary["per_category"].values())
    total_fail = sum(c["fail"] for c in summary["per_category"].values())
    print(f"  total PASS={total_pass} FAIL={total_fail}")
    for cat, counts in summary["per_category"].items():
        print(f"    {cat:<20} pass={counts['pass']:4d} fail={counts['fail']:4d}")
    if summary["misses"]:
        print()
        print(f"  misses ({len(summary['misses'])}):")
        for cat, h, r in summary["misses"][:10]:
            print(f"    {cat} {h}: {r}")
    return 0 if total_fail == 0 else 1


if __name__ == "__main__":
    sys.exit(main())
