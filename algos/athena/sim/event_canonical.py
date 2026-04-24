"""Canonical sort keys for engine event buckets whose emission order is
JVM-`HashSet`-internal rather than game-semantically determined.

Rationale (Cluster H, Option C)
-------------------------------

Engine's CollisionSystem populates `ColliderComponent.collidedWithThisTurn`
via ProximityArena.getCollisions() iterating a `Set<Pair<ProximitySensor>>`
(Java HashSet, ProximityArena.java:42-53). Downstream, ShieldSystem,
SelfDestructSystem, and TargetAndAttackSystem iterate those collided lists
in the order the HashSet produced them. Java HashSet iteration order is
driven by `Object.hashCode()` — a per-object JVM-assigned pseudo-random
identity hash (HotSpot uses a thread-local PRNG, lazy-assigned on first
hashCode read) — and the HashSet's internal bucket layout. None of this
is part of the game contract: the Citadel server could upgrade its JVM
tomorrow and these emission orders would change with zero game-semantic
impact.

Rather than emulating the JVM (Option B — ~3 sessions, brittle-by-JVM-
version) or shipping an intentionally-imperfect partial port that leaves
"~200 unexplained residual frames" for future confusion (Option A), the
validator accepts multiset-equality on these four event buckets:

    shield, damage, death, selfDestruct

Both sides (sim + replay) canonicalize the buckets to a stable sort key
per event, then compare as ordered lists. Content identical → gate
passes; order JVM-internal → doesn't affect the gate. All *other* event
buckets (attack, breach, move, spawn, melee) remain strict-ordered —
their emission is driven by game.gameObjects iteration (insertion order,
engine AND sim), not HashSet bucketing.

Downstream contract
-------------------

Any code consuming shield / damage / death / selfDestruct event buckets
must treat them as sets or sort-to-canonical-key before relying on
order. This applies to:
  * OffensePlanner beam search (Phase 5)
  * OpponentModel predictors
  * sim_rs Rust port (Phase 1.B.5) — uses IndexMap or BTreeMap + emits
    through these same keys
  * Any future tooling

If some future planner genuinely requires JVM-exact order, the
conversation reopens at that point; speculative byte-exactness is
explicitly rejected here.
"""

from __future__ import annotations

from typing import Any, Callable, List, Tuple


# Engine wire format per bucket (inPerspective output, SimCore mirrors):
#   attack:       [[src_xy], [tgt_xy], dmg,   typeID, src_uid, tgt_uid, pid]
#   breach:       [[xy],     dmg,      typeID, uid,   pid]
#   damage:       [[xy],     dmg,      typeID, uid,   pid]
#   death:        [[xy],     typeID,   uid,    pid,   removed]
#   move:         [[old_xy], [new_xy], [next_xy], typeID, uid, pid]
#   selfDestruct: [[src_xy], [target_xys], dmg, typeID, uid, pid]
#   shield:       [[src_xy], [tgt_xy], amount, typeID, src_uid, tgt_uid, pid]
#   spawn:        [[xy],     typeID,   uid,    pid]


def _key_shield(e: list) -> Tuple:
    """shield event canonical key: (src_uid, tgt_uid, amount, typeID).
    Supports apply to each unique target at most once per action phase
    via shielderComponent.shieldedAlready; the (src, tgt) pair is
    unique within the phase, making it a natural canonical key."""
    return (str(e[4]), str(e[5]), float(e[2]), int(e[3]))


def _key_damage(e: list) -> Tuple:
    """damage event canonical key: (victim_uid, amount, typeID, xy, pid).
    Multiple damage events to the same victim in one frame can differ
    by source (SD AOE hitting multiple victims, turret + SD same frame);
    including amount + typeID + coord + pid prevents collision when
    two equal-amount damage events land the same frame."""
    xy = tuple(e[0]) if isinstance(e[0], (list, tuple)) else (0, 0)
    return (str(e[3]), float(e[1]), int(e[2]), xy, int(e[4]))


def _key_death(e: list) -> Tuple:
    """death event canonical key: (uid, typeID, pid, removed, xy).
    Each uid dies at most once per game — uid alone would suffice, but
    the extra fields guard against stale/duplicate events in edge cases."""
    xy = tuple(e[0]) if isinstance(e[0], (list, tuple)) else (0, 0)
    return (str(e[2]), int(e[1]), int(e[3]), bool(e[4]), xy)


def _key_self_destruct(e: list) -> Tuple:
    """selfDestruct event canonical key: (src_uid, src_xy, dmg, typeID,
    sorted_target_xys). Two events per SDer when attackWalker !=
    attackTower (Citadel never hits this, but the key supports it);
    sort the target_xys list so per-target iteration order within a
    single SD event is also normalized (JVM-HashSet order internally)."""
    src_xy = tuple(e[0]) if isinstance(e[0], (list, tuple)) else (0, 0)
    raw_targets = e[1] or []
    target_xys = tuple(sorted(
        tuple(t) if isinstance(t, (list, tuple)) else (t,)
        for t in raw_targets
    ))
    return (str(e[4]), src_xy, float(e[2]), int(e[3]), target_xys)


# Map bucket name → key function. Buckets NOT in this map remain
# strict-ordered in the validator.
CANONICAL_KEYS: dict = {
    "shield": _key_shield,
    "damage": _key_damage,
    "death": _key_death,
    "selfDestruct": _key_self_destruct,
}


def canonicalize_bucket(bucket_name: str, entries: List[list]) -> List[list]:
    """Sort `entries` by the canonical key for `bucket_name`. Returns a
    new list; input is not modified. If the bucket has no canonical
    key (i.e. strict-ordered), returns a shallow copy of entries."""
    keyfn = CANONICAL_KEYS.get(bucket_name)
    if keyfn is None:
        return list(entries)
    return sorted(entries, key=keyfn)


def bucket_keys(bucket_name: str, entries: List[list]) -> List[tuple]:
    """Return a sorted list of canonical keys — one per entry. For
    multiset-equality comparison on HashSet-order-affected buckets:
    two buckets are multiset-equal iff their sorted key lists compare
    equal. This is robust against event-body orderings (e.g. target_xys
    lists inside selfDestruct entries) that the raw body-compare would
    incorrectly flag as divergent."""
    keyfn = CANONICAL_KEYS.get(bucket_name)
    if keyfn is None:
        return []
    return sorted(keyfn(e) for e in entries)
