# Oracle Successor — Autonomous Build Prompt

You are a Claude Code session. Your job is to **build the successor algorithm to `algos/oracle_pure/` (the M1Lite variant)** for the Citadel Terminal competition. Work **fully autonomously** — do not ask the user any clarifying questions, do not pause for confirmation, do not propose-and-wait. Make decisions, document them, ship.

A **separate Claude Code session is already iterating small/incremental variations** of `oracle_pure_M1Lite` (see [oracle_pure_M1Lite_VC_upload/VARIANT_C_REPORT.md](oracle_pure_M1Lite_VC_upload/VARIANT_C_REPORT.md) — a single value-function term added). **Your job is the opposite**: a complete architectural overhaul. Big changes only. If the change you are considering is the kind of thing that other session might also produce, it is too small.

Project root: `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/`. Today is **2026-04-27** (Citadel Spring 2026 deadline 2026-04-27 21:00 UTC — already very close, but treat the algo work as continuing past the deadline; the goal is the strongest algo, not a deadline scramble).

---

## ⚠️ ISOLATION REQUIREMENT (read before doing anything)

**The other session is actively writing to this repository in parallel.** You will silently corrupt their work — and they will silently corrupt yours — if you both write into the same working tree on the same branch. So you MUST work in **complete filesystem isolation** from them.

**Use a git worktree.** Before you do anything else, run:

```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
git fetch --all
mkdir -p .claude/worktrees
git worktree add .claude/worktrees/oracle-successor -b oracle-successor main
cd .claude/worktrees/oracle-successor
git status
```

From this point on, **`pwd` should always be `.claude/worktrees/oracle-successor/`**, never the original repo root. All file edits, all `Read`/`Write`/`Edit`/`Bash` calls must use absolute paths inside this worktree (or relative paths from inside it). When you reference a path like `algos/oracle_pure/oracle_core/search.py` in this prompt, the full path you use is `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/.claude/worktrees/oracle-successor/algos/oracle_pure/oracle_core/search.py`.

**Hard isolation rules** (these are stricter than the earlier "don't commit/push" rule — they govern in-worktree edits too):

1. **Never write outside your worktree.** No edits to `/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/<anything-not-under-.claude/worktrees/oracle-successor>`. The only exception is paths that are explicitly outside the repo (e.g. `~/.c1_session.json` for auth — read-only).
2. **Never modify the existing oracle_pure or oracle_pure_M1Lite_* folders, even inside your worktree.** They are the other session's working surface (the other session also has its own worktree, but the live upload folders at the repo root are shared reference). Treat all of these as read-only:
   - `algos/oracle_pure/` (the canonical M1Lite dev folder)
   - `oracle_pure_upload/`
   - `oracle_pure_M1Lite_upload/`
   - `oracle_pure_M1Lite_VB_upload/`
   - `oracle_pure_M1Lite_VC_upload/`
   - `oracle_pure_M2_upload/`
   - `oracle_upload/`
   You may **read** them freely (and you must, for grounding). You may not edit, rename, or delete them.
3. **Never modify shared infrastructure.** Treat the following as read-only too:
   - `tools/` (root-level tools — the other session may rely on them)
   - `C1GamesStarterKit-master/` (the engine; never modify)
   - `algos/athena*/`, `algos/heuristic_*/`, `algos/v13_second_ring/`, `algos/_config_inspector/`, `algos/replay_decoded/` (other algos)
   - `research/` (shared research corpora)
   - `docs/` (project documentation; if you find a doc bug, note it in your CONTEXT_HANDOFF.md instead of editing)
   - `data/` (shared leaderboard snapshots; you may add new files under your own subdir `data/oracle_v2/` if needed, but do not edit existing ones)
   - `Ranked Replays/`, `replays/oracle_pure_live/` (existing replay corpora are read-only — your new replays go to a NEW directory `replays/oracle_v2_corpus/` inside your worktree)
   - `CLAUDE.md`, `AGENTS.md`, `README.md`, `Research Report.md` (root project docs)
   - The `MEMORY.md` index and individual memory files in `~/.claude/projects/...` (the user's persistent memory — only edit if your work produces a finding worth saving across sessions, and do so via the standard memory protocol, NOT by direct edit during the build)
4. **Never commit and never push.** The worktree's branch (`oracle-successor`) stays local-only until the user inspects it. No `git commit`, no `git push`, no `gh pr create`. The user reviews the worktree and decides what to merge.
5. **Where you ARE allowed to write** (only under your worktree, all paths relative to the worktree root):
   - `algos/<algo>/` — your new algo (create from a copy of `algos/oracle_pure/` inside your worktree)
   - `<algo>_upload/` — your upload folder, mirroring the new algo
   - `replays/oracle_v2_corpus/` — replays you scrape in Phase 1
   - `tools/oracle_v2/` — any new tools you build (do not put them at the root of `tools/`)
   - `data/oracle_v2/` — any new data artifacts (priors, classifiers, etc.)
   - Inside the above subtrees you have full write authority.
6. **If you need to test against an existing algo** (e.g. to run `bestof.py` between your new algo and `algos/oracle_pure/`), **read-only is fine** — `run_match.py` only reads the opponent's source. You will NOT need to modify `algos/oracle_pure/` to use it as a benchmark target.
7. **If the other session has uncommitted work in the main checkout**, you will NOT see it in your worktree (worktrees track committed state on a branch). That is intentional — your worktree is your isolated reality. Do not try to read the other session's uncommitted state.
8. **At the end of every phase**, run `git status` from inside the worktree. The only modified/untracked paths should be inside the directories listed in rule 5. If anything else shows up, you violated isolation — investigate immediately and revert.
9. **When you finish**: leave the worktree intact. Do NOT run `git worktree remove`. Tell the user the branch name (`oracle-successor`) and the worktree path so they can `cd` into it and inspect. The user decides if/when to merge to main; the user decides if/when to clean up the worktree.

**Why this matters**: the user's memory file [`memory/v13_mirror_ceiling.md`](.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/v13_mirror_ceiling.md) and the M2 post-mortem in `oracle_pure_M1Lite_upload/CONTEXT_HANDOFF.md §16` both describe how seemingly-isolated changes silently broke other algos. The other session may have made similar discoveries you do not yet see. Worktree isolation guarantees your work and theirs do not collide until the user merges them deliberately.

---

## 0 · The non-negotiable rules

These come from the user directly (CONTEXT_HANDOFF §13) and from CLAUDE.md/AGENTS.md. Memorize them — every change you propose is judged against them.

1. **Search OWNS every decision.** No heuristic_v1-style "build_defense → build_supports → spend_hoard → search-on-leftover" pipeline. The search returns ONE plan that contains BOTH defense AND offense, and that plan is applied. The only hardcoded behavior allowed is the watchdog safe-fallback (4 corner turrets + center scouts).
2. **No hardcoded tile lists derived from observed losses.** "Move SUPPORTS_BACK to (10,11) because we saw a trap at (12,11)" is the exact pattern that got M2 rejected on the live ladder. Tile coordinates pulled from specific replays = anti-pattern. Mechanisms derived from `game_state` (e.g. "tiles unreachable by BFS after applying this plan") = potentially OK but verify against the engine, not just your BFS.
3. **No archetype labels driving discrete switches.** The F4 anti-pattern: classify opponent as "funnel" → bump `k_opp_phase1` from 1 to 2. This is forbidden. Continuous signals (per-tile pressure scalars, per-mobile self-destruct penalties) are OK.
4. **More compute ≠ better.** Three documented attempts (M1-G3, k_opp_phase1=2, k_opp_phase1=3) regressed matches. Search is candidate-limited, not compute-limited. Diversify the candidates and the opp samples; do not just scale up the existing loop.
5. **Strict-superset rule.** Every milestone must beat every Tier A opponent the prior milestone beat. ANY single regression on Tier A = REJECT the milestone. Same for Tier B (snorkeldink-v3-1 — must stay 2/2).
6. **Local validation is necessary but not sufficient.** M2 passed Tier A 10/10 and snorkeldink 2/2 locally and still regressed 0/3 vs ameyg/funnel-rush-v6/v7/v8 on the live ladder. The local benchmark suite does NOT cover the full opponent space. You must add adversarial test cases derived from the live replays (see Phase 4 below).
7. **The user values critical pushback over deferential agreement.** When the prior session's documented conclusions disagree with what you observe, document the disagreement explicitly with citations and update the docs. Do not silently override; do not blindly trust either. Example: CONTEXT_HANDOFF §15-§16 enumerates 8+ specific things the prior session was wrong about.
8. **Do not commit, push, or upload anything** unless the user explicitly asks. Produce the upload folder; the user uploads.

---

## 1 · What you are starting from

### 1.1 The current flagship: `algos/oracle_pure/` (M1Lite line)

A genuinely search-driven algo. Per-turn flow ([algos/oracle_pure/algo_strategy.py](algos/oracle_pure/algo_strategy.py:140)):

```
on_turn(turn_state):
  1. Build gamelib.GameState from turn_state JSON
  2. Adapt to sim_rs schema dict via state_adapter.adapt_game_state()
  3. result = search(gs, config, opp_model, ...)  ← all decisions here
  4. apply_to_game_state(result.best_plan, gs, config)
  5. gs.submit_turn()
```

The search ([algos/oracle_pure/oracle_core/search.py](algos/oracle_pure/oracle_core/search.py)) does:
- enumerate_plans() → ~500–2000 candidate ActionPlans (defense template × offense template cross-product)
- Phase-1 cull: every candidate × `k_opp_phase1=1` opp samples → score via `value.evaluate(sim_rs(state'))`
- Phase-2: top `phase2_top_n=30` × `k_opp=6` opp samples → re-rank
- Optional depth-2: top `depth2_top_n=3` get a single-step lookahead (cheap heuristic opp response + a hardcoded "scout from (14,0)" us response) → blended `0.6 × post1 + 0.4 × post2`
- Watchdog: 11s soft / 13s SIGALRM hard
- Telemetry: avg 394 ms/turn, ~1600 sims/turn, 3.6 % of budget consumed

Component sizes (LoC):

| File | LoC | What it does |
|---|---|---|
| `algos/oracle_pure/oracle_core/search.py` | 392 | 2-phase search + depth-2 |
| `algos/oracle_pure/oracle_core/enumerator.py` | 683 | ~13 defense templates × ~150 offense templates |
| `algos/oracle_pure/oracle_core/value.py` | 230 | HP-dominant scalar; **no spatial awareness** |
| `algos/oracle_pure/oracle_core/opponent_model.py` | 486 | Bucket prior + posterior + injected adversarial samples |
| `algos/oracle_pure/oracle_core/sim_eval.py` | 914 | sim_rs (Linux) / pysim (Mac) wrapper |
| `algos/oracle_pure/oracle_core/state_adapter.py` | 264 | gamelib → sim dict |
| `algos/oracle_pure/oracle_core/plan.py` | 368 | ActionPlan dataclass + apply |
| `algos/oracle_pure/oracle_core/action_frame_utils.py` | 714 | Per-frame trackers |
| `algos/oracle_pure/oracle_core/constants.py` | 109 | Unit/edge/resource indices |

**Read every one of these files before designing your overhaul.** The 4160 LoC is small enough to internalize in 30–60 min and you cannot improve what you do not understand.

### 1.2 The 4 currently-shipping live algo instances (your data source)

The user has **4 live instances** of the M1Lite line on terminal.c1games.com that have been gathering ranked-match replays:

| Local upload folder | algo.json `name` | algo_strategy MD5 | Notes |
|---|---|---|---|
| `oracle_pure_upload/` | `oracle_pure` | 39b6c6e… | Identical to M1Lite |
| `oracle_pure_M1Lite_upload/` | `oracle_pure` | 39b6c6e… | Canonical M1Lite |
| `oracle_pure_M1Lite_VB_upload/` | `oracle_pure` | 39b6c6e… | Identical to M1Lite |
| `oracle_pure_M1Lite_VC_upload/` | `oracle_pure_VC` | db775a1… | Variant C (breach-pressure value term) |

**Three are byte-identical M1Lite; one (VC) has a single value-function term added.** The user said "4 identical folders" — interpret loosely: 3 strict-identical + VC as "near-identical". You should scrape replays from **all 4** to maximize the corpus.

**Server-side algo IDs**: as of the 2026-04-26T21:27 leaderboard snapshot (`data/citadel_leaderboard_algo.json`), only the original `oracle_pure_upload` line had IDs (361251 / 361264 / 361265 — ratings 2167 / 2016 / 1838). M1Lite + VC were uploaded after that snapshot. **You must discover the current set of IDs at runtime** via `/api/game/algo/mine/1338` or by listing the user's algos.

### 1.3 Where the prior session's known weaknesses are documented

Trust these as **hypotheses to verify**, not facts. The prior session was wrong many times (CONTEXT_HANDOFF §15 lists 7 documented cases; §16 adds an 8th — M2 itself).

Honest known issues from prior sessions:

1. **Trap bug** (CONTEXT_HANDOFF §5.2): `defense:supports` template places supports at `(12,11)/(15,11)` which seal the y=12 launch gaps; offense self-destructs at (2,11)/(25,11). Documented in 2 specific 100-turn losses (suchir-g/python-algo-baseline match 15314226; not-tnb/python-algo-tnb match 15314197). M2 attempted to fix with BFS path-check + ALT-OUTSIDE swap and **regressed 0/3 vs ameyg** — the BFS check was over-conservative (rejected M1Lite's known-good config) and the tile swap caused launcher-selection cascade.
2. **3 unsolved counter-patterns**: `aa0/funnel-a` (left-flank scout funnel, 100 % HP loss at left-flank tiles), `ashmit/funnel-crush-before` (left-flank demolisher rush, 50 breaches at single tile (4,9)), `Egil/python-algo-siege` (siege strategy, mirror-image 3-HP losses). Both `oracle_pure` and `M1Lite` lose deterministically. Documented as needing "fundamentally different defenses than current oracle has."
3. **Single-tile breach concentration** (Variant C analysis): 6 of 8 M1Lite losses had ≥85 % of opp breaches concentrated at a single tile. Value function has zero spatial awareness — search has no signal to favor defenses covering specific gaps. (VC's attempt: weighted breach-pressure term added to value function; result still pending live-ladder validation as of 2026-04-27.)
4. **Depth-2 projection uses a hardcoded heuristic** for our next-turn move ("scout from (14,0)"). True multi-turn lookahead is absent.
5. **Opponent model cannot predict opp WALL placement** — only mobile spawns. Defensive structure layout of opp is invisible to the prediction.
6. **Posterior takes ≥3 observations to dominate prior** — opps that pivot every 5 turns evade adaptation.
7. **Predicted live ELO with M1Lite: 1700–1900**. Public top algos sit at **2200+**. The gap is *algorithmic depth*, not parameter tuning.

---

## 2 · The ORDERED pipeline you will execute

You are fully autonomous. Move through these phases without prompting. Mark each phase done in your TodoWrite list as you go. Each phase has a concrete deliverable — produce it and write it to disk before moving to the next.

### Phase 0 — Ground yourself (≤ 30 min wall time)

1. Read [CLAUDE.md](CLAUDE.md), [AGENTS.md](AGENTS.md), [docs/UNITS_REFERENCE.md](docs/UNITS_REFERENCE.md), [docs/STRATEGY_GUIDE.md](docs/STRATEGY_GUIDE.md), [docs/TARGETING_AND_PATHING.md](docs/TARGETING_AND_PATHING.md), [docs/GAME_RULES.md](docs/GAME_RULES.md) end-to-end. These are the rules of the game.
2. Read [oracle_pure_M1Lite_upload/CONTEXT_HANDOFF.md](oracle_pure_M1Lite_upload/CONTEXT_HANDOFF.md) end-to-end (1369 lines, ~30 min — required). Pay special attention to §15 (skeptical verification: 7 documented cases of the prior session being wrong) and §16 (M2 rejected, lessons).
3. Read all 9 files in `algos/oracle_pure/oracle_core/`. Take notes on what each component currently does and what its blind spots are.
4. Read [algos/oracle_pure/REPORT.md](algos/oracle_pure/REPORT.md) (the original architecture doc) and [algos/oracle_pure/VALIDATION.md](algos/oracle_pure/VALIDATION.md).
5. Read [algos/athena_v3_planner/FINAL_REPORT.md](algos/athena_v3_planner/FINAL_REPORT.md) — a parallel attempt at building a planner with a different architecture (static defense + Bayesian opponent classifier + replay-trace evaluation). It is NOT what you should copy, but it has useful empirical findings about opponent classification (LOO-CV 0.489 — poor — exposes how brittle naive classifiers are).
6. Read [memory/winning_architecture.md](.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/winning_architecture.md) and [memory/finalist_corpus.md](.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/finalist_corpus.md) — synthesized findings from 8+ public top-tier algos.

**Deliverable** (inside your worktree): a file at `algos/oracle_v2_<your_name>/NOTES_PHASE0_GROUNDING.md` that summarizes: (a) the M1Lite architecture in your own words, (b) the 5 most architecturally-significant blind spots you identified from reading the code, (c) the 3 most important things you disagree with from the prior session's analysis.

Pick a name for the new algo. Suggested: `oracle_v2_planner` or `oracle_chronos` or whatever you want. Use it consistently. Refer to it as `<algo>` below. The first time you create the directory, do it via `cp -r algos/oracle_pure algos/<algo>` from inside the worktree — this gives you a clean copy you may freely edit, leaving the original `algos/oracle_pure/` untouched (per isolation rule 2).

### Phase 1 — Scrape the live replays (≤ 60 min wall time)

You need **as many live ranked replays from the 4 instances as possible** — these are the empirical evidence of where M1Lite actually fails.

Two paths are available. Use whichever works first.

**Path A: scrape_ranked_replays.py (preferred — already built, faster, no browser).**

Auth cookie at `~/.c1_session.json` (`{csrftoken, sessionid}`). Verified endpoints (per `memory/citadel_live_api.md`):
- `GET /api/game/algo/mine/1338` — list **YOUR** active algos in competition 1338 (returns IDs)
- `GET /api/game/algo/{id}/matches` — public, no auth needed — match history with rating-at-time-of-match for both sides
- `GET /api/game/replayexpanded/{match_id}` — auth-gated — full replay JSON

Steps:
1. Hit `/api/game/algo/mine/1338` to get the live IDs of all 4 oracle instances. (The user has 6 total team WICK algos, but only the 4 oracle ones matter here.)
2. For each ID, hit `/api/game/algo/{id}/matches` to enumerate all matches.
3. For each match, download the replay via `/api/game/replayexpanded/{match_id}` and save to `replays/oracle_v2_corpus/{algo_id}_{result}_{opp_user}_{opp_algo}_t{turns}_{match_id}.replay`.
4. Skip duplicates — many opponents have been faced multiple times across instances.

`tools/scrape_ranked_replays.py` already implements most of this. Read it, extend if needed, run it. Existing local replay store at `replays/oracle_pure_live/` (133 files) is your **fallback corpus** — start with it if scraping fails.

**Path B: Claude-in-Chrome MCP (fallback — only if Path A is blocked).**

The user has the Claude-in-Chrome MCP installed (`mcp__Claude_in_Chrome__*` tools — check the deferred tool list and load via `ToolSearch` with `query: "Claude_in_Chrome", max_results: 30`). If `scrape_ranked_replays.py` cannot get past auth or rate-limiting, you can:
- Open `https://terminal.c1games.com/myAlgos` in a managed Chrome
- Navigate to each algo's match history
- Trigger the replay-viewer endpoint and capture the network calls
- Use `harvest --browser chrome` mode of `scrape_ranked_replays.py` (which logs every XHR) to find the real replay-download URL pattern if it has changed

**Deliverable** (inside your worktree): ≥ 100 fresh replay files in `replays/oracle_v2_corpus/` (this is a NEW directory — `replays/oracle_pure_live/` is the prior session's corpus and is read-only per isolation rule 3). Organized by `{algo_id}_{result}_{opp_user}_{opp_algo}_t{turns}_{match_id}.replay`. A summary file `replays/oracle_v2_corpus/INDEX.md` listing total counts (per algo, per opponent, per result), the date range, and which scraping path was used.

### Phase 2 — Deep replay analysis (≤ 4 hours wall time)

The goal is to find **systemic weaknesses**, not individual replay bugs. M1Lite plays ~1500-plan search per turn — when it loses, it is because some category of state is being mis-evaluated, not because of a typo. You are looking for **patterns** that appear across many losses.

Suggested analysis dimensions (do all of them; you have multiple `Agent` subagents available for parallelization):

1. **Loss bucket clustering**: For every loss, categorize by:
   - Final HP delta (close losses vs blowouts)
   - Turn count (early collapse vs 100-turn HP-tiebreak)
   - Opponent archetype (heuristic-spawned, search-driven, finalist-style funnel/demo-train, hand-crafted siege)
   - Failure mode (overwhelmed defense / starved offense / pathing trap / shield-stack outscaled / etc.)
2. **Spatial breach analysis**: For every loss, where did opp breaches happen? Is there a gap-density distribution? VC's analysis found 6/8 losses had ≥85 % concentration at a single tile — verify on the larger corpus.
3. **Self-destruct analysis**: For every match (win and loss), how much of our MP self-destructed in our own territory vs reached opp's edge? The trap bug is one cause; pathing-around-our-own-walls is another. Quantify the "wasted MP fraction."
4. **Search-decision analysis**: extract the `[oracle_pure]` debug-write lines from each replay — what plans did the search actually pick? What does the top-3 distribution look like? Is search picking the same template repeatedly (which would suggest enumerator monoculture) or genuinely exploring?
5. **Opponent-model accuracy**: when oracle predicted opp's spawn signature, how often did the prediction match? The opp model is supposedly the bottleneck — verify on real data.
6. **Compute utilization**: are there matches where the watchdog tripped or wall-clock spiked? Are there matches where the search was clearly under-utilized (early game with few candidates, late game with no MP)? Is the budget being spent where it matters?
7. **Comparative slice — wins vs losses against the SAME opponent**: For opponents that beat us sometimes and lose to us other times, what differs? This isolates the opp's behavior from our own.
8. **Compare to top-tier algos in `research/finalist_repos/`**: The Lostkids/aa0/funnel-rush algos are public Season-1 finalists. What do they do that we cannot match? Static skeleton + adaptive offense? Heavy support stacking? Demolisher trains?

**Deliverable**: `algos/<algo>/REPLAY_ANALYSIS.md` (≥ 4000 words) with:
- Loss-bucket distribution (table with counts and percentages)
- Top-5 systemic failure mechanisms ranked by HP-loss attribution
- Concrete code-level hypotheses for each: which oracle component is the culprit (search, value, opponent_model, enumerator, depth-2), what's the mechanism, what evidence supports it
- A list of **adversarial test scenarios** you will use in Phase 4 to validate the overhaul (replay-derived states + expected behavior)

Parallelize: spawn 3–5 `Agent` subagents (subagent_type=general-purpose), each takes one analysis dimension, dumps results to a temp file, you synthesize.

### Phase 3 — Design the overhaul (≤ 2 hours wall time)

The other parallel session is doing **small, surgical** changes (e.g. VC = 1 value-function term). You are NOT doing that. You are designing **a major rework** that changes how the algorithm actually thinks.

Big-change candidates the prior session identified as "real algorithmic gaps" but never built:

- **Multi-turn search depth ≥ 3 with proper opp action chains.** The current depth-2 is heuristic-only. A real lookahead search would project 3+ turns with sampled opp responses at each level (chance nodes), not the cheap "scout-from-center" stub. This addresses snorkeldink-style cumulative-pressure attacks that depth-2 cannot see.
- **Spatial value function.** Currently a turret at (0,13) and a turret at (4,11) score identically per HP. Add per-tile coverage / threat-coverage / path-density terms derived from `game_state` geometry — not from observed losses. This is what makes the search prefer defenses that cover the actual contested gaps.
- **Adversarial opponent simulation, not bucket sampling.** The current opp model samples from an empirical bucket distribution. Top algos likely model opp as a *responding* search agent: at each turn, simulate "what would a strong opp do given my plan?" — i.e. minimax (or expectiminimax with sampling) rather than independent-sample. Even a 1-ply opp-best-response would change the picture dramatically, because the *worst-case* over a small set of opp responses is what matters, not the *average*.
- **Plan diversity beyond template cross-product.** Enumerator currently emits ~13 × ~150 templates. Genuine algorithmic diversity could come from: monte-carlo random plan generation seeded by sim-driven feedback; CFR-style iterative best-response refinement; learned plan embeddings from successful past plays. A pure-random "noise plan" generator that emits N plans the templates would never produce, gated by feasibility, may surface plays the templates cannot.
- **Opp WALL prediction.** The current opp model predicts opp mobile spawns only. Predicting the structural layout opp will build (or has built) is critical for accurate sim_rs evaluation — without it, our offense plans are evaluated against an incorrect future opp board.
- **Integrated offensive viability oracle.** Instead of a BFS path check (which M2 proved is over-conservative — fails on M1Lite's working config), use **sim_rs itself as the path validator**: spawn one synthetic test mobile of each type from each candidate launcher in the post-deploy state and measure how far it gets / how much damage it absorbs. This uses the actual engine, not an approximation. Cost: ≤ 100 sims/turn, well within budget.
- **Self-play training of value weights or opponent prior.** With sim_rs at 14 K sims/sec and the M1Lite agent itself, you can run thousands of self-play rollouts and use the outcomes to fit the value-function weights or to enrich the opponent prior. This is NOT machine-learning-as-magic — it's empirical regression on simulated data, with the learned object being scalar weights or count tables, not a neural network.
- **MCTS / UCT instead of two-phase search.** The current two-phase cull is essentially a "best-of-K samples" tournament. UCT with progressive widening would allocate compute to promising plans more efficiently and would naturally produce deeper trees. You have a budget of ~10 K sims/turn server-side and a candidate space of ~2000 — UCT is well-matched.

**You must pick at least 2–3 of these (or invent better) and design them concretely.** A "complete overhaul" is not a single new component bolted onto M1Lite. It is a re-think of the core decision loop.

**Deliverable**: `algos/<algo>/OVERHAUL_DESIGN.md` (≥ 3000 words) with:
- The 2–3 architectural changes you will make
- A diagram of the new per-turn flow (replacing the M1Lite flow above)
- For each change: mechanism, why it addresses one of the systemic weaknesses from REPLAY_ANALYSIS.md, expected compute cost, fallback if it fails to ship
- Explicit anti-pattern check: for each change, demonstrate it does NOT violate rules 1–4 above. Quote the rule and explain why your change is in compliance.
- Implementation order (smallest-blast-radius first, so you can verify each piece in isolation)

### Phase 4 — Build adversarial simulations (≤ 3 hours wall time)

The user explicitly required: **"can also create its own tests by building simulations that identify or replicate oracle_pure_m1_lite's weaknesses based on the replays and then tests its new and improved strategy based on that."**

This is your insurance against the M2 failure mode (passed local Tier A 10/10, regressed live).

Build a **scenario-based test harness** that loads replay-derived states and runs the new algo against:

1. **Replay-state replay**: For each loss in REPLAY_ANALYSIS.md's adversarial scenarios list, extract the sim state at the turn where M1Lite started losing. Run new algo from that state with the same opp behavior (recorded from the replay). Compare HP trajectory.
2. **Synthetic opponent harness**: Build minimal opp algos that replicate the **systemic** behaviors of the top losing opponents (left-flank funnel, single-tile demo drill, siege, support-stack, snorkeldink-style demo train). These are NOT the actual opponent code — they are minimal reproducers of the *attack pattern* M1Lite cannot handle. Use them as deterministic validation opponents going forward.
3. **Simulated self-play**: Run new algo vs M1Lite N=20 with `tools/bestof.py`. Wilson 95 % CI lower bound > 0.5 = winning the head-to-head.

**Deliverable**:
- `algos/<algo>/tests/test_adversarial_scenarios.py` — pytest file with one test per replay-derived scenario, asserting the new algo does measurably better than M1Lite on that scenario
- `algos/<algo>/tests/synthetic_opponents/` — folder with minimal-reproducer opponent algos (each is a normal `algo_strategy.py` you can pass to `run_match.py`), one per identified systemic attack pattern
- `algos/<algo>/tests/test_self_play_vs_m1lite.py` — bestof harness asserting Wilson LB > 0.5

### Phase 5 — Implement the overhaul (≤ 8 hours wall time)

Now the actual work. Recommended scaffolding:

1. Create `algos/<algo>/` by deep-copying `algos/oracle_pure/`. Same file layout. Update `algo.json` to `{"name": "<algo>"}`.
2. Implement Change #1 (smallest blast radius). Add unit tests. Run Tier A locally — must be 10/10. Run snorkeldink — must be 2/2. Run synthetic-opponent suite from Phase 4 — must improve over M1Lite. If any fails: investigate, then either fix or revert that change.
3. Implement Change #2. Same validation.
4. Implement Change #3. Same validation.
5. After each change ships, update `algos/<algo>/CHANGELOG.md` with: the mechanism, the validation results, the regression risk (per the M2 lesson — what could break that the local suite would not catch?).

**Hard rules during implementation**:
- Never mock `sim_rs` in tests. The whole project insists on engine-truth (`memory/engine_semantics.md`, `algos/athena/sim/SIM_PARITY.md`). If a test would need sim_rs to be mocked, the test is wrong; rewrite it.
- The wall-clock per turn must stay ≤ 1.5 s on local pysim across the Tier A suite. (Server-side sim_rs is 5× faster, so this gives ~0.3 s server-side average — far below the 11 s budget.)
- The watchdog must not fire across ANY of the validation matches. If it does, the search is mis-budgeted; fix it.
- If a candidate change requires editing `state_adapter.py`, `sim_eval.py`, or anything in `bundled_sim_rs/` / `vendored_sim/`, **stop and reconsider**. Those are the engine-parity surface and the prior session burned a multi-day investigation on edge-ID bugs there. Almost no overhaul needs to touch them.

### Phase 6 — Validate & ship (≤ 2 hours wall time)

Run the complete validation matrix:

| Tier | Target | Must pass |
|---|---|---|
| **Tier A** (regression floor — 10 matches, 5 opps × P1+P2) | v13_second_ring, python-algo (starter), heuristic_v1, Lostkids/python-2l-aet, funnel_INTER | 10/10 |
| **Tier B** (snorkeldink breakthrough — 2 matches) | research/finalist_repos/terminal-c1/snorkeldink-v3-1 | 2/2 |
| **Tier C** (head-to-head vs M1Lite — Wilson 95 %) | algos/oracle_pure (M1Lite is the current main) | LB > 0.5 over n=20 |
| **Tier D** (synthetic opponents from Phase 4) | each of your minimal-reproducer attack-pattern algos | Each: better than M1Lite by measurable HP delta |
| **Tier E** (telemetry health) | per typical Tier A match | avg sims/turn ≥ 1000; avg wall ≤ 1.5 s; max wall < 2 s; no watchdog fires; no on_turn exceptions |

**ANY single failure on Tier A or Tier B = revert the offending change and try a different approach.** Do not ship a milestone that regresses.

If everything passes: create the upload folder by copying `algos/<algo>/` to `<algo>_upload/` at the repo root. The user uploads it manually. Document the validation in `<algo>_upload/REPORT.md` (architecture summary + validation results + honest expected-ELO range with reasoning chain) and `<algo>_upload/CONTEXT_HANDOFF.md` (so the next session has context).

**Do NOT** zip, push, commit, or upload anything. Do NOT modify `oracle_pure_M1Lite_upload/` or the other live folders. The folder you create is the deliverable.

---

## 3 · Tools at your disposal

- `tools/scrape_ranked_replays.py` — auth-cookie based replay scraper (preferred for Phase 1)
- `tools/bestof.py` — parallel matches with Wilson CI (your Tier C / Tier D harness)
- `tools/tournament.py` — round-robin between many algos
- `tools/analyze_replay.py` / `tools/detailed_replay.py` — single-replay introspection
- `tools/batch_replays.py` — corpus-level statistics (use this in Phase 2)
- `tools/extract_replay_features.py` — 14-feature vectors per replay
- `tools/profile_turns.py` — per-turn compute timing
- `tools/replay_to_actions.py` / `tools/replay_bridge.py` — extract sim-state from replay
- `algos/_config_inspector/` — algo that dumps the live server config (run via `/inspect-config` skill)
- Skills: `/bestof`, `/run-match`, `/test-algo`, `/analyze-replay`, `/detailed-replay`, `/profile-turns`, `/competition-reference`, `/batch-replays`, `/inspect-config` — invoke via the Skill tool when applicable
- MCP servers (load via `ToolSearch`):
  - `mcp__Claude_in_Chrome__*` — browser automation (Phase 1 fallback; the cookie path is preferred)
  - `mcp__computer-use__*` — desktop automation (only if Chrome MCP can't reach the site)
  - `mcp__scheduled-tasks__*` — schedule recurring agents (probably not needed)
- Subagents: use `Agent` with `subagent_type=general-purpose` or `Explore` aggressively. Phase 2 and Phase 5 both have parallelizable workloads.
- Sim infrastructure:
  - `algos/oracle_pure/bundled_sim_rs/` — Rust sim_rs wheel (Linux x64; loads on the live server only)
  - `algos/oracle_pure/vendored_sim/` — pure-Python pysim fallback (Mac local)
  - `algos/athena/sim_rs/` — sim_rs source (Cargo + maturin); rebuild only if you change engine semantics, which you should not
  - Parity gate: `algos/athena/sim/SIM_PARITY.md` (STRICT 19-col zero / CASCADE 4-col <1 % — hold the line)

---

## 4 · Validation opponents — local paths

```
TIER A (regression floor — must remain 10/10):
  algos/v13_second_ring
  C1GamesStarterKit-master/python-algo
  algos/heuristic_v1
  research/finalist_repos/Terminal-Lostkids/python-2l-aet
  research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER

TIER B (breakthrough indicator — must remain 2/2):
  research/finalist_repos/terminal-c1/snorkeldink-v3-1

TIER C (head-to-head, current main):
  algos/oracle_pure                      ← this IS the canonical M1Lite

USEFUL EXTRA OPPONENTS (consider adding to your local suite if you can):
  research/finalist_repos/Terminal-Lostkids/python-2l            (Lostkids base)
  research/finalist_repos/terminal-c1/snorkeldink-v3-2/v3-3      (snorkeldink variants)
  research/finalist_repos/terminal-c1/snorkeldink-AdapDef        (adaptive variant)
  research/finalist_repos/terminal-c1/algo-1, frumblesnatch-v1   (other top finalists)
  research/finalist_repos/terminal_c1_gt/*                       (GT corpus)
```

`algos/heuristic_v2/` exists but recent error files suggest it has runtime issues — verify it loads before using.

---

## 5 · Local match invocation

```bash
python3 C1GamesStarterKit-master/scripts/run_match.py <algo1_dir> <algo2_dir>
# Replay lands in C1GamesStarterKit-master/replays/*.replay
# Move to replays/oracle_v2_corpus/ for persistence
```

`tools/bestof.py <a> <b> [-n 5]` runs 2n matches in parallel and reports Wilson 95 % CI. Use absolute paths.

---

## 6 · The shape of the deliverable

When you are done, the user expects to find — **inside your worktree at `.claude/worktrees/oracle-successor/`**, not at the original repo root — the following layout. The user will `cd` into the worktree to inspect.

```
algos/<algo>/                            # canonical dev folder
├── algo_strategy.py                     # the new entry point
├── algo.json                            # {"name": "<algo>"}
├── run.sh
├── oracle_core/                         # rewritten/extended
│   └── *.py
├── data/
│   ├── citadel_config_snapshot.json
│   ├── opp_model_priors.json (or your replacement)
│   └── ...
├── tests/
│   ├── test_components.py
│   ├── test_adversarial_scenarios.py    # Phase 4 deliverable
│   ├── test_self_play_vs_m1lite.py      # Phase 4 deliverable
│   └── synthetic_opponents/
├── bundled_sim_rs/                      # copied from oracle_pure
├── vendored_sim/                        # copied from oracle_pure
├── gamelib/                             # copied from oracle_pure
├── NOTES_PHASE0_GROUNDING.md
├── REPLAY_ANALYSIS.md
├── OVERHAUL_DESIGN.md
├── REPORT.md                            # final architecture + validation
├── VALIDATION.md                        # per-match results
├── CONTEXT_HANDOFF.md                   # for the next session
└── CHANGELOG.md                         # per-change record

<algo>_upload/                           # ready-to-upload (folder, not zip)
└── ... (mirror of the above, minus the development docs)

replays/oracle_v2_corpus/                # what you scraped in Phase 1
├── INDEX.md
└── *.replay

tools/oracle_v2/                         # any new tools (not at root tools/)
data/oracle_v2/                          # any new data artifacts
```

(All of the above are RELATIVE to the worktree root `.claude/worktrees/oracle-successor/`. Nothing is written outside the worktree, per isolation rule 1.)

---

## 7 · The single most important meta-instruction

**You are not the prior session.** The prior session was wrong many times — about the trap mechanism, about hardcoded fixes being structural, about M2 being safe to ship, about funnel-archetype-naming being equivalent to funnel-archetype-behavior. You will be wrong too. The way you minimize damage:

- Treat every prior conclusion as a hypothesis. When you have time, verify it. When you do not, flag your reliance on it.
- Be ruthless about regression-detection. The strict-superset rule exists because subtle regressions have shipped in this project before — your changes will produce some too unless you have specific defenses against them.
- Document what you did and why, not just what shipped. The next session needs to be able to reproduce your reasoning, not just your code.
- When you finish, the user's expectation is **a measurably stronger algo backed by replay-derived analysis and sim-validated improvements**, not "I made some changes that pass Tier A." If your overhaul does not beat M1Lite head-to-head with Wilson LB > 0.5 on n ≥ 20, it is not ready to ship — go back to Phase 3 and try a different design.

Begin Phase 0 now.
