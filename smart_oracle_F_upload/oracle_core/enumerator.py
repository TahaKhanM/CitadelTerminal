"""Plan enumerator for oracle_pure.

Generates candidate ActionPlans for the current turn. Goal: produce
≥500 distinct, feasible plans on a typical mid-game turn so the
search has real choices to evaluate. Plans cover:

  Defense atoms:
    - corner walls / corner turrets
    - center turret anchors at y=11
    - wall row at y=12 (with launch gaps)
    - sidelane turrets at y=9
    - diagonal reinforcement turrets at y=11 outside the anchors
    - support back-row (base + upgraded)
    - second-ring turrets
    - upgrade-only variants (upgrade existing damaged structures)
    - refund-and-replace (remove damaged structure, place replacement)
    - reactive patches (turret near a recent breach tile)
    - empty (do nothing on defense)

  Offense atoms:
    - scout rushes from many launchers × multiple batch sizes
    - demolisher rushes
    - interceptor screens (anti-rush)
    - mixed waves (1 demo + N scouts; N supports + scouts)
    - "hold all MP" (zero-cost, opportunity-cost comparison)

The enumerator does NOT decide; it only PROPOSES. Every proposal is
gated by tile-legality + budget feasibility BEFORE being returned.

NB: the v13 skeleton is included as ONE template among many. The
search picks it only if sim_rs evaluation says it's actually best
for the current state. Including it as a candidate is required so
the search can find it; hardcoding it as the default would defeat
the purpose.
"""
from __future__ import annotations

from dataclasses import dataclass
from itertools import product
from typing import List, Optional, Sequence, Tuple

from .constants import (
    ARENA_DIM, ARENA_HALF_Y, BOTTOM_LEFT_EDGE, BOTTOM_RIGHT_EDGE,
    DEMOLISHER_IDX, INTERCEPTOR_IDX, MOBILE_IDXS, MP_RES, SCOUT_IDX,
    SP_RES, STRUCTURE_IDXS, SUPPORT_IDX, TURRET_IDX, WALL_IDX,
    cost_for_idx, shorthand_for_idx, starting_hp_for_idx,
)
from .plan import (
    ActionPlan, KIND_REMOVE, KIND_SPAWN_MOBILE, KIND_SPAWN_STRUCT,
    KIND_UPGRADE, Op, attach_costs, compute_costs,
)


# I-S6 — Smart-refund constants (probe-driven trap mitigation)
#
# Trap detection: only generate smart-refund templates when the probe
# breach count is at most TRAP_THRESHOLD. With PROBE_SCOUT_COUNT=5,
# a threshold of 2 means: 0/5, 1/5 or 2/5 breaches => trap; 3+/5 => OK.
# This is conservative — true traps show 0/5 in suchir-g/not-tnb data.
SMART_REFUND_TRAP_THRESHOLD = 2
# Cap how many smart-refund templates to generate (one per top candidate).
SMART_REFUND_MAX_TEMPLATES = 3
# Minimum breach gain to consider a removal worthwhile. Setting this
# to 3 means the structure removal must restore at least 3 of 5 probe
# scouts' ability to breach — a strong signal the structure is genuinely
# trap-causing, not coincidentally correlated with breach.
# This filter prevents refunding a wall whose removal only marginally
# improves probe outcome (whose defensive value would be lost for
# minimal offense gain).
SMART_REFUND_MIN_GAIN = 3


# ===========================================================================
# Defense atoms (each is a list of (unit_idx, x, y) tuples)
# ===========================================================================

# Center anchor turrets — covers center exits
ANCHOR_TURRETS = [(TURRET_IDX, 11, 11), (TURRET_IDX, 13, 11),
                  (TURRET_IDX, 14, 11), (TURRET_IDX, 16, 11)]

# Sidelane turrets — covers (4,9)/(23,9)/(8,5)/(20,6) hotspots from heuristic_v1 data
SIDELANE_TURRETS = [(TURRET_IDX, 7, 9), (TURRET_IDX, 20, 9)]

# Outer wing turrets at y=11
OUTER_TURRETS = [(TURRET_IDX, 5, 11), (TURRET_IDX, 22, 11),
                 (TURRET_IDX, 8, 11), (TURRET_IDX, 19, 11)]

# Diagonal reinforcement turrets (covers diagonal breach tiles)
DIAG_TURRETS = [(TURRET_IDX, 4, 11), (TURRET_IDX, 23, 11)]

# Inner corner turrets
INNER_CORNER_TURRETS = [(TURRET_IDX, 1, 13), (TURRET_IDX, 26, 13)]

# Outer corner turrets
OUTER_CORNER_TURRETS = [(TURRET_IDX, 0, 13), (TURRET_IDX, 27, 13)]

# Wall row at y=12 with launch gaps at x=12 and x=15
WALL_ROW_Y12 = [(WALL_IDX, x, 12) for x in range(2, 26) if x not in (12, 15)]

# Edge walls at y=13 (the corners just inside the spawn edges)
EDGE_WALLS = [(WALL_IDX, 2, 13), (WALL_IDX, 25, 13),
              (WALL_IDX, 4, 13), (WALL_IDX, 23, 13)]

# Back-row support positions (Y=11 / Y=10 — protected by wall row at y=12)
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]

# Second-ring turrets (deep defenders at y=5)
SECOND_RING = [(TURRET_IDX, 11, 5), (TURRET_IDX, 16, 5)]

# Reactive-patch turret tiles near common breach hotspots
REACTIVE_PATCH_TILES_LEFT = [(7, 8), (5, 9), (4, 10), (6, 10), (8, 8), (9, 7)]
REACTIVE_PATCH_TILES_RIGHT = [(20, 8), (22, 9), (23, 10), (21, 10), (19, 8), (18, 7)]

# ---------------------------------------------------------------------------
# Flank-corridor atoms (smart_oracle_F) — only proposed when the funnel
# detector identifies that the opponent is consistently attacking one flank.
# Coordinates derived from FUNNEL_COUNTER_PLAN.md §3 (F1) — they cover the
# breach tiles observed in 8/8 historical funnel losses (left: 3,10/4,9/
# 5,8/7,6/2,11; right: mirrors). Atoms are PRIORITY-ORDERED for greedy
# budget filling.
# ---------------------------------------------------------------------------

LEFT_FLANK_CORRIDOR = [
    (TURRET_IDX, 3, 10),
    (TURRET_IDX, 5, 9),
    (TURRET_IDX, 6, 8),
    (WALL_IDX, 2, 11),
    (WALL_IDX, 3, 11),
    (WALL_IDX, 4, 10),
]
LEFT_FLANK_UPGRADES = [(3, 10), (5, 9), (6, 8)]

RIGHT_FLANK_CORRIDOR = [
    (TURRET_IDX, 24, 10),
    (TURRET_IDX, 22, 9),
    (TURRET_IDX, 21, 8),
    (WALL_IDX, 25, 11),
    (WALL_IDX, 24, 11),
    (WALL_IDX, 23, 10),
]
RIGHT_FLANK_UPGRADES = [(24, 10), (22, 9), (21, 8)]

# Minimal "corner clamp" — cheaper variants for early turns when SP is
# small but the funnel signal is already firing.
LEFT_FLANK_MIN = [
    (TURRET_IDX, 3, 10),
    (TURRET_IDX, 5, 9),
    (WALL_IDX, 2, 11),
]
LEFT_FLANK_MIN_UPGRADES = [(3, 10)]

RIGHT_FLANK_MIN = [
    (TURRET_IDX, 24, 10),
    (TURRET_IDX, 22, 9),
    (WALL_IDX, 25, 11),
]
RIGHT_FLANK_MIN_UPGRADES = [(24, 10)]


# ===========================================================================
# Offense launchers — diverse spawn tiles on bottom edges
# ===========================================================================

LAUNCHERS_CENTER = [(13, 0), (14, 0)]
LAUNCHERS_MID = [(10, 3), (17, 3), (11, 2), (16, 2), (12, 1), (15, 1)]
LAUNCHERS_OUTER = [(3, 10), (24, 10), (4, 9), (23, 9), (5, 8), (22, 8)]
LAUNCHERS_FAR = [(0, 13), (27, 13), (1, 12), (26, 12), (2, 11), (25, 11)]
LAUNCHERS_OFFAXIS = [(6, 7), (21, 7), (7, 6), (20, 6), (8, 5), (19, 5)]

ALL_LAUNCHERS = (LAUNCHERS_CENTER + LAUNCHERS_MID + LAUNCHERS_OUTER +
                 LAUNCHERS_FAR + LAUNCHERS_OFFAXIS)


# ===========================================================================
# Defense plan builders
# ===========================================================================

def _is_legal_struct_tile(game_state, x: int, y: int) -> bool:
    if y >= ARENA_HALF_Y or y < 0 or x < 0 or x >= ARENA_DIM:
        return False
    if not game_state.game_map.in_arena_bounds([x, y]):
        return False
    if game_state.contains_stationary_unit([x, y]):
        return False
    return True


def _existing_struct(game_state, x: int, y: int):
    """Return the friendly stationary unit at (x,y) or None."""
    try:
        cell = game_state.game_map[x, y]
    except Exception:
        return None
    if not cell:
        return None
    for u in cell:
        if not getattr(u, "stationary", True):
            continue
        if int(getattr(u, "player_index", 0)) != 0:
            continue
        return u
    return None


def _build_defense_plan(name: str, atoms: Sequence[Tuple[int, int, int]],
                        game_state, config, sp_budget: float,
                        upgrades: Sequence[Tuple[int, int]] = (),
                        archetype: str = "skeleton") -> Optional[ActionPlan]:
    """Build a plan from a sequence of defense atoms, gated by budget."""
    plan = ActionPlan(name=name, archetype=archetype)
    sp_used = 0.0
    placed_xys = set()
    for idx, x, y in atoms:
        if not _is_legal_struct_tile(game_state, x, y):
            continue
        if (x, y) in placed_xys:
            continue
        c = cost_for_idx(config, idx, upgrade=False)
        if sp_used + c > sp_budget + 1e-9:
            continue
        plan.add_struct(idx, x, y)
        placed_xys.add((x, y))
        sp_used += c
    for x, y in upgrades:
        existing = _existing_struct(game_state, x, y)
        if existing is None and (x, y) not in placed_xys:
            continue
        if existing is not None and getattr(existing, "upgraded", False):
            continue
        # Use existing structure's idx if known, else cost = 4 (turret upgrade max)
        if existing is not None:
            sh = str(getattr(existing, "unit_type", "DF"))
            type_idx = {"FF": 0, "EF": 1, "DF": 2,
                        "WALL": 0, "SUPPORT": 1, "TURRET": 2}.get(sh.upper(), 2)
        else:
            type_idx = TURRET_IDX
        c = cost_for_idx(config, type_idx, upgrade=True)
        if sp_used + c > sp_budget + 1e-9:
            continue
        plan.add_upgrade(x, y)
        sp_used += c
    if not plan.ops:
        return None
    plan.sp_cost = sp_used
    plan.mp_cost = 0.0
    return plan


def _gen_refund_smart(game_state, config) -> List[ActionPlan]:
    """Probe-driven refund template generator (I-S6).

    Strategy:
      1. Adapt the live game state to the sim_rs schema dict.
      2. Run the offensive viability probe on the BASE state (no plan
         changes). Measures current breach rate.
      3. If base_breach >= PROBE_SCOUT_COUNT - SMART_REFUND_TRAP_THRESHOLD_OFFSET
         (i.e. trap-free), return empty list — the existing damaged-turret
         refund handles routine maintenance, smart-refund is for traps only.
      4. Otherwise, iterate own structures; for each, clone the base state,
         REMOVE the structure (immediately strip from clone), re-run the
         probe, and measure the breach delta.
      5. Sort candidates by breach gain (descending). Generate up to
         SMART_REFUND_MAX_TEMPLATES refund plans, each with a single
         KIND_REMOVE op targeting one trap-causing structure. Each plan
         scores independently in the search.

    Why per-structure single-target plans (not combined)?
      - The probe measures GAIN PER REMOVAL. Combining removals would
        change other structures' marginal gains (interaction effects).
        One template per top-K candidate keeps each plan principled.
      - The search will combine smart-refund with offense templates
        (cross-product), so the right scout rush still gets the gain.

    Cost: O(num_own_structures) probe calls per turn. Probe ~0.2 ms each;
    typical mid-game has ~20-40 structures → ~4-8 ms per turn. Plus one
    state adapt (~1 ms). Well within budget vs. M1Lite's 394 ms baseline.

    Returns: list of ActionPlan with KIND_REMOVE ops; empty if no trap or
    no useful candidates found. The plans use the standard refund mechanism
    (delayed by one turn at 0.9 × cost, per Citadel engine semantics).
    """
    # Defensive imports — keep this function isolated from sim_eval at
    # module import time so test suites that don't need sim still load.
    try:
        from .state_adapter import adapt_game_state
        from .sim_eval import _bridge_or_pysim, _get_sim_rs, _default_config_path
        from .viability_probe import (
            PROBE_SCOUT_COUNT, _shallow_clone_state, probe_offense_viability,
        )
    except Exception:
        # If sim infrastructure isn't importable (e.g. during partial
        # unit-test imports), silently skip smart-refund.
        return []

    # Step 1: adapt the game state.
    try:
        base_state = adapt_game_state(
            game_state, my_player=1,
            turn=int(getattr(game_state, "turn_number", 0)),
        )
    except Exception:
        return []

    # Build a probe runner that picks sim_rs if available, else pysim.
    config_path = _default_config_path()

    def _probe_runner(state):
        sim_rs = _get_sim_rs()
        if sim_rs is not None:
            return sim_rs.simulate_action_phase_py(state, config_path)
        return _bridge_or_pysim(state, config_path)

    # Step 2: probe the base state with no plan changes.
    empty_plan = ActionPlan(name="probe:base", archetype="probe")
    try:
        base_breach = probe_offense_viability(
            base_state, empty_plan, config, _probe_runner, my_player=1,
        )
    except Exception:
        return []

    # Step 3: trap-free fast path — no smart-refund needed.
    # We only generate smart-refund templates when the base probe
    # detects a meaningful trap (low breach count).
    if base_breach > SMART_REFUND_TRAP_THRESHOLD:
        return []

    # Step 4: trap detected — find structures whose removal restores
    # viability. Iterate own structures, remove each, re-run probe.
    own_structures = [
        s for s in base_state.get("structures", [])
        if int(s.get("player", 0)) == 1
    ]
    if not own_structures:
        return []

    removal_gains: List[Tuple[int, Tuple[int, int], int]] = []  # (gain, xy, type_idx)
    for s in own_structures:
        try:
            xy = (int(s["xy"][0]), int(s["xy"][1]))
        except (KeyError, IndexError, TypeError, ValueError):
            continue
        # Only consider bottom-half tiles (defense-in-place sanity check)
        if xy[1] >= ARENA_HALF_Y or xy[1] < 0:
            continue

        # Clone the base state and STRIP this structure (immediate
        # removal in the test state). This isolates the structure's
        # impact on offensive viability — even though the actual game
        # refund is delayed, the probe must see the GONE state to
        # measure the trap-restoration effect.
        test_state = _shallow_clone_state(base_state)
        test_state["structures"] = [
            s2 for s2 in test_state["structures"]
            if not (
                int(s2.get("player", 0)) == 1
                and tuple(s2.get("xy", (-1, -1))) == xy
            )
        ]

        try:
            new_breach = probe_offense_viability(
                test_state, empty_plan, config, _probe_runner, my_player=1,
            )
        except Exception:
            continue

        gain = new_breach - base_breach
        if gain >= SMART_REFUND_MIN_GAIN:
            type_idx = int(s.get("type_idx", -1))
            removal_gains.append((gain, xy, type_idx))

    if not removal_gains:
        return []

    # Sort by gain descending. Tie-break by structure type:
    #   prefer SUPPORTS first (the §16 trap mechanism — supports at
    #   (12,11)/(15,11) sealing wall-row gaps are the canonical
    #   trap-causers and refunding them costs least defensive value
    #   since their main job is offensive shielding; if they're
    #   trap-prone, they aren't doing it well),
    #   then WALLS (cheap to lose, refund 0.9 SP, may temporarily
    #   open a gap that we'll refill next turn),
    #   then TURRETS last (most defensive value — only refund if
    #   strictly necessary).
    # Within the same type, prefer the structure with higher gain.
    _TYPE_REFUND_PRIORITY = {
        SUPPORT_IDX: 0,  # most preferred to refund
        WALL_IDX: 1,
        TURRET_IDX: 2,   # least preferred
    }

    def _sort_key(t):
        gain, _xy, type_idx = t
        prio = _TYPE_REFUND_PRIORITY.get(int(type_idx), 99)
        return (-gain, prio)

    removal_gains.sort(key=_sort_key)

    # Step 5: generate templates. Each template removes ONE structure;
    # the search will pick the best one given offense pairings.
    smart_plans: List[ActionPlan] = []
    for gain, xy, type_idx in removal_gains[:SMART_REFUND_MAX_TEMPLATES]:
        plan = ActionPlan(
            name=f"defense:refund_smart_{xy[0]}_{xy[1]}",
            archetype="refund_smart",
        )
        plan.add_remove(xy[0], xy[1])
        plan.sp_cost = 0.0
        plan.mp_cost = 0.0
        smart_plans.append(plan)

    return smart_plans


def _enumerate_defense_templates(game_state, config, sp_budget: float,
                                 recent_breaches: Sequence[Tuple[int, int]],
                                 funnel_target: Optional[str] = None,
                                 ) -> List[ActionPlan]:
    """Return ~30-40 distinct defense plans, all feasible at sp_budget.

    `funnel_target` ∈ {None, "left", "right"} — when set, smart_oracle_F
    injects flank-corridor templates focused on that side. Baseline
    templates remain in the candidate pool; the search picks the best via
    sim_rs evaluation. IS6's smart-refund block (further down) is
    unaffected.
    """
    plans: List[ActionPlan] = []
    turn = game_state.turn_number

    # 1. EMPTY (just save SP — useful for early-game banking)
    p = ActionPlan(name="defense:none", archetype="none")
    p.sp_cost = 0.0
    plans.append(p)

    # 2. Defense templates — diverse mixes the search picks among.
    #
    # ATOM ORDERING NOTE: each template is a PRIORITY ORDERED list of
    # atoms. _build_defense_plan greedily places atoms while SP allows.
    # If SP runs out partway through, later atoms go unplaced. Therefore
    # the order REALLY matters, and we offer multiple templates with
    # different priority orderings so the search can find the best fit
    # for current SP.
    #
    # CRITICAL: INNER_CORNER_TURRETS at (1,13) and (26,13) intercept
    # the corner-breach path scouts use to walk around clustered
    # interior defense. They MUST appear early in templates that
    # claim to be "real defense."

    # Heuristic_v1-style ordering: turrets first then walls. Wins
    # because scout rushes are blunted by turrets first; walls fill
    # in after.
    p = _build_defense_plan(
        "defense:t0_full",
        ANCHOR_TURRETS + INNER_CORNER_TURRETS + SIDELANE_TURRETS +
        OUTER_TURRETS + DIAG_TURRETS + WALL_ROW_Y12 + EDGE_WALLS,
        game_state, config, sp_budget, archetype="skeleton_full",
    )
    if p:
        plans.append(p)

    # Anchor + corners (cheap critical-tile coverage; INNER corners
    # are the breach-stop, walls protect from rush)
    p = _build_defense_plan(
        "defense:anchor_corners_walls",
        ANCHOR_TURRETS + INNER_CORNER_TURRETS + WALL_ROW_Y12,
        game_state, config, sp_budget, archetype="anchor_corners",
    )
    if p:
        plans.append(p)

    # Anchor turrets only (early-game minimal: covers center only)
    p = _build_defense_plan(
        "defense:anchor_only",
        ANCHOR_TURRETS, game_state, config, sp_budget, archetype="anchor",
    )
    if p:
        plans.append(p)

    # Walls-first (extend wall row 4-12, no new turrets — for when
    # we already have turrets and want to wall them off)
    for k in (2, 4, 6, 8, 12):
        plan = ActionPlan(name=f"defense:walls{k}", archetype="walls_only")
        sp_used = 0.0
        for idx, x, y in WALL_ROW_Y12:
            if not _is_legal_struct_tile(game_state, x, y):
                continue
            cost = cost_for_idx(config, idx, upgrade=False)
            if sp_used + cost > sp_budget + 1e-9:
                break
            plan.add_struct(idx, x, y)
            sp_used += cost
            if len([op for op in plan.ops if op.kind == KIND_SPAWN_STRUCT]) >= k:
                break
        if plan.ops:
            plan.sp_cost = sp_used
            plan.mp_cost = 0.0
            plans.append(plan)

    # Diag reinforcement (covers (4,9)/(23,9) breach hotspots from
    # heuristic_v1's data analysis — these are TOP breach tiles)
    p = _build_defense_plan(
        "defense:diag_reinforce",
        DIAG_TURRETS + ANCHOR_TURRETS + INNER_CORNER_TURRETS + WALL_ROW_Y12,
        game_state, config, sp_budget, archetype="diag",
    )
    if p:
        plans.append(p)

    # Sidelane + corners (mid-line defense)
    p = _build_defense_plan(
        "defense:side_corners",
        SIDELANE_TURRETS + INNER_CORNER_TURRETS + ANCHOR_TURRETS + WALL_ROW_Y12,
        game_state, config, sp_budget, archetype="side_corners",
    )
    if p:
        plans.append(p)

    # Outer wing turrets first (wider intercept envelope)
    p = _build_defense_plan(
        "defense:outer_wide",
        OUTER_TURRETS + ANCHOR_TURRETS + INNER_CORNER_TURRETS + WALL_ROW_Y12,
        game_state, config, sp_budget, archetype="outer_wide",
    )
    if p:
        plans.append(p)

    # Anchor + outer corners (covers x=0, x=27 corners — the literal
    # breach tiles where scouts hit our edge)
    p = _build_defense_plan(
        "defense:outer_corner_block",
        OUTER_CORNER_TURRETS + INNER_CORNER_TURRETS + ANCHOR_TURRETS + WALL_ROW_Y12,
        game_state, config, sp_budget, archetype="outer_corner_block",
    )
    if p:
        plans.append(p)

    # Anchor + support (offense-amplifier — supports give shield to scouts)
    p = _build_defense_plan(
        "defense:supports",
        SUPPORTS_BACK + ANCHOR_TURRETS,
        game_state, config, sp_budget, archetype="supports",
    )
    if p:
        plans.append(p)

    # 3. Wall-heavy (wall row + corner turrets only)
    p = _build_defense_plan(
        "defense:wall_heavy",
        INNER_CORNER_TURRETS + WALL_ROW_Y12 + EDGE_WALLS,
        game_state, config, sp_budget, archetype="wall_heavy",
    )
    if p:
        plans.append(p)

    # 4. Corner-heavy (anchor corners with edge walls)
    p = _build_defense_plan(
        "defense:corner_heavy",
        OUTER_CORNER_TURRETS + INNER_CORNER_TURRETS + EDGE_WALLS,
        game_state, config, sp_budget, archetype="corner_heavy",
    )
    if p:
        plans.append(p)

    # 5. Side-biased (left-heavy and right-heavy)
    left_atoms = [(TURRET_IDX, 7, 9), (TURRET_IDX, 8, 11), (TURRET_IDX, 4, 11),
                  (TURRET_IDX, 1, 13), (TURRET_IDX, 11, 11), (TURRET_IDX, 13, 11),
                  (WALL_IDX, 2, 13), (WALL_IDX, 4, 13)]
    right_atoms = [(TURRET_IDX, 20, 9), (TURRET_IDX, 19, 11), (TURRET_IDX, 23, 11),
                   (TURRET_IDX, 26, 13), (TURRET_IDX, 16, 11), (TURRET_IDX, 14, 11),
                   (WALL_IDX, 25, 13), (WALL_IDX, 23, 13)]
    p = _build_defense_plan("defense:left_heavy", left_atoms,
                            game_state, config, sp_budget, archetype="side_left")
    if p:
        plans.append(p)
    p = _build_defense_plan("defense:right_heavy", right_atoms,
                            game_state, config, sp_budget, archetype="side_right")
    if p:
        plans.append(p)

    # 6. Support-back-row + minimal defense
    p = _build_defense_plan(
        "defense:supports_only",
        SUPPORTS_BACK,
        game_state, config, sp_budget, archetype="supports",
    )
    if p:
        plans.append(p)

    # 7. Second-ring (deep defenders)
    p = _build_defense_plan(
        "defense:second_ring",
        ANCHOR_TURRETS + SECOND_RING,
        game_state, config, sp_budget, archetype="second_ring",
    )
    if p:
        plans.append(p)

    # 8. Upgrades-only — pick K most-damaged structures, upgrade them.
    upgrade_candidates = []
    for x in range(ARENA_DIM):
        for y in range(ARENA_HALF_Y):
            u = _existing_struct(game_state, x, y)
            if u is None:
                continue
            if getattr(u, "upgraded", False):
                continue
            # Don't upgrade walls below half HP — refund-replace them instead.
            try:
                hp = float(getattr(u, "health", 0))
                maxhp = float(getattr(u, "max_health", 1) or 1)
                hp_frac = hp / maxhp
            except Exception:
                hp_frac = 1.0
            sh = str(getattr(u, "unit_type", "DF")).upper()
            # Upgrades cost: turret 4, support 4, wall 2.
            if sh in ("DF", "TURRET"):
                cost, idx = 4.0, TURRET_IDX
            elif sh in ("EF", "SUPPORT"):
                cost, idx = 4.0, SUPPORT_IDX
            elif sh in ("FF", "WALL"):
                cost, idx = 2.0, WALL_IDX
            else:
                continue
            upgrade_candidates.append((cost, idx, x, y, hp_frac))
    # Sort upgrade candidates: prefer turrets > supports > walls; prefer healthy > damaged.
    upgrade_candidates.sort(key=lambda t: (t[1] != TURRET_IDX, -t[4]))

    for k in (2, 4, 8, 16):
        if k > len(upgrade_candidates):
            break
        sp_used = 0.0
        plan = ActionPlan(name=f"defense:upg{k}", archetype="upgrade")
        for cost, idx, x, y, _ in upgrade_candidates[:k]:
            if sp_used + cost > sp_budget + 1e-9:
                break
            plan.add_upgrade(x, y)
            sp_used += cost
        if plan.ops:
            plan.sp_cost = sp_used
            plan.mp_cost = 0.0
            plans.append(plan)

    # 9. Refund-and-replace: remove K most-damaged turrets (refund) + place fresh nearby.
    damaged_turrets = []
    for x in range(ARENA_DIM):
        for y in range(ARENA_HALF_Y):
            u = _existing_struct(game_state, x, y)
            if u is None:
                continue
            sh = str(getattr(u, "unit_type", "")).upper()
            if sh not in ("DF", "TURRET"):
                continue
            try:
                hp = float(getattr(u, "health", 0))
                maxhp = float(getattr(u, "max_health", 1) or 1)
                hp_frac = hp / maxhp
            except Exception:
                continue
            if hp_frac < 0.4:
                damaged_turrets.append((hp_frac, x, y))
    damaged_turrets.sort()  # most-damaged first
    for k in (1, 2):
        if not damaged_turrets[:k]:
            break
        plan = ActionPlan(name=f"defense:refund{k}", archetype="refund")
        for _, x, y in damaged_turrets[:k]:
            plan.add_remove(x, y)
        plan.sp_cost = 0.0
        plan.mp_cost = 0.0
        plans.append(plan)

    # 9b. SMART REFUND (I-S6): probe-driven structure removal targeting
    # trap-causing structures. Generates refund templates aimed at
    # whichever own structures, when removed, RESTORE offensive
    # viability (per the sim_rs probe). Complements the damaged-turret
    # refund above — operates on ANY own structure type (turret /
    # support / wall) and uses SIM OUTCOME instead of damage state.
    #
    # Key idea (vs §16 of CONTEXT_HANDOFF.md): rather than predict
    # traps via BFS (over-conservative) or hardcoded tile swaps
    # (regression-prone), we ASK THE SIMULATOR which structures cause
    # the trap by removing each candidate and re-running the probe.
    # The actual game removal goes through the existing KIND_REMOVE
    # mechanism — engine refunds 0.9 × cost × hp_frac next turn.
    smart_refund_plans = _gen_refund_smart(game_state, config)
    plans.extend(smart_refund_plans)

    # 10. Reactive patches: turret near a recent breach
    if recent_breaches:
        for k_breaches in (1, 2):
            atoms = []
            for bx, by in recent_breaches[-k_breaches:]:
                # Side-aware patch: build behind the breach
                if bx < 14:
                    candidates = [(TURRET_IDX, bx + 1, min(by + 1, 13)),
                                  (TURRET_IDX, bx, min(by + 2, 13))]
                else:
                    candidates = [(TURRET_IDX, bx - 1, min(by + 1, 13)),
                                  (TURRET_IDX, bx, min(by + 2, 13))]
                atoms.extend(candidates)
            p = _build_defense_plan(
                f"defense:patch{k_breaches}",
                atoms + ANCHOR_TURRETS,
                game_state, config, sp_budget, archetype="reactive",
            )
            if p:
                plans.append(p)

    # 11. Skeleton + upgrade (combined: build atoms + upgrade existing)
    if upgrade_candidates:
        upg_subset = [(x, y) for cost, idx, x, y, _ in upgrade_candidates[:4]]
        p = _build_defense_plan(
            "defense:skeleton_upg",
            ANCHOR_TURRETS + SIDELANE_TURRETS + WALL_ROW_Y12 + EDGE_WALLS,
            game_state, config, sp_budget,
            upgrades=upg_subset, archetype="skeleton_upg",
        )
        if p:
            plans.append(p)

    # 12. Flank-corridor templates (smart_oracle_F) — gated by the funnel
    # detector AND (in algo_strategy.py) the OpponentClassifier verdict.
    # The detector trigger is necessary but not sufficient: the classifier
    # must additionally classify the opponent as `single_archetype` for
    # algo_strategy to actually pass `funnel_target` here. This prevents
    # the IS6 regression (oracle multi-archetype opponents stay None) while
    # still firing on real funnel attackers like ameyg/funnel-rush-v7.
    if funnel_target == "left":
        p = _build_defense_plan(
            "defense:left_flank_corridor",
            LEFT_FLANK_CORRIDOR + ANCHOR_TURRETS + INNER_CORNER_TURRETS,
            game_state, config, sp_budget,
            upgrades=LEFT_FLANK_UPGRADES,
            archetype="left_flank_corridor",
        )
        if p:
            plans.append(p)
        p = _build_defense_plan(
            "defense:left_flank_min",
            LEFT_FLANK_MIN + ANCHOR_TURRETS,
            game_state, config, sp_budget,
            upgrades=LEFT_FLANK_MIN_UPGRADES,
            archetype="left_flank_min",
        )
        if p:
            plans.append(p)
        if upgrade_candidates:
            upg_subset = [(x, y) for cost, idx, x, y, _ in upgrade_candidates[:2]]
            p = _build_defense_plan(
                "defense:left_flank_corridor_upg",
                LEFT_FLANK_CORRIDOR + ANCHOR_TURRETS,
                game_state, config, sp_budget,
                upgrades=LEFT_FLANK_UPGRADES + upg_subset,
                archetype="left_flank_corridor_upg",
            )
            if p:
                plans.append(p)
    elif funnel_target == "right":
        p = _build_defense_plan(
            "defense:right_flank_corridor",
            RIGHT_FLANK_CORRIDOR + ANCHOR_TURRETS + INNER_CORNER_TURRETS,
            game_state, config, sp_budget,
            upgrades=RIGHT_FLANK_UPGRADES,
            archetype="right_flank_corridor",
        )
        if p:
            plans.append(p)
        p = _build_defense_plan(
            "defense:right_flank_min",
            RIGHT_FLANK_MIN + ANCHOR_TURRETS,
            game_state, config, sp_budget,
            upgrades=RIGHT_FLANK_MIN_UPGRADES,
            archetype="right_flank_min",
        )
        if p:
            plans.append(p)
        if upgrade_candidates:
            upg_subset = [(x, y) for cost, idx, x, y, _ in upgrade_candidates[:2]]
            p = _build_defense_plan(
                "defense:right_flank_corridor_upg",
                RIGHT_FLANK_CORRIDOR + ANCHOR_TURRETS,
                game_state, config, sp_budget,
                upgrades=RIGHT_FLANK_UPGRADES + upg_subset,
                archetype="right_flank_corridor_upg",
            )
            if p:
                plans.append(p)

    # Filter: keep distinct plans only (by op tuples).
    seen = set()
    distinct = []
    for p in plans:
        sig = tuple(sorted((op.kind, op.unit_idx, op.xy) for op in p.ops))
        if sig in seen:
            continue
        seen.add(sig)
        distinct.append(p)
    return distinct


# ===========================================================================
# Offense plan builders
# ===========================================================================

def _is_legal_spawn_tile(game_state, x: int, y: int) -> bool:
    if not game_state.game_map.in_arena_bounds([x, y]):
        return False
    # Mobile spawns are valid only on the bottom edges
    if (x, y) not in BOTTOM_LEFT_EDGE and (x, y) not in BOTTOM_RIGHT_EDGE:
        return False
    if game_state.contains_stationary_unit([x, y]):
        return False
    return True


def _enumerate_offense_options(game_state, config, mp_budget: float
                               ) -> List[ActionPlan]:
    """Return ~50-200 offense plans, all feasible at mp_budget."""
    plans: List[ActionPlan] = []

    # 1. HOLD ALL MP (no offensive action — banks for next turn)
    p = ActionPlan(name="offense:hold", archetype="hold")
    p.sp_cost = 0.0
    p.mp_cost = 0.0
    plans.append(p)

    if mp_budget < 1.0:
        return plans  # No offense possible

    # Filter launchers to those that are currently legal
    legal_launchers = [(x, y) for x, y in ALL_LAUNCHERS
                       if _is_legal_spawn_tile(game_state, x, y)]
    if not legal_launchers:
        return plans

    scout_cost = cost_for_idx(config, SCOUT_IDX)
    demo_cost = cost_for_idx(config, DEMOLISHER_IDX)
    int_cost = cost_for_idx(config, INTERCEPTOR_IDX)

    max_scouts = int(mp_budget // max(scout_cost, 1e-6))
    max_demos = int(mp_budget // max(demo_cost, 1e-6))
    max_ints = int(mp_budget // max(int_cost, 1e-6))

    # 2. Scout rush from each launcher × multiple batch sizes
    scout_batches = [4, 6, 8, 10, 12, 16, max_scouts]
    scout_batches = sorted({n for n in scout_batches if 1 <= n <= max_scouts})
    for x, y in legal_launchers:
        for n in scout_batches:
            cost = n * scout_cost
            if cost > mp_budget + 1e-9:
                continue
            p = ActionPlan(name=f"offense:scout{n}@{x},{y}",
                           archetype="scout_rush")
            p.add_mobile(SCOUT_IDX, x, y, n)
            p.sp_cost = 0.0
            p.mp_cost = cost
            plans.append(p)

    # 3. Demolisher rush from each launcher × batches (only if MP ≥ 3)
    if max_demos >= 1:
        demo_batches = [1, 2, 3, 4, 6, max_demos]
        demo_batches = sorted({n for n in demo_batches if 1 <= n <= max_demos})
        for x, y in legal_launchers:
            for n in demo_batches:
                cost = n * demo_cost
                if cost > mp_budget + 1e-9:
                    continue
                p = ActionPlan(name=f"offense:demo{n}@{x},{y}",
                               archetype="demo_rush")
                p.add_mobile(DEMOLISHER_IDX, x, y, n)
                p.sp_cost = 0.0
                p.mp_cost = cost
                plans.append(p)

    # 4. Interceptor screen — small batches from launcher, anti-rush
    if max_ints >= 1:
        int_batches = [1, 2, 3, max_ints]
        int_batches = sorted({n for n in int_batches if 1 <= n <= max_ints})
        # Use only off-axis launchers for interceptors (they screen flank rushes)
        screen_launchers = [(x, y) for x, y in legal_launchers
                            if (x, y) in LAUNCHERS_OFFAXIS or
                            (x, y) in LAUNCHERS_OUTER or
                            (x, y) in LAUNCHERS_FAR]
        for x, y in screen_launchers:
            for n in int_batches:
                cost = n * int_cost
                if cost > mp_budget + 1e-9:
                    continue
                p = ActionPlan(name=f"offense:int{n}@{x},{y}",
                               archetype="int_screen")
                p.add_mobile(INTERCEPTOR_IDX, x, y, n)
                p.sp_cost = 0.0
                p.mp_cost = cost
                plans.append(p)

    # 5. Mixed wave: 1 demo (frontline crusher) + N scouts (cleanup)
    if max_demos >= 1 and max_scouts >= 4:
        for x, y in [(13, 0), (14, 0), (3, 10), (24, 10)]:
            if not _is_legal_spawn_tile(game_state, x, y):
                continue
            for demo_n in (1, 2):
                for scout_n in (4, 6, 8):
                    cost = demo_n * demo_cost + scout_n * scout_cost
                    if cost > mp_budget + 1e-9:
                        continue
                    p = ActionPlan(
                        name=f"offense:mix{demo_n}d+{scout_n}s@{x},{y}",
                        archetype="mix_demo_scout",
                    )
                    p.add_mobile(DEMOLISHER_IDX, x, y, demo_n)
                    p.add_mobile(SCOUT_IDX, x, y, scout_n)
                    p.sp_cost = 0.0
                    p.mp_cost = cost
                    plans.append(p)

    # 6. Two-prong: split MP across both center launchers
    if max_scouts >= 4:
        for n_each in (3, 4, 6):
            cost = 2 * n_each * scout_cost
            if cost > mp_budget + 1e-9:
                continue
            l1, l2 = (13, 0), (14, 0)
            if not (_is_legal_spawn_tile(game_state, *l1) and
                    _is_legal_spawn_tile(game_state, *l2)):
                continue
            p = ActionPlan(name=f"offense:twoprong{n_each}each",
                           archetype="two_prong")
            p.add_mobile(SCOUT_IDX, l1[0], l1[1], n_each)
            p.add_mobile(SCOUT_IDX, l2[0], l2[1], n_each)
            p.sp_cost = 0.0
            p.mp_cost = cost
            plans.append(p)
        # Wide split: corner + corner
        for n_each in (3, 4, 6):
            cost = 2 * n_each * scout_cost
            if cost > mp_budget + 1e-9:
                continue
            l1, l2 = (3, 10), (24, 10)
            if not (_is_legal_spawn_tile(game_state, *l1) and
                    _is_legal_spawn_tile(game_state, *l2)):
                continue
            p = ActionPlan(name=f"offense:wide{n_each}each",
                           archetype="two_prong_wide")
            p.add_mobile(SCOUT_IDX, l1[0], l1[1], n_each)
            p.add_mobile(SCOUT_IDX, l2[0], l2[1], n_each)
            p.sp_cost = 0.0
            p.mp_cost = cost
            plans.append(p)

    return plans


# ===========================================================================
# Public API
# ===========================================================================

def enumerate_plans(game_state, config, *,
                    recent_breaches: Sequence[Tuple[int, int]] = (),
                    max_plans: int = 1500,
                    funnel_target: Optional[str] = None,
                    ) -> List[ActionPlan]:
    """Generate a diverse, feasible candidate set of ActionPlans.

    Returns a list of plans, each with sp_cost / mp_cost pre-computed.
    Caller is expected to evaluate each via the search.

    `funnel_target` ∈ {None, "left", "right"} forwards to
    _enumerate_defense_templates so flank-corridor templates appear
    in the candidate pool when the funnel detector has fired
    (smart_oracle_F extension).
    """
    sp = float(game_state.get_resource(SP_RES, 0))
    mp = float(game_state.get_resource(MP_RES, 0))

    defenses = _enumerate_defense_templates(
        game_state, config, sp_budget=sp, recent_breaches=recent_breaches,
        funnel_target=funnel_target,
    )
    if not defenses:
        defenses = [ActionPlan(name="defense:none", archetype="none")]

    # I-S6: smart-refund templates are HIGH-PRIORITY — they're only
    # generated when the probe detects a trap, and they're the highest-
    # signal candidates we have for trap states. Reorder the defenses
    # list so smart-refund templates appear FIRST, ensuring they're
    # cross-producted with all offense templates before max_plans is
    # hit. The remaining defenses keep their original order.
    smart_refund_defenses = [d for d in defenses if d.archetype == "refund_smart"]
    other_defenses = [d for d in defenses if d.archetype != "refund_smart"]
    defenses_ordered = smart_refund_defenses + other_defenses

    out: List[ActionPlan] = []
    for d in defenses_ordered:
        sp_left = sp - d.sp_cost
        if sp_left < -1e-9:
            continue
        offenses = _enumerate_offense_options(
            game_state, config, mp_budget=mp,
        )
        for o in offenses:
            combo = ActionPlan(
                name=f"{d.name}|{o.name}",
                archetype=f"{d.archetype}+{o.archetype}",
            )
            combo.ops = list(d.ops) + list(o.ops)
            combo.sp_cost = d.sp_cost + o.sp_cost
            combo.mp_cost = d.mp_cost + o.mp_cost
            out.append(combo)
            if len(out) >= max_plans:
                break
        if len(out) >= max_plans:
            break
    return out


__all__ = [
    "enumerate_plans",
    "_enumerate_defense_templates",
    "_enumerate_offense_options",
    "_gen_refund_smart",
    "ALL_LAUNCHERS",
]
