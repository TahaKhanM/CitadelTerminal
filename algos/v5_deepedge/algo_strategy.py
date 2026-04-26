# v5_deepedge — fork of v4_smartdemo that ADDS a Phase 8 deep-edge turret
# pair at (11,4) and (16,4) (both upgraded). Targets the breach hotspots
# (2,11), (6,7), (8,5), (16,2), (15,1) observed in v4_smartdemo's 4
# ranked losses. Range 3.5 from (11,4) covers (10,3)-(13,2); range 3.5
# from (16,4) covers (15,1)-(18,6). Cost: 12 SP, built only after
# canonical Phase 7 finishes (~turn 30+).
#
# v4_smartdemo — fork of lostkids_v3 with a fixed-cadence demolisher rule
# gated by opponent-type detection.
#
# v4_demoaggro (parent experiment) showed that firing demolishers every
# 3rd attack scored 90% vs baseline LostKids but lost 0-10 vs
# v13_second_ring (which sends interceptors+demos itself).
#
# v4_smartdemo gates the every-3rd-attack rule on `enemy_recent_demos < 2`,
# i.e. only fire the cadence against passive defenders. Against active
# counter-offensive opponents (v13-class) the gate closes and the algo
# falls back to the lostkids_v3 scouts_failing+losing_hp_race rule.
#
# Original lostkids_v3 docstring follows.
#
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
        DEMOLISHER_TRIGGER_STRENGTH = 35
        ATTACK_MP_THRESHOLD = 17
        DEMOLISHER_MIN_MP = 18
        # If our last N scout waves each scored fewer than this many
        # breaches, force-switch to demolisher mode for the next attack
        # regardless of measured edge strength. Lookback set to 3 (not 2)
        # because in mirror conditions both sides often have a slow start
        # (T22 attack scores 0); waiting for THREE failures avoids burning
        # MP on demos when scouts will work in 1-2 more attempts.
        SCOUT_FAIL_BREACH_THRESHOLD = 4
        SCOUT_FAIL_LOOKBACK = 3
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

        # v3 adaptive helpers — gates designed to NOT fire in LK mirror:
        # - ANTI_DEMO: needs 4+ enemy demos seen (LK never sends demos).
        # - ANTI_SCOUT: streak ≥ 3 consecutive scout-spawn turns (LK pings
        #   in big waves every 6 turns with gaps → streak resets to 0).
        # - REACTIVE: opt-in; spends SP on extra turrets that hurt the
        #   canonical build economy in mirror.
        # - ANTI_STOCKPILE: opt-in; spawns wave-eater interceptors when
        #   opp_MP >= 22. Helps against game-1782-style hoarders but cost
        #   slightly regressed mirror parity in testing.
        self.ENABLE_REACTIVE = False
        self.ENABLE_ANTI_DEMO = True
        self.ENABLE_ANTI_SCOUT = True
        self.ENABLE_ANTI_STOCKPILE = False

        # Enemy deployment insights
        self.batch_count_history = [0, 0, 0]
        self.scored_on_locations = []
        self.enemy_recent_demos = 0
        self.enemy_recent_scouts = 0
        self.enemy_mp_spent_history = []
        self.last_attack_side = None
        self.consecutive_same_side_attacks = 0
        self.attack_count = 0

        # NEW v3: scout-success tracking.
        # `scout_attack_breaches` is a list of breach counts from each
        # scout-only attack this game. We force demolisher mode after the
        # latest SCOUT_FAIL_LOOKBACK entries are all under threshold.
        self.scout_attack_breaches = []
        # Per-turn aggregates filled by on_action_frame; consumed at the
        # next attack decision.
        self._pending_attack_was_scouts = False
        self._this_turn_lk_breaches = 0
        # NEW v3.1: track consecutive turns with non-zero opponent scout
        # spawns. Distinguishes between "every-turn pinger" (game 1620
        # opponent: scouts EVERY turn → consecutive count grows) and
        # "big-wave attacker" (LK-class: scouts every 6 turns → count
        # resets between attacks). Only fire anti-scout-hardening when
        # consecutive >= 3 (sustained pressure).
        self._opp_scout_streak = 0
        self._opp_scouts_this_turn = 0

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
        # Update the consecutive-scout-pings streak based on the just-finished
        # turn's opponent scout spawns (recorded by on_action_frame). This
        # distinguishes "ping every turn" (1620-style) from "big wave every
        # 6 turns" (LK-class) — the first gets a growing streak, the second
        # resets to 0 between waves.
        if self._opp_scouts_this_turn > 0:
            self._opp_scout_streak += 1
        else:
            self._opp_scout_streak = 0
        # Reset per-turn aggregates ahead of the next action phase.
        self._this_turn_lk_breaches = 0
        self._opp_scouts_this_turn = 0

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
        # Adaptive helpers fire AFTER the canonical build to spend ONLY
        # leftover SP. Each is gated to a specific opponent pattern that
        # the canonical build can't handle, AND a SP cushion to avoid
        # starving the canonical build.
        if getattr(self, "ENABLE_REACTIVE", False):
            self._reactive_defense(game_state)
        if getattr(self, "ENABLE_ANTI_DEMO", False):
            self._anti_demolisher_hardening(game_state)
        if getattr(self, "ENABLE_ANTI_SCOUT", False):
            self._anti_scout_hardening(game_state)

    def _anti_scout_hardening(self, game_state):
        """Plug the BL/BR edge-corner gaps that ranked game 1620 exploited:
        opponent sent 4-7 scouts EVERY TURN, breaching at deep tiles like
        (8,5) / (22,8) where canonical Lostkids has no turret in range.

        Trigger:
          - turn >= 12 (canonical Phase 2/3 done),
          - opp_scout_streak >= 3 (scouts seen for 3 consecutive turns —
            distinguishes the every-turn-pinger from a normal LK-class
            opponent that pings 17 scouts every 6 turns and has gaps),
          - SP cushion >= 8 (canonical build phases stay funded).

        Action: place ONE turret at the side that's been more-pressured.
        Tile (5,8) for BL, (22,8) for BR — both off-spawn-path, both at
        the breach hotspot. Range 2.5 covers the immediate breach edges.
        """
        if game_state.turn_number < 12:
            return
        if self._opp_scout_streak < 3:
            return
        cost = game_state.type_cost(TURRET)[SP]
        if game_state.get_resource(SP) < cost + 8:
            return
        # Tally where opponents have breached us; pick the worse side.
        from collections import Counter
        side_counts = Counter()
        for (x, y) in self.scored_on_locations:
            side_counts["L" if x < 14 else "R"] += 1
        if side_counts.get("R", 0) >= side_counts.get("L", 0):
            tile = [22, 8]
        else:
            tile = [5, 8]
        if game_state.contains_stationary_unit(tile):
            # Already placed; try the OTHER side once.
            other = [5, 8] if tile == [22, 8] else [22, 8]
            if game_state.contains_stationary_unit(other):
                return
            tile = other
        game_state.attempt_spawn(TURRET, tile)

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
        # NEW v3.2: anti-stockpile defense. Gated by ENABLE_ANTI_STOCKPILE
        # because in tightening tests the cost of pre-spawning interceptors
        # against an opponent who STILL won't attack on the predicted turn
        # outweighs the benefit. Useful against ladder opponents that
        # demonstrably hoard for 30+ scout dumps (game 1782 pattern); off
        # by default to keep mirror parity solid.
        if (getattr(self, "ENABLE_ANTI_STOCKPILE", False)
                and self.enemy_MP >= 22
                and self.my_MP >= 4
                and game_state.turn_number >= 12):
            interceptor_count = 2 if self.enemy_MP < 28 else 3
            self.spawn_interceptor(
                game_state, DEFENSE_INTERCEPTOR_LOCATION_LEFT,
                interceptor_count,
            )
            self.spawn_interceptor(
                game_state, DEFENSE_INTERCEPTOR_LOCATION_RIGHT,
                interceptor_count,
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

        # Canonical Lostkids spawn pair (4,9) + (3,10).
        edge_strength = (self.enemy_left_edge_strength if attack_side == "left"
                         else self.enemy_right_edge_strength)
        first_group_size = self.choose_number_of_scouts_in_first_group_based_on_enemy_edge_strength(edge_strength)
        first_loc = [4, 9]
        second_loc = [3, 10]
        if attack_side == "left":
            first_loc = [game_state.ARENA_SIZE - first_loc[0] - 1, first_loc[1]]
            second_loc = [game_state.ARENA_SIZE - second_loc[0] - 1, second_loc[1]]
        my_mp = game_state.get_resource(MP, 0)
        scouts_total = int(my_mp // game_state.type_cost(SCOUT)[MP])
        first = min(int(first_group_size), scouts_total)
        rest = scouts_total - first
        if first > 0:
            game_state.attempt_spawn(SCOUT, first_loc, first)
        if rest > 0:
            game_state.attempt_spawn(SCOUT, second_loc, rest)

    def _execute_demo_attack(self, game_state):
        """Pure demolisher wave: spend most MP on demos, zero scouts.
        Demos chew the wall/turret line at range 4.5. After 2-3 demo waves
        the opponent's defense is degraded enough that the next scout wave
        scores normally. Mixing demos + scouts in the same turn is sub-
        optimal — speed mismatch (demos 0.5 vs scouts 1) means scouts surge
        ahead and die without cover."""
        attack_side = "left" if self.turn_strategy == "attack_demo_left" else "right"
        # Demolishers benefit from spawning on the deepest viable edge tile
        # so they sit at range 4.5 from enemy structures while taking less
        # turret fire on approach.
        if attack_side == "right":
            spawn = [4, 9]
        else:
            spawn = [game_state.ARENA_SIZE - 4 - 1, 9]
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

        # Track total attack-mode decisions made this game.
        self.attack_count += 1

        # FIXED-CADENCE demolisher rule: every 3rd attack fires demos,
        # but ONLY if the opponent isn't an active counter-offensive type
        # (signaled by enemy_recent_demos > 1, meaning they've sent demos
        # of their own — v13-class opponents). Against passive defenders
        # (LK-class, funnel-rush) the cadence cracks open static walls.
        forced_demo_cadence = (
            (self.attack_count % 3) == 0
            and self.enemy_recent_demos < 2
            and self.my_MP >= DEMOLISHER_MIN_MP
        )

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
        losing_hp_race = (self.my_HP < self.enemy_HP - 5) or (self.my_HP < 35 and self.my_HP < self.enemy_HP)
        if ((scouts_failing and losing_hp_race) or forced_demo_cadence) and self.my_MP >= DEMOLISHER_MIN_MP:
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
                # Track THIS turn's raw opponent scout count for the
                # consecutive-streak detector. This is the per-turn
                # observation; the streak is updated next turn in
                # starter_strategy after we see whether scouts came in.
                self._opp_scouts_this_turn += e_scouts
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
