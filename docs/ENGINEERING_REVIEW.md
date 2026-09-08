# Engineering review — 8 September 2026

The competition code is preserved at
[`ce2142e`](https://github.com/TahaKhanM/CitadelTerminal/tree/ce2142efe5c516f759410b3178bfe30907224377).
Changes described here happened afterwards. Historical results belong to their
original revision and configuration, not automatically to the revised agent.

## What the implementation actually does

The strongest part of the project is the connection between a simulator,
constrained plan search and replay-based diagnosis. `algo_strategy.py` wraps
protocol handling and a watchdog; `enumerator.py` proposes structures and
mobile deployments; `opponent_model.py` selects representative action scenarios;
`search.py` culls, reranks and sometimes extends candidates by another turn.
`sim_eval.py` adapts state to native or portable simulation, and `value.py`
encodes the objective. The Rust crate contains the state, geometry, pathfinding,
spatial lookup, frame loop and combat systems.

The design reduces the cost of testing thousands of alternatives, but the
candidate space and objective are hand-designed. A top-weight scenario set with
uniform score averaging is not an unbiased expected-value estimate under the
opponent's true distribution. The cheap first pass can also prune a plan that
would perform well against another response. These are deliberate compute
tradeoffs to discuss, not evidence that the agent is free of heuristics.

## Correctness changes

| Finding | Change and why it matters |
|---|---|
| Online observations were fed only when `turnInfo[0] == 2` inside `on_action_frame`. `AlgoCore` forwards only phase 1 there; phase 2 ends the game. | Flush the completed observation on the next deploy callback, before resetting it. Keep the preceding deploy state's resource buckets and an idempotent turn guard. Include no-offense observations instead of discarding them. |
| A structure scheduled for removal halved the cumulative value of all structures visited so far. | Discount only that structure's contribution. Reordering the structure list must not change evaluation. |
| The second-turn approximation increased MP one turn late at ramp boundaries. | Use the retained engine's ramp-start and interval parameters. Tests cover the 3→4 and 8→9 turn transitions. Other aspects of the continuation policy remain approximations. |
| A portable simulation failure could return the input unchanged, presenting a failed rollout as a valid no-damage outcome. | Propagate simulation failure. No successfully evaluated candidate triggers the existing turn fallback rather than manufacturing a score. |
| A fresh environment imported the `sim_rs/` source directory as a namespace package, then failed every rollout because no native entry point existed. | Check that the imported backend exposes the required callable; otherwise use the portable simulator. Verified in an isolated NumPy environment without the extension. |
| An unreachable weighted-random branch suggested posterior sampling, although the preceding bound always selected top-weight modes. | Remove the dead branch and document representative scenario selection accurately. |
| The local Java config charged 2 SP for a support upgrade while the simulator's raw server fields inherited 4. | Remove the stale override and legacy resource key. A test compares local `unitInformation`/`resources` with the retained raw snapshot. |

These fix demonstrable semantics; they do not establish a higher tournament win
rate. The callback change in particular can alter the opponent model and thus
strategy choices. A held-out matchup study is still required before claiming a
performance improvement over the competition revision.

## Evaluation and reproducibility

The shared `tools/match_runner.py` now owns an isolated directory/config for each
Java process. It requires a successful engine exit and terminal frame, reads
`endStats.winner`, rejects recorded bot crashes/timeouts, and preserves diagnostic
tails plus the replay. Timed-out Java/bot process groups are terminated together.
The same worker backs single games, best-of, round-robin and pool evaluation.
Single games no longer collect and overwrite unrelated earlier replays.

Failed games are not silently counted as losses. Rates use completed games;
errors stay visible and make the command fail. That conditional rate alone is
not an agent's operational performance: an incomplete benchmark must be repaired
before comparing candidates. Ties are included as non-wins, reported separately,
and never translated into wins for the other player.

Wilson intervals assume Bernoulli observations from independent draws. Side
swapping tests an important asymmetry, but replaying a deterministic opponent
with the same seed does not supply new independent draws. Report opponent/seed
coverage and reserve an untouched evaluation set before tuning. A strong next
benchmark would freeze opponents and configurations, pair sides/seeds, report
crashes and ties, and show per-opponent uncertainty rather than one selected
headline percentage.

The public checkout lacks the ranked replay corpus used in the historical
parity reports. Previously `test_mode_parity.py` printed PASS after zero turns,
and the dtype script printed a missing fixture as both SKIP and PASS. They now
state the missing coverage. The quick regression path always includes 120
seeded synthetic positions across the fuzzer's twelve categories.

## Verification performed

- `cargo test --locked --lib --tests`: **42 passed** (Rust crate, source rebuilt).
- `python3 -m unittest discover -s tests -p test_evaluation.py -v`: **10 passed**.
- `python3 tests/test_strategy_regressions.py -v`: **5 passed** for F2; the same
  command with `ALGO_UNDER_TEST=oracle_pure` passed **5** for that variant.
- Python float32 helpers/config checks: **3 passed, 1 ranked-fixture check skipped**.
- `python3 -m algos.athena.sim.fuzz --n 240 --seed 42`: **240 passed**, twenty per
  category, comparing fast and instrumented final states.
- The existing `oracle_pure/tests/test_components.py` suite passed its eight
  enumerator, feasibility, value, prior, plan and end-to-end search checks.
- `./tools/test.sh algos/smart_oracle_F2`: valid two-array deploy protocol without
  watchdog/exception fallback.
- Two starter-vs-v13 engine games and two F2-vs-starter games completed; all four
  stored terminal records passed the revised outcome parser. F2 won both sides
  against the starter. A repeat with a freshly built native wheel from
  `4479ca8` left F2 at 33 and 35 HP; [machine-readable evidence](verification/2026-09-08.json)
  records the engine/config hashes and terminal statistics. This is a functional smoke test.

Local execution used the existing NumPy environment and Java 25 on macOS. Rust
source tests rebuilt without relying on a previously installed Python extension;
initial full agent games used an available native extension; the final recorded
comparison rebuilt and used a wheel from this checkout. An isolated Python+NumPy
environment without the native module also passed both variants' regressions,
the F2 first-turn smoke check and 240 synthetic cases. CI exercises that portable
path on future changes. The old large
Java-corpus parity and million-case fuzz claims were not rerun.

## Attribution and remaining weaknesses

Git records Taha's implementation work, while the roster records Wick as TAHA
and Rham. The starter game, library and community bots are upstream/reference
material; their license remains intact. The retained history identifies project changes and upstream contributions.

The retained leaderboard is reconstructed before the deadline. A final official
standing and the previous 1,000+ entrant denominator require separate evidence.
The F2 report also contains inconsistent labels for a small local benchmark,
including equal-HP self-play counted under a wins heading; it is not a reliable
basis for a 12–0 final-agent claim.

Remaining engineering limits include duplicated variant modules, a heuristic
second-turn policy, candidate-order/time-budget sensitivity, platform-dependent
native packaging and an incomplete distributable Java parity corpus. Keeping
historical variants separately runnable preserves comparison provenance, but
future work would benefit from an explicit versioned engine API and frozen
fixtures. Large-scale refactoring before securing those fixtures would make
regression attribution harder.

The public Linux workflow also passed both jobs at `4479ca8`: [CI run](https://github.com/TahaKhanM/CitadelTerminal/actions/runs/34173882101).
