---
name: competition-reference
description: Surface exact Citadel Terminal competition rules, unit stats, or API details on demand. Use when the user asks for a specific number/formula/rule (e.g. "what's the Turret range", "how much SP for a Support upgrade", "what's the MP decay rate") and you want authoritative values fast without paraphrasing from memory.
---

# competition-reference

Fast-lookup skill for competition rules, unit stats, API signatures, and formulas. Prefer invoking this over recalling numbers — the values in this competition differ from the base game and memory is unreliable.

## When to use
- User asks a specific numeric question: costs, HP, range, damage, income rate, decay, refund formula, map boundaries, etc.
- You're about to write a magic number in algo code — double-check it here first.
- You're explaining a mechanic and want to cite the exact rule.

## How to respond

1. **Identify the category** of the question:
   - Unit stat → look up in [`docs/UNITS_REFERENCE.md`](../../../docs/UNITS_REFERENCE.md)
   - Rule / turn structure / resource math → [`docs/GAME_RULES.md`](../../../docs/GAME_RULES.md)
   - API method → [`docs/API_REFERENCE.md`](../../../docs/API_REFERENCE.md)
   - Map geometry / edges → [`docs/MAP_AND_COORDINATES.md`](../../../docs/MAP_AND_COORDINATES.md)
   - Targeting/pathing → [`docs/TARGETING_AND_PATHING.md`](../../../docs/TARGETING_AND_PATHING.md)
   - Strategy / archetypes → [`docs/STRATEGY_GUIDE.md`](../../../docs/STRATEGY_GUIDE.md)

2. **Read the relevant file** (don't guess).

3. **Answer concisely** with:
   - The exact number or rule.
   - A one-sentence explanation of why it matters.
   - The file:line where it's stated (for the user's reference).

## Quick-reference cheat sheet (cached here for common lookups)

### Unit costs & stats (competition values)

| Unit | SP/MP Cost | HP | Range | Damage | Speed (frames/tile) |
|---|---|---|---|---|---|
| Wall (FF) | 1 SP | 60 (200 upg) | — | — | — |
| Wall upgrade | +2 SP | — | — | — | — |
| Support (EF) | 4 SP | 1 | 3.5 (7 upg) | — | — |
| Support upgrade | +4 SP | — | — | — | — |
| Turret (DF) | 2 SP | 60 (100 upg) | 2.5 (3.5 upg) | 6 (20 upg) | — |
| Turret upgrade | +4 SP | — | — | — | — |
| Scout (PI) | 1 MP | 15 | 3.5 | 2 (both types) | 1 |
| Demolisher (EI) | 3 MP | 5 | 4.5 | 8 (both types) | 2 |
| Interceptor (SI) | 1 MP | 40 | 4.5 | 20 vs mobile, 0 vs structure | 4 |

### Economy

- Start: 8 SP, 1 MP, 40 HP
- Per-turn income: +4 SP, +(1 + turn//5) MP
- Damage bonus: +1 SP per HP damage dealt last turn
- Scoring bonus: +1 SP per unit that breached enemy edge
- MP decay: 25 %/turn, rounded to 0.1
- Turn limit: 100 rounds
- Deploy time: 15 s, then 1 dmg/sec penalty

### Targeting priority (all damaging units)
1. Mobile > Structures
2. Nearest (Euclidean)
3. Lowest HP
4. Deepest into attacker's side
5. Closest to an edge (far from x=13.5)
6. Most recently created (fallback)

### Self-destruct
- Triggered when mobile has nowhere to move
- Range 1.5, damage = starting HP of SD-ing unit
- Requires ≥5 tiles traveled before SD deals damage
- Only damages enemy units; friendly units are untouched

### Refund & removal
```
base structure:     refund = 0.9 × InitialCost × (CurrentHP / StartingHP)   (rounded 0.1)
upgraded structure: refund = 0.8 × InitialCost × (CurrentHP / StartingHP)
turnsRequiredToRemove = 2 (base) / 3 (upgraded)
```
Upgraded structures refund ~11% less AND take an extra turn to clear — upgrade-then-remove is lossy.

### Support shield formulas (verified against official replay's live config)
```
base_shield_per_unit       = 3        (flat, range 3.5, 1 HP)
upgraded_shield_per_unit   = 1 + 0.7 × Y_position   (range 7, 40 HP)
```
Maximum upgraded value at Y=13: `1 + 0.7 × 13 = 10.1` shield per unit.
Minimum upgraded value at Y=0: `1` — LESS than base (3). Only upgrade Supports at Y≥3.

**Upgrading is tanky**: base Support HP=1 → upgraded HP=**40**. Upgraded Supports survive stray hits and persist across many turns.

### Arena geometry
- 28 × 28 grid, diamond-shaped playable region
- Your territory: y < 14
- Your edges:
  - `BOTTOM_LEFT` (id=2): tiles `[13-y, y]` for y ∈ [0,13]
  - `BOTTOM_RIGHT` (id=3): tiles `[14+y, y]` for y ∈ [0,13]

## Protocol

If the user asks a question you're NOT sure about or it's not covered above:
1. Read the relevant doc file.
2. If still unclear, check `C1GamesStarterKit-master/game-configs.json` as a sanity check (but warn that it's from an older season — the live server config is authoritative).
3. If still unclear, offer to run a small experiment (e.g., `run-match` between two test algos that probe the mechanic).
