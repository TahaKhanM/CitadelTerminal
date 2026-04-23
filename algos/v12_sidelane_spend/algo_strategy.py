"""v12 sidelane-spend — v11 base + deep sidelane turrets + mid-game SP spend.

Addresses hypotheses §5.1/§5.3/§5.4 of REPLAY_ANALYSIS_2026-04-23.md:
  §5.1 Spend-the-hoard: v11 finished losses with 42–73 idle SP.
  §5.3 Harden right corner: (23, 9) was 86 % of damage in the 1829 loss.
  §5.4 Harden left corner: (6, 7) was 26 breaches in the 1750 loss.

Key change vs v11: adds two deep sidelane turrets at [7, 9] and [20, 9] to
the opening, placed AFTER center/outer turrets but BEFORE corner turrets
and the full wall line. Range-3.5 (upgraded) covers directly:
  [7,  9] → (7,6), (6,7), (5,8), (8,6)          — all 1750/1841 loss tiles
  [20, 9] → (20,6), (21,7), (22,8), (23,9)      — all 1829 loss tiles
Corner turrets are deferred to T1 to make room. Upgrade these two at T2.

Then `_spend_hoard` commits any remaining SP mid/late-game on backing walls
and a second-ring of sidelane turrets, so unused SP stops sitting idle.
"""
import gamelib
import json


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()

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
        sup = ui[1]
        self.support_shields = float(sup.get("shieldPerUnit", 0)) > 0 or \
            float(sup.get("upgrade", {}).get("shieldPerUnit", 0)) > 0
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
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]

        # Opening budget = 40 SP. v11 used: 12 turrets (24) + 16 walls (16).
        # v12 swaps the two outer corner turrets [0,13]/[27,13] for the two
        # deep sidelane turrets [7,9]/[20,9] — same SP, same T0 wall count,
        # but coverage is relocated to the actual breach hotspots. Outer
        # corners are placed later in _spend_hoard when we have free SP.
        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        # Wall line with center gaps at [12,12] and [15,12].
        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= 1:
            gs.attempt_upgrade([[13, 11], [14, 11], [11, 11], [16, 11]])
        if t >= 2:
            gs.attempt_upgrade(sidelane_deep)
        if t >= 3:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 7:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        for loc in center_turrets + sidelane_deep + outer_turrets + inner_corners + outer_corners:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded and u.health < 0.6 * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _spend_hoard(self, gs):
        """Convert idle SP into extra sidelane defences and wall upgrades.

        Runs after opening is established. Priority:
          1. Secondary sidelane turrets [23,10]/[6,10] backing the deep pair.
          2. Extra corner walls and backing walls.
          3. Upgrade sidelane-critical wall-line tiles to tank demolisher fire.
          4. Third-row sidelane turrets [21,10]/[7,10].
        """
        t = gs.turn_number
        if t < 6:
            return

        gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
        if t >= 8:
            gs.attempt_upgrade([[23, 10], [6, 10]])

        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])

        if t >= 12:
            gs.attempt_spawn(TURRET, [[21, 10], [7, 10]])
            gs.attempt_upgrade([[21, 10], [7, 10]])

        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

    def _reactive_defense(self, gs):
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            build = [x, min(max(y + 1, 0), 13)]
            if gs.game_map.in_arena_bounds(build) and not gs.contains_stationary_unit(build):
                key = tuple(build)
                if key not in placed:
                    gs.attempt_spawn(TURRET, build)
                    placed.add(key)

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        if enemy_mp >= 6 * scout_cost and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)

        if enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= 10 * scout_cost:
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
