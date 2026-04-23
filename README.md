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
| [`docs/CLAUDE_WORKFLOW.md`](docs/CLAUDE_WORKFLOW.md) | **The iteration loop** — how to actually use Claude Code to develop algos |
| [`docs/`](docs/) | Rules, unit reference, API, map, strategy, local-testing guides |
| `algos/` | Your algorithms (one directory each) |
| `tools/` | Match running: `run.sh`, `test.sh`, `bestof.py` (parallel), `tournament.py` (parallel), `eval.sh`. Replay analysis: `analyze_replay.py`, `detailed_replay.py`, `batch_replays.py`, `profile_turns.py`. Algo management: `new_algo.sh`, `diff_algos.sh`. |
| [`docs/BASELINE_BUILDING_PROMPT.md`](docs/BASELINE_BUILDING_PROMPT.md) | Two prompts for a fresh Claude Code session: **best-algo** (60-120 min, adaptive/opponent-modeled) and **baseline** (30-60 min, faster bar) |
| [`docs/OBTAINING_REPLAYS.md`](docs/OBTAINING_REPLAYS.md) | How to download official replays and why screen recording is not a substitute |
| [`docs/RUST_MIGRATION.md`](docs/RUST_MIGRATION.md) | Phase-2 playbook: when and how to port the algo to Rust (read only after Python plateaus ~1900 ELO) |
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
| `/bestof` | Play the same matchup N times (2N including both sides) with confidence interval |
| `/tournament` | Round-robin between 3+ algos |
| `/analyze-replay` | Quick summary of a `.replay` file |
| `/detailed-replay` | Deep section-by-section analysis of one replay (use for official replays) |
| `/batch-replays` | Aggregate analysis across many replays — breach hot spots, compute trends, outliers |
| `/profile-turns` | Report per-turn compute time vs. the 15 s budget |
| `/inspect-config` | Dump the live server config (resolves doc ambiguities) |
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
