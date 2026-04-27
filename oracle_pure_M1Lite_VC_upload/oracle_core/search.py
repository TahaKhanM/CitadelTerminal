"""Search loop for oracle_pure.

Architecture (this is the LOAD-BEARING control flow — NOT a wrapper
around heuristic_v1):

    on_turn:
      1. base_state = adapt_game_state(game_state)
      2. candidates = enumerate_plans(game_state)           ~500-2000
      3. opp_samples = opp_model.sample(k=K_OPP)            ~4-8
      4. Two-phase eval:
         a) Phase-1 cull: each candidate × 1 opp_sample   → score_1[]
         b) Phase-2 confidence: top-N candidates × K_OPP  → score_2[]
      5. Optional depth-2: top-3 candidates project +1 turn (heuristic
         opp_top response + our heuristic next-turn move) and rescored.
      6. Pick max-EU plan. Apply.

Time budget is the binding constraint. We never do a "fixed work then
score" pass — we always check the deadline before each sim_rs call.
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


# ---------------------------------------------------------------------------
# Telemetry
# ---------------------------------------------------------------------------

@dataclass
class SearchTelemetry:
    candidates_total: int = 0
    candidates_evaluated_phase1: int = 0
    candidates_evaluated_phase2: int = 0
    sims_run: int = 0
    wall_clock_s: float = 0.0
    used_depth2: bool = False
    deadline_hit_phase: int = 0  # 0=not, 1=phase1, 2=phase2
    top_n: List[Tuple[str, float, float]] = field(default_factory=list)
    best_plan_name: str = ""
    best_score: float = 0.0
    fallback_used: bool = False


@dataclass
class SearchResult:
    best_plan: ActionPlan
    telemetry: SearchTelemetry


# ---------------------------------------------------------------------------
# Sim invocation (sim_rs OR pysim — whichever is loaded)
# ---------------------------------------------------------------------------

def _run_sim(state_dict: Dict[str, Any], config_path: str
             ) -> Dict[str, Any]:
    """Call sim_rs.simulate_action_phase_py if available, else pysim."""
    sim_rs = _get_sim_rs()
    if sim_rs is not None:
        return sim_rs.simulate_action_phase_py(state_dict, config_path)
    return _bridge_or_pysim(state_dict, config_path)


def _fast_copy_state(s: Dict[str, Any]) -> Dict[str, Any]:
    """Hand-rolled deep copy for the sim state_dict — ~22x faster than
    `copy.deepcopy` because it knows the exact structure: top-level dict
    with scalar fields, two player dicts of scalars, and lists of dicts
    whose only nested mutable fields are short coordinate lists.
    """
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


# ---------------------------------------------------------------------------
# Apply a plan to a sim state (treats both player perspectives)
# ---------------------------------------------------------------------------

def _apply_plan(plan: ActionPlan, state_dict: Dict[str, Any], my_player: int,
                config) -> int:
    """Apply structure + mobile ops in plan to state_dict.

    Returns the next free UID.
    """
    return apply_to_state_dict(plan, state_dict, my_player=my_player,
                               config=config, deduct_resources=True)


# ---------------------------------------------------------------------------
# Depth-2 projection: cheap next-turn heuristic
# ---------------------------------------------------------------------------

def _project_next_turn(post_state: Dict[str, Any], opp_top_sig: ActionSignature,
                       config, my_player: int, config_path: str
                       ) -> Dict[str, Any]:
    """Apply a cheap next-turn projection on the post-action state.

    - Add expected income to both sides (4 SP, +MP per turn formula, decay).
    - Sample 1 opp action (the top sig).
    - Apply our cheap "best scout rush from optimal corner" heuristic.
    - Run sim_rs again.
    - Return the second post-state.
    """
    sd = deepcopy(post_state)
    turn = int(sd.get("turn", 0)) + 1
    sd["turn"] = turn

    # Income / decay
    for key in ("p1", "p2"):
        sp = float(sd[key]["sp"])
        mp = float(sd[key]["mp"])
        # MP decays first, then accrues
        mp = round(mp * 0.75, 1)
        # Per-turn MP income (Citadel: 1 MP base, +1 every 5 turns)
        mp_income = 1 + (turn // 5)
        sp_income = 4
        sd[key]["sp"] = round(sp + sp_income, 1)
        sd[key]["mp"] = round(mp + mp_income, 1)

    # Apply opp's top action (their predicted next turn)
    opp_player = 2 if my_player == 1 else 1
    if not opp_top_sig.is_empty():
        # Materialize at canonical center launcher
        opp_plan = materialize_signature(
            opp_top_sig, opp_player=opp_player,
            rng=__import__("random").Random(0),
            mp_budget=float(sd[("p2" if my_player == 1 else "p1")]["mp"]),
            config=config,
        )
        _apply_plan(opp_plan, sd, my_player=opp_player, config=config)

    # Apply our cheap "best scout rush from center" heuristic
    side_key = "p1" if my_player == 1 else "p2"
    my_mp = float(sd[side_key]["mp"])
    scout_cost = cost_for_idx(config, SCOUT_IDX)
    if my_mp >= 6 * scout_cost:
        n = int(my_mp // max(scout_cost, 1e-6))
        if n > 0:
            # Spawn from center launcher
            quick = ActionPlan(name="d2:cheap_scout", archetype="depth2_heuristic")
            quick.add_mobile(SCOUT_IDX, 14, 0, n)
            quick.mp_cost = n * scout_cost
            _apply_plan(quick, sd, my_player=my_player, config=config)

    # Run sim
    return _run_sim(sd, config_path)


# ---------------------------------------------------------------------------
# Main search
# ---------------------------------------------------------------------------

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
           breach_pressure: Optional[Dict[Tuple[int, int], float]] = None,
           config_path: Optional[str] = None,
           debug_log=None,
           ) -> SearchResult:
    """Pick the maximum-EU plan for the current turn.

    Args:
      game_state: live gamelib.GameState
      config: live game config
      opp_model: trained OpponentModel instance
      time_budget_s: hard wall-clock cap (search returns best-so-far)
      k_opp: opp samples for phase-2 confidence
      phase1_depth: 1 = single sample per candidate (quick cull)
      phase2_top_n: how many top candidates from phase-1 to re-eval at k_opp
      do_depth2: whether to run depth-2 projection on top candidates
      depth2_top_n: how many candidates get depth-2 treatment
      recent_breaches: tiles where opp recently breached us (for enumerator)
      breach_pressure: per-tile soft pressure (Variant C). Decayed map of
          observed-breach intensity passed through to value.evaluate()
          so the search rewards plans that cover hotspots. Optional —
          empty/None disables the term (back-compat with M1Lite).
      config_path: path to citadel_config_snapshot.json (for sim_rs)
      debug_log: optional callable(str) for telemetry messages
    """
    t0 = time.time()
    tel = SearchTelemetry()
    deadline = t0 + time_budget_s

    config_path = config_path or _default_config_path()

    if debug_log is None:
        debug_log = lambda _msg: None  # noqa: E731

    # 1. Snapshot current state
    try:
        base_state = adapt_game_state(game_state, my_player=1,
                                      turn=int(game_state.turn_number))
    except Exception as e:
        debug_log(f"[search] adapt failed: {e!r}")
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # 2. Enumerate candidates
    candidates = enumerate_plans(
        game_state, config,
        recent_breaches=recent_breaches,
        max_plans=max_plans,
    )
    tel.candidates_total = len(candidates)
    if not candidates:
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # 3. Sample opp plans (separate sets so phase-1 and phase-2 are
    # diverse rather than overlapping subsets)
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

    # 4a. Phase-1: score every candidate against k_opp_phase1 samples
    # (still cheap relative to phase-2 since phase2_top_n is small).
    # Per-candidate score = mean over k_opp_phase1 samples.
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
                sample_scores.append(evaluate(post, my_player=1,
                                              breach_pressure=breach_pressure))
            if sample_scores:
                phase1_scores.append((sum(sample_scores) / len(sample_scores), cand))
                tel.candidates_evaluated_phase1 += 1
        except Exception as e:
            debug_log(f"[search] phase1 eval failed for {cand.name}: {e!r}")
            continue

    if not phase1_scores:
        # Couldn't evaluate any candidate — return empty plan as fallback
        return SearchResult(best_plan=ActionPlan(name="empty"), telemetry=tel)

    # 4b. Phase-2: rescore top-N at k_opp confidence
    phase1_scores.sort(key=lambda t: -t[0])
    top_for_phase2 = phase1_scores[:phase2_top_n]

    phase2_scores: List[Tuple[float, ActionPlan]] = []
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
                                 breach_pressure=breach_pressure)
                sample_scores.append(score)
            except Exception as e:
                debug_log(f"[search] phase2 eval failed for {cand.name}: {e!r}")
                continue
        if sample_scores:
            avg = sum(sample_scores) / len(sample_scores)
            phase2_scores.append((avg, cand))
            tel.candidates_evaluated_phase2 += 1

    # Use phase-2 scores if we got any, else fall back to phase-1
    final_scores = phase2_scores if phase2_scores else phase1_scores

    # 4c. Depth-2: rescore top-N with next-turn projection
    if do_depth2 and time.time() < deadline:
        final_scores.sort(key=lambda t: -t[0])
        depth2_top = final_scores[:depth2_top_n]
        depth2_scores: List[Tuple[float, ActionPlan]] = []

        # Find the top opp sig for depth-2 projection
        top_opp_sig = None
        if opp_samples_phase2:
            # Use the first sample (highest weight)
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
                # Project second turn
                if top_opp_sig is None:
                    top_opp_sig = ActionSignature()
                post2 = _project_next_turn(
                    post1, top_opp_sig, config,
                    my_player=1, config_path=config_path,
                )
                tel.sims_run += 1
                # Score: average of post1 and post2 (post2 weighted slightly more
                # since it incorporates next-turn dynamics)
                s1 = evaluate(post1, my_player=1,
                              breach_pressure=breach_pressure)
                s2 = evaluate(post2, my_player=1,
                              breach_pressure=breach_pressure)
                combined = 0.6 * s1 + 0.4 * s2
                depth2_scores.append((combined, cand))
                tel.used_depth2 = True
            except Exception as e:
                debug_log(f"[search] depth-2 failed for {cand.name}: {e!r}")
                continue
        if depth2_scores:
            depth2_scores.sort(key=lambda t: -t[0])
            # Replace top-N in final_scores with depth-2 scores; keep other
            # phase-2 candidates for completeness.
            depth2_names = {p.name for _, p in depth2_scores}
            kept = [(s, p) for s, p in final_scores if p.name not in depth2_names]
            final_scores = depth2_scores + kept

    final_scores.sort(key=lambda t: -t[0])
    best_score, best_plan = final_scores[0]

    # Telemetry
    tel.wall_clock_s = time.time() - t0
    tel.best_plan_name = best_plan.name
    tel.best_score = best_score
    tel.top_n = [
        (p.name, score, score - best_score)
        for score, p in final_scores[:5]
    ]
    return SearchResult(best_plan=best_plan, telemetry=tel)


__all__ = [
    "search", "SearchResult", "SearchTelemetry",
]
