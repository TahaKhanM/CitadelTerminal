# Citadel Terminal: a Top-5 Tournament AI

I finished top 5 out of more than 1,000 entrants in Correlation One's 2026 Citadel Terminal
competition. The agent is fully search-driven, with no hand-tuned play rules. Every move it makes
is the output of a search loop that simulates thousands of possible futures per turn and to make
that search fast enough I reimplemented the game engine's combat phase from scratch in Rust.

Citadel Terminal is an algorithmic-strategy competition: you write a bot, upload it and it plays
ranked ELO matches against everyone else's. Most entrants wrote hand-tuned rule sets. This repo is
the archived result of going the other way, written up so the work is easy to follow.

There is a one-page summary in [`citadel_terminal_application.pdf`](citadel_terminal_application.pdf).
This README is the engineering tour.

---

## Results

- Top 5 of 1,000+ entrants on the live ranked ELO ladder.
- 12 wins, 0 losses on the final local benchmark, which included my own earlier variants, the
  strongest published finalist algos (Lostkids, Midwest and Georgia Tech) and bots reconstructed
  from real ranked losses.
- Roughly 50,000 lines of code, built solo: a Rust simulator, a Python search loop, an
  opponent-modeling pipeline, replay tooling and benchmark harnesses. The Rust simulator alone is
  about 14,300 lines.

---

## The game

Citadel Terminal is a special-ruleset version of Correlation One's Terminal: a two-player,
simultaneous-turn tower-defense game on a 28x28 diamond grid. You own the bottom half of the board.
Each turn you spend two resources: Structure Points buy walls, turrets and supports, while Mobile
Points (which decay 25% per turn) buy attacking units. Both players commit their moves blind, then a
deterministic action phase resolves all combat. You win by reducing the opponent's 40 HP to zero or by holding more HP at turn 100. The Citadel ruleset changes several unit stats in ways that
reshape strategy. The live-config-verified values are in
[`docs/game/UNITS_REFERENCE.md`](docs/game/UNITS_REFERENCE.md).

---

## The approach: search instead of heuristics

Most submissions were rule sets of the form "if the opponent does X, build Y". That approach has a
ceiling: every opponent you did not anticipate is a hole in your logic.

This agent makes every decision through the same loop. Each turn it:

1. Enumerates candidate plans, meaning combinations of structures to build and units to deploy.
   Up to about 2,500 plans per turn, time-budgeted.
2. Samples plausible opponent responses from a model built from 427 ranked replays.
3. Rolls each plan-and-response pair forward through the fast simulator.
4. Commits to the plan with the highest expected utility.

There is no opening book and no scripted midgame or endgame. The agent rediscovers the right play
from the game rules every match. The search engine lives in
[`algos/oracle_pure/`](algos/oracle_pure) and the shipped agent that wraps it is
[`algos/smart_oracle_F2/`](algos/smart_oracle_F2).

---

## The core engineering problem: a fast, exact simulator

Search only works if you can roll out thousands of futures inside the 15-second turn budget. The
official engine is a Java jar that cannot be embedded and the reference Python simulator ran at
about 336 games per second, roughly 100x too slow to search with.

So I rewrote the engine's entire action phase in Rust
([`algos/athena/sim_rs/`](algos/athena/sim_rs)) and exposed it to the Python search loop as a
compiled extension via PyO3 and maturin. Getting exact parity required decompiling the engine
bytecode to recover the precise per-frame ordering (move, collision, shield, breach, self-destruct,
attack) and the targeting priority rules. That reverse-engineering work is documented in
[`research/engine_decompiled/PATHFINDER_SPEC.md`](research/engine_decompiled/PATHFINDER_SPEC.md)
and [`research/engine_decompiled/GOTCHAS.md`](research/engine_decompiled/GOTCHAS.md).

| Metric | Value | Note |
|---|---|---|
| Single-core throughput | 14,300 games/s | about 43x the 336/s Python reference |
| 10-thread throughput | 75,000 games/s | about 225x |
| Python vs Rust fuzz parity | 13,319 / 13,319 frames | byte-identical, zero divergence |
| Rust unit tests | 42 / 42 passing | |
| Large-scale fuzz | two 1,000,000-config runs (seeds 42 and 17) | 0 failures |
| Parity vs the Java engine | 19 metrics at exactly zero error across 87,677 frames from 23 ranked replays | the remaining 0.30% traces to JVM hash-ordering, verified in the decompiled source |

Every number above traces to [`algos/athena/sim/SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md)
and [`algos/athena/sim/PARITY_REPORTS/`](algos/athena/sim/PARITY_REPORTS).

---

## Reading the opponent in real time

Two small components let the search adapt mid-match without introducing regressions:

- **Opponent classifier**
  ([`opp_classifier.py`](algos/smart_oracle_F2/oracle_core/opp_classifier.py)). Over a rolling
  10-turn window it measures spawn-tile concentration and unit-type diversity to label the opponent
  as single-archetype or multi-archetype. Its only job is to gate the adaptive response. A wrong
  read never makes the agent worse; it just forgoes a specialization.
- **Funnel detector**
  ([`funnel_detector.py`](algos/smart_oracle_F2/oracle_core/funnel_detector.py)). It tracks the
  tiles where the opponent keeps breaching. When one zone dominates recent breaches, it injects
  defense templates targeted at that exact tile into the candidate pool. It does not decide
  anything itself. It only expands the search's options and the search picks on the merits.

This split, where the model only gates and the search decides, is why adaptation added wins
without costing any.

---

## Closing the loop on every loss

Every ranked loss was pulled from the competition's authenticated REST API with a scraper I wrote
(it drives the site's own endpoints using browser cookies), then walked frame by frame to find
which turn the agent fell behind and why the search preferred its chosen plan over the
alternatives. Each fix was replayed against that same match locally before shipping. This produced
three documented variants, each gated on keeping every prior win:

| Variant | What it fixed |
|---|---|
| `smart_oracle_vd` | First adaptive defense. Added the funnel detector and flank-corridor templates against opponents that repeatedly attacked one side. |
| `smart_oracle_F` | Merged the funnel response onto the strongest search baseline (`IS6`) and gated it behind the classifier, so specialized defense only fires when the opponent is provably vulnerable to it. No regression on prior wins. |
| `smart_oracle_F2` | The final ship. Replay analysis of four live losses showed the detector fired too late. Lowered the trigger threshold, taught it a center-drill attack vector it had been blind to and added templates at the exact tiles where the losses happened. 12-0 on the local benchmark. |

The full per-loss diagnosis is in
[`algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md`](algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md).
The broader iteration story (the V, IS, J, K and M2 variant experiments) is in
[`docs/devlog/`](docs/devlog).

---

## One turn, end to end

```
game state
    |
    v
enumerate candidate plans (defenses x offenses, up to ~2,500, time-budgeted)
    |
    v
sample K opponent responses        <-- prior built from 427 ranked replays
    |
    v
inject funnel-defense templates    <-- only if the classifier reads single-archetype
    |
    v
roll out every plan/response pair  <-- sim_rs (Rust), two phases: cheap cull, then re-rank
    |
    v
commit the plan with the highest expected utility
```

---

## Repo map

| Path | What's there |
|---|---|
| [`algos/smart_oracle_F2/`](algos/smart_oracle_F2) | the shipped Top-5 agent (search engine plus adaptive layer) |
| [`algos/oracle_pure/`](algos/oracle_pure) | the search engine: plan enumeration, opponent prior, rollout loop and [`REPORT.md`](algos/oracle_pure/REPORT.md) |
| [`algos/athena/sim_rs/`](algos/athena/sim_rs) | the Rust action-phase simulator (PyO3 extension) |
| [`algos/athena/sim/`](algos/athena/sim) | the Python simulator, [`SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md) and the parity reports |
| [`algos/baselines/`](algos/baselines) | earlier heuristic algos (v13, heuristic_v1, heuristic_v2), the bots the search had to beat |
| [`tools/`](tools) | eval and replay pipeline: `bestof.py` (Wilson CI), `tournament.py`, `scrape_ranked_replays.py` and more |
| [`docs/game/`](docs/game), [`docs/workflow/`](docs/workflow), [`docs/devlog/`](docs/devlog) | distilled game reference, how I iterated and the iteration story |
| [`research/`](research) | decompiled-engine specs, opponent clustering, audits and competitive analysis |
| `.agents/skills/` | 13 custom Claude Code skills built for the workflow (`/run-match`, `/bestof`, `/tournament`, `/analyze-replay` and others) |
| [`data/`](data) | leaderboard snapshots and pairwise match analyses |

---

## Run it locally

Requires Python 3 and Java 10+ (for the engine in `C1GamesStarterKit-master/`).

```bash
# play the shipped agent against the starter algo (replays land in ./replays/)
./tools/run.sh algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo

# fast syntax and runtime sanity check of an algo
./tools/test.sh algos/smart_oracle_F2

# head-to-head over 2N games with a Wilson 95% CI on the win rate
python3 tools/bestof.py algos/smart_oracle_F2 algos/baselines/v13_second_ring 10
```

The agent runs out of the box on its vendored Python simulator. For the native-speed Rust
simulator, build the wheel once with `cd algos/athena/sim_rs && maturin develop --release`. See
[`docs/workflow/LOCAL_TESTING.md`](docs/workflow/LOCAL_TESTING.md).

---

## Verification

Every claim in this README traces to a file in this repo.

| Claim | Source |
|---|---|
| Simulator throughput, 42/42 tests, 13,319/13,319 fuzz, 1M-config fuzz runs, Java-engine parity | [`algos/athena/sim/SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md), [`PARITY_REPORTS/`](algos/athena/sim/PARITY_REPORTS) |
| The three loss-driven variants and the 12-0 benchmark | [`VARIANT_SMART_ORACLE_F2_REPORT.md`](algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md), [`docs/devlog/`](docs/devlog) |
| Top-5 finish and the one-page summary | [`citadel_terminal_application.pdf`](citadel_terminal_application.pdf) |
| Live-config-verified unit math | [`docs/game/UNITS_REFERENCE.md`](docs/game/UNITS_REFERENCE.md) |

Two scope notes, for accuracy. The 427 replays train the opponent action prior that the search
samples from, not the archetype classifier, which is a separate and deliberately simple component.
The 13,319/13,319 fuzz result compares the Python and Rust simulators byte for byte; the comparison
against the Java engine itself is the separate 19-metric zero-error gate over 87,677 ranked frames.
