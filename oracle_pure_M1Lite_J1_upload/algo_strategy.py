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
    # Variant C (kept): per-tile breach pressure used as a soft signal in
    # the value function. Each opp breach against us adds +1.0 to that
    # tile's pressure; decays multiplicatively each turn.
    BREACH_PRESSURE_DECAY = 0.9
    BREACH_PRESSURE_PRUNE_BELOW = 0.05  # drop tiles whose pressure faded out

    # Variant J1: per-tile TRANSIT pressure for opp mobiles passing through
    # oracle's territory. Increment via `move` events in on_action_frame
    # (one increment per step in cell). Decay 0.85/turn (~6-turn half-life;
    # matches oracle_successor's calibration). The signal answers "where
    # are opp's mobiles ACTUALLY going through MY territory" — much more
    # informative than only the breach exit tile, especially for
    # single-tile-targeting opponents (Egil concentrates 87% of damage
    # on (8,5) but transits cluster around y=11-13 where supports/turrets
    # could intercept). The signal feeds an adaptive coverage term in
    # value.py whose weight scales with peak/mean of the pressure map
    # (funnel_alpha) — uniform attack patterns get baseline weight,
    # concentrated attacks get high weight.
    TRANSIT_PRESSURE_DECAY = 0.85
    TRANSIT_PRESSURE_PRUNE_BELOW = 0.5
    # Minimum total pressure before funnel_alpha can fire (avoids early-
    # game noise; ~5 turns of moderate opp transit).
    TRANSIT_MIN_TOTAL = 50.0
    # Minimum turn count gate (avoids early-game noise — opp may have
    # only deployed once which is uniform-noise pattern).
    TRANSIT_MIN_TURN = 8
    # Top-K tiles to retain for the search (the value function only
    # iterates over high-pressure tiles, capped for performance).
    TRANSIT_TOP_K = 20

    def __init__(self):
        super().__init__()
        self.config = None
        self.opp_model: Optional[OpponentModel] = None
        self.turn_observer = TurnObserver(self_player_id=1)
        self.recent_breaches: List[Tuple[int, int]] = []
        self.breach_pressure: dict = {}  # {(x,y): float}
        # J1: cumulative transit pressure (decayed each turn).
        self.transit_pressure: dict = {}  # {(x,y): float}
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

            # Variant C/J1 — decay BOTH breach_pressure and transit_pressure
            # once per turn (idempotent via _last_pressure_decayed_turn).
            # Decay BEFORE search so the pressure passed to evaluate()
            # reflects "going into this turn" state. on_action_frame
            # increments will be applied during this turn's action phase,
            # becoming visible NEXT turn.
            cur_turn = int(gs.turn_number)
            if cur_turn != self._last_pressure_decayed_turn:
                if self.breach_pressure:
                    decayed = {}
                    for xy, p in self.breach_pressure.items():
                        new_p = p * self.BREACH_PRESSURE_DECAY
                        if new_p >= self.BREACH_PRESSURE_PRUNE_BELOW:
                            decayed[xy] = new_p
                    self.breach_pressure = decayed
                # J1: decay transit_pressure
                if self.transit_pressure:
                    t_decayed = {}
                    for xy, p in self.transit_pressure.items():
                        new_p = p * self.TRANSIT_PRESSURE_DECAY
                        if new_p >= self.TRANSIT_PRESSURE_PRUNE_BELOW:
                            t_decayed[xy] = new_p
                    self.transit_pressure = t_decayed
                self._last_pressure_decayed_turn = cur_turn

            # J1: pre-aggregate top-K transit tiles + compute funnel_alpha.
            # Pass the result to evaluate() via the breach_pressure param —
            # this reuses VC's existing pipeline so search.py is unchanged.
            adaptive_pressure = self._build_adaptive_pressure(cur_turn)

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
                breach_pressure=adaptive_pressure,
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
            # J1 telemetry: log transit pressure + funnel_alpha
            if self.transit_pressure:
                top_t = sorted(self.transit_pressure.items(),
                               key=lambda x: -x[1])[:3]
                alpha = self._compute_funnel_alpha()
                weight = self._compute_adaptive_weight(alpha, cur_turn)
                total_p = sum(self.transit_pressure.values())
                gamelib.debug_write(
                    f"[oracle_pure] j1_transit n={len(self.transit_pressure)} "
                    f"total={total_p:.1f} alpha={alpha:.3f} "
                    f"weight={weight:.3f} top={top_t}"
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
    # J1 — Adaptive transit-pressure signal helpers
    # ------------------------------------------------------------------
    def _compute_funnel_alpha(self) -> float:
        """funnel_alpha = peak / mean of nonzero transit pressure.

        Returns 0.0 when total pressure is below TRANSIT_MIN_TOTAL
        (insufficient evidence). When opp's mobiles are scattered
        uniformly, peak/mean ≈ 1.0 and the signal stays low. When opp
        targets a specific path repeatedly, peak/mean grows; we
        normalize by /mean so the value is intuitive.

        Range: [0, +inf) but practically ~1.0 (uniform) to ~10+
        (single-tile concentration).
        """
        if not self.transit_pressure:
            return 0.0
        vals = [p for p in self.transit_pressure.values() if p > 0.5]
        if not vals:
            return 0.0
        total = sum(vals)
        if total < self.TRANSIT_MIN_TOTAL:
            return 0.0
        peak = max(vals)
        mean_nonzero = total / len(vals)
        if mean_nonzero <= 1e-6:
            return 0.0
        return peak / mean_nonzero

    def _compute_adaptive_weight(self, alpha: float, cur_turn: int) -> float:
        """Compute the coverage-term weight from funnel_alpha.

        Calibration (HARDENED post-self-play regression evidence):
        Empirical data showed J1 vs M1L (symmetric self-play) reaches
        alpha=4.18 while a true funnel attack (Egil/python-algo-siege)
        reaches alpha=7.5. The original threshold (2.0) caught both
        cases. Raising threshold to 5.0 and capping max weight at 0.5
        avoids false positives in symmetric play while still meaningfully
        boosting coverage when a real funnel is detected.

        - Below TRANSIT_MIN_TURN (early game): force baseline 0.2
          (insufficient observations even if total > MIN_TOTAL — typical
          early game has 1-2 deploys at the same launcher which is
          mechanical, not strategic).
        - alpha < 5.0  → weight = 0.2 (baseline = VC default; symmetric
          play and any normal scout rush stays here).
        - alpha 5.0..8.0 → weight scales linearly 0.2 → 0.5.
        - alpha > 8.0 → weight = 0.5 (saturated; very concentrated attack).

        Math: weight = 0.2 + clip((alpha - 5.0) / 3.0, 0, 1) * 0.3.
        """
        if cur_turn < self.TRANSIT_MIN_TURN:
            return 0.2
        if alpha <= 5.0:
            return 0.2
        boost = min(1.0, (alpha - 5.0) / 3.0)
        return 0.2 + boost * 0.3

    def _build_adaptive_pressure(self, cur_turn: int) -> dict:
        """Build the dict passed to evaluate() as `breach_pressure` arg.

        We REPLACE VC's breach-exit signal with J1's transit signal —
        the transit signal is strictly more informative for the
        coverage scoring (it tells us where opp's mobiles ACTUALLY
        cross our turret ranges, not just where they exit). The same
        structural pipeline (search.py → value.py coverage_value)
        consumes the dict.

        We pre-aggregate to top-K to bound the per-evaluation cost.
        Each tile's value is multiplied by the adaptive weight so the
        downstream coverage_value just sums turret_threat × weighted_p
        — calibration is pre-applied here, not at the value site.

        When the signal is too weak (or too early), this returns the
        breach_pressure dict (VC's existing fallback) — preserving
        VD-equivalent behavior in unfunneled matches.
        """
        # FAST PATH: when no funnel signal, behave EXACTLY as VD.
        # This is critical for self-play stability: if J1 and VD pass
        # different dicts to evaluate() in symmetric games, the EU
        # comparisons diverge and we lose the strict-superset property.
        # VC's breach_pressure dict is the proven baseline; we fall
        # through to it in all sub-funnel cases (including empty).
        if not self.transit_pressure:
            return self.breach_pressure

        alpha = self._compute_funnel_alpha()
        weight = self._compute_adaptive_weight(alpha, cur_turn)
        if weight <= 0.2:
            # Baseline weight = no clear funnel. Behave as VD.
            return self.breach_pressure

        # FUNNEL DETECTED: use transit dict at boosted weight.
        # Aggregate top-K (bounded for performance).
        items = sorted(
            self.transit_pressure.items(), key=lambda x: -x[1]
        )[:self.TRANSIT_TOP_K]
        # Apply weight as a SCALAR MULTIPLIER (relative to VC's 0.2
        # baseline). Since coverage_value uses weights["breach_pressure_coverage"]
        # at 0.2, we pre-multiply the data by (weight / 0.2). When weight=0.5,
        # effective weight is 2.5x baseline.
        scalar = weight / 0.2
        return {tile: p * scalar for tile, p in items}

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

        # J1: Track opp mobile TRANSIT through oracle's territory.
        # Hook on `move` events — each step contributes +1.0 to the
        # cell the unit just left. Restricting to y < 14 ensures the
        # signal reflects transits IN OUR TERRITORY (the cells where
        # we could intercept), not in opp's territory (where we have
        # no defensive options anyway).
        # move event format: [from_xy, to_xy, ?, type_idx, uid, owner]
        try:
            events = frame.get("events", {}) or {}
            for mv in events.get("move", []) or []:
                if not mv or len(mv) < 6:
                    continue
                from_xy, _to_xy, _, type_idx, _uid, owner = mv[:6]
                if int(owner) != 2:
                    continue  # only opp mobiles
                if int(type_idx) not in (3, 4, 5):
                    continue  # only Scout/Demolisher/Interceptor
                try:
                    fx, fy = int(from_xy[0]), int(from_xy[1])
                except Exception:
                    continue
                if 0 <= fx < 28 and 0 <= fy < 14:
                    self.transit_pressure[(fx, fy)] = \
                        self.transit_pressure.get((fx, fy), 0.0) + 1.0
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
