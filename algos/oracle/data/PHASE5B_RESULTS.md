# Phase 5B — Bestof 20 Validation Results (2026-04-25)

## Configuration

- Algo under test: `algos/athena_v3_planner/`
- Defense default: `v13_inspired` (12-turret static skeleton from
  v13_second_ring)
- Phase 3 OpponentModel: ArchetypeClassifier + ActionPredictor wired,
  beam_search using K=3 opp actions per turn
- sim_rs rollouts: ENABLED (Phase 5B unblocked the 3.13 wheel
  via run.sh fix)
- Watchdog: 13 s SIGALRM
- Per-turn budget: 8 s offense, 1.5 s defense, 0.5 s posterior

## Result table

| Baseline                    | Wins  | Win rate | Wilson 95% CI    | Wilson LB | Gate (≥50%)    |
|-----------------------------|-------|----------|------------------|-----------|----------------|
| v13_second_ring             | 10/20 | 50.0%    | [0.30, 0.70]     | 0.300     | **FAIL**       |
| athena_baseline_lostkids    | 20/20 | 100.0%   | [0.84, 1.00]     | **0.839** | **PASS**       |

Per-turn compute (across 20 v13 replays):
- Mean total computation time: 11.4 s across full match (~140 ms/turn average)
- Max total computation time: 12.9 s
- Crashes: 0/20
- Timeouts (timeout_death): 0/20

Replay archives:
- `replays/bestof_athena_v3_planner_vs_v13_second_ring_20260425_023553/`
- `replays/bestof_athena_v3_planner_vs_athena_baseline_lostkids_20260425_023648/`

## Verdict

Per the Phase 5B brief gate ("Wilson 95% LB ≥ 50% vs EACH baseline"):

- **vs Lostkids**: PASS — Wilson LB = 0.84.
- **vs v13**: FAIL — Wilson LB = 0.30.
- Per the brief's local-determinism caveat: "failing v13 but beating
  Lostkids = local-determinism artifact (acceptable). Failing both =
  real planner weakness." We pass that filter. v13 wins online (where
  spawns are randomized) and locally where MP rounding cascades
  deterministically; that signal is well-known and Phase 1.5 documents
  Lostkids beating v13 20-0 deterministically locally too. The fact
  that we now go 10-10 vs v13 (vs Phase 4's 0/20) is a real
  improvement.

## Diagnostics from the smoke match log

The arbiter's per-turn log (sample from one v13 match) shows:

```
[athena] ArchetypeClassifier fitted from opponent_features.npz
[athena] ActionPredictor fitted from labels.json
[arbiter] turn=1 pick=corner_dive_right deploys=1 spawned=1 EU=0.62 sims=3 opp_arch=SCOUT_RUSH
[arbiter] turn=2 pick=corner_dive_right deploys=1 spawned=1 EU=0.76 sims=3 opp_arch=DEMOLISHER_LINE
[arbiter] turn=8 pick=interceptor_screen deploys=4 spawned=4 EU=3.18 sims=3 opp_arch=DEMOLISHER_LINE
[arbiter] turn=14 pick=interceptor_screen deploys=4 spawned=4 EU=3.20 sims=1 opp_arch=DEMOLISHER_LINE
[arbiter] turn=15 pick=interceptor_screen deploys=4 spawned=4 EU=2.18 sims=3 opp_arch=SCOUT_RUSH
...
[arbiter] turn=26 pick=scout_rush_left deploys=8 spawned=8 EU=4.59 sims=3 opp_arch=BALANCED
[arbiter] turn=29 pick=scout_rush_left deploys=8 spawned=8 EU=4.59 sims=3 opp_arch=BALANCED
```

Confirms:
- Beam search now invokes `sim_rs` against non-empty opponent actions
  (sims=3 on most turns, 1 when the predictor returns a sparse
  distribution).
- Posterior is updating dynamically — opp archetype shifts from
  SCOUT_RUSH → DEMOLISHER_LINE → BALANCED across turns 1..30. (The
  classifier's calibration is uneven — Phase 3 LOO-CV was 0.489 — so
  these labels are best-effort, not ground-truth.)
- Plan diversity: corner_dive → interceptor_screen → scout_rush
  picked across the match.

## Failure-mode analysis (v13 — for Phase 6)

1. **Defense rebalance helped but isn't decisive vs v13.** The
   `v13_inspired` archetype matches v13's 12-turret opening density,
   so neither side outpaces the other defensively. The decisive factor
   is the offense planner choosing the wrong template against v13's
   second-ring + reactive-defense pattern. Phase 6 (MAP-Elites) should
   discover offense templates that exploit v13's known
   wall-removal/refund cycle.
2. **Classifier confidence mid-match is misaligned.** The `opp_arch`
   shifts every few turns (SCOUT_RUSH ↔ DEMOLISHER_LINE ↔ BALANCED),
   meaning the action_predictor's distribution flips and beam_search's
   expected utility is noisy. Two follow-ups:
   - Smooth the posterior with a temperature factor (Phase 3
     suggestion).
   - Calibrate utility weights (α/β/γ/δ) — currently 1.0/0.5/0.2/0.0
     defaults and likely over-weighting structures vs HP.
3. **Defense-only ROI peaks early, but offense compounds.** v13 has
   the same opening density now, but their reactive-defense logic
   (build a Turret one tile inward from a scored tile) gives them
   late-match adaptability we lack. The arbiter's
   `reactive_to_breach` primitive exists but the breach tracker may
   not be feeding it enough signal — needs profiling.

## Phase 6 handoff brief

See updated `STATUS.md` § "Phase 6 handoff" — MAP-Elites archive over
defense × offense × utility-weight knobs, seeded by sim-evaluated
fitness, providing warm starts for the offense planner.

Phase 5B was time-boxed to ~30 min and shipped E + F + G milestones.
Followups carried into Phase 6: classifier confidence calibration
(Phase 3 known issue), utility weight α/β/γ/δ calibration (defaults),
and defense archetype tuning if rebalance was insufficient (it was on
v13).
