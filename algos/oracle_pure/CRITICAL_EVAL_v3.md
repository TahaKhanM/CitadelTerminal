# Critical Evaluation of IMPROVEMENT_PLAN_v3.md

**Question**: Is the v3 plan a genuine improvement to oracle_pure_M1Lite, or
is it a hardcoded patch to a specific failure mode?

**Honest answer**: T0 (the centerpiece fix) is **a HARDCODED PATCH** — the same nature as the bug it's fixing. My v3 proposal isn't even the BEST hardcoded fix. The plan should be revised to include a PRINCIPLED structural fix alongside the tactical patch.

---

## 1. The criticism applied to T0 (SUPPORTS_BACK fix)

### What the v3 plan proposed
```python
# OLD (BUGGY):
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]

# V3-PROPOSED FIX:
SUPPORTS_BACK = [(SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10),
                 (SUPPORT_IDX, 13, 9),  (SUPPORT_IDX, 14, 9)]
```

### What's wrong with this fix

**Problem 1: It's the SAME class of fix as the original bug.** The original buggy SUPPORTS_BACK was a hardcoded tile list. My v3-proposed fix is also a hardcoded tile list. I'm just replacing one set of magic constants with another.

**Problem 2: It doesn't generalize.** If someone later adds a template that places structures at, say, (8,10) or (19,11), they could create a NEW trap that the v3 plan would miss entirely.

**Problem 3: My proposed fix isn't even the BEST hardcoded fix.** Empirically verified (this session) shield/path tradeoff:

| Config | Total shield | Gaps reachable | Notes |
|---|---|---|---|
| ORIGINAL (BUGGY) | 33.4 | NO (trap) | the bug |
| V3-PROPOSED (low-y central) | **30.6** | yes | **8% less shield** than original |
| V3-FULL-Y10 (agent's first proposal) | 32.0 | yes | 4% less shield |
| **ALT-OUTSIDE** `[(10,11),(17,11),(13,10),(14,10)]` | **33.4** | yes | **SAME shield, no trap** |
| CONSERVATIVE (corners) | 33.4 | yes | Same shield, no trap, but supports far from defense |

**ALT-OUTSIDE is provably better than V3-PROPOSED**: same shield strength, same gap-reachability. If we're going to ship a hardcoded fix, it should be ALT-OUTSIDE, not V3-PROPOSED.

The fact that I went TWO ITERATIONS (the agent's first proposal → my v3-PROPOSED → realizing ALT-OUTSIDE is better) without finding the optimal hardcoded tiles is itself evidence that **hardcoded patches are fragile**. The next person could miss it again.

**Problem 4: The fix addresses ONE template only.** `defense:supports` is one of ~13 defense templates. Other templates could have similar issues (the defense:patch family places turrets near recent breaches — could those turrets block our launchers? Unverified). T0 fixes only the known instance.

### Verdict on T0

**T0 IS a hardcoded patch.** It's correct (removes a strictly-bad option from candidate space) but not principled. Shipping it improves immediate performance on the suchir-g/not-tnb loss class but doesn't address the architectural issue: **the enumerator has no automated check that a template doesn't trap our offense**.

---

## 2. The DEEPER question: WHY does the search PICK the trap-forming plan?

The trap forms when supports are placed at (12,11)/(15,11) AFTER walls exist at y=12. If sim_rs correctly simulates the action phase, the search SHOULD see that the post-state has a sealed gap.

Why doesn't it?

### Hypothesis 1: Single-turn evaluation can't see the trap
- Sim_rs simulates ONE action phase per evaluation
- In ONE turn, our scouts spawn at center launchers (13,0)/(14,0); their immediate path is up the column
- The supports being placed at (12,11)/(15,11) doesn't IMMEDIATELY hurt the in-flight scouts — those scouts are already in transit
- The HURT happens to FUTURE scouts on FUTURE turns, after walls are built and supports are present
- The value function evaluates the immediate outcome → supports look beneficial

### Hypothesis 2: depth-2 doesn't help
- Depth-2 projects 1 turn ahead with a hardcoded "scout from (14,0) if MP >= 6×scout_cost"
- This hardcoded next-turn move uses center launchers, which don't go through the gaps anyway
- Depth-2 doesn't see that a side-launcher offensive plan would fail

### Hypothesis 3: the value function has no STRUCTURAL signal
- `_structure_value_per_side` rewards placing supports at any tile (coef × hp_frac)
- Placing a support at (12,11) gets the same +10 (upgraded support coef) as placing one at (10,11)
- The value function has NO awareness that (12,11) blocks gaps

This means even the path-correct ALT-OUTSIDE fix doesn't address the root cause — the search would still pick `defense:supports` whenever its static value is high. T0 just makes that always-pickable plan less actively-bad.

---

## 3. PRINCIPLED alternatives that genuinely generalize

### Alternative A: Path-viability check at template emission time

Add a structural constraint to `_build_defense_plan`: after placing all atoms, verify the resulting layout doesn't trap our offense. If it does, REJECT the plan.

```python
def _build_defense_plan(name, atoms, game_state, config, sp_budget, ...):
    plan = ActionPlan(...)
    # ... existing logic ...
    if plan.ops:
        plan.sp_cost = sp_used
        plan.mp_cost = 0.0
        # NEW: validate path viability before returning
        if not _is_offense_viable(game_state, plan):
            return None  # reject — plan would trap our offense
        return plan
    return None

def _is_offense_viable(game_state, plan):
    """Check that this plan, applied to the current state, leaves at
    least one launcher tile with a path to opp territory."""
    blocked = _get_existing_blocked_tiles(game_state)
    for op in plan.structure_ops():
        if op.kind == KIND_SPAWN_STRUCT:
            blocked.add(op.xy)
    
    # BFS: from any of our spawn-edge tiles, can we reach y >= 14?
    from collections import deque
    spawn_tiles = [(x, 13-x) for x in range(14)] + [(x, x-14) for x in range(14, 28)]
    spawn_tiles = [t for t in spawn_tiles
                   if game_state.game_map.in_arena_bounds(list(t))
                   and t not in blocked]
    visited = set(spawn_tiles)
    q = deque(spawn_tiles)
    while q:
        x, y = q.popleft()
        if y >= 14:
            return True  # found a path
        for dx, dy in [(0,1),(0,-1),(1,0),(-1,0)]:
            nx, ny = x+dx, y+dy
            if not game_state.game_map.in_arena_bounds([nx, ny]): continue
            if (nx, ny) in blocked: continue
            if (nx, ny) in visited: continue
            visited.add((nx, ny))
            q.append((nx, ny))
    return False
```

**Why this is genuinely structural**:
- Catches ANY future template that traps offense, not just defense:supports
- No hardcoded knowledge of specific tiles
- Generalizes to a 9th template added by a future developer

**Cost**: 1 BFS per defense template per turn = ~13 × 50 = 650 BFS calls per turn. BFS on 28×28 grid is cheap (~1ms). Total overhead: ~10ms/turn. Negligible relative to the 11s budget.

**Risk**: false positives. A template that LEGITIMATELY blocks our offense (e.g., we've already lost and want to defend turtle) would be rejected. Mitigation: only reject if the plan's STRUCTURE-PLACEMENT ops would block; allow plans with no structure ops.

### Alternative B: Stuck-mobile penalty in value function (T2 from v3)

This is already in v3 plan. It's PRINCIPLED. The fix:
- After sim_rs, count our mobiles that died WITHOUT breaching, in our own territory
- Penalize the score by per-stuck-unit penalty

**Why this is principled**:
- Generalizes — any plan producing stuck mobiles is penalized regardless of root cause
- Reactive — doesn't require predicting which templates are bad
- Self-correcting — if a template is occasionally good and occasionally bad, the search picks it when good and avoids when bad

**Why it's not enough alone**:
- ONLY signals after sim_rs has already run; doesn't prevent the trap from being CONSIDERED
- If the trap forms on T20 but stuck mobiles only show on T25+ offense, the T20 plan that placed the supports doesn't get penalized
- Multi-turn delayed consequences are still not captured

### Alternative C: Remove `defense:supports` template entirely

**Why this might be better than fixing it**:
- Supports require MANY turns to be net-positive (need upgrade + need scouts to actually pass through)
- The data (replays) shows oracle wins MOST matches WITHOUT picking defense:supports
- If a template is rarely picked AND frequently buggy, removing it is safer than fixing

**Risk**:
- Loses the offensive-amplification benefit when supports DO help
- Without data on "% of wins where defense:supports was picked," can't quantify

**Test**: count, across 49 wins by oracle_pure, how many had `defense:supports` picked at any turn. If the count is small (<10%), removing the template is low-risk.

### Alternative D: Make `defense:supports` adaptive to existing wall row

Generate the support tiles DYNAMICALLY based on which wall-row tiles are placed:

```python
def _enumerate_support_template(game_state, sp_budget):
    # Find which wall-row tiles are present
    # Identify the GAPS (where walls are missing)
    # Place supports such that none are adjacent to a gap from below
    walls_y12 = set()
    for x in range(28):
        u = _existing_struct(game_state, x, 12)
        if u is not None and str(u.unit_type).upper() in ('FF', 'WALL'):
            walls_y12.add((x, 12))
    
    # Gaps are tiles in y=12 row that are NOT walls AND in arena
    gaps = [(x, 12) for x in range(2, 26)
            if (x, 12) not in walls_y12 and in_arena(x, 12)]
    
    # Avoid supports adjacent to gaps
    forbidden = set()
    for gx, gy in gaps:
        for dx in (-1, 0, 1):
            forbidden.add((gx + dx, gy - 1))  # the y=11 adjacent
    
    # Pick highest-shield safe tiles
    candidate_tiles = sorted(
        [(x, y) for x in range(28) for y in (10, 11)
         if (x, y) not in forbidden
         and game_state.game_map.in_arena_bounds([x, y])
         and not game_state.contains_stationary_unit([x, y])],
        key=lambda xy: -xy[1]  # prefer higher y for more shield
    )[:4]
    return [(SUPPORT_IDX, x, y) for x, y in candidate_tiles]
```

**Why principled**: support placement adapts to actual board state. No hardcoded tile list.

**Cost**: small computation per turn.

**Risk**: complexity. Easier to introduce subtle bugs in the dynamic logic.

### Alternative E: Multi-turn reachability check at value function

Look at the post-state. Verify that the layout still has a viable offense. If not, penalize the plan even if no immediate damage was done.

```python
# In value.py after computing other terms:
if my_player == 1:
    # Check if our launcher tiles still have paths to opp territory
    if not _viable_offense_path_exists(state_dict, my_player):
        score -= 200  # large penalty for self-trapping
```

**Why principled**: catches the trap REGARDLESS of which template caused it.

**Cost**: BFS per evaluation = expensive (multiplied by number of plans evaluated). Mitigation: only do the check on plans that PLACED structures.

---

## 4. Comparison matrix

| Approach | Hardcoded? | Generalizes? | Cost | Risk |
|---|---|---|---|---|
| **T0-V3-proposed** (low-y supports) | YES | NO | 0 | Reduces shield 8%, addresses ONE template |
| **T0-ALT-OUTSIDE** (10,11/17,11) | YES | NO | 0 | Same shield, addresses ONE template; better than V3 |
| **A: Path-viability check** | NO | YES | ~10ms/turn | Could over-reject; verify with tests |
| **B: Stuck-mobile penalty (T2)** | NO | YES | minimal | Reactive only; misses delayed-consequence traps |
| **C: Remove defense:supports** | YES | YES | 0 | Loses offensive-amplification benefit |
| **D: Adaptive support placement** | NO | YES | minimal | Code complexity |
| **E: Reachability check in value** | NO | YES | ~10ms/eval | Expensive at scale; caught by A anyway |

The honest ranking by principled-ness:
1. **A** (path-viability check at template emission) — most general; prevents ALL trap templates ever
2. **B** (stuck-mobile penalty) — also general; reactive instead of preventive; complementary to A
3. **D** (adaptive support placement) — generalizes within the supports template only
4. **C** (remove template) — simplest; loses functionality
5. **T0-ALT-OUTSIDE** — best hardcoded fix
6. **T0-V3-proposed** — sub-optimal hardcoded fix (worse than ALT-OUTSIDE)

---

## 5. The honest truth about my v3 plan

### What I did wrong

1. **I didn't critically evaluate my own fix.** The ALT-OUTSIDE alternative is provably better than my V3-PROPOSED fix. I didn't explore the design space enough before writing v3.

2. **I called T0 "structural" when it isn't.** In my v3 plan §8, I claimed:
   > "T0 (move SUPPORTS_BACK off gap-adjacent tiles): structural change — corrects a bug in the existing template"
   
   That's wrong. It's a TILE-LIST swap. The "structural" framing was rationalization.

3. **I included T2 (stuck-mobile penalty) but didn't promote it.** T2 is the more principled fix. The plan should have led with T2 + path-viability check, with T0 as a tactical triage.

4. **I missed the reachability-check approach entirely.** A single ~30-line BFS function at template emission time would catch this bug AND any future trap-forming template. That's the kind of principled fix the project explicitly prefers.

### What v3 still gets right

1. M1Lite is the correct base — head-to-head data is decisive.
2. The bug diagnosis is correct — the supports-template trap is real.
3. T2 (stuck-mobile penalty), if shipped, would help even when T0 doesn't.
4. T1 (allow support removal) is correct as an escape valve.
5. The other Tier 2 deferred work is correctly identified.

---

## 6. Revised recommendation: ship T0-ALT-OUTSIDE + Path-Viability Check, NOT v3-as-written

### New M2 milestone composition (revised from v3):

**T0' (revised)**: change SUPPORTS_BACK to ALT-OUTSIDE
```python
SUPPORTS_BACK = [(SUPPORT_IDX, 10, 11), (SUPPORT_IDX, 17, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
```
**Justification for the hardcoded patch**: it's STRICTLY BETTER than the broken original (same shield, no trap). Ship for immediate triage. But understand it's a patch.

**T0_NEW** (the structural fix): add path-viability check
- File: `oracle_core/enumerator.py:_build_defense_plan`
- Add `_is_offense_viable()` BFS check after building each defense plan
- Reject plans that would seal off all our spawn-to-opp paths
- Add `tests/test_path_viability.py` with synthetic test cases (including the trap scenario)

**T1, T2, T3** as in v3 (with refinements per the critical analysis).

### Validation rules (additions)

When testing T0_NEW (path-viability check):
- The check must NOT reject the standard `defense:t0_full` template (the validated skeleton must still pass)
- The check MUST reject a synthetic test plan that places walls + supports trapping our offense
- Telemetry: log how often the check rejects a plan; should be rare (most templates are fine)

---

## 7. Will the revised plan ACTUALLY improve M1Lite?

### High-confidence YES
- **T0-ALT-OUTSIDE**: strictly removes a bad option from candidate space; new tiles preserve shield strength.
- **T0_NEW (path-viability check)**: principled; prevents future bugs; small overhead.
- **T1 (allow support removal)**: gives search escape; can't make things worse.
- **T2 (stuck-mobile penalty)**: principled; complements T0_NEW; fast to implement.

### Medium-confidence
- **T3 (filter LAUNCHERS_FAR by reachability)**: addresses one specific bug; small upside.
- **T4 (G3-Additive)**: still recommended; subtle benefit.

### How could the revised plan REGRESS oracle?

**Risk 1**: T0_NEW (path-viability check) might be too strict and reject valid templates. Mitigation: thorough testing against the suchir-g/not-tnb loss STATES (verify `defense:supports` would now be rejected) AND against winning matches (verify standard `defense:t0_full` is still accepted).

**Risk 2**: T0-ALT-OUTSIDE supports at (10,11) and (17,11) might be MORE EXPOSED to opp scout rushes than (12,11)/(15,11). Mitigation: sim_rs evaluation will reveal this — if the supports die fast, the value function down-weights the template naturally.

**Risk 3**: T2 (stuck-mobile penalty) might over-penalize plans where mobiles die for legitimate reasons (opp turret kills are not "stuck"). Mitigation: only count post-state mobiles with `steps_taken < 5` as stuck (the criterion I already proposed).

### Net assessment

The REVISED plan (T0-ALT-OUTSIDE + T0_NEW path-check + T1 + T2 + T3) is GENUINELY better than M1Lite, with high confidence:
- Fixes the immediate known bug (T0-ALT-OUTSIDE)
- Prevents future similar bugs (T0_NEW)
- Provides escape from any unforeseen traps (T1 + T2)
- Fixes the related corner-launcher bug (T3)

It's also better than the v3 plan as written because it includes the principled fix (T0_NEW) the v3 plan was missing.

---

## 8. What I'm NOT confident about

1. **Whether the trap actually CAUSES many losses or just the 2 user-flagged ones.** The user flagged 2 specific losses; the bug pattern might be present in others (e.g., pipmy/Redemption losses where game went 79 turns without HP-tiebreak — different pattern). Need to scan all loss replays for the same self-destruct-in-own-territory signature.

2. **Whether shipping ALT-OUTSIDE + path-check might surface OTHER hidden bugs.** Path-viability check could reveal that other templates are also questionable. If multiple templates get rejected, the candidate space shrinks and the search has less to work with.

3. **Whether the deeper search/value architecture issues** (no multi-turn awareness of trap formation; value function blind to spatial constraints) need to be addressed for true robustness. The current fixes are tactical — the architecture allows similar issues to recur.

---

## 9. Final recommendation

**Replace v3's T0 with the revised T0' + T0_NEW combination.**

Update IMPROVEMENT_PLAN_v3.md → IMPROVEMENT_PLAN_v4.md with:
- T0' (ALT-OUTSIDE supports) as immediate hardcoded triage
- T0_NEW (path-viability check) as the principled structural fix
- T1, T2, T3 as in v3
- An honest section acknowledging T0' is hardcoded but T0_NEW provides the generalization

**Key honesty**: T0' alone, without T0_NEW, is a hardcoded patch. The plan is principled ONLY if T0_NEW is shipped alongside. Don't ship T0' without T0_NEW or it's just a band-aid.

The user was right to push for this critical evaluation. The v3 plan needed it.
