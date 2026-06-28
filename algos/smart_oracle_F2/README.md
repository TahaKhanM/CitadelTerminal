# smart_oracle_F2 — the shipped agent (Top 5 of 1,000+)

This is the algorithm that finished **Top 5** on the Citadel Terminal ranked ladder. It is a
**fully search-driven** agent: nothing about the opening, midgame, or endgame is scripted.
Every turn it enumerates candidate plans, samples plausible opponent responses, rolls each
forward through the Rust simulator, and commits to the plan with the highest expected utility.

It runs on the shared engine, not its own copy of it:

- **Search engine** — [`../oracle_pure/`](../oracle_pure) (a.k.a. *M1Lite*): plan enumeration,
  the 427-replay opponent action prior, and the two-phase rollout loop. `oracle_core/` here is
  the deployed snapshot of that engine plus the adaptive layer below.
- **Simulator** — the rollouts call the Rust action-phase sim whose source lives in
  [`../athena/sim_rs/`](../athena/sim_rs) (built via `maturin`, parity-gated in
  [`../athena/sim/SIM_PARITY.md`](../athena/sim/SIM_PARITY.md)). A vendored pure-Python sim
  (`vendored_sim/`) is the portable fallback when the compiled wheel isn't present.

## What makes it `F2` (the adaptive layer)

`smart_oracle_F2` is the third regression-gated step of the shipped adaptive branch, built on
the strongest search baseline (`IS6`):

```
IS6 → smart_oracle_vd → smart_oracle_F → smart_oracle_F2
```

- **Opponent classifier** (`oracle_core/opp_classifier.py`) — over a rolling 10-turn window it
  reads spawn-tile concentration and unit-type diversity: `SINGLE` if concentration ≥ 0.6,
  `MULTI` if ≤ 0.4 with ≥ 3 distinct unit types. It only *gates* the funnel response, so a wrong
  read can't cause a regression.
- **Funnel detector** (`oracle_core/funnel_detector.py`) — tracks where the opponent has been
  breaching; when one zone/tile dominates recent breaches it injects clamp-down defense templates
  at that exact tile into the candidate pool, and the search picks among them on the merits.
- **The three F2 fixes** (diagnosed from 4 live-ladder losses, see
  [`VARIANT_SMART_ORACLE_F2_REPORT.md`](VARIANT_SMART_ORACLE_F2_REPORT.md)):
  1. Lowered `FUNNEL_MIN_BREACHES` 10 → 6 (the detector was firing too late, after 25+ HP was gone).
  2. Taught it a CENTER-zone drill vector it had been blind to.
  3. Added deep-clamp templates at the exact loss tiles.

  Result: **12 wins / 0 losses** on the local benchmark (earlier variants + published finalists + decoded ranked attackers).

## Run it

```bash
# from the repo root, against the starter algo:
./tools/run.sh algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo
# or a quick syntax/runtime check:
./tools/test.sh algos/smart_oracle_F2
```

`gamelib/` is vendored from the starter kit (the engine expects it inside each algo folder).
`algo_strategy.py` is the engine entry point; `oracle_core/` is the brain.
