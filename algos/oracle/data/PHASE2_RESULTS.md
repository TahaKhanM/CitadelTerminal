# Phase 2 — defense-only Athena validation results

Date: 2026-04-25
Driver: `tools/bestof.py` (parallel runner with Wilson 95% CI).

## Headline result

| Matchup | Wins | Losses | Win rate | Wilson 95% LB | Gate ≥ 35% |
|---|---|---|---|---|---|
| `athena_v3_planner_defense_only` vs `v13_second_ring`         | 0 | 20 | 0.0% | **0.000** | **FAIL** |
| `athena_v3_planner_defense_only` vs `athena_baseline_lostkids`| 0 | 20 | 0.0% | **0.000** | **FAIL** |

Both gates **FAIL**. Per the build-plan spec:
> If FAIL on either: do NOT loosen the gate; document the failure with
> diagnosis (which archetype was selected? was the planner exceeding
> budget? did probabilistic_placement misfire?) and proceed — Phase 3+
> may fix it.

We follow that protocol. Diagnosis below; Phase 2 is committed and
handed off to Phase 3.

## Bestof commands

```bash
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    algos/athena_v3_planner_defense_only algos/v13_second_ring 10
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    algos/athena_v3_planner_defense_only algos/athena_baseline_lostkids 10
```

Both runs: 20 games each (10 with defense-only on P1, 10 on P2).
Workers: 10. Wall time: 27.1s and 20.1s respectively.

Replay archives:
- `replays/bestof_athena_v3_planner_defense_only_vs_v13_second_ring_20260425_003633/`
- `replays/bestof_athena_v3_planner_defense_only_vs_athena_baseline_lostkids_20260425_003704/`

## Per-game observations

vs **v13_second_ring**: 32-turn games on average. Defense-only HP drains
to -2 to -11 (engine ends when first side hits ≤0). End-state:
v13 SP-on-board ≈ 158, defense-only ≈ 68.

vs **athena_baseline_lostkids**: ~20-turn games (Lostkids' offense is
faster). Defense-only HP drains to -10. Lostkids ends with 40 HP.

## Process metrics

- **Crashes**: 0/40 games (zero across both bestofs).
- **Timeouts**: 0/40 games. Per-turn compute well under the 13s
  watchdog: vs v13 → 240ms total / 32 turns ≈ 7.5 ms/turn; vs Lostkids
  → similar.
- **Watchdog fires**: 0.
- **Safe-fallback path triggers**: 0.

The production wrappers are working — no failure traces.

## Diagnosis: WHY it loses 100%

This is a NO-OFFENSE algo. The Phase 2 defense-only variant explicitly
spawns no Scouts/Demolishers/Interceptors. That means:

1. **MP accumulates unused.** Turn 5: 4.3 MP. Turn 30: 24 MP. None
   spent. Defense-only has no way to deal damage to the opponent.
2. **Outcome ties on HP-drain.** Both algos start at 40 HP. v13 and
   Lostkids both deal HP damage via Scout breaches. Defense-only does
   NOT — its only chance to win is if the opponent crashes (none did)
   or if it survives to turn 100 with higher HP (which would only
   happen if its defense were perfect AND the opponent stopped
   attacking).
3. **The local engine doesn't enforce a "tied HP at turn 100 → SP-on-
   board tiebreak" path** that defense-only would benefit from — every
   game ended via HP-drain, well before turn 100.

This is a correctness-of-spec result, not a bug in the defense engine.
Spec language ("a defense-only algo is allowed to LOSE on offense; it
just needs to outlast the opponent's HP-burn long enough to win on
tied-HP tiebreak") assumed the local engine would sometimes drag games
to turn 100. In practice both opponents reliably break our edge defense
within 20-32 turns and HP-drain to a win.

### Specific archetypes selected

- **v_funnel.json** (default). Always selected — no archetype switching
  is implemented in Phase 2.

### Was the planner exceeding budget?

No. Per-turn compute < 10 ms in 100% of observed turns. SP budget is
respected: at turn 5 defense-only spent SP down to 0 (8 starting + 4
income across 5 turns ≈ 28 SP spent on the build).

### Did probabilistic_placement misfire?

Inspecting turn 5 vs turn 30 board states:
- Defense-only stalls at 5-6 turrets across the whole game (started at
  4 from priority-1, never gets above 6).
- Defense-only does build 4-11 supports by turn 25 (good — supports
  ARE being placed).
- v13 builds 12 turrets by turn 5 and 20 turrets by turn 30 — much
  faster turret accumulation. v13's archetype hard-codes a heavy turret
  ring; our v_funnel.json prioritizes only 4 base turrets at priority 1
  and gates further turret placement behind support placement at
  priority 5+.

Combined diagnosis:
- The archetype is wall-heavy and turret-light relative to v13's
  meta-fit ring. Our 32-placement v_funnel only has 4 base turrets
  total at priority 1; v13 places 12+ in the same time.
- probabilistic_placement is restricted to `y >= 11` for turrets and
  `5 <= y <= 9` for supports. With most front-row tiles already
  occupied by walls from the archetype, probabilistic_placement
  contributes few new turrets.
- The reactive_to_breach path triggers only after a breach, but
  defense-only loses ~10 HP/turn after T25 — too late to recover.

## What this means for Phase 2

The defense IS solid on its own terms — it doesn't crash, doesn't
timeout, and survives 20-32 turns against strong opponents. What it
CAN'T do is win without offense, because:

1. The local engine's typical 30-50 turn HP-drain endgame doesn't favor
   a 0-MP-spent algo.
2. Our archetypes under-emphasize early turrets vs the meta.

Phase 3+ (offense) is required to clear the gate. The defense engine
itself is functioning as designed; it just isn't sufficient on its own.

## Recommended Phase 2B follow-ups (out of scope here)

These are optional improvements the next phase could consider:

1. **Re-balance v_funnel.json**: move 4-6 more base turrets to
   priority 1-2 (currently they're at priority 6+). Trade some
   priority-3 walls for priority-2 turrets.
2. **Loosen probabilistic_placement turret-y constraint** from
   `y >= 11` to `y >= 9` so the sampler can patch the mid-row gaps.
3. **Adopt v13's ring archetype** as a fourth option. v13 wins 20-0 on
   the local engine; its defensive layout is strong and we could clone
   it into `defenses/v13_ring.json`.
4. **Add a 2-game smoke test against `python-algo`** to verify the
   defense-only algo at least beats the trivial scout-rush baseline
   before committing further changes (it currently doesn't — it loses
   to python-algo too).

These are deferred; Phase 2 is committed and handed off.

## Validation gate verdict

| Gate | Pass? |
|---|---|
| Wilson LB ≥ 35% vs v13_second_ring         | **FAIL** (LB = 0.000) |
| Wilson LB ≥ 35% vs athena_baseline_lostkids| **FAIL** (LB = 0.000) |

Per spec: documented; not loosened; proceeding to Phase 3.
