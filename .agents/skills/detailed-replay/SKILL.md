---
name: detailed-replay
description: Deep, section-by-section analysis of a single Citadel Terminal replay — economy timeline, structure placements, upgrades, scoring, attrition, shielding, spawns, damage, compute time. Use this INSTEAD of /analyze-replay when the user wants a thorough breakdown, is examining an official ranked-match download, or wants to zoom into a specific turn.
---

# detailed-replay

Produces turn-by-turn breakdowns of a single replay. Use for deep dives; use `/analyze-replay` for quick summaries and `/batch-replays` for aggregating across many games.

## When to use
- User says "analyse this replay in detail" / "deep-dive on the v11 game" / "what happened turn by turn".
- User downloaded an official replay and wants to extract patterns (strategy, spawn tiles, upgrade timing).
- Investigating a specific anomaly (spike turn, crash, sudden score burst).
- Verifying the live server config — the first line of an official replay contains the full authoritative config.

## Steps

1. **Identify the replay file** (usually under `replays/`).
2. **Run with no section flag** for the full report, or with `--section <name>` for one piece:
   ```bash
   python3 tools/detailed_replay.py <replay>                  # all sections
   python3 tools/detailed_replay.py <replay> --section config # just live config
   python3 tools/detailed_replay.py <replay> --turn 47        # deep-dive one turn
   python3 tools/detailed_replay.py <replay> --json           # machine-readable
   ```
3. **Available sections**: `config`, `economy`, `structures`, `upgrades`, `scoring`, `attrition`, `shielding`, `spawns`, `damage`, `compute`.
4. **Interpret**:
   - **config**: exact live server values for every unit (only present in official replays).
   - **economy**: HP/SP/MP timeline per player per turn — spot resource snowball.
   - **structures**: where and when each Wall/Support/Turret was built.
   - **upgrades**: when each structure got upgraded (inferred from HP exceeding base).
   - **scoring**: breach timeline + top tiles + "big turn" flagging (≥3 breaches in one turn).
   - **attrition**: per-unit-type death counts + hot spots.
   - **shielding**: total shield HP granted — surfaces whether Supports are firing as expected.
   - **spawns**: mobile-unit deploy distribution.
   - **damage**: total damage events.
   - **compute**: per-side mean/max ms; flags timeout risk.

## Reading the output

- **If scoring is heavily concentrated on one tile** (e.g., `(22, 19): 38`) → the algo found a single weakness in the opponent's defense and exploited it repeatedly. Good sign of adaptive targeting.
- **If breaches happen on consecutive turns** (e.g., T47, T49-52) → a sustained wave strategy, likely Support-shielded.
- **If "Support" death count is low but shielding HP is high** → upgraded Supports are doing their job (durable + shielding many units).
- **If compute max ≥ 10000 ms in an official replay** → real risk of server timeouts.

## When NOT to use
- For a quick win/lose check → `/analyze-replay` is faster.
- For patterns across many replays → `/batch-replays`.
- For profiling compute time specifically → `/profile-turns` (it's focused).

## Official vs local replays
Official replays (downloaded from https://terminal.c1games.com) contain:
- The live server config on the first line (authoritative numbers)
- Unit IDs letting you track individuals across frames
- Richer event data

Local replays (from `./tools/run.sh`) use the stale shipped `game-configs.json`, which is the BASE GAME not the competition. When doing real strategy work, always prefer analysing official replays.
