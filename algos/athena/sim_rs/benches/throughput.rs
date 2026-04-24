//! Throughput benchmark — measures sims/sec on a realistic mid-game fixture.
//!
//! Loads `fixtures/mid_game_turn.json` — a turn extracted from
//! `v13_360023_m15302618_vs_python-algo-turtle-v4_1609_win.replay` (turn 38,
//! 108 structures, 5 mobiles, active shields). This reflects realistic
//! late-game load (targeting cascade over many candidates, SD AOE, shield-
//! give hot) — 3-mobile/5-structure toy fixtures underestimate.
//!
//! A second "toy" benchmark is retained for comparability with the original
//! baseline (3 mobiles, 5 structures).

use std::{fs, path::PathBuf};

use criterion::{black_box, criterion_group, criterion_main, Criterion, Throughput};
use serde_json::Value;

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

fn fixture_path() -> PathBuf {
    let mut p = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    p.push("fixtures");
    p.push("mid_game_turn.json");
    p
}

fn build_state_toy() -> SimState {
    let mut state = SimState::new(5);
    state.p1 = PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 };
    state.p2 = PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 };

    let structs: [((i32, i32), i32, bool, f32, &str); 5] = [
        ((13, 14), IDX_TURRET,  false, 75.0,  "100"),
        ((14, 14), IDX_TURRET,  true,  100.0, "101"),
        ((13, 17), IDX_SUPPORT, true,  40.0,  "102"),
        ((12, 14), IDX_WALL,    false, 60.0,  "103"),
        ((15, 14), IDX_WALL,    true,  200.0, "104"),
    ];
    for (xy, type_idx, upgraded, hp, uid) in structs.iter() {
        state.structures.insert(*xy, Structure {
            xy: *xy, type_idx: *type_idx, upgraded: *upgraded,
            hp: *hp, uid: uid.to_string(), player: 2,
            turn_start_removal: None, shielded_already: Vec::new(),
        });
    }
    let scouts: [((i32, i32), i32, &str); 3] = [
        ((13, 0), EDGE_TOP_RIGHT, "200"),
        ((14, 0), EDGE_TOP_LEFT,  "201"),
        ((12, 1), EDGE_TOP_RIGHT, "202"),
    ];
    for (xy, target_edge, uid) in scouts.iter() {
        state.mobiles.push(Mobile {
            xy: *xy, type_idx: IDX_SCOUT, hp: 15.0, shield: 0.0,
            uid: uid.to_string(), player: 1, spawn_xy: *xy,
            target_edge: *target_edge, steps_taken: 0, move_buildup: 0.0,
            last_move: 0, finished_navigating: false,
            reached_target: false, breached: false,
        });
    }
    state
}

fn build_state_fixture() -> SimState {
    let data = fs::read_to_string(fixture_path()).expect("fixture json");
    let v: Value = serde_json::from_str(&data).expect("json parse");
    let turn = v["turn"].as_i64().unwrap() as i32;
    let mut state = SimState::new(turn);
    let p1 = &v["p1"];
    state.p1 = PlayerStats {
        hp: p1["hp"].as_f64().unwrap() as f32,
        sp: p1["sp"].as_f64().unwrap() as f32,
        mp: p1["mp"].as_f64().unwrap() as f32,
    };
    let p2 = &v["p2"];
    state.p2 = PlayerStats {
        hp: p2["hp"].as_f64().unwrap() as f32,
        sp: p2["sp"].as_f64().unwrap() as f32,
        mp: p2["mp"].as_f64().unwrap() as f32,
    };
    for s in v["structures"].as_array().unwrap() {
        let xy = s["xy"].as_array().unwrap();
        let xy = (xy[0].as_i64().unwrap() as i32, xy[1].as_i64().unwrap() as i32);
        let tsr = if s["turn_start_removal"].is_null() {
            None
        } else {
            Some(s["turn_start_removal"].as_i64().unwrap() as i32)
        };
        state.structures.insert(xy, Structure {
            xy,
            type_idx: s["type_idx"].as_i64().unwrap() as i32,
            upgraded: s["upgraded"].as_bool().unwrap(),
            hp: s["hp"].as_f64().unwrap() as f32,
            uid: s["uid"].as_str().unwrap().to_string(),
            player: s["player"].as_i64().unwrap() as u8,
            turn_start_removal: tsr,
            shielded_already: Vec::new(),
        });
    }
    for m in v["mobiles"].as_array().unwrap() {
        let xy = m["xy"].as_array().unwrap();
        let xy = (xy[0].as_i64().unwrap() as i32, xy[1].as_i64().unwrap() as i32);
        let sp = m["spawn_xy"].as_array().unwrap();
        let spawn_xy = (sp[0].as_i64().unwrap() as i32, sp[1].as_i64().unwrap() as i32);
        state.mobiles.push(Mobile {
            xy,
            type_idx: m["type_idx"].as_i64().unwrap() as i32,
            hp: m["hp"].as_f64().unwrap() as f32,
            shield: m["shield"].as_f64().unwrap() as f32,
            uid: m["uid"].as_str().unwrap().to_string(),
            player: m["player"].as_i64().unwrap() as u8,
            spawn_xy,
            target_edge: m["target_edge"].as_i64().unwrap() as i32,
            steps_taken: m["steps_taken"].as_i64().unwrap() as i32,
            move_buildup: m["move_buildup"].as_f64().unwrap() as f32,
            last_move: m["last_move"].as_i64().unwrap() as i32,
            finished_navigating: m["finished_navigating"].as_bool().unwrap(),
            reached_target: m["reached_target"].as_bool().unwrap(),
            breached: m["breached"].as_bool().unwrap(),
        });
    }
    state
}

fn throughput_bench(c: &mut Criterion) {
    let cfg = SimConfig::load(&snapshot_path()).expect("config load");

    // Toy fixture — retained for baseline comparability.
    {
        let mut template = build_state_toy();
        // Pre-warm pathfinders once — shared via clone() across bench iters,
        // matching the PyO3 batch-API usage pattern.
        sim_rs::systems::ensure_pathfinders(&mut template);
        let mut group = c.benchmark_group("simulate_action_phase");
        group.throughput(Throughput::Elements(1));
        group.bench_function("3_mob_5_struct", |b| {
            b.iter(|| {
                let mut state = template.clone();
                let r = simulate_action_phase(black_box(&mut state),
                                               black_box(&cfg), 200);
                black_box(r);
            });
        });
        group.finish();
    }

    // Realistic mid-game fixture — primary metric for the 25K target.
    {
        let mut template = build_state_fixture();
        sim_rs::systems::ensure_pathfinders(&mut template);
        let mut group = c.benchmark_group("simulate_action_phase");
        group.throughput(Throughput::Elements(1));
        group.bench_function("mid_game_108_struct_5_mob", |b| {
            b.iter(|| {
                let mut state = template.clone();
                let r = simulate_action_phase(black_box(&mut state),
                                               black_box(&cfg), 200);
                black_box(r);
            });
        });
        group.finish();
    }
}

criterion_group!(benches, throughput_bench);
criterion_main!(benches);
