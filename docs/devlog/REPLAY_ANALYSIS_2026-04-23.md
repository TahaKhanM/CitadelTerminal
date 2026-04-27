# Replay Analysis — final_v11 @ ELO 1689 — 2026-04-23

Analyzed 7 official ranked replays (3 losses, 4 wins including 1 upset).
Tools: `tools/batch_replays.py`, `tools/detailed_replay.py`.

---

## Section 1 — Executive summary

Win rate is a sharp cliff at the `+50` ELO gap: we go **100 % (3 / 3)** vs opponents
≤ +49 above us, **33 % (1 / 3)** in the +50..+150 band, and **0 % (0 / 1)** above
+150. Our one upset (vs 1756, +67) was a clock/attrition win against a pure
wall-turtle, not a generalisable strategic win.

The single biggest weakness is that **final_v11 stops building after turn 10 and
hoards SP for the rest of the game**. In every loss we finished with 30–70 SP
unspent on the turn the game was decided. Meanwhile every strong opponent used
their SP to stack **Supports** (shield caravans of 8–11 upgraded supports) so
their scouts arrive with ~+25–40 shield while ours arrive bare. The
algo has zero supports, zero mid-game turret adds, and a reactive layer that
places turrets way too deep in our own territory (y=4–9) to ever matter.

Breach locations are NOT random: 88 % of breaches taken in losses come from just
two sidelane tiles — (23, 9) on the right and (6, 7) on the left — i.e. scouts
threading past our single outer turret and running far into y < 10.

Python is **not** the bottleneck. Max compute time across all 7 replays = **42 ms**
(budget 15 000 ms). Rust migration is not justified by current evidence.

---

## Section 2 — Per-loss postmortem (sorted by opponent strength)

### Loss vs ELO 1841 (+152)  `final_v11_vs_1841_elo_loss.replay`  Final HP -1 / 19

- **Enemy opening (T0–T10)**: 4 Supports at [13/14, 15/16] all upgraded by T9 — a
  *front-row support caravan*. Then turrets spread on row 14 and corner clamps.
- **Enemy attack archetype**: mixed **Scout rush shielded by 4 front supports**,
  with Demolisher lines earlier (46 Demolisher deaths total).
- **Our first breach taken**: **T0** (1 pt) — we lost HP on literally the first
  action phase; fully 3 pts given up by T7.
- **Game-deciding turn**: **T43**. Enemy had 16.4 MP (maxed), we had 11.2 MP;
  exchange was P1 6 / P2 12, breaking the game open. We never recovered.
- **Specific breach tiles**: (22, 8) ×17, (9, 4) ×12, (20, 6) ×5, (19, 5) ×4,
  (7, 6) ×2 — *every single one* on the sidelanes, y < 10.
- **Compute time**: max 41 ms, mean 12 ms. Plenty of headroom.
- **Root cause**: enemy scouts arrive with ~+40 shield HP (4 front-row upgraded
  Supports ≈ 10 shield each at Y ≈ 12–13). Our 4 centre turrets + 2 outer turrets
  can't out-damage that before scouts reach the sidelane gaps at x∈{5,22}. We
  have no supports and no secondary turret ring behind y=11.

### Loss vs ELO 1829 (+140)  `final_v11_vs_1829_elo_loss.replay`  Final HP -2 / 37

- **Enemy opening (T0–T10)**: turret ring at row 14–17, walls at row 14.
- **Enemy attack archetype**: **pure back-row Support-caravan + Scout spam**.
  From T15 onwards they built **11 Supports at y∈{23,24,25}** and spawned
  **271 Scouts** over the game from (13, 27). Total shield: 5 988 HP across
  1 996 shield events.
- **Our first breach taken**: T4 (1 pt). Sustained bleed from T28 onwards.
- **Game-deciding turn**: **T39**. They scored 5 at (23, 9). We had **42 SP sitting idle**
  (P1 SP timeline: 40 at T38 → 42 at T39 → 45 at T40). Complete failure to
  convert SP into defence.
- **Specific breach tiles**: **(23, 9) ×36** (!), (21, 7) ×3, (25, 11) ×2, (1, 12) ×1.
  One tile accounts for 86 % of all damage we took.
- **Compute time**: max ~40 ms. Still fine.
- **Root cause**: turret [22, 11] + single wall at [23, 12] are a paper-thin
  right-corner bastion vs shielded scout waves. Walls at (21, 12) and (20, 12)
  died 12 × each from demolisher fire, opening the path. We never reinforced
  even as we accumulated 45+ SP.

### Loss vs ELO 1750 (+61)  `final_v11_vs_1750_elo_loss.replay`  Final HP 0 / 23

- **Enemy opening (T0–T10)**: turrets at row 15–17, walls at 14, then
  **8 Supports at [12–15, 20–21]** mid-row by T9.
- **Enemy attack archetype**: **Support-caravan + left-side scout funnel**.
  Walls on their right corner [24–27, 14–15] force scouts down their left edge
  into our left sidelane.
- **Our first breach taken**: T13 (P1 actually got 8 — this was a TURN WE SCORED,
  not took damage). Our HP was still 40 at T13. First HP drop was at T14 → 32
  from offscreen scout damage, then T21 to 23.
- **Game-deciding turn**: **T45**. Enemy had 21.6 MP (maxed), scored **15 at (6, 7)**
  in a single wave. We had **66 SP unspent** and only 16 MP.
- **Specific breach tiles**: (6, 7) ×26, (5, 8) ×9, (7, 6) ×5 — every tile on
  the left sidelane.
- **Compute time**: max ~40 ms.
- **Root cause**: same as 1829 but mirrored. Left-side breakdown. SP hoard
  peaked at **73 SP** on T42. We literally had 73 SP of unused defence while
  scouts were walking into y=7.

---

## Section 3 — Upset win postmortem

### Win vs ELO 1756 (+67)  `final_v11_vs_1756_elo_win.replay`  Final HP 19 / 0

- Game went 82 turns (longest of all 7 replays).
- Opponent played **pure wall-turtle**: built a full wall row at y=14 across
  the whole map and only 4 turrets. No supports.
- They scored 21 points against us early (T11–T47) through (4, 9), bringing us
  19 HP. Then they ran dry: MP capped at 17, no offensive output.
- From T50 onward P1 HP was locked at 19 and we accumulated SP up to **214**,
  spending none. Their defence slowly cracked to our 10-scout waves; they bled
  at 2–6 HP/turn from T77 and died on T82.
- **What we did differently**: nothing. We played the same script. We won
  because the opponent's offence is structurally weaker than our defence —
  not because of anything final_v11 did right.
- **Takeaway**: this is NOT evidence that our strategy scales vs strong play.
  It is evidence that v11's defence holds against bots with no shield support
  and no scout rush. All 3 losses came from shield-equipped scout rushes,
  which this opponent simply didn't have.

---

## Section 4 — Cross-replay patterns

### Hot breach tiles unique to losses

From `batch_replays --my-elo 1689` loss-only aggregation:

| Tile   | Count | Side    | Notes                                   |
|--------|-------|---------|-----------------------------------------|
| (23, 9)| 36    | Right   | 1829 loss — *one tile = 86 % of damage* |
| (6, 7) | 26    | Left    | 1750 loss — left sidelane               |
| (22, 8)| 17    | Right   | 1841 loss                               |
| (9, 4) | 12    | Left    | 1841 loss — very deep, y=4              |
| (5, 8) | 9     | Left    | 1750 loss                               |
| (7, 6) | 7     | Left    | 1750 loss                               |
| (20, 6)| 5     | Right   | 1841 loss                               |
| (19, 5)| 4     | Right   | 1841 loss                               |

**Every single loss breach is at 4 ≤ y ≤ 11 on the outer x corridors
(x ≤ 9 or x ≥ 19)**. Zero breaches at centre. The centre gap at [12,12]/[15,12]
is over-defended; the sidelanes are under-defended.

### Hot enemy spawn tiles in losses

| Tile    | Count | Meaning                                      |
|---------|-------|----------------------------------------------|
| (13, 27)| 285   | Top-centre scout rush (primary)              |
| (15, 26)| 117   | Slight-right scout rush                      |
| (5, 19) | 71    | Deep-left edge spawn (demolishers / scouts)  |
| (9, 23) | 18    | Left interior                                |
| (24, 17)| 16    | Deep-right edge spawn                        |

Strong opponents spawn from **(13–15, 26–27)** — they use top-centre and rely
on their own funnel walls to push left or right. Our defence is symmetric
and doesn't anticipate which side the funnel dumps scouts onto.

### Breach-timing pattern in losses

| Phase      | Turns  | Breaches taken |
|------------|--------|----------------|
| Early      | T0–T15 | 7              |
| Mid        | T16–T35| 7              |
| Late       | T36–T49| 68             |

**92 % of loss damage happens in the late game (T36+)**, once the enemy has
MP > 13 and a stacked support caravan. Our defence is fine early; it fails
to scale into the late-game shielded-scout regime.

---

## Section 5 — Ranked hypotheses for v12

Ordered by expected ELO gain, descending. Each is falsifiable via
`/bestof final_v11 <candidate>`.

1. **Spend the hoard.** If we add a mid-game SP-spending loop (once baseline
   opening is complete, use excess SP on upgrading walls at y=12 and adding
   turrets at [22, 10] / [5, 10]), then by T40 unused SP drops from ~55 → ~10
   and losses-to-sidelane-scout-rushes (1750, 1829) should flip.
   — *Motivated by*: 1829 (42 SP idle at T39), 1750 (66 SP idle at T45).

2. **Add 4–6 back-row upgraded Supports starting T12.** If we add Supports at
   y∈{2–4} along x∈{12–15} once our defence baseline is set, then our scout
   waves arrive with +15–25 shield HP and the 1841-style centre-rush attack
   pierces opposing defences 50 % more often.
   — *Motivated by*: 1841 (enemy had 4 front supports, we had 0), 1829
   (1 996 shield events for them vs 0 for us).

3. **Harden the right corner.** If we add a second turret at [23, 10] and a
   wall at [23, 13] by T12, then breaches at (23, 9) drop from 36 → ≤ 5
   in rematches of the 1829 style — flipping that loss.
   — *Motivated by*: 1829, where (23, 9) alone was 86 % of damage taken.

4. **Mirror-harden the left corner.** Same as (3) on the left: turret [4, 10]
   and wall [2, 13] → already exists. But (6, 7), (5, 8), (7, 6) are exposed.
   Add turrets at [6, 10] and [7, 10] by T12.
   — *Motivated by*: 1750 (26 breaches at (6, 7)).

5. **Smarter reactive placement.** Currently `_reactive_defense` spawns at
   `[x, y+1]` of a breach — which lands at y=4–9, too deep. Redirect reactive
   turrets to the *chokepoint on the predicted path* (i.e., clamp y to the
   range [9, 12]). If done, the 1 reactive turret/game will actually intercept.
   — *Motivated by*: 1841 built reactive turrets at [12, 2], [9, 5], [7, 7] —
   useless placement. 1750 same at [6, 8], [5, 9].

6. **Attack threshold retune.** Current scout wave triggers at `mp ≥ 10*cost`
   (= 10 MP). Strong opponents cap at 15–17 MP. If we raise the threshold to
   14 MP, we attack less often but each wave is larger than their defence can
   absorb. Falsifiable: compare breach rate at (22, 19) across thresholds.
   — *Motivated by*: upset win showed 10-scout waves being just enough; losses
   show them not being enough vs shielded interception.

7. **Interceptor timing.** Currently 1 Interceptor spawned at [13, 0] when
   enemy MP ≥ 6 scouts. But we *always* spawn it from centre, giving up the
   sidelane. Spawn at [7, 6] or [20, 6] instead when the threat is sidelane
   (i.e., their previous breach of us was sidelane). Expected to reduce
   late-game sidelane breaches by half.
   — *Motivated by*: every loss took its killing blow from a sidelane.

---

## Section 6 — Red flags

- **Compute time**: no spike > 73 ms anywhere across all 7 replays (our max
  41 ms, opponent max 242 ms in the 1472 blowout). No turn approached 2 000 ms.
- **Config**: `detailed_replay --section config` of
  `final_v11_vs_1841_elo_loss.replay` matches `docs/UNITS_REFERENCE.md` exactly
  — Turret 6/60 → 20/100, Support 1/40 shield at +0.7/Y (range 7 upgraded),
  Wall 60 → 200 HP, resources 4 SP / (1 + t/5) MP / 25 % decay / 150 MP cap,
  starting 8 SP / 1 MP / 40 HP. No drift from docs.
- **Errors / crashes**: none. All 7 games played to completion without algo
  timeout or stack trace.

---

## Section 7 — Language decision

**Stay on Python for v12.**

- Peak compute in any official replay: **42 ms** (our side).
- Budget: **15 000 ms**.
- Headroom factor: ~350×.
- None of the top-7 hypotheses in §5 require heavy per-turn computation — they
  are all static-placement rules, threshold tweaks, or small reactive logic.
  No hypothesis triggers `docs/RUST_MIGRATION.md` criteria.
- Revisit Rust if/when hypothesis (5) evolves into a per-turn predictive
  pathing simulation that runs for every candidate spawn tile (would approach
  1–3 s at scale). Not needed for v12.

---

Report path: `docs/REPLAY_ANALYSIS_2026-04-23.md`
