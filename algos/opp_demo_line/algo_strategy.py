"""Synthetic opponent: demolisher line.

Builds a protective wall line at y=11 across most of the board, then spawns
Demolishers at [24, 10] to chew the enemy front from range 4.5.
Exists to punish algos with front-heavy walls / exposed turrets.
"""
import gamelib


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

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)

        # Corner turrets + wall line at y=11 (leaving [24,10] spawn tile clear).
        corners = [[0, 13], [27, 13]]
        gs.attempt_spawn(TURRET, corners)
        gs.attempt_upgrade(corners)

        # Wall line across y=11 from x=6..23 (leave x=24,25,26 clear for demos)
        wall_line = [[x, 11] for x in range(6, 24)]
        gs.attempt_spawn(WALL, wall_line)

        # A couple of turrets behind the wall
        gs.attempt_spawn(TURRET, [[13, 10], [14, 10]])

        # Launch demolishers when we have >= 3 MP (1 demo) -- but prefer 4+ for swarm.
        mp = gs.get_resource(MP)
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        if mp >= 2 * demo_cost:
            gs.attempt_spawn(DEMOLISHER, [24, 10], 1000)

        gs.submit_turn()

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
