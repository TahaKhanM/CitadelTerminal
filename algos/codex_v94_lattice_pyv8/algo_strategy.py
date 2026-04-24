"""codex_v94_lattice_pyv8: v73 lattice defense with v79 pressure offense."""
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

        walls = [mirror(p) for p in self.RAW_WALLS]
        turrets = [mirror(p) for p in self.RAW_TURRETS]
        supports = [mirror(p) for p in self.RAW_SUPPORTS]

        wall_limit = min(len(walls), 6 + t * 3)
        turret_limit = min(len(turrets), 2 + t // 2)
        support_limit = 0 if t < 14 else min(len(supports), 1 + (t - 14) // 2)

        gs.attempt_spawn(WALL, walls[:wall_limit])
        gs.attempt_spawn(TURRET, turrets[:turret_limit])
        if support_limit:
            gs.attempt_spawn(SUPPORT, supports[:support_limit])

        if t >= 12:
            gs.attempt_upgrade(turrets[:min(turret_limit, 6)])
        if t >= 18 and support_limit:
            gs.attempt_upgrade(supports[:support_limit])
        if t >= 28:
            gs.attempt_upgrade(turrets[:turret_limit])

        self._attack(gs)

        gs.submit_turn()

    def _attack(self, gs):
        t = gs.turn_number
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        if t <= 1 and mp >= interceptor_cost:
            gs.attempt_spawn(INTERCEPTOR, [7, 6], 1)
            return

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

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
