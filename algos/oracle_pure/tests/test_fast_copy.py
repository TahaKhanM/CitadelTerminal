"""Parity + perf test for `_fast_copy_state` in oracle_core.search.

Run from algos/oracle_pure:
    python3 tests/test_fast_copy.py

Verifies:
1. Deep equality: deepcopy(s) == _fast_copy_state(s) for varied states.
2. Independence: mutating the copy never touches the original.
3. Speed: _fast_copy_state is at least 10x faster than copy.deepcopy.
"""
from __future__ import annotations

import sys
import time
from copy import deepcopy
from pathlib import Path

THIS = Path(__file__).resolve()
ORACLE_PURE = THIS.parent.parent
if str(ORACLE_PURE) not in sys.path:
    sys.path.insert(0, str(ORACLE_PURE))

from oracle_core.search import _fast_copy_state   # noqa: E402


def _empty_state(turn: int = 0) -> dict:
    return {
        "turn": turn,
        "p1": {"hp": 40.0, "sp": 8.0, "mp": 1.0},
        "p2": {"hp": 40.0, "sp": 8.0, "mp": 1.0},
        "structures": [],
        "mobiles": [],
    }


def _structure(uid: str, x: int, y: int, type_idx: int = 2,
               upgraded: bool = False, hp: float = 60.0,
               player: int = 1) -> dict:
    return {
        "xy": [x, y],
        "type_idx": type_idx,
        "upgraded": upgraded,
        "hp": hp,
        "uid": uid,
        "player": player,
        "turn_start_removal": None,
    }


def _mobile(uid: str, x: int, y: int, type_idx: int = 3, hp: float = 15.0,
            shield: float = 0.0, player: int = 1, spawn_xy=None) -> dict:
    return {
        "xy": [x, y],
        "type_idx": type_idx,
        "hp": hp,
        "shield": shield,
        "uid": uid,
        "player": player,
        "spawn_xy": list(spawn_xy) if spawn_xy is not None else [x, y],
        "target_edge": 0,
        "steps_taken": 0,
        "move_buildup": 0.0,
        "last_move": 0,
        "finished_navigating": False,
        "reached_target": False,
        "breached": False,
    }


def _make_states():
    """Five varied state samples covering empty / mid / late."""
    # 1. Turn 0 fresh
    s1 = _empty_state(0)

    # 2. Early turn — a couple of structures, no mobiles
    s2 = _empty_state(3)
    s2["p1"] = {"hp": 40.0, "sp": 5.0, "mp": 2.0}
    s2["p2"] = {"hp": 40.0, "sp": 6.0, "mp": 2.0}
    s2["structures"] = [
        _structure("100", 13, 11),
        _structure("101", 14, 11, upgraded=True, hp=100.0),
        _structure("102", 13, 12, type_idx=0, hp=60.0),
    ]

    # 3. Mid-game with mobiles in flight
    s3 = _empty_state(12)
    s3["p1"] = {"hp": 38.0, "sp": 14.0, "mp": 7.0}
    s3["p2"] = {"hp": 37.0, "sp": 16.0, "mp": 9.0}
    s3["structures"] = [
        _structure(str(200 + i), 11 + i, 11) for i in range(6)
    ]
    s3["structures"].extend([
        _structure("210", x, 12, type_idx=0) for x in range(2, 26) if x != 13
    ])
    s3["mobiles"] = [
        _mobile("M1", 14, 0, type_idx=3, player=1),
        _mobile("M2", 14, 0, type_idx=3, player=1),
        _mobile("M3", 13, 27, type_idx=4, player=2),
    ]

    # 4. Late-game heavy
    s4 = _empty_state(35)
    s4["p1"] = {"hp": 18.0, "sp": 22.0, "mp": 8.0}
    s4["p2"] = {"hp": 5.0, "sp": 19.0, "mp": 12.0}
    s4["structures"] = [
        _structure(f"L{i}", i % 28, (i // 28) + 1, type_idx=2 if i % 3 == 0 else 0,
                   upgraded=(i % 5 == 0))
        for i in range(40)
    ]
    s4["mobiles"] = [
        _mobile(f"K{i}", (i * 3) % 28, i % 14, type_idx=(3 + i % 3),
                player=1 + (i % 2), spawn_xy=[(i * 3) % 28, 0])
        for i in range(20)
    ]

    # 5. Turn 50 with a `spawn_xy` missing (legacy / partial)
    s5 = _empty_state(50)
    s5["p1"] = {"hp": 12.0, "sp": 30.0, "mp": 4.0}
    s5["p2"] = {"hp": 28.0, "sp": 25.0, "mp": 6.0}
    s5["mobiles"] = [
        # Missing spawn_xy on purpose — _fast_copy_state must use xy as fallback
        {
            "xy": [3, 10],
            "type_idx": 5,
            "hp": 40.0,
            "shield": 0.0,
            "uid": "no-spawn",
            "player": 2,
            "target_edge": 1,
            "steps_taken": 0,
            "move_buildup": 0.0,
            "last_move": 0,
            "finished_navigating": False,
            "reached_target": False,
            "breached": False,
        }
    ]

    return [s1, s2, s3, s4, s5]


def test_deep_equality():
    states = _make_states()
    for i, s in enumerate(states):
        a = deepcopy(s)
        b = _fast_copy_state(s)
        # b may have spawn_xy filled in even where the original lacked it
        # (sample 5). Normalize by giving a the same fallback for fair equality.
        for mob in a["mobiles"]:
            mob.setdefault("spawn_xy", list(mob["xy"]))
        assert a == b, f"sample {i}: deepcopy != fast_copy\n  a={a}\n  b={b}"
    print(f"PASS test_deep_equality: {len(states)} states match")


def test_independence():
    states = _make_states()
    for i, s in enumerate(states):
        original_snapshot = deepcopy(s)
        copy = _fast_copy_state(s)

        # Mutate every mutable container in the copy
        copy["turn"] = 999
        copy["p1"]["hp"] = -1.0
        copy["p1"]["sp"] = 9999.0
        copy["p2"]["mp"] = -7.5
        for stt in copy["structures"]:
            stt["xy"][0] = 99
            stt["xy"][1] = 99
            stt["hp"] = 0.0
        copy["structures"].append(_structure("INJECT", 0, 0))
        for mob in copy["mobiles"]:
            mob["xy"][0] = 99
            if "spawn_xy" in mob:
                mob["spawn_xy"][0] = 99
            mob["hp"] = 0.0
        copy["mobiles"].append(_mobile("INJECT", 0, 0))

        assert s == original_snapshot, (
            f"sample {i}: original mutated through fast_copy alias!\n"
            f"  before={original_snapshot}\n  after={s}"
        )
    print(f"PASS test_independence: {len(states)} states unaliased after mutation")


def test_speed():
    """_fast_copy_state must be at least 10x faster than deepcopy on average."""
    # Use the heaviest state (#4) — that's where the win matters.
    states = _make_states()
    heavy = states[3]

    n_iter = 2000

    t0 = time.perf_counter()
    for _ in range(n_iter):
        deepcopy(heavy)
    deep_s = time.perf_counter() - t0

    t0 = time.perf_counter()
    for _ in range(n_iter):
        _fast_copy_state(heavy)
    fast_s = time.perf_counter() - t0

    speedup = deep_s / max(fast_s, 1e-9)
    print(f"  deepcopy:       {deep_s*1e3:.1f} ms / {n_iter} iters "
          f"({deep_s/n_iter*1e6:.1f} us/op)")
    print(f"  fast_copy:      {fast_s*1e3:.1f} ms / {n_iter} iters "
          f"({fast_s/n_iter*1e6:.1f} us/op)")
    print(f"  speedup:        {speedup:.1f}x")
    assert speedup >= 10.0, (
        f"fast_copy_state only {speedup:.1f}x faster than deepcopy; "
        f"required >= 10x"
    )
    print(f"PASS test_speed: {speedup:.1f}x speedup over deepcopy")


def main():
    print("=" * 70)
    print("oracle_pure fast_copy_state tests (G7 parity + perf)")
    print("=" * 70)
    test_deep_equality()
    test_independence()
    test_speed()
    print("=" * 70)
    print("ALL FAST_COPY TESTS PASSED")


if __name__ == "__main__":
    main()
