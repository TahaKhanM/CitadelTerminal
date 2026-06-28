# PROMPT — Clean, restructure, and present the Citadel Terminal repo for recruiters

> **How to use this file:** Open a fresh Claude Code session at the repo root
> (`/Users/tahakhan/Documents/Work/Projects/CitadelTerminal`) and paste everything
> below the line. It is fully self-contained — every fact, path, and number it needs
> has already been verified and is embedded here. Delete this file as the final step
> of execution (it is a working artifact, not part of the shipped repo).

---

You are preparing a **finished, archived competition repository** for presentation to
recruiters during internship season. The Citadel Terminal AI competition is **over** —
this is portfolio/archive cleanup, **not active development**. Your two deliverables:

1. A **clean, well-organized repository** with a **curated, narrative git history** that
   reads as a clear engineering story.
2. A **recruiter-facing hero README** that leads with the result and the headline
   engineering wins.

The owner has already made the high-level calls (do not re-litigate these):
- **Rewrite git history** into a cleaner, narrative sequence of milestone commits.
- **Archive heavy artifacts to a branch** (recoverable), keep the distilled docs on `main`.
- **README = recruiter hero-narrative** (Top-5 result first; story-first; links to depth).

Everything below is verified ground truth. Trust it, but spot-check before any
destructive action.

---

## 0. HARD RULES (read first)

- **This is destructive (deletions + history rewrite).** Make a complete, pushed backup
  **before touching anything** (Phase 1). Do not skip it.
- **The repo is on a public remote:** `origin = https://github.com/TahaKhanM/CitadelTerminal.git`.
  Rewriting history means a **force-push**. That is acceptable (owner's repo, owner's call),
  but the backup branch/tag must be pushed first so nothing is lost.
- **NEVER delete the "distilled gems"** listed in §3 — they are the most impressive evidence
  in the repo and several hide inside otherwise-bulky `research/`.
- **Verify before deleting.** After cleanup the working tree must still be runnable
  (`python3 -c "import gamelib"` from an algo dir; `tools/test.sh` on the hero algo).
- **Keep claims honest** (§7 lists the precise wordings — e.g. the 427 replays train the
  opponent *prior*, not the classifier; the 13,319 fuzz is Python↔Rust). Don't inflate.
- Work in logical phases, committing as you go on a scratch branch; the final history
  reflow (Phase 6) comes last.

---

## 1. SAFETY BACKUP (do this first, no exceptions)

```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
git status                      # confirm starting point
git add -A && git commit -m "wip: pre-cleanup snapshot" || true
git tag pre-cleanup-backup
git branch archive/full-history            # preserves the entire original history + tree
git push origin archive/full-history pre-cleanup-backup
# Belt-and-suspenders: a full mirror outside the repo
git clone --mirror . ../CitadelTerminal-backup.git
```
Confirm `git branch -a` shows `archive/full-history` and the tag exists **before** proceeding.

---

## 2. GROUND TRUTH — what this project is (use for the README and commit messages)

### The result
**Top 5 of 1,000+ applicants** in the **Citadel Terminal** algorithmic competition
(Correlation One, 2026). The polished owner-written narrative already exists at
[`citadel_terminal_application.pdf`](citadel_terminal_application.pdf) — **the README must
align with it.** Keep that PDF and link it from the README.

### The game (one paragraph for the README)
Citadel Terminal is a special-ruleset version of Correlation One's *Terminal*: a two-player,
simultaneous-turn **tower-defense** game on a **28×28 diamond grid**. You own the bottom
half (y<14); each turn you spend two resources — **Structure Points (SP)** on walls/turrets/
supports and **Mobile Points (MP, 25%/turn decay)** on scouts/demolishers/interceptors — then
a deterministic **action phase** resolves combat. Win by dropping the opponent's 40 HP to 0,
or hold higher HP at turn 100. Algos upload to a site that auto-queues **ranked ELO matches**;
you iterate via downloadable replays. The "Citadel" ruleset differs from base Terminal
(e.g. wall upgrades cost 2 SP; base Support HP is 1 but upgraded is 40; upgraded turrets are
unusually strong) — authoritative values live in `docs/UNITS_REFERENCE.md`.

### The approach (the thesis)
**Search, not heuristics.** Most submissions were hand-tuned rule sets. This agent makes
**every decision the output of a search loop**: each turn it enumerates candidate plans,
samples plausible opponent responses from a model trained on **427 ranked replays**, rolls
each candidate forward through a fast simulator, and commits to the plan with the highest
expected utility. No opening/midgame/endgame is scripted — it is rediscovered from first
principles every match.

### The headline engineering win — `sim_rs` (the Rust simulator)
Search only works if the simulator is fast enough to roll out thousands of futures inside the
15-second turn budget. The official Java engine couldn't be embedded; the reference Python sim
ran ~**336 games/s**, ~100× too slow. So a **from-scratch Rust reimplementation of the engine's
action phase** was built (`algos/athena/sim_rs/`), exposed to the Python search loop as a
**PyO3/maturin compiled extension**. Reaching parity required **decompiling the engine bytecode**
to recover the exact per-frame ordering and targeting priority.

| Metric | Value | Note |
|---|---|---|
| Single-core throughput | **14,300 games/s** | ~43× the 336/s Python reference |
| 10-thread throughput | **75,000 games/s** | ~225× |
| Fuzz parity | **13,319 / 13,319** frames | Python↔Rust **byte-identical**, zero divergence |
| Rust unit tests | **42 / 42** passing | |
| Large-scale fuzz | **2× 1,000,000-config** runs (seeds 42 & 17) | 0 failures |

Authoritative source for these: `algos/athena/sim/SIM_PARITY.md` and `algos/athena/sim/PARITY_REPORTS/`.

### Reading the opponent in real time
- **Opponent classifier** — over a rolling **10-turn window** it computes spawn-tile
  concentration and unit-type diversity to decide `single_archetype` vs `multi_archetype`.
  Its only job is to **gate** the adaptive response, so a wrong read never causes a regression.
- **Funnel detector** — tracks the tiles where the opponent has been breaching; when one
  tile/zone dominates recent breaches it injects defense templates clustered around that exact
  tile into the search candidate pool, and the search picks among them on the merits.

### Closing the loop on every loss (the variant chain)
Every ranked loss was pulled from the competition's **authenticated REST API** via a custom
browser-cookie–driven tool, walked frame-by-frame, and turned into a structured account of
*which* turn the agent fell behind and *why* the search ranked its chosen plan above the
alternatives — then the fix was replayed against the same match locally before shipping.
This produced three documented, regression-gated variants:

| Variant | What it fixed |
|---|---|
| `smart_oracle_vd` | First adaptive defense: added the funnel detector + flank-corridor templates against opponents who repeatedly attacked one side. |
| `smart_oracle_F` | Merged the funnel response onto the strongest baseline (IS6) and **gated it behind the classifier** so specialized defense only fires when the opponent is provably vulnerable to it — no regression on prior wins. |
| `smart_oracle_F2` | **The final ship.** Replay analysis of 4 live losses showed the funnel detector fired too late: lowered the trigger threshold (MIN_BREACHES 10→6), taught it a center-drill attack vector it had been blind to, and added deep-clamp templates at the exact loss tiles. **12 wins / 0 losses** on the local benchmark. |

### Verified outcomes (use these, they all trace to a file)
- Top 5 of 1,000+ applicants on the live ELO ladder.
- 12–0 on the local benchmark (vs the owner's own earlier variants + the strongest published
  finalists from Lostkids / Midwest / Georgia Tech + decoded ranked-match attackers).
- ~50,000 lines across the Rust sim, Python search, opponent-modeling pipeline, replay tooling,
  and benchmark harnesses (Rust alone = 14,338 LOC; de-duplicated authored total ≈ 61K).

---

## 3. THE ALGO LINEAGE (the hero path — for the README "journey" + commit narrative)

```
v13 (hand-tuned heuristic BASELINE — the bot the search had to beat)
   └─ heuristic_v1 / heuristic_v2        (safety-net heuristics; dead-ends)

athena            = the ENGINE: Python action-phase sim + the sim_rs Rust port + parity harness
   └─ athena_v3_planner   (earlier search PROTOTYPE; superseded as a strategy, but its
                           sim_eval.py / state_adapter.py were inherited by the oracle line)

oracle (failed "heuristic disguised as search")  →  REPLACED BY:
oracle_pure / "M1Lite"   = THE SEARCH ENGINE (≈1,500–2,500 candidate plans/turn,
   │                         427-replay opponent prior, 2-phase sim_rs rollout). Hit ~2138 ELO.
   ├─ V-series (VA,VB,VC,VD,VE,VF)        value-function experiments → VD strongest baseline
   ├─ IS-series (IS3..IS6)                trap-refund fixes → IS6 strongest live variant
   ├─ J / K / M2 series (→ M2v3)          single-tile lane/path-block fixes (parallel branch)
   └─ smart_oracle line (the shipped adaptive branch):
        smart_oracle_vd → smart_oracle_F → smart_oracle_F2   ★ FINAL HERO ★
```

**Reconciliation to state plainly (memory/older notes are stale on this):** `athena` is the
**foundation** (Rust sim + adapter code) plus a **discarded prototype planner**
(`athena_v3_planner`, classifier LOO-CV 0.489); the strategy that actually **shipped** is
`oracle_pure → smart_oracle_F2`, running on Athena's engine. **`smart_oracle_F2` is the single
canonical hero algo.** Note it currently exists **only as an upload snapshot**
(`smart_oracle_F2_upload/`) with no `algos/` dev folder — Phase 5 promotes it to
`algos/smart_oracle_F2/` so the hero's source is first-class.

---

## 4. CRUFT INVENTORY — exact paths, with KEEP / ARCHIVE / DELETE

Current footprint: **570 MB working tree + 355 MB `.git`**. Tracked: 9,513 files (the bulk is
`research/` = 7,693 tracked files; the starter kit is only 71 tracked — fine as-is).

### 4a. ARCHIVE to a branch (heavy, recoverable — Phase 4)
| Path | Size | Why archive (not delete) |
|---|---|---|
| `research/engine_decompiled/sources/` | ~61 MB | Raw decompiled Java tree — provenance for the parity work, but bulky and not recruiter-facing. **Keep `GOTCHAS.md` + `PATHFINDER_SPEC.md` on main.** |
| `research/replica_analysis/replay_registry.json` (+ `profiles/`, `validation/`) | ~18 MB | Scraped match index used for opponent profiling. |
| `research/finalist_repos/` | 6.4 MB | Cloned public competitor algos (research reference). |
| The ~27 `*_upload/` snapshots at repo root | ~42 MB | One-time upload staging copies; each re-vendors `gamelib/` + a `.so`. **First promote `smart_oracle_F2_upload/` to `algos/smart_oracle_F2/` (Phase 5); also keep the variant `VARIANT_*.md` / `REPORT.md` reports — see §5 devlog.** Then archive the rest. |
| `tools/cfr.jar` | 2.2 MB | Decompiler used to produce the engine sources; archive with them. |

### 4b. DELETE / gitignore (regenerable junk — Phase 3)
| Pattern | Notes |
|---|---|
| `**/__pycache__/`, `**/*.pyc`, `.pytest_cache/`, `.mypy_cache/` | Already gitignored; remove from tree. |
| `algos/athena/build/` (~7.9 MB), `algos/**/*.so`, `algos/athena/sim/_fastsim.c` | mypyc/Cython/Rust build output; regenerable. |
| `**/documentation/_build/`, `**/*.doctree` | Sphinx build output (tracked in a couple of places); regenerable. |
| `**/.DS_Store` (59 files) | macOS junk. |
| `C1GamesStarterKit-master/game-configs.json.starterkit-original` | Redundant duplicate. |

### 4c. KEEP — the distilled gems (NEVER delete; these are the portfolio)
- `docs/` — the whole game/ API / strategy / workflow reference (fix the inconsistencies in §7).
- `algos/athena/sim/SIM_PARITY.md` **and** `algos/athena/sim/PARITY_REPORTS/` (incl. `fuzz_1M_s42.md`, `fuzz_1M_s17.md`).
- `research/engine_decompiled/GOTCHAS.md`, `research/engine_decompiled/PATHFINDER_SPEC.md`,
  `research/A3_EVIDENCE.md`, `research/AUDIT_2026-04-24.md`.
- The narrative reports (relocate to a `docs/devlog/` — see §5):
  `ORACLE_HANDOFF.md`, `SMART_ORACLE_F_HANDOFF.md`, `M1LITE_VARIANTS_FINAL_REPORT.md`,
  `ORACLE_PURE_M1LITE_FULL_CONTEXT.md`, `algos/oracle_pure/REPORT.md`,
  `smart_oracle_F2_upload/VARIANT_SMART_ORACLE_F2_REPORT.md`, `algos/athena_v3_planner/FINAL_REPORT.md`.
  *(`ORACLE_SUCCESSOR_PROMPT.md` (39 KB) is a build-prompt — archive it, don't feature it.)*
- `data/` — leaderboard snapshots + pairwise analyses (evidence of the result). Keep; optionally
  tuck under `data/` as-is or `docs/results/`.
- `.agents/skills/` — **14 custom Claude Code skills** built for the workflow (`new-algo`,
  `run-match`, `bestof`, `tournament`, `analyze-replay`, `detailed-replay`, `batch-replays`,
  `profile-turns`, `inspect-config`, `upload-algo`, `competition-reference`, `test-algo`,
  `skill-creator-terminal`). This is itself an impressive artifact — feature it briefly.
- `tools/` — the eval + replay pipeline (`bestof.py` Wilson-CI, `tournament.py`, `evaluate.py`,
  `scrape_ranked_replays.py`, `bulk_download_my_replays.py`, `extract_replay_features.py`, etc.).
  *(Note: `evolve.py` and `validate_simulator.py` carry honest "ARCHIVED/BLOCKED" headers — leave them; they show good-faith dead-end documentation.)*
- `citadel_terminal_application.pdf` — keep; link from the README.
- `C1GamesStarterKit-master/` — read-only reference (only 71 files tracked; `engine.jar` is
  required for local matches). Leave as-is.
- `Citadel Context Files/` — original source material. Either keep, or move under
  `docs/source-material/` if you want a cleaner top level (your call; it's small relative to the rest).

---

## 5. TARGET REPO STRUCTURE (after cleanup)

Aim for a top level a recruiter can read in 30 seconds. Suggested:

```
README.md                          ← NEW hero narrative (Phase 7)
CLAUDE.md                          ← keep (project context; AGENTS.md is a near-duplicate — keep one, note it)
citadel_terminal_application.pdf   ← the written narrative (linked from README)
docs/
  game/            ← GAME_RULES, UNITS_REFERENCE, MAP_AND_COORDINATES, TARGETING_AND_PATHING, API_REFERENCE, STRATEGY_GUIDE
  workflow/        ← CLAUDE_WORKFLOW, LOCAL_TESTING, OBTAINING_REPLAYS, RUST_MIGRATION
  devlog/          ← the relocated handoff/variant reports (the iteration story)
  source-material/ ← (optional) the Citadel Context Files
algos/
  smart_oracle_F2/ ← ★ THE HERO (promoted from smart_oracle_F2_upload/)
  oracle_pure/     ← the search engine (M1Lite) + REPORT.md
  athena/          ← the Rust sim (sim_rs/) + Python sim + SIM_PARITY.md + PARITY_REPORTS/
  baselines/       ← v13_second_ring, heuristic_v1, heuristic_v2 (clearly labeled "earlier baselines")
tools/             ← eval + replay pipeline
research/          ← distilled docs only (GOTCHAS, PATHFINDER_SPEC, A3_EVIDENCE, AUDIT); raw bulk archived
data/              ← leaderboard snapshots / results evidence
C1GamesStarterKit-master/  ← read-only reference
```
Use `git mv` for relocations so history follows the files. Don't over-engineer — if a move
creates churn with little benefit, prefer leaving a thing where it is and fixing links.

**Promote the hero (do this carefully):**
```bash
git mv smart_oracle_F2_upload algos/smart_oracle_F2
```
Then inside `algos/smart_oracle_F2/`: keep `algo_strategy.py`, the whole `oracle_core/`, and its
report. Decide on the vendored `bundled_sim_rs/*.so` and the duplicated `gamelib/` — keep just
enough that the algo is **understandable and runnable**, but don't leave large binaries lying
around if `algos/athena/sim_rs/` already holds the real source. Add a short
`algos/smart_oracle_F2/README.md` pointing at the engine in `algos/oracle_pure/` and `algos/athena/`.

---

## 6. REWRITE HISTORY INTO A CLEAN NARRATIVE (Phase 6 — after the tree is final)

The owner wants the **log itself to tell a clear story**. Recommended approach: once the working
tree is in its final cleaned state, **rebuild `main` as a curated sequence of milestone commits**
on an orphan branch (this also drops the bloated history from `main`'s ancestry, shrinking the
clone; the full original remains on `archive/full-history`).

```bash
# tree is already final & committed on your scratch branch
git checkout --orphan main-clean
git reset                                  # unstage everything; keep working tree
# now add files in NARRATIVE GROUPS, committing each milestone:
```

Suggested milestone commits (each adds the corresponding part of the final tree; real authorship
dates can be recovered from `git log archive/full-history` if you want them spread realistically):

1. **`Game reverse-engineering: rules, unit reference, and engine semantics`** — `docs/game/`,
   `research/engine_decompiled/GOTCHAS.md`, `PATHFINDER_SPEC.md`, `AUDIT_2026-04-24.md`, `CLAUDE.md`.
2. **`Workflow tooling: custom Claude Code skills + local eval harness`** — `.agents/skills/`,
   `tools/` (eval, tournament, bestof), `docs/workflow/`.
3. **`Heuristic baselines (v13 + reactive defense)`** — `algos/baselines/`.
4. **`Action-phase simulator (Python) with parity harness vs the Java engine`** —
   `algos/athena/sim/` (Python sim), `validate_simulator`/parity scaffolding.
5. **`Rust port of the action phase (sim_rs): 43x/225x speedup, byte-identical parity`** —
   `algos/athena/sim_rs/`, `SIM_PARITY.md`, `PARITY_REPORTS/`, `A3_EVIDENCE.md`.
6. **`Replay pipeline: authenticated REST scraping + opponent prior from 427 ranked replays`** —
   `tools/scrape_ranked_replays.py`, `bulk_download_my_replays.py`, `extract_replay_features.py`,
   `data/`.
7. **`Search-driven agent (oracle_pure / M1Lite): plan enumeration + opponent-sampled rollouts`** —
   `algos/oracle_pure/`.
8. **`Regression-gated variant search (V/IS/J/K/M2 → IS6)`** — `docs/devlog/` variant reports.
9. **`Adaptive defense: opponent classifier + funnel detector (smart_oracle vd → F)`** —
   the smart-oracle devlog + classifier/funnel code paths.
10. **`Final ship: smart_oracle_F2 (per-loss-diagnosed fixes) — Top-5 finish`** —
    `algos/smart_oracle_F2/`, `README.md`, `citadel_terminal_application.pdf`.

Write **descriptive commit bodies** (2–4 lines each) summarizing the milestone and its result —
the log is part of the portfolio. Then:

```bash
git branch -M main-clean main               # replace main
git gc --aggressive --prune=now             # shrink .git (old objects still held by archive/full-history)
```

**Force-push (backups already pushed in Phase 1):**
```bash
git push --force-with-lease origin main
git push origin archive/full-history archive/heavy-artifacts pre-cleanup-backup
```

> Alternative if you'd rather preserve the *real* chronology but still purge the heavy blobs:
> use `git filter-repo --invert-paths --path research/engine_decompiled/sources --path research/replica_analysis/replay_registry.json --path-glob '*_upload/*' ...` then reword commits. The
> orphan-reflow above is simpler and gives a cleaner story; prefer it unless asked otherwise.

---

## 7. FIX THESE INACCURACIES (do during cleanup; the README must be correct)

- **Skills path:** `README.md` references `.claude/skills/` but the skills actually live in
  `.agents/skills/`. Fix every reference.
- **Support-shield coefficient:** `README.md` says `shield = 1 + 0.3 × Y`, but `CLAUDE.md` and the
  live-config-verified docs say **`1 + 0.7 × Y`** (range 7). Verify against
  `algos/athena/data/citadel_config_snapshot.json` and standardize on the correct value everywhere.
- **Stale "SCAFFOLD" label:** `algos/athena/sim_rs/README.md` still says *Status: SCAFFOLD* — the
  port is finished (64 KB `systems.rs`, installed wheel, 42 tests). Update it.
- **Dead doc links:** `README.md`'s layout table links `docs/BASELINE_BUILDING_PROMPT.md` and other
  files — verify each linked path exists post-restructure and fix or remove dead links.
- **Honest phrasings for the README (keep precise):**
  - "trained on 427 ranked replays" refers to the **opponent action prior** the search samples
    from (`algos/oracle_pure/oracle_core/opponent_model.py`) — *not* the archetype classifier
    (which is a separate component, LOO-CV ≈ 0.489). Don't conflate.
  - The **13,319/13,319** fuzz is **Python-sim ↔ Rust-sim** byte-identical; Java-engine parity is a
    separate STRICT/CASCADE gate over ~87K ranked frames. The PDF's "byte-identical to the engine"
    is true transitively (the Python sim is the validated engine mirror) — fine to keep, just don't
    misstate the 13,319 run.
  - Verify the **candidate-plan count** in `algos/oracle_pure/oracle_core/enumerator.py` before
    quoting "~2,500"; phrase as "up to ~2,500" or use the real number you find.

---

## 8. WRITE THE README (Phase 7 — recruiter hero-narrative)

Audience: **recruiters and engineers skimming a portfolio.** Lead with the result, tell the story,
link to depth. Use the verified facts in §2–§3 verbatim where possible. Keep it tight (aim
~150–220 lines). Suggested structure:

1. **Title + one-liner** — e.g. *"Building a Top-5 Tournament AI — Citadel Terminal (Correlation One, 2026)."*
   Subtitle: *Top 5 of 1,000+. A fully search-driven agent — no heuristics — on a custom Rust simulator.*
2. **The result** (2–3 bullets: Top-5; 12–0 local benchmark; ~50K LOC end-to-end ownership).
3. **What it is** (the one-paragraph game summary from §2).
4. **The approach: search, not heuristics** (the thesis paragraph).
5. **Headline engineering win: the Rust simulator** — include the metrics table from §2 and
   one line on the decompile-to-parity work. Link `algos/athena/sim/SIM_PARITY.md`.
6. **Reading the opponent in real time** — classifier + funnel detector (the gating insight).
7. **Closing the loop on every loss** — the variant-chain table from §2.
8. **Architecture** — a small ASCII/mermaid diagram of one turn:
   `deploy → enumerate ~N plans → opponent model samples responses → sim_rs rollouts → rank by
   expected utility → (classifier-gated funnel templates injected) → commit best plan`.
9. **Repo map** — a short table pointing at the hero (`algos/smart_oracle_F2/`), the engine
   (`algos/oracle_pure/`, `algos/athena/`), the sim (`algos/athena/sim_rs/`), tooling, and `docs/`.
10. **Run it locally** — minimal quickstart (run a match between the hero and the starter algo;
    where replays land). Pull accurate commands from the existing README + `docs/workflow/LOCAL_TESTING.md`.
11. **The numbers, verified** — short "everything here traces to a file" list linking
    `SIM_PARITY.md`, `PARITY_REPORTS/`, the variant report, and the application PDF.

Tone: confident, specific, no fluff. Prefer concrete numbers over adjectives. Every headline claim
should link to the file that backs it.

Also: refresh `CLAUDE.md`'s top so a future reader knows the competition is **complete** and points
them at the README + hero algo. Decide whether to keep both `CLAUDE.md` and `AGENTS.md` (they're
near-duplicates) — keeping `CLAUDE.md` and deleting/aliasing `AGENTS.md` is fine.

---

## 9. VERIFY & FINISH (Phase 8)

- [ ] Hero algo still imports/runs: from `algos/smart_oracle_F2/`, `python3 -c "import gamelib"`
      succeeds and `tools/test.sh` (or a local match vs `C1GamesStarterKit-master/python-algo`) runs.
- [ ] `.gitignore` covers all regenerable junk in §4b (it's mostly there; add any gaps — e.g.
      `*_upload/` if any remain, Sphinx `_build/`, `*.doctree`).
- [ ] No `*_upload/` snapshots, `__pycache__/`, `build/`, `.DS_Store`, or `_build/` left tracked
      (`git ls-files | grep -E '_upload/|__pycache__|/build/|\.DS_Store|_build/'` returns nothing).
- [ ] Top level reads clean (§5); every README link resolves; §7 inaccuracies fixed.
- [ ] `git log --oneline` reads as the §6 narrative.
- [ ] Backups intact and pushed: `archive/full-history`, `archive/heavy-artifacts`,
      `pre-cleanup-backup`, and the `../CitadelTerminal-backup.git` mirror.
- [ ] Force-push `main`; report the before/after `.git` and working-tree sizes.
- [ ] **Delete this prompt file** (`CLEANUP_AND_README_PROMPT.md`) as the final commit.

Report a concise summary of what changed: sizes before/after, what was archived where (with
restore instructions), the new structure, and any §7 inaccuracy you couldn't confirm.
