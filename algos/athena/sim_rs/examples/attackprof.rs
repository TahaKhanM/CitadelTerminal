//! Line-level profile of system_attack's hot sub-functions.
//!
//! Runs N action-phase sims and times steady-state per-frame attack cost
//! via a loop that separates clone/setup overhead from the attack body.

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

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut tmpl = build_state_fixture();
    ensure_pathfinders(&mut tmpl);
    const N: u32 = 20000;

    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);

    // Baseline: full simulate.
    let t = Instant::now();
    for _ in 0..N {
        let mut s = tmpl.clone_no_scratch();
        let mut f = 0;
        while f < 200 {
            evs.clear();
            system_move(&mut s, &cfg, &mut evs);
            system_collision(&mut s, &cfg, &mut evs);
            system_shield_decay(&mut s, &cfg, &mut evs);
            system_shield_give(&mut s, &cfg, &mut evs);
            system_breach(&mut s, &cfg, &mut evs);
            system_self_destruct(&mut s, &cfg, &mut evs);
            system_attack(&mut s, &cfg, &mut evs);
            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, &cfg, &mut evs);
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { f += 1; break; }
            if s.mobiles.is_empty() { f += 1; break; }
            f += 1;
        }
    }
    let baseline_ns = t.elapsed().as_nanos();
    println!("baseline (full frame loop)          {:>8.2} µs/run", baseline_ns as f64 / N as f64 / 1000.0);

    // Skip attack only.
    let t = Instant::now();
    for _ in 0..N {
        let mut s = tmpl.clone_no_scratch();
        let mut f = 0;
        while f < 200 {
            evs.clear();
            system_move(&mut s, &cfg, &mut evs);
            system_collision(&mut s, &cfg, &mut evs);
            system_shield_decay(&mut s, &cfg, &mut evs);
            system_shield_give(&mut s, &cfg, &mut evs);
            system_breach(&mut s, &cfg, &mut evs);
            system_self_destruct(&mut s, &cfg, &mut evs);
            // system_attack skipped
            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, &cfg, &mut evs);
            f += 1;
            if f >= 30 { break; } // mobiles never die without attack; cap frames
        }
    }
    let skip_attack_ns = t.elapsed().as_nanos();
    println!("skip system_attack (cap 30 frames)  {:>8.2} µs/run", skip_attack_ns as f64 / N as f64 / 1000.0);

    // Skip all targeting (attack + SD) — isolates non-targeting overhead.
    let t = Instant::now();
    for _ in 0..N {
        let mut s = tmpl.clone_no_scratch();
        let mut f = 0;
        while f < 200 {
            evs.clear();
            system_move(&mut s, &cfg, &mut evs);
            system_shield_give(&mut s, &cfg, &mut evs);
            system_breach(&mut s, &cfg, &mut evs);
            clear_destroyed(&mut s, &mut evs);
            f += 1;
            if f >= 30 { break; }
        }
    }
    let skip_targ_ns = t.elapsed().as_nanos();
    println!("skip attack + SD (cap 30 frames)    {:>8.2} µs/run", skip_targ_ns as f64 / N as f64 / 1000.0);

    // Clone only.
    let t = Instant::now();
    for _ in 0..N {
        let s = tmpl.clone_no_scratch();
        std::hint::black_box(&s);
    }
    let clone_ns = t.elapsed().as_nanos();
    println!("clone_no_scratch only               {:>8.2} µs/run", clone_ns as f64 / N as f64 / 1000.0);

    println!("");
    println!("=== implied ===");
    let attack_per_sim_us = (baseline_ns.saturating_sub(skip_attack_ns)) as f64 / N as f64 / 1000.0;
    println!("system_attack contribution ≈ {:.2} µs/sim (baseline − no-attack)", attack_per_sim_us);
}
