//! Bulk system-cost profiler. Run N action-phases; for each system,
//! measure the TOTAL time across all N runs × all frames by taking two
//! Instant::now() calls ONLY around a loop of 1000 calls in a row.
//! This amortizes the Instant overhead.

use std::{fs, path::PathBuf, time::Instant};
use serde_json::Value;
use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::{
    clear_destroyed, system_attack, system_breach, system_collision, system_move,
    system_remove_own_unit, system_self_destruct, system_shield_decay, system_shield_give,
    ensure_pathfinders,
};

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

/// Run N sims with ONE system replaced by a no-op wrapper, see net cost.
fn measure_with_system_skipped<F>(name: &str, template: &SimState, cfg: &SimConfig, n: u32, sys: F)
where F: Fn(&mut SimState, &SimConfig, &mut Vec<EventEntry>) {
    let _ = name;
    let _ = sys;
    // Actually simpler: measure bypassing one system entirely.
    // But that breaks correctness — can't measure like that.
    // Instead: time each system separately with a single outer Instant.
    let mut acc = 0u128;
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
    let t = Instant::now();
    for _ in 0..n {
        let mut state = template.clone();
        let mut frames = 0;
        while frames < 200 {
            evs.clear();
            system_move(&mut state, cfg, &mut evs);
            system_collision(&mut state, cfg, &mut evs);
            system_shield_decay(&mut state, cfg, &mut evs);
            system_shield_give(&mut state, cfg, &mut evs);
            system_breach(&mut state, cfg, &mut evs);
            system_self_destruct(&mut state, cfg, &mut evs);
            system_attack(&mut state, cfg, &mut evs);
            clear_destroyed(&mut state, &mut evs);
            system_remove_own_unit(&mut state, cfg, &mut evs);
            if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 { break; }
            if state.mobiles.is_empty() { break; }
            frames += 1;
        }
    }
    acc = t.elapsed().as_nanos();
    println!("{:30} {:>8.2} µs/run", name, acc as f64 / n as f64 / 1000.0);
}

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut template = build_state_fixture();
    ensure_pathfinders(&mut template);
    const N: u32 = 5000;

    // Baseline.
    let t = Instant::now();
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
    for _ in 0..N {
        let mut state = template.clone();
        let mut frames = 0;
        while frames < 200 {
            evs.clear();
            system_move(&mut state, &cfg, &mut evs);
            system_collision(&mut state, &cfg, &mut evs);
            system_shield_decay(&mut state, &cfg, &mut evs);
            system_shield_give(&mut state, &cfg, &mut evs);
            system_breach(&mut state, &cfg, &mut evs);
            system_self_destruct(&mut state, &cfg, &mut evs);
            system_attack(&mut state, &cfg, &mut evs);
            clear_destroyed(&mut state, &mut evs);
            system_remove_own_unit(&mut state, &cfg, &mut evs);
            if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 { break; }
            if state.mobiles.is_empty() { break; }
            frames += 1;
        }
    }
    let baseline = t.elapsed().as_nanos() as f64 / N as f64 / 1000.0;
    println!("baseline (all systems):        {:>8.2} µs/run", baseline);

    // SKIP attack — shows how much attack contributes.
    let t = Instant::now();
    for _ in 0..N {
        let mut state = template.clone();
        let mut frames = 0;
        while frames < 200 {
            evs.clear();
            system_move(&mut state, &cfg, &mut evs);
            system_shield_give(&mut state, &cfg, &mut evs);
            system_breach(&mut state, &cfg, &mut evs);
            system_self_destruct(&mut state, &cfg, &mut evs);
            // system_attack SKIPPED
            clear_destroyed(&mut state, &mut evs);
            system_remove_own_unit(&mut state, &cfg, &mut evs);
            if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 { break; }
            if state.mobiles.is_empty() { break; }
            frames += 1;
        }
    }
    println!("without attack:               {:>8.2} µs/run", t.elapsed().as_nanos() as f64 / N as f64 / 1000.0);

    // SKIP move — shows how much move contributes.
    let t = Instant::now();
    for _ in 0..N {
        let mut state = template.clone();
        let mut frames = 0;
        while frames < 200 {
            evs.clear();
            // system_move SKIPPED
            system_shield_give(&mut state, &cfg, &mut evs);
            system_breach(&mut state, &cfg, &mut evs);
            system_self_destruct(&mut state, &cfg, &mut evs);
            system_attack(&mut state, &cfg, &mut evs);
            clear_destroyed(&mut state, &mut evs);
            system_remove_own_unit(&mut state, &cfg, &mut evs);
            // Force loop exit after 50 frames (no mobiles dying/moving)
            frames += 1;
            if frames >= 50 { break; }
        }
    }
    println!("without move (50 frames):     {:>8.2} µs/run", t.elapsed().as_nanos() as f64 / N as f64 / 1000.0);

    // Pure clone + empty loop.
    let t = Instant::now();
    for _ in 0..N {
        let state = template.clone();
        std::hint::black_box(&state);
    }
    println!("clone only:                   {:>8.2} µs/run", t.elapsed().as_nanos() as f64 / N as f64 / 1000.0);
}
