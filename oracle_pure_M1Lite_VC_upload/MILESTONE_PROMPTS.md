# Milestone Prompts — oracle_pure improvement plan

> **STATUS UPDATE (2026-04-26)**:
> - **M1 as originally specified (G7+G3) was REJECTED** by Tier A 10/10
>   rule (G3 caused 4/10 regression and broke G7's snorkeldink breakthrough).
>   See `MILESTONE_M1_RESULTS.md`.
> - **M1-Lite (G7 only) is being shipped** — see Part 1 of
>   `M1_LITE_SHIP_AND_G3_INVESTIGATE.md`.
> - **G3 is on a separate investigation track** — see Part 2 of
>   `M1_LITE_SHIP_AND_G3_INVESTIGATE.md` for the G3-Additive proposed fix.
> - **M2-M5 prompts below are unchanged in goal** but should:
>   - Treat M1-Lite (not M1) as the foundation
>   - Recognize that G3 may not be available; G2 (M2) may underperform without it

This file contains 5 self-contained prompts, one per milestone. Each prompt
is complete enough to give to a fresh Claude session without additional
context. Each milestone produces a NEW upload folder for live testing and
must pass strict local validation before being shipped.

The 8 fixes (G1-G8) are described in detail in `REVISED_IMPROVEMENT_PLAN.md`
and the dependency analysis is in `FINAL_CRITICAL_EVAL.md`. Read those
first.

---

## SHARED VALIDATION TEST SUITE — applies to every milestone

Each milestone runs the SAME tiered validation suite. The tiering exists so
we don't waste compute re-testing weak opponents we already beat
deterministically. The suite has THREE tiers, each with a strict purpose:

### Tier A — REGRESSION FLOOR (mandatory, must pass 100%)

The new milestone MUST beat every algo that the original `oracle_pure`
beats. **A single regression here = REJECT the milestone.** This is the
"strict superset of original wins" rule the user mandated.

**Required matches (10 matches, ~5 minutes total):**

| # | Opponent | Sides | Why |
|---|---|---|---|
| 1 | `algos/v13_second_ring/` | P1 + P2 | Sanity floor + cheap |
| 2 | `C1GamesStarterKit-master/python-algo/` | P1 + P2 | Tier-1 baseline |
| 3 | `algos/heuristic_v1/` | P1 + P2 | Tier-2 — proven hand-tuned |
| 4 | `research/finalist_repos/Terminal-Lostkids/python-2l-aet/` | P1 + P2 | Tier-3 — APAC 3rd place |
| 5 | `research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/` | P1 + P2 | Tier-3 — funnel-archetype |

Use absolute paths. Match-runner is `python3 C1GamesStarterKit-master/scripts/run_match.py <P1> <P2>`.
Verify NO `FailedToLoad` event in logs (relative-path bugs would silently
report Winner=2). Required result: **10W / 0L**.

If even ONE match flips W→L, the milestone is REJECTED.

### Tier B — BREAKTHROUGH SIGNAL (the snorkeldink test)

`oracle_pure` LOSES 0/2 vs `snorkeldink-v3-1` (documented in REPORT.md
§5.1). This is the strongest LOCAL indicator we have of a real algorithmic
breakthrough. **Any milestone that converts even ONE side to W is a
genuine improvement.** Both sides W = breakthrough.

**Required matches (2 matches, ~1.5 minutes):**

| # | Opponent | Sides | Result interpretation |
|---|---|---|---|
| 6 | `research/finalist_repos/terminal-c1/snorkeldink-v3-1/` | P1 + P2 | 0/2 = no progress; 1/2 = real progress; 2/2 = breakthrough |

This test does NOT block the milestone (oracle_pure already loses 0/2;
matching this is acceptable). It SIGNALS whether the milestone delivers
actual improvement vs just "doesn't regress."

### Tier C — TARGETED FUNNEL-LOSS REPRODUCTION

For changes that should affect funnel matchups (especially M2, M3, M5),
load specific game-states from saved replays at the tilt-turn and verify
the search produces meaningfully different output.

**Replays to test against (in `replays/oracle_pure_live/`):**

| State | Loaded turn | Original picked plan | Test |
|---|---|---|---|
| `oracle_pure_L_gencersarp_t51_15313336.replay` | T18 (just before first breach) | `defense:upg2` (no flank defense) | Did the search now pick a flank-aware plan? |
| `oracle_pure_L_aa0_t58_15313355.replay` | T26 (first 7-HP breach) | `defense:upg2` (didn't reinforce left flank) | Same |
| `oracle_pure_inst3_1860_L_ashmit_funnel-crush-before_t77_15313562.replay` | T48 (first 7-HP breach) | `defense:upg2` | Same |

Use a script like:
```python
from algos.oracle_pure.oracle_core.search import search
# Reconstruct GameState from the replay's deploy frame at the target turn
# Run search, log top-5 plans
# Assert: with the new fix, top-5 includes at least one flank-aware
# plan (gap-fill, anti-demo, or patch-corridor at the breached side)
```

The exact assertion depends on which milestone is being tested:
- **M2** (G2 sampling): assert opp_samples now include large-MP scout
  rushes from the breach-side launcher.
- **M3** (G1+G5): assert top-5 includes `defense:gap_at_*` or
  `defense:patch{2,3}_at_*` plans for the breached side.
- **M5** (G6): assert that after 3 turns of failed offense, the
  effectiveness tracker has accumulated meaningful penalty signal.

### Tier D — TELEMETRY HEALTH CHECK (per match)

For ONE representative Tier-A match (e.g., heuristic_v1 P1), capture
per-turn telemetry and verify:

- `sims_run/turn` averages within reasonable bounds (≥1500 local; ≥3000
  expected on server). For G7 (M1): NEW avg should be ≥1.5× OLD avg.
- `wall_clock/turn` stays under 11 seconds (watchdog safety).
- No `WATCHDOG fired` events in the log.
- No `Exception` events in the log.

### Compute budget for full validation

| Tier | Matches | Approx time | Required? |
|---|---|---|---|
| A — Regression floor | 10 | ~5 min | YES (gate) |
| B — Snorkeldink signal | 2 | ~1.5 min | YES (signal only) |
| C — Funnel reproduction | 0 (state-load) | ~30 sec | Only for M2, M3, M5 |
| D — Telemetry | 0 (parsed from A) | n/a | YES |

**Total per milestone: ~7 minutes of local matches.** Avoids re-testing
the dozens of human-player ladder opponents (we already know
deterministically how those go).

### Validation outcome matrix

| Tier A | Tier B | Outcome |
|---|---|---|
| 10/10 | 0/2 | PASS (no regression; no breakthrough either; ship) |
| 10/10 | 1/2 | PASS + PARTIAL BREAKTHROUGH (ship, expect ELO bump) |
| 10/10 | 2/2 | PASS + FULL BREAKTHROUGH (ship, expect significant ELO gain) |
| <10/10 | any | **REJECT** (regression on prior wins; the user's strict-superset rule) |

### Live ladder validation (manual, after upload)

After local Tier A passes and the upload folder is created, the user
manually uploads to terminal.c1games.com. Watch for:
- Live ELO not dropping >40 vs prior milestone after 10+ ranked matches
- Specifically: any new losses are NOT to opponents prior milestone beat

If live ELO regresses or new losses appear vs prior-beaten opps, the
milestone is REJECTED retroactively. The previous milestone's upload
remains the live algo. Document why in the failed milestone's results.

---

## PROMPT — Milestone 1: Foundation (G7 + G3)

You are implementing **Milestone 1** of the oracle_pure improvement plan.
This milestone ships TWO fixes with near-zero regression risk:

- **G7**: Hand-rolled fast state copy (replaces `deepcopy` in `search.py`,
  ~22× faster, enables more sims per turn)
- **G3**: Re-build opponent prior with breach-context (the prior currently
  has only `br0` buckets because `tools/build_opp_model.py` hardcoded
  `recent_breaches=0`; fixing this populates `br1_2` and `br3p` buckets)

**Read first:**
1. `algos/oracle_pure/REVISED_IMPROVEMENT_PLAN.md` (G3, G7 sections)
2. `algos/oracle_pure/FINAL_CRITICAL_EVAL.md` (validation gates)
3. The "SHARED VALIDATION TEST SUITE" section above
4. `algos/oracle_pure/oracle_core/search.py` (G7 lands)
5. `algos/oracle_pure/tools/build_opp_model.py` (G3 lands)

### Implementation steps

#### G7 — Fast state copy
1. In `oracle_core/search.py`, add helper `_fast_copy_state(s)` that
   returns a structurally-deep copy via dict-comprehension (no recursion):
   ```python
   def _fast_copy_state(s):
       return {
           "turn": s["turn"],
           "p1": dict(s["p1"]),
           "p2": dict(s["p2"]),
           "structures": [{**ss, "xy": list(ss["xy"])} for ss in s["structures"]],
           "mobiles": [
               {**mm, "xy": list(mm["xy"]),
                "spawn_xy": list(mm.get("spawn_xy", mm["xy"]))}
               for mm in s["mobiles"]
           ],
       }
   ```
2. Replace EVERY `deepcopy(base_state)` call in `search.py` (3 locations:
   phase-1 loop, phase-2 loop, depth-2 projection).
3. Write `tests/test_fast_copy.py` that:
   - Generates 5 sample base_states (varied turn states)
   - For each: verifies `deepcopy(s) == _fast_copy_state(s)` (deep equality)
   - Mutates copies independently and verifies original is unchanged
   - Times both — `_fast_copy_state` must be ≥10× faster
4. Run the test — must pass.

#### G3 — Re-build prior with breach context
1. In `tools/build_opp_model.py`, change bucket-key construction (lines
   ~147 and ~153) to track running breach counts:
   ```python
   # Inside the per-replay loop:
   running_breaches_against_p1 = 0
   running_breaches_against_p2 = 0
   for t in sorted(action_frames):
       bk_p1 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                          recent_breaches=running_breaches_against_p1)
       bucket_counters[bk_p1][sig_opp_against_p1] += 1
       bk_p2 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                          recent_breaches=running_breaches_against_p2)
       bucket_counters[bk_p2][sig_opp_against_p2] += 1
       events = action_frames[t]
       for b in events.get('breach', []):
           if not b or len(b) < 5: continue
           owner = int(b[4])
           if owner == 1: running_breaches_against_p2 += 1
           else: running_breaches_against_p1 += 1
   ```
2. Back up old prior: `cp data/opp_model_priors.json data/opp_model_priors.OLD.json`
3. Re-run: `python3 tools/build_opp_model.py`
4. Verify with assertion script that NEW prior has buckets keyed `br0`,
   `br1_2`, AND `br3p`.
5. Write `tests/test_prior_parity.py` that asserts: every bucket key in
   OLD must exist in NEW (strict superset; no information loss).

### Validation (per the SHARED TEST SUITE above)

1. Component tests: `python3 tests/test_components.py` — all pass.
2. New tests: `test_fast_copy.py`, `test_prior_parity.py` — all pass.
3. **Tier A regression floor: 10/10 required.** Run all 10 matches per
   the table above. Any L = REJECT M1.
4. **Tier B snorkeldink signal:** run vs snorkeldink-v3-1 P1 + P2.
   Record result; do NOT block on outcome (M1 is bug-fixes, not expected
   to convert snorkeldink).
5. **Tier D telemetry:** in the heuristic_v1 P1 match, log per-turn
   `sims_run`. Assert: average is ≥1.5× the pre-G7 baseline (G7's
   benefit). Capture max wall-clock (must be <11s).
6. Tier C is SKIPPED for M1 (no behavioral changes that should affect
   funnel state evaluation).

### Upload + commit

If validation passes:
1. Create `oracle_pure_M1_upload/` (copy from `oracle_pure_upload/` and
   apply M1 changes including new `data/opp_model_priors.json`).
2. Path-portability test: copy to `/tmp/oracle_pure_M1_test/`, run match
   vs v13_second_ring, verify Winner=1 + no FailedToLoad.
3. Commit on branch `oracle-pure-M1`:
   ```
   git checkout -b oracle-pure-M1
   git add <changed files>
   git commit -m "M1: G7 fast copy + G3 rebuild prior — pure bug/perf fixes"
   ```
4. Write `algos/oracle_pure/MILESTONE_M1_RESULTS.md`:
   - All Tier A results (W/L per match)
   - Tier B snorkeldink results (W/L per side)
   - Tier D telemetry comparison (sims/turn before vs after)
   - Live testing instructions for the user
5. Wait for user to upload and report live ladder ELO change.

### Outcome decision tree

- Tier A 10/10 + Tier B any: PASS, ship M1, await live result.
- Tier A <10/10: REJECT, document, do NOT ship.
- After live: ELO drops >40 → REJECT retroactively, fall back to live
  oracle_pure_upload, investigate.

---

## PROMPT — Milestone 2: Sampling (G8 + G2)

You are implementing **Milestone 2**. **M1-Lite must have passed local
validation AND live ladder confirmation** (≥10 ranked matches without
ELO regression). Read `MILESTONE_M1_RESULTS.md` AND
`M1_LITE_SHIP_AND_G3_INVESTIGATE.md` first.

This milestone ships:
- **G8**: Pass breach TILES (not just count) from `algo_strategy` →
  `search` → `opponent_model.sample()`. Plumbing only.
- **G2**: Use opp's OBSERVED MP-spend posterior to size adversarial
  samples. Replaces hand-tuned magic constants with data-derived weights.
  **Cap weights at 3.0** to prevent monopolization.

**Important — G3 dependency note:** G2's effectiveness depends on whether
G3 (or G3-Additive) populated the br1_2/br3p prior buckets. Three
scenarios:
1. **If G3-Additive shipped before M2**: G2 works as designed; full
   benefit expected.
2. **If only M1-Lite shipped (no G3)**: G2 still works on the in-game
   posterior, but the prior contribution to br1_2/br3p is empty. G2 will
   only kick in after ≥3 in-game opp observations populate the
   posterior. Expect smaller improvement.
3. **If G3-Additive failed and was abandoned**: same as case 2.
Document which scenario applies in MILESTONE_M2_RESULTS.md.

### Implementation steps

#### G8 — Thread breach tiles
1. In `algo_strategy.py:on_turn`, add `recent_breach_tiles=self.recent_breaches[-6:]`
   to the `search()` call (alongside the existing `recent_breaches=`).
2. In `search.py`, add `recent_breach_tiles=()` parameter to `search()`.
   Forward to `opp_model.sample(...)` calls.
3. In `opponent_model.py:sample()`, add `recent_breach_tiles=None`
   parameter (default None for backward compat). Initially DON'T USE it
   (G2 will).

#### G2 — Posterior-driven sample sizing
1. In `opponent_model.py`, add `_opp_spend_distribution(bk, opp_mp, config)`
   helper (full code in `REVISED_IMPROVEMENT_PLAN.md` G2 section).
   **Critical: cap returned weights at 3.0** to match existing adversarial
   sample weights and prevent monopolization.
2. In `OpponentModel.sample()`, prepend `_opp_spend_distribution(bk, opp_mp, config)`
   results to `sigs_with_weights` BEFORE the existing fixed-weight
   adversarial samples.
3. The existing fixed-weight samples REMAIN as fallback for cold-start.

### Validation

1. Component tests pass.
2. New `tests/test_g2_sampling.py`:
   - Manually populate `model.posterior['t10_19|mp8_12|mp0_3|br0']` with
     test signatures
   - Call `sample(...)` and assert posterior sigs appear with capped weights
   - Assert empty posterior falls back to existing samples (no regression)
3. **Tier A 10/10 required** — this is the highest-risk M2 gate. Posterior
   sampling could distort opp predictions, potentially flipping wins.
4. **Tier B snorkeldink:** Re-run; this is where G2's improvement might
   first surface (snorkeldink banks SP/MP — the posterior should now
   capture this if G3 populated br1_2/br3p priors).
5. **Tier C funnel reproduction:** Load the gencersarp T18 state. Call
   `opp_model.sample()` and assert: returned plans now include at least
   one demolisher signature (which the prior would have missed). If no
   demolisher sigs appear, G2 isn't doing what it should.
6. Tier D telemetry on heuristic_v1 P1 — wall-clock unchanged.

### Upload + commit
- `oracle_pure_M2_upload/` (copy M1 + apply M2 changes)
- Path-portability test
- Branch `oracle-pure-M2`
- Write `MILESTONE_M2_RESULTS.md`
- Wait for live validation

### Outcome decision tree
- Tier A 10/10 + Tier C passes: PASS, ship.
- Tier A 10/10 + Tier C fails: G2 not actually working as designed —
  diagnose before shipping. Could be a posterior-population bug.
- Tier A <10/10: REJECT.
- Tier B converts a snorkeldink side: bonus signal; document.

---

## PROMPT — Milestone 3: Defense templates (G1 + G5)

You are implementing **Milestone 3**. **M1 + M2 must have passed.**
This is the **highest-risk milestone** — both fixes add new defense
templates that compete with existing templates.

This milestone ships:
- **G1**: Coverage-gap proposer — derives gap tiles from LIVE board state.
  **GATED to fire only after T8 AND when at least one breach has occurred**
  (avoids early-game noise).
- **G5**: Multi-intensity patch family. **Intensity 2/3 GATED to fire
  only when ≥3/4 anchor turrets exist** (skeleton built).

### Implementation steps

#### G1 — Coverage-gap proposer
1. In `oracle_core/enumerator.py`, add `_enumerate_coverage_gap_templates(...)`
   per the code sketch in `REVISED_IMPROVEMENT_PLAN.md` G1 section, with
   the GATING:
   ```python
   def _enumerate_coverage_gap_templates(game_state, config, sp_budget,
                                          recent_breaches,
                                          n_gap_proposals=3):
       turn = getattr(game_state, 'turn_number', 0)
       if turn < 8: return []           # GATING
       if not recent_breaches: return []  # GATING
       # ... rest of impl
   ```
2. The proposer must NOT propose walls (turrets only) to avoid blocking
   oracle's own launchers.
3. Add to `_enumerate_defense_templates()` near the end:
   ```python
   plans.extend(_enumerate_coverage_gap_templates(
       game_state, config, sp_budget, recent_breaches))
   ```

#### G5 — Multi-intensity patch family
1. In `enumerator.py`, REPLACE the existing `defense:patch{1,2}` logic
   (lines ~436-454) with the multi-intensity emitter (full code in
   `REVISED_IMPROVEMENT_PLAN.md` G5 section), with the GATING:
   ```python
   y11_turret_count = sum(1 for t in ANCHOR_TURRETS
                           if _existing_struct(game_state, t[1], t[2]))
   skeleton_built = y11_turret_count >= 3
   for intensity in (1, 2, 3):
       if intensity >= 2 and not skeleton_built:
           continue  # GATING
       # ... emit patch
   ```

### Validation (HIGHEST risk)

1. Component tests pass.
2. **Tier A 10/10 — run twice independently**. The added templates could
   distract search; verify deterministic 10/10 on both runs.
3. **Tier B snorkeldink:** run again. If snorkeldink converts even one
   side to W (after M2 was 0/2), this milestone is delivering.
4. **Tier C funnel reproduction:**
   - Load gencersarp T18 state. Verify search top-5 includes at least
     one `defense:gap_at_*` OR `defense:patch{2,3}_at_*` plan.
   - Load aa0/funnel-a T26 state. Same check.
   - Load ashmit T48 state. Same check.
   - For each: log whether the gap/patch plan was actually picked
     (top-1) or just available (top-5).
5. **Negative test:** load a boss-win state (R2 Infiltrator T10) and
   assert that gap templates do NOT fire (gating should suppress them
   when no breach has occurred).
6. **Telemetry budget:** per-turn `candidates_total` should remain
   ≤2700. New templates add ≤9 plans; the cap is 2500 in shipping
   config; verify enumeration doesn't bust.

### Upload + commit
- `oracle_pure_M3_upload/`
- Path-portability test
- Branch `oracle-pure-M3`
- `MILESTONE_M3_RESULTS.md`
- Live test ≥15 ranked matches (more than other milestones; higher risk)

### Outcome decision tree
- Tier A 10/10 + Tier C plans available + Tier C plans picked at least
  once: PASS, breakthrough probable.
- Tier A 10/10 + Tier C plans available but never picked: PASS but
  marginal. The fix is structurally correct but value function still
  prefers other plans. M3 ships but improvement may be small.
- Tier A 10/10 + Tier C plans NOT available: gap detector failing —
  diagnose before shipping.
- Tier A <10/10: REJECT.
- Tier A passes but live regresses >30 ELO: REJECT retroactively, fall
  back to M2 live algo.

---

## PROMPT — Milestone 4: Compute scaling (G4)

You are implementing **Milestone 4**. **M1 must have passed.** M2 + M3
preferred but optional (M4 builds on M1's G7 freed time).

This milestone ships:
- **G4**: Adaptive compute budget — when phase-1+phase-2 complete with
  >40% of budget remaining, run an additional phase-3 that re-evaluates
  the top-10 candidates with double `k_opp`. The expanded sampling
  improves top-pick reliability. **MARGIN GATE: phase-3 only changes
  the pick if the new winner has ≥10% better score** (prevents
  low-confidence flips).

### Implementation steps

1. In `oracle_core/search.py`, after the phase-2 evaluation loop, add
   the phase-3 logic (full code in `REVISED_IMPROVEMENT_PLAN.md` G4
   section), with the MARGIN GATE.
2. Add telemetry field `tel.used_phase3 = True` for matches where
   phase-3 fired and changed the winner.

### Validation

1. Component tests pass.
2. **Tier A 10/10 required.** With the margin gate, phase-3 should NOT
   change any bar match's outcome.
3. **Tier B snorkeldink:** run; phase-3's improved top-pick reliability
   may matter here.
4. **Tier D telemetry:**
   - In the heuristic_v1 P1 match, log how often phase-3 fires (should
     be in matches where pre-M4 phase-2 finished fast — typically early
     turns).
   - When phase-3 fires, log whether it changed the winner (the margin
     gate should make this rare on bar matches).
5. Sanity test: in a synthetic state where phase-2 has clear winner
   (margin > 100 utility), phase-3 must NOT override.
6. Sanity test: in a synthetic state where top-2 phase-2 are within
   1% utility, phase-3 should re-rank confidently.

### Upload + commit
- `oracle_pure_M4_upload/`
- Path-portability test
- Branch `oracle-pure-M4`
- `MILESTONE_M4_RESULTS.md`
- Live test ≥10 matches; expect modest improvement.

---

## PROMPT — Milestone 5: Behavioral (G6) — OPTIONAL

You are implementing **Milestone 5** — the OPTIONAL behavioral fix.
**M1 + M2 must have passed.** **M3 strongly preferred** (G6 needs
defensive options G1+G5 provide; otherwise it just blocks bad plans
without giving good alternatives).

**Skip this milestone if M1+M2+M3+M4 have already delivered the desired
ELO gain.** This is the riskiest fix.

This milestone ships:
- **G6**: Offense-effectiveness tracker by `(archetype, launcher_zone)`.
  Specifically targets the demo-siege-v2 failure mode where oracle gets
  stuck spamming useless interceptor clusters for 30+ turns. **Penalty
  is CONSERVATIVE: -15 max** (vs the prior plan's -30) — too small risks
  no effect; too large risks blocking legitimate retries.

### Implementation steps

1. Create `oracle_core/offense_tracker.py` (full code in
   `REVISED_IMPROVEMENT_PLAN.md` G6 section).
2. Wire it into `algo_strategy.py`:
   - Init in `__init__`
   - On every breach event by US (player_id matches us in
     `on_action_frame`), record `(archetype, launcher_xy, mp_spent, hp_dealt)`
3. Pass to `value.evaluate()` via search → value pipeline.
4. In `value.py`, add the CONSERVATIVE penalty:
   ```python
   if plan and plan.has_mobiles() and offense_tracker is not None:
       for op in plan.mobile_ops():
           expected = offense_tracker.expected_dmg_per_mp(
               plan.archetype, op.xy, current_turn=state_dict["turn"]
           )
           if expected < 0.05 and plan.mp_cost >= 3:
               score -= 15 * min(plan.mp_cost / 5.0, 2.0)  # MAX -15
   ```

### Validation

1. Component tests pass.
2. **Tier A 10/10 BOTH SIDES.** This penalty could over-correct against
   bar opponents that oracle currently beats with offense needing 2-3
   setup attempts. CRITICAL: any flip = REJECT.
3. **Tier B snorkeldink:** run; demo-siege-v2 isn't local but
   snorkeldink IS the closest analog (both have static defense that
   oracle can't crack repeatedly).
4. **Tier C synthetic test:**
   - Construct a synthetic match log where oracle's offense fails 3
     turns straight from launcher (3,17)
   - Verify the tracker has expected_dmg_per_mp < 0.05 for that
     (archetype, zone)
   - Verify a NEW plan with same archetype + DIFFERENT zone (e.g.,
     (24,10) instead of (3,17)) is NOT penalized
5. Telemetry: log `expected_dmg_per_mp` for every plan considered in
   the heuristic_v1 P1 match; verify the tracker has accumulated stats
   by T20+.

### Upload + commit
- `oracle_pure_M5_upload/`
- Path-portability test
- Branch `oracle-pure-M5`
- `MILESTONE_M5_RESULTS.md`
- Live test ≥15 matches; especially watch demo-siege-v2 results.
- If demo-siege-v2 still loses: tune penalty up to -25 (still
  conservative); if oracle's other matches regress, tune DOWN to -10
  or revert.

---

## Master execution checklist (REVISED 2026-04-26)

For each milestone, in order:

- [x] **M1 (original G7+G3)**: REJECTED. See MILESTONE_M1_RESULTS.md.
- [ ] **M1-Lite (G7 only)**: ship per Part 1 of M1_LITE_SHIP_AND_G3_INVESTIGATE.md.
      Tier A 10/10 + Tier B 2/2 (snorkeldink BREAKTHROUGH).
- [ ] **G3-Investigate (G3-Additive variant)**: per Part 2 of
      M1_LITE_SHIP_AND_G3_INVESTIGATE.md. If passes → ship as M1.5.
      If fails → continue M2 without G3.
- [ ] **M2**: Sampling (G8 + G2) — requires M1-Lite shipped. Effectiveness
      depends on G3-Additive status (see scenario notes in M2 prompt above).
- [ ] **M3**: Defense templates (G1 + G5) — requires M2. **High
      regression risk.**
- [ ] **M4**: Compute scaling (G4) — requires M1-Lite (G7); M2 preferred.
- [ ] **M5**: Behavioral (G6) — OPTIONAL. Skip if M1-M4 already met
      target.

### Decision points

After M2 lands successfully (live confirmed):
- If snorkeldink-v3-1 is now 1/2 or 2/2: **major signal** — proceed to M3 with confidence.
- If snorkeldink-v3-1 still 0/2 but live ELO up >30: M2 worked, proceed to M3.
- If live ELO unchanged or down: M2 passed local but not generalizing; pause and reassess before M3.

After M3 lands successfully:
- If snorkeldink-v3-1 converted: M3 is delivering its purpose.
- If snorkeldink-v3-1 still 0/2: M3 didn't address the breakthrough; M4 is still worth doing for general improvement, M5 may be the missing piece.

### Final retrospective

After all milestones (or whatever subset passed), write
`POST_PLAN_RETROSPECTIVE.md` documenting:
- Which milestones landed (and which were rejected and why)
- Total local + live ELO delta
- Snorkeldink-v3-1 final state (still 0/2 = future work needed)
- Surviving improvement opportunities for a future plan
