"""smart_oracle_vd — VD baseline + funnel-aware defense focusing.

VD already drove every decision through sim_rs search. The only behavior
smart_oracle_vd adds is a funnel detector that fires when the opponent
has been consistently breaching one flank zone over several turns. When
it fires:

  1. The search restricts its opponent samples to those whose launcher
     routes to the funnel zone, so the value function is no longer
     averaged across attack patterns the opponent isn't actually using.
  2. The enumerator adds flank-corridor defense templates focused on
     that zone, giving the search concrete options to spend SP on the
     side that's actually being hit.

The search algorithm itself is unchanged — it still enumerates plans,
runs sim_rs, and picks the maximum-EU plan. We just reshape the inputs
so the EU it computes reflects the funnel reality. Outside of funnel
games the detector returns None and the algo behaves exactly like VD.

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
from oracle_core.funnel_detector import detect_funnel_target
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
            gamelib.debug_write(f"[smart_oracle_vd] sim_rs path={p}")
        except Exception as e:
            gamelib.debug_write(f"[smart_oracle_vd] sim_rs probe failed: {e!r}")
            self._sim_rs_status = "failed"

        # Load opponent prior (offline-trained from 427 ranked replays).
        prior_path = os.path.join(_HERE, "data", "opp_model_priors.json")
        try:
            self.opp_model = OpponentModel(prior_path=prior_path,
                                           prior_weight=1.0,
                                           min_obs_for_posterior_dominance=3,
                                           rng_seed=42)
            stats = self.opp_model.stats()
            gamelib.debug_write(f"[smart_oracle_vd] opp_model: {stats}")
        except Exception as e:
            gamelib.debug_write(f"[smart_oracle_vd] opp_model init failed: {e!r}")
            self.opp_model = OpponentModel(rng_seed=42)

        gamelib.debug_write("[smart_oracle_vd] on_game_start complete")

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

            # smart_oracle_vd: detect a funnel attack pattern in the opp's
            # recent breaches BEFORE running search. detect_funnel_target
            # returns "left"/"right" only when ≥3 of the last 6 breaches
            # cluster on one flank zone. None means the opponent is not
            # funneling and the search behaves exactly like VD.
            funnel_target = detect_funnel_target(self.recent_breaches)
            if funnel_target is not None:
                gamelib.debug_write(
                    f"[smart_oracle_vd] FUNNEL detected → {funnel_target} "
                    f"(recent_breaches={list(self.recent_breaches)[-6:]})"
                )

            # Drive the SEARCH. The search is still the SOLE decision-maker
            # for both defense and offense. funnel_target only reshapes the
            # inputs (opp samples + defense candidate set); it does NOT
            # bypass or override the search.
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
                funnel_target=funnel_target,
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
                    f"[smart_oracle_vd] vc_pressure n={len(self.breach_pressure)} "
                    f"top={top_p}"
                )
            gamelib.debug_write(
                f"[smart_oracle_vd] turn={gs.turn_number} "
                f"best={result.best_plan.name} score={tel.best_score:.1f} "
                f"cands={tel.candidates_total} "
                f"eval1={tel.candidates_evaluated_phase1} "
                f"eval2={tel.candidates_evaluated_phase2} "
                f"sims={tel.sims_run} "
                f"d2={tel.used_depth2} "
                f"funnel={tel.funnel_target} "
                f"opp_p1={tel.opp_samples_after_filter_phase1} "
                f"opp_p2={tel.opp_samples_after_filter_phase2} "
                f"wall={tel.wall_clock_s:.2f}s "
                f"applied={report}"
            )
            # Log top-3 plans for telemetry quality
            top_str = ", ".join(
                f"{n}({s:.0f})" for n, s, _ in tel.top_n[:3]
            )
            gamelib.debug_write(f"[smart_oracle_vd]  top3: {top_str}")

            # Reset turn observer for next turn's accumulation
            self.turn_observer.reset()

            gs.submit_turn()
            return
        except _TurnTimeout:
            gamelib.debug_write(
                f"[smart_oracle_vd] WATCHDOG fired @ {TURN_WATCHDOG_S}s - "
                f"using safe fallback"
            )
            used_fallback = True
        except Exception as e:
            gamelib.debug_write(
                f"[smart_oracle_vd] on_turn exception: {e!r}\n{traceback.format_exc()}"
            )
            used_fallback = True
        finally:
            disarm()

        if used_fallback:
            self._fallback_count += 1
            self._safe_fallback(turn_state)
            gamelib.debug_write(
                f"[smart_oracle_vd] fallback count={self._fallback_count}/{self._turn_count}"
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
            gamelib.debug_write(f"[smart_oracle_vd] safe_fallback failed: {e!r}")

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

        # Track breaches against us.
        try:
            events = frame.get("events", {}) or {}
            for b in events.get("breach", []) or []:
                if not b or len(b) < 5:
                    continue
                loc, _dmg, _utype, _uid, owner = b[:5]
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
