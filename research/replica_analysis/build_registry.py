#!/usr/bin/env python3
"""build_registry.py — Build a comprehensive registry of all replays.

For each .replay file:
  - Filename-derived: our_algo, opponent_name, opp_elo, our_won, match_id
  - Content-parsed: P1 name (us), P2 name (opp), turn count, final HP, winner, end_stats
  - Behavioral fingerprint of P2 (the opponent): unit_mix, spawn_locations,
    first_attack_turn, defense_layout_at_T15, MP_at_attack_turns,
    avg_units_per_attack, breach_locations.

Output:
  research/replica_analysis/replay_registry.json — list of records, one per replay
  research/replica_analysis/opponent_index.json — opponent → list of (filename, our_algo, won) tuples
"""
import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent.parent
RANKED_DIR = REPO_ROOT / "Ranked Replays"
LOCAL_REPLAYS = REPO_ROOT / "replays"
OUT_DIR = REPO_ROOT / "research" / "replica_analysis"
OUT_DIR.mkdir(parents=True, exist_ok=True)

# Filename patterns:
#  <our_algo>_<win|loss>_<opp>_<elo>_<match_id>.replay
FILENAME_RE = re.compile(
    r"^(?P<algo>.+?)_(?P<result>win|loss)_(?P<opp>.+?)_(?P<elo>\d{3,4})_(?P<match>\d+)\.replay$"
)

# unit type indices in stationary array order: walls(0), supports(1), turrets(2)
# mobile array order: scouts(3), demos(4), interceptors(5)
TYPE_NAMES = {0: "wall", 1: "support", 2: "turret",
              3: "scout", 4: "demo", 5: "interceptor"}


def parse_replay(path: Path) -> dict:
    """Parse a replay; return a behavioral profile dict for P2 (opponent)."""
    out = {
        "file": str(path.relative_to(REPO_ROOT)),
        "name": path.name,
        "size_bytes": path.stat().st_size,
        "ok": False,
        "error": None,
    }
    # Filename extraction
    m = FILENAME_RE.match(path.name)
    if m:
        out["our_algo"] = m.group("algo")
        out["result"] = m.group("result")
        out["opponent"] = m.group("opp")
        out["opp_elo"] = int(m.group("elo"))
        out["match_id"] = m.group("match")
    else:
        out["our_algo"] = None
        out["result"] = None
        out["opponent"] = None
        out["opp_elo"] = None
        out["match_id"] = None

    # Parse JSON lines
    try:
        with open(path, encoding="utf-8", errors="ignore") as f:
            lines = [l.strip() for l in f if l.strip().startswith("{")]
        if not lines:
            out["error"] = "no_json_lines"
            return out
        config = json.loads(lines[0])
        if "unitInformation" not in config:
            out["error"] = "no_config_line"
            config = None
            frame_lines = lines
        else:
            frame_lines = lines[1:]

        # Walk frames
        end_stats = None
        opp_walls = defaultdict(list)        # turn -> list of [x,y]
        opp_supports = defaultdict(list)
        opp_turrets = defaultdict(list)
        opp_scout_count = 0
        opp_demo_count = 0
        opp_interc_count = 0
        opp_spawn_locations = []   # list of [x,y]
        opp_first_attack_turn = None
        opp_attacks = []           # list of dicts: {turn, scouts, demos, intercs, mp_before}
        opp_mp_per_turn = {}
        opp_sp_per_turn = {}
        breaches_against_us = []
        breaches_for_us = []
        max_turn = 0
        p1_name = None
        p2_name = None

        # Track per-turn opponent attack
        cur_attack = {"scouts": 0, "demos": 0, "intercs": 0, "spawns": []}
        cur_attack_turn = -1

        for ln in frame_lines:
            try:
                ev = json.loads(ln)
            except Exception:
                continue
            if "endStats" in ev:
                end_stats = ev["endStats"]
                if "player1" in end_stats and "name" in end_stats["player1"]:
                    p1_name = end_stats["player1"]["name"]
                if "player2" in end_stats and "name" in end_stats["player2"]:
                    p2_name = end_stats["player2"]["name"]
            ti = ev.get("turnInfo")
            if not ti:
                continue
            turn = ti[1]
            phase = ti[0]
            frame = ti[2] if len(ti) > 2 else 0
            max_turn = max(max_turn, turn)

            # Resources at start-of-turn (deploy phase, frame -1)
            if phase == 0:
                p2s = ev.get("p2Stats", [])
                if len(p2s) >= 3:
                    opp_sp_per_turn.setdefault(turn, p2s[1])
                    opp_mp_per_turn.setdefault(turn, p2s[2])

            # Action phase frame 0 — defense snapshots and spawn deltas
            if phase == 1 and frame == 0:
                p2u = ev.get("p2Units", [])
                if len(p2u) >= 3:
                    opp_walls[turn] = [tuple(s[:2]) for s in p2u[0] if len(s) >= 2]
                    opp_supports[turn] = [tuple(s[:2]) for s in p2u[1] if len(s) >= 2]
                    opp_turrets[turn] = [tuple(s[:2]) for s in p2u[2] if len(s) >= 2]
                # Spawns this turn
                spawns = ev.get("events", {}).get("spawn", [])
                this_turn_attack = False
                if cur_attack_turn != turn:
                    if cur_attack["scouts"] + cur_attack["demos"] + cur_attack["intercs"] > 0:
                        opp_attacks.append({
                            "turn": cur_attack_turn,
                            "scouts": cur_attack["scouts"],
                            "demos": cur_attack["demos"],
                            "intercs": cur_attack["intercs"],
                            "spawns": cur_attack["spawns"],
                            "mp_before": opp_mp_per_turn.get(cur_attack_turn),
                        })
                    cur_attack = {"scouts": 0, "demos": 0, "intercs": 0, "spawns": []}
                    cur_attack_turn = turn
                for s in spawns:
                    if len(s) >= 4 and s[3] == 2:  # P2 (opponent) spawn
                        type_idx = s[1]
                        loc = s[0]
                        if type_idx == 3:
                            opp_scout_count += 1
                            cur_attack["scouts"] += 1
                            opp_spawn_locations.append(tuple(loc))
                            cur_attack["spawns"].append((tuple(loc), "scout"))
                            this_turn_attack = True
                        elif type_idx == 4:
                            opp_demo_count += 1
                            cur_attack["demos"] += 1
                            opp_spawn_locations.append(tuple(loc))
                            cur_attack["spawns"].append((tuple(loc), "demo"))
                            this_turn_attack = True
                        elif type_idx == 5:
                            opp_interc_count += 1
                            cur_attack["intercs"] += 1
                            opp_spawn_locations.append(tuple(loc))
                            cur_attack["spawns"].append((tuple(loc), "interceptor"))
                            this_turn_attack = True
                if this_turn_attack and opp_first_attack_turn is None:
                    opp_first_attack_turn = turn

            # Breach events (for tracking score events)
            for b in ev.get("events", {}).get("breach", []):
                # b = [location, dmg, types?, attacker_id, owner_player]
                if len(b) >= 5:
                    loc, _, _, _, owner = b[:5]
                    if owner == 1:  # us scoring on opp
                        breaches_for_us.append((tuple(loc), turn))
                    elif owner == 2:  # opp scoring on us
                        breaches_against_us.append((tuple(loc), turn))

        # Flush last attack
        if cur_attack["scouts"] + cur_attack["demos"] + cur_attack["intercs"] > 0:
            opp_attacks.append({
                "turn": cur_attack_turn,
                "scouts": cur_attack["scouts"],
                "demos": cur_attack["demos"],
                "intercs": cur_attack["intercs"],
                "spawns": cur_attack["spawns"],
                "mp_before": opp_mp_per_turn.get(cur_attack_turn),
            })

        # Final HP from endStats
        winner = None
        p1_hp_final = None
        p2_hp_final = None
        if end_stats:
            p1_hp_final = end_stats.get("player1", {}).get("integrity")
            p2_hp_final = end_stats.get("player2", {}).get("integrity")
            if "winner" in end_stats:
                winner = end_stats["winner"]
            elif p1_hp_final is not None and p2_hp_final is not None:
                winner = 1 if p1_hp_final > p2_hp_final else (2 if p2_hp_final > p1_hp_final else 0)

        # Take a snapshot at T15, T30, T50 (or last available before)
        def snapshot_at(turn):
            """Find latest snapshot ≤ turn for opp."""
            best = None
            for t in sorted(opp_walls.keys()):
                if t <= turn:
                    best = t
            if best is None:
                return None
            return {
                "turn": best,
                "walls": list(opp_walls[best]),
                "supports": list(opp_supports[best]),
                "turrets": list(opp_turrets[best]),
            }

        out["ok"] = True
        out["max_turn"] = max_turn
        out["winner"] = winner
        out["p1_name"] = p1_name
        out["p2_name"] = p2_name
        out["p1_hp_final"] = p1_hp_final
        out["p2_hp_final"] = p2_hp_final
        out["opp_unit_total"] = opp_scout_count + opp_demo_count + opp_interc_count
        out["opp_unit_mix"] = {
            "scouts": opp_scout_count,
            "demos": opp_demo_count,
            "interceptors": opp_interc_count,
        }
        out["opp_first_attack_turn"] = opp_first_attack_turn
        out["opp_total_attacks"] = len(opp_attacks)
        out["opp_attacks"] = opp_attacks[:60]  # cap to keep file size manageable
        out["opp_spawn_locations_top"] = Counter(opp_spawn_locations).most_common(15)
        out["opp_snapshot_T5"] = snapshot_at(5)
        out["opp_snapshot_T10"] = snapshot_at(10)
        out["opp_snapshot_T15"] = snapshot_at(15)
        out["opp_snapshot_T25"] = snapshot_at(25)
        out["opp_snapshot_T50"] = snapshot_at(50)
        out["opp_final_snapshot"] = snapshot_at(max_turn)
        out["breaches_against_us_top"] = Counter(b[0] for b in breaches_against_us).most_common(10)
        out["breaches_for_us_top"] = Counter(b[0] for b in breaches_for_us).most_common(10)
        out["breaches_against_us_count"] = len(breaches_against_us)
        out["breaches_for_us_count"] = len(breaches_for_us)
        out["opp_mp_per_turn"] = {k: v for k, v in opp_mp_per_turn.items() if k <= 60}
        out["opp_sp_per_turn"] = {k: v for k, v in opp_sp_per_turn.items() if k <= 60}
    except Exception as e:
        out["error"] = repr(e)[:300]
    return out


def main():
    replay_paths = []
    for d in [RANKED_DIR, LOCAL_REPLAYS]:
        if d.exists():
            for p in d.rglob("*.replay"):
                replay_paths.append(p)

    print(f"Found {len(replay_paths)} replays. Parsing…", file=sys.stderr)
    records = []
    for i, p in enumerate(replay_paths):
        rec = parse_replay(p)
        records.append(rec)
        if (i + 1) % 50 == 0:
            print(f"  parsed {i+1}/{len(replay_paths)}", file=sys.stderr)

    # Save
    out_path = OUT_DIR / "replay_registry.json"
    with open(out_path, "w") as f:
        json.dump(records, f, default=str, indent=2)
    print(f"Wrote {out_path} ({len(records)} records)", file=sys.stderr)

    # Build opponent index
    opp_idx = defaultdict(list)
    for r in records:
        if r.get("ok") and r.get("opponent"):
            opp_idx[r["opponent"]].append({
                "file": r["file"],
                "name": r["name"],
                "our_algo": r.get("our_algo"),
                "result": r.get("result"),
                "opp_elo": r.get("opp_elo"),
                "max_turn": r.get("max_turn"),
                "winner": r.get("winner"),
                "p1_hp_final": r.get("p1_hp_final"),
                "p2_hp_final": r.get("p2_hp_final"),
                "p2_name": r.get("p2_name"),
            })
    out_path2 = OUT_DIR / "opponent_index.json"
    with open(out_path2, "w") as f:
        json.dump(dict(opp_idx), f, default=str, indent=2)
    print(f"Wrote {out_path2} ({len(opp_idx)} opponents)", file=sys.stderr)

    # Print summary
    print("\n=== TOP 25 OPPONENTS BY REPLAY COUNT ===")
    by_count = sorted(opp_idx.items(), key=lambda x: -len(x[1]))[:25]
    for name, recs in by_count:
        elos = [r["opp_elo"] for r in recs if r.get("opp_elo")]
        avg_elo = sum(elos) / len(elos) if elos else 0
        wins_for_us = sum(1 for r in recs if r.get("result") == "win")
        losses_for_us = sum(1 for r in recs if r.get("result") == "loss")
        print(f"  {name:<35} replays={len(recs):3d}  avg_elo={avg_elo:6.0f}  our W/L: {wins_for_us}/{losses_for_us}")


if __name__ == "__main__":
    main()
