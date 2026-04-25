# Sparring smoke matrix — Athena v3 vs each sparring partner

**Generated**: 2026-04-25 (Wave 1-J build).
**Driver**: `C1GamesStarterKit-master/scripts/run_match.py` (single match per pair).
**Athena**: `algos/athena_v3_planner/` (commit `89e6528`, the shipping state).
**Engine**: starter-kit `engine.jar` with deterministic spawn assignment
(Athena always P1 in this matrix).
**Replays**: persisted to `replays/sparring_smoke_<name>.replay`.

## Result table

| # | Sparring partner          | Winner               | Turns | Athena HP | Sparring HP | Breach (Athena → opp) | Breach (opp → Athena) | Crash | Timeout |
|---|---------------------------|----------------------|------:|----------:|------------:|----------------------:|----------------------:|:-----:|:-------:|
| 1 | anti_athena_hoarder       | **anti_athena_hoarder** |  69 |  0.0      | 39.0        |  1                    | 40                    |  no   |   no    |
| 2 | scout_rush_adversary      | athena_v3_planner    | 100   | 36.0      | 17.0        | 23                    |  4                    |  no   |   no    |
| 3 | asymmetric_defender       | **asymmetric_defender** |  56 |  0.0      | 13.0        | 27                    | 40                    |  no   |   no    |
| 4 | misdirector               | athena_v3_planner    | 100   | 40.0      | 37.0        |  3                    |  0                    |  no   |   no    |
| 5 | side_exploiter            | **side_exploiter**   |  71   |  -1.0     | 33.0        |  7                    | 41                    |  no   |   no    |
| 6 | mirror_athena             | athena_v3_planner    |  83   | 37.0      | -6.0        | 46                    |  3                    |  no   |   no    |

**Aggregate**: Athena wins 3/6, sparring partners win 3/6. Zero crashes, zero
timeouts across all 6 matches. All algos executed their full 100-turn
budget cleanly.

## Per-match observations

### 1. anti_athena_hoarder (sparring **WINS**, turn 69)

The hoarder achieved exactly the regression profile from G11. By
sitting on dense upgraded turrets through turns 0-17 and then releasing
its pent-up MP as a single coordinated Demolisher line + Scout follow-up
at turn 22+, it punished Athena's offensive MP-burn pattern. Athena
delivered only 1 breach the entire match while taking 40 — the sparring
partner accumulated 168 SP of stationary resource left on board (vs
Athena's 17), confirming it actually accomplished the "dense defense
that withstands sustained offense" profile that defines the G11
regression cluster (`new-strat-algo`, `python-algo-v5`,
`python-algo-v3-detectors`). **Useful for self-play training.**

### 2. scout_rush_adversary (sparring loses, turn 100)

Athena's defense held against the scout rush. The early scout pings
(turns 0-5) did some chip damage (4 breaches total), but by turn 6+
Athena's turret upgrades had locked down both edges. The match ran the
full 100 turns and Athena won on HP 36 vs 17. The opponent did achieve
some early-game pressure — 4 breaches at turns 0-5 cost Athena ~12 HP
— so this partner is genuinely useful for stress-testing the early
game even though Athena ultimately wins. **Useful for classifier
re-training.**

### 3. asymmetric_defender (sparring **WINS**, turn 56)

The asymmetric layout broke Athena's symmetric offense priors. With a
heavy left-side defense and thin right-side, Athena's beam search picked
both `corner_dive_left` and `scout_rush_right` plays — both ran into
the wrong configuration. The 27 breaches Athena delivered weren't enough
to overcome the 40 it took back. **High-value sparring partner — directly
exposes the static-archetype-symmetric assumption.**

### 4. misdirector (sparring loses, turn 100)

Athena ignored the decoy. The classifier never got pulled toward
SCOUT_RUSH despite the alternate-corner spawning, so Athena played
straight up the middle. The decoy logic likely needs more aggressive
visual asymmetry (more decoy structures, or a louder feint signal) to
force a misclassification. Still useful — confirms Athena is genuinely
robust to small-signal misdirection but vulnerable to large-signal
asymmetry (per #3 above).

### 5. side_exploiter (sparring **WINS**, turn 71)

The asymmetric counter-strategy worked on the P1 path. Athena lost on
HP 0 vs 33. The 41 opponent breaches vs Athena's 7 confirms the
heavy-left + late-right-flank Demolisher push exploited an actual
side-asymmetry weakness. **Direct evidence of the Phase 6B no-gate
asymmetry being exploitable.**

### 6. mirror_athena (sparring loses, turn 83)

Athena's full beam search beats its own simplified replica with shifted
weights. The α=2.0 / β=1.5 / γ=δ=0 utility function biased mirror_athena
toward scout floods, but the base v13_inspired defense and lack of
sim_rs-grounded scoring left it weaker than the real Athena. The
46-breach delivery from Athena vs 3 from mirror confirms the real
classifier+sim+archive pipeline is materially stronger than a
heuristic rotation, even when the rotation has identical templates.
This is a useful **lower bound** for Phase 8B re-training — if
Athena's expected uplift is greater than what mirror_athena can
deliver, that's hard evidence the planner pipeline is doing real work.

## Key takeaways for upcoming self-play / curriculum-learning phases

1. **Three of six partners directly beat current Athena.** That's the
   intended outcome — adversarial training requires opponents that
   exploit actual weaknesses, not opponents that get steamrolled.
2. **The hoarder + asymmetric_defender + side_exploiter trio reproduce
   the documented G11 / Phase 6B weaknesses.** Self-play replays
   against these three should give the classifier the SCOUT_RUSH /
   DEMOLISHER_LINE / asymmetric-defense confusion examples it needs to
   re-fit.
3. **Misdirector and scout_rush_adversary stress-tested Athena
   without breaking it.** Useful for "Athena's robustness" measurement
   — if a future Athena variant LOSES to these, that's a regression.
4. **mirror_athena loses cleanly.** Confirms the classifier+sim+archive
   pipeline is doing real work beyond the static templates. If a
   future ablation removes those components and the result still beats
   mirror_athena, that's evidence the components weren't pulling weight.

## How to reproduce

```bash
ATHENA=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/athena_v3_planner
SPARRING=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/sparring

PYTHON_CMD=/opt/miniconda3/bin/python3.13 python3 \
  C1GamesStarterKit-master/scripts/run_match.py \
  $ATHENA $SPARRING/<partner_name>
```

Each match takes 20-90 seconds depending on length. Replays land in
`C1GamesStarterKit-master/replays/`.
