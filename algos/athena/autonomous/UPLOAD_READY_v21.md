# UPLOAD READY — v21_temporal_phase_gate

**Date:** 2026-04-24 cycle 3
**Algo:** `algos/v21_temporal_phase_gate/`
**Tag:** `temporal-gate (invention #2)`
**Parent:** v13_second_ring

## Local evaluation @ n=30 games/opponent (150 games total)

| opponent | W-L-T | WR | Wilson 95% CI | mean ΔHP | mean turns |
|---|---|---:|---|---:|---:|
| v13_second_ring | 30-0-0 | 100% | [0.89, 1.00] | +7.0 | 100 |
| v14_support_caravan | 30-0-0 | 100% | [0.89, 1.00] | **+42.0** | **58** |
| opp_scout_rush | 30-0-0 | 100% | [0.89, 1.00] | +40.0 | 30 |
| opp_demo_line | 30-0-0 | 100% | [0.89, 1.00] | +48.0 | 25 |
| opp_turret_castle | 30-0-0 | 100% | [0.89, 1.00] | +1.0 | 100 |

**Overall: 150/150 (100%). Wilson LB 0.89.** Exceeds criterion A LB ≥0.85.

## Zip

Run `/upload-algo` on `algos/v21_temporal_phase_gate` to produce the upload zip.
Or manually:
```
cd C1GamesStarterKit-master
./scripts/zipalgo_mac ../algos/v21_temporal_phase_gate ../v21_temporal_phase_gate.zip
```

## Rationale — why upload this now

1. **First mirror-breaker.** Every prior variant (v14, v20_demo_train, v20_sidelane, v20_burst_scout, v20_support_kill, etc.) tied both v13 and v14 or lost. v21 cleanly beats both at n=30, Wilson LB 0.89.

2. **Novel tactical innovation:** temporal opponent-MP tracking. When opponent's MP drops > 5 in a single turn (they just attacked), v21 defers its own attack 2 turns, reinforces Interceptor defense at T_cooldown−1, then counter-attacks at threshold `6 × scout_cost` (vs v13's 10×) when opponent is low-resource.

3. **Against v14 Support Caravan (the prior mirror barrier):** v21 finishes the game at turn 58 with +42 HP — dominant, not a draw. v21's phase-gate catches v14's Caravan during MP-spending windows.

4. **Ranked context:** our current ranked record is 22W/25L (ELO 1581). Top losses: oleh-v2 (7 losses, scout_rush archetype) and turtle variants (7 losses, all T100 timeouts). v21 likely improves vs scout_rush (Interceptor reserve during peak wave) and demo_line (attack-defer when Demo wave incoming). Turret-castle still narrow (+1 HP); v22_siege_breaker queued.

## Upload action (user)

1. Zip `algos/v21_temporal_phase_gate`.
2. Upload at https://terminal.c1games.com → My Algos → Upload an Algo.
3. Paste the new algo_id into `algos/athena/autonomous/RANK_UPDATES.md` when it appears.

The autonomous loop continues iterating on wave-3 counters regardless of upload status.
