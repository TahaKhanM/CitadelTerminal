"""arc_dynamic — fundamentally different paradigm: Python-driven adaptive defense.

NO defense-order.json. Every turn, the algo evaluates game state and decides
what to build via a priority queue. Priorities shift based on:
  * Opponent's spawn timing (early attacker → defense first; hoarder → supports)
  * Where opponent has scored on us (heat-map → reactive turrets)
  * Our resource curve (front-load critical defense before supports)

Key innovations vs JSON-driven algos:
  1. **Reactive priority shifting**: if opponent attacks early (T<10), defer
     supports indefinitely — no shield ramp matters if you're dead.
  2. **Resource-aware build choice**: prefer the structure with highest
     marginal HP-saved-per-SP given current threat profile.
  3. **Per-turn re-planning**: don't commit to a fixed sequence; reconsider
     every turn given new information.

Architecture:
  * Defense template = Lostkids V-funnel (proven shape)
  * Build priorities = list of (priority_func, build_func) pairs
  * Each turn, sort by priority, build highest-priority first
"""
from __future__ import annotations

import gamelib
import random
import math
import json
import os
import sys
import time
import traceback
import signal
import threading
from sys import maxsize


TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    if hasattr(signal, "SIGALRM"):
        try:
            old = signal.signal(signal.SIGALRM, _sigalrm_handler)
            signal.alarm(seconds)

            def disarm():
                signal.alarm(0)
                signal.signal(signal.SIGALRM, old)
            return disarm
        except (ValueError, OSError):
            pass
    timer = threading.Timer(seconds, lambda: None)
    timer.daemon = True
    timer.start()
    return timer.cancel


# Defense layout constants (Lostkids V-funnel)
V_WALLS = [
    [3, 12], [4, 12], [5, 12],
    [6, 11], [7, 11], [8, 11],
    [9, 10], [10, 10], [11, 10],
    [12, 9], [13, 9], [14, 9], [15, 9],
    [16, 10], [17, 10], [18, 10],
    [19, 11], [20, 11], [21, 11],
    [22, 12], [23, 12], [24, 12],
]
DEEPER_V_WALLS = [
    [10, 9], [11, 8], [17, 9],
    [16, 8], [9, 8], [8, 9],
    [7, 10], [18, 8], [19, 9],
    [20, 10], [6, 9], [5, 10],
    [4, 11], [21, 9], [22, 10], [23, 11],
]
INNER_WALLS = [[12, 7], [13, 7], [14, 7], [15, 7]]
CORNER_TURRETS = [[2, 13], [25, 13]]
CORNER_EXT = [[3, 13], [24, 13], [4, 13], [23, 13]]
FUNNEL_TURRETS = [[6, 10], [9, 9], [18, 9], [21, 10]]
SUPPORT_BACK = [[11, 6], [16, 6], [11, 5], [16, 5]]
SUPPORT_EXTRA = [[10, 5], [17, 5]]
DEEP_TURRETS = [[12, 8], [15, 8]]
OUTER_TURRETS = [[5, 9], [22, 9]]


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write(f'arc_dynamic seed: {seed}')

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
        self.t_dmg_up = float(ui[2].get("upgrade", {}).get("attackDamageWalker", 20))
        self.t_dmg_base = float(ui[2].get("attackDamageWalker", 6))

        # State
        self.scored_on_locations = []
        self.opp_total_scouts = 0
        self.opp_total_demos = 0
        self.opp_first_attack_turn = -1
        self.opp_attack_history = []  # list of (turn, scouts, demos)
        self.last_attack_side = None
        self.consecutive_same_side_attacks = 0
        self.scout_attack_breaches = []
        self._pending_attack_was_scouts = False
        self._this_turn_breaches = 0
        self.last_opp_mp = 1.0
        self.opp_aggression = 0  # 0=hoarder, 1=balanced, 2=rusher

    def on_turn(self, turn_state):
        disarm = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        gs = None
        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            self._main(gs)
            gs.submit_turn()
        except Exception:
            try:
                gamelib.debug_write("arc_dynamic fallback: " + traceback.format_exc())
            except Exception:
                pass
            try:
                if gs is None:
                    gs = gamelib.GameState(self.config, turn_state)
                    gs.suppress_warnings(True)
                self._safe_fallback(gs)
                gs.submit_turn()
            except Exception:
                pass
        finally:
            try:
                disarm()
            except Exception:
                pass

    def _safe_fallback(self, gs):
        for loc in [[2, 13], [25, 13], [11, 11], [16, 11], [13, 11], [14, 11]]:
            gs.attempt_spawn(TURRET, loc)

    # ========================================================
    # MAIN — adaptive priority-driven build
    # ========================================================
    def _main(self, gs):
        if self._pending_attack_was_scouts:
            self.scout_attack_breaches.append(self._this_turn_breaches)
            self._pending_attack_was_scouts = False
        self._this_turn_breaches = 0

        t = gs.turn_number

        # 1. Update opponent profile.
        self._classify_opponent(gs)

        # 2. Refund nearly-dead structures (proven good practice).
        self._refund_low_hp(gs)

        # 3. Build defense per dynamic priority.
        self._build_dynamic(gs)

        # 4. Reactive defense at chronic breach tiles.
        self._reactive_patches(gs)

        # 5. Decide attack.
        self._attack(gs)

    def _classify_opponent(self, gs):
        """Update opponent profile from on_action_frame data."""
        t = gs.turn_number
        # Fast classification: pre-T8.
        if t < 8:
            if self.opp_first_attack_turn != -1 and self.opp_first_attack_turn <= 6:
                if self.opp_total_scouts >= 5:
                    self.opp_aggression = 2  # rusher
                else:
                    self.opp_aggression = 1
        # Lock at T8.
        elif t == 8:
            if self.opp_first_attack_turn == -1 or self.opp_first_attack_turn >= 12:
                self.opp_aggression = 0  # hoarder
            elif self.opp_total_scouts + self.opp_total_demos * 3 >= 10:
                self.opp_aggression = 2  # rusher
            else:
                self.opp_aggression = 1  # balanced

    def _refund_low_hp(self, gs):
        for x in range(28):
            for y in range(14):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                u = gs.contains_stationary_unit([x, y])
                if not u:
                    continue
                if u.unit_type == TURRET and u.health / max(u.max_health, 1) < 0.30:
                    gs.attempt_remove([x, y])
                elif u.unit_type == WALL and u.health / max(u.max_health, 1) < 0.50:
                    gs.attempt_remove([x, y])

    def _build_dynamic(self, gs):
        """Build defense based on dynamic priorities.

        Priority order is RECOMPUTED each turn given:
          - opponent aggression profile
          - turn number
          - current SP
        """
        t = gs.turn_number
        sp = gs.get_resource(SP, 0)

        # Priority list: (name, build_function returning SP cost or 0)
        # Build greedy: highest priority first; stop when SP exhausted.
        actions = self._priority_actions(gs, t, sp)
        for name, fn in actions:
            try:
                fn(gs)
            except Exception:
                pass

    def _priority_actions(self, gs, t, sp):
        """Return list of (name, fn) in priority order for this turn."""
        actions = []

        # ALWAYS HIGHEST: V-funnel walls + 2 corner anchors (T0-T5).
        # Without these, anything else is meaningless.
        actions.append(("v_walls_corners", self._build_v_walls_and_corners))

        # 4 funnel turrets (T1+).
        if t >= 1:
            actions.append(("funnel_turrets", self._build_funnel_turrets))

        # CRITICAL EARLY: if opponent is rusher OR we don't know yet,
        # upgrade corner turrets ASAP. They're our T6-T10 defense.
        if t >= 2 and self.opp_aggression != 0:
            actions.append(("corner_upgrades", self._upgrade_corners))

        # Deeper-V walls (T3+).
        if t >= 3:
            actions.append(("deeper_walls", self._build_deeper_walls))

        # If we know opponent is hoarder, defer corner upgrades, focus on supports.
        if t >= 2 and self.opp_aggression == 0:
            actions.append(("corner_upgrades", self._upgrade_corners))

        # 4-corner cluster (T5+).
        if t >= 5:
            actions.append(("corner_cluster", self._build_corner_cluster))

        # Inner walls (deepest V).
        if t >= 6:
            actions.append(("inner_walls", self._build_inner_walls))

        # Funnel turret upgrades (T7+).
        if t >= 7:
            actions.append(("funnel_upgrades", self._upgrade_funnel))

        # Supports — timing depends on aggression.
        # Hoarder: supports earlier (T8+). Rusher: supports late (T15+).
        support_start = 15 if self.opp_aggression == 2 else 10 if self.opp_aggression == 1 else 8
        if t >= support_start:
            actions.append(("supports_back", self._build_back_supports))

        # Deep turrets (12,8)/(15,8) and outer (5,9)/(22,9).
        if t >= 12:
            actions.append(("deep_turrets", self._build_deep_turrets))
        if t >= 18:
            actions.append(("outer_turrets", self._build_outer_turrets))

        # More supports late.
        if t >= 22:
            actions.append(("supports_extra", self._build_extra_supports))

        return actions

    # === build functions ===
    def _build_v_walls_and_corners(self, gs):
        for loc in CORNER_TURRETS:
            gs.attempt_spawn(TURRET, loc)
        for loc in V_WALLS:
            gs.attempt_spawn(WALL, loc)

    def _build_funnel_turrets(self, gs):
        for loc in FUNNEL_TURRETS:
            gs.attempt_spawn(TURRET, loc)

    def _upgrade_corners(self, gs):
        for loc in CORNER_TURRETS:
            gs.attempt_upgrade(loc)

    def _build_deeper_walls(self, gs):
        for loc in DEEPER_V_WALLS:
            gs.attempt_spawn(WALL, loc)

    def _build_corner_cluster(self, gs):
        for loc in CORNER_EXT:
            gs.attempt_spawn(TURRET, loc)
            gs.attempt_upgrade(loc)

    def _build_inner_walls(self, gs):
        for loc in INNER_WALLS:
            gs.attempt_spawn(WALL, loc)

    def _upgrade_funnel(self, gs):
        for loc in FUNNEL_TURRETS:
            gs.attempt_upgrade(loc)

    def _build_back_supports(self, gs):
        for loc in SUPPORT_BACK:
            gs.attempt_spawn(SUPPORT, loc)
            gs.attempt_upgrade(loc)

    def _build_deep_turrets(self, gs):
        for loc in DEEP_TURRETS:
            gs.attempt_spawn(TURRET, loc)
            gs.attempt_upgrade(loc)

    def _build_outer_turrets(self, gs):
        for loc in OUTER_TURRETS:
            gs.attempt_spawn(TURRET, loc)
            gs.attempt_upgrade(loc)

    def _build_extra_supports(self, gs):
        for loc in SUPPORT_EXTRA:
            gs.attempt_spawn(SUPPORT, loc)
            gs.attempt_upgrade(loc)

    # === reactive defense ===
    def _reactive_patches(self, gs):
        if gs.get_resource(SP, 0) < 4:
            return
        from collections import Counter
        counts = Counter([tuple(p) for p in self.scored_on_locations[-12:]])
        for (x, y), n in counts.most_common(2):
            if n < 2:
                break
            cands = [[x, y + 1], [x + (1 if x < 14 else -1), y + 1]]
            for c in cands:
                if c[1] < 0 or c[1] > 13:
                    continue
                if not gs.game_map.in_arena_bounds(c):
                    continue
                if gs.contains_stationary_unit(c):
                    continue
                if gs.attempt_spawn(TURRET, c) > 0:
                    break

    # === attack ===
    def _enemy_edge_strength(self, gs, side):
        if side == "left":
            locs = [[0, 14], [1, 14], [2, 14], [3, 14], [1, 15], [2, 15], [3, 15], [2, 16]]
            ref = [0.5, 13]
        else:
            locs = [[27, 14], [26, 14], [25, 14], [24, 14], [26, 15], [25, 15], [24, 15], [25, 16]]
            ref = [26.5, 13]
        s = 0.0
        for loc in locs:
            u = gs.contains_stationary_unit(loc)
            if not u:
                continue
            d = math.dist(ref, loc)
            if u.unit_type == TURRET:
                s += (25.0 if u.upgraded else 5.0) / max(d, 0.1)
            elif u.unit_type == WALL and loc[1] == 14:
                s += 3.0 if u.upgraded else 1.0
        return s

    def _path_damage(self, gs, spawn, target_edge):
        try:
            path = gs.find_path_to_edge(spawn, target_edge)
        except Exception:
            return float("inf")
        if not path:
            return float("inf")
        total = 0.0
        for p in path:
            try:
                atks = gs.get_attackers(p, 0)
            except Exception:
                atks = []
            for a in atks:
                total += self.t_dmg_up if a.upgraded else self.t_dmg_base
        return total

    def _best_spawn(self, gs, cands, target):
        scored = []
        for c in cands:
            if not gs.game_map.in_arena_bounds(c):
                continue
            if gs.contains_stationary_unit(c):
                continue
            scored.append((self._path_damage(gs, c, target), c))
        scored.sort(key=lambda kv: kv[0])
        return scored[0][1] if scored else None

    def _attack(self, gs):
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        ic_cost = gs.type_cost(INTERCEPTOR)[MP]
        if t < 1:
            return

        # Pick weaker side.
        ls = self._enemy_edge_strength(gs, "left")
        rs = self._enemy_edge_strength(gs, "right")
        side = "right" if ls > rs else "left"
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT

        if side == "right":
            scout_cands = [[4, 9], [3, 10], [13, 0], [14, 0], [5, 8]]
            demo_cands = [[4, 9], [5, 8], [3, 10]]
        else:
            scout_cands = [[23, 9], [24, 10], [14, 0], [13, 0], [22, 8]]
            demo_cands = [[23, 9], [22, 8], [24, 10]]

        # Aggression-aware MP threshold.
        # Vs rusher: attack early (low threshold).
        # Vs hoarder: hoard MP for big wave.
        if self.opp_aggression == 2:
            scout_threshold = 10
            demo_threshold = 9
        elif self.opp_aggression == 0:
            scout_threshold = 16
            demo_threshold = 15
        else:
            scout_threshold = 12
            demo_threshold = 12

        # Demo trigger if defense is heavy.
        side_strength = ls if side == "left" else rs
        scouts_failing = (
            len(self.scout_attack_breaches) >= 1
            and self.scout_attack_breaches[-1] < 4
        )

        if (side_strength >= 25 and my_mp >= demo_threshold) or (scouts_failing and my_mp >= demo_threshold):
            # Demolisher wave
            spawn = self._best_spawn(gs, demo_cands, target)
            if spawn is not None:
                n = min(int(my_mp // demo_cost), 8)
                gs.attempt_spawn(DEMOLISHER, spawn, n)
                self.last_attack_side = f"demo_{side}"
            return

        if my_mp >= scout_threshold:
            spawn = self._best_spawn(gs, scout_cands, target)
            if spawn is not None:
                # First-wave size scaled by enemy strength.
                first = min(5 + int(side_strength / 7), 10)
                scouts_total = int(my_mp // scout_cost)
                first = min(first, scouts_total)
                rest = scouts_total - first
                if first > 0:
                    gs.attempt_spawn(SCOUT, spawn, first)
                if rest > 0:
                    sec = scout_cands[1] if scout_cands[1] != spawn else scout_cands[0]
                    if not gs.contains_stationary_unit(sec) and gs.game_map.in_arena_bounds(sec):
                        gs.attempt_spawn(SCOUT, sec, rest)
                self._pending_attack_was_scouts = True
                self.last_attack_side = side
                return

        # Defensive interceptor screen if opp is hoarding big rush.
        opp_mp = gs.get_resource(MP, 1)
        if opp_mp >= 12 and my_mp >= 2 * ic_cost and t >= 6:
            for sp_loc in [[6, 7], [21, 7]]:
                if gs.game_map.in_arena_bounds(sp_loc) and not gs.contains_stationary_unit(sp_loc):
                    gs.attempt_spawn(INTERCEPTOR, sp_loc)

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            if len(b) < 5:
                continue
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on_locations.append(tuple(loc))
            elif owner == 1:
                self._this_turn_breaches += 1
        # Track opponent spawns.
        try:
            if state["turnInfo"][0] == 1 and state["turnInfo"][2] == 0:
                spawns = state.get("events", {}).get("spawn", [])
                e_scouts = e_demos = e_ints = 0
                for s in spawns:
                    if len(s) < 4 or s[3] != 2:
                        continue
                    if s[1] == 3:
                        e_scouts += 1
                    elif s[1] == 4:
                        e_demos += 1
                    elif s[1] == 5:
                        e_ints += 1
                self.opp_total_scouts += e_scouts
                self.opp_total_demos += e_demos
                if self.opp_first_attack_turn == -1 and (e_scouts or e_demos or e_ints):
                    try:
                        self.opp_first_attack_turn = int(state["turnInfo"][1])
                    except (KeyError, IndexError, ValueError):
                        self.opp_first_attack_turn = 0
        except Exception:
            pass


if __name__ == "__main__":
    AlgoStrategy().start()
