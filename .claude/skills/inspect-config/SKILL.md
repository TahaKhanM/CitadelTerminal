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

## ⚠ Important: where the config comes from

When you run the inspector **locally**, it reads whatever config the local engine loads — which is [`C1GamesStarterKit-master/game-configs.json`](../../../C1GamesStarterKit-master/game-configs.json). That file is the **base game** snapshot and does NOT reflect the special-competition values. Running `_config_inspector` locally will show you:
- Wall upgrade HP 150 (base), NOT 200 (competition)
- Support base HP 30 (base), NOT 1 (competition)
- Upgraded Support `shieldPerUnit: 4, shieldBonusPerY: 0` (base), potentially different in competition
- Starting HP 30 / Cores 40 / Bits 5 (base), NOT HP 40 / SP 8 / MP 1 (competition)
- And so on.

To see the **competition** config you need one of:

1. **Upload the inspector to the competition server**, let it play at least one ranked match, then download the replay. The replay header contains the competition-delivered config.
2. **Feed it a replay from a competition match** via `test_algo`:
   ```bash
   ./tools/test.sh _config_inspector path/to/competition.replay
   ```
   The `test_algo` binary replays stored states and hands the stored config to your algo's `on_game_start`, letting you read the real competition values.

## Steps

1. **Run the inspector against itself locally** (fastest; produces a replay and stderr):
   ```bash
   ./tools/run.sh _config_inspector _config_inspector
   ```
   Useful as a sanity check — but remember, this shows BASE-GAME values unless you've swapped `game-configs.json` for competition values.

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
  upgrade: {"shieldRange":7,"shieldPerUnit":1,"shieldBonusPerY":0.3,...}

Base Support stats: shieldPerUnit=3.0, shieldBonusPerY=0, shieldRange=3.5
Upgraded Support stats: shieldPerUnit=1.0, shieldBonusPerY=0.3, shieldRange=7.0
Derived upgraded-Support shield at Y=13: 1.0 + 0.3 * 13 = 4.9
```

From this, you'd confirm the docs' "1 + 0.3 × Y" interpretation is correct.

## Don't
- Don't use the inspector algo for ranked matches — it's a diagnostic that sits out the game.
- Don't edit the inspector to add strategy logic — keep it minimal so the diagnostic output stays clean.
