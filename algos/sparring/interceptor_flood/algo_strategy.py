"""interceptor_flood — sparring partner that simulates the lostkids ladder
loss vs algo-v6 (1737).

Profile:
  * Defense: 4-corner-turret + Y=12 wall ring (copied from demo_rusher).
  * Offense:
    - Turns 0-21: 1 INTERCEPTOR per turn from a per-game-seeded mid-edge
      tile (4,9) on BL or (24,10) on BR. Both are valid edge spawn tiles
      that drop the interceptor near the front line where it can walk
      across and bleed opponent HP if their early defense is thin.
    - Turn >= 22 AND MP >= 7: switch to 7-Scout waves from the lower-density
      side (path-aware). Continues every turn afterward as long as MP allows.
  * Key trait: NEVER skip a turn — pressure every single turn from T0.

Production safety: 13s SIGALRM watchdog + try/except + safe-fallback,
copied from scout_rush_adversary.

This algo is intentionally simple — its sole purpose is to *play* the
constant-interceptor + delayed-scout pattern so we can verify whether a
target algo (e.g., v5_antiinterceptor) beats it where v4_smartdemo loses.
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


# Phase boundaries
INTERCEPTOR_PHASE_END = 21       # Last turn (inclusive) of pure-interceptor phase
SCOUT_PHASE_MIN_MP = 7           # MP threshold to fire a scout wave in scout phase
SCOUT_PHASE_WAVE_SIZE = 7        # Scouts per wave when affordable


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        # Per-game seed: which side the interceptor flood prefers.
        self.spawn_left = bool(random.randint(0, 1))
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
                gamelib.debug_write("interceptor_flood: turn failed -- safe fallback")
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
        self._build_basic_defense(gs)
        self._reactive_patches(gs)
        self._offense(gs)

    def _build_basic_defense(self, gs):
        """Starter-style 4-corner-turret + Y=12 wall ring.

        Copied verbatim from demo_rusher — known-good basic defense.
        """
        t = gs.turn_number
        core_turrets = [[3, 12], [11, 11], [16, 11], [24, 12]]
        gs.attempt_spawn(TURRET, core_turrets)

        wall_line = [[x, 12] for x in range(4, 24)
                     if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)

        if t >= 2:
            gs.attempt_upgrade([[11, 11], [16, 11]])
        if t >= 4:
            gs.attempt_spawn(TURRET, [[13, 11], [14, 11]])
        if t >= 6:
            gs.attempt_upgrade([[13, 11], [14, 11]])
        if t >= 10:
            gs.attempt_spawn(TURRET, [[8, 11], [19, 11]])

        # Auto-replace dying turrets with upgrades (HP < 60% triggers an
        # upgrade attempt).
        for loc in core_turrets + [[13, 11], [14, 11], [8, 11], [19, 11]]:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded \
                   and u.health < 0.6 * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _reactive_patches(self, gs):
        """If a tile has been scored on, drop a Turret one tile inward."""
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

    def _offense(self, gs):
        """Constant pressure — interceptor phase then scout phase.

        Phase A (turns 0-21): 1 interceptor per turn from a side spawn tile.
        Phase B (turns 22+): scout waves of SCOUT_PHASE_WAVE_SIZE when
                             MP >= SCOUT_PHASE_MIN_MP, otherwise carry MP.

        NEVER skip a turn — always attempt to send something.
        """
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)

        if t <= INTERCEPTOR_PHASE_END:
            # Interceptor phase: 1 INTERCEPTOR per turn.
            interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]
            if my_mp < interceptor_cost:
                return  # T0: 1 MP < 1.0 cost? We have exactly 1 MP — should fit.

            spawn = self._pick_interceptor_spawn(gs)
            if spawn is None:
                return
            gs.attempt_spawn(INTERCEPTOR, spawn, 1)
            return

        # Scout phase (turn 22+).
        scout_cost = gs.type_cost(SCOUT)[MP]
        if my_mp < SCOUT_PHASE_MIN_MP * scout_cost:
            return  # Hold this turn — wave threshold not met.

        # Path-aware: pick the side with the fewer enemy stationary units
        # in front (lower-density). This makes the scout wave bleed through
        # the weak edge.
        spawn = self._pick_scout_spawn(gs)
        if spawn is None:
            return

        # Re-check after spawn pick — gs.get_resource may have updated.
        my_mp = gs.get_resource(MP, 0)
        n = min(SCOUT_PHASE_WAVE_SIZE, int(my_mp // scout_cost))
        if n > 0:
            gs.attempt_spawn(SCOUT, spawn, n)

    def _pick_interceptor_spawn(self, gs):
        """Return a valid edge spawn for an interceptor.

        Primary tile per side seed; fall back to canonical [13,0]/[14,0]
        if blocked.
        """
        if self.spawn_left:
            cands = [[4, 9], [3, 10], [13, 0], [14, 0]]
        else:
            cands = [[24, 10], [23, 10], [14, 0], [13, 0]]
        for c in cands:
            if not gs.game_map.in_arena_bounds(c):
                continue
            if gs.contains_stationary_unit(c):
                continue
            return c
        return None

    def _pick_scout_spawn(self, gs):
        """Pick the spawn side with lower enemy stationary-unit density.

        Counts enemy stationaries on the opposing front (y in 14..18) on
        each half (x<14 vs x>=14). Pick the side opposite the heavier
        half, which is where our scouts can break through more easily.
        """
        left_density = 0
        right_density = 0
        try:
            for x in range(0, 14):
                for y in range(14, 19):
                    if gs.contains_stationary_unit([x, y]):
                        left_density += 1
            for x in range(14, 28):
                for y in range(14, 19):
                    if gs.contains_stationary_unit([x, y]):
                        right_density += 1
        except Exception:  # noqa: BLE001
            pass

        # Spawn from the side aimed at the LOWER-density opponent half.
        # If opponent's left half is denser, send scouts through our left
        # corner so they walk into opponent's right (weaker) half. The
        # diagonal mirror means our [13,0] spawn walks toward opponent
        # right via center, and [14,0] walks toward opponent left.
        # Keep it simple: use the canonical edge corners.
        if left_density <= right_density:
            cands = [[13, 0], [14, 0]]
        else:
            cands = [[14, 0], [13, 0]]
        for c in cands:
            if not gs.game_map.in_arena_bounds(c):
                continue
            if gs.contains_stationary_unit(c):
                continue
            return c
        return None

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
