"""
v21_scout_probe: v13 + live probe-Scout damage sampling (INVENTION #1).
Parent: v13_second_ring. Tag: "probe-recon".
Hypothesis: direct live damage-heatmap outperforms batch-counting structure
predictors used by all finalist algos. Uses on_action_frame + per-tile
decay to build path-safety estimates.
"""
import gamelib
import json


# Filled-in defaults (see report):
# - Probe turns: T5-T8 inclusive (one Scout per turn, alternating side).
# - Probe launch tiles (cycled): [0,13], [27,13], [5,8], [22,8].
#   [5,8]/[22,8] are in-arena diamond tiles (y+x>=13 and (27-x)+y>=13).
# - Decay factor: 0.9 per turn applied to all heatmap entries at start of turn.
# - Heatmap influence: added to path-damage score as a weighted tiebreaker
#   with HEATMAP_WEIGHT = 1.0 (raw damage units, comparable to predicted
#   turret damage). Small cells drop when below PRUNE_THRESHOLD=0.1 to keep
#   dict size bounded.
PROBE_TURNS = (5, 6, 7, 8)
PROBE_LAUNCH = [[0, 13], [27, 13], [5, 8], [22, 8]]
HEATMAP_DECAY = 0.9
HEATMAP_WEIGHT = 1.0
PRUNE_THRESHOLD = 0.1


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
        # --- v21 additions ---
        self.unsafe_heatmap = {}   # (x, y) -> accumulated opponent damage
        self._probe_idx = 0        # rotates through PROBE_LAUNCH

    # ---------------- heatmap helpers ----------------

    def _decay_heatmap(self):
        if not self.unsafe_heatmap:
            return
        decayed = {}
        for k, v in self.unsafe_heatmap.items():
            nv = v * HEATMAP_DECAY
            if nv >= PRUNE_THRESHOLD:
                decayed[k] = nv
        self.unsafe_heatmap = decayed

    def _spawn_probe(self, gs):
        t = gs.turn_number
        if t not in PROBE_TURNS:
            return
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        if mp < scout_cost:
            return
        # Rotate through launch tiles for diversified sampling.
        for _ in range(len(PROBE_LAUNCH)):
            loc = PROBE_LAUNCH[self._probe_idx % len(PROBE_LAUNCH)]
            self._probe_idx += 1
            if gs.game_map.in_arena_bounds(loc) and not gs.contains_stationary_unit(loc):
                gs.attempt_spawn(SCOUT, loc)
                break

    # ---------------- main turn loop ----------------

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._decay_heatmap()

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)
        self._spawn_probe(gs)

        gs.submit_turn()

    # ---------------- v13 defense (unchanged) ----------------

    def _build_defense(self, gs):
        t = gs.turn_number
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
        """v21 modification: path-damage score now includes heatmap sum.

        score(opt) = predicted_turret_damage(path) + HEATMAP_WEIGHT * heat(path)
        Falls back to pure predicted-damage scoring when heatmap is empty.
        """
        best = None
        best_score = None
        t_dmg_up = float(self.config["unitInformation"][2].get(
            "upgrade", {}).get("attackDamageWalker",
            self.config["unitInformation"][2].get("attackDamageWalker", 6)))
        have_heat = bool(self.unsafe_heatmap)
        for opt in options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            dmg = 0.0
            heat = 0.0
            for p in path:
                dmg += len(gs.get_attackers(p, 0)) * t_dmg_up
                if have_heat:
                    heat += self.unsafe_heatmap.get((p[0], p[1]), 0.0)
            score = dmg + HEATMAP_WEIGHT * heat
            if best is None or score < best_score:
                best = opt
                best_score = score
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

    # ---------------- live damage observation ----------------

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})

        # Build set of currently-live my-scout unit_ids from this frame's
        # p1Units snapshot so we can attribute opponent attacks.
        # p1Units order: [walls, supports, turrets, scouts, demolishers, interceptors, removals]
        try:
            p1_units = state.get("p1Units", [])
            live_scouts = set()
            if len(p1_units) >= 4:
                for u in p1_units[3]:
                    # u = [x, y, hp, unit_id]
                    if len(u) >= 4:
                        live_scouts.add(u[3])
            # attack event:
            # [[att_x,att_y], [vic_x,vic_y], dmg, att_type, att_id, vic_id, att_player]
            for a in events.get("attack", []):
                if len(a) < 7:
                    continue
                if a[6] != 2:  # raw convention: 2 = opponent
                    continue
                vic_id = a[5]
                if vic_id not in live_scouts:
                    continue
                vx, vy = a[1][0], a[1][1]
                dmg = float(a[2])
                key = (vx, vy)
                self.unsafe_heatmap[key] = self.unsafe_heatmap.get(key, 0.0) + dmg
        except Exception:
            pass

        # Still track scored-on breaches for reactive defense (v13 behavior).
        for b in events.get("breach", []):
            try:
                loc, _, _, _, owner = b
                if owner == 2:
                    self.scored_on.append(loc)
            except Exception:
                continue


if __name__ == "__main__":
    AlgoStrategy().start()
