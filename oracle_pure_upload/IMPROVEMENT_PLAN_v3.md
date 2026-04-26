# oracle_pure — Improvement Plan v3 (M1Lite-base, post-trap-discovery)

**Date**: 2026-04-27
**Base**: oracle_pure_M1Lite (G7 fast_copy_state shipped)
**Replaces**: REVISED_IMPROVEMENT_PLAN.md (G1-G8 framework needs revision)

---

## 1. Empirical findings since the last plan

### Finding 1: M1Lite is a strict improvement over oracle_pure (CONFIRMED)

Head-to-head across 22 shared opponents:
- **18 dead-heat-perfect** (both versions win)
- **3 dead-heat-zero** (both versions lose deterministic counter-patterns: aa0/funnel-a, ashmit/funnel-crush-before, Egil/python-algo-siege — replays are byte-identical)
- **1 M1Lite IMPROVED** (ameyg/funnel-rush-v7: oracle_pure 1/2 → M1Lite 1/1; the M1Lite win replay diverges from the oracle_pure loss at T20 due to G7 timing)
- **0 regressions**

ELO is NOT the indicator (matchups differ). The shared-opp comparison is decisive.

**Verdict**: M1Lite is the new base.

### Finding 2: Self-induced offense paralysis bug (CRITICAL — newly discovered)

The user-flagged losses (m1_lite vs `suchir-g/python-algo-baseline`, `not-tnb/python-algo-tnb`) confirmed a **mechanical bug** in the enumerator that affects all "100-turn HP-tiebreak" losses:

**The bug**: `enumerator.py:86-87` defines:
```python
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
```

When the search picks `defense:supports` or `defense:supports_only`, supports are placed at **(12,11) and (15,11)** — the only y=11 cells adjacent to the wall row's launch gaps at (12,12) and (15,12).

**The trap**: with supports at (12,11)/(15,11), AND anchor turrets at (11,11)/(13,11)/(14,11)/(16,11), the cells *adjacent to the gaps from below* are all blocked. Oracle's mobiles cannot reach (12,11) or (15,11) → cannot step UP through the gaps → walk to (2,11) or (25,11) → self-destruct in oracle's own territory.

**Quantitative impact** (the two user-flagged losses):

| Metric | suchir-g loss | not-tnb loss |
|---|---|---|
| Oracle MP spent | 1018 | 1031 |
| Oracle breaches dealt | **3** (all T0-T2) | **3** (all T0-T2) |
| Mobiles self-destructed in OWN territory | **75.1%** (587/782) | **39.2%** (338/863) |
| 100% of SDs at | (2,11) or (25,11) | (2,11) or (25,11) |
| Oracle dmg/MP | 0.0029 | 0.0029 |
| Opp dmg/MP | 0.0222 | 0.0233 — **8× better** |
| Final HP (oracle vs opp) | 35 vs 37 | 35 vs 37 |

The bug causes **100-turn games where oracle outspends opp 4.7× in MP but loses the breach race 3-vs-5**.

### Finding 3: Verified geometry of the fix

A first-pass fix (moving all supports to contiguous y=10: `[(12,10),(13,10),(14,10),(15,10)]`) was proposed by the first analysis agent. **A geometric BFS test (in this session) showed it MAY still create path issues** — supports at (12,10) and (15,10) block the cells from which units enter (12,11) from below, forcing a circuitous route via (12,13)/(15,13) that the engine pathfinder may not prefer.

**The safer fix**: don't place supports anywhere in the gap-adjacent columns (x=12 or x=15) below y=12. Use the **central column only**:
```python
SUPPORTS_BACK = [(SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10),
                 (SUPPORT_IDX, 13, 9),  (SUPPORT_IDX, 14, 9)]
```
This places 4 supports in the central x=13/14 column at y=9-10. They provide shield to scouts passing through the central column AND don't block any gap-approach tile.

Verified geometrically (BFS through gamelib's `in_arena_bounds`):
- (12,11) reachable from spawn ✓
- (15,11) reachable from spawn ✓
- (12,12) and (15,12) reachable + exitable to opp territory ✓

### Finding 4: Three persistent counter-patterns remain unsolved

Both versions lose IDENTICALLY to:
- **aa0/funnel-a** (left-flank funnel breach at (3,10))
- **ashmit/funnel-crush-before** (50 breaches at (4,9))
- **Egil/python-algo-siege** (siege strategy)

These are byte-identical losses across versions. They are **structural counter-strategies** the search hasn't solved. They will need a SEPARATE plan (see Tier 2 below).

---

## 2. Status of the prior G1-G8 plan

Re-evaluating each in light of new findings:

| Fix | Status | Notes |
|---|---|---|
| **G7** (fast copy) | ✅ SHIPPED in M1Lite | Confirmed working |
| **G3** (rebuild prior) | ❌ REJECTED in M1 | Caused regression; G3-Additive proposed but not yet validated |
| **G2** (posterior sampling) | ⏸ PAUSED | Depends on G3-Additive being shipped first |
| **G8** (pass tiles to opp_model) | ⏸ PAUSED | Plumbing for G2 |
| **G1** (coverage gap proposer) | ⚠️ DOWNGRADED | Audit found it hardcoded; needs full rewrite |
| **G5** (multi-intensity patches) | ⚠️ DOWNGRADED | Audit found regression risk |
| **G4** (adaptive compute) | ⏸ DEFERRED | Lower priority than the trap fix |
| **G6** (offense tracker) | ⏸ DEFERRED | Will help but lower priority than the trap fix |

The G1/G5 hand-crafted defensive templates wouldn't have helped the 2 user-flagged losses anyway — those losses are not coverage issues, they're SELF-INDUCED PATH BLOCKS.

The trap fix is **higher priority than ANY of the prior G1-G8** because:
1. It's a clear bug (verifiable via geometry test)
2. It affects 100-turn HP-tiebreak losses (the most "annoying" loss type)
3. The fix is small (~5 lines)
4. Risk is low (only changes `defense:supports` placement)

---

## 3. The new plan: T0-T6 (sequential milestones from M1Lite)

**T0 — TRAP FIX** (this is the new "M2" — supersedes the old G2-on-M1Lite plan as the next milestone)
**T1 — Refund-Replace allows support removal**
**T2 — Stuck-mobile penalty in value function**
**T3 — Filter LAUNCHERS_FAR by reachability**
**T4 — G3-Additive (rebuild prior carefully)**
**T5 — G2 (posterior sampling, depends on T4)**
**T6 — G6 (offense tracker, depends on T2)**

### T0 — Fix `SUPPORTS_BACK` to avoid gap-adjacent tiles [HIGHEST PRIORITY]

**File**: `oracle_core/enumerator.py:86-87`

**Change**:
```python
# OLD (BUGGY):
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]

# NEW:
SUPPORTS_BACK = [(SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10),
                 (SUPPORT_IDX, 13, 9),  (SUPPORT_IDX, 14, 9)]
```

**Why central column?**
- (13,10) and (14,10): provide shield to scouts in central spawn lanes (13,0)/(14,0)
- (13,9) and (14,9): deeper-back shield, also reaches via support range 7 to scouts at y=2-3
- Neither blocks any gap-approach (verified by BFS)

**Why NOT (12,10) and (15,10)?**
- (12,10) blocks the only direct approach to (12,11) which is the gap-approach tile
- (15,10) blocks (15,11) similarly
- The agent-proposed fix would create a different (perhaps less severe) trap

**Validation gate** — must pass ALL:

1. **Component tests** (`tests/test_components.py`) — all pass
2. **NEW geometry test** (`tests/test_supports_geometry.py`):
   - Build full skeleton WITH new `SUPPORTS_BACK`
   - Use gamelib BFS to verify (12,11), (15,11), (12,12), (15,12) all reachable from spawn
   - Use gamelib BFS to verify mobiles starting at (13,0) can REACH opp territory (y>=14) — i.e., the gaps are actually USABLE end-to-end
3. **Replay reproduction**: load the suchir-g state at T49 (when the trap formed). Run search with new SUPPORTS_BACK. Verify oracle's offense has DIFFERENT outcomes — specifically, fewer self-destructs in own territory
4. **Tier A (per MILESTONE_PROMPTS.md)**: 10/10 vs (v13_second_ring, python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER) both sides — must hold
5. **Tier B (snorkeldink-v3-1)**: must NOT regress from M1Lite's 2/2 — if it drops, REJECT
6. **NEW Tier C (trap-loss reproduction)**: re-simulate the suchir-g and not-tnb states — verify fix changes the outcome (fewer SDs in own territory; more breaches against opp)

**Upload folder**: `oracle_pure_T0_upload/`
**Branch**: `oracle-pure-T0`
**Commit only after all validation gates pass**.

**Live ladder validation criteria**:
- Watch for matches that ran 100 turns to HP-tiebreak in the past
- Same opps should now finish in <80 turns with oracle winning
- specifically: re-match against suchir-g/python-algo-baseline if available

### T1 — Allow `defense:refund` to remove SUPPORTS too [HIGH PRIORITY]

**File**: `oracle_core/enumerator.py:407-433` (the refund-replace logic)

**Current bug** (verified by code-cross-check agent):
```python
# Line 414:
if sh not in ("DF", "TURRET"):    # ← ONLY turrets considered
    continue
```

**Change**:
```python
# Allow removing supports too (in case the trap forms despite T0 fix,
# OR an old game state has supports placed under the gaps)
if sh not in ("DF", "TURRET", "EF", "SUPPORT"):
    continue
# Drop the hp_frac filter for supports (base supports are 1HP — always low frac)
if sh in ("DF", "TURRET") and hp_frac >= 0.4:
    continue  # only refund damaged TURRETS
```

**Why important**: even with T0 fix, if the search hypothetically re-encounters the trap (e.g., from a buggy template), the search must be able to ESCAPE by removing the offending support. Currently it can't.

**Validation**: same as T0; plus a unit test that constructs a state with a support at (12,11) and verifies `defense:refund1` includes a support-removal candidate.

### T2 — Penalize "stuck mobile" outcomes in value function [HIGH PRIORITY]

**File**: `oracle_core/value.py`

**Add a new term**: per-unit penalty for mobiles that died WITHOUT breaching, in our own territory.

```python
# In evaluate(), after the existing breach term:

# NEW: penalize "self-destruct in own territory" — strong signal of
# blocked offense. Doesn't just check breach flag; checks whether OUR
# mobile units are in the post-state at unexpected locations OR if
# the deploys we sent never appeared as breaches.

# Approximate: count mobiles in post_state that are OURS, not breached,
# and have steps_taken < expected_path_length.
my_stuck = 0
for m in state_dict.get("mobiles", []):
    if int(m.get("player", 0)) != my_player:
        continue
    if m.get("breached", False):
        continue
    # If still on board (not killed), unlikely to have made progress
    if int(m.get("steps_taken", 0)) < 5:
        my_stuck += 1
weights["stuck_penalty"] = -10  # NEW weight
stuck_term = weights["stuck_penalty"] * my_stuck

# Add to total score
return float(hp_term + struct_term + sp_term + mp_term + breach_term +
             syn_term + stuck_term)
```

**Why important**: even with T0 fix, if a different defense template inadvertently traps mobiles, the value function should penalize plans that produce stuck mobiles, helping the search avoid them.

**Penalty magnitude (-10)**: small enough to not flip non-trap matches; large enough to be visible across multiple stuck units. A turn with 10 stuck mobiles → -100 utility, comparable to losing 1 HP.

**Validation**:
- Tier A 10/10 must hold (penalty doesn't flip wins)
- Test: load a state where stuck-mobile penalty would have prevented an SD-heavy plan; verify search now avoids it

### T3 — Filter `LAUNCHERS_FAR` by reachability [MEDIUM PRIORITY]

**File**: `oracle_core/enumerator.py:104` and `_is_legal_spawn_tile()` around line 484

**Bug**: `LAUNCHERS_FAR = [(0,13), (27,13), (1,12), (26,12), (2,11), (25,11)]` includes `(0,13)` and `(27,13)`. When INNER_CORNER_TURRETS at `(1,13)` and `(26,13)` are placed, units spawning at `(0,13)`/`(27,13)` have NO open neighbors and self-destruct on spawn.

**Fix**: enhance `_is_legal_spawn_tile()`:
```python
def _is_legal_spawn_tile(game_state, x: int, y: int) -> bool:
    if not game_state.game_map.in_arena_bounds([x, y]):
        return False
    if (x, y) not in BOTTOM_LEFT_EDGE and (x, y) not in BOTTOM_RIGHT_EDGE:
        return False
    if game_state.contains_stationary_unit([x, y]):
        return False
    # NEW: reject if all neighbors are blocked
    n_open = 0
    for dx, dy in [(-1,0),(1,0),(0,-1),(0,1)]:
        nx, ny = x+dx, y+dy
        if not game_state.game_map.in_arena_bounds([nx, ny]):
            continue
        if game_state.contains_stationary_unit([nx, ny]):
            continue
        n_open += 1
    return n_open >= 1
```

**Validation**: standard Tier A + a unit test confirming (0,13) is rejected when (1,13) is occupied.

### T4 — G3-Additive (rebuild prior with breach context, additively)

**Already specified in `M1_LITE_SHIP_AND_G3_INVESTIGATE.md` Part 2**.

This is the original G3 fix, but ADDITIVELY: every observation goes to br0 (preserving OLD behavior exactly) AND additionally to its actual breach-band bucket.

**Status**: prerequisite for T5 (G2). Lower priority than T0-T3 because the trap fix matters more for the 100-turn loss pattern.

### T5 — G2 (posterior sampling)

**Already specified in `MILESTONE_PROMPTS.md` (M2 prompt)**.

Depends on T4 being shipped (otherwise br1_2/br3p posteriors are empty and G2 falls back to existing samples).

### T6 — G6 (offense tracker)

**Already specified in `MILESTONE_PROMPTS.md` (M5 prompt)**.

Synergizes with T2 (stuck-mobile penalty): T2 penalizes the *outcome* of bad offense plans; T6 penalizes the *archetype* across turns.

---

## 4. What's NOT in this plan (deferred or rejected)

### Deferred (Tier 2 — separate plan needed)
- **Counter for funnel-channeled flank breaches** (aa0/funnel-a, ashmit/funnel-crush-before, Egil/siege loss patterns). These are persistent unsolved counter-strategies. Need a separate, narrowly-focused plan after T0-T6 ship.
- **pipmy/Redemption pattern** (3 oracle_pure losses, 0 m1_lite matches). Need data first; once m1_lite plays Redemption, can analyze.

### Rejected (now and likely for good)
- **G1 hardcoded coverage-gap templates with specific tile lists** — replaced by the principled idea (gap-detection from live state) but the implementation needs full re-thinking; the original sketch in REVISED_IMPROVEMENT_PLAN.md added templates that bypass real path constraints.
- **G5 single-intensity 20-SP patches** — adds heavy-SP templates that distort search, low expected benefit
- **G4 adaptive compute via funnel-detector** — heuristic switch violation; the time-remaining version is OK but lower priority

---

## 5. Ordering and dependencies

```
M1Lite (shipped)
  ↓
T0 (TRAP FIX) ← must ship first; standalone
  ↓
T1 (allow support removal) ← refines T0; standalone after T0
  ↓
T2 (stuck-mobile penalty) ← compounds T0; standalone
  ↓
T3 (filter LAUNCHERS_FAR) ← related to T0; standalone
  ↓
T4 (G3-Additive) ← prerequisite for T5
  ↓
T5 (G2 posterior sampling)
  ↓
T6 (G6 offense tracker)
```

**Recommended grouping into milestones**:
- **M2 (T0+T1+T2+T3)**: anti-trap milestone. Ship as one unit since they're related.
- **M3 (T4)**: prior fix. Standalone.
- **M4 (T5+T6)**: sampling + behavioral. Ship together.

---

## 6. Validation framework (per milestone)

Same shared test suite from `MILESTONE_PROMPTS.md`:

**Tier A — REGRESSION FLOOR (must pass 100%)**:
- 10 matches: v13_second_ring, python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER (P1+P2)
- ANY single regression = REJECT

**Tier B — BREAKTHROUGH SIGNAL**:
- snorkeldink-v3-1 (P1+P2). M1Lite is 2/2. Future milestones must NOT drop below 2/2.

**NEW Tier C — TRAP-LOSS REPRODUCTION** (specifically for T0-T2):
- Load suchir-g and not-tnb game states at T49/T50 (the trap-formation turn)
- Run search with new code
- Verify: (a) `defense:supports` no longer trapped — gaps remain reachable; (b) self-destruct count in own territory drops; (c) breach count goes UP

**Tier D — TELEMETRY**:
- sims/turn (must not drop)
- wall/turn (must not exceed 11s)
- watchdog/exception count = 0

**Strict rule**: each milestone must pass Tier A + must not regress Tier B from M1Lite baseline (2/2 vs snorkeldink). Any regression → REJECT and rollback.

---

## 7. Honest expectations

| Milestone | Est. ELO | Confidence |
|---|---|---|
| M2 (T0+T1+T2+T3) | +30-60 | HIGH (T0 alone fixes a real bug; verifiable) |
| M3 (T4) | +5-15 | MEDIUM (G3-Additive is a strict superset; should not regress; benefit subtle) |
| M4 (T5+T6) | +10-30 | MEDIUM (depends on M3 populating posterior data) |

Total realistic: **+45-105 ELO from M1Lite baseline**.

Critical caveat: 3 unsolved counter-patterns (aa0/funnel-a, ashmit/funnel-crush-before, Egil/siege) will continue to be deterministic losses unless a separate plan addresses them. ELO ceiling at ~2300 until those are solved.

---

## 8. Why this plan is non-hardcoded

Per the project's anti-pattern checklist:
- **T0** (move SUPPORTS_BACK off gap-adjacent tiles): structural change — corrects a bug in the existing template; doesn't introduce new tile-specific intelligence.
- **T1** (allow refund of supports): mechanical extension — allows the search to consider an additional class of action (remove a support); no specific opp knowledge.
- **T2** (stuck-mobile penalty): generalizes — any plan with stuck mobiles gets penalized regardless of opp.
- **T3** (filter unreachable launchers): mechanical — based on live state, not hardcoded.
- **T4-T6**: per the prior plan; non-hardcoded.

No fix bakes in tile-specific knowledge from observed losses; every fix would help against a NEW, unknown opp using a similar mechanism.

---

## 9. Files to update

When implementing M2 (T0+T1+T2+T3):
- `oracle_core/enumerator.py`: change SUPPORTS_BACK; modify refund-replace; modify _is_legal_spawn_tile
- `oracle_core/value.py`: add stuck_mobile penalty term
- `tests/test_supports_geometry.py`: NEW test file
- `tests/test_components.py`: ensure existing tests still pass
- `MILESTONE_M2_RESULTS.md`: validation report
- `oracle_pure_T0_upload/` or `oracle_pure_M2_upload/`: new upload folder

---

## Acknowledgments

This plan supersedes prior plans because of new empirical findings that the older analysis missed:
- The "support trap" bug was not in any prior plan (G1-G8 didn't touch this)
- The geometric verification proved the first agent's proposed fix was incomplete
- The head-to-head shared-opp analysis confirmed M1Lite is the right base
- The 3 persistent counter-patterns (funnel/siege archetypes) are now correctly identified as unsolved (deferred to future plan)
