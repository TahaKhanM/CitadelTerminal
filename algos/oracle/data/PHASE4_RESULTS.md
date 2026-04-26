# Athena v3 Phase 4 — bestof results

## Setup

Two ranked /bestof runs of 20 games each (10 per spawn side):

```
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner_offense_only v13_second_ring 10
/opt/miniconda3/bin/python3.13 tools/bestof.py \
    athena_v3_planner_offense_only athena_baseline_lostkids 10
```

## Results

| Matchup | Wins | Losses | Wilson 95% CI | Mean turn | p99 turn |
|---|---|---|---|---|---|
| offense-only vs v13_second_ring     | 0 | 20 | [0.00, 0.16] | not measured | not measured |
| offense-only vs athena_baseline_lostkids | 0 | 20 | [0.00, 0.16] | not measured | not measured |

End-state HP from sample games:
  vs v13:        HP 0 - 40 (we lose)
  vs Lostkids:   HP -6 - 15 (we lose; -6 means HP went negative i.e. defense fully broken)

Wall clock: ~22 s for 20-game v13 set, ~23 s for 20-game Lostkids set.

## Verdict

**No hard gate.** Per Phase 4 spec: this is a partial Athena (no real
defense — minimal hand-written 4-turret + 6-wall layout), so a 0/20
sweep is the *expected* outcome of testing offense in isolation.

The meaningful test is **Phase 5 integration** — when DefensePlanner
(Phase 2) + OffensePlanner (Phase 4) + OpponentModel (Phase 3) compose
into one EconomyArbiter and the gamelib<->sim_rs adapter is fully
wired. At that point the offense engine actually gets to run sim
rollouts (currently `skip_sim=True` because the adapter is incomplete)
and the defense covers more than 4 turrets.

## What this validation DID confirm

- ✅ Algo never crashed across 40 games.
- ✅ Watchdog never fired (no per-turn timeouts).
- ✅ Beam search picks a template every turn the algo had MP for one.
- ✅ Spawn execution succeeds when our minimal defense doesn't block
  the chosen tile (most of the game).

## Why offense-only loses cleanly

1. **Insufficient defense.** 4 turrets + 6 walls cannot stop v13's 12+
   turret army or Lostkids' Scout swarm. We bleed HP each turn and
   cap out long before the 100-turn tiebreak.
2. **No sim rollout in this variant.** With `skip_sim=True`, beam
   search picks by mp_cost heuristic — it always picks the biggest
   spawn, which is sometimes wrong (e.g. when defense is needed).
3. **No opponent model wired.** Phase 3's posterior is computed every
   turn but never consumed; opp_actions_top_k=[] always. Phase 5 will
   wire it.

## Phase 4B / Phase 5 followups

Recorded in AUTONOMOUS_LOG.md:
1. **Wire the gamelib->sim_rs state adapter.** Need uid + HP fidelity
   so sim_rs.simulate_action_phase_py doesn't reject a synthesized
   state (currently fails with "_raw_unit_information missing"). Once
   wired, flip `skip_sim=False` in the offense engine.
2. **Calibrate utility weights** (α/β/γ/δ) — current α=1.0, β=0.5,
   γ=0.2, δ=0.3 is a stab. Phase 9 MAP-Elites can re-tune.
3. **Compose with full defense** — drop the minimal hand-written
   defense in favor of the Phase 2 archetype-based engine.
4. **Wire opponent posterior** — feed Phase 3 ArchetypeClassifier →
   ActionPredictor.top_k → beam_search.opp_actions_top_k.
5. **Per-turn compute profiling** — not measured in this run; should
   be < 50ms with the heuristic path, ~500ms-1s with sim rollout.
