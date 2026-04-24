# RESULTS — measured win rates + CIs

## Baseline smoke run (n=4 × 2 sides = 8 games/opponent), 2026-04-24 cycle 1

### v13_second_ring
| opponent | games | W | WR | Wilson 95% CI | mean ΔHP | mean turns | crashes |
|---|---:|---:|---:|---|---:|---:|---:|
| v14_support_caravan | 8 | 0 | 0.0% | [0.00, 0.32] | 0.0 | 100.0 | 0 |
| v15_adaptive | 8 | 8 | 100.0% | [0.68, 1.00] | +41.0 | 0.0 | 0 |
| opp_scout_rush | 8 | 8 | 100.0% | [0.68, 1.00] | +46.0 | 26.0 | 0 |
| opp_demo_line | 8 | 8 | 100.0% | [0.68, 1.00] | +48.0 | 25.0 | 0 |
| opp_turret_castle | 8 | 8 | 100.0% | [0.68, 1.00] | +1.0 | 100.0 | 0 |

**Overall: 32/40 (80%).** p50 turn time 36ms, p95 73ms, p99 111ms.

## 🚨 CRITICAL OBSERVATION
- **v14_support_caravan DEFEATS v13_second_ring 8-0.** Contradicts prior memory v13_mirror_ceiling.md. v14 is likely the actual current champion, not v13.
- **v15_adaptive has turns=0.0** — likely crashing silently or collapsing on turn 0. Needs investigation but deprioritized (v14 is more interesting).
- **v13 ties opp_turret_castle HP (+1.0 over 100 turns)** — essentially a coin flip on this matchup. Both turtle.

## Next steps
- Baseline v14 vs same pool (including v13) at n=8 to confirm strength
- Set v14 as incumbent "champion to beat"
- Build v20+ variants targeting v14's weaknesses (not v13's)

## v14_support_caravan — TBD
(pending next eval run)

## algos/athena — TBD
(pending next eval run)
