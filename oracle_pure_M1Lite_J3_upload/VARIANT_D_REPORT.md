# Variant D — Hybrid M1Lite

**Status**: PRIMARY UPLOAD CANDIDATE
**Built**: 2026-04-27 by manual merge of VA + VB + VC after the prior agent's worktree was cleaned up.

## What this variant does

Combines three independent improvements over M1Lite, all wired through new optional parameters in `evaluate()` and `search()` (back-compat with M1Lite when None):

### From Variant A
- **I3 — Wasted-MP penalty** (`oracle_core/value.py`): after sim_rs runs, if `mp_spent_this_turn ≥ 5` AND `damage_caused` is small, subtract `5 × (mp_spent − 1 × damage)`. Targets the trap pattern where mobiles spawn but accomplish nothing (replay 15314226: 587 SDs in own territory, 1018 MP spent for 3 breaches).
- **I7 — Variance-aware ranking** (`oracle_core/search.py`): phase-2 plans ranked by `mean − 0.1 × std` instead of mean alone.

### From Variant B
- **I2 — Sim-based viability probe** (`oracle_core/viability_probe.py` + `search.py`): for each unique defense template (~13/turn), runs sim_rs with 5 synthetic test scouts at center launcher (14, 0). Counts how many breach. Adds soft penalty `-50 × missing_breaches` (max -250) in value function.
- Crucially **NOT** the M2-style BFS path validation. Uses sim_rs (the actual engine) so cannot be more conservative than the engine itself.

### From Variant C
- **I4 — Breach-pressure coverage** (`oracle_core/value.py` + `algo_strategy.py`): per-tile pressure map maintained from `on_action_frame` events, decay 0.9/turn. Value-function term: `0.2 × Σ_{turret t} hp_frac × atk × Σ_{tile in range} pressure(tile)`. Rewards turrets that project threat at high-pressure tiles.

## Validation

| Test | Result |
|---|---|
| Tier A (5 opps × 2 sides) | **10/10 wins** |
| Tier B (snorkeldink × 2 sides) | 2/2 wins |
| H2H vs M1Lite (6 reps) | **6/6 wins** |
| Wall-clock per turn | ~575ms (vs 394ms M1Lite, 5% of 11s budget) |

## Why this works when VB alone doesn't

The probe (VB) demotes "trap-prone" defenses, but alone it's over-conservative — in head-to-head vs M1Lite, VB wins only 1/6. The signals balance:

- The probe penalizes plans where structures block our scouts.
- The waste penalty (VA) penalizes plans where mobiles fail to deal damage.
- The coverage signal (VC) rewards plans where defenses cover hot tiles.

Together they bias the search toward plans that are viable AND productive AND defend observed weak points. The combination is genuinely synthetic, not just additive.

## Risks

1. **Live ladder ≠ local validation.** §16 of CONTEXT_HANDOFF.md documents how M2 passed local Tier A and regressed live on ameyg matchups. Same risk applies here.
2. **6 ranked-ladder loss opponents are not available locally** (Egil/python-algo-siege, ashmit, suchir-g, aa0/swap3, not-tnb). Cannot verify the trap fix on actual trap matchups.
3. **Non-transitive vs other variants**: VD lost to VA, VB, VC, VE in head-to-head (each individual variant beat VD 2/2). The combined signals create a profile exploitable by oracle-internal opponents but apparently more robust against external opponents (M1Lite included).
4. **3 new value-function terms could over-bias** in unseen matchups. The local 6/6 vs M1Lite is encouraging but a small sample.

## Files changed vs M1Lite baseline

- `oracle_core/value.py` — 3 new terms (waste, trap, coverage)
- `oracle_core/search.py` — variance ranking + viability probe loop
- `oracle_core/viability_probe.py` — NEW file
- `oracle_core/plan.py` — `defense_viability` field on ActionPlan
- `algo_strategy.py` — breach pressure map maintenance

## Falls back to M1Lite when

- `pre_state_dict=None` (waste term silent)
- `defense_viability=None` (trap term silent)
- `breach_pressure=None` or empty (coverage silent)
- `VARIANCE_LAMBDA=0` would disable variance ranking (currently 0.1)

So shipping VD is reversible — flip three flags and you're back at M1Lite.

## Confidence

**Medium-high** for shipping. Strong local signal (6/6 vs M1Lite is the most decisive in this experiment). Acknowledged risks: live ladder may differ, individual variants might be safer choices if VD shows live regression.

**Recommended deployment**: Upload VD as primary. If live ladder shows regression on opponents M1Lite was beating, fall back to VA (simpler, also confirmed better than M1Lite locally).
