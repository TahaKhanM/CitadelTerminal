#!/usr/bin/env bash
# Portable first-turn protocol check; no platform-specific test binary required.
set -euo pipefail
REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
exec "${PYTHON_CMD:-python3}" "$REPO_ROOT/tools/smoke_algo.py" "${1:-$REPO_ROOT/C1GamesStarterKit-master/python-algo}"
