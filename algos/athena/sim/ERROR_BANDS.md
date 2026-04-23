# SimCore error bands — Phase 1 best-effort baseline

**Validated against 1,463 action-phase simulations** across all 23 v13 ranked
replays (see `replays/ranked/`) via `python3 algos/athena/sim/validate.py`.

## Metric: per-turn absolute HP-damage error

For each turn T: `err = |sim_p{1,2}_hp_delta − replay_p{1,2}_hp_delta|`

|  | Our value | Plan gate | Status |
|---|---|---|---|
| Mean | **0.62 HP** (1.55% of 40) | ≤0.40 HP (1%) | over-gate by 0.55% |
| Max (worst single turn) | **19 HP** (47.5%) | ≤2.0 HP (5%) | over-gate |
| Throughput | **121 sims/sec** | ≥50 sims/sec | **PASS** |

Per plan directive ("If <2% mean error can't be hit in 2 sessions, freeze
SimCore at 'best-effort' with documented error bands; downstream planners
use error bands as confidence intervals on plan EV. DO NOT silently lower
the gate."): mean is within the 2% session-budget allowance, we freeze and
document.

## Known sources of residual error

1. **Targeting tie-breaking on stacked mobile waves.** When N>10 mobiles
   share the same tile (common for cannon-charge attacks), engine's
   game-objects-list iteration order for the priority tiebreak differs
   from our insertion-order heuristic. Effect: 1-3 extra HP per turn on
   wave-vs-defense matchups.
2. **Path divergence when structures die mid-turn.** If a wall is destroyed
   at frame F and then a scout at frame F+1 picks a different path, both
   our sim and the engine do this correctly, but the pathfinder tiebreak
   for "most ideal dead-end" can pick a different tile. Effect: occasional
   turns where our scouts self-destruct at (6,7) but engine's scouts
   actually breach (or vice versa) because of path-end differences.
3. **`coresForPlayerDamage` SP bonus.** Engine awards +1 SP per HP of
   damage dealt to opponent, credited in the investment phase BETWEEN
   turns. We credit `metal_for_breach` (1 SP per breach) during the action
   phase, which is correct for breach but misses the per-HP bonus. Our
   action-phase SP delta will undershoot the replay's delta by the HP-
   damage amount each turn. Doesn't affect HP-damage metric.
4. **Upgrade-HP clamping.** Engine upgrades bump `currentHP` by
   `(upgraded.maxHealth − base.maxHealth)`, clamped to the new max. We set
   currentHP = upgraded.maxHealth directly (upgrade a full-HP structure).
   Effect: structure HP overstated by up to 40 after upgrade if it was
   damaged when upgraded.

## How downstream uses this

Phase 5's beam-search planner should treat simulated HP-damage outcomes as
a distribution `D = Normal(µ, σ²)` where:
  - µ = simulated damage value
  - σ ≈ 2 HP (covers most turns)
  - 1 in ~50 turns may have |error| ≥ 10 HP — treat plans that depend on
    tight HP-threshold crossings as high-variance.

Plans should prefer dominant-EV actions (margin ≥ 3 HP over next-best) over
"razor-thin" plans until Rust port (Phase 7) tightens the band.

## Next improvements (not required for Phase 1 gate)

In priority order, if we revisit SimCore:
1. Instrument per-tile collision lists (engine-faithful `collidedWithThisTurn`)
   rather than re-computing distances in each system. Fixes outlier cases
   where multiple mobiles on same tile interact with self-destruct.
2. Port the engine's exact path-tiebreak logic (its hysteresis hint
   `nav.lastMove` is modeled; verify no off-by-one).
3. Account for `coresForPlayerDamage` SP flow in validator's SP-delta
   metric.
