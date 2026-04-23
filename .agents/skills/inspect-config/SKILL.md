---
name: inspect-config
description: Run the `_config_inspector` diagnostic algo to dump the live server-delivered game config to stderr, revealing the ACTUAL current values for every unit, upgrade, shield bonus, and resource. Use when the user asks "what's the real X", "what does the server actually say for Y", "resolve the Support shield ambiguity", or when `docs/UNITS_REFERENCE.md` flags a value as needing verification.
---

# inspect-config

Some values in this competition are ambiguous across doc sources (most notably the upgraded Support's `shieldPerUnit` and `shieldBonusPerY`). This skill runs a stub algo whose sole job is to print the server's live config to stderr, then compare it against the docs.

## When to use
- Resolving an ambiguity in `docs/UNITS_REFERENCE.md` (flagged with ⚠).
- After a competition rule patch to see if values changed.
- Before writing strategy code that depends on a specific number.
- Answering questions like "does upgraded Support actually scale with Y?"

## ⚠ Important: local config drift is already patched

The base-game `C1GamesStarterKit-master/game-configs.json` drifts from competition values (most notably EF as the old Encryptor with `shieldRange: 0`). This has been reconciled — see [`configs/competition-game-configs.json`](../../../configs/competition-game-configs.json) and [`tools/apply_competition_config.sh`](../../../tools/apply_competition_config.sh). All the match-running tools (`run.sh`, `bestof.py`, `tournament.py`, `eval.sh`) apply this patch before launching the engine, so local matches already run with competition values.

This skill's role is now **parity verification**, not drift discovery:
- Confirm the patched local config matches the latest competition replay's header.
- Detect when a competition rule change invalidates the tracked patch.
- Re-verify any value the docs flag as ambiguous.

To verify against a **real competition** match, feed the inspector a downloaded replay:
```bash
./tools/test.sh _config_inspector path/to/competition.replay
```
The `test_algo` binary hands the replay's stored config to `on_game_start`, letting you compare competition values against the patched local config.

## Steps

1. **Run the inspector against itself locally** (fastest; produces a replay and stderr):
   ```bash
   ./tools/run.sh _config_inspector _config_inspector
   ```
   `run.sh` applies `configs/competition-game-configs.json` before launch, so the dumped values are the patched-competition values. If you want to see the raw base-game config, invoke `run_match.py` directly without the wrapper.

2. **Retrieve the stderr output**. The `run.sh` wrapper calls `run_match.py` which streams stderr to the console. Key blocks to look for:
   - `RESOURCES:` — the `resources` config block (decay rates, income, HP)
   - `UNITS (pruned to fields that matter):` — per-unit stat dump with `base:` and `upgrade:` blocks
   - `Base Support stats:` and `Upgraded Support stats:` — the derived `GameUnit` fields
   - `Derived upgraded-Support shield at Y=13:` — the authoritative answer to the main ambiguity

3. **Compare against `docs/UNITS_REFERENCE.md`**. Flag any discrepancies the user should know about.

4. **Update docs if needed**. If the server config differs from what the docs claim, either:
   - Fix the doc (if the doc was wrong), OR
   - Note the discrepancy in the doc and treat the server as authoritative.

## Expected output (example)

```
CONFIG INSPECTOR — live server config
RESOURCES:
  { "bitDecayPerRound": 0.25, "coresPerRound": 4.0, ... }

UNITS:
[1] EF (encryptor)
  base:    {"shorthand":"EF","cost1":4.0,"startHealth":1.0,"shieldRange":3.5,"shieldPerUnit":3,...}
  upgrade: {"shieldRange":7,"shieldPerUnit":1,"shieldBonusPerY":0.7,...}

Base Support stats: shieldPerUnit=3.0, shieldBonusPerY=0, shieldRange=3.5
Upgraded Support stats: shieldPerUnit=1.0, shieldBonusPerY=0.7, shieldRange=7.0
Derived upgraded-Support shield at Y=13: 1.0 + 0.7 * 13 = 10.1
```

From this, you'd confirm the docs' "1 + 0.7 × Y" interpretation is correct.

## Don't
- Don't use the inspector algo for ranked matches — it's a diagnostic that sits out the game.
- Don't edit the inspector to add strategy logic — keep it minimal so the diagnostic output stays clean.
