# VARIANTS — every algo built, status, parent, notes

Status: active / archived / champion / broken / pending-eval

| version | parent | tag | status | notes |
|---|---|---|---|---|
| v13_second_ring | (baseline) | incumbent | co-champion | mirrors v14 (8 ties) |
| v14_support_caravan | v13 | — | co-champion | mirrors v13 (8 ties) |
| v15_adaptive | v14 | — | **broken** | turns=0 in eval → crashes or collapses T0 |
| v20_demo_train | v13 | demo-train-offense | pending-eval | Demolisher+Support train from y=10; 4 upgraded Supports at [11,13],[13,13],[14,13],[16,13]; demo trigger `MP ≥ 4×demo_cost` |
| v20_sidelane | v13 | sidelane-adaptation | pending-eval | east-west spawn bias from opponent x-distribution (threshold ±3) |
| v21_scout_probe | v13 | probe-recon **(invention #1)** | pending-eval | probe Scouts T5-T8 at alternating edges build live damage heatmap; score spawns by heatmap |

## Queued next wave (build after eval returns)
- v20_classifier_gated (Lostkids batch-count + FUNNEL 4-stage side predictor)
- v20_sim_scored (use athena SimCore to score 10+ candidate plans per turn)
- v20_edge_block_remove (spawn/upgrade/remove [0,13]+[1,13] per finalist consensus)
- v20_upgraded_turret_dense (replace v13 wall-heavy with upgrade-heavy)
- v21_temporal_phase_gate (invention #2 — gate attack on opponent MP delta)
- v21_quadrant_adaptive_support (invention #3 — deploy Supports by NW/NE/SW/SE burden)
- v20_sd_corridor (SD funnel 5+ tile dead-ends)
- v20_mixed_wave (Interceptor+Scout+Demolisher sequenced wave)
