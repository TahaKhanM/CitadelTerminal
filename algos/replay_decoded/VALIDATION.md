# Validation Results

Two complementary validation gates:

## A. Deploy fidelity (replay vs decoded schedule)

For each candidate, we replay the FIRST 5 of its source replays through
`_tools/validate_decode.py` and compare:

* `S_rec` — fraction of structures in the original replay also in the
  decoded schedule for that turn (positional + type match)
* `M_rec` — fraction of (mobile_unit, x, y) keys in original also in
  decoded schedule
* `U_rec` — fraction of upgrade positions in original also in decoded

Deploy fidelity does NOT require count matching for mobiles; it tests
whether the decoded algo will SPAWN at the right positions, which is
what matters for Oracle's enumerator and for opponent-mirroring.

| algo | S_rec (avg) | M_rec (avg) | U_rec (avg) | verdict |
|------|-------------|-------------|-------------|---------|
| funnel-crush | 100.0% | 100.0% | 100.0% | PERFECT — fully deterministic |
| python-algo-classic | 100.0% | 100.0% | 100.0% | PERFECT — fully deterministic |
| switch2 | 95.4% | 90.7% | 81.4% | STRONG — light variance in upgrade timing |
| funnel-rush-v9 | 92.8% | 73.6% | 90.1% | STRONG — mobile counts vary turn-to-turn |
| funnel-rush-v8 | 88.5% | 74.1% | 61.9% | DECENT — different mobiles than v9 |
| oleh-v2 (clustered) | 53.1% | 70.8% | 27.0% | WEAK — adaptive algo, hard to decode |

`oleh-v2`'s low U_rec is because the algo's UPGRADE choices are clearly
opponent-adaptive — it upgrades different turrets in different matches
based on which side the opponent attacks. We re-decoded using only the
9 replays in the dominant turn-0 cluster (from 4 distinct opening
clusters total) and U_rec only rose modestly.

## B. Match-runtime validation

Decoded algos were run head-to-head and against `v13_second_ring`. All
matches are deterministic, so 2 runs of the same matchup return the
exact same outcome (we ran 2 runs each as a sanity check).

### Mirror matches (algo vs itself)

Goal: confirm no crashes; the decoded algo should at least play to a
reasonable number of turns against its own deterministic opponent.

| algo | winner | p1_hp | p2_hp | turns |
|------|--------|-------|-------|-------|
| funnel-rush-v9 | 1 | -8 | -8 | 30 |
| funnel-rush-v8 | 1 | 0 | 0 | 47 |
| funnel-crush | 1 | -3 | -3 | 43 |
| switch2 | 1 | -3 | -3 | 30 |
| python-algo-classic | 1 | 40 | 40 | 100 |
| oleh-v2 | 1 | 40 | 0 | 91 |

* All mirrors complete without crashes.
* Most end in a tie on HP (then p1 wins on tiebreak — this is engine
  behavior, not a defect).
* `python-algo-classic` mirror runs all 100 turns at full HP — both
  sides build a defense fortress and never break through.
* `oleh-v2` mirror is the cleanest decisive game (40-0 at turn 91) —
  whichever side moves first on the asymmetric layout wins big.

### vs `v13_second_ring`

Each matchup deterministic, so listed once:

| algo | winner | hp1 | hp2 | turns | meaning |
|------|--------|-----|-----|-------|---------|
| funnel-rush-v9 | v13 | -6 | 36 | 25 | v13 destroys it (v13 walls counter scout-rush) |
| funnel-rush-v8 | DECODED | 40 | -8 | 47 | Decoded WINS — interceptor mix beats v13 |
| funnel-crush | v13 | 0 | 29 | 62 | Long game, decoded barely loses |
| switch2 | v13 | -3 | 39 | 31 | v13 wins decisively |
| python-algo-classic | v13 | -1 | 40 | 59 | v13 wins; long game |
| oleh-v2 | v13 | -7 | 40 | 31 | v13 wins decisively |

* **funnel-rush-v8 BEATS v13_second_ring 47-turn finish at full HP**.
  This is significant — funnel-rush-v8 is decoded from algo that beat
  US 6/10 times (rating 1858). Its decoded form ALSO beats v13. So:
  (a) the decode captured real strength, and (b) v13 has a structural
  weakness vs interceptor-heavy mid-game pressure.
* **5/6 decoded algos LOSE to v13**. That's expected — v13 is one of
  our strongest baselines (matches v13 vs v13 are also tight). The
  decoded algos are STRONG but not omnipotent.

### Cross-matches (decoded vs decoded)

| matchup | winner | hp1 | hp2 | turns |
|---------|--------|-----|-----|-------|
| funnel-rush-v9 vs oleh-v2 | funnel-rush-v9 | 17 | -6 | 36 |
| funnel-crush vs python-algo-classic | funnel-crush | 27 | -22 | 54 |
| switch2 vs funnel-rush-v8 | funnel-rush-v8 | -15 | 15 | 44 |
| funnel-rush-v9 vs funnel-crush | funnel-crush | -8 | 1 | 25 |
| python-algo-classic vs oleh-v2 | oleh-v2 | -12 | 40 | 61 |

Implied tier ranking (from these 5 matches; small sample):

1. **funnel-rush-v8** beats switch2; also beats v13. Top performer.
2. **funnel-crush** beats funnel-rush-v9 AND python-algo-classic.
3. **funnel-rush-v9** beats oleh-v2 but loses to funnel-crush + v13.
4. **oleh-v2** beats python-algo-classic but loses to funnel-rush-v9 + v13.
5. **python-algo-classic** loses to both funnel-crush and oleh-v2.
6. **switch2** loses to funnel-rush-v8.

This roughly matches the original-replay rating ordering:
funnel-rush-v9 (1981) > funnel-rush-v8 (1858) > funnel-crush (1850) >
oleh-v2 (1776) > switch2 (1763) > python-algo-classic (1708).
The ordering isn't EXACTLY preserved — funnel-rush-v8 outperforms v9
in our matches because v8's mix of interceptors counters scout-spam
(v9 is pure-scout). This is consistent with v8 being a refinement of v9.

## Reproduction

```bash
# A. Fidelity:
python3 algos/replay_decoded/_tools/validate_decode.py

# B. Match runtime:
python3 algos/replay_decoded/_tools/run_validation.py
# Writes algos/replay_decoded/validation_log.jsonl
```

Raw match log in `validation_log.jsonl`.
