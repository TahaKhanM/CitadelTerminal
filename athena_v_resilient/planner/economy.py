"""EconomyArbiter — Phase 5 milestone B.

Top-level per-turn orchestrator that composes:
  - Phase 0.5 action-frame trackers (already wired in `algo_strategy.py`).
  - Phase 3 OpponentModel (ArchetypeClassifier + ActionPredictor) — optional.
  - Phase 2 DefensePlanner (six primitives in `planner.defense`).
  - Phase 4 OffensePlanner (templates + beam_search).
  - Phase 4 Watchdog from `planner.budget`.

Per-turn flow inside ``on_turn``:
  1. ``Watchdog.start_turn()`` — reset all timers.
  2. (optional) opponent-posterior update — bounded by 'Opponent posterior'
     soft cap (500ms).
  3. DefensePlanner sequence — bounded by 'DefensePlanner' soft cap (1.5s):
        build_default_defences -> refund_low_health_structures
        -> max_heap_repair -> probabilistic_placement (when SP > 4)
        -> edge_block_and_remove -> reactive_to_breach.
  4. OffensePlanner — bounded by 'OffensePlanner' soft cap (8s):
        adapt_game_state -> generate_candidates -> beam_search
        (with sim rollouts when sim_rs is available).
  5. Spawn the chosen offense plan via ``attempt_spawn``.

On any exception or BudgetExceeded the arbiter raises into the algo's
top-level try/except, which uses the safe-fallback path.

The arbiter does NOT call ``submit_turn()`` — the algo strategy handles
that (so the safe-fallback can also submit).
"""
from __future__ import annotations

import os
import sys
import time
from typing import Any, Dict, List, Optional, Tuple


def _synthetic_opp_actions(
    opp_mp: float, turn: int,
) -> List[Tuple[Dict[str, Any], float]]:
    """Static fallback opp_actions when the predictor isn't useful.

    Used when ActionPredictor.top_k returns only the NONE fallback (a
    common state when labels.json doesn't contain state-conditioned
    distributions, as observed in the 4 athena_baseline ranked-loss
    replays — 13ms median compute = 1 sim per candidate against a
    passive opponent only).

    Returns a distribution of 6-9 plausible opponent moves covering
    common archetypes (scout rush, demo train, passive). Probabilities
    sum to ~1.0 so beam_search's weighted-utility computation is
    well-formed.

    The set scales with opp_mp: at low MP we only include cheap-MP
    actions (corner Scout); at high MP we include heavy waves.
    """
    actions: List[Tuple[Dict[str, Any], float]] = []

    # Shorthand: SCOUT=3, DEMOLISHER=4, INTERCEPTOR=5
    # Edge: TR (opponent's right = our right edge), TL (opp's left = our left)
    # wave_size_bucket strings come from the action_predictor's vocab.

    # Re-weighted post-Athena_loss replay: less aggressive opp distribution.
    # Previously gave 8-14 wave_size_bucket (10 Scouts) 36% probability
    # combined, which made our interceptor_screen the highest-utility
    # plan because sim said "interceptors stop incoming 10-Scout wave".
    # Real opponents at this ELO don't always send max-size waves — many
    # are hoarding, doing demo trains, or attacking different edges.
    # Down-weight the heavy-attack predictions to ~25% combined and add
    # more passive/light-attack mass.
    if opp_mp >= 8.0:
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TR",
                         "wave_size_bucket": "8-14", "spend_mp": True}, 0.10))
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TL",
                         "wave_size_bucket": "8-14", "spend_mp": True}, 0.10))
        actions.append(({"primary_mobile_type": 4, "primary_edge": "TR",
                         "wave_size_bucket": "1-3", "spend_mp": True}, 0.08))
        actions.append(({"primary_mobile_type": 4, "primary_edge": "TL",
                         "wave_size_bucket": "1-3", "spend_mp": True}, 0.08))

    if opp_mp >= 4.0:
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TR",
                         "wave_size_bucket": "4-7", "spend_mp": True}, 0.10))
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TL",
                         "wave_size_bucket": "4-7", "spend_mp": True}, 0.10))

    if opp_mp >= 1.0:
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TR",
                         "wave_size_bucket": "1-3", "spend_mp": True}, 0.07))
        actions.append(({"primary_mobile_type": 3, "primary_edge": "TL",
                         "wave_size_bucket": "1-3", "spend_mp": True}, 0.07))

    # Heavy weight on passive — opp often hoards, especially mid-game.
    actions.append(({"primary_mobile_type": -1, "primary_edge": "NONE",
                     "wave_size_bucket": "0", "spend_mp": False}, 0.30))

    # Normalize so weights sum to 1.0.
    total = sum(p for _, p in actions)
    if total > 0:
        actions = [(a, p / total) for a, p in actions]
    return actions

# These imports are absolute relative to the algo's vendored package layout.
# They work both inside `algos/athena_v3_planner/` (when imported as
# `algos.athena_v3_planner.planner.economy`) and inside the variant
# folders that vendor a copy of `planner/` and `offense/`.

from .budget import BudgetExceeded, Watchdog
from .defense import (
    SP_IDX,
    anti_demo_hardening,
    build_default_defences,
    edge_block_and_remove,
    max_heap_repair,
    probabilistic_placement,
    reactive_to_breach,
    refund_low_health_structures,
)


# --- Live-replay-driven adaptive triggers ---
# These are the heuristics the live replays motivated:
# 1) anti-demo trigger: switch to forward Y=12 turrets when opp demos appear.
# 2) heavy-wall trigger: override scout offense with demolishers when opp
#    front-line walls are dense enough that scouts won't breach.
ANTI_DEMO_TRIGGER_COUNT = 3      # opp Demos in last 3 turns
ANTI_DEMO_TRIGGER_TURNS = 3
HEAVY_WALL_OVERRIDE_THRESHOLD = 18    # opp walls in y∈[14,17]
HEAVY_WALL_OVERRIDE_MAX_TURRETS = 6   # opp turrets in y∈[14,17] — ABOVE this means heavy turrets, demos die
HEAVY_WALL_OVERRIDE_MIN_MP = 9        # 3 demos = 9 MP
DEMOLISHER_UNIT_INDEX = 4             # in events.spawn frames


def _count_recent_opp_demolishers(action_frame_buffer, current_turn, n_turns):
    """Scan the action-frame buffer for opp's Demolisher spawns in the
    last ``n_turns`` turns. Returns 0 on empty buffer or any error."""
    if not action_frame_buffer:
        return 0
    threshold_turn = max(0, current_turn - n_turns)
    count = 0
    for frame in action_frame_buffer:
        try:
            ti = frame.get("turnInfo", [])
            if len(ti) < 2:
                continue
            if ti[1] < threshold_turn:
                continue
            for s in frame.get("events", {}).get("spawn", []) or []:
                if (
                    isinstance(s, list) and len(s) >= 4
                    and s[1] == DEMOLISHER_UNIT_INDEX
                    and s[3] == 2
                ):
                    count += 1
        except Exception:  # noqa: BLE001 — defensive over malformed frames
            continue
    return count


def _count_opp_front_structures(game_state):
    """Count opponent's WALL and TURRET units in their forward rows y∈[14,17].

    These are the structures that block our scout paths. Above 17 is too
    far back to matter for athena's offense; below 14 is in our half.
    Returns (wall_count, turret_count)."""
    walls = 0
    turrets = 0
    for x in range(28):
        for y in range(14, 18):
            try:
                u = game_state.contains_stationary_unit([x, y])
            except Exception:  # noqa: BLE001
                continue
            if not u:
                continue
            if getattr(u, "player_index", -1) != 1:
                continue
            unit_type = getattr(u, "unit_type", "")
            if unit_type == "FF":
                walls += 1
            elif unit_type == "DF":
                turrets += 1
    return walls, turrets


class EconomyArbiter:
    """Per-turn orchestrator. Created once at on_game_start, then
    `execute(game_state)` is called from on_turn.
    """

    def __init__(
        self,
        config: Dict[str, Any],
        *,
        archetype_path: str,
        snapshot_path: str,
        breach_tracker=None,
        opponent_classifier=None,
        action_predictor=None,
        watchdog: Optional[Watchdog] = None,
        # Tuning knobs — all overridable for tests / MAP-Elites
        offense_min_mp: float = 1.0,
        probabilistic_sp_floor: float = 4.0,
        probabilistic_support_weight: float = 25.0,
        probabilistic_n_samples: int = 120,
        wall_refund_threshold: float = 0.5,
        turret_refund_threshold: float = 0.3,
        breach_reactive_threshold: float = 1.0,
        # Phase 6 milestone K: MAP-Elites archive (loaded lazily, degrade
        # gracefully if missing/corrupt).
        archive_path: Optional[str] = None,
        archive_sample_k: int = 10,
        # Phase 6B milestone N — archive policy decision (Path B).
        #
        # The Phase 6B bestof 20 validation found:
        #   no-gate (Phase 6A behavior, threshold=0.0):
        #     vs v13:      15-5  LB 0.531  (+0.23 vs Phase 5B)
        #     vs Lostkids: 14-6  LB 0.481  (−0.36 vs Phase 5B)
        #   gate=0.6 (Path C confidence gate):
        #     vs v13:       8-12 LB 0.219  (−0.08 vs Phase 5B)
        #     vs Lostkids: 10-10 LB 0.299  (−0.54 vs Phase 5B)
        #
        # Both gate policies regress vs Phase 5B's 20-0 Lostkids (LB
        # 0.839). Per the Phase 6B brief's hard constraint ("Never let
        # Athena's effective performance drop below Phase 5B's"), we
        # ship Phase 6 with the archive integration in place but
        # **disabled by default** (threshold=1.01 = always-gated).
        #
        # To re-enable for experimentation:
        #   archive_confidence_threshold=0.0 → Phase 6A always-on (best vs v13)
        #   archive_confidence_threshold=0.6 → Path C confidence gate
        #
        # Phase 6C followup: re-tune the fitness harness (extend to
        # 25+ rounds, add per-archetype cells, add MP-efficiency
        # tiebreakers) so the archive's elites cover Lostkids' V-funnel
        # pattern. Once that lands, lower the threshold back to 0.6 (or
        # 0.0) and re-validate.
        archive_confidence_threshold: float = 1.01,
        debug_log_func=None,
    ):
        self.config = config
        self.archetype_path = archetype_path
        self.snapshot_path = snapshot_path
        self.breach_tracker = breach_tracker
        self.opponent_classifier = opponent_classifier
        self.action_predictor = action_predictor
        self.watchdog = watchdog or Watchdog()
        self.offense_min_mp = float(offense_min_mp)
        self.probabilistic_sp_floor = float(probabilistic_sp_floor)
        self.probabilistic_support_weight = float(probabilistic_support_weight)
        self.probabilistic_n_samples = int(probabilistic_n_samples)
        self.wall_refund_threshold = float(wall_refund_threshold)
        self.turret_refund_threshold = float(turret_refund_threshold)
        self.breach_reactive_threshold = float(breach_reactive_threshold)
        self.debug_log = debug_log_func or (lambda msg: None)

        # Posterior over archetypes — uniform until first update.
        self._posterior: Optional[Dict[str, float]] = None
        if opponent_classifier is not None:
            try:
                self._posterior = opponent_classifier.uniform_prior()
            except Exception:  # noqa: BLE001
                self._posterior = None

        # Phase 5B: action-frame buffer hook. Set externally by
        # algo_strategy to a list of already-parsed action frames; the
        # _update_posterior method runs extract_features over it each
        # turn. None means no posterior update is possible.
        self.action_frame_buffer: Optional[List[Dict[str, Any]]] = None

        self.turn_count = 0
        # Diagnostics: count of turns where opp_actions_top_k was populated
        # vs empty. Used by smoke tests and the Phase 5B 90 %% mid-game
        # invariant in the brief.
        self._opp_actions_populated_turns: int = 0
        self._opp_actions_empty_turns: int = 0

        # Phase 6 milestone K: MAP-Elites archive loaded once at game start.
        self.archive_sample_k = int(archive_sample_k)
        # Phase 6B milestone N — Path C confidence gate threshold.
        self.archive_confidence_threshold = float(archive_confidence_threshold)
        # Diagnostics: count of turns where archive was consulted vs
        # gated off by low classifier confidence.
        self._archive_gated_turns: int = 0
        self._archive_consulted_turns: int = 0
        self._archive = None
        self._archive_rng = None
        if archive_path:
            try:
                # Lazy import — keeps economy.py importable when the
                # archive subpackage is absent (older worktrees).
                try:
                    from archive.archive import MAPElitesArchive  # type: ignore
                except ImportError:
                    from ..archive.archive import MAPElitesArchive  # type: ignore
                import os
                if os.path.exists(archive_path):
                    self._archive = MAPElitesArchive.deserialize(archive_path)
                    import random as _random
                    self._archive_rng = _random.Random(0xA7C71)
                    self.debug_log(
                        f"[arbiter] map-elites archive loaded: "
                        f"{self._archive.coverage()[0]}/"
                        f"{self._archive.coverage()[1]} cells"
                    )
                else:
                    self.debug_log(
                        f"[arbiter] map-elites archive not found at "
                        f"{archive_path}; falling back to JSON templates."
                    )
            except Exception as exc:  # noqa: BLE001
                self.debug_log(
                    f"[arbiter] map-elites archive load failed: {exc!r}; "
                    "falling back to JSON templates."
                )
                self._archive = None
                self._archive_rng = None

    # ------------------------------------------------------------------
    # Public API
    # ------------------------------------------------------------------

    def execute(self, game_state) -> None:
        """Run one full turn. Caller wraps in a watchdog + try/except.

        Raises ``BudgetExceeded`` (caller catches and falls back) on
        budget violation. All other component exceptions are caught
        and logged inside the relevant phase so a single misbehaving
        primitive doesn't poison the whole turn.
        """
        self.turn_count += 1
        self.watchdog.start_turn()

        # ---- 1. Opponent posterior (best-effort) ----
        try:
            self.watchdog.begin("Opponent posterior")
            self._update_posterior(game_state)
            self.watchdog.check("Opponent posterior")
        except BudgetExceeded:
            raise
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] posterior update soft-fail: {exc!r}")

        # ---- 2. Defense pipeline ----
        try:
            self.watchdog.begin("DefensePlanner")
            self._defense_phase(game_state)
            self.watchdog.check("DefensePlanner")
        except BudgetExceeded:
            raise
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] defense soft-fail: {exc!r}")

        # ---- 3. Offense pipeline ----
        try:
            self.watchdog.begin("OffensePlanner")
            self._offense_phase(game_state)
            self.watchdog.check("OffensePlanner")
        except BudgetExceeded:
            raise
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] offense soft-fail: {exc!r}")

    # ------------------------------------------------------------------
    # Component phases
    # ------------------------------------------------------------------

    def _update_posterior(self, game_state) -> None:
        """Phase 5B: feed action-frame buffer through Phase 3 features ->
        ArchetypeClassifier and update the running posterior.

        Mechanics:
          - The classifier was fit at on_game_start over the labeled
            corpus.
          - extract_features() runs the Phase 0.5 trackers over the
            buffered action frames, producing the 14-dim vector the
            classifier expects.
          - We call ``predict_proba`` on the cumulative feature vector
            each turn (cumulative is correct: features are themselves
            running aggregates, not a per-turn delta), and treat the
            posterior as ``predict_proba`` directly (NOT
            ``update_posterior`` — that double-counts when called over
            the cumulative trackers).

        Throttling: only re-extract once per turn. Bail early if no
        frames or no classifier. Soft-fail on any exception so the
        offense pipeline still runs.
        """
        if self.opponent_classifier is None or self._posterior is None:
            return
        if not self.action_frame_buffer:
            return
        try:
            # Local import — keeps top-level economy.py importable when
            # opponent/ subpackage isn't available.
            try:
                from opponent.features import extract_features  # type: ignore
            except ImportError:
                from ..opponent.features import extract_features  # type: ignore

            # opp_pid=2 because the algo runs as player 1 from
            # gamelib's POV (raw JSON convention 1=us, 2=opp).
            features = extract_features(
                self.action_frame_buffer, opponent_player_id=2,
            )
            # If no opponent activity yet (turns_observed == 0), keep
            # the uniform prior — predict_proba on all-zero features
            # returns garbage.
            if features.get("turns_observed", 0.0) <= 0.0:
                return
            self._posterior = self.opponent_classifier.predict_proba(features)
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] posterior compute soft-fail: {exc!r}")

    def _defense_phase(self, game_state) -> None:
        """Compose the six defense primitives in order."""
        try:
            build_default_defences(
                game_state, self.archetype_path, config=self.config,
            )
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] build_default_defences: {exc!r}")

        try:
            refund_low_health_structures(
                game_state,
                wall_threshold=self.wall_refund_threshold,
                turret_threshold=self.turret_refund_threshold,
                config=self.config,
            )
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] refund_low_health_structures: {exc!r}")

        try:
            max_heap_repair(game_state, config=self.config)
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] max_heap_repair: {exc!r}")

        # Anti-demo trigger: if opp's recent Demolisher count is high,
        # place forward Y=12 turrets BEFORE probabilistic_placement consumes
        # leftover SP. Each anti-demo turret puts demos at Y=14 in range —
        # base turrets at Y=11 cannot reach Y=14.
        try:
            recent_demos = _count_recent_opp_demolishers(
                self.action_frame_buffer,
                self.turn_count,
                ANTI_DEMO_TRIGGER_TURNS,
            )
            if recent_demos >= ANTI_DEMO_TRIGGER_COUNT:
                placed = anti_demo_hardening(game_state, config=self.config)
                if placed:
                    self.debug_log(
                        f"[arbiter] anti_demo_hardening placed {placed} "
                        f"(opp_demos_3turns={recent_demos})"
                    )
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] anti_demo_hardening: {exc!r}")

        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
        except Exception:  # noqa: BLE001
            sp = 0.0
        if sp > self.probabilistic_sp_floor:
            try:
                probabilistic_placement(
                    game_state,
                    support_weight=self.probabilistic_support_weight,
                    n_samples=self.probabilistic_n_samples,
                    config=self.config,
                )
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] probabilistic_placement: {exc!r}")

        try:
            edge_block_and_remove(game_state, config=self.config)
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] edge_block_and_remove: {exc!r}")

        try:
            reactive_to_breach(
                game_state, self.breach_tracker,
                threshold=self.breach_reactive_threshold,
                config=self.config,
            )
        except Exception as exc:  # noqa: BLE001
            self.debug_log(f"[arbiter] reactive_to_breach: {exc!r}")

    def _offense_phase(self, game_state) -> None:
        """Pick offense via the robust multi-tier planner; execute the plan.

        The new planner (planner.offense_planner.pick_offense_plan)
        guarantees a non-empty deploy list whenever MP >= 1.0. Each
        internal failure is logged and the next tier kicks in:

          T1 sim-evaluated beam search (full sim_rs rollouts)
            ↓ on any exception or empty-result
          T2 heuristic beam search (templates + state, no sim)
            ↓ on any exception or empty-result
          T3 rule-based MP-aware (turn + state-derived enemy strength)
            ↓ on any exception
          T4 hardcoded (alternating-side scouts, can't fail)

        Replaces the prior _offense_phase which had multiple silent-return
        paths (adapt_game_state crash → no offense; generate_candidates
        crash → no offense; beam_search crash → no offense; hoard pick →
        no offense). Live-ladder analysis (3+ uploads, ~13 games) showed
        every loss was driven by the silent-return path firing every turn.
        """
        try:
            from .offense_planner import pick_offense_plan  # type: ignore
        except ImportError:
            from planner.offense_planner import pick_offense_plan  # type: ignore

        try:
            from offense.state_adapter import adapt_game_state  # type: ignore
        except ImportError:
            try:
                from ..offense.state_adapter import adapt_game_state  # type: ignore
            except ImportError:
                adapt_game_state = None  # type: ignore

        MP_IDX = 1
        try:
            mp_avail = float(game_state.get_resource(MP_IDX, 0))
        except Exception:  # noqa: BLE001
            mp_avail = 0.0
        if mp_avail < 1.0:
            # Tier 4 needs at least 1 MP to spawn anything.
            return

        # Build the sim_rs-ready state dict from gamelib. If this fails
        # the planner falls through to tier 3 (state-free rule-based).
        state_dict = None
        if adapt_game_state is not None:
            try:
                state_dict = adapt_game_state(
                    game_state, my_player=1, turn=self.turn_count,
                )
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] adapt_game_state: {exc!r}")
                # state_dict stays None → tier 3+ takes over

        # Phase 6B confidence-gated MAP-Elites archive (preserved).
        archive_for_this_turn = None
        if self._archive is not None:
            posterior_max = 0.0
            if self._posterior:
                try:
                    posterior_max = max(self._posterior.values())
                except Exception:  # noqa: BLE001
                    posterior_max = 0.0
            if posterior_max > self.archive_confidence_threshold:
                archive_for_this_turn = self._archive
                self._archive_consulted_turns += 1
            else:
                self._archive_gated_turns += 1

        # Opponent action top-k (Phase 5B). Soft-fails on any error.
        opp_actions: List[Tuple[Dict[str, Any], float]] = []
        try:
            opp_mp = float(game_state.get_resource(MP_IDX, 1))
        except Exception:  # noqa: BLE001
            opp_mp = 0.0
        if self.action_predictor is not None and self._posterior is not None:
            try:
                # athena_baseline: k=30 (max realistic opp-response set).
                # Combined with ~1000-candidate beam, target is ~30,000
                # sims/turn = ~50% sim_rs budget utilization.
                opp_actions = self.action_predictor.top_k(
                    {"mp": opp_mp, "turn": self.turn_count},
                    self._posterior, k=30,
                )
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] action_predictor.top_k: {exc!r}")
                opp_actions = []

        # Loss-replay diagnosis (4 ranked games against athena_baseline):
        # action_predictor.top_k returns only [(NONE, 1.0)] — the labels.json
        # file has replay-level archetype labels but NO state-conditioned
        # action counts, so _counts is empty for every archetype, marginal
        # is empty, top_k always returns the NONE fallback. Net effect:
        # beam_search ran against a passive opponent (1 sim per candidate
        # ≈ 12 ms/turn). The 30,000-sims/turn throttle never engaged.
        #
        # Fix: when the predictor returns <3 real (non-NONE) actions,
        # AUGMENT with a static distribution of plausible opponent moves.
        # Beam search now sims our plans against a realistic opponent
        # spread instead of a single passive opponent.
        n_real = sum(
            1 for a, _ in opp_actions
            if int(a.get("primary_mobile_type", -1)) >= 0
        )
        if n_real < 3:
            opp_actions = _synthetic_opp_actions(opp_mp, self.turn_count)

        if 10 <= self.turn_count <= 90:
            if opp_actions:
                self._opp_actions_populated_turns += 1
            else:
                self._opp_actions_empty_turns += 1

        # Budget for offense.
        rem = max(50.0, self.watchdog.remaining_ms() - 1000.0)
        offense_budget = min(8000.0, rem)

        # Run the robust planner. NEVER returns empty when mp_avail >= 1.0.
        plan = pick_offense_plan(
            state_dict, mp_avail,
            turn=self.turn_count,
            posterior=self._posterior,
            opp_actions_top_k=opp_actions,
            config_path=self.snapshot_path,
            archive=archive_for_this_turn,
            budget_ms=offense_budget,
            log_fn=self.debug_log,
        )

        if not plan.deploys:
            # Should be unreachable when mp_avail >= 1.0, but defend
            # against contract bugs.
            self.debug_log(
                f"[arbiter] planner returned empty plan (mp={mp_avail:.1f}, "
                f"tier={plan.tier}); spawning emergency scout"
            )
            try:
                game_state.attempt_spawn(
                    self.config["unitInformation"][3]["shorthand"], [13, 0], 1,
                )
            except Exception:  # noqa: BLE001
                pass
            return

        # Heavy-wall override: when opp has dense walls but FEW turrets in
        # their front, the beam search's scout-flood plans bounce off
        # (scouts can't damage walls; they die to turrets, but here turrets
        # are few). Replace the plan with a Demolisher train + scout
        # follow-up. Demos chew walls; scouts breach the gap.
        # NOTE: trigger requires LOW turret count specifically because
        # demos die fast vs heavy turret defense (e.g. v13_second_ring's
        # turret line). Heavy-walls + heavy-turrets is NOT this condition.
        try:
            opp_walls_front, opp_turrets_front = _count_opp_front_structures(game_state)
        except Exception:  # noqa: BLE001
            opp_walls_front, opp_turrets_front = 0, 0
        # Heavy-wall + low-turret triggers the override.
        if (
            opp_walls_front >= HEAVY_WALL_OVERRIDE_THRESHOLD
            and opp_turrets_front <= HEAVY_WALL_OVERRIDE_MAX_TURRETS
            and mp_avail >= HEAVY_WALL_OVERRIDE_MIN_MP
        ):
            override_deploys: List[Tuple[str, Tuple[int, int]]] = []
            n_demos = 3  # 9 MP for the demo train
            for _ in range(n_demos):
                override_deploys.append(("DEMOLISHER", (4, 9)))
            scout_mp = max(0, int(mp_avail - n_demos * 3))
            for _ in range(scout_mp):
                override_deploys.append(("SCOUT", (3, 10)))
            self.debug_log(
                f"[arbiter] heavy-wall override: opp_walls={opp_walls_front}, "
                f"mp={mp_avail:.1f}, plan={n_demos}D+{scout_mp}S"
            )
            self._execute_offense_plan_from_deploys(
                game_state, override_deploys, plan.tier
            )
            return

        self._execute_offense_plan_from_deploys(game_state, plan.deploys, plan.tier)

    def _execute_offense_plan(self, game_state, candidate) -> None:
        """Spawn each unit in the candidate's deploy list."""
        # Map unit-name -> shorthand via runtime config (canonical).
        scout_sh = self.config["unitInformation"][3]["shorthand"]
        demo_sh = self.config["unitInformation"][4]["shorthand"]
        intercept_sh = self.config["unitInformation"][5]["shorthand"]
        name_to_sh = {
            "SCOUT": scout_sh,
            "DEMOLISHER": demo_sh,
            "INTERCEPTOR": intercept_sh,
        }

        spawned = 0
        for unit_name, loc in candidate.deploys:
            sh = name_to_sh.get(str(unit_name).upper(), scout_sh)
            try:
                n = game_state.attempt_spawn(sh, list(loc))
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] attempt_spawn failed: {exc!r}")
                n = 0
            if not n:
                # MP exhausted or path blocked — stop the cascade.
                break
            spawned += n

        # Phase 5B: surface the most-likely archetype (top-1) when posterior is set.
        top_arch = "?"
        if self._posterior:
            try:
                top_arch = max(self._posterior.items(), key=lambda kv: kv[1])[0]
            except Exception:  # noqa: BLE001
                top_arch = "?"
        self.debug_log(
            f"[arbiter] turn={self.turn_count} pick={candidate.name} "
            f"deploys={len(candidate.deploys)} spawned={spawned} "
            f"EU={candidate.expected_utility:.2f} sims={candidate.sim_count} "
            f"opp_arch={top_arch}"
        )

    def _execute_offense_plan_from_deploys(
        self,
        game_state,
        deploys,
        tier: int = 0,
    ) -> int:
        """Spawn each (unit_name, (x,y)) in ``deploys``.

        Differs from ``_execute_offense_plan`` in that:
          - Takes a raw deploys list (from the multi-tier offense_planner)
            instead of a Candidate object.
          - Does NOT break the cascade on a single attempt_spawn failure.
            Live ladder analysis showed a single early failure (e.g. one
            blocked tile) was killing the entire offense for the turn.
            We now skip-and-continue so subsequent deploys at unblocked
            tiles still fire.

        Returns total units spawned. Logs to debug for live diagnostics.
        """
        scout_sh = self.config["unitInformation"][3]["shorthand"]
        demo_sh = self.config["unitInformation"][4]["shorthand"]
        intercept_sh = self.config["unitInformation"][5]["shorthand"]
        name_to_sh = {
            "SCOUT": scout_sh,
            "DEMOLISHER": demo_sh,
            "INTERCEPTOR": intercept_sh,
        }

        spawned_total = 0
        attempted = 0
        skipped = 0
        for unit_name, loc in deploys:
            sh = name_to_sh.get(str(unit_name).upper(), scout_sh)
            attempted += 1
            try:
                n = game_state.attempt_spawn(sh, list(loc))
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] attempt_spawn {sh}@{loc!r}: {exc!r}")
                n = 0
            if n:
                spawned_total += int(n)
            else:
                skipped += 1

        self.debug_log(
            f"[arbiter] T{self.turn_count} TIER{tier} attempted={attempted} "
            f"spawned={spawned_total} skipped={skipped}"
        )
        return spawned_total


__all__ = ["EconomyArbiter"]
