import gamelib
import random
import math
import warnings
from sys import maxsize
import json


"""
Most of the algo code you write will be in this file unless you create new
modules yourself. Start by modifying the 'on_turn' function.

Advanced strategy tips:

  - You can analyze action frames by modifying on_action_frame function

  - The GameState.map object can be manually manipulated to create hypothetical
  board states. Though, we recommended making a copy of the map to preserve
  the actual current map state.
"""

class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write('Random seed: {}'.format(seed))

    def on_game_start(self, config):
        """
        Read in config and perform any initial setup here
        """
        gamelib.debug_write('Configuring your custom algo strategy...')
        self.config = config
        global FILTER, ENCRYPTOR, DESTRUCTOR, PING, EMP, SCRAMBLER, BITS, CORES
        FILTER = config["unitInformation"][0]["shorthand"]
        ENCRYPTOR = config["unitInformation"][1]["shorthand"]
        DESTRUCTOR = config["unitInformation"][2]["shorthand"]
        PING = config["unitInformation"][3]["shorthand"]
        EMP = config["unitInformation"][4]["shorthand"]
        SCRAMBLER = config["unitInformation"][5]["shorthand"]
        BITS = 0
        CORES = 1
        # This is a good place to do initial setup
        #self.scored_on_locations = []
        self.scored_on_locations = set()




    def on_turn(self, turn_state):
        """
        game_state = gives you state of arena
        this executes every turn --> starter_strategy is used every turn
        """
        game_state = gamelib.GameState(self.config, turn_state)
        gamelib.debug_write('Performing turn {} of your custom algo strategy'.format(game_state.turn_number))
        game_state.suppress_warnings(True)  #Comment or remove this line to enable warnings.

        self.starter_strategy(game_state)

        game_state.submit_turn()


    def starter_strategy(self, game_state):
        self.build_defences(game_state)

        # Sending more at once is better since attacks can only hit a single ping at a time
        if game_state.get_resource(BITS, 0) > 14:
            # To simplify we will just check sending them from back left and right
            #ping_spawn_location_options = [[11, 2]]
            #best_location = self.least_damage_spawn_location(game_state, ping_spawn_location_options)
            #game_state.attempt_spawn(PING, best_location, 1000)
            ping_locs = [[2,11],[3,10],[4,9],[5,8],[6,7],[7,6],[8,5], [9,4], [10, 3], [11, 2], [25,11],[24,10],[23,9],[22,8],[21,7],[20,6], [19,5], [18,4], [17,3],[16,2],[15,1]]
            best_ping_loc = self.least_damage_spawn_location(game_state, ping_locs)

            if (game_state.turn_number % 2 == 0):
                spawned = game_state.attempt_spawn(PING, best_ping_loc, 1000)
                if not spawned:
                    left_scrambler_spawn = [5, 8]
                    right_scrambler_spawn = [22,8]
                    if (game_state.contains_stationary_unit([15,1])):
                        left_scrambler_spawn = [13, 0]
                        right_scrambler_spawn = [14,0]
                    while (game_state.attempt_spawn(PING, left_scrambler_spawn, 1)):
                        game_state.attempt_spawn(PING, left_scrambler_spawn, 1)
                        game_state.attempt_spawn(PING, right_scrambler_spawn, 1)
            else:
                spawned = game_state.attempt_spawn(EMP, best_ping_loc, 1000)
                if not spawned:
                    left_scrambler_spawn = [5, 8]
                    right_scrambler_spawn = [22,8]
                    if (game_state.contains_stationary_unit([15,1])):
                        left_scrambler_spawn = [13, 0]
                        right_scrambler_spawn = [14,0]
                    while (game_state.attempt_spawn(EMP, left_scrambler_spawn, 1)):
                        game_state.attempt_spawn(EMP, left_scrambler_spawn, 1)
                        game_state.attempt_spawn(EMP, right_scrambler_spawn, 1)
        else:
            #self.stall_with_scramblers(game_state)
            #game_state.attempt_spawn(SCRAMBLER, [[6, 7], [21,7]])
            if game_state.get_resource(BITS, 1) >= 10:
                self.stall_with_scramblers(game_state)



    def build_defences(self, game_state):
        '''
        # Brian Defense
        destructor_locations = [[0, 13], [27, 13], [1, 12], [26, 12]]
        game_state.attempt_spawn(DESTRUCTOR, destructor_locations)

        filter_locations = [[1,13], [26,13]]
        game_state.attempt_spawn(FILTER, filter_locations)
        '''

        # Wang Defense Formation

        # def_1
        destructors_points = [[3, 12], [24, 12], [6, 11], [21, 11], [9, 10], [18, 10], [12, 9], [15, 9]]
        filters_points = [[3, 13], [24, 13], [6, 12], [21, 12], [9, 11], [18, 11], [12, 10], [15, 10]]
        encryptors_points = []
        game_state.attempt_spawn(FILTER, filters_points)
        game_state.attempt_spawn(DESTRUCTOR, destructors_points)
        #game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_2
        destructors_points = [[1, 12], [2, 12], [25, 12], [26, 12], [7, 11], [20, 11], [10, 10], [17, 10]]
        filters_points = [[0, 13], [1, 13], [26, 13], [27, 13]]
        game_state.attempt_spawn(FILTER, filters_points)
        game_state.attempt_spawn(DESTRUCTOR, destructors_points)
        #game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_3
        destructors_points = [[4, 12], [23, 12], [8, 11], [19, 11], [13, 9], [14, 9]]
        filters_points = [[2, 13], [4, 13], [23, 13], [25, 13], [7, 12], [20, 12]]
        game_state.attempt_spawn(FILTER, filters_points)
        game_state.attempt_spawn(DESTRUCTOR, destructors_points)
        #game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_4
        destructors_points = [[2, 11], [3, 11], [24, 11], [25, 11], [6, 10], [7, 10], [8, 10], [19, 10], [20, 10], [21, 10], [10, 9], [17, 9], [12, 8], [15, 8]]
        filters_points = [[8, 12], [19, 12], [10, 11], [17, 11], [13, 10], [14, 10]]
        game_state.attempt_spawn(FILTER, filters_points)
        game_state.attempt_spawn(DESTRUCTOR, destructors_points)
        #game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_5
        destructors_points = [[4, 11], [23, 11], [4, 10], [23, 10], [6, 9], [9, 9], [18, 9], [21, 9], [10, 8], [13, 8], [14, 8], [17, 8]]
        # game_state.attempt_spawn(FILTER, filters_points)
        game_state.attempt_spawn(DESTRUCTOR, destructors_points)
        ##game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_6
        if game_state.get_resource(CORES, 0) > 6:
            encryptors_points = [[13, 0], [15, 1], [13, 1], [15, 2], [14, 3], [13, 3], [12, 3], [10, 3], [10, 4], [11, 5], [12, 5], [13, 5], [14, 5], [15, 5], [16, 5], [18, 5]]
            # game_state.attempt_spawn(FILTER, filters_points)
            # game_state.attempt_spawn(DESTRUCTOR, destructors_points)
            game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

        # def_7
        if game_state.get_resource(CORES, 0) > 10:
            encryptors_points = [[18, 4], [17, 3], [10, 5], [12, 1], [16, 3], [15, 3]]
            # game_state.attempt_spawn(FILTER, filters_points)
            # game_state.attempt_spawn(DESTRUCTOR, destructors_points)
            game_state.attempt_spawn(ENCRYPTOR, encryptors_points)

    '''
    def stall_with_scramblers(self, game_state):
        for location in list(set(self.scored_on_locations)):
            # Build destructor one space above so that it doesn't block our own edge spawn locations
            game_state.attempt_spawn(SCRAMBLER, location)
    '''
    def stall_with_scramblers(self, game_state):
        for loc in list(self.scored_on_locations):
            gamelib.debug_write("Scrambler Location: {}".format(loc))
            
            # send a scrambler for every 3 enemy num of bits
            num_scramblers = (int) (game_state.get_resource(BITS, 1))//4 + 1
            gamelib.debug_write("NumScrams: {}".format(num_scramblers))
            if num_scramblers > 3:
                num_scramblers = 3
            for i in range(0, num_scramblers):
                while loc[1] >= 11:
                    x, y = loc
                    if loc[0] > loc[1]:
                        x -= 1
                        y -= 1
                    if loc[1] > loc[0]:
                        x += 1
                        y -= 1
                game_state.attempt_spawn(SCRAMBLER, [x, y])


    def emp_line_strategy(self, game_state):
        """
        Build a line of the cheapest stationary unit so our EMP's can attack from long range.
        """
        # First let's figure out the cheapest unit
        # We could just check the game rules, but this demonstrates how to use the GameUnit class
        stationary_units = [FILTER, DESTRUCTOR, ENCRYPTOR]
        cheapest_unit = FILTER
        for unit in stationary_units:
            unit_class = gamelib.GameUnit(unit, game_state.config)
            if unit_class.cost < gamelib.GameUnit(cheapest_unit, game_state.config).cost:
                cheapest_unit = unit

        # Now let's build out a line of stationary units. This will prevent our EMPs from running into the enemy base.
        # Instead they will stay at the perfect distance to attack the front two rows of the enemy base.
        for x in range(27, 5, -1):
            game_state.attempt_spawn(cheapest_unit, [x, 11])

        # Now spawn EMPs next to the line
        game_state.attempt_spawn(EMP, [24, 10], 1000)
    
    
    def least_damage_spawn_location(self, game_state, location_options):
        """
        This function will help us guess which location is the safest to spawn moving units from.
        It gets the path the unit will take then checks locations on that path to
        estimate the path's damage risk.
        """
        damages = []
        # Get the damage estimate each path will take


        for location in location_options:
            path = game_state.find_path_to_edge(location)
            damage = 0
            if path is not None:
                for path_location in path:
                # Get number of enemy destructors that can attack the final location and multiply by destructor damage
                    damage += len(game_state.get_attackers(path_location, 0)) * gamelib.GameUnit(DESTRUCTOR, game_state.config).damage
                damages.append(damage)

        # Now just return the location that takes the least damage
        return location_options[damages.index(min(damages))]
    
    def detect_enemy_unit(self, game_state, unit_type=None, valid_x = None, valid_y = None):
        total_units = 0
        for location in game_state.game_map:
            if game_state.contains_stationary_unit(location):
                for unit in game_state.game_map[location]:
                    if unit.player_index == 1 and (unit_type is None or unit.unit_type == unit_type) and (valid_x is None or location[0] in valid_x) and (valid_y is None or location[1] in valid_y):
                        total_units += 1
        return total_units

    def filter_blocked_locations(self, locations, game_state):
        filtered = []
        for location in locations:
            if not game_state.contains_stationary_unit(location):
                filtered.append(location)
        return filtered

    def on_action_frame(self, turn_string):
        """
        This is the action frame of the game. This function could be called
        hundreds of times per turn and could slow the algo down so avoid putting slow code here.
        Processing the action frames is complicated so we only suggest it if you have time and experience.
        Full doc on format of a game frame at: https://docs.c1games.com/json-docs.html
        """
        # Let's record at what position we get scored on
        state = json.loads(turn_string)
        events = state["events"]
        breaches = events["breach"]
        for breach in breaches:
            location = breach[0]
            unit_owner_self = True if breach[4] == 1 else False
            # When parsing the frame data directly,
            # 1 is integer for yourself, 2 is opponent (StarterKit code uses 0, 1 as player_index instead)
            if not unit_owner_self:
                gamelib.debug_write("Got scored on at: {}".format(location))
                #self.scored_on_locations.append(location)
                self.scored_on_locations.add((location[0], location[1]))
                gamelib.debug_write("All locations: {}".format(self.scored_on_locations))


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
