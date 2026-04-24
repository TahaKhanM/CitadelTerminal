"""Athena v3 planner — DEFENSE-ONLY variant.

Phase 2 validation harness: composes the six defense primitives in
``planner.defense`` plus the BreachLocationTracker from
``opponent.action_frame_utils``. Spawns NO offense (no Scouts/
Demolishers/Interceptors) — the gate is "defense holds long enough to
not immediately fold."

Production safety wrappers (try/except + 13s SIGALRM watchdog +
safe-fallback) lifted verbatim from
``algos/athena_baseline_lostkids/algo_strategy.py``.
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

import gamelib

# Make the local subpackages importable when the engine launches us.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

from planner.defense import (  # noqa: E402
    SP_IDX,
    build_default_defences,
    edge_block_and_remove,
    max_heap_repair,
    probabilistic_placement,
    reactive_to_breach,
    refund_low_health_structures,
)
from opponent.action_frame_utils import BreachLocationTracker  # noqa: E402


# --- Production safety: turn-time watchdog --------------------------------
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


# Archetype options. Default is V-funnel (Lostkids-derived); future
# Phase 3+ opponent classifier will pick one based on opponent profile.
_ARCHETYPES = {
    "v_funnel": "v_funnel.json",
    "two_layer_keep": "two_layer_keep.json",
    "spread_line": "spread_line.json",
}
_DEFAULT_ARCHETYPE = "v_funnel"


def _archetype_path(name: str) -> str:
    return os.path.join(_HERE, "defenses", _ARCHETYPES.get(name, _ARCHETYPES[_DEFAULT_ARCHETYPE]))


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write("[athena-defense-only] random seed: {}".format(seed))
        self.config = None
        self.archetype = _DEFAULT_ARCHETYPE
        self.archetype_file = _archetype_path(self.archetype)
        self.breach_tracker = BreachLocationTracker(self_player_id=1)
        self.turret_shorthand = "DF"  # filled in on_game_start

    def on_game_start(self, config):
        self.config = config
        try:
            self.turret_shorthand = config["unitInformation"][2]["shorthand"]
        except Exception:  # noqa: BLE001
            self.turret_shorthand = "DF"
        gamelib.debug_write(
            "[athena-defense-only] on_game_start; archetype={} turret_sh={}".format(
                self.archetype, self.turret_shorthand
            )
        )

    def on_turn(self, turn_state):
        """Wrapped in 13s watchdog + try/except + safe-fallback."""
        start = time.time()
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        game_state = None
        used_fallback = False
        try:
            game_state = gamelib.GameState(self.config, turn_state)
            self._defense_turn(game_state)
            game_state.submit_turn()
        except _TurnTimeout:
            gamelib.debug_write(
                "[athena-defense-only] watchdog fired after {}s — safe fallback".format(
                    TURN_WATCHDOG_SECONDS
                )
            )
            used_fallback = True
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                "[athena-defense-only] on_turn exception: {!r}\n{}".format(
                    exc, traceback.format_exc()
                )
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
                    "[athena-defense-only] safe-fallback also failed: {!r}".format(exc2)
                )
                try:
                    if game_state is not None:
                        game_state.submit_turn()
                except Exception:  # noqa: BLE001
                    pass

        if fired() and not used_fallback:
            gamelib.debug_write(
                "[athena-defense-only] watchdog flag set but turn finished in {:.2f}s".format(
                    time.time() - start
                )
            )

    # --- Defense planner --------------------------------------------------

    def _defense_turn(self, game_state):
        """Execute the Phase 2 defense pipeline.

        Order matters:
          1. build_default_defences         — establish archetype baseline
          2. refund_low_health_structures   — drop near-dead structures
          3. max_heap_repair                — queue most-damaged for refund
          4. probabilistic_placement        — only if SP > 4 (keeps a buffer)
          5. edge_block_and_remove          — same-turn edge wall + remove
          6. reactive_to_breach             — patch breach hot tiles
        """
        # 1. archetype build
        try:
            build_default_defences(game_state, self.archetype_file, config=self.config)
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write("[athena-defense-only] build_default_defences: {!r}".format(exc))

        # 2. refund low-HP structures (Lostkids thresholds)
        try:
            refund_low_health_structures(
                game_state, wall_threshold=0.5, turret_threshold=0.3, config=self.config
            )
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write("[athena-defense-only] refund_low_health: {!r}".format(exc))

        # 3. FUNNEL-style max-heap repair
        try:
            max_heap_repair(game_state, config=self.config)
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write("[athena-defense-only] max_heap_repair: {!r}".format(exc))

        # 4. probabilistic placement (only if we have SP headroom)
        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
        except Exception:  # noqa: BLE001
            sp = 0.0
        if sp > 4.0:
            try:
                probabilistic_placement(
                    game_state,
                    support_weight=25.0,
                    n_samples=120,
                    config=self.config,
                )
            except Exception as exc:  # noqa: BLE001
                gamelib.debug_write(
                    "[athena-defense-only] probabilistic_placement: {!r}".format(exc)
                )

        # 5. edge block + remove (Lostkids same-turn trick)
        try:
            edge_block_and_remove(game_state, config=self.config)
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write("[athena-defense-only] edge_block_and_remove: {!r}".format(exc))

        # 6. reactive patch from breach tracker
        try:
            reactive_to_breach(game_state, self.breach_tracker, threshold=1.0, config=self.config)
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write("[athena-defense-only] reactive_to_breach: {!r}".format(exc))

    # --- Safe-fallback minimal defense (lifted from Lostkids) -------------

    _SAFE_FALLBACK_TURRETS = ((2, 13), (25, 13), (3, 13), (24, 13))

    def _safe_fallback_turn(self, game_state):
        try:
            sp = game_state.get_resource(SP_IDX, 0)
        except Exception:  # noqa: BLE001
            sp = 0.0
        for loc in self._SAFE_FALLBACK_TURRETS:
            try:
                cost = game_state.type_cost(self.turret_shorthand)[SP_IDX]
            except Exception:  # noqa: BLE001
                cost = 2
            if sp < cost:
                break
            try:
                spawned = game_state.attempt_spawn(self.turret_shorthand, list(loc))
            except Exception:  # noqa: BLE001
                spawned = 0
            if spawned:
                sp -= cost

    # --- Action-frame: feed BreachLocationTracker ------------------------

    def on_action_frame(self, turn_string):
        try:
            frame = json.loads(turn_string)
            self.breach_tracker.consume_action_frame(frame)
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                "[athena-defense-only] on_action_frame exception: {!r}".format(exc)
            )


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
