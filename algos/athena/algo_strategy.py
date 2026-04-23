"""Athena v3 — top-of-leaderboard Citadel Terminal algo (in-progress scaffold).

Phase 0 scaffold: loads verified Citadel config snapshot at startup, vendors a
patched gamelib (deque-based BFS + GameState path cache), and currently plays
a minimal safe defense so it can be driven against v13 for baseline timings
before the real planner comes online.

Every subsequent phase (0.5 → 12) layers specific capabilities in without
altering the outer `on_turn` contract defined here.
"""

import json
import os
import traceback

import gamelib


ATHENA_ROOT = os.path.dirname(os.path.abspath(__file__))
SNAPSHOT_PATH = os.path.join(ATHENA_ROOT, "data", "citadel_config_snapshot.json")


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.snapshot = None
        self.shorthand_by_role = None  # {"WALL": "FF", ...}

    def on_game_start(self, config):
        self.config = config
        self._load_snapshot_and_cross_check(config)
        # Role-name constants derived from live config, NEVER hardcoded.
        # gamelib.GameState will also set these as indices during __init__.
        self._resolve_role_shorthands(config)
        gamelib.debug_write("[athena] Phase 0 scaffold booted. Roles: {}".format(self.shorthand_by_role))

    def on_turn(self, turn_state):
        try:
            game_state = gamelib.GameState(self.config, turn_state)
            game_state.suppress_warnings(True)
            self._minimal_safe_turn(game_state)
            game_state.submit_turn()
        except Exception:
            gamelib.debug_write("[athena] on_turn failed — emitting empty turn:\n" + traceback.format_exc())
            # Fallback: cannot even build safe defense; submit a bare turn.
            try:
                game_state = gamelib.GameState(self.config, turn_state)
                game_state.submit_turn()
            except Exception:
                gamelib.debug_write("[athena] Even bare submit failed; giving up this turn.")

    # ------------------------------------------------------------------ internal

    def _load_snapshot_and_cross_check(self, live_config):
        try:
            with open(SNAPSHOT_PATH, "r") as f:
                self.snapshot = json.load(f)
        except FileNotFoundError:
            gamelib.debug_write("[athena] citadel_config_snapshot.json MISSING — using live config only")
            self.snapshot = None
            return
        snap_units = self.snapshot.get("_raw_unit_information", [])
        live_units = live_config.get("unitInformation", [])
        for i, live in enumerate(live_units):
            if i >= len(snap_units):
                continue
            live_hp = live.get("startHealth")
            snap_hp = snap_units[i].get("startHealth")
            if live_hp is not None and snap_hp is not None and abs(live_hp - snap_hp) > 1e-6:
                gamelib.debug_write(
                    "[athena] config drift unit[{}] {}: snapshot HP {} != live HP {} — trusting live"
                    .format(i, live.get("shorthand"), snap_hp, live_hp))

    def _resolve_role_shorthands(self, config):
        mapping = {}
        for i, unit in enumerate(config.get("unitInformation", [])):
            sh = unit.get("shorthand")
            disp = (unit.get("display") or "").upper()
            if disp in ("FILTER", "WALL"):
                mapping["WALL"] = sh
            elif disp in ("ENCRYPTOR", "SUPPORT"):
                mapping["SUPPORT"] = sh
            elif disp in ("DESTRUCTOR", "TURRET"):
                mapping["TURRET"] = sh
            elif disp == "PING" or disp == "SCOUT":
                mapping["SCOUT"] = sh
            elif disp == "EMP" or disp == "DEMOLISHER":
                mapping["DEMOLISHER"] = sh
            elif disp == "SCRAMBLER" or disp == "INTERCEPTOR":
                mapping["INTERCEPTOR"] = sh
        self.shorthand_by_role = mapping

    def _minimal_safe_turn(self, game_state):
        """Phase 0 placeholder defense. Real planner replaces this at Phase 5."""
        WALL = game_state.WALL
        TURRET = game_state.TURRET
        front_walls = [[0, 13], [27, 13], [3, 12], [24, 12], [6, 11], [21, 11], [10, 10], [17, 10], [13, 10], [14, 10]]
        turrets = [[3, 11], [24, 11], [13, 9], [14, 9]]
        game_state.attempt_spawn(WALL, front_walls)
        game_state.attempt_spawn(TURRET, turrets)


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
