# Prompt: Build a best-in-class algo (and baseline variant)

This file contains TWO prompts for a fresh Claude Code instance opened at the repo root:

- **§ 1 — Best-algo prompt** (primary): aims for the strongest uploadable algo; multi-opponent, adaptive, performance-optimized, up to 15 iterations.
- **§ 2 — Baseline prompt** (for quick starts): a shorter, lower-bar version if you just need a working first algo fast.

The repo contains everything Claude needs. **Nothing needs to be uploaded** — `CLAUDE.md` auto-loads and points at every reference doc and skill.

---

## § 1 — BEST-ALGO PROMPT (primary)

> Copy the block below verbatim into a new Claude Code session. Expect a 60-120 minute run producing an algo that is competitive to upload.

```
You are building the STRONGEST POSSIBLE algorithm for the Citadel Terminal
competition — a special-ruleset tower-defense game. This repo is fully
scaffolded with competition rules, reference docs, helper tools, and skills.
Your job is NOT to produce a beat-the-starter baseline — that bar is too low.
Your job is to produce a ranked-competition-ready algo that demonstrably beats
multiple archetype opponents, plays adaptively, stays well under compute
budget, and passes a battery of stress tests.

ORIENTATION (read these, in order, before writing any code)
  1. CLAUDE.md                             — repo orientation
  2. docs/CLAUDE_WORKFLOW.md               — the iteration loop
  3. docs/UNITS_REFERENCE.md               — COMPETITION numbers (not base game)
  4. docs/STRATEGY_GUIDE.md                — archetypes + math
  5. docs/TARGETING_AND_PATHING.md         — engine's movement/targeting rules
  6. docs/API_REFERENCE.md                 — gamelib methods
  7. C1GamesStarterKit-master/python-algo/algo_strategy.py — one of your opponents

Use TodoWrite aggressively to track iterations and sub-tasks. Use existing
skills (/new-algo, /test-algo, /run-match, /bestof, /tournament,
/analyze-replay, /profile-turns, /inspect-config). Don't reinvent tooling —
tools/ has everything.

PHASE 0 — VERIFY THE CONFIG (mandatory, takes ~2 minutes)
  Run /inspect-config once. Record the ACTUAL values for:
    • Upgraded Support: shieldPerUnit, shieldBonusPerY
    • Turret base and upgrade: attackRange, attackDamageWalker, startHealth
    • Wall base and upgrade: startHealth, cost1
    • Resource schedule: bitDecayPerRound, bitsPerRound, coresPerRound
  If any value conflicts with docs/UNITS_REFERENCE.md, trust the server
  config and note the discrepancy in your final report.
  Note: running locally reads the shipped game-configs.json which is BASE
  GAME values. Either (a) accept that as your reference for local testing
  knowing the server differs, or (b) temporarily patch game-configs.json
  to competition values during development (then restore before upload).

PHASE 1 — BUILD A SYNTHETIC OPPONENT SUITE (mandatory, ~10 minutes)
  Don't just benchmark against the starter algo. Create THREE synthetic
  opponents under algos/ so you can test against the real meta:
    • opp_scout_rush    — floods Scouts from one corner every 2-3 turns
    • opp_demo_line     — starter's demolisher_line_strategy on steroids,
                           walls on y=11 + mass Demolishers
    • opp_turret_castle — dense double-layered walls/turrets, no offense
                           until turn 15, then Scout pressure
  Each should be ~50 lines of algo_strategy.py, deliberately one-archetype.
  These exist to SURFACE WEAKNESSES in your main algo. If your algo
  beats starter 10/10 but loses to opp_scout_rush 2/10, you learned
  something crucial before upload.

PHASE 2 — ITERATE YOUR MAIN ALGO (up to 15 iterations)
  Per iteration:
    1. Hypothesis in ONE sentence. Cite which competition rule or
       opponent weakness it exploits.
    2. Scaffold: /new-algo vN_<name> (copy from prior best if refining).
    3. Implement MINIMUM version. Every number read from self.config.
    4. /test-algo — must be clean.
    5. Quick check: /run-match vN starter.
    6. Eval gate: ./tools/eval.sh vN (must pass before proceeding).
    7. Synthetic-opponent sweep:
         /bestof vN opp_scout_rush 5
         /bestof vN opp_demo_line 5
         /bestof vN opp_turret_castle 5
       Target: ≥ 70% against EACH synthetic opponent.
    8. Profile: /profile-turns on a late-game replay from the above.
       Max turn < 2000 ms, mean < 500 ms.
    9. Report 3-sentence status.

REQUIRED CAPABILITIES IN THE FINAL ALGO (not just good scores, but the
mechanisms that produce them)
  • OPPONENT MODELING. Track enemy structure layout (where their turrets
    are, how dense their walls are, which side they favor) and ADAPT —
    don't always send Scouts to the same side. At minimum, detect
    "enemy has no front wall" vs "enemy has a solid front" and branch.
  • ON_ACTION_FRAME LEARNING. Beyond breach tracking: record where your
    units died, which of your structures took the most damage, and
    which lanes the enemy pressured. Use this signal on the next turn.
  • PHASE-AWARE ECONOMY. Different MP/SP budgets for turns 0-5, 6-15,
    16-30, 31+. Income scales with turn number; so should spend patterns.
  • UPGRADE-ON-PRESSURE LOGIC. When a specific Turret takes damage across
    multiple turns, upgrade it rather than just rebuilding.
  • PATH CACHING. Never call game_state.find_path_to_edge for the same
    (spawn_tile, state) twice in a turn. Cache within on_turn.
  • GRACEFUL DEGRADATION. If compute budget runs short (track your own
    turn time with time.perf_counter), fall back to a safe move rather
    than hanging.

SUCCESS CRITERIA (all must hold simultaneously; run ./tools/eval.sh and
the synthetic sweep to verify)
  A. Beats vanilla starter ≥ 90% over 20 games (n=10). Wilson CI lower
     bound ≥ 0.70.
  B. Beats EACH synthetic opponent (opp_scout_rush, opp_demo_line,
     opp_turret_castle) ≥ 70% over 10 games (n=5).
  C. Mirror self-match balanced — neither orientation wins more than
     4/6 (i.e., no side-asymmetry bugs).
  D. Max per-turn compute time < 2000 ms across ALL sampled replays
     (tighter than baseline's 5000 ms). Mean < 500 ms.
  E. Zero crashes across a full tournament of the main algo + all 3
     synthetic opponents + starter (6 algos × 5 = 20 matches).
     Run: python3 tools/tournament.py <final> opp_scout_rush
          opp_demo_line opp_turret_castle
          C1GamesStarterKit-master/python-algo
  F. /inspect-config output recorded and any doc discrepancies noted.
  G. Strategy is one coherent architecture, not a junk drawer. A reader
     looking at on_turn should be able to describe the algo in one
     paragraph.

PROCESS RULES
  • Keep a named baseline. After your first passing iteration, never
    delete it — it's your regression fallback.
  • One hypothesis per iteration. Don't bundle "more turrets + new
    scout spawn + reactive supports" in a single change; isolate each.
  • After every iteration that meaningfully improves win rate, tag it:
    copy the algo to algos/<name>_checkpoint_<N>/ for rollback.
  • If three consecutive iterations fail to improve win rate or crash
    the algo, pivot to a different angle — don't grind.
  • If total eval runtime on one iteration exceeds 5 minutes, parallelize
    via subagents: spawn Agent(general-purpose) tasks to run
    independent /bestof checks against different opponents in parallel.
  • When stuck for 5+ iterations, ask the user for direction rather
    than continuing to iterate blindly.

MUST NOT
  • Hardcode unit numbers. Use self.config["unitInformation"][i].
  • Place walls on your own spawn-edge diagonals.
  • Modify files in C1GamesStarterKit-master/.
  • Claim success without running ./tools/eval.sh AND the synthetic
    sweep AND the tournament — all three, output visible.
  • Keep dead code paths "in case." Delete what doesn't ship.
  • Invent strategies that ignore the rule changes (expensive Supports
    with HP 1, upgraded-Wall spam when it's no longer free, etc.).
  • Blow through the 15-iteration cap without reporting progress to
    the user at iteration 10.

REPORTING
  Per iteration (3-4 sentences):
    "Iter N: hypothesis was <X>, rooted in <rule / opponent weakness>."
    "Result: <W>/<T> vs starter; vs opp_X <W>/<T>; max compute <ms>."
    "Signal: <what you learned from analyze-replay on losses>."
    "Next: <keep + refine / revert / pivot to Y>."

  Final handoff when done (structured):
    1. Path to final algo + checkpoint history.
    2. ./tools/eval.sh full output on final algo (paste it).
    3. Synthetic-opponent sweep results table.
    4. Tournament results table.
    5. Config inspector output + any server-vs-doc discrepancies.
    6. Strategy summary: ONE paragraph describing the architecture,
       then a bulleted list of the adaptive behaviors.
    7. Known weaknesses — 2-3 specific scenarios where the algo
       underperforms, each with a candidate fix for the next iteration.
    8. Dropped variants log — algos you tried and abandoned, one
       line each explaining why.

Begin now. First message back should be your reading plan, confirmation
that /inspect-config was run, and the iteration-1 hypothesis.
```

---

## § 2 — BASELINE PROMPT (faster, lower-bar)

> Use this if you want a working first algo in ~30-60 minutes without the full best-algo rigor. Copy the block below as the first message.

```
You are building the initial competitive algorithm for the Citadel Terminal
competition — a special-ruleset version of Correlation One's Terminal
tower-defense game. This repo is already fully scaffolded with competition
rules, reference docs, helper tools, and Claude Code skills. Your job is to
produce a STRONG BASELINE ALGO: one that consistently beats the vanilla
starter-algo, has no compute-time issues, and is clean enough to iterate
on further.

ORIENTATION (read these, in order, before writing any code)
  1. CLAUDE.md                             — repo orientation
  2. docs/CLAUDE_WORKFLOW.md               — the iteration loop
  3. docs/UNITS_REFERENCE.md               — COMPETITION numbers (not base game)
  4. docs/STRATEGY_GUIDE.md                — archetypes + math
  5. docs/TARGETING_AND_PATHING.md         — engine's movement/targeting rules
  6. C1GamesStarterKit-master/python-algo/algo_strategy.py  — your opponent

Use TodoWrite to track your iterations. Use skills when they apply
(/new-algo, /test-algo, /run-match, /bestof, /analyze-replay,
/profile-turns, /inspect-config). Don't reinvent — tools/ has everything.

SUCCESS CRITERIA (ALL must hold; verify with ./tools/eval.sh)
  A. Beats vanilla starter-algo ≥ 80% over 10 games
     (`/bestof <algo> C1GamesStarterKit-master/python-algo 5`
      verdict must be "meaningfully stronger").
  B. Mirror self-match is balanced (neither side wins 6/6 — rules out
     side-asymmetry bugs from coordinate-mirroring assumptions).
  C. Max per-turn compute time < 5000 ms in any real match replay
     (`/profile-turns <replay>`).
  D. No crashes across the full eval suite.
  E. One coherent primary archetype — not a junk drawer of half-measures.

    Shortcut: `./tools/eval.sh <algo_name>` runs A-D in sequence and
    exits 0 on pass. Use it every iteration — it's your success gate.

PROCESS (repeat until success criteria met OR 8 iterations, whichever first)
  1. State the iteration's hypothesis in ONE sentence.
  2. Scaffold: `/new-algo vN_<name>` (copy from prior best if continuing).
  3. Implement the MINIMUM version of the hypothesis.
  4. Syntax check: `/test-algo vN_<name>` — must be clean.
  5. Quick check: `/run-match vN_<name> C1GamesStarterKit-master/python-algo`.
     If you lose here, investigate with `/analyze-replay` before more matches.
  6. Eval: `./tools/eval.sh vN_<name>`.
     Pass → stop. Fail → diagnose and iterate.
  7. Report status (3 sentences: hypothesis / result / next move).

STRATEGY GUIDANCE (specific to THIS competition's rules)
  • Turret UPGRADES are unusually strong here (20 dmg, 100 HP, range 3.5
    for 6 total SP). Upgrading beats spreading. Mid-game, prioritize it.
  • Wall upgrade now costs 2 SP (was free in base game). 3 SP for a 200
    HP Wall is still the best HP/SP in the game — budget for it.
  • Support HP is 1 (not 30!). They die to any stray attack. Either
    wall-protect them or treat them as one-shot shield dispensers.
  • Upgraded Support *probably* grants `1 + 0.3*Y` shield per unit
    (see UNITS_REFERENCE.md for the ambiguity). Run /inspect-config
    if you plan to build your strategy around Supports.
  • On_action_frame breach events are your reactive-defense signal.
    The starter algo uses them minimally; a smarter reactive system
    (tracks lane pressure, upgrades under pressure) is a clear edge.
  • Scout rushes on the weakest corner + demolisher line when the
    opponent stacks front = cheap solid offense. Don't get fancy.

MUST NOT
  • Hardcode unit numbers — use `self.config["unitInformation"][i]`.
  • Place walls on your own spawn-edge diagonals (blocks mobile spawns).
  • Modify files in C1GamesStarterKit-master/ (read-only vendored code).
  • Invent strategies that ignore the competition rule changes (e.g.,
    expensive Support stacks with HP 1).
  • Claim success without running ./tools/eval.sh and showing it passed.
  • Silently skip failed iterations — report what was tried and why it
    didn't work, even when keeping only the winning variant.

OUTPUT
  At end of every iteration, report:
    "Iteration N: hypothesis was <X>."
    "Result: <W-L>/<games> vs starter, max compute <T> ms."
    "Next: <keep / revert / pivot to Y>."

  Final handoff when done:
    • Path to the final algo folder.
    • Output of ./tools/eval.sh on it.
    • One-paragraph strategy summary.
    • Top 2 known weaknesses (for the next iteration to target).
    • List of other variants you tried and why they were dropped.

Begin now. First message back should be your reading plan + iteration 1
hypothesis.
```

---

## What the user needs to do

**Before sending the prompt:**
1. Open Claude Code (`claude` CLI) in the root of this repo:
   ```bash
   cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
   claude
   ```
2. The session auto-loads `CLAUDE.md`. No files need to be uploaded — the repo IS the context.
3. On macOS only: ensure binaries are unquarantined (one-time fix):
   ```bash
   xattr -d com.apple.quarantine C1GamesStarterKit-master/scripts/test_algo_mac 2>/dev/null || true
   xattr -d com.apple.quarantine C1GamesStarterKit-master/scripts/zipalgo_mac  2>/dev/null || true
   ```
4. Verify Java is installed: `java --version` (Java 10+ required).

**During the session:**
- Let Claude drive the iteration loop. Don't interrupt mid-iteration unless you see it going off-track.
- If Claude asks permission for shell commands, the pre-approved list in `.claude/settings.json` covers 95 % of what it needs. Only rarely should you be prompted.
- If Claude claims a win, ask it to show `./tools/eval.sh <algo>` output.

**After the session:**
- The final algo is at `algos/v<N>_<name>/`.
- Replays are under `replays/` for any future analysis.
- Claude's closing report tells you what to work on next.

---

## Expected session shapes

### Best-algo prompt (§ 1)
Typical session: **60-120 minutes**, 6-12 iterations.

| Phase | Typical content | Outcome |
|---|---|---|
| 0 | Read docs, run /inspect-config, record server config deltas | Recorded |
| 1 | Build 3 synthetic opponents (scout_rush, demo_line, turret_castle) | ~10 min setup |
| 2 (iter 1-2) | v1 with corner turrets + reactive; v2 adds upgrades on pressure | ~70 % vs starter |
| 2 (iter 3-5) | Add opponent detection + phase-aware economy | ~85 % vs starter, 60 % vs synthetics |
| 2 (iter 6-9) | Path caching, on_action_frame learning signals, Demo-line trigger | 90 %+ vs starter, 70 % vs each synth |
| 2 (iter 10-12) | Compute optimization (< 2 s max), mirror-match balancing | Passes full eval |
| Final | Tournament + handoff report | Upload-ready |

### Baseline prompt (§ 2)
Typical session: **30-60 minutes**, 3-5 iterations.

| Iter | Typical content | Outcome |
|---|---|---|
| 1 | Reading + plan + starter + reactive upgrades + Scout rush on low-pressure turns | ~60 % vs starter → not enough |
| 2 | Add upgraded Turrets at corners + better reactive placement | ~75 % vs starter → close |
| 3 | Add Demolisher line trigger when enemy stacks front; tighten MP usage | ~85 % → passes eval |
| 4 (optional) | Polish: mirror-match balancing, compute-time optimization | Final handoff |

If Claude is still stuck after the iteration cap, it should ask for direction — both prompts permit this explicitly.

---

## Prompt design notes (for future adjustment)

- **Two tiers on purpose.** The best-algo prompt adds ~2-3× runtime for substantially stronger output; the baseline prompt optimizes for "get something working fast." Use the right one.
- **Success criteria are measurable, not qualitative.** Hard numeric gates (Wilson CI, ms thresholds, win rates) give reproducible output; "strong" alone would produce wildly varying results.
- **eval.sh is load-bearing.** Without a single-command pass/fail gate, "run 5 quality checks" becomes a multi-step dance Claude may half-skip.
- **Synthetic opponents surface real weaknesses.** Starter algo is a soft target; benchmarking against 3 archetypes (scout rush, demo line, turret castle) catches over-specialization.
- **Required capabilities force architecture, not just score.** A win rate can mask a fragile 200-line spaghetti strategy; requiring opponent modeling, phase-aware economy, path caching, etc. forces the algo toward an extensible shape.
- **MUST NOT list fences off trap moves** (hardcoding numbers, messing with vendor dirs, overclaiming success).
- **Iteration caps prevent infinite fiddling.** Both prompts permit asking for direction after stalling.
- **Structured handoff format** means the next session starts warm — not cold-parsing "here's my algo."

## Further tightening (beyond § 1)

If you want to push even harder than the best-algo prompt:

- Add a 4th synthetic opponent that uses the **Support + Scout caravan** archetype.
- Raise win threshold vs synthetics to 80 % (up from 70 %).
- Require > 95 % win rate vs starter with n=20.
- Require < 1000 ms max compute time (down from 2000 ms).
- Mandate a **tournament against all prior checkpoints** at the end — the final algo must beat every checkpoint ≥ 70 %.
- Require the algo to carry **per-match memory across turns** (beyond `on_action_frame`) — e.g., weight which spawn tile has historically succeeded this match.
- Add a **code-quality review pass**: no function > 50 lines, no hardcoded locations except in a single `CONFIG` block at the top.

## Related files

- [`tools/eval.sh`](../tools/eval.sh) — the evaluation gate script
- [`docs/CLAUDE_WORKFLOW.md`](CLAUDE_WORKFLOW.md) — the human-readable workflow doc this prompt references
- [`docs/STRATEGY_GUIDE.md`](STRATEGY_GUIDE.md) — strategy archetypes the prompt points Claude to
- [`.claude/skills/`](../.claude/skills/) — the skills Claude will invoke
