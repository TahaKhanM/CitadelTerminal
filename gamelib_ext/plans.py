"""Plan dataclass + candidate_plans generator.

A Plan is the aggregate set of commits a player can make in a single deploy
phase: structure builds, upgrades, removals, mobile spawns. `candidate_plans`
produces a shortlist of ≤N budget-feasible, non-dominated plans so Phase 3
search can evaluate each via the simulator.

Design goals:
- Bounded (≤ max_plans = 20) to keep Phase 3 under the 1.5 s turn budget.
- Diverse across strategic axes: defense-heavy, offense-heavy, Support-stacking.
- Cheap to generate: O(budget) per plan, no exhaustive search.
"""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Optional, Tuple

from .simulator import Plan, SimState


DEFAULT_MAX_PLANS = 20


def _plan_no_op() -> Plan:
    """Baseline: do nothing."""
    return Plan()


def _scout_rush_left() -> Plan:
    return Plan(mobile_spawns=[("PI", 13, 0, 1000)])


def _scout_rush_right() -> Plan:
    return Plan(mobile_spawns=[("PI", 14, 0, 1000)])


def _scout_split() -> Plan:
    return Plan(mobile_spawns=[("PI", 13, 0, 500), ("PI", 14, 0, 500)])


def _demo_left() -> Plan:
    return Plan(mobile_spawns=[("EI", 13, 0, 1000)])


def _demo_right() -> Plan:
    return Plan(mobile_spawns=[("EI", 14, 0, 1000)])


def _interceptor_plug() -> Plan:
    return Plan(mobile_spawns=[("SI", 13, 0, 1)])


def _support_caravan(sp_budget: float) -> Plan:
    """Build upgraded Supports at back row Y=13 if affordable."""
    adds = []
    upgrades = []
    if sp_budget >= 32:
        for x in (12, 13, 14, 15):
            adds.append(("EF", x, 13))
            upgrades.append((x, 13))
    elif sp_budget >= 16:
        for x in (13, 14):
            adds.append(("EF", x, 13))
            upgrades.append((x, 13))
    return Plan(structure_adds=adds, upgrades=upgrades)


def _wall_fortify_center(sp_budget: float) -> Plan:
    """Fill in missing center-row walls on Y=12."""
    adds = []
    if sp_budget >= 6:
        for x in (12, 13, 14, 15):
            adds.append(("FF", x, 12))
    return Plan(structure_adds=adds)


def _turret_reinforce_center(sp_budget: float) -> Plan:
    """Drop upgraded turrets at main center slots."""
    adds = []
    upgrades = []
    if sp_budget >= 24:
        for loc in ([13, 11], [14, 11]):
            adds.append(("DF", loc[0], loc[1]))
            upgrades.append((loc[0], loc[1]))
    elif sp_budget >= 12:
        for loc in ([13, 11],):
            adds.append(("DF", loc[0], loc[1]))
            upgrades.append((loc[0], loc[1]))
    return Plan(structure_adds=adds, upgrades=upgrades)


def _scout_rush_plus_supports(sp_budget: float) -> Plan:
    p = _support_caravan(sp_budget)
    p.mobile_spawns = [("PI", 14, 0, 1000)]
    return p


def _scout_rush_plus_supports_left(sp_budget: float) -> Plan:
    p = _support_caravan(sp_budget)
    p.mobile_spawns = [("PI", 13, 0, 1000)]
    return p


def candidate_plans(state: SimState, max_plans: int = DEFAULT_MAX_PLANS) -> List[Plan]:
    """Enumerate ≤max_plans budget-feasible, non-dominated Plan candidates.

    Non-dominated means no pair is Pareto-equivalent; we enforce this by
    covering distinct strategic axes. The caller feeds each plan to the
    simulator and picks the best.
    """
    sp = state.my_sp
    mp = state.my_mp

    scout_cost = 1.0
    demo_cost = 3.0
    interceptor_cost = 1.0

    plans: List[Plan] = []
    plans.append(_plan_no_op())

    # Offense options (only if we can afford to spawn)
    if mp >= 10 * scout_cost:
        plans.append(_scout_rush_left())
        plans.append(_scout_rush_right())
        plans.append(_scout_split())
    elif mp >= 5 * scout_cost:
        plans.append(_scout_rush_right())

    if mp >= 4 * demo_cost:
        plans.append(_demo_left())
        plans.append(_demo_right())

    if mp >= interceptor_cost and state.enemy_mp >= 6:
        plans.append(_interceptor_plug())

    # Defense-structural options
    if sp >= 6:
        plans.append(_wall_fortify_center(sp))
    if sp >= 12:
        plans.append(_turret_reinforce_center(sp))

    # Support-caravan offense (combined)
    if sp >= 16:
        plans.append(_support_caravan(sp))
    if sp >= 16 and mp >= 10:
        plans.append(_scout_rush_plus_supports(sp))
        plans.append(_scout_rush_plus_supports_left(sp))

    # Dedupe by fingerprint (structure + mobile signature)
    seen = set()
    out: List[Plan] = []
    for p in plans:
        key = (
            tuple(sorted(p.structure_adds)),
            tuple(sorted(p.upgrades)),
            tuple(sorted(p.removals)),
            tuple(sorted(p.mobile_spawns)),
        )
        if key in seen:
            continue
        seen.add(key)
        out.append(p)
        if len(out) >= max_plans:
            break
    return out


__all__ = ["Plan", "candidate_plans", "DEFAULT_MAX_PLANS"]
