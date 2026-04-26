# Citadel Terminal (Correlation One C1Games Terminal): A Comprehensive Research Report on Repositories, Strategies, and AI-Assisted Workflows

## 1. Executive Overview

Citadel Terminal — branded variously as "Terminal," "C1 Games Terminal," and "Correlation One Terminal" — is a two-player tower-defense programming competition organized by Correlation One in partnership with Citadel and Citadel Securities. Players (mostly university and high-school students) submit Python, Java, or Rust algorithms ("algos") that fight head-to-head on a 28×28 diamond grid; cash prizes for the Global Championship reach $30,000+ for first place, with $150,000+ in total annual prize pools across regional events ([Correlation One](https://www.correlation-one.com/blog/terminal-2021-2022)). As of 2026, the schedule lists India (March 23–30), APAC (April 13–20), Europe (April 21–28), and High-School Terminal (July 13–20) competitions ([Citadel](https://www.citadel.com/careers/programs-and-events/terminal/)). Over 2.2 million matches have been played on the official platform ([terminal.c1games.com](https://terminal.c1games.com/)).

The Terminal community has been remarkably consistent over many seasons in arriving at a small set of dominant strategic archetypes: **simulation-driven adaptive algos**, **scout/ping rushes** ("ping cannons"), **demolisher lines**, **funnel/V-shape mazes**, and increasingly **opponent-prediction systems** that anticipate the simultaneous opponent move. Top competitors' postmortems (especially Ryan Draves, CMU Team 13, Team 6/UWaterloo) reveal that >90% of top-10 algos run a custom in-process simulator of the engine's action phase to evaluate hundreds of candidate (defense + attack) deployments per turn ([Medium](https://medium.com/terminal-player-strategies/cmu-team-13-our-experience-at-terminal-live-33d909647621); [Medium](https://medium.com/terminal-player-strategies/ryan-ds-terminal-strategy-eaa39f123a7e)).

This report consolidates: (1) every public Terminal repository found, (2) detailed code-architecture analyses of notable algos, (3) a unified strategy taxonomy, (4) Claude Code workflow recommendations specific to Terminal-style game-AI development, and (5) supplementary learning resources.

---

## 2. Public GitHub Repositories of Terminal Teams (Comprehensive List)

### 2.1 Official C1 Games / Reference Repositories

| Repo | Purpose / Notes |
|---|---|
| [correlation-one/C1GamesStarterKit](https://github.com/correlation-one/C1GamesStarterKit) | The canonical starter kit (234 stars, 465 forks). Ships `gamelib` (`algocore.py`, `game_state.py`, `game_map.py`, `navigation.py`, `unit.py`, `util.py`, `advanced.py`), `engine.jar`, `game-configs.json`, plus Python/Java/Rust starter algos. Recent commits ([Starterkit Update #105](https://github.com/correlation-one/C1GamesStarterKit/commit/ad5ec7df27fd3a3b3ba6ff714f6944c690faadab)) modernized terminology to Walls / Turrets / Supports / Scouts / Demolishers / Interceptors. The Java starter ([StarterAlgo.java](https://github.com/correlation-one/C1GamesStarterKit/blob/master/java-algo/src/main/java/com/c1games/terminal/starteralgo/StarterAlgo.java)) is the canonical "if >10 enemies in front rows → demolisher line; else → scout rush every other turn" reference. |
| [Python gamelib docs](https://pythondocumentation.readthedocs.io/en/latest/gamelib.html) | Read-the-Docs for `GameState.attempt_spawn`, `attempt_remove`, `find_path_to_edge`, `get_attackers`, etc. |

### 2.2 High-Placing / Notable Team Repositories

These repositories document either (a) a public placement, (b) named live-event finishes, or (c) a substantial codebase. Many top players deliberately removed full source after the season at Correlation One's request, preserving only design notes (e.g., [luckystarufo/_Terminal_CorrelationOne](https://github.com/luckystarufo/_Terminal_CorrelationOne) which now contains only README/diagrams).

**University & live-event placers (2018–2025):**

| Repo | Team / Event | Result |
|---|---|---|
| [The-Travelling-Salesmen/terminal-c1](https://github.com/The-Travelling-Salesmen/terminal-c1) | MIT/Harvard team | **#1 at Harvard live event**. Multiple algo lineages (`algo-1`, `frumblesnatch-v1`, `snorkeldink-v1/v2/v3-1/v3-2/v3-3`, `snorkeldink-AdapDef`). Python-dominant (81%), Java (11%), Rust (8%), MIT-licensed. The version-history naming pattern is itself instructive: Snorkeldink v3-3 is a 3rd-generation refinement on top of an "AdapDef" (adaptive defense) family. |
| [dwei-exe/c1-terminal](https://github.com/dwei-exe/c1-terminal) | dwei.exe + ng-333 + nithinRavikumar | **5th place, 2025 High-School Terminal North America**. Final algo: `dwei-exe/beta-2-5-13`. Python 92%, Java 2%, Rust 1.6%; includes a dedicated `map_coordinates_tool` (HTML/JS visualizer) for designing structures. |
| [nknguyenhc/Terminal-Lostkids](https://github.com/nknguyenhc/Terminal-Lostkids) | Team Lostkids (Python-2L-AET) | **3rd place overall, APAC Terminal** (top-4 finalist). Multiple iterations (`python-v1`, `python-2l`, `python-2l-md`, `python-2l-aet`, plus Rust and Java variants). Detailed README documents two strategy generations: (i) horizontal-wall + dual entrance design with attack-pattern-driven entrance opening/closing, (ii) V-shape 2-layer defense with 3 upgraded turrets per side, refund-and-replace defenses, scout-batch prediction, and interceptor counter-rush. Excellent open documentation of strengths/weaknesses, including scout-spam-detection and scout-vs-demolisher tradeoff analysis. |
| [sebdixon/terminal](https://github.com/sebdixon/terminal) | "GRETCHEN" / GRETCHEN 2 | **5th place, Terminal Europe**. The final algo combined a **Bayesian defense system with an adaptive attack** (`GRETCHEN/algo_strategy.py`); also contains an `adaptive-1` precursor. |
| [langsonzhang/Terminal-C1-Midwest-2022](https://github.com/langsonzhang/Terminal-C1-Midwest-2022) | "Murphy's Lawyers" (UofT) | **5/24 at C1 Midwest 2022**. Strategy named "FUNNEL" — a funneling maze defense. |
| [yip6ga1lok6/C1-Terminal-Summer-2022](https://github.com/yip6ga1lok6/C1-Terminal-Summer-2022) | C1 Summer 2022 | **6th of 91 teams**. |
| [aubreyyan/terminal_live_2020](https://github.com/aubreyyan/terminal_live_2020) | "PIP INSTALL UT" (UT Austin + Georgia Tech) | **6/45 at Terminal Live 2020**, $1,000 prize. Source intentionally NOT published, only placement evidence. |
| [vishruthb/terminal](https://github.com/vishruthb/terminal) | vishruthb | **Top 24 in US Citadel Securities Terminal Competition**. |
| [brian-cai/terminal_c1_gt](https://github.com/brian-cai/terminal_c1_gt) | Georgia Tech variant | Multiple `algo_strategy_wang5.py`-style versions; uses GT-specific rule modifications. Tournament VOD link: [twitch.tv/videos/483165853](https://www.twitch.tv/videos/483165853). |
| [fayllkw/C1_Terminal_19](https://github.com/fayllkw/C1_Terminal_19) | CMU Live 2019 variant | |
| [Abhishek21g/Citadel---C1-Terminal](https://github.com/Abhishek21g/Citadel---C1-Terminal) | Citadel C1 Summer Invitational submission | |
| [kapilsinha/terminal_c1_game](https://github.com/kapilsinha/terminal_c1_game) | Team "PPPostered AI" | |
| [TheAvi123/C1_Terminal_Algorithm](https://github.com/TheAvi123/C1_Terminal_Algorithm) | West Coast Regional. Final algo in `prototypo_X2` |
| [hatmer/CompetitionBot](https://github.com/hatmer/CompetitionBot) | Citadel Correlation One submission |
| [shu1/terminal](https://github.com/shu1/terminal) | Older AI bot, includes step-method/process_config pattern from earlier API |
| [kcirerick/terminal-algo](https://github.com/kcirerick/terminal-algo) | West Coast Regional, focus on Deep RL training |
| [TaoXU0/CS5446Project-C1Terminal](https://github.com/TaoXU0/CS5446Project-C1Terminal) | RL coursework |
| [PForet/TC1](https://github.com/PForet/TC1) | Reverse-engineered Terminal engine for RL training |
| [yuanxinwang/c1terminal (alex-yxw/c1terminal)](https://github.com/yuanxinwang/c1terminal) | "AI Strategies for C1 Terminal" |
| [denizy97/Terminal-Algo](https://github.com/denizy97/Terminal-Algo) | Personal algo |
| [tim-zwart/Terminal_Algos](https://github.com/tim-zwart/Terminal_Algos) | Personal algos |
| [Carlipoot/terminal](https://github.com/Carlipoot/terminal) | Personal algos |
| [thisIsTheFoxe/C1GamesStarterKit](https://github.com/thisIsTheFoxe/C1GamesStarterKit) | Personal fork |
| [andrew880/C1GamesStarterKit](https://github.com/andrew880/C1GamesStarterKit) | Personal fork |
| [Mehty/C1Games](https://github.com/Mehty/C1Games) | Personal |
| [AFederici/haxxor0](https://github.com/AFederici/haxxor0) | "AI for Citadel competition" |
| [fredtsui-zz/c1game-terminal-algo](https://github.com/fredtsui-zz/c1game-terminal-algo) | Modified starter |
| [DerekYoungMT/terminal-python-algo](https://github.com/DerekYoungMT/terminal-python-algo) | |
| [vinharish77/TerminalCompetition](https://github.com/vinharish77/TerminalCompetition) | |
| [luckystarufo/_Terminal_CorrelationOne](https://github.com/luckystarufo/_Terminal_CorrelationOne) | Strategy diagrams only — code redacted at C1's request |

### 2.3 Notable Postmortem Blogs (often more useful than the source)

- **Ryan Draves (3rd, Global Season 1; 2nd, UMich Live)** — [medium.com/terminal-player-strategies/ryan-ds-terminal-strategy](https://medium.com/terminal-player-strategies/ryan-ds-terminal-strategy-eaa39f123a7e). Describes the *predictor system* (small army of pattern-matching predictors that confidently predict opponent moves), a min-max/expectimax simulator over (X defensive placements × Y offensive placements), the **ML_Bootstrap** algo (Keras-trained NN for defensive placements, ported to dependency-free C++ for upload, climbed to ~rank 40 via supervised learning on replays), and a **C++ port of the simulator** for performance.
- **CMU Team 13 (Kyle Chin / Austin Schick / Eric Clinch — 2nd globally)** — [medium.com/terminal-player-strategies/cmu-team-13](https://medium.com/terminal-player-strategies/cmu-team-13-our-experience-at-terminal-live-33d909647621). Modeled the game as a **two-player zero-sum minimax game**, defined a security metric U(board) = score(blue)·HP(blue) − score(red)·HP(red), simulated thousands of variations, built a 3,000+ line C++ simulator achieving 480 rounds/sec (1,175 rounds in 2.5 sec), with a Python↔C++ interface, a Tkinter deployment-design GUI, and an in-simulator REPL for step-by-step debugging. Reports that single bug-fixes in the simulator moved them from rank 19 to rank 5.
- **Team 6 / UWaterloo (Griffin Keglevich / Ruslan / Tilman)** — [medium.com/terminal-player-strategies/the-terminus-of-our-terminal-strategy](https://medium.com/terminal-player-strategies/the-terminus-of-our-terminal-strategy-19c96da2acf5). Started with PPO-based RL, abandoned it because action space was too large and the model was capped by the heuristic policy library. Pivoted to a heuristic system with a custom simulator returning frame-by-frame damage; value function `3.5*units_scored + cores_damage`. Key engineering insight: modular `Defences.py` / `Strategies.py` modules + a per-version git branch let three teammates parallelize work cleanly.
- **CMU Team 17 (Josh Durham, Sayan Chaudhry, Abraham Riedel-Mishaan)** — [medium.com/terminal-player-strategies/cmu-team-17](https://medium.com/terminal-player-strategies/cmu-team-17-terminal-strategy-e0adc7f387f9). Reached **rank 12 globally** post-event. Iterated by pushing to live ladder and analyzing each loss, with a notable special-case "ping cannon" defense.
- **Sentdex tutorial** — [pythonprogramming.net/correlation-one-terminal](https://pythonprogramming.net/correlation-one-terminal/). The most-cited beginner walkthrough (covers `AlgoCore`, `on_game_start`, `on_turn`, `attempt_spawn`, decrementing `_player_resources` to make `can_spawn` accurate within a turn, defense templates with Filter walls and Destructor turrets).

### 2.4 Forum Threads That Function as Strategy Canon

- [forum.c1games.com](https://forum.c1games.com/) — main discussion site. Notable threads:
  - **[Regarding Maze Algos](https://forum.c1games.com/t/regarding-maze-algos/472)** — analysis of why **AdrianMargel's "sawtoothV2"** (the originator of the **9-11-13 maze**) won the CodeBullet challenge with a non-adaptive, hardcoded structure that always builds the same firewalls and spawns max EMPs at [24,10] each turn. 4 of top-8 in CB challenge were "maze" algos.
  - **[Disguised Ping Cannon Meta?](https://forum.c1games.com/t/disguised-ping-cannon-meta/1446)** — thesis that nearly all top algos are "disguised ping cannons" that lock down the middle and play out the sides.
  - **[Tips on countering ping cannons](https://forum.c1games.com/t/tips-on-countering-ping-cannons/732)**.
  - **[Kauffk's Final Game](https://forum.c1games.com/t/kauffks-final-game/875)** — "Voice" beat KauffK's "Demux" by downloading a public replay of an opponent's previous game and **hardcoding a replay-mimicry script** (13 ms compute time gave it away).
  - **[The Ultimate Copy-Cat — ML Implementations](https://forum.c1games.com/t/the-ultimate-copy-cat-ml-implementations/654)** — discussion of imitation learning from replays.
  - **[Won't Forget Truth_of_Cthaeh](https://forum.c1games.com/t/wont-forget-truth-of-cthaeh/478)** — KauffK's algo dominated rank 1 for 20 consecutive days in 2018.
  - **[Leaderboard Matches 03/11/2019](https://forum.c1games.com/t/leaderboard-matches-03-11-2019/1066)** — auto-generated replay catalog for all top-25 leaderboard algos, useful for opponent corpus building.
  - **[Tactical Terminal Puzzle #2 (4Core 8Bit attack)](https://forum.c1games.com/t/ttp-2-tactical-terminal-puzzle-4core-8bit-attack/992)** — community-made tactical puzzles.

---

## 3. Detailed Code-Architecture Analysis of Notable Algos

Across the public corpus, repositories cluster into five architectural archetypes:

### 3.1 Starter-Algo Pattern (baseline)
Every algo subclasses `gamelib.AlgoCore`, overriding `on_game_start(config)` and `on_turn(turn_state)`. Within `on_turn`, you typically construct `game_state = gamelib.GameState(self.config, turn_state)`, build a defense, plan an attack, and call `game_state.submit_turn()` ([starter Java](https://github.com/correlation-one/C1GamesStarterKit/blob/master/java-algo/src/main/java/com/c1games/terminal/starteralgo/StarterAlgo.java)). The starter exposes the canonical helpers — `attempt_spawn`, `attempt_upgrade`, `attempt_remove`, `find_path_to_edge`, `get_attackers`, `least_damage_spawn_location`, `detect_enemy_unit` — which most algos extend. A frequently-cited gotcha (Sentdex) is that **`enable_warnings` should be set to False each turn** because Terminal warnings keep firing for non-error reasons.

### 3.2 Modular Heuristic Algos (Lostkids, Murphy's Lawyers FUNNEL)
- **Decision frame**: a fixed defensive blueprint (job list of `(unit_type, location)` tuples) is built every turn; offense is conditional on resource thresholds (e.g., MP ≥ 12 → spawn 1 batch of scouts, MP ≥ 24 → 3 batches).
- **Reactive defense**: enemy-attack history → priority queue of locations to reinforce/upgrade.
- **Refund-cycling**: walls/turrets that drop below an HP threshold or are no longer optimal are removed (the engine refunds 75% of remaining HP-proportional SP) and re-built next turn.
- **Lostkids' V-shape 2-layer**: two layers of walls in a V from edge to edge, with three upgraded front-line turrets on each side, dedicated entrances opened/closed by removing/rebuilding 1–2 walls per turn.
- **Lostkids' offense**: detects whether a direct path exists; if yes, sends 1 scout batch (won't be intercepted by walls because path is open); if not, sends 3 batches (first two break walls, third scores).

### 3.3 Simulation-Driven Algos (Ryan Draves, CMU13, Team 6)
- **In-process simulator** that re-implements the action phase: information units' BFS pathing (recompute when a structure is removed/destroyed), attack targeting (mobile > nearest > lowest HP+shield > closest-to-attacker-edge > farthest-from-centerline > highest-UID), self-destruct radius damage, support shielding contribution.
- **Performance optimizations** (CMU13): store pieces in an auxiliary list (sparse board) to avoid scanning cells; precompute movement paths once per round, recompute only when structures change — boosted them from 24 sims/sec to 380 sims/sec, and ultimately 480/sec in C++.
- **Evaluation function**: typical form is `α·units_scored + β·cores_damage + γ·friendly_HP_remaining − δ·enemy_HP_lost` with α = 3.5 (Team 6) or a "security minimax" U(board) = score(blue)·HP(blue) − score(red)·HP(red) (CMU13).
- **Search**: Team 6 enumerates a small set of named (defense_template × attack_pattern) tuples and evaluates each once. CMU13 performs an **approximate simultaneous-move minimax** generating thousands of opponent deployments (using past observed deployments + random perturbations) and chooses the deployment that maximizes the *worst-case* score (security/maximin). They specifically note that pure security overweights defensive Scrambler/Interceptor plays and shifted to "approximate security."

### 3.4 Predictor / Opponent-Model Algos (Ryan Draves)
- A class hierarchy of "predictors" each looks for a specific opponent pattern (e.g., "always sends 12+ scouts at MP threshold T from corner X").
- Predictors maintain confidence; a confident predictor *spends the enemy's resources* in the simulated game state, allowing the algo to plan against the *predicted* deployment rather than an empty turn.
- Critically, top players observed that the prediction phase had been under-prioritized in the wider community; Ryan Draves attributes much of his Season-1 success to predictors.

### 3.5 ML-Based Algos (Ryan Draves' ML_Bootstrap, kcirerick, TaoXU0, PForet)
- **ML_Bootstrap pipeline**: replay-fetcher script that hits Terminal's API → data generator that converts replays into supervised (board_state → defensive_placements) pairs → Keras/TF training environment → **C++ neural network port** (no NumPy / TensorFlow at runtime, since uploaded algos run inside a sandbox with strict dependency limits and timeout). Reached ~rank 40 with pure imitation learning on placements while keeping handcrafted simulation for attack decisions.
- **PForet/TC1** reverse-engineers the engine specifically to support RL (since the official engine isn't designed for fast self-play loops).
- **Team 6** abandoned PPO RL because the action space (every (x,y) for every unit type for every turn) was too vast and rewards too sparse.

### 3.6 Common Helper Modules Observed
- `simulator.py` / `simulator.cpp` — frame-by-frame action-phase reimplementation
- `damage_calculator.py` — given a list of attackers and a path, expected HP loss per frame
- `pathing.py` / `pathing_predictor.py` — BFS clone of `find_path_to_edge` with axis-alternating tiebreaks
- `predictors/` — opponent-pattern detectors (corner_rush, slow_emp, scout_alternation, …)
- `defenses/` — named build templates (corner_diamond, sawtooth, v_funnel, 9-11-13_maze)
- `attacks/` — named offensive sequences (scout_rush, demolisher_line, interceptor_trap, mixed_wave, suicide_bomb)
- `replay_parser.py` — for offline analysis of `.replay` JSON files
- `gui.py` (Tkinter / Pygame / web map-maker like [kevinbai.design/terminal-map-maker](https://www.kevinbai.design/terminal-map-maker)) — for designing structures by hand

---

## 4. Unified Strategy Taxonomy

### 4.1 Defensive Archetypes
- **Corner Diamond / "Ping Cannon" structure** — heavy turrets in both corners, walls layered to push enemy units into kill-zone alleys. The community thesis is that "everything is a disguised ping cannon": dominate the middle, then play out the sides ([forum.c1games.com](https://forum.c1games.com/t/disguised-ping-cannon-meta/1446)).
- **9-11-13 Maze (sawtooth)** — AdrianMargel's static maze that builds in the same order every game, never spending more than 92 cores; sends scouts at [24,10] every turn. Won the CodeBullet $2,000 challenge despite being completely non-adaptive.
- **V-Shape 2-Layer** (Lostkids) — two layers of walls in a V across the map, 3 upgraded turrets each side near the edge; tuned against 2-batch scout spam.
- **Horizontal Wall + Dual Entrances** (Lostkids v1) — wall line with two openings opened/closed dynamically based on offense/defense state.
- **FUNNEL** (Murphy's Lawyers) — funnels enemies into dense turret kill-zones.
- **Reactive corners** (starter pattern) — track `scoredOnLocations` and rebuild defenses one square above each breach the next turn.
- **Layered walls + supports behind** — Walls absorb hits; the new ruleset's Support HP=1 means supports must be deeply protected (in original rules they had 30 HP, so they could sit at the front line).

### 4.2 Offensive Archetypes
- **Scout/Ping Rush** (the dominant attack) — Spawn 100 cheap fast units in one turn, usually every other turn, choosing the spawn location with `least_damage_spawn_location` over candidate edge locations [13,0] / [14,0] etc. The starter Java algo explicitly demonstrates this pattern.
- **Demolisher Line** — Build a horizontal "highway" wall row at y=10–11, spawn many demolishers at [24,10] (or symmetric); demolishers get long range against any structure cluster on opponent's front rows. Triggered by detecting >10 enemy units at y=14,15.
- **Interceptor Wall / Trap** — Spawn interceptors as defensive mobiles to chew up an inbound scout rush. Lostkids switches between wall-based and interceptor-based corner defense based on predicted opponent batch count.
- **Mixed Wave** — Demolisher tank + scouts behind, common in 2019 era.
- **Self-Destruct Bomb** — Wall the unit in deliberately so it travels >5 tiles before getting blocked, triggering self-destruct (deals damage equal to starting HP). Useful for blowing open a wall layer.
- **EMP Spam (legacy term for Demolisher)** — gradual attrition through long-range damage, Team 6's original design.
- **Replay-Mimic** (Voice's exploit) — parse opponent's prior replay and replay your own pre-computed counter sequence ([forum.c1games.com](https://forum.c1games.com/t/kauffks-final-game/875)). Borderline ethically but legal under rules.

### 4.3 Economic Strategy
- **MP banking** — MP decays 25% per turn, so banking is bounded; optimal threshold is to attack when MP ≥ ~12 (1 batch) or ~24 (multi-batch).
- **SP cycling via removal** — `attempt_remove` returns 75% of (SP × HP_fraction). Top algos remove half-damaged turrets to reinvest the SP into upgraded versions or shifted positions. Sub-rule note: **competition ruleset makes wall upgrades cost 2 SP** (was 0), so wall-upgrade timing is meaningful.
- **+4 base SP + damage SP** — algos that absorb damage on cheap walls actually *gain* SP, enabling counter-builds.

### 4.4 Counter-Strategy / Adaptation
- **Pattern-match opponent** (Lostkids, Ryan Draves): track which side they attack, MP threshold at which they attack, batch counts. Use this to choose interceptors vs. walls.
- **Reactive defense**: rebuild + upgrade at scored-on locations.
- **Foregoing rebuild** (Team 6 insight): if opponent is EMP-spamming a corner and there's no path for them to ping into the breach, don't rebuild — denies them free SP from your damage.
- **Wall sacrificing**: deliberately leave low-HP walls in front so the opponent expends shots on cheap targets.
- **Approximate security** (CMU13): pure maximin overweights defensive plays; shift to expected-value-with-floor.

### 4.5 Late-Game (rounds 50–100)
- HP decides: tie-break is HP at round 100, so any algo behind starts spending all SP defensively.
- Enormous MP reserves accumulate if not spent (decay caps it at ~4× per-turn income).
- Support shielding compounds — top algos line the offensive corridor with supports right before a final all-in.

### 4.6 Map-Control Concepts
- **Centerline control** — middle structures force opponent units to one side, where you concentrate turrets.
- **Corner pressure** — by attacking only one corner and forcing them to commit defenses there, you free the opposite side for a bigger eventual rush.
- **Path-prediction abuse** — because pathing is BFS with axis-alternating tiebreaks, you can place a single wall to flip opponent units' paths, wasting their MP.

### 4.7 Opponent Unpredictability
- Top algos ensemble: maintain N candidate strategies and pick the one with best worst-case (security) score against the predicted opponent distribution.
- Many add small randomness on tied evaluations to avoid being deterministically counter-mimicked.

---

## 5. Tools & Workflows for Terminal-Style Competitions with Claude Code

The Terminal codebase has unusual properties for AI-assisted development: (a) the action phase is **deterministic given inputs**, but (b) match outcomes are **highly non-deterministic across opponents**, and (c) Python algos run sandboxed with timeout, which prohibits heavy ML dependencies. This shapes Claude Code best practices.

### 5.1 Claude Code Project Setup for Terminal Algos

**Recommended directory layout (tested pattern combining lessons from [Anthropic's Claude Code best practices](https://code.claude.com/docs/en/best-practices), [How Anthropic teams use Claude Code](https://www-cdn.anthropic.com/58284b19e702b49db9302d5b6f135ad8871e7658.pdf), and Terminal-specific repos):**

```
my-terminal-algo/
├── CLAUDE.md                # Project memory: rules, conventions, gamelib gotchas
├── .claude/
│   ├── settings.json        # Permissions (allow run_match.sh, deny network)
│   ├── agents/              # Subagents: simulator-builder, replay-analyst, evaluator
│   ├── commands/            # /selfplay, /benchmark, /replay-analyze, /tournament
│   └── hooks/               # Pre-edit linter; post-edit unit tests
├── docs/
│   ├── rules.md             # Pasted-in current ruleset (very important — rules drift)
│   ├── gamelib-cheatsheet.md
│   ├── strategy-notes.md    # Living journal of what works/doesn't
│   └── opponent-models.md   # Patterns observed in replay corpus
├── algos/
│   ├── prod/                # Current submission
│   ├── candidate/           # Versions under test
│   └── baselines/           # starter-algo + tournament boss snapshots
├── gamelib/                 # Vendored from C1GamesStarterKit
├── sim/                     # Custom simulator (see §3.3)
│   ├── action_phase.py
│   ├── pathing.py
│   ├── targeting.py
│   └── tests/
├── tools/
│   ├── replay_parser.py
│   ├── self_play.py
│   └── tournament.py
├── replays/                 # Downloaded/local .replay files
└── engine.jar               # From starter kit
```

### 5.2 The CLAUDE.md File (most leverage)

[Anthropic's own teams](https://www-cdn.anthropic.com/58284b19e702b49db9302d5b6f135ad8871e7658.pdf) emphasize: *"the better the CLAUDE.md files, the better Claude Code performs"*; Arize's prompt-learning research ([Arize blog](https://arize.com/blog/claude-md-best-practices-learned-from-optimizing-claude-code-with-prompt-learning/)) produced 5–11% improvements on SWE-Bench by tuning CLAUDE.md alone. For Terminal specifically the file should encode:

1. **Hard rules of the current competition** (e.g., Walls 2 SP to upgrade, Support HP=1, Turret 60/100 HP, Turret damage 6/20). Rule changes between competitions are a primary source of bugs.
2. **`gamelib` gotchas** (set `enable_warnings=False`, decrement `_player_resources` after `attempt_spawn` if you're checking affordability in the same turn, prefer `attempt_spawn(..., 1000)` to spawn-as-many-as-affordable).
3. **Test commands**: `python scripts/run_match.py algos/candidate algos/prod`, `python tools/self_play.py --rounds 50`.
4. **Determinism guarantees**: simulator must be byte-identical to engine's action phase for the same inputs; any divergence is a bug, not a feature.
5. **Time budget**: each turn has a 5-second limit (15 sec for first turn). Simulation calls must respect this.
6. **What NOT to do**: don't add network calls, don't add heavy deps (NumPy is OK; TensorFlow/PyTorch will time out at import), don't break the `algos/prod` algo without updating tests.

### 5.3 Plan Mode + Slash Commands for Iteration Loops

Per [Claude Code best practices](https://code.claude.com/docs/en/best-practices), the recommended four-phase workflow is **Explore → Plan (Plan Mode, Shift+Tab twice) → Implement → Verify**. For Terminal:

- `/selfplay <algo_a> <algo_b> [n=20]` — runs n matches, prints win-rate and average HP-margin.
- `/benchmark <candidate>` — runs candidate vs. starter, vs. last 3 prod versions, vs. corpus of downloaded high-rank replays.
- `/replay-analyze <replay.json>` — loads a replay, summarizes opponent's defensive structure, attack timing, MP-thresholds.
- `/tournament` — round-robin all algos under `algos/baselines/` + `algos/candidate/`, save win-matrix.
- `/diff-strategy <a> <b>` — compares two algos' decision logs over the same input replay frame-by-frame.

### 5.4 Subagents for Game-AI Development

[Subagents](https://code.claude.com/docs/en/sub-agents) run in isolated context windows and only return summaries. Useful Terminal subagents:

- **`simulator-validator`** — given a `.replay` and your simulator, verifies frame-by-frame outputs match the engine. Returns only divergence lines.
- **`replay-analyst`** — reads a corpus of replays (potentially 100+ files), produces opponent-pattern frequencies. Without subagent isolation this would blow main context.
- **`code-reviewer`** — fresh-context reviewer of your candidate algo before submission ([Boris Cherny / Anthropic recommend Writer/Reviewer pattern](https://code.claude.com/docs/en/best-practices)).
- **`tester`** — runs the test harness, returns only failures.

### 5.5 Test-Driven Development for Game Bots

Anthropic explicitly recommends TDD for agentic coding ([Best Practices](https://code.claude.com/docs/en/best-practices)):

1. Ask Claude to write tests based on input/output pairs (e.g., "given board state X, our `simulator.evaluate(scout_rush_left)` must return ≥ 3 scored units"). Tell it explicitly *not* to write implementation yet.
2. Run tests, confirm failure.
3. Commit tests.
4. Implement until tests pass; "verify with independent subagents that the implementation isn't overfitting to the tests."

For nondeterministic outcomes, use **statistical tests**: "candidate algo wins ≥ 55% over 200 self-play matches against prod." The seed should be fixed when using `run_match.py` for reproducibility.

### 5.6 Self-Play Testing Harness (the critical infrastructure)

The most successful Terminal teams (CMU13, Team 6, Ryan Draves) all built rich local match harnesses. A minimal `tools/self_play.py` should:

1. Spawn matches in parallel using `multiprocessing` (each match is ~10–30 sec).
2. Sweep candidate against (starter, last-prod, top-N prior versions, public competitor algos from forum corpus).
3. Aggregate to a win-matrix and Elo or Bradley-Terry ranking.
4. Surface specific opponent algos where candidate regresses.

Pair this with [Boris Cherny's parallel-Claude pattern](https://code.claude.com/docs/en/best-practices): "Create 3-4 git checkouts in separate folders, open each in separate terminal tabs, start Claude in each with different tasks." For Terminal: one branch tunes defense, one tunes offense, one tunes the simulator, one runs the tournament.

### 5.7 Replay Analysis Workflow

`.replay` files are JSON with full action-phase frames. Pipeline:

1. Use `tools/replay_parser.py` to extract per-turn (own_state, opp_state, own_deployments, opp_deployments, score_changes).
2. Feed the corpus to a `replay-analyst` subagent that clusters opponents by: corner-attacker vs. center-attacker, MP-threshold for attack, scout vs. demolisher preference, support placement style.
3. Save patterns to `docs/opponent-models.md` (which CLAUDE.md references). Over weeks, this becomes a living methodology — the same loop the [Agent RL](https://agent-rl-skill.vercel.app/) skill formalizes for Claude Code.
4. Use clustering to seed your in-algo predictor classes (Ryan Draves' approach).

### 5.8 Debugging Nondeterministic Outcomes

- **Always seed `run_match.py`** with a fixed seed for unit tests; only randomize for benchmarking.
- **Frame-by-frame diff**: when prod and candidate disagree on a single match, run both with the same input replay and diff their `attempt_spawn` calls turn-by-turn. The `diff-strategy` slash command above automates this.
- **Per-turn decision logs**: have your algo print structured JSON to `gamelib.debug_write` describing why it made each decision. Replay these in a `replay-analyst` subagent.
- **Statistical regression tests** rather than single-match assertions: `assert win_rate(candidate, prod, n=200) >= 0.5 - 0.03` (3% tolerance).
- **Avoid context pollution**: when Claude has been correcting failed approaches for >2 turns, run `/clear` and restart with a better prompt incorporating learnings ([dev.to](https://dev.to/jangwook_kim_e31e7291ad98/claude-code-advanced-workflow-subagents-commands-multi-session-50hl)).

### 5.9 Iterative Strategy Refinement Loop (the Ryan Draves pattern, augmented with Claude Code)

```
1. Watch the most recent prod loss in playground.
2. Ask Claude in plan mode: "Why did our algo lose this match? Hypothesize 3 root causes."
3. Pick one, write a failing test in tests/.
4. Have Claude implement a candidate fix.
5. Run /selfplay candidate vs. prod (200 matches). 
6. If win-rate ≥ threshold, run /benchmark against full opponent corpus.
7. If no regression on >80% of corpus, promote candidate → prod, archive prod → baselines/v_n.
8. Update docs/strategy-notes.md with what changed and why.
```

This is exactly the **eval-driven development** loop Anthropic advocates ([Demystifying Evals](https://www.anthropic.com/engineering/demystifying-evals-for-ai-agents)): start with 20–50 simple tasks (in Terminal: 20–50 representative match scenarios), iterate.

### 5.10 Hooks for Safety

[Hooks](https://code.claude.com/docs/en/best-practices) deterministically run scripts at events. Useful for Terminal:
- **Pre-edit hook**: refuse edits to `algos/prod/algo_strategy.py` unless `tests/regression/` passes on candidate.
- **Post-edit hook**: re-run quick smoke match (1 round vs. starter).
- **Pre-submit hook**: refuse to zip an algo that imports `tensorflow`, `torch`, or `requests`.

### 5.11 The "Ralph Wiggum" / Persistent Loop Pattern

For overnight tuning, [the Ralph loop](https://awesomeclaude.ai/ralph-wiggum) (now an official Anthropic plugin) repeatedly feeds a prompt back to Claude until a stop condition. Concretely for Terminal: `claude -p "/ralph-loop:ralph-loop 'Tune the demolisher-line trigger threshold so candidate beats prod by ≥10% over 200 matches. Iterate until DONE.' --max-iterations 30"`.

### 5.12 Subagents + Parallel Sessions for "Agent Teams"

For larger overhauls, use git worktrees (lighter than checkouts) with one Claude session per worktree, each on a separate concern (defense.py / attack.py / simulator/ ). Synchronize via well-defined module boundaries (Team 6's exact engineering insight, achieved differently). Anthropic's [agent teams](https://code.claude.com/docs/en/best-practices) feature automates this for parallel sessions with shared task state.

---

## 6. Additional Resources

### 6.1 Official
- **Main site**: [terminal.c1games.com](https://terminal.c1games.com/) — playground, match upload, leaderboard.
- **Rules**: [terminal.c1games.com/rules](https://terminal.c1games.com/rules).
- **Docs server**: [docs.c1games.com](https://docs.c1games.com).
- **Forum**: [forum.c1games.com](https://forum.c1games.com/) — primary strategy discussion site.
- **Citadel Terminal program page**: [citadel.com/careers/programs-and-events/terminal](https://www.citadel.com/careers/programs-and-events/terminal/) ; [citadelsecurities.com/careers/programs-and-events/terminal](https://www.citadelsecurities.com/careers/programs-and-events/terminal/).
- **Map maker**: [kevinbai.design/terminal-map-maker](https://www.kevinbai.design/terminal-map-maker) — community tool for visually designing structures.
- **Python gamelib API**: [pythondocumentation.readthedocs.io/en/latest/gamelib.html](https://pythondocumentation.readthedocs.io/en/latest/gamelib.html).

### 6.2 Tutorials & Postmortems
- **Sentdex tutorial series**: [pythonprogramming.net/correlation-one-terminal](https://pythonprogramming.net/correlation-one-terminal/) (still the best beginner walkthrough despite legacy unit names).
- **Medium publication "Terminal Player Strategies"**: [medium.com/terminal-player-strategies](https://medium.com/terminal-player-strategies) — host of the Ryan Draves, CMU13, CMU17, Team 6 postmortems.

### 6.3 Tournament VODs
- **Terminal Global Championship Final Four (May 2021)**: [youtube.com/watch?v=904OUEAk4xI](https://www.youtube.com/watch?v=904OUEAk4xI).
- **Terminal Live event archives** on [twitch.tv/correlation_one](https://www.twitch.tv/correlation_one) and re-uploads on [twitch.tv/videos/483165853](https://www.twitch.tv/videos/483165853).
- **CodeBullet Terminal challenge announcement video** — referenced in Ryan Draves' postmortem; still the largest single bot-game audience exposure for Terminal.

### 6.4 Cross-Pollination Competitions
- **MIT Battlecode** — [battlecode.org](https://battlecode.org/). Real-time strategy in Java; pathfinding, distributed comms, $15K prize pool, every January. Terminal alumni who win Battlecode include the same demographic.
- **Russian AI Cup** — [Wikipedia](https://en.wikipedia.org/wiki/Russian_AI_Cup); turn-based strategy, multiple language support, MacBook/cash prizes.
- **Lux AI Challenge** — [Stone Tao's writeup](https://blog.stoneztao.com/posts/ai-challenge-survey/) (also the best comparative analysis of AI competition design); 1v1 RTS on Kaggle, Python/JS/C++/Java, $10K+.
- **Halite** (Two Sigma, on Kaggle) — resource-management; classic for studying ML-vs-heuristic tradeoffs.
- **CodinGame Spring Challenge** — bi-annual 10-day contests; rapid-iteration analog to Terminal events.
- **Screeps** — persistent-world JavaScript bot game; closest analog for "long-running production algo" mentality.
- **Coder One Bomberland**, **AICrowd**, **Kaggle ConnectX/Hungry Geese** — broader ecosystem ([gocoder.one/blog/ai-game-competitions-list](https://www.gocoder.one/blog/ai-game-competitions-list/)).

### 6.5 Academic Background
- **MCTS-Minimax Hybrids** — Baier et al., [Journal of AI Research](https://www.jair.org/index.php/jair/article/download/11208/26419/20772) and [MCTS-αβ rollout-based hybrid](https://se1f330a320707f8e.jimcontent.com/download/version/1474273359/module/12396903227/name/a%20rollout-based%20search%20algorithm%20unifying%20mcts%20and%20alpha-beta.pdf). Highly relevant: Terminal's branching factor is too large for pure minimax (CMU13 explicitly noted this), and pure MCTS is weak in the tactical phase, so hybrid algorithms with informed rollouts are theoretically the right frontier — though no public Terminal algo has yet implemented full MCTS-αβ.
- **MCTS with Dynamic Depth Minimax** — [Ji & Thielscher 2023](https://cgi.cse.unsw.edu.au/~mit/Papers/ACG23.pdf). Particularly relevant for time-constrained Terminal turns.
- **AlphaZero MCTS-Minimax in self-play** — [Truong 2023](https://ml-research.github.io/papers/truong2023monte.pdf).

### 6.6 Claude Code & AI-Assisted Coding References
- **Anthropic — Claude Code best practices**: [code.claude.com/docs/en/best-practices](https://code.claude.com/docs/en/best-practices).
- **Anthropic — Common workflows**: [code.claude.com/docs/en/common-workflows](https://code.claude.com/docs/en/common-workflows).
- **Anthropic — Subagents**: [code.claude.com/docs/en/sub-agents](https://code.claude.com/docs/en/sub-agents).
- **Anthropic — Building agents with the Claude Agent SDK**: [anthropic.com/engineering/building-agents-with-the-claude-agent-sdk](https://www.anthropic.com/engineering/building-agents-with-the-claude-agent-sdk).
- **Anthropic — Demystifying evals for AI agents**: [anthropic.com/engineering/demystifying-evals-for-ai-agents](https://www.anthropic.com/engineering/demystifying-evals-for-ai-agents).
- **How Anthropic teams use Claude Code (PDF)**: [www-cdn.anthropic.com/58284b19e702b49db9302d5b6f135ad8871e7658.pdf](https://www-cdn.anthropic.com/58284b19e702b49db9302d5b6f135ad8871e7658.pdf).
- **Arize — CLAUDE.md best practices learned from prompt-learning optimization**: [arize.com/blog/claude-md-best-practices-learned-from-optimizing-claude-code-with-prompt-learning](https://arize.com/blog/claude-md-best-practices-learned-from-optimizing-claude-code-with-prompt-learning/).
- **Awesome-Claude-Code** (curated list of skills, hooks, slash-commands): [github.com/hesreallyhim/awesome-claude-code](https://github.com/hesreallyhim/awesome-claude-code).
- **Claude Code best-practice synthesis (Rosmur)**: [rosmur.github.io/claudecode-best-practices](https://rosmur.github.io/claudecode-best-practices/).
- **Game-development-specific Claude Code workflows**: [github.com/HermeticOrmus/claude-code-game-development](https://github.com/HermeticOrmus/claude-code-game-development) and [github.com/Donchitos/Claude-Code-Game-Studios](https://github.com/Donchitos/Claude-Code-Game-Studios) (49 agents, 72 skills — overkill for a single-file algo, but the hook patterns transfer).
- **Slash commands vs. subagents vs. skills explained**: [alexop.dev/posts/claude-code-customization-guide-claudemd-skills-subagents](https://alexop.dev/posts/claude-code-customization-guide-claudemd-skills-subagents/) and [youngleaders.tech/p/claude-skills-commands-subagents-plugins](https://www.youngleaders.tech/p/claude-skills-commands-subagents-plugins).
- **Agent RL (per-skill feedback memory)**: [agent-rl-skill.vercel.app](https://agent-rl-skill.vercel.app/).
- **Ralph Wiggum loop** (overnight iterative coding): [awesomeclaude.ai/ralph-wiggum](https://awesomeclaude.ai/ralph-wiggum).

---

## 7. Synthesis and Recommendations

Based on the body of evidence:

1. **The single biggest competitive lever is a fast, accurate simulator of the engine's action phase.** CMU13 went from rank 19 → 5 just by fixing simulator bugs; Ryan Draves and Team 6 echo this. Build it first, in pure Python initially, then port hot loops to C/Cython if you need >100 sims/turn.

2. **The second-biggest lever is opponent prediction.** Ryan Draves's Season-1 success came from predictors others under-prioritized. Even a simple "this opponent has spawned scouts at MP threshold T from corner X for the last 3 turns, assume they will again" radically improves simulated minimax accuracy.

3. **Modular architecture wins in teams.** Separate `defenses/`, `attacks/`, `simulator/`, `predictors/` modules let multiple humans (or multiple Claude Code sessions) work in parallel without merge hell. All three top postmortems explicitly cite this.

4. **Static algos can win** (sawtoothV2 won CB challenge being completely non-adaptive). Don't over-engineer adaptiveness if a clean, well-tested static algo is killing the leaderboard. The "disguised ping cannon" thesis suggests the meta is convergent toward a small set of dominant structural patterns.

5. **For Claude Code specifically**: the highest-leverage initial investments are (a) a thorough CLAUDE.md encoding the current ruleset and gamelib gotchas, (b) slash commands wrapping `/selfplay`, `/benchmark`, `/replay-analyze`, (c) a `simulator-validator` subagent to keep your simulator byte-identical to the engine, (d) git worktrees with parallel Claude sessions for offense/defense/simulator concerns, and (e) an eval suite of 20–50 representative match scenarios that you grow as you encounter losses on the live ladder. Use Plan Mode (Shift+Tab twice) for any non-trivial feature; use TDD to avoid the "looks right, fails on edge case" failure mode that is endemic to game logic.

6. **Cross-pollinate from Battlecode and Lux AI** for engineering patterns (precomputed paths, sparse-board data structures, distributed task allocation) but don't expect ML to dominate Terminal — every team that has tried (Team 6 PPO, Ryan Draves' supervised NN, kcirerick's Deep RL, TaoXU0) has either pivoted to heuristics or capped out below pure simulation-driven heuristic algos. The game's combinatorially huge action space and sandboxed dependency-free runtime make it hard for ML to outperform a well-engineered handcrafted simulator.

The Terminal community has produced an unusually well-documented competitive bot ecosystem; the repos and postmortems above effectively constitute a multi-year curriculum for anyone serious about climbing the leaderboard.