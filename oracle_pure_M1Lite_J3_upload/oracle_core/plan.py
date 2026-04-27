"""ActionPlan dataclass + utilities to apply / serialize plans.

A plan is a typed sequence of operations that the search evaluates as
a unit. The plan is feasible iff total SP cost ≤ available SP and
total MP cost ≤ available MP and every tile is legal.

We keep this representation orthogonal from gamelib so the same plan
can be:
  - APPLIED to a live gamelib.GameState (in algo_strategy)
  - APPLIED to a sim_rs state dict (in search.py for simulation)
  - SCORED for cost / feasibility (in enumerator.py)
"""
from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Optional, Sequence, Tuple, Union

from .constants import (
    ARENA_DIM, ARENA_HALF_Y, MOBILE_IDXS, STRUCTURE_IDXS, SP_RES, MP_RES,
    REFUND_BASE, REFUND_UPGRADED, cost_for_idx, shorthand_for_idx,
    starting_hp_for_idx, MOBILE_HP_DEFAULT,
)

# Operation kinds
KIND_SPAWN_STRUCT = "spawn_struct"   # Place a structure (Wall/Support/Turret)
KIND_UPGRADE = "upgrade"             # Upgrade an existing or just-spawned structure
KIND_REMOVE = "remove"               # Mark structure for removal (refund)
KIND_SPAWN_MOBILE = "spawn_mobile"   # Spawn N copies of a mobile at a tile


@dataclass
class Op:
    """One typed operation in a plan."""
    kind: str
    unit_idx: int            # 0..5; for KIND_REMOVE/UPGRADE this is unused (-1 ok)
    xy: Tuple[int, int]
    count: int = 1           # only for KIND_SPAWN_MOBILE; structures always 1


@dataclass
class ActionPlan:
    """A complete plan for one turn."""
    name: str
    ops: List[Op] = field(default_factory=list)
    sp_cost: float = 0.0
    mp_cost: float = 0.0
    # Optional metadata for telemetry/debug.
    archetype: str = ""
    # Variant B: probe-derived offensive viability.
    # None = not yet computed; otherwise integer [0, n_probe_scouts].
    # 0 = trap-prone defense (probe scouts couldn't reach opp territory).
    # Used by value.evaluate to apply a soft penalty.
    defense_viability: Optional[int] = None

    def add_struct(self, unit_idx: int, x: int, y: int):
        self.ops.append(Op(KIND_SPAWN_STRUCT, unit_idx, (x, y)))

    def add_upgrade(self, x: int, y: int):
        self.ops.append(Op(KIND_UPGRADE, -1, (x, y)))

    def add_remove(self, x: int, y: int):
        self.ops.append(Op(KIND_REMOVE, -1, (x, y)))

    def add_mobile(self, unit_idx: int, x: int, y: int, count: int = 1):
        self.ops.append(Op(KIND_SPAWN_MOBILE, unit_idx, (x, y), count))

    def has_mobiles(self) -> bool:
        return any(op.kind == KIND_SPAWN_MOBILE for op in self.ops)

    def mobile_ops(self) -> List[Op]:
        return [op for op in self.ops if op.kind == KIND_SPAWN_MOBILE]

    def structure_ops(self) -> List[Op]:
        return [op for op in self.ops if op.kind in
                (KIND_SPAWN_STRUCT, KIND_UPGRADE, KIND_REMOVE)]

    def __repr__(self):
        return (f"<Plan {self.name!r} ops={len(self.ops)} "
                f"sp={self.sp_cost:.1f} mp={self.mp_cost:.1f} "
                f"arch={self.archetype!r}>")


# ---------------------------------------------------------------------------
# Cost computation
# ---------------------------------------------------------------------------

def compute_costs(plan: ActionPlan, config) -> Tuple[float, float]:
    """Compute total (sp_cost, mp_cost) given a plan and live config.

    Refunds (KIND_REMOVE) are NOT subtracted here — the engine returns
    refund SP only after a 2/3-turn delay, so the search treats remove
    as a 0-cost intent (the actual SP gain is accounted for in the
    next turn's adapt_game_state).

    Upgrades that target tiles also placed in this same plan are treated
    as paying both the spawn cost AND the upgrade cost.
    """
    sp = 0.0
    mp = 0.0
    for op in plan.ops:
        if op.kind == KIND_SPAWN_STRUCT:
            sp += cost_for_idx(config, op.unit_idx, upgrade=False)
        elif op.kind == KIND_UPGRADE:
            # We don't know the unit_idx by xy without looking at the state.
            # Caller must set unit_idx via add_upgrade_typed when known;
            # otherwise estimate the average upgrade cost (defensive).
            sp += op.count * 4.0  # conservative upper bound
        elif op.kind == KIND_SPAWN_MOBILE:
            mp += op.count * cost_for_idx(config, op.unit_idx, upgrade=False)
        # KIND_REMOVE costs nothing
    return sp, mp


def attach_costs(plan: ActionPlan, config) -> ActionPlan:
    plan.sp_cost, plan.mp_cost = compute_costs(plan, config)
    return plan


# ---------------------------------------------------------------------------
# Apply to live gamelib.GameState (for algo_strategy.on_turn final commit)
# ---------------------------------------------------------------------------

def apply_to_game_state(plan: ActionPlan, gs, config) -> dict:
    """Apply each op to a live gamelib.GameState.

    Returns a small report dict (counts of placements that succeeded).
    """
    placed_struct = 0
    placed_mobile = 0
    upgraded = 0
    removed = 0

    # Resolve shorthand strings for spawn calls.
    sh_for = {idx: shorthand_for_idx(config, idx) for idx in range(6)}

    # 1. Process structure spawns first (so subsequent upgrades work).
    for op in plan.ops:
        if op.kind != KIND_SPAWN_STRUCT:
            continue
        if op.unit_idx not in STRUCTURE_IDXS:
            continue
        xy = list(op.xy)
        if not gs.game_map.in_arena_bounds(xy):
            continue
        if xy[1] >= ARENA_HALF_Y:
            continue  # don't place in opp's half
        if gs.contains_stationary_unit(xy):
            continue
        try:
            n = gs.attempt_spawn(sh_for[op.unit_idx], xy)
            if n:
                placed_struct += 1
        except Exception:
            pass

    # 2. Removes (return refund delayed; engine handles).
    for op in plan.ops:
        if op.kind != KIND_REMOVE:
            continue
        xy = list(op.xy)
        if not gs.game_map.in_arena_bounds(xy):
            continue
        try:
            n = gs.attempt_remove(xy)
            if n:
                removed += 1
        except Exception:
            pass

    # 3. Upgrades.
    for op in plan.ops:
        if op.kind != KIND_UPGRADE:
            continue
        xy = list(op.xy)
        if not gs.game_map.in_arena_bounds(xy):
            continue
        try:
            n = gs.attempt_upgrade(xy)
            if n:
                upgraded += 1
        except Exception:
            pass

    # 4. Mobile spawns last (deploy phase mobiles).
    for op in plan.ops:
        if op.kind != KIND_SPAWN_MOBILE:
            continue
        if op.unit_idx not in MOBILE_IDXS:
            continue
        xy = list(op.xy)
        if not gs.game_map.in_arena_bounds(xy):
            continue
        if gs.contains_stationary_unit(xy):
            continue  # blocked by structure (likely our own wall on edge)
        try:
            n = gs.attempt_spawn(sh_for[op.unit_idx], xy, op.count)
            placed_mobile += n
        except Exception:
            pass

    return {
        "placed_struct": placed_struct, "placed_mobile": placed_mobile,
        "upgraded": upgraded, "removed": removed,
    }


# ---------------------------------------------------------------------------
# Apply to sim_rs state dict (for search.py simulation)
# ---------------------------------------------------------------------------

def apply_to_state_dict(plan: ActionPlan, state_dict: dict, my_player: int,
                        config, *, deduct_resources: bool = True,
                        next_uid_seed: Optional[int] = None) -> int:
    """Mutate a sim_rs schema dict to reflect this plan applied at deploy.

    - Adds new structures (with full HP) to state_dict["structures"]
    - Marks existing structures as upgraded (in place) — bumps HP to upgraded startHealth
    - Marks structures for removal (sets turn_start_removal)
    - Adds mobiles as queued spawns (will be picked up by the action phase)
    - Deducts SP/MP from p1/p2 stats (if deduct_resources)

    Returns the next free UID seed (for chained calls).
    """
    side_key = "p1" if my_player == 1 else "p2"
    if next_uid_seed is None:
        next_uid_seed = _max_uid(state_dict) + 1

    # Index existing structures by (x, y) for quick lookup.
    by_xy = {tuple(s["xy"]): s for s in state_dict.get("structures", [])}

    sp_remaining = float(state_dict[side_key]["sp"])
    mp_remaining = float(state_dict[side_key]["mp"])

    # 1. Spawn structures (only ours; opponent's plans go through this too with
    #    their own my_player).
    for op in plan.ops:
        if op.kind != KIND_SPAWN_STRUCT:
            continue
        if op.unit_idx not in STRUCTURE_IDXS:
            continue
        xy = (int(op.xy[0]), int(op.xy[1]))
        if xy in by_xy:
            continue  # blocked
        cost = cost_for_idx(config, op.unit_idx)
        if deduct_resources and sp_remaining + 1e-9 < cost:
            continue
        if deduct_resources:
            sp_remaining -= cost
        hp = starting_hp_for_idx(config, op.unit_idx, upgrade=False)
        new_struct = {
            "xy": [xy[0], xy[1]],
            "type_idx": int(op.unit_idx),
            "upgraded": False,
            "hp": float(hp),
            "uid": str(next_uid_seed),
            "player": int(my_player),
            "turn_start_removal": None,
        }
        state_dict.setdefault("structures", []).append(new_struct)
        by_xy[xy] = new_struct
        next_uid_seed += 1

    # 2. Upgrades.
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
        if deduct_resources and sp_remaining + 1e-9 < cost:
            continue
        if deduct_resources:
            sp_remaining -= cost
        s["upgraded"] = True
        # Bump HP to upgraded start health so sim accounts for the buff.
        upg_hp = starting_hp_for_idx(config, idx, upgrade=True)
        # Preserve any existing damage as a fraction.
        base_hp = starting_hp_for_idx(config, idx, upgrade=False)
        if base_hp > 0:
            cur_frac = float(s.get("hp", base_hp)) / base_hp
        else:
            cur_frac = 1.0
        s["hp"] = float(upg_hp * max(0.0, min(1.0, cur_frac)))

    # 3. Removes.
    for op in plan.ops:
        if op.kind != KIND_REMOVE:
            continue
        xy = (int(op.xy[0]), int(op.xy[1]))
        s = by_xy.get(xy)
        if s is None or s.get("player") != my_player:
            continue
        s["turn_start_removal"] = int(state_dict.get("turn", 0))

    # 4. Mobile spawns.
    for op in plan.ops:
        if op.kind != KIND_SPAWN_MOBILE:
            continue
        if op.unit_idx not in MOBILE_IDXS:
            continue
        cost = cost_for_idx(config, op.unit_idx)
        x, y = int(op.xy[0]), int(op.xy[1])
        target_edge = _spawn_to_target_edge(x, y, my_player)
        for _ in range(int(op.count)):
            if deduct_resources and mp_remaining + 1e-9 < cost:
                break
            if deduct_resources:
                mp_remaining -= cost
            hp = MOBILE_HP_DEFAULT.get(op.unit_idx, 15.0)
            state_dict.setdefault("mobiles", []).append({
                "xy": [x, y],
                "type_idx": int(op.unit_idx),
                "hp": float(hp),
                "shield": 0.0,
                "uid": str(next_uid_seed),
                "player": int(my_player),
                "spawn_xy": [x, y],
                "target_edge": int(target_edge),
                "steps_taken": 0,
                "move_buildup": 0.0,
                "last_move": 0,
                "finished_navigating": False,
                "reached_target": False,
                "breached": False,
            })
            next_uid_seed += 1

    if deduct_resources:
        # Round to engine's float32 resolution.
        state_dict[side_key]["sp"] = float(round(sp_remaining, 1))
        state_dict[side_key]["mp"] = float(round(mp_remaining, 1))

    return next_uid_seed


def _max_uid(state_dict: dict) -> int:
    m = 1_000_000  # base seed (matches state_adapter convention)
    for u in (state_dict.get("structures", []) +
              state_dict.get("mobiles", [])):
        try:
            v = int(u.get("uid", 0))
            if v > m:
                m = v
        except (TypeError, ValueError):
            continue
    return m


def _spawn_to_target_edge(x: int, y: int, player: int) -> int:
    """Mirror of state_adapter._spawn_to_target_edge.

    Edge IDs match vendored_sim/map.py:
      EDGE_TOP_RIGHT=0, EDGE_TOP_LEFT=1, EDGE_BOTTOM_LEFT=2, EDGE_BOTTOM_RIGHT=3.
    """
    HALF = 14
    if y < HALF:
        return 0 if x < HALF else 1   # bottom spawns target top edges
    return 3 if x < HALF else 2       # top spawns target bottom edges


__all__ = [
    "Op", "ActionPlan",
    "KIND_SPAWN_STRUCT", "KIND_UPGRADE", "KIND_REMOVE", "KIND_SPAWN_MOBILE",
    "compute_costs", "attach_costs",
    "apply_to_game_state", "apply_to_state_dict",
]
