# Variant K2 — Path-Blocking via find_path_to_edge

**Status**: SHIPPABLE
**Built from**: `oracle_pure_M1Lite_VD_upload/`
**Date**: 2026-04-27

## What this variant does

Adds a single new defense template family — `defense:path_block_*` — driven by the engine's own `find_path_to_edge` pathfinder. When breach pressure is observed at a tile (>= 1.0 cumulative), the enumerator runs `game_state.find_path_to_edge([breach_x, breach_y])` to derive the path opp's mobiles would walk through oracle's territory, and proposes a defense template that places turrets on the highest-y tiles of that path (closest to opp territory entry).

Mirrors snorkeldink-v3-2's `reactive_defence.build_reactive_defense` mechanism, which deterministically beats VD/M1Lite (verified: VD goes 0/6 vs v3-2 in this study). v3-2 builds reactive turrets along the path — K2 mirrors that, but as one template among ~30 that the search evaluates and selects from.

## Why this is structural, not hardcoded

- **Path comes from `game_state.find_path_to_edge` (engine pathfinder)**, not heuristic BFS or hardcoded tile lists. Per §16 of CONTEXT_HANDOFF.md, BFS-based path validation is over-conservative; using the engine's own pathfinder avoids that trap.
- **Block tiles are derived from the engine path's intersection with oracle's territory**. Oracle's defense tiles are determined turn-by-turn by what tiles the engine pathfinder actually uses — not by hardcoded coordinates.
- **Trigger is observed pressure** (per-tile `breach_pressure[tile] >= 1.0` after natural decay), not configured-in tile lists.

## Implementation

Two files modified vs VD baseline:

### `oracle_core/enumerator.py`

Added at module scope (before `_enumerate_defense_templates`):

```python
def _is_valid_turret_position(xy, game_state) -> bool:
    """Legal turret position: in bounds, in oracle territory, not occupied,
    not on a spawn edge."""
    # ...

def _gen_path_block_templates(game_state, breach_pressure, config):
    """For each high-pressure breach tile, run find_path_to_edge and
    yield (template_name, atoms) pairs along the engine's path."""
    if not breach_pressure:
        return
    top_tiles = sorted(breach_pressure.items(), key=lambda x: -x[1])[:3]
    top_tiles = [(t, p) for t, p in top_tiles if p >= 1.0]
    # ... defensive copies, exception-safe find_path_to_edge call
    # filter to oracle territory (y < 14), sort by descending y
    # pick top-2 valid turret positions, yield template
```

Modified `_enumerate_defense_templates`:
- Added `breach_pressure=None` kwarg
- Added a new section (#12) calling `_gen_path_block_templates` gated by `turn > 8 and breach_pressure`. Templates are budget-gated through the existing `_build_defense_plan`.

Modified `enumerate_plans`:
- Added `breach_pressure=None` kwarg, passed through to `_enumerate_defense_templates`.

### `oracle_core/search.py`

Single-line change: `enumerate_plans(...)` call now passes `breach_pressure=breach_pressure` (already a parameter of the search() function in VD).

## API discoveries about find_path_to_edge

Verified via direct experimentation (not documentation):

1. **Returns a list of `[x, y]` lists** (not tuples), or `None` on error/blocked-start.
2. **Auto-targets opp's edge** when `target_edge=None` (the default). For a start in oracle's territory (y < 14), the inferred target is `TOP_LEFT` or `TOP_RIGHT`. The path therefore goes UP through oracle's territory and into opp territory.
3. **The start tile must NOT be occupied by a stationary unit.** If it is, the call warns and returns None (per `gamelib/game_state.py:478`). K2 handles this by trying nearby unoccupied tiles before giving up.
4. **The start tile is included as the first entry of the returned path.**
5. **Path is 4-connected (orthogonal)**, with diagonal moves expressed as two consecutive cells.
6. **No exceptions in normal use**, but K2 wraps the call in `try/except` defensively.

## Validation

### Tier A — REGRESSION FLOOR (must pass 100%)

| Opp | Side P1 | Side P2 | Result |
|---|---|---|---|
| v13_second_ring | W | W | **2/2** |
| python-algo (starter) | W | W | **2/2** |
| heuristic_v1 | W | W | **2/2** |
| Lostkids/python-2l-aet | W | W | **2/2** |
| funnel_INTER | W | W | **2/2** |

**Tier A: 10/10** — no regressions.

### Tier B — Breakthrough indicator

| Opp | P1 | P2 | Result |
|---|---|---|---|
| snorkeldink-v3-1 | W | W | **2/2** |

**Tier B preserved.**

### Critical targeted test — snorkeldink-v3-2

The matchup that motivated this variant. v3-2 runs the same reactive_defence mechanism K2 mirrors.

| Test | K2 wins | v3-2 wins |
|---|---|---|
| 6 reps × 2 sides | **6** | 0 |
| Goal: ≥1 win | **EXCEEDED 6×** | — |

**For comparison: VD vs snorkeldink-v3-2 = 0/6.** K2 reverses this completely.

Sample game: K2 (P1) won 12-(-2). Search selected `defense:path_block_25_11|offense:scout4@23,9` as the best plan at turn 18 with pressure(25,11)=1.47, and `defense:path_block_24_10|offense:demo1@4,9` at turn 20. The template was both proposed AND selected — the search's value function judged it best given the observed breach pattern.

### H2H

| H2H | K2 | Other | Ties |
|---|---|---|---|
| K2 vs VD (4 games serial) | 0 | 0 | **4** |
| K2 vs J2 (6 games) | 0 | 0 | **6** |

K2 ties VD and J2 across all matches. Both algorithms reach turn 100 with HP -2 vs -2 (engine convention: negative HP after no breach in timeout window). This is expected: K2's path-block templates only fire under observed breach pressure, and in K2-vs-VD/K2-vs-J2 matchups neither side achieves enough breaches to trigger the new templates. K2 plays near-identically to VD/J2 against these targets — no regression, no improvement.

### Telemetry

Single-match measurements vs snorkeldink-v3-2 (P1):
- Wall-clock: avg 1.02s, max 2.09s, 36 turns
- No `WATCHDOG fired` events
- No `on_turn exception` events
- No fallback count
- 2 path_block templates selected as `best=` plan
- Sims/turn: ~2700 (vs VD's similar baseline; viability probe runs once per unique defense template)

vs heuristic_v1: avg 0.89s, max 1.95s — well within budget. Wall-clock is ~3× M1Lite (394ms baseline) but still ~10% of the 11s search budget; the increase comes from VD's viability probe running on the new path_block templates.

## What works

1. **The engine pathfinder gives the right path tiles.** When opp breaches at (25,11), `find_path_to_edge([25,11])` returns a path through oracle's territory whose top tiles (y=13, 12) are exactly where a defender should sit to intercept.
2. **The search picks path_block when it's the right call.** Telemetry shows the search ranking `defense:path_block_*` as the top plan precisely when there's high pressure at a single tile (the v3-2 attack pattern).
3. **No regression on Tier A.** The new templates only fire when (a) `turn > 8` AND (b) `breach_pressure[tile] >= 1.0` — gating prevents early-game noise from polluting the candidate space.

## Risks and limitations

1. **Live ladder ≠ local validation.** §16 of CONTEXT_HANDOFF.md documents how M2 passed local Tier A and regressed live. Same risk applies, but K2's mechanism is fundamentally different (uses engine pathfinder, not BFS; doesn't change the support back row).
2. **Wall-clock 3× baseline.** Still well within the 13s watchdog (max 2.6s observed), but could be a problem if a future opponent triggers many distinct breaches per turn (more path_block templates → more probes). Cap at 3 templates per turn enforced.
3. **find_path_to_edge depends on the current board state.** If opp's structures have shifted between the breach occurring and the next turn (unlikely but possible), the path inferred may not match the path opp's NEXT mobile takes. Acceptable: defenses-along-the-path are still useful even if not perfect.
4. **find_path_to_edge auto-targets opp's edge from a tile in oracle territory** — the path goes from the breach tile INTO opp territory, then we filter to oracle-territory tiles. This is symmetric in the path-finder's logic to "where opp would walk to reach this tile" but not literally identical. The validation 6/6 vs v3-2 confirms it's good enough.

## Falls back to VD when

- `breach_pressure` is None / empty: K2 templates not generated (back-compat).
- All breach pressures < 1.0: noise floor; K2 silent.
- Turn ≤ 8: K2 gated off (avoid early-game stochastic-breach noise).

## Files changed vs VD baseline

- `oracle_core/enumerator.py` — added `_is_valid_turret_position`, `_gen_path_block_templates`; updated `_enumerate_defense_templates` and `enumerate_plans` signatures
- `oracle_core/search.py` — passed `breach_pressure` to `enumerate_plans`

## Confidence

**High** for the targeted goal (beating snorkeldink-v3-2). Proof is decisive: VD 0/6, K2 6/6.

**Medium** for general improvement. Tier A 10/10 + B 2/2 confirm no regression. H2H ties vs VD/J2 confirm K2 plays equivalently when the new templates don't fire (most matches). The new templates fire when the opponent is the v3-2-style sustained-attack archetype — exactly the matchups VD was vulnerable to.

**Recommended deployment**: Upload K2 alongside VD on the live ladder. If K2 regresses on opponents VD was beating, fall back to VD. Acknowledge §16's lesson that local validation is necessary but not sufficient.
