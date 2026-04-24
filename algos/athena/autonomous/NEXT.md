# NEXT — literal next action

After bootstrap file creation:

1. Spawn RA-1..RA-5 research sub-agents concurrently:
   - RA-1: Engine-mechanics authority briefing (docs/ + engine_decompiled/)
   - RA-2: Existing athena/ algo architecture + v13 champion audit
   - RA-3: Finalist-repo synthesis (research/finalist_repos/)
   - RA-4: Ranked-replay reconnaissance via Citadel live API
   - RA-5: Memory / prior-session lessons audit

2. Build tools/evaluate.py (standardized per-algo report: WR+CI vs each opponent, mirror balance, crash rate, turn-time p50/p95/p99, HP trajectory, SP efficiency).

3. Commit state scaffolding.

4. Integrate RA-1..RA-5 returns → seed IDEAS.md with ≥15 priority-scored ideas, ≥3 inventions.

5. Enter main loop: spawn 5 concurrent variant builders (v20a classic improvement, v20b side-lane aggression, v22a SD bomb, one finalist-port, one pure-invention).

## Invariants
- Never terminate except on CHAMPIONSHIP_CASE.md meeting full A–G bar.
- Heartbeat every ~15 min wall clock.
- Commit every 5 cycles.
- /compact at ~70% context usage.
