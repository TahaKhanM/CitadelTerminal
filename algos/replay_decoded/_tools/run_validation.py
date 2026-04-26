"""Run validation matches for each decoded algo and write a JSONL log.

Each match: invoke run_match.py, find the latest replay, parse endStats,
emit one JSON record per match.
"""

from __future__ import annotations

import glob
import json
import os
import subprocess
import sys
import time
from pathlib import Path

ROOT = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal")
ALGOS = ROOT / "algos"
REPLAY_DIR = ROOT / "C1GamesStarterKit-master/replays"
LOG = ROOT / "algos/replay_decoded/validation_log.jsonl"


def latest_replay_after(t0: float) -> str | None:
    """Return the most recent .replay whose mtime >= t0."""
    paths = []
    for p in glob.glob(str(REPLAY_DIR / "*.replay")):
        if os.path.getmtime(p) >= t0:
            paths.append(p)
    if not paths:
        # Fallback: latest overall
        all_paths = glob.glob(str(REPLAY_DIR / "*.replay"))
        if not all_paths:
            return None
        return max(all_paths, key=os.path.getmtime)
    return max(paths, key=os.path.getmtime)


def parse_endstats(replay_path: str) -> dict:
    with open(replay_path) as f:
        lines = [l.strip() for l in f if l.strip().startswith("{")]
    if not lines:
        return {}
    last = json.loads(lines[-1])
    es = last.get("endStats", {})
    return {
        "winner": es.get("winner"),
        "p1_hp": (last.get("p1Stats") or [None])[0],
        "p2_hp": (last.get("p2Stats") or [None])[0],
        "turns": es.get("turns"),
        "p1_crashed": (es.get("player1") or {}).get("crashed"),
        "p2_crashed": (es.get("player2") or {}).get("crashed"),
        "p1_timeout": (es.get("player1") or {}).get("timeout_death"),
        "p2_timeout": (es.get("player2") or {}).get("timeout_death"),
    }


def run_match(algo1: str, algo2: str, label: str) -> dict:
    t0 = time.time()
    p1 = ALGOS / algo1
    p2 = ALGOS / algo2
    cmd = ["python3", "C1GamesStarterKit-master/scripts/run_match.py", str(p1), str(p2)]
    try:
        proc = subprocess.run(cmd, cwd=str(ROOT), capture_output=True, text=True, timeout=300)
        ok = proc.returncode == 0
        out = proc.stdout
        err = proc.stderr
    except subprocess.TimeoutExpired:
        ok = False
        out, err = "", ""
    rec = {"label": label, "algo1": algo1, "algo2": algo2, "ok": ok}

    # Parse winner from stdout: "Winner (p1 perspective, 1 = p1 2 = p2): N"
    import re
    m = re.search(r"Winner.*?:\s*([12])", out)
    if m:
        rec["winner"] = int(m.group(1))
    # Parse the saved replay path from stdout: "Saving replay: <path>"
    m2 = re.search(r"Saving replay:\s*(\S+)", out)
    if m2:
        replay_path = m2.group(1)
        if os.path.exists(replay_path):
            es = parse_endstats(replay_path)
            rec.update({k: v for k, v in es.items() if k not in rec or rec[k] is None})
            rec["replay"] = os.path.basename(replay_path)
    print(f"  {label}: winner={rec.get('winner')} hp={rec.get('p1_hp')}-{rec.get('p2_hp')} turns={rec.get('turns')}", flush=True)
    return rec


def main():
    cands = ["funnel-rush-v9", "funnel-rush-v8", "funnel-crush",
             "switch2", "python-algo-classic", "oleh-v2"]
    LOG.unlink(missing_ok=True)
    log = open(LOG, "w")

    print("=== Mirror matches ===", flush=True)
    for c in cands:
        rec = run_match(f"replay_decoded/{c}", f"replay_decoded/{c}", f"mirror_{c}")
        log.write(json.dumps(rec) + "\n")
        log.flush()

    print("\n=== vs v13_second_ring (2 each) ===", flush=True)
    for c in cands:
        for i in (1, 2):
            rec = run_match(f"replay_decoded/{c}", "v13_second_ring", f"vs_v13_{c}_{i}")
            log.write(json.dumps(rec) + "\n")
            log.flush()

    print("\n=== Cross-matches ===", flush=True)
    cross = [
        ("funnel-rush-v9", "oleh-v2"),
        ("funnel-crush", "python-algo-classic"),
        ("switch2", "funnel-rush-v8"),
        ("funnel-rush-v9", "funnel-crush"),
        ("python-algo-classic", "oleh-v2"),
    ]
    for a, b in cross:
        rec = run_match(f"replay_decoded/{a}", f"replay_decoded/{b}", f"cross_{a}_vs_{b}")
        log.write(json.dumps(rec) + "\n")
        log.flush()

    log.close()
    print(f"\nWrote {LOG}", flush=True)


if __name__ == "__main__":
    main()
