"""Sim-evaluated fitness — Phase 6 milestone J1.

For each Genome we want a single fitness scalar. The full Athena loop
(EconomyArbiter + algo_strategy + engine.jar) is too heavy to call per
genome — at ~140 ms/turn × 100 turns × 6 matches × 200 iterations =
16,800 s. Instead we use a **lightweight sim harness** built on top of
the Rust ``simulate_action_phase_py`` binding:

  1. Build a fresh sim_rs state dict (empty board, starting HP/SP/MP).
  2. Place the genome's defense archetype as structures (bottom side).
  3. Place a baseline opponent's defense archetype mirrored on top.
  4. For N=8 sim rounds:
       a. Add per-turn MP income to both sides.
       b. Each side commits a fraction of MP to its dominant offense
          template (genome's vs baseline's).
       c. Run one action phase via ``simulate_action_phase_py``.
       d. Accumulate HP/SP/MP/breach stats.
  5. Final fitness = ``alpha_w * delta_hp_inflicted - beta_w * delta_hp_taken``
     where alpha_w/beta_w come from the genome itself (this rewards
     genomes that pick consistent utility weights).

This is NOT a substitute for engine.jar matches — it ignores wall
stationary placement quirks, the 2-turn build-then-attack cadence, and
some opponent reactivity. But it produces a **rank-correlated** signal
strong enough to seed MAP-Elites diversity.

Per-call cost on Apple M4: ~30-40 ms for 8 rounds (4 sims/round × 8 ≈
32 sim calls per match, ~8 matches per genome -> ~250 sim calls).
Within Phase 6's 200-iteration budget that's ~1 minute total — safe.

Public API::

    evaluate(genome, *, baselines, n_rounds=8, seed=0)
        -> (fitness_score, behavior_descriptors, sim_summary)
"""
from __future__ import annotations

import json
import os
import sys
import time
from copy import deepcopy
from pathlib import Path
from typing import Any, Dict, List, Optional, Sequence, Tuple

# Re-use the existing offense subpackage helpers.
try:
    from ..offense.sim_eval import _get_sim_rs, _default_config_path
    from ..offense.templates import (
        OffenseTemplate,
        SpawnGroup,
        load_all_templates,
    )
except ImportError:  # pragma: no cover — exercised only by direct invocation
    sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
    from offense.sim_eval import _get_sim_rs, _default_config_path  # type: ignore
    from offense.templates import (  # type: ignore
        OffenseTemplate,
        SpawnGroup,
        load_all_templates,
    )

from .genome import DEFENSE_ARCHETYPES, DOMINANT_TEMPLATE_NAMES, Genome


# Default mobile starting HP (Citadel snapshot: Scout=15, Demolisher=5,
# Interceptor=40). Hardcoded as a fallback; the sim_rs binding reads
# the real values from config_path during simulate.
_DEFAULT_MOBILE_HP = {3: 15.0, 4: 5.0, 5: 40.0}
_MP_COST_BY_IDX = {3: 1.0, 4: 3.0, 5: 1.0}
_UNIT_NAME_TO_IDX = {
    "WALL": 0, "SUPPORT": 1, "TURRET": 2,
    "SCOUT": 3, "DEMOLISHER": 4, "INTERCEPTOR": 5,
}


# ---------------------------------------------------------------------------
# State-builder helpers
# ---------------------------------------------------------------------------


def _archetype_path(name: str) -> Path:
    here = Path(__file__).resolve().parents[1]
    return here / "defenses" / f"{name}.json"


def _load_defense(name: str) -> List[Dict[str, Any]]:
    """Return a list of placement dicts: {unit, loc, upgrade, priority}."""
    p = _archetype_path(name)
    if not p.exists():
        return []
    try:
        d = json.loads(p.read_text())
        return list(d.get("placements", []))
    except Exception:  # noqa: BLE001
        return []


def _structure_default_hp(type_idx: int, upgraded: bool, config: Dict[str, Any]) -> float:
    try:
        info = config["unitInformation"][type_idx]
    except (KeyError, IndexError, TypeError):
        return 1.0
    if upgraded and isinstance(info.get("upgrade"), dict):
        sh = info["upgrade"].get("startHealth", info.get("startHealth", 1.0))
    else:
        sh = info.get("startHealth", 1.0)
    try:
        return float(sh)
    except Exception:  # noqa: BLE001
        return 1.0


def _mirror_y(y: int) -> int:
    """Mirror a bottom-half y (0..13) to the top half (14..27)."""
    return 27 - int(y)


def _build_initial_state(
    *,
    my_defense: str,
    opp_defense: str,
    config: Dict[str, Any],
    my_density_mult: float = 1.0,
    starting_hp: float = 40.0,
    starting_sp: float = 8.0,
    starting_mp: float = 1.0,
) -> Dict[str, Any]:
    """Build a fresh sim_rs state dict with both sides' defense skeletons placed.

    p1 = bottom (us) — defense placed verbatim from JSON.
    p2 = top (opp) — defense mirrored over y=13.5.
    """
    state = {
        "turn": 0,
        "p1": {"hp": float(starting_hp), "sp": float(starting_sp),
               "mp": float(starting_mp)},
        "p2": {"hp": float(starting_hp), "sp": float(starting_sp),
               "mp": float(starting_mp)},
        "structures": [],
        "mobiles": [],
    }
    next_uid = 1

    def _add(plc: Dict[str, Any], player: int, mirror: bool) -> int:
        nonlocal next_uid
        unit_name = str(plc.get("unit", "TURRET")).upper()
        type_idx = _UNIT_NAME_TO_IDX.get(unit_name, 2)
        loc = plc.get("loc") or [0, 0]
        x = int(loc[0])
        y = int(loc[1])
        if mirror:
            y = _mirror_y(y)
        upgraded = bool(plc.get("upgrade", False))
        # Honor only priority-1 placements as starting; deeper priorities
        # would require SP we don't track per-turn here.
        hp = _structure_default_hp(type_idx, upgraded, config)
        state["structures"].append({
            "xy": [x, y],
            "type_idx": int(type_idx),
            "upgraded": bool(upgraded),
            "hp": float(hp),
            "uid": str(next_uid),
            "player": int(player),
            "turn_start_removal": False,
        })
        next_uid += 1
        return next_uid

    # Genome-driven priority-floor: density_mult >= 1.0 picks priority
    # 1..3, density_mult < 1.0 picks priority 1 only. This widens the
    # MAP-Elites BC2 axis — the genome's defense-density intent is
    # reflected in the actual on-board structure count.
    my_priority_max = 3 if my_density_mult >= 1.0 else 2
    if my_density_mult >= 1.3:
        my_priority_max = 4
    for plc in _load_defense(my_defense):
        if int(plc.get("priority", 1)) <= my_priority_max:
            _add(plc, player=1, mirror=False)
    for plc in _load_defense(opp_defense):
        if int(plc.get("priority", 1)) <= 3:
            _add(plc, player=2, mirror=True)

    return state


# ---------------------------------------------------------------------------
# Offense template -> per-turn deploy expansion
# ---------------------------------------------------------------------------


_TEMPLATE_CACHE: Optional[Dict[str, OffenseTemplate]] = None


def _all_templates() -> Dict[str, OffenseTemplate]:
    global _TEMPLATE_CACHE
    if _TEMPLATE_CACHE is not None:
        return _TEMPLATE_CACHE
    out: Dict[str, OffenseTemplate] = {}
    for tpl in load_all_templates():
        out[tpl.name] = tpl
    _TEMPLATE_CACHE = out
    return out


def _expand_template(
    template_name: str,
    mp_avail: float,
) -> Tuple[List[Tuple[str, Tuple[int, int]]], float]:
    """Expand a template into a list of (unit_name, (x,y)) tuples
    truncated to the MP budget. Returns (deploys, total_mp_used)."""
    tpls = _all_templates()
    tpl = tpls.get(template_name)
    if tpl is None:
        return [], 0.0
    deploys: List[Tuple[str, Tuple[int, int]]] = []
    used = 0.0
    for grp in tpl.spawns:
        idx = _UNIT_NAME_TO_IDX.get(grp.unit, 3)
        cost = _MP_COST_BY_IDX.get(idx, 1.0)
        for _ in range(grp.count):
            if used + cost > mp_avail + 1e-9:
                return deploys, used
            deploys.append((grp.unit, (int(grp.loc[0]), int(grp.loc[1]))))
            used += cost
    return deploys, used


# ---------------------------------------------------------------------------
# Per-turn action-phase wrapper
# ---------------------------------------------------------------------------


def _apply_deploys(
    state: Dict[str, Any],
    *,
    my_deploys: Sequence[Tuple[str, Tuple[int, int]]],
    opp_deploys: Sequence[Tuple[str, Tuple[int, int]]],
    next_uid_seed: int,
) -> int:
    """Append mobile deploys to state['mobiles']; return updated next_uid."""
    next_uid = int(next_uid_seed)
    state.setdefault("mobiles", [])

    def _add_one(unit_name: str, loc: Tuple[int, int], player: int) -> None:
        nonlocal next_uid
        idx = _UNIT_NAME_TO_IDX.get(unit_name, 3)
        x = int(loc[0])
        y = int(loc[1])
        if player == 2:
            # mirror bottom spawns to top
            y = _mirror_y(y)
            x = 27 - x
        # target_edge per sim_eval convention
        if player == 1:
            if y == 13 - x:
                target_edge = 3  # TR
            elif y == x - 14:
                target_edge = 2  # TL
            else:
                target_edge = 3
        else:
            if y == 14 + x:
                target_edge = 1  # BR
            elif y == 41 - x:
                target_edge = 0
            else:
                target_edge = 0
        state["mobiles"].append({
            "xy": [int(x), int(y)],
            "type_idx": int(idx),
            "hp": float(_DEFAULT_MOBILE_HP.get(idx, 15.0)),
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
        next_uid += 1

    for plan, player, side_key in (
        (my_deploys, 1, "p1"),
        (opp_deploys, 2, "p2"),
    ):
        for unit, loc in plan:
            idx = _UNIT_NAME_TO_IDX.get(str(unit).upper(), 3)
            cost = _MP_COST_BY_IDX.get(idx, 1.0)
            cur = float(state[side_key]["mp"])
            if cur + 1e-9 < cost:
                continue
            state[side_key]["mp"] = round(cur - cost, 1)
            _add_one(str(unit).upper(), loc, player)
    return next_uid


def _next_uid_seed(state: Dict[str, Any]) -> int:
    max_uid = 0
    for u in state.get("structures", []) + state.get("mobiles", []):
        try:
            v = int(u.get("uid", 0))
            if v > max_uid:
                max_uid = v
        except (TypeError, ValueError):
            continue
    return max_uid + 1


# ---------------------------------------------------------------------------
# Single-match driver
# ---------------------------------------------------------------------------


def _simulate_match(
    genome: Genome,
    opp: "BaselineOpponent",
    *,
    config_path: str,
    config_dict: Dict[str, Any],
    n_rounds: int = 8,
) -> Dict[str, Any]:
    """Run a small N-round sim and return a summary dict.

    Returns: {
      'delta_hp_self': float (HP we lost),
      'delta_hp_opp': float (HP they lost),
      'win': 1 if we ended with strictly more HP than them, 0 if loss, 0.5 if tie,
      'mean_mp_at_attack': float (avg MP held at start of attack rounds),
      'defense_density': int (count of structures placed by mid-game),
      'breaches_self_total': int,
      'breaches_opp_total': int,
    }
    """
    sim_rs = _get_sim_rs()
    if sim_rs is None:
        # Degenerate fallback — return neutral result so map-elites
        # still progresses with a rank-by-genome bias from the bcs.
        return {
            "delta_hp_self": 0.0,
            "delta_hp_opp": 0.0,
            "win": 0.5,
            "mean_mp_at_attack": 0.0,
            "defense_density": 0,
            "breaches_self_total": 0,
            "breaches_opp_total": 0,
        }

    state = _build_initial_state(
        my_defense=genome.archetype_name,
        opp_defense=opp.defense_name,
        config=config_dict,
        my_density_mult=float(genome.turret_density_mult),
    )
    n_struct_after_open = sum(
        1 for s in state["structures"] if s["player"] == 1
    )

    mp_at_attack_samples: List[float] = []
    breaches_self = 0
    breaches_opp = 0

    for round_idx in range(int(n_rounds)):
        # Per-turn MP income: 4 + (1 + turn//5) per Citadel rules.
        income = 1.0 + max(1.0, 1.0 + round_idx // 5)
        state["p1"]["mp"] = round(float(state["p1"]["mp"]) * 0.75 + income, 1)
        state["p2"]["mp"] = round(float(state["p2"]["mp"]) * 0.75 + income, 1)

        # Attack-or-hoard decision
        my_mp = float(state["p1"]["mp"])
        opp_mp = float(state["p2"]["mp"])
        # Genome-driven hoard: if MP < threshold * (a small running cap), hold off.
        cap = max(my_mp, 5.0 + 1.0 * round_idx)
        my_attack = my_mp >= genome.mp_hoard_threshold * cap
        my_deploys: List[Tuple[str, Tuple[int, int]]] = []
        if my_attack:
            mp_at_attack_samples.append(my_mp)
            my_deploys, _ = _expand_template(
                genome.template_name, my_mp,
            )
        opp_deploys: List[Tuple[str, Tuple[int, int]]] = []
        if opp_mp >= 1.0:
            opp_deploys, _ = _expand_template(opp.template_name, opp_mp)

        next_uid = _next_uid_seed(state)
        _apply_deploys(state, my_deploys=my_deploys,
                       opp_deploys=opp_deploys, next_uid_seed=next_uid)

        # Run action phase
        try:
            post = sim_rs.simulate_action_phase_py(state, config_path)
        except Exception:  # noqa: BLE001
            # Sim crashed on this state — bail out neutral.
            break

        # Tally breaches before clobbering state
        for m in post.get("mobiles", []):
            if not bool(m.get("breached", False)):
                continue
            if int(m.get("player", 0)) == 1:
                breaches_self += 1
            else:
                breaches_opp += 1
        # Clean dead mobiles from state for the next round (the post
        # already has updated structures and mobiles list)
        state = post
        # Drop mobiles that have breached or have hp <= 0 (engine
        # auto-cleans these between turns)
        state["mobiles"] = [
            m for m in state["mobiles"]
            if not bool(m.get("breached", False))
            and float(m.get("hp", 0.0)) > 0.0
        ]
        state["turn"] = int(state.get("turn", 0)) + 1

    p1 = state["p1"]
    p2 = state["p2"]
    starting = 40.0
    delta_hp_self = float(starting) - float(p1["hp"])
    delta_hp_opp = float(starting) - float(p2["hp"])
    win = 1.0 if p1["hp"] > p2["hp"] else (0.0 if p1["hp"] < p2["hp"] else 0.5)

    return {
        "delta_hp_self": delta_hp_self,
        "delta_hp_opp": delta_hp_opp,
        "win": float(win),
        "mean_mp_at_attack": (
            sum(mp_at_attack_samples) / len(mp_at_attack_samples)
            if mp_at_attack_samples else 0.0
        ),
        "defense_density": int(n_struct_after_open),
        "breaches_self_total": int(breaches_self),
        "breaches_opp_total": int(breaches_opp),
    }


# ---------------------------------------------------------------------------
# Baseline opponents
# ---------------------------------------------------------------------------


class BaselineOpponent:
    """Tiny struct: defense archetype name + offense template name."""

    def __init__(self, name: str, defense_name: str, template_name: str) -> None:
        self.name = name
        self.defense_name = defense_name
        self.template_name = template_name


def default_baselines() -> List[BaselineOpponent]:
    """The two default fitness-eval opponents.

    - **v13_proxy**: dense ring defense + scout_flood (closest sim-only
      proxy for v13_second_ring's behavior).
    - **lostkids_proxy**: v_funnel defense + dual_flank (closest sim-only
      proxy for athena_baseline_lostkids).
    """
    return [
        BaselineOpponent(
            name="v13_proxy",
            defense_name="v13_inspired",
            template_name="scout_flood",
        ),
        BaselineOpponent(
            name="lostkids_proxy",
            defense_name="v_funnel",
            template_name="dual_flank",
        ),
    ]


# ---------------------------------------------------------------------------
# Public API: evaluate one genome
# ---------------------------------------------------------------------------


_CONFIG_CACHE: Optional[Dict[str, Any]] = None
_CONFIG_PATH_CACHE: Optional[str] = None


def _load_config_cached() -> Tuple[str, Dict[str, Any]]:
    global _CONFIG_CACHE, _CONFIG_PATH_CACHE
    if _CONFIG_CACHE is not None and _CONFIG_PATH_CACHE is not None:
        return _CONFIG_PATH_CACHE, _CONFIG_CACHE
    path = _default_config_path()
    with open(path, "r") as fh:
        _CONFIG_CACHE = json.load(fh)
    _CONFIG_PATH_CACHE = path
    return path, _CONFIG_CACHE


def evaluate(
    genome: Genome,
    *,
    baselines: Optional[List[BaselineOpponent]] = None,
    n_matches_per_baseline: int = 3,
    n_rounds: int = 8,
) -> Tuple[float, Tuple[float, float], List[Dict[str, Any]]]:
    """Run sim-evaluated fitness for one genome.

    Returns:
        fitness (float):
            Aggregate score:
                fitness = (mean_win - 0.5) * 10.0
                          + (mean_delta_hp_opp - mean_delta_hp_self)
                          + 0.5 * mean_breaches_opp
                          - 0.5 * mean_breaches_self
        behavior (tuple[float, float]):
            (mean_mp_at_attack, mean_defense_density)
        sim_results (list[dict]):
            Per-match summary, used by callers for diagnostics.

    Notes:
    - We average across (n_matches_per_baseline × len(baselines)) matches.
    - Each match is deterministic in this harness because the sim_rs
      action phase is deterministic given the state. Replicates within
      a baseline are only meaningful when we add genome stochasticity.
      Since the genome is constant per call, n_matches_per_baseline=1
      would suffice; we keep the parameter for future genome-level RNG.
    """
    if baselines is None:
        baselines = default_baselines()

    config_path, config = _load_config_cached()

    sim_results: List[Dict[str, Any]] = []
    for opp in baselines:
        for _ in range(int(n_matches_per_baseline)):
            r = _simulate_match(
                genome, opp,
                config_path=config_path,
                config_dict=config,
                n_rounds=n_rounds,
            )
            r["opponent"] = opp.name
            sim_results.append(r)

    if not sim_results:
        return (0.0, (0.0, 0.0), [])

    n = float(len(sim_results))
    mean_win = sum(r["win"] for r in sim_results) / n
    mean_delta_hp_opp = sum(r["delta_hp_opp"] for r in sim_results) / n
    mean_delta_hp_self = sum(r["delta_hp_self"] for r in sim_results) / n
    mean_breaches_opp = sum(r["breaches_opp_total"] for r in sim_results) / n
    mean_breaches_self = sum(r["breaches_self_total"] for r in sim_results) / n
    mean_mp_atk = sum(r["mean_mp_at_attack"] for r in sim_results) / n
    mean_def_dens = sum(r["defense_density"] for r in sim_results) / n

    fitness = (
        (mean_win - 0.5) * 10.0
        + (mean_delta_hp_opp - mean_delta_hp_self)
        + 0.5 * mean_breaches_opp
        - 0.5 * mean_breaches_self
    )
    return float(fitness), (float(mean_mp_atk), float(mean_def_dens)), sim_results


__all__ = [
    "BaselineOpponent",
    "default_baselines",
    "evaluate",
]
