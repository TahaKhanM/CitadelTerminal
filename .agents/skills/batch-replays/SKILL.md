---
name: batch-replays
description: Summarize many Citadel Terminal replays at once and surface patterns (win rates, breach-tile hot spots, enemy spawn distributions, compute-time trends, outlier games). Use when the user has downloaded a batch of ranked games ("analyse my last 20 ranked", "look at all replays in this folder", "which opponent beats me") or wants to aggregate tournament results.
---

# batch-replays

Scans many replays in parallel, rolls up per-replay stats into cross-game patterns, flags outlier games.

## When to use
- User has downloaded 5+ ranked replays and wants to see trends.
- Post-tournament analysis ("which of these opponents are hardest?").
- Diagnosing a recent dip in ranked performance.
- Spotting over-fit strategies (algo wins 10/10 vs starter but loses to varied opponents).

## Steps

1. **Collect replay paths** — a glob, a directory, or an explicit list:
   ```bash
   python3 tools/batch_replays.py replays/ranked_*.replay
   python3 tools/batch_replays.py replays/tournament_20260423_*/
   python3 tools/batch_replays.py replays/*.replay --me final_v11 --top 15
   ```

2. **Flags**:
   - `--me <name>` — identify which side is you (helps interpret "breached against me" vs "we scored").
   - `--top N` — how many hot-spot tiles to list (default 10).
   - `--json` — emit structured output (for further scripting).

3. **Interpret the summary**:
   - **Win rate + win counts**: P1 vs P2 split, ties.
   - **Max / mean compute time across games**: if trending up, something in your algo is getting slower over the match.
   - **Top tiles where enemy breached**: your persistent weak spots — plug these in your next iteration.
   - **Top tiles where YOUR mobiles scored**: confirms your offensive strategy is working as designed.
   - **Top enemy spawn tiles**: opponent's deploy pattern. If they spawn exclusively at (13,27) and (14,27), you can angle your defense.
   - **Outlier games**: `CLOSE` (margin ≤ 3 HP) games are worth deep-diving — those pivot on one decision. `BLOWOUT` games confirm your strategy holds up.

4. **Follow-up**:
   - Pick 1-2 outlier games (close losses especially) and run `/detailed-replay` on each.
   - If enemy always breaches the same tile, reinforce that tile in the next algo iteration.

## Performance

Reads replays in parallel — ~50 replays/second on 10 cores for 3 MB official replays. Fast enough to run over ~hundreds of ranked games without waiting.

## When NOT to use
- Single-replay analysis → `/analyze-replay` or `/detailed-replay`.
- Compute time on one specific replay → `/profile-turns`.
- Match-running (creating new replays) → `/run-match`, `/bestof`, `/tournament`.

## Example: triage 30 ranked games

```bash
# Download 30 replays from the website into replays/ranked/
python3 tools/batch_replays.py replays/ranked/*.replay --me my_algo --top 15

# If output shows (22,19) as a weak spot getting breached often:
#   -> next iteration: plant an upgraded Turret at (22, 12) or similar
# If compute max is 8000 ms:
#   -> profile-turns on the slowest replay, optimize the offending turn
```
