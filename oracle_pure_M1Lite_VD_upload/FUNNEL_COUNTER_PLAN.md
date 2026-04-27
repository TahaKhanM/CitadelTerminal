# oracle_pure — Funnel Archetype Counter-Plan

**Scope:** narrow improvement plan focused exclusively on defeating the FUNNEL ARCHETYPE that beats oracle_pure on the live ladder.

**Live performance:** 3 instances (2179 / 2042 / 1860 ELO), 49W / 8L (86%).

**THE finding:** **8 of 8 losses are funnel-archetype attacks.** Every loss matches the same pattern despite varying algo names. Algo NAME is not archetype — `swap3`, `python-algo`, `python-algo-classic`, `babayaga2` are all funnels by behaviour.

This document has 4 sections:
1. Empirical funnel signature (what unifies all 8 losses)
2. Mechanical root cause (which oracle component fails and why)
3. The 6 concrete fixes, ranked by expected impact
4. Validation plan + what NOT to do

---

## 1. Empirical funnel signature

### 1.1 All 8 losses share THREE quantifiable features

For all 8 losses, opp's behavior matches:

**(a) Wall-channeled corridor** — opp invests **77-100% of walls** in y=14-20 (their own half between mid and front lines). This forms a CHANNEL that constrains where attackers can walk.

| Loss | Wall %, y=14-20 (T30 snapshot) |
|---|---|
| gencersarp/babayaga2 | high (28+ walls in zone) |
| aa0/funnel-a (×2) | high (29+ walls) |
| ameyg/funnel-rush-v7 | high (27 walls) |
| ashmit/funnel-crush-before | high (30 walls) |
| **aa0/swap3** | **24/31 = 77%** |
| **suchir-g/python-algo** | **28/29 = 97%** |
| **aa0/python-algo-classic** | **25/25 = 100%** |

**(b) Concentrated single-tile spawn** — opp launches **55-92% of all attackers from ONE tile** (typically (3,17) for left-corner demo funnels OR (13,27)/(14,27) for top-center scout funnels):

| Loss | Top spawn tile | % of all opp spawns |
|---|---|---|
| gencersarp | (3,17) | dominant — 76+85=161 demos+ints from (3,17) |
| aa0/funnel-a (×2) | (14,27) | dominant — 235+ scouts |
| ameyg/v7 | (14,27) + (7,21) harassment | combined dominant |
| ashmit | (14,27) | dominant — 50-scout killshot |
| **aa0/swap3** | **(3,17) — 64%** | 84/131 spawns |
| **suchir-g/python-algo** | **(3,17) — 92%** | 74/80 spawns (purest funnel) |
| **aa0/python-algo-classic** | **(13,27)+(14,27) — 92%** | 353 scouts |

**(c) Concentrated FLANK-CORNER breach** — **88-100% of HP loss** lands on **oracle's flank corner zone** (x ≤ 7 OR x ≥ 20, y ≤ 13):

| Loss | Top breach tile(s) | Flank concentration |
|---|---|---|
| gencersarp | (23,9) | 100% |
| aa0/funnel-a #1 | (3,10), (7,6), (4,9), (2,11) | 100% LEFT flank |
| aa0/funnel-a #2 | identical to #1 | 100% LEFT flank |
| ameyg/v7 | (2,11)=69%, (16,2)=23%, (1,12)=8% | 92% flank/corner |
| ashmit | (4,9) | 100% LEFT flank |
| **aa0/swap3** | **(25,11)** | **100% RIGHT flank** |
| **suchir-g/python-algo** | **(23,9)=88%, (22,8)=12%** | **100% RIGHT flank** |
| **aa0/python-algo-classic** | **(4,9)+(25,11)+(5,8)+(23,9)+(21,7)+(24,10)+(22,8)** | **100% flanks (rotating)** |

### 1.2 The funnel mechanic, in one sentence

> *Opp's wall corridor channels their attackers around oracle's center turret cluster (x=11-16) into oracle's flank corners (x ≤ 7 or x ≥ 20), where oracle has thin or zero turret coverage.*

This is **not a tactical accident** — every funnel opponent INTENTIONALLY routes attackers away from oracle's center and into the flanks. It's a known counter to center-anchor defenses.

### 1.3 Empirical coverage analysis: WHERE oracle is structurally weak

Computed: for each known breach tile across the 8 losses, count how many of oracle's 16 standard turret atoms (anchor + sidelane + outer + diag + inner-corner + outer-corner) reach that tile:

| Breach tile | Base-range cover (2.5) | Upgrade-range cover (3.5) | Status |
|---|---|---|---|
| ( 2,11) | 2 turrets | 4 turrets | COVERED |
| ( 3,10) | 2 | 2 | COVERED |
| ( 4, 9) | 2 | 3 | COVERED |
| ( 5, 8) | **1** | 3 | **THIN** |
| ( 7, 6) | **0** | 1 | **UPG-ONLY** |
| (20, 6) | **0** | 1 | **UPG-ONLY** |
| (21, 7) | **1** | 1 | **THIN** |
| (22, 8) | **1** | 3 | **THIN** |
| (23, 9) | 2 | 3 | COVERED |
| (25,11) | 2 | 4 | COVERED |
| ( 1,12) | 2 | 3 | COVERED |
| (16, 2) | **0** | **0** | **UNCOVERED** |

**Findings:**
- **4/12 breach tiles** have NO base-turret coverage in oracle's standard atom set (5,8 thin / 7,6 / 20,6 / 22,8 thin).
- **(16,2) is completely uncoverable** by any oracle atom — this is where ameyg/v7's T82 demolisher wave landed.
- **Even "COVERED" tiles get breached** because 2 base turrets × 6 dmg × 4 ticks = 48 dmg, only 3 scouts killed from a 30-scout wave. **Coverage ≠ defense; firepower density is what matters.**

### 1.4 The W vs L delta — when oracle DOES beat funnels, what's different?

Oracle won 9 funnel matches. The W vs L difference is mechanical, not strategic:

| Factor | Wins (n=9) | Losses (n=8) |
|---|---|---|
| Opp uses upgraded supports for shielding scouts | rare | **3/8 (aa0×2, ameyg/v7, ashmit)** |
| Opp banks MP for 30+ unit killshot | **rare** | **6/8** |
| Opp targets oracle's flanks (not just center) | rare | **8/8** |
| Match length | 38-77 turns | 51-99 turns (longer = opp had more time to bank) |
| Opp uses demolisher prep wave to destroy oracle's anchors | never | **2/8 (ameyg/v7, gencersarp)** |
| Final wave size | typically 8-15 units | **17-50 units (3-5× larger)** |

**Conclusion:** Oracle survives funnel attacks ≤15 units (its center-anchor + corner-turret defense kills them). It loses to funnel attacks of 20+ units because the FLANK COVERAGE GAP cannot kill that many in time.

---

## 2. Mechanical root cause (per-component)

### 2.1 Enumerator: defense templates lack flank-corridor coverage

**File:** `oracle_core/enumerator.py:33-92` (atom definitions), `:194-470` (templates).

**Problem 1 — atom set has no turret tiles for the breach corridor:**
- Oracle's atoms cover y=11 row (anchor/outer/diag) and y=13 corners (inner/outer corner).
- **NO atom places a turret at y=8-10 except (7,9) and (20,9).**
- Breach tiles (5,8), (7,6), (20,6), (22,8), (16,2) have either ZERO or ONE atom in their range.
- Even with `defense:t0_full` placing every atom we have, (7,6) is only covered by an upgraded (7,9) turret — single-coverage.

**Problem 2 — patch plan places only ONE turret per breach (lines 435-454):**
```python
candidates = [(TURRET_IDX, bx + 1, min(by + 1, 13)),
              (TURRET_IDX, bx, min(by + 2, 13))]
```
Even if patch1 fires for breach (4,9), it places a single turret at (5,10). 1 base turret × 4 ticks × 6 dmg = 24 dmg = 2 scouts killed from a 30-scout wave. **Mathematically insufficient.**

**Problem 3 — no template for "build SECOND-LAYER FLANK fortress":**
- No `defense:left_flank_corridor` or `defense:right_flank_corridor` template that places 3-4 turrets at `(2,8), (4,8), (5,9), (6,9)` plus walls in front.
- No template for opening pre-emptive demolisher-prep counter (e.g., walls at (2,12)/(3,12)/(4,12) up to upgraded HP=200 to absorb opp demos at (3,17) → bottom-right path).

### 2.2 Opponent model: never predicts the killshot

**File:** `oracle_core/opponent_model.py:292-356` (sample), `tools/build_opp_model.py:147,153` (prior builder).

**Problem 1 — prior has zero coverage of post-breach buckets:**
The prior was built with `recent_breaches=0` for every observation (`build_opp_model.py:147`). The bucket key includes `_band_breach()` which returns `br0/br1_2/br3p`. So **all 40 prior buckets are keyed `…|br0`**.

The moment oracle has been breached even once (which happens at T18-T26 in every loss), the bucket lookup MISSES the prior. `_sigs_for_bucket()` returns ONLY:
- `(empty, 0.5)`
- 5 hand-crafted scout signatures at `n_scouts_max = int(opp_mp)` (lines 322-340)
- 5 hand-crafted demo signatures at `n_demo = max(1, int(opp_mp/3))` (lines 348-356)

Top-K=6 sampling deterministically picks 6 of these 11 — and **never the 30+ scout killshot opp actually sends**. The largest scout sample = `int(opp_mp)` = ~15-25 max. The killshots are 32-50.

**Problem 2 — adversarial samples don't model opp's WALL+CHANNELING:**
The samples are pure mobile-spawn signatures. They don't say "opp will have built a wall corridor that channels these scouts to (4,9)." So when sim_rs evaluates a candidate, the opp's MOBILES are present but opp's WALLS aren't accurately predicted. Predicted breach locations are wrong → search picks wrong defense.

**Problem 3 — `recent_breaches` is just a COUNT, not the tile pattern:**
`algo_strategy.py:163` passes `self.recent_breaches[-6:]` to enumerator (used for patch plans). But the OPPONENT MODEL only sees `recent_breaches=len(self.recent_breaches)` (line 297) — a single integer. So the opp model can't say "user's last 5 breaches were all at left flank → predict more left-flank attacks." It treats "5 breaches at (4,9)" identically to "5 breaches scattered."

### 2.3 Value function: rewards aggregate, not coverage

**File:** `oracle_core/value.py:84-103` (`_structure_value_per_side`).

**Problem:** `_structure_value_per_side()` sums `coef × hp_frac` over ALL structures regardless of position. Two plans with the same SP cost are scored:

- `defense:upg2` (upgrade 2 anchor turrets): +24 struct value (2 × (12-4)) — no new spatial coverage
- `defense:patch1` (1 new base turret near breach tile): +4 struct value (1 × 4) — **6× lower static value**

Even when sim_rs predicts "patch1 prevents 1 scout from breaching" (saving 1 HP = +100 utility), the value function hands `upg2` an extra 20 utility for the static structure value. Combined with the BAD opp prediction (problem 2.2), the simulator UNDER-predicts how many scouts patch1 actually saves. Net: search picks `upg2` even when patch1 is the right move.

**Quantitative evidence:**
- vs ashmit T48: oracle saw a 7-HP breach at (4,9). vs T54-T76 (29 turns of opportunity), oracle made ZERO new structure placements within manhattan-5 of (4,9). Search persistently picked center-upgrade plans over flank-defense plans.
- vs gencersarp T18-T44: same pattern. Opp drilled (23,9) 18 times. Oracle never placed multiple turrets covering (23,9). Right-side defense structurally collapsed by T44.

### 2.4 Search: phase-1 with k_opp_phase1=1 hides the killshot

**File:** `oracle_core/search.py:233-258`, `algo_strategy.py:158`.

Phase-1 evaluates each candidate against ONE opp sample. With deterministic top-K sampling that one sample is "scout-center" (highest weight). The killshot scenario (30+ scouts at flank-targeted breach tile) is never in phase-1's sample. So plans that DEFEND against the killshot have no advantage in phase-1 ranking → they don't make top-30 → phase-2 never evaluates them.

This is why bumping `k_opp_phase1=2` regressed wins (per REPORT.md §3.3): we changed which samples were used WITHOUT fixing problem 2.2 (sample diversity). The fix needs to be coordinated.

---

## 3. The 6 fixes (ranked by expected funnel-loss conversion)

Each fix addresses a SPECIFIC root cause. Implement in order; validate after each.

### F1. Add flank-corridor defense templates [HIGHEST IMPACT]

**Addresses:** §2.1 (atom gap); converts losses where oracle has SP but no template to spend it on flank defense.

**Code change** in `oracle_core/enumerator.py`:

```python
# NEW atoms — flank corridor turrets that cover the actual breach tiles
# observed in 8/8 funnel losses. NOT hardcoded "always build" — they're
# OPTIONS the search picks when sim_rs evaluation against funnel samples
# (see F2) shows them best.

# Left flank corridor (covers breaches at (3,10)/(4,9)/(5,8)/(7,6)/(2,11))
LEFT_FLANK_CORRIDOR = [
    (TURRET_IDX, 3, 10),    # primary defender; covers (4,9), (3,10), (5,8) at upg-range
    (TURRET_IDX, 5, 9),     # second-row; covers (7,6), (5,8), (4,9) at upg-range
    (WALL_IDX, 2, 11),      # absorbs scouts attacking from (2,11)
    (WALL_IDX, 3, 11),
    (WALL_IDX, 4, 10),
    (TURRET_IDX, 6, 8),     # covers (7,6), (5,8) — fills the (7,6) gap
]
LEFT_FLANK_CORRIDOR_UPGRADES = [(3, 10), (5, 9), (6, 8)]

# Right flank corridor (mirror)
RIGHT_FLANK_CORRIDOR = [
    (TURRET_IDX, 24, 10),   # covers (23,9), (24,10), (25,11) at upg-range
    (TURRET_IDX, 22, 9),    # covers (22,8), (20,6), (21,7) at upg-range
    (WALL_IDX, 25, 11),
    (WALL_IDX, 24, 11),
    (WALL_IDX, 23, 10),
    (TURRET_IDX, 21, 8),    # fills the (20,6) gap
]
RIGHT_FLANK_CORRIDOR_UPGRADES = [(24, 10), (22, 9), (21, 8)]

# Anti-demo-funnel left (counters opp's (3,17) → (23,9) demo path
# by placing walls + upgraded turrets ALONG the corridor)
ANTI_DEMO_FUNNEL_RIGHT = [
    # Walls along the demolisher's likely path through y=12 row
    (WALL_IDX, 18, 12), (WALL_IDX, 19, 12), (WALL_IDX, 20, 12),
    (WALL_IDX, 21, 12), (WALL_IDX, 22, 12), (WALL_IDX, 23, 12),
    (WALL_IDX, 24, 12), (WALL_IDX, 25, 12),
    # Upgraded turrets behind the walls (one-shot demos at 5HP)
    (TURRET_IDX, 22, 11), (TURRET_IDX, 23, 11), (TURRET_IDX, 24, 11),
]
ANTI_DEMO_FUNNEL_RIGHT_UPGRADES = [(22, 11), (23, 11), (24, 11)]
ANTI_DEMO_FUNNEL_LEFT = [
    (WALL_IDX, 2, 12), (WALL_IDX, 3, 12), (WALL_IDX, 4, 12),
    (WALL_IDX, 5, 12), (WALL_IDX, 6, 12), (WALL_IDX, 7, 12),
    (TURRET_IDX, 4, 11), (TURRET_IDX, 5, 11), (TURRET_IDX, 7, 11),
]
ANTI_DEMO_FUNNEL_LEFT_UPGRADES = [(4, 11), (5, 11)]


# In _enumerate_defense_templates, add these as conditional templates:
def _enumerate_defense_templates(game_state, config, sp_budget,
                                 recent_breaches, ...):
    ...
    # Compute breach side bias from recent_breaches
    left_breach_count = sum(1 for x, y in recent_breaches if x <= 7)
    right_breach_count = sum(1 for x, y in recent_breaches if x >= 20)
    
    # ALWAYS include both flank corridors as candidates (the search
    # will only pick them if sim_rs evaluation shows them best).
    p = _build_defense_plan(
        "defense:left_flank_corridor",
        LEFT_FLANK_CORRIDOR + ANCHOR_TURRETS,
        game_state, config, sp_budget,
        upgrades=LEFT_FLANK_CORRIDOR_UPGRADES,
        archetype="left_flank",
    )
    if p: plans.append(p)
    
    p = _build_defense_plan(
        "defense:right_flank_corridor",
        RIGHT_FLANK_CORRIDOR + ANCHOR_TURRETS,
        game_state, config, sp_budget,
        upgrades=RIGHT_FLANK_CORRIDOR_UPGRADES,
        archetype="right_flank",
    )
    if p: plans.append(p)
    
    # Anti-demo funnel templates (against opp's (3,17) → flank drill)
    p = _build_defense_plan(
        "defense:anti_demo_right",
        ANTI_DEMO_FUNNEL_RIGHT + ANCHOR_TURRETS,
        game_state, config, sp_budget,
        upgrades=ANTI_DEMO_FUNNEL_RIGHT_UPGRADES,
        archetype="anti_demo",
    )
    if p: plans.append(p)
    
    p = _build_defense_plan(
        "defense:anti_demo_left",
        ANTI_DEMO_FUNNEL_LEFT + ANCHOR_TURRETS,
        game_state, config, sp_budget,
        upgrades=ANTI_DEMO_FUNNEL_LEFT_UPGRADES,
        archetype="anti_demo",
    )
    if p: plans.append(p)
```

**Why this is structural, not hardcoded:** these are CANDIDATES the search evaluates. Against non-funnel opps, sim_rs scores them lower than `defense:t0_full` and they don't get picked. Against funnel opps (after F2 makes the opp model predict the killshot correctly), they score highest because they actually save HP.

**Estimated impact:** +30-50 ELO. **Effort:** 4-6 hours including local validation. **Risk:** adds ~4 plans → minor enumeration cost; mitigated by F4 below (priority sort).

### F2. Make opp model PREDICT the killshot [HIGH IMPACT, COUPLED WITH F1]

**Addresses:** §2.2 (opp model never samples 30+ unit waves).

**Code change** in `oracle_core/opponent_model.py:292-356`:

```python
# Modify the diverse-adversarial-sample injection.

# Two changes:
# (a) When opp_mp >= 13 (banking signal), inject MAX-MP single-tile
#     killshot scenarios with high weight. These are the funnel patterns
#     we lose to.
# (b) When recent_breaches has TILES (not just count), inject signatures
#     SIDE-MATCHING the historical breach pattern.

# Need to thread recent_breach_TILES (not just count) through sample().
# Update algo_strategy.py:163:
#   recent_breaches=self.recent_breaches[-6:]  # already a list of tiles
# And in opp_model.sample signature, accept it.

def sample(self, game_state, *, our_mp, opp_mp, recent_breaches=0,
           recent_breach_tiles=None, k=8, opp_player=2, config=None):
    ...
    sigs_with_weights = self._sigs_for_bucket(bk)
    sigs_with_weights.append((ActionSignature(), 0.5))
    
    # === KILLSHOT predictor ===
    # When opp banks (mp >= 13), they're queueing a 30+ unit wave.
    # The funnel pattern from 8/8 losses: MAX-MP single-tile spawn.
    if opp_mp >= 13:
        n_scouts_killshot = int(opp_mp)  # All MP into scouts
        # Inject ALL 5 launchers with HIGH weight — force search to
        # consider every direction the killshot could come from.
        for launcher in ("center", "L_corner", "L_mid", "R_corner", "R_mid"):
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_killshot,
                                 scout_launcher=launcher), 5.0)
            )
        # Demolisher killshot too (8+ demos at MP/3)
        n_demos_killshot = int(opp_mp / 3)
        if n_demos_killshot >= 6:
            for launcher in ("L_corner", "R_corner", "center"):
                sigs_with_weights.append(
                    (ActionSignature(demo_count=n_demos_killshot,
                                     demo_launcher=launcher), 4.0)
                )
    
    # === SIDE-BIASED prediction from breach history ===
    # If recent breaches are concentrated on one side, opp is funneling there.
    # Predict more attacks SAME-SIDE.
    if recent_breach_tiles:
        left_breaches = sum(1 for x, y in recent_breach_tiles if x <= 7)
        right_breaches = sum(1 for x, y in recent_breach_tiles if x >= 20)
        if left_breaches >= 2:
            # Triple-weight all left-side opp launchers (bottom edges
            # mirror to opp's TR/center spawns that route to our left)
            for launcher in ("center",):  # opp's center spawn → our left flank
                sigs_with_weights.append(
                    (ActionSignature(scout_count=int(opp_mp),
                                     scout_launcher=launcher), 6.0)
                )
        if right_breaches >= 2:
            for launcher in ("L_corner",):  # opp's L_corner → our right flank
                sigs_with_weights.append(
                    (ActionSignature(scout_count=int(opp_mp),
                                     scout_launcher=launcher), 6.0)
                )
    
    # ... rest unchanged ...
```

Also update `algo_strategy.py:163` to pass `recent_breach_tiles=self.recent_breaches[-6:]` and update `search.py` to forward through.

**Estimated impact:** +20-40 ELO. **Effort:** 6-8 hours. **Risk:** changes the predicted-attack landscape; F1 templates need to be in place to provide defensive options.

### F3. Re-build opponent prior with breach context [MEDIUM-HIGH IMPACT]

**Addresses:** §2.2 (prior covers only `br0` buckets).

**Code change** in `tools/build_opp_model.py`:

```python
# Track running breach counts as we walk through replay frames.
# Update bucket_key to include actual breach count.
running_breaches_against_p1 = 0
running_breaches_against_p2 = 0
for t in sorted(action_frames):
    # Use CURRENT count for bucket lookup
    bk_p1 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                       recent_breaches=running_breaches_against_p1)
    bucket_counters[bk_p1][sig_opp_against_p1] += 1
    bk_p2 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                       recent_breaches=running_breaches_against_p2)
    bucket_counters[bk_p2][sig_opp_against_p2] += 1
    # Update running counts AFTER recording
    for b in events.get('breach', []):
        owner = int(b[4])
        if owner == 1:
            running_breaches_against_p2 += 1
        else:
            running_breaches_against_p1 += 1
# Then re-run on all 427 ranked replays + the 20 oracle_pure live replays
```

**Estimated impact:** +10-20 ELO (compounds with F2). **Effort:** 1 hour to rewrite + 10 minutes to re-run.

### F4. Add archetype-flag fast-path in search [MEDIUM IMPACT]

**Addresses:** §2.4 (phase-1 with k=1 hides killshot).

**Code change** in `oracle_core/search.py`: detect "funnel-archetype game" by lightweight heuristic on observed opp behavior, then bump `k_opp_phase1=3` only for those games. This protects the proven config for non-funnel opps.

```python
# In algo_strategy.on_turn:
opp_funnel_signal = (
    len(self.recent_breaches) >= 3 and
    # Most breaches concentrated on flank
    sum(1 for x, y in self.recent_breaches if x <= 7 or x >= 20) >= 2
)

result = search(
    gs, self.config, self.opp_model,
    time_budget_s=SEARCH_BUDGET_S,
    k_opp=8 if opp_funnel_signal else 6,
    k_opp_phase1=2 if opp_funnel_signal else 1,
    phase2_top_n=40 if opp_funnel_signal else 30,
    do_depth2=True,
    depth2_top_n=4 if opp_funnel_signal else 3,
    max_plans=2500,
    recent_breaches=self.recent_breaches[-6:],
    debug_log=lambda m: gamelib.debug_write(m),
)
```

**Why narrow:** the previous experiment of `k_opp_phase1=2` regressed FUNNEL-WIN matches. The fix is to bump diversity ONLY when the game is genuinely a funnel (per breach signal), not always.

**Estimated impact:** +10-15 ELO. **Effort:** 2 hours. **Risk:** false-positive funnel detection → regress non-funnel matches; mitigated by requiring 3+ breaches AND flank concentration.

### F5. Re-shape patch plan to a CORRIDOR (3-4 turrets), not a single turret [MEDIUM IMPACT]

**Addresses:** §2.1 problem 2 (patch1 mathematically insufficient against killshots).

**Code change** in `oracle_core/enumerator.py:435-454`:

```python
# Replace single-turret patch with a CORRIDOR patch
if recent_breaches:
    breach_counter = Counter(recent_breaches)
    for (bx, by), count in breach_counter.most_common(2):
        if count < 2: continue  # only patch HOT tiles
        # Build a 3-turret + 2-wall corridor covering the breach
        if bx < 14:  # left flank
            corridor_atoms = [
                (TURRET_IDX, bx + 1, by + 1),
                (TURRET_IDX, max(bx - 1, 0), by + 2),
                (TURRET_IDX, bx + 2, by),
                (WALL_IDX, bx, max(by - 1, 0)),
                (WALL_IDX, bx, by + 1),
            ]
        else:  # right flank
            corridor_atoms = [
                (TURRET_IDX, bx - 1, by + 1),
                (TURRET_IDX, min(bx + 1, 27), by + 2),
                (TURRET_IDX, bx - 2, by),
                (WALL_IDX, bx, max(by - 1, 0)),
                (WALL_IDX, bx, by + 1),
            ]
        # Upgrade the corridor turrets — vital for the math
        upg_atoms = [(t[1], t[2]) for t in corridor_atoms if t[0] == TURRET_IDX]
        p = _build_defense_plan(
            f"defense:corridor_at_{bx}_{by}",
            corridor_atoms + ANCHOR_TURRETS,
            game_state, config, sp_budget,
            upgrades=upg_atoms,
            archetype="corridor_patch",
        )
        if p: plans.append(p)
```

**Math check:** at breach (4,9), corridor places (5,10) + (3,11) + (6,9) + walls. 3 upgraded turrets × 4 ticks × 20 dmg = 240 dmg = ~20 scouts killed. Vs 30-scout wave: 10 breach. **3× better than current patch1 (which kills 2).**

**SP cost:** 3 turrets (6 SP) + 3 upgrades (12 SP) + 2 walls (2 SP) = 20 SP. Realistic at mid-game when oracle has accumulated SP.

**Estimated impact:** +10-15 ELO. **Effort:** 3 hours. **Risk:** SP-heavy plan (20 SP); only fires when oracle has the budget; not always available.

### F6. Track and refuse repeated-failed offense (intra-game learning) [MEDIUM IMPACT]

**Addresses:** documented in §2.3 evidence — oracle keeps spending MP on offense plans that yield 0 dmg/MP for many turns.

**Code change** — add `OffenseEffectivenessTracker` to `oracle_core/`, integrate into value function:

```python
# oracle_core/offense_tracker.py (NEW)
class OffenseEffectivenessTracker:
    """Tracks our offensive plan effectiveness intra-game."""
    def __init__(self):
        self.plan_history: dict[str, list[float]] = {}  # archetype -> list of dmg/MP
    def record(self, archetype: str, mp_spent: float, hp_dealt: float):
        ratio = hp_dealt / max(1.0, mp_spent)
        self.plan_history.setdefault(archetype, []).append(ratio)
    def expected_dmg_per_mp(self, archetype: str, fallback: float = 0.5) -> float:
        history = self.plan_history.get(archetype)
        if not history or len(history) < 2:
            return fallback
        # Recent-weighted average
        weights = [0.7 ** (len(history) - 1 - i) for i in range(len(history))]
        return sum(r*w for r,w in zip(history, weights)) / sum(weights)

# In value.py.evaluate(), accept offense_tracker, apply penalty:
if plan.has_mobiles() and offense_tracker is not None:
    expected = offense_tracker.expected_dmg_per_mp(plan.archetype)
    if expected < 0.1 and plan.mp_cost >= 3:
        # Penalty for plans of an archetype that's been failing
        score -= 50 * (plan.mp_cost / 5.0)
```

In `algo_strategy.on_action_frame`, when a breach by US fires, attribute it to the most-recent OUR-spawned plan archetype.

**Estimated impact:** +10-15 ELO. **Effort:** 6-8 hours. **Risk:** misattribution if multiple plans in flight; mitigate with simple "last plan wins."

---

## 4. Implementation order + validation

### Order

1. **F3** (rebuild prior) — 1 hour, no risk; data improvement only.
2. **F1** (flank corridor templates) — 4-6 hours; opens defensive options.
3. **F2** (killshot predictor + side-biased samples) — 6-8 hours; makes search SEE the threat.
4. Validate locally vs the 5 funnel-loss replays via `test_algo_mac` (does the search now pick `defense:left_flank_corridor` or similar at the tilt turn?). Run full local ladder (must maintain 10/10 on bar opponents).
5. Upload to live; play 10 ranked matches; check funnel-loss conversion.
6. **F5** (corridor patch) — 3 hours.
7. **F4** (archetype fast-path) — 2 hours.
8. **F6** (offense tracker) — 6-8 hours.
9. Re-validate after each, with the funnel-loss replays as the golden test.

### Validation gates

For each fix:
- **Local component test**: re-run `tests/test_components.py` — all pass.
- **Local ladder**: 10/10 wins both sides on bar opponents (v13_second_ring, python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER).
- **Funnel-replay regression**: feed the 5 funnel loss states (extracted at the tilt turn) into the search and verify the BEST plan now includes a flank corridor or anti-demo template. Specifically: at gencersarp T20, search should pick `defense:right_flank_corridor` or `defense:anti_demo_right`; at ashmit T48, it should pick `defense:left_flank_corridor`.
- **Live ladder**: 10 ranked matches after each Phase 1 fix. Funnel-archetype losses should drop from 5/5 historical to ≤1/5 expected.

### What this plan estimates

If F1+F2+F3 land cleanly: **convert at least 4-5 of the 8 funnel losses to wins**. ELO impact: +60-90 (oracle_pure jumps from ~2050 average across 3 instances toward 2200+).

If F1-F6 all land: **convert 6-7 of 8 funnel losses**. The remaining 1-2 (likely ameyg/v7 if opp escalates demo prep + killshot in a way our prior still doesn't predict) require the deeper Tier-3 fixes from IMPROVEMENT_PLAN.md (multi-turn skeleton planning, archetype classifier).

### What NOT to do

These are tempting but WRONG approaches:

- ❌ **Hardcode "always build turret at (4,9), (5,8), (23,9), (25,11)"** — works against the 8 known losses but degrades vs non-funnel opps and won't generalize to a funnel attacking different tiles.
- ❌ **Always pick `defense:left_flank_corridor`** — same problem; it's a CANDIDATE, search must choose it based on sim_rs evaluation.
- ❌ **Bump `k_opp_phase1=3` globally** — already tested in REPORT.md §3.3; regressed wins. F4 makes it conditional.
- ❌ **Add a "funnel detector" that pivots strategy** — that's a heuristic switch, the failure mode the project explicitly rejects. F4 only TUNES search params on signal; it doesn't pick plans.
- ❌ **Build supports as a generic anti-funnel** — supports help our offense (shields scouts) but don't address the DEFENSIVE flank gap. The losses are defensive, not offensive.

### What success looks like

After F1+F2+F3 (Phase 1 of this plan):
- Replaying gencersarp's loss: at T20, oracle's debug log shows `best=defense:right_flank_corridor|offense:scout4@2,11` (or similar) instead of `defense:upg2|offense:demo1@13,0`.
- Replaying aa0/funnel-a: at T26, oracle picks `defense:left_flank_corridor|offense:scout5@(2,11)`.
- Replaying ashmit/funnel-crush-before: at T48 (after the first 7-HP breach), oracle picks `defense:corridor_at_4_9|offense:hold` or similar to fortify the left flank instead of the historical `defense:upg2`.
- Live ladder: oracle's 3 instances stop losing to aa0/funnel-a, suchir-g/python-algo, ashmit/funnel-crush-before. ameyg/funnel-rush-v7 may still be marginal (50-MP killshot is hard).

---

## Appendix A — Breach concentration data (full)

```
LOSS                                    Opp top spawn    Top breach    Single-tile %
gencersarp/babayaga2 (51t)              (3,17) demo      (23,9)         100% (40/40 HP)
aa0/funnel-a #1 (58t)                   (14,27) scout    (3,10)         43%, top-4 = 100%
aa0/funnel-a #2 (58t)                   (14,27) scout    same as #1     identical
ameyg/funnel-rush-v7 (99t)              (14,27)+harass   (2,11)         69%, top-3 = 100%
ashmit/funnel-crush-before (77t)        (14,27) scout    (4,9)          100% (50/50 HP)
aa0/swap3 (55t)                         (3,17) demo      (25,11)        100% (43/43 HP)
suchir-g/python-algo (46t)              (3,17) demo      (23,9)         88%, top-2 = 100%
aa0/python-algo-classic (60t)           (13,27)+(14,27)  (4,9)          41%, top-3 = 82%
```

## Appendix B — Three funnel sub-archetypes oracle loses to

1. **Left-corner DEMO funnel → right-flank breach:** opp spawns at (3,17), demos walk diagonally, breach at (23,9)/(25,11). Variants: gencersarp, aa0/swap3, suchir-g.
2. **Top-center SCOUT funnel → left-flank breach:** opp spawns at (13-14,27), scouts walk through opp's wall corridor, breach at (4,9)/(3,10)/(7,6). Variants: aa0/funnel-a (×2), ashmit, aa0/python-algo-classic.
3. **Mixed escalating demo+scout killshots:** opp uses demo prep waves to destroy oracle's anchor turrets, then scout killshot. Variants: ameyg/funnel-rush-v7.

The flank-corridor templates (F1) provide defense for sub-archetypes 1+2. Anti-demo-funnel templates (F1) address sub-archetype 1's structure-destruction phase. Killshot prediction (F2) makes the search SEE all three.
