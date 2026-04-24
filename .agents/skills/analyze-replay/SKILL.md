---
name: analyze-replay
description: Parse a Citadel Terminal .replay file and summarize what happened - turn count, winner, key scoring events, damage exchanges, algo crashes. Use when the user wants insight into why an algo won or lost a specific match without opening the browser viewer.
---

# analyze-replay

Takes a `.replay` file path and produces a turn-by-turn summary of the match. Useful for debugging strategy without needing the Playground viewer.

## When to use
- User asks "what happened in this replay", "why did my algo lose", "summarize this match".
- Diagnosing an algo crash that only showed up in a specific game.
- Extracting training signal for ML-based algos.

## Replay format

A `.replay` file is a JSON Lines file — one JSON object per line, each representing an action frame or turn state. Fields of interest:

- Lines starting with `{"turnInfo": [0, ...` — Deploy phase state (start of each turn)
- Lines starting with `{"turnInfo": [1, ...` — Action phase frames (many per turn)
- Lines starting with `{"turnInfo": [2, ...` — End game state
- Each frame has `p1Stats` `[HP, SP, MP, time_ms]`, `p2Stats`, `p1Units`, `p2Units`, `events`
- `events` has keys: `spawn`, `death`, `attack`, `breach`, `damage`, `shield`, `move`, `selfDestruct`, `melee`

Note: in raw replay JSON, player IDs are 1 and 2 (not 0 and 1 like in the Python `GameState` wrapper).

## Steps

1. **Confirm the file path.** Default to the most recent file under `replays/` or `C1GamesStarterKit-master/replays/` if the user didn't name one.

2. **Use a Python snippet** to parse (don't try to eyeball the JSON):

```python
import json
from collections import Counter, defaultdict

with open(replay_path) as f:
    lines = [json.loads(l) for l in f if l.strip()]

frames = [l for l in lines if "turnInfo" in l]
turns = defaultdict(list)
for f in frames:
    turn_no = f["turnInfo"][1]
    turns[turn_no].append(f)

# Final state
end = frames[-1]
print(f"Final HP: P1={end['p1Stats'][0]} P2={end['p2Stats'][0]}, "
      f"ended turn {end['turnInfo'][1]}")

# Score timeline
breach_events = [(f["turnInfo"][1], ev) for f in frames for ev in f.get("events", {}).get("breach", [])]
for turn, ev in breach_events:
    loc, _, _, _, owner = ev
    scorer = "P1" if owner == 1 else "P2"
    print(f"  T{turn}: {scorer} breached at {loc}")

# Per-turn damage dealt
dmg = defaultdict(lambda: [0, 0])  # turn -> [p1_dmg_dealt, p2_dmg_dealt]
for f in frames:
    turn = f["turnInfo"][1]
    for ev in f.get("events", {}).get("breach", []):
        if ev[4] == 1: dmg[turn][0] += 1
        else:          dmg[turn][1] += 1

# Unit attrition
deaths = Counter()
for f in frames:
    for d in f.get("events", {}).get("death", []):
        loc, unit_type, _, _, player = d
        deaths[(player, unit_type)] += 1
print("Deaths by (player, unit_type):", deaths)
```

3. **Summarize** for the user:
   - Winner, final HP, turn count.
   - Per-turn scoring timeline (who breached, where, when).
   - Attack vs defense attrition (how many of each unit died per side).
   - Any evidence of algo crashes (game ended early, one side stopped acting).
   - Compute time anomalies (p1Stats[3] > 15000 = timeout imminent).

4. **Highlight strategy signals**:
   - Which edge did the losing player get scored on most? → defense gap.
   - Did the winner's attacks follow a pattern (e.g., always same spawn tile)?
   - Did either side waste Supports (many Support deaths with low corresponding friendly shielded units)?
   - What was the MP curve — did someone hoard and lose to decay?

5. **Report** in plain English, 3-6 bullets max. Keep it digestible.

## Output example

> **Match: `my_algo_v2_vs_baseline_2026-04-22T14:33.replay`**
> - Winner: my_algo_v2 (HP 23 vs 0), game ended turn 47.
> - Scoring: 17 breaches for my_algo (all from [14,0] or [13,0], mostly Scouts).
> - Baseline never successfully scored; Interceptors got stuck in the corner funnel.
> - my_algo lost 8 Turrets to Demolisher waves but was replacing them with upgraded Turrets from turn 25 onward.
> - Compute times peaked at 1.8s/turn (safe, well under 15s budget).

## Related tools
- `watch_replay.py` under `C1GamesStarterKit-master/scripts/contributions/` — unsupported community text-dump viewer. Complements this skill for frame-level granularity.
- Upload the `.replay` to https://terminal.c1games.com/playground for the official visual viewer.
