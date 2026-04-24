# Pre-commit hook regression-block demonstration (2026-04-24)

Purpose: prove that `algos/athena/sim/install_hooks.sh` installs a
working pre-commit hook that blocks a deliberately-injected regression
before it can land on `main`. Evidence for the P5 verification-hygiene
deliverable.

## Procedure

1. Install the hook:
   ```
   bash algos/athena/sim/install_hooks.sh
   ```
   Output:
   ```
   Installed pre-commit hook at /Users/tahakhan/.../CitadelTerminal/.git/hooks/pre-commit
   Python interpreter: /opt/miniconda3/bin/python3.13 (3.13.12)
   Bypass: 'git commit --no-verify'
   Uninstall: 'rm /Users/tahakhan/.../CitadelTerminal/.git/hooks/pre-commit'
   ```

2. Switch to a throwaway branch:
   ```
   git checkout -b demo_hook_regression
   ```

3. Inject a regression that silently widens `FP32 = np.float32` to
   `FP32 = float` in `algos/athena/sim/pysim.py`:
   ```diff
   FP32 = np.float32
   + # DEMO REGRESSION: silently widen FP32 to float64.
   + FP32 = float  # intentional regression for hook demo
   ```
   This is the exact bug pattern the dtype-propagation test was built to
   catch — any arithmetic downstream of `FP32(x)` returns a Python
   `float` (float64), and the float32 invariant breaks.

4. Attempt commit:
   ```
   git add algos/athena/sim/pysim.py
   git commit -m "DEMO: intentional regression (FP32 → float) for hook demo"
   ```

## Result — commit BLOCKED

Hook output (captured in `/tmp/hook_demo_attempt2.log` during the run):

```
[pre-commit] SimCore quick parity gate
==> float32_propagation
    FAIL  0.2s
==> mode_parity
    PASS  69.9s

Report: /Users/tahakhan/.../algos/athena/sim/PARITY_REPORTS/2026-04-24.md
```

The commit **did not land** — `git log` still pointed at the pre-
regression HEAD; no new commit was created.

## PARITY_REPORTS output

The `float32_propagation` gate emitted specific widening-site
diagnostics at the time of the block (from `PARITY_REPORTS/2026-04-24.md`
immediately after the failing run; snapshot preserved at
`/tmp/hook_demo_parity_report.md`):

```
FAIL  round01 returns fp32: float32 widening at _round01(0.0): value=0.0 type=float
FAIL  apply_damage returns fp32: float32 widening at _apply_damage(15.0, 0.0, 8.0).new_hp: value=7.0 type=float
PASS  config specs are fp32
FAIL  ranked replay propagation: float32 widening at T0 post-deploy mobile 9.shield: value=0.0 type=float

3/4 tests failed
```

Each `FAIL` line names the exact call that widened + the offending
type (`type=float`) — the exact information a reviewer needs to trace
and fix the regression without running any further tools.

## Cleanup

```
git reset HEAD algos/athena/sim/pysim.py
git checkout -- algos/athena/sim/pysim.py
git checkout main
git branch -D demo_hook_regression
```

Branch `demo_hook_regression` existed only for the demo (its tip was
`8c62097`, same as main HEAD at demo time). Deleted post-demo.

## Independent verification

Anyone can reproduce by repeating steps 1–4 above. The hook is
idempotent and blocks the commit on every regression attempt. To
disable for a specific commit: `git commit --no-verify` (documented in
the install script's output).
