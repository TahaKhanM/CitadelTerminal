"""demo_rusher — sparring partner that practices the late-game demolisher
rush pattern that beats lostkids_v3 on the ladder.

Profile:
  * Turns 0-25: build a basic 4-corner-turret + Y=12 wall ring (~4-6 SP/turn).
    Skip Supports entirely.
  * Turns 25-40: stockpile MP. No offense at all.
  * Turns 40+: every 3 turns, dump ALL stockpiled MP into Demolishers from a
    single spawn tile. Aim for 8-12 Demolishers per wave (24-36 MP).
    Spawn tile is per-game-seeded between (3,10) and (24,10).

Production safety: 13s SIGALRM watchdog + try/except + safe-fallback,
copied from scout_rush_adversary.

This algo is intentionally simple — its sole purpose is to *play* the
demo-rush pattern so we can verify lostkids_v3's anti-demo features fire.
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


# Demo rush parameters
DEMO_RUSH_START_TURN = 40   # First turn that can fire a demo wave
DEMO_RUSH_INTERVAL = 3      # Fire wave every N turns once started
MIN_DEMOS_PER_WAVE = 8      # Don't fire if we can't afford this many
MAX_DEMOS_PER_WAVE = 12     # Cap so leftover MP carries to next wave


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        # Per-game seed: which side does the demo wave spawn from?
        self.spawn_left = bool(random.randint(0, 1))
        self.scored_on = []
        self.last_wave_turn = -DEMO_RUSH_INTERVAL  # so first wave can fire at T40

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
                gamelib.debug_write("demo_rusher: turn failed -- safe fallback")
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
        self._demo_rush_offense(gs)

    def _build_basic_defense(self, gs):
        """Starter-style 4-corner-turret + Y=12 wall ring. ~4-6 SP/turn."""
        t = gs.turn_number
        # Turn 0: 4 core turrets + a Y=12 wall ring across the inner span.
        core_turrets = [[3, 12], [11, 11], [16, 11], [24, 12]]
        gs.attempt_spawn(TURRET, core_turrets)

        wall_line = [[x, 12] for x in range(4, 24)
                     if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)

        # Tier 2 (T2-T6): center upgrades only — keep SP modest so the
        # demo rush still has structures to stand behind.
        if t >= 2:
            gs.attempt_upgrade([[11, 11], [16, 11]])
        if t >= 4:
            # Two more inner-ring turrets to plug the center.
            gs.attempt_spawn(TURRET, [[13, 11], [14, 11]])
        if t >= 6:
            gs.attempt_upgrade([[13, 11], [14, 11]])
        if t >= 10:
            # Sidelane patches — minimal.
            gs.attempt_spawn(TURRET, [[8, 11], [19, 11]])

        # Auto-replace dying turrets with upgrades (HP < 60% triggers an
        # upgrade attempt, which heals because upgrade resets HP via
        # max_health bump — same trick as v13_second_ring).
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

    def _demo_rush_offense(self, gs):
        """Late-game demolisher rush.

        Phase A (turns 0-39): never spawn anything. Just stockpile MP.
        Phase B (turns 40+): every DEMO_RUSH_INTERVAL turns, dump 8-12
                             Demolishers from a single side spawn tile.
        """
        t = gs.turn_number
        if t < DEMO_RUSH_START_TURN:
            return  # Pure hoard phase.

        # Are we due for a wave?
        if (t - self.last_wave_turn) < DEMO_RUSH_INTERVAL:
            return

        my_mp = gs.get_resource(MP, 0)
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        n_affordable = int(my_mp // demo_cost)

        # Only fire if we can afford at least the wave minimum. Otherwise
        # keep stockpiling — the next eligible turn we'll have more.
        if n_affordable < MIN_DEMOS_PER_WAVE:
            return

        # Cap the wave so excess MP rolls forward.
        n_to_spawn = min(n_affordable, MAX_DEMOS_PER_WAVE)

        # Spawn tile: per-game seeded side. (3,10) is BOTTOM_LEFT diagonal,
        # (24,10) is BOTTOM_RIGHT diagonal — both are deep flank corners
        # that put Demolishers in attack range of opponent structures
        # quickly. Fall through to the canonical [13,0]/[14,0] if the
        # diagonal corner is blocked.
        spawn_candidates = self._spawn_candidates()
        spawn = None
        for cand in spawn_candidates:
            if not gs.game_map.in_arena_bounds(cand):
                continue
            if gs.contains_stationary_unit(cand):
                continue
            spawn = cand
            break

        if spawn is None:
            return

        spawned = gs.attempt_spawn(DEMOLISHER, spawn, n_to_spawn)
        if spawned > 0:
            self.last_wave_turn = t

    def _spawn_candidates(self):
        """Return ordered list of demo-spawn tiles, primary side first."""
        if self.spawn_left:
            return [[3, 10], [13, 0], [14, 0], [24, 10]]
        return [[24, 10], [14, 0], [13, 0], [3, 10]]

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
