# Units Reference — Competition Values

All values below reflect the **special-competition ruleset**. They are what the server config delivers at game start, **verified against the live config extracted from an official downloaded replay (`replays/v11_official_replay_01.replay`)**. The starter-kit `game-configs.json` is out-of-date and must NOT be treated as authoritative.

> **How these were verified**: the first line of every official `.replay` file is the full live server config JSON. Run
> ```bash
> python3 -c 'import json; print(json.dumps(json.loads(open("<replay>").readline())["unitInformation"], indent=2))'
> ```
> to dump it yourself. The numbers here match that source as of 2026-04-23.

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
| Starting HP | **1** ⚠️ | **40** ⚠️ (upgrade massively increases HP!) |
| Shield range | 3.5 | **7** |
| Shield per mobile unit (base component) | **3** (flat) | **1** (flat) |
| Shield bonus per Y | 0 | **0.7 × Y** |
| Effective shield formula | `3` | `1 + 0.7 × Y_position` |

⚠️ **Base Support HP is 1** — a single attack from anything kills it. An unupgraded Support is a one-shot shield dispenser.

⚠️ **Upgrading a Support raises its HP from 1 to 40**, making it durable enough to last multiple turns. This is a big deal strategically — *upgraded* Supports can anchor your back row across an entire game; *base* Supports are effectively single-use unless walled.

### Upgraded Support shield math

**Formula (authoritative, from live config):**
```
shield_per_unit = 1 + 0.7 × Y_position
range = 7 (Euclidean)
```

Per-Y-position table:

| Y | Shield/unit | Notes |
|---|---|---|
| 0 | 1.0 | worse than base Support's flat 3 |
| 3 | 3.1 | break-even with base |
| 7 | 5.9 | clearly better than base |
| 10 | 8.0 | very strong |
| 13 (back row) | **10.1** | maximum |

**Implication**: stacking 3-4 upgraded Supports at Y=13 gives each passing Scout ~30-40 shield — more than doubling HP (Scout base 15 → shielded ~45-55). This is **much stronger than the "0.3 × Y" reading in older versions of these docs**. Support-caravan strategies are high-EV in this competition.

⚠️ Placement rule: DON'T upgrade a Support at Y<3 — the per-unit shield would be *worse* than leaving it at base. Always place upgraded Supports at Y≥7; ideally Y=13.

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

- **Remove**: mark your own structure for removal. Removal completes after `turnsRequiredToRemove` turns (NOT at end of the current action phase as earlier doc versions claimed):
  - Base structure: **2 turns** to complete removal.
  - Upgraded structure: **3 turns** to complete removal.

  Refund formula differs by upgrade state:
  ```
  base structure:     refund = 0.9 × InitialCost × (CurrentHP / StartingHP)
  upgraded structure: refund = 0.8 × InitialCost × (CurrentHP / StartingHP)
  ```

  Implications:
  - The 2-turn removal delay means you can't pivot defense on a single turn via remove-and-replace. Plan 2+ turns ahead.
  - Upgraded structures refund 11 % less — upgrade-then-remove is lossy. Don't use upgrade as a temporary boost expecting full refund.

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
