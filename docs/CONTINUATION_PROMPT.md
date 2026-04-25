# Athena Improvement — Continuation Session

## Mission

Execute the 7-task improvement roadmap documented in `research/opponent_analysis/OPPONENT_CLUSTERS.md`. The goal is to improve `athena_baseline` (currently ~1574 ELO, losing 0% above the 1500 threshold) toward the 1800-1900 ELO band where the lostkids port (`baseline` algo) currently competes.

The diagnostic phase is COMPLETE. 307-replay corpus extracted, opponent behavior profiled, weaknesses identified. This session is execution.

---

## REQUIRED READING (do this first, in order)

1. **`CLAUDE.md`** (project root) — orientation, repo layout, key constants. Auto-loaded.

2. **`research/opponent_analysis/OPPONENT_CLUSTERS.md`** — THE PLAYBOOK. Contains: the 1700 ELO performance cliff, two opponent archetypes (Type A aggressive grinders, Type B scout-floods), per-opp diagnostic table, and the 7-task prioritized improvement plan.

3. **Recent git log**: `git log --oneline -15` — read commit messages to understand: the journey from broken sim_rs (parents[2] bug) → bundled wheel working → throttle to 50% utilization → utility miscalibration → the corpus analysis that produced the current plan.

4. **Current athena_baseline source**: `algos/athena_baseline/` — especially:
   - `planner/economy.py:_offense_phase` — has `_synthetic_opp_actions()`
   - `planner/offense.py` — has candidate-explosion + utility weights
   - `planner/defense.py:probabilistic_placement` — known bug here (T1.1)
   - `offense/templates/` — 17 JSON templates including `scout_micro_*`

5. **Known-good test commands**:
   - `pytest algos/athena_baseline/tests/ -q` (118 tests, must stay green)
   - `python tools/bulk_download_my_replays.py` (incremental — skips existing)
   - `python tools/extract_replay_features.py` (rebuilds /tmp/opp_features.json)

---

## ARCHITECTURAL CONTEXT (what's been tried, what works)

### sim_rs is now operational on live

The `parents[2]` bug in `offense/sim_eval.py:34` blocked sim_rs imports on the competition server (live extracts to filesystem root, only 2 parents available). Fixed in commit `55fa7b0`. Verified via T0 spawn at (12, 1) signature.

### sim_rs throttle is at ~48% utilization mid-game

Candidate explosion (14 spawn-tile sweep × 6 unit-mix variants × 16 base templates) + opp_actions k=30 produces ~26K sims/turn at MP=20. See `research/opponent_analysis/OPPONENT_CLUSTERS.md` and athena_baseline's planner code.

### action_predictor returns garbage; synthetic opp_actions covers it

`opponent/labels.json` has 47 replay-level archetype labels but NO state-conditioned action distributions, so `_counts` is empty and `top_k()` returns only the NONE fallback. `_synthetic_opp_actions()` in `planner/economy.py` augments with 9 plausible actions when predictor returns <3 real actions. **DO NOT remove this** until the classifier rebuild (T3.0) ships its replacement.

### utility function is partly tuned but still imperfect

Current weights (after Athena_loss diagnosis):
- HP_DEALT_MULTIPLIER = 3.0
- ALPHA_HP_TAKEN = 0.1
- BETA_MP_SPENT = 0.2
- INTERCEPTOR_HEAVY_PENALTY = 10.0 (when >50% of plan is INT)

Rationale documented in code comments. T2.1 will recalibrate from data.

### 311-replay corpus exists but isn't versioned

Location: `Ranked Replays/<algo>/<algo>_<win|loss>_<opp>_<rate>_<id>.replay`

Sizes: 2.2 GB total. Gitignored. Reproducible via bulk downloader if deleted. Subdirectories: `baseline` (124), `v13_second_ring` (74), `v21_temporal_phase_gate` (65), `v21_quadrant_adaptive_support` (33), `athena_v3_planner` (9), `athena_baseline` (6).

---

## THE PLAN — execute in this exact priority order

For EACH task: read the goal, propose a specific implementation, implement it, validate it locally with a bestof, commit, move on. DO NOT batch multiple tasks in one commit — keep changes isolated.

Each task's "validation" step uses `python C1GamesStarterKit-master/scripts/run_match.py` or the `tools/bestof.py` script. The fixture opponent panel is:
- `algos/v13_second_ring` (validated baseline)
- `algos/baseline` (lostkids port — strong reference)
- `algos/sparring/*` (Wave-1 adversarial suite)

If a fix REGRESSES the bestof vs `algos/v13_second_ring` (drops below N=20 8W/12L), REVERT.

### T1.1 — Defense-offense decoupling (do FIRST, lowest risk, fastest)

**GOAL**: stop `probabilistic_placement` from putting Walls/Turrets/Supports on our own spawn-edge tiles. Bug documented at length in OPPONENT_CLUSTERS.md and earlier git history.

**WHERE**: `algos/athena_baseline/planner/defense.py`, `probabilistic_placement` function. Currently allows Support placement at `5 ≤ y ≤ 9`, Turret at `y ≥ 9`, Wall at `y ≥ 11`. The BL diagonal (y = 13 - x for x in 0..13) and BR diagonal (y = x - 14 for x in 14..27) hit those bands.

**IMPLEMENTATION**:

    SPAWN_EDGE_TILES = frozenset(
        {(x, 13 - x) for x in range(14)} |
        {(x, x - 14) for x in range(14, 28)}
    )

    # Inside probabilistic_placement, in the candidate-building loop:
    if (x, y) in SPAWN_EDGE_TILES:
        continue   # never block our own deploy tiles
    if 5 <= y <= 9:
        candidates.append((v_s, support_sh, x, y))
    # ... (same for turret + wall bands)

**VALIDATION**:
1. `pytest algos/athena_baseline/tests/ -q` → 118/118 pass
2. `python tools/bestof.py algos/athena_baseline algos/v13_second_ring 20` → should be ≥ 10 wins (was likely ≤8 before fix)
3. Inspect 2-3 generated replays — verify Athena's defensive structures are NEVER on tiles like (4,9), (5,8), (23,9) etc.

If green, commit with title `fix(defense): exclude spawn-edge tiles from probabilistic_placement (T1.1)`.

### T1.2 — Add flank_pressure_v_demo template

**GOAL**: counter-template for Type-A (aggressive demo-heavy) opponents. 56% of our games are vs python-algo / oleh-v2 / defensive-order-algo / jae-3 / terminator. Their unit mix is 21-35% Demolishers. Demolishers are slow (1 tile / 4 frames) — fast Scouts on flanks AROUND the demos should beat them.

**IMPLEMENTATION**: create `algos/athena_baseline/offense/templates/flank_pressure_v_demo.json`:

    {
      "name": "flank_pressure_v_demo",
      "description": "Anti-demo: 8 Scouts flank-side at (1,12), 2 Interceptors at (4,9) to engage demos.",
      "side": "BL",
      "min_mp": 10,
      "spawns": [
        {"unit": "SCOUT", "loc": [1, 12], "count": 8},
        {"unit": "INTERCEPTOR", "loc": [4, 9], "count": 2}
      ]
    }

And mirror at `flank_pressure_v_demo_right.json` (anchor at (26,12) and (23,9)).

**VALIDATION**:
1. Run beam_search local benchmark — does new template fire when MP=10 vs simulated demo-heavy opp_action? (Use `_synthetic_opp_actions` override if needed.)
2. bestof vs `algos/sparring/opp_demo_line` if exists, else vs `algos/v13_second_ring`.
3. Tile sweep + unit-mix variants will auto-generate from this base per the existing candidate-explosion logic.

Commit: `feat(offense): add flank_pressure_v_demo template (T1.2)`.

### T1.3 — Build oleh-v2 sparring clone

**GOAL**: reverse-engineer oleh-v2's behavior from its 20 replays in our corpus, codify it as a sparring opponent, then study what beats it. oleh-v2 won 18 of 20 games against us — single most impactful opponent to crack.

**STEPS**:
1. Profile oleh-v2 from `Ranked Replays/baseline/baseline_*oleh-v2*.replay` and `Ranked Replays/v13_second_ring/v13_second_ring_*oleh-v2*.replay`. Extract: structure layout per turn, spawn timing, unit choices. Use `tools/extract_replay_features.py` as starting point.

2. Create `algos/sparring/opp_oleh_v2_clone/` (copy `C1GamesStarterKit-master/python-algo` as base). Hard-code the observed behavior: place same structures at same turns, spawn same waves at same locations.

3. bestof athena_baseline vs the clone N=20.
   - If clone reproduces the live ~2W/18L pattern → clone is faithful
   - If not, the clone is missing a key behavior — iterate.

4. Once clone is faithful, manually probe what beats it:
   - bestof of v13_second_ring → expect mid 30%
   - Try Athena variants: more demos, scout-flood, interceptor screens
   - Find a strategy that wins ≥ 12/20

5. Codify the winning strategy as a template: `oleh_v2_counter.json` or integrate the insight into utility weights / archetype priors.

**VALIDATION**: bestof original athena_baseline vs new athena_baseline (with the counter strategy) should show new version wins ≥ 12/20. If not, iterate or revert.

Commit: `feat(offense): oleh-v2 counter-strategy from sparring (T1.3)`.

### T2.1 — HP_DEALT_MULTIPLIER regression

**GOAL**: replace hand-picked utility weights with empirically-fit ones. For each replay, the actual offensive plan's hp_dealt is observable. The utility weights should be the values that maximize "predicted plan utility correlates with actual game outcome (win)."

**IMPLEMENTATION**:
1. For each replay in corpus, extract per-turn:
   - Our offensive plan
   - Opp's defensive layout at that turn
   - hp_dealt_actual (from p2 HP delta in the replay)
2. Re-sim each plan via sim_rs to get hp_dealt_predicted
3. Fit logistic regression: P(game_won) ~ alpha * cum_hp_dealt - beta * cum_hp_taken - gamma * cum_mp_cost (per game)
4. New weights: solve for the coefficients that maximize correlation

**VALIDATION**: bestof with new weights vs old weights vs v13_second_ring. If new weights produce ≥ 13/20 vs 8/20 baseline → ship.

Commit: `tune(offense): recalibrate utility weights from 307-replay regression (T2.1)`.

### T2.2 — Anti-funnel-rush template

**GOAL**: counter Type-B passive scout-flood opponents (funnel-rush v8/v9 are 0W/8L for us — most damaging non-oleh-v2 type).

**OBSERVATION FROM CORPUS**:
- funnel-rush attacks at turn 19-22 reliably
- Spawn 65-77% Scouts in waves of 12-14
- Light front-line walls (5.2-5.8 vs Type-A's 7.7-12.5)

**COUNTER**:
- At predicted attack turns, place an extra Demolisher line at our front to chew through their thin walls
- Spawn 4 Demolishers at (4, 9) at turn 18 (one before their wave)

**IMPLEMENTATION**: similar to T1.2 — new template + turn-conditional logic in offense_planner.

Commit: `feat(offense): anti-funnel-rush template (T2.2)`.

### T2.3 — Depth-2 multi-turn lookahead

**GOAL**: extend beam_search to 2-turn lookahead for the top-K candidates. Multi-turn discrepancies between sim and reality (interceptor breach, wall removal) only show up in 2-turn rollouts.

**IMPLEMENTATION**: in `planner/offense.py:beam_search`:

    def beam_search_depth_2(state, candidates, opp_actions):
        for cand in candidates:
            for opp_a in opp_actions:
                mid_state = sim_rs.simulate(state, cand, opp_a)
                our_response = depth_1_best(mid_state)  # cheap inner search
                for opp_b in opp_actions_t2(mid_state):
                    final = sim_rs.simulate(mid_state, our_response, opp_b)
                    util = utility(state, final, mp_used=...)
                    accumulate(cand, util)

Cost: ~25K sims/turn extra. Total ~50K. Still well under budget.

**VALIDATION**: bestof vs current Athena. Watch turn-time profile — must stay under 12 sec/turn (see watchdog in algo_strategy.py).

Commit: `feat(offense): depth-2 multi-turn lookahead (T2.3)`.

### T3.0 — Classifier-driven per-archetype templates

**GOAL**: replace synthetic_opp_actions with a real classifier output. The existing classifier (LOO-CV 0.489) is too weak. Re-train with the 307-replay corpus, using behavioral features rather than the original 14-feature vector.

This is the largest task — likely 1+ session. Defer to a follow-up plan if the earlier tasks burn through context.

---

## VALIDATION HARNESS — use `tools/bestof.py`

    # N=20 (Wilson 95% CI excludes "no improvement" if >= 12 wins)
    python tools/bestof.py algos/athena_baseline algos/v13_second_ring 20

    # Quick smoke (N=5, just to confirm not broken)
    python tools/bestof.py algos/athena_baseline algos/baseline 5

If the script doesn't exist or has issues, fall back to manual loop: `python C1GamesStarterKit-master/scripts/run_match.py algos/athena_baseline algos/v13_second_ring` — alternate side ordering between runs.

---

## HAND-OFF RULES

1. **One task per commit.** No batching.
2. **Run tests before each commit.** 118/118 must pass.
3. **Never commit `Ranked Replays/`.** It's gitignored.
4. **Never put `bin/` in the upload folder.** Causes docker sandbox error on live (well-documented in earlier commits).
5. **Validate locally before celebrating.** A "fix" that bestof's at 8/20 vs baseline is REGRESSION, not improvement.
6. **When in doubt, run more matches.** N=5 is noise; N=20 is signal.
7. **STOP and write a status report** if you hit a fundamental blocker (e.g., sim_rs starts failing again, or the live server changes). Don't fight it for hours — surface it.
8. **Build the upload folder at `athena_<task>/` (project root)** so the user can upload it directly. Format: copy `algos/athena_baseline`, strip dev artifacts, delete bin/, set `algo.json` name.

---

## EXIT CRITERIA

You're done when ANY of:
- All 7 tasks are complete and committed (T1.1 through T3.0)
- Cumulative bestof improvement ≥ +200 ELO equivalent (rough proxy: win rate vs v13 ≥ 80% AND vs baseline ≥ 50%)
- You hit a context limit and must hand off to another session

For task 7 (classifier rebuild), if you can't complete it in this session, write a `T3_HANDOFF.md` in `research/opponent_analysis/` with:
- What you tried
- What partially works
- What's unclear
- Suggested next steps

---

## ANTI-PATTERNS — what to AVOID

- **Don't claim ELO numbers without measurement.** Each task's "estimated ELO" in OPPONENT_CLUSTERS.md is conservative SPECULATION. Replace with measured bestof results.
- **Don't add diagnostic spawns to the live algo** without a clear "remove after" plan. The earlier signature spawn at (12,1) cost 1 Scout/turn for several uploads.
- **Don't ship without testing on the FULL upload pipeline.** The athena_baseline upload folder must contain the right files — the bundled `.so`, the `data/`, the `opponent/`, but NEVER bin/.
- **Don't trust sim_rs predictions over live observation.** The sim is single-turn; multi-turn dynamics diverge. When sim says "X wins" but live data says X loses, trust live.
- **Don't fork into branches.** Stay on main. The user's workflow doesn't use branches.
- **Don't over-engineer.** The existing infrastructure has 80% of what you need (MAP-Elites archive, sparring suite, bestof tool). Extend, don't rebuild.

---

## ONE LAST THING

The user's actual goal is "best Athena possible" — not "complete the plan." If during execution you discover something that's NOT in the 7-task plan but is clearly higher-leverage (e.g., a bug found while debugging T1.1), prioritize that. The plan is a guide, not a contract.

Start by reading the four required documents, then propose your T1.1 plan in a single response and wait for "go" before implementing anything. After T1.1 is committed and validated, proceed autonomously through T1.2, T1.3, T2.1, T2.2, T2.3, surfacing T3.0 status at the end.

GO.
