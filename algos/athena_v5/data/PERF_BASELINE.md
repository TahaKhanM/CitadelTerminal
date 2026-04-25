# Athena v3 — Rust SimCore performance baseline

Captured: **2026-04-24** during Phase 0 task 3.

This is the budget basis for the search planner in Phase 4. The numbers
reflect the sim_rs branch as-of merge-base `8d8cd80` ("perf(sim_rs):
squared-distance + mob_near gate skip walker rebuild") which is the
latest perf push on `main`.

## Machine

| | |
|---|---|
| OS | Darwin 24.5.0 (macOS 15) |
| Kernel | xnu-11417.121.6 RELEASE_ARM64_T8132 |
| CPU | Apple M4 |
| Physical cores | 10 |
| Logical cores | 10 |
| RAM | 16.0 GB |
| Rust toolchain | cargo 1.94.1 (29ea6fb6a 2026-03-24) |
| Profile | `--release` |
| Default features | `instrumented` (event-bucket emission ON) |

`uname -a`:
```
Darwin Tahas-MacBook-Air.local 24.5.0 Darwin Kernel Version 24.5.0: Tue Apr 22 19:54:43 PDT 2025; root:xnu-11417.121.6~2/RELEASE_ARM64_T8132 arm64
```

## Single-core — `cargo bench --bench throughput -- mid_game_108_struct_5_mob --quick`

Criterion best-fit (3 estimates, low/median/high):

| Bench | time per sim | throughput |
|---|---|---|
| `mid_game_108_struct_5_mob` | 68.897 µs / 68.974 µs / 68.993 µs | **14.494 / 14.498 / 14.514 K sims/s** |
| `mid_game_108_struct_5_mob_lite` | 78.345 / 78.639 / 79.816 µs | 12.529 / 12.716 / 12.764 K sims/s |

- **Single-core baseline: 14.5 K sims/s** on the realistic mid-game fixture.
- The `lite` variant is **slower** here, presumably because the gate skip
  on walker rebuild is most effective at high mob density.

## Parallel — `cargo run --release --example parallel_bench`

```
rayon num_threads = 10
mid_game_108_struct_5_mob (rayon batch=2048)
  best   = 88,155 sims/s   (11.34 µs per sim)
  median = 86,092 sims/s   (11.62 µs per sim)
```

- **10-thread parallel baseline: ~88 K sims/s** (best), **~86 K sims/s** (median).
- Speedup vs single-core best: 88,155 / 14,514 ≈ **6.07×**, against an
  ideal 10× — typical of memory-bound sim work on M-series cores.

## Reconciliation with prior records

`SIM_PARITY.md` quotes "Rust 14.3 K FAST single-core / 75 K 10-thread"
captured before commits 9cffd99 / 481ac1b / 8d8cd80 landed. This
Phase 0 baseline (14.5 K single / 88 K parallel) is consistent with
those commits delivering a small further bump. The 25 K target was not
met; we proceed at 14.5 K as called out in the agent brief.

## Implications for the Phase 4 planner budget

Per-turn deploy budget: **15 s** (after which 1 dmg/sec penalty kicks in).
Reserve ~3 s headroom for non-search work (state parsing, action
emission, IO, JVM jitter).

| Search budget | Sims at 88 K/s parallel |
|---|---|
| 1.0 s | ~88,000 |
| 5.0 s | ~440,000 |
| 12.0 s (max safe) | ~1,056,000 |

A million-sim search per turn is comfortably feasible — Plan D viable.

## Reproduce

```bash
cd algos/athena/sim_rs
cargo bench --bench throughput -- mid_game_108_struct_5_mob --quick
cargo run --release --example parallel_bench
```

## Caveats

- Numbers are from a clean `cargo bench --quick` run (criterion does its
  own warm-up; the `lite` variant is included for context but is not the
  budget basis).
- Rayon batch defaults to 2048 in `parallel_bench`. Smaller/larger
  batches have not been swept here — Phase 4 should re-tune if the
  search granularity changes.
- `instrumented` feature is the default; FAST mode (no event emission)
  was not measured on this machine. The agent brief's "14.65 K FAST" is
  Linux-host data and not directly comparable to this M4 number.
