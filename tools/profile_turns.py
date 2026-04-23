#!/usr/bin/env python3
"""
Profile an algo's per-turn compute time from a replay file, and print a
summary showing the 15s-budget risk.

Each replay frame has `p1Stats = [hp, sp, mp, time_ms]` and `p2Stats`
similarly. `time_ms` is the algo's compute time for that frame, in ms.

Usage:
    python3 tools/profile_turns.py <replay_file>                 # profile both sides
    python3 tools/profile_turns.py <replay_file> --side p1       # just P1

Critical threshold: **15000 ms** per turn. At 15s the algo starts taking 1
damage/sec. Warn at >5000 ms; flag any turn above 10000 ms.
"""
import json
import sys
from pathlib import Path
from statistics import mean, median


BUDGET_MS = 15_000
WARN_MS = 5_000
FLAG_MS = 10_000


def profile(replay_path: Path, side: str = "both"):
    with open(replay_path) as f:
        frames = []
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                frames.append(json.loads(line))
            except json.JSONDecodeError:
                continue

    # We only care about deploy-phase frames (turnInfo[0] == 0) — that's the
    # frame where the algo's last-turn compute time was finalized.
    deploy_frames = [f for f in frames if "turnInfo" in f and f["turnInfo"][0] == 0]
    if not deploy_frames:
        print(f"[profile] No deploy frames found in {replay_path}")
        return 1

    sides = ["p1", "p2"] if side == "both" else [side]
    for s in sides:
        stats_key = f"{s}Stats"
        times = []
        over_warn = []
        over_flag = []
        for f in deploy_frames:
            turn = f["turnInfo"][1]
            # time is field [3] of the stats array
            t = f[stats_key][3] if len(f[stats_key]) > 3 else 0
            times.append((turn, t))
            if t >= FLAG_MS:
                over_flag.append((turn, t))
            elif t >= WARN_MS:
                over_warn.append((turn, t))

        if not times:
            continue

        just_times = [t for _, t in times]
        print(f"\n=== {s.upper()} compute-time profile ===")
        print(f"  Turns sampled: {len(just_times)}")
        print(f"  Mean:     {mean(just_times):.1f} ms")
        print(f"  Median:   {median(just_times):.1f} ms")
        print(f"  Max:      {max(just_times):.1f} ms "
              f"(turn {max(times, key=lambda tt: tt[1])[0]})")
        print(f"  Budget:   {BUDGET_MS} ms")
        safety = BUDGET_MS - max(just_times)
        print(f"  Headroom: {safety:.0f} ms")

        if over_flag:
            print(f"\n  ⚠ {len(over_flag)} turns > {FLAG_MS} ms — TIMEOUT RISK:")
            for turn, t in over_flag[:10]:
                print(f"    T{turn:3d}: {t:>6.0f} ms")
        elif over_warn:
            print(f"\n  ! {len(over_warn)} turns > {WARN_MS} ms — consider optimizing:")
            for turn, t in over_warn[:5]:
                print(f"    T{turn:3d}: {t:>6.0f} ms")
        else:
            print("  ✓ All turns under warning threshold.")

    print()
    return 0


def main(argv):
    if len(argv) < 2:
        print(__doc__)
        return 1
    path = Path(argv[1]).expanduser()
    if not path.exists():
        print(f"Replay not found: {path}")
        return 2

    side = "both"
    if "--side" in argv:
        side = argv[argv.index("--side") + 1]
        if side not in ("p1", "p2", "both"):
            print(f"Invalid --side: {side}")
            return 3

    return profile(path, side)


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
