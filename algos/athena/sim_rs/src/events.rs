//! Event canonical keys — mirrors `algos/athena/sim/event_canonical.py`.
//!
//! Same four JVM-HashSet-ordered buckets: `shield`, `damage`, `death`,
//! `selfDestruct`. Rust keys use `String` uids + `f32::to_bits()` bit-pattern
//! semantics; the validator's multiset-equality gate expects the sorted-key
//! list produced here to match Python's sorted-key list element-for-element.
//!
//! # Rationale (from `event_canonical.py`)
//!
//! Engine's `CollisionSystem` populates `ColliderComponent.collidedWithThisTurn`
//! via `ProximityArena.getCollisions()` iterating a
//! `Set<Pair<ProximitySensor>>` (Java HashSet,
//! `ProximityArena.java:42-53`). Downstream, `ShieldSystem`,
//! `SelfDestructSystem`, and `TargetAndAttackSystem` iterate those collided
//! lists in the order the HashSet produced them. Java HashSet iteration
//! order is driven by `Object.hashCode()` (HotSpot PRNG-seeded identity
//! hash), which is not part of the game contract. SimCore accepts
//! multiset-equality on these four buckets via the canonical-key gate here.
//!
//! # Ordering-vs-bit-compare subtlety
//!
//! Python's `sorted(..., key=lambda e: (str, str, float, int))` compares
//! floats by numeric value. Rust tuple `Ord` on `u32` (the `to_bits()` raw
//! pattern) preserves numeric ordering *for non-negative f32*: the IEEE-754
//! binary32 layout is monotone-increasing in bit pattern for `[+0.0, +inf]`.
//! Shield amounts and damage amounts are always non-negative in Citadel
//! Terminal (HealthComponent enforces `amount >= 0` at apply-time), so
//! `u32::cmp` on `to_bits()` yields the same order as Python `float::cmp`.
//! Equality (`==` / `Hash`) is pure bit-pattern — matches Python np.float32
//! round-trip bit equality.
//!
//! # Engine wire format per bucket (inPerspective output)
//! ```text
//! attack:       [[src_xy], [tgt_xy], dmg,   typeID, src_uid, tgt_uid, pid]
//! breach:       [[xy],     dmg,      typeID, uid,   pid]
//! damage:       [[xy],     dmg,      typeID, uid,   pid]
//! death:        [[xy],     typeID,   uid,    pid,   removed]
//! move:         [[old_xy], [new_xy], [next_xy], typeID, uid, pid]
//! selfDestruct: [[src_xy], [target_xys], dmg, typeID, uid, pid]
//! shield:       [[src_xy], [tgt_xy], amount, typeID, src_uid, tgt_uid, pid]
//! spawn:        [[xy],     typeID,   uid,    pid]
//! ```

// ---------------------------------------------------------------------------
// Canonical key types
// ---------------------------------------------------------------------------

/// Canonical key for a `shield` bucket entry:
/// `(src_uid, tgt_uid, amount_bits, type_id)`.
///
/// Engine citation: `ShieldSystem.java` + `ShielderComponent.shieldedAlready`
/// — a (src, tgt) pair is unique within a single action phase because the
/// shielder may shield each target at most once. That invariant makes
/// `(src_uid, tgt_uid)` a natural partial key; we extend with amount + type_id
/// as a collision guard for edge cases (duplicate events, re-application
/// after source death, etc.).
#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct ShieldKey {
    pub src_uid: String,
    pub tgt_uid: String,
    pub amount_bits: u32, // f32::to_bits() — see module doc re: ordering
    pub type_id: i32,
}

/// Canonical key for a `damage` bucket entry:
/// `(victim_uid, amount_bits, type_id, xy, pid)`.
///
/// Engine citation: `GlobalDamaged.java` + `HealthComponent.damage` callsites
/// in `TargetAndAttackSystem.java` / `SelfDestructSystem.java`. Multiple
/// damage events to the same victim in one frame can differ by source (SD
/// AOE hitting multiple victims, turret + SD colliding in one frame);
/// including `amount` + `type_id` + `xy` + `pid` prevents canonical-key
/// collisions between two equal-amount events on the same frame.
#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct DamageKey {
    pub victim_uid: String,
    pub amount_bits: u32,
    pub type_id: i32,
    pub xy: (i32, i32),
    pub pid: i32,
}

/// Canonical key for a `death` bucket entry:
/// `(uid, type_id, pid, removed, xy)`.
///
/// Engine citation: `GlobalDeath.java` + `DeathSystem.java`. Each uid dies
/// at most once per game — uid alone would suffice as a canonical key, but
/// the extra fields guard against stale/duplicate events in edge cases
/// (e.g. self-destruct + direct-damage both reducing HP to 0 in one frame).
#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct DeathKey {
    pub uid: String,
    pub type_id: i32,
    pub pid: i32,
    pub removed: bool,
    pub xy: (i32, i32),
}

/// Canonical key for a `selfDestruct` bucket entry:
/// `(src_uid, src_xy, damage_bits, type_id, sorted target_xys)`.
///
/// Engine citation: `SelfDestructSystem.java` — two events are emitted per
/// SD-er when `attackWalker != attackTower` (Citadel never trips this path
/// in practice, but the key supports it). `target_xys` is sorted because
/// the per-target iteration within a single SD event is JVM-HashSet
/// internal — sorting normalises that sub-ordering.
#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub struct SelfDestructKey {
    pub src_uid: String,
    pub src_xy: (i32, i32),
    pub damage_bits: u32,
    pub type_id: i32,
    pub target_xys: Vec<(i32, i32)>, // sorted on construction
}

// ---------------------------------------------------------------------------
// EventEntry — neutral container that captures every field needed to derive
// each bucket's canonical key. Callers construct the right variant for the
// bucket they own; `canonicalize_bucket` matches on the variant.
// ---------------------------------------------------------------------------

/// Neutral event-entry wrapper used by `canonicalize_bucket`. Each variant
/// carries exactly the fields drawn from the engine wire format (see module
/// doc) that the corresponding canonical key reads.
#[derive(Debug, Clone, PartialEq)]
pub enum EventEntry {
    /// Engine wire: `[[src_xy], [tgt_xy], amount, typeID, src_uid, tgt_uid, pid]`
    Shield {
        src_xy: (i32, i32),
        tgt_xy: (i32, i32),
        amount: f32,
        type_id: i32,
        src_uid: String,
        tgt_uid: String,
        pid: i32,
    },
    /// Engine wire: `[[xy], dmg, typeID, uid, pid]`
    Damage {
        xy: (i32, i32),
        amount: f32,
        type_id: i32,
        victim_uid: String,
        pid: i32,
    },
    /// Engine wire: `[[xy], typeID, uid, pid, removed]`
    Death {
        xy: (i32, i32),
        type_id: i32,
        uid: String,
        pid: i32,
        removed: bool,
    },
    /// Engine wire: `[[src_xy], [target_xys], dmg, typeID, uid, pid]`
    SelfDestruct {
        src_xy: (i32, i32),
        target_xys: Vec<(i32, i32)>,
        damage: f32,
        type_id: i32,
        src_uid: String,
        pid: i32,
    },
}

impl EventEntry {
    /// Derive the canonical-key tuple for this entry, using the bucket name
    /// hint to pick the right variant extraction. Returns `None` for an
    /// entry whose variant does not match the bucket (shouldn't happen in
    /// well-typed callers — but we fail-soft so `canonicalize_bucket` can
    /// still leave the mismatched entry in place).
    fn shield_key(&self) -> Option<ShieldKey> {
        if let EventEntry::Shield { src_uid, tgt_uid, amount, type_id, .. } = self {
            Some(ShieldKey {
                src_uid: src_uid.clone(),
                tgt_uid: tgt_uid.clone(),
                amount_bits: amount.to_bits(),
                type_id: *type_id,
            })
        } else {
            None
        }
    }

    fn damage_key(&self) -> Option<DamageKey> {
        if let EventEntry::Damage { xy, amount, type_id, victim_uid, pid } = self {
            Some(DamageKey {
                victim_uid: victim_uid.clone(),
                amount_bits: amount.to_bits(),
                type_id: *type_id,
                xy: *xy,
                pid: *pid,
            })
        } else {
            None
        }
    }

    fn death_key(&self) -> Option<DeathKey> {
        if let EventEntry::Death { xy, type_id, uid, pid, removed } = self {
            Some(DeathKey {
                uid: uid.clone(),
                type_id: *type_id,
                pid: *pid,
                removed: *removed,
                xy: *xy,
            })
        } else {
            None
        }
    }

    fn self_destruct_key(&self) -> Option<SelfDestructKey> {
        if let EventEntry::SelfDestruct { src_xy, target_xys, damage, type_id, src_uid, .. } = self
        {
            let mut sorted_targets = target_xys.clone();
            sorted_targets.sort();
            Some(SelfDestructKey {
                src_uid: src_uid.clone(),
                src_xy: *src_xy,
                damage_bits: damage.to_bits(),
                type_id: *type_id,
                target_xys: sorted_targets,
            })
        } else {
            None
        }
    }
}

// ---------------------------------------------------------------------------
// Public canonicalisation API — matches `event_canonical.canonicalize_bucket`.
// ---------------------------------------------------------------------------

/// Sort `entries` in place by the canonical key for `bucket_name`.
/// If the bucket has no canonical key (i.e. is strict-ordered in the
/// validator), this is a no-op.
///
/// Engine citation: mirror of `event_canonical.canonicalize_bucket` —
/// this is the single call site both sides (sim + replay) use to normalise
/// JVM-HashSet-ordered buckets before multiset-equality comparison.
pub fn canonicalize_bucket(bucket_name: &str, entries: &mut Vec<EventEntry>) {
    match bucket_name {
        "shield" => {
            entries.sort_by(|a, b| {
                a.shield_key().cmp(&b.shield_key())
            });
        }
        "damage" => {
            entries.sort_by(|a, b| {
                a.damage_key().cmp(&b.damage_key())
            });
        }
        "death" => {
            // Normalise any SelfDestruct-internal `target_xys` ordering by
            // sorting entries by their derived DeathKey.
            entries.sort_by(|a, b| {
                a.death_key().cmp(&b.death_key())
            });
        }
        "selfDestruct" => {
            // Also normalise each entry's own `target_xys` list so the
            // downstream serialised form is canonical, not just the sort
            // order. (Python does the same via `sorted(target_xys)` inside
            // `_key_self_destruct`.)
            for e in entries.iter_mut() {
                if let EventEntry::SelfDestruct { target_xys, .. } = e {
                    target_xys.sort();
                }
            }
            entries.sort_by(|a, b| {
                a.self_destruct_key().cmp(&b.self_destruct_key())
            });
        }
        _ => {
            // Strict-ordered bucket — no-op, matches Python's `return list(entries)`
            // semantics (caller already owns the Vec).
        }
    }
}

/// Return the sorted-key list for multiset-equality comparison.
///
/// Engine citation: mirror of `event_canonical.bucket_keys`. Two buckets are
/// multiset-equal iff their sorted-key lists compare element-for-element
/// equal. Returns an empty `Vec` for strict-ordered buckets (same as Python).
pub fn bucket_keys(bucket_name: &str, entries: &[EventEntry]) -> Vec<BucketKey> {
    let mut out: Vec<BucketKey> = match bucket_name {
        "shield" => entries
            .iter()
            .filter_map(|e| e.shield_key().map(BucketKey::Shield))
            .collect(),
        "damage" => entries
            .iter()
            .filter_map(|e| e.damage_key().map(BucketKey::Damage))
            .collect(),
        "death" => entries
            .iter()
            .filter_map(|e| e.death_key().map(BucketKey::Death))
            .collect(),
        "selfDestruct" => entries
            .iter()
            .filter_map(|e| e.self_destruct_key().map(BucketKey::SelfDestruct))
            .collect(),
        _ => Vec::new(),
    };
    out.sort();
    out
}

/// Unified key enum so `bucket_keys` can return a homogeneous sorted list.
#[derive(Debug, Clone, PartialEq, Eq, Hash, PartialOrd, Ord)]
pub enum BucketKey {
    Shield(ShieldKey),
    Damage(DamageKey),
    Death(DeathKey),
    SelfDestruct(SelfDestructKey),
}
