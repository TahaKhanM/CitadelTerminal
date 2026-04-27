# Advanced Strategies — Brainstorm + Implementation Prompt

This doc has two parts:

- **§ A — Strategy brainstorm**: a catalogue of radically more ambitious / technical / mathematical strategy ideas, grouped by technique. These are the *deltas* from v1-v13 (all rule-based greedy) toward the kind of thinking required to reach the ELO 2000+ tier.
- **§ B — Implementation prompt**: a self-contained prompt to paste into a fresh Claude Code session. It drives a multi-iteration build of these ideas, validated against the existing opp_* baselines and v13 via the existing `bestof` / `tournament` / `eval.sh` tooling.

Everything in v1-v13 is a fixed schedule of turret placements plus threshold-triggered scout spawns. No search, no opponent modeling, no forward simulation, no math. The ideas below change that.

---

## § A — Strategy Brainstorm

### The game, re-framed mathematically

Citadel Terminal is an **imperfect-information extensive-form game with simultaneous moves** but a **deterministic resolution function**. Each turn both players commit an "army plan" blind, then a deterministic engine (shield → move → attack → cleanup, per frame) resolves it. The resolved state is fully observable.

This means:
- **Given a forward simulator of the action phase, any candidate plan can be evaluated exactly.** This is the single biggest lever v1-v13 leaves on the table.
- Action space factorises: `plan = (structure_ops, mobile_ops)`, and within each, positions are discrete over ≤392 tiles × ≤6 unit types × {upgrade, build, remove}.
- Time horizon ≤100 turns; MP decays geometrically (γ=0.75) so the *optimal spending schedule* has a clean analytical structure.
- Compute budget is **15,000 ms/turn**; v13 uses **~47 ms max, ~15 ms mean**. Roughly **300–1000× headroom** unused.

The 300× headroom is the entire argument for switching away from rule-based. Everything below exploits it.

---

### Tier 0 — Foundation (prerequisite for Tiers 2-4)

**Idea 0.1: In-process forward simulator**
Port the engine's per-frame loop (shield → move → attack → cleanup; Euclidean ranges; targeting priority from [TARGETING_AND_PATHING.md](TARGETING_AND_PATHING.md)) into `gamelib_ext/simulator.py`. Inputs: a `GameState`-like snapshot + a list of (player, action) commits. Output: resolved state + per-frame event log (damage dealt, breaches, deaths).
- Rebuild `find_path_to_edge` as a cached BFS keyed by the wall-set fingerprint.
- Skip the JVM completely; target <2 ms per full-action-phase sim on Python.
- This single tool unlocks Tiers 2, 3, and parts of 4.

**Idea 0.2: Action-space discretiser**
Define a `Plan` as a small named-tuple: `(structure_adds, upgrades, removals, mobile_spawns)`. Provide a generator that emits a ranked shortlist of ~20 candidate plans from any state (budget-feasible, non-trivial, not dominated). This shrinks a 10^6-candidate space to something searchable.

---

### Tier 1 — Analytical / Mathematical (closed-form, no search)

**Idea 1.1: Kill-curve differential equation for Scout waves**
Along a known path, the number of alive scouts N(s) at arc-length s obeys

```
dN/ds = −( Σ_turret covers(s) * dmg(turret) ) / (scout_hp_eff · v_scout)
```

Solve analytically given upgraded-turret placement → **closed-form predictor of "how many scouts survive a given defense"**. Extend with Support shield by replacing `scout_hp_eff ← 15 + Σ 1+0.7y_i` for reachable supports. Makes offense-sizing deterministic — no more "spawn when MP ≥ 10x cost" heuristics.

**Idea 1.2: Defense layout as Integer Linear Program**
Variables: `b_{t,p} ∈ {0,1}` = "place unit type t at tile p", `u_{t,p} ∈ {0,1}` = "upgrade it". Constraints: SP budget, tile exclusivity, spawn-edge clearance, adjacency rules. Objective: minimise `max_{enemy_path P} Σ_{tile on P} enemy_breach_weight(tile) · (1 − coverage_probability(tile))`. Solve with `pulp` (CBC) — small enough to solve in <1 s. Re-solve every 3-5 turns when SP accumulates or enemy structure map changes.

**Idea 1.3: MP accumulation optimiser**
MP follows `MP_{t+1} = 0.75·MP_t + (1 + ⌊t/5⌋)` plus breach bonuses. Closed-form solution for "given current MP and future income schedule, when is the peak MP if I skip spending for k turns?" Use this to pick **optimal burst turns** for Scout rushes instead of the current "spawn when MP ≥ 10x cost" threshold. Solves the offensive-timing problem with calculus, not if-statements.

**Idea 1.4: Economic Pareto frontier**
For every (SP, MP) budget at turn t, precompute (offline, once) the Pareto-optimal allocation between (defense layout quality) and (offense wave strength). At runtime look up the frontier; pick a point on it weighted by current HP delta. Replaces all hand-tuned "upgrade schedule" code with a table lookup.

---

### Tier 2 — Search-Based (use the 300× headroom)

**Idea 2.1: 1-ply expectimax over the shortlisted plan set**
For each candidate plan from the discretiser: simulate against a fixed-policy opponent model (e.g. "opponent plays its mean historical plan"). Pick the plan with highest simulated HP delta. At ~2 ms/simulation × 20 plans = 40 ms/turn. Pure win.

**Idea 2.2: 2-ply minimax with action pruning**
Us → opponent → us. Prune each layer to top-5 plans by a heuristic evaluator (closed-form HP delta estimate from 1.1). Leaves: 5×5 = 25 full simulations. At 10 ms each = 250 ms/turn — well within budget.

**Idea 2.3: MCTS with UCB1**
Root = current state. Actions = shortlisted plans. Rollout policy = a cheap heuristic bot (v13-like) playing against itself until turn 100 or HP=0. Budget 500 rollouts/turn. Accumulated value estimates give a principled plan-ranking. More robust to opponent variance than minimax.

**Idea 2.4: Model-predictive control (MPC) with receding horizon**
Plan a full 5-turn sequence every turn, then only commit turn-1 and re-plan. Horizon depth lets us "bank MP for 3 turns, then burst on turn 4" emerge naturally from the optimiser without hand-coding the saving behaviour. Use beam search (width 10, depth 5) with the forward simulator as rollout.

---

### Tier 3 — Opponent Modelling

**Idea 3.1: Archetype classifier**
After turns 2-3, classify the opponent into one of ~6 archetypes (`scout_rush`, `demo_line`, `turret_castle`, `support_caravan`, `corner_spammer`, `balanced`) via a hand-crafted decision tree over observed features: turret count, wall density, front-row occupancy, first spawn edge, mobile type. Maintain one precomputed counter-strategy per archetype. Zero online compute. Should beat any fixed opponent ≥75 %.

**Idea 3.2: Bayesian opponent-policy posterior**
Treat opponent as a mixture over K base policies. Maintain `p_i(t) = P(opponent uses policy i | observations up to t)` with Bayesian updates frame-by-frame. Best-respond against the posterior. Handles opponents that switch phases mid-game (defensive → aggressive).

**Idea 3.3: Online no-regret learner (Hedge / CFR-lite)**
Keep a small strategy portfolio (~10 pure strategies). Each turn, play a Hedge-weighted mixture; update weights by "counterfactual regret" = "simulated HP delta if I'd played pure strategy i vs observed actual HP delta". Converges to equilibrium against self-play and exploits fixed opponents fast.

**Idea 3.4: Spawn-edge prediction from breach history**
Fit a simple Markov chain over opponent's spawn-edge choices per turn. Use the prediction to pre-position interceptors and to skew defense placement toward the predicted lane. Takes 2 KB of state, 30 lines of code, likely worth +30-50 ELO on its own.

---

### Tier 4 — Learning / Meta-Optimisation (offline)

**Idea 4.1: Evolutionary hyperparameter tuning over v13's knobs**
v13 has ~20 magic numbers (turn thresholds, position lists, upgrade priorities). Run CMA-ES offline with fitness = win rate in a mini-tournament against opp_scout_rush + opp_demo_line + opp_turret_castle + v12. ~1000 evaluations × 3 games each = one overnight run. Likely +40 ELO with zero algorithmic change.

**Idea 4.2: Self-play reinforcement learning**
Implement a lightweight PPO loop: state = (board tensor, resources, turn); action = categorical over ~30 plan templates; reward = HP delta. Train 10 k episodes. Export policy weights as numpy; inference = one 3-layer MLP forward pass (<0.5 ms). This is how the Rust #1 likely works.

**Idea 4.3: Imitation learning from own wins**
Take every winning replay, label each turn's action. Train a policy to imitate. Dead simple, gives a fast neural prior usable as the rollout policy inside MCTS (2.3).

**Idea 4.4: AlphaZero-style self-play with tree search**
MCTS rollouts guided by a policy net (what plan is likely good) + value net (is this state winning). Overkill to implement from scratch but the canonical "beat everyone" design.

---

### Tier 5 — Advanced Offensive Mechanics (unexplored in v1-v13)

**Idea 5.1: Support caravan offense**
None of v1-v13 use Supports. An upgraded Support at Y=13 grants `1 + 0.7·13 = 10.1` shield per Scout, range 7. Four back-row upgraded supports → +40 shield/Scout → each Scout has **effectively 55 HP**, not 15. A 10-Scout wave becomes 550 effective HP — enough to break any defense short of 8+ upgraded turrets. Cost: 32 SP, achievable by T12. **This is the single most-exploitable gap in current strategy.**

**Idea 5.2: Demolisher train behind Support screen**
Demolishers have 5 HP (glass cannon) but 8 dmg/frame at range 4.5. A single upgraded Support adds +10 HP (3× original). A caravan of 4 Supports + 3 Demolishers = 3 × (5+40) = 135 effective HP dealing 24 dmg/frame from range 4.5. Blows through upgraded walls and turrets from outside their range.

**Idea 5.3: Interceptor screen + Scout backline**
Interceptors (40 HP, 20 dmg vs mobile, 0 vs structure) are useless offensively alone — but deployed as a *screen*, they eat turret fire AND kill any enemy interceptors, letting scouts behind score freely. Mixed-unit waves are not in any v1-v13 strategy.

**Idea 5.4: Scripted self-destruct chains**
A self-destructing mobile deals its full starting HP as damage in a 1.5 Euclidean radius. A Scout that has travelled ≥5 tiles and is then forced to a dead-end SDs for 15 dmg to adjacent enemy structures. Deliberately engineer paths that force SD in the enemy's back row via temporary wall placements → zero-MP structure damage.

**Idea 5.5: Path-engineering via own walls**
Place and remove walls to *force* our own mobiles to take specific paths (longer but safer, or short but high-yield). The engine recomputes paths each frame, so mid-action phase our removal refunds + reroutes are allowed. Completely unused.

**Idea 5.6: Removal-and-rebuild adaptive defense**
Refund is 0.9 × cost × (HP/maxHP). A healthy unupgraded turret refunds 1.8 SP of 2 SP cost. Net cost of relocating = 0.2 SP. If breach patterns show the enemy is attacking the left 80 % of the time after T10, remove right-side turrets, rebuild them on the left. Currently no v1-v13 ever *removes* a structure.

---

### Tier 6 — Exotic / Clever

**Idea 6.1: Compute-budget adversarial exploitation**
We use 47 ms / turn. If the opponent uses ~2 s / turn (realistic for deep-search bots), we can *deliberately create high-branching states* (many mobile types, ambiguous pathing) to push them toward their 15 s timeout. Each second past 15 s costs them 1 HP.

**Idea 6.2: Opening book**
Precompute the perfect first 4-8 turns against each identified archetype. Ship them as a lookup table. Most human-designed algos are deterministic in the opening — a tailored counter-opening gives free HP in T0-T8.

**Idea 6.3: Endgame HP optimiser**
From T90 onward, the game is a timed HP-delta problem, not a win-the-breach problem. Switch policies: maximise own HP gain per remaining turn, ignore long-horizon defense investment. v1-v13 have no endgame distinction.

**Idea 6.4: Mirror-match asymmetry exploitation**
In self-play, v13 likely has deterministic tiebreakers that favour one side (engine's `MOST_RECENTLY_CREATED` target-priority interacts with spawn order). Identify and exploit.

---

### Recommended build order (highest-EV first)

1. **Tier 0 forward simulator** (gate for everything else) — 1-2 days
2. **Tier 5.1 Support caravan offense** — cheap, huge ELO gain, no simulator needed — half a day
3. **Tier 3.1 archetype classifier + 4 counters** — 1 day
4. **Tier 2.1 1-ply expectimax** using simulator — half a day
5. **Tier 1.2 ILP defense solver** — 1 day
6. **Tier 3.4 spawn-edge Markov predictor** — half a day
7. **Tier 4.1 evolutionary tuning on all the above** — overnight
8. **Tier 2.3 MCTS** — 1-2 days (if 1-7 plateau at ~ELO 1900)
9. **Tier 4.2 self-play RL** — only if Python caps out, then port to Rust

---

## § B — Implementation Prompt

Copy the block below into a fresh Claude Code session opened at the repo root. It's self-contained: the session auto-loads `CLAUDE.md` and points at everything else. Budget 4-12 hours of iterations.

```
You are building the NEXT GENERATION of Citadel Terminal algos. The current
best (v13_second_ring) is a rule-based greedy bot sitting mid-leaderboard.
The ELO #1 bot is written in Rust — that tells us the top tier uses real
algorithms (search, learning, optimisation), not hand-tuned thresholds.

Your job is to move this codebase from "rule-based greedy" to "analytically
grounded + search-based + opponent-adaptive" across multiple iterations,
validating every step against the existing opp_* baselines AND v13 using
the existing tooling. Don't bundle ideas — one hypothesis per iteration.

ORIENTATION (read in this order, then STOP reading and start working)
  1. CLAUDE.md
  2. docs/ADVANCED_STRATEGIES_PROMPT.md § A  ← the brainstorm; this is the menu
  3. docs/CLAUDE_WORKFLOW.md                 ← the iteration loop you must follow
  4. docs/UNITS_REFERENCE.md                 ← COMPETITION numbers
  5. docs/TARGETING_AND_PATHING.md           ← rules the simulator must match
  6. docs/API_REFERENCE.md                   ← gamelib surface
  7. algos/v13_second_ring/algo_strategy.py  ← the baseline to beat
  8. algos/opp_scout_rush/, opp_demo_line/, opp_turret_castle/ ← opponent archetypes

Do NOT re-read the raw "Citadel Context Files/". The docs/ summaries are
authoritative; if they seem wrong, run `/inspect-config` and trust the
server config.

THE BUILD ORDER (STRICT — do NOT skip)

Phase 0 — Simulator foundation
  Goal: an in-process forward simulator we can call 100s of times per turn
  without hitting the JVM. Target < 2 ms per full action-phase simulation.

  0.1 Create gamelib_ext/simulator.py (new module; do NOT modify gamelib/).
      Implement:
        • State snapshot from a GameState instance (walls, turrets, supports,
          mobiles-in-flight if any, HP, resources).
        • Per-frame loop: shield pass → move pass (BFS-cached path) → attack
          pass (targeting priority per TARGETING_AND_PATHING.md) → cleanup.
        • Euclidean range checks. Self-destruct rule (≥5 tiles moved).
        • Return (end_state, damage_to_us, damage_to_them, breach_count,
          frame_count).
      Validate by replaying 3 real .replay files frame-by-frame; simulator
      output must match engine's actual damage/breaches to within ±5 %.
      If it doesn't match, FIX THE SIMULATOR before proceeding. This is
      load-bearing for every later iteration.

  0.2 gamelib_ext/plans.py — a Plan dataclass (structure_adds, upgrades,
      removals, mobile_spawns) and a generator `candidate_plans(state) →
      list[Plan]` returning ≤20 budget-feasible, non-dominated candidates.

  GATE: Simulator error < 5 % on 3 replay comparisons. Commit as
        gamelib_ext_v0. Only then proceed.

Phase 1 — Support caravan offense (v14)
  Hypothesis: None of v1-v13 use Supports. An upgraded Support at Y=13
  grants 10.1 shield/Scout at range 7. Four supports = +40 shield/Scout
  = 55 effective HP per Scout. A 10-Scout wave is 550 effective HP, enough
  to break most mid-tier defenses. Cost 32 SP, achievable by T12.

  /new-algo v14_support_caravan v13_second_ring
  Minimal changes:
    • T8-T12: schedule 4 upgraded Supports at [12,13],[13,13],[14,13],[15,13].
    • Keep all v13 defense scheduling; just reallocate some SP from
      redundant upgrade turns to Supports.
    • Do NOT change offensive spawning yet — the shields are the whole
      point; let existing Scout triggers benefit.
  GATE: /bestof v14 v13 10   — v14 must win ≥65 %
        /bestof v14 opp_scout_rush 5   — ≥70 %
        /bestof v14 opp_demo_line 5    — ≥70 %
        /bestof v14 opp_turret_castle 5 — ≥70 %
        /profile-turns — max < 500 ms.
  If any gate fails, analyse replays, iterate ONCE, then either fix or
  revert to v13 and pivot to Phase 2 without the caravan.

Phase 2 — Opponent archetype classifier + counters (v15)
  Hypothesis: Fixed-policy opponents can be beaten ≥ 75 % if we identify
  them by T3 and play a pre-tuned counter.

  /new-algo v15_adaptive v14_support_caravan  (or v13 if v14 regressed)
  Add gamelib_ext/classifier.py:
    • Features after T3: turret_count, wall_count, support_count,
      enemy_front_density(y=14-15), first_spawn_edge, mobile_type_seen.
    • Decision tree (hand-crafted, ≤30 nodes) → archetype ∈
      {scout_rush, demo_line, turret_castle, support_caravan, balanced}.
    • Counter-strategy table: per-archetype overrides for spawn edge,
      upgrade priority, and wave size.
  GATE: /bestof v15 v14 10 — v15 must win ≥60 %
        /bestof v15 opp_* — ≥75 % each
        /tournament v15 v14 v13 opp_* — v15 top of table.

Phase 3 — Forward-simulator-based plan ranking (v16)
  Hypothesis: With a working simulator we can evaluate ~20 candidate plans
  per turn and pick the highest-HP-delta one — no more hand-coded thresholds.

  /new-algo v16_expectimax v15_adaptive
  Replace _attack_phase and _spend_hoard with:
    1. plans = candidate_plans(state)   # from Phase 0.2
    2. For each plan: simulate action phase vs opponent's most-probable
       response (from Phase 2 classifier → counter → mean-action).
    3. Pick plan with max (their_HP_loss − our_HP_loss), tie-break on
       structural damage dealt.
    4. Commit the plan via attempt_spawn / attempt_upgrade / mark_removal.
  Budget: 20 plans × 2 ms simulate = 40 ms. Well within 15 s.
  GATE: /bestof v16 v15 10 — ≥60 %
        Tournament same as Phase 2 — v16 top.
        Max compute < 1500 ms.

Phase 4 — Analytical defense (ILP)  (v17)
  Hypothesis: Turret/wall placement is an ILP solvable in < 1 s that
  dominates hand-placed schedules.

  /new-algo v17_ilp_defense v16_expectimax
  Add gamelib_ext/defense_ilp.py:
    • pip install pulp (add to requirements if not present).
    • Variables b[t,p], u[t,p] over ~20 candidate tiles × {WALL, TURRET,
      SUPPORT}. NOT over all 200 tiles — the candidate set is seeded from
      hotspot analysis in ITERATION_HISTORY_V1_V11.md.
    • Objective: minimise weighted sum of (enemy's simulated breach damage
      against a scout-rush probe) + (SP cost).
    • Solve every 3 turns or when SP > 10 accumulated.
  GATE: Same pattern — /bestof vs v16 ≥ 55 %, opp_* ≥ 75 %.

Phase 5 — Spawn-edge Markov prediction (v18)
  Hypothesis: Opponents' spawn-edge choice is highly autocorrelated; a
  2nd-order Markov chain predicts it with > 60 % accuracy after T5.

  /new-algo v18_predictive v17_ilp_defense
  Add gamelib_ext/spawn_predictor.py: track enemy breaches' origin edges
  per turn; maintain Markov P(edge_t | edge_{t-1}, edge_{t-2}). Use
  prediction to bias defense-ILP weights toward the predicted lane AND to
  pre-spawn an Interceptor at the expected intercept tile.
  GATE: /bestof v18 v17 10 ≥ 55 %; tournament top.

Phase 6 — Offline hyperparameter evolution (v19)
  Hypothesis: ~20 magic numbers in the combined v18 algo (thresholds,
  weights, positions) have untapped tuning headroom. CMA-ES over them
  against the opp_* suite finds ≥ 40 ELO.

  Build tools/evolve.py:
    • Wraps bestof.py.
    • Population 30, generations 20, fitness = sum of win rates vs opp_* +
      v13 + v16 (5 opponents × 4 games = 20 games per eval).
    • Run overnight (≈ 6000 matches total).
  Copy the best candidate to algos/v19_evolved/.
  GATE: /bestof v19 v18 10 ≥ 55 %; tournament top.

Phase 7 — OPTIONAL: MCTS (v20)
  Only attempt if v19 wins ≤ 55 % vs v18 (i.e., we've plateaued on
  heuristic methods). Otherwise ship v19.

  Implement UCB1 MCTS over Plan actions, using the Phase 0 simulator as
  rollout engine, Phase 2 archetype → opponent-plan distribution as the
  opponent move sampler, and Phase 4 ILP-cost as the rollout-policy
  heuristic. 500 rollouts / turn. GATE same pattern.

PROCESS RULES (these are MANDATORY, not suggestions)

  • One hypothesis per iteration. No bundling.
  • After every iteration: run /bestof AND /tournament; paste results in
    the iteration's status message. No "it seems to work" without numbers.
  • Keep v13_second_ring and every intermediate vN as regression
    references — NEVER delete them. Copy to algos/<vN>_checkpoint/ before
    starting the next phase.
  • Every number the algo uses MUST come from self.config (unit stats) or
    from a module-level CONFIG dict (your own constants) — no magic
    numbers scattered through on_turn.
  • Performance gate: max turn time < 1500 ms on every gate, mean < 300 ms.
    If the simulator ever exceeds 2 ms/sim, profile with cProfile and fix
    before adding more callers.
  • If 3 consecutive phases fail their gates, STOP and report. Don't
    grind. The next human decision is "port to Rust" or "pivot ideas".
  • If a phase's added complexity yields < 3 % win-rate lift over the
    prior checkpoint, REVERT that phase — complexity is a tax.
  • Use TodoWrite for each phase. Mark completed only after gate passes.
  • Use subagents in parallel where safe: e.g. Phase 6 evolutionary
    evaluation can farm matches to general-purpose subagents.

MUST NOT

  • Modify C1GamesStarterKit-master/ (read-only).
  • Hardcode unit numbers anywhere (always self.config["unitInformation"][i]).
  • Skip the simulator validation in Phase 0. Every later phase depends
    on it being within 5 % of reality.
  • Claim a gate passes without pasting the /bestof + /profile-turns
    output that proves it.
  • Place walls on own spawn-edge diagonals (y=x for BOTTOM_LEFT,
    y=27-x for BOTTOM_RIGHT).
  • Make Phase N depend on Phase M > N. Each phase must be a strict
    superset of prior behaviour, gated independently.

REPORTING (after every phase)

  Phase <N>: <one-line hypothesis>
  Result: /bestof vs prior = W/T (XX %); vs each opp_* = ...; max compute ms.
  Signal: <what the replay analysis showed>.
  Next: <advance to Phase N+1 / revert / pivot>.

FINAL HANDOFF (when you stop — either finished or hit a blocker)

  1. Path to the strongest algo.
  2. Full tournament table: v13, v14…v19, opp_scout_rush, opp_demo_line,
     opp_turret_castle. (Run it and paste the output.)
  3. /profile-turns output on a late-game replay of the strongest algo.
  4. List of every gamelib_ext/ module added, with a 1-sentence purpose.
  5. Three concrete follow-ups for the next session (ranked by expected
     ELO gain, citing which § A idea they come from).

Begin. Start with Phase 0. First message back should be your reading
completion, your simulator design sketch (what functions, what state
representation), and whether you see any blocker before writing code.
```

---

## Notes on running this

- Budget the session for **4-12 hours**. Phases 0-3 are the ELO-critical ones; 4-6 are refinements; 7 is optional.
- Each phase's gate is deliberately specific (`/bestof` thresholds, compute ms). Don't relax them mid-session — a failing gate is signal, not noise.
- The Phase 0 simulator is the highest-risk item. If it can't hit 5 % accuracy against real replays, stop and escalate: everything else assumes it. A good fallback is to wrap the JVM engine via `subprocess` as a slower but ground-truth simulator (~200 ms/call instead of 2 ms), which is still usable for Tier 2 search with smaller budgets.
- Port-to-Rust is an escape hatch, not a first step. v14 (Support caravan) and v15 (archetype classifier) are near-guaranteed wins with zero infrastructure.
