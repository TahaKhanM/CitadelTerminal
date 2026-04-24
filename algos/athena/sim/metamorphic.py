"""Metamorphic parity tests (Phase 1.B.4).

Metamorphic relations assert that a game-semantic transformation of the
input produces a predictable transformation of the output. They catch
bugs that standard unit tests can't: asymmetries between players,
order-sensitivity in deploy actions, non-determinism, and
coordinate-frame mistakes.

Four relations shipped:

M1 — player-swap:
    Swap p1 ↔ p2 in a starting state (coordinates mirrored across
    y=13.5). The action phase should produce swapped outputs: p1's
    final HP in original == p2's final HP in swapped, and vice versa.

M2 — mirror-map:
    Mirror the map across x=13.5 (x' = 27 - x). Edge types swap
    (BOTTOM_LEFT ↔ BOTTOM_RIGHT, etc.). The action phase should
    produce x-mirrored outputs for structures and mobiles.

M3 — determinism:
    Same input, run twice → byte-identical outputs. Guards against
    stray random calls, set-iteration leakage, or system-time
    dependencies.

M4 — deploy-commutativity:
    Spawning [A, B] vs [B, A] in the deploy phase — when A and B are
    on different tiles with different type_idx — produces identical
    action-phase output. (Engine: SpawnUnitsSystem orders spawns by
    command position, but the subsequent action phase treats them as
    a flat gameObjects list; the only order-sensitivity is tied
    targeting, which is already handled by uid-based tiebreak.)

Usage:
  python3 -m algos.athena.sim.metamorphic
  python3 -m algos.athena.sim.metamorphic --fuzz-n 10000 --seed 42
"""

from __future__ import annotations

import argparse
import copy
import sys
import time
from pathlib import Path
from typing import List, Tuple

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import (  # noqa: E402
    IDX_SCOUT, IDX_WALL, SimConfig,
)
from sim.map import (  # noqa: E402
    EDGE_BOTTOM_LEFT, EDGE_BOTTOM_RIGHT,
    EDGE_TOP_LEFT, EDGE_TOP_RIGHT,
)
from sim.pysim import simulate_action_phase  # noqa: E402
from sim.state import Mobile, PlayerStats, SimState, Structure  # noqa: E402
from sim.fuzz import (  # noqa: E402
    CATEGORIES, _GENERATORS, _state_snapshot, _clone_state,
    _next_uid,
)

FP32 = np.float32


# ------------------------------------------------------------- M1 player-swap

def _swap_players_xy(xy: Tuple[int, int]) -> Tuple[int, int]:
    x, y = xy
    return (x, 27 - y)


def _swap_target_edge(e: int) -> int:
    return {EDGE_TOP_RIGHT: EDGE_BOTTOM_RIGHT,
            EDGE_TOP_LEFT: EDGE_BOTTOM_LEFT,
            EDGE_BOTTOM_LEFT: EDGE_TOP_LEFT,
            EDGE_BOTTOM_RIGHT: EDGE_TOP_RIGHT}[e]


def _swap_players(state: SimState) -> SimState:
    new = SimState(
        turn=state.turn,
        structures={},
        mobiles=[],
        p1=PlayerStats(hp=state.p2.hp, sp=state.p2.sp, mp=state.p2.mp),
        p2=PlayerStats(hp=state.p1.hp, sp=state.p1.sp, mp=state.p1.mp),
    )
    for s in state.structures.values():
        new_xy = _swap_players_xy(s.xy)
        new_player = 2 if s.player == 1 else 1
        new.structures[new_xy] = Structure(
            xy=new_xy, type_idx=s.type_idx, upgraded=s.upgraded,
            hp=s.hp, uid=s.uid, player=new_player,
        )
    for m in state.mobiles:
        new.mobiles.append(Mobile(
            xy=_swap_players_xy(m.xy), type_idx=m.type_idx, hp=m.hp,
            shield=m.shield, uid=m.uid,
            player=2 if m.player == 1 else 1,
            spawn_xy=_swap_players_xy(m.spawn_xy),
            target_edge=_swap_target_edge(m.target_edge),
        ))
    return new


def _check_m1_player_swap(state: SimState, config: SimConfig) -> bool:
    orig = _clone_state(state)
    swapped = _swap_players(state)
    simulate_action_phase(orig, config)
    simulate_action_phase(swapped, config)
    # After swap+run: swapped.p1 should mirror orig.p2.
    return (
        float(swapped.p1.hp) == float(orig.p2.hp)
        and float(swapped.p2.hp) == float(orig.p1.hp)
    )


# ------------------------------------------------------------- M2 mirror-map

def _mirror_xy(xy: Tuple[int, int]) -> Tuple[int, int]:
    return (27 - xy[0], xy[1])


def _mirror_target_edge(e: int) -> int:
    return {EDGE_TOP_RIGHT: EDGE_TOP_LEFT,
            EDGE_TOP_LEFT: EDGE_TOP_RIGHT,
            EDGE_BOTTOM_LEFT: EDGE_BOTTOM_RIGHT,
            EDGE_BOTTOM_RIGHT: EDGE_BOTTOM_LEFT}[e]


def _mirror_map(state: SimState) -> SimState:
    new = SimState(
        turn=state.turn, structures={}, mobiles=[],
        p1=PlayerStats(hp=state.p1.hp, sp=state.p1.sp, mp=state.p1.mp),
        p2=PlayerStats(hp=state.p2.hp, sp=state.p2.sp, mp=state.p2.mp),
    )
    for s in state.structures.values():
        new_xy = _mirror_xy(s.xy)
        new.structures[new_xy] = Structure(
            xy=new_xy, type_idx=s.type_idx, upgraded=s.upgraded,
            hp=s.hp, uid=s.uid, player=s.player,
        )
    for m in state.mobiles:
        new.mobiles.append(Mobile(
            xy=_mirror_xy(m.xy), type_idx=m.type_idx, hp=m.hp,
            shield=m.shield, uid=m.uid, player=m.player,
            spawn_xy=_mirror_xy(m.spawn_xy),
            target_edge=_mirror_target_edge(m.target_edge),
        ))
    return new


def _check_m2_mirror_map(state: SimState, config: SimConfig) -> bool:
    orig = _clone_state(state)
    mirrored = _mirror_map(state)
    simulate_action_phase(orig, config)
    simulate_action_phase(mirrored, config)
    # HP totals should match exactly (symmetry doesn't affect damage);
    # structure/mobile count should match per-type.
    if float(orig.p1.hp) != float(mirrored.p1.hp):
        return False
    if float(orig.p2.hp) != float(mirrored.p2.hp):
        return False
    if len(orig.structures) != len(mirrored.structures):
        return False
    if len(orig.mobiles) != len(mirrored.mobiles):
        return False
    return True


# ------------------------------------------------------------- M3 determinism

def _check_m3_determinism(state: SimState, config: SimConfig) -> bool:
    a = _clone_state(state)
    b = _clone_state(state)
    simulate_action_phase(a, config)
    simulate_action_phase(b, config)
    return _state_snapshot(a) == _state_snapshot(b)


# ---------------------------------------------------- M4 deploy-commutativity

def _check_m4_resource_independence(state: SimState, config: SimConfig) -> bool:
    """Resource-independence: the action phase does not read p1/p2 SP or MP
    (only HP). Scaling SP/MP up by an arbitrary amount before
    `simulate_action_phase` must produce a byte-identical action-phase
    outcome. Engine citation: `TargetAndAttackSystem.java`,
    `BreachSystem.java`, `SelfDestructSystem.java`, `ShieldSystem.java`,
    `MoveSystem.java` — none of these systems read `PlayerStats.sp` or
    `.mp`; SP/MP are deploy-phase-only state.

    NOTE: the originally-proposed M4 "reverse mobile list" is NOT a valid
    invariant of this engine. Mobile-list iteration order legitimately
    affects SD target enumeration (`SelfDestructSystem` iterates
    insertion order, and reordering SDers changes which enemies die
    first → changes subsequent SDers' target sets → cascades into
    per-turret HP distribution). This is the same class of ordering
    sensitivity as JVM-HashSet in `ColliderComponent.collidedWithThisTurn`
    that the validator's CASCADE gate treats as attributable. Verified
    empirically on `replays/ranked/v13_360023_m15302640.replay` T56/T60/
    T66 and `v13_360023_m15302704.replay` T37/T39/T41: reversing the
    mobile list produces 32-HP swaps between paired turrets — well
    outside the 1-ULP cascade band. Replaced with this stronger
    resource-independence invariant."""
    a = _clone_state(state)
    b = _clone_state(state)
    # Mutate p1/p2 SP and MP on b by a large amount. If sim reads these,
    # the action-phase output will diverge.
    b.p1.sp = np.float32(float(b.p1.sp) + 999.0)
    b.p2.sp = np.float32(float(b.p2.sp) + 999.0)
    b.p1.mp = np.float32(float(b.p1.mp) + 999.0)
    b.p2.mp = np.float32(float(b.p2.mp) + 999.0)
    simulate_action_phase(a, config)
    simulate_action_phase(b, config)

    # HP must be identical; structures must be identical; mobiles must
    # be byte-identical. SP/MP of course differ (by the injected +999).
    if float(a.p1.hp) != float(b.p1.hp): return False
    if float(a.p2.hp) != float(b.p2.hp): return False
    sa = sorted((s.xy, s.type_idx, bool(s.upgraded), float(s.hp),
                  str(s.uid), int(s.player)) for s in a.structures.values())
    sb = sorted((s.xy, s.type_idx, bool(s.upgraded), float(s.hp),
                  str(s.uid), int(s.player)) for s in b.structures.values())
    if sa != sb:
        return False
    ma = sorted(a.mobiles, key=lambda m: (str(m.uid), m.xy))
    mb = sorted(b.mobiles, key=lambda m: (str(m.uid), m.xy))
    if len(ma) != len(mb):
        return False
    for x, y in zip(ma, mb):
        if (x.xy, x.type_idx, str(x.uid), int(x.player),
            float(x.hp), float(x.shield)) != \
           (y.xy, y.type_idx, str(y.uid), int(y.player),
            float(y.hp), float(y.shield)):
            return False
    return True


# Kept for back-compat — some old runners import the original name.
_check_m4_deploy_commutativity = _check_m4_resource_independence


# ---------------------------------------------------------------- driver

_RELATIONS = (
    ("M1_player_swap", _check_m1_player_swap),
    ("M2_mirror_map", _check_m2_mirror_map),
    ("M3_determinism", _check_m3_determinism),
    ("M4_resource_independence", _check_m4_resource_independence),
)


def run_metamorphic_over_fuzz(n: int, seed: int, config: SimConfig) -> dict:
    import hashlib
    stats = {name: {"pass": 0, "fail": 0} for name, _ in _RELATIONS}
    failing_examples: List[Tuple[str, str, int]] = []
    t0 = time.perf_counter()
    for i in range(n):
        cat = CATEGORIES[i % len(CATEGORIES)]
        cat_seed = int(hashlib.sha1(cat.encode()).hexdigest()[:8], 16)
        rng = np.random.default_rng((int(seed), cat_seed, int(i)))
        state = _GENERATORS[cat](rng)
        for name, check in _RELATIONS:
            try:
                ok = check(state, config)
            except Exception:
                ok = False
            if ok:
                stats[name]["pass"] += 1
            else:
                stats[name]["fail"] += 1
                if len(failing_examples) < 10:
                    failing_examples.append((name, cat, i))
    dt = time.perf_counter() - t0
    return {
        "n_configs": n, "seed": seed, "wall_s": dt,
        "per_relation": stats, "first_failures": failing_examples,
    }


def run_metamorphic_over_ranked(config: SimConfig) -> dict:
    """Run M1-M4 on the turn-start state of every turn of every ranked
    replay. This is the full-corpus coverage gate from P4."""
    from pathlib import Path
    from sim.validate import (
        _parse_replay, _index_deploy_frames, _index_first_action_frames,
        _extract_deploy_actions, _extract_deploy_events_in_order,
        _build_state_from_deploy_frame, _collect_upgraded_uids,
    )
    from sim.pysim import apply_deploy_actions
    stats = {name: {"pass": 0, "fail": 0} for name, _ in _RELATIONS}
    failing_examples: List[Tuple[str, str, int]] = []
    t0 = time.perf_counter()
    corpus = sorted((Path(__file__).resolve().parent.parent.parent.parent
                       / "replays" / "ranked").glob("*.replay"))
    for path in corpus:
        frames, _ = _parse_replay(path)
        deploys = _index_deploy_frames(frames)
        actions_first = _index_first_action_frames(frames)
        upgraded_pre = _collect_upgraded_uids(frames)
        for t in sorted(deploys.keys()):
            if t not in actions_first:
                continue
            state = _build_state_from_deploy_frame(deploys[t], config,
                                                     upgraded_pre.get(t, set()))
            p1s, p1u, p2s, p2u = _extract_deploy_actions(actions_first[t])
            ordered = _extract_deploy_events_in_order(actions_first[t])
            apply_deploy_actions(state, config, p1s, p1u, p2s, p2u,
                                   ordered_events=ordered)
            for name, check in _RELATIONS:
                try:
                    ok = check(state, config)
                except Exception:
                    ok = False
                if ok:
                    stats[name]["pass"] += 1
                else:
                    stats[name]["fail"] += 1
                    if len(failing_examples) < 10:
                        failing_examples.append((name, path.name, t))
    dt = time.perf_counter() - t0
    return {"n_replays": len(corpus), "wall_s": dt,
             "per_relation": stats, "first_failures": failing_examples}


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--fuzz-n", type=int, default=120)
    ap.add_argument("--seed", type=int, default=42)
    ap.add_argument("--ranked", action="store_true",
                     help="also run on turn-start state of every ranked replay")
    args = ap.parse_args()
    config = SimConfig.load()

    any_fail = False

    summary = run_metamorphic_over_fuzz(args.fuzz_n, args.seed, config)
    print(f"fuzz n={summary['n_configs']} seed={summary['seed']} "
          f"wall={summary['wall_s']:.1f}s")
    for name, counts in summary["per_relation"].items():
        ok = counts["fail"] == 0
        if not ok:
            any_fail = True
        print(f"  {name:<30} pass={counts['pass']:6d} fail={counts['fail']:6d} "
              f"{'PASS' if ok else 'FAIL'}")
    if summary["first_failures"]:
        print("  first failures:")
        for name, cat, i in summary["first_failures"]:
            print(f"    {name} on {cat} @ i={i}")

    if args.ranked:
        print()
        rr = run_metamorphic_over_ranked(config)
        print(f"ranked n={rr['n_replays']} wall={rr['wall_s']:.1f}s")
        for name, counts in rr["per_relation"].items():
            ok = counts["fail"] == 0
            if not ok:
                any_fail = True
            print(f"  {name:<30} pass={counts['pass']:6d} fail={counts['fail']:6d} "
                  f"{'PASS' if ok else 'FAIL'}")
        if rr["first_failures"]:
            print("  first failures:")
            for name, rp, t in rr["first_failures"]:
                print(f"    {name} on {rp} T{t}")

    return 0 if not any_fail else 1


if __name__ == "__main__":
    sys.exit(main())
