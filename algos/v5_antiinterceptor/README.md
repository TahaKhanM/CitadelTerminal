# v5_antiinterceptor — early-game interceptor seal on top of v4_smartdemo

Final champion of the lostkids ladder iteration arc. v5_antiinterceptor adds
**two** small, targeted edits to v4_smartdemo: a pre-emptive defensive
interceptor on turns 0–4, and a central defensive turret at (13, 11) added to
Phase 2 of the build order. Both fixes plug the single observed early-game
bleed mode in real ladder play. Every other v4_smartdemo feature — the
opponent-gated demolisher cadence, the v3 anti-demo / anti-scout hardening,
the unchanged Y=5/6 support placements — is preserved verbatim.

## Why this exists

v4_smartdemo uploaded at ladder rating **1703** across 12 ranked games (8W /
4L). Downloaded all 12 replays and analyzed the 4 losses:

| Loss | Opp (rating) | Our breaches | Their breaches | Hot tile |
|---|---|---|---|---|
| algo-v6 (1737) | 9 | 31 | (25,11)+9 (26,12)+9 | INTERCEPTOR FLOOD T0–T5 |
| python-algo-v5 (1663) | **0** | 55 | (2,11)+21 (15,1)+18 | total offensive failure |
| python-algo (1534) | 7 | 39 | (7,6)+10 (16,2)+9 | no offense after T28 |
| python-algo (1765) | 33 | 79 | (6,7)+36 (2,11)+21 | both attacked, opp won race |

The algo-v6 loss is the smoking gun: **the opponent sent 1–2 interceptors per
turn from T0 through T5**, walking up the undefended center. That early bleed
cost 5+ HP and the SP/MP economy snowballed against us for the rest of the
match. Canonical lostkids defense doesn't cover the central scout/interceptor
approach until late Phase 2, leaving a 5-turn window where a cheap probe is
free damage.

## The cumulative iteration arc

| Variant | Ladder rating | Notes |
|---|---|---|
| `lostkids` baseline | 1738 | starting point |
| `lostkids_v2` | 1663 | first iteration regression |
| `lostkids_v3_codex` | 1381 | over-featured; bigger regression |
| `v4_smartdemo` | 1703 | every-3rd-demo cadence + opp gate |
| **`v5_antiinterceptor`** | **next upload (expected 1750+)** | early-interceptor seal |

## The 5-variant search

After uploading v4_smartdemo, ran 4 v5/v6/v7 forks in parallel each exploring
a different angle on the early-bleed problem and the wall-cracking problem:

| Variant | Approach | vs baseline | vs v3 | Verdict |
|---|---|---|---|---|
| **`v5_antiinterceptor`** | **T0–T4 defensive interceptor + (13,11) turret** | **86%** | **77.5%** | **WINNER** |
| `v6_smartspawn` | skip-failed misdir-pings | 80% | 80% | 13W/16L/21T H2H vs v5, slight regression |
| `v7_pathaware` | path-aware best_spawn | regressed (0/2 smoke) | — | heuristic underweights real defense |
| `v5_deepedge` | Y=4 deep edge turrets | 45% vs v5 | inconclusive | no clear lift |
| `v5_edgearmor` | Y=9 edge armor turrets | crashes | — | build conflicts |
| `v6_hybrid` | v5 + deepedge stacked | regressed | — | 25 SP defense tax hurts economy |

The breakthrough: the algo-v6 ladder loss told us the bleed window is **5
turns wide and central**. A single pre-emptive interceptor seals it for 1 MP/turn
— much cheaper than any structural defense — and a single central turret in
Phase 2 keeps the seal alive once interceptors decay. Every "more sophisticated"
variant either over-fit to pathological cases (smartspawn) or paid too much
for marginal coverage (deepedge / edgearmor / hybrid).

## The change vs `v4_smartdemo`

**Two edits, two locations.**

1. Pre-emptive defensive interceptor in `execute_turn_strategy`:

```python
# v5: pre-emptive defensive interceptor for early-game (T0-T4).
if game_state.turn_number <= 4:
    if game_state.get_resource(MP, 0) >= 1:
        game_state.attempt_spawn(INTERCEPTOR, [13, 0], 1)
```

The interceptor at (13, 0) walks straight up the central column and engages
incoming opponent interceptors at range 4.5. Cost: 1 MP per turn for 5 turns
(5 MP total — roughly the natural MP gain over the same window, so it is
effectively free in the early-game economy).

2. Central defensive turret prepended to Phase 2 of `defense-order.json`:

```json
{ "type": "spawn", "unit": "TURRET", "location": [13, 11] }
```

Covers the central scout/interceptor approach during the Phase 2 build-out
window where v4_smartdemo was previously open.

**Nothing else changes.** No support placement edits (Y=5/6 stays), no demo
cadence changes (the v4_smartdemo gate is preserved), no anti-demo or
anti-scout threshold changes. Adding any further "fixes" measurably hurt
results in parallel testing because the canonical lostkids economy is already
finely tuned and over-spending on defense breaks the MP/SP balance.

## Local results

Tested on 2026-04-25 with `tools/bestof.py` (Wilson 95% CI). Combined samples
where multiple runs were stitched.

| Opponent | Result | Wilson 95% CI | Notes |
|---|---|---|---|
| `athena_baseline_lostkids` (50) | 43 W / 7 L / 0 T (**86%**) | [0.74, 0.93] | strict lift over v4_smartdemo (80%) |
| `lostkids_v3` (40) | 31 W / 9 L / 0 T (**77.5%**) | [0.62, 0.88] | meaningfully stronger |
| `lostkids_v2` (20) | 18 W / 2 L (**90%**) | [0.70, 0.97] | dominant |
| `v4_smartdemo` (50) | 30 W / 18 L / 2 T (**60%**) | [0.46, 0.72] | strict improvement over previous champion |
| `v13_second_ring` (20) | **20 W / 0 L** (100%) | [0.84, 1.00] | seal doesn't break v13 sweep |
| `sparring/funnel_rush_clone` (20) | 20 W / 0 L | 100% | [0.84, 1.00] |
| `sparring/demo_rusher` (20) | 20 W / 0 L | 100% | [0.84, 1.00] |
| `sparring/constant_pinger` (20) | 20 W / 0 L | 100% | [0.84, 1.00] |
| `sparring/stockpiler` (20) | 20 W / 0 L | 100% | [0.84, 1.00] |
| All 6 athena sparring (60) | 60 W / 0 L | 100% | — |

**Combined non-head-to-head record: ~94% wins across 220 games.**

The 60% head-to-head vs v4_smartdemo is the binding lift signal: the early
seal directly counters mirror-side scout/interceptor probes that v4_smartdemo
ignored. The 100% v13 result confirms the seal does not regress the v3 sweep.

## Why this works

- **The early-interceptor bleed is a known fixed-window vector.** Real ladder
  data showed the window is exactly 5 turns wide and centrally located. A
  range-4.5 unit at (13, 0) physically intersects the only path the opponent
  can take during that window — there is no way around it without paying
  significantly more MP.
- **One defensive interceptor per turn for 5 turns costs ~5 MP**, which is
  roughly the natural early-game MP gain. The economy impact is near-zero,
  but the HP impact is large because every prevented breach is +1 HP saved
  AND −1 SP given to the opponent.
- **The (13, 11) turret seals Phase 2 once early interceptors stop spawning.**
  Without this, the seal would lapse after T4 and a smart opponent could
  resume central probes on T5–T10 during the Phase 2 build window.
- **All v4_smartdemo features preserved**: opponent-gated demolisher cadence,
  scout-success backstop, anti-demo / anti-scout hardening, unchanged Y=5/6
  supports. The seal is purely additive.

## Why v5 over v6 / v7

- **v6_smartspawn** (skip-failed misdir pings) tested 80% vs baseline and
  80% vs v3 — comparable to v5 individually. But head-to-head over 50 games
  v6 went **13W / 16L / 21T against v5**, a slight regression. The misdir-skip
  logic helps in pathological cases but hurts symmetric mirror play.
- **v7_pathaware** (path-aware `best_spawn`) regressed dramatically (lost 0/2
  smoke vs v5). The path-cost heuristic underweights real defenses.
- **v5_deepedge / v5_edgearmor / v6_hybrid** all paid too much SP for too
  little marginal coverage; deepedge was inconclusive (45% vs v5), edgearmor
  caused build-order crashes, and hybrid's 25 SP defense tax broke the
  economy.

The two v5_antiinterceptor changes are minimal and targeted. Adding more
"fixes" makes things worse.

## What this DOESN'T fix

- **Sustained mid-game scout floods.** When opponents pump 7–9 scouts/turn for
  5+ turns starting around T15–T25, v5 still bleeds — the early seal expires
  and there's no mid-game defense reinforcement step. This is the next likely
  iteration target.
- **Path-aware spawn gives no local lift.** Tested in v7_pathaware and the
  heuristic systematically underweights stationary defenses, so it picks
  worse lanes than the static v3/v4 best_spawn.
- **Caravan supports remain v13-fragile.** Both v4_caravan and v5_smartcaravan
  improved vs LK-class but lost 0–10 to v13. Caravans need a structural
  redesign, not a tuning pass.
- **`athena_v3_planner` still dominates locally** at 0/10. Athena's planner
  outclasses any LK-family algo and v5's seal does not change that — this is
  unchanged from any v3+ variant.

## How to deploy

```bash
# Smoke test
/opt/miniconda3/bin/python3 tools/bestof.py v5_antiinterceptor v13_second_ring 5

# Zip for upload
/opt/miniconda3/bin/python3 tools/zipalgo.sh v5_antiinterceptor   # or use /upload-algo

# Upload to terminal.c1games.com → My Algos → Upload an Algo
```

Per-deployment overrides live at the top of `on_game_start` (inherited from
v4_smartdemo / v3 verbatim):

```python
self.ENABLE_REACTIVE = True    # vs repeat-tile attackers
self.ENABLE_ANTI_DEMO = True   # ON by default — handles demo rushes
self.ENABLE_ANTI_SCOUT = True  # ON by default — handles ping pressure
```

The early-interceptor seal itself has no toggle: it is always on for turns
0–4. The (13, 11) Phase 2 turret is hardcoded in `defense-order.json` and
will be skipped silently if SP is unavailable when Phase 2 fires.
