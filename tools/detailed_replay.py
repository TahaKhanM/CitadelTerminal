#!/usr/bin/env python3
"""
Deep analysis of a single Citadel Terminal replay, with extra detail for
official competition replays (downloaded from https://terminal.c1games.com).

Official replays carry:
  - The LIVE server config as the first line (authoritative numbers)
  - Unit IDs that let you track individuals across frames
  - More comprehensive `events` (damage, shield, move, melee, selfDestruct)
  - Richer metadata (name, wins, rating trends)

This tool produces a turn-by-turn digest plus specific strategy-signal reports:
  - Per-turn SP/MP/HP timeline for both players
  - Spawn distribution (where the enemy deployed mobiles)
  - Structure placement timeline (what was built where, when)
  - Upgrade timeline
  - Most-hit structures (what YOU lost the most of, and where enemy focused)
  - Shield events (how much shielding you received; which Supports fired)
  - Scoring timeline with per-breach attribution
  - Compute-time headroom

Usage:
    python3 tools/detailed_replay.py <replay>                  # full report
    python3 tools/detailed_replay.py <replay> --section spawn  # one section
    python3 tools/detailed_replay.py <replay> --turn 15        # deep-dive one turn
    python3 tools/detailed_replay.py <replay> --json            # machine-readable

Sections: config, economy, structures, upgrades, scoring, attrition, shielding,
          spawns, damage, compute, turn
"""
import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

FILENAME_ELO_RE = re.compile(r"_vs_(\d{3,4})_elo_(win|loss)", re.IGNORECASE)


def _parse_filename_tags(path):
    m = FILENAME_ELO_RE.search(Path(path).name)
    if m:
        return int(m.group(1)), m.group(2).lower()
    return None, None


UNIT_NAMES = {"FF": "Wall", "EF": "Support", "DF": "Turret",
              "PI": "Scout", "EI": "Demolisher", "SI": "Interceptor"}
UNIT_IDX_NAMES = {0: "Wall", 1: "Support", 2: "Turret",
                  3: "Scout", 4: "Demolisher", 5: "Interceptor",
                  6: "Remove", 7: "Upgrade"}


def load_replay(path: Path):
    with open(path) as f:
        lines = [l.strip() for l in f if l.strip()]
    # First line MIGHT be a config (official replay) or might be a turnInfo frame (local).
    first = json.loads(lines[0])
    config = first if "unitInformation" in first else None
    frames = []
    start = 1 if config else 0
    for ln in lines[start:]:
        try:
            obj = json.loads(ln)
        except json.JSONDecodeError:
            continue
        if "turnInfo" in obj:
            frames.append(obj)
    return config, frames


def _unit_name(val):
    if isinstance(val, (int, float)):
        return UNIT_IDX_NAMES.get(int(val), f"type_{val}")
    return UNIT_NAMES.get(val, str(val))


def section_config(config):
    if not config:
        print("(No config in replay — this looks like a local replay. Official downloads include the live config.)")
        return
    print("=== LIVE SERVER CONFIG ===")
    for u in config.get("unitInformation", []):
        sh = u.get("shorthand", "?")
        print(f"\n[{sh}] {u.get('display', '')}")
        for k, v in u.items():
            if k in ("upgrade", "icon", "iconxScale", "iconyScale", "display", "shorthand"):
                continue
            print(f"  {k}: {v}")
        if "upgrade" in u:
            print("  UPGRADE:")
            for k, v in u["upgrade"].items():
                if k in ("icon", "iconxScale", "iconyScale", "display"):
                    continue
                print(f"    {k}: {v}")
    print("\nRESOURCES:")
    for k, v in config.get("resources", {}).items():
        print(f"  {k}: {v}")


def section_economy(frames):
    print("=== ECONOMY TIMELINE ===")
    print(f"{'Turn':>4}  {'P1_HP':>6} {'P1_SP':>6} {'P1_MP':>6}  |  "
          f"{'P2_HP':>6} {'P2_SP':>6} {'P2_MP':>6}")
    seen = set()
    for f in frames:
        if f["turnInfo"][0] != 0:
            continue
        t = f["turnInfo"][1]
        if t in seen:
            continue
        seen.add(t)
        p1 = f["p1Stats"]
        p2 = f["p2Stats"]
        print(f"{t:>4}  {p1[0]:>6.0f} {p1[1]:>6.1f} {p1[2]:>6.1f}  |  "
              f"{p2[0]:>6.0f} {p2[1]:>6.1f} {p2[2]:>6.1f}")


def section_structures(frames):
    """Structure placements over time (spawn events with type in structure categories)."""
    print("=== STRUCTURE PLACEMENT TIMELINE ===")
    first_seen = {}   # (owner, type, loc) -> turn
    for f in frames:
        t = f["turnInfo"][1]
        for ev in f.get("events", {}).get("spawn", []):
            if len(ev) < 4:
                continue
            loc = tuple(ev[0]) if isinstance(ev[0], list) else ev[0]
            utype = ev[1]
            owner = ev[-1] if isinstance(ev[-1], (int, float)) else None
            if owner is None:
                continue
            name = _unit_name(utype)
            if name in ("Wall", "Support", "Turret"):
                key = (owner, name, loc)
                if key not in first_seen:
                    first_seen[key] = t
    by_owner = defaultdict(list)
    for (owner, name, loc), t in first_seen.items():
        by_owner[owner].append((t, name, loc))
    for owner in sorted(by_owner):
        entries = sorted(by_owner[owner])
        print(f"\n  P{owner} structures ({len(entries)} total):")
        for t, name, loc in entries[:50]:
            print(f"    T{t:>3}  {name:<8}  @{loc}")
        if len(entries) > 50:
            print(f"    ... {len(entries) - 50} more")


def section_upgrades(frames):
    """Reconstruct upgrade timeline by tracking structures with rising HP > base HP."""
    print("=== UPGRADES TIMELINE ===")
    # Use p1Units/p2Units snapshots on deploy frames to detect upgraded structures
    # (walls > 60 HP, supports > 1 HP, turrets > 60 HP are upgraded).
    upgrade_times = {}
    for f in frames:
        if f["turnInfo"][0] != 0:
            continue
        t = f["turnInfo"][1]
        for player_idx, key in enumerate(["p1Units", "p2Units"], start=1):
            units = f[key]
            # index 0 = Walls, 1 = Supports, 2 = Turrets
            for type_idx, thresh in [(0, 60), (1, 1), (2, 60)]:
                for u in units[type_idx]:
                    x, y, hp = u[0], u[1], u[2]
                    if hp > thresh:
                        k = (player_idx, type_idx, x, y)
                        if k not in upgrade_times:
                            upgrade_times[k] = t
    if not upgrade_times:
        print("  (no upgrades detected)")
        return
    by_owner = defaultdict(list)
    for (pl, ti, x, y), t in upgrade_times.items():
        by_owner[pl].append((t, _unit_name(ti), (x, y)))
    for pl in sorted(by_owner):
        entries = sorted(by_owner[pl])
        print(f"\n  P{pl} upgrades ({len(entries)}):")
        for t, name, loc in entries[:30]:
            print(f"    T{t:>3}  {name:<8}  @{loc}")


def section_scoring(frames):
    print("=== SCORING (BREACH) TIMELINE ===")
    breaches = []
    for f in frames:
        t = f["turnInfo"][1]
        for ev in f.get("events", {}).get("breach", []):
            # Shape: [location, unit_type_or_damage, ..., owner]
            if len(ev) < 5:
                continue
            loc, owner = ev[0], ev[-1]
            breaches.append((t, tuple(loc) if isinstance(loc, list) else loc, owner))
    p1 = [b for b in breaches if b[2] == 1]
    p2 = [b for b in breaches if b[2] == 2]
    print(f"  P1 total: {len(p1)}   P2 total: {len(p2)}")
    print("  P1 breach tiles:", Counter(b[1] for b in p1).most_common(5))
    print("  P2 breach tiles:", Counter(b[1] for b in p2).most_common(5))
    # Timeline
    print("\n  Turn-by-turn breach count:")
    by_turn = defaultdict(lambda: [0, 0])
    for t, _, owner in breaches:
        by_turn[t][0 if owner == 1 else 1] += 1
    for t in sorted(by_turn):
        p1n, p2n = by_turn[t]
        marker = "⚠" if (p1n >= 3 or p2n >= 3) else " "
        print(f"    T{t:>3} {marker} P1={p1n:>2}  P2={p2n:>2}")


def section_attrition(frames):
    print("=== UNIT ATTRITION (deaths) ===")
    deaths = Counter()
    by_loc = defaultdict(lambda: Counter())
    for f in frames:
        for d in f.get("events", {}).get("death", []):
            if len(d) < 2:
                continue
            utype = d[1]
            player = None
            for field in reversed(d):
                if isinstance(field, (int, float)) and field in (1, 2):
                    player = int(field)
                    break
            if player is None:
                continue
            name = _unit_name(utype)
            deaths[(player, name)] += 1
            loc = d[0]
            if isinstance(loc, list):
                by_loc[(player, name)][tuple(loc)] += 1

    by_player = defaultdict(Counter)
    for (pl, name), n in deaths.items():
        by_player[pl][name] += n
    for pl in (1, 2):
        summary = ", ".join(f"{k}={v}" for k, v in by_player[pl].most_common())
        print(f"  P{pl}: {summary or '(none)'}")

    # Hot spots — where did each unit die most?
    print("\n  Top death locations by player/type:")
    for (pl, name), locs in by_loc.items():
        top = locs.most_common(3)
        if top and top[0][1] >= 3:
            print(f"    P{pl} {name}: {top}")


def section_shielding(frames):
    print("=== SHIELDING ===")
    shield_events = []
    for f in frames:
        for ev in f.get("events", {}).get("shield", []):
            shield_events.append(ev)
    if not shield_events:
        print("  (no shield events)")
        return
    # Shield event shape (official): [loc1, loc2, shield_amount, ..., owner]
    total_by_player = defaultdict(float)
    count_by_player = defaultdict(int)
    for ev in shield_events:
        # Try to find an amount-looking float and an owner-looking int
        owner = None
        amount = None
        for field in reversed(ev):
            if isinstance(field, (int, float)):
                if owner is None and field in (1, 2):
                    owner = int(field)
                elif amount is None and field > 0 and field not in (1, 2):
                    amount = float(field)
        if owner is not None and amount is not None:
            total_by_player[owner] += amount
            count_by_player[owner] += 1
    for pl in sorted(total_by_player):
        n = count_by_player[pl]
        tot = total_by_player[pl]
        avg = tot / n if n else 0
        print(f"  P{pl}: {n} shield events, {tot:.0f} total HP, {avg:.1f} avg/event")


def section_spawns(frames):
    print("=== MOBILE SPAWN DISTRIBUTION ===")
    # Mobile unit types are indices 3, 4, 5
    spawn_tiles = {1: Counter(), 2: Counter()}
    spawn_type = {1: Counter(), 2: Counter()}
    for f in frames:
        for ev in f.get("events", {}).get("spawn", []):
            if len(ev) < 4:
                continue
            utype = ev[1]
            if _unit_name(utype) not in ("Scout", "Demolisher", "Interceptor"):
                continue
            loc = tuple(ev[0]) if isinstance(ev[0], list) else ev[0]
            owner = ev[-1] if isinstance(ev[-1], (int, float)) else None
            if owner in (1, 2):
                spawn_tiles[owner][loc] += 1
                spawn_type[owner][_unit_name(utype)] += 1
    for pl in (1, 2):
        print(f"\n  P{pl} mobile spawns:")
        print(f"    types: {dict(spawn_type[pl])}")
        top = spawn_tiles[pl].most_common(5)
        for loc, n in top:
            print(f"    {loc}: {n}")


def section_damage(frames):
    print("=== DAMAGE DEALT (from damage events) ===")
    dmg_by_player = defaultdict(float)
    dmg_count = defaultdict(int)
    for f in frames:
        for ev in f.get("events", {}).get("damage", []):
            owner = None
            amount = None
            for field in reversed(ev):
                if isinstance(field, (int, float)):
                    if owner is None and field in (1, 2):
                        owner = int(field)
                    elif amount is None and field > 0 and field < 500:
                        amount = float(field)
            if owner and amount:
                dmg_by_player[owner] += amount
                dmg_count[owner] += 1
    for pl in sorted(dmg_by_player):
        print(f"  P{pl}: {dmg_count[pl]} damage events, {dmg_by_player[pl]:.0f} total damage dealt")


def section_compute(frames):
    print("=== COMPUTE TIME HEADROOM ===")
    deploy_frames = [f for f in frames if f["turnInfo"][0] == 0]
    for key, label in [("p1Stats", "P1"), ("p2Stats", "P2")]:
        times = [(f["turnInfo"][1], f[key][3] if len(f[key]) > 3 else 0) for f in deploy_frames]
        if not times:
            continue
        just = [t for _, t in times]
        maxt = max(just)
        print(f"\n  {label}:  mean={sum(just)/len(just):.1f}ms  max={maxt:.0f}ms (turn {max(times, key=lambda tt: tt[1])[0]})")
        if maxt > 10000:
            print(f"    ⚠ CRITICAL — timeouts likely on server")
        elif maxt > 5000:
            print(f"    ! CONCERN — tight; investigate spike turns")
        else:
            print(f"    ✓ comfortable")


def section_turn(frames, turn_num):
    print(f"=== TURN {turn_num} DEEP-DIVE ===")
    turn_frames = [f for f in frames if f["turnInfo"][1] == turn_num]
    if not turn_frames:
        print(f"  No frames for turn {turn_num}")
        return
    deploy = next((f for f in turn_frames if f["turnInfo"][0] == 0), None)
    if deploy:
        p1, p2 = deploy["p1Stats"], deploy["p2Stats"]
        print(f"  Deploy-phase state: P1 HP={p1[0]:.0f} SP={p1[1]:.1f} MP={p1[2]:.1f}  |  "
              f"P2 HP={p2[0]:.0f} SP={p2[1]:.1f} MP={p2[2]:.1f}")
    # Events across the turn
    aggregated = defaultdict(list)
    for f in turn_frames:
        for ek, evs in f.get("events", {}).items():
            aggregated[ek].extend(evs)
    print(f"\n  Events this turn:")
    for ek in ("spawn", "breach", "death", "damage", "shield", "selfDestruct"):
        if aggregated[ek]:
            print(f"    {ek}: {len(aggregated[ek])}")
            if ek == "breach":
                for ev in aggregated[ek][:10]:
                    print(f"      {ev}")
            elif ek in ("selfDestruct",):
                for ev in aggregated[ek][:5]:
                    print(f"      {ev}")


SECTIONS = {
    "config": section_config,
    "economy": section_economy,
    "structures": section_structures,
    "upgrades": section_upgrades,
    "scoring": section_scoring,
    "attrition": section_attrition,
    "shielding": section_shielding,
    "spawns": section_spawns,
    "damage": section_damage,
    "compute": section_compute,
}


def emit_json_summary(config, frames):
    """Machine-readable summary for scripts that want structured output."""
    deploy_frames = [f for f in frames if f["turnInfo"][0] == 0]
    if not frames:
        return {"error": "no frames"}
    last = frames[-1]
    breaches = {"p1": 0, "p2": 0}
    for f in frames:
        for ev in f.get("events", {}).get("breach", []):
            owner = ev[-1] if isinstance(ev[-1], (int, float)) else 0
            if owner == 1:
                breaches["p1"] += 1
            elif owner == 2:
                breaches["p2"] += 1
    p1_hp = float(last["p1Stats"][0])
    p2_hp = float(last["p2Stats"][0])
    winner = "p1" if p1_hp > p2_hp else ("p2" if p2_hp > p1_hp else "tie")
    return {
        "has_config": config is not None,
        "turns": int(last["turnInfo"][1]),
        "winner": winner,
        "p1_final_hp": p1_hp,
        "p2_final_hp": p2_hp,
        "p1_breaches": breaches["p1"],
        "p2_breaches": breaches["p2"],
        "p1_max_compute_ms": max((f["p1Stats"][3] for f in deploy_frames if len(f["p1Stats"]) > 3), default=0),
        "p2_max_compute_ms": max((f["p2Stats"][3] for f in deploy_frames if len(f["p2Stats"]) > 3), default=0),
    }


def main(argv):
    if len(argv) < 2 or argv[1] in ("-h", "--help"):
        print(__doc__)
        return 0
    path = Path(argv[1]).expanduser()
    if not path.exists():
        print(f"Replay not found: {path}")
        return 2

    want_json = "--json" in argv
    section = None
    if "--section" in argv:
        section = argv[argv.index("--section") + 1]
    turn_num = None
    if "--turn" in argv:
        turn_num = int(argv[argv.index("--turn") + 1])

    config, frames = load_replay(path)
    opp_elo, outcome = _parse_filename_tags(path)

    print(f"=== Replay: {path.name} ===")
    print(f"Frames: {len(frames)}, config header: {'yes (official)' if config else 'no (local)'}")
    if opp_elo is not None:
        print(f"Filename tag: opponent ELO {opp_elo}, outcome = {outcome.upper()}")
    print()

    if want_json:
        print(json.dumps(emit_json_summary(config, frames), indent=2))
        return 0

    if turn_num is not None:
        section_turn(frames, turn_num)
        return 0

    sections_to_run = [section] if section else list(SECTIONS.keys())
    for s in sections_to_run:
        if s not in SECTIONS:
            print(f"(Unknown section '{s}' — available: {list(SECTIONS)})")
            continue
        fn = SECTIONS[s]
        if s == "config":
            fn(config)
        else:
            fn(frames)
        print()
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
