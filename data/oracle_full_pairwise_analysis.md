# Oracle algos — full pairwise replay comparison

**Fetched:** 2026-04-27T15:09:50Z (Citadel Spring 2026 Europe, comp_id=1338, team Wick)
**Source:** match list via `/api/game/algo/{id}/matches`; 76 replays via `/api/game/replayexpanded/{match_id}`
**Method:** for every pair of oracle algos (A, B), find every opponent algo_id that BOTH faced (R-tier bootstrap excluded), pull both replays, compare HP-margin and W/L. Per shared opponent algo: `verdict = A` if A's WR > B's WR (or HP-margin if WR equal); same for `B`; else `TIE`.

## Algos compared

| short | full name | algo_id | live ELO | total W-L |
|---|---|---|---|---|
| IS5 | `oracle_pure_M1Lite_IS5_upload` | 361576 | 2000 | 26-8 |
| IS6 | `oracle_pure_M1Lite_IS6_upload` | 361577 | 2006 | 19-7 |
| M1Lite | `oracle_pure_M1Lite_upload` | 361589 | 1961 | 16-5 |
| VD | `oracle_pure_M1Lite_VD_upload` | 361590 | 1967 | 22-9 |
| smart | `smart_oracle_vd` (NEW) | 361667 | 1897 | 13-3 |

## Per-pair head-to-head matrix (W-L-T on shared opp algos)

Rows = perspective. Read `IS5 vs M1Lite = 3-5-0` as: IS5 better on 3 shared opps, M1Lite better on 5, 0 ties.

| | IS5 | IS6 | M1Lite | VD | smart |
|---|---|---|---|---|---|
| **IS5** | – | 0-0-**10** | **3-5-**0 | 2-5-**4** | 2-1-0 |
| **IS6** | 0-0-**10** | – | **3-1-**0 | 3-3-**4** | n/a |
| **M1Lite** | 5-3-0 | 1-3-0 | – | 6-4-0 | 0-1-0 |
| **VD** | 5-2-**4** | 3-3-**4** | 4-6-0 | – | 0-2-0 |
| **smart** | 1-2-0 | n/a | 1-0-0 | 2-0-0 | – |

n_shared_opps per pair (informativeness — bigger = more reliable):

| | IS5 | IS6 | M1Lite | VD | smart |
|---|---|---|---|---|---|
| IS5 | – | 10 | 8 | 11 | 3 |
| IS6 | 10 | – | 4 | 10 | 0 |
| M1Lite | 8 | 4 | – | 10 | 1 |
| VD | 11 | 10 | 10 | – | 2 |
| smart | 3 | 0 | 1 | 2 | – |

## Round-robin ranking — wins/losses per opp across all pairs

| rank | algo | per-opp wins | losses | ties | net | decisive WR | total opps eval |
|---|---|---|---|---|---|---|---|
| 1 | **IS6** | 6 | 4 | **14** | **+2** | .600 | 24 |
| 1 | **smart** | 4 | 2 | 0 | **+2** | **.667** | 6 |
| 3 | **M1Lite** | 12 | 11 | 0 | +1 | .522 | 23 |
| 4 | **VD** | 12 | 13 | 8 | −1 | .480 | 33 |
| 5 | **IS5** | 7 | 11 | **14** | −4 | .389 | 32 |

**Tied for #1: IS6 (lots of ties with IS5 + edge over M1Lite/VD)** and **smart (smaller sample but no losses to VD/M1Lite)**.

## Pair-by-pair detail

### IS5 vs IS6 (n=10, both 0-0-10 → identical performance)

Every shared opp produces identical HP outcomes — IS5 and IS6 effectively play the same algorithm vs these 10 opponents. Notable opps:

| opp algo | user | IS5 | IS6 | verdict |
|---|---|---|---|---|
| funnel-rush-v6 | ameyg | 0/1 hp−40 | 0/1 hp−40 | TIE (both lose hard) |
| swap3 | aa0 | 0/1 hp−37 | 0/1 hp−37 | TIE (both lose) |
| goonmaxxing | zarifahmed997 | 0/1 hp−25 (×1), 1/1 hp41 (×1) | identical | TIE |
| dont-name-your-algo-hox-fende | foxhenderson | 1/1 hp43 (×2) | identical | TIE |
| Redemption | pipmy | – | – | TIE |
| python-algo-v2 | aa0 | – | – | TIE |
| python-algo-v3 | aa0 | – | – | TIE |

→ **IS5 ≡ IS6** at the replay level on shared opponents.

### IS5 vs M1Lite (n=8, IS5 4/8 / M1Lite 4/8, per-opp 3-5-0)

| opp algo | user | IS5 | M1Lite | verdict |
|---|---|---|---|---|
| funnel-rush-v7 | ameyg | **1/1 hp42** | 0/1 hp−43 | **IS5** |
| funnel-rush-v8 | ameyg | 0/1 hp−55 | **1/1 hp39** | **M1Lite** |
| swap3 | aa0 | 0/1 hp−37 | **0/1 hp−17** | M1Lite (HP) |
| python-algo | Arun__ls | 0/1 hp−30 | **1/1 hp42** | **M1Lite** |
| Redemption | pipmy | **0/1 hp−4** | 0/1 hp−37 | IS5 (HP) |
| python-algo_03 | Orangemath | 1/1 hp41 | **1/1 hp48** | M1Lite (HP) |
| python-algo-v3 | vik07x | 1/1 hp40 | **1/1 hp47** | M1Lite (HP) |
| python-algo-v3 | aa0 | **1/1 hp42** | 0/1 hp−32 | **IS5** |

→ M1Lite handles `python-algo` archetype (Arun__ls, v3 Orangemath/vik07x) better than IS5; IS5 handles funnel-rush-v7 + python-algo-v3 aa0 better. **Per-opp: M1Lite 5-3 over IS5.**

### IS5 vs VD (n=11, both 7/11, per-opp 2-5-4)

VD is per-opp better (5-2 with 4 ties). Notable:

| opp algo | user | IS5 | VD | verdict |
|---|---|---|---|---|
| funnel-rush-v6 | ameyg | 0/1 hp−40 | **0/1 hp−37** | VD (HP) |
| funnel-rush-v7 | ameyg | **1/1 hp42** | 0/1 hp−38 | **IS5** |
| swap3 | aa0 | 0/1 hp−37 | **0/1 hp−30** | VD (HP) |
| python-algo | Arun__ls | 0/1 hp−30 | **1/1 hp50** | **VD** |
| python-algo_03 | Orangemath | 1/1 hp41 | 1/1 hp41 | TIE |
| dont-name-your-algo-hox-fende | foxhenderson | 1/1 hp43 | 1/1 hp43 | TIE |
| Python-algo2 | NKessler | 0/1 hp−10 | **1/1 hp44** | **VD** |
| python-algo-v3 | vik07x | 1/1 hp40 | 1/1 hp40 | TIE |
| siege-smart-v3 | aa0 | 1/1 hp37 | **1/1 hp46** | VD (HP) |
| python-algo-v2 | aa0 | 1/1 hp40 | 1/1 hp40 | TIE |
| python-algo-v3 | aa0 | **1/1 hp42** | 0/1 hp−22 | **IS5** |

→ VD wins 5-2 per opp. IS5 only wins on funnel-rush-v7 and aa0/python-algo-v3.

### IS5 vs smart (n=3, IS5 3/3 / smart 2/3, per-opp 2-1-0)

Already covered in the [previous report](smart_oracle_vd_common_opp_analysis.md):

| opp | smart | IS5 | verdict |
|---|---|---|---|
| funnel-rush-v7 (ameyg) | 1/1 hp46 | 1/1 hp42 | **smart** (HP edge) |
| python-algo (aa0) | 0/1 hp−19 | **1/1 hp44** | **IS5** |
| Alan (pipmy) | 1/1 hp41 | **1/1 hp44** | IS5 (HP) |

### IS6 vs M1Lite (n=4, IS6 2/4 / M1Lite 1/4, per-opp 3-1-0)

| opp algo | user | IS6 | M1Lite | verdict |
|---|---|---|---|---|
| swap3 | aa0 | 0/1 hp−37 | **0/1 hp−17** | M1Lite (HP) |
| python-algo-witch | gencersarp | **1/1 hp41** | 1/1 hp40 | IS6 (HP) |
| Redemption | pipmy | **0/1 hp−20** | 0/1 hp−47 | IS6 (HP) |
| python-algo-v3 | aa0 | **1/1 hp42** | 0/1 hp−32 | **IS6** |

→ IS6 wins 3 of 4 per-opp.

### IS6 vs VD (n=10, IS6 7/10 / VD 4/10, per-opp 3-3-4)

| opp algo | user | IS6 | VD | verdict |
|---|---|---|---|---|
| python-algo | suchir-g | 0/1 hp−36 | **0/1 hp−26** | VD (HP) |
| funnel-rush-v6 | ameyg | 0/1 hp−40 | **0/1 hp−37** | VD (HP) |
| swap3 | aa0 | 0/1 hp−37 | **0/1 hp−30** | VD (HP) |
| Lazarus | foxhenderson | 1/1 hp43 | 1/1 hp43 | TIE |
| medusa-3 | aa0 | 1/1 hp36 | 1/1 hp36 | TIE |
| Redemption | pipmy | **1/1 hp39** | 0/1 hp−16 | **IS6** |
| dont-name-your-algo-hox-fende | foxhenderson | 1/1 hp43 | 1/1 hp43 | TIE |
| python-algo-v2 | aa0 | **1/1 hp42** | 0/1 hp−22 | **IS6** |
| python-algo-v2 | aa0 | 1/1 hp40 | 1/1 hp40 | TIE |
| python-algo-v3 | aa0 | **1/1 hp42** | 0/1 hp−22 | **IS6** |

→ IS6 sweeps the python-algo-v2/v3 / Redemption opps where VD loses HP. VD edges IS6 only on opps where both lose anyway.

### M1Lite vs VD (n=10, M1Lite 7/10 / VD 6/10, per-opp 6-4-0)

10 shared opps with NO ties — every replay produced a clear winner. M1Lite wins 6 of 10.

(Full row list omitted for brevity — saved in raw data; M1Lite wins on `python-algo (Arun__ls)`, `python-algo_03`, `Hox-Fenderson`, `Redemption`, `python-algo-siege`, `python-algo-v3 vik07x`. VD wins on `funnel-rush-v7 ameyg`, `swap3`, `rebirthV2`, `python-algo-v3 aa0`.)

### M1Lite vs smart (n=1, M1Lite 0/1 / smart 1/1)

| opp | smart | M1Lite | verdict |
|---|---|---|---|
| funnel-rush-v7 (ameyg) | **1/1 hp46** | 0/1 hp−43 | **smart** |

→ Only one shared opp; smart wins decisively.

### VD vs smart (n=2, VD 1/2 / smart 2/2)

| opp | smart | VD | verdict |
|---|---|---|---|
| funnel-rush-v7 (ameyg) | **1/1 hp46** | 0/1 hp−38 | **smart** |
| v6-tweak (aa0) | **1/1 hp22** | 1/1 hp8 | smart (HP) |

→ Smart sweeps VD on the 2 shared opps.

### IS6 vs smart — NO SHARED OPPS

IS6's last ranked match was earlier in the day; smart was uploaded after IS6 stopped getting matched. Cannot compare directly.

## What the data actually says

### Equivalence and dominance

- **IS5 ≡ IS6** on every shared opp (10 ties out of 10).
- **smart > VD** on shared opps (2-0).
- **smart > M1Lite** on shared opp (1-0; n=1 is small).
- **IS5 > smart** on shared opps (2-1; smart's only loss = the aa0/python-algo killshot).
- **M1Lite > IS5** on shared opps (5-3); **M1Lite > VD** (6-4).
- **IS6 > M1Lite** on shared opps (3-1); **IS6 > VD** (3-3 with 4 ties → IS6 effectively dominant given VD only wins HP-margin ties on opps both lose).

### Where the sample is informative

The most reliable comparisons (≥8 shared opps): **IS5 vs IS6 (10 ties), IS5 vs M1Lite (M1Lite +2), IS5 vs VD (VD +3), IS6 vs VD (IS6 +0 net but only because of 4 ties), M1Lite vs VD (M1Lite +2)**.

The smart comparisons are constrained by sample size — only 16 ranked games — but the directional signal vs VD/M1Lite is clean (smart wins all 3 H2H verdicts on the canonical funnel-rush-v7 opp).

### Final ranking based on shared-opp head-to-head

Tier 1 — equivalent dominance:
1. **IS6** (per-opp +2 net, never losses to anyone except by HP-tiebreaker; sweeps M1Lite 3-1)
1. **smart** (per-opp +2 net, .667 decisive WR; only loss is the killshot vs aa0/python-algo)

Tier 2 — middle-pack:
3. **M1Lite** (+1 net; beats IS5 5-3, beats VD 6-4, but loses to IS6 1-3 and smart 0-1)

Tier 3 — sub-baseline on shared opps:
4. **VD** (−1 net; loses to M1Lite 4-6, ties IS6 3-3 with 4 ties, loses to smart 0-2)
5. **IS5** (−4 net; identical to IS6 on shared opps but loses to M1Lite 3-5 and VD 2-5)

### Caveats

- **IS5 ≡ IS6 perfect tie** is suspicious. Either (a) the upload is functionally identical (in which case the ELO difference of 6 points is just sample noise), or (b) the engine is so deterministic on these matchups that they always produce the same result. Verifying would require diffing IS5 vs IS6 source.
- **smart's 6-opp sample** is too small to confidently slot above IS6 — both share +2 net but smart has zero ties (so its decisive WR .667 looks better than IS6's .600 mathematically).
- **Live ELO contradicts** this ranking somewhat (IS6 2006 > IS5 2000 > VD 1967 > M1Lite 1961 > smart 1897). Live ELO is influenced by which opponents each algo got matched against, which depends on upload time + what was active on the ladder; it's not a pure quality signal.
- **Shared opps are not random** — each algo plays whoever is on the ladder when it's matchmaking. M1Lite's strong record vs IS5 and VD is partly because it played later (different ladder population).
- The aa0 user dominates the dataset — `aa0/swap3`, `python-algo`, `python-algo-v2`, `python-algo-v3`, `python-algo-classic`, `siege-smart-v3`, `medusa-3`, `funnel-rush-v?` (uploaded by ameyg). Per-opp wins/losses cluster around aa0's iterations.
