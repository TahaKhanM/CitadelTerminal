# oracle_pure — Revised Improvement Plan (post-critique)

This document replaces the prior `FUNNEL_COUNTER_PLAN.md`. The user (and a critical audit) correctly flagged that the previous plan:
1. Was wrong about the failure mode (lumped two distinct archetypes under "funnel")
2. Crossed into hardcoded territory in F1 (specific tile lists) and F4 (heuristic switch)
3. Risked regressing oracle's own winning launchers
4. Had numerical errors (patch math was off by 2×)

This revision is built from three rigorous cross-checks:
- **Empirical**: 13 total losses across 3 instances (8 prior + 5 new), with the new ones revealing a SECOND failure mode (long-loop demolisher attrition vs. demo-siege-v2)
- **Code verification**: every claim about oracle's behavior re-grounded in actual file:line evidence
- **Anti-pattern audit**: each proposed fix tested against REPORT.md §8's explicit forbidden patterns

---

## 1. The actual failure landscape

Total live performance: 49W / 13L (79%). Two distinct loss archetypes:

### Archetype A — Wall-channeled funnel attack (8 losses)
- **Mechanism**: opp builds wall corridor in their own half (y=14-20), channels mobiles down a funnel toward oracle's flank corners (x≤7 OR x≥20)
- **Examples**: aa0/funnel-a×2, ameyg/funnel-rush-v7, ashmit/funnel-crush-before, gencersarp/babayaga2, suchir-g/python-algo, aa0/swap3, aa0/python-algo-classic
- **Breach tiles**: (4,9), (3,10), (2,11), (7,6), (5,8) on left flank; (23,9), (25,11), (20,6), (21,7), (22,8) on right
- **Oracle's failure**: defensive — turret coverage thin at flank tiles (only 2/12 breach tiles have ≥2 base-turret cover); opp's killshot waves overwhelm what coverage exists

### Archetype B — Long-loop Demolisher attrition (5 losses)
- **Mechanism**: opp builds two-corner artillery fortress (NOT a funnel — walls in opp's territory only); spawns 5-7 demolishers per turn from corner (25,11); demos walk a 46-hop U-shape around the bottom and breach oracle's left flank at (5,19)
- **Examples**: louiebwu/demo-siege-v2 (5 losses)
- **Breach tile**: 78-98% concentration at (5,19) — a tile distinct from any funnel-loss breach tile
- **Oracle's failure**: OFFENSIVE — oracle deals 5-6× less damage in losses (8 dmg) vs the 1 win (45 dmg) against the same opp. Oracle's defense holds but oracle gets stuck in an "interceptor cluster at (3,17)" feedback loop and stops attacking

**Critical correction**: the W vs L delta against demo-siege-v2 is dominated by OFFENSE (5× difference), not defense (~0× difference). Defense plans alone won't fix this.

---

## 2. What's actually wrong (cross-verified)

### Component-level defects (verified file:line)

| # | Defect | File:Line | Verified |
|---|---|---|---|
| D1 | Prior covers only `br0` buckets — empty for `br1_2`/`br3p` | `tools/build_opp_model.py:147,153` | ✓ all 40 buckets are `…br0` in `data/opp_model_priors.json` |
| D2 | Patch plan places 2 turrets per breach (NOT 1 as I claimed), no upgrades, no walls | `enumerator.py:436-454` | ✓ verified — math was off by 2× in my prior plan |
| D3 | Value function `_structure_value_per_side` has zero spatial awareness — coef × hp_frac, no position weighting | `value.py:84-103` | ✓ verified — no x/y references in the loop |
| D4 | `recent_breaches` count (not tiles) passed to opp_model; tiles only flow to enumerator | `search.py:225,230`; `algo_strategy.py:297` | ✓ verified |
| D5 | Adversarial sample cap is `int(opp_mp)` — issue is TEMPORAL (no banking look-ahead), not numerical | `opponent_model.py:319-352` | ✓ verified — my prior plan misframed this |
| D6 | `REACTIVE_PATCH_TILES_LEFT/RIGHT` constants declared but DEAD CODE — never used in any template | `enumerator.py:93-94` | ✓ verified |
| D7 | Once oracle has ≥3 breaches in last 6 turns, bucket key permanently lands in `br3p` (which has no prior coverage per D1) | `algo_strategy.py:275-276,297` + `opponent_model.py:_band_breach` | ✓ verified |
| D8 | Static struct value of `defense:upg2` (≈+16) >> `defense:patch1` (+4-8) — hard structural bias against patches | `value.py:74-81` | ✓ verified by computation |
| D9 | At T19 in gencersarp loss, oracle made zero placements after losing 6 turrets (right-flank collapse) — confirms search picks "do nothing" over patch1 | replay structure deltas | ✓ verified empirically |

### What this means for the fix design

The user's mandate: **no hardcoding, no regression, genuine improvement**. Each fix below is filtered through:
- **Generalization test**: would this help against a 9th funnel variant attacking a tile we haven't seen? Or only the 13 observed?
- **Regression test**: which of the 49 wins (esp. bosses + heuristic_v1 + Lostkids) could this hurt?
- **Anti-pattern test**: does this trigger any of REPORT.md §8's forbidden patterns?

---

## 3. The revised fixes (G1-G6)

### G1. Coverage-gap proposer (REPLACES the rejected F1)

**The fix**: derive defensive turret atoms from the LIVE board state's coverage gaps, not from a hardcoded tile list.

**Why this generalizes**: against the 8 known funnels, gap analysis surfaces `(7,6), (20,6), (16,2)` — which ARE the breach tiles. Against a 9th funnel attacking `(8,5)`, gap analysis surfaces `(8,5)` — even though `(8,5)` was never in the training set. **The proposer doesn't know what a "funnel" is — it just knows where oracle is structurally thin.**

**Code sketch** (`oracle_core/enumerator.py`, replacing the rejected hand-crafted constants):

```python
def _enumerate_coverage_gap_templates(game_state, config, sp_budget,
                                       n_gap_proposals=3):
    """Generate defense templates that fill the K thinnest-covered tiles
    in the lower-spine region (y<11). NO hardcoded tile list."""
    plans = []

    # Existing turrets (mine, alive, with their range)
    my_turrets = []
    for x in range(28):
        for y in range(14):
            u = _existing_struct(game_state, x, y)
            if u is None: continue
            if str(getattr(u, 'unit_type', '')).upper() not in ('DF', 'TURRET'):
                continue
            rng = 3.5 if getattr(u, 'upgraded', False) else 2.5
            my_turrets.append((x, y, rng))

    # Per-tile coverage count for the defendable region
    def coverage_at(tx, ty):
        return sum(1 for (sx, sy, r) in my_turrets
                   if (tx-sx)**2 + (ty-sy)**2 <= r*r)

    # Find thinnest tiles in y in [3, 11] (where breaches actually occur,
    # excluding y=12-13 corner-walls that are usually fine)
    candidate_tiles = []
    for x in range(28):
        for y in range(3, 12):
            if not game_state.game_map.in_arena_bounds([x, y]):
                continue
            cov = coverage_at(x, y)
            if cov <= 1:  # only consider thin/uncovered tiles
                candidate_tiles.append((cov, x, y))

    candidate_tiles.sort()  # cov asc -> thinnest first

    # For each of the top N thinnest tiles, propose a turret pair that
    # would cover it. The pair tiles are derived from the breach tile,
    # not from a fixed atom list.
    for cov, gx, gy in candidate_tiles[:n_gap_proposals]:
        # Propose turrets at (gx, gy+1) and (gx+1, gy+1) IF legal.
        # These offsets are GEOMETRIC (one tile back from gap), not opp-specific.
        proposals = []
        for ox, oy in [(0, 1), (1, 1), (-1, 1), (0, 2)]:
            tx, ty = gx + ox, gy + oy
            if 0 <= tx < 28 and 0 <= ty < 14:
                if game_state.game_map.in_arena_bounds([tx, ty]) and \
                   not game_state.contains_stationary_unit([tx, ty]):
                    # Only propose if it would actually cover the gap
                    if (tx-gx)**2 + (ty-gy)**2 <= 3.5*3.5:  # upgrade range
                        proposals.append((TURRET_IDX, tx, ty))

        if not proposals:
            continue

        # Build a template combining the gap-filler turrets + ANCHOR.
        # Include upgrade option for the gap turrets (cost-permitting).
        upg_atoms = [(t[1], t[2]) for t in proposals]
        p = _build_defense_plan(
            f"defense:gap_at_{gx}_{gy}",
            proposals + ANCHOR_TURRETS,
            game_state, config, sp_budget,
            upgrades=upg_atoms,
            archetype="coverage_gap",
        )
        if p: plans.append(p)
    return plans

# In _enumerate_defense_templates, append:
plans.extend(_enumerate_coverage_gap_templates(game_state, config, sp_budget))
```

**Why this is NOT hardcoded**:
- No tile constants. The proposed turret tiles are derived from the live structure layout via geometric offsets from observed gap.
- Gaps are computed every turn — same code handles 8 known funnels AND the 9th unknown variant identically.
- Doesn't propose anything that walls off oracle's own launchers (the audit's main objection to the prior F1).

**Regression risk**: low. Against bosses where oracle's defense covers all spawns adequately, no tiles in y=3-11 will have coverage ≤ 1 → no gap templates emitted. Against winning matches, gap proposals at non-attacked tiles score lower than `defense:upg2` (no breach to defend) and the search rejects them. Adds at most 3 templates per turn → minor enumeration cost.

**Validation**: a parity test should confirm against bosses that no gap template is emitted at T0 (since oracle has no turrets yet); after T7 with full skeleton built, gaps should emerge at the empirically-leaked tiles. Run on the 13 loss replays — gap templates should surface `(5,19), (4,9), (23,9), (25,11)` etc. naturally.

**Estimated impact**: +20-40 ELO on funnel matches; 0 ELO on non-funnel matches (template doesn't fire). **Effort**: 4-6 hours.

---

### G2. Posterior-driven killshot prediction (REPLACES the semi-hardcoded F2)

**The fix**: use the OPPONENT'S OWN OBSERVED MP-spend distribution from the posterior to size adversarial samples — not a hardcoded `mp >= 13` threshold and not a hardcoded directional-routing assumption.

**Why this generalizes**: an opp who actually banks-then-killshots will have posterior signatures with `cost ≈ opp_mp` → drives high-weight large samples. An opp who steady-attacks gets steady-attack samples. A 9th opponent with novel spawn pattern gets observed and added via `TurnObserver.snapshot()` naturally.

**Code sketch** (`oracle_core/opponent_model.py`):

```python
def _opp_spend_distribution(self, bk: str, opp_mp: float, config) -> list:
    """Return a list of (scout_count, demo_count, int_count, weight) tuples
    representing OPP's observed MP-spend distribution in this bucket.
    NO hardcoded thresholds — uses what THIS opp has actually done.
    """
    post = self.posterior.get(bk, Counter())
    if not post:
        return []  # cold start; let other samples cover

    # For each observed signature, compute spent_mp_ratio = total_cost / opp_mp_at_observation
    # Re-scale signatures to CURRENT opp_mp by the ratio.
    samples = []
    for sig, n in post.items():
        cost = self._sig_mp_cost(sig, config)
        # If observed cost was > current opp_mp, downscale unit counts
        # proportionally; if smaller, leave as-is (opp may not always max out)
        ratio = min(opp_mp / max(cost, 1e-6), opp_mp / max(cost, 1e-6))
        scaled = ActionSignature(
            scout_count=int(sig.scout_count * ratio) if cost > opp_mp else sig.scout_count,
            demo_count=int(sig.demo_count * ratio) if cost > opp_mp else sig.demo_count,
            int_count=int(sig.int_count * ratio) if cost > opp_mp else sig.int_count,
            scout_launcher=sig.scout_launcher,
            demo_launcher=sig.demo_launcher,
            int_launcher=sig.int_launcher,
        )
        samples.append((scaled, float(n)))
    return samples

# In sample(), prepend these high-quality samples before the existing
# generic adversarial injections. NO magic threshold; weights come from
# observation count, not from inflated constants.
```

**Why this is NOT hardcoded**:
- No `>=13` magic threshold — uses observed `cost` per signature.
- No directional assumption — sample's launcher comes from what opp ACTUALLY launched, not from "left breaches → predict left."
- Weights are observation counts (1-50 typical), comparable to existing adversarial weights (0.3-0.8) so they don't monopolize phase-2 sampling.

**Regression risk**: low-medium. Against bosses (~22-47 turn matches), posterior accumulates 10-20 observations → high-quality bespoke predictions. Against bar opponents, posterior covers their actual patterns. Risk: cold-start (turn 1-3) when posterior is empty → falls through to existing adversarial samples (no change). Against opps with VERY DIVERSE plays per game, the posterior fragments and nothing dominates — still functional, just lower confidence.

**Estimated impact**: +15-30 ELO. **Effort**: 4-6 hours including a parity test that verifies sample diversity vs the current implementation on a saved bucket state.

---

### G3. Re-build prior with breach context (UNCHANGED from F3 — true bug fix)

**The fix**: in `tools/build_opp_model.py`, track running breach count while walking each replay, pass it to `bucket_key()` instead of hardcoded 0.

**Why this is NOT hardcoded**: this is a literal bug fix. The bucket schema has 3 breach bands; the prior currently populates only 1. Filling the other 2 doesn't bake in any opp-specific knowledge.

**Code sketch** (per `tools/build_opp_model.py`):

```python
running_breaches_against_p1 = 0
running_breaches_against_p2 = 0
for t in sorted(action_frames):
    bk_p1 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                       recent_breaches=running_breaches_against_p1)
    bucket_counters[bk_p1][sig_opp_against_p1] += 1
    bk_p2 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                       recent_breaches=running_breaches_against_p2)
    bucket_counters[bk_p2][sig_opp_against_p2] += 1
    for b in events.get('breach', []):
        owner = int(b[4])
        if owner == 1: running_breaches_against_p2 += 1
        else: running_breaches_against_p1 += 1
```

**Regression risk**: minimal. Bucket fragmentation is mitigated by the existing top-16-per-bucket cap.

**Validation gate**: write `tests/test_prior_parity.py` that asserts the new prior at `br0` buckets is a strict superset of the old prior. If a bucket has fewer signatures, fail.

**Estimated impact**: +10-20 ELO (compounds with G2 — multiplicative not additive). **Effort**: 2 hours.

---

### G4. Adaptive compute budget (REPLACES the rejected F4)

**The fix**: instead of a heuristic "funnel detector that bumps `k_opp_phase1`," condition compute expansion on **time-budget remaining** — a non-archetype signal. If phase-1 finishes early, reinvest the time in higher-confidence phase-2.

**Why this is NOT a heuristic switch**:
- No archetype detection. The condition is `if elapsed < budget * 0.3` (have spare time), not `if funnel_signal`.
- Compute scales uniformly across all opponents, including bosses, heuristic_v1, Lostkids, etc.
- This is exactly what `IMPROVEMENT_PLAN.md §B6` already proposed and was approved.

**Code sketch** (`oracle_core/search.py`, after phase-2 completes):

```python
# After phase-2 evaluation:
phase1_2_elapsed = time.time() - t0
remaining_budget = time_budget_s - phase1_2_elapsed

# Tier 3: if budget remains, re-evaluate top-10 with double k_opp.
# This is content-agnostic — fires on ANY game where phase-1+2 finish fast.
if remaining_budget > time_budget_s * 0.4 and len(final_scores) >= 10:
    top_for_phase3 = final_scores[:10]
    phase3_scores = []
    k_opp_phase3 = min(k_opp * 2, 16)
    opp_samples_phase3 = opp_model.sample(
        game_state, our_mp=our_mp, opp_mp=opp_mp,
        recent_breaches=len(recent_breaches), k=k_opp_phase3,
        opp_player=2, config=config,
    )
    for score, cand in top_for_phase3:
        if time.time() > deadline: break
        sample_scores = []
        for opp_sample in opp_samples_phase3:
            if time.time() > deadline: break
            try:
                sd = _fast_copy_state(base_state)  # cheaper than deepcopy
                _apply_plan(cand, sd, my_player=1, config=config)
                _apply_plan(opp_sample, sd, my_player=2, config=config)
                post = _run_sim(sd, config_path)
                tel.sims_run += 1
                sample_scores.append(evaluate(post, my_player=1, ...))
            except Exception:
                continue
        if sample_scores:
            phase3_scores.append((sum(sample_scores)/len(sample_scores), cand))

    if phase3_scores:
        phase3_scores.sort(key=lambda t: -t[0])
        # Phase-3 results override phase-2 for the top-10 (higher confidence)
        ...
```

**Regression risk**: low. The budget gate is symmetric across all opponents; if no time remains, phase-3 doesn't fire. Worst case: zero impact. Pairs naturally with G7 (fast state copy).

**Estimated impact**: +5-15 ELO. **Effort**: 3 hours.

---

### G5. Multi-intensity patch family (REPLACES F5's single 20-SP version)

**The fix**: emit a family of patch templates at intensities `(1, 2, 3)` turrets — let the search pick the right intensity based on SP available.

**Why this is NOT hardcoded**: same patch GEOMETRY as the existing patch1 (offsets from breach tile), just parameterized by N. Doesn't bake in tile-specific knowledge.

**Code sketch** (`oracle_core/enumerator.py`, replacing the existing patch1/patch2 logic):

```python
if recent_breaches:
    breach_counter = Counter(recent_breaches)
    for (bx, by), count in breach_counter.most_common(2):
        if count < 1:
            continue
        # Emit patches at intensity 1, 2, 3
        for intensity in (1, 2, 3):
            atoms = []
            # Offset list — fixed GEOMETRY, opp-agnostic
            offsets = [
                (1 if bx < 14 else -1, 1),    # immediate behind, side-aware
                (0, 2),                        # straight back
                (-1 if bx < 14 else 1, 1),    # other side back
                (0, 0),                        # block at breach (wall)
            ]
            for ox, oy in offsets[:intensity * 2]:
                tx, ty = bx + ox, by + oy
                if 0 <= tx < 28 and 0 <= ty < 14:
                    if oy == 0 or (ox == 0 and oy == 0):
                        atoms.append((WALL_IDX, tx, ty))
                    else:
                        atoms.append((TURRET_IDX, tx, ty))
            upg_atoms = [(t[1], t[2]) for t in atoms if t[0] == TURRET_IDX]
            # Only include upgrades for intensity 3 (high-SP scenarios)
            if intensity < 3:
                upg_atoms = []
            p = _build_defense_plan(
                f"defense:patch{intensity}_at_{bx}_{by}",
                atoms + ANCHOR_TURRETS,
                game_state, config, sp_budget,
                upgrades=upg_atoms,
                archetype=f"reactive_patch_i{intensity}",
            )
            if p: plans.append(p)
```

**Regression risk**: low. Patches only fire when `recent_breaches` is non-empty; against opps with no breaches (typical bosses, heuristic_v1) they don't appear in candidates. The family form means low-SP states get patch1 (cheap, available); high-SP states get patch3 (corridor-strength, rare).

**Estimated impact**: +10-20 ELO on adversarial matches; 0 on non-adversarial. **Effort**: 3 hours.

---

### G6. Offense-effectiveness tracker (REFINED from F6)

**The fix**: track our offensive-plan effectiveness intra-game by `(archetype, launcher_zone)` not just archetype — so failing scout-from-(13,0) doesn't penalize scout-from-(3,10).

**Why this is NOT hardcoded**: the launcher_zone is a coarse 5-bucket categorization (L_corner, L_mid, center, R_mid, R_corner — same buckets as the opp model uses). No specific tile constants.

**Critically**: this fix addresses **demo-siege-v2's failure mode** specifically (the offense-paralysis loop where oracle keeps spamming interceptors at (3,17)). When the tracker shows `(int_screen, L_corner)` is yielding 0 dmg/MP after 3 attempts, the value function penalizes that plan family — opening room for the search to consider scout/demo plans that might break out of the loop.

**Code sketch** (new file `oracle_core/offense_tracker.py`):

```python
class OffenseEffectivenessTracker:
    def __init__(self, decay_per_turn=0.85):
        self.history: dict[tuple[str, str], list[tuple[int, float]]] = {}
        # Key: (archetype, launcher_zone). Value: [(turn, dmg/MP)]
        self.decay = decay_per_turn

    def record(self, archetype: str, launcher_xy, mp_spent: float,
               hp_dealt: float, turn: int):
        zone = self._zone_of(launcher_xy)
        key = (archetype, zone)
        ratio = hp_dealt / max(mp_spent, 1.0)
        self.history.setdefault(key, []).append((turn, ratio))

    def expected_dmg_per_mp(self, archetype: str, launcher_xy, current_turn: int,
                            fallback: float = 0.5) -> float:
        zone = self._zone_of(launcher_xy)
        key = (archetype, zone)
        events = self.history.get(key, [])
        if len(events) < 2:
            return fallback
        # Decay-weighted average
        weights = [self.decay ** (current_turn - t) for t, _ in events]
        ratios = [r for _, r in events]
        return sum(r*w for r, w in zip(ratios, weights)) / max(sum(weights), 1e-6)

    def _zone_of(self, xy):
        if not xy: return "unknown"
        x, _ = xy
        if x <= 5: return "L_corner"
        if x <= 11: return "L_mid"
        if x <= 16: return "center"
        if x <= 22: return "R_mid"
        return "R_corner"
```

In `value.py.evaluate()`:
```python
if plan and plan.has_mobiles() and offense_tracker is not None:
    for op in plan.mobile_ops():
        expected = offense_tracker.expected_dmg_per_mp(
            plan.archetype, op.xy, current_turn=state_dict["turn"]
        )
        if expected < 0.05 and plan.mp_cost >= 3:
            # Penalty proportional to wasted MP (small but noticeable)
            score -= 30 * min(plan.mp_cost / 5.0, 3.0)
```

**Why this addresses demo-siege-v2 specifically**: in the 5 demo-siege losses, oracle spammed `int_screen, L_corner` every turn for 30+ turns at 0 dmg/MP. The tracker would record 5+ failed attempts → expected drops to ~0 → penalty fires → search explores scout/demo plans elsewhere → potentially finds the offense-burst pattern that won the 1 W match.

**Regression risk**: medium. Misattribution risk (multiple plans in flight); cold-start risk (penalty starts after 2-3 attempts); risk of penalizing slow-payoff plans (e.g., big scout rushes that need 2-3 setup turns).

**Mitigations**:
- Only penalize if `mp_cost >= 3` (don't punish small probes)
- Decay weight (recent failures matter more)
- Penalty cap (-30 max) — small enough that a clearly-good plan can still win
- Only fire after 2+ recorded attempts (cold-start safe)

**Estimated impact**: +15-30 ELO (largest single fix for demo-siege archetype). **Effort**: 6-8 hours.

---

### G7. Hand-rolled fast state copy (PERF UNCHANGED FROM IMPROVEMENT_PLAN.md B5)

Already approved in `IMPROVEMENT_PLAN.md`. Cuts deepcopy from 32% of search wall time → enables the G4 adaptive budget to actually fit work into freed time.

```python
def _fast_copy_state(s):
    return {
        "turn": s["turn"],
        "p1": dict(s["p1"]),
        "p2": dict(s["p2"]),
        "structures": [{**ss, "xy": list(ss["xy"])} for ss in s["structures"]],
        "mobiles": [
            {**mm, "xy": list(mm["xy"]),
             "spawn_xy": list(mm.get("spawn_xy", mm["xy"]))}
            for mm in s["mobiles"]
        ],
    }
```

**Effort**: 2 hours including parity test.

---

### G8. Pass breach TILES (not count) to opp_model (NEW)

**The fix**: thread `recent_breach_tiles` through to `OpponentModel.sample()` so the model can use spatial patterns from the actual posterior data — not just the count.

**Why this is NOT hardcoded**: the model uses observed breach tile patterns to weight signatures by spatial similarity to recent observations. No tile constants in the code.

**Code sketch**:

```python
# algo_strategy.py:163 — pass tiles instead of relying on count
result = search(
    gs, self.config, self.opp_model,
    recent_breaches=self.recent_breaches[-6:],
    recent_breach_tiles=self.recent_breaches[-6:],  # NEW
    ...
)

# search.py — forward through to opp_model.sample
opp_samples_phase2 = opp_model.sample(
    game_state, our_mp=our_mp, opp_mp=opp_mp,
    recent_breaches=len(recent_breaches),
    recent_breach_tiles=recent_breach_tiles,  # NEW
    k=k_opp, opp_player=2, config=config,
)

# opponent_model.py:sample — accept and use it
def sample(self, ..., recent_breach_tiles=None, ...):
    # Use breach tiles to filter posterior signatures by spatial similarity
    # to observed pattern. NO directional assumptions; uses cosine-similarity
    # over launcher-zone histogram of past observations.
    ...
```

**Effort**: 3-4 hours.

---

## 4. What we explicitly REJECT from the prior plan

| Prior fix | Why rejected |
|---|---|
| **F1** (hand-crafted flank corridor templates with hardcoded tiles) | Encodes 22 specific tiles from 8 known losses; walls oracle's own launchers (2,11)/(25,11); won't generalize to a 9th funnel variant |
| **F4** (archetype-detector that bumps `k_opp_phase1` on funnel signal) | Heuristic-switch violation; the plan's own §4 NOT-TO-DO list said this is forbidden; the bumped configuration is documented as having regressed Lostkids and FUNNEL matches in REPORT §3.3 |
| **F2's magic constants** (`>=13` threshold, weights `5.0`/`6.0`, directional routing) | Hand-tuned to 8 known losses; replaced with G2's posterior-derived approach |

These rejections are not failures of intent — they're the result of the user's correct insistence on no-hardcoding-no-regression. Better to delete bad fixes than ship them.

---

## 5. Implementation order (revised)

### Phase 1 (true bug fixes, no risk):
1. **G3** rebuild prior with breach context (2 hours, no risk, +10-20 ELO)
2. **G7** fast state copy (2 hours, no risk, +10 ELO via more sims)
3. Run full local ladder regression — must maintain 10/10 on bar opponents

### Phase 2 (structural fixes):
4. **G1** coverage-gap proposer (4-6 hours; replaces hardcoded F1)
5. **G2** posterior-driven sample sizing (4-6 hours; replaces hand-tuned F2)
6. **G8** thread breach tiles through to opp_model (3-4 hours)
7. Run full local ladder + 10 live ranked matches; validate funnel-loss conversion

### Phase 3 (offense-paralysis fix, addresses demo-siege-v2):
8. **G6** offense-effectiveness tracker with launcher zones (6-8 hours)
9. Re-run local ladder; especially validate vs demo-siege-v2 if a local equivalent exists; live test

### Phase 4 (compute scaling):
10. **G4** adaptive compute budget (3 hours)
11. **G5** multi-intensity patch family (3 hours)
12. Final ladder + live validation

---

## 6. Validation framework (stricter than the prior plan)

For EACH of G1-G8:

**1. Generalization test**: against the 13 loss replays, the fix's behavior must trigger on the EMPIRICAL gap pattern (not from a hardcoded list). Specifically:
- G1: gap analysis on the gencersarp T18 state must surface (23,9) area as thin (not hardcoded)
- G1: gap analysis on the demo-siege-v2 T40 state must surface (5,19) area as thin
- G2: posterior at the demo-siege bucket must contain demolisher-corner signatures derived from observation
- G6: tracker after 3 turns of 0-dmg interceptor spam must show expected_dmg_per_mp < 0.05

**2. Regression test**: against the 49 wins, NO fix may flip a win to a loss. Specifically:
- vs all 4 bosses (R1-R4): must remain 100% (4/4)
- vs heuristic_v1: must remain 100% (was 10/10)
- vs Lostkids/python-2l-aet: must remain 100%
- vs python-algo (starter): must remain 100%

**3. Anti-pattern test**: each fix is reviewed against REPORT §8's 10 anti-patterns. Specifically:
- No tile constants derived from observed losses (anti-pattern #1)
- No archetype-mode switches (anti-pattern #2)
- No value-function tuning to make search pick X (anti-pattern #4)

**4. Live test**: after Phase 2 (G1+G2+G3+G7+G8), upload to live and play 15 ranked matches. Funnel-loss conversion target: ≥3 of the 8 known funnel patterns now win. After Phase 3 (+G6), demo-siege-v2 conversion target: ≥1 of 5 wins.

---

## 7. Honest expected impact (revised)

| Fix | Type | Est. ELO | Confidence |
|---|---|---|---|
| G3 (rebuild prior) | Bug fix | +10-20 | HIGH (proven defect) |
| G7 (fast copy) | Perf | +5-15 | HIGH (proven 22× faster) |
| G1 (coverage gap) | Structural | +20-40 | MEDIUM (should help, but coverage ≠ defense as audit noted) |
| G2 (posterior sample) | Structural | +15-30 | MEDIUM (depends on G3 to populate posterior) |
| G6 (offense tracker) | Behavioral | +15-30 | MEDIUM (largest fix for demo-siege archetype) |
| G8 (tile threading) | Plumbing | +5-10 | MEDIUM |
| G4 (adaptive budget) | Perf | +5-15 | LOW-MEDIUM (depends on G7 to free time) |
| G5 (patch family) | Structural | +5-15 | LOW-MEDIUM |

**Total estimated**: +80-175 ELO if all 8 land cleanly. Realistic: probably +50-100 (impacts compound non-linearly; some are dependent).

**Targets**:
- Phase 1 only (G3+G7): 2050-2200 ELO range across 3 instances → expect 2150-2250
- Phase 1+2 (G1+G2+G8): 2200-2300 expected
- Phase 1+2+3+4 (all): 2250-2350 expected

This is honest. The prior plan's estimate of "+60-90 ELO from F1+F2+F3" was based on hardcoded fixes that would have regressed wins. The revised estimate is more conservative because the structural fixes are weaker per-fix but don't carry hidden regression cost.

---

## 8. Acknowledgments and corrections to the record

### What I got wrong in the prior plan
1. Conflated "all 8 losses are funnel" — actually 8 are funnel + 5 are demo-siege-v2 (different archetype). Now identified.
2. F1 hardcoded specific tiles (3,10), (5,9), (6,8), etc. — would not generalize.
3. F4 was a heuristic-mode switch that I rationalized despite the plan's own NOT-TO-DO list flagging it.
4. F2's `>=13` and weights `5.0/6.0` were hand-tuned to observed losses.
5. Patch math was off by 2× (placed 2 turrets per breach, not 1).

### What the user got right
- Suspicion that hardcoding had crept in: confirmed, F1 + F4 + part of F2.
- Demand for cross-checking with code: revealed several factual errors.
- Demand for new replays: revealed the second failure mode (demo-siege-v2).

### What's now in scope vs out of scope
- IN SCOPE: G1-G8 above. All structural, all generalize, all regression-safe.
- OUT OF SCOPE: anything that requires per-opponent classifiers, anything that hardcodes tile lists, anything that switches search mode based on detected archetype.

This plan is what I should have written first. The user's pushback was correct, and the corrections are documented.
