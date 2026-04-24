"""Per-turn post-state diff tool.

Given a replay and a turn number T, compare SimCore's post-action-phase
state to the replay's turn-T+1 deploy frame. Reports:
  * HP/SP/MP mismatch
  * Structures alive in one but not the other (by position)
  * Structures with mismatched current HP (by position)
  * Mobile-damage aggregate per player

Usage:
    python3 algos/athena/sim/diff_turn.py <replay.replay> <turn>

Goal: the FIRST mismatch between sim and replay on a single turn is the
root cause of that replay's max-error; fix it and re-run validate.py.
"""

from __future__ import annotations

import json
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import (  # noqa: E402
    IDX_SUPPORT,
    IDX_TURRET,
    IDX_WALL,
    SimConfig,
)
from sim.pysim import apply_deploy_actions, simulate_action_phase  # noqa: E402
from sim.state import PlayerStats, SimState  # noqa: E402
from sim.validate import (  # noqa: E402
    _build_state_from_deploy_frame,
    _collect_upgraded_uids,
    _extract_deploy_actions,
    _index_deploy_frames,
    _index_first_action_frames,
    _parse_replay,
)


def _struct_dict_from_frame(frame: dict, side: str) -> dict:
    """Return {(x, y): (type_idx, hp, uid)} for structures in a deploy frame."""
    out = {}
    units = frame.get(side, []) or []
    for type_idx in (IDX_WALL, IDX_SUPPORT, IDX_TURRET):
        if type_idx >= len(units):
            continue
        for u in units[type_idx]:
            if len(u) >= 4:
                out[(int(u[0]), int(u[1]))] = (type_idx, float(u[2]), str(u[3]))
    return out


def _type_name(type_idx: int) -> str:
    return {0: "FF/Wall", 1: "EF/Support", 2: "DF/Turret",
            3: "PI/Scout", 4: "EI/Demolisher", 5: "SI/Interceptor"}.get(type_idx, f"type_{type_idx}")


def diff_turn(path: Path, target_turn: int, config: SimConfig) -> None:
    frames, _cfg = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions = _index_first_action_frames(frames)
    ups = _collect_upgraded_uids(frames)

    if target_turn not in deploys:
        print(f"no deploy frame for turn {target_turn}")
        return
    if target_turn + 1 not in deploys:
        print(f"no post-turn deploy frame; game ended at turn {target_turn}")
        return
    df = deploys[target_turn]
    df_next = deploys[target_turn + 1]
    af = actions.get(target_turn)
    if af is None:
        print(f"no action frame for turn {target_turn}")
        return

    # Build state from replay's pre-turn deploy snapshot.
    state = _build_state_from_deploy_frame(df, config, ups.get(target_turn, set()))
    p1_sp, p1_up, p2_sp, p2_up = _extract_deploy_actions(af)
    apply_deploy_actions(state, config, p1_sp, p1_up, p2_sp, p2_up)
    pre_p1_hp = state.p1.hp
    pre_p2_hp = state.p2.hp

    res = simulate_action_phase(state, config)

    # Replay post-state
    r_p1_hp = float(df_next["p1Stats"][0])
    r_p2_hp = float(df_next["p2Stats"][0])
    r_p1_sp = float(df_next["p1Stats"][1])
    r_p2_sp = float(df_next["p2Stats"][1])

    r_p1_structs = _struct_dict_from_frame(df_next, "p1Units")
    r_p2_structs = _struct_dict_from_frame(df_next, "p2Units")

    print(f"=== Turn {target_turn} diff ===")
    print(f"Pre-turn p1 HP={pre_p1_hp} p2 HP={pre_p2_hp}")
    print()

    # Damage deltas
    sim_p1d = pre_p1_hp - state.p1.hp
    sim_p2d = pre_p2_hp - state.p2.hp
    replay_p1d = pre_p1_hp - r_p1_hp
    replay_p2d = pre_p2_hp - r_p2_hp
    print(f"HP damage delta — sim vs replay:")
    print(f"  p1 took dmg: sim={sim_p1d}  replay={replay_p1d}  ERR={abs(sim_p1d-replay_p1d):.1f}")
    print(f"  p2 took dmg: sim={sim_p2d}  replay={replay_p2d}  ERR={abs(sim_p2d-replay_p2d):.1f}")

    # Structures: compare sim state to replay state
    # First, structures alive in one but not the other.
    sim_structs = {xy: s for xy, s in state.structures.items()}
    for player, side_key, r_structs in [(1, "p1Units", r_p1_structs), (2, "p2Units", r_p2_structs)]:
        sim_p_structs = {xy: s for xy, s in sim_structs.items() if s.player == player}
        r_set = set(r_structs.keys())
        sim_set = set(sim_p_structs.keys())

        alive_only_in_sim = sim_set - r_set
        alive_only_in_replay = r_set - sim_set

        if alive_only_in_sim:
            print(f"\n  p{player} structures ALIVE in sim but DEAD in replay:")
            for xy in sorted(alive_only_in_sim):
                s = sim_p_structs[xy]
                print(f"    {xy} {_type_name(s.type_idx)} hp={s.hp:.1f} upg={s.upgraded}")
        if alive_only_in_replay:
            print(f"\n  p{player} structures ALIVE in replay but DEAD in sim:")
            for xy in sorted(alive_only_in_replay):
                tidx, hp, uid = r_structs[xy]
                print(f"    {xy} {_type_name(tidx)} hp={hp:.1f}")

        # HP mismatches on structures alive in both
        hp_mism = []
        for xy in sim_set & r_set:
            sim_hp = sim_p_structs[xy].hp
            r_hp = r_structs[xy][1]
            if abs(sim_hp - r_hp) > 0.01:
                hp_mism.append((xy, sim_hp, r_hp, sim_p_structs[xy].type_idx))
        if hp_mism:
            print(f"\n  p{player} structure HP mismatches (both alive, sim != replay):")
            for xy, sh, rh, ti in sorted(hp_mism):
                print(f"    {xy} {_type_name(ti)}: sim={sh:.1f}  replay={rh:.1f}  delta={sh-rh:+.1f}")

    # Event breakdown
    e_counts = {}
    for e in res.frame_events:
        e_counts[e["type"]] = e_counts.get(e["type"], 0) + 1
    print(f"\nsim event counts: {e_counts}  frames={res.frame_count}")

    breaches = [e for e in res.frame_events if e["type"] == "breach"]
    if breaches:
        print(f"\nsim breaches ({len(breaches)}):")
        by_tile = {}
        for b in breaches:
            by_tile[b["xy"]] = by_tile.get(b["xy"], 0) + 1
        for xy, n in sorted(by_tile.items(), key=lambda kv: -kv[1]):
            print(f"    {xy}: {n} breach(es)")


if __name__ == "__main__":
    if len(sys.argv) < 3:
        sys.exit("usage: diff_turn.py <replay.replay> <turn>")
    path = Path(sys.argv[1])
    turn = int(sys.argv[2])
    diff_turn(path, turn, SimConfig.load())
