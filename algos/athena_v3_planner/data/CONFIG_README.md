# Citadel config snapshot

`citadel_config_snapshot.json` is the live Citadel competition game config.

## Provenance

- **Source**: dumped from the running engine via `_config_inspector` algo
  (skill: `/inspect-config`).
- **Captured**: 2026-04-24 (Athena v3 Phase 0 — task 2).
- **Method**: `./tools/run.sh _config_inspector _config_inspector`. The
  `run.sh` wrapper applies `configs/competition-game-configs.json` before
  launching `engine.jar`, so the dumped values are the actual values our
  local matches run against — and these match what the live competition
  server delivers (verified against downloaded ranked replays during the
  earlier SimCore parity work).
- **Raw extraction**: full stderr captured to `/tmp/inspect_full.log`,
  the `RAW CONFIG JSON:` block (lines 63–259, prefixed
  `SAPlayer 1 _config_inspector: `) was stripped of the prefix and the
  resulting JSON validated with `json.loads`.

## Authority — DO NOT use other configs

This file is the single source of truth for unit numerics inside Athena.

- **DO NOT** read values from `C1GamesStarterKit-master/game-configs.json`.
  That file is the base-game (pre-Citadel) shipped config and is known
  outdated (e.g. EF carries the old Encryptor zero-shield-range numbers
  before the rule patch).
- **DO NOT** hardcode numeric stats anywhere in the algo. Either:
  1. Read from `config["unitInformation"]` inside `on_game_start` — the
     server hands the algo its authoritative copy at game start, and that
     is preferred at runtime; or
  2. If working in offline tooling (sim, replay analysis, planner pre-
     compute), load this snapshot file with `json.load(...)`.

## Quick reference (verified against snapshot)

These are the numbers other Athena modules can rely on — they all
trace back to `citadel_config_snapshot.json`:

| Field | Value | Notes |
|---|---|---|
| `resources.bitDecayPerRound` | 0.25 | MP decay per turn |
| `resources.bitsPerRound` | 1.0 | Base MP income (then ramps via `turnIntervalForBitSchedule=5`, `bitGrowthRate=1.0`) |
| `resources.coresPerRound` | 4.0 | SP per turn (flat) |
| `resources.startingHP` | 40.0 | Starting health |
| `resources.startingBits` | 1.0 | Starting MP |
| `resources.startingCores` | 8.0 | Starting SP |
| `resources.maxBits` | 150.0 | MP cap |
| `metalForBreach` (per PI/EI/SI) | 1.0 | +1 SP per mobile-unit breach |
| FF (Wall / "Filter") base HP / cost | 60 / 1 SP | upgrade: 200 HP, +2 SP |
| EF (Support / "Encryptor") base HP / cost | 1 / 4 SP | upgrade: 40 HP, +2 SP |
| EF base shieldPerUnit / range | 3.0 / 3.5 | flat shield, no Y-bonus |
| EF upgrade shieldPerUnit / range | 1.0 / 7.0 | + `shieldBonusPerY=0.7` |
| EF upgraded shield @ Y=13 | 1.0 + 0.7 × 13 = 10.1 | per Support per Scout |
| DF (Turret / "Destructor") base HP / dmg / range | 60 / 6 / 2.5 | upgrade: 100 / 20 / 3.5, +4 SP |
| PI (Scout / "Ping") HP / dmg / range / speed / cost MP | 15 / 2 / 3.5 / 1.0 / 1 MP | Self-destruct: 15 dmg, range 1.5, after 5 steps |
| EI (Demolisher / "EMP") HP / dmg / range / speed / cost MP | 5 / 8 / 4.5 / 0.5 / 3 MP | Long-range, slow |
| SI (Interceptor / "Scrambler") HP / dmg / range / speed / cost MP | 40 / 20 / 4.5 / 0.25 / 1 MP | Defensive mob, attacks walkers only |

The shorthand mapping FF/EF/DF/PI/EI/SI is the engine's pre-rename code
(see memory note: `citadel_runtime_shorthands.md`). The doc-level rename
(WALL/SUPPORT/TURRET/SCOUT/DEMOLISHER/INTERCEPTOR) only exists in
`docs/UNITS_REFERENCE.md` — runtime config strings are still the old
two-letter codes.

## Re-running the snapshot

If the competition rules change, regenerate via:

```bash
./tools/run.sh _config_inspector _config_inspector > /tmp/inspect_full.log 2>&1
# extract RAW CONFIG JSON block, strip the SAPlayer prefix, validate, save
```

Or use the skill: `/inspect-config`.

## Phase 5 augmentation — SimCore (sim_rs) keys

Phase 5 (sim adapter close-out) added two SimCore-required keys alongside
the existing ones:

| SimCore key (added) | Mirror of (existing) |
|---|---|
| `_raw_unit_information` | `unitInformation` |
| `_resources_block_verbatim` | `resources` |

Both schemas now coexist in the same JSON file:

- `unitInformation` + `resources` — used by `gamelib` (`game_state.py`,
  `unit.py`, `game_map.py`) and Athena's defense / opponent / planner
  modules. This is what `config["unitInformation"][i]["shorthand"]`
  returns at runtime.
- `_raw_unit_information` + `_resources_block_verbatim` — used by
  SimCore (`algos/athena/sim/config.py` and `algos/athena/sim_rs/src/config.rs`).
  Required for the sim_rs PyO3 binding `simulate_action_phase_py(state_dict, config_path)`
  to load.

Both blocks are **byte-identical mirrors** at write time. If you regenerate
the snapshot via `/inspect-config`, the regen script must populate both
mirrors. The augmentation script lives inline in
`algos/athena_v3_planner/offense/state_adapter.py` (`augment_snapshot_for_simcore`)
and is run automatically when the algo starts up if the snapshot lacks
the SimCore keys (idempotent — no-op when keys already present).
