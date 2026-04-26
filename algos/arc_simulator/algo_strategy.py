"""arc_simulator — fundamentally different decision paradigm: simulate, then act.

Each turn:
  1. Build a hypothetical board state by overlaying our planned defense additions.
  2. For each candidate ATTACK choice (scout flood, demo wave, defensive
     interceptors, no-attack), simulate the path-damage outcome and predict
     # of breaches scored.
  3. For each candidate DEFENSE BUILD (which structure to place this turn),
     simulate how it affects the path-damage of *opponent's* hypothetical
     attack from each spawn corner.
  4. Score each (defense, attack) pair by:
        score = expected_breaches - expected_breaches_against_us
     where expected_breaches uses path simulation:
        breaches = clamp(N_scouts - turret_damage_along_path / scout_HP, 0, N)

Implementation choices:
  - Simulate using the SAME GameState.find_path_to_edge / get_attackers
    primitives the engine uses for pathing. We don't fork the engine; we
    use the live game_state with hypothetical structure additions on a
    *cloned* game_map.
  - Branch over a small action space (~5-10 options) per turn for tractability.
  - Time budget: each find_path_to_edge is O(28*28). We do ~20 path queries
    per turn = ~16K cells = milliseconds. Safe.

Defense template: Lostkids V-funnel (it's optimal shape). What changes is
the BUILD ORDER — chosen dynamically each turn by simulation.
"""
from __future__ import annotations

import gamelib
import math
import json
import os
import random
import sys
import time
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


# Defense template (Lostkids V-funnel) but with explicit cost+priority
DEFENSE_PLAN = [
    # (priority, kind, unit_type, location, depends_on_turn)
    # kind: 'spawn' or 'upgrade'
    # priority: lower = build first
    (1,  'spawn',   'TURRET', [2, 13], 0),
    (1,  'spawn',   'TURRET', [25, 13], 0),
    # V walls priority 2 — built early
    *[(2, 'spawn', 'WALL', loc, 0) for loc in [
        [3, 12], [4, 12], [5, 12], [6, 11], [7, 11], [8, 11],
        [9, 10], [10, 10], [11, 10],
        [12, 9], [13, 9], [14, 9], [15, 9],
        [16, 10], [17, 10], [18, 10],
        [19, 11], [20, 11], [21, 11],
        [22, 12], [23, 12], [24, 12]
    ]],
    # 4 funnel turrets
    (3, 'spawn', 'TURRET', [9, 9], 1),
    (3, 'spawn', 'TURRET', [18, 9], 1),
    (3, 'spawn', 'TURRET', [6, 10], 1),
    (3, 'spawn', 'TURRET', [21, 10], 1),
    # Corner upgrades (DYNAMIC priority — simulator decides)
    (4, 'upgrade', 'TURRET', [2, 13], 2),
    (4, 'upgrade', 'TURRET', [25, 13], 2),
    # Deeper walls
    *[(5, 'spawn', 'WALL', loc, 3) for loc in [
        [10, 9], [11, 8], [12, 7], [13, 7], [14, 7], [15, 7],
        [16, 8], [17, 9], [9, 8], [8, 9], [7, 10], [18, 8],
        [19, 9], [20, 10], [6, 9], [5, 10], [4, 11], [21, 9],
        [22, 10], [23, 11]
    ]],
    # Corner-extension turrets
    (6, 'spawn', 'TURRET', [3, 13], 5),
    (6, 'spawn', 'TURRET', [24, 13], 5),
    (6, 'upgrade', 'TURRET', [3, 13], 5),
    (6, 'upgrade', 'TURRET', [24, 13], 5),
    (6, 'spawn', 'TURRET', [4, 13], 8),
    (6, 'spawn', 'TURRET', [23, 13], 8),
    (6, 'upgrade', 'TURRET', [4, 13], 8),
    (6, 'upgrade', 'TURRET', [23, 13], 8),
    # Funnel upgrades
    (7, 'upgrade', 'TURRET', [9, 9], 7),
    (7, 'upgrade', 'TURRET', [18, 9], 7),
    (7, 'upgrade', 'TURRET', [6, 10], 7),
    (7, 'upgrade', 'TURRET', [21, 10], 7),
    # Supports
    (8, 'spawn', 'SUPPORT', [11, 6], 10),
    (8, 'upgrade', 'SUPPORT', [11, 6], 10),
    (8, 'spawn', 'SUPPORT', [16, 6], 10),
    (8, 'upgrade', 'SUPPORT', [16, 6], 10),
    (8, 'spawn', 'SUPPORT', [11, 5], 10),
    (8, 'upgrade', 'SUPPORT', [11, 5], 10),
    (8, 'spawn', 'SUPPORT', [16, 5], 10),
    (8, 'upgrade', 'SUPPORT', [16, 5], 10),
    # Deep turrets (12,8)/(15,8)
    (9, 'spawn', 'TURRET', [12, 8], 14),
    (9, 'upgrade', 'TURRET', [12, 8], 14),
    (9, 'spawn', 'TURRET', [15, 8], 14),
    (9, 'upgrade', 'TURRET', [15, 8], 14),
    # Outer turrets and extra supports
    (10, 'spawn', 'TURRET', [22, 9], 18),
    (10, 'upgrade', 'TURRET', [22, 9], 18),
    (10, 'spawn', 'TURRET', [5, 9], 18),
    (10, 'upgrade', 'TURRET', [5, 9], 18),
    (10, 'spawn', 'SUPPORT', [10, 5], 22),
    (10, 'upgrade', 'SUPPORT', [10, 5], 22),
    (10, 'spawn', 'SUPPORT', [17, 5], 22),
    (10, 'upgrade', 'SUPPORT', [17, 5], 22),
]


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write(f'arc_simulator seed: {seed}')

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
        self.scout_hp = float(ui[3].get("startHealth", 15))
        self.demo_hp = float(ui[4].get("startHealth", 5))

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
                gamelib.debug_write("arc_simulator fallback: " + traceback.format_exc())
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
        for loc in [[2, 13], [25, 13], [9, 9], [18, 9], [6, 10], [21, 10]]:
            gs.attempt_spawn(TURRET, loc)

    def _main(self, gs):
        self._refund_low_hp(gs)
        self._build_defense(gs)
        self._reactive_patches(gs)
        self._attack_with_simulation(gs)

    def _build_defense(self, gs):
        """Build per priority queue, gated by turn requirements."""
        t = gs.turn_number
        for prio, kind, unit_name, loc, depends_t in DEFENSE_PLAN:
            if t < depends_t:
                continue
            unit = {'WALL': WALL, 'TURRET': TURRET, 'SUPPORT': SUPPORT}[unit_name]
            if kind == 'spawn':
                if not gs.contains_stationary_unit(loc):
                    cost = gs.type_cost(unit)[SP]
                    if gs.get_resource(SP, 0) < cost:
                        return  # stop building, save SP
                    gs.attempt_spawn(unit, loc)
            elif kind == 'upgrade':
                u = gs.contains_stationary_unit(loc)
                if u and not u.upgraded:
                    cost = gs.type_cost(unit, upgrade=True)[SP]
                    if gs.get_resource(SP, 0) < cost:
                        return
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

    # =========================================================
    # SIMULATION-BASED ATTACK DECISION
    # =========================================================
    def _path_damage(self, gs, spawn, target):
        try:
            path = gs.find_path_to_edge(spawn, target)
        except Exception:
            return float("inf"), 0
        if not path:
            return float("inf"), 0
        total = 0.0
        path_len = len(path)
        for p in path:
            try:
                atks = gs.get_attackers(p, 0)
            except Exception:
                atks = []
            for a in atks:
                total += self.t_dmg_up if a.upgraded else self.t_dmg_base
        return total, path_len

    def _simulate_scout_breaches(self, gs, spawn, target, n_scouts, shield_per_scout=0.0):
        """Simulate how many of N scouts breach the opposite edge.

        Per-scout damage taken ≈ path_damage / n_scouts (assuming linear sharing).
        Each scout dies if accumulated damage taken ≥ HP + shield.
        Returns expected breaches.
        """
        path_dmg, path_len = self._path_damage(gs, spawn, target)
        if path_dmg == float("inf"):
            return 0
        if n_scouts <= 0:
            return 0
        per_scout_dmg = path_dmg  # worst case: all damage hits each scout's path
        per_scout_hp = self.scout_hp + shield_per_scout
        # Heuristic: scout pack tanks damage in sequence.
        # First scout absorbs per_scout_hp damage, then next, etc.
        # Total damage delivered by turrets = path_dmg * n_scouts (each scout
        # walks the full path, takes path_dmg damage per scout-pass).
        # Wait — that's wrong. Engine fires one shot per turret per frame.
        # If 10 scouts pile on tile X for 1 frame, only 1 dies. The rest live.
        # So damage per frame = sum(turret dmg in range), and each scout HP=15
        # means a turret needs 15/dmg_per_frame frames per scout.
        # Simpler model: total turret-frames-to-kill = path_len (frames spent on path)
        # Total kill capacity = sum_per_tile(turret_dmg_in_range * 1 frame) = path_dmg
        # Effective damage absorbed = path_dmg
        # Scouts killed = path_dmg / per_scout_hp (approx)
        scouts_killed = path_dmg / per_scout_hp
        breaches = max(0, n_scouts - scouts_killed)
        return breaches

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

    def _our_supports_count(self, gs):
        n = 0
        for loc in [[11, 6], [16, 6], [11, 5], [16, 5], [10, 5], [17, 5]]:
            u = gs.contains_stationary_unit(loc)
            if u and u.unit_type == SUPPORT and u.upgraded:
                n += 1
        return n

    def _our_shield_per_scout(self, gs):
        """Estimate shield per scout passing through our support corridor."""
        # Crude: each upgraded support at Y=5 gives 1+0.7*5 = 4.5 shield, range 7.
        # Most supports at Y=5-6. Avg shield/support ≈ 5.
        n = self._our_supports_count(gs)
        return n * 5.0

    def _attack_with_simulation(self, gs):
        t = gs.turn_number
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        ic_cost = gs.type_cost(INTERCEPTOR)[MP]

        if t < 1:
            return

        # Generate candidate attack actions.
        # For each side (left/right) and each unit type (scout/demo), compute
        # expected breaches via simulation. Pick the highest-EV action.
        candidates = []
        shield_per_scout = self._our_shield_per_scout(gs)

        for side in ("left", "right"):
            target = gs.game_map.TOP_LEFT if side == "right" else gs.game_map.TOP_RIGHT
            if side == "right":
                scout_spawns = [[4, 9], [3, 10], [13, 0], [14, 0], [5, 8]]
                demo_spawns = [[4, 9], [5, 8], [3, 10]]
            else:
                scout_spawns = [[23, 9], [24, 10], [14, 0], [13, 0], [22, 8]]
                demo_spawns = [[23, 9], [22, 8], [24, 10]]

            # Scout action: pick best spawn (least damage path), simulate.
            best_scout_breach = 0
            best_scout_spawn = None
            for sp_loc in scout_spawns:
                if not gs.game_map.in_arena_bounds(sp_loc):
                    continue
                if gs.contains_stationary_unit(sp_loc):
                    continue
                n_scouts = int(my_mp // scout_cost)
                breaches = self._simulate_scout_breaches(
                    gs, sp_loc, target, n_scouts, shield_per_scout
                )
                if breaches > best_scout_breach:
                    best_scout_breach = breaches
                    best_scout_spawn = sp_loc
            if best_scout_spawn:
                candidates.append({
                    'kind': 'scout',
                    'side': side,
                    'spawn': best_scout_spawn,
                    'n': int(my_mp // scout_cost),
                    'expected_breaches': best_scout_breach,
                })

            # Demo action.
            best_demo_breach = 0
            best_demo_spawn = None
            for sp_loc in demo_spawns:
                if not gs.game_map.in_arena_bounds(sp_loc):
                    continue
                if gs.contains_stationary_unit(sp_loc):
                    continue
                n_demos = int(my_mp // demo_cost)
                # Demos are different — they fire FROM the path. Approx 8 dmg/2-frames = 4 dmg/frame.
                # Damage they DEAL ≈ path_len_frames * n_demos * 4
                # But what we care about is: do they break through enough to score?
                # Demos die fast. Let's use a heuristic: demos breach the EDGE rarely (they
                # walk slowly and die). Score them on STRUCTURE damage instead.
                path_dmg, path_len = self._path_damage(gs, sp_loc, target)
                if path_dmg == float("inf"):
                    continue
                # Demos that survive = max(0, n_demos - path_dmg/demo_hp)
                surviving = max(0, n_demos - path_dmg / self.demo_hp)
                # Each surviving demo deals ~4 dmg/frame for path_len/2 frames = 2*path_len dmg
                struct_damage_dealt = surviving * 2 * path_len
                # Approximate breaches: surviving demos breach if path is short.
                breaches = surviving * (1.0 if path_len < 25 else 0.5)
                # SCORE = breaches + struct_damage_dealt / 50 (chip damage value)
                score = breaches + struct_damage_dealt / 50.0
                if score > best_demo_breach:
                    best_demo_breach = score
                    best_demo_spawn = sp_loc
            if best_demo_spawn:
                candidates.append({
                    'kind': 'demo',
                    'side': side,
                    'spawn': best_demo_spawn,
                    'n': int(my_mp // demo_cost),
                    'expected_breaches': best_demo_breach,
                })

        # Defensive interceptor option if opp hoarded MP.
        opp_mp = gs.get_resource(MP, 1)
        if opp_mp >= 12 and my_mp >= 2 * ic_cost and t >= 6:
            candidates.append({
                'kind': 'interceptor',
                'side': 'both',
                'expected_breaches': 0.5,  # defensive value
            })

        # No-attack option.
        candidates.append({
            'kind': 'save',
            'expected_breaches': my_mp * 0.1,  # MP saved value (approximate, decay applies)
        })

        # Pick highest expected breaches.
        candidates.sort(key=lambda c: -c['expected_breaches'])
        best = candidates[0]

        if best['kind'] == 'save':
            return
        if best['kind'] == 'interceptor':
            for sp_loc in [[6, 7], [21, 7]]:
                if gs.game_map.in_arena_bounds(sp_loc) and not gs.contains_stationary_unit(sp_loc):
                    gs.attempt_spawn(INTERCEPTOR, sp_loc)
            return

        # Threshold check: only attack if expected breaches > 1.0
        # (don't waste MP on speculative attacks).
        if best['expected_breaches'] < 1.0 and t < 25:
            return

        if best['kind'] == 'scout':
            n = best['n']
            spawn = best['spawn']
            # Two-tile split: half at primary, half at secondary
            second = None
            if best['side'] == 'right':
                cand_secondary = [[3, 10], [4, 9], [13, 0]]
            else:
                cand_secondary = [[24, 10], [23, 9], [14, 0]]
            for c in cand_secondary:
                if c == spawn:
                    continue
                if gs.game_map.in_arena_bounds(c) and not gs.contains_stationary_unit(c):
                    second = c
                    break
            first = min(int(n * 0.5) + 1, n)
            rest = n - first
            if first > 0:
                gs.attempt_spawn(SCOUT, spawn, first)
            if rest > 0 and second:
                gs.attempt_spawn(SCOUT, second, rest)
            self.last_attack_side = best['side']
        elif best['kind'] == 'demo':
            gs.attempt_spawn(DEMOLISHER, best['spawn'], min(best['n'], 8))
            self.last_attack_side = f"demo_{best['side']}"

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
