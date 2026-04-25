"""mirror_athena — adversarial sparring partner that runs a minimal,
hand-extracted replica of Athena's offense/defense logic with SHIFTED
utility weights (α=2.0, β=1.5, γ=0, δ=0).

The brief asks for a frozen Athena snapshot. Importing the live
`algos/athena_v3_planner/` package brings in:
  * `offense.sim_eval` → `sim_rs` PyO3 wheel (Rust extension)
  * `planner.economy.EconomyArbiter` → ArchetypeClassifier + ActionPredictor
  * `archive.MAPElitesArchive` → JSON archive loader
  * vendored gamelib path-shadowing risks
Per the brief: "If imports are too tangled, fall back to a hand-extracted
minimal Athena replica." That's what this is.

The replica preserves Athena's signature behaviors:
  * v13_inspired defense skeleton (the default archetype)
  * 17-template-style offense rotation through scout_rush / demolisher_line
    / mixed_burst / hoard / interceptor_screen
  * Beam-search-style scoring with utility weights — but with weights
    shifted to OVER-weight HP delta and UNDER-weight structure damage
    (α=2.0, β=1.5, γ=0, δ=0). This intentionally biases the algo toward
    aggressive HP-pressure plays (scout flood) and away from structure-
    damaging Demolisher trains.

Production safety wrappers — see anti_athena_hoarder.
"""
from __future__ import annotations

import json
import os
import random
import signal
import sys
import threading
import traceback
from sys import maxsize

import gamelib

_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)


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

    def disarm_thread():
        timer.cancel()

    return disarm_thread, lambda: fired["v"]


# ============================================================================
# Mini-Athena state. Frozen utility-weight snapshot per the brief:
#   α=2.0 (HP delta — heavy weight)
#   β=1.5 (HP-cost — heavy weight)
#   γ=0   (structure damage — IGNORE)
#   δ=0   (MP efficiency — IGNORE)
# This biases beam-search toward HP-pressure offense (scout floods) and
# away from structure-grinding Demolisher trains.
# ============================================================================
ALPHA = 2.0
BETA = 1.5
GAMMA = 0.0
DELTA = 0.0


# Mini-template library — 6 simplified rotations matching Athena's
# `scout_rush_left/right`, `demolisher_line_left/right`, `mixed_burst`,
# `interceptor_screen`, `hoard`. Each is a dict of (kind, location, count).
TEMPLATES = {
    "scout_rush_left": [(("SCOUT", [13, 0]),)],
    "scout_rush_right": [(("SCOUT", [14, 0]),)],
    "demolisher_line_left": [(("DEMOLISHER", [10, 3]), ("SCOUT", [13, 0]))],
    "demolisher_line_right": [(("DEMOLISHER", [17, 3]), ("SCOUT", [14, 0]))],
    "mixed_burst_center": [(("DEMOLISHER", [10, 3]), ("SCOUT", [14, 0]))],
    "interceptor_screen": [(("INTERCEPTOR", [13, 0]), ("INTERCEPTOR", [14, 0]))],
    "hoard": [],  # empty plan = save MP
}


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        self.scored_on = []
        # Rolling EMA of opponent HP for utility computation. Updated
        # from action frames (best-effort).
        self.opp_hp_estimate = 40.0
        self.my_hp_estimate = 40.0
        # Last-turn pick — used to detect repetition and break ties.
        self._last_pick = None

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
        # Map shorthand → cost for utility estimation.
        self._unit_to_shorthand = {
            "WALL": WALL, "SUPPORT": SUPPORT, "TURRET": TURRET,
            "SCOUT": SCOUT, "DEMOLISHER": DEMOLISHER, "INTERCEPTOR": INTERCEPTOR,
        }

    def on_turn(self, turn_state):
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            self._main_turn(gs)
            gs.submit_turn()
        except (_TurnTimeout, Exception):  # noqa: BLE001
            try:
                gamelib.debug_write("mirror_athena: turn failed -- safe fallback")
                gamelib.debug_write(traceback.format_exc())
            except Exception:  # noqa: BLE001
                pass
            try:
                gs = gamelib.GameState(self.config, turn_state)
                gs.suppress_warnings(True)
                self._safe_fallback(gs)
                gs.submit_turn()
            except Exception:  # noqa: BLE001
                pass
        finally:
            try:
                disarm()
            except Exception:  # noqa: BLE001
                pass

    # ---------- main turn ----------

    def _main_turn(self, gs):
        # Cache live HP from gs (overrides EMA when available).
        try:
            self.my_hp_estimate = gs.my_health
            self.opp_hp_estimate = gs.enemy_health
        except Exception:  # noqa: BLE001
            pass

        self._build_v13_inspired_defense(gs)
        self._reactive_patches(gs)
        self._beam_search_offense(gs)

    def _build_v13_inspired_defense(self, gs):
        """v13_inspired archetype skeleton — the default Athena defense."""
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]

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
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 7:
            gs.attempt_upgrade(inner_corners)

    def _reactive_patches(self, gs):
        placed = set()
        for loc in self.scored_on[-6:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y]]
            for build in candidates:
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

    def _beam_search_offense(self, gs):
        """Mini beam-search over the 7-template library.

        Utility = ALPHA * hp_pressure + BETA * mp_used - GAMMA * structure_dmg - DELTA * mp_efficiency
        With α=2.0, β=1.5, γ=δ=0 (per brief), plays HEAVILY for HP pressure
        and tolerates high MP burn — biased toward scout floods.
        """
        t = gs.turn_number
        if t == 0:
            return  # Don't have MP for offense on turn 0 reliably.

        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]

        # Score each template heuristically.
        scored = []
        for name, plan in TEMPLATES.items():
            score = self._score_template(name, plan, my_mp, scout_cost,
                                         demo_cost, interceptor_cost)
            scored.append((score, name, plan))

        # Pick the highest-scoring template.
        scored.sort(reverse=True)
        best_score, best_name, best_plan = scored[0]
        self._last_pick = best_name

        # Execute the plan.
        if best_name == "hoard":
            return  # No-op — economy ramp.
        for step in best_plan:
            for unit_label, loc in step:
                shorthand = self._unit_to_shorthand[unit_label]
                if not gs.game_map.in_arena_bounds(loc):
                    continue
                if gs.contains_stationary_unit(loc):
                    continue
                # For Scout/Demolisher: spawn as many as MP allows on this loc.
                if unit_label in ("SCOUT",):
                    gs.attempt_spawn(shorthand, loc, 1000)
                elif unit_label in ("DEMOLISHER",):
                    n = max(1, int(my_mp / demo_cost / 2))
                    gs.attempt_spawn(shorthand, loc, n)
                elif unit_label in ("INTERCEPTOR",):
                    gs.attempt_spawn(shorthand, loc, 1)

    def _score_template(self, name, plan, my_mp, scout_cost, demo_cost,
                        interceptor_cost):
        """Hand-rolled scoring matching Athena's beam-search shape but with
        the over-weighted HP-delta utility weights from the brief.
        """
        # Estimate HP pressure (α-weighted) and mp_used (β-weighted).
        hp_pressure = 0.0
        mp_used = 0.0

        if name in ("scout_rush_left", "scout_rush_right"):
            n_scouts = int(my_mp / scout_cost)
            # Each Scout estimates 0.5 expected HP damage if breach probability
            # is ~0.3 (rough heuristic — replaces sim_rs rollout).
            hp_pressure = 0.5 * n_scouts
            mp_used = n_scouts * scout_cost
        elif name in ("demolisher_line_left", "demolisher_line_right"):
            n_demos = int(my_mp / demo_cost / 2)
            n_scouts = int((my_mp - n_demos * demo_cost) / scout_cost)
            # Demolishers do structure damage, not HP — heavily underweighted
            # by γ=0. We get a small HP residual.
            hp_pressure = 0.2 * n_demos + 0.4 * n_scouts
            mp_used = n_demos * demo_cost + n_scouts * scout_cost
        elif name == "mixed_burst_center":
            n_demos = 4
            n_scouts = int((my_mp - n_demos * demo_cost) / scout_cost)
            hp_pressure = 0.3 * n_demos + 0.4 * n_scouts
            mp_used = n_demos * demo_cost + n_scouts * scout_cost
        elif name == "interceptor_screen":
            # Defensive — basically zero offensive value.
            hp_pressure = 0.0
            mp_used = 2 * interceptor_cost
        elif name == "hoard":
            # Saves MP for next turn — small future-value heuristic.
            hp_pressure = 0.05 * my_mp  # tiny ramp value
            mp_used = 0

        # Apply utility weights — the SHIFT.
        return ALPHA * hp_pressure + BETA * mp_used  # γ=δ=0

    def _safe_fallback(self, gs):
        for loc in [[11, 11], [16, 11], [13, 11], [14, 11]]:
            gs.attempt_spawn(TURRET, loc)

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:  # noqa: BLE001
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            try:
                loc, _, _, _, owner = b
            except (TypeError, ValueError):
                continue
            if owner == 2:
                self.scored_on.append(loc)


if __name__ == "__main__":
    AlgoStrategy().start()
