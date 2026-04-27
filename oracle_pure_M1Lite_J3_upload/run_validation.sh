#!/bin/bash
# Quick validation script for Variant J3.

set -u

ROOT="/Users/tahakhan/Documents/Work/Projects/CitadelTerminal"
J3="$ROOT/oracle_pure_M1Lite_J3_upload"
RESULTS="$J3/validation_results.txt"
RUNNER="$ROOT/C1GamesStarterKit-master/scripts/run_match.py"

> "$RESULTS"

run_match() {
  local algo1=$1; local algo2=$2; local label=$3
  echo "Running: $label"
  local t0=$(date +%s)
  local out
  out=$(python3 "$RUNNER" "$algo1" "$algo2" 2>&1)
  local t1=$(date +%s)
  local winner
  winner=$(echo "$out" | grep -oE 'Winner \(p1 perspective, 1 = p1 2 = p2\): [0-9]+' | tail -1 | awk '{print $NF}')
  if [ -z "$winner" ]; then
    winner="?"
  fi
  local elapsed=$((t1 - t0))
  echo "$label: winner=$winner time=${elapsed}s" | tee -a "$RESULTS"
}

# Define opponents
PYTHON_ALGO="$ROOT/C1GamesStarterKit-master/python-algo"
HEURISTIC_V1="$ROOT/algos/heuristic_v1"
V13="$ROOT/algos/v13_second_ring"
LOSTKIDS="$ROOT/research/finalist_repos/Terminal-Lostkids/python-algo"
FUNNEL="$ROOT/research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER"
VD="$ROOT/oracle_pure_M1Lite_VD_upload"
M1LITE="$ROOT/oracle_pure_M1Lite_upload"

# Tier A
run_match "$J3" "$PYTHON_ALGO" "TierA J3_vs_python"
run_match "$PYTHON_ALGO" "$J3" "TierA python_vs_J3"
run_match "$J3" "$HEURISTIC_V1" "TierA J3_vs_heuristic_v1"
run_match "$HEURISTIC_V1" "$J3" "TierA heuristic_v1_vs_J3"
run_match "$J3" "$V13" "TierA J3_vs_v13"
run_match "$V13" "$J3" "TierA v13_vs_J3"
run_match "$J3" "$LOSTKIDS" "TierA J3_vs_lostkids"
run_match "$LOSTKIDS" "$J3" "TierA lostkids_vs_J3"
run_match "$J3" "$FUNNEL" "TierA J3_vs_funnel"
run_match "$FUNNEL" "$J3" "TierA funnel_vs_J3"

# H2H
[ -d "$VD" ] && run_match "$J3" "$VD" "H2H J3_vs_VD"
[ -d "$VD" ] && run_match "$VD" "$J3" "H2H VD_vs_J3"
[ -d "$M1LITE" ] && run_match "$J3" "$M1LITE" "H2H J3_vs_M1Lite"
[ -d "$M1LITE" ] && run_match "$M1LITE" "$J3" "H2H M1Lite_vs_J3"

echo ""
echo "=== Summary ==="
cat "$RESULTS"
