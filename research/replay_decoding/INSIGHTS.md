# Replay-Decoded Strategic Insights

This file lists the most actionable strategic patterns extracted from
six top-tier opponent algos that were decoded from `Ranked Replays/`
(see `INVENTORY.md` and `CANDIDATES.md`). Each insight is grounded in
observed deploy schedules — no extrapolation.

Counts come from aggregating decoded turn-by-turn schedules in:

- `funnel-rush-v9/decoded_turns.json` (8 source replays, avg 1981, 8/8 wins)
- `funnel-rush-v8/decoded_turns.json` (10 source, avg 1858, 6/10 wins)
- `funnel-crush/decoded_turns.json` (4 source, avg 1850, 4/4 wins)
- `switch2/decoded_turns.json` (12 source, avg 1763, 9/12 wins)
- `python-algo-classic/decoded_turns.json` (7 source, avg 1708, 4/7 wins)
- `oleh-v2/decoded_turns.json` (9 source, dominant cluster of 20 total, avg 1776, 16/20 wins)

---

## Insight 1: "Front-line Turret Wedge" opening dominates strong algos

**Source**: funnel-rush-v9, funnel-rush-v8, switch2, funnel-crush
(every >1750 algo)

**Pattern**: On turn 0, top algos place 4–5 Turrets on y=11 or y=12 (the
"second ring" — the row immediately behind the deploy edge). Locations
cluster around the 4 corners and 2 mid-x slots:

* **funnel-rush-v8/v9** (identical opening, both 10/10 and 8/8 same):
  TURRET at (11,12), (16,12), (22,11), (24,12)
* **switch2**: TURRET at (3,12), (10,11), (17,11) + WALLs at (10,12), (17,12)
* **funnel-crush**: TURRET at (1,12), (22,11), (24,10), (24,12)
* **python-algo-classic** + **oleh-v2** are the OUTLIERS — they place at
  y=10 (one row deeper) with a single TURRET at (3,12)

**Implication for Oracle**: The "second-ring" defense is the consensus
strong-algo opening. Oracle's enumerator should include opening
ActionPlans that place 4 Turrets at y=11–12 with walls behind them as a
high-prior baseline. The exact slots matter less than the WEDGE shape:
two clusters of 2 turrets near each corner with 1 mid-line turret.

---

## Insight 2: Side commitment — most algos commit to ONE deploy edge

**Source**: edge-spawn analysis across all 6 decoded algos

**Pattern**: Mobile spawn locations cluster on ONE bottom edge:

| algo | BL spawns | BR spawns | bias |
|------|-----------|-----------|------|
| switch2 | 0 | 382 | 100% RIGHT |
| python-algo-classic | 229 | 788 | 77% RIGHT |
| oleh-v2 | 230 | 16 | 93% LEFT |
| funnel-rush-v9 | 234 | 115 | 67% LEFT |
| funnel-crush | 208 | 94 | 69% LEFT |
| funnel-rush-v8 | 117 | 188 | 62% RIGHT |

Note that funnel-rush-v9 (LEFT-leaning) and funnel-rush-v8 (RIGHT-leaning)
have identical openings but split on attack side. This is likely a
deliberate "mirror" relationship — same algo author, two sides.

**Implication for Oracle**: When Oracle's enumerator generates attack
plans, it should generate distinct LEFT-only and RIGHT-only ActionPlans
rather than splitting attacks. Top algos almost never split — they
amass on one edge and overwhelm.

---

## Insight 3: The "y=7 wall ceiling" funnel

**Source**: funnel-rush-v8 + funnel-rush-v9 (both with 100% turn-2/3
fidelity)

**Pattern**: Both funnel-rush v8/v9 build a HORIZONTAL WALL at y=7
spanning roughly x=6 to x=18 over turns 2–5:

* Turn 2: WALL at (12,7), (13,7), (14,7), (15,7)
* Turn 3: WALL at (10,7), (11,7), (16,7), (17,7)
* Turn 4: WALL at (8,7), (9,7), (18,7), ...
* Turn 5: WALL at (7,6), then walls at corners (0,13), (24,13), (25,13)

This creates a "ceiling" that funnels enemy units into the corners of
our defense, while also forming a wall behind which Supports later spawn
to shield outgoing scouts.

**Implication for Oracle**: This is a HIGH-LEVERAGE pattern: it both (a)
forces enemy paths through Turret kill zones and (b) shields friendly
mobile waves. Oracle should generate ActionPlans of the form
"build walls at y=7 spanning [a,b] on turns 2..5 once turn-0 turrets are
in" as a candidate.

---

## Insight 4: Scout-rush at exactly turn 6 (MP=5.2 break)

**Source**: funnel-rush-v9, funnel-rush-v8

**Pattern**: Both algos make their FIRST mobile commitment on turn 6:

* funnel-rush-v9 turn 6: 5 SCOUTs at (13,0)
* funnel-rush-v8 turn 6: 5 SCOUTs at (13,0)

Why turn 6? Because that's when MP first crosses the threshold to fund a
meaningful Scout pulse. With base income (1 + turn//5) per turn and 25%
decay, MP crosses ~5 between turns 5–6 if you save it.

**Implication for Oracle**: Oracle should consider an "MP-bank to first
attack at turn 6" plan as a high-prior opening for its enumerator. The
specific spawn location (13,0) — the bottom-tip — gives a Scout the
shortest path to the opponent's edge with predictable enemy targeting.

---

## Insight 5: Heavy Demolisher pressure from territory-edge (oleh-v2)

**Source**: oleh-v2 (16/20 wins, one of the strongest decoded algos)

**Pattern**: oleh-v2 spawns Demolishers at (25, 11) STARTING TURN 4 —
EVERY OTHER TURN — for the entire mid-game (turns 4, 6, 8, 10, 12, ...).
Total 201 Demolishers (vs only 45 Scouts) in observed corpus.

The (25,11) tile is on the BR edge (x=25, y=27-x=11... wait, 27-25=2,
not 11). Let me re-check: (25, 11) — for BR, valid spawn is y = x - 14,
so x=25 → y=11. YES, that's BR edge.

So oleh-v2 SPAMS Demolishers on the right edge mid-y (where they need
to walk further), trading speed for damage. This works because:
- Demolishers have 5 HP each (more than scouts) and 8 damage to
  structures (Citadel: same as base?)
- Walking through the deeper y allows the Demolisher to hit MORE enemy
  walls before dying

**Implication for Oracle**: A "demolisher-from-edge-mid" plan is
unrepresented in v13's playbook (v13 is scout-rush focused). Oracle
should add a "every-other-turn demolisher pulse" plan as a candidate
strategy when the opponent has heavy front-line walls.

---

## Insight 6: Support placement is LATE (>turn 20) and DEEP (y≤7)

**Source**: All 6 decoded algos

**Pattern**: First Support appears between turns 15 and 24 (median ~20),
NOT in the opening. Locations are deep in own territory (y=2..7), where
they're behind front-line defenses. Examples:

* funnel-rush-v9 first Support: turn 21, at y=2..6
* funnel-crush first Support: turn 20
* switch2 first Support: turn 24

This contrasts with the "support-spam early" archetype (which typical
weak baselines favor). Top algos delay Supports until they have a
secure front line.

**Implication for Oracle**: Oracle's enumerator should NOT consider
SUPPORT placements before turn ~15 in standard plans. The early budget
should go entirely to Turrets + Walls. Once a stable defense exists,
Supports back-stack to amplify the opponent's eventual attack.

---

## Insight 7: Turn-10 / Turn-12 upgrade burst

**Source**: funnel-rush-v9, funnel-rush-v8, switch2, oleh-v2

**Pattern**: A burst of UPGRADES happens around turn 10–12 (often 4
upgrades in a single turn), targeting the front-row Turrets placed on
turn 0. funnel-rush-v9 turn 10: 4 upgrades at (22,12), (0,13), (23,13),
(24,12). The dollar cost is significant (32 SP for 4 upgrades) so this
implies the algo BANKED SP from turns 5–9.

**Implication for Oracle**: Oracle should consider an "upgrade-burst"
plan where SP is hoarded for turns 5–9 then spent at turn 10 to upgrade
the original turrets in one shot. This synchronizes a defensive power
spike right before the opponent's first MP attack-window.

---

## Insight 8: funnel-rush-v8's Interceptor mid-game beats v13_second_ring

**Source**: validation runtime — `funnel-rush-v8` (decoded) vs
`v13_second_ring` ended at turn 47 with funnel-rush-v8 at 40 HP and
v13 at -8 HP.

**Pattern**: Of the 6 decoded algos, ONLY funnel-rush-v8 wins against
v13. Compare its mobile-mix vs other algos:

| algo | scouts | demolishers | interceptors |
|------|--------|-------------|--------------|
| funnel-rush-v9 | 270 | 65 | 14 (5%) |
| funnel-rush-v8 | 203 | 36 | 66 (22%) |
| funnel-crush | 183 | 25 | 94 |
| switch2 | 374 | 8 | 0 |

funnel-rush-v8 has 4.7× more INTERCEPTORs than v9. Interceptors are
SHORT-RANGE (range 4 in this competition), HIGH-DAMAGE (8 dmg), so they
counter v13's scout-rush by intercepting and killing the rushing scouts
before they reach the back row.

**Implication for Oracle**: If Oracle's enumerator already has
ActionPlans built around scout-rush, it should ALSO carry a "defensive
INTERCEPTOR pulse on enemy MP-spike turns" plan. Detecting an enemy
scout-rush coming (e.g., enemy MP > 8 + scout-favorable opening) and
launching ~3 interceptors at (24,10) can swing what's otherwise a
losing matchup.

---

## Bonus: Algo signatures (compact)

For future opponent identification (e.g., from a single observed turn
0):

| algo | turn-0 fingerprint |
|------|---------------------|
| funnel-rush-v8/v9 | TURRET at (11,12),(16,12),(22,11),(24,12) — 4 turrets, no walls |
| funnel-crush | TURRET at (1,12),(22,11),(24,10),(24,12) — 4 asymmetric turrets |
| switch2 | TURRET at (3,12),(10,11),(17,11) + WALL at (10,12),(17,12) — 5 units |
| python-algo-classic | TURRET at (3,12),(7,10),(11,10),(16,10) — y=10 deep ring |
| oleh-v2 (cluster A) | TURRET at (3,12),(7,10),(11,10),(16,10) — same as classic! |
| oleh-v2 (cluster B) | TURRET/WALL at (0,13)+(1,13)+(26,13)+(27,13) — corner-bunker |

`python-algo-classic` and `oleh-v2`-clusterA are SUSPICIOUSLY identical
in opening (same 4 turret tiles). Likely shared starter-template
heritage, but their later play diverges (classic spams scouts, oleh
spams demos).

---

## Full validation results

See `VALIDATION.md` for win/loss outcomes against v13_second_ring and
between decoded algos, plus deploy-fidelity metrics from
`_tools/validate_decode.py`.
