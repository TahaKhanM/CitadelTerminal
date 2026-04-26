"""Inventory all opponents in our ranked replay corpus.

Walk every .replay file under Ranked Replays/, parse the header line
(JSON before frames begin) to extract our algo and the opponent algo,
plus their names + ratings + ids. Aggregate by opponent algo_id.

Outputs a JSON map {opponent_algo_id: {name, ratings:[...], replays:[...]}}
"""

from __future__ import annotations

import json
import os
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal")
REPLAYS = ROOT / "Ranked Replays"


def _parse_replay_header(path: Path) -> dict | None:
    """Parse just enough of the replay to find the metadata header.

    The header is the first JSON line and contains 'unitInformation' (the
    config) plus 'gameMetadata' or similar with player names/ratings.
    """
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s.startswith("{"):
                continue
            try:
                d = json.loads(s)
            except Exception:
                continue
            # Header: has 'unitInformation' (config)
            if "unitInformation" in d:
                return d
            return None
    return None


def _extract_filename_meta(filename: str) -> dict:
    """Filenames look like: <our_algo>_<win|loss>_<opponent>_<rating>_<match_id>.replay
    But opponent names can contain underscores.

    We split from the right: match_id, rating, then the rest is opponent.
    The leftmost token before 'win'/'loss' is OUR algo.
    """
    base = filename.replace(".replay", "")
    # Find win/loss marker
    m = re.search(r"_(win|loss)_", base)
    if not m:
        return {}
    ours = base[: m.start()]
    outcome = m.group(1)
    rest = base[m.end() :]
    # Last underscored token is match_id, second-to-last is rating
    parts = rest.rsplit("_", 2)
    if len(parts) < 3:
        return {"our_algo": ours, "outcome": outcome, "opponent_name_raw": rest}
    opponent = parts[0]
    try:
        rating = int(parts[1])
    except ValueError:
        rating = None
    match_id = parts[2]
    return {
        "our_algo": ours,
        "outcome": outcome,
        "opponent_name_raw": opponent,
        "opponent_rating": rating,
        "match_id": match_id,
    }


def main() -> None:
    by_opponent_name: dict[str, dict] = defaultdict(
        lambda: {"replays": [], "ratings": [], "outcomes": [], "our_algos": []}
    )
    total = 0
    parsed_header = 0
    no_header = 0

    for replay_path in sorted(REPLAYS.rglob("*.replay")):
        total += 1
        meta = _extract_filename_meta(replay_path.name)
        opp_name = meta.get("opponent_name_raw", "<unknown>")
        rec = by_opponent_name[opp_name]
        rec["replays"].append(str(replay_path.relative_to(ROOT)))
        rec["ratings"].append(meta.get("opponent_rating"))
        rec["outcomes"].append(meta.get("outcome"))
        rec["our_algos"].append(meta.get("our_algo"))

        # Try to grab algo_id from header (replay metadata)
        if "algo_id" not in rec:
            header = _parse_replay_header(replay_path)
            if header:
                parsed_header += 1
                # Look for player1/player2 metadata
                # Typical schema: header has 'player1', 'player2', or similar
                meta_keys = [k for k in header.keys() if k not in ("unitInformation",)]
                if meta_keys and "algo_id" not in rec:
                    rec["header_keys"] = meta_keys
            else:
                no_header += 1

    # Aggregate
    print(f"Total replays: {total}", file=sys.stderr)
    print(f"Parsed header: {parsed_header} / {parsed_header + no_header}", file=sys.stderr)
    print(f"Unique opponent names: {len(by_opponent_name)}", file=sys.stderr)

    # Order by # replays desc
    out = {}
    for name, rec in sorted(by_opponent_name.items(), key=lambda kv: -len(kv[1]["replays"])):
        ratings = [r for r in rec["ratings"] if r is not None]
        out[name] = {
            "n_replays": len(rec["replays"]),
            "avg_rating": (sum(ratings) / len(ratings)) if ratings else None,
            "max_rating": max(ratings) if ratings else None,
            "min_rating": min(ratings) if ratings else None,
            "wins_against_us": sum(1 for o in rec["outcomes"] if o == "loss"),  # we LOST = they WON
            "losses_against_us": sum(1 for o in rec["outcomes"] if o == "win"),  # we WON = they LOST
            "our_algos_played": sorted(set(rec["our_algos"])),
            "replays": rec["replays"],
        }

    # Write JSON
    out_path = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/replay_decoded/_tools/inventory.json")
    out_path.parent.mkdir(parents=True, exist_ok=True)
    with open(out_path, "w") as f:
        json.dump(out, f, indent=2)

    # Print top opponents
    print("\nTop opponents (by replay count):", file=sys.stderr)
    print(f"{'name':<40} {'n':>4} {'avg':>6} {'max':>6} {'wins':>5} {'losses':>7}", file=sys.stderr)
    for name, info in list(out.items())[:25]:
        avg = f"{info['avg_rating']:.0f}" if info["avg_rating"] else "?"
        mx = f"{info['max_rating']:.0f}" if info["max_rating"] else "?"
        print(
            f"{name:<40} {info['n_replays']:>4} {avg:>6} {mx:>6} "
            f"{info['wins_against_us']:>5} {info['losses_against_us']:>7}",
            file=sys.stderr,
        )


if __name__ == "__main__":
    main()
