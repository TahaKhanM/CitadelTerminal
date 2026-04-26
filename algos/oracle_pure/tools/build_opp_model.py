"""Offline opponent-model prior builder for oracle_pure.

Scans every .replay file in the Ranked Replays/ corpus and emits a
JSON file of bucket -> [{sig, weight}] entries.

Usage:
    python3 algos/oracle_pure/tools/build_opp_model.py [--out PATH] [--limit N]

Bucket keys come from oracle_core.opponent_model.bucket_key:
    "{turn_band}|{opp_mp_band}|{our_mp_band}|{breach_band}"

Each .replay turn yields TWO observations (each side becomes "us"
once for symmetric training).
"""
from __future__ import annotations

import argparse
import json
import os
import sys
from collections import Counter, defaultdict
from pathlib import Path

# Make oracle_core importable
THIS = Path(__file__).resolve()
ORACLE_PURE = THIS.parent.parent
if str(ORACLE_PURE) not in sys.path:
    sys.path.insert(0, str(ORACLE_PURE))

from oracle_core.opponent_model import (   # noqa: E402
    ActionSignature, _sig_to_dict, _x_to_launcher_bucket, bucket_key,
)


REPO = ORACLE_PURE.parent.parent   # CitadelTerminal/
RANKED_DIR = REPO / "Ranked Replays"


def parse_replay(path: Path):
    """Yield per-turn (turn, p1_stats, p2_stats, p1_spawns, p2_spawns).

    p1_spawns / p2_spawns are lists of (unit_type_idx, x, y) for new
    mobile units (type 3/4/5) reported in the first action frame.
    """
    deploy_frames = {}   # turn -> stats dict
    action_frames = {}   # turn -> first-frame events
    try:
        with path.open() as f:
            for line in f:
                line = line.strip()
                if not line:
                    continue
                try:
                    d = json.loads(line)
                except Exception:
                    continue
                ti = d.get("turnInfo")
                if not ti or len(ti) < 3:
                    continue
                phase, turn, frame_idx = ti[0], ti[1], ti[2]
                if phase == 0 and turn not in deploy_frames:
                    deploy_frames[turn] = {
                        "p1": list(d.get("p1Stats", [])),
                        "p2": list(d.get("p2Stats", [])),
                    }
                elif phase == 1 and frame_idx == 0 and turn not in action_frames:
                    action_frames[turn] = d.get("events", {})
    except Exception as e:
        print(f"  warn: parse failed for {path.name}: {e!r}", file=sys.stderr)
        return

    for t in sorted(action_frames):
        if t not in deploy_frames:
            continue
        events = action_frames[t]
        p1_spawns, p2_spawns = [], []
        for sp in events.get("spawn", []) or []:
            if not sp or len(sp) < 4:
                continue
            loc, utype, _uid, owner = sp[:4]
            if int(utype) not in (3, 4, 5):
                continue
            try:
                x, y = int(loc[0]), int(loc[1])
            except Exception:
                continue
            if int(owner) == 1:
                p1_spawns.append((int(utype), x, y))
            elif int(owner) == 2:
                p2_spawns.append((int(utype), x, y))

        yield t, deploy_frames[t]["p1"], deploy_frames[t]["p2"], p1_spawns, p2_spawns


def spawns_to_signature(spawns):
    scout_locs = [(x, y) for ut, x, y in spawns if ut == 3]
    demo_locs = [(x, y) for ut, x, y in spawns if ut == 4]
    int_locs = [(x, y) for ut, x, y in spawns if ut == 5]

    def primary(locs):
        if not locs:
            return None
        from collections import Counter
        c = Counter(_x_to_launcher_bucket(x) for x, _ in locs)
        return c.most_common(1)[0][0]

    return ActionSignature(
        scout_count=len(scout_locs),
        demo_count=len(demo_locs),
        int_count=len(int_locs),
        scout_launcher=primary(scout_locs),
        demo_launcher=primary(demo_locs),
        int_launcher=primary(int_locs),
    )


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default=str(ORACLE_PURE / "data" / "opp_model_priors.json"))
    ap.add_argument("--limit", type=int, default=None,
                    help="Only process N replays (testing).")
    ap.add_argument("--ranked", default=str(RANKED_DIR))
    args = ap.parse_args()

    ranked_root = Path(args.ranked)
    if not ranked_root.exists():
        print(f"ERROR: ranked dir not found: {ranked_root}", file=sys.stderr)
        sys.exit(1)

    files = sorted(ranked_root.rglob("*.replay"))
    if args.limit:
        files = files[:args.limit]
    print(f"Processing {len(files)} replays...", file=sys.stderr)

    bucket_counters = defaultdict(Counter)
    n_obs = 0
    n_replays_ok = 0
    for i, path in enumerate(files):
        try:
            n_added = 0
            for t, p1s, p2s, p1_spawns, p2_spawns in parse_replay(path):
                # p1's perspective (us=p1, opp=p2): record p2's spawns
                if len(p1s) >= 3 and len(p2s) >= 3:
                    p1_mp = float(p1s[2])
                    p2_mp = float(p2s[2])
                    sig_opp = spawns_to_signature(p2_spawns)
                    bk = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp, recent_breaches=0)
                    bucket_counters[bk][sig_opp] += 1
                    n_obs += 1
                    n_added += 1
                    # p2's perspective (us=p2, opp=p1): record p1's spawns
                    sig_opp = spawns_to_signature(p1_spawns)
                    bk = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp, recent_breaches=0)
                    bucket_counters[bk][sig_opp] += 1
                    n_obs += 1
                    n_added += 1
            if n_added > 0:
                n_replays_ok += 1
        except Exception as e:
            print(f"  WARN: {path.name}: {e!r}", file=sys.stderr)
            continue
        if (i + 1) % 50 == 0:
            print(f"  {i+1}/{len(files)}  total_obs={n_obs}  buckets={len(bucket_counters)}",
                  file=sys.stderr)

    print(f"\nDone. {n_replays_ok}/{len(files)} replays parsed; "
          f"{n_obs} obs across {len(bucket_counters)} buckets",
          file=sys.stderr)

    # Convert to serializable form
    out = {}
    for bk, counter in bucket_counters.items():
        # Drop "all-zero" signatures (no spawns) since they correspond to
        # opp doing nothing and we already inject that as a default sample.
        rows = []
        for sig, cnt in counter.most_common():
            if sig.is_empty():
                continue
            rows.append({"sig": _sig_to_dict(sig), "weight": float(cnt)})
        # Cap rows per bucket at 16 most-common to keep file small
        rows = rows[:16]
        if rows:
            out[bk] = rows

    out_path = Path(args.out)
    out_path.parent.mkdir(parents=True, exist_ok=True)
    with out_path.open("w") as f:
        json.dump(out, f, indent=1)
    print(f"Wrote {out_path}  ({len(out)} buckets, "
          f"{sum(len(v) for v in out.values())} signatures)",
          file=sys.stderr)


if __name__ == "__main__":
    main()
