"""chimera_v1 — Gen-1 candidate. Adaptive multi-archetype.

Detects opponent profile during T0-T6 and switches to one of three
attack patterns:
  * "scout" → opponent is light on defense, scout floods score
  * "demo" → opponent has heavy turrets, demolishers chew them
  * "hoard" → opponent isn't attacking; we accumulate and stab T20+

Key signals collected via on_action_frame:
  * enemy_attack_mass: scouts+demos+interceptors deployed by T6
  * enemy_front_density: stationary units in opponent's y∈[14,16]
  * enemy_corner_thickness: structure count at corners

Defense is symmetric Lostkids-V (same as apex_v1) — the differentiator
is the offense decision tree. Risk: getting profiling wrong and using
the wrong attack mode. Mitigation: re-evaluate every 6 turns.
"""
from __future__ import annotations

import gamelib
import math
import json
import signal
import threading
import traceback


TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _h(_a, _b):
    raise _TurnTimeout("watchdog")


def _arm_watchdog(seconds):
    if hasattr(signal, "SIGALRM"):
        try:
            old = signal.signal(signal.SIGALRM, _h)
            signal.alarm(seconds)
            return lambda: (signal.alarm(0), signal.signal(signal.SIGALRM, old))
        except (ValueError, OSError):
            pass
    timer = threading.Timer(seconds, lambda: None)
    timer.daemon = True
    timer.start()
    return timer.cancel


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.scored_on = []
        self.enemy_attack_mass = 0
        self.enemy_demo_mass = 0
        self.enemy_scout_mass = 0
        self.profile = "balanced"
        self.scout_breaches_last_wave = 0
        self._this_turn_breaches = 0
        self.last_profile_eval = -1
        self._pending_scout_attack = False

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
                gamelib.debug_write("chimera_v1 fallback: " + traceback.format_exc())
            except Exception:
                pass
            try:
                if gs is None:
                    gs = gamelib.GameState(self.config, turn_state)
                    gs.suppress_warnings(True)
                self._fallback(gs)
                gs.submit_turn()
            except Exception:
                pass
        finally:
            try:
                disarm()
            except Exception:
                pass

    def _fallback(self, gs):
        for loc in [[11, 11], [16, 11], [13, 11], [14, 11], [2, 13], [25, 13]]:
            gs.attempt_spawn(TURRET, loc)

    def _main(self, gs):
        if self._pending_scout_attack:
            self.scout_breaches_last_wave = self._this_turn_breaches
            self._pending_scout_attack = False
        self._this_turn_breaches = 0

        self._refund(gs)
        self._build_defense(gs)
        self._build_supports(gs)
        self._reactive(gs)
        self._evaluate_profile(gs)
        self._attack(gs)

    def _build_defense(self, gs):
        t = gs.turn_number
        v_walls = [
            [3, 12], [4, 12], [5, 12],
            [6, 11], [7, 11], [8, 11],
            [9, 10], [10, 10], [11, 10],
            [12, 9], [13, 9], [14, 9], [15, 9],
            [16, 10], [17, 10], [18, 10],
            [19, 11], [20, 11], [21, 11],
            [22, 12], [23, 12], [24, 12],
        ]
        gs.attempt_spawn(WALL, v_walls)
        gs.attempt_spawn(TURRET, [[2, 13], [25, 13]])

        funnel_turrets = [[6, 10], [9, 9], [18, 9], [21, 10]]
        gs.attempt_spawn(TURRET, funnel_turrets)

        if t >= 2:
            for loc in [[10, 9], [11, 8], [16, 8], [17, 9]]:
                gs.attempt_spawn(WALL, loc)

        if t >= 3:
            for loc in [[2, 13], [25, 13]]:
                gs.attempt_upgrade(loc)
        if t >= 5:
            for loc in [[3, 13], [24, 13]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)
        if t >= 7:
            for loc in funnel_turrets:
                gs.attempt_upgrade(loc)
        if t >= 10:
            for loc in [[5, 9], [22, 9]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)
        if t >= 14:
            for loc in [[4, 13], [23, 13]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

    def _build_supports(self, gs):
        # Conservative support build — caravan_v1 is the dedicated specialist.
        t = gs.turn_number
        if t >= 9:
            for loc in [[12, 6], [15, 6]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 14:
            for loc in [[11, 5], [16, 5]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)

    def _refund(self, gs):
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

    def _reactive(self, gs):
        if gs.get_resource(SP, 0) < 4:
            return
        from collections import Counter
        counts = Counter([tuple(p) for p in self.scored_on[-10:]])
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

    # ============================================================
    # OPPONENT PROFILING
    # ============================================================
    def _evaluate_profile(self, gs):
        t = gs.turn_number
        # Re-eval every 6 turns or after T6 (first lock-in).
        if t < 6:
            return
        if t - self.last_profile_eval < 6:
            return
        self.last_profile_eval = t

        front = self._enemy_front_density(gs)
        corner = self._enemy_corner_thickness(gs)
        agg = self.enemy_attack_mass

        # Profile heuristics:
        #   * "fortress" → very dense front, low aggression
        #   * "rusher" → low front, high aggression
        #   * "balanced" → middle
        if front >= 18 and agg < 6:
            self.profile = "fortress"  # use demolishers
        elif front <= 10 and agg >= 8:
            self.profile = "rusher"  # counter-flood + interceptors
        elif self.enemy_demo_mass >= 4:
            self.profile = "demoer"  # need extra anti-demo
        else:
            self.profile = "balanced"

    def _enemy_front_density(self, gs):
        n = 0
        for y in (14, 15, 16):
            for x in range(28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                u = gs.contains_stationary_unit([x, y])
                if u and u.unit_type in (WALL, TURRET):
                    n += 2 if u.upgraded else 1
        return n

    def _enemy_corner_thickness(self, gs):
        n = 0
        for x, y in [(0, 14), (1, 14), (2, 14), (27, 14), (26, 14), (25, 14)]:
            u = gs.contains_stationary_unit([x, y])
            if u:
                n += 1
        return n

    # ============================================================
    # ATTACK
    # ============================================================
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

    def _pick_side(self, gs):
        l = self._enemy_edge_strength(gs, "left")
        r = self._enemy_edge_strength(gs, "right")
        return "right" if l > r else "left"

    def _path_damage(self, gs, spawn, target):
        try:
            path = gs.find_path_to_edge(spawn, target)
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

        side = self._pick_side(gs)
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT

        if side == "right":
            scout_cands = [[4, 9], [3, 10], [13, 0], [14, 0]]
            demo_cands = [[4, 9], [5, 8], [13, 0]]
        else:
            scout_cands = [[23, 9], [24, 10], [14, 0], [13, 0]]
            demo_cands = [[23, 9], [22, 8], [14, 0]]

        # Profile-driven attack selection
        if self.profile == "fortress":
            # Lots of demo waves
            if my_mp >= 4 * demo_cost:
                spawn = self._best_spawn(gs, demo_cands, target)
                if spawn:
                    gs.attempt_spawn(DEMOLISHER, spawn, min(int(my_mp // demo_cost), 8))
                    return
            # If we can't afford demos yet, save MP (let it grow even with decay).
            return

        if self.profile == "rusher":
            # Defensive interceptors + counter-flood
            e_mp = gs.get_resource(MP, 1)
            if e_mp >= 8 * scout_cost and my_mp >= 2 * ic_cost:
                for spawn in [[6, 7], [21, 7]]:
                    if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                        gs.attempt_spawn(INTERCEPTOR, spawn)
            if my_mp >= 10 * scout_cost:
                spawn = self._best_spawn(gs, scout_cands, target)
                if spawn:
                    gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
                    self._pending_scout_attack = True
            return

        if self.profile == "demoer":
            # Extra anti-demo + scouts
            if t >= 8 and my_mp >= 12 * scout_cost:
                spawn = self._best_spawn(gs, scout_cands, target)
                if spawn:
                    gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
                    self._pending_scout_attack = True
            return

        # balanced — default Lostkids-style
        if t >= 8 and self.scout_breaches_last_wave == 0 and my_mp >= 4 * demo_cost:
            spawn = self._best_spawn(gs, demo_cands, target)
            if spawn:
                gs.attempt_spawn(DEMOLISHER, spawn, min(int(my_mp // demo_cost), 6))
                return
        if my_mp >= 12 * scout_cost:
            spawn = self._best_spawn(gs, scout_cands, target)
            if spawn:
                gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
                self._pending_scout_attack = True

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
                self.scored_on.append(tuple(loc))
            elif owner == 1:
                self._this_turn_breaches += 1
        # Track enemy mobile-unit deployments.
        for s in events.get("spawn", []):
            if len(s) < 4:
                continue
            unit_type = s[1]
            owner = s[3]
            if owner != 2:
                continue
            if unit_type == 3:  # Scout
                self.enemy_scout_mass += 1
                self.enemy_attack_mass += 1
            elif unit_type == 4:  # Demolisher
                self.enemy_demo_mass += 1
                self.enemy_attack_mass += 3
            elif unit_type == 5:  # Interceptor
                self.enemy_attack_mass += 1


if __name__ == "__main__":
    AlgoStrategy().start()
