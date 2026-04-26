"""stockpiler — sparring partner that simulates the LostKidsv2 ladder loss
pattern from `replays/LostKidsv2_vs_1782.replay`: an opponent that hoards
MP for many turns and then dumps 30+ scouts in one or two massive waves.

Profile:
  * Defense (turns 0-50): build a basic 4-corner-turret + Y=12 wall ring
    (pattern reused from algos/sparring/demo_rusher/). Auto-replace dying
    turrets via the upgrade-as-heal trick. After the basic ring is up,
    stop spending SP — the threat is offense, not defense.
  * Offense:
      Turns 0-12   : send only TINY probes (1-2 scouts on rare turns,
                     specifically T0 and T4) to keep MP from overflowing
                     obvious upper-bound heuristics on the opponent.
      Turns 12-40  : pure HOARD. Skip attacks entirely. Let MP grow to 25+.
      Turns 40+    : when MP >= MIN_DUMP_MP, dump ALL MP into Scouts
                     (large attempt_spawn count, engine clamps to MP)
                     from a single corner edge. After the wave, keep
                     hoarding until next dump (~T48 second wave).
  * Spawn tile selection: pick the side of the map (BOTTOM_LEFT corner
    [13,0] or BOTTOM_RIGHT corner [14,0]) whose path crosses the FEWEST
    opponent stationary structures.

Production safety: 13s SIGALRM watchdog + try/except + safe-fallback,
copied from scout_rush_adversary / demo_rusher / constant_pinger.
"""
from __future__ import annotations

import json
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


# Offense parameters (mirroring LostKidsv2_vs_1782 cadence)
PROBE_TURNS = {0, 4}            # tiny probes — 1 scout each, hide MP buildup
PROBE_SCOUTS = 1                # 1 scout per probe turn
HOARD_END_TURN = 40             # hoarding window ends when t >= this
DUMP_INTERVAL = 8               # min turns between full dumps (T40, T48, ...)
MIN_DUMP_MP = 25                # don't dump unless MP >= this
LARGE_SPAWN_COUNT = 1000        # engine clamps to affordable count


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.scored_on = []
        self.path_cache = {}
        self.last_dump_turn = -DUMP_INTERVAL  # so first dump can fire at T40

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
                gamelib.debug_write("stockpiler: turn failed -- safe fallback")
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
        self._stockpile_offense(gs)

    def _build_basic_defense(self, gs):
        """4-corner-turret + Y=12 wall ring (reused from demo_rusher)."""
        t = gs.turn_number
        # Turn 0: 4 core turrets + a Y=12 wall ring across the inner span.
        core_turrets = [[3, 12], [11, 11], [16, 11], [24, 12]]
        gs.attempt_spawn(TURRET, core_turrets)

        wall_line = [[x, 12] for x in range(4, 24)
                     if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)

        # Tier 2 (T2-T6): center upgrades only.
        if t >= 2:
            gs.attempt_upgrade([[11, 11], [16, 11]])
        if t >= 4:
            gs.attempt_spawn(TURRET, [[13, 11], [14, 11]])
        if t >= 6:
            gs.attempt_upgrade([[13, 11], [14, 11]])
        if t >= 10:
            gs.attempt_spawn(TURRET, [[8, 11], [19, 11]])

        # Auto-replace dying turrets via upgrade-as-heal trick.
        for loc in core_turrets + [[13, 11], [14, 11], [8, 11], [19, 11]]:
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

    def _stockpile_offense(self, gs):
        """Three-phase offense:

        Phase A (t in PROBE_TURNS): 1-2 tiny probe scouts (low signal).
        Phase B (otherwise t < HOARD_END_TURN): pure hoard, no spawn.
        Phase C (t >= HOARD_END_TURN, MP >= MIN_DUMP_MP, gap-respected):
                dump ALL MP into Scouts at the lowest-density side.
        """
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]

        # Phase A: tiny probes.
        if t in PROBE_TURNS:
            n_probes = min(PROBE_SCOUTS, int(my_mp // scout_cost))
            if n_probes > 0:
                spawn = self._pick_spawn(gs)
                if spawn is not None:
                    gs.attempt_spawn(SCOUT, spawn, n_probes)
            return

        # Phase B: hoard.
        if t < HOARD_END_TURN:
            return

        # Phase C: dump.
        if (t - self.last_dump_turn) < DUMP_INTERVAL:
            return
        if my_mp < MIN_DUMP_MP:
            return  # keep hoarding — wait for more MP.

        spawn = self._pick_spawn(gs)
        if spawn is None:
            return

        # Engine clamps to affordable count, so LARGE_SPAWN_COUNT == "ALL".
        spawned = gs.attempt_spawn(SCOUT, spawn, LARGE_SPAWN_COUNT)
        if spawned > 0:
            self.last_dump_turn = t

    # ---------- spawn-side selection ----------

    def _pick_spawn(self, gs):
        """Return the bottom-edge spawn tile whose path crosses the
        fewest opponent stationary structures. Falls back to first valid
        option if pathing fails for all.
        """
        spawn_options = [[13, 0], [14, 0]]
        spawn_options = [s for s in spawn_options
                         if gs.game_map.in_arena_bounds(s)
                         and not gs.contains_stationary_unit(s)]
        if not spawn_options:
            return None

        best = None
        best_count = None
        for opt in spawn_options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            count = 0
            for p in path:
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
        return best if best is not None else spawn_options[0]

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
