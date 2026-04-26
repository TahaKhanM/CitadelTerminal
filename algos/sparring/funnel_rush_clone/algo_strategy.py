"""funnel_rush_clone — sparring partner that plays heavy-corner-funnel
defense + periodic scout pings.

Profile:
  * Stack 6 upgraded turrets in BOTTOM_LEFT corner by turn ~15.
  * Funnel walls forcing attackers into the kill-zone.
  * Almost no defense on BOTTOM_RIGHT (skeleton walls only).
  * Scout ping every 4-5 turns from the corner where opponent is weakest.

This style is what beats lostkids_v3 on the ladder. Use this as a
sparring partner to test demolisher mode against heavy corner defense.

Production safety wrappers — see scout_rush_adversary for pattern:
  * 13-second SIGALRM watchdog (with threading.Timer fallback).
  * Top-level try/except in on_turn -> safe-fallback turret defense.
  * All numerics read from runtime config (server-delivered).
  * Vendored gamelib (copied from lostkids_v3/gamelib/).
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
        # Track when we last sent a scout ping so we can pace at every 4-5 turns.
        self._last_ping_turn = -10
        # Track which side opponent shows weaker defense on.
        # We'll observe opponent breach attempts + opponent structures.
        self.scored_on = []
        # Track opponent structure x-coords from action frames (rough heuristic).
        self._opp_left_struct = 0
        self._opp_right_struct = 0

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
                gamelib.debug_write("funnel_rush_clone: turn failed -- safe fallback")
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
        self._build_corner_funnel(gs)
        self._reactive_patches(gs)
        self._scout_ping_offense(gs)

    def _build_corner_funnel(self, gs):
        """Stack 6+ upgraded turrets in BOTTOM_LEFT corner with funnel walls.

        Layout (approximate):
            Row y=13: walls at (0,13), (4,13) | turrets at (1,13),(2,13),(3,13)
            Row y=12: turrets at (1,12),(2,12),(3,12) | wall at (4,12)
            Row y=11: walls at (4,11) for funnel close-out
            BOTTOM_RIGHT: skeleton walls only at edges.
        """
        t = gs.turn_number

        # Phase 0 (turn 0): plant the 6 corner turrets unupgraded + key funnel walls.
        # We need to be careful about deploy edges: BOTTOM_LEFT edge is (x,13-x)
        # for x in 0..13 — i.e. (0,13),(1,12),(2,11)... So putting walls on
        # (0,13) actually blocks our own deploy edge. Use (3,13) and (4,12) as
        # the corner-cap walls to funnel attackers without sealing our spawn.
        # Layout chosen so attackers approaching from BOTTOM_LEFT or moving along
        # row y=12 hit the funnel:
        #   - 6 turrets in the (1..3, 12..13) block
        #   - corner-cap walls at (4,12) and (4,11) to force lane right
        #   - back-stop walls at (5,11) for second-line
        corner_turrets = [
            [1, 12], [2, 12], [3, 12],
            [1, 13], [2, 13], [3, 13],
        ]
        gs.attempt_spawn(TURRET, corner_turrets)

        # Funnel walls -- these cap the corner so nothing slips up from y<12.
        funnel_walls_t0 = [
            [4, 12], [4, 11],     # cap right side of corner
            [5, 11],              # extension to keep funnel deep
        ]
        gs.attempt_spawn(WALL, funnel_walls_t0)

        # Skeleton walls on right side -- just enough to look like defense.
        skeleton_right = [[24, 12], [25, 12], [26, 13]]
        if t >= 1:
            gs.attempt_spawn(WALL, skeleton_right)

        # Upgrades come online progressively (cost-aware via attempt_upgrade
        # which silently skips if SP insufficient).
        if t >= 2:
            gs.attempt_upgrade([[2, 12], [2, 13]])
        if t >= 4:
            gs.attempt_upgrade([[1, 12], [3, 12]])
        if t >= 6:
            gs.attempt_upgrade([[1, 13], [3, 13]])
        if t >= 8:
            # Upgrade funnel walls so demolishers don't punch through.
            gs.attempt_upgrade([[4, 12], [4, 11]])
        if t >= 10:
            # Add second-ring turret behind the funnel.
            gs.attempt_spawn(TURRET, [[5, 12]])
            gs.attempt_upgrade([[5, 12]])
        if t >= 12:
            # Mid-line catchup to handle right-side leakage.
            gs.attempt_spawn(TURRET, [[14, 12], [13, 11]])
        if t >= 15:
            gs.attempt_upgrade([[14, 12]])
        if t >= 18:
            # Continue thickening the corner if we have spare SP.
            gs.attempt_spawn(TURRET, [[6, 12]])
            gs.attempt_upgrade([[6, 12], [13, 11]])

    def _reactive_patches(self, gs):
        """If we got scored on, add a turret near the breach point."""
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

    def _scout_ping_offense(self, gs):
        """Every 4-5 turns, ping 8+ scouts from the side where the opponent
        has weaker defense (more structures = stronger).
        """
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]

        if my_mp < 8 * scout_cost:
            return  # not enough for a real ping

        # Throttle: at most one ping every 4 turns.
        if t - self._last_ping_turn < 4:
            return

        # Choose corner with the weaker opponent defense.
        # Heuristic: count opponent stationary units in their left half (x<14)
        # vs right half (x>=14) on rows y in [14..27].
        left_struct, right_struct = self._count_opponent_halves(gs)

        # Spawn from the corner where the opponent has FEWER structures
        # so our scouts hit the soft side.
        if left_struct <= right_struct:
            spawn = [13, 0]  # left-leaning spawn (heads to BOTTOM_LEFT->TOP_RIGHT)
        else:
            spawn = [14, 0]

        # If our preferred spawn is blocked, try the other.
        if gs.contains_stationary_unit(spawn):
            spawn = [14, 0] if spawn[0] == 13 else [13, 0]
        if gs.contains_stationary_unit(spawn):
            return

        n = int(my_mp / scout_cost)
        spawned = gs.attempt_spawn(SCOUT, spawn, n)
        if spawned > 0:
            self._last_ping_turn = t

    def _count_opponent_halves(self, gs):
        """Count opponent stationary units on left (x<14) vs right (x>=14)
        in their territory (y >= 14)."""
        left = 0
        right = 0
        for x in range(28):
            for y in range(14, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                units = gs.game_map[x, y]
                if not units:
                    continue
                for u in units:
                    if not u.stationary:
                        continue
                    if u.player_index != 1:
                        continue
                    if x < 14:
                        left += 1
                    else:
                        right += 1
        return left, right

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
            # In raw action-frame JSON, owner==2 means the opponent scored on us.
            if owner == 2:
                self.scored_on.append(loc)


if __name__ == "__main__":
    AlgoStrategy().start()
