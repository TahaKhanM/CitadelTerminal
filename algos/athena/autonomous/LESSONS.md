# LESSONS — observed patterns; what wins, what fails

Consolidated from RA-1..RA-5 briefs (cycle 0). Priority-ordered.

## CORE LESSONS

### L1. Offensive archetype is the bottleneck, not defense.
v13-family mirror ceiling is 40-40 regardless of defensive tuning. Center-only Scouts die to 60 dmg/frame turret focus-fire. Pick from these archetypes (NOT defensive tunings):
- Demolisher+Support train (range 4.5 from y=10 outranges base Turret 2.5)
- Interceptor+Scout mixed wave
- Self-destruct scripted attack
- Own-wall path engineering
- Removal-relocate dynamic

### L2. Frame-order exploits (engine bytecode-verified)
`move(1) → collision(2) → shield_decay(3) → shield_give(4) → breach(5) → self_destruct(6) → attack(8)`
- Shield applies BEFORE attack → Supports placed at Y≥7 guarantee shield before turrets fire
- Self-destruct triggers BEFORE attack → SD fires even on frame it dies
- Breach awards SP immediately in-frame (opponent gets +1 SP next deploy)

### L3. Upgraded-Support shield is stackable + linear
`shield = 1 + 0.7 × Y`, range 7. Four upgraded Supports at [12-15, 13] → ~40 shield per Scout (stacks linearly; each scout picks up from all in-range). At Y=13: 10.1 per Support.

### L4. Targeting tiebreaks (decompilation-verified)
1. Distance
2. HP+shield (LOWER preferred)
3. |y − attacker_start_y| (SMALLER = closer to attacker's own edge, preferred) — **not "distance to enemy edge"**
4. |x − 13.5| (LARGER preferred — farthest from centerline) 
5. uid ordering (LARGER preferred, usually newest)

### L5. SD mechanic exact
- Range: 1.5 Euclidean (up to 8 tiles: 4 orth + 4 diag)
- Damage = unit starting HP (Scout 15, Demolisher 5, Interceptor 40)
- Minimum travel: 5 tiles to trigger. <5 tiles = silent (0 damage)

### L6. Upgraded Turret specs
- 20 dmg/frame, 100 HP, range 3.5 (vs base 6 dmg/75 HP/2.5 range)
- **Total cost: 6 SP** (2 base + 4 upgrade — NOT 4 SP as docs say)
- Saturation: one upgraded Turret per 7-tile lane is overkill; 4+ in a row wastes SP

### L7. MP decay + income formula
`MP_new = round(MP_old × 0.75, 0.1) + MP_income`. Income: +1 MP T0-3; +2 MP T4-8; +3 MP T9-13; +4 MP T14+. Hoarding 8+ MP costs exponentially.

### L8. Engine mis-docs (decompilation finds)
- `metalForBreach = 1` is per-breacher-count (NOT "+1 SP per damage")
- `coresForPlayerDamage` is a legacy key ABSENT from live config
- Pathfinder re-validates lazily on wall death mid-turn (stateless BFS misses this)
- Live API shorthands still `FF/EF/DF/PI/EI/SI` (pre-rename codes)

### L9. Public-finalist gaps (unsolved)
1. Demolisher-spam-one-side defense
2. Mirror-match offensive diversification (MAP-Elites over archetypes)
3. Predictor + simulator fusion (Ryan Draves framework — never published)

### L10. SimCore is authoritative and usable
- Python: 60.4 sims/s INSTRUMENTED (byte-exact to engine)
- Rust: 7.9K sims/s single-core (no PyO3 bindings yet)
- 19 STRICT columns @ max_err=0; 75 invariants + 4 metamorphic pass

## DO NOT REPEAT
- Markov spawn-edge prediction → zero signal on v13 mirrors
- CMA-ES defensive tuning → no fitness gradient
- Caravan-only shield scaling → insufficient against focus-fire
- v13 hyperparameter sweeps → hard ceiling is archetype

## BUILD ON
- Edge-strength side scorer: `25/dist` (upgraded turret) + `5/dist` (base) + `3/1` (upgraded/base wall)
- Build-tier list: V-walls → upgraded interior turrets → second layer → upgraded back Supports
- Refund+repair: walls <50%, turrets <30%
- Edge-block-and-remove: spawn [0,13]/[1,13], upgrade, immediate remove
- Pathfinder `deque` substitution → 6-7× speedup
- Score function: `3.5 × units_to_base + cores_damage − 100 × supports_lost − turret_damage`

## V13 TOP-3 CONCRETE FIXES (from RA-2)
1. East-west lane adaptation: measure opponent x-distribution, bias spawn [13,0] or [14,0] toward sparser half (+4-6% lift vs asymmetric)
2. Turn-scaled Scout threshold: `(8 + 0.1×turn) × scout_cost`, cap 12 (+2-3% vs hoarders)
3. Breach-rate-responsive Interceptors: if breach-rate ≥2/last5, lower trigger from `6×scout_cost` to `4×scout_cost` (+1-2% vs rushers)
