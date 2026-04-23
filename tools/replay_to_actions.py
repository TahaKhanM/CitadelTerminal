#!/usr/bin/env python3
"""
replay_to_actions.py — Extract per-turn per-player actions from a Citadel
replay file into a deterministic JSON schema. Feeds SimCore validation
(Phase 1) and offense/defense analysis (later phases).

Schema produced for each turn:
  {
    "turn":         <int>,
    "starting_state": {
       "p1": {"hp":40.0,"sp":8.0,"mp":1.0},
       "p2": {"hp":40.0,"sp":8.0,"mp":1.0},
       "p1_structures": [[x,y,type_idx,hp,unit_id,pending_removal],...],
       "p2_structures": [...],
    },
    "p1_actions": {
       "spawns":    [[x,y,type_idx], ...],          # structures + mobile units, in order
       "upgrades":  [[x,y], ...],
       "removes":   [[x,y], ...],                   # inferred from pending_removal flag deltas
    },
    "p2_actions": { ... same shape ... },
    "result": {"turns": <int>, "winner": 1|2|None}
  }

Player index conventions:
  * Raw replay action-frame player fields use 1=self, 2=opponent (matches
    the way the replay was recorded from p1's perspective).
  * p1/p2 fields here are relative to the replay recording (p1 == the first
    player, p2 == the second). We do NOT flip to 0/1.

CLI:
    python3 tools/replay_to_actions.py <replay.replay> [--out actions.json]
    python3 tools/replay_to_actions.py --all replays/ranked/    # batch mode
"""

from __future__ import annotations

import argparse
import json
import sys
from collections import defaultdict
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

ARENA = 28
HALF = 14

IDX_WALL = 0
IDX_SUPPORT = 1
IDX_TURRET = 2
IDX_SCOUT = 3
IDX_DEMOLISHER = 4
IDX_INTERCEPTOR = 5
IDX_REMOVE = 6
IDX_UPGRADE = 7

STRUCTURE_TYPES = {IDX_WALL, IDX_SUPPORT, IDX_TURRET}
MOBILE_TYPES = {IDX_SCOUT, IDX_DEMOLISHER, IDX_INTERCEPTOR}

PLAYER_P1 = 1  # raw-JSON "self"
PLAYER_P2 = 2  # raw-JSON "opponent"


def load_frames(path: Path) -> Tuple[Dict[str, Any], List[Dict[str, Any]]]:
    """Return (config_frame, list_of_game_frames). The first line is the
    game config; the last line may be a non-JSON summary."""
    lines = []
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s:
                continue
            if not s.startswith("{"):
                continue
            try:
                lines.append(json.loads(s))
            except Exception:
                continue
    if not lines:
        raise ValueError(f"No JSON frames in {path}")
    # First JSON line is the config (has unitInformation + resources)
    if "unitInformation" in lines[0]:
        return lines[0], lines[1:]
    # fallback: no config frame
    return {}, lines


def _turn_info(frame: Dict[str, Any]) -> Tuple[int, int, int, int]:
    ti = list(frame.get("turnInfo") or [0, 0, 0, 0])
    while len(ti) < 4:
        ti.append(0)
    return int(ti[0]), int(ti[1]), int(ti[2]), int(ti[3])


def _snapshot_structures(units_array: List[list], player_id_bit: int) -> List[list]:
    """Convert p?Units structure rows to a compact list of
    [x, y, type_idx, hp, unit_id, pending_removal]."""
    out: List[list] = []
    for type_idx in (IDX_WALL, IDX_SUPPORT, IDX_TURRET):
        if type_idx >= len(units_array):
            continue
        for u in units_array[type_idx]:
            if not isinstance(u, (list, tuple)):
                continue
            if len(u) < 4:
                continue
            x, y, hp = int(u[0]), int(u[1]), float(u[2])
            uid = str(u[3]) if len(u) >= 4 else "?"
            pend = bool(u[4]) if len(u) >= 5 else False
            out.append([x, y, type_idx, hp, uid, pend])
    return out


def _stats_to_dict(stats: List[Any]) -> Dict[str, float]:
    s = list(stats) + [0.0] * 4
    return {"hp": float(s[0]), "sp": float(s[1]), "mp": float(s[2]), "extra": float(s[3])}


def extract_actions(config: Dict[str, Any], frames: List[Dict[str, Any]]) -> Dict[str, Any]:
    """Walk frames; for each turn T, use:
      * The phase=0 deploy frame of turn T as the "starting_state" snapshot.
      * The first phase=1 frame of turn T for spawn events that list deploy-phase
        actions of BOTH players.
      * The phase=0 deploy frame of turn T+1 to detect structures that got
        REMOVED (their entries disappear or their pending_removal flag
        appears/clears).
    """
    # Index frames by (phase, turn, frame_num)
    deploy_frames: Dict[int, Dict[str, Any]] = {}
    first_action_frames: Dict[int, Dict[str, Any]] = {}
    last_seen_turn = -1
    for f in frames:
        phase, turn, fnum, _ = _turn_info(f)
        if phase == 0 and fnum == -1:
            deploy_frames[turn] = f
        if phase == 1 and fnum == 0 and turn not in first_action_frames:
            first_action_frames[turn] = f
        last_seen_turn = max(last_seen_turn, turn)

    # Final state is the last known frame
    final_frame = frames[-1] if frames else {}
    final_p1_hp = _stats_to_dict(final_frame.get("p1Stats", [0, 0, 0, 0]))["hp"]
    final_p2_hp = _stats_to_dict(final_frame.get("p2Stats", [0, 0, 0, 0]))["hp"]
    if final_p1_hp > final_p2_hp:
        winner = 1
    elif final_p2_hp > final_p1_hp:
        winner = 2
    else:
        winner = None

    turns_out: List[Dict[str, Any]] = []
    sorted_turns = sorted(deploy_frames.keys())
    for idx, t in enumerate(sorted_turns):
        df = deploy_frames[t]
        af = first_action_frames.get(t)

        starting_state = {
            "p1": _stats_to_dict(df.get("p1Stats", [0, 0, 0, 0])),
            "p2": _stats_to_dict(df.get("p2Stats", [0, 0, 0, 0])),
            "p1_structures": _snapshot_structures(df.get("p1Units", []), 1),
            "p2_structures": _snapshot_structures(df.get("p2Units", []), 2),
        }

        p1_spawns: List[list] = []
        p2_spawns: List[list] = []
        p1_upgrades: List[list] = []
        p2_upgrades: List[list] = []

        if af is not None:
            for e in af.get("events", {}).get("spawn", []):
                # [[x, y], unit_type_index, unit_id, player]
                if not isinstance(e, (list, tuple)) or len(e) < 4:
                    continue
                xy = e[0]
                if not isinstance(xy, (list, tuple)) or len(xy) < 2:
                    continue
                x, y, utype, player = int(xy[0]), int(xy[1]), int(e[1]), int(e[3])
                if utype == IDX_UPGRADE:
                    (p1_upgrades if player == PLAYER_P1 else p2_upgrades).append([x, y])
                elif utype in STRUCTURE_TYPES or utype in MOBILE_TYPES:
                    (p1_spawns if player == PLAYER_P1 else p2_spawns).append([x, y, utype])
                # type 6 (remove) is not observed in spawn events — see below.

        # Infer removes by diffing structure snapshots between turn T's deploy
        # frame and turn T+1's deploy frame. Any structure present in T but
        # gone in T+1 AND NOT in the death events of T is NOT a remove — it
        # was killed. A structure marked with pending_removal=True in T's
        # deploy frame has been flagged for removal this turn.
        p1_removes: List[list] = [
            [x, y] for (x, y, _t, _hp, _uid, pr) in starting_state["p1_structures"] if pr
        ]
        p2_removes: List[list] = [
            [x, y] for (x, y, _t, _hp, _uid, pr) in starting_state["p2_structures"] if pr
        ]

        turn_rec = {
            "turn": t,
            "starting_state": starting_state,
            "p1_actions": {"spawns": p1_spawns, "upgrades": p1_upgrades, "removes": p1_removes},
            "p2_actions": {"spawns": p2_spawns, "upgrades": p2_upgrades, "removes": p2_removes},
        }
        turns_out.append(turn_rec)

    return {
        "turns": turns_out,
        "result": {"turns": last_seen_turn, "winner": winner,
                   "final_p1_hp": final_p1_hp, "final_p2_hp": final_p2_hp},
    }


# --------------------------------------------------------------------- CLI

def process_one(path: Path, out_path: Optional[Path] = None) -> Dict[str, Any]:
    config, frames = load_frames(path)
    doc = extract_actions(config, frames)
    doc["_source"] = path.name
    if out_path is not None:
        out_path.write_text(json.dumps(doc, separators=(",", ":")))
    return doc


def _summarize(doc: Dict[str, Any]) -> str:
    turns = doc["turns"]
    total_p1_spawns = sum(len(t["p1_actions"]["spawns"]) for t in turns)
    total_p2_spawns = sum(len(t["p2_actions"]["spawns"]) for t in turns)
    total_p1_up = sum(len(t["p1_actions"]["upgrades"]) for t in turns)
    total_p2_up = sum(len(t["p2_actions"]["upgrades"]) for t in turns)
    total_p1_rm = sum(len(t["p1_actions"]["removes"]) for t in turns)
    total_p2_rm = sum(len(t["p2_actions"]["removes"]) for t in turns)
    r = doc["result"]
    return (f"turns={len(turns)}  winner={r['winner']}  "
            f"hp={r['final_p1_hp']}/{r['final_p2_hp']}  "
            f"p1: {total_p1_spawns} sp / {total_p1_up} up / {total_p1_rm} rm   "
            f"p2: {total_p2_spawns} sp / {total_p2_up} up / {total_p2_rm} rm")


def main() -> int:
    p = argparse.ArgumentParser()
    p.add_argument("path", help="replay file OR directory (with --all)")
    p.add_argument("--out", help="output JSON path for single replay")
    p.add_argument("--all", action="store_true", help="treat path as a directory and process *.replay")
    p.add_argument("--summary-only", action="store_true")
    args = p.parse_args()

    if args.all:
        d = Path(args.path)
        replays = sorted(d.glob("*.replay"))
        if not replays:
            print(f"No *.replay in {d}", file=sys.stderr)
            return 2
        for rp in replays:
            out = rp.with_suffix(".actions.json")
            doc = process_one(rp, out)
            print(f"{rp.name}  →  {out.name}   ({_summarize(doc)})")
        return 0

    rp = Path(args.path)
    out = Path(args.out) if args.out else None
    doc = process_one(rp, out)
    print(f"{rp.name}   ({_summarize(doc)})")
    if args.summary_only:
        return 0
    if out is None:
        # dump minimal first/last turn summary to stdout
        if doc["turns"]:
            print("turn 0:", json.dumps(doc["turns"][0]["p1_actions"]), "|",
                  json.dumps(doc["turns"][0]["p2_actions"]))
            print("turn", doc["turns"][-1]["turn"], ":", json.dumps(doc["turns"][-1]["p1_actions"])[:200])
    return 0


if __name__ == "__main__":
    sys.exit(main())
