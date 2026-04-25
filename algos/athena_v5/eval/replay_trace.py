"""Phase 7 — replay-trace G11 eval harness.

For each ranked replay we:
  1. Parse the per-turn (state, recorded p1_actions, recorded p2_actions)
     using the existing `algos/athena/sim/validate.py` parser.
  2. At each turn the replay reaches its first action frame, hand the
     pre-deploy ``SimState`` (converted to a sim_rs state-dict) to a
     callable that picks Athena's deploys (the EconomyArbiter offense
     pipeline). v13 was player 1 in the recorded matches; we therefore
     replace v13's deploys with Athena's chosen plan.
  3. Apply Athena's deploys + the opponent's (recorded) deploys via
     ``offense.sim_eval.evaluate_action_phase`` and propagate the
     resulting HP changes into our running tally.
  4. Track HP for both sides cumulatively across all turns of the
     replay; declare ``athena_predicted = "win"`` if Athena's final
     HP > opp HP, ``"loss"`` if Athena's HP < opp HP, ``"tie"`` on
     equality.

Design notes
------------

* **Defense fidelity.** We deliberately keep the replay's structures
  intact across turns. Computing Athena's full defense pipeline against
  a real `gamelib.GameState` is out of scope for Phase 7 (the brief
  scopes G11 to "sim Athena's actions vs the actual opponent's
  actions"). Athena's offense plays through the v13-defense board —
  this matches how the original G11 was framed in the Athena brief and
  isolates the offense-quality signal.
* **State source.** Each turn's pre-deploy state is reconstructed
  byte-identically (verified by Phase 0 cross-validate) from the
  replay's deploy frame. Athena's offense thus sees the same SP/MP/HP
  v13 saw — fair comparison.
* **Athena offense path.** We invoke ``planner.offense.generate_candidates``
  + ``planner.offense.beam_search`` directly on the state-dict — the
  same code paths the live ``EconomyArbiter`` uses, but bypassing the
  gamelib.GameState dependency. Posterior + action-predictor are wired
  exactly as they would be at game start.
* **Opponent action extraction.** The replay's ACTUAL opponent deploys
  (player 2 in the recorded match) are parsed from the action frame's
  spawn events and fed verbatim into ``evaluate_action_phase``.

Public API
----------

    evaluate_replay(replay_path, *, athena_arbiter=None, max_turns=100,
                    state_factory=None) -> dict

Returns:

    {
        "replay": str,
        "v13_actual": "win"|"loss"|"unknown",
        "athena_predicted": "win"|"loss"|"tie"|"crash",
        "athena_hp_final": float,
        "opp_hp_final": float,
        "turns_simulated": int,
        "athena_offense_picks": List[str],       # Candidate.name per turn
        "wall_clock_s": float,
        "error": Optional[str],
    }
"""
from __future__ import annotations

import os
import sys
import time
import traceback
from copy import deepcopy
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

# Make sibling sim package + sim_rs reference state importable.
_HERE = Path(__file__).resolve()
_REPO_ROOT = _HERE.parents[3]              # worktree root
_ATHENA_V3 = _HERE.parents[1]              # athena_v3_planner/
_SIM_PKG_ROOT = _REPO_ROOT / "algos" / "athena"  # sim/ + data/


def _ensure_path_order():
    """Force sys.path so that:
      - athena_v3_planner is FIRST (so `import offense.*` resolves to our
        package, not the empty `algos/athena/offense/__init__.py` stub).
      - athena (sim_rs reference) is reachable for `from sim.config import ...`.
      - repo_root is reachable for `from algos.athena_v3_planner...`.

    Critically, `sim/validate.py` does ``sys.path.insert(0, .../algos/athena)``
    at IMPORT time, which clobbers our preferred order. We have to call
    this function whenever we know that import has happened.
    """
    # Strip any existing entries we manage.
    bad = {str(_REPO_ROOT), str(_SIM_PKG_ROOT), str(_ATHENA_V3)}
    sys.path[:] = [p for p in sys.path if p not in bad]
    # Re-prepend in reverse order so LAST insert wins.
    sys.path.insert(0, str(_REPO_ROOT))     # for algos.athena_v3_planner.*
    sys.path.insert(0, str(_SIM_PKG_ROOT))  # for sim.config / sim.validate
    sys.path.insert(0, str(_ATHENA_V3))     # for offense.* / planner.* / opponent.*


_ensure_path_order()


def _import_sim_helpers():
    """Import sim.validate's helpers, then UNDO the sys.path-injection it does.

    `sim/validate.py` runs `sys.path.insert(0, ...algos/athena)` at import time
    (so it can `from sim.config import ...` from anywhere). That put
    `algos/athena/` ahead of `algos/athena_v3_planner/` on sys.path, which
    shadows our `offense` package with the empty stub at
    `algos/athena/offense/__init__.py`. We have to undo it before any
    further `import offense.*` calls.
    """
    from sim.config import SimConfig  # type: ignore
    from sim.validate import (  # type: ignore
        _build_state_from_deploy_frame,
        _collect_upgraded_uids,
        _extract_deploy_actions,
        _extract_deploy_events_in_order,
        _index_deploy_frames,
        _index_first_action_frames,
        _parse_replay,
    )

    # `sim/validate.py` rewrites sys.path on import — undo that so the rest
    # of the harness can `import offense.*` from athena_v3_planner.
    _ensure_path_order()

    return {
        "SimConfig": SimConfig,
        "_build_state_from_deploy_frame": _build_state_from_deploy_frame,
        "_collect_upgraded_uids": _collect_upgraded_uids,
        "_extract_deploy_actions": _extract_deploy_actions,
        "_extract_deploy_events_in_order": _extract_deploy_events_in_order,
        "_index_deploy_frames": _index_deploy_frames,
        "_index_first_action_frames": _index_first_action_frames,
        "_parse_replay": _parse_replay,
    }


def _parse_replay_per_turn(replay_path: Path):
    """Extract per-turn (pre_deploy_state, p1_spawns, p2_spawns, ordered_events).

    Mirrors cross_validate's pipeline (verified bit-identical Python ↔ Rust on
    all 47 ranked replays during Phase 0).

    Yields tuples (turn_number, state_pre_deploy, p1_spawns, p1_upgrades,
                   p2_spawns, p2_upgrades, ordered_events, deploy_frame).
    """
    h = _import_sim_helpers()
    SimConfig = h["SimConfig"]
    _build_state_from_deploy_frame = h["_build_state_from_deploy_frame"]
    _collect_upgraded_uids = h["_collect_upgraded_uids"]
    _extract_deploy_actions = h["_extract_deploy_actions"]
    _extract_deploy_events_in_order = h["_extract_deploy_events_in_order"]
    _index_deploy_frames = h["_index_deploy_frames"]
    _index_first_action_frames = h["_index_first_action_frames"]
    _parse_replay = h["_parse_replay"]

    config = SimConfig.load()
    frames, _ = _parse_replay(replay_path)
    deploys = _index_deploy_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    for t in sorted(deploys.keys()):
        if t not in actions_first:
            continue
        state = _build_state_from_deploy_frame(
            deploys[t], config, upgraded_pre.get(t, set()),
        )
        p1s, p1u, p2s, p2u = _extract_deploy_actions(actions_first[t])
        ordered = _extract_deploy_events_in_order(actions_first[t])
        yield (t, state, p1s, p1u, p2s, p2u, ordered, deploys[t], config)


def _state_to_dict_with_v13_resources_pre_deploy(state, deploy_frame: Dict[str, Any]) -> Dict[str, Any]:
    """Convert SimState -> sim_rs state-dict, preserving v13's pre-deploy SP/MP.

    The deploy frame's p1Stats[1]/p1Stats[2] are SP/MP at the start of the
    deploy phase — exactly what v13 had to spend, and what Athena should
    see for its offense decision.
    """
    # cross_validate._py_state_to_dict mirrored locally (we already have the
    # method on offense.sim_eval, but importing it pulls the offense package
    # which is fine — we rely on it elsewhere too).
    from offense.sim_eval import py_state_to_dict  # type: ignore

    sd = py_state_to_dict(state)
    # _build_state_from_deploy_frame reads p1Stats / p2Stats already, so the
    # returned SimState already reflects the pre-deploy resources. Belt-and-
    # suspenders: re-stamp from the deploy frame to make this resilient if
    # the SimState construction ever shifts.
    p1s = deploy_frame.get("p1Stats") or [40, 8, 1, 0]
    p2s = deploy_frame.get("p2Stats") or [40, 8, 1, 0]
    sd["p1"] = {"hp": float(p1s[0]), "sp": float(p1s[1]), "mp": float(p1s[2])}
    sd["p2"] = {"hp": float(p2s[0]), "sp": float(p2s[1]), "mp": float(p2s[2])}
    return sd


def _opp_deploys_from_p2_spawns(p2_spawns: List[list]) -> List[Tuple[int, List[int]]]:
    """Convert _extract_deploy_actions output to evaluate_action_phase format.

    p2_spawns entries are [x, y, type_idx, uid]. Athena G11 plays as p1
    (replacing v13); the opponent is whatever was player 2 in the recorded
    match. We pass mobile spawns only (type_idx 3..5); structures (0..2)
    are already in state and we don't reapply them.
    """
    out: List[Tuple[int, List[int]]] = []
    for ev in p2_spawns:
        if len(ev) < 3:
            continue
        x, y, type_idx = int(ev[0]), int(ev[1]), int(ev[2])
        if type_idx not in (3, 4, 5):
            continue  # skip structures; only mobiles matter for action phase
        out.append((type_idx, [x, y]))
    return out


def _build_default_arbiter() -> Tuple[Optional[Any], Optional[Any], Dict[str, Any]]:
    """Construct (opponent_classifier, action_predictor, snapshot_path).

    Mirrors algo_strategy.on_game_start. Failures are logged but the harness
    falls back to None for either component.
    """
    _ensure_path_order()
    from offense.state_adapter import augment_snapshot_for_simcore  # type: ignore

    snapshot_file = str(_ATHENA_V3 / "data" / "citadel_config_snapshot.json")
    try:
        augment_snapshot_for_simcore(snapshot_file)
    except Exception:  # noqa: BLE001
        pass

    classifier = None
    predictor = None
    try:
        from opponent.classifier import fit_default_classifier  # type: ignore

        npz_path = str(_ATHENA_V3 / "data" / "opponent_features.npz")
        if os.path.isfile(npz_path):
            classifier = fit_default_classifier(npz_path)
    except Exception:  # noqa: BLE001
        classifier = None

    try:
        from opponent.action_predictor import ActionPredictor  # type: ignore

        labels_path = str(_ATHENA_V3 / "opponent" / "labels.json")
        if os.path.isfile(labels_path):
            predictor = ActionPredictor().fit_from_labels_json(labels_path)
    except Exception:  # noqa: BLE001
        predictor = None

    config_snapshot = {}
    try:
        import json
        with open(snapshot_file) as f:
            config_snapshot = json.load(f)
    except Exception:  # noqa: BLE001
        config_snapshot = {}

    return classifier, predictor, {
        "snapshot_path": snapshot_file,
        "config": config_snapshot,
    }


def _athena_pick_offense(
    state_dict: Dict[str, Any],
    *,
    classifier,
    predictor,
    snapshot_path: str,
    turn: int,
    posterior: Optional[Dict[str, float]] = None,
) -> Any:
    """Run the Athena offense pipeline on a sim_rs state-dict.

    Mirrors EconomyArbiter._offense_phase but skipping the gamelib pieces:
      - generate_candidates(state_dict, mp_avail, ...)
      - opp_actions_top_k from action_predictor.top_k(...)
      - beam_search(...)

    Returns the chosen Candidate (may be the "hoard" sentinel).
    """
    _ensure_path_order()
    from planner.offense import beam_search, generate_candidates  # type: ignore

    mp_avail = float(state_dict["p1"].get("mp", 0.0))
    if mp_avail < 1.0:
        # Hoard candidate path — no spawn this turn.
        cands = generate_candidates(state_dict, mp_avail, my_player=1)
        return cands[0]  # always the "hoard" sentinel

    cands = generate_candidates(state_dict, mp_avail, my_player=1)
    if len(cands) <= 1:
        return cands[0]

    # Build opp_actions_top_k from posterior + predictor when available.
    opp_actions: List[Tuple[Dict[str, Any], float]] = []
    if predictor is not None and posterior is not None:
        try:
            opp_mp = float(state_dict["p2"].get("mp", 0.0))
            opp_actions = predictor.top_k(
                {"mp": opp_mp, "turn": turn}, posterior, k=3,
            )
        except Exception:  # noqa: BLE001
            opp_actions = []

    try:
        best = beam_search(
            state_dict,
            cands,
            opp_actions_top_k=opp_actions,
            my_player=1,
            budget_ms=4000.0,                  # tighter than live's 8s
            config_path=snapshot_path,
            skip_sim=False,
        )
    except Exception:  # noqa: BLE001
        # Heuristic-only retry — guarantees we get *some* plan back.
        best = beam_search(
            state_dict,
            cands,
            opp_actions_top_k=[],
            my_player=1,
            budget_ms=500.0,
            config_path=snapshot_path,
            skip_sim=True,
        )
    return best


def _candidate_to_deploys(cand) -> List[Tuple[str, List[int]]]:
    """Convert Candidate.deploys to the (unit_name, [x,y]) form
    evaluate_action_phase expects."""
    out: List[Tuple[str, List[int]]] = []
    for unit, loc in (cand.deploys or []):
        out.append((str(unit), [int(loc[0]), int(loc[1])]))
    return out


def evaluate_replay(
    replay_path,
    *,
    classifier=None,
    predictor=None,
    snapshot_path: Optional[str] = None,
    config_snapshot: Optional[Dict[str, Any]] = None,
    max_turns: int = 100,
) -> Dict[str, Any]:
    """Run the G11 replay-trace eval on a single replay.

    Athena replaces v13 (player 1). For each replay turn we:
      - Build the pre-deploy state from the deploy frame.
      - Hand it to Athena's offense pipeline (returns a Candidate).
      - Run one action phase: Athena's chosen deploys + recorded p2
        deploys (the actual opponent's actions) -> sim_rs delta.
      - Carry forward HP into the *next* replay turn's state (we
        replace the deploy frame's pX_hp with our running tally).

    The match ends when (a) we run out of replay turns, (b) max_turns
    is reached, (c) either side's HP <= 0.

    Parameters mirror the Phase 7 brief — additional keyword args are
    passed for re-using a pre-built classifier / predictor across the
    47-replay sweep.
    """
    replay_path = Path(replay_path)
    t_start = time.perf_counter()
    out: Dict[str, Any] = {
        "replay": str(replay_path.relative_to(_REPO_ROOT))
                  if str(replay_path).startswith(str(_REPO_ROOT))
                  else str(replay_path),
        "v13_actual": "unknown",
        "athena_predicted": "unknown",
        "athena_hp_final": 0.0,
        "opp_hp_final": 0.0,
        "turns_simulated": 0,
        "athena_offense_picks": [],
        "wall_clock_s": 0.0,
        "error": None,
    }

    if classifier is None and predictor is None and snapshot_path is None:
        # Caller didn't pre-build context — do it once here.
        classifier, predictor, ctx = _build_default_arbiter()
        snapshot_path = ctx["snapshot_path"]
        config_snapshot = ctx.get("config")

    _ensure_path_order()
    from offense.sim_eval import evaluate_action_phase  # type: ignore

    # Posterior — built once, updated each turn we want to. For Phase 7 the
    # cheapest correct thing is the uniform prior (matches the live
    # arbiter's behaviour at game start; predictor fallback is also clean).
    posterior: Optional[Dict[str, float]] = None
    if classifier is not None:
        try:
            posterior = classifier.uniform_prior()
        except Exception:  # noqa: BLE001
            posterior = None

    athena_hp = 40.0
    opp_hp = 40.0
    seen_first_state = False

    try:
        for turn_n, state, p1_sp, p1_up, p2_sp, p2_up, ordered, deploy_frame, config in (
            _parse_replay_per_turn(replay_path)
        ):
            if turn_n >= max_turns:
                break
            out["turns_simulated"] += 1

            # Build state dict; on turn 0 use the replay's HP, then carry our
            # running HP from prior turn.
            sd = _state_to_dict_with_v13_resources_pre_deploy(state, deploy_frame)

            if seen_first_state:
                sd["p1"]["hp"] = float(athena_hp)
                sd["p2"]["hp"] = float(opp_hp)
            else:
                # First turn — read from the deploy frame (always 40/40 at t=0
                # but be defensive for non-zero starting turns).
                athena_hp = float(sd["p1"]["hp"])
                opp_hp = float(sd["p2"]["hp"])
                seen_first_state = True

            # Athena's pick
            try:
                cand = _athena_pick_offense(
                    sd,
                    classifier=classifier,
                    predictor=predictor,
                    snapshot_path=snapshot_path or "",
                    turn=turn_n,
                    posterior=posterior,
                )
                out["athena_offense_picks"].append(getattr(cand, "name", "?"))
                athena_deploys = _candidate_to_deploys(cand)
            except Exception as exc:  # noqa: BLE001
                out["athena_offense_picks"].append("error")
                athena_deploys = []

            # Opponent's recorded deploys (player 2 of the replay)
            opp_deploys = _opp_deploys_from_p2_spawns(p2_sp)

            # Run action phase: Athena's deploys + opp's actual deploys.
            # NOTE: the replay's deploys for p1 (v13) are intentionally NOT
            # applied — Athena replaces v13.
            res = evaluate_action_phase(
                sd,
                athena_deploys,
                opp_deploys,
                my_player=1,
                config_path=snapshot_path,
            )

            # Carry forward HP using the deltas evaluate_action_phase reports.
            athena_hp = athena_hp - float(res.get("delta_hp_self", 0.0))
            opp_hp = opp_hp - float(res.get("delta_hp_opp", 0.0))

            # Early termination if either side dies
            if athena_hp <= 0.0 or opp_hp <= 0.0:
                break
    except Exception as exc:  # noqa: BLE001
        out["error"] = f"{exc!r}\n{traceback.format_exc()}"
        out["athena_predicted"] = "crash"
        out["wall_clock_s"] = time.perf_counter() - t_start
        return out

    out["athena_hp_final"] = float(athena_hp)
    out["opp_hp_final"] = float(opp_hp)

    # Verdict
    if athena_hp > opp_hp:
        out["athena_predicted"] = "win"
    elif athena_hp < opp_hp:
        out["athena_predicted"] = "loss"
    else:
        out["athena_predicted"] = "tie"

    # v13's actual outcome: from filename / replay_index. Caller may inject this.
    name = replay_path.name
    if "_win" in name:
        out["v13_actual"] = "win"
    elif "_loss" in name:
        out["v13_actual"] = "loss"

    out["wall_clock_s"] = time.perf_counter() - t_start
    return out


# ---------------------------------------------------------------------------
# CLI: smoke test on 3 mixed-outcome replays
# ---------------------------------------------------------------------------

def _smoke_main() -> int:
    """Phase 7 milestone P2: 3-replay smoke. Pick a mix of v13 wins/losses."""
    import json

    idx_path = _ATHENA_V3 / "data" / "replay_index.json"
    with open(idx_path) as f:
        records = json.load(f)
    wins = [r for r in records if r.get("outcome") == "win"][:2]
    losses = [r for r in records if r.get("outcome") == "loss"][:1]
    sample = wins + losses

    print(f"smoke: {len(sample)} replays")
    classifier, predictor, ctx = _build_default_arbiter()

    for rec in sample:
        path = _REPO_ROOT / rec["file"]
        if not path.exists():
            print(f"  SKIP missing: {rec['file']}")
            continue
        res = evaluate_replay(
            path,
            classifier=classifier,
            predictor=predictor,
            snapshot_path=ctx["snapshot_path"],
            config_snapshot=ctx.get("config"),
        )
        print(
            f"  {path.name[:50]:<50} v13={res['v13_actual']:5s} "
            f"athena={res['athena_predicted']:5s} "
            f"hp(self={res['athena_hp_final']:5.1f}, opp={res['opp_hp_final']:5.1f}) "
            f"turns={res['turns_simulated']:3d} "
            f"wall={res['wall_clock_s']:.2f}s"
        )
        if res.get("error"):
            print(f"    ERROR: {res['error'][:200]}")
    return 0


if __name__ == "__main__":
    sys.exit(_smoke_main())


__all__ = ["evaluate_replay"]
