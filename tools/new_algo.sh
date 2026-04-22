#!/usr/bin/env bash
# Scaffold a new algo by copying the starter template (or an existing algo).
# Usage:
#   ./tools/new_algo.sh <new_name>                 # copies python-algo starter
#   ./tools/new_algo.sh <new_name> <source_algo>   # copies from algos/<source> or a path

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
STARTER_KIT="$REPO_ROOT/C1GamesStarterKit-master"

if [ $# -lt 1 ]; then
  echo "Usage: $0 <new_algo_name> [source_algo_dir]"
  exit 1
fi

NAME="$1"
SRC="${2:-$STARTER_KIT/python-algo}"

# Validate name
if [[ "$NAME" =~ [[:space:]] ]] || [[ "$NAME" =~ [^A-Za-z0-9_-] ]]; then
  echo "ERROR: algo name '$NAME' must contain only letters, digits, _ and - (no spaces, no special chars)."
  exit 2
fi

# Short name resolution for source
if [ ! -d "$SRC" ] && [ -d "$REPO_ROOT/algos/$SRC" ]; then
  SRC="$REPO_ROOT/algos/$SRC"
fi

DEST="$REPO_ROOT/algos/$NAME"
if [ -d "$DEST" ]; then
  echo "ERROR: algo already exists at $DEST"
  exit 3
fi

mkdir -p "$REPO_ROOT/algos"
cp -r "$SRC" "$DEST"

echo "[new_algo] Created: $DEST"
echo "[new_algo] Source:  $SRC"
echo "[new_algo] Edit:    $DEST/algo_strategy.py"
