"""v22_demo_gate: v21 + anti-demo-line (upgraded Turrets at [10,12] and [17,12] by T8).
Parent: v21_temporal_phase_gate. Tag: "anti-demo-line".
Hypothesis: ranked demo_line opponents (ELO 1800+, 3/6 coin flip) push 10-15 Demolishers
from deep back spawns; 2 upgraded Turrets at the midline one-shot Demo chunks of 5
HP each, breaking the push before it compresses.
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
        # v22: anti-demo-line — gate Turrets at [10,12] / [17,12] by T8.
        self.demo_gate_locs = [[10, 12], [17, 12]]
        self.opp_demo_count = 0
        self.opp_is_demo_line = False

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        # INVENTION #2: update temporal phase-gate state before any spend.
        self._update_phase_gate(gs)

        self._build_defense(gs)
        self._build_demo_gate(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._phase_gate_defense(gs)
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

    def _build_demo_gate(self, gs):
        """v22: place upgraded Turrets at [10,12] and [17,12] by T8 to gate
        demo_line pushes from [3,17] / [24,17]. Budget: 2 * 6 SP = 12 SP full.
        Fallback: base Turrets (2 * 2 SP = 4 SP) if SP < 12 by T7, then upgrade
        next turn when affordable.
        """
        t = gs.turn_number
        if t > 8 and not self.opp_is_demo_line:
            # After T8 keep the gate alive only via repair path below.
            pass
        sp = gs.get_resource(SP)
        turret_cost = gs.type_cost(TURRET)[SP]
        for loc in self.demo_gate_locs:
            if gs.game_map.in_arena_bounds(loc) and not gs.contains_stationary_unit(loc):
                if gs.get_resource(SP) >= turret_cost:
                    gs.attempt_spawn(TURRET, loc)
        if t >= 7 or gs.get_resource(SP) >= 12:
            gs.attempt_upgrade(self.demo_gate_locs)

        # Repair: if demo_line detected and gate Turret HP < 30%, replace.
        if self.opp_is_demo_line:
            for loc in self.demo_gate_locs:
                for u in gs.game_map[loc[0], loc[1]]:
                    if u.unit_type == TURRET and u.health < 0.3 * u.max_health:
                        gs.attempt_remove(loc)
                        break

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

        # INVENTION #2: defer Scout/Demo bursts while absorbing opponent wave.
        deferring = t < self.attack_cooldown_until
        # Counter-attack turn (cooldown just expired): lower Scout threshold.
        counter = (self.attack_cooldown_until > 0
                   and t == self.attack_cooldown_until)
        scout_thresh = 6 * scout_cost if counter else 10 * scout_cost

        enemy_front = self._count_enemy_front(gs)

        if not deferring and enemy_front >= 10 and mp >= 4 * demo_cost:
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
        # v22: count enemy Demolisher spawns (EI shorthand). Mark demo_line
        # archetype if they've fielded >5 Demos by T10.
        turn_info = state.get("turnInfo", [])
        turn_num = turn_info[1] if len(turn_info) > 1 else 0
        for s in events.get("spawn", []):
            if len(s) < 4:
                continue
            _loc, unit_type, _uid, owner = s[0], s[1], s[2], s[3]
            if owner == 2 and unit_type == 4:  # 4 = DEMOLISHER type index
                self.opp_demo_count += 1
        if turn_num <= 10 and self.opp_demo_count > 5:
            self.opp_is_demo_line = True


if __name__ == "__main__":
    AlgoStrategy().start()
