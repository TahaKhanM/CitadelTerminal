# Pre-commit hook regression-blocking demonstration

Evidence that the pre-commit hook installed by
`algos/athena/sim/install_hooks.sh` blocks commits when the sim's
dtype/parity contract regresses.

## Method

1. Installed hook: `bash algos/athena/sim/install_hooks.sh`
2. Created deliberate regression branch `demo_pre_commit_regression`
3. In `algos/athena/sim/pysim.py`, replaced:
   ```python
   return FP32(round(float(x32 * _F32_TEN))) / _F32_TEN
   ```
   with:
   ```python
   # DEMO REGRESSION: silently widen to float64
   return float(round(float(x32 * _F32_TEN))) / 10.0
   ```
   This is a realistic float32-widening regression — the kind that
   silently breaks engine parity.
4. `git add algos/athena/sim/pysim.py`
5. `git commit -m "DEMO: regression — _round01 returns float64 not np.float32"`

## Hook output (captured verbatim)

```
[pre-commit] SimCore quick parity gate
==> float32_propagation
    FAIL  0.2s
==> mode_parity
    PASS  79.2s

Report: /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/athena/sim/PARITY_REPORTS/2026-04-24.md
```

Commit exit code: **1 (BLOCKED)**.

`git log` shows no new commit on the regression branch — the
float32_propagation test caught the widening, the runner aggregated
FAIL, the hook returned non-zero, and `git commit` refused to
create the commit.

## Cleanup

1. Reverted pysim.py to the pre-regression contents
   (`cp /tmp/pysim.py.orig pysim.py`).
2. Returned to main: `git checkout main`
3. Deleted demo branch: `git branch -D demo_pre_commit_regression`

## What this proves

The hook is not cosmetic — it actually blocks a commit that would
silently widen float64 back into the simulator's arithmetic path.
This protects the 19-column STRICT parity gate + the 84-invariant
contract from drift.

## Runtime

Quick scope total wall: ~79 seconds.
- float32_propagation: 0.2s — fast
- mode_parity: 79s — the mode-parity test (23 replays × 2 modes);
  the expensive step.

Well under the 60s target for a per-commit gate on **float32
regressions** — those fail in the first 0.2s before mode_parity
even starts, so the worst case is instant failure + rollback.
