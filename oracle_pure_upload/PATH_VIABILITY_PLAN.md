# M2 Plan — Path-Viability Check (the actual recommendation)

**One-line summary**: ship a single principled fix — a BFS check that
rejects any defense plan that would seal off all our spawn-to-opp paths —
plus a tactical SUPPORTS_BACK tile swap as immediate triage. **No
hardcoded archetype templates. No heuristic switches.**

This document REPLACES the unclear v3 plan + critical eval combo. Read
this one document; ignore the prior tier confusion.

---

## 1. What we're shipping in M2

Just **2 changes** to `oracle_core/enumerator.py`:

### Change A — Path-viability check at template construction (THE PRINCIPLED FIX)

After every defense template is built, run a BFS to verify the resulting
layout doesn't seal off oracle's offense. If it does, REJECT the plan.

```python
# oracle_core/enumerator.py — add this helper near the top
from collections import deque

def _is_offense_viable(game_state, plan_structure_xys):
    """BFS check: after applying plan's structure ops, can ANY mobile unit
    spawned at our edge reach opp territory (y>=14)?
    
    plan_structure_xys: set of (x, y) tiles the plan would ADD as blocked.
    
    Returns True if at least one spawn-to-opp path exists. False if not.
    """
    blocked = set(plan_structure_xys)
    # Add existing structures
    for x in range(28):
        for y in range(14):
            if not game_state.game_map.in_arena_bounds([x, y]):
                continue
            if game_state.contains_stationary_unit([x, y]):
                blocked.add((x, y))
    # BFS from our spawn-edge tiles
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
            return True
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


# Modify _build_defense_plan to call the check before returning
def _build_defense_plan(name, atoms, game_state, config, sp_budget,
                        upgrades=(), archetype="skeleton"):
    plan = ActionPlan(name=name, archetype=archetype)
    sp_used = 0.0
    placed_xys = set()
    # ... existing atom-placement loop unchanged ...
    
    if not plan.ops:
        return None
    plan.sp_cost = sp_used
    plan.mp_cost = 0.0
    
    # NEW: validate that the plan doesn't seal off our offense
    structure_tiles = {tuple(op.xy) for op in plan.ops 
                       if op.kind == KIND_SPAWN_STRUCT}
    if structure_tiles and not _is_offense_viable(game_state, structure_tiles):
        return None  # reject — plan would trap our offense
    
    return plan
```

**Why this is genuinely structural**:
- No hardcoded tile lists. The check works against any layout.
- Catches `defense:supports` (the known bug) AND any future template that
  inadvertently traps offense.
- Generalizes to a 9th, 10th, 11th template added by future code.

**Cost** (verified by benchmark, this session):
- 0.255 ms per call
- ~13 defense templates per turn × 1 check each = ~3.3 ms/turn
- Current wall-clock: 394 ms/turn average
- New wall-clock: ~398 ms/turn (1.1% overhead)
- 11s SIGALRM watchdog ceiling: 96% headroom remains

**Sims/turn impact**: ZERO. The check runs at template *construction*
time, BEFORE any candidate enters the sim_rs evaluation pipeline. Number
of sims evaluated per turn is unchanged.

### Change B — SUPPORTS_BACK fix to ALT-OUTSIDE (immediate triage)

The path-viability check (Change A) would correctly REJECT the current
buggy `SUPPORTS_BACK` template entirely (because all permutations lead to
sealed gaps). To preserve the support-amplification capability, we ALSO
swap the buggy tiles for safe ones:

```python
# oracle_core/enumerator.py — replace existing SUPPORTS_BACK constant
# OLD (BUGGY): supports at (12,11) and (15,11) seal gaps at (12,12) and (15,12)
# NEW (ALT-OUTSIDE): same shield strength, no trap
SUPPORTS_BACK = [(SUPPORT_IDX, 10, 11), (SUPPORT_IDX, 17, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
```

**Verified geometric properties** (via gamelib BFS, this session):
- Total upgraded shield: 33.4 (same as buggy original; vs 30.6 for the
  earlier proposed (13,9)/(14,9) variant)
- Gaps (12,12) and (15,12) remain reachable from spawn tiles
- Mobiles can reach opp territory from any launcher

Without Change B, Change A alone would reject `defense:supports` and
oracle would never get any support-amplification. With Change B,
`defense:supports` becomes a viable plan that the search can pick when
beneficial.

---

## 2. Why this is the right plan (vs alternatives)

| Approach | Hardcoded? | Generalizes? | Cost | Verdict |
|---|---|---|---|---|
| **Change A (path-check) + Change B (ALT-OUTSIDE)** | B is hardcoded; A is principled | A: yes; B: no | 4.5 ms/turn | **THIS PLAN** |
| Change A alone (no SUPPORTS_BACK fix) | No | YES | 4.5 ms/turn | Loses support functionality entirely |
| Change B alone (no path check) | YES | NO | 0 | Just band-aid; future bugs slip through |
| Stuck-mobile penalty in value function | No | YES | minimal | Reactive; misses delayed-consequence traps |
| Remove `defense:supports` template | YES | YES | 0 | Loses functionality permanently |

The combination A + B is best because:
- **A is the principled, generalizing fix** — it's the structural improvement that makes the algo genuinely better
- **B preserves the support functionality** — without B, A would correctly reject all support templates and we'd lose offensive amplification
- Together: bug fixed AND functionality preserved AND future similar bugs prevented

---

## 3. Compute analysis (your specific question)

### Current baseline (measured this session, fresh M1Lite vs heuristic_v1)

| Metric | Value | % of 11s budget |
|---|---|---|
| Avg wall-clock/turn | 394 ms | 3.6% |
| Max wall-clock/turn | 810 ms | 7.4% |
| P50 | 430 ms | 3.9% |
| P90 | 590 ms | 5.4% |
| P99 | 810 ms | 7.4% |
| Avg sims/turn | 1604 | — |
| Max sims/turn | 2686 | — |

**We're using ~4-7% of the 11s budget.** Massive headroom.

### Path-check overhead (measured this session)

| Scenario | ms/call |
|---|---|
| Empty plan (no extra structures) | 0.259 |
| Plan with 3 extra structures | 0.259 |
| Trap plan (BFS fails fast) | 0.255 |
| Full template with 10 extras | 0.251 |

The check is essentially constant-time at this board size (28×28 grid; ~50
blocked tiles in mid-game).

### Per-turn projection

Calling the check once per defense template:

| Templates checked | Total cost | New wall-clock | New % of budget |
|---|---|---|---|
| 13 (defenses only) | 3.3 ms | ~397 ms | 3.6% |
| 18 (defenses + 5 patches) | 4.5 ms | ~399 ms | 3.6% |
| Worst case: 1500 (every plan) | 376 ms | ~770 ms | 7.0% |

**Even the absolute worst case (running the check on every one of the
1500 plans, ignoring caching) leaves 93% of the budget free.** The
realistic case (1.1% overhead) is undetectable.

### Sims/turn impact

**Zero.** The path check runs at template *construction* time (in
`_build_defense_plan`), BEFORE plans are added to the candidate list that
gets passed to `sim_rs`. Number of sims is determined by the search loop
(`phase1`, `phase2`, `depth2`), which doesn't see the path check at all.

If anything, the sim count could INCREASE slightly: rejecting
trap-forming templates means the search has FEWER bad candidates to
evaluate, so it can finish phase-2 faster and (with adaptive budget, a
future fix) explore more depth. But that's a downstream benefit, not a
guarantee.

---

## 4. Validation gates (must pass before shipping M2)

### Gate 1: Component tests
- `python3 algos/oracle_pure/tests/test_components.py` — all pass
- New: `tests/test_path_viability.py`:
  - Construct a state where the standard `defense:t0_full` is built
    (anchor + walls + outer + diag); assert `_is_offense_viable()`
    returns True (it should — the standard skeleton is path-correct)
  - Construct the trap state (anchor + walls + supports at
    (12,11)/(15,11)); assert `_is_offense_viable()` returns False
  - Construct the ALT-OUTSIDE state (supports at (10,11)/(17,11) etc.);
    assert returns True
  - Edge case: empty board → spawn tiles immediately reach y >= 14 from
    the spawn edge tiles via... wait, spawn tiles ARE at y=0 and
    diagonals. Path to y=14 with no blocks: should return True.

### Gate 2: Trap-replay reproduction
- Load the suchir-g loss state at T49 (when the trap formed)
- Apply path-check to all candidate defense plans
- Assert: the previously-picked `defense:supports` (with old constants)
  would now be REJECTED
- Assert: at least one viable defense plan remains (we don't completely
  paralyze the enumerator)

### Gate 3: Tier A regression floor (10 matches, both sides)
Same as the standard milestone validation suite:
- v13_second_ring P1 + P2
- python-algo (starter) P1 + P2
- heuristic_v1 P1 + P2
- Lostkids/python-2l-aet P1 + P2
- funnel_INTER P1 + P2

**ALL 10 must win.** Any regression = REJECT M2.

### Gate 4: Tier B (snorkeldink-v3-1)
M1Lite achieves 2/2 vs snorkeldink. M2 must NOT drop below this.
If 0/2 or 1/2: REJECT (we lost the M1Lite breakthrough).

### Gate 5: Telemetry sanity
- Average wall-clock/turn: must be ≤ 1.5× M1Lite baseline (~600ms cap;
  realistic: still under 500ms)
- Max wall-clock/turn: must be < 2s (well under watchdog)
- Sims/turn: must NOT decrease (we want the check to be free)
- No `WATCHDOG fired` events, no exceptions

If any sims/turn drops: investigate (might mean check is more expensive
than projected).

---

## 5. Upload + commit + live testing

### Local validation (before any commit)

1. Implement Change A + Change B in `oracle_core/enumerator.py`
2. Add `tests/test_path_viability.py` per Gate 1 spec
3. Run all 5 gates
4. If ALL pass: proceed. If ANY fail: REJECT, document, do not ship.

### Upload folder

If gates pass:
```bash
ROOT=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
rm -rf $ROOT/oracle_pure_M2_upload
mkdir -p $ROOT/oracle_pure_M2_upload
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
# Use the OLD prior (M1Lite uses OLD; G3-Additive is a separate later milestone)
cp $ROOT/algos/oracle_pure/data/opp_model_priors.OLD.json \
   $ROOT/oracle_pure_M2_upload/data/opp_model_priors.json
rm -f $ROOT/oracle_pure_M2_upload/data/opp_model_priors.M1_breach_context.json
rm -f $ROOT/oracle_pure_M2_upload/data/opp_model_priors.OLD.json
```

### Path-portability test

```bash
rm -rf /tmp/oracle_pure_M2_test
cp -R $ROOT/oracle_pure_M2_upload /tmp/oracle_pure_M2_test
python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py \
  /tmp/oracle_pure_M2_test \
  $ROOT/algos/v13_second_ring 2>&1 | tail -10
# Expect: Winner=1, no FailedToLoad
```

### Commit

```bash
git checkout -b oracle-pure-M2
git add oracle_core/enumerator.py
git add tests/test_path_viability.py
git add algos/oracle_pure/PATH_VIABILITY_PLAN.md
git add oracle_pure_M2_upload/
git commit -m "M2: path-viability check + ALT-OUTSIDE SUPPORTS_BACK"
```

Don't merge to main until live validation passes.

### Live ladder validation (manual — user uploads)

User uploads `oracle_pure_M2_upload/` to terminal.c1games.com. Wait for
≥10 ranked matches.

**Success criteria**:
- ELO not regressing >40 vs M1Lite baseline
- No matches that were previously won are now lost (strict superset rule)
- Bonus: matches against `suchir-g/python-algo-baseline` and
  `not-tnb/python-algo-tnb` (the user-flagged 100-turn losses) now end
  in oracle wins, OR at minimum, oracle's mobiles are no longer
  self-destructing in own territory

If success: merge `oracle-pure-M2` to main.
If failure: rollback. Document in `MILESTONE_M2_RESULTS.md`.

---

## 6. What's NOT in this plan (deferred)

The following are intentionally NOT shipped in M2:

| Item | Reason for deferral |
|---|---|
| T1 (allow refund of supports) | M2's path check makes trap formation impossible; T1's escape valve is no longer urgent. Worth doing later. |
| T2 (stuck-mobile penalty) | Same — path check prevents the trap from forming, so post-hoc detection is less critical. Worth doing later for general robustness. |
| T3 (filter LAUNCHERS_FAR by reachability) | Different bug class (corner launchers). Worth a separate small fix later. |
| T4 (G3-Additive prior rebuild) | Independent change; affects opp model not enumerator. Separate milestone. |
| T5/T6 (G2/G6 sampling) | Independent changes. Separate milestones. |
| Hardcoded "anti-funnel" defense templates | The 3 unsolved counter-patterns (aa0/funnel-a, ashmit/funnel-crush-before, Egil/siege) are a different problem entirely. Need a separate plan. |

The discipline here: **M2 ships ONE coherent change** (the path-check + the SUPPORTS_BACK swap that supports it). Don't try to fix everything in one milestone.

---

## 7. Risks and mitigations

### Risk 1: Path check rejects a template that's actually valuable
**Mitigation**: Gate 1's component test verifies that `defense:t0_full`
(the standard winning skeleton) is NOT rejected. Gate 3's local ladder
catches any hidden valuable template that gets killed.

### Risk 2: ALT-OUTSIDE supports at (10,11)/(17,11) are more exposed to opp scout rushes
- (10,11) and (17,11) are slightly outside the protective core (anchors at 11,11/16,11 are adjacent)
- Opp scouts could pick them off
- **Mitigation**: sim_rs evaluation will reveal this — if the supports die fast, the value function down-weights `defense:supports` naturally. The search adapts.
- **Empirical check**: in Gate 3 matches, log how often `defense:supports` is picked. Compare to M1Lite baseline. If much less often (because supports die), the template is now less useful but no worse than M1Lite.

### Risk 3: Path check has a bug (e.g., mishandles edge case)
**Mitigation**: Gate 1's tests cover edge cases. Also: even if the check has a bug that occasionally rejects valid plans, the ENUMERATOR returns None for that plan and the search just considers other options. There's always at least `defense:none` available.

### Risk 4: BFS performance degrades on more complex states
- I benchmarked at 0.255ms for ~50 blocked tiles. With 100 blocked tiles, BFS would still be sub-millisecond on a 28×28 grid.
- Even pessimistic 10× slowdown (2.5ms/call × 18 templates = 45ms/turn) is still 0.4% of budget.

---

## 8. Honest expectations

| Outcome | Probability | Reasoning |
|---|---|---|
| M2 passes all 5 gates | HIGH | Path check is well-defined; ALT-OUTSIDE is verified geometrically |
| M2 ships and ELO improves +20-50 | MEDIUM | Eliminates the 100-turn HP-tiebreak loss class (suchir-g, not-tnb pattern), but only if those losses recur on the live ladder |
| M2 ships and ELO unchanged | MEDIUM | If suchir-g/not-tnb were rare bugs not pattern-matched on the live ladder, the fix may have small impact |
| M2 regresses (some Tier A flip) | LOW | The path check is conservative (only rejects truly trap-forming plans); SUPPORTS_BACK ALT-OUTSIDE has same shield strength |
| Live ELO drops >40 | VERY LOW | Would require the path check to reject many useful templates we don't anticipate |

---

## 9. Summary in one paragraph

**M2 is two changes to `oracle_core/enumerator.py`**: (A) add a 30-line
BFS-based `_is_offense_viable()` check that rejects defense templates
which seal off all spawn-to-opp paths, and (B) swap the buggy
`SUPPORTS_BACK = [(12,11),(15,11),(13,10),(14,10)]` for `[(10,11),(17,11),(13,10),(14,10)]`
which has the same shield strength but doesn't trap our offense. Change
A is the principled fix that catches this bug class generally; Change B
preserves the support-amplification capability that change A would
otherwise eliminate. The path check costs 0.255 ms/call, ~4.5 ms/turn
total, vs current 394 ms/turn average — 1.1% overhead, negligible.
Sims/turn unaffected. Validates against 5 gates including the local
10/10 ladder + snorkeldink 2/2 + a new test for the trap-loss
reproduction. Ships as `oracle_pure_M2_upload/` on branch
`oracle-pure-M2`. If live ladder validates, merge to main.
