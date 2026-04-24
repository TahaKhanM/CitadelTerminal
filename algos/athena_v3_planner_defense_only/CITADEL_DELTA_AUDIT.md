# Citadel-delta audit — athena_v3_planner_defense_only

This algo inherits the Citadel-delta correctness of the Phase 2 defense
engine (`algos/athena_v3_planner/planner/defense.py`). Every unit cost
and stat is read from runtime config — see the original audit at
`algos/athena_baseline_lostkids/CITADEL_DELTA_AUDIT.md` for the
side-by-side comparison.

Phase 2 strategic deltas vs Lostkids:
1. Support placements at Y=7-8 (not Y=5/6) — above the
   `1 + 0.7*Y` shield break-even.
2. probabilistic_placement support_weight=25 (Citadel-tuned, vs base
   GRETCHEN's 100x for 1-HP supports).
