# Variant J3 — Path-Prediction in Evaluation

**Status**: Implemented. Local validation pending (results below).
**Author**: Claude Code session (2026-04-27).
**Base**: `oracle_pure_M1Lite_VD_upload` (Variant D — Hybrid VA+VB+VC).

---

## 1. Problem statement

The defining failure mode in VD's losses (e.g., 35 breaches at (8,5) vs Egil over 51 turns) is **single-tile targeting** by the opponent that the search cannot counter.

Mechanism:
- Opp consistently breaches at the same tile (or via the same launcher).
- VD's value function evaluates plans on POST-state aggregates (HP, structure value, breaches actually achieved in the simulated rollout) but does NOT incorporate "given opp's likely launchers, would our defense intercept their probable paths?".
- Result: The search may pick defenses that look strong overall (high struct value, decent breach term) but happen to leave a corridor open for opp's actual repeating attack.

The fix needs to reward defenses that *block opp's empirical attack pattern* — without hardcoding any opponent archetype or tile list.

---

## 2. Approach: Path-Aware Value Function via Sim Probe

For each unique defense template that survives phase-1 into phase-2, run a NEW sim probe:

1. Apply the defense ops to a fresh sim state.
2. Spawn synthetic OPPONENT scouts at the top-3 RECENT launcher positions (tracked per turn from `on_action_frame`'s `events.spawn`).
3. Run sim_rs `simulate_action_phase`.
4. Count opp breaches via opp's SP delta.
5. Return `kill_ratio = 1.0 - (breached / spawned)`.

The kill_ratio is then passed to `evaluate()` as `path_intercept_score`. Defenses scoring high (intercepting opp's actual pattern) earn a positive bonus; those scoring low (letting the pattern through) earn a penalty.

**The signal is empirical**: observe → predict → score. The probe simulates opp's ACTUAL attack pattern, not a generic archetype assumption.

---

## 3. Files changed

### New: `oracle_core/path_intercept_probe.py`

Implements `probe_path_interception(base_state, defense_plan, opp_recent_launchers, config, sim_runner, ...)`. Returns kill_ratio in [0, 1] (PROBE_NEUTRAL_SCORE=0.5 if no launcher data).

Critical implementation detail: the `_opp_target_edge(spawn_xy)` helper uses the **canonical SimCore convention** from `algos/athena/sim_rs/src/map.rs`:
- `0 = TOP_RIGHT, 1 = TOP_LEFT, 2 = BOTTOM_LEFT, 3 = BOTTOM_RIGHT`

For top-player spawns:
- `x < 14` (TL side) → target BOTTOM_RIGHT (3)
- `x ≥ 14` (TR side) → target BOTTOM_LEFT (2)

This matches `oracle_core/plan.py::_spawn_to_target_edge` and `oracle_core/viability_probe.py`. Earlier draft used the WRONG convention (sim_eval.py's stale comment) and the probe returned 0.0 for every defense — the convention bug was caught and fixed.

### Modified: `oracle_core/value.py`

- Added `path_intercept_weight: 50.0` to `VALUE_WEIGHTS`.
- Added `path_intercept_score` keyword arg to `evaluate()`.
- New term: `path_intercept_term = weight * (score - 0.5)`. Centered on 0.5 so neutral data contributes 0; perfect interception adds +25; total failure subtracts -25.

### Modified: `oracle_core/search.py`

- Added `opp_recent_launchers` parameter to `search()`.
- Phase-2 inner loop: for each unique defense template (by `cand.name.split("|")[0]`), runs the J3 probe ONCE and caches the kill_ratio. Per-candidate evaluations look up the cached score by defense name.
- Cost: ~15 extra sims per turn (one per unique defense template surviving phase 1), each ~0.2 ms = ~3 ms overhead. Well under budget.
- Depth-2 also passes the cached score to evaluate().

### Modified: `algo_strategy.py`

- Added `opp_launcher_weights: dict[(x,y) -> float]` tracker.
- `on_action_frame`: every opp mobile spawn (Scout/Demolisher/Interceptor) increments that tile's weight by +1.0.
- `on_turn`: per-turn decay (`LAUNCHER_DECAY=0.85`) applied once via `_last_launcher_decayed_turn` idempotent guard.
- Top-3 launchers (`LAUNCHER_TOP_K=3`) sorted by weight descending are passed to `search()` as `opp_recent_launchers`.
- Telemetry: `[oracle_pure] j3_launchers n=N top=[...]` on each turn.

---

## 4. Critical bug found and fixed during implementation

**Bug**: my first draft used the same target_edge convention as `sim_eval.py::_apply_deploys_inplace`, which has a stale/wrong comment claiming `0=BL, 1=BR, 2=TL, 3=TR`. The actual canonical convention (per `sim_rs/src/map.rs` and `plan.py::_spawn_to_target_edge`) is the OPPOSITE: `0=TR, 1=TL, 2=BL, 3=BR`.

**Symptom**: the probe always returned `kill_ratio = 0.0` regardless of defense quality.

**Discovery process**: Testing with progressively heavier defenses (4 turrets → 24 upgraded turrets → full bottom-edge wall) all returned 0.0 breach interception. This was the smell — even an impenetrable wall shouldn't allow scouts through. Further drilldown showed scouts with mismatched target_edge were treated as "any reasonable edge" by the engine and stuttered/breached at 1 SP each.

**Fix**: switched `_opp_target_edge` to the canonical convention. Re-test produced meaningful, differentiated kill_ratios:

```
defense:none            kill_ratio = 0.000   ← opp breaches freely
defense:central_4upg    kill_ratio = 1.000   ← perfect interception
defense:thick           kill_ratio = 0.800   ← good
defense:left_focus      kill_ratio = 0.000   ← defense on wrong side
defense:right_focus     kill_ratio = 0.500   ← intercepts only one launcher
defense:bot_wall        kill_ratio = 1.000   ← impenetrable
```

Per-probe time: ~0.2 ms.

This also exposes that **`sim_eval.py::_apply_deploys_inplace` may have a latent bug** in target_edge assignment for both bottom and top players. The comments inside sim_eval.py contradict the canonical convention. This is out of scope for J3 (the scout deploys via that path land on edges and breach as expected during full evaluation), but worth flagging as a cleanup task.

---

## 5. Validation results

### Tier A (must pass 100%)

(See `validation_results.txt` for full output.)

### H2H

(Pending.)

### Targeted test

The probe's signal differentiation was demonstrated above (section 4). With the actual J3 in a live match vs python-algo:
- `j3_launchers` is populated and decayed each turn.
- Top launchers shift as opp's behavior changes (e.g., (7, 21) early → (13, 27) once opp commits to center).
- Per-turn search wall-clock is ~0.2-0.6s — well under budget.

---

## 6. Critical assessment

### Mechanism actually fires?

Yes. From a quick local match (J3 vs python-algo):
- Turn 0: `j3_launchers n=1 top=[((7, 21), 0.85)]` (post first observation)
- Turn 6: `j3_launchers n=6 top=[((13, 27), 1.4449...), ((11, 25), 0.6141...), ...]`
- Path-intercept probes run during phase-2 (visible via `sims=1027` count).

### Risk of regression

- The J3 weight (50.0) is comparable to VC's `breach_pressure_coverage` (0.2 × ~50 effective). Both are SOFT signals.
- Probe results are CACHED per unique defense template (by `def_name`). So compute scales with template diversity (typically ~10-15 templates), not with all 2500 candidates.
- If `opp_recent_launchers` is empty (no observation data, e.g., turn 0 before opp's first deploy), the probe is skipped entirely — back-compat with VD behavior.

### Comparison to VB's viability probe

VB and J3 use the same probe machinery (sim_rs as path oracle) but answer DIFFERENT questions:
- **VB** (`probe_offense_viability`): probes OUR defense vs OUR offense. "Does our defense block our own scouts?" — penalizes trap defenses.
- **J3** (`probe_path_interception`): probes OUR defense vs OPP's empirical offense. "Does our defense block opp's likely scouts?" — rewards defenses that intercept opp's actual attack pattern.

They're complementary: VB prevents self-traps, J3 rewards correct counter-positioning.

### What this does NOT solve

- **Opponent learning vs static defenses**: J3 reacts to opp's RECENT pattern. If opp adapts every turn, the probe lags by one turn. (Decay rate 0.85 means recent launchers dominate.)
- **Multi-launcher fan-outs**: probe tests top-3 launchers in parallel, but spawns 5 scouts per launcher → 15 total. Heavy demolisher/interceptor opps may not be well-modeled (probe always uses scouts).
- **The trap bug**: J3 doesn't directly fix the (12,11)/(15,11) trap — that's still VB's job.

### Future improvements

- Use opp's actual unit type at each launcher (track Demolisher/Interceptor counts separately).
- Adaptively choose probe scout count based on opp MP economy.
- Cache probe across sub-turns when defense template repeats.

---

## 7. Files

| Path | Purpose |
|---|---|
| `oracle_pure_M1Lite_J3_upload/` | Full upload folder |
| `oracle_pure_M1Lite_J3_upload/oracle_core/path_intercept_probe.py` | New probe module |
| `oracle_pure_M1Lite_J3_upload/oracle_core/value.py` | Updated evaluate() |
| `oracle_pure_M1Lite_J3_upload/oracle_core/search.py` | Updated search() |
| `oracle_pure_M1Lite_J3_upload/algo_strategy.py` | Launcher tracking |
| `oracle_pure_M1Lite_J3_upload/run_validation.sh` | Validation script |
| `oracle_pure_M1Lite_J3_upload/validation_results.txt` | Validation output |

---
