# Athena v3 — READY FOR UPLOAD

This algo is packaged and ready for human review + manual upload to
terminal.c1games.com. **Do not auto-upload — that is a user-only action.**

---

## TL;DR

| | |
|---|---|
| Status | BUILD COMPLETE (Phases 0 → 8) |
| Package | `algos/athena_v3_planner/athena_v3.zip` |
| Size | **129,681 bytes (≈ 127 KB)** — well under the 5 MB limit |
| Headline | G11 +36.2 pp delta vs v13 (Athena 39/47 LB 0.699 vs v13 22/47 LB 0.333) |
| Final report | `algos/athena_v3_planner/FINAL_REPORT.md` |

---

## Pre-flight checklist

- [x] **Final smoke match passed** — Athena vs v13_second_ring, 100
      turns, no crash, no timeout, ~63 ms/turn average vs 13 s
      watchdog limit. Replay at
      `replays/phase8_smoke/athena_v3_vs_v13_second_ring.replay`.
      Detailed report: `data/PHASE8_SMOKE.md`.
- [x] **`.zipignore` configured** — excludes tests/, eval/, sim/,
      build documentation, Phase ledgers, replay index. Includes all
      runtime code + data.
- [x] **Zip built and verified < 5 MB** — 127 KB actual size. 62
      files. Built via
      `C1GamesStarterKit-master/scripts/zipalgo_mac`. Detailed report:
      `data/UPLOAD_PACKAGE.md`.
- [x] **Sanity tests** — extract to tmp, import `algo_strategy.py`,
      `test_algo_mac` exit 0 (V4 in `data/UPLOAD_PACKAGE.md`).
- [x] **FINAL_REPORT.md** — composite of all 8 phases at
      `FINAL_REPORT.md`.
- [x] **Memory snapshot** — user auto-memory updated. Marker at
      `data/PHASE8_MEMORY_SNAPSHOT.md`.

---

## Where to upload

1. Open https://terminal.c1games.com.
2. Sign in (team WICK, comp_id 1338 — Citadel competition).
3. Navigate **My Algos** → **Upload an Algo**.
4. Select `algos/athena_v3_planner/athena_v3.zip`.
5. The server will:
   - Compile the algo.
   - Run a syntax / boot smoke test.
   - Auto-queue ranked matches.
6. The algo will appear under **My Algos** alongside `v13` (the
   current leader baseline). It will also be available on the
   **Playground** for ad-hoc matches.

---

## What to expect immediately

- **Compile + boot test**: 10-60 s. If it fails, the upload UI will
  surface the Python traceback. Common causes: missing module path,
  the `gamelib/` import. Should not happen — the local smoke match
  ran 100 turns clean.
- **First ranked match**: queued within 1-2 minutes typically. Look
  for it under `/api/game/algo/<new_id>/matches`.
- **Rank trajectory**: ELO updates after each ranked match. Expect
  noisy first 10-20 games.

---

## Watch-for items during the first 24 h of ranked play

These are the known weaknesses that may surface live:

### 1. Classifier mis-fires (BALANCED collapse)

The Phase 3 archetype classifier has LOO-CV 0.489 — 51 % of
held-out folds collapse to the BALANCED catch-all class. When this
fires:

- The action_predictor returns the BALANCED archetype's empirical
  action distribution (close to a uniform prior).
- Beam search's expected-utility ranking becomes noisy.
- Athena may default to `hoard` template too aggressively (see § 3
  below).

**Symptom in arbiter log**: `opp_arch=BALANCED` for many turns
straight. **Mitigation**: Phase 8B re-fit on self-play augmented
corpus (deferred).

### 2. MAP-Elites archive disabled by default

`archive_confidence_threshold=1.01` — the archive is loaded but
the gate never fires. Phase 6B showed both always-on and Path-C
gate=0.6 regressed Lostkids' floor. The archive will not contribute
candidates at runtime.

**Symptom**: no `[arbiter] ... source=map_elites_archive` log
lines. **Mitigation**: Phase 8B classifier fix → Phase 6C re-tune
(deferred). To re-enable manually for experimentation, edit
`algo_strategy.py` to pass `archive_confidence_threshold=0.6` (or
0.0 for always-on) into `EconomyArbiter`.

### 3. Long hoard-heavy match regressions

Three of the 47 G11 outcomes flipped v13 → Athena loss. All three
share a 24-25 turn hoard-heavy opponent profile (`new-strat-algo`,
`python-algo`, `python-algo-v5`). Pick distribution shows Athena
reaching for `hoard` 14-16× per match.

**Specific opponents to watch on the live ladder**:
- `new-strat-algo` (ELO 1576 in the G11 corpus)
- `python-algo-v5` (ELO 1468)
- `python-algo` (any variant; G11 corpus has 4 distinct python-algo
  variants — Athena went 3-1 vs the `python-algo` group, the loss
  was a long match)

If Athena loses to one of these and plays many `hoard` turns,
that's the regression cluster. Flag for the human reviewer.

### 4. Side asymmetry (unverified)

Phase 6B no-gate vs Lostkids was 10/10 P1=athena and 4/10
P1=lostkids — substantial spawn-side asymmetry. We did not split
G11 by side. If the live ladder shows materially different rates
between left-deploy and right-deploy games, the template library
or defense priorities may be biased.

**Recommended check**: after 50 ranked games, pull match results
via `/api/game/algo/<id>/matches` and split by spawn side.

---

## Recommended monitoring

### First 24 h

1. **ELO rank trajectory** — chart ELO every 4 h. Athena should
   start at 1500 (default) and climb. v13's last known live rate
   was ~47 % (G11 baseline); Athena's predicted ceiling is 83 %.
   Realistic expectation: somewhere in between, likely 55-70 %.
2. **Crash / timeout rate** — Citadel exposes `crashed` and
   `timeout_death` per match. Both should be 0. If non-zero,
   pull the replay and check the safe-fallback path; production
   wrappers should catch all known crash modes.
3. **Per-opponent W/L** — once ≥ 5 ranked matches accrue, group
   by opponent name. Cross-reference against G11 per-opponent
   table at `data/G11_PER_OPPONENT.md`.

### After 24 h

If Athena is performing within expectations (ELO up, no crashes):
proceed with Phase 8B (classifier re-fit on self-play augmented
corpus) — see `FINAL_REPORT.md` § 7.1.

If Athena underperforms (ELO flat or down, regression cluster
opponents winning frequently): pull replays of those losses,
diagnose against the per-opponent regression analysis at
`data/G11_PER_OPPONENT.md`, and either roll back to v13 or apply
the targeted fixes (hoard-counter template § 7.5; classifier
temperature scaling § 7.1).

---

## Performance bounds

- **Floor (75 %)**: Phase 5B local bestof. Includes the v13
  local-determinism artifact (10/20 v13, 20/20 Lostkids, 30/40 total).
- **Ceiling (83 %)**: G11 counter-factual. Achieved if classifier
  picks correct archetype on first contact and opponents play
  exactly their recorded ranked-match patterns.
- **Best estimate live ladder**: 55-70 %. Real opponents will
  adapt; classifier mis-fires (§ 1 above) will compound.

This is the algo as built. Honest expected uplift over v13 is
+5 pp to +20 pp on the live ladder, with significant variance in
the first 50 games due to small-sample noise.

---

## If you choose NOT to upload

The current shipping candidate is a real improvement over v13 by
the most rigorous available measurement (G11 +36.2 pp). The known
weaknesses (§ Watch-for items) are documented; none are blockers.

If the human reviewer wants more pre-upload validation, the
recommended path is **Phase 8B first**:
1. Generate 100 self-play replays.
2. Re-fit classifier; target LOO-CV ≥ 0.70.
3. Re-run G11; expect a smaller gain than +36.2 pp once the
   classifier is no longer overfit to v13's opponent set.

If the human reviewer is OK with the current state, **just upload**.
The build is complete.

---

*Phase 8 closed by agent #12. Athena v3 build ledger ends here.*
