"""Athena v3 planner — OFFENSE-ONLY variant.

Phase 4 validation harness: minimal hand-written defense (4 base
turrets at canonical safe positions + a small wall ring) PLUS the
full Phase 4 offense engine:
  - 17 JSON templates loaded from offense/templates/.
  - Beam search with budget management (Watchdog).
  - sim_rs PyO3 wheel for action-phase rollout (lazy-imported; if
    unavailable, falls back to no-rollout pick by mp_cost heuristic).

Production-safety wrappers (try/except + 13s SIGALRM watchdog +
safe-fallback) lifted from
``algos/athena_v3_planner_defense_only/algo_strategy.py``.
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

import gamelib

# Make the local subpackages importable when the engine launches us.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

from planner.offense import (  # noqa: E402
    Candidate,
    beam_search,
    generate_candidates,
)
from planner.budget import BudgetExceeded, Watchdog  # noqa: E402

# sim_rs is OPTIONAL for offense-only — if not available, we degrade to a
# heuristic (max-units-within-budget) candidate without sim rollout.
try:
    import sim_rs  # type: ignore  # noqa: F401
    _SIM_RS_AVAILABLE = True
except ImportError:
    _SIM_RS_AVAILABLE = False


# --- Production safety: turn-time watchdog --------------------------------
TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    """Arm a SIGALRM watchdog. Returns (disarm, fired_check) tuple."""
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


# Resource indices (gamelib's GameState.get_resource expects 0=SP, 1=MP).
SP_IDX = 0
MP_IDX = 1


# --- Minimal defense layout (canonical safe positions) -------------------

# Base turrets along y=12 (safer than y=13 — they're behind a wall row).
_BASE_TURRETS = ((3, 12), (24, 12), (10, 12), (17, 12))
# Front-row walls to delay any path into our turrets.
_BASE_WALLS = (
    (0, 13), (1, 13), (26, 13), (27, 13),
    (3, 13), (24, 13),
)


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        gamelib.debug_write("[athena-offense-only] random seed: {}".format(seed))
        self.config = None
        self.turret_shorthand = "DF"
        self.wall_shorthand = "FF"
        self.scout_shorthand = "PI"
        self.demolisher_shorthand = "EI"
        self.interceptor_shorthand = "SI"
        self.turn_count = 0
        self.watchdog = Watchdog(total_budget_ms=TURN_WATCHDOG_SECONDS * 1000.0)

    def on_game_start(self, config):
        self.config = config
        try:
            self.wall_shorthand = config["unitInformation"][0]["shorthand"]
            self.turret_shorthand = config["unitInformation"][2]["shorthand"]
            self.scout_shorthand = config["unitInformation"][3]["shorthand"]
            self.demolisher_shorthand = config["unitInformation"][4]["shorthand"]
            self.interceptor_shorthand = config["unitInformation"][5]["shorthand"]
        except Exception:  # noqa: BLE001
            pass
        gamelib.debug_write(
            "[athena-offense-only] on_game_start; sim_rs={} wall={} turret={} "
            "scout={} demo={} interceptor={}".format(
                _SIM_RS_AVAILABLE, self.wall_shorthand, self.turret_shorthand,
                self.scout_shorthand, self.demolisher_shorthand,
                self.interceptor_shorthand,
            )
        )

    # ----------------------------------------------------------------------
    # on_turn: minimal defense + offense engine
    # ----------------------------------------------------------------------

    def on_turn(self, turn_state):
        start = time.time()
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        game_state = None
        used_fallback = False
        try:
            game_state = gamelib.GameState(self.config, turn_state)
            self.turn_count = int(game_state.turn_number)
            self.watchdog.start_turn()
            self._minimal_defense(game_state)
            self.watchdog.check("DefensePlanner")
            self._offense_turn(game_state)
            game_state.submit_turn()
        except (_TurnTimeout, BudgetExceeded) as exc:
            gamelib.debug_write(
                "[athena-offense-only] turn watchdog fired: {!r}".format(exc)
            )
            used_fallback = True
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                "[athena-offense-only] on_turn exception: {!r}\n{}".format(
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
            except Exception as exc2:  # noqa: BLE001
                gamelib.debug_write(
                    "[athena-offense-only] safe-fallback failed: {!r}".format(exc2)
                )
                try:
                    if game_state is not None:
                        game_state.submit_turn()
                except Exception:  # noqa: BLE001
                    pass

        if fired() and not used_fallback:
            gamelib.debug_write(
                "[athena-offense-only] watchdog flag set but turn finished "
                "in {:.2f}s".format(time.time() - start)
            )

    # ----------------------------------------------------------------------
    # Minimal defense
    # ----------------------------------------------------------------------

    def _minimal_defense(self, game_state):
        """Place 4 turrets + 6 walls at canonical positions (config-driven costs)."""
        try:
            sp = float(game_state.get_resource(SP_IDX, 0))
        except Exception:  # noqa: BLE001
            sp = 0.0

        # Walls first (cheaper, block edge path).
        for loc in _BASE_WALLS:
            try:
                cost = game_state.type_cost(self.wall_shorthand)[SP_IDX]
            except Exception:  # noqa: BLE001
                cost = 1.0
            if sp < cost:
                break
            try:
                placed = game_state.attempt_spawn(self.wall_shorthand, list(loc))
            except Exception:  # noqa: BLE001
                placed = 0
            if placed:
                sp -= cost

        # Turrets next.
        for loc in _BASE_TURRETS:
            try:
                cost = game_state.type_cost(self.turret_shorthand)[SP_IDX]
            except Exception:  # noqa: BLE001
                cost = 2.0
            if sp < cost:
                break
            try:
                placed = game_state.attempt_spawn(self.turret_shorthand, list(loc))
            except Exception:  # noqa: BLE001
                placed = 0
            if placed:
                sp -= cost

    # ----------------------------------------------------------------------
    # Offense engine
    # ----------------------------------------------------------------------

    def _offense_turn(self, game_state):
        """Run beam search and execute the chosen plan."""
        self.watchdog.begin("OffensePlanner")
        try:
            mp_avail = float(game_state.get_resource(MP_IDX, 0))
        except Exception:  # noqa: BLE001
            mp_avail = 0.0
        if mp_avail < 1.0:
            return  # not enough MP for any meaningful spawn

        # Build a state_dict for the sim. Since sim_rs needs a fully-typed
        # state (uids, structure HP from config, etc.), we instead use the
        # heuristic path: skip rollout if sim_rs is unavailable OR if our
        # gamelib<->sim_rs adapter isn't fully wired (Phase 5 will add a
        # complete adapter; for now we use a minimal shim).
        state_dict = self._gamelib_to_simdict(game_state)

        cands = generate_candidates(state_dict, mp_avail, my_player=1)
        if len(cands) <= 1:
            return  # only the hoard; nothing to spawn

        # Per-spec: budget budget_ms for offense
        rem = max(50.0, self.watchdog.remaining_ms() - 1000.0)  # leave 1s GC
        offense_budget = min(8000.0, rem)

        best = beam_search(
            state_dict, cands,
            opp_actions_top_k=[],          # Phase 4 ships without opp predictor wired
            my_player=1,
            budget_ms=offense_budget,
            skip_sim=True,                 # heuristic-only — Phase 5 wires sim_rs
        )
        if best is None or best.name == "hoard":
            return

        # Execute the chosen plan
        for unit_name, loc in best.deploys:
            shorthand = self._mobile_shorthand(unit_name)
            try:
                spawned = game_state.attempt_spawn(shorthand, list(loc))
            except Exception as exc:  # noqa: BLE001
                gamelib.debug_write(
                    "[athena-offense-only] attempt_spawn failed: {!r} "
                    "unit={} loc={}".format(exc, unit_name, loc)
                )
                spawned = 0
            if not spawned:
                # Likely MP exhausted or path blocked; stop the cascade
                break

        gamelib.debug_write(
            "[athena-offense-only] turn={} mp={:.1f} pick={} "
            "EU={:.2f} sims={}".format(
                self.turn_count, mp_avail, best.name,
                best.expected_utility, best.sim_count,
            )
        )

    def _mobile_shorthand(self, name: str) -> str:
        return {
            "SCOUT": self.scout_shorthand,
            "DEMOLISHER": self.demolisher_shorthand,
            "INTERCEPTOR": self.interceptor_shorthand,
        }.get(name.upper(), self.scout_shorthand)

    # ----------------------------------------------------------------------
    # Gamelib -> sim_rs state-dict shim (minimal — no uid/HP fidelity)
    # ----------------------------------------------------------------------

    def _gamelib_to_simdict(self, game_state) -> dict:
        """Build a minimal sim_rs-schema dict from gamelib's GameState.

        This is a shim — we intentionally elide uid + HP fields where we
        don't have them, because beam_search's static prune doesn't need
        them. The Rust simulate_action_phase WILL crash on a malformed
        state, so we always pass opp_actions_top_k=[] in offense_only
        (no rollout). Phase 5 will wire a complete adapter.
        """
        # gamelib's GameState exposes HP directly as attributes.
        try:
            p1_hp = float(getattr(game_state, "my_health", 30.0))
            p2_hp = float(getattr(game_state, "enemy_health", 30.0))
        except Exception:  # noqa: BLE001
            p1_hp, p2_hp = 30.0, 30.0
        try:
            p1_sp = float(game_state.get_resource(SP_IDX, 0))
            p1_mp = float(game_state.get_resource(MP_IDX, 0))
            p2_sp = float(game_state.get_resource(SP_IDX, 1))
            p2_mp = float(game_state.get_resource(MP_IDX, 1))
        except Exception:  # noqa: BLE001
            p1_sp, p1_mp, p2_sp, p2_mp = 8.0, 0.0, 8.0, 0.0

        # We only need the spawn-tile occupancy info for candidate
        # generation; HP/uid don't matter for the no-rollout path.
        structures = []
        try:
            game_map = game_state.game_map
            for x in range(28):
                for y in range(28):
                    if not game_map.in_arena_bounds([x, y]):
                        continue
                    units = game_map[x, y]
                    if not units:
                        continue
                    for u in units:
                        if u.stationary:
                            structures.append({
                                "xy": [x, y],
                                "type_idx": _shorthand_to_type_idx(u.unit_type),
                                "upgraded": bool(u.upgraded),
                                "hp": float(getattr(u, "health", 1.0)),
                                "uid": str(getattr(u, "unit_id", id(u))),
                                "player": 1 if u.player_index == 0 else 2,
                                "turn_start_removal": None,
                            })
        except Exception as exc:  # noqa: BLE001
            gamelib.debug_write(
                "[athena-offense-only] gamelib->simdict structures fail: {!r}".format(exc)
            )

        return {
            "turn": int(self.turn_count),
            "p1": {"hp": p1_hp, "sp": p1_sp, "mp": p1_mp},
            "p2": {"hp": p2_hp, "sp": p2_sp, "mp": p2_mp},
            "structures": structures,
            "mobiles": [],
        }

    # ----------------------------------------------------------------------
    # Safe-fallback minimal defense (lifted from defense-only)
    # ----------------------------------------------------------------------

    _SAFE_FALLBACK_TURRETS = ((2, 13), (25, 13), (3, 13), (24, 13))

    def _safe_fallback_turn(self, game_state):
        try:
            sp = game_state.get_resource(SP_IDX, 0)
        except Exception:  # noqa: BLE001
            sp = 0.0
        for loc in self._SAFE_FALLBACK_TURRETS:
            try:
                cost = game_state.type_cost(self.turret_shorthand)[SP_IDX]
            except Exception:  # noqa: BLE001
                cost = 2.0
            if sp < cost:
                break
            try:
                placed = game_state.attempt_spawn(self.turret_shorthand, list(loc))
            except Exception:  # noqa: BLE001
                placed = 0
            if placed:
                sp -= cost

    def on_action_frame(self, turn_string):
        # No-op for now — telemetry hooks can land in Phase 5.
        return


# ----------------------------------------------------------------------------
# Helpers
# ----------------------------------------------------------------------------

def _shorthand_to_type_idx(sh: str) -> int:
    return {
        "FF": 0, "EF": 1, "DF": 2, "PI": 3, "EI": 4, "SI": 5,
        "WALL": 0, "SUPPORT": 1, "TURRET": 2,
        "SCOUT": 3, "DEMOLISHER": 4, "INTERCEPTOR": 5,
    }.get(str(sh).upper(), 0)


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
