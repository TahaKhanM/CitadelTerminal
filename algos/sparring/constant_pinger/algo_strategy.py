"""constant_pinger — sparring partner that simulates the LostKidsv2 ladder
loss pattern (replays/LostKidsv2_vs_1620.replay).

Profile:
  * Defense (turns 0-50): build 4 corner turrets at the back row plus a
    Y=12 wall ring. ~25 SP across the early turns. After basic defense
    is up, save SP — the threat is offense, not defense.
  * Offense (turns 4+): EVERY turn, spawn floor(MP) scouts (~4-7) from
    the spawn tile that has the FEWEST opponent structures along the
    path. Continuous pressure — never skip a turn.

The continuous pinging is the threat: 4-7 small scouts per turn,
multiple turns, eventually breaching opponents that don't account for
the relentless cadence.

Production safety: 13s SIGALRM watchdog + try/except + safe-fallback,
copied from scout_rush_adversary / demo_rusher.
"""
from __future__ import annotations

import json
import math
import os
import signal
import sys
import threading
import traceback

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


# Offense parameters
OFFENSE_START_TURN = 4   # First turn that pings scouts


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.scored_on = []
        self.path_cache = {}

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
            self.path_cache.clear()
            self._main_turn(gs)
            gs.submit_turn()
        except (_TurnTimeout, Exception):  # noqa: BLE001
            try:
                gamelib.debug_write("constant_pinger: turn failed -- safe fallback")
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
        self._constant_ping_offense(gs)

    def _build_basic_defense(self, gs):
        """4 back-row corner turrets + Y=12 wall ring. ~25 SP early total.

        Mirrors v13_second_ring's `inner_corners` ([1,13]/[26,13]) and
        `center_turrets` ([11,11],[14,11]) — minimal but real defense.
        After the basic ring is up we stop spending SP so MP is the
        constraint, not money.
        """
        t = gs.turn_number
        # 4 back-row corner turrets — two inner corners + two center.
        # T0 cost: 4 * 3 SP = 12 SP.
        corner_turrets = [[1, 13], [11, 11], [16, 11], [26, 13]]
        gs.attempt_spawn(TURRET, corner_turrets)

        # Y=12 wall ring across the inner span. T0 cost: ~10-13 SP.
        wall_line = [[x, 12] for x in range(4, 24) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        # Cap two inner corners with a wall to anchor the back-row turrets.
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        # T2: upgrade the center pair so the front breach tiles are real
        # threats. ~8 SP. Brings cumulative defense ~25 SP through T2.
        if t >= 2:
            gs.attempt_upgrade([[11, 11], [16, 11]])

        # T6+: opportunistic small upgrades only if SP is spilling. We
        # don't want to drown defense in SP — the threat is offense.
        if t >= 6 and gs.get_resource(SP) >= 10:
            gs.attempt_upgrade([[1, 13], [26, 13]])

        # Auto-replace dying turrets via upgrade-as-heal trick.
        for loc in corner_turrets:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded \
                   and u.health < 0.6 * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _reactive_patches(self, gs):
        """If a tile got scored on, drop a Turret one tile inward."""
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

    def _constant_ping_offense(self, gs):
        """EVERY turn from OFFENSE_START_TURN onward, spawn floor(MP) scouts
        from the spawn tile whose path crosses the FEWEST opponent
        structures.
        """
        t = gs.turn_number
        if t < OFFENSE_START_TURN:
            return

        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        n_scouts = int(math.floor(my_mp / scout_cost))
        if n_scouts <= 0:
            return

        # Two canonical bottom-edge spawn tiles.
        spawn_options = [[13, 0], [14, 0]]
        spawn_options = [s for s in spawn_options
                         if gs.game_map.in_arena_bounds(s)
                         and not gs.contains_stationary_unit(s)]
        if not spawn_options:
            return

        spawn = self._best_spawn_fewest_structures(gs, spawn_options)
        if spawn is None:
            return

        gs.attempt_spawn(SCOUT, spawn, n_scouts)

    def _best_spawn_fewest_structures(self, gs, options):
        """Pick the spawn tile whose path crosses the fewest opponent
        stationary units (turrets/walls/supports). If we can't path,
        fall back to first option.
        """
        best = None
        best_count = None
        for opt in options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            count = 0
            for p in path:
                # Only count tiles in the opponent half (y >= 14).
                if p[1] < 14:
                    continue
                # Look at adjacent enemy structures within Scout range
                # (range 4.5 from path tile).
                for dx in range(-3, 4):
                    for dy in range(-3, 4):
                        nx, ny = p[0] + dx, p[1] + dy
                        if not gs.game_map.in_arena_bounds([nx, ny]):
                            continue
                        if ny < 14:
                            continue
                        for u in gs.game_map[nx, ny]:
                            if u.player_index == 1 and u.stationary:
                                count += 1
            if best is None or count < best_count:
                best = opt
                best_count = count
        return best if best is not None else (options[0] if options else None)

    def _cached_path(self, gs, loc):
        key = (loc[0], loc[1])
        if key in self.path_cache:
            return self.path_cache[key]
        try:
            path = gs.find_path_to_edge(loc)
        except Exception:  # noqa: BLE001
            path = []
        self.path_cache[key] = path
        return path

    def _safe_fallback(self, gs):
        """Minimal safe behaviour if main turn raises."""
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
