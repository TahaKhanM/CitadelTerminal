"""Regression runner — the one-shot script that gates every change.

Runs (in order) against the current working tree:

  1. test_float32_propagation.py   — Cluster I dtype guard
  2. test_mode_parity.py           — dual-mode byte-identical
  3. validate.py --full            — 23-column Phase 1.B.1-final gate
  4. metamorphic --fuzz-n=<N>      — M1-M4 on N random configs
  5. fuzz --n=<N>                  — 12-category fuzz batch
  6. invariants check              — when invariants.py lands; optional
  7. cross_validate.py             — Python ↔ Rust; no-op until sim_rs ships

Emits `algos/athena/sim/PARITY_REPORTS/<YYYY-MM-DD>.md` per run and
appends a row to `algos/athena/sim/timing_history.csv`.

Exit codes:
  0 — all gates pass
  1 — any gate fails (validator STRICT or CASCADE >=1%, fuzz/metamorphic
      miss, dtype widening, mode divergence)

Scope modes (CLI `--scope`):
  quick  — float32 + mode_parity only (< 60s target; pre-commit hook)
  short  — quick + validator + metamorphic N=120   (< 5 min)
  full   — short + fuzz N=10000 + metamorphic N=1200 (~ 1 min on laptop)
  heavy  — full + fuzz N=100000 (~ 6 min)

Usage:
  python3 algos/athena/sim/regression_runner.py --scope quick
  python3 algos/athena/sim/regression_runner.py --scope full
"""

from __future__ import annotations

import argparse
import csv
import datetime
import subprocess
import sys
import time
from pathlib import Path
from typing import List, Tuple

# Use the SAME python that invoked this runner, so children inherit the
# correct interpreter (avoids Python 3.9 vs 3.13 mismatch when the hook
# fires via /usr/bin/env python3 but the repo targets a newer Python via
# dataclass slots=True etc.).
PY = sys.executable

ROOT = Path(__file__).resolve().parent.parent.parent.parent  # repo root
SIM = Path(__file__).resolve().parent
REPORTS_DIR = SIM / "PARITY_REPORTS"
TIMING_CSV = SIM / "timing_history.csv"


def _run(cmd: list, label: str, timeout: int = 600) -> Tuple[bool, float, str]:
    """Run a subprocess; return (ok, wall_s, tail_of_output)."""
    t0 = time.perf_counter()
    try:
        cp = subprocess.run(cmd, capture_output=True, text=True,
                             timeout=timeout, cwd=ROOT)
        dt = time.perf_counter() - t0
        tail = (cp.stdout[-1000:] + "\n---stderr:\n" + cp.stderr[-500:]).strip()
        return (cp.returncode == 0, dt, tail)
    except subprocess.TimeoutExpired:
        dt = time.perf_counter() - t0
        return (False, dt, f"[TIMEOUT after {timeout}s] {label}")


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--scope", choices=("quick", "short", "full", "heavy"),
                     default="short")
    ap.add_argument("--seed", type=int, default=42)
    args = ap.parse_args()

    steps: List[Tuple[str, list, int]] = []

    # Always: dtype propagation + dual-mode parity
    steps.append((
        "float32_propagation",
        [PY, "-m", "algos.athena.sim.tests.test_float32_propagation"],
        60,
    ))
    steps.append((
        "mode_parity",
        [PY, "-m", "algos.athena.sim.tests.test_mode_parity"],
        300,
    ))

    if args.scope in ("short", "full", "heavy"):
        steps.append((
            "validator",
            [PY, "algos/athena/sim/validate.py", "--full"],
            300,
        ))
        steps.append((
            "metamorphic_120",
            [PY, "-m", "algos.athena.sim.metamorphic",
             "--fuzz-n", "120", "--seed", str(args.seed)],
            120,
        ))

    if args.scope in ("full", "heavy"):
        steps.append((
            "fuzz_10k",
            [PY, "-m", "algos.athena.sim.fuzz",
             "--n", "10000", "--seed", str(args.seed)],
            300,
        ))
        steps.append((
            "metamorphic_1200",
            [PY, "-m", "algos.athena.sim.metamorphic",
             "--fuzz-n", "1200", "--seed", str(args.seed)],
            300,
        ))

    if args.scope == "heavy":
        steps.append((
            "fuzz_100k",
            [PY, "-m", "algos.athena.sim.fuzz",
             "--n", "100000", "--seed", str(args.seed)],
            1200,
        ))

    REPORTS_DIR.mkdir(exist_ok=True)
    today = datetime.date.today().isoformat()
    report_path = REPORTS_DIR / f"{today}.md"

    results = []
    any_fail = False
    for label, cmd, timeout in steps:
        print(f"==> {label}")
        ok, dt, tail = _run(cmd, label, timeout=timeout)
        print(f"    {'PASS' if ok else 'FAIL'}  {dt:.1f}s")
        results.append((label, ok, dt, tail))
        if not ok:
            any_fail = True

    # Report
    lines = [f"# Parity report — {today}", ""]
    lines.append(f"Scope: `{args.scope}`, seed: `{args.seed}`")
    lines.append("")
    lines.append("| Gate | Result | Wall | ")
    lines.append("|---|---|---|")
    for label, ok, dt, _ in results:
        lines.append(f"| {label} | {'PASS' if ok else 'FAIL'} | {dt:.1f}s |")
    lines.append("")
    lines.append(f"**Overall: {'PASS' if not any_fail else 'FAIL'}**")
    lines.append("")
    for label, ok, dt, tail in results:
        lines.append(f"## {label}")
        lines.append("```")
        lines.append(tail)
        lines.append("```")
    report_path.write_text("\n".join(lines))
    print()
    print(f"Report: {report_path}")

    # Append timing row
    header = ("date", "scope", "seed", "overall", *[s[0] for s in steps])
    row = [today, args.scope, str(args.seed), "pass" if not any_fail else "fail"]
    row.extend("pass" if r[1] else "fail" for r in results)
    new_file = not TIMING_CSV.exists()
    with open(TIMING_CSV, "a", newline="") as f:
        w = csv.writer(f)
        if new_file:
            w.writerow(header)
        w.writerow(row)

    return 0 if not any_fail else 1


if __name__ == "__main__":
    sys.exit(main())
