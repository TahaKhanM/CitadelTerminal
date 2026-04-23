# Config Patch + Track B-amended Prompts

Two prompts. Run in sequence, in separate fresh Claude Code sessions:

1. **§ 1 — Config patch** (~30-60 min). Makes the local engine match competition Support/Turret/Wall numbers, so caravan strategies are actually testable locally.
2. **§ 2 — Track B amended** (overnight). Runs Phase 5 (Markov) + Phase 6 (CMA-ES) on top of the patched config, now including the caravan knob set.

Do NOT merge these into one session. The config patch produces a committable infrastructure change; Track B consumes thousands of matches. Mixing them pollutes context and risks CMA-ES kicking off before the patch is verified.

Background: see [FOLLOWUP_TRACKS_PROMPT.md](FOLLOWUP_TRACKS_PROMPT.md) for why Track A and Track B exist. The Track A session found that `C1GamesStarterKit-master/game-configs.json` ships the OLD Encryptor for `EF` (shieldRange: 0), so local matches exhibit zero shield events and v14's caravan is invisible. The competition server uses the correct values. This prompt fixes the local side.

---

## § 1 — Config patch session

> Paste verbatim into a fresh Claude Code session at the repo root. Target 30-60 minutes.

```
You are fixing a vendored-config drift that makes Support caravan strategies
untestable in local matches. The v14 Track A session confirmed:

  • Competition server config is correct (extracted from the header of
    replays/final_v11_vs_1654_elo_win.replay).
  • Local C1GamesStarterKit-master/game-configs.json ships the OLD
    Encryptor EF block: shieldRange 0, upgraded generates MP not shields.
  • Consequence: every local /bestof match records 0 shield events.
    v14_support_caravan and v13_second_ring are behaviourally identical
    locally, explaining the 10/10 tie.

Your job is to land a tracked, idempotent local-config patch that makes
/run-match, /bestof, /tournament, /test-algo, and /profile-turns use
competition-accurate values. NOT to change any algo code. NOT to touch
the simulator.

ORIENTATION
  1. CLAUDE.md
  2. docs/UNITS_REFERENCE.md                (competition values — ground truth)
  3. docs/FOLLOWUP_TRACKS_PROMPT.md § Track A findings (context)
  4. C1GamesStarterKit-master/game-configs.json  (what's wrong)
  5. replays/final_v11_vs_1654_elo_win.replay   (authoritative live config
       in the first JSON line's header.config field)
  6. tools/run.sh, tools/bestof.py, tools/test.sh (callers to update)

STEP 1 — Extract the competition config  (~10 min)

  a. Read the first line of replays/final_v11_vs_1654_elo_win.replay.
     It's JSON with a top-level `config` key. Dump config.unitInformation
     and config.resources to a scratchpad.

  b. Diff the replay's config against
     C1GamesStarterKit-master/game-configs.json. List every numeric /
     boolean difference, not just EF. (Expect drift on Turret upgrades,
     Wall upgrade cost, Support — confirm or refute each.)

  c. Cross-check the diffs against docs/UNITS_REFERENCE.md. If the replay
     and the doc disagree on ANY field, halt and report — don't silently
     pick one. Trust the replay over the doc, but flag it.

STEP 2 — Create the tracked competition config  (~5 min)

  a. mkdir -p configs/
  b. Write configs/competition-game-configs.json with the competition
     values from STEP 1. Preserve every field of the starter-kit schema
     (don't drop keys just because they're unchanged) so the file is a
     drop-in replacement.
  c. Add a top-level "_source" string documenting provenance, e.g.:
       "_source": "extracted from replays/final_v11_vs_1654_elo_win.replay
                   header.config, 2026-04-23, verified against
                   docs/UNITS_REFERENCE.md"

STEP 3 — Idempotent sync script  (~10 min)

  a. Create tools/apply_competition_config.sh:
       - Read configs/competition-game-configs.json.
       - Diff against C1GamesStarterKit-master/game-configs.json.
       - If different: back up the starter-kit file to
         C1GamesStarterKit-master/game-configs.json.starterkit-original
         IF NO SUCH BACKUP YET EXISTS, then copy the competition file in.
       - If same: no-op.
       - Print one line: "config: already in sync" or
         "config: synced competition values from configs/".
       - Exit 0 in both cases; exit 1 only on error.

  b. Make it executable: chmod +x.

  c. Call it from the top of:
       - tools/run.sh
       - tools/test.sh
       - tools/bestof.py                (via subprocess near entry)
       - tools/tournament.py            (same)
       - tools/eval.sh                  (same)
     Just the one-line script call — don't restructure the callers.

STEP 4 — Validate  (~15-20 min)

  a. Run ./tools/apply_competition_config.sh once manually. Confirm it
     creates the backup file and reports "synced".

  b. Run /inspect-config. Confirm the EF block now shows:
       shieldRange: 7, shieldPerUnit: 1, shieldBonusPerY: 0.7,
       upgrade.startHealth: 40, unitCategory Support (not Encryptor).
     If any field still shows starter-kit values, the script didn't
     actually sync — fix it.

  c. Run /run-match v14_support_caravan v13_second_ring. Then, from
     the generated replay, count shield events:
       python3 -c "
       import json
       with open('<path to replay>') as f:
           frames = [json.loads(l) for l in f if l.strip()]
       shield_events = sum(1 for f in frames[1:] for e in f.get('events',{}).get('shield',[]) if e)
       print(f'shield events: {shield_events}')
       "
     This MUST be > 0 (Track A measured 0/5927 on the broken config).
     Target ≥ 50 shield events on a full-length game.

  d. Run /bestof v14_support_caravan v13_second_ring 5. Report the
     win/tie/loss. Expected: v14 wins ≥ 2/5 now that shields actually
     fire. If it still ties 0/5, the caravan has a real geometry/timing
     bug worth a follow-up session (do NOT chase it here).

STEP 5 — Commit

  Commit message:
    "Sync local game-configs to competition values

    Adds configs/competition-game-configs.json (authoritative) and
    tools/apply_competition_config.sh (idempotent sync, called by
    every match-running tool). Fixes EF (Support) from the vendored
    old-Encryptor block (shieldRange 0) to competition values
    (shieldRange 7, shieldBonusPerY 0.7), plus <N> other field
    drifts listed in commit body.

    Before: /bestof v14 v13 → 0 W / 10 T, zero shield events.
    After:  shields fire N times per game; caravan validatable.

    Starter-kit original preserved at
    C1GamesStarterKit-master/game-configs.json.starterkit-original.
    Re-running tools/apply_competition_config.sh is safe & idempotent."

  Include the full diff list in the commit body.

EXIT CRITERIA (all must hold):
  • configs/competition-game-configs.json exists and matches the replay
    header.
  • tools/apply_competition_config.sh exists, is idempotent, wired into
    all 5 callers.
  • /inspect-config shows competition EF values.
  • /bestof v14 v13 5 produces > 0 shield events in the replays.
  • Commit is clean with a full diff summary in the message body.

MUST NOT
  • Modify any algo_strategy.py file.
  • Modify gamelib_ext/ or gamelib/.
  • Change engine.jar or any .class file.
  • Edit C1GamesStarterKit-master/game-configs.json directly as a one-
    shot; the apply-script is the only sanctioned writer.
  • Run any CMA-ES / long optimisation.
  • Produce a handoff longer than one screen.

REPORTING FORMAT

  STEP 1 diff summary: <N numeric differences, list the non-EF ones>
  STEP 4c shield event count: <N>
  STEP 4d /bestof v14 v13 5 result: <W/T/L>
  Commit SHA: <hash>
  Next session should: run Track B amended (§ 2 of
    docs/CONFIG_PATCH_PROMPT.md).

Begin with reading the replay header.
```

---

## § 2 — Track B amended

> Paste verbatim into a fresh Claude Code session AFTER § 1 is committed. Budget 6-12 hours, mostly overnight CMA-ES.

Key differences from the original Track B in [FOLLOWUP_TRACKS_PROMPT.md](FOLLOWUP_TRACKS_PROMPT.md):

- **Prerequisite:** § 1's config-patch commit must be present.
- **v14 caravan is now validated first**, before Phase 5 starts. If the patch doesn't make the caravan win vs v13, Phase 5 evolves from v13 instead.
- **Phase 6 CMA-ES now includes the `CARAVAN_*` knob family** — it was stripped in the broken-config version.

```
You are extending v15_adaptive with two simulator-free wins on top of a
freshly-patched local config. The config patch (commit <CHECK GIT LOG>,
see docs/CONFIG_PATCH_PROMPT.md § 1) synced local Support/Turret/Wall
values to competition. Shields now fire in local matches, so caravan
strategies are finally evaluable.

Do NOT touch gamelib_ext/simulator.py. Do NOT attempt Phase 3 (MCTS /
expectimax) or Phase 4 (ILP). Only Phase 5 + Phase 6 from
docs/ADVANCED_STRATEGIES_PROMPT.md § A.

ORIENTATION
  1. CLAUDE.md
  2. docs/CONFIG_PATCH_PROMPT.md § 1     (the patch you depend on)
  3. docs/ADVANCED_STRATEGIES_PROMPT.md § A Tier 3.4 + Tier 4.1
  4. docs/FOLLOWUP_TRACKS_PROMPT.md § Track A findings (context)
  5. algos/v15_adaptive/algo_strategy.py
  6. gamelib_ext/classifier.py            (the Phase 5 hook-in point)
  7. tools/bestof.py, tools/tournament.py (evaluators)

  Do NOT re-read the full brainstorm. Only Tier 3.4 and Tier 4.1 are in
  scope.

STEP 0 — Verify the patch landed  (MANDATORY, ~5 min)

  a. git log --oneline -5. Confirm the config-patch commit is present.
  b. ./tools/apply_competition_config.sh → expect "already in sync".
  c. /inspect-config → confirm EF.shieldRange == 7. If not, STOP and
     ask for human intervention; do NOT proceed.

STEP 1 — Re-baseline the caravan  (~10 min)

  Now that shields work locally, re-validate Phase 1's original
  hypothesis BEFORE building on v15.

  a. /bestof v14_support_caravan v13_second_ring 10
       → record result. This is the canary for the rest of the session.

  b. Pick the Phase 5 baseline:
       - If v14 wins ≥ 55 % over v13: baseline = v15 (caravan is real,
         build on the adaptive layer that already references it).
       - If v14 ties or loses: baseline = v13 (skip the caravan — it's
         broken beyond shield-fires-or-not). Report the finding.

  c. Lock your baseline choice in. Do not re-open it during Phases 5
     and 6.

PHASE 5 — Markov spawn-edge predictor  (~2-4 hours)

  Hypothesis: opponent spawn edges are autocorrelated; a 2nd-order Markov
  chain over {LEFT, RIGHT, NONE} predicts next spawn > 60 % accuracy
  after T5.

  Build:
    a. gamelib_ext/spawn_predictor.py:
         class SpawnPredictor:
           - ingest(turn_n, observed_edges: set)  # LEFT / RIGHT / both / NONE
           - predict() -> dict{LEFT: p, RIGHT: p, NONE: p}
           - confidence = max(p)
           - entropy    = -Σ p log p
         Dict[(prev2, prev1) -> Counter[next]] with Laplace smoothing +1.
         30-60 LoC. No heavy deps.

    b. /new-algo v18_predictive <baseline-from-STEP-1>.
       Wire in:
         - on_action_frame (or on_turn's early parse): ingest observed
           opponent mobile spawns by inspecting turn_state p2Units.
         - In _build_defense / v15 archetype branches:
           if confidence > 0.6 AND baseline has symmetric turret layout:
             bias next turret placement toward predicted lane's hotspots
             (y=9, y=11 on the predicted half). Small bias: at most one
             extra turret per turn.
         - If confidence > 0.7 AND MP ≥ 1 AND not already Interceptor-ing:
           pre-spawn an Interceptor at [7,6] (LEFT) or [20,6] (RIGHT) one
           turn ahead.

  Gate:
    /bestof v18 <baseline> 10            ≥ 55 %
    /bestof v18 opp_scout_rush 5         ≥ 80 %  (alternating-corner
                                                  bot, Markov should
                                                  eat it)
    /bestof v18 opp_demo_line 5          ≥ 70 %
    /bestof v18 opp_turret_castle 5      ≥ 70 %
    /profile-turns max < 500 ms.
    /tournament v18 <baseline> v14 v13 opp_*  → v18 strictly on top.

  If the <baseline> gate fails: ONE iteration allowed — either widen
  confidence threshold or strengthen bias. If still failing, revert to
  baseline and proceed to Phase 6 with Phase 5 disabled.

PHASE 6 — CMA-ES overnight  (~4-8 hours)

  Hypothesis: ~15-20 hand-tuned magic numbers across the chosen baseline
  (v18 or baseline) have 30-40 ELO of untapped tuning headroom.

  UNLIKE the original Track B, the caravan knobs ARE now in scope.

  Build:
    a. Extract knobs from the evolution target (v18 if Phase 5 kept,
       else baseline). Target 15-20 numeric parameters:

         CARAVAN_START_TURN, CARAVAN_COMPLETE_TURN
         CARAVAN_POSITIONS (4 positions encoded as 4 x-coords at y=13)
         SCOUT_THRESHOLD_MULT (MP multiplier to spawn Scouts)
         DEMO_TRIGGER_FRONT (enemy front count to switch to Demos)
         DEMO_THRESHOLD_MULT
         PRESSURE_RESPONSE_MP (enemy MP that triggers our Interceptor)
         SPEND_HOARD_T5_BUDGET, ..._T8_BUDGET, ..._T12_BUDGET
         UPGRADE_PRIORITY_HP_PCT (patch-upgrade HP ratio threshold)
         MARKOV_CONFIDENCE_BIAS (if Phase 5 live)
         MARKOV_CONFIDENCE_INTERCEPT
         ARCHETYPE_HOARD_OVERRIDE (if classifier present)

       Move them into a module-level CONFIG dict at the top of
       algo_strategy.py. Parameterise code to read from CONFIG. Allow
       override via env var CITADEL_CFG_JSON (json-parsed if set).

       Sensible bounds per knob (tight, not open-ended) go in a parallel
       BOUNDS dict for CMA-ES.

    b. tools/evolve.py:
         - pip install cma (add to any requirements.txt).
         - Population 20-30, sigma0 = 0.3 on unit-normalised params.
         - Per individual fitness:
             spawn CITADEL_CFG_JSON=<individual> and for each opponent
             in [v14, v13, opp_scout_rush, opp_demo_line, opp_turret_castle]:
               run tools/bestof.py <evolved> <opponent> 2   (4 games)
             Fitness = sum of win rates (0..5).
         - Parallelise with concurrent.futures.ProcessPoolExecutor.
           Fan out individuals across workers; within each, farm the
           5 bestof calls serially (they share the same evolved-algo
           files — avoid race conditions on the config-override).

           IMPORTANT: each worker needs its own algo folder so parallel
           bestof runs don't stomp on each other's CONFIG files. Copy
           evolved algos to temp folders per individual, or pass CONFIG
           through env-var exclusively (preferred).

         - Checkpoint every generation to tools/evolve_state.pkl so
           interruption is recoverable.
         - Log: gen N | best=X.XX | mean=Y.YY | worst=Z.ZZ | top knobs=...

    c. Launch run_in_background. Estimated 10-15 generations × 25 min =
       4-6 hours. Don't block on it — let it run, move on to preparing
       the v19 scaffold.

    d. When done: copy the best individual's CONFIG dict into a new
       algos/v19_evolved/ (copied from the evolution target). Hard-code
       the evolved CONFIG as the default; keep env-var override intact.

  Gate:
    /bestof v19 <evolution-target> 10  ≥ 55 %
    /bestof v19 opp_* 5                each ≥ 75 %
    /tournament v19 v18 v15 v14 v13 opp_*  → v19 strictly on top.
    /profile-turns max < 500 ms.

  If v19 fails to improve: CMA-ES found no lift.
    - If fitness noise dominated (variance between top individuals < 5 %),
      rerun ONCE with N=3 (6 games/individual) and pop=15 over 15 gens.
    - Otherwise accept — knobs are near-optimal; ship the evolution
      target unchanged.

PROCESS RULES

  • STEP 0 → STEP 1 → Phase 5 → Phase 6. Strict order.
  • Phase 6 cannot run before Phase 5 lands or is explicitly skipped.
  • CMA-ES is CPU-heavy. Don't run /bestof or /tournament while it's
    active — wait for the background job to complete.
  • Preserve every intermediate algo (v14, v15, v18, v19). No deletes.
  • One phase in_progress in TodoWrite at a time.
  • If CMA-ES hasn't converged when your time budget expires, ship the
    best-of-generation-N individual and note "CMA-ES mid-run at gen
    N/<max>".

MUST NOT
  • Modify gamelib_ext/simulator.py.
  • Modify engine.jar / C1GamesStarterKit-master/* except via the
    config sync script (which should already be a no-op).
  • Hardcode any magic number — all CMA-ES-reachable knobs go through
    CONFIG.
  • Attempt Phase 3 (MCTS), Phase 4 (ILP), or Phase 7 in
    docs/ADVANCED_STRATEGIES_PROMPT.md. Explicitly deferred.
  • Declare victory without pasting the final tournament table and
    /profile-turns output.

REPORTING (per phase)

  Phase <N>: <hypothesis>.
  Baseline chosen: <v15 or v13>, because <v14 caravan result>.
  Result: /bestof vs baseline = W/T; vs opp_* = ...; max compute ms.
  Signal: <what analyse-replay on the losses/ties showed>.
  Next: <keep / revert>.

FINAL HANDOFF (structured)

  1. Strongest algo path + one-paragraph architecture summary.
  2. STEP 1 v14-vs-v13 baseline result (canary for the whole session).
  3. Tournament table: v13, v14, v15, v18, v19, opp_*.
  4. /profile-turns on a late-game v19 replay.
  5. CMA-ES summary:
       - Generations completed.
       - Best fitness per generation (plot-as-numbers).
       - Knob-change magnitudes (top 5 most-moved knobs with initial →
         evolved values).
       - Noise estimate: variance among top-10 individuals.
  6. Two concrete follow-ups for the next session, ranked by expected
     ELO gain, citing § A menu IDs.

Begin with STEP 0.
```

---

## Why this two-step beats the original Track B

- **Caravan becomes testable.** The original Track B would have run CMA-ES on a fitness landscape where `CARAVAN_*` knobs were noise. Post-patch, the caravan either wins or loses on its own merits, and the CMA-ES can optimise over it rigorously.
- **Canary built in.** The amended Track B's STEP 1 is a one-shot sanity check. If v14 still ties v13 after the patch, the caravan has a real geometry bug and the session bails cleanly on v13 as the baseline rather than building on a broken foundation.
- **Infrastructure is tracked.** `configs/competition-game-configs.json` + `tools/apply_competition_config.sh` are git-tracked and idempotent — every future session (and the next `/upload-algo`) inherits the fix without re-discovery.
- **Compatible with upload.** The patch affects ONLY local testing. The competition server uses its own correct config regardless.
