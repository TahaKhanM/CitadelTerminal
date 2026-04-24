# system_attack hotspot profile — 2026-04-24

Run on `mid_game_108_struct_5_mob` fixture (turn 38, 108 structures,
5 mobiles) after Refactor 1 (UID `String→u32`) + scratch-cache +
per-turret enemy-struct cand cache + mobile bbox pre-filter landed.

Tool: `examples/attackprof.rs` + `examples/attack_internal_prof.rs`
(`cargo build --release --no-default-features --example`).

## Per-sim timing (attackprof)

| Segment | µs / sim |
|---|---|
| Full frame loop (baseline) | 69.74 |
| Skip `system_attack` (cap 30 frames) | 4.47 |
| Skip `system_attack` + `system_self_destruct` (cap 30 frames) | 4.37 |
| `clone_no_scratch` only | 2.55 |
| **Implied `system_attack` cost** | **≈ 65.3 µs/sim = 93% of total** |

## Per-frame internals (attack_internal_prof, N=10,000 sims)

| Counter | Value |
|---|---|
| Frames per sim | 69.0 |
| Turret infos per frame | 45 |
| Scratch (dirty) rebuilds per sim | 4.0 |
| Turret fires per sim (bbox-hit or struct-cand) | 957 (13.87 / frame) |
| Turret skipped (bbox miss, no struct cand) | 2,148 (31.13 / frame) |
| bbox skip rate | **69.2%** |
| Avg enemy-mob cands per fire (upper bound) | 0.161 |
| Avg per-turret enemy-struct cand slice | **3.389** |

## Interpretation

The bbox pre-filter is working well: 69% of per-frame turret
iterations are rejected before entering `fire_one_turret`. The
remaining 957 fires/sim carry the 65-µs/sim attack cost →
**68 ns per fire**.

Two sub-phases dominate each fire:

1. **`state.structures.get(&xy)` inside `pick_target_struct`** —
   3.4 IndexMap hash lookups per fire when the struct-fallback
   path triggers. Estimated ~50 ns each, i.e. ~170 ns in the
   multi-cand sub-path. `scl == 1` fast path skips `pick_target_struct`
   but still does one `.get(&xy)` to validate liveness.

2. **Per-candidate distance computation** inside
   `pick_target_struct` + inside `fire_one_turret`'s walker-cand
   build. `dx² + dy²` with i32→f32 casts; small N (0-5 walker cand,
   1-5 struct cand) per fire, so auto-vectorisation across cands is
   unlikely to help without Structure-of-Arrays.

## Leverage ordering (by expected impact)

1. **Replace IndexMap hash lookup with direct Vec index access
   on the struct-cand slice** — store structure Vec-indices in
   `turret_enemy_cands_flat` at dirty-rebuild time rather than xy
   keys. Uses `IndexMap::get_index(i)` which is O(1) array access,
   ~2-5× faster than `.get(&xy)`. Expected: ~1.3-1.6× attack.

2. **Structures SoA**: split `IndexMap<(i32,i32), Structure>` into
   (index ⇒ field) arrays. Lets the inner loop SIMD-load contiguous
   `hp` + `xy` arrays for distance/liveness checks and removes the
   `Structure` 60-byte pointer chase per candidate. Expected:
   ~1.2-1.4× on top of (1).

3. **SIMD `f32x8` fused distance filter** (portable_simd) over
   the enemy-struct-cand slice. With avg slice size 3.4 this is
   a short loop — most benefit will come AFTER SoA when the
   underlying data is contiguous enough to vectorize.

4. **PGO retry** — 1.1-1.3× free if the cached-rustc bug resolves.

## Next action

Implement (1): change `turret_enemy_cands_flat` to store indices
into `structures` (Vec of pairs) and adapt `pick_target_struct` /
`fire_one_turret`'s struct-fallback to index directly. Measure.
