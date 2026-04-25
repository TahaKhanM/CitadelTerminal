//! 8-core rayon benchmark — measures sims/sec when running a batch of
//! simulations in parallel. Uses `simulate_batch_parallel` which clones the
//! template per-sim and runs `simulate_action_phase_lite` on rayon workers.
//!
//! Reports best-of-3 and median sims/sec on the realistic `mid_game_108_struct_5_mob`
//! fixture. Batch size is 2,048 — large enough for rayon to schedule across
//! the machine's logical cores.

use std::{fs, path::PathBuf, time::Instant};

use serde_json::Value;

use sim_rs::config::SimConfig;
use sim_rs::sim::simulate_batch_parallel;
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
            uid: s["uid"].as_str().unwrap().parse::<u32>().expect("numeric uid"),
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
            uid: m["uid"].as_str().unwrap().parse::<u32>().expect("numeric uid"),
            player: m["player"].as_i64().unwrap() as u8,
            spawn_xy,
            target_edge: m["target_edge"].as_i64().unwrap() as i32,
            steps_taken: m["steps_taken"].as_i64().unwrap() as i32,
            move_buildup: m["move_buildup"].as_f64().unwrap() as f32,
            last_move: m["last_move"].as_i64().unwrap() as i32,
            finished_navigating: m["finished_navigating"].as_bool().unwrap(),
            reached_target: m["reached_target"].as_bool().unwrap(),
            breached: m["breached"].as_bool().unwrap()
        });
    }
    state
}

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).expect("config load");
    let mut tmpl = build_state_fixture();
    // Pre-warm pathfinders on the template (shared via clone into workers).
    sim_rs::systems::ensure_pathfinders(&mut tmpl);

    println!("rayon num_threads = {}", rayon::current_num_threads());

    // Warm up.
    for _ in 0..3 {
        let r = simulate_batch_parallel(&tmpl, &cfg, 256, 200);
        std::hint::black_box(&r);
    }

    const BATCH: usize = 2048;
    let mut samples = Vec::new();
    for _ in 0..5 {
        let start = Instant::now();
        let r = simulate_batch_parallel(&tmpl, &cfg, BATCH, 200);
        std::hint::black_box(&r);
        samples.push(start.elapsed().as_nanos() as u64);
    }
    samples.sort();
    let best_ns = samples[0];
    let median_ns = samples[samples.len() / 2];
    let per_sim_ns_best = best_ns as f64 / BATCH as f64;
    let per_sim_ns_median = median_ns as f64 / BATCH as f64;
    let sps_best = 1.0e9 / per_sim_ns_best;
    let sps_median = 1.0e9 / per_sim_ns_median;
    println!(
        "mid_game_108_struct_5_mob (rayon batch={BATCH})  best={:.0}/s  median={:.0}/s  ({:.2} µs/{:.2} µs per-sim)",
        sps_best, sps_median, per_sim_ns_best / 1000.0, per_sim_ns_median / 1000.0,
    );
}
