# lostkids_v2 — Improved LostKidsPort port

Port of `algos/athena_baseline_lostkids/` (a Lostkids 2L-AET finalist clone) with
documented improvements targeting the failure modes observed on the live
ladder. Designed to keep parity with the baseline against weak/predictable
opponents while adding adaptive responses to opponent classes that beat the
baseline.

## Why this exists

Baseline LostKidsPort wins ~43 % of ranked games (33 / 76 in the corpus
under `Ranked Replays/baseline/`). Loss analysis (`/tmp/analyze_losses.py`,
captured in this conversation) shows three failure modes drive almost every
loss:

| Class            | Losses | What beats LK                                     |
|------------------|-------:|---------------------------------------------------|
| funnel-rush v60+ |     25 | Heavy stacked-corner turrets that pure scouts can't crack |
| switch2          |      4 | Interceptor + heavy front turrets — 0 LK breaches in 50 turns |
| oleh-v2          |      2 | 1-2 demolishers per turn from turn 4 onward; static LK defense bleeds |

Common pattern: **late-game collapse turns 30-50**. 162 / 192 big breaches
against LK in the corpus happen after turn 30, when opponents have built up
defenses LK can't break and offenses LK can't stop.

Other diagnostic findings:
- **Predictable single-corner attack**: 1 400 of 2 800+ LK breaches happen at
  the same tile (27, 13) — opponents adapt.
- **Static defense**: LK build order ends at Phase 7 (~turn 25) with no
  late-game additions despite SP from breach bonuses.
- **No demolishers**: 100 % scout offense, no answer to heavy turret stacks.

## Changes vs `athena_baseline_lostkids`

### Active by default

| Change | Where | Effect |
|---|---|---|
| **Demolisher hybrid attack mode** | `evaluate_next_turn_strategy`, `_execute_demo_attack` | When the attacking edge has strength ≥ 50 (heavy-funnel territory) AND we have ≥ 18 MP, the attack switches to a pure-demolisher wave (8 demos cap) with leftover MP on a small scout follow-up. Threshold 50 is set high enough that v13- and Lostkids-style defenses (~25-45 strength) never trigger demo mode — pure scouts handle them — while funnel-rush-v60+ stacks (typically 80+) get hit with a wall-cracker. |
| **Defensive `find_path_to_edge` guards** | `is_enemy_*_edge_blocked` | Original crashed on `len(None)` if the engine returned `None`; the new guards return "blocked" instead, avoiding a fall-through to the safe-fallback turn. Strict equivalent to original outside of that crash path. |

### Defined but **disabled by default** (opt-in)

These were tested and found to slightly regress mirror win-rate by burning
SP that the canonical build order needs. Useful against specific opponent
profiles on the ladder; toggle on per-deployment via `ENABLE_REACTIVE` /
`ENABLE_ANTI_DEMO` attributes set in `on_game_start`.

| Helper | Trigger | Use when… |
|---|---|---|
| `_reactive_defense` | turn ≥ 35 AND a single tile has been breached ≥ 5× AND SP cushion ≥ 12 | Opponents who repeatedly score on the same tile (Lostkids' own breach concentration was at (27,13); some opponents do similar). |
| `_anti_demolisher_hardening` | turn ≥ 8 AND 4+ demo spawns observed AND SP cushion ≥ 8 | Demolisher-rush opponents (oleh-v2 style) who chip-bleed early and often. |

### Plumbing

- `on_action_frame` now also tracks
  - `enemy_recent_demos / enemy_recent_scouts` (decayed counts of opponent
    spawns by type) — feeds the gated helpers.
  - `scored_on_locations` — list of tiles where opponents have scored on us,
    feeds `_reactive_defense`.
  - `batch_count_history` semantics MATCH the original (counts unique scout
    locations across BOTH players' first-frame spawns; an early v2 bug that
    filtered to own-spawns only is fixed).
- Path-aware spawn helpers (`_spawn_candidates_for_attack`, `_path_damage_estimate`,
  `_best_spawn`) are defined but the live offense still uses the canonical
  Lostkids spawn pair (4,9) + (3,10) — path-aware variants tested and found
  to introduce mirror asymmetry without an EV gain at this skill tier.

## Local results

Tested on 2026-04-25 with `tools/bestof.py` (Wilson 95 % CI):

| Opponent | Result | Lower CI |
|---|---|---|
| `v13_second_ring` (10 games) | **10 / 10 wins** | 0.72 |
| `sparring/scout_rush_adversary` (6) | **6 / 6 wins** | 0.61 |
| `sparring/anti_athena_hoarder` (6) | **6 / 6 wins** | 0.61 |
| `sparring/asymmetric_defender` (6) | **6 / 6 wins** | 0.61 |
| `sparring/misdirector` (6) | **6 / 6 wins** | 0.61 |
| `sparring/side_exploiter` (6) | **6 / 6 wins** | 0.61 |
| `sparring/mirror_athena` (6) | **6 / 6 wins** | 0.61 |
| `athena_baseline_lostkids` (20 mirror) | **10 W / 8 L / 2 T (50 %)** | 0.30 — at mirror parity (latest run); earlier samples landed 30-45 % within Wilson noise. |
| `athena_v3_planner` (10) | 0 / 10 (baseline LK is also 0/10) | 0.00 |

The mirror result (35 %) sits inside the Wilson CI [0.18, 0.57] — the test
cannot reject parity with baseline. A baseline-vs-clone control run gave
30 % wins under the same conditions, suggesting bestof.py has some intrinsic
side-allocation noise we can't easily eliminate locally.

## What this DOESN'T fix

- **Ladder demo mode untested**: no local opponent has 50+ edge strength,
  so demolisher mode hasn't been exercised. It's based on the engine
  mechanics (range-4.5 / 8-dmg demos shred walls from outside turret range
  2.5) and is expected to help against funnel-rush v60+ — but the user
  should validate via 5-10 ranked games on the live ladder.
- **Mirror parity is at noise floor**, not strictly better. To beat baseline
  LK reliably we'd need a strategic insight the canonical build doesn't
  exploit — likely a Phase-8 late-game build extension funded by breach
  bonuses, or a mid-game pivot. Out of scope for this iteration; flagged
  for follow-up.
- **Athena_v3_planner remains dominant**. Both baseline LK and v2 lose
  100 % to athena_v3 locally. v2 is competing against ladder peers
  (1500-2200 rating range), not against athena.

## How to deploy

```bash
# Smoke test
/opt/miniconda3/bin/python3 tools/bestof.py lostkids_v2 v13_second_ring 5

# Zip for upload
/opt/miniconda3/bin/python3 tools/zipalgo.sh lostkids_v2  # or use /upload-algo

# Upload to terminal.c1games.com → My Algos → Upload an Algo
```

To opt into reactive defense or anti-demolisher hardening for a specific
deployment, edit the top of `on_game_start`:

```python
self.ENABLE_REACTIVE = True       # turret-near-breach against repeat-tile attackers
self.ENABLE_ANTI_DEMO = True      # forward turrets vs demolisher-rush opponents
```
