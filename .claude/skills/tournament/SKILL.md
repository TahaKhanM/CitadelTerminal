---
name: tournament
description: Run a round-robin local tournament between multiple Citadel Terminal algos and report win rates. Use when the user has 3+ algo variants and wants to know which is strongest before uploading. Plays every pair twice (once on each side) to control for spawn-side asymmetries.
---

# tournament

Runs N algos in round-robin pairs, outputs a win-rate table.

## When to use
- User has multiple variants (v1, v2, v3) and asks "which is best?".
- Validating that a new change actually improves performance against its predecessors.
- Before uploading — one last filtering step.

## Steps

1. **Collect the algo list.** Either:
   - User provides explicit paths: `algos/v1 algos/v2 algos/v3`.
   - Or scan `algos/` for subdirectories and confirm the list with the user.

2. **Plan matches.** For N algos, total matches = N×(N−1). Every ordered pair plays once (since top/bottom asymmetry exists). With 4 algos that's 12 matches; each match runs in ~5-30 seconds. Warn if N×(N−1) × est_match_time exceeds 10 minutes — the user may want to scope down.

3. **Run matches** in a loop. Use the helper `tools/tournament.sh` if present, otherwise:

```bash
mkdir -p replays/tournament_$(date +%Y%m%d_%H%M%S)
TOURNEY_DIR=replays/tournament_$(date +%Y%m%d_%H%M%S)

for a1 in algos/v1 algos/v2 algos/v3 algos/v4; do
  for a2 in algos/v1 algos/v2 algos/v3 algos/v4; do
    [ "$a1" = "$a2" ] && continue
    name=$(basename $a1)_vs_$(basename $a2)
    python3 C1GamesStarterKit-master/scripts/run_match.py $a1 $a2 2>&1 | tee $TOURNEY_DIR/$name.log
    mv C1GamesStarterKit-master/replays/*.replay $TOURNEY_DIR/$name.replay 2>/dev/null
  done
done
```

4. **Parse the match logs** for winners. The `engine.jar` stdout includes a line like `"Player 1 wins!"` at end-of-match. You can also parse the replay's last frame's `p1Stats[0]` vs `p2Stats[0]`.

5. **Build the win matrix** and summary:

```
                  v1    v2    v3    v4    Wins
   v1 (bot)        -    W     W     L     2/3
   v1 (top)        -    L     W     L     1/3
   v2 (bot)        L     -    W     L     1/3
   ...
   -----------
   v1: 3/6 (50%)
   v2: 4/6 (67%)
   v3: 5/6 (83%)  ← strongest
   v4: 0/6 (0%)
```

6. **Report**:
   - Ranked list by win rate.
   - Statistical caveat: with only 1 game per pair, variance is high — encourage 5x repetition for any decision that actually matters.
   - Which algo(s) have asymmetric results (win on top but lose on bottom, or vice versa) — signals side-specific bugs.
   - Highlight any algo that crashed or timed out ("v2 crashed in 3 of 6 games" is a bigger signal than win rate).

## Recommended structure for larger tournaments

Put this in `tools/tournament.py` (Python is cleaner than shell for parsing replays):

```python
import subprocess, json, os, sys
from pathlib import Path
from itertools import permutations

def run_match(a1, a2, out_dir):
    result = subprocess.run(
        ["python3", "C1GamesStarterKit-master/scripts/run_match.py", a1, a2],
        capture_output=True, text=True, timeout=120,
    )
    # parse winner from stdout or from the generated .replay
    replay = sorted(Path("C1GamesStarterKit-master/replays").glob("*.replay"),
                    key=os.path.getmtime)[-1]
    with open(replay) as f:
        last_line = [l for l in f if l.strip()][-1]
    final = json.loads(last_line)
    p1_hp, p2_hp = final["p1Stats"][0], final["p2Stats"][0]
    winner = 1 if p1_hp > p2_hp else 2
    # move replay
    dest = Path(out_dir) / f"{Path(a1).name}_vs_{Path(a2).name}.replay"
    replay.rename(dest)
    return winner, p1_hp, p2_hp

algos = sys.argv[1:]
out = Path("replays") / f"tournament_{__import__('time').strftime('%Y%m%d_%H%M%S')}"
out.mkdir(parents=True)
wins = {a: 0 for a in algos}
for a1, a2 in permutations(algos, 2):
    winner, p1hp, p2hp = run_match(a1, a2, out)
    wins[a1 if winner == 1 else a2] += 1
    print(f"{a1} vs {a2}: winner={winner} ({p1hp} vs {p2hp})")

total = (len(algos) - 1) * 2
for a, w in sorted(wins.items(), key=lambda kv: -kv[1]):
    print(f"{a}: {w}/{total}")
```

## Don't
- Don't run more than ~5 matches per pair in a single invocation without the user asking — it's slow and hogs CPU.
- Don't skip the "both sides" runs — spawn-side matters a lot in Terminal (your corner-turret placement favors one mirror).
