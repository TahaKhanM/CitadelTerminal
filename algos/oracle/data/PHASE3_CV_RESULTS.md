# Phase 3 — Leave-one-opponent-out cross-validation

Run via `algos/athena_v3_planner/opponent/cv_runner.py` on the
47-replay v13 corpus indexed at `data/replay_index.json`.

## Headline

- **Mean top-1 accuracy: 0.489** (23/47)
- Distinct opponents (folds): 30
- Validation gate (>=0.70): **FAIL**

## Per-class recall

| archetype | recall | n_held_out |
|---|---|---|
| SCOUT_RUSH | 0.440 | 25 |
| DEMOLISHER_LINE | 0.286 | 7 |
| EDGE_FEINT | 0.000 | 1 |
| SUPPORT_BURST | 0.000 | 1 |
| TURTLE_GRIND | n/a (no test samples) | 0 |
| BALANCED | 0.769 | 13 |

## Confusion matrix

| true \\ pred | SCOUT_RUSH | DEMOLISHER_LINE | EDGE_FEINT | SUPPORT_BURST | TURTLE_GRIND | BALANCED |
|---|---|---|---|---|---|---|
| SCOUT_RUSH | 11 | 0 | 0 | 0 | 0 | 14 |
| DEMOLISHER_LINE | 0 | 2 | 0 | 0 | 0 | 5 |
| EDGE_FEINT | 0 | 0 | 0 | 0 | 0 | 1 |
| SUPPORT_BURST | 0 | 0 | 0 | 0 | 0 | 1 |
| TURTLE_GRIND | 0 | 0 | 0 | 0 | 0 | 0 |
| BALANCED | 1 | 2 | 0 | 0 | 0 | 10 |

## Per-opponent accuracy (top-1)

| opponent | accuracy | n_replays | true | pred |
|---|---|---|---|---|
| R1_Sawtooth | 1/1 | 1 | DEMOLISHER_LINE | DEMOLISHER_LINE |
| R2_Infiltrator | 0/1 | 1 | BALANCED | DEMOLISHER_LINE |
| R3_Jukebox | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| R4_Champion | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| algo4 | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| banana | 1/1 | 1 | BALANCED | BALANCED |
| diego_v2 | 0/2 | 2 | EDGE_FEINT,SCOUT_RUSH | BALANCED,BALANCED |
| doublegap | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| gooder-maybe | 0/1 | 1 | DEMOLISHER_LINE | BALANCED |
| new-strat-algo | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| oleh-v2 | 4/9 | 9 | BALANCED,SCOUT_RUSH,BALANCED,SCOUT_RUSH,BALANCED,SCOUT_RUSH,BALANCED,SCOUT_RUSH,SCOUT_RUSH | BALANCED,BALANCED,BALANCED,BALANCED,BALANCED,BALANCED,BALANCED,BALANCED,BALANCED |
| please-work-man-im-tired | 3/3 | 3 | SCOUT_RUSH,SCOUT_RUSH,SCOUT_RUSH | SCOUT_RUSH,SCOUT_RUSH,SCOUT_RUSH |
| potential-algo | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| python-algo | 2/4 | 4 | DEMOLISHER_LINE,BALANCED,DEMOLISHER_LINE,SCOUT_RUSH | DEMOLISHER_LINE,BALANCED,BALANCED,BALANCED |
| python-algo-baseline | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| python-algo-jae-2 | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| python-algo-jae-3 | 2/4 | 4 | BALANCED,BALANCED,DEMOLISHER_LINE,SCOUT_RUSH | BALANCED,BALANCED,BALANCED,BALANCED |
| python-algo-jae-4 | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| python-algo-master | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| python-algo-turtle-new-submis | 0/1 | 1 | DEMOLISHER_LINE | BALANCED |
| python-algo-turtle-v4 | 1/1 | 1 | BALANCED | BALANCED |
| python-algo-v3 | 0/1 | 1 | SUPPORT_BURST | BALANCED |
| python-algo-v3-detectors | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| python-algo-v5 | 0/1 | 1 | DEMOLISHER_LINE | BALANCED |
| python-algo-v8 | 1/1 | 1 | BALANCED | BALANCED |
| python-algo1 | 0/1 | 1 | SCOUT_RUSH | BALANCED |
| python-algo3 | 0/1 | 1 | BALANCED | SCOUT_RUSH |
| streeter | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |
| takedown1-algo | 0/1 | 1 | BALANCED | DEMOLISHER_LINE |
| the-goat-algo | 1/1 | 1 | SCOUT_RUSH | SCOUT_RUSH |

## Failure-mode notes

Per `docs/ATHENA_BUILD_PLAN.md` § Phase 3 protocol: gate FAILED at
0.489 vs 0.70 target. Per spec: "do NOT loosen the gate; document
failure modes and proceed. Phase 4 can still use the classifier
(just with calibrated confidence) and Phase 9 (MAP-Elites) can re-fit."

Failure-mode summary:

1. **Predictions collapse to BALANCED.** 21/24 errors land in the
   BALANCED column of the confusion matrix — the GaussianNB consistently
   prefers BALANCED for borderline cases. This is partly because
   BALANCED has the broadest empirical Gaussian (it is by construction
   the catch-all bucket: high feature variance), so its likelihood
   density at any "near-median" feature vector is highest among the
   classes. Fixing this needs either:
   - a tighter labeler (more aggressive thresholds for SCOUT_RUSH /
     DEMOLISHER_LINE so BALANCED is genuinely small-sample and gets
     downweighted via the Laplace prior), or
   - a different model class — multinomial NB on discretized
     features, or a logistic regression with class-balanced loss.

2. **Singleton classes recall = 0.** EDGE_FEINT and SUPPORT_BURST
   each have exactly one labeled sample. Leave-one-opponent-out
   holds out the only training example for these classes, so the
   fold's training set has 0 samples and the classifier silently
   replaces them with the global mean/var fallback (see
   `classifier.py:fit` 0-sample branch). Recall on those folds can
   only be > 0 by luck of the global posterior. This is structural
   on a 47-replay corpus and won't move unless we either downsample
   to 4 archetypes (drop these two) or get more labeled data.

3. **DEMOLISHER_LINE recall = 0.286.** Five out of seven held-out
   demolisher-line replays predict as BALANCED. Inspecting the
   feature matrix, several DEMOLISHER_LINE labels have
   `demolisher_share` only 0.45-0.55 — right at the labeler's
   threshold — so the GaussianNB sees them inside the BALANCED
   distribution density.

4. **TURTLE_GRIND has 0 corpus samples → impossible to test.** v13's
   ranked opponents skew offensive (which is consistent with the
   ladder meta context); none of them played a true turtle game.
   Phase 4 / 9 should retrain when more replays land or seed a
   synthetic TURTLE_GRIND sample.

5. **Per-opponent volatility on oleh-v2 (4/9).** oleh-v2 plays a
   genuinely mixed game — sometimes scout-rush, sometimes balanced
   — so the leave-out-opponent fold drops 9 samples that span 2
   labels. The classifier converges to BALANCED for all 9 because
   the remaining training set has no concentrated signal.

### Implication for Phase 4

The classifier will deliver useful posteriors for ~60% of opponents
and converge to BALANCED for the remaining ~40%. Phase 4 (offense
beam search) should:

- Use the **archetype posterior, not the top-1 label**, in plan
  scoring. A BALANCED-collapsed posterior is approximately equivalent
  to a uniform prior over actions, which is the right behavior for
  unknown opponents.
- Treat low-max-probability posteriors as "unknown" and lean on the
  no-prediction baseline candidate from the beam search (Phase 5
  spec).
- Phase 9 (MAP-Elites archive) re-fits the classifier on a much
  larger corpus harvested from self-play, which should resolve the
  singleton-class problem.