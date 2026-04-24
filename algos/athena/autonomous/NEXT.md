# NEXT — literal next action for the loop

## Immediate
1. Commit wave-1 variants + evaluate reports.
2. Update HEARTBEAT.md with milestone (wave-1 evaluated; v14 is the barrier).
3. Spawn wave-2 variant builders (5 concurrent sub-agents):
   - v20_burst_scout (P5 anti-caravan)
   - v20_support_kill (P5 anti-caravan)
   - v20_flank_edge (P5 anti-caravan)
   - v20_hybrid_turtle_cracker (P5 classifier-dispatch)
   - v21_temporal_phase_gate (P5 invention #2)

## After wave-2 builds
- Crash-check each; evaluate vs full pool.
- Any variant with W ≥ 1 vs v14 is a **mirror-breaker** — huge signal.
- Record in VARIANTS.md / RESULTS.md.

## Resume-from-compact protocol
If context was compacted:
- Read STATE.md (this dir) for phase + cycle.
- Read HEARTBEAT.md tail for last 3 cycles of progress.
- Read IDEAS.md P5 entries for what to build next.
- Read RESULTS.md for what's been evaluated.
- Re-spawn wave-2 builders with prompts templated after `cycle-1` builder prompts (keep scope: copy v13, implement tactic, crash-check only).

## Invariants
- Never commit without pre-commit hook passing.
- Heartbeat at every wave completion (build + eval).
- Update VARIANTS.md the same turn a variant is built.
- DO NOT terminate except on CHAMPIONSHIP_CASE.md meeting A-G bar.
