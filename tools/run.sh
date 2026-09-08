#!/usr/bin/env bash
# Run one isolated match and keep only that match's replay and diagnostics.
set -euo pipefail
REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
exec "${PYTHON_CMD:-python3}" "$REPO_ROOT/tools/match_runner.py" "$@"
