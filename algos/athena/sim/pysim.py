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
from typing import Dict, List, Optional, Set, Tuple

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
from .pathfinder import navigate
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


def _distance(a: Tuple[int, int], b: Tuple[int, int]) -> float:
    dx = a[0] - b[0]; dy = a[1] - b[1]
    return math.sqrt(dx * dx + dy * dy)


def _target_edges_mobile(player: int) -> Tuple[int, int]:
    if player == 1:
        return (EDGE_TOP_LEFT, EDGE_TOP_RIGHT)
    return (EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT)


def _target_edge_tiles(player: int) -> frozenset:
    if player == 1:
        return _EDGE_SET_CACHE[EDGE_TOP_LEFT] | _EDGE_SET_CACHE[EDGE_TOP_RIGHT]
    return _EDGE_SET_CACHE[EDGE_BOTTOM_LEFT] | _EDGE_SET_CACHE[EDGE_BOTTOM_RIGHT]


def _blocked_set(state: SimState) -> Set[Tuple[int, int]]:
    return set(state.structures.keys())


# ----------------------------------------------------------------- deploy

def apply_deploy_actions(state: SimState, config: SimConfig,
                         p1_spawns: List[list], p1_upgrades: List[list],
                         p2_spawns: List[list], p2_upgrades: List[list]) -> None:
    """Mutate state to post-deploy: spawn structures, apply upgrades, spawn
    mobile units. Structure ownership is enforced by half-board."""
    for player, spawns, ups in ((1, p1_spawns, p1_upgrades), (2, p2_spawns, p2_upgrades)):
        # Structures first
        for loc in spawns:
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
            state.structures_version += 1
        # Upgrades — engine's SpawnUnitsSystem (off 287–855):
        #   currentHP += (upgradeMaxHP − baseMaxHP), clamped to upgradeMaxHP.
        # So a damaged wall at 30/60 HP upgraded becomes 30 + (200-60) = 170/200,
        # NOT 200. We previously set hp = upgradeMaxHP which over-counted by up
        # to 40 HP per damaged-upgraded structure.
        for loc in ups:
            x, y = int(loc[0]), int(loc[1])
            s = state.structures.get((x, y))
            if s is None or s.player != player or s.upgraded:
                continue
            base_spec = config.structure_spec(s.type_idx, upgraded=False)
            upg_spec = config.structure_spec(s.type_idx, upgraded=True)
            s.upgraded = True
            s.hp = min(upg_spec.hp, s.hp + (upg_spec.hp - base_spec.hp))
        # Mobile units
        for loc in spawns:
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

def _recompute_path(m: Mobile, state: SimState) -> None:
    m.path = navigate(m.xy, m.target_edge, _blocked_set(state))
    m.path_version = state.structures_version


def system_move(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """NavigateToEdgeSystem.moveComponents — float-accumulator model.

    Per frame, for each mobile that's still navigating:
      buildup += speed
      if buildup >= 0.9999:
        buildup -= 1.0
        step one tile along path
      if the pathfinder returns same-tile on getStep:
        finished_navigating = True
        reached_target = (current tile is on target edge)
    """
    target_tiles_cache: Dict[int, frozenset] = {1: _target_edge_tiles(1), 2: _target_edge_tiles(2)}
    for m in state.mobiles:
        if m.hp <= 0 or m.finished_navigating:
            continue
        spec = config.mobile_spec(m.type_idx)
        m.move_buildup += spec.speed
        if m.move_buildup < _MOVE_THRESHOLD:
            continue
        m.move_buildup -= 1.0

        # Re-path if structures changed since last compute (matches engine's
        # getStep semantics) OR path head doesn't align with current tile.
        if (m.path_version != state.structures_version
                or not m.path or m.path[0] != m.xy):
            _recompute_path(m, state)

        next_tile = None
        if len(m.path) >= 2:
            cand = m.path[1]
            # Guard: structure may have been placed on the next tile during
            # the same turn (e.g. deploy ordering). Re-nav in that case.
            if cand in state.structures:
                _recompute_path(m, state)
                if len(m.path) >= 2 and m.path[1] not in state.structures:
                    cand = m.path[1]
                else:
                    cand = None
            next_tile = cand

        if next_tile is None:
            # Pathfinder stuck. Engine's getStep returns same-tile here.
            m.finished_navigating = True
            m.reached_target = m.xy in target_tiles_cache[m.player]
            continue

        prev = m.xy
        m.xy = next_tile
        m.path = m.path[1:]
        m.steps_taken += 1
        events.append({"type": "move", "uid": m.uid, "from": prev, "to": next_tile})
        # Engine's rule: finishedNavigating is set ONLY when getStep returns
        # the same tile as current (delta=0). Successfully moving to a new
        # tile NEVER sets it, even if the new tile is the current path end.
        # The next frame's move will call getStep again and set
        # finishedNavigating if still stuck; meanwhile the attack phase
        # this frame can still destroy the blocker.
        if m.xy in target_tiles_cache[m.player]:
            m.finished_navigating = True
            m.reached_target = True


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

    Gate: finished_navigating AND NOT reached_target AND steps_taken >=
    selfDestructStepsRequired.

    Effects: deal self_destruct_damage_walker to enemy mobiles in
    self_destruct_range; deal self_destruct_damage_tower to enemy structures
    in range; destroy the self-destructor.

    Units that are stuck but haven't walked 5 tiles are silently removed
    without AOE.
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


def system_attack(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """TargetAndAttackSystem.processTargeting.

    Every live attacker (Turrets + damage-dealing mobiles) picks ONE target
    this frame via the priority key:

      (distance, hp+shield, |y - player_edge|, |x - 13.5|)

    Mobile units target both enemy mobiles (walker damage) and enemy
    structures (tower damage); Turrets only target mobiles.

    CRITICAL: each attacker re-filters `hp > 0` during target selection
    because prior attackers in this frame may have killed mobiles. Without
    this, multiple turrets target the same already-dead unit and waste
    their shots (observed: 19 scouts "survive" when replay shows 0).
    """
    # Build the list of attackers up-front; target selection reads
    # state.mobiles / state.structures live (so mutations apply).
    attacker_structs = [s for s in state.structures.values() if s.hp > 0 and s.type_idx == IDX_TURRET]
    attacker_mobiles = [m for m in state.mobiles if m.hp > 0]

    # Turrets first (matches engine: iteration order is game-objects insertion
    # order; Turrets existed from earlier turns so they're earlier in the list
    # than same-turn-spawned mobiles).
    for s in attacker_structs:
        if s.hp <= 0:
            continue
        spec = config.structure_spec(IDX_TURRET, upgraded=s.upgraded)
        if spec.attack_damage_walker <= 0:
            continue
        r = spec.attack_range
        y_edge = 0.0 if s.player == 1 else 28.0
        best = None
        best_key = None
        for m in state.mobiles:
            if m.hp <= 0 or m.player == s.player:
                continue
            d = _distance(s.xy, m.xy)
            if d > r + 1e-9:
                continue
            key = (d, m.hp + m.shield, abs(m.xy[1] - y_edge), abs(m.xy[0] - 13.5))
            if best_key is None or key < best_key:
                best_key = key
                best = m
        if best is None:
            continue
        dmg = spec.attack_damage_walker
        new_hp, new_sh = _apply_damage(best.hp, best.shield, dmg)
        best.hp, best.shield = new_hp, new_sh
        events.append({"type": "attack", "attacker_uid": s.uid,
                       "victim_uid": best.uid, "dmg": dmg,
                       "from_xy": s.xy, "to_xy": best.xy})

    # Mobile attackers — live_mobiles may have changed; re-scan state.mobiles.
    for m in attacker_mobiles:
        if m.hp <= 0:
            continue
        spec = config.mobile_spec(m.type_idx)
        if spec.attack_damage_walker <= 0 and spec.attack_damage_tower <= 0:
            continue
        r = spec.attack_range
        y_edge = 0.0 if m.player == 1 else 28.0
        best = None
        best_key = None
        best_kind = None
        # Enemy mobiles (walker damage)
        if spec.attack_damage_walker > 0:
            for other in state.mobiles:
                if other is m or other.hp <= 0 or other.player == m.player:
                    continue
                d = _distance(m.xy, other.xy)
                if d > r + 1e-9:
                    continue
                key = (d, other.hp + other.shield, abs(other.xy[1] - y_edge), abs(other.xy[0] - 13.5))
                if best_key is None or key < best_key:
                    best_key = key
                    best = other
                    best_kind = "mobile"
        # Enemy structures (tower damage)
        if spec.attack_damage_tower > 0:
            for s in state.structures.values():
                if s.hp <= 0 or s.player == m.player:
                    continue
                d = _distance(m.xy, s.xy)
                if d > r + 1e-9:
                    continue
                key = (d, s.hp, abs(s.xy[1] - y_edge), abs(s.xy[0] - 13.5))
                if best_key is None or key < best_key:
                    best_key = key
                    best = s
                    best_kind = "struct"
        if best is None:
            continue
        if best_kind == "mobile":
            dmg = spec.attack_damage_walker
            new_hp, new_sh = _apply_damage(best.hp, best.shield, dmg)
            best.hp, best.shield = new_hp, new_sh
        else:
            dmg = spec.attack_damage_tower
            best.hp -= dmg
        events.append({"type": "attack", "attacker_uid": m.uid,
                       "victim_uid": best.uid, "dmg": dmg,
                       "from_xy": m.xy, "to_xy": best.xy})


def clear_destroyed(state: SimState, events: List[dict]) -> None:
    to_kill = [xy for xy, s in state.structures.items() if s.hp <= 0]
    for xy in to_kill:
        s = state.structures.pop(xy)
        state.structures_version += 1
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
