//! State containers — mirror `algos/athena/sim/state.py`.
//!
//! Engine citations (all paths are relative to
//! `research/engine_decompiled/sources/com/c1games/terminal/`):
//!
//! * `game/HealthComponent.java` — `hp: f32`, `shield: f32`, death threshold 0.001 f
//! * `game/Gameobject.java`      — `uid: String`, `player: 1|2`
//! * `game/PlayerStats.java`     — `hp/sp/mp` all 32-bit floats
//! * `game/components/RefundComponent.java` — `turnStartRemoval` pending-removal bookkeeping
//! * `game/systems/BreachSystem.java:28` — `disableGameObject` on breach (breached mobiles
//!   are skipped in `TargetAndAttackSystem.java:30` attacker snapshot)
//! * `game/Game.java:getNewID (Game.java:132)` — monotonic UID counter; SimCore NEVER
//!   allocates UIDs, only propagates engine-assigned ones from the replay
//!
//! Float precision: all HP / shield / SP / MP fields are `f32` end-to-end to match
//! the engine's Java 32-bit float arithmetic (Cluster I parity). Python sim stores
//! these as `numpy.float32`; Rust uses the native `f32` so accumulated-error traces
//! should match bit-for-bit.

use indexmap::IndexMap;

use crate::pathfinder::PathFinder;

/// Live structure in the simulation (Wall / Support / Turret).
///
/// Engine citation: populated from spawn events (`Gameobject.java` + the replay's
/// unit blob). `turn_start_removal` mirrors
/// `components/RefundComponent.java`.`turnStartRemoval` — the turn on which the
/// type-6 removal command was issued. Removal fires at the START of turn
/// `turn_start_removal + turns_required_to_remove` via `RemoveOwnUnitSystem.java`.
#[derive(Debug, Clone)]
pub struct Structure {
    pub xy: (i32, i32),
    pub type_idx: i32,
    pub upgraded: bool,
    pub hp: f32,
    /// UID from the replay's spawn event (`ev[2]` in raw JSON). Never re-used.
    /// Stored as `u32` — the engine's Game.java:132 getNewID counter is a
    /// monotone int that never reaches the 2^32 ceiling in a single game.
    /// Callers with string-form UIDs (replay ingestion, tests) parse to u32
    /// at the boundary.
    pub uid: u32,
    /// 1 = first/bottom player, 2 = second/top player. Raw-JSON convention,
    /// flipped from the `game_state.GameUnit` API's 0/1 (see CLAUDE.md Note 6).
    pub player: u8,
    /// Engine's `RefundComponent.turnStartRemoval`. `None` = not pending removal;
    /// otherwise the integer turn number on which type-6 was issued.
    pub turn_start_removal: Option<i32>,
    /// Supports-only: uids already shielded by THIS structure this action phase.
    /// Mirrors `ShieldingSystem.java`'s per-support "already shielded" tracking;
    /// Supports cannot re-shield a mobile they already touched in the same phase.
    pub shielded_already: Vec<u32>,
}

/// Live mobile unit mid-action-phase (Scout / Demolisher / Interceptor).
///
/// Engine citation: `info/PathFinder.java`, `systems/MoveSystem.java`,
/// `systems/BreachSystem.java`. All per-frame mutable fields correspond 1:1 to
/// `MovementComponent.java` state.
#[derive(Debug, Clone)]
pub struct Mobile {
    /// Current tile. Engine's `PositionComponent.java`.
    pub xy: (i32, i32),
    pub type_idx: i32,
    pub hp: f32,
    pub shield: f32,
    /// See `Structure.uid` — same u32 interning story.
    pub uid: u32,
    pub player: u8,
    /// Original spawn tile. Used by `PathFinder.java` to compute the route.
    pub spawn_xy: (i32, i32),
    /// `game_map` edge constant (see `sim/map.rs`).
    pub target_edge: i32,
    /// Number of completed moves (`MovementComponent.stepsTaken`).
    pub steps_taken: i32,
    /// Engine uses a float accumulator: `buildup += speed` per frame; a move
    /// fires and `buildup -= 1.0` when `buildup >= 0.9999`. See
    /// `systems/MoveSystem.java`.
    pub move_buildup: f32,
    /// `lastMove` hint passed into `PathFinder.get_step()`:
    /// 0=SPAWNED, 1=HORIZONTAL, 2=VERTICAL. Updated after every successful move.
    pub last_move: i32,
    /// True when pathfinder returned the same tile (delta=0).
    /// See `systems/MoveSystem.java`.
    pub finished_navigating: bool,
    /// True when `finished_navigating` AND `xy` is in nav targets.
    pub reached_target: bool,
    /// Set by `systems/BreachSystem.java:28 disableGameObject` → attack
    /// component `isEnabled==false` → `TargetAndAttackSystem.java:30` skips
    /// the unit from the attacker snapshot. SDed / attack-killed mobiles are
    /// NOT disabled and still fire one last shot (engine's
    /// `attackWhenDestroyed` gate); only breachers must be explicitly excluded.
    pub breached: bool,
}

/// Per-player scoreboard. Engine citation: `game/PlayerStats.java`.
/// All three fields are Java `float` (32-bit). `roundDecimals` in
/// `PlayerStats.java` snaps SP/MP to 0.1 precision at turn boundaries.
#[derive(Debug, Clone)]
pub struct PlayerStats {
    pub hp: f32,
    pub sp: f32,
    pub mp: f32,
}

/// Full mutable state across an action-phase simulation.
///
/// Engine citation: mirrors `game/Game.java` top-level state — the
/// `structures` map tracks engine's `gameObjects` ArrayList (tile-keyed for
/// O(1) lookup) and `mobiles` is the walker ArrayList (iteration order
/// matters for targeting tiebreaks in `TargetAndAttackSystem.java`).
#[derive(Debug, Clone)]
pub struct SimState {
    pub turn: i32,
    /// IndexMap preserves insertion order — required for deterministic
    /// structure iteration (engine's `gameObjects` ArrayList ordering).
    /// NEVER use `std::collections::HashMap` here: Rust's default HashMap
    /// randomises order per-process and would reproduce the JVM-HashSet
    /// nondeterminism we're trying to eliminate (see Cluster I notes).
    pub structures: IndexMap<(i32, i32), Structure>,
    /// Mobiles in insertion order — engine uses an ArrayList in
    /// `systems/MoveSystem.java` + `TargetAndAttackSystem.java`; iteration
    /// order matters for targeting ties.
    pub mobiles: Vec<Mobile>,
    pub p1: PlayerStats,
    pub p2: PlayerStats,
    /// Fast-path set for `system_remove_own_unit`: xy tiles whose structure
    /// has `turn_start_removal` set. Kept in sync wherever
    /// `turn_start_removal` is mutated. Empty → `system_remove_own_unit`
    /// is a no-op immediately (avoids O(structures) scan per frame).
    /// Rust uses `IndexMap<K, ()>` for set-with-insertion-order semantics.
    pub pending_removal_xys: IndexMap<(i32, i32), ()>,
    /// Per-edge `PathFinder` instances, indexed by edge id (0..=3).
    /// `pathfinders[edge_id]` is `None` until needed (built lazily by
    /// `ensure_pathfinders`). Fixed-size array avoids the IndexMap hash
    /// lookup cost in the hot `system_move` path.
    ///
    /// Engine citation: `Game.java` holds 4 `PathFinder` instances per game;
    /// each is invalidated/revalidated as walls are placed/removed. See
    /// `sim/pysim.py::_ensure_pathfinders` for the Python reference.
    pub pathfinders: [Option<Box<PathFinder>>; 4],
    /// Per-frame scratch buffers — cleared (not freed) between frames so
    /// hot systems do zero-alloc work after capacity is seeded. See
    /// `Scratch` for the per-field docs.
    #[doc(hidden)]
    pub scratch: Scratch,
}

/// Pre-extracted per-turret attack info. Populated at system_attack entry so
/// the per-turret loop body doesn't re-do the structures.get(xy) hash lookup
/// + spec-match for every live turret every frame.
///
/// `enemy_cand_{start,end}` indices into `Scratch.turret_enemy_cands_flat`.
/// Contains every enemy structure tile within this turret's `attack_range`
/// at dirty-rebuild time; `fire_one`'s struct-targeting fallback iterates
/// this sub-list (≈5 entries) instead of the full enemy-player roster (~54
/// entries on mid-game), which dominates runtime when walkers aren't in
/// range of a turret.
#[derive(Debug, Clone, Copy, Default)]
pub struct TurretInfo {
    pub xy: (i32, i32),
    pub player: u8,
    pub dmg_walker: f32,
    pub dmg_tower: f32,
    pub range_sq_eps: f32,
    /// Precomputed `ceil(sqrt(range_sq_eps))` for the per-frame bbox
    /// pre-filter in `system_attack`. Saved here so the hot turret loop
    /// doesn't re-do a sqrt + ceil + cast per turret per frame.
    pub range_bound: i32,
    pub enemy_cand_start: u32,
    pub enemy_cand_end: u32,
}

/// Per-frame scratch buffers reused across frames to avoid per-frame heap
/// allocations inside `system_attack`, `system_shield_give`, and
/// `system_self_destruct`.
#[derive(Debug, Clone, Default)]
pub struct Scratch {
    /// `system_attack`: attacker structure tile list (turrets).
    pub attacker_struct_xys: Vec<(i32, i32)>,
    /// `system_attack`: pre-extracted turret attack info so the hot fire
    /// loop doesn't hash-lookup the structure each pass.
    pub turret_infos: Vec<TurretInfo>,
    /// `system_attack`: attacker mobile index list.
    pub attacker_mobile_idxs: Vec<usize>,
    /// `fire_one`: walker target candidate records.
    pub walker_cands: Vec<WalkerCand>,
    /// `fire_one`: structure target candidate tiles.
    pub struct_cand_xys: Vec<(i32, i32)>,
    /// `system_shield_give`: live Support xy list (one entry per support).
    pub support_xys: Vec<(i32, i32)>,
    /// `system_shield_give`: uids newly shielded by the current support;
    /// dumped onto `Structure.shielded_already` at the end of each support's
    /// inner loop to avoid simultaneous borrow of `state.structures` +
    /// `state.mobiles`.
    pub newly_shielded: Vec<u32>,
    /// `clear_destroyed`: dead structure xys discovered this frame.
    pub dead_struct_xys: Vec<(i32, i32)>,
    /// `system_attack`: player-1 owned structure xys (refreshed at system
    /// start). Enables O(p1_struct_count) iteration for p2 turrets instead of
    /// O(all_structures).
    pub p1_struct_xys: Vec<(i32, i32)>,
    /// `system_attack`: player-2 owned structure xys.
    pub p2_struct_xys: Vec<(i32, i32)>,
    /// `system_attack`: parallel IndexMap indices for `p1_struct_xys`
    /// (`structures.get_index(p1_struct_idxs[k]) == structures.get(p1_struct_xys[k])`).
    /// Populated at dirty-rebuild time and consumed to build
    /// `turret_enemy_cands_flat` (which stores indices) — avoids the need
    /// to re-hash `(x, y)` in the inner rebuild loop.
    pub p1_struct_idxs: Vec<u32>,
    /// `system_attack`: parallel IndexMap indices for `p2_struct_xys`.
    pub p2_struct_idxs: Vec<u32>,
    /// `system_attack`: flat concatenated per-turret enemy-structure
    /// candidate *IndexMap indices* (not xy) within range. Indexed by
    /// `TurretInfo.enemy_cand_{start,end}`.
    /// Pre-filtered by Euclidean distance at rebuild time so `fire_one`'s
    /// struct-targeting fallback skips the 54-tile distance scan AND the
    /// per-candidate hash lookup.
    ///
    /// Invariant: the IndexMap indices here are ONLY valid between
    /// dirty-rebuilds. Any structure add/remove sets `structures_dirty` and
    /// forces a rebuild before the next read. See `system_attack` dirty
    /// block and `clear_destroyed` / `system_remove_own_unit` dirty flips.
    pub turret_enemy_cands_flat: Vec<u32>,
    /// Per-frame compact cache of live enemy mobile coords. `p1_live_mobs`
    /// holds player-1-owned live mobiles (attackable by p2 turrets), and
    /// vice versa. Rebuilt once per `system_attack` entry from `state.mobiles`
    /// (mobiles move every frame so this can't be dirty-cached like
    /// structures — but iterating a flat `Vec<MobileCand>` is cheaper than
    /// dereferencing the full `Mobile` struct 5 times per turret fire).
    pub p1_live_mobs: Vec<WalkerCand>,
    pub p2_live_mobs: Vec<WalkerCand>,
    /// Bounding-box over the enemy-mobile positions, per player.
    /// `(x_lo, x_hi, y_lo, y_hi)`; turrets outside this bbox + range skip
    /// the walker_cands build entirely and jump straight to struct fallback.
    pub p1_mob_bbox: (i32, i32, i32, i32),
    pub p2_mob_bbox: (i32, i32, i32, i32),
    /// Dirty bit for the three scratch lists above (plus `support_xys`).
    /// True on template build and whenever any structure dies; cleared after
    /// the scratch is rebuilt in `system_attack` / `system_shield_give`.
    /// Without this flag we'd rescan all 108 structures 2-3 times per frame
    /// — in the mid-game fixture that dominates the simulator's runtime
    /// (measured 77 µs of 90 µs/sim via `examples/simprof`).
    pub structures_dirty: bool,
}

/// Walker target candidate — indices + copyable data only, no String clone.
#[derive(Debug, Clone, Copy, Default)]
pub struct WalkerCand {
    pub idx: usize,
    pub hp: f32,
    pub shield: f32,
    pub xy: (i32, i32),
}

impl SimState {
    /// Build a fresh state at the given turn with default starting stats
    /// (HP 40, SP 8, MP 1). These defaults are overwritten by
    /// `validate._build_state_from_deploy_frame` when loading from a replay.
    ///
    /// Engine citation: starting values live in `Config.java` (`startingHP`,
    /// `startingCores`, `startingBits`); this constructor mirrors the
    /// Python-sim convenience factory in `sim/state.py`.
    pub fn new(turn: i32) -> Self {
        Self {
            turn,
            structures: IndexMap::new(),
            mobiles: Vec::new(),
            p1: PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 },
            p2: PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 },
            pending_removal_xys: IndexMap::new(),
            pathfinders: [None, None, None, None],
            scratch: Scratch {
                // Force initial scratch rebuild on first `system_attack` /
                // `system_shield_give` — the freshly-constructed scratch is
                // empty and callers may insert structures before the first
                // system runs.
                structures_dirty: true,
                ..Default::default()
            },
        }
    }

    /// Look up the structure at a tile. Engine citation: engine allows only
    /// one structure per tile (see `BlocksUnitsComponent.java`).
    pub fn struct_at(&self, xy: (i32, i32)) -> Option<&Structure> {
        self.structures.get(&xy)
    }

    /// Return every mobile currently standing on `xy`. Iteration order is
    /// insertion order, matching engine's ArrayList traversal in
    /// `TargetAndAttackSystem.java`.
    pub fn mobiles_at(&self, xy: (i32, i32)) -> Vec<&Mobile> {
        self.mobiles.iter().filter(|m| m.xy == xy).collect()
    }

    /// Remove a structure by UID match at its tile. Mirrors
    /// `sim/state.py::SimState.remove_structure`. Uses UID equality (not
    /// reference identity) because Clone gives us fresh instances.
    ///
    /// Engine citation: removal drops the struct from engine's
    /// `gameObjects` list (`ClearDestroyedSystem.java` / explicit destroys).
    pub fn remove_structure(&mut self, s: &Structure) {
        let xy = s.xy;
        if let Some(existing) = self.structures.get(&xy) {
            if existing.uid == s.uid {
                self.structures.shift_remove(&xy);
                self.pending_removal_xys.shift_remove(&xy);
            }
        }
    }

    /// `true` if any structure occupies `xy`. Engine citation: tile-occupancy
    /// check used by `SpawnUnitsSystem.java` pre-spawn validation and by the
    /// pathfinder's `blocked` grid.
    pub fn is_occupied(&self, xy: (i32, i32)) -> bool {
        self.structures.contains_key(&xy)
    }

    /// Clone the state into a fresh sim-ready snapshot WITHOUT cloning
    /// `scratch`. Each scratch Vec carries capacity but no live data
    /// (systems rebuild from `structures` + `mobiles` on dirty), so
    /// cloning them costs 10 heap allocs per sim that serve no purpose —
    /// in the `simulate_batch_parallel` path, 10 threads × 10 allocs ×
    /// 80 K sims/s ≈ 8 M allocs/s, past the macOS allocator's serialization
    /// threshold. Use this in hot batch paths where you intend to run
    /// `simulate_action_phase` on the result.
    pub fn clone_no_scratch(&self) -> Self {
        Self {
            turn: self.turn,
            structures: self.structures.clone(),
            mobiles: self.mobiles.clone(),
            p1: self.p1.clone(),
            p2: self.p2.clone(),
            pending_removal_xys: self.pending_removal_xys.clone(),
            pathfinders: [
                self.pathfinders[0].clone(),
                self.pathfinders[1].clone(),
                self.pathfinders[2].clone(),
                self.pathfinders[3].clone(),
            ],
            scratch: Scratch {
                structures_dirty: true,
                ..Default::default()
            },
        }
    }

    /// Player-indexed scoreboard accessor. Engine citation:
    /// `PlayerStats.java` instances are stored in `Game.players[0..1]`.
    pub fn player_stats(&self, player: u8) -> &PlayerStats {
        if player == 1 { &self.p1 } else { &self.p2 }
    }

    /// Mutable scoreboard accessor.
    pub fn player_stats_mut(&mut self, player: u8) -> &mut PlayerStats {
        if player == 1 { &mut self.p1 } else { &mut self.p2 }
    }

    /// Return the opposing player id. Engine citation: player ids are 1/2
    /// in raw-JSON and action-frame replay formats.
    pub fn enemy_player(&self, player: u8) -> u8 {
        if player == 1 { 2 } else { 1 }
    }
}

/// Returned from the action-phase simulator. All damage values are cumulative
/// across the whole action phase.
///
/// Engine citation: mirrors `sim/state.py::ActionResult`. `frame_events` is
/// shaped like a replay's events dict so we can diff directly against
/// `GlobalDamaged / GlobalDeath / GlobalSelfDestruct / GlobalBreach` emitters
/// in `game/events/`.
#[derive(Debug, Clone)]
pub struct ActionResult {
    pub final_state: SimState,
    /// HP damage dealt TO p2 (by p1). See `PlayerDamageSystem.java`.
    pub p1_damage_dealt: f32,
    /// HP damage dealt TO p1 (by p2).
    pub p2_damage_dealt: f32,
    /// Structure damage dealt by player 1 vs player 2 (insertion-ordered map).
    pub structure_damage_by_player: IndexMap<u8, f32>,
    /// Event log accumulated across frames — tuple-of-strings in insertion
    /// order to mirror raw replay JSON.
    pub frame_events: Vec<IndexMap<String, serde_json::Value>>,
    pub frame_count: i32,
}

impl ActionResult {
    /// Return `Some(1)` / `Some(2)` if there is a decisive HP winner,
    /// `None` on an HP-equal draw. Matches `ActionResult.winner` in
    /// `sim/state.py`.
    ///
    /// Engine citation: `Game.java`'s end-of-turn HP comparison.
    pub fn winner(&self) -> Option<u8> {
        let p1 = self.final_state.p1.hp;
        let p2 = self.final_state.p2.hp;
        if p1 <= 0.0 && p2 > 0.0 { return Some(2); }
        if p2 <= 0.0 && p1 > 0.0 { return Some(1); }
        if p1 > p2 { return Some(1); }
        if p2 > p1 { return Some(2); }
        None
    }
}
