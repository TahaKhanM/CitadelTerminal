//! State containers — mirror `algos/athena/sim/state.py`.
//!
//! Engine citations:
//! * `HealthComponent.java` — `hp: f32`, `shield: f32`, death threshold 0.001 f
//! * `Gameobject.java` — `uid: String`, `player: 1|2`
//! * `PlayerStats.java` — `hp/sp/mp` all 32-bit floats; `roundDecimals` snaps to 0.1
//!
//! Float precision: all HP / shield / SP / MP fields are `f32` end-to-end to
//! match the engine's Java 32-bit float arithmetic (Cluster I).

use indexmap::IndexMap;

#[derive(Debug, Clone)]
pub struct Structure {
    pub xy: (i32, i32),
    pub type_idx: i32,
    pub upgraded: bool,
    pub hp: f32,
    pub uid: String,
    pub player: u8,
    pub turn_start_removal: Option<i32>,
    pub shielded_already: Vec<String>,
}

#[derive(Debug, Clone)]
pub struct Mobile {
    pub xy: (i32, i32),
    pub type_idx: i32,
    pub hp: f32,
    pub shield: f32,
    pub uid: String,
    pub player: u8,
    pub spawn_xy: (i32, i32),
    pub target_edge: i32,
    pub steps_taken: i32,
    pub move_buildup: f32,
    pub last_move: i32,
    pub finished_navigating: bool,
    pub reached_target: bool,
    pub breached: bool,
}

#[derive(Debug, Clone)]
pub struct PlayerStats {
    pub hp: f32,
    pub sp: f32,
    pub mp: f32,
}

#[derive(Debug, Clone)]
pub struct SimState {
    pub turn: i32,
    // IndexMap preserves insertion order — required for deterministic
    // structure iteration (engine's gameObjects ArrayList ordering).
    pub structures: IndexMap<(i32, i32), Structure>,
    pub mobiles: Vec<Mobile>,
    pub p1: PlayerStats,
    pub p2: PlayerStats,
    pub pending_removal_xys: Vec<(i32, i32)>,
}

impl SimState {
    pub fn new(turn: i32) -> Self {
        Self {
            turn,
            structures: IndexMap::new(),
            mobiles: Vec::new(),
            p1: PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 },
            p2: PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 },
            pending_removal_xys: Vec::new(),
        }
    }
}
