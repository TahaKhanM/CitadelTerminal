# Smoke test — athena_baseline_lostkids vs v13_second_ring

Date: 2026-04-25
Engine: `C1GamesStarterKit-master/engine.jar` (August_17_2020), Java
OpenJDK Temurin 25.0.2.
Driver: `C1GamesStarterKit-master/scripts/run_match.py`.

## Command

```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
python3 C1GamesStarterKit-master/scripts/run_match.py \
    /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/athena_baseline_lostkids \
    /Users/tahakhan/Documents/Work/Projects/CitadelTerminal/algos/v13_second_ring
```

**Note**: `run_match.py` `cd`s into the starter-kit dir, so algo paths
must be **absolute**. A first attempt with relative paths failed with
`No such file or directory` at engine bootup. This isn't a port issue —
it's a documented run_match.py quirk.

## Result

- Both algos booted cleanly (no `FailedToLoad`).
- Match completed normally — 37 turns.
- **Winner**: `athena_baseline_lostkids` (P1).
- Points scored: 47 (P1) – 8 (P2).
- `crashed: false` for both sides; `timeout_death: false` for both.
- Total per-side compute: P1 386 ms, P2 281 ms across 37 turns
  (≈10 ms / turn average — well under the 13 s watchdog).

`endStats` (extracted from final replay frame):

```json
{
  "duration": 4933,
  "winner": 1,
  "player1": {
    "name": "athena_baseline_lostkids",
    "points_scored": 47.0,
    "stationary_resource_spent": 203.0,
    "dynamic_resource_spent": 78.0,
    "dynamic_resource_destroyed": 31.0,
    "stationary_resource_left_on_board": 112.0,
    "crashed": false,
    "timeout_death": false,
    "total_computation_time": 386
  },
  "player2": {
    "name": "v13_second_ring",
    "points_scored": 8.0,
    "stationary_resource_spent": 164.0,
    "dynamic_resource_spent": 115.0,
    "dynamic_resource_destroyed": 107.0,
    "stationary_resource_left_on_board": 128.0,
    "crashed": false,
    "timeout_death": false,
    "total_computation_time": 281
  },
  "frames": 2346,
  "turns": 37
}
```

## Replay artifact

- Engine wrote to:
  `C1GamesStarterKit-master/replays/p1-25-04-2026-00-09-55-1777072195778-1278915005.replay`
- Persisted copy:
  `replays/smoke_tests/lostkids_baseline_smoke_2026-04-25.replay`
  (5.25 MB — full per-frame replay).

## stderr observations

The Lostkids strategy is idempotent: it re-issues every build_order
spawn each turn and the engine logs "Could not spawn ... blocked" for
already-placed structures. These are EXPECTED — not crashes — and
match the original Lostkids stderr fingerprint. `attempt_spawn`
returns 0 on a blocked tile, so the algo continues normally.

## Sign-off

- Package import: PASS
- Engine bootup: PASS (with absolute-path invocation)
- Game completes to natural turn-100 limit OR sudden-death: PASS
  (37-turn finish — Lostkids broke through and crashed v13's HP)
- No crashes / timeouts: PASS
- Replay file produced: PASS

This single match is N=1 evidence only and cannot be used to claim
Lostkids consistently beats v13 — that's what task 5's `/bestof 20`
exists to measure with a Wilson 95 % CI. The smoke test only
establishes that the port runs end-to-end without fault.
