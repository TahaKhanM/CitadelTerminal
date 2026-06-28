#!/usr/bin/env python3
"""validate_replica.py — Compare a replica's behavior to the original opponent's profile.

For a given replica:
  1. Run replica vs python-algo-replica locally (or use an existing replay).
  2. Extract behavioral fingerprint (unit mix, first-attack turn, spawn locations, defense layout).
     Use SAME extractor as build_registry.py.
  3. Compare to opponent profile (research/replica_analysis/profiles/<opp>.json).
  4. Compute similarity scores per dimension.

Usage:
    python3 validate_replica.py <replica_name> [<opp_name>]
        Default opp_name == replica_name minus "-replica" suffix
    python3 validate_replica.py --pair <replica_A> <replica_B> <ground_truth_replay>
        Compare a replica-vs-replica match to a real cross-opponent replay
"""
import json
import sys
import subprocess
from collections import Counter
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(REPO_ROOT / "research" / "replica_analysis"))


def extract_p1_signature(replay_path):
    """Extract behavioral fingerprint of P1 from a replay (so replica should be P1)."""
    with open(replay_path, encoding="utf-8", errors="ignore") as f:
        lines = [l.strip() for l in f if l.strip().startswith("{")]
    if not lines:
        return None
    config = json.loads(lines[0]) if "unitInformation" in lines[0] else None
    frames = lines[1:] if config else lines

    p1_walls_per_turn = {}
    p1_supports_per_turn = {}
    p1_turrets_per_turn = {}
    p1_scout_count = 0
    p1_demo_count = 0
    p1_interc_count = 0
    p1_spawn_locations = []
    p1_first_attack_turn = None
    p1_attacks = []
    p1_mp_per_turn = {}
    p2_breaches = []  # opp scoring on us
    p1_breaches = []  # us scoring on opp
    p1_hp_history = []
    p2_hp_history = []
    max_turn = 0

    cur = {"scouts": 0, "demos": 0, "intercs": 0}
    cur_turn = -1

    for ln in frames:
        try:
            ev = json.loads(ln)
        except Exception:
            continue
        ti = ev.get("turnInfo")
        if not ti:
            continue
        turn = ti[1]
        phase = ti[0]
        frame = ti[2] if len(ti) > 2 else 0
        max_turn = max(max_turn, turn)

        if phase == 0:  # start of deploy phase
            p1s = ev.get("p1Stats", [])
            p2s = ev.get("p2Stats", [])
            if len(p1s) >= 3:
                p1_mp_per_turn.setdefault(turn, p1s[2])
                p1_hp_history.append((turn, p1s[0]))
            if len(p2s) >= 3:
                p2_hp_history.append((turn, p2s[0]))

        if phase == 1 and frame == 0:
            p1u = ev.get("p1Units", [])
            if len(p1u) >= 3:
                p1_walls_per_turn[turn] = [tuple(s[:2]) for s in p1u[0] if len(s) >= 2]
                p1_supports_per_turn[turn] = [tuple(s[:2]) for s in p1u[1] if len(s) >= 2]
                p1_turrets_per_turn[turn] = [tuple(s[:2]) for s in p1u[2] if len(s) >= 2]

            spawns = ev.get("events", {}).get("spawn", [])
            this_turn_attack = False

            if cur_turn != turn:
                if cur["scouts"] + cur["demos"] + cur["intercs"] > 0:
                    p1_attacks.append({
                        "turn": cur_turn,
                        "scouts": cur["scouts"],
                        "demos": cur["demos"],
                        "intercs": cur["intercs"],
                        "mp_before": p1_mp_per_turn.get(cur_turn),
                    })
                cur = {"scouts": 0, "demos": 0, "intercs": 0}
                cur_turn = turn

            for s in spawns:
                if len(s) >= 4 and s[3] == 1:  # P1 spawn
                    type_idx = s[1]
                    loc = s[0]
                    if type_idx == 3:
                        p1_scout_count += 1
                        cur["scouts"] += 1
                        p1_spawn_locations.append(tuple(loc))
                        this_turn_attack = True
                    elif type_idx == 4:
                        p1_demo_count += 1
                        cur["demos"] += 1
                        p1_spawn_locations.append(tuple(loc))
                        this_turn_attack = True
                    elif type_idx == 5:
                        p1_interc_count += 1
                        cur["intercs"] += 1
                        p1_spawn_locations.append(tuple(loc))
                        this_turn_attack = True
            if this_turn_attack and p1_first_attack_turn is None:
                # only count "real" attacks (ignore single interceptor stalls)
                if cur["scouts"] + cur["demos"] >= 3:
                    p1_first_attack_turn = turn

        for b in ev.get("events", {}).get("breach", []):
            if len(b) >= 5:
                loc, _, _, _, owner = b[:5]
                if owner == 1:
                    p1_breaches.append((tuple(loc), turn))
                elif owner == 2:
                    p2_breaches.append((tuple(loc), turn))

    if cur["scouts"] + cur["demos"] + cur["intercs"] > 0:
        p1_attacks.append({
            "turn": cur_turn,
            "scouts": cur["scouts"],
            "demos": cur["demos"],
            "intercs": cur["intercs"],
            "mp_before": p1_mp_per_turn.get(cur_turn),
        })

    total = p1_scout_count + p1_demo_count + p1_interc_count
    sig = {
        "max_turn": max_turn,
        "p1_first_attack_turn": p1_first_attack_turn,
        "p1_unit_total": total,
        "p1_unit_mix": {
            "scouts": p1_scout_count,
            "demos": p1_demo_count,
            "interceptors": p1_interc_count,
        },
        "p1_unit_pct": {
            "scout_pct": p1_scout_count / max(total, 1) * 100,
            "demo_pct": p1_demo_count / max(total, 1) * 100,
            "interceptor_pct": p1_interc_count / max(total, 1) * 100,
        },
        "p1_total_attacks": len(p1_attacks),
        "p1_attacks": p1_attacks,
        "p1_spawn_top": Counter(p1_spawn_locations).most_common(15),
        "p1_breaches_for_us_count": len(p1_breaches),
        "p2_breaches_against_us_count": len(p2_breaches),
        "p1_breaches_top": Counter(b[0] for b in p1_breaches).most_common(10),
        "p2_breaches_top": Counter(b[0] for b in p2_breaches).most_common(10),
        "p1_walls_T15": p1_walls_per_turn.get(15) or p1_walls_per_turn.get(max(k for k in p1_walls_per_turn.keys() if k <= 15), default=[]) if p1_walls_per_turn else [],
        "p1_turrets_T15": p1_turrets_per_turn.get(15) or [],
        "p1_supports_T15": p1_supports_per_turn.get(15) or [],
        "p1_hp_final": p1_hp_history[-1][1] if p1_hp_history else None,
        "p2_hp_final": p2_hp_history[-1][1] if p2_hp_history else None,
    }
    return sig


def mirror_y(tile):
    """Mirror a P2 tile to P1 frame: (x, 27-y). Used to compare opp profile (P2) → replica (P1)."""
    return (tile[0], 27 - tile[1])


def compare_to_profile(sig, profile):
    """Compare a P1 replica signature to an opp profile (which is in P2 coordinates).

    Returns dict of similarity scores in [0,1] (1=perfect match).
    """
    scores = {}

    # 1. Unit mix similarity (1 - sum of abs diffs / 200, since pct points 0-100 each)
    expected = profile["unit_mix_avg"]
    actual = sig["p1_unit_pct"]
    diff = (
        abs(expected["scout_pct"] - actual["scout_pct"])
        + abs(expected["demo_pct"] - actual["demo_pct"])
        + abs(expected["interceptor_pct"] - actual["interceptor_pct"])
    )
    scores["unit_mix_similarity"] = max(0.0, 1.0 - diff / 200.0)

    # 2. First attack turn (within ±3 turns = full credit, >10 = 0)
    expected_fat = profile["first_attack_turn"].get("median")
    actual_fat = sig.get("p1_first_attack_turn")
    if expected_fat is None or actual_fat is None:
        scores["first_attack_similarity"] = None
    else:
        diff_t = abs(expected_fat - actual_fat)
        scores["first_attack_similarity"] = max(0.0, 1.0 - diff_t / 10.0)
        scores["first_attack_diff_turns"] = diff_t

    # 3. Spawn location overlap: top 10 spawn tiles in P1 frame.
    # Mirror profile spawns (P2 coords) to P1 frame.
    expected_spawn = set()
    for loc, ct in profile["spawn_locations_top"][:10]:
        try:
            x, y = loc
        except Exception:
            x, y = loc[0], loc[1]
        expected_spawn.add(mirror_y((x, y)))
    actual_spawn = set()
    for loc, ct in sig["p1_spawn_top"][:10]:
        actual_spawn.add(tuple(loc))
    if expected_spawn:
        scores["spawn_overlap_pct"] = len(expected_spawn & actual_spawn) / len(expected_spawn)
    else:
        scores["spawn_overlap_pct"] = None

    # 4. Defense layout overlap at T15: count of tiles matching, mirrored to P1
    def to_tile_set(common_list, top_n=20):
        out = set()
        for tile, ct, pct in common_list[:top_n]:
            if pct < 0.4:
                continue
            x, y = tile
            out.add(mirror_y((x, y)))
        return out

    exp_walls = to_tile_set(profile["defense_T15"]["common_walls"], 30)
    exp_turrets = to_tile_set(profile["defense_T15"]["common_turrets"], 15)
    exp_supports = to_tile_set(profile["defense_T15"]["common_supports"], 15)

    act_walls = set(sig["p1_walls_T15"])
    act_turrets = set(sig["p1_turrets_T15"])
    act_supports = set(sig["p1_supports_T15"])

    def jaccard(a, b):
        if not a and not b:
            return 1.0
        if not a or not b:
            return 0.0
        return len(a & b) / len(a | b)

    scores["wall_jaccard"] = jaccard(exp_walls, act_walls)
    scores["turret_jaccard"] = jaccard(exp_turrets, act_turrets)
    scores["support_jaccard"] = jaccard(exp_supports, act_supports)

    return scores


def run_match(algo_a_path, algo_b_path):
    """Run a match and return path to the produced replay."""
    out = subprocess.run(
        ["./tools/run.sh", str(algo_a_path), str(algo_b_path)],
        cwd=REPO_ROOT,
        capture_output=True,
        text=True,
        timeout=180,
    )
    # Find latest replay
    replays = sorted((REPO_ROOT / "replays").glob("*.replay"), key=lambda p: p.stat().st_mtime, reverse=True)
    return replays[0] if replays else None


def validate_single(replica_name, opp_name=None):
    if opp_name is None:
        opp_name = replica_name.replace("-replica", "")

    profile_path = REPO_ROOT / "research" / "replica_analysis" / "profiles" / f"{opp_name}.json"
    if not profile_path.exists():
        print(f"ERROR: profile not found at {profile_path}", file=sys.stderr)
        return 1

    with open(profile_path) as f:
        profile = json.load(f)

    print(f"=== Validating {replica_name} (vs profile of {opp_name}, n={profile['n_replays']}) ===\n")

    replica_path = REPO_ROOT / "algos" / "opponent_replicas" / replica_name
    opp_replica_path = REPO_ROOT / "algos" / "opponent_replicas" / "python-algo-replica"

    print(f"Running {replica_name} (P1) vs python-algo-replica (P2)...")
    replay = run_match(replica_path, opp_replica_path)
    if not replay:
        print("ERROR: no replay produced")
        return 1
    print(f"Replay: {replay.name}")

    sig = extract_p1_signature(replay)
    if not sig:
        print("ERROR: failed to extract signature")
        return 1

    print(f"\n--- Replica P1 signature ---")
    print(f"  unit_total={sig['p1_unit_total']} mix={sig['p1_unit_pct']}")
    print(f"  first_attack_turn={sig['p1_first_attack_turn']}")
    print(f"  total_attacks={sig['p1_total_attacks']}")
    print(f"  spawn_top: {sig['p1_spawn_top'][:5]}")
    print(f"  HP final: P1={sig['p1_hp_final']} P2={sig['p2_hp_final']}, turns={sig['max_turn']}")
    print(f"  walls T15: {len(sig['p1_walls_T15'])}, turrets T15: {len(sig['p1_turrets_T15'])}, supports T15: {len(sig['p1_supports_T15'])}")

    print(f"\n--- Profile expects (mean over {profile['n_replays']} replays) ---")
    print(f"  unit_mix: {profile['unit_mix_avg']}")
    print(f"  first_attack_turn median: {profile['first_attack_turn']['median']}")
    print(f"  attack_count avg: {profile['attack_count_avg']:.1f}")
    print(f"  game_turns avg: {profile['max_turns_avg']:.1f}")
    print(f"  spawn top 5: {profile['spawn_locations_top'][:5]}")

    scores = compare_to_profile(sig, profile)
    print(f"\n--- Similarity scores (1.0 = perfect) ---")
    for k, v in scores.items():
        if isinstance(v, float):
            print(f"  {k}: {v:.3f}")
        else:
            print(f"  {k}: {v}")

    out_dir = REPO_ROOT / "research" / "replica_analysis" / "validation"
    out_dir.mkdir(exist_ok=True)
    with open(out_dir / f"{replica_name}.json", "w") as f:
        json.dump({
            "replica": replica_name,
            "opponent_profile": opp_name,
            "replay": str(replay.relative_to(REPO_ROOT)),
            "p1_signature": sig,
            "profile_summary": {
                "n_replays": profile["n_replays"],
                "unit_mix_avg": profile["unit_mix_avg"],
                "first_attack_turn": profile["first_attack_turn"],
                "attack_count_avg": profile["attack_count_avg"],
                "max_turns_avg": profile["max_turns_avg"],
            },
            "scores": scores,
        }, f, default=str, indent=2)
    print(f"\nWrote {out_dir / f'{replica_name}.json'}")
    return 0


def validate_pair(replica_a, replica_b, ground_truth_replay):
    """Run replica_A vs replica_B; compare to a real cross-opponent replay."""
    print(f"=== Pair validation: {replica_a} vs {replica_b} ===")
    print(f"Ground truth replay: {ground_truth_replay}\n")

    # Parse ground truth signatures (BOTH players)
    gt_path = Path(ground_truth_replay)
    if not gt_path.is_absolute():
        gt_path = REPO_ROOT / gt_path
    gt_p1_sig = extract_p1_signature(gt_path)
    # For P2, we need a different extractor — for now, we'll just compare turn count and breaches
    # since extract_p1_signature gives P2 hp final too via p2_hp_final
    print(f"--- Ground truth ---")
    print(f"  Turns: {gt_p1_sig['max_turn']}")
    print(f"  P1 final HP: {gt_p1_sig['p1_hp_final']}, P2 final HP: {gt_p1_sig['p2_hp_final']}")
    print(f"  P1 unit_total={gt_p1_sig['p1_unit_total']} mix={gt_p1_sig['p1_unit_pct']}")
    print(f"  P1 first attack: {gt_p1_sig['p1_first_attack_turn']}, P1 attacks: {gt_p1_sig['p1_total_attacks']}")
    print(f"  P1 breaches for us: {gt_p1_sig['p1_breaches_for_us_count']} (top: {gt_p1_sig['p1_breaches_top'][:3]})")
    print(f"  P2 breaches against us: {gt_p1_sig['p2_breaches_against_us_count']} (top: {gt_p1_sig['p2_breaches_top'][:3]})")

    # Run pair
    replica_a_path = REPO_ROOT / "algos" / "opponent_replicas" / replica_a
    replica_b_path = REPO_ROOT / "algos" / "opponent_replicas" / replica_b
    print(f"\nRunning {replica_a} (P1) vs {replica_b} (P2)...")
    replay = run_match(replica_a_path, replica_b_path)
    if not replay:
        print("ERROR: no replay produced")
        return 1
    print(f"Replay: {replay.name}")
    sig = extract_p1_signature(replay)
    print(f"\n--- Replica match ---")
    print(f"  Turns: {sig['max_turn']}")
    print(f"  P1 final HP: {sig['p1_hp_final']}, P2 final HP: {sig['p2_hp_final']}")
    print(f"  P1 unit_total={sig['p1_unit_total']} mix={sig['p1_unit_pct']}")
    print(f"  P1 first attack: {sig['p1_first_attack_turn']}, P1 attacks: {sig['p1_total_attacks']}")
    print(f"  P1 breaches for us: {sig['p1_breaches_for_us_count']}")
    print(f"  P2 breaches against us: {sig['p2_breaches_against_us_count']}")

    # Compare
    print(f"\n--- Differences (replica - ground truth) ---")
    print(f"  Turn count: {sig['max_turn']} vs {gt_p1_sig['max_turn']} (Δ={sig['max_turn'] - gt_p1_sig['max_turn']})")
    if sig['p1_hp_final'] is not None and gt_p1_sig['p1_hp_final'] is not None:
        print(f"  P1 HP final: {sig['p1_hp_final']} vs {gt_p1_sig['p1_hp_final']} (Δ={sig['p1_hp_final'] - gt_p1_sig['p1_hp_final']})")
    print(f"  P1 unit_total: {sig['p1_unit_total']} vs {gt_p1_sig['p1_unit_total']}")
    print(f"  Same winner? GT: {'P1' if (gt_p1_sig['p1_hp_final'] or 0) > (gt_p1_sig['p2_hp_final'] or 0) else 'P2'}; Replica: {'P1' if (sig['p1_hp_final'] or 0) > (sig['p2_hp_final'] or 0) else 'P2'}")
    return 0


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        return 1
    if sys.argv[1] == "--pair":
        return validate_pair(sys.argv[2], sys.argv[3], sys.argv[4])
    return validate_single(sys.argv[1], sys.argv[2] if len(sys.argv) > 2 else None)


if __name__ == "__main__":
    sys.exit(main())
