# Citadel Terminal — Complete Game Rules

This document reflects the **special-competition ruleset**, which overrides certain base-game values. Where a value differs from the base game, the competition (current) value is stated first; the base value is noted as "(base: X)" only when useful.

## 1. Game overview

Terminal is a **two-player, simultaneous-turns tower-defense game**. Both players submit commands at the same time; the engine resolves them together each turn. The map is a **diamond-shaped region inside a 28×28 grid (x,y ∈ [0,27])** — corners of the square grid fall outside play.

- You occupy the **bottom half** (y < 14). Your opponent occupies the top half (y ≥ 14). When the engine runs your algo from the top half it mirrors the coordinate system for you, so **always write code as if you are the bottom player**.
- Win by reducing the opponent's **Player Health** from 40 → 0, OR having higher HP at the end of round 100. Tied HP → lowest total computation time wins.

## 2. Starting state

| Item | Value |
|---|---|
| Player HP | 40 |
| Structure Points (SP) | 8 |
| Mobile Points (MP) | 1 |
| Turn number | 0 |

## 3. Turn structure

Every turn has three phases in order: **Restore → Deploy → Action**.

### 3.1 Restore phase

Happens at the start of every turn (except turn 0). Order of operations:

1. **MP decay**: your stored MP from last turn is reduced by **25 %** (rounded to the nearest 0.1).
2. **Base income**: you gain **+4 SP** and **+(1 + turn_number // 5) MP**.
   - Turns 0-4: +1 MP/turn
   - Turns 5-9: +2 MP/turn
   - Turns 10-14: +3 MP/turn … and so on.
3. **Damage bonus**: +1 SP per point of HP damage you dealt to your opponent last turn. (Strong snowball — scoring once is worth more than just the HP lead.)
4. **Resource-generating structures**: any Supports with resource-generation upgrade would produce here. *In the current competition config, Supports do not generate resources — they only shield.* The starter-kit config shows a `generatesResource2: 2` for upgraded Support, which is **stale/old-season** metadata, ignore it.

### 3.2 Deploy phase

You have **15 seconds** of wall clock time. After that, you take **1 damage per second** until you submit (or forever if the engine decides to give up on you).

During Deploy you can:

- **Spawn structures** (Walls, Supports, Turrets) anywhere in your half of the arena on unoccupied tiles. Costs SP.
- **Spawn mobile units** (Scouts, Demolishers, Interceptors) on your two edges (BOTTOM_LEFT or BOTTOM_RIGHT diagonal). Costs MP. Mobile units can stack arbitrarily deep on the same tile.
- **Upgrade existing structures** (Wall → 200 HP tank, Turret → stronger/longer-ranged, Support → bigger shield / longer range). Costs additional SP (see `UNITS_REFERENCE.md`).
- **Mark structures for removal.** Marked structures are removed at the END of the upcoming action phase and you receive a refund:

  ```
  refund = 0.9 × InitialCost × (RemainingHealth / OriginalHealth)
  ```

  (rounded to nearest 0.1). You don't get the structure back immediately — you get SP back next Restore phase.

**Visibility during Deploy**: you see the full board AND the opponent's surviving structures, but **not** the opponent's mobile or structural deployments for this turn. Both sides commit blind.

### 3.3 Action phase

The engine resolves the turn in **discrete frames** until every mobile unit has either reached the opposite edge or been destroyed. Each frame, in order:

1. **Shielding**: every Support grants shield to any friendly mobile unit that just entered its range AND has not already been shielded by that specific Support.
2. **Movement**: every mobile unit whose frame count matches its speed threshold attempts to step. A unit with no legal move (path totally blocked) self-destructs (rules in §5).
3. **Attack**: every unit with damage rolls targeting logic (§4) and deals damage to exactly one target.
4. **Cleanup**: units whose HP ≤ 0 are removed.

**Mobile-unit speed** is *frames per tile*: Scout moves once every 1 frame, Demolisher once every 2 frames, Interceptor once every 4 frames. (The `unit.speed` field in `game-configs.json` is the *inverse* — `1/framesPerTile` — so Scout speed=1, Demolisher=0.5, Interceptor=0.25.)

**Scoring**: when a mobile unit reaches the opposite edge it:
- Reduces opponent HP by **1**.
- Grants you **+1 SP** in your next Restore phase (this stacks with the damage-bonus SP).
- Disappears from the arena.

## 4. Targeting logic

All units (structures AND mobiles) that deal damage share the same target-selection rules. Each frame, a unit's list of candidate targets starts as *every enemy unit in range*, then is filtered in priority order until one remains:

1. **Prefer mobile units over structures.** A Turret with any mobile enemy in range never targets a structure; a mobile unit that can also hit structures prefers the mobile ones too. (Scouts/Demolishers can hit both; Interceptors CANNOT damage structures at all.)
2. **Nearest target.** Euclidean distance.
3. **Lowest remaining HP.**
4. **Furthest into your own half** (deepest Y toward your side; attackers pick defenders that have advanced the most).
5. **Closest to an edge** (larger `|x − 13.5|`).
6. **Fallback**: most recently created unit.

Each unit deals damage **at most once per frame**. "Overkill" damage is discarded — a 5-HP unit taking 8 damage does not let the 3 extra damage carry over to another unit.

**Mobile unit damage flavors**:
- `attackDamageWalker` (damage_i in `GameUnit`): damage vs other mobile units.
- `attackDamageTower` (damage_f): damage vs structures.
- If one of these is 0, the unit simply cannot target units of that category.

## 5. Movement & pathing

### 5.1 Path choice

Mobile units target the opposite edge. Each step they pick the neighbor tile (up/down/left/right — no diagonals) that is:

1. Closest to the destination in **step count** (BFS distance through unblocked tiles).
2. Tie-broken by **alternating movement axis**: prefer the opposite axis from the previous step (if previous step was vertical, prefer horizontal next). On the very first step, prefer vertical.
3. Further tie-broken by preferring the tile that's **toward the target corner of the destination edge** (TOP_LEFT vs TOP_RIGHT matters — choose the matching x-direction).

Units "zig-zag" naturally from this rule set. The path is **recomputed dynamically** during the action phase — destroying a structure mid-action can reroute a unit partway through its journey, occasionally causing it to double back.

### 5.2 Destination fallback & self-destruct

If the target edge is UNREACHABLE (fully walled off), the unit instead aims for:

1. The reachable tile with the **greatest Y** into enemy territory.
2. Among those, the one closest to its target edge (biased toward the corner side).

Once it reaches that deepest tile and has nowhere to step further, it **self-destructs**:

- **Range**: 1.5 Euclidean (hits the 4 orthogonal neighbors + the 4 diagonals, effectively — any enemy within 1.5 of the SD tile).
- **Damage**: equal to the unit's **starting** HP (Scout 15, Demolisher 5, Interceptor 40). Applies only to enemy units.
- **Requirement**: the unit must have moved at least **5 tiles** since spawn. Otherwise SD deals no damage (the unit still dies).
- **Attack on death**: the unit still performs its normal attack on the frame it self-destructs.

This matters tactically:
- A trapped Interceptor deep in the enemy base is a 40-damage bomb — intentional trap pathing is a known strategy.
- An Interceptor that self-destructs too early (<5 tiles) just wastes MP.
- Scouts/Demolishers self-destructing usually isn't worth the loss of forward damage.

## 6. Resources

- **Structure Points (SP, resource index 0)**: used for every structure placement, upgrade, and is refunded (partially) when you remove your own structures.
- **Mobile Points (MP, resource index 1)**: used for every mobile unit deploy. Decays 25 %/turn — stockpiling beyond 2 turns loses ~44 % of the hoard.

**Income per turn (Restore):**

```
SP_gained = 4 + HP_damage_dealt_last_turn + scoring_bonuses
MP_gained = 1 + (turn_number // 5)
MP_stored = round(MP_stored_last_turn * 0.75, 1) + MP_gained
```

## 7. Scoring and ranking specifics

- HP damage is the only win-condition signal. You can lose in a tiebreak by computation time even at equal HP.
- Every point of HP you take from the opponent → 1 SP next turn. Large attack waves compound.
- Every point of HP you lose → opponent gets +1 SP. Letting through scouts is a bigger economic loss than it looks.

## 8. Game end

- Round 100 completes → higher HP wins.
- HP ties at round 100 OR both hit 0 on the same frame → lower total compute time wins.
- Single-side HP ≤ 0 → other player wins immediately.

## 9. Rule-change diff (base game → this competition)

| Unit | Changed field | Base | Competition |
|---|---|---|---|
| **Wall** | Upgrade cost (SP) | 0 | **2** |
| **Support** | Base HP | 30 | **1** |
| **Support** | Upgraded shield per unit (base component) | 4 | **1** |
| **Turret** | Base HP | 75 | **60** |
| **Turret** | Base damage | 5 | **6** |
| **Turret** | Upgraded damage | 16 | **20** |
| **Turret** | Upgraded HP | 75 | **100** |

Scout, Demolisher, and Interceptor stats are **unchanged** from base game values.

See [`UNITS_REFERENCE.md`](UNITS_REFERENCE.md) for the full per-unit stat tables with all numeric values.
