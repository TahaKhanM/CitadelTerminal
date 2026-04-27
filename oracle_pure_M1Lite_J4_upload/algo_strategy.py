"""oracle_pure — fully search-driven Citadel Terminal algo.

This is NOT a heuristic algorithm with bolted-on search. The search
OWNS every decision. Defense and offense both come from the
sim_rs-evaluated maximum-EU candidate plan. The only hardcoded
behavior is the safe fallback that fires when the per-turn watchdog
trips — and even that is just "4 corner turrets + scouts," not a
strong defense.

Architecture:
  on_turn:
    1. Adapt live game_state to sim_rs schema (state_adapter)
    2. Enumerate ~500-2000 candidate plans (defense + offense combos)
    3. Sample K opponent action plans from the OpponentModel
       (replay-trained prior + in-game adaptive update)
    4. Run sim_rs on each (candidate × opp_sample) → evaluate value()
    5. Pick maximum-EU plan; apply via plan.apply_to_game_state
    6. Submit turn

Safety:
  - 13s SIGALRM watchdog per on_turn
  - Top-level try/except → falls through to safe_fallback() which
    places 4 corner turrets + spawns scouts at center launcher.
"""
from __future__ import annotations

import json
import os
import signal
import sys
import threading
import time
import traceback
from typing import List, Optional, Tuple

# Ensure local subpackage is importable
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

import gamelib

from oracle_core.constants import (
    DEMOLISHER_IDX, INTERCEPTOR_IDX, MP_RES, SCOUT_IDX, SP_RES,
    TURRET_IDX, shorthand_for_idx,
)
from oracle_core.opponent_model import OpponentModel, TurnObserver
from oracle_core.plan import ActionPlan, apply_to_game_state
from oracle_core.search import search


# Watchdog config (15s engine cap; we leave 2s margin)
TURN_WATCHDOG_S = 13
SEARCH_BUDGET_S = 11.0


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_S")


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
    # Variant C: per-tile breach pressure used as a soft signal in the
    # value function. Each opp breach against us adds +1.0 to that
    # tile's pressure; pressure decays multiplicatively each turn so
    # old hotspots fade naturally (no hard threshold). The decay rate
    # was chosen so a single breach takes ~10 turns to fade to 35%
    # (0.9^10 = 0.349). The increment lives in on_action_frame; decay
    # runs once per turn at the start of on_turn.
    BREACH_PRESSURE_DECAY = 0.9
    BREACH_PRESSURE_PRUNE_BELOW = 0.05  # drop tiles whose pressure faded out

    def __init__(self):
        super().__init__()
        self.config = None
        self.opp_model: Optional[OpponentModel] = None
        self.turn_observer = TurnObserver(self_player_id=1)
        self.recent_breaches: List[Tuple[int, int]] = []
        self.breach_pressure: dict = {}  # {(x,y): float}
        self._last_pressure_decayed_turn = -1  # decay runs once per turn
        self._turn_count = 0
        self._fallback_count = 0
        self._sim_rs_status = "unknown"
        # J4 — map opp unit_id (assigned by engine on spawn) to its
        # spawn-xy launcher tile. Built from opponent spawn events;
        # read by breach events to attribute breaches back to launchers.
        # Reset is per-match (lifecycle = AlgoStrategy instance lifetime).
        self._opp_unit_spawn_xy: dict = {}

    # ------------------------------------------------------------------
    # Game start
    # ------------------------------------------------------------------
    def on_game_start(self, config):
        self.config = config

        # Eager-load sim_rs to surface load issues early.
        try:
            from oracle_core.sim_eval import _get_sim_rs, _SIM_RS_LOAD_PATH
            _ = _get_sim_rs()
            from oracle_core.sim_eval import _SIM_RS_LOAD_PATH as p
            self._sim_rs_status = p
            gamelib.debug_write(f"[oracle_pure] sim_rs path={p}")
        except Exception as e:
            gamelib.debug_write(f"[oracle_pure] sim_rs probe failed: {e!r}")
            self._sim_rs_status = "failed"

        # Load opponent prior (offline-trained from 427 ranked replays).
        prior_path = os.path.join(_HERE, "data", "opp_model_priors.json")
        try:
            self.opp_model = OpponentModel(prior_path=prior_path,
                                           prior_weight=1.0,
                                           min_obs_for_posterior_dominance=3,
                                           rng_seed=42)
            stats = self.opp_model.stats()
            gamelib.debug_write(f"[oracle_pure] opp_model: {stats}")
        except Exception as e:
            gamelib.debug_write(f"[oracle_pure] opp_model init failed: {e!r}")
            self.opp_model = OpponentModel(rng_seed=42)

        gamelib.debug_write("[oracle_pure] on_game_start complete")

    # ------------------------------------------------------------------
    # Turn loop
    # ------------------------------------------------------------------
    def on_turn(self, turn_state):
        self._turn_count += 1
        t0 = time.time()
        disarm, _ = _arm_watchdog(TURN_WATCHDOG_S)
        gs = None
        used_fallback = False

        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)

            # Variant C — decay breach_pressure once per turn (idempotent
            # via _last_pressure_decayed_turn). Decay BEFORE search so the
            # pressure passed to evaluate() reflects "going into this turn"
            # state. on_action_frame increments will be applied during
            # this turn's action phase, becoming visible NEXT turn.
            cur_turn = int(gs.turn_number)
            if cur_turn != self._last_pressure_decayed_turn:
                if self.breach_pressure:
                    decayed = {}
                    for xy, p in self.breach_pressure.items():
                        new_p = p * self.BREACH_PRESSURE_DECAY
                        if new_p >= self.BREACH_PRESSURE_PRUNE_BELOW:
                            decayed[xy] = new_p
                    self.breach_pressure = decayed
                self._last_pressure_decayed_turn = cur_turn

            # Drive the SEARCH. The search is the SOLE decision-maker for
            # both defense and offense — there is NO heuristic pipeline
            # before it.
            result = search(
                gs, self.config, self.opp_model,
                time_budget_s=SEARCH_BUDGET_S,
                k_opp=6,
                k_opp_phase1=1,           # single sample for cheap cull (proven 10/10)
                phase2_top_n=30,
                do_depth2=True,
                depth2_top_n=3,
                max_plans=2500,
                recent_breaches=self.recent_breaches[-6:],
                breach_pressure=self.breach_pressure,
                debug_log=lambda m: gamelib.debug_write(m),
            )

            # Apply best plan
            report = apply_to_game_state(result.best_plan, gs, self.config)
            tel = result.telemetry
            # Variant C telemetry: log pressure map size (small overhead)
            if self.breach_pressure:
                top_p = sorted(self.breach_pressure.items(),
                               key=lambda x: -x[1])[:2]
                gamelib.debug_write(
                    f"[oracle_pure] vc_pressure n={len(self.breach_pressure)} "
                    f"top={top_p}"
                )
            gamelib.debug_write(
                f"[oracle_pure] turn={gs.turn_number} "
                f"best={result.best_plan.name} score={tel.best_score:.1f} "
                f"cands={tel.candidates_total} "
                f"eval1={tel.candidates_evaluated_phase1} "
                f"eval2={tel.candidates_evaluated_phase2} "
                f"sims={tel.sims_run} "
                f"d2={tel.used_depth2} "
                f"wall={tel.wall_clock_s:.2f}s "
                f"applied={report}"
            )
            # Log top-3 plans for telemetry quality
            top_str = ", ".join(
                f"{n}({s:.0f})" for n, s, _ in tel.top_n[:3]
            )
            gamelib.debug_write(f"[oracle_pure]  top3: {top_str}")
            # J4 — log hot launchers when there's signal, but only
            # every 5 turns to minimize per-turn compute overhead
            # (M1Lite H2H losses were tiebreak-induced from extra
            # logging compute).
            if self.opp_model is not None and (gs.turn_number % 5 == 0):
                hot = self.opp_model.hot_launchers(top_k=3)
                if hot:
                    hot_str = " ".join(
                        f"{xy}={br}/{sp}" for xy, br, sp in hot
                    )
                    gamelib.debug_write(
                        f"[oracle_pure]  j4_hot_launchers: {hot_str} "
                        f"bias_apps={self.opp_model.bias_applications}"
                    )

            # Reset turn observer for next turn's accumulation
            self.turn_observer.reset()

            gs.submit_turn()
            return
        except _TurnTimeout:
            gamelib.debug_write(
                f"[oracle_pure] WATCHDOG fired @ {TURN_WATCHDOG_S}s - "
                f"using safe fallback"
            )
            used_fallback = True
        except Exception as e:
            gamelib.debug_write(
                f"[oracle_pure] on_turn exception: {e!r}\n{traceback.format_exc()}"
            )
            used_fallback = True
        finally:
            disarm()

        if used_fallback:
            self._fallback_count += 1
            self._safe_fallback(turn_state)
            gamelib.debug_write(
                f"[oracle_pure] fallback count={self._fallback_count}/{self._turn_count}"
            )

    # ------------------------------------------------------------------
    # Safe fallback (NOT a strong defense pipeline — per project spec)
    # ------------------------------------------------------------------
    def _safe_fallback(self, turn_state):
        """Place 4 corner turrets + scout rush from center.

        This is the LAST RESORT (only fires on watchdog timeout or
        crash). It is intentionally weak so it doesn't substitute for
        the search.
        """
        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            turret_sh = shorthand_for_idx(self.config, TURRET_IDX)
            scout_sh = shorthand_for_idx(self.config, SCOUT_IDX)
            # 4 corner turrets — bare minimum
            for xy in ([0, 13], [27, 13], [1, 12], [26, 12]):
                if not gs.contains_stationary_unit(xy):
                    try:
                        gs.attempt_spawn(turret_sh, xy)
                    except Exception:
                        pass
            # Scout rush
            mp = gs.get_resource(MP_RES)
            if mp >= 1:
                try:
                    gs.attempt_spawn(scout_sh, [13, 0], int(mp))
                except Exception:
                    pass
            gs.submit_turn()
        except Exception as e:
            gamelib.debug_write(f"[oracle_pure] safe_fallback failed: {e!r}")

    # ------------------------------------------------------------------
    # Action-frame tracking
    # ------------------------------------------------------------------
    def on_action_frame(self, turn_string):
        try:
            frame = json.loads(turn_string)
        except Exception:
            return

        # Track opp deploys for the opp model (live update).
        try:
            self.turn_observer.consume(frame)
        except Exception:
            pass

        # J4 — populate the unit_id → spawn_xy map from opp spawn events.
        # Action-frame spawn shape: [[x,y], unit_type_idx, unit_id, player_id]
        # Mobile types are 3 (scout), 4 (demolisher), 5 (interceptor).
        # We only need launcher coordinates for mobiles since structures
        # don't breach. Records every spawn (regardless of breach) so the
        # breach rate denominator (total spawns) is accurate.
        try:
            events = frame.get("events", {}) or {}
            for sp in events.get("spawn", []) or []:
                if not sp or len(sp) < 4:
                    continue
                loc, utype, uid, owner = sp[:4]
                if int(owner) != 2:
                    continue  # only opp spawns
                try:
                    ut = int(utype)
                except Exception:
                    continue
                if ut not in (SCOUT_IDX, DEMOLISHER_IDX, INTERCEPTOR_IDX):
                    continue
                try:
                    x, y = int(loc[0]), int(loc[1])
                except Exception:
                    continue
                launcher_xy = (x, y)
                # Track spawn (denominator for breach rate)
                if self.opp_model is not None:
                    self.opp_model.observe_spawn(launcher_xy)
                # Map unit_id -> spawn_xy so subsequent breach events
                # can be attributed to the launcher this unit came from.
                try:
                    self._opp_unit_spawn_xy[str(uid)] = launcher_xy
                except Exception:
                    pass
        except Exception:
            pass

        # Track breaches against us.
        try:
            events = frame.get("events", {}) or {}
            for b in events.get("breach", []) or []:
                if not b or len(b) < 5:
                    continue
                loc, _dmg, _utype, uid, owner = b[:5]
                if int(owner) != 2:
                    continue  # only count opp breaches against us
                try:
                    x, y = int(loc[0]), int(loc[1])
                except Exception:
                    continue
                self.recent_breaches.append((x, y))
                if len(self.recent_breaches) > 32:
                    self.recent_breaches.pop(0)
                # Variant C — increment per-tile pressure. Decay runs
                # once per turn at the start of on_turn; here we only
                # add. A single breach contributes +1.0; two breaches
                # at the same tile in one turn contribute +2.0; etc.
                self.breach_pressure[(x, y)] = \
                    self.breach_pressure.get((x, y), 0.0) + 1.0
                # J4 — attribute breach back to the spawning launcher.
                # If we don't have a spawn_xy mapping (e.g. unit was
                # spawned in a frame we missed), silently skip — better
                # to under-record than to mis-attribute.
                if self.opp_model is not None:
                    launcher_xy = self._opp_unit_spawn_xy.get(str(uid))
                    if launcher_xy is not None:
                        self.opp_model.observe_breach(launcher_xy)
        except Exception:
            pass

        # If this is the LAST action frame of the turn (phase=2 end), feed
        # the accumulated turn observation into the opp model.
        try:
            ti = frame.get("turnInfo")
            if ti and len(ti) >= 1 and int(ti[0]) == 2:
                # End of action phase — observation is complete.
                sig = self.turn_observer.snapshot()
                if not sig.is_empty():
                    # We need the resources at the START of this turn for the
                    # bucket key. Use the action-frame stats as proxy.
                    p1s = frame.get("p1Stats") or [0, 0, 0]
                    p2s = frame.get("p2Stats") or [0, 0, 0]
                    our_mp = float(p1s[2]) if len(p1s) >= 3 else 0.0
                    opp_mp = float(p2s[2]) if len(p2s) >= 3 else 0.0
                    turn = int(ti[1]) if len(ti) >= 2 else 0
                    self.opp_model.observe(
                        turn, our_mp=our_mp, opp_mp=opp_mp,
                        recent_breaches=len(self.recent_breaches),
                        sig=sig,
                    )
                self.turn_observer.reset()
        except Exception:
            pass


if __name__ == "__main__":
    AlgoStrategy().start()
