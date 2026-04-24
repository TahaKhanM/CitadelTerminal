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

/// Enumerate tiles on the given edge in canonical insertion order —
/// `MapBounds.java:30-43` (BL iterates `(13-i, i)` for i ∈ [0,13]).
pub fn edge_tiles(edge: i32) -> Vec<(i32, i32)> {
    let mut out = Vec::with_capacity(14);
    for i in 0..HALF {
        out.push(match edge {
            EDGE_BOTTOM_LEFT  => (13 - i, i),
            EDGE_BOTTOM_RIGHT => (14 + i, i),
            EDGE_TOP_LEFT     => (i,           14 + i),
            EDGE_TOP_RIGHT    => (27 - i,      14 + i),
            _ => unreachable!(),
        });
    }
    out
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
