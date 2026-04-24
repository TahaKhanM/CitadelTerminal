"""v20_sidelane: v13 + east-west spawn adaptation based on opponent x-distribution.
Parent: v13_second_ring. Tag: "sidelane-adaptation".
Hypothesis: +4-6% WR vs asymmetric opponents (turret_castle, off-center builders).

Core change vs v13 (RA-2 fix #1):
  Each turn we compute the opponent's stationary-unit x-distribution (left half
  x<14 vs right half x>=14). If the imbalance exceeds 3 units, we spawn Scout
  bursts, Demolisher bursts, and Interceptor plugs from the SPARSER side so
  units avoid the opponent's dense defense. Otherwise we fall back to v13's
  centre-split [13,0]/[14,0]. Defense build order is unchanged.
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

        # Opening identical to v12: 12 turrets (24 SP) + walls (16 SP).
        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= 1:
            gs.attempt_upgrade(center_turrets)
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
        """Convert idle SP into second-ring defences targeting the actual
        hot-spots per §5.2. [11,5]/[16,5] upgraded cover (18,4)/(9,4)/
        (19,5)/(11,2) — 79 of 129 breaches across the v12 loss set.
        """
        t = gs.turn_number
        if t < 5:
            return

        second_ring = [[11, 5], [16, 5]]
        gs.attempt_spawn(TURRET, second_ring)
        if t >= 8:
            gs.attempt_upgrade(second_ring)

        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])

        if t >= 12:
            gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])

        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

    def _reactive_defense(self, gs):
        """Place a backing Turret one tile inward from a scored-on tile.

        §5.6 fix: v12's build = [x, min(max(y+1, 0), 13)] clamped (0,13)
        to itself (the breach tile). Try multiple candidates with side-
        aware preference and fall through if out-of-bounds or occupied.
        """
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

    # ------------------------------------------------------------------
    # v20_sidelane: dynamic east-west spawn adaptation
    # ------------------------------------------------------------------
    def _pick_spawn_side(self, gs):
        """Return 'left', 'right', or 'center' based on opponent x-distribution.

        Counts enemy stationary units on each half of the board. If one side
        has at least 3 more units than the other, return the SPARSER side so
        our mobile units path around the dense defense. Otherwise 'center'.
        """
        left_count = 0
        right_count = 0
        for loc in gs.game_map:
            x, y = loc
            if y < 14:
                continue  # enemy territory only
            for u in gs.game_map[x, y]:
                if u.player_index == 1 and u.stationary:
                    if x < 14:
                        left_count += 1
                    else:
                        right_count += 1
        diff = left_count - right_count
        if diff >= 3:
            return "right"  # left is dense, so spawn right (sparser)
        if diff <= -3:
            return "left"   # right is dense, so spawn left (sparser)
        return "center"

    def _spawn_options_for_side(self, gs, side, role):
        """Get candidate spawn tiles for the chosen side + role.

        role in {"attack", "interceptor"} — interceptor prefers inner tiles.
        Returned options are filtered to in-bounds and unoccupied.
        """
        if side == "left":
            if role == "interceptor":
                opts = [[6, 7], [7, 6], [3, 10]]
            else:  # attack
                opts = [[3, 10], [6, 7], [7, 6]]
        elif side == "right":
            if role == "interceptor":
                opts = [[21, 7], [20, 6], [24, 10]]
            else:
                opts = [[24, 10], [21, 7], [20, 6]]
        else:  # center
            if role == "interceptor":
                opts = [[13, 0]]
            else:
                opts = [[13, 0], [14, 0]]
        return [s for s in opts
                if gs.game_map.in_arena_bounds(s)
                and not gs.contains_stationary_unit(s)]

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        if enemy_mp >= 6 * scout_cost and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            side = self._pick_spawn_side(gs)
            opts = self._spawn_options_for_side(gs, side, "interceptor")
            # Fallback to center if side-specific options are all blocked.
            if not opts and side != "center":
                opts = self._spawn_options_for_side(gs, "center", "interceptor")
            for spawn in opts:
                if gs.attempt_spawn(INTERCEPTOR, spawn) > 0:
                    break

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)
        side = self._pick_spawn_side(gs)

        if enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = self._spawn_options_for_side(gs, side, "attack")
            if not spawn_opts and side != "center":
                spawn_opts = self._spawn_options_for_side(gs, "center", "attack")
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= 10 * scout_cost:
            spawn_opts = self._spawn_options_for_side(gs, side, "attack")
            if not spawn_opts and side != "center":
                spawn_opts = self._spawn_options_for_side(gs, "center", "attack")
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
