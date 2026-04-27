# Oracle Backtest Context — Strategies & Replay Corpus

**Snapshot date:** 2026-04-27
**Scope:** Every algorithm against which the Oracle family is simulated
locally + every replay we have on disk that involves an Oracle-named algo,
indexed for use in future backtests, regression tests, and counterfactual
evals.

This file is the single entry point for "what does Oracle get tested
against, and where are the replays?". For deep architecture details on
Oracle itself, read [oracle_pure_M1Lite_upload/REPORT.md](../oracle_pure_M1Lite_upload/REPORT.md)
and [oracle_pure_M1Lite_upload/CONTEXT_HANDOFF.md](../oracle_pure_M1Lite_upload/CONTEXT_HANDOFF.md).

---

## 1. Opponent set used for local backtest

Oracle (`oracle_pure_M1Lite`) is the canonical search-driven algo on `main`.
Its local validation harness exercises it against three concentric tiers of
opponents: a *bar* set it must beat 10/10 to ship, an *informational* set
that documents real strengths and weaknesses, and a *server-only* boss tier
that cannot run locally.

### 1.1 Ranking — hardest to easiest

Hardness is measured as Oracle's own win rate over the local
`scripts/run_match.py` harness using the shipping config
(`k_opp=6, k_opp_phase1=1, phase2_top_n=30, depth2_top_n=3,
max_plans=2500, time_budget_s=11.0`). Each opponent's "result" is *Oracle's*
record — both sides played, deterministic engine.

| # | Opponent (path) | Origin | Result | Tier | Notes |
|---:|---|---|---|---|---|
| 1 | [snorkeldink-v3-1](../research/finalist_repos/terminal-c1/snorkeldink-v3-1/algo_strategy.py) | **Harvard #1** ("The Travelling Salesmen") | **0/2** | hardest | Demo-funnel; same archetype that produces 6/8 of Oracle's live losses |
| 2 | [snorkeldink-v3-3](../research/finalist_repos/terminal-c1/snorkeldink-v3-3/algo_strategy.py) | Harvard variant | 0/2 | hardest | 129-line compact form |
| 3 | [snorkeldink-AdapDef](../research/finalist_repos/terminal-c1/snorkeldink-AdapDef/algo_strategy.py) | Harvard adaptive variant | 0/2 | hardest | Adapts wall corridor to Oracle |
| 4 | [snorkeldink-v3-2](../research/finalist_repos/terminal-c1/snorkeldink-v3-2/algo_strategy.py) | Harvard variant | mixed | hardest | Added in K2 testing pass |
| 5 | [Lostkids/python-2l](../research/finalist_repos/Terminal-Lostkids/python-2l/algo_strategy.py) | **LostKids** (APAC 3rd) — non-adaptive base | **0/2** | hardest | V-shape funnel-eater |
| 6 | [Lostkids/python-2l-aet](../research/finalist_repos/Terminal-Lostkids/python-2l-aet/algo_strategy.py) | **LostKids** adaptive ("aet") | **10/10** | bar (Tier-3) | Hardest opponent Oracle reliably beats; λ=0.3 in VA regressed against this |
| 7 | [funnel_INTER](../research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER/algo_strategy.py) | **Midwest FUNNEL** ("Murphy's Lawyers", 5/24) | **10/10** | bar (Tier-3) | 723 lines — the largest opponent codebase |
| 8 | [Lostkids/python-2l-md](../research/finalist_repos/Terminal-Lostkids/python-2l-md/algo_strategy.py) | LostKids medium-adaptive variant | 2/2 | informational | |
| 9 | [terminal_c1_gt/python-algo](../research/finalist_repos/terminal_c1_gt/python-algo/) | **Georgia Tech** (Citadel team, wang5 base) | 2/2 | informational | Multi-strategy folder; uses one of `algo_strategy_wang*.py` at runtime |
| 10 | [frumblesnatch-v1](../research/finalist_repos/terminal-c1/frumblesnatch-v1/algo_strategy.py) | Season-1 public algo | 2/2 | informational | |
| 11 | [v13_second_ring](../algos/v13_second_ring/algo_strategy.py) | Oracle's predecessor (still on the server, rating 1707) | **10/10** | regression floor | Dropping below this = Oracle got worse |
| 12 | [heuristic_v1](../algos/heuristic_v1/algo_strategy.py) | First in-house heuristic | **10/10** | bar (Tier-2) | Static defense + scout rush; trap-bug exposed Oracle here at T67-T68 |
| 13 | [heuristic_v2](../algos/heuristic_v2/algo_strategy.py) | More aggressive heuristic, 798 lines | informal | tournament | Used in `replays/bestof_heuristic_v2_*` runs |
| 14 | [python-algo (starter)](../C1GamesStarterKit-master/python-algo/algo_strategy.py) | Correlation One starter kit | **10/10** | bar (Tier-1, sanity) | Must win 100%; used as smoke test |

### 1.2 Server-only (untestable locally)

Citadel boss algos are hosted on the competition server only — no public
binaries exist:

- `R1_Sawtooth`
- `R2_Infiltrator`
- `R3_Jukebox`
- `R4_Champion`
- `PunchbagRob`

Oracle has played all five in live ranked matches. Recordings live under
each algo folder's `_archive/` (see Section 2).

### 1.3 What "hardest" really means

Snorkeldink and the live-ladder funnel family (`ameyg/funnel-rush-v6/v7/v8`,
`aa0/funnel-a`, `aa0/swap3`, `gencersarp/babayaga2`,
`ashmit/funnel-crush-before`, `suchir-g/python-algo`) all share a single
mechanic: **walls in y=14-20 channel attackers around Oracle's center
turret cluster (x=11-16) into thinly-covered flanks (x ≤ 7 OR x ≥ 20)**. 88-100 % of HP loss in those games lands on those flank corners. This
is the single unsolved structural weakness — see
[oracle_pure_M1Lite_VD_upload/FUNNEL_COUNTER_PLAN.md](../oracle_pure_M1Lite_VD_upload/FUNNEL_COUNTER_PLAN.md)
for the empirical signature and mechanical root cause. Variants VA, VC, and
VD ([M1LITE_VARIANTS_FINAL_REPORT.md](../M1LITE_VARIANTS_FINAL_REPORT.md))
are the explicit attempts to address this.

---

## 2. Replay corpus

**410 oracle replays total** (121 server-current + 289 archived) are
organized one folder per source algo under `Ranked Replays/`. Layout and
counts are documented in
[Ranked Replays/ORACLE_REPLAYS.md](../Ranked%20Replays/ORACLE_REPLAYS.md);
the machine-readable manifest is
[Ranked Replays/ORACLE_REPLAYS_MANIFEST.json](../Ranked%20Replays/ORACLE_REPLAYS_MANIFEST.json).

### 2.1 Per-folder summary

| Source folder | Status | algo_id | Rating | Current | Archive | Total |
|---|---|---:|---:|---:|---:|---:|
| `oracle_pure_M1Lite_upload` | live | 361589 | 2020 | 20 | 91 | 111 |
| `oracle_pure_M1Lite_IS5_upload` | live | 361576 | 2014 | 31 | 0 | 31 |
| `oracle_pure_M1Lite_IS6_upload` | live | 361577 | 2024 | 23 | 0 | 23 |
| `oracle_pure_M1Lite_VD_upload` | live | 361590 | 1980 | 30 | 25 | 55 |
| `smart_oracle_vd_upload` | live | 361667 | 1921 | 17 | 1 | 18 |
| `oracle_pure_M1Lite_VA_upload` | superseded | 361470 | — | 0 | 25 | 25 |
| `oracle_pure_M1Lite_VC_upload` | superseded | 361472 | — | 0 | 22 | 22 |
| `oracle_pure_M1Lite_VF_upload` | superseded | 361519 | — | 0 | 21 | 21 |
| `oracle_pure_upload` | superseded | — | — | 0 | 82 | 82 |
| `oracle_upload` | superseded | 361523 | — | 0 | 18 | 18 |
| `oracle_pure_M2_upload` | superseded | — | — | 0 | 4 | 4 |
| **TOTAL** | | | | **121** | **289** | **410** |

`oracle_pure_M1Lite_upload`, `_IS5_upload`, and `_IS6_upload` share the
**same** `algo_strategy.py` (sha1 `a26358ab7f6b`, 306 lines) — three slots
running the M1Lite code in parallel for matchmaking variance.

### 2.2 Per-folder structure

```
Ranked Replays/<source_folder>/
├── _source/                         → symlink to ../../<source_folder>/   (algo source)
├── <algo>_<outcome>_<opp>_<rating>_<match_id>.replay   ← CURRENT ranked replays (live algos only)
└── _archive/
    └── <variant>/                   ← grouped by historical-variant prefix
        └── <variant>_<W|L>_<opp_user>_<opp_algo>_t<turns>_<match_id>.replay
```

Variant prefixes inside `_archive/` correspond to earlier server uploads of
the same source code under a different `algo_id`. Known historical IDs:

| variant | historical algo_id |
|---|---:|
| VA | 361470 |
| VC | 361472 |
| VD (older) | 361471 |
| VF | 361519 |
| oracle_successor | 361523 |
| (others — `M1L_old`, `M1L_new`, `m1_lite_inst1..3`, `m1l_inst1`, `oracle_pure_inst1..3`, `m2_inst1..2`, `oracle_pure`) | not recorded |

### 2.3 Provenance

Original index files from the two earlier bulk-pull batches are preserved at
[Ranked Replays/_provenance/](../Ranked%20Replays/_provenance/). They name
the historical batch each replay came from and were used to drive the
deduplication pass that removed 58 duplicate match_ids when the two
archives were merged.

---

## 3. How to run a backtest

### 3.1 Single match (deterministic engine)

```bash
python3 C1GamesStarterKit-master/scripts/run_match.py \
    oracle_pure_M1Lite_upload \
    research/finalist_repos/terminal-c1/snorkeldink-v3-1
```

Replays land in `C1GamesStarterKit-master/replays/`. Move into
`replays/local/` for persistence.

### 3.2 Two-sided pair (the local "10/10" measurement)

```bash
# bestof N=5 plays 5 games on each side = 10 total
.claude/skills/bestof/run.sh oracle_pure_M1Lite_upload <opponent> 5
```

This is what the "10/10" / "2/2" results in the table above are based on.
Output goes into `replays/bestof_<algo_a>_vs_<algo_b>_<timestamp>/`.

### 3.3 Tournament across multiple opponents

```bash
.claude/skills/tournament/run.sh oracle_pure_M1Lite_upload \
    research/finalist_repos/Terminal-Lostkids/python-2l-aet \
    research/finalist_repos/Terminal-C1-Midwest-2022/funnel_INTER \
    research/finalist_repos/terminal-c1/snorkeldink-v3-1 \
    algos/v13_second_ring \
    algos/heuristic_v1
```

Plays every pair twice (each side once); reports per-pair win-rate.

### 3.4 Counterfactual replay-trace (Athena's G11-style eval)

`algos/athena_v3_planner/eval/replay_trace.py` replays a corpus of recorded
games, substituting `Athena` for one of the players and measuring outcome
delta. The full Phase-7 G11 result (47 replays / 30 distinct opponents,
ELO 1100-1929) lives at
[algos/athena_v3_planner/data/G11_RESULTS.md](../algos/athena_v3_planner/data/G11_RESULTS.md).
Athena predicted 39W/8L (LB95 0.699) vs v13's actual 22W/25L (LB95 0.333)
on that corpus — a +36.2 pp delta.

### 3.5 Refresh the live replay corpus

Re-pulls every ranked replay for every currently-uploaded algo on
`terminal.c1games.com`. Cookies must be in `~/.c1_session.json`
(extracted from a logged-in Chrome).

```bash
# all live algos
python3 tools/bulk_download_my_replays.py --delay 0.1 --out "Ranked Replays"

# filter to oracle algos only
python3 tools/bulk_download_my_replays.py --algos 361589 361576 361577 361590 361667 \
    --delay 0.1 --out "Ranked Replays"
```

`--skip-existing` (default) avoids redownloading.

---

## 4. Quick-reference numbers

| Constant | Value |
|---|---|
| Server competition | Citadel — `comp_id=1338`, "Spring 2026 Europe Terminal" |
| Team | WICK — `team_id=5826` |
| Live oracle algos | 5 (`oracle_pure_M1Lite_upload`, `_IS5`, `_IS6`, `_VD`, `smart_oracle_vd_upload`) |
| Live oracle algo IDs | 361589, 361576, 361577, 361590, 361667 |
| Total local opponents | 14 (12 distinct files + 2 GT/GtVariants in the same folder) |
| Bar tier opponents | 5 (`python-algo`, `heuristic_v1`, `python-2l-aet`, `funnel_INTER`, `v13_second_ring`) |
| Informational tier opponents | 7 (3 win + 4 loss) |
| Server-only boss algos | 5 (`R1_Sawtooth`, `R2_Infiltrator`, `R3_Jukebox`, `R4_Champion`, `PunchbagRob`) |
| Total oracle replays on disk | 410 |
| Replay corpus size | ~3.0 GB |

---

## 5. Related references

- Oracle architecture: [oracle_pure_M1Lite_upload/REPORT.md](../oracle_pure_M1Lite_upload/REPORT.md)
- Funnel-counter analysis (the unsolved problem): [oracle_pure_M1Lite_VD_upload/FUNNEL_COUNTER_PLAN.md](../oracle_pure_M1Lite_VD_upload/FUNNEL_COUNTER_PLAN.md)
- M1Lite improvement-variant outcomes (VA / VB / VC / VD / VE): [M1LITE_VARIANTS_FINAL_REPORT.md](../M1LITE_VARIANTS_FINAL_REPORT.md)
- Athena (separate planner-based algo) Phase-7 results: [algos/athena_v3_planner/FINAL_REPORT.md](../algos/athena_v3_planner/FINAL_REPORT.md)
- Citadel live API + cookie auth: `~/.claude/projects/-Users-tahakhan-Documents-Work-Projects-CitadelTerminal/memory/citadel_live_api.md`
- Finalist source-repo origin URLs: [research/finalist_repos/README.md](../research/finalist_repos/README.md)
