---
name: new-algo
description: Scaffold a new Citadel Terminal algo folder under algos/ by copying the starter template. Use when the user wants to create a fresh algo, a variant of an existing algo, or a clean baseline to experiment against. Accepts an optional algo name argument.
---

# new-algo

Scaffolds a new algo folder under `algos/` in this repo, cloned from the starter template (`C1GamesStarterKit-master/python-algo/`).

## When to use
- User says: "new algo", "create an algo called X", "clone the starter algo", "make a variant of X".
- User is about to start iterating on a bot and needs a clean workspace.

## Steps

1. **Confirm the algo name.** If not provided in the user's request, ask. Use snake_case or kebab-case (no spaces / special chars — the competition site rejects uploads with them).
2. **Confirm it doesn't already exist.** Check `algos/<name>/` — if it does, ask the user whether to overwrite, pick a different name, or just edit the existing one.
3. **Choose the source template:**
   - Default: `C1GamesStarterKit-master/python-algo/` (the vanilla starter).
   - If the user says "copy from `<existing_algo>`", use `algos/<existing_algo>/`.
4. **Run the copy:**
   ```bash
   cp -r <source> algos/<name>/
   ```
5. **Drop breadcrumb files** into the new algo folder (only if they don't already exist):
   - `algo.json` — already present in starter template; leave as-is.
   - `.zipignore` — already present; leave as-is.
6. **Update the algo's README or top-of-file docstring** if the user wants to note the variant's purpose (optional, ask).
7. **Smoke test** by running `test-algo` skill against the new folder — this confirms the copy worked and the algo runs end-to-end on a replay.
8. **Report** the full path to `algo_strategy.py`, so the user can start editing immediately.

## Notes
- Don't put algos directly in `C1GamesStarterKit-master/` — that directory is treated as read-only vendored code. Always use `algos/<name>/`.
- The `run.sh` / `run.ps1` scripts inside the algo folder do NOT need modification — they reference `algo_strategy.py` relatively.
- If the user wants a minimal algo (empty `on_turn`), offer to strip the `starter_strategy` method out after copying.
