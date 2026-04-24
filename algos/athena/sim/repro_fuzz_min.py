"""Minimal repro of the cross_validate fuzz divergence at i=131 (max_budget
category). All 13 interceptors spawn at (21, 7) targeting TOP_LEFT edge
through a maze of player-1 walls. Python says 13 breach; Rust says 0.

This script spawns JUST ONE interceptor (everything else identical) and
prints its per-frame xy + last_move under both impls via PyO3, isolating
where the path diverges.
"""

from __future__ import annotations

import hashlib
import sys
from pathlib import Path

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig
from sim.pysim import simulate_action_phase
from sim.fuzz import _GENERATORS, CATEGORIES, _clone_state
from sim.cross_validate import _py_state_to_dict, _sim_config_to_dict


def main() -> int:
    try:
        import sim_rs  # type: ignore
    except ImportError as e:
        print(f"sim_rs not available: {e}", file=sys.stderr)
        return 2

    config = SimConfig.load()
    cat = "max_budget"
    cat_seed = int(hashlib.sha1(cat.encode()).hexdigest()[:8], 16)
    rng = np.random.default_rng((42, cat_seed, 131))
    state = _GENERATORS[cat](rng)

    # Trim to 1 mobile so the trace is comprehensible.
    state.mobiles = state.mobiles[:1]

    py = _clone_state(state)
    simulate_action_phase(py, config)

    print("PY post-sim:")
    for m in py.mobiles:
        print(f"  mobile uid={m.uid} xy={m.xy} reached={m.reached_target} "
              f"fin={m.finished_navigating} hp={m.hp} steps={m.steps_taken}")
    print(f"  p1 sp={py.p1.sp} hp={py.p1.hp} | p2 sp={py.p2.sp} hp={py.p2.hp}")
    print(f"  structures alive: {sum(1 for s in py.structures.values() if s.hp > 0)}")
    print()

    rs_state = sim_rs.simulate_action_phase_py(
        _py_state_to_dict(state), _sim_config_to_dict(config))
    print("RS post-sim:")
    for m in rs_state["mobiles"]:
        print(f"  mobile uid={m['uid']} xy={m['xy']} reached={m['reached_target']} "
              f"fin={m['finished_navigating']} hp={m['hp']} steps={m['steps_taken']}")
    print(f"  p1 sp={rs_state['p1']['sp']} hp={rs_state['p1']['hp']} | "
          f"p2 sp={rs_state['p2']['sp']} hp={rs_state['p2']['hp']}")
    print(f"  structures alive: {sum(1 for s in rs_state['structures'] if s['hp'] > 0)}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
