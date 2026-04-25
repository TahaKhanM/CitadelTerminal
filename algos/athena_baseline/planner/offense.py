"""Offense planner — candidate generation + beam search.

Phase 4 tasks 3 & 4.

Per-turn flow inside ``on_turn``:
  1. Defense planner places structures (Phase 2).
  2. Offense planner (this module) chooses one mobile-spawn plan from
     a beam of candidates, evaluating each against the action
     predictor's top-K opponent responses.
  3. Algo strategy executes the chosen plan via attempt_spawn.

Public API:
  - ``generate_candidates(state_dict, mp_available, sp_available, ...)``
    -> list[Candidate]
  - ``beam_search(state_dict, candidates, posterior, action_predictor,
    config_path, budget_ms=8000)`` -> Candidate
  - ``Candidate`` dataclass (see below).

`state_dict` is the sim_rs-schema dict (see offense.sim_eval.py:
  py_state_to_dict). All numerics from runtime config; spawn-edge
masking respects walls already present on the bottom edges.
"""
from __future__ import annotations

import time
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Callable, Dict, Iterable, List, Optional, Sequence, Tuple

# Dual-mode imports: relative for test-time `algos.athena_v3_planner.*`
# package layout, top-level for runtime where `_HERE` (the algo dir) is
# on sys.path and `offense` / `planner` are top-level packages.
try:
    from ..offense.sim_eval import (
        UNIT_NAME_TO_IDX,
        evaluate_action_phase,
        py_state_to_dict,
    )
    from ..offense.templates import (
        OffenseTemplate,
        SPAWN_EDGE_TILES,
        SpawnGroup,
        load_all_templates,
    )
except ImportError:
    from offense.sim_eval import (  # type: ignore
        UNIT_NAME_TO_IDX,
        evaluate_action_phase,
        py_state_to_dict,
    )
    from offense.templates import (  # type: ignore
        OffenseTemplate,
        SPAWN_EDGE_TILES,
        SpawnGroup,
        load_all_templates,
    )


# ---------------------------------------------------------------------------
# Utility weights — tuned later by Phase 9 MAP-Elites
# ---------------------------------------------------------------------------

ALPHA_HP_TAKEN = 1.0    # weight on HP we lose
BETA_MP_SPENT = 0.5     # weight on MP we spend
GAMMA_STRUCT_KILLED = 0.2  # weight on opponent structures destroyed
DELTA_SP_GAINED = 0.3   # weight on SP we gain (from breaches)


# ---------------------------------------------------------------------------
# Data classes
# ---------------------------------------------------------------------------

@dataclass
class Candidate:
    """A concrete spawn plan ready to be executed via attempt_spawn.

    ``deploys`` is a list of ``(unit_name, [x, y])`` tuples — one entry
    per single mobile unit (NOT per group).
    """
    name: str                                      # human label
    template: Optional[str]                        # source template name (None for "hoard")
    side: str
    deploys: List[Tuple[str, Tuple[int, int]]]
    mp_cost: float
    expected_utility: float = 0.0
    sim_count: int = 0  # how many opponent responses were sim'd
    debug: Dict[str, Any] = field(default_factory=dict)


# ---------------------------------------------------------------------------
# Candidate generation
# ---------------------------------------------------------------------------

# MP cost per mobile unit by name (Citadel runtime config defaults; should be
# overridden if caller passes explicit mp_costs).
_DEFAULT_MOBILE_MP = {"SCOUT": 1.0, "DEMOLISHER": 3.0, "INTERCEPTOR": 1.0}


def _expand_template_to_deploys(
    template: OffenseTemplate,
    mp_costs: Dict[str, float],
) -> Tuple[List[Tuple[str, Tuple[int, int]]], float]:
    """Expand each SpawnGroup into per-unit deploys; return (deploys, total_mp)."""
    deploys: List[Tuple[str, Tuple[int, int]]] = []
    total = 0.0
    for grp in template.spawns:
        cost = mp_costs.get(grp.unit, _DEFAULT_MOBILE_MP.get(grp.unit, 1.0))
        for _ in range(grp.count):
            deploys.append((grp.unit, (int(grp.loc[0]), int(grp.loc[1]))))
            total += cost
    return deploys, total


def _truncate_to_mp(
    deploys: List[Tuple[str, Tuple[int, int]]],
    mp_costs: Dict[str, float],
    mp_cap: float,
) -> Tuple[List[Tuple[str, Tuple[int, int]]], float]:
    """Take a prefix of `deploys` whose total cost <= mp_cap."""
    out: List[Tuple[str, Tuple[int, int]]] = []
    used = 0.0
    for unit, loc in deploys:
        cost = mp_costs.get(unit, _DEFAULT_MOBILE_MP.get(unit, 1.0))
        if used + cost > mp_cap + 1e-9:
            continue
        out.append((unit, loc))
        used += cost
    return out, used


def _spawn_locs_blocked(
    state_dict: Dict[str, Any],
    deploys: List[Tuple[str, Tuple[int, int]]],
    my_player: int = 1,
) -> bool:
    """Return True if any deploy tile is occupied by a friendly structure.

    The engine silently drops mobile spawns on a tile occupied by any
    structure (own or opponent's). This is a fast feasibility check —
    it doesn't check whether the *path* from the spawn tile is blocked.
    """
    occupied = set()
    for s in state_dict.get("structures", []):
        try:
            occupied.add((int(s["xy"][0]), int(s["xy"][1])))
        except (KeyError, IndexError, TypeError):
            continue
    for _, loc in deploys:
        if (int(loc[0]), int(loc[1])) in occupied:
            return True
    return False


# Athena v5: per-side spawn-edge tile lists (in-frame for player 1).
# Used by candidate expansion to sweep alternate spawn tiles for each
# template instead of using only the hard-coded canonical tile.
_BL_EDGE_TILES_FROM_FRONT: Tuple[Tuple[int, int], ...] = (
    (3, 10), (4, 9), (5, 8), (2, 11), (6, 7), (1, 12),
    (7, 6), (8, 5), (0, 13), (9, 4), (10, 3), (11, 2),
    (12, 1), (13, 0),
)
_BR_EDGE_TILES_FROM_FRONT: Tuple[Tuple[int, int], ...] = (
    (24, 10), (23, 9), (22, 8), (25, 11), (21, 7), (26, 12),
    (20, 6), (19, 5), (27, 13), (18, 4), (17, 3), (16, 2),
    (15, 1), (14, 0),
)


def _expand_template_with_tile_sweep(
    tpl: OffenseTemplate,
    mp_costs: Dict[str, float],
    state_dict: Dict[str, Any],
    mp_available: float,
    my_player: int = 1,
    max_tile_variants: int = 14,         # athena_baseline: full edge sweep
) -> List[Tuple[List[Tuple[str, Tuple[int, int]]], float, Tuple[int, int]]]:
    """Generate spawn-tile variants of a template — Athena v5 candidate explosion.

    For each open spawn-edge tile on the template's chosen side, produce
    a variant where the template's first spawn group is rebound to that
    tile. Returns list of (deploys, total_mp_cost, anchor_tile) tuples.

    For BOTH-side templates (interceptor_screen, dual_flank), the BL
    tile of any (BL_tile, BR_tile) pair gets swept; the BR tile follows
    from the canonical relationship (mirror across x=13.5).

    This gets us from "1 spawn location per template" to "up to 8 per
    template" — 8x the search resolution before unit-mix variation.
    """
    occupied = set()
    for s in state_dict.get("structures", []):
        try:
            occupied.add((int(s["xy"][0]), int(s["xy"][1])))
        except (KeyError, IndexError, TypeError):
            continue

    side = (tpl.side or "BL").upper()
    if side == "BL":
        edge = _BL_EDGE_TILES_FROM_FRONT
    elif side == "BR":
        edge = _BR_EDGE_TILES_FROM_FRONT
    elif side in ("CENTER", "BOTH"):
        # Center / both — sweep across BR primary; less critical for
        # these templates because they're already multi-tile by design.
        edge = _BR_EDGE_TILES_FROM_FRONT
    else:
        edge = _BL_EDGE_TILES_FROM_FRONT

    # Filter to open tiles, take the top N (by template's "front-of-edge"
    # priority — closer to opponent first).
    open_tiles = [t for t in edge if t not in occupied][:max_tile_variants]

    out: List[Tuple[List[Tuple[str, Tuple[int, int]]], float, Tuple[int, int]]] = []

    # Get the canonical first-spawn-group anchor as the reference tile.
    if not tpl.spawns:
        return out
    canonical_first_tile = (int(tpl.spawns[0].loc[0]), int(tpl.spawns[0].loc[1]))

    for new_tile in open_tiles:
        # Build deploys with the first spawn group rebound to new_tile.
        # Subsequent groups keep their own offsets (shift by delta).
        dx = new_tile[0] - canonical_first_tile[0]
        dy = new_tile[1] - canonical_first_tile[1]
        deploys: List[Tuple[str, Tuple[int, int]]] = []
        total = 0.0
        valid = True
        for grp in tpl.spawns:
            cost = mp_costs.get(grp.unit, _DEFAULT_MOBILE_MP.get(grp.unit, 1.0))
            shifted_loc = (
                int(grp.loc[0]) + dx,
                int(grp.loc[1]) + dy,
            )
            # Drop variant if shifted location is occupied or out of
            # spawn edges — don't pursue invalid plans.
            if shifted_loc in occupied:
                valid = False
                break
            for _ in range(grp.count):
                deploys.append((grp.unit, shifted_loc))
                total += cost
        if not valid:
            continue

        # MP-fit truncate.
        if total > mp_available + 1e-9:
            deploys, total = _truncate_to_mp(deploys, mp_costs, mp_available)
            if not deploys:
                continue

        out.append((deploys, total, new_tile))

    return out


def _expand_template_with_unit_mix(
    tpl: OffenseTemplate,
    base_deploys: List[Tuple[str, Tuple[int, int]]],
    base_total: float,
    mp_costs: Dict[str, float],
    mp_available: float,
) -> List[Tuple[List[Tuple[str, Tuple[int, int]]], float, str]]:
    """Generate unit-mix variants — Athena v5 candidate explosion.

    Given a base deploys list, generate alternate compositions:
    - (1.0x demos, 1.0x scouts, 1.0x intercs) ← base
    - heavier demos
    - heavier scouts
    - mixed shift toward scouts
    - mixed shift toward demos

    Each variant must respect mp_available. Returns list of
    (deploys, total_cost, label) tuples.
    """
    out: List[Tuple[List[Tuple[str, Tuple[int, int]]], float, str]] = []
    out.append((list(base_deploys), base_total, "base"))

    # Group base deploys by tile to know how many of each unit go where.
    by_tile_unit: Dict[Tuple[Tuple[int, int], str], int] = {}
    for unit, loc in base_deploys:
        by_tile_unit[(loc, unit)] = by_tile_unit.get((loc, unit), 0) + 1

    # Variant: replace some Scouts with Demolishers (or vice versa).
    # Only worthwhile if base has multiple of one type.
    n_scouts = sum(c for (_, u), c in by_tile_unit.items() if u == "SCOUT")
    n_demos = sum(c for (_, u), c in by_tile_unit.items() if u == "DEMOLISHER")
    n_intercs = sum(c for (_, u), c in by_tile_unit.items() if u == "INTERCEPTOR")

    scout_cost = mp_costs.get("SCOUT", 1.0)
    demo_cost = mp_costs.get("DEMOLISHER", 3.0)
    intercept_cost = mp_costs.get("INTERCEPTOR", 1.0)

    # Variant A: convert 3 Scouts → 1 Demolisher (saves nothing, changes mix)
    if n_scouts >= 3:
        # Pick anchor tile (most-Scout-heavy)
        anchor = None
        for (loc, unit), n in by_tile_unit.items():
            if unit == "SCOUT" and n >= 3:
                anchor = loc
                break
        if anchor is not None:
            new = []
            removed = 0
            added_demo = False
            for unit, loc in base_deploys:
                if unit == "SCOUT" and loc == anchor and removed < 3:
                    removed += 1
                    if not added_demo:
                        new.append(("DEMOLISHER", loc))
                        added_demo = True
                    continue
                new.append((unit, loc))
            new_total = base_total - 3 * scout_cost + demo_cost
            if new_total <= mp_available + 1e-9:
                out.append((new, new_total, "+1demo_-3scout"))

    # Variant B: convert 1 Demolisher → 3 Scouts (cheaper, less alpha-strike)
    if n_demos >= 1:
        anchor_demo = None
        for (loc, unit), n in by_tile_unit.items():
            if unit == "DEMOLISHER":
                anchor_demo = loc
                break
        if anchor_demo is not None:
            new = []
            removed = False
            extra_added = 0
            for unit, loc in base_deploys:
                if unit == "DEMOLISHER" and loc == anchor_demo and not removed:
                    removed = True
                    extra_added = 3
                    continue
                new.append((unit, loc))
            for _ in range(extra_added):
                new.append(("SCOUT", anchor_demo))
            new_total = base_total - demo_cost + 3 * scout_cost
            if new_total <= mp_available + 1e-9:
                out.append((new, new_total, "+3scout_-1demo"))

    # athena_baseline: more variants for max sim_rs utilization.

    # Variant C: heavy demo — convert 6 Scouts → 2 Demolishers
    if n_scouts >= 6:
        anchor = None
        for (loc, unit), n in by_tile_unit.items():
            if unit == "SCOUT" and n >= 6:
                anchor = loc
                break
        if anchor is not None:
            new = []
            removed = 0
            added = 0
            for unit, loc in base_deploys:
                if unit == "SCOUT" and loc == anchor and removed < 6:
                    removed += 1
                    if added < 2:
                        new.append(("DEMOLISHER", loc))
                        added += 1
                    continue
                new.append((unit, loc))
            new_total = base_total - 6 * scout_cost + 2 * demo_cost
            if new_total <= mp_available + 1e-9:
                out.append((new, new_total, "+2demo_-6scout"))

    # Variant D: pure scout — replace ALL Demolishers with Scouts at same anchor
    if n_demos >= 1:
        new = []
        added_scout: Dict[Tuple[int, int], int] = {}
        for unit, loc in base_deploys:
            if unit == "DEMOLISHER":
                added_scout[loc] = added_scout.get(loc, 0) + 3
                continue
            new.append((unit, loc))
        for loc, n in added_scout.items():
            for _ in range(n):
                new.append(("SCOUT", loc))
        new_total = base_total - n_demos * demo_cost + (3 * n_demos) * scout_cost
        if new_total <= mp_available + 1e-9:
            out.append((new, new_total, "pure_scout"))

    # Variant E: pure demo — replace 6 Scouts with 2 Demolishers (if possible)
    # Skipped if not enough scouts; covered by Variant C.

    # Variant F: add interceptor screen — if base has no interceptors and
    # has 2+ MP slack, add a 2-interceptor escort at the same anchor as
    # the first scout group.
    if n_intercs == 0 and base_total + 2 <= mp_available + 1e-9:
        anchor = None
        for (loc, unit), n in by_tile_unit.items():
            if unit in ("SCOUT", "DEMOLISHER"):
                anchor = loc
                break
        if anchor is not None:
            new = list(base_deploys) + [("INTERCEPTOR", anchor), ("INTERCEPTOR", anchor)]
            new_total = base_total + 2 * intercept_cost
            if new_total <= mp_available + 1e-9:
                out.append((new, new_total, "+2interceptor_escort"))

    return out


def generate_candidates(
    state_dict: Dict[str, Any],
    mp_available: float,
    *,
    posterior: Optional[Dict[str, float]] = None,
    templates: Optional[Sequence[OffenseTemplate]] = None,
    mp_costs: Optional[Dict[str, float]] = None,
    my_player: int = 1,
    max_candidates: int = 2000,           # athena_baseline: 500 → 2000 (max throttle)
    archive=None,                          # MAPElitesArchive | None
    archive_sample_k: int = 10,
    archive_rng=None,                      # random.Random | None
    expand_variants: bool = True,         # Athena v5: per-tile + unit-mix sweep
) -> List[Candidate]:
    """Enumerate ~30-100 offense candidates feasible at the current state.

    Always includes a "hoard" candidate (spawn nothing) at the head.
    Templates that don't fit the MP budget are skipped, except: any
    template whose ``min_mp <= mp_available`` is included (truncated to
    fit the actual budget if needed).

    Phase 6 milestone K: when ``archive`` is a MAPElitesArchive, sample
    up to ``archive_sample_k`` cells and translate each genome into a
    "virtual" offense candidate. These get evaluated alongside the JSON
    templates so beam search can pick the best of both.
    """
    if templates is None:
        templates = load_all_templates()
    if mp_costs is None:
        mp_costs = dict(_DEFAULT_MOBILE_MP)

    out: List[Candidate] = []

    # Always include hoard.
    out.append(Candidate(
        name="hoard",
        template=None,
        side="NONE",
        deploys=[],
        mp_cost=0.0,
        debug={"reason": "save MP for next turn"},
    ))

    # Build a name->template index so archive-derived candidates can
    # rebind to the corresponding template in O(1).
    tpl_by_name: Dict[str, OffenseTemplate] = {t.name: t for t in templates}

    seen_signatures: set = set()  # de-dup by (template_name, mp_cost) bucket

    def _push(cand: Candidate, source: str) -> None:
        sig = (cand.template, round(cand.mp_cost, 1), source)
        if sig in seen_signatures:
            return
        seen_signatures.add(sig)
        out.append(cand)

    # ------- 1. Stock JSON templates + Athena v5 variant expansion -------
    for tpl in templates:
        if tpl.min_mp > mp_available + 1e-9:
            continue  # not enough MP for this template's minimum
        deploys, total = _expand_template_to_deploys(tpl, mp_costs)

        # MP-fit: truncate to budget if oversized
        if total > mp_available + 1e-9:
            deploys, total = _truncate_to_mp(deploys, mp_costs, mp_available)
            if not deploys:
                continue

        # Spawn-tile blocking check (drops obviously infeasible candidates)
        canonical_blocked = _spawn_locs_blocked(state_dict, deploys, my_player=my_player)
        if not canonical_blocked:
            _push(Candidate(
                name=tpl.name,
                template=tpl.name,
                side=tpl.side,
                deploys=deploys,
                mp_cost=total,
            ), source="template")

            # Athena v5: per-template UNIT-MIX variants on the canonical
            # spawn — generates "+1demo_-3scout" etc. variants.
            if expand_variants:
                for vd, vt, vlabel in _expand_template_with_unit_mix(
                    tpl, deploys, total, mp_costs, mp_available,
                ):
                    if vlabel == "base":  # already pushed above
                        continue
                    if _spawn_locs_blocked(state_dict, vd, my_player=my_player):
                        continue
                    _push(Candidate(
                        name=f"{tpl.name}__{vlabel}",
                        template=tpl.name,
                        side=tpl.side,
                        deploys=vd,
                        mp_cost=vt,
                    ), source=f"variant:{vlabel}")

        # Athena v5: SPAWN-TILE SWEEP — even if canonical was blocked
        # (or to find better tiles than canonical), enumerate alternate
        # spawn-edge tiles. This is the biggest single multiplier.
        # athena_baseline: ALSO apply unit-mix variants to each tile
        # variant — composes for ~14 tiles × 4 mixes = ~56 variants per
        # template, total ~900 candidates pre-prune.
        if expand_variants:
            for vd, vt, vtile in _expand_template_with_tile_sweep(
                tpl, mp_costs, state_dict, mp_available, my_player=my_player,
            ):
                # Skip if this is the canonical tile (already pushed).
                if not canonical_blocked and len(vd) == len(deploys):
                    if vd and deploys and vd[0][1] == deploys[0][1]:
                        continue  # same anchor — no variant
                _push(Candidate(
                    name=f"{tpl.name}@{vtile}",
                    template=tpl.name,
                    side=tpl.side,
                    deploys=vd,
                    mp_cost=vt,
                ), source=f"tile_sweep:{vtile}")

                # Compose: unit-mix variants on each tile-sweep variant.
                for vmd, vmt, vmlabel in _expand_template_with_unit_mix(
                    tpl, vd, vt, mp_costs, mp_available,
                ):
                    if vmlabel == "base":
                        continue  # already pushed above
                    if _spawn_locs_blocked(state_dict, vmd, my_player=my_player):
                        continue
                    _push(Candidate(
                        name=f"{tpl.name}@{vtile}__{vmlabel}",
                        template=tpl.name,
                        side=tpl.side,
                        deploys=vmd,
                        mp_cost=vmt,
                    ), source=f"tile_sweep+mix:{vtile}/{vmlabel}")

    # ------- 2. Archive-derived candidates (Phase 6 milestone K) -------
    if archive is not None and archive_sample_k > 0:
        try:
            sampled = archive.sample(archive_sample_k, rng=archive_rng)
        except Exception:  # noqa: BLE001
            sampled = []
        for genome in sampled:
            try:
                tpl_name = genome.template_name
            except Exception:  # noqa: BLE001
                continue
            tpl = tpl_by_name.get(tpl_name)
            if tpl is None:
                continue
            if tpl.min_mp > mp_available + 1e-9:
                continue
            deploys, total = _expand_template_to_deploys(tpl, mp_costs)
            if total > mp_available + 1e-9:
                deploys, total = _truncate_to_mp(
                    deploys, mp_costs, mp_available,
                )
                if not deploys:
                    continue
            if _spawn_locs_blocked(state_dict, deploys, my_player=my_player):
                continue
            # Tag archive-derived candidates with name "archive:<tpl>"
            # so smoke tests + logs can verify integration.
            _push(Candidate(
                name=f"archive:{tpl.name}",
                template=tpl.name,
                side=tpl.side,
                deploys=deploys,
                mp_cost=total,
                debug={"source": "map_elites_archive",
                       "genome": genome.to_dict()},
            ), source="archive")

    # Heuristic prune to max_candidates if we somehow exceed it
    if len(out) > max_candidates:
        # Score by (MP utilization * 1.0 + side-prior alignment) — preserve hoard
        keep = [out[0]]
        rest = sorted(out[1:], key=lambda c: -c.mp_cost / max(mp_available, 1.0))
        keep.extend(rest[: max_candidates - 1])
        out = keep
    return out


# ---------------------------------------------------------------------------
# Opponent action -> opp_deploys conversion
# ---------------------------------------------------------------------------

# Map archetype-action's coarse fields back to a concrete deploy list.
# This is a best-effort heuristic — the action predictor's "action" is
# (primary_mobile_type, primary_edge, wave_size_bucket, spend_mp). For
# beam search we just need a reasonable concrete spawn so sim_rs has
# something to attack. The opponent has bottom convention swapped:
# from p2's perspective, BL/BR are "their" bottom, but from our
# perspective, opp spawns at TL/TR (y=27-x and y=14+x respectively).

_BUCKET_TO_COUNT = {"0": 0, "1-3": 2, "4-7": 5, "8-14": 10, "15+": 16}


def _opp_spawn_from_action(action: Dict[str, Any]) -> List[Tuple[str, Tuple[int, int]]]:
    """Translate a coarse predicted action into an opp_deploys list.

    Picks a representative tile per primary_edge:
      TL -> (3, 17)   (mirrored from BL spawn)
      TR -> (24, 17)
      BL/BR/NONE -> we treat as no-op or central spawn.
    """
    ut = int(action.get("primary_mobile_type", -1))
    if ut < 0 or not action.get("spend_mp", False):
        return []
    bucket = str(action.get("wave_size_bucket", "0"))
    n = _BUCKET_TO_COUNT.get(bucket, 0)
    if n == 0:
        return []

    # Map type idx -> name
    name = {3: "SCOUT", 4: "DEMOLISHER", 5: "INTERCEPTOR"}.get(ut)
    if name is None:
        return []

    edge = str(action.get("primary_edge", "NONE"))
    if edge == "TL":
        loc = (3, 17)
    elif edge == "TR":
        loc = (24, 17)
    elif edge == "BL":
        # opp playing from BL would be us — interpret as TL fallback
        loc = (3, 17)
    elif edge == "BR":
        loc = (24, 17)
    else:
        return []

    return [(name, loc) for _ in range(n)]


# ---------------------------------------------------------------------------
# Utility & beam search
# ---------------------------------------------------------------------------

def _utility(eval_result: Dict[str, Any], mp_cost: float) -> float:
    """Per-spec utility:
       delta_HP_dealt - α * delta_HP_taken - β * MP_spent + γ * structures_destroyed
       + δ * SP_gained
    """
    hp_dealt = float(eval_result.get("delta_hp_opp", 0.0))
    hp_taken = float(eval_result.get("delta_hp_self", 0.0))
    structs_killed = int(eval_result.get("structures_destroyed_opp", 0))
    sp_gained = float(eval_result.get("delta_sp_self", 0.0))
    return (
        hp_dealt
        - ALPHA_HP_TAKEN * hp_taken
        - BETA_MP_SPENT * float(mp_cost)
        + GAMMA_STRUCT_KILLED * structs_killed
        + DELTA_SP_GAINED * sp_gained
    )


def _heuristic_utility(cand: Candidate) -> float:
    """No-rollout utility — used when sim_rs is unavailable or the state
    dict isn't fully sim-typed.

    Score: max-MP-utilization with a small bias toward Demolisher-mix
    (better expected damage per MP). This is a stand-in until Phase 5
    wires the full gamelib<->sim_rs adapter.
    """
    if not cand.deploys:
        return 0.0
    n_demo = sum(1 for u, _ in cand.deploys if u == "DEMOLISHER")
    return cand.mp_cost + 0.5 * n_demo


def beam_search(
    state_dict: Dict[str, Any],
    candidates: Sequence[Candidate],
    *,
    posterior: Optional[Dict[str, float]] = None,
    opp_actions_top_k: Optional[List[Tuple[Dict[str, Any], float]]] = None,
    my_player: int = 1,
    config_path: Optional[str] = None,
    budget_ms: float = 8000.0,
    candidate_prune_to: int = 1000,       # athena_baseline: 250 → 1000 (max beam)
    sim_evaluator: Optional[Callable[..., Dict[str, Any]]] = None,
    skip_sim: bool = False,
) -> Candidate:
    """Score every candidate against opp_actions_top_k; return the best.

    Aggregation: expected utility = Σ_action P(action) * utility(sim_result).
    P(action) is the predictor probability (treat as weight; we
    renormalize to sum=1 before mixing).

    Budget management: tracks elapsed wall clock; if we exceed
    ``budget_ms``, returns the best-so-far. Always returns a non-empty
    Candidate (hoard always exists).

    candidate_prune_to: if len(candidates) > candidate_prune_to, prune
    by a fast static heuristic before full sim — preserves the "hoard"
    sentinel.

    skip_sim: if True OR if opp_actions_top_k is empty, score by
    ``_heuristic_utility`` — no sim_rs calls. Used by the offense-only
    Phase 4 variant where the gamelib->sim_rs adapter isn't fully wired.
    """
    if not candidates:
        # Defensive: synthesize a hoard candidate
        return Candidate(name="hoard_default", template=None, side="NONE",
                         deploys=[], mp_cost=0.0)

    if sim_evaluator is None:
        sim_evaluator = evaluate_action_phase

    # When neither sim_rs (Rust) NOR vendored pysim is available, the
    # sim_eval._python_fallback_sim returns identity → every spawn
    # candidate scores -BETA*mp_cost → hoard always wins. We force
    # skip_sim=True in that case so heuristic_utility takes over and at
    # least picks a non-hoard candidate. When pysim IS available
    # (vendored into algos/athena_v3_planner/vendored_sim/) we leave
    # skip_sim=False so we get REAL sim-evaluated deltas — Rust path
    # runs at ~14.6 K sims/s, Python path at ~376 sims/s; both are
    # bit-exact with engine.jar (cross_validate.py: 13319 frames
    # byte-identical Python ↔ Rust). The Python path is slower but
    # produces the same strategic-quality output.
    if not skip_sim:
        try:
            from offense.sim_eval import sim_rs_available  # type: ignore
        except ImportError:
            from ..offense.sim_eval import sim_rs_available  # type: ignore
        if not sim_rs_available():
            # Probe pysim availability — keep sim mode if pysim works.
            try:
                try:
                    from .. import vendored_sim  # type: ignore
                    from ..vendored_sim import pysim as _vsim_probe  # type: ignore  # noqa: F401
                except ImportError:
                    import vendored_sim  # type: ignore  # noqa: F401
                    from vendored_sim import pysim as _vsim_probe  # type: ignore  # noqa: F401
                # Pysim is importable — sim path is viable via the
                # _python_fallback_sim → pysim.simulate_action_phase chain.
            except Exception:  # noqa: BLE001
                # Neither Rust nor Python sim available — heuristic only.
                skip_sim = True

    t0 = time.perf_counter()

    # 1. Static prune (fast scoring) if too many candidates
    if len(candidates) > candidate_prune_to:
        # Heuristic: prefer high-MP candidates (more pressure) plus the
        # hoard sentinel. Diversity over similar templates is implicit
        # because templates have distinct (side, unit-mix) signatures.
        scored = [
            (c, c.mp_cost + (0.5 if c.template is None else 0.0))
            for c in candidates
        ]
        scored.sort(key=lambda kv: -kv[1])
        candidates = [c for c, _ in scored[:candidate_prune_to]]

    # 2. Normalize opp_actions_top_k weights
    opp_actions = list(opp_actions_top_k or [])
    if opp_actions:
        ws = sum(float(p) for _, p in opp_actions)
        if ws > 0:
            opp_actions = [(a, float(p) / ws) for a, p in opp_actions]

    # 2.5. No-rollout fast path
    if skip_sim or not opp_actions:
        best: Optional[Candidate] = None
        best_util = -float("inf")
        for cand in candidates:
            u = _heuristic_utility(cand)
            cand.expected_utility = u
            cand.sim_count = 0
            if u > best_util:
                best_util = u
                best = cand
        return best if best is not None else candidates[0]

    best: Optional[Candidate] = None
    best_util = -float("inf")

    for cand in candidates:
        # Budget check
        elapsed_ms = (time.perf_counter() - t0) * 1000.0
        if elapsed_ms > budget_ms:
            break

        weighted_util = 0.0
        sims = 0
        for action, p in opp_actions:
            # Inner budget check between sims
            if (time.perf_counter() - t0) * 1000.0 > budget_ms:
                break
            opp_deploys = _opp_spawn_from_action(action)
            res = sim_evaluator(
                state_dict,
                cand.deploys,
                opp_deploys,
                my_player=my_player,
                config_path=config_path,
            )
            u = _utility(res, cand.mp_cost)
            weighted_util += p * u
            sims += 1

        cand.expected_utility = weighted_util
        cand.sim_count = sims
        if weighted_util > best_util:
            best_util = weighted_util
            best = cand

    return best if best is not None else candidates[0]


# ---------------------------------------------------------------------------
# High-level convenience: pick a plan in one call
# ---------------------------------------------------------------------------

def pick_offense_plan(
    state_dict: Dict[str, Any],
    *,
    mp_available: float,
    posterior: Optional[Dict[str, float]] = None,
    action_predictor=None,           # optional ActionPredictor instance
    turn: int = 0,
    opp_mp_estimate: float = 0.0,
    my_player: int = 1,
    budget_ms: float = 8000.0,
    templates: Optional[Sequence[OffenseTemplate]] = None,
) -> Candidate:
    """Top-level helper: candidates + opponent prediction + beam_search.

    Falls back to (a) uniform posterior if none supplied, (b)
    do-nothing opponent action if no predictor supplied.
    """
    cands = generate_candidates(
        state_dict, mp_available,
        posterior=posterior,
        templates=templates,
        my_player=my_player,
    )
    opp_actions: List[Tuple[Dict[str, Any], float]] = []
    if action_predictor is not None and posterior is not None:
        try:
            opp_actions = action_predictor.top_k(
                {"mp": opp_mp_estimate, "turn": turn},
                posterior,
                k=5,
            )
        except Exception:
            opp_actions = []
    return beam_search(
        state_dict, cands,
        posterior=posterior,
        opp_actions_top_k=opp_actions,
        my_player=my_player,
        budget_ms=budget_ms,
    )


__all__ = [
    "ALPHA_HP_TAKEN",
    "BETA_MP_SPENT",
    "GAMMA_STRUCT_KILLED",
    "DELTA_SP_GAINED",
    "Candidate",
    "generate_candidates",
    "beam_search",
    "pick_offense_plan",
]
