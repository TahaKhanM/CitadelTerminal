"""Oracle v2 — heuristic-defense + search-based offense.

Architecture:
  Defense pipeline (PROVEN — beats v13 10/10): direct port of heuristic_v1's
    _build_defense / _build_supports / _spend_hoard / _reactive_defense
    / _pressure_response. These are based on aggregate replay data
    (top breach hotspots: (4,9), (23,9), (20,6), (8,5), etc.) and
    the Citadel-specific Support shielding insight (4 upgraded
    supports = +40 shield/scout).

  Offense (Oracle's edge): Oracle's search-based offense via the
    sim_rs evaluator. We enumerate offensive ActionPlans, simulate
    each against K opp samples, pick the best by expected utility.
    Falls back to heuristic_v1's adaptive scout/demo if search fails.

Safety:
  - 13s SIGALRM watchdog (never time-out the deploy phase)
  - Top-level try/except → safe fallback (heuristic_v1's offense path)
"""
from __future__ import annotations

import json
import os
import random
import signal
import sys
import threading
import time
import traceback
from sys import maxsize
from typing import Optional, List

import gamelib

# Make local subpackage importable
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

# Oracle search components (offense only)
from oracle_core.search import select_best_plan, ActionPlan
from oracle_core.enumerator import (
    apply_to_game_state, _enumerate_offense_options,
    _OFFENSE_LAUNCHERS,
)
from oracle_core.opponent_model import OpponentModel


# Globals (initialized at game start from config — Citadel uses pre-rename codes)
WALL = "FF"
SUPPORT = "EF"
TURRET = "DF"
SCOUT = "PI"
DEMOLISHER = "EI"
INTERCEPTOR = "SI"
MP = 1
SP = 0


# --- Safety wrappers --------------------------------------------------------
TURN_WATCHDOG_SECONDS = 13
SEARCH_TIME_BUDGET_S = 6.0  # 6s for offense search; defense is heuristic (~50ms)


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds: int):
    if hasattr(signal, "SIGALRM"):
        try:
            old_handler = signal.signal(signal.SIGALRM, _sigalrm_handler)
            signal.alarm(seconds)
            def disarm():
                signal.alarm(0)
                signal.signal(signal.SIGALRM, old_handler)
            return disarm, lambda: False
        except (ValueError, OSError):
            pass
    fired = {"v": False}
    def fire():
        fired["v"] = True
    timer = threading.Timer(seconds, fire)
    timer.daemon = True
    timer.start()
    def disarm():
        timer.cancel()
    return disarm, lambda: fired["v"]


# ---------------------------------------------------------------------------
# Algo
# ---------------------------------------------------------------------------

class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        try:
            gamelib.debug_write(f"[oracle] seed={seed}")
        except Exception:
            pass

        self.config = None
        self.opp_model: Optional[OpponentModel] = None

        # Heuristic-v1 trackers
        self.scored_on: list = []
        self.opp_spawn_left = 0
        self.opp_spawn_right = 0
        self.opp_demo_count = 0
        self.opp_scout_count = 0
        self.path_cache: dict = {}

        self._turn_count = 0
        self._fallback_count = 0

    def on_game_start(self, config):
        self.config = config
        global WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR
        try:
            ui = config["unitInformation"]
            WALL = ui[0]["shorthand"]
            SUPPORT = ui[1]["shorthand"]
            TURRET = ui[2]["shorthand"]
            SCOUT = ui[3]["shorthand"]
            DEMOLISHER = ui[4]["shorthand"]
            INTERCEPTOR = ui[5]["shorthand"]
        except Exception as e:
            gamelib.debug_write(f"[oracle] shorthand resolve failed: {e!r}")

        # Eager-load sim_rs to warm import path (avoid first-turn cost)
        try:
            from oracle_core.sim_eval import _get_sim_rs, _SIM_RS_LOAD_PATH
            sim = _get_sim_rs()
            from oracle_core.sim_eval import _SIM_RS_LOAD_PATH as p
            gamelib.debug_write(
                f"[oracle] sim_rs: loaded={'YES' if sim else 'NO'} path={p}"
            )
        except Exception as e:
            gamelib.debug_write(f"[oracle] sim_rs probe failed: {e!r}")

        # Opp model (loads from cache fast)
        try:
            self.opp_model = OpponentModel()
            stats = getattr(self.opp_model, "stats", lambda: {})()
            gamelib.debug_write(f"[oracle] opp_model: {stats}")
        except Exception as e:
            gamelib.debug_write(f"[oracle] opp_model init failed: {e!r}")
            self.opp_model = None

        gamelib.debug_write("[oracle] on_game_start complete")

    def on_turn(self, turn_state):
        self._turn_count += 1
        t0 = time.time()
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        gs = None
        used_fallback = False

        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            self.path_cache.clear()

            # PHASE 1 — DEFENSE (heuristic_v1's proven pipeline)
            self._build_defense(gs)
            self._build_supports(gs)
            self._spend_hoard(gs)
            self._reactive_defense(gs)
            self._pressure_response(gs)

            # PHASE 2 — OFFENSE
            # v2: Always run heuristic offense as baseline (proven 10/10 vs v13).
            # Search can add SUPPLEMENTAL offense if MP remains, but does NOT
            # replace heuristic. This guarantees we never weaken vs heuristic_v1.
            self._heuristic_offense(gs)
            # If MP still > 1 after heuristic, supplement with search-found plan
            try:
                remaining_mp = float(gs.get_resource(MP, 0))
            except Exception:
                remaining_mp = 0.0
            if remaining_mp >= 1.0:
                self._search_offense(gs)

            elapsed = time.time() - t0
            gamelib.debug_write(
                f"[oracle] turn={gs.turn_number} elapsed={elapsed:.2f}s "
                f"sp={gs.get_resource(SP, 0):.1f} mp={gs.get_resource(MP, 0):.1f}"
            )

            gs.submit_turn()

        except _TurnTimeout:
            gamelib.debug_write(f"[oracle] WATCHDOG fired @ {TURN_WATCHDOG_SECONDS}s")
            used_fallback = True
        except Exception as e:
            gamelib.debug_write(
                f"[oracle] on_turn exception: {e!r}\n{traceback.format_exc()}"
            )
            used_fallback = True
        finally:
            disarm()

        if used_fallback:
            self._fallback_count += 1
            gamelib.debug_write(
                f"[oracle] using fallback (count={self._fallback_count}/{self._turn_count})"
            )
            try:
                if gs is None:
                    gs = gamelib.GameState(self.config, turn_state)
                    gs.suppress_warnings(True)
                self._build_defense(gs)
                self._heuristic_offense(gs)
                gs.submit_turn()
            except Exception as e2:
                gamelib.debug_write(f"[oracle] fallback failed: {e2!r}")
                try:
                    if gs is not None:
                        gs.submit_turn()
                except Exception:
                    pass

    # ======================================================================
    # DEFENSE — direct port of heuristic_v1 (proven 10/10 vs v13)
    # ======================================================================

    def _build_defense(self, gs):
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]
        diag_reinforce = [[4, 11], [23, 11]]

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
        if t >= 4:
            gs.attempt_spawn(TURRET, diag_reinforce)
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 6:
            gs.attempt_upgrade(diag_reinforce)
        if t >= 7:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        # Oracle v3: DIAGONAL-EXIT WALLS at the END (after upgrades).
        # Ladder analysis showed top breach tiles: (8,5), (10,3), (12,1)
        # on BL diagonal and (20,6), (23,9), (24,10) on BR. These get
        # AT MOST leftover SP each turn — never compete with the proven
        # turret/wall pipeline above. Keeps base defense intact, adds
        # blockers when budget allows.
        # IMPORTANT: NOT placing on (1,12), (26,12), (0,13), (27,13)
        # because those are scout-pathing routes (we MIGHT spawn from
        # outer corners) AND because heuristic_v1's offense uses them.
        bl_diag_blockers = [[8, 5], [10, 3], [12, 1]]
        br_diag_blockers = [[20, 6], [23, 9], [24, 10]]
        if t >= 2:  # only after base defense has settled
            gs.attempt_spawn(WALL, bl_diag_blockers)
            gs.attempt_spawn(WALL, br_diag_blockers)
        if t >= 6:  # upgrade diag walls so demos take 3 shots not 1
            gs.attempt_upgrade(bl_diag_blockers)
            gs.attempt_upgrade(br_diag_blockers)

        # Auto-reupgrade damaged turrets
        for loc in (center_turrets + sidelane_deep + outer_turrets +
                    inner_corners + outer_corners + diag_reinforce):
            for u in gs.game_map[loc[0], loc[1]]:
                if (u.unit_type == TURRET and not u.upgraded
                        and u.health < 0.5 * u.max_health):
                    gs.attempt_upgrade(loc)
                    break

    def _build_supports(self, gs):
        t = gs.turn_number
        if t < 7:
            return
        support_locs = [[12, 11], [15, 11], [13, 10], [14, 10]]
        for loc in support_locs:
            if not gs.contains_stationary_unit(loc):
                if gs.attempt_spawn(SUPPORT, loc) > 0:
                    gs.attempt_upgrade(loc)
            else:
                for u in gs.game_map[loc[0], loc[1]]:
                    if u.unit_type == SUPPORT and not u.upgraded:
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

        attacked_left = sum(1 for (x, y) in self.scored_on if x < 14)
        attacked_right = len(self.scored_on) - attacked_left

        if t >= 12:
            if attacked_left >= attacked_right:
                gs.attempt_spawn(TURRET, [[6, 10], [23, 10]])
            else:
                gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])

        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

        if t >= 25:
            gs.attempt_spawn(TURRET, [[3, 11], [24, 11]])
            gs.attempt_upgrade([[3, 11], [24, 11]])

    def _reactive_defense(self, gs):
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            if x < 14:
                cands = [[x + 1, y + 1], [x, y + 1], [x + 1, y], [x, y]]
            else:
                cands = [[x - 1, y + 1], [x, y + 1], [x - 1, y], [x, y]]
            for build in cands:
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
        try:
            enemy_mp = gs.get_resource(MP, 1)
            my_mp = gs.get_resource(MP)
            scout_cost = gs.type_cost(SCOUT)[MP]
            int_cost = gs.type_cost(INTERCEPTOR)[MP]
        except Exception:
            return

        if enemy_mp >= 9 * scout_cost and my_mp >= int_cost:
            total = self.opp_spawn_left + self.opp_spawn_right
            if total < 3:
                spawn = [13, 0]
            elif self.opp_spawn_left > self.opp_spawn_right:
                spawn = [6, 7]
            else:
                spawn = [21, 7]
            try:
                if (gs.game_map.in_arena_bounds(spawn)
                        and not gs.contains_stationary_unit(spawn)):
                    gs.attempt_spawn(INTERCEPTOR, spawn)
            except Exception:
                pass

    # ======================================================================
    # OFFENSE — Oracle search; falls back to heuristic_v1
    # ======================================================================

    def _search_offense(self, gs) -> int:
        """Run search over offensive plans; apply best.

        Returns count of mobile units spawned. 0 if search produced no plan.
        """
        # Skip search on turn 0 (no MP to attack with anyway)
        try:
            mp = float(gs.get_resource(MP, 0))
        except Exception:
            return 0
        if mp < 1.0:
            return 0

        opp_model = self.opp_model
        if opp_model is None:
            class _StubOpp:
                def sample_actions(self, g, k=8):
                    return []
            opp_model = _StubOpp()

        try:
            # Use a smaller candidate pool for offense-only search
            plan = select_best_plan(
                gs, opp_model,
                time_budget_s=SEARCH_TIME_BUDGET_S,
                max_candidates=120,
                k_opp_samples=4,
                depth=1,
                debug_log=gamelib.debug_write,
            )
        except Exception as e:
            gamelib.debug_write(f"[oracle] search exception: {e!r}")
            return 0

        if plan is None:
            return 0

        # Apply ONLY the mobile_actions (defense already done by heuristic).
        spawned = 0
        for unit, loc, count in (plan.mobile_actions or []):
            try:
                n = gs.attempt_spawn(unit, list(loc), count)
                spawned += n if n else 0
            except Exception:
                continue
        if spawned > 0:
            gamelib.debug_write(
                f"[oracle.search] mobile={spawned} via '{plan.description[:50]}'"
            )
        return spawned

    def _heuristic_offense(self, gs):
        """Fallback offense — heuristic_v1's adaptive scout/demo logic."""
        try:
            mp = gs.get_resource(MP)
            scout_cost = gs.type_cost(SCOUT)[MP]
            demo_cost = gs.type_cost(DEMOLISHER)[MP]
            t = gs.turn_number
        except Exception:
            return
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)

        if enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if mp >= 9 * scout_cost:
            spawn_opts = self._scout_spawn_options(gs)
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    # ======================================================================
    # Helpers (heuristic_v1)
    # ======================================================================

    def _scout_spawn_options(self, gs):
        opts = [[13, 0], [14, 0]]
        opp_left = self._count_opp_structures_side(gs, 'left')
        opp_right = self._count_opp_structures_side(gs, 'right')
        if opp_left + 4 < opp_right:
            opts.insert(0, [3, 10])
        elif opp_right + 4 < opp_left:
            opts.insert(0, [24, 10])
        return [s for s in opts
                if gs.game_map.in_arena_bounds(s)
                and not gs.contains_stationary_unit(s)]

    def _count_opp_structures_side(self, gs, side):
        n = 0
        for y in range(14, 28):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                if side == 'left' and x >= 14:
                    continue
                if side == 'right' and x < 14:
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 1
        return n

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
        if not options:
            return None
        best = None
        best_dmg = None
        try:
            t_dmg_up = float(self.config["unitInformation"][2].get(
                "upgrade", {}).get("attackDamageWalker",
                self.config["unitInformation"][2].get(
                    "attackDamageWalker", 6)))
        except Exception:
            t_dmg_up = 20.0
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

    # ======================================================================
    # On action frame
    # ======================================================================

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})

        for b in events.get("breach", []):
            if len(b) < 5:
                continue
            loc, _, _, _, owner = b[:5]
            if owner == 2:
                self.scored_on.append(loc)

        for sp in events.get("spawn", []):
            if len(sp) < 4:
                continue
            loc, utype, _, owner = sp[:4]
            if owner != 2:
                continue
            if utype not in (3, 4, 5):
                continue
            x, _ = loc
            if x < 14:
                self.opp_spawn_left += 1
            else:
                self.opp_spawn_right += 1
            if utype == 3:
                self.opp_scout_count += 1
            elif utype == 4:
                self.opp_demo_count += 1


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
