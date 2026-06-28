#!/usr/bin/env python3
"""profile_opponent.py — Aggregate behavioral profile across all replays of one opponent.

Usage:
    python3 profile_opponent.py <opponent_name>
    python3 profile_opponent.py --all   # write profile per opponent (>=4 replays)
"""
import json
import sys
from collections import Counter, defaultdict
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent.parent
OUT_DIR = REPO_ROOT / "research" / "replica_analysis"
PROFILES_DIR = OUT_DIR / "profiles"
PROFILES_DIR.mkdir(exist_ok=True)


def median(xs):
    if not xs:
        return None
    ys = sorted(xs)
    n = len(ys)
    if n % 2 == 1:
        return ys[n // 2]
    return (ys[n // 2 - 1] + ys[n // 2]) / 2


def profile_opponent(opp_name: str, registry: list) -> dict:
    """Build a multi-replay average behavioral profile for one opponent."""
    matches = [r for r in registry if r.get("opponent") == opp_name and r.get("ok")]
    if not matches:
        return {"opponent": opp_name, "n": 0}

    profile = {
        "opponent": opp_name,
        "n_replays": len(matches),
        "match_files": [m["file"] for m in matches],
        "elos": [m.get("opp_elo") for m in matches],
        "elos_avg": sum(e for e in [m.get("opp_elo") for m in matches] if e) / len(matches),
        "us_won_count": sum(1 for m in matches if m.get("result") == "win"),
        "us_lost_count": sum(1 for m in matches if m.get("result") == "loss"),
        "max_turns": [m.get("max_turn") for m in matches],
        "max_turns_avg": sum(m.get("max_turn", 0) or 0 for m in matches) / len(matches),
        "p2_names": list({m.get("p2_name") for m in matches if m.get("p2_name")}),
    }

    # Aggregate unit-mix across replays
    total_scouts = sum(m["opp_unit_mix"]["scouts"] for m in matches)
    total_demos = sum(m["opp_unit_mix"]["demos"] for m in matches)
    total_intercs = sum(m["opp_unit_mix"]["interceptors"] for m in matches)
    total_units = total_scouts + total_demos + total_intercs
    profile["unit_mix_avg"] = {
        "scouts": total_scouts / len(matches),
        "demos": total_demos / len(matches),
        "interceptors": total_intercs / len(matches),
        "scout_pct": total_scouts / max(total_units, 1) * 100,
        "demo_pct": total_demos / max(total_units, 1) * 100,
        "interceptor_pct": total_intercs / max(total_units, 1) * 100,
    }

    # First attack turn distribution
    fats = [m.get("opp_first_attack_turn") for m in matches if m.get("opp_first_attack_turn")]
    profile["first_attack_turn"] = {
        "median": median(fats),
        "min": min(fats) if fats else None,
        "max": max(fats) if fats else None,
        "values": fats,
    }

    # Spawn locations: aggregate top across all replays
    spawn_loc_counter = Counter()
    for m in matches:
        for loc, ct in m.get("opp_spawn_locations_top", []) or []:
            # loc may be list or tuple
            try:
                key = tuple(loc)
            except Exception:
                key = tuple(eval(loc)) if isinstance(loc, str) else None
            if key:
                spawn_loc_counter[key] += ct
    profile["spawn_locations_top"] = spawn_loc_counter.most_common(15)

    # Defense layout at T15 / T25 / T50 — pick most-common occupied tiles
    def aggregate_snapshot(key):
        tile_walls = Counter()
        tile_supports = Counter()
        tile_turrets = Counter()
        n = 0
        for m in matches:
            snap = m.get(key)
            if not snap:
                continue
            n += 1
            for tile in snap.get("walls", []):
                tile_walls[tuple(tile)] += 1
            for tile in snap.get("supports", []):
                tile_supports[tuple(tile)] += 1
            for tile in snap.get("turrets", []):
                tile_turrets[tuple(tile)] += 1
        return {
            "n_with_snapshot": n,
            "common_walls": [(list(t), c, c/max(n,1)) for t, c in tile_walls.most_common(40)],
            "common_supports": [(list(t), c, c/max(n,1)) for t, c in tile_supports.most_common(20)],
            "common_turrets": [(list(t), c, c/max(n,1)) for t, c in tile_turrets.most_common(30)],
        }

    profile["defense_T5"] = aggregate_snapshot("opp_snapshot_T5")
    profile["defense_T10"] = aggregate_snapshot("opp_snapshot_T10")
    profile["defense_T15"] = aggregate_snapshot("opp_snapshot_T15")
    profile["defense_T25"] = aggregate_snapshot("opp_snapshot_T25")
    profile["defense_T50"] = aggregate_snapshot("opp_snapshot_T50")

    # Attack pattern: list of (turn, scout_count, demo_count, interceptor_count, mp_before)
    # for the WINNING replays — that's how the opp wins. Aggregate.
    attack_signatures = []
    for m in matches:
        for atk in m.get("opp_attacks", []) or []:
            attack_signatures.append((
                atk.get("turn"),
                atk.get("scouts"),
                atk.get("demos"),
                atk.get("intercs"),
                atk.get("mp_before"),
            ))

    # MP threshold for triggering attacks
    mp_at_attack = [a[4] for a in attack_signatures if a[4] is not None and (a[1] + a[2] + a[3]) >= 5]
    profile["mp_at_attack"] = {
        "median": median(mp_at_attack),
        "min": min(mp_at_attack) if mp_at_attack else None,
        "max": max(mp_at_attack) if mp_at_attack else None,
        "n_samples": len(mp_at_attack),
    }

    # Turn-by-turn modal action: attack vs no-attack
    turn_attack_freq = defaultdict(int)
    turn_count = defaultdict(int)
    for m in matches:
        max_t = m.get("max_turn", 0)
        attack_turns = {a.get("turn") for a in (m.get("opp_attacks") or []) if a.get("scouts", 0) + a.get("demos", 0) + a.get("intercs", 0) >= 5}
        for t in range(max_t + 1):
            turn_count[t] += 1
            if t in attack_turns:
                turn_attack_freq[t] += 1
    profile["attack_frequency_by_turn"] = {
        t: {"attacks": turn_attack_freq[t], "out_of": turn_count[t], "pct": turn_attack_freq[t] / max(turn_count[t], 1)}
        for t in sorted(turn_count.keys()) if t <= 50
    }

    # Per-attack breakdown
    profile["attack_signatures_first10"] = attack_signatures[:30]
    profile["attack_count_avg"] = sum(m.get("opp_total_attacks", 0) for m in matches) / len(matches)

    # Where opp scored on us (breach locations on our side)
    breach_us_counter = Counter()
    breach_them_counter = Counter()
    for m in matches:
        for loc, ct in m.get("breaches_against_us_top", []) or []:
            try:
                key = tuple(loc)
            except Exception:
                key = tuple(eval(loc)) if isinstance(loc, str) else None
            if key:
                breach_us_counter[key] += ct
        for loc, ct in m.get("breaches_for_us_top", []) or []:
            try:
                key = tuple(loc)
            except Exception:
                key = tuple(eval(loc)) if isinstance(loc, str) else None
            if key:
                breach_them_counter[key] += ct
    profile["breaches_against_us"] = breach_us_counter.most_common(20)
    profile["breaches_for_us"] = breach_them_counter.most_common(20)

    return profile


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        return 1
    registry_path = OUT_DIR / "replay_registry.json"
    with open(registry_path) as f:
        registry = json.load(f)

    if sys.argv[1] == "--all":
        # Build profiles for all opponents with >= 4 replays
        opp_counts = Counter(r["opponent"] for r in registry if r.get("ok") and r.get("opponent"))
        for opp, ct in opp_counts.most_common():
            if ct < 4:
                continue
            profile = profile_opponent(opp, registry)
            out = PROFILES_DIR / f"{opp}.json"
            with open(out, "w") as f:
                json.dump(profile, f, default=str, indent=2)
            print(f"Wrote {out} (n={ct})")
        return 0

    opp = sys.argv[1]
    profile = profile_opponent(opp, registry)
    out = PROFILES_DIR / f"{opp}.json"
    with open(out, "w") as f:
        json.dump(profile, f, default=str, indent=2)
    print(f"Wrote {out}")
    print(f"\n=== Profile summary for '{opp}' ===")
    print(f"  Replays: {profile['n_replays']}")
    print(f"  Avg ELO: {profile['elos_avg']:.0f}")
    print(f"  Our W/L: {profile['us_won_count']}/{profile['us_lost_count']}")
    print(f"  Avg turns: {profile['max_turns_avg']:.1f}")
    print(f"  Unit mix: scouts={profile['unit_mix_avg']['scout_pct']:.1f}% demos={profile['unit_mix_avg']['demo_pct']:.1f}% intercs={profile['unit_mix_avg']['interceptor_pct']:.1f}%")
    print(f"  First attack turn (median): {profile['first_attack_turn']['median']}")
    print(f"  Avg total attacks: {profile['attack_count_avg']:.1f}")
    print(f"  Spawn loc top:")
    for loc, ct in profile["spawn_locations_top"][:8]:
        print(f"    {loc}: {ct}")
    print(f"  T15 defense (top tiles):")
    for tile, ct, pct in profile["defense_T15"]["common_turrets"][:8]:
        print(f"    turret {tile}: {ct} ({pct:.0%})")
    return 0


if __name__ == "__main__":
    sys.exit(main())
