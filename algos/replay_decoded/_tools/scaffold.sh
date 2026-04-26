#!/bin/bash
# Scaffold the algo directories with the required plumbing for each
# decoded opponent. Copies algo_strategy.py template, gamelib/, run.sh,
# algo.json from v13_second_ring as the canonical base.

set -e

REPO=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
TEMPLATE=$REPO/algos/replay_decoded/_tools/algo_template.py
SOURCE_ALGO=$REPO/algos/v13_second_ring

if [ "$#" -lt 1 ]; then
    echo "Usage: scaffold.sh <opponent_name> [<opponent_name>...]"
    exit 1
fi

for opp in "$@"; do
    DEST=$REPO/algos/replay_decoded/$opp
    mkdir -p "$DEST"
    if [ ! -f "$DEST/decoded_turns.json" ]; then
        echo "MISSING decoded_turns.json for $opp; run decode_opponent.py first"
        exit 1
    fi
    # gamelib (copy not symlink so the algo is self-contained for upload)
    if [ ! -d "$DEST/gamelib" ]; then
        cp -r "$SOURCE_ALGO/gamelib" "$DEST/gamelib"
    fi
    # algo.json
    cat > "$DEST/algo.json" <<EOF
{"language": "python", "name": "replay-decoded-$opp"}
EOF
    # run.sh
    cp "$SOURCE_ALGO/run.sh" "$DEST/run.sh"
    chmod +x "$DEST/run.sh"
    # algo_strategy.py
    cp "$TEMPLATE" "$DEST/algo_strategy.py"
    echo "Scaffolded $DEST"
done
