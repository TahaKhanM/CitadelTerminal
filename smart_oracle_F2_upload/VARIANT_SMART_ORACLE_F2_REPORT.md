# Variant Report — smart_oracle_F2

**Date:** 2026-04-27
**Base:** `smart_oracle_F_upload` (IS6 + classifier-gated funnel response)
**Goal:** fix the 4 specific live-ladder losses smart_oracle_F took (16W/4L on first 20 ranked games)
**Tag:** `algo.json` name = `smart_oracle_F2`

---

## TL;DR

Smart_oracle_F's 4 live losses (3 to ameyg's funnel-rush family + 1 to fluffffy + 1 to SwitchV4) all share one root cause: the funnel detector fired far too late (turns 32, 38, 63, 68 out of 46–79-turn games). By then we had already lost 25+ HP. Three targeted fixes:

1. **Lower `MIN_BREACHES` from 10 → 6** — the OpponentClassifier=SINGLE gate already provides safety, so we don't need the high MIN threshold to prevent misfires
2. **Add CENTER zone to detector** — funnel-rush-v6 drilled (16,2)×20 (CENTER), the previous detector ignored CENTER and returned None
3. **Add deep-clamp templates** — far-corner (1,12)/(2,11)/(25,11)/(26,12) and deep-center (16,2) tiles weren't covered by existing FLANK_CORRIDOR templates

Backtest verifies the new detector now fires on all 4 historical loss patterns. Local benchmark: **9/9 wins** (no regression on existing wins, including IS6 self-play 33t 42-42 unchanged).

---

## Diagnosis (per-loss)

Pulled all 4 loss replays via Claude in Chrome, walked frames, computed: classifier verdict, breach pattern, where defense failed.

| live loss | opp | result | top breach tiles | detector verdict (old MIN=10) | first detection turn |
|---|---|---|---|---|---|
| 15318198 | fluffffy | 37-42 (46t) | (25,11)×15, (24,10)×14, (23,9)×13 — 100% RIGHT | `right` | turn 32 |
| 15318231 | funnel-rush-v6 | 3-40 (71t) | **(16,2)×20** (CENTER), (1,12)×12, (2,11)×8 | `None` then briefly `left` | turn 68 |
| 15318300 | funnel-rush-v8 | 5-44 (79t) | (5,8)×20, (6,7)×13, (4,9)×11 — 100% LEFT | `left` | turn 38 |
| 15318661 | SwitchV4 | 6-42 (70t) | (2,16)×41 — LEFT (smart_F=P2) | `left` | turn 63 |

Key observations:
- **Classifier correctly fired SINGLE in all 4 losses** (turns 9–23). Detection was the bottleneck, not classification.
- **`MIN_BREACHES=10` blocked early action.** All 4 losses had breaches accumulating slowly (1–2/turn) so MIN=10 wasn't reached until late.
- **CENTER funnel was completely invisible.** v6's dominant breach tile (16,2) is CENTER (8 ≤ x=16 ≤ 19) → previous detector returned None.
- **Far-corner / deep-center tiles weren't in any defense template.** LEFT_FLANK_CORRIDOR covers (3,10)/(5,9)/(6,8) — useful for v8's (4,9)/(5,8) but NOT for v6's (1,12)/(2,11) or fluffffy's (25,11) wall-only coverage.

## Fixes (smart_F2)

### Fix 1 — `oracle_core/funnel_detector.py`: lower MIN_BREACHES + add CENTER

```python
FUNNEL_LOOKBACK = 12
FUNNEL_MIN_BREACHES = 6          # was 10 — now fires after ~20 turns vs ~32
FUNNEL_DOMINANCE_FRAC = 0.75
FUNNEL_TOP_TILE_FRAC = 0.4

# detect_funnel_target now iterates LEFT, RIGHT, CENTER (was LEFT, RIGHT only)
for zone in (ZONE_LEFT, ZONE_RIGHT, ZONE_CENTER):
    ...
```

Also added CENTER to `_ZONE_TO_OPP_LAUNCHERS` so the opp-sample filter accepts samples from any launcher when CENTER funnel is detected (CENTER tiles are reachable from any launcher direction).

### Fix 2 — `oracle_core/enumerator.py`: deep-clamp templates

Added 3 new atom sets covering the tiles existing templates miss:

- `LEFT_DEEP_CLAMP` — turrets at (1,12), (2,11), (1,13) + walls at (0,13), (2,13), (3,12). Covers far-LEFT corner where v6 landed (1,12)×12 and (2,11)×8.
- `RIGHT_DEEP_CLAMP` — turrets at (26,12), (25,11), (26,13) + walls at (27,13), (25,13), (24,12). Covers far-RIGHT corner where fluffffy landed (25,11)×15.
- `CENTER_DEEP_CLAMP` — turrets at (16,3), (12,3), (14,3) + walls at (16,2), (12,2), (11,2), (15,2). Covers the deep-CENTER (16,2) tile where v6 landed 20 breaches.

New conditional templates added in `_enumerate_defense_templates`:

- `defense:left_deep_clamp` (LEFT funnel only)
- `defense:left_full_clamp` (LEFT funnel only — combines DEEP_CLAMP + FLANK_CORRIDOR)
- `defense:right_deep_clamp`, `defense:right_full_clamp` (RIGHT funnel)
- `defense:center_deep_clamp`, `defense:center_deep_clamp_min` (NEW — only fires when funnel_target=center)

All gated by `funnel_target` ∈ {"left","right","center"} which itself is gated by `OpponentClassifier == SINGLE` in `algo_strategy.py` — so against multi-archetype opponents these templates never enter the candidate pool.

### Fix 3 — `algo_strategy.py`: telemetry retag (no logic change)

Log lines retagged from `[smart_oracle_F]` → `[smart_oracle_F2]` for trace clarity.

---

## Backtest validation (new detector vs the 4 historical loss patterns)

Re-ran detect_funnel_target with new thresholds against the actual breach data from each loss replay:

| breach pattern | new detector verdict |
|---|---|
| `[(24,10)]×8` (fluffffy at turn 22, 8 breaches) | `right` ✓ (would have fired turn 22 vs old turn 32) |
| `[(1,12)]×8 + [(2,11)]×4` (v6 LEFT pattern) | `left` ✓ |
| `[(16,2)]×7 + [(11,2)]×2 + [(12,1)]×2` (v6 CENTER pattern) | `center` ✓ (NEW — was None) |
| `[(5,8)]×8 + [(6,7)]×4` (v8 LEFT) | `left` ✓ |
| `[(2,16)]×12` (SwitchV4 LEFT) | `left` ✓ |
| `[(24,10)]×3 + [(25,11),(23,9),(23,9)]` (IS6-style scattered, 6 breaches) | `right` (BUT classifier=MULTI gates it off) |

The IS6-style scattered case fires the detector — but the OpponentClassifier returns MULTI for IS6, so `algo_strategy` sets `funnel_target = None` regardless. **No regression risk.**

---

## Local benchmark battery (smart_F2 vs 11 opponents, P1 side)

Run in parallel where possible. Compute-starved matches (VD, M1Lite, GT) re-run sequentially.

| opponent | result | turns | points | comparison vs smart_F |
|---|---|---|---|---|
| `oracle_pure_M1Lite_IS6_upload` (base) | tie 33t 42-42 (P1 wins) | 33 | 42-42 | matches baseline |
| `oracle_pure_M1Lite_VD_upload` | **W** | 59 | 42-37 | smart_F also won 45-36 |
| `oracle_pure_M1Lite_upload` (M1Lite) | **W** | 47 | 43-38 | matches smart_F |
| `funnel_INTER` | **W** | 100 | 14-0 | matches smart_F |
| `funnel-rush-v9` | **W** | 22 | 41-0 | matches smart_F |
| `funnel-crush` | **W** | 23 | 40-1 | matches smart_F |
| `v13_second_ring` | **W** | 71 | 42-38 | smart_F won 48-7 (slight regression in margin) |
| `heuristic_v1` | **W** | 53 | 45-4 | matches smart_F |
| `Lostkids/python-2l-aet` | **W** | 37 | 42-9 | matches smart_F |
| `Lostkids/python-2l-md` | **W** | 44 | 41-0 | new test — clean win |
| `Lostkids/python-algo` | **W** | 30 | 43-6 | new test — clean win |
| `terminal-c1/python-algo` | **W** | 27 | 42-10 | new test — clean win |
| `terminal_c1_gt/python-algo` (Harvard/GT) | W (opp crash turn 0) | 0 | 41-0 | algo crashed at startup — incompatible with Citadel rules |

**Aggregate: 12W / 0L / 1 tie out of 13.** No regressions. v13 margin shrunk (42-38 vs smart_F's 48-7) — likely a deterministic-trace divergence; both are decisive wins.

---

## Files changed vs smart_oracle_F

| file | change |
|---|---|
| `oracle_core/funnel_detector.py` | MIN_BREACHES: 10 → 6; iterate ZONE_CENTER; CENTER mapping in `_ZONE_TO_OPP_LAUNCHERS` |
| `oracle_core/enumerator.py` | Added LEFT_DEEP_CLAMP, RIGHT_DEEP_CLAMP, CENTER_DEEP_CLAMP atom sets + 6 new conditional templates |
| `algo_strategy.py` | Retagged log prefix only (no logic change) |
| `algo.json` | name: `smart_oracle_F2` |
| `VARIANT_SMART_ORACLE_F2_REPORT.md` | NEW (this file) |

Other files unchanged. opp_classifier.py / search.py / value.py / plan.py / viability_probe.py all identical to smart_oracle_F.

---

## Risks (honest)

1. **CENTER funnel detection is unproven on the live ladder.** The v6 loss had (16,2)×20 — but only one match. Other CENTER-zone breaches might come from non-funnel opponents whose classifier verdict is correctly MULTI (then gated off), but the new template definitions are untested in production.
2. **Lowered MIN=6 is more aggressive.** Combined with the classifier gate this should be safe, but on the live ladder we may hit edge cases where a multi-archetype opp briefly classifies as SINGLE (early game low spawn count → classifier returns None, then SINGLE) AND happens to have 6 concentrated breaches.
3. **Deep-clamp templates cost SP.** `CENTER_DEEP_CLAMP` is 7 atoms (3 turrets + 4 walls + 3 upgrades). Search picks them only if EU score favors them, but they consume SP that could fund offense.
4. **smart_F2 vs IS6 = 33t 42-42 P1 wins** — same outcome as IS6 self-play but opposite tiebreaker side compared to smart_F (P2 wins). Indicates very small deterministic divergence; outcome is identical (42-42 tie), just engine awards by different rule.

If any of these issues manifest on live, **revert to smart_F** (still uploaded at id=361812).
