# v7_pathaware — Benchmark Report

**Date:** 2026-04-26
**Test driver:** `tools/bestof.py` (Wilson 95 % CI; sides alternated within each run)
**Logs:** `/tmp/v7_vs_*.log`

## TL;DR

**v7_pathaware regresses against the v5/v4 lineage and ties lostkids_v3.** The
path-aware primary-spawn replacement DOES NOT improve offensive efficacy in
the local environment — likely because the path-damage estimator's
"least-attackers-along-path" heuristic systematically picks dead-end paths
that look safe but fail to breach. Sparring partners and v13 are still
swept (they don't punish bad spawn picks), but every Lostkids-lineage
opponent (lostkids_v3, v4_smartdemo, v5_antiinterceptor, athena_baseline)
either ties or wins majority. **Recommendation: do NOT promote v7. Keep
v5_antiinterceptor as the deployed champion.**

## What v7_pathaware changes vs v5_antiinterceptor

Only `_execute_scout_attack` is touched:

```python
# v5_antiinterceptor (canonical Lostkids spawn pair):
first_loc = [4, 9]; second_loc = [3, 10]   # mirrored for attack_left

# v7_pathaware (path-aware best_spawn):
best_spawn = self._best_spawn(game_state, attack_side)
first_loc = list(best_spawn) if best_spawn else [4, 9]
second_loc = [first_loc[0] - 1, first_loc[1] + 1]  # offset for natural funnel
```

`_best_spawn` evaluates 5 candidate edge tiles (`[2,11], [3,10], [4,9],
[5,8], [6,7]`, mirrored for `attack_left`) and picks the one with the
lowest cumulative estimated turret damage on its `find_path_to_edge`
path. All other v5_antiinterceptor features (early-game interceptors at
(13,0), central turret at (13,11), smartdemo cadence, anti-scout streak,
anti-demo hardening) are inherited unchanged.

## Results

Six benchmarks ran in parallel (final wall time ~67 s for the longest).

| Opponent | n×2 games | v7_wins | opp_wins | ties | Win rate | Wilson 95 % CI | Verdict |
|---|---:|---:|---:|---:|---:|---|---|
| `sparring/funnel_rush_clone` | 10 | **10** | 0 | 0 | **100.0 %** | [0.722, 1.000] | meaningfully stronger |
| `v13_second_ring` | 10 | **10** | 0 | 0 | **100.0 %** | [0.722, 1.000] | meaningfully stronger |
| `lostkids_v3` | 20 | 10 | 10 | 0 | 50.0 % | [0.299, 0.701] | inconclusive (true 50 / 50) |
| `athena_baseline_lostkids` | 20 | 8 | **12** | 0 | 40.0 % | [0.219, 0.613] | regressed |
| `v4_smartdemo` | 20 | 7 | **13** | 0 | 35.0 % | [0.181, 0.567] | regressed |
| `v5_antiinterceptor` | 20 | 6 | **14** | 0 | 30.0 % | [0.146, 0.519] | regressed |

**Combined non-sparring decisive record:** 31 W / 49 L / 0 T = **38.8 %**
across 80 head-to-heads against the four Lostkids-lineage opponents.

## Comparison to v5_antiinterceptor (deployed champion)

For the same opponent set v5_antiinterceptor's record (200+ games of prior
testing — see `algos/v5_antiinterceptor/README.md`):

| Opponent | v7 win rate | v5 win rate | Δ |
|---|---:|---:|---:|
| `funnel_rush_clone` | 100 % | 100 % | tie |
| `v13_second_ring` | 100 % | 100 % | tie |
| `lostkids_v3` | 50.0 % | 77.5 % | **−27.5 pp** |
| `athena_baseline_lostkids` | 40.0 % | 86.0 % | **−46.0 pp** |
| `v4_smartdemo` | 35.0 % | 60.0 % | **−25.0 pp** |
| `v5_antiinterceptor` (head-to-head) | 30.0 % | (parent) | **−40 pp from a true 50 / 50 mirror** |

Every Lostkids-lineage matchup regresses by 25–46 pp. Sparring partners
and v13 don't differentiate because their defenses are weak enough that
even a sub-optimal spawn breaches.

## Why path-aware spawn loses

Three plausible mechanisms, in order of suspected impact:

1. **`_path_damage_estimate` undercounts upgraded-turret coverage.** The
   estimator counts `len(get_attackers(p, 0)) * tdmg` per path tile, which
   treats every turret-in-range as one shot per tile. Real turrets fire
   every frame, and scouts pass through each tile in 1 frame — so a single
   upgraded turret in range deals 20 dmg per frame the unit is in range,
   not 20 once. Spawn tiles whose paths cross 1 long-range upgraded turret
   look "safer" than paths crossing 2 short-range base turrets, but in
   practice the upgraded turret does much more damage.
2. **Off-canonical spawn tiles route through dead-end pockets.** Tiles
   like `(2, 11)` and `(6, 7)` are deeper into our half — paths from them
   wrap around our own walls and may reach the enemy edge through tiles
   the canonical (4, 9)/(3, 10) spawn doesn't, but those longer paths
   spend more frames in range of our own structures (slowing scouts) and
   also hit unexpected turret coverage on the enemy side.
3. **Mirror asymmetry.** v5_antiinterceptor's canonical pair forces a
   predictable funnel; in mirror games both sides have the same defensive
   layout calibrated against this funnel. v7's variable spawn breaks the
   symmetry asymmetrically — opp's canonical defense was designed for the
   canonical attack and crushes alternates.

## Verdict

**v7_pathaware is rejected.** Path-aware spawn was the most theoretically
attractive of the v5/v6/v7 candidates, but it underperforms in every
Lostkids-lineage matchup that actually distinguishes algos in our local
environment. The two variants that DID survive comparison —
`v5_antiinterceptor` (championed) and `v6_smartspawn` (close second) —
both kept the canonical (4, 9) / (3, 10) primary spawn intact.

The path-damage heuristic could plausibly be salvaged with:
- A frame-aware estimator (`damage = attackers × frames_in_range × dmg_per_frame`);
- Restricting candidates to a 2-tile window around the canonical pair (so
  mirror parity is preserved while only side-stepping confirmed-failed pings);
- Falling back to canonical when `best_spawn`'s estimate is within 1.2× of
  the canonical estimate (i.e., only divergence on STRONG signals).

But none of those are obviously worth more iteration cycles than continued
ladder-replay-driven tuning of v5_antiinterceptor.

## Raw logs

```
/tmp/v7_vs_funnel.log     (10 games)   v7_pathaware  10–0   funnel_rush_clone
/tmp/v7_vs_v13.log        (10 games)   v7_pathaware  10–0   v13_second_ring
/tmp/v7_vs_lk3.log        (20 games)   v7_pathaware  10–10  lostkids_v3
/tmp/v7_vs_athena.log     (20 games)   v7_pathaware  8–12   athena_baseline_lostkids
/tmp/v7_vs_v4smart.log    (20 games)   v7_pathaware  7–13   v4_smartdemo
/tmp/v7_vs_v5.log         (20 games)   v7_pathaware  6–14   v5_antiinterceptor
```

Each log contains the per-game line plus the `RESULTS` block plus the
`SUMMARY_JSON` with point estimate and Wilson CI. Re-run with:

```bash
/opt/miniconda3/bin/python3 tools/bestof.py v7_pathaware <opponent> <n> > /tmp/v7_vs_<short>.log 2>&1
```
