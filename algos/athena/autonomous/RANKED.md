# RANKED — ranked-replay analysis + queued counters

Auto-analyzed 47 ranked matches for v13_second_ring (algo_id=360023):
**22 wins / 25 losses** as of 2026-04-24. All 47 replays parsed successfully
(0 malformed, 0 skipped). Source: `/replays/ranked/*.replay`.

## Loss breakdown by archetype

| archetype      | losses | median ELO | avg turns to loss | example match                                   |
|----------------|-------:|-----------:|------------------:|-------------------------------------------------|
| scout_rush     |     12 |       1786 |              67.6 | m15303336 vs oleh-v2 (1799) — lost T51, HP -9   |
| turret_castle  |      7 |       1714 |             101.0 | m15304872 vs please-work-man-im-tired (1776)    |
| demo_line      |      3 |       1837 |              76.0 | m15302640 vs python-algo (1875) — demos @[3,17] |
| mixed          |      3 |       1644 |              59.7 | m15302611 vs takedown1-algo — Scout+Demo flip   |

Archetype rules used: `scout_rush` = >70% Scout units and >=15 Scouts with
turret count <10; `demo_line` = >50% Demolishers and >=8 spawned; `turret_castle`
= opponent finishes with >=25 walls or >=10 turrets AND game reaches turn >=95
with us surviving but behind on HP (timeout loss); `mixed` = high mobile count
without a dominant unit; `support_caravan`, `flank_attack`, `sd_bomb` = none
observed in the loss set.

## Top losing matchups (>=3 losses)

- **scout_rush (12 losses)** — dominated by `oleh-v2` (7 losses, 2 wins vs us
  total — 22% winrate). Pattern: opponent masses ~22-28 Scouts in a single turn
  (peak wave avg 23.4), usually from a corner (BOTTOM_LEFT [0,13] or
  BOTTOM_RIGHT [27,13] equivalents on their side). Our defense chips the wave
  but insufficient shielding / turret concentration at the impact point lets
  ~8-12 Scouts through. Break turn median ~45 (mid-game, right when their MP
  banks a 15 MP spike). **Counter tactic:** (a) heavier Interceptor reserves to
  absorb single-wave Scout spam; (b) anti-corner turret ring at [3,12],[4,12]
  and [23,12],[24,12]; (c) stronger upgraded Support dispersion near the
  predicted impact tile — use the live opp-spawn-histogram we now have in the
  replay data to bias support placement toward their preferred corner.

- **turret_castle (7 losses)** — `please-work-man-im-tired` (3 losses) +
  `potential-algo`, `the-goat-algo`, `python-algo-v3-detectors`, `python-algo-
  turtle-new-submis`. Pattern: opponent builds 30-41 walls + 10+ turrets by
  turn 15, drip-feeds Scouts every turn to chip our HP starting as early as
  T1-T18, and simply outlasts us to turn 100 with a 4-12 HP lead. We can't
  breach their wall. Our Scout rushes die to their turret castle. **Counter
  tactic:** a dedicated *siege* variant with aggressive Demolisher trains
  (Demolishers out-range turrets at 4.5 vs 3.5) to punch gaps before sending
  Scouts; OR a late-game upgraded-Support burst that deletes 4+ HP in a single
  window. Cannot win turtle-vs-turtle with current budget allocation — need to
  lean offensive earlier.

- **demo_line (3 losses)** — only 3 losses and 3 wins (50/50), but ELO is
  highest (median 1837). Opponent pushes ~10-15 Demolishers from a deep
  back-edge spawn ([3,17] or [24,17]) to grind our back ranks. Our break turn
  is late (T71-72), so the wave succeeds on first contact. **Counter tactic:**
  turret-upgrade timing — upgraded turrets (20 dmg, 100 HP) one-shot chunks of
  a demo wave. Place 2-3 upgraded turrets at [10,12] and [17,12] to gate the
  demo lane before it compresses.

## Win breakdown

| archetype       | wins |
|-----------------|-----:|
| mixed           |    9 |
| scout_rush      |    7 |
| demo_line       |    3 |
| support_caravan |    2 |
| unknown (R0 boss fight, no spawns recorded) | 1 |

We routinely beat `scout_rush` (7/19 of the scout-rush encounters we saw
across both columns end as wins; losses cluster around oleh-v2 specifically —
not a general weakness). We beat `demo_line` 50/50, handle `mixed` fine, and
there's insufficient sample for `support_caravan` / `flank_attack` (0 losses
ever seen).

## Queued counters (priority 1-5, highest=5)

1. **[P5] v14_oleh_counter** — anti-`scout_rush` (oleh-v2 specialization).
   Pre-commit 2 upgraded Turrets at the oleh preferred-corner tile learned
   from replay histograms (7/7 oleh losses feature a peak Scout wave at a
   consistent corner); hold an Interceptor reserve that fires on T>=20 if
   opponent MP >10. Spec: shift 6 SP from mid-row Walls to corner Supports +
   upgraded Turrets; maintain 2 Interceptors at [6,8] and [21,8] when enemy
   MP >=12.

2. **[P5] v14_siege_breaker** — anti-`turret_castle`. Use Demolisher lane
   trains (8x Demolishers from [3,10] or [24,10] every 3 turns once MP bank
   hits 24) to force gaps, then follow with Scout sweep. Never play a pure
   defensive endgame against a turtler — we always lose tiebreaker. Spec:
   detect turtle signature (opp walls>=25 by T10) and auto-switch to
   aggressive MP saving + triggered Demo-Scout combo at T15 / T25 / T35.

3. **[P4] v14_demo_gate** — anti-`demo_line`. Place 2 upgraded Turrets at
   [10,12] and [17,12] by T8 before opponent's first demo train lands. Cost:
   16 SP (2x4 turret + 2x4 upgrade). Frees us to win the 50/50 demo matchup
   at 1800+ ELO.

4. **[P3] v14_adaptive_corner** — archetype-reactive defense. On turn 3
   inspect opponent's first-5-turn structure placement; if >=6 turrets on the
   left half, bias our Scout deploys right (and vice versa). Currently v13
   uses symmetric deploy — costs us ~2-3 breaches per game vs asymmetric
   defenses like oleh-v2.

5. **[P2] v14_interceptor_reserve** — general robustness. Keep an
   Interceptor reserve (2 units held as MP, not spawned) when enemy MP >= 12
   and prior-turn wave was Scout-heavy. Spec: tune the `mp_threshold`
   parameter in `algo_strategy.py` and bestof-N against oleh-v2 replay.

## Open questions

1. **oleh-v2's preferred-corner:** my spawn-histogram tracks (x,y) tuples,
   but I did not project them onto "corner" vs "center" buckets in this pass.
   Need to run a secondary script on the 7 oleh losses specifically to
   confirm which corner is preferred so v14_oleh_counter can pre-commit
   defenders correctly. (Data is in `/tmp/losses_refined.json` —
   `top_spawn_locs` per loss.)

2. **SD-bomb archetype:** not observed in this loss set. Either no opponent
   is using it yet, or our classifier missed it (self-destruct events aren't
   being inspected here). Worth a separate pass that aggregates
   `events.selfDestruct` across replays.

3. **Flank attacks:** also not observed (no spawns with >=50% edge-x usage
   in losses). If `please-work-man-im-tired` or `the-goat-algo` start mixing
   corner Scout waves with their turtle core, v14_oleh_counter would also
   cover them.

4. **R0/R1 boss fights** (5 wins): excluded from archetype counting because
   they're fixed-algo challenges with no meaningful opponent strategy signal.

5. **python-algo-jae-3** shows up as both 1 win + 3 losses: worth a
   dedicated bestof series to see if this opponent is a genuine counter or
   variance. Currently classified as scout_rush (2) and mixed (1).

---

## Data lineage

Classification script: `/tmp/classify_replays.py` (streaming JSON line parser,
handles 24MB replays without loading full file). Archetype features extracted
per replay: `opp_struct_early` (turret/wall/support count at deploy T5),
`opp_struct_late` (same at T10-15), `opp_mobile_total` (aggregate spawns),
`peak_wave` (largest single-turn mobile count), `top_spawn_locs`,
`break_turn` (first turn our HP fell). Heuristic overlay: `turret_castle` is
reclassified from `scout_rush`/`mixed`/`demo_line` when (a) game reaches T>=95
and (b) we survive with HP > 10 and (c) opponent has >=25 walls OR >=10
turrets late. Full per-replay feature dump saved to `/tmp/losses_refined.json`.
