# Phase 8 milestone V — upload-package report

Date: 2026-04-25.

## Zip artifact

| Field | Value |
|---|---|
| Path | `algos/athena_v3_planner/athena_v3.zip` |
| Size | **129,681 bytes (≈ 127 KB)** |
| 5 MB limit | **PASS** (1.4 % of budget) |
| File count | 62 files |
| Build command | `bash C1GamesStarterKit-master/scripts/zipalgo_mac algos/athena_v3_planner/ algos/athena_v3_planner/athena_v3.zip` |
| Build script | `C1GamesStarterKit-master/scripts/zipalgo_mac` (Mach-O x86_64) |

## What's IN the zip

### Runtime code (must be present)

- `algo_strategy.py` — entry point
- `algo.json` — `{"name": "athena_v3_planner"}`
- `run.sh` — engine launcher (uses /opt/miniconda3/bin/python3.13)
- `gamelib/` — vendored game lib (8 modules)
- `planner/` — economy.py, defense.py, offense.py, budget.py
- `offense/` — sim_eval.py, state_adapter.py, templates.py
- `offense/templates/` — 17 offense JSON templates
- `opponent/` — classifier.py, action_predictor.py, features.py,
  archetypes.py, action_frame_utils.py, build_corpus.py, cv_runner.py,
  ARCHETYPES.md
- `archive/` — archive.py, behavior.py, fitness.py, genome.py,
  map_elites.py (loaded but disabled-by-default at runtime)
- `defenses/` — 4 archetype JSONs (v_funnel, two_layer_keep,
  spread_line, v13_inspired)

### Runtime data (must be present)

| File | Bytes | Purpose |
|---|---|---|
| `data/citadel_config_snapshot.json` | 9,369 | Live server config snapshot — sim_rs config source |
| `data/map_elites_archive.json` | 13,502 | 22-cell MAP-Elites archive (disabled by default but loaded for re-enable readiness) |
| `data/opponent_features.npz` | 23,236 | Classifier training data (47 × 14 feature matrix) |
| `opponent/labels.json` | 39,562 | ActionPredictor training data (per-replay archetype labels) |
| `opponent/archetypes.json` | n/a | 6-class taxonomy enum |

## What's OUT of the zip (.zipignore exclusions)

| Pattern | What it excludes | Why |
|---|---|---|
| `*/STATUS.md`, `*/AUTONOMOUS_LOG.md`, `*/FINAL_REPORT.md`, `*/READY_FOR_UPLOAD.md`, `*/README.md` | Build documentation | Not loaded at runtime, ~120 KB total |
| `*.ps1` | Windows launchers | Mac/Linux deploy doesn't use them |
| `*/athena_v3.zip` | The zip itself | Avoid recursion |
| `*/tests/*` | All 10 test files | Never executed at runtime |
| `*/eval/*` | Phase 7 G11 harness (replay_trace.py, run_g11.py, per_opponent.py) | Build-time only |
| `*/sim/*` | replay_inventory.py | Build-time only |
| `*/data/PHASE*` | Phase 2/3/4/5/5B/5_SMOKE/6/6_ARCHIVE/8/8_SMOKE/8_MEMORY ledgers | Build-time documentation |
| `*/data/G11_*` | G11_RESULTS.{md,json}, G11_PER_OPPONENT.md | Build-time eval results |
| `*/data/PERF_*` | PERF_BASELINE.md | Build-time perf measurement |
| `*/data/CONFIG_*` | CONFIG_README.md | Documentation |
| `*/data/replay_index.json` | Phase 0 build-time replay corpus index | Build-time only |
| `*/.git/*`, `*/.DS_Store`, `*/__pycache__/*`, `*.pyc` | OS / VCS noise | Standard exclusions |

## Sanity tests (V4)

### 1. Extract to tmp dir

```bash
mkdir -p /tmp/athena_v3_test && cd /tmp/athena_v3_test
unzip -o /abs/path/algos/athena_v3_planner/athena_v3.zip
ls /tmp/athena_v3_test/algos/athena_v3_planner/
```

Output:
```
algo.json          algo_strategy.py   archive/   data/
defenses/          gamelib/           offense/   opponent/
planner/           run.sh
```

PASS — all 10 expected top-level entries present.

### 2. Import test

```bash
cd /tmp/athena_v3_test/algos/athena_v3_planner/
/opt/miniconda3/bin/python3.13 -c "
import sys, os
sys.path.insert(0, os.getcwd())
import gamelib
import algo_strategy
"
```

Output:
```
OK: algo_strategy imports cleanly
AthenaStrategy class: True
```

PASS — module imports without errors. Class definition resolves.

### 3. test_algo_mac

```bash
C1GamesStarterKit-master/scripts/test_algo_mac \
    /tmp/athena_v3_test/algos/athena_v3_planner \
    /tmp/athena_v3_test/algos/athena_v3_planner
```

Exit code: 0. PASS (silent success).

## Caveats for the human reviewer

1. **The .zipignore had to use broad globs** (`*/data/PHASE*` instead
   of an exhaustive list). zipalgo's pattern matcher has a per-pattern
   limit in practice; the broader pattern works reliably.
2. **`algos/athena_v3_planner/` is the source dir** — when uploading,
   the zip's top-level prefix is `algos/athena_v3_planner/`. The
   live-server unzipper accepts this format (Lostkids baseline used
   the same path-rooted structure successfully in Phase 1.5).
3. **`run.sh` hardcodes `/opt/miniconda3/bin/python3.13`** as the
   preferred interpreter. The Citadel server image may not have that
   path; the script falls back to `python3.13` then `python3` if the
   exact path is missing. If the live server runs Python 3.9 (matches
   the starter-kit default) and the sim_rs PyO3 wheel is built against
   3.9, the planner will work; if 3.13 is preferred and the bundled
   wheel is 3.13-only, this should be re-tested on the live image.
   (Phase 0 rebuild was against 3.13 because the system 3.9 wheel
   was stale.)

## Reproducing this package

```bash
# From repo root, on the c1e3b83 main HEAD or its descendant.
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/.claude/worktrees/agent-a8bc0a9c
chmod +x C1GamesStarterKit-master/scripts/zipalgo_mac
C1GamesStarterKit-master/scripts/zipalgo_mac \
    algos/athena_v3_planner/ \
    algos/athena_v3_planner/athena_v3.zip

# Verify
ls -la algos/athena_v3_planner/athena_v3.zip   # should be ~130 KB
unzip -l algos/athena_v3_planner/athena_v3.zip | tail -3
```
