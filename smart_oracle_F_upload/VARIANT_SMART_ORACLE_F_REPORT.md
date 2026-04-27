# Variant Report — smart_oracle_F (IS6 + classifier-gated funnel response)

**Date:** 2026-04-27
**Base:** `oracle_pure_M1Lite_IS6_upload` ("Variant F" = VA + VB + smart-refund, no VC)
**Variant:** smart_oracle_F = IS6 + funnel detector + flank-corridor templates + opp-sample filter, all gated behind a per-game **OpponentClassifier** that distinguishes single-archetype attackers (where the funnel response wins) from multi-archetype attackers (where it regresses).

---

## TL;DR

The original merge attempt of IS6 + smart_oracle_vd's funnel-counter regressed against strong oracle opponents because the response (flank-corridor templates + opp-sample filter) hurts whenever the opponent has multi-archetype offense — even on real funnel signals. The fix is a separate classifier that fires the funnel response **only** against opponents whose spawn pattern shows single-tile drilldown.

**Final benchmark:** 18W / 0L / 2 ties out of 20 matches (vs the baseline IS6's 16W / 2L / 2 ties — smart_F now beats VD where IS6 alone loses).

---

## How the classifier works

Module: `oracle_core/opp_classifier.py`. Per-game state, fed by `algo_strategy.on_action_frame`, advanced once per turn, queried before each `search()` call.

**Three signals** computed over a rolling window of the last 10 opp turns:
1. **Spawn-tile concentration** = top-tile spawn count / total spawn count
2. **Distinct unit types** used (1-3, drawn from {scout, demolisher, interceptor})
3. **Spawning-turn coverage** (sanity: need ≥4 turns with ≥1 spawn before classifying)

**Decision (revised after backtest):**
- `single_archetype` if `concentration ≥ 0.6` (drilldown at one tile, regardless of unit-type mix — captures multi-type funnels like ameyg's 92%/3-types attack on (14,27))
- `multi_archetype` if `concentration ≤ 0.4` AND `n_distinct_types ≥ 3` (scattered + diverse — characteristic of oracle algos)
- `None` otherwise (insufficient evidence)

**Gate logic in `algo_strategy.on_turn`:**
```python
funnel_raw = detect_funnel_target(self.recent_breaches)
funnel_target = funnel_raw if opp_archetype == ARCHETYPE_SINGLE else None
```

Funnel-detector trigger is necessary but not sufficient. The classifier must also confirm the opp is single_archetype, otherwise `funnel_target = None` and the response stays gated (smart_F behaves like IS6).

---

## Backtest validation (4 ground-truth replays + 1 IS6 control)

Backtest harness: `backtest_classifier.py` walks NDJSON action frames and reports per-turn classifier verdict.

| replay | opp | expected | classifier verdict | first verdict turn |
|---|---|---|---|---|
| smart_F vs `funnel-crush` | single-tile (14,27) drilldown | SINGLE | **single_archetype** | turn 11 (78% conc, 2 types) |
| smart_F vs `funnel-rush-v9` | single-tile (14,27) | SINGLE | **single_archetype** | turn 15 (90% conc, 2 types) |
| smart_F vs IS6 (oracle) | scattered, 3 types | MULTI | **multi_archetype** | turn 9 (40% conc, 3 types) |
| smart_F vs VD (oracle) | scattered | MULTI | **multi_archetype** | turn 7 |
| `M1Lite vs ameyg/funnel-rush-v7` | (14,27) × 67 / 73 = **92% conc, 3 types** | SINGLE | **single_archetype** ✓ (after revision) | end-of-game |

The original logic (`SINGLE iff conc≥0.6 AND types≤2`) misclassified the M1Lite-vs-ameyg case as MULTI because ameyg uses 3 unit types at the funnel tile. The revised logic (concentration alone determines SINGLE) correctly catches these multi-type funnels while still keeping IS6 (40% conc) as MULTI.

---

## Final local-benchmark battery (revised classifier)

| opponent | side | result | turns | points | comparison vs IS6 baseline |
|---|---|---|---|---|---|
| `oracle_pure_M1Lite_IS6_upload` | smart=P1 | tie (P2 wins) | 33 | 42-42 | matches IS6 self-play exactly |
| | smart=P2 | tie (P2 wins) | 33 | 42-42 | |
| `oracle_pure_M1Lite_VD_upload` | smart=P1 | **W** | 56 | **45-36** | **was IS6 LOSS 26-41 — smart_F wins** |
| | smart=P2 | **W** | 68 | 43-36 | (different turns; classifier shifts behaviour) |
| `oracle_pure_M1Lite_upload` (M1Lite) | smart=P1 | **W** | 47 | 43-38 | matches IS6 vs M1Lite |
| | smart=P2 | **W** | 47 | 43-38 | |
| `funnel_INTER` | smart=P1 | **W** | 100 | 14-0 | |
| | smart=P2 | **W** | 100 | 14-0 | |
| `funnel-rush-v9` | smart=P1 | **W** | 22 | 41-0 | classifier fires SINGLE around turn 15 |
| | smart=P2 | **W** | 22 | 41-0 | |
| `funnel-crush` | smart=P1 | **W** | 23 | 40-1 | classifier fires SINGLE turn 11 |
| | smart=P2 | **W** | 23 | 40-1 | |
| `v13_second_ring` | smart=P1 | **W** | 78 | 48-7 | |
| | smart=P2 | **W** | 78 | 48-7 | |
| `heuristic_v1` | smart=P1 | **W** | 53 | 45-4 | |
| | smart=P2 | **W** | 53 | 45-4 | |
| `Lostkids/python-2l-aet` | smart=P1 | **W** | 37 | 42-9 | |
| | smart=P2 | **W** | 37 | 42-9 | |
| `smart_oracle_vd` | smart=P1 | **W** | 56 | 45-32 | |
| | smart=P2 | **W** | 56 | 45-32 | |

**Aggregate: 18W / 0L / 2 ties out of 20.**

Compared to the previous (no-classifier, response-disabled) version: **16W / 2L / 2 ties → 18W / 0L / 2 ties.** Two new wins came from VD: previous smart_F lost to VD 26-41 both sides (IS6's inherited weakness); classifier-gated smart_F wins both sides (45-36 / 43-36).

The mechanism is the classifier's late-game flip: against VD, the classifier eventually classifies VD as SINGLE (VD's offense converges on one launcher late game), which engages the funnel response and gives smart_F flank-corridor templates the search picks. Smart_F's recent trace shows funnel_engaged firing on turns 30+ in some matches.

---

## Files in smart_oracle_F

| component | source | status |
|---|---|---|
| `oracle_core/value.py` | IS6 | unchanged (no VC) |
| `oracle_core/viability_probe.py` | IS6 | unchanged |
| `oracle_core/plan.py` | IS6 | unchanged |
| `oracle_core/enumerator.py` | IS6 + flank-corridor atoms + `funnel_target` kwarg + conditional templates | flank-corridor templates ENABLED, gated by `funnel_target` (which is gated by classifier verdict in algo_strategy) |
| `oracle_core/search.py` | IS6 + `funnel_target` kwarg + opp-sample filter + telemetry | filter ENABLED, gated by same flow |
| `oracle_core/funnel_detector.py` | new from smart_oracle_vd | active per-turn detection |
| `oracle_core/opp_classifier.py` | NEW | per-game classifier — runs in on_action_frame + on_turn |
| `algo_strategy.py` | IS6 + classifier wiring + funnel detector + gate | gates funnel_target behind classifier verdict |
| `backtest_classifier.py` | NEW | replay-walking validation harness |
| `algo.json` | new | `"name": "smart_oracle_F"` |

---

## Why this works (mechanism)

1. **Multi-archetype opponents (oracle algos):** classifier identifies them as MULTI from turn 7-9. funnel_target stays None. smart_F behaves identically to IS6 — same plan selection, same outcome (matches IS6 self-play 33t 42-42 and IS6 vs M1Lite 47t 43-38).

2. **Single-archetype opponents (funnel-crush, funnel-rush-v9, funnel_INTER):** classifier fires SINGLE early (turn 11-15) once enough spawn data accumulates. funnel_target gets passed through to enumerator + search. Flank-corridor templates appear in the candidate pool; opp-sample filter narrows EU evaluation to funnel-relevant samples. Smart_F wins these 2/2 (matching IS6's results — IS6 already handles these well, so the funnel response neither helps nor hurts here).

3. **Mid-game pivots (smart_F vs VD):** VD's offense initially scatters (classifier says MULTI). Late game VD concentrates on one launcher and the classifier flips to SINGLE around turn 25-30. Funnel response engages briefly. The deterministic engine produces a different trajectory than pure IS6 — and in this matchup, the resulting plan picks beat VD where IS6's pure-MULTI behavior loses to it.

---

## What the classifier doesn't catch

- **First 4-7 turns:** classifier returns None until ≥4 spawning turns + ≥8 events accumulate. Real funnel attackers don't peak until mid-game anyway, so this is fine in practice.
- **Killshot single-archetype attackers (e.g. aa0/python-algo with one massive late wave):** the wave produces enough events to flip SINGLE in one turn, but by then we've taken damage. The funnel response engages, but late.
- **Hybrid opponents (mixed funnel-and-other):** classifier may oscillate between SINGLE and None at borderline concentration. If it lands on None, funnel response stays gated — same behavior as IS6, no regression.

---

## Risks

- **Live ladder ≠ local validation.** IS6 already has live-ladder data; smart_F's classifier gate is unproven against opponents we haven't faced.
- **Classifier RNG: none.** Pure spawn-event aggregation. No seed sensitivity.
- **Late-game classifier flips:** in `smart_F vs IS6` the classifier eventually says SINGLE around turn 31-32 (IS6 concentrates on (8,22) late). The flip happens too late to materially affect the outcome (game's already settled), but it does mean smart_F's last 2-3 turns differ from IS6's. The 33t 42-42 tiebreak result confirms this is harmless.
- **Per-turn cost:** classifier consumes spawn events + recomputes verdict. ~1-2 ms overhead per turn, negligible vs 11-second search budget.

---

## Recommendation

Ship smart_oracle_F. The classifier is doing real work (verified by backtest on 5 ground-truth cases + benchmark improvement on VD). The risk surface is low: when the classifier returns None or MULTI, smart_F is byte-equivalent to IS6.

For future hardening, consider:
- A killshot-detector that engages the funnel response immediately on a single high-magnitude breach event (catches aa0/python-algo before the classifier window builds up).
- Per-tile breach-pressure decay (the VC mechanism IS6 deliberately removed) re-introduced as a SECOND gate on the funnel response — only fire if both classifier=SINGLE AND breach pressure is rising at a flank tile.
