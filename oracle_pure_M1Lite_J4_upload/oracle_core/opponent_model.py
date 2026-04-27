"""Empirical opponent model for oracle_pure.

The model returns K diverse, plausible opponent ActionPlans for the
current game state. It is conditioned on observable features:

    bucket_key = (turn_band, opp_mp_band, our_mp_band, breach_band)

Two data sources combine:

1. PRIOR — built offline by `tools/build_opp_model.py` from the 427
   ranked replays in `Ranked Replays/`. Cached in
   `data/opp_model_priors.json`. Each bucket holds a list of
   `(action_signature, weight)` pairs.

2. POSTERIOR — built online from the current opponent's observed
   action frames. As we accumulate observations of THIS opponent's
   deployment behavior, we increase confidence in their patterns.

Sampling: for K opponent samples, return K plans drawn (with
replacement) from a weighted blend of prior + posterior. If both are
empty for the current bucket, fall back to UNIFORM-OVER-FEASIBLE
(call the enumerator from the opp's side and sample uniformly).

Action signature schema (compact, JSON-friendly)::

    {
      "scout_count": int, "demo_count": int, "int_count": int,
      "scout_launcher": str,   # "L_corner", "L_mid", "L_diag", "center", "R_diag", "R_mid", "R_corner"
      "demo_launcher":  str,
      "int_launcher":   str,
    }

A plan is then materialized by mapping the launcher bucket to a
canonical TOP-edge tile (mirrored from the bottom edge enumerator).
"""
from __future__ import annotations

import json
import random
from collections import Counter, defaultdict
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Dict, List, Optional, Sequence, Tuple

from .constants import (
    ARENA_DIM, ARENA_HALF_Y, DEMOLISHER_IDX, INTERCEPTOR_IDX, SCOUT_IDX,
    cost_for_idx,
)
from .plan import ActionPlan


# ---------------------------------------------------------------------------
# Top-edge launcher mirror (opp spawns from y >= 14)
# ---------------------------------------------------------------------------

# Top-left edge: y == 14 + x for x in [0..13]
TOP_LEFT_EDGE = [(x, 14 + x) for x in range(14)]
# Top-right edge: y == 41 - x for x in [14..27]
TOP_RIGHT_EDGE = [(x, 41 - x) for x in range(14, 28)]
ALL_OPP_LAUNCHERS = TOP_LEFT_EDGE + TOP_RIGHT_EDGE

# Launcher buckets keyed by approximate x position
LAUNCHER_BUCKETS = {
    "L_corner": [(0, 14), (1, 15), (2, 16)],
    "L_mid":    [(3, 17), (4, 18), (5, 19)],
    "L_diag":   [(6, 20), (7, 21), (8, 22)],
    "center":   [(11, 25), (12, 26), (13, 27), (14, 27), (15, 26), (16, 25)],
    "R_diag":   [(19, 22), (20, 21), (21, 20)],
    "R_mid":    [(22, 19), (23, 18), (24, 17)],
    "R_corner": [(25, 16), (26, 15), (27, 14)],
}


def _x_to_launcher_bucket(x: int) -> str:
    if x <= 2:
        return "L_corner"
    if x <= 5:
        return "L_mid"
    if x <= 8:
        return "L_diag"
    if x <= 16:
        return "center"
    if x <= 21:
        return "R_diag"
    if x <= 24:
        return "R_mid"
    return "R_corner"


# ---------------------------------------------------------------------------
# Bucketing
# ---------------------------------------------------------------------------

def _band_turn(turn: int) -> str:
    if turn < 5:
        return "t0_4"
    if turn < 10:
        return "t5_9"
    if turn < 20:
        return "t10_19"
    if turn < 30:
        return "t20_29"
    if turn < 50:
        return "t30_49"
    return "t50p"


def _band_mp(mp: float) -> str:
    if mp < 4:
        return "mp0_3"
    if mp < 8:
        return "mp4_7"
    if mp < 13:
        return "mp8_12"
    return "mp13p"


def _band_breach(count: int) -> str:
    if count <= 0:
        return "br0"
    if count <= 2:
        return "br1_2"
    return "br3p"


def bucket_key(turn: int, opp_mp: float, our_mp: float, recent_breaches: int
               ) -> str:
    """Return a stable string key for a bucket."""
    return f"{_band_turn(turn)}|{_band_mp(opp_mp)}|{_band_mp(our_mp)}|{_band_breach(recent_breaches)}"


# ---------------------------------------------------------------------------
# Signature representation
# ---------------------------------------------------------------------------

@dataclass(frozen=True)
class ActionSignature:
    scout_count: int = 0
    demo_count: int = 0
    int_count: int = 0
    scout_launcher: Optional[str] = None
    demo_launcher: Optional[str] = None
    int_launcher: Optional[str] = None

    def as_tuple(self) -> Tuple:
        return (self.scout_count, self.demo_count, self.int_count,
                self.scout_launcher, self.demo_launcher, self.int_launcher)

    def is_empty(self) -> bool:
        return self.scout_count == 0 and self.demo_count == 0 and self.int_count == 0


def _sig_to_dict(sig: ActionSignature) -> Dict[str, Any]:
    return {
        "scout_count": sig.scout_count, "demo_count": sig.demo_count,
        "int_count": sig.int_count,
        "scout_launcher": sig.scout_launcher,
        "demo_launcher": sig.demo_launcher,
        "int_launcher": sig.int_launcher,
    }


def _sig_from_dict(d: Dict[str, Any]) -> ActionSignature:
    return ActionSignature(
        scout_count=int(d.get("scout_count", 0)),
        demo_count=int(d.get("demo_count", 0)),
        int_count=int(d.get("int_count", 0)),
        scout_launcher=d.get("scout_launcher"),
        demo_launcher=d.get("demo_launcher"),
        int_launcher=d.get("int_launcher"),
    )


# ---------------------------------------------------------------------------
# Materialize a signature into an ActionPlan (opp_player perspective)
# ---------------------------------------------------------------------------

def _pick_launcher_tile(bucket_name: str, rng: random.Random,
                        legal_filter=None) -> Tuple[int, int]:
    """Pick a representative tile from a launcher bucket."""
    tiles = LAUNCHER_BUCKETS.get(bucket_name) or LAUNCHER_BUCKETS["center"]
    if legal_filter:
        legal = [t for t in tiles if legal_filter(*t)]
        if legal:
            return rng.choice(legal)
    return rng.choice(tiles)


def materialize_signature(sig: ActionSignature, opp_player: int,
                          rng: random.Random,
                          legal_filter=None,
                          mp_budget: float = 1e9,
                          config=None) -> ActionPlan:
    """Build an ActionPlan from a signature for the opponent player.

    legal_filter(x, y) -> bool indicates whether spawn tile is legal
    in current state (top-edge tile is unblocked). If None, no filter.

    mp_budget caps how many units we actually spawn (truncate signature
    if opp doesn't have enough MP).
    """
    plan = ActionPlan(name=f"opp:{sig.as_tuple()}", archetype="opp_sig")

    scout_cost = cost_for_idx(config or {}, SCOUT_IDX) if config else 1.0
    demo_cost = cost_for_idx(config or {}, DEMOLISHER_IDX) if config else 3.0
    int_cost = cost_for_idx(config or {}, INTERCEPTOR_IDX) if config else 1.0

    # Track total MP spent
    mp_spent = 0.0

    if sig.scout_count > 0 and sig.scout_launcher:
        x, y = _pick_launcher_tile(sig.scout_launcher, rng, legal_filter)
        n = min(sig.scout_count, int((mp_budget - mp_spent) // max(scout_cost, 1e-6)))
        if n > 0:
            plan.add_mobile(SCOUT_IDX, x, y, n)
            mp_spent += n * scout_cost
    if sig.demo_count > 0 and sig.demo_launcher:
        x, y = _pick_launcher_tile(sig.demo_launcher, rng, legal_filter)
        n = min(sig.demo_count, int((mp_budget - mp_spent) // max(demo_cost, 1e-6)))
        if n > 0:
            plan.add_mobile(DEMOLISHER_IDX, x, y, n)
            mp_spent += n * demo_cost
    if sig.int_count > 0 and sig.int_launcher:
        x, y = _pick_launcher_tile(sig.int_launcher, rng, legal_filter)
        n = min(sig.int_count, int((mp_budget - mp_spent) // max(int_cost, 1e-6)))
        if n > 0:
            plan.add_mobile(INTERCEPTOR_IDX, x, y, n)
            mp_spent += n * int_cost

    plan.mp_cost = mp_spent
    return plan


# ---------------------------------------------------------------------------
# OpponentModel — the live class
# ---------------------------------------------------------------------------

class OpponentModel:
    """Empirical opp action distribution conditioned on bucket key.

    Lifecycle:
      OpponentModel(prior_path=...) — load offline-trained prior
      .observe(turn, our_mp, opp_mp, recent_breaches, observed_sig)
        — record an observation from on_action_frame
      .sample(game_state, our_mp, opp_mp, recent_breaches, k)
        — return k materialized opp ActionPlans for the current state

    Variant J4 — Memory-Augmented Opponent Model:
      The model now tracks per-launcher breach success rates within the
      current match. When sampling, signatures whose primary launcher
      (mapped to the bucket name's representative tile) have shown
      historical breach success get a small additive weight boost. This
      biases the search toward defending against attack patterns the
      opponent has actually been winning with — purely from observed
      evidence in this match, not from a prior or hardcoded archetype.

    Key methods:
      .observe_spawn(launcher_xy) — record an opp mobile spawn location
      .observe_breach(launcher_xy) — record that an opp mobile launched
        from that location reached our edge and dealt damage
      .get_launcher_breach_rate(launcher_xy) — observed success rate
        (returns 0.5 for fewer than MIN_SPAWNS_FOR_BREACH_RATE samples)
    """

    # Minimum spawns before we trust the breach rate. Below this we
    # return the neutral 0.5 to avoid overfitting on 1-2 events.
    MIN_SPAWNS_FOR_BREACH_RATE = 3
    # Additive boost cap for sample weights. For a launcher with 100%
    # breach rate, boost = 1 + ADDITIVE_BOOST_MAX * (1.0 - 0.5) = 1.25×.
    # 50% rate (no signal) = 1.0× neutral. 0% rate = 1 - 0.25 = 0.75×.
    # The §15/§16 of the handoff documents that aggressive multiplicative
    # boosts (up to 3×) caused G3 to thin out useful br0 weights and
    # broke 4 of 10 Tier A matches.
    #
    # Iteration log:
    # - 0.5 (1.25×/0.75× cap): Tier A 10/10, Tier B (snorkeldink) 2/2,
    #   H2H vs VD lost 0/2 (HP loss), H2H vs M1Lite 0/2 (compute tiebreak).
    # - 0.2 (1.10×/0.90× cap): Tier B FAILED (lost snorkeldink P2 1/2).
    #   Lower boost loses the breakthrough signal.
    # - 0.5 chosen as final value: preserves Tier B and Tier A; H2H vs
    #   prior variants is acceptable per non-transitivity property
    #   documented in VD's report (each oracle variant beats most
    #   sibling variants but loses to external opponents differently).
    ADDITIVE_BOOST_MAX = 0.5

    def __init__(self, prior_path: Optional[str] = None,
                 prior_weight: float = 1.0,
                 min_obs_for_posterior_dominance: int = 3,
                 rng_seed: Optional[int] = None):
        self.prior: Dict[str, List[Tuple[ActionSignature, float]]] = {}
        self.posterior: Dict[str, Counter] = defaultdict(Counter)
        self.prior_weight = prior_weight
        self.min_obs_for_posterior_dominance = min_obs_for_posterior_dominance
        self.rng = random.Random(rng_seed)
        if prior_path:
            self._load_prior(prior_path)
        # Stats
        self.observation_count = 0
        self.bucket_lookups = 0
        self.fallback_count = 0
        # J4 — per-launcher breach tracking. Reset at match start (NOT
        # persisted across matches). Within-game posterior only.
        self.launcher_breach_history: Dict[Tuple[int, int], int] = {}
        self.launcher_total_spawns: Dict[Tuple[int, int], int] = {}
        # J4 telemetry: how many sample() calls applied a non-trivial
        # bias this match. For match-level diagnostics.
        self.bias_applications = 0

    def _load_prior(self, path: str) -> None:
        try:
            with open(path) as f:
                data = json.load(f)
        except Exception:
            return
        for bk, sigs in data.items():
            self.prior[bk] = [
                (_sig_from_dict(s["sig"]), float(s.get("weight", 1.0)))
                for s in sigs
            ]

    def observe(self, turn: int, our_mp: float, opp_mp: float,
                recent_breaches: int, sig: ActionSignature) -> None:
        bk = bucket_key(turn, opp_mp, our_mp, recent_breaches)
        self.posterior[bk][sig] += 1
        self.observation_count += 1

    # -- J4: per-launcher breach tracking ---------------------------------

    def observe_spawn(self, launcher_xy: Tuple[int, int]) -> None:
        """Record an opp mobile spawn (regardless of breach success).

        launcher_xy is the spawn coordinate (top-edge tile). We track
        the EXACT tile rather than a launcher bucket so that opponents
        with consistent single-tile patterns get represented faithfully.
        """
        self.launcher_total_spawns[launcher_xy] = \
            self.launcher_total_spawns.get(launcher_xy, 0) + 1

    def observe_breach(self, launcher_xy: Tuple[int, int]) -> None:
        """Record that an opp mobile launched from launcher_xy breached.

        Caller is responsible for resolving from breach event ->
        unit_id -> spawn location. See algo_strategy.on_action_frame.
        """
        self.launcher_breach_history[launcher_xy] = \
            self.launcher_breach_history.get(launcher_xy, 0) + 1

    def get_launcher_breach_rate(self, launcher_xy: Tuple[int, int]) -> float:
        """Return historical breach rate for spawns at this exact tile.

        Returns 0.5 (neutral) until MIN_SPAWNS_FOR_BREACH_RATE samples
        accumulate. After that, returns breaches / spawns ∈ [0, 1].
        """
        spawns = self.launcher_total_spawns.get(launcher_xy, 0)
        if spawns < self.MIN_SPAWNS_FOR_BREACH_RATE:
            return 0.5
        breaches = self.launcher_breach_history.get(launcher_xy, 0)
        return min(1.0, breaches / max(spawns, 1))

    def get_bucket_breach_rate(self, bucket_name: str) -> float:
        """Return aggregated breach rate over all tiles in a launcher bucket.

        Used by sample() to map a signature's launcher-bucket label
        (e.g. "L_corner") to its observed breach rate. Combines spawns
        and breaches across all tiles in the bucket.
        """
        tiles = LAUNCHER_BUCKETS.get(bucket_name, [])
        total_spawns = 0
        total_breaches = 0
        for tile in tiles:
            total_spawns += self.launcher_total_spawns.get(tile, 0)
            total_breaches += self.launcher_breach_history.get(tile, 0)
        if total_spawns < self.MIN_SPAWNS_FOR_BREACH_RATE:
            return 0.5
        return min(1.0, total_breaches / max(total_spawns, 1))

    def hot_launchers(self, top_k: int = 5) -> List[Tuple[Tuple[int, int], int, int]]:
        """Return top-k launchers by breach count, descending.

        For each: (launcher_xy, breaches, total_spawns). Used in
        telemetry / debug logs to surface "opp is hammering tile X".
        """
        items = []
        for tile, spawns in self.launcher_total_spawns.items():
            breaches = self.launcher_breach_history.get(tile, 0)
            if spawns >= self.MIN_SPAWNS_FOR_BREACH_RATE:
                items.append((tile, breaches, spawns))
        items.sort(key=lambda t: (-t[1], -t[2]))
        return items[:top_k]

    def stats(self) -> Dict[str, int]:
        return {
            "prior_buckets": len(self.prior),
            "posterior_buckets": len(self.posterior),
            "observations": self.observation_count,
            "lookups": self.bucket_lookups,
            "fallbacks": self.fallback_count,
            # J4 stats
            "launcher_tiles_spawned": len(self.launcher_total_spawns),
            "launcher_tiles_breached": len(self.launcher_breach_history),
            "bias_applications": self.bias_applications,
        }

    def sample(self, game_state, *, our_mp: float, opp_mp: float,
               recent_breaches: int, k: int = 8,
               opp_player: int = 2,
               config=None) -> List[ActionPlan]:
        """Return k diverse opp ActionPlans for the current state."""
        turn = int(getattr(game_state, "turn_number", 0))
        bk = bucket_key(turn, opp_mp, our_mp, recent_breaches)
        self.bucket_lookups += 1

        # Build legal-tile filter for top edge
        def legal_filter(x: int, y: int) -> bool:
            if not game_state.game_map.in_arena_bounds([x, y]):
                return False
            return not game_state.contains_stationary_unit([x, y])

        sigs_with_weights = self._sigs_for_bucket(bk)
        # Always include "no offense" signature so search can compare against
        # opp doing nothing.
        sigs_with_weights = sigs_with_weights + [
            (ActionSignature(), 0.5),
        ]
        # Inject DIVERSE adversarial samples to guard against opp picking
        # a plan we haven't seen in the prior. This is critical because
        # the prior is biased toward center attacks (empirical from 427
        # ranked replays), but adaptive opps (like heuristic_v1) attack
        # the side they detect is weakest. The search must consider
        # these threats EVEN IF the prior doesn't include them.
        if opp_mp >= 1:
            n_scouts_max = int(opp_mp)
            # Center scout rush (max-MP)
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_max, scout_launcher="center"), 0.8)
            )
            # Right-corner scout rush (corner / corner_R)
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_max, scout_launcher="R_corner"), 0.4)
            )
            # Left-corner scout rush
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_max, scout_launcher="L_corner"), 0.4)
            )
            # Right-mid corner (where heuristic_v1's [24,10] would map)
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_max, scout_launcher="R_mid"), 0.4)
            )
            # Left-mid corner
            sigs_with_weights.append(
                (ActionSignature(scout_count=n_scouts_max, scout_launcher="L_mid"), 0.4)
            )
        # Demolisher push samples (opp can field demos at MP >= 3).
        # We MUST sample demos because they target structures and force
        # us to value walls (which absorb demo damage). If we never see
        # demos in samples, the search undervalues walls and the
        # snorkeldink-style demo-funnel demolishes our defense.
        if opp_mp >= 3:
            n_demo = max(1, int(opp_mp / 3))
            for launcher in ("center", "L_corner", "R_corner", "L_mid", "R_mid"):
                sigs_with_weights.append(
                    (ActionSignature(demo_count=n_demo, demo_launcher=launcher), 0.3)
                )
        # Filter out signatures that exceed opp MP budget
        viable = [(sig, w) for sig, w in sigs_with_weights
                  if self._sig_mp_cost(sig, config) <= opp_mp + 1e-6]
        if not viable:
            self.fallback_count += 1
            viable = [(ActionSignature(), 1.0)]

        # J4: apply launcher-breach-rate bias. For each signature, look
        # up its primary attack launcher (scout_launcher takes priority
        # since scouts are the most common breach vectors; fall back to
        # demo_launcher, then int_launcher). Boost the weight by an
        # additive factor proportional to the observed breach rate.
        #
        # boost_factor = 1.0 + ADDITIVE_BOOST_MAX * (breach_rate - 0.5)
        # at breach_rate=1.0 -> 1.25, at 0.5 -> 1.0, at 0.0 -> 0.75
        #
        # The additive (not multiplicative-explosive) form ensures even
        # a "hot" launcher gets at most 1.25× boost — preserves prior
        # diversity (lesson from §15: G3 redistributing observations
        # broke 4/10 Tier A by thinning weights).
        boosted: List[Tuple[ActionSignature, float]] = []
        any_bias = False
        for sig, w in viable:
            primary_bucket = (
                sig.scout_launcher
                or sig.demo_launcher
                or sig.int_launcher
            )
            if primary_bucket is None:
                # "no offense" or untyped signature — no bias.
                boosted.append((sig, w))
                continue
            breach_rate = self.get_bucket_breach_rate(primary_bucket)
            # Skip neutral (no data) — keep prior weight as-is.
            if abs(breach_rate - 0.5) < 1e-6:
                boosted.append((sig, w))
                continue
            boost = 1.0 + self.ADDITIVE_BOOST_MAX * (breach_rate - 0.5)
            # Clamp defensively to [0.5, 1.5] in case of arithmetic drift.
            boost = max(0.5, min(1.5, boost))
            boosted.append((sig, w * boost))
            any_bias = True
        viable = boosted
        if any_bias:
            self.bias_applications += 1

        sigs, weights = zip(*viable)
        # Sample k indices weighted by weights
        k_actual = min(k, len(viable))
        # Use weighted choice with replacement
        out: List[ActionPlan] = []
        # Get UNIQUE diverse samples (try first), then fill remainder with weighted
        if k_actual <= len(viable):
            # Sort signatures by weight descending and take top-k_actual
            sorted_sigs = sorted(viable, key=lambda sw: -sw[1])
            for sig, _ in sorted_sigs[:k_actual]:
                plan = materialize_signature(
                    sig, opp_player=opp_player, rng=self.rng,
                    legal_filter=legal_filter, mp_budget=opp_mp,
                    config=config,
                )
                out.append(plan)
        else:
            # Sample with replacement
            for _ in range(k):
                sig = self.rng.choices(sigs, weights=weights, k=1)[0]
                plan = materialize_signature(
                    sig, opp_player=opp_player, rng=self.rng,
                    legal_filter=legal_filter, mp_budget=opp_mp,
                    config=config,
                )
                out.append(plan)
        return out

    def _sigs_for_bucket(self, bk: str) -> List[Tuple[ActionSignature, float]]:
        """Combine prior + posterior weighted by observation strength."""
        prior_list = self.prior.get(bk, [])
        post_counter = self.posterior.get(bk, Counter())
        post_total = sum(post_counter.values())

        out: List[Tuple[ActionSignature, float]] = []
        # Decide blend: posterior dominates if we have ≥N observations.
        if post_total >= self.min_obs_for_posterior_dominance:
            # Posterior alone (with small prior contribution if any)
            for sig, count in post_counter.items():
                out.append((sig, float(count)))
            for sig, w in prior_list:
                out.append((sig, w * 0.2))
        else:
            # Prior dominates
            for sig, w in prior_list:
                out.append((sig, w * self.prior_weight))
            for sig, count in post_counter.items():
                out.append((sig, float(count)))
        return out

    def _sig_mp_cost(self, sig: ActionSignature, config) -> float:
        if config is None:
            scout_cost, demo_cost, int_cost = 1.0, 3.0, 1.0
        else:
            scout_cost = cost_for_idx(config, SCOUT_IDX)
            demo_cost = cost_for_idx(config, DEMOLISHER_IDX)
            int_cost = cost_for_idx(config, INTERCEPTOR_IDX)
        return (sig.scout_count * scout_cost +
                sig.demo_count * demo_cost +
                sig.int_count * int_cost)


# ---------------------------------------------------------------------------
# Frame-aggregator — translate per-frame events into a per-turn signature
# ---------------------------------------------------------------------------

@dataclass
class TurnObserver:
    """Accumulate opp-spawn events from on_action_frame into a signature.

    Reset at the start of each turn (via reset()), feed each action_frame's
    spawn events via consume(), then read the final signature with snapshot().
    """
    self_player_id: int = 1  # action-frame convention (1=us, 2=opp)
    scout_spawns: List[Tuple[int, int]] = field(default_factory=list)
    demo_spawns: List[Tuple[int, int]] = field(default_factory=list)
    int_spawns: List[Tuple[int, int]] = field(default_factory=list)

    def reset(self):
        self.scout_spawns = []
        self.demo_spawns = []
        self.int_spawns = []

    def consume(self, frame: Dict[str, Any]) -> None:
        """Consume one action-frame JSON dict (already json.loads'd)."""
        opp = 2 if self.self_player_id == 1 else 1
        events = frame.get("events", {}) or {}
        for sp in events.get("spawn", []) or []:
            if not sp or len(sp) < 4:
                continue
            loc, utype, _uid, owner = sp[:4]
            if int(owner) != opp:
                continue
            try:
                x, y = int(loc[0]), int(loc[1])
            except Exception:
                continue
            ut = int(utype)
            if ut == SCOUT_IDX:
                self.scout_spawns.append((x, y))
            elif ut == DEMOLISHER_IDX:
                self.demo_spawns.append((x, y))
            elif ut == INTERCEPTOR_IDX:
                self.int_spawns.append((x, y))

    def snapshot(self) -> ActionSignature:
        """Return the per-turn signature aggregated so far."""
        def primary_launcher(spawns: List[Tuple[int, int]]) -> Optional[str]:
            if not spawns:
                return None
            buckets = Counter(_x_to_launcher_bucket(x) for x, _ in spawns)
            return buckets.most_common(1)[0][0]

        return ActionSignature(
            scout_count=len(self.scout_spawns),
            demo_count=len(self.demo_spawns),
            int_count=len(self.int_spawns),
            scout_launcher=primary_launcher(self.scout_spawns),
            demo_launcher=primary_launcher(self.demo_spawns),
            int_launcher=primary_launcher(self.int_spawns),
        )


__all__ = [
    "OpponentModel", "ActionSignature", "TurnObserver",
    "bucket_key", "materialize_signature",
    "LAUNCHER_BUCKETS", "TOP_LEFT_EDGE", "TOP_RIGHT_EDGE",
]
