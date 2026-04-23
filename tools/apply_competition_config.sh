#!/usr/bin/env bash
# Sync C1GamesStarterKit-master/game-configs.json from configs/competition-game-configs.json.
#
# The starter kit ships with pre-competition values (old Encryptor block, higher
# starting resources, lower turret damage). This script overwrites that file
# with the tracked competition config so local matches match server behaviour.
#
# Idempotent: no-op when already in sync. First-time sync backs the starter-kit
# original up to game-configs.json.starterkit-original.
#
# Called at the top of every tool that runs a local match.

set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SRC="$REPO_ROOT/configs/competition-game-configs.json"
DST="$REPO_ROOT/C1GamesStarterKit-master/game-configs.json"
BACKUP="$DST.starterkit-original"

if [ ! -f "$SRC" ]; then
  echo "[apply_competition_config] ERROR: $SRC missing" >&2
  exit 1
fi
if [ ! -f "$DST" ]; then
  echo "[apply_competition_config] ERROR: $DST missing" >&2
  exit 1
fi

if cmp -s "$SRC" "$DST"; then
  echo "config: already in sync"
  exit 0
fi

if [ ! -f "$BACKUP" ]; then
  cp "$DST" "$BACKUP"
fi

cp "$SRC" "$DST"
echo "config: synced competition values from configs/"
exit 0
