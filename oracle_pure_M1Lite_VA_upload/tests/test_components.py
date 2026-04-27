"""Component-level validation tests for oracle_pure.

Run from the algos/oracle_pure directory:
    python3 tests/test_components.py
"""
from __future__ import annotations

import json
import os
import sys
from copy import deepcopy
from pathlib import Path

THIS = Path(__file__).resolve()
ORACLE_PURE = THIS.parent.parent
if str(ORACLE_PURE) not in sys.path:
    sys.path.insert(0, str(ORACLE_PURE))

import gamelib  # noqa: E402

from oracle_core.constants import (   # noqa: E402
    SCOUT_IDX, SP_RES, MP_RES, TURRET_IDX, WALL_IDX,
)
from oracle_core.enumerator import enumerate_plans   # noqa: E402
from oracle_core.opponent_model import (              # noqa: E402
    ActionSignature, OpponentModel, materialize_signature,
)
from oracle_core.plan import (                       # noqa: E402
    ActionPlan, KIND_SPAWN_MOBILE, KIND_SPAWN_STRUCT, apply_to_state_dict,
)
from oracle_core.search import search                 # noqa: E402
from oracle_core.state_adapter import adapt_game_state   # noqa: E402
from oracle_core.value import evaluate                # noqa: E402


def _load_config() -> dict:
    path = ORACLE_PURE / "data" / "citadel_config_snapshot.json"
    with path.open() as f:
        return json.load(f)


def _make_initial_game_state(config: dict) -> "gamelib.GameState":
    """Build a minimal turn 0 GameState for testing.

    The starter kit's GameState requires a turn-state JSON string. We
    construct the minimal valid string for turn 0.
    """
    turn_state_json = json.dumps({
        "turnInfo": [0, 0, -1, 0],
        "p1Stats": [40, 8, 1, 0],   # hp, sp, mp
        "p2Stats": [40, 8, 1, 0],
        "p1Units": [[], [], [], [], [], [], [], []],
        "p2Units": [[], [], [], [], [], [], [], []],
        "events": {"spawn": [], "breach": [], "damage": [], "death": [],
                   "attack": [], "shield": [], "move": [], "selfDestruct": [],
                   "melee": []},
    })
    gs = gamelib.GameState(config, turn_state_json)
    gs.suppress_warnings(True)
    return gs


def _make_midgame_game_state(config: dict) -> "gamelib.GameState":
    """Build a turn 12 GameState with several structures already placed."""
    # p1Units format: list of 8 lists; index 0 = walls, 2 = turrets, 1 = supports
    p1_walls = [[x, 12, 60.0, str(100 + i)] for i, x in enumerate(range(2, 26)) if x not in (12, 15)]
    p1_turrets = [
        [11, 11, 100.0, "201"],   # upgraded center
        [13, 11, 100.0, "202"],
        [14, 11, 100.0, "203"],
        [16, 11, 100.0, "204"],
        [7, 9, 60.0, "205"],
        [20, 9, 60.0, "206"],
    ]
    turn_state = {
        "turnInfo": [0, 12, -1, 0],
        "p1Stats": [38, 14, 7, 0],
        "p2Stats": [37, 16, 9, 0],
        "p1Units": [
            p1_walls, [], p1_turrets,
            [], [], [], [], [],
        ],
        "p2Units": [[], [], [], [], [], [], [], []],
        "events": {"spawn": [], "breach": [], "damage": [], "death": [],
                   "attack": [], "shield": [], "move": [], "selfDestruct": [],
                   "melee": []},
    }
    gs = gamelib.GameState(config, json.dumps(turn_state))
    gs.suppress_warnings(True)
    return gs


# ===========================================================================
# Tests
# ===========================================================================

def test_enumerator_diversity_initial():
    config = _load_config()
    gs = _make_initial_game_state(config)
    plans = enumerate_plans(gs, config, max_plans=2000)
    assert len(plans) >= 100, f"only {len(plans)} plans for initial state"
    # Check archetype diversity
    archetypes = {p.archetype for p in plans}
    assert len(archetypes) >= 5, f"too few archetypes: {archetypes}"
    print(f"PASS test_enumerator_diversity_initial: {len(plans)} plans, "
          f"{len(archetypes)} archetypes")


def test_enumerator_diversity_midgame():
    config = _load_config()
    gs = _make_midgame_game_state(config)
    plans = enumerate_plans(gs, config, max_plans=2000)
    assert len(plans) >= 500, f"only {len(plans)} plans for midgame state"
    # Archetypes
    archetypes = {p.archetype.split("+")[1] if "+" in p.archetype else p.archetype
                  for p in plans}
    assert "hold" in archetypes, f"hold archetype missing: {archetypes}"
    assert any("scout" in a for a in archetypes), \
        f"scout archetypes missing: {archetypes}"
    print(f"PASS test_enumerator_diversity_midgame: {len(plans)} plans, "
          f"{len(archetypes)} offense archetypes")


def test_enumerator_feasibility():
    """All plans must respect SP/MP budget."""
    config = _load_config()
    gs = _make_midgame_game_state(config)
    sp = float(gs.get_resource(SP_RES, 0))
    mp = float(gs.get_resource(MP_RES, 0))
    plans = enumerate_plans(gs, config, max_plans=2000)
    for p in plans:
        assert p.sp_cost <= sp + 1e-6, \
            f"plan {p.name} sp_cost={p.sp_cost} > sp={sp}"
        assert p.mp_cost <= mp + 1e-6, \
            f"plan {p.name} mp_cost={p.mp_cost} > mp={mp}"
    print(f"PASS test_enumerator_feasibility: all {len(plans)} plans within budget")


def test_value_anti_symmetry():
    """For a symmetric state, evaluate(p=1) == -evaluate(p=2) (modulo
    structure-value bias which is absolute)."""
    state = {
        "turn": 5,
        "p1": {"hp": 35.0, "sp": 10.0, "mp": 5.0},
        "p2": {"hp": 30.0, "sp": 8.0, "mp": 4.0},
        "structures": [],
        "mobiles": [],
    }
    s1 = evaluate(state, my_player=1)
    s2 = evaluate(state, my_player=2)
    # Should be approximately opposite for HP/resource terms
    assert abs(s1 + s2) < 1e-3, f"non-anti-symmetric: s1={s1}, s2={s2}"
    print(f"PASS test_value_anti_symmetry: s1={s1:.2f} s2={s2:.2f}")


def test_value_hp_dominance():
    """Losing 5 HP should outweigh gaining ~5 SP (resources weight is small)."""
    state_keep = {
        "turn": 5,
        "p1": {"hp": 40.0, "sp": 10.0, "mp": 5.0},
        "p2": {"hp": 40.0, "sp": 10.0, "mp": 5.0},
        "structures": [], "mobiles": [],
    }
    state_lose_hp = deepcopy(state_keep)
    state_lose_hp["p1"]["hp"] = 35.0
    state_lose_hp["p1"]["sp"] = 30.0   # huge SP gain
    s_keep = evaluate(state_keep, my_player=1)
    s_lose = evaluate(state_lose_hp, my_player=1)
    assert s_keep > s_lose, \
        f"HP not dominant: keep={s_keep}, lose-hp-gain-sp={s_lose}"
    print(f"PASS test_value_hp_dominance: keep_hp={s_keep:.0f} > "
          f"lose_hp_gain_sp={s_lose:.0f}")


def test_opp_model_load_and_sample():
    config = _load_config()
    gs = _make_midgame_game_state(config)
    prior = ORACLE_PURE / "data" / "opp_model_priors.json"
    model = OpponentModel(prior_path=str(prior), rng_seed=0)
    assert model.stats()["prior_buckets"] >= 30, \
        f"prior loaded only {model.stats()['prior_buckets']} buckets"
    samples = model.sample(gs, our_mp=7.0, opp_mp=9.0,
                           recent_breaches=0, k=4, opp_player=2,
                           config=config)
    assert len(samples) >= 1, f"no opp samples returned"
    print(f"PASS test_opp_model_load_and_sample: "
          f"{model.stats()['prior_buckets']} prior buckets, "
          f"{len(samples)} samples returned")


def test_apply_plan_to_state_dict():
    """Plan operations should mutate state_dict correctly + deduct resources."""
    config = _load_config()
    state = {
        "turn": 5,
        "p1": {"hp": 40.0, "sp": 10.0, "mp": 5.0},
        "p2": {"hp": 40.0, "sp": 8.0, "mp": 5.0},
        "structures": [], "mobiles": [],
    }
    plan = ActionPlan(name="test")
    plan.add_struct(TURRET_IDX, 13, 11)   # cost 2 SP
    plan.add_struct(WALL_IDX, 13, 12)      # cost 1 SP
    plan.add_mobile(SCOUT_IDX, 14, 0, 3)   # cost 3 MP
    apply_to_state_dict(plan, state, my_player=1, config=config)
    assert state["p1"]["sp"] == 7.0, f"SP={state['p1']['sp']}, expected 7.0"
    assert state["p1"]["mp"] == 2.0, f"MP={state['p1']['mp']}, expected 2.0"
    assert len(state["structures"]) == 2
    assert len(state["mobiles"]) == 3
    print("PASS test_apply_plan_to_state_dict")


def test_search_runs_end_to_end():
    """End-to-end smoke: search returns a plan within time budget."""
    config = _load_config()
    gs = _make_midgame_game_state(config)
    prior = ORACLE_PURE / "data" / "opp_model_priors.json"
    model = OpponentModel(prior_path=str(prior), rng_seed=0)
    debug_msgs = []
    result = search(
        gs, config, model,
        time_budget_s=10.0,   # generous for local pysim
        k_opp=3,
        phase2_top_n=10,
        do_depth2=True,
        depth2_top_n=2,
        recent_breaches=[(5, 9), (22, 8)],
        debug_log=debug_msgs.append,
    )
    tel = result.telemetry
    assert tel.candidates_total > 100, \
        f"only {tel.candidates_total} candidates"
    assert tel.sims_run > 0, f"no sims run: tel={tel}"
    assert result.best_plan.name, "no best plan name"
    print(f"PASS test_search_runs_end_to_end: best={result.best_plan.name} "
          f"score={tel.best_score:.0f} cands={tel.candidates_total} "
          f"eval1={tel.candidates_evaluated_phase1} "
          f"eval2={tel.candidates_evaluated_phase2} "
          f"sims={tel.sims_run} d2={tel.used_depth2} wall={tel.wall_clock_s:.2f}s")
    if debug_msgs:
        print("  debug msgs (first 3):")
        for m in debug_msgs[:3]:
            print(f"    {m}")
    if tel.top_n:
        print("  top-3 plans by score:")
        for n, s, d in tel.top_n[:3]:
            print(f"    {n}  score={s:.0f}  delta={d:.0f}")


def main():
    print("=" * 70)
    print("oracle_pure component tests")
    print("=" * 70)
    test_enumerator_diversity_initial()
    test_enumerator_diversity_midgame()
    test_enumerator_feasibility()
    test_value_anti_symmetry()
    test_value_hp_dominance()
    test_opp_model_load_and_sample()
    test_apply_plan_to_state_dict()
    test_search_runs_end_to_end()
    print("=" * 70)
    print("ALL TESTS PASSED")


if __name__ == "__main__":
    main()
