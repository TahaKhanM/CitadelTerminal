# SimCore parity — bit-exact against engine.jar (with documented cascade band)

**Supersedes `ERROR_BANDS.md`.** Status as of Phase 1.B.1-final (2026-04-24).

## Formal gate: C-tight-coherent

The validator at `algos/athena/sim/validate.py --full` evaluates **23 parity
columns** per action-phase frame against every v13 ranked replay in
`replays/ranked/` and composes three gates:

### STRICT gate — 19 exact-zero columns

Every frame, every replay, every turn: `max_err = 0, mean_err = 0`.

```
p1_hp, p2_hp, p1_sp, p2_sp, p1_mp, p2_mp,
p1_structures, p1_removal_pseudo, p1_upgrade_pseudo,
p2_structures, p2_removal_pseudo, p2_upgrade_pseudo,
event_attack, event_breach, event_death, event_melee,
event_move, event_shield, event_spawn.
```

A single non-zero frame in any of these columns is a strict regression
and fails the gate. Regression hook: `pytest -q` + `python3
algos/athena/sim/validate.py --full` must both return zero non-zero
STRICT frames.

### CASCADE gate — 4 JVM-HashSet-attributable columns, <1% of corpus

```
p1_mobiles, p2_mobiles, event_damage, event_selfDestruct.
```

These columns' emission orders and per-unit HP distributions are
derived from engine's internal iteration of
`ColliderComponent.collidedWithThisTurn`, a Java `HashSet<Pair>`.
HashSet iteration order is driven by per-object identity hashes (JVM-
assigned via HotSpot's thread-local PRNG) — not by game semantics. Any
downstream system that consumes that set (CollisionSystem,
ShieldSystem, SelfDestructSystem, TargetAndAttackSystem tie-breakers
feeding from it) inherits the non-determinism.

The CASCADE gate accepts that:

- `p{1,2}_mobiles`: uid-sorted multiset equality with ≤1-ULP-float32
  tolerance on the HP+shield slot. One ULP covers the single-reorder
  case where attacker-A-hits-first vs attacker-B-hits-first causes
  `shield - damage` to accumulate one bit differently. Beyond 1 ULP
  the frame is flagged as structural state cascade and counted toward
  the cascade budget.
- `event_damage` / `event_selfDestruct`: multiset-equality on the
  canonical key (`event_canonical._key_damage`,
  `_key_self_destruct`). Keys cover (`uid`, `amount_f32`, `typeID`,
  `xy`, `pid`) / (`src_uid`, `src_xy`, `dmg_f32`, `typeID`,
  `sorted(target_xys)`). When per-frame event counts differ (because
  reordered SDers found different target sets), the frame counts
  toward the cascade budget.

**Gate**: total CASCADE frames across all 23 replays and all columns
must be **< 1% of the total frame corpus**, with 100% attribution to a
JVM-HashSet-driven system (Collision / Shield / SelfDestruct / Target
tiebreak). Attribution is structural: the 19 STRICT columns include
`p_hp`, `event_attack`, `event_death`, and `event_shield`. If those
are all at zero for the same turn, per-player totals and per-unit
attack counts are identical; only mobile-HP distribution + damage/SD
*event body* differ → necessarily attributable.

### Throughput gate

Instrumented-mode throughput ≥ 50 sims/sec on a single core.

## Current baseline (2026-04-24, commit TBD)

| Gate | Measurement | Status |
|---|---|---|
| STRICT | 19 columns @ 0/87677 frames/0 replays | PASS |
| CASCADE | 262/87677 = 0.30% | PASS |
| Throughput | 60.4 sims/s INSTRUMENTED | PASS |

Per-column cascade breakdown on the current baseline:
- `p1_mobiles`: 53 frames, 3 replays
- `p2_mobiles`: 189 frames, 3 replays
- `event_damage`: 10 frames, 4 replays
- `event_selfDestruct`: 10 frames, 4 replays

17 of 23 replays are strict-clean across all 23 columns (zero diffs in
any column, including cascade).

## How the C-tight gate was reached

Clusters A–I landed the step-change fixes (see git log for details):

- **A** 23-column validator + uid plumbing
- **B** placement/upgrade cost deduct + 0.1 rounding
- **B.1** config loader copy-then-patch + drift guard (removed the
  bogus `coresForPlayerDamage` rule — confirmed absent from live
  server config)
- **C** deploy-phase spawn event emission
- **D** inline deaths + attacker-snapshot parity
- **E** SD event payload + 1-or-2-event emission
- **F** removal state + RemoveOwnUnitSystem port
- **G** walkersAlive + HP-zero gates at bottom of loop
- **H** multiset-equality on the four JVM-HashSet-ordered buckets via
  canonical keys (`event_canonical.py`)
- **H-refinement** 1-ULP-float32 tolerance on mobile-bucket HP+shield;
  uid-canonicalized unit ordering
- **I** float32 end-to-end propagation (all HP/shield/damage/SP/MP
  arithmetic; dtype guarded by `tests/test_float32_propagation.py`)

## How to re-validate

```
python3 algos/athena/sim/validate.py --full
```

Expected output: three gate lines all PASS, final
"Phase 1.B.1-final C-tight-coherent gate: PASS".

Any regression:

1. `python3 algos/athena/sim/diff_frames.py <replay> <turn>` — narrow
   to a specific frame + column.
2. `python3 algos/athena/sim/diff_turn.py <replay> <turn>` — post-
   state diff for the whole turn.
3. Cross-reference the failing column:
   - STRICT regression → trace to the specific engine source-line
     governing the emission (see
     `research/engine_decompiled/GOTCHAS.md`).
   - CASCADE regression → verify it's still <1% and still attributable
     (same turn, p_hp strict, event_attack strict, and event_damage
     count-match within multiset).

## What this means downstream

- `simulate_action_phase(state, config)` is ground truth for plans
  whose outcomes hinge on HP totals, SP/MP resources, structure
  states, attack counts, death counts, shield emissions, breach
  counts — all STRICT columns.
- Plans that hinge on **exact** per-unit mobile HP trajectories or
  exact SD target enumeration are not byte-exact; they are bounded
  within the cascade band (same total damage, same victims, same
  deaths; just a 1-ULP-f32 drift in distribution or a same-count
  multiset with different target enumeration).
- Downstream consumers (beam search, opponent model, Rust port) are
  instructed to treat `event_damage`/`event_selfDestruct`/`mobiles`
  as HashSet-order-agnostic — see `event_canonical.py` contract.

Engine consistency verdict (Phase 1.B.0) is in
`research/AUDIT_2026-04-24.md`.
