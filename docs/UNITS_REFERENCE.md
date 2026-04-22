# Units Reference — Competition Values

All values below reflect the **special-competition ruleset**. They are what the server config delivers at game start. The starter-kit `game-configs.json` is out-of-date and must NOT be treated as authoritative.

Shorthand constants in code:
```
WALL = "FF"          SCOUT = "PI"
SUPPORT = "EF"       DEMOLISHER = "EI"
TURRET = "DF"        INTERCEPTOR = "SI"
REMOVE = "RM"        UPGRADE = "UP"
```

Resource indices (used with `GameState.get_resource()` / costs tuples):
```
SP = 0   (Structure Points — for structures)
MP = 1   (Mobile Points — for mobile units)
```

---

## Mobile units (attackers)

Deployable only on your two arena edges (BOTTOM_LEFT / BOTTOM_RIGHT diagonals). Multiple can stack on the same tile. Disappear from the arena after reaching the opposing edge (scoring) or being destroyed.

| | Scout (PI) | Demolisher (EI) | Interceptor (SI) |
|---|---|---|---|
| Cost (MP) | **1** | **3** | **1** |
| Starting HP | **15** | **5** | **40** |
| Attack range | **3.5** | **4.5** | **4.5** |
| Damage vs mobile | **2** | **8** | **20** |
| Damage vs structures | **2** | **8** | **0** ✱ |
| Speed (frames per tile) | **1** (fastest) | **2** | **4** (slowest) |
| Breach damage (player HP) | 1 | 1 | 1 |
| Self-destruct damage | 15 | 5 | 40 |
| Self-destruct range | 1.5 | 1.5 | 1.5 |
| Self-destruct min travel | 5 tiles | 5 tiles | 5 tiles |

✱ Interceptors **do not deal damage to structures**; they only attack other mobile units.

**Per-MP damage potential** (target is the enemy's edge defense line, ignoring death along the way):
- Scout: 2 dmg / 1 MP = 2.0 dmg per MP (and breaches for +1 HP if it survives)
- Demolisher: 8 dmg / 3 MP ≈ 2.67 dmg per MP (long range — deals damage from afar without dying as quickly)
- Interceptor: 20 dmg / 1 MP (only vs mobile). For structure damage: 0.

**Per-MP HP potential** (crude tank stat):
- Interceptor: 40 HP / 1 MP = 40 (best wall-of-meat)
- Scout: 15 HP / 1 MP = 15
- Demolisher: 5 HP / 3 MP = 1.67 (fragile glass cannon)

**Key tactical notes:**
- **Scouts** are your primary scoring unit. Mass-spawn them on the side of the board where enemy turret coverage is weakest; they're fast enough to survive a short run. Single scouts are cheap suicide probes.
- **Demolishers** sit at range 4.5 and nuke structures (8 dmg) from 4 tiles out. Speed 2 means they move half as fast as Scouts — usually paired with a wall line so they tank from long range. Their 5 HP makes them die fast when turrets reach them.
- **Interceptors** are the defensive mobile unit — 40 HP + 20 dmg vs mobiles + 0.25 speed (slow). Useful for early-game stalling and for destroying enemy rushes. Cannot damage structures, but their 40-HP/5-tile self-destruct can dumpster a pack of enemies trapped in your lines. One common aggressive use: channel them into an enemy dead-end for the 40-dmg self-destruct.

---

## Structures (defenders)

Deployable anywhere on your half of the arena on unoccupied tiles. Block movement (both sides). Persist across turns. Cannot stack. Only Turrets attack.

### Wall (FF)

| | Base | Upgraded |
|---|---|---|
| Cost (SP) | **1** | **+2** (so 1 total base + 2 upgrade = 3 total SP spent) |
| Starting HP | **60** | **200** |
| Damage | — | — |
| Role | Path manipulation, tanking hits for turrets | Same role with much more survivability |

Walls have the best HP/SP ratio of any unit in the game (60 HP / SP base; 66.7 HP/SP fully upgraded). Upgrade cost was 0 in the base game and **2 SP in this competition** — do not bank on free upgrades.

### Support (EF)

| | Base | Upgraded |
|---|---|---|
| Cost (SP) | **4** | **+4** (4 base + 4 upgrade = 8 total) |
| Starting HP | **1** ⚠️ | 1 (no HP change) |
| Shield range | 3.5 | **7** |
| Shield per mobile unit | **3** (flat) | see below ⚠️ |

⚠️ **Support HP is 1** in this competition (base game: 30). A single attack from anything kills it. Either protect it with walls or accept it as a one-shot-shield dispenser.

⚠️ **Upgraded shield per unit — how to read the docs:**

The competition docs describe the upgrade two places, which must be combined:

1. **Doc 4 (Structure Units table, upgrade cell)**: *"Range increased to 7 units. Base shielding increased to 4. (0.3 × Y position) additional shielding."* — i.e. base-game upgrade formula is `4 + 0.3 × Y`.
2. **Doc 1 (Rule Changes for Special Competition, Support row)**: lists **`Upgraded Shield Per Unit: 1`** for the competition (base game value was 4).

Combining them, the competition upgraded Support most likely grants **`1 + 0.3 × Y_position`** shield per mobile unit: Doc 1 overrides the "4" base shield component to "1", and the `0.3 × Y` positional bonus is not mentioned as changed, so it is assumed unchanged.

Practical consequences assuming the combined formula:
- At Y=0: `1 + 0` = **1 shield/unit** (WORSE than a base Support's flat 3).
- At Y=7: `1 + 2.1` = **3.1 shield/unit** (roughly break-even with base).
- At Y=13 (your back row): `1 + 3.9` = **4.9 shield/unit** (best).

**Implication**: don't upgrade Supports deployed near your front (Y<7) — you'd actively lose per-unit shielding. Only upgrade Supports that sit deep in your territory.

⚠️ There is a non-trivial alternative reading where the competition's upgraded Support is a **flat 1 shield/unit** (i.e., the `shieldBonusPerY: 0.3` field was also zeroed out). Doc 1 doesn't explicitly say one way or the other. If you want certainty, read the server-delivered `self.config["unitInformation"][1]["upgrade"]` dict in `on_game_start` and check for `shieldBonusPerY`.

**Shielding rules:**
- A Support shields each mobile unit **once** — when that unit first enters its range.
- There's no cap on total shield a unit can accumulate across multiple Supports.
- Shield is added to HP (technically raises current health above max_health).
- Only friendly mobile units get shielded.

### Turret (DF)

| | Base | Upgraded |
|---|---|---|
| Cost (SP) | **2** | **+4** (6 total) |
| Starting HP | **60** | **100** |
| Attack range | **2.5** | **3.5** |
| Damage per frame | **6** | **20** |

Turrets fire every frame at one target (targeting rules in `TARGETING_AND_PATHING.md`). They only damage enemy **mobile units** (never enemy structures).

**DPS math:**
- Base Turret: 6 dmg/frame. Kills a Scout (15 HP) in 3 frames. Kills a Demolisher (5 HP) in 1 frame.
- Upgraded Turret: 20 dmg/frame. Kills a Scout in 1 frame. Kills an Interceptor (40 HP) in 2 frames.

**Range math:**
- Base range 2.5 means a Turret at [13,11] covers roughly [11..15, 9..13] — a 5×5ish diamond.
- Upgraded range 3.5 extends that to ~7×7. Upgrading a Turret roughly doubles its coverage area.

**Upgrade value**: SP-for-SP an upgraded Turret is MUCH stronger than two base Turrets (6 SP → 20 dmg + range 3.5 vs. 4 SP → two separate 6-dmg range-2.5 turrets). Mid/late game, upgrading is almost always the right call; early game you spread cheap turrets to cover area.

---

## Removal (RM) & Upgrade (UP)

`"RM"` and `"UP"` are not deployable units — they're *modifiers* sent in build commands:

- **Remove**: mark your own structure for removal. It will be destroyed at end of action phase. Refund = `0.9 × InitialCost × (CurrentHP / StartingHP)` rounded to 0.1, given as SP next Restore.
- **Upgrade**: spend the upgrade cost on an existing structure to apply upgraded stats. Current missing-HP carries over (upgrading a Turret at 10/60 HP leaves it at 50/100 HP after upgrade).

Both are used via `game_state.attempt_remove(locs)` and `game_state.attempt_upgrade(locs)`.

---

## Unit config JSON schema (reference)

Each unit in the server-delivered `config["unitInformation"]` list has these fields (non-exhaustive):

| Field | Meaning |
|---|---|
| `shorthand` | `"FF"`, `"EF"`, `"DF"`, `"PI"`, `"EI"`, `"SI"`, `"RM"`, `"UP"` |
| `display` | Human-readable name |
| `unitCategory` | 0 = structure, 1 = mobile |
| `cost1` | SP cost (structures) |
| `cost2` | MP cost (mobile units) |
| `startHealth` | Base HP |
| `attackRange` | Euclidean attack radius |
| `shieldRange` | Support shielding radius |
| `shieldPerUnit` | Support shield per mobile unit (base) |
| `shieldBonusPerY` | Upgraded Support: extra shield per Y-position |
| `attackDamageWalker` | Damage vs mobile |
| `attackDamageTower` | Damage vs structures |
| `speed` | Inverse of frames-per-tile (Scout: 1.0, Demolisher: 0.5, Interceptor: 0.25) |
| `selfDestructDamageWalker` / `…Tower` | SD damage for mobiles |
| `selfDestructRange` | 1.5 for all mobiles |
| `selfDestructStepsRequired` | 5 |
| `playerBreachDamage` | +1 to opponent HP on reaching edge |
| `upgrade` | Nested dict of overrides applied on upgrade |
| `refundPercentage` | 0.75 in older config; competition uses the 0.9 formula stated in rules — verify live |
| `turnsRequiredToRemove` | 1 (removal effective at end of action phase) |

**Always read these at runtime from `self.config` inside `on_game_start`** — hardcoding them will break you if Correlation One patches values mid-season.
