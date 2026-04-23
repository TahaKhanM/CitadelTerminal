#!/usr/bin/env bash
# Quick syntax/runtime check of an algo using test_algo_<os>.
# Usage: ./tools/test.sh <algo_dir> [replay_file]

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
STARTER_KIT="$REPO_ROOT/C1GamesStarterKit-master"

"$REPO_ROOT/tools/apply_competition_config.sh"

ALGO="${1:-$STARTER_KIT/python-algo}"
REPLAY="${2:-}"

# Short name resolution
if [ ! -d "$ALGO" ] && [ -d "$REPO_ROOT/algos/$ALGO" ]; then
  ALGO="$REPO_ROOT/algos/$ALGO"
fi

# Pick OS binary
case "$(uname -s)" in
  Darwin) BIN="$STARTER_KIT/scripts/test_algo_mac" ;;
  Linux)  BIN="$STARTER_KIT/scripts/test_algo_linux" ;;
  *)      BIN="$STARTER_KIT/scripts/test_algo_windows.exe" ;;
esac

# Ensure executable
chmod +x "$BIN" 2>/dev/null || true

echo "[test.sh] Algo:   $ALGO"
echo "[test.sh] Binary: $BIN"

if [ -n "$REPLAY" ]; then
  echo "[test.sh] Replay: $REPLAY"
  "$BIN" "$ALGO" "$REPLAY"
else
  "$BIN" "$ALGO"
fi
