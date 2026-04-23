"""Phase 0 gate: verify athena's vendored gamelib pathfinds >=4x faster than
the starter kit's queue.Queue-based gamelib.

Each flavour is benchmarked in its own subprocess with its own sys.path so the
two gamelib packages never collide in a single interpreter.

Run from repo root:
    python3 algos/athena/tests/bench_pathfinding.py
"""

import json
import os
import subprocess
import sys
import textwrap

REPO = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", "..", ".."))

STARTER_GAMELIB_PARENT = os.path.join(REPO, "C1GamesStarterKit-master", "python-algo")
ATHENA_GAMELIB_PARENT = os.path.join(REPO, "algos", "athena")
COMP_CFG = os.path.join(REPO, "configs", "competition-game-configs.json")


CHILD_SCRIPT = textwrap.dedent(r"""
    import json, sys, time, os
    import gamelib
    from gamelib.game_state import GameState
    CFG_PATH = sys.argv[1]
    LABEL = sys.argv[2]
    USE_CACHE = sys.argv[3] == "1"
    with open(CFG_PATH) as f:
        cfg = json.load(f)
    cfg.pop("_source", None)

    state = {
        "turnInfo": [0, 5, 0, 0],
        "p1Stats": [40.0, 8.0, 1.0, 0.0],
        "p2Stats": [40.0, 8.0, 1.0, 0.0],
        "p1Units": [[], [], [], [], [], [], []],
        "p2Units": [[], [], [], [], [], [], []],
        "events": {"selfDestruct": [], "breach": [], "damage": [], "shield": [],
                   "melee": [], "attack": [], "death": [], "spawn": [], "move": []},
    }
    gs = GameState(cfg, json.dumps(state))
    gs.suppress_warnings(True)
    import gamelib.game_state as GSM
    WALL = GSM.WALL
    TURRET = GSM.TURRET
    for loc in [[0,13],[27,13],[1,12],[26,12],[3,12],[24,12],[6,11],[21,11],
                [9,10],[18,10],[13,10],[14,10],[11,9],[16,9]]:
        gs.attempt_spawn(WALL, loc)
    for loc in [[3,11],[24,11],[13,9],[14,9]]:
        gs.attempt_spawn(TURRET, loc)

    sample = [
        ([1,12], gs.game_map.TOP_RIGHT),
        ([26,12], gs.game_map.TOP_LEFT),
        ([7,6], gs.game_map.TOP_RIGHT),
        ([20,6], gs.game_map.TOP_LEFT),
        ([13,0], gs.game_map.TOP_LEFT),
        ([14,0], gs.game_map.TOP_RIGHT),
    ]

    # Warmup
    for loc, edge in sample:
        gs.find_path_to_edge(loc, edge)
    if not USE_CACHE and hasattr(gs, "_invalidate_path_cache"):
        gs._invalidate_path_cache()

    ITERS = 200
    t0 = time.perf_counter()
    for _ in range(ITERS):
        for loc, edge in sample:
            gs.find_path_to_edge(loc, edge)
            if not USE_CACHE and hasattr(gs, "_invalidate_path_cache"):
                gs._invalidate_path_cache()
    t1 = time.perf_counter()
    calls = ITERS * len(sample)
    per_us = (t1 - t0) / calls * 1e6
    print("{label}\tcalls={calls}\ttotal={total:.4f}s\tper_call_us={per:.2f}".format(
        label=LABEL, calls=calls, total=t1 - t0, per=per_us))
""").strip()


def run_bench(gamelib_parent, label, use_cache):
    # Write the child script to a temp file so we can import gamelib cleanly.
    env = os.environ.copy()
    env["PYTHONPATH"] = gamelib_parent + os.pathsep + env.get("PYTHONPATH", "")
    result = subprocess.run(
        [sys.executable, "-c", CHILD_SCRIPT, COMP_CFG, label, "1" if use_cache else "0"],
        cwd=gamelib_parent,
        env=env,
        capture_output=True,
        text=True,
        timeout=120,
    )
    if result.returncode != 0:
        print("FAILED:", label, file=sys.stderr)
        print(result.stderr, file=sys.stderr)
        raise SystemExit(1)
    line = result.stdout.strip().splitlines()[-1]
    # Parse "label\tcalls=..\ttotal=..s\tper_call_us=.."
    parts = dict(kv.split("=") for kv in line.split("\t")[1:])
    total = float(parts["total"].rstrip("s"))
    print(line)
    return total


def main():
    print("=" * 72)
    print("Pathfinding benchmark (same algo, deque + cache vs queue.Queue)")
    print("=" * 72)
    t_starter = run_bench(STARTER_GAMELIB_PARENT, "starter/queue.Queue", use_cache=False)
    t_athena_nocache = run_bench(ATHENA_GAMELIB_PARENT, "athena/deque_no_cache", use_cache=False)
    t_athena_cache = run_bench(ATHENA_GAMELIB_PARENT, "athena/deque+cache", use_cache=True)

    speedup_deque_only = t_starter / t_athena_nocache
    speedup_full = t_starter / t_athena_cache
    print("-" * 72)
    print("speedup (deque alone)  = %.2fx" % speedup_deque_only)
    print("speedup (deque+cache)  = %.2fx" % speedup_full)
    gate = 4.0
    ok = speedup_full >= gate
    print("Phase 0 gate (>= %.1fx): %s" % (gate, "PASS" if ok else "FAIL"))
    sys.exit(0 if ok else 1)


if __name__ == "__main__":
    main()
