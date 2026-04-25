# Athena v3 — opponent archetype taxonomy

This file defines the **archetypes** the Phase 3 classifier emits. The
canonical machine-readable enum lives in `archetypes.json` (single
source of truth — code imports from there). This document explains
each one in plain English and lists 2-3 quantitative features that
are expected to be HIGH or LOW for matches assigned to it.

The taxonomy was derived from inspection of the 47-replay v13 corpus
(`data/replay_index.json`, 30 distinct opponents) plus the strategy
archetypes named in `docs/STRATEGY_GUIDE.md` and the public finalist
retrospectives (Lostkids, FUNNEL, Harvard, GT). It is intentionally
**small** (six classes) so the Bayesian classifier has enough samples
per class on a 47-replay corpus.

Long-tail / hard-to-classify opponents fall into `BALANCED` rather
than spawning new singleton classes.

---

## 1. `SCOUT_RUSH`

**Plain English**: Pile a single column with Scouts and walk through
the cheapest path. High MP burn rate, low SP banking. Common for
unranked / starter algos and ladder-rookies.

**Signature features**:
- Scout share of mobile spawns: **HIGH** (≥0.70).
- Spawn-x histogram entropy: **LOW** (concentrated on 1-2 columns).
- Mean MP at spawn: **LOW** (spends MP every turn, no hoarding).

## 2. `DEMOLISHER_LINE`

**Plain English**: Train of Demolishers along a sacrificial wall row,
abusing Demolisher range 4.5 > base Turret range 2.5. Gradually
demolishes the opponent's front line at safe range. Lostkids / FUNNEL
ladder-meta archetype.

**Signature features**:
- Demolisher share of mobile spawns: **HIGH** (≥0.40).
- Edge-asymmetry magnitude: moderate (one side preferred but Demos
  fire from both rows).
- Average attack-wave size: **MEDIUM** (5-10 units, not the 20+ of a
  Scout cannon).

## 3. `EDGE_FEINT`

**Plain English**: Alternates spawn edges, often with a "decoy wall"
on one corner; high spawn-x entropy across BL-vs-BR but with
asymmetric concentration (peak shifts every 3-5 turns). Common for
adaptive-hole / Harvard-pattern algos.

**Signature features**:
- Spawn-x histogram entropy: **HIGH** (≥2.5 nats, broad).
- Edge-usage asymmetry magnitude: **LOW** (close to 0.5/0.5 over
  the match), but per-turn locality is HIGH (single-side bursts).
- Wall-removal rate: **HIGH** (rebuilds the corner that just fired).

## 4. `SUPPORT_BURST`

**Plain English**: Banks MP for 3-5 turns, builds a back-row Support
shield wall, then unleashes a single overwhelming Scout cannon when
shields are stacked. Sees big MP swings on the resource trace. Citadel
upgraded-Support-meta archetype.

**Signature features**:
- Mean MP at spawn: **HIGH** (≥10 — visible hoarding).
- Average attack-wave size: **HIGH** (≥15 mobile units in one turn).
- Per-turn breach rate: **SPIKY** (low until burst, then very high).

## 5. `TURTLE_GRIND`

**Plain English**: Almost no offense; pours SP into stacked walls and
upgraded turrets, lets the opponent suicide into the defense, wins
turn-100 HP tiebreaks. Annoying, MP often goes unspent.

**Signature features**:
- Total mobile spawns / turn: **VERY LOW** (<3 per turn).
- Per-turn breach rate against us: **VERY LOW** (≤0.2).
- Mean MP at spawn: **HIGH** (chronic hoarding — they never spend).

## 6. `BALANCED`

**Plain English**: Mixed offense (Scouts + Demolishers + occasional
Interceptors), no extreme behavior on any axis. The catch-all bucket;
includes most "vanilla python-algo" variants and any opponent the
clustering doesn't separate cleanly. Long-tail safe class.

**Signature features**:
- All features near corpus median.
- Use as the prior bias when posterior is flat.

---

## Mapping rationale (for Phase 3 task 2 labeling)

The features are computed from the action-frame fingerprints. Features
are computed in `features.py` and consumed by the heuristic labeler
(task 2) and the trained classifier (task 4). The labeler thresholds
are derived from the corpus quantiles and documented in `labels.json`
metadata.

Long-tail caveat: with 47 replays / 30 opponents, several archetypes
will have <5 training samples. The classifier reports calibrated
posterior probabilities so Phase 4 can downweight low-confidence
predictions; it does not gate on minimum-sample sufficiency.
