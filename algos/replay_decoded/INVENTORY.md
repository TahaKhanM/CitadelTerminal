# Replay Inventory

Source: `Ranked Replays/` (427 total replays across 11 of OUR algos).
Opponents identified by name parsed from filename
(`<our_algo>_<win|loss>_<opponent_name>_<rating>_<match_id>.replay`).

The replay header contains no opponent algo_id (player names anonymized
to "algo1"/"algo2" inside `endStats`); the filename is the only source
of truth for opponent identity.

## Top 25 opponents by replay count

| name | n_replays | avg_rating | max_rating | wins_against_us | losses_against_us |
|------|-----------|------------|------------|-----------------|-------------------|
| python-algo | 89 | 1565 | 1976 | 39 | 50 |
| oleh-v2 | 20 | 1776 | 2282 | 16 | 4 |
| defensive-order-algo | 13 | 1405 | 1684 | 7 | 6 |
| switch2 | 12 | 1763 | 1925 | 9 | 3 |
| python-algo1 | 11 | 1476 | 1758 | 1 | 10 |
| python-algo-jae-3 | 11 | 1624 | 1786 | 9 | 2 |
| funnel-rush-v8 | 10 | 1858 | 2211 | 6 | 4 |
| funnel-rush-v9 | 8 | 1981 | 2141 | 8 | 0 |
| terminator | 7 | 1581 | 1654 | 7 | 0 |
| python-algo-v5 | 7 | 1619 | 1735 | 5 | 2 |
| python-algo-classic | 7 | 1708 | 1718 | 4 | 3 |
| python-algo-babayaga3 | 6 | 1687 | 1732 | 3 | 3 |
| python-algo-baseline | 6 | 1542 | 1583 | 5 | 1 |
| python-algo-claude | 6 | 1463 | 1476 | 4 | 2 |
| python-algo-v11 | 5 | 1712 | 1731 | 1 | 4 |
| python-algo-v3-1 | 5 | 1302 | 1460 | 0 | 5 |
| algo12 | 5 | 1545 | 1607 | 0 | 5 |
| streeter | 5 | 1604 | 1655 | 3 | 2 |
| python-algo-tnb | 5 | 1468 | 1544 | 5 | 0 |
| funnel-crush | 4 | 1850 | 1957 | 4 | 0 |
| python-algo-turtle-v4 | 4 | 1598 | 1601 | 2 | 2 |
| econ-support-greedy | 4 | 1400 | 1423 | 0 | 4 |
| python-algo-v10 | 4 | 1574 | 1645 | 1 | 3 |
| python-algo-v3 | 4 | 1316 | 1402 | 1 | 3 |

## Notable observations

* **funnel-rush family** has 32 distinct version names (v5..v94, mostly
  iterative variants of the same author's algo). v8 (10) and v9 (8) have
  the most data and dominate ratings (1858/1981 avg).
* **oleh-v2** (20 replays, 1776 avg, max 2282!) is the single most
  data-rich strong opponent.
* **The strongest single algo we've seen** is `funnel-rush-v9` (avg 1981,
  beat us 8/8) — a perfect decode candidate.

## Output JSON

Full machine-readable inventory at `_tools/inventory.json`.

## Reproduction

```bash
python3 algos/replay_decoded/_tools/inventory.py
```
