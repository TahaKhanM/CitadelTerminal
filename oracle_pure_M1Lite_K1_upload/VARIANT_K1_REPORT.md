# VARIANT K1 — First-Breach Reaction Templates

**Status: SHIPPED-CANDIDATE.** All Tier A pass, Tier B pass, critical
snorkeldink-v3-2 hard-counter resolved (6/6 wins vs VD's 0/6), and the
H2H-vs-VD regression that emerged in the un-gated prototype was fixed
with a `turn >= 4` activation gate. Recommend uploading.

---

## 1. Goal

VD beats most opponents reliably but **deterministically loses 0/6 to
snorkeldink-v3-2** because that opponent attacks the same 1-2 tiles
*slowly* (29 breaches over 31 turns at (15,1)/(16,2)). VD's reactive
mechanisms either don't fire or fire too late — by the time pressure
accumulates enough for VD's value function to favor patches, the
opponent has already opened the lane wide enough to swing the match.

K1's mechanism: **react to the first observed breach immediately**,
generating defense templates that cover the breach tile. Less gated
than J2's `lane_block` templates (which require turn>12 AND peak>=2.5
AND peak/total>=40%, which v3-2's slow drip never reaches in time).

The mechanism is principled — turret placement is derived from observed
breach tiles, not from hardcoded opponent profiles.

---

## 2. Implementation

### 2.1 New function: `_enumerate_first_breach_response_templates`

Lives in `oracle_core/enumerator.py`. Takes the per-tile
`breach_pressure` map (already maintained by `algo_strategy.py` — VD's
VC mechanism). Steps:

1. **Gates** (very few — that's the K1 thesis):
   - `breach_pressure` non-empty
   - `turn >= 4` (skip early-game edge-ping noise)
   - per-tile: `pressure >= 0.5` (signal-noise floor against decay)
   - per-tile: `0 <= y < 14` (must be in our half)

2. Sort tiles by pressure desc, take **top-5** to bound the candidate
   set.

3. For each qualifying tile, find legal turret positions within
   Chebyshev-2, prefer tiles ~2 cells inside our territory (not on the
   edge breach destination, not on our spawn lanes). Reuses the
   `_find_valid_turret_positions_near` helper (ported from J2).

4. Emit a defense template named `defense:first_breach_<x>_<y>` with:
   - 2 turrets near the breach tile (the K1 contribution)
   - `ANCHOR_TURRETS` skeleton (always-relevant baseline)
   - `INNER_CORNER_TURRETS` (corner-breach stop)
   - `WALL_ROW_Y12` (deflector)

5. Reject templates where SP gating skipped all K1 turrets (the
   template would collapse into the standard skeleton).

### 2.2 Integration: `enumerate_plans` (PREPEND, not append)

K1 templates are inserted at the front of the defense list. The
defense × offense expansion below caps at `max_plans=2500`, so trailing
defenses can be silently dropped — when a real breach signal exists,
K1 templates are likely the most relevant defense and must not be
dropped.

### 2.3 Search wiring

`oracle_core/search.py` now passes `breach_pressure` and `debug_log`
through to `enumerate_plans`. (One-line change mirroring what J2
already did.)

### 2.4 Constants

```python
K1_PRESSURE_MIN = 0.5     # signal-noise floor
K1_TURN_MIN = 4           # avoid early-game edge-ping noise
K1_TOP_TILES = 5          # cap unique tiles per turn
K1_RADIUS = 2             # Chebyshev radius for turret placement
K1_TURRETS_PER_TEMPLATE = 2
```

These are **signal characteristics**, not opponent profiles. Lowering
`K1_TURN_MIN` to 0 (no gate) regresses H2H mirrors badly (see §4.4);
the gate is justified by signal-stability reasoning, not by
opponent-tuning.

---

## 3. Why this is different from J2

| | J2 (`lane_block`) | K1 (`first_breach`) |
|---|---|---|
| Turn gate | `turn > 12` | `turn >= 4` |
| Peak pressure required | `>= 2.5` | `>= 0.5` |
| Concentration ratio gate | `peak/total >= 0.40` | none |
| Tile dimension | one primary tile only | top-5 unique tiles, one template each |
| Templates per turn | up to 2 | up to 5 |
| Fires at turn 1 if opponent breaches? | No | Yes (after turn 4) |

J2's gates were calibrated for **sustained concentrated pressure**
(e.g., Egil's 35-breach pattern). They explicitly reject **slow drip
attackers** because peak pressure stays below threshold. K1 inverts:
**any sustained breach pattern at any tile, however slow, gets a
covering template within 4 turns of first appearance.**

---

## 4. Validation results

All matches run on the local engine (`run_match.py` + `engine.jar`).
Reported results below are from K1 with the `K1_TURN_MIN=4` gate
(the un-gated prototype is in §4.4 for transparency).

### 4.1 Critical targeted test: snorkeldink-v3-2

**Goal: at least 1 win out of 6.**

| Side | Reps | Wins | Detail |
|---|---|---|---|
| K1 as P1 | 3 | **3/3** | turn 35-37, p_h margin 12-25 |
| K1 as P2 | 3 | **3/3** | turn 33-37 |
| **Total** | **6** | **6/6** | (vs VD's 0/6) |

The hard-counter is **fully resolved**. The K1 templates fire at
~35 emissions per match against v3-2; the search picks
`defense:first_breach_<tile>` as the best plan at multiple decision
points each game.

### 4.2 Tier A (must pass 100%)

| Opponent | K1 P1 | K1 P2 | Total |
|---|---|---|---|
| v13_second_ring | 1/1 | 1/1 | **2/2** |
| python-algo (starter kit) | 1/1 | 1/1 | **2/2** |
| heuristic_v1 | 1/1 | 1/1 | **2/2** |
| Lostkids/python-2l-aet | 1/1 | 1/1 | **2/2** |
| funnel_INTER (Midwest 2022) | 1/1 | 1/1 | **2/2** |
| **Tier A** | | | **10/10** |

### 4.3 Tier B (≥1/2 required)

| Opponent | K1 P1 | K1 P2 | Total |
|---|---|---|---|
| snorkeldink-v3-1 | 1/1 | 1/1 | **2/2** |

### 4.4 H2H baselines

| Opponent | K1 P1 | K1 P2 | Total |
|---|---|---|---|
| oracle_pure_M1Lite_VD_upload | 2/2 | 2/2 | **4/4** |
| oracle_pure_M1Lite_J2_upload | 2/2 | 2/2 | **4/4** |

K1 dominates both prior variants in head-to-head play.

### 4.5 Iteration: the un-gated K1 prototype

For honesty: my first K1 implementation had `K1_TURN_MIN=0` — fire
from turn 1 if any breach observed. Result:

- snorkeldink-v3-2: **6/6** (still works)
- Tier A: **10/10** (still works)
- **K1 vs VD: 0/4** (regressed from VD's mirror)

Investigation showed K1 was firing on benign turn-1 edge-pings — a
single MP=1 scout naturally lands at (12,1) or (0,13), generating a
phantom pressure that drove K1 to spend SP on covering templates
while VD took simpler `upg2` upgrades. By turn 30+ K1 was SP-behind
and lost the mirror.

The fix: **`turn >= 4` gate**. By turn 4 the early-game banking
phase is over, real attack patterns become established, and the gate
suppresses ~6 turns of phantom pressure decay. snorkeldink-v3-2's
drip-attack pattern still kicks in at turn 5+ so the gate doesn't
compromise the critical use case.

This is consistent with the prompt's iteration protocol §1: "If
Tier A regresses: K1 is over-reactive on early-game noise. Add a
`turn > 4` gate." The Tier A didn't actually regress (it only hit
mirror H2H), but the pathology was exactly the predicted one.

---

## 5. Telemetry sample

From a K1 vs snorkeldink-v3-2 match:

```
turn=4  K1 fires:  tile=(8,5) pressure=0.90 placed_turrets=[(9,6), (8,7)]
turn=11 best=defense:first_breach_8_5|offense:int3@20,6 score=403.5
turn=14 best=defense:first_breach_8_5|offense:int1@23,9 score=85.3
35 K1 emissions over the match; chosen as best plan at 4+ turns.
```

The mechanism fires AND the search picks the new templates — exactly
as designed.

---

## 6. Files changed vs VD

- `oracle_core/enumerator.py`:
  - Added module docstring describing K1.
  - Added constants `K1_PRESSURE_MIN`, `K1_TURN_MIN`, `K1_TOP_TILES`,
    `K1_RADIUS`, `K1_TURRETS_PER_TEMPLATE`.
  - Added `_find_valid_turret_positions_near` helper (port of J2's
    helper, simplified — no `extra_top_xys` since K1 generates one
    template per tile independently).
  - Added `_enumerate_first_breach_response_templates`.
  - Modified `enumerate_plans` signature: new kwargs
    `breach_pressure` and `debug_log`. Prepends K1 templates to the
    defense list when signal qualifies.
  - Updated `__all__`.

- `oracle_core/search.py`:
  - Updated module docstring.
  - Updated `search` docstring.
  - Pass `breach_pressure=breach_pressure, debug_log=debug_log` to
    `enumerate_plans` (mirroring J2's change).

- `algo_strategy.py`: **unchanged** (already maintains
  `breach_pressure` from VC era and passes it to `search`).

- `algo.json`, `run.sh`, `data/`, `gamelib/`, `bundled_sim_rs/`:
  unchanged.

---

## 7. Honest assessment

### What we know
- K1 demonstrably fires (telemetry confirms ~35 emissions per
  snorkeldink-v3-2 match).
- The search picks K1 templates as the best plan at multiple
  decision points per match (when they cross the value function's
  threshold).
- snorkeldink-v3-2 hard-counter is **resolved 6/6** (was 0/6).
- All Tier A and Tier B opponents pass.
- K1 beats both VD and J2 in mirror H2H (4/4 each).
- The `turn>=4` gate was discovered through actual testing, not
  theory. Without it, K1 regressed 0/4 vs VD.

### What we don't know
- **Live ladder behavior is unknown.** Local engine testing is a
  proxy; the live opponent population at the actual rank may include
  archetypes K1 has never faced.
- **Single-rep variance.** Most Tier A opponents got 1 rep per side.
  Citadel matches are largely deterministic, but a 6-rep replication
  on the most important opponents would be more rigorous.
- **K1 vs other drip-attackers.** The mechanism should generalize
  (the tile coordinates are derived from observation, not
  hardcoded), but no other observed slow-drip opponents were tested.
  If snorkeldink-v3-3 or similar variants exist on the ladder, K1
  should help — but this is unverified.
- **Compute cost is up.** K1 emits up to 5 extra defense templates
  per turn × ~50 offense plans = ~250 extra candidates. Total stays
  within `max_plans=2500` cap, but per-turn wall clock may rise
  slightly. Local turns observed at 0.4-2.0s (well under the 11s
  budget), but I didn't profile rigorously.

### Recommended next steps
1. **Ship K1.** It strictly improves vs VD on the most important
   benchmark while maintaining everything else. The trade-off is
   compute (~250 extra plans/turn, comfortably within budget) for
   substantial gain (snorkeldink hard-counter resolved).
2. After live-ladder data: if K1 proves equal-or-better on the
   ladder, retire VD as the canonical base and rebase further work
   off K1.
3. If K1 still has trouble on some opponents that didn't surface in
   local testing, consider raising `K1_TURN_MIN` further or
   tightening `K1_PRESSURE_MIN` — but only after live data confirms
   the regression is real, not a single-rep coincidence.
