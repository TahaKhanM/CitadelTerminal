"""Synthetic replay-derived adversary: edge caravan pressure.

This is not a submission candidate. It approximates the new `v21_loss_1`
ranked replay from the opponent's bottom-player perspective:

- wide opening walls and turret ring;
- four mid/back Supports around y=7-8;
- repeated center Scout waves, with flank Demolisher pressure from [3,10]
  and [24,10].

The purpose is to make edge-lane collapse a local regression test instead of
relying only on generic `opp_*` baselines.
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

        # Replay-derived stationary layout, transformed from raw top-player
        # coordinates with [x, y] -> [27-x, 27-y].
        walls = [
            [24, 13], [23, 13], [18, 11], [9, 11], [8, 12], [7, 11],
            [4, 13], [3, 13], [20, 11], [19, 12], [13, 12], [12, 11],
            [15, 11], [14, 12], [1, 13], [0, 13], [25, 13], [22, 13],
            [5, 13], [2, 13], [27, 13], [26, 13], [6, 11], [5, 12],
            [22, 12], [21, 11], [17, 10], [10, 10],
        ]
        turrets = [[4, 12], [23, 12], [8, 11], [19, 11], [13, 11],
                   [14, 11], [18, 10], [9, 10]]

        for locs in (walls[:8], walls[8:16], walls[16:24], walls[24:]):
            gs.attempt_spawn(WALL, locs)
        gs.attempt_spawn(TURRET, turrets)
        gs.attempt_upgrade([[4, 12], [23, 12], [13, 11], [14, 11]])
        if t >= 12:
            gs.attempt_upgrade([[8, 11], [19, 11], [18, 10], [9, 10]])

        supports = [[15, 8], [12, 8], [14, 7], [13, 7]]
        if t >= 12:
            gs.attempt_spawn(SUPPORT, supports[:2])
        if t >= 14:
            gs.attempt_upgrade(supports[:2])
        if t >= 18:
            gs.attempt_spawn(SUPPORT, supports[2:])
        if t >= 20:
            gs.attempt_upgrade(supports)

        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        mp = gs.get_resource(MP)

        # Cadence intentionally resembles the replay: frequent center Scouts,
        # plus periodic flank Demolishers to tear down edge structures.
        if t >= 10 and t % 2 == 0 and mp >= 8 * scout_cost:
            spawn = [13, 0] if (t // 2) % 2 == 0 else [14, 0]
            gs.attempt_spawn(SCOUT, spawn, 1000)
        elif t >= 14 and t % 5 in (1, 4) and mp >= 2 * demo_cost:
            spawn = [24, 10] if t % 10 < 5 else [3, 10]
            gs.attempt_spawn(DEMOLISHER, spawn, 1000)
        elif t >= 25 and mp >= 12 * scout_cost:
            gs.attempt_spawn(SCOUT, [13, 0], 1000)

        gs.submit_turn()

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
