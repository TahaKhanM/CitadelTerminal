"""scout_rush_adversary — adversarial sparring partner targeting Athena's
classifier early-game blind spot (Phase 3 G5: SCOUT_RUSH samples are
14/25 misclassified to BALANCED).

Profile:
  * Turn 0: spawn 5 Scouts to one edge (chosen by per-game seed).
  * Turn 1: spawn 5 more Scouts on the other edge.
  * Turns 2-5: maximum scout pressure — flood from the side with weaker
    enemy front; alternate every turn so the classifier can't lock on.
  * Turns 6+: continue scout-only pressure but interleave with single
    interceptors to bait Athena into committing defensive resources.

By the time Athena's classifier locks on (typically turn 4-6 on G5
data), the adversary has already taken 8-12 HP off Athena.

Production safety wrappers — see anti_athena_hoarder for pattern.
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
        # Per-game seed varies side selection.
        seed = random.randrange(maxsize)
        random.seed(seed)
        self.opening_left_first = bool(random.randint(0, 1))
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
                gamelib.debug_write("scout_rush_adversary: turn failed -- safe fallback")
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
        # Minimal defense — we are aggressive, not defensive.
        self._build_minimal_defense(gs)
        self._reactive_patches(gs)
        self._scout_rush_offense(gs)

    def _build_minimal_defense(self, gs):
        """Just enough turrets to survive Athena's offense — no more."""
        t = gs.turn_number
        # Turn 0: 4 turrets + 8 walls.
        core_turrets = [[11, 11], [13, 11], [14, 11], [16, 11]]
        gs.attempt_spawn(TURRET, core_turrets)

        # Wall line — just the inner span.
        wall_line = [[x, 12] for x in range(8, 20) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)

        if t >= 2:
            gs.attempt_upgrade([[13, 11], [14, 11]])
        if t >= 4:
            # Add 2 sidelane turrets only when enemy threat materializes.
            gs.attempt_spawn(TURRET, [[8, 11], [19, 11]])
        if t >= 6:
            gs.attempt_spawn(TURRET, [[5, 11], [22, 11]])
            gs.attempt_upgrade([[11, 11], [16, 11]])
        if t >= 10:
            # Belated catch-up — once the rush is done, we still need to defend.
            gs.attempt_upgrade([[8, 11], [19, 11]])

    def _reactive_patches(self, gs):
        placed = set()
        for loc in self.scored_on[-4:]:
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

    def _scout_rush_offense(self, gs):
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        # Pick edges. BOTTOM_LEFT corner is [13,0]; BOTTOM_RIGHT is [14,0].
        # For deeper diagonal spawns we use [3,10] and [24,10] as flank
        # corners, but [13,0]/[14,0] are the safe canonical scout spawns.
        left_spawn = [13, 0]
        right_spawn = [14, 0]

        if t == 0:
            # Turn 0: spawn 5 Scouts to "first" side (per-game seeded).
            # We have 1 MP; only 1 scout fits anyway, but commit to the side.
            # The attempt_spawn will skip silently if MP < cost.
            spawn = left_spawn if self.opening_left_first else right_spawn
            n = int(my_mp / scout_cost)
            if n > 0 and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(SCOUT, spawn, n)
            return

        if t == 1:
            # Turn 1: spawn 5 more Scouts on the OTHER side.
            spawn = right_spawn if self.opening_left_first else left_spawn
            n = int(my_mp / scout_cost)
            if n > 0 and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(SCOUT, spawn, n)
            return

        if 2 <= t <= 5:
            # Maximum scout pressure — alternate side based on turn parity
            # to deny the classifier a clean spawn-x histogram.
            spawn = left_spawn if (t % 2 == 0) else right_spawn
            n = int(my_mp / scout_cost)
            if n > 0 and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(SCOUT, spawn, n)
            return

        # Turns 6+: continue scout pressure but interleave 1 interceptor
        # every 4 turns to bait Athena into defensive commitments.
        if t % 4 == 0 and my_mp >= interceptor_cost:
            # Spawn a single interceptor first (bait), then dump the rest as scouts.
            spawn = left_spawn if (t % 8 == 0) else right_spawn
            if not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)
        # Then dump the rest as scouts.
        my_mp = gs.get_resource(MP, 0)
        if my_mp >= 5 * scout_cost:
            spawn = left_spawn if (t % 2 == 0) else right_spawn
            if not gs.contains_stationary_unit(spawn):
                n = int(my_mp / scout_cost)
                gs.attempt_spawn(SCOUT, spawn, n)

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
