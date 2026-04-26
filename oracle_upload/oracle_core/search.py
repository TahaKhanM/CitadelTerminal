"""Oracle search loop — picks the best action plan per turn.

Algorithm:
  1. Enumerate candidate plans (from enumerator.enumerate_candidates).
  2. Build a base_state_dict from game_state (state_adapter.adapt_game_state).
  3. Sample K opponent action plans from the opponent model.
  4. For each candidate:
       a. Deep-copy base_state_dict and apply the candidate's structure_actions.
          (Structure placement happens in the deploy phase, before action phase.)
       b. For each opponent sample:
            - Convert (my_mobile_actions, opp_mobile_actions) to deploy lists.
            - Call sim_rs.simulate_action_phase via sim_eval.evaluate_action_phase
              (or the lower-level helper for raw post-state).
            - Score the post-state with value.evaluate(post_state, my_player).
       c. Average the scores across opponent samples; that's the candidate's EU.
  5. Track best EU; check time-budget every iteration; return best-so-far on hit.

Time budget is HARD: ``time.time() > deadline`` checks inside the loop. Default
8 s leaves ~5 s margin under the 13 s SIGALRM watchdog.

Parallelism: optional ThreadPoolExecutor scoring. Disabled by default because
the Rust sim releases the GIL but each call is already <0.1 ms; thread overhead
dominates at small candidate counts. Enable via ``parallel_workers > 1`` for
larger sweeps (>200 candidates × 8 opp samples).

Throughput (measured locally on M1 mac, sim_rs PyO3 wheel, single-threaded):
    ~4,700 full per-candidate-rollout sims/s on a mid-game state with 40+
    structures (32 candidates × 3 opp samples = 96 sims in 20 ms). The dominant
    cost is the per-rollout deepcopy (~50 µs each); raw sim_rs alone is ~13 K
    sims/s on small states, ~3-5 K on mid-game.
    For an 8 s budget with k_opp_samples=8 that's ~4,000 (candidate × opp)
    rollouts, i.e. ~500 candidates fully evaluated. Larger candidate sets
    rely on the deadline cutoff to settle on the best-so-far.

Public API:
    select_best_plan(game_state, opponent_model, *, time_budget_s=8.0,
                     max_candidates=500, k_opp_samples=8, depth=1) -> ActionPlan
"""
from __future__ import annotations

import copy
import sys
import time
from dataclasses import dataclass, field
from typing import Any, Callable, Dict, List, Optional, Sequence, Tuple

# ---------------------------------------------------------------------------
# Soft-import the parallel modules. If the real ones aren't ready yet, define
# minimal stand-ins so this module is importable and testable on its own.
# This also lets unit tests inject mocks via monkeypatching the search module.
# ---------------------------------------------------------------------------

try:  # pragma: no cover — wired up once enumerator.py lands
    from .enumerator import (  # type: ignore
        ActionPlan as _RealActionPlan,
        enumerate_candidates as _real_enumerate_candidates,
        apply_to_game_state as _real_apply_to_game_state,
    )
    _ENUMERATOR_AVAILABLE = True
except ImportError:
    _RealActionPlan = None  # type: ignore
    _real_enumerate_candidates = None  # type: ignore
    _real_apply_to_game_state = None  # type: ignore
    _ENUMERATOR_AVAILABLE = False


try:  # pragma: no cover — wired up once value.py lands
    from .value import evaluate as _real_evaluate  # type: ignore
    _VALUE_AVAILABLE = True
except ImportError:
    _real_evaluate = None  # type: ignore
    _VALUE_AVAILABLE = False


try:  # pragma: no cover — wired up once opponent_model.py lands
    from .opponent_model import OpponentModel as _RealOpponentModel  # type: ignore
    _OPPONENT_MODEL_AVAILABLE = True
except ImportError:
    _RealOpponentModel = None  # type: ignore
    _OPPONENT_MODEL_AVAILABLE = False


# Always-available infrastructure (these modules already exist):
from .sim_eval import (
    UNIT_NAME_TO_IDX,
    _apply_deploys_inplace,  # internal but bit-exact with engine
    _default_config_path,
    _get_sim_rs,
    _bridge_or_pysim,
    py_state_to_dict,
)
from .state_adapter import adapt_game_state


# ---------------------------------------------------------------------------
# ActionPlan fallback — used only when enumerator.py hasn't shipped yet.
# Exposed at module level so tests / callers always see a usable shape.
# ---------------------------------------------------------------------------

@dataclass
class _FallbackActionPlan:
    """Stand-in for enumerator.ActionPlan.

    Structure / mobile actions follow the agreed schema:
      structure_actions: list of (unit_shorthand, [x, y], upgraded_bool)
                         e.g. ("WALL", [13, 13], False)
      mobile_actions:    list of (unit_shorthand, [x, y], count)
                         e.g. ("SCOUT", [13, 0], 5)
    """
    structure_actions: List[Tuple[str, List[int], bool]] = field(default_factory=list)
    mobile_actions: List[Tuple[str, List[int], int]] = field(default_factory=list)
    description: str = ""
    sp_cost: float = 0.0
    mp_cost: float = 0.0


ActionPlan = _RealActionPlan if _RealActionPlan is not None else _FallbackActionPlan


# ---------------------------------------------------------------------------
# Public API
# ---------------------------------------------------------------------------

def select_best_plan(
    game_state,
    opponent_model: Any,
    *,
    time_budget_s: float = 8.0,
    max_candidates: int = 500,
    k_opp_samples: int = 8,
    depth: int = 1,
    my_player: int = 1,
    debug_log: Optional[Callable[[str], None]] = None,
    # Test/integration hooks — allow injection without monkeypatching:
    _enumerate_fn: Optional[Callable[..., List[Any]]] = None,
    _evaluate_fn: Optional[Callable[..., float]] = None,
    _adapt_fn: Optional[Callable[..., Dict[str, Any]]] = None,
    _sim_fn: Optional[Callable[..., Dict[str, Any]]] = None,
    _config_path: Optional[str] = None,
) -> Any:
    """The brain. Search wide over plans, deep over opp uncertainty.

    Returns the best ActionPlan to apply. NEVER raises — on internal failure
    it falls back to a no-op plan. The wrapping algo_strategy is responsible
    for actually applying the plan via apply_to_game_state.

    Args:
        game_state: gamelib.GameState (live competition object).
        opponent_model: object with ``sample_actions(game_state, k)`` method
            returning a list of ActionPlan-shaped objects.
        time_budget_s: hard wall-clock cap in seconds.
        max_candidates: cap on enumerator output (passed through).
        k_opp_samples: how many opponent action samples to average over.
        depth: 1 = single-turn rollout. 2+ reserved for future deeper search.
        my_player: 1 = bottom side (default), 2 = top side.
        debug_log: optional callable receiving diagnostic strings.
        _enumerate_fn / _evaluate_fn / _adapt_fn / _sim_fn / _config_path:
            test injection hooks. Override the module-level functions.
    """
    deadline = time.time() + time_budget_s

    # Resolve the dependency functions — prefer injected, then real, then
    # raise a clear error if neither is wired up. Tests inject; production
    # uses the real modules once they land.
    enumerate_fn = _enumerate_fn or _real_enumerate_candidates
    evaluate_fn = _evaluate_fn or _real_evaluate
    adapt_fn = _adapt_fn or adapt_game_state
    config_path = _config_path or _default_config_path()

    if enumerate_fn is None:
        return _log_and_return_noop(
            debug_log, "enumerator.enumerate_candidates not available"
        )
    if evaluate_fn is None:
        return _log_and_return_noop(
            debug_log, "value.evaluate not available"
        )

    # ---- 1. Enumerate candidates ------------------------------------------
    try:
        candidates = enumerate_fn(game_state, max_candidates=max_candidates)
    except Exception as e:  # noqa: BLE001
        return _log_and_return_noop(
            debug_log, f"enumerator raised {type(e).__name__}: {e}"
        )

    if debug_log is not None:
        debug_log(f"[oracle] enumerated {len(candidates)} candidates")

    if not candidates:
        return _make_noop("empty_no_op")

    # ---- 2. Sample opponent actions ---------------------------------------
    opp_samples: List[Any] = []
    try:
        opp_samples = list(opponent_model.sample_actions(game_state, k=k_opp_samples)) \
            if opponent_model is not None else []
    except Exception as e:  # noqa: BLE001
        if debug_log is not None:
            debug_log(f"[oracle] opp_model raised {type(e).__name__}: {e}; "
                      "falling back to single idle opp")
        opp_samples = []

    if not opp_samples:
        # No model data — assume opponent does nothing this turn.
        opp_samples = [_make_noop("opp_unknown_idle")]

    # ---- 3. Adapt the live game_state to a sim_rs schema dict --------------
    try:
        base_state_dict = adapt_fn(
            game_state, my_player=my_player,
            turn=getattr(game_state, "turn_number", 0),
        )
    except Exception as e:  # noqa: BLE001
        return _log_and_return_noop(
            debug_log, f"state_adapter raised {type(e).__name__}: {e}"
        )

    # ---- 4. Score each candidate ------------------------------------------
    best_plan = candidates[0]
    best_score = float("-inf")
    n_evaluated = 0
    n_sims_total = 0
    t_loop_start = time.time()

    for cand in candidates:
        # HARD time-budget check.
        if time.time() > deadline:
            if debug_log is not None:
                debug_log(
                    f"[oracle] time budget hit after {n_evaluated}/"
                    f"{len(candidates)} candidates ({n_sims_total} sims)"
                )
            break

        try:
            score, n_sims = _score_candidate(
                base_state_dict=base_state_dict,
                plan=cand,
                opp_samples=opp_samples,
                config_path=config_path,
                evaluate_fn=evaluate_fn,
                my_player=my_player,
                depth=depth,
                deadline=deadline,
                sim_fn_override=_sim_fn,
            )
        except Exception as e:  # noqa: BLE001
            # Per-candidate failure shouldn't kill the whole search — log
            # and skip. Defaults to negative infinity so it never wins.
            if debug_log is not None:
                debug_log(
                    f"[oracle] scoring failed for "
                    f"'{getattr(cand, 'description', '?')}': "
                    f"{type(e).__name__}: {e}"
                )
            score, n_sims = float("-inf"), 0

        n_sims_total += n_sims
        n_evaluated += 1
        if score > best_score:
            best_score = score
            best_plan = cand

    elapsed_ms = (time.time() - t_loop_start) * 1000.0
    if debug_log is not None:
        debug_log(
            f"[oracle] picked '{getattr(best_plan, 'description', '?')}' "
            f"score={best_score:.3f} after {n_evaluated} candidates / "
            f"{n_sims_total} sims in {elapsed_ms:.1f} ms"
        )
    return best_plan


# ---------------------------------------------------------------------------
# Per-candidate scoring
# ---------------------------------------------------------------------------

def _score_candidate(
    *,
    base_state_dict: Dict[str, Any],
    plan: Any,
    opp_samples: Sequence[Any],
    config_path: str,
    evaluate_fn: Callable[..., float],
    my_player: int,
    depth: int,
    deadline: float,
    sim_fn_override: Optional[Callable[..., Dict[str, Any]]] = None,
) -> Tuple[float, int]:
    """Sim plan vs each opp sample, return (avg_score, n_sims)."""
    opp_player = 2 if my_player == 1 else 1

    # 1. Apply structure actions ONCE per candidate (they don't depend on opp).
    #    Use deepcopy so opp samples each see the same post-structure state
    #    without cross-contamination.
    candidate_state = copy.deepcopy(base_state_dict)
    _apply_structures_inplace(
        candidate_state,
        getattr(plan, "structure_actions", []) or [],
        my_player=my_player,
    )
    # Deduct SP for the candidate's structures (best-effort — if the enumerator
    # already accounts, sp_cost=0 and this is a no-op).
    sp_cost = float(getattr(plan, "sp_cost", 0.0))
    if sp_cost > 0:
        side = "p1" if my_player == 1 else "p2"
        candidate_state[side]["sp"] = max(
            0.0, float(candidate_state[side]["sp"]) - sp_cost
        )

    # 2. Loop over opponent samples.
    scores: List[float] = []
    sim_fn = sim_fn_override or _run_action_phase

    my_mobiles = _plan_to_deploys(plan, is_opp=False)
    for opp_plan in opp_samples:
        if time.time() > deadline:
            break
        opp_mobiles = _plan_to_deploys(opp_plan, is_opp=True)

        # Per-opp deepcopy so deploys/MP deductions don't leak between samples.
        rollout_state = copy.deepcopy(candidate_state)

        # ALSO apply opponent structure actions — most opp models will
        # produce mobile-only plans, but if they predict structures we
        # should respect them.
        opp_structs = getattr(opp_plan, "structure_actions", []) or []
        if opp_structs:
            _apply_structures_inplace(
                rollout_state, opp_structs, my_player=opp_player
            )

        # Apply mobile deploys (deducts MP, appends Mobile entries).
        try:
            _apply_deploys_inplace(
                rollout_state,
                my_player=my_player,
                opp_player=opp_player,
                my_deploys=my_mobiles,
                opp_deploys=opp_mobiles,
            )
        except Exception:  # noqa: BLE001
            # Bad unit name etc. — score this rollout as -inf, continue.
            scores.append(float("-inf"))
            continue

        try:
            post_state = sim_fn(rollout_state, config_path)
        except Exception:  # noqa: BLE001
            scores.append(float("-inf"))
            continue

        try:
            s = float(evaluate_fn(post_state, my_player=my_player))
        except Exception:  # noqa: BLE001
            scores.append(float("-inf"))
            continue
        scores.append(s)

    if not scores:
        return float("-inf"), 0

    # Filter -inf scores (failed rollouts) so a single bad sim doesn't
    # tank the average. If ALL rollouts failed, return -inf (caller skips).
    finite = [s for s in scores if s != float("-inf")]
    if not finite:
        return float("-inf"), len(scores)

    avg = sum(finite) / len(finite)
    return avg, len(scores)


def _run_action_phase(state_dict: Dict[str, Any], config_path: str) -> Dict[str, Any]:
    """Run one action phase. Tries sim_rs first, then bridge/pysim."""
    sim_rs = _get_sim_rs()
    if sim_rs is not None:
        return sim_rs.simulate_action_phase_py(state_dict, config_path)
    return _bridge_or_pysim(state_dict, config_path)


# ---------------------------------------------------------------------------
# Plan -> sim deploy/structure conversions
# ---------------------------------------------------------------------------

def _plan_to_deploys(plan: Any, is_opp: bool = False) -> List[Tuple[str, List[int]]]:
    """Convert ActionPlan.mobile_actions into sim_eval's deploy format.

    mobile_actions schema: list of (unit_shorthand, [x, y], count).
    sim_eval wants: list of (unit, [x, y]) — one entry per unit.
    """
    out: List[Tuple[str, List[int]]] = []
    actions = getattr(plan, "mobile_actions", []) or []
    for entry in actions:
        try:
            unit, loc, count = entry
        except (ValueError, TypeError):
            continue
        try:
            count = int(count)
        except (ValueError, TypeError):
            count = 1
        for _ in range(max(0, count)):
            out.append((unit, [int(loc[0]), int(loc[1])]))
    return out


def _apply_structures_inplace(
    state_dict: Dict[str, Any],
    structure_actions: Sequence[Tuple[str, str, Sequence[int]]],
    *,
    my_player: int,
) -> None:
    """Insert structure_actions into state_dict["structures"] in place.

    Each entry is (action_type, unit_shorthand, [x, y]) where action_type
    is in {"spawn", "upgrade", "remove"} — matches the enumerator's schema.

    - "spawn":   add a new base structure (or no-op if tile occupied).
    - "upgrade": mark an existing structure at [x,y] as upgraded; bump HP.
    - "remove":  remove an existing structure at [x,y] (refund handled in
                 action phase by the engine if there is one — sim_rs's
                 simulate_action_phase will see the missing structure).
    """
    if not structure_actions:
        return

    structs = state_dict.setdefault("structures", [])
    # Build a quick (xy)->index lookup so upgrades / removes find their target.
    xy_to_idx: Dict[Tuple[int, int], int] = {}
    for i, s in enumerate(structs):
        try:
            xy_to_idx[(int(s["xy"][0]), int(s["xy"][1]))] = i
        except (KeyError, IndexError, TypeError):
            continue

    # Find a UID seed safely above any existing.
    next_uid = 1
    for s in structs:
        try:
            v = int(s.get("uid", 0))
            if v >= next_uid:
                next_uid = v + 1
        except (TypeError, ValueError):
            continue

    # Default structure HPs (matches Citadel snapshot's startHealth).
    DEFAULT_STRUCTURE_HP = {0: 60.0, 1: 1.0, 2: 75.0}
    DEFAULT_STRUCTURE_HP_UPGRADED = {0: 120.0, 1: 40.0, 2: 100.0}

    # Track removals to do at end (so we don't shift indices mid-loop)
    indices_to_remove: List[int] = []

    for entry in structure_actions:
        # Schema: (action_type, unit_shorthand, [x, y])
        try:
            action_type, unit, loc = entry
        except (ValueError, TypeError):
            continue
        action_type = str(action_type).lower()
        type_idx = UNIT_NAME_TO_IDX.get(str(unit).upper())
        try:
            x, y = int(loc[0]), int(loc[1])
        except (TypeError, IndexError, ValueError):
            continue
        existing_idx = xy_to_idx.get((x, y))

        if action_type == "remove":
            if existing_idx is not None:
                indices_to_remove.append(existing_idx)
            continue

        if action_type == "upgrade":
            if existing_idx is not None:
                existing = structs[existing_idx]
                if not existing.get("upgraded", False):
                    existing["upgraded"] = True
                    eti = int(existing.get("type_idx", type_idx if type_idx is not None else 0))
                    up_hp = DEFAULT_STRUCTURE_HP_UPGRADED.get(eti, 75.0)
                    existing["hp"] = max(float(existing.get("hp", 0.0)), up_hp)
            continue

        # action_type == "spawn" (default for any unknown)
        if type_idx is None or type_idx not in (0, 1, 2):
            continue
        if existing_idx is not None:
            # Tile already occupied — silently skip (engine would too)
            continue

        hp = DEFAULT_STRUCTURE_HP.get(int(type_idx), 60.0)
        structs.append({
            "xy": [x, y],
            "type_idx": int(type_idx),
            "upgraded": False,
            "hp": float(hp),
            "uid": str(next_uid),
            "player": int(my_player),
            "turn_start_removal": None,
        })
        xy_to_idx[(x, y)] = len(structs) - 1
        next_uid += 1

    # Apply removals last (process highest-index first so we don't shift earlier indices)
    for idx in sorted(set(indices_to_remove), reverse=True):
        if 0 <= idx < len(structs):
            try:
                del structs[idx]
            except IndexError:
                pass


# ---------------------------------------------------------------------------
# No-op / fallback helpers
# ---------------------------------------------------------------------------

def _make_noop(description: str) -> Any:
    """Return a no-op ActionPlan in whichever class is currently active."""
    return ActionPlan(description=description)  # type: ignore[call-arg]


def _log_and_return_noop(
    debug_log: Optional[Callable[[str], None]], reason: str
) -> Any:
    if debug_log is not None:
        debug_log(f"[oracle] {reason} — returning no-op plan")
    else:
        # Print to stderr so a missing debug_log isn't silent on the live
        # server (engine surfaces stderr as replay debug).
        print(f"[oracle] {reason} — returning no-op plan", file=sys.stderr)
    return _make_noop(f"failed:{reason[:40]}")


__all__ = [
    "ActionPlan",
    "select_best_plan",
]
