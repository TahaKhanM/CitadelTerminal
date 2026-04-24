"""Dump Rust-side per-frame trace via debug_trace_py."""

from __future__ import annotations

import hashlib
import sys
from pathlib import Path

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig
from sim.fuzz import _GENERATORS, CATEGORIES, _clone_state
from sim.cross_validate import _py_state_to_dict, _sim_config_to_dict


def main() -> int:
    import sim_rs  # type: ignore

    _ = SimConfig.load()
    cat_seed = int(hashlib.sha1(b"max_budget").hexdigest()[:8], 16)
    rng = np.random.default_rng((42, cat_seed, 131))
    state = _GENERATORS["max_budget"](rng)
    state.mobiles = state.mobiles[:1]

    trace = sim_rs.debug_trace_py(
        _py_state_to_dict(state), _sim_config_to_dict(None), 200)
    print(f"frames: {trace['frames']}, final_mobs: {trace['final_mob_count']}")
    print(f"final p1_sp: {trace['p1_sp']}, p2_hp: {trace['p2_hp']}")
    print()
    print("RS trace (only frames where mobile moved):")
    last_xy = None
    for entry in trace["trace"]:
        xy = entry.get("xy")
        if xy is None:
            print(f"  frame {entry['frame']}: mob_count={entry['mob_count']} "
                  f"p1_sp={entry['p1_sp']} p2_hp={entry['p2_hp']}")
            continue
        if xy != last_xy:
            print(f"  frame {entry['frame']:3d}: xy={tuple(xy)} steps={entry['steps_taken']} "
                  f"lm={entry['last_move']} buildup={entry['move_buildup']:.3f} "
                  f"fin={entry['finished_navigating']} reached={entry['reached_target']}")
            last_xy = xy
    return 0


if __name__ == "__main__":
    sys.exit(main())
