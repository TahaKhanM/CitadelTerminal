# Citadel Terminal

A tournament game agent that combines Python search with a Rust combat simulator for [Correlation One's Terminal](https://terminal.c1games.com/). It compares attack and defence plans against representative opponent responses within an 11-second search budget.

The simulator is the core of the project. Fast rollouts let the agent compare more plans, but movement, targeting and floating-point behaviour must still agree with the Java game engine. The Rust implementation exposes combat simulation to Python through PyO3.

## Search and simulation

1. Build candidates from defence templates, mobile-unit counts and legal launch positions. The final variant considers up to 2,500 plans.
2. Select opponent scenarios using a replay-derived prior and observations from completed turns.
3. Run a cheap first pass, then compare the shortlist against up to six scenarios. Use remaining time for selected second-turn rollouts.
4. Score health, surviving structures, resources and breaches. Penalise wasted attacks and inconsistent results across scenarios.

The candidate templates make search practical under the turn limit. They also constrain what it can discover. Opponent adaptation changes both templates and scenario selection, so it needs evaluation against unseen opponents.

The Rust simulator uses float32 arithmetic to match rounding-sensitive Java behaviour. Its fast path skips detailed replay construction; an instrumented path exposes intermediate state for comparison. The Python reference provides a portable fallback.

Historical measurements record roughly **14,300 action-phase simulations per second on one core** and **75,000 with ten threads** on a midgame fixture. These measure a specific fixture rather than complete games. [Simulator notes](algos/athena/sim/SIM_PARITY.md) record the conditions and optimisation attempts.

## Results

| Check | Recorded result |
| --- | --- |
| Java replay comparison | Historical reports cover 87,677 frames with exact agreement in 19 columns and a documented cascade tolerance in four others. The ranked replay corpus is absent from the public checkout. |
| Native and synthetic checks | The 8 September 2026 review records 42 Rust tests, 10 evaluation tests, five regressions per search variant and 240 seeded dual-mode cases passing. |
| F2 against the bundled starter | Two wins with both sides tested and no engine errors. Final F2 health was 33 and 35. [Run record](docs/verification/2026-09-08.json). |
| Competition standing | The [reconstructed leaderboard](data/CITADEL_LEADERBOARD.md) places team Wick fifth by peak rating among observed teams before the deadline on 26 April 2026. An official final placing is not established. |

The starter comparison checks that the agent completes games. The development prior uses 427 ranked replays, so repeated evaluation on that pool does not establish tournament strength on unseen opponents. Earlier blanket win-rate and final-placement claims exceed the retained evidence.

## Run it

Use Python 3.11+ and NumPy for the portable simulator. Full matches also need
Java 10+; native compilation additionally needs Rust and maturin. macOS and
Linux/WSL are the maintained local platforms.

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -r requirements.txt

# One initial-turn protocol check; rejects turn fallback and malformed output.
./tools/test.sh algos/smart_oracle_F2

# One full game, isolated config and replay output.
./tools/run.sh algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo

# One game per side. Increase N for a larger, explicitly chosen evaluation.
python tools/bestof.py algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo 1 --serial
```

Set `PYTHON_CMD=/path/to/python` when the engine should launch a particular
interpreter. Every match now gets its own configuration copy and replay folder;
`summary.json` preserves outcomes and failure diagnostics. Incomplete engine
runs are errors, excluded from measured win-rate denominators and make the
command exit nonzero. The printed Wilson interval is nominal: repeatedly
running deterministic opponents/seeds does not produce independent evidence.

For native simulation, from the activated environment:

```bash
python -m pip install 'maturin>=1.4,<2'
cd algos/athena/sim_rs
maturin develop --release
cargo test --locked --lib --tests
```

[Local testing details](docs/workflow/LOCAL_TESTING.md) cover the maintained
verification commands. CI exercises the public, self-contained checks; missing
ranked replay tests are reported as skipped, not as successful zero-case parity.

## Where to read next

| Path | Purpose |
|---|---|
| [`algos/smart_oracle_F2/`](algos/smart_oracle_F2/) | Final competition family, now with dated correctness fixes |
| [`algos/oracle_pure/`](algos/oracle_pure/) | Earlier search baseline and component tests |
| [`algos/athena/sim_rs/`](algos/athena/sim_rs/) | Rust state, pathfinding, combat systems and PyO3 bindings |
| [`algos/athena/sim/`](algos/athena/sim/) | Python reference, synthetic fuzzer and historical parity tooling |
| [`tools/match_runner.py`](tools/match_runner.py) | Shared isolated engine runner and validated outcome parsing |
| [`docs/devlog/`](docs/devlog/) | Competition-era experiments, including rejected variants |
| [`research/`](research/) | Opponent analyses and engine-behavior notes |

## Credits and status

Team **Wick** is recorded as **TAHA and Rham**. Muhammad Taha's commits cover the agent, simulator integration, search and replay workflow.

The engine and starter code come from [Correlation One's starter kit](https://github.com/correlation-one/C1GamesStarterKit). Its [licence](C1GamesStarterKit-master/License.md), including the noncommercial restriction, remains applicable. Other bots are retained as evaluation baselines.

The current branch includes later correctness fixes. The [competition revision](https://github.com/TahaKhanM/CitadelTerminal/tree/ce2142efe5c516f759410b3178bfe30907224377) preserves the submitted-era code. The next evaluation needs a fixed holdout pool and a distributable Java replay corpus. [Engineering review](docs/ENGINEERING_REVIEW.md).
