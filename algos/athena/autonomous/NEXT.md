# NEXT — literal next action for the loop

## Immediate on cycle 4
1. Check v21 n=15 validation results (background task bdu5i33pc). If Wilson LB ≥0.85 vs v13+v14 → v21 is confirmed champion.
2. **Emit UPLOAD_READY_v21.md heartbeat.** v21 is strong enough to upload and gather ranked data on. Include:
   - algos/v21_temporal_phase_gate zip path (call /upload-algo first)
   - Local eval summary (100% @ n=15)
   - Rationale: first mirror-breaker, invention #2, catches opponent at low-MP moments.
3. Spawn wave-3 builders (5 concurrent, SAME CONCURRENCY as wave-2 for builds, but SEQUENTIAL for evaluations):
   - v22_oleh_counter (anti-scout_rush, anti-oleh-v2)
   - v22_siege_breaker (anti-turret_castle)
   - v22_phase_gate_hybrid (v21 + oleh_counter + siege_breaker, champion-candidate evolution)
   - v22_demo_gate (anti-demo_line)
   - v21_quadrant_adaptive_support (INVENTION #3)

## After wave-3 builds
- Crash-check each.
- Evaluate SEQUENTIALLY (one at a time — parallel evaluator has a tempdir race).
- Record, commit, heartbeat.

## Wave-4 queue
- Adversary pool expansion (15+ new stress-testers from IDEAS.md P3)
- Fix evaluator race condition
- Run v21 / v22_phase_gate_hybrid at n=100 for true criterion-A sample size
- Build adversaries that replay ranked opponents: opp_oleh_mimic (replay the actual oleh-v2 spawns)

## Invariants
- ALWAYS call ScheduleWakeup at end of every turn with prompt="<<autonomous-loop-dynamic>>"
- Never commit without pre-commit hook passing
- Heartbeat at every cycle
- Sequential evals only (until race fixed)
- NEVER terminate except on CHAMPIONSHIP_CASE.md meeting A-G bar

## Resume-from-compact checklist
- Read STATE.md (cycle 3, champion = v21_temporal_phase_gate)
- Read this NEXT.md
- Read HEARTBEAT.md tail (3 cycles)
- Check if v21_n15 bg task completed: `algos/v21_temporal_phase_gate/evaluate_report.json`
- Read RANKED.md for ranked-specific counter priorities
