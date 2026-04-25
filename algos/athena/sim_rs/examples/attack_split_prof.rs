//! Split system_attack timing into: dirty-rebuild cost, outer-loop +
//! bbox check cost (fires skipped), and fire body cost. Uses a replay
//! harness that can disable individual sub-phases via feature flags
//! within the example (no library mod needed — we re-implement the outer
//! loop here).

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

fn run_full(tmpl: &SimState, cfg: &SimConfig, n: u32) -> f64 {
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
    let t_start = Instant::now();
    for _ in 0..n {
        let mut s = tmpl.clone_no_scratch();
        let mut f = 0;
        while f < 200 {
            evs.clear();
            system_move(&mut s, cfg, &mut evs);
            system_collision(&mut s, cfg, &mut evs);
            system_shield_decay(&mut s, cfg, &mut evs);
            system_shield_give(&mut s, cfg, &mut evs);
            system_breach(&mut s, cfg, &mut evs);
            system_self_destruct(&mut s, cfg, &mut evs);
            system_attack(&mut s, cfg, &mut evs);
            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, cfg, &mut evs);
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { break; }
            if s.mobiles.is_empty() { break; }
            f += 1;
        }
    }
    t_start.elapsed().as_nanos() as f64 / n as f64 / 1000.0
}

fn run_no_attack(tmpl: &SimState, cfg: &SimConfig, n: u32) -> f64 {
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
    let t_start = Instant::now();
    for _ in 0..n {
        let mut s = tmpl.clone_no_scratch();
        let mut f = 0;
        while f < 200 {
            evs.clear();
            system_move(&mut s, cfg, &mut evs);
            system_collision(&mut s, cfg, &mut evs);
            system_shield_decay(&mut s, cfg, &mut evs);
            system_shield_give(&mut s, cfg, &mut evs);
            system_breach(&mut s, cfg, &mut evs);
            system_self_destruct(&mut s, cfg, &mut evs);
            // no attack
            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, cfg, &mut evs);
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { break; }
            if s.mobiles.is_empty() { break; }
            f += 1;
        }
    }
    t_start.elapsed().as_nanos() as f64 / n as f64 / 1000.0
}

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut tmpl = build_state_fixture();
    ensure_pathfinders(&mut tmpl);
    const N: u32 = 5000;

    // warm
    for _ in 0..100 {
        let mut s = tmpl.clone_no_scratch();
        let mut evs: Vec<EventEntry> = Vec::new();
        system_move(&mut s, &cfg, &mut evs);
        system_attack(&mut s, &cfg, &mut evs);
    }

    let best_of = |f: &dyn Fn() -> f64| -> f64 {
        let mut m = f64::MAX;
        for _ in 0..5 {
            let v = f();
            if v < m { m = v; }
        }
        m
    };

    let full = best_of(&|| run_full(&tmpl, &cfg, N));
    let no_attack = best_of(&|| run_no_attack(&tmpl, &cfg, N));
    println!("full        : {:.2} µs/sim", full);
    println!("no_attack   : {:.2} µs/sim", no_attack);
    println!("attack impl : {:.2} µs/sim", full - no_attack);
}
