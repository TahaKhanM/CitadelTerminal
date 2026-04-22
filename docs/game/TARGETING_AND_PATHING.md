# Targeting, Pathing, and Action-Phase Mechanics

Most strategic bugs in a Terminal algo come from misunderstanding **what the engine does during the action phase**. This file is the authoritative cheat sheet.

## The action phase, frame-by-frame

Every frame, the engine (`Game.java:167-182` `gameEngineLoop`) runs these eight systems in this exact order:

```
1. Movement       (NavigateToEdgeSystem)      – each mobile whose speed counter hit 0 steps to its chosen neighbor.
2. Collision      (CollisionSystem)           – mobiles colocated on the same tile register collision pairs.
3. Shield decay   (ShieldSystem.processShieldDecay)
4. Shield give    (ShieldSystem.processShieldGiveSystem) – Supports grant shield to newly-in-range friendlies (once per (Support, target) pair).
5. Breach         (BreachSystem)              – mobiles with finished_navigating && reached_target score −1 opp HP and +metalForBreach SP.
6. Self-destruct  (SelfDestructSystem)        – mobiles with finished_navigating && !reached_target && stepsTaken ≥ minimumSteps detonate (§5.2).
7. Attack         (TargetAndAttackSystem)     – every damaging unit picks ONE target via the cascade below and deals one attack.
8. Cleanup                                    – units with HP ≤ float32 death threshold (0.001 f) removed from gameObjects.
```

Phase continues until `gameover` fires (every player mobile destroyed or breached) or the 300-frame action-phase safety cap trips.

Edge case: **when a structure is destroyed mid-action**, the `PathFinder.remove(x,y)` call invalidates non-edge OPEN cells in the affected pathfinder; subsequent `getStep` calls re-validate lazily. Net effect: the very next step a mobile takes routes around the new hole.

## Targeting priority (the "filter list" algorithm)

All attackers — turrets and damaging mobile units — pick targets this way:

```
candidates = every enemy unit within attackRange

step 1: walker-priority split. If any walker (mobile) is in range, discard structures; an attacker
        only considers structures when no walker was damageable.
step 2: keep only candidates with minimum Euclidean distance.
step 3: keep only candidates with minimum (currentHP + shieldHP).
        (Engine: TargetAndAttackSystem.java:77 uses HP+shield, not raw HP.)
step 4: keep only candidates CLOSEST to the attacker's own edge —
        smallest |target.y − attackerStartY| where attackerStartY = 0 (player-0) or 28 (player-1).
        For a player-0 turret that means the enemy mobile with SMALLEST y
        (i.e. the one JUST entered your half, not the one deepest into it).
step 5: keep only candidates farthest from x = 13.5 (|x − 13.5| is maximal).
step 6: pick the candidate with the LARGEST integer-parsed gid (uid).
        Gids monotonically increase across the match, so this is almost always — but
        not strictly — the most-recently-created unit.
```

The attacker commits to ONE target and does its full `attackDamage` to that target's HP. Overkill is wasted.

**Implications for algo design:**

- **Health-based griefing**: a unit with just-barely-less HP than another gets focus-fired first. In high-frame fights, low-HP units clear out ahead of high-HP ones — which matters for swarms.
- **Interceptor targeting**: Interceptors have `attackDamageTower = 0`, so they NEVER target enemy structures. If you want them to damage structures, you're wrong — they exist to kill enemy mobiles (and for self-destruct plays).
- **Demolisher vs Scout priority**: a Turret will prefer whichever mobile is closer. If you drop a pack of 1-HP Scouts as bait in front of a Demolisher stack, the turrets will shoot Scouts first (step 3 tiebreak: lowest HP).
- **Creation order matters** on absolute ties. `attempt_spawn` order in your `on_turn` literally decides who gets targeted first in rare situations.

## Pathing (mobile-unit movement)

### Destination selection

1. A unit's nominal destination is the **opposite-corner edge** (BOTTOM_LEFT → TOP_RIGHT, BOTTOM_RIGHT → TOP_LEFT).
2. If NO tile on that edge is reachable, the unit aims for the deepest reachable tile (largest Y into enemy side), breaking ties toward the target corner.
3. If no forward progress possible at all, the unit is already at its destination and self-destructs.

### Per-step decision (once per `speed` frames)

For each orthogonal neighbor that is in-bounds and not occupied by a structure:

1. **Prefer shorter BFS distance to destination.**
2. Among tiles with equal BFS distance, **alternate axis**: if last move was vertical, pick a horizontal neighbor; if last was horizontal, pick vertical. On a fresh-spawn unit (no previous move), prefer vertical.
3. Among still-tied tiles, pick the one in the **target-edge direction** (e.g., heading top-right: prefer +x over −x when both have equal path length).

This zig-zag behavior is why a straight vertical wall column often funnels units into a diagonal trail — they're physically forced to swap axes each step.

### Self-destruct

Triggered (in the SelfDestructSystem frame-step) when a mobile has `finished_navigating && !reached_target && stepsTaken >= minimumSteps`. Equivalent to "it called `getStep` and got the same tile back, that tile is NOT in its `navigationTargetLocations` list, and it has moved at least `selfDestructStepsRequired` steps".

- **Range**: 1.5 Euclidean around its current tile (config `selfDestructRange`).
- **Damage**: the unit's configured `selfDestructDamageWalker` / `selfDestructDamageTower`. In this competition these values equal the unit's starting HP (Scout 15, Demolisher 5, Interceptor 40) — but the underlying mechanic is a separate config field, not live HP.
- **Minimum travel**: `selfDestructStepsRequired = 5`. If the mobile has moved fewer steps, it dies silently with 0 SD damage.
- **One final attack**: SelfDestructSystem runs BEFORE TargetAndAttackSystem in the same frame, so the unit still performs its attack this frame before being cleaned up.

**Interceptor trap play**: funnel an opponent Interceptor into a pocket deep in your base. If they walked ≥5 tiles getting there, they deal 40 SD damage to YOUR units on death. So don't build walled-off channels that let enemy Interceptors tour your base freely.

**Own-side SD play**: some algos intentionally build "lanes" that terminate at the deep enemy corner so their Interceptors dump 40 damage into enemy turret clusters. Combined with enemy Turret focus being on the Interceptor (40 HP tanks hits), this can bust open a defense.

### Pathing recomputation

The engine keeps **four persistent `PathFinder` instances, one per target edge** (`TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_LEFT`, `BOTTOM_RIGHT`). Each holds a 28×28 state array (`INVALID / OPEN / WALL`), `pathlength`, `idealness`, and `parentDirection`. Walls update these incrementally via `put(x,y)` on spawn and `remove(x,y)` on death; a wall death invalidates all non-edge `OPEN` cells globally on the affected pathfinder, which are then re-validated lazily inside `getStep` on the next call. The net effect is identical to "every unit re-evaluates its route from its current position the next step after a wall dies", but the mechanism is incremental and lazy, not per-step full recompute. See `research/engine_decompiled/PATHFINDER_SPEC.md` for the bit-exact port.

## Shielding during action phase

- Each Support examines mobile units within its `shieldRange` **every frame**.
- If a friendly unit is in range and this Support has not shielded it yet, it grants that unit `shieldPerUnit` (+ `shieldBonusPerY * y` if upgraded) of bonus HP.
- The shielded unit's `.health` now exceeds `.max_health` (the clamp is removed by the shield).
- A single Support NEVER shields the same unit twice. But N different Supports can all shield the same unit (stacking).
- There's no limit on total shield stacked on one unit.

**Placement math** (verified from live config): an upgraded Support at `[13, 13]` grants `1 + 0.7 × 13 = 10.1` per-unit shield. The range of 7 covers everything from roughly `[6..20, 6..20]` — effectively half your territory. Stacking 3 upgraded Supports at the back row means a charging Scout picks up **30+ shield** on its way out of your base (doubling its 15-HP base to effectively 45+ HP).

## Action-frame JSON (for `on_action_frame`)

The action phase streams JSON frame objects to your algo. Parse with `json.loads(turn_string)`. Relevant top-level keys:

```
turnInfo   – [phaseType, turn, ..., frameId]   (phaseType == 1 during action)
p1Stats    – [health, SP, MP, time]  (p1 = YOU in raw frames)
p2Stats    – same for opponent
p1Units    – [walls[], supports[], turrets[], scouts[], demos[], ints[], removes[], upgrades[]]
              each inner list is [[x, y, hp, ...], ...]
p2Units    – same for opponent
events     – dict of event arrays: "spawn", "death", "attack", "shield",
             "breach", "damage", "move", "selfDestruct", "melee"
```

The `events` dict is the most useful part for learning from matches. Example:

```python
def on_action_frame(self, turn_string):
    state = json.loads(turn_string)
    for breach in state["events"]["breach"]:
        location, _, _, _, owner = breach
        # owner == 1 means YOUR unit breached enemy edge; owner == 2 means enemy breached you
        if owner == 2:
            self.scored_on_locations.append(location)
```

⚠️ **`player_index` convention flip**: in the raw JSON frame, player numbers are 1 (you) and 2 (opponent). In the parsed `GameUnit.player_index`, it's 0 (you) and 1 (opponent). Writing `if breach[4] == 0` is a bug — raw-frame events use the 1/2 convention.

## Simulating forward yourself

To predict outcomes without running the engine:

1. Copy `game_state.game_map` (so you don't pollute the real one).
2. Hypothetically place your planned structures on the copy.
3. Call `game_state.find_path_to_edge([x, y], target_edge)` to see the path a mobile would take from a spawn tile.
4. For each tile on that path, compute `get_attackers(tile, 0)` — these are enemy turrets that would hit a mobile there.
5. Multiply attacker count × their `damage_i` × "frames the unit spends on that tile" (= 1 for Scout, 2 for Demolisher, 4 for Interceptor) for a rough damage estimate.

This is essentially what `least_damage_spawn_location` in the starter code does — just smarter algos simulate many hypotheticals per turn.
