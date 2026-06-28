# Building a Top-5 Tournament AI — Citadel Terminal (Correlation One, 2026)

**Top 5 of 1,000+.** A fully search-driven game-playing agent — *no hand-tuned heuristics* —
running on a custom Rust simulator I built from scratch.

> Citadel Terminal was an algorithmic-strategy competition: you write a bot, upload it, and it
> plays **ranked ELO matches** against everyone else's. Most entrants hand-tuned rule sets.
> I built a search engine instead — and to make search fast enough, I had to rebuild the game
> engine in Rust. This repo is the archived result, written up for anyone curious how it works.

The polished one-page writeup is in [`citadel_terminal_application.pdf`](citadel_terminal_application.pdf).
This README is the engineering tour.

---

## The result

- 🏆 **Top 5 of 1,000+ applicants** on the live ranked ELO ladder.
- ✅ **12 wins, 0 losses** on the local benchmark — against my own earlier variants, the strongest
  *published* finalist algos (Lostkids / Midwest / Georgia Tech), and bots decoded from real ranked losses.
- 🧱 **~50,000 lines** end-to-end and solo: a Rust simulator, a Python search loop, an opponent-modeling
  pipeline, replay tooling, and benchmark harnesses (Rust alone ≈ 14,300 LOC).

---

## What it is

Citadel Terminal is a special-ruleset version of Correlation One's *Terminal*: a two-player,
**simultaneous-turn tower-defense** game on a **28×28 diamond grid**. You own the bottom half
(y < 14). Each turn you spend two resources — **Structure Points (SP)** on walls/turrets/supports
and **Mobile Points (MP**, which decay 25%/turn) on scouts/demolishers/interceptors — then a
deterministic **action phase** resolves all combat. You win by dropping the opponent's 40 HP to 0,
or by holding more HP at turn 100. The "Citadel" ruleset differs from base Terminal in ways that
reshape strategy (wall upgrades cost 2 SP; base Support HP is 1 but *upgraded* is 40; upgraded
turrets are unusually strong) — the live-config-verified values live in
[`docs/game/UNITS_REFERENCE.md`](docs/game/UNITS_REFERENCE.md).

---

## The approach: search, not heuristics

Most submissions were hand-written rule sets: *"if the enemy does X, build Y."* That ceilings out —
every opponent you didn't anticipate is a hole.

This agent makes **every decision the output of a search loop.** Each turn it:

1. **Enumerates** candidate plans — combinations of structures to build and units to deploy
   (**up to ~2,500 plans/turn**, time-budgeted).
2. **Samples** plausible opponent responses from a model trained on **427 ranked replays**.
3. **Rolls** each (my plan × sampled response) forward through the fast simulator.
4. **Commits** to the plan with the highest expected utility.

No opening book, no scripted midgame or endgame. The whole game is rediscovered from first
principles, every match. The engine that does this lives in [`algos/oracle_pure/`](algos/oracle_pure)
("M1Lite"); the shipped agent that wraps it is [`algos/smart_oracle_F2/`](algos/smart_oracle_F2).

---

## Headline engineering win: the Rust simulator

Search only works if you can roll out thousands of futures inside the **15-second turn budget.**
The official engine is a Java `.jar` that can't be embedded, and the reference Python simulator ran at
~**336 games/s** — about 100× too slow to search with.

So I reimplemented the engine's entire **action phase from scratch in Rust**
([`algos/athena/sim_rs/`](algos/athena/sim_rs)) and exposed it to the Python search loop as a
**PyO3 / maturin compiled extension.** Reaching exact parity meant **decompiling the engine
bytecode** to recover the precise per-frame ordering (move → collision → shield → breach →
self-destruct → attack) and targeting priority — captured in
[`research/engine_decompiled/PATHFINDER_SPEC.md`](research/engine_decompiled/PATHFINDER_SPEC.md)
and [`research/engine_decompiled/GOTCHAS.md`](research/engine_decompiled/GOTCHAS.md).

| Metric | Value | Note |
|---|---|---|
| Single-core throughput | **14,300 games/s** | ~43× the 336/s Python reference |
| 10-thread throughput | **75,000 games/s** | ~225× |
| Python↔Rust fuzz parity | **13,319 / 13,319** frames | **byte-identical**, zero divergence |
| Rust unit tests | **42 / 42** passing | |
| Large-scale fuzz | **2× 1,000,000**-config runs (seeds 42 & 17) | 0 failures |
| vs. the Java engine | **19 columns @ exactly 0 error** across 87,677 frames / 23 ranked replays | the remaining 0.30% is provably JVM hash-ordering |

Every number above traces to [`algos/athena/sim/SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md)
and [`algos/athena/sim/PARITY_REPORTS/`](algos/athena/sim/PARITY_REPORTS).

---

## Reading the opponent in real time

Two lightweight components let the search adapt mid-match without ever regressing:

- **Opponent classifier** ([`opp_classifier.py`](algos/smart_oracle_F2/oracle_core/opp_classifier.py)) —
  over a rolling **10-turn window** it computes spawn-tile concentration and unit-type diversity to
  label the opponent `single_archetype` (concentration ≥ 0.6) or `multi_archetype` (≤ 0.4 with ≥ 3
  unit types). Its *only* job is to **gate** the adaptive response — so a wrong read can never make
  the agent worse, only forgo a specialization.
- **Funnel detector** ([`funnel_detector.py`](algos/smart_oracle_F2/oracle_core/funnel_detector.py)) —
  tracks the tiles where the opponent keeps breaching. When one zone dominates recent breaches, it
  **injects clamp-down defense templates at that exact tile** into the candidate pool. It doesn't
  *decide* anything — it just expands the search's options, and the search picks on the merits.

This "model only gates, search decides" split is why adaptation added wins without ever costing one.

---

## Closing the loop on every loss

Every ranked loss was pulled from the competition's **authenticated REST API** (a custom
browser-cookie–driven scraper), walked frame-by-frame to find *which* turn the agent fell behind
and *why* the search ranked its chosen plan above the alternative, then the fix was replayed against
that same match locally before shipping. That produced three documented, regression-gated variants:

| Variant | What it fixed |
|---|---|
| `smart_oracle_vd` | First adaptive defense: added the funnel detector + flank-corridor templates against opponents who repeatedly attacked one side. |
| `smart_oracle_F` | Merged the funnel response onto the strongest search baseline (`IS6`) and **gated it behind the classifier**, so specialized defense only fires when the opponent is provably vulnerable — no regression on prior wins. |
| **`smart_oracle_F2`** ★ | **The final ship.** Replay analysis of 4 live losses showed the detector fired too late: lowered the trigger (`MIN_BREACHES` 10 → 6), taught it a center-drill vector it had been blind to, and added deep-clamp templates at the exact loss tiles. **12–0** on the local benchmark. |

The full diagnosis is in
[`algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md`](algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md);
the broader iteration story (the V / IS / J / K / M2 search-variant fan-out) is in
[`docs/devlog/`](docs/devlog).

---

## Architecture — one turn

```
                       ┌──────────────────────────────────────────────┐
   game state  ─────▶  │ enumerate candidate plans  (up to ~2,500)     │
                       │   defenses × offenses, time-budgeted          │
                       └──────────────────────┬───────────────────────┘
                                              │
   427-replay prior ───▶  sample K plausible opponent responses
                                              │
                       ┌──────────────────────▼───────────────────────┐
   classifier ─gate─▶  │ funnel templates injected (if opp is SINGLE)  │
                       └──────────────────────┬───────────────────────┘
                                              │
                       ┌──────────────────────▼───────────────────────┐
   sim_rs (Rust) ───▶  │ 2-phase rollout: cull all → re-rank top-30 ×6 │
                       └──────────────────────┬───────────────────────┘
                                              │
                                   rank by expected utility
                                              │
                                     ▼  commit best plan
```

---

## Repo map

| Path | What's there |
|---|---|
| [`algos/smart_oracle_F2/`](algos/smart_oracle_F2) | ★ the shipped Top-5 agent (search engine + adaptive layer) |
| [`algos/oracle_pure/`](algos/oracle_pure) | the search engine ("M1Lite"): plan enumeration, opponent prior, rollout loop + [`REPORT.md`](algos/oracle_pure/REPORT.md) |
| [`algos/athena/sim_rs/`](algos/athena/sim_rs) | the **Rust** action-phase simulator (PyO3 extension) |
| [`algos/athena/sim/`](algos/athena/sim) | the Python sim + [`SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md) + parity reports |
| [`algos/baselines/`](algos/baselines) | earlier heuristic algos (v13, heuristic_v1/v2) — the bots search had to beat |
| [`tools/`](tools) | eval + replay pipeline: `bestof.py` (Wilson-CI), `tournament.py`, `scrape_ranked_replays.py`, … |
| [`docs/game/`](docs/game) · [`docs/workflow/`](docs/workflow) · [`docs/devlog/`](docs/devlog) | distilled game reference · how I iterated · the iteration story |
| [`research/`](research) | decompiled-engine specs, opponent clustering, audits, competitive analysis |
| [`.agents/skills/`](.agents/skills) | **13 custom Claude Code skills** I built for the workflow (`/run-match`, `/bestof`, `/tournament`, `/analyze-replay`, …) |
| [`data/`](data) | leaderboard snapshots + pairwise match analyses (the result, on file) |

---

## Run it locally

Requires Python 3 and Java 10+ (for the engine in `C1GamesStarterKit-master/`).

```bash
# play the hero against the starter algo (replays land in ./replays/)
./tools/run.sh algos/smart_oracle_F2 C1GamesStarterKit-master/python-algo

# fast syntax/runtime sanity check of an algo
./tools/test.sh algos/smart_oracle_F2

# statistically-honest head-to-head (2N games + Wilson 95% CI on win rate)
python3 tools/bestof.py algos/smart_oracle_F2 algos/baselines/v13_second_ring 10
```

The hero runs out of the box via its vendored Python sim. For the native-speed Rust sim, build the
wheel once: `cd algos/athena/sim_rs && maturin develop --release`. See
[`docs/workflow/LOCAL_TESTING.md`](docs/workflow/LOCAL_TESTING.md).

---

## The numbers, verified — everything here traces to a file

| Claim | Source |
|---|---|
| Simulator throughput, 42/42 tests, 13,319/13,319 fuzz, 2× 1M fuzz, Java-engine parity | [`algos/athena/sim/SIM_PARITY.md`](algos/athena/sim/SIM_PARITY.md) · [`PARITY_REPORTS/`](algos/athena/sim/PARITY_REPORTS) |
| The three regression-gated variants & the 12–0 benchmark | [`VARIANT_SMART_ORACLE_F2_REPORT.md`](algos/smart_oracle_F2/VARIANT_SMART_ORACLE_F2_REPORT.md) · [`docs/devlog/`](docs/devlog) |
| Top-5 finish & the one-page narrative | [`citadel_terminal_application.pdf`](citadel_terminal_application.pdf) |
| Live-config-verified unit math (incl. the 0.7×Y Support-shield coefficient) | [`docs/game/UNITS_REFERENCE.md`](docs/game/UNITS_REFERENCE.md) |

*Honest-scope notes:* the **427 replays** train the *opponent action prior* the search samples
from — not the archetype classifier (a separate, deliberately simple component). The **13,319/13,319**
fuzz is **Python-sim ↔ Rust-sim** byte-identical; the Java-engine comparison is the separate 19-column
STRICT gate over 87,677 ranked frames.
