"""Python ↔ Rust cross-validation harness (P2 integration gate).

For every turn of every ranked replay (and every fuzz config in a
specified batch), runs both implementations:

    - Python simulate_action_phase on the same initial state
    - Rust sim_rs.simulate_action_phase (via the PyO3 binding) on a
      state constructed from the same inputs

and asserts byte-identical final state under the C-tight-coherent gate:
    * stats: strict (HP / SP / MP exact)
    * structures: strict multiset (sorted by xy)
    * mobiles:    uid-sorted multiset with ≤1-ULP-float32 tolerance on
                  HP+shield (mirrors validator CASCADE band)

Exits 0 only when every run agrees. Any divergence → report first
failing turn + column + sim/rust values, and exit 1.

PyO3 bindings:
    The Rust crate exposes a `simulate_action_phase` PyO3 function that
    accepts a state blob (dict) and returns the post-simulation state.
    Built via:
        cd algos/athena/sim_rs
        maturin develop --release --features pyo3

    If the binding isn't built (import fails), this script reports the
    missing binding and exits 2 (distinguished from parity-fail).

Usage:
    python3 -m algos.athena.sim.cross_validate               # ranked
    python3 -m algos.athena.sim.cross_validate --fuzz 10000  # + fuzz
"""

from __future__ import annotations

import argparse
import sys
import time
from pathlib import Path
from typing import List, Tuple

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import SimConfig
from sim.pysim import apply_deploy_actions, simulate_action_phase
from sim.validate import (
    _build_state_from_deploy_frame,
    _collect_upgraded_uids,
    _extract_deploy_actions,
    _extract_deploy_events_in_order,
    _index_deploy_frames,
    _index_first_action_frames,
    _parse_replay,
)
from sim.fuzz import _GENERATORS, CATEGORIES, _clone_state


def _try_import_rust():
    try:
        import sim_rs  # type: ignore
        return sim_rs
    except ImportError as e:
        print(f"sim_rs PyO3 binding not available ({e})", file=sys.stderr)
        print("build with: cd algos/athena/sim_rs && "
              "maturin develop --release --features pyo3", file=sys.stderr)
        return None


def _compare_final_states(py_state, rs_state, *, tol_ulp: int = 1) -> List[str]:
    """Return a list of divergence descriptions; empty list = perfect match."""
    diffs: List[str] = []
    # Stats strict.
    for side, ps in (("p1", "p1"), ("p2", "p2")):
        pa = getattr(py_state, ps); pb = rs_state[ps]
        for field in ("hp", "sp", "mp"):
            a = float(getattr(pa, field)); b = float(pb[field])
            if a != b:
                diffs.append(f"{ps}.{field}: py={a} rs={b}")
    # Structures strict multiset (sort by xy).
    sa = sorted((s.xy, int(s.type_idx), bool(s.upgraded), float(s.hp),
                  str(s.uid), int(s.player))
                 for s in py_state.structures.values())
    sb = sorted((tuple(s["xy"]), int(s["type_idx"]), bool(s["upgraded"]),
                  float(s["hp"]), str(s["uid"]), int(s["player"]))
                 for s in rs_state["structures"])
    if sa != sb:
        for i, (a, b) in enumerate(zip(sa, sb)):
            if a != b:
                diffs.append(f"struct[{i}]: py={a} rs={b}")
                break
        if len(sa) != len(sb):
            diffs.append(f"struct-count: py={len(sa)} rs={len(sb)}")
    # Mobiles: uid-sorted, 1-ULP tolerance on HP/shield.
    ma = sorted(py_state.mobiles, key=lambda m: (str(m.uid), m.xy))
    mb = sorted(rs_state["mobiles"], key=lambda m: (str(m["uid"]), tuple(m["xy"])))
    if len(ma) != len(mb):
        diffs.append(f"mob-count: py={len(ma)} rs={len(mb)}")
        return diffs
    for x, y in zip(ma, mb):
        if (x.xy, int(x.type_idx), str(x.uid), int(x.player)) != \
           (tuple(y["xy"]), int(y["type_idx"]), str(y["uid"]), int(y["player"])):
            diffs.append(f"mob-ident: py={x.xy},{x.uid} rs={y['xy']},{y['uid']}")
            continue
        for field in ("hp", "shield"):
            a = np.float32(getattr(x, field))
            b = np.float32(y[field])
            if a == b:
                continue
            ai = np.int32(a.view(np.int32))
            bi = np.int32(b.view(np.int32))
            if abs(int(ai) - int(bi)) > tol_ulp:
                diffs.append(f"mob.{field}: uid={x.uid} py={float(a)} rs={float(b)}")
    return diffs


def _py_then_rs(path: Path, config: SimConfig, sim_rs, out: dict) -> None:
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
        apply_deploy_actions(state, config, p1s, p1u, p2s, p2u,
                              ordered_events=ordered)
        py = _clone_state(state)
        simulate_action_phase(py, config)
        # Rust side: pass state-dict built from the same pre-sim state.
        rs_state = sim_rs.simulate_action_phase_py(
            _py_state_to_dict(state), _sim_config_to_dict(config))
        diffs = _compare_final_states(py, rs_state)
        out["checks"] += 1
        if diffs:
            out["fails"] += 1
            if out["first_fail"] is None:
                out["first_fail"] = (path.name, t, diffs[:3])


def _py_state_to_dict(state) -> dict:
    return {
        "turn": int(state.turn),
        "p1": {"hp": float(state.p1.hp), "sp": float(state.p1.sp),
                "mp": float(state.p1.mp)},
        "p2": {"hp": float(state.p2.hp), "sp": float(state.p2.sp),
                "mp": float(state.p2.mp)},
        "structures": [
            {"xy": list(s.xy), "type_idx": int(s.type_idx),
              "upgraded": bool(s.upgraded), "hp": float(s.hp),
              "uid": str(s.uid), "player": int(s.player),
              "turn_start_removal": s.turn_start_removal}
            for s in state.structures.values()
        ],
        "mobiles": [
            {"xy": list(m.xy), "type_idx": int(m.type_idx),
              "hp": float(m.hp), "shield": float(m.shield),
              "uid": str(m.uid), "player": int(m.player),
              "spawn_xy": list(m.spawn_xy),
              "target_edge": int(m.target_edge),
              "steps_taken": int(m.steps_taken),
              "move_buildup": float(m.move_buildup),
              "last_move": int(m.last_move),
              "finished_navigating": bool(m.finished_navigating),
              "reached_target": bool(m.reached_target),
              "breached": bool(m.breached)}
            for m in state.mobiles
        ],
    }


def _sim_config_to_dict(config) -> str:
    """Rust side loads the same JSON snapshot via path."""
    return str((Path(__file__).resolve().parent.parent
                 / "data" / "citadel_config_snapshot.json"))


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--fuzz", type=int, default=0)
    ap.add_argument("--seed", type=int, default=42)
    args = ap.parse_args()

    sim_rs = _try_import_rust()
    if sim_rs is None:
        return 2

    config = SimConfig.load()
    out = {"checks": 0, "fails": 0, "first_fail": None}
    t0 = time.perf_counter()
    corpus = sorted((Path(__file__).resolve().parent.parent.parent.parent
                       / "replays" / "ranked").glob("*.replay"))
    for path in corpus:
        _py_then_rs(path, config, sim_rs, out)
    ranked_wall = time.perf_counter() - t0
    fuzz_wall = 0.0
    if args.fuzz > 0:
        import hashlib
        t_f = time.perf_counter()
        for i in range(args.fuzz):
            cat = CATEGORIES[i % len(CATEGORIES)]
            cat_seed = int(hashlib.sha1(cat.encode()).hexdigest()[:8], 16)
            rng = np.random.default_rng((int(args.seed), cat_seed, int(i)))
            state = _GENERATORS[cat](rng)
            py = _clone_state(state)
            simulate_action_phase(py, config)
            rs_state = sim_rs.simulate_action_phase_py(
                _py_state_to_dict(state), _sim_config_to_dict(config))
            diffs = _compare_final_states(py, rs_state)
            out["checks"] += 1
            if diffs:
                out["fails"] += 1
                if out["first_fail"] is None:
                    out["first_fail"] = (f"fuzz:{cat}", i, diffs[:3])
        fuzz_wall = time.perf_counter() - t_f

    print(f"cross_validate: checks={out['checks']} "
          f"ranked_wall={ranked_wall:.1f}s fuzz_wall={fuzz_wall:.1f}s")
    print(f"  ok={out['checks']-out['fails']}  fail={out['fails']}")
    if out["first_fail"]:
        n, t, d = out["first_fail"]
        print(f"  first fail: {n} T{t}: {d}")
    gate = "PASS" if out["fails"] == 0 else "FAIL"
    print(f"  gate: {gate}")
    return 0 if out["fails"] == 0 else 1


if __name__ == "__main__":
    sys.exit(main())
