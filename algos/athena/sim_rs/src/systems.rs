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

/// Push an event into the per-frame buffer — completely elided when the
/// `instrumented` feature is disabled. FAST builds don't even CONSTRUCT
/// the EventEntry (no String clones, no alloc). INSTRUMENTED builds (the
/// default, used by tests + cross-validator) behave exactly like a direct
/// `events.push(...)`.
#[cfg(feature = "instrumented")]
macro_rules! push_event {
    ($events:expr, $entry:expr) => { $events.push($entry) };
}
#[cfg(not(feature = "instrumented"))]
macro_rules! push_event {
    ($events:expr, $entry:expr) => { {} };
}

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
/// Public so external benches can pre-warm a template state once and share
/// the built pathfinders via `SimState::clone()` across sims, amortising the
/// ~2.5 µs `PathFinder::new` cost over many iterations.
pub fn ensure_pathfinders(state: &mut SimState) {
    use crate::pathfinder::{edge_direction, PathFinder};
    // Fast-path: at least one slot is occupied → all needed ones were built
    // on the first call (and none are invalidated mid-phase).
    if state.pathfinders.iter().any(|p| p.is_some()) {
        return;
    }
    let walls: Vec<(i32, i32)> = state.structures.keys().copied().collect();
    // Determine which edges are needed via a single mobile scan.
    let mut needed: [bool; 4] = [false; 4];
    for m in state.mobiles.iter() {
        if m.hp <= 0.0 || m.finished_navigating { continue; }
        if (0..4).contains(&m.target_edge) { needed[m.target_edge as usize] = true; }
    }
    // Collect mobile spawn → path-walk seeds per edge, so we can do a
    // targeted cache warmup after pathfinder construction.
    let mut per_edge_starts: [Vec<(i32, i32, u8)>; 4] =
        [Vec::new(), Vec::new(), Vec::new(), Vec::new()];
    for m in state.mobiles.iter() {
        if m.hp <= 0.0 || m.finished_navigating { continue; }
        if (0..4).contains(&m.target_edge) {
            per_edge_starts[m.target_edge as usize].push((m.xy.0, m.xy.1, m.last_move as u8));
        }
    }
    for edge in [EDGE_TOP_RIGHT, EDGE_TOP_LEFT, EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT] {
        if !needed[edge as usize] { continue; }
        let perfects = edge_tiles_slice(edge);
        let mut pf = PathFinder::new(ARENA, edge_direction(edge), &walls, perfects);
        // Walk each mobile's path from (xy, last_move) until the pathfinder
        // returns the same tile (stuck signal), priming the step_cache for
        // exactly the tiles the sim will query. Matches the on-demand BFS
        // path semantically — no scan-order state corruption.
        for &(sx, sy, slm) in per_edge_starts[edge as usize].iter() {
            let mut cx = sx;
            let mut cy = sy;
            let mut lm = slm;
            for _ in 0..192 {
                let (nx, ny) = pf.get_step(cx, cy, lm);
                if (nx, ny) == (cx, cy) { break; }
                lm = if ny == cy { HORIZONTAL } else { VERTICAL };
                cx = nx;
                cy = ny;
            }
        }
        // NOTE: the old `pf.prewarm_step_cache()` full-arena scan has been
        // replaced by `warmup_via_mobile_paths` below. Scan-order prewarm
        // produced pathfinder-state divergence with the Python reference on
        // 6/10,000 `max_budget` fuzz configs (cross_validate 2026-04-24):
        // prewarm's per-tile get_step invocations leave `pathlength` /
        // `status` in an order-dependent state that differs from a fresh
        // on-demand BFS for certain wall mazes, and the wrong result gets
        // cached. Walking only the tiles each mobile will actually query
        // produces a safe warm cache AND matches the subsequent sim's
        // access pattern exactly (zero wasted cache entries).
        state.pathfinders[edge as usize] = Some(Box::new(pf));
    }
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

    let SimState { pathfinders, mobiles, .. } = state;
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
        let pf = pathfinders[m.target_edge as usize]
            .as_mut()
            .expect("per-edge pathfinder missing");
        let (nx, ny) = pf.get_step(m.xy.0, m.xy.1, m.last_move as u8);
        m.last_move = if ny == m.xy.1 { HORIZONTAL as i32 } else { VERTICAL as i32 };
        let dx = nx - m.xy.0;
        let dy = ny - m.xy.1;
        if dx == 0 && dy == 0 {
            m.finished_navigating = true;
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
    // Seed the support-xy scratch list (reuses buffer capacity). Same
    // dirty-flag caching as `system_attack`: rebuild only when the live
    // structure set changes. The list may contain xys whose support died
    // mid-frame (SD this turn); the inner loop rechecks hp > 0.0.
    if state.scratch.structures_dirty {
        state.scratch.support_xys.clear();
        // NOTE: we can't clear `structures_dirty` here because `system_attack`
        // also consumes it — it runs AFTER shield_give in the frame order.
        // Instead we leave the flag set; system_attack's builder below reads
        // from the same structures list and writes its own cached state.
        for (xy, s) in state.structures.iter() {
            if s.type_idx == IDX_SUPPORT && s.hp > 0.0 {
                state.scratch.support_xys.push(*xy);
            }
        }
    }
    if state.scratch.support_xys.is_empty() {
        return;
    }

    let n = state.scratch.support_xys.len();
    for i in 0..n {
        let sup_xy = state.scratch.support_xys[i];
        // Fetch support info fresh each pass (may have died to SD this frame).
        let (sup_player, sup_type_idx, sup_amount, sup_range) = {
            let s = match state.structures.get(&sup_xy) {
                Some(s) => s,
                None => continue,
            };
            if s.hp <= 0.0 {
                continue;
            }
            let spec = config.structure_spec(IDX_SUPPORT, s.upgraded);
            if spec.shield_per_unit <= 0.0 {
                continue;
            }
            let y = s.xy.1 as f32;
            let y_factor: f32 = 13.5_f32 - (13.5_f32 - y).abs();
            let amount = spec.shield_per_unit + y_factor * spec.shield_bonus_per_y;
            (s.player, s.type_idx, amount, spec.shield_range)
        };
        state.scratch.newly_shielded.clear();
        for m in state.mobiles.iter_mut() {
            if m.hp <= 0.0 || m.player != sup_player {
                continue;
            }
            if {
                let s = state.structures.get(&sup_xy).expect("support disappeared");
                s.shielded_already.iter().any(|u| *u == m.uid)
            } {
                continue;
            }
            if distance(sup_xy, m.xy) > sup_range + 1e-9 {
                continue;
            }
            m.shield += sup_amount;
            state.scratch.newly_shielded.push(m.uid);
            #[cfg(feature = "instrumented")]
            {
                let sup_uid = state.structures.get(&sup_xy).unwrap().uid;
                push_event!(events, EventEntry::Shield {
                    src_xy: sup_xy,
                    tgt_xy: m.xy,
                    amount: sup_amount,
                    type_id: sup_type_idx,
                    src_uid: sup_uid,
                    tgt_uid: m.uid,
                    pid: sup_player as i32,
                });
            }
            #[cfg(not(feature = "instrumented"))]
            {
                let _ = events;
                let _ = sup_type_idx;
            }
        }
        if !state.scratch.newly_shielded.is_empty() {
            if let Some(s) = state.structures.get_mut(&sup_xy) {
                for uid in state.scratch.newly_shielded.drain(..) {
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
    let mut todos: Vec<(usize, f32, f32, (i32, i32), u32, i32, u8)> = Vec::new();
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
            m.uid,
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
        push_event!(events, EventEntry::Death {
            xy,
            type_id: type_idx,
            uid,
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
        uid: u32,
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
            uid: m.uid,
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
                    (o.hp, o.shield, o.player, o.uid, o.xy, o.type_idx)
                };
                let (new_hp, new_sh, died) = apply_damage(other_hp, other_shield, dmg_walker);
                {
                    let o = &mut state.mobiles[i];
                    o.hp = new_hp;
                    o.shield = new_sh;
                }
                walker_locs.push(other_xy);
                push_event!(events, EventEntry::Damage {
                    xy: other_xy,
                    amount: dmg_walker,
                    type_id: other_type_idx,
                    victim_uid: other_uid,
                    pid: other_player as i32,
                });
                if died {
                    push_event!(events, EventEntry::Death {
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
                    (s.hp, s.player, s.uid, s.type_idx)
                };
                // Structures have no shield — pass 0.0.
                let (new_hp, _sh, died) = apply_damage(s_hp, 0.0, dmg_tower);
                if let Some(s) = state.structures.get_mut(&sxy) {
                    s.hp = new_hp;
                }
                tower_locs.push(sxy);
                push_event!(events, EventEntry::Damage {
                    xy: sxy,
                    amount: dmg_tower,
                    type_id: s_type_idx,
                    victim_uid: s_uid,
                    pid: s_player as i32,
                });
                if died {
                    push_event!(events, EventEntry::Death {
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
                push_event!(events, EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: all_locs,
                    damage: dmg_walker,
                    type_id: p.type_idx,
                    src_uid: p.uid,
                    pid: p.player as i32,
                });
            } else {
                push_event!(events, EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: walker_locs,
                    damage: dmg_walker,
                    type_id: p.type_idx,
                    src_uid: p.uid,
                    pid: p.player as i32,
                });
                push_event!(events, EventEntry::SelfDestruct {
                    src_xy: p.xy,
                    target_xys: tower_locs,
                    damage: dmg_tower,
                    type_id: p.type_idx,
                    src_uid: p.uid,
                    pid: p.player as i32,
                });
            }
        }
        // SDer always destroyed, even if under-ranged
        // (SelfDestructSystem.java:58-60).
        push_event!(events, EventEntry::Death {
            xy: p.xy,
            type_id: p.type_idx,
            uid: p.uid,
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
    let mut best_uid: Option<u32> = None;
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
        // Engine parses the (string) UID as i64 and picks the larger value;
        // our UIDs are u32 from a monotone counter so direct > compare matches.
        let game_object_id_larger = uid_tiebreak_needed
            && best_uid.map_or(true, |b| cand.uid > b);
        let second_cond = uid_tiebreak_needed && game_object_id_larger;
        if !first_cond && !second_cond {
            continue;
        }
        best_xy = Some(cand.xy);
        closest_distance = new_dist;
        closest_health = new_hp;
        distance_to_player_start = new_dist_start;
        distance_to_center = new_dist_center;
        best_uid = Some(cand.uid);
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
    let mut best_uid: Option<u32> = None;

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
        let new_uid = state.mobiles[cand.idx].uid;
        // Engine parses the (string) UID as i64 and picks the larger value;
        // our UIDs are u32 from a monotone counter so direct > compare matches.
        let game_object_id_larger = uid_tiebreak_needed
            && best_uid.map_or(true, |b| new_uid > b);
        let second_cond = uid_tiebreak_needed && game_object_id_larger;
        if !first_cond && !second_cond {
            continue;
        }
        best_idx = Some(cand.idx);
        closest_distance = new_dist;
        closest_health = new_hp;
        distance_to_player_start = new_dist_start;
        distance_to_center = new_dist_center;
        best_uid = Some(new_uid);
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
    // Snapshot attacker identifiers + per-player enemy lists + pre-extracted
    // turret info into scratch (reused across frames, capacity preserved).
    // Rebuild only when `structures_dirty` — set true on every structure
    // death in `clear_destroyed` + `system_remove_own_unit`. Structures don't
    // spawn mid-action-phase, and turret damage/range is a spec constant, so
    // as long as the live structure SET is unchanged, the scratch lists are
    // also unchanged. Eliminates a per-frame 108-structure IndexMap scan.
    if state.scratch.structures_dirty {
        state.scratch.turret_infos.clear();
        state.scratch.p1_struct_xys.clear();
        state.scratch.p2_struct_xys.clear();
        state.scratch.turret_enemy_cands_flat.clear();
        for (xy, s) in state.structures.iter() {
            if s.hp <= 0.0 {
                continue;
            }
            if s.type_idx == IDX_TURRET {
                let spec = config.structure_spec(IDX_TURRET, s.upgraded);
                if spec.attack_damage_walker > 0.0 || spec.attack_damage_tower > 0.0 {
                    let r_sq = spec.attack_range * spec.attack_range + 1e-9;
                    state.scratch.turret_infos.push(crate::state::TurretInfo {
                        xy: *xy,
                        player: s.player,
                        dmg_walker: spec.attack_damage_walker,
                        dmg_tower: spec.attack_damage_tower,
                        range_sq_eps: r_sq,
                        range_bound: r_sq.sqrt().ceil() as i32,
                        enemy_cand_start: 0,
                        enemy_cand_end: 0,
                    });
                }
            }
            // All live structures count as targets.
            match s.player {
                1 => state.scratch.p1_struct_xys.push(*xy),
                2 => state.scratch.p2_struct_xys.push(*xy),
                _ => {}
            }
        }
        // Precompute per-turret enemy-structure candidates within range.
        // This catches the hot fallback path where no walker is in range and
        // the turret would otherwise scan the entire enemy roster. Runs once
        // per wall-death; O(turrets × enemy_structures) ≈ 30 × 54 = 1620 ops.
        let n_turrets = state.scratch.turret_infos.len();
        for i in 0..n_turrets {
            let ti = state.scratch.turret_infos[i];
            let enemy_list: &[(i32, i32)] = if ti.player == 1 {
                state.scratch.p2_struct_xys.as_slice()
            } else {
                state.scratch.p1_struct_xys.as_slice()
            };
            let r_sq = ti.range_sq_eps;
            let start = state.scratch.turret_enemy_cands_flat.len() as u32;
            for &(sx, sy) in enemy_list {
                let dx = (sx - ti.xy.0) as f32;
                let dy = (sy - ti.xy.1) as f32;
                if dx * dx + dy * dy <= r_sq {
                    state.scratch.turret_enemy_cands_flat.push((sx, sy));
                }
            }
            let end = state.scratch.turret_enemy_cands_flat.len() as u32;
            state.scratch.turret_infos[i].enemy_cand_start = start;
            state.scratch.turret_infos[i].enemy_cand_end = end;
        }
        state.scratch.structures_dirty = false;
    }
    // Single pass over mobiles: populates both attacker_mobile_idxs (for
    // the mobile-attacker phase) and the per-player live-mobile cache +
    // bbox (consumed by turret fire_one). Cheaper than two passes.
    state.scratch.attacker_mobile_idxs.clear();
    state.scratch.p1_live_mobs.clear();
    state.scratch.p2_live_mobs.clear();
    let mut p1_lo_x = i32::MAX; let mut p1_hi_x = i32::MIN;
    let mut p1_lo_y = i32::MAX; let mut p1_hi_y = i32::MIN;
    let mut p2_lo_x = i32::MAX; let mut p2_hi_x = i32::MIN;
    let mut p2_lo_y = i32::MAX; let mut p2_hi_y = i32::MIN;
    for (i, m) in state.mobiles.iter().enumerate() {
        if !m.breached {
            state.scratch.attacker_mobile_idxs.push(i);
        }
        if m.hp <= 0.0 {
            continue;
        }
        let cand = crate::state::WalkerCand {
            idx: i, hp: m.hp, shield: m.shield, xy: m.xy,
        };
        match m.player {
            1 => {
                state.scratch.p1_live_mobs.push(cand);
                if m.xy.0 < p1_lo_x { p1_lo_x = m.xy.0; }
                if m.xy.0 > p1_hi_x { p1_hi_x = m.xy.0; }
                if m.xy.1 < p1_lo_y { p1_lo_y = m.xy.1; }
                if m.xy.1 > p1_hi_y { p1_hi_y = m.xy.1; }
            }
            2 => {
                state.scratch.p2_live_mobs.push(cand);
                if m.xy.0 < p2_lo_x { p2_lo_x = m.xy.0; }
                if m.xy.0 > p2_hi_x { p2_hi_x = m.xy.0; }
                if m.xy.1 < p2_lo_y { p2_lo_y = m.xy.1; }
                if m.xy.1 > p2_hi_y { p2_hi_y = m.xy.1; }
            }
            _ => {}
        }
    }
    state.scratch.p1_mob_bbox = (p1_lo_x, p1_hi_x, p1_lo_y, p1_hi_y);
    state.scratch.p2_mob_bbox = (p2_lo_x, p2_hi_x, p2_lo_y, p2_hi_y);

    // Turrets first — iterate the pre-extracted info array.
    // Outer-loop fast skip: a turret with no enemy structs in range AND
    // no enemy mobile within its bbox + range fires nothing this frame.
    // Skipping at this level avoids the full `fire_one_turret` call
    // including its scratch clear + two branch-heavy targeting checks.
    let n_turrets = state.scratch.turret_infos.len();
    let (p1_bbox, p2_bbox) = (state.scratch.p1_mob_bbox, state.scratch.p2_mob_bbox);
    let (p1_any, p2_any) = (!state.scratch.p1_live_mobs.is_empty(),
                             !state.scratch.p2_live_mobs.is_empty());
    for i in 0..n_turrets {
        let ti = state.scratch.turret_infos[i];
        let has_struct_cands = ti.enemy_cand_end > ti.enemy_cand_start;
        let (enemy_any, bbox) = if ti.player == 1 { (p2_any, p2_bbox) } else { (p1_any, p1_bbox) };
        let mob_near = enemy_any
            && ti.xy.0 >= bbox.0 - ti.range_bound
            && ti.xy.0 <= bbox.1 + ti.range_bound
            && ti.xy.1 >= bbox.2 - ti.range_bound
            && ti.xy.1 <= bbox.3 + ti.range_bound;
        if !mob_near && !has_struct_cands {
            continue;
        }
        fire_one_turret(
            state, events, ti.xy, ti.player,
            ti.dmg_walker, ti.dmg_tower, ti.range_sq_eps,
            ti.enemy_cand_start as usize, ti.enemy_cand_end as usize,
        );
    }

    // Mobile attackers. Mobiles move every frame, so we can't pre-filter
    // their enemy-structure candidates; fall back to the original per-frame
    // distance-scan path (mobile attackers are rare vs. turret count).
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
        let r_sq_eps = att_range * att_range + 1e-9;
        fire_one_mobile(state, events, att_xy, att_player, dmg_w, dmg_t, r_sq_eps);
    }
}

/// Mobile-attacker fallback: original per-frame distance-scan path (no
/// pre-filter cache, since mobiles move every frame). Identical structure
/// to `fire_one_turret` minus the enemy_cand slice optimization.
fn fire_one_mobile(
    state: &mut SimState,
    events: &mut Vec<EventEntry>,
    att_xy: (i32, i32),
    att_player: u8,
    dmg_walker: f32,
    dmg_tower: f32,
    r_sq_eps: f32,
) {
    let r_sq = r_sq_eps;
    state.scratch.walker_cands.clear();
    if dmg_walker > 0.0 {
        for (i, m) in state.mobiles.iter().enumerate() {
            if m.hp <= 0.0 || m.player == att_player { continue; }
            let dx = (m.xy.0 - att_xy.0) as f32;
            let dy = (m.xy.1 - att_xy.1) as f32;
            if dx * dx + dy * dy > r_sq { continue; }
            state.scratch.walker_cands.push(crate::state::WalkerCand {
                idx: i, hp: m.hp, shield: m.shield, xy: m.xy,
            });
        }
    }
    let mut target_mobile_idx: Option<usize> = None;
    let wcl = state.scratch.walker_cands.len();
    if wcl == 1 {
        target_mobile_idx = Some(state.scratch.walker_cands[0].idx);
    } else if wcl > 1 {
        let cand_ptr = state.scratch.walker_cands.as_ptr();
        let cand_slice = unsafe { std::slice::from_raw_parts(cand_ptr, wcl) };
        target_mobile_idx = pick_target_mobile_idx(state, att_xy, att_player, cand_slice);
    }
    let mut target_struct_xy: Option<(i32, i32)> = None;
    if target_mobile_idx.is_none() && dmg_tower > 0.0 {
        state.scratch.struct_cand_xys.clear();
        let enemy_list = if att_player == 1 {
            &state.scratch.p2_struct_xys
        } else {
            &state.scratch.p1_struct_xys
        };
        let list_ptr = enemy_list.as_ptr();
        let list_len = enemy_list.len();
        let list_slice = unsafe { std::slice::from_raw_parts(list_ptr, list_len) };
        for &xy in list_slice {
            let dx = (xy.0 - att_xy.0) as f32;
            let dy = (xy.1 - att_xy.1) as f32;
            if dx * dx + dy * dy > r_sq { continue; }
            state.scratch.struct_cand_xys.push(xy);
        }
        let scl = state.scratch.struct_cand_xys.len();
        if scl == 1 {
            target_struct_xy = Some(state.scratch.struct_cand_xys[0]);
        } else if scl > 1 {
            let xys_ptr = state.scratch.struct_cand_xys.as_ptr();
            let xys_slice = unsafe { std::slice::from_raw_parts(xys_ptr, scl) };
            target_struct_xy = pick_target_struct(state, att_xy, att_player, xys_slice);
        }
    }
    // Apply damage + emit events (identical to fire_one_turret tail).
    if let Some(mi) = target_mobile_idx {
        let hp_before = state.mobiles[mi].hp;
        let shield_before = state.mobiles[mi].shield;
        let (new_hp, new_sh, died) = apply_damage(hp_before, shield_before, dmg_walker);
        state.mobiles[mi].hp = new_hp;
        state.mobiles[mi].shield = new_sh;
        #[cfg(feature = "instrumented")]
        {
            let m = &state.mobiles[mi];
            let victim_uid = m.uid;
            let victim_xy = m.xy;
            let victim_type_idx = m.type_idx;
            let victim_player = m.player;
            push_event!(events, EventEntry::Damage {
                xy: victim_xy, amount: dmg_walker,
                type_id: victim_type_idx, victim_uid,
                pid: victim_player as i32,
            });
            if died {
                push_event!(events, EventEntry::Death {
                    xy: victim_xy, type_id: victim_type_idx,
                    uid: victim_uid, pid: victim_player as i32,
                    removed: false,
                });
            }
        }
        #[cfg(not(feature = "instrumented"))]
        { let _ = died; }
    } else if let Some(sxy) = target_struct_xy {
        let hp_before = state.structures[&sxy].hp;
        let (new_hp, _sh, died) = apply_damage(hp_before, 0.0, dmg_tower);
        if let Some(s) = state.structures.get_mut(&sxy) {
            s.hp = new_hp;
        }
        #[cfg(feature = "instrumented")]
        {
            let s = &state.structures[&sxy];
            let victim_uid = s.uid;
            let victim_type_idx = s.type_idx;
            let victim_player = s.player;
            push_event!(events, EventEntry::Damage {
                xy: sxy, amount: dmg_tower, type_id: victim_type_idx,
                victim_uid, pid: victim_player as i32,
            });
            if died {
                push_event!(events, EventEntry::Death {
                    xy: sxy, type_id: victim_type_idx,
                    uid: victim_uid, pid: victim_player as i32,
                    removed: false,
                });
            }
        }
        #[cfg(not(feature = "instrumented"))]
        { let _ = died; }
    }
}

/// Turret-variant of `fire_one` — reuses a pre-filtered enemy-structure
/// candidate slice from `Scratch.turret_enemy_cands_flat[start..end]`
/// (populated at dirty-rebuild time). Skips the per-frame 54-tile distance
/// scan for struct candidates.
fn fire_one_turret(
    state: &mut SimState,
    events: &mut Vec<EventEntry>,
    att_xy: (i32, i32),
    att_player: u8,
    dmg_walker: f32,
    dmg_tower: f32,
    r_sq_eps: f32,
    enemy_cand_start: usize,
    enemy_cand_end: usize,
) {
    let r_sq = r_sq_eps;

    // Caller (system_attack) has already bbox-pre-filtered; enemy_mobs_list
    // may still be empty if all mobiles are dead but within bbox.
    let enemy_mobs_list: &[crate::state::WalkerCand] = if att_player == 1 {
        state.scratch.p2_live_mobs.as_slice()
    } else {
        state.scratch.p1_live_mobs.as_slice()
    };

    state.scratch.walker_cands.clear();
    if dmg_walker > 0.0 && !enemy_mobs_list.is_empty() {
        // Iterate the pre-partitioned enemy live mob cache (flat WalkerCand
        // list instead of the full `Mobile` struct). No hp/player re-check
        // — dead mobiles are filtered out at cache build time.
        for cand in enemy_mobs_list {
            let dx = (cand.xy.0 - att_xy.0) as f32;
            let dy = (cand.xy.1 - att_xy.1) as f32;
            if dx * dx + dy * dy > r_sq {
                continue;
            }
            // Re-snapshot the mobile's hp/shield in case an earlier turret
            // this frame reduced it (the cache reflects start-of-system HP).
            let m = &state.mobiles[cand.idx];
            if m.hp <= 0.0 {
                continue;
            }
            state.scratch.walker_cands.push(crate::state::WalkerCand {
                idx: cand.idx, hp: m.hp, shield: m.shield, xy: m.xy,
            });
        }
    }
    let mut target_mobile_idx: Option<usize> = None;
    let wcl = state.scratch.walker_cands.len();
    if wcl == 1 {
        target_mobile_idx = Some(state.scratch.walker_cands[0].idx);
    } else if wcl > 1 {
        let cand_ptr = state.scratch.walker_cands.as_ptr();
        let cand_slice = unsafe { std::slice::from_raw_parts(cand_ptr, wcl) };
        target_mobile_idx = pick_target_mobile_idx(state, att_xy, att_player, cand_slice);
    }
    let mut target_struct_xy: Option<(i32, i32)> = None;
    if target_mobile_idx.is_none() && dmg_tower > 0.0 {
        let scl = enemy_cand_end - enemy_cand_start;
        if scl == 1 {
            let xy = state.scratch.turret_enemy_cands_flat[enemy_cand_start];
            if let Some(s) = state.structures.get(&xy) {
                if s.hp > 0.0 {
                    target_struct_xy = Some(xy);
                }
            }
        } else if scl > 1 {
            let flat_ptr = state.scratch.turret_enemy_cands_flat.as_ptr();
            let slice = unsafe {
                std::slice::from_raw_parts(flat_ptr.add(enemy_cand_start), scl)
            };
            target_struct_xy = pick_target_struct(state, att_xy, att_player, slice);
        }
    }

    // Apply damage + emit events.
    if let Some(mi) = target_mobile_idx {
        let hp_before = state.mobiles[mi].hp;
        let shield_before = state.mobiles[mi].shield;
        let (new_hp, new_sh, died) = apply_damage(hp_before, shield_before, dmg_walker);
        state.mobiles[mi].hp = new_hp;
        state.mobiles[mi].shield = new_sh;
        #[cfg(feature = "instrumented")]
        {
            let m = &state.mobiles[mi];
            let victim_uid = m.uid;
            let victim_xy = m.xy;
            let victim_type_idx = m.type_idx;
            let victim_player = m.player;
            push_event!(events, EventEntry::Damage {
                xy: victim_xy,
                amount: dmg_walker,
                type_id: victim_type_idx,
                victim_uid,
                pid: victim_player as i32,
            });
            if died {
                push_event!(events, EventEntry::Death {
                    xy: victim_xy,
                    type_id: victim_type_idx,
                    uid: victim_uid,
                    pid: victim_player as i32,
                    removed: false,
                });
            }
        }
        #[cfg(not(feature = "instrumented"))]
        {
            let _ = died;
        }
    } else if let Some(sxy) = target_struct_xy {
        let hp_before = state.structures[&sxy].hp;
        let (new_hp, _sh, died) = apply_damage(hp_before, 0.0, dmg_tower);
        if let Some(s) = state.structures.get_mut(&sxy) {
            s.hp = new_hp;
        }
        #[cfg(feature = "instrumented")]
        {
            let s = &state.structures[&sxy];
            let victim_uid = s.uid;
            let victim_type_idx = s.type_idx;
            let victim_player = s.player;
            push_event!(events, EventEntry::Damage {
                xy: sxy,
                amount: dmg_tower,
                type_id: victim_type_idx,
                victim_uid,
                pid: victim_player as i32,
            });
            if died {
                push_event!(events, EventEntry::Death {
                    xy: sxy,
                    type_id: victim_type_idx,
                    uid: victim_uid,
                    pid: victim_player as i32,
                    removed: false,
                });
            }
        }
        #[cfg(not(feature = "instrumented"))]
        {
            let _ = died;
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
    // Collect dead structure xys into scratch (reused across frames).
    state.scratch.dead_struct_xys.clear();
    for (xy, s) in state.structures.iter() {
        if s.hp <= 0.0 {
            state.scratch.dead_struct_xys.push(*xy);
        }
    }
    if !state.scratch.dead_struct_xys.is_empty() {
        let n = state.scratch.dead_struct_xys.len();
        for i in 0..n {
            let xy = state.scratch.dead_struct_xys[i];
            state.structures.shift_remove(&xy);
            state.pending_removal_xys.shift_remove(&xy);
            for pf_slot in state.pathfinders.iter_mut() {
                if let Some(pf) = pf_slot.as_mut() {
                    pf.remove(xy.0, xy.1);
                }
            }
        }
        // Invalidate cached scratch lists (turret_infos, p1/p2_struct_xys,
        // support_xys) — they're keyed on the live-structure set.
        state.scratch.structures_dirty = true;
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
        uid: u32,
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
                    Some((refund_metal, s.uid, s.type_idx, s.player)),
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
    let any_removed = !plans.is_empty();
    for pl in plans {
        let p_stats = state.player_stats_mut(pl.player);
        p_stats.sp = round01(p_stats.sp + pl.refund_metal);
        push_event!(events, EventEntry::Death {
            xy: pl.xy,
            type_id: pl.type_idx,
            uid: pl.uid,
            pid: pl.player as i32,
            removed: true,
        });
        state.structures.shift_remove(&pl.xy);
        state.pending_removal_xys.shift_remove(&pl.xy);
        for pf_slot in state.pathfinders.iter_mut() {
            if let Some(pf) = pf_slot.as_mut() {
                pf.remove(pl.xy.0, pl.xy.1);
            }
        }
    }
    if any_removed {
        state.scratch.structures_dirty = true;
    }
}
