#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# On the competition server we use whatever python3 is provided
# (no sim_rs wheel is bundled there; the algo runs on heuristic-only
# offense scoring — see planner.offense.beam_search). Locally,
# python3.13 from miniconda has the maturin-built sim_rs wheel.
# PYTHON_CMD env var (set by C1 starter scripts) wins if present.
if [ -n "${PYTHON_CMD}" ]; then
    PY="${PYTHON_CMD}"
elif command -v python3 >/dev/null 2>&1; then
    PY=python3
elif command -v python3.13 >/dev/null 2>&1; then
    PY=python3.13
else
    PY=python
fi
"$PY" -u "$DIR/algo_strategy.py"
