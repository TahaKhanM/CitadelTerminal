# Smart Oracle Lineage — Session Handoff

**Date:** 2026-04-27
**Session goal:** build a funnel-aware oracle algo on top of VD baseline; iterate to a classifier-gated combined algo on top of IS6.

This document is the entry point for the next Claude Code session. It summarises every artifact produced this session, every empirical result that drove the design, and the current ship state.

---

## TL;DR

Two new algos shipped this session, both upload-ready folders at the project root:

1. **`smart_oracle_vd_upload/`** — VD baseline + funnel detector + flank-corridor templates + opp-sample filter. Local battery 12/12 wins. Live ladder shows it beats VD on shared opp matchups (head-to-head 2-0 on `funnel-rush-v7 ameyg`).

2. **`smart_oracle_F_upload/`** — IS6 baseline + same funnel response, **gated by an OpponentClassifier** that fires the response only against single-archetype attackers. Local battery **18W / 0L / 2 ties out of 20**. Smart_F beats VD where pure IS6 alone loses to it. `algo.json` name = `smart_oracle_F`.

The classifier fix solved a regression we discovered along the way: IS6 + naive funnel response loses to oracle-style opponents because the flank-focused defense leaves center exposed to multi-archetype offense. Adding a classifier that only fires the response when the opponent is provably single-archetype eliminates the regression while preserving the funnel wins.

---

## Algo lineage (this session, from oldest to newest)

```
oracle_pure_M1Lite_VD_upload (existing — VD: VA + VB + VC)
        │
        ▼  add funnel_detector + flank-corridor templates + opp-sample filter
smart_oracle_vd_upload (NEW)
        │
        │  parallel investigation: IS6 was actually stronger than VD on shared opps
        │  IS6 = VA + VB + smart-refund (NO VC). VC was deliberately removed.
        ▼
oracle_pure_M1Lite_IS6_upload (existing — strongest M1Lite variant @ live ELO 2006)
        │
        ▼  merge: keep IS6, drop VC, add funnel response, gate behind classifier
smart_oracle_F_upload (NEW — recommended ship)
```

## What's in each new algo

### smart_oracle_vd_upload/
- Base: copy of `oracle_pure_M1Lite_VD_upload`
- New: `oracle_core/funnel_detector.py` (detect 3+ flank breaches in last 6 turns)
- New flank-corridor atom defs + conditional templates in `oracle_core/enumerator.py`
- `oracle_core/search.py` accepts `funnel_target`, filters opp samples, forwards to enumerator
- `algo_strategy.py` calls `detect_funnel_target` per turn
- Inherits VD's VC (breach-pressure coverage)

### smart_oracle_F_upload/
- Base: copy of `oracle_pure_M1Lite_IS6_upload`
- New: `oracle_core/funnel_detector.py` (TIGHTER thresholds — see below)
- New: `oracle_core/opp_classifier.py` (single vs multi archetype)
- New: `backtest_classifier.py` (replay-walking validation harness)
- Same flank-corridor atom defs / templates / opp-sample filter as smart_oracle_vd
- `algo_strategy.py` calls classifier per turn AND gates funnel_target on classifier verdict
- **No VC** (IS6's deliberate fix preserved)

---

## Key technical decisions

### Why two algos and not one?

Initially built smart_oracle_vd on top of VD because VD is the natural home for funnel-counter logic (VC's breach-pressure map captures funnel signals). After deeper analysis, IS6 (which deliberately deleted VC) turned out stronger. Smart_oracle_F migrated to IS6 base AND added a classifier to make the funnel response safe against IS6's opponent set.

Both folders are kept; smart_oracle_F is the recommended ship.

### Why the OpponentClassifier?

Without it, the funnel response (flank-corridor templates + opp-sample filter) hurts vs oracle-style opponents that have multi-archetype offense — they breach our flank only because their offense is broadly distributed; shifting our defense to the flank leaves center exposed.

The classifier distinguishes "single-tile drilldown" attackers (where the funnel response wins, e.g. ameyg/funnel-rush-v7) from "multi-archetype" attackers (where it regresses, e.g. IS6/VD/M1Lite). Decision rule:
- `SINGLE` if spawn-tile concentration ≥ 0.6 over last 10 opp turns
- `MULTI` if concentration ≤ 0.4 AND ≥3 distinct unit types
- `None` otherwise

The original concentration+types AND-logic missed multi-type funnels (ameyg uses 3 types at one tile = 92% conc, was misclassified MULTI). Backtest revealed this; revised logic uses concentration alone for SINGLE.

### Funnel detector thresholds (final)

After 4 rounds of tuning to eliminate false positives:
```python
FUNNEL_LOOKBACK = 12
FUNNEL_MIN_BREACHES = 10
FUNNEL_DOMINANCE_FRAC = 0.75
FUNNEL_TOP_TILE_FRAC = 0.4
```

Real funnels (ameyg drilled (2,11) × 56 vs M1Lite, (4,9) × 26 vs VD) easily clear these. Inter-oracle play caps at ~6-8 breaches → never triggers.

---

## Ship-decision priority

1. **smart_oracle_F** — primary recommendation. 18W/0L/2 ties locally. Improves on IS6 by beating VD where IS6 alone loses.
2. **smart_oracle_vd** — secondary. 12/12 locally. Live H2H vs VD shows +12.7 pp on shared opps. Use if smart_oracle_F shows live regression vs opponents we haven't faced locally.
3. **IS6 (`oracle_pure_M1Lite_IS6_upload`)** — fallback. Pure baseline. Smart_oracle_F is byte-equivalent to IS6 when classifier returns None or MULTI, so the regression risk is bounded.

---

## Empirical results (full benchmark battery, smart_oracle_F final)

20 matches against 10 opponents, both spawn sides:

| opponent | result | turns | points | notes |
|---|---|---|---|---|
| `IS6` (base) | 0W-0L-2tie (P2 wins both) | 33 | 42-42 | identical to IS6 self-play |
| `VD` | **2W-0L** | 56 / 68 | 45-36 / 43-36 | **WINS where pure IS6 loses 26-41** |
| `M1Lite` | **2W-0L** | 47 | 43-38 | matches IS6's record |
| `funnel_INTER` | **2W-0L** | 100 | 14-0 | |
| `funnel-rush-v9` | **2W-0L** | 22 | 41-0 | classifier fires SINGLE turn 15 |
| `funnel-crush` | **2W-0L** | 23 | 40-1 | classifier fires SINGLE turn 11 |
| `v13_second_ring` | **2W-0L** | 78 | 48-7 | |
| `heuristic_v1` | **2W-0L** | 53 | 45-4 | |
| `Lostkids/python-2l-aet` | **2W-0L** | 37 | 42-9 | |
| `smart_oracle_vd` | **2W-0L** | 56 | 45-32 | |

**Aggregate: 18W / 0L / 2 ties.**

Crashes: 0. Timeouts: 0. Per-turn wall-clock: 0.1-5.5s (search budget 11s; engine cap 15s).

---

## Live-ladder analysis (Citadel Spring 2026 Europe, comp_id=1338, team Wick)

Pulled via Claude in Chrome from `terminal.c1games.com/api/game/algo/{id}/matches` and `/api/game/replayexpanded/{match_id}`. **All raw analysis files saved at:**

- [data/smart_oracle_vd_common_opp_analysis.md](data/smart_oracle_vd_common_opp_analysis.md) — per-replay common-opponent analysis for smart_oracle_vd vs the 4 oracle siblings (IS5, IS6, M1Lite, VD)
- [data/oracle_full_pairwise_analysis.md](data/oracle_full_pairwise_analysis.md) — full pairwise comparison of all 5 oracle variants on shared opponent algos (76 unique replays, ranking by per-opponent head-to-head verdicts)
- [data/IS6_smart_compatibility.md](data/IS6_smart_compatibility.md) — code-level compatibility analysis of IS6 + smart_oracle_vd merge

### Key live findings

1. **IS6 is the strongest M1Lite variant on shared opps** (per-opp net +2, decisive WR .600 across 24 opponent evaluations). Beats M1Lite 3-1 head-to-head; ties VD 3-3 with 4 ties; perfectly mirrors IS5 on every shared opp (10/10 ties — they may be functionally equivalent).

2. **Smart_oracle_vd had +12.7 pp over its VD base** on the 6 opponents both played live (smart 8/11, VD 12/20). The funnel addition pays off where it matters.

3. **VD has a deliberate weakness on `pipmy/Redemption`** — VD's VC term causes a 55-HP swing on this matchup vs IS6 (VD lost hp-16, IS6 won hp+39). Confirmed by IS6's docstring deletion of VC. smart_oracle_F preserves the deletion.

4. **Smart_oracle_vd's only live loss was to aa0/python-algo** — a single-wave killshot the funnel detector's 12-turn lookback can't catch in time. Action item for future work: add a same-turn killshot detector.

---

## Files I created/modified this session (for git commit)

### New algo folders
- `smart_oracle_vd_upload/` — full algo folder (algo_strategy.py, oracle_core/, gamelib/, etc.)
- `smart_oracle_F_upload/` — full algo folder (includes opp_classifier.py + backtest_classifier.py)

### New analysis docs
- `data/smart_oracle_vd_common_opp_analysis.md`
- `data/oracle_full_pairwise_analysis.md`
- `data/IS6_smart_compatibility.md`

### This handoff
- `SMART_ORACLE_F_HANDOFF.md` (you are reading it)

### NOT mine — DO NOT touch in commit
- The various `oracle_pure_M1Lite_{IS3,IS4,IS5,IS6,J1-J4,K1-K2,M2v2-v4,VA,VB,VC,VD,VE,VF}_upload/` folders pre-existed or come from background scheduled tasks. The session's changes are smart_oracle_vd_upload and smart_oracle_F_upload only.
- Modified `algos/athena/sim/PARITY_REPORTS/2026-04-26.md` and other M files — not from this session.
- `data/CITADEL_LEADERBOARD.md`, `data/citadel_*.json` — pre-existing untracked from prior sessions.

---

## Next-Claude actions / future work

### Immediate (recommended)

- **Upload `smart_oracle_F_upload/` to terminal.c1games.com.** Folder is upload-ready: convention matches every existing oracle upload folder (algo.json, algo_strategy.py, run.sh, sim_bridge.py at root + bundled_sim_rs/, data/, gamelib/, oracle_core/, vendored_sim/ subdirs).
- Watch the first ~10 ranked games for live-ladder validation. Specifically:
  - vs ameyg's funnel-rush family (we have NEVER beaten ameyg's v7+v8 with any oracle variant locally; smart_oracle_F should beat them per design)
  - vs aa0's python-algo (the killshot that smart_oracle_vd lost to live)
  - vs any opponent IS6 hasn't faced before — those reveal whether the classifier gate misfires

### Hardening (deferred)

- **Single-wave killshot detector.** smart_oracle_F's classifier has a 4-turn cold start. aa0's python-algo killshots land in 1-2 turns from a single spawn — by the time the classifier flips SINGLE, we've lost 30+ HP. Add a separate detector that fires on a single high-magnitude breach event regardless of history.
- **Re-validate VD's VC removal.** smart_oracle_F's VD-beating performance is partially explained by VD's VC weakness on `pipmy/Redemption`. Worth re-checking whether VC could be re-added with a guard (e.g. only fire VC when classifier=MULTI). Saved in [data/IS6_smart_compatibility.md](data/IS6_smart_compatibility.md) §3.

### Don't do

- **Don't bypass the classifier gate.** The flank-corridor templates ARE in the candidate pool but the gate prevents them from being added to enumeration unless classifier=SINGLE. Removing the gate reintroduces the IS6-vs-IS6 32-41 regression observed during build.
- **Don't re-enable VC in smart_oracle_F.** IS6 deliberately removed it per a documented `pipmy/Redemption` regression. Re-introducing it would re-introduce that loss.
- **Don't modify the funnel detector thresholds without re-running the backtest.** I tuned them across 4 rounds (3→4→6→10 min breaches; 60%→75% dominance; added 40% top-tile gate) to eliminate false positives. Loosening them re-opens the IS6 regression.

---

## Repo orientation pointers

- **CLAUDE.md** at root explains the project (Citadel competition, oracle architecture).
- **docs/** has the canonical game rules / unit references / strategy guide.
- **algos/** has the canonical user algos (oracle_pure, athena, athena_v3_planner, etc.). The various `oracle_pure_M1Lite_*_upload/` folders at the root are upload-ready snapshots, not the canonical source.
- **C1GamesStarterKit-master/** has engine.jar, scripts (run_match.py, test_algo_*, zipalgo_*).
- **research/finalist_repos/** has decoded competitor algos (Lostkids, Midwest-2022, etc.) used as benchmark opponents.
- **data/** has analysis outputs from this and prior sessions.
- **/tmp/run_benchmark.sh** is a tiny wrapper script I wrote for running matches with strict-name endStats parsing (handles the parallel-match contention from background scheduled tasks). It's at /tmp not in the repo because it's session-only.

### Local benchmark workflow

```bash
# Single match between two algo folders (absolute paths required):
/tmp/run_benchmark.sh /Users/.../smart_oracle_F_upload /Users/.../oracle_pure_M1Lite_IS6_upload

# Output format: WIN=1|2 P1=name P2=name turns=N pts=A-B crash=(...) timeout=(...)
# Side-mirror by reversing the args.

# Backtest classifier on a replay file:
cd smart_oracle_F_upload
python3 backtest_classifier.py /path/to/replay.replay [--brief|--our-side p1|p2]
```

### Live-ladder API workflow (via Claude in Chrome)

```javascript
// In browser console at terminal.c1games.com (already authenticated):
// List my algos for the comp:
fetch('/api/game/algo/mine/1338').then(r=>r.json()).then(d=>console.log(d))

// Match history for an algo:
fetch('/api/game/algo/{algo_id}/matches').then(r=>r.json()).then(d=>console.log(d))

// Full replay (NDJSON):
fetch('/api/game/replayexpanded/{match_id}').then(r=>r.text()).then(t=>console.log(t.slice(0,2000)))
```

Citadel comp_id=1338 (Spring 2026 Europe). Team Wick id=5826. Five oracle algos at last fetch (2026-04-27T15:09Z):
- `oracle_pure_M1Lite_IS5_upload` id=361576 ELO 2000
- `oracle_pure_M1Lite_IS6_upload` id=361577 ELO 2006 ← STRONGEST baseline
- `oracle_pure_M1Lite_upload` id=361589 ELO 1961
- `oracle_pure_M1Lite_VD_upload` id=361590 ELO 1967
- `smart_oracle_vd_upload` id=361667 ELO 1897 (uploaded by user during session)

`smart_oracle_F` not yet uploaded; recommended next action.

---

## Per-folder upload-readiness checklist

Both `smart_oracle_vd_upload/` and `smart_oracle_F_upload/` follow the canonical oracle upload convention (verified by diff against `oracle_pure_M1Lite_VD_upload/`):
- algo.json (name field set)
- algo_strategy.py
- run.sh (executable)
- sim_bridge.py
- bundled_sim_rs/ (Linux x86_64 abi3 .so for the live server)
- data/ (citadel_config_snapshot.json, opp_model_priors.json)
- gamelib/ (vendored)
- oracle_core/ (algo logic)
- vendored_sim/ (Python fallback)
- 9 standard project docs (CONTEXT_HANDOFF, FUNNEL_COUNTER_PLAN, etc.) inherited from the base — **kept intentionally**, every other oracle upload includes them
- 1 variant-specific report (VARIANT_SMART_ORACLE_VD_REPORT.md / VARIANT_SMART_ORACLE_F_REPORT.md) — **see these for the full per-variant story**

Total folder size ~1.5MB each. No `.DS_Store`, no `errorFile*`, no `__pycache__` debris (gamelib/__pycache__ is included to match convention).

---

## Honest risks

1. **Live ladder ≠ local benchmark.** Smart_oracle_F's 18W/0L/2-tie local battery is encouraging but doesn't include any of the live opponents (aa0/python-algo, suchir-g, gencersarp, ameyg's actual ladder algos) that smart_oracle_vd lost to in live play.
2. **Classifier sample size on real funnels:** validated on 5 ground-truth replays (3 single-archetype, 2 multi-archetype). The single-tile concentration heuristic might fail on funnel patterns we haven't seen.
3. **Late-game classifier flips:** vs IS6 the classifier eventually flips to SINGLE around turn 31-32 (IS6 concentrates on (8,22) late). The 33t 42-42 outcome confirms this is harmless in IS6 self-play, but not validated against opponents that punish late-game defense shifts.
4. **The smart_oracle_F vs VD win is somewhat unexplained.** smart_oracle_F's classifier mostly returns MULTI vs VD, yet the game outcome diverges from pure IS6 vs VD. Likely the late-game SINGLE flip changes plan selection enough to shift the outcome. Worth tracing if it ever reverses on the live ladder.
