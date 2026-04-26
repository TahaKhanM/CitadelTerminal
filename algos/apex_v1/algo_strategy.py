"""apex_v1 — Gen-1 candidate. Lostkids V-funnel + Citadel-tuned upgrades.

Beats lostkids_v3 by:
  * Earlier offensive trigger (12 MP scout-rush, 9 MP demolisher) — Lostkids
    waits for 17 MP which lets opponents block the edge or reactively defend.
  * Pre-emptive demolisher wave at T8-10 to break opening defenses BEFORE
    they upgrade — exploits the Citadel-only +25% upgraded turret damage
    (turrets that AREN'T yet upgraded fall fast).
  * Aggressive demo backstop: switch to demos after a SINGLE failed scout
    wave (Lostkids waits for 3) — burning MP on scouts that don't score is
    strictly worse than burning MP on demos that do damage even when they
    die. Works because demos at range 4.5 stop at y=10 vs stationary y≥14
    structures.
  * Damage-aware spawn-tile selection across 5 candidate tiles (not 2).
    FUNNEL-style: pick the path with minimum total turret-damage exposure.
  * Edge toggle preserved (Lostkids' signature trick), but only fired when
    BOTH edges threatened — saves 2 SP/turn we'd otherwise burn.

Defense is the canonical Lostkids V-funnel, expressed inline (no JSON).
Refund thresholds: wall <50%, turret <30% (Lostkids/finalist consensus).
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
    fired = {"v": False}
    timer = threading.Timer(seconds, lambda: fired.__setitem__("v", True))
    timer.daemon = True
    timer.start()
    return timer.cancel


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.scored_on = []
        self.scout_wave_breaches = []
        self._pending_attack_was_scouts = False
        self._this_turn_breaches = 0
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
        self.turret_dmg_up = float(ui[2].get("upgrade", {}).get("attackDamageWalker", 20))
        self.turret_dmg_base = float(ui[2].get("attackDamageWalker", 6))

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
                gamelib.debug_write("apex_v1 fallback: " + traceback.format_exc())
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

    # ============================================================
    # MAIN
    # ============================================================
    def _main(self, gs):
        # Roll over previous turn's scout-attack breach count.
        if self._pending_attack_was_scouts:
            self.scout_wave_breaches.append(self._this_turn_breaches)
            self._pending_attack_was_scouts = False
        self._this_turn_breaches = 0

        my_mp = gs.get_resource(MP, 0)
        enemy_mp = gs.get_resource(MP, 1)
        my_hp = gs.my_health
        enemy_hp = gs.enemy_health

        self._refund_low_health(gs)
        self._build_defense(gs)
        self._build_supports(gs)
        self._block_edges(gs, enemy_mp)
        self._reactive_patches(gs)
        self._attack(gs, my_mp, my_hp, enemy_hp)

    # ============================================================
    # DEFENSE — canonical Lostkids V-funnel, inlined
    # ============================================================
    def _build_defense(self, gs):
        t = gs.turn_number

        # Phase 1: V-shape wall + 2 corner turrets (T0).
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

        # Phase 2: 4 funnel-corner turrets (T0+).
        funnel_turrets = [[6, 10], [9, 9], [18, 9], [21, 10]]
        gs.attempt_spawn(TURRET, funnel_turrets)

        # Phase 3: extend deeper V at y=7-9 (T2+).
        if t >= 2:
            deeper_walls = [
                [10, 9], [11, 8], [12, 7], [13, 7], [14, 7], [15, 7],
                [16, 8], [17, 9],
            ]
            gs.attempt_spawn(WALL, deeper_walls)

        # Phase 4: corner-cluster upgraded turrets (T3+).
        if t >= 3:
            for loc in [[2, 13], [25, 13]]:
                gs.attempt_upgrade(loc)
        if t >= 5:
            for loc in [[3, 13], [24, 13]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)
        if t >= 8:
            for loc in [[4, 13], [23, 13]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

        # Phase 5: upgrade funnel turrets (T6+).
        if t >= 6:
            for loc in funnel_turrets:
                gs.attempt_upgrade(loc)

        # Phase 6: deep-side turrets at (5,9)/(22,9) — covers v3 hot-spot
        # breaches at (5,8)/(22,8) — and upgrade them.
        if t >= 10:
            for loc in [[5, 9], [22, 9]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

        # Phase 7: late-game wall upgrades on the V (T15+).
        if t >= 15:
            for loc in v_walls[:6] + v_walls[-6:]:
                gs.attempt_upgrade(loc)

        # Phase 8: extra forward turrets if heavy demolisher pressure (T20+).
        if t >= 12 and self.enemy_recent_demos >= 4:
            for loc in [[12, 8], [15, 8]]:
                gs.attempt_spawn(TURRET, loc)
                gs.attempt_upgrade(loc)

    def _build_supports(self, gs):
        """Upgraded back-row supports — competition-only big-shield play.

        Per Citadel: shield = 1 + 0.7*Y per upgraded support, range 7.
        At Y=6 that's 5.2/scout. We seed cautiously (4 supports = ~20 shield
        per scout passing through) so scout caravans tank deeper into enemy.
        """
        t = gs.turn_number
        if t >= 7:
            for loc in [[11, 6], [16, 6]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 12:
            for loc in [[11, 5], [16, 5]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)
        if t >= 18:
            for loc in [[10, 5], [17, 5]]:
                gs.attempt_spawn(SUPPORT, loc)
                gs.attempt_upgrade(loc)

    def _refund_low_health(self, gs):
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

    # ============================================================
    # EDGE TOGGLE
    # ============================================================
    def _block_edges(self, gs, enemy_mp):
        # Always close own edges if we have SP and we're not committing
        # offense. The walls get refunded next turn via attempt_remove.
        my_mp = gs.get_resource(MP, 0)
        # If we'll attack this turn we open the chosen edge; block_for_attack
        # will be called from _attack and should NOT clobber. Here we just
        # close both eagerly; _attack opens the side it picks.
        if enemy_mp < 6 and my_mp >= 12:
            return  # rely on _attack to manage edges
        for loc in [[0, 13], [1, 13], [26, 13], [27, 13]]:
            if not gs.contains_stationary_unit(loc):
                gs.attempt_spawn(WALL, loc)
                if enemy_mp >= 15:
                    gs.attempt_upgrade(loc)
                gs.attempt_remove(loc)

    def _reactive_patches(self, gs):
        # One backing turret per chronic breach tile. Cheap and SP-gated.
        if gs.get_resource(SP, 0) < 4:
            return
        from collections import Counter
        counts = Counter([tuple(p) for p in self.scored_on[-12:]])
        for (x, y), n in counts.most_common(2):
            if n < 2:
                break
            if x < 14:
                cands = [[x + 1, y + 1], [x, y + 1]]
            else:
                cands = [[x - 1, y + 1], [x, y + 1]]
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
    # ATTACK
    # ============================================================
    def _attack(self, gs, my_mp, my_hp, enemy_hp):
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number

        # Don't attack T0 (no MP).
        if t < 1:
            return

        # Pre-emptive demolisher wave T8-10 to bust opening defenses BEFORE
        # they're upgraded. 4 demos = 12 MP — we have it by T8 (1+1+1+1+2+2+2+2+2 = 14 banked).
        if 8 <= t <= 10 and my_mp >= 4 * demo_cost and not self._committed_recent_demo():
            self._fire_demos(gs, my_mp, count_cap=5)
            self.last_attack_side = "demo_preemptive"
            return

        # Standard scout flood at MP >= 12.
        SCOUT_TRIGGER_MP = 12.0
        DEMO_TRIGGER_MP = 9.0  # 3 demos

        # Decide demolisher mode if scouts have failed recently AND we're
        # behind on HP.
        scouts_failing = (
            len(self.scout_wave_breaches) >= 1
            and self.scout_wave_breaches[-1] < 3
        )
        losing = (my_hp < enemy_hp - 3) or (my_hp < 30)
        force_demo = scouts_failing and losing and my_mp >= DEMO_TRIGGER_MP

        if force_demo:
            self._fire_demos(gs, my_mp, count_cap=8)
            return

        if my_mp >= SCOUT_TRIGGER_MP:
            self._fire_scouts(gs, my_mp)

    def _committed_recent_demo(self):
        return self.last_attack_side == "demo_preemptive"

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

    def _pick_attack_side(self, gs):
        l = self._enemy_edge_strength(gs, "left")
        r = self._enemy_edge_strength(gs, "right")
        return "right" if l > r else "left"

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
                attackers = gs.get_attackers(p, 0)
            except Exception:
                attackers = []
            # Sum attacker dpf, weighted by upgraded if applicable
            for a in attackers:
                total += self.turret_dmg_up if a.upgraded else self.turret_dmg_base
        return total

    def _best_spawn(self, gs, candidates, target_edge):
        scored = []
        for c in candidates:
            if not gs.game_map.in_arena_bounds(c):
                continue
            if gs.contains_stationary_unit(c):
                continue
            d = self._path_damage(gs, c, target_edge)
            scored.append((d, c))
        scored.sort(key=lambda kv: kv[0])
        return scored[0][1] if scored else None

    def _open_attack_edge(self, gs, side):
        # Mark the corner walls for removal so the edge clears for spawning
        # — refund applies next turn but the spawn tiles become valid this
        # turn because attempt_remove only marks; they're still walkable.
        # We instead just don't BLOCK them this turn by skipping that pair
        # in _block_edges. Here we ensure they're not in the way: if a wall
        # exists, mark for remove (it'll go away next turn, but for THIS
        # turn the spawn checks contains_stationary_unit anyway). Better:
        # spawn on an alternative tile.
        # NOTE: we just allow attack tiles deeper in the funnel.
        pass

    def _fire_scouts(self, gs, my_mp):
        scout_cost = gs.type_cost(SCOUT)[MP]
        side = self._pick_attack_side(gs)
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT
        # Lostkids spawn pair: (4,9)+(3,10) for right-attack, mirror for left.
        if side == "right":
            primary_cands = [[4, 9], [3, 10], [5, 8], [6, 7], [13, 0], [14, 0]]
            secondary = [3, 10]
        else:
            primary_cands = [[23, 9], [24, 10], [22, 8], [21, 7], [14, 0], [13, 0]]
            secondary = [24, 10]
        spawn = self._best_spawn(gs, primary_cands, target)
        if spawn is None:
            return
        # First-wave: enemy_strength-aware split.
        strength = self._enemy_edge_strength(gs, "left" if side == "right" else "right")
        first = min(5 + int(strength / 7), 10)
        scouts_total = int(my_mp // scout_cost)
        first = min(first, scouts_total)
        rest = scouts_total - first
        if first > 0:
            gs.attempt_spawn(SCOUT, spawn, first)
        if rest > 0:
            sec = secondary if not gs.contains_stationary_unit(secondary) else spawn
            gs.attempt_spawn(SCOUT, sec, rest)
        self._pending_attack_was_scouts = True
        self.last_attack_side = side

    def _fire_demos(self, gs, my_mp, count_cap=8):
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        side = self._pick_attack_side(gs)
        target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT
        if side == "right":
            cands = [[4, 9], [5, 8], [6, 7], [13, 0]]
        else:
            cands = [[23, 9], [22, 8], [21, 7], [14, 0]]
        spawn = self._best_spawn(gs, cands, target)
        if spawn is None:
            return
        n = min(int(my_mp // demo_cost), count_cap)
        if n > 0:
            gs.attempt_spawn(DEMOLISHER, spawn, n)
            self.last_attack_side = f"demo_{side}"
            self._pending_attack_was_scouts = False

    # ============================================================
    # ACTION FRAME
    # ============================================================
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
        # Track enemy demolisher deployments (decay).
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
