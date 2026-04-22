#!/usr/bin/env python3
"""
Round-robin tournament runner for Citadel Terminal algos.

Usage:
    python3 tools/tournament.py <algo_dir_1> <algo_dir_2> [algo_dir_3 ...]
    python3 tools/tournament.py algos/v1 algos/v2 algos/v3

Every ordered pair plays once (so each algo plays each opponent twice — once as
player 1, once as player 2). Replays are saved under replays/tournament_<ts>/.
A win-rate table is printed at the end.

Requires: Python 3.6+, Java 10+ on PATH.
"""
import json
import os
import subprocess
import sys
import time
from itertools import permutations
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parent.parent
STARTER_KIT = REPO_ROOT / "C1GamesStarterKit-master"
RUN_MATCH = STARTER_KIT / "scripts" / "run_match.py"


def resolve_algo(name: str) -> Path:
    """Accept either a full path or a shortname under algos/."""
    p = Path(name)
    if p.is_dir():
        return p.resolve()
    candidate = REPO_ROOT / "algos" / name
    if candidate.is_dir():
        return candidate.resolve()
    raise FileNotFoundError(f"Cannot find algo '{name}' as path or under algos/")


def latest_replay_in(directory: Path) -> Path:
    replays = sorted(directory.glob("*.replay"), key=lambda p: p.stat().st_mtime)
    if not replays:
        raise RuntimeError(f"No .replay files produced in {directory}")
    return replays[-1]


def winner_from_replay(path: Path) -> tuple:
    """Parse the last frame of a replay to determine winner and final HP."""
    with open(path) as f:
        last = None
        for line in f:
            if line.strip():
                last = line
    final = json.loads(last)
    p1_hp = final["p1Stats"][0]
    p2_hp = final["p2Stats"][0]
    winner = 1 if p1_hp > p2_hp else (2 if p2_hp > p1_hp else 0)
    return winner, p1_hp, p2_hp


def run_one_match(a1: Path, a2: Path, out_dir: Path, timeout_sec: int = 180) -> dict:
    start = time.time()
    proc = subprocess.run(
        ["python3", str(RUN_MATCH), str(a1), str(a2)],
        capture_output=True, text=True, timeout=timeout_sec,
    )
    duration = time.time() - start

    replay_src = latest_replay_in(STARTER_KIT / "replays")
    fname = f"{a1.name}_vs_{a2.name}.replay"
    dest = out_dir / fname
    replay_src.rename(dest)

    winner, p1_hp, p2_hp = winner_from_replay(dest)
    return {
        "p1": a1.name,
        "p2": a2.name,
        "winner_side": winner,
        "winner_name": a1.name if winner == 1 else (a2.name if winner == 2 else "tie"),
        "p1_hp": p1_hp,
        "p2_hp": p2_hp,
        "replay": dest,
        "duration_sec": round(duration, 1),
        "stdout_tail": proc.stdout.strip().splitlines()[-5:] if proc.stdout else [],
    }


def main(argv):
    if len(argv) < 3:
        print(__doc__)
        return 1
    algos = [resolve_algo(a) for a in argv[1:]]

    ts = time.strftime("%Y%m%d_%H%M%S")
    out_dir = REPO_ROOT / "replays" / f"tournament_{ts}"
    out_dir.mkdir(parents=True, exist_ok=True)

    wins = {a.name: 0 for a in algos}
    total_games = {a.name: 0 for a in algos}
    all_results = []

    pairs = list(permutations(algos, 2))
    print(f"[tournament] {len(algos)} algos, {len(pairs)} matches total.")
    print(f"[tournament] Output: {out_dir}\n")

    for i, (a1, a2) in enumerate(pairs, 1):
        print(f"[{i}/{len(pairs)}] {a1.name} (P1) vs {a2.name} (P2)... ", end="", flush=True)
        try:
            r = run_one_match(a1, a2, out_dir)
        except Exception as e:
            print(f"ERROR: {e}")
            continue
        print(f"{r['winner_name']} wins ({r['p1_hp']:.0f} vs {r['p2_hp']:.0f}) "
              f"in {r['duration_sec']}s")
        total_games[a1.name] += 1
        total_games[a2.name] += 1
        if r["winner_name"] != "tie":
            wins[r["winner_name"]] += 1
        all_results.append(r)

    print("\n" + "=" * 60)
    print("TOURNAMENT STANDINGS")
    print("=" * 60)
    ranked = sorted(wins.items(), key=lambda kv: -kv[1])
    for name, w in ranked:
        g = total_games[name]
        pct = (100 * w / g) if g else 0
        print(f"  {name:<30} {w:>2} / {g:<2}  ({pct:>5.1f}%)")

    summary_path = out_dir / "summary.json"
    with open(summary_path, "w") as f:
        json.dump(
            {
                "algos": [a.name for a in algos],
                "wins": wins,
                "total_games": total_games,
                "matches": [
                    {k: (str(v) if isinstance(v, Path) else v) for k, v in r.items()}
                    for r in all_results
                ],
            },
            f,
            indent=2,
        )
    print(f"\n[tournament] Summary: {summary_path}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
