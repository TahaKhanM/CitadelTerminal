//! ProximityArena — deterministic spatial index for mobiles + structures.
//!
//! Rust port of the scaffold in `algos/athena/sim/proximity.py`. The engine
//! uses a tile-bucketed `HashSet<ProximitySensor>` grid; we intentionally
//! replace that with an `IndexMap` to remove JVM-hash-order non-determinism
//! (the four HashSet-ordered buckets — shield, damage, death, selfDestruct —
//! are reconciled downstream via `event_canonical.py` / `events.rs` canonical
//! keys; see `SIM_PARITY.md` § CASCADE gate).
//!
//! # Engine citations
//! * `ProximityArena.java:14-53`      — tile-bucketed sensor storage; the
//!   `getCollisions` double-loop we replace with direct range queries.
//! * `ProximityArena.java:42-53`      — `Set<Pair<ProximitySensor>>` that
//!   drives `ColliderComponent.collidedWithThisTurn` ordering (JVM-internal,
//!   absorbed by canonical-key gate).
//! * `ProximitySensor.java`           — `isHitBy` + radius + identity mask.
//! * `ProximitySensorFactory.java:28-41` — `rasterize(radius)` — the GridMask
//!   construction we mirror in spirit via Euclidean distance filtering.
//! * `ProximityUtil.java`             — `combine` pair-enumeration helper.
//!
//! # Determinism contract
//! All iteration is **insertion-order** via `IndexMap`. Range queries filter
//! the full insertion sequence by Euclidean distance `<= sqrt(range_sq)` so
//! that the returned `Vec<_>` ordering matches `sim/state.py`'s dict-insertion
//! iteration — which is the order SimCore's validator expects on the four
//! strict-ordered buckets (attack, breach, move, spawn, melee).
//!
//! `std::collections::HashMap` is deliberately **not** used anywhere that
//! affects iteration — per the Phase 1.B.5 port contract.

use indexmap::IndexMap;

/// 28×28 grid of inserted mobiles + structures with deterministic
/// insertion-order iteration.
///
/// Engine citation: `ProximityArena.java:14-22` (`Grid<Set<ProximitySensor>>`
/// allCoords construction). We flatten the grid into two `IndexMap`s keyed by
/// uid (mobiles) / coordinate (structures); range queries iterate the full
/// insertion sequence — O(n) per query, but n ≤ ~50 mobiles + ≤ ~400 towers so
/// the constant factor wins vs. a tile-binned grid for SimCore's real-world
/// callsites (per-frame targeting + SD AOE).
#[derive(Debug, Clone)]
pub struct ProximityArena {
    /// `uid -> (x, y)`; insertion order preserved. Engine citation:
    /// `Game.gameObjects` ArrayList — we mirror its insertion-order semantics
    /// here so that `mobiles_in_range` iteration matches the engine's
    /// gameObjects scan used in strict-ordered buckets.
    mobiles: IndexMap<u32, (i32, i32)>,
    /// `(x, y) -> uid`; one structure per tile (engine's placement rule —
    /// `StructureSystem.java` rejects overlap). Insertion-order iteration for
    /// the structure-scan order.
    structures: IndexMap<(i32, i32), u32>,
}

impl Default for ProximityArena {
    fn default() -> Self {
        Self::new()
    }
}

impl ProximityArena {
    /// Construct an empty arena.
    ///
    /// Engine citation: `ProximityArena.java:17-22` — the allCoords init loop
    /// that populates every tile with an empty sensor set. We don't pre-seed
    /// tiles because the IndexMap grows lazily on first insert.
    pub fn new() -> Self {
        Self {
            mobiles: IndexMap::new(),
            structures: IndexMap::new(),
        }
    }

    /// Insert a mobile unit at `xy`. Duplicate uid overwrites the previous
    /// position (matches engine behaviour: a unit can only be at one tile at
    /// a time — `HealthComponent` update on the same `Gameobject`).
    ///
    /// Engine citation: `ProximityArena.java:24-31 addSensor` — we skip the
    /// GridMask rasterisation because range queries do the radius check at
    /// query time (O(n) scan) rather than at insert time.
    pub fn insert_mobile(&mut self, uid: u32, xy: (i32, i32)) {
        self.mobiles.insert(uid, xy);
    }

    /// Insert a structure at `xy`. One structure per tile; re-inserting
    /// overwrites (engine citation: `StructureSystem.java` placement check).
    ///
    /// Engine citation: `ProximityArena.java:24-31 addSensor`.
    pub fn insert_structure(&mut self, xy: (i32, i32), uid: u32) {
        self.structures.insert(xy, uid);
    }

    /// Remove a mobile by uid. No-op if absent. `IndexMap::shift_remove`
    /// preserves insertion order of surviving entries (critical for parity —
    /// `IndexMap::swap_remove` would corrupt the iteration contract).
    ///
    /// Engine citation: `ProximityArena.java:33-40 removeSensor`.
    pub fn remove_mobile(&mut self, uid: u32) {
        self.mobiles.shift_remove(&uid);
    }

    /// Remove a structure by coordinate. Shift-remove preserves surviving
    /// insertion order.
    ///
    /// Engine citation: `ProximityArena.java:33-40 removeSensor`.
    pub fn remove_structure(&mut self, xy: (i32, i32)) {
        self.structures.shift_remove(&xy);
    }

    /// Return uids of every mobile whose position lies within
    /// `sqrt(range_sq)` (Euclidean) of `xy`, in insertion order.
    ///
    /// Engine citation: `ProximitySensorFactory.java:28-41 rasterize` — the
    /// engine's mask uses a 0.01 tolerance on the `radius²` comparison to
    /// round-trip through the GridMask integer rasterisation. Our Euclidean
    /// test operates directly on the float range_sq supplied by the caller
    /// (the caller is responsible for matching the engine's radius² +
    /// tolerance convention — see `sim/pysim.py` SD / attack callsites).
    pub fn mobiles_in_range(&self, xy: (i32, i32), range_sq: f32) -> Vec<u32> {
        let mut out = Vec::new();
        let (cx, cy) = (xy.0 as f32, xy.1 as f32);
        for (uid, &(mx, my)) in self.mobiles.iter() {
            let dx = mx as f32 - cx;
            let dy = my as f32 - cy;
            if dx * dx + dy * dy <= range_sq {
                out.push(*uid);
            }
        }
        out
    }

    /// Return `(x, y)` of every structure within `sqrt(range_sq)` of `xy`, in
    /// insertion order.
    ///
    /// Engine citation: `ProximityArena.java:42-53 getCollisions` — the inner
    /// nested-loop iteration we replace with this direct range scan. See the
    /// module-level doc for why we trade tile-bucketing for an O(n) scan.
    pub fn structures_in_range(&self, xy: (i32, i32), range_sq: f32) -> Vec<(i32, i32)> {
        let mut out = Vec::new();
        let (cx, cy) = (xy.0 as f32, xy.1 as f32);
        for (&(sx, sy), _) in self.structures.iter() {
            let dx = sx as f32 - cx;
            let dy = sy as f32 - cy;
            if dx * dx + dy * dy <= range_sq {
                out.push((sx, sy));
            }
        }
        out
    }

    /// Test-only: number of mobiles currently tracked.
    pub fn mobile_count(&self) -> usize {
        self.mobiles.len()
    }

    /// Test-only: number of structures currently tracked.
    pub fn structure_count(&self) -> usize {
        self.structures.len()
    }

    /// Test-only: look up a mobile's position.
    pub fn mobile_position(&self, uid: u32) -> Option<(i32, i32)> {
        self.mobiles.get(&uid).copied()
    }

    /// Test-only: look up a structure's uid at `xy`.
    pub fn structure_uid(&self, xy: (i32, i32)) -> Option<u32> {
        self.structures.get(&xy).copied()
    }
}
