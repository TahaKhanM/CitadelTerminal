# G11 — Per-opponent breakdown (Phase 7 milestone R)

Source: `data/G11_RESULTS.json`


- Distinct opponents in corpus: **30**
- Total replays: 47

## Per-opponent table

| opponent | n | v13 W-L | Athena W-L-T | Δ/match (Athena − v13) |
|---|---|---|---|---|
| banana | 1 | 0-1 | 1-0-0 | +1.000 |
| python-algo-baseline | 1 | 0-1 | 1-0-0 | +1.000 |
| python-algo-jae-2 | 1 | 0-1 | 1-0-0 | +1.000 |
| python-algo-turtle-new-submis | 1 | 0-1 | 1-0-0 | +1.000 |
| python-algo-v3 | 1 | 0-1 | 1-0-0 | +1.000 |
| python-algo-v8 | 1 | 0-1 | 1-0-0 | +1.000 |
| takedown1-algo | 1 | 0-1 | 1-0-0 | +1.000 |
| the-goat-algo | 1 | 0-1 | 1-0-0 | +1.000 |
| oleh-v2 | 9 | 2-7 | 9-0-0 | +0.778 |
| python-algo-jae-3 | 4 | 1-3 | 4-0-0 | +0.750 |
| diego_v2 | 2 | 1-1 | 2-0-0 | +0.500 |
| python-algo | 4 | 3-1 | 3-1-0 | +0.000 |
| please-work-man-im-tired | 3 | 0-3 | 0-3-0 | +0.000 |
| R1_Sawtooth | 1 | 1-0 | 1-0-0 | +0.000 |
| R2_Infiltrator | 1 | 1-0 | 1-0-0 | +0.000 |
| R3_Jukebox | 1 | 1-0 | 1-0-0 | +0.000 |
| R4_Champion | 1 | 1-0 | 1-0-0 | +0.000 |
| algo4 | 1 | 1-0 | 1-0-0 | +0.000 |
| doublegap | 1 | 1-0 | 1-0-0 | +0.000 |
| gooder-maybe | 1 | 1-0 | 1-0-0 | +0.000 |
| potential-algo | 1 | 0-1 | 0-1-0 | +0.000 |
| python-algo-jae-4 | 1 | 1-0 | 1-0-0 | +0.000 |
| python-algo-master | 1 | 1-0 | 1-0-0 | +0.000 |
| python-algo-turtle-v4 | 1 | 1-0 | 1-0-0 | +0.000 |
| python-algo-v3-detectors | 1 | 0-1 | 0-1-0 | +0.000 |
| python-algo1 | 1 | 1-0 | 1-0-0 | +0.000 |
| python-algo3 | 1 | 1-0 | 1-0-0 | +0.000 |
| streeter | 1 | 1-0 | 1-0-0 | +0.000 |
| new-strat-algo | 1 | 1-0 | 0-1-0 | -1.000 |
| python-algo-v5 | 1 | 1-0 | 0-1-0 | -1.000 |

## Top Athena improvements over v13

| opponent | n | v13 wins | Athena wins | Δ/match |
|---|---|---|---|---|
| banana | 1 | 0 | 1 | +1.000 |
| python-algo-baseline | 1 | 0 | 1 | +1.000 |
| python-algo-jae-2 | 1 | 0 | 1 | +1.000 |
| python-algo-turtle-new-submis | 1 | 0 | 1 | +1.000 |
| python-algo-v3 | 1 | 0 | 1 | +1.000 |

## Top Athena regressions vs v13

| opponent | n | v13 wins | Athena wins | Δ/match | match_ids that regressed |
|---|---|---|---|---|---|
| new-strat-algo | 1 | 1 | 0 | -1.000 | 15305301 |
| python-algo-v5 | 1 | 1 | 0 | -1.000 | 15304426 |

## Diagnosis: Athena's losses (v13 won, Athena did not)

These are the cases where Athena strictly regressed against v13. 
For each one we surface the offense-pick distribution from 
`data/G11_RESULTS.json` to indicate which template Athena 
kept reaching for.

Total regressions: **3** of 22 v13 wins.

| match_id | opponent | elo | turns | hp_self | hp_opp | top picks |
|---|---|---|---|---|---|---|
| 15303643 | python-algo | 1502 | 24 | 0.0 | 7.0 | 14×hoard, 9×interceptor_screen, 1×corner_dive_right |
| 15305301 | new-strat-algo | 1576 | 25 | 0.0 | 8.0 | 16×hoard, 5×interceptor_screen, 2×corner_dive_right |
| 15304426 | python-algo-v5 | 1468 | 24 | -1.0 | 7.0 | 14×hoard, 9×interceptor_screen, 1×corner_dive_right |

## Hypotheses on regressions

1. **Multi-round opponents.** The regressions cluster on long (20+ turn) matches where Athena's HP attrition is faster than v13's. Without Athena's own defense applied (G11 setup uses v13's recorded defense board for fairness), Athena pays for offense-only divergence.
2. **High-pressure opponents.** Opponents like ``please-work-man-im-tired`` and ``new-strat-algo`` deploy heavy mobile pressure that v13 actually withstood through the v13_inspired defense placement we don't fully replicate in this counter-factual. Athena's offense doesn't change the opponent's MP enough to push them off optimal play.
3. **Pick-distribution noise.** Athena flips between 2–3 templates across the regression set (corner_dive_*, scout_rush_*, interceptor_screen). The classifier posterior is uniform until frames accumulate, so early-game picks are predictor-empty and default to the highest-MP-utilization template — sometimes the wrong call vs the actual opponent's recorded counter-spawn.

These are HYPOTHESES — full Phase 8 final-report writeup should validate by re-running individual regressions with verbose Candidate.debug logs.
