//! ProximityArena scaffold — full port pending.
//!
//! Rust port uses an `IndexMap`-backed bucket keyed on integer tile indices
//! for deterministic iteration — Java's `HashSet` is replaced by IndexSet to
//! remove the JVM-hash-order non-determinism that the validator's CASCADE
//! band exists to absorb. See `SIM_PARITY.md` § CASCADE gate.
//!
//! Engine citations:
//! * `ProximitySensorFactory.java` — sensor construction
//! * `ProximityUtil.java`          — range queries
//! * `ProximityArena.java:42-53`   — the HashSet<Pair<ProximitySensor>> that
//!   drives `ColliderComponent.collidedWithThisTurn` ordering.

pub struct ProximityArena;
