# Phase 6B — Bestof 20 Validation Results (2026-04-25)

## Configuration

- Algo under test: `algos/athena_v3_planner/`
- Phase 6A integration: MAP-Elites archive **loaded** from
  `data/map_elites_archive.json` (22/64 cells filled,
  `archive_sample_k=10`).
- Defense default: `v13_inspired` (12-turret static skeleton, Phase 5B).
- Phase 3 OpponentModel: ArchetypeClassifier + ActionPredictor wired,
  beam_search using K=3 opp actions per turn (Phase 5B).
- sim_rs rollouts: ENABLED.
- Watchdog: 13 s SIGALRM.
- Per-turn budget: 8 s offense, 1.5 s defense, 0.5 s posterior.

## Result table — three policies tested

| Baseline           | Phase 5B (no arch.) | Phase 6A no-gate | Phase 6B gate=0.6 | **Phase 6 default (gate=1.01 — disabled)** |
|--------------------|---------------------|------------------|-------------------|--------------------------------------------|
| v13_second_ring    | 10/20 LB 0.300      | 15/20 LB 0.531   | 8/20 LB 0.219     | (smoke: P1 wins 40 to -2)                  |
| Lostkids           | 20/20 LB 0.839      | 14/20 LB 0.481   | 10/20 LB 0.299    | (smoke: P1 wins 18 to -4)                  |

(See replay archives under `replays/bestof_athena_v3_planner_vs_*_20260425_*` for raw 40 game data.)

Per-turn compute (across sampled v13 replays from Phase 6B no-gate run):
- Mean P1 turn time: ~245 ms (Phase 5B was ~140 ms — slight uplift from
  archive candidate generation when gate is open).
- Median P1 turn time: ~38 ms.
- Max single turn: 14.7-15.0 s on turn 1 (cold start: classifier fit +
  archive deserialise + sim_rs warmup; within 15 s budget).
- Crashes: 0/40. Timeouts: 0/40.

## Verdict — ARCHIVE: DISABLED — regression vs Phase 5B confirmed; pending Phase 6C fitness re-tune

Per the Phase 6B brief's hard constraint: **"Never let Athena's
effective performance drop below Phase 5B's. If archive hurts,
disable it."**

The bestof matrix shows BOTH archive-enabled policies (no-gate and
gate=0.6) regress vs Phase 5B's 20-0 Lostkids result (LB 0.839):
- no-gate: −0.36 LB Lostkids regression (despite +0.23 LB v13 gain).
- gate=0.6: −0.54 LB Lostkids regression AND −0.08 LB v13 regression.

The no-gate v13 improvement (15-5 LB 0.531) is real and statistically
significant, but it cannot offset the Lostkids regression under the
brief's per-baseline floor rule.

## Decision (Milestone N) — Path B with capability preserved

The archive is **loaded** but **disabled by default** via
``archive_confidence_threshold=1.01`` (max possible posterior is 1.0,
so the gate is never satisfied). All Phase 6A integration code remains
in place; users can re-enable for experimentation by passing a lower
threshold:

```python
# algo_strategy.py overrides (for Phase 6C / experimentation)
EconomyArbiter(
    ...,
    archive_path=archive_file,
    archive_confidence_threshold=0.0,  # = Phase 6A always-on
    # archive_confidence_threshold=0.6,  # = Path C confidence gate
)
```

Smoke matches with the default (gate=1.01, archive disabled):
- vs v13_second_ring: Athena wins 40 to −2 (80 turns, P1).
- vs athena_baseline_lostkids: Athena wins 18 to −4 (40 turns, P1).

This restores Phase 5B-equivalent behavior on the offense pipeline:
``generate_candidates`` is called with ``archive=None`` on every turn,
making candidate enumeration byte-identical to Phase 5B.

## Why the archive regressed Lostkids

Looking at the no-gate side-split:
- vs Lostkids P1=athena: 10/10 wins (clean sweep).
- vs Lostkids P1=lostkids: 4/10 wins (regressed from Phase 5B's likely
  10/10).

The 22-cell archive over-fits archetype-similar opponents:
- v13_proxy in fitness eval uses the **same** ``v13_inspired`` defense
  the live v13 algo uses. Archive-derived candidates' deploy patterns
  align with v13's reactive-defense gaps.
- lostkids_proxy in fitness eval uses ``v_funnel`` defense, but
  Lostkids' actual offense pattern (dual-flank + corner-dive)
  significantly drifts from the archive's ``scout_flood`` /
  ``scout_rush_left`` clustering. Archive-perturbed candidates
  destabilise the Phase 5B mirror-tuned templates that empirically
  swept Lostkids.

The classifier-confidence gate (Path C) didn't recover this because
the Phase 3 classifier's calibrated max-posterior rarely exceeds 0.6
(LOO-CV is 0.489). At 0.6 threshold, the archive is gated off most
turns, but the gate-state transitions perturb the deterministic
template-pick path enough to break Lostkids on P1=lostkids side too.

## Reproduce

```bash
# bestof 20 (archive disabled — Phase 6 default)
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner v13_second_ring 10
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner athena_baseline_lostkids 10

# Re-enable archive for Phase 6C experimentation:
# Edit algos/athena_v3_planner/algo_strategy.py — pass
# archive_confidence_threshold=0.0 (always-on) or 0.6 (Path C) into
# the EconomyArbiter constructor, then re-run bestof.
```

## Phase 6 followups (carried into Phase 6C / Phase 8)

1. **Refine the fitness harness** so archive elites cover Lostkids
   (V-funnel) too. The current sim is 12 rounds, 2 baselines —
   Lostkids-proxy is one of them, but the 12-round horizon biases
   against late-burst genomes. Extend to 25-30 rounds.
2. **Add archetype-conditional archive cells** — instead of one global
   archive, maintain per-archetype archives so beam_search consults
   only cells fit for the current classifier's max-posterior archetype.
3. **Tighten the Phase 3 classifier** (LOO-CV 0.489 → ≥0.7 target).
   The confidence gate is a band-aid for the underlying calibration
   problem; once LOO-CV climbs above 0.7, the 0.6 threshold becomes
   meaningful again.
4. **Add fitness tiebreakers** (MP efficiency, structures killed,
   per-frame breach scoring) to break the −9.0 ties currently
   crowding 22 cells of the archive at the same fitness.
5. **Sweep `archive_sample_k`** from 5 → 20 once the fitness function
   is healthier; current 10 may be too aggressive when the archive's
   diversity is low.

## Phase 7 recommendation

Per the Phase 6B brief: "If archive was disabled (Path B), skip Phase
7 and recommend Phase 8 (self-play hardening) instead."

LLM-FunSearch (Phase 7) generates novel offense templates via an
LLM-evaluated candidate-generation loop, hooking into the same
``MAPElitesArchive``. With the archive currently regressing vs
Phase 5B, generating MORE candidates that flow into the same archive
would compound the over-fit. **Recommended next phase: Phase 8
(self-play hardening) — generate diverse opponent replays via
self-play, expand the corpus, retrain the classifier toward LOO-CV
≥0.7, then re-tune the archive (Phase 6C) on the broader corpus.**

If Phase 8 is bypassed, Phase 6C alone (fitness re-tune,
archetype-conditional archive cells, tiebreakers) should be the next
milestone before Phase 7.
