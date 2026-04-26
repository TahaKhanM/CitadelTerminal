# Decode Candidates

Selection criteria:
- ≥3 replays
- avg rating > 1700 (worth decoding — competitive)
- consistent turn-0 opening across replays (≥80% of replays share the
  same opening structures, validated by clustering all 4-tuples of
  (unit, x, y) emitted on turn 0)

## Picked candidates

| name | n_replays | avg_rating | max_rating | wins_against_us | turn-0 consistency | notes |
|------|-----------|------------|------------|-----------------|---------------------|-------|
| **funnel-rush-v9** | 8 | 1981 | 2141 | 8 (100%) | 8/8 identical | Strongest single algo we've faced. Perfect win record. |
| **funnel-rush-v8** | 10 | 1858 | 2211 | 6 (60%) | 10/10 identical | Same opening as v9; likely a sibling. Larger sample. |
| **funnel-crush** | 4 | 1850 | 1957 | 4 (100%) | 4/4 identical | Distinct from funnel-rush family. Perfect win record. |
| **switch2** | 12 | 1763 | 1925 | 9 (75%) | 7/12 dominant | Most-played strong algo, but 3 distinct openings (perhaps adapts to opponent side). |
| **python-algo-classic** | 7 | 1708 | 1718 | 4 (57%) | 7/7 identical | Stable. Decent strength. |
| **oleh-v2** | 20 | 1776 | 2282 | 16 (80%) | 9/20 dominant cluster (4 clusters total) | Most data; multi-modal opening (4 clusters: 9 / 6 / 3 / 2 split). Re-decoded using only the dominant 9-replay cluster (see `_tools/decode_oleh_clustered.py`); fidelity is still imperfect (53% S, 27% U) because the algo is opponent-adaptive. |

## Rejected candidates

* `python-algo` (89 replays, 1565 avg) — ambiguous name covers many
  unrelated authors; can't reliably aggregate. Rating too low.
* `funnel-rush-v7-1` (3 replays, 2092 avg) — too few replays.
* `repentance` (3, 1786) — too few replays.
* `algo-v4` (3, 1777) — only 3 replays AND lost all.
* `python-algo-babayaga2` (3, 1726) — small sample; v3 has more data.

## Output

For each picked candidate, `algos/replay_decoded/{name}/` contains:
- `algo_strategy.py` (replays the decoded schedule)
- `decoded_turns.json` (the turn-by-turn schedule)
- `algo.json`, `run.sh`, `gamelib/` (standard plumbing)
