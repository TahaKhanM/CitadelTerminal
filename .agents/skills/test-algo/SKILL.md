---
name: test-algo
description: Quickly syntax-check and runtime-check a Citadel Terminal algo by feeding it a replay via test_algo_<os>. Use after editing an algo when you want to confirm it doesn't crash before running a full match. Fast (~5-10 seconds).
---

# test-algo

Runs an algo against a pre-recorded replay file via the starter kit's `test_algo` binary. The algo receives real turn states and must respond without crashing. Faster than a full match — this is the first thing to run after edits.

## When to use
- User just modified `algo_strategy.py` and wants a sanity check.
- Before committing a change or uploading to the competition site.
- To reproduce a crash seen in a specific server-side replay (download the `.replay`, then test against it).

## Steps

1. **Identify the algo directory.** Default to the most recently modified one under `algos/`.

2. **Pick the platform binary:**
   - macOS → `C1GamesStarterKit-master/scripts/test_algo_mac`
   - Linux → `test_algo_linux`
   - Windows → `test_algo_windows.exe`

3. **Ensure executable bit is set** on Unix:
   ```bash
   chmod +x C1GamesStarterKit-master/scripts/test_algo_mac
   ```

4. **Run it with a replay source:**
   ```bash
   # default replay shipped with the starter kit
   ./C1GamesStarterKit-master/scripts/test_algo_mac algos/<name>/

   # specific replay (usually downloaded from the site after a crash)
   ./C1GamesStarterKit-master/scripts/test_algo_mac algos/<name>/ ~/Downloads/problem.replay
   ```

5. **Interpret output.**
   - Clean run → algo is loadable and doesn't crash on the replay states.
   - Python traceback → bug in your algo; fix before running full matches.
   - Timeout warnings → your algo is taking too long per turn; profile.

6. **Report** the outcome:
   - "Clean run on N turns" → ready to match-test.
   - Exact traceback + file/line if it crashed.

## What it does NOT test
- **Does NOT verify strategic outcome.** The replay states are fixed; your algo's decisions don't affect them.
- **Does NOT catch edge cases your algo never encounters** in the specific replay (e.g., if the replay never triggers your `elif turn > 50` branch).
- **Does NOT benchmark**. For end-to-end testing, use the `run-match` skill instead.

## When this is the wrong tool
- If the user wants "does my algo actually beat the baseline", use `run-match`.
- If the user wants to learn from events in multiple matches, use `tournament`.
- If the user has a suspected bug but no replay, write a unit test in `algos/<name>/gamelib/tests.py` and run `python3 -m unittest discover` inside the algo folder.
