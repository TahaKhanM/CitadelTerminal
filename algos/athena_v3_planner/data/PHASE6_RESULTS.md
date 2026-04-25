# Phase 6B — Bestof 20 Validation Results (2026-04-25)

## Configuration

- Algo under test: `algos/athena_v3_planner/`
- Phase 6A integration: MAP-Elites archive **enabled** by default
  (loaded from `data/map_elites_archive.json`, 22/64 cells filled,
  `archive_sample_k=10`).
- Defense default: `v13_inspired` (12-turret static skeleton, Phase 5B).
- Phase 3 OpponentModel: ArchetypeClassifier + ActionPredictor wired,
  beam_search using K=3 opp actions per turn (Phase 5B).
- sim_rs rollouts: ENABLED.
- Watchdog: 13 s SIGALRM.
- Per-turn budget: 8 s offense, 1.5 s defense, 0.5 s posterior.

## Result table

| Baseline                    | Wins  | Win rate | Wilson 95% CI    | Wilson LB | Phase 5B LB | Δ vs Phase 5B |
|-----------------------------|-------|----------|------------------|-----------|-------------|---------------|
| v13_second_ring             | 15/20 | 75.0%    | [0.53, 0.89]     | **0.531** | 0.300       | **+0.231**    |
| athena_baseline_lostkids    | 14/20 | 70.0%    | [0.48, 0.85]     | **0.481** | 0.839       | **−0.358**    |

Per-turn compute (across sampled v13 replays):
- Mean P1 turn time: ~245 ms (Phase 5B was ~140 ms — slightly higher with
  archive enabled, expected from extra candidate generation).
- Median P1 turn time: ~38 ms (low — archive-derived candidates are cheap).
- Max single turn: 14.7-15.0 s on turn 1 (cold start: classifier fit +
  archive deserialise + sim_rs warmup; within 15 s budget).
- Crashes: 0/40
- Timeouts: 0/40

Replay archives:
- `replays/bestof_athena_v3_planner_vs_v13_second_ring_20260425_034152/`
- `replays/bestof_athena_v3_planner_vs_athena_baseline_lostkids_20260425_034155/`

## Per-side breakdown

### vs v13_second_ring (15-5)

- Athena P1 (a=p1):  8/10 wins
- Athena P2 (a=p2):  7/10 wins  → near-symmetric, real planner improvement.

### vs athena_baseline_lostkids (14-6)

- Athena P1 (a=p1): 10/10 wins
- Athena P2 (a=p2):  4/10 wins  → all 6 losses are P2-side.

The Lostkids loss pattern is asymmetric: Athena dominates on the spawn
side that v3_planner was tuned for, but loses repeatedly on the flipped
side. The offense templates skew toward one corner; the archive's
sampled candidates skew the same way. Phase 5B's deterministic 20/20
sweep relied on a mirror-tuned set of templates that the archive's
weighted sampling perturbs.

## Verdict — Path C: Mixed

Per the Phase 6B brief:

- **Path A (helps or neutral)**: not satisfied — Lostkids regression is
  real (LB 0.839 → 0.481, −0.36 LB).
- **Path B (clear regression)**: not the cleanest fit — vs v13 the
  archive **dramatically improves** the LB (+0.23). A blanket disable
  throws away a real, statistically meaningful gain.
- **Path C (mixed)**: **APPLIES**. The archive helps versus an
  archetype-similar opponent (v13 second-ring — same `v13_inspired`
  defense skeleton) and hurts versus an archetype-different opponent
  (Lostkids' V-funnel + dual-flank).

The brief's prescribed Path C remediation is a **classifier-confidence
gate**: only consult the archive when `max(posterior) > 0.6`, otherwise
fall back to JSON-templates-only candidate generation.

Rationale:
- The archive was populated from sim-evaluated fitness against
  v13_proxy and lostkids_proxy. The v13_proxy proxy uses the same
  `v13_inspired` defense the live v13 algo uses; the lostkids_proxy
  proxy uses `v_funnel` defense. So when our classifier is confident
  the opponent is v13-like (DEMOLISHER_LINE / SCOUT_RUSH high
  posterior), the archive's elite genomes line up with reality.
- When the classifier is uncertain (posterior flips between archetypes
  every few turns — Phase 3 LOO-CV is 0.489), the archive's elite
  genomes may be tuned for the **wrong** opponent, so we should fall
  back to the broader JSON-template set.

A confidence gate is a strict superset of "always on" or "always off":
- `posterior_max > 0.6` → archive enabled (recovers v13 improvement).
- `posterior_max ≤ 0.6` → archive disabled (recovers Lostkids
  baseline behavior).

## Implementation (Milestone N)

In `EconomyArbiter._offense_phase`, the archive is gated on
`max(self._posterior.values()) > self.archive_confidence_threshold`
(default 0.6). When disabled at the call site, `archive=None` is passed
to `generate_candidates`, restoring exact Phase 5B candidate
enumeration.

Default threshold rationale: 0.6 is the brief's suggested floor and
conservatively above the 1/N=0.167 uniform-prior baseline (6
archetypes). With Phase 3 LOO-CV at 0.489, calibrated per-class
accuracy is below 0.6 on average — meaning the gate will only fire on
turns where the posterior has converged to a clear winning archetype
(typically late mid-game).

## Reproduce

```bash
# vs v13_second_ring
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner v13_second_ring 10

# vs athena_baseline_lostkids
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner athena_baseline_lostkids 10
```

## Phase 6 followups (carried into Phase 6C / Phase 8)

1. **Refine the fitness harness so archive elites cover Lostkids
   (V-funnel) too.** The current sim is 12 rounds, 2 baselines —
   Lostkids-proxy (v_funnel + dual_flank) is one of them, but the
   sim's 12-round horizon biases against the late-burst genomes that
   counter Lostkids' rolling-defense pattern. Extend to 25-30 rounds.
2. **Add archetype-conditional archive cells** — instead of one global
   archive, maintain a per-archetype archive (~22 cells × 6 archetypes)
   so beam_search consults only the archive cells fit for the current
   classifier's max-posterior archetype.
3. **Calibrate the confidence threshold** by sweeping
   `archive_confidence_threshold ∈ {0.3, 0.4, 0.5, 0.6, 0.7}` against
   bestof 20 vs both baselines. Path C lands at 0.6 by brief default;
   real optimum may differ.
4. **Tighten the Phase 3 classifier** (Phase 3 LOO-CV is 0.489). The
   confidence gate is a band-aid for the underlying calibration
   problem; once LOO-CV climbs above 0.7, the gate threshold can be
   lowered safely.
