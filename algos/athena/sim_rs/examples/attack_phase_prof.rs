//! Breaks system_attack's ~65µs/sim cost into (a) dirty-rebuild phase
//! and (b) per-frame fire loop phase. This tells us whether SIMD on the
//! rebuild (runs ~4× per sim) or on the fire loop (runs ~69× per sim) is
//! the higher-leverage target.

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

    // Full baseline.
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
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { break; }
            if s.mobiles.is_empty() { break; }
            f += 1;
        }
    }
    let baseline_us = t.elapsed().as_nanos() as f64 / N as f64 / 1000.0;
    println!("full sim baseline           : {:>7.2} µs/sim", baseline_us);

    // Run ONE action phase, then re-run system_attack repeatedly on the
    // resulting state. Between attack calls, reset p1/p2 HP so fires
    // happen (otherwise after one kill the picks change). We want to
    // measure cost of a non-dirty attack call.
    let mut tmpl2 = tmpl.clone_no_scratch();
    // Prime by running one full sim to warm scratch.
    let mut evs2: Vec<EventEntry> = Vec::with_capacity(32);
    for _f in 0..10 {
        evs2.clear();
        system_move(&mut tmpl2, &cfg, &mut evs2);
        system_shield_give(&mut tmpl2, &cfg, &mut evs2);
        system_breach(&mut tmpl2, &cfg, &mut evs2);
        system_self_destruct(&mut tmpl2, &cfg, &mut evs2);
        system_attack(&mut tmpl2, &cfg, &mut evs2);
    }
    // Remove any dirty marker so the next attack takes the "no rebuild" path.
    tmpl2.scratch.structures_dirty = false;
    // Freeze a post-prime snapshot that we'll clone before each measurement
    // so scratch + live mob bbox are already populated (steady-state shape).
    let primed = tmpl2.clone();

    // Measure NON-DIRTY system_attack cost over many frames. Reset state.mobiles
    // HP each time to prevent mobiles dying (which would empty the mobile list).
    let t = Instant::now();
    for _ in 0..N {
        let mut s = primed.clone_no_scratch();
        let mut evs3: Vec<EventEntry> = Vec::with_capacity(32);
        for _ in 0..10 {
            evs3.clear();
            // Reset mobile HP so they survive to fire next turret iter.
            for m in s.mobiles.iter_mut() { m.hp = 15.0; m.shield = 0.0; }
            // Keep dirty=false — test the steady-state path.
            s.scratch.structures_dirty = false;
            system_attack(&mut s, &cfg, &mut evs3);
        }
    }
    let attack_steady_us = t.elapsed().as_nanos() as f64 / N as f64 / 10.0 / 1000.0;
    println!("system_attack steady-state  : {:>7.2} µs/call (non-dirty)", attack_steady_us);

    // Measure DIRTY attack cost.
    let t = Instant::now();
    for _ in 0..N {
        let mut s = primed.clone_no_scratch();
        let mut evs3: Vec<EventEntry> = Vec::with_capacity(32);
        for _ in 0..10 {
            evs3.clear();
            for m in s.mobiles.iter_mut() { m.hp = 15.0; m.shield = 0.0; }
            s.scratch.structures_dirty = true;
            system_attack(&mut s, &cfg, &mut evs3);
        }
    }
    let attack_dirty_us = t.elapsed().as_nanos() as f64 / N as f64 / 10.0 / 1000.0;
    println!("system_attack dirty-rebuild : {:>7.2} µs/call", attack_dirty_us);

    let rebuild_extra = attack_dirty_us - attack_steady_us;
    println!();
    println!("=== implied ===");
    println!("steady-state fire loop cost : {:>7.2} µs/frame", attack_steady_us);
    println!("dirty-rebuild extra cost    : {:>7.2} µs / rebuild", rebuild_extra);
    println!("fire loop × 69 frames       : {:>7.2} µs/sim", attack_steady_us * 69.0);
    println!("rebuild × 4 rebuilds        : {:>7.2} µs/sim", rebuild_extra * 4.0);
}
