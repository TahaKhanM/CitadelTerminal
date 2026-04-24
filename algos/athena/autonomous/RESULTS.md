# RESULTS — measured win rates + CIs

## 🏆 CHAMPION CANDIDATE: v21_temporal_phase_gate (cycle 3, 2026-04-24)

**Sequential n=8** (re-run due to parallel-eval race that crashed vs v13 runs):

| opp | W-L-T | WR | ΔHP | turns | crashes |
|---|---|---:|---:|---:|---:|
| **v13_second_ring** | **8-0-0** | **100%** | **+7.0** | 100 | 0 |
| **v14_support_caravan** | **8-0-0** | **100%** | **+42.0** | 58 | 0 |
| opp_scout_rush | 8-0-0 | 100% | +40.0 | 30 | 0 |
| opp_demo_line | 8-0-0 | 100% | +48.0 | 25 | 0 |
| opp_turret_castle | 8-0-0 | 100% | +1.0 | 100 | 0 |

**Overall: 40/40 (100%).** p50=7ms p95=32ms p99=38ms. Wilson LB at n=8: 0.68 — need n≥100 for LB≥0.85 per criterion A. **Validation n=15 running in bg.**

## Wave-2 all 5 variants (parallel eval, some corrupted by race condition)

### v20_burst_scout
- v13: WLT 0-0-0 crash=8 (race)
- v14: WLT 0-0-3 crash=5 (real ties when games ran)
- others: 8-0-0 each, ΔHP +40-46
**Verdict:** burst alone insufficient; archive and inspect the 3 real v14 ties for any pattern.

### v20_support_kill
- v14: WLT 0-0-8 (clean 8 ties)
- v13: crash=8 (race)
**Verdict:** archive — Support-kill Demo doesn't break the mirror.

### v20_flank_edge ⚠️ REGRESSION
- v14: 0-8-0 ΔHP **−44** (8 losses!)
- opp_scout_rush: 0-8-0 ΔHP **−50** (edge-path-self-destruct death trap!)
**Verdict:** archived — edge paths cause attackers to walk into their own death corridor.

### v20_hybrid_turtle_cracker
- v14: 0-6-0 crash=2
- others: 8-0-0
**Verdict:** classifier misclassifies v14 as turtle sometimes → Demos lose like v20_demo_train's −40 HP. Need better classifier logic.

### v21_temporal_phase_gate
- See champion section above. Clean sweep sequential.

## Synthesis
- **v21's mechanism:** defer attack 2 turns when opponent MP drops >5 (signals they attacked), reinforce Interceptors T_cooldown-1, counter-attack at lower threshold (6×scout_cost). This breaks the mirror because:
  - vs v14: v14 spends MP into Caravan+shield regen; v21 catches v14 at low-MP moments and presses.
  - vs v13: same — v21 times its bursts better, winning narrowly at ΔHP+7.
- **v21's turn_number=58 vs v14** means v21 finishes the game 40+ turns earlier than mirror — it's actually BREACHING v14's HP, not tying.

## Running ranked-match context
- 22 wins / 25 losses across 47 ranked. Top losses: oleh-v2 (7 losses, 22% WR, scout_rush), turret_castle variants (7 losses, all T100 timeouts), demo_line at 1800+ ELO (3/6).
- v21 likely helps vs scout_rush (Interceptor reserve during their peak wave) and demo_line (defer attack when Demo wave incoming).
- v21 unlikely to solve turret_castle by itself — still +1 HP vs turret_castle (narrow). Wave-3 v22_siege_breaker needed.

## Concurrency bug in evaluator
Running 5 evaluate.py instances in parallel causes tmp-dir/replay-dir collisions that crash v13 match runs consistently. Either:
- Add locking to bestof.py's tempdir allocation, or
- Document that evaluate.py should run sequentially

Workaround for cycle 3: run validation sequentially. TODO cycle-N: fix bestof.py race.
