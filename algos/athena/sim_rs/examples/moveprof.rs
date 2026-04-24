//! Microprofiler: run only system_move in isolation over many frames.

use std::{fs, path::PathBuf, time::Instant};
use serde_json::Value;
use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::{system_move, ensure_pathfinders};

fn snapshot_path() -> PathBuf {
    let mut p = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    p.push(".."); p.push("data"); p.push("citadel_config_snapshot.json"); p
}
fn fixture_path() -> PathBuf {
    let mut p = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    p.push("fixtures"); p.push("mid_game_turn.json"); p
}

fn build_state_fixture() -> SimState {
    let data = fs::read_to_string(fixture_path()).unwrap();
    let v: Value = serde_json::from_str(&data).unwrap();
    let turn = v["turn"].as_i64().unwrap() as i32;
    let mut state = SimState::new(turn);
    let p1 = &v["p1"];
    state.p1 = PlayerStats { hp: p1["hp"].as_f64().unwrap() as f32, sp: p1["sp"].as_f64().unwrap() as f32, mp: p1["mp"].as_f64().unwrap() as f32 };
    let p2 = &v["p2"];
    state.p2 = PlayerStats { hp: p2["hp"].as_f64().unwrap() as f32, sp: p2["sp"].as_f64().unwrap() as f32, mp: p2["mp"].as_f64().unwrap() as f32 };
    for s in v["structures"].as_array().unwrap() {
        let xy = s["xy"].as_array().unwrap();
        let xy = (xy[0].as_i64().unwrap() as i32, xy[1].as_i64().unwrap() as i32);
        let tsr = if s["turn_start_removal"].is_null() { None } else { Some(s["turn_start_removal"].as_i64().unwrap() as i32) };
        state.structures.insert(xy, Structure {
            xy, type_idx: s["type_idx"].as_i64().unwrap() as i32,
            upgraded: s["upgraded"].as_bool().unwrap(), hp: s["hp"].as_f64().unwrap() as f32,
            uid: s["uid"].as_str().unwrap().to_string(), player: s["player"].as_i64().unwrap() as u8,
            turn_start_removal: tsr, shielded_already: Vec::new(),
        });
    }
    for m in v["mobiles"].as_array().unwrap() {
        let xy = m["xy"].as_array().unwrap();
        let xy = (xy[0].as_i64().unwrap() as i32, xy[1].as_i64().unwrap() as i32);
        let sp = m["spawn_xy"].as_array().unwrap();
        let spawn_xy = (sp[0].as_i64().unwrap() as i32, sp[1].as_i64().unwrap() as i32);
        state.mobiles.push(Mobile {
            xy, type_idx: m["type_idx"].as_i64().unwrap() as i32,
            hp: m["hp"].as_f64().unwrap() as f32, shield: m["shield"].as_f64().unwrap() as f32,
            uid: m["uid"].as_str().unwrap().to_string(), player: m["player"].as_i64().unwrap() as u8,
            spawn_xy, target_edge: m["target_edge"].as_i64().unwrap() as i32,
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

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut template = build_state_fixture();
    ensure_pathfinders(&mut template);

    const N: u32 = 5000;

    // Measure just the FIRST system_move call (warmest case).
    let mut total_ns = 0u128;
    for _ in 0..N {
        let mut state = template.clone();
        let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
        let t = Instant::now();
        system_move(&mut state, &cfg, &mut evs);
        total_ns += t.elapsed().as_nanos();
        std::hint::black_box(&state);
    }
    println!("first system_move after clone:  {:.2} µs/call",
             total_ns as f64 / N as f64 / 1000.0);

    // Measure the AVERAGE per-frame cost over 50 frames.
    let mut total_ns = 0u128;
    let mut frames = 0u64;
    for _ in 0..N {
        let mut state = template.clone();
        let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
        for _ in 0..50 {
            evs.clear();
            let t = Instant::now();
            system_move(&mut state, &cfg, &mut evs);
            total_ns += t.elapsed().as_nanos();
            frames += 1;
            if state.mobiles.iter().all(|m| m.finished_navigating) { break; }
        }
    }
    println!("avg per-frame system_move:      {:.2} µs/frame ({} frames)",
             total_ns as f64 / frames as f64 / 1000.0, frames);

    // Measure total simulate cost (clone + system_move only, no other systems).
    let mut total_ns = 0u128;
    for _ in 0..N {
        let t = Instant::now();
        let mut state = template.clone();
        let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
        for _ in 0..200 {
            evs.clear();
            system_move(&mut state, &cfg, &mut evs);
            if state.mobiles.iter().all(|m| m.finished_navigating) { break; }
        }
        total_ns += t.elapsed().as_nanos();
    }
    println!("clone+50×system_move:           {:.2} µs/run",
             total_ns as f64 / N as f64 / 1000.0);
}
