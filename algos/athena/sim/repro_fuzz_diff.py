"""Reproduce the first cross_validate fuzz divergence for debugging.

Usage:
    python3.12 -m algos.athena.sim.repro_fuzz_diff --seed 42

Iterates the same fuzz sequence as cross_validate.py with --fuzz 10000
and dumps the FIRST config whose Python and Rust outputs diverge —
printing the pre-sim state, post-Python state, post-Rust state, and
the diff so we can trace which engine rule the two implementations
disagree on.
"""

from __future__ import annotations

import argparse
import hashlib
import sys
from pathlib import Path

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig
from sim.pysim import simulate_action_phase
from sim.fuzz import _GENERATORS, CATEGORIES, _clone_state
from sim.cross_validate import _py_state_to_dict, _sim_config_to_dict, _compare_final_states


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--seed", type=int, default=42)
    ap.add_argument("--n", type=int, default=10000)
    args = ap.parse_args()

    try:
        import sim_rs  # type: ignore
    except ImportError as e:
        print(f"sim_rs not available: {e}", file=sys.stderr)
        return 2

    config = SimConfig.load()
    for i in range(args.n):
        cat = CATEGORIES[i % len(CATEGORIES)]
        cat_seed = int(hashlib.sha1(cat.encode()).hexdigest()[:8], 16)
        rng = np.random.default_rng((int(args.seed), cat_seed, int(i)))
        state = _GENERATORS[cat](rng)
        py = _clone_state(state)
        simulate_action_phase(py, config)
        rs_state = sim_rs.simulate_action_phase_py(
            _py_state_to_dict(state), _sim_config_to_dict(config))
        diffs = _compare_final_states(py, rs_state)
        if diffs:
            print(f"first diff at i={i} category={cat}")
            print(f"  diffs: {diffs}")
            print()
            # Dump the pre-sim state so we can reproduce deterministically.
            state_dict = _py_state_to_dict(state)
            print("pre-sim structures:")
            for s in state_dict["structures"]:
                print(f"  xy={s['xy']} type={s['type_idx']} upg={s['upgraded']} "
                      f"hp={s['hp']} uid={s['uid']} player={s['player']} "
                      f"tsr={s['turn_start_removal']}")
            print(f"pre-sim mobiles ({len(state_dict['mobiles'])}):")
            for m in state_dict["mobiles"]:
                print(f"  xy={m['xy']} spawn={m['spawn_xy']} type={m['type_idx']} "
                      f"hp={m['hp']} uid={m['uid']} player={m['player']} "
                      f"target_edge={m['target_edge']} last_move={m['last_move']}")
            print(f"pre-sim p1: hp={state_dict['p1']['hp']} sp={state_dict['p1']['sp']} "
                  f"mp={state_dict['p1']['mp']}")
            print(f"pre-sim p2: hp={state_dict['p2']['hp']} sp={state_dict['p2']['sp']} "
                  f"mp={state_dict['p2']['mp']}")
            print()
            print(f"PY post: p1 hp={py.p1.hp} sp={py.p1.sp} | p2 hp={py.p2.hp} sp={py.p2.sp}")
            print(f"RS post: p1 hp={rs_state['p1']['hp']} sp={rs_state['p1']['sp']} | "
                  f"p2 hp={rs_state['p2']['hp']} sp={rs_state['p2']['sp']}")
            return 0
    print("no divergence across", args.n, "configs")
    return 0


if __name__ == "__main__":
    sys.exit(main())
