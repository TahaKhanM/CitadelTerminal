"""v18 = v13_second_ring + 2nd-order Markov spawn-edge predictor.

Phase 5 hypothesis (docs/ADVANCED_STRATEGIES_PROMPT.md § A Tier 3.4):
  Opponent spawn edges are autocorrelated; a 2nd-order Markov chain over
  {LEFT, RIGHT, BOTH, NONE} predicts next spawn > 60 % of the time after
  T5. When we have high confidence in a predicted lane, bias one extra
  turret and pre-spawn an Interceptor there — cheap asymmetric defense.

Perspective convention inside this algo:
  LEFT  = threat side is OUR left (enemy spawn from their TOP_RIGHT, x≥14).
  RIGHT = threat side is OUR right (enemy spawn from their TOP_LEFT, x<14).
  BOTH  = enemy spawned on both edges in the same turn.
  NONE  = no enemy mobile observed.

Baseline = v13 (caravan showed 0 % win rate vs v13 over 20 games in the
pre-Phase-5 canary, so the support-caravan path was abandoned; v18
extends v13's defensive core).
"""
import os
import sys
import json

import gamelib

_HERE = os.path.dirname(os.path.abspath(__file__))
_REPO_ROOT = os.path.dirname(os.path.dirname(_HERE))
if _REPO_ROOT not in sys.path:
    sys.path.insert(0, _REPO_ROOT)
from gamelib_ext.spawn_predictor import (  # noqa: E402
    SpawnPredictor,
    LEFT,
    RIGHT,
)


MARKOV_CONFIDENCE_BIAS = 0.40       # turret bias kicks in above this
MARKOV_CONFIDENCE_INTERCEPT = 0.50  # pre-spawn interceptor above this
MARKOV_MIN_SAMPLES = 2              # need ≥2 observed transitions before trusting
MARKOV_PRESPAWN_COOLDOWN = 2        # ≥2 turns between pre-spawn interceptors
MARKOV_MAX_BIAS_TURRETS_PER_TURN = 2
LEFT_INTERCEPTOR_SPAWN = [7, 6]
RIGHT_INTERCEPTOR_SPAWN = [20, 6]
# Extra turret placement hotspots biased per predicted side. Ordered by
# preference; first vacant + in-bounds tile wins. y=9 and y=11 per spec.
LEFT_BIAS_TURRETS = [[5, 9], [4, 11], [3, 11], [6, 11]]
RIGHT_BIAS_TURRETS = [[22, 9], [23, 11], [24, 11], [21, 11]]


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
        self.scored_on = []
        self.path_cache = {}
        self._predictor = SpawnPredictor()
        self._edges_this_turn = set()   # filled by on_action_frame
        self._last_ingested_turn = -1
        self._last_prespawn_turn = -10

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._flush_markov(gs.turn_number)

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._markov_bias_defense(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._markov_prespawn_interceptor(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    def _flush_markov(self, turn_n):
        if turn_n == 0:
            return
        prev = turn_n - 1
        if prev <= self._last_ingested_turn:
            return
        self._predictor.ingest(prev, self._edges_this_turn)
        self._edges_this_turn = set()
        self._last_ingested_turn = prev

    def _predicted_side(self, gs):
        if gs.turn_number < 5:
            return None, 0.0
        if self._predictor.samples < MARKOV_MIN_SAMPLES:
            return None, 0.0
        p = self._predictor.predict()
        side, conf = max(p.items(), key=lambda kv: kv[1])
        if side not in (LEFT, RIGHT):
            return None, conf
        return side, conf

    def _markov_bias_defense(self, gs):
        side, conf = self._predicted_side(gs)
        if side is None or conf < MARKOV_CONFIDENCE_BIAS:
            return
        candidates = LEFT_BIAS_TURRETS if side == LEFT else RIGHT_BIAS_TURRETS
        placed = 0
        for loc in candidates:
            if placed >= MARKOV_MAX_BIAS_TURRETS_PER_TURN:
                break
            if not gs.game_map.in_arena_bounds(loc):
                continue
            if gs.contains_stationary_unit(loc):
                continue
            if gs.attempt_spawn(TURRET, loc) > 0:
                placed += 1

    def _markov_prespawn_interceptor(self, gs):
        t = gs.turn_number
        if t - self._last_prespawn_turn < MARKOV_PRESPAWN_COOLDOWN:
            return
        side, conf = self._predicted_side(gs)
        if side is None or conf < MARKOV_CONFIDENCE_INTERCEPT:
            return
        if gs.get_resource(MP) < gs.type_cost(INTERCEPTOR)[MP]:
            return
        spawn = LEFT_INTERCEPTOR_SPAWN if side == LEFT else RIGHT_INTERCEPTOR_SPAWN
        if not gs.game_map.in_arena_bounds(spawn):
            return
        if gs.contains_stationary_unit(spawn):
            return
        if gs.attempt_spawn(INTERCEPTOR, spawn) > 0:
            self._last_prespawn_turn = t

    def _build_defense(self, gs):
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]

        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= 1:
            gs.attempt_upgrade(center_turrets)
        if t >= 2:
            gs.attempt_upgrade(sidelane_deep)
        if t >= 3:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 7:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        for loc in center_turrets + sidelane_deep + outer_turrets + inner_corners + outer_corners:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded and u.health < 0.6 * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _spend_hoard(self, gs):
        t = gs.turn_number
        if t < 5:
            return
        second_ring = [[11, 5], [16, 5]]
        gs.attempt_spawn(TURRET, second_ring)
        if t >= 8:
            gs.attempt_upgrade(second_ring)
        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])
        if t >= 12:
            gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])
        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])
        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

    def _reactive_defense(self, gs):
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y], [x, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y], [x, y]]
            for build in candidates:
                bx, by = build
                if by > 13 or by < 0:
                    continue
                if not gs.game_map.in_arena_bounds(build):
                    continue
                if gs.contains_stationary_unit(build):
                    continue
                key = (bx, by)
                if key in placed:
                    continue
                if gs.attempt_spawn(TURRET, build) > 0:
                    placed.add(key)
                    break

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        if enemy_mp >= 6 * scout_cost and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)

        if enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= 10 * scout_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    def _count_enemy_front(self, gs):
        n = 0
        for y in (14, 15):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 1
        return n

    def _best_spawn(self, gs, options):
        best = None
        best_dmg = None
        t_dmg_up = float(self.config["unitInformation"][2].get(
            "upgrade", {}).get("attackDamageWalker",
            self.config["unitInformation"][2].get("attackDamageWalker", 6)))
        for opt in options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            dmg = 0.0
            for p in path:
                dmg += len(gs.get_attackers(p, 0)) * t_dmg_up
            if best is None or dmg < best_dmg:
                best = opt
                best_dmg = dmg
        return best

    def _cached_path(self, gs, loc):
        key = (loc[0], loc[1])
        if key in self.path_cache:
            return self.path_cache[key]
        try:
            path = gs.find_path_to_edge(loc)
        except Exception:
            path = []
        self.path_cache[key] = path
        return path

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on.append(loc)
        ui = self.config["unitInformation"]
        shorthands = [u.get("shorthand") for u in ui]
        mobile_shorthands = {SCOUT, DEMOLISHER, INTERCEPTOR}
        for sp in events.get("spawn", []):
            try:
                loc, type_idx, _uid, player = sp[:4]
            except Exception:
                continue
            if player != 2:
                continue
            if type_idx < 0 or type_idx >= len(shorthands):
                continue
            sh = shorthands[type_idx]
            if sh not in mobile_shorthands:
                continue
            try:
                ex = int(loc[0])
            except Exception:
                continue
            # Enemy spawn at x<14 (their TOP_LEFT edge) → unit travels
            # down-right → hits OUR right → label RIGHT. x≥14 → LEFT.
            self._edges_this_turn.add(RIGHT if ex < 14 else LEFT)


if __name__ == "__main__":
    AlgoStrategy().start()
