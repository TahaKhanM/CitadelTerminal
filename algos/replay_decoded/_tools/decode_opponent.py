"""Decode an opponent algo's deploy schedule from a batch of replays.

Strategy:
  1. For each replay, identify which player slot the OPPONENT was in
     (we wrote our algo's name into the filename's left side; we KNOW the
     replay frames flip player_index based on side, but the filename
     win/loss tells us our side via 'winner' in endStats).
  2. Walk action frames, extract spawn events that came from the opponent.
  3. Per-turn: collect (x, y, type_idx, count) and upgrades.
  4. Aggregate across replays: vote on the most common deploy per turn.
  5. Emit decoded_turns.json with shape:
        { "<turn>": {"structures": [["WALL", [x,y]], ...],
                     "mobiles":    [["SCOUT", [x,y], n], ...],
                     "upgrades":   [["TURRET", [x,y]], ...]},
          "default": ... }

Type idx: 0 = WALL/FF, 1 = SUPPORT/EF, 2 = TURRET/DF,
          3 = SCOUT/PI, 4 = DEMOLISHER/EI, 5 = INTERCEPTOR/SI
          6 = removal pseudo, 7 = upgrade

Engine uses player slot 1 / 2 in spawn events. Bottom = player 1, top = player 2.
"""

from __future__ import annotations

import json
import sys
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal")
REPLAYS = ROOT / "Ranked Replays"

UNIT_NAMES = {0: "WALL", 1: "SUPPORT", 2: "TURRET", 3: "SCOUT", 4: "DEMOLISHER", 5: "INTERCEPTOR"}
STRUCTURES = {0, 1, 2}
MOBILES = {3, 4, 5}


def _load_replay_lines(path: Path) -> list[dict]:
    out = []
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s.startswith("{"):
                continue
            try:
                d = json.loads(s)
            except Exception:
                continue
            out.append(d)
    return out


def _identify_our_side(lines: list[dict], outcome: str) -> int:
    """outcome from filename: 'win' or 'loss'.
    Returns 1 or 2 indicating which player slot WE were in.

    Use endStats.winner: if outcome=='win' and winner==1, we are player 1.
    """
    for d in reversed(lines):
        es = d.get("endStats")
        if es:
            winner = es.get("winner")
            if winner not in (1, 2):
                return 1  # default
            if outcome == "win":
                return int(winner)
            else:  # loss
                return 2 if winner == 1 else 1
    return 1  # fallback


def _flip_xy_for_bottom(x: int, y: int, was_top: bool) -> tuple[int, int]:
    """Engine's coordinates are global (28x28). If a player was on TOP
    (player slot 2 in old engine semantics for spawn events), their
    spawn coords are y >= 14. If we want to *replay* that algo as if
    we were on the bottom, we mirror about y=13.5 (and x about x=13.5).
    """
    if not was_top:
        return (x, y)
    return (27 - x, 27 - y)


def _extract_first_action_frames(lines: list[dict]) -> dict[int, dict]:
    """Return {turn_number: first action frame (frame 0)} for each turn."""
    out: dict[int, dict] = {}
    for d in lines:
        ti = d.get("turnInfo") or []
        if len(ti) >= 3 and ti[0] == 1 and ti[2] == 0 and ti[1] not in out:
            out[int(ti[1])] = d
    return out


def _decode_replay(path: Path, opponent_outcome: str) -> dict[int, dict]:
    """Return {turn: {"structures": [...], "mobiles": [...], "upgrades": [...]}}
    of the OPPONENT'S deploy actions, mirrored to bottom-side coordinates if needed.

    opponent_outcome: 'win' if opponent won (i.e. WE lost), 'loss' if WE won.
    """
    lines = _load_replay_lines(path)
    # Determine our side
    our_outcome = "loss" if opponent_outcome == "win" else "win"
    our_side = _identify_our_side(lines, our_outcome)
    opp_side = 1 if our_side == 2 else 2  # 1 or 2
    opp_was_top = (opp_side == 2)

    decoded: dict[int, dict] = {}
    for turn, frame in _extract_first_action_frames(lines).items():
        events = frame.get("events", {})
        spawns = events.get("spawn", [])
        structs: list = []
        mobiles_count: dict = defaultdict(int)
        upgrades: list = []
        for ev in spawns:
            if not isinstance(ev, list) or len(ev) < 4:
                continue
            xy = ev[0]
            type_idx = int(ev[1])
            ev_player = int(ev[3])
            if ev_player != opp_side:
                continue
            x, y = int(xy[0]), int(xy[1])
            x, y = _flip_xy_for_bottom(x, y, opp_was_top)
            if type_idx in STRUCTURES:
                structs.append([UNIT_NAMES[type_idx], [x, y]])
            elif type_idx in MOBILES:
                mobiles_count[(UNIT_NAMES[type_idx], x, y)] += 1
            elif type_idx == 7:
                # Upgrade — position only; algo's attempt_upgrade(loc) figures out
                # the actual structure type at runtime.
                upgrades.append([x, y])
        mobiles = [[name, [x, y], n] for (name, x, y), n in mobiles_count.items()]
        decoded[turn] = {
            "structures": structs,
            "mobiles": mobiles,
            "upgrades": upgrades,
        }
    return decoded


def aggregate_decoded(decodes: list[dict[int, dict]], threshold_frac: float = 0.30) -> dict:
    """Vote across N decoded replays to produce a final schedule.

    For each turn:
    - structures: union of (unit, x, y) appearing in >= threshold_frac of
      replays where this turn exists.
    - mobiles: average count rounded down for each (unit, x, y) appearing
      in >= threshold_frac of replays. Counts averaged ONLY over replays
      where this (unit, x, y) appeared (avoids biasing toward zero).
    - upgrades: union of upgrade positions appearing in >= threshold_frac.

    threshold_frac=0.30 by default — captures strong tendencies while not
    requiring identical play across all replays. Multi-modal algos (e.g.
    oleh-v2 with two opening variants) get a UNION of both modes.
    """
    final: dict = {}
    all_turns = sorted({t for d in decodes for t in d.keys()})

    for turn in all_turns:
        present = [d[turn] for d in decodes if turn in d]
        n_present = len(present)
        thresh_present = max(1, int(round(threshold_frac * n_present)))

        struct_counter = Counter()
        mob_counts: dict = defaultdict(list)  # (name, x, y) -> [counts per replay]
        upg_counter = Counter()

        for entry in present:
            seen_structs = set()
            for name, (x, y) in entry["structures"]:
                key = (name, x, y)
                if key not in seen_structs:
                    struct_counter[key] += 1
                    seen_structs.add(key)
            for name, (x, y), n in entry["mobiles"]:
                mob_counts[(name, x, y)].append(n)
            seen_upg = set()
            for upg in entry["upgrades"]:
                # upg is [x, y] (post-fix) or [name, [x, y]] (legacy)
                if isinstance(upg, (list, tuple)):
                    if len(upg) == 2 and isinstance(upg[0], int):
                        x, y = int(upg[0]), int(upg[1])
                    elif len(upg) == 2 and isinstance(upg[1], (list, tuple)):
                        x, y = int(upg[1][0]), int(upg[1][1])
                    else:
                        continue
                    key = (x, y)
                    if key not in seen_upg:
                        upg_counter[key] += 1
                        seen_upg.add(key)

        structures = [[n, [x, y]] for (n, x, y), c in struct_counter.items() if c >= thresh_present]
        mobiles = []
        for (n, x, y), counts in mob_counts.items():
            if len(counts) >= thresh_present:
                avg = sum(counts) // len(counts)
                if avg > 0:
                    mobiles.append([n, [x, y], avg])
        upgrades = [[x, y] for (x, y), c in upg_counter.items() if c >= thresh_present]

        final[str(turn)] = {
            "structures": structures,
            "mobiles": mobiles,
            "upgrades": upgrades,
            "_n_present": n_present,
        }
    return final


def decode_opponent(opponent_name: str, replay_paths: list[Path]) -> dict:
    """Decode an opponent algo from a batch of replays."""
    decodes = []
    for p in replay_paths:
        # outcome 'win' or 'loss' refers to OUR result in filename
        if "_loss_" in p.name:
            opp_outcome = "win"
        elif "_win_" in p.name:
            opp_outcome = "loss"
        else:
            continue
        try:
            d = _decode_replay(p, opp_outcome)
            decodes.append(d)
            print(f"  decoded {p.name}: {len(d)} turns", file=sys.stderr)
        except Exception as e:
            print(f"  FAILED {p.name}: {e}", file=sys.stderr)
    if not decodes:
        return {}
    return aggregate_decoded(decodes)


if __name__ == "__main__":
    inv_path = ROOT / "algos/replay_decoded/_tools/inventory.json"
    inv = json.loads(inv_path.read_text())

    # CLI: opponent_name [opponent_name ...]
    targets = sys.argv[1:] if len(sys.argv) > 1 else []
    if not targets:
        print("Usage: decode_opponent.py <opponent_name> [...]", file=sys.stderr)
        sys.exit(1)

    for tgt in targets:
        if tgt not in inv:
            print(f"Unknown opponent: {tgt}", file=sys.stderr)
            continue
        info = inv[tgt]
        print(f"\n=== Decoding {tgt} (n={info['n_replays']}, avg={info['avg_rating']}) ===", file=sys.stderr)
        replays = [ROOT / r for r in info["replays"]]
        decoded = decode_opponent(tgt, replays)
        out_dir = ROOT / "algos/replay_decoded" / tgt
        out_dir.mkdir(parents=True, exist_ok=True)
        with open(out_dir / "decoded_turns.json", "w") as f:
            json.dump(decoded, f, indent=2)
        print(f"  Wrote {out_dir}/decoded_turns.json with {len(decoded)} turns", file=sys.stderr)
