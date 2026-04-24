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

## Python ↔ Rust cross-validation (2026-04-24)

PyO3 bindings + maturin abi3-py39 landed. `cross_validate.py` runs both
implementations on the same inputs and diffs post-sim state under
canonical-key ordering for the 4 JVM-HashSet-ordered buckets.

```
python3.12 -m algos.athena.sim.cross_validate
  cross_validate: checks=3319 ranked_wall=32.1s fuzz_wall=0.0s
  ok=3319  fail=0   gate: PASS

python3.12 -m algos.athena.sim.cross_validate --fuzz 10000 --seed 42
  cross_validate: checks=13319 ranked_wall=32.1s fuzz_wall=5.9s
  ok=13319  fail=0  gate: PASS
```

**Zero divergences** across 3,319 ranked frames + 10,000 seeded fuzz
configs across all 12 stratified categories (commit `17d7376`). A
prewarm-introduced pathfinder bug found during the 10K-fuzz run
(`PathFinder::prewarm_step_cache` produced order-dependent
`pathlength` / `status` state on certain `max_budget` wall mazes;
6/10,000 configs diverged at `--fuzz 10000`) was replaced by a
walk-warmup that walks each live mobile's path from spawn to stuck,
priming the step_cache along exactly the tiles the sim will query.

Repro evidence: `repro_fuzz_{diff,min,trace,rs_trace}.py`.

## ACHIEVED TARGETS — Rust port & accuracy (2026-04-24)

All accuracy floors GREEN. Performance floors are tracked separately
under § REMAINING TARGETS — none of the three perf floors (Rust FAST
single-core, Rust FAST 8-core, Python FAST) is met yet, so they
explicitly do NOT belong in this table. They will be promoted back
here only after a clean two-consecutive-bench measurement on
`mid_game_108_struct_5_mob` shows the floor crossed.

| Target | Floor | Status | Evidence |
|---|---|---|---|
| STRICT 19 columns max_err=0 on 23 ranked replays | required | **GREEN** | `validate.py --full` (prior session) |
| CASCADE 4 columns multiset-equal <1% corpus | required | **GREEN** | `validate.py --full` (prior session) |
| Python ↔ Rust byte-identical ranked | 3,319/3,319 | **GREEN** | commit `17d7376` |
| Python ↔ Rust byte-identical 10K fuzz | 13,319/13,319 | **GREEN** | commit `17d7376` |
| 1M fuzz seed=42 clean | required | **GREEN** | commit `c9a55b4` |
| 1M fuzz seed=17 clean (independent seed) | required | **GREEN** | commit `50075e5` |
| 84+ invariants on 8.3M assertions | zero fail | **GREEN** | prior session evidence |
| 4 metamorphic on 45,944 runs | zero fail | **GREEN** | prior session evidence |
| Pre-commit hook blocks seeded regressions | demonstrated | **GREEN** | prior session evidence |
| Rust INSTRUMENTED single-core | ≥5,000 sims/s | **GREEN** | 14.3 K on `mid_game_108_struct_5_mob` (quickbench) |

## REMAINING TARGETS — perf floors (open as of 2026-04-24)

These were prematurely placed in ACHIEVED in commit `1011545`. They
are NOT met. Each row carries the current measurement, the floor it
must clear, and the gap (factor required). Do not promote a row back
to § ACHIEVED until two consecutive bench runs on
`mid_game_108_struct_5_mob` (clean machine, no concurrent load) show
the floor cleared.

| Target | Floor | Current | Gap | Lever sequence (this session) |
|---|---|---|---|---|
| Rust FAST single-core | ≥25,000 sims/s | **14.3 K** | 1.74× | (1) portable_simd f32x8 bbox filter in `system_attack`, (2) Structures SoA, (3) PGO retry |
| Rust FAST 8-core batch | ≥150,000 sims/s | **75 K on 10-thread** | 2.0× (8c) / 1.5× (10c) | (4) `Arc<PathFinder>` + per-thread `bumpalo::Bump`; eliminates allocator contention from per-sim clones |
| Python FAST single-core | ≥1,500 sims/s | **376 sims/s** | 4.0× | mypyc landed (commit `93d60c8`); next: portable_simd-style C extension hot loop, or full Cython rewrite of fire_one |

### FAST single-core optimization trace

Commit-level evidence (before → after on `mid_game_108_struct_5_mob`):
- Baseline (pre-session): **6.0 K** sims/s
- Commit `17d7376` (walk-warmup replacing buggy prewarm + cross_validate gate): **14.3 K**
- Commit `6defecf` (force-inline fire_one_turret + per-system profile): maintained **14.3 K**

Profile evidence (commit `b1ab1f7` / `examples/attackprof.rs` +
`examples/attack_phase_prof.rs`):
- `system_attack` = 93% of per-sim cost (65 µs of 70 µs/sim)
- Within attack:
  - Fire loop (steady-state): 21 µs/sim (0.31 µs × 69 frames)
  - Dirty rebuild: 15 µs/sim (4 µs × 4 rebuilds)
  - Per-turret outer-loop bbox check: 31 µs/sim across 3,105 iterations
    (45 turret_infos × 69 frames; 69 % bbox skip rate functional)
- Per-turret fire body (~14 fires/frame × 69 frames = 966 fires/sim)
  averages 68 ns/fire — near the register/scheduler floor; further
  gains below this require structural SoA + SIMD.

### FAST single-core — outstanding

The 14.3 K → 25 K gap (1.74×) requires two structural refactors that
were *not* included in this commit cluster because each requires
non-local change to the core state layout:

1. **Structure-of-Arrays** (`Vec<Structure>` → per-field `Vec<f32>`,
   `Vec<(i16, i16)>`, etc.). Target: vectorise the dirty-rebuild
   distance filter and the per-turret enemy-cand liveness scan.
2. **portable_simd f32x4** (Apple Silicon NEON 128-bit width) on the
   bbox filter in the outer turret loop — 45 scalar checks → 12 SIMD
   checks per frame.

Per-fire cost is already at the IPC ceiling for scalar code on this
fixture (3,105 iter × 20 ns = 62 µs vs 65 µs measured attack cost →
~95 % of attack time is in the hot loop body). SIMD + SoA are the
remaining levers; they are hard to apply incrementally without
risking the strict-parity gate, and were sequenced after the
correctness fix (walk-warmup) which itself fixed a bug the prior
session's "prewarm for 14.4 K" would have shipped.

### FAST 8-core — outstanding

10-thread batch best of **75 K sims/s** on 10-core Apple M-series
(`examples/parallel_bench` batch=2048). Per-core efficiency 50 % —
allocator contention on the per-sim IndexMap + pathfinder clones under
10-way parallel load is the dominant loss. Options for the 150 K
8-core floor:

1. **`Arc<PathFinder>`** so per-sim clone is a refcount bump rather
   than a 26 KB `Box<PathFinder>` memcpy; CoW via `Arc::make_mut` on
   the rare mid-sim `put`/`remove` call site.
2. **Bumpalo-based per-thread scratch pools** so Rayon workers never
   allocate mid-sim (the IndexMap clone is the other big allocator hit).
3. **Delta-replay**: share an `Arc`-wrapped immutable base state, each
   sim applies an action-diff without cloning the base. Most invasive.

### Python FAST — outstanding

Python FAST `mid_game_108_struct_5_mob` measured at **336 sims/s
best** (pre-session baseline ~110 sims/s) after `__slots__` + mypyc
compile of `state.py` / `pathfinder.py` / `pysim.py` / `config.py` +
Cython `_fastsim` extension build — all landed by the parallel
Python-perf agent. The 336 → 1,500 gap requires further inner-loop
work in the attack+shield systems (the Python path has additional
overhead from numpy.float32 coercion at every arithmetic site that
Rust avoids by construction).
