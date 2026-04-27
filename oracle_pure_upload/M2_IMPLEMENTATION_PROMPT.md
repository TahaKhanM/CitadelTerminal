# M2 Implementation Prompt — Path-Viability Check + ALT-OUTSIDE Supports

You are implementing **M2** for `oracle_pure_M1Lite`. Your work has TWO
concrete code changes, ONE new test file, FIVE validation gates, and a
final upload-folder creation step. **Read this entire prompt before
starting.**

---

## Context (read first)

The base is `oracle_pure_M1Lite` (G7 fast_copy_state shipped, currently
live on the ladder). Two losses to lower-ranked opponents
(`suchir-g/python-algo-baseline` and `not-tnb/python-algo-tnb`, both
100-turn HP-tiebreak losses) revealed a self-induced offense-block bug:
when the search picks `defense:supports`, supports are placed at (12,11)
and (15,11) — directly adjacent to the wall row's launch gaps at
(12,12) and (15,12). Combined with anchor turrets at (11,11)/(13,11)/
(14,11)/(16,11), oracle's mobiles cannot reach the gaps from below and
self-destruct in their own territory.

Two changes ship together:
1. **Change A (principled)**: a path-viability BFS check at template
   construction time that rejects defense plans which would seal off
   all spawn-to-opp paths. Generalizes — catches THIS bug AND any
   future similar bug.
2. **Change B (triage)**: swap the buggy SUPPORTS_BACK to ALT-OUTSIDE
   `[(10,11),(17,11),(13,10),(14,10)]` — same upgraded-shield strength
   (33.4) but no trap. Without B, A would correctly reject ALL support
   templates and we'd lose offensive amplification.

### Mandatory reading before writing any code

1. `algos/oracle_pure/PATH_VIABILITY_PLAN.md` — the FULL plan with
   verified compute analysis (path check costs 0.255 ms/call, ~4.5 ms/turn
   total; 1.1% overhead vs current 394 ms/turn baseline; sims/turn
   unchanged because check runs at template construction)
2. `algos/oracle_pure/CRITICAL_EVAL_v3.md` — why the path check is
   structural; why ALT-OUTSIDE is the right tile choice
3. `algos/oracle_pure/oracle_core/enumerator.py` — the ONE file you'll
   modify; understand `_build_defense_plan`, `SUPPORTS_BACK`, the
   `_enumerate_defense_templates` flow
4. `algos/oracle_pure/MILESTONE_PROMPTS.md` SHARED VALIDATION TEST
   SUITE section — the Tier A/B/C/D framework

### Hard rules (do not violate)

- **Do not modify ANY file other than `oracle_core/enumerator.py` and
  `tests/test_path_viability.py`** (new file). Specifically: do NOT
  modify `algo_strategy.py`, `search.py`, `value.py`, `state_adapter.py`,
  or any data file.
- **Do not zip the upload folder.** Create the folder; the user uploads
  the folder via the competition site UI.
- **Do not merge to main until live ladder validation completes.** Stay
  on branch `oracle-pure-M2`.
- **If ANY validation gate fails, REJECT the milestone:** do not create
  the upload folder, do not commit; document failure in
  `MILESTONE_M2_RESULTS.md` and stop.
- **Use ABSOLUTE paths in run_match.py invocations.** Relative paths
  cause silent FailedToLoad bugs (per CLAUDE.md / prior incident).

---

## Implementation step 1 — Change A: add the path-viability check

In `algos/oracle_pure/oracle_core/enumerator.py`:

### Step 1a — add the helper function

Locate the existing helper `_is_legal_struct_tile` (around line 130-150).
Add `_is_offense_viable` AS A NEW HELPER (do not modify
`_is_legal_struct_tile`):

```python
from collections import deque  # add to imports if not present


def _is_offense_viable(game_state, plan_structure_xys):
    """Returns True iff after applying plan's structure ops, at least one
    spawn-edge tile has a path to opp territory (y >= 14).

    plan_structure_xys: iterable of (x, y) tiles the plan would ADD as
        blocked (i.e., new structures it places). Existing on-board
        structures are derived from game_state.

    BFS on a 28x28 grid; cost ~0.25 ms per call (verified by benchmark).
    Called once per defense template at construction time, NOT per
    candidate plan — sims/turn impact is zero.
    """
    blocked = set(plan_structure_xys)
    # Add existing on-board structures (any stationary unit blocks paths)
    for x in range(28):
        for y in range(14):  # only oracle's territory matters for the check
            if not game_state.game_map.in_arena_bounds([x, y]):
                continue
            if game_state.contains_stationary_unit([x, y]):
                blocked.add((x, y))

    # BFS from spawn tiles toward opp territory
    spawn_tiles = ([(x, 13 - x) for x in range(14)] +
                   [(x, x - 14) for x in range(14, 28)])
    spawn_tiles = [t for t in spawn_tiles
                   if game_state.game_map.in_arena_bounds(list(t))
                   and t not in blocked]
    visited = set(spawn_tiles)
    q = deque(spawn_tiles)
    while q:
        x, y = q.popleft()
        if y >= 14:
            return True  # reached opp territory
        for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
            nx, ny = x + dx, y + dy
            if not game_state.game_map.in_arena_bounds([nx, ny]):
                continue
            if (nx, ny) in blocked:
                continue
            if (nx, ny) in visited:
                continue
            visited.add((nx, ny))
            q.append((nx, ny))
    return False
```

### Step 1b — gate the defense-plan return on the check

Locate `_build_defense_plan(...)`. Find where it returns the populated
`plan` (the line `return plan` near the end of the function, AFTER
`plan.sp_cost = sp_used; plan.mp_cost = 0.0`).

Replace the trailing `return plan` block with:

```python
    if not plan.ops:
        return None
    plan.sp_cost = sp_used
    plan.mp_cost = 0.0

    # Reject plans that would seal off all spawn-to-opp paths (M2 fix)
    structure_tiles = {tuple(op.xy) for op in plan.ops
                       if op.kind == KIND_SPAWN_STRUCT}
    if structure_tiles and not _is_offense_viable(game_state, structure_tiles):
        return None  # plan would trap our offense — reject

    return plan
```

(The `not plan.ops → return None` and `plan.sp_cost / mp_cost = ...`
lines should already exist; adapt to the file's actual structure. The
NEW addition is the `# Reject plans...` block + the conditional return
None.)

**Verify**: import `KIND_SPAWN_STRUCT` is already imported at the top of
the file (it should be — `_build_defense_plan` already uses it). Add
`from collections import deque` if not present.

---

## Implementation step 2 — Change B: swap SUPPORTS_BACK

Locate the `SUPPORTS_BACK` constant (around line 86-87 of
`enumerator.py`):

```python
# REPLACE THIS:
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]

# WITH THIS:
# ALT-OUTSIDE: same upgraded-shield strength (33.4) but doesn't block
# the cells adjacent to wall-row gaps at (12,12) and (15,12).
# See PATH_VIABILITY_PLAN.md and CRITICAL_EVAL_v3.md for rationale.
SUPPORTS_BACK = [(SUPPORT_IDX, 10, 11), (SUPPORT_IDX, 17, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
```

That's the only constant to change. Do NOT change other tile constants
(ANCHOR_TURRETS, OUTER_TURRETS, etc.).

---

## Implementation step 3 — write the new test

Create `algos/oracle_pure/tests/test_path_viability.py` with these
assertions:

```python
"""M2 fix validation: _is_offense_viable() correctness."""
import json
import sys
from pathlib import Path

ORACLE_PURE = Path(__file__).resolve().parent.parent
if str(ORACLE_PURE) not in sys.path:
    sys.path.insert(0, str(ORACLE_PURE))

import gamelib  # noqa: E402

from oracle_core.enumerator import _is_offense_viable  # noqa: E402


def _load_config():
    with (ORACLE_PURE / "data" / "citadel_config_snapshot.json").open() as f:
        return json.load(f)


def _make_state(p1_walls=(), p1_supports=(), p1_turrets=()):
    """Construct a GameState with the given P1 structures.
    Each list contains [x, y, hp, unit_id] entries."""
    config = _load_config()
    p1u = [list(p1_walls), list(p1_supports), list(p1_turrets),
           [], [], [], [], []]
    p2u = [[], [], [], [], [], [], [], []]
    ts = json.dumps({
        "turnInfo": [0, 5, -1, 0],
        "p1Stats": [40, 8, 1, 0],
        "p2Stats": [40, 8, 1, 0],
        "p1Units": p1u, "p2Units": p2u,
        "events": {"spawn": [], "breach": [], "damage": [], "death": [],
                   "attack": [], "shield": [], "move": [], "selfDestruct": [],
                   "melee": []},
    })
    gs = gamelib.GameState(config, ts)
    gs.suppress_warnings(True)
    return gs


def test_empty_board_is_viable():
    """No structures: every spawn tile reaches opp territory trivially."""
    gs = _make_state()
    assert _is_offense_viable(gs, set())


def test_standard_skeleton_is_viable():
    """Full t0_full skeleton (anchors + walls + corners + outers + diags)
    must remain viable — this is oracle's winning defense."""
    walls = [[x, 12, 60.0, str(100 + i)]
             for i, x in enumerate(range(2, 26)) if x not in (12, 15)]
    edge_walls = [[2, 13, 60.0, "200"], [25, 13, 60.0, "201"],
                  [4, 13, 60.0, "202"], [23, 13, 60.0, "203"]]
    turrets = [[x, y, 100.0, str(300 + i)] for i, (x, y) in enumerate([
        (11, 11), (13, 11), (14, 11), (16, 11),  # anchor
        (5, 11), (22, 11), (8, 11), (19, 11),     # outer
        (4, 11), (23, 11),                         # diag
        (1, 13), (26, 13), (0, 13), (27, 13),     # corners
        (7, 9), (20, 9),                           # sidelane
        (11, 5), (16, 5),                          # second ring
    ])]
    gs = _make_state(p1_walls=walls + edge_walls, p1_turrets=turrets)
    assert _is_offense_viable(gs, set()), "standard skeleton must remain viable"


def test_buggy_supports_at_gap_adjacent_is_NOT_viable():
    """Original buggy SUPPORTS_BACK with supports at (12,11)/(15,11)
    on top of the standard skeleton MUST be rejected (this is THE bug)."""
    walls = [[x, 12, 60.0, str(100 + i)]
             for i, x in enumerate(range(2, 26)) if x not in (12, 15)]
    edge_walls = [[2, 13, 60.0, "200"], [25, 13, 60.0, "201"],
                  [4, 13, 60.0, "202"], [23, 13, 60.0, "203"]]
    anchors = [[x, 11, 100.0, str(300 + i)]
               for i, x in enumerate([11, 13, 14, 16])]
    gs = _make_state(p1_walls=walls + edge_walls, p1_turrets=anchors)
    # The plan would ADD supports at (12,11) and (15,11)
    plan_tiles = {(12, 11), (15, 11), (13, 10), (14, 10)}
    assert not _is_offense_viable(gs, plan_tiles), \
        "buggy supports must be rejected — they seal the gaps"


def test_alt_outside_supports_IS_viable():
    """ALT-OUTSIDE supports at (10,11)/(17,11) on top of skeleton MUST
    be accepted (same shield strength, no trap)."""
    walls = [[x, 12, 60.0, str(100 + i)]
             for i, x in enumerate(range(2, 26)) if x not in (12, 15)]
    edge_walls = [[2, 13, 60.0, "200"], [25, 13, 60.0, "201"],
                  [4, 13, 60.0, "202"], [23, 13, 60.0, "203"]]
    anchors = [[x, 11, 100.0, str(300 + i)]
               for i, x in enumerate([11, 13, 14, 16])]
    gs = _make_state(p1_walls=walls + edge_walls, p1_turrets=anchors)
    # The plan would ADD supports at (10,11)/(17,11)/(13,10)/(14,10)
    plan_tiles = {(10, 11), (17, 11), (13, 10), (14, 10)}
    assert _is_offense_viable(gs, plan_tiles), \
        "ALT-OUTSIDE supports must remain viable"


def test_compute_overhead_is_negligible():
    """Path check must run in < 2 ms per call on a realistic mid-game
    state (sanity guard against unexpected slowdown)."""
    import time
    walls = [[x, 12, 60.0, str(100 + i)]
             for i, x in enumerate(range(2, 26)) if x not in (12, 15)]
    edge_walls = [[2, 13, 60.0, "200"], [25, 13, 60.0, "201"],
                  [4, 13, 60.0, "202"], [23, 13, 60.0, "203"]]
    turrets = [[x, y, 100.0, str(300 + i)] for i, (x, y) in enumerate([
        (11, 11), (13, 11), (14, 11), (16, 11),
        (5, 11), (22, 11), (8, 11), (19, 11),
        (4, 11), (23, 11), (1, 13), (26, 13),
        (0, 13), (27, 13), (7, 9), (20, 9),
        (11, 5), (16, 5),
    ])]
    gs = _make_state(p1_walls=walls + edge_walls, p1_turrets=turrets)
    N = 100
    t0 = time.perf_counter()
    for _ in range(N):
        _is_offense_viable(gs, {(10, 11), (17, 11), (13, 10), (14, 10)})
    elapsed = (time.perf_counter() - t0) / N * 1000  # ms
    assert elapsed < 2.0, f"path check too slow: {elapsed:.3f} ms/call"
    print(f"  path check timing: {elapsed:.3f} ms/call (target < 2.0)")


def main():
    test_empty_board_is_viable()
    print("PASS test_empty_board_is_viable")
    test_standard_skeleton_is_viable()
    print("PASS test_standard_skeleton_is_viable")
    test_buggy_supports_at_gap_adjacent_is_NOT_viable()
    print("PASS test_buggy_supports_at_gap_adjacent_is_NOT_viable")
    test_alt_outside_supports_IS_viable()
    print("PASS test_alt_outside_supports_IS_viable")
    test_compute_overhead_is_negligible()
    print("PASS test_compute_overhead_is_negligible")
    print("\nALL M2 PATH-VIABILITY TESTS PASSED")


if __name__ == "__main__":
    main()
```

Run the test:
```bash
python3 algos/oracle_pure/tests/test_path_viability.py
```

All 5 tests must pass. If any fail, STOP and investigate before
proceeding to validation gates.

---

## Validation gates (must ALL pass before creating upload folder)

### Gate 1 — component tests (existing)

```bash
python3 algos/oracle_pure/tests/test_components.py
```

All 8 existing tests must pass. The path check is conservative — it only
rejects plans that would seal off ALL spawn-to-opp paths. Existing
defense templates that don't seal paths must still produce valid plans.

If any existing test fails, the path check is too aggressive — diagnose
and fix before proceeding.

### Gate 2 — new path-viability tests (this milestone)

```bash
python3 algos/oracle_pure/tests/test_path_viability.py
```

All 5 new tests must pass.

### Gate 3 — Tier A regression floor (10 matches)

Run all 10 matches with ABSOLUTE paths. The script:

```bash
ROOT=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
HV1=$ROOT/algos/heuristic_v1
FUNNEL=$ROOT/research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER
LOSTKIDS=$ROOT/research/finalist_repos/Terminal-Lostkids/python-2l-aet
V13=$ROOT/algos/v13_second_ring
PYAL=$ROOT/C1GamesStarterKit-master/python-algo

WINS=0; TOTAL=0
for OPP in "$V13" "$PYAL" "$HV1" "$LOSTKIDS" "$FUNNEL"; do
  for side in 1 2; do
    if [ "$side" = "1" ]; then A=$ROOT/algos/oracle_pure; B=$OPP; else A=$OPP; B=$ROOT/algos/oracle_pure; fi
    OUT=$(python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py "$A" "$B" 2>&1)
    W=$(echo "$OUT" | grep "Winner" | awk -F': ' '{print $2}')
    if echo "$OUT" | grep -q "FailedToLoad"; then echo "FAILED TO LOAD!"; exit 1; fi
    NAME=$(basename "$OPP")
    if [ "$side" = "1" ]; then RES=$([ "$W" = "1" ] && echo W || echo L); else RES=$([ "$W" = "2" ] && echo W || echo L); fi
    if [ "$RES" = "W" ]; then WINS=$((WINS+1)); fi
    TOTAL=$((TOTAL+1))
    echo "[$RES] oracle_pure as P$side vs $NAME: Winner=$W"
  done
done
echo "=== TIER A SUMMARY: $WINS / $TOTAL ==="
```

**REQUIRED RESULT: 10 / 10 wins.** Any L = REJECT M2.

### Gate 4 — Tier B (snorkeldink-v3-1 — must NOT regress from M1Lite's 2/2)

```bash
SNORK=$ROOT/research/finalist_repos/terminal-c1/snorkeldink-v3-1
for side in 1 2; do
  if [ "$side" = "1" ]; then A=$ROOT/algos/oracle_pure; B=$SNORK; else A=$SNORK; B=$ROOT/algos/oracle_pure; fi
  OUT=$(python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py "$A" "$B" 2>&1)
  W=$(echo "$OUT" | grep "Winner" | awk -F': ' '{print $2}')
  if [ "$side" = "1" ]; then RES=$([ "$W" = "1" ] && echo W || echo L); else RES=$([ "$W" = "2" ] && echo W || echo L); fi
  echo "[$RES] oracle_pure as P$side vs snorkeldink-v3-1: Winner=$W"
done
```

**REQUIRED RESULT: 2/2 wins** (matching M1Lite). If 1/2 or 0/2: REJECT
M2 — this would mean we lost M1Lite's snorkeldink breakthrough.

### Gate 5 — telemetry sanity (parsed from a Tier A match)

Run a focused match and capture per-turn timing. Use the heuristic_v1
P1 match (from Gate 3) as the source. Parse:

```bash
ROOT=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py \
  $ROOT/algos/oracle_pure $ROOT/algos/heuristic_v1 2>&1 > /tmp/m2_timing.log

python3 << 'EOF'
import re
with open('/tmp/m2_timing.log') as f:
    content = f.read()
turns = re.findall(r'turn=(\d+).*?cands=(\d+).*?sims=(\d+).*?wall=([\d.]+)s', content)
walls = [float(t[3]) for t in turns]
sims = [int(t[2]) for t in turns]
print(f"Turns played: {len(turns)}")
print(f"Avg wall/turn: {sum(walls)/len(walls):.3f}s  (M1Lite baseline: 0.394s)")
print(f"Max wall/turn: {max(walls):.3f}s  (M1Lite baseline: 0.810s)")
print(f"Avg sims/turn: {sum(sims)/len(sims):.0f}  (M1Lite baseline: 1604)")
# Sanity gates
assert sum(walls)/len(walls) < 0.6, f"avg wall too high: {sum(walls)/len(walls):.3f}s"
assert max(walls) < 2.0, f"max wall too high: {max(walls):.3f}s"
assert sum(sims)/len(sims) > 1000, f"sims dropped too much: {sum(sims)/len(sims):.0f}"
# No watchdog or exception events
assert 'WATCHDOG fired' not in content, "watchdog triggered!"
assert 'on_turn exception' not in content, "exception thrown!"
print("Telemetry sanity: PASS")
EOF
```

**Required**:
- avg wall/turn < 0.6s (1.5× M1Lite baseline; we expect ~0.4s)
- max wall/turn < 2s (well under 11s watchdog)
- avg sims/turn > 1000 (must not drop drastically; expect ~1600)
- No `WATCHDOG fired` events
- No `on_turn exception` events

If any sanity check fails: REJECT M2.

### Gate 6 — verify Change B took effect (smoke check)

Quick sanity:
```bash
grep "SUPPORTS_BACK = " /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/oracle_core/enumerator.py
```
Should show the new ALT-OUTSIDE values: `(SUPPORT_IDX, 10, 11), (SUPPORT_IDX, 17, 11), (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)`.

```bash
grep "_is_offense_viable" /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/oracle_core/enumerator.py | wc -l
```
Should show ≥3 occurrences (definition + use + import).

---

## Upload folder creation (ONLY if all 6 gates passed)

If ANY gate failed, STOP. Document failure in
`MILESTONE_M2_RESULTS.md` and do not create the upload folder.

If ALL gates passed:

```bash
ROOT=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal

# Create upload folder
rm -rf $ROOT/oracle_pure_M2_upload
mkdir -p $ROOT/oracle_pure_M2_upload

# Copy required files (NOT the test files, NOT the prior backups)
cp -R $ROOT/algos/oracle_pure/oracle_core $ROOT/oracle_pure_M2_upload/
cp -R $ROOT/algos/oracle_pure/bundled_sim_rs $ROOT/oracle_pure_M2_upload/
cp -R $ROOT/algos/oracle_pure/vendored_sim $ROOT/oracle_pure_M2_upload/
cp -R $ROOT/algos/oracle_pure/gamelib $ROOT/oracle_pure_M2_upload/
cp -R $ROOT/algos/oracle_pure/data $ROOT/oracle_pure_M2_upload/
cp $ROOT/algos/oracle_pure/algo_strategy.py $ROOT/oracle_pure_M2_upload/
cp $ROOT/algos/oracle_pure/algo.json $ROOT/oracle_pure_M2_upload/
cp $ROOT/algos/oracle_pure/run.sh $ROOT/oracle_pure_M2_upload/
cp $ROOT/algos/oracle_pure/sim_bridge.py $ROOT/oracle_pure_M2_upload/ 2>/dev/null
chmod +x $ROOT/oracle_pure_M2_upload/run.sh

# Use M1Lite's prior (the OLD content). M1 G3-rebuild rejected; do not ship.
cp $ROOT/algos/oracle_pure/data/opp_model_priors.OLD.json \
   $ROOT/oracle_pure_M2_upload/data/opp_model_priors.json

# Remove backup priors so they don't get shipped
rm -f $ROOT/oracle_pure_M2_upload/data/opp_model_priors.M1_breach_context.json
rm -f $ROOT/oracle_pure_M2_upload/data/opp_model_priors.OLD.json

ls -la $ROOT/oracle_pure_M2_upload/
ls -la $ROOT/oracle_pure_M2_upload/data/
```

### Path-portability test

```bash
rm -rf /tmp/oracle_pure_M2_test
cp -R $ROOT/oracle_pure_M2_upload /tmp/oracle_pure_M2_test
python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py \
  /tmp/oracle_pure_M2_test \
  $ROOT/algos/v13_second_ring 2>&1 | tail -10
```

**Required**: `Winner (p1 perspective): 1` and NO `FailedToLoad` in
output. If FailedToLoad appears, the upload folder is incomplete —
diagnose and fix before commit.

---

## Commit + branch

ONLY after all gates pass and path-portability test confirms:

```bash
cd $ROOT
git checkout -b oracle-pure-M2
git add algos/oracle_pure/oracle_core/enumerator.py
git add algos/oracle_pure/tests/test_path_viability.py
git add oracle_pure_M2_upload/
git add algos/oracle_pure/MILESTONE_M2_RESULTS.md  # written below
git commit -m "$(cat <<'EOF'
M2: path-viability check + ALT-OUTSIDE SUPPORTS_BACK

Two changes to oracle_core/enumerator.py:
  A. _is_offense_viable() BFS check at template construction time —
     rejects plans that would seal off all spawn-to-opp paths
  B. SUPPORTS_BACK swapped to ALT-OUTSIDE [(10,11),(17,11),(13,10),(14,10)]
     — same upgraded shield strength as buggy original (33.4) but
     doesn't seal the gap-adjacent tiles (12,11) / (15,11)

Together: A is the principled generalizing fix; B preserves the
support-amplification capability (without B, A would correctly reject
all support templates).

Validation:
  - 5 new path-viability tests pass
  - 8 existing component tests pass
  - Tier A 10/10 vs (v13, python-algo, heuristic_v1, Lostkids/python-2l-aet,
    funnel_INTER) both sides
  - Tier B snorkeldink-v3-1 2/2 (M1Lite breakthrough preserved)
  - Telemetry: avg wall ~0.4s/turn (vs M1Lite 0.394s baseline), no
    watchdog/exception events
  - Path check overhead: ~5 ms/turn (1.1% of 11s budget)

Awaiting live ladder validation before merge to main.

Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>
EOF
)"
```

DO NOT MERGE TO MAIN. The branch sits unmerged until live validation
confirms.

---

## Write `MILESTONE_M2_RESULTS.md`

After commit, write `algos/oracle_pure/MILESTONE_M2_RESULTS.md` with:

```markdown
# Milestone M2 — Results

**Status**: SHIPPED to branch `oracle-pure-M2`, awaiting live ladder
validation.

## Validation gate results

### Gate 1 — Component tests
[paste output from `python3 algos/oracle_pure/tests/test_components.py`]

### Gate 2 — Path-viability tests
[paste output from `python3 algos/oracle_pure/tests/test_path_viability.py`]

### Gate 3 — Tier A regression floor
[paste the `[W] oracle_pure as P{1,2} vs {opp}: Winner=X` lines and the
SUMMARY: line]

### Gate 4 — Tier B snorkeldink-v3-1
[paste the 2 result lines]

### Gate 5 — Telemetry
[paste the timing summary + sanity assertions]

### Gate 6 — Code-change verification
[paste grep outputs]

## Upload folder

Path: `oracle_pure_M2_upload/`
Path-portability test: [paste result]

## Summary

[1-paragraph summary of what landed and what to expect from live
testing]

## Live testing instructions for the user

1. Upload `oracle_pure_M2_upload/` (the FOLDER, not zipped) to
   terminal.c1games.com
2. Wait for ≥10 ranked matches
3. Check ELO trajectory:
   - If ELO stays ≥ baseline (M1Lite ~2103) and no new losses appear
     against opps M1Lite previously beat → SUCCESS, merge
     `oracle-pure-M2` to main
   - If ELO drops >40 or M1Lite-beaten opps now lose → REJECT
     retroactively, fall back to M1Lite live algo
4. Specifically watch for matches against suchir-g/python-algo-baseline
   and not-tnb/python-algo-tnb (the user-flagged trap losses) — should
   now finish without 100-turn HP-tiebreak
```

---

## Failure protocol (if any gate fails)

1. Do NOT create the upload folder.
2. Do NOT commit.
3. Write `algos/oracle_pure/MILESTONE_M2_RESULTS.md` documenting:
   - Which specific gate failed (1, 2, 3, 4, 5, or 6)
   - The exact error/loss
   - Which match flipped (if Tier A regression)
   - Hypothesis about the cause (path check too aggressive? template
     interaction? etc.)
4. Stop. Report back to user with the failure analysis.

The user will decide: investigate further, or revert and re-plan.

---

## Final output (what the user sees from your work)

- 1 modified file: `oracle_core/enumerator.py` (Change A + Change B)
- 1 new file: `tests/test_path_viability.py`
- 1 new file: `oracle_pure_M2_upload/` (folder, not zip)
- 1 new file: `algos/oracle_pure/MILESTONE_M2_RESULTS.md`
- 1 new branch: `oracle-pure-M2` (committed, not merged)
- A concise summary message stating: gates passed, upload folder ready,
  awaiting live test
