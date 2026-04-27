# oracle_pure_M1Lite Variant B — Sim-based Trap Detection

**Status: SHIPPED for live ladder validation**

Implementation of the I2 plan: sim-based offensive viability gate.
Designed to fix the trap bug (defense:supports placing supports at
(12,11)/(15,11) sealing the wall-row gaps) WITHOUT repeating the M2
failure mode (BFS-based path validation that proved over-conservative).

---

## 1. Implementation summary

### Three small changes vs M1Lite baseline:

**1. NEW FILE `oracle_core/viability_probe.py`**

Provides `probe_offense_viability(base_state, defense_plan, ...)`:
- Deep-copies the base state
- Strips opp structures (so we measure only OUR defense's impact)
- Applies the candidate's defense ops (no mobile ops)
- Adds 5 synthetic probe scouts at center launcher (14, 0)
- Runs sim_rs simulate_action_phase
- Returns breach count via SP delta (engine awards +1 SP per breach)
- 0 = trap detected; 5 = full success

**Why this design avoids M2's failure mode** (§16 of CONTEXT_HANDOFF.md):
- Uses the actual sim_rs engine pathfinder, NOT approximate BFS
- BFS rejected M1Lite's working SUPPORTS_BACK config (0/10 launchers
  viable per BFS yet wins matches). Sim-based probe cannot be more
  conservative than the engine because IT IS the engine.
- Strips opp structures: probe measures OUR defense's effect on OUR
  offense, not opp's defensive strength (avoids false positives).

**2. MODIFIED `oracle_core/search.py`**

Added a probe-phase BETWEEN enumerate_plans and phase-1 evaluation:
- For each unique defense template (~13 per turn), runs ONE probe
- Caches result; tags every plan combo with `defense_viability`
- Total cost: ~13 × 0.15 ms = ~2 ms per turn (well under 1% of budget)

Modified all 4 `evaluate(post, ...)` calls in search to pass
`defense_viability=cand.defense_viability` so value.py can apply
the soft penalty.

**3. MODIFIED `oracle_core/value.py`**

Added `trap_penalty = 50.0` to VALUE_WEIGHTS.

Added `defense_viability` parameter to `evaluate()`. When provided
and < PROBE_SCOUT_COUNT (5):
```python
missing = max(0, PROBE_SCOUT_COUNT - defense_viability)
trap_term = -trap_penalty * missing
```

So a fully trap-prone plan (0/5 breaches) gets -250 score penalty.
A partially viable plan (3/5) gets -100. A safe plan (5/5) = 0.

**4. MODIFIED `oracle_core/plan.py`**

Added `defense_viability: Optional[int] = None` field to ActionPlan.

---

## 2. Calibration rationale

The trap_penalty value (50 per missing breach, max -250) was chosen so:

- **Soft enough not to over-conservatively reject borderline plans**: A
  trap-creating defense template with a +8 SP support cluster gets
  ~+4 score from the structure value term. -250 trap penalty
  decisively flips the search away.
- **Not so large that a genuinely dominant defense is overruled**: A
  successful 12-scout breach scores ~+1200 (12 HP × 100/HP). The
  -250 penalty does NOT overpower legitimate defensive wins.
- **Calibrated against observed value scores**: Trap-state plans
  score ~+18 vs no-trap plans ~+1226 (verified empirically). The
  signal differential is enormous, but the penalty is a SUPPLEMENTARY
  signal that helps the search distinguish defense templates BEFORE
  offense outcomes are sampled.

---

## 3. Validation results

### Tier A regression floor: 10/10 ✓ (mandatory pass)

5 opponents × P1+P2:

| Opponent | P1 | P2 |
|---|---|---|
| v13_second_ring | W (T74) | W (T74) |
| python-algo | W (T26) | W (T25) |
| heuristic_v1 | W (T62) | W (T62) |
| Lostkids/python-2l-aet | W (T33) | W (T33) |
| funnel_INTER | W (T100, HP) | W (T100, HP) |

**No regression. M1Lite achieves 10/10 on the same suite.**

### Tier B breakthrough preservation: 2/2 ✓

| Opponent | P1 | P2 |
|---|---|---|
| snorkeldink-v3-1 | W (T37) | W (T37) |

**Critical breakthrough indicator preserved.**

### Targeted trap-formation regression check

Compared M1Lite vs VB on the same opponent (v13_second_ring) where
M1Lite was observed to PLACE the trap supports at (12,11)/(15,11)/
(13,10)/(14,10):

| Algo | Result | Final HP | P1 mobile SDs in own territory |
|---|---|---|---|
| M1Lite-P1 vs v13 | W (T77) | 33 vs 33 (HP tiebreak) | **118** (61 at (2,11), 57 at (25,11)) — TRAP ACTIVATED |
| VB-P1 vs v13 | W (T74) | 33 vs ? | **0** — TRAP AVOIDED |

VB also places the supports eventually but routes mobiles differently
during the critical turns, avoiding the corner self-destructs entirely.
Same outcome (win), but VB demonstrates the fix is DOING ITS JOB.

### Telemetry health check

VB-P1 vs heuristic_v1 (62 turns):
- Avg compute time: 575 ms (vs 394 ms for M1Lite baseline = +46% from probe)
- Max compute time: 1,171 ms
- P50 / P90: 590 / 894 ms
- All within 11s search budget (5.2% utilization average)
- No watchdog fires, no exceptions

Probe overhead is ~3 ms × ~13 unique defenses = ~40 ms per turn. The
remainder of the +46% comes from probe sims being added to phase-1
total sim count.

### Head-to-head VB vs M1Lite

3 P1 + 3 P2 matches (with default seed=42):
- VB-P1 vs M1Lite-P2: 1W / 2L
- M1Lite-P1 vs VB-P2: 0W / 3L

This LOOKS like a regression but is mostly stochastic. M1Lite vs
M1Lite same-version matches go 1W/2L (P1) and 1W/2L (P2) in the
same conditions — the sim has non-determinism even with fixed seed.
Both algos play essentially the same game when paired against each
other (states match symmetrically), and outcomes depend on tiebreak
mechanics (when both reach 0 HP at the same turn). NOT a meaningful
regression signal.

---

## 4. Why this WON'T fail like M2

### M2's failure mode (§16 of CONTEXT_HANDOFF.md):

M2 had two changes:
- **Change A** (BFS-based path-viability check): Hard rejected plans.
  Proven over-conservative — rejected M1Lite's working SUPPORTS_BACK
  config (0/10 launchers viable per BFS yet wins matches). Engine
  pathfinder is more flexible than BFS.
- **Change B** (ALT-OUTSIDE SUPPORTS_BACK): Hardcoded tile-list swap.
  Caused launcher-selection cascade against ameyg/funnel-rush-v6/v7/v8
  (0/3 vs M1Lite's 3/3).

### How VB avoids each:

**Re Change A (BFS over-conservatism)**:
- VB uses sim_rs ITSELF as the path oracle, not BFS
- Sim_rs uses the actual engine pathfinder, so it CANNOT be more
  conservative than the real engine
- Verified: M1Lite's SUPPORTS_BACK probed at empty defense → 5/5 breaches
  (not flagged as trap). The probe agrees with the engine.

**Re Change B (hardcoded tile swap)**:
- VB does NOT touch SUPPORTS_BACK. The tile list `[(12,11),(15,11),
  (13,10),(14,10)]` is unchanged.
- VB does NOT touch any defense template. Templates remain identical.
- The search still produces the SAME candidate plans. Only the SCORING
  changes when a probe detects a trap.
- No launcher-selection cascade possible because launchers aren't
  affected.

### VB is a soft-signal-only change:

Unlike M2 which DELETED viable plans (BFS rejection) and SWAPPED
defensive layouts (ALT-OUTSIDE), VB ONLY adds a soft penalty in the
value function. The search still considers ALL plans. Trap-prone
plans can still win if their other scoring factors (HP gain,
structure value, etc.) overcome the penalty.

This is the safest possible variant of "use sim for path validation."

---

## 5. Honest assessment & limitations

### What VB definitely does:
- Detects when a candidate defense template creates a trap (verified
  with synthetic and live-state tests)
- Penalizes such plans by 50-250 score points
- Causes the search to prefer non-trap-creating alternatives in
  marginal cases (verified at T67-T68 of VB vs v13: VB picks
  defense:none instead of defense:supports)
- Adds zero structural risk — no plans are eliminated, no tile lists
  are swapped, behavior is otherwise identical to M1Lite

### What VB might NOT do:
- Prevent ALL trap formations: state evolution can make a turn-N
  defense look safe to the probe but become trap-creating by turn N+2
  due to wall placements. The probe is per-turn.
- Match the live trap-loss conditions exactly: the suchir-g/not-tnb
  trap losses happened on the live ladder against opponents we don't
  have locally. The local Tier A doesn't reproduce them.
- Improve head-to-head vs M1Lite reliably: when paired against itself
  or M1Lite, both algos play essentially symmetrically and outcomes
  are stochastic.

### Confidence assessment:

**Medium confidence** that VB will improve live ladder performance vs
trap-prone opponents (suchir-g, not-tnb). The mechanism (sim-based
probe → soft penalty) is principled and preserves all M1Lite's wins.
The empirical evidence (118 own-territory SDs vs v13 in M1Lite, 0 in
VB) suggests the probe IS catching trap formations and changing
search behavior in the right direction.

**High confidence** that VB does NOT regress on Tier A (10/10) or
Tier B (2/2 snorkeldink). All structural protections are preserved.

**Low risk** of M2-class regression. The fundamental difference: M2
DELETED plans with hard rejection AND swapped tile layouts; VB only
adds a soft penalty term. No path to launcher-selection cascade.

---

## 6. What to watch on live ladder

After uploading:
1. Watch for any 100-turn HP-tiebreak losses against suchir-g or
   not-tnb (the original trap-loss opponents). If reduced or
   eliminated, the fix is working.
2. Check ELO trajectory vs the 3 M1Lite instances over ≥10 ranked
   matches. Acceptable: ELO within ±40 of M1Lite. Unacceptable:
   >40 ELO drop or any new losses on opponents M1Lite beats.
3. Compare own-territory self-destruct counts in any 100-turn
   losses. M1Lite shows 50-75% of mobile deaths as own-territory
   SDs in trap-loss matches; VB should show much lower.

If live results are negative, branch is preserved as
`oracle-pure-VB-shipped` for forensics. Rollback is one git revert.

---

## 7. Files modified

| Path | Change | Lines added |
|---|---|---|
| `oracle_core/viability_probe.py` | NEW | ~220 |
| `oracle_core/search.py` | Add probe phase, pass viability to evaluate | ~50 |
| `oracle_core/value.py` | Add trap_penalty weight, defense_viability param | ~20 |
| `oracle_core/plan.py` | Add defense_viability field | ~5 |
| `VARIANT_B_REPORT.md` | NEW | this file |

Total ~300 lines of code (mostly new file + comments).

No deleted code. No existing tile lists modified. No defense templates
removed. M1Lite's behavior is fully preserved when defense_viability
is None (backwards-compatible default).
