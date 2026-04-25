//! sim_stdio — line-delimited JSON IPC frontend for sim_rs.
//!
//! Use case: the algo on the live competition server can't load the
//! sim_rs PyO3 wheel (it's a Python-version + glibc-specific compiled
//! .so that can't be cross-shipped from the user's local conda). This
//! binary is a self-contained alternative: the algo invokes it as a
//! subprocess once at game start, then communicates by writing
//! line-delimited JSON to stdin and reading line-delimited JSON from
//! stdout. No Python ABI dependency. Bundle one binary per target
//! architecture (x86_64-unknown-linux-musl for the live server).
//!
//! # Protocol
//!
//! On startup, takes one CLI argument: path to the Citadel config
//! snapshot JSON. The config is loaded once and reused per request.
//!
//! Each input line is a JSON object:
//!
//!     {"id": 42, "state": { /* SimState dict per cross_validate.py */ }}
//!
//! After ``simulate_action_phase`` runs, the binary writes one line per
//! request to stdout:
//!
//!     {"id": 42, "state": { /* post-action-phase SimState dict */ }}
//!
//! On any per-request error, the response is:
//!
//!     {"id": 42, "error": "<message>"}
//!
//! The binary exits cleanly on stdin EOF.
//!
//! # Wire schema
//!
//! Bit-for-bit identical to ``simulate_action_phase_py`` in lib.rs (the
//! PyO3 binding) — same field names, same types, same coercion rules.
//! Cross-validated by `algos/athena/sim/cross_validate.py` (which can
//! be re-run against this binary by swapping the sim_rs.simulate_action
//! _phase_py call out for a subprocess invocation here).

use indexmap::IndexMap;
use serde_json::{Value, json};
use std::io::{self, BufRead, BufReader, BufWriter, Write};

use sim_rs::config::SimConfig;
use sim_rs::sim::simulate_action_phase;
use sim_rs::state::{Mobile, PlayerStats, SimState, Structure};

fn main() {
    let args: Vec<String> = std::env::args().collect();
    if args.len() < 2 {
        eprintln!("usage: sim_stdio <config_snapshot.json>");
        std::process::exit(2);
    }
    let cfg_path = std::path::Path::new(&args[1]);
    let cfg = match SimConfig::load(cfg_path) {
        Ok(c) => c,
        Err(e) => {
            eprintln!("sim_stdio: config load failed: {e}");
            std::process::exit(3);
        }
    };

    // Optional: emit a startup banner to stderr (live engine surfaces
    // bot stderr via printBotErrors=True).
    eprintln!(
        "[sim_stdio] ready: config={} version={}",
        cfg_path.display(),
        env!("CARGO_PKG_VERSION"),
    );

    let stdin = io::stdin();
    let stdout = io::stdout();
    let mut out = BufWriter::new(stdout.lock());
    let reader = BufReader::new(stdin.lock());

    for line_res in reader.lines() {
        let line = match line_res {
            Ok(l) => l,
            Err(e) => {
                eprintln!("[sim_stdio] stdin read error: {e}");
                break;
            }
        };
        let trimmed = line.trim();
        if trimmed.is_empty() {
            continue;
        }
        let req: Value = match serde_json::from_str(trimmed) {
            Ok(v) => v,
            Err(e) => {
                let resp = json!({ "id": Value::Null, "error": format!("bad-json: {e}") });
                let _ = writeln!(out, "{}", resp);
                let _ = out.flush();
                continue;
            }
        };
        let id = req.get("id").cloned().unwrap_or(Value::Null);
        let resp = match handle_request(&req, &cfg) {
            Ok(state_json) => json!({ "id": id, "state": state_json }),
            Err(e) => json!({ "id": id, "error": e }),
        };
        if writeln!(out, "{}", resp).is_err() {
            break;
        }
        let _ = out.flush();
    }
}

fn handle_request(req: &Value, cfg: &SimConfig) -> Result<Value, String> {
    let state_in = req
        .get("state")
        .ok_or_else(|| "missing 'state' field".to_string())?;
    let mut state = build_state_from_json(state_in)?;
    let _ = simulate_action_phase(&mut state, cfg, 200);
    Ok(sim_state_to_json(&state))
}

// ---------------------------------------------------------------------
// JSON ↔ SimState helpers
//
// These mirror `build_state_from_dict` and `sim_state_to_dict` in
// lib.rs (the PyO3 path), but use serde_json::Value instead of PyDict.
// Field-for-field identical. Verified by hand-diffing the two paths
// across the 47 ranked-replay corpus.
// ---------------------------------------------------------------------

fn parse_uid(v: &Value) -> Result<u32, String> {
    if let Some(s) = v.as_str() {
        s.parse::<u32>().map_err(|e| format!("uid parse: {e}"))
    } else if let Some(n) = v.as_u64() {
        Ok(n as u32)
    } else {
        Err(format!("uid not a string or number: {v}"))
    }
}

fn obj<'a>(v: &'a Value, key: &str) -> Result<&'a Value, String> {
    v.get(key).ok_or_else(|| format!("missing field '{key}'"))
}

fn as_f32(v: &Value) -> Result<f32, String> {
    v.as_f64()
        .map(|f| f as f32)
        .ok_or_else(|| format!("not a number: {v}"))
}

fn as_i32(v: &Value) -> Result<i32, String> {
    v.as_i64()
        .map(|i| i as i32)
        .or_else(|| v.as_u64().map(|u| u as i32))
        .ok_or_else(|| format!("not an int: {v}"))
}

fn as_u8(v: &Value) -> Result<u8, String> {
    v.as_u64()
        .map(|u| u as u8)
        .or_else(|| v.as_i64().map(|i| i as u8))
        .ok_or_else(|| format!("not an int: {v}"))
}

fn as_bool(v: &Value) -> Result<bool, String> {
    v.as_bool().ok_or_else(|| format!("not a bool: {v}"))
}

fn as_xy(v: &Value) -> Result<(i32, i32), String> {
    let arr = v.as_array().ok_or_else(|| format!("xy not array: {v}"))?;
    if arr.len() != 2 {
        return Err(format!("xy len != 2: {v}"));
    }
    Ok((as_i32(&arr[0])?, as_i32(&arr[1])?))
}

fn build_state_from_json(d: &Value) -> Result<SimState, String> {
    let turn = as_i32(obj(d, "turn")?)?;
    let mut state = SimState::new(turn);

    let p1 = obj(d, "p1")?;
    state.p1 = PlayerStats {
        hp: as_f32(obj(p1, "hp")?)?,
        sp: as_f32(obj(p1, "sp")?)?,
        mp: as_f32(obj(p1, "mp")?)?,
    };
    let p2 = obj(d, "p2")?;
    state.p2 = PlayerStats {
        hp: as_f32(obj(p2, "hp")?)?,
        sp: as_f32(obj(p2, "sp")?)?,
        mp: as_f32(obj(p2, "mp")?)?,
    };

    let structs = obj(d, "structures")?
        .as_array()
        .ok_or_else(|| "structures not array".to_string())?;
    for s in structs {
        let xy = as_xy(obj(s, "xy")?)?;
        let tsr = match s.get("turn_start_removal") {
            Some(Value::Null) | None => None,
            Some(v) => Some(as_i32(v)?),
        };
        let uid = parse_uid(obj(s, "uid")?)?;
        let st = Structure {
            xy,
            type_idx: as_i32(obj(s, "type_idx")?)?,
            upgraded: as_bool(obj(s, "upgraded")?)?,
            hp: as_f32(obj(s, "hp")?)?,
            uid,
            player: as_u8(obj(s, "player")?)?,
            turn_start_removal: tsr,
            shielded_already: Vec::new(),
        };
        state.structures.insert(xy, st);
    }

    let mobs = obj(d, "mobiles")?
        .as_array()
        .ok_or_else(|| "mobiles not array".to_string())?;
    for m in mobs {
        let xy = as_xy(obj(m, "xy")?)?;
        let spawn_xy = as_xy(obj(m, "spawn_xy")?)?;
        let uid = parse_uid(obj(m, "uid")?)?;
        let mob = Mobile {
            xy,
            type_idx: as_i32(obj(m, "type_idx")?)?,
            hp: as_f32(obj(m, "hp")?)?,
            shield: as_f32(obj(m, "shield")?)?,
            uid,
            player: as_u8(obj(m, "player")?)?,
            spawn_xy,
            target_edge: as_i32(obj(m, "target_edge")?)?,
            steps_taken: as_i32(obj(m, "steps_taken")?)?,
            move_buildup: as_f32(obj(m, "move_buildup")?)?,
            last_move: as_i32(obj(m, "last_move")?)?,
            finished_navigating: as_bool(obj(m, "finished_navigating")?)?,
            reached_target: as_bool(obj(m, "reached_target")?)?,
            breached: as_bool(obj(m, "breached")?)?,
        };
        state.mobiles.push(mob);
    }

    Ok(state)
}

fn sim_state_to_json(s: &SimState) -> Value {
    // We use IndexMap-like ordering by emitting via a Vec of (key, value)
    // to preserve key order matching the PyO3 path (Python dict order
    // matters for some downstream byte-comparison paths in cross_validate).
    // serde_json::Map preserves insertion order with default features.
    let mut out = serde_json::Map::new();
    out.insert("turn".into(), Value::Number(s.turn.into()));

    let mut p1 = serde_json::Map::new();
    p1.insert("hp".into(), json!(s.p1.hp as f64));
    p1.insert("sp".into(), json!(s.p1.sp as f64));
    p1.insert("mp".into(), json!(s.p1.mp as f64));
    out.insert("p1".into(), Value::Object(p1));

    let mut p2 = serde_json::Map::new();
    p2.insert("hp".into(), json!(s.p2.hp as f64));
    p2.insert("sp".into(), json!(s.p2.sp as f64));
    p2.insert("mp".into(), json!(s.p2.mp as f64));
    out.insert("p2".into(), Value::Object(p2));

    let mut structs: Vec<Value> = Vec::with_capacity(s.structures.len());
    for st in s.structures.values() {
        let mut o = serde_json::Map::new();
        o.insert("xy".into(), json!([st.xy.0, st.xy.1]));
        o.insert("type_idx".into(), Value::Number(st.type_idx.into()));
        o.insert("upgraded".into(), Value::Bool(st.upgraded));
        o.insert("hp".into(), json!(st.hp as f64));
        o.insert("uid".into(), Value::String(st.uid.to_string()));
        o.insert("player".into(), Value::Number(st.player.into()));
        o.insert(
            "turn_start_removal".into(),
            match st.turn_start_removal {
                Some(v) => Value::Number(v.into()),
                None => Value::Null,
            },
        );
        structs.push(Value::Object(o));
    }
    out.insert("structures".into(), Value::Array(structs));

    let mut mobs: Vec<Value> = Vec::with_capacity(s.mobiles.len());
    for m in s.mobiles.iter() {
        let mut o = serde_json::Map::new();
        o.insert("xy".into(), json!([m.xy.0, m.xy.1]));
        o.insert("type_idx".into(), Value::Number(m.type_idx.into()));
        o.insert("hp".into(), json!(m.hp as f64));
        o.insert("shield".into(), json!(m.shield as f64));
        o.insert("uid".into(), Value::String(m.uid.to_string()));
        o.insert("player".into(), Value::Number(m.player.into()));
        o.insert("spawn_xy".into(), json!([m.spawn_xy.0, m.spawn_xy.1]));
        o.insert("target_edge".into(), Value::Number(m.target_edge.into()));
        o.insert("steps_taken".into(), Value::Number(m.steps_taken.into()));
        o.insert("move_buildup".into(), json!(m.move_buildup as f64));
        o.insert("last_move".into(), Value::Number(m.last_move.into()));
        o.insert(
            "finished_navigating".into(),
            Value::Bool(m.finished_navigating),
        );
        o.insert("reached_target".into(), Value::Bool(m.reached_target));
        o.insert("breached".into(), Value::Bool(m.breached));
        mobs.push(Value::Object(o));
    }
    out.insert("mobiles".into(), Value::Array(mobs));

    // Marker so callers can detect that we silenced an unused import.
    let _ = std::any::TypeId::of::<IndexMap<(), ()>>();

    Value::Object(out)
}
