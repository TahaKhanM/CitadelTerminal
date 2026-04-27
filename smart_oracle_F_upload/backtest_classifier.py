"""Backtest the OpponentClassifier against replay files.

Walks each NDJSON replay frame-by-frame, feeds opp spawn events into the
classifier, and emits the classifier's verdict at each turn end.

Replay format (per-line JSON):
  - First line: header with debug/unitInformation/timingAndReplay
  - Middle lines: per-action-frame state (turnInfo, p1Stats, p2Stats,
    p1Units, p2Units, events:{spawn, breach, …})
  - Last line: endStats with player1/player2 names + winner

Action-frame `turnInfo` is `[phase, turn_number, action_frame_idx, total_frames]`
where phase=0 (deploy), 1 (action_running), 2 (action_end). We treat the
phase=2 transition as the turn boundary.

Usage:
  python3 backtest_classifier.py <replay_path> [--our-side auto|p1|p2]
                                                [--brief]
"""
from __future__ import annotations

import argparse
import json
import os
import sys
from typing import Optional

# Make sibling modules importable when run from the package dir.
_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

from oracle_core.opp_classifier import (
    OpponentClassifier, ARCHETYPE_SINGLE, ARCHETYPE_MULTI, WINDOW_TURNS,
)


def _detect_our_side(last_line_data: dict) -> int:
    """Heuristic: oracle algos use sim_rs and burn 60+ s; opps use < 1 s."""
    es = last_line_data.get("endStats")
    if not es:
        raise ValueError("Replay has no endStats")
    p1, p2 = es["player1"], es["player2"]
    return 1 if p1["total_computation_time"] > p2["total_computation_time"] else 2


def backtest(replay_path: str, our_side: Optional[int] = None,
             brief: bool = False) -> None:
    with open(replay_path, "r") as f:
        lines = [ln.strip() for ln in f if ln.strip()]
    if not lines:
        print("EMPTY replay")
        return

    # Final endStats — names + winner.
    last = json.loads(lines[-1])
    es = last.get("endStats")
    if es is None:
        print(f"Replay has no endStats — incomplete? lines={len(lines)}")
        return

    if our_side is None:
        our_side = _detect_our_side(last)
    opp_side = 2 if our_side == 1 else 1

    p_us = es["player1"] if our_side == 1 else es["player2"]
    p_op = es["player2"] if our_side == 1 else es["player1"]
    won = (es.get("winner") == our_side)

    print(f"Replay: {os.path.basename(replay_path)}")
    print(f"  us={p_us['name']} (P{our_side}) opp={p_op['name']} (P{opp_side})")
    print(f"  result: {'W' if won else 'L'} ({p_us['points_scored']}-{p_op['points_scored']}) "
          f"turns={es.get('turns')} winner=P{es.get('winner')}")
    print(f"  compute: us={p_us['total_computation_time']/1000:.1f}s "
          f"opp={p_op['total_computation_time']/1000:.1f}s")
    print()

    classifier = OpponentClassifier(self_player_id=our_side)

    # Walk middle frames. Track turn boundaries via turnInfo[0].
    last_phase = -1
    last_turn = -1
    verdicts_per_turn = {}

    for i in range(1, len(lines) - 1):
        try:
            frame = json.loads(lines[i])
        except json.JSONDecodeError:
            continue
        ti = frame.get("turnInfo")
        if not ti or len(ti) < 2:
            continue
        phase = int(ti[0])
        turn = int(ti[1])

        # Feed all action-phase frames (phase 1 and 2 carry events).
        classifier.consume_frame(frame)

        # Detect end-of-action-phase transition: phase changes from 1 → 0/2
        # or turn increments. The replay has a transition frame at phase 2
        # which signals action-phase end.
        if (turn != last_turn and last_turn != -1) or (last_phase == 1 and phase == 2):
            # Flush previous turn.
            classifier.advance_turn()
            verdict = classifier.classify()
            stats = classifier.stats()
            verdicts_per_turn[last_turn] = (verdict, stats)
        last_phase = phase
        last_turn = turn

    # Flush last turn.
    classifier.advance_turn()
    final_verdict = classifier.classify()
    final_stats = classifier.stats()
    verdicts_per_turn[last_turn] = (final_verdict, final_stats)

    # Report.
    if brief:
        # Report when each verdict first emerged + final state.
        first_single = first_multi = first_any = None
        for t in sorted(verdicts_per_turn):
            v, s = verdicts_per_turn[t]
            if v and first_any is None:
                first_any = (t, v)
            if v == ARCHETYPE_SINGLE and first_single is None:
                first_single = t
            if v == ARCHETYPE_MULTI and first_multi is None:
                first_multi = t
        print(f"  first verdict: turn {first_any[0]} = {first_any[1]}" if first_any else "  no verdict ever emitted")
        print(f"  first SINGLE: turn {first_single}" if first_single is not None else "  never SINGLE")
        print(f"  first MULTI:  turn {first_multi}" if first_multi is not None else "  never MULTI")
        print(f"  final stats: conc={final_stats['concentration']:.2f} "
              f"top_tile={final_stats['top_tile']} (×{final_stats['top_tile_count']}) "
              f"types={final_stats['n_distinct_types']} verdict={final_verdict}")
    else:
        # Per-turn timeline
        prev_v = None
        for t in sorted(verdicts_per_turn):
            v, s = verdicts_per_turn[t]
            marker = " ←" if v != prev_v else ""
            if v is not None or marker:
                print(f"  T{t:3d}: events={s['n_events']:3d} "
                      f"top={str(s['top_tile']):>9}×{s['top_tile_count']:2d} "
                      f"conc={s['concentration']:.2f} "
                      f"types={s['n_distinct_types']} → "
                      f"{v or '-':17s}{marker}")
            prev_v = v
        print(f"\n  FINAL: {final_verdict} (matches expectation? check below)")


def main():
    p = argparse.ArgumentParser()
    p.add_argument("replay_path")
    p.add_argument("--our-side", choices=["auto", "p1", "p2"], default="auto")
    p.add_argument("--brief", action="store_true",
                   help="Report only the first/last verdicts, not full timeline")
    args = p.parse_args()
    side = None if args.our_side == "auto" else (1 if args.our_side == "p1" else 2)
    backtest(args.replay_path, our_side=side, brief=args.brief)


if __name__ == "__main__":
    main()
