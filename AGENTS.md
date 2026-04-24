# Citadel Terminal — Codex Project Context

You are helping develop **algorithms ("algos") for the Citadel Terminal competition** — a special-ruleset version of Correlation One's *Terminal*, a two-player tower-defense game where bots play ranked matches against each other on a diamond-shaped 28×28 grid.

**CRITICAL: This is a *special competition* with rule changes vs. the base game.** Always consult [`docs/GAME_RULES.md`](docs/GAME_RULES.md) before reasoning about unit stats — the values shipped in `C1GamesStarterKit-master/game-configs.json` are from an older/base season and **do NOT reflect competition values**. The competition's real config is delivered by the server at game start.

---

## Repository layout

```
CitadelTerminal/
├── AGENTS.md                      ← you are here (orientation)
├── docs/                          ← deep reference material (READ THESE)
│   ├── GAME_RULES.md              ← turn structure, resources, win conditions, competition deltas
│   ├── UNITS_REFERENCE.md         ← exact stats for every unit (COMPETITION values)
│   ├── MAP_AND_COORDINATES.md     ← diamond geometry, edges, spawn locations
│   ├── TARGETING_AND_PATHING.md   ← engine's movement/targeting/self-destruct rules
│   ├── API_REFERENCE.md           ← GameState / GameMap / GameUnit methods
│   ├── STRATEGY_GUIDE.md          ← archetypes, math, common patterns & counters
│   ├── CLAUDE_WORKFLOW.md         ← the iteration loop: ideate → edit → test → match → analyze → upload
│   └── LOCAL_TESTING.md           ← how to run matches locally and test syntax
├── algos/                         ← user's algorithms live here (one folder per algo)
├── tools/                         ← helper scripts (match runners, replay analysis)
├── replays/                       ← local match replays land here (.replay files)
├── C1GamesStarterKit-master/      ← official starter kit (engine.jar, sample algo, scripts)
│   ├── engine.jar                 ← game engine
│   ├── python-algo/               ← reference algo with starter `gamelib`
│   ├── game-configs.json          ← ⚠️ OUTDATED base-game config, NOT competition values
│   └── scripts/                   ← test_algo_*, zipalgo_*, run_match.py
└── Citadel Context Files/         ← original docs (HTML + images + video transcripts)
```

**Where new algos go**: create `algos/<algo_name>/` by copying `C1GamesStarterKit-master/python-algo/`. The `algos/` directory is the canonical workspace for user algorithms; the starter kit is a read-only reference.

---

## How to work on this project

### Before writing or modifying strategy code
1. Re-read [`docs/UNITS_REFERENCE.md`](docs/UNITS_REFERENCE.md) so unit math is correct.
2. Skim [`docs/STRATEGY_GUIDE.md`](docs/STRATEGY_GUIDE.md) for relevant archetypes.
3. If pathing/targeting logic matters, consult [`docs/TARGETING_AND_PATHING.md`](docs/TARGETING_AND_PATHING.md) — most strategic bugs trace back to misunderstanding these rules.

### When editing algo code
- All user code lives in `algo_strategy.py` inside an algo folder; override `on_turn(turn_state)` and optionally `on_game_start(config)` / `on_action_frame(turn_string)`.
- Read `config` values at runtime — **never hardcode unit stats**. The server-supplied config is authoritative and the shipped `game-configs.json` is not.
- `game_state.attempt_spawn(...)` and `attempt_upgrade(...)` silently skip invalid placements; always check return counts or pre-filter locations.
- Stationary units block pathing, including your own; walls placed on edges prevent you from spawning mobile units there next turn.
- `debug_write(...)` in `gamelib.util` prints to stderr, which the engine surfaces as replay debug output — use it liberally while iterating.

### When running matches locally
- Use `python3 C1GamesStarterKit-master/scripts/run_match.py <algo1_dir> <algo2_dir>` (Java 10+ required; `engine.jar` lives in the starter kit root).
- Replay files land in `C1GamesStarterKit-master/replays/` by default — move them to `replays/` in this repo for persistence.
- For fast syntax/sanity checks without a full engine run, use the `test_algo_mac` / `test_algo_linux` binary against a `.replay` file (see [`docs/LOCAL_TESTING.md`](docs/LOCAL_TESTING.md)).
- Watching replays requires uploading them to https://terminal.c1games.com/playground (no local viewer ships with the starter kit).

### Uploading an algo to the competition site
- Zip the algo folder (not the whole repo) with `scripts/zipalgo_mac <algo_folder> <output.zip>`; `.zipignore` controls exclusions.
- Upload the zip at https://terminal.c1games.com under "My Algos" → "Upload an Algo". It will compile, auto-queue ranked matches, and become available on the Playground.

---

## Key constants you will re-use constantly

| | Value |
|---|---|
| Arena size | 28 × 28 (diamond) |
| Your territory | y < 14 (bottom half) |
| Your deploy edges | `BOTTOM_LEFT` (y=x diagonal to [0,13]) + `BOTTOM_RIGHT` (y=27−x diagonal to [27,13]) |
| Starting resources | 8 SP, 1 MP, 40 HP |
| Per-turn MP decay | 25 % (rounds to nearest 0.1) |
| Per-turn base income | 4 SP + (1 + turn//5) MP |
| Bonus SP | +1 per mobile unit that breaches an opponent edge (engine: `metalForBreach=1` per Scout/Demolisher/Interceptor; `coresForPlayerDamage` is a legacy key absent from the live server config) |
| Turn limit | 100 rounds, then highest HP wins |
| Deploy time budget | 15 s before 1 dmg/sec penalty |

Full numerics in [`docs/GAME_RULES.md`](docs/GAME_RULES.md).

---

## Using the built-in skills

Invoke these with `/<skill-name>` when the user asks for related work:

- **`/new-algo`** — scaffold a new algo folder from the starter template.
- **`/run-match`** — run a local match between two algos; stream or tail output.
- **`/test-algo`** — fast syntax/runtime check against an existing replay.
- **`/bestof`** — run 2N matches with a Wilson 95 % CI on the win rate (decides close calls).
- **`/tournament`** — round-robin between 3+ algos with ranked standings.
- **`/analyze-replay`** — parse a `.replay` file and summarize what happened.
- **`/profile-turns`** — report per-turn compute time vs. the 15-second budget.
- **`/inspect-config`** — dump the live server config so you can resolve any doc ambiguity.
- **`/upload-algo`** — zip the algo for manual upload to the competition site.
- **`/competition-reference`** — quickly surface exact unit numbers or rules.

Skills live under `.Codex/skills/` and each has its own SKILL.md with invocation details.

---

## Important "do not confuse this" notes

1. **Starter-kit config is outdated.** `C1GamesStarterKit-master/game-configs.json` uses pre-rename shorthands (FF/EF/DF/PI/EI/SI → Wall/Support/Turret/Scout/Demolisher/Interceptor) AND its numeric values predate the competition changes. Treat it as engine-plumbing reference only — quote competition numbers from `docs/UNITS_REFERENCE.md`.
2. **"Bits" and "Cores" are old names.** In the current docs and this project, say **Mobile Points (MP)** and **Structure Points (SP)**. Old variable names in the starter code (`MP = 1`, `SP = 0`) are *resource-type indices*, not quantities.
3. **Walls upgrade now costs SP.** In the base game, upgrading a Wall was free. In this competition it costs 2 SP. Factor this into defense budgeting.
4. **Base Support HP is 1; upgraded Support HP is 40.** Base Supports die to any stray attack (one-shot shield dispensers). Upgraded Supports are durable anchors. Base shield is **3** per unit (range 3.5); **upgraded shield is `1 + 0.7 × Y` per unit (range 7)** — at Y=13 that's 10.1 shield per Support per Scout, so 4 back-row upgraded Supports ≈ 40 shield per Scout. Numbers verified from the live config extracted from an official downloaded replay.
5. **Upgraded Turrets gain more in this competition** than in the base game (20 dmg / 100 HP vs 16 / 75) — upgrades are unusually strong here.
6. **`player_index`** in the starter code is 0 for you and 1 for opponent. In raw action-frame JSON (`on_action_frame`) the convention flips to 1 = you, 2 = opponent. Easy bug source.
7. **Always work from the bottom half.** Write all coordinates as if you're the bottom player; the engine auto-mirrors when you're actually on top.

---

## Where the original source material lives

- Competition-specific docs (with rule-change table and special-ruleset context): `Citadel Context Files/Actual Game Context/` (HTML + `Doc 1.png`–`Doc 12.png`).
- Base-game Terminal docs (identical rules except for the special-competition table): `Citadel Context Files/Doc Pics/` (`Docs 1.png`–`Docs 21.png` + `Terminal.html`).
- Video tutorial transcripts: `Citadel Context Files/Video Transcripts/` (game objective, units, upload flow, play-by-hand).

All of that has been distilled into `docs/` — but if you ever need to re-verify an exact table or diagram, the source images are authoritative.
