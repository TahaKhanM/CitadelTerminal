# sim_rs — Rust port of SimCore

Bit-exact Rust mirror of `algos/athena/sim/` (the Citadel Terminal action-phase
simulator). Built so the search agent (`algos/oracle_pure/`, `algos/smart_oracle_F2/`)
can roll thousands of candidate futures forward inside the 15-second turn budget.

## Status: COMPLETE

Full port landed and shipped. ~4,600 lines of Rust across `src/`, exposed to Python as a
PyO3/maturin compiled extension (`simulate_action_phase_py`).

| | |
|---|---|
| Throughput | **14.3 K games/s** single-core (~43× the 336/s Python reference); **75 K games/s** on 10 threads |
| Rust tests | **42 / 42** passing (`cargo test --release`) |
| Python ↔ Rust parity | **13,319 / 13,319** fuzz frames byte-identical (commit `17d7376`) |
| Large-scale fuzz | 2× **1,000,000**-config campaigns (seeds 42 & 17), **0** failures |
| vs. Java engine | STRICT 19-column @ `max_err=0` across 87,677 frames / 23 ranked replays; CASCADE 262/87,677 = 0.30% (all JVM HashSet-ordering attributable) |

Full evidence: [`../sim/SIM_PARITY.md`](../sim/SIM_PARITY.md) and
[`../sim/PARITY_REPORTS/`](../sim/PARITY_REPORTS) (incl. `fuzz_1M_s42.md`, `fuzz_1M_s17.md`).

## Layout

```
sim_rs/
├── Cargo.toml            # PyO3, bumpalo, indexmap, criterion
└── src/
    ├── lib.rs            # module wiring + PyO3 entry point
    ├── state.rs          # Structure / Mobile / PlayerStats / SimState
    ├── config.rs         # StructureSpec / MobileSpec / Resources
    ├── map.rs            # diamond geometry (on_diamond, edge_tiles)
    ├── pathfinder.rs     # full pathing port (mirrors research/engine_decompiled/PATHFINDER_SPEC.md)
    ├── proximity.rs      # IndexMap-backed spatial arena
    ├── events.rs         # canonical-key types for the multiset-equality gate
    ├── systems.rs        # the frame loop: move → collision → shield → breach → self-destruct → attack
    └── sim.rs / bin/     # FAST + INSTRUMENTED drivers, stdio bridge
```

## Build

```bash
cd algos/athena/sim_rs
maturin develop --release            # build + install the Python wheel into the active env
cargo test --release                 # 42/42
cargo bench --bench action_phase     # throughput
```

## Parity contract

Identical to the Python sim per [`../sim/SIM_PARITY.md`](../sim/SIM_PARITY.md):

* 19 STRICT columns @ `max_err = 0` on 23 ranked replays + 10K fuzz
* 4 CASCADE columns multiset-equal, < 1 % of corpus, all JVM-HashSet-attributable
* Python ↔ Rust byte-identical under canonical ordering
  (cross-validator: `../sim/cross_validate.py`)
