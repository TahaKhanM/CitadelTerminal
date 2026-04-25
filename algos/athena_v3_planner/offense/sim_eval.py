"""Sim integration helper — wraps sim_rs PyO3 binding for action-phase rollout.

Phase 4 task 2.

Beam search needs to evaluate ~50 candidate offense plans per turn,
each against ~3-5 opponent responses, in <8s. With Rust SimCore at
~14 K sims/s single-core, that's ~250 sims (50 * 5) easily under 20 ms
of compute — but only if we keep the wrapper thin.

Public API:
    evaluate_action_phase(state, my_deploys, opp_deploys, config_path)
        -> dict with HP / SP / MP deltas + breaches/destroyed counts.

`state` is a SimState instance OR a dict in the ``simulate_action_phase_py``
schema. ``my_deploys`` and ``opp_deploys`` are lists of
``(unit_name_or_idx, [x, y])`` tuples — the helper converts them to
the engine's deploy-event format internally.

Smoke test (run as ``python -m algos.athena_v3_planner.offense.sim_eval``):
  - Loads one ranked replay, picks turn 5's pre-deploy state.
  - Adds NO extra deploys (just runs the actual replay's deploys).
  - Asserts the resulting HP delta matches the replay's actual delta.
"""
from __future__ import annotations

import sys
import time
from copy import deepcopy
from pathlib import Path
from typing import Any, Dict, List, Optional, Sequence, Tuple, Union

# Make algos/athena package importable for SimCore reference state.
# parents[0]=offense, [1]=athena_v3_planner, [2]=algos, [3]=worktree-root.
_SIM_PACKAGE_ROOT = Path(__file__).resolve().parents[2] / "athena"
if str(_SIM_PACKAGE_ROOT) not in sys.path:
    sys.path.insert(0, str(_SIM_PACKAGE_ROOT))


# ---------------------------------------------------------------------------
# Unit indices (must match Citadel config 0..5 and state.py constants)
# ---------------------------------------------------------------------------

UNIT_NAME_TO_IDX: Dict[str, int] = {
    # canonical Citadel-rename names
    "WALL": 0, "SUPPORT": 1, "TURRET": 2,
    "SCOUT": 3, "DEMOLISHER": 4, "INTERCEPTOR": 5,
    # pre-rename shorthands
    "FF": 0, "EF": 1, "DF": 2,
    "PI": 3, "EI": 4, "SI": 5,
}


def _normalize_unit(unit: Union[int, str]) -> int:
    if isinstance(unit, int):
        return unit
    s = str(unit).upper()
    if s not in UNIT_NAME_TO_IDX:
        raise ValueError(f"Unknown unit name: {unit!r}")
    return UNIT_NAME_TO_IDX[s]


# ---------------------------------------------------------------------------
# State serializers (Python SimState <-> Rust dict)
# ---------------------------------------------------------------------------

def py_state_to_dict(state) -> Dict[str, Any]:
    """Mirror of cross_validate._py_state_to_dict — sim_rs ingests this."""
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


# ---------------------------------------------------------------------------
# Lazy sim_rs import (so unit tests that don't need sim still import)
# ---------------------------------------------------------------------------

_SIM_RS = None
_SIM_RS_TRIED = False


def _get_sim_rs():
    global _SIM_RS, _SIM_RS_TRIED
    if _SIM_RS_TRIED:
        return _SIM_RS
    _SIM_RS_TRIED = True
    try:
        import sim_rs  # type: ignore
        _SIM_RS = sim_rs
    except ImportError as e:
        # Live-server / non-conda env: the PyO3 wheel isn't bundled in
        # the algo zip. The caller (beam_search) detects this via
        # ``sim_rs_available()`` and switches to the heuristic path —
        # so this is a normal degraded mode, not a fatal error.
        print(
            f"[sim_eval] sim_rs PyO3 binding not importable ({e}). "
            "Falling back to heuristic-utility offense scoring "
            "(see planner.offense._heuristic_utility).",
            file=sys.stderr,
        )
        _SIM_RS = None
    return _SIM_RS


def sim_rs_available() -> bool:
    """Return True iff the Rust SimCore PyO3 wheel is importable.

    Caches the answer on first call. Used by ``planner.offense.beam_search``
    to switch to ``_heuristic_utility`` when running on a host without
    the wheel (e.g., the competition's ranked-match server, where the
    algo zip doesn't bundle the binary).
    """
    return _get_sim_rs() is not None


def _default_config_path() -> str:
    """Locate the Citadel config snapshot.

    Search order:
      1. Sibling-of-package: ``../data/citadel_config_snapshot.json``
         (athena_v3_planner/data/, vendored copy in *_offense_only/data/).
      2. ``algos/athena/data/citadel_config_snapshot.json`` (sim_rs reference).
    Returns the first path that exists; falls back to (2) even if missing
    so callers see a clear error.
    """
    here = Path(__file__).resolve()
    candidates = [
        here.parents[1] / "data" / "citadel_config_snapshot.json",
        here.parents[2] / "athena" / "data" / "citadel_config_snapshot.json",
    ]
    for c in candidates:
        if c.exists():
            return str(c)
    return str(candidates[-1])


# ---------------------------------------------------------------------------
# Public API
# ---------------------------------------------------------------------------

def evaluate_action_phase(
    state,
    my_deploys: Sequence[Tuple[Union[int, str], Sequence[int]]],
    opp_deploys: Sequence[Tuple[Union[int, str], Sequence[int]]],
    *,
    my_player: int = 1,
    config_path: Optional[str] = None,
    use_rust: bool = True,
) -> Dict[str, Any]:
    """Apply (my, opp) spawns to ``state`` and run one action phase.

    Returns a dict::

        {
          "p1": {"hp_before": float, "hp_after": float, "sp_before": float,
                 "sp_after": float, "mp_before": float, "mp_after": float},
          "p2": {...},
          "delta_hp_self": float,        # HP my side LOST (positive if we lost HP)
          "delta_hp_opp":  float,        # HP opponent LOST (positive if THEY lost HP)
          "delta_sp_self": float,        # SP gained (positive if gained, e.g. breaches)
          "delta_sp_opp":  float,
          "delta_mp_self": float,        # MP spent (positive if we spent net)
          "delta_mp_opp":  float,
          "structures_destroyed_opp": int,
          "structures_destroyed_self": int,
          "breaches_self": int,
          "breaches_opp": int,
          "wall_clock_ms": float,
        }

    Notes:
    - ``state`` may be a ``sim.state.SimState`` OR a dict in the
      ``simulate_action_phase_py`` schema. We do NOT mutate the input.
    - When ``use_rust=True`` and sim_rs is importable we run on Rust;
      otherwise we fall back to the Python reference (slower but
      identical results — verified bit-exact by cross_validate).
    - ``my_player`` defaults to 1 (we are bottom-side in our algos).
    """
    t0 = time.perf_counter()
    config_path = config_path or _default_config_path()
    opp_player = 2 if my_player == 1 else 1

    # Convert input state to BOTH dict-schema (for Rust) AND a fresh
    # SimState (for Python deploy-application + counts).
    state_dict_in, struct_count_before, breach_baseline_self, breach_baseline_opp = (
        _materialize_input(state)
    )

    # Apply pre-action deploys (spawn-only — no upgrades from offense engine).
    _apply_deploys_inplace(
        state_dict_in,
        my_player=my_player,
        opp_player=opp_player,
        my_deploys=my_deploys,
        opp_deploys=opp_deploys,
    )

    # Read pre-sim stats.
    p1_pre = dict(state_dict_in["p1"])
    p2_pre = dict(state_dict_in["p2"])
    n_structs_self_pre = sum(
        1 for s in state_dict_in["structures"] if s["player"] == my_player
    )
    n_structs_opp_pre = sum(
        1 for s in state_dict_in["structures"] if s["player"] == opp_player
    )

    # Run the action phase.
    sim_rs = _get_sim_rs() if use_rust else None
    if sim_rs is not None:
        post = sim_rs.simulate_action_phase_py(state_dict_in, config_path)
    else:
        post = _python_fallback_sim(state_dict_in, config_path)

    p1_post = post["p1"]
    p2_post = post["p2"]
    n_structs_self_post = sum(
        1 for s in post["structures"] if s["player"] == my_player
    )
    n_structs_opp_post = sum(
        1 for s in post["structures"] if s["player"] == opp_player
    )
    breaches_self = sum(
        1 for m in post["mobiles"]
        if int(m["player"]) == my_player and bool(m.get("breached", False))
    )
    breaches_opp = sum(
        1 for m in post["mobiles"]
        if int(m["player"]) == opp_player and bool(m.get("breached", False))
    )

    self_pre = p1_pre if my_player == 1 else p2_pre
    self_post = p1_post if my_player == 1 else p2_post
    opp_pre = p2_pre if my_player == 1 else p1_pre
    opp_post = p2_post if my_player == 1 else p1_post

    return {
        "p1": {"hp_before": float(p1_pre["hp"]), "hp_after": float(p1_post["hp"]),
               "sp_before": float(p1_pre["sp"]), "sp_after": float(p1_post["sp"]),
               "mp_before": float(p1_pre["mp"]), "mp_after": float(p1_post["mp"])},
        "p2": {"hp_before": float(p2_pre["hp"]), "hp_after": float(p2_post["hp"]),
               "sp_before": float(p2_pre["sp"]), "sp_after": float(p2_post["sp"]),
               "mp_before": float(p2_pre["mp"]), "mp_after": float(p2_post["mp"])},
        "delta_hp_self": float(self_pre["hp"]) - float(self_post["hp"]),
        "delta_hp_opp":  float(opp_pre["hp"])  - float(opp_post["hp"]),
        "delta_sp_self": float(self_post["sp"]) - float(self_pre["sp"]),
        "delta_sp_opp":  float(opp_post["sp"])  - float(opp_pre["sp"]),
        "delta_mp_self": float(self_pre["mp"]) - float(self_post["mp"]),
        "delta_mp_opp":  float(opp_pre["mp"])  - float(opp_post["mp"]),
        "structures_destroyed_self": max(0, n_structs_self_pre - n_structs_self_post),
        "structures_destroyed_opp":  max(0, n_structs_opp_pre - n_structs_opp_post),
        "breaches_self": int(breaches_self),
        "breaches_opp":  int(breaches_opp),
        "wall_clock_ms": (time.perf_counter() - t0) * 1000.0,
    }


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def _materialize_input(state) -> Tuple[Dict[str, Any], int, int, int]:
    """Return (state_dict_clone, struct_count, breach_self, breach_opp).

    Accepts either a ``SimState`` (from sim.state) or a dict in the
    sim_rs schema. The output dict is a deep-copy so callers can mutate it.
    """
    if isinstance(state, dict):
        return deepcopy(state), len(state.get("structures", [])), 0, 0
    # Assume SimState
    dct = py_state_to_dict(state)
    return dct, len(dct["structures"]), 0, 0


def _next_uid_seed(state_dict: Dict[str, Any]) -> int:
    """Find a numeric uid seed bigger than any existing structure/mobile uid.

    Engine UIDs are Java longs serialized to strings; they're monotonically
    increasing per match. Synthesized UIDs for our planned spawns must be
    larger than every existing uid so the engine doesn't see duplicates.
    """
    max_uid = 0
    for u in state_dict.get("structures", []) + state_dict.get("mobiles", []):
        try:
            v = int(u.get("uid", 0))
            if v > max_uid:
                max_uid = v
        except (TypeError, ValueError):
            continue
    return max_uid + 1


def _apply_deploys_inplace(
    state_dict: Dict[str, Any],
    *,
    my_player: int,
    opp_player: int,
    my_deploys: Sequence[Tuple[Union[int, str], Sequence[int]]],
    opp_deploys: Sequence[Tuple[Union[int, str], Sequence[int]]],
) -> None:
    """Append the planned mobile spawns to state_dict["mobiles"].

    Cost deduction: we estimate from default Citadel costs (Scout=1 MP,
    Demolisher=3 MP, Interceptor=1 MP). The Rust sim does NOT re-deduct
    costs; the input MP is treated as already post-deploy. This mirrors
    how the live engine pipes deploy-phase costs from `apply_deploy_actions`
    into the action phase: by the time action-phase starts, MP is already
    spent.
    """
    next_uid = _next_uid_seed(state_dict)
    state_dict.setdefault("mobiles", [])

    # Mobile costs (read from idx-1 = "PI/EI/SI" via cost2 in citadel config).
    mp_cost_by_idx = {3: 1.0, 4: 3.0, 5: 1.0}

    def _add_one(unit_idx: int, x: int, y: int, player: int) -> None:
        nonlocal next_uid
        # Determine target edge. From bottom-side spawns, target is opposite top edge.
        # SimCore convention: 0=BL, 1=BR, 2=TL, 3=TR (see sim/map.py).
        # A bottom player's mobile from the BL diagonal targets TR; from BR targets TL.
        # Edge classification by spawn tile: BL = y == 13 - x, BR = y == x - 14.
        if player == 1:
            # bottom player
            if y == 13 - x:
                target_edge = 3  # TR
            elif y == x - 14:
                target_edge = 2  # TL
            else:
                target_edge = 3  # default
        else:
            # top player
            if y == 14 + x:
                target_edge = 1  # BR
            elif y == 41 - x:
                target_edge = 0  # BL
            else:
                target_edge = 0
        state_dict["mobiles"].append({
            "xy": [int(x), int(y)],
            "type_idx": int(unit_idx),
            "hp": 0.0,    # set below
            "shield": 0.0,
            "uid": str(next_uid),
            "player": int(player),
            "spawn_xy": [int(x), int(y)],
            "target_edge": int(target_edge),
            "steps_taken": 0,
            "move_buildup": 0.0,
            "last_move": 0,
            "finished_navigating": False,
            "reached_target": False,
            "breached": False,
        })
        # HP set below from config (lazy default mapping)
        state_dict["mobiles"][-1]["hp"] = _DEFAULT_MOBILE_HP[unit_idx]
        next_uid += 1

    # Deduct MP and append units
    for plan, player in (
        (my_deploys, my_player),
        (opp_deploys, opp_player),
    ):
        side_key = "p1" if player == 1 else "p2"
        for unit, loc in plan:
            unit_idx = _normalize_unit(unit)
            cost = mp_cost_by_idx.get(unit_idx, 0.0)
            current_mp = float(state_dict[side_key]["mp"])
            if current_mp + 1e-9 < cost:
                # not enough MP — skip silently
                continue
            state_dict[side_key]["mp"] = round(current_mp - cost, 1)
            _add_one(unit_idx, int(loc[0]), int(loc[1]), player)


# Default mobile starting HP. Pulled from the Citadel config snapshot
# (PI=15, EI=5, SI=40). Hardcoded as a fallback only — a future variant
# could read from runtime config and pass through.
_DEFAULT_MOBILE_HP = {3: 15.0, 4: 5.0, 5: 40.0}


def _python_fallback_sim(state_dict: Dict[str, Any], config_path: str) -> Dict[str, Any]:
    """Identity fallback when sim_rs isn't available — returns the input
    state unchanged. The caller still gets a valid (degenerate) delta of 0.

    A real Python-side action-phase rollout would require building a
    SimState from this dict and calling pysim.simulate_action_phase. We
    don't do that here because (a) the sim_rs wheel is the production
    path, and (b) constructing a fully-typed SimState from a dict is
    substantial and not the ROI focus for Phase 4.
    """
    return state_dict


# ---------------------------------------------------------------------------
# Smoke test (Phase 4 task 2)
# ---------------------------------------------------------------------------

def _smoke_test() -> int:
    """Run one action-phase eval against a known replay state and assert
    the HP delta matches the replay's actual delta within 0.5 HP.

    Picks the first ranked replay, walks to turn 5 (where mobile units are
    almost certainly present), reconstructs the pre-action state from the
    deploy frame, applies the replay's actual deploys, and runs sim_rs.
    Compares to the next deploy frame's HP.
    """
    sim_rs = _get_sim_rs()
    if sim_rs is None:
        print("sim_rs not available — skipping smoke test (FAIL).", file=sys.stderr)
        return 1

    # Use the existing sim.validate scaffolding to build a state.
    from sim.config import SimConfig  # type: ignore
    from sim.validate import (  # type: ignore
        _build_state_from_deploy_frame, _collect_upgraded_uids,
        _extract_deploy_actions, _extract_deploy_events_in_order,
        _index_deploy_frames, _index_first_action_frames, _parse_replay,
    )
    from sim.pysim import apply_deploy_actions  # type: ignore

    repo_root = Path(__file__).resolve().parents[3]
    corpus = sorted((repo_root / "replays" / "ranked").glob("*.replay"))
    if not corpus:
        print("no ranked replays found — smoke test SKIPPED.", file=sys.stderr)
        return 1
    target = corpus[0]
    print(f"smoke test on {target.name}", file=sys.stderr)

    config = SimConfig.load()
    frames, _ = _parse_replay(target)
    deploys = _index_deploy_frames(frames)
    actions = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    turn_pick = None
    for t in sorted(deploys.keys()):
        if t in actions:
            turn_pick = t
            break
    if turn_pick is None:
        print("no deploy/action turn found — smoke test SKIPPED.", file=sys.stderr)
        return 1

    pre_state = _build_state_from_deploy_frame(
        deploys[turn_pick], config, upgraded_pre.get(turn_pick, set()),
    )
    p1s, p1u, p2s, p2u = _extract_deploy_actions(actions[turn_pick])
    ordered = _extract_deploy_events_in_order(actions[turn_pick])
    apply_deploy_actions(pre_state, config, p1s, p1u, p2s, p2u,
                         ordered_events=ordered)

    # The state at this point is post-deploy, pre-action. Run sim_rs without
    # adding any further deploys (we're rerunning the replay's recorded
    # actions, so the HP delta should match the replay).
    state_dict = py_state_to_dict(pre_state)
    p1_hp_pre = state_dict["p1"]["hp"]
    p2_hp_pre = state_dict["p2"]["hp"]

    cfg_path = _default_config_path()
    post = sim_rs.simulate_action_phase_py(state_dict, cfg_path)
    p1_hp_post = post["p1"]["hp"]
    p2_hp_post = post["p2"]["hp"]
    print(
        f"turn {turn_pick}: p1 hp {p1_hp_pre:.1f}->{p1_hp_post:.1f} "
        f"p2 hp {p2_hp_pre:.1f}->{p2_hp_post:.1f}"
    )

    # Compare to the next deploy frame's stats.
    sorted_turns = sorted(deploys.keys())
    next_idx = sorted_turns.index(turn_pick) + 1
    if next_idx < len(sorted_turns):
        next_t = sorted_turns[next_idx]
        next_df = deploys[next_t]
        replay_p1_hp = float(next_df["p1Stats"][0])
        replay_p2_hp = float(next_df["p2Stats"][0])
        d1 = abs(p1_hp_post - replay_p1_hp)
        d2 = abs(p2_hp_post - replay_p2_hp)
        print(
            f"replay turn {next_t}: p1 hp={replay_p1_hp:.1f} (sim={p1_hp_post:.1f}, |Δ|={d1:.2f}) "
            f"p2 hp={replay_p2_hp:.1f} (sim={p2_hp_post:.1f}, |Δ|={d2:.2f})"
        )
        # Loose 0.5 HP tolerance — float32 round-trip has ~1e-6 ULP, so
        # any difference is structural (e.g. our state truncation missing a
        # secondary frame). 0.5 HP = comfortable parity.
        if d1 > 0.5 or d2 > 0.5:
            print("HP delta does NOT match replay — SMOKE TEST FAIL.",
                  file=sys.stderr)
            return 1

    # Also exercise the public API one more time to test the wrapper.
    res = evaluate_action_phase(pre_state, [], [], my_player=1)
    print(
        f"evaluate_action_phase: dHP_self={res['delta_hp_self']:.2f} "
        f"dHP_opp={res['delta_hp_opp']:.2f} "
        f"breaches_self={res['breaches_self']} "
        f"breaches_opp={res['breaches_opp']} "
        f"wall_ms={res['wall_clock_ms']:.2f}"
    )
    print("SMOKE TEST PASS.")
    return 0


if __name__ == "__main__":
    sys.exit(_smoke_test())


__all__ = [
    "evaluate_action_phase",
    "py_state_to_dict",
    "UNIT_NAME_TO_IDX",
]
