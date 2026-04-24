# codex_v25_burst12

Current Codex champion candidate.

Parent:
- `codex_v24_quadrant_gate`
- ultimately derived from `v21_temporal_phase_gate`

Behavioral delta:
- Keeps v21's temporal phase gate and quadrant-adaptive Support placement.
- Raises ordinary Scout burst threshold from `10 * scout_cost` to `12 * scout_cost`.
- Leaves phase-gate counter-attacks at `6 * scout_cost`.

Why this matters:
- The 10-Scout cadence wins mirrors, but still bounces off some strong defensive candidates at 40-40.
- Waiting for 12 Scouts produces larger shielded bursts without changing the counter-attack timing that makes v21 strong.

Validation:
- Standard panel, n=15 per side per opponent: 150/150 wins, Wilson lower bound 0.89 on every matchup.
- Mean HP deltas: +41 vs v13, +36 vs v14, +40 vs opp_scout_rush, +45 vs opp_demo_line, +43 vs opp_turret_castle.
- Candidate tournament: no tested v21/v22 branch beat it directly; it beat v21_temporal, v22_oleh_counter, and v22_siege_breaker, and tied v24_quadrant, v22_demo_gate, and v22_phase_gate_hybrid.
- Compute from n=15 run: p50 7ms, p95 19ms, p99 25ms.
