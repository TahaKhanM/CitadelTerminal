"""codex_v77_imitate_oleh1824: action imitation of oleh-v2 1824 ranked winner.

Generated from an official ranked replay where this P1 strategy beat v13. The
schedule is bottom-oriented; the engine mirrors it automatically when needed.
Fallback logic spends surplus MP after the scripted plan diverges.
"""
import gamelib
from collections import defaultdict

SPAWNS = {0: [[0, 13, 2], [1, 13, 2], [27, 13, 2], [26, 13, 2]],
 1: [[2, 13, 2], [25, 13, 2]],
 2: [[6, 10, 2], [21, 10, 2]],
 3: [[1, 12, 2], [26, 12, 2]],
 4: [[4, 10, 2], [5, 10, 2]],
 5: [[22, 10, 2], [23, 10, 2]],
 8: [[4, 11, 2], [5, 11, 2]],
 9: [[6, 11, 2], [8, 11, 2]],
 10: [[9, 11, 2], [10, 11, 2]],
 11: [[11, 11, 2], [12, 11, 2]],
 12: [[13, 11, 2], [14, 11, 2]],
 13: [[15, 11, 2], [16, 11, 2]],
 14: [[17, 11, 2], [18, 11, 2]],
 15: [[19, 11, 2], [20, 11, 2]],
 16: [[21, 11, 2], [22, 11, 2]],
 17: [[23, 11, 2], [3, 12, 0], [4, 12, 0]],
 18: [[5, 12, 0], [6, 12, 0], [7, 12, 0], [8, 12, 0]],
 19: [[9, 12, 0], [10, 12, 0], [11, 12, 0], [12, 12, 0]],
 20: [[13, 12, 0], [14, 12, 0], [15, 12, 0], [16, 12, 0]],
 21: [[17, 12, 0], [18, 12, 0], [19, 12, 0], [20, 12, 0]],
 22: [[21, 12, 0],
      [22, 12, 0],
      [23, 12, 0],
      [24, 12, 0],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 23: [[13, 2, 1]],
 24: [[7, 12, 0]],
 25: [[16, 11, 2], [17, 11, 2], [18, 11, 2], [15, 12, 0]],
 26: [[16, 12, 0], [17, 12, 0], [18, 12, 0]],
 27: [[14, 2, 1]],
 28: [[14, 3, 1],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 29: [[14, 4, 1]],
 30: [[7, 12, 0], [13, 3, 1]],
 31: [[14, 12, 0], [15, 12, 0], [16, 12, 0], [17, 12, 0]],
 32: [[13, 4, 1]],
 33: [[9, 11, 2],
      [10, 11, 2],
      [11, 11, 2],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 34: [[9, 12, 0], [10, 12, 0], [11, 12, 0], [12, 12, 0]],
 35: [[6, 12, 0], [7, 12, 0], [8, 12, 0]],
 37: [[10, 12, 0], [11, 12, 0], [12, 12, 0], [13, 12, 0]],
 38: [[13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 40: [[13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 42: [[2, 11, 2],
      [3, 11, 2],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 44: [[8, 11, 2],
      [6, 12, 0],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 46: [[6, 11, 2],
      [5, 12, 0],
      [6, 12, 0],
      [8, 12, 0],
      [9, 12, 0],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]],
 48: [[8, 11, 2],
      [5, 12, 0],
      [6, 12, 0],
      [8, 12, 0],
      [9, 12, 0],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3],
      [13, 0, 3]]}
UPGRADES = {6: [[2, 13]],
 7: [[25, 13]],
 36: [[6, 10]],
 38: [[21, 10]],
 39: [[4, 10], [23, 10]],
 40: [[5, 10]],
 41: [[22, 10]]}


class AlgoStrategy(gamelib.AlgoCore):
    def on_game_start(self, config):
        self.config = config
        ui = config["unitInformation"]
        global UNIT, WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR, MP, SP
        WALL = ui[0]["shorthand"]
        SUPPORT = ui[1]["shorthand"]
        TURRET = ui[2]["shorthand"]
        SCOUT = ui[3]["shorthand"]
        DEMOLISHER = ui[4]["shorthand"]
        INTERCEPTOR = ui[5]["shorthand"]
        UNIT = [WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR]
        MP = 1
        SP = 0

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        t = gs.turn_number

        spawns = SPAWNS.get(t, [])
        for x, y, unit_idx in spawns:
            if unit_idx <= 2:
                gs.attempt_spawn(UNIT[unit_idx], [x, y])

        for x, y in UPGRADES.get(t, []):
            gs.attempt_upgrade([x, y])

        mobile_counts = defaultdict(int)
        for x, y, unit_idx in spawns:
            if unit_idx >= 3:
                mobile_counts[(unit_idx, x, y)] += 1
        for (unit_idx, x, y), count in sorted(mobile_counts.items()):
            gs.attempt_spawn(UNIT[unit_idx], [x, y], count)

        self._fallback_spend(gs)
        gs.submit_turn()

    def _fallback_spend(self, gs):
        t = gs.turn_number
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        if t < 20:
            return
        if mp >= 24 * scout_cost:
            gs.attempt_spawn(SCOUT, [14, 0], 1000)
        elif t >= 30 and mp >= demo_cost + interceptor_cost:
            gs.attempt_spawn(INTERCEPTOR, [15, 1], 1)
            gs.attempt_spawn(DEMOLISHER, [14, 0], 1000)
        elif t >= 30 and mp >= 2 * demo_cost:
            gs.attempt_spawn(DEMOLISHER, [14, 0], 1000)

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
