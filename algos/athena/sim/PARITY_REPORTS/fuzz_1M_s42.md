# 1,000,000-config fuzz round — seed 42

Run via `python3 -m algos.athena.sim.fuzz --n 1000000 --seed 42`
on 2026-04-24. Gate: each of the 12 stratified categories runs both
FAST and INSTRUMENTED modes on the same generated state and asserts
byte-identical final state; misses persist to
`algos/athena/sim/fuzz_regression/<category>/<hash>.json`.

## Result

```
fuzz n=1000000 seed=42 wall=5298.6s
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
Wall time 5,298.6 s = 88 min 19 s. Aggregate throughput
~188.7 configs/sec on Python (single-process, sequential).

## Gate status

Round 1 (seed=42): **PASS** (zero unfixed misses).
Round 2 (seed=17): running in background at
`/tmp/fuzz_1M_s17_v2.log`; results will be committed as
`fuzz_1M_s17.md` on completion.

## Regression directory

`algos/athena/sim/fuzz_regression/` remains empty — no configs were
persisted because no divergence was observed.
