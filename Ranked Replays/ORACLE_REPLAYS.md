# Oracle Replays — Index

Every Oracle replay we have on disk, organized by the source-code folder that
produced it. Snapshot date **2026-04-27**, competition Citadel
(`comp_id=1338`), team **WICK** (id 5826).

## Layout

```
Ranked Replays/
├── ORACLE_REPLAYS.md                         ← you are here
├── ORACLE_REPLAYS_MANIFEST.json              ← machine-readable manifest
├── _provenance/
│   ├── all_variants_archive_v1_MANIFEST.json ← original index from older bulk pull
│   └── all_variants_archive_v2_MANIFEST.json ← original index from later bulk pull
└── <source_folder>/
    ├── _source       → symlink to ../../<source_folder>/   (algo source code)
    ├── <algo>_<outcome>_<opp>_<rating>_<match_id>.replay   ← CURRENT ranked replays
    │                                                         (only for live algos)
    └── _archive/
        └── <variant>/                ← group by historical-variant prefix
            └── <variant>_<W|L>_<opp_user>_<opp_algo>_t<turns>_<match_id>.replay
```

`_archive/` holds **older** ranked replays that were downloaded earlier in the
project (the variant prefixes — `VA`, `VC`, `VD`, `VF`, `oracle_pure`,
`oracle_pure_inst1..3`, `m1_lite_inst1..3`, `m1l_inst1`, `m2_inst1..2`,
`M1L_old`, `M1L_new`, `oracle_successor`) come from earlier uploads of the
same source code under a different server `algo_id`. The historical IDs we
know (from `_provenance/`):

| variant | historical algo_id | current upload folder |
|---|---:|---|
| VA | 361470 | oracle_pure_M1Lite_VA_upload |
| VC | 361472 | oracle_pure_M1Lite_VC_upload |
| VD (older) | 361471 | oracle_pure_M1Lite_VD_upload (current id 361590) |
| VF | 361519 | oracle_pure_M1Lite_VF_upload |
| oracle_successor | 361523 | oracle_upload |

## Folders

`current` = ranked replays for the algo currently uploaded to the server
(only the 5 live oracle algos). `archive` = older ranked replays grouped by
variant prefix.

| Source folder | Server status | algo_id | Rating | Current | Archive | Total |
|---|---|---:|---:|---:|---:|---:|
| [oracle_pure_M1Lite_upload](oracle_pure_M1Lite_upload/) | live | 361589 | 2020 | 20 | 91 | 111 |
| [oracle_pure_M1Lite_IS5_upload](oracle_pure_M1Lite_IS5_upload/) | live | 361576 | 2014 | 31 | 0 | 31 |
| [oracle_pure_M1Lite_IS6_upload](oracle_pure_M1Lite_IS6_upload/) | live | 361577 | 2024 | 23 | 0 | 23 |
| [oracle_pure_M1Lite_VD_upload](oracle_pure_M1Lite_VD_upload/) | live | 361590 | 1980 | 30 | 25 | 55 |
| [smart_oracle_vd_upload](smart_oracle_vd_upload/) | live | 361667 | 1921 | 17 | 1 | 18 |
| [oracle_pure_M1Lite_VA_upload](oracle_pure_M1Lite_VA_upload/) | superseded | 361470 | — | 0 | 25 | 25 |
| [oracle_pure_M1Lite_VC_upload](oracle_pure_M1Lite_VC_upload/) | superseded | 361472 | — | 0 | 22 | 22 |
| [oracle_pure_M1Lite_VF_upload](oracle_pure_M1Lite_VF_upload/) | superseded | 361519 | — | 0 | 21 | 21 |
| [oracle_pure_upload](oracle_pure_upload/) | superseded | — | — | 0 | 82 | 82 |
| [oracle_upload](oracle_upload/) | superseded | 361523 | — | 0 | 18 | 18 |
| [oracle_pure_M2_upload](oracle_pure_M2_upload/) | superseded | — | — | 0 | 4 | 4 |
| | | | **TOTAL** | **121** | **289** | **410** |

*Note*: 3 curated replays (`*_vs_ameyg_funnel_*.replay`) were filed under each
algo's `_archive/curated/`. The `_archive/` totals above include them.

## Variant breakdown inside `_archive/`

`oracle_pure_M1Lite_upload/_archive/` (90 replays, six variants):

| variant subdir | count | meaning |
|---|---:|---|
| M1L_old | 27 | early M1Lite snapshot |
| M1L_new | 16 | later M1Lite snapshot |
| m1_lite_inst1 | 16 | first slot, named with `m1_lite_` prefix |
| m1_lite_inst2 | 11 | second slot |
| m1_lite_inst3 | 17 | third slot |
| m1l_inst1 | 3 | one-off prefix variant |

`oracle_pure_upload/_archive/` (82 replays, four variants — the original
`oracle_pure` algo plus three slot instances):

| variant subdir | count |
|---|---:|
| oracle_pure | 20 |
| oracle_pure_inst1 | 7 |
| oracle_pure_inst2 | 29 |
| oracle_pure_inst3 | 26 |

Other folders contain a single variant subdir each (`VA/`, `VC/`, `VD/`,
`VF/`, `m2_inst1/`, `m2_inst2/`, `oracle_successor/`).

`oracle_pure_M1Lite_VF_upload/_archive/` also has a `curated/` subdir with one
hand-picked replay (`Oracle_Pure_M1_Lite_VF_SD_loss.replay`) that pre-existed
this consolidation.

## Source-code provenance

`oracle_pure_M1Lite_upload`, `IS5`, and `IS6` were uploaded with **identical**
`algo_strategy.py` (sha1=`a26358ab7f6b`, 306 lines) — three slots running the
same M1-Lite code in parallel for matchmaking variance. `VD` and
`smart_oracle_vd` are distinct codebases (349 / 367 lines). All other folders
have their own source files; see `_source/algo_strategy.py` inside each
folder. Exact hashes live in `ORACLE_REPLAYS_MANIFEST.json`.

## How to refresh / extend

- **Re-pull live replays** (the 5 algos currently on the server):
  `python3 tools/bulk_download_my_replays.py --algos 361589 361576 361577 361590 361667 --delay 0.1 --out "Ranked Replays"`
  (with `~/.c1_session.json` populated from a logged-in Chrome session).
- **Add a new oracle variant**: re-upload it under a new algo name on the
  competition site, get its `algo_id` from `/api/game/algo/mine/1338`, append
  to the script's `--algos` list, re-run.
- **Source folders without `_source/algo_strategy.py`** (e.g.
  `oracle_pure_M2_upload`) only have a partial scaffold on disk — replays for
  them exist but the strategy file would need to be reconstructed from git
  history if needed.
