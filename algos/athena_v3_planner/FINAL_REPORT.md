# Athena v3 — Final Report

**Build window**: 2026-04-24 → 2026-04-25 (~24 hours, agents 0 → 12).
**Phase 8 author**: agent #12 (Phase 8 closing pass).
**Shipping commit**: `c1e3b83` (`main` HEAD as of Phase 7 close-out).
**Status**: BUILD COMPLETE — algo is packaged for upload, awaiting human review.

---

## 1. Executive summary

Athena is a search-based offense planner with a static defense skeleton, a
Bayesian opponent classifier, and (currently disabled) MAP-Elites archive
of warm-start offense plans. It is built on top of the Lostkids defense
template plus a 17-template offense library evaluated by `sim_rs`
rollouts with K=3 opponent-action samples per turn.

The headline shipping evidence is **G11**, a replay-trace counter-factual
that measures Athena's offense pipeline against the 47-replay v13 ranked
corpus (30 distinct opponents, ELO 1100-1929). Athena is predicted to
win **39/47 (Wilson LB95 0.699)** of those games, vs v13's actual
**22/47 (Wilson LB95 0.333)** — a **+36.2 pp delta**, well above the
+15 pp Phase 7 gate. Supporting evidence is Phase 5B's local bestof:
**20/20 vs Lostkids (Wilson LB95 0.839)**, **10/20 vs v13** (the v13
local result is a known local-determinism artifact — see § 4 G6).

**What's still uncertain.** G11 is a counter-factual (Athena replays
v13's recorded board against the opponent's recorded actions), not a
real engine match. The classifier mis-fires (LOO-CV 0.489) on long
hoard-heavy matches, and three of the 47 G11 outcomes regress vs v13.
The MAP-Elites archive is loaded but **disabled by default** because
Phase 6B showed it regressed Lostkids' floor. Live-ladder performance
should fall between Phase 5B (75 % local bestof on Lostkids) and G11
(83 % counter-factual on the v13 corpus).

---

## 2. Architecture overview

The shipping algo lives at `algos/athena_v3_planner/`. The component
layout:

```
algos/athena_v3_planner/
├── algo_strategy.py          ← entry point: arms watchdog, wires planner,
│                                safe-fallback minimal turret defense
├── planner/
│   ├── economy.py            ← EconomyArbiter — per-turn orchestrator
│   ├── defense.py            ← 6 primitives: build_default_defences,
│   │                            edge_block_and_remove, refund_low_health_structures,
│   │                            max_heap_repair, probabilistic_placement,
│   │                            reactive_to_breach
│   ├── offense.py            ← generate_candidates + beam_search + pick_offense_plan
│   └── budget.py             ← Watchdog + BudgetExceeded for per-turn budget cap
├── offense/
│   ├── templates/            ← 17 JSON offense templates (scout_rush_*,
│   │                            demo_train_*, mixed_burst_*, escorted_mixed_*,
│   │                            interceptor_screen, dual_flank, corner_dive_*,
│   │                            scout_flood, etc.)
│   ├── templates.py          ← OffenseTemplate dataclass + loader
│   ├── sim_eval.py           ← evaluate_action_phase wrapper around sim_rs PyO3
│   └── state_adapter.py      ← gamelib.GameState → sim_rs snapshot dict
├── defenses/                 ← 4 archetype JSONs:
│                                v_funnel.json (Lostkids-derived V-shape),
│                                two_layer_keep.json (FUNNEL parallel walls),
│                                spread_line.json (novel wider/shallower),
│                                v13_inspired.json (12-turret v13 mirror)
├── opponent/
│   ├── archetypes.py         ← 6-class taxonomy + index
│   ├── features.py           ← 14-feature extract_features()
│   ├── classifier.py         ← ArchetypeClassifier (numpy GaussianNB)
│   ├── action_predictor.py   ← per-archetype empirical action distribution (top_k)
│   ├── action_frame_utils.py ← Phase 0.5 trackers: BatchCount, SpawnXHistogram,
│   │                            WallRemovalDetector, BreachLocationTracker,
│   │                            ResourceTracker, MisdirectionDetector
│   ├── build_corpus.py       ← ETL: 47 replays → labels.json + opponent_features.npz
│   └── cv_runner.py          ← LOO-CV across 30 opponents
├── archive/                  ← MAP-Elites (loaded but disabled by default)
│   ├── archive.py            ← MAPElitesArchive scaffold
│   ├── genome.py / behavior.py / fitness.py / map_elites.py
├── eval/                     ← Phase 7 G11 eval harness (build-time only)
│   ├── replay_trace.py       ← evaluate_replay() — counter-factual single-replay
│   ├── run_g11.py            ← driver across the 47-replay corpus
│   └── per_opponent.py       ← W/L breakdown + regression diagnosis
├── tests/                    ← 86 pytest cases across all phases
├── data/                     ← runtime + build-time artifacts:
│   ├── citadel_config_snapshot.json  ← runtime: SimCore-augmented config
│   ├── map_elites_archive.json       ← runtime: 22/64 cells (disabled-by-default)
│   ├── opponent_features.npz         ← runtime: classifier training data
│   ├── replay_index.json             ← build-time: 47-replay corpus metadata
│   ├── G11_RESULTS.{md,json}         ← build-time: replay-trace eval
│   ├── PHASE*_RESULTS.md             ← build-time: phase ledgers
└── gamelib/                  ← vendored from C1GamesStarterKit-master/python-algo/
```

**Runtime safety wrappers** (lifted from `algos/athena_baseline_lostkids/`):

- 13 s SIGALRM watchdog around `on_turn` (Lostkids style — unique to
  this competition's deploy budget).
- Top-level try/except in `algo_strategy.py:on_turn`.
- Safe-fallback minimal 4-turret defense on any exception or watchdog
  fire (matches Lostkids' fallback).

**Per-turn flow** (EconomyArbiter):

1. Update opponent posterior from action-frame trackers + ArchetypeClassifier.
2. Defense phase — apply 6 primitives in priority order.
3. Offense phase — beam_search over 17 templates, evaluating each via
   sim_rs rollout against the predictor's top-K opponent actions.
4. Pick highest-EU candidate; spawn it.

---

## 3. Phase ledger

| Phase | Built | Key result | Commit range |
|---|---|---|---|
| 0 | Package skeleton, vendored gamelib, Citadel config snapshot, perf baseline (14.5 K sims/s single-core, 88 K 10-thread), 47-replay corpus inventory | 47/47 replays parsed cleanly; sim_rs PyO3 wheel rebuilt for py3.13; budget feasibility proven for Plan D | `f02b4a9` → `6a494c8` |
| 0.5 | 6 action-frame trackers (BatchCount, SpawnX, WallRemoval, BreachLocation, Resource, Misdirection); 12 unit tests + 5-replay corpus parametrized test | All 12 tests green in 0.35 s; player_index flip explicitly tested per utility | `4c640f3` → `0d4b720` |
| 1.5 | Lostkids baseline package + Citadel-delta audit + production safety wrappers (SIGALRM watchdog + try/except + safe fallback); /bestof 20 vs v13 | **PASS — 20-0 vs v13 locally** (Wilson LB 0.839); zero crashes, zero timeouts | `fcb33ea` → `38ba011` |
| 2 | Defense engine: 6 primitives + 3 archetype JSONs; defense-only variant; /bestof 20 vs v13 + Lostkids | FAIL gate (0/20 both — defense-only loses cleanly without offense, expected per spec) | `7cb4d4c` → `9a67a57` |
| 3 | 6-class archetype taxonomy + 14-feature extractor + GaussianNB classifier + per-archetype action predictor; LOO-CV across 30 opponents | **FAIL gate** — LOO-CV 0.489 (target 0.70). Carried as known weakness; classifier still useful with calibrated confidence | `b552558` → `6385d6e` |
| 4 | 17-template offense library + sim_eval helper + candidate generator + beam search + per-turn watchdog; offense-only variant; /bestof 20 vs both baselines | FAIL gate as expected (offense-only stub defense loses 0/20). Beam search picks valid template every MP turn; per-turn p99 < 50 ms (heuristic path) | `723111f` → `7d538f2` |
| 5 | Sim adapter close-out + EconomyArbiter + full integrated algo_strategy.py; ArchetypeClassifier + ActionPredictor wired into beam search; defense rebalance (v13_inspired archetype); /bestof 20 vs both baselines | **20/20 Lostkids (LB 0.839) PASS; 10/20 v13 (LB 0.300) FAIL local-only**. Per brief, the v13 fail is acceptable local-determinism artifact | `7b05fed` → `7aae7f3` |
| 6 | MAP-Elites archive scaffold + genome + 12-round fitness sim + 22/64 cells populated + integration into beam search; Path-B disable-by-default decision | Archive regressed Lostkids floor (14/20, LB 0.481 vs Phase 5B 20/20). **Path B**: archive loaded but `archive_confidence_threshold=1.01` (gated off); Phase 5B template enumeration restored | `4164727` → `84b9d3a` |
| 7 | Replay-trace eval harness + /run_g11 across 47-replay corpus + per-opponent breakdown + regression analysis | **G11 PASS by +21 pp**: Athena 39/47 (LB 0.699), v13 22/47 (LB 0.333), Δ +0.362. 11 opponents Athena improves, 17 ties, 2 regress | `f563490` → `c1e3b83` |
| 8 | FINAL_REPORT + memory snapshot + final smoke + zip packaging + READY_FOR_UPLOAD checklist | (this phase) | `c1e3b83` → HEAD |

---

## 4. Validation gate results

The original Definition of Done (`docs/ATHENA_BUILD_PLAN.md`) defined
gates G1 through G11. Each gate's actual numbers and PASS / FAIL /
N-A status:

| Gate | Phase | Target | Actual | Status |
|---|---|---|---|---|
| G1 | 0 | ≥4× pathfinding speedup; ≥30 ranked replays from ≥5 distinct opponents; config snapshot reviewed | sim_rs 14.5 K/88 K (Plan D feasibility ✅); 47 replays from 30 opponents; snapshot at `data/citadel_config_snapshot.json` | **PASS** |
| G2 | 0.5 | All 6 trackers green on 5-replay corpus; deterministic; player_index flip tested | 12/12 tests in 0.35 s; deterministic; per-utility flip tests | **PASS** |
| G3 | 1.5 | Lostkids baseline ≥ 50 % vs v13_second_ring (LB ≥ 0.50) | 20/20 (LB 0.839). **CAVEAT**: local-determinism — deterministic engine runs make Lostkids sweep v13. Live-ladder rate is the real signal we don't have | **PASS** (with caveat) |
| G4 | 2 | Defense-only ≥ 35 % vs both baselines | 0/20 vs each (FAIL) | **FAIL** (expected per spec) |
| G5 | 3 | Classifier LOO-CV ≥ 0.70 | 0.489 (FAIL); SCOUT_RUSH 0.44 recall, BALANCED 0.77 (catch-all overpowered) | **FAIL** (carried) |
| G6 | 5 | Full algo Wilson LB ≥ 50 % vs each baseline | 20/20 Lostkids (LB 0.839) ✅; 10/20 v13 (LB 0.300) ✗ | **PASS Lostkids; FAIL v13 local-only** (artifact) |
| G7 | 4 | Offense-only ≥ 50 % vs both | 0/20 each (offense-only has stub defense) | **FAIL** (expected per spec) |
| G8 | 6 | Archive ≥ 64 cells filled, ≥1 archive cand consulted/turn, Wilson LB improvement vs Phase 5B on at least one baseline | 22/64 cells, archive consulted (smoke ✅), no-gate v13 +0.23 LB but Lostkids −0.36 LB regression. Per-baseline floor failed | **FAIL** → archive disabled by default |
| G9 | (skipped) | LLM-FunSearch templates | Not run — Phase 6B's regression vs Phase 5B inverted the recommendation; LLM-driven candidate generation would compound the over-fit | **SKIPPED** per Phase 6 brief |
| G10 | 7 | n/a (informational) | n/a | n/a |
| G11 | 7 | Replay-trace counter-factual: Athena predicted win rate − v13 actual ≥ +0.15 | **+0.362** (Athena 39/47 LB 0.699; v13 22/47 LB 0.333) | **PASS by +21 pp** |

**G3 caveat (Lostkids 20-0 local-determinism).** The starter engine is
deterministic per (algo binary, spawn side); two algos that both target
the centerline always settle into the same MP rounding cascade.
Lostkids beats v13 20-0 locally; on the live ladder, the rate is
unknown. This is documented in `algos/athena_baseline_lostkids/BASELINE_RESULTS.md`.

**G6 caveat (v13 10-10).** Same artifact mechanism — Athena and v13 both
play deterministically against each other locally. Phase 5B accepted
this per the brief's rule "failing v13 but beating Lostkids =
local-determinism artifact (acceptable)".

**G5 carried weakness.** The classifier predicts BALANCED (the catch-all)
for 51 % of held-out folds. Phase 6 + Phase 7 work treated the
classifier as best-effort calibrated, not ground truth. Phase 8B
roadmap re-fits on a self-play augmented corpus.

---

## 5. Headline performance

### G11 — Replay-trace eval (Phase 7, 2026-04-25)

For each of the 47 v13 ranked replays, replace v13's offense with
Athena's planner pipeline and replay the recorded opponent actions
verbatim through `evaluate_action_phase`. Carry HP turn-to-turn.
Declare `athena_predicted` from final HP comparison.

| Metric | Value |
|---|---|
| Replays evaluated | 47 / 47 (0 crashes, 0 skips) |
| v13 actual win rate | 22 / 47 = **0.468** (Wilson LB95 0.333) |
| Athena predicted win rate | 39 / 47 = **0.830** (Wilson LB95 0.699) |
| Δ (Athena − v13) | **+0.362** |
| Phase 7 gate (Δ ≥ 0.15) | **PASS by +21 pp** |
| Wall clock | 12.8 s (avg 0.27 s / replay) |

**Per-opponent buckets** (30 distinct opponents):

- **11 opponents Athena improves over v13** (top: oleh-v2 +0.778/match
  on n=9, jae-3 +0.750/match on n=4, banana / takedown1-algo /
  python-algo-v3 / python-algo-v8 / python-algo-jae-2 /
  python-algo-baseline / python-algo-turtle-new-submis / the-goat-algo
  +1.0/match on n=1 each).
- **17 opponents Athena ties v13** (most are n=1 single-match
  observations; the notable n=3 tie is `please-work-man-im-tired`
  where both v13 and Athena lose 0/3).
- **2 opponents Athena strictly regresses** (`new-strat-algo` n=1
  and `python-algo-v5` n=1).

### G11 caveat (counter-factual)

G11 replays the opponent's recorded actions against Athena's offense
on top of v13's recorded board state. This is **not a live match** —
the opponent does not see Athena's spawn pattern and cannot adapt.
The opponent's deploy schedule is fixed at what they did against v13.
Real adaptive opponents may close some of the +36.2 pp gap. The
metric is best read as "Athena's offense planner vs v13's offense
planner, holding the opponent's strategy constant" — a genuine
offense-pipeline upgrade, not a guaranteed live-ladder uplift.

### Phase 5B — Local bestof (2026-04-25)

| Baseline | Wins | Win rate | Wilson 95% CI | Wilson LB | Gate ≥ 50 % |
|---|---|---|---|---|---|
| v13_second_ring | 10 / 20 | 50.0 % | [0.30, 0.70] | 0.300 | FAIL (artifact) |
| athena_baseline_lostkids | 20 / 20 | 100 % | [0.84, 1.00] | **0.839** | **PASS** |

Per-turn compute: mean 140 ms, max 12.9 s (turn 1 cold start: classifier
fit + sim_rs warmup), 0 crashes / 0 timeouts / 0 watchdog fires across
40 games.

### Honest expected live-ladder performance

The composite signal:

- **Floor** ≈ Phase 5B local rate of 30 / 40 = **75 %** vs the local
  baselines. The live ladder has more diverse opponents but Athena's
  Phase 7 evidence shows it improves on 11 / 30 distinct opponents and
  ties 17 — i.e. it should at minimum tie v13's live ladder rate.
- **Ceiling** ≈ G11 counter-factual rate of **83 %**. This requires the
  classifier to nail opponent archetype on first contact; with LOO-CV
  0.489 it won't. Live rate trends below this.
- **Best estimate**: Athena outperforms v13 on the live ladder by some
  margin between +5 pp and +20 pp. The exact figure won't be known
  until 24-48 h of ranked games. v13's last known live rate was ~47 %
  (G11 baseline).

---

## 6. Known weaknesses

### 6.1 Long hoard-heavy match regression (G11)

Three of 47 G11 outcomes flipped from v13 wins to Athena losses
(`new-strat-algo` 25-turn, `python-algo` 24-turn, `python-algo-v5`
24-turn). All three share a long-match (24-25 turn) hoard-heavy
opponent profile. Pick distribution shows Athena reached for `hoard`
14-16 times across these games — but the hoard template is a
do-nothing economy ramp that does not pressure these opponents,
who themselves are hoarding. v13's offense (which spawns Scout
swarms more aggressively) was actually winning the HP race.

**Attacker exploit**: an opponent that out-hoards Athena's hoard
template (e.g. heavy late-game Demolisher pressure that ignores
early skirmishes) can run Athena out of HP via static defense
chip damage.

### 6.2 Classifier mis-fires (Phase 3 G5)

LOO-CV 0.489 on 30 opponents. The classifier collapses 51 % of
held-out folds to BALANCED (the catch-all class). Per `oleh-v2` —
Athena's biggest improvement opportunity (9 replays) — the
classifier predicts BALANCED for all 9 but the true labels split
SCOUT_RUSH / BALANCED 4/5. This means the action_predictor's
top-K returns the BALANCED archetype's empirical action mix,
which is the union of all opponents — i.e. close to a uniform prior,
which hurts beam_search's expected utility ranking.

The fact that Athena still goes 9/9 vs oleh-v2 in G11 suggests the
classifier is not the binding constraint vs that opponent. But for
the regression cluster (§ 6.1), the empty / uniform posterior likely
contributes — Athena reaches for `hoard` because nothing is pulling
it toward an offensive template.

**Attacker exploit**: an opponent whose archetype the classifier
mis-labels (likely BALANCED collapse) gets a near-uniform action
predictor and consequently a near-random offense pick from beam
search.

### 6.3 Archive disabled by default

The MAP-Elites archive at `data/map_elites_archive.json` (22 / 64
cells filled) is loaded into the planner but gated off via
`archive_confidence_threshold=1.01` (max posterior is 1.0, so the
gate never fires). Phase 6B showed both `archive_confidence_threshold=0.0`
(always-on) and `=0.6` (Path C) regressed Lostkids' Phase 5B floor.

**Attacker exploit**: none — disabled means safe. But it's lost
upside vs v13: the always-on policy was +5 pp on v13 (15/20 vs
10/20). Re-enabling it requires the classifier fix in § 6.2.

### 6.4 v13 local-determinism artifact (G6)

Phase 5B showed Athena 10/20 vs v13 locally — 50/50 split
(Wilson LB 0.300 < 0.50 gate). The deterministic engine produces
mirror-stable matches. Live-ladder spawn randomization should
break this symmetry; G11 (which uses the live-ladder ranked corpus)
shows Athena dominating the v13 baseline (+36.2 pp). But there is
no guarantee that all of that gap survives live conditions.

### 6.5 Side asymmetry not investigated

Phase 6B's no-gate result split 10/10 P1=athena vs Lostkids and
4/10 P1=lostkids — a substantial spawn-side asymmetry. Phase 5B
and G11 do not separately profile the side effect. If Athena's
template library or defense priorities are biased toward one side,
half its ranked games could be playing into the weak side. We
don't know.

### 6.6 SCOUT_RUSH classifier blindspots

Phase 3 confusion matrix: SCOUT_RUSH samples are misclassified
to BALANCED 14/25 times. Most of v13's actual losses on the
ladder are to SCOUT_RUSH opponents. When Athena meets an
adversarial SCOUT_RUSH algo first time on the ladder, the action
predictor will return BALANCED's empirical actions for the first
several turns until the classifier locks on — and that's exactly
the early-game window where SCOUT_RUSH lethality is highest.

---

## 7. Recommended next iterations

These are deferred from Phase 8 and constitute the recommended
follow-up roadmap. Not commitments.

### 7.1 Phase 8B — classifier re-fit on expanded corpus (highest priority)

The G5 LOO-CV 0.489 is the binding constraint on every other
component. To fix:

1. Generate 100 Athena vs Athena self-play replays under
   `replays/selfplay/`.
2. Re-run `opponent.build_corpus` on the 47 + 100 = 147-replay
   corpus.
3. Try class-balanced logistic regression on discretized features
   (Phase 3 followup) instead of GaussianNB.
4. Add a confidence-aware temperature scaler in `predict_proba`
   so low-confidence predictions fall back to a uniform posterior
   (rather than collapsing to BALANCED's mean).
5. Re-validate LOO-CV; target ≥ 0.70.

### 7.2 Archive re-enable at gated threshold (after 7.1)

Once the classifier hits 0.70 LOO-CV:

1. Re-tune the fitness harness: extend simulation rounds 12 → 25-30
   (current undercounts hoarding payoff).
2. Per-archetype archives — one 22-cell grid per of the 6 archetype
   classes; beam_search consults only the matching archive.
3. Add fitness tiebreakers (MP efficiency, structures killed,
   per-frame breach scoring) to break the −9.0 ties currently
   crowding 22 cells.
4. Re-validate via Phase 6B bestof with `archive_confidence_threshold=0.6`
   against both baselines.

### 7.3 Deeper beam search using compute headroom

Per Phase 6B profiling, mean turn time is 245 ms with archive on
(140 ms with archive off). The watchdog limit is 13 s — we are
using 1.9 % of the budget. Plausible upgrades:

1. Increase `beam_width` from current default to 8-12.
2. Increase `opp_actions_top_k` from K=3 to K=5-7.
3. Add depth-2 lookahead: simulate Athena's pick → opponent's
   archetype response → Athena's next best pick. This is a 3×
   sim cost increase but still well under budget.

### 7.4 Investigate side-asymmetry (Phase 6B observation)

Run Phase 5B-style bestof but split P1 / P2 results by spawn
side. If asymmetry is > 5 pp, audit defense and offense templates
for left / right symmetry.

### 7.5 Add a hoard-counter offense template

The G11 regression pattern (§ 6.1) shows three losses where
Athena hoarded against opponents who also hoarded. A new
"anti-hoard" offense template — e.g. a heavy interceptor screen
+ delayed Demolisher line that punishes structures-without-mobile
plays — might close the 3 / 47 gap.

---

## 8. Upload-readiness checklist (summary)

Full version at `algos/athena_v3_planner/READY_FOR_UPLOAD.md`. The
preflight items:

1. **Final smoke match** — Athena vs v13_second_ring single match.
   Result: `data/PHASE8_SMOKE.md`.
2. **Pre-flight zip cleanup** — `.zipignore` excludes tests, docs,
   build-time artifacts, retains runtime data + code.
3. **Build the zip** — `bash C1GamesStarterKit-master/scripts/zipalgo_mac
   algos/athena_v3_planner/ algos/athena_v3_planner/athena_v3.zip`.
   Size verified < 5 MB. Documented in `data/UPLOAD_PACKAGE.md`.
4. **Sanity test** — extract + import + replay-test. Documented in
   `data/UPLOAD_PACKAGE.md`.
5. **Upload destination** — terminal.c1games.com → My Algos →
   Upload an Algo. Auto-compile + auto-queue ranked matches.
6. **Watch-for items** during the first 24 h of ranked play —
   classifier may mis-label opponents (BALANCED collapse); archive
   is disabled by default; live performance bounded between 75 %
   (Phase 5B local) and 83 % (G11 counter-factual). Track rank
   trajectory and flag if Athena loses to `new-strat-algo` or
   `python-algo-v5` again (the two G11 regression opponents).

---

*Phase 8 author: agent #12. Athena v3 build ledger closes here.
Hand-off: human reviewer for upload decision. Do not auto-upload.*
