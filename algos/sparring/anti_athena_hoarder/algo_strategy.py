"""anti_athena_hoarder — adversarial sparring partner targeting Athena v3's
G11 regression cluster (new-strat-algo / python-algo-v5 / python-algo-v3-detectors).

Profile (modeled on the 3 long-match opponents that beat v13 in G11):
  * Never attacks before turn 18.
  * Hoards MP aggressively; only releases offense when MP > 24 AND turn >= 22.
  * Defense is dense early — 12+ turrets in the first 8 turns, all upgraded
    by turn 14 (mirrors the python-algo-v5 / new-strat-algo layout).
  * Single all-in: a Demolisher line + Scout follow-up at turn 22-25 once
    Athena's MP has been depleted by sustained attacking.

Production safety wrappers (lifted from athena_v3_planner):
  * 13 s SIGALRM watchdog around `on_turn`.
  * Top-level try/except.
  * Safe-fallback minimal turret defense on any exception or watchdog fire.
"""
from __future__ import annotations

import json
import os
import signal
import sys
import threading
import traceback

import gamelib

# Make local imports relative-safe.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)


# --- Production safety wrappers --------------------------------------------
TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    """Arm a SIGALRM watchdog. Returns (disarm, fired_check) tuple."""
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
                gamelib.debug_write("anti_athena_hoarder: turn failed -- safe fallback")
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
        self._build_dense_defense(gs)
        self._reactive_patches(gs)
        self._maybe_release_all_in(gs)

    def _build_dense_defense(self, gs):
        """Match the python-algo-v5 / new-strat-algo dense profile.

        Goal: ~12 turrets up by turn 8, all upgraded by turn 14.
        We build 4 deep-front, then 4 outer, then 4 corner-flank.
        """
        t = gs.turn_number

        # Tier 1 — center spine (turn 0).
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        gs.attempt_spawn(TURRET, center_turrets)

        # Tier 2 — outer spine (turn 0-2).
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        gs.attempt_spawn(TURRET, outer_turrets)

        # Tier 3 — sidelane deep + corners (turn 1-3).
        sidelane_deep = [[7, 9], [20, 9]]
        gs.attempt_spawn(TURRET, sidelane_deep)
        inner_corners = [[1, 13], [26, 13]]
        gs.attempt_spawn(TURRET, inner_corners)

        # Wall line at y=12 — protects the row of turrets.
        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        # Upgrades cadence — by turn 14 everything should be upgraded.
        if t >= 1:
            gs.attempt_upgrade(center_turrets)
        if t >= 2:
            gs.attempt_upgrade(sidelane_deep)
        if t >= 4:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= 6:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 8:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            # Add second-ring deep cover (lifted from v13_second_ring §5.2).
            second_ring = [[11, 5], [16, 5]]
            gs.attempt_spawn(TURRET, second_ring)
            gs.attempt_upgrade(second_ring)
        if t >= 12:
            # Walls upgrade en masse — strengthens the late-game keep.
            gs.attempt_upgrade(wall_line[:8])
            gs.attempt_upgrade(wall_line[-8:])
        if t >= 14:
            # Final pass — every Turret upgraded, more wall density.
            for loc in (
                center_turrets + outer_turrets + sidelane_deep
                + inner_corners + [[11, 5], [16, 5]]
            ):
                for u in gs.game_map[loc[0], loc[1]]:
                    if u.unit_type == TURRET and not u.upgraded:
                        gs.attempt_upgrade(loc)
                        break

        # Late-game Supports — hoarder uses any spare SP for shield density.
        if t >= 16:
            supports = [[13, 9], [14, 9], [13, 8], [14, 8]]
            gs.attempt_spawn(SUPPORT, supports)
            gs.attempt_upgrade(supports)

    def _reactive_patches(self, gs):
        """Patch any breach tile from the last few turns with a backing turret."""
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

    def _maybe_release_all_in(self, gs):
        """The signature move: hoard until turn 22-25, then release everything."""
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        scout_cost = gs.type_cost(SCOUT)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        # Pre-turn-18 — never attack. Only minimal interceptor screen
        # if enemy MP is critical (matches new-strat-algo pacing).
        if t < 18:
            enemy_mp = gs.get_resource(MP, 1)
            if enemy_mp >= 12 * scout_cost and my_mp >= interceptor_cost:
                # Light interceptor screen — burn 1 MP to slow the rush.
                gs.attempt_spawn(INTERCEPTOR, [13, 0])
            return

        # Turn 18-21 — accumulate. Only respond if attacked.
        if t < 22:
            return

        # Turn 22+ — release all-in if we hit the MP threshold.
        if my_mp < 24 and t < 25:
            return

        # All-in: a Demolisher line + Scout follow-up.
        # We pick the side with thinner enemy front.
        left_strength = self._enemy_front_strength(gs, range(0, 14))
        right_strength = self._enemy_front_strength(gs, range(14, 28))

        if left_strength <= right_strength:
            demo_spawn = [10, 3]
            scout_spawn = [13, 0]
        else:
            demo_spawn = [17, 3]
            scout_spawn = [14, 0]

        # Validate spawns.
        if (gs.game_map.in_arena_bounds(demo_spawn)
                and not gs.contains_stationary_unit(demo_spawn)):
            n_demo = max(0, int((my_mp - 6 * scout_cost) / demo_cost))
            if n_demo >= 4:
                gs.attempt_spawn(DEMOLISHER, demo_spawn, n_demo)
                if (gs.game_map.in_arena_bounds(scout_spawn)
                        and not gs.contains_stationary_unit(scout_spawn)):
                    gs.attempt_spawn(SCOUT, scout_spawn, 1000)
                return

        # Fallback: pure scout flood if we couldn't place demos.
        if (gs.game_map.in_arena_bounds(scout_spawn)
                and not gs.contains_stationary_unit(scout_spawn)):
            gs.attempt_spawn(SCOUT, scout_spawn, 1000)

    def _enemy_front_strength(self, gs, x_range):
        n = 0
        for y in (14, 15, 16):
            for x in x_range:
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 2 if u.upgraded else 1
        return n

    def _safe_fallback(self, gs):
        """Minimal 4-turret defense if main turn errored or timed out."""
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
