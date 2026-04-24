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

## NEXT PHASE: 2 — Defense engine

**Spec**: `docs/ATHENA_BUILD_PLAN.md` § Phase 2.

### Scope

Build the planner-side defense layer at:
- `algos/athena_v3_planner/planner/defense.py` (logic).
- `algos/athena_v3_planner/defenses/*.json` (3+ archetypes:
  ring / corner-castle / mid-row examples — see plan doc).

Goals:
- 3+ archetypes loadable from JSON (so we can A/B test build orders
  without code changes).
- Probabilistic placement: each tile gets a placement priority +
  conditional spawn rule (e.g. "spawn only if enemy left-edge MP
  history > 8").
- Refund-on-low-HP logic generalized from Lostkids' 0.3/0.5 thresholds
  (read from defense.json so different archetypes can use different
  thresholds).
- Repair logic: if we spent SP this turn replacing a removed structure,
  prioritize that over net-new structures.
- Breach-reactive: use `BreachLocationTracker` from Phase 0.5 to
  patch the actual breach hot tile next turn (the missing piece every
  public algo has too).

### Validation gates (Phase 2 — DOUBLE the previous baseline)

Defense-only Athena vs both baselines:

1. **vs `v13_second_ring`**: Wilson 95 % LB ≥ 35 % (so we tie-or-better
   on the local-engine archetype that the live ladder actually
   responds to).
2. **vs `athena_baseline_lostkids`** (newly available — Phase 1.5):
   Wilson 95 % LB ≥ 35 % (independent confirmation that we're not
   over-fitting to v13 quirks).

Both gates must pass before defense is "phase 2 done".

A defense-only algo is allowed to LOSE on offense (no Scouts /
Demolishers / Interceptors); it just needs to outlast the opponent's
HP-burn long enough to win on tied-HP tiebreak (which favors the
side with more SP-on-board at game end). 35 % LB is a deliberate
floor for "passable defense"; the goal isn't to win but to prove the
defense layer is solid.

### What Phase 2 should NOT touch

- `algos/athena_baseline_lostkids/` — frozen as a regression baseline.
  Future Athena variants compare against the SHA at `2a7f9d0`.
- The planner's offense layer (`algos/athena_v3_planner/offense/`) —
  that's Phase 3.
- `algos/athena/sim_rs/` and SimCore parity work — out of scope.

### Useful pointers

- `algos/athena_v3_planner/opponent/action_frame_utils.py` — six
  Phase 0.5 utilities (BatchCountTracker, SpawnXHistogram,
  WallRemovalDetector, **BreachLocationTracker**, ResourceTracker,
  MisdirectionDetector). Wire BreachLocationTracker into the
  reactive-patch path.
- `algos/athena_baseline_lostkids/CITADEL_DELTA_AUDIT.md` — the four
  Citadel deltas, including the strategic notes on Support Y-placement
  and turret-upgrade weight that Phase 2 should respect.
- `algos/athena_baseline_lostkids/algo_strategy.py` — production
  wrappers (`_arm_watchdog`, `_safe_fallback_turn`) that Phase 2's
  `algos/athena_v3_planner/algo_strategy.py` should adopt verbatim or
  re-implement equivalently.
- `tools/bestof.py` — the gate-runner. 20 games per matchup is the
  Phase 1.5 cadence; Phase 2 may want 30+ for tighter CIs.

---

## Gotchas inherited by Phase 2

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
