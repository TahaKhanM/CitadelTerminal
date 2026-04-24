"""Replay-derived low-base support Scout rush from v21_loss_02.

This approximates the ranked opponent as a bottom-player algo. It is used both
as a regression adversary and as a candidate shell: compact upgraded front
turrets, low-y Supports, then repeated shielded Scout waves.
"""
import gamelib


class AlgoStrategy(gamelib.AlgoCore):
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

        walls = [
            [27, 13], [0, 13], [4, 12], [3, 12], [2, 12], [1, 12],
            [26, 12], [25, 12], [24, 12], [23, 12], [10, 12], [9, 12],
            [12, 12], [11, 12], [16, 12], [15, 12], [17, 12], [18, 12],
            [19, 12], [20, 12], [14, 2], [20, 13], [14, 13],
        ]
        turrets = [
            [14, 12], [11, 12], [8, 12], [5, 12],
            [20, 12], [17, 12], [22, 10],
        ]

        for chunk in (walls[:6], walls[6:12], walls[12:18], walls[18:]):
            gs.attempt_spawn(WALL, chunk)
        gs.attempt_spawn(TURRET, turrets)

        if t >= 12:
            gs.attempt_upgrade([[20, 12], [14, 12], [20, 13], [14, 13]])
        if t >= 16:
            gs.attempt_upgrade([[11, 12], [17, 12], [8, 12], [5, 12]])

        supports = [[13, 2], [14, 2], [13, 3], [14, 3]]
        if t >= 8:
            gs.attempt_spawn(SUPPORT, supports)

        scout_cost = gs.type_cost(SCOUT)[MP]
        mp = gs.get_resource(MP)
        if t >= 10 and mp >= 8 * scout_cost:
            spawn = [13, 0] if t % 4 < 2 else [14, 0]
            gs.attempt_spawn(SCOUT, spawn, 1000)
        elif t >= 22 and mp >= 14 * scout_cost:
            gs.attempt_spawn(SCOUT, [13, 0], 1000)

        gs.submit_turn()

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
