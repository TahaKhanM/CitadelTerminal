#!/usr/bin/env python3
"""bulk_download_my_replays.py — pull every ranked replay from every algo
on the user's C1 account, organized by algo name.

Endpoints used (verified 2026-04-25):
  GET /api/game/algo/mine/{comp_id}            — list user's algos
  GET /api/game/algo/{algo_id}/matches         — list match metadata
  GET /api/game/replayexpanded/{match_id}      — download replay JSON

Auth: cookies extracted from Chrome via browser_cookie3.

Output:
  Ranked Replays/<algo_name>/<algo_name>_vs_<opp>_<elo>_<id>.replay
"""
from __future__ import annotations

import argparse
import json
import os
import re
import sys
import time
from pathlib import Path

import requests

CITADEL_COMP_ID = 1338
BASE = "https://terminal.c1games.com"
COOKIE_FILE = Path.home() / ".c1_session.json"
OUT_ROOT = Path(__file__).resolve().parent.parent / "Ranked Replays"

UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_0) AppleWebKit/605.1.15"


def _safe_filename(s: str) -> str:
    s = re.sub(r"[^A-Za-z0-9._-]+", "_", s).strip("_")
    return s[:80] or "unknown"


def load_cookies() -> dict:
    if not COOKIE_FILE.exists():
        sys.exit(f"missing {COOKIE_FILE} — extract via browser_cookie3 first")
    with open(COOKIE_FILE) as f:
        return json.load(f)


def get_my_algos(session: requests.Session) -> list[dict]:
    r = session.get(f"{BASE}/api/game/algo/mine/{CITADEL_COMP_ID}", timeout=20)
    r.raise_for_status()
    return r.json()["data"]["algos"]


def list_matches(session: requests.Session, algo_id: int) -> list[dict]:
    r = session.get(f"{BASE}/api/game/algo/{algo_id}/matches", timeout=30)
    r.raise_for_status()
    body = r.json()
    if isinstance(body, dict) and "data" in body:
        body = body["data"]
    if isinstance(body, dict) and "matches" in body:
        body = body["matches"]
    return body


def download_replay(session: requests.Session, match_id: int) -> bytes | None:
    r = session.get(f"{BASE}/api/game/replayexpanded/{match_id}", timeout=60)
    if r.status_code != 200:
        return None
    if r.headers.get("content-length") and int(r.headers["content-length"]) < 1000:
        return None  # error stub
    if not r.text or not r.text.lstrip().startswith(("{", "[")):
        return None
    return r.content


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default=str(OUT_ROOT), help="output dir")
    ap.add_argument("--skip-existing", action="store_true", default=True,
                    help="don't re-download replays already on disk")
    ap.add_argument("--algos", nargs="*", type=int,
                    help="only download for these specific algo IDs")
    ap.add_argument("--limit-per-algo", type=int, default=None,
                    help="cap downloads per algo")
    ap.add_argument("--delay", type=float, default=0.3,
                    help="seconds between requests (server-friendly)")
    args = ap.parse_args()

    out_root = Path(args.out)
    out_root.mkdir(parents=True, exist_ok=True)

    cookies = load_cookies()
    session = requests.Session()
    session.cookies.update(cookies)
    session.headers.update({
        "User-Agent": UA,
        "X-CSRFToken": cookies.get("csrftoken", ""),
        "Referer": f"{BASE}/myAlgos",
        "Accept": "application/json, text/plain, */*",
    })

    algos = get_my_algos(session)
    if args.algos:
        algos = [a for a in algos if a["id"] in args.algos]
    print(f"Account has {len(algos)} algos to process:")
    for a in algos:
        print(f"  - {a['id']:<8} {a['name']:<35} rating={a['rating']}")

    grand_total_downloaded = 0
    grand_total_skipped = 0
    grand_total_failed = 0

    for algo in algos:
        algo_id = algo["id"]
        algo_name = _safe_filename(algo["name"])
        algo_dir = out_root / algo_name
        algo_dir.mkdir(parents=True, exist_ok=True)

        print(f"\n=== {algo['name']} (id={algo_id}, rating={algo['rating']}) ===")
        try:
            matches = list_matches(session, algo_id)
        except Exception as exc:  # noqa: BLE001
            print(f"  ! list_matches failed: {exc!r}")
            continue

        usable = [m for m in matches if m.get("replay") and not m.get("crashed")]
        if args.limit_per_algo:
            usable = usable[: args.limit_per_algo]
        print(f"  {len(usable)} ranked matches with replay (of {len(matches)} total)")

        n_dl = n_skip = n_fail = 0
        for m in usable:
            mid = m["id"]
            won = (m.get("winning_algo") or {}).get("id") == algo_id
            opp_algo = (m.get("losing_algo") if won else m.get("winning_algo")) or {}
            opp_name = _safe_filename(opp_algo.get("name", "unknown"))
            opp_rating = opp_algo.get("rating", "?")
            outcome = "win" if won else "loss"
            fname = f"{algo_name}_{outcome}_{opp_name}_{opp_rating}_{mid}.replay"
            fpath = algo_dir / fname

            if args.skip_existing and fpath.exists():
                n_skip += 1
                continue

            time.sleep(args.delay)
            data = download_replay(session, mid)
            if data is None:
                n_fail += 1
                print(f"    ✗ {mid} ({opp_name}) — download failed")
                continue
            fpath.write_bytes(data)
            n_dl += 1
            if n_dl <= 5 or n_dl % 25 == 0:
                print(f"    ✓ {mid} ({outcome} vs {opp_name}@{opp_rating}, {len(data)//1024}KB)")

        print(f"  → {n_dl} downloaded, {n_skip} skipped, {n_fail} failed")
        grand_total_downloaded += n_dl
        grand_total_skipped += n_skip
        grand_total_failed += n_fail

    print(f"\nTOTAL: downloaded={grand_total_downloaded} "
          f"skipped={grand_total_skipped} failed={grand_total_failed}")


if __name__ == "__main__":
    main()
