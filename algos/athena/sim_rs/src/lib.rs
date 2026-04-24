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
pub mod state;

// Systems + fast-mode frame loop are not yet ported; the scaffold keeps them
// empty but already allocated so git history reveals the port's landing points.
// See ATHENA_BUILD_PLAN.md § Phase 5 for the sequencing notes.

#[cfg(feature = "pyo3")]
mod py_api {
    use pyo3::prelude::*;

    /// Placeholder PyO3 entry point. Full exports land alongside the system
    /// port; this stub exists so the build succeeds end-to-end.
    #[pymodule]
    fn sim_rs(_py: Python<'_>, m: &Bound<'_, PyModule>) -> PyResult<()> {
        m.add("__version__", env!("CARGO_PKG_VERSION"))?;
        Ok(())
    }
}
