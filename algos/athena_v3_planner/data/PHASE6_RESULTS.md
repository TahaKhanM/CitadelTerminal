# Phase 6 — Bestof 20 Validation Results — DEFERRED to Phase 6B

Phase 6A shipped milestones I + J + K within the 30 min agent budget
but did not run the bestof 20 validation against either baseline.
This file is reserved for Phase 6B's results.

## Reproduce (Phase 6B)

```bash
# vs v13_second_ring (Phase 5B baseline: 10-10, LB=0.30)
/bestof 20 athena_v3_planner v13_second_ring

# vs athena_baseline_lostkids (Phase 5B baseline: 20-0, LB=0.84)
/bestof 20 athena_v3_planner athena_baseline_lostkids
```

Both runs should be archived under
`replays/bestof_athena_v3_planner_vs_<baseline>_<timestamp>/`.

## Phase 5B baseline (the bar to beat)

| Baseline                    | Wins  | Win rate | Wilson 95% LB |
|-----------------------------|-------|----------|---------------|
| v13_second_ring             | 10/20 | 50.0%    | **0.300**     |
| athena_baseline_lostkids    | 20/20 | 100.0%   | **0.839**     |

## Confirmation that Phase 6A integration is live

Smoke match (single match vs v13_second_ring) on 2026-04-25:

- `[arbiter] map-elites archive loaded: 22/64 cells` printed at
  game start.
- `[arbiter] turn=N archive_cands=1..3` on every offensive turn
  through turn 99 (logs are in match stderr; replay was not
  archived from the smoke since results aren't claimed).
- 100 turns reached, no watchdog fires, no crashes.
