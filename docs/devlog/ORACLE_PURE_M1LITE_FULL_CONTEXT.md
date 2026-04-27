# Oracle Pure M1Lite — Full Session Context

**Date**: 2026-04-27
**Session goal**: Improve oracle_pure_M1Lite, address known weaknesses (blocking-defense trap bug, single-tile-targeting funnel losses), explore search-algorithm improvements without heuristic shortcuts.

**Final ship recommendation**: [oracle_pure_M1Lite_M2v3_upload/](oracle_pure_M1Lite_M2v3_upload/)

---

## 1. Starting state

- **Baseline**: oracle_pure_M1Lite (M1Lite from §16 of CONTEXT_HANDOFF.md) — search-driven algo with 13 defense × 150 offense = ~1500 candidate plans per turn, evaluated via sim_rs
- **Known weaknesses** (from earlier replay analysis of 18 live ladder losses across 11 distinct opponents):
  - **Trap bug**: `defense:supports` template seals wall-row gaps, traps own scouts at corners (suchir-g, not-tnb)
  - **Single-tile-targeting**: 6 of 8 deterministic losses had ≥77% damage at one tile (Egil/siege at (8,5), aa0/swap3 at (25,11), etc.)
  - **funnel-rush-v6/v7/v8 sensitivity**: timing-induced sim-budget variance
- **Live live algos**: 5 instances on Citadel ladder (M1Lite + VA + VC + VD + VF)

---

## 2. Variants built and validated (chronological)

### Wave 1: Initial diversification (parallel subagents, V*)

| Variant | Mechanism | Tier A | Tier B | H2H vs M1Lite | Status |
|---|---|---|---|---|---|
| **VA** | I3 wasted-MP penalty + I7 variance ranking (λ=0.1) | 10/10 | 2/2 | 2/2 | SHIPPED |
| **VB** | I2 sim-based offensive viability probe | 10/10 | 2/2 | varies | shipped, weak alone |
| **VC** | I4 breach-pressure coverage in value function (weight=0.2) | 10/10 | 2/2 | 2/2 | SHIPPED |
| **VD** | VA + VB + VC combined (hybrid) | 10/10 | 2/2 | **6/6** | SHIPPED — strongest baseline |
| **VE** | I14 demo-heavy + I13 multi-launcher offense | 10/10 | 2/2 | 1/2 (regress) | NOT SHIPPED |
| **VF** | VA + VB only (= VD minus VC) | 10/10 | 2/2 | 4/4 | SHIPPED |

**Live ladder verdict** (from replay archive of 109 matches):
- VD beats every other variant H2H on shared opponents: VA 3-1, VC 2-0, VF 2-0, OS 1-0
- VD = strongest baseline for further iteration

### Wave 2: Trap-fix improvements on VD baseline (IS series)

Built to address `defense:supports` trap bug:

| Variant | Mechanism | Tier A | Tier B | H2H vs VD |
|---|---|---|---|---|
| **IS3** | Delete `defense:supports` template entirely | 10/10 | 2/2 | passed |
| **IS4** | 5 SUPPORT_POSITION_SETS (probe picks among them) | 10/10 | 6/6 (3 reps) | 4/4 |
| **IS5** | KIND_REMOVE op + probe-driven removal templates | 10/10 | 2/2 | 4/4 |
| **IS6** | Probe-driven `defense:refund_smart_X_Y` (no new op type) | 10/10 | 2/2 | 2/2 |

All four IS variants ship-quality. IS6 verified on actual replay states (suchir-g, not-tnb) — correctly identifies trap structures at (12,11)/(15,11) for refund.

### Wave 3: Single-tile-targeting fix attempts (J series)

Address VD's pattern of losing when opp consistently targets one tile:

| Variant | Mechanism | Extended (22) | snorkeldink-v3-2 |
|---|---|---|---|
| **J1** | Transit-pressure spatial value function (adaptive weight) | 14/22 (64%) | 2/2 ✓ |
| **J2** | Pressure-derived defense templates (lane_block) | 20/22 (91%) | **0/2** ✗ |
| **J3** | Path-prediction in evaluation (sim-based opp probe) | mixed | 2/2 ✓ |
| **J4** | Memory-augmented opp model (launcher-bias) | weak | weak |

**Key finding**: template diversity beats scoring weight. J2's lane_block templates (active candidate generation) outperformed J1's adaptive scoring weight (passive signal).

**Limitation**: J2 still loses 0/2 to snorkeldink-v3-2 (slow-accumulation pattern that fails J2's `peak ≥ 2.5` gate).

### Wave 4: snorkeldink-v3-2 fix (K + M2 series)

Address the v3-2 deterministic loss across entire oracle family:

| Variant | Mechanism | snorkeldink-v3-2 | Extended (22) |
|---|---|---|---|
| **K1** | First-breach reaction (low pressure threshold = 0.5) | 6/6 ✓ | 18/22 (82%) |
| **K2** | Path-blocking via `find_path_to_edge` | 6/6 ✓ | 18/22 (82%) |
| **M2v2** | K1 + K2 combined | 6/6 ✓ | 20/22 (91%) — **lk_2l 0/2 regression** |
| **M2v3** | J2's lane_block + K2 path-block (drop K1) | 2/2 ✓ | **21/22 (95.5%)** — **lk_2l 19/20** |
| **M2v4** | M2v3 + 3-turret variant when pressure ≥ 2.0 | 10/10 ✓ | regressed | **lk_2l 0/20** ✗

**M2v2's lk_2l regression diagnosed**: K1's low pressure threshold (0.5) caused template inflation (47 reactive templates per match vs J2's 26), crowding out offensive plans. Fix: drop K1, use J2's better-gated lane_block alongside K2's path-blocking → M2v3.

**M2v4's lk_2l regression**: 3-turret variant placed structures that disrupted oracle's offense vs lk_2l's specific attack. Stochastic 0/20 sequential losses (vs M2v3's 19/20). DISCARDED.

---

## 3. Final shipping recommendation: **M2v3**

[oracle_pure_M1Lite_M2v3_upload/](oracle_pure_M1Lite_M2v3_upload/) is the strongest variant overall.

### M2v3 mechanism

Three independent improvements over M1Lite, all observation-derived (no hardcoded coordinates or archetype labels):

1. **VA's wasted-MP penalty** (`value.py`): `−5 × max(0, mp_spent − 1 × damage_caused)` when mp_spent ≥ 5. Detects offense plans that simulate as wasted (no breaches, no struct damage).

2. **VA's variance-aware ranking** (`search.py`): `mean − 0.1 × std` over k_opp samples. Demotes plans whose value depends on a single fortunate opp sample.

3. **VB's sim-based probe** (`viability_probe.py`): for each unique defense template, runs 5 synthetic scouts at center launcher (14, 0). Counts breaches, applies soft penalty `−50 × missing_breaches`.

4. **VC's breach-pressure coverage** (`value.py`): per-tile pressure tracking from `on_action_frame`, decay 0.85/turn. Coverage term: `0.2 × Σ (turret_threat × pressure)` for our turrets within attack range of high-pressure tiles.

5. **J2's pressure-derived lane_block templates** (`enumerator.py`): generate up to 2 defense templates per turn placing turrets within Chebyshev-2 of high-pressure tiles. Gates: `peak ≥ 2.5`, `peak / total ≥ 40%`, `turn > 12`.

6. **K2's path-blocking templates** (`enumerator.py`): for each top-pressure tile, call `game_state.find_path_to_edge(tile)` and place turrets at the path cells closest to opp territory. Mirrors snorkeldink-v3-2's reactive_defence mechanism. Required for the v3-2 fix.

### M2v3 validation

| Test | Result |
|---|---|
| Tier A (5 opps × 2 sides) | 10/10 ✓ |
| Tier B (snorkeldink-v3-1) | 2/2 ✓ |
| **lk_2l (sequential 20-rep)** | **19/20 (95%)** ✓ |
| **snorkeldink-v3-2** | **2/2 ✓** (was 0/6 across entire family) |
| H2H vs M1Lite | wins decisively |
| H2H vs VD | 2/2 ✓ |
| H2H vs J2 | 2/2 ✓ |
| Extended tournament (22 diverse opps) | **21/22 (95.5%)** |

**Single weakness**: snorkeldink-v3-3 stochastic 80% win rate (4/5 each side). Loss is by 4 HP margin (40-36); the variance comes from opp_model sampling shifting offense plan ranking. Attempted fix (M2v4) regressed lk_2l worse than the v3-3 problem — DISCARDED.

### Strict-superset properties

When breach_pressure is empty (early game, no breaches observed), M2v3 ≡ VD byte-identical. Adaptive features only fire when observation evidence is real.

---

## 4. Replay archive

109 matches archived at [replays/all_variants_archive_v2/](replays/all_variants_archive_v2/) (also `all_variants_archive/`). Per-variant breakdown:

| Variant | Live matches | Win rate (excl boss) |
|---|---|---|
| VA | 25 | 57% |
| VC | 22 | 67% |
| VD | 24 | 70% |
| VF | 20 | 69% |
| oracle_successor | 18 | 71% |

Manifest at `MANIFEST.json` for cross-referencing.

---

## 5. Critical lessons learned

### What worked
1. **VB's sim-based probe** — uses sim_rs as path oracle, NOT BFS. Avoids the M2 regression mode (BFS proven over-conservative; M1Lite's working SUPPORTS_BACK config = 0/10 viable per BFS yet wins matches).
2. **Probe-derived templates** (J2 lane_block, K2 path_block) — derive tile placements from observation, not hardcoded coordinates.
3. **VC's coverage signal** — passive value-function term, weight 0.2 calibrated to be a tie-breaker, not a dominant signal.
4. **K2's path-blocking** mirrors snorkeldink-v3-2's own reactive_defence — symmetric counter to v3-2's mechanism.
5. **Hybrid combinations** with proper gating: M2v3 = VA + VB + VC + J2 + K2 all firing only when observation evidence is real.

### What didn't work
1. **K1's low-threshold first-breach reaction** (pressure ≥ 0.5) — fired too aggressively, caused template inflation, crowded out offensive plans. Fixed by dropping K1 in favor of J2's better-gated lane_block.
2. **3-turret variants** (M2v4) — placed structures that disrupted oracle's offensive options against specific opponents (lk_2l).
3. **Hardcoded tile-list patches** (M2 ALT-OUTSIDE) — caused launcher-selection cascades, regressed live (per CONTEXT_HANDOFF.md §16).
4. **Increasing k_opp samples globally** — §8 Lesson 1: regresses by amplifying biased opp model signal.
5. **Memory-augmented opp model with multiplicative bias** (J4) — too aggressive boost factor, lost to siblings.

### Repeated patterns to avoid
- **§15 track record**: rationalizing fixes as principled when they have hardcoded elements. Verified again with M2v4 (3-turret variant disguised as "stronger interception" but actually opponent-specific tuning).
- **Local validation ≠ live ladder**: M2 (BFS path check) passed Tier A 10/10 then regressed live on ameyg. Same risk applies to all variants.
- **Strict-superset rule** must be applied to live ladder, not just local opponents.

---

## 6. Search algorithm architecture (current)

### M2v3 search loop per turn
```
on_turn(turn_state):
  1. base_state = adapt_game_state(turn_state)
  2. candidates = enumerate_plans(...)            ~1500-2500 plans
     • Standard 13 defense × 150 offense templates (M1Lite)
     • J2 lane_block templates (gated)
     • K2 path_block templates (gated)
  3. opp_samples = opp_model.sample(k=4)
  4. Phase 1: each candidate × 1 opp_sample → score    cull
  5. Probe (VB): per unique defense, sim with 5 scouts → defense_viability
  6. Phase 2: top-30 candidates × 4 opp samples → mean − 0.1×std
  7. Depth-2 (top-3): cheap heuristic projection
  8. Pick max-EU plan. Apply.
```

### Value function (`value.py` evaluate)
```
score = hp_term × 100
      + struct_term
      + sp_term + mp_term (saturating)
      + breach_term × 25
      + syn_term (support × scout)
      − waste_term (VA: mp_spent − damage_caused × 5)
      − trap_term (VB: missing_breaches × 50)
      + coverage_term (VC: turret × pressure × 0.2)
```

---

## 7. Files / folders inventory

### Upload folders (all in repo root)
- **oracle_pure_M1Lite_upload/** — original M1Lite baseline
- **oracle_pure_M1Lite_VA/B/C/D/E/F_upload/** — Wave 1 variants
- **oracle_pure_M1Lite_IS3/IS4/IS5/IS6_upload/** — trap-fix variants
- **oracle_pure_M1Lite_J1/J2/J3/J4_upload/** — single-tile-targeting variants
- **oracle_pure_M1Lite_K1/K2_upload/** — first-breach + path-block variants
- **oracle_pure_M1Lite_M2v2/M2v3/M2v4_upload/** — combined variants
- **oracle_pure_M1Lite_M2v3_upload/** — **PRIMARY SHIP RECOMMENDATION**

### Reports
- `M1LITE_VARIANTS_FINAL_REPORT.md` — comprehensive report on Wave 1+2 variants
- `oracle_pure_M1Lite_M2v3_upload/` and similar — each variant has its own VARIANT_X_REPORT.md
- This file: `ORACLE_PURE_M1LITE_FULL_CONTEXT.md` — session-level summary

### Replay archives
- `replays/all_variants_archive/` — 101 replays from earlier session
- `replays/all_variants_archive_v2/` — 109 replays from this session

### Live algos (terminal.c1games.com)
- VA (361470, rating 1898)
- VC (361472, rating 1901)
- VD (361471, rating 2014)
- VF (361519, rating 1885)
- oracle_successor (361523, rating 1940) — separate worktree at `.claude/worktrees/oracle-successor/`

---

## 8. Recommended next steps

If shipping M2v3:
1. Upload `oracle_pure_M1Lite_M2v3_upload/` as a new instance on the live ladder
2. Watch for ≥10 ranked matches; check for regressions on opps M1Lite/VD beats
3. If live regression: fall back to VD or M1Lite (both still live)

For future iteration:
1. **The unsolved snorkeldink-v3-3 stochastic loss** — 80% win rate. Variance source is opp_model sampling. Could be addressed by:
   - Increased k_opp samples ONLY for high-pressure plans (adaptive sampling)
   - Defense-specific bonus when target tile pressure is sustained
   - Different turret placement strategy that doesn't conflict with offensive plays
2. **Other unsolved deterministic losses** (aa0/swap3, suchir-g/python-algo) — need spatial-aware defense or opp structure prediction
3. **The M2v4 lesson**: any 3-turret-or-more variant must be tested against ALL existing wins (especially lk_2l-class opponents) before shipping

---

## Critical caveat (§15 redux)

This session log applied §15 caution by:
- Running 20-rep sequential tests on critical matchups (vs trusting single-rep validation)
- Comparing variants head-to-head before shipping recommendations
- Discarding regressing variants (M2v4) rather than rationalizing them
- Acknowledging stochasticity rather than overstating determinism

Despite this, M2v3 has not been live-validated. Local 95.5% extended-tournament win rate is the strongest signal we have, but live ladder may surprise.

End of context document.
