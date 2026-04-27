# Variant F — VA + VB (without VC)

**Status**: SHIPPED — competitive with VD locally, may be more robust against search-based opponents.

## Hypothesis tested

User's intuition: combining VA's wins (pipmy/Redemption via I3+I7) with VD's wins (aa0/funnel-a via VB probe) into a hybrid without VC's coverage term should be the best variant.

VF = M1Lite + I3 (waste penalty) + I7 (variance ranking) + VB (sim-based probe).
**Strict subset of VD** — VD = VF + VC's breach-pressure coverage.

## What was changed vs VD

Removed VC's contributions:
- `oracle_core/value.py`: removed `_coverage_value()` function, `breach_pressure` param, `breach_pressure_coverage` weight, coverage term
- `oracle_core/search.py`: removed `breach_pressure` param and pass-through to evaluate
- `algo_strategy.py`: replaced with M1Lite's version (removes pressure-map maintenance)

Verified: zero references to `breach_pressure` remain in VF.

## Validation results

| Test | Result |
|---|---|
| Tier A (5 opps × 2 sides) | **10/10 wins** |
| Tier B (snorkeldink × 2 sides) | 2/2 wins |
| H2H vs M1Lite (4 reps) | **4/4 wins** |
| H2H vs VA (2 reps) | **2/2 wins** |
| H2H vs VD (4 reps) | **0/4 wins** ← VD beat VF |

## What VC's effect actually is (from VF vs VD comparison)

VF vs VD differ ONLY by VC's coverage term. In 4 head-to-head matches: **VD won all 4**.

This is direct local evidence that **VC IS contributing positively** in oracle-vs-oracle matchups. Removing VC made VD weaker locally.

But on the live ladder:
- VD lost to pipmy/Redemption (search-based opp) — VA won the same matchup
- VA lacks VC AND VB. So VC is the prime suspect for that specific regression — but it could also be VB's probe over-conservatism.

**Honest interpretation**:
- VC's coverage helps in matches where opp behavior is consistent enough that observed-breach pressure points at real weak spots (most heuristic-based opponents).
- VC may HURT against highly-variable search-based opponents where pressure history is misleading.

## Variant comparison summary

| Comparison | Winner | Mechanism |
|---|---|---|
| VF vs M1Lite | **VF 4/4** | I3 + I7 + probe all add value |
| VF vs VA | **VF 2/2** | Probe (VB) adds value over I3+I7 alone |
| VF vs VD | **VD 4/4** | VC's coverage helps in oracle-vs-oracle |
| VD vs M1Lite (prior) | VD 6/6 | Combined signals dominate |
| VA vs M1Lite (prior) | VA 2/2 | I3+I7 alone improves |
| VC vs M1Lite (prior) | VC 2/2 | Coverage alone improves |

VF sits BETWEEN VA and VD in local strength: better than VA, worse than VD.

## Live ladder recommendations

Given the data, the live ladder picture is:
- VD: wins on aa0/funnel-a, Egil/siege; lost on pipmy/Redemption (vs M1Lite which also lost)
- VA: wins on Egil/siege, pipmy/Redemption; lost on ameyg/funnel-rush-v7 (regression)
- VF: untested live, but mechanism predicts:
  - Should preserve VD's wins (aa0/funnel-a, Egil) since probe is intact
  - Should recover VA's pipmy win (no VC interference)
  - Should preserve preserve ameyg/funnel-rush-v7 win (probe is intact, unlike VA)

**Honest hypothesis prediction**: VF will out-perform VA on funnel/trap matches AND out-perform VD on search-based opponents. Net effect uncertain without live data.

## Risks

1. **Local H2H 0/4 vs VD**: VC IS adding signal locally. Removing it produces a measurable degradation against opponents that share the value function space. Live ladder may behave differently.
2. **No live data for VF yet**: All claims about live behavior are extrapolation from VA and VD's individual live performance.
3. **The 6 ranked-loss opponents (Egil, ashmit, suchir-g, aa0/swap3, suchir-g/baseline, not-tnb) cannot be reproduced locally** — same caveat as VD.

## Conclusion

VF is a valid third candidate alongside VA and VD. The local evidence suggests VC has a real positive effect (VD > VF locally), so VF is NOT strictly better. But the live data suggests VC may regress against specific opp types (pipmy), so VF MAY be live-better in those matchups.

The honest recommendation: upload VF as a third candidate alongside VD and VA, then compare live performance across all three.
