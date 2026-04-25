# Phase 8 final smoke match — Athena vs v13_second_ring

Date: 2026-04-25
Setup: single full match (P1=Athena, P2=v13_second_ring), 100-turn cap.

## Command

```bash
/opt/miniconda3/bin/python3.13 \
    C1GamesStarterKit-master/scripts/run_match.py \
    /abs/path/algos/athena_v3_planner \
    /abs/path/algos/v13_second_ring
```

## Replay

`replays/phase8_smoke/athena_v3_vs_v13_second_ring.replay`
(7.5 MB, 2884 frames, 100 turns).

## End-state metrics

| Metric | Player 1 (Athena) | Player 2 (v13) |
|---|---|---|
| Crashed | **false** | false |
| timeout_death | **false** | false |
| time_damage_taken | **0** | 0 |
| Points scored | 1.0 | 7.0 |
| Total computation time (ms) | 6365 | 897 |
| Stationary resource spent | 583 | 207 |
| Dynamic resource spent | 761 | 152 |
| Dynamic resource destroyed | 760 | 137 |
| Stationary resource left on board | 296 | 166 |
| **Winner** | — | **P2 (v13)** |

Match duration: 12,941 ms wall.

## Per-turn timing

- Total Athena compute over 100 turns: 6,365 ms.
- Average per-turn: **~63 ms / turn**.
- Watchdog limit: 13,000 ms / turn → **0.5 % of budget**.
- p99 per-turn: not directly measurable from end-stats, but the
  `time_damage_taken=0` confirms no turn ever crossed the 15 s
  punitive threshold; combined with the 6.4 s cumulative total, no
  single turn could have been > 6.4 s, comfortably within the 13 s
  internal watchdog.

## Verdict

| Check | Status |
|---|---|
| No crash | **PASS** |
| Watchdog never fires | **PASS** |
| p99 turn time ≤ 13 s | **PASS** (≤ 6.4 s upper bound) |
| Production safety wrappers intact | **PASS** (no fallback path triggered) |
| Per-turn arbiter log present | **PASS** (turn=1..100 lines visible) |

Outcome (P2 win) is the well-documented Phase 5B / Phase 6B local
v13 mirror-ceiling artifact (G6 caveat in FINAL_REPORT.md § 4). The
relevant signal here is that Athena ran without errors and stayed
within compute budget for all 100 turns. The G11 replay-trace
(Phase 7) is the live-ladder predictor; this smoke is purely a
production-safety sanity check.

## Sample arbiter log lines (from match stderr)

```
[arbiter] turn=1 pick=corner_dive_right deploys=1 spawned=1 EU=0.62 sims=3 opp_arch=SCOUT_RUSH
...
[arbiter] turn=84 pick=scout_rush_center deploys=10 spawned=10 EU=6.90 sims=3 opp_arch=BALANCED
[arbiter] turn=99 pick=scout_rush_center deploys=10 spawned=10 EU=7.00 sims=3 opp_arch=BALANCED
[arbiter] turn=100 pick=scout_rush_center deploys=10 spawned=10 EU=7.00 sims=3 opp_arch=BALANCED
```

Confirms beam search, sim_rs rollouts (sims=3), and posterior update
(opp_arch label transitions) all functioning end-to-end.
