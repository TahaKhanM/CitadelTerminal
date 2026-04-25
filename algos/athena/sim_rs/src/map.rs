//! Diamond map geometry — mirror `algos/athena/sim/map.py`.
//!
//! Engine citation: `MapBounds.java:9-70`. Arena = 28×28 diamond; edges enumerate
//! BOTTOM_LEFT (y+x==13, y∈[0,13]), BOTTOM_RIGHT (x-y==14, y∈[0,13]), and the
//! two top mirrors. Half split at y=14.

pub const ARENA: i32 = 28;
pub const HALF: i32 = 14;

pub const EDGE_TOP_RIGHT: i32 = 0;
pub const EDGE_TOP_LEFT: i32 = 1;
pub const EDGE_BOTTOM_LEFT: i32 = 2;
pub const EDGE_BOTTOM_RIGHT: i32 = 3;

/// Diamond-bounds check — matches `MapBounds.java:54-70` scanline fill.
pub fn on_diamond(x: i32, y: i32) -> bool {
    if !(0..ARENA).contains(&x) || !(0..ARENA).contains(&y) {
        return false;
    }
    if y < HALF {
        // Bottom half: x ∈ [13 - y, 14 + y].
        x >= 13 - y && x <= 14 + y
    } else {
        // Top half: x ∈ [y - 14, 41 - y].
        x >= y - HALF && x <= 41 - y
    }
}

/// Static per-edge tile tables. `MapBounds.java:30-43` order — BL iterates
/// `(13-i, i)` for i ∈ [0,13]; TR / TL / BR are the three mirrors. Computed
/// once at const-eval time so per-frame callers can borrow `&'static [...]`
/// instead of allocating a fresh `Vec`.
pub const EDGE_TILES_BOTTOM_LEFT: [(i32, i32); 14] = {
    let mut out = [(0, 0); 14];
    let mut i = 0;
    while i < 14 {
        out[i as usize] = (13 - i, i);
        i += 1;
    }
    out
};
pub const EDGE_TILES_BOTTOM_RIGHT: [(i32, i32); 14] = {
    let mut out = [(0, 0); 14];
    let mut i = 0;
    while i < 14 {
        out[i as usize] = (14 + i, i);
        i += 1;
    }
    out
};
pub const EDGE_TILES_TOP_LEFT: [(i32, i32); 14] = {
    let mut out = [(0, 0); 14];
    let mut i = 0;
    while i < 14 {
        out[i as usize] = (i, 14 + i);
        i += 1;
    }
    out
};
pub const EDGE_TILES_TOP_RIGHT: [(i32, i32); 14] = {
    let mut out = [(0, 0); 14];
    let mut i = 0;
    while i < 14 {
        out[i as usize] = (27 - i, 14 + i);
        i += 1;
    }
    out
};

/// Borrow the precomputed edge-tile table. Zero-alloc, returns a `'static`
/// slice. Semantics match the original `edge_tiles()` signature but without
/// the per-call `Vec` allocation.
#[inline]
pub fn edge_tiles_slice(edge: i32) -> &'static [(i32, i32)] {
    match edge {
        EDGE_BOTTOM_LEFT => &EDGE_TILES_BOTTOM_LEFT,
        EDGE_BOTTOM_RIGHT => &EDGE_TILES_BOTTOM_RIGHT,
        EDGE_TOP_LEFT => &EDGE_TILES_TOP_LEFT,
        EDGE_TOP_RIGHT => &EDGE_TILES_TOP_RIGHT,
        _ => unreachable!(),
    }
}

/// Legacy `Vec`-returning shim — kept for any external callers that expected
/// an owned Vec (tests, benches). Prefer `edge_tiles_slice` in hot paths.
pub fn edge_tiles(edge: i32) -> Vec<(i32, i32)> {
    edge_tiles_slice(edge).to_vec()
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test] fn bottom_row_only_center() {
        assert!(on_diamond(13, 0));
        assert!(on_diamond(14, 0));
        assert!(!on_diamond(12, 0));
        assert!(!on_diamond(15, 0));
    }
    #[test] fn top_row_only_center() {
        assert!(on_diamond(13, 27));
        assert!(on_diamond(14, 27));
        assert!(!on_diamond(12, 27));
    }
    #[test] fn bottom_left_edge_count() {
        assert_eq!(edge_tiles(EDGE_BOTTOM_LEFT).len(), 14);
    }
}
