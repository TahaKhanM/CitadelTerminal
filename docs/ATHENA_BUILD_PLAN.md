# Athena v3 — Top-of-Leaderboard Build Plan (Citadel Terminal)

**Goal: rank #1 on the Citadel Terminal leaderboard.**

Self-contained implementation plan. v3 fixes Citadel-competition alignment and production-safety gaps in v2. Removes license-risk measures (vendored finalist code is for local use freely).

---

## Pre-flight context (read these first)

1. `CLAUDE.md` — project orientation. **Note explicitly: this is a SPECIAL competition with rule changes vs base-game Terminal. The shipped `C1GamesStarterKit-master/game-configs.json` is OUTDATED. The competition's real values come from the server at game start.**
2. `docs/GAME_RULES.md`, `docs/UNITS_REFERENCE.md` — Citadel-specific rule deltas
3. `~/.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/MEMORY.md`
4. `~/.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/winning_architecture.md`
5. `~/.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/finalist_corpus.md`
6. `research/finalist_repos/Terminal-Lostkids/python-2l-aet/algo_strategy.py`
7. `research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/attack_method.py`
8. `research/finalist_repos/terminal-c1/snorkeldink-v3-1/adaptive_opening.py`
9. `algos/v13_second_ring/algo_strategy.py`
10. `tools/eval.sh`, `tools/bestof.py`, `tools/tournament.py`

---

## Citadel-specific facts that MUST be respected

These differ from base-game Terminal (which most public finalist code targets). Plans, scripts, and validators must respect these:

- **Unit shorthand rename**: FILTER/ENCRYPTOR/DESTRUCTOR/PING/EMP/SCRAMBLER → WALL/SUPPORT/TURRET/SCOUT/DEMOLISHER/INTERCEPTOR. Always read shorthands from `config["unitInformation"][i]["shorthand"]`, never hardcode.
- **Resource indices**: `MP = 1, SP = 0` in the starter API. These are *type indices*, not quantities.
- **`player_index` flip**: in `gamelib.GameState` and `gamelib.GameUnit`, `0 = you, 1 = opponent`. In raw action-frame JSON (`on_action_frame`), `1 = you, 2 = opponent`. **Every action-frame parser must handle this flip explicitly.**
- **Wall upgrade costs SP** (was free in base game — 2 SP for upgrade).
- **Support HP**: base 1 (one-shot), upgraded 40 (durable).
- **Support shield**: base = 3 flat (range 3.5); upgraded = `1 + 0.7×Y` per Support per Scout (range 7). At Y=13, ≈10.1 shield/Support.
- **Turret upgrade**: 6 dmg/60 HP base → 20 dmg/100 HP upgraded (unusually strong upgrades in Citadel).
- **Wall HP**: base 60, upgraded 200.
- **Per-turn MP decay**: 25% (round to nearest 0.1) before income.
- **Per-turn income**: 4 SP + `(1 + turn//5)` MP base.
- **Bonus SP**: +1 per HP damage dealt to opponent.
- **Compute budget**: 15s/turn before 1 dmg/sec penalty.
- **Starting**: 8 SP, 1 MP, 40 HP.
- **Map**: 28×28 diamond. You are y < 14. Spawn edges: BOTTOM_LEFT, BOTTOM_RIGHT.

**ALL numerical constants in our code must be read from runtime config (`config["unitInformation"]`) at `on_game_start`. Never hardcode HP/damage/range/cost values.**

---

## Architecture

```
EconomyArbiter (top-level SP/MP allocation, hard 14.3s budget, watchdog at 13s)
   ├── DefensePlanner (3 archetypes + GRETCHEN-style probabilistic placement
   │                   with Citadel-tuned support weighting)
   ├── OffensePlanner (beam search w/ static-eval prune over script portfolio)
   └── OpponentModel (Bayesian archetype + Predictors w/ constraint-based scoring)
            ↓ all use ↓
        SimCore (Rust validated against engine.jar; Python ref impl for debug)
            ↑                            ↑
        ActionFrameUtils         MAP-Elites Archive (64 cells, 12-D genome)
        (histograms, batch-count,        ↑
         pending-removal, etc.)    LLM FunSearch + Replay Scraper

Production safety:
  - Top-level try/except in on_turn → fallback "minimal safe turn"
  - 13s watchdog → force fallback if planner hangs
  - Empty-plan handling → defense-only no-op offense turn
```

Code under `algos/athena/` (full layout in v2 plan section, unchanged).

---

## Hard rules across all phases

- **Read all unit values from runtime config**, never hardcode. Citadel != base game.
- **Statistical gates use Wilson 95% CI lower bound** that excludes the threshold. N ≥ 50 for tight comparisons.
- **Per-turn compute budget allocation** (hard caps, planner degrades gracefully on overrun):
  - SimCore setup + caching: 200ms
  - OpponentModel posterior + predictors: 500ms
  - DefensePlanner: 1.5s
  - OffensePlanner beam search: 8s
  - MAP-Elites archive lookup: 100ms
  - GC + spike reserve: 4s
  - **Watchdog forces fallback at 13s.**
- **Pause-and-confirm with user** at: end of Phase 1, end of Phase 1.5, end of Phase 5, before Phase 12 first upload, before each subsequent live re-upload, on any failed phase gate.
- **Every phase reports two comparisons**: `(athena_vN vs v13)` AND `(athena_vN vs athena_baseline_lostkids)`.
- **Sim correctness > everything else.** Per-frame per-tile damage delta: max ≤5%, mean ≤1%.
- **Use existing tools first**: `/eval`, `/bestof`, `/tournament`, `/profile-turns`, `/detailed-replay`, `/inspect-config`, `/competition-reference`, `/test-algo`, `/run-match`, `/upload-algo`.
- **Production safety wrappers are non-negotiable** in any algo we ship to ranked play (try/except + watchdog + safe-fallback).

---

## Phase 0: Performance moat + replay corpus + Citadel config lock-in

**Tasks:**
1. **Run `/inspect-config`** to dump the live server-delivered config to stderr. Cross-reference EVERY key value (HP, damage, range, cost, shield formula, MP decay, income formula) against `docs/UNITS_REFERENCE.md` and the "Citadel-specific facts" section above. Resolve any discrepancy by trusting the live config. Write the verified values to `algos/athena/data/citadel_config_snapshot.json` with a comment explaining "source of truth = live server, NOT starter `game-configs.json`".
2. **Use `/competition-reference`** for any rule lookup needed during construction.
3. Patch `algos/athena/gamelib/navigation.py` (vendored, not the starter's): replace `queue.Queue` with `collections.deque`. Verify against bundled test replay.
4. Add path cache to `algos/athena/gamelib/game_state.py` keyed on `(start_tile, target_edge, structure_hash)`.
5. **Replay corpus**: v13 is currently on the live ladder at rank 7. Its match history page on terminal.c1games.com has ranked replays. Adapt `LanHikari22/terminal_selemium_replay` (or equivalent) to download ≥30 of v13's most recent ranked replays. These already span multiple opponents naturally. Store in `data/replays/ranked/`. **If TOS blocks scraping, fall back to manual download — but acquire the corpus before Phase 1.**
6. Run `/profile-turns` baseline measurement on v13.

**Validation gate:** ≥4× pathfinding speedup confirmed; ≥30 ranked replays from ≥5 distinct opponents in `data/replays/ranked/`; `citadel_config_snapshot.json` written and reviewed.

---

## Phase 0.5: Action-frame utilities (citadel-aware)

**Tasks (all in `algos/athena/opponent/action_frame_utils.py`):**
1. `BatchCountTracker` — Lostkids pattern. **Note the player_index flip**: action-frame `spawn[1]` uses raw JSON convention (`1=self, 2=opponent`). Filter by enemy spawns only.
2. `SpawnXHistogram` — 28-bin running counter of enemy mobile spawn x-columns. Decay 0.95/turn.
3. `WallRemovalDetector` — scans `pending_removal=True` flags on opponent structures.
4. `BreachLocationTracker` — accumulates breach events scored against us (`breach[4] != self_id`).
5. `ResourceTracker` — opponent MP/SP trajectory.
6. `MisdirectionDetector` — Lostkids `is_enemy_*_edge_misdirecting`.

**Validation gate:** Unit tests for each utility against known replays. Each utility produces deterministic output. **Player_index flip explicitly tested** — common bug source.

---

## Phase 1: SimCore (Python reference impl)

**Tasks:**
1. Build `tools/replay_to_actions.py` FIRST. Extract per-turn per-player actions from `.replay` JSON. Validate against 5 known replays manually. **Handle player_index flip** in raw JSON.
2. Create `algos/athena/sim/pysim.py` with `simulate_action_phase(state, my_actions, opp_actions) → ActionResult`. **Verify frame loop order against `engine.jar` source/docs before implementing** — likely `support_shielding → unit_attack_then_take_damage → death_resolution → movement → breach_check → self_destruct_at_endpoint`. Self-destruct triggers when (no legal next-tile) AND (≥5 tiles traveled from spawn).
3. Targeting priority — **verify against `engine.jar`** by examining the bundled `python-algo` reference or decompiling the engine. Most likely: `closest → lowest HP → furthest along path → lowest-y → lowest-x`.
4. Support shield once-per-pair: `set[(support_id, target_id)]`. **Verify support_id stability across frames in engine.**
5. Movement speeds and ALL unit attributes READ FROM `citadel_config_snapshot.json`.

**Validation gate (tightened — 2026-04-23):**
- Replay-to-actions extractor passes manual review on 5 replays.
- SimCore runs against extracted actions for ≥30 ranked replays from ≥5 distinct opponents.
- **Bit-exact parity with engine.jar: max per-turn HP error = 0 across ALL sims.**
  (The original gate "max ≤5%, mean ≤1%" was tightened by user directive;
  freezing at best-effort was rejected. See algos/athena/sim/ERROR_BANDS.md.)
- ≥50 sims/sec on commodity laptop.

**Gate status: PASS.** Final pass commit: see `git log` after the
"Phase 1 SimCore: bit-exact parity" commit. 0 HP-damage delta on all
1,463 turns across 23 v13 ranked replays.

**Pause-and-confirm with user before advancing.**

---

## Phase 1.5: Lostkids baseline port (regression baseline)

**Tasks:**
1. Port `research/finalist_repos/Terminal-Lostkids/python-2l-aet/algo_strategy.py` to `algos/athena_baseline_lostkids/`. **Lostkids ALREADY uses current shorthands (WALL/TURRET/etc.) — this port is mostly straight copy.** Verify constants vs Citadel config (Lostkids' MP≥17 threshold is Citadel-compatible because Citadel uses 25% decay).
2. Add production safety wrappers (try/except + watchdog + safe fallback) to the baseline as well.
3. Run `/bestof 50 athena_baseline_lostkids v13_second_ring`.

**Validation gate:** Wilson lower bound on win rate > 60%. If not: Lostkids port has bugs OR v13 is stronger than expected. Debug before proceeding.

**Pause-and-confirm with user before advancing.**

---

## Phase 2: Defense engine (3 archetypes, Citadel-tuned)

**Tasks:**
1. Three defense archetype JSONs in `algos/athena/defenses/`:
   - `v_funnel.json` — Lostkids V-shape
   - `two_layer_keep.json` — FUNNEL pattern
   - `spread_line.json` — wider, shallower (novel)
2. `planner/defense.py:build_default_defences()` — walk JSON until SP runs out.
3. `planner/defense.py:edge_block_and_remove()` — Lostkids pattern. **First verify in isolation that `attempt_remove` after same-turn `attempt_spawn` keeps the wall this turn and removes next turn — semantics matter.**
4. `planner/defense.py:refund_low_health_structures()` — walls <50%, turrets <30%.
5. `planner/defense.py:max_heap_repair()` — FUNNEL pattern. **Fix the FUNNEL bug** at line 327 (use `[1]` not `[0]` for SP cost — which is index `SP=0` in our config but `[1]` in the cost-vector tuple `[MP_cost, SP_cost]`).
6. `planner/defense.py:probabilistic_placement()` — GRETCHEN-style, but **the support-loss penalty weight (was 100× in base-game GRETCHEN) must be re-tuned for Citadel's 40-HP upgraded supports. Initial value: 25× (since upgraded supports are ~10× more durable). Final value: tune in Phase 9 MAP-Elites.**
7. `planner/defense.py:reactive_to_breach()` — uses `BreachLocationTracker`.
8. Production safety wrappers around all defense logic.

**Validation gate:** `/bestof 50 athena_v2_defense_only athena_baseline_lostkids` — Wilson lower bound ≥45% (defense alone, not expected to beat full Lostkids).

---

## Phase 3: Offensive script portfolio (5 Citadel-feasible scripts)

Common `AttackScript` interface (unchanged from v2 plan).

Five scripts:

1. **`corner_ping.py`** — port FUNNEL `CornerPing.get_spawns`. **Wall HP and Scout HP read from Citadel config**, not hardcoded. First-wave formula: `ceil(wall_HP × 0.75 / SCOUT_HP) + ping_loss`.
2. **`demolisher_line.py`** — Citadel asymmetry: Demolisher range **4.5** > base Turret range **2.5**. Sacrificial walls at y=10 cols 4-6 (or mirror). Demolishers from `[5,9]`/`[22,9]` hit y≤14 enemy structures while outside base-turret range. Citadel upgraded turrets reach 3.5 — also doesn't reach. Closes Waterloo's kryptonite.
3. **`escorted_mixed.py`** — 2 Interceptors front (Citadel base Interceptor: 40 HP, 20 dmg vs mobile only), 6-12 Scouts trail.
4. **`adaptive_hole.py`** — Harvard pattern. Side-sense weights using **Citadel** unit names: `WALL=1, TURRET=6` (was FILTER/DESTRUCTOR in original).
5. **`sd_opportunist.py`** — detects existing dead-ends in opponent's defense via BFS from each spawn edge. Fires Interceptor only when SimCore confirms ≥5 tiles travelled and dead-end on enemy side.
6. **`misdirection_counter.py`** — Lostkids pattern.

**Validation gates:**
- Each script /bestof 30 vs v13: at least 3 with Wilson lower bound > 50%.
- Portfolio /bestof 50 vs v13: Wilson lower bound > 65%.
- Portfolio /bestof 50 vs `athena_baseline_lostkids`: Wilson lower bound > 50%.

---

## Phase 4: Opponent model (Bayesian classifier + Predictors)

**Tasks:**
1. Build labeled corpus `data/eval_corpus/`. Manually label ≥50 turns from replays into 8 archetype classes (`PingCannon, DemolisherLine, AdaptiveHole, FunnelDefense, ScoutRush, InterceptorWall, SupportCannon, Mirror`). ≥10 turns per class.
2. `opponent/classifier.py` — Bayesian classifier with explicit per-archetype likelihood functions. Dirichlet prior. Posterior updates each turn.
3. `opponent/predictors/` — base class with `PredictedAction` constraint-based scoring (matches() method for hit/miss).
4. Predictors: PingCannon, DemolisherLine, Mirror, WallRemoval, InterceptorRush, EconomyShock.
5. Beta-binomial confidence: `(hits+1)/(hits+misses+2)`. Logged to `data/predictor_logs/`.
6. Defense archetype selection from classifier posterior (e.g., `PingCannon → v_funnel`).

**Validation gate:** Classifier confusion matrix on labeled corpus: per-class recall ≥70%. Predictor scoring rule explicit + tested. Phase 4 algo /bestof 50 vs Phase 3: Wilson lower bound > 53%.

---

## Phase 5: Plan search engine

**Tasks:**
1. `planner/offense.py:fast_static_eval(plan, state)` — heuristic, ~10us per call.
2. `planner/offense.py:beam_search()`:
   - Generate ~5000 candidate plans
   - Static-eval prune to top 100
   - Full SimCore eval on top 100, scored against predicted opponent action OR posterior-weighted archetypes
   - Plus 1 mandatory **no-prediction baseline candidate** (lower-bound EV)
3. **Depth-2 trigger** (revised): fires when (a) predicted opponent breach > 3 HP next turn, OR (b) we've banked MP ≥3 turns (intentional cannon charge), OR (c) game turn ≥ 90 (endgame). NOT triggered by `MP > 25` (rarely satisfied due to 25% decay).
4. `planner/arbiter.py:EconomyArbiter` — marginal utility comparison; saving threshold 1.33×; damage→SP feedback explicit.
5. **Utility weights** (3.5, support_loss_weight, 1) are tunable hyperparameters for Phase 9 MAP-Elites.
6. Production safety: top-level try/except in `planner.choose_plan()` returning a defense-only no-op turn on any exception.

**Validation gates:**
- Per-turn compute ≤8s at beam width 100 in pure Python.
- Phase 5 /bestof 50 vs Phase 4: Wilson lower bound > 55%.
- Phase 5 /bestof 50 vs `athena_baseline_lostkids`: Wilson lower bound > 65%.

**Pause-and-confirm with user before Phase 6.**

---

## Phase 6: Predictor framework integration

(Unchanged from v2: wire predictors into beam search, threshold 0.7 calibrated from logs.)

**Validation gate:** Phase 6 /bestof 50 vs Phase 5: Wilson lower bound > 53%.

---

## Phase 7: Rust hot-path port (PyO3)

(Unchanged from v2: validate Rust DIRECTLY against `engine.jar`. Python sim is debug aid only.)

**Validation gate:** Rust passes engine.jar validation independently. ≥10× sims/sec speedup. Per-turn compute ≤2s at beam width 100 with depth-2 enabled.

---

## Phase 8: Self-play eval pipeline

**Tasks:**
1. `tools/athena_tournament.sh` — round-robin {Athena variants × v13 × athena_baseline_lostkids × vendored finalist ports (Lostkids, FUNNEL, Harvard) for local evaluation only}. Wilson 95% CI per pair.
2. Regression detector threshold ≥10% (NOT 5% — inside 30-game noise floor).
3. `tools/behavioral_diff.py` — diff two algos' decisions across same replay seeds.

**Validation gate:** Single command produces ranked Wilson-CI table reproducibly.

---

## Phase 9: MAP-Elites archive (offline, scoped + Citadel-tuned)

**Tasks:**
1. Behavior descriptors: 4-D, 64 cells (offense_archetype × defense_archetype × mp_burn_rate × support_count).
2. Genome: 12 floats (utility weights including the GRETCHEN support-loss weight which Citadel needs re-tuned, planner thresholds, beam-prune cutoffs).
3. CMA-MAE update over 100 evals/cell with rotating opponent set.
4. Total: ~64 × 100 × 10s = 18h. **If single eval takes 30s in real games, scope down to 50 evals/cell (~9h) — note this is a real possibility.**
5. Runtime: classifier output → archive query → planner priors.

**Validation gate:** Archive contains ≥30 cells filled. Top-3 specialists per archetype each beat no-archive variant by ≥3% in matching matchups.

---

## Phase 10: LLM FunSearch loop

**Tasks:**
1. `tools/funsearch.py` — nightly loop with curated prompt template at `tools/funsearch_prompts.json`.
2. 10 mutations/night, each tournament-evaluated (≥30 games × 4 opponents).
3. Promote champion if Wilson lower bound on improvement > 5%.
4. Log to `data/funsearch/<date>/`.
5. Auto-rollback on 3 consecutive regressions.
6. **Cost budget**: default ~$10/night Claude API; user-configured cap.

**Validation gate:** Loop runs 5 nights unattended. Cumulative win-rate gain Wilson lower bound ≥3%.

---

## Phase 11: Replay scraping + opponent intel (ongoing)

**Tasks:**
1. Scraper from Phase 0 reused. Daily cron: download newly-played ranked replays (now includes Athena's matches once uploaded).
2. Auto-`/detailed-replay` analysis.
3. Novelty detection: classifier confidence < 50% on ≥3 turns → flag novel archetype → seed FunSearch prompt.
4. Weekly: review novel archetypes with user; manually extend classifier catalog.

**Validation gate:** New archetype detected within 3 days of first appearance.

---

## Phase 12: Production deployment + iteration

**Tasks:**
1. **Pause-and-confirm with user before first upload.**
2. **Verify production safety** — try/except + watchdog + safe-fallback all wired in shipped algo. Test by deliberately raising an exception in a unit test.
3. `/upload-algo athena_<latest>` — packages with `zipalgo_<os>`. **Upload itself is MANUAL via terminal.c1games.com "My Algos" → "Upload an Algo"** (the skill packages, doesn't upload). Plan delivery: tell user to upload, wait for confirmation.
4. Monitor leaderboard rank daily.
5. Each loss replay → Phase 11 → opponent model update → planner update → tournament-validated → re-upload.
6. Re-upload weekly minimum, max 1/day. **Verify Citadel platform's actual upload rate limits — adjust if more restrictive.**
7. **Pause-and-confirm with user before EACH live re-upload.**

**Target:** rank ≤5 by week 3, ≤3 by week 5, contention for #1 by week 6+.

---

## Production safety (non-negotiable, applies to every shipped algo)

Every algo we upload to ranked play MUST have:

1. **Top-level try/except in `on_turn()`** — on any exception, fall back to a "minimal safe turn":
   - Rebuild defenses from `defense-order.json` to whatever extent SP allows
   - No offense (skip planner)
   - Return `game_state.submit_turn()` immediately
2. **Watchdog timer** — start at top of `on_turn()`, force-fallback at 13s (2s before the 15s deploy budget). Implement as a thread + signal, or as periodic budget checks at every planner inner-loop boundary.
3. **Empty-plan handling** — if beam search returns no candidates, fall back to no-op offense turn (defense + nothing else).
4. **Config snapshot fallback** — if `on_game_start` config differs from our snapshot, use the LIVE config (it's authoritative); log the discrepancy.

These are NOT optional. Test by deliberately raising an exception in `on_turn` during a `/test-algo` run.

---

## Hard rules

- **No phase advances if its validation gate fails.** Roll back, debug, retry.
- **Sim correctness > everything else.** Frame ordering must match `engine.jar`.
- **All sim validation against `engine.jar` ground truth.**
- **Always include no-prediction baseline candidate in beam search.**
- **All numerical constants from runtime config.** Citadel != base game.
- **Production safety wrappers in every shipped algo.**
- **Pause-and-confirm at mandatory checkpoints.**
- **Statistical rigor: Wilson 95% CI lower bound EXCLUDES threshold.** N ≥ 50 for tight comparisons.
- **Use existing tools first.**

---

## Success = leaderboard rank #1.
