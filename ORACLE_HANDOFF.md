# Oracle Pure / M1Lite — Unified Handoff

**Last update**: 2026-04-27. Two separate sessions delivered work culminating in three commits on `main`:
- `533a111` — backtest corpus (410 replays + BACKTEST_CONTEXT.md + .gitignore whitelist)
- `ce44c09` — smart_oracle_F (classifier-gated funnel response on IS6 base)
- `f76b8a9` — M2v3 series (19 variants on VD baseline)

**Read this document first.** It points to the per-session detail docs and reconciles overlapping work.

---

## 1. What this project is

A search-driven algorithm for **Citadel Terminal** (special-rules variant of Correlation One's Terminal — 28×28 diamond grid tower defense, 100-turn matches with HP tiebreak).

Oracle's per-turn flow:
- Adapt game state via `state_adapter.adapt_game_state` → sim_rs schema dict
- Enumerate ~1500–2500 candidate plans (defense × offense cross-product)
- Two-phase evaluation: phase-1 cull (1 opp_sample per cand), phase-2 confidence (k_opp=4 samples on top-30)
- Optional depth-2 projection on top-3
- Pick highest expected-utility plan

**Live algo IDs** (terminal.c1games.com, competition 1338, team WICK 5826):
- VA: 361470 (~1898 ELO)
- VC: 361472 (~1901)
- VD: 361471 (~2014)
- VF: 361519 (~1885)
- oracle_successor: 361523 (~1940)
- smart_oracle_vd: live at ~1897 (per session 2's note; check `/api/game/algo/mine/1338` for ID)

---

## 2. The two ship-candidates (read both before deciding)

Two sessions independently produced strong ship-candidate variants from different lineages. They have NOT been compared head-to-head locally yet.

### Candidate A: **`smart_oracle_F_upload/`** (commit `ce44c09`)

**Lineage**: M1Lite → IS6 → smart_oracle_F.

**Key mechanism**: `OpponentClassifier` (in `oracle_core/opp_classifier.py`) — per-game single-vs-multi-archetype detector that gates the funnel-counter response.

**Key files**:
- `smart_oracle_F_upload/VARIANT_SMART_ORACLE_F_REPORT.md` — full mechanism + risks
- `smart_oracle_F_upload/oracle_core/opp_classifier.py` — the classifier
- `smart_oracle_F_upload/backtest_classifier.py` — replay-walking validation harness
- `SMART_ORACLE_F_HANDOFF.md` — session 2's entry-point doc
- Live-ladder analysis: `data/smart_oracle_vd_common_opp_analysis.md`, `data/oracle_full_pairwise_analysis.md`
- Compatibility analysis: `data/IS6_smart_compatibility.md`

**Sibling**: `smart_oracle_vd_upload/` — VD + funnel-counter (already live as smart_oracle_vd at ~1897 ELO).

### Candidate B: **`oracle_pure_M1Lite_M2v3_upload/`** (commit `f76b8a9`)

**Lineage**: M1Lite → VD → M2v3 (= VD + J2's pressure-derived lane_block templates + K2's path-blocking via `find_path_to_edge`).

**Key mechanism**: observation-derived defense templates that fire when breach_pressure shows sustained single-tile targeting. Mirrors snorkeldink-v3-2's own reactive_defence.

**Key files**:
- `oracle_pure_M1Lite_M2v3_upload/VARIANT_M2_REPORT.md` — full mechanism + risks
- `oracle_pure_M1Lite_M2v3_upload/oracle_core/enumerator.py` — adds `_enumerate_lane_block_templates` and `_gen_path_block_templates`
- `oracle_pure_M1Lite_M2v3_upload/oracle_core/viability_probe.py` — VB sim-based probe
- `ORACLE_PURE_M1LITE_FULL_CONTEXT.md` — session 1's full summary
- `M1LITE_VARIANTS_FINAL_REPORT.md` — earlier Wave 1+2 report

### Differences worth noting

| | smart_oracle_F | M2v3 |
|---|---|---|
| Trigger | Discrete classifier (single vs multi archetype) | Continuous breach_pressure observation |
| Base | IS6 (probe-driven smart-refund) | VD (VA + VB + VC hybrid) |
| Risk vector | Classifier may misclassify on novel opps | Templates may fire on noise / crowd out offense |
| Validated against | Common-opp pairwise on live replays | 22-opp local extended tournament + 20-rep sequential |

**The two sessions did not run a head-to-head between smart_oracle_F and M2v3.** That's a critical next step — see §6.

---

## 3. Variant inventory (full)

All `oracle_pure_M1Lite_*_upload/` and `smart_oracle_*_upload/` folders are at the repo root.

### Wave 1 (initial diversification, M1Lite baseline)
| Folder | Mechanism | Status |
|---|---|---|
| oracle_pure_M1Lite_VA_upload | I3 wasted-MP penalty + I7 variance ranking | LIVE |
| oracle_pure_M1Lite_VB_upload | I2 sim-based viability probe (alone) | not live |
| oracle_pure_M1Lite_VC_upload | I4 breach-pressure coverage | LIVE |
| oracle_pure_M1Lite_VD_upload | VA + VB + VC hybrid (strongest baseline) | LIVE — `smart_oracle_vd` derived from this |
| oracle_pure_M1Lite_VE_upload | Demo-heavy + multi-launcher offense | regressed, not live |
| oracle_pure_M1Lite_VF_upload | VA + VB without VC | LIVE |

### Wave 2 (trap-fix on VD)
| Folder | Mechanism |
|---|---|
| oracle_pure_M1Lite_IS3_upload | Delete `defense:supports` template entirely |
| oracle_pure_M1Lite_IS4_upload | 5 SUPPORT_POSITION_SETS (probe picks among) |
| oracle_pure_M1Lite_IS5_upload | KIND_REMOVE op + probe-driven removal |
| oracle_pure_M1Lite_IS6_upload | Probe-driven `defense:refund_smart_X_Y` (smart_oracle_F base) |

### Wave 3 (single-tile-targeting attempts)
| Folder | Mechanism |
|---|---|
| oracle_pure_M1Lite_J1_upload | Adaptive-weight transit-pressure spatial value |
| oracle_pure_M1Lite_J2_upload | Pressure-derived lane_block templates |
| oracle_pure_M1Lite_J3_upload | Path-prediction in evaluation (sim-based opp probe) |
| oracle_pure_M1Lite_J4_upload | Memory-augmented opp model (launcher-bias) |

### Wave 4 (snorkeldink-v3-2 fix)
| Folder | Mechanism |
|---|---|
| oracle_pure_M1Lite_K1_upload | First-breach reaction (low pressure threshold) |
| oracle_pure_M1Lite_K2_upload | Path-blocking via `find_path_to_edge` |
| oracle_pure_M1Lite_M2v2_upload | K1 + K2 combined (regressed lk_2l) |
| **oracle_pure_M1Lite_M2v3_upload** | **J2 lane_block + K2 path-block — primary ship** |
| oracle_pure_M1Lite_M2v4_upload | M2v3 + 3-turret variant (regressed lk_2l, discarded) |

### Smart-oracle line
| Folder | Mechanism |
|---|---|
| smart_oracle_vd_upload | VD + funnel-counter (live as smart_oracle_vd) |
| **smart_oracle_F_upload** | **IS6 + OpponentClassifier-gated funnel response — primary ship** |

---

## 4. Backtest corpus (commit 533a111)

410 oracle replays organized by variant under [Ranked Replays/](Ranked Replays/) with `_archive/<variant>/` provenance grouping.

**Read first**: [docs/BACKTEST_CONTEXT.md](docs/BACKTEST_CONTEXT.md) — entry-point doc that:
- Ranks all 14 local opponents by difficulty (Snorkeldink-v3-2 / Lostkids-base = hardest at 0/2; starter python-algo = easiest at 10/10)
- Enumerates 5 server-only boss tier (R1 Sawtooth, R2 Infiltrator, R3 Jukebox, R4 Champion + 1 more)
- Points to manifest files and how to run backtests

**Folder structure**:
- `Ranked Replays/<variant>/` — replays per algorithm (e.g., `Ranked Replays/oracle_pure_M1Lite_VA/`)
- `Ranked Replays/_provenance/` — original v1+v2 archive MANIFEST files
- `Ranked Replays/ORACLE_REPLAYS.md` + `ORACLE_REPLAYS_MANIFEST.json` — folder-level index

**Whitelist behavior**: `.gitignore` explicitly whitelists the oracle subset of `Ranked Replays/`. Non-oracle algo replays (athena_baseline/, lostkids_v2/, etc.) stay ignored.

---

## 5. Validation infrastructure

### Local match runner
```bash
python3 C1GamesStarterKit-master/scripts/run_match.py \
  <P1_path>/oracle_pure_M1Lite_VARIANT_upload \
  <P2_path>/research/finalist_repos/<opponent>
```
Output: `Winner (p1 perspective, 1 = p1 2 = p2): N`

### Tier A regression floor (10 matches: 5 opps × 2 sides)
- v13_second_ring (`algos/v13_second_ring/`)
- python-algo (`C1GamesStarterKit-master/python-algo/`)
- heuristic_v1 (`algos/heuristic_v1/`)
- Lostkids/python-2l-aet (`research/finalist_repos/Terminal-Lostkids/python-2l-aet/`)
- funnel_INTER (`research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/`)

### Tier B breakthrough preservation
- snorkeldink-v3-1 (`research/finalist_repos/terminal-c1/snorkeldink-v3-1/`)

### Extended tournament (22 matches: 11 diverse opps × 2 sides)
Lostkids variants (python, 2l, 2l-md, 2l-aet, v1) + Harvard variants (python, snorkeldink-v3-1/v3-2/v3-3/AdapDef, frumblesnatch, algo-1) + GT python-algo + Midwest funnel_INTER.

### Critical adversarial matchup
**snorkeldink-v3-2** at `research/finalist_repos/terminal-c1/snorkeldink-v3-2/` — was a deterministic 0/6 loss across the entire prior oracle family (M1Lite, VA, VC, VD, VF, J1, J2). M2v3 fixes it 2/2 via K2 path-blocking. **Any new variant must preserve this fix.**

### Reliability note
The Citadel sim has stochasticity (PYTHONHASHSEED). For close matchups (e.g., snorkeldink-v3-3), run 10–20 sequential reps per side. Single-rep tournaments can be misleading — see §15 of M2v4 retrospective in `ORACLE_PURE_M1LITE_FULL_CONTEXT.md`.

---

## 6. Open questions / immediate next steps

1. **Head-to-head between smart_oracle_F and M2v3.** Both are ship-candidates from different lineages. Suggested protocol:
   - Run them locally vs each other (10 reps × 2 sides = 20 matches) to find the stronger
   - Run each against the snorkeldink-v3-2 critical opp (3 reps × 2 sides = 6 each) to confirm both fix it
   - Run each against lk_2l (Lostkids/python-2l) — M2v3 wins 19/20; smart_oracle_F status unknown
   - Cross-check on extended 22-opp tournament for consistency

2. **Live ladder upload of the winner.** Existing live algos (VA, VC, VD, VF, oracle_successor, smart_oracle_vd) provide good A/B comparators. Upload the winner of the H2H as a new instance, watch ≥10 ranked matches, fall back to VD if regressions appear.

3. **Unsolved deterministic losses (still present in both candidates)**:
   - `aa0/swap3` — right-corner funnel, not in local opp set
   - `suchir-g/python-algo` — right-flank demo spam
   - These need either (a) deeper architectural changes (multi-turn lookahead, opp structure prediction) or (b) live-ladder data to diagnose

4. **Stochastic 80% win rate on snorkeldink-v3-3** (M2v3-specific). Variance source is opp_model sampling shifting offense plan ranking, which propagates to defense pairing. Possible fixes:
   - Adaptive k_opp samples ONLY for high-pressure plans
   - Defense-specific bonus when target tile pressure is sustained
   - DON'T do M2v4-style 3-turret variant (regresses lk_2l 0/20)

---

## 7. Reading order for the next Claude session

1. **This document** (you're here)
2. [docs/BACKTEST_CONTEXT.md](docs/BACKTEST_CONTEXT.md) — local opp difficulty + how to backtest
3. [ORACLE_PURE_M1LITE_FULL_CONTEXT.md](ORACLE_PURE_M1LITE_FULL_CONTEXT.md) — session 1's full M2v3 lineage
4. [SMART_ORACLE_F_HANDOFF.md](SMART_ORACLE_F_HANDOFF.md) — session 2's smart_oracle_F entry point
5. [oracle_pure_M1Lite_VA_upload/CONTEXT_HANDOFF.md](oracle_pure_M1Lite_VA_upload/CONTEXT_HANDOFF.md) — pre-session §15 (track record) and §16 (M2 lessons)
6. Per-variant `VARIANT_*_REPORT.md` files — for variants you'll touch

Then **before any work**:
- Verify git is clean: `git status` (some pre-existing untracked files: `ORACLE_SUCCESSOR_PROMPT.md`, `data/citadel_*.json`, scheduled-task artifacts — leave them untouched unless asked)
- Check live ladder state: API call to `/api/game/algo/mine/1338` via Claude in Chrome or curl with `~/.c1_session.json`

---

## 8. Critical reminders (from §15 of pre-session CONTEXT_HANDOFF.md)

These apply to every variant decision:

- **Hardcoded tile-list patches** (M2-style ALT-OUTSIDE swap) regress live. Don't repeat.
- **Single-rep validation** can mislead — run 10–20 sequential reps for close matchups.
- **Local validation ≠ live ladder.** M2 (BFS path check) passed Tier A 10/10 then regressed live on ameyg/funnel-rush. Same risk applies.
- **Strict-superset rule**: every milestone must beat every opp the prior milestone beat. Violations include both clear regressions (M2v2 lk_2l 0/2, M2v4 lk_2l 0/20) and subtle ones (J2 snorkeldink-v3-2 0/2 was not caught until extended testing).
- **More compute ≠ better.** §8 Lesson 1: bumping `k_opp` regressed (FUNNEL, Lostkids); G3 prior rebuild broke 4/10 Tier A.
- **The opp model is the limiting factor** — Python hash-seed RNG affects sample ordering, which affects plan ranking, which propagates to outcomes. Variance is real.

End of handoff.
