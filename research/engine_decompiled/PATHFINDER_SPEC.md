# Engine.jar PathFinder — behavioral spec

All line citations point at
`research/engine_decompiled/sources/com/c1games/terminal/game/systems/map/path/PathFinder.java`
(decompiled from `engine.jar` with CFR 0.152).

Purpose of this doc: give a Python porter enough to write a bit-exact
reproduction, without reading 491 lines of decompiled Java. This is the
source of truth for `algos/athena/sim/pathfinder.py`; starter-gamelib's
`navigation.py` is NOT and does not match the engine on some
wall-death-mid-turn scenarios.

---

## 1. Instance model

* **4 instances per game.** The engine creates ONE `PathFinder` per target
  edge: `topLeft, topRight, bottomLeft, bottomRight` — see `Game.java`
  constructor. A mobile unit's `NavigationComponent.pathfinder` points at
  whichever of the 4 matches its spawn-derived target edge.
* Each instance holds **persistent state** across frames. State is
  updated incrementally as walls are added (`put(x,y)`) / removed
  (`remove(x,y)`), NOT rebuilt from scratch each frame. This persistence
  is what the starter gamelib's stateless BFS misses.

State arrays, all of length `dimension * dimension` = 784 (for a 28×28
map, PathFinder.java:46):
* `status[]` — `byte`, one of `INVALID=0, OPEN=1, WALL=2` (lines 10–12).
* `parentDirection[]` — `short`, bitmask built by `addDirectionToMask`;
  used only inside `validate()` (lines 13–17 define the direction codes
  `LEFT=1, RIGHT=2, DOWN=3, UP=4`).
* `pathlength[]` — `short`, distance to the nearest perfect (edge) tile
  for an OPEN cell, 0 for edge cells.
* `idealness[]` — `int`, packed `(yIdealness<<16) | xIdealness`; perfect
  cells get `Integer.MAX_VALUE`. See `idealness(x,y)` lines 198–207.
* `visited[]` — `int`, per-cell exploration depth marker inside
  `idealnessSearch()`.
* `explored[]`, `perfectLookup[]` — `boolean` flags.
* `boardInvalid` — set to `true` on first `remove(x,y)` call since the
  last validation; triggers a sweep that re-marks all non-edge OPEN
  tiles as INVALID (lines 132–140).

Neighbor offsets (lines 19–20, order matters for tiebreaks!):
```
NEIGHBOR_X = {+1, -1,  0,  0}      // RIGHT, LEFT, DOWN, UP
NEIGHBOR_Y = { 0,  0, +1, -1}
PARENT_DIRECTION = {LEFT=1, RIGHT=2, DOWN=3, UP=4}
```

At construction (lines 62–79):
* The 4 triangular corners of the diamond are pre-marked `WALL` (status
  2). This makes off-diamond tiles behave identically to real walls.
* Each `perfect` (edge) tile is marked `OPEN`, `pathlength=0`,
  `idealness=MAX_VALUE`, and flagged in `perfectLookup[]`.
* Each initial wall from the `walls` list is marked `WALL`.

---

## 2. `put(targetX, targetY)` — wall added (lines 82–119)

1. If tile is already WALL, no-op.
2. Mark tile WALL.
3. Push the **4 orthogonal OPEN neighbors** into `currentQueue`.
4. BFS outward: for each cell popped, decide whether its current
   `pathlength` is still achievable via at least one neighbor with
   `pathlength == self_pathlength − 1`. If yes (`found=true`), keep it.
   If no, set its status to `INVALID (0)` and push its OPEN neighbors
   for the same check. (Cascading invalidation.)
5. Cells on the edge (pathlength == 0) are skipped — they're always
   reachable (line 100).

Note: `put()` does NOT recompute; it only invalidates cells that lost
their shortcut. Revalidation is deferred to the next `getStep()` that
visits one of the invalidated cells' neighbors.

---

## 3. `remove(targetX, targetY)` — wall destroyed (lines 121–141)

1. If tile is not WALL, no-op.
2. If the tile's `idealness` would be `MAX_VALUE` (i.e. it's a perfect /
   edge tile), set status=OPEN with `pathlength=0`.
3. Else set status=INVALID.
4. If `boardInvalid` is false, flip it true AND **wipe all non-edge OPEN
   cells to INVALID** (a full sweep — lines 134–139). This is expensive
   but matches the engine's behavior: after a wall dies, virtually every
   non-edge cell's pathlength is suspect until re-validated.

This sweep is the key reason my starter-gamelib-style port diverged:
the engine treats *any* `remove` during the turn as a global reset of
non-edge pathlengths, then lazily re-validates via `getStep`.

---

## 4. `validate(CoordQueue targets)` (lines 143–189)

Called from `getStep` with the queue containing the unit's current tile
plus any INVALID neighbors.

For each target in the queue:
1. Skip if already OPEN.
2. Run `idealnessSearch(targetX, targetY)` (see §5) which fills
   `parentDirection[]` and populates `startSearchAt` with the seed
   cells from which the outward BFS starts.
3. Run an outward BFS from those seed cells, walking the
   `parentDirection` pointers backward. For each cell popped:
   * If already OPEN with pathlength < `currentPathLength`, skip.
   * Else mark status=OPEN, set `pathlength = currentPathLength`, set
     `idealness = searchedBestIdealness`.
   * If we've reached the target, drain queues and break.
   * Otherwise enqueue cells whose direction bits appear in this cell's
     `parentDirection` mask (lines 172–183). This respects the parent
     pointers established by the idealness search.
4. Increment `currentPathLength` after each frontier sweep; swap
   queues.

---

## 5. `idealnessSearch(targetX, targetY)` (lines 243–309)

BFS outward from `targetX, targetY` across all non-WALL cells, tracking
the best idealness encountered. Three states:
* Pure-INVALID cells have no pathlength; they contribute their raw
  `idealness(x,y)` value to the running best.
* Pre-existing OPEN cells contribute `idealness[x,y]` AND their
  pathlength adds to the cumulative distance — these are the cheap
  anchors (line 269).

Key: the `searchedBestIdealness` seen so far AND its
`searchedCurrPathlength` determine which cells can still improve the
score. Cells whose total cost (`distanceExplored + pathlength`) exceeds
`currentBest` are pruned (line 267, 270).

`startSearchAt` holds every cell that *tied* the best-idealness-at-best-
path-length, including all siblings on the same idealness isoline
(lines 279–281).

Parent-direction bookkeeping (lines 283–289): for each neighbor that
hasn't been visited at a shorter depth AND is non-WALL, we mark the
back-pointer from neighbor → this cell. That back-pointer chain is what
`validate()` walks.

After the search, `validate()` reconstructs pathlengths along the
chains in BFS layers.

---

## 6. `getStep(unitX, unitY, prevdirection)` — the entry point (lines 345–412)

Constants used here:
* `SPAWNED = 0`, `HORIZONTAL = 1`, `VERTICAL = 2` (lines 351–353).
* `prevdirection` is `NavigationComponent.lastMove`; set by
  `NavigateToEdgeSystem.moveComponents` as
  `lastMove = (nextTile[1] == pos.y) ? HORIZONTAL : VERTICAL;`
  (NavigateToEdgeSystem.java:44).

### Step 6.1 — Populate the candidate list
Push the unit's current tile into both `requiresValidation` and
`possibleSteps`. Then, for each of the 4 neighbors (in NEIGHBOR_X/Y
order: +x, −x, +y, −y):
* If neighbor is INVALID → push into `requiresValidation` (line 363).
* If neighbor is NOT a wall → push into `possibleSteps` (line 366).

### Step 6.2 — Run `validate(requiresValidation)`
This brings any INVALID neighbors (and the unit's own tile if invalid)
up to a consistent OPEN state with correct `pathlength`.

### Step 6.3 — Filter by minimum pathlength (two passes, lines 371–388)
* First pass: scan candidates, find the lowest `pathlength` value.
  (First pass just *records* the minimum; it does NOT filter — each
  candidate is re-pushed regardless of whether it's the min, because
  the loop increments the writer index to rewrite in place. Read the
  bytecode carefully: `if (...pathlength > s) continue; s = ...;` then
  `this.possibleSteps.push(stepX, stepY);`. The `continue` skips the
  re-push only when pathlength *is strictly greater* than the running
  min, so cells with pathlength *≤* min get re-pushed AND the min gets
  updated as we go. Effectively this compact the list to the running
  min of everything seen so far — see **§6.7 CAVEAT**.)
* Second pass: keep only cells whose pathlength equals the final `s`.

### Step 6.4 — Zigzag filter (lines 389–398, only if `size > 1`)
**Drop** candidates whose next direction matches the previous move's
axis. Explicitly:
```
if (prev == VERTICAL && stepX == unitX)   drop;  // vertical-after-vertical
if (prev == HORIZONTAL && stepY == unitY) drop;  // horizontal-after-horizontal
if (prev == SPAWNED && stepY == unitY)    drop;  // first move prefers vertical
```
"Stay put" (stepX==unitX && stepY==unitY) always matches, so it's
always dropped unless `size == 1` (bypass).

### Step 6.5 — Direction-toward-target filter (lines 399–408, only if
`size > 1`)
**Drop** candidates where BOTH `stepX != unitX + direction[0]` AND
`stepY != unitY + direction[1]`. In words: keep only steps whose x or y
increment matches the target-edge direction vector.

### Step 6.6 — Return
`possibleSteps.next()` pulls the first remaining; returns `(currX,
currY)`. With `size == 1`, this is the unique survivor. With `size > 1`
at this point, the queue order — which is the push order from step
6.1 (neighbors first, in RIGHT/LEFT/DOWN/UP order, preceded by the
unit's own tile) — decides the tiebreak.

### §6.7 Filter #1 initial value — `s` starts at MAX_VALUE
CFR's variable naming made this ambiguous: the decompiled source shows
`int n = Integer.MAX_VALUE;` but uses a different-looking `s` in the
comparison. **The raw bytecode (PathFinder.class offsets 139–141)
disambiguates**: `ldc 2147483647; istore 7` — local slot 7 is `s` and
it IS initialized to `Integer.MAX_VALUE`. CFR just named the same slot
inconsistently.

So filter #1 behaves as expected: every candidate's pathlength ≤ the
running min gets re-pushed AND updates `s` downward. After this
pass, `s` holds the true minimum across all candidates. The queue
holds a suffix where each surviving entry's pathlength is ≤ the
running-min-at-the-time-it-was-re-pushed — which may include cells
whose pathlength exceeds the final `s`. Filter #2 then enforces strict
equality with the final `s`, leaving only true minima.

After filter #2, the order of survivors is the enumeration order
restricted to minima: `[self, +x, -x, +y, -y]` — this is the tiebreak
order that decides the final step when filters #3 and #4 can't narrow
further.

---

## 7. NavigateToEdgeSystem integration (lines 35–61)

Called once per frame from `Game.gameEngineLoop`. Per mobile unit:

1. If `nav.navigating == false` → skip.
2. `nav.currentMoveBuildup += nav.speed`.
3. If buildup `< 0.9999f` → skip (not enough speed accumulated).
4. `buildup -= 1.0f` then call
   `nextTile = pathfinder.getStep(pos.x, pos.y, nav.lastMove)`.
5. Update `nav.lastMove = (nextTile.y == pos.y) ? HORIZONTAL : VERTICAL`.
6. Compute `delta = nextTile − pos`.
7. If `delta == (0,0)`:
   * `nav.navigating = false`
   * `nav.speed = 0`
   * `nav.finishedNavigating = true`
   * Iterate `nav.navigationTargetLocations`; if any matches current
     position → `nav.reachedTarget = true`. CONTINUE TO NEXT UNIT.
8. Else `teleportBody(unit, delta)`: update position, increment
   `stepsTaken`, emit GlobalMove event, update colliders.

**Only a same-tile return from getStep sets
`finishedNavigating=true`.** Moving to a new tile never does, even if
that tile is the last reachable one — exactly the fix we already
applied to `system_move`.

---

## 8. MapBounds — edge tile geometry (MapBounds.java lines 24–43)

With boardSize = 28:
* **EDGE_TOP_RIGHT (0):** `(14+i, 27−i)` for i ∈ [0,14) → tiles
  `(14,27), (15,26), …, (27,14)`. Direction `(+1, +1)`.
* **EDGE_TOP_LEFT (1):** `(13−i, 27−i)` → `(13,27), (12,26), …, (0,14)`.
  Direction `(−1, +1)`.
* **EDGE_BOTTOM_LEFT (2):** `(13−i, i)` → `(13,0), (12,1), …, (0,13)`.
  Direction `(−1, −1)`.
* **EDGE_BOTTOM_RIGHT (3):** `(14+i, i)` → `(14,0), (15,1), …, (27,13)`.
  Direction `(+1, −1)`.

Our `sim/map.py::edge_tiles` matches this — verified earlier.

---

## 9. What this implies for the port

`algos/athena/sim/pathfinder.py` needs to be replaced with a class that:

1. Maintains **4 persistent PathFinder instances**, one per target edge,
   with all of the state arrays listed in §1.
2. Hooks `put(x,y)` on wall ADD and `remove(x,y)` on wall REMOVE — these
   fire during `apply_deploy_actions` (spawns) and during
   `clear_destroyed` (deaths during the action phase).
3. Exposes `get_step(x, y, prev_direction, edge)` that returns the
   next tile coordinates using the 6-step flow in §6, including the
   filter-#1 ordering bug.
4. Is called from `system_move` INSTEAD of the stateless BFS-navigate
   we use today. Remove `sim.pathfinder.navigate()`.

After the port, **no `m.path` list is needed on Mobile** — the engine
doesn't maintain a path; it just asks the pathfinder for one step at a
time. Simplify accordingly.

`apply_deploy_actions` needs: call `put()` on every structure spawn.
`clear_destroyed` needs: call `remove()` on every structure death.

---

## 10. Residuals this spec is expected to fix

Based on the diff_turn analyses done on m15303042 turn 70 and
m15302627 turn 50:
* Wall dies mid-turn → current port re-navigates with a stateless BFS
  → picks a different cell order → different ultimate path → wrong
  damage distribution. Replacing the stateless BFS with the engine's
  stateful `put`/`remove`/`getStep` model makes the tiebreak orders
  identical.

After the port, validate.py should report `max_hp_err = 0` on every
turn. If anything remains, the residual source is NOT the pathfinder
and we investigate it separately.
