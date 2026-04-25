//! Action-phase bench stub. Full frame-loop benchmarks land alongside the
//! systems port; this stub exists so `cargo build --release` succeeds with the
//! `[[bench]]` entry already declared in Cargo.toml.
//!
//! Engine citation: `Game.gameEngineLoop` — the systems sequence benchmarked
//! here (move → collision → shield_decay → shield_give → breach →
//! self_destruct → attack) becomes real once those systems are ported.

use criterion::{criterion_group, criterion_main, Criterion};

fn noop_bench(c: &mut Criterion) {
    c.bench_function("action_phase_noop", |b| b.iter(|| 1 + 1));
}

criterion_group!(benches, noop_bench);
criterion_main!(benches);
