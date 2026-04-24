# IDEAS — priority-scored backlog

Priority 1-5 (5 = highest). Attach `[type]` tag: `[variant]` = new algo, `[adversary]` = stress-tester, `[tool]` = infra, `[invention]` = self-invented archetype.

## IMMEDIATE (P5) — build now

1. **(P5)[variant] v20_demo_train** — Demolisher+Support train from y=10. Demolishers have range 4.5 vs base Turret 2.5; stack 4 upgraded Supports at [11-14,13] for ~40 shield. Counters the "no public algo handles demolisher spam" gap (RA-3).
2. **(P5)[variant] v20_classifier_gated** — Integrate Lostkids-style batch-count classifier + FUNNEL 4-stage side prediction + v13 defense. Dispatch to 3 offensive sub-policies based on turn-0-to-3 read.
3. **(P5)[variant] v20_sidelane** — v13 + east-west spawn adaptation (RA-2 fix #1): measure opponent x-distribution, bias spawn to sparser half. Expected +4-6%.
4. **(P5)[variant] v20_sim_scored** — Use athena SimCore to pre-score 10+ candidate plans per turn; pick the one with highest expected breach+HP delta. Integrates RA-3's "frame-by-frame attack simulator" (unique to FUNNEL in public finalists).
5. **(P5)[invention] v21_scout_probe** — **Invention #1.** Spawn 2-3 probe Scouts at random edges T5-T8 to sample live damage heatmap. Build a per-tile "unsafe" score directly, no batch-counting. No finalist does this.

## HIGH (P4) — queue after immediate batch

6. **(P4)[variant] v20_sd_corridor** — Deliberate 5+ tile dead-end pockets funnel enemy Interceptors for 40-dmg SD. Engine mechanic (L5).
7. **(P4)[variant] v20_upgraded_turret_dense** — Competition-boosted upgraded Turrets (20 dmg, range 3.5). Replace v13's wall-heavy build with upgrade-heavy build. Break the walls/turrets ratio away from base game.
8. **(P4)[variant] v20_aggressive_refund** — Refund walls <50% HP, turrets <30% HP (RA-3 consensus). Remove unattacked-side structures, redeploy to pressure side.
9. **(P4)[invention] v21_temporal_phase_gate** — **Invention #2.** Gate own attack on opponent MP deltas: if opponent MP rose >25 in 1 turn they attacked; defer 2 turns and reinforce. No finalist does this.
10. **(P4)[invention] v21_quadrant_adaptive_support** — **Invention #3.** Compute live "defense burden" per NW/NE/SW/SE quadrant; deploy supports to highest-burden quadrant dynamically. No finalist adapts Support position per opponent layout.

## MEDIUM (P3)

11. **(P3)[variant] v20_mixed_wave** — Interceptor + Scout + Demolisher mixed wave with deliberate unit-type sequencing (Interceptors eat first frame attacks, Scouts follow tanked, Demolishers mop behind).
12. **(P3)[variant] v20_edge_block_remove** — Implement the "edge-block-and-remove" tactic all top algos use but v13 doesn't: spawn [0,13]/[1,13], upgrade, remove same turn.
13. **(P3)[variant] v20_support_damage_weighted** — Adopt GRETCHEN's score: `3.5 × units_to_base + cores_damage − 100 × supports_lost − turret_damage`. Avoid feeding into shielded paths.
14. **(P3)[variant] v20_breach_responsive** — RA-2 fix #3: lower Interceptor threshold when breach rate spikes.
15. **(P3)[variant] v20_turn_scaled_threshold** — RA-2 fix #2: `(8 + 0.1×turn) × scout_cost` up to 12 for Scout attack gate.
16. **(P3)[adversary] opp_anti_demolisher** — Mass Interceptors at y=10 targeting our Demolisher trains.
17. **(P3)[adversary] opp_anti_shield** — Burst (≥10 Demolishers single frame) to outpace shield regen.
18. **(P3)[adversary] opp_anti_corner** — Defense clustered at corners to punish [0,13]/[27,13] spawns.
19. **(P3)[adversary] opp_mirror_exploiter** — Plays asymmetrically on each side; tests if our side-detection works.
20. **(P3)[adversary] opp_v13_mirror_max** — v13 tuned against athena specifically.

## LOW (P2) — only if plateau

21. **(P2)[variant] v20_markov_rebuild** — Revive Markov prediction with improved signal (sensors on opponent MP velocity, not just spawn location).
22. **(P2)[tool]** — Build SimCore beam-search harness (width 50, depth 3) via PyO3 if Rust ≥25K sims/sec.
23. **(P2)[tool]** — Self-play evolution background: 15 candidates in ELO tournament, mutate top 20%.

## SEED INVENTIONS (must build ≥3 unique to pass criterion C)
- **v21_scout_probe** (see #5)
- **v21_temporal_phase_gate** (see #9)
- **v21_quadrant_adaptive_support** (see #10)

(If any of these align with public finalist work uncovered later, replace with new invention. Target: 3 novel at termination.)
