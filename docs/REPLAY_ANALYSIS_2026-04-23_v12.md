# v12_sidelane_spend Replay Analysis — 2026-04-23

**Algo**: `v12_sidelane_spend` (ELO 1709, rank 9/10)
**Replays analyzed**: 5 losses, 0 wins.
**Record**: 0-5 against opponents in the +35 to +127 ELO bracket.
**Still missing**: any win on disk. Rank 9/10 with zero recorded wins means we cannot cross-check against positive examples. Upload v12 to ranked, let it queue 10-15 games, then re-download and re-run analysis if upsets appear.

---

## § 1 Executive Summary

v12's sidelane Turrets at [7,9]/[20,9] successfully eliminated the v11 hotspots (23,9)/(6,7)/(5,8) — only 1 breach at (5,8) and 6 at (6,7) across 5 losses combined. But **two dominant failure modes remain and a third emerged**. (1) **Shielded Scout waves funnel across the map via the [12,12]/[15,12] wall gaps and exit on the OPPOSITE-side BOTTOM edges**: (18,4)=39 breaches, (9,4)=22, (19,5)=10, (11,2)=8 dominate the loss data — 79 of 129 total breaches (61 %) on just these four tiles. (2) **Outer corners [0,13] and [27,13] remain undefended until T10-T28** — (0,13) cost us 4 HP in the 1822 loss, and the mirror-failure at (27,13) contributed to the 1744 loss. (3) **NEW: against a T0 Support-caravan opening (1805 + 1783), we are blown out by turn 43-51** — we have zero counter, and 2 of our 5 losses are this archetype. Our own offense is hyper-predictable: 90 % of all Scouts/Demolishers spawn at [13,0]/[14,0], producing 0 HP scored in 1822 (99 turns). Compute is comfortably inside budget (max 47 ms on our side across all 5 games). Config matches docs exactly. **Verdict: stay Python. Fix defense-ordering, add anti-Support opener, and — most importantly — diversify offense spawns.**

---

## § 2 Per-Loss Postmortem (strongest opponent first)

### 2.1 Loss vs 1836 ELO (+127 gap) — HP 23-36 — 99 turns

**File**: [v12_sidelane_spend_vs_1836_elo_loss.replay](../replays/v12_sidelane_spend_vs_1836_elo_loss.replay)

- **Enemy opening (T0-10)**: Balanced. T0 Turrets at [11,16]/[13,16]/[14,16]/[16,16] (mirror-center); wall line y=15 across; T12+ upgrade center Turrets.
- **Enemy attack archetype**: Pure central Scout spam — **931 Scouts total, 860 from (13,27) alone** + 71 from (14,27). No Demolishers, 0 Supports for most of game. Just raw MP→Scout conversion riding through our gaps. Minimal shielding (66 shield events, 9.0 avg).
- **Turn of first breach**: T24 (5 Scouts reached (4,9)). Before that, defense held.
- **Game-deciding turn**: T59. Opponent had 116 SP hoarded, spawned 50 units, 9 breached at (18,4) — dropped us 32→23 HP. We never recovered.
- **Breach tiles (ours)**: (18,4)=9, (4,9)=5, (6,7)=3. Cross-diagonal funnels through the [12,12]/[15,12] gaps.
- **Our response**: `_reactive_defense` ran but placed [18,5] / [4,10] / [6,8] — all already in-range of existing Turrets; didn't plug anything new. `_build_defense` had [7,9] upgraded by T16 — should cover (4,9), but single-Turret DPS can't handle 5 Scouts stacked in 2-3 frames.
- **Our offense**: 178 Scouts + 237 Demolishers + 89 Interceptors, **scored only 4 HP at (16,25)**. Same sterile center-push as 1822.
- **Root cause**: `_best_spawn` only evaluates [13,0]/[14,0] ([algo_strategy.py:172](../algos/v12_sidelane_spend/algo_strategy.py:172)). Against a center-heavy defense, we have no escape lane — we need a side-alternative.

### 2.2 Loss vs 1822 ELO (+113 gap) — HP 36-40 — 99 turns

**File**: [v12_sidelane_spend_vs_1822_elo_loss.replay](../replays/v12_sidelane_spend_vs_1822_elo_loss.replay)

- **Enemy opening (T0-10)**: Balanced front-row Turrets + full wall line y=14.
- **Enemy attack archetype**: Late-game Support caravan. T26-27: 4 Supports at [13,18]/[14,18]/[15,19]/[12,19], upgraded T29-32. Spawn pressure from 5 distinct tiles (827 Scout deaths total).
- **Turn of first (and only) breach**: T7. All 4 breaches at tile **(0,13)** — our undefended outer corner.
- **Game-deciding turn**: T7. We had 0 Turret coverage on (0,13); `outer_corners` only spawns at `t >= 10` ([algo_strategy.py:90-92](../algos/v12_sidelane_spend/algo_strategy.py:90)), and actually placed at T23. 4 HP lost; we never recovered because offense scored zero.
- **Breach tile**: (0,13)×4.
- **Our response**: `_reactive_defense` computed [0,14] (outside arena) → clamped to (0,13) which wasn't ours yet → no-op. Latent bug.
- **Our offense**: 177 Scouts + 237 Demolishers, **zero breaches**. Demolishers died at (15,13)/(12,13)/(15,12) — inside our own wall gaps.
- **Root cause**: Undefended outer corner (timing) + center-only offense failed vs 10 upg Turrets + 4 upg Supports.

### 2.3 Loss vs 1805 ELO (+96 gap) — HP -16-19 — blowout, turn 51

**File**: [v12_sidelane_spend_vs_1805_elo_loss.replay](../replays/v12_sidelane_spend_vs_1805_elo_loss.replay)

- **Enemy opening**: **T0 Support-caravan rush**. 2 Supports at [13,16]/[14,16] turn 0; 4 Supports total by T2; 3 upgraded by T8.
- **Enemy attack archetype**: Shielded Scout + Demolisher from 5 spawn points; 505 shield events totaling 4553 HP of shielding.
- **Turn of first breach**: T0 (1 HP), steady drip T5/T7/T9/T11.
- **Game-deciding turn**: T37. Enemy spent 50 SP on a 7-Scout wave at (18,4) — 7 HP dropped 32→25. T43/45/47/49/51 each added 4-18 more breaches.
- **Breach tiles (ours)**: (18,4)=25, (11,2)=8, (19,5)=6, (9,4)=4, (20,6)=4.
- **Our response**: Reactive Turrets placed at [18,5] etc., but fresh base Turrets (60 HP) can't out-DPS shielded Scouts.
- **Our offense**: 102 Scouts / 35 Demolishers scored 21 HP, mostly at (23,18)=12 — opponent's BOTTOM_RIGHT weak spot we never deliberately exploited.
- **Root cause**: No detection/response to T0 Support opening; base Turrets out-tempo'd by shield stacking.

### 2.4 Loss vs 1783 ELO (+74 gap) — HP -4-28 — blowout, turn 43

**File**: [v12_sidelane_spend_vs_1783_elo_loss.replay](../replays/v12_sidelane_spend_vs_1783_elo_loss.replay)

- **Enemy opening**: **T0 Support-caravan rush** (same archetype as 1805). 4 Supports upgraded by T9.
- **Enemy attack archetype**: 211 Scouts + 5 Interceptors from diverse spawn tiles (17,24)=33, (10,24)=31, (8,22)=26, (13,27)=23, (19,22)=22 — they probe every angle.
- **Turn of first breach**: T0 (1 HP lost before any defense exists). Steady 1-2 HP/turn through T11.
- **Game-deciding turn**: T38-T43. 4-8 breaches per turn through (9,4). Game ended T43 with us at -4 HP.
- **Breach tiles (ours)**: (9,4)=18, (8,5)=7, (26,12)=5, (18,4)=5, (19,5)=4. (9,4)/(8,5) are in the coverage zone of our [7,9] upgraded Turret — but shielded Scouts outpace it.
- **Our response**: Same reactive-defense failure; our sidelane Turret at [7,9] upgraded T19 was too late.
- **Our offense**: 72 Scouts + 25 Demolishers scored 12 HP at (22,19)=5 and (23,18)=5 — again, opponent's BOTTOM_RIGHT weakness ignored.
- **Root cause**: Same as 1805 — no anti-Support opener. **This is the second replay of the same failure mode, elevating it to the #1 issue.**

### 2.5 Loss vs 1744 ELO (+35 gap) — HP 32--2 — blowout, turn ~97

**File**: [v12_sidelane_spend_vs_1744_elo_loss.replay](../replays/v12_sidelane_spend_vs_1744_elo_loss.replay)

- **Side note**: we were **P2 (top player)** in this game. All coordinates below are reported raw; our algo's perspective is auto-mirrored. (Our T0 Turrets land on raw (11,16)/(13,16)/(14,16)/(16,16).)
- **Enemy opening (T0-10)**: Non-center. T0 Turrets at [3,12]/[8,10]/[19,10]/[24,12]; T1 [13,10]/[14,10]; corner walls at [0,13]-[2,13] and [25,13]-[27,13]; no center-stack. Different shape.
- **Enemy attack archetype**: Slow-build offense T23+: 8 base Supports at raw y=2-4 (their back row) for ~24 shield per unit. Massive Scout waves at T90-T97.
- **Turn of first breaches against us**: T0-T3, 1 HP each (4 HP total by T3). Then quiet 85 turns while both economies built.
- **Game-deciding turns**: T90/93/94/96/97 — opponent scored 6/6/3/2/9/8 = 34 HP in an 8-turn endgame blitz. We died T97 on HP.
- **Breach tiles (from our P2 perspective, mirrored back to bottom-player coordinates)**: (2,11)=19, (18,4)=12, (1,12)=4, (3,10)=3, (27,13)=2. **Note (27,13) — our outer corner mirror-failure.** Also (2,11)/(1,12) near BOTTOM_LEFT corner — same outer-corner region.
- **Our offense**: 42 HP scored (P2 total). Mostly at raw (2,16)→(2,11) equivalent and (18,23)→(18,4) equivalent — cross-diagonal funnels mirroring the same pattern our opponents use against us. When we do score, we score big.
- **Root cause**: Slow economy race + opponent's late-game Support stack + our corner defense (outer corners built T10+) couldn't hold the final blitz. Even though we scored 42 HP over the game, we lost the timing race on the final wave.

---

## § 3 Upset Win Postmortem

**No wins on disk.** (0 / 5 games.) Cannot analyze what works. See recommendation at top of report.

---

## § 4 Cross-Replay Patterns

### Breach tiles (all 5 losses, tiles on our own BOTTOM edges)

| Tile | Count | Edge | Appears in |
|---|---:|---|---|
| (18, 4) | **39** | BOTTOM_RIGHT | 1805, 1783, 1836, 1744 (mirror) |
| (9, 4) | **22** | BOTTOM_LEFT | 1783, 1805 |
| (19, 5) | 10 | BOTTOM_RIGHT | 1805, 1783 |
| (11, 2) | 8 | BOTTOM_LEFT | 1805 |
| (8, 5) | 7 | BOTTOM_LEFT | 1783 |
| (6, 7) | 6 | BOTTOM_LEFT | 1836, 1744 (mirror) |
| (4, 9) | 5 | BOTTOM_LEFT | 1836 |
| (26, 12) | 5 | BOTTOM_RIGHT | 1783 |
| (0, 13) | 4 | BOTTOM_LEFT corner | 1822 |
| (20, 6) | 4 | BOTTOM_RIGHT | 1805 |
| (21, 7) | 4 | BOTTOM_RIGHT | 1744 (mirror) |
| (27, 13) | 2 | BOTTOM_RIGHT corner | 1744 (mirror) |

**Dominant pattern**: 79 / 129 total breaches (61 %) occurred on just **(18,4) + (9,4) + (19,5) + (11,2)** — four tiles deep in the BOTTOM edges, reached by Scouts that crossed our wall gaps at [12,12]/[15,12] and traveled diagonally across the map. Our deep sidelane Turrets at [7,9]/[20,9] have upgraded range 3.5 and cover (5.5-8.5, 5.5-12.5) and (16.5-23.5, 5.5-12.5) respectively — that means **(18,4)/(11,2)/(19,5)/(9,4) are JUST OUTSIDE our coverage** (diagonal distance ≥ 3.5). We need second-ring Turrets closer to the edges.

### Enemy spawn tiles (all 5 losses)

| Tile | Count | Their edge (raw) |
|---|---:|---|
| (13, 27) | 1063 | TOP_LEFT/mid |
| (14, 27) | 714 | TOP_RIGHT/mid |
| (24, 17) | 217 | TOP_RIGHT corner |
| (11, 25) | 171 | near center-top |
| (3, 17) | 153 | TOP_LEFT corner |

Strong opponents default to central back-row spawns (13/14, 27) for straight pushes + opposite-corner spawns (24,17)/(3,17) for cross-map diagonal funnels. We see all the major deploy patterns.

### Turn-range pattern

- **Fast collapses (T43-51)**: 1805, 1783 — both T0 Support rush.
- **Mid-late bleed + endgame collapse (T85-97)**: 1744 — opponent outscaled us.
- **Attrition into narrow HP loss (T99)**: 1822, 1836 — we survived defensively but scored too little.

Three distinct modes. Two of the five are the Support-rush collapse — a single hypothesis would knock out **40 % of our losses.**

### Economy pattern

- Opponents routinely hoard **50-170 SP** (1836 reached 170 SP; 1744 opponent hit 52 SP; 1783 hit similar; 1822 hit 36). We hoard max ~11 SP.
- Our `_spend_hoard` fires (17-27 structures per game) but targets secondary sidelane positions [23,10]/[6,10]/[21,10]/[7,10] that **don't fire in any game's decisive turn**. We're building defense in the wrong 2nd-ring slots.

---

## § 5 Ranked Hypotheses for v13 (ordered by expected impact)

1. **Add anti-Support-caravan detection + early upgrade pivot.** If enemy Support count ≥ 2 by turn 3, skip `outer_turrets` opening (saves 8 SP by T3) and upgrade 2 center Turrets at T3-4 instead of T12-13. Upgraded Turret 20 dmg/frame one-shots any Scout regardless of shield. Motivated by 1805 + 1783 (both T0 Support rush, both blowouts by T43/T51). Expected: knock 2 of 5 losses out of the blowout bucket. (§2.3, §2.4)
2. **Add second-ring Turrets at [16,5] and [11,5] (or [15,5]/[12,5]) by T10.** The dominant breach tiles (18,4)=39, (9,4)=22, (19,5)=10, (11,2)=8 all lie outside the range of [7,9]/[20,9]. A Turret at [16,5] upgraded covers (14-19, 2-8) — directly protects (18,4)/(19,5)/(16,4). A Turret at [11,5] upgraded covers (9-14, 2-8) — protects (9,4)/(11,2)/(8,5). Cost ~12 SP for both base+upgrade. Motivated by 4 of 5 losses. (§2.1, §2.3, §2.4)
3. **Build outer corner Turrets [0,13] and [27,13] at T0, not T10.** 1822 cost us 4 HP at (0,13); 1744 cost us 2 HP at (27,13) mirror. Reorder `_build_defense` to place outer corners first. Delay one `outer_turrets` pair ([5,11]/[22,11]) by 1-2 turns. Motivated by 1822, 1744. (§2.2, §2.5)
4. **Diversify offensive spawns — add [3,10] and [24,10] as candidates.** `_best_spawn` only evaluates [13,0]/[14,0]. 1822 game: 502 mobiles spawned, 0 HP scored through the center. In 1783/1805 we did score at opponent's BOTTOM_RIGHT (23,18) by accident when a path happened to route there — but we didn't deliberately pivot. Add [3,10]/[24,10] to `_attack_phase` spawn_opts, let `_best_spawn` route to the cheaper lane. Motivated by 1822 (0 scored), 1836 (4 scored). (§2.1, §2.2)
5. **Add 3-4 upgraded Supports at [12,13]/[13,13]/[14,13]/[15,13] starting T6-T12.** At Y=13 each upg Support gives 10.1 shield/unit → 40 shield/Scout with 4 stacked. Our 10-Scout rushes would go from ~150 HP to ~550 HP through the defensive gauntlet. Cost 32 SP across T6-T12. Motivated by offensive sterility in 1822/1836. (§2.1, §2.2)
6. **Fix `_reactive_defense` clamping bug.** [algo_strategy.py:135](../algos/v12_sidelane_spend/algo_strategy.py:135): `build = [x, min(max(y+1, 0), 13)]` — for a breach at (0,13), computed build is (0,13) itself (the breached tile). Either the tile is already ours (no-op) or it's the undefended spot we just let breach. Rewrite to place backing Turret 1 tile INWARD — for a BOTTOM_LEFT breach at (x,y), build at (x+1, y) or (x, y+1) whichever has no structure and is covered. (§2.2, §2.5)
7. **Upgrade walls at [11,12] and [16,12] by T8.** Adjacent to our [12,12]/[15,12] gaps, these walls die repeatedly (11+6 deaths in 1805 alone). Upgraded walls (200 HP) tank Demolisher fire long enough for Turrets to work. Cost 4 SP. (§2.3)

---

## § 6 What to Preserve

1. **Deep sidelane Turrets at [7,9] and [20,9]** — v12's signature change. Across 5 losses, only 1 breach at (5,8) and 6 at (6,7); (23,9) and (21,7) basically solved. These are clearly load-bearing; do not remove.
2. **`_spend_hoard` mechanic** ([algo_strategy.py:100-129](../algos/v12_sidelane_spend/algo_strategy.py:100)) — we built 27-49 structures per game vs v11's ~11. Framework is right; just retarget placements per §5.2.
3. **`_best_spawn` path-damage scorer** ([algo_strategy.py:191-207](../algos/v12_sidelane_spend/algo_strategy.py:191)) — correctly picks least-dangerous option. Weakness is the *candidate set*, not the scorer. Keep the function, expand inputs per §5.4.
4. **`_pressure_response` interceptor-plug** — successfully dumped interceptors at [13,0] when enemy MP ≥ 6× Scout cost. Not the deciding factor but doesn't hurt.

---

## § 7 Red Flags

- **Compute (ours)**: max **47 ms** across all 5 losses, mean 11-23 ms. Well inside the 15 s budget; no spikes. (The 166 ms entry in the batch summary was opponent compute in 1744 where we played as P2; not relevant to us.)
- **Crashes**: none.
- **Config drift**: none. All 5 replays' live configs match [docs/UNITS_REFERENCE.md](UNITS_REFERENCE.md) exactly — Wall 1/2 SP & 60/200 HP, Support shield `1 + 0.7×Y` at range 7 upg, Turret 2/4 SP & 6/20 dmg & 2.5/3.5 range, Scout/Demo/Interceptor as documented. No surprises.
- **Zero wins on disk** — cannot learn what works against current meta. Priority: upload v12 to ranked, harvest wins.
- **`_reactive_defense` corner-breach no-op bug** — latent, see §5.6.
- **Offensive sterility in 2 of 5 games** (1822 scored 0, 1836 scored 4) — we are not scoring reliably, which means we lose attrition races even when defense holds.

---

## § 8 Language Verdict

**Stay Python.** No §5 hypothesis adds significant runtime logic. The heaviest proposal (§5.1 enemy-Support detection) adds one board scan per turn — microseconds. Our current 11-23 ms mean leaves 1000× headroom to the 15 s budget.

---

Report path: `docs/REPLAY_ANALYSIS_2026-04-23_v12.md`
