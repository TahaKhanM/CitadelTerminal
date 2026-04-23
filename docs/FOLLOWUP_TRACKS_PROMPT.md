# Follow-up Tracks — After the v13→v15 Session

The first implementation run of [ADVANCED_STRATEGIES_PROMPT.md](ADVANCED_STRATEGIES_PROMPT.md) produced v14 and v15 but both tied v13 10/10. Two issues surfaced that invalidate moving forward to Phase 3 (simulator-based search):

1. **Caravan did not actually shift outcomes** — either a placement/timing bug or shields never reach Scouts.
2. **Local engine starts at 5 MP, docs claim 1 MP** — a real config-vs-docs drift that skews every timing decision.
3. **Simulator accuracy ~30 % — unusable for Phase 3+.**

Rather than fix the simulator, these two tracks bypass Phase 0–3 entirely and chase near-term wins with zero new infrastructure.

**Run order:** Track A first (short, may commit code/doc fixes) → then Track B (long, consumes Track A's fixed code).
**Run in:** separate fresh Claude Code sessions at the repo root. Don't reuse the session that produced v15.

---

## § Track A — MP config reconciliation + caravan debug

> Copy verbatim into a new Claude Code session. Expect 30-90 minutes.

```
You're debugging two specific issues from the v13→v15 iteration session.
Both are in the handoff report at docs/ADVANCED_STRATEGIES_PROMPT.md §B
and the session stopped rather than advancing to Phase 3.

YOUR SCOPE IS NARROW: reconcile the MP=5 vs MP=1 config discrepancy,
diagnose why v14's Support caravan ties v13 in 10/10 matches, and ship
whatever minimal fix falls out. Do NOT build new algos, do NOT touch the
simulator, do NOT start any optimization run.

ORIENTATION
  1. CLAUDE.md
  2. docs/GAME_RULES.md                      (claims MP starts at 1)
  3. docs/UNITS_REFERENCE.md                 (upgraded Support range + Y-bonus)
  4. docs/TARGETING_AND_PATHING.md           (shield-application rule)
  5. algos/v14_support_caravan/algo_strategy.py
  6. algos/v13_second_ring/algo_strategy.py  (for diff)

TASK 1 — MP starting-resource reconciliation  (~15 min)

  a. Run /inspect-config. Record the EXACT values of:
       resources.bitsPerRound          (MP income)
       resources.bitDecayPerRound      (MP decay)
       resources.coresPerRound         (SP income)
       resources.bitsStart / coresStart   (starting balances)
       unitInformation[*].shorthand map

  b. Run /run-match v13_second_ring v13_second_ring and inspect the
     first-turn game state. Report actual MP and SP available on
     turn 0 (you may need to add a one-line debug_write in v13
     temporarily, then revert).

  c. If starting MP is not 1, or income or decay differs from
     docs/GAME_RULES.md: edit docs/GAME_RULES.md to match reality,
     with a note like "verified YYYY-MM-DD via /inspect-config".

  d. If docs/UNITS_REFERENCE.md shield formula (1 + 0.7·Y) disagrees
     with the live config's shieldBonusPerY, fix it the same way.

  DELIVERABLE: a short diff to docs/ (if needed) plus one paragraph
  in your reply stating which doc values were wrong and the corrected
  values. If nothing was wrong, say that.

TASK 2 — Why does the caravan not win?  (~30-60 min)

  The v14 hypothesis: 4 upgraded Supports at [12,13][13,13][14,13][15,13]
  give each Scout +40 effective HP (10.1 shield × 4 supports at Y=13).
  Actual outcome: 0 W / 10 T vs v13. Something is breaking.

  Hypotheses to test, in order. Stop at the first that pans out.

  H1: Caravan never finishes building.
    • Scripted turns in v14 may not leave enough SP after T8-T12
      defense obligations. Run one match; have v14 debug_write each
      turn its support_count and upgraded_support_count. If by T15
      it hasn't reached 4 upgraded, this is the bug.

  H2: Supports are built but destroyed before they shield anything.
    • Upgraded Support HP is 40. Is any enemy mobile reaching y=13?
      /analyze-replay any v14_vs_v13 match — look at unit attrition
      for Supports. If SupportDeaths > 0 in first 30 turns, relocate
      or wall-protect.

  H3: Supports exist but Scouts never enter their range.
    • Range 7 at [13,13] covers spawns at [13,0]/[14,0] ONLY from
      y=6 upward (distance 7 from y=13 is y=6). A Scout spawned at
      [13,0] walks y=0→y=13; the first 6 steps are unshielded.
      Verify by checking whether shield events fire in the action
      phase frames for v14's own Scouts. (Look for shield grants
      in a .replay file — they're in events.shield.)
    • If true, the fix is either (a) move Supports forward to
      [13,11] / [14,11] (but they'd block defensive turret
      positions — reconcile) or (b) deploy from a closer spawn tile
      once the caravan is up.

  H4: Shield grants are applied, but v13 opponents aren't reachable.
    • If v14 Scouts are shielded but die to v13's turret-ring at y=11
      regardless (55 eff HP still dies to 3 upgraded turrets focus-
      firing: 3 × 20 = 60 dmg/frame), the caravan is strategically
      wasted because the defensive ring is too strong. In that case
      the follow-up is to pair the caravan with a Demolisher screen
      (range 4.5 attacks the turrets before they shred the Scouts),
      not more shields.

  For whichever hypothesis pans out, propose the MINIMAL code change.
  If the change is <20 lines, make it on algos/v14_support_caravan/
  directly and re-run /bestof v14 v13 5. Report the new result.

  If the change is >20 lines, DO NOT MAKE IT. Instead, write up the
  finding and the proposed fix in your reply so it can go into the
  next iteration prompt.

EXIT CRITERIA (one of):
  • MP/shield docs reconciled AND caravan root-cause diagnosed AND
    (either trivially fixed with /bestof v14 v13 5 ≥ 3 wins, OR
     written up for the next prompt).
  • An unrelated blocker is discovered that invalidates the premise —
    report it and stop.

MUST NOT
  • Build new algo versions (no v16, no v14b, etc.). Edit v14 in place
    only if the fix is tiny; otherwise write up and hand off.
  • Modify C1GamesStarterKit-master/ or gamelib/.
  • Touch the simulator in gamelib_ext/. It's out of scope.
  • Launch any CMA-ES or multi-game-minimum optimization run.
  • Produce a handoff longer than one screen.

REPORTING FORMAT

  TASK 1 result: <what was wrong, what was fixed in docs/>
  TASK 2 hypothesis confirmed: H<N>
  Evidence: <the one log line / replay event / diff that proved it>
  Fix shipped: <"yes, algos/v14 updated, /bestof now X/5">
              or <"no, proposed fix is: ...">
  Next session recommendation: <one sentence>.

Begin with /inspect-config.
```

---

## § Track B — Markov spawn predictor + CMA-ES overnight

> Copy verbatim into a new Claude Code session. Expect 6-12 hours elapsed (mostly overnight CMA-ES). Run AFTER Track A commits.

```
You are extending v15_adaptive with two simulator-free wins: a 2nd-order
Markov predictor of the opponent's spawn edge (Phase 5 in
docs/ADVANCED_STRATEGIES_PROMPT.md), and a CMA-ES sweep over the
algorithm's magic numbers (Phase 6). Neither depends on the broken
Phase 0 simulator. Both were called out as high-EV follow-ups in the
v13→v15 handoff report.

Do NOT touch gamelib_ext/simulator.py. Do NOT attempt Phase 3 or 4.

ORIENTATION
  1. CLAUDE.md
  2. docs/ADVANCED_STRATEGIES_PROMPT.md § A Tier 3.4 (Markov) + Tier 4.1 (CMA-ES)
  3. docs/CLAUDE_WORKFLOW.md
  4. algos/v15_adaptive/algo_strategy.py
  5. gamelib_ext/classifier.py                  (the plumbing to hook into)
  6. tools/bestof.py + tools/tournament.py      (the evaluators)

  Skip re-reading the full brainstorm. § A Tier 3.4 and Tier 4.1 are
  the only menu entries in scope.

PHASE 5 — Markov spawn-edge predictor (v18_predictive)

  Hypothesis: opponent spawn edges are highly autocorrelated; a
  2nd-order Markov chain over {BOTTOM_LEFT, BOTTOM_RIGHT, NONE}
  predicts next-turn spawn with > 60 % accuracy after T5.

  Build:
    a. gamelib_ext/spawn_predictor.py:
         • class SpawnPredictor: ingest opponent mobile-spawn events
           per turn (from on_action_frame or from reading
           p2Units in turn_state). Maintain a dict[(prev2, prev1) →
           Counter[next]] with Laplace smoothing (+1). Provide
           predict() → {LEFT: p_L, RIGHT: p_R, NONE: p_N} with
           confidence = max(p) and entropy = −Σ p log p.
         • 30-60 LoC. No heavy dependencies.

    b. /new-algo v18_predictive v15_adaptive. Wire it in:
         • on_action_frame / on_turn: feed observed spawns into the
           predictor.
         • In _build_defense and v15's archetype counters: when
           confidence > 0.6, bias the next turret placement toward
           the predicted lane's sidelane hotspots (y=9, y=11 rows
           on the predicted half). Small bias only — don't abandon
           the symmetric base defense.
         • Optional: if confidence > 0.7 AND we have ≥ 1 MP, pre-
           spawn an Interceptor at [7,6] (LEFT predicted) or [20,6]
           (RIGHT predicted) one turn in advance.

  Gate:
    /bestof v18 v15 10        ≥ 55 %   (strict improvement required)
    /bestof v18 opp_scout_rush 5   ≥ 80 %  (this opponent alternates
                                             corners predictably —
                                             the Markov should eat it)
    /bestof v18 opp_demo_line 5    ≥ 70 %
    /bestof v18 opp_turret_castle 5 ≥ 70 %
    /profile-turns max < 500 ms.
    /tournament v18 v15 v14 v13 opp_* — v18 strictly on top.

  If the v15 gate fails (no improvement): the Markov either isn't
  triggering, or the bias is too weak. ONE iteration allowed: widen
  the confidence threshold OR strengthen the bias. If still failing,
  revert and proceed to Phase 6 on v15 instead.

PHASE 6 — CMA-ES overnight hyperparameter sweep (v19_evolved)

  Hypothesis: v18 (or v15 if Phase 5 reverted) contains ~12-20
  hand-tuned magic numbers with 30-40 ELO of untapped headroom.

  Build:
    a. Identify the knobs. Scan the chosen baseline (v18 or v15) for:
         • CARAVAN_* turn thresholds and positions
         • SCOUT_THRESHOLD_* (MP multiplier to trigger rush)
         • DEMO_TRIGGER_* (enemy front density threshold)
         • PRESSURE_RESPONSE_* (MP threshold for defensive Interceptor)
         • _spend_hoard turn gates (T5, T8, T12, T15, T20 in v13)
         • Phase 5 bias weight (if v18) and confidence threshold
       Target 12-20 numeric parameters with sensible bounds.
       Extract into a CONFIG dict at the top of algo_strategy.py that
       can be JSON-overridden via an env var (e.g., CITADEL_CFG_JSON).

    b. tools/evolve.py (new):
         • pip install cma (add to any requirements.txt present).
         • Population 20-30, sigma0 = 0.3 (unit-normalized params).
         • Per individual: spawn CITADEL_CFG_JSON and call
           tools/bestof.py against each of v14, v13, opp_scout_rush,
           opp_demo_line, opp_turret_castle for N=2 (4 games each).
           Fitness = sum of win rates (max 5.0).
         • Parallelise via concurrent.futures.ProcessPoolExecutor.
           Fan out individuals; within each, farm bestof invocations.
         • Checkpoint every generation to tools/evolve_state.pkl so
           an interrupted run can resume.
         • Log best-of-generation to stdout.

    c. Launch run_in_background, estimated 4-8 hours. Sleep budget
       per generation ≈ pop × 5_opponents × 4_games × match_time.
       Match time ≈ 20-40 s locally → a gen is ~60-90 min unless
       parallelised. With 4 workers, ~15-25 min per gen × 10 gens
       = 2.5-4 hours.

    d. When done: copy the best-scoring individual's CONFIG into a
       new algo algos/v19_evolved/ (copied from whichever baseline
       you evolved from). Commit the CONFIG override as the default.

  Gate:
    /bestof v19 <baseline> 10   ≥ 55 %
    /bestof v19 opp_* 5         each ≥ 75 %
    /tournament v19 v18 v15 v14 v13 opp_*  — v19 strictly on top.
    /profile-turns max < 500 ms.

  If v19 fails to improve: CMA-ES found no lift — either the
  parameters are already near-optimal (good signal, ship v18/v15),
  or the fitness signal is too noisy at N=2. Try ONE rerun with
  N=3 and pop=15 over 15 gens. Otherwise accept and move on.

PROCESS RULES

  • Phase 5 gate MUST pass before starting Phase 6. If Phase 5
    reverts, Phase 6 evolves v15 instead of v18.
  • Do NOT run both phases in parallel — CMA-ES will consume all CPU
    and starve Phase 5 bestof runs of compute.
  • Preserve every intermediate algo (v15, v18, v19) — no deletes.
  • Use TodoWrite. One phase in_progress at a time.
  • If CMA-ES is still running when your budget runs out, ship the
    best-so-far generation's individual as v19 and note "CMA-ES was
    mid-run at generation N/30".

MUST NOT

  • Modify gamelib_ext/simulator.py. Out of scope.
  • Modify C1GamesStarterKit-master/ or gamelib/.
  • Hardcode values — every magic number goes through the CONFIG
    dict so CMA-ES can sweep it.
  • Attempt ILP (Phase 4) or MCTS (Phase 7). Explicitly deferred.
  • Declare victory without pasting the final tournament table and
    /profile-turns output.

REPORTING

  Per phase (3-4 sentences):
    "Phase <N>: <hypothesis>."
    "Result: /bestof vs baseline = W/T; vs opp_* = ...; max ms."
    "Signal: <what analyze-replay on the losses/ties showed>."
    "Next: <keep / revert>."

  Final handoff (structured):
    1. Strongest algo path.
    2. Tournament table: v13, v14, v15, v18, v19, opp_*.
    3. /profile-turns max/mean on a late-game replay of v19.
    4. CMA-ES summary: generations run, best fitness, which knobs
       moved the most (relative to their initial value).
    5. Two concrete follow-ups for the NEXT session (ranked by
       expected ELO gain, from the § A menu).

Begin with Phase 5.
```

---

## Running the two tracks

**Track A** — short, interactive, commit-producing:
```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
claude
# paste Track A prompt
```
Expected: doc fixes for MP/shield values, caravan root cause identified, optionally a small v14 patch. Review its diff, commit.

**Track B** — long, overnight, runs background CMA-ES:
```bash
# in a SECOND terminal, after Track A commits
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
claude
# paste Track B prompt
```
Expected: v18_predictive and v19_evolved shipped; tournament table showing clear improvement over v15.

Running them in the same session mixes concerns — Track A's debugging output would pollute the context Track B needs for match analysis, and Track B's 1000+ match outputs would drown out Track A's tight debug signals. Separate sessions keep each agent focused.

If Track A finds the caravan fix is >20 lines (handed off rather than shipped), feed that writeup into Track B's Phase 5 as a preamble — v18 can then inherit the corrected caravan placement instead of v15's broken one.
