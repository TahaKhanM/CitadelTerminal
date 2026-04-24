"""Proximity / collision grid for SimCore.

Iteration order is SimCore-internal — NOT matched against engine JVM
order. Engine's CollisionSystem uses a `Set<Pair<ProximitySensor>>`
whose iteration is Java-HashSet bucket order (identity-hash based;
JVM-version-specific, not game-semantic). Replicating that exactly
would bind SimCore to HotSpot's PRNG and bucket layout — a brittle
dependency on engine implementation details that aren't part of the
game contract.

SimCore instead uses deterministic insertion-order iteration per grid
tile. For the four event buckets whose engine emission order is driven
by that HashSet (shield, damage, death, selfDestruct), the validator
compares via sim.event_canonical multiset-equality keys — see
algos/athena/sim/event_canonical.py for rationale and keys.

Current SimCore status
---------------------

The sim-level callsites that previously used direct `for m in
state.mobiles` / `for s in state.structures.values()` scans already
iterate in deterministic order (dict insertion / list insertion,
uid-sorted at state-build by validate._build_state_from_deploy_frame).
A full ProximityArena port — where every in-range check routes through
a grid-bucketed `ColliderComponent.collidedWithThisTurn`-equivalent —
is deferred to the Phase 1.B.3 performance push, where it will pay for
itself via O(n) grid lookup vs today's O(n²) pairwise distance scans.

The module stays here as a named placeholder so the Phase 1.B.5 Rust
port (sim_rs/src/proximity.rs) has a clear parallel structure to
mirror, and so a later Python optimization pass has a drop-in target.

Engine-source reference
-----------------------
  ProximityArena.java:14-53       grid-bucketed sensor storage
  ProximitySensor.java            isHitBy + radius + identity mask
  ProximitySensorFactory.java     rasterize GridMask from radius
  CollisionSystem.java:20-33      runCollisionSystem driver
"""

from __future__ import annotations

from typing import Dict, List, Tuple

from .config import ARENA


class ProximityArena:
    """28×28 grid; each tile holds an insertion-ordered list of sensor
    user_data (Structure or Mobile uid keys). Deterministic iteration.

    NOTE: currently unused by pysim.py. Present as a named module so
    downstream (Rust port, performance push) has a scaffolded target.
    """

    def __init__(self, size: int = ARENA):
        self.size = size
        # {tile_xy: [uid, uid, ...]}; insertion order preserved.
        self._grid: Dict[Tuple[int, int], List[str]] = {}

    def add(self, uid: str, xy: Tuple[int, int]) -> None:
        bucket = self._grid.setdefault(xy, [])
        if uid not in bucket:
            bucket.append(uid)

    def remove(self, uid: str, xy: Tuple[int, int]) -> None:
        bucket = self._grid.get(xy)
        if bucket is None:
            return
        try:
            bucket.remove(uid)
        except ValueError:
            pass
        if not bucket:
            del self._grid[xy]

    def at(self, xy: Tuple[int, int]) -> List[str]:
        return self._grid.get(xy, [])

    def all_tiles(self):
        """Iterate (xy, bucket) in insertion order."""
        return iter(self._grid.items())

    def clear(self) -> None:
        self._grid.clear()
