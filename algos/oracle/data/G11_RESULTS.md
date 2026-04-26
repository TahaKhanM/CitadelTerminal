# G11 — Athena replay-trace eval (Phase 7)


**Setup**: For each ranked v13 replay, replace v13 (player 1) 
with Athena's offense pipeline and replay the recorded 
opponent (player 2) actions verbatim through 
`evaluate_action_phase`. Carry HP turn-to-turn. Declare 
`athena_predicted` from final HP comparison.


## Aggregate

- Replays evaluated: **47** of 47
- Skipped: 0
- v13 actual: 22W / 25L (rate 0.468, Wilson LB95 0.333)
- Athena predicted: 39W / 8L / 0T / 0C (rate 0.830, Wilson LB95 0.699)
- Wall clock: 12.8s

## Phase 7 gate

| Metric | Value |
|---|---|
| v13 actual win rate | 0.468 |
| Athena predicted win rate | 0.830 |
| Delta (athena − v13) | **+0.362** |
| Gate threshold (Δ ≥ 0.15) | **PASS** |

## Per-replay results

| # | match_id | opponent | elo | boss | v13 actual | Athena pred | Athena hp_self | Athena hp_opp | turns | wall (s) |
|---|---|---|---|---|---|---|---|---|---|---|
| 1 | 15302602 | gooder-maybe | 1453 |  | win | win | 26.0 | -7.0 | 13 | 0.11 |
| 2 | 15302606 | python-algo-v3 | 1612 |  | loss | win | 36.0 | -5.0 | 14 | 0.24 |
| 3 | 15302609 | diego_v2 | 1486 |  | win | win | 29.0 | -7.0 | 13 | 0.15 |
| 4 | 15302611 | takedown1-algo | 1644 |  | loss | win | 23.0 | -7.0 | 13 | 0.10 |
| 5 | 15302614 | R2_Infiltrator | 0 | Y | win | win | 19.0 | -1.0 | 12 | 0.07 |
| 6 | 15302615 | R1_Sawtooth | 0 | Y | win | win | 40.0 | -5.0 | 14 | 0.17 |
| 7 | 15302616 | R3_Jukebox | 0 | Y | win | win | 40.0 | -5.0 | 14 | 0.11 |
| 8 | 15302618 | python-algo-turtle-v4 | 1609 |  | win | win | 40.0 | -5.0 | 14 | 0.32 |
| 9 | 15302620 | python-algo-master | 1556 |  | win | win | 19.0 | -1.0 | 12 | 0.09 |
| 10 | 15302621 | R4_Champion | 0 | Y | win | win | 40.0 | -5.0 | 14 | 0.13 |
| 11 | 15302622 | python-algo3 | 1629 |  | win | win | 19.0 | -1.0 | 12 | 0.36 |
| 12 | 15302625 | doublegap | 1736 |  | win | win | 28.0 | -5.0 | 13 | 0.31 |
| 13 | 15302627 | algo4 | 1757 |  | win | win | 40.0 | -2.0 | 14 | 0.33 |
| 14 | 15302638 | python-algo-turtle-new-submis | 1846 |  | loss | win | 40.0 | -5.0 | 14 | 0.39 |
| 15 | 15302640 | python-algo | 1875 |  | loss | win | 35.0 | -1.0 | 12 | 0.23 |
| 16 | 15302704 | python-algo1 | 1758 |  | win | win | 24.0 | -1.0 | 18 | 0.24 |
| 17 | 15302827 | python-algo-baseline | 1775 |  | loss | win | 36.0 | -1.0 | 14 | 0.17 |
| 18 | 15303042 | banana | 1926 |  | loss | win | 36.0 | -1.0 | 12 | 0.34 |
| 19 | 15303107 | python-algo | 1619 |  | win | win | 29.0 | -1.0 | 17 | 0.29 |
| 20 | 15303134 | python-algo-jae-2 | 1772 |  | loss | win | 25.0 | -4.0 | 20 | 0.24 |
| 21 | 15303183 | diego_v2 | 1929 |  | loss | win | 35.0 | -7.0 | 15 | 0.20 |
| 22 | 15303255 | oleh-v2 | 1763 |  | loss | win | 35.0 | -5.0 | 13 | 0.35 |
| 23 | 15303276 | oleh-v2 | 1700 |  | win | win | 35.0 | -5.0 | 13 | 0.13 |
| 24 | 15303336 | oleh-v2 | 1799 |  | loss | win | 35.0 | -5.0 | 13 | 0.17 |
| 25 | 15303408 | oleh-v2 | 1807 |  | loss | win | 36.0 | -1.0 | 16 | 0.21 |
| 26 | 15303451 | the-goat-algo | 1771 |  | loss | win | 7.0 | -6.0 | 24 | 0.61 |
| 27 | 15303499 | oleh-v2 | 1728 |  | loss | win | 35.0 | -7.0 | 16 | 0.14 |
| 28 | 15303546 | oleh-v2 | 1704 |  | win | win | 35.0 | -5.0 | 13 | 0.16 |
| 29 | 15303601 | oleh-v2 | 1824 |  | loss | win | 35.0 | -5.0 | 13 | 0.14 |
| 30 | 15303643 | python-algo | 1502 |  | win | loss | 0.0 | 7.0 | 24 | 0.32 |
| 31 | 15303734 | python-algo-jae-3 | 1786 |  | loss | win | 35.0 | -7.0 | 13 | 0.39 |
| 32 | 15303878 | oleh-v2 | 1706 |  | loss | win | 35.0 | -5.0 | 13 | 0.23 |
| 33 | 15303926 | oleh-v2 | 1783 |  | loss | win | 35.0 | -5.0 | 13 | 0.12 |
| 34 | 15303999 | python-algo | 1599 |  | win | win | 7.0 | -1.0 | 22 | 0.13 |
| 35 | 15304067 | python-algo-jae-3 | 1639 |  | win | win | 36.0 | 0.0 | 12 | 0.13 |
| 36 | 15304106 | python-algo-jae-3 | 1714 |  | loss | win | 25.0 | -4.0 | 20 | 0.53 |
| 37 | 15304224 | please-work-man-im-tired | 1714 |  | loss | loss | -1.0 | 34.0 | 24 | 0.56 |
| 38 | 15304426 | python-algo-v5 | 1468 |  | win | loss | -1.0 | 7.0 | 24 | 0.34 |
| 39 | 15304485 | python-algo-v8 | 1837 |  | loss | win | 36.0 | -1.0 | 16 | 0.27 |
| 40 | 15304561 | python-algo-v3-detectors | 1686 |  | loss | loss | -1.0 | 19.0 | 24 | 0.56 |
| 41 | 15304630 | please-work-man-im-tired | 1612 |  | loss | loss | -1.0 | 34.0 | 24 | 0.53 |
| 42 | 15304771 | python-algo-jae-3 | 1641 |  | loss | win | 25.0 | -5.0 | 20 | 0.31 |
| 43 | 15304872 | please-work-man-im-tired | 1776 |  | loss | loss | -1.0 | 34.0 | 24 | 0.50 |
| 44 | 15304981 | python-algo-jae-4 | 1112 |  | win | win | 36.0 | -1.0 | 12 | 0.25 |
| 45 | 15305087 | streeter | 1444 |  | win | win | 10.0 | -1.0 | 22 | 0.28 |
| 46 | 15305215 | potential-algo | 1639 |  | loss | loss | -10.0 | 14.0 | 27 | 0.52 |
| 47 | 15305301 | new-strat-algo | 1576 |  | win | loss | 0.0 | 8.0 | 25 | 0.39 |
