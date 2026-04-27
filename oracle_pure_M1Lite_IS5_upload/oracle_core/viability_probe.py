"""Sim-based offensive viability probe for Variant B.

Purpose: Detect whether a candidate defense template would create a
"trap" state where our mobile units cannot reach opp territory. Uses
sim_rs as the path oracle (NOT BFS — see §16 of CONTEXT_HANDOFF.md
for why BFS is over-conservative).

Mechanism:
  1. Take a candidate plan's structure ops.
  2. Apply them to a fresh sim state.
  3. Add 5 synthetic probe scouts at center launcher (14, 0).
  4. Run sim_rs simulate_action_phase.
  5. Count breaches (via SP delta — engine awards +1 SP per breached mobile).
  6. Return breach count. 0 = trap detected.

Why probe scouts at (14, 0)?
  - Center launcher tests both wall-row gaps at (12, 12) and (15, 12).
  - If center scouts can't breach, nothing reasonable can.
  - The actual trap (m1_lite vs suchir-g, not-tnb) shows ALL launchers
    fail when both gaps are sealed by supports — so a single probe is
    sufficient to detect the universal trap.

Why 5 scouts (not 1)?
  - 1 scout might die to a single turret hit (not a trap, just bad luck).
  - 5 scouts are statistically robust: if NONE of 5 breach, the path is
    truly blocked, not just stochastic loss.

Cost: ~0.2 ms per probe call. With caching per defense template, total
overhead per turn is ~3 ms (13 templates × 0.2 ms).
"""
from __future__ import annotations

from typing import Any, Dict, Iterable, Optional, Tuple

from .constants import (
    ARENA_HALF_Y, MOBILE_HP_DEFAULT, SCOUT_IDX, STRUCTURE_IDXS,
    cost_for_idx, starting_hp_for_idx,
)
from .plan import (
    ActionPlan, KIND_REMOVE, KIND_SPAWN_MOBILE, KIND_SPAWN_STRUCT,
    KIND_UPGRADE,
)


# Default probe parameters — tuned in Variant B
PROBE_LAUNCHER = (14, 0)            # BR center; tests both wall-row gaps
PROBE_SCOUT_COUNT = 5               # statistically robust without overkill
PROBE_TARGET_EDGE = 1               # TL (BR-spawned bottom mobile target)


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


def _apply_defense_only(plan: ActionPlan, state_dict: Dict[str, Any],
                        my_player: int, config) -> int:
    """Apply ONLY structure / upgrade ops from plan. Skip mobiles. Returns next uid.

    Mirrors apply_to_state_dict but defensively skips KIND_SPAWN_MOBILE
    so the probe state contains only the candidate's defense layout.
    """
    side_key = "p1" if my_player == 1 else "p2"
    next_uid = _max_uid_in_state(state_dict) + 1
    by_xy = {tuple(s["xy"]): s for s in state_dict.get("structures", [])}
    sp_remaining = float(state_dict[side_key]["sp"])

    # 1. Spawn structures
    for op in plan.ops:
        if op.kind != KIND_SPAWN_STRUCT:
            continue
        if op.unit_idx not in STRUCTURE_IDXS:
            continue
        xy = (int(op.xy[0]), int(op.xy[1]))
        if xy in by_xy:
            continue
        cost = cost_for_idx(config, op.unit_idx)
        if sp_remaining + 1e-9 < cost:
            continue
        sp_remaining -= cost
        hp = starting_hp_for_idx(config, op.unit_idx, upgrade=False)
        new_struct = {
            "xy": [xy[0], xy[1]],
            "type_idx": int(op.unit_idx),
            "upgraded": False,
            "hp": float(hp),
            "uid": str(next_uid),
            "player": int(my_player),
            "turn_start_removal": None,
        }
        state_dict.setdefault("structures", []).append(new_struct)
        by_xy[xy] = new_struct
        next_uid += 1

    # 2. Upgrades
    for op in plan.ops:
        if op.kind != KIND_UPGRADE:
            continue
        xy = (int(op.xy[0]), int(op.xy[1]))
        s = by_xy.get(xy)
        if s is None or s.get("player") != my_player:
            continue
        if s.get("upgraded"):
            continue
        idx = int(s.get("type_idx", -1))
        if idx not in STRUCTURE_IDXS:
            continue
        cost = cost_for_idx(config, idx, upgrade=True)
        if sp_remaining + 1e-9 < cost:
            continue
        sp_remaining -= cost
        s["upgraded"] = True
        upg_hp = starting_hp_for_idx(config, idx, upgrade=True)
        base_hp = starting_hp_for_idx(config, idx, upgrade=False)
        if base_hp > 0:
            cur_frac = float(s.get("hp", base_hp)) / base_hp
        else:
            cur_frac = 1.0
        s["hp"] = float(upg_hp * max(0.0, min(1.0, cur_frac)))

    # 3. Removals (IS5). For PROBE purposes the structure is physically
    # removed from the probe state — this models the next-turn state
    # the engine will produce after turn_start_removal completes.
    # Note: in the actual sim and apply_to_state_dict the engine semantics
    # (turn_start_removal flag, removed at start of next turn, refund) are
    # preserved. The probe is only forecasting "what would offense look
    # like next turn if I remove this structure NOW?"
    remove_xys = [
        (int(op.xy[0]), int(op.xy[1])) for op in plan.ops
        if op.kind == KIND_REMOVE
    ]
    if remove_xys:
        remove_set = set(remove_xys)
        state_dict["structures"] = [
            s for s in state_dict.get("structures", [])
            if not (tuple(s["xy"]) in remove_set
                    and int(s.get("player", 0)) == my_player)
        ]

    state_dict[side_key]["sp"] = float(round(sp_remaining, 1))
    return next_uid


def probe_offense_viability(
    base_state: Dict[str, Any],
    defense_plan: ActionPlan,
    config,
    sim_runner,            # callable: (state_dict) -> post_state_dict
    *,
    my_player: int = 1,
    n_probe_scouts: int = PROBE_SCOUT_COUNT,
    probe_launcher: Tuple[int, int] = PROBE_LAUNCHER,
    strip_opp_structures: bool = True,
    extra_remove_xys: Optional[Iterable[Tuple[int, int]]] = None,
) -> int:
    """Return the number of probe scouts that successfully breached.

    0 = trap detected (no scout reached opp edge).
    n_probe_scouts = full success.

    Args:
        base_state: pre-deploy state dict (will be deep-copied).
        defense_plan: ActionPlan with defense ops; mobile ops are IGNORED
            so we test the defense's effect on offensive viability.
        config: live game config dict.
        sim_runner: callable(state_dict) -> post_state_dict. Pass a closure
            wrapping sim_rs.simulate_action_phase_py(state, config_path).
        my_player: 1 or 2.
        n_probe_scouts: how many probe scouts to spawn.
        probe_launcher: (x, y) spawn tile for probe scouts.
        strip_opp_structures: if True (default), remove all opp structures
            from the probe state. This isolates the test to "does MY
            defense block MY offense?". Without this, the probe also
            measures opp's defensive strength, which would falsely flag
            stronger opps as creating traps in my own territory.
        extra_remove_xys: iterable of (x, y) tiles whose structures should
            be PHYSICALLY removed from the probe state before running the
            sim. Used by IS5 removal-template generation: the engine's
            actual KIND_REMOVE op only sets turn_start_removal (which
            doesn't unblock paths within the same turn — Citadel
            turns_required_to_remove >= 2), but the probe needs to model
            the post-removal state to test whether removing the structure
            *would* restore offensive viability next turn. Only structures
            owned by my_player are removed.

    Returns:
        Integer count of scouts that breached (0 to n_probe_scouts).
    """
    # Deep-copy via _fast_copy_state-like primitive
    probe = _shallow_clone_state(base_state)

    # Strip opp structures so the probe ONLY measures the impact of MY
    # defense layout (existing + planned) on MY offense. Without this,
    # heavy opp defense would falsely make probes return 0, marking
    # every plan as trap-prone (uniform penalty = no signal).
    if strip_opp_structures:
        opp_player = 2 if my_player == 1 else 1
        probe["structures"] = [s for s in probe.get("structures", [])
                                if int(s.get("player", 0)) != opp_player]

    # IS5: physically remove specific structures from the probe state.
    # This models "if I remove X NOW, what would happen?" — i.e. the
    # next-turn state after the engine's pending removal completes.
    if extra_remove_xys:
        remove_set = {(int(x), int(y)) for x, y in extra_remove_xys}
        if remove_set:
            probe["structures"] = [
                s for s in probe.get("structures", [])
                if not (tuple(s["xy"]) in remove_set
                        and int(s.get("player", 0)) == my_player)
            ]

    next_uid = _apply_defense_only(defense_plan, probe, my_player, config)

    # Add probe scouts
    side_key = "p1" if my_player == 1 else "p2"
    sp_pre = float(probe[side_key]["sp"])

    x, y = probe_launcher
    # Determine target edge from spawn tile (consistent with plan.py)
    if y == 13 - x:
        target_edge = 0  # BL → TR
    elif y == x - 14:
        target_edge = 1  # BR → TL
    else:
        target_edge = 0

    for i in range(n_probe_scouts):
        probe.setdefault("mobiles", []).append({
            "xy": [int(x), int(y)],
            "type_idx": int(SCOUT_IDX),
            "hp": float(MOBILE_HP_DEFAULT.get(SCOUT_IDX, 15.0)),
            "shield": 0.0,
            "uid": str(next_uid + i),
            "player": int(my_player),
            "spawn_xy": [int(x), int(y)],
            "target_edge": int(target_edge),
            "steps_taken": 0,
            "move_buildup": 0.0,
            "last_move": 0,
            "finished_navigating": False,
            "reached_target": False,
            "breached": False,
        })

    # Run sim
    try:
        post = sim_runner(probe)
    except Exception:
        # If sim fails, default to "non-trap" (safe — don't penalize)
        return n_probe_scouts

    # Count breaches via SP delta
    # Engine awards +1 SP per mobile that breaches opp edge (see CLAUDE.md
    # "Bonus SP" line). So sp_post - sp_pre = breach count.
    sp_post = float(post[side_key]["sp"])
    breached = int(round(sp_post - sp_pre))
    # Clamp to valid range
    return max(0, min(n_probe_scouts, breached))


def _shallow_clone_state(s: Dict[str, Any]) -> Dict[str, Any]:
    """Shallow clone matching _fast_copy_state in search.py."""
    return {
        "turn": s["turn"],
        "p1": dict(s["p1"]),
        "p2": dict(s["p2"]),
        "structures": [{**ss, "xy": list(ss["xy"])} for ss in s.get("structures", [])],
        "mobiles": [
            {**mm, "xy": list(mm["xy"]),
             "spawn_xy": list(mm.get("spawn_xy", mm["xy"]))}
            for mm in s.get("mobiles", [])
        ],
    }


__all__ = [
    "probe_offense_viability",
    "PROBE_LAUNCHER",
    "PROBE_SCOUT_COUNT",
]
