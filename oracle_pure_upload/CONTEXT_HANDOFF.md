# Context Handoff — oracle_pure / oracle_pure_M1Lite project state

**Read this entire document before doing any work on oracle_pure.**

This is a context handoff from a previous Claude Code session whose
context window was about to expire. Everything you need to make
informed decisions about oracle_pure's improvement path is here.

Total reading time: ~30 minutes. Worth it — saves you from repeating
multi-day investigations whose conclusions are documented below.

---

## 0. Quick orientation

You are working on a search-driven algorithm called **`oracle_pure`** for
the **Citadel Terminal** competition (a special-rules variant of
Correlation One's *Terminal* — a 2-player tower defense game on a 28×28
diamond grid). The user is `TAHA` (team WICK, team_id 5826) on
`terminal.c1games.com`.

The algorithm is currently **live on the ladder**: 6 instances total,
3 of `oracle_pure` (rating 1947–2172) and 3 of `oracle_pure_M1Lite`
(rating 1806–2103). The variant labeled "M1Lite" is a strict improvement
over the original (verified head-to-head, see §5).

**Current active work**: M2 milestone — adding a path-viability check to
prevent the algorithm from trapping its own offense. See §6 for full
details. The implementation prompt is at
`algos/oracle_pure/M2_IMPLEMENTATION_PROMPT.md`.

---

## 1. Project context

### What is oracle_pure?

A genuinely search-driven Citadel Terminal algorithm. The search OWNS
every per-turn decision (defense + offense). It is NOT a heuristic
pipeline with bolted-on search — that was the prior failed attempt
(`algos/oracle/`).

Key facts:
- 100% of decisions come from search picking among ~1500 candidate plans
- Each plan is evaluated by `sim_rs` (Rust simulator on the live server,
  pure Python `pysim` fallback locally on Mac)
- Time budget: 11s soft / 13s SIGALRM watchdog
- Currently uses ~3.6% of the budget (avg 394ms/turn — see §3.2)
- The only hardcoded fallback is a "place 4 corner turrets + scout
  rush" safety net that fires only if the watchdog trips

### Repository root

`/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/`

Critical paths:
- `algos/oracle_pure/` — current dev folder for oracle_pure (active)
- `algos/oracle/` — the FAILED prior heuristic-disguised attempt (DO NOT
  TOUCH; read-only reference for "what not to do")
- `oracle_pure_upload/` — original oracle_pure (live as `oracle_pure_upload`
  on the ladder, 3 instances)
- `oracle_pure_M1Lite_upload/` — M1Lite variant (live as
  `oracle_pure_M1Lite_upload`, 3 instances)
- `C1GamesStarterKit-master/` — engine.jar + run_match.py + python-algo
  reference (read-only)
- `replays/oracle_pure_live/` — 144+ live ladder replay files (organized
  by version + result + opponent)

### Key project documents (READ THESE in order)

1. `CLAUDE.md` (repo root) — project orientation, key constants,
   important "do not confuse this" notes
2. `AGENTS.md` (repo root) — agent communication conventions
3. `docs/UNITS_REFERENCE.md` — Citadel-specific unit stats (verify
   against, never hardcode)
4. `docs/TARGETING_AND_PATHING.md` — engine targeting + pathing rules
5. `docs/STRATEGY_GUIDE.md` — archetype reference

For oracle_pure specifically:
6. `algos/oracle_pure/REPORT.md` — original architecture + anti-patterns
7. `algos/oracle_pure/VALIDATION.md` — original validation results
8. `algos/oracle_pure/IMPROVEMENT_PLAN.md` — first improvement plan
   (22 fixes; many rejected later)
9. `algos/oracle_pure/M2_IMPLEMENTATION_PROMPT.md` — **CURRENT M2
   PROMPT** — what's actively being implemented

---

## 2. Algorithm architecture (how oracle_pure works)

### Per-turn flow (`algos/oracle_pure/algo_strategy.py:on_turn`)

```python
on_turn(turn_state):
  1. Build gamelib.GameState from turn_state JSON
  2. Adapt to sim_rs schema dict via state_adapter.adapt_game_state()
  3. Call search(...) which returns the best ActionPlan
  4. Apply that ActionPlan via plan.apply_to_game_state()
  5. Submit turn
  
  On exception/watchdog: safe_fallback() places 4 corner turrets
  and spawns scouts at center launcher (intentionally weak)
```

### The search loop (`oracle_core/search.py`)

```python
search(...):
  base_state = adapt_game_state(...)  # convert once
  candidates = enumerate_plans(...)    # ~1500 plans
  
  # Phase 1: cheap cull (k_opp_phase1=1 sample per candidate)
  for cand in candidates (~1500):
    sd = _fast_copy_state(base_state)  # M1Lite: 22x faster than deepcopy
    apply candidate's structure ops + opp's mobile ops
    run sim_rs → score via value.evaluate()
    add to phase1_scores
  
  # Phase 2: top-30 with full sampling (k_opp=6 samples each)
  for cand in top 30 from phase1:
    avg score over 6 opp samples
  
  # Depth-2: top-3 get next-turn projection (cheap heuristic)
  for cand in top 3:
    project 1 more turn forward, blend scores
  
  return top_scoring_plan
```

### Enumerator (`oracle_core/enumerator.py`)

Generates ~1500 candidate plans per turn as the cross-product of:
- **~13 defense templates** (defense:none, defense:t0_full,
  defense:anchor_corners_walls, defense:walls{2,4,6,8,12},
  defense:diag_reinforce, defense:side_corners, defense:outer_wide,
  defense:outer_corner_block, defense:supports, defense:wall_heavy,
  defense:upg{2,4,8,16}, defense:refund{1,2}, defense:patch{1,2})
- **~150 offense templates** (offense:hold + scout/demo/interceptor
  rushes from ~30 launchers × 4-6 batch sizes + mixed waves + two-prong)

Cross-product → ~1500 plans, capped at 2500.

**Key constants** to know (at top of `enumerator.py`):
- `ANCHOR_TURRETS = [(11,11),(13,11),(14,11),(16,11)]` — central row
- `OUTER_TURRETS = [(5,11),(22,11),(8,11),(19,11)]` — wider y=11
- `DIAG_TURRETS = [(4,11),(23,11)]` — diagonal at y=11
- `INNER_CORNER_TURRETS = [(1,13),(26,13)]` — at corners
- `OUTER_CORNER_TURRETS = [(0,13),(27,13)]` — at outer corners
- `SIDELANE_TURRETS = [(7,9),(20,9)]` — y=9 pair
- `SECOND_RING = [(11,5),(16,5)]` — deep at y=5
- `WALL_ROW_Y12 = [(WALL_IDX, x, 12) for x in range(2, 26) if x not in (12, 15)]`
  — 22 walls at y=12 with **launch gaps** at x=12 and x=15
- `EDGE_WALLS = [(2,13),(25,13),(4,13),(23,13)]`
- **`SUPPORTS_BACK` — has the BUG** (currently `[(12,11),(15,11),(13,10),(14,10)]`)
  — see §6 for the M2 fix

### Value function (`oracle_core/value.py`)

Scores a sim_rs post-state from oracle's perspective. Components:
- **HP differential** (weight 100) — dominant
- **Structure value** (sum of coef × hp_frac per structure) — coefficients
  per (type_idx, upgraded): wall 1.5/6.0, turret 4.0/12.0, support
  0.5/10.0
- **Resource banking** (saturating log function past ~10 SP/MP)
- **Breach term** (weight 25 × delta_hp) — currently buggy (see §6.1
  "code defects" — patch math was wrong)
- **Support-scout synergy** (small bonus for upgraded supports + our
  scouts)

**Key blind spot**: the value function has ZERO spatial awareness. A
turret at (0,13) and a turret at (4,11) score identically per HP. This
is a known architectural limitation (deferred to future work).

### Opponent model (`oracle_core/opponent_model.py`)

Empirical action-signature distribution conditioned on bucket:
`(turn_band, opp_mp_band, our_mp_band, breach_band)`.

- **Prior**: built offline from 427 ranked replays
  (`tools/build_opp_model.py`); cached in `data/opp_model_priors.json`
- **Posterior**: updated in-game from `on_action_frame` events
- **Sampling**: top-K by weight (deterministic) — bucket lookup +
  hand-injected adversarial signatures (5 scout launchers + 5 demo
  launchers as fallback for cold-start)

**Known issue**: prior currently has only `br0` buckets (M1's G3 fix
attempted to add `br1_2`/`br3p` coverage but was rejected — see §7).
G3-Additive proposed for future.

### sim_rs / pysim (`oracle_core/sim_eval.py`, `vendored_sim/`)

- `sim_rs`: Rust action-phase simulator, ~14.6K sims/sec single-thread,
  bundled as Linux x64 abi3 wheel in `bundled_sim_rs/` (loads on the
  live server only)
- `pysim`: pure-Python fallback in `vendored_sim/`, ~3K sims/sec
  (used locally on Mac since the bundled wheel is Linux-only)
- Both are bit-exact with engine.jar (verified, see
  `algos/athena/sim/SIM_PARITY.md`)

### Critical edge-ID convention (the bug that bit us early)

`vendored_sim/map.py` defines:
- `EDGE_TOP_RIGHT = 0`
- `EDGE_TOP_LEFT = 1`
- `EDGE_BOTTOM_LEFT = 2`
- `EDGE_BOTTOM_RIGHT = 3`

The inherited `state_adapter.py` originally used `BL=0, TR=3` which is
WRONG vs map.py. **This was fixed early in oracle_pure development**
(see git history). Both `state_adapter.py` and `plan.py` now correctly
use map.py's convention. Don't touch these without re-verifying.

---

## 3. Versions and current state

### oracle_pure (original)

Live as `oracle_pure_upload`, 3 instances (algo_ids 361251 / 361264 / 361265).

The first shipping version. Hit 2138 ELO live (vs predicted 1700-1900).
Beats all 4 official bosses (R1 Sawtooth, R2 Infiltrator, R3 Jukebox,
R4 Champion). Has the trap bug (see §6).

### oracle_pure_M1Lite

Live as `oracle_pure_M1Lite_upload`, 3 instances (algo_ids 361357 /
361353 / 361359).

Differs from oracle_pure ONLY in the G7 fast_copy_state fix:
`oracle_core/search.py` replaces `deepcopy(base_state)` with a
hand-rolled shallow copy `_fast_copy_state()`. 22× faster per call;
~37% reduction in per-turn wall-clock; identical algorithm logic
otherwise.

**Strictly better than oracle_pure** (verified head-to-head, see §5).
This is the BASE for all future improvements.

### Currently shipped wall-clock telemetry

Measured on heuristic_v1 P1 match (M1Lite vs heuristic_v1):

| Metric | Value | % of 11s budget |
|---|---|---|
| Avg wall-clock/turn | 394 ms | 3.6% |
| Max wall-clock/turn | 810 ms | 7.4% |
| P50 / P90 / P99 | 430 / 590 / 810 ms | — |
| Avg sims/turn | 1,604 | — |
| Max sims/turn | 2,686 | — |
| Headroom remaining | 96% | — |

**The algorithm is candidate-limited, not compute-limited.** With ~1500
distinct plans per turn, even infinite compute wouldn't add more
distinct sims. See §8 for why "use more compute" hasn't worked.

---

## 4. Pre-shipped improvements (the journey so far)

### M0 (the original ship — `algos/oracle_pure/`)
Built from scratch, beats local Tier 1-3 ladder 10/10, hits 2138 ELO live.

### M1 attempted (G7 + G3) — REJECTED
- **G7** (fast_copy_state): clean win, 22× faster per copy, no behavior change
- **G3** (rebuild prior with breach context via `tools/build_opp_model.py`):
  bucket schema correctly populated (40 → 116 buckets); BUT redistributing
  observations thinned br0 weights ~50% in many buckets. Against opps that
  rarely breach (heuristic_v1, python-2l-aet, snorkeldink P2), oracle stays
  in br0 with weaker predictions. **Broke 4 of 10 Tier A matches.** Also
  destroyed G7's snorkeldink breakthrough (G7-only got snorkeldink 2/2;
  G7+G3 dropped to 0/2).

User decision: ship G7 as M1-Lite; investigate G3 separately.

### M1Lite (G7 only) — SHIPPED ✓
- Tier A: 10/10 vs (v13_second_ring, python-algo, heuristic_v1,
  Lostkids/python-2l-aet, funnel_INTER) both sides
- **Tier B: 2/2 vs snorkeldink-v3-1** ★ BREAKTHROUGH (was 0/2)
- Live ELO: 1806-2103 across 3 instances
- Wall-clock: -37% vs oracle_pure baseline

### M2 (current work) — IN IMPLEMENTATION
Path-viability check + ALT-OUTSIDE SUPPORTS_BACK swap. See §6.
Implementation prompt at `M2_IMPLEMENTATION_PROMPT.md`. Branch
`oracle-pure-M2` (when shipped). The previous Claude session wrote the
prompt; a new session executes it.

---

## 5. What we learned from 114 live replays

The user uploaded 6 algo instances (3 of each version). Across all
instances, **114 non-boss matches** were collected:
- oracle_pure: 70 matches (52W/18L)
- M1Lite: 44 matches (36W/8L)

### Head-to-head finding (§5.1)

Across **22 shared opponents** (where both versions played the same opp):
- 18 dead-heat-perfect (both versions win)
- 3 dead-heat-zero (both versions lose deterministic counter-patterns)
- 1 M1Lite IMPROVED (ameyg/funnel-rush-v7: oracle_pure 1/2 → M1Lite 1/1)
- **0 regressions**

**Verdict: M1Lite is a strict improvement over oracle_pure.** This is
the head-to-head data — ELO is confounded by matchmaking and is NOT a
reliable comparison metric.

The single improvement (ameyg/funnel-rush-v7) was investigated:
turn-by-turn replays are byte-identical T0–T19, then diverge at T20 due
to G7's faster sims producing slightly different plan selection. The
divergence cascades and M1Lite wins at T68 vs oracle_pure losing at
T99. Pure timing-induced sim-budget mechanism — exactly what G7 was
designed to enable.

### Three persistent counter-patterns (UNSOLVED)

Both versions LOSE IDENTICALLY (byte-identical replays) to:

1. **`aa0/funnel-a`** (left-flank scout funnel)
   - Opp builds wall corridor + supports late game
   - 100% of HP loss at left-flank tiles (3,10), (4,9), (7,6), (2,11)
   - Opp's MP-banking pattern: hold 20+ MP, dump 30+ scouts

2. **`ashmit/funnel-crush-before`** (left-flank scout drill)
   - 100% of HP loss at single tile (4,9) (50 breaches)
   - Pure left-side funnel demolisher rush

3. **`Egil/python-algo-siege`** (siege strategy, asymmetric)
   - Mirror-image losses: oracle ends at 3 HP regardless of side
   - Loss is structural, not stochastic

These are **deferred to a future plan** — they need fundamentally
different defenses than current oracle has. ELO ceiling ~2300 until
they're addressed. Don't try to fix them in M2-M5; they're a separate
problem.

### The trap bug discovered (§5.2)

The user flagged TWO peculiar 100-turn losses:
- `m1_lite/inst3` vs `suchir-g/python-algo-baseline`
- `m1_lite/inst3` vs `not-tnb/python-algo-tnb`

Forensic analysis (3 separate Claude agents in parallel) revealed:
**oracle's offense is being blocked by its OWN defensive components.**

Mechanism (verified by replay frame-by-frame trace + code inspection):
1. Search picks `defense:supports` mid-game (T37-T75), placing supports
   at (12,11) and (15,11)
2. These tiles are the ONLY y=11 cells adjacent to the wall row's
   launch gaps at (12,12) and (15,12)
3. Combined with anchor turrets at (11,11)/(13,11)/(14,11)/(16,11),
   the gap-approach tiles are sealed
4. Oracle's mobiles cannot reach (12,11) or (15,11) → cannot step UP
   through the gaps → walk to (2,11) or (25,11) → SELF-DESTRUCT

Quantitative damage:
| Match | MP spent | Breaches dealt | % SD in own territory | Final HP |
|---|---|---|---|---|
| suchir-g | 1018 | 3 (T0-T2 only!) | 75.1% (587/782) | 35 vs 37 |
| not-tnb | 1031 | 3 (T0-T2 only!) | 39.2% (338/863) | 35 vs 37 |

**Both losses: oracle outspent opp 4.7× in MP, lost 3-vs-5 on breaches.**
100% of self-destructs were at (2,11) or (25,11) — the only edge tiles
left reachable.

This is the **PRIMARY motivation for M2**.

---

## 6. M2 — what's being implemented now

### The fix (two changes to one file)

Both changes are in `oracle_core/enumerator.py`:

**Change A — path-viability check** (the principled fix)

Add a new helper `_is_offense_viable(game_state, plan_structure_xys)`
that BFS-checks whether ANY spawn-edge tile can still reach opp
territory (y≥14) after the plan's structures are added. Insert a
rejection check at the end of `_build_defense_plan()` — if no path
exists, return `None` (reject the plan).

```python
from collections import deque

def _is_offense_viable(game_state, plan_structure_xys):
    blocked = set(plan_structure_xys)
    for x in range(28):
        for y in range(14):
            if not game_state.game_map.in_arena_bounds([x, y]):
                continue
            if game_state.contains_stationary_unit([x, y]):
                blocked.add((x, y))
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
        for dx, dy in [(0,1),(0,-1),(1,0),(-1,0)]:
            nx, ny = x+dx, y+dy
            if not game_state.game_map.in_arena_bounds([nx, ny]): continue
            if (nx, ny) in blocked: continue
            if (nx, ny) in visited: continue
            visited.add((nx, ny))
            q.append((nx, ny))
    return False
```

**Change B — SUPPORTS_BACK swap to ALT-OUTSIDE**

```python
# OLD (BUGGY):
SUPPORTS_BACK = [(SUPPORT_IDX, 12, 11), (SUPPORT_IDX, 15, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
# NEW:
SUPPORTS_BACK = [(SUPPORT_IDX, 10, 11), (SUPPORT_IDX, 17, 11),
                 (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10)]
```

ALT-OUTSIDE has identical upgraded-shield strength (33.4) but doesn't
seal the gap-adjacent tiles.

### Why both changes (not just one)?

- Change A alone: would correctly reject ALL `defense:supports`
  variants (they all trap), so we'd lose support-amplification entirely
- Change B alone: hardcoded patch (different bug class could recur)
- Together: bug fixed (A), function preserved (B), future bugs prevented (A)

### Verified compute impact

Benchmarked this session:
- Path check: 0.255 ms/call (constant time at 28×28 grid)
- Per-turn cost: 13 templates × 0.255 ms = 3.3 ms
- With 5 patch templates: 4.5 ms/turn
- Current avg wall-clock: 394 ms/turn
- After path check: ~398 ms/turn (1.1% overhead)
- **Sims/turn: UNCHANGED** (check runs at template construction, not
  per sim)
- Even worst case (path-check on every plan, 1500/turn): 770ms total,
  still 7% of 11s budget

### Implementation prompt

Self-contained at `algos/oracle_pure/M2_IMPLEMENTATION_PROMPT.md`.
Includes:
- Exact code for Change A + B
- New `tests/test_path_viability.py` with 5 specific assertions
- 6 validation gates (component tests, path tests, Tier A 10/10,
  Tier B snorkeldink 2/2, telemetry sanity, code-change verification)
- Upload folder creation (folder, NOT zip — user explicit preference)
- Branch `oracle-pure-M2`, no merge to main until live validation
- Failure protocol (don't ship if any gate fails)

A fresh Claude session should be able to execute the prompt without
reading anything else (it's fully self-contained). But if you're the
new session and need to make decisions ABOUT the M2 plan, read
PATH_VIABILITY_PLAN.md and CRITICAL_EVAL_v3.md (referenced below).

---

## 7. Plan history (what's been tried, rejected, shipped)

The improvement plan has gone through 4 major iterations. Each one
taught us something. Don't recreate previous mistakes.

### Iteration 1: `IMPROVEMENT_PLAN.md` (original, 22 fixes)

The first comprehensive plan. Listed 22 fixes across 4 tiers. Many were
hardcoded shortcuts that violated the project's anti-pattern rules
(REPORT.md §8). Most are now defunct; the document is preserved for
context.

### Iteration 2: `FUNNEL_COUNTER_PLAN.md` (REJECTED)

Focused plan to counter funnel archetypes. Rejected after critical
audit revealed:
- F1 (flank-corridor templates) was hardcoded — encoded specific tiles
  from 8 observed losses; would have walled oracle's own launchers
- F4 (archetype-detector that bumps k_opp_phase1) was a heuristic-switch
  violation — exactly what the project anti-pattern checklist forbids
- F2 had magic constants hand-tuned to observed losses

### Iteration 3: `REVISED_IMPROVEMENT_PLAN.md` (G1-G8 framework)

After the FUNNEL critique, restructured into 8 fixes (G1-G8) with
explicit non-hardcoded alternatives. Spawned the M1 attempt (G7+G3).

### Iteration 4: `IMPROVEMENT_PLAN_v3.md` (REJECTED)

Built to address the trap bug. Proposed T0 (move SUPPORTS_BACK to
contiguous y=10). User correctly questioned whether this was hardcoded.
Critical evaluation (`CRITICAL_EVAL_v3.md`) revealed:
- T0 IS hardcoded (just swapping one tile list for another)
- My v3 proposal was sub-optimal (8% less shield than original)
- ALT-OUTSIDE option (which I missed initially) is provably better
- The principled fix is a path-viability check, NOT tile-list swaps

### Iteration 5: `PATH_VIABILITY_PLAN.md` (CURRENT)

The actual M2 plan. Two changes (Change A + Change B). Verified compute
analysis. Ships as M2.

### Documents to read in priority order (if you need plan context)

1. `algos/oracle_pure/PATH_VIABILITY_PLAN.md` — the actual current plan
2. `algos/oracle_pure/M2_IMPLEMENTATION_PROMPT.md` — implementation details
3. `algos/oracle_pure/CRITICAL_EVAL_v3.md` — why we rejected v3
4. `algos/oracle_pure/CONTEXT_HANDOFF.md` — this document
5. `algos/oracle_pure/REPORT.md` — original architecture + anti-patterns
6. `algos/oracle_pure/MILESTONE_M1_RESULTS.md` — why G3 failed
7. `algos/oracle_pure/M1_LITE_SHIP_AND_G3_INVESTIGATE.md` — M1Lite ship +
   G3-Additive investigation prompt

Documents to NOT read unless needed for archaeological purposes:
- `algos/oracle_pure/IMPROVEMENT_PLAN.md` (superseded)
- `algos/oracle_pure/FUNNEL_COUNTER_PLAN.md` (rejected)
- `algos/oracle_pure/REVISED_IMPROVEMENT_PLAN.md` (mostly superseded)
- `algos/oracle_pure/IMPROVEMENT_PLAN_v3.md` (rejected)
- `algos/oracle_pure/MILESTONE_PROMPTS.md` (M2 prompt is in
  M2_IMPLEMENTATION_PROMPT.md instead now)

---

## 8. Hard-won lessons (the principles)

The user has actively pushed back on hardcoded patches throughout the
project. These are the principles you must apply:

### Lesson 1: "More compute = better" is empirically false

Three documented attempts to use more compute REGRESSED matches:
- Bumping `k_opp_phase1` from 1 to 2 → lost FUNNEL match
- Bumping to 3 → lost Lostkids match
- G3 (richer prior) → broke 4/10 Tier A

Why: the search is **candidate-limited, not compute-limited**. With ~1500
distinct plans per turn, more compute just adds noisy averages. Sample
diversity > sample count. Better signal > more samples.

### Lesson 2: Hardcoded tile lists ARE the anti-pattern

REPORT.md §8 explicitly forbids hardcoding heuristic_v1's defense
skeleton. By extension: hardcoding ANY specific tile coordinates from
observed losses is the same anti-pattern with different coordinates.

The PRINCIPLED approach: derive from board state, not constants.
- Path-viability check derives from `game_state` — generalizes
- "Move SUPPORTS_BACK to ALT-OUTSIDE" is hardcoded — patch only

In M2, Change A is principled; Change B is acknowledged as a hardcoded
triage that's necessary because Change A would otherwise eliminate ALL
support templates.

### Lesson 3: ELO is a confounded metric

Don't use ELO to compare two algorithm versions — opponent matchups
differ between instances. Use **head-to-head shared-opponent comparison**
instead. M1Lite was confirmed strict-superior via 22 shared opps despite
having a slightly LOWER aggregate ELO (small sample size noise).

### Lesson 4: Strict-superset rule

Every milestone must beat EVERY opponent the prior milestone beat. ANY
regression on Tier A = REJECT the milestone. This rule has saved us
from shipping multiple bad fixes (G3, F1, F4, the v3 T0 sub-optimal).

### Lesson 5: snorkeldink-v3-1 is the breakthrough indicator

Oracle_pure was 0/2 vs snorkeldink-v3-1. M1Lite is 2/2 (BREAKTHROUGH).
Future milestones must NOT drop below 2/2 — losing this signal would
mean we lost the M1Lite improvement.

This is the strongest LOCAL indicator we have of "real algorithmic
improvement" vs "doesn't break anything."

### Lesson 6: Don't waste compute on weak opps already winning

The Tier A/B/C/D framework (in MILESTONE_PROMPTS.md, but copied below
in §9) is intentionally minimal — ~7 minutes of local matches per
milestone. Don't re-test the dozens of human-player ladder opponents
we beat deterministically.

### Lesson 7: Live validation gates the merge

Local Tier A 10/10 ≠ live ladder success. Even passing local gates,
each milestone branch (`oracle-pure-M2`, etc.) must NOT merge to main
until live ladder validation confirms (≥10 ranked matches, ELO not
dropping >40 vs prior milestone).

### Lesson 8: User communication preferences

The user is critical, evidence-driven, and skeptical of enthusiasm:
- Proposes counter-arguments to your plans (push back when justified)
- Insists on empirical evidence over claims
- Catches hardcoded fixes that you might rationalize as structural
- Does not want celebratory language; wants honest assessment
- Cares about actual mechanism, not just outcomes
- Will reject plans that don't have clear validation gates

When in doubt, lean toward more critical analysis, more honest
acknowledgment of limitations, and more concrete evidence.

---

## 9. Validation framework

This is the SHARED VALIDATION TEST SUITE used for every milestone. Per
milestone (~7 minutes total local compute):

### Tier A — REGRESSION FLOOR (mandatory, must pass 100%)

10 matches (5 opps × P1+P2). Strict superset of original wins.

| Opp | Path |
|---|---|
| v13_second_ring | `algos/v13_second_ring/` |
| python-algo (starter) | `C1GamesStarterKit-master/python-algo/` |
| heuristic_v1 | `algos/heuristic_v1/` |
| Lostkids/python-2l-aet | `research/finalist_repos/Terminal-Lostkids/python-2l-aet/` |
| funnel_INTER | `research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/` |

Use ABSOLUTE paths in `run_match.py` invocations. Verify NO
`FailedToLoad` in any log. **ANY single regression = REJECT milestone.**

### Tier B — BREAKTHROUGH SIGNAL (snorkeldink-v3-1)

2 matches vs `research/finalist_repos/terminal-c1/snorkeldink-v3-1/`
(P1+P2). M1Lite achieves 2/2; future milestones must NOT drop below.

| Result | Interpretation |
|---|---|
| 0/2 (regression) | REJECT milestone — lost M1Lite breakthrough |
| 1/2 | OK but flag — partial regression |
| 2/2 | preserved — ship |

### Tier C — TARGETED REPRODUCTION

For changes that should affect specific known states. M2 specifically:
load saved replay states (suchir-g, not-tnb) and verify the new code
produces meaningfully different output.

### Tier D — TELEMETRY HEALTH CHECK

Per a representative Tier A match (heuristic_v1 P1):
- avg sims/turn ≥ 1000 (must not drop drastically)
- avg wall/turn < 0.6s (must not blow up)
- max wall/turn < 2s (well under watchdog)
- No `WATCHDOG fired` events
- No `on_turn exception` events

### Live ladder validation (manual, after upload folder created)

User uploads the `oracle_pure_M{N}_upload/` folder. Watch for ≥10
ranked matches:
- ELO not dropping >40 vs prior milestone
- No new losses against opps prior milestone beat (strict superset
  applies live too)

If live regresses, REJECT retroactively. Previous milestone's upload
remains live; failed milestone branch is abandoned.

---

## 10. Forward roadmap (after M2 ships, if approved)

In order of priority. Each is independent of the others (mostly).

### M3 candidate: G3-Additive (rebuild prior, additively)

**Status**: prompt written at `M1_LITE_SHIP_AND_G3_INVESTIGATE.md`
Part 2. Not yet implemented.

The G3 fix in M1 redistributed observations across br0/br1_2/br3p
buckets, thinning br0 weights. The Additive variant would record EVERY
observation at br0 (preserving OLD behavior exactly) AND additionally
at the actual breach-band bucket — strict superset of OLD prior.

Validation: must verify new prior strictly superset of OLD; Tier A 10/10;
Tier B snorkeldink 2/2 preserved.

### M4 candidate: G2 (posterior sampling)

**Status**: specified in `MILESTONE_PROMPTS.md`. Depends on G3-Additive.

Use opp's OBSERVED MP-spend distribution to size adversarial samples.
Replaces hand-tuned magic constants in `opponent_model.py`. Cap
weights at 3.0 to prevent monopolization.

### Various T1-T6 from v3 plan (smaller fixes)

- **T1**: Allow `defense:refund` to remove SUPPORTS too (escape valve
  if the path check ever has a hole)
- **T2**: Penalize "stuck mobile" outcomes in value function (general
  protection)
- **T3**: Filter `LAUNCHERS_FAR` by reachability (prevents corner
  launchers blocked by inner-corner turrets)

### The 3 unsolved counter-patterns (separate plan needed)

aa0/funnel-a, ashmit/funnel-crush-before, Egil/python-algo-siege —
both versions lose 100% of the time, byte-identical replays. These
require structural defensive thinking that's out of scope for M2-M4.
Defer.

### Architectural improvements (long-term)

The value function has zero spatial awareness. The depth-2 projection
uses a hardcoded "scout from (14,0)" heuristic. The opp model can't
predict opp's WALL placement (only mobile spawns). These are
foundational issues that any of T1-T6 only partially address.

---

## 11. File map (where everything lives)

### Source code (modify only with care)

| Path | Purpose | Modified for M2? |
|---|---|---|
| `algos/oracle_pure/algo_strategy.py` | main entry point | NO |
| `algos/oracle_pure/oracle_core/search.py` | search loop (G7 lives here) | NO for M2 |
| `algos/oracle_pure/oracle_core/enumerator.py` | candidate templates | YES (Change A + B) |
| `algos/oracle_pure/oracle_core/value.py` | scoring function | NO for M2 |
| `algos/oracle_pure/oracle_core/opponent_model.py` | opp samples | NO for M2 |
| `algos/oracle_pure/oracle_core/plan.py` | ActionPlan dataclass | NO |
| `algos/oracle_pure/oracle_core/state_adapter.py` | game_state → dict | NO (don't touch) |
| `algos/oracle_pure/oracle_core/sim_eval.py` | sim_rs wrapper | NO (don't touch) |

### Tests

| Path | Purpose |
|---|---|
| `algos/oracle_pure/tests/test_components.py` | 8 existing component tests |
| `algos/oracle_pure/tests/test_fast_copy.py` | M1Lite G7 parity test (3 tests) |
| `algos/oracle_pure/tests/test_prior_parity.py` | M1 G3 parity test (rejected G3 path) |
| `algos/oracle_pure/tests/test_path_viability.py` | NEW for M2 — 5 tests |

### Data

| Path | Purpose |
|---|---|
| `algos/oracle_pure/data/citadel_config_snapshot.json` | game config (live server's) |
| `algos/oracle_pure/data/opp_model_priors.json` | the OLD prior (used by M1Lite) |
| `algos/oracle_pure/data/opp_model_priors.OLD.json` | backup of OLD prior |
| `algos/oracle_pure/data/opp_model_priors.M1_breach_context.json` | rejected G3 prior |

### Upload folders (live ladder)

| Path | Status |
|---|---|
| `oracle_pure_upload/` | original, live as 3 instances |
| `oracle_pure_M1Lite_upload/` | M1Lite, live as 3 instances |
| `oracle_pure_M2_upload/` | NOT YET CREATED — M2 will create this |

### Replays (the data we analyzed)

| Path | Contents |
|---|---|
| `replays/oracle_pure_live/` | 144+ live ladder replays, organized by `{version}_{instance}_{W/L}_{opp_user}_{opp_algo}_t{turns}_{match_id}.replay` |

The 8 most important replays (the trap losses + comparison wins):
- `m1_lite_inst3_L_suchir-g_python-algo-baseline_t100_15314226.replay` (trap loss)
- `m1_lite_inst3_L_not-tnb_python-algo-tnb_t100_15314197.replay` (trap loss)
- `m1_lite_inst1_W_ameyg_funnel-rush-v7_t68_15314321.replay` (M1Lite improvement)
- `oracle_pure_inst2_L_ameyg_funnel-rush-v7_t99_15313499.replay` (oracle_pure loss vs v7 — for delta analysis)
- `oracle_pure_inst3_L_ashmit_funnel-crush-before_t77_15313562.replay` (unsolved counter)
- 3 byte-identical replays of `aa0/funnel-a` (unsolved counter)

### Tools

| Path | Purpose |
|---|---|
| `tools/scrape_ranked_replays.py` | scrape replays from terminal.c1games.com |
| `tools/bestof.py` | run N matches in parallel with Wilson CI |
| `tools/analyze_replay.py` | parse a single replay |
| `tools/detailed_replay.py` | deep replay analysis |
| `algos/oracle_pure/tools/build_opp_model.py` | rebuild prior (G3 lives here; rejected) |

### Live API (for downloading new replays)

Auth cookie at `~/.c1_session.json`. Endpoints (verified per
`citadel_live_api.md` memory):
- `GET /api/game/algo/mine/1338` — list my algos
- `GET /api/game/algo/{id}/matches` — algo's match history
- `GET /api/game/replayexpanded/{match_id}` — full replay JSON

Algo IDs:
- oracle_pure: 361251, 361264, 361265
- M1Lite: 361357, 361353, 361359

---

## 12. What you should do FIRST as the new session

1. **Read this whole document** (you're doing that now).

2. **Read `algos/oracle_pure/PATH_VIABILITY_PLAN.md`** to confirm the
   M2 plan in detail. Read `M2_IMPLEMENTATION_PROMPT.md` if you'll be
   executing M2.

3. **Check git state**:
   ```bash
   cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
   git branch --show-current  # likely 'main' or 'oracle-pure-M1-Lite'
   git log --oneline -10
   git status --short | head -20
   ```

4. **Check whether M2 has been started yet**:
   ```bash
   git branch -a | grep -i m2
   ls oracle_pure_M2_upload 2>/dev/null && echo "M2 upload folder exists" \
                                       || echo "M2 not yet implemented"
   ```

5. **If M2 not yet implemented**: execute the M2 prompt
   (`M2_IMPLEMENTATION_PROMPT.md`) — it's self-contained.

6. **If M2 implemented but not validated**: check
   `algos/oracle_pure/MILESTONE_M2_RESULTS.md` for status.

7. **If M2 validated and live-tested**: based on user feedback,
   determine if M3 (G3-Additive) is next or if a different focus is
   needed.

---

## 13. Critical things the user has said (preserve these)

Direct quotes from the user, verbatim, that you should remember:

- *"The exact elo and the losses to an opponent in an elo CANNOT be
  used to determine which algo performs better as elo can fluctuate
  between copies of the exact same algo due to the exact opponents
  given."*

- *"The biggest local indicator of performance improvement would be a
  win against snorkeldink-v3-1."*

- *"Although it should not waste too much compute and time testing
  against weaker opponents when already winning all the games."*

- *"You must make sure that the changes you are making are not
  hardcoded as this will lead to a regression."*

- *"You must also make sure that any changes you are making will not
  lead to a regression but are a genuine improvement over oracle_pure."*

- *"The improvement must mean that the new algo beats every other algo
  compared against for first ship that oracle_pure did."* (i.e., strict
  superset of original wins)

- *"Create an upload folder (not zip) to be uploaded and tested on the
  live leaderboard."*

These are guardrails. When proposing or evaluating any change, mentally
check it against all of these.

---

## 14. Open questions / decisions awaiting user input

If M2 ships and is live-validated:
- Does user want to proceed to G3-Additive (the M1 deferred fix)?
- Or directly to G2 / G6 from the v3 plan?
- Or something else (e.g., tackle the 3 unsolved counter-patterns)?

These should NOT be decided unilaterally. Wait for user direction
after M2 ships.

---

## TL;DR

oracle_pure is a search-driven Citadel Terminal algo currently on the
ladder at 1806-2103 ELO across 6 instances (3 oracle_pure + 3 M1Lite,
where M1Lite is a strict improvement). Replay analysis discovered a
trap bug where `defense:supports` template traps oracle's own offense
by sealing the wall row's launch gaps. M2 fixes this with a path-
viability BFS check (principled, generalizes) plus an ALT-OUTSIDE
SUPPORTS_BACK swap (necessary triage so the path check doesn't
eliminate ALL support templates). The implementation prompt is at
`M2_IMPLEMENTATION_PROMPT.md`. Compute overhead verified at 1.1% of
the 11s budget; sims/turn unchanged. Validation: Tier A 10/10 + Tier B
snorkeldink 2/2 + 5 new path-viability tests + telemetry sanity. If
gates pass, create `oracle_pure_M2_upload/` folder, commit on branch
`oracle-pure-M2`, hand off for live validation, do NOT merge to main.
3 unsolved counter-patterns (aa0/funnel-a, ashmit/funnel-crush-before,
Egil/siege) are deferred — they need a separate plan.

Now go.
