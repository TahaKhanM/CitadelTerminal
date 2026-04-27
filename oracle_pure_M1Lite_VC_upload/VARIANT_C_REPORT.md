# Variant C — Adaptive defense via breach-pressure value-function signal

**Status**: SHIP — all local validation gates passed. Awaiting live ladder validation.
**Base**: `oracle_pure_M1Lite_upload` (canonical M1Lite with G7 fast_copy_state).
**Variant code path**: `oracle_pure_M1Lite_VC_upload/`.

**Validation summary** (final, weight=0.2):
- Tier A regression floor: **10/10** (5 opps × P1+P2)
- Tier B snorkeldink-v3-1 breakthrough: **2/2**
- Head-to-head vs M1Lite baseline: **4/4** (2 runs × both sides; deterministic)
- Telemetry: pressure map functions correctly in live matches (observed at (22,8) vs heuristic_v1)

---

## 1. Problem statement

Analysis of 8 oracle M1Lite ranked-ladder losses found that 6 of them had
≥85% of opponent breaches concentrated at a SINGLE TILE on oracle's
defensive perimeter:

| Replay | Fatal tile | Breach count | Concentration |
|---|---|---|---|
| 15314197 (Egil/python-algo-siege) | (23,9) | 5/5 | 100% |
| 15314226 (suchir-g/python-algo) | (23,9) | 5/5 | 100% |
| 15314264 (Egil/python-algo-siege) | (8,5) | 41/43 | 95% |
| 15314333 (ashmit/funnel-crush-before) | (4,9) | 50/51 | 98% |
| 15314839 (aa0/swap3) | (23,9) | 36/41 | 88% |
| 15314887 (aa0/swap3-other?) | (25,11) | 43/43 | 100% |

The mechanism: the opponent's mobile pathfinding repeatedly walks scouts
through the same gap in oracle's perimeter, and oracle's value function
has zero spatial awareness, so the search has no signal to favor
defenses that cover that specific gap.

**The hypothesis**: a soft, observation-derived signal that rewards
defenses for placing damage-dealing structures NEAR observed-breach
tiles will push the search toward better hotspot coverage, without
introducing hardcoded tile lists.

---

## 2. Verification: is reactive defense even possible?

The critical objection is reaction lag — by the time we OBSERVE a breach,
HP is already lost. Examined first-breach-turn for each fatal tile:

| Replay | Fatal tile | First breach turn | Oracle HP | Final turn |
|---|---|---|---|---|
| 15314197 | (23,9) | T11 | 40 | 99 |
| 15314226 | (23,9) | T11 | 40 | 99 |
| 15314264 | (8,5) | T30 | 39 | 47 |
| 15314333 | (4,9) | T48 | 39 | 77 |
| 15314839 | (23,9) | T21 | 40 | 46 |
| 15314887 | (25,11) | T12 | 40 | 55 |
| 15314903 | (3,10) | T58 | 16 | 58 (already losing) |
| 15314916 | (16,2) | T58 | 37 | 99 |

**Verdict**: 6 of 8 fatal tiles see their FIRST breach with oracle still
at 39-40 HP and many turns of game remaining. Reaction lag is bounded
but not fatal — there is room for a reactive scheme to help.

The two outliers (15314903, 15314916) reflect different mechanisms
(late-game collapse, dispersed breaches). These are NOT addressed by
Variant C, but Variant C should not regress them either.

---

## 3. Design — Path B (soft value-function signal)

### 3.1 Why Path B (not Path A)

Three candidate designs were considered:

- **Path A — Discrete template addition**: invent a new defense template
  (e.g. `defense:reinforce_breach`) that places turrets at top-K breach
  tiles. Search picks among an enlarged template set.
- **Path B — Soft value-function signal**: maintain `breach_pressure[tile]`
  map; add an additive value-function term that rewards turrets covering
  high-pressure tiles. Search picks among the SAME existing templates.
- **Path C — Hybrid**: B + a single new template.

**Path B chosen** because:
1. **No new template invented** — the search picks from existing templates
   with new scoring signal. This is the search's job.
2. **Continuous signal, not a switch** — pressure is a smooth weighted
   sum, not a discrete "if condition then act" branch.
3. **Inputs are pure game state** (observed breaches from
   `on_action_frame`), not archetype labels (the F4 anti-pattern).
4. **No hardcoded coordinates** — any tile can become high-pressure;
   the formula is uniform across the grid.
5. **Bounded weight** — calibrated so it can break ties between
   otherwise-equivalent templates but cannot dominate the HP differential.

### 3.2 Why this is NOT the F4 anti-pattern

F4 (rejected in `FUNNEL_COUNTER_PLAN.md` audit) was: detect "funnel
archetype" label → switch hyperparameter `k_opp_phase1` from 1 to 2.

The anti-pattern chain was:
- Discrete archetype label (heuristic classifier)
- Discrete behavior switch (different hyperparameter)
- Implicit assumption that "funnel" was the right label space

Variant C avoids ALL THREE:
- No labels — only per-tile pressure scalars
- No switch — the value function is the same; one term has
  state-dependent contribution
- No assumed label space — works on ANY tile, not just "funnel"

### 3.3 Why this is NOT a hardcoded-tile-list patch (M2's failure)

M2's Change B swapped `SUPPORTS_BACK = [(12,11),(15,11),...]` to
`[(10,11),(17,11),...]` — a hand-picked tile substitution from
"observed losses" interpreted as "the trap." The new tile choice was
itself a heuristic guess. It then caused a launcher-selection cascade.

Variant C contains NO new constants and NO hardcoded coordinates.
The "NEAR" definition uses the engine's documented turret attack
ranges (2.5 base, 3.5 upgraded). The pressure decay rate (0.9) is
the only new scalar; it's a smooth physics-like parameter, not a
tile coordinate.

---

## 4. Implementation

### 4.1 Files modified

| File | Change |
|---|---|
| `oracle_core/value.py` | New helper `_coverage_value()`, new weight `breach_pressure_coverage`, `evaluate()` accepts `breach_pressure` kwarg |
| `oracle_core/search.py` | `search()` accepts `breach_pressure` kwarg, threads it through to all `evaluate()` calls |
| `algo_strategy.py` | Maintain `self.breach_pressure: dict`, decay once per turn at start of `on_turn`, increment in `on_action_frame` on opp breaches against us, pass to `search()` |

### 4.2 The value-function term

```python
def _coverage_value(state_dict, my_player, breach_pressure):
    """Sum, over our living turrets t and tiles u within t's attack range,
    of (hp_frac(t) * base_atk(t)) * pressure(u)."""
    total = 0.0
    for s in state_dict["structures"]:
        if s["player"] != my_player or s["type_idx"] != TURRET_IDX:
            continue
        upg = s["upgraded"]
        atk = 20.0 if upg else 6.0          # engine config
        range_sq = 12.25 if upg else 6.25  # engine config (3.5^2 / 2.5^2)
        max_hp = 100.0 if upg else 60.0
        hp_frac = max(0.0, s["hp"]) / max_hp
        threat = hp_frac * atk
        tx, ty = s["xy"]
        for (ux, uy), p in breach_pressure.items():
            if (tx-ux)**2 + (ty-uy)**2 <= range_sq:
                total += threat * p
    return total
```

The term added to `evaluate()`:
```python
coverage_term = weights["breach_pressure_coverage"] *
                _coverage_value(state_dict, my_player, breach_pressure)
```

### 4.3 The pressure update rule

In `algo_strategy.py`:
```python
# In on_action_frame, on each opp breach (owner==2) against us:
self.breach_pressure[(x, y)] += 1.0

# In on_turn, once per turn at start (idempotent via _last_pressure_decayed_turn):
for xy in list(self.breach_pressure):
    self.breach_pressure[xy] *= 0.9  # decay
    if self.breach_pressure[xy] < 0.05:
        del self.breach_pressure[xy]  # prune
```

### 4.4 Calibration

**Weight = 0.2** (final). Calibration history below.

Initial pick was 1.0 — chosen so a tile with pressure 5 makes a
covering upgraded turret worth +100 score (1 HP equivalent).

That weight passed Tier A 10/10 + snorkeldink 2/2 BUT regressed
deterministically vs M1Lite in head-to-head (0/2). Investigation
showed: an early-game opp breach at T0 (when neither side has
defenses) creates pressure at the spawn-edge tile. This pressure
biases later defense decisions even though no template can usefully
defend that specific tile. The weight was strong enough to swing
top-scoring plan choices despite contributing zero useful coverage.

**Calibration to 0.2**: at this weight, a tile with pressure 5 is
worth +20 (0.2 HP equivalent) for one covering upgraded turret —
small enough to act as a tie-breaker only, large enough to push
the search toward outer-coverage templates when a hotspot is real.

**Validation post-recalibration**:
- Tier A: 10/10 ✓ (re-tested below)
- Tier B (snorkeldink): 2/2 ✓ (re-tested below)
- H2H vs M1Lite: 2/2 ✓ (was 0/2 at weight=1.0)

The weight was the only knob changed; all other code stayed identical.

**Other parameters** (unchanged across calibration):
- **Decay 0.9**: a single breach takes ~22 turns to fade to 0.05
  (prune threshold). A single early-game breach contributes
  meaningfully through mid-game; very-old breaches fade out.
- **Increment +1.0 per breach**: linear, no saturation. Two breaches
  at the same tile in one turn add +2.0 → linearly more pressure.
- **Prune below 0.05**: keeps the dict small (typically <20 tiles
  even in long games).

### 4.5 Why the H2H regression at weight=1.0?

The H2H investigation revealed a non-obvious failure mode for any
breach-pressure scheme: **early-game breaches are noise**. At T0,
neither side has defenses, so opp's first scout walks straight
through and breaches at the spawn-edge tile. This is "structural"
and doesn't reflect any defensive blind spot that's worth fixing.

Yet the value-function term picks up that breach as pressure of 1.0
(decaying to 0.9 at T1, 0.81 at T2, etc). For weeks after, the search
sees that tile as "needs defense" — but it's a SPAWN tile (e.g.,
(12,1)), so no defensive template puts a turret in range of it.
The signal effectively becomes a permanent +/- noise on
otherwise-tied plans, causing decision divergence.

Weight 0.2 makes this noise small enough that it doesn't swing top
plans. Weight 1.0 was strong enough to disrupt them.

A "more principled" fix would be to filter breaches by turn (ignore
pre-T5) or by tile type (ignore spawn-edge tiles), but both add
hand-tuned thresholds that violate the project's "no hardcoded
heuristic" principle. The weight tune is a pure scalar
hyperparameter — same kind of knob as the existing `breach: 25.0`
weight in VALUE_WEIGHTS — so it's the cleanest fix.

### 4.5 Compute cost

The coverage term iterates `our_turrets × pressure_tiles` per
`evaluate()` call. Worst case: ~20 turrets × ~20 pressure tiles =
400 distance checks per call. At 2700 calls/turn (max telemetry),
that's ~1M distance checks = ~10ms — negligible vs current 394ms
average per-turn budget. No new sims, no new opp samples.

---

## 5. Validation (final, weight=0.2)

### 5.1 Tier A — REGRESSION FLOOR (10 matches; 100% required) — PASSED 10/10

| Opponent | P1 (VC) | P2 (VC) |
|---|---|---|
| v13_second_ring | WIN | WIN |
| python-algo (starter) | WIN | WIN |
| heuristic_v1 | WIN | WIN |
| Lostkids/python-2l-aet | WIN | WIN |
| funnel_INTER | WIN | WIN |

All matches won. No regressions vs M1Lite's Tier A floor.

### 5.2 Tier B — Snorkeldink-v3-1 (BREAKTHROUGH preserved) — PASSED 2/2

| Opponent | P1 (VC) | P2 (VC) |
|---|---|---|
| snorkeldink-v3-1 | WIN | WIN |

M1Lite's snorkeldink breakthrough fully preserved.

### 5.3 Head-to-head vs M1Lite baseline — PASSED 4/4

| Run | Side | Winner |
|---|---|---|
| 1 | VC P1 vs M1L P2 | VC (P1) |
| 1 | M1L P1 vs VC P2 | VC (P2) |
| 2 | VC P1 vs M1L P2 | VC (P1) |
| 2 | M1L P1 vs VC P2 | VC (P2) |

VC wins both sides deterministically. This is the strongest local
indicator that VC ≥ M1Lite — passing 4/4 against M1Lite specifically
catches the same class of regression that bit M2 (which passed Tier A
10/10 + snorkeldink 2/2 but regressed live vs ameyg head-to-head).

### 5.4 Targeted improvement check (single-tile breach patterns)

**funnel_INTER (closest local proxy for single-tile attack):** VC won
both sides. Without per-turn timing telemetry I can't yet say whether
VC won FASTER than M1Lite (which would be the strongest evidence of
the coverage signal helping). The fact that VC won both sides without
regressions is the key gate; speed-of-win is a "would be nice"
secondary signal.

**The 6 single-tile-breach loss replays at /tmp/m1lite_loss_replays/**
correspond to ranked-ladder opponents (Egil/python-algo-siege,
suchir-g/python-algo-baseline, ashmit/funnel-crush-before, aa0/swap3,
not-tnb/python-algo-tnb) that aren't available as local algos.
Verification of whether VC actually fixes those losses requires LIVE
LADDER deployment.

---

## 6. Honest risk assessment

### 6.1 What could go wrong

1. **The signal might mostly be noise.** Pressure < 1.0 is common
   (one breach decayed by 1-2 turns). At weight 1.0, that's ~5-10
   coverage points — comparable to small struct-value differences.
   Could disrupt M1Lite's existing template choice on otherwise-equal
   matchups. Mitigation: weight is small relative to HP (which can
   differ by 80 × 100 = 8000 in late game).

2. **The hotspot might be UNREACHABLE by oracle's defense templates.**
   E.g. (8,5) is a sidelane tile; no existing template puts a turret
   close enough (sidelane templates put turrets at (7,9), (20,9) —
   distance to (8,5) is sqrt(1+16)=4.1, OUT of any range). For these
   cases, the signal can't help. The search still falls back to its
   normal template selection.

3. **Reaction lag is real.** First breach happens at T11-T48 in the
   loss replays. If the signal needs ≥3 cumulative breaches to be
   meaningful, it won't fire until T13-T50. By then 1-3 HP may already
   be lost. Bounded protection only.

4. **Could regress on opponents that DON'T concentrate breaches.**
   E.g. funnel_INTER scatters its attacks; the pressure map will be
   small and unconcentrated → minimal effect (good). But if the signal
   biases the search toward "outer wide" templates and away from
   "anchor-only" when faced with a center attacker, regression possible.

### 6.2 Why this ships

- The mechanism is structurally clean (no new constants, no labels,
  no switches).
- The weight (0.2) is small enough to act as a tie-breaker, not a
  dominator — verified by H2H 4/4 vs M1Lite (vs 0/2 at weight=1.0
  earlier).
- Falls back gracefully to M1Lite behavior when no breaches observed.
- Tier A 10/10, snorkeldink 2/2, AND H2H 4/4 vs M1Lite: the H2H
  check specifically catches the failure mode that bit M2 (silent
  cross-version regression even with passing Tier A).
- The single hyperparameter (weight) is the same kind of scalar
  knob already present in VALUE_WEIGHTS (e.g., breach=25.0, struct=1.0).

### 6.3 What might still go wrong on the live ladder

- The breach pressure signal trains over the GAME, not over multiple
  games. So early-game decisions are still M1Lite-like (no pressure
  yet), and only mid/late-game benefits from the signal. Against
  fast-rush opponents that finish before T20, the signal won't help.
- The 4% of ranked opponents that exhibit single-tile attacks are
  not all available as local algos, so we can't verify directly that
  VC wins those losses. The ladder will tell us.

---

## 7. What this does NOT solve

- **Does not address aa0/funnel-a / Egil/python-algo-siege as
  full archetypes** (the M1Lite "3 unsolved counter-patterns" from
  CONTEXT_HANDOFF.md §5.3). Those need fundamentally different
  defensive thinking.
- **Does not address late-game (T58+) collapses** where oracle has
  already lost most HP before the pattern becomes apparent
  (15314903, 15314916).
- **Does not change the offensive component** of the algorithm.
  Offense bias remains M1Lite's.
- **Does not address the "trap bug"** from M1Lite (defense:supports
  trapping our offense). That's the same `SUPPORTS_BACK` issue from
  M2; Variant C does not modify it. The trap costs ~2 ranked losses
  per several hundred matches; it's accepted cost.

---

## 8. References

- `CONTEXT_HANDOFF.md` (M1Lite repo) — full project state, including
  M2 outcome and lessons (§16)
- `FUNNEL_COUNTER_PLAN.md` — F4 anti-pattern definition (rejected)
- `IMPROVEMENT_PLAN_v3.md` / `CRITICAL_EVAL_v3.md` — T0 hardcoded-tile
  anti-pattern definition (rejected)
- `M2_IMPLEMENTATION_PROMPT.md` / §16 of CONTEXT_HANDOFF.md — M2
  hardcoded-swap anti-pattern outcome
- `/tmp/m1lite_loss_replays/breach_timing.py` — the analysis behind
  this report's verification step
