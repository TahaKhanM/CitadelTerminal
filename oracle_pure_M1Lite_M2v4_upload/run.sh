#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
if [ -n "${PYTHON_CMD}" ]; then
    PY="${PYTHON_CMD}"
elif command -v python3 >/dev/null 2>&1; then
    PY=python3
else
    PY=python
fi
"$PY" -u "$DIR/algo_strategy.py"
