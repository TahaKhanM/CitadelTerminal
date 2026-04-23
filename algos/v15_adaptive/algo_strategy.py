"""v15 = v14_support_caravan + archetype classifier + counter-strategies.

Hypothesis (from ADVANCED_STRATEGIES_PROMPT §3.1):
  Fixed-policy opponents can be beaten ≥75 % if we identify their archetype
  by T3 and play a pre-tuned counter. v14's static offense ties v13's
  mirror; a per-archetype policy should break that stalemate.

The classifier + counter-table live in gamelib_ext/classifier.py and this
module. Counters only alter OFFENSE (spawn threshold, unit type, edge);
defense is inherited verbatim from v14 so we don't regress vs opp_*.

Counter table:
  scout_rush       — drop more Interceptors to absorb their rush waves.
  demo_line        — use our own Demolishers (under the caravan) to
                     outrange their wall+demo line.
  turret_castle    — accumulate MP for huge bursts (threshold ↑ 20), spawn
                     through the WEAK side of their castle.
  support_caravan  — spawn Scouts aggressively; their Supports are fragile.
  balanced         — bigger scout bursts (threshold ↑ 14) and alternate
                     edges to probe both lanes.
"""
import os
import sys
import json

import gamelib

# Make repo's gamelib_ext importable when the engine launches algo_strategy.py
_HERE = os.path.dirname(os.path.abspath(__file__))
_REPO_ROOT = os.path.dirname(os.path.dirname(_HERE))
if _REPO_ROOT not in sys.path:
    sys.path.insert(0, _REPO_ROOT)
from gamelib_ext.classifier import (  # noqa: E402
    classify,
    extract_features,
    SCOUT_RUSH,
    DEMO_LINE,
    TURRET_CASTLE,
    SUPPORT_CARAVAN,
    BALANCED,
)


# -- v14 heritage constants (unchanged) --
CARAVAN_INNER = [[11, 13], [16, 13]]
CARAVAN_OUTER = [[10, 13], [17, 13]]
CARAVAN_PLACE_INNER_TURN = 8
CARAVAN_UPGRADE_INNER_TURN = 9
CARAVAN_PLACE_OUTER_TURN = 10
CARAVAN_UPGRADE_OUTER_TURN = 11

# Per-archetype offense thresholds (MP above which to burst Scouts).
#
# Default 10 mirrors v14 verbatim → never regress vs dense defenders.
# TURRET_CASTLE also kept at 10: an earlier attempt with threshold 16 lost
#   0/10 to v14 because with 5-MP/5-MP starting resources (local config)
#   v14 bursts at T3 when MP≈12.4 hits threshold 10, while v15-with-16
#   still hoards and takes 3 breaches at (7,6). Over-hoarding lets the
#   opponent burst first.
# SCOUT_RUSH / DEMO_LINE lowered to 8: these opponents have weak defense;
#   any burst breaches. Smaller / more-frequent waves keep pressure on.
SCOUT_THRESHOLD_DEFAULT = 10
SCOUT_THRESHOLD_TURRET_CASTLE = 10  # intentionally == default
SCOUT_THRESHOLD_SCOUT_RUSH = 8
SCOUT_THRESHOLD_DEMO_LINE = 8


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
        # Archetype state
        self._enemy_spawns = []  # list of (x, y, unit_type)
        self._archetype = BALANCED
        self._features = None

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        # Refresh classification from T3 onward (cheap; one scan per turn).
        if gs.turn_number >= 3:
            self._features = extract_features(
                gs, WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR,
                self._enemy_spawns,
            )
            self._archetype = classify(self._features)

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._support_caravan(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    def _support_caravan(self, gs):
        t = gs.turn_number
        if t >= CARAVAN_PLACE_INNER_TURN:
            gs.attempt_spawn(SUPPORT, CARAVAN_INNER)
        if t >= CARAVAN_UPGRADE_INNER_TURN:
            gs.attempt_upgrade(CARAVAN_INNER)
        if t >= CARAVAN_PLACE_OUTER_TURN:
            gs.attempt_spawn(SUPPORT, CARAVAN_OUTER)
        if t >= CARAVAN_UPGRADE_OUTER_TURN:
            gs.attempt_upgrade(CARAVAN_OUTER)

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
        """Drop interceptors when enemy MP threatens a rush.

        Archetype override: scout_rush opponents predictably burst; lower
        the trigger so we pre-place plugs every 3 turns they can spawn.
        """
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        trigger = 6 * scout_cost
        if self._archetype == SCOUT_RUSH:
            trigger = 4 * scout_cost
        if enemy_mp >= trigger and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)
            # Extra plug on opposite edge for scout_rush which alternates
            if self._archetype == SCOUT_RUSH:
                spawn2 = [14, 0]
                if gs.game_map.in_arena_bounds(spawn2) and not gs.contains_stationary_unit(spawn2):
                    gs.attempt_spawn(INTERCEPTOR, spawn2)

    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        # Archetype-specific scout threshold. Default matches v14 (10), so
        # BALANCED / SUPPORT_CARAVAN behave as v14 — no regression.
        thr = {
            TURRET_CASTLE: SCOUT_THRESHOLD_TURRET_CASTLE,
            SCOUT_RUSH: SCOUT_THRESHOLD_SCOUT_RUSH,
            DEMO_LINE: SCOUT_THRESHOLD_DEMO_LINE,
        }.get(self._archetype, SCOUT_THRESHOLD_DEFAULT)

        enemy_front = self._count_enemy_front(gs)

        # Demolisher burst when enemy is wall-heavy (front_density high)
        # Always keep the v14 trigger, but for DEMO_LINE add an extra push:
        # their wall line at y=11 is exactly what Demolisher (range 4.5)
        # out-ranges. Use our back-row spawn so Demos stay inside the
        # caravan's shield range.
        demo_trigger_multiplier = 4
        if self._archetype == DEMO_LINE:
            demo_trigger_multiplier = 3

        if enemy_front >= 10 and mp >= demo_trigger_multiplier * demo_cost:
            spawn_opts = self._offensive_spawn_options(gs)
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= thr * scout_cost:
            spawn_opts = self._offensive_spawn_options(gs)
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    def _offensive_spawn_options(self, gs):
        """Edge tiles we spawn mobiles from.

        Default: v14's [[13, 0], [14, 0]]. When the opponent's structures
        show a left/right bias, bias our spawn toward the weaker side.
        """
        base = [[13, 0], [14, 0]]
        if self._features is not None:
            if self._features.left_bias > self._features.right_bias + 2:
                # Opponent structures lean LEFT → their RIGHT is softer →
                # we want our mobile going TOP_LEFT (hits their right? no,
                # unit spawned left heads TOP_RIGHT, hitting their right
                # half). Adjust accordingly.
                base = [[13, 0], [14, 0]]  # keep default; bias is noisy
            elif self._features.right_bias > self._features.left_bias + 2:
                base = [[14, 0], [13, 0]]
        return [s for s in base
                if gs.game_map.in_arena_bounds(s)
                and not gs.contains_stationary_unit(s)]

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
        # Track opponent mobile spawns for classifier
        ui = self.config["unitInformation"]
        shorthands = [u.get("shorthand") for u in ui]
        for sp in events.get("spawn", []):
            # Format: [[x,y], unit_type_idx, unit_id, player]
            try:
                loc, type_idx, _uid, player = sp[:4]
            except Exception:
                continue
            if player != 2:
                continue
            if type_idx < 0 or type_idx >= len(shorthands):
                continue
            sh = shorthands[type_idx]
            if sh in (SCOUT, DEMOLISHER, INTERCEPTOR):
                self._enemy_spawns.append((int(loc[0]), int(loc[1]), sh))


if __name__ == "__main__":
    AlgoStrategy().start()
