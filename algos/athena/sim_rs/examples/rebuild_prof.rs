//! Isolate scratch-dirty rebuild cost from inner fire-loop cost by
//! comparing:
//!   A. 30 frames of attack-only with dirty=false (cached scratch, no
//!      structure deaths => no forced rebuild)
//!   B. 30 frames of attack-only with dirty=true forced before each call
//!      (worst-case full rebuild every frame)
//! The delta attributes frame time to (a) outer loop + inner fires vs
//! (b) the 108-structure scratch rebuild.

use std::{fs, path::PathBuf, time::Instant};
use serde_json::Value;
use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::{system_attack, ensure_pathfinders};

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
            uid: s["uid"].as_str().unwrap().parse::<u32>().expect("numeric uid"), player: s["player"].as_i64().unwrap() as u8,
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
            uid: m["uid"].as_str().unwrap().parse::<u32>().expect("numeric uid"), player: m["player"].as_i64().unwrap() as u8,
            spawn_xy, target_edge: m["target_edge"].as_i64().unwrap() as i32,
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
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut tmpl = build_state_fixture();
    ensure_pathfinders(&mut tmpl);
    const N: u32 = 20000;
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);

    // Warm caches: first call on each state will do a rebuild since scratch is dirty=true
    // after clone_no_scratch; subsequent calls reuse cached scratch.

    let t = Instant::now();
    for _ in 0..N {
        let mut s = tmpl.clone_no_scratch();
        // Force first rebuild.
        system_attack(&mut s, &cfg, &mut evs);
        evs.clear();
        // Now dirty is false. Run 29 more frames with cache hot.
        for _ in 0..29 {
            system_attack(&mut s, &cfg, &mut evs);
            evs.clear();
        }
    }
    let a = t.elapsed().as_nanos() as f64 / N as f64 / 1000.0;

    let t = Instant::now();
    for _ in 0..N {
        let mut s = tmpl.clone_no_scratch();
        for _ in 0..30 {
            // Mark dirty so every frame does a full rebuild.
            s.scratch.structures_dirty = true;
            system_attack(&mut s, &cfg, &mut evs);
            evs.clear();
        }
    }
    let b = t.elapsed().as_nanos() as f64 / N as f64 / 1000.0;

    let rebuild_cost = (b - a) / 29.0;  // 29 extra rebuilds in case B
    println!("A. 30 frames, 1 rebuild (normal):        {:.2} µs/sim", a);
    println!("B. 30 frames, 30 rebuilds (forced):      {:.2} µs/sim", b);
    println!("  => Δ = {:.2} µs over 29 extra rebuilds = {:.2} µs/rebuild", b - a, rebuild_cost);
    println!();
    println!("Baseline (4 rebuilds/sim × {:.2} µs) = {:.2} µs/sim overhead from rebuilds",
             rebuild_cost, 4.0 * rebuild_cost);
}
