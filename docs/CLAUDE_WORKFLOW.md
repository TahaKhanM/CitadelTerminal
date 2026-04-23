# Workflow — Developing Citadel Terminal Algos with Claude Code

This doc describes the end-to-end loop for using Claude Code to build, iterate on, and ship winning algos. It's the "how we actually work" companion to the reference material in `docs/` and the skills in `.claude/skills/`.

---

## 0. Principles

Good algorithm development on this platform has five properties. Structure every session around them.

1. **Fast feedback.** A change must be verifiable in seconds (`test-algo`) or tens of seconds (one local match), not minutes. If you're waiting, something is wrong.
2. **Numeric discipline.** Unit stats in this competition differ from the base game and from the shipped `game-configs.json`. ALWAYS consult [`docs/UNITS_REFERENCE.md`](UNITS_REFERENCE.md) or read the live `self.config` — never rely on memory.
3. **Empirical over intuitive.** A "clever" strategy that loses a tournament 4-12 is worse than a dumb one that wins 9-7. Tournaments and replay analysis beat armchair reasoning.
4. **Small, reversible edits.** One idea per iteration. Keep a baseline algo unchanged so you can always A/B test.
5. **Context economy.** Claude's effectiveness drops as context fills with unrelated files. Narrow sessions to one task. Use `/clear` or restart sessions between unrelated work.

---

## 1. Start of session: orient Claude

When opening Claude Code in this repo:

### Recommended first message

A short, task-focused opening lets Claude autoload the right context. Good opener templates:

- **"Help me build a new algo focused on X"** — Claude will scan `CLAUDE.md`, invoke `/new-algo`, and start a fresh algo.
- **"My algo `v3` lost to the starter baseline; figure out why"** — Claude will find the replay, invoke `/analyze-replay`, and propose fixes.
- **"Tournament the last three algos and tell me which is strongest"** — Claude will invoke `/tournament`, parse results, and highlight patterns.

### What Claude should read first (automatically)

- `CLAUDE.md` (always auto-loaded) — orients Claude to repo structure.
- [`docs/UNITS_REFERENCE.md`](UNITS_REFERENCE.md) when unit math is involved.
- [`docs/TARGETING_AND_PATHING.md`](TARGETING_AND_PATHING.md) when movement/targeting is the issue.
- [`docs/STRATEGY_GUIDE.md`](STRATEGY_GUIDE.md) when brainstorming approaches.

**Don't** have Claude read the raw `Citadel Context Files/` images unless you're re-verifying a rule. The docs/ summaries are faster.

---

## 2. Ideation — pick a strategic hypothesis

Before any code, state the hypothesis in ONE sentence. Good hypotheses are:

- **Specific**: "A Scout rush spawned from [14,0] every turn 8+ will score because most algos underbuild right-side defense."
- **Falsifiable**: there's a match outcome that would clearly disprove it.
- **Mechanical**: rooted in the unit numbers and map geometry, not vibes.

Bad hypotheses: "I want a really aggressive bot" (not specific), "I want to beat the meta" (not actionable without a named opponent).

### Using Claude for ideation

Ask Claude to play brainstormer:

```
"Given the competition rules (Wall upgrade = 2 SP, Support HP = 1, Turret upgrade = 20 dmg / 100 HP),
which strategic archetypes from docs/STRATEGY_GUIDE.md look most
resource-efficient in the first 15 turns? Rank them and cite the math."
```

Claude should pull numbers from `docs/UNITS_REFERENCE.md`, not paraphrase from memory. If the answer doesn't cite specific numbers, push back.

Good ideation output is a 1-paragraph strategy description plus an expected outcome against a known opponent.

---

## 2.5. Tier 1 tuning vs Tier 5 archetype design — pick the right lever

Not every "idea" is the same size. Before writing code, decide which *tier* the change lives at. Getting this wrong wastes entire sessions.

### The two modes

| Mode | What you're doing | Signal you're looking for | Right tool |
|---|---|---|---|
| **Tier 1 tuning** | Keep the offensive archetype. Adjust defensive placements, upgrade order, thresholds, caravan sizing, resource allocation. | Win-rate delta against a *weaker* opponent. | `/bestof` with n=10+, `/tournament` **only after** multiple variants clear the baseline. |
| **Tier 5 archetype design** | Replace the offensive archetype itself — a fundamentally different way of scoring (e.g. Demolisher+Support train, Interceptor+Scout mixed wave, scripted self-destruct, own-wall path engineering, remove-and-relocate). | Does the new archetype break a known ceiling that tuning can't? | `/new-algo` + a deliberate plan from [`docs/ADVANCED_STRATEGIES_PROMPT.md`](ADVANCED_STRATEGIES_PROMPT.md) §5.2–§5.6. |

### The v13 mirror ceiling — why this distinction matters here

The v1–v19 line all share **one offensive archetype**: center-only Scouts from [13,0]/[14,0], Demolishers only when enemy front ≥ 10. Against any v13-family defense (12+ turret ring at y=11, center upgraded), that archetype deterministically ties at **40–40**:

- `/bestof v14_support_caravan v13_second_ring 20` → 0 W / 20 T
- `/bestof v18_predictive v13_second_ring 10` → 0 W / 10 T
- v13 vs v13 mirror → 40–40 every game

Three independent sessions (original, Track A, Track B amended) confirmed this. Caravan shields, Markov spawn prediction, CMA-ES hyperparameter search — none broke the tie, because the ceiling is **offensive archetype**, not defensive tuning. CMA-ES specifically reported `top10_var=0.000` against v13 because every config produces the same 40–40 outcome (no fitness gradient to climb).

Full writeup: [`memory/v13_mirror_ceiling.md`](../.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/v13_mirror_ceiling.md).

### Red flags that you're about to repeat v14/v15/v18/v19

If your plan is:

- "Add more X to v13's defense" (another turret ring, more walls, different upgrade order)
- "Tune v13's thresholds" (spawn threshold, wave size, caravan size)
- "Re-run CMA-ES / evolve.py against v13 with a different fitness function"
- "Add a spawn predictor to v13" (v13 spawns are deterministic — nothing to predict)
- "Make another caravan variant"

…you are in Tier 1 mode inside a saturated region. **Push back before writing code.** The unblock is always a different offensive archetype, not more defensive knobs.

### The Tier 5 menu (in order of preference)

From [`docs/ADVANCED_STRATEGIES_PROMPT.md`](ADVANCED_STRATEGIES_PROMPT.md) § A Tier 5 — none of these have been tried in v1–v19:

1. **§5.2 Demolisher + Support train** — Demolishers kill structures from range 4.5; upgraded back-row Supports shield them. Removes the 60-dmg/frame turret focus-fire problem entirely.
2. **§5.3 Interceptor + Scout mixed wave** — Interceptors (40 HP, 20 dmg vs mobile) soak focus-fire; Scouts trail behind to score.
3. **§5.4 Scripted self-destruct** — SD range 1.5 damage = starting HP, requires ≥5 tiles traveled. Engineer a path that guarantees high-HP mobiles detonate adjacent to the enemy wall-turret stack.
4. **§5.5 Own-wall path engineering** — Place friendly walls to force your *own* mobile units around a longer but lower-damage path, avoiding the v13 ring.
5. **§5.6 Remove-and-relocate** — Use the remove mechanic to temporarily open lanes on your side turn-by-turn, redirecting spawns through corridors the opponent's turrets don't cover.

When in doubt: if the last four iterations all tied v13 at 40–40, **stop tuning and build a Tier 5 archetype**.

---

## 3. Scaffold a new algo variant

```
/new-algo v1_scout_rush
```

This creates `algos/v1_scout_rush/` by copying `C1GamesStarterKit-master/python-algo/`. Equivalent shell command if invoking directly:

```bash
./tools/new_algo.sh v1_scout_rush
```

### Naming convention

Use a version number + description: `v1_scout_rush`, `v2_shielded_scouts`, `v3_demo_line`. This makes tournament output readable later.

### Keep a baseline

Always keep at least one algo named `baseline` (the unmodified starter) or `v0`. It's your regression-test opponent — if a new variant can't beat it, you've regressed.

---

## 4. The iteration loop

Once you have a scaffolded algo, this is the per-change cycle:

```
 ┌─────────────┐    ┌──────────────┐    ┌──────────────┐    ┌────────────┐
 │ 1. Read doc │───▶│ 2. Edit code │───▶│ 3. Syntax    │───▶│ 4. Match   │
 │    + plan   │    │    (minimal) │    │    check     │    │    run     │
 └─────────────┘    └──────────────┘    └──────────────┘    └──────┬─────┘
                                                                    │
 ┌─────────────┐    ┌──────────────┐    ┌──────────────┐    ┌──────▼─────┐
 │ 8. Commit   │◀───│ 7. Iterate   │◀───│ 6. Decide:   │◀───│ 5. Analyze │
 │    or move  │    │   or revert  │    │   keep/drop  │    │   replay   │
 └─────────────┘    └──────────────┘    └──────────────┘    └────────────┘
```

### Step 1: Read & plan

Before editing `algo_strategy.py`, have Claude:

- Read the current algo's `on_turn` / `on_game_start` so it knows what's there.
- Articulate the change: "I'll add X, delete Y, leave Z alone."
- Cite the specific rule numbers from `docs/UNITS_REFERENCE.md` that motivate the change.

Use `TodoWrite` to break complex changes into steps.

### Step 2: Edit code (minimal)

Apply the plan with `Edit` tool — one logical change per edit. Keep diffs small: it's easier to revert one concept than untangle a meta-refactor.

**Golden rules for edits:**
- Read `self.config["unitInformation"]` for numbers — never hardcode.
- Check `attempt_spawn` / `attempt_upgrade` return values — they silently no-op on failure.
- Use `debug_write` liberally (`gamelib.debug_write(...)`); you can throttle before upload.
- Don't place walls on your spawn edges (y=x for BOTTOM_LEFT, y=27-x for BOTTOM_RIGHT).

### Step 3: Syntax check (seconds)

```
/test-algo v1_scout_rush
```

or

```bash
./tools/test.sh v1_scout_rush
```

This runs `test_algo_mac` against the shipped replay — catches Python errors, import failures, and crashes on realistic frames. Takes ~5-10 seconds. If it fails, **fix before running matches**.

### Step 4: Match run

```
/run-match v1_scout_rush baseline
```

or

```bash
./tools/run.sh v1_scout_rush baseline
```

This runs one match and saves the replay to `replays/v1_scout_rush_vs_baseline_<timestamp>.replay`.

**Don't just look at the winner.** Read the engine's stdout for:
- `"Player N wins"` — obvious.
- Per-turn stats — did your MP pile up (bad, decay ate it)? Did your SP stay flat (defense not accumulating)?
- Any stderr (your `debug_write`) — did your strategy trigger when expected?

### Step 5: Analyze replay

```
/analyze-replay replays/v1_scout_rush_vs_baseline_*.replay
```

or

```bash
python3 tools/analyze_replay.py replays/v1_scout_rush_vs_baseline_*.replay
```

Look for:
- **Breach timeline** — when did each side score, from where?
- **Unit attrition** — which unit type died most? Are you losing Scouts efficiently or bleeding them into turrets?
- **Big turns** — did any single turn have 3+ breaches? That's where the game pivoted.
- **Compute time** — were you close to the 15 s budget? If >2 s, optimize.

### Step 6: Decide keep vs drop

Be ruthless. Rules of thumb:

- **Beats baseline 3+ matches in a row** → keep, run a bigger tournament.
- **Loses to baseline even once** → revert, think again.
- **Ties or close games** → run 3-5 matches before deciding (deterministic engine but any `random.` use introduces variance).
- **Crashes** → always revert. Crashes cost you ranked points.

### Step 7: Iterate or revert

Use `git` (if initialized) or `cp -r` to snapshot algos before risky changes. The `new-algo` skill supports copying from an existing algo:

```bash
./tools/new_algo.sh v2_from_v1 v1_scout_rush
```

Now you can experiment on `v2_from_v1` while keeping `v1_scout_rush` as a known-working fallback.

### Step 8: Commit

If using git, commit each kept iteration with a message explaining the hypothesis and the outcome:

```
git commit -am "v2: +reactive turrets in corner lanes — beat baseline 4/4"
```

This gives you a bisection trail when a future change regresses.

---

## 5. Tournament validation

Once you have 3+ variants that all beat baseline, run a full tournament:

```
/tournament
```

or

```bash
python3 tools/tournament.py algos/v1_scout_rush algos/v2_shielded_scouts algos/v3_demo_line C1GamesStarterKit-master/python-algo
```

This runs every ordered pair (top vs bottom matters — always play both sides). With N algos you get N × (N−1) matches. A 4-algo tournament is 12 matches — a few minutes.

### Reading tournament output

Claude should highlight:
- **Pareto winners** — algos that beat everything else.
- **Rock-paper-scissors** — algo X beats Y beats Z beats X (common in Terminal; suggests the meta isn't one-dimensional).
- **Side-asymmetry** — algo wins as Player 1 but loses as Player 2. Usually a bug (coordinate mirroring assumption) or a strategy that only works from one starting edge.
- **Crashes** — any timeouts or crashes trump win rate; they'll cost you ranked games.

### Statistical caveat

Single-match tournaments have high variance. If two algos finish within 1 win of each other, Claude should suggest running 3-5 reruns before declaring a winner.

---

## 6. Upload to the competition site

Once you have a variant that:

1. Beats baseline consistently.
2. Beats your previous leader consistently.
3. Has compute times well under 15 s (keep a 5 s safety margin).
4. Doesn't crash on `test-algo` across several replays.

...invoke:

```
/upload-algo v5_winner
```

This runs pre-flight checks (test-algo, regression vs baseline), then zips. You'll still upload the `.zip` manually to https://terminal.c1games.com/myalgos — Claude can't do the browser step.

**After upload** — click the algo tile on the "My Algos" page to refresh compile status. Once compiled it plays ranked matches automatically; replays appear under "My Global Replays".

---

## 7. Learning from ranked losses

When your uploaded algo loses a ranked match, you have a golden debugging artifact: the replay.

### Workflow

1. **Download the replay** from the website (right-click the replay → Save).
2. **Drop it in `replays/`** under a meaningful name: `replays/v5_ranked_loss_vs_<opponent>.replay`.
3. **Reproduce locally:**
   ```bash
   ./tools/test.sh algos/v5 replays/v5_ranked_loss_vs_<opponent>.replay
   ```
   This streams the opponent's states into your algo. If your algo logs with `debug_write`, you'll see what it was "thinking" at each turn of the losing game.
4. **Analyze:**
   ```bash
   python3 tools/analyze_replay.py replays/v5_ranked_loss_vs_<opponent>.replay
   ```
   Look for the turn where things turned — usually a single breach or a Demolisher wave that tipped the economy.
5. **Form a patch hypothesis.** Examples:
   - "Opponent mass-Scouts from [0, 13] on turn 7 and we only had 1 turret covering that corner."
   - "Opponent funnels through y=14 center and our corner turrets never see them."
6. **Create a new variant** (`/new-algo v6_from_v5 v5`), apply the fix, regression-test against `v5`, tournament, upload.

### Don't overfit

If you patch against one specific opponent strategy, make sure the patch doesn't regress against your baseline or your other tournament opponents. A narrow fix that loses to 3 other algos isn't a fix.

---

## 8. Subagents and parallel work

Claude Code can spawn subagents for parallel or isolated work. Use them when:

- **Researching across many replays.** Instead of Claude reading 20 replays serially, spawn a `general-purpose` subagent: "Analyze these 20 replays, find breach-location patterns, report which 3 tiles get breached most often."
- **Exploring strategy variants in parallel.** Use `isolation: "worktree"` to have each subagent iterate on a separate algo variant without conflicts.
- **Preserving main context.** A big analysis result (e.g., "read 50 matches' event logs") should be summarized by a subagent — you get the summary, not the raw data, so your main session stays lean.

Don't use subagents for:
- Trivial one-shot edits (just do them directly).
- Tightly-coupled work where you need both halves in the same conversation.

---

## 9. Memory for durable context

If you discover something non-obvious about your strategies, a specific opponent, or a mechanic you keep forgetting, tell Claude to save it to memory. Examples worth saving:

- `"remember: we settled on mirroring our defense around x=13.5 because the engine's own mirroring assumes bottom-player orientation"`
- `"remember: v3 uses a remove-and-rebuild cycle on turret [0,13]; don't refactor it"`
- `"remember: the tournament sees v5 lose to v2 specifically because v2's Demo line out-ranges v5's turrets — fix by upgrading center turrets"`

Ephemeral stuff (current task, last match result) should NOT go in memory — use the todo list or conversation context.

---

## 10. Anti-patterns

Things that waste time in this loop:

1. **Editing without reading the current algo first.** Blind edits break invariants you didn't know existed.
2. **Hardcoding unit numbers.** Always `self.config["unitInformation"][i]`.
3. **Running tournaments on every edit.** Use `test-algo` → one match first; tournament only after 2-3 kept changes.
4. **Not saving replays.** The starter kit dumps `.replay` files into its own `replays/` dir — `tools/run.sh` auto-moves them. If you ran the engine directly, manually move them to the repo's `replays/` so they persist.
5. **Ignoring compute time.** If your algo is at 8 s/turn locally, you'll timeout on the server (server is slightly slower). Profile with `cProfile` — it's usually `find_path_to_edge` called repeatedly.
6. **Commenting out instead of deleting.** When you remove a strategy, delete the code. Commented blocks rot and confuse future edits.
7. **Not reading `debug_write` output.** It's free signal. Enable warnings, then decide what to throttle before upload.
8. **Polishing one variant forever.** If v5 beats baseline 8/10 and hasn't improved in 4 iterations, upload it and START v6 from a fresh angle. Uploaded bots accumulate ranking data while you build the next one.

---

## 11. Quick-reference: commands

| Intent | Slash command | Shell equivalent |
|---|---|---|
| Scaffold new algo | `/new-algo <name>` | `./tools/new_algo.sh <name>` |
| Syntax/runtime check | `/test-algo <name>` | `./tools/test.sh <name>` |
| Single match | `/run-match <a1> <a2>` | `./tools/run.sh <a1> <a2>` |
| Best-of-N (variance-aware) | `/bestof <a1> <a2> [n]` | `python3 tools/bestof.py <a1> <a2> [n]` |
| Tournament | `/tournament <a1> <a2> ...` | `python3 tools/tournament.py <a1> <a2> ...` |
| Replay summary | `/analyze-replay <file>` | `python3 tools/analyze_replay.py <file>` |
| Turn-time profiling | `/profile-turns <replay>` | `python3 tools/profile_turns.py <replay>` |
| Dump live server config | `/inspect-config` | `./tools/run.sh _config_inspector _config_inspector` |
| Diff two algos | — | `./tools/diff_algos.sh <a1> <a2>` |
| Zip for upload | `/upload-algo <name>` | `./C1GamesStarterKit-master/scripts/zipalgo_mac algos/<name> algos/<name>.zip` |
| Rule lookup | `/competition-reference` (then ask) | — |
| Create new skill | `/skill-creator-terminal` | — |

---

## 12. Example session: end-to-end

Here's a realistic 30-minute iteration.

```
[user] I want to try a strategy that combines a Demolisher line with
       reactive Turret placement. Let's iterate on it.

[claude] Reading docs/STRATEGY_GUIDE.md §3.2 (Demolisher line) and §2.5
         (Reactive turrets). Demolisher at range 4.5 + wall at y=11 is
         starter-algo territory; I'll add reactive Turret placement on
         scored-on locations using on_action_frame events. Scaffold?

[user] yes

[claude] /new-algo v1_demo_reactive   (copies python-algo)
         Edited algos/v1_demo_reactive/algo_strategy.py: combined the
         demolisher_line_strategy with build_reactive_defense.

         /test-algo v1_demo_reactive   → clean run, 8.2s
         /run-match v1_demo_reactive baseline   → v1 wins HP 14-0

         /run-match v1_demo_reactive v1_demo_reactive   (mirror test)
                                                        → tie, 20-20

         Looks good. Want a tournament or continue iterating?

[user] one more iteration — add Support shielding for the Demolishers

[claude] /new-algo v2_demo_shielded v1_demo_reactive
         Edited to add 2 upgraded Supports at [13,13] [14,13] before
         demolisher spawns start (turn 8+).
         (Note from UNITS_REFERENCE.md: upgraded Support at Y=13 gives
         1 + 0.7 × 13 = 10.1 shield/unit — verified against the live
         competition config.)

         /test-algo v2_demo_shielded   → clean
         /run-match v2_demo_shielded v1_demo_reactive   → v2 wins HP 22-0
         /run-match v2_demo_shielded baseline           → v2 wins HP 30-0

[user] tournament all three

[claude] /tournament algos/v2_demo_shielded algos/v1_demo_reactive \
                    C1GamesStarterKit-master/python-algo
         Results:
           v2_demo_shielded: 4/4 (100%) 🏆
           v1_demo_reactive: 2/4 (50%)
           python-algo:      0/4 (0%)

         v2 is strict improvement. Upload?

[user] yes

[claude] Pre-flight: test-algo clean, regression vs baseline clean.
         Zipping...
         Created algos/v2_demo_shielded.zip (72 KB).
         Upload at https://terminal.c1games.com/myalgos
         → "Upload an Algo" → select the zip.
         Remember to click the tile to refresh compile status.
```

That's the loop. Most of your time is in steps 4-5 (matches and replay analysis); Claude handles all the mechanical glue.
