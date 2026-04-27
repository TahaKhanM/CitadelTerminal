# Final Critical Evaluation — G1..G8 risk/benefit + dependencies

Before writing the milestone prompts, here's a hard look at each fix
under three lenses: **will it definitely help?**, **could it
regress?**, and **what does it actually depend on?**

---

## G7 — Hand-rolled fast state copy
- **Will it definitely help?** YES. Pure perf change. 22× faster than
  `deepcopy` on benchmark. Doesn't change behavior.
- **Could it regress?** Only if a state-dict field is missed in the
  shallow-copy code, causing aliasing across candidates. A parity
  test that fuzzes 100 base_states and asserts deepcopy ≡ fast_copy
  after sim mutation eliminates this risk.
- **Verdict:** GENUINE IMPROVEMENT. Ship first; foundation for G4.

## G3 — Re-build prior with breach context
- **Will it definitely help?** YES. The prior currently has 2/3 of
  its bucket schema empty (br1_2 and br3p have ZERO entries because
  the builder hardcoded recent_breaches=0). Filling them adds
  information without removing any.
- **Could it regress?** Bucket fragmentation could thin per-bucket
  signature counts; mitigated by the existing top-16-per-bucket cap.
  Worst case: br0 buckets unchanged, br1_2 and br3p buckets gain
  signal. Strict superset.
- **Verdict:** GENUINE IMPROVEMENT. Ship in M1.

## G8 — Pass breach TILES (not count) to opp_model
- **Will it definitely help?** ALONE: no — it's plumbing. Downstream
  features (G2 spatial similarity, future fixes) need it.
- **Could it regress?** Only if a downstream consumer mis-uses the
  tile data. Adding the parameter as optional kwarg = zero behavior
  change unless the consumer uses it.
- **Verdict:** ENABLER. Ship with G2.

## G2 — Posterior-driven killshot prediction
- **Will it definitely help?** CONDITIONAL.
  - HELPS when: G3 has populated br1_2/br3p prior; AND we've observed
    ≥3 opp actions in current bucket; AND opp has actually banked MP.
  - DOESN'T HELP when: cold-start (turn 1-7); opp's bucket has no
    prior or posterior data → falls through to existing adversarial
    samples.
  - In funnel matches, posterior typically accumulates 5-10 opp
    observations by T20 (when killshot fires) → should help.
  - In demo-siege matches, opp's pattern is "5 demos every turn"; the
    posterior sigs would predict 5-demo waves correctly even without G2.
- **Could it regress?** YES, mild. If posterior signatures get
  large weights (observation counts can hit 20+) they could
  monopolize phase-2 sampling and crowd out the existing fixed-weight
  adversarial samples that catch unobserved attack patterns.
  Mitigation: **cap posterior signature weights at 3.0** (similar
  to existing adversarial weights).
- **Verdict:** GENUINE IMPROVEMENT with the weight cap. Ship in M2.

## G4 — Adaptive compute budget (time-remaining condition)
- **Will it definitely help?** PROBABLY. Phase-3 re-evaluates top-10
  with 2× k_opp. More samples = better mean estimates = top-pick more
  reliable.
- **Could it regress?** Mildly. Phase-3 might rank a different #1
  than phase-2 due to sample variance. If the new #1 happens to be
  worse than phase-2's #1, that's a regression on that turn.
  Mitigation: phase-3 only changes the pick if its score margin
  exceeds phase-2's by a threshold (e.g., +10%).
- **Verdict:** GENUINE IMPROVEMENT with margin gate. Ship after G7.

## G5 — Multi-intensity patch family
- **Will it definitely help?** UNCERTAIN.
  - HELPS when: oracle has ≥2 recent breaches (patches fire) AND
    sufficient SP for intensity 2-3.
  - DOESN'T HELP: when SP is low (intensity 3 needs ~12 SP).
  - The CURRENT patch math is off (places 2 turrets, not 1) so
    "intensity 1" already exists. The new "intensity 2/3" only fires
    in higher-SP states.
- **Could it regress?** YES. New higher-intensity templates with
  upgrades inflate static struct value (+24-+48), which COULD beat
  the standard `defense:t0_full` for the search and divert SP into
  patches when the skeleton isn't fully built. Mitigation: ONLY
  emit intensity-2/3 patches when `defense:t0_full` is at least 80%
  built (existing turrets cover y=11 row).
- **Verdict:** RISKY. Ship behind a build-state gate. Could cut.

## G1 — Coverage-gap proposer
- **Will it definitely help?** UNCERTAIN.
  - The audit established this generalizes to any breach pattern —
    but does it actually fire on the breach tiles we lose to?
  - At T0-T10, EVERY tile has cov=0 → proposer would emit useless
    templates competing with t0_full.
  - At T20+ with full skeleton, proposer surfaces uncovered tiles
    — including (5,8), (7,6), (16,2). These ARE the breach tiles.
  - But: proposing turrets at "(gap_tile + (0,1))" might place them
    on tiles already in the standard atom set. e.g., gap at (5,8) →
    propose turret at (5,9) which is in REACTIVE_PATCH_TILES_LEFT
    constant (dead code) but already covered by SIDELANE (7,9). The
    new turret at (5,9) might not actually cover (5,8) better than
    the existing (7,9).
- **Could it regress?** YES, in two ways:
  1. Early game: proposes random tiles (everything is gap=0), search
     wastes evaluations.
  2. Mid-game: gap-fillers compete with `defense:upg2`. Static value
     of new turret = +4; static value of upgrade = +8. So the search
     correctly prefers upgrades — gap-fillers rarely win unless
     sim_rs shows breach-prevention. But if sim_rs predictions are
     still off (because opp model still doesn't see killshots in
     cold-start), gap-fillers won't win even when they should.
- **Verdict:** UNCERTAIN. Ship ONLY after G2+G3 are validated. Gate
  the proposer to fire only after T8 (skeleton is built) AND only
  when at least one breach has occurred (avoid early-game noise).

## G6 — Offense-effectiveness tracker
- **Will it definitely help?** UNCERTAIN — high risk/high reward.
  - For demo-siege-v2 (where oracle stuck spamming int_screen at
    (3,17) for 30 turns at 0 dmg/MP), this is the SINGLE most
    targeted fix.
  - For funnel matches where oracle's offense lands SOMETIMES, the
    tracker might over-penalize archetypes after 1-2 unlucky misses.
- **Could it regress?** YES, multiple ways:
  1. Misattribution: which plan caused which breach event? Hard to
     attribute precisely when multiple plans are in flight.
  2. Cold-start: penalty fires at attempt 3+. A new opp where
     scout-rush failed twice in early-game might never get tried
     again, even if attempt #4-5 would have worked.
  3. Penalty magnitude: too large = blocks legitimate retries; too
     small = no effect.
- **Verdict:** RISKIEST. Ship LAST with smallest possible penalty
  (initial: -15 utility; tune up only if validated to help).

---

## Dependencies and ordering

```
G7 (fast copy) ─────┐
                    ├─→ G4 (adaptive budget) — uses freed time
                    └─→ All other fixes — faster sims = more validation cycles

G3 (rebuild prior) ─┐
G8 (pass tiles) ────┴─→ G2 (posterior sampling) — uses prior + tiles

G3 + G2 ────────────────→ G1 (coverage gap) — gap proposer's value depends on
                            sim_rs predicting attacks correctly, which depends
                            on opp model quality (G2)

G3 + G2 + G1 ───────────→ G5 (patch family) — patches compete with G1's gap
                            templates; both should be in place to balance

G3 + G2 + G1 + G5 ──────→ G6 (offense tracker) — penalty needs to be applied
                            on TOP of properly-evaluated plans; needs the
                            other defensive fixes to give the search options
```

## Recommended milestone grouping

| Milestone | Fixes | Risk | Est. ELO | Reason |
|---|---|---|---|---|
| **M1 — Foundation** | G7 + G3 | LOW | +10-25 | Pure bug fixes; ship first |
| **M2 — Sampling** | G8 + G2 (with weight cap) | LOW-MED | +10-25 | Better opp predictions |
| **M3 — Defense templates** | G1 (gated) + G5 (gated) | MED | +10-25 | Adds defensive options; gating prevents regression |
| **M4 — Compute scaling** | G4 (with margin gate) | LOW | +5-15 | Uses freed time productively |
| **M5 — Behavioral (optional)** | G6 (with -15 penalty) | HIGH | +5-25 | Fixes demo-siege offense paralysis; biggest risk |

**Each milestone must independently pass:**
1. Local component tests (`tests/test_components.py`)
2. Local ladder both sides 10/10 vs (v13_second_ring, python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER)
3. New upload folder created (`oracle_pure_M{N}_upload/`)
4. Live ladder validation: ≥10 ranked matches, ELO must not drop >40 vs prior milestone
5. **If ANY regression**: rollback the milestone, document what failed and why

**No milestone may be deployed if it regresses the local 10/10 bar.** This is non-negotiable.

## What NOT to commit to in advance

- Don't commit to ALL 5 milestones. After M1+M2 land successfully, decide based on actual ELO movement whether M3-M5 are worth the risk.
- Don't commit to estimated ELO numbers; they're guesses. Real measurement is the gate.
- If M3 (the riskiest defensive change) regresses, M4 and M5 are still worth attempting from M2 baseline.

## Rollback strategy

Each milestone:
- Lives on a branch `oracle-pure-M{N}` off main
- Upload folder at `oracle_pure_M{N}_upload/`
- If validation fails: branch is abandoned (don't merge), upload folder is kept for forensic inspection
- Main branch always reflects the LAST PASSING milestone
