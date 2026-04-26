#!/bin/bash
# Run a battery of validation matches for each decoded algo:
#   1. mirror match (algo vs itself) — should not crash
#   2. 3 matches vs v13_second_ring
#   3. 3 cross-matches between candidates
# Output: validation_log.jsonl with 1 line per match

set -e
REPO=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
DECODED_DIR=$REPO/algos/replay_decoded
LOG=$DECODED_DIR/validation_log.jsonl

# Reset log
> "$LOG"

run_match() {
    local algo1=$1
    local algo2=$2
    local label=$3
    cd "$REPO" || exit 1
    OUT=$(python3 C1GamesStarterKit-master/scripts/run_match.py "$REPO/algos/$algo1" "$REPO/algos/$algo2" 2>&1 | tail -2 | head -1)
    # Extract winner
    WIN=$(echo "$OUT" | grep -oE 'Winner.*: [12]' | grep -oE '[12]$')
    # Get latest replay
    REPLAY=$(ls -t $REPO/C1GamesStarterKit-master/replays/*.replay | head -1)
    # Extract HP from endStats
    python3 -c "
import json
fpath = '$REPLAY'
with open(fpath) as f:
    lines = [l.strip() for l in f if l.strip().startswith('{')]
last = json.loads(lines[-1])
es = last.get('endStats', {})
print(json.dumps({
    'label': '$label',
    'algo1': '$algo1', 'algo2': '$algo2',
    'winner': es.get('winner'),
    'p1_hp': last.get('p1Stats', [None])[0],
    'p2_hp': last.get('p2Stats', [None])[0],
    'turns': es.get('turns'),
    'p1_crashed': es.get('player1', {}).get('crashed'),
    'p2_crashed': es.get('player2', {}).get('crashed'),
    'replay': fpath.split('/')[-1],
}))
" >> "$LOG"
}

CANDS=("funnel-rush-v9" "funnel-rush-v8" "funnel-crush" "switch2" "python-algo-classic" "oleh-v2")

# Mirror matches
for c in "${CANDS[@]}"; do
    echo "Mirror: replay_decoded/$c"
    run_match "replay_decoded/$c" "replay_decoded/$c" "mirror_$c"
done

# vs v13_second_ring (3 each)
for c in "${CANDS[@]}"; do
    for i in 1 2; do
        echo "Match $i: replay_decoded/$c vs v13_second_ring"
        run_match "replay_decoded/$c" "v13_second_ring" "vs_v13_${c}_${i}"
    done
done

# Cross-matches
echo "Cross: funnel-rush-v9 vs oleh-v2"
run_match "replay_decoded/funnel-rush-v9" "replay_decoded/oleh-v2" "cross_funnel-rush-v9_oleh-v2"
echo "Cross: funnel-crush vs python-algo-classic"
run_match "replay_decoded/funnel-crush" "replay_decoded/python-algo-classic" "cross_funnel-crush_classic"
echo "Cross: switch2 vs funnel-rush-v8"
run_match "replay_decoded/switch2" "replay_decoded/funnel-rush-v8" "cross_switch2_funnel-rush-v8"

echo "Done. Log: $LOG"
