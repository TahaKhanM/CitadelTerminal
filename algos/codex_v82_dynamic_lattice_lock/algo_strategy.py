"""codex_v82_dynamic_lattice_lock: pyv8 pressure with lattice-only banana.

v80 improved the lattice matchup but paid for it by always abandoning the
pyv8 pressure that beats v68. This variant detects support-heavy enemy tops,
then pivots into the banana burst and spends late SP on the right corner leak.
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

        self._build_defense(gs)
        self._attack(gs)
        gs.submit_turn()

    def _build_defense(self, gs):
        t = gs.turn_number
        lattice = self._enemy_lattice(gs)
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

        # The v21_loss_03 / v73 pattern breaks through (26,12) late with
        # center Demolishers. Only build the extra right lock once the enemy
        # reveals a support lattice so [24,10] remains available versus v68.
        if lattice and t >= 22:
            gs.attempt_spawn(TURRET, [[25, 11]])
        if lattice and t >= 24:
            gs.attempt_spawn(TURRET, [[24, 10]])
        if lattice and t >= 28:
            gs.attempt_spawn(TURRET, [[24, 11]])

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
        if lattice and t >= 26:
            gs.attempt_upgrade([[25, 11], [26, 12]])
        if lattice and t >= 30:
            gs.attempt_upgrade([[24, 10], [24, 11]])

        supports = [[13, 9], [14, 9], [12, 8], [15, 8]]
        if t >= 22:
            gs.attempt_spawn(SUPPORT, supports[:2])
        if t >= 26:
            gs.attempt_upgrade(supports[:2])
        if t >= 36:
            gs.attempt_spawn(SUPPORT, supports[2:])
        if t >= 42:
            gs.attempt_upgrade(supports)

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

        lattice = self._enemy_lattice(gs)

        if lattice and t >= 23 and (t % 2 == 1 or mp >= 15) and mp >= 2 * demo_cost:
            demo_count = 3 if mp >= 3 * demo_cost else 2
            gs.attempt_spawn(DEMOLISHER, [2, 11], demo_count)
            if gs.get_resource(MP) >= 5 * scout_cost:
                gs.attempt_spawn(SCOUT, [3, 10], 1000)
            return

        # One Interceptor per turn creates the same persistent lane pressure
        # seen in python-algo-v8; Demolishers follow before the banana switch.
        if t >= 17 and mp >= interceptor_cost:
            if t < 46:
                gs.attempt_spawn(INTERCEPTOR, [7, 6], 1)
            else:
                gs.attempt_spawn(INTERCEPTOR, [9, 4], 1)
            mp = gs.get_resource(MP)

        if 13 <= t < 25 and (t % 3 == 0 or mp >= 15) and mp >= demo_cost:
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
                if y >= 18:
                    high_structures += 1
        return supports >= 5 or (supports >= 4 and turrets >= 10 and high_structures >= 18)

    def on_action_frame(self, turn_string):
        pass


if __name__ == "__main__":
    AlgoStrategy().start()
