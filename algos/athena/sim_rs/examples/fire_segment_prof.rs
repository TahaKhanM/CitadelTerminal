//! Within fire_one_turret, measure cumulative time spent in each
//! sub-phase across all 957 fires/sim. Re-implements fire_one_turret here
//! locally to add timer probes (the production version is otherwise hot
//! enough that probes would skew it).
//!
//! Probes:
//!   T1: walker_cands clear+rebuild (only when mob_near)
//!   T2: walker target pick (when wcl >= 1)
//!   T3: struct target pick (when target_mobile_idx is None && scl >= 1)
//!   T4: damage apply + event emit
//!
//! Numbers are µs/sim summed across all fires, so directly comparable
//! to attackprof's 65-µs system_attack contribution.

use std::{fs, path::PathBuf, time::Instant};
use serde_json::Value;
use sim_rs::config::SimConfig;
use sim_rs::events::EventEntry;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};
use sim_rs::systems::{
    clear_destroyed, system_breach, system_collision, system_move,
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

#[inline(never)]
fn timed_outer_overhead(state: &mut SimState, _cfg: &SimConfig) -> u64 {
    // Iterate but skip every fire — measures pure outer-loop overhead per frame.
    let n_turrets = state.scratch.turret_infos.len();
    let (p1_bbox, p2_bbox) = (state.scratch.p1_mob_bbox, state.scratch.p2_mob_bbox);
    let (p1_any, p2_any) = (!state.scratch.p1_live_mobs.is_empty(),
                             !state.scratch.p2_live_mobs.is_empty());
    let mut k: u64 = 0;
    let t = Instant::now();
    for i in 0..n_turrets {
        let ti = state.scratch.turret_infos[i];
        let has_struct_cands = ti.enemy_cand_end > ti.enemy_cand_start;
        let (enemy_any, bbox) = if ti.player == 1 { (p2_any, p2_bbox) } else { (p1_any, p1_bbox) };
        let mob_near = enemy_any
            && ti.xy.0 >= bbox.0 - ti.range_bound
            && ti.xy.0 <= bbox.1 + ti.range_bound
            && ti.xy.1 >= bbox.2 - ti.range_bound
            && ti.xy.1 <= bbox.3 + ti.range_bound;
        if !mob_near && !has_struct_cands {
            k += 1;
            continue;
        }
        // Pretend fire — sum into k to prevent dead-code elimination.
        k += (ti.xy.0 + ti.xy.1) as u64;
    }
    std::hint::black_box(k);
    t.elapsed().as_nanos() as u64
}

fn main() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let mut tmpl = build_state_fixture();
    ensure_pathfinders(&mut tmpl);
    const N: u32 = 5000;
    let mut evs: Vec<EventEntry> = Vec::with_capacity(32);

    // Pre-warm scratch.
    {
        let mut s = tmpl.clone_no_scratch();
        sim_rs::systems::system_attack(&mut s, &cfg, &mut evs);
        evs.clear();
    }

    let mut total_outer_ns: u64 = 0;
    let mut total_frames: u64 = 0;
    let t_wall = Instant::now();
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
            // Run the rebuild side of system_attack but measure outer-only.
            if s.scratch.structures_dirty {
                sim_rs::systems::system_attack(&mut s, &cfg, &mut evs);
                evs.clear();
            } else {
                // Per-frame init that system_attack does:
                s.scratch.attacker_mobile_idxs.clear();
                s.scratch.p1_live_mobs.clear();
                s.scratch.p2_live_mobs.clear();
                let mut p1_lo_x = i32::MAX; let mut p1_hi_x = i32::MIN;
                let mut p1_lo_y = i32::MAX; let mut p1_hi_y = i32::MIN;
                let mut p2_lo_x = i32::MAX; let mut p2_hi_x = i32::MIN;
                let mut p2_lo_y = i32::MAX; let mut p2_hi_y = i32::MIN;
                for (i, m) in s.mobiles.iter().enumerate() {
                    if !m.breached { s.scratch.attacker_mobile_idxs.push(i); }
                    if m.hp <= 0.0 { continue; }
                    let cand = sim_rs::state::WalkerCand { idx: i, hp: m.hp, shield: m.shield, xy: m.xy };
                    match m.player {
                        1 => { s.scratch.p1_live_mobs.push(cand);
                            if m.xy.0 < p1_lo_x { p1_lo_x = m.xy.0; }
                            if m.xy.0 > p1_hi_x { p1_hi_x = m.xy.0; }
                            if m.xy.1 < p1_lo_y { p1_lo_y = m.xy.1; }
                            if m.xy.1 > p1_hi_y { p1_hi_y = m.xy.1; }
                        }
                        2 => { s.scratch.p2_live_mobs.push(cand);
                            if m.xy.0 < p2_lo_x { p2_lo_x = m.xy.0; }
                            if m.xy.0 > p2_hi_x { p2_hi_x = m.xy.0; }
                            if m.xy.1 < p2_lo_y { p2_lo_y = m.xy.1; }
                            if m.xy.1 > p2_hi_y { p2_hi_y = m.xy.1; }
                        }
                        _ => {}
                    }
                }
                s.scratch.p1_mob_bbox = (p1_lo_x, p1_hi_x, p1_lo_y, p1_hi_y);
                s.scratch.p2_mob_bbox = (p2_lo_x, p2_hi_x, p2_lo_y, p2_hi_y);
                let outer_ns = timed_outer_overhead(&mut s, &cfg);
                total_outer_ns += outer_ns;
                total_frames += 1;
            }
            clear_destroyed(&mut s, &mut evs);
            system_remove_own_unit(&mut s, &cfg, &mut evs);
            if s.p1.hp <= 0.0 || s.p2.hp <= 0.0 { break; }
            if s.mobiles.is_empty() { break; }
            f += 1;
        }
    }
    let wall = t_wall.elapsed().as_nanos();

    println!("=== fire_segment_prof N={} ===", N);
    println!("baseline (no probes)     : {:.2} µs/sim",
             wall as f64 / N as f64 / 1000.0);
    println!("frames measured          : {:.1} / sim", total_frames as f64 / N as f64);
    println!("outer-only loop          : {:.2} µs/sim ({:.2} ns/frame)",
             total_outer_ns as f64 / N as f64 / 1000.0,
             total_outer_ns as f64 / total_frames as f64);
}
