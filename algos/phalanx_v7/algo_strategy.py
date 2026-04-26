# lostkids_v3 — targets specific failure modes observed in 6 LostKidsv2
# ranked games (replays/LostKidsv2_vs_*.replay):
#
#   - vs 1620 (LOSS): opp drove constant scout pressure; v2 leaked at deep
#     edge tiles (10,3), (11,2), (12,1), (8,5).
#   - vs 1744 (LOSS): late-game demolisher rush (8-10 demos/turn from T42).
#     Anti-demo hardening was disabled by default → never fired. v2 also
#     scored 0 breaches the entire game.
#   - vs 1782 (LOSS, -22 -- 40): opp breached at (22,8) ×53. v2 scored 0
#     breaches across 48 turns despite repeated 17-23 scout waves.
#
# Changes vs lostkids_v2:
#   1. Side-gap turrets at (5,9), (22,9) added in defense-order Phase 2 + upgraded
#      in Phase 6. Plugs the (8,5) and (22,8) breach hotspots that the canonical
#      Lostkids build leaves uncovered (only walls behind, no turret in range 2.5).
#   2. Scout-success tracking: if last 2 scout waves scored < 5 breaches each,
#      the next attack switches to demolisher mode regardless of edge_strength.
#      Catches opponents whose defense is strong-but-not-50+-strength (the
#      old trigger was too coarse — scouts failed against funnel walls v2 saw
#      as "30 strength").
#   3. Demolisher trigger: edge_strength >= 35 (was 50) AND MP >= 18.
#   4. Anti-demo hardening: ENABLED by default (gated by 4+ enemy demos seen
#      AND turn >= 8 AND SP cushion >= 8). Lossy in mirror but the demo-rush
#      loss at game 1744 shows we need it on for ladder play.
#   5. Reactive defense remains opt-in (mirror cost > ladder benefit).
#
# Build order changes (defense-order.json):
#   - Phase 2 spawns (5,9), (22,9) base turrets (8 SP cost vs 4 in v2).
#   - Phase 6 upgrades (5,9), (22,9) (+8 SP cost vs v2).
#   - Total extra SP cost: ~16 SP, paid in installments — defers Phase 5/7
#     by ~3 SP-income-turns.
#
# All numerics still runtime-config-driven.
import gamelib
import random
import math
import warnings
from sys import maxsize
import json
import os
import sys
import time
import traceback
import signal
import threading


# --- Production safety: turn-time watchdog --------------------------------
# 13s cap leaves 2s headroom under the 15s engine cutoff.
TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    if hasattr(signal, "SIGALRM"):
        try:
            old_handler = signal.signal(signal.SIGALRM, _sigalrm_handler)
            signal.alarm(seconds)

            def disarm_sigalrm():
                signal.alarm(0)
                signal.signal(signal.SIGALRM, old_handler)

            return disarm_sigalrm, lambda: False
        except (ValueError, OSError):
            pass
    fired = {"v": False}

    def fire():
        fired["v"] = True

    timer = threading.Timer(seconds, fire)
    timer.daemon = True
    timer.start()
    return timer.cancel, lambda: fired["v"]


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write('lostkids_v3 random seed: {}'.format(seed))

    def on_game_start(self, config):
        gamelib.debug_write('Configuring lostkids_v3...')
        self.config = config
        global WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR, MP, SP
        global REFUND_THRESHOLD_WALL, REFUND_THRESHOLD_TURRET
        global EDGE_BLOCK_LOCATIONS_LEFT, EDGE_BLOCK_LOCATIONS_RIGHT
        global BLOCK_EDGE_ENEMY_MP_THRESHOLD, UPGRADE_EDGE_WALL_THRESHOLD
        global ENEMY_EDGE_DEFENSE_LOCATIONS_LEFT, ENEMY_EDGE_DEFENSE_LOCATIONS_RIGHT
        global DEFENSE_INTERCEPTOR_LOCATION_LEFT, DEFENSE_INTERCEPTOR_LOCATION_RIGHT
        global DEMOLISHER_TRIGGER_STRENGTH, ATTACK_MP_THRESHOLD
        global DEMOLISHER_MIN_MP, DEMOLISHER_PRESSURE_TRIGGER
        global SCOUT_FAIL_BREACH_THRESHOLD, SCOUT_FAIL_LOOKBACK
        global FORCE_DEMO_EVERY_N_TURNS

        WALL = config["unitInformation"][0]["shorthand"]
        SUPPORT = config["unitInformation"][1]["shorthand"]
        TURRET = config["unitInformation"][2]["shorthand"]
        SCOUT = config["unitInformation"][3]["shorthand"]
        DEMOLISHER = config["unitInformation"][4]["shorthand"]
        INTERCEPTOR = config["unitInformation"][5]["shorthand"]
        self.mobile_unit_indices = [3, 4, 5]
        self.costs = {
            3: config["unitInformation"][3]["cost2"],
            4: config["unitInformation"][4]["cost2"],
            5: config["unitInformation"][5]["cost2"],
        }
        MP = 1
        SP = 0

        REFUND_THRESHOLD_WALL = 0.5
        REFUND_THRESHOLD_TURRET = 0.3
        with open(os.path.join(os.path.dirname(__file__), 'defense-order.json'), 'r') as f:
            self.build_order = json.loads(f.read())

        EDGE_BLOCK_LOCATIONS_LEFT = [[0, 13], [1, 13]]
        EDGE_BLOCK_LOCATIONS_RIGHT = [[27, 13], [26, 13]]

        DEFENSE_INTERCEPTOR_LOCATION_LEFT = [4, 9]
        DEFENSE_INTERCEPTOR_LOCATION_RIGHT = [23, 9]

        ENEMY_EDGE_DEFENSE_LOCATIONS_LEFT = [[0, 14], [1, 14], [2, 14], [3, 14], [1, 15], [2, 15], [3, 15], [2, 16]]
        ENEMY_EDGE_DEFENSE_LOCATIONS_RIGHT = [[27, 14], [26, 14], [25, 14], [24, 14], [26, 15], [25, 15], [24, 15], [25, 16]]

        BLOCK_EDGE_ENEMY_MP_THRESHOLD = 12
        UPGRADE_EDGE_WALL_THRESHOLD = 15

        # v3 thresholds.
        # Demolisher trigger lowered from 50 → 35 based on game 1782 / 1744
        # losses where v2 scored 0 breaches against opps whose edge_strength
        # measured below 50 but were still impenetrable to scouts. Above 35
        # is also where v13's mid-build sits (~45) — but the scout-success
        # backstop below means we don't fire demos vs v13 unless scouts have
        # already failed twice in a row, which doesn't happen vs v13.
        # phalanx_v7 deltas (refined after over-aggressive v7-pre regressed):
        # Trigger 10 → 8: a small lower bound to fire demos against any
        # meaningful corner defense without burning MP on no-defense.
        DEMOLISHER_TRIGGER_STRENGTH = 8
        ATTACK_MP_THRESHOLD = 12
        DEMOLISHER_MIN_MP = 9
        SCOUT_FAIL_BREACH_THRESHOLD = 4
        SCOUT_FAIL_LOOKBACK = 1
        FORCE_DEMO_EVERY_N_TURNS = 4
        DEMOLISHER_PRESSURE_TRIGGER = 3

        # Per-game state
        self.my_left_edge_blocked = True
        self.my_right_edge_blocked = True
        self.enemy_left_edge_blocked = True
        self.enemy_right_edge_blocked = True
        self.enemy_left_edge_strength = 100
        self.enemy_right_edge_strength = 100
        self.my_MP = 0
        self.enemy_MP = 0
        self.turn_strategy = "defend"  # defend, attack_left, attack_right, attack_demo_left, attack_demo_right
        self.min_sp_to_save = 0

        # v3: anti-demo hardening enabled by default. The LK family never
        # sends demolishers, so enemy_recent_demos stays at 0 in mirror →
        # trigger condition never met → no mirror regression.
        # Reactive defense kept opt-in: in mirror, late-game breach
        # concentration triggers it and the SP it spends on reactive
        # turrets costs us economy. Useful only against repeat-tile
        # ladder attackers — toggle on for those deployments.
        self.ENABLE_REACTIVE = False
        self.ENABLE_ANTI_DEMO = True

        # Enemy deployment insights
        self.batch_count_history = [0, 0, 0]
        self.scored_on_locations = []
        self.enemy_recent_demos = 0
        self.enemy_recent_scouts = 0
        self.enemy_mp_spent_history = []
        self.last_attack_side = None
        self.consecutive_same_side_attacks = 0
        # v7: track turn of last demo wave for forced-cadence offense.
        self.last_demo_turn = -10

        # NEW v3: scout-success tracking.
        # `scout_attack_breaches` is a list of breach counts from each
        # scout-only attack this game. We force demolisher mode after the
        # latest SCOUT_FAIL_LOOKBACK entries are all under threshold.
        self.scout_attack_breaches = []
        # Per-turn aggregates filled by on_action_frame; consumed at the
        # next attack decision.
        self._pending_attack_was_scouts = False
        self._this_turn_lk_breaches = 0

    def on_turn(self, turn_state):
        start = time.time()
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        game_state = None
        used_fallback = False
        try:
            game_state = gamelib.GameState(self.config, turn_state)
            self.starter_strategy(game_state)
            game_state.submit_turn()
        except _TurnTimeout:
            gamelib.debug_write(
                "[lostkids_v3] watchdog fired after {}s".format(TURN_WATCHDOG_SECONDS)
            )
            used_fallback = True
        except Exception as exc:
            gamelib.debug_write(
                "[lostkids_v3] on_turn exception: {!r}\n{}".format(
                    exc, traceback.format_exc()
                )
            )
            used_fallback = True
        finally:
            disarm()

        if used_fallback:
            try:
                if game_state is None:
                    game_state = gamelib.GameState(self.config, turn_state)
                self._safe_fallback_turn(game_state)
                game_state.submit_turn()
            except Exception as exc2:
                gamelib.debug_write(
                    "[lostkids_v3] safe-fallback also failed: {!r}\n{}".format(
                        exc2, traceback.format_exc()
                    )
                )
                try:
                    if game_state is not None:
                        game_state.submit_turn()
                except Exception:
                    pass

    _SAFE_FALLBACK_TURRETS = ((2, 13), (25, 13), (3, 13), (24, 13))

    def _safe_fallback_turn(self, game_state):
        try:
            sp = game_state.get_resource(SP, 0)
        except Exception:
            sp = 0.0
        for loc in self._SAFE_FALLBACK_TURRETS:
            try:
                cost = game_state.type_cost(TURRET)[SP]
            except Exception:
                cost = 2
            if sp < cost:
                break
            try:
                spawned = game_state.attempt_spawn(TURRET, list(loc))
            except Exception:
                spawned = 0
            if spawned:
                sp -= cost

    def starter_strategy(self, game_state):
        # Finalize the breach-count for the PREVIOUS turn's attack now that
        # its action phase has completed. on_action_frame populated
        # `_this_turn_lk_breaches` during the action; we record it here at
        # the start of the next turn's on_turn (before any new state changes).
        if self._pending_attack_was_scouts:
            self.scout_attack_breaches.append(self._this_turn_lk_breaches)
            self._pending_attack_was_scouts = False
        # Reset for the action phase that will play after THIS turn's submit.
        self._this_turn_lk_breaches = 0

        self.parse_game_state(game_state)
        self.build_defences(game_state)
        self.execute_turn_strategy(game_state)
        self.evaluate_next_turn_strategy(game_state)

    def parse_game_state(self, game_state):
        self.my_MP = game_state.get_resource(MP, 0)
        self.enemy_MP = game_state.get_resource(MP, 1)
        # Track HP for the demo-trigger condition: only switch to demos when
        # we're actually losing the HP race (otherwise scouts are working).
        try:
            self.my_HP = game_state.my_health
            self.enemy_HP = game_state.enemy_health
        except AttributeError:
            self.my_HP = 40
            self.enemy_HP = 40
        self.enemy_left_edge_blocked = self.is_enemy_left_edge_blocked(game_state)
        self.enemy_right_edge_blocked = self.is_enemy_right_edge_blocked(game_state)
        self.enemy_left_edge_strength = self.compute_enemy_left_edge_defense_strength(game_state)
        self.enemy_right_edge_strength = self.compute_enemy_right_edge_defense_strength(game_state)
        self.my_left_edge_blocked = self.is_my_left_edge_blocked(game_state)
        self.my_right_edge_blocked = self.is_my_right_edge_blocked(game_state)

    def is_enemy_left_edge_blocked(self, game_state):
        path = game_state.find_path_to_edge([1, 12], game_state.game_map.TOP_RIGHT)
        if not path or len(path) < 10:
            return True
        for i in range(min(10, len(path))):
            location = path[i]
            if location[1] < 12:
                return True
        return False

    def is_enemy_right_edge_blocked(self, game_state):
        path = game_state.find_path_to_edge([26, 12], game_state.game_map.TOP_LEFT)
        if not path or len(path) < 10:
            return True
        for i in range(min(10, len(path))):
            location = path[i]
            if location[1] < 12:
                return True
        return False

    def is_enemy_left_edge_misdirecting(self, game_state):
        return not self.enemy_left_edge_blocked and game_state.contains_stationary_unit([0, 14])

    def is_enemy_right_edge_misdirecting(self, game_state):
        return not self.enemy_right_edge_blocked and game_state.contains_stationary_unit([27, 14])

    def is_my_left_edge_blocked(self, game_state):
        for location in EDGE_BLOCK_LOCATIONS_LEFT:
            if not game_state.contains_stationary_unit(location):
                return False
        return True

    def is_my_right_edge_blocked(self, game_state):
        for location in EDGE_BLOCK_LOCATIONS_RIGHT:
            if not game_state.contains_stationary_unit(location):
                return False
        return True

    def compute_enemy_left_edge_defense_strength(self, game_state):
        # Same weights as original Lostkids — proven calibrated against the
        # ladder. Citadel's stronger upgraded turret is reflected via the
        # demolisher trigger threshold, not by inflating the score weight.
        strength = 0
        for location in ENEMY_EDGE_DEFENSE_LOCATIONS_LEFT:
            distance_to_edge = math.dist([0.5, 13], location)
            unit_strength = 0
            unit = game_state.contains_stationary_unit(location)
            if unit and unit.unit_type == TURRET:
                if unit.upgraded:
                    unit_strength = 25 / max(distance_to_edge, 0.1)
                else:
                    unit_strength = 5 / max(distance_to_edge, 0.1)
            elif location[1] == 14 and unit and unit.unit_type == WALL:
                if unit.upgraded:
                    unit_strength = 3
                else:
                    unit_strength = 1
            strength += unit_strength
        return strength

    def compute_enemy_right_edge_defense_strength(self, game_state):
        strength = 0
        for location in ENEMY_EDGE_DEFENSE_LOCATIONS_RIGHT:
            distance_to_edge = math.dist([26.5, 13], location)
            unit_strength = 0
            unit = game_state.contains_stationary_unit(location)
            if unit and unit.unit_type == TURRET:
                if unit.upgraded:
                    unit_strength = 25 / max(distance_to_edge, 0.1)
                else:
                    unit_strength = 5 / max(distance_to_edge, 0.1)
            elif location[1] == 14 and unit and unit.unit_type == WALL:
                if unit.upgraded:
                    unit_strength = 3
                else:
                    unit_strength = 1
            strength += unit_strength
        return strength

    def build_defences(self, game_state):
        self.block_edge(game_state)
        self.refund_low_health_structures(game_state)
        self.build_default_defences(game_state)
        # Reactive / anti-demo helpers are DEFINED but disabled by default
        # — every additional structure costs SP that the canonical build
        # order needs, and in mirror conditions this consistently regresses
        # win rate. Toggle ENABLE_REACTIVE / ENABLE_ANTI_DEMO at the top
        # of on_game_start to opt back in for ladder play if a particular
        # opponent profile justifies it.
        if getattr(self, "ENABLE_REACTIVE", False):
            self._reactive_defense(game_state)
        if getattr(self, "ENABLE_ANTI_DEMO", False):
            self._anti_demolisher_hardening(game_state)

    def _reactive_defense(self, game_state):
        """Place a backing turret one row inward of a chronically breached
        tile. Heavily gated to avoid SP starvation:
          - Only after turn 35 (canonical build mostly settled by then)
          - Only if a single tile has been breached >= 5 times
          - Only if SP cushion > 12 (next build phase already affordable)
        At most 1 reactive turret per turn."""
        if game_state.turn_number < 35:
            return
        if not self.scored_on_locations:
            return
        cost = game_state.type_cost(TURRET)[SP]
        if game_state.get_resource(SP) < cost + 12:
            return
        from collections import Counter
        counts = Counter(self.scored_on_locations)
        REACTIVE_THRESHOLD = 5
        for (x, y), n in counts.most_common(3):
            if n < REACTIVE_THRESHOLD:
                return
            if x < 14:
                candidates = [[x + 1, y - 1], [x, y - 1], [x + 2, y - 1]]
            else:
                candidates = [[x - 1, y - 1], [x, y - 1], [x - 2, y - 1]]
            for build in candidates:
                bx, by = build
                if by < 0 or by > 13:
                    continue
                if not game_state.game_map.in_arena_bounds(build):
                    continue
                if game_state.contains_stationary_unit(build):
                    continue
                if game_state.attempt_spawn(TURRET, build) > 0:
                    return

    def _anti_demolisher_hardening(self, game_state):
        """Place a single forward turret if persistent demolisher pressure
        is observed AND there's plenty of leftover SP. Conservative: at
        most 1 turret per turn, only if SP > 8 (cushion for next phase)."""
        if game_state.turn_number < 8:
            return
        if self.enemy_recent_demos < 4:
            return
        cost = game_state.type_cost(TURRET)[SP]
        if game_state.get_resource(SP) < cost + 8:
            return
        # One forward turret on whichever side has lower edge strength
        # (means: opponent is attacking us harder there).
        candidates = [[6, 12], [21, 12], [10, 12], [17, 12]]
        for loc in candidates:
            if game_state.contains_stationary_unit(loc):
                continue
            if game_state.attempt_spawn(TURRET, loc) > 0:
                return

    def block_edge(self, game_state):
        if game_state.turn_number == 0:
            self.block_left_edge(game_state)
            self.block_right_edge(game_state)

        if self.turn_strategy == "defend" and self.enemy_MP >= BLOCK_EDGE_ENEMY_MP_THRESHOLD and not self.my_left_edge_blocked \
                and not self.enemy_left_edge_blocked and not self.my_right_edge_blocked and not self.enemy_right_edge_blocked:
            if random.randint(0, 1) == 0:
                self.block_left_edge(game_state)
            else:
                self.block_right_edge(game_state)
            return

        if not self.enemy_left_edge_blocked and not self.my_left_edge_blocked and not self._is_attack_left():
            if self.enemy_MP < BLOCK_EDGE_ENEMY_MP_THRESHOLD or self._is_attack_right():
                self.block_left_edge(game_state)

        if not self.enemy_right_edge_blocked and not self.my_right_edge_blocked and not self._is_attack_right():
            if self.enemy_MP < BLOCK_EDGE_ENEMY_MP_THRESHOLD or self._is_attack_left():
                self.block_right_edge(game_state)

    def _is_attack_left(self):
        return self.turn_strategy in ("attack_left", "attack_demo_left")

    def _is_attack_right(self):
        return self.turn_strategy in ("attack_right", "attack_demo_right")

    def block_left_edge(self, game_state):
        for location in EDGE_BLOCK_LOCATIONS_LEFT:
            game_state.attempt_spawn(WALL, location)
            if self.enemy_MP > UPGRADE_EDGE_WALL_THRESHOLD:
                game_state.attempt_upgrade(location)
            game_state.attempt_remove(location)
        self.my_left_edge_blocked = True

    def block_right_edge(self, game_state):
        for location in EDGE_BLOCK_LOCATIONS_RIGHT:
            game_state.attempt_spawn(WALL, location)
            if self.enemy_MP > UPGRADE_EDGE_WALL_THRESHOLD:
                game_state.attempt_upgrade(location)
            game_state.attempt_remove(location)
        self.my_right_edge_blocked = True

    def enumerate_friendly_side_locations(self, game_state):
        locations = []
        for x in range(game_state.ARENA_SIZE):
            if x < game_state.HALF_ARENA:
                for y in range(game_state.HALF_ARENA - x - 1, game_state.HALF_ARENA):
                    locations.append([x, y])
            else:
                for y in range(x - 14, game_state.HALF_ARENA):
                    locations.append([x, y])
        return locations

    def refund_low_health_structures(self, game_state):
        for location in self.enumerate_friendly_side_locations(game_state):
            structure = game_state.contains_stationary_unit(location)
            if structure:
                if structure.unit_type == TURRET:
                    if structure.health / structure.max_health < REFUND_THRESHOLD_TURRET:
                        game_state.attempt_remove(location)
                elif structure.unit_type == WALL:
                    if structure.health / structure.max_health < REFUND_THRESHOLD_WALL:
                        game_state.attempt_remove(location)

    def build_default_defences(self, game_state):
        stop_flag = False
        for job_list in self.build_order:
            if stop_flag:
                break
            for build_job in job_list:
                if build_job["type"] == "spawn":
                    unit = eval(build_job["unit"])
                    location = build_job["location"]
                    if game_state.get_resource(SP) - game_state.type_cost(unit)[0] < self.min_sp_to_save:
                        stop_flag = True
                        break
                    game_state.attempt_spawn(unit, location)
                elif build_job["type"] == "upgrade":
                    unit = eval(build_job["unit"])
                    location = build_job["location"]
                    if game_state.get_resource(SP) - game_state.type_cost(unit, upgrade=True)[0] < self.min_sp_to_save:
                        stop_flag = True
                        break
                    game_state.attempt_upgrade(location)

    # --- Path-aware spawn helpers --------------------------------------
    def _spawn_candidates_for_attack(self, attack_side, game_state):
        """Return list of candidate spawn tiles for the given attack side.
        attack_side: 'left' (=spawn on BOTTOM_RIGHT, target TOP_LEFT) or
                     'right' (=spawn on BOTTOM_LEFT, target TOP_RIGHT).
        Tiles must be on the player's edge AND not blocked."""
        if attack_side == "right":
            # BOTTOM_LEFT edge: y = 13 - x for x in [0..13]. Spawn deep enough
            # that a couple of structures can sit ahead.
            base = [[2, 11], [3, 10], [4, 9], [5, 8], [6, 7]]
        else:
            # BOTTOM_RIGHT edge: y = x - 14 for x in [14..27].
            base = [[25, 11], [24, 10], [23, 9], [22, 8], [21, 7]]
        valid = []
        for tile in base:
            if not game_state.game_map.in_arena_bounds(tile):
                continue
            if game_state.contains_stationary_unit(tile):
                continue
            valid.append(tile)
        return valid

    def _path_damage_estimate(self, game_state, spawn_tile, attack_side):
        """Estimate total turret damage taken on the path from spawn_tile to
        the opposite edge. Each attacker contributes upgraded-turret-DPS-equivalent
        damage per frame the unit is in range. Returns inf if no path."""
        target = (game_state.game_map.TOP_RIGHT if attack_side == "right"
                  else game_state.game_map.TOP_LEFT)
        try:
            path = game_state.find_path_to_edge(spawn_tile, target)
        except Exception:
            return float("inf")
        if not path:
            return float("inf")
        # Use upgraded turret damage as a worst-case proxy.
        try:
            tdmg = float(self.config["unitInformation"][2]["upgrade"].get(
                "attackDamageWalker",
                self.config["unitInformation"][2].get("attackDamageWalker", 6),
            ))
        except Exception:
            tdmg = 20.0
        total = 0.0
        for p in path:
            try:
                attackers = game_state.get_attackers(p, 0)
            except Exception:
                attackers = []
            total += len(attackers) * tdmg
        return total

    def _best_spawn(self, game_state, attack_side):
        """Pick the spawn tile with the LEAST estimated damage on its path.
        Returns None if no candidate is viable."""
        candidates = self._spawn_candidates_for_attack(attack_side, game_state)
        if not candidates:
            return None
        scored = []
        for c in candidates:
            d = self._path_damage_estimate(game_state, c, attack_side)
            scored.append((d, c))
        scored.sort(key=lambda kv: kv[0])
        return scored[0][1] if scored else None

    def execute_turn_strategy(self, game_state):
        if self.turn_strategy == "defend":
            self._execute_defense(game_state)
        elif self.turn_strategy in ("attack_left", "attack_right"):
            self._execute_scout_attack(game_state)
        elif self.turn_strategy in ("attack_demo_left", "attack_demo_right"):
            self._execute_demo_attack(game_state)

    def _execute_defense(self, game_state):
        if not self.enemy_left_edge_blocked and not self.my_left_edge_blocked:
            if self.should_use_wall_on_edge():
                self.block_left_edge(game_state)
            else:
                self.spawn_interceptor(
                    game_state,
                    DEFENSE_INTERCEPTOR_LOCATION_LEFT,
                    self.choose_number_of_interceptor_based_on_enemy_MP(),
                )
        if not self.enemy_right_edge_blocked and not self.my_right_edge_blocked:
            if self.should_use_wall_on_edge():
                self.block_right_edge(game_state)
            else:
                self.spawn_interceptor(
                    game_state,
                    DEFENSE_INTERCEPTOR_LOCATION_RIGHT,
                    self.choose_number_of_interceptor_based_on_enemy_MP(),
                )

    def _execute_scout_attack(self, game_state):
        attack_side = "left" if self.turn_strategy == "attack_left" else "right"
        # Mark this turn so evaluate_next_turn_strategy will record the
        # breach result into scout_attack_breaches for the demo backstop.
        self._pending_attack_was_scouts = True
        # Misdirection clear (legacy LK pattern).
        if attack_side == "left" and self.is_enemy_left_edge_misdirecting(game_state) and not self.enemy_left_edge_blocked:
            self.ping_one_batch(game_state, [23, 9])
            return
        if attack_side == "right" and self.is_enemy_right_edge_misdirecting(game_state) and not self.enemy_right_edge_blocked:
            self.ping_one_batch(game_state, [4, 9])
            return

        # v4a: path-aware spawn for scouts too (over 5 candidates).
        edge_strength = (self.enemy_left_edge_strength if attack_side == "left"
                         else self.enemy_right_edge_strength)
        first_group_size = self.choose_number_of_scouts_in_first_group_based_on_enemy_edge_strength(edge_strength)
        if attack_side == "right":
            primary_cands = [[4, 9], [3, 10], [5, 8], [13, 0], [14, 0]]
            secondary_cands = [[3, 10], [4, 9], [13, 0], [14, 0]]
            target = game_state.game_map.TOP_RIGHT
        else:
            primary_cands = [[23, 9], [24, 10], [22, 8], [14, 0], [13, 0]]
            secondary_cands = [[24, 10], [23, 9], [14, 0], [13, 0]]
            target = game_state.game_map.TOP_LEFT
        first_loc = self._best_spawn_v3b(game_state, primary_cands, target)
        second_loc = self._best_spawn_v3b(game_state, secondary_cands, target)
        if first_loc is None:
            first_loc = [4, 9] if attack_side == "right" else [23, 9]
        if second_loc is None or second_loc == first_loc:
            second_loc = [3, 10] if attack_side == "right" else [24, 10]
        my_mp = game_state.get_resource(MP, 0)
        scouts_total = int(my_mp // game_state.type_cost(SCOUT)[MP])
        first = min(int(first_group_size), scouts_total)
        rest = scouts_total - first
        if first > 0:
            game_state.attempt_spawn(SCOUT, first_loc, first)
        if rest > 0:
            game_state.attempt_spawn(SCOUT, second_loc, rest)

    def _execute_demo_attack(self, game_state):
        """v3b: path-aware demolisher spawn over 5 candidates."""
        attack_side = "left" if self.turn_strategy == "attack_demo_left" else "right"
        if attack_side == "right":
            cands = [[4, 9], [5, 8], [6, 7], [3, 10], [13, 0]]
            target = game_state.game_map.TOP_RIGHT
        else:
            cands = [[23, 9], [22, 8], [21, 7], [24, 10], [14, 0]]
            target = game_state.game_map.TOP_LEFT
        spawn = self._best_spawn_v3b(game_state, cands, target)
        if spawn is None:
            spawn = [4, 9] if attack_side == "right" else [23, 9]
        my_mp = game_state.get_resource(MP, 0)
        demo_cost = game_state.type_cost(DEMOLISHER)[MP]
        scout_cost = game_state.type_cost(SCOUT)[MP]
        demo_count = int(my_mp // demo_cost)
        # Cap to prevent stupid huge waves (10+ demos overkill).
        demo_count = min(demo_count, 8)
        demo_mp = demo_count * demo_cost
        if demo_count > 0:
            game_state.attempt_spawn(DEMOLISHER, spawn, demo_count)
        # Spend leftover MP on a small scout follow-up at the offset tile.
        leftover = my_mp - demo_mp
        scout_count = int(leftover // scout_cost)
        if scout_count > 0:
            if attack_side == "right":
                scout_spawn = [3, 10]
            else:
                scout_spawn = [game_state.ARENA_SIZE - 3 - 1, 10]
            game_state.attempt_spawn(SCOUT, scout_spawn, scout_count)

    def _best_spawn_v3b(self, gs, cands, target_edge):
        """Pick spawn tile minimizing total path turret-damage."""
        best = None
        best_dmg = None
        try:
            t_dmg_up = float(self.config["unitInformation"][2].get(
                "upgrade", {}).get("attackDamageWalker", 20))
            t_dmg_base = float(self.config["unitInformation"][2].get(
                "attackDamageWalker", 6))
        except Exception:
            t_dmg_up = 20.0
            t_dmg_base = 6.0
        for c in cands:
            if not gs.game_map.in_arena_bounds(c):
                continue
            if gs.contains_stationary_unit(c):
                continue
            try:
                path = gs.find_path_to_edge(c, target_edge)
            except Exception:
                continue
            if not path:
                continue
            total = 0.0
            for p in path:
                try:
                    atks = gs.get_attackers(p, 0)
                except Exception:
                    atks = []
                for a in atks:
                    total += t_dmg_up if a.upgraded else t_dmg_base
            if best is None or total < best_dmg:
                best = c
                best_dmg = total
        return best

    def ping_one_batch(self, game_state, location):
        game_state.attempt_spawn(SCOUT, location, 1000)

    def should_use_wall_on_edge(self):
        return self.batch_count_history[1] == 0 and self.batch_count_history[2] == 0

    def spawn_interceptor(self, game_state, location, number):
        game_state.attempt_spawn(INTERCEPTOR, location, math.floor(number))

    def spawn_demolisher(self, game_state, location, number):
        game_state.attempt_spawn(DEMOLISHER, location, math.floor(number))

    def choose_number_of_interceptor_based_on_enemy_MP(self):
        return max(self.enemy_MP / 4, 3)

    def choose_number_of_scouts_in_first_group_based_on_enemy_edge_strength(self, strength):
        return min(5 + math.floor(strength / 7), 10)

    def evaluate_next_turn_strategy(self, game_state):
        self.my_MP = game_state.get_resource(MP, 0)
        if self.my_MP < ATTACK_MP_THRESHOLD:
            self.turn_strategy = "defend"
            return
        # Pick weaker side for attack (same logic as baseline).
        if self.compute_enemy_left_edge_defense_strength(game_state) > self.compute_enemy_right_edge_defense_strength(game_state):
            attack_side = "right"
            for location in EDGE_BLOCK_LOCATIONS_RIGHT:
                game_state.attempt_remove(location)
        else:
            attack_side = "left"
            for location in EDGE_BLOCK_LOCATIONS_LEFT:
                game_state.attempt_remove(location)

        # Wave-type decision: route to demolishers ONLY when scouts have
        # demonstrably failed AND we're actually losing on HP. The HP gate
        # is critical for mirror parity — in symmetric games both sides
        # have low early-breach rates, but switching to demos burns
        # economy without a real reason. Only commit to demos when behind.
        scouts_failing = (
            len(self.scout_attack_breaches) >= SCOUT_FAIL_LOOKBACK
            and all(b < SCOUT_FAIL_BREACH_THRESHOLD
                    for b in self.scout_attack_breaches[-SCOUT_FAIL_LOOKBACK:])
        )
        # We're "behind" if we've taken >= 5 HP damage AND we've taken more
        # damage than we've dealt. (Equivalent in starting state would be
        # opp HP > my HP, but be tolerant of small ties.)
        # phalanx_v2: demolisher specialist. Fire demos whenever:
        #   (a) the picked side has *any* edge defense (strength >= 10), OR
        #   (b) scouts have failed before AND we have demo MP.
        # Otherwise scout flood.
        side_strength = (self.enemy_left_edge_strength if attack_side == "left"
                         else self.enemy_right_edge_strength)
        if (side_strength >= DEMOLISHER_TRIGGER_STRENGTH
                and self.my_MP >= DEMOLISHER_MIN_MP):
            self.turn_strategy = f"attack_demo_{attack_side}"
        elif scouts_failing and self.my_MP >= DEMOLISHER_MIN_MP:
            self.turn_strategy = f"attack_demo_{attack_side}"
        else:
            self.turn_strategy = f"attack_{attack_side}"

        # Side-toggle tracker (kept for potential future use).
        if self.last_attack_side == attack_side:
            self.consecutive_same_side_attacks += 1
        else:
            self.consecutive_same_side_attacks = 1
            self.last_attack_side = attack_side

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
            # Only process the first frame of action phase.
            if state["turnInfo"][0] == 1 and state["turnInfo"][2] == 0:
                spawns = state["events"]["spawn"]
                # IMPORTANT: match Lostkids' original semantics — batch_count_history
                # counts unique scout spawn LOCATIONS in the first action frame,
                # ACROSS BOTH PLAYERS. Adding an owner filter here breaks
                # `should_use_wall_on_edge` and asymmetrically vs baseline.
                locations = set()
                e_demos = 0
                e_scouts = 0
                for spawn in spawns:
                    if len(spawn) < 2:
                        continue
                    if spawn[1] == 3:
                        locations.add(tuple(spawn[0]))
                    # Track enemy unit deployments separately (uses owner field).
                    if len(spawn) >= 4 and spawn[3] == 2:
                        if spawn[1] == 4:
                            e_demos += 1
                        elif spawn[1] == 3:
                            e_scouts += 1
                batch_count = min(3, len(locations))
                if batch_count >= 1:
                    self.batch_count_history[batch_count - 1] += 1
                self.enemy_recent_demos = int(self.enemy_recent_demos * 0.7) + e_demos
                self.enemy_recent_scouts = int(self.enemy_recent_scouts * 0.7) + e_scouts
            for ev in state.get("events", {}).get("breach", []):
                if len(ev) < 5:
                    continue
                loc, _, _, _, owner = ev
                if owner == 2:
                    self.scored_on_locations.append(tuple(loc))
                elif owner == 1:
                    # Tally OUR breaches this turn for the scout-success
                    # backstop in evaluate_next_turn_strategy.
                    self._this_turn_lk_breaches += 1
        except Exception as exc:
            gamelib.debug_write(
                "[lostkids_v3] on_action_frame exception: {!r}".format(exc)
            )


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
