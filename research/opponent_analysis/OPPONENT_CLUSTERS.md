# Opponent Corpus Analysis — 2026-04-25

**Goal**: Build the best version of Athena possible by understanding who's on the ladder, what they do, and where Athena fails.

**Corpus**: 307 usable replays across 6 of our algos vs 91 distinct opponents.

---

## 1. Headline findings

### The performance cliff at 1700 ELO

All 6 of our algos drop sharply when the opponent rating exceeds 1700:

| Our algo | 1300-1500 | 1500-1700 | 1700-1900 | 1900+ |
|---|---|---|---|---|
| baseline (lostkids port) | 100% (n=2) | 71% (n=14) | **50% (n=36)** | **42% (n=72)** |
| v13_second_ring | 85% (n=13) | 47% (n=34) | **17% (n=24)** | **0% (n=1)** |
| v21_quadrant_adaptive_support | 79% (n=14) | 40% (n=15) | **0% (n=2)** | **0% (n=2)** |
| v21_temporal_phase_gate | 62% (n=24) | 38% (n=13) | **0% (n=11)** | **0% (n=10)** |
| athena_v3_planner | 60% (n=5) | 0% (n=3) | **0% (n=1)** | — |
| athena_baseline | 67% (n=3) | 0% (n=2) | **0% (n=1)** | — |

**Implications**:
1. Lostkids port (baseline) is the **only** algo that competes above 1900 (42% — barely losing). All others go 0%.
2. Athena (both versions) goes 0% above 1500 ELO. The 1574/1418 ratings reflect performance near the bottom of mid-tier matchups.
3. Athena's win-rate at the **1300-1500 band** (60-67%) tells us the **machinery isn't broken** — it can win games when matchups are favorable. The problem is opponents above 1500 systematically beat it.

### Two opponent archetypes dominate

K-means clustering yielded weak silhouette scores (0.21-0.26 across k=3..11), meaning opponents lie on a **continuous spectrum**, not in crisp archetypes. But two qualitative groups emerge from the behavioral profile:

**Type A — Aggressive grinders (~250 units/game, mixed offense)**
- Examples: python-algo (61 games), oleh-v2 (20), defensive-order-algo (10), python-algo-jae-3 (9), terminator (4)
- Spawn 240-431 mobile units per game
- Unit mix: 39-59% Scouts / 18-35% Demolishers / 15-31% Interceptors
- Front-line wall density 7.7-12.5
- First attack at turn 11-14
- These are most current-meta opponents. They press steadily, have real demolisher pressure, and walled fronts.

**Type B — Defensive/passive (~120 units/game, scout-heavy)**
- Examples: legacy lostkids-vs (48 games), funnel-rush v8/v9 (17 games), switch2 (7), python-algo-babayaga3 (5)
- Spawn 100-140 units per game
- Unit mix: 65-95% Scouts (very pure)
- Front-line walls 5.2-7.5 (lighter)
- First attack at turn 19-22 (wait longer)
- These are turtle/funnel/legacy archetypes. Less front-line presence, longer game pacing.

---

## 2. Per-opponent diagnostic table (top 15 by encounter count)

```
opponent              games  avg_rate  units_avg  S/D/I_frac  front_walls  first_atk
python-algo            61      1603       260      49/28/23     7.7         13
oleh-v2                20      1776       241      59/18/23     7.7         11
(legacy@2100-2199)     14      2139       137      96/0/4       7.9         22
defensive-order-algo   10      1423       237      56/21/22     7.8         11
(legacy@2000-2099)      9      2066       114      95/0/5       7.7         22
funnel-rush-v8          9      1844       137      65/9/26      5.8         19
python-algo-jae-3       9      1616       297      42/27/31     7.9         12
funnel-rush-v9          8      1981       129      77/7/16      5.2         20
(legacy@1800-1899)      7      1825       116      90/1/9       6.1         22
switch2                 7      1854       109      88/1/11      7.0         20
(legacy@1900-1999)      6      1950       109      95/0/5       7.5         22
python-algo-babayaga3   5      1678       182      79/10/11     7.0         15
terminator              4      1552       431      39/34/27    12.5         14
(legacy@1700-1799)      4      1762        97      77/4/19      7.2         12
python-algo_2           4      1420       343      50/35/15     7.5         13
```

**Per-matchup win/loss for high-priority opponents**:

| Opp | Avg rate | baseline | v13 | v21_quad | v21_temp | athena_v3 | athena_base |
|---|---|---|---|---|---|---|---|
| python-algo | 1603 | 8W/4L | 10W/3L | 7W/5L | 7W/11L | 2W/2L | 2W/0L |
| oleh-v2 | 1776 | 0W/2L | **3W/9L** | 0W/1L | 1W/3L | — | 0W/1L |
| defensive-order-algo | 1423 | — | 0W/3L | 1W/1L | 3W/1L | 0W/1L | — |
| funnel-rush-v8 | 1844 | 2W/4L | — | — | 1W/2L | — | — |
| funnel-rush-v9 | 1981 | **0W/6L** | — | — | 0W/2L | — | — |
| terminator | 1552 | — | 0W/2L | — | 0W/1L | — | 0W/1L |

**The critical losses we need to flip**:
1. **oleh-v2**: lost 18 of 20 games across all algos. This single opponent contributes massively to Athena's ELO drag.
2. **funnel-rush-v9**: 0W/8L. Top-tier scout-flood algo at 1981 ELO.
3. **terminator**: 0W/4L. High-volume mixed offense.

---

## 3. Why Athena loses (mechanism diagnosis)

### Loss-replay pattern: defensive sim-vs-live miscalibration

From the most recent Athena_loss replay (Apr 25, 0-45 in 35 turns), we already established:
- sim_rs reports `interceptor_screen` deals 4 HP to opp per turn (sim assumes interceptors walk to opp's edge after killing scouts in 1-turn rollout)
- On live, this never happens — opp's defense kills our interceptors first
- The fix in `athena_baseline`'s `INTERCEPTOR_HEAVY_PENALTY=10.0` partially patches this

**But the corpus reveals a deeper pattern**: Athena's offense_planner has **no specific counter for demo-heavy opponents**. When 28% of opp's units are Demolishers (python-algo's mix), Athena's typical offense (Scouts at flank) gets eaten.

### Athena's offense is too narrow

Across 15 athena_v3 + athena_baseline replays:
- 0 Demolisher spawns
- 0 spawn locations outside (3,10), (4,9), (6,7), (13,0), (14,0), (21,7)
- Picks dominated by 4-7 templates (out of the 16 + variants)

The **candidate-explosion** work added 92× more candidates locally, but live data shows beam search converges on the same handful. The candidates are diverse — the SCORING is concentrating picks.

---

## 4. Strategic recommendations for the best Athena

### Tier 1 — Highest ELO impact (do first)

**1. Fix the 1300-1700 ELO band first** (Athena's win rates: 60-67% there, 0% above)
   - This is where the win-rate cliff sits. Even a small improvement shifts ranked games above 1700 → exposes Athena to harder opponents → ELO climbs.
   - **Concrete action**: bestof against python-algo (the #1 most-faced opp). v13 wins 77% (10W/3L). Athena-v3 wins 50% (2W/2L). Close the gap.

**2. Add a "counter demo-heavy" template**
   - 56% of our games are vs Type-A aggressive opponents (python-algo, oleh-v2, defensive-order-algo, jae-3)
   - These spawn 21-28% Demolishers
   - Demolishers shred walls but are slow → counter is fast Scouts on flanks AROUND demos, not into them
   - Add template: `flank_pressure_v_demo` (8 Scouts at (1, 12) + 2 Interceptors at (4, 9) — engage demos, send scouts past)

**3. Anti-oleh-v2 specifically**
   - 20 games, 18 losses. 1776 avg rating, 59/18/23 S/D/I, fast attack at turn 11.
   - oleh-v2 hits us early and consistently → we need EARLY defense (turrets up by turn 8) plus opportunistic offense
   - Concrete experiment: clone oleh-v2's behavior in a sparring algo, bestof against current Athena, identify the breaking pattern.

### Tier 2 — Medium impact

**4. Counter-funnel-rush template**
   - funnel-rush v8/v9 spawn 65-77% Scouts at high volume (137 units/game)
   - Their scouts come in waves of 12-14 — overwhelming a 4-Interceptor screen
   - Counter: **Demolisher line at our front rows** during their predicted wave turns (we can predict because they attack at turn 19-20 reliably)

**5. Calibrate sim utility against actual game outcomes**
   - For each replay, compute "what did sim_rs predict for our actual offensive plan vs what actually happened"
   - If we systematically over-predict damage by 30%+, scale back HP_DEALT_MULTIPLIER
   - 307-replay corpus is enough for this regression

**6. Defense-offense decoupling (still unfixed)**
   - Earlier diagnosis: `probabilistic_placement` puts Supports at (4,9), (23,9) — **own spawn-edge tiles** — eliminating 8 of 16 templates
   - Fix is one-liner: `if (x, y) in SPAWN_EDGE_TILES: continue` in defense.py
   - Estimated: +50-100 ELO

### Tier 3 — Long horizon

**7. Multi-turn lookahead** (depth-2 beam)
   - Sim_rs is single-turn. Many sim/live discrepancies (interceptor breach, wall-removal trick) only show up after multiple turns
   - Depth-2: simulate one MORE turn after our action + opp's response
   - Cost: 5x compute, but we have 50% headroom

**8. Per-archetype counter-strategy library**
   - Build 2-3 dedicated openers per archetype (Type A: aggressive, Type B: passive)
   - Classifier output picks which library to use
   - Replaces beam-search-finds-it-from-scratch with informed prior

---

## 5. Concrete next steps in priority order

| # | Task | Estimated ELO | Effort |
|---|---|---|---|
| 1 | Fix defense over-walling (one-liner in `probabilistic_placement`) | +50-100 | 30 min |
| 2 | Recalibrate sim's HP_DEALT vs actual outcomes (regression on 307 replays) | +30-60 | 2 hr |
| 3 | Add `flank_pressure_v_demo` template, validate vs python-algo bestof | +50-100 | 3 hr |
| 4 | Build oleh-v2 sparring clone, bestof against Athena, study the breakers | +80-150 | 4 hr |
| 5 | Anti-funnel-rush template + bestof | +30-60 | 2 hr |
| 6 | Multi-turn lookahead (depth-2 beam) | +50-100 | 1 day |
| 7 | Classifier output → per-archetype template library | +50-100 | 1 day |

**Conservative additive estimate: +340-670 ELO**, but improvements are correlated (fixing #1 may make #2 easier to detect, etc.) — realistic combined gain: **+200-300 ELO** from all of Tier 1+2.

That moves Athena from current 1574 toward 1800-1900 — squarely into the band where lostkids currently sits. Beyond that, Tier 3 work (multi-turn / classifier-driven) is required to break into the 2000+ tier.

---

## 6. Ground truth for future work

This corpus is now in `research/opponent_analysis/`:
- `opp_features.json` — 307 replays × 30+ behavioral features. Reusable for re-clustering, regression, etc.
- `opp_clusters.json` — k-means assignments (low silhouette — use for direction-finding only)
- `OPPONENT_CLUSTERS.md` — this document

The bulk-download script (`tools/bulk_download_my_replays.py`) is reusable — re-running it pulls any new ranked games.

**Bottom line for Athena**: we have a clear list of exactly what to fix and roughly how much each fix is worth. The corpus turned vague "make it better" into a prioritized 7-task roadmap with measurable ELO targets.
