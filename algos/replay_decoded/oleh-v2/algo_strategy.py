"""Replay-decoded algo template — replays a hardcoded turn schedule.

Loads `decoded_turns.json` from the same directory and executes the
recorded structures / mobiles / upgrades on each turn.

Falls back gracefully past the last observed turn (continues spending
remaining SP / MP using the LAST observed turn's pattern).
"""

import json
import os
import sys

import gamelib

HERE = os.path.dirname(os.path.abspath(__file__))
with open(os.path.join(HERE, "decoded_turns.json")) as f:
    DECODED = json.load(f)

# Pre-compute the maximum observed turn (for fallback past EOG)
_OBSERVED_TURNS = sorted(int(k) for k in DECODED.keys())
_MAX_TURN = _OBSERVED_TURNS[-1] if _OBSERVED_TURNS else 0

# Map unit-name string -> attribute on game_state config (set on_game_start)
_UNIT_NAMES = ("WALL", "SUPPORT", "TURRET", "SCOUT", "DEMOLISHER", "INTERCEPTOR")


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self._unit_lookup = {}

    def on_game_start(self, config):
        self.config = config
        # Resolve runtime shorthands to GameUnit-compatible identifiers.
        # gamelib.game_state.GameState.attempt_spawn accepts the shorthand
        # string directly (it indexes into self.config['unitInformation']).
        # We map our human names to whatever shorthand the LIVE config uses
        # because Citadel still uses pre-rename codes (FF/EF/DF/PI/EI/SI).
        info = config.get("unitInformation", [])
        # Shorthand at index i corresponds to type_idx i.
        # 0=Wall, 1=Support, 2=Turret, 3=Scout, 4=Demolisher, 5=Interceptor
        self._unit_lookup = {}
        for i, name in enumerate(_UNIT_NAMES):
            try:
                shorthand = info[i].get("shorthand")
                if shorthand:
                    self._unit_lookup[name] = shorthand
                else:
                    self._unit_lookup[name] = name
            except Exception:
                self._unit_lookup[name] = name

    def _shorthand(self, name):
        return self._unit_lookup.get(name, name)

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        turn = int(gs.turn_number)

        entry = DECODED.get(str(turn))
        if entry is None and turn > _MAX_TURN and _MAX_TURN > 0:
            # Fall back to a "late-game persistence" loop: replay the last
            # 3 observed turns cyclically. This keeps the algo applying
            # pressure rather than going silent past EOG of training data.
            cycle_idx = (turn - _MAX_TURN - 1) % 3
            fallback_turn = _MAX_TURN - cycle_idx
            entry = DECODED.get(str(fallback_turn), {})
        elif entry is None:
            entry = {}

        # Structures (build defenses)
        for s in entry.get("structures", []) or []:
            try:
                name, loc = s
                gs.attempt_spawn(self._shorthand(name), loc)
            except Exception as e:
                gamelib.debug_write(f"struct spawn failed {s}: {e}")

        # Upgrades (single-arg attempt_upgrade(loc))
        for u in entry.get("upgrades", []) or []:
            try:
                if isinstance(u, (list, tuple)) and len(u) == 2 and isinstance(u[0], int):
                    loc = list(u)
                elif isinstance(u, (list, tuple)) and len(u) == 2 and isinstance(u[1], (list, tuple)):
                    loc = list(u[1])
                else:
                    continue
                gs.attempt_upgrade(loc)
            except Exception as e:
                gamelib.debug_write(f"upgrade failed {u}: {e}")

        # Mobiles (deploy from edges / interior)
        for m in entry.get("mobiles", []) or []:
            try:
                name, loc, n = m
                gs.attempt_spawn(self._shorthand(name), loc, int(n))
            except Exception as e:
                gamelib.debug_write(f"mobile spawn failed {m}: {e}")

        gs.submit_turn()


if __name__ == "__main__":
    AlgoStrategy().start()
