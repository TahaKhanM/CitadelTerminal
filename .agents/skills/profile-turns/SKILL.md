---
name: profile-turns
description: Profile a Citadel Terminal algo's per-turn compute time from a replay file. Use when the user suspects timeouts, asks "is my algo too slow", "how close am I to 15 seconds", or after shipping any change that might have slowed `on_turn` (heavy pathfinding, full-board loops, or on_action_frame logic).
---

# profile-turns

Reads a `.replay` file and reports max/mean/median compute time per turn for each player, plus a headroom calculation against the 15-second budget.

Server timeout rules: at 15 000 ms the algo starts taking **1 damage per second** until it submits. A 5 s+ turn is a warning; a 10 s+ turn is a flag — almost certainly you'll time out occasionally on the server (which is slightly slower than local).

## When to use
- After any significant change to `on_turn` — especially anything touching `find_path_to_edge`, which is O(arena²) per call.
- Suspected timeouts (algo losing games that it should have won, especially in late turns).
- Pre-upload regression check: profile the latest replay to confirm you're well under budget.

## Steps

1. **Pick a recent replay** — ideally one where the algo in question was in a long game (turn 50+). Longer games stress the compute budget more.

2. **Run:**
   ```bash
   python3 tools/profile_turns.py <replay_file>
   python3 tools/profile_turns.py <replay_file> --side p1    # just P1 if desired
   ```

3. **Interpret the output**:
   - `Max` > 10 000 ms → **urgent** — will time out on server.
   - `Max` > 5 000 ms → **concerning** — optimize or verify safety on slow turns.
   - `Mean` > 2 000 ms → latent risk; a spike during a complex turn could push you over.
   - `Max` < 1 000 ms → comfortable.

4. **If there's a problem, identify the spike turn** from the report, then:
   - Open the algo's code and inspect `on_turn` logic.
   - Common culprits: repeated `find_path_to_edge` calls (cache them), full-map iteration inside `on_action_frame` (that's called hundreds of times per action phase), stacked `attempt_spawn` with num=1000 in a loop.
   - Fix, re-run `/run-match`, re-profile.

## Typical optimizations

- **Cache paths per turn**: call `game_state.find_path_to_edge(spawn_tile)` once per candidate spawn tile, store in a dict, reuse.
- **Limit damage simulation**: instead of simulating 20 hypothetical spawn locations, pre-filter to the 4 most promising edge tiles.
- **Move heavy logic out of `on_action_frame`**: that function runs hundreds of times per turn. Keep it to bookkeeping only.
- **Avoid `copy.deepcopy(game_state.game_map)`** if you can — it's expensive. Use a light-weight proxy or just track "what would change" analytically.

## Don't
- Don't obsess over 500 ms turns — the 15 s budget is generous.
- Don't interpret a single-turn spike as "my algo is broken" — look at the distribution. One 8-second spike on turn 0 while loading config is fine; a 4-second spike on every turn is not.
