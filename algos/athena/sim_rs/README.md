# sim_rs — Rust port of SimCore

Bit-exact Rust mirror of `algos/athena/sim/` (Citadel Terminal action-phase
simulator). Gated against the same C-tight-coherent parity gate (19 STRICT /
4 CASCADE <1% / throughput ≥50 sims/s).

## Status: SCAFFOLD

Directory layout, Cargo.toml, and module skeletons are in place. The full port
lands in Phase 5 per `docs/ATHENA_BUILD_PLAN.md` using four concurrent sub-agents
(R1: state+config, R2: pathfinder, R3: proximity+events, R4: systems).

## What's here

```
sim_rs/
├── Cargo.toml            # workspace, PyO3, bumpalo, indexmap, criterion
└── src/
    ├── lib.rs            # module plan + PyO3 entry point stub
    ├── state.rs          # Structure / Mobile / PlayerStats / SimState
    ├── config.rs         # StructureSpec / MobileSpec / Resources
    ├── map.rs            # diamond geometry (on_diamond, edge_tiles)
    ├── pathfinder.rs     # constants; full port pending (mirrors PATHFINDER_SPEC)
    ├── proximity.rs      # IndexMap-backed arena stub
    └── events.rs         # Canonical-key types for multiset-equality gate
```

## Parity contract

Identical to Python per `algos/athena/sim/SIM_PARITY.md`:

* 19 STRICT columns @ max_err=0 on 23 ranked + 10K fuzz
* 4 CASCADE columns multiset-equal, <1% of corpus, all JVM-HashSet-attributable
* Python ↔ Rust byte-identical on same corpus under canonical ordering
  (cross-validator: `algos/athena/sim/cross_validate.py`)

## Build (when implemented)

```bash
cd algos/athena/sim_rs
cargo build --release --features pyo3
cargo test
cargo bench --bench action_phase
```
