"""v19 = v13_second_ring with CMA-ES tunable CONFIG.

Phase 6 (docs/ADVANCED_STRATEGIES_PROMPT.md § A Tier 4.1): extract v13's
~16 hand-tuned magic numbers into a single CONFIG dict. Each knob has a
(lo, hi) bound in BOUNDS. Evolution uses a unit-normalised vector with
CMA-ES (sigma0=0.3) and writes the mapped CONFIG to an environment
variable CITADEL_CFG_JSON consumed at on_game_start time.

Defaults in CONFIG reproduce v13 verbatim so an un-overridden v19 ==
v13 behaviourally.
"""
import os
import sys
import json

import gamelib

# Load CONFIG/BOUNDS from sibling knobs.py. Algo's cwd at runtime is
# the algo directory, so a plain import works — but be defensive.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)
from knobs import CONFIG, BOUNDS  # noqa: E402,F401


def _load_env_overrides(cfg: dict) -> dict:
    env = os.environ.get("CITADEL_CFG_JSON")
    if not env:
        return cfg
    try:
        override = json.loads(env)
    except Exception:
        return cfg
    merged = dict(cfg)
    for k, v in override.items():
        if k in merged:
            merged[k] = v
    return merged


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()

    def on_game_start(self, config):
        self.config = config
        self.cfg = _load_env_overrides(CONFIG)
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
        self.scored_on = []
        self.path_cache = {}

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    def _build_defense(self, gs):
        t = gs.turn_number
        c = self.cfg
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]

        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= c["BUILD_T_UPGRADE_CENTER"]:
            gs.attempt_upgrade(center_turrets)
        if t >= c["BUILD_T_UPGRADE_SIDELANE"]:
            gs.attempt_upgrade(sidelane_deep)
        if t >= c["BUILD_T_UPGRADE_OUTER_MID"]:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= c["BUILD_T_UPGRADE_OUTER_EDGE"]:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= c["BUILD_T_UPGRADE_INNER_CORNER"]:
            gs.attempt_upgrade(inner_corners)
        if t >= c["BUILD_T_OUTER_CORNERS"]:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        hp_pct = c["UPGRADE_HP_PCT"]
        for loc in center_turrets + sidelane_deep + outer_turrets + inner_corners + outer_corners:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded and u.health < hp_pct * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _spend_hoard(self, gs):
        t = gs.turn_number
        c = self.cfg
        if t < c["HOARD_T_START"]:
            return

        second_ring = [[11, 5], [16, 5]]
        gs.attempt_spawn(TURRET, second_ring)
        if t >= c["HOARD_T_UPGRADE_SECOND_RING"]:
            gs.attempt_upgrade(second_ring)

        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])

        if t >= c["HOARD_T_THIRD_RING"]:
            gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])

        if t >= c["HOARD_T_WALL_UPGRADES"]:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= c["HOARD_T_OUTER_RING"]:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

    def _reactive_defense(self, gs):
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y], [x, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y], [x, y]]
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

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        trigger = self.cfg["PRESSURE_TRIGGER_MULT"] * scout_cost
        if enemy_mp >= trigger and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        c = self.cfg
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)

        if enemy_front >= c["DEMO_FRONT_THRESHOLD"] and mp >= c["DEMO_THRESHOLD_MULT"] * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= c["SCOUT_THRESHOLD_MULT"] * scout_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    def _count_enemy_front(self, gs):
        n = 0
        for y in (14, 15):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 1
        return n

    def _best_spawn(self, gs, options):
        best = None
        best_dmg = None
        t_dmg_up = float(self.config["unitInformation"][2].get(
            "upgrade", {}).get("attackDamageWalker",
            self.config["unitInformation"][2].get("attackDamageWalker", 6)))
        for opt in options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            dmg = 0.0
            for p in path:
                dmg += len(gs.get_attackers(p, 0)) * t_dmg_up
            if best is None or dmg < best_dmg:
                best = opt
                best_dmg = dmg
        return best

    def _cached_path(self, gs, loc):
        key = (loc[0], loc[1])
        if key in self.path_cache:
            return self.path_cache[key]
        try:
            path = gs.find_path_to_edge(loc)
        except Exception:
            path = []
        self.path_cache[key] = path
        return path

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on.append(loc)


if __name__ == "__main__":
    AlgoStrategy().start()
