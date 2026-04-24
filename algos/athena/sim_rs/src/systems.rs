//! Per-frame action-phase systems — Rust port of `algos/athena/sim/pysim.py`.
//!
//! Frame-loop order (verified from engine.jar bytecode; see
//! `research/engine_decompiled/sources/com/c1games/terminal/game/Game.java:167-182
//! gameEngineLoop`):
//!
//! ```text
//! clear_destroyed  (start of gameEngineLoop)
//!   → system_move
//!   → system_collision
//!   → system_shield_decay
//!   → system_shield_give
//!   → system_breach
//!   → system_self_destruct
//!   → system_attack
//! clear_destroyed  (end of gameEngineLoop)
//! system_remove_own_unit   (runRemoveOwnUnitSystem, GameMain.runLoop:316)
//! ```
//!
//! All arithmetic is performed in `f32` to mirror engine's Java 32-bit float
//! precision exactly (Cluster I parity). Python sim pins np.float32 via its
//! `FP32` helper; this port uses native `f32` for the same bit-for-bit trace.
//!
//! # Engine source citations
//!
//! * `systems/NavigateToEdgeSystem.java:35`  — `system_move`
//! * `systems/CollisionSystem.java`          — `system_collision` (no-op)
//! * `systems/ShieldSystem.java:28-57`       — `system_shield_decay` + `system_shield_give`
//! * `systems/BreachSystem.java:21-37`       — `system_breach`
//! * `systems/SelfDestructSystem.java:24-63` — `system_self_destruct`
//! * `systems/TargetAndAttackSystem.java:25-120` — `system_attack`
//! * `systems/RemoveOwnUnitSystem.java:20-34` — `system_remove_own_unit`
//! * `HealthComponent.java:62-89`            — `_apply_damage` shared helper
//! * `Game.java:167-195 gameEngineLoop / clearDestroyedGameObjects` — frame order

use crate::config::{SimConfig, IDX_SUPPORT, IDX_TURRET};
use crate::events::EventEntry;
use crate::map::{
    edge_tiles_slice, EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT, EDGE_TOP_LEFT, EDGE_TOP_RIGHT,
};
use crate::pathfinder::{HORIZONTAL, VERTICAL};
use crate::state::SimState;
use indexmap::IndexMap;

// ---------------------------------------------------------------- constants

/// Engine's MovementComponent accumulator threshold
/// (NavigateToEdgeSystem.java:47). A mobile moves when `buildup >= 0.9999`.
const MOVE_THRESHOLD: f32 = 0.9999;

/// Death threshold — HealthComponent.java:75/83 `<= 0.001f` gate.
const DEATH_EPS: f32 = 0.001;

const ARENA: i32 = 28;

// -------------------------------------------------------------- f32 helpers

/// Mirror engine's `PlayerStats.roundDecimals` (PlayerStats.java:151-153):
/// `(float)Math.round(inp * 10.0f) / 10.0f`. Snaps to nearest 0.1 in 32-bit
/// float. Engine applies this after every addToMetal/addToFood.
#[inline]
pub fn round01(x: f32) -> f32 {
    // Java Math.round(float) rounds half-up. Rust's f32::round rounds
    // half-away-from-zero — same result for non-halfway values. Deposits in
    // SP/MP land on whole-tenths so halfway cases don't occur in practice.
    ((x * 10.0_f32).round()) / 10.0_f32
}

/// Euclidean distance between two integer tiles, computed in f32.
#[inline]
fn distance(a: (i32, i32), b: (i32, i32)) -> f32 {
    let dx = (a.0 - b.0) as f32;
    let dy = (a.1 - b.1) as f32;
    (dx * dx + dy * dy).sqrt()
}

/// Lookup the precomputed edge-tile set (for reached_target check).
#[inline]
fn edge_set(edge: i32) -> &'static [(i32, i32)] {
    edge_tiles_slice(edge)
}

// -------------------------------------------- shared damage helper (HealthComponent)

/// Engine damage flow — port of `HealthComponent.dealDamageToHealthComponent`
/// (HealthComponent.java:62-89).
///
/// Returns `(new_hp, new_shield, died)`. `died` is `true` only on the first
/// alive→dead transition, per the `currentHP <= 0.001f && oldHP > 0.0f` gate.
///
/// Shield absorption: if `shield >= dmg`, damage is fully absorbed (no HP loss).
/// Otherwise shield is wiped and the remainder hits HP, gated by the death
/// check above.
pub fn apply_damage(target_hp: f32, target_shield: f32, dmg: f32) -> (f32, f32, bool) {
    if dmg <= 0.0 {
        return (target_hp, target_shield, false);
    }
    let old_hp = target_hp;
    if target_shield >= dmg {
        return (target_hp, target_shield - dmg, false);
    }
    let new_hp = target_hp - (dmg - target_shield);
    let died = new_hp <= DEATH_EPS && old_hp > 0.0;
    (new_hp, 0.0, died)
}

// --------------------------------------------------------------- pathfinders

/// Lazily initialize the 4 per-edge PathFinder instances from current
/// structures. Mirror of `sim/pysim.py::_ensure_pathfinders`.
/// Build the 4-edge pathfinder container eagerly, but only populate the
/// edges actually needed by live mobiles — each `PathFinder::new` allocates
/// ~30 KB of state arrays and is the single biggest per-sim cost, so we defer
/// until a mobile with that `target_edge` appears. In the common mid-game
/// fixture (all 5 mobiles target EDGE_TOP_RIGHT) this cuts pathfinder init
/// from 4 instances to 1.
/// First-call pathfinder builder. Only constructs `PathFinder` instances for
/// target_edges that actually appear in live mobiles — the engine holds 4
/// pathfinders but SimCore sims a single action phase where typically only
/// 1 edge is needed (e.g. all scouts target EDGE_TOP_RIGHT). Each
/// `PathFinder::new` allocates ~30 KB of state arrays, so pruning unused
/// edges cuts the per-sim init cost by up to 4x.
///
/// Called only when `state.pathfinders.is_none()` (start of first frame).
fn ensure_pathfinders(state: &mut SimState) {
    use crate::pathfinder::{edge_direction, PathFinder};
    if state.pathfinders.is_some() {
        return;
    }
    let mut pfs: IndexMap<i32, PathFinder> = IndexMap::with_capacity(4);
    let mut needed: [bool; 4] = [false; 4];
    for m in state.mobiles.iter() {
        if m.hp <= 0.0 || m.finished_navigating { continue; }
        if (0..4).contains(&m.target_edge) { needed[m.target_edge as usize] = true; }
    }
    // Walls snapshot — identical across all 4 PathFinders.
    let walls: Vec<(i32, i32)> = state.structures.keys().copied().collect();
    for edge in [EDGE_TOP_RIGHT, EDGE_TOP_LEFT, EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT] {
        if !needed[edge as usize] { continue; }
        let perfects = edge_tiles_slice(edge);
        pfs.insert(edge, PathFinder::new(ARENA, edge_direction(edge), &walls, perfects));
    }
    state.pathfinders = Some(pfs);
}

// ================================================================ system_move

/// Exact port of `NavigateToEdgeSystem.moveComponents`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// NavigateToEdgeSystem.java:35-70).
///
/// For each navigating mobile:
///   1. `buildup += speed`
///   2. if `buildup < 0.9999` → skip frame
///   3. `buildup -= 1.0`
///   4. `nextTile = pathfinder.get_step(pos.x, pos.y, last_move)`
///   5. `last_move = HORIZONTAL` if ny == y else `VERTICAL`
///   6. if delta == (0, 0) → `finished_navigating=true`, `reached_target =
///      (xy in nav_targets)`
///   7. else move body, increment stepsTaken, emit move event (strict bucket —
///      NOT canonicalized).
pub fn system_move(state: &mut SimState, config: &SimConfig, _events: &mut Vec<EventEntry>) {
    ensure_pathfinders(state);
    // Borrow the static per-edge tables. Zero alloc, 'static slices.
    let edge_sets: [(i32, &'static [(i32, i32)]); 4] = [
        (EDGE_TOP_RIGHT, edge_set(EDGE_TOP_RIGHT)),
        (EDGE_TOP_LEFT, edge_set(EDGE_TOP_LEFT)),
        (EDGE_BOTTOM_LEFT, edge_set(EDGE_BOTTOM_LEFT)),
        (EDGE_BOTTOM_RIGHT, edge_set(EDGE_BOTTOM_RIGHT)),
    ];

    // Split-borrow: alias disjoint fields of SimState via destructuring so we
    // can hold `&mut state.pathfinders` and `&mut state.mobiles` simultaneously
    // without the Option::take()/restore dance. Zero field moves per frame.
    let SimState { pathfinders, mobiles, .. } = state;
    let pathfinders = pathfinders.as_mut().expect("pathfinders");
    for m in mobiles.iter_mut() {
        if m.hp <= 0.0 || m.finished_navigating {
            continue;
        }
        let spec = config.mobile_spec(m.type_idx);
        m.move_buildup += spec.speed;
        if m.move_buildup < MOVE_THRESHOLD {
            continue;
        }
        m.move_buildup -= 1.0;
        let pf = pathfinders
            .get_mut(&m.target_edge)
            .expect("per-edge pathfinder missing");
        let (nx, ny) = pf.get_step(m.xy.0, m.xy.1, m.last_move as u8);
        // lastMove: horizontal if y unchanged, else vertical.
        m.last_move = if ny == m.xy.1 { HORIZONTAL as i32 } else { VERTICAL as i32 };
        let dx = nx - m.xy.0;
        let dy = ny - m.xy.1;
        if dx == 0 && dy == 0 {
            m.finished_navigating = true;
            // reached_target iff current xy is in this mobile's specific
            // target-edge list — matches NavigateToEdgeSystem.java:50-54.
            let targets: &[(i32, i32)] = edge_sets
                .iter()
                .find(|(e, _)| *e == m.target_edge)
                .expect("edge_sets missing target edge")
                .1;
            m.reached_target = targets.iter().any(|&t| t == m.xy);
            continue;
        }
        m.xy = (nx, ny);
        m.steps_taken += 1;
        // NOTE: `move` is a strict-order bucket (not a canonical-key event
        // bucket). We don't track it in EventEntry; it's synthesized by the
        // translator layer when comparing against replay wire format. See
        // pysim.py's flat-event list for the Python reference.
    }
}

// ============================================================ system_collision

/// Engine citation: `systems/CollisionSystem.java`. The engine doesn't
/// destroy units here — it only populates per-frame collision lists consumed
/// by shield/SD/attack. SimCore computes ranges directly via Euclidean
/// distance inside each later system, so this is a no-op. Kept in frame-order
/// as an explicit marker.
pub fn system_collision(_state: &mut SimState, _config: &SimConfig, _events: &mut Vec<EventEntry>) {
    // intentionally empty
}

// =========================================================== system_shield_decay

/// Engine citation: `ShieldSystem.processShieldDecay`. Citadel config has
/// `shieldDecay=0` — no-op. Hook preserved for future rule changes.
pub fn system_shield_decay(
    _state: &mut SimState,
    _config: &SimConfig,
    _events: &mut Vec<EventEntry>,
) {
    // intentionally empty (shieldDecayPerFrame=0 in Citadel config)
}

// ============================================================ system_shield_give

/// Engine citation: `ShieldSystem.processShieldGiveSystem`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// ShieldSystem.java:28-57).
///
/// For each live Support with `shieldPerUnit > 0`, find friendly mobile units
/// within `shield_range`. Apply shield amount once per (support, mobile) pair
/// across the entire action phase (engine's `shieldedAlready` HashSet).
///
/// Shield amount (verified via bytecode):
/// ```text
/// distance_from_mid = |13.5 - support.y| - 0.5
/// amount = shieldPerUnit + (13.0 - distance_from_mid) * bonusShieldPerY
///        = shieldPerUnit + (13.5 - |13.5 - support.y|) * bonusShieldPerY
/// ```
pub fn system_shield_give(
    state: &mut SimState,
    config: &SimConfig,
    events: &mut Vec<EventEntry>,
) {
    // Collect (structure_xy, player, shield_amount, range, uid) tuples first
    // so we can iterate mobiles with a fresh &mut without aliasing.
    struct SupportInfo {
        xy: (i32, i32),
        player: u8,
        uid: String,
        type_idx: i32,
        amount: f32,
        range: f32,
    }
    let mut supports: Vec<SupportInfo> = Vec::new();
    for s in state.structures.values() {
        if s.type_idx != IDX_SUPPORT || s.hp <= 0.0 {
            continue;
        }
        let spec = config.structure_spec(IDX_SUPPORT, s.upgraded);
        if spec.shield_per_unit <= 0.0 {
            continue;
        }
        // y is integer; expression stays in f32 end-to-end so that mixing
        // with spec.shield_per_unit / shield_bonus_per_y doesn't upcast.
        let y = s.xy.1 as f32;
        let y_factor: f32 = 13.5_f32 - (13.5_f32 - y).abs();
        let amount: f32 = spec.shield_per_unit + y_factor * spec.shield_bonus_per_y;
        supports.push(SupportInfo {
            xy: s.xy,
            player: s.player,
            uid: s.uid.clone(),
            type_idx: s.type_idx,
            amount,
            range: spec.shield_range,
        });
    }

    for sup in supports.iter() {
        // Iterate mobiles; shield them and mark support.shielded_already via
        // a secondary structure_mut pass afterwards (avoids simultaneous
        // borrow of state.structures + state.mobiles).
        let mut newly_shielded: Vec<String> = Vec::new();
        for m in state.mobiles.iter_mut() {
            if m.hp <= 0.0 || m.player != sup.player {
                continue;
            }
            // Check if support already shielded this mobile.
            if {
                let s = state.structures.get(&sup.xy).expect("support disappeared");
                s.shielded_already.iter().any(|u| u == &m.uid)
            } {
                continue;
            }
            if distance(sup.xy, m.xy) > sup.range + 1e-9 {
                continue;
            }
            m.shield += sup.amount;
            newly_shielded.push(m.uid.clone());
            events.push(EventEntry::Shield {
                src_xy: sup.xy,
                tgt_xy: m.xy,
                amount: sup.amount,
                type_id: sup.type_idx,
                src_uid: sup.uid.clone(),
                tgt_uid: m.uid.clone(),
                pid: sup.player as i32,
            });
        }
        if !newly_shielded.is_empty() {
            if let Some(s) = state.structures.get_mut(&sup.xy) {
                for uid in newly_shielded {
                    s.shielded_already.push(uid);
                }
            }
        }
    }
}

// =============================================================== system_breach

/// Engine citation: `BreachSystem.breachProcess`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// BreachSystem.java:21-37).
///
/// Gate: `finished_navigating AND reached_target`.
/// Effects: apply `breach_damage` to enemy HP, refund `metal_for_breach` SP
/// to own player, destroy the unit (and mark `breached=true` so system_attack
/// skips it).
pub fn system_breach(state: &mut SimState, config: &SimConfig, events: &mut Vec<EventEntry>) {
    // Collect (hp_damage, breacher_player) + breacher info so we can apply to
    // state.p1/p2 stats without borrow conflicts.
    let mut todos: Vec<(usize, f32, f32, (i32, i32), String, i32, u8)> = Vec::new();
    for (i, m) in state.mobiles.iter().enumerate() {
        if m.hp <= 0.0 {
            continue;
        }
        if !(m.finished_navigating && m.reached_target) {
            continue;
        }
        let spec = config.mobile_spec(m.type_idx);
        todos.push((
            i,
            spec.breach_damage,
            spec.metal_for_breach,
            m.xy,
            m.uid.clone(),
            m.type_idx,
            m.player,
        ));
    }
    for (i, breach_dmg, metal_refund, xy, uid, type_idx, player) in todos {
        // Enemy HP damage (PlayerStats.dealDamage).
        let enemy_pid = state.enemy_player(player);
        let es = state.player_stats_mut(enemy_pid);
        es.hp -= breach_dmg;
        // Own SP refund (PlayerStats.addToMetal + roundDecimals).
        let os = state.player_stats_mut(player);
        os.sp = round01(os.sp + metal_refund);
        // Death event (engine: BreachSystem.java:32-35 emits Death then Breach).
        events.push(EventEntry::Death {
            xy,
            type_id: type_idx,
            uid: uid.clone(),
            pid: player as i32,
            removed: false,
        });
        // (Breach event is a strict-order bucket; not part of our canonical set.)
        let m = &mut state.mobiles[i];
        m.breached = true;
        m.hp = 0.0;
    }
}

// ========================================================== system_self_destruct

/// Engine citation: `SelfDestructSystem.processSelfDestructs`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// SelfDestructSystem.java:24-63).
///
/// Gate: `finished_navigating AND NOT reached_target AND steps_taken >=
/// selfDestructStepsRequired`. Breachers are excluded via the `breached`
/// flag (they have reached_target=true anyway — defensive check).
///
/// Effects: deal `self_destruct_damage_walker` to enemy mobiles in range;
/// deal `self_destruct_damage_tower` to enemy structures in range; destroy
/// the self-destructor. Units stuck but haven't walked 5 tiles are silently
/// removed without AOE.
///
/// Event order per SDer matches engine's SelfDestructSystem.java:32-60:
///   1. Damage + optional Death for each victim (walker → tower).
///   2. GlobalSelfDestruct event (one or two, depending on damage equality).
///   3. GlobalDeath for the SDer itself.
pub fn system_self_destruct(
    state: &mut SimState,
    config: &SimConfig,
    events: &mut Vec<EventEntry>,
) {
    // Snapshot mobile indices that pass the gate — engine iterates a fixed
    // list; new deaths within the loop don't rescind their SD.
    struct SDPlan {
        mob_idx: usize,
        xy: (i32, i32),
        player: u8,
        type_idx: i32,
        uid: String,
        steps_taken: i32,
    }
    let mut plans: Vec<SDPlan> = Vec::new();
    for (i, m) in state.mobiles.iter().enumerate() {
        if !(m.finished_navigating && !m.reached_target) {
            continue;
        }
        if m.breached {
            continue;
        }
        plans.push(SDPlan {
            mob_idx: i,
            xy: m.xy,
            player: m.player,
            type_idx: m.type_idx,
            uid: m.uid.clone(),
            steps_taken: m.steps_taken,
        });
    }

    for p in plans.iter() {
        let spec = config.mobile_spec(p.type_idx).clone();
        if p.steps_taken >= spec.self_destruct_steps_required {
            let r = spec.self_destruct_range;
            let dmg_walker = spec.self_destruct_damage_walker;
            let dmg_tower = spec.self_destruct_damage_tower;

            let mut walker_locs: Vec<(i32, i32)> = Vec::new();
            let mut tower_locs: Vec<(i32, i32)> = Vec::new();

            // Walker victims — iterate mobiles in insertion order.
            for i in 0..state.mobiles.len() {
                if i == p.mob_idx {
                    continue;
                }
                let (other_hp, other_shield, other_player, other_uid, other_xy, other_type_idx) = {
                    let o = &state.mobiles[i];
                    if o.hp <= 0.0 || o.player == p.player {
                        continue;
                    }
                    if distance(p.xy, o.xy) > r + 1e-9 {
                        continue;
                    }
                    (o.hp, o.shield, o.player, o.uid.clone(), o.xy, o.type_idx)
                };
                let (new_hp, new_sh, died) = apply_damage(other_hp, other_shield, dmg_walker);
                {
                    let o = &mut state.mobiles[i];
                    o.hp = new_hp;
                    o.shield = new_sh;
                }
                walker_locs.push(other_xy);
                events.push(EventEntry::Damage {
                    xy: other_xy,
                    amount: dmg_walker,
                    type_id: other_type_idx,
                    victim_uid: other_uid.clone(),
                    pid: other_player as i32,
                });
                if died {
                    events.push(EventEntry::Death {
                        xy: other_xy,
                        type_id: other_type_idx,
                        uid: other_uid,
                        pid: other_player as i32,
                        removed: false,
                    });
                }
            }

            // Tower victims — iterate structures in insertion order.
            let struct_xys: Vec<(i32, i32)> = state.structures.keys().copied().collect();
            for sxy in struct_xys {
                let (s_hp, s_player, s_uid, s_type_idx) = {
                    let s = match state.structures.get(&sxy) {
                        Some(s) => s,
                        None => continue,
                    };
                    if s.hp <= 0.0 || s.player == p.player {
                        continue;
                    }
                    if distance(p.xy, sxy) > r + 1e-9 {
                        continue;
                    }
                    (s.hp, s.player, s.uid.clone(), s.type_idx)
                };
                // Structures have no shield — pass 0.0.
                let (new_hp, _sh, died) = apply_damage(s_hp, 0.0, dmg_tower);
                if let Some(s) = state.structures.get_mut(&sxy) {
                    s.hp = new_hp;
                }
                tower_locs.push(sxy);
                events.push(EventEntry::Damage {
                    xy: sxy,
                    amount: dmg_tower,
                    type_id: s_type_idx,
                    victim_uid: s_uid.clone(),
                    pid: s_player as i32,
                });
                if died {
                    events.push(EventEntry::Death {
                        xy: sxy,
                        type_id: s_type_idx,
                        uid: s_uid,
                        pid: s_player as i32,
                        removed: false,
                    });
                }
            }

            // Engine emits ONE GlobalSelfDestruct when walker==tower dmg,
            // else TWO. Citadel never trips the two-event branch on ranked
            // replays — but the code path is kept for fuzz parity.
            if dmg_walker == dmg_tower {
                let mut all_locs = walker_locs.clone();
                all_locs.extend(tower_locs.iter().copied());
                events.push(EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: all_locs,
                    damage: dmg_walker,
                    type_id: p.type_idx,
                    src_uid: p.uid.clone(),
                    pid: p.player as i32,
                });
            } else {
                events.push(EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: walker_locs,
                    damage: dmg_walker,
                    type_id: p.type_idx,
                    src_uid: p.uid.clone(),
                    pid: p.player as i32,
                });
                events.push(EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: tower_locs,
                    damage: dmg_tower,
                    type_id: p.type_idx,
                    src_uid: p.uid.clone(),
                    pid: p.player as i32,
                });
            }
        }
        // SDer always destroyed, even if under-ranged
        // (SelfDestructSystem.java:58-60).
        events.push(EventEntry::Death {
            xy: p.xy,
            type_id: p.type_idx,
            uid: p.uid.clone(),
            pid: p.player as i32,
            removed: false,
        });
        state.mobiles[p.mob_idx].hp = 0.0;
    }
}

// =============================================================== system_attack

/// Engine-source: `TargetAndAttackSystem.pickUnit`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// TargetAndAttackSystem.java).
///
/// Priority (all f32):
///   1. closer  (strict `<`)
///   2. equal-distance AND less HP+shield
///   3. equal-distance AND equal HP AND closer to start edge (|y - start_pos|)
///   4. equal-distance AND equal HP AND equal-to-start AND farther from center
///      (|x - 13.5| LARGER)
///   5. equal-distance AND equal HP AND equal-to-start AND equal-from-center
///      AND larger uid (as integer)
/// Pick target structure by tile. Structures are looked up on demand from
/// `state.structures` so the caller doesn't have to materialise
/// `Vec<&Structure>` per attacker. UID tiebreak is computed lazily — the
/// parse + compare only fire if distance+HP+start+center all tied (rare).
fn pick_target_struct(
    state: &SimState,
    attacker_xy: (i32, i32),
    attacker_player: u8,
    cand_xys: &[(i32, i32)],
) -> Option<(i32, i32)> {
    if cand_xys.is_empty() {
        return None;
    }
    let start_pos: f32 = if attacker_player == 1 { 0.0 } else { 28.0 };
    let mut closest_distance: f32 = 1.0e10;
    let mut closest_health: f32 = 1.0e10;
    let mut distance_to_player_start: f32 = 1.0e10;
    let mut distance_to_center: f32 = 0.0;
    let mut best_uid_cached: Option<i64> = None;
    let mut best_xy: Option<(i32, i32)> = None;

    for &xy in cand_xys {
        let cand = match state.structures.get(&xy) {
            Some(c) => c,
            None => continue,
        };
        if cand.hp <= 0.0 {
            continue;
        }
        let dx = (cand.xy.0 - attacker_xy.0) as f32;
        let dy = (cand.xy.1 - attacker_xy.1) as f32;
        let new_dist = (dx * dx + dy * dy).sqrt();
        let new_hp = cand.hp;
        let new_dist_start = (cand.xy.1 as f32 - start_pos).abs();
        let new_dist_center = (cand.xy.0 as f32 - 13.5_f32).abs();
        let closer = new_dist < closest_distance;
        let equal_distance = new_dist == closest_distance;
        let less_hp = new_hp < closest_health;
        let equal_hp = new_hp == closest_health;
        let closer_to_start = new_dist_start < distance_to_player_start;
        let equal_to_start = new_dist_start == distance_to_player_start;
        let farther_from_center = new_dist_center > distance_to_center;
        let equal_from_center = new_dist_center == distance_to_center;
        let first_cond = closer
            || (equal_distance && less_hp)
            || (equal_distance && equal_hp && closer_to_start)
            || (equal_distance && equal_hp && equal_to_start && farther_from_center);
        let uid_tiebreak_needed = equal_distance
            && equal_hp
            && equal_to_start
            && equal_from_center
            && !first_cond;
        let game_object_id_larger = if !uid_tiebreak_needed {
            false
        } else {
            let new_uid = cand.uid.as_str();
            let new_int: Result<i64, _> = new_uid.parse();
            match (new_int, best_uid_cached) {
                (Ok(a), Some(b)) => a > b,
                (Ok(_), None) => true,
                _ => {
                    if let Some(best_xy) = best_xy {
                        if let Some(best) = state.structures.get(&best_xy) {
                            new_uid > best.uid.as_str()
                        } else {
                            true
                        }
                    } else {
                        true
                    }
                }
            }
        };
        let second_cond = uid_tiebreak_needed && game_object_id_larger;
        if !first_cond && !second_cond {
            continue;
        }
        best_xy = Some(cand.xy);
        closest_distance = new_dist;
        closest_health = new_hp;
        distance_to_player_start = new_dist_start;
        distance_to_center = new_dist_center;
        best_uid_cached = cand.uid.parse().ok();
    }
    best_xy
}

/// Pick target mobile by index. UID tiebreak is computed lazily via index
/// lookup into `state.mobiles` so candidate records don't need an owned
/// `String` per entry.
fn pick_target_mobile_idx(
    state: &SimState,
    attacker_xy: (i32, i32),
    attacker_player: u8,
    candidates: &[crate::state::WalkerCand],
) -> Option<usize> {
    if candidates.is_empty() {
        return None;
    }
    let start_pos: f32 = if attacker_player == 1 { 0.0 } else { 28.0 };
    let mut closest_distance: f32 = 1.0e10;
    let mut closest_health: f32 = 1.0e10;
    let mut distance_to_player_start: f32 = 1.0e10;
    let mut distance_to_center: f32 = 0.0;
    let mut best_idx: Option<usize> = None;
    let mut best_uid_cached: Option<i64> = None;

    for cand in candidates {
        if cand.hp <= 0.0 {
            continue;
        }
        let dx = (cand.xy.0 - attacker_xy.0) as f32;
        let dy = (cand.xy.1 - attacker_xy.1) as f32;
        let new_dist = (dx * dx + dy * dy).sqrt();
        let new_hp = cand.hp + cand.shield;
        let new_dist_start = (cand.xy.1 as f32 - start_pos).abs();
        let new_dist_center = (cand.xy.0 as f32 - 13.5_f32).abs();
        let closer = new_dist < closest_distance;
        let equal_distance = new_dist == closest_distance;
        let less_hp = new_hp < closest_health;
        let equal_hp = new_hp == closest_health;
        let closer_to_start = new_dist_start < distance_to_player_start;
        let equal_to_start = new_dist_start == distance_to_player_start;
        let farther_from_center = new_dist_center > distance_to_center;
        let equal_from_center = new_dist_center == distance_to_center;
        let first_cond = closer
            || (equal_distance && less_hp)
            || (equal_distance && equal_hp && closer_to_start)
            || (equal_distance && equal_hp && equal_to_start && farther_from_center);
        let uid_tiebreak_needed = equal_distance
            && equal_hp
            && equal_to_start
            && equal_from_center
            && !first_cond;
        let game_object_id_larger = if !uid_tiebreak_needed {
            false
        } else {
            let new_uid = state.mobiles[cand.idx].uid.as_str();
            let new_int: Result<i64, _> = new_uid.parse();
            match (new_int, best_uid_cached) {
                (Ok(a), Some(b)) => a > b,
                (Ok(_), None) => true,
                _ => {
                    if let Some(bi) = best_idx {
                        new_uid > state.mobiles[bi].uid.as_str()
                    } else {
                        true
                    }
                }
            }
        };
        let second_cond = uid_tiebreak_needed && game_object_id_larger;
        if !first_cond && !second_cond {
            continue;
        }
        best_idx = Some(cand.idx);
        closest_distance = new_dist;
        closest_health = new_hp;
        distance_to_player_start = new_dist_start;
        distance_to_center = new_dist_center;
        best_uid_cached = state.mobiles[cand.idx].uid.parse().ok();
    }
    best_idx
}

/// Exact port of `TargetAndAttackSystem.processTargeting`.
///
/// Engine citation: `research/engine_decompiled/sources/com/c1games/terminal/
/// game/systems/TargetAndAttackSystem.java:25-120`. Attackers fire in game-
/// object insertion order (structures first — only turrets attack — then
/// mobiles). Each picks HIGH priority (walker targets) first; if no alive
/// walker in range, falls back to LOW priority (structure targets). Damage
/// applied IMMEDIATELY via `HealthComponent.dealDamageToHealthComponent`.
///
/// Attackers that died this frame (to SD or earlier attacks) still fire —
/// engine gate is `isEnabled() || attackWhenDestroyed`. Breached mobiles are
/// explicitly excluded (they were `disableGameObject`'d in BreachSystem).
pub fn system_attack(state: &mut SimState, config: &SimConfig, events: &mut Vec<EventEntry>) {
    // Snapshot attacker identifiers into scratch (reused across frames).
    state.scratch.attacker_struct_xys.clear();
    for (xy, s) in state.structures.iter() {
        if s.type_idx == IDX_TURRET {
            state.scratch.attacker_struct_xys.push(*xy);
        }
    }
    state.scratch.attacker_mobile_idxs.clear();
    for (i, m) in state.mobiles.iter().enumerate() {
        if !m.breached {
            state.scratch.attacker_mobile_idxs.push(i);
        }
    }

    // Turrets first. Iterate by index — the scratch Vec is stable across
    // the loop body because fire_one only touches state.scratch.walker_cands
    // + state.scratch.struct_cand_xys (disjoint buffers).
    let n_turrets = state.scratch.attacker_struct_xys.len();
    for i in 0..n_turrets {
        let sxy = state.scratch.attacker_struct_xys[i];
        let (att_xy, att_player, dmg_w, dmg_t, att_range) = {
            let s = match state.structures.get(&sxy) {
                Some(s) => s,
                None => continue,
            };
            let spec = config.structure_spec(IDX_TURRET, s.upgraded);
            if spec.attack_damage_walker <= 0.0 && spec.attack_damage_tower <= 0.0 {
                continue;
            }
            (s.xy, s.player, spec.attack_damage_walker,
             spec.attack_damage_tower, spec.attack_range)
        };
        fire_one(state, events, att_xy, att_player, dmg_w, dmg_t, att_range);
    }

    // Mobile attackers.
    let n_mobs = state.scratch.attacker_mobile_idxs.len();
    for i in 0..n_mobs {
        let mi = state.scratch.attacker_mobile_idxs[i];
        let (att_xy, att_player, dmg_w, dmg_t, att_range) = {
            let m = match state.mobiles.get(mi) {
                Some(m) => m,
                None => continue,
            };
            if m.breached {
                continue;
            }
            let spec = config.mobile_spec(m.type_idx);
            if spec.attack_damage_walker <= 0.0 && spec.attack_damage_tower <= 0.0 {
                continue;
            }
            (m.xy, m.player, spec.attack_damage_walker,
             spec.attack_damage_tower, spec.attack_range)
        };
        fire_one(state, events, att_xy, att_player, dmg_w, dmg_t, att_range);
    }
}

/// Pick target + apply damage + emit events. Separated from `system_attack`
/// to share the call between turret and mobile attackers.
fn fire_one(
    state: &mut SimState,
    events: &mut Vec<EventEntry>,
    att_xy: (i32, i32),
    att_player: u8,
    dmg_walker: f32,
    dmg_tower: f32,
    att_range: f32,
) {
    let r_sq = att_range * att_range + 1e-9;

    // Walker candidate list (mobiles in range, alive, enemy) — scratch-reused.
    state.scratch.walker_cands.clear();
    if dmg_walker > 0.0 {
        for (i, m) in state.mobiles.iter().enumerate() {
            if m.hp <= 0.0 || m.player == att_player {
                continue;
            }
            let dx = (m.xy.0 - att_xy.0) as f32;
            let dy = (m.xy.1 - att_xy.1) as f32;
            if dx * dx + dy * dy > r_sq {
                continue;
            }
            state.scratch.walker_cands.push(crate::state::WalkerCand {
                idx: i,
                hp: m.hp,
                shield: m.shield,
                xy: m.xy,
            });
        }
    }
    let mut target_mobile_idx: Option<usize> = None;
    if !state.scratch.walker_cands.is_empty() {
        // SAFETY: walker_cands is borrowed read-only for this call; picker
        // reads state.mobiles (disjoint field). No aliasing.
        let cand_ptr = state.scratch.walker_cands.as_ptr();
        let cand_len = state.scratch.walker_cands.len();
        let cand_slice = unsafe { std::slice::from_raw_parts(cand_ptr, cand_len) };
        target_mobile_idx = pick_target_mobile_idx(state, att_xy, att_player, cand_slice);
    }
    let mut target_struct_xy: Option<(i32, i32)> = None;
    if target_mobile_idx.is_none() && dmg_tower > 0.0 {
        // Structure candidates — scratch-reused.
        state.scratch.struct_cand_xys.clear();
        for (xy, s) in state.structures.iter() {
            if s.hp <= 0.0 || s.player == att_player {
                continue;
            }
            let dx = (xy.0 - att_xy.0) as f32;
            let dy = (xy.1 - att_xy.1) as f32;
            if dx * dx + dy * dy > r_sq {
                continue;
            }
            state.scratch.struct_cand_xys.push(*xy);
        }
        if !state.scratch.struct_cand_xys.is_empty() {
            // SAFETY: same disjoint-field argument as walker path above.
            let xys_ptr = state.scratch.struct_cand_xys.as_ptr();
            let xys_len = state.scratch.struct_cand_xys.len();
            let xys_slice = unsafe { std::slice::from_raw_parts(xys_ptr, xys_len) };
            target_struct_xy = pick_target_struct(state, att_xy, att_player, xys_slice);
        }
    }

    // Apply damage + emit events.
    if let Some(mi) = target_mobile_idx {
        let (hp_before, shield_before, victim_uid, victim_xy, victim_type_idx, victim_player) = {
            let m = &state.mobiles[mi];
            (m.hp, m.shield, m.uid.clone(), m.xy, m.type_idx, m.player)
        };
        let (new_hp, new_sh, died) = apply_damage(hp_before, shield_before, dmg_walker);
        {
            let m = &mut state.mobiles[mi];
            m.hp = new_hp;
            m.shield = new_sh;
        }
        events.push(EventEntry::Damage {
            xy: victim_xy,
            amount: dmg_walker,
            type_id: victim_type_idx,
            victim_uid: victim_uid.clone(),
            pid: victim_player as i32,
        });
        if died {
            events.push(EventEntry::Death {
                xy: victim_xy,
                type_id: victim_type_idx,
                uid: victim_uid,
                pid: victim_player as i32,
                removed: false,
            });
        }
    } else if let Some(sxy) = target_struct_xy {
        let (hp_before, victim_uid, victim_type_idx, victim_player) = {
            let s = &state.structures[&sxy];
            (s.hp, s.uid.clone(), s.type_idx, s.player)
        };
        let (new_hp, _sh, died) = apply_damage(hp_before, 0.0, dmg_tower);
        if let Some(s) = state.structures.get_mut(&sxy) {
            s.hp = new_hp;
        }
        events.push(EventEntry::Damage {
            xy: sxy,
            amount: dmg_tower,
            type_id: victim_type_idx,
            victim_uid: victim_uid.clone(),
            pid: victim_player as i32,
        });
        if died {
            events.push(EventEntry::Death {
                xy: sxy,
                type_id: victim_type_idx,
                uid: victim_uid,
                pid: victim_player as i32,
                removed: false,
            });
        }
    }
}

// ================================================================ clear_destroyed

/// Pop dead units from state and keep pathfinders in sync. Does NOT emit
/// death events — those fire inline at each kill site.
///
/// Engine citation: `Game.clearDestroyedGameObjects` (Game.java:184-193).
/// Pure list-cleanup; no events.
pub fn clear_destroyed(state: &mut SimState, _events: &mut Vec<EventEntry>) {
    // Collect dead structure xys first.
    let dead_xys: Vec<(i32, i32)> = state
        .structures
        .iter()
        .filter(|(_, s)| s.hp <= 0.0)
        .map(|(xy, _)| *xy)
        .collect();
    for xy in dead_xys {
        state.structures.shift_remove(&xy);
        state.pending_removal_xys.shift_remove(&xy);
        if let Some(pfs) = state.pathfinders.as_mut() {
            for pf in pfs.values_mut() {
                pf.remove(xy.0, xy.1);
            }
        }
    }
    state.mobiles.retain(|m| m.hp > 0.0);
}

// ============================================================= system_remove_own_unit

/// Engine citation: `RemoveOwnUnitSystem.removeOwnUnitProcess`
/// (research/engine_decompiled/sources/com/c1games/terminal/game/systems/
/// RemoveOwnUnitSystem.java:20-34).
///
/// For each structure with `turn_start_removal` set, if
/// `turn_start_removal + turns_required_to_remove <= current_turn`, the
/// structure is refunded and destroyed:
///   * `refund_metal = cost_total * (hp / max_hp) * refund_pct`
///   * refund credited via `round01(sp + refund)`.
///   * Death event emitted with `removed=true`.
///   * Structure popped from state.structures and from pathfinders.
pub fn system_remove_own_unit(
    state: &mut SimState,
    config: &SimConfig,
    events: &mut Vec<EventEntry>,
) {
    if state.pending_removal_xys.is_empty() {
        return;
    }
    let current_turn = state.turn;
    // Collect plan + scrub stale entries.
    struct Plan {
        xy: (i32, i32),
        refund_metal: f32,
        uid: String,
        type_idx: i32,
        player: u8,
    }
    let mut plans: Vec<Plan> = Vec::new();
    let pending_xys: Vec<(i32, i32)> = state.pending_removal_xys.keys().copied().collect();
    for xy in pending_xys {
        let (ready, info) = {
            let s = match state.structures.get(&xy) {
                Some(s) => s,
                None => {
                    // Stale entry — scrub.
                    state.pending_removal_xys.shift_remove(&xy);
                    continue;
                }
            };
            if s.turn_start_removal.is_none() || s.hp <= 0.0 {
                state.pending_removal_xys.shift_remove(&xy);
                continue;
            }
            let spec = config.structure_spec(s.type_idx, s.upgraded);
            let t_start = s.turn_start_removal.unwrap();
            let ready = t_start + spec.turns_required_to_remove <= current_turn;
            if !ready {
                (false, None)
            } else {
                let base_spec = config.structure_spec(s.type_idx, false);
                let total_cost = base_spec.cost_sp + if s.upgraded { spec.cost_sp } else { 0.0 };
                let hp_pct = if spec.hp > 0.0 { s.hp / spec.hp } else { 0.0 };
                let refund_metal = total_cost * hp_pct * spec.refund_pct;
                (
                    true,
                    Some((refund_metal, s.uid.clone(), s.type_idx, s.player)),
                )
            }
        };
        if ready {
            let (refund_metal, uid, type_idx, player) = info.unwrap();
            plans.push(Plan {
                xy,
                refund_metal,
                uid,
                type_idx,
                player,
            });
        }
    }
    for pl in plans {
        let p_stats = state.player_stats_mut(pl.player);
        p_stats.sp = round01(p_stats.sp + pl.refund_metal);
        events.push(EventEntry::Death {
            xy: pl.xy,
            type_id: pl.type_idx,
            uid: pl.uid,
            pid: pl.player as i32,
            removed: true,
        });
        state.structures.shift_remove(&pl.xy);
        state.pending_removal_xys.shift_remove(&pl.xy);
        if let Some(pfs) = state.pathfinders.as_mut() {
            for pf in pfs.values_mut() {
                pf.remove(pl.xy.0, pl.xy.1);
            }
        }
    }
}
