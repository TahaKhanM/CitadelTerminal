# Finalist Reference Repos

Source code from public Citadel/base-game Terminal finalist algos, vendored here as
reference material for the Athena build (see `docs/ATHENA_BUILD_PLAN.md`).

## What's here

| Folder | Source | Result |
|---|---|---|
| `Terminal-Lostkids/` | https://github.com/nknguyenhc/Terminal-Lostkids | APAC 3rd place |
| `Terminal-C1-Midwest-2022/` | https://github.com/langsonzhang/Terminal-C1-Midwest-2022 | Midwest 5/24 ("FUNNEL" / Murphy's Lawyers) |
| `terminal-c1/` | https://github.com/The-Travelling-Salesmen/terminal-c1 | Harvard #1 ("snorkeldink-v3-1") |
| `terminal_c1_gt/` | https://github.com/brian-cai/terminal_c1_gt | GT Citadel team |

## What was stripped

To keep the repo lean (198MB → 6.1MB), the following were removed from each clone:

- `engine.jar` files (we already have one in `C1GamesStarterKit-master/engine.jar`)
- `scripts/` directories — duplicate `zipalgo_*`, `test_algo_*` binaries
- `documentation/` directories — auto-generated Sphinx HTML
- `*.mov`, `*.replay`, `*.zip`, `*.mp4` binary artifacts
- `__pycache__/` Python bytecode caches
- The original `.git/` directories (so they integrate with our git history as regular files)

## Re-cloning the originals

If you ever need the full original repo (e.g., to inspect a stripped artifact):

```bash
git clone <URL from table above> /tmp/original_<name>
```

## Strategic notes on each

See `~/.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/finalist_corpus.md`
for the canonical pattern catalog (formulas, thresholds, key file:line references).
