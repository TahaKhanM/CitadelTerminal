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

# These imports are absolute relative to the algo's vendored package layout.
# They work both inside `algos/athena_v3_planner/` (when imported as
# `algos.athena_v3_planner.planner.economy`) and inside the variant
# folders that vendor a copy of `planner/` and `offense/`.

from .budget import BudgetExceeded, Watchdog
from .defense import (
    SP_IDX,
    build_default_defences,
    edge_block_and_remove,
    max_heap_repair,
    probabilistic_placement,
    reactive_to_breach,
    refund_low_health_structures,
)


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

        # ----------------------------------------------------------------
        # SIM_RS LOAD VERIFICATION SIGNATURE — multi-mode diagnostic
        # ----------------------------------------------------------------
        # On engine turn 0, encode the sim_rs load outcome as a unique
        # spawn tile location. Each tile is on the BL spawn-edge but
        # NEVER used by any fallback path (tier 3/4/last_resort), so the
        # turn-0 spawn in any replay uniquely identifies the load state:
        #
        #   (12, 1) → SUCCESS: bundled .so loaded
        #   (11, 2) → ImportError on bundled .so dlopen (Python ABI / glibc)
        #   (10, 3) → bundled dir or .so file missing from zip
        #   (9, 4)  → platform mismatch (not Linux x86_64)
        #   default → no diagnostic; sim_rs path is "untried" (defensive)
        #
        # NOTE: game_state.turn_number is the engine's authoritative turn
        # counter (starts at 0); self.turn_count is incremented at the
        # start of arb.execute so it'd be wrong here.
        #
        # This is a temporary diagnostic; removed after live result.
        try:
            engine_turn = int(getattr(game_state, "turn_number", -1))
        except Exception:  # noqa: BLE001
            engine_turn = -1
        if engine_turn == 0:
            try:
                try:
                    from offense.sim_eval import (  # type: ignore
                        SIGNATURE_TILE,
                        SIGNATURE_TILE_IMPORT_ERROR,
                        SIGNATURE_TILE_FILE_MISSING,
                        SIGNATURE_TILE_PLATFORM_MISMATCH,
                        _get_sim_rs,
                    )
                except ImportError:
                    from ..offense.sim_eval import (  # type: ignore
                        SIGNATURE_TILE,
                        SIGNATURE_TILE_IMPORT_ERROR,
                        SIGNATURE_TILE_FILE_MISSING,
                        SIGNATURE_TILE_PLATFORM_MISMATCH,
                        _get_sim_rs,
                    )
                _get_sim_rs()  # force load attempt (lazy by default)
                try:
                    from offense.sim_eval import (  # type: ignore  # noqa: F811
                        _SIM_RS_LOAD_PATH, _SIM_RS_LOAD_ERROR,
                        _SIM_RS_LOAD_DETAILS,
                    )
                except ImportError:
                    from ..offense.sim_eval import (  # type: ignore  # noqa: F811
                        _SIM_RS_LOAD_PATH, _SIM_RS_LOAD_ERROR,
                        _SIM_RS_LOAD_DETAILS,
                    )

                # Map load_path → diagnostic tile.
                tile = None
                if _SIM_RS_LOAD_PATH == "bundled":
                    tile = SIGNATURE_TILE
                elif _SIM_RS_LOAD_PATH == "import_error":
                    tile = SIGNATURE_TILE_IMPORT_ERROR
                elif _SIM_RS_LOAD_PATH == "file_missing":
                    tile = SIGNATURE_TILE_FILE_MISSING
                elif _SIM_RS_LOAD_PATH == "platform_mismatch":
                    tile = SIGNATURE_TILE_PLATFORM_MISMATCH
                # "conda" / "untried" / "failed" → no signature (use
                # normal flow). "conda" only happens locally; live
                # server can never be "conda". "untried"/"failed" are
                # defensive states that shouldn't occur after our forced
                # _get_sim_rs() call above.

                self.debug_log(
                    f"[athena DIAG] T0 sim_rs load: path={_SIM_RS_LOAD_PATH} "
                    f"err={_SIM_RS_LOAD_ERROR!r} "
                    f"details={_SIM_RS_LOAD_DETAILS}"
                )

                if tile is not None:
                    scout_sh = self.config["unitInformation"][3]["shorthand"]
                    n = game_state.attempt_spawn(scout_sh, list(tile))
                    self.debug_log(
                        f"[athena DIAG] T0 SIGNATURE @ {tile}: "
                        f"path={_SIM_RS_LOAD_PATH} spawned={n}"
                    )
                    if n > 0:
                        return  # MP=0 now, normal offense skipped
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[athena DIAG] T0 SIGNATURE error: {exc!r}")
                # Continue to normal offense on any error.
        # ----------------------------------------------------------------

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
        if self.action_predictor is not None and self._posterior is not None:
            try:
                try:
                    opp_mp = float(game_state.get_resource(MP_IDX, 1))
                except Exception:  # noqa: BLE001
                    opp_mp = 0.0
                opp_actions = self.action_predictor.top_k(
                    {"mp": opp_mp, "turn": self.turn_count},
                    self._posterior, k=3,
                )
            except Exception as exc:  # noqa: BLE001
                self.debug_log(f"[arbiter] action_predictor.top_k: {exc!r}")
                opp_actions = []

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
