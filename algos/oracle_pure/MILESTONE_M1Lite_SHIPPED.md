# Milestone M1-Lite — SHIPPED

**Branch**: `oracle-pure-M1-Lite`
**Upload folder**: `oracle_pure_M1Lite_upload/`
**Ship contents**: G7 only (G3 deferred — see `M1_LITE_SHIP_AND_G3_INVESTIGATE.md` PART 2 for the investigation track).

---

## Pre-flight checks (all green)

```
$ python3 algos/oracle_pure/tests/test_components.py     # 8 / 8 PASS
$ python3 algos/oracle_pure/tests/test_fast_copy.py      # 3 / 3 PASS  (22.0× speedup)
$ python3 algos/oracle_pure/tests/test_prior_parity.py   # 3 / 3 PASS  (G3 artifact, kept for follow-up)
$ ls algos/oracle_pure/data/opp_model_priors*.json
  citadel_config_snapshot.json
  opp_model_priors.json                       # OLD content (40 buckets, live)
  opp_model_priors.OLD.json                   # baseline backup
  opp_model_priors.M1_breach_context.json     # G3 rebuild artifact (preserved)
$ grep -c 'deepcopy(base_state)' algos/oracle_pure/oracle_core/search.py     # 0
$ grep -c '_fast_copy_state(base_state)' algos/oracle_pure/oracle_core/search.py # 3
```

`build_opp_model.py` is back at baseline (G3 plumbing reverted before ship —
the only G3 artifact that ships is the inert JSON backup, kept for the
investigation track).

---

## Final Tier A — 10 / 10

| # | P1 | P2 | Winner | oracle_pure | Log |
|---|---|---|---|---|---|
| 1 | oracle_pure | v13_second_ring | 1 | **W** | `01_v13_p1.log` |
| 2 | v13_second_ring | oracle_pure | 2 | **W** | `g7_final_v13_p2.log` |
| 3 | oracle_pure | python-algo | 1 | **W** | `g7_final_pyalgo_p1.log` |
| 4 | python-algo | oracle_pure | 2 | **W** | `g7only_pyalgo_p2.log` |
| 5 | oracle_pure | heuristic_v1 | 1 | **W** | `g7only_heur_p1.log` |
| 6 | heuristic_v1 | oracle_pure | 2 | **W** | `g7only_heur_p2.log` |
| 7 | oracle_pure | python-2l-aet | 1 | **W** | `g7only_lk_p1.log` |
| 8 | python-2l-aet | oracle_pure | 2 | **W** | `g7only_lk_p2.log` |
| 9 | oracle_pure | funnel_INTER | 1 | **W** | `g7_final_funnel_p1.log` |
| 10 | funnel_INTER | oracle_pure | 2 | **W** | `g7only_funnel_p2.log` |

(Logs in `/tmp/oracle_M1_validation/`.)

No `FailedToLoad` events in any log. **Tier A 10 / 10 confirmed.**

---

## Tier B — snorkeldink-v3-1 BREAKTHROUGH (2 / 2)

| Side | Winner | oracle_pure | Log |
|---|---|---|---|
| oracle_pure P1 vs snorkeldink P2 | 1 | **W** ★ | `g7only_snork_p1.log` |
| snorkeldink P1 vs oracle_pure P2 | 2 | **W** ★ | `g7only_snork_p2.log` |

Documented baseline (REPORT.md §5.1): oracle_pure was **0 / 2** vs
snorkeldink-v3-1. **G7 alone converts both sides.** The same matchup with
the rejected G3 prior reverted to 0 / 2 — confirming that G3 was
*destroying* a real performance win that G7 unlocks.

---

## Tier D — telemetry (heuristic_v1 P1, 64 turns)

| Metric | Baseline (deepcopy) | G7 (fast_copy) | Δ |
|---|---|---|---|
| sims_run / turn (avg) | 1604 | 1604 | 0 % |
| wall-clock / turn (avg) | 0.63 s | 0.40 s | **−37 %** |
| wall-clock / turn (max) | 1.19 s | 0.90 s | −24 % |
| `WATCHDOG fired` events | 0 | 0 | — |
| `Exception` events | 0 | 0 | — |

The `≥1.5× sims/turn` assertion in the original M1 prompt is *not* met on
this match because the search is **work-bound, not deadline-bound** — even
at deepcopy speed, it finishes its 1604 sims in 0.63 s, well under the
8 s budget. G7's measured benefit shows up as **wall-clock headroom**
against the 15 s server hard-timeout, not extra sims here. On a
deadline-bound match the 22× per-call speedup (test_fast_copy.py) would
translate directly to more sims; on a work-bound match it translates to
safety margin.

---

## Path-portability test

```
$ cp -r oracle_pure_M1Lite_upload /tmp/oracle_pure_M1Lite_test
$ /tmp/oracle_M1_validation/run_match.sh /tmp/oracle_pure_M1Lite_test \
    algos/v13_second_ring /tmp/portability.log
$ grep -E "Winner|FailedToLoad" /tmp/portability.log
Winner (p1 perspective, 1 = p1 2 = p2): 1
```

Winner = 1 (oracle wins as P1), no `FailedToLoad`. Algo is path-portable.

---

## Live testing instructions (user)

1. Zip the upload folder:
   ```
   ./C1GamesStarterKit-master/scripts/zipalgo_mac \
     oracle_pure_M1Lite_upload \
     oracle_pure_M1Lite_upload.zip
   ```
   Or invoke `/upload-algo` on `oracle_pure_M1Lite_upload/`.

2. Upload at https://terminal.c1games.com → "My Algos" → "Upload an Algo".
   Suggested name: `oracle_pure_M1Lite` (so it slot-tests against the live
   `oracle_pure` rather than replacing it).

3. Watch ≥ 10 ranked matches. Predictions:
   - **No regression** vs prior live algo (G7 only changes copy speed; with
     identical sample order and identical state it picks identical plans).
   - Possible **+ELO bump** from snorkeldink-v3-1 conversion (if that
     opponent plays our ladder level).
   - Possible additional **+small ELO** on long-running matches that
     previously hit the 15 s timeout.

4. Decision criteria:
   - ELO ≥ baseline (oracle_pure ~2150): **SUCCESS**, merge `oracle-pure-M1-Lite`
     to main; the next milestone branches off this.
   - ELO drops > 40 over 10 matches: **REJECT** retroactively, revert by
     re-uploading `oracle_pure_upload/`. The local Tier B breakthrough did
     not generalize; investigate before doing M2.

---

## Files in this commit

- `algos/oracle_pure/oracle_core/search.py` — G7 fast_copy_state
- `algos/oracle_pure/tests/test_fast_copy.py` — parity + 22× perf test
- `algos/oracle_pure/tests/test_prior_parity.py` — G3 parity test (artifact, no-op without G3 active)
- `algos/oracle_pure/data/opp_model_priors.M1_breach_context.json` — G3 rebuild artifact, preserved for the G3 investigation track
- `algos/oracle_pure/data/opp_model_priors.OLD.json` — explicit baseline backup
- `algos/oracle_pure/MILESTONE_M1_RESULTS.md` — original M1 attempt write-up
- `algos/oracle_pure/MILESTONE_M1Lite_SHIPPED.md` — this doc
- `oracle_pure_M1Lite_upload/` — the upload folder (G7 patch + baseline data)

`build_opp_model.py` was modified during the rejected M1 attempt and is
**not** in this commit — it is back at the main-branch baseline. The G3
script is recoverable from `MILESTONE_M1_RESULTS.md` cause-analysis section
or `M1_LITE_SHIP_AND_G3_INVESTIGATE.md` PART 2 if the investigation track
needs it.
