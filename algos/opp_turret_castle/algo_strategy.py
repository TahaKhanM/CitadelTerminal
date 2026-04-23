"""Synthetic opponent: turret castle.

Dense double-layered wall+turret defense on rows 12 and 10; upgrades turrets
aggressively. No offense until turn 15, then methodical scout pressure.
Exists to punish algos that can't break dense defense.
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
        t = gs.turn_number

        # Outer ring (y=12) walls, with two funnel gaps for our own scouts.
        outer_walls = [[x, 12] for x in range(1, 27) if x not in (12, 15)]
        gs.attempt_spawn(WALL, outer_walls)

        # Inner turret ring (y=11): heavy on the center.
        turrets = [[x, 11] for x in (3, 7, 10, 13, 14, 17, 20, 24)]
        gs.attempt_spawn(TURRET, turrets)

        # Back corners as last line.
        gs.attempt_spawn(TURRET, [[0, 13], [27, 13]])

        # Upgrade turrets when we can afford it.
        gs.attempt_upgrade(turrets)
        gs.attempt_upgrade([[0, 13], [27, 13]])
        # Upgrade walls near the gaps so they don't collapse.
        gs.attempt_upgrade([[11, 12], [13, 12], [14, 12], [16, 12]])

        # Offense: only after turn 15, drop all MP into Scouts through the right gap.
        if t >= 15:
            mp = gs.get_resource(MP)
            scout_cost = gs.type_cost(SCOUT)[MP]
            if mp >= 6 * scout_cost:
                gs.attempt_spawn(SCOUT, [14, 0], 1000)

        gs.submit_turn()

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
