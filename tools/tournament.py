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


REPO_ROOT = Path(__file__).resolve().parent.parent
STARTER_KIT = REPO_ROOT / "C1GamesStarterKit-master"
ENGINE_JAR = STARTER_KIT / "engine.jar"
CONFIG_JSON = STARTER_KIT / "game-configs.json"


def resolve_algo(name: str) -> Path:
    p = Path(name)
    if p.is_dir():
        return p.resolve()
    candidate = REPO_ROOT / "algos" / name
    if candidate.is_dir():
        return candidate.resolve()
    raise FileNotFoundError(f"Cannot find algo '{name}'")


def run_one_match(task):
    """Top-level for ProcessPool picklability. Same approach as bestof.py."""
    p1_dir, p2_dir, out_dir, pair_id, timeout_sec = task
    p1_dir, p2_dir, out_dir = Path(p1_dir), Path(p2_dir), Path(out_dir)
    worker_dir = Path(tempfile.mkdtemp(prefix="cit_tournament_"))
    start = time.time()
    try:
        (worker_dir / "game-configs.json").symlink_to(CONFIG_JSON)
        (worker_dir / "replays").mkdir()

        proc = subprocess.run(
            ["java", "-jar", str(ENGINE_JAR), "work",
             str(p1_dir / "run.sh"), str(p2_dir / "run.sh")],
            cwd=str(worker_dir),
            capture_output=True, text=True,
            timeout=timeout_sec,
        )
        duration = time.time() - start

        replays = list((worker_dir / "replays").glob("*.replay"))
        if not replays:
            return {"pair": pair_id, "error": "no replay"}
        src = replays[0]
        dest = out_dir / f"{p1_dir.name}_vs_{p2_dir.name}.replay"
        shutil.move(str(src), str(dest))

        with open(dest) as f:
            last = None
            for line in f:
                if line.strip():
                    last = line
        final = json.loads(last)
        p1_hp = float(final["p1Stats"][0])
        p2_hp = float(final["p2Stats"][0])
        if p1_hp > p2_hp:
            winner_side = 1
        elif p2_hp > p1_hp:
            winner_side = 2
        else:
            winner_side = 0

        return {
            "pair": pair_id,
            "p1": p1_dir.name,
            "p2": p2_dir.name,
            "winner_side": winner_side,
            "winner_name": p1_dir.name if winner_side == 1 else (p2_dir.name if winner_side == 2 else "tie"),
            "p1_hp": p1_hp,
            "p2_hp": p2_hp,
            "replay": str(dest),
            "duration": duration,
        }
    except subprocess.TimeoutExpired:
        return {"pair": pair_id, "error": f"timeout after {timeout_sec}s"}
    except Exception as e:
        return {"pair": pair_id, "error": str(e)}
    finally:
        shutil.rmtree(worker_dir, ignore_errors=True)


def parse_args(argv):
    positional = [a for a in argv[1:] if not a.startswith("--")]
    flags = [a for a in argv[1:] if a.startswith("--")]
    if len(positional) < 2:
        print(__doc__)
        sys.exit(1)
    algos = [resolve_algo(a) for a in positional]
    workers = None
    for f in flags:
        if f.startswith("--workers="):
            v = f.split("=", 1)[1]
            workers = None if v == "auto" else int(v)
    if workers is None:
        workers = min(os.cpu_count() or 4, len(algos) * (len(algos) - 1))
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

    with ProcessPoolExecutor(max_workers=workers) as pool:
        future_to_task = {pool.submit(run_one_match, t): t for t in tasks}
        for fut in as_completed(future_to_task):
            r = fut.result()
            if "error" in r:
                errors += 1
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
        }, f, indent=2)
    print(f"\n[tournament] Summary: {summary_path}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
