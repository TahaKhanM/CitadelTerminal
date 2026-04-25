"""asymmetric_defender — adversarial sparring partner targeting Athena's
static defense archetypes (`v_funnel`, `two_layer_keep`, `spread_line`,
`v13_inspired` are all symmetric — they assume the opponent can attack
from either side equally).

Profile:
  * Heavy left side: 10+ upgraded turrets at x ∈ [0, 9].
  * Thin right side: only 2 turrets at x ∈ [22, 27].
  * Forces Athena's offense to commit one way or the other:
    - if Athena attacks LEFT, it crashes into a wall of upgraded turrets;
    - if Athena attacks RIGHT, this opponent's surplus left structure can
      absorb hits that come back through.
  * Per-game seed flips which side is heavy (alternates every match).

Production safety wrappers — see anti_athena_hoarder.
"""
from __future__ import annotations

import json
import os
import random
import signal
import sys
import threading
import traceback
from sys import maxsize

import gamelib

_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)


TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
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


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        # Flip which side is heavy.
        self.heavy_left = bool(random.randint(0, 1))
        self.scored_on = []

    def on_game_start(self, config):
        self.config = config
        ui = config["unitInformation"]
        global WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR, MP, SP
        WALL = ui[0]["shorthand"]
        SUPPORT = ui[1]["shorthand"]
        TURRET = ui[2]["shorthand"]
        SCOUT = ui[3]["shorthand"]
        DEMOLISHER = ui[4]["shorthand"]
        INTERCEPTOR = ui[5]["shorthand"]
        MP = 1
        SP = 0

    def on_turn(self, turn_state):
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            self._main_turn(gs)
            gs.submit_turn()
        except (_TurnTimeout, Exception):  # noqa: BLE001
            try:
                gamelib.debug_write("asymmetric_defender: turn failed -- safe fallback")
                gamelib.debug_write(traceback.format_exc())
            except Exception:  # noqa: BLE001
                pass
            try:
                gs = gamelib.GameState(self.config, turn_state)
                gs.suppress_warnings(True)
                self._safe_fallback(gs)
                gs.submit_turn()
            except Exception:  # noqa: BLE001
                pass
        finally:
            try:
                disarm()
            except Exception:  # noqa: BLE001
                pass

    # ---------- main strategy ----------

    def _main_turn(self, gs):
        self._build_asymmetric_defense(gs)
        self._reactive_patches(gs)
        self._opportunistic_attack(gs)

    def _build_asymmetric_defense(self, gs):
        """Heavy on one side, thin on the other.

        We mirror the layout based on `self.heavy_left`:
          heavy_left=True  → 10 turrets at x ∈ [0,11], 2 at x ∈ [22,27]
          heavy_left=False → 2 turrets at x ∈ [0,5], 10 at x ∈ [16,27]
        """
        t = gs.turn_number

        if self.heavy_left:
            heavy_turrets = [
                [1, 13], [3, 12], [5, 12], [7, 12], [9, 12],
                [3, 11], [5, 11], [7, 11], [9, 11], [11, 11],
            ]
            light_turrets = [[24, 11], [26, 12]]
        else:
            heavy_turrets = [
                [26, 13], [24, 12], [22, 12], [20, 12], [18, 12],
                [24, 11], [22, 11], [20, 11], [18, 11], [16, 11],
            ]
            light_turrets = [[3, 11], [1, 12]]

        # Build heavy side aggressively.
        for i, loc in enumerate(heavy_turrets):
            if t >= i // 3:
                gs.attempt_spawn(TURRET, loc)

        # Build light side just enough to not be free.
        for loc in light_turrets:
            if t >= 1:
                gs.attempt_spawn(TURRET, loc)

        # Wall coverage: dense on heavy side, thin on light side.
        if self.heavy_left:
            heavy_walls = [[x, 13] for x in range(0, 12)] + [[x, 12] for x in range(2, 12)]
            light_walls = [[24, 13], [26, 13]]
        else:
            heavy_walls = [[x, 13] for x in range(16, 28)] + [[x, 12] for x in range(16, 26)]
            light_walls = [[1, 13], [3, 13]]

        gs.attempt_spawn(WALL, heavy_walls)
        gs.attempt_spawn(WALL, light_walls)

        # Upgrade cadence — heavy side gets all upgrades by turn 12.
        for i, loc in enumerate(heavy_turrets):
            if t >= 2 + i // 2:
                gs.attempt_upgrade(loc)

        # Light side gets upgraded only after heavy is done.
        if t >= 14:
            gs.attempt_upgrade(light_turrets)

        # Late: pile on supports behind the heavy spine.
        if t >= 10:
            if self.heavy_left:
                supports = [[5, 9], [7, 9], [9, 9], [11, 9]]
            else:
                supports = [[16, 9], [18, 9], [20, 9], [22, 9]]
            gs.attempt_spawn(SUPPORT, supports)
            if t >= 14:
                gs.attempt_upgrade(supports)

    def _reactive_patches(self, gs):
        placed = set()
        for loc in self.scored_on[-6:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y]]
            for build in candidates:
                bx, by = build
                if by > 13 or by < 0:
                    continue
                if not gs.game_map.in_arena_bounds(build):
                    continue
                if gs.contains_stationary_unit(build):
                    continue
                key = (bx, by)
                if key in placed:
                    continue
                if gs.attempt_spawn(TURRET, build) > 0:
                    placed.add(key)
                    break

    def _opportunistic_attack(self, gs):
        """Attack from the heavy side — we've got coverage to make a path."""
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]

        if self.heavy_left:
            # Attack from the heavy left flank.
            scout_spawn = [13, 0]
            demo_spawn = [10, 3]
        else:
            scout_spawn = [14, 0]
            demo_spawn = [17, 3]

        if my_mp >= 6 * demo_cost:
            if not gs.contains_stationary_unit(demo_spawn):
                gs.attempt_spawn(DEMOLISHER, demo_spawn, 4)
                return

        if my_mp >= 8 * scout_cost:
            if not gs.contains_stationary_unit(scout_spawn):
                gs.attempt_spawn(SCOUT, scout_spawn, 1000)

    def _safe_fallback(self, gs):
        for loc in [[11, 11], [16, 11], [13, 11], [14, 11]]:
            gs.attempt_spawn(TURRET, loc)

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:  # noqa: BLE001
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            try:
                loc, _, _, _, owner = b
            except (TypeError, ValueError):
                continue
            if owner == 2:
                self.scored_on.append(loc)


if __name__ == "__main__":
    AlgoStrategy().start()
