#!/usr/bin/env bash
# Diff the strategy files between two algos.
# Shows only differences in algo_strategy.py (the bit you actually edited).
#
# Usage:
#   ./tools/diff_algos.sh <algo_a> <algo_b>
#   ./tools/diff_algos.sh v1 v2
#   ./tools/diff_algos.sh v1 v2 --full     # include gamelib/ diffs too

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

if [ $# -lt 2 ]; then
  echo "Usage: $0 <algo_a> <algo_b> [--full]"
  exit 1
fi

A="$1"
B="$2"
FULL="${3:-}"

# Short-name resolution
[ ! -d "$A" ] && [ -d "$REPO_ROOT/algos/$A" ] && A="$REPO_ROOT/algos/$A"
[ ! -d "$B" ] && [ -d "$REPO_ROOT/algos/$B" ] && B="$REPO_ROOT/algos/$B"

if [ ! -d "$A" ] || [ ! -d "$B" ]; then
  echo "ERROR: one or both algo dirs missing: $A , $B"
  exit 2
fi

echo "=== Strategy file diff: $(basename $A) vs $(basename $B) ==="
diff -u "$A/algo_strategy.py" "$B/algo_strategy.py" || true

if [ "$FULL" = "--full" ]; then
  echo ""
  echo "=== Full recursive diff ==="
  diff -r "$A" "$B" --exclude='__pycache__' --exclude='*.pyc' --exclude='.DS_Store' || true
fi
