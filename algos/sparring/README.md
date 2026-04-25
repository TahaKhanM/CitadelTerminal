# Athena v3 sparring partners

Six adversarial algos targeting Athena v3's documented weaknesses. Each
is a complete C1 starter-kit-style algo (`algo.json` + `algo_strategy.py`
+ vendored `gamelib/`) with production wrappers (try/except + 13-second
SIGALRM watchdog + safe-fallback minimal turret defense).

These are designed to be used as opponents in upcoming self-play and
curriculum-learning phases for Athena v3. Each one targets a specific
documented blind spot from `algos/athena_v3_planner/FINAL_REPORT.md` § 6.

## Roster

| Partner | Targets | Failure mode exposed |
|---|---|---|
| `anti_athena_hoarder/` | FINAL_REPORT § 6.1 — long hoard-heavy match regression (G11 cluster: `new-strat-algo` 25-turn, `python-algo-v5` 24-turn, `python-algo-v3-detectors` 24-turn). | Athena reaches for `hoard` 14-16× across these games; opponent out-hoards and punishes with delayed all-in. |
| `scout_rush_adversary/` | FINAL_REPORT § 6.6 — SCOUT_RUSH classifier blindspot (Phase 3 G5: 14/25 misclassified to BALANCED). | Action_predictor returns BALANCED's empirical mix for early turns; SCOUT_RUSH lethality is highest in that exact window. |
| `asymmetric_defender/` | FINAL_REPORT § 6.5 — side-asymmetry weakness (Phase 6B: 10/10 P1=athena vs 4/10 P1=lostkids); also static defense archetypes (v_funnel, two_layer_keep, spread_line, v13_inspired all symmetric). | Athena's symmetric offense priors collapse against asymmetric defense. |
| `misdirector/` | FINAL_REPORT § 6.2 — classifier mis-fires + missing misdirection counter (Lostkids has it; Athena doesn't). | Decoy structures + late-game opposite-side commits exploit the assumption that early-board structure placement reflects intent. |
| `side_exploiter/` | FINAL_REPORT § 6.5 — direct exploit of P1/P2 asymmetry. | Detects which side Athena lands on at game start and applies a hard-coded counter-configuration. |
| `mirror_athena/` | FINAL_REPORT § 6.3 — archive disabled by default; baseline for measuring planner-pipeline value. | Frozen Athena replica with shifted utility weights (α=2.0, β=1.5, γ=δ=0); over-weighting HP delta biases toward scout floods. |

## Smoke matrix headline

3 of 6 partners beat current Athena in single-match smoke (turn 69, 56,
71). Zero crashes, zero timeouts. See `SMOKE_MATRIX.md` for the full
breakdown and per-match diagnosis.

| Partner | Outcome | Turns | Athena HP | Sparring HP |
|---|---|---:|---:|---:|
| anti_athena_hoarder | **sparring wins** | 69 | 0 | 39 |
| scout_rush_adversary | athena wins | 100 | 36 | 17 |
| asymmetric_defender | **sparring wins** | 56 | 0 | 13 |
| misdirector | athena wins | 100 | 40 | 37 |
| side_exploiter | **sparring wins** | 71 | -1 | 33 |
| mirror_athena | athena wins | 83 | 37 | -6 |

## Production safety

All 6 algos share the same wrapper pattern (lifted from
`algos/athena_baseline_lostkids/` and `algos/athena_v3_planner/`):

* 13-second `SIGALRM` watchdog around `on_turn` (with `threading.Timer`
  fallback when SIGALRM is unavailable).
* Top-level `try/except` in `on_turn` catches both the watchdog
  timeout exception and any unhandled exception.
* On any failure, fall through to a 4-turret safe-fallback at
  `[11,11], [16,11], [13,11], [14,11]`.
* All numerics are read from runtime `config` (never hardcoded).
* Vendored `gamelib/` (copied from `v13_second_ring/gamelib/` which is
  itself the `python-algo` reference) — no system-level package
  dependency.

## How each partner is wired

### Variable per-game seeds

`scout_rush_adversary`, `asymmetric_defender`, and `misdirector` all
seed a per-game RNG at `__init__` time, so each match probes a
different angle:

* `scout_rush_adversary`: which edge gets the turn-0 rush.
* `asymmetric_defender`: which side is heavy.
* `misdirector`: which corner gets the decoy.

This is critical — if Athena memorizes a fixed pattern, the partner
isn't probing the actual generalization weakness. Side selection is via
`random.randint(0, 1)` in `__init__`.

### Action-frame consumption

`misdirector` tracks Athena's mobile-spawn x-distribution from
`on_action_frame` to feed its "attack opposite where they enter" logic.
`side_exploiter` uses the first turn's spawn events to detect which
player_index Athena was assigned. Both partners observe the
action-frame `owner` field convention (in raw JSON, `1` = the algo
itself, `2` = the opponent — flipped from the GameState convention).

## Reproducing the smoke matrix

```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal

ATHENA=algos/athena_v3_planner
SPARRING=algos/sparring

for partner in anti_athena_hoarder scout_rush_adversary \
               asymmetric_defender misdirector \
               side_exploiter mirror_athena; do
  PYTHON_CMD=/opt/miniconda3/bin/python3.13 python3 \
    C1GamesStarterKit-master/scripts/run_match.py \
    $ATHENA $SPARRING/$partner
done
```

Replays land in `C1GamesStarterKit-master/replays/`. The current matrix
replays are persisted to `replays/sparring_smoke_<partner>.replay`.

## What's next (not part of this build)

These partners are the input for upcoming self-play / curriculum
phases. Recommended uses:

1. **Classifier re-fit** (Phase 8B from FINAL_REPORT § 7.1) — generate
   ~100 Athena vs `scout_rush_adversary` and Athena vs `misdirector`
   self-play replays, re-run `opponent.build_corpus`, re-fit
   GaussianNB.
2. **Asymmetric awareness** — generate self-play replays against
   `asymmetric_defender` and `side_exploiter`, train a left-vs-right
   defense recommender on top of the existing archetypes.
3. **Hoard counter** (FINAL_REPORT § 7.5) — use
   `anti_athena_hoarder` as the adversary while developing the
   recommended hoard-counter offense template.
4. **Pipeline-value baseline** — use `mirror_athena` as the lower bound
   when ablating the classifier / sim_rs / archive components.
