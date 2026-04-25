//! Sub-system profiler: measure pathfinder hot paths.

use std::{fs, path::PathBuf, time::Instant};

use serde_json::Value;

use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::pathfinder::{edge_direction, PathFinder};
use sim_rs::map::{edge_tiles_slice, EDGE_TOP_RIGHT};
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::system_move;

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
    let _cfg = SimConfig::load(&snapshot_path()).unwrap();
    let template = build_state_fixture();

    const N: u32 = 5000;
    let walls: Vec<(i32, i32)> = template.structures.keys().copied().collect();
    let perfects = edge_tiles_slice(EDGE_TOP_RIGHT);

    // Time just PathFinder::new for the real fixture walls.
    let t = Instant::now();
    for _ in 0..N {
        let pf = PathFinder::new(28, edge_direction(EDGE_TOP_RIGHT), &walls, perfects);
        std::hint::black_box(&pf);
    }
    let ns = t.elapsed().as_nanos();
    println!("PathFinder::new: {:.2} µs/call", ns as f64 / N as f64 / 1000.0);

    // Time first get_step (triggers validate).
    let t = Instant::now();
    let mut pf = PathFinder::new(28, edge_direction(EDGE_TOP_RIGHT), &walls, perfects);
    let first_new = t.elapsed().as_nanos();
    let t = Instant::now();
    for _ in 0..N {
        let step = pf.get_step(13, 0, 0);
        std::hint::black_box(step);
    }
    let ns = t.elapsed().as_nanos();
    println!("1st PathFinder::new took {:.2} µs", first_new as f64 / 1000.0);
    println!("cached get_step (same unit xy, fresh pf):   {:.2} µs/call", ns as f64 / N as f64 / 1000.0);

    // Time full system_move (incl ensure_pathfinders).
    let t = Instant::now();
    for _ in 0..N {
        let mut state = template.clone();
        let mut evs: Vec<EventEntry> = Vec::with_capacity(32);
        system_move(&mut state, &_cfg, &mut evs);
        std::hint::black_box(&state);
    }
    let ns = t.elapsed().as_nanos();
    println!("system_move (first frame after clone):     {:.2} µs/call", ns as f64 / N as f64 / 1000.0);

    // Time SimState::clone itself.
    let t = Instant::now();
    for _ in 0..N {
        let s = template.clone();
        std::hint::black_box(&s);
    }
    let ns = t.elapsed().as_nanos();
    println!("SimState::clone:                           {:.2} µs/call", ns as f64 / N as f64 / 1000.0);
}
