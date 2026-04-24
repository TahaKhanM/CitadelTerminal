//! Event canonical-key parity tests — mirrors
//! `algos/athena/sim/event_canonical.py`. Expected orderings cross-checked
//! against a live Python run of `canonicalize_bucket(...)` (see comments
//! above each test case).
//!
//! Engine citations:
//! * `ProximityArena.java:42-53`       — `Set<Pair<ProximitySensor>>` that
//!   drives JVM-HashSet ordering on these four buckets.
//! * `ShieldSystem.java`, `SelfDestructSystem.java`,
//!   `TargetAndAttackSystem.java`, `DeathSystem.java` — the emitters.

use sim_rs::events::{canonicalize_bucket, bucket_keys, BucketKey, EventEntry};

// ---------------------------------------------------------------------------
// Shield bucket
// ---------------------------------------------------------------------------

#[test]
fn shield_canonical_order() {
    // Key: (src_uid, tgt_uid, amount_bits, type_id).
    // Python reference run produced (src, tgt, amount) in this order:
    //   [('S1', 'T1', 5.0), ('S1', 'T2', 5.0),
    //    ('S2', 'T1', 1.0), ('S2', 'T1', 3.0)]
    let mut entries = vec![
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 3.0, type_id: 2,
            src_uid: "S2".into(), tgt_uid: "T1".into(), pid: 1,
        }, // E1
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 5.0, type_id: 2,
            src_uid: "S1".into(), tgt_uid: "T2".into(), pid: 1,
        }, // E2
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 5.0, type_id: 2,
            src_uid: "S1".into(), tgt_uid: "T1".into(), pid: 1,
        }, // E3
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 1.0, type_id: 2,
            src_uid: "S2".into(), tgt_uid: "T1".into(), pid: 1,
        }, // E4
    ];

    canonicalize_bucket("shield", &mut entries);

    let summaries: Vec<(String, String, f32)> = entries
        .iter()
        .map(|e| match e {
            EventEntry::Shield { src_uid, tgt_uid, amount, .. } => {
                (src_uid.clone(), tgt_uid.clone(), *amount)
            }
            _ => unreachable!("non-shield entry in shield bucket"),
        })
        .collect();

    assert_eq!(
        summaries,
        vec![
            ("S1".to_string(), "T1".to_string(), 5.0),
            ("S1".to_string(), "T2".to_string(), 5.0),
            ("S2".to_string(), "T1".to_string(), 1.0),
            ("S2".to_string(), "T1".to_string(), 3.0),
        ]
    );
}

// ---------------------------------------------------------------------------
// Damage bucket
// ---------------------------------------------------------------------------

#[test]
fn damage_canonical_order() {
    // Key: (victim_uid, amount_bits, type_id, xy, pid).
    // Python reference (victim, amount, xy):
    //   [('V1', 5.0, (4,4)), ('V1', 10.0, (3,3)),
    //    ('V1', 10.0, (4,4)), ('V2', 10.0, (5,5))]
    let mut entries = vec![
        EventEntry::Damage { xy: (5, 5), amount: 10.0, type_id: 3,
            victim_uid: "V2".into(), pid: 1 }, // D1
        EventEntry::Damage { xy: (4, 4), amount: 10.0, type_id: 3,
            victim_uid: "V1".into(), pid: 1 }, // D2
        EventEntry::Damage { xy: (4, 4), amount: 5.0,  type_id: 3,
            victim_uid: "V1".into(), pid: 1 }, // D3
        EventEntry::Damage { xy: (3, 3), amount: 10.0, type_id: 3,
            victim_uid: "V1".into(), pid: 1 }, // D4
    ];

    canonicalize_bucket("damage", &mut entries);

    let summaries: Vec<(String, f32, (i32, i32))> = entries
        .iter()
        .map(|e| match e {
            EventEntry::Damage { victim_uid, amount, xy, .. } => {
                (victim_uid.clone(), *amount, *xy)
            }
            _ => unreachable!(),
        })
        .collect();

    assert_eq!(
        summaries,
        vec![
            ("V1".to_string(), 5.0,  (4, 4)),
            ("V1".to_string(), 10.0, (3, 3)),
            ("V1".to_string(), 10.0, (4, 4)),
            ("V2".to_string(), 10.0, (5, 5)),
        ]
    );
}

// ---------------------------------------------------------------------------
// Death bucket
// ---------------------------------------------------------------------------

#[test]
fn death_canonical_order() {
    // Key: (uid, type_id, pid, removed, xy). Python reference (uid, removed):
    //   [('M1', False), ('M1', True), ('M2', False), ('M3', False)]
    let mut entries = vec![
        EventEntry::Death { xy: (1, 1), type_id: 3, uid: "M3".into(), pid: 1, removed: false }, // DE1
        EventEntry::Death { xy: (2, 2), type_id: 3, uid: "M1".into(), pid: 1, removed: true  }, // DE2
        EventEntry::Death { xy: (2, 2), type_id: 3, uid: "M1".into(), pid: 1, removed: false }, // DE3
        EventEntry::Death { xy: (3, 3), type_id: 3, uid: "M2".into(), pid: 1, removed: false }, // DE4
    ];

    canonicalize_bucket("death", &mut entries);

    let summaries: Vec<(String, bool)> = entries
        .iter()
        .map(|e| match e {
            EventEntry::Death { uid, removed, .. } => (uid.clone(), *removed),
            _ => unreachable!(),
        })
        .collect();

    assert_eq!(
        summaries,
        vec![
            ("M1".to_string(), false),
            ("M1".to_string(), true),
            ("M2".to_string(), false),
            ("M3".to_string(), false),
        ]
    );
}

// ---------------------------------------------------------------------------
// SelfDestruct bucket
// ---------------------------------------------------------------------------

#[test]
fn self_destruct_canonical_order_and_target_sort() {
    // Key: (src_uid, src_xy, damage_bits, type_id, sorted_target_xys).
    // Python reference (src, src_xy, dmg, sorted target list):
    //   A (1,1) 5.0 [(2,2),(3,3)]   (from SD2: original [(3,3),(2,2)])
    //   A (1,1) 5.0 [(2,2),(3,3)]   (from SD4: original [(2,2),(3,3)])
    //   A (1,1) 5.0 [(2,2),(9,9)]   (from SD3: original [(9,9),(2,2)])
    //   B (5,5) 2.0 [(6,6),(7,7)]   (from SD1: original [(7,7),(6,6)])
    //
    // Stable-sort tiebreak: SD2 comes before SD4 because their keys are
    // exactly equal and SD2 was inserted first. Python's `sorted(...)` is
    // stable; Rust's `sort_by` is also stable — both agree here.
    let mut entries = vec![
        EventEntry::SelfDestruct {
            src_xy: (5, 5),
            target_xys: vec![(7, 7), (6, 6)],
            damage: 2.0, type_id: 4,
            src_uid: "B".into(), pid: 1,
        }, // SD1
        EventEntry::SelfDestruct {
            src_xy: (1, 1),
            target_xys: vec![(3, 3), (2, 2)],
            damage: 5.0, type_id: 4,
            src_uid: "A".into(), pid: 1,
        }, // SD2
        EventEntry::SelfDestruct {
            src_xy: (1, 1),
            target_xys: vec![(9, 9), (2, 2)],
            damage: 5.0, type_id: 4,
            src_uid: "A".into(), pid: 1,
        }, // SD3
        EventEntry::SelfDestruct {
            src_xy: (1, 1),
            target_xys: vec![(2, 2), (3, 3)],
            damage: 5.0, type_id: 4,
            src_uid: "A".into(), pid: 1,
        }, // SD4
    ];

    canonicalize_bucket("selfDestruct", &mut entries);

    // After canonicalisation, each entry's `target_xys` is sorted (Rust does
    // this explicitly; Python's reference canonicalises only the sort key,
    // not the body — but SimCore + replay both consume the Rust form, so the
    // stricter normalisation here is the contract).
    let summaries: Vec<(String, (i32, i32), f32, Vec<(i32, i32)>)> = entries
        .iter()
        .map(|e| match e {
            EventEntry::SelfDestruct { src_uid, src_xy, damage, target_xys, .. } => (
                src_uid.clone(),
                *src_xy,
                *damage,
                target_xys.clone(),
            ),
            _ => unreachable!(),
        })
        .collect();

    assert_eq!(
        summaries,
        vec![
            ("A".to_string(), (1, 1), 5.0, vec![(2, 2), (3, 3)]),
            ("A".to_string(), (1, 1), 5.0, vec![(2, 2), (3, 3)]),
            ("A".to_string(), (1, 1), 5.0, vec![(2, 2), (9, 9)]),
            ("B".to_string(), (5, 5), 2.0, vec![(6, 6), (7, 7)]),
        ]
    );
}

// ---------------------------------------------------------------------------
// Misc
// ---------------------------------------------------------------------------

#[test]
fn unknown_bucket_is_noop() {
    // Strict-ordered buckets (attack, breach, move, spawn, melee) pass
    // through `canonicalize_bucket` unchanged — matches Python's `return
    // list(entries)` when no key function is registered.
    let mut entries = vec![
        EventEntry::Damage { xy: (9, 9), amount: 1.0, type_id: 3,
            victim_uid: "Z".into(), pid: 1 },
        EventEntry::Damage { xy: (0, 0), amount: 1.0, type_id: 3,
            victim_uid: "A".into(), pid: 1 },
    ];
    let original = entries.clone();
    canonicalize_bucket("attack", &mut entries);
    assert_eq!(entries, original);
}

#[test]
fn bucket_keys_returns_sorted_keys() {
    let entries = vec![
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 3.0, type_id: 2,
            src_uid: "S2".into(), tgt_uid: "T1".into(), pid: 1,
        },
        EventEntry::Shield {
            src_xy: (0, 0), tgt_xy: (1, 1),
            amount: 5.0, type_id: 2,
            src_uid: "S1".into(), tgt_uid: "T1".into(), pid: 1,
        },
    ];
    let keys = bucket_keys("shield", &entries);
    assert_eq!(keys.len(), 2);
    // First key must have src_uid "S1" after sort.
    match &keys[0] {
        BucketKey::Shield(k) => assert_eq!(k.src_uid, "S1"),
        _ => panic!("wrong BucketKey variant"),
    }
    match &keys[1] {
        BucketKey::Shield(k) => assert_eq!(k.src_uid, "S2"),
        _ => panic!("wrong BucketKey variant"),
    }

    // Strict-ordered bucket returns empty key list.
    let no_keys = bucket_keys("attack", &entries);
    assert!(no_keys.is_empty());
}

#[test]
fn float_bit_equality_f32_roundtrip() {
    // Python side uses np.float32 round-trip equality. Rust uses f32::to_bits.
    // A clean 3.0 → 0x40400000, stable across both.
    let k_a = EventEntry::Shield {
        src_xy: (0, 0), tgt_xy: (0, 0),
        amount: 3.0f32, type_id: 0,
        src_uid: "x".into(), tgt_uid: "y".into(), pid: 1,
    };
    let k_b = EventEntry::Shield {
        src_xy: (0, 0), tgt_xy: (0, 0),
        amount: 3.0f32, type_id: 0,
        src_uid: "x".into(), tgt_uid: "y".into(), pid: 1,
    };
    let keys = bucket_keys(
        "shield",
        &[k_a, k_b],
    );
    assert_eq!(keys[0], keys[1]);
}
