"""Validate decoded algo accuracy by comparing scheduled vs original deploys.

For each candidate, take ONE source replay it was decoded from, then
compare the per-turn structures/mobiles/upgrades emitted by the decoded
schedule against the actual replay. Compute fidelity %.
"""

from __future__ import annotations

import json
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path("/Users/tahakhan/Documents/Work/Projects/CitadelTerminal")
sys.path.insert(0, str(ROOT / "algos/replay_decoded/_tools"))

from decode_opponent import _decode_replay  # noqa: E402


def fidelity(decoded: dict, original: dict) -> dict:
    """Per-turn / overall: what fraction of the original's actions does
    the decode reproduce? And what fraction of decode's actions are NOT
    in original (false positives)?"""
    turns = sorted(set(int(t) for t in decoded.keys()) | set(original.keys()))
    matched_struct = 0
    total_orig_struct = 0
    matched_mob = 0
    total_orig_mob = 0
    matched_upg = 0
    total_orig_upg = 0
    for t in turns:
        d = decoded.get(str(t)) or decoded.get(t) or {}
        o = original.get(t) or {}
        d_structs = {(n, x, y) for n, (x, y) in d.get("structures", [])}
        o_structs = {(n, x, y) for n, (x, y) in o.get("structures", [])}
        d_mobs = {(n, x, y, n_count) for n, (x, y), n_count in d.get("mobiles", [])}
        o_mobs = {(n, x, y, n_count) for n, (x, y), n_count in o.get("mobiles", [])}
        d_upgs = set(tuple(u) for u in d.get("upgrades", []))
        # original upgrades may use new format [x,y]
        o_upgs = set()
        for u in o.get("upgrades", []):
            if isinstance(u, list) and len(u) == 2:
                if isinstance(u[0], int):
                    o_upgs.add((u[0], u[1]))
                elif isinstance(u[1], list):
                    o_upgs.add((u[1][0], u[1][1]))

        matched_struct += len(d_structs & o_structs)
        total_orig_struct += len(o_structs)
        # For mobiles: require unit type + position match (count comparison loose)
        d_mob_keys = {(n, x, y) for n, x, y, _ in d_mobs}
        o_mob_keys = {(n, x, y) for n, x, y, _ in o_mobs}
        matched_mob += len(d_mob_keys & o_mob_keys)
        total_orig_mob += len(o_mob_keys)
        matched_upg += len(d_upgs & o_upgs)
        total_orig_upg += len(o_upgs)

    return {
        "struct_recall": matched_struct / total_orig_struct if total_orig_struct else 1.0,
        "mob_recall": matched_mob / total_orig_mob if total_orig_mob else 1.0,
        "upg_recall": matched_upg / total_orig_upg if total_orig_upg else 1.0,
        "n_turns": len(turns),
        "totals": (total_orig_struct, total_orig_mob, total_orig_upg),
    }


def main():
    inv = json.loads((ROOT / "algos/replay_decoded/_tools/inventory.json").read_text())
    candidates = sys.argv[1:] or [
        "funnel-rush-v9", "funnel-rush-v8", "funnel-crush",
        "switch2", "python-algo-classic", "oleh-v2",
    ]
    print(f"{'algo':<25} {'replay':<50} {'S_rec':>6} {'M_rec':>6} {'U_rec':>6}  totals")
    for cand in candidates:
        info = inv[cand]
        decoded = json.loads((ROOT / f"algos/replay_decoded/{cand}/decoded_turns.json").read_text())
        # Test fidelity on each source replay
        recalls_S = []
        recalls_M = []
        recalls_U = []
        for r in info["replays"][:5]:
            p = ROOT / r
            opp_outcome = "win" if "_loss_" in p.name else "loss"
            orig = _decode_replay(p, opp_outcome)
            f = fidelity(decoded, orig)
            recalls_S.append(f["struct_recall"])
            recalls_M.append(f["mob_recall"])
            recalls_U.append(f["upg_recall"])
            tot = f["totals"]
            print(f"{cand:<25} {p.name[:50]:<50} {f['struct_recall']:>6.1%} {f['mob_recall']:>6.1%} {f['upg_recall']:>6.1%}  S={tot[0]} M={tot[1]} U={tot[2]}")
        print(f"  AVG: S={sum(recalls_S)/len(recalls_S):.1%}  M={sum(recalls_M)/len(recalls_M):.1%}  U={sum(recalls_U)/len(recalls_U):.1%}\n")


if __name__ == "__main__":
    main()
