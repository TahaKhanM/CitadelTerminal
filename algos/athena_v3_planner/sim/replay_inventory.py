"""Inventory the ranked replay corpus.

Walks `data/replays/` and `replays/` looking for ranked-match downloads
(filename pattern `v13_*`), extracts a small metadata record per replay,
and writes a JSON index to `algos/athena_v3_planner/data/replay_index.json`.

Per-record fields:
    file:               relative path from repo root
    match_id:           integer parsed from filename `m\\d+`
    opponent_name:      the `vs_<opponent>` slug from the filename
    opponent_elo:       integer parsed from filename, 0 for boss matches
    outcome:            'win' | 'loss' | 'unknown' (from filename suffix)
    is_boss:            True when filename contains `_boss`
    turns:              from `endStats.turns` (last frame)
    frames:             from `endStats.frames`
    duration_ms:        from `endStats.duration`
    winner:             1 | 2 from `endStats.winner` (1 = self/p1)
    p1_final_hp:        last `p1Stats[0]`
    p2_final_hp:        last `p2Stats[0]`
    parsed_ok:          True when end-stats were found

The script is intentionally dependency-light: stdlib only, no sim_rs,
no parser sharing — it just enumerates and parses end-states. For full
Python-vs-Rust parity verification of the corpus, use the existing
`algos/athena/sim/cross_validate.py` (this script is the lighter
metadata-only pass).

Usage:
    python3 algos/athena_v3_planner/sim/replay_inventory.py
"""
from __future__ import annotations

import json
import re
from pathlib import Path
from typing import Iterator


# repo_root = .../CitadelTerminal/.claude/worktrees/agent-a17ebb78  (this worktree)
REPO_ROOT = Path(__file__).resolve().parents[3]
SEARCH_ROOTS = [REPO_ROOT / "replays", REPO_ROOT / "data" / "replays"]
OUTPUT = REPO_ROOT / "algos" / "athena_v3_planner" / "data" / "replay_index.json"

# Filename pattern (one canonical form observed in replays/ranked):
#   v13_<algoid>_m<matchid>_vs_<opponent>_<elo>_<outcome>[_boss].replay
NAME_RE = re.compile(
    r"^v13_(?P<algoid>\d+)_m(?P<matchid>\d+)_vs_(?P<opponent>.+?)_(?P<elo>\d+)_(?P<outcome>win|loss)(?P<boss>_boss)?\.replay$"
)


def _enumerate_replays() -> Iterator[Path]:
    """Yield all v13_*.replay files under known replay roots."""
    seen: set[Path] = set()
    for root in SEARCH_ROOTS:
        if not root.exists():
            continue
        for p in root.rglob("v13_*.replay"):
            if p not in seen:
                seen.add(p)
                yield p


def _parse_filename(name: str) -> dict:
    m = NAME_RE.match(name)
    if not m:
        return {
            "match_id": None,
            "opponent_name": None,
            "opponent_elo": None,
            "outcome": "unknown",
            "is_boss": False,
        }
    return {
        "match_id": int(m.group("matchid")),
        "opponent_name": m.group("opponent"),
        "opponent_elo": int(m.group("elo")),
        "outcome": m.group("outcome"),
        "is_boss": bool(m.group("boss")),
    }


def _parse_replay(path: Path) -> dict:
    """Stream the replay file, return end-state metadata."""
    end_stats = None
    last_frame = None
    n_frames = 0
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s.startswith("{"):
                # leading blank line + any non-JSON trailing summary line
                continue
            try:
                d = json.loads(s)
            except json.JSONDecodeError:
                continue
            n_frames += 1
            if "endStats" in d:
                end_stats = d["endStats"]
                last_frame = d
    if end_stats is None:
        return {"parsed_ok": False, "n_lines": n_frames}
    p1_hp = None
    p2_hp = None
    if last_frame is not None:
        p1s = last_frame.get("p1Stats") or []
        p2s = last_frame.get("p2Stats") or []
        if p1s:
            p1_hp = p1s[0]
        if p2s:
            p2_hp = p2s[0]
    return {
        "parsed_ok": True,
        "n_lines": n_frames,
        "turns": end_stats.get("turns"),
        "frames": end_stats.get("frames"),
        "duration_ms": end_stats.get("duration"),
        "winner": end_stats.get("winner"),
        "p1_final_hp": p1_hp,
        "p2_final_hp": p2_hp,
        "p1_crashed": (end_stats.get("player1") or {}).get("crashed"),
        "p2_crashed": (end_stats.get("player2") or {}).get("crashed"),
    }


def build_index() -> list[dict]:
    records: list[dict] = []
    for path in sorted(_enumerate_replays()):
        rel = path.relative_to(REPO_ROOT)
        rec: dict = {"file": str(rel)}
        rec.update(_parse_filename(path.name))
        rec.update(_parse_replay(path))
        records.append(rec)
    return records


def main() -> int:
    records = build_index()
    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT.write_text(json.dumps(records, indent=2) + "\n")
    # Print a summary
    n = len(records)
    n_ok = sum(1 for r in records if r.get("parsed_ok"))
    n_boss = sum(1 for r in records if r.get("is_boss"))
    n_win = sum(1 for r in records if r.get("outcome") == "win")
    n_loss = sum(1 for r in records if r.get("outcome") == "loss")
    opps = sorted({r["opponent_name"] for r in records if r.get("opponent_name")})
    print(f"replay_inventory: {n} files, {n_ok} parsed OK, {n_boss} boss")
    print(f"  outcomes: {n_win} win, {n_loss} loss, {n - n_win - n_loss} other")
    print(f"  distinct opponents ({len(opps)}): {', '.join(opps)}")
    print(f"  index written to: {OUTPUT.relative_to(REPO_ROOT)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
