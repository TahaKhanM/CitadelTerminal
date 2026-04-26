"""arc_demoline — fundamentally different topology: horizontal wall + permanent demo highway.

NOT a V-funnel fork. Architecture:

  y=14:  enemy structures (their problem)
  y=13:  our corner anchors (2,13)(25,13) + extension turrets
  y=12:  base turrets [5,12][7,12][9,12][13,12][14,12][18,12][20,12][22,12]
  y=11:  WALL ROW spanning x=[3..24] with gaps at (4,11)(13,11)(14,11)(23,11)
  y=10:  empty 'demo highway' — our demos park here, fire at y≥12 from range 4.5
  y= 9:  demo spawn tiles (4,9) (left-attack) and (23,9) (right-attack)

Key insight (proven by Snorkeldink finalist): if our demos sit at y=10 with
walls in front blocking pathing, they fire from range 4.5 at y≥14 enemy
structures while remaining outside enemy turret 3.5 range. Each frame they
live = 8 dmg to one structure. Sustained chip > scout flood vs upgraded
defenses.

Defense priorities (Python-driven, no JSON):
  1. y=12 turrets (T0-T2) — kill scouts that breach the wall at y=11
  2. y=11 wall row (T0-T2) — pathing barrier
  3. corner anchor upgrades (T3-T5)
  4. y=12 turret upgrades (T5-T8) — bunker DPS
  5. supports along demo highway (T8+) — shield demos as they fire
  6. extra y=12 turrets (T12+)

Offense:
  * SUSTAINED demo pressure: spawn ⌊MP/3⌋ demos every turn from T4.
  * Scouts only when enemy front collapses (chip damage < 6 structures left).
  * Path-aware spawn over (4,9)/(23,9)/(3,10)/(24,10) for resilience.
"""
from __future__ import annotations

import gamelib
import math
import json
import os
import sys
import time
import random
import traceback
import signal
import threading
from sys import maxsize


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


# Topology constants
WALL_ROW_GAPS = {(4, 11), (13, 11), (14, 11), (23, 11)}
WALL_ROW = [[x, 11] for x in range(3, 25) if (x, 11) not in WALL_ROW_GAPS]
Y12_TURRETS = [[5, 12], [7, 12], [9, 12], [13, 12], [14, 12], [18, 12], [20, 12], [22, 12]]
Y12_TURRETS_PRIORITY_UPGRADE = [[13, 12], [14, 12], [9, 12], [18, 12]]  # center first
CORNER_ANCHORS = [[2, 13], [25, 13]]
CORNER_EXT = [[3, 13], [24, 13]]
WALL_TOP = [[3, 13], [24, 13], [4, 13], [23, 13]]  # walls on y=13 edges (corner protection)
HIGHWAY_SUPPORTS = [[12, 10], [15, 10]]  # along y=10 demo highway
HIGHWAY_SUPPORTS_EXTRA = [[11, 10], [16, 10]]  # extension


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write(f'arc_demoline seed: {seed}')

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

        self.scored_on_locations = []
        self.opp_total_scouts = 0
        self.opp_total_demos = 0
        self.opp_first_attack_turn = -1
        self.last_attack_side = None
        self._this_turn_breaches = 0

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
                gamelib.debug_write("arc_demoline fallback: " + traceback.format_exc())
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
        for loc in [[2, 13], [25, 13], [13, 12], [14, 12], [9, 12], [18, 12]]:
            gs.attempt_spawn(TURRET, loc)

    def _main(self, gs):
        t = gs.turn_number
        self._refund_low_hp(gs)
        self._build_demoline_defense(gs, t)
        self._reactive_patches(gs)
        self._attack(gs, t)

    def _build_demoline_defense(self, gs, t):
        # Phase 1 (T0): 2 corners + 4 center y=12 turrets + 8 walls.
        # T0 SP = 8. Corners (4 SP) + 2 center turrets at (13,12)(14,12) (4 SP) = 8 SP.
        for loc in CORNER_ANCHORS:
            gs.attempt_spawn(TURRET, loc)
        for loc in [[13, 12], [14, 12]]:
            gs.attempt_spawn(TURRET, loc)

        # Phase 2 (T1+): start the wall row.
        if t >= 1:
            for loc in WALL_ROW:
                gs.attempt_spawn(WALL, loc)

        # Phase 3 (T2+): outer y=12 turrets.
        if t >= 2:
            for loc in [[9, 12], [18, 12]]:
                gs.attempt_spawn(TURRET, loc)

        # Phase 4 (T3+): UPGRADE corner anchors.
        if t >= 3:
            for loc in CORNER_ANCHORS:
                gs.attempt_upgrade(loc)

        # Phase 5 (T4+): more y=12 turrets.
        if t >= 4:
            for loc in [[5, 12], [22, 12]]:
                gs.attempt_spawn(TURRET, loc)

        # Phase 6 (T5+): UPGRADE center y=12 turrets.
        if t >= 5:
            for loc in [[13, 12], [14, 12]]:
                gs.attempt_upgrade(loc)

        # Phase 7 (T7+): UPGRADE outer y=12 turrets.
        if t >= 7:
            for loc in [[9, 12], [18, 12]]:
                gs.attempt_upgrade(loc)

        # Phase 8 (T9+): more y=12 turrets.
        if t >= 9:
            for loc in [[7, 12], [20, 12]]:
                gs.attempt_spawn(TURRET, loc)

        # Phase 9 (T10+): UPGRADE far y=12 turrets.
        if t >= 10:
            for loc in [[5, 12], [22, 12]]:
                gs.attempt_upgrade(loc)

        # Phase 10 (T12+): demo-highway supports — shield our demos as they fire.
        if t >= 12:
            for loc in HIGHWAY_SUPPORTS:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)

        # Phase 11 (T15+): corner-extension turrets.
        if t >= 15:
            for loc in CORNER_EXT:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

        # Phase 12 (T18+): top walls + extra supports.
        if t >= 18:
            for loc in WALL_TOP[2:]:  # (4,13)(23,13)
                gs.attempt_spawn(WALL, loc)
            for loc in HIGHWAY_SUPPORTS_EXTRA:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)

        # Phase 13 (T22+): all remaining y=12 turret upgrades.
        if t >= 22:
            for loc in Y12_TURRETS:
                u = gs.contains_stationary_unit(loc)
                if u and u.unit_type == TURRET and not u.upgraded:
                    gs.attempt_upgrade(loc)

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

    # === offense ===
    def _enemy_front_count(self, gs):
        n = 0
        for y in (14, 15, 16):
            for x in range(28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                u = gs.contains_stationary_unit([x, y])
                if u and u.unit_type in (WALL, TURRET):
                    n += 2 if u.upgraded else 1
        return n

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

    def _attack(self, gs, t):
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        if t < 1:
            return

        ls = self._enemy_edge_strength(gs, "left")
        rs = self._enemy_edge_strength(gs, "right")
        side = "right" if ls > rs else "left"
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT

        if side == "right":
            demo_cands = [[4, 9], [3, 10], [5, 8], [13, 0]]
            scout_cands = [[3, 10], [4, 9], [13, 0], [14, 0]]
        else:
            demo_cands = [[23, 9], [24, 10], [22, 8], [14, 0]]
            scout_cands = [[24, 10], [23, 9], [14, 0], [13, 0]]

        front_score = self._enemy_front_count(gs)

        # If enemy front gutted (<8 score), pivot to scout flood.
        if front_score < 8 and my_mp >= 8 * scout_cost:
            spawn = self._best_spawn(gs, scout_cands, target)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
                self.last_attack_side = side
                return

        # SUSTAINED DEMO PRESSURE: spawn N demos any turn we have ≥ N×3 MP.
        # T4-onward: we have ~4 MP = 1 demo. Each turn fire as many demos as
        # affordable. Cap at 8 demos per wave.
        if my_mp >= demo_cost and t >= 3:
            spawn = self._best_spawn(gs, demo_cands, target)
            if spawn is not None:
                n = min(int(my_mp // demo_cost), 8)
                # Fire at least 1 demo if we can afford 1 AND it's T4+,
                # OR fire only big waves (>= 3 demos) if T < 4.
                if t >= 4 or n >= 3:
                    gs.attempt_spawn(DEMOLISHER, spawn, n)
                    leftover = my_mp - n * demo_cost
                    scout_count = int(leftover // scout_cost)
                    if scout_count > 0:
                        # Spend remainder on scouts at offset tile to draw fire.
                        sec = scout_cands[0] if scout_cands[0] != spawn else scout_cands[1]
                        if (gs.game_map.in_arena_bounds(sec)
                                and not gs.contains_stationary_unit(sec)):
                            gs.attempt_spawn(SCOUT, sec, scout_count)
                    self.last_attack_side = side

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
