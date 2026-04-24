//! Throughput benchmark — measures sims/sec for a small deterministic scenario.
//!
//! The goal of this bench is to track the FAST-mode cost of a complete
//! `simulate_action_phase` call (not a per-frame tick). The scenario is
//! intentionally small (3 mobiles + 5 structures) so the bench stays
//! dominated by per-frame system overhead, not per-unit work — that's the
//! surface the integration phase will target for its 25K sims/sec goal.

use std::path::PathBuf;

use criterion::{black_box, criterion_group, criterion_main, Criterion, Throughput};

use sim_rs::config::{SimConfig, IDX_SCOUT, IDX_SUPPORT, IDX_TURRET, IDX_WALL};
use sim_rs::map::{EDGE_TOP_LEFT, EDGE_TOP_RIGHT};
use sim_rs::sim::simulate_action_phase;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};

fn snapshot_path() -> PathBuf {
    let mut p = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    p.push("..");
    p.push("data");
    p.push("citadel_config_snapshot.json");
    p
}

fn build_state() -> SimState {
    let mut state = SimState::new(5);
    state.p1 = PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 };
    state.p2 = PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 };

    // 5 structures — 2 turrets, 1 support, 2 walls — for p2 defence line.
    let structs: [(
        (i32, i32),
        i32,
        bool,
        f32,
        &str,
    ); 5] = [
        ((13, 14), IDX_TURRET,  false, 75.0,  "100"),
        ((14, 14), IDX_TURRET,  true,  100.0, "101"),
        ((13, 17), IDX_SUPPORT, true,  40.0,  "102"),
        ((12, 14), IDX_WALL,    false, 60.0,  "103"),
        ((15, 14), IDX_WALL,    true,  200.0, "104"),
    ];
    for (xy, type_idx, upgraded, hp, uid) in structs.iter() {
        state.structures.insert(
            *xy,
            Structure {
                xy: *xy,
                type_idx: *type_idx,
                upgraded: *upgraded,
                hp: *hp,
                uid: uid.to_string(),
                player: 2,
                turn_start_removal: None,
                shielded_already: Vec::new(),
            },
        );
    }

    // 3 scouts from p1.
    let scouts: [((i32, i32), i32, &str); 3] = [
        ((13, 0), EDGE_TOP_RIGHT, "200"),
        ((14, 0), EDGE_TOP_LEFT, "201"),
        ((12, 1), EDGE_TOP_RIGHT, "202"),
    ];
    for (xy, target_edge, uid) in scouts.iter() {
        state.mobiles.push(Mobile {
            xy: *xy,
            type_idx: IDX_SCOUT,
            hp: 15.0,
            shield: 0.0,
            uid: uid.to_string(),
            player: 1,
            spawn_xy: *xy,
            target_edge: *target_edge,
            steps_taken: 0,
            move_buildup: 0.0,
            last_move: 0,
            finished_navigating: false,
            reached_target: false,
            breached: false,
        });
    }

    state
}

fn throughput_bench(c: &mut Criterion) {
    let cfg = SimConfig::load(&snapshot_path()).expect("config load");
    let template = build_state();

    let mut group = c.benchmark_group("simulate_action_phase");
    group.throughput(Throughput::Elements(1));
    group.bench_function("3_mob_5_struct", |b| {
        b.iter(|| {
            // Clone the template so each iteration starts from the same state.
            let mut state = template.clone();
            let result = simulate_action_phase(black_box(&mut state), black_box(&cfg), 200);
            black_box(result);
        });
    });
    group.finish();
}

criterion_group!(benches, throughput_bench);
criterion_main!(benches);
