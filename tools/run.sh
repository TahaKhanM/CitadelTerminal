#!/usr/bin/env bash
# Thin wrapper around run_match.py that keeps replays in this repo's replays/ dir.
# Usage: ./tools/run.sh <algo1_dir> <algo2_dir>
#   Defaults to python-algo vs python-algo if not provided.

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
STARTER_KIT="$REPO_ROOT/C1GamesStarterKit-master"
REPLAY_OUT="$REPO_ROOT/replays"

ALGO1="${1:-$STARTER_KIT/python-algo}"
ALGO2="${2:-$STARTER_KIT/python-algo}"

# If a short name was given (e.g., "v1"), resolve it under algos/
if [ ! -d "$ALGO1" ] && [ -d "$REPO_ROOT/algos/$ALGO1" ]; then
  ALGO1="$REPO_ROOT/algos/$ALGO1"
fi
if [ ! -d "$ALGO2" ] && [ -d "$REPO_ROOT/algos/$ALGO2" ]; then
  ALGO2="$REPO_ROOT/algos/$ALGO2"
fi

# run_match.py cd's into the starter-kit dir before invoking engine.jar,
# so relative paths break. Always convert to absolute.
ALGO1="$(cd "$ALGO1" && pwd)"
ALGO2="$(cd "$ALGO2" && pwd)"

mkdir -p "$REPLAY_OUT"

echo "[run.sh] Algo 1: $ALGO1"
echo "[run.sh] Algo 2: $ALGO2"

python3 "$STARTER_KIT/scripts/run_match.py" "$ALGO1" "$ALGO2"

# Move any replay(s) that were written to the starter kit's default output dir
SRC_REPLAYS="$STARTER_KIT/replays"
if [ -d "$SRC_REPLAYS" ]; then
  shopt -s nullglob
  REPLAYS=( "$SRC_REPLAYS"/*.replay )
  if [ ${#REPLAYS[@]} -gt 0 ]; then
    TS="$(date +%Y%m%d_%H%M%S)"
    N1="$(basename "$ALGO1")"
    N2="$(basename "$ALGO2")"
    for r in "${REPLAYS[@]}"; do
      mv "$r" "$REPLAY_OUT/${N1}_vs_${N2}_${TS}.replay"
    done
    echo "[run.sh] Replay moved to: $REPLAY_OUT/${N1}_vs_${N2}_${TS}.replay"
  fi
fi
