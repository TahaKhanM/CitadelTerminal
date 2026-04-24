# Engine decompilation — top gotchas

One-page cheat sheet of the bytecode-archaeology surprises that would
have silently broken SimCore if we had relied on docs or CFR output
alone. Each has a source citation so future debugging has a ground-
truth pointer.

## 1. `coresForPlayerDamage` is a legacy key — absent from live config

**Symptom:** Cluster B/B.1 — "SP income" column diverged by ~1 SP per
damage-dealing turn. Docs said "+1 SP per HP damage dealt last turn".

**Truth:** `Config.java:311` only reads `coresForPlayerDamage` in the
pre-Season-5 mechanics branch. The live server config has no
`coresForPlayerDamage` key. `BreachSystem.java:27` is the only live
SP-bonus mechanism: `addToMetal(metalForBreach)` per breacher.

**Fix:** SimCore's config loader drift-guards the absence of the key
and disables the rule; CLAUDE.md + GAME_RULES.md re-worded "damage
bonus" → "breach bonus".

## 2. `processUnitInfoInPlace` copy-then-patch on upgrade

**Symptom:** Support upgrade cost: local competition config says
`upgrade.cost1 = 2`. Applying that directly would charge 2 SP per
upgrade. Live games clearly charge 4 SP.

**Truth:** `Config.java:99-176`: the engine loader only overrides base
fields when the upgrade dict contains them. Live server config's
`unitInformation[1].upgrade` omits `cost1` → base `cost1 = 4.0`
inherits. The local competition config's `upgrade.cost1 = 2` was
wrong, but our Cluster B.1 loader mirrored engine's copy-then-patch
instead of the local file, sidestepping the bug.

**Fix:** `SimConfig._fill_unit_config` / `_patch_upgrade` in
`config.py` implement the engine's fallback exactly. Any new unit-
config introspection MUST route through these helpers.

## 3. Targeting tiebreak 4 — CLOSEST to attacker's edge, not deepest

**Symptom:** Picker occasionally chose the "wrong" mobile under tight
ties — the one deepest into player territory rather than the one just
arriving.

**Truth:** `TargetAndAttackSystem.java:80-82` minimizes `|target.y -
attackerStartY|` where `attackerStartY = 0` (player-0) or `28`
(player-1). For a player-0 turret that means smallest `y` — CLOSEST
to the attacker's own edge, not the deepest defender.

**Fix:** Rewrote the cascade in `system_attack` and re-worded
TARGETING_AND_PATHING.md + GAME_RULES.md.

## 4. Targeting tiebreak 5 — LARGER |x − 13.5|, not smaller

**Symptom:** Symmetric-tie misses.

**Truth:** `TargetAndAttackSystem.java:83-85` uses
`fartherFromCenter = newDistCenter > distanceToCenter` — larger wins.
CFR decompilation can mask the comparison direction; must read the
Java source + confirm against behaviour.

## 5. Targeting tiebreak 6 — largest `Integer.parseInt(uid)`, not most-recent

**Symptom:** Under true ties, picker grabbed the first-seen candidate.

**Truth:** `TargetAndAttackSystem.java:87`:
`Integer.parseInt(newgid) > Integer.parseInt(targetgid)`. Almost
always correlates with most-recent (gids monotonically increase), but
the comparison is strictly the parsed integer.

**Fix:** All sim uids are numeric strings; the tiebreak compares as
`int(uid)`.

## 6. `finished_navigating` is a same-tile-return gate only

**Symptom:** Mobiles occasionally self-destructed one frame too early.

**Truth:** `NavigateToEdgeSystem.java:46-56` sets
`finishedNavigating=true` only when `getStep` returns the SAME tile
(delta=0). A length-1 path that moves one tile does NOT set it — the
unit continues next frame.

**Fix:** `system_move` sets `finished_navigating` only on delta=0.
Phase 1.F was the commit.

## 7. `reached_target` is a SPECIFIC-edge check, not "any edge"

**Symptom:** A p2 Interceptor "breached" at a BOTTOM_RIGHT tile it
was actually trying to dead-end-SD on.

**Truth:** `NavigateToEdgeSystem.java:50-54` iterates the mobile's
own `navigationTargetLocations` (populated at spawn based on the
mobile's target edge), not any edge. BreachSystem requires BOTH
`finishedNavigating && reachedTarget`.

**Fix:** `system_breach` validates against the mobile's specific
target-edge tile set.

## 8. JVM HashSet ordering permeates 4 event buckets

**Symptom:** `event_damage`, `event_selfDestruct`, `event_shield`,
`event_death` buckets would never match exactly between SimCore and
replay — order varied by JVM run.

**Truth:** `ProximityArena.java:42-53` uses
`Set<Pair<ProximitySensor>>`. Java HashSet iteration order is driven
by `Object.hashCode()` (HotSpot's thread-local PRNG, lazy-assigned on
first hashCode read) + HashSet bucket layout. Not game-semantic.

**Fix:** Cluster H — accept multiset-equality on canonical keys for
the 4 affected buckets (`event_canonical.py`). Cluster H-refinement
added 1-ULP-float32 tolerance on the downstream `p{1,2}_mobiles`
column for single-reorder HP accumulation drift. See `SIM_PARITY.md`
§ CASCADE gate.

## 9. `Float.round` vs Python `round` on halfway values

**Symptom:** Potential 1-ULP drift on SP/MP accumulation when an
input lands exactly on a half-tenth boundary.

**Truth:** `PlayerStats.java:151-153` uses `Math.round(float) → long`:
Java rounds half-up. Python's `round()` rounds half-to-even. In
practice SP/MP deductions are whole numbers × 10 ∈ integers, so no
halfway inputs actually land on the boundary — but any new
resource-mutation site MUST avoid Python `round()` on
float64-widened inputs.

**Fix:** `_round01` in `pysim.py` casts through `np.float32` before
the `round` + re-cast. Guarded by `test_float32_propagation.py`.

## 10. HP-<=-0 gate ends the action phase even with walkers alive

**Symptom:** FAST vs INSTRUMENTED mode divergence on 17/23 replays.

**Truth:** `GameMain.runLoop:301` calls `processEndGame(false)` each
frame; when either player's HP drops to 0-or-below, the outer
`while (!this.exitGame)` breaks at the top of the next iteration.
`simulate_action_phase_iter` already had this gate; `simulate_action_phase`
was checking only `walkersAlive` and ran extra frames.

**Fix:** Phase 1.B.2 commit added the HP gate + `system_remove_own_unit`
to FAST-mode, making both modes byte-identical on final state.
