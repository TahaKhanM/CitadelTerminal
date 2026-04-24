"""codex_v105_capline_lattice_center: v104 with capline center attack.

Keep the pyv8-style Demolisher/Interceptor pressure that beats v68-like edge
caravans. Only pivot into the v80 banana burst when the opponent has committed
to five or more Supports, which is the high-shield lattice failure mode.
"""
import gamelib


def mirror(loc):
    return [27 - loc[0], 27 - loc[1]]


class AlgoStrategy(gamelib.AlgoCore):
    RAW_WALLS = [
        [0, 14], [1, 14], [26, 14], [27, 14], [2, 14], [3, 15],
        [4, 16], [5, 17], [6, 18], [7, 18], [8, 18], [9, 18],
        [10, 18], [11, 18], [12, 18], [13, 18], [14, 18], [15, 18],
        [16, 19], [16, 23], [23, 16], [24, 15], [25, 14],
        [20, 19], [21, 18], [22, 17], [17, 22], [18, 21], [19, 20],
        [15, 21], [15, 24], [16, 20],
    ]
    RAW_TURRETS = [
        [3, 16], [24, 16], [15, 19], [12, 19], [22, 18],
        [20, 20], [18, 22], [19, 21], [15, 20], [17, 23],
        [14, 21], [17, 24], [18, 23], [19, 22], [20, 21],
        [21, 20], [22, 19],
    ]
    RAW_SUPPORTS = [
        [9, 21], [9, 20], [10, 21], [10, 20], [11, 21],
        [11, 20], [12, 21], [12, 20], [13, 21], [13, 20],
        [11, 22], [10, 22],
    ]

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
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        t = gs.turn_number

        self._build_defense(gs)
        self._attack(gs)
        gs.submit_turn()

    def _build_defense(self, gs):
        t = gs.turn_number
        capline = self._enemy_capline(gs)
        main_turrets = [[13, 12], [14, 12], [4, 12], [23, 12],
                        [8, 11], [19, 11], [12, 11], [15, 11],
                        [11, 11], [16, 11]]
        gs.attempt_spawn(TURRET, main_turrets[:4])
        if t >= 1:
            gs.attempt_spawn(TURRET, main_turrets[4:6])
        if t >= 8:
            gs.attempt_spawn(TURRET, main_turrets[6:8])
        if t >= 9:
            gs.attempt_spawn(TURRET, main_turrets[8:10])
        if t >= 10:
            gs.attempt_spawn(TURRET, [[1, 12], [26, 12]])

        # Full cap line from the 1837-ELO replay; leave no diagonal corner gap.
        wall_line = [[x, 13] for x in list(range(0, 13)) + list(range(15, 28))]
        for i in range(0, len(wall_line), 4):
            if t >= 2 + i // 4:
                gs.attempt_spawn(WALL, wall_line[i:i + 4])

        # Reinforce the recurrent ranked hot spots without occupying mobile
        # launch tiles [7,6], [20,6], [9,4].
        if t >= 14:
            gs.attempt_spawn(TURRET, [[6, 10], [21, 10]])
        if t >= 24:
            gs.attempt_spawn(TURRET, [[5, 8], [22, 8]])
        if t >= 34:
            gs.attempt_spawn(TURRET, [[4, 9], [23, 9]])
        if t >= 34:
            gs.attempt_spawn(TURRET, [[2, 11], [25, 11]])

        if capline and t >= 12:
            self._build_capline_lowlattice(gs)

        if t >= 10:
            gs.attempt_upgrade(main_turrets[:6])
        if t >= 18:
            gs.attempt_upgrade(main_turrets[6:10])
            gs.attempt_upgrade([[6, 10], [21, 10]])
            gs.attempt_upgrade([[1, 12], [26, 12]])
        if t >= 28:
            gs.attempt_upgrade([[5, 8], [22, 8]])
        if t >= 38:
            gs.attempt_upgrade([[4, 9], [23, 9]])

        supports = [[13, 9], [14, 9], [12, 8], [15, 8]]
        if t >= 22:
            gs.attempt_spawn(SUPPORT, supports[:2])
        if t >= 26:
            gs.attempt_upgrade(supports[:2])
        if t >= 36:
            gs.attempt_spawn(SUPPORT, supports[2:])
        if t >= 42:
            gs.attempt_upgrade(supports)

    def _build_capline_lowlattice(self, gs):
        t = gs.turn_number
        age = max(0, t - 12)
        walls = [mirror(p) for p in self.RAW_WALLS]
        turrets = [mirror(p) for p in self.RAW_TURRETS]
        supports = [mirror(p) for p in self.RAW_SUPPORTS]

        wall_limit = min(len(walls), 8 + age * 3)
        turret_limit = min(len(turrets), 2 + age // 2)
        support_limit = 0 if age < 8 else min(len(supports), 1 + (age - 8) // 2)

        gs.attempt_spawn(WALL, walls[:wall_limit])
        gs.attempt_spawn(TURRET, turrets[:turret_limit])
        if support_limit:
            gs.attempt_spawn(SUPPORT, supports[:support_limit])
        if age >= 6:
            gs.attempt_upgrade(turrets[:min(turret_limit, 6)])
        if age >= 10 and support_limit:
            gs.attempt_upgrade(supports[:support_limit])
        if age >= 16:
            gs.attempt_upgrade(turrets[:turret_limit])

    def _attack(self, gs):
        t = gs.turn_number
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        if t <= 1 and mp >= interceptor_cost:
            gs.attempt_spawn(INTERCEPTOR, [7, 6], 1)
            return

        # v78 copied pyv8's small [1,12] Scout poke, but [1,12] is now a
        # permanent corner turret to stop the lattice corner leak.

        lattice_mode = self._enemy_lattice(gs)
        if lattice_mode and t >= 25:
            if (t % 3 == 0 or mp >= 18) and mp >= 3 * demo_cost:
                gs.attempt_spawn(DEMOLISHER, [2, 11], 3)
                if gs.get_resource(MP) >= 5 * scout_cost:
                    gs.attempt_spawn(SCOUT, [3, 10], 1000)
            elif t >= 45 and mp >= interceptor_cost:
                gs.attempt_spawn(INTERCEPTOR, [24, 10], min(3, int(mp // interceptor_cost)))
            return

        if self._enemy_capline(gs) and t >= 15:
            if t % 4 == 1 and mp >= demo_cost + interceptor_cost:
                gs.attempt_spawn(INTERCEPTOR, [15, 1], 1)
                gs.attempt_spawn(DEMOLISHER, [14, 0], 1000)
            elif mp >= 2 * demo_cost:
                gs.attempt_spawn(DEMOLISHER, [14, 0], 1000)
            elif mp >= 10 * scout_cost:
                gs.attempt_spawn(SCOUT, [14, 0], 1000)
            return

        # One Interceptor per turn creates the same persistent lane pressure
        # seen in python-algo-v8; Demolishers follow before the banana switch.
        if t >= 17 and mp >= interceptor_cost:
            if t < 46:
                gs.attempt_spawn(INTERCEPTOR, [7, 6], 1)
            else:
                gs.attempt_spawn(INTERCEPTOR, [9, 4], 1)
            mp = gs.get_resource(MP)

        if t >= 13 and (t % 3 == 0 or mp >= 15) and mp >= demo_cost:
            spawn = [24, 10]
            if t >= 63 and t % 6 == 3:
                spawn = [3, 10]
            elif t >= 69 and t % 6 == 3:
                spawn = [18, 4]
            gs.attempt_spawn(DEMOLISHER, spawn, 1000)
        elif t >= 45 and mp >= 18 * scout_cost:
            gs.attempt_spawn(SCOUT, [14, 0], 1000)

    def _enemy_lattice(self, gs):
        supports = 0
        turrets = 0
        walls = 0
        high_structures = 0
        for loc in gs.game_map:
            x, y = loc
            if y < 14:
                continue
            for unit in gs.game_map[x, y]:
                if unit.player_index != 1 or not unit.stationary:
                    continue
                if unit.unit_type == SUPPORT:
                    supports += 1
                elif unit.unit_type == TURRET:
                    turrets += 1
                elif unit.unit_type == WALL:
                    walls += 1
                if y >= 18:
                    high_structures += 1
        return (high_structures >= 20 and turrets >= 6 and walls >= 25) or supports >= 6

    def _enemy_capline(self, gs):
        y14 = 0
        y16 = 0
        high = 0
        for loc in gs.game_map:
            x, y = loc
            if y < 14:
                continue
            for unit in gs.game_map[x, y]:
                if unit.player_index != 1 or not unit.stationary:
                    continue
                if y == 14:
                    y14 += 1
                elif y == 16:
                    y16 += 1
                if y >= 18:
                    high += 1
        return y14 >= 18 and y16 <= 8 and high < 5

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
