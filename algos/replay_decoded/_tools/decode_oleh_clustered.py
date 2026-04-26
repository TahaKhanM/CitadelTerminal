"""Re-decode oleh-v2 using replay clustering by opening pattern.

Two openings observed (split ~9/11 of 20):
 A) "deep-defense": Turrets at y=10/12 interior (e.g. (7,10), (11,10), (16,10), (3,12))
 B) "frontline": Walls at y=13 corners (e.g. (0,13), (1,13), (27,13), (26,13))

Pick the dominant cluster and decode using only those replays. This
should boost recall to ~95%+ by removing cross-cluster contamination.
"""

from __future__ import annotations

import json
import sys
from collections import Counter
from pathlib import Path

ROOT = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal")
sys.path.insert(0, str(ROOT / "algos/replay_decoded/_tools"))

from decode_opponent import _decode_replay, aggregate_decoded  # noqa: E402


def cluster_replays(replays: list[Path]) -> dict[tuple, list[Path]]:
    """Group replays by their turn-0 structure signature."""
    clusters: dict[tuple, list[Path]] = {}
    for p in replays:
        opp_outcome = "win" if "_loss_" in p.name else "loss"
        d = _decode_replay(p, opp_outcome)
        s0 = d.get(0, {}).get("structures", [])
        sig = tuple(sorted((n, x, y) for n, (x, y) in s0))
        clusters.setdefault(sig, []).append(p)
    return clusters


def main():
    inv = json.loads((ROOT / "algos/replay_decoded/_tools/inventory.json").read_text())
    target = sys.argv[1] if len(sys.argv) > 1 else "oleh-v2"
    info = inv[target]
    replays = [ROOT / r for r in info["replays"]]
    clusters = cluster_replays(replays)
    print(f"{target}: {len(clusters)} clusters")
    for sig, paths in sorted(clusters.items(), key=lambda kv: -len(kv[1])):
        print(f"  cluster size={len(paths)}: opening_sig={list(sig)[:3]}...")

    # Pick the dominant cluster
    dominant = max(clusters.values(), key=len)
    print(f"\nDecoding from dominant cluster ({len(dominant)} replays)")
    decodes = []
    for p in dominant:
        opp_outcome = "win" if "_loss_" in p.name else "loss"
        d = _decode_replay(p, opp_outcome)
        decodes.append(d)
    final = aggregate_decoded(decodes, threshold_frac=0.5)  # higher conf in single cluster
    out_path = ROOT / f"algos/replay_decoded/{target}/decoded_turns.json"
    with open(out_path, "w") as f:
        json.dump(final, f, indent=2)
    print(f"Wrote {out_path}")


if __name__ == "__main__":
    main()
