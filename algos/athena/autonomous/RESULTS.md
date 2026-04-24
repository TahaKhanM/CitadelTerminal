# RESULTS — measured win rates + CIs

All evals n=4 × 2 sides = 8 games per opponent (smoke level).

## BASELINES

### v13_second_ring — incumbent
| opp | W-L-T | WR | ΔHP | turns | note |
|---|---|---|---|---|---|
| v14_support_caravan | 0-0-8 | 0% | +0 | 100 | **mirror tie** |
| v15_adaptive | 8-0-0 | 100% | +41 | 0 | v15 broken |
| opp_scout_rush | 8-0-0 | 100% | +46 | 26 | crush |
| opp_demo_line | 8-0-0 | 100% | +48 | 25 | crush |
| opp_turret_castle | 8-0-0 | 100% | +1 | 100 | narrow turtle win |

### v14_support_caravan — co-champion
Mirror of v13: 0-0-8 vs v13, 8-0 vs everything else (similar ΔHP pattern).

## WAVE-1 VARIANTS (2026-04-24, cycle 1)

### v20_demo_train (parent=v13, tag=demo-train-offense)
| opp | W-L-T | WR | ΔHP | turns |
|---|---|---|---|---|
| v13_second_ring | 0-0-8 | 0% | +0 | 100 |
| **v14_support_caravan** | **0-8-0** | **0%** | **-40** | 99 |
| v15_adaptive | 8-0-0 | 100% | +41 | 0 |
| opp_scout_rush | 8-0-0 | 100% | +46 | 26 |
| opp_demo_line | 8-0-0 | 100% | +48 | 25 |
| opp_turret_castle | 8-0-0 | 100% | **+8** | 100 | cleaner crack than v13 (+1) |

**Verdict:** archived. Demolishers are actively WORSE vs v14 Support Caravan (−40 HP delta). The Caravan shields too effectively. BUT: +8 HP advantage vs turret_castle shows Demolisher archetype DOES help vs turtles. Useful data; keep for hybrid.

### v20_sidelane (parent=v13, tag=sidelane-adaptation)
| opp | W-L-T | WR | ΔHP | turns |
|---|---|---|---|---|
| v13_second_ring | 0-0-8 | 0% | +0 | 100 |
| v14_support_caravan | 0-0-8 | 0% | +0 | 100 |
| v15_adaptive | 8-0-0 | 100% | +41 | 0 |
| opp_scout_rush | 8-0-0 | 100% | +46 | 26 |
| opp_demo_line | 8-0-0 | 100% | +45 | 25 |
| opp_turret_castle | 8-0-0 | 100% | +1 | 100 |

**Verdict:** neutral. East-west adaptation didn't break either mirror, but no regressions. Keep the `_pick_spawn_side` helper — reusable by future variants (wave-2 flank-rush can layer on top).

### v21_scout_probe (parent=v13, tag=probe-recon, invention #1)
| opp | W-L-T | WR | ΔHP | turns |
|---|---|---|---|---|
| v13_second_ring | 0-0-8 | 0% | +0 | 100 |
| v14_support_caravan | 0-0-8 | 0% | +0 | 100 |
| v15_adaptive | 8-0-0 | 100% | +41 | 0 |
| opp_scout_rush | 8-0-0 | 100% | +46 | 26 |
| opp_demo_line | 8-0-0 | 100% | +48 | 25 |
| opp_turret_castle | 8-0-0 | 100% | +2 | 100 |

**Verdict:** neutral-minor. Heatmap helps with turret_castle slightly (+2 vs v13's +1) but doesn't break v13/v14 mirror. Invention #1 has useful ideas but needs a stronger OFFENSIVE archetype to leverage the heatmap data.

## SYNTHESIS

The mirror ceiling holds. Three types of variants all tied or underperformed:
1. Defense+offense swap (Demo train): lost 8-0 to v14
2. Spawn adaptation: tied both
3. Live heatmap recon: tied both

**v14 Support Caravan is the true barrier**, not v13's defense. Any new champion MUST defeat v14 first.

### Wave-2 anti-caravan thesis
To beat v14:
1. **v20_burst_scout** — spawn 15+ Scouts single frame (MP hoarded to 15+). Raw damage 15×2=30/frame outpaces Support shield regen (shield applies ONCE per pair per frame per decompiled engine, and decays by decay_per_frame, so a single burst frame has capped shield).
2. **v20_support_kill** — Demolishers targeting the Support block [11-16, 13] directly. Supports have 40 HP upgraded → 2 Demolisher shots = 16 damage → need 3 Demolishers per Support OR coordinated multi-Demo focus.
3. **v20_flank_edge** — spawn [0,13] or [27,13] and path along y=13 hugging the edge. Bypasses central Caravan range (shield range 7 from center). Finalist `v20_edge_block_remove` layered with flanking.

These are prioritized P5 for wave-2.
