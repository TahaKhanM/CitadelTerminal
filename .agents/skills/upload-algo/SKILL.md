---
name: upload-algo
description: Zip a Citadel Terminal algo folder using zipalgo_<os> in preparation for uploading to terminal.c1games.com. Use when the user finishes an iteration and says "zip this for upload", "package my algo", "prepare for submission".
---

# upload-algo

Packages an algo folder into a deflated zip, respecting `.zipignore`. Produces a file ready to upload via the terminal.c1games.com web UI (this skill does NOT upload — that's a manual web step).

## When to use
- User says "upload", "zip this", "prepare for submission", "package my algo".
- Algo has been tested locally and is ready for ranked play.

## Pre-flight checks (do these first!)

1. **Run `test-algo` skill** against the default replay. Bail out if it crashes — do not zip broken code.

2. **Run `run-match` against the starter algo** as a minimum-viable regression. If the algo loses 0-40 to the starter, warn the user before zipping.

3. **Verify no forbidden file/folder names**:
   - No spaces in the algo folder name or internal filenames.
   - No special characters (`()[]&@!` etc.) — the competition compiler rejects them.

4. **Confirm `.zipignore`** exists and is reasonable. Default content:
   ```
   __pycache__/
   *.pyc
   .DS_Store
   tests/
   documentation/
   ```

## Steps

1. **Select the zipalgo binary** for the host OS:
   - macOS → `C1GamesStarterKit-master/scripts/zipalgo_mac`
   - Linux → `zipalgo_linux`
   - Windows → `zipalgo_win.exe`

2. **Ensure it's executable** (Unix):
   ```bash
   chmod +x C1GamesStarterKit-master/scripts/zipalgo_mac
   ```

3. **Build the zip**:
   ```bash
   ./C1GamesStarterKit-master/scripts/zipalgo_mac algos/<name> algos/<name>.zip
   ```

4. **Sanity check the zip**:
   ```bash
   unzip -l algos/<name>.zip | head -30
   ```
   Expected:
   - `algo_strategy.py` at root
   - `run.sh` at root
   - `gamelib/` with its Python files
   - NO `__pycache__/`, NO `.replay` files, NO documentation

5. **Size check**: typical algos zip to 30-80 KB. >500 KB is suspicious (probably included replays or cache).

6. **Report** the zip path and file size. Remind the user to upload via:
   - https://terminal.c1games.com/myalgos → "Upload an Algo"
   - After upload, click the algo on that page to refresh compile status.
   - Once compiled, it auto-queues ranked matches; games appear under "My Global Replays".

## Upload flow (manual, for reference)

The zip is uploaded via browser. Steps:

1. Go to https://terminal.c1games.com/myalgos
2. Click "Upload an Algo"
3. Select the zip file from `algos/`
4. Wait for compile (usually <1 minute)
5. If "failed to compile", click the algo tile — status refreshes only on click. If it still says failed, re-examine the zip (likely a file naming issue or missing `run.sh`).

## Don't
- Don't include the whole repo in the zip — only the single algo folder.
- Don't upload an algo with `debug_write` spamming per-frame — the compute budget is tight and stderr I/O isn't free. Throttle debug output before upload.
- Don't modify `run.sh` inside the algo folder unless you genuinely need to change how Python is invoked.
