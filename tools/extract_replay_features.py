#!/usr/bin/env python3
"""extract_replay_features.py — pull behavioral features from each replay.

For each replay, we extract a dict describing OUR OPPONENT's play:
- Defensive layout snapshots at T15 / T30 / T50 (per-tile densities)
- Offensive style (unit-type ratios, spawn-tile preferences, wave timing)
- Economic patterns (MP hoarding, SP spend rate)
- Game-result summary

Output: /tmp/opp_features.json — a list of records, one per replay.

These features then feed `cluster_opponents.py` for k-means archetypes
and `analyze_per_cluster.py` for win-rate-by-archetype tables.
"""
from __future__ import annotations

import json
import pickle
import statistics
import sys
from collections import Counter
from pathlib import Path


def extract_features(replay_path: Path, our_won: bool | None) -> dict:
    """Walk a replay; produce a feature dict describing the opponent.

    P1 = us (the algo we uploaded), P2 = opp (engine convention always
    puts the algo whose folder we downloaded into player 1).
    """
    lines = replay_path.read_text(encoding="utf-8", errors="ignore").splitlines()

    # Per-turn snapshots of opp state
    opp_walls_per_turn: dict[int, list[tuple[int, int]]] = {}
    opp_supports_per_turn: dict[int, list[tuple[int, int]]] = {}
    opp_turrets_per_turn: dict[int, list[tuple[int, int]]] = {}
    opp_mp_per_turn: dict[int, float] = {}
    opp_sp_per_turn: dict[int, float] = {}

    # Opp mobile-unit spawns
    opp_scout_count = 0
    opp_demo_count = 0
    opp_interc_count = 0
    opp_spawn_locs: list[tuple[int, int]] = []
    opp_first_attack_turn: int | None = None

    end_stats = None
    for line in lines:
        line = line.strip()
        if not line:
            continue
        try:
            ev = json.loads(line)
        except Exception:  # noqa: BLE001
            continue

        if "endStats" in ev:
            end_stats = ev["endStats"]

        if "turnInfo" not in ev:
            continue
        ti = ev["turnInfo"]
        # turnInfo: [phase, turn, frame]
        # phase 0 = deploy phase (start-of-turn snapshot lives here)
        # phase 1 frame 0 = action phase start (deploys are visible in events)
        turn = ti[1]

        # Capture opp resources at deploy-phase start (turn-start state).
        if ti[0] == 0:
            p2s = ev.get("p2Stats", [])
            if len(p2s) >= 3:
                opp_sp_per_turn.setdefault(turn, p2s[1])
                opp_mp_per_turn.setdefault(turn, p2s[2])

        # Capture opp structures at action phase frame 0 (post-deploy).
        if ti[0] == 1 and ti[2] == 0:
            p2u = ev.get("p2Units", [])
            # p2Units = [walls, supports, turrets, scouts, demos, intercs, removals, upgrades]
            if len(p2u) >= 3:
                opp_walls_per_turn[turn] = [
                    (int(s[0]), int(s[1])) for s in p2u[0] if len(s) >= 2
                ]
                opp_supports_per_turn[turn] = [
                    (int(s[0]), int(s[1])) for s in p2u[1] if len(s) >= 2
                ]
                opp_turrets_per_turn[turn] = [
                    (int(s[0]), int(s[1])) for s in p2u[2] if len(s) >= 2
                ]
            # Capture opp's mobile-unit spawns this turn (from events.spawn)
            spawns = ev.get("events", {}).get("spawn", [])
            this_turn_attack = False
            for s in spawns:
                if len(s) >= 4 and s[3] == 2 and s[1] in (3, 4, 5):
                    this_turn_attack = True
                    if s[1] == 3:
                        opp_scout_count += 1
                    elif s[1] == 4:
                        opp_demo_count += 1
                    else:
                        opp_interc_count += 1
                    opp_spawn_locs.append((int(s[0][0]), int(s[0][1])))
            if this_turn_attack and opp_first_attack_turn is None:
                opp_first_attack_turn = turn

    # ---- Compute derived features ----
    feats: dict = {"path": str(replay_path)}

    # Defensive snapshots — opp structures at T15/T30/T50
    for snap_t in (15, 30, 50):
        # Find the closest turn we have data for
        snap_turn = snap_t
        avail = sorted(opp_walls_per_turn)
        if not avail:
            wn = sn = tn = 0
            front = center = lflank = rflank = 0
            tile_set: set = set()
        else:
            snap_turn = min(avail, key=lambda t: abs(t - snap_t))
            walls = opp_walls_per_turn.get(snap_turn, [])
            supps = opp_supports_per_turn.get(snap_turn, [])
            turrs = opp_turrets_per_turn.get(snap_turn, [])
            wn, sn, tn = len(walls), len(supps), len(turrs)
            tile_set = set(walls) | set(supps) | set(turrs)
            # Opp front line is y=14 (their lowest row in our half).
            front = sum(1 for x, y in tile_set if y == 14)
            # Opp center: x in [12,15], y in [14,16]
            center = sum(1 for x, y in tile_set if 12 <= x <= 15 and 14 <= y <= 16)
            # Opp left-flank (THEIR left = x in 0-9 from their perspective; in
            # board frame they're at top so their left = our right? Use board
            # frame consistently: x in [0,9] = "left side").
            lflank = sum(1 for x, y in tile_set if 0 <= x <= 9 and 14 <= y <= 17)
            rflank = sum(1 for x, y in tile_set if 18 <= x <= 27 and 14 <= y <= 17)

        feats[f"t{snap_t}_walls"] = wn
        feats[f"t{snap_t}_supports"] = sn
        feats[f"t{snap_t}_turrets"] = tn
        feats[f"t{snap_t}_total"] = wn + sn + tn
        feats[f"t{snap_t}_front"] = front
        feats[f"t{snap_t}_center"] = center
        feats[f"t{snap_t}_lflank"] = lflank
        feats[f"t{snap_t}_rflank"] = rflank

    # Offensive style
    n_offense = opp_scout_count + opp_demo_count + opp_interc_count
    feats["opp_total_units_spawned"] = n_offense
    feats["opp_scout_frac"] = opp_scout_count / n_offense if n_offense else 0
    feats["opp_demo_frac"] = opp_demo_count / n_offense if n_offense else 0
    feats["opp_interc_frac"] = opp_interc_count / n_offense if n_offense else 0
    feats["opp_first_attack_turn"] = opp_first_attack_turn or -1

    # Spawn-tile preferences
    if opp_spawn_locs:
        loc_counts = Counter(opp_spawn_locs)
        # Most common spawn tile + its share
        top_loc, top_n = loc_counts.most_common(1)[0]
        feats["opp_top_spawn_loc"] = list(top_loc)
        feats["opp_top_spawn_share"] = top_n / len(opp_spawn_locs)
        feats["opp_unique_spawn_tiles"] = len(loc_counts)
        # Side preference: corner (x=0,27) vs mid (4-23)
        feats["opp_corner_spawn_frac"] = sum(
            n for (x, y), n in loc_counts.items() if x in (0, 27)
        ) / len(opp_spawn_locs)
    else:
        feats["opp_top_spawn_loc"] = None
        feats["opp_top_spawn_share"] = 0
        feats["opp_unique_spawn_tiles"] = 0
        feats["opp_corner_spawn_frac"] = 0

    # Economic features
    if opp_mp_per_turn:
        mps = list(opp_mp_per_turn.values())
        feats["opp_mp_max"] = max(mps)
        feats["opp_mp_mean"] = statistics.mean(mps)
    else:
        feats["opp_mp_max"] = 0
        feats["opp_mp_mean"] = 0

    # Game outcome
    if end_stats:
        feats["turns"] = end_stats.get("turns", 0)
        feats["winner"] = end_stats.get("winner")
        feats["our_won"] = our_won if our_won is not None else (
            end_stats.get("winner") == 1
        )
        p1 = end_stats.get("player1", {})
        p2 = end_stats.get("player2", {})
        feats["p1_pts"] = p1.get("points_scored", 0)
        feats["p2_pts"] = p2.get("points_scored", 0)
        feats["opp_mp_spoiled"] = p2.get("dynamic_resource_spoiled", 0)
    else:
        feats["turns"] = 0
        feats["our_won"] = our_won
        feats["winner"] = None
        feats["p1_pts"] = 0
        feats["p2_pts"] = 0
        feats["opp_mp_spoiled"] = 0

    return feats


def main():
    with open("/tmp/replay_index.pkl", "rb") as f:
        records = pickle.load(f)

    out = []
    for i, rec in enumerate(records, 1):
        if i % 25 == 0:
            print(f"  ... extracting {i}/{len(records)}")
        try:
            feats = extract_features(Path(rec["path"]), rec.get("won"))
        except Exception as e:  # noqa: BLE001
            print(f"  skip {rec['path']}: {e!r}")
            continue
        feats["our_algo"] = rec["our"]
        feats["opp_name"] = rec["opp"]
        feats["opp_rating"] = rec.get("opp_rating", 0)
        feats["match_id"] = rec.get("match_id")
        feats["is_legacy"] = rec.get("is_legacy", False)
        out.append(feats)

    with open("/tmp/opp_features.json", "w") as f:
        json.dump(out, f, indent=2, default=str)
    print(f"\nExtracted features for {len(out)} replays → /tmp/opp_features.json")


if __name__ == "__main__":
    main()
