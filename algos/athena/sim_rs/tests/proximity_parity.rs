//! ProximityArena parity tests — insertion-order iteration + range queries.
//!
//! Engine citations:
//! * `ProximityArena.java:24-31 addSensor`
//! * `ProximityArena.java:33-40 removeSensor`
//! * `ProximitySensorFactory.java:28-41 rasterize` — radius semantics.
//!
//! The expected orderings here are **insertion-order-filtered-by-range** —
//! the parity contract SimCore ships against. See `SIM_PARITY.md` § CASCADE.

use sim_rs::proximity::ProximityArena;

// After the UID-intern refactor arena keys are u32 (interned externally).
const M1: u32 = 1;
const M2: u32 = 2;
const M3: u32 = 3;
const M4: u32 = 4;
const M5: u32 = 5;
const S1: u32 = 11;
const S2: u32 = 12;
const S3: u32 = 13;
const S4: u32 = 14;
const A_UID: u32 = 21;

#[test]
fn empty_arena_returns_empty_list() {
    let arena = ProximityArena::new();
    assert_eq!(arena.mobiles_in_range((13, 10), 16.0), Vec::<u32>::new());
    assert_eq!(arena.structures_in_range((13, 10), 16.0), Vec::<(i32, i32)>::new());
    assert_eq!(arena.mobile_count(), 0);
    assert_eq!(arena.structure_count(), 0);
}

#[test]
fn mobiles_in_range_insertion_order() {
    // Centre (13, 10). range_sq = 16 → radius 4.
    // Mobiles (insertion order):
    //   M1 at (13, 10) : d² = 0    (in range)
    //   M2 at (15, 12) : d² = 4+4 = 8   (in range)
    //   M3 at (20, 20) : d² = 49+100 = 149 (OUT)
    //   M4 at (10, 10) : d² = 9    (in range)
    //   M5 at (17, 10) : d² = 16   (in range, boundary)
    //
    // Expected (insertion order, filtered): [M1, M2, M4, M5].
    let mut arena = ProximityArena::new();
    arena.insert_mobile(M1, (13, 10));
    arena.insert_mobile(M2, (15, 12));
    arena.insert_mobile(M3, (20, 20));
    arena.insert_mobile(M4, (10, 10));
    arena.insert_mobile(M5, (17, 10));

    let in_range = arena.mobiles_in_range((13, 10), 16.0);
    assert_eq!(in_range, vec![M1, M2, M4, M5]);
}

#[test]
fn mobile_remove_preserves_surviving_order() {
    // Insert M1, M2, M3; remove M2; remaining order must be [M1, M3].
    // (IndexMap::shift_remove, not swap_remove.)
    let mut arena = ProximityArena::new();
    arena.insert_mobile(M1, (13, 10));
    arena.insert_mobile(M2, (14, 10));
    arena.insert_mobile(M3, (15, 10));

    arena.remove_mobile(M2);
    let in_range = arena.mobiles_in_range((13, 10), 100.0);
    assert_eq!(in_range, vec![M1, M3]);

    // Missing-key removal is a no-op.
    arena.remove_mobile(999);
    assert_eq!(arena.mobile_count(), 2);
}

#[test]
fn structures_insert_and_remove() {
    let mut arena = ProximityArena::new();
    arena.insert_structure((5, 5), S1);
    arena.insert_structure((6, 6), S2);
    assert_eq!(arena.structure_count(), 2);
    assert_eq!(arena.structure_uid((5, 5)), Some(S1));

    arena.remove_structure((5, 5));
    assert_eq!(arena.structure_count(), 1);
    assert_eq!(arena.structure_uid((5, 5)), None);
    assert_eq!(arena.structure_uid((6, 6)), Some(S2));
}

#[test]
fn structures_in_range_insertion_order() {
    // Centre (13, 10), range_sq = 9 → radius 3.
    // Structures (insertion order):
    //   S1 at (13, 10)   d² = 0  in
    //   S2 at (20, 20)   d² = 149 out
    //   S3 at (12, 11)   d² = 2  in
    //   S4 at (13, 13)   d² = 9  in (boundary)
    let mut arena = ProximityArena::new();
    arena.insert_structure((13, 10), S1);
    arena.insert_structure((20, 20), S2);
    arena.insert_structure((12, 11), S3);
    arena.insert_structure((13, 13), S4);

    let in_range = arena.structures_in_range((13, 10), 9.0);
    assert_eq!(in_range, vec![(13, 10), (12, 11), (13, 13)]);
}

#[test]
fn structure_remove_absent_is_noop() {
    let mut arena = ProximityArena::new();
    arena.insert_structure((1, 1), A_UID);
    arena.remove_structure((99, 99));
    assert_eq!(arena.structure_count(), 1);
    arena.remove_structure((1, 1));
    assert_eq!(arena.structure_count(), 0);
    assert_eq!(arena.structures_in_range((1, 1), 100.0), Vec::<(i32, i32)>::new());
}

#[test]
fn mobile_reinsert_updates_position() {
    // Re-inserting the same uid should update the position (engine semantics:
    // one Gameobject, one position). The updated tile must pass the range
    // query; the stale tile must NOT yield a hit.
    let mut arena = ProximityArena::new();
    arena.insert_mobile(M1, (0, 0));
    arena.insert_mobile(M1, (13, 10));

    assert_eq!(arena.mobile_count(), 1);
    assert_eq!(arena.mobile_position(M1), Some((13, 10)));
    // Querying from (13, 10) with radius 1 still hits M1:
    assert_eq!(arena.mobiles_in_range((13, 10), 1.0), vec![M1]);
    // Querying from (0, 0) with radius 1 does NOT hit M1 (it has moved):
    assert_eq!(arena.mobiles_in_range((0, 0), 1.0), Vec::<u32>::new());
}
