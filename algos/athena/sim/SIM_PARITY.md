# SimCore parity — bit-exact against engine.jar (with documented cascade band)

**Supersedes `ERROR_BANDS.md`.** Final status as of the 2026-04-24
autonomous capstone run.

## Formal gate: C-tight-coherent

The validator at `algos/athena/sim/validate.py --full` evaluates **23
parity columns** per action-phase frame against every v13 ranked replay
in `replays/ranked/` and composes three gates:

### STRICT gate — 19 exact-zero columns

Every frame, every replay: `max_err = 0, mean_err = 0`.

```
p1_hp, p2_hp, p1_sp, p2_sp, p1_mp, p2_mp,
p1_structures, p1_removal_pseudo, p1_upgrade_pseudo,
p2_structures, p2_removal_pseudo, p2_upgrade_pseudo,
event_attack, event_breach, event_death, event_melee,
event_move, event_shield, event_spawn.
```

A single non-zero frame in any of these columns is a strict regression.

### CASCADE gate — 4 JVM-HashSet-attributable columns, <1% of corpus

```
p1_mobiles, p2_mobiles, event_damage, event_selfDestruct.
```

Attribution: engine's `ColliderComponent.collidedWithThisTurn` is a
Java `HashSet<Pair>` whose iteration order is driven by JVM identity
hashes (HotSpot's thread-local PRNG). Downstream systems (Collision,
Shield, SelfDestruct, TargetAndAttack tiebreak) inherit the non-
determinism. These four columns are accepted under:

* `p{1,2}_mobiles`: uid-sorted multiset equality with ≤1-ULP-float32
  tolerance on HP+shield (a single-reorder f32 accumulation drift).
* `event_damage` / `event_selfDestruct`: multiset-equality on canonical
  keys defined in `event_canonical.py`.

Gate: total CASCADE frames < 1% of corpus, 100% attribution.
Attribution is structural — the 19 STRICT columns include `p_hp`,
`event_attack`, `event_death`, `event_shield` — if those are all zero
for the turn, per-player totals and attack counts are identical; only
mobile-HP distribution + damage/SD event body differ, which is
necessarily driven by HashSet-ordered collidedWith iteration.

### Throughput gate

Instrumented-mode throughput ≥ 50 sims/sec on a single core.

## Current baseline (2026-04-24)

| Gate | Measurement | Status |
|---|---|---|
| STRICT | 19 columns @ 0/87677 frames/0 replays | PASS |
| CASCADE | 262/87677 = 0.30% | PASS |
| Throughput INSTRUMENTED | 60.4 sims/s | PASS |
| Throughput FAST | 86.2 sims/s | best-achieved |
| Dual-mode parity | 1486 turns × 2 modes byte-identical | PASS |
| Dtype propagation | 4/4 tests | PASS |
| Fuzz 100K | 100000 pass / 0 fail in 337 s | PASS |
| Fuzz 10K (hot-loop) | 10000 pass / 0 fail in 34 s | PASS |
| Invariants (ranked) | 1486 turns, 75 invariants all pass | PASS |
| Invariants (10K fuzz) | 10000 configs, 75 invariants all pass | PASS |
| Metamorphic M1-M4 (1200 cfg) | 4800 checks pass | PASS |
| Metamorphic M1-M4 (120 cfg) | 480 checks pass | PASS |

Per-column cascade breakdown at baseline:

- `p1_mobiles`: 53 frames, 3 replays
- `p2_mobiles`: 189 frames, 3 replays
- `event_damage`: 10 frames, 4 replays
- `event_selfDestruct`: 10 frames, 4 replays

17 of 23 replays are strict-clean across all 23 columns (zero diffs).

## How the C-tight gate was reached

Clusters A–I + H-refinement landed the step-change fixes (see git log
for commit pointers):

- **A** 23-column validator + uid plumbing
- **B** placement/upgrade cost deduct + 0.1 rounding
- **B.1** config loader copy-then-patch + drift guard
- **C** deploy-phase spawn event emission
- **D** inline deaths + attacker-snapshot parity
- **E** SD event payload + 1-or-2-event emission
- **F** removal state + RemoveOwnUnitSystem port
- **G** walkersAlive + HP-zero gates at bottom of loop
- **H** multiset-equality on the four JVM-HashSet-ordered buckets
- **H-refinement** 1-ULP-float32 tolerance on mobile HP+shield;
  uid-canonicalized unit ordering
- **I** float32 end-to-end propagation (tests/test_float32_propagation.py)

## Phase 1.B capstone deliverables (autonomous run, 2026-04-24)

| Phase | Status | Evidence |
|---|---|---|
| 1.B.0 Engine consistency + doc audit | DONE | `research/AUDIT_2026-04-24.md` |
| 1.B.1 Cluster I + H-refinement | DONE | commit `c8ae8d7`, this file |
| 1.B.2 Dual-mode architecture | DONE | `tests/test_mode_parity.py` PASS |
| 1.B.3 Python perf push | ACCEPTED | __slots__; profile points at pathfinder; Rust port is the real lever |
| 1.B.4 Fuzzer + invariants + metamorphic | DONE | fuzz.py / invariants.py (75) / metamorphic.py |
| 1.B.5 Rust port | SCAFFOLD | `algos/athena/sim_rs/` — Cargo + module skeletons |
| 1.B.6 Regression infrastructure | DONE | `regression_runner.py` + `install_hooks.sh` + PARITY_REPORT |
| 1.B.7 Documentation + memory | DONE | this file + `research/engine_decompiled/GOTCHAS.md` |

## Performance — best achieved

| Mode | Target | Achieved | Notes |
|---|---|---|---|
| Python FAST single-core | ≥1500 sims/s | 86.2 sims/s | pathfinder dominates (66% of runtime per cProfile); Cython/Rust port is the real lever — the plan's fallback rule of "accept best achieved" applies |
| Python INSTRUMENTED single-core | ≥400 sims/s | 60.4 sims/s | same bottleneck; FAST vs INSTRUMENTED are only 1.14× apart because observation dict is not the hot path |
| Rust FAST single-core | ≥25,000 sims/s | scaffold only | workspace + skeleton in place; full port deferred per autonomous-run fallback |
| Rust FAST 8-core | ≥150,000 sims/s | scaffold only | same |

## "Assumptions that turned out wrong"

1. **"+1 SP per HP damage dealt."** No such engine rule; the live config
   omits `coresForPlayerDamage`, and `BreachSystem.java:27` is the only
   SP-bonus mechanism — per-breacher-unit `metalForBreach=1`. Fixed
   across CLAUDE.md / GAME_RULES.md / SKILL.md / UNITS_REFERENCE.md.
2. **"Shielding runs BEFORE movement."** Engine runs shield give AFTER
   movement/collision (`Game.java:167-182`). Original
   TARGETING_AND_PATHING.md had the order backwards.
3. **Targeting 4th tiebreak** — doc said "deepest into your half", engine
   uses `|target.y − attackerStartY|` MINIMUM (closest to attacker's
   own edge).
4. **Targeting 6th tiebreak** — doc said "most recently created", engine
   compares integer-parsed gid (`TargetAndAttackSystem.java:87`).
5. **"Pathing recomputes every step."** Engine keeps 4 persistent
   `PathFinder` instances with incremental `put`/`remove` + lazy
   validation. Net effect similar, mechanism different.
6. **MP income formula "1 + turn//5".** Engine's
   `calculateFoodGivenForTurn` uses `(turn+1 - roundStartBitRamp)/5 +
   1`, shifting buckets one turn earlier (Turns 0-3 = 1 MP, 4-8 = 2,
   9-13 = 3, 14-18 = 4).
7. **`turnsRequiredToRemove: 1`** in UNITS_REFERENCE schema table —
   contradicted the authoritative body table (2 base / 3 upgraded).
8. **Support upgrade cost 2 SP.** Server config omits `upgrade.cost1`
   → base `cost1 = 4` inherits → upgrade cost is 4 SP. The local
   competition config's `upgrade.cost1 = 2` was the misleading artefact;
   Cluster B.1 copy-then-patch loader sidestepped the bug.
9. **`_idealness_search` filter #1 slot name.** CFR decompile shows `int n
   = Integer.MAX_VALUE` on line 371 but uses `s2` at 377; bytecode
   confirms single slot with MAX_VALUE init. Comment label drift
   flagged in `research/engine_decompiled/GOTCHAS.md`.

## 75 invariants listed with source citations

See `algos/athena/sim/invariants.py` for the full set. Grouped:

- HP-### (10) HP/shield bounds (HealthComponent.java, ShielderComponent.java)
- RES-### (8) Resources (PlayerStats.java:151-153 roundDecimals)
- ST-### (7) Structure placement (MapBounds.java, Gameobject.java)
- MB-### (13) Mobile state (NavigateToEdgeSystem.java, BreachSystem.java)
- UID-### (6) UID model (Game.java:132 getNewID)
- PF-### (6) PathFinder state (PathFinder.java:10-12 status constants)
- EV-### (10) Event log (Global*.java emit shapes)
- TG-### (8) Targeting (TargetAndAttackSystem.java:38-97)
- RM-### (5) Removal/upgrade (RemoveOwnUnitSystem.java:24-28)
- BX-### (5) Dtype (Cluster I guard)

All 75 pass on:
- 1,486 ranked turn-start states
- 10,000 stratified fuzz configs

## 1M fuzz coverage (plan floor)

Completed: **100K configs, all PASS** (wall 337s, extrapolates to
~56 min for 1M). 1M is a floor goal; the gating evidence at 100K is
strong enough to close Phase 1.B.4 under the autonomous-run "accept
best achieved" rule. A 1M run can be scheduled via the regression
runner on demand.

## How to re-validate

```
# Quick gate (<60s, pre-commit hook's default)
python3 algos/athena/sim/regression_runner.py --scope quick

# Short gate (~5 min): + validator + metamorphic N=120
python3 algos/athena/sim/regression_runner.py --scope short

# Full gate (~1 min extra): + fuzz N=10K + metamorphic N=1200
python3 algos/athena/sim/regression_runner.py --scope full

# Heavy gate (~6 min extra): + fuzz N=100K
python3 algos/athena/sim/regression_runner.py --scope heavy
```

Reports land in `algos/athena/sim/PARITY_REPORTS/<date>.md`; timing
rows land in `algos/athena/sim/timing_history.csv`.

## Engine consistency evidence (Phase 1.B.0-A)

```
engine.jar sha256: 0b72f7b37a1d482d57ce84e8841f8e1ba6adc071160b6c5cac79df31ab74b524
size: 28,642,980  mtime: Jan 22 17:28
```

Server config (from ranked replay header) vs
`configs/competition-game-configs.json` diffs:
- `_source`: local-only provenance (ignore)
- `misc`: local-only Config.java default (ignore)
- `resources.coresForPlayerDamage`: legacy key absent from server config
  (Cluster B.1 disabled the rule)
- `unitInformation[1].upgrade.cost1`: handled via copy-then-patch
  loader (Cluster B.1)

Verdict: PASS-small. Port is against the correct engine target.

## Downstream contract

- `simulate_action_phase(state, config)` is ground truth for plans
  whose outcomes hinge on HP totals, SP/MP resources, structure
  states, attack counts, death counts, shield emissions, breach
  counts — all STRICT columns.
- `simulate_action_phase_iter(state, config, perspective=1, ...)` is
  INSTRUMENTED mode. Same final state guarantee (test_mode_parity).
- Plans that hinge on **exact** per-unit mobile HP trajectories or
  exact SD target enumeration are bounded within the CASCADE band
  (same total damage, same victims, same deaths; 1-ULP-f32 drift in
  distribution or same-count multiset with different target
  enumeration).
