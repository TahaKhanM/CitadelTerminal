"""Synthetic opponent: scout rush.

One-archetype bot that floods Scouts from an alternating corner every few
turns with a minimal corner-pack defense. Exists to surface weaknesses in
the main algo (does it cover both spawn sides?).
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
        self.next_left = True

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        t = gs.turn_number

        # Minimal defense: corner turrets + a few mid-row.
        defense = [[0, 13], [27, 13], [4, 12], [23, 12], [13, 12], [14, 12]]
        gs.attempt_spawn(TURRET, defense)
        gs.attempt_spawn(WALL, [[1, 13], [26, 13], [2, 13], [25, 13]])

        # Every 3 turns: all-in scouts from alternating corner.
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        if t >= 2 and t % 3 == 0 and mp >= 5 * scout_cost:
            loc = [13, 0] if self.next_left else [14, 0]
            gs.attempt_spawn(SCOUT, loc, 1000)
            self.next_left = not self.next_left

        gs.submit_turn()

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
