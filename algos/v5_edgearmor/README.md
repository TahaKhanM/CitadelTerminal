# v4_smartdemo — opponent-gated demolisher cadence on top of lostkids_v3

Final winner from a 6-variant parallel design search exploring how to break
through LostKids-class static defenses without giving up the v3 mirror parity
or the v3 sweep against active counter-offenders. v4_smartdemo adds **one**
small block of code: every 3rd attack fires demolishers instead of scouts,
**but only when `enemy_recent_demos < 2`** (i.e. the opponent isn't
counter-punching). All other v3 features are preserved verbatim.

## Why this exists

Six ranked games against LostKidsv3-class opponents (`Ranked Replays/lostkids_v3_codex/`)
told a clean story:

| Outcome | Count | Notes |
|---|---|---|
| Wins | 4 | 3 clear, 1 mislabeled |
| Losses | 2 | **Both** had opponent breaching at tile **(11, 2) ×23–24** — a deep BL-edge tile uncovered by canonical LK defense. |

Pure scout pressure against an LK-class wall line is predictable, and the
two losses showed the opponent winning the breach-rate race despite v3's
gated counters. The fix can't be *more defense* (would damage mirror parity);
it has to be *more offensive variety*. Demolishers crack walls — but firing
them indiscriminately collapses against v13-class opponents who send demos
of their own.

## The 6-variant search

Spawned 6 v4 forks in parallel, each exploring a different angle. Two
follow-up v5 variants combined the winner with caravan-style support stacks:

| Variant | Approach | vs baseline | vs v13 | Verdict |
|---|---|---|---|---|
| `v4_deepplug` | turrets at (11,4) + (16,4) | 35% | 100% | regressed vs v3 |
| `v4_caravan` | Y=13 supports (front-row) | 70% | 0% | crashes vs v13 |
| `v4_demoaggro` | every-3rd attack = demos (no gate) | 90% | 0% | crashes vs v13 |
| `v4_layered` | Y=4 second ring | 0% | 100% | regressed vs v3 |
| `v4_hybrid` | caravan + v3 features | 60% | 0% | crashes vs v13 |
| **`v4_smartdemo`** | **demoaggro gated by `enemy_recent_demos < 2`** | **85%** | **100%** | **WINNER** |
| `v5_smartcaravan` | smartdemo + Y=13 caravan | 87% | 0% | crashes vs v13 |
| `v5b_protected_caravan` | smartdemo + Y=11/12 caravan | 87% | 0% | still crashes vs v13 |

The breakthrough: every-3rd-attack demos give the offensive diversity needed
to break LK-class static defenses, while the `enemy_recent_demos < 2` gate
detects "active counter-offensive" opponents and falls back to pure scouts
where they already work. Caravans never recovered the v13 sweep, no matter
where they were stacked.

## The change vs `lostkids_v3`

One block in `evaluate_next_turn_strategy` (lines 817–847 of
`algo_strategy.py`):

```python
self.attack_count += 1

# FIXED-CADENCE demolisher rule: every 3rd attack fires demos,
# but ONLY if the opponent isn't an active counter-offensive type
# (signaled by enemy_recent_demos > 1). Against passive defenders
# (LK-class, funnel-rush) the cadence cracks open static walls.
forced_demo_cadence = (
    (self.attack_count % 3) == 0
    and self.enemy_recent_demos < 2
    and self.my_MP >= DEMOLISHER_MIN_MP
)

# Existing v3 scout-success backstop:
scouts_failing = (...)
losing_hp_race = (...)
if ((scouts_failing and losing_hp_race) or forced_demo_cadence) \
        and self.my_MP >= DEMOLISHER_MIN_MP:
    self.turn_strategy = f"attack_demo_{attack_side}"
else:
    self.turn_strategy = f"attack_{attack_side}"
```

`enemy_recent_demos` is the v3 EWMA tracker (`int(prev * 0.7) + this_turn`)
already maintained by `on_action_frame`. The gate threshold is `< 2`: zero
or one observed demos in recent memory means "passive defender, fire the
cadence"; two or more means "active offender, stay with pure scouts".

**Nothing else changes.** No support placement edits (Y=5/6 stays — that was
the v13-vulnerability vector in caravan variants), no defense-order edits,
no anti-demo or anti-scout threshold changes. The v3 README's deployment
toggles (`ENABLE_REACTIVE`, `ENABLE_ANTI_DEMO`, `ENABLE_ANTI_SCOUT`) all
apply unchanged.

## Local results

Tested on 2026-04-25 with `tools/bestof.py` (Wilson 95% CI). Combined samples
where multiple runs were stitched.

| Opponent | Result | Wilson 95% CI | Notes |
|---|---|---|---|
| `athena_baseline_lostkids` (40) | 32 W / 7 L / 1 T (80%) | [0.65, 0.90] | meaningfully stronger than v3 baseline |
| `lostkids_v3` (30) | 25 W / 5 L / 0 T (83%) | [0.66, 0.93] | strict improvement over v3 |
| `lostkids_v2` (20) | 15 W / 4 L / 1 T (75%) | [0.53, 0.89] | strong improvement |
| `v13_second_ring` (20) | **20 W / 0 L** (100%) | [0.84, 1.00] | gate works — dominant |
| `sparring/demo_rusher` (20) | 20 W / 0 L | 100% | gate fires — pure scouts |
| `sparring/funnel_rush_clone` (20) | 20 W / 0 L | 100% | demos crack walls |
| `sparring/constant_pinger` (20) | 20 W / 0 L | 100% | anti-scout streak fires |
| `sparring/stockpiler` (20) | 20 W / 0 L | 100% | |
| All 6 athena sparring (60) | 60 W / 0 L | 100% | |
| `athena_v3_planner` (10) | 0 W / 10 L | 0% | unchanged from baseline |

**Combined non-athena record: ~91% wins across 200+ games.**

The two binding constraints — `v13_second_ring` (gate must close) and
`athena_baseline_lostkids` (gate must open) — are both satisfied with
margin. The 100% v13 result is the same as v3; the 80% LK-baseline result
is the lift the cadence buys.

## Why this works

- **Pure-scout offense becomes predictable** for LK-class static defenders
  once they've seen 3–4 same-side waves. The every-3rd demo wave breaks the
  pattern and cracks the wall line on the followup, freeing scouts to score.
- **v13-class active offenders DO send their own demos**, which trips the
  `enemy_recent_demos >= 2` predicate and skips our cadence. We fall back to
  pure scouts — which is precisely what beat v13 in v3 already.
- **No support placement changes** (Y=5/6 stays). Every caravan variant
  (`v4_caravan`, `v4_hybrid`, `v5_smartcaravan`, `v5b_protected_caravan`)
  crashed against v13 regardless of support row, so the design space here is
  empty — caravans cannot be made v13-safe with the current v3 base.
- **All v3 features preserved**: anti-demo hardening, anti-scout-pressure
  hardening, scout-success backstop, `is_enemy_*_edge_blocked` guard. The
  cadence rule is purely additive.

## What this DOESN'T fix

- **`athena_v3_planner` remains dominant** at 0/10 locally. The gate doesn't
  help here — Athena's planner outclasses any LK-family algo regardless of
  offensive variety.
- **(11, 2) deep-edge breach is not directly patched.** v4_smartdemo wins
  the breach race rather than denying the tile; if the opponent stockpiles
  a single overwhelming wave (the 1782-style problem v3 also flagged) the
  cadence doesn't save us — we still lose the HP race even if we breached
  more often per turn.
- **Caravans remain blocked.** Both v5 follow-ups (smartcaravan and the
  Y=11/12 protected variant) confirm the mirror-side support placement is
  fundamentally incompatible with v13-class counter-offense; this needs a
  structural redesign, not a tuning pass.

## How to deploy

```bash
# Smoke test
/opt/miniconda3/bin/python3 tools/bestof.py v4_smartdemo v13_second_ring 5

# Zip for upload
/opt/miniconda3/bin/python3 tools/zipalgo.sh v4_smartdemo   # or use /upload-algo

# Upload to terminal.c1games.com → My Algos → Upload an Algo
```

Per-deployment overrides live at the top of `on_game_start` (inherited from
v3 verbatim):

```python
self.ENABLE_REACTIVE = True    # vs repeat-tile attackers (game 1620 deep breaches)
self.ENABLE_ANTI_DEMO = True   # ON by default — handles 1744-style demo rushes
self.ENABLE_ANTI_SCOUT = True  # ON by default — handles 1620-style ping pressure
```

The smartdemo gate itself has no toggle: it is always on, always gated by
`enemy_recent_demos`. Tune `DEMOLISHER_MIN_MP` (default 18) at the top of
`algo_strategy.py` if MP availability becomes the binding constraint on the
ladder.
