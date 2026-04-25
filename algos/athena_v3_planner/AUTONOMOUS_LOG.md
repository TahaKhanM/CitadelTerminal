# Athena v3 planner — autonomous build log

## Phased-handoff protocol

Prior monolithic build attempts hit API rate limits before producing a
single commit. The fix: each agent owns ONE phase (or part of a phase),
commits at every milestone, and exits cleanly. The next agent picks up
from the committed state.

Each phase entry below records:
- The phase number and what it covers
- The agent that ran it (timestamp / model)
- Per-task commit SHAs
- A short result summary
- A pointer to the next phase

The current agent should NEVER advance past its own phase, even if
budget allows. Print "ATHENA PHASE N COMPLETE" and exit.

---

## Phase 0 — Performance moat + replay corpus + Citadel config lock-in

**Agent**: Claude Opus 4.7 (1M context).
**Started**: 2026-04-24.
**Branch**: `worktree-agent-a17ebb78` (off `main`).
**Plan reference**: `docs/ATHENA_BUILD_PLAN.md` § Phase 0.

### Task 1 — Package skeleton

**Commit**: `f02b4a9`
**Result**:
- Scaffolded `algos/athena_v3_planner/` with `data/`, `sim/`, `planner/`,
  `defenses/`, `offense/`, `opponent/`, `tests/` subdirs.
- Vendored `gamelib/` from `C1GamesStarterKit-master/python-algo/gamelib/`.
- Wrote no-op `algo_strategy.py` + `algo.json` stub so the package
  imports cleanly.
- Verified: `python3 -c "import sys; sys.path.insert(0, 'algos/athena_v3_planner'); import algo_strategy"` succeeds.

### Task 2 — Citadel config snapshot

**Commit**: `275bd09`
**Result**:
- Ran `/inspect-config` (`./tools/run.sh _config_inspector _config_inspector`).
- Captured the live RAW CONFIG JSON to
  `algos/athena_v3_planner/data/citadel_config_snapshot.json`.
- Wrote `data/CONFIG_README.md` documenting provenance, the
  do-not-use-other-configs rule, and a verified quick-reference table.
- Confirmed key Citadel-specific values: FF upgrade HP=200, EF base
  HP=1, EF upgraded `shieldPerUnit=1.0 + shieldBonusPerY=0.7` →
  10.1 shield/Support/Scout at Y=13, DF upgrade dmg=20 / range=3.5,
  `metalForBreach=1.0` per PI/EI/SI.

### Task 3 — Perf baseline measurement

**Commit**: `222496d`
**Result**: Recorded Rust SimCore throughput on this machine:
- Single-core `cargo bench --bench throughput -- mid_game_108_struct_5_mob`:
  **14.5 K sims/s** (68.9 µs per sim).
- 10-thread `cargo run --release --example parallel_bench`:
  best **88,155 sims/s**, median 86,092 sims/s.
- Speedup ≈ 6.07× vs 10× ideal (memory-bound, expected on M-series).
- Written to `data/PERF_BASELINE.md`.
- Machine: Apple M4 / 10 core / 16 GB RAM / Darwin 24.5.0.
- Implication: ~88K × 12s ≈ **1.06 M sims per turn** budget — Plan D
  (search-based planner) comfortably feasible.

### Task 4 — Replay corpus inventory + parse validation

**Commit**: `a891046`
**Result**:
- Wrote `sim/replay_inventory.py` (stdlib only, no sim_rs dependency).
- Walked `replays/` + `data/replays/` for `v13_*.replay`:
  **47 files**, 47/47 parsed end-state metadata cleanly,
  **30 distinct opponents** (>>>>>= 5 floor), 4 boss matches,
  22 wins / 25 losses, avg 69.9 turns.
- Wrote per-replay metadata index to `data/replay_index.json`.
- Rebuilt sim_rs PyO3 wheel (the installed copy was missing
  `simulate_action_phase_py`):
  `CONDA_PREFIX=/opt/miniconda3 /opt/miniconda3/bin/python3.13 -m maturin develop --release --features pyo3`.
- Ran `algos/athena/sim/cross_validate.py`:
  `checks=3319  ok=3319  fail=0  gate: PASS`.
  All 3,319 deploy-action turns across all 47 replays are bit-identical
  between Python reference and Rust port. Zero parity regressions vs
  SIM_PARITY.md baseline.

### Task 5 — Autonomous log + handoff

**Commit**: (this commit).
**Result**: This file. Phase-0 narrative + handoff protocol committed.

### Task 6 — STATUS.md final

**Commit**: (next commit).
**Result**: Final phase-summary update; will mark Phase 0 DONE.

---

## Phase 0.5 — Action-frame utilities

**Agent**: Claude Opus 4.7 (1M context).
**Started / completed**: 2026-04-24.
**Branch**: `worktree-agent-afde0da4` (off `main` @ `6a494c8`).
**Plan reference**: `docs/ATHENA_BUILD_PLAN.md` § Phase 0.5.

All six utilities ship in a single module
`algos/athena_v3_planner/opponent/action_frame_utils.py` exported via
`opponent/__init__.py`. Each is a `@dataclass` so callers can hold one
instance per match and feed it action frames inside `on_action_frame`.
Every utility takes a `self_player_id` parameter (default 1, matching
the action-frame JSON convention `1=self, 2=opponent`) so the
player_index flip never has to be guessed at by the caller.

### Tasks (commit-by-commit)

| Task | Class | Commit |
|---|---|---|
| 1 | `BatchCountTracker`         | `4c640f3` |
| 2 | `SpawnXHistogram`           | `93d377d` |
| 3 | `WallRemovalDetector`       | `1b18ea1` |
| 4 | `BreachLocationTracker`     | `29ffff4` |
| 5 | `ResourceTracker`           | `92c001b` |
| 6 | `MisdirectionDetector`      | `c0b5d0d` |
| 7 | tests + 5-replay corpus run | `86b0216` |
| 8 | STATUS + log handoff        | (this commit) |

### Implementation notes

- **Mobile vs structure types**: pulled from
  `data/citadel_config_snapshot.json` indices: 0 Wall, 1 Support, 2
  Turret, 3 Scout, 4 Demolisher, 5 Interceptor, 6 Remove (pending
  queue), 7 Upgrade. `MOBILE_TYPES = {3,4,5}`,
  `STRUCTURE_TYPES = {0,1,2}`.
- **`_is_first_action_frame`** (helper) gates every per-turn read on
  `turnInfo[0]==1` (action phase) AND `turnInfo[2]==0` (frame index 0)
  — the same point Lostkids reads `events.spawn`. By that point all of
  the turn's spawn events have been emitted but no unit has moved.
- **Edge classification** in `_classify_edge` partitions the diamond
  by `y >= 14` (top vs bottom half) and `x <= 13` (left vs right). This
  is robust to engine quirks at diagonal endpoints; explicit row-13/14
  checks were tried first and rejected.
- **Decay**: `SpawnXHistogram` uses 0.95/turn, `BreachLocationTracker`
  0.9/turn (more aggressive — recent breach tells matter more than old
  ones).
- **Determinism**: every utility tracks `_counted_turns` /
  `_scanned_turns` / `_seen_event_keys` so re-feeding the same frame is
  a no-op. The determinism test confirms byte-identical state across
  two runs of the same replay.
- **No sim_rs dependency**: pure stdlib + numpy. Tests run in 0.35 s.

### Tests

`tests/test_action_frame_utils.py` — 12 tests:

- 6 `*_player_index_flip` synthetic-frame tests (one per utility)
  that mix player 1 + player 2 entries and assert the utility filters
  correctly. Each also re-runs with `self_player_id=2` to verify the
  flip works in both directions.
- 1 `test_determinism_same_replay_twice` runs the full pipeline twice
  on `m15302602_vs_gooder-maybe_1453_win.replay` and asserts every
  utility's recorded state matches.
- 5 parametrised `test_corpus_no_crash_well_formed` runs across:
  - `m15302602_vs_gooder-maybe_1453_win` (30 turns, win)
  - `m15302606_vs_python-algo-v3_1612_loss` (72 turns, loss)
  - `m15302609_vs_diego_v2_1486_win` (52 turns, win)
  - `m15302611_vs_takedown1-algo_1644_loss` (44 turns, loss)
  - `m15302614_vs_R2_Infiltrator_0_win_boss` (26 turns, boss win)
  Asserts all six utilities run end-to-end without crashing and that
  every returned value is in the expected range / shape.

### Pre-commit hook status

The hook at `.git/hooks/pre-commit` was already patched (uses
`git rev-parse --show-toplevel`) before Phase 0.5 started. Every Phase
0.5 commit went through `mode_parity` (≈100 s per commit) cleanly —
no `--no-verify` was needed.

---

## Phase 1.5 — Lostkids baseline port

**Agent**: Claude Opus 4.7 (1M context).
**Started / completed**: 2026-04-25.
**Branch**: `worktree-agent-a7799040` (off `main` @ `0d4b720`).
**Plan reference**: `docs/ATHENA_BUILD_PLAN.md` § Phase 1.5.

### Tasks (commit-by-commit)

| Task | Output                                | Commit |
|---|---|---|
| 1 | Lostkids package structure              | `fcb33ea` |
| 2 | Citadel-delta audit (zero patches)      | `8eaa91f` |
| 3 | Production safety wrappers + watchdog   | `5684a5a` |
| 4 | Smoke test (single match)               | `fd7e641` |
| 5 | `/bestof 20` vs `v13_second_ring`       | `2a7f9d0` |
| 6 | STATUS + AUTONOMOUS_LOG handoff         | (this commit) |

### Implementation notes

- **gamelib**: vendored from `C1GamesStarterKit-master/python-algo/gamelib/`,
  NOT from Lostkids' own gamelib variant. Keeps engine parity with the
  rest of our local algos.
- **Attribution**: `algo_strategy.py` carries an "Adapted from
  research/finalist_repos/..." header. `defense-order.json` is JSON so
  inline attribution wasn't possible; it's a verbatim copy and noted in
  the audit doc.
- **Citadel deltas**: zero code patches needed. `attempt_upgrade` /
  `type_cost` / `get_resource` all read from runtime config. The four
  build-plan deltas (Wall upgrade SP cost, Support shield formula,
  Turret upgrade strength, MP decay 0.25) are auto-handled or are
  no-deltas. Two strategic-tuning candidates flagged in
  `CITADEL_DELTA_AUDIT.md` for Phase 2+ (Support Y-placement at 5/6 is
  suboptimal for Citadel; threat-score weight under-counts upgraded
  turrets).
- **Production wrappers** (in `algo_strategy.py`, lines 30+):
  - `TURN_WATCHDOG_SECONDS = 13`.
  - `_arm_watchdog(seconds)` — SIGALRM primary, thread-Timer fallback.
    Returns `(disarm_callable, fired_callable)` so the caller can both
    cancel the timer and check whether it fired.
  - `_TurnTimeout` exception bubbled by SIGALRM handler.
  - `on_turn` wrapped in try/except. On `_TurnTimeout` or any
    `Exception`, logs to stderr via `gamelib.debug_write` and falls
    through to `_safe_fallback_turn`.
  - `_safe_fallback_turn` spawns up to 4 base turrets at canonical
    positions `((2,13), (25,13), (3,13), (24,13))` if SP allows; no
    offense. Costs are read from `type_cost(TURRET)` (config-driven).
  - `on_action_frame` wrapped in try/except — telemetry must never
    crash the algo.
  - Watchdog unit-tested in isolation: SIGALRM raises `_TurnTimeout`
    after 1 s during a 3 s sleep.

### Validation results

`tools/bestof.py athena_baseline_lostkids v13_second_ring 10` → 20
games:

- **Win rate 100 % (20-0)**.
- Wilson 95 % CI `[0.8389, 1.0000]`. **LB 0.8389 ≥ 0.50 gate: PASS.**
- Zero crashes, zero `timeout_death`. Per-turn compute ≈19.5 ms.
- All 20 games end identically (32-7 HP, 37 turns) because both algos
  are operating in their deterministic regimes: v13 has zero `random.*`
  calls; Lostkids' single random branch (`block_edge` tie-break) isn't
  triggered against v13's standard defense.

Replays preserved (gitignored):
`replays/bestof_athena_baseline_lostkids_vs_v13_second_ring_20260425_001252/`.

### Caveats

The 20-0 sweep is decisive *vs v13_second_ring on the local engine*
but is not a claim about live-ladder strength:

1. v13_second_ring's Support-heavy archetype is hamstrung by the older
   local Support config (`docs/UNITS_REFERENCE.md` § Support note —
   `shieldRange = 0` locally vs Citadel's 7.0). Lostkids' Scout-heavy
   offense is largely unaffected by that delta. So the local matchup
   over-weights Lostkids.
2. Determinism means the "20 games" is one trajectory replayed 20×.
   Wilson CI applies for regression, but it's not a substitute for
   varied-opponent ladder data.
3. Lostkids' Y=5/6 upgraded-Support placements are sub-Citadel-optimal
   (Y<7 underperforms base shield). On the live ladder this likely
   costs Lostkids matches against scout-rush opponents that v13's
   Y≥10 placements would have shielded against.

These caveats DO NOT change the Phase 1.5 verdict — gate passes — but
they bound the predictive power of the baseline number for Phase 2+
expectations.

---

## Phase 2 — Defense engine

**Agent**: Claude Opus 4.7 (1M context).
**Started / completed**: 2026-04-25.
**Branch**: `worktree-agent-aab163ca` (off `main` @ `38ba011`).
**Plan reference**: `docs/ATHENA_BUILD_PLAN.md` § Phase 2.

### Tasks (commit-by-commit)

| Task | Output                                             | Commit |
|---|---|---|
| 1 | Three defense archetype JSONs (v_funnel, two_layer_keep, spread_line) | `7cb4d4c` |
| 2-7 | Defense engine (`planner/defense.py`, six functions)   | `41cb327` |
| 8 | Defense-only Athena variant + smoke test            | `32111f7` |
| 9 | `/bestof 20` vs v13 + Lostkids — gates FAIL         | `61bd133` |
| 10 | STATUS + AUTONOMOUS_LOG handoff                    | (this commit) |

### Tasks-2-through-7 commit batching note

Tasks 2-7 are six separate functions in `planner/defense.py`. They
were committed together in `41cb327` to amortize the ~100s
SimCore-parity pre-commit hook (six 100s commits = 10 wall-min, much
of the agent's 25-min budget). Each function in the module is fully
documented per-task; the deliverable is task-by-task even though the
git history is one commit. The commit message enumerates the
six tasks individually. This is a documented deviation; the next
agent should NOT re-split for cosmetic reasons.

### Implementation notes

- **All six defense primitives** in a single module
  (`planner/defense.py`):
  `build_default_defences`, `edge_block_and_remove`,
  `refund_low_health_structures`, `max_heap_repair`,
  `probabilistic_placement`, `reactive_to_breach`.
- **Numerics fully runtime-driven**: every HP/damage/range/cost read
  goes through `game_state.type_cost(...)` or
  `config["unitInformation"][i]`. No constants are hardcoded.
- **FUNNEL `[0]` vs `[1]` cost-vector bug fixed** in `max_heap_repair`:
  reads `type_cost(unit)[SP_IDX]` explicitly with a comment naming the
  fix. FUNNEL's literal `[0]` was a latent bug if engine tuple-order
  ever changes.
- **support_weight = 25** (not GRETCHEN's 100x). Citadel upgraded
  supports are 40 HP / shield = `1 + 0.7×Y` per Scout — much more
  durable and valuable than the base 1-HP supports GRETCHEN faced.
  Phase 9 MAP-Elites will retune.
- **Defense-only variant**: `algos/athena_v3_planner_defense_only/`,
  copied subpackages (planner/, defenses/, opponent/, data/) so the
  algo bundle is self-contained for upload. Runs identically to the
  Phase 1.5 Lostkids baseline w.r.t. production wrappers (13 s SIGALRM
  watchdog + try/except + 4-turret safe-fallback).

### Validation results

| Matchup | Wins | Losses | Wilson 95% LB | Gate (≥0.35) |
|---|---|---|---|---|
| def-only vs v13_second_ring         | 0 | 20 | **0.000** | **FAIL** |
| def-only vs athena_baseline_lostkids| 0 | 20 | **0.000** | **FAIL** |

Both gates **FAIL**. Per spec, NOT loosened; failure documented in
`data/PHASE2_RESULTS.md` and below.

### Failure diagnosis

The defense-only algo cannot win on the local engine because:

1. **No offense → no HP damage → no path to victory.** The spec
   anticipated turn-100 SP-tiebreak wins, but on the local engine
   both opponents reliably HP-drain the defense in 20-32 turns
   before the tiebreak path triggers.
2. **Archetypes under-build turrets vs the meta.** v_funnel only puts
   4 base turrets at priority 1 and gates further turret placement
   behind support placement (priority 5+). v13 builds 12 turrets by
   T5 and 20 by T30; we stall at 5-6 across the entire game.
3. **probabilistic_placement turret-y is restricted to y >= 11.**
   With most front-row tiles already filled by walls, the sampler
   contributes few new turrets.

The defense engine itself is functioning correctly:
- 0 crashes / 0 timeouts / 0 watchdog fires across 40 games.
- Per-turn compute ~7.5 ms (well under the 13s watchdog).
- All primitives execute as designed; build/refund/repair logic is
  composing correctly.

The gate failure is a SUFFICIENCY problem, not a CORRECTNESS one.
Phase 3 (offense) is the right place to fix it.

### Phase 2B follow-ups (carried into Phase 3 or later)

1. Rebalance `v_funnel.json` to put 4-6 more base turrets at priority
   1-2 (currently priority 6+).
2. Loosen `probabilistic_placement` turret-y constraint from `y >= 11`
   to `y >= 9`.
3. Optionally clone v13_second_ring's ring layout as a fourth
   archetype `defenses/v13_ring.json`. v13 wins 20-0 vs Lostkids on
   the local engine — its archetype is meta-fit.
4. Currently defense-only LOSES to even `python-algo`. A 2-game smoke
   test vs python-algo would be a good early canary for Phase 2B
   tuning work.

These are deferred; Phase 2 is committed with the failure documented
and proceeds to Phase 3 per spec protocol.

---

## Phase 3 — Opponent model

**Agent**: Claude Opus 4.7 (1M context).
**Started / completed**: 2026-04-25.
**Branch**: `worktree-agent-ab21feea` (off `main` @ `9a67a57`).
**Plan reference**: `docs/ATHENA_BUILD_PLAN.md` § Phase 3.

### Tasks (commit-by-commit)

| Task | Output                                              | Commit |
|---|---|---|
| 1   | Archetype taxonomy (6 classes + ARCHETYPES.md + JSON enum) | `b552558` |
| 2-3 | Corpus labeling + feature extractor (combined commit)  | `6f84eba` |
| 4   | Bayesian classifier (numpy GaussianNB)                  | `9cd3259` |
| 5   | Per-archetype action predictor                          | `0502e5c` |
| 6   | Leave-one-opponent-out cross-validation                 | `0504042` |
| 7   | Unit tests (13 new — 25 total in athena_v3_planner)     | `702c603` |
| 8   | STATUS + AUTONOMOUS_LOG handoff                         | (this commit) |

### Tasks 2-3 batching note

Task 2 (corpus labeling) requires Task 3 (feature extractor) — the
labeler operates over the feature dict. They were committed together
in `6f84eba` because `build_corpus.py` produces both labels.json AND
opponent_features.npz in a single ~2s pass. Same ~100s pre-commit-hook
amortization rationale Phase 2 used for tasks 2-7.

### Implementation notes

- **Six archetypes** (small enough for a 47-replay corpus):
  SCOUT_RUSH (25), DEMOLISHER_LINE (7), EDGE_FEINT (1),
  SUPPORT_BURST (1), TURTLE_GRIND (0), BALANCED (13).
- **14 features** (`opponent/features.py:FEATURE_NAMES`):
  spawn-x entropy / peak col / peak share, scout/demolisher/
  interceptor share, mean MP at spawn, breach rate / turn,
  edge asymmetry, wall removal rate, mean wave size, support /
  turret share, turns observed.
- **Classifier**: pure-numpy Gaussian Naive Bayes with sklearn-style
  `var_smoothing=1e-3` (bumped from sklearn default 1e-9 because of
  the small corpus). Laplace +1 prior smoothing. Zero-sample classes
  fall back to global mean / variance; single-sample classes use the
  sample mean + global variance.
- **Action predictor**: empirical (state-bucket -> action -> count)
  per archetype, with Laplace +1 smoothing and a chain of fallbacks
  (per-state -> archetype-global -> uniform 'do nothing'). Always
  returns >=1 action.
- **No sklearn dependency** — confirmed pre-build that sklearn is
  not installed on this machine.
- **Pure stdlib + numpy + pytest**. No engine.jar / sim_rs in any
  of the Phase 3 code paths.

### Validation results

Leave-one-opponent-out CV (30 folds, all 47 replays):

- **Mean top-1 accuracy: 0.489 (23/47)** — gate FAILED at 0.70 target.
- Per-class recall: BALANCED 0.769, SCOUT_RUSH 0.440,
  DEMOLISHER_LINE 0.286, EDGE_FEINT / SUPPORT_BURST 0.000 (singletons),
  TURTLE_GRIND n/a (0 corpus samples).
- Confusion matrix shows 21/24 errors land in the BALANCED column —
  the BALANCED Gaussian is the broadest by construction so it
  dominates near-median observations.
- Full report: `data/PHASE3_CV_RESULTS.md` with 4 documented failure
  modes + Phase 4 implications.

Per spec: gate NOT loosened. The classifier ships as-is; Phase 4
should use the **archetype posterior, not the top-1 label**, in plan
scoring so the BALANCED-collapse acts as 'no signal' rather than a
wrong answer. Phase 9 MAP-Elites can re-fit on a much larger
self-play corpus.

### Tests

`tests/test_phase3_opponent_model.py` — 13 new tests, all green:

- 4 feature-extractor tests (key/shape sanity, player_index flip,
  pure-demolisher sanity, invalid-opp-id raises).
- 5 classifier tests (predict_proba shape + sums-to-1, fit
  determinism, predict-vs-proba consistency, update_posterior
  monotonicity on a synthetic well-separated corpus, unfitted
  raises).
- 4 action-predictor tests (top_k always returns >=1, zero-obs
  archetype falls back gracefully, unfitted raises, state-bucket
  helpers).

Combined with the 12 Phase 0.5 tests: **25 tests, 9.85s on Apple M4**.

### Gotchas / caveats

1. **CV gate failed.** This is by design per spec (do not loosen);
   Phase 4 must consume the posterior and treat low-confidence
   predictions as approximately uniform. The classifier's
   `predict_proba` output is the right interface, not `predict`.
2. **TURTLE_GRIND has 0 training samples.** v13's ranked corpus
   skews offensive — none of the 30 distinct opponents played a
   pure turtle. The classifier handles this gracefully (global-stats
   fallback) but the class can never be predicted with high
   confidence on this corpus.
3. **Singleton classes** (EDGE_FEINT, SUPPORT_BURST) cannot be
   leave-one-out-recovered: holding out the only training example
   leaves the fold with zero samples for that class. Structural; not
   fixable without more data.
4. **The labeler IS the ground truth.** Both the GaussianNB
   training labels AND the CV target labels come from the heuristic
   in `build_corpus.py:_label_from_features`. Future re-labeling
   passes (e.g. by-hand verification, k-means clustering) should
   keep that file's threshold-driven approach for reproducibility.

---

## NEXT PHASE: 4 — Offense engine

**Spec**: `docs/ATHENA_BUILD_PLAN.md` § Phase 5 (the build plan numbers
the offense / plan-search work as Phase 5 because the original Phase 3
was offense scripts and Phase 4 was the opponent model — the v3-planner
build sequence has folded those into Phase 3 = opponent model and
Phase 4 = the offense engine that consumes it). For implementation
specifics also reference `docs/ATHENA_BUILD_PLAN.md` § Phase 3
(offensive script portfolio: corner_ping, demolisher_line,
escorted_mixed, adaptive_hole, sd_opportunist, misdirection_counter).

### Scope (3-line summary)

Build a beam-search offense engine that, every turn, generates ~50
candidate offense plans (template × spawn timing × side selection),
sim-evaluates the top-K against the action predictor's top-K opponent
responses, and chooses the plan with the highest expected damage minus
expected own-loss. Lives at `algos/athena_v3_planner/planner/offense.py`
and `algos/athena_v3_planner/offense/templates/`.

### Prerequisites already in place

- 47 ranked replays + 14-feature classifier + per-archetype action
  predictor (Phase 3, this phase).
- Three defense archetype JSONs + six defense primitives (Phase 2).
- Lostkids baseline (Phase 1.5) for regression checks.
- Rust SimCore at 14.5 K single-core / 88 K 10-thread (Phase 0).

### Recommended Phase 4 task structure

1. **Offense template scaffolding.** Write the `AttackScript` interface
   (load Phase 3 spec from `docs/ATHENA_BUILD_PLAN.md` § Phase 3) and
   six initial templates seeded from the finalist corpus:
   - `corner_ping.py` (FUNNEL — first-wave Scout count from Citadel
     wall_HP/scout_HP, NOT hardcoded).
   - `demolisher_line.py` (Lostkids — Demolisher range 4.5 abuse).
   - `escorted_mixed.py` (2 Interceptor front, 6-12 Scout trail).
   - `adaptive_hole.py` (Harvard — side-sense weights using Citadel
     unit names).
   - `sd_opportunist.py` (BFS for dead-ends, Interceptor sniper).
   - `misdirection_counter.py` (Lostkids — counter the feint).

2. **Beam search.** `planner/offense.py:beam_search` — generate ~50
   candidate plans per turn, prune via `fast_static_eval` (~10us
   per plan), full SimCore eval on top 10. Score against the action
   predictor's top-K opponent response. Mandatory no-prediction
   baseline candidate (lower-bound EV).

3. **Score function.** Damage to opp HP (weight 3.5) + bonus SP from
   breaches − own mobile loss × support_loss_weight − defense
   compromise. Weights are tunable for Phase 9 MAP-Elites.

4. **Wire into algo_strategy.py.** Replace the defense-only variant
   with v3_planner main: each turn, run defense primitives FIRST
   (consumes SP), then offense beam search (consumes MP), with the
   13s SIGALRM watchdog + safe-fallback already in place from
   Phase 1.5.

5. **Validation.** /bestof 20 vs v13_second_ring + athena_baseline_lostkids.
   Build-plan gate: portfolio Wilson LB > 65% vs v13, > 50% vs Lostkids.
   Phase 2 defense-only baseline was 0/40 — any positive win-rate is
   evidence the offense engine is doing useful work.

### Phase 3 outputs Phase 4 will consume

- `opponent/classifier.py:ArchetypeClassifier` — `predict_proba`
  during a live match for the current opponent posterior.
- `opponent/action_predictor.py:ActionPredictor` — `top_k(state,
  posterior, k)` returns the (action, prob) list to score offense
  plans against.
- `data/opponent_features.npz` + `opponent/labels.json` — for
  re-fitting if Phase 4 wants different feature weights or a larger
  corpus.

### Gotchas inherited from Phase 3

1. **The classifier's CV gate FAILED at 0.489.** Phase 4 should NOT
   trust the top-1 label; instead use the full posterior in scoring.
   The BALANCED-collapse failure mode is, paradoxically, the right
   signal for unknown opponents (it approximates a uniform prior).
2. **Singletons + zero-sample classes.** TURTLE_GRIND has 0 training
   samples; EDGE_FEINT and SUPPORT_BURST have 1 each. Phase 4 should
   include a `low_confidence_fallback` path (e.g. when max-posterior
   < 0.40) that uses a generic offense template rather than the
   archetype-specific predictor.
3. **Action predictor returns marginal probabilities** that don't
   necessarily sum to 1 (they're scores, not strict probabilities).
   Treat as weights when scoring multi-step plans.
4. **The 100s pre-commit hook** still dominates wall-clock. Phase 4
   has more code than Phase 3 — batch logically.
5. **Phase 2B follow-ups still open.** The defense-only variant still
   loses 0/40 to v13 + Lostkids. Phase 4 must include offense to win
   anything; do not regress on the per-turn compute or watchdog
   safety baseline.


---

## Gotchas inherited by all subsequent phases (originally for Phase 2)

1. **Python**: use `/opt/miniconda3/bin/python3.13` for any sim/parity
   tooling. System Python 3.9 trips on `@dataclass(slots=True)`.
2. **Pre-commit hook**: now patched. No `--no-verify` needed. The
   `mode_parity` regression takes ≈100 s on Apple M4 — budget for it.
3. **Replay file format**: each `.replay` starts with a blank line;
   one JSON object per line; line 1 = config header, last frame
   carries `endStats`. Use
   `algos/athena/sim/validate.py:_parse_replay` for robust loading,
   or `algos/athena_v3_planner/sim/replay_inventory.py` for stdlib
   metadata.
4. **47 ranked replays** in the corpus. Cross-validation already shows
   Python ↔ Rust bit-identical on all 3,319 deploy turns (`d103907`).
5. **Don't reuse `algos/codex_v*`** — they perform poorly on the live
   ladder.
6. **Phase 0.5 utilities are now ready to wire into Phase 2.** They
   live in `algos/athena_v3_planner/opponent/action_frame_utils.py`
   exported via `opponent/__init__.py`. Each is a `@dataclass` so the
   planner can hold one per-match and feed it action frames.
7. **`run_match.py` requires absolute paths** — using relative paths
   trips an `IOException: No such file or directory` at engine bootup.
   `tools/bestof.py` already handles this; if you call run_match.py
   directly, prefix with `/Users/tahakhan/.../algos/...`.
8. **Local engine ≠ live engine on Supports.** Don't trust local
   bestof for any Support-shield-dependent strategy without first
   patching `C1GamesStarterKit-master/game-configs.json` to Citadel
   values (or running through the SimCore which has the right config).
   See `docs/UNITS_REFERENCE.md` § Support note.
