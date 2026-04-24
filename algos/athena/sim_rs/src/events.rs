//! Event canonical keys — mirrors `algos/athena/sim/event_canonical.py`.
//!
//! Same four JVM-HashSet-ordered buckets: `shield`, `damage`, `death`,
//! `selfDestruct`. Rust keys use `String` uids + `f32` bit-pattern-compare
//! semantics; the validator's multiset-equality gate expects identical keys.

#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct ShieldKey {
    pub src_uid: String,
    pub tgt_uid: String,
    pub amount_bits: u32,   // f32.to_bits() for canonicalisation
    pub type_id: i32,
}

#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct DamageKey {
    pub victim_uid: String,
    pub amount_bits: u32,
    pub type_id: i32,
    pub xy: (i32, i32),
    pub pid: i32,
}

#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct DeathKey {
    pub uid: String,
    pub type_id: i32,
    pub pid: i32,
    pub removed: bool,
    pub xy: (i32, i32),
}

#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct SelfDestructKey {
    pub src_uid: String,
    pub src_xy: (i32, i32),
    pub damage_bits: u32,
    pub type_id: i32,
    pub target_xys: Vec<(i32, i32)>, // sorted on insertion
}
