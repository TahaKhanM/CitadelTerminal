# Phase 6 — MAP-Elites archive (2026-04-25)

## Configuration

- Grid:                  **8x8 = 64 cells**
- Iterations:            **500** (200 also runs in <2s; we used 500
                         for tighter coverage saturation)
- Init genomes:          10 random
- Seed:                  42
- Sim rounds per match:  12
- Matches per baseline:  3
- Baselines:             v13_proxy (defense=v13_inspired,
                         offense=scout_flood),
                         lostkids_proxy (defense=v_funnel,
                         offense=dual_flank)

Per-genome eval cost: ~5-9 ms (Apple M4, sim_rs PyO3 release wheel).
Total run time: **4.1 s** for 500 iterations.

## Behavior space

- **BC1 = mean MP at attack** (proxy for hoarding behavior). Bin edges:
  ``[0, 1.5, 3, 5, 7.5, 10, 13, 17, +inf)``.
- **BC2 = defense density** (count of structures placed at game start by
  the genome, including its priority-1+2+3 archetype tiers). Bin edges:
  ``[0, 8, 12, 14.5, 17, 19.5, 22, 28, +inf)``.

The BC2 axis is genome-driven via ``turret_density_mult``: a high
density genome unlocks priority-3+4 placements, a low density genome
sticks to priority-1.

## Result summary

| Metric                   | Value           |
|--------------------------|-----------------|
| Cells filled             | **22 / 64** (34.4%) |
| Best fitness in archive  | **-9.0**        |
| Archetype distribution   | v_funnel(11) / two_layer_keep(7) / v13_inspired(4) — ``spread_line`` failed to win any cell |
| Template distribution    | scout_flood (9 cells) dominates; followed by scout_rush_left (2), scout_rush_right (2), demo_train_right (3) |

The archive is biased toward low-MP eager genomes (BC1 col=2,
``mean_mp_at_attack ~ 4-5``) — this is a sim-only artifact: with
``mp_hoard_threshold`` mutated near the lower bound, every genome
attacks early. Higher BC1 cells (col=4-7) remain mostly empty because
hoarding under the lightweight 12-round sim doesn't pay off — by round
12 there hasn't been enough MP accrual for late-burst genomes to express
their hoarding payoff. **Phase 6B followup**: extend the harness to 25-
30 rounds OR penalize the early-attack winners to incentivize
exploration of the hoarding regime.

Best-fitness genomes cluster around (4-5 mean MP, 14-28 def density)
which is **not** the v13/lostkids defensive-mass region — the archive
is currently exploring the eager-Scout, dense-defense corner.

## Top-5 genomes (by fitness, ties broken by cell)

| cell    | fitness | beh (mp, dens) | arch       | template          | mp_hoard | density_mult |
|---------|---------|----------------|------------|-------------------|----------|--------------|
| [2, 3]  | -9.00   | (4.6, 16.0)    | v_funnel   | scout_rush_left   | 0.36     | (1.0+)       |
| [2, 6]  | -9.00   | (4.2, 24.0)    | v_funnel   | scout_flood       | 0.36     | (1.3+)       |
| [2, 7]  | -9.00   | (4.2, 28.0)    | v_funnel   | scout_flood       | 0.32     | (1.3+)       |
| [2, 5]  | -9.00   | (4.6, 20.0)    | t_l_keep   | scout_flood       | 0.37     | (1.0+)       |
| [2, 2]  | -9.00   | (4.2, 14.0)    | t_l_keep   | scout_rush_left   | 0.33     | (1.0+)       |

(``t_l_keep`` = ``two_layer_keep``; archetype indices: 0 v_funnel, 1
two_layer_keep, 2 spread_line, 3 v13_inspired.)

## Archive serialization

Saved to: ``algos/athena_v3_planner/data/map_elites_archive.json``.
Format: ``MAPElitesArchive.to_dict()`` (see ``archive/archive.py``).
Each cell entry::

    {"cell":[col,row], "fitness":float, "behavior":[bc1,bc2], "genome":{...}}

## Reproduce

```bash
/opt/miniconda3/bin/python3.13 -m algos.athena_v3_planner.archive.map_elites \
    --iterations 500 --seed 42 --rounds 12 \
    --output algos/athena_v3_planner/data/map_elites_archive.json
```

## Phase 6 followups (carried)

1. **Extend sim harness to 25+ rounds** so hoarding genomes have time
   to express their payoff, populating BC1 cols 4-7.
2. **Refine BC2 binning** — defense density is currently dominated by
   archetype × density_mult bucketing; consider a continuous proxy like
   "SP-spent-by-turn-30" once the harness models per-turn SP income.
3. **Fitness signal is too coarse** — every winner sits at -9 because
   the +/-1 HP rounding plus scout_flood saturation produces tied
   matches frequently. Add tiebreakers (e.g. MP efficiency, structures
   killed) into the aggregate fitness.
4. **Behavior collapse on archetype 2 (``spread_line``)** — no cell in
   the final archive uses it, despite uniform sampling. Mutation
   probably bounces it out fast because it scores worse than v_funnel
   in the lightweight sim. Worth investigating in 6B whether the sim is
   biased against ``spread_line``'s wider deployment area.
