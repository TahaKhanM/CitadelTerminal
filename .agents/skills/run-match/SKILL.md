---
name: run-match
description: Run a local Citadel Terminal match between two algos using the starter kit's engine.jar. Use when the user wants to play two specific algos against each other locally and inspect the replay. Expects two algo directory paths; defaults to python-algo vs. python-algo if omitted.
---

# run-match

Plays a single local match between two algo directories and saves a replay file.

## When to use
- "Run match A vs B", "play my_algo against baseline", "how does X do against Y".
- User wants a quick head-to-head result before tournament-sizing a change.
- As a smoke-test after scaffolding a new algo.

## Steps

1. **Identify the two algos.** If user didn't specify both, ask (don't guess). Typical candidates:
   - `algos/<user_algo>` — user's current work
   - `algos/<baseline>` — previous version for comparison
   - `C1GamesStarterKit-master/python-algo` — vanilla starter (fine baseline)

2. **Verify both directories exist** and contain `run.sh` (Unix) or `run.ps1` (Windows).

3. **Confirm Java is installed:**
   ```bash
   java --version
   ```
   If missing, tell the user to install Java 10+ (`brew install openjdk` on macOS).

4. **Run the match.** From repo root:
   ```bash
   python3 C1GamesStarterKit-master/scripts/run_match.py <algo1_path> <algo2_path>
   ```
   Or use the helper if it exists: `./tools/run.sh <algo1> <algo2>`.

5. **Output interpretation.** The engine prints to stdout:
   - Turn-by-turn resource state
   - Final winner and HP totals
   - Path to the generated `.replay` file

6. **Move the replay to `replays/`** for persistence and name it usefully:
   ```bash
   mv C1GamesStarterKit-master/replays/*.replay replays/<algo1>_vs_<algo2>_<timestamp>.replay
   ```

7. **Report back** with:
   - Winner and final HP
   - Any stderr warnings (timeouts, crashes, errors from `debug_write`)
   - Path to replay so user can upload it to https://terminal.c1games.com/playground to watch

## Flags & variations
- **Run in background** if the match might take >30s (it usually doesn't, but some long-simulation algos do):
  use `run_in_background=true` on the Bash tool and monitor.
- **Run multiple matches** when determinism matters. The engine is deterministic given the same code, but if both algos use `random`, seed differences cause variance. Run 5 matches for a quick win-rate estimate.

## Failure modes
- **`Unable to access jarfile engine.jar`**: the `cwd` is wrong. `run_match.py` handles this; don't invoke `java -jar engine.jar` directly.
- **One algo hangs**: usually a missing `game_state.submit_turn()` or an infinite loop in `on_turn`. The engine will eventually time out the hanger and declare the other player the winner.
- **Algo crashes on turn N**: match ends; the crashing side loses. Read the stderr trace to find the exception.

## Don't
- Don't commit `.replay` files unless the user explicitly wants them archived — they're large and binary-ish.
- Don't run matches from inside `C1GamesStarterKit-master/scripts/` — `run_match.py` auto-resolves to parent directory.
