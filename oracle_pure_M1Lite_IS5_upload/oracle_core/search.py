"""Search loop for oracle_pure - VARIANT F (VA + VB only).

Combines:
- VA (I7): variance-aware ranking with VARIANCE_LAMBDA = 0.1
- VA (I3): wasted-MP penalty (passes pre_state_dict to evaluate)
- VB (I2): sim-based viability probe per defense template

VC removed based on live ladder evidence (caused regression on
pipmy/Redemption — VD lost where VA won, the only difference being VC).
"""
from __future__ import annotations

import math
import time
from copy import deepcopy
from dataclasses import dataclass, field
from typing import Any, Dict, List, Optional, Sequence, Tuple

from .constants import (
    DEMOLISHER_IDX, INTERCEPTOR_IDX, MP_RES, SCOUT_IDX, SP_RES,
    cost_for_idx,
)
from .enumerator import enumerate_plans
from .opponent_model import (
    ActionSignature, OpponentModel, materialize_signature,
)
from .plan import (
    ActionPlan, KIND_SPAWN_MOBILE, apply_to_state_dict,
)
from .sim_eval import (
    _apply_deploys_inplace, _bridge_or_pysim, _get_sim_rs,
    _default_config_path,
)
from .state_adapter import adapt_game_state
from .value import VALUE_WEIGHTS, evaluate
from .viability_probe import (
    PROBE_SCOUT_COUNT, probe_offense_viability,
)


# VA — I7: variance-aware ranking. lambda=0.1 calibrated by VA agent.
VARIANCE_LAMBDA = 0.1


@dataclass
class SearchTelemetry:
    candidates_total: int = 0
    candidates_evaluated_phase1: int = 0
    candidates_evaluated_phase2: int = 0
    sims_run: int = 0
    wall_clock_s: float = 0.0
    used_depth2: bool = False
    deadline_hit_phase: int = 0
    top_n: List[Tuple[str, float, float]] = field(default_factory=list)
    best_plan_name: str = ""
    best_score: float = 0.0
    fallback_used: bool = False


@dataclass
class SearchResult:
    best_plan: ActionPlan
    telemetry: SearchTelemetry


def _run_sim(state_dict: Dict[str, Any], config_path: str
             ) -> Dict[str, Any]:
    sim_rs = _get_sim_rs()
    if sim_rs is not None:
        return sim_rs.simulate_action_phase_py(state_dict, config_path)
    return _bridge_or_pysim(state_dict, config_path)


def _fast_copy_state(s: Dict[str, Any]) -> Dict[str, Any]:
    return {
        "turn": s["turn"],
        "p1": dict(s["p1"]),
        "p2": dict(s["p2"]),
        "structures": [{**ss, "xy": list(ss["xy"])} for ss in s["structures"]],
        "mobiles": [
            {**mm, "xy": list(mm["xy"]),
             "spawn_xy": list(mm.get("spawn_xy", mm["xy"]))}
            for mm in s["mobiles"]
        ],
    }


def _apply_plan(plan: ActionPlan, state_dict: Dict[str, Any], my_player: int,
                config) -> int:
    return apply_to_state_dict(plan, state_dict, my_player=my_player,
                               config=config, deduct_resources=True)


def _project_next_turn(post_state: Dict[str, Any], opp_top_sig: ActionSignature,
                       config, my_player: int, config_path: str
                       ) -> Dict[str, Any]:
    sd = deepcopy(post_state)
    turn = int(sd.get("turn", 0)) + 1
    sd["turn"] = turn

    for key in ("p1", "p2"):
        sp = float(sd[key]["sp"])
        mp = float(sd[key]["mp"])
        mp = round(mp * 0.75, 1)
        mp_income = 1 + (turn // 5)
        sp_income = 4
        sd[key]["sp"] = round(sp + sp_income, 1)
        sd[key]["mp"] = round(mp + mp_income, 1)

    opp_player = 2 if my_player == 1 else 1
    if not opp_top_sig.is_empty():
        opp_plan = materialize_signature(
            opp_top_sig, opp_player=opp_player,
            rng=__import__("random").Random(0),
            mp_budget=float(sd[("p2" if my_player == 1 else "p1")]["mp"]),
            config=config,
        )
        _apply_plan(opp_plan, sd, my_player=opp_player, config=config)

    side_key = "p1" if my_player == 1 else "p2"
    my_mp = float(sd[side_key]["mp"])
    scout_cost = cost_for_idx(config, SCOUT_IDX)
    if my_mp >= 6 * scout_cost:
        n = int(my_mp // max(scout_cost, 1e-6))
        if n > 0:
            quick = ActionPlan(name="d2:cheap_scout", archetype="depth2_heuristic")
            quick.add_mobile(SCOUT_IDX, 14, 0, n)
            quick.mp_cost = n * scout_cost
            _apply_plan(quick, sd, my_player=my_player, config=config)

    return _run_sim(sd, config_path)


def search(game_state, config, opp_model: OpponentModel, *,
           time_budget_s: float = 8.0,
           k_opp: int = 4,
           k_opp_phase1: int = 3,
           phase1_depth: int = 1,
           phase2_top_n: int = 30,
           do_depth2: bool = True,
           depth2_top_n: int = 3,
           max_plans: int = 3000,
           recent_breaches: Sequence[Tuple[int, int]] = (),
           config_path: Optional[str] = None,
           debug_log=None,
           ) -> SearchResult:
    """Pick the maximum-EU plan for the current turn.

    Variant F = VA + VB (no VC coverage term).
    """
    t0 = time.time()
    tel = SearchTelemetry()
    deadline = t0 + time_budget_s
    config_path = config_path or _default_config_path()

    if debug_log is None:
        debug_log = lambda _msg: None

    try:
        base_state = adapt_game_state(game_state, my_player=1,
                                      turn=int(game_state.turn_number))
    except Exception as e:
        debug_log(f"[search] adapt failed: {e!r}")
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # IS5: thread base_state + a sim_runner into enumerate_plans so
    # _gen_remove_to_unblock can run probe-driven removal generation.
    # The runner closure also feeds VB's per-template viability probe
    # below — same callable.
    def _probe_runner(state):
        return _run_sim(state, config_path)

    candidates = enumerate_plans(
        game_state, config,
        recent_breaches=recent_breaches,
        max_plans=max_plans,
        base_state=base_state,
        sim_runner=_probe_runner,
    )
    tel.candidates_total = len(candidates)
    if not candidates:
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # 2b. VB: Sim-based viability probe per unique defense template.

    defense_probes: Dict[str, int] = {}
    for cand in candidates:
        def_name = cand.name.split("|", 1)[0]
        if def_name in defense_probes:
            cand.defense_viability = defense_probes[def_name]
            continue
        defense_only = ActionPlan(name=def_name, archetype=cand.archetype)
        for op in cand.ops:
            if op.kind != KIND_SPAWN_MOBILE:
                defense_only.ops.append(op)
        try:
            breach = probe_offense_viability(
                base_state, defense_only, config, _probe_runner,
                my_player=1,
            )
            tel.sims_run += 1
        except Exception as e:
            debug_log(f"[search] viability probe failed for {def_name}: {e!r}")
            breach = PROBE_SCOUT_COUNT
        defense_probes[def_name] = breach
        cand.defense_viability = breach

    our_mp = float(game_state.get_resource(MP_RES, 0))
    opp_mp = float(game_state.get_resource(MP_RES, 1))
    opp_samples_phase1 = opp_model.sample(
        game_state, our_mp=our_mp, opp_mp=opp_mp,
        recent_breaches=len(recent_breaches), k=k_opp_phase1,
        opp_player=2, config=config,
    )
    opp_samples_phase2 = opp_model.sample(
        game_state, our_mp=our_mp, opp_mp=opp_mp,
        recent_breaches=len(recent_breaches), k=k_opp,
        opp_player=2, config=config,
    )

    # 4a. Phase-1
    phase1_scores: List[Tuple[float, ActionPlan]] = []
    for cand in candidates:
        if time.time() > deadline:
            tel.deadline_hit_phase = 1
            break
        try:
            sample_scores = []
            for opp_sample in opp_samples_phase1:
                if time.time() > deadline:
                    break
                sd = _fast_copy_state(base_state)
                _apply_plan(cand, sd, my_player=1, config=config)
                _apply_plan(opp_sample, sd, my_player=2, config=config)
                post = _run_sim(sd, config_path)
                tel.sims_run += 1
                sample_scores.append(
                    evaluate(post, my_player=1,
                             pre_state_dict=base_state,
                             defense_viability=cand.defense_viability)
                )
            if sample_scores:
                phase1_scores.append((sum(sample_scores) / len(sample_scores), cand))
                tel.candidates_evaluated_phase1 += 1
        except Exception as e:
            debug_log(f"[search] phase1 eval failed for {cand.name}: {e!r}")
            continue

    if not phase1_scores:
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # 4b. Phase-2 with VA's variance-aware ranking
    phase1_scores.sort(key=lambda t: -t[0])
    top_for_phase2 = phase1_scores[:phase2_top_n]

    phase2_scores_full: List[Tuple[float, float, float, ActionPlan]] = []
    for score1, cand in top_for_phase2:
        if time.time() > deadline:
            tel.deadline_hit_phase = 2
            break
        sample_scores = []
        for opp_sample in opp_samples_phase2:
            if time.time() > deadline:
                break
            try:
                sd = _fast_copy_state(base_state)
                _apply_plan(cand, sd, my_player=1, config=config)
                _apply_plan(opp_sample, sd, my_player=2, config=config)
                post = _run_sim(sd, config_path)
                tel.sims_run += 1
                score = evaluate(post, my_player=1,
                                 pre_state_dict=base_state,
                                 defense_viability=cand.defense_viability)
                sample_scores.append(score)
            except Exception as e:
                debug_log(f"[search] phase2 eval failed for {cand.name}: {e!r}")
                continue
        if sample_scores:
            mean_score = sum(sample_scores) / len(sample_scores)
            if len(sample_scores) >= 2:
                var = sum((s - mean_score) ** 2 for s in sample_scores) / (
                    len(sample_scores) - 1
                )
                std_score = math.sqrt(max(0.0, var))
            else:
                std_score = 0.0
            risk_adj_score = mean_score - VARIANCE_LAMBDA * std_score
            phase2_scores_full.append(
                (risk_adj_score, mean_score, std_score, cand)
            )
            tel.candidates_evaluated_phase2 += 1

    phase2_scores: List[Tuple[float, ActionPlan]] = [
        (risk_adj, plan) for risk_adj, _mean, _std, plan in phase2_scores_full
    ]

    final_scores = phase2_scores if phase2_scores else phase1_scores

    # 4c. Depth-2
    if do_depth2 and time.time() < deadline:
        final_scores.sort(key=lambda t: -t[0])
        depth2_top = final_scores[:depth2_top_n]
        depth2_scores: List[Tuple[float, ActionPlan]] = []

        top_opp_sig = None
        if opp_samples_phase2:
            opp_first = opp_samples_phase2[0]
            mob_ops = opp_first.mobile_ops()
            scout = sum(op.count for op in mob_ops if op.unit_idx == SCOUT_IDX)
            demo = sum(op.count for op in mob_ops if op.unit_idx == DEMOLISHER_IDX)
            ints = sum(op.count for op in mob_ops if op.unit_idx == INTERCEPTOR_IDX)
            from .opponent_model import _x_to_launcher_bucket
            scout_lncher = (
                _x_to_launcher_bucket(mob_ops[0].xy[0])
                if scout > 0 and mob_ops else None
            )
            top_opp_sig = ActionSignature(
                scout_count=scout, demo_count=demo, int_count=ints,
                scout_launcher=scout_lncher,
            )

        for score, cand in depth2_top:
            if time.time() > deadline:
                break
            try:
                sd = _fast_copy_state(base_state)
                _apply_plan(cand, sd, my_player=1, config=config)
                if opp_samples_phase2:
                    _apply_plan(opp_samples_phase2[0], sd, my_player=2, config=config)
                post1 = _run_sim(sd, config_path)
                tel.sims_run += 1
                if top_opp_sig is None:
                    top_opp_sig = ActionSignature()
                post2 = _project_next_turn(
                    post1, top_opp_sig, config,
                    my_player=1, config_path=config_path,
                )
                tel.sims_run += 1
                s1 = evaluate(post1, my_player=1,
                              pre_state_dict=base_state,
                              defense_viability=cand.defense_viability)
                s2 = evaluate(post2, my_player=1,
                              pre_state_dict=post1,
                              defense_viability=cand.defense_viability)
                combined = 0.6 * s1 + 0.4 * s2
                depth2_scores.append((combined, cand))
                tel.used_depth2 = True
            except Exception as e:
                debug_log(f"[search] depth-2 failed for {cand.name}: {e!r}")
                continue
        if depth2_scores:
            depth2_scores.sort(key=lambda t: -t[0])
            depth2_names = {p.name for _, p in depth2_scores}
            kept = [(s, p) for s, p in final_scores if p.name not in depth2_names]
            final_scores = depth2_scores + kept

    final_scores.sort(key=lambda t: -t[0])
    best_score, best_plan = final_scores[0]

    tel.wall_clock_s = time.time() - t0
    tel.best_plan_name = best_plan.name
    tel.best_score = best_score
    tel.top_n = [
        (p.name, score, score - best_score)
        for score, p in final_scores[:5]
    ]

    # IS5 telemetry: report when removal templates fire, both at
    # enumeration and at selection. Greppable as "[IS5]" in stderr.
    n_remove_cands = sum(
        1 for c in candidates
        if c.name.split("|", 1)[0].startswith("defense:remove_unblock_")
    )
    if n_remove_cands > 0:
        debug_log(
            f"[IS5] removal templates ENUMERATED this turn: "
            f"{n_remove_cands} (out of {len(candidates)} candidates)"
        )
    if best_plan.name.split("|", 1)[0].startswith("defense:remove_unblock_"):
        debug_log(
            f"[IS5] removal template SELECTED as best plan: "
            f"{best_plan.name} (score={best_score:.2f})"
        )

    return SearchResult(best_plan=best_plan, telemetry=tel)


__all__ = [
    "search", "SearchResult", "SearchTelemetry",
]
