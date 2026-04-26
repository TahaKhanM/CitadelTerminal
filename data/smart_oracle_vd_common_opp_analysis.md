# smart_oracle_vd — per-replay common-opponent analysis

**Fetched:** 2026-04-27T15:09:50Z (Citadel Spring 2026 Europe, comp_id=1338, team Wick)
**Source:** match list via `/api/game/algo/{id}/matches`; replay frames via `/api/game/replayexpanded/{match_id}` (NDJSON)

**Method:** for each smart_oracle_vd ranked match, find every sibling oracle match against the **same opponent algo_id**, fetch both replays, and compare the actual game outcome side-by-side (HP, points, breach count, breach concentration, turn count). Then rank.

## The 6 pairs

Smart played 12 ranked opponents (R-tier bootstrap excluded). Only 4 of those opponents were also faced by a sibling oracle, producing 6 same-opp_algo pairs:

| pair # | opp algo (user) | smart match | sibling | sibling match |
|---|---|---|---|---|
| 1 | `funnel-rush-v7 / ameyg` | 15316964 | IS5 | 15316114 |
| 2 | `funnel-rush-v7 / ameyg` | 15316964 | M1Lite | 15316289 |
| 3 | `funnel-rush-v7 / ameyg` | 15316964 | VD | 15316245 |
| 4 | `python-algo / aa0` | 15316984 | IS5 | 15316805 |
| 5 | `Alan / pipmy` | 15317157 | IS5 | 15317135 |
| 6 | `v6-tweak / aa0` | 15317220 | VD | 15317214 |

## Per-pair replay analysis

### Pair 1 — `funnel-rush-v7 / ameyg`: **smart vs IS5 → tie (both perfect defense)**

| metric | smart (15316964) | IS5 (15316114) |
|---|---|---|
| result | **W** | **W** |
| final HP | **40 vs −6** | 40 vs −2 |
| points | **46–0** | 42–0 |
| turns | 54 | 55 |
| breaches against us | **0** | **0** |
| MP spoiled | 20 | 20 |

Both fully shut out ameyg's funnel rush — zero breaches in either game. Smart marginally cleaner (46–0 vs 42–0 points; opp HP −6 vs −2). **Verdict: tie.**

---

### Pair 2 — `funnel-rush-v7 / ameyg`: **smart >>> M1Lite**

| metric | smart (15316964) | M1Lite (15316289) |
|---|---|---|
| result | **W** | **L** |
| final HP | **40 vs −6** | **−24 vs 19** |
| points | **46–0** | 21–64 |
| turns | 54 | **84** (dragged out) |
| breaches against us | **0** | **64** |
| top breach tile | – | **(2,11) × 56** (LEFT flank) |
| zone of breaches | – | 100% LEFT |

M1Lite was slowly drilled at (2,11) — 56 of 64 breaches at one tile on the left flank. Smart let nothing through. **This is the textbook case smart_oracle_vd was designed for.** Verdict: **smart wins decisively**.

---

### Pair 3 — `funnel-rush-v7 / ameyg`: **smart >>> VD**

| metric | smart (15316964) | VD (15316245) |
|---|---|---|
| result | **W** | **L** |
| final HP | **40 vs −6** | **−10 vs 28** |
| points | **46–0** | 12–50 |
| turns | 54 | 72 |
| breaches against us | **0** | **50** |
| top breach tile | – | **(4,9) × 26**, (5,8) × 15 (LEFT flank) |
| zone of breaches | – | 82% LEFT |

VD's direct base-loss against the SAME ameyg algo. The funnel landed at (4,9) — exactly the tile `LEFT_FLANK_CORRIDOR` covers in smart's enumerator. Smart converted with 0 breaches; VD let 50 through. **Direct evidence the funnel addition works.** Verdict: **smart wins decisively**.

---

### Pair 4 — `python-algo / aa0`: **IS5 >>> smart**

| metric | smart (15316984) | IS5 (15316805) |
|---|---|---|
| result | **L** | **W** |
| final HP | **−9 vs 10** | **37 vs −5** |
| points | 30–49 | **45–3** |
| turns | 60 | 59 |
| breaches against us | **49** | **3** |
| top breach tile | **(25,11) × 46** (RIGHT flank) | (2,16) × 3 |
| zone of breaches | 100% RIGHT | 100% LEFT |

aa0's funnel landed 46 of 49 breaches at smart's (25,11) RIGHT-flank tile. **Smart's funnel detector failed to engage in time** — aa0's killshot landed in a single wave (or two) before the detector's "≥3 of last 6 breaches in same zone" threshold could fire. IS5 only let 3 breaches through; structurally different defense handled aa0 better. **Smart lost; IS5 dominated.**

---

### Pair 5 — `Alan / pipmy`: **smart ≈ IS5 (near-identical wins)**

| metric | smart (15317157) | IS5 (15317135) |
|---|---|---|
| result | **W** | **W** |
| final HP | **39 vs −2** | 39 vs −5 |
| points | **42–1** | 45–1 |
| turns | 61 | 63 |
| breaches against us | 1 | 1 |
| top breach tile | (25,16) × 1 | (25,16) × 1 |

Mirror-image performance — same final HP, same single breach, same tile. IS5 marginally more aggressive (45 vs 42 points). **Verdict: tie.**

---

### Pair 6 — `v6-tweak / aa0`: **smart > VD (HP margin)**

| metric | smart (15317220) | VD (15317214) |
|---|---|---|
| result | **W** | **W** |
| final HP | **17 vs −5** | 7 vs −1 |
| points | 45–23 | 41–33 |
| turns | 49 | 46 |
| breaches against us | **23** | **33** |
| top breach tile | (2,16) × 15 | (4,18) × 25 (LEFT flank) |

Both won. Smart let 10 fewer breaches and ended with 10 more HP. Same opponent, smart a touch tighter. **Verdict: smart edges VD.**

---

## Aggregate H2H (per-pair sign only, ties count for nobody)

| sibling | wins for smart | wins for sibling | ties |
|---|---|---|---|
| IS5 | 0 | **1** (python-algo aa0) | **2** (funnel-rush, Alan) |
| M1Lite | **1** (funnel-rush) | 0 | 0 |
| VD | **2** (funnel-rush, v6-tweak) | 0 | 0 |
| IS6 | – | – | – (no shared opp) |

## Final ranking based on shared-opponent replay outcomes

| rank | algo | head-to-head with smart on shared opps | key match |
|---|---|---|---|
| 1 | `oracle_pure_M1Lite_IS5_upload` | beats smart 1-0-2 (1 win, 2 ties) | crushed `python-algo aa0` 37 HP vs smart's −9 |
| 2 | **`smart_oracle_vd`** | own baseline | perfect 0-breach funnel defense vs ameyg |
| 3 | `oracle_pure_M1Lite_VD_upload` | loses to smart 0-2 | leaks 50 breaches at (4,9) on funnel-rush; loses HP race on v6-tweak |
| 4 | `oracle_pure_M1Lite_upload` | loses to smart 0-1 | meltdown — 64 breaches, 84-turn loss to ameyg |
| 5 | `oracle_pure_M1Lite_IS6_upload` | unrankable (no overlap) | – |

## Key findings from the replay-level analysis

1. **The funnel addition works on the canonical funnel pattern.** vs `funnel-rush-v7 / ameyg`, smart and IS5 both held to **0 breaches**, while M1Lite leaked 64 (at tile 2,11) and VD leaked 50 (at tile 4,9). This is the exact pattern `LEFT_FLANK_CORRIDOR` covers — same tiles, same outcome.

2. **Smart's only loss is exactly the failure mode the funnel detector can't catch.** vs `python-algo / aa0`, 46 of 49 breaches landed at (25,11) in what looks like 1–2 killshot waves. The detector's "≥3 of last 6 breaches in same flank" threshold cannot fire when the killshot lands all at once — there's no warning history. IS5's structurally tighter defense handled it without needing detection.

3. **Smart equals IS5 on benign matchups (Alan, funnel-rush) and edges VD on v6-tweak.** The funnel addition adds value where it matters and adds nothing where it doesn't — consistent with the "no-op when `funnel_target=None`" design.

4. **The python-algo aa0 loss is the only actionable signal for further work.** A complementary fix would be a single-wave killshot detector (e.g., trigger focused defense after even ONE high-magnitude breach at a flank tile) — that would have caught aa0 in pair 4. The current detector requires history, which the killshot doesn't give.
