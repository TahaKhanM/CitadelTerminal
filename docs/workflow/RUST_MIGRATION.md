# Rust Migration Playbook

**Only read this if you've exhausted Python strategy improvements and you need compute-intensive techniques (tree search, Monte Carlo, 2-turn lookahead).**

The Citadel Terminal leaderboard position #1 (ELO 2031) uses Rust. 7 of the top 8 use Python. The 277-ELO gap between position #2 (Python, 1966) and position #8 (us, 1689) suggests **Python has headroom** — don't port prematurely. Port only when Python's 15-second-per-turn compute budget actively blocks your next strategic improvement.

## When to port

Trigger conditions (ANY of):

- [ ] You've done 10+ Python iterations and plateaued at ~1900 ELO.
- [ ] Your strongest strategic hypothesis requires per-turn compute exceeding ~3 seconds in Python.
- [ ] You want to implement 2+ turn lookahead, Monte Carlo rollouts, or path-search-based opponent modeling.

If NONE of those hold, keep iterating Python.

## Protocol — what the engine needs from a Rust algo

The engine (`C1GamesStarterKit-master/engine.jar`) communicates with each algo via stdin/stdout, **language-agnostic**. The wire protocol is JSON-lines:

- **Config frame** (once at game start): full unit/resource config blob, one JSON line on stdin.
- **Turn frame** (`turnInfo[0] == 0`, start of deploy phase): game state JSON on stdin. Algo must reply with TWO JSON lines on stdout:
  1. Build-phase commands: list of `[shorthand, x, y]` or `["UP", x, y]` or `["RM", x, y]`.
  2. Deploy-phase commands: list of `[shorthand, x, y]` for mobile units.
- **Action frame** (`turnInfo[0] == 1`, during action phase): game state JSON on stdin. No reply required (algos can read events and do bookkeeping).
- **End frame** (`turnInfo[0] == 2`): game over; algo should exit cleanly.

Your `run.sh` should invoke the compiled Rust binary. The zipped upload contains the binary (or source that compiles on Ubuntu 22.04 with `cargo build --release`).

## Minimum viable Rust starter

```
rust-algo/
├── Cargo.toml
├── run.sh                # invokes ./target/release/algo
├── src/
│   ├── main.rs           # main loop: read stdin, dispatch, write stdout
│   ├── types.rs          # serde-derived structs matching the JSON wire format
│   ├── game_state.rs     # GameState wrapper (analogous to python gamelib.GameState)
│   ├── pathing.rs        # ShortestPathFinder port
│   ├── unit.rs           # GameUnit
│   └── strategy.rs       # YOUR algo logic
└── algo.json
```

Key crates:
- `serde`, `serde_json` — JSON parsing (drop-in, well-optimized).
- `smallvec` — stack-allocated vectors for hot loops.
- `ahash` — faster hash maps (path-cache keys).

Avoid stdlib's `HashMap` in hot paths — it's 2-3× slower than `ahash::AHashMap`.

## Porting checklist

1. **Write the wire-protocol layer** (main.rs + types.rs) first. Test with `test_algo_mac <rust-algo>/` against a sample replay — it should run clean but do nothing.
2. **Port gamelib data structures** (`GameMap`, `GameUnit`, `GameState`, `ShortestPathFinder`) with `serde` — roughly 500-800 lines of straight translation from `C1GamesStarterKit-master/python-algo/gamelib/*.py`.
3. **Port your Python strategy verbatim** to Rust. Make no algorithmic changes in this step; just get feature parity.
4. **Run `./tools/eval.sh`** against the ported version — it should match Python's win rate against starter (modulo any bugs in the port). If it doesn't, fix those before adding new techniques.
5. **Now start using the compute budget** — add tree search, simulation, or whatever advanced technique motivated the port. Benchmark each addition.
6. **Upload and re-evaluate on ranked**. Expected gain over pure-Python equivalent: +100-200 ELO from unlocked techniques.

## Existing community Rust starters

Search the C1 forum and `community/` for existing starter kits. If one exists, fork it rather than writing from scratch. If none exists, the C1 team will accept a PR (see `community/README.md`).

Rough estimate: if no starter exists, budget 2-4 full days to write the protocol layer + gamelib port + have a Rust algo that reproduces your Python algo's behavior. Another 2-5 days for the strategic additions that motivated the port.

## What doesn't change

All of the tooling under `tools/` still works with a Rust algo — `run.sh`, `test.sh`, `bestof.py`, `tournament.py`, `eval.sh`, `detailed_replay.py`, `batch_replays.py`. They all communicate with algos via `run.sh` and don't care what language the algo is written in.

The replay format is the same, so all the analysis tools apply unchanged.

## What to watch out for

- **Upload size**: don't ship compiled binaries bigger than 50 MB. Strip the binary (`strip -s`) and use `--release` LTO.
- **Ubuntu 22.04 compatibility**: the server compiles your algo (or runs a pre-compiled binary you ship). If you ship a binary built on macOS, it won't run on Linux. Either ship source + `Cargo.toml` and let the server build, or cross-compile with `x86_64-unknown-linux-gnu`.
- **Panics = crash = forfeit**. Use `Result` everywhere; panics in `on_turn` cause a forfeit.
- **Compute time measurement**: Rust's stdlib `Instant` is fine for tracking turn time — use it to enforce your own safety budget (e.g. short-circuit to a fallback move if you've spent > 10 s).
