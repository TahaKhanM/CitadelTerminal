"""Athena v3 — full integrated algo (Phase 5).

Composes:
  - DefensePlanner (Phase 2, six primitives in `planner.defense`)
  - OffensePlanner (Phase 4, beam search in `planner.offense`)
  - OpponentModel (Phase 3, ArchetypeClassifier + ActionPredictor) — optional
  - Phase 0.5 action-frame trackers (BreachLocationTracker is wired here;
    others can be added when their consumers land)
  - EconomyArbiter (Phase 5, `planner.economy`) — the per-turn orchestrator
  - Watchdog (Phase 4, `planner.budget`)

Production safety wrappers (lifted from `algos/athena_baseline_lostkids/`):
  - 13 s SIGALRM watchdog around `on_turn`
  - top-level try/except
  - safe-fallback minimal turret defense on any exception or watchdog fire
"""
from __future__ import annotations

import json
import os
import random
import signal
import sys
import threading
import time
import traceback
from sys import maxsize
from typing import Optional

import gamelib

# Make the local subpackages importable when the engine launches us.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

# Local imports
from offense.state_adapter import augment_snapshot_for_simcore  # noqa: E402
from opponent.action_frame_utils import BreachLocationTracker  # noqa: E402
from planner.budget import BudgetExceeded, Watchdog  # noqa: E402
from planner.economy import EconomyArbiter  # noqa: E402

# Phase 3 OpponentModel — best-effort fit at game start.
try:
    from opponent.classifier import ArchetypeClassifier, fit_default_classifier  # noqa: E402
    from opponent.action_predictor import ActionPredictor  # noqa: E402

    _OPPONENT_MODEL_AVAILABLE = True
except Exception:  # noqa: BLE001
    _OPPONENT_MODEL_AVAILABLE = False


# --- Production safety: turn-time watchdog (lifted from Lostkids) -----------
TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    """Arm a SIGALRM watchdog. Returns (disarm, fired_check) tuple.

    Falls back to a thread-Timer if SIGALRM is unavailable. Lifted from
    `algos/athena_baseline_lostkids/algo_strategy.py`.
    """
    if hasattr(signal, "SIGALRM"):
        try:
            old_handler = signal.signal(signal.SIGALRM, _sigalrm_handler)
            signal.alarm(seconds)

            def disarm_sigalrm():
                signal.alarm(0)
                signal.signal(signal.SIGALRM, old_handler)

            return disarm_sigalrm, lambda: False
        except (ValueError, OSError):
            pass
    fired = {"v": False}

    def fire():
        fired["v"] = True

    timer = threading.Timer(seconds, fire)
    timer.daemon = True
    timer.start()

    def disarm_thread():
        timer.cancel()

    return disarm_thread, lambda: fired["v"]


# --- Defense archetype options ---------------------------------------------
_ARCHETYPES = {
    "v_funnel": "v_funnel.json",
    "two_layer_keep": "two_layer_keep.json",
    "spread_line": "spread_line.json",
    "v13_inspired": "v13_inspired.json",
}
_DEFAULT_ARCHETYPE = "v13_inspired"  # Phase 5B: switch default to the v13 skeleton.


def _archetype_path(name: str) -> str:
    return os.path.join(
        _HERE, "defenses", _ARCHETYPES.get(name, _ARCHETYPES[_DEFAULT_ARCHETYPE]),
    )


def _snapshot_path() -> str:
    return os.path.join(_HERE, "data", "citadel_config_snapshot.json")


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write(f"[athena] random seed: {seed}")
        self.config = None
        self.archetype = _DEFAULT_ARCHETYPE
        self.archetype_file = _archetype_path(self.archetype)
        self.snapshot_file = _snapshot_path()
        self.breach_tracker = BreachLocationTracker(self_player_id=1)
        # Action-frame buffer for feature extraction (Phase 5B).
        # Capped to MAX_FRAME_BUFFER to bound memory; extract_features
        # is monotone-ish in the trackers so the cap is safe.
        self._action_frames: list = []
        self.arbiter: Optional[EconomyArbiter] = None
        self.turret_shorthand = "DF"

    MAX_FRAME_BUFFER = 5000  # ~50 turns at 100 frames/turn — well under any match

    def on_game_start(self, config):
        self.config = config

        # Idempotent: ensure SimCore-required keys are in the vendored
        # config snapshot. No-op if already present (Phase 5 milestone A
        # patched the file at build time, but this guards against fresh
        # checkouts / regenerations).
        try:
            changed = augment_snapshot_for_simcore(self.snapshot_file)
            if changed:
                gamelib.debug_write(
                    "[athena] augmented config snapshot with SimCore keys"
                )
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(f"[athena] augment_snapshot failed: {exc!r}")

        try:
            self.turret_shorthand = config["unitInformation"][2]["shorthand"]
        except Exception:  # noqa: BLE001
            self.turret_shorthand = "DF"

        # Phase 5B: Wire the OpponentModel (best-effort).
        # If the corpus npz / labels.json aren't present, fall through to
        # opponent_classifier=None — the arbiter handles that cleanly.
        opponent_classifier = None
        action_predictor = None
        if _OPPONENT_MODEL_AVAILABLE:
            try:
                npz_path = os.path.join(_HERE, "data", "opponent_features.npz")
                labels_path = os.path.join(_HERE, "opponent", "labels.json")
                if os.path.isfile(npz_path):
                    opponent_classifier = fit_default_classifier(npz_path)
                    gamelib.debug_write(
                        "[athena] ArchetypeClassifier fitted from "
                        f"{os.path.basename(npz_path)}"
                    )
                if os.path.isfile(labels_path):
                    action_predictor = ActionPredictor().fit_from_labels_json(
                        labels_path,
                    )
                    gamelib.debug_write(
                        "[athena] ActionPredictor fitted from labels.json"
                    )
            except Exception as exc:  # noqa: BLE001
                gamelib.debug_write(
                    f"[athena] OpponentModel fit failed: {exc!r} "
                    "— falling back to no-classifier path"
                )
                opponent_classifier = None
                action_predictor = None

        # Create the EconomyArbiter once. With opponent_classifier and
        # action_predictor both set, the arbiter will:
        #   - update the archetype posterior each turn (Milestone E1)
        #   - feed top-3 opponent actions into beam_search (Milestone E2)
        # If either is None the arbiter falls back to the heuristic path.
        self.arbiter = EconomyArbiter(
            config=self.config,
            archetype_path=self.archetype_file,
            snapshot_path=self.snapshot_file,
            breach_tracker=self.breach_tracker,
            opponent_classifier=opponent_classifier,
            action_predictor=action_predictor,
            watchdog=Watchdog(),
            debug_log_func=gamelib.debug_write,
        )
        # Expose the action-frame buffer to the arbiter so _update_posterior
        # can call extract_features(self._action_frames, opp_pid=2).
        self.arbiter.action_frame_buffer = self._action_frames
        gamelib.debug_write(
            f"[athena] on_game_start; archetype={self.archetype} "
            f"turret_sh={self.turret_shorthand}"
        )

    def on_turn(self, turn_state):
        """Wrapped in 13s watchdog + try/except + safe-fallback."""
        start = time.time()
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        game_state = None
        used_fallback = False
        try:
            game_state = gamelib.GameState(self.config, turn_state)
            if self.arbiter is None:
                # Defensive: shouldn't happen — on_game_start sets it.
                self._safe_fallback_turn(game_state)
            else:
                self.arbiter.execute(game_state)
            game_state.submit_turn()
        except _TurnTimeout:
            gamelib.debug_write(
                f"[athena] watchdog fired after {TURN_WATCHDOG_SECONDS}s "
                "— safe fallback"
            )
            used_fallback = True
        except BudgetExceeded as exc:
            gamelib.debug_write(
                f"[athena] BudgetExceeded (component={exc.component}, "
                f"elapsed={exc.elapsed_ms:.1f}ms cap={exc.cap_ms:.1f}ms) "
                "— safe fallback"
            )
            used_fallback = True
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                f"[athena] on_turn exception: {exc!r}\n{traceback.format_exc()}"
            )
            used_fallback = True
        finally:
            disarm()

        if used_fallback:
            try:
                if game_state is None:
                    game_state = gamelib.GameState(self.config, turn_state)
                self._safe_fallback_turn(game_state)
                game_state.submit_turn()
            except Exception as exc2:  # noqa: BLE001
                gamelib.debug_write(
                    f"[athena] safe-fallback also failed: {exc2!r}"
                )
                try:
                    if game_state is not None:
                        game_state.submit_turn()
                except Exception:  # noqa: BLE001
                    pass

        if fired() and not used_fallback:
            gamelib.debug_write(
                f"[athena] watchdog flag set but turn finished in "
                f"{time.time() - start:.2f}s"
            )

    # ------------------------------------------------------------------
    # Safe-fallback (lifted from Lostkids/defense-only variant)
    # ------------------------------------------------------------------

    _SAFE_FALLBACK_TURRETS = ((2, 13), (25, 13), (3, 13), (24, 13))
    _SP_IDX = 0

    def _safe_fallback_turn(self, game_state):
        try:
            sp = game_state.get_resource(self._SP_IDX, 0)
        except Exception:  # noqa: BLE001
            sp = 0.0
        for loc in self._SAFE_FALLBACK_TURRETS:
            try:
                cost = game_state.type_cost(self.turret_shorthand)[self._SP_IDX]
            except Exception:  # noqa: BLE001
                cost = 2.0
            if sp < cost:
                break
            try:
                spawned = game_state.attempt_spawn(
                    self.turret_shorthand, list(loc),
                )
            except Exception:  # noqa: BLE001
                spawned = 0
            if spawned:
                sp -= cost

    # ------------------------------------------------------------------
    # Action-frame: feed Phase 0.5 trackers
    # ------------------------------------------------------------------

    def on_action_frame(self, turn_string):
        try:
            frame = json.loads(turn_string)
            self.breach_tracker.consume_action_frame(frame)
            # Buffer for Phase 5B feature extraction. Bound by
            # MAX_FRAME_BUFFER — we drop oldest frames if exceeded.
            self._action_frames.append(frame)
            if len(self._action_frames) > self.MAX_FRAME_BUFFER:
                # Drop ~10% to amortize cost
                drop = self.MAX_FRAME_BUFFER // 10
                del self._action_frames[:drop]
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                f"[athena] on_action_frame exception: {exc!r}"
            )


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
