//! Measure cache hit rate + system breakdown.
use std::{fs, path::PathBuf, time::Instant};
use serde_json::Value;
use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::{system_move, system_shield_give, system_breach, system_self_destruct, system_attack, clear_destroyed, system_remove_own_unit, ensure_pathfinders};

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
    let mut template = build_state_fixture();
    ensure_pathfinders(&mut template);
    const N: u32 = 2000;
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);

    // Time individual systems in aggregate
    let mut t_move = 0u128; let mut t_shield = 0u128; let mut t_breach = 0u128;
    let mut t_sd = 0u128; let mut t_att = 0u128; let mut t_cd = 0u128; let mut t_ro = 0u128;
    let mut total_frames = 0u32;
    for _ in 0..N {
        let mut state = template.clone();
        let mut frames = 0;
        while frames < 200 {
            evs.clear();
            let t = Instant::now(); system_move(&mut state, &cfg, &mut evs); t_move += t.elapsed().as_nanos();
            let t = Instant::now(); system_shield_give(&mut state, &cfg, &mut evs); t_shield += t.elapsed().as_nanos();
            let t = Instant::now(); system_breach(&mut state, &cfg, &mut evs); t_breach += t.elapsed().as_nanos();
            let t = Instant::now(); system_self_destruct(&mut state, &cfg, &mut evs); t_sd += t.elapsed().as_nanos();
            let t = Instant::now(); system_attack(&mut state, &cfg, &mut evs); t_att += t.elapsed().as_nanos();
            let t = Instant::now(); clear_destroyed(&mut state, &mut evs); t_cd += t.elapsed().as_nanos();
            let t = Instant::now(); system_remove_own_unit(&mut state, &cfg, &mut evs); t_ro += t.elapsed().as_nanos();
            frames += 1;
            if state.p1.hp <= 0.0 || state.p2.hp <= 0.0 { break; }
            if state.mobiles.is_empty() { break; }
        }
        total_frames += frames;
    }
    println!("system                   avg µs/run");
    println!("-------------------------------------");
    println!("move                    {:>10.2}", t_move as f64 / N as f64 / 1000.0);
    println!("shield_give             {:>10.2}", t_shield as f64 / N as f64 / 1000.0);
    println!("breach                  {:>10.2}", t_breach as f64 / N as f64 / 1000.0);
    println!("self_destruct           {:>10.2}", t_sd as f64 / N as f64 / 1000.0);
    println!("attack                  {:>10.2}", t_att as f64 / N as f64 / 1000.0);
    println!("clear_destroyed         {:>10.2}", t_cd as f64 / N as f64 / 1000.0);
    println!("remove_own_unit         {:>10.2}", t_ro as f64 / N as f64 / 1000.0);
    println!("avg frames/sim: {:.1}", total_frames as f64 / N as f64);
}
