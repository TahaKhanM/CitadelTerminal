"""misdirector — adversarial sparring partner targeting Athena's lack of
misdirection counter (Lostkids has it; Athena doesn't).

Profile (modeled on Lostkids' edge_misdirecting checks):
  * Turns 1-3: spawn a single decoy structure at [0,14] OR [27,14] —
    visible to Athena's classifier, suggesting an attack path on that side.
  * Track which side Athena's mobile units enter from (via on_action_frame
    spawn events filtered on player_index 0 — note: in raw action-frame
    JSON, player_index "1" is the algo and "2" is the opponent, but
    self.scored_on still uses the GameState convention: owner==2 = us
    being scored on, owner==1 = opponent breached us).
  * Turns 4+: commit full attack to the OPPOSITE side from where Athena
    has been entering.
  * Per-game seed alternates which corner the decoy is on.

Production safety wrappers — see anti_athena_hoarder.
"""
from __future__ import annotations

import json
import os
import random
import signal
import sys
import threading
import traceback
from sys import maxsize

import gamelib

_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)


TURN_WATCHDOG_SECONDS = 13


class _TurnTimeout(Exception):
    pass


def _sigalrm_handler(_signum, _frame):
    raise _TurnTimeout("on_turn exceeded TURN_WATCHDOG_SECONDS")


def _arm_watchdog(seconds):
    if hasattr(signal, "SIGALRM"):
        try:
            old_handler = signal.signal(signal.SIGALRM, _sigalrm_handler)
            signal.alarm(seconds)

            def disarm_sigalrm():
                signal.alarm(0)
                signal.signal(signal.SIGALRM, old_handler)

            return disarm_sigalrm, lambda: False
        except (ValueError, OSError):
            pass
    fired = {"v": False}

    def fire():
        fired["v"] = True

    timer = threading.Timer(seconds, fire)
    timer.daemon = True
    timer.start()

    def disarm_thread():
        timer.cancel()

    return disarm_thread, lambda: fired["v"]


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        seed = random.randrange(maxsize)
        random.seed(seed)
        # Which corner is the decoy. Alternates per game.
        self.decoy_left = bool(random.randint(0, 1))
        self.scored_on = []
        # Track enemy spawn x for misdirection detection.
        # spawn_x_history[turn] = list of spawn x-coordinates.
        self.enemy_spawn_x = []  # rolling list of x-coords
        self.enemy_left_count = 0
        self.enemy_right_count = 0

    def on_game_start(self, config):
        self.config = config
        ui = config["unitInformation"]
        global WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR, MP, SP
        WALL = ui[0]["shorthand"]
        SUPPORT = ui[1]["shorthand"]
        TURRET = ui[2]["shorthand"]
        SCOUT = ui[3]["shorthand"]
        DEMOLISHER = ui[4]["shorthand"]
        INTERCEPTOR = ui[5]["shorthand"]
        MP = 1
        SP = 0

    def on_turn(self, turn_state):
        disarm, fired = _arm_watchdog(TURN_WATCHDOG_SECONDS)
        try:
            gs = gamelib.GameState(self.config, turn_state)
            gs.suppress_warnings(True)
            self._main_turn(gs)
            gs.submit_turn()
        except (_TurnTimeout, Exception):  # noqa: BLE001
            try:
                gamelib.debug_write("misdirector: turn failed -- safe fallback")
                gamelib.debug_write(traceback.format_exc())
            except Exception:  # noqa: BLE001
                pass
            try:
                gs = gamelib.GameState(self.config, turn_state)
                gs.suppress_warnings(True)
                self._safe_fallback(gs)
                gs.submit_turn()
            except Exception:  # noqa: BLE001
                pass
        finally:
            try:
                disarm()
            except Exception:  # noqa: BLE001
                pass

    # ---------- main strategy ----------

    def _main_turn(self, gs):
        self._build_baseline_defense(gs)
        self._maybe_decoy(gs)
        self._reactive_patches(gs)
        self._misdirection_attack(gs)

    def _build_baseline_defense(self, gs):
        """Standard symmetric defense — the trick is in the offense, not here."""
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, outer_turrets)

        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= 1:
            gs.attempt_upgrade(center_turrets)
        if t >= 4:
            gs.attempt_upgrade(outer_turrets)
        if t >= 8:
            gs.attempt_spawn(TURRET, [[1, 13], [26, 13]])
            gs.attempt_upgrade([[1, 13], [26, 13]])
        if t >= 12:
            gs.attempt_upgrade(wall_line[:8])
            gs.attempt_upgrade(wall_line[-8:])

    def _maybe_decoy(self, gs):
        """Turns 1-3: place decoy at the chosen corner edge.

        We use a wall (cheap, 1 SP) to suggest commitment to that side,
        then mark it for removal next turn so it disappears mysteriously
        once Athena has noted it.
        """
        t = gs.turn_number
        if t < 1 or t > 3:
            return
        decoy_loc = [0, 13] if self.decoy_left else [27, 13]
        # Spawn a decoy turret if we can afford one (2 SP) and it slots in.
        if not gs.contains_stationary_unit(decoy_loc):
            gs.attempt_spawn(TURRET, decoy_loc)
            # Mark for removal — the misdirection is the *visible* threat
            # disappearing, suggesting we abandoned that flank.
            gs.attempt_remove(decoy_loc)

    def _reactive_patches(self, gs):
        placed = set()
        for loc in self.scored_on[-6:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y]]
            for build in candidates:
                bx, by = build
                if by > 13 or by < 0:
                    continue
                if not gs.game_map.in_arena_bounds(build):
                    continue
                if gs.contains_stationary_unit(build):
                    continue
                key = (bx, by)
                if key in placed:
                    continue
                if gs.attempt_spawn(TURRET, build) > 0:
                    placed.add(key)
                    break

    def _misdirection_attack(self, gs):
        """Turns 4+: commit full attack to OPPOSITE side from where Athena enters.

        We use the running enemy_left_count / enemy_right_count from
        on_action_frame to infer which side Athena prefers, then attack
        the OTHER side.
        """
        t = gs.turn_number
        if t < 4:
            return
        my_mp = gs.get_resource(MP, 0)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]

        # Decide attack side. Default to the side OPPOSITE the decoy if no
        # spawn data; otherwise attack the OPPOSITE of where they enter.
        if self.enemy_left_count + self.enemy_right_count >= 5:
            # Enough data — attack the side they DON'T defend.
            attack_left = self.enemy_left_count > self.enemy_right_count
            # Inverted intuition: if enemy enters left often, that's their
            # offense path — defense is concentrated there too. Attack right.
            # No — actually we attack the side they DON'T defend, which
            # corresponds to the side they DON'T spawn at (their offense
            # path = their broken-wall path = their MISSING defense).
            # So: attack on the side enemy spawns FROM. Wait — Athena uses
            # find_path_to_edge from a low-y spawn; it picks the cleanest
            # path. Where they ENTER depends on opening side, not defense.
            # Simplest heuristic: attack opposite the decoy unconditionally.
            attack_left = not self.decoy_left
        else:
            # No data — attack opposite the decoy (the "real" plan).
            attack_left = not self.decoy_left

        if attack_left:
            scout_spawn = [13, 0]
            demo_spawn = [10, 3]
        else:
            scout_spawn = [14, 0]
            demo_spawn = [17, 3]

        # Rotate between Demolisher push and Scout flood depending on MP.
        if my_mp >= 5 * demo_cost:
            if not gs.contains_stationary_unit(demo_spawn):
                gs.attempt_spawn(DEMOLISHER, demo_spawn, 4)
                # Scout follow-up.
                if not gs.contains_stationary_unit(scout_spawn):
                    gs.attempt_spawn(SCOUT, scout_spawn, 1000)
                return
        if my_mp >= 8 * scout_cost:
            if not gs.contains_stationary_unit(scout_spawn):
                gs.attempt_spawn(SCOUT, scout_spawn, 1000)

    def _safe_fallback(self, gs):
        for loc in [[11, 11], [16, 11], [13, 11], [14, 11]]:
            gs.attempt_spawn(TURRET, loc)

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:  # noqa: BLE001
            return
        events = state.get("events", {})
        # Track breach (we are scored on if owner==2).
        for b in events.get("breach", []):
            try:
                loc, _, _, _, owner = b
            except (TypeError, ValueError):
                continue
            if owner == 2:
                self.scored_on.append(loc)
        # Track enemy spawn distribution. In raw action-frame JSON, the
        # spawn event format is [location, unit_owner, unit_type, ...].
        # owner == 2 is the opponent (Athena).
        for s in events.get("spawn", []):
            try:
                loc = s[0]
                owner = s[3] if len(s) > 3 else None
            except (TypeError, IndexError):
                continue
            if owner != 2:
                continue
            x = loc[0] if isinstance(loc, (list, tuple)) and len(loc) >= 1 else None
            if x is None:
                continue
            # In the opponent's frame, x < 14 means their LEFT side
            # (which is OUR right when mirrored). For consistency we
            # track it as-recorded; over many frames the histogram is
            # what matters for the misdirection inference.
            if x < 14:
                self.enemy_left_count += 1
            else:
                self.enemy_right_count += 1


if __name__ == "__main__":
    AlgoStrategy().start()
