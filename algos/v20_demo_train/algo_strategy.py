"""v20_demo_train: v13 defense + Demolisher+Support train offense from y=10.
Parent: v13_second_ring. Tag: "demo-train-offense".
Hypothesis: offensive-archetype diversification breaks v13 mirror tie by
exploiting Demolisher range 4.5 > base Turret 2.5.

Core difference from v13:
  Replaces v13's Scout-burst offense with a Demolisher+Support train.
  Demolishers spawn from [13,0]/[14,0] (east-west bias via _best_spawn),
  then ride up the 4 upgraded Supports at [11,13], [13,13], [14,13],
  [16,13] which shield them linearly (~40 cumulative shield, per L3 of
  autonomous/LESSONS.md). From y=10 the Demolisher's 4.5 range outranges
  base Turret 2.5, letting it chip enemy defense from safety.

v13 defense (turret ring at y=11, second-ring at y=5, reactive backing)
preserved verbatim. Only the offense changes.

Scout fallback retained in case MP stockpile grows beyond useful demo
batches (e.g. enemy blocks all demo paths to edge).
"""
import gamelib
import json


# Support-train config. 4 upgraded Supports at Y=13 flanking the two gaps
# in v13's y=12 wall (x=12 and x=15 are open — Scouts/Demolishers exit
# through those). Placing Supports directly on (12,13)/(15,13) would
# seal the gaps, so we flank: (11,13) (13,13) (14,13) (16,13). At Y=13
# every Support is in range of a mobile unit in the y=10..13 corridor
# (shield range = 7 upgraded). Each upgraded Support gives 1 + 0.7*13 =
# 10.1 shield → 4 Supports ≈ 40 shield per mobile unit.
TRAIN_SUPPORTS = [[11, 13], [13, 13], [14, 13], [16, 13]]
TRAIN_PLACE_TURN = 2   # start placing Supports at T2
TRAIN_UPGRADE_TURN = 3  # upgrade once placed (silently deferred if short SP)


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()

    def on_game_start(self, config):
        self.config = config
        ui = config["unitInformation"]
        # NOTE: runtime shorthands are still the pre-rename codes
        # FF/EF/DF/PI/EI/SI (see citadel_runtime_shorthands memory).
        # We read them dynamically so this works across config variants.
        global WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR, MP, SP
        WALL = ui[0]["shorthand"]
        SUPPORT = ui[1]["shorthand"]
        TURRET = ui[2]["shorthand"]
        SCOUT = ui[3]["shorthand"]
        DEMOLISHER = ui[4]["shorthand"]
        INTERCEPTOR = ui[5]["shorthand"]
        MP = 1
        SP = 0
        self.scored_on = []
        self.path_cache = {}

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._build_defense(gs)
        self._spend_hoard(gs)
        self._support_train(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    def _support_train(self, gs):
        """Place & upgrade the 4-Support train at Y=13.

        Supports live at [11,13], [13,13], [14,13], [16,13] — flanking
        (but not sealing) the y=12 wall gaps at x=12 and x=15. Scheduled
        from T2; attempt_spawn/upgrade silently no-op on SP shortfall so
        this competes cleanly with v13's T0-T7 defense opening (40 SP).
        Re-attempted every turn so late SP surplus still completes the
        train.
        """
        t = gs.turn_number
        if t >= TRAIN_PLACE_TURN:
            gs.attempt_spawn(SUPPORT, TRAIN_SUPPORTS)
        if t >= TRAIN_UPGRADE_TURN:
            gs.attempt_upgrade(TRAIN_SUPPORTS)

    def _build_defense(self, gs):
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]

        # Opening identical to v12: 12 turrets (24 SP) + walls (16 SP).
        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        if t >= 1:
            gs.attempt_upgrade(center_turrets)
        if t >= 2:
            gs.attempt_upgrade(sidelane_deep)
        if t >= 3:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 7:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        for loc in center_turrets + sidelane_deep + outer_turrets + inner_corners + outer_corners:
            for u in gs.game_map[loc[0], loc[1]]:
                if u.unit_type == TURRET and not u.upgraded and u.health < 0.6 * u.max_health:
                    gs.attempt_upgrade(loc)
                    break

    def _spend_hoard(self, gs):
        """Convert idle SP into second-ring defences targeting the actual
        hot-spots per §5.2. [11,5]/[16,5] upgraded cover (18,4)/(9,4)/
        (19,5)/(11,2) — 79 of 129 breaches across the v12 loss set.
        """
        t = gs.turn_number
        if t < 5:
            return

        second_ring = [[11, 5], [16, 5]]
        gs.attempt_spawn(TURRET, second_ring)
        if t >= 8:
            gs.attempt_upgrade(second_ring)

        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])

        if t >= 12:
            gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])

        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

    def _reactive_defense(self, gs):
        """Place a backing Turret one tile inward from a scored-on tile.

        §5.6 fix: v12's build = [x, min(max(y+1, 0), 13)] clamped (0,13)
        to itself (the breach tile). Try multiple candidates with side-
        aware preference and fall through if out-of-bounds or occupied.
        """
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1], [x + 1, y], [x, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1], [x - 1, y], [x, y]]
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

    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        scout_cost = gs.type_cost(SCOUT)[MP]
        my_mp = gs.get_resource(MP)
        if enemy_mp >= 6 * scout_cost and my_mp >= gs.type_cost(INTERCEPTOR)[MP]:
            spawn = [13, 0]
            if gs.game_map.in_arena_bounds(spawn) and not gs.contains_stationary_unit(spawn):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    def _attack_phase(self, gs):
        """Demolisher-primary attack. Triggers on MP >= 4*demo_cost (~12 MP
        with demo_cost=3) — enough for a 4-Demolisher batch to ride the
        Support train up the x=13/14 corridor. Scout fallback kicks in if
        MP stockpile gets unusually high (hoard-stall indicator) or if
        demolishers can't path (attempt_spawn returns 0).
        """
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        spawn_opts = [[13, 0], [14, 0]]
        spawn_opts = [s for s in spawn_opts
                      if gs.game_map.in_arena_bounds(s)
                      and not gs.contains_stationary_unit(s)]

        # Demolisher trigger (PRIMARY offense): ~4 demolishers worth of MP.
        # At demo_cost=3 this is 12 MP, matching the instructor's "12 MP
        # for Demolishers" example. Demolisher range 4.5 outranges base
        # Turret 2.5, so from y=10 it chips enemy defense safely.
        if mp >= 4 * demo_cost:
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                # attempt_spawn returns the number spawned. 0 means no
                # path / blocked — fall through to Scout fallback rather
                # than burning the turn.
                n_demo = gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                if n_demo > 0:
                    return

        # Scout fallback: fires if MP stockpile is very high AND demos
        # either couldn't path or were under-triggered. Protects against
        # MP waste when demolishers can't find a path to edge.
        if mp >= 10 * scout_cost:
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    def _count_enemy_front(self, gs):
        n = 0
        for y in (14, 15):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 1
        return n

    def _best_spawn(self, gs, options):
        best = None
        best_dmg = None
        t_dmg_up = float(self.config["unitInformation"][2].get(
            "upgrade", {}).get("attackDamageWalker",
            self.config["unitInformation"][2].get("attackDamageWalker", 6)))
        for opt in options:
            path = self._cached_path(gs, opt)
            if not path:
                continue
            dmg = 0.0
            for p in path:
                dmg += len(gs.get_attackers(p, 0)) * t_dmg_up
            if best is None or dmg < best_dmg:
                best = opt
                best_dmg = dmg
        return best

    def _cached_path(self, gs, loc):
        key = (loc[0], loc[1])
        if key in self.path_cache:
            return self.path_cache[key]
        try:
            path = gs.find_path_to_edge(loc)
        except Exception:
            path = []
        self.path_cache[key] = path
        return path

    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})
        for b in events.get("breach", []):
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on.append(loc)


if __name__ == "__main__":
    AlgoStrategy().start()
