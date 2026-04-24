# IDEAS — priority-scored backlog

Updated cycle 2 with wave-1 lessons. **v14 Support Caravan is the target to beat.**

## P5 (wave-2, build immediately)

1. **(P5)[variant] v20_burst_scout** — Override v13's Scout threshold from `10 × scout_cost` to `14-16 × scout_cost`. Hoard MP aggressively, dump 15+ Scouts single frame. Raw damage 30+/frame overwhelms Support shield regen (shield applies ONCE per pair; stack decays quickly). Thesis: this is the core anti-caravan tactic.
2. **(P5)[variant] v20_support_kill** — Demolisher-focused offense targeting the opponent's Support block at [11-16, 13]. Since upgraded Supports have 40 HP and Demolishers do 8 dmg/frame, 5 Demolisher shots kill one Support. Spawn demolishers with a Supported path passing through the caravan zone to hit the Supports head-on. Defeats v14 by neutralizing its engine.
3. **(P5)[variant] v20_flank_edge** — Spawn at [0,13] or [27,13] edges; path hugs y=13 along the edge. Shield range is 7, so at x=0 y=13 the caravan at [11-16,13] provides 0 shield coverage. Edge-run bypasses the caravan completely. Layer on top of v20_sidelane's `_pick_spawn_side`.
4. **(P5)[variant] v20_hybrid_turtle_cracker** — Merge v20_demo_train (which crushed turret_castle with +8 HP advantage) with v13's Scout offense (which ties v14 but wins elsewhere). Classify opponent turn-0 to turn-5; if turret-heavy dispatch demo, else Scout. This is the first true **classifier-gated** variant.
5. **(P5)[invention][variant] v21_temporal_phase_gate** — **Invention #2.** Track opponent MP delta per turn. If opponent MP jumped >25 in one turn → they attacked → defer our attack 2 turns, reinforce defense, then counter-attack. No finalist algo does this.

## P4 (wave-3)

6. **(P4)[variant] v20_sim_scored** — athena SimCore scores 10 candidate plans/turn; pick highest breach+HP-delta EV. Needs pathfinding wrap to evaluate plans fast.
7. **(P4)[variant] v20_edge_block_remove** — spawn [0,13]+[1,13] → upgrade → remove same turn. Per finalist consensus; v13 doesn't do this.
8. **(P4)[invention][variant] v21_quadrant_adaptive_support** — **Invention #3.** Compute defense-burden per NW/NE/SW/SE quadrant; place Supports to highest-burden quadrant. Adaptive.
9. **(P4)[variant] v20_upgraded_turret_dense** — replace v13 wall-heavy with upgrade-heavy defense. Competition boost makes upgrades overpowered.
10. **(P4)[variant] v20_mixed_wave** — Interceptor + Scout + Demolisher sequenced wave (Interceptors tank frame-1, Scouts follow shielded, Demos clean behind).

## P3 adversaries (stress-testers, build in parallel with wave-2)

11. **(P3)[adversary] opp_caravan_max** — extreme v14 variant: 8 upgraded Supports with aggressive hoarding. If our champion beats this, it beats v14.
12. **(P3)[adversary] opp_anti_burst** — many mid-range Interceptors to eat mass-Scout bursts.
13. **(P3)[adversary] opp_flank_block** — structures at corners to deny [0,13]/[27,13] spawns.
14. **(P3)[adversary] opp_anti_demolisher** — Interceptor spam at y=10 to pick off Demos before they hit.
15. **(P3)[adversary] opp_sd_defense** — max-distance wall setup so enemy SDs fire far from own defense.
16. **(P3)[adversary] opp_early_breach_rush** — T0-T3 all-in Scout rush.
17. **(P3)[adversary] opp_late_game_scale** — turtle to T50 then scale.
18. **(P3)[adversary] opp_mirror_exploiter** — plays differently each side to test our side-detection.

## P2 (if plateau)

19. **(P2)[tool] SimCore PyO3 beam search** — width 50 depth 3 via Rust.
20. **(P2)[tool] self-play evolution** — ELO tournament over top 15 variants, mutate/crossover.
21. **(P2)[variant] v20_markov_rebuild** — prediction gated by opponent MP velocity.

## Lessons from wave-1 applied

- Mirror ties imply offensive-archetype change is NOT sufficient by itself (v20_demo_train's pure archetype switch lost to v14). It needs to be coupled with either burst damage or target selection.
- v13 narrowly beats opp_turret_castle (+1 HP); v20_demo_train crushes it (+8 HP). Turtles die to Demolishers.
- All 3 wave-1 variants crushed opp_scout_rush / opp_demo_line / opp_turret_castle (aside from thin turtle wins) → adversary pool needs diversification.
