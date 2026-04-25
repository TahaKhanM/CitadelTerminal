# Phase 5 — End-to-end smoke test

**Date**: 2026-04-25
**Match**: athena_v3_planner (full integrated) vs v13_second_ring
**Match length**: 100 turns (full game)

## Result

| Metric | Value |
|---|---|
| Winner | **athena_v3_planner** (p1) |
| Turn count | 100 (full game) |
| athena crashed | False |
| athena timeout_death | False |
| athena time_damage_taken | 0 |
| athena total_computation_time | 618 ms |
| athena avg time/turn | ~6.2 ms |
| v13 total_computation_time | 677 ms |
| Watchdog fires | 0 |
| BudgetExceeded events | 0 |
| Engine exceptions | 0 |

## What ran

The integrated Athena algo's per-turn flow (`EconomyArbiter.execute`):

1. **Defense pipeline** — full Phase 2 archetype (`v_funnel.json`):
   `build_default_defences -> refund_low_health_structures -> max_heap_repair
   -> probabilistic_placement (when SP > 4) -> edge_block_and_remove
   -> reactive_to_breach`. All six primitives executed.
2. **Offense pipeline** — Phase 4 beam search:
   `adapt_game_state -> generate_candidates -> beam_search`. Picked a
   non-hoard plan on every turn that had MP. Sample picks (from stderr):
   - turn 1: `corner_dive_left` (1 deploy)
   - turn 6-25: `interceptor_screen` (4 deploys/turn)
   - turn 26+: rotating `scout_rush_left` / `demo_train_left` /
     `escorted_mixed_left` (3-11 deploys/turn)
3. **Watchdog**: never fired. Per-turn budget caps respected.

## Caveats / followups for Phase 5B

- `sims=0` on every beam_search invocation. The arbiter passes
  `opp_actions_top_k=[]` because `action_predictor` is not yet wired into
  the arbiter (the wiring requires the trackers→features mapping to be
  calibrated first, which is Phase 5B work). With empty `opp_actions`,
  beam_search auto-falls back to the heuristic-only path. Once the
  predictor is wired, `sims > 0` will appear and the beam search will
  use real sim_rs rollouts (the adapter is now production-ready as of
  Phase 5 milestone A).
- Single match only — Wilson 95% LB requires `/bestof 20`. That run is
  Phase 5C / Phase 5B validation milestone. Acknowledged in the brief
  that the bestof can be deferred under time budget pressure; the
  integration is the main deliverable.
- "Checked for stationary unit outside of arena bounds" warning printed
  many times during the match. This comes from gamelib's
  `contains_stationary_unit`, called by defense.py with off-board
  coordinates. Defensive printout, not an error — but worth tightening
  the bounds check upstream as a Phase 5B cleanup.

## Reproduce

```bash
PYTHON_CMD=/opt/miniconda3/bin/python3.13 \
  /opt/miniconda3/bin/python3.13 \
  C1GamesStarterKit-master/scripts/run_match.py \
  /Users/tahakhan/.../algos/athena_v3_planner \
  /Users/tahakhan/.../algos/v13_second_ring
```

Replay archived at:
`C1GamesStarterKit-master/replays/p1-25-04-2026-02-02-38-1777078958799-780320578.replay`
