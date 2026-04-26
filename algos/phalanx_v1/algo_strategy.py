"""phalanx_v1 — Gen-1 candidate. Demolisher-line specialist.

Bet: in Citadel, the +25% upgraded turret damage (20 vs 16 base game)
makes scouts much harder to push through; demolishers, by contrast, sit
at range 4.5 from y=11 and chew structures while staying out of base
2.5-range turret coverage. Sustained demolisher pressure compounds:
every wall they kill widens the path for the next wave.

Architecture:
  * Wall ROW at y=11 spanning [3..24, 11] — forces opponent mobiles through
    an inner path while letting OUR demos park at y=10 firing into y≥11
    enemy structures from range 4.5.
  * Sparse turret coverage at corners and centre at y=12 (above the wall),
    so they fire INTO the gap.
  * Spawn demos at [4,9] or [23,9] every turn we have ≥3 MP. Sustained
    chip damage > occasional flood.
  * Backup: when enemy front is gutted (<6 stationary structures in their
    front), pivot to scout flood.
  * Edge toggle preserved for misdirection.
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


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("watchdog")


def _arm_watchdog(seconds):
    if hasattr(signal, "SIGALRM"):
        try:
            old = signal.signal(signal.SIGALRM, _sigalrm_handler)
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
        self.enemy_recent_demos = 0
        self.last_attack_side = None

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
                gamelib.debug_write("phalanx_v1 fallback: " + traceback.format_exc())
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
        for loc in [[2, 13], [25, 13], [11, 11], [16, 11], [13, 11], [14, 11]]:
            gs.attempt_spawn(TURRET, loc)

    def _main(self, gs):
        self._refund_low_hp(gs)
        self._build_phalanx(gs)
        self._reactive_patches(gs)
        self._attack(gs)

    def _build_phalanx(self, gs):
        t = gs.turn_number

        # Phase 1: y=11 wall row spanning the front (excluding spawn-tile gaps).
        # Gaps at x=4 and x=23 so we can spawn demos at [4,9] / [23,9] and
        # they walk forward without obstruction.
        wall_row = [[x, 11] for x in range(3, 25) if x not in (4, 23, 13, 14)]
        gs.attempt_spawn(WALL, wall_row)

        # Phase 2: turrets at y=12 above the wall row, plus corner anchors.
        front_turrets = [[5, 12], [9, 12], [13, 12], [14, 12], [18, 12], [22, 12]]
        gs.attempt_spawn(TURRET, front_turrets)
        corners = [[2, 13], [25, 13]]
        gs.attempt_spawn(TURRET, corners)

        # Phase 3: y=12 wall coverage at corners (T1+) — demolishers don't
        # care, but enemy scouts get filtered into our turrets.
        if t >= 1:
            cover = [[3, 12], [4, 12], [23, 12], [24, 12]]
            gs.attempt_spawn(WALL, cover)

        # Phase 4: upgrade central turrets first (T2+).
        if t >= 2:
            for loc in [[13, 12], [14, 12]]:
                gs.attempt_upgrade(loc)
        if t >= 4:
            for loc in [[9, 12], [18, 12]]:
                gs.attempt_upgrade(loc)
        if t >= 6:
            for loc in [[5, 12], [22, 12]]:
                gs.attempt_upgrade(loc)
        if t >= 8:
            for loc in corners:
                gs.attempt_upgrade(loc)

        # Phase 5: side-corner extra turrets if enemy front gutted (T10+).
        if t >= 10:
            for loc in [[1, 13], [26, 13]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

        # Phase 6: deep-side turrets covering low-y breach hot-spots (T12+).
        if t >= 12:
            for loc in [[7, 9], [20, 9]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

        # Phase 7: late-game wall upgrades on key segments.
        if t >= 16:
            for loc in [[3, 12], [24, 12]]:
                gs.attempt_upgrade(loc)
            # Promote wall row to upgraded for the spans most often hit.
            for x in [5, 9, 13, 14, 18, 22]:
                gs.attempt_upgrade([x, 11])

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
    # ATTACK — sustained demolisher pressure
    # ============================================================
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

        if t < 1:
            return

        side = self._pick_side(gs)
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT

        if side == "right":
            demo_cands = [[4, 9], [5, 8], [6, 7], [13, 0]]
            scout_cands = [[3, 10], [4, 9], [13, 0], [14, 0]]
        else:
            demo_cands = [[23, 9], [22, 8], [21, 7], [14, 0]]
            scout_cands = [[24, 10], [23, 9], [14, 0], [13, 0]]

        # Pivot to scout flood if enemy front is broken (<6 score).
        front_score = self._enemy_front_count(gs)
        if front_score < 6 and my_mp >= 8 * scout_cost:
            spawn = self._best_spawn(gs, scout_cands, target)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, int(my_mp // scout_cost))
                self.last_attack_side = side
                return

        # Sustained demo chip: send 1+ demos any turn we have >=1 demo's worth.
        if my_mp >= demo_cost:
            spawn = self._best_spawn(gs, demo_cands, target)
            if spawn is not None:
                # Don't fire single demos before T4 — too inefficient.
                if t >= 4 or my_mp >= 4 * demo_cost:
                    n = max(1, int(my_mp // demo_cost))
                    n = min(n, 8)  # cap
                    gs.attempt_spawn(DEMOLISHER, spawn, n)
                    self.last_attack_side = side
                    # Spend leftover MP on a few scouts to bait turret fire
                    # away from demos.
                    leftover = my_mp - n * demo_cost
                    if leftover >= scout_cost:
                        scout_spawn = self._best_spawn(gs, scout_cands, target)
                        if scout_spawn is not None and scout_spawn != spawn:
                            gs.attempt_spawn(SCOUT, scout_spawn, int(leftover // scout_cost))
                    return

        # Otherwise, save MP (decay 25% is acceptable when waiting for demo
        # threshold).

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
        e_demos = 0
        for s in events.get("spawn", []):
            if len(s) < 4:
                continue
            if s[1] == 4 and s[3] == 2:
                e_demos += 1
        if e_demos:
            self.enemy_recent_demos = int(self.enemy_recent_demos * 0.7) + e_demos


if __name__ == "__main__":
    AlgoStrategy().start()
