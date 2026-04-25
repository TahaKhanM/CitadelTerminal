"""Athena Phase 2 — defense engine.

This module implements six defensive primitives the planner composes
into a turn:

  1. ``build_default_defences``     — walks an archetype JSON in priority
                                       order and spawns / upgrades.
  2. ``edge_block_and_remove``      — Lostkids' single-turn wall trick
                                       (spawn wall + same-turn remove).
  3. ``refund_low_health_structures`` — drop low-HP structures so we get
                                       back ~70-90% SP next turn.
  4. ``max_heap_repair``            — FUNNEL pattern: most-damaged
                                       structures first (with the
                                       cost-vector index bug FIXED).
  5. ``probabilistic_placement``    — GRETCHEN-style sampler weighted by
                                       per-tile value.
  6. ``reactive_to_breach``         — patch turret coverage at recently
                                       breached tiles.

All numerics (HP, damage, range, costs) are read from runtime config —
NEVER hard-coded. The Citadel server delivers the live config in
``on_game_start``.

Citadel-specific tuning (vs base game):
  - Wall upgrade now costs 2 SP (was 0). ``build_default_defences`` and
    ``max_heap_repair`` budget for this.
  - Upgraded Support shield = ``1 + 0.7*Y`` per Support per Scout at
    range 7. Worth ~10x base shield at Y=13 — Supports are the highest
    per-SP defensive ROI when upgraded.
  - Upgraded Turret = 20 dmg / 100 HP (vs base 6/60). Upgrades are
    unusually strong here.
  - ``probabilistic_placement`` support_weight default = 25.
    GRETCHEN's original 100x reflected base supports being 1-HP one-
    shots. Citadel upgraded supports are 40 HP (~10x more durable), so
    25x is the right starting point. Phase 9 MAP-Elites will retune.
"""

from __future__ import annotations

import heapq
import json
import math
import os
import random
from typing import Any, Dict, Iterable, List, Optional, Sequence, Tuple

# Resource indices — Citadel runtime convention (legacy starter-kit).
# Code paths that pass `resource_type` to GameState use these.
MP_IDX = 1
SP_IDX = 0


def _shorthand_for(config: Dict[str, Any], unit_index: int) -> str:
    """Return the runtime ``shorthand`` (FF/EF/DF/...) for a unit index.

    Citadel's live server still uses the pre-rename shorthands (FF, EF,
    DF, PI, EI, SI). The newer documentation says Wall/Support/Turret
    but the runtime config doesn't.
    """
    return config["unitInformation"][unit_index]["shorthand"]


# unitInformation indices — match action_frame_utils.
WALL_IDX = 0
SUPPORT_IDX = 1
TURRET_IDX = 2


# ---------------------------------------------------------------------------
# Coordinate / map helpers
# ---------------------------------------------------------------------------

ARENA_SIZE = 28
HALF_ARENA = 14


def _in_our_half(x: int, y: int) -> bool:
    """True iff (x, y) is on our (bottom) half of the diamond.

    Bottom half: 0 <= y < 14, with x bounded by the diamond geometry
    (13 - y) <= x <= (14 + y).
    """
    if not (0 <= x < ARENA_SIZE and 0 <= y < HALF_ARENA):
        return False
    return (HALF_ARENA - 1 - y) <= x <= (HALF_ARENA + y)


def _location_is_buildable(game_state, x: int, y: int) -> bool:
    """Conservative pre-filter: in-bounds, in our half, no occupant."""
    if not _in_our_half(x, y):
        return False
    try:
        if game_state.contains_stationary_unit([x, y]):
            return False
    except Exception:  # noqa: BLE001 — defensive against bad GameState
        return False
    return True


# ---------------------------------------------------------------------------
# Archetype loading
# ---------------------------------------------------------------------------

def load_archetype(archetype_path: str) -> List[Dict[str, Any]]:
    """Load and validate an archetype JSON file.

    Returns the list of placements sorted by (priority asc, original
    order). Raises ``ValueError`` if the file is malformed.
    """
    with open(archetype_path, "r") as f:
        data = json.load(f)
    placements = data.get("placements")
    if not isinstance(placements, list):
        raise ValueError(f"Archetype {archetype_path}: missing 'placements' list")
    # Stable sort preserves original order on tie.
    indexed = list(enumerate(placements))
    indexed.sort(key=lambda kv: (int(kv[1].get("priority", 999)), kv[0]))
    return [kv[1] for kv in indexed]


# ---------------------------------------------------------------------------
# Task 2 — build_default_defences
# ---------------------------------------------------------------------------

def build_default_defences(
    game_state,
    archetype_path: str,
    config: Optional[Dict[str, Any]] = None,
    min_sp_to_save: float = 0.0,
) -> Tuple[int, int, float]:
    """Walk the archetype JSON in priority order, spawning + upgrading.

    Returns ``(spawned_count, upgraded_count, sp_remaining)``.

    Citadel: Wall upgrade now costs SP (was free). We deduct via
    ``type_cost(unit, upgrade=True)`` which reads the live config.
    """
    cfg = config or game_state.config
    placements = load_archetype(archetype_path)

    spawned = 0
    upgraded = 0

    for p in placements:
        unit_name = p["unit"]
        x, y = p["loc"]
        is_upgrade = bool(p.get("upgrade", False))

        # Map JSON name to runtime shorthand
        if unit_name == "WALL":
            shorthand = _shorthand_for(cfg, WALL_IDX)
        elif unit_name == "SUPPORT":
            shorthand = _shorthand_for(cfg, SUPPORT_IDX)
        elif unit_name == "TURRET":
            shorthand = _shorthand_for(cfg, TURRET_IDX)
        else:
            continue  # unknown unit name

        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
        except Exception:  # noqa: BLE001
            break

        if is_upgrade:
            # Upgrade: structure must already exist at (x, y). Read the
            # SP cost of upgrade from the runtime config.
            try:
                cost = float(game_state.type_cost(shorthand, upgrade=True)[SP_IDX])
            except Exception:  # noqa: BLE001
                cost = 0.0
            if sp - cost < min_sp_to_save:
                break
            try:
                n = game_state.attempt_upgrade([x, y])
            except Exception:  # noqa: BLE001
                n = 0
            if n:
                upgraded += int(n)
        else:
            # Spawn: requires a buildable tile.
            if not _location_is_buildable(game_state, x, y):
                continue
            try:
                cost = float(game_state.type_cost(shorthand)[SP_IDX])
            except Exception:  # noqa: BLE001
                cost = 0.0
            if sp - cost < min_sp_to_save:
                break
            try:
                n = game_state.attempt_spawn(shorthand, [x, y])
            except Exception:  # noqa: BLE001
                n = 0
            if n:
                spawned += int(n)

    try:
        sp_remaining = float(game_state.get_resource(SP_IDX, 0))
    except Exception:  # noqa: BLE001
        sp_remaining = 0.0
    return spawned, upgraded, sp_remaining


# ---------------------------------------------------------------------------
# Task 3 — edge_block_and_remove
# ---------------------------------------------------------------------------

# Our spawn-edge tiles. The two diagonals BOTTOM_LEFT (y=x to [0,13]) and
# BOTTOM_RIGHT (y=27-x to [27,13]). For "block & remove" we only care
# about the y=13 corner tiles — those are the ones that block scout
# paths into our edges.
_EDGE_BLOCK_TILES_LEFT = [[0, 13], [1, 13]]
_EDGE_BLOCK_TILES_RIGHT = [[27, 13], [26, 13]]
EDGE_BLOCK_TILES = _EDGE_BLOCK_TILES_LEFT + _EDGE_BLOCK_TILES_RIGHT


def edge_block_and_remove(game_state, config: Optional[Dict[str, Any]] = None) -> int:
    """Lostkids "spawn wall + immediately attempt_remove" same-turn trick.

    The engine accepts attempt_remove on a unit spawned the same turn;
    the wall blocks attacks during the action phase, then the removal
    queue refunds it ~80-90% next turn.

    Returns count of edge tiles where a wall was placed.

    NOTE: We've verified empirically (Lostkids' bestof 20-0 sweep) that
    same-turn spawn-then-remove preserves the wall for THIS turn's
    action phase. If a future engine change breaks this, the function
    is still safe — it just won't help that turn.
    """
    cfg = config or game_state.config
    wall = _shorthand_for(cfg, WALL_IDX)
    placed = 0
    for tile in EDGE_BLOCK_TILES:
        x, y = tile
        try:
            if game_state.contains_stationary_unit(tile):
                continue
        except Exception:  # noqa: BLE001
            continue
        if not _in_our_half(x, y):
            continue
        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
            cost = float(game_state.type_cost(wall)[SP_IDX])
        except Exception:  # noqa: BLE001
            break
        if sp < cost:
            break
        try:
            n = game_state.attempt_spawn(wall, tile)
        except Exception:  # noqa: BLE001
            n = 0
        if n:
            placed += int(n)
            try:
                game_state.attempt_remove(tile)
            except Exception:  # noqa: BLE001
                pass
    return placed


# ---------------------------------------------------------------------------
# Task 4 — refund_low_health_structures
# ---------------------------------------------------------------------------

def _our_structure_locations(game_state) -> List[Tuple[int, int]]:
    """All [x,y] tiles in our half currently holding our structure.

    O(half-arena-tile-count) ~ 196 tiles; cheap relative to a turn.
    """
    locs: List[Tuple[int, int]] = []
    for x in range(ARENA_SIZE):
        for y in range(HALF_ARENA):
            if not _in_our_half(x, y):
                continue
            try:
                u = game_state.contains_stationary_unit([x, y])
            except Exception:  # noqa: BLE001
                continue
            if u and getattr(u, "player_index", 0) == 0:
                locs.append((x, y))
    return locs


def refund_low_health_structures(
    game_state,
    wall_threshold: float = 0.5,
    turret_threshold: float = 0.3,
    config: Optional[Dict[str, Any]] = None,
) -> int:
    """Mark for removal any wall < wall_threshold HP or turret < turret_threshold HP.

    Returns count of structures queued for removal. The engine refunds
    ~80% of base SP cost (90% if not upgraded; varies by config) at
    next turn's resource phase.
    """
    cfg = config or game_state.config
    wall_sh = _shorthand_for(cfg, WALL_IDX)
    turret_sh = _shorthand_for(cfg, TURRET_IDX)
    refunded = 0
    for x, y in _our_structure_locations(game_state):
        try:
            u = game_state.contains_stationary_unit([x, y])
        except Exception:  # noqa: BLE001
            continue
        if not u:
            continue
        try:
            ratio = float(u.health) / float(u.max_health) if u.max_health else 1.0
        except Exception:  # noqa: BLE001
            continue
        utype = getattr(u, "unit_type", None)
        do_refund = False
        if utype == wall_sh and ratio < wall_threshold:
            do_refund = True
        elif utype == turret_sh and ratio < turret_threshold:
            do_refund = True
        if do_refund:
            try:
                n = game_state.attempt_remove([x, y])
            except Exception:  # noqa: BLE001
                n = 0
            if n:
                refunded += int(n)
    return refunded


# ---------------------------------------------------------------------------
# Task 5 — max_heap_repair (FUNNEL-style, with cost-vector bug FIXED)
# ---------------------------------------------------------------------------

def max_heap_repair(
    game_state,
    config: Optional[Dict[str, Any]] = None,
    max_repairs: int = 32,
) -> int:
    """Repair the most-damaged friendly structures, max-heap by damage.

    Algorithm:
      1. Walk our structures.
      2. Compute ``damage = max_health - current_health``; skip if 0.
      3. Push (-damage, x, y, type) onto a min-heap (== max-heap by damage).
      4. Pop in damage-order, ``attempt_remove`` each tile so that the
         engine refunds it next turn. The planner may then re-spawn at
         the same tile via the archetype walk.

    BUG FIX vs FUNNEL's max_heap_repair (line ~327 in
    research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/algo_strategy.py):
    FUNNEL uses ``cost_vector[0]`` to read the SP cost. In Citadel's
    runtime config the ``type_cost`` tuple is ``(SP_cost, MP_cost)``
    indexed as ``[SP_IDX=0, MP_IDX=1]`` — and ``[0]`` happens to be SP
    for a structure (both Lostkids and FUNNEL share that quirk). The
    *real* FUNNEL bug is that they compute a STRUCTURE's cost using
    ``cost2`` (the MP cost) for the comparison against available SP.
    Our fix: explicitly read ``type_cost(unit)[SP_IDX]``.

    Returns count of structures queued for repair (i.e., removed).
    """
    cfg = config or game_state.config

    heap: List[Tuple[float, int, int, str]] = []
    for x, y in _our_structure_locations(game_state):
        try:
            u = game_state.contains_stationary_unit([x, y])
        except Exception:  # noqa: BLE001
            continue
        if not u:
            continue
        try:
            dmg = float(u.max_health) - float(u.health)
        except Exception:  # noqa: BLE001
            continue
        if dmg <= 0:
            continue
        utype = getattr(u, "unit_type", "")
        # Push -dmg so heapq (min-heap) returns largest dmg first.
        heap.append((-dmg, int(x), int(y), str(utype)))

    heapq.heapify(heap)

    repaired = 0
    while heap and repaired < max_repairs:
        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
        except Exception:  # noqa: BLE001
            break
        if sp <= 0:
            break

        neg_dmg, x, y, utype = heapq.heappop(heap)
        # FIX: use SP_IDX (== 0) explicitly. FUNNEL's port used
        # `cost_vector[0]` literally without naming the index, leaving a
        # latent bug if cost_vector ever swapped order. We're explicit.
        try:
            cost = float(game_state.type_cost(utype)[SP_IDX])
        except Exception:  # noqa: BLE001
            cost = 0.0
        # Repair = remove (refund) so we can re-place. We don't deduct
        # any SP this turn for the remove; the SP cost is in NEXT turn
        # when we re-spawn. We compare against current SP only as a
        # sanity gate so we don't queue more repairs than we can
        # plausibly fund.
        if cost > 0 and sp < cost * 0.5:
            # Less than half a unit's cost remaining this turn — not
            # enough headroom to plan a re-spawn next turn. Stop here.
            break
        try:
            n = game_state.attempt_remove([x, y])
        except Exception:  # noqa: BLE001
            n = 0
        if n:
            repaired += int(n)
    return repaired


# ---------------------------------------------------------------------------
# Task 6 — probabilistic_placement (GRETCHEN-style)
# ---------------------------------------------------------------------------

def _all_buildable_tiles(game_state) -> List[Tuple[int, int]]:
    out: List[Tuple[int, int]] = []
    for x in range(ARENA_SIZE):
        for y in range(HALF_ARENA):
            if _location_is_buildable(game_state, x, y):
                out.append((x, y))
    return out


def _turret_value(
    game_state, x: int, y: int, config: Dict[str, Any], turret_sh: str
) -> float:
    """Estimated value of placing a base turret at (x, y).

    Heuristic: damage_potential * coverage * (HP / max_HP).
    Coverage = number of attacker-tile candidates within attack range
    (we approximate by a simple radius check on a 5x5 box). HP/max_HP
    is 1.0 at placement and acts as a "newly placed" weight so this
    function is easy to extend later when feeding existing-unit data.
    """
    info = config["unitInformation"][TURRET_IDX]
    dmg = float(info.get("attackDamageWalker", 0.0))
    rng = float(info.get("attackRange", 2.5))
    hp_ratio = 1.0
    # Coverage: count tiles inside attack range that are within the
    # arena (cheap proxy for "things this turret can shoot").
    coverage = 0
    r2 = rng * rng
    rad = int(math.ceil(rng))
    for dx in range(-rad, rad + 1):
        for dy in range(-rad, rad + 1):
            if dx * dx + dy * dy <= r2:
                xx, yy = x + dx, y + dy
                if 0 <= xx < ARENA_SIZE and 0 <= yy < ARENA_SIZE:
                    coverage += 1
    return dmg * coverage * hp_ratio


def _wall_value(game_state, x: int, y: int, config: Dict[str, Any]) -> float:
    """Estimated value of placing a wall at (x, y).

    Heuristic: 1 / (1 + distance-to-base-line) * HP buffer. Walls farther
    forward (high y) are worth more because they protect Supports below.
    We approximate distance-to-base-line as (HALF_ARENA - 1 - y).
    """
    info = config["unitInformation"][WALL_IDX]
    hp = float(info.get("startHealth", 60.0))
    forward_bonus = 1.0 / (1.0 + (HALF_ARENA - 1 - y))
    # Larger HP = larger buffer for whatever's behind. Normalize ~60 -> 1.
    return forward_bonus * (hp / 60.0)


def _support_value(
    game_state, x: int, y: int, support_weight: float, config: Dict[str, Any]
) -> float:
    """Estimated value of placing a (potential) Support at (x, y).

    Citadel upgraded shield = ``1 + 0.7 * Y`` per unit, range 7. Higher
    Y = much more shield, so value grows linearly with Y.
    Per the build-plan note, support_weight=25 is the Citadel-tuned
    starting weight (vs base-game GRETCHEN's 100x).
    """
    upgrade = config["unitInformation"][SUPPORT_IDX].get("upgrade", {})
    shield_bonus_per_y = float(upgrade.get("shieldBonusPerY", 0.0))
    shield_per_unit = float(upgrade.get("shieldPerUnit", 0.0))
    # Per-Scout shield from a single upgraded Support at this Y:
    per_scout_shield = shield_per_unit + shield_bonus_per_y * float(y)
    return support_weight * per_scout_shield


def probabilistic_placement(
    game_state,
    support_weight: float = 25.0,
    n_samples: int = 200,
    config: Optional[Dict[str, Any]] = None,
    rng: Optional[random.Random] = None,
) -> int:
    """GRETCHEN-style: sample N candidate placements, value-weighted.

    Process:
      1. Collect all buildable tiles in our half.
      2. For each tile, score the three candidate placements (Wall,
         Turret, Support) using the value heuristics above.
      3. Pick the top tile-types until SP runs out.

    ``support_weight`` defaults to 25 — see module docstring rationale.

    Returns count of placements made.
    """
    cfg = config or game_state.config
    if rng is None:
        rng = random.Random()

    wall_sh = _shorthand_for(cfg, WALL_IDX)
    turret_sh = _shorthand_for(cfg, TURRET_IDX)
    support_sh = _shorthand_for(cfg, SUPPORT_IDX)

    candidates: List[Tuple[float, str, int, int]] = []
    tiles = _all_buildable_tiles(game_state)
    # Sample n_samples random tiles (no replacement). Reduces compute on
    # near-empty boards while still covering the high-value back rows.
    if len(tiles) > n_samples:
        tiles = rng.sample(tiles, n_samples)
    for x, y in tiles:
        # Turret value
        v_t = _turret_value(game_state, x, y, cfg, turret_sh)
        # Wall value (only sensible at front rows; cheap heuristic)
        v_w = _wall_value(game_state, x, y, cfg)
        # Restrict types to roles by Y to avoid placing Turrets in the
        # back row. Supports are deliberately NOT placed here: this
        # function only spawns BASE structures, but base Supports have
        # 1 HP and 3 shield — they die in a single attack frame and
        # provide negligible shielding. Replay analysis showed athena
        # placing 38 useless base Supports per game while paying 4 SP
        # each. Upgraded Supports are placed via the archetype only.
        if y >= 9:
            candidates.append((v_t, turret_sh, x, y))
        if y >= 11:
            candidates.append((v_w, wall_sh, x, y))

    candidates.sort(key=lambda c: -c[0])

    placed = 0
    used_tiles: set = set()
    for value, shorthand, x, y in candidates:
        if value <= 0:
            break
        if (x, y) in used_tiles:
            continue
        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
            cost = float(game_state.type_cost(shorthand)[SP_IDX])
        except Exception:  # noqa: BLE001
            break
        # Skip this candidate if too expensive — keep trying cheaper ones.
        # Previously this was `break`, which abandoned the entire placement
        # loop the moment a high-value Support (4 SP) was unaffordable,
        # leaving cheaper Turrets (2 SP) and Walls (1 SP) unplaced.
        if sp < cost:
            continue
        try:
            n = game_state.attempt_spawn(shorthand, [x, y])
        except Exception:  # noqa: BLE001
            n = 0
        if n:
            placed += int(n)
            used_tiles.add((x, y))
    return placed


# ---------------------------------------------------------------------------
# Task 7 — reactive_to_breach
# ---------------------------------------------------------------------------

def reactive_to_breach(
    game_state,
    breach_tracker,
    threshold: float = 1.0,
    config: Optional[Dict[str, Any]] = None,
) -> int:
    """Patch turret coverage at recently-breached tiles.

    For each tile (x,y) in ``breach_tracker.per_tile`` whose accumulated
    decayed weight >= ``threshold``:
      - If our half tile and not occupied: place a base turret at the
        nearest valid tile within range of (x,y).
      - Else: try to upgrade the nearest existing friendly turret.

    Returns count of reactive placements/upgrades.
    """
    cfg = config or game_state.config
    turret_sh = _shorthand_for(cfg, TURRET_IDX)
    info = cfg["unitInformation"][TURRET_IDX]
    base_range = float(info.get("attackRange", 2.5))

    # Collect breach hot tiles within our half (the breach tracker also
    # records tiles in opponent half if WE breach; we filter to OUR
    # half here — those are the tiles WE need to defend).
    hot: List[Tuple[Tuple[int, int], float]] = []
    try:
        per_tile = getattr(breach_tracker, "per_tile", {}) or {}
    except Exception:  # noqa: BLE001
        per_tile = {}
    for tile, weight in per_tile.items():
        if weight < threshold:
            continue
        x, y = tile
        if not _in_our_half(int(x), int(y)):
            continue
        hot.append((tile, float(weight)))
    hot.sort(key=lambda kv: -kv[1])

    actions = 0
    for (bx, by), _w in hot:
        # Find nearest buildable tile within turret range to cover (bx,by).
        best: Optional[Tuple[float, int, int]] = None
        rad = int(math.ceil(base_range)) + 1
        for dx in range(-rad, rad + 1):
            for dy in range(-rad, rad + 1):
                cx, cy = int(bx) + dx, int(by) + dy
                if not _location_is_buildable(game_state, cx, cy):
                    continue
                d2 = dx * dx + dy * dy
                if d2 > base_range * base_range + 1e-9:
                    continue
                if best is None or d2 < best[0]:
                    best = (d2, cx, cy)
        if best is not None:
            _, cx, cy = best
            try:
                sp = float(game_state.get_resource(SP_IDX, 0))
                cost = float(game_state.type_cost(turret_sh)[SP_IDX])
            except Exception:  # noqa: BLE001
                break
            if sp >= cost:
                try:
                    n = game_state.attempt_spawn(turret_sh, [cx, cy])
                except Exception:  # noqa: BLE001
                    n = 0
                if n:
                    actions += int(n)
                    continue
        # Fall through: try to upgrade an EXISTING friendly turret near
        # the breach tile.
        nearest_turret: Optional[Tuple[float, int, int]] = None
        rad2 = int(math.ceil(base_range)) + 1
        for dx in range(-rad2, rad2 + 1):
            for dy in range(-rad2, rad2 + 1):
                cx, cy = int(bx) + dx, int(by) + dy
                if not (0 <= cx < ARENA_SIZE and 0 <= cy < HALF_ARENA):
                    continue
                try:
                    u = game_state.contains_stationary_unit([cx, cy])
                except Exception:  # noqa: BLE001
                    continue
                if not u or getattr(u, "unit_type", "") != turret_sh:
                    continue
                if getattr(u, "upgraded", False):
                    continue
                d2 = dx * dx + dy * dy
                if nearest_turret is None or d2 < nearest_turret[0]:
                    nearest_turret = (d2, cx, cy)
        if nearest_turret is not None:
            _, cx, cy = nearest_turret
            try:
                sp = float(game_state.get_resource(SP_IDX, 0))
                ucost = float(game_state.type_cost(turret_sh, upgrade=True)[SP_IDX])
            except Exception:  # noqa: BLE001
                break
            if sp >= ucost:
                try:
                    n = game_state.attempt_upgrade([cx, cy])
                except Exception:  # noqa: BLE001
                    n = 0
                if n:
                    actions += int(n)
    return actions
