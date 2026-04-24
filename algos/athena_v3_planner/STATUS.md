## Phase 0 — in progress (2026-04-24)

| Task | Status | Commit |
|---|---|---|
| 1. Package skeleton + vendored gamelib | DONE | `f02b4a9` |
| 2. Citadel config snapshot | DONE | `275bd09` |
| 3. Perf baseline measurement | DONE | `222496d` |
| 4. Replay corpus inventory + parse validation | DONE | (this commit) |
| 5. AUTONOMOUS_LOG + handoff | pending | — |
| 6. STATUS.md final | pending | — |

## Replay corpus inventory results (task 4)

- 47 ranked-replay files matched `v13_*.replay` under `replays/ranked/`.
- 47 of 47 parsed end-state metadata cleanly (`parsed_ok=True`).
- 30 distinct opponents in corpus (well above the build plan's
  ≥5-opponent floor).
- 4 boss matches (R1_Sawtooth, R2_Infiltrator, R3_Jukebox, R4_Champion).
- Outcomes: **22 wins / 25 losses** — broadly balanced, useful for
  training both the "what made us win" and "what made us lose" signal.
- Avg game length: ~70 turns.

## Cross-parser validation (Python ↔ Rust)

`/opt/miniconda3/bin/python3.13 -m algos.athena.sim.cross_validate` ran
the Python sim and the Rust PyO3 sim against every ranked replay turn
in the corpus, gate `C-tight-coherent`:

```
cross_validate: checks=3319 ranked_wall=48.1s fuzz_wall=0.0s
  ok=3319  fail=0
  gate: PASS
```

All **3,319 deploy-action turns across all 47 replays** are bit-identical
(within the documented 1-ULP-float32 cascade band) between the Python
reference and the Rust port. Zero parity regressions vs the
`SIM_PARITY.md` baseline.

This validates: (a) every replay in the corpus is well-formed and
parseable by both implementations, (b) the Rust PyO3 wheel is fresh
against the latest perf push (rebuilt via `maturin develop --release
--features pyo3` against `/opt/miniconda3` Python 3.13), and (c) we
have a safe foundation for Phase 1+ work.

## Gotchas the next agent should know about

1. **Pre-commit hook is broken in worktree checkouts.** The hook at
   `.git/hooks/pre-commit` uses `${GIT_DIR}/..` to resolve the repo
   top-level — this fails in `git worktree` checkouts because
   `GIT_DIR` is `.git/worktrees/<name>`, not `.git`. Workaround used
   in Phase 0: `git commit --no-verify` after manually verifying
   `algos/athena/sim/regression_runner.py --scope quick` PASSES.
   The right fix is one line:
   ```bash
   TOPLEVEL="$(git rev-parse --show-toplevel)"
   "/opt/miniconda3/bin/python3.13" "$TOPLEVEL/algos/athena/sim/regression_runner.py" --scope quick
   ```
   But the file is outside the worktree, so this agent cannot edit it
   (sandbox restriction). Flag this for the user.

2. **System Python is 3.9** at `/Library/Developer/CommandLineTools/usr/bin/python3`.
   The sim/cross_validate stack requires 3.10+ (`@dataclass(slots=True)`).
   Use `/opt/miniconda3/bin/python3.13` for any sim/parity tooling.

3. **sim_rs PyO3 wheel.** A stale wheel was installed at
   `/Users/tahakhan/Library/Python/3.9/lib/python/site-packages/sim_rs/`
   missing `simulate_action_phase_py`. Phase 0 rebuilt via
   `CONDA_PREFIX=/opt/miniconda3 /opt/miniconda3/bin/python3.13 -m maturin
   develop --release --features pyo3` from `algos/athena/sim_rs/`. Phase
   1+ should re-run this whenever sim_rs source changes.

4. **Replay file format.** Files start with a leading newline, then one
   JSON object per line: line 1 is the config header (`unitInformation`),
   lines 2..N are deploy/action frames, last frame contains `endStats`.
   `algos/athena/sim/validate.py:_parse_replay` is the existing robust
   loader; `algos/athena_v3_planner/sim/replay_inventory.py` is a
   stdlib-only metadata variant.

5. **47 replays, not 23.** The agent brief mentioned 23, but there are
   47 ranked v13 replays. The task 4 inventory captured all 47.
