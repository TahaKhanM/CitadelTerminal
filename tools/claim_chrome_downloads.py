#!/usr/bin/env python3
"""
claim_chrome_downloads.py — rename Chrome's raw temp download files
(`.com.google.Chrome.XXX`) into the proper replay filenames.

Usage:
    python3 tools/claim_chrome_downloads.py manifest.json

`manifest.json` is a list of records written BEFORE the Chrome downloads fire,
in the exact order the downloads will be triggered. Each record:
    {"match_id": 15303183, "target": "v13_360023_m15303183_vs_diego_v2_1929_loss.replay",
     "expected_bytes": 9125603}

We then scan ~/Downloads for `.com.google.Chrome.*` files newer than the
manifest's mtime, sort by mtime ascending, and rename them in order into
replays/ranked/.

Run this script AFTER the download loop in the Chrome tab reports done.
"""

import json
import os
import shutil
import sys
import time
from pathlib import Path

HOME = Path.home()
DOWNLOADS = HOME / "Downloads"
REPO = Path(__file__).resolve().parent.parent
OUT_DIR = REPO / "replays" / "ranked"
OUT_DIR.mkdir(parents=True, exist_ok=True)


def looks_like_replay(path: Path) -> bool:
    try:
        with open(path, "rb") as f:
            head = f.read(200).decode("utf-8", errors="replace").lstrip()
        return head.startswith("{") and ("unitInformation" in head or "debug" in head)
    except Exception:
        return False


def wait_for_new_files(manifest_mtime: float, expected_count: int, timeout: float = 120.0) -> list:
    """Return the list of Chrome temp files created after manifest_mtime, sorted ascending by mtime."""
    start = time.time()
    while time.time() - start < timeout:
        candidates = [
            p for p in DOWNLOADS.glob(".com.google.Chrome.*")
            if p.is_file() and p.stat().st_mtime > manifest_mtime - 1
        ]
        # Also include recently-created .replay files (Chrome sometimes names them)
        candidates += [
            p for p in DOWNLOADS.glob("*.replay")
            if p.is_file() and p.stat().st_mtime > manifest_mtime - 1
        ]
        candidates = list({p: None for p in candidates}.keys())
        if len(candidates) >= expected_count:
            candidates.sort(key=lambda p: p.stat().st_mtime)
            return candidates[-expected_count:]
        time.sleep(1.0)
    # timeout: return whatever we have
    candidates = [p for p in DOWNLOADS.glob(".com.google.Chrome.*") if p.is_file() and p.stat().st_mtime > manifest_mtime - 1]
    candidates += [p for p in DOWNLOADS.glob("*.replay") if p.is_file() and p.stat().st_mtime > manifest_mtime - 1]
    candidates = list({p: None for p in candidates}.keys())
    candidates.sort(key=lambda p: p.stat().st_mtime)
    return candidates


def main():
    if len(sys.argv) < 2:
        sys.exit("usage: claim_chrome_downloads.py manifest.json")
    manifest_path = Path(sys.argv[1])
    manifest = json.loads(manifest_path.read_text())
    expected = len(manifest)
    manifest_mtime = manifest_path.stat().st_mtime
    print(f"Waiting for {expected} Chrome temp downloads newer than manifest…")
    temps = wait_for_new_files(manifest_mtime, expected, timeout=180.0)
    print(f"Found {len(temps)} candidate temp file(s).")
    if len(temps) < expected:
        print("WARNING: fewer temps than expected; renaming what we have.")

    moved = 0
    for rec, temp in zip(manifest, temps):
        tgt = rec["target"]
        expected_bytes = rec.get("expected_bytes")
        actual_bytes = temp.stat().st_size
        if expected_bytes is not None and expected_bytes != actual_bytes:
            print(f"  SIZE MISMATCH match_id={rec.get('match_id')}: expected {expected_bytes}, got {actual_bytes} ({temp.name})")
            # Don't fail — could be server flux; still rename
        if not looks_like_replay(temp):
            print(f"  NOT A REPLAY: {temp.name}  — skipping rec {rec.get('match_id')}")
            continue
        dest = OUT_DIR / tgt
        shutil.move(str(temp), str(dest))
        print(f"  → {dest.name}")
        moved += 1
    print(f"Moved {moved}/{len(manifest)} replays to {OUT_DIR}")


if __name__ == "__main__":
    main()
