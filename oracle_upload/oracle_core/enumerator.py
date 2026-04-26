"""Oracle candidate action enumerator.

Produces ~200-800 reasonable action plans per turn for the Oracle search
loop to evaluate. Plans are independent ``ActionPlan`` instances built
as the cross product of a small defensive option set and a small
offensive option set, filtered by joint resource feasibility.

Public API:
    enumerate_candidates(game_state, max_candidates=800, *, recent_breaches=None)
        -> List[ActionPlan]
        Generate the candidate action space for the current turn.

    apply_to_game_state(game_state, plan)
        -> int
        Mutate ``game_state`` to attempt every action in ``plan``.
        Returns the number of successful spawns / upgrades / removes.

ActionPlan schema (matches the user's Phase-1 spec):
    structure_actions : list of (action_type, unit_shorthand, [x, y])
        action_type ∈ {"spawn", "upgrade", "remove"}.
        unit_shorthand for "spawn": "FF"/"EF"/"DF" (Wall/Support/Turret).
        For "upgrade" and "remove" the engine looks up the existing unit
        at [x, y]; the unit slot is informational.
    mobile_actions    : list of (unit_shorthand, [x, y], count)
        unit_shorthand: "PI"/"EI"/"SI" (Scout/Demolisher/Interceptor).
    description       : human-readable label, e.g. "scout_rush_left_8".
    sp_cost           : estimated SP cost (for budget filtering).
    mp_cost           : estimated MP cost.

NOTE on schema compatibility: ``oracle_core.search.py`` was prototyped
against an earlier 3-tuple schema ``(unit_shorthand, [x, y], upgraded)``.
That module's ``_apply_structures_inplace`` will need to be updated to
unpack our 3-tuple ``(action_type, unit_shorthand, [x, y])`` — handled
in a sibling change.

Design notes:
- All unit costs are read from the live game config when available
  (``game_state.config["unitInformation"]``). DEFAULT_COSTS is fallback only.
- Stationary unit shorthands at runtime are FF/EF/DF (pre-rename codes,
  see MEMORY: citadel_runtime_shorthands). We use those by default.
- We never enumerate the full combinatorial space. We pick a small set of
  defense templates (~10-25) and offense templates (~30-60), then filter
  the cross product → ~200-1000 plans per turn.
- The enumerator is purely deterministic. No opponent prediction, no
  randomness, no I/O.
"""
from __future__ import annotations

from dataclasses import dataclass, field
from typing import Any, List, Optional, Sequence, Tuple, Union

# ---------------------------------------------------------------------------
# Live-config shorthands (FF/EF/DF/PI/EI/SI). The Citadel server has not
# renamed these to WALL/SUPPORT/... at runtime — only the docs use the new
# names. See MEMORY: citadel_runtime_shorthands.
# ---------------------------------------------------------------------------
WALL: str = "FF"
SUPPORT: str = "EF"
TURRET: str = "DF"
SCOUT: str = "PI"
DEMOLISHER: str = "EI"
INTERCEPTOR: str = "SI"

# Resource indices used by gamelib.GameState.get_resource()
SP_IDX: int = 0
MP_IDX: int = 1

# Map / arena constants
ARENA_SIZE: int = 28
HALF_ARENA: int = 14

# Fallback cost table (SP, MP) — used only if config lookup fails. Verified
# against algos/oracle/data/citadel_config_snapshot.json.
DEFAULT_COSTS: dict = {
    WALL: (1.0, 0.0),
    SUPPORT: (4.0, 0.0),
    TURRET: (2.0, 0.0),
    SCOUT: (0.0, 1.0),
    DEMOLISHER: (0.0, 3.0),
    INTERCEPTOR: (0.0, 1.0),
}

# Upgrade costs (SP) — base values from the live config. Used for upgrade
# action cost estimation when the runtime config can't be read.
DEFAULT_UPGRADE_SP: dict = {
    WALL: 2.0,
    SUPPORT: 2.0,
    TURRET: 4.0,
}

# Hard ceiling on plan count to keep the search bounded.
DEFAULT_MAX_CANDIDATES: int = 800


# ---------------------------------------------------------------------------
# Data class
# ---------------------------------------------------------------------------

# Type aliases for clarity.
StructureAction = Tuple[str, str, List[int]]  # (action_type, unit_shorthand, [x, y])
MobileAction = Tuple[str, List[int], int]      # (unit_shorthand, [x, y], count)


@dataclass
class ActionPlan:
    """A single candidate plan for one turn.

    See module docstring for the schema.
    """
    structure_actions: List[StructureAction] = field(default_factory=list)
    mobile_actions: List[MobileAction] = field(default_factory=list)
    description: str = ""
    sp_cost: float = 0.0
    mp_cost: float = 0.0

    @property
    def is_empty(self) -> bool:
        return not self.structure_actions and not self.mobile_actions

    def signature(self) -> Tuple:
        """Hashable identity for de-duping plans by content (not description)."""
        struct = tuple(
            (str(a), str(u), (int(loc[0]), int(loc[1])))
            for (a, u, loc) in self.structure_actions
        )
        mobile = tuple(
            (str(u), (int(loc[0]), int(loc[1])), int(n))
            for (u, loc, n) in self.mobile_actions
        )
        return (struct, mobile)


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def _bottom_left_edge_tiles() -> List[List[int]]:
    """[(0,13), (1,12), ..., (13,0)] — BL spawn edge."""
    return [[i, 13 - i] for i in range(14)]


def _bottom_right_edge_tiles() -> List[List[int]]:
    """[(14,0), (15,1), ..., (27,13)] — BR spawn edge."""
    return [[14 + i, i] for i in range(14)]


def _all_bottom_edge_tiles() -> List[List[int]]:
    return _bottom_left_edge_tiles() + _bottom_right_edge_tiles()


_BL_TILES: List[List[int]] = _bottom_left_edge_tiles()
_BR_TILES: List[List[int]] = _bottom_right_edge_tiles()
_ALL_EDGE_TILES_SET: set = {(t[0], t[1]) for t in _all_bottom_edge_tiles()}


def _is_in_bottom_half(loc: Sequence[int]) -> bool:
    return 0 <= int(loc[1]) < HALF_ARENA


def _is_edge_tile(loc: Sequence[int]) -> bool:
    return (int(loc[0]), int(loc[1])) in _ALL_EDGE_TILES_SET


def _get_costs(game_state, unit: str) -> Tuple[float, float]:
    """Return (SP_cost, MP_cost) for a unit. Reads from live config if
    available, falls back to DEFAULT_COSTS otherwise.
    """
    try:
        c = game_state.type_cost(unit)
        if c is not None and len(c) >= 2:
            return float(c[0] or 0.0), float(c[1] or 0.0)
    except Exception:
        pass
    return DEFAULT_COSTS.get(unit, (0.0, 0.0))


def _get_upgrade_sp(game_state, unit: str) -> float:
    """SP cost to upgrade a unit of this type."""
    try:
        c = game_state.type_cost(unit, upgrade=True)
        if c is not None and len(c) >= 1:
            return float(c[0] or 0.0)
    except Exception:
        pass
    return DEFAULT_UPGRADE_SP.get(unit, 0.0)


def _has_stationary_at(game_state, loc: Sequence[int]) -> bool:
    """True if there's any stationary unit at this tile."""
    try:
        u = game_state.contains_stationary_unit(loc)
        return bool(u)
    except Exception:
        return False


def _free_to_build(game_state, loc: Sequence[int]) -> bool:
    """True if we can plausibly place a structure at this tile.

    We restrict to bottom half + in-bounds + no existing stationary unit.
    """
    if not _is_in_bottom_half(loc):
        return False
    try:
        gm = game_state.game_map
        if not gm.in_arena_bounds(loc):
            return False
    except Exception:
        return False
    return not _has_stationary_at(game_state, loc)


def _suppress_warnings_if_possible(game_state) -> None:
    """Disable gamelib's chatty stderr while we probe the state."""
    try:
        game_state.suppress_warnings(True)
    except Exception:
        pass


# ---------------------------------------------------------------------------
# Defense option enumeration
# ---------------------------------------------------------------------------

def _support_back_row_tiles() -> List[List[int]]:
    """Tiles in the deep back row suitable for Support stacking.

    Restricted to y in [2, 7] so supports get the y-bonus shielding while
    remaining close to mobile spawn paths. EXCLUDES the BL/BR spawn edge
    tiles (y == 13 - x is BL, y == x - 14 is BR) — structures can't be
    placed there (they are mobile-spawn locations).
    """
    out: List[List[int]] = []
    for y in (2, 3, 4, 5, 6, 7):
        # Diamond inscribed in 28x28: at row y the x-span is
        # [13 - y, 14 + y]. The BL edge tile is (13 - y, y); the BR edge
        # tile is (14 + y, y). Exclude both.
        x_lo = max(0, HALF_ARENA - (y + 1))
        x_hi = min(ARENA_SIZE - 1, HALF_ARENA + y)
        # The two outermost x at each y are the spawn-edge tiles — skip them.
        x_inner_lo = x_lo + 1
        x_inner_hi = x_hi - 1
        # Prefer central columns (x in [4, 23]) to avoid corner deadweight.
        lo = max(4, x_inner_lo)
        hi = min(23, x_inner_hi)
        for x in range(lo, hi + 1):
            out.append([x, y])
    return out


_SUPPORT_BACK_ROW_TILES: List[List[int]] = _support_back_row_tiles()


# ---------------------------------------------------------------------------
# Strong opening / full-defense skeletons. These are the v13 / lostkids /
# funnel-rush style plans that fill many actions in one turn — critical for
# matching the strength of top-tier algos that compose coherent defenses.
# ---------------------------------------------------------------------------

# v13's defense skeleton (12 turrets at y=11/9 + wall row at y=12).
# Order matters: most important first so the engine fills as much as possible
# when SP is constrained.
_V13_TURRETS_HIGH_PRIO = [
    [13, 11], [14, 11], [11, 11], [16, 11],   # center 4
]
_V13_TURRETS_MED_PRIO = [
    [7, 9], [20, 9],                          # sidelane deep
    [5, 11], [22, 11], [8, 11], [19, 11],     # outer 4
    [1, 13], [26, 13],                        # inner corners
]
_V13_WALL_LINE = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
_V13_WALL_CORNERS = [[2, 13], [25, 13]]

# Lostkids V-shape (alternate skeleton — diversifies our search):
_LOSTKIDS_V_TURRETS = [
    [4, 12], [23, 12], [3, 11], [24, 11],     # outer V tips
    [13, 10], [14, 10],                       # apex
]
_LOSTKIDS_V_WALLS = [
    [4, 13], [23, 13],                        # outer corners
    [5, 12], [22, 12], [6, 11], [21, 11],     # V slope
    [13, 11], [14, 11],                       # below apex
    [12, 10], [15, 10], [11, 9], [16, 9],     # extending V
]

# FUNNEL-style (parallel walls funneling enemies into kill zones):
_FUNNEL_TURRETS = [
    [10, 12], [17, 12],                       # mid choke turrets
    [13, 11], [14, 11],                       # center
    [6, 10], [21, 10],                        # rear choke
]
_FUNNEL_WALLS = [
    [9, 12], [11, 12], [16, 12], [18, 12],   # walls flanking turrets
    [12, 12], [15, 12],                       # center walls
    [3, 13], [24, 13], [2, 13], [25, 13],   # corner walls
]


def _strong_defense_plans(game_state, sp_available: float) -> List[ActionPlan]:
    """Generate large multi-action defense plans matching v13 / lostkids / funnel."""
    plans: List[ActionPlan] = []
    wall_sp, _ = _get_costs(game_state, WALL)
    turret_sp, _ = _get_costs(game_state, TURRET)
    if wall_sp <= 0:
        wall_sp = 1.0
    if turret_sp <= 0:
        turret_sp = 2.0

    def _build_skeleton(turrets, walls, name: str) -> Optional[ActionPlan]:
        """Build as much of (turrets, walls) as fits in budget, in priority order."""
        actions: List[Tuple[str, str, List[int]]] = []
        cost = 0.0
        # Turrets first (more value)
        for t in turrets:
            if not _free_to_build(game_state, t):
                continue
            if cost + turret_sp > sp_available + 1e-6:
                break
            actions.append(("spawn", TURRET, list(t)))
            cost += turret_sp
        # Then walls
        for w in walls:
            if not _free_to_build(game_state, w):
                continue
            if cost + wall_sp > sp_available + 1e-6:
                break
            actions.append(("spawn", WALL, list(w)))
            cost += wall_sp
        if not actions:
            return None
        return ActionPlan(
            structure_actions=actions,
            description=f"defense_{name}_x{len(actions)}",
            sp_cost=cost,
        )

    # v13-style: turrets first (high prio), then medium prio, then wall row
    p = _build_skeleton(
        _V13_TURRETS_HIGH_PRIO + _V13_TURRETS_MED_PRIO,
        _V13_WALL_LINE + _V13_WALL_CORNERS,
        "v13_skeleton",
    )
    if p:
        plans.append(p)

    # v13-style: minimal opening (just the 4 center turrets + a few walls)
    p = _build_skeleton(
        _V13_TURRETS_HIGH_PRIO,
        _V13_WALL_LINE[:6] + _V13_WALL_CORNERS,
        "v13_minimal",
    )
    if p:
        plans.append(p)

    # Lostkids V-shape (alternate skeleton — diversifies search)
    p = _build_skeleton(
        _LOSTKIDS_V_TURRETS,
        _LOSTKIDS_V_WALLS,
        "lostkids_v",
    )
    if p:
        plans.append(p)

    # FUNNEL parallel-walls
    p = _build_skeleton(
        _FUNNEL_TURRETS,
        _FUNNEL_WALLS,
        "funnel",
    )
    if p:
        plans.append(p)

    return plans


def _enumerate_defense_options(
    game_state,
    sp_available: float,
    *,
    recent_breaches: Optional[Sequence[Sequence[int]]] = None,
) -> List[ActionPlan]:
    """Build a small set of plausible defensive plans.

    Each option captures one *defensive intent* (idle, patch breaches,
    add turrets, add supports, upgrade, refund). Total: ~10-30 options.
    """
    options: List[ActionPlan] = []
    wall_sp, _ = _get_costs(game_state, WALL)
    turret_sp, _ = _get_costs(game_state, TURRET)
    support_sp, _ = _get_costs(game_state, SUPPORT)

    # 1) Idle: no structural changes — always present.
    options.append(ActionPlan(description="defense_idle"))

    # 2) Reactive turret placements at recent breaches. Cap at 4 unique
    # breach columns to bound the option count.
    if recent_breaches:
        seen_cols: set = set()
        for breach in recent_breaches:
            try:
                bx, by = int(breach[0]), int(breach[1])
            except (TypeError, IndexError, ValueError):
                continue
            if bx in seen_cols:
                continue
            seen_cols.add(bx)
            if len(seen_cols) > 4:
                break
            for ty in (12, 11):
                tile = [bx, ty]
                if not (_is_in_bottom_half(tile) and _free_to_build(game_state, tile)):
                    continue
                if turret_sp > sp_available + 1e-6:
                    break
                options.append(ActionPlan(
                    structure_actions=[("spawn", TURRET, tile)],
                    description=f"defense_turret_at_breach_{bx}_{ty}",
                    sp_cost=turret_sp,
                ))
                # Also a turret + wall combo if there's room.
                wall_tile = [bx, min(13, ty + 1)]
                if (wall_tile != tile
                        and _is_in_bottom_half(wall_tile)
                        and _free_to_build(game_state, wall_tile)
                        and turret_sp + wall_sp <= sp_available + 1e-6):
                    options.append(ActionPlan(
                        structure_actions=[
                            ("spawn", TURRET, tile),
                            ("spawn", WALL, wall_tile),
                        ],
                        description=f"defense_turret_wall_at_breach_{bx}_{ty}",
                        sp_cost=turret_sp + wall_sp,
                    ))
                break  # at most one turret offer per breach column

    # 3) Tip-leak wall patches at the corner edge tiles.
    tip_walls = [
        ([0, 13], [1, 12]),
        ([27, 13], [26, 12]),
    ]
    for w1, w2 in tip_walls:
        wlist: List[StructureAction] = []
        cost = 0.0
        for w in (w1, w2):
            if _free_to_build(game_state, w):
                wlist.append(("spawn", WALL, list(w)))
                cost += wall_sp
        if wlist and cost <= sp_available + 1e-6:
            options.append(ActionPlan(
                structure_actions=wlist,
                description=f"defense_wall_patch_{w1[0]}_{w1[1]}",
                sp_cost=cost,
            ))

    # 4) Front-row turret pairs at the central choke points.
    central_turret_pairs = [
        ([6, 11], [7, 11]),
        ([20, 11], [21, 11]),
        ([10, 12], [17, 12]),
        ([12, 12], [15, 12]),
    ]
    for t1, t2 in central_turret_pairs:
        actions: List[StructureAction] = []
        cost = 0.0
        for t in (t1, t2):
            if _free_to_build(game_state, t):
                actions.append(("spawn", TURRET, list(t)))
                cost += turret_sp
        if actions and cost <= sp_available + 1e-6:
            options.append(ActionPlan(
                structure_actions=actions,
                description=f"defense_turret_pair_{t1[0]}_{t1[1]}",
                sp_cost=cost,
            ))

    # 5) Support stacking — back row, in batches of 1 / 2 / 4.
    free_support_tiles = [
        t for t in _SUPPORT_BACK_ROW_TILES if _free_to_build(game_state, t)
    ]
    for batch_size in (1, 2, 4):
        if batch_size * support_sp > sp_available + 1e-6:
            break
        if len(free_support_tiles) < batch_size:
            break
        chosen = free_support_tiles[:batch_size]
        actions = [("spawn", SUPPORT, list(t)) for t in chosen]
        options.append(ActionPlan(
            structure_actions=actions,
            description=f"defense_supports_x{batch_size}",
            sp_cost=batch_size * support_sp,
        ))

    # 6) Upgrade plans — upgrade existing turrets near the front, or
    # existing supports anywhere. Pick at most a couple per kind.
    try:
        gm = game_state.game_map
    except Exception:
        gm = None

    upgrade_turret_targets: List[List[int]] = []
    upgrade_support_targets: List[List[int]] = []
    if gm is not None:
        for x in range(ARENA_SIZE):
            for y in range(HALF_ARENA):
                try:
                    if not gm.in_arena_bounds([x, y]):
                        continue
                    cell = gm[x, y]
                except Exception:
                    continue
                for u in cell or []:
                    if getattr(u, "player_index", 0) != 0:
                        continue
                    if getattr(u, "upgraded", False):
                        continue
                    if u.unit_type == TURRET and y >= 11:
                        upgrade_turret_targets.append([x, y])
                    elif u.unit_type == SUPPORT:
                        upgrade_support_targets.append([x, y])

    # Cap candidates to keep options bounded.
    upgrade_turret_targets = upgrade_turret_targets[:6]
    upgrade_support_targets = upgrade_support_targets[:4]

    if upgrade_turret_targets:
        upgrade_cost = _get_upgrade_sp(game_state, TURRET)
        for n in (1, 2):
            if n > len(upgrade_turret_targets):
                break
            cost = n * upgrade_cost
            if cost > sp_available + 1e-6:
                break
            actions = [
                ("upgrade", TURRET, list(t))
                for t in upgrade_turret_targets[:n]
            ]
            options.append(ActionPlan(
                structure_actions=actions,
                description=f"defense_upgrade_turrets_x{n}",
                sp_cost=cost,
            ))

    if upgrade_support_targets:
        upgrade_cost = _get_upgrade_sp(game_state, SUPPORT)
        for n in (1, 2):
            if n > len(upgrade_support_targets):
                break
            cost = n * upgrade_cost
            if cost > sp_available + 1e-6:
                break
            actions = [
                ("upgrade", SUPPORT, list(t))
                for t in upgrade_support_targets[:n]
            ]
            options.append(ActionPlan(
                structure_actions=actions,
                description=f"defense_upgrade_supports_x{n}",
                sp_cost=cost,
            ))

    # 6.5) STRONG OPENING / FULL DEFENSE PLANS
    # These large multi-action plans are critical for matching v13/lostkids/funnel-rush
    # who all build a coherent skeleton each turn. Without these, Oracle gets
    # outscaled by opponents who place 12+ structures per turn.
    options.extend(_strong_defense_plans(game_state, sp_available))

    # 7) Refund cycle: remove damaged structures (HP < 50% of max).
    refund_targets: List[List[int]] = []
    if gm is not None:
        for x in range(ARENA_SIZE):
            for y in range(HALF_ARENA):
                try:
                    if not gm.in_arena_bounds([x, y]):
                        continue
                    cell = gm[x, y]
                except Exception:
                    continue
                for u in cell or []:
                    if getattr(u, "player_index", 0) != 0:
                        continue
                    if getattr(u, "pending_removal", False):
                        continue
                    max_hp = float(getattr(u, "max_health", 0) or 0)
                    hp = float(getattr(u, "health", 0) or 0)
                    if max_hp > 0 and hp / max_hp < 0.5:
                        refund_targets.append([x, y])
    if refund_targets:
        # Cap to top-3 so the option set stays manageable.
        refund_targets = refund_targets[:3]
        actions = [("remove", WALL, list(t)) for t in refund_targets]
        options.append(ActionPlan(
            structure_actions=actions,
            description=f"defense_refund_damaged_x{len(refund_targets)}",
            sp_cost=0.0,  # remove queues are free at request time
        ))

    return options


# ---------------------------------------------------------------------------
# Offense option enumeration
# ---------------------------------------------------------------------------

# Canonical offense launch points (mobile spawn tiles).
_OFFENSE_LAUNCHERS: dict = {
    "left_corner": [0, 13],
    "right_corner": [27, 13],
    "left_inside": [13, 0],
    "right_inside": [14, 0],
    "left_mid": [3, 10],
    "right_mid": [24, 10],
}


def _scout_batch_sizes(mp_per_unit: float, mp_available: float) -> List[int]:
    """Discrete batch sizes worth trying for Scout rushes."""
    if mp_per_unit <= 0:
        return []
    cap = int(mp_available // mp_per_unit)
    if cap <= 0:
        return []
    candidates = [5, 8, 12, 16, cap]
    seen: set = set()
    out: List[int] = []
    for n in candidates:
        n = min(n, cap)
        if n >= 1 and n not in seen:
            seen.add(n)
            out.append(n)
    return out


def _demolisher_batch_sizes(mp_per_unit: float, mp_available: float) -> List[int]:
    if mp_per_unit <= 0:
        return []
    cap = int(mp_available // mp_per_unit)
    if cap <= 0:
        return []
    candidates = [1, 2, 3, cap]
    seen: set = set()
    out: List[int] = []
    for n in candidates:
        n = min(n, cap)
        if n >= 1 and n not in seen:
            seen.add(n)
            out.append(n)
    return out


def _interceptor_batch_sizes(mp_per_unit: float, mp_available: float) -> List[int]:
    if mp_per_unit <= 0:
        return []
    cap = int(mp_available // mp_per_unit)
    if cap <= 0:
        return []
    candidates = [1, 2, 3]
    out: List[int] = []
    seen: set = set()
    for n in candidates:
        n = min(n, cap)
        if n >= 1 and n not in seen:
            seen.add(n)
            out.append(n)
    return out


def _enumerate_offense_options(
    game_state,
    mp_available: float,
) -> List[ActionPlan]:
    """Build a small set of plausible offensive plans.

    Each option captures one *offensive intent* (hold, scout-rush from
    each launcher in batch sizes, demolisher line, mixed wave, interceptor
    screen, self-destruct setup). Total: ~30-60 options.
    """
    options: List[ActionPlan] = []
    _, scout_mp = _get_costs(game_state, SCOUT)
    _, demo_mp = _get_costs(game_state, DEMOLISHER)
    _, intc_mp = _get_costs(game_state, INTERCEPTOR)

    # Defensive fallbacks if config didn't deliver costs.
    if scout_mp <= 0:
        scout_mp = 1.0
    if demo_mp <= 0:
        demo_mp = 3.0
    if intc_mp <= 0:
        intc_mp = 1.0

    # 1) Hold MP — always present. Empty mobile_actions.
    options.append(ActionPlan(description="offense_hold"))

    # Determine which launchers are usable this turn.
    launcher_free: dict = {}
    for name, loc in _OFFENSE_LAUNCHERS.items():
        on_edge = _is_edge_tile(loc)
        blocked = _has_stationary_at(game_state, loc)
        launcher_free[name] = (on_edge and not blocked)

    # 2) Scout rushes from each launcher, multiple batch sizes.
    scout_batches = _scout_batch_sizes(scout_mp, mp_available)
    for name, loc in _OFFENSE_LAUNCHERS.items():
        if not launcher_free.get(name, False):
            continue
        for n in scout_batches:
            cost = n * scout_mp
            if cost > mp_available + 1e-6:
                continue
            options.append(ActionPlan(
                mobile_actions=[(SCOUT, list(loc), n)],
                description=f"offense_scout_{name}_x{n}",
                mp_cost=cost,
            ))

    # 3) Scout split (half/half) — only at the two inside launchers.
    if (launcher_free.get("left_inside", False)
            and launcher_free.get("right_inside", False)):
        for total in (8, 12, 16):
            half = total // 2
            cost = total * scout_mp
            if cost > mp_available + 1e-6:
                continue
            options.append(ActionPlan(
                mobile_actions=[
                    (SCOUT, list(_OFFENSE_LAUNCHERS["left_inside"]), half),
                    (SCOUT, list(_OFFENSE_LAUNCHERS["right_inside"]), total - half),
                ],
                description=f"offense_scout_split_{total}",
                mp_cost=cost,
            ))

    # 4) Demolisher line from the inside / mid launchers.
    demo_batches = _demolisher_batch_sizes(demo_mp, mp_available)
    demo_launchers = ("left_inside", "right_inside", "left_mid", "right_mid")
    for name in demo_launchers:
        if not launcher_free.get(name, False):
            continue
        loc = _OFFENSE_LAUNCHERS[name]
        for n in demo_batches:
            cost = n * demo_mp
            if cost > mp_available + 1e-6:
                continue
            options.append(ActionPlan(
                mobile_actions=[(DEMOLISHER, list(loc), n)],
                description=f"offense_demo_{name}_x{n}",
                mp_cost=cost,
            ))

    # 5) Mixed waves: 1-2 demolishers in front, scout pile-on behind.
    for name in ("left_inside", "right_inside"):
        if not launcher_free.get(name, False):
            continue
        loc = _OFFENSE_LAUNCHERS[name]
        for demo_n in (1, 2):
            demo_cost = demo_n * demo_mp
            if demo_cost > mp_available + 1e-6:
                continue
            scout_budget = mp_available - demo_cost
            for scout_n in (4, 8, 12):
                scout_cost = scout_n * scout_mp
                if scout_cost > scout_budget + 1e-6:
                    continue
                options.append(ActionPlan(
                    mobile_actions=[
                        (DEMOLISHER, list(loc), demo_n),
                        (SCOUT, list(loc), scout_n),
                    ],
                    description=f"offense_mixed_{name}_d{demo_n}_s{scout_n}",
                    mp_cost=demo_cost + scout_cost,
                ))

    # 6) Interceptor screen from corners (preferred) and inside spawns
    # (fallback when corners are walled-off, common in the early game).
    intc_batches = _interceptor_batch_sizes(intc_mp, mp_available)
    intc_launchers = ("left_corner", "right_corner", "left_inside", "right_inside")
    for name in intc_launchers:
        if not launcher_free.get(name, False):
            continue
        loc = _OFFENSE_LAUNCHERS[name]
        for n in intc_batches:
            cost = n * intc_mp
            if cost > mp_available + 1e-6:
                continue
            options.append(ActionPlan(
                mobile_actions=[(INTERCEPTOR, list(loc), n)],
                description=f"offense_intc_{name}_x{n}",
                mp_cost=cost,
            ))

    # 7) Self-destruct setup: one Scout at a deeply-walled launcher.
    # Cheap (1 MP) — let the search loop's sim eval decide if it pays off.
    if mp_available >= scout_mp - 1e-6:
        for name in ("left_inside", "right_inside"):
            if not launcher_free.get(name, False):
                continue
            loc = _OFFENSE_LAUNCHERS[name]
            options.append(ActionPlan(
                mobile_actions=[(SCOUT, list(loc), 1)],
                description=f"offense_sd_setup_{name}",
                mp_cost=scout_mp,
            ))

    return options


# ---------------------------------------------------------------------------
# Public API
# ---------------------------------------------------------------------------

def enumerate_candidates(
    game_state,
    max_candidates: int = DEFAULT_MAX_CANDIDATES,
    *,
    recent_breaches: Optional[Sequence[Sequence[int]]] = None,
) -> List[ActionPlan]:
    """Generate candidate action plans for this turn.

    Args:
        game_state: a live ``gamelib.GameState`` instance.
        max_candidates: hard ceiling on returned plan count.
        recent_breaches: optional list of [x, y] tiles where the opponent
            recently breached our edge. Used to bias defense generation.
            Pass ``algo_strategy.scored_on_locations`` (or similar) here.

    Returns:
        A list of feasible ``ActionPlan`` instances. Always contains at
        least one (the no-op plan ``defense_idle+offense_hold``).
    """
    if max_candidates <= 0:
        max_candidates = DEFAULT_MAX_CANDIDATES

    _suppress_warnings_if_possible(game_state)

    sp = 0.0
    mp = 0.0
    try:
        sp_val = game_state.get_resource(SP_IDX, 0)
        mp_val = game_state.get_resource(MP_IDX, 0)
        sp = float(sp_val if sp_val is not None else 0.0)
        mp = float(mp_val if mp_val is not None else 0.0)
    except Exception:
        pass

    # If a breach hint wasn't passed, try a couple of common attribute names
    # on the strategy object reachable via game_state. Best-effort only.
    if recent_breaches is None:
        recent_breaches = (
            getattr(game_state, "scored_on_locations", None)
            or getattr(game_state, "recent_breaches", None)
        )

    defense_options = _enumerate_defense_options(
        game_state, sp, recent_breaches=recent_breaches,
    )
    offense_options = _enumerate_offense_options(game_state, mp)

    plans: List[ActionPlan] = []
    for d in defense_options:
        for o in offense_options:
            total_sp = d.sp_cost + o.sp_cost
            total_mp = d.mp_cost + o.mp_cost
            if total_sp > sp + 1e-6:
                continue
            if total_mp > mp + 1e-6:
                continue
            plan = ActionPlan(
                structure_actions=list(d.structure_actions) + list(o.structure_actions),
                mobile_actions=list(d.mobile_actions) + list(o.mobile_actions),
                description=f"{d.description}+{o.description}",
                sp_cost=total_sp,
                mp_cost=total_mp,
            )
            plans.append(plan)
            if len(plans) >= max_candidates:
                return plans

    # Guarantee at least the no-op plan is present.
    if not plans:
        plans.append(ActionPlan(description="defense_idle+offense_hold"))

    return plans


def apply_to_game_state(game_state, plan: ActionPlan) -> int:
    """Mutate ``game_state`` to attempt every action in ``plan``.

    The caller is responsible for calling ``game_state.submit_turn()``
    after — this function only stages the actions.

    Args:
        game_state: a live ``gamelib.GameState`` instance.
        plan: an ``ActionPlan``.

    Returns:
        The number of successful spawns / upgrades / removes (sum across
        every action in the plan).
    """
    count = 0
    for action_type, unit, loc in plan.structure_actions:
        try:
            if action_type == "spawn":
                count += int(game_state.attempt_spawn(unit, loc) or 0)
            elif action_type == "upgrade":
                count += int(game_state.attempt_upgrade(loc) or 0)
            elif action_type == "remove":
                count += int(game_state.attempt_remove(loc) or 0)
        except Exception:
            # Defensive: never crash the algo on a single bad action.
            continue
    for unit, loc, n in plan.mobile_actions:
        if n <= 0:
            continue
        try:
            count += int(game_state.attempt_spawn(unit, loc, int(n)) or 0)
        except Exception:
            continue
    return count


__all__ = [
    "ActionPlan",
    "StructureAction",
    "MobileAction",
    "enumerate_candidates",
    "apply_to_game_state",
    "WALL", "SUPPORT", "TURRET", "SCOUT", "DEMOLISHER", "INTERCEPTOR",
    "DEFAULT_COSTS", "DEFAULT_MAX_CANDIDATES",
]
