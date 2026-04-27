"""Path-prediction probe for Variant J3.

Purpose: Score a candidate defense by running a sim where the OPPONENT
spawns mobiles at their RECENT launcher positions (tracked via
on_action_frame across turns). Count how many opp mobiles are killed
before they breach. Return the kill ratio.

A defense that intercepts opp's actual attack pattern scores higher.

Mechanism:
  1. Take a candidate plan's structure ops applied to a fresh sim state.
  2. Add synthetic OPP scouts at the top-3 recent launcher positions,
     N scouts per launcher.
  3. Run sim_rs simulate_action_phase.
  4. Count opp breaches via opp's SP delta (engine awards +1 SP per
     breached mobile, same convention as the offense viability probe).
  5. Return kill_ratio = 1.0 - (breached / spawned).

Why this is different from VB's offense viability probe:
  - VB probes OUR offense vs OUR defense (does our defense block US?)
  - J3 probes OUR defense vs OPP's offense (does our defense block THEM?)

The signal is empirical: observe → predict → score. The probe simulates
opp's ACTUAL attack pattern, not a generic archetype assumption.

Cost: ~0.2 ms per probe call. With caching per defense template, total
overhead per turn is ~3 ms (top ~15 unique defense templates × 0.2 ms).
"""
from __future__ import annotations

from typing import Any, Dict, List, Optional, Sequence, Tuple

from .constants import (
    MOBILE_HP_DEFAULT, SCOUT_IDX,
)
from .plan import (
    ActionPlan, KIND_SPAWN_MOBILE,
)
from .viability_probe import (
    _apply_defense_only, _shallow_clone_state,
)


# Default probe parameters
PROBE_TOP_K_LAUNCHERS = 3       # use top-3 recent opp launchers
PROBE_SCOUTS_PER_LAUNCHER = 5   # 5 scouts per launcher (statistically robust)
PROBE_NEUTRAL_SCORE = 0.5       # returned when no launcher data
PROBE_MAX_SAMPLES = 15          # cap total sample count


def _opp_target_edge(spawn_xy: Tuple[int, int]) -> int:
    """Compute opp mobile target edge from spawn tile.

    Canonical SimCore convention from map.py / sim_rs/src/map.rs:
        0 = TOP_RIGHT
        1 = TOP_LEFT
        2 = BOTTOM_LEFT
        3 = BOTTOM_RIGHT

    Top-player spawns target the bottom edges:
      - x < 14  (TL side) → target BOTTOM_RIGHT (3)
      - x >= 14 (TR side) → target BOTTOM_LEFT (2)

    Mirror of plan._spawn_to_target_edge for top player. The convention
    aligns with viability_probe (which uses 0/1 for bottom→top targets).
    """
    x, _y = spawn_xy
    return 3 if x < 14 else 2


def _max_uid_in_state(state_dict: Dict[str, Any]) -> int:
    m = 1_000_000
    for u in state_dict.get("structures", []) + state_dict.get("mobiles", []):
        try:
            v = int(u.get("uid", 0))
            if v > m:
                m = v
        except (TypeError, ValueError):
            continue
    return m


def probe_path_interception(
    base_state: Dict[str, Any],
    defense_plan: ActionPlan,
    opp_recent_launchers: Sequence[Tuple[Tuple[int, int], float]],
    config,
    sim_runner,
    *,
    my_player: int = 1,
    n_scouts_per_launcher: int = PROBE_SCOUTS_PER_LAUNCHER,
    top_k: int = PROBE_TOP_K_LAUNCHERS,
    strip_existing_opp_mobiles: bool = True,
) -> float:
    """Return the kill ratio (0.0 .. 1.0) of probe-spawned opp mobiles
    against `defense_plan`'s post-deploy state.

    1.0 = perfect interception (none breach).
    0.0 = total failure (all breach).

    Args:
        base_state: pre-deploy state dict (will be shallow-copied).
        defense_plan: ActionPlan with defense ops; mobile ops are IGNORED
            (we test "does defense X intercept opp's pattern?", not how
            our offense interacts).
        opp_recent_launchers: sequence of ((x, y), weight) pairs describing
            opp's recently-observed launcher positions, in descending
            weight order. Only the top `top_k` are used.
        config: live game config dict.
        sim_runner: callable(state_dict) -> post_state_dict.
        my_player: 1 or 2 (defaults to 1, we are bottom-side).
        n_scouts_per_launcher: probe scouts per launcher (default 5).
        top_k: max launcher tiles to probe (default 3).
        strip_existing_opp_mobiles: if True, strip pre-existing opp
            mobiles from the probe state so the test isolates "would
            opp's NEW mobiles break through?".

    Returns:
        Kill ratio in [0.0, 1.0]; PROBE_NEUTRAL_SCORE if no launcher
        data is available (signal degenerate).
    """
    if not opp_recent_launchers:
        return PROBE_NEUTRAL_SCORE

    opp_player = 2 if my_player == 1 else 1

    # Shallow-clone state, apply defense (our side), strip pre-existing
    # opp mobiles from the cloned state so the probe-spawned scouts are
    # the only opp mobiles in flight.
    probe = _shallow_clone_state(base_state)
    if strip_existing_opp_mobiles:
        probe["mobiles"] = [m for m in probe.get("mobiles", [])
                            if int(m.get("player", 0)) != opp_player]

    next_uid = _apply_defense_only(defense_plan, probe, my_player, config)

    # Pre-sim opp SP (used to count breaches via SP delta)
    opp_side_key = "p1" if opp_player == 1 else "p2"
    opp_sp_pre = float(probe[opp_side_key]["sp"])

    # Seed probe scouts at the top-k launcher positions.
    spawned = 0
    scout_hp = float(MOBILE_HP_DEFAULT.get(SCOUT_IDX, 15.0))
    selected = list(opp_recent_launchers)[:top_k]
    for launcher_entry in selected:
        try:
            launcher_xy, _weight = launcher_entry
            lx, ly = int(launcher_xy[0]), int(launcher_xy[1])
        except (ValueError, TypeError, IndexError):
            continue
        target_edge = _opp_target_edge((lx, ly))
        for _ in range(n_scouts_per_launcher):
            if spawned >= PROBE_MAX_SAMPLES:
                break
            probe.setdefault("mobiles", []).append({
                "xy": [lx, ly],
                "type_idx": int(SCOUT_IDX),
                "hp": scout_hp,
                "shield": 0.0,
                "uid": str(next_uid + spawned),
                "player": int(opp_player),
                "spawn_xy": [lx, ly],
                "target_edge": int(target_edge),
                "steps_taken": 0,
                "move_buildup": 0.0,
                "last_move": 0,
                "finished_navigating": False,
                "reached_target": False,
                "breached": False,
            })
            spawned += 1
        if spawned >= PROBE_MAX_SAMPLES:
            break

    if spawned == 0:
        return PROBE_NEUTRAL_SCORE

    # Run sim
    try:
        post = sim_runner(probe)
    except Exception:
        # If sim fails, return neutral (don't score)
        return PROBE_NEUTRAL_SCORE

    # Count breaches via opp SP delta (engine awards +1 SP per breach).
    opp_sp_post = float(post[opp_side_key]["sp"])
    breached = int(round(opp_sp_post - opp_sp_pre))
    breached = max(0, min(spawned, breached))

    kill_ratio = 1.0 - (breached / float(spawned))
    return float(kill_ratio)


__all__ = [
    "probe_path_interception",
    "PROBE_TOP_K_LAUNCHERS",
    "PROBE_SCOUTS_PER_LAUNCHER",
    "PROBE_NEUTRAL_SCORE",
    "PROBE_MAX_SAMPLES",
]
