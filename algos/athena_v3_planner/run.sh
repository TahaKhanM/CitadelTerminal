#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# Phase 5B: prefer python3.13 (where sim_rs PyO3 wheel was rebuilt against
# the live API). The system python3 is 3.9 with a stale wheel that fails
# with "list cannot be converted to PyTuple" on the simulate_action_phase_py
# signature. Fall back to python3 if 3.13 isn't on the path.
if command -v /opt/miniconda3/bin/python3.13 >/dev/null 2>&1; then
    PY=/opt/miniconda3/bin/python3.13
elif command -v python3.13 >/dev/null 2>&1; then
    PY=$(command -v python3.13)
else
    PY=${PYTHON_CMD:-python3}
fi
"$PY" -u "$DIR/algo_strategy.py"
