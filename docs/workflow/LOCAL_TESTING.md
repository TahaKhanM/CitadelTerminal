# Local Testing Guide

You will iterate faster by testing locally before uploading to the competition site.

## Prerequisites

- **Python 3.6+** (run `python3 --version` to verify).
- **Java 10+** for the engine (`java --version`). On macOS: `brew install openjdk`. On Linux: `sudo apt install default-jdk`.
- Starter kit binaries at `C1GamesStarterKit-master/scripts/`:
  - `test_algo_mac` / `test_algo_linux` / `test_algo_windows.exe` — fast syntax-and-runtime sanity checker
  - `zipalgo_mac` / `zipalgo_linux` / `zipalgo_win.exe` — zip an algo for upload
  - `run_match.py` / `run_match.sh` / `run_match.ps1` — full match runner wrapping `engine.jar`

First-time setup on Unix:

```bash
chmod +x C1GamesStarterKit-master/scripts/test_algo_mac
chmod +x C1GamesStarterKit-master/scripts/zipalgo_mac
chmod +x C1GamesStarterKit-master/scripts/run_match.sh
chmod +x C1GamesStarterKit-master/python-algo/run.sh
```

## Workflow 1: Quick syntax/runtime check

Feed your algo a replay and see if it crashes. No engine needed; the tool streams frames from the replay into your algo and checks it responds.

```bash
./C1GamesStarterKit-master/scripts/test_algo_mac algos/my_algo/
# or with a specific replay file:
./C1GamesStarterKit-master/scripts/test_algo_mac algos/my_algo/ C1GamesStarterKit-master/scripts/test_replay.replay
```

Use this after any meaningful code change. Catches crashes in ~10 seconds.

## Workflow 2: Full match locally

Play two algos against each other via `engine.jar`. A `.replay` file is written that you can upload to https://terminal.c1games.com/playground to visualize.

```bash
# from repo root
python3 C1GamesStarterKit-master/scripts/run_match.py algos/my_algo algos/baseline
```

Defaults both to `python-algo` if you omit args. Replays land in `C1GamesStarterKit-master/replays/` by default — you'll want to either symlink or move them.

Recommended pattern: a helper script at `tools/run.sh` (already created) that centralizes this.

```bash
./tools/run.sh algos/my_algo algos/baseline
```

## Workflow 3: Round-robin tournament

When you have 3+ variants and want to see which wins:

```bash
./tools/tournament.sh algos/v1 algos/v2 algos/v3
```

This runs every pairing twice (one as bottom, one as top) and summarizes wins.

## Viewing replays

There's NO local replay viewer shipped with the starter kit. Upload the `.replay` file to https://terminal.c1games.com/playground to watch. You'll need a terminal account.

Alternatively, the community has built a Python watcher: `C1GamesStarterKit-master/scripts/contributions/watch_replay.py` — unsupported but works as a rough text dump.

## Debugging during a match

`gamelib.debug_write(...)` writes to stderr, which:
- Shows up in the engine's console output when running locally
- Shows up in the "Debug" pane on the Playground website when you open a replay

Use it aggressively. Typical pattern:

```python
import gamelib

def on_turn(self, turn_state):
    game_state = gamelib.GameState(self.config, turn_state)
    gamelib.debug_write(f"[T{game_state.turn_number}] "
                       f"HP {game_state.my_health} | "
                       f"SP {game_state.get_resource(SP):.1f} | "
                       f"MP {game_state.get_resource(MP):.1f}")
    ...
```

## Uploading

```bash
./C1GamesStarterKit-master/scripts/zipalgo_mac algos/my_algo algos/my_algo.zip
```

Then:
1. Go to https://terminal.c1games.com/myalgos
2. "Upload an Algo"
3. Select the zip file
4. Wait for compile. If it fails, click the algo — the status doesn't refresh automatically.

The algo starts playing ranked matches automatically once compiled. You can view games under "My Global Replays".

## Common errors

| Error | Cause / fix |
|---|---|
| `Unable to access jarfile engine.jar` | You're running from wrong cwd. `engine.jar` is at the starter-kit root. Use `run_match.py` (cwd-independent). |
| `SyntaxError: invalid syntax` on upload but not locally | Your local Python is 3.10+ but server is 3.6-ish. Avoid walrus operators, structural pattern matching, `|`-unions in type hints, f-string `=` specifier. |
| Timeout damage on the server | Your `on_turn` is taking >15s. Profile; the main culprits are repeated `find_path_to_edge` calls. Cache paths within a turn. |
| Algo "crashes" on replay but runs fine locally | Stdin/stdout encoding mismatch on Windows. Prefer running from PowerShell and avoid `print()` (use `debug_write`). |

## Reproducing a specific game locally

1. Download the `.replay` file from the website (right-click → Save).
2. Run `test_algo_mac` with that replay:
   ```
   ./scripts/test_algo_mac algos/my_algo/ ~/Downloads/that_game.replay
   ```
   This streams the SAME game states to your algo. Errors/logs will reproduce.

Note: your algo's *decisions* won't change the simulation — it's running on replay states. This only tests "did my algo crash or error on this state?"
