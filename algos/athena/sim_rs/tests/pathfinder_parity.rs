//! PathFinder parity tests.
//!
//! Each scenario constructs an equivalent Rust `PathFinder` and (optionally)
//! shells out to Python to build the identical scenario, comparing the
//! returned step tile byte-for-byte.
//!
//! Hard-coded expected values come from running the Python port directly
//! (see `scripts/gen_pathfinder_parity.py` semantics — executed inline here
//! to avoid adding yet another script). If Python is unavailable the test
//! still asserts against the hard-coded expected values so CI stays green
//! on minimal environments.

use std::process::Command;

use sim_rs::pathfinder::{
    edge_direction, PathFinder, EDGE_BOTTOM_LEFT, EDGE_TOP_RIGHT, HORIZONTAL, SPAWNED, VERTICAL,
};

// Copy of algos/athena/sim/map.py::edge_tiles for test isolation.
fn edge_tiles(edge: i32) -> Vec<(i32, i32)> {
    let mut out = Vec::with_capacity(14);
    match edge {
        0 => {
            for y in 14..28 {
                out.push((41 - y, y));
            }
        }
        1 => {
            for y in 14..28 {
                out.push((y - 14, y));
            }
        }
        2 => {
            for y in 0..14 {
                out.push((13 - y, y));
            }
        }
        3 => {
            for y in 0..14 {
                out.push((y + 14, y));
            }
        }
        _ => unreachable!(),
    }
    out
}

/// Build a PathFinder + return the step coordinate for a single (unit, prev) query.
fn rust_single_step(
    edge: i32,
    walls: &[(i32, i32)],
    unit: (i32, i32),
    prev: u8,
) -> (i32, i32) {
    let perf = edge_tiles(edge);
    let mut pf = PathFinder::new(28, edge_direction(edge), walls, &perf);
    pf.get_step(unit.0, unit.1, prev)
}

/// Shell out to the Python port and return the same scenario's step tuple,
/// or `None` if the interpreter / module isn't available.
fn python_single_step(
    edge: i32,
    walls: &[(i32, i32)],
    unit: (i32, i32),
    prev: u8,
) -> Option<(i32, i32)> {
    let walls_lit: String = format!(
        "[{}]",
        walls
            .iter()
            .map(|(x, y)| format!("({},{})", x, y))
            .collect::<Vec<_>>()
            .join(",")
    );
    let code = format!(
        "from algos.athena.sim.pathfinder import PathFinder, EDGE_DIRECTION\n\
         from algos.athena.sim.map import edge_tiles\n\
         pf = PathFinder(28, EDGE_DIRECTION[{edge}], {walls_lit}, edge_tiles({edge}))\n\
         x, y = pf.get_step({ux}, {uy}, {prev})\n\
         print(f'{{x}} {{y}}')\n",
        edge = edge,
        walls_lit = walls_lit,
        ux = unit.0,
        uy = unit.1,
        prev = prev,
    );
    // Repo root: 3 levels above this file (sim_rs/tests/..).
    let manifest_dir = env!("CARGO_MANIFEST_DIR");
    let repo_root = std::path::Path::new(manifest_dir)
        .ancestors()
        .nth(3)
        .unwrap();
    let out = Command::new("python3")
        .arg("-c")
        .arg(&code)
        .current_dir(repo_root)
        .output()
        .ok()?;
    if !out.status.success() {
        return None;
    }
    let s = String::from_utf8(out.stdout).ok()?;
    let parts: Vec<i32> = s
        .split_whitespace()
        .filter_map(|p| p.parse::<i32>().ok())
        .collect();
    if parts.len() != 2 {
        return None;
    }
    Some((parts[0], parts[1]))
}

fn assert_parity(
    label: &str,
    edge: i32,
    walls: &[(i32, i32)],
    unit: (i32, i32),
    prev: u8,
    expected: (i32, i32),
) {
    let rust = rust_single_step(edge, walls, unit, prev);
    assert_eq!(
        rust, expected,
        "[{label}] rust={rust:?} expected={expected:?}"
    );
    if let Some(py) = python_single_step(edge, walls, unit, prev) {
        assert_eq!(
            py, expected,
            "[{label}] python={py:?} disagrees with hard-coded expected={expected:?}"
        );
    }
}

#[test]
fn s1_empty_board_tr_edge_center_spawned() {
    assert_parity("S1", EDGE_TOP_RIGHT, &[], (13, 12), SPAWNED, (13, 13));
}

#[test]
fn s2_empty_board_bl_edge_spawned() {
    assert_parity("S2", EDGE_BOTTOM_LEFT, &[], (14, 15), SPAWNED, (14, 14));
}

#[test]
fn s3_single_wall_does_not_block_center() {
    assert_parity(
        "S3",
        EDGE_TOP_RIGHT,
        &[(14, 13)],
        (13, 12),
        SPAWNED,
        (13, 13),
    );
}

#[test]
fn s4_unit_on_target_edge_stays_put() {
    assert_parity("S4", EDGE_TOP_RIGHT, &[], (14, 27), HORIZONTAL, (14, 27));
}

#[test]
fn s5_wall_maze_sequence_tracks() {
    let walls: Vec<(i32, i32)> = vec![(14, 13), (15, 13), (15, 14), (15, 15)];
    // Step A: at (13,12) SPAWNED.
    assert_parity("S5", EDGE_TOP_RIGHT, &walls, (13, 12), SPAWNED, (13, 13));
    // Step B: at (13,13) prev=VERTICAL (just moved vertically).
    assert_parity("S5b", EDGE_TOP_RIGHT, &walls, (13, 13), VERTICAL, (13, 14));
    // Step C: at (13,14) prev=HORIZONTAL.
    assert_parity("S5c", EDGE_TOP_RIGHT, &walls, (13, 14), HORIZONTAL, (13, 15));
}

#[test]
fn s6_bl_edge_with_diag_wall() {
    // From (13,13) with a wall at (12,12), heading BL with SPAWNED last-move
    // the filter pipeline prefers vertical first (SPAWNED drops stepY==unitY),
    // yielding (13,12).
    assert_parity(
        "S6",
        EDGE_BOTTOM_LEFT,
        &[(12, 12)],
        (13, 13),
        SPAWNED,
        (13, 12),
    );
}

#[test]
fn s7_prev_direction_swaps_tiebreak() {
    // Same unit/edge; prev=HORIZONTAL → prefer vertical step (13,13).
    // prev=VERTICAL → prefer horizontal step (14,12).
    assert_parity("S7h", EDGE_TOP_RIGHT, &[], (13, 12), HORIZONTAL, (13, 13));
    assert_parity("S7v", EDGE_TOP_RIGHT, &[], (13, 12), VERTICAL, (14, 12));
}

#[test]
fn s8_put_remove_restores_clean_state() {
    // Mutating state mid-test: put a wall, query, remove, query again.
    // Expected results come from running the Python port with the identical
    // mutation sequence — the global-invalidation sweep inside `remove`
    // must leave the pathfinder consistent.
    let perf = edge_tiles(EDGE_TOP_RIGHT);
    let mut pf = PathFinder::new(28, edge_direction(EDGE_TOP_RIGHT), &[], &perf);
    pf.put(14, 14);
    let during = pf.get_step(13, 12, SPAWNED);
    pf.remove(14, 14);
    let after = pf.get_step(13, 12, SPAWNED);
    assert_eq!(during, (13, 13));
    assert_eq!(after, (13, 13));

    // Cross-validate the mutation sequence end-to-end against Python.
    let manifest_dir = env!("CARGO_MANIFEST_DIR");
    let repo_root = std::path::Path::new(manifest_dir)
        .ancestors()
        .nth(3)
        .unwrap();
    let code = "from algos.athena.sim.pathfinder import PathFinder, EDGE_DIRECTION\n\
                from algos.athena.sim.map import edge_tiles\n\
                pf = PathFinder(28, EDGE_DIRECTION[0], [], edge_tiles(0))\n\
                pf.put(14,14)\n\
                a = pf.get_step(13,12,0)\n\
                pf.remove(14,14)\n\
                b = pf.get_step(13,12,0)\n\
                print(f'{a[0]} {a[1]} {b[0]} {b[1]}')\n";
    if let Ok(out) = Command::new("python3")
        .arg("-c")
        .arg(code)
        .current_dir(repo_root)
        .output()
    {
        if out.status.success() {
            let s = String::from_utf8(out.stdout).unwrap();
            let parts: Vec<i32> = s
                .split_whitespace()
                .filter_map(|p| p.parse::<i32>().ok())
                .collect();
            assert_eq!(parts.len(), 4, "python output = {s:?}");
            assert_eq!((parts[0], parts[1]), during);
            assert_eq!((parts[2], parts[3]), after);
        }
    }
}
