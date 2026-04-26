"""caravan_v1 — Gen-1 candidate. Maximum upgraded-Support stack.

Bet: Citadel's upgraded Support shield = 1 + 0.7*Y per scout, range 7.
At Y=11-13 stacking 5-6 supports gives ~50 shield per Scout — making
15-HP scouts effectively 65-HP tanks. A 12-scout flood at MP=12 puts
180 base HP into the lane; with caravan, ~780 effective HP. Most
defenses cannot stop that.

Trade-off: thinner front defense to free up SP for supports.
~30 SP investment in supports = ~7-8 turns of income foregone from
turrets, BUT each subsequent scout wave nets 5-12 HP off opponent.
Break-even is turn ~15; afterwards we dominate.

Architecture:
  * Light front (8 turrets, 4 upgraded by T8).
  * 6 upgraded Supports back row by T18 (3 at Y=13, 3 at Y=11).
  * Massive scout floods at MP >= 12, EARLIEST starting T7.
  * Defensive interceptors when enemy MP > 8.
  * Edge toggle for both sides.
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
        self.last_attack_side = None
        self._scout_breaches_last = 0
        self._this_turn_breaches = 0

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
                gamelib.debug_write("caravan_v1 fallback: " + traceback.format_exc())
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
        self._scout_breaches_last = self._this_turn_breaches
        self._this_turn_breaches = 0
        self._refund(gs)
        self._build_defense(gs)
        self._build_supports(gs)
        self._reactive(gs)
        self._defensive_interceptors(gs)
        self._attack(gs)

    def _build_defense(self, gs):
        t = gs.turn_number
        # 4 center + 2 sidelane + 2 corner = 8 turrets.
        center = [[11, 11], [13, 11], [14, 11], [16, 11]]
        sidelane = [[7, 9], [20, 9]]
        corners = [[2, 13], [25, 13]]
        gs.attempt_spawn(TURRET, center + sidelane + corners)

        # V-shape walls (lighter than lostkids).
        v_walls = [
            [3, 12], [4, 12], [5, 12],
            [6, 11], [8, 11],
            [9, 10], [10, 10],
            [12, 9], [15, 9],
            [17, 10], [18, 10],
            [19, 11], [21, 11],
            [22, 12], [23, 12], [24, 12],
        ]
        gs.attempt_spawn(WALL, v_walls)

        if t >= 2:
            for loc in [[13, 11], [14, 11]]:
                gs.attempt_upgrade(loc)
        if t >= 4:
            gs.attempt_upgrade(corners)
        if t >= 6:
            gs.attempt_upgrade([[11, 11], [16, 11]])
        if t >= 8:
            gs.attempt_upgrade(sidelane)

        # Phase: only add a couple more turrets if pressured (T12+).
        if t >= 12:
            for loc in [[5, 11], [22, 11]]:
                gs.attempt_spawn(TURRET, loc)

    def _build_supports(self, gs):
        """The big bet: 6 upgraded supports by T18.

        Y=11 supports give 1 + 0.7*11 = 8.8 per scout.
        Y=13 supports give 1 + 0.7*13 = 10.1 per scout.
        6 supports → ~50-60 shield per scout passing through range 7.
        Range 7 covers spawn corners at [13,0]/[14,0] all the way to ~Y=7.
        """
        t = gs.turn_number
        if t >= 5:
            for loc in [[12, 11], [15, 11]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 8:
            for loc in [[12, 13], [15, 13]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 12:
            for loc in [[13, 13], [14, 13]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 18:
            for loc in [[11, 13], [16, 13]]:
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
                # Don't refund supports — they're our investment.
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

    def _defensive_interceptors(self, gs):
        e_mp = gs.get_resource(MP, 1)
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        ic_cost = gs.type_cost(INTERCEPTOR)[MP]
        # If enemy hoarded a big rush, drop 2 interceptors to slow it.
        if e_mp >= 12 * scout_cost and my_mp >= 2 * ic_cost:
            for spawn in [[6, 7], [21, 7]]:
                if not gs.game_map.in_arena_bounds(spawn):
                    continue
                if gs.contains_stationary_unit(spawn):
                    continue
                gs.attempt_spawn(INTERCEPTOR, spawn)

    def _enemy_edge_strength(self, gs, side):
        if side == "left":
            locs = [[0, 14], [1, 14], [2, 14], [3, 14], [1, 15], [2, 15], [3, 15]]
            ref = [0.5, 13]
        else:
            locs = [[27, 14], [26, 14], [25, 14], [24, 14], [26, 15], [25, 15], [24, 15]]
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
        if t < 1:
            return

        side = self._pick_side(gs)
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT

        if side == "right":
            scout_cands = [[4, 9], [3, 10], [13, 0], [14, 0], [5, 8]]
            demo_cands = [[4, 9], [5, 8], [13, 0]]
        else:
            scout_cands = [[23, 9], [24, 10], [14, 0], [13, 0], [22, 8]]
            demo_cands = [[23, 9], [22, 8], [14, 0]]

        # Trigger threshold scales with how many supports are up — the more
        # we have, the more value per scout.
        n_supports = sum(
            1
            for x, y in [
                (12, 11), (15, 11), (12, 13), (15, 13),
                (13, 13), (14, 13), (11, 13), (16, 13),
            ]
            if gs.contains_stationary_unit([x, y])
        )
        scout_trigger = max(8, 14 - n_supports)  # 14→8 as supports grow

        # Demo wave if scouts visibly fail or front is dense.
        if self._scout_breaches_last == 0 and t >= 8 and my_mp >= 4 * demo_cost:
            spawn = self._best_spawn(gs, demo_cands, target)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, min(int(my_mp // demo_cost), 7))
                self.last_attack_side = f"demo_{side}"
                return

        if my_mp >= scout_trigger * scout_cost:
            spawn = self._best_spawn(gs, scout_cands, target)
            if spawn is None:
                return
            gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
            self.last_attack_side = f"scout_{side}"

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


if __name__ == "__main__":
    AlgoStrategy().start()
