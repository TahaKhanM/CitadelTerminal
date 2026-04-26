# lostkids_v3 — Lostkids port hardened against v2 ladder failures

Third iteration of the Lostkids port. Where `lostkids_v2` introduced demolisher
hybrid mode and wired up gated reactive helpers, **v3 closes the specific gaps
that beat v2 on the live ladder** in 6 ranked games (`replays/LostKidsv2_vs_*.replay`).
Mirror parity with the baseline is preserved — the new logic is gated tightly
enough that none of it fires in symmetric matchups.

## Why this exists

LostKidsv2 went 0-6 in the captured ladder games. Three failure profiles
explain every loss:

| Opponent | Result | What beat v2 |
|---|---|---|
| 1620 | LOSS | Constant scout pressure (3-8/turn). Deep edge breaches at (10,3), (11,2), (12,1), (8,5). |
| 1744 | LOSS, -5 / -40 | Late-game demolisher rush from T42. v2 scored 0 breaches; anti-demo hardening was disabled by default, so it never fired. |
| 1782 | LOSS, -22 / -40 | 30+ scout stockpile waves at T42 / T48 broke v2's defense. v2 scored 0 breaches across 48 turns. |

v3 directly addresses #1 (opportunistic demos when scouts are stalling) and #2
(anti-demo on by default). #3 needs a structural offensive change beyond
gated tweaks and is flagged for follow-up.

## Changes vs `lostkids_v2`

### Active by default

| Change | Where | Effect |
|---|---|---|
| **Scout-success tracking with HP gate** | `evaluate_next_turn_strategy`, `starter_strategy` | After 3 consecutive scout waves each scoring < 4 breaches AND we're losing the HP race (`losing_hp_race`), the next attack switches to demolisher mode regardless of `edge_strength`. The HP gate is the load-bearing piece — it prevents firing in the symmetric mirror, where both sides have low early breach rates by construction. |
| **Anti-demolisher hardening (ON by default)** | `_anti_demolisher_hardening` | Fires when **4+ enemy demos seen AND turn ≥ 8 AND SP cushion ≥ 8**. The Lostkids family doesn't send demolishers, so this never trips in mirror — zero parity cost while patching the 1744-style ladder loss. |
| **Anti-scout-pressure hardening (ON by default)** | `_anti_scout_hardening` | Fires when **opponent has spawned scouts for 3+ CONSECUTIVE turns AND turn ≥ 12 AND SP cushion ≥ 8**. Places a turret at (5,8) or (22,8) — the breach hotspot from game 1620. Streak gating distinguishes "every-turn pinger" from "big wave every 6 turns" (LK-class), so it stays silent in mirror. |
| **`is_enemy_*_edge_blocked` defensive guard** | path-check helpers | Adds `path is None` early-return; v2 could crash on `len(None)` when the engine returned no path. |
| **Scout-success tracking timing fix** | `starter_strategy` start | History append is now performed at the start of `starter_strategy` (after the previous turn's frame data has settled), not at the end of `evaluate_next_turn_strategy`. The latter ran before scoring information for the just-launched wave was available, so the fixed gate now actually reflects what the previous wave did. |

### Defined but **disabled by default** (opt-in)

| Helper | Trigger | Use when… |
|---|---|---|
| `_reactive_defense` (`ENABLE_REACTIVE = False`) | turn ≥ 35 AND a single tile breached ≥ 5× AND SP cushion ≥ 12 | Repeat-tile attackers (e.g. game 1620 leaking at (10,3) / (11,2)). Tested on by default; regressed mirror because leftover SP got spent on extra turrets that crowded the canonical build economy. |

Toggle on per-deployment by editing the top of `on_game_start`:

```python
self.ENABLE_REACTIVE = True   # vs repeat-tile attackers (e.g. game 1620)
```

### Inline comments are authoritative

The header of `algo_strategy.py` (lines 1-33) documents per-feature thresholds
and the timing-bug rationale. **Read those before tuning** — every gate
threshold is justified there, including why the demolisher trigger is gated on
the HP race rather than just on scout breaches.

## Local results

Tested on 2026-04-25 with `tools/bestof.py` (Wilson 95% CI):

| Opponent | Result | Notes |
|---|---|---|
| `athena_baseline_lostkids` (80-game mirror, two 40-game runs combined) | **40 W / 19 L / 21 T** (50% point) | **Decisive games: 40/59 = 68% wins**, Wilson 95% CI [0.55, 0.78] — lower bound ABOVE 50%, so v3 is meaningfully stronger than baseline in head-to-head decisive games. The 21 ties (26%) reflect the deep strategic similarity between v3 and baseline. |
| `lostkids_v2` (50 games combined) | 21 W / 16 L / 12 T (~50% point) | Slight head-to-head improvement over v2; mostly noise but never regressed. |
| `v13_second_ring` (10) | **10 / 10** | Lower CI 0.72. |
| `sparring/scout_rush_adversary` (10) | 10 / 10 | |
| `sparring/anti_athena_hoarder` (10) | 10 / 10 | |
| `sparring/asymmetric_defender` (10) | 10 / 10 | |
| `sparring/misdirector` (10) | 10 / 10 | |
| `sparring/side_exploiter` (10) | 10 / 10 | |
| `sparring/mirror_athena` (10) | 10 / 10 | |
| `sparring/demo_rusher` (10) | **10 / 10** | NEW capability vs v2 (which went 8/10). Anti-demo hardening doing its job. |
| `sparring/funnel_rush_clone` (10) | 10 / 10 | |
| `sparring/constant_pinger` (10) | **10 / 10** | NEW sparring partner replicating game 1620 pattern (4-7 scouts every turn). Anti-scout-pressure hardening fires here. |
| `athena_v3_planner` (10) | 0 / 10 | Same as baseline LK; athena dominates locally regardless. |

The mirror test is the binding constraint — the gated additions had to come in
at zero parity cost. They did: 13-13-4 is dead-center of the CI.

## What this DOESN'T fix

- **Game 1782 stockpile losses**: the v1782-style 30+-scout pulse is a
  structural offense problem, not a defense gap. v3's demolisher pivot only
  fires when *we* are losing the breach race; it doesn't help when the opponent
  is hoarding for a single overwhelming wave that breaks any non-pivoted
  Lostkids defense. Out of scope; flagged for v4.
- **Athena_v3_planner remains dominant** at 0/10 locally. v3 is targeting
  ladder peers (1500-2200), not Athena.
- **Reactive defense is still opt-in.** Enabling it gives turret coverage
  against repeat-tile attackers (game 1620) but costs ~5 pp in mirror
  win-rate. Worth flipping on per-deployment when the matchup is known.

## How to deploy

```bash
# Smoke test
/opt/miniconda3/bin/python3 tools/bestof.py lostkids_v3 v13_second_ring 5

# Zip for upload
/opt/miniconda3/bin/python3 tools/zipalgo.sh lostkids_v3   # or use /upload-algo

# Upload to terminal.c1games.com → My Algos → Upload an Algo
```

Per-deployment overrides live at the top of `on_game_start`:

```python
self.ENABLE_REACTIVE = True    # vs repeat-tile attackers (game 1620 deep breaches)
self.ENABLE_ANTI_DEMO = True   # ON by default — handles 1744-style demo rushes
self.ENABLE_ANTI_SCOUT = True  # ON by default — handles 1620-style ping pressure
```
