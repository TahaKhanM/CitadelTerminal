//! Top-level action-phase driver — Rust port of `sim/pysim.py`'s
//! `simulate_action_phase` (FAST) and `simulate_action_phase_iter`.
//!
//! # Frame loop (engine citation: Game.java:167-195, GameMain.runLoop:268-318)
//!
//! Each action frame runs the systems in this order:
//!
//! ```text
//! system_move
//!   system_collision      (no-op)
//!   system_shield_decay   (no-op on Citadel config)
//!   system_shield_give
//!   system_breach
//!   system_self_destruct
//!   system_attack
//! clear_destroyed           (pop dead units)
//! system_remove_own_unit    (RemoveOwnUnitSystem; refund+death, removed=true)
//! ```
//!
//! # Termination gates (mirror pysim.py exactly)
//!
//! 1. `HP <= 0` for either player (engine: `processEndGame(false)`,
//!    GameMain.runLoop:301).
//! 2. `state.mobiles.empty()` (engine: walkersAlive scan,
//!    GameMain.runLoop:296-300).
//! 3. `frame_counter >= max_frames` safety cap (SimCore convention; engine
//!    has no hard cap).
//!
//! Both gates are applied AFTER the frame's systems and `clear_destroyed` run
//! (matches pysim.py and `GameMain.runLoop` order).

use crate::config::SimConfig;
use crate::events::EventEntry;
use crate::state::{ActionResult, SimState};
use crate::systems::{
    clear_destroyed, system_attack, system_breach, system_collision, system_move,
    system_remove_own_unit, system_self_destruct, system_shield_decay, system_shield_give,
};
use indexmap::IndexMap;

/// Per-frame observation shape returned by `simulate_action_phase_iter`.
///
/// Engine wire format counterpart: `Parser.parseVisiblesListToJson` (one
/// replay action-phase frame). This is a light-weight per-frame snapshot used
/// by validators & fuzzers; it carries post-frame player stats + the flat
/// event list emitted during that frame. Translating to the 9-bucket engine
/// wire format is done by a separate layer (see `sim/pysim.py`'s
/// `_translate_events_to_buckets`).
#[derive(Debug, Clone)]
pub struct FrameObservation {
    /// Post-frame player-1 HP / SP / MP.
    pub p1_hp: f32,
    pub p1_sp: f32,
    pub p1_mp: f32,
    /// Post-frame player-2 HP / SP / MP.
    pub p2_hp: f32,
    pub p2_sp: f32,
    pub p2_mp: f32,
    /// Flat event list emitted during this frame (damage, shield, death,
    /// self-destruct events in emission order — see `events.rs` for the
    /// canonical-key contract).
    pub events: Vec<EventEntry>,
    /// Frame index (0-based).
    pub frame_idx: i32,
    /// Number of live mobiles post-clear.
    pub mobile_count: usize,
}

/// FAST-mode action phase. Runs frames to completion without materialising
/// per-frame observations. Accumulates all emitted events into a single
/// `frame_events` list on the returned `ActionResult`.
///
/// Engine citation: mirror of `sim/pysim.py::simulate_action_phase`.
///
/// * Pre-condition: `state` already reflects deploy-phase (structures +
///   mobile spawns). Use `apply_deploy_actions` to build this from replay
///   records.
/// * Post-condition: `state.mobiles` is pruned to live units, dead structures
///   are popped, pathfinders are in sync.
pub fn simulate_action_phase(
    state: &mut SimState,
    config: &SimConfig,
    max_frames: i32,
) -> ActionResult {
    let p1_start_hp = state.p1.hp;
    let p2_start_hp = state.p2.hp;

    // Single scratch event buffer reused across frames — FAST mode doesn't
    // need per-frame history; systems append then we `clear` between frames.
    // Mirrors pysim.py's simulate_action_phase which similarly reuses one
    // flat_events list (though Python accumulates; Rust FAST drops).
    let mut scratch_events: Vec<EventEntry> = Vec::with_capacity(32);
    let mut f: i32 = 0;

    while f < max_frames {
        scratch_events.clear();

        system_move(state, config, &mut scratch_events);
        system_collision(state, config, &mut scratch_events);
        system_shield_decay(state, config, &mut scratch_events);
        system_shield_give(state, config, &mut scratch_events);
        system_breach(state, config, &mut scratch_events);
        system_self_destruct(state, config, &mut scratch_events);
        system_attack(state, config, &mut scratch_events);

        clear_destroyed(state, &mut scratch_events);
        system_remove_own_unit(state, config, &mut scratch_events);

        // Termination gates — mirror pysim.py's simulate_action_phase. Both
        // apply AFTER this frame's systems (engine: processEndGame(false) +
        // walkersAlive are checked after gameEngineLoop returns).
        if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 {
            f += 1;
            break;
        }
        if state.mobiles.is_empty() {
            f += 1;
            break;
        }
        f += 1;
    }

    let p1_dmg = (p2_start_hp - state.p2.hp).max(0.0);
    let p2_dmg = (p1_start_hp - state.p1.hp).max(0.0);

    // Drop pathfinders + scratch buffers from the cloned final_state — they
    // are 4x ~30 KB vec arrays and a handful of scratch Vecs that tests +
    // downstream consumers never read. Callers that need the final state
    // post-action only look at HP/SP/MP, structures, and mobiles.
    let saved_pf = std::mem::replace(&mut state.pathfinders, [None, None, None, None]);
    let saved_scratch = std::mem::take(&mut state.scratch);
    let final_state = state.clone();
    state.pathfinders = saved_pf;
    state.scratch = saved_scratch;

    ActionResult {
        final_state,
        p1_damage_dealt: p1_dmg,
        p2_damage_dealt: p2_dmg,
        structure_damage_by_player: IndexMap::new(),
        frame_events: Vec::new(),
        frame_count: f,
    }
}

/// Lightweight action-phase runner for batch/throughput callers that only
/// need final player stats + mobile/structure HP summaries — skips the
/// heavy `SimState::clone()` into `ActionResult.final_state`.
///
/// Returns `(p1_damage_dealt, p2_damage_dealt, frame_count)`. Callers that
/// need the full post-action state should use `simulate_action_phase`
/// instead.
pub fn simulate_action_phase_lite(
    state: &mut SimState,
    config: &SimConfig,
    max_frames: i32,
) -> (f32, f32, i32) {
    let p1_start_hp = state.p1.hp;
    let p2_start_hp = state.p2.hp;

    let mut scratch_events: Vec<EventEntry> = Vec::with_capacity(32);
    let mut f: i32 = 0;

    while f < max_frames {
        scratch_events.clear();

        system_move(state, config, &mut scratch_events);
        system_collision(state, config, &mut scratch_events);
        system_shield_decay(state, config, &mut scratch_events);
        system_shield_give(state, config, &mut scratch_events);
        system_breach(state, config, &mut scratch_events);
        system_self_destruct(state, config, &mut scratch_events);
        system_attack(state, config, &mut scratch_events);

        clear_destroyed(state, &mut scratch_events);
        system_remove_own_unit(state, config, &mut scratch_events);

        if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 {
            f += 1;
            break;
        }
        if state.mobiles.is_empty() {
            f += 1;
            break;
        }
        f += 1;
    }

    let p1_dmg = (p2_start_hp - state.p2.hp).max(0.0);
    let p2_dmg = (p1_start_hp - state.p1.hp).max(0.0);
    (p1_dmg, p2_dmg, f)
}

/// INSTRUMENTED action phase — yields a `FrameObservation` per frame.
///
/// Engine citation: mirror of `sim/pysim.py::simulate_action_phase_iter`.
/// Frame-loop structure is identical to `simulate_action_phase`; the only
/// addition is an observation built after each frame's systems run.
pub fn simulate_action_phase_iter(
    state: &mut SimState,
    config: &SimConfig,
    max_frames: i32,
) -> Vec<FrameObservation> {
    let mut out: Vec<FrameObservation> = Vec::new();
    let mut f: i32 = 0;

    while f < max_frames {
        let mut frame_events: Vec<EventEntry> = Vec::new();

        system_move(state, config, &mut frame_events);
        system_collision(state, config, &mut frame_events);
        system_shield_decay(state, config, &mut frame_events);
        system_shield_give(state, config, &mut frame_events);
        system_breach(state, config, &mut frame_events);
        system_self_destruct(state, config, &mut frame_events);
        system_attack(state, config, &mut frame_events);

        clear_destroyed(state, &mut frame_events);
        system_remove_own_unit(state, config, &mut frame_events);

        out.push(FrameObservation {
            p1_hp: state.p1.hp,
            p1_sp: state.p1.sp,
            p1_mp: state.p1.mp,
            p2_hp: state.p2.hp,
            p2_sp: state.p2.sp,
            p2_mp: state.p2.mp,
            events: frame_events,
            frame_idx: f,
            mobile_count: state.mobiles.len(),
        });

        if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 {
            break;
        }
        if state.mobiles.is_empty() {
            break;
        }
        f += 1;
    }

    out
}

// ------------------------------------------------------------------- batch API
//
// `simulate_batch_parallel` runs N sims in parallel via rayon's work-stealing
// thread pool. Each sim starts from a clone of `template`; the caller is
// responsible for warming pathfinders on `template` (via
// `systems::ensure_pathfinders`) BEFORE calling this function so the pathfinder
// state is shared via clone across threads (matches the sequential
// benchmark path).
//
// Engine citation: no engine analogue. Citadel's engine runs one match per
// JVM; batch parallelism is a SimCore feature for beam-search planners that
// evaluate many hypothetical turns in parallel.
//
// Determinism: each sim is independent (no shared mutable state — rayon
// schedules templates.clone()s into per-thread workers). Because SimCore
// itself is deterministic (see metamorphic M3), identical inputs produce
// identical outputs regardless of thread scheduling.

/// Run `n` sims in parallel from `template` via rayon. Returns a vector of
/// `(p1_damage, p2_damage, frame_count)` tuples matching
/// `simulate_action_phase_lite`'s output, in index order.
///
/// `template` should have `pathfinders.is_some()` (call
/// `systems::ensure_pathfinders(&mut template)` beforehand) so the cloned
/// state starts with pre-validated path state.
pub fn simulate_batch_parallel(
    template: &SimState,
    cfg: &SimConfig,
    n: usize,
    max_frames: i32,
) -> Vec<(f32, f32, i32)> {
    use rayon::prelude::*;
    (0..n)
        .into_par_iter()
        .map(|_| {
            // `clone_no_scratch` drops the 10 scratch-Vec allocs per sim —
            // in the parallel hot path the allocator serializes those cross-
            // thread and caps throughput well below the per-core ceiling.
            let mut state = template.clone_no_scratch();
            simulate_action_phase_lite(&mut state, cfg, max_frames)
        })
        .collect()
}
