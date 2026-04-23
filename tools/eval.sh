#!/usr/bin/env bash
# Pre-upload validation suite for a Citadel Terminal algo.
#
# Runs the full battery of local checks an algo should clear before being zipped
# for the competition site. Designed for any archetype (center-Scout rush, Demolisher
# train, Interceptor+Scout mix, self-destruct wave, own-wall path engineering, etc.) —
# it validates runtime correctness and surfaces win-rate signal, not any particular
# tactical pattern.
#
# Usage:
#   ./tools/eval.sh <algo_name> [opponent=<starter>] [n=5]
#
# Examples:
#   ./tools/eval.sh v20_demolisher_train
#   ./tools/eval.sh v21_interceptor_mix opp_defensive 10
#   ./tools/eval.sh v22_self_destruct v13_second_ring 10
#
# Runs:
#   1. Syntax / runtime check against bundled test replay (test_algo)
#   2. Single match vs opponent (quick sanity)
#   3. Best-of-2N vs opponent (Wilson 95% CI)
#   4. Mirror self-match (side-asymmetry detection; also catches the v13-family
#      40-40 mirror-tie signature — see memory/v13_mirror_ceiling.md)
#   5. Turn-time profile against the latest match replay
#
# Exits:
#   0 on all checks passing.
#   1 on any check failing (detail in stderr).

set -uo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
STARTER_KIT="$REPO_ROOT/C1GamesStarterKit-master"
DEFAULT_OPPONENT="$STARTER_KIT/python-algo"

"$REPO_ROOT/tools/apply_competition_config.sh"

if [ $# -lt 1 ]; then
  echo "Usage: $0 <algo_name> [opponent=<starter>] [n=5]"
  echo ""
  echo "Runs the full baseline-evaluation suite on <algo_name>:"
  echo "  1. test-algo (syntax sanity)"
  echo "  2. run-match one game vs opponent"
  echo "  3. best-of-2N vs opponent"
  echo "  4. mirror self-match"
  echo "  5. turn-time profile"
  exit 2
fi

ALGO="$1"
OPPONENT="${2:-$DEFAULT_OPPONENT}"
N="${3:-5}"

# Short-name resolution
[ ! -d "$ALGO" ] && [ -d "$REPO_ROOT/algos/$ALGO" ] && ALGO="$REPO_ROOT/algos/$ALGO"
[ ! -d "$OPPONENT" ] && [ -d "$REPO_ROOT/algos/$OPPONENT" ] && OPPONENT="$REPO_ROOT/algos/$OPPONENT"

ALGO_NAME="$(basename "$ALGO")"
OPP_NAME="$(basename "$OPPONENT")"

HR="────────────────────────────────────────────────────────────"
OK="✓"
FAIL="✗"
FAILED=0

log_step() { echo; echo "$HR"; echo ">> $*"; echo "$HR"; }
log_ok()   { echo "$OK  $*"; }
log_fail() { echo "$FAIL $*" >&2; FAILED=$((FAILED + 1)); }

log_step "EVAL SUITE — $ALGO_NAME   (opponent: $OPP_NAME, n=$N)"

# ── Step 1: test-algo syntax ────────────────────────────────────────────
log_step "1/5  test-algo — syntax/runtime sanity check"
if "$REPO_ROOT/tools/test.sh" "$ALGO" >/dev/null 2>&1; then
  log_ok "test-algo: clean run against bundled test replay"
else
  log_fail "test-algo: crashed or errored. Run ./tools/test.sh $ALGO_NAME to see details."
fi

# ── Step 2: single match ───────────────────────────────────────────────
log_step "2/5  single match — $ALGO_NAME vs $OPP_NAME"
if "$REPO_ROOT/tools/run.sh" "$ALGO" "$OPPONENT" >/tmp/eval_match.log 2>&1; then
  # run.sh names the replay by basename; look for any match from the last 60s
  REPLAY="$(find "$REPO_ROOT/replays" -maxdepth 1 -name "*${ALGO_NAME}*${OPP_NAME}*.replay" -newermt '-60 seconds' 2>/dev/null | head -1)"
  if [ -z "$REPLAY" ]; then
    REPLAY="$(ls -t "$REPO_ROOT"/replays/*.replay 2>/dev/null | head -1)"
  fi
  if [ -n "$REPLAY" ]; then
    log_ok "Match completed, replay: $(basename "$REPLAY")"
  else
    log_fail "Match completed but no replay produced"
  fi
else
  log_fail "Match failed; see /tmp/eval_match.log"
fi

# ── Step 3: best-of-2N vs opponent ─────────────────────────────────────
log_step "3/5  best-of-$((N*2)) — $ALGO_NAME vs $OPP_NAME"
BESTOF_OUT=$(python3 "$REPO_ROOT/tools/bestof.py" "$ALGO" "$OPPONENT" "$N" 2>&1) || true
echo "$BESTOF_OUT" | tail -15
SUMMARY_LINE="$(echo "$BESTOF_OUT" | grep "^SUMMARY_JSON:" | head -1 || true)"
if [ -z "$SUMMARY_LINE" ]; then
  log_fail "best-of: could not find machine-readable summary line"
else
  A_WINS=$(echo "$SUMMARY_LINE" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1]).get('a_wins',0))" "$SUMMARY_LINE")
  CI_LOW=$(echo "$SUMMARY_LINE" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1]).get('ci_low',0))" "$SUMMARY_LINE")
  CI_HIGH=$(echo "$SUMMARY_LINE" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1]).get('ci_high',0))" "$SUMMARY_LINE")
  if awk "BEGIN {exit !($CI_LOW > 0.55)}"; then
    log_ok "best-of: $ALGO_NAME wins $A_WINS/$((N*2)) — CI lower bound $CI_LOW > 0.55"
  elif awk "BEGIN {exit !($CI_HIGH < 0.45)}"; then
    log_fail "best-of: opponent dominant — $ALGO_NAME wins only $A_WINS/$((N*2)), CI upper $CI_HIGH"
  else
    log_fail "best-of: inconclusive — $ALGO_NAME wins $A_WINS/$((N*2)), CI=[$CI_LOW, $CI_HIGH]"
  fi
fi

# ── Step 4: mirror self-match ──────────────────────────────────────────
log_step "4/5  mirror self-match — $ALGO_NAME vs $ALGO_NAME"
MIRROR_OUT=$(python3 "$REPO_ROOT/tools/bestof.py" "$ALGO" "$ALGO" 3 2>&1) || true
echo "$MIRROR_OUT" | tail -10
MIRROR_SUMMARY="$(echo "$MIRROR_OUT" | grep "^SUMMARY_JSON:" | head -1 || true)"
# In mirror, 'a_wins' == wins when ALGO is P1 (first 3 games),
# 'b_wins' == wins when ALGO is P2 (last 3 games after orientation flip).
# Side asymmetry exists if one orientation wins 3/3 and the other wins 0/3.
if [ -z "$MIRROR_SUMMARY" ]; then
  log_fail "mirror: could not find machine-readable summary line"
else
  P1_ORIENTATION_WINS=$(echo "$MIRROR_SUMMARY" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1])['a_wins'])" "$MIRROR_SUMMARY")
  P2_ORIENTATION_WINS=$(echo "$MIRROR_SUMMARY" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1])['b_wins'])" "$MIRROR_SUMMARY")
  TIE_COUNT=$(echo "$MIRROR_SUMMARY" | python3 -c "import sys,json; print(json.loads(sys.argv[1].split(':',1)[1])['ties'])" "$MIRROR_SUMMARY")
  TOTAL_MIRROR=6
  # Imbalance = absolute difference between wins when-P1 and wins-when-P2. ≥3 is extreme.
  DIFF=$(( P1_ORIENTATION_WINS > P2_ORIENTATION_WINS ? P1_ORIENTATION_WINS - P2_ORIENTATION_WINS : P2_ORIENTATION_WINS - P1_ORIENTATION_WINS ))
  if [ "$P1_ORIENTATION_WINS" -eq 0 ] && [ "$P2_ORIENTATION_WINS" -eq 0 ]; then
    log_ok "mirror: all $TIE_COUNT games tied — no side-asymmetry"
  elif [ "$DIFF" -ge 3 ]; then
    log_fail "mirror: side-asymmetry detected — P1-side wins $P1_ORIENTATION_WINS/3, P2-side wins $P2_ORIENTATION_WINS/3"
  else
    log_ok "mirror: balanced — P1-side $P1_ORIENTATION_WINS/3, P2-side $P2_ORIENTATION_WINS/3, ties $TIE_COUNT"
  fi
fi

# ── Step 5: profile turn times on latest real replay ───────────────────
log_step "5/5  profile-turns — per-turn compute time"
LATEST_REPLAY="$(ls -t "$REPO_ROOT"/replays/*.replay 2>/dev/null | head -1 || true)"
if [ -z "$LATEST_REPLAY" ]; then
  log_fail "No replay found to profile"
else
  PROFILE_OUT=$(python3 "$REPO_ROOT/tools/profile_turns.py" "$LATEST_REPLAY" 2>&1)
  echo "$PROFILE_OUT" | head -20
  # Extract max ms from profile output
  MAX_MS="$(echo "$PROFILE_OUT" | awk '/Max:/ {gsub(/\..*/, "", $2); print $2; exit}')"
  if [ -z "$MAX_MS" ]; then
    log_fail "profile-turns: couldn't parse output"
  elif [ "$MAX_MS" -gt 10000 ]; then
    log_fail "profile-turns: max compute time ${MAX_MS}ms > 10000ms — TIMEOUT RISK"
  elif [ "$MAX_MS" -gt 5000 ]; then
    log_fail "profile-turns: max compute time ${MAX_MS}ms > 5000ms — investigate before upload"
  else
    log_ok "profile-turns: max ${MAX_MS}ms (well under 5000ms budget)"
  fi
fi

# ── Summary ─────────────────────────────────────────────────────────────
echo
echo "$HR"
if [ "$FAILED" -eq 0 ]; then
  echo "✓  EVAL SUITE PASSED — $ALGO_NAME meets baseline criteria."
  exit 0
else
  echo "✗  EVAL SUITE FAILED — $FAILED check(s) failed."
  exit 1
fi
