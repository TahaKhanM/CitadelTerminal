"""gamelib_ext — extensions to the starter-kit gamelib used by vN algos.

Read-only rules:
- We DO NOT modify C1GamesStarterKit-master/. Every algo's bundled gamelib is
  unchanged. Instead, each algo that wants these extensions imports them
  explicitly (e.g. `from gamelib_ext import simulator`).
- The sim mirrors the engine's published rules (see docs/TARGETING_AND_PATHING.md)
  with no hidden state. If a rule changes on the server, regenerate numbers
  from `self.config` at runtime — never hardcode.
"""

from .simulator import (
    SimState,
    SimResult,
    Plan,
    snapshot_from_game_state,
    simulate_action_phase,
    apply_plan_preaction,
)

__all__ = [
    "SimState",
    "SimResult",
    "Plan",
    "snapshot_from_game_state",
    "simulate_action_phase",
    "apply_plan_preaction",
]
