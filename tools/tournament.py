#!/usr/bin/env python3
"""
Round-robin tournament runner for Citadel Terminal algos.

Every ordered pair of algos plays once (so each algo plays each opponent twice —
once as P1, once as P2). Matches run in parallel across CPU cores; each match
executes in its own tempdir so replay files don't collide.

Usage:
    python3 tools/tournament.py <algo_1> <algo_2> [algo_3 ...] [--workers=auto]
    python3 tools/tournament.py algos/v1 algos/v2 algos/v3

For a detailed write-up of the eval methodology see docs/CLAUDE_WORKFLOW.md.
"""
import json
import os
import shutil
import subprocess
import sys
import tempfile
import time
from concurrent.futures import ProcessPoolExecutor, as_completed
from itertools import permutations
from pathlib import Path


from match_runner import REPO_ROOT, resolve_algo, run_one_match as _run_match


def run_one_match(task):
    result = _run_match(task)
    result['pair'] = result['game_id']
    if 'error' not in result:
        side = {'p1': 1, 'p2': 2, 'tie': 0}[result['winner']]
        result['winner_side'] = side
        result['winner_name'] = result['p1'] if side == 1 else (result['p2'] if side == 2 else 'tie')
    return result


def parse_args(argv):
    positional = [a for a in argv[1:] if not a.startswith("--")]
    flags = [a for a in argv[1:] if a.startswith("--")]
    if len(positional) < 2:
        print(__doc__)
        sys.exit(1)
    algos = [resolve_algo(a) for a in positional]
    if len({a.name for a in algos}) != len(algos):
        raise ValueError("algo directory names must be unique in a tournament")
    workers = None
    for f in flags:
        if f.startswith("--workers="):
            v = f.split("=", 1)[1]
            workers = None if v == "auto" else int(v)
    if workers is None:
        workers = min(os.cpu_count() or 4, len(algos) * (len(algos) - 1))
    if workers < 1:
        raise ValueError("workers must be positive")
    return algos, workers


def main(argv):
    algos, workers = parse_args(argv)
    ts = time.strftime("%Y%m%d_%H%M%S")
    out_dir = REPO_ROOT / "replays" / f"tournament_{ts}"
    out_dir.mkdir(parents=True, exist_ok=True)

    pairs = list(permutations(algos, 2))
    tasks = [(str(a1), str(a2), str(out_dir), i + 1, 180) for i, (a1, a2) in enumerate(pairs)]

    print(f"[tournament] {len(algos)} algos, {len(pairs)} matches total, {workers} workers.")
    print(f"[tournament] Output: {out_dir}\n")

    wall_start = time.time()
    wins = {a.name: 0 for a in algos}
    games = {a.name: 0 for a in algos}
    matrix = {}  # (p1_name, p2_name) -> result dict
    errors = 0
    failed_matches = []

    with ProcessPoolExecutor(max_workers=workers) as pool:
        future_to_task = {pool.submit(run_one_match, t): t for t in tasks}
        for fut in as_completed(future_to_task):
            r = fut.result()
            if "error" in r:
                errors += 1
                failed_matches.append(r)
                print(f"  pair {r['pair']}: ERROR — {r['error']}")
                continue
            print(f"  {r['p1']:<25} vs {r['p2']:<25}  "
                  f"→ {r['winner_name']:<22}  "
                  f"({r['p1_hp']:.0f}-{r['p2_hp']:.0f})  {r['duration']:.1f}s")
            if r["winner_name"] != "tie":
                wins[r["winner_name"]] += 1
            games[r["p1"]] += 1
            games[r["p2"]] += 1
            matrix[(r["p1"], r["p2"])] = r

    wall_elapsed = time.time() - wall_start

    print("\n" + "=" * 60)
    print(f"TOURNAMENT STANDINGS (wall {wall_elapsed:.1f}s, {len(pairs)} matches)")
    print("=" * 60)
    ranked = sorted(wins.items(), key=lambda kv: -kv[1])
    for name, w in ranked:
        g = games[name]
        pct = (100 * w / g) if g else 0
        print(f"  {name:<30} {w:>2} / {g:<2}  ({pct:>5.1f}%)")
    if errors:
        print(f"\n  errors: {errors}")

    # Win matrix — who beats whom
    names = [a.name for a in algos]
    print("\nWin matrix (row = P1, col = P2; 'W' = row wins, 'L' = loses, '·' = tie, '.' = no game):")
    header = "  " + " " * 18 + "".join(f"{n[:4]:>6}" for n in names)
    print(header)
    for p1 in names:
        row = f"  {p1[:18]:<18}"
        for p2 in names:
            if p1 == p2:
                row += "    — "
            elif (p1, p2) in matrix:
                r = matrix[(p1, p2)]
                if r["winner_name"] == p1:
                    row += "     W"
                elif r["winner_name"] == p2:
                    row += "     L"
                else:
                    row += "     ·"
            else:
                row += "     ."
        print(row)

    summary_path = out_dir / "summary.json"
    with open(summary_path, "w") as f:
        json.dump({
            "algos": [a.name for a in algos],
            "wins": wins,
            "games": games,
            "matches": [
                {k: (str(v) if isinstance(v, Path) else v) for k, v in m.items()}
                for m in matrix.values()
            ],
            "wall_seconds": wall_elapsed,
            "workers": workers,
            "errors": failed_matches,
            "complete": not failed_matches,
        }, f, indent=2)
    print(f"\n[tournament] Summary: {summary_path}")
    return 1 if errors else 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
