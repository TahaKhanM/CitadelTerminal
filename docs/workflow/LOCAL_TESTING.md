# Local testing

Run from the repository root. Python 3.11+ and NumPy run the portable agent;
full games additionally need Java 10+. Rust and maturin are optional for native
simulation. macOS and Linux/WSL are the maintained platforms.

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -r requirements.txt
```

## Public checks

```bash
python -m unittest discover -s tests -p test_evaluation.py -v
python tests/test_strategy_regressions.py -v
ALGO_UNDER_TEST=oracle_pure python tests/test_strategy_regressions.py -v
python -m algos.athena.sim.tests.test_float32_propagation
python -m algos.athena.sim.fuzz --n 240 --seed 42
./tools/test.sh algos/smart_oracle_F2
```

Variant tests run in separate processes because each competition upload vendors
modules with the same names. The portable smoke checker sends a synthetic first
turn, validates the two JSON command arrays, and rejects watchdog/exception
fallback. It does not depend on the old platform-specific test binary or a
missing sample replay. It also does not replace a full match.

For Rust source tests and the optional native extension:

```bash
cd algos/athena/sim_rs
cargo test --locked --lib --tests
python -m pip install 'maturin>=1.4,<2'
maturin develop --release
```

## Full games and comparison

```bash
./tools/run.sh algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo
python tools/bestof.py algos/smart_oracle_F2 algos/baselines/v13_second_ring 1 --serial
python tools/tournament.py algos/smart_oracle_F2 algos/oracle_pure algos/baselines/v13_second_ring --workers=2
```

Use `PYTHON_CMD=/path/to/python` if the bots should use a specific interpreter.
A search turn may use up to eleven seconds; serial full games can take several
minutes. Concurrency changes wall-time pressure and can change how much search
completes, so compare under a fixed worker count and similar machine load.

Every game uses a private config and working directory. Outputs go into a fresh
folder below `replays/`, with summaries and diagnostics. The authoritative local
config is `configs/competition-game-configs.json`, checked against the raw
server fields in the retained simulator snapshot. The shared runner does not
mutate source configuration at import time or reuse unrelated replay files.

Errors, missing/partial terminal frames and recorded bot failures make the
command fail. Inspect them before interpreting any win rate. Wilson intervals
are nominal and do not account for repeated deterministic fixtures, opponent
selection or tuning on the same pool.

## Historical replay validation

```bash
python -m algos.athena.sim.tests.test_mode_parity
python algos/athena/sim/regression_runner.py --scope quick
```

Ranked replay checks report skipped coverage if their corpus is absent. The
quick regression runner also runs synthetic dual-mode cases, and writes a dated
report/timing row. The heavy historical validator requires the original replay
corpus; a source build plus synthetic fuzzing does not reproduce the retained
87,677-frame Java comparison. See the
[engineering review](../ENGINEERING_REVIEW.md) for exactly what was rerun.
