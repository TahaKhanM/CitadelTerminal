---
name: bestof
description: Play the same two Citadel Terminal algos N times (alternating which side each plays) and report a win rate with a 95% confidence interval. Use when the user says "bestof", "how reliable is this win", "is v3 actually better than v2", "run 10 games between X and Y", or when a single-game result isn't enough to decide.
---

# bestof

Runs 2n matches (n per orientation) between two algos and reports win rate with a Wilson 95% confidence interval — so you can tell the difference between "5/10 genuine dead heat" and "5/10 statistical noise in favor of algo A".

## When to use
- User sees a close result in a single `/run-match` and wants confirmation.
- Deciding between two promising variants before upload.
- Regression-testing a new version vs. a known-good baseline.

Default n=5 → 10 total games (~1-5 minutes). For a high-confidence decision, use n=10 or n=20.

## Steps

1. **Identify the two algos** (short names under `algos/` or explicit paths).
2. **Pick n** based on urgency:
   - Quick sanity check: n=3 (6 games).
   - Default confidence: n=5 (10 games).
   - Publication-quality: n=20 (40 games; slow).
3. **Run it:**
   ```bash
   python3 tools/bestof.py <algo_a> <algo_b> [n]
   ```
4. **Interpret the verdict.** The tool prints a Wilson 95% CI and a verdict:
   - `lower CI > 0.55` → algo A is statistically dominant.
   - `upper CI < 0.45` → algo B is statistically dominant.
   - Otherwise → inconclusive; more games needed.
5. **Report** both the raw win count AND the CI — the user cares about both the observed data and the confidence.

## Failure modes
- If a single match crashes, the script still proceeds; report the crash count alongside results. A repeatedly-crashing algo should be investigated before drawing conclusions.
- If games all run in <5s, there's likely a crash/early-resign happening — inspect one of the replays to see.

## Don't
- Don't use this as a single-game wrapper when the user wants one match — use `/run-match` instead.
- Don't interpret a 6/10 result (60%) as "clearly better" — 6/10 has a Wilson CI of roughly [0.31, 0.83]. Not decisive.
