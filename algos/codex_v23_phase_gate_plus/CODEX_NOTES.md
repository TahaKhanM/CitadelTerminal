# codex_v23_phase_gate_plus

Isolated Codex work folder derived from `v21_temporal_phase_gate`.

Design stance:
- Preserve v21's temporal phase gate as the default behavior.
- Add side-aware spawn selection only when the opponent's stationary layout is clearly asymmetric.
- Use side-aware spawning only when the opponent's structure layout is clearly asymmetric.
- Keep the gap-safe Support helper inactive; the first validation pass showed it preserved wins but collapsed the v14 HP margin.
- Avoid the failed v22 pattern of broad archetype dispatch that misclassified v13/v14 and overrode the phase gate.

Validation targets:
- Must not regress v21 against `v13_second_ring` or `v14_support_caravan`.
- Must improve or match v21 against `opp_turret_castle`.
- Must stay crash-free and comfortably under the 15s turn budget.
