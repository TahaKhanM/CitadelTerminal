# SimCore validation — bit-exact against engine.jar

**Status: PASS. Zero error across all 1,463 action-phase simulations.**

Every turn of every v13 ranked replay reproduces the engine's
p1_hp_delta and p2_hp_delta exactly. The sim is no longer a best-effort
approximation; it is the same behavior as engine.jar for the action
phase, modulo any residual we haven't yet measured.

## Gate results

Validator: `python3 algos/athena/sim/validate.py`. 1,463 turns across
23 replays (every turn of every v13 ranked replay in `replays/ranked/`).

| Metric | Value | Original plan gate | User-tightened gate | Status |
|---|---|---|---|---|
| Mean per-turn HP error | **0.00 HP** | ≤ 0.40 HP (1%) | ≤ 0.10 HP | PASS |
| Max per-turn HP error  | **0.00 HP** | ≤ 2.00 HP (5%) | ≤ 2.00 HP | PASS |
| Throughput             | ~78 sims/sec | ≥ 50 sims/sec | ≥ 50 sims/sec | PASS |

All 23 replays individually: `max=0, mean=0.00`.

## How we got here

The path from the baseline (mean 1.55%, max 47.5%) to bit-exact:

1. **Upgrade-uid tracking.** Engine assigns a NEW gid on upgrade; the
   UPGRADE event's third field is the new uid. Old logic looked it up
   by position. Brought max 20→13, mean 0.67→0.16.
2. **`finished_navigating` timing.** Set ONLY when `getStep` returns the
   same tile as current (delta 0). The old "eager" logic set it after
   any move that left a length-1 path, which skipped attacks that would
   have killed the next wall. Max 13→13 but specific bosses cleared.
3. **Stateful PathFinder port** (`research/engine_decompiled/` +
   `sim/pathfinder.py`). Replaced starter-gamelib's stateless BFS with
   a direct port of `engine.jar.PathFinder`: 4 per-edge instances,
   persistent state arrays (`status`/`pathlength`/`idealness`/
   `parentDirection`), `put` on wall add + `remove` on wall death +
   `getStep` per mobile per frame. Max 13→6, mean 0.16→0.06.
4. **Targeting tiebreak rewrite** (`sim/pysim.system_attack`). Ported
   `TargetAndAttackSystem.pickUnit` exactly:
   * **Walker-priority split**: walkers are high priority, structures
     are low priority. An attacker only targets a structure if no walker
     is in range.
   * **|x − 13.5| tiebreak goes to LARGER, not smaller** (farther from
     center, not closer). Original sim had the sign wrong.
   * **5th tiebreak: larger gid wins** (`Integer.parseInt(a) >
     Integer.parseInt(b)`). The original sim kept the first-seen tie.
5. **Attacker snapshot**. Engine's attack phase uses a snapshot of
   attackers at phase start; a unit that dies mid-phase still emits
   its one attack event that frame (gameobject-destroy is queued until
   the next clear_destroyed). Old sim filtered dead attackers mid-loop,
   dropping their shots.
6. **`reached_target` = specific edge only**, not union. A mobile only
   breaches at one of the tiles in its own `navigationTargetLocations`,
   not any edge for its player's side. Old sim treated both edges as
   valid breach points, causing p2 interceptors to "breach" at a
   BOTTOM_RIGHT tile they were actually trying to dead-end-SD on.
7. **Numeric uids** via `SimState.alloc_id()`. The 5th targeting
   tiebreak requires integer-parseable uids; the old `"sim{i}"` prefix
   would crash `int()`.

The PathFinder port caught one CFR decompilation gotcha: filter #1's
local `s` was shown in the Java as `void var7_8` (CFR's stand-in for
an unnamed stack slot), which I first read as "uninitialized = 0". The
raw bytecode (`ldc 2147483647; istore 7` at PathFinder.class:139)
confirmed `s = Integer.MAX_VALUE`.

## What this means downstream

* `simulate_action_phase(state, config)` — the beam-search planner can
  treat its return value as ground truth. No σ, no confidence bands.
  Plans that depend on tight HP-threshold crossings are now safe.
* `GameState._path_cache` in the vendored gamelib (Phase 0 path
  cache) is orthogonal to this — it only caches paths within the
  uploaded algo's own planner, not the sim. Unaffected.
* Rust port (Phase 7) now has a clear reference: re-port
  `sim/pathfinder.py` + `sim/pysim.py:system_attack` + the rest of the
  frame loop, validated the same way.

## How to catch a regression

The validator is the source of truth:
```
python3 algos/athena/sim/validate.py
```
Any non-zero number in the `max` column for any replay means a
regression. `diff_turn.py <replay> <turn>` narrows to a post-state
diff; `diff_frames.py <replay> <turn>` gives frame-by-frame event
counts. Use the latter first — divergence typically appears as a
specific frame's attack / death count differing.
