"""SimCore — Python reference implementation of engine.jar's action phase.

Frame-loop order (verified from engine.jar bytecode; see
memory/engine_semantics.md):

    clear_destroyed → move → collision → shield_decay → shield_give
      → breach → self_destruct → attack → clear_destroyed

One frame = one "step". Mobile units with speed < 1 move every 1/speed frames
via a float accumulator (buildup += speed; move when buildup >= 0.9999;
buildup -= 1.0).

Simulation ends when no live mobile units remain OR a safety frame cap is
hit. The engine has no hard cap; we pick 200 (a 100-turn game with a
slow-moving unit stuck couldn't legitimately exceed this).

Key correctness gates (agent-verified against bytecode):
  * Breach requires `finished_navigating AND reached_target`.
  * Self-destruct requires `finished_navigating AND NOT reached_target AND
    steps_taken >= selfDestructStepsRequired`.
  * Damage flow: shield (all layers) absorbs; if damage > total shield, all
    shields wiped AND remainder hits HP.
  * `shielded_already` set per Support is lifelong — never re-shields a unit.
  * Collisions DO NOT destroy units; they only populate collidedWithThisTurn.
    Mutual destruction occurs only via self-destruct AOE.
  * Breach refunds SP to the breacher via metal_for_breach.
  * Targeting: distance → hp+shield → |y - edge| → |x - 13.5|.
"""

from __future__ import annotations

import math
from collections import deque
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Set, Tuple  # noqa: F401

from .config import (
    IDX_DEMOLISHER,
    IDX_INTERCEPTOR,
    IDX_SCOUT,
    IDX_SUPPORT,
    IDX_TURRET,
    IDX_WALL,
    MOBILE_TYPES,
    STRUCTURE_TYPES,
    MobileSpec,
    SimConfig,
    StructureSpec,
)
from .map import (
    ARENA,
    EDGE_BOTTOM_LEFT,
    EDGE_BOTTOM_RIGHT,
    EDGE_TOP_LEFT,
    EDGE_TOP_RIGHT,
    HALF,
    edge_tiles,
    euclidean,
    on_diamond,
    spawn_tile_target_edge,
)
from .pathfinder import PathFinder, make_pathfinders, _HORIZONTAL, _VERTICAL, _SPAWNED
from .state import (
    ActionResult,
    Mobile,
    PlayerStats,
    SimState,
    Structure,
)

# ----------------------------------------------------------------- helpers

_MOVE_THRESHOLD = 0.9999  # engine's accumulator threshold


def _is_structure(type_idx: int) -> bool:
    return type_idx in STRUCTURE_TYPES


def _edge_set(edge: int) -> frozenset:
    return frozenset(edge_tiles(edge))


# precompute edge sets once — used in hot path
_EDGE_SET_CACHE = {e: _edge_set(e) for e in (EDGE_TOP_RIGHT, EDGE_TOP_LEFT, EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT)}
_EDGE_LISTS_CACHE = {e: edge_tiles(e) for e in (EDGE_TOP_RIGHT, EDGE_TOP_LEFT, EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT)}


def _distance(a: Tuple[int, int], b: Tuple[int, int]) -> float:
    dx = a[0] - b[0]; dy = a[1] - b[1]
    return math.sqrt(dx * dx + dy * dy)


def _target_edges_mobile(player: int) -> Tuple[int, int]:
    if player == 1:
        return (EDGE_TOP_LEFT, EDGE_TOP_RIGHT)
    return (EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT)


def _blocked_set(state: SimState) -> Set[Tuple[int, int]]:
    return set(state.structures.keys())


def _ensure_pathfinders(state: SimState) -> None:
    """Initialize 4 per-edge PathFinder instances from current structures."""
    if state.pathfinders is not None:
        return
    walls = [s.xy for s in state.structures.values()]
    state.pathfinders = make_pathfinders(
        dimension=ARENA,
        walls=walls,
        edge_to_perfects=_EDGE_LISTS_CACHE,
    )


# ----------------------------------------------------------------- deploy

def apply_deploy_actions(state: SimState, config: SimConfig,
                         p1_spawns: List[list], p1_upgrades: List[list],
                         p2_spawns: List[list], p2_upgrades: List[list],
                         ordered_events: Optional[List] = None) -> None:
    """Mutate state to post-deploy: spawn structures, apply upgrades, spawn
    mobile units. Structure ownership is enforced by half-board.

    If `ordered_events` is provided (list of (player, [x,y,type]) tuples
    in the exact engine-issued order), use it for spawn-order fidelity
    on both structures and mobiles. Upgrades are still processed per-
    player after all structures on that side are placed.

    Without `ordered_events`, falls back to (p1_spawns, p2_spawns) order.
    """
    if ordered_events is None:
        ordered_events = [(1, s) for s in p1_spawns] + [(2, s) for s in p2_spawns]

    # Structures in engine-issued order
    for player, loc in ordered_events:
        x, y, tidx = int(loc[0]), int(loc[1]), int(loc[2])
        if not on_diamond(x, y):
            continue
        if tidx not in STRUCTURE_TYPES:
            continue
        owner = 1 if y < HALF else 2
        if owner != player:
            continue
        if (x, y) in state.structures:
            continue
        spec = config.structure_spec(tidx, upgraded=False)
        state.structures[(x, y)] = Structure(
            xy=(x, y), type_idx=tidx, upgraded=False,
            hp=spec.hp, uid=state.alloc_id(), player=player,
        )
        if state.pathfinders is not None:
            for pf in state.pathfinders.values():
                pf.put(x, y)

    # Upgrades — engine's SpawnUnitsSystem (off 287–855):
    #   currentHP += (upgradeMaxHP − baseMaxHP), clamped to upgradeMaxHP.
    for player, ups in ((1, p1_upgrades), (2, p2_upgrades)):
        for loc in ups:
            x, y = int(loc[0]), int(loc[1])
            s = state.structures.get((x, y))
            if s is None or s.player != player or s.upgraded:
                continue
            base_spec = config.structure_spec(s.type_idx, upgraded=False)
            upg_spec = config.structure_spec(s.type_idx, upgraded=True)
            s.upgraded = True
            s.hp = min(upg_spec.hp, s.hp + (upg_spec.hp - base_spec.hp))

    # Mobile units in engine-issued order
    for player, loc in ordered_events:
        x, y, tidx = int(loc[0]), int(loc[1]), int(loc[2])
        if tidx not in MOBILE_TYPES:
            continue
        spec = config.mobile_spec(tidx)
        target_edge = spawn_tile_target_edge((x, y))
        state.mobiles.append(Mobile(
            xy=(x, y), type_idx=tidx,
            hp=spec.hp, shield=0.0,
            uid=state.alloc_id(), player=player,
            spawn_xy=(x, y), target_edge=target_edge,
        ))


# -------------------------------------------------------- per-frame systems

def system_move(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """Exact port of NavigateToEdgeSystem.moveComponents (decompiled Java
    in research/engine_decompiled/sources/.../NavigateToEdgeSystem.java:35).

    For each navigating mobile:
      1. `nav.currentMoveBuildup += nav.speed`
      2. if buildup < 0.9999 → skip frame
      3. `buildup -= 1.0`
      4. `nextTile = pathfinder.getStep(pos.x, pos.y, nav.lastMove)`
      5. `nav.lastMove = (nextTile.y == pos.y) ? HORIZONTAL : VERTICAL`
      6. if `delta == (0,0)`:
           `finished_navigating = true`, `speed = 0`, `navigating = false`;
           `reached_target = true` iff position matches any navigation target
      7. else move body to nextTile, increment stepsTaken, emit GlobalMove.
    """
    _ensure_pathfinders(state)
    pathfinders = state.pathfinders
    for m in state.mobiles:
        if m.hp <= 0 or m.finished_navigating:
            continue
        spec = config.mobile_spec(m.type_idx)
        m.move_buildup += spec.speed
        if m.move_buildup < _MOVE_THRESHOLD:
            continue
        m.move_buildup -= 1.0
        pf: PathFinder = pathfinders[m.target_edge]
        nx, ny = pf.get_step(m.xy[0], m.xy[1], m.last_move)
        # lastMove: horizontal=1 if y unchanged, else vertical=2
        m.last_move = _HORIZONTAL if ny == m.xy[1] else _VERTICAL
        dx = nx - m.xy[0]
        dy = ny - m.xy[1]
        if dx == 0 and dy == 0:
            m.finished_navigating = True
            # reached_target iff current position is in the SPECIFIC
            # nav-targets list for this mobile's assigned edge (matches
            # NavigateToEdgeSystem.java:50-54 — only the unit's own
            # `navigationTargetLocations`, not the union of p's two edges).
            m.reached_target = m.xy in _EDGE_SET_CACHE[m.target_edge]
            continue
        prev = m.xy
        m.xy = (nx, ny)
        m.steps_taken += 1
        events.append({"type": "move", "uid": m.uid, "from": prev, "to": m.xy})


def system_collision(state: SimState) -> None:
    """CollisionSystem.runCollisionSystem.

    Engine doesn't destroy units here — only populates per-frame collision
    lists. Since our SimCore doesn't track sensor overlap events for
    shield/self-destruct (we compute range at each system directly via
    euclidean distance), this system is a no-op. Kept as an explicit marker
    in the frame-order so the ordering contract is visible.
    """
    pass


def system_shield_decay(state: SimState, config: SimConfig) -> None:
    """ShieldSystem.processShieldDecay. Citadel config has shieldDecay=0 —
    no-op. Hook preserved for future rule changes."""
    # Engine iterates all HealthComponents and applies shieldDecayPerFrame
    # to each shield layer. Our model collapses shields into a single float
    # so decay would just subtract from m.shield. For now shield_decay is 0.
    pass


def system_shield_give(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """ShieldSystem.processShieldGiveSystem.

    For each live Support with shieldPerUnit > 0, find friendly mobile units
    within shield_range. Apply shield amount once per (support, mobile) pair
    across the entire action phase (engine's shieldedAlready HashSet).

    Shield amount (verified via bytecode):
      distance_from_mid = abs(13.5 - support.y) - 0.5
      amount = shieldPerUnit + (13.0 - distance_from_mid) * bonusShieldPerY
             = shieldPerUnit + (13.5 - abs(13.5 - support.y)) * bonusShieldPerY
    """
    for s in state.structures.values():
        if s.type_idx != IDX_SUPPORT or s.hp <= 0:
            continue
        spec = config.structure_spec(IDX_SUPPORT, upgraded=s.upgraded)
        if spec.shield_per_unit <= 0:
            continue
        r = spec.shield_range
        y = s.xy[1]
        shield_amount = spec.shield_per_unit + (13.5 - abs(13.5 - y)) * spec.shield_bonus_per_y
        for m in state.mobiles:
            if m.hp <= 0 or m.player != s.player:
                continue
            if m.uid in s.shielded_already:
                continue
            if _distance(s.xy, m.xy) > r + 1e-9:
                continue
            m.shield += shield_amount
            s.shielded_already.add(m.uid)
            events.append({"type": "shield", "support_uid": s.uid,
                           "target_uid": m.uid, "amount": shield_amount})


def system_breach(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """BreachSystem.breachProcess.

    Gate: finished_navigating AND reached_target.
    Effects: apply breach_damage to the ENEMY player's HP, refund
    metal_for_breach SP to OUR player, destroy the unit.
    """
    for m in state.mobiles:
        if m.hp <= 0:
            continue
        if not (m.finished_navigating and m.reached_target):
            continue
        spec = config.mobile_spec(m.type_idx)
        enemy = state.player_stats(state.enemy_player(m.player))
        enemy.hp -= spec.breach_damage
        own = state.player_stats(m.player)
        own.sp += spec.metal_for_breach
        events.append({"type": "breach", "xy": m.xy, "dmg": spec.breach_damage,
                       "attacker_type": m.type_idx, "attacker_uid": m.uid,
                       "attacker_player": m.player})
        m.hp = 0  # destroyed on same frame


def _apply_damage(target_hp: float, target_shield: float, dmg: float) -> Tuple[float, float]:
    """Engine damage flow: if dmg <= shield_total → absorb onto shields; else
    shields wiped AND remainder hits HP.
    Returns (new_hp, new_shield)."""
    if dmg <= 0:
        return target_hp, target_shield
    if target_shield >= dmg:
        return target_hp, target_shield - dmg
    return target_hp - (dmg - target_shield), 0.0


def system_self_destruct(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """SelfDestructSystem.processSelfDestructs.

    Gate from agent spec & bytecode: `finished_navigating AND NOT
    reached_target AND steps_taken >= selfDestructStepsRequired`. Flags
    were already set by system_move this frame (or a prior frame) via
    the delta=0 return from get_step.

    Effects: deal self_destruct_damage_walker to enemy mobiles in
    self_destruct_range; deal self_destruct_damage_tower to enemy
    structures in range; destroy the self-destructor. Units that are
    stuck but haven't walked 5 tiles are silently removed without AOE.
    """
    for m in state.mobiles:
        if m.hp <= 0:
            continue
        if not (m.finished_navigating and not m.reached_target):
            continue
        spec = config.mobile_spec(m.type_idx)
        if m.steps_taken >= spec.self_destruct_steps_required:
            r = spec.self_destruct_range
            dmg_walker = spec.self_destruct_damage_walker
            dmg_tower = spec.self_destruct_damage_tower
            for other in state.mobiles:
                if other is m or other.hp <= 0 or other.player == m.player:
                    continue
                if _distance(m.xy, other.xy) > r + 1e-9:
                    continue
                other.hp, other.shield = _apply_damage(other.hp, other.shield, dmg_walker)
                events.append({"type": "damage", "xy": other.xy, "dmg": dmg_walker,
                               "victim_uid": other.uid, "source_uid": m.uid})
            for s in list(state.structures.values()):
                if s.hp <= 0 or s.player == m.player:
                    continue
                if _distance(m.xy, s.xy) > r + 1e-9:
                    continue
                s.hp -= dmg_tower
                events.append({"type": "damage", "xy": s.xy, "dmg": dmg_tower,
                               "victim_uid": s.uid, "source_uid": m.uid})
            events.append({"type": "selfDestruct", "xy": m.xy, "uid": m.uid,
                           "player": m.player})
        m.hp = 0


def _pick_target(attacker_xy: Tuple[int, int], attacker_player: int,
                 candidates: list) -> Optional[object]:
    """Exact port of TargetAndAttackSystem.pickUnit (decompiled Java).

    Candidates is a list of target objects (Structure or Mobile); this
    function returns the picked one or None.

    Priority (all floats; from pickUnit source):
      1. closer  (strict <)
      2. equalDistance AND lessHP
      3. equalDistance AND equalHP AND closerToStart (smaller |y - start|)
      4. equalDistance AND equalHP AND equalToStart AND fartherFromCenter
         (LARGER |x - 13.5|)  ← counter-intuitive
      5. equalDistance AND equalHP AND equalToStart AND equalFromCenter
         AND gameObjectIDLarger (LARGER uid as int)

    Dead candidates (hp <= 0) are skipped.
    """
    if not candidates:
        return None
    start_pos = 0.0 if attacker_player == 1 else 28.0
    closest_distance = 1.0e10
    closest_health = 1.0e10
    distance_to_player_start = 1.0e10
    distance_to_center = 0.0
    target_gid = ""
    best = None
    for cand in candidates:
        hp = cand.hp
        if hp <= 0.0:
            continue
        # distance
        dx = cand.xy[0] - attacker_xy[0]
        dy = cand.xy[1] - attacker_xy[1]
        new_dist = (dx * dx + dy * dy) ** 0.5
        closer = new_dist < closest_distance
        equal_distance = new_dist == closest_distance
        # hp+shield
        new_hp = hp + getattr(cand, "shield", 0.0)
        less_hp = new_hp < closest_health
        equal_hp = new_hp == closest_health
        # |y - start_pos|
        new_dist_start = abs(cand.xy[1] - start_pos)
        closer_to_start = new_dist_start < distance_to_player_start
        equal_to_start = new_dist_start == distance_to_player_start
        # |x - 13.5|
        new_dist_center = abs(cand.xy[0] - 13.5)
        farther_from_center = new_dist_center > distance_to_center
        equal_from_center = new_dist_center == distance_to_center
        # gid compare (as int)
        new_gid = cand.uid
        # Empty string means no winner yet → any gid wins
        try:
            if target_gid == "":
                game_object_id_larger = True
            else:
                game_object_id_larger = int(new_gid) > int(target_gid)
        except (TypeError, ValueError):
            # Non-numeric uids shouldn't happen in our sim (state.alloc_id
            # returns numeric strings), but fall back safely.
            game_object_id_larger = str(new_gid) > str(target_gid)
        # Update condition matches decompiled bytecode:
        #   (closer
        #    OR (equal_dist AND less_hp)
        #    OR (equal_dist AND equal_hp AND closer_to_start)
        #    OR (equal_dist AND equal_hp AND equal_to_start AND farther_from_center))
        #   OR (equal_dist AND equal_hp AND equal_to_start AND equal_from_center
        #       AND gid_larger)
        first_cond = (closer
                      or (equal_distance and less_hp)
                      or (equal_distance and equal_hp and closer_to_start)
                      or (equal_distance and equal_hp and equal_to_start and farther_from_center))
        second_cond = (equal_distance and equal_hp and equal_to_start
                       and equal_from_center and game_object_id_larger)
        if not first_cond and not second_cond:
            continue
        # Update best
        best = cand
        closest_distance = new_dist
        closest_health = new_hp
        distance_to_player_start = new_dist_start
        distance_to_center = new_dist_center
        target_gid = new_gid
    return best


def system_attack(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """Exact port of TargetAndAttackSystem.processTargeting (decompiled).

    Engine behavior:
      * Iterate attackers in game-object insertion order (structures first,
        then mobiles, each in their own insertion order).
      * ATTACKER SNAPSHOT: attackers that were alive at the START of the
        attack phase all fire, even if killed by an earlier attacker in
        the same phase. The engine uses `gameObjectDestroy.accept(...)`
        which only queues for destruction — components stay enabled until
        the next clear_destroyed. A scout that dies mid-phase still emits
        its one attack event this frame.
      * Each attacker picks HIGH priority (walkers/mobiles) first; if no
        alive walker in range, falls back to LOW priority (towers/
        structures). Skipped entirely if no target in either list.
      * Damage applied IMMEDIATELY via dealDamageToHealthComponent.
    """
    # Snapshot attackers: anyone with hp > 0 at phase start fires.
    attacker_structs = [s for s in state.structures.values()
                        if s.hp > 0 and s.type_idx == IDX_TURRET]
    attacker_mobiles = [m for m in state.mobiles if m.hp > 0]

    def _fire(att_xy, att_player, att_uid, dmg_walker, dmg_tower, att_range):
        """Pick target (walkers first, then structures); apply damage."""
        r = att_range
        # Walker candidates in range
        walker_candidates = []
        if dmg_walker > 0:
            for other in state.mobiles:
                if other.hp <= 0 or other.player == att_player:
                    continue
                dx = other.xy[0] - att_xy[0]
                dy = other.xy[1] - att_xy[1]
                d_sq = dx * dx + dy * dy
                if d_sq > r * r + 1e-9:
                    continue
                walker_candidates.append(other)
        target = None
        is_walker = False
        if walker_candidates:
            target = _pick_target(att_xy, att_player, walker_candidates)
            is_walker = True
        if target is None and dmg_tower > 0:
            struct_candidates = []
            for s in state.structures.values():
                if s.hp <= 0 or s.player == att_player:
                    continue
                dx = s.xy[0] - att_xy[0]
                dy = s.xy[1] - att_xy[1]
                if dx * dx + dy * dy > r * r + 1e-9:
                    continue
                struct_candidates.append(s)
            target = _pick_target(att_xy, att_player, struct_candidates)
            is_walker = False
        if target is None:
            return
        if is_walker:
            dmg = dmg_walker
            new_hp, new_sh = _apply_damage(target.hp, target.shield, dmg)
            target.hp, target.shield = new_hp, new_sh
        else:
            dmg = dmg_tower
            target.hp -= dmg
        events.append({"type": "attack", "attacker_uid": att_uid,
                       "victim_uid": target.uid, "dmg": dmg,
                       "from_xy": att_xy, "to_xy": target.xy})

    # Turrets first
    for s in attacker_structs:
        spec = config.structure_spec(IDX_TURRET, upgraded=s.upgraded)
        if spec.attack_damage_walker <= 0 and spec.attack_damage_tower <= 0:
            continue
        _fire(s.xy, s.player, s.uid, spec.attack_damage_walker,
              spec.attack_damage_tower, spec.attack_range)

    # Mobile attackers
    for m in attacker_mobiles:
        spec = config.mobile_spec(m.type_idx)
        if spec.attack_damage_walker <= 0 and spec.attack_damage_tower <= 0:
            continue
        _fire(m.xy, m.player, m.uid, spec.attack_damage_walker,
              spec.attack_damage_tower, spec.attack_range)


def clear_destroyed(state: SimState, events: List[dict]) -> None:
    to_kill = [xy for xy, s in state.structures.items() if s.hp <= 0]
    for xy in to_kill:
        s = state.structures.pop(xy)
        if state.pathfinders is not None:
            for pf in state.pathfinders.values():
                pf.remove(xy[0], xy[1])
        events.append({"type": "death", "xy": xy, "uid": s.uid,
                       "type_idx": s.type_idx, "player": s.player, "self_destruct": False})
    survivors = []
    for m in state.mobiles:
        if m.hp > 0:
            survivors.append(m)
        else:
            sd = m.finished_navigating and not m.reached_target and \
                 m.steps_taken >= 5  # engine's stepsRequired
            events.append({"type": "death", "xy": m.xy, "uid": m.uid,
                           "type_idx": m.type_idx, "player": m.player,
                           "self_destruct": sd})
    state.mobiles = survivors


# --------------------------------------------------------------- top-level

def simulate_action_phase(
    state: SimState,
    config: SimConfig,
    max_frames: int = 200,
) -> ActionResult:
    """Run frames until no mobile units remain or max_frames hit.

    Pre-condition: state already reflects deploy-phase (structures + mobile
    spawns). Use apply_deploy_actions to build this from replay records.
    """
    p1_start_hp = state.p1.hp
    p2_start_hp = state.p2.hp
    p1_start_sp = state.p1.sp
    p2_start_sp = state.p2.sp

    frame_events: List[dict] = []
    f = 0
    while f < max_frames:
        clear_destroyed(state, frame_events)
        if not state.mobiles:
            break

        system_move(state, config, frame_events)
        system_collision(state)
        system_shield_decay(state, config)
        system_shield_give(state, config, frame_events)
        system_breach(state, config, frame_events)
        system_self_destruct(state, config, frame_events)
        system_attack(state, config, frame_events)

        f += 1

    clear_destroyed(state, frame_events)

    p1_dmg = max(0.0, p2_start_hp - state.p2.hp)
    p2_dmg = max(0.0, p1_start_hp - state.p1.hp)

    return ActionResult(
        final_state=state,
        p1_damage_dealt=p1_dmg,
        p2_damage_dealt=p2_dmg,
        structure_damage_by_player={1: 0.0, 2: 0.0},
        frame_events=frame_events,
        frame_count=f,
    )
