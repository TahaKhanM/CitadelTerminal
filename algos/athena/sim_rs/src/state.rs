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
    /// See `Game.java:132 getNewID` for the engine counter this corresponds to.
    pub uid: String,
    /// 1 = first/bottom player, 2 = second/top player. Raw-JSON convention,
    /// flipped from the `game_state.GameUnit` API's 0/1 (see CLAUDE.md Note 6).
    pub player: u8,
    /// Engine's `RefundComponent.turnStartRemoval`. `None` = not pending removal;
    /// otherwise the integer turn number on which type-6 was issued.
    pub turn_start_removal: Option<i32>,
    /// Supports-only: uids already shielded by THIS structure this action phase.
    /// Mirrors `ShieldingSystem.java`'s per-support "already shielded" tracking;
    /// Supports cannot re-shield a mobile they already touched in the same phase.
    pub shielded_already: Vec<String>,
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
    pub uid: String,
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
    /// Per-edge `PathFinder` instances (keyed by edge id 0..=3). Lazily
    /// initialized in `system_move` from current structures.
    ///
    /// Engine citation: `Game.java` holds 4 `PathFinder` instances per game;
    /// each is invalidated/revalidated as walls are placed/removed. See
    /// `sim/pysim.py::_ensure_pathfinders` for the Python reference.
    pub pathfinders: Option<IndexMap<i32, PathFinder>>,
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
            pathfinders: None,
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
