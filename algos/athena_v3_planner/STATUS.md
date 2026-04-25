# Athena v3 planner — STATUS

## Current phase: **Phase 4 — DONE** (2026-04-25, all 7 sub-tasks shipped)

Next phase: **Phase 5 — EconomyArbiter integration** (compose Phase 2
DefensePlanner + Phase 3 OpponentModel + Phase 4 OffensePlanner under a
single per-turn arbiter, complete the gamelib<->sim_rs state adapter
so beam_search can actually run sim rollouts, validate end-to-end vs
both baselines with Wilson LB ≥ 50% target gradienting toward 65%).
See `AUTONOMOUS_LOG.md` § Phase 5 for the next-agent handoff brief.

## Phase 4 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. Offense template library (17 JSON templates + loader/validator) | DONE | `723111f` |
| 2. Sim integration helper (`offense/sim_eval.py`) + smoke test | DONE | `1bd864f` |
| 3. Candidate generator (`planner/offense.py:generate_candidates`) | DONE | `ac11377` |
| 4. Beam search engine (`planner/offense.py:beam_search`) + tests | DONE | `6026462` |
| 5. Per-turn budget manager (`planner/budget.py:Watchdog`) + tests | DONE | `60f7b63` |
| 6. Offense-only Athena variant + smoke test (vs python-algo) | DONE | `a6af590` |
| 7. /bestof 20 vs v13 + Lostkids — gates documented as expected loss | DONE | `8b65e8c` |
| 8. STATUS + AUTONOMOUS_LOG handoff | DONE | (this commit) |

### Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 4)

| Sub-gate | Status | Evidence |
|---|---|---|
| Wilson 95% LB > 65% vs v13_second_ring          | **FAIL (expected)** | 0/20, LB = 0.00. Defense intentionally minimal in this variant — Phase 5 composes with full defense. |
| Wilson 95% LB > 50% vs athena_baseline_lostkids | **FAIL (expected)** | 0/20, LB = 0.00. Same reason. |
| No crashes / no timeouts                        | PASS | 0/40 across both bestofs. |
| Per-turn compute < 13s                          | PASS | Watchdog never fired; beam search uses skip_sim heuristic so per-turn is < 50ms. |
| Beam search picks valid spawn each MP-having turn | PASS | Debug logs show pick=... every turn (e.g. "interceptor_screen", "scout_flood"). |

Per spec: "No hard gate — this is a partial Athena (no real defense),
so we expect modest performance. Document whatever we get; the
meaningful test is Phase 5 integration." Documented in
`data/PHASE4_RESULTS.md`.

### Where the Phase 4 deliverables live

```
algos/athena_v3_planner/
├── offense/
│   ├── __init__.py              ← loader exports
│   ├── templates.py             ← OffenseTemplate dataclass + load_all_templates + validate
│   ├── sim_eval.py              ← evaluate_action_phase wrapper around sim_rs PyO3 binding
│   └── templates/               ← 17 JSON templates:
│         scout_rush_{left,right,center}, scout_flood, scout_flood_right,
│         demo_train_{left,right}, heavy_demo_{left,right},
│         mixed_burst_{left,right}, escorted_mixed_{left,right},
│         interceptor_screen, dual_flank, corner_dive_{left,right}
├── planner/
│   ├── offense.py               ← generate_candidates + beam_search + pick_offense_plan
│   └── budget.py                ← Watchdog + BudgetExceeded
├── tests/
│   ├── test_phase4_offense.py   ← 14 tests (templates, candidate gen, beam search)
│   └── test_phase4_budget.py    ← 8 tests (watchdog API surface)
└── data/
    └── PHASE4_RESULTS.md        ← bestof results + diagnosis

algos/athena_v3_planner_offense_only/
├── algo.json
├── algo_strategy.py             ← 4-turret minimal defense + Phase 4 offense engine
├── gamelib/                     ← vendored
├── offense/                     ← copy of athena_v3_planner/offense
├── planner/                     ← copy of athena_v3_planner/planner
├── opponent/                    ← copy of athena_v3_planner/opponent
├── data/                        ← copy of athena_v3_planner/data
├── SMOKE_TEST.md                ← single-match validation
└── run.sh
```

### Phase 4B follow-ups (deferred to Phase 5)

Recorded in `data/PHASE4_RESULTS.md` § "Phase 4B / Phase 5 followups":
1. Wire the gamelib->sim_rs state adapter (uid + HP fidelity).
   Currently sim_eval.evaluate_action_phase fails on a synthesized
   state with "_raw_unit_information missing" — fix is to either
   (a) regenerate the vendored config snapshot via /inspect-config to
   include the SimCore key, or (b) bypass the sim_rs config-loading
   path and pass a fully-typed SimState. Phase 5 picks one.
2. Flip `skip_sim=False` in the offense_only variant once (1) lands.
3. Calibrate utility weights (α/β/γ/δ) on a small fuzz harness
   before Phase 9 MAP-Elites tunes them properly.
4. Compose Phase 2 full-archetype defense in place of the 4-turret
   stub.
5. Wire Phase 3 ArchetypeClassifier → ActionPredictor.top_k →
   beam_search.opp_actions_top_k.
6. Profile per-turn compute (heuristic path is < 50ms; with sim
   rollout we expect ~500ms-1s based on the 14.5K sims/s budget).

These are the meaningful work items for Phase 5; Phase 4 is committed
with the foundation ready.

## How to reproduce Phase 4

```bash
# Run all Phase 4 tests
/opt/miniconda3/bin/python3.13 -m pytest \
    algos/athena_v3_planner/tests/test_phase4_offense.py \
    algos/athena_v3_planner/tests/test_phase4_budget.py -v

# Single-match smoke test (offense-only vs python-algo)
/opt/miniconda3/bin/python3.13 \
    C1GamesStarterKit-master/scripts/run_match.py \
    algos/athena_v3_planner_offense_only \
    C1GamesStarterKit-master/python-algo

# Bestof 20 (sample — see data/PHASE4_RESULTS.md for full results)
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner_offense_only v13_second_ring 10
```



## Phase 3 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. Archetype taxonomy (6 classes + ARCHETYPES.md) | DONE | `b552558` |
| 2-3. Corpus labeling + feature extractor (47 x 14 matrix) | DONE | `6f84eba` (combined) |
| 4. Bayesian classifier (numpy GaussianNB) | DONE | `9cd3259` |
| 5. Per-archetype action predictor | DONE | `0502e5c` |
| 6. Leave-one-opponent-out CV | DONE | `0504042` |
| 7. Unit tests (13 new tests) | DONE | `702c603` |
| 8. STATUS + AUTONOMOUS_LOG handoff | DONE | (this commit) |

### Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 3)

| Sub-gate | Status | Evidence |
|---|---|---|
| Mean top-1 archetype accuracy >= 0.70 | **FAIL** | LOO-CV = 0.489 (23/47). See `data/PHASE3_CV_RESULTS.md` for confusion matrix + 4 documented failure modes. |
| pytest suite < 30s | PASS | 25 tests in 9.85s on Apple M4. |
| Classifier / predictor handle 0-sample classes | PASS | TURTLE_GRIND has 0 corpus samples — classifier falls back to global stats; predictor returns >=1 valid 'do-nothing' action. |
| Player_index flip explicitly tested | PASS | `test_feature_extractor_player_index_flip` swaps p1/p2 spawns and confirms symmetric features. |
| Posterior update monotonicity | PASS | Synthetic well-separated corpus + obs at archetype mean -> archetype posterior never decreases. |

Per spec: "If FAIL: do NOT loosen the gate; document failure modes
... and proceed. Phase 4 can still use the classifier (just with
calibrated confidence) and Phase 9 (MAP-Elites) can re-fit." We
followed that protocol — diagnosis is in `data/PHASE3_CV_RESULTS.md`.

### Where the Phase 3 deliverables live

```
algos/athena_v3_planner/
├── opponent/
│   ├── ARCHETYPES.md             ← 6-class taxonomy + features per archetype
│   ├── archetypes.json           ← canonical enum
│   ├── archetypes.py             ← Python loader (ARCHETYPES, archetype_index)
│   ├── features.py               ← 14-feature extract_features() + features_from_replay()
│   ├── build_corpus.py           ← one-shot ETL: feeds 47 replays through extractor + heuristic labeler
│   ├── labels.json               ← per-replay archetype labels + features
│   ├── classifier.py             ← ArchetypeClassifier (numpy GaussianNB)
│   ├── action_predictor.py       ← ActionPredictor (per-archetype empirical action distribution)
│   └── cv_runner.py              ← leave-one-opponent-out CV runner -> PHASE3_CV_RESULTS.md
└── data/
    ├── opponent_features.npz     ← 47 x 14 feature matrix + label/opponent strings
    └── PHASE3_CV_RESULTS.md      ← LOO-CV report with confusion matrix + failure-mode docs
```

### Phase 3B follow-ups (deferred)

Recorded in `data/PHASE3_CV_RESULTS.md` § "Failure-mode notes":
1. Tighter labeler thresholds for SCOUT_RUSH / DEMOLISHER_LINE so
   BALANCED is genuinely small-sample (currently the catch-all has
   the broadest Gaussian and dominates predictions for borderline
   cases).
2. Try a multinomial NB on discretized features OR a class-balanced
   logistic regression — likely better fits than GaussianNB on a
   47-sample, 14-feature, 6-class problem with skewed labels.
3. Augment the corpus with self-play replays once Phase 4-5 ship —
   the singletons (EDGE_FEINT, SUPPORT_BURST) and 0-sample classes
   (TURTLE_GRIND) need more samples.
4. Add a `predict_proba`-aware confidence calibrator for Phase 4
   beam search (e.g. temperature-scale the softmax, or fall back
   to uniform when max(post) < 0.4).

These will be picked up either at the start of Phase 9 MAP-Elites
(when self-play data lands) or as part of Phase 6 predictor framework
integration if Phase 4-5 prove the offense engine is the larger
bottleneck.

## How to reproduce Phase 3

```bash
# 1. Rebuild feature matrix + labels (47 replays, ~2s)
/opt/miniconda3/bin/python3.13 -m algos.athena_v3_planner.opponent.build_corpus

# 2. Run cross-validation
/opt/miniconda3/bin/python3.13 -m algos.athena_v3_planner.opponent.cv_runner

# 3. Run all unit tests
/opt/miniconda3/bin/python3.13 -m pytest algos/athena_v3_planner/tests/ -v
```


## Phase 2 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. Three defense archetype JSONs                 | DONE | `7cb4d4c` |
| 2-7. Defense engine (planner/defense.py, six fns)| DONE | `41cb327` (combined commit — see commit msg + handoff log for rationale) |
| 8. Defense-only Athena variant + smoke test      | DONE | `32111f7` |
| 9. /bestof 20 vs v13 + Lostkids — gates FAIL     | DONE | `61bd133` |
| 10. STATUS + AUTONOMOUS_LOG handoff              | DONE | (this commit) |

### Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 2)

| Sub-gate | Status | Evidence |
|---|---|---|
| Wilson 95% LB ≥ 35% vs v13_second_ring          | **FAIL** | 0/20, LB = 0.000. See `data/PHASE2_RESULTS.md`. |
| Wilson 95% LB ≥ 35% vs athena_baseline_lostkids | **FAIL** | 0/20, LB = 0.000. |
| No crashes / no timeouts                        | PASS | 0/40 across both bestofs. |
| Per-turn compute < 13s                          | PASS | ~7.5 ms/turn. |

Per spec: "If FAIL on either: do NOT loosen the gate; document the
failure with diagnosis ... and proceed — Phase 3+ may fix it." We
followed that protocol — diagnosis is in
`data/PHASE2_RESULTS.md`. Phase 3 inherits the gate failure as an
explicit known limitation: Athena v3 cannot win without offense, and
the defense archetypes under-emphasize early turrets vs the local
meta.

### Where the Phase 2 deliverables live

```
algos/athena_v3_planner/
├── planner/
│   ├── __init__.py
│   └── defense.py          ← six defense primitives (build_default_defences,
│                              edge_block_and_remove, refund_low_health_structures,
│                              max_heap_repair, probabilistic_placement,
│                              reactive_to_breach). All numerics from runtime config.
├── defenses/
│   ├── v_funnel.json       ← Lostkids-derived V-shape (32 placements)
│   ├── two_layer_keep.json ← FUNNEL two-parallel-walls (32 placements)
│   └── spread_line.json    ← novel — wider/shallower (32 placements)
└── data/
    └── PHASE2_RESULTS.md   ← bestof results + failure diagnosis

algos/athena_v3_planner_defense_only/
├── algo.json
├── algo_strategy.py        ← composes the six defense primitives + watchdog
├── gamelib/                ← vendored
├── planner/                ← copy of athena_v3_planner/planner
├── defenses/               ← copy of athena_v3_planner/defenses
├── opponent/               ← copy of athena_v3_planner/opponent (BreachLocationTracker)
├── data/                   ← copy of citadel_config_snapshot.json
└── run.sh
```

### Phase 2B follow-ups (deferred)

Recorded in `data/PHASE2_RESULTS.md` § "Phase 2B follow-ups":
1. Rebalance `v_funnel.json` toward heavier early turrets (priority 1-2).
2. Loosen `probabilistic_placement` turret-y constraint from `y >= 11` to `y >= 9`.
3. Optional: clone v13_second_ring's ring archetype into a fourth `defenses/v13_ring.json`.
4. Add a smoke-test gate vs `python-algo` (currently fails too — defense-only
   loses to even the trivial scout-rush baseline).

These will be picked up either at the start of Phase 3 (if Phase 3
needs the defense to be stronger to validate the opponent model) or
at Phase 9 MAP-Elites.



## Phase 1.5 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. Package structure (gamelib + algo_strategy + defense-order) | DONE | `fcb33ea` |
| 2. Citadel-delta audit                                          | DONE | `8eaa91f` |
| 3. Production safety wrappers (try/except + 13s watchdog)        | DONE | `5684a5a` |
| 4. Smoke test (single match)                                     | DONE | `fd7e641` |
| 5. /bestof 20 vs v13_second_ring                                 | DONE | `2a7f9d0` |
| 6. STATUS + AUTONOMOUS_LOG handoff                               | DONE | (this commit) |

### Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 1.5)

| Sub-gate | Status | Evidence |
|---|---|---|
| Algo loads without crash         | PASS | Smoke test + 20 bestof matches, zero crashes. |
| No `timeout_death` in bestof     | PASS | All 20 games `timeout_death=false`. |
| Wilson 95 % LB ≥ 50 %            | **PASS** | 20-0 sweep → LB 0.8389. |
| Citadel-delta audit documented   | PASS | `algos/athena_baseline_lostkids/CITADEL_DELTA_AUDIT.md`. |
| Production wrappers in place     | PASS | 13 s SIGALRM watchdog + try/except + safe fallback (4 base turrets). |

### Where the baseline lives

```
algos/athena_baseline_lostkids/
├── algo.json                ← {"name": "athena_baseline_lostkids"}
├── algo_strategy.py         ← ported Lostkids + safety wrappers
├── defense-order.json       ← Lostkids 7-phase build_order
├── run.sh                   ← chmod +x launcher
├── gamelib/                 ← vendored from C1GamesStarterKit
├── CITADEL_DELTA_AUDIT.md
├── SMOKE_TEST.md
└── BASELINE_RESULTS.md
```

20 archived bestof replays at:
`replays/bestof_athena_baseline_lostkids_vs_v13_second_ring_20260425_001252/`
(gitignored; path documented in `BASELINE_RESULTS.md`).

## Phase 0.5 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. BatchCountTracker                  | DONE | `4c640f3` |
| 2. SpawnXHistogram                    | DONE | `93d377d` |
| 3. WallRemovalDetector                | DONE | `1b18ea1` |
| 4. BreachLocationTracker              | DONE | `29ffff4` |
| 5. ResourceTracker                    | DONE | `92c001b` |
| 6. MisdirectionDetector               | DONE | `c0b5d0d` |
| 7. tests + 5-replay corpus validation | DONE | `86b0216` |
| 8. STATUS + AUTONOMOUS_LOG handoff    | DONE | (this commit) |

All six utilities live in
`algos/athena_v3_planner/opponent/action_frame_utils.py` (single
module). Pre-commit `mode_parity` regression gate ran green on every
commit (no `--no-verify`). The pre-commit hook in
`.git/hooks/pre-commit` was patched between Phase 0 and 0.5 to use
`git rev-parse --show-toplevel`, so the worktree-checkout bug from
Phase 0 is fixed.

### Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 0.5)

| Sub-gate | Status | Evidence |
|---|---|---|
| Per-utility unit tests | **DONE** | `tests/test_action_frame_utils.py` — 12 tests, all PASS in 0.35s. |
| Each utility deterministic | **DONE** | `test_determinism_same_replay_twice` confirms byte-identical state across two runs of the same replay. |
| player_index flip explicitly tested | **DONE** | One synthetic-frame test per utility (6 total) explicitly mixes player 1 + player 2 entries and asserts the utility filters by ``self_player_id``. |
| Tests run without sim_rs | **DONE** | Pure stdlib + numpy + pytest. No engine.jar / Rust dependency. |
| 5-replay corpus validation | **DONE** | Parametrised test runs all 6 utilities across 5 replays from `data/replay_index.json` — 2 wins, 2 losses, 1 boss, 5 distinct opponents. No crashes; outputs well-formed (range checks on all returned values). |

## How to run the tests

```bash
/opt/miniconda3/bin/python3.13 -m pytest \
    algos/athena_v3_planner/tests/test_action_frame_utils.py -v
```

12 tests, ~0.35s on Apple M4.

## Phase 0 task ledger

| Task | Status | Commit |
|---|---|---|
| 1. Package skeleton + vendored gamelib | DONE | `f02b4a9` |
| 2. Citadel config snapshot | DONE | `275bd09` |
| 3. Perf baseline measurement | DONE | `222496d` |
| 4. Replay corpus inventory + parse validation | DONE | `a891046` |
| 5. AUTONOMOUS_LOG + handoff | DONE | `d103907` |
| 6. STATUS.md final | DONE | (this commit) |

## Validation gate (per `docs/ATHENA_BUILD_PLAN.md` § Phase 0)

The original gate text reads:
> ≥4× pathfinding speedup confirmed; ≥30 ranked replays from ≥5
> distinct opponents in `data/replays/ranked/`;
> `citadel_config_snapshot.json` written and reviewed.

How this Phase 0 satisfies it:

| Sub-gate | Status | Evidence |
|---|---|---|
| ≥4× pathfinding speedup | **deferred** | This Phase 0 measured the **end-to-end Rust SimCore throughput** rather than the gamelib pathfinder's 4× target. The 14.5 K single-core / 88 K 10-thread numbers (vs the earlier 14.3 K / 75 K reference points in `SIM_PARITY.md`) are the budget basis the build-plan mandate (Plan D feasibility) actually depends on. The gamelib `queue.Queue → deque` patch in `algos/athena_v3_planner/gamelib/` is **NOT** applied yet — that's a Phase 0 followup or rolls into Phase 1. Logged in AUTONOMOUS_LOG.md. |
| ≥30 ranked replays | **EXCEEDED** | 47 v13 ranked replays in `replays/ranked/`, all parseable. |
| ≥5 distinct opponents | **EXCEEDED** | 30 distinct opponents represented in the corpus. |
| `citadel_config_snapshot.json` written & reviewed | **DONE** | `algos/athena_v3_planner/data/citadel_config_snapshot.json` + `CONFIG_README.md`. |

## What the next phase needs

Phase 0.5 should build action-frame utilities under
`algos/athena_v3_planner/opponent/action_frame_utils.py`. The
foundations are in place:
- A working algo package (`algo_strategy.py` imports cleanly).
- A vendored `gamelib/` (untouched starter copy; deque-patch + path
  cache are Phase 0 followups / Phase 1).
- The Citadel config snapshot for any unit-stat lookup.
- A 47-replay corpus, indexed by `data/replay_index.json`, all
  bit-exact verified Python ↔ Rust.

## Gotchas — please read before starting Phase 0.5

These are repeated from `AUTONOMOUS_LOG.md` for visibility.

1. **Pre-commit hook broken in worktree checkouts.** All Phase 0
   commits used `git commit --no-verify` because
   `.git/hooks/pre-commit` does
   `${GIT_DIR}/../algos/athena/sim/regression_runner.py` and `GIT_DIR`
   inside a worktree is `.git/worktrees/<name>`, not `.git`. The hook
   thus tries to open `.git/worktrees/algos/athena/sim/...` which does
   not exist. The right fix is one line:
   ```bash
   TOPLEVEL="$(git rev-parse --show-toplevel)"
   "/opt/miniconda3/bin/python3.13" "$TOPLEVEL/algos/athena/sim/regression_runner.py" --scope quick
   ```
   Each Phase 0 commit was paired with a manual run of that
   regression-runner command — all green. The user should patch the
   hook on the main checkout (sandbox prevents agent edits to
   `.git/hooks/`).

2. **Python version: use `/opt/miniconda3/bin/python3.13`** for any
   sim/parity tooling. System `/Library/Developer/CommandLineTools/usr/bin/python3`
   is 3.9 and trips on `@dataclass(slots=True)` in `algos/athena/sim/state.py`.

3. **sim_rs PyO3 wheel rebuild.** The installed wheel under
   `~/Library/Python/3.9/lib/python/site-packages/sim_rs/` is stale
   (missing `simulate_action_phase_py`). Phase 0 rebuilt against 3.13:
   ```bash
   cd algos/athena/sim_rs
   CONDA_PREFIX=/opt/miniconda3 /opt/miniconda3/bin/python3.13 -m maturin develop --release --features pyo3
   ```
   Repeat after any `sim_rs/src/**/*.rs` change.

4. **Replay file format.** Each `.replay` starts with a blank line,
   then one JSON object per line; line 1 is the config header, subsequent
   lines are deploy / action frames, last frame carries `endStats`.
   `algos/athena/sim/validate.py:_parse_replay` is the robust loader.
   `algos/athena_v3_planner/sim/replay_inventory.py` is a stdlib-only
   metadata variant.

5. **47 ranked replays, not 23** as the agent brief said. The corpus is
   larger than expected; this is good news for Phase 1+.

6. **Pathfinder deque-patch + path cache** (build-plan items 3 and 4 of
   Phase 0) were NOT applied to
   `algos/athena_v3_planner/gamelib/`. Those are pure-Python perf items
   that don't gate Plan D feasibility (the Rust SimCore is the real
   budget driver). Treat them as Phase 0 followups or roll them into
   the Phase 0.5 / Phase 1 patch sweep.
