# Athena v3 planner — offense-only smoke test

## Phase 4 task 6 — single match vs python-algo (Citadel starter)

**Result**: Did NOT crash. Match completes at turn 21.
**Winner**: p2 (python-algo). Expected — offense-only has a deliberately
minimal hand-written defense (4 base turrets + 6 walls), so python-algo's
diverse defense + turret army out-trades us before our offense scales.

```
$ /opt/miniconda3/bin/python3.13 \
    C1GamesStarterKit-master/scripts/run_match.py \
    algos/athena_v3_planner_offense_only \
    C1GamesStarterKit-master/python-algo
...
Winner (p1 perspective, 1 = p1 2 = p2): 2
```

Sample debug output from one of our turns:

```
[athena-offense-only] on_game_start; sim_rs=True wall=FF turret=DF \
    scout=PI demo=EI interceptor=SI
...
[athena-offense-only] turn=21 mp=6.2 pick=interceptor_screen \
    EU=4.00 sims=0
```

`sims=0` is expected: Phase 4 ships with `skip_sim=True` because the
gamelib<->sim_rs adapter isn't fully wired yet (uid/HP parity gaps
would crash the Rust simulator on a synthesized state). Phase 5 will
add the full adapter and turn rollouts on. The Phase 4 offense engine
is therefore **heuristic-scored** in this variant — utility is
`mp_cost + 0.5 * #demolishers`, which still picks meaningful templates
(scout_flood, demo_train, interceptor_screen depending on MP).

## What this validates

- ✅ Imports load (vendored `offense/`, `planner/`, `opponent/`, `gamelib/`).
- ✅ Watchdog-wrapped on_turn never crashes.
- ✅ Beam search returns a valid Candidate every turn (incl. hoard).
- ✅ attempt_spawn executes the chosen plan (debug shows `pick=...`).
- ✅ Game runs through turn 21 without algo timeout / engine kick.

## What it does NOT validate (deferred to Phase 5)

- ❌ Win-rate vs ranked baselines (Phase 4 task 7 covers /bestof; result
  is documented as "expect modest" per spec).
- ❌ sim_rs rollout against opponent posterior (Phase 5 wires the
  full gamelib→sim_rs state adapter).
- ❌ Action predictor integration (Phase 5: feed Phase 3 posterior
  into beam_search.opp_actions_top_k).
