# VARIANTS — every algo built, status, parent, notes

Status: active / archived / champion / broken / pending-eval

| version | parent | tag | status | notes |
|---|---|---|---|---|
| v13_second_ring | (baseline) | incumbent | archived | mirrored by v14 (8 ties); beaten by v21 |
| v14_support_caravan | v13 | caravan | archived | mirrored by v13; beaten by v21 |
| v15_adaptive | v14 | — | **broken** | turns=0 in eval → crashes T0; excluded from pool |
| v20_demo_train | v13 | demo-train-offense | archived | LOSES 0-8 vs v14 (ΔHP-40); +8 HP vs turret_castle useful data |
| v20_sidelane | v13 | sidelane-adaptation | keep | ties both mirrors; `_pick_spawn_side` helper reusable |
| v21_scout_probe | v13 | probe-recon (**invention #1**) | keep | ties both mirrors; heatmap needs stronger offense to leverage |
| v20_burst_scout | v13 | burst-anti-caravan | keep | 3 real ties vs v14 (5 crash=race condition); burst alone insufficient |
| v20_support_kill | v13 | support-kill | archived | 0-0-8 vs v14; Support-kill Demo doesn't break mirror |
| v20_flank_edge | v20_sidelane | flank-edge-bypass | **archived regression** | 0-8 vs v14 AND 0-8 vs opp_scout_rush (ΔHP-50) — edge paths are death-traps |
| v20_hybrid_turtle_cracker | v13 | classifier-dispatch | archived | 0-6 vs v14 (classifier picks Demo when shouldn't); needs better classifier |
| **v21_temporal_phase_gate** | v13 | **temporal-gate (invention #2)** | **champion candidate** | **40/40 @ n=8 sequential; beats v13 ΔHP+7, v14 ΔHP+42; validating at n=15** |

## Inventions committed: 2/3
1. v21_scout_probe (probe-recon heatmap)
2. v21_temporal_phase_gate (opponent-MP-delta deferral) — **CHAMPION CANDIDATE**

## Wave-3 queue (from RANKED.md ranked-loss analysis)
- **v22_oleh_counter** (P5) — anti-oleh-v2 scout_rush (7/9 losses). Pre-commit 2 upgraded Turrets at oleh-preferred corner + Interceptor reserve at enemy-MP>=12.
- **v22_siege_breaker** (P5) — anti-turret_castle (7 losses, all T100 timeouts). Demo train 8× every 3 turns once MP bank ≥24. Never play pure-defensive endgame vs turtler.
- **v22_demo_gate** (P4) — anti-demo_line at 1800+ ELO (3/6 wins, 50/50 coin flip). 2 upgraded Turrets at [10,12]/[17,12] by T8.
- **v22_adaptive_corner** (P3) — defense-reactive; inspect opp first-5-turn structures; bias deploys to sparser side.
- **v22_phase_gate_hybrid** (P5) — v21 + oleh_counter + siege_breaker combined.

## Third invention queued
- **v21_quadrant_adaptive_support** (invention #3) — compute defense-burden per NW/NE/SW/SE; deploy supports to highest-burden quadrant. Will build after v22 counters.

## Adversary pool (3/20)
Existing: opp_scout_rush, opp_demo_line, opp_turret_castle. Need: opp_oleh_mimic, opp_caravan_max, opp_anti_burst, opp_flank_block, opp_demo_high_elo, etc.
