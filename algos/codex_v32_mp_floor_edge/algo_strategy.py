"""codex_v32_mp_floor_edge: support-scout counter with MP-floor edge reserve.

Parent: v21_quadrant_adaptive_support / v21_temporal_phase_gate.
Tag: "quadrant-adaptive-support".
Hypothesis: side Interceptors help only if they do not starve the 12-Scout
counterattack. Spend surplus MP above the burst floor on edge defense.
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
        # INVENTION #2: temporal phase gate — track opponent MP turn-over-turn.
        self.enemy_mp_history = []  # ring buffer last 5 turns
        self.attack_cooldown_until = 0  # turn_number at which we re-enable attack
        # INVENTION #3: quadrant-adaptive Support placement state.
        self.recent_breaches = []  # list[(x, y, turn)] — our edge breached
        self.enemy_spawn_counts = {"NW": 0, "NE": 0, "SW": 0, "SE": 0}  # cumulative
        self.cached_quadrant = None  # last quadrant we deployed Supports to
        self.active_supports = []  # list of [x, y] placed for the cached quadrant

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        # INVENTION #2: update temporal phase-gate state before any spend.
        self._update_phase_gate(gs)

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._phase_gate_defense(gs)
        self._adaptive_support(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    def _update_phase_gate(self, gs):
        """Track opponent MP; detect sharp drop => they just attacked.

        MP decay is `round(MP * 0.75, 0.1) + income`, so a natural no-spend
        turn leaves MP nearly flat or rising (income >= decay loss at low MP).
        A drop of >5 MP turn-over-turn is unambiguously a spend event.
        """
        enemy_mp = gs.get_resource(MP, 1)
        self.enemy_mp_history.append(enemy_mp)
        if len(self.enemy_mp_history) > 5:
            self.enemy_mp_history.pop(0)
        if len(self.enemy_mp_history) >= 2:
            drop = self.enemy_mp_history[-2] - self.enemy_mp_history[-1]
            if drop > 5.0:
                # Opponent attacked; defer our attack by 2 turns, counter at +3.
                self.attack_cooldown_until = gs.turn_number + 2

    def _phase_gate_defense(self, gs):
        """Pre-empt expected survivor wave one turn before cooldown lifts."""
        if self.attack_cooldown_until <= 0:
            return
        if gs.turn_number != self.attack_cooldown_until - 1:
            return
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]
        for spawn in ([10, 3], [17, 3]):
            if gs.get_resource(MP) < interceptor_cost:
                break
            if not gs.game_map.in_arena_bounds(spawn):
                continue
            if gs.contains_stationary_unit(spawn):
                continue
            gs.attempt_spawn(INTERCEPTOR, spawn)

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

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]
        burst_floor = 12 * scout_cost
        if enemy_mp >= 8 * scout_cost and gs.turn_number >= 32:
            for spawn in ([6, 7], [21, 7], [7, 6], [20, 6]):
                if gs.get_resource(MP) - interceptor_cost < burst_floor:
                    break
                if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                    gs.attempt_spawn(INTERCEPTOR, spawn)
            return
        if enemy_mp >= 6 * scout_cost and my_mp >= interceptor_cost:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    # INVENTION #3: quadrant-adaptive Support placement.
    QUADRANT_SUPPORTS = {
        "NW": [[8, 11], [9, 11], [10, 11], [11, 11]],
        "NE": [[16, 11], [17, 11], [18, 11], [19, 11]],
        "SW": [[4, 5], [5, 5], [6, 5], [7, 5]],
        "SE": [[20, 5], [21, 5], [22, 5], [23, 5]],
    }
    QUADRANT_CENTROIDS = {"NW": (7, 10), "NE": (20, 10), "SW": (4, 3), "SE": (23, 3)}

    @staticmethod
    def _quadrant_of(x, y):
        """Return NW/NE/SW/SE for a coord in our territory (y<14), else None."""
        if y < 0 or y > 13:
            return None
        if y >= 7:
            return "NW" if x < 14 else "NE"
        return "SW" if x < 14 else "SE"

    def _compute_quadrant_burdens(self, gs):
        burdens = {"NW": 0.0, "NE": 0.0, "SW": 0.0, "SE": 0.0}
        # +3 per enemy structure with x within 4 of the quadrant's centroid x.
        for x in range(28):
            for y in range(14, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index != 1 or not u.stationary:
                        continue
                    for q, (cx, _) in self.QUADRANT_CENTROIDS.items():
                        if abs(x - cx) <= 4:
                            burdens[q] += 3.0
        # +2 per enemy mobile-unit spawn observed (cumulative, half-map x).
        for q, n in self.enemy_spawn_counts.items():
            burdens[q] += 2.0 * n
        # +5 per recent breach (within last 5 turns) near the quadrant.
        t = gs.turn_number
        for bx, by, bt in self.recent_breaches:
            if t - bt > 5:
                continue
            q = self._quadrant_of(bx, by)
            if q is not None:
                burdens[q] += 5.0
        return burdens

    def _adaptive_support(self, gs):
        """Deploy Supports to the highest-burden quadrant; reallocate on shift.

        Cost notes: base Support ~4 SP, upgrade ~4 SP (total ~8 SP per; spec's
        ~5 SP figure is approximate). We place base Supports first, then upgrade
        in budget order. Dynamic reallocation only fires every 10 turns with
        >=8 SP rebuild budget, per spec.
        """
        burdens = self._compute_quadrant_burdens(gs)
        best = max(burdens, key=lambda q: burdens[q])
        # If max burden is 0 and no history, just default to NW (harmless).
        if burdens[best] <= 0 and self.cached_quadrant is None:
            best = "NW"

        # Dynamic reallocation.
        if (self.cached_quadrant is not None
                and best != self.cached_quadrant
                and gs.turn_number % 10 == 0
                and gs.get_resource(SP) >= 8):
            for loc in self.active_supports:
                gs.attempt_remove(loc)
            self.active_supports = []
            self.cached_quadrant = best
        elif self.cached_quadrant is None:
            self.cached_quadrant = best

        q = self.cached_quadrant
        targets = self.QUADRANT_SUPPORTS[q]
        # Base Supports first (cheaper), then upgrades.
        for loc in targets:
            if gs.get_resource(SP) < gs.type_cost(SUPPORT)[SP]:
                break
            if gs.game_map.in_arena_bounds(loc) and not gs.contains_stationary_unit(loc):
                if gs.attempt_spawn(SUPPORT, loc) > 0 and loc not in self.active_supports:
                    self.active_supports.append(loc)
        for loc in targets:
            gs.attempt_upgrade(loc)

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        # INVENTION #2: defer Scout/Demo bursts while absorbing opponent wave.
        deferring = t < self.attack_cooldown_until
        # Counter-attack turn (cooldown just expired): lower Scout threshold.
        counter = (self.attack_cooldown_until > 0
                   and t == self.attack_cooldown_until)
        scout_thresh = 6 * scout_cost if counter else 12 * scout_cost

        enemy_front = self._count_enemy_front(gs)
        enemy_supports = self._count_enemy_supports(gs)

        if (not deferring and enemy_supports < 2
                and enemy_front >= 10 and mp >= 4 * demo_cost):
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if not deferring and mp >= scout_thresh:
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

    def _count_enemy_supports(self, gs):
        n = 0
        for loc in gs.game_map:
            x, y = loc
            if y < 14:
                continue
            for u in gs.game_map[x, y]:
                if u.player_index == 1 and u.stationary and u.unit_type == SUPPORT:
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
        turn_info = state.get("turnInfo", [0, 0])
        cur_turn = turn_info[1] if len(turn_info) > 1 else 0
        for b in events.get("breach", []):
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on.append(loc)
                # INVENTION #3: track our-edge breaches by quadrant.
                try:
                    self.recent_breaches.append((int(loc[0]), int(loc[1]), cur_turn))
                    # Prune entries older than ~10 turns to keep list bounded.
                    self.recent_breaches = [
                        e for e in self.recent_breaches if cur_turn - e[2] <= 10
                    ]
                except Exception:
                    pass
        # INVENTION #3: accumulate enemy mobile-unit spawn counts by quadrant half.
        # spawn event schema: [location, unit_type_index, ...]; owner field in frame.
        for s in events.get("spawn", []):
            try:
                loc = s[0]
                unit_type = s[1]
                owner = s[3] if len(s) > 3 else None
                # Mobile units: Scout(3), Demolisher(4), Interceptor(5) when ui ordering canonical.
                if owner == 2 and unit_type in (3, 4, 5):
                    x = int(loc[0])
                    # Map enemy-side spawn x to our-territory quadrant halves.
                    if x < 14:
                        # Spawned on enemy's NW -> threatens our NW & SW (top-down flow).
                        self.enemy_spawn_counts["NW"] += 1
                        self.enemy_spawn_counts["SW"] += 1
                    else:
                        self.enemy_spawn_counts["NE"] += 1
                        self.enemy_spawn_counts["SE"] += 1
            except Exception:
                continue


if __name__ == "__main__":
    AlgoStrategy().start()
