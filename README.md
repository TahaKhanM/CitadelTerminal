# Citadel Terminal — Algo Workspace

Workspace for building algorithms for the Citadel Terminal competition on https://terminal.c1games.com. Set up for fast iteration with Claude Code.

## Quickstart

```bash
# 1. Scaffold a new algo variant
./tools/new_algo.sh my_first_algo

# 2. Edit strategy
$EDITOR algos/my_first_algo/algo_strategy.py

# 3. Smoke test (fast, ~5s)
./tools/test.sh my_first_algo

# 4. Run a match against the starter baseline
./tools/run.sh my_first_algo C1GamesStarterKit-master/python-algo

# 5. Tournament between several variants
python3 tools/tournament.py algos/v1 algos/v2 algos/v3

# 6. Analyze a replay
python3 tools/analyze_replay.py replays/<file>.replay

# 7. Package for upload
./C1GamesStarterKit-master/scripts/zipalgo_mac algos/my_first_algo algos/my_first_algo.zip
# then upload at https://terminal.c1games.com/myalgos
```

## Layout

| Path | Contents |
|---|---|
| [`CLAUDE.md`](CLAUDE.md) | Orientation for Claude Code sessions — start here |
| [`docs/`](docs/) | Rules, unit reference, API, map, strategy, local-testing guides |
| `algos/` | Your algorithms (one directory each) |
| `tools/` | Helper scripts: `run.sh`, `test.sh`, `new_algo.sh`, `tournament.py`, `analyze_replay.py` |
| `replays/` | Local replay files produced by `tools/run.sh` |
| `C1GamesStarterKit-master/` | Official starter kit (engine.jar, sample algo, test/zip binaries) — treat as read-only |
| `Citadel Context Files/` | Original docs (HTML + screenshots + video transcripts) |
| `.claude/settings.json` | Pre-approved Bash permissions for Claude Code |
| `.claude/skills/` | Custom Claude Code skills (invoke with `/<skill-name>`) |

## Claude Code skills available

| Slash command | Purpose |
|---|---|
| `/new-algo` | Scaffold a new algo folder |
| `/run-match` | Run a local match between two algos |
| `/test-algo` | Quick syntax/runtime check against a replay |
| `/analyze-replay` | Parse and summarize a `.replay` file |
| `/tournament` | Round-robin between 3+ algos |
| `/upload-algo` | Zip an algo for upload |
| `/competition-reference` | Fast lookup for exact rules, stats, formulas |
| `/skill-creator-terminal` | Scaffold a new skill for this project |

## Prerequisites

- Python 3.6+
- Java 10+ (for the engine)
- macOS/Linux/Windows (starter kit ships binaries for all three)

See [`docs/LOCAL_TESTING.md`](docs/LOCAL_TESTING.md) for full install + troubleshooting.

## Competition ruleset quick-reference

The rules deviate from the base Terminal game. Authoritative values live in [`docs/UNITS_REFERENCE.md`](docs/UNITS_REFERENCE.md). Some highlights:

- Wall upgrade **costs 2 SP** (free in base game).
- Support base HP is **1** (was 30).
- Turret base HP is **60** (was 75), base damage **6**, upgrade damage **20**, upgrade HP **100**.
- Upgraded Support shield scales with Y position: `shield = 1 + 0.3 × Y`.

Full details: [`docs/GAME_RULES.md`](docs/GAME_RULES.md) and [`docs/UNITS_REFERENCE.md`](docs/UNITS_REFERENCE.md).
