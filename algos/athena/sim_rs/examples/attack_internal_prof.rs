//! Drill-down stats on system_attack's hot path — derives per-frame
//! counts of turret fires, bbox skips, and avg walker-/struct-cand
//! sizes from the post-attack scratch state. Paired with attackprof's
//! per-sim timing numbers to attribute the 65-µs/sim attack cost to
//! specific inner-loop counters.

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
    const N: u32 = 10000;

    let mut total_turret_infos: u64 = 0;
    let mut total_turret_fires: u64 = 0;
    let mut total_turret_skipped_bbox: u64 = 0;
    let mut total_walker_cand_count: u64 = 0;
    let mut total_struct_cand_count: u64 = 0;
    let mut total_frames: u64 = 0;
    let mut total_scratch_rebuilds: u64 = 0;

    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);

    let t_start = Instant::now();
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
            let dirty = s.scratch.structures_dirty;
            if dirty { total_scratch_rebuilds += 1; }
            system_attack(&mut s, &cfg, &mut evs);

            // Derive counters from post-attack scratch state.
            let (p1_bbox, p2_bbox) = (s.scratch.p1_mob_bbox, s.scratch.p2_mob_bbox);
            let (p1_any, p2_any) = (!s.scratch.p1_live_mobs.is_empty(), !s.scratch.p2_live_mobs.is_empty());
            total_turret_infos += s.scratch.turret_infos.len() as u64;
            for ti in s.scratch.turret_infos.iter() {
                let has_struct_cands = ti.enemy_cand_end > ti.enemy_cand_start;
                let (enemy_any, bbox) = if ti.player == 1 { (p2_any, p2_bbox) } else { (p1_any, p1_bbox) };
                let mob_near = enemy_any
                    && ti.xy.0 >= bbox.0 - ti.range_bound
                    && ti.xy.0 <= bbox.1 + ti.range_bound
                    && ti.xy.1 >= bbox.2 - ti.range_bound
                    && ti.xy.1 <= bbox.3 + ti.range_bound;
                if !mob_near && !has_struct_cands {
                    total_turret_skipped_bbox += 1;
                } else {
                    total_turret_fires += 1;
                    if mob_near {
                        let enemy_mobs = if ti.player == 1 { &s.scratch.p2_live_mobs } else { &s.scratch.p1_live_mobs };
                        total_walker_cand_count += enemy_mobs.len() as u64;
                    }
                    total_struct_cand_count += (ti.enemy_cand_end - ti.enemy_cand_start) as u64;
                }
            }

            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, &cfg, &mut evs);
            total_frames += 1;
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { break; }
            if s.mobiles.is_empty() { break; }
            f += 1;
        }
    }
    let wall_ns = t_start.elapsed().as_nanos();
    let wall_us_per_sim = wall_ns as f64 / N as f64 / 1000.0;
    let frames_per_sim = total_frames as f64 / N as f64;

    println!("=== attack internals across {} sims ===", N);
    println!("wall                              : {:>7.2} µs/sim (instrumented overhead included)", wall_us_per_sim);
    println!("frames/sim                        : {:>7.2}", frames_per_sim);
    println!("turret_infos/sim (total over frames): {:>7.2}", total_turret_infos as f64 / N as f64);
    println!("turret_infos/frame                : {:>7.2}", total_turret_infos as f64 / total_frames as f64);
    println!("scratch rebuilds/sim              : {:>7.2}", total_scratch_rebuilds as f64 / N as f64);
    println!("turret fires (bbox-hit or struct) : {:>7.2} / sim ({:.2} / frame)",
             total_turret_fires as f64 / N as f64,
             total_turret_fires as f64 / total_frames as f64);
    println!("turret skipped (bbox + no struct) : {:>7.2} / sim ({:.2} / frame)",
             total_turret_skipped_bbox as f64 / N as f64,
             total_turret_skipped_bbox as f64 / total_frames as f64);
    println!("avg enemy-mob cands upper bound   : {:>7.3}", total_walker_cand_count as f64 / total_turret_fires.max(1) as f64);
    println!("avg per-turret enemy_cand slice   : {:>7.3}", total_struct_cand_count as f64 / total_turret_fires.max(1) as f64);
    println!();
    println!("skip rate bbox                    : {:>6.1}%", total_turret_skipped_bbox as f64 / total_turret_infos as f64 * 100.0);
}
