# Strategy Guide — Citadel Terminal

This is opinionated, but based on the actual mechanics: it's meant to accelerate your design intuition, not to prescribe any single winning bot.

## 1. Economy first

Every decision ultimately routes through the resource engine. Internalize these:

| Resource flow | Rate |
|---|---|
| SP income | 4/turn baseline + (damage dealt last turn) |
| MP income | `1 + turn // 5`/turn |
| MP decay | 25 %/turn |
| MP to SP conversion | 1 HP scored = +1 SP next turn |
| Wall SP/HP | 1 SP → 60 HP (base) or 3 SP → 200 HP (upgraded) — **22 % more HP/SP upgraded**, plus area denial |
| Turret SP/DPS | 2 SP → 6 dmg/frame → 6 DPS at range 2.5; 6 SP → 20 DPS at range 3.5. Upgrading ≈ **3.3× damage + 2× range area** |

Takeaways:

- **MP is use-it-or-lose-it.** You lose 25 % of any MP you don't spend. Hoarding for "next turn's big attack" costs you exponentially.
- **Every turn you score, you bank SP.** Even an unopposed 1-HP Scout push nets you 2 SP (damage bonus + scoring bonus) — basically free structure income.
- **Every turn the opponent scores, they bank SP.** Conversely, leaking 1 HP to them funds their next Turret.
- **Early vs late income**: at turn 5, you go from 1 MP/turn to 2 MP/turn. At turn 10, 3 MP/turn. Most algos plan their first "real" attack around turn 5-7 when 2-3 MP/turn turns into 5-10 MP cumulative. Pre-turn-5, interceptors to stall is often optimal.

## 2. Defense archetypes

### 2.1 Corner turrets + funnel walls ("V" or corner-pack)

Put strong Turrets at `[0, 13]` and `[27, 13]` (your back corners), and angle walls to funnel attackers through their coverage. This is the default starter approach — proven solid but beatable by demolisher-walls at range 4.5.

### 2.2 Horizontal "wall" defense

A line of walls at y=11 or y=12 across the full board, with turrets at the gaps. Forces enemy mobiles to pick a narrow gap where your turrets concentrate fire. Weakness: demolishers can sit at y=14 and chew your wall line from range 4.5 without ever being in range of your turrets.

### 2.3 Double-layer "keep"

Two wall rows (e.g. y=12 AND y=10) with upgraded Turrets sandwiched inside. Very sturdy but expensive; a typical version costs 20-30 SP to stand up. Good mid-game upgrade path once you have 8+ SP/turn income.

### 2.4 Asymmetric "crumple zone"

Build strong on one side (e.g., left), barely anything on the right, and watch where the opponent attacks. Once they commit, reactively build against their path. Very SP-efficient if you're a good reader; gives away games if you misread.

### 2.5 Reactive turrets on breach points

The starter strategy does this: record where enemy units scored (via `on_action_frame`'s `breach` events) and plant a Turret above that tile. Simple, effective as a baseline — but slow to adapt (you've already lost the HP before the Turret exists).

## 3. Attack archetypes

### 3.1 Scout rush

Spawn 10+ Scouts on one edge tile. Cheap (1 MP each), fast (1 frame/tile), and they stack so turrets only hit one at a time (though overkill damage is wasted — a 20-damage upgraded Turret still kills exactly one 15-HP Scout per frame, and the extra 5 damage is discarded). Best when enemy has sparse Turret coverage on your chosen side.

**Math**: 15-HP Scouts die to an upgraded Turret in 1 frame. Against one upgraded Turret on their path, you'll lose ~7 Scouts in a 7-tile danger zone. A 10-Scout rush gets 3 through. You need 10 MP minimum — that's ~5 turns of income at mid-game.

Variant: Scouts-on-both-sides. If the enemy doubles down on defending one edge, the opposite edge is probably undefended.

### 3.2 Demolisher line

Long-range attackers (range 4.5) sit on y=11 behind a line of walls. Because they're protected, they chew through enemy front-line structures for several turns. Best when opponent has a stationary defense you can shred.

Starter algo implements a basic version: build a wall row on y=11, spawn demolishers at [24, 10].

**Math**: Demolisher has 5 HP and 8 dmg (per Doc 4). Against an enemy unupgraded Turret (6 dmg/frame), the Demolisher dies in 1 frame but gets off 8 dmg before dying. Efficient ONLY if wall-protected — raw, you trade 3 MP for 8 structure damage. With 1-2 wall layers between the Demolisher and the Turret, a single Demolisher can take out a base Turret (60 HP → 8 × ~8 frames); two or three are needed for an upgraded Turret (100 HP).

### 3.3 Interceptor stall / self-destruct bomb

Interceptors have 40 HP and don't damage structures. Two uses:

1. **Early-game defense**: enemy sends a Scout pack; your Interceptor kills them with 20 dmg/frame vs mobile.
2. **Self-destruct deep in enemy base**: if you can path an Interceptor into an enemy dead-end past 5 tiles of travel, it deals 40 damage to every enemy unit in range 1.5 when it SDs. Brutal against dense Turret clusters.

The trick with #2 is getting one to actually reach the dead-end. Usually combined with a removed-wall setup: wall off a path, opponent builds around it, you remove the wall mid-deploy so the Interceptor's pathing changes and it can enter.

### 3.4 Support-stacked Scout "caravan"

Stack 3-4 upgraded Supports on back row (`[12,13] [13,13] [14,13] [15,13]`). Per the most likely reading of the competition rules (see `UNITS_REFERENCE.md`, Support section), each upgraded Support at Y=13 grants `1 + 0.3·13 = 4.9` shield per Scout passing through. Four of them on the back row stack to ~19.6 extra HP per Scout — roughly doubling a 15-HP Scout's durability.

⚠️ If the alternative reading is correct and upgraded Supports grant only a flat 1 shield/unit (no Y bonus), this strategy is much weaker (4 shield total per Scout from 4 Supports — not game-changing). Before committing heavy SP to this plan, confirm by inspecting the actual config at runtime: `self.config["unitInformation"][1]["upgrade"]`.

Cost: 4 × (4 + 4) = 32 SP for 4 upgraded Supports. Expensive — plan for ~5 turns of income to set up. Pays off across many attack waves if the Y-bonus formula is in effect.

### 3.5 "Remove + replace" for SP efficiency

Marking your own Turret for removal refunds 90 % × `(CurrentHP/MaxHP)` of its SP. A nearly-dead Turret might refund 1 SP, but a fresh Turret refunds 1.8 SP. Some algos rotate turret placements based on which lane the opponent is attacking: remove the turret they're not pressuring, spawn one where they are. Break-even point is roughly: remove if health > 20% of max (otherwise refund is pitiful).

## 4. Resource allocation framework

At each turn your algo essentially solves:

- **Threat** = MP damage the opponent could deal this turn + next.
- **Opportunity** = expected HP we can score this turn at current MP.
- **Value of an SP** = `(damage-per-SP delivered) - (damage-per-SP received)` change if spent.
- **Value of an MP** = `expected HP dealt now × 1 + bonus SP × 1` minus 25 % decay if held.

A concrete heuristic many algos use:

```
if opponent_visibly_weak_left_side and my_MP ≥ 10:
    scout_rush(left_spawn)
elif my_SP ≥ 6 and I_can_upgrade_a_turret:
    upgrade_turret(critical_spot)
elif my_SP ≥ 4:
    plug_reactive_defense_hole()
else:
    save()
```

Refine with: scoring prediction (simulate the Scout rush before committing), and enemy-pressure estimation (if they have MP stockpiled, prioritize defense).

## 5. Action-frame learning patterns

`on_action_frame` gives per-frame event data. High-value learning signals:

- **breach events**: where did enemy score? → place reactive Turrets.
- **death events** by type and location: are your walls dying at the same place every turn? → reinforce or reroute.
- **attack events**: map which tiles your units spent time on. High time-on-tile × high inbound damage = bad spawn corridor.
- **damage events**: tally damage dealt by unit type. If Demolishers you send consistently deal < 10 dmg before dying, they're under-tanked — add walls or send in pairs.

Keep `on_action_frame` FAST — it runs 100s of times per turn. Don't do heavy computation inside it; just record data for `on_turn` to analyze next turn.

## 6. Known meta (from community observation)

- **"Funnel boss"** bots wall off everything except one narrow corridor with stacked Turrets in the corridor. Beat by mass-Scout rushes on a DIFFERENT corridor (force them to re-open) OR Demolisher range attacks that never enter the corridor.
- **"Reactive" bots** rebuild where they were breached. Beat by alternating attack sides — or by back-to-back same-side attacks (the defender builds, but you keep hammering).
- **"Greedy Support"** bots lean heavy on upgraded Supports and Scout swarms. Beat by demolishing their Supports (low HP!) with an early Demolisher strike, then enjoying their un-shielded follow-up.

## 7. Anti-patterns to avoid

- **Don't wall your own spawn edges.** Placing a Wall on the BOTTOM_LEFT or BOTTOM_RIGHT diagonal prevents you from spawning there next turn. If your strategy depends on spawning from corners, keep those corners open.
- **Don't over-build Supports without walls.** Support HP is 1. An enemy Scout reaching them erases your investment.
- **Don't let MP decay bite you.** If you banked 8 MP for "a big play later", next turn you have 6 MP. In 2 turns you have 4.5 MP. In 3 turns you have 3.4 MP. Spend, don't save.
- **Don't forget the SP bonus for damage taken.** If you're losing badly, the opponent's SP advantage compounds. Sometimes ceding 1 HP to preserve a defense is better than over-committing resources to stop them.
- **Don't hard-code unit numbers.** The server config is authoritative and can change between seasons. Read `self.config["unitInformation"]` in `on_game_start`.
- **Don't trust `game-configs.json` in the starter kit.** It's a historical snapshot, not the live config.
