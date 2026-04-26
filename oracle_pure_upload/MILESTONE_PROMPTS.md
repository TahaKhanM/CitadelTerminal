# Milestone Prompts — oracle_pure improvement plan

This file contains 5 self-contained prompts, one per milestone. Each prompt
is complete enough to give to a fresh Claude session without additional
context. Each milestone produces a NEW upload folder for live testing and
must pass strict local validation before being shipped.

**Critical rule (apply to all prompts):** If a milestone regresses the
local 10/10 bar against (v13_second_ring, python-algo, heuristic_v1,
Lostkids/python-2l-aet, funnel_INTER), the milestone is REJECTED. Do not
ship the upload folder. Document what regressed and why in
`MILESTONE_M{N}_RESULTS.md`. Do not proceed to the next milestone until
the failed one is either fixed or formally abandoned.

The 8 fixes (G1-G8) are described in detail in `REVISED_IMPROVEMENT_PLAN.md`
and the dependency analysis is in `FINAL_CRITICAL_EVAL.md`. Read those
first.

---

## PROMPT — Milestone 1: Foundation (G7 + G3)

You are implementing **Milestone 1** of the oracle_pure improvement plan.
This milestone ships TWO fixes that are pure bug fixes / pure perf with
near-zero regression risk:

- **G7**: Hand-rolled fast state copy (replaces `deepcopy` in `search.py`,
  ~22× faster, enables more sims per turn)
- **G3**: Re-build opponent prior with breach-context (the prior currently
  has only `br0` buckets because `tools/build_opp_model.py` hardcoded
  `recent_breaches=0`; fixing this populates `br1_2` and `br3p` buckets)

**Read first:**
1. `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/REVISED_IMPROVEMENT_PLAN.md` (G3, G7 sections)
2. `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/FINAL_CRITICAL_EVAL.md` (validation gates)
3. `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/oracle_core/search.py` (where G7 lands)
4. `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/tools/build_opp_model.py` (where G3 lands)
5. `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/oracle_pure/data/opp_model_priors.json` (the artifact G3 rebuilds)

### Implementation steps

#### G7 — Fast state copy
1. In `oracle_core/search.py`, add a helper:
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
   phase-1 loop, phase-2 loop, depth-2 projection) with `_fast_copy_state(base_state)`.
3. Write `algos/oracle_pure/tests/test_fast_copy.py` that:
   - Loads 5 sample base_states (from running `adapt_game_state` on
     varied turn states)
   - For each: copies via `deepcopy` and via `_fast_copy_state`
   - Asserts both copies are deeply equal (use `==` or recursive compare)
   - Mutates the copies independently (apply `_apply_plan`) and asserts the
     ORIGINAL is unchanged
   - Times both: must show `_fast_copy_state` is ≥10× faster on average
4. Run `python3 algos/oracle_pure/tests/test_fast_copy.py` — all checks
   must pass. If any fail, do NOT proceed.

#### G3 — Re-build prior with breach context
1. In `tools/build_opp_model.py`, change the bucket-key construction (lines
   ~147 and ~153) to track running breach counts:
   ```python
   # Inside the per-replay loop, after deploys = ... and actions = ...
   running_breaches_against_p1 = 0
   running_breaches_against_p2 = 0
   for t in sorted(action_frames):
       # Use CURRENT count (events from THIS turn happen AFTER deploy)
       bk_p1 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                          recent_breaches=running_breaches_against_p1)
       bucket_counters[bk_p1][sig_opp_against_p1] += 1
       bk_p2 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                          recent_breaches=running_breaches_against_p2)
       bucket_counters[bk_p2][sig_opp_against_p2] += 1
       # Update counts AFTER recording observations
       events = action_frames[t]
       for b in events.get('breach', []):
           if not b or len(b) < 5: continue
           owner = int(b[4])
           if owner == 1:
               running_breaches_against_p2 += 1
           else:
               running_breaches_against_p1 += 1
   ```
2. Re-run the builder on all 427 ranked replays: `python3 algos/oracle_pure/tools/build_opp_model.py`.
3. Verify the new prior has bucket coverage across all 3 breach bands:
   ```python
   import json
   d = json.load(open('algos/oracle_pure/data/opp_model_priors.json'))
   from collections import Counter
   bands = Counter(k.split('|')[-1] for k in d.keys())
   print(bands)  # Must show all of {br0, br1_2, br3p}
   assert 'br1_2' in bands and 'br3p' in bands, 'G3 fix failed'
   ```
4. Write `algos/oracle_pure/tests/test_prior_parity.py` that:
   - Loads the OLD prior (back it up first as `opp_model_priors.OLD.json`)
   - Loads the NEW prior
   - For every bucket key in OLD, asserts NEW has the same key OR a
     substring match (e.g., `t10_19|mp4_7|mp0_3|br0` should be present
     in NEW with same signatures)
   - This is a strict-superset test: NEW prior must have AT LEAST the
     same coverage as OLD plus the new br1_2/br3p buckets

### Validation gate (must pass before shipping)

1. **Component tests:** `python3 algos/oracle_pure/tests/test_components.py`
   — all 8 tests must pass.
2. **Fast-copy parity test:** new `test_fast_copy.py` — all checks pass.
3. **Prior parity test:** new `test_prior_parity.py` — strict superset.
4. **Local ladder both sides 10/10:** run vs (v13_second_ring,
   python-algo from C1GamesStarterKit, heuristic_v1, Lostkids/python-2l-aet,
   funnel_INTER). 10/10 wins required (5 opps × P1 + P2 = 10 matches).
5. **Search telemetry sanity:** in a heuristic_v1 match, log per-turn
   `sims_run` — must be ≥1.5× the pre-fix average (G7 should let us run
   more sims in the same time budget).

If ANY of (1)-(5) fails, REJECT M1, document in
`algos/oracle_pure/MILESTONE_M1_RESULTS.md` what failed, do NOT create
the upload folder.

### Upload folder creation (only after validation passes)

1. Create `oracle_pure_M1_upload/` at the repo root.
2. Copy: `algo_strategy.py`, `algo.json`, `run.sh`, all of `oracle_core/`,
   `bundled_sim_rs/`, `vendored_sim/`, `gamelib/`, `data/` (with the new
   `opp_model_priors.json`), `sim_bridge.py` if exists.
3. Test the upload folder by extracting to `/tmp/oracle_pure_M1_test/`
   and running a match vs v13_second_ring. Must win, no FailedToLoad.
4. Write `algos/oracle_pure/MILESTONE_M1_RESULTS.md`:
   - Validation results (all 5 gates with pass/fail + numbers)
   - Telemetry comparison (sims/turn before vs after)
   - Live testing instructions (manual upload to terminal.c1games.com)
   - Expected ELO movement: +10-25 (cautious estimate)
5. Commit on a branch `oracle-pure-M1`. Do NOT merge to main yet — wait
   for live ladder validation.

### Live ladder validation (manual step by user)

User uploads `oracle_pure_M1_upload/` to terminal.c1games.com as a new
algo. Wait for ≥10 ranked matches. Note the ELO trajectory.

- If ELO ≥ prior baseline: M1 succeeded; merge `oracle-pure-M1` to main.
- If ELO drops >40 vs prior: M1 regressed live. Investigate before M2.

### Output

Final report in `MILESTONE_M1_RESULTS.md`. Communicate concisely whether
M1 passed or failed and what (if anything) needs to change.

---

## PROMPT — Milestone 2: Sampling (G8 + G2)

You are implementing **Milestone 2** of the oracle_pure improvement plan.
**M1 must be successfully validated before starting M2.** Read
`algos/oracle_pure/MILESTONE_M1_RESULTS.md` first; if M1 was rejected, do
NOT proceed.

This milestone ships TWO coupled fixes:
- **G8**: Pass breach TILES (not just count) from `algo_strategy` →
  `search` → `opponent_model.sample()`. Plumbing only.
- **G2**: Use opp's OBSERVED MP-spend posterior (now properly populated by
  G3 from M1) to size adversarial samples. Replaces the prior plan's
  hand-tuned magic constants with data-derived weights.

**Read first:**
1. `MILESTONE_M1_RESULTS.md` (verify M1 passed)
2. `REVISED_IMPROVEMENT_PLAN.md` (G2, G8 sections)
3. `oracle_core/opponent_model.py` (where G2 lands, lines 292-356)
4. `oracle_core/search.py` (where G8 threads)
5. `algo_strategy.py` (where G8 originates)

### Implementation steps

#### G8 — Thread breach tiles to opp_model
1. In `algo_strategy.py:on_turn`, change the search call to add
   `recent_breach_tiles=self.recent_breaches[-6:]`. The `recent_breaches`
   already passes the LAST 6 tiles; just rename or alias.
2. In `search.py`, add `recent_breach_tiles=()` parameter. Forward it to
   `opp_model.sample(...)` calls.
3. In `opp_model.sample(...)`, add `recent_breach_tiles=None` parameter
   (default None for backwards compat). Initially DON'T USE it (G2 will).

#### G2 — Posterior-driven sample sizing
1. In `opponent_model.py`, add a new helper method:
   ```python
   def _opp_spend_distribution(self, bk: str, opp_mp: float, config) -> list:
       """Return [(ActionSignature, weight)] sized to current opp_mp from
       observed posterior. NO magic threshold."""
       post = self.posterior.get(bk, Counter())
       if not post:
           return []
       samples = []
       MAX_WEIGHT = 3.0  # cap to prevent monopolization (per G2 critique)
       for sig, n in post.items():
           cost = self._sig_mp_cost(sig, config)
           # Scale to current opp_mp if observed cost differs significantly
           if cost > 1e-6 and opp_mp > 1e-6:
               ratio = opp_mp / cost
               # Only re-scale if ratio is sensible (avoid huge multipliers)
               if 0.5 <= ratio <= 3.0:
                   scaled_sig = ActionSignature(
                       scout_count=int(sig.scout_count * ratio),
                       demo_count=int(sig.demo_count * ratio),
                       int_count=int(sig.int_count * ratio),
                       scout_launcher=sig.scout_launcher,
                       demo_launcher=sig.demo_launcher,
                       int_launcher=sig.int_launcher,
                   )
               else:
                   scaled_sig = sig  # don't re-scale outliers
           else:
               scaled_sig = sig
           weight = min(float(n), MAX_WEIGHT)
           samples.append((scaled_sig, weight))
       return samples
   ```
2. In `OpponentModel.sample()`, prepend `_opp_spend_distribution(bk, opp_mp, config)`
   results to `sigs_with_weights` BEFORE the existing adversarial samples.
3. The existing fixed-weight scout/demo samples REMAIN as fallback for
   cold-start (when posterior is empty).

### Validation gate

1. Component tests pass.
2. New unit test `tests/test_g2_sampling.py`:
   - Manually populate `model.posterior['t10_19|mp8_12|mp0_3|br0']` with
     a few signatures
   - Call `model.sample(...)` and assert returned plans include sigs
     proportional to posterior counts
   - Assert WEIGHT CAP holds (no signature returns weight > 3.0)
   - Assert with empty posterior, returns existing adversarial samples
3. Local ladder: 10/10 vs the 5 bar opps both sides. **CRITICAL**: this
   is the highest-risk gate; if any match flips, REJECT M2.
4. Funnel-replay re-evaluation: load 1 funnel-loss state (e.g.,
   gencersarp at T18 deploy frame), feed through search, log the opp
   samples. Assert that with G2 enabled, the samples now include at
   least one demolisher signature (which the prior would have missed).

### Upload folder + live testing
1. Create `oracle_pure_M2_upload/` (copy from M1, apply M2 changes).
2. Path-portability test in `/tmp/`.
3. Write `MILESTONE_M2_RESULTS.md`.
4. Commit branch `oracle-pure-M2`.
5. Manual live upload, ≥10 ranked matches, ELO check.

If regression vs M1 baseline: REJECT M2. If passes: merge to main.

---

## PROMPT — Milestone 3: Defense templates (G1 + G5)

You are implementing **Milestone 3** of the oracle_pure improvement plan.
**M1 AND M2 must be successfully validated.** This is the **highest-risk
milestone** — both fixes add new defense templates that compete with
existing templates and could distract the search.

This milestone ships:
- **G1**: Coverage-gap proposer — derives gap tiles from LIVE board state,
  proposes turret-pair templates to fill them. Generalizes to any breach
  pattern (not hardcoded to known losses).
- **G5**: Multi-intensity patch family (1/2/3 turrets) — search picks
  intensity from SP available.

Both fixes are GATED to prevent early-game noise.

### Implementation steps

#### G1 — Coverage-gap proposer
1. In `oracle_core/enumerator.py`, add helper function (refer to
   `REVISED_IMPROVEMENT_PLAN.md` G1 section for the exact code sketch):
   ```python
   def _enumerate_coverage_gap_templates(game_state, config, sp_budget,
                                          recent_breaches,
                                          n_gap_proposals=3):
       """..."""
       # GATING: only fire after T8 (skeleton built) AND only when
       # at least one recent breach has occurred. This prevents
       # early-game noise where every tile has cov=0.
       turn = getattr(game_state, 'turn_number', 0)
       if turn < 8:
           return []
       if not recent_breaches:
           return []
       # ... rest of impl per REVISED_IMPROVEMENT_PLAN.md G1
   ```
2. The PROPOSED OFFSETS for filling a gap MUST be geometric (one tile
   back, side-mirrored). NO hardcoded tile lists.
3. The proposer must NOT propose walls — only turrets — to avoid
   blocking oracle's own launchers.
4. Add to `_enumerate_defense_templates()` near the end:
   ```python
   plans.extend(_enumerate_coverage_gap_templates(
       game_state, config, sp_budget, recent_breaches))
   ```

#### G5 — Multi-intensity patch family
1. In `oracle_core/enumerator.py`, REPLACE the existing `defense:patch{k}`
   logic (lines ~436-454) with a multi-intensity emitter:
   ```python
   if recent_breaches:
       breach_counter = Counter(recent_breaches)
       for (bx, by), count in breach_counter.most_common(2):
           if count < 1:
               continue
           # Intensity 1: cheap, always available
           # Intensity 2: medium SP
           # Intensity 3: high SP, includes upgrades — only if existing
           #              skeleton turrets at y=11 row are mostly built
           y11_turret_count = sum(1 for t in ANCHOR_TURRETS
                                   if _existing_struct(game_state, t[1], t[2]))
           skeleton_built = y11_turret_count >= 3  # ≥3/4 anchors
           
           for intensity in (1, 2, 3):
               if intensity >= 2 and not skeleton_built:
                   continue  # GATING: don't push high-intensity if
                             # skeleton isn't up
               # Geometric offsets — opp-agnostic
               offsets = [
                   (1 if bx < 14 else -1, 1),
                   (0, 2),
                   (-1 if bx < 14 else 1, 1),
                   (0, 0),
               ]
               atoms = []
               for ox, oy in offsets[:intensity * 2]:
                   tx, ty = bx + ox, by + oy
                   if 0 <= tx < 28 and 0 <= ty < 14:
                       if oy == 0:
                           atoms.append((WALL_IDX, tx, ty))
                       else:
                           atoms.append((TURRET_IDX, tx, ty))
               upg_atoms = ([(t[1], t[2]) for t in atoms if t[0] == TURRET_IDX]
                            if intensity == 3 else [])
               p = _build_defense_plan(
                   f"defense:patch{intensity}_at_{bx}_{by}",
                   atoms + ANCHOR_TURRETS,
                   game_state, config, sp_budget,
                   upgrades=upg_atoms,
                   archetype=f"reactive_patch_i{intensity}",
               )
               if p: plans.append(p)
   ```

### Validation gate (HIGHEST risk milestone)

1. Component tests pass.
2. **Critical: extreme regression sensitivity check.** Run the local ladder
   3× independently (same matches will deterministically replay, but if any
   variance creeps in, catch it). 10/10 wins required ALL THREE TIMES.
3. **Funnel-replay re-evaluation:** for each of 3 funnel-loss states
   (gencersarp T18, aa0/funnel-a T26, ashmit T48), feed through search and
   log top-3 plans. Assert that at least ONE coverage-gap or patch-intensity-2+
   plan appears in top-5 (NOT necessarily picked, but available).
4. **Negative test:** for boss-win states (R2 Infiltrator T10), assert
   coverage-gap templates DON'T fire (gating should suppress them when
   no breach has occurred).
5. **Telemetry:** per-turn `candidates_total` should be ≤2700 (we added
   ~3-9 templates total; cap is 2500 in shipping config — ensure new
   templates don't push us out of budget).

If ANY local match flips W→L, OR if the gating fails (gap templates
fire when they shouldn't), REJECT M3.

### Upload + live testing
- `oracle_pure_M3_upload/`
- `MILESTONE_M3_RESULTS.md`
- Branch `oracle-pure-M3`
- Manual live test, ≥15 ranked matches (more than other milestones to
  build confidence given higher risk)
- ELO must not drop >30 vs M2 baseline

If live regression: REJECT M3, document, fall back to M2 as the live
algo.

---

## PROMPT — Milestone 4: Compute scaling (G4)

You are implementing **Milestone 4** of the oracle_pure improvement plan.
**M1, M2 must be successfully validated. M3 is preferred but optional**
(if M3 was rejected, M4 still works on top of M2).

This milestone ships:
- **G4**: Adaptive compute budget — when phase-1+phase-2 complete with
  >40% of budget remaining, run an additional phase-3 that re-evaluates
  the top-10 candidates with double k_opp. The expanded sampling improves
  top-pick reliability.

### Implementation steps

1. In `oracle_core/search.py`, after the phase-2 evaluation loop:
   ```python
   # === Phase-3: adaptive expansion when budget remains ===
   phase12_elapsed = time.time() - t0
   if (phase12_elapsed < time_budget_s * 0.4
           and len(final_scores) >= 10):
       top_for_phase3 = final_scores[:10]
       k_opp_phase3 = min(k_opp * 2, 16)
       opp_samples_phase3 = opp_model.sample(
           game_state, our_mp=our_mp, opp_mp=opp_mp,
           recent_breaches=len(recent_breaches),
           k=k_opp_phase3, opp_player=2, config=config,
       )
       phase3_scores = []
       for score, cand in top_for_phase3:
           if time.time() > deadline: break
           sample_scores = []
           for opp_sample in opp_samples_phase3:
               if time.time() > deadline: break
               try:
                   sd = _fast_copy_state(base_state)
                   _apply_plan(cand, sd, my_player=1, config=config)
                   _apply_plan(opp_sample, sd, my_player=2, config=config)
                   post = _run_sim(sd, config_path)
                   tel.sims_run += 1
                   sample_scores.append(evaluate(post, my_player=1))
               except Exception:
                   continue
           if sample_scores:
               phase3_scores.append(
                   (sum(sample_scores) / len(sample_scores), cand))
       
       if phase3_scores:
           phase3_scores.sort(key=lambda t: -t[0])
           # MARGIN GATE: only override phase-2 winner if phase-3
           # winner has ≥10% better score (prevents low-confidence flips)
           p2_winner_score, p2_winner_plan = final_scores[0]
           p3_winner_score, p3_winner_plan = phase3_scores[0]
           if p3_winner_score > p2_winner_score * 1.10:
               final_scores = phase3_scores + final_scores[len(phase3_scores):]
               tel.used_depth2 = True  # mark phase-3 was used
   ```
2. Add a telemetry field `tel.used_phase3 = True` for matches where
   phase-3 fired and changed the winner.

### Validation gate

1. Component tests pass.
2. Local ladder 10/10. CRITICAL: phase-3 must NOT change the win/loss
   outcome of any bar match. The margin gate prevents this.
3. Telemetry: `sims_run/turn` should increase by 60-150 sims when
   phase-3 fires (10 candidates × 12-16 samples × 1 sim).
4. Sanity test: in a synthetic state where phase-2 has clear winner
   (margin > 100 utility), phase-3 must NOT override (margin gate
   should suppress).
5. Sanity test: in a synthetic state where top-2 phase-2 are within
   1% utility, phase-3 should re-rank confidently and pick the higher
   one.

### Upload + live testing
- `oracle_pure_M4_upload/`
- `MILESTONE_M4_RESULTS.md`
- Branch `oracle-pure-M4`
- Live test ≥10 matches; expect modest improvement (top picks slightly
  better calibrated).

---

## PROMPT — Milestone 5: Behavioral (G6) — OPTIONAL

You are implementing **Milestone 5** — the OPTIONAL behavioral fix.
**M1, M2 must be validated. M3 is strongly preferred** (G6 needs the
defensive options G1+G5 provide; otherwise it just blocks bad plans
without giving good alternatives).

**This is the riskiest milestone.** Skip it if M1+M2+M3+M4 have already
delivered the desired ELO gain.

This milestone ships:
- **G6**: Offense-effectiveness tracker by `(archetype, launcher_zone)`.
  Specifically targets the demo-siege-v2 failure mode where oracle gets
  stuck spamming the same useless interceptor cluster for 30+ turns.

### Implementation steps

1. Create `oracle_core/offense_tracker.py` (per the code sketch in
   `REVISED_IMPROVEMENT_PLAN.md` G6 section).
2. Wire it into `algo_strategy.py`:
   - Init in `__init__`
   - On every breach event by US (player_id matches us in
     `on_action_frame`), record (archetype, launcher_xy, mp_spent, hp_dealt)
3. Pass to `value.evaluate()` via search → value pipeline.
4. In `value.py`, add a CONSERVATIVE penalty:
   ```python
   if plan and plan.has_mobiles() and offense_tracker is not None:
       for op in plan.mobile_ops():
           expected = offense_tracker.expected_dmg_per_mp(
               plan.archetype, op.xy, current_turn=state_dict["turn"]
           )
           # CONSERVATIVE PENALTY: -15 max (smaller than my prior plan's -30)
           if expected < 0.05 and plan.mp_cost >= 3:
               score -= 15 * min(plan.mp_cost / 5.0, 2.0)
   ```

### Validation gate

1. Component tests pass.
2. Local ladder 10/10. **MUST RUN BOTH SIDES.** This penalty could
   over-correct against opponents oracle currently beats with offense
   that needs 2-3 setup attempts.
3. Demo-siege-v2 specific test: there's no local equivalent of
   demo-siege-v2, but you can construct a synthetic match (use one of
   the funnel-rush algos as proxy). Verify oracle's offense diversity
   increases (more unique archetypes used per match) without losing
   total HP dealt.
4. Telemetry: log `offense_tracker.expected_dmg_per_mp` for every
   plan considered. After 30 turns of an adversarial match, check that
   the tracker has accumulated meaningful per-(archetype, zone) stats.

### Upload + live testing
- `oracle_pure_M5_upload/`
- `MILESTONE_M5_RESULTS.md`
- Branch `oracle-pure-M5`
- Live test ≥15 matches (high-risk milestone); especially watch for
  losses to demo-siege-v2 (target: convert ≥2 of 5 future matches to
  wins).
- If demo-siege-v2 still loses: tune penalty up to -25 (still
  conservative); if oracle's other matches regress, tune DOWN to -10
  or revert.

---

## Master execution checklist

For each milestone, in order:

- [ ] **M1**: Foundation (G7 + G3) — implement, validate, upload, live
      test, merge to main.
- [ ] **M2**: Sampling (G8 + G2) — same. Requires M1.
- [ ] **M3**: Defense templates (G1 + G5) — same. Requires M2. **High
      regression risk** — be ready to abandon.
- [ ] **M4**: Compute scaling (G4) — same. Requires M1 (G7); M2 preferred.
- [ ] **M5**: Behavioral (G6) — OPTIONAL. Skip if M1-M4 already met
      target ELO. Requires M2; M3 strongly preferred.

After M2 lands: pause and decide whether to continue to M3 based on
actual ELO movement. If M1+M2 added +30 ELO (i.e., 2150 → 2180), M3+M4
could push to 2200+. If M1+M2 added <10 ELO, the structural premise
needs re-examination before more changes.

After all milestones (or whatever subset passed): write a final
`POST_PLAN_RETROSPECTIVE.md` documenting:
- Which milestones landed
- Which were rejected and why
- Total ELO delta
- Surviving improvement opportunities for a future plan
