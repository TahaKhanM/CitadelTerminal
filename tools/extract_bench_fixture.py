"""Extract a realistic mid-game turn from a ranked replay into a JSON
fixture the Rust benchmark can load.

Choice: pick a turn with many structures AND many mobiles in flight so
the benchmark reflects realistic late-game load (targeting cascade,
SD AOE, shield-give all hot).

Output:
    algos/athena/sim_rs/fixtures/mid_game_turn.json
"""
from __future__ import annotations
import json, sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parent.parent / "algos" / "athena"))

from sim.config import SimConfig
from sim.validate import (
    _parse_replay, _index_deploy_frames, _index_first_action_frames,
    _extract_deploy_actions, _extract_deploy_events_in_order,
    _build_state_from_deploy_frame, _collect_upgraded_uids,
)
from sim.pysim import apply_deploy_actions


def _structure_load(df: dict) -> int:
    """Heuristic: number of structures + mobiles spawning this turn."""
    count = 0
    for key in ("p1Units", "p2Units"):
        units = df.get(key, [])
        for tidx in range(6):  # types 0..5 (structures + mobiles)
            if tidx < len(units):
                count += len(units[tidx] or [])
    return count


def main() -> int:
    config = SimConfig.load()
    best = None  # (load, replay, turn)
    for path in sorted((Path(__file__).resolve().parent.parent
                          / "replays" / "ranked").glob("*.replay"))[:8]:
        frames, _ = _parse_replay(path)
        deploys = _index_deploy_frames(frames)
        actions_first = _index_first_action_frames(frames)
        upgraded_pre = _collect_upgraded_uids(frames)
        # Try turns 20..40 — mid-game load.
        for t in range(20, 40):
            if t not in deploys or t not in actions_first:
                continue
            load = _structure_load(deploys[t])
            # Also require some mobile spawns this turn (non-zero deploy mobiles)
            af = actions_first[t]
            p1s, p1u, p2s, p2u = _extract_deploy_actions(af)
            n_mobile_spawns = sum(1 for sp in (p1s + p2s) if sp[2] >= 3)
            if n_mobile_spawns < 2:
                continue
            if best is None or load > best[0]:
                best = (load, path, t)
    if best is None:
        raise SystemExit("no viable turn found")
    _, path, turn = best
    print(f"selected {path.name} turn {turn} (load-score={best[0]})", file=sys.stderr)

    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    # Build the state with deploy actions applied — this is what
    # simulate_action_phase starts from.
    state = _build_state_from_deploy_frame(deploys[turn], config,
                                             upgraded_pre.get(turn, set()))
    p1s, p1u, p2s, p2u = _extract_deploy_actions(actions_first[turn])
    ordered = _extract_deploy_events_in_order(actions_first[turn])
    apply_deploy_actions(state, config, p1s, p1u, p2s, p2u,
                          ordered_events=ordered)

    # Serialize to a stable JSON shape the Rust bench will parse.
    structs = []
    for xy, s in state.structures.items():
        structs.append({
            "xy": list(xy), "type_idx": s.type_idx, "upgraded": bool(s.upgraded),
            "hp": float(s.hp), "uid": s.uid, "player": s.player,
            "turn_start_removal": s.turn_start_removal,
        })
    mobiles = []
    for m in state.mobiles:
        mobiles.append({
            "xy": list(m.xy), "type_idx": m.type_idx, "hp": float(m.hp),
            "shield": float(m.shield), "uid": m.uid, "player": m.player,
            "spawn_xy": list(m.spawn_xy), "target_edge": m.target_edge,
            "steps_taken": m.steps_taken, "move_buildup": float(m.move_buildup),
            "last_move": m.last_move,
            "finished_navigating": m.finished_navigating,
            "reached_target": m.reached_target,
            "breached": m.breached,
        })
    out = {
        "source_replay": path.name,
        "turn": turn,
        "p1": {"hp": float(state.p1.hp), "sp": float(state.p1.sp),
                "mp": float(state.p1.mp)},
        "p2": {"hp": float(state.p2.hp), "sp": float(state.p2.sp),
                "mp": float(state.p2.mp)},
        "structures": structs,
        "mobiles": mobiles,
    }

    out_path = (Path(__file__).resolve().parent.parent
                / "algos" / "athena" / "sim_rs" / "fixtures"
                / "mid_game_turn.json")
    out_path.parent.mkdir(exist_ok=True, parents=True)
    out_path.write_text(json.dumps(out, indent=2))
    print(f"wrote {out_path}")
    print(f"structures={len(structs)} mobiles={len(mobiles)}", file=sys.stderr)
    return 0


if __name__ == "__main__":
    sys.exit(main())
