#!/usr/bin/env python3
"""
Summarize a Citadel Terminal .replay file.

Usage:
    python3 tools/analyze_replay.py <path_to_replay>
    python3 tools/analyze_replay.py replays/my_algo_vs_baseline_*.replay

Produces a human-readable digest: winner, scoring timeline, per-turn damage,
unit attrition, compute-time anomalies. For single-file use only (pass one path).
"""
import json
import sys
from collections import Counter, defaultdict
from pathlib import Path


UNIT_NAMES = {
    "FF": "Wall",
    "EF": "Support",
    "DF": "Turret",
    "PI": "Scout",
    "EI": "Demolisher",
    "SI": "Interceptor",
}


def load_replay(path: Path):
    frames = []
    with open(path) as f:
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                frames.append(json.loads(line))
            except json.JSONDecodeError:
                # first line of replay may be a non-JSON header depending on version
                continue
    return frames


def summarize(path: Path) -> str:
    frames = load_replay(path)
    if not frames:
        return f"[analyze_replay] Could not parse any frames from {path}"

    game_frames = [f for f in frames if "turnInfo" in f]
    if not game_frames:
        return f"[analyze_replay] No game frames found in {path}"

    first, last = game_frames[0], game_frames[-1]
    final_turn = last["turnInfo"][1]
    p1_hp_final, p2_hp_final = last["p1Stats"][0], last["p2Stats"][0]
    winner = "P1" if p1_hp_final > p2_hp_final else ("P2" if p2_hp_final > p1_hp_final else "TIE")

    # Per-turn damage dealt to opponent HP (counted from breach events)
    breaches_by_turn = defaultdict(lambda: [0, 0])  # turn -> [p1_breaches, p2_breaches]
    breach_locations = {"P1": Counter(), "P2": Counter()}
    for f in game_frames:
        turn = f["turnInfo"][1]
        for ev in f.get("events", {}).get("breach", []):
            loc, _, _, _, owner = ev
            idx = 0 if owner == 1 else 1
            breaches_by_turn[turn][idx] += 1
            breach_locations["P1" if owner == 1 else "P2"][tuple(loc)] += 1

    # Deaths by unit type and player
    deaths = Counter()
    for f in game_frames:
        for d in f.get("events", {}).get("death", []):
            _, unit_type, _, _, player = d
            deaths[(player, unit_type)] += 1

    # Max compute time per side
    p1_max_ms = max(f["p1Stats"][3] for f in game_frames)
    p2_max_ms = max(f["p2Stats"][3] for f in game_frames)

    # MP curves (turn start only)
    deploy_frames = [f for f in game_frames if f["turnInfo"][0] == 0]
    mp_curve_p1 = [(f["turnInfo"][1], f["p1Stats"][2]) for f in deploy_frames]
    mp_curve_p2 = [(f["turnInfo"][1], f["p2Stats"][2]) for f in deploy_frames]

    lines = []
    lines.append(f"Replay: {path.name}")
    lines.append(f"Winner: {winner}   (final HP: P1={p1_hp_final:.0f}  P2={p2_hp_final:.0f})")
    lines.append(f"Duration: {final_turn + 1} turns, {len(game_frames)} total frames")
    lines.append("")
    lines.append("Breach summary:")
    total_p1 = sum(b[0] for b in breaches_by_turn.values())
    total_p2 = sum(b[1] for b in breaches_by_turn.values())
    lines.append(f"  P1 scored {total_p1} times,  P2 scored {total_p2} times")
    if breach_locations["P1"]:
        top_p1 = breach_locations["P1"].most_common(3)
        lines.append(f"  P1 top breach tiles: {top_p1}")
    if breach_locations["P2"]:
        top_p2 = breach_locations["P2"].most_common(3)
        lines.append(f"  P2 top breach tiles: {top_p2}")
    lines.append("")
    lines.append("Attrition (unit deaths):")
    by_player = defaultdict(Counter)
    for (pl, ut), n in deaths.items():
        by_player[pl][UNIT_NAMES.get(ut, ut)] += n
    for pl in (1, 2):
        summary = ", ".join(f"{k}={v}" for k, v in by_player[pl].most_common())
        lines.append(f"  P{pl}: {summary or '(none)'}")
    lines.append("")
    lines.append(f"Peak compute time: P1 {p1_max_ms:.0f}ms  P2 {p2_max_ms:.0f}ms  (budget: 15000ms)")

    # Notable turns: which turns had > 3 breaches?
    notable = [(t, b) for t, b in breaches_by_turn.items() if max(b) >= 3]
    if notable:
        lines.append("")
        lines.append("Big turns (≥3 breaches):")
        for t, (p1b, p2b) in sorted(notable):
            lines.append(f"  T{t}: P1 scored {p1b}, P2 scored {p2b}")

    return "\n".join(lines)


def main(argv):
    if len(argv) != 2:
        print(__doc__)
        return 1
    p = Path(argv[1]).expanduser()
    if not p.exists():
        print(f"Replay not found: {p}")
        return 2
    print(summarize(p))
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
