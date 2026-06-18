# CitadelTerminal

AI strategy workspace for the Citadel Securities Terminal AI Challenge, combining Python game-playing logic with local simulation, replay analysis, and Rust engine work.

## Context

Built for the Citadel Securities Terminal AI Challenge.

**Result:** top 5 of 1000+ applicants.

Terminal is a two-player strategy game where each turn requires allocating resources to defensive structures and attacking units under strict time limits. The strongest solutions need good simulation, opponent modelling, pathing intuition, and fast iteration.

## What I Built

- Python strategy variants for local and live competition evaluation.
- Replay analysis scripts for diagnosing breaches, attacks, defensive failures, and matchup weaknesses.
- A custom simulation workflow, including Rust-backed simulation work under `algos/athena/sim_rs/`.
- Experiment logs, ranked replay corpora, and opponent/strategy research used to iterate under competition pressure.
- Tooling for packaging algorithms and running local matches against baselines and stored opponents.

## Technical Ideas Demonstrated

- Game-playing AI under a fixed turn-time budget.
- Search/evaluation loops for attack and defence choices.
- Opponent modelling from replay traces and historical matchups.
- Simulation parity checks between local engine behaviour and observed game results.
- Performance-oriented engineering: Python strategy code supported by native/Rust simulation paths.

## Repository Structure

```text
.
├── algos/                         # Strategy variants and active Athena workspace
│   └── athena/
│       ├── algo_strategy.py       # Main Python strategy entry point
│       ├── sim/                   # Python simulation/parity tooling
│       └── sim_rs/                # Rust simulation engine work
├── C1GamesStarterKit-master/      # Official starter kit and local engine
├── data/                          # Leaderboard and match metadata snapshots
├── research/                      # Finalist repos, replay analysis, replicas, notes
├── configs/                       # Competition configuration snapshots
└── README.md
```

## Tech Stack

- Python for strategy implementation, replay analysis, and tooling.
- Rust for simulation-engine work in `algos/athena/sim_rs/`.
- Java starter-kit engine from Correlation One / Terminal.
- Shell scripts for match runs, packaging, and local evaluation.

## Setup

Install Python dependencies:

```bash
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

The official starter kit includes `C1GamesStarterKit-master/engine.jar`, so local matches also require a working Java runtime.

Rust simulation work:

```bash
cd algos/athena/sim_rs
cargo test
```

## Example Commands

Run the active strategy using the starter kit:

```bash
cd algos/athena
./run.sh
```

Run Rust simulation tests:

```bash
cd algos/athena/sim_rs
cargo test
```

Package an algorithm for upload:

```bash
./C1GamesStarterKit-master/scripts/zipalgo_mac algos/athena algos/athena.zip
```

## Engineering Highlights

- Treated the competition as a systems problem, not only a strategy problem: simulator fidelity, replay tooling, and iteration speed mattered.
- Built and retained research artefacts that explain why strategy variants changed.
- Used a separate Rust simulation project to explore faster evaluation and parity testing.
- Kept the official engine/starter kit available so reviewers can inspect the expected competition entry-point shape.

## Public-Readiness Notes

- Some replay and finalist-repo material is retained for research provenance.
- The repo contains generated/local artefacts from competition iteration; generated build outputs are not part of the core strategy.
- Public docs describe the portfolio value, but this is still a competition workspace rather than a clean library.

## Future Improvements

- Add a minimal deterministic smoke match that runs quickly without the full replay corpus.
- Move the strongest replay-analysis commands into a single documented CLI.
- Add a concise architecture diagram showing strategy, simulator, replay analysis, and packaging flow.

## Usage Note

This repository is public for portfolio review and educational inspection. It is not affiliated with Citadel Securities or Correlation One.
