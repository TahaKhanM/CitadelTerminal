#!/usr/bin/env python3
"""
Run the same matchup N times, in parallel, and report the win rate with a
Wilson 95% confidence interval.

Runs `n` matches with a as P1 and b as P2, and another `n` with sides flipped,
for 2n games total. Each match runs in its own tempdir (so replay files don't
collide), and the matches are distributed across CPU cores via a process pool.

Usage:
    python3 tools/bestof.py <algo_a> <algo_b> [n=5] [--workers=auto] [--serial]

Examples:
    python3 tools/bestof.py algos/v3 algos/baseline 10
    python3 tools/bestof.py algos/v3 algos/baseline 10 --workers=4
    python3 tools/bestof.py algos/v3 algos/baseline 5 --serial   # old 1-at-a-time mode

Both-sides requirement: engine doesn't randomize spawn side, so we alternate
to measure true performance independent of side advantage.
"""
import json
import math
import os
import shutil
import subprocess
import sys
import tempfile
import time
from concurrent.futures import ProcessPoolExecutor, as_completed
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parent.parent
STARTER_KIT = REPO_ROOT / "C1GamesStarterKit-master"
ENGINE_JAR = STARTER_KIT / "engine.jar"
CONFIG_JSON = STARTER_KIT / "game-configs.json"

subprocess.run([str(REPO_ROOT / "tools" / "apply_competition_config.sh")], check=True)


def resolve_algo(name: str) -> Path:
    p = Path(name)
    if p.is_dir():
        return p.resolve()
    candidate = REPO_ROOT / "algos" / name
    if candidate.is_dir():
        return candidate.resolve()
    raise FileNotFoundError(f"Cannot find algo '{name}'")


def _find_runsh(algo_dir: Path) -> str:
    """Return the run.sh path the engine should invoke (absolute)."""
    rs = algo_dir / "run.sh"
    if not rs.exists():
        raise FileNotFoundError(f"{algo_dir} has no run.sh")
    return str(rs)


def run_one_match(task):
    """Run a single match in an isolated tempdir. Top-level so ProcessPool can pickle it."""
    p1_dir, p2_dir, out_dir, game_id, timeout_sec = task

    p1_dir, p2_dir, out_dir = Path(p1_dir), Path(p2_dir), Path(out_dir)
    worker_dir = Path(tempfile.mkdtemp(prefix="cit_match_"))
    start = time.time()

    try:
        # The engine reads `game-configs.json` from cwd and writes `replays/*.replay` there.
        # By giving each match its own cwd, parallel matches don't clash on replay filenames.
        (worker_dir / "game-configs.json").symlink_to(CONFIG_JSON)
        (worker_dir / "replays").mkdir()

        p1_run = _find_runsh(p1_dir)
        p2_run = _find_runsh(p2_dir)

        proc = subprocess.run(
            ["java", "-jar", str(ENGINE_JAR), "work", p1_run, p2_run],
            cwd=str(worker_dir),
            capture_output=True,
            text=True,
            timeout=timeout_sec,
        )
        duration = time.time() - start

        replays = list((worker_dir / "replays").glob("*.replay"))
        if not replays:
            return {
                "game_id": game_id,
                "error": "no replay produced",
                "stdout_tail": proc.stdout.splitlines()[-5:] if proc.stdout else [],
                "stderr_tail": proc.stderr.splitlines()[-5:] if proc.stderr else [],
                "duration": duration,
            }
        src = replays[0]

        dest = out_dir / f"game_{game_id:02d}_{p1_dir.name}_vs_{p2_dir.name}.replay"
        shutil.move(str(src), str(dest))

        # Parse winner from the last frame
        with open(dest) as f:
            last = None
            for line in f:
                if line.strip():
                    last = line
        final = json.loads(last)
        p1_hp = float(final["p1Stats"][0])
        p2_hp = float(final["p2Stats"][0])
        if p1_hp > p2_hp:
            winner = "p1"
        elif p2_hp > p1_hp:
            winner = "p2"
        else:
            winner = "tie"

        return {
            "game_id": game_id,
            "p1": p1_dir.name,
            "p2": p2_dir.name,
            "winner": winner,
            "p1_hp": p1_hp,
            "p2_hp": p2_hp,
            "replay": str(dest),
            "duration": duration,
        }
    except subprocess.TimeoutExpired:
        return {"game_id": game_id, "error": f"timeout after {timeout_sec}s", "duration": time.time() - start}
    except Exception as e:
        return {"game_id": game_id, "error": str(e), "duration": time.time() - start}
    finally:
        shutil.rmtree(worker_dir, ignore_errors=True)


def wilson_interval(wins: int, total: int, z: float = 1.96):
    if total == 0:
        return 0.0, 1.0
    p = wins / total
    denom = 1 + z**2 / total
    center = (p + z**2 / (2 * total)) / denom
    half = (z * math.sqrt(p * (1 - p) / total + z**2 / (4 * total**2))) / denom
    return max(0.0, center - half), min(1.0, center + half)


def parse_args(argv):
    positional = [a for a in argv[1:] if not a.startswith("--")]
    flags = [a for a in argv[1:] if a.startswith("--")]
    if len(positional) < 2:
        print(__doc__)
        sys.exit(1)
    a = resolve_algo(positional[0])
    b = resolve_algo(positional[1])
    n = int(positional[2]) if len(positional) > 2 else 5

    workers = None
    serial = False
    for f in flags:
        if f.startswith("--workers="):
            v = f.split("=", 1)[1]
            workers = None if v == "auto" else int(v)
        elif f == "--serial":
            serial = True
    if serial:
        workers = 1
    if workers is None:
        # Matches are CPU-bound (Java engine + Python algos with JIT'd Rust
        # sims). Capping at cores/2 leaves each match ~2 cores, which keeps
        # per-match wall time near the serial baseline (~160s).
        workers = min(max((os.cpu_count() or 4) // 2, 1), 2 * n)
    return a, b, n, workers


def main(argv):
    a_path, b_path, n, workers = parse_args(argv)
    ts = time.strftime("%Y%m%d_%H%M%S")
    out_dir = REPO_ROOT / "replays" / f"bestof_{a_path.name}_vs_{b_path.name}_{ts}"
    out_dir.mkdir(parents=True, exist_ok=True)

    print(f"[bestof] {a_path.name}  vs  {b_path.name}")
    print(f"[bestof] Games per side: {n}  (total: {2*n})  workers: {workers}")
    print(f"[bestof] Output: {out_dir}\n")

    # Build task list: first n games with a as P1, then n with b as P1 (so a becomes P2).
    tasks = []
    for i in range(n):
        tasks.append((str(a_path), str(b_path), str(out_dir), i + 1, 480))
    for i in range(n):
        tasks.append((str(b_path), str(a_path), str(out_dir), n + i + 1, 480))

    wall_start = time.time()
    results = [None] * len(tasks)
    a_wins = b_wins = ties = errors = 0
    a_wins_as_p1 = b_wins_as_p1 = 0   # for side-asymmetry detection

    with ProcessPoolExecutor(max_workers=workers) as pool:
        future_to_id = {pool.submit(run_one_match, t): t[3] for t in tasks}
        for fut in as_completed(future_to_id):
            gid = future_to_id[fut]
            r = fut.result()
            results[gid - 1] = r
            if "error" in r:
                errors += 1
                print(f"  [game {gid:2}] ERROR: {r['error']}")
                continue
            # Determine which original algo won and record side info.
            a_was_p1 = (gid <= n)
            if r["winner"] == "p1":
                if a_was_p1:
                    a_wins += 1
                    a_wins_as_p1 += 1
                    tag = f"{a_path.name} wins"
                else:
                    b_wins += 1
                    b_wins_as_p1 += 1
                    tag = f"{b_path.name} wins"
            elif r["winner"] == "p2":
                if a_was_p1:
                    b_wins += 1
                    tag = f"{b_path.name} wins"
                else:
                    a_wins += 1
                    tag = f"{a_path.name} wins"
            else:
                ties += 1
                tag = "tie"
            side_marker = "[P1=a]" if a_was_p1 else "[P1=b]"
            print(f"  game {gid:2} {side_marker}: {tag:30}  "
                  f"HP {r['p1_hp']:.0f}-{r['p2_hp']:.0f}  ({r['duration']:.1f}s)")

    wall_elapsed = time.time() - wall_start
    total = 2 * n
    a_rate = a_wins / total if total else 0
    b_rate = b_wins / total if total else 0
    lo_a, hi_a = wilson_interval(a_wins, total)

    print("\n" + "=" * 60)
    print(f"RESULTS — {total} games ({n} each side, wall {wall_elapsed:.1f}s)")
    print("=" * 60)
    print(f"  {a_path.name:<30} {a_wins:>2}  ({a_rate:>5.1%})  "
          f"CI_95=[{lo_a:.2f}, {hi_a:.2f}]")
    print(f"  {b_path.name:<30} {b_wins:>2}  ({b_rate:>5.1%})")
    if ties:
        print(f"  ties:                          {ties}")
    if errors:
        print(f"  errors:                        {errors}")
    # Side-split for mirror-match / asymmetry detection
    if a_path == b_path:
        print(f"\n  mirror-match side split: P1-side wins {a_wins_as_p1}/{n}, "
              f"P2-side wins {n - a_wins_as_p1 - (sum(1 for i,r in enumerate(results[:n]) if r and r.get('winner') == 'tie'))}/{n}")
    print()

    if lo_a > 0.55:
        print(f"[verdict] {a_path.name} is meaningfully stronger (lower CI > 55%).")
    elif hi_a < 0.45:
        print(f"[verdict] {b_path.name} is meaningfully stronger (upper CI < 45%).")
    else:
        print("[verdict] Inconclusive — results overlap 50%. Run more games.")

    # Machine-readable summary line for eval.sh and other parsers
    print(f"SUMMARY_JSON: "
          f'{{"a":"{a_path.name}","b":"{b_path.name}","n":{n},"total":{total},'
          f'"a_wins":{a_wins},"b_wins":{b_wins},"ties":{ties},"errors":{errors},'
          f'"a_rate":{a_rate:.4f},"ci_low":{lo_a:.4f},"ci_high":{hi_a:.4f},'
          f'"wall_seconds":{wall_elapsed:.2f},"workers":{workers}}}')
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
