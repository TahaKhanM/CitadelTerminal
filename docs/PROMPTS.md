# Reusable Prompts for Claude Code Instances

Two prompts for the Citadel Terminal iteration loop. Each runs in a **fresh Claude Code session** opened at the repo root. The repo is committed and all tools/docs/replays are visible.

**Workflow**: run Prompt 1 → read its report → run Prompt 2 with the report path.

Before pasting either prompt, fill in the `[PLACEHOLDERS]` with current values.

---

## Prompt 1 — Replay Analysis

Paste this into a fresh Claude Code session. It reads the downloaded replays, produces a structured report, and does NOT modify any algo code.

```
You're analysing official ranked replays for the Citadel Terminal competition.

CURRENT STATE
  Algo:     [ALGO_NAME]           (e.g., v12_sidelane_spend)
  Our ELO:  [OUR_ELO]             (e.g., 1710)
  Position: [POSITION]/[TOTAL]    (e.g., 7/8)

Replays are in replays/ following the convention:
  [ALGO_NAME]_vs_[OPP_ELO]_elo_(win|loss).replay

ORIENT (read in order, before doing anything)
  1. CLAUDE.md
  2. docs/UNITS_REFERENCE.md
  3. docs/STRATEGY_GUIDE.md
  4. docs/OBTAINING_REPLAYS.md
  5. docs/ITERATION_HISTORY_V1_V11.md
  6. algos/[ALGO_NAME]/algo_strategy.py

STEPS

1. List replays and verify naming:
     ls replays/[ALGO_NAME]_vs_*_elo_*.replay
   If fewer than 3, tell me to download more losses.

2. Batch analysis (ELO-aware):
     python3 tools/batch_replays.py \
       replays/[ALGO_NAME]_vs_*_elo_*.replay \
       --me [ALGO_NAME] --my-elo [OUR_ELO] --top 15

3. Deep-dive EVERY loss, strongest opponent first:
     python3 tools/detailed_replay.py <loss_replay>
   For each, run sections: economy, upgrades, scoring, spawns,
   shielding, attrition, compute. Then --turn on the pivotal turn.

4. Deep-dive any upset wins (wins above our ELO):
     python3 tools/detailed_replay.py <upset_win_replay>
   Extract what worked against stronger play.

5. Read our algo source:
     Read algos/[ALGO_NAME]/algo_strategy.py
   Correlate replay findings against the actual code. Where does
   the code fail to handle what strong opponents did?

6. Extract the live server config from one official replay:
     python3 tools/detailed_replay.py <any_replay> --section config
   Compare against docs/UNITS_REFERENCE.md. Note any discrepancies.

REPORT (write to docs/REPLAY_ANALYSIS_[DATE].md)

  § 1 EXECUTIVE SUMMARY (4-6 sentences)

  § 2 PER-LOSS POSTMORTEM (one subsection per loss, strongest first)
    For each loss:
      ## Loss vs [OPP_ELO] ELO (+[GAP] gap)
      - Enemy opening (turns 0-10): structure shape, turret positions
      - Enemy attack archetype: scout rush / demo line / interceptor
        bomb / support caravan / mixed
      - Turn of first breach against us and what caused it
      - Game-deciding turn: what changed, with event-level detail
      - Breach tiles: which of our tiles were exploited
      - Our algo's response: what did our code TRY to do? (cite the
        specific function/line in algo_strategy.py)
      - Root cause: 1-2 sentence diagnosis

  § 3 UPSET WIN POSTMORTEM (if any)
    What we did differently vs our losses. Which code paths fired.

  § 4 CROSS-REPLAY PATTERNS
    - Breach tiles unique to losses (not overall): tile → count
    - Enemy spawn tiles in losses only
    - Turn-range pattern: do we lose early, mid, or late?
    - Economy pattern: SP/MP curve comparison (us vs opponent)

  § 5 RANKED HYPOTHESES FOR NEXT VERSION (3-7, ordered by expected impact)
    Each must be:
      - One sentence, falsifiable
      - Cite which loss(es) motivated it
      - Reference a specific tile, turn range, or mechanic
    Example:
      "Add an upgraded Turret at [23,10] covering the (23,9) breach
       tile that cost us 36 HP across 3 losses (§2.1, §2.2)."

  § 6 WHAT TO PRESERVE
    Top 3 strengths of the current algo that should NOT be changed.

  § 7 RED FLAGS
    - Compute time spikes (any turn >2000 ms)
    - Crashes or errors
    - Config drift from docs/UNITS_REFERENCE.md

  § 8 LANGUAGE VERDICT
    If any hypothesis requires >3s compute/turn → flag Rust.
    Otherwise → stay Python.

RULES
  - Every claim must reference a specific replay filename, turn, tile.
  - No vague recommendations. "Improve defense" is not actionable.
    "Add upgraded Turret at [X,Y] to cover breach tile [A,B]" is.
  - Keep under 500 lines.
  - Finish by printing the report path.
```

---

## Prompt 2 — Build a Better Algorithm

Paste this into a fresh Claude Code session AFTER the analysis report exists. It iterates the algo using the report's hypotheses.

```
You're building the next version of the Citadel Terminal algo, improving
on the current deployed version.

CURRENT STATE
  Current algo:      algos/[CURRENT_ALGO]   (e.g., algos/v12_sidelane_spend)
  Our ELO:           [OUR_ELO]              (e.g., 1710)
  Analysis report:   docs/[REPORT_FILE]     (e.g., docs/REPLAY_ANALYSIS_2026-04-23.md)

ORIENT (read in order)
  1. CLAUDE.md
  2. The analysis report at docs/[REPORT_FILE] — read §1, §2, §5 carefully
  3. algos/[CURRENT_ALGO]/algo_strategy.py
  4. docs/UNITS_REFERENCE.md
  5. docs/ITERATION_HISTORY_V1_V11.md — what was tried before, avoid repeats
  6. docs/CLAUDE_WORKFLOW.md

SUCCESS CRITERIA (ALL must hold)
  (A) Beats [CURRENT_ALGO] ≥ 70% over 20 games (n=10):
        python3 tools/bestof.py <new> algos/[CURRENT_ALGO] 10
      CI lower bound ≥ 0.55.
  (B) Beats each synthetic opponent ≥ 75% over 10 games:
        python3 tools/bestof.py <new> algos/opp_scout_rush 5
        python3 tools/bestof.py <new> algos/opp_demo_line 5
        python3 tools/bestof.py <new> algos/opp_turret_castle 5
  (C) Mirror self-match balanced (no side wins 5+/6).
  (D) Max compute < 3000 ms, mean < 300 ms.
  (E) No crashes across full eval: ./tools/eval.sh <new>
  (F) At least 2 of the report's §5 hypotheses addressed in code.

PROCESS (up to 10 iterations)

  For each iteration:
    1. State hypothesis in ONE sentence. MUST cite which report
       section and which loss motivated it.
    2. Scaffold: /new-algo vN_<name> (copy from best passing variant).
    3. Implement MINIMUM version. All unit numbers from self.config.
    4. /test-algo vN_<name>
    5. Quick match: /run-match vN_<name> [CURRENT_ALGO]
       If loss, /analyze-replay the result before continuing.
    6. Eval gate: ./tools/eval.sh vN_<name>
    7. Regression bar:
         python3 tools/bestof.py vN_<name> algos/[CURRENT_ALGO] 10
       Must hit ≥ 70%.
    8. Synthetic sweep (each ≥ 75%):
         python3 tools/bestof.py vN_<name> algos/opp_scout_rush 5
         python3 tools/bestof.py vN_<name> algos/opp_demo_line 5
         python3 tools/bestof.py vN_<name> algos/opp_turret_castle 5
    9. Hypothesis-specific check: run /detailed-replay on one match,
       verify the specific weakness from the report improved.
    10. Report (4 lines):
          "Iter N: hypothesis addressed §[report section]."
          "Result: vs [CURRENT_ALGO] W/T, synthetic sweep [scores]."
          "Evidence: [metric that changed, with numbers]."
          "Next: keep / refine / pivot."

STRATEGY NOTES (competition-specific)
  • Upgraded Support at Y=13 gives 10.1 shield/unit (shieldBonusPerY=0.7).
    Upgraded Support HP is 40. This is HIGH VALUE if wall-protected.
  • Upgraded Turret: 20 dmg, 100 HP, range 3.5 — best SP value in game.
  • Wall upgrade costs 2 SP → 200 HP. Best HP/SP at 70 marginal HP/SP.
  • Removal takes 2 turns (base) / 3 turns (upgraded). Plan ahead.
  • Refund: 90% base, 80% upgraded.
  • MP decays 25%/turn. Spend, don't save.
  • Demolisher damage is 8 (not 6 as in the shipped config).

PARALLELISM
  If you have 2+ independent hypotheses, use subagents with
  isolation="worktree" to explore them in parallel. Pick the winner
  by bestof vs [CURRENT_ALGO].

MUST NOT
  • Hardcode unit numbers — use self.config["unitInformation"][i].
  • Place walls on spawn-edge diagonals.
  • Modify C1GamesStarterKit-master/.
  • Claim success without showing ./tools/eval.sh + bestof output.
  • Keep dead code.
  • Repeat approaches documented as failed in ITERATION_HISTORY_V1_V11.md.

LANGUAGE TRIGGER
  If stuck after 5 iterations AND the report §8 flagged Rust → STOP
  and tell me. Don't attempt a port in this session.

FINAL HANDOFF
  1. Path to final algo.
  2. ./tools/eval.sh full output (paste it).
  3. python3 tools/bestof.py <final> algos/[CURRENT_ALGO] 10 output.
  4. Synthetic sweep results.
  5. One paragraph: what changed and why.
  6. Per-hypothesis: which from §5 got implemented, code references,
     measured metric changes.
  7. Known remaining weaknesses + suggested next hypotheses.

Begin now. First message: confirm you read the analysis report,
list its §5 hypotheses, and state iteration-1 choice.
```

---

## How to use these prompts

### Step 1: Download replays
After uploading an algo and letting it play ranked for a while, download the loss replays (and any upset wins) from https://terminal.c1games.com/myalgos.

Name them: `<algo>_vs_<opp_elo>_elo_<win|loss>.replay`

Drop them in `replays/` and commit:
```bash
git add replays/*_elo_*.replay && git commit -m "Add ranked replays for <algo>"
```

### Step 2: Run Prompt 1 (Analysis)
Open a fresh Claude Code session at the repo root. Fill in the placeholders and paste Prompt 1. It writes a report to `docs/REPLAY_ANALYSIS_<date>.md`.

Commit the report:
```bash
git add docs/REPLAY_ANALYSIS_*.md && git commit -m "Replay analysis for <algo>"
```

### Step 3: Run Prompt 2 (Build)
Open a fresh Claude Code session. Fill in placeholders (including the report path from step 2). Paste Prompt 2. It iterates until success criteria are met.

When it produces a passing algo, commit and upload:
```bash
git add algos/<new_algo>/ && git commit -m "Add <new_algo>"
./C1GamesStarterKit-master/scripts/zipalgo_mac algos/<new_algo> algos/<new_algo>.zip
# Upload the zip at https://terminal.c1games.com/myalgos
```

### Step 4: Repeat
Download new ranked replays from the uploaded algo → go to Step 1.

### Why commit between steps
Every new Claude Code session (especially those using isolation/worktree mode) starts from the committed HEAD. If you don't commit between steps, the next session can't see the previous session's output. This was the root cause of the earlier worktree visibility issues.

### Quick-reference: placeholders to fill

| Placeholder | Where to find it |
|---|---|
| `[ALGO_NAME]` | The algo you uploaded (e.g., `v12_sidelane_spend`) |
| `[CURRENT_ALGO]` | Same as ALGO_NAME for Prompt 2's regression baseline |
| `[OUR_ELO]` | Check https://terminal.c1games.com leaderboard |
| `[POSITION]/[TOTAL]` | Same leaderboard page |
| `[REPORT_FILE]` | Output of Prompt 1 (e.g., `REPLAY_ANALYSIS_2026-04-24.md`) |
| `[DATE]` | Today's date (YYYY-MM-DD) |
