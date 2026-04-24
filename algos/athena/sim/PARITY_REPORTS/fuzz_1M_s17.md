# 1,000,000-config fuzz round — seed 17

Run via `python3 -m algos.athena.sim.fuzz --n 1000000 --seed 17`
on 2026-04-24, as the second of the two independent-seed 1M rounds
required by P3.

## Result

```
fuzz n=1000000 seed=17 wall=5127.5s
  total PASS=1000000 FAIL=0
    empty_board          pass=83334 fail=   0
    single_mobile        pass=83334 fail=   0
    wall_maze            pass=83334 fail=   0
    turret_wall_mix      pass=83334 fail=   0
    support_heavy        pass=83333 fail=   0
    support_upgraded     pass=83333 fail=   0
    scout_swarm          pass=83333 fail=   0
    demo_column          pass=83333 fail=   0
    interceptor_mass     pass=83333 fail=   0
    se_destruct_dead_end pass=83333 fail=   0
    edge_corner_spawn    pass=83333 fail=   0
    max_budget           pass=83333 fail=   0
```

**1,000,000 PASS / 0 FAIL** across all 12 categories.
Wall 5,127.5 s = 85 min 27 s. Aggregate throughput ~195 cfg/s.

## Gate status — P3 CLOSED

**Round 1 (seed=42):** 1,000,000 PASS / 0 FAIL — see
  `fuzz_1M_s42.md`.
**Round 2 (seed=17):** 1,000,000 PASS / 0 FAIL — this report.

**Two consecutive 1M fuzz batches with different seeds produced
zero unfixed misses.** Stability proven; the 12-category stratified
generator covers the reachable state space (walls, SD dead ends,
scout swarms, max budget, etc.) to P3's floor.

## Regression directory

`algos/athena/sim/fuzz_regression/` remains empty — no configs
persisted because no divergence was observed across either round.
