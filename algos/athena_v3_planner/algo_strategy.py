"""Athena v3 planner — Phase 0 stub.

This is a no-op skeleton. The real planner (search-based, Plan D) will be
layered in across subsequent phases per `docs/ATHENA_BUILD_PLAN.md`. For
Phase 0 we only need the file to import cleanly so the package is loadable
by the engine and by downstream tooling.

No game logic is wired here yet. `on_game_start` just stashes the config
and `on_turn` submits an empty turn so the engine accepts the algo.
"""

import gamelib


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self.config = None

    def on_game_start(self, config):
        self.config = config

    def on_turn(self, turn_state):
        # No-op: instantiate GameState (so the engine sees a valid handshake)
        # and immediately submit it with no spawns/upgrades.
        game_state = gamelib.GameState(self.config, turn_state)
        game_state.submit_turn()


if __name__ == "__main__":
    algo = AlgoStrategy()
    algo.start()
