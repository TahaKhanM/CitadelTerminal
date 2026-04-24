//! `sim_rs` — bit-exact Rust port of SimCore (Citadel Terminal action-phase
//! simulator). Mirrors `algos/athena/sim/pysim.py` system-for-system against the
//! decompiled engine at `research/engine_decompiled/sources/com/c1games/terminal/`.
//!
//! # Scope of this scaffold
//!
//! This crate is a *scaffold* — the directory layout, Cargo.toml, and module
//! structure are in place, but the systems are stubbed pending the full port.
//! The stub exists so downstream tooling (regression runner, cross-validator)
//! can depend on `sim_rs` with a stable symbol surface while the port lands
//! module-by-module.
//!
//! # Module plan (matches Python sim/)
//!
//! | Rust module | Python file              | Engine citations                                     |
//! |-------------|--------------------------|------------------------------------------------------|
//! | `state`     | `sim/state.py`           | Game.java, Gameobject.java, HealthComponent.java     |
//! | `config`    | `sim/config.py`          | Config.java (processUnitInfoInPlace copy-then-patch) |
//! | `map`       | `sim/map.py`             | MapBounds.java                                       |
//! | `pathfinder`| `sim/pathfinder.py`      | PathFinder.java + PATHFINDER_SPEC.md                 |
//! | `proximity` | `sim/proximity.py`       | ProximityArena.java, ProximitySensor.java            |
//! | `events`    | `sim/event_canonical.py` | Global*.java (GlobalDamaged/Death/Shield/SD/…)       |
//! | `systems`   | `sim/pysim.py` (frame)   | Game.gameEngineLoop, *System.java                    |
//! | `fast_mode` | `sim/pysim.py` FAST path | same as `systems` minus observation build            |
//! | `py_api`    | PyO3 bindings            | —                                                    |
//!
//! # Parity contract (C-tight-coherent, final Phase 1.B.1 gate)
//!
//! * 19 STRICT columns @ max_err=0 across 23 ranked replays + 10K fuzz
//! * 4 CASCADE columns (p{1,2}_mobiles + event_{damage,selfDestruct})
//!   multiset-equal on canonical keys with ≤1-ULP-float32 tolerance on HP+shield,
//!   <1% of corpus, 100% JVM-HashSet-attributable.
//! * Python ↔ Rust byte-identical under canonical-key ordering (enforced by
//!   `algos/athena/sim/cross_validate.py`).

pub mod config;
pub mod events;
pub mod map;
pub mod pathfinder;
pub mod proximity;
pub mod sim;
pub mod state;
pub mod systems;

#[cfg(feature = "pyo3")]
mod py_api {
    //! PyO3 bindings for cross_validate.py.
    //!
    //! Exposes a single `simulate_action_phase_py(state_dict, config_path)`
    //! function that mirrors the Python reference API expected by
    //! `algos/athena/sim/cross_validate.py`:
    //!
    //! * Input: a state dict (same shape as `_py_state_to_dict` emits) +
    //!   a path string to the Citadel config JSON snapshot.
    //! * Output: post-simulation state dict with the same shape.
    //!
    //! UIDs cross the boundary as strings (matching Python's replay wire
    //! format) and are parsed to `u32` on ingress / stringified on egress.
    //! All floats are Python `float` (f64); we down-cast to `f32` on ingress
    //! and up-cast on egress to match engine precision.
    //!
    //! Build:
    //!     cd algos/athena/sim_rs
    //!     maturin develop --release --features pyo3

    use pyo3::prelude::*;
    use pyo3::types::{PyDict, PyList};

    use crate::config::SimConfig;
    use crate::sim::simulate_action_phase;
    use crate::state::{Mobile, PlayerStats, SimState, Structure};

    fn parse_uid_str(s: &str) -> PyResult<u32> {
        s.parse::<u32>().map_err(|_| {
            pyo3::exceptions::PyValueError::new_err(
                format!("uid '{s}' not parseable as u32 (cross_validate expects numeric uids)"))
        })
    }

    fn build_state_from_dict(d: &Bound<'_, PyDict>) -> PyResult<SimState> {
        let turn: i32 = d.get_item("turn")?
            .ok_or_else(|| pyo3::exceptions::PyKeyError::new_err("turn"))?
            .extract()?;
        let mut state = SimState::new(turn);
        let p1 = d.get_item("p1")?
            .ok_or_else(|| pyo3::exceptions::PyKeyError::new_err("p1"))?;
        let p1 = p1.downcast::<PyDict>()?;
        state.p1 = PlayerStats {
            hp: p1.get_item("hp")?.unwrap().extract::<f64>()? as f32,
            sp: p1.get_item("sp")?.unwrap().extract::<f64>()? as f32,
            mp: p1.get_item("mp")?.unwrap().extract::<f64>()? as f32,
        };
        let p2 = d.get_item("p2")?
            .ok_or_else(|| pyo3::exceptions::PyKeyError::new_err("p2"))?;
        let p2 = p2.downcast::<PyDict>()?;
        state.p2 = PlayerStats {
            hp: p2.get_item("hp")?.unwrap().extract::<f64>()? as f32,
            sp: p2.get_item("sp")?.unwrap().extract::<f64>()? as f32,
            mp: p2.get_item("mp")?.unwrap().extract::<f64>()? as f32,
        };
        let structs = d.get_item("structures")?
            .ok_or_else(|| pyo3::exceptions::PyKeyError::new_err("structures"))?;
        let structs = structs.downcast::<PyList>()?;
        for item in structs.iter() {
            let s = item.downcast::<PyDict>()?;
            let xy: (i32, i32) = s.get_item("xy")?.unwrap().extract::<Vec<i32>>().map(|v| (v[0], v[1]))?;
            let tsr_raw = s.get_item("turn_start_removal")?.unwrap();
            let tsr: Option<i32> = if tsr_raw.is_none() {
                None
            } else {
                Some(tsr_raw.extract::<i32>()?)
            };
            let uid_s: String = s.get_item("uid")?.unwrap().extract()?;
            state.structures.insert(xy, Structure {
                xy,
                type_idx: s.get_item("type_idx")?.unwrap().extract::<i32>()?,
                upgraded: s.get_item("upgraded")?.unwrap().extract::<bool>()?,
                hp: s.get_item("hp")?.unwrap().extract::<f64>()? as f32,
                uid: parse_uid_str(&uid_s)?,
                player: s.get_item("player")?.unwrap().extract::<u8>()?,
                turn_start_removal: tsr,
                shielded_already: Vec::new(),
            });
        }
        let mobs = d.get_item("mobiles")?
            .ok_or_else(|| pyo3::exceptions::PyKeyError::new_err("mobiles"))?;
        let mobs = mobs.downcast::<PyList>()?;
        for item in mobs.iter() {
            let m = item.downcast::<PyDict>()?;
            let xy: (i32, i32) = m.get_item("xy")?.unwrap().extract::<Vec<i32>>().map(|v| (v[0], v[1]))?;
            let spawn_xy: (i32, i32) = m.get_item("spawn_xy")?.unwrap().extract::<Vec<i32>>().map(|v| (v[0], v[1]))?;
            let uid_s: String = m.get_item("uid")?.unwrap().extract()?;
            state.mobiles.push(Mobile {
                xy,
                type_idx: m.get_item("type_idx")?.unwrap().extract::<i32>()?,
                hp: m.get_item("hp")?.unwrap().extract::<f64>()? as f32,
                shield: m.get_item("shield")?.unwrap().extract::<f64>()? as f32,
                uid: parse_uid_str(&uid_s)?,
                player: m.get_item("player")?.unwrap().extract::<u8>()?,
                spawn_xy,
                target_edge: m.get_item("target_edge")?.unwrap().extract::<i32>()?,
                steps_taken: m.get_item("steps_taken")?.unwrap().extract::<i32>()?,
                move_buildup: m.get_item("move_buildup")?.unwrap().extract::<f64>()? as f32,
                last_move: m.get_item("last_move")?.unwrap().extract::<i32>()?,
                finished_navigating: m.get_item("finished_navigating")?.unwrap().extract::<bool>()?,
                reached_target: m.get_item("reached_target")?.unwrap().extract::<bool>()?,
                breached: m.get_item("breached")?.unwrap().extract::<bool>()?,
            });
        }
        Ok(state)
    }

    fn sim_state_to_dict<'py>(py: Python<'py>, s: &SimState) -> PyResult<Bound<'py, PyDict>> {
        let d = PyDict::new_bound(py);
        d.set_item("turn", s.turn)?;
        let p1 = PyDict::new_bound(py);
        p1.set_item("hp", s.p1.hp as f64)?;
        p1.set_item("sp", s.p1.sp as f64)?;
        p1.set_item("mp", s.p1.mp as f64)?;
        d.set_item("p1", p1)?;
        let p2 = PyDict::new_bound(py);
        p2.set_item("hp", s.p2.hp as f64)?;
        p2.set_item("sp", s.p2.sp as f64)?;
        p2.set_item("mp", s.p2.mp as f64)?;
        d.set_item("p2", p2)?;
        let structs = PyList::empty_bound(py);
        for st in s.structures.values() {
            let sd = PyDict::new_bound(py);
            sd.set_item("xy", st.xy)?;
            sd.set_item("type_idx", st.type_idx)?;
            sd.set_item("upgraded", st.upgraded)?;
            sd.set_item("hp", st.hp as f64)?;
            sd.set_item("uid", st.uid.to_string())?;
            sd.set_item("player", st.player)?;
            match st.turn_start_removal {
                Some(v) => sd.set_item("turn_start_removal", v)?,
                None => sd.set_item("turn_start_removal", py.None())?,
            }
            structs.append(sd)?;
        }
        d.set_item("structures", structs)?;
        let mobs = PyList::empty_bound(py);
        for m in s.mobiles.iter() {
            let md = PyDict::new_bound(py);
            md.set_item("xy", m.xy)?;
            md.set_item("type_idx", m.type_idx)?;
            md.set_item("hp", m.hp as f64)?;
            md.set_item("shield", m.shield as f64)?;
            md.set_item("uid", m.uid.to_string())?;
            md.set_item("player", m.player)?;
            md.set_item("spawn_xy", m.spawn_xy)?;
            md.set_item("target_edge", m.target_edge)?;
            md.set_item("steps_taken", m.steps_taken)?;
            md.set_item("move_buildup", m.move_buildup as f64)?;
            md.set_item("last_move", m.last_move)?;
            md.set_item("finished_navigating", m.finished_navigating)?;
            md.set_item("reached_target", m.reached_target)?;
            md.set_item("breached", m.breached)?;
            mobs.append(md)?;
        }
        d.set_item("mobiles", mobs)?;
        Ok(d)
    }

    /// Run the Rust action-phase simulator on `state_dict` using the config
    /// at `config_path`. Returns the post-sim state as a dict with the same
    /// schema as `_py_state_to_dict` in `cross_validate.py`.
    ///
    /// UIDs crossing the PyO3 boundary are stringified integers (to match
    /// replay JSON); on the Rust side they are stored as `u32`.
    #[pyfunction]
    fn simulate_action_phase_py<'py>(
        py: Python<'py>,
        state_dict: Bound<'py, PyDict>,
        config_path: String,
    ) -> PyResult<Bound<'py, PyDict>> {
        let cfg = SimConfig::load(std::path::Path::new(&config_path))
            .map_err(|e| pyo3::exceptions::PyRuntimeError::new_err(
                format!("config load failed: {e}")))?;
        let mut state = build_state_from_dict(&state_dict)?;
        let _ = simulate_action_phase(&mut state, &cfg, 200);
        sim_state_to_dict(py, &state)
    }

    /// Debug helper: run N frames of the action phase and return the
    /// final `(mobile_xy, steps_taken, last_move, finished_navigating,
    /// reached_target)` of the first mobile plus final sp/hp.
    #[pyfunction]
    fn debug_trace_py<'py>(
        py: Python<'py>,
        state_dict: Bound<'py, PyDict>,
        config_path: String,
        max_frames: i32,
    ) -> PyResult<Bound<'py, PyDict>> {
        use crate::events::EventEntry;
        use crate::systems::{
            clear_destroyed, ensure_pathfinders, system_attack, system_breach,
            system_collision, system_move, system_remove_own_unit, system_self_destruct,
            system_shield_decay, system_shield_give,
        };
        let cfg = SimConfig::load(std::path::Path::new(&config_path))
            .map_err(|e| pyo3::exceptions::PyRuntimeError::new_err(
                format!("config load failed: {e}")))?;
        let mut state = build_state_from_dict(&state_dict)?;
        ensure_pathfinders(&mut state);
        let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
        let trace = PyList::empty_bound(py);
        let mut f = 0;
        while f < max_frames {
            evs.clear();
            system_move(&mut state, &cfg, &mut evs);
            system_collision(&mut state, &cfg, &mut evs);
            system_shield_decay(&mut state, &cfg, &mut evs);
            system_shield_give(&mut state, &cfg, &mut evs);
            system_breach(&mut state, &cfg, &mut evs);
            system_self_destruct(&mut state, &cfg, &mut evs);
            system_attack(&mut state, &cfg, &mut evs);
            clear_destroyed(&mut state, &mut evs);
            system_remove_own_unit(&mut state, &cfg, &mut evs);
            // Snapshot per-frame: mobile xy + flags (first mobile only).
            let entry = PyDict::new_bound(py);
            entry.set_item("frame", f)?;
            entry.set_item("mob_count", state.mobiles.len())?;
            if let Some(m) = state.mobiles.first() {
                entry.set_item("xy", m.xy)?;
                entry.set_item("steps_taken", m.steps_taken)?;
                entry.set_item("last_move", m.last_move)?;
                entry.set_item("move_buildup", m.move_buildup as f64)?;
                entry.set_item("finished_navigating", m.finished_navigating)?;
                entry.set_item("reached_target", m.reached_target)?;
                entry.set_item("breached", m.breached)?;
                entry.set_item("hp", m.hp as f64)?;
            }
            entry.set_item("p1_sp", state.p1.sp as f64)?;
            entry.set_item("p2_hp", state.p2.hp as f64)?;
            trace.append(entry)?;
            if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 { f += 1; break; }
            if state.mobiles.is_empty() { f += 1; break; }
            f += 1;
        }
        let out = PyDict::new_bound(py);
        out.set_item("trace", trace)?;
        out.set_item("final_mob_count", state.mobiles.len())?;
        out.set_item("p1_sp", state.p1.sp as f64)?;
        out.set_item("p2_hp", state.p2.hp as f64)?;
        out.set_item("frames", f)?;
        Ok(out)
    }

    #[pymodule]
    fn sim_rs(_py: Python<'_>, m: &Bound<'_, PyModule>) -> PyResult<()> {
        m.add("__version__", env!("CARGO_PKG_VERSION"))?;
        m.add_function(wrap_pyfunction!(simulate_action_phase_py, m)?)?;
        m.add_function(wrap_pyfunction!(debug_trace_py, m)?)?;
        Ok(())
    }
}
