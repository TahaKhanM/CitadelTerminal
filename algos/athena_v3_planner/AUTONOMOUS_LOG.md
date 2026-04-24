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

## NEXT PHASE: 1.5 — Lostkids baseline port (regression baseline)

**Spec**: `docs/ATHENA_BUILD_PLAN.md` § Phase 1.5.

The port lives at a brand-new `algos/athena_baseline_lostkids/`
(scaffold from `C1GamesStarterKit-master/python-algo/`, then port
strategy from
`research/finalist_repos/Terminal-Lostkids/python-2l-aet/algo_strategy.py`).

### Tasks

1. **Scaffold the algo folder.** `algos/athena_baseline_lostkids/`
   from the starter template (or use the `/new-algo` skill).
2. **Port the Lostkids algo.** Lostkids ALREADY uses current Citadel
   shorthands (WALL/TURRET/etc.) — this is mostly a straight copy
   with two corrections:
   - Verify constants vs `data/citadel_config_snapshot.json`
     (Lostkids' `MP >= 17` interceptor threshold is Citadel-compatible
     because Citadel keeps the 25 % MP decay — but cross-check the
     income formula `1 + turn // 5` MP/turn).
   - Replace any direct-from-`game-configs.json` reads with config
     reads from `on_game_start(config)` — Lostkids doesn't actually
     hardcode but a quick grep is mandatory.
3. **Add production safety wrappers.** Top-level `try/except` in
   `on_turn` that falls through to a "minimal safe turn" (defense-only
   no-op offense), plus a 13 s watchdog. The build plan calls these
   "non-negotiable" for any algo we eventually ship to ranked.
4. **Regression test.** `/bestof 50 athena_baseline_lostkids
   v13_second_ring`. **Validation gate: Wilson lower bound on win
   rate > 60 %.** If it falls short, the port has bugs OR v13 is
   unexpectedly strong against the Lostkids strategy. Debug before
   advancing — DO NOT proceed with a flawed regression baseline.

### Why we want this baseline

Phase 2+ defenses and Phase 3+ offense will both report "athena_vN vs
v13" AND "athena_vN vs athena_baseline_lostkids". The Lostkids port is
the second comparison anchor: a known-strong Citadel-aware strategy
that the new planner has to outperform. Without a working port,
"better than v13" alone doesn't tell us if Athena is genuinely
winning.

### What Phase 1.5 should NOT touch

- The Phase 0.5 utilities under `opponent/` — they're not used by the
  Lostkids baseline (Lostkids has its own `batch_count_history`
  inline). Athena will plug the new `BatchCountTracker` etc. into the
  planner in Phase 2+.
- `algos/athena/sim_rs/` and the rest of the SimCore — Phase 1 is
  still IN PROGRESS upstream (Phase 1.B.1 sim parity work on a
  separate branch). Phase 1.5 is the regression *baseline*, not a sim
  validation.

### Useful pointers

- `research/finalist_repos/Terminal-Lostkids/python-2l-aet/`: source.
  Skim `algo_strategy.py` end-to-end before porting. Defense order is
  in `defense-order.json`.
- `algos/v13_second_ring/algo_strategy.py`: the regression target.
- `tools/bestof.py` (or the `/bestof` skill): N-game runner with Wilson
  CI.
- `data/citadel_config_snapshot.json`: live Citadel values; treat as
  authoritative.
- `docs/STRATEGY_GUIDE.md`: relevant archetypes if you need to debug
  the port behaviour.

---

## Gotchas inherited by Phase 1.5

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
4. **47 ranked replays** in the corpus (not 23 — bigger than expected).
   Cross-validation already shows Python ↔ Rust bit-identical on all
   3,319 deploy turns (`d103907` log).
5. **Don't reuse `algos/codex_v*`** — they perform poorly on the live
   ladder.
6. **Phase 0.5 utilities are scoped for Phase 2+**, not 1.5. Don't
   wire them into the Lostkids baseline; they belong in the planner
   side of Athena, not in the regression baseline.
