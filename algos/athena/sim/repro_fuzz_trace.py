"""Frame-by-frame trace of the diverging mobile's path in Python sim.
For Rust, we'll need a separate Rust-side debug trace but first see the
expected (Python) trajectory.
"""

from __future__ import annotations

import hashlib
import sys
from pathlib import Path

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig
from sim.pysim import apply_deploy_actions  # noqa
from sim.fuzz import _GENERATORS, CATEGORIES, _clone_state
from sim.pathfinder import make_pathfinders
from sim.map import edge_tiles


def main() -> int:
    config = SimConfig.load()
    cat_seed = int(hashlib.sha1(b"max_budget").hexdigest()[:8], 16)
    rng = np.random.default_rng((42, cat_seed, 131))
    state = _GENERATORS["max_budget"](rng)

    # Just 1 mobile at (21, 7) heading to edge 1 (TOP_LEFT).
    m = state.mobiles[0]
    print(f"mobile: xy={m.xy} spawn={m.spawn_xy} target_edge={m.target_edge} "
          f"type={m.type_idx}")
    print(f"structures: {len(state.structures)} alive")

    # Build pathfinders for edge 1.
    walls = list(state.structures.keys())
    edge_to_perfects = {}
    for e in range(4):
        edge_to_perfects[e] = edge_tiles(e)
    pfs = make_pathfinders(28, walls, edge_to_perfects)
    pf = pfs[m.target_edge]

    # Simulate 60 steps of movement (interceptors move every 4 frames = 15 actual moves in 60 frames).
    cur = tuple(m.xy)
    last_move = 0  # SPAWNED
    print()
    print("PY path trace:")
    for step in range(30):
        nx, ny = pf.get_step(cur[0], cur[1], last_move)
        marker = ""
        if (nx, ny) == cur:
            marker = "  [STUCK]"
        elif (nx, ny) in edge_tiles(1):
            marker = "  [ON EDGE]"
        print(f"  step {step}: cur=({cur[0]:2d},{cur[1]:2d}) -> ({nx:2d},{ny:2d}){marker}")
        if (nx, ny) == cur:
            break
        last_move = 1 if ny == cur[1] else 2  # HORIZONTAL if y unchanged
        cur = (nx, ny)

    # Now do a similar trace in Rust by exposing enough of PathFinder through
    # PyO3. For now, just print the Python trace and we'll manually compare
    # against a Rust-side dump if needed.
    return 0


if __name__ == "__main__":
    sys.exit(main())
