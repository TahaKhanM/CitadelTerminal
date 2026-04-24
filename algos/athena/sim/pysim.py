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

import numpy as np

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

# Float32 scaffolding (Cluster I). See config.py FP32 note. Pre-computed
# float32 literals so hot-path expressions don't accidentally upcast via
# a bare Python float (13.5, 0.1, 0.0, etc.). Mixing np.float32 with a
# Python float silently widens to float64 — ONE stray literal in a chain
# of ops defeats the round-trip.
FP32 = np.float32
_F32_ZERO = FP32(0.0)
_F32_TEN = FP32(10.0)
_F32_ONE_TENTH = FP32(0.1)
_F32_13_5 = FP32(13.5)
_F32_DEATH_EPS = FP32(0.001)  # HealthComponent.java:75/83 "<= 0.001f" gate


def _round01(x) -> np.float32:
    """Mirror engine's PlayerStats.roundDecimals (PlayerStats.java:151-153):
    `(float)Math.round(inp * 10.0f) / 10.0f` — snap to nearest 0.1, all
    arithmetic in 32-bit float.

    Engine applies this after every addToMetal/addToFood, so persistent
    SP/MP values never carry float-precision noise. SimCore mirrors
    exactly at every SP/MP mutation site (apply_deploy_actions,
    system_breach refund, system_remove_own_unit refund).

    Returns np.float32. Accepts float / int / np.float32 inputs; the
    np.float32 cast on the input is defensive — the multiply would
    upcast to float64 if one operand were a Python float."""
    x32 = FP32(x)
    # Python's round() returns int for integer-valued floats; int / float32
    # → float32 (numpy preserves the float32 dtype on mixed scalar ops).
    # Engine uses Math.round(float)→long; semantics match for non-halfway
    # values. (Half-way: Java rounds half-up; Python rounds half-to-even.
    # SP/MP deductions are whole numbers × 10 ∈ integers, so no halfway
    # inputs land on the rounding boundary in practice.)
    return FP32(round(float(x32 * _F32_TEN))) / _F32_TEN


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
                         ordered_events: Optional[List] = None,
                         events: Optional[List[dict]] = None) -> None:
    """Mutate state to post-deploy: spawn structures, apply upgrades, spawn
    mobile units.

    Entry shapes (uid is engine-assigned from the replay spawn event's
    3rd slot; see _extract_deploy_actions / _extract_deploy_events_in_order):
      p{1,2}_spawns:   [x, y, type_idx, uid]
      p{1,2}_upgrades: [x, y, new_uid]
      ordered_events:  (player, [x, y, type_idx, uid])

    If `events` is provided, a spawn event dict is appended for every
    successful placement / upgrade / mobile spawn in the same order
    engine.jar emits GlobalSpawn (SpawnUnitsSystem.java:158 for
    placements/mobiles, :160 for upgrades). This mirrors the engine's
    actionFrame=0 emission order: events emitted by processInputBuild +
    processInputDeploy accumulate before the first frame's visibles +
    events are serialized (GameMain.runLoop). Event shape:
        {"type":"spawn", "xy":(x,y), "type_idx":tidx, "uid":uid,
         "player":player}
    — translated to engine wire format by _translate_events_to_buckets.

    Engine command-order semantics (reproduced here):
      Parser.processInputForPlayer processes each command in the order the
      algo issued it, and SpawnUnitsSystem.attemptSpawn / the type-7 branch
      creates a new Gameobject immediately. gameObjects is appended in
      that interleaved order — so a turn that places wall → upgrades
      other wall → places turret produces gameObjects ending in
      [wall, upgraded_wall', turret]. Processing all structures first
      then all upgrades then all mobiles loses this interleaving and
      corrupts the per-type bucket order that the validator gates on.

    Single-pass implementation: iterate ordered_events exactly once and
    dispatch per-type. Upgrades replace the target structure's uid and
    move it to the end of state.structures (dict del + re-insert) to
    match gameObjects's post-clearDestroyed state.

    The non-ordered_events fallback (old p{1,2}_spawns + p{1,2}_upgrades
    path) is preserved for callers that don't have per-event ordering —
    in that case structures are placed first, then upgrades, then mobiles.
    Validators should always pass ordered_events."""
    if ordered_events is not None:
        # Single-pass: handle every event in its engine-issued position.
        for player, loc in ordered_events:
            x, y, tidx = int(loc[0]), int(loc[1]), int(loc[2])
            uid = str(loc[3])
            xy = (x, y)
            placer = state.p1 if player == 1 else state.p2
            if tidx in STRUCTURE_TYPES:
                if not on_diamond(x, y):
                    continue
                owner = 1 if y < HALF else 2
                if owner != player:
                    continue
                if xy in state.structures:
                    continue
                spec = config.structure_spec(tidx, upgraded=False)
                # Deduct SP cost. Engine: SpawnUnitsSystem.attemptSpawn:146
                # → PlayerStats.playerUseResources(metalCost, foodCost)
                # subtracts the BASE unit-config cost from metal (SP).
                # Round to 0.1 to mirror engine's PlayerStats.roundDecimals.
                placer.sp = _round01(placer.sp - spec.cost_sp)
                state.structures[xy] = Structure(
                    xy=xy, type_idx=tidx, upgraded=False,
                    hp=spec.hp, uid=uid, player=player,
                )
                if state.pathfinders is not None:
                    for pf in state.pathfinders.values():
                        pf.put(x, y)
                if events is not None:
                    # Engine: SpawnUnitsSystem.java:158 emits GlobalSpawn
                    # for structure/mobile placements (tidx 0..5).
                    events.append({
                        "type": "spawn", "xy": xy, "type_idx": tidx,
                        "uid": uid, "player": player,
                    })
            elif tidx == 7:  # UPGRADE
                s = state.structures.get(xy)
                if s is None or s.player != player or s.upgraded:
                    continue
                base_spec = config.structure_spec(s.type_idx, upgraded=False)
                upg_spec = config.structure_spec(s.type_idx, upgraded=True)
                # Deduct SP cost. Engine: createUnitConfig(…, upgraded=true)
                # builds a UnitConfig from the unit's .upgrade variant; that
                # variant's metalCost is what playerUseResources(line 146)
                # deducts. In our config.py, upg_spec.cost_sp holds exactly
                # that value.
                #
                # KNOWN CONFIG-SNAPSHOT DRIFT (triage queue, not a Cluster B
                # fix): our citadel_config_snapshot.json has
                # _raw_unit_information.EF.upgrade.cost1 = 2.0, but the live
                # ranked engine's upgrade block has NO cost1 for EF, making
                # metalCost inherit the base's 4.0 per Config.java:184-190's
                # copy-then-patch behavior. Until the snapshot / config
                # loader is fixed, EF upgrades under-deduct 2 SP vs engine.
                placer.sp = _round01(placer.sp - upg_spec.cost_sp)
                s.upgraded = True
                s.hp = min(upg_spec.hp, s.hp + (upg_spec.hp - base_spec.hp))
                s.uid = uid  # new uid assigned by engine on upgrade
                # Engine destroys the old Gameobject and creates a fresh
                # one (SpawnUnitsSystem.java:93 + 147-160). The fresh
                # Gameobject has a brand-new RefundComponent with
                # turnStartRemoval=-1 (unset). Any prior pending-removal
                # status on the pre-upgrade structure is discarded. A
                # subsequent type-6 command this turn will set a fresh
                # turn_start_removal on the upgraded structure — so we
                # MUST reset it here, otherwise the pre-upgrade flag
                # leaks through and type-6's `turn_start_removal is
                # None` gate skips the new removal.
                if s.turn_start_removal is not None:
                    state.pending_removal_xys.discard(xy)
                s.turn_start_removal = None
                # Re-insert at end of dict to match gameObjects order
                # (engine destroys old Gameobject, appends new one to
                # game.gameObjects — see Gameobject.java:57-60 with
                # autoAdd=true from SpawnUnitsSystem.java:147).
                del state.structures[xy]
                state.structures[xy] = s
                if events is not None:
                    # Engine: SpawnUnitsSystem.java:160 emits GlobalSpawn
                    # with type_id=7 for upgrades, carrying the new uid.
                    events.append({
                        "type": "spawn", "xy": xy, "type_idx": 7,
                        "uid": uid, "player": player,
                    })
            elif tidx in MOBILE_TYPES:
                spec = config.mobile_spec(tidx)
                # Deduct MP cost. Engine: the same playerUseResources call
                # deducts the mobile UnitConfig's foodCost from food (MP).
                placer.mp = _round01(placer.mp - spec.cost_mp)
                target_edge = spawn_tile_target_edge(xy)
                state.mobiles.append(Mobile(
                    xy=xy, type_idx=tidx,
                    hp=spec.hp, shield=_F32_ZERO,
                    uid=uid, player=player,
                    spawn_xy=xy, target_edge=target_edge,
                ))
                if events is not None:
                    # Engine: SpawnUnitsSystem.java:158 emits GlobalSpawn
                    # for mobile placements (same code path as structures).
                    events.append({
                        "type": "spawn", "xy": xy, "type_idx": tidx,
                        "uid": uid, "player": player,
                    })
            elif tidx == 6:  # REMOVE
                # Engine: SpawnUnitsSystem.java:52-60.
                #   if target has RefundComponent && turnStartRemoval < 0:
                #       refund.turnStartRemoval = gameMain.turn;
                #       addSpawnEvent(type=6, target.uid, player);
                # SimCore mirrors the same ordering: first mutate state
                # (turn_start_removal set to state.turn), then emit spawn
                # event. Only sets turn_start_removal on structures that
                # exist, belong to the issuing player, and aren't already
                # pending removal. Mid-action-phase consumption: the
                # run_remove_own_unit_system call at end-of-action-frame
                # checks `turn_start_removal + turns_required_to_remove
                # <= current_turn` and fires the refund + GlobalDeath
                # (removed=true). For Citadel's turns_required_to_remove
                # >= 2 this never fires within the same turn's action
                # phase; the actual refund fires at the NEXT turn's
                # BUILDPHASE call site (not run inside this per-turn
                # validator — the validator rebuilds state from the next
                # turn's deploy frame, which already reflects the
                # refund).
                s = state.structures.get(xy)
                if (s is not None
                        and s.player == player
                        and s.turn_start_removal is None):
                    s.turn_start_removal = state.turn
                    state.pending_removal_xys.add(xy)
                if events is not None:
                    events.append({
                        "type": "spawn", "xy": xy, "type_idx": 6,
                        "uid": uid, "player": player,
                    })
        return

    # Fallback: no ordered_events. Structure, upgrade, mobile — each in its
    # own pass. Used only by callers that lack per-event ordering.
    for player, spawns in ((1, p1_spawns), (2, p2_spawns)):
        placer = state.p1 if player == 1 else state.p2
        for loc in spawns:
            x, y, tidx = int(loc[0]), int(loc[1]), int(loc[2])
            if tidx not in STRUCTURE_TYPES:
                continue
            if not on_diamond(x, y):
                continue
            owner = 1 if y < HALF else 2
            if owner != player:
                continue
            xy = (x, y)
            if xy in state.structures:
                continue
            uid = str(loc[3])
            spec = config.structure_spec(tidx, upgraded=False)
            placer.sp = _round01(placer.sp - spec.cost_sp)
            state.structures[xy] = Structure(
                xy=xy, type_idx=tidx, upgraded=False,
                hp=spec.hp, uid=uid, player=player,
            )
            if state.pathfinders is not None:
                for pf in state.pathfinders.values():
                    pf.put(x, y)

    for player, ups in ((1, p1_upgrades), (2, p2_upgrades)):
        placer = state.p1 if player == 1 else state.p2
        for loc in ups:
            x, y = int(loc[0]), int(loc[1])
            xy = (x, y)
            s = state.structures.get(xy)
            if s is None or s.player != player or s.upgraded:
                continue
            base_spec = config.structure_spec(s.type_idx, upgraded=False)
            upg_spec = config.structure_spec(s.type_idx, upgraded=True)
            placer.sp = _round01(placer.sp - upg_spec.cost_sp)
            s.upgraded = True
            s.hp = min(upg_spec.hp, s.hp + (upg_spec.hp - base_spec.hp))
            s.uid = str(loc[2])
            del state.structures[xy]
            state.structures[xy] = s

    for player, spawns in ((1, p1_spawns), (2, p2_spawns)):
        placer = state.p1 if player == 1 else state.p2
        for loc in spawns:
            x, y, tidx = int(loc[0]), int(loc[1]), int(loc[2])
            if tidx not in MOBILE_TYPES:
                continue
            uid = str(loc[3])
            spec = config.mobile_spec(tidx)
            placer.mp = _round01(placer.mp - spec.cost_mp)
            target_edge = spawn_tile_target_edge((x, y))
            state.mobiles.append(Mobile(
                xy=(x, y), type_idx=tidx,
                hp=spec.hp, shield=_F32_ZERO,
                uid=uid, player=player,
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
        # y is int; (13.5 - abs(13.5 - y)) is a constant in Python double
        # space. We collapse it to float32 BEFORE multiplying by the
        # float32 spec.shield_bonus_per_y and adding to float32
        # spec.shield_per_unit so the whole expression stays in float32.
        # Engine's ShieldSystem.java:32-33 does this in one Java-float
        # expression end-to-end; SimCore mirrors.
        y_factor = FP32(_F32_13_5 - FP32(abs(13.5 - y)))
        shield_amount = spec.shield_per_unit + y_factor * spec.shield_bonus_per_y
        for m in state.mobiles:
            if m.hp <= 0 or m.player != s.player:
                continue
            if m.uid in s.shielded_already:
                continue
            if _distance(s.xy, m.xy) > r + 1e-9:
                continue
            m.shield = m.shield + shield_amount  # both FP32, stays FP32
            s.shielded_already.add(m.uid)
            events.append({"type": "shield", "support_uid": s.uid,
                           "target_uid": m.uid, "amount": shield_amount})


def system_breach(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """BreachSystem.breachProcess (BreachSystem.java:21-37).

    Gate: finished_navigating AND reached_target.
    Effects: apply breach_damage to the ENEMY player's HP, refund
    metal_for_breach SP to OUR player, destroy the unit.

    Event order per breacher matches engine's BreachSystem.java:32-35:
      1. GlobalDeath for the breacher (removed=false).
      2. GlobalBreached event.
    """
    for m in state.mobiles:
        if m.hp <= 0:
            continue
        if not (m.finished_navigating and m.reached_target):
            continue
        spec = config.mobile_spec(m.type_idx)
        enemy = state.player_stats(state.enemy_player(m.player))
        # Engine: PlayerStats.dealDamage subtracts damage from totalHP
        # (Java float). SimCore keeps the same float32 precision.
        enemy.hp = FP32(enemy.hp - spec.breach_damage)
        own = state.player_stats(m.player)
        # Engine: PlayerStats.addToMetal(metalForBreach) applies
        # roundDecimals after the add, so SP stays snapped to 0.1.
        own.sp = _round01(own.sp + spec.metal_for_breach)
        events.append({"type": "death", "xy": m.xy, "uid": m.uid,
                       "type_idx": m.type_idx, "player": m.player,
                       "removed": False})
        events.append({"type": "breach", "xy": m.xy, "dmg": spec.breach_damage,
                       "attacker_type": m.type_idx, "attacker_uid": m.uid,
                       "attacker_player": m.player})
        # Engine disables the breacher (BreachSystem.java:28) — this flag
        # is what system_attack's snapshot filter uses to exclude
        # breachers (they have hp=0 like SDers + attack-killed mobiles
        # but should NOT fire, unlike the other two).
        m.breached = True
        m.hp = _F32_ZERO  # destroyed on same frame; clear_destroyed pops before yield


def _apply_damage(target_hp, target_shield, dmg) -> Tuple[np.float32, np.float32, bool]:
    """Engine damage flow — port of HealthComponent.dealDamageToHealthComponent
    (HealthComponent.java:62-89).

    Returns (new_hp: float32, new_shield: float32, died: bool).

    All arithmetic operates in np.float32 to match engine's Java-float
    precision exactly. Inputs are cast defensively at the boundary —
    callers pass np.float32 state fields and np.float32 config damage
    values, but a stray Python float literal (e.g. the 0.0 shield arg
    for structure hits) would upcast the whole expression.

    Death semantics: `died` is True ONLY on the first alive→dead transition,
    per engine's `currentHP <= 0.001f && oldHP > 0.0f` gate. Subsequent
    attacks on an already-dead unit do not re-trigger death — callers rely
    on this to avoid double-emitting GlobalDeath.

    Shield absorption: if total shield >= dmg, damage is fully absorbed by
    shields and no HP loss or death occurs. If shield is less than dmg,
    the shield is wiped and the remainder hits HP, gated by the death
    check above."""
    hp32 = FP32(target_hp)
    sh32 = FP32(target_shield)
    d32 = FP32(dmg)
    if d32 <= _F32_ZERO:
        return hp32, sh32, False
    old_hp = hp32
    if sh32 >= d32:
        return hp32, sh32 - d32, False
    new_hp = hp32 - (d32 - sh32)
    died = bool(new_hp <= _F32_DEATH_EPS and old_hp > _F32_ZERO)
    return new_hp, _F32_ZERO, died


def system_self_destruct(state: SimState, config: SimConfig, events: List[dict]) -> None:
    """SelfDestructSystem.processSelfDestructs (SelfDestructSystem.java:24-63).

    Gate from agent spec & bytecode: `finished_navigating AND NOT
    reached_target AND steps_taken >= selfDestructStepsRequired`. Flags
    were already set by system_move this frame (or a prior frame) via
    the delta=0 return from get_step.

    Effects: deal self_destruct_damage_walker to enemy mobiles in
    self_destruct_range; deal self_destruct_damage_tower to enemy
    structures in range; destroy the self-destructor. Units that are
    stuck but haven't walked 5 tiles are silently removed without AOE.

    Event order per SDer matches engine's SelfDestructSystem.java:32-60:
      1. For each collided victim (walker and tower interleaved by
         collider iteration in engine; SimCore splits into walkers-
         first-then-structures until a ProximityArena port lands —
         the per-bucket event ordering this produces is a Cluster H
         concern, not Cluster D).
         a. Apply damage via HealthComponent.dealDamageToHealthComponent.
         b. Emit GlobalDamaged (line 42).
         c. If victim died, emit GlobalDeath (line 44).
      2. Emit GlobalSelfDestruct event (line 50) — SimCore emits ONE
         regardless of whether attackWalker == attackTower. The engine
         emits two when the damage values differ; correcting that is
         Cluster E.
      3. Emit GlobalDeath for the SD-er itself (line 59) — always
         fires, even if the SD dealt no damage (attacker.hp set to 0).
    """
    # No hp filter here — engine's SD gate is nav-only
    # (SelfDestructSystem.java:26-27). If SDer M1 kills SDer M2 (hp→0) this
    # same frame, M2's SD still fires: M2 was in the pre-loop snapshot and
    # engine's loop only checks nav flags, not current hp. Filtering by hp
    # breaks SD-cascade parity on replays with dense mobile clusters.
    for m in state.mobiles:
        if not (m.finished_navigating and not m.reached_target):
            continue
        if m.breached:
            # Breachers have reachedTarget=True so they already failed the
            # gate above, but defensive check in case of future
            # refactoring: breachers were disabled by BreachSystem
            # (engine: BreachSystem.java:28) and don't SD.
            continue
        spec = config.mobile_spec(m.type_idx)
        if m.steps_taken >= spec.self_destruct_steps_required:
            r = spec.self_destruct_range
            dmg_walker = spec.self_destruct_damage_walker
            dmg_tower = spec.self_destruct_damage_tower
            # Track victim locations per category for the GlobalSelfDestruct
            # payload. Engine's SelfDestructSystem.java:36-40 appends to
            # attackedLocationsWalkers / attackedLocationsTowers during the
            # hit loop.
            walker_locs: List[Tuple[int, int]] = []
            tower_locs: List[Tuple[int, int]] = []
            for other in state.mobiles:
                if other is m or other.hp <= 0 or other.player == m.player:
                    continue
                if _distance(m.xy, other.xy) > r + 1e-9:
                    continue
                new_hp, new_sh, died = _apply_damage(other.hp, other.shield, dmg_walker)
                other.hp, other.shield = new_hp, new_sh
                walker_locs.append(other.xy)
                events.append({"type": "damage", "xy": other.xy, "dmg": dmg_walker,
                               "victim_uid": other.uid, "source_uid": m.uid})
                if died:
                    events.append({"type": "death", "xy": other.xy, "uid": other.uid,
                                   "type_idx": other.type_idx, "player": other.player,
                                   "removed": False})
            for s in list(state.structures.values()):
                if s.hp <= 0 or s.player == m.player:
                    continue
                if _distance(m.xy, s.xy) > r + 1e-9:
                    continue
                # Structures have no shield. Use _F32_ZERO not bare 0.0 to
                # prevent _apply_damage upcasting on a Python-float literal.
                new_hp, _sh, died = _apply_damage(s.hp, _F32_ZERO, dmg_tower)
                s.hp = new_hp
                tower_locs.append(s.xy)
                events.append({"type": "damage", "xy": s.xy, "dmg": dmg_tower,
                               "victim_uid": s.uid, "source_uid": m.uid})
                if died:
                    events.append({"type": "death", "xy": s.xy, "uid": s.uid,
                                   "type_idx": s.type_idx, "player": s.player,
                                   "removed": False})
            # Engine's SelfDestructSystem.java:47-56 emits ONE GlobalSelfDestruct
            # with merged walker+tower locations and damage=attackWalker when
            # attackWalker == attackTower; else TWO events (walker then tower,
            # in that emission order). For Citadel, every mobile type has
            # equal walker/tower SD damage, so the two-event branch is
            # unreachable on ranked replays but we implement it anyway for
            # fuzz / future-config parity.
            if dmg_walker == dmg_tower:
                events.append({
                    "type": "selfDestruct", "xy": m.xy, "uid": m.uid,
                    "player": m.player, "type_idx": m.type_idx,
                    "target_locations": list(walker_locs) + list(tower_locs),
                    "damage": dmg_walker,
                })
            else:
                events.append({
                    "type": "selfDestruct", "xy": m.xy, "uid": m.uid,
                    "player": m.player, "type_idx": m.type_idx,
                    "target_locations": list(walker_locs),
                    "damage": dmg_walker,
                })
                events.append({
                    "type": "selfDestruct", "xy": m.xy, "uid": m.uid,
                    "player": m.player, "type_idx": m.type_idx,
                    "target_locations": list(tower_locs),
                    "damage": dmg_tower,
                })
        # SDer is always destroyed, even if steps_taken < minimumSteps
        # (engine: SelfDestructSystem.java:58-60).
        events.append({"type": "death", "xy": m.xy, "uid": m.uid,
                       "type_idx": m.type_idx, "player": m.player,
                       "removed": False})
        m.hp = _F32_ZERO


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
            # Non-numeric uids shouldn't happen in our sim — every uid
            # originates from engine.jar's monotonic getNewID() counter,
            # plumbed through apply_deploy_actions. Fall back safely.
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
    # Snapshot attackers. Engine gate is `isEnabled() || attackWhenDestroyed`
    # (TargetAndAttackSystem.java:29-30): a unit that DIED this frame still
    # fires if its attack component is enabled (i.e. not disabled by
    # BreachSystem's disableGameObject). SDers set `selfDestructed=true`
    # which triggers attackWhenDestroyed, so they also fire their last
    # shot. Only BREACHERS are excluded — SimCore tracks that via
    # Mobile.breached (set in system_breach).
    #
    # No hp filter here. Dying turrets still fire; dying mobiles (from
    # SD or earlier attack) still fire; dying targets still get hit
    # in _fire via the candidates' hp>0 filter.
    attacker_structs = [s for s in state.structures.values()
                        if s.type_idx == IDX_TURRET]
    attacker_mobiles = [m for m in state.mobiles if not m.breached]

    def _fire(att_xy, att_player, att_uid, dmg_walker, dmg_tower, att_range):
        """Pick target (walkers first, then structures); apply damage; emit
        attack (+ death if target died this hit).

        Engine emits per hit in this order (TargetAndAttackSystem.java:
        111-118):
          1. GlobalAttack
          2. GlobalDamaged   ← synthesized by _translate_events_to_buckets
                                from the attack event (not a separate flat
                                event here)
          3. GlobalDeath     ← only if this hit killed the target (first
                                alive→dead transition per HealthComponent
                                .java:75/83)
        """
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
            new_hp, new_sh, died = _apply_damage(target.hp, target.shield, dmg)
            target.hp, target.shield = new_hp, new_sh
        else:
            dmg = dmg_tower
            # Structures have no shield — pass _F32_ZERO.
            new_hp, _sh, died = _apply_damage(target.hp, _F32_ZERO, dmg)
            target.hp = new_hp
        events.append({"type": "attack", "attacker_uid": att_uid,
                       "victim_uid": target.uid, "dmg": dmg,
                       "from_xy": att_xy, "to_xy": target.xy})
        if died:
            events.append({"type": "death", "xy": target.xy, "uid": target.uid,
                           "type_idx": target.type_idx, "player": target.player,
                           "removed": False})

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


def system_remove_own_unit(state: SimState, config: SimConfig,
                             events: List[dict], current_turn: int) -> None:
    """Port of RemoveOwnUnitSystem.removeOwnUnitProcess
    (RemoveOwnUnitSystem.java:20-34).

    For each structure with turn_start_removal set, if
      turn_start_removal + turns_required_to_remove <= current_turn
    the structure is refunded and destroyed this system-call:
      * refund_metal = costMetal * (hp / max_hp) * refund_pct
        (engine: line 24. costMetal is the structure's *total* cost —
         base + upgrade bump for upgraded structures, via spawnComponent
         accumulation in attemptSpawn line 154.)
      * refund credited to the refunder's SP via _round01(sp + refund)
        to mirror PlayerStats.addToMetal's post-op rounding.
      * GlobalDeath emitted with removed=True.
      * structure popped from state.structures (engine: currentHP=-1 +
        gameObjectDestroy.accept, popped at next clearDestroyed — SimCore
        pops directly for simplicity, same visible effect).

    Citadel note: every structure's turns_required_to_remove >= 2, so
    within a single turn's action phase this gate is always `>`
    (pending structure survives this turn). The function is called every
    action frame regardless, mirroring engine's GameMain.runLoop:316.
    Fast-path: iterate the small pending-removal set, not all structures."""
    pending = state.pending_removal_xys
    if not pending:
        return
    to_refund = []
    for xy in list(pending):
        s = state.structures.get(xy)
        if s is None or s.turn_start_removal is None or s.hp <= 0.0:
            # Stale entry (structure died from combat this frame or
            # earlier); scrub.
            pending.discard(xy)
            continue
        spec = config.structure_spec(s.type_idx, upgraded=s.upgraded)
        if s.turn_start_removal + spec.turns_required_to_remove > current_turn:
            continue
        to_refund.append((xy, s, spec))
    for xy, s, spec in to_refund:
        base_spec = config.structure_spec(s.type_idx, upgraded=False)
        # spawnComponent.costMetal = base + upgrade bump (see
        # SpawnUnitsSystem.java:154). For a base structure, total = base.
        # For an upgraded structure, total = base.cost + upgrade.cost.
        # All operands are float32 (spec.cost_sp and _F32_ZERO); the
        # ternary selects between two float32 values so the sum stays
        # float32.
        total_cost = base_spec.cost_sp + (spec.cost_sp if s.upgraded else _F32_ZERO)
        hp_pct = s.hp / spec.hp if spec.hp > _F32_ZERO else _F32_ZERO
        refund_metal = total_cost * hp_pct * spec.refund_pct
        placer = state.p1 if s.player == 1 else state.p2
        placer.sp = _round01(placer.sp + refund_metal)
        events.append({
            "type": "death", "xy": xy, "uid": s.uid,
            "type_idx": s.type_idx, "player": s.player,
            "removed": True,
        })
        # Pop from state + pathfinders (engine: set hp=-1 + queue destroy;
        # SimCore collapses to direct pop — same observable effect since
        # engine's next clearDestroyed at start-of-gameEngineLoop would
        # have done this anyway).
        state.structures.pop(xy, None)
        pending.discard(xy)
        if state.pathfinders is not None:
            for pf in state.pathfinders.values():
                pf.remove(xy[0], xy[1])


def clear_destroyed(state: SimState, events: Optional[List[dict]] = None) -> None:
    """Pop dead units from state and keep pathfinders in sync. Does NOT emit
    death events — those fire inline at each kill site (system_attack /
    system_self_destruct / system_breach), matching engine's
    TargetAndAttackSystem.java:115-118 / SelfDestructSystem.java:43-60 /
    BreachSystem.java:32-33 emission sites.

    The `events` parameter is retained for back-compat but unused (old
    simulate_action_phase passed a flat list here). The engine's
    Game.clearDestroyedGameObjects (Game.java:184-193) is similarly a
    pure list-cleanup — no events."""
    to_kill = [xy for xy, s in state.structures.items() if s.hp <= 0]
    for xy in to_kill:
        state.structures.pop(xy)
        if state.pathfinders is not None:
            for pf in state.pathfinders.values():
                pf.remove(xy[0], xy[1])
    state.mobiles = [m for m in state.mobiles if m.hp > 0]


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
    # Action-iteration shape matches engine's GameMain.runLoop:268-318:
    # systems run, snapshot (implicit in this non-iter path), walkersAlive
    # gate at BOTTOM of iteration. Turns with zero mobile spawns still
    # produce one frame so frame_count matches engine.
    while f < max_frames:
        system_move(state, config, frame_events)
        system_collision(state)
        system_shield_decay(state, config)
        system_shield_give(state, config, frame_events)
        system_breach(state, config, frame_events)
        system_self_destruct(state, config, frame_events)
        system_attack(state, config, frame_events)

        # Post-clear per frame — dead units pop before the next frame's
        # systems run (engine: Game.java:181 end-of-engineLoop clear).
        clear_destroyed(state)

        f += 1
        if not state.mobiles:
            break

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


# ============================================================================
# FRAME-LEVEL OBSERVATION (Phase 1.B instrumentation)
# ============================================================================
#
# simulate_action_phase_iter yields one observation per engine action-frame.
# Each observation is a dict shaped like a replay action-phase frame (see
# Parser.parseVisiblesListToJson), produced by translating SimCore's internal
# flat-event list into the engine's 9-bucket events dict and snapshotting the
# current p1/p2 units per-type.
#
# PURE INSTRUMENTATION: this path runs the same systems in the same order as
# simulate_action_phase, emits the same flat events, and applies no additional
# mutations. The only thing it does differently is yield after each frame
# and materialize a per-frame observation dict.
#
# Known already-surfaced divergences from the engine's wire format (to be
# fixed in Step 2 individual commits; Step 1 just surfaces them):
#   * Deaths from this-frame's attack/SD/breach are emitted by SimCore's
#     clear_destroyed() at the START of the NEXT frame. The engine emits
#     them in THIS frame. So sim death events are 1 frame late.
#   * `selfDestruct` event target-locations list is empty in SimCore.
#   * `damage`/`attack`/`shield`/`move` events in SimCore don't carry the
#     engine's typeID/PID trailing fields (we fill them from state at
#     translate time, so this isn't a gap — but note if state has drifted,
#     these fields become unreliable).
#   * `spawn` events are not emitted by apply_deploy_actions (deploy-phase
#     spawns are engine-emitted by Parser.processInputDeploy; SimCore just
#     mutates state silently).
#   * p{1,2}Units[6] (removal pseudo-unit) HP slot: we emit 0.0 because we
#     don't track turnStartRemoval — only the pending_removal boolean.

# Bucket names, fixed engine order (don't reorder — we gate on equality).
_EVENT_BUCKETS = ("attack", "breach", "damage", "death", "melee",
                  "move", "selfDestruct", "shield", "spawn")


def _empty_buckets() -> Dict[str, list]:
    return {k: [] for k in _EVENT_BUCKETS}


def _pid_persp(player: int, perspective: int) -> int:
    """Engine's samePlayerToPlayerID: returns 1 if actor pid matches viewer, else 2."""
    return 1 if player == perspective else 2


def _unit_type_by_uid(state: SimState, uid: str) -> int:
    """Look up a unit's type_idx from state by uid. Returns -1 if not found.

    Used when translating a flat sim event to engine wire format — the flat
    event lacks typeID but the state still has the unit (for structures that
    survive the frame, or mobiles that haven't been cleared yet)."""
    for s in state.structures.values():
        if s.uid == uid:
            return s.type_idx
    for m in state.mobiles:
        if m.uid == uid:
            return m.type_idx
    return -1


def _unit_player_by_uid(state: SimState, uid: str) -> int:
    for s in state.structures.values():
        if s.uid == uid:
            return s.player
    for m in state.mobiles:
        if m.uid == uid:
            return m.player
    return 0


def _translate_events_to_buckets(
    flat_events: List[dict],
    state: SimState,
    perspective: int = 1,
    dead_lookup: Optional[Dict[str, Tuple[int, int]]] = None,
) -> Dict[str, list]:
    """Convert SimCore's flat event list into the engine's 9-bucket wire
    format, player-`perspective` view.

    `dead_lookup` is an optional {uid: (type_idx, player)} dict for units that
    were destroyed in a previous frame and are no longer present in state —
    used to resolve typeID/PID for their trailing death events. Without it,
    death events for already-removed units would have type=-1.
    """
    buckets = _empty_buckets()
    for e in flat_events:
        if not isinstance(e, dict):
            continue
        t = e.get("type")
        if t == "move":
            uid = e.get("uid")
            # lookup type + player from surviving state
            tid = _unit_type_by_uid(state, uid)
            ply = _unit_player_by_uid(state, uid)
            if tid < 0 and dead_lookup is not None:
                tid, ply = dead_lookup.get(uid, (-1, 0))
            old = e.get("from") or (0, 0)
            new = e.get("to") or (0, 0)
            buckets["move"].append([
                [int(old[0]), int(old[1])],
                [int(new[0]), int(new[1])],
                [0, 0],
                int(tid),
                str(uid),
                _pid_persp(ply, perspective),
            ])
        elif t == "attack":
            src_uid = e.get("attacker_uid")
            tgt_uid = e.get("victim_uid")
            tid = _unit_type_by_uid(state, src_uid)
            ply = _unit_player_by_uid(state, src_uid)
            if tid < 0 and dead_lookup is not None:
                tid, ply = dead_lookup.get(src_uid, (-1, 0))
            src_xy = e.get("from_xy") or (0, 0)
            tgt_xy = e.get("to_xy") or (0, 0)
            buckets["attack"].append([
                [int(src_xy[0]), int(src_xy[1])],
                [int(tgt_xy[0]), int(tgt_xy[1])],
                float(e.get("dmg") or 0.0),
                int(tid),
                str(src_uid),
                str(tgt_uid),
                _pid_persp(ply, perspective),
            ])
            # Engine also emits a `damage` for every attack. SimCore does NOT
            # emit a separate damage event for attacks — it only emits damage
            # for SD hits. For faithful wire-format output, synthesize the
            # matching damage event here. The victim's typeID/PID are looked
            # up the same way.
            v_tid = _unit_type_by_uid(state, tgt_uid)
            v_ply = _unit_player_by_uid(state, tgt_uid)
            if v_tid < 0 and dead_lookup is not None:
                v_tid, v_ply = dead_lookup.get(tgt_uid, (-1, 0))
            buckets["damage"].append([
                [int(tgt_xy[0]), int(tgt_xy[1])],
                float(e.get("dmg") or 0.0),
                int(v_tid),
                str(tgt_uid),
                _pid_persp(v_ply, perspective),
            ])
        elif t == "damage":
            # SD hit on a victim
            v_uid = e.get("victim_uid")
            v_tid = _unit_type_by_uid(state, v_uid)
            v_ply = _unit_player_by_uid(state, v_uid)
            if v_tid < 0 and dead_lookup is not None:
                v_tid, v_ply = dead_lookup.get(v_uid, (-1, 0))
            xy = e.get("xy") or (0, 0)
            buckets["damage"].append([
                [int(xy[0]), int(xy[1])],
                float(e.get("dmg") or 0.0),
                int(v_tid),
                str(v_uid),
                _pid_persp(v_ply, perspective),
            ])
        elif t == "death":
            # type_idx & player are stamped on the event at kill time by
            # system_attack / system_self_destruct / system_breach (and later
            # by system_remove_own_unit in Cluster F). The `removed` flag
            # tracks engine's GlobalDeath.removed: True only for deaths from
            # RemoveOwnUnitSystem (refund), False for combat deaths.
            xy = e.get("xy") or (0, 0)
            # NOTE: use `get(k, default)` NOT `get(k) or default` — walls
            # have type_idx==0 which is falsy, and `0 or -1` evaluates to
            # -1 (this bug persisted across several commits until Cluster D
            # finally surfaced it via inlined wall-death emission).
            buckets["death"].append([
                [int(xy[0]), int(xy[1])],
                int(e.get("type_idx", -1)),
                str(e.get("uid")),
                _pid_persp(int(e.get("player", 0)), perspective),
                bool(e.get("removed", False)),
            ])
        elif t == "breach":
            xy = e.get("xy") or (0, 0)
            buckets["breach"].append([
                [int(xy[0]), int(xy[1])],
                float(e.get("dmg") or 0.0),
                int(e.get("attacker_type") or -1),
                str(e.get("attacker_uid")),
                _pid_persp(int(e.get("attacker_player") or 0), perspective),
            ])
        elif t == "selfDestruct":
            # Engine's GlobalSelfDestruct wire format (inPerspective):
            # [[src_xy], [target_xys], damage, typeID, srcID, srcPID_in_persp]
            # Fields are now carried on the flat event directly (populated
            # by system_self_destruct per SelfDestructSystem.java:47-56).
            xy = e.get("xy") or (0, 0)
            uid = str(e.get("uid"))
            tid = int(e.get("type_idx", -1))
            locs = e.get("target_locations") or []
            buckets["selfDestruct"].append([
                [int(xy[0]), int(xy[1])],
                [[int(l[0]), int(l[1])] for l in locs],
                float(e.get("damage", 0.0)),
                tid,
                uid,
                _pid_persp(int(e.get("player", 0)), perspective),
            ])
        elif t == "shield":
            # SimCore shield events carry support_uid, target_uid, amount.
            # Resolve xy/type/pid from state.
            s_uid = e.get("support_uid")
            t_uid = e.get("target_uid")
            s_tid = _unit_type_by_uid(state, s_uid)
            s_ply = _unit_player_by_uid(state, s_uid)
            s_xy = (0, 0); t_xy = (0, 0)
            for s in state.structures.values():
                if s.uid == s_uid:
                    s_xy = s.xy; break
            for m in state.mobiles:
                if m.uid == t_uid:
                    t_xy = m.xy; break
            buckets["shield"].append([
                [int(s_xy[0]), int(s_xy[1])],
                [int(t_xy[0]), int(t_xy[1])],
                float(e.get("amount") or 0.0),
                int(s_tid),
                str(s_uid),
                str(t_uid),
                _pid_persp(s_ply, perspective),
            ])
        elif t == "spawn":
            # Emitted by apply_deploy_actions for structure / upgrade /
            # mobile placements (mirrors SpawnUnitsSystem.java:158/160).
            # Engine wire format: [[x,y], typeID, uid, srcPID_in_persp]
            xy = e.get("xy") or (0, 0)
            tidx = int(e.get("type_idx") or 0)
            uid = str(e.get("uid"))
            ply = int(e.get("player") or 0)
            buckets["spawn"].append([
                [int(xy[0]), int(xy[1])],
                tidx,
                uid,
                _pid_persp(ply, perspective),
            ])
        # `melee` is not emitted by SimCore (Citadel has no melee units).
    return buckets


def _snapshot_units(state: SimState, config: Optional[SimConfig] = None,
                     perspective: int = 1) -> Tuple[list, list]:
    """Snapshot p1Units / p2Units in engine wire format (8-bucket list of
    lists). Perspective-mirroring of coordinates is NOT applied — we assume
    the replay under compare is stored with perspective=1 (p1 view),
    identity coordinates. Caller can convert if needed.

    Each real-unit entry is [x, y, hp+shield, uid]. Pseudo-units (removal
    index 6, upgrade index 7) are joined by (x,y).

    Pseudo-unit index 6 (removal) HP slot matches engine's
    DisplayUnitsSystem.java:32:
        float(turns_required_to_remove - (current_turn - turn_start_removal))
    — countdown from turns_required down to 0 across the life of a pending
    removal. Requires `config` to look up the structure's
    turns_required_to_remove; if None is passed (legacy callers), falls
    back to 0.0 which preserves old behavior of emitting the pseudo-unit
    without correct countdown.

    Performance-sensitive — called once per frame."""
    p1w, p1s, p1t, p1sc, p1d, p1i, p1rm, p1up = [], [], [], [], [], [], [], []
    p2w, p2s, p2t, p2sc, p2d, p2i, p2rm, p2up = [], [], [], [], [], [], [], []
    p1_by_type = (p1w, p1s, p1t, p1sc, p1d, p1i, p1rm, p1up)
    p2_by_type = (p2w, p2s, p2t, p2sc, p2d, p2i, p2rm, p2up)
    turn = state.turn
    for s in state.structures.values():
        if s.hp <= 0.0:
            continue
        x, y = s.xy
        uid = s.uid
        bucket = p1_by_type if s.player == 1 else p2_by_type
        bucket[s.type_idx].append([x, y, s.hp, uid])
        if s.turn_start_removal is not None:
            if config is not None:
                spec = config.structure_spec(s.type_idx, upgraded=s.upgraded)
                hp_slot = float(spec.turns_required_to_remove - (turn - s.turn_start_removal))
            else:
                hp_slot = 0.0
            bucket[6].append([x, y, hp_slot, uid])
        if s.upgraded:
            bucket[7].append([x, y, 0.0, uid])
    for m in state.mobiles:
        if m.hp <= 0.0:
            continue
        x, y = m.xy
        bucket = p1_by_type if m.player == 1 else p2_by_type
        bucket[m.type_idx].append([x, y, m.hp + m.shield, m.uid])
    return list(p1_by_type), list(p2_by_type)


_EMPTY_EV: Dict[str, list] = {}


def build_frame_observation(
    state: SimState,
    flat_events: List[dict],
    frame_idx: int,
    turn: int,
    total_frame_number: int,
    perspective: int = 1,
    dead_lookup: Optional[Dict[str, Tuple[int, int]]] = None,
    config: Optional[SimConfig] = None,
) -> dict:
    """Assemble a single engine-equivalent action-phase frame dict from
    current SimState + the events emitted during this frame.

    `config` is required for the removal pseudo-unit HP countdown to
    match engine's DisplayUnitsSystem.java:32 formula. Legacy callers
    that pass None will emit pseudo-units with hp=0 (old behavior).

    Output schema matches Parser.parseVisiblesListToJson:
      turnInfo:  [1, turn, frame_idx, total_frame_number]
      p1Stats:   [hp, sp, mp]            (no botTime — sim doesn't have one)
      p2Stats:   [hp, sp, mp]
      p1Units:   list of 8 lists, index=type_idx, entry=[x,y,hp+shield,uid]
      p2Units:   ditto
      events:    dict of 9 buckets in inPerspective format (see _translate…)
                 Empty dict when the frame had no events — consumers should
                 treat missing buckets as [].
    """
    p1u, p2u = _snapshot_units(state, config=config, perspective=perspective)
    if flat_events:
        ev = _translate_events_to_buckets(
            flat_events, state, perspective=perspective, dead_lookup=dead_lookup
        )
    else:
        ev = _EMPTY_EV
    p1 = state.p1; p2 = state.p2
    return {
        "turnInfo": [1, turn, frame_idx, total_frame_number],
        "p1Stats": [p1.hp, p1.sp, p1.mp],
        "p2Stats": [p2.hp, p2.sp, p2.mp],
        "p1Units": p1u,
        "p2Units": p2u,
        "events": ev,
    }


def simulate_action_phase_iter(
    state: SimState,
    config: SimConfig,
    max_frames: int = 200,
    perspective: int = 1,
    total_frame_start: int = 0,
    seed_events: Optional[List[dict]] = None,
):
    """Per-frame observation generator. Yields one engine-equivalent action-
    phase frame dict per engine actionFrame.

    Frame-loop structure is IDENTICAL to simulate_action_phase (same systems,
    same order, same mutations). The only addition is an observation dict
    built after each frame's systems run.

    seed_events, if provided, is a list of event dicts that are prepended
    to frame 0's flat_events. Used by validators to seed the deploy-phase
    spawn events (engine emits these during processInputBuild /
    processInputDeploy, and they appear in the first action frame's event
    bucket alongside any move/attack events from the initial gameEngineLoop
    run during BUILDPHASE/DEPLOYPHASE).

    Yields:
        dict with keys turnInfo, p1Stats, p2Stats, p1Units, p2Units, events
        (see build_frame_observation).

    Does NOT run the post-loop final clear_destroyed; caller should run it
    if terminal-state cleanup is desired (mirrors simulate_action_phase's
    one-trailing-call behavior)."""
    turn = state.turn
    # Maintain a dead-unit lookup so events emitted AFTER a unit's death can
    # still resolve typeID/PID from its former state. Populated from
    # clear_destroyed's pre_events each frame.
    dead_lookup: Dict[str, Tuple[int, int]] = {}

    f = 0
    total_frame = total_frame_start
    pending_seed = list(seed_events) if seed_events else []

    # Engine's action-iteration shape (GameMain.runLoop:268-318) is:
    #   ++totalActionFrames; ++totalFrameNumber;
    #   emit frame;
    #   clearEvents;
    #   if (!walkersAlive) end action;
    #   else { gameEngineLoop; runRemoveOwnUnitSystem; ++actionFrame; }
    #
    # Key subtlety: the walkersAlive check is at the BOTTOM of the
    # iteration, so the emit happens before it — a turn where no mobiles
    # ever spawn still emits one action frame (empty walker buckets,
    # full structure snapshot) before the loop terminates. Previous
    # SimCore shape checked `not state.mobiles` at the top and skipped
    # that frame entirely — cause of the ~10-per-replay frame_count
    # residuals. This loop now mirrors engine: systems run (engine's
    # pre-emit gameEngineLoop), then emit, then walkersAlive gate at
    # bottom. The systems are no-ops on empty state.mobiles so the
    # "no mobiles ever" case emits exactly one frame.
    while f < max_frames:
        # Deploy-phase spawn events seed frame 0 (engine's processInputBuild
        # + processInputDeploy emit into the same event buckets before the
        # first action frame's clearEvents runs).
        flat_events: List[dict] = list(pending_seed) if pending_seed else []
        pending_seed = []

        system_move(state, config, flat_events)
        system_collision(state)
        system_shield_decay(state, config)
        system_shield_give(state, config, flat_events)
        system_breach(state, config, flat_events)
        system_self_destruct(state, config, flat_events)
        system_attack(state, config, flat_events)

        # Engine's end-of-gameEngineLoop clearDestroyedGameObjects
        # (Game.java:181) removes dead units before visibles are captured.
        clear_destroyed(state)

        # RemoveOwnUnitSystem runs AFTER gameEngineLoop in engine's
        # ACTION-phase iteration (GameMain.runLoop:316). Events and pops
        # land in this frame's bucket / snapshot.
        system_remove_own_unit(state, config, flat_events, turn)

        # Harvest dead-unit info from ALL death events in this frame's
        # bucket (system_attack/SD/breach inline + RemoveOwnUnitSystem's
        # refund deaths) so the translator can resolve event references
        # whose target has been popped. Single pass.
        # Use get(k, default) NOT get(k) or default — walls have type_idx=0.
        for e in flat_events:
            if isinstance(e, dict) and e.get("type") == "death":
                uid = str(e.get("uid"))
                dead_lookup[uid] = (int(e.get("type_idx", -1)),
                                    int(e.get("player", 0)))

        obs = build_frame_observation(
            state, flat_events, frame_idx=f, turn=turn,
            total_frame_number=total_frame,
            perspective=perspective, dead_lookup=dead_lookup,
            config=config,
        )
        yield obs

        # Termination gates — engine applies BOTH, after emit:
        #
        #   1. processEndGame(false) — GameMain.runLoop:301, called
        #      between clearEvents and the walkersAlive if/else.
        #      Sets this.exitGame=true if p1.totalHP<=0 or p2.totalHP<=0.
        #      The outer `while (!this.exitGame)` then breaks at the top
        #      of the next iteration. Net effect: when either player's
        #      HP drops to 0-or-below, the action phase emits its current
        #      frame and then ends — even if walkers are still alive.
        #
        #   2. walkersAlive gate — GameMain.runLoop:296-300. Scans all
        #      UnitInfoComponents for WALKER category; SimCore's
        #      state.mobiles is exactly that set post-clear_destroyed.
        #
        # Example where gate 1 is load-bearing: T30 of a ranked replay
        # where p2.hp hits -6 at F28 with 3 walkers still on the board.
        # Replay emits F28 and stops; without the HP gate SimCore
        # iterates until state.mobiles empties, producing up to 86
        # excess frames.
        if state.p1.hp <= 0.0 or state.p2.hp <= 0.0:
            return
        if not state.mobiles:
            return

        f += 1
        total_frame += 1
