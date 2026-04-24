"""v22_phase_gate_hybrid: v21_temporal_phase_gate + classifier-dispatched counters for
scout_rush (corner Turrets + Interceptor reserve) and turret_castle (Demo train + siege
override). Parent: v21_temporal_phase_gate. Tag: "phase-gate-hybrid".
Hypothesis: combining the mirror-breaker phase-gate with ranked-loss-specific counters
yields strictly-dominant behavior vs all tested archetypes. Champion-evolution target.
"""
import gamelib
import json


# --- Turtle-cracker Support-train config (borrowed from v20_demo_train) -----
# 4 Supports at Y=13 flanking the y=12 wall gaps at x=12 and x=15. At Y=13
# each upgraded Support gives 1 + 0.7*13 = 10.1 shield → ≈ 40 shield per
# mobile rider across the 4 Supports. Only activated against the
# turret_castle archetype (where we must press or lose the T100 tiebreaker).
TRAIN_SUPPORTS = [[11, 13], [13, 13], [14, 13], [16, 13]]

# --- Scout-rush anti-corner Turret ring -------------------------------------
# Cover corners [0..2] and [25..27] by placing Turrets one tile inward on
# y=12. Upgraded Turret range 3.5 overlaps the corner spawn tiles at y=14.
SCOUT_RUSH_CORNER_TURRETS = [[3, 12], [4, 12], [23, 12], [24, 12]]

# --- Scout-rush Interceptor reserve tiles -----------------------------------
SCOUT_RUSH_INTERCEPTOR_RESERVE = [[6, 8], [21, 8]]


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()

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
        self.scored_on = []
        self.path_cache = {}
        # v21 INVENTION #2: temporal phase gate.
        self.enemy_mp_history = []
        self.attack_cooldown_until = 0

        # v22 classifier state — populated by _classify_opponent every turn.
        self.opp_archetype = "mixed"        # "turtle" | "scout_rush" | "mixed"
        self.opp_wall_count = 0
        self.opp_turret_count = 0
        self.opp_peak_wave = 0              # max mobile unit count in any frame
        self.opp_corner_spawns = 0          # cumulative corner mobile spawns

    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        # Classify first — dispatch below branches on self.opp_archetype.
        self._classify_opponent(gs)

        # v21 phase-gate temporal tracking (unchanged).
        self._update_phase_gate(gs)

        # Core v21 defense pipeline — always runs (do NOT break).
        self._build_defense(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._phase_gate_defense(gs)

        # v22 archetype-specific extra defense layered on top.
        self._archetype_defense(gs)

        # Dispatched offense: turtle → demo train, scout_rush → v21 + reserve,
        # mixed → pure v21 behavior (fallthrough — preserves 150/150 result).
        self._dispatch_offense(gs)

        gs.submit_turn()

    # ------------------------------------------------------------------ v22
    def _classify_opponent(self, gs):
        """Scan stationary enemy units to update opp_{wall,turret}_count; fold
        in peak_wave / corner_spawns already accumulated by on_action_frame.
        Locks archetype by turn 10 so downstream dispatch is stable.
        """
        wall_count = 0
        turret_count = 0
        for x in range(0, 28):
            for y in range(14, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index != 1 or not u.stationary:
                        continue
                    if u.unit_type == WALL:
                        wall_count += 1
                    elif u.unit_type == TURRET:
                        turret_count += 1
        self.opp_wall_count = wall_count
        self.opp_turret_count = turret_count

        # Re-classify until T10; after that lock whatever we decided. Mixed is
        # the safe fallthrough so v21's 150/150 mirror-break is preserved.
        if gs.turn_number <= 10:
            if wall_count >= 20 or turret_count >= 8:
                self.opp_archetype = "turtle"
            elif self.opp_peak_wave >= 12 or self.opp_corner_spawns >= 3:
                self.opp_archetype = "scout_rush"
            else:
                self.opp_archetype = "mixed"

    def _archetype_defense(self, gs):
        """Extra defensive placements gated on archetype. No-op on mixed."""
        if self.opp_archetype == "scout_rush" and gs.turn_number >= 3:
            # Pre-commit upgraded corner Turrets. attempt_spawn/upgrade
            # silently no-op on SP shortfall, so we re-attempt each turn.
            gs.attempt_spawn(TURRET, SCOUT_RUSH_CORNER_TURRETS)
            gs.attempt_upgrade(SCOUT_RUSH_CORNER_TURRETS)

    def _dispatch_offense(self, gs):
        """Pick offensive behavior based on opp_archetype.

        turtle     → demo train + siege override (press every 3rd turn from
                     T15 or we lose the T100 tiebreaker).
        scout_rush → v21 attack + Interceptor reserve on enemy-MP spike.
        mixed      → pure v21 behavior (fallthrough, preserves 150/150 result).
        """
        if self.opp_archetype == "turtle":
            self._turtle_offense(gs)
        elif self.opp_archetype == "scout_rush":
            self._scout_rush_offense(gs)
            self._attack_phase(gs)
        else:
            # mixed → pristine v21 offense.
            self._attack_phase(gs)

    def _turtle_offense(self, gs):
        """Demolisher-train siege offense vs turret_castle.

        - Places + upgrades the 4-Support train at y=13 from T2/T3.
        - From T15, every 3rd turn, commit 8 Demolishers from the best
          center spawn + 2 Interceptors from the flanks as screening.
        - Overrides the v21 phase-gate defer: turret_castle opponents
          drip-feed Scouts and will time-out-win at T100 if we don't press.
        """
        t = gs.turn_number
        # Support train (borrowed from v20_demo_train). Attempt every turn —
        # attempt_spawn/upgrade silently no-op on SP shortfall.
        if t >= 2:
            gs.attempt_spawn(SUPPORT, TRAIN_SUPPORTS)
        if t >= 3:
            gs.attempt_upgrade(TRAIN_SUPPORTS)

        if t < 15 or t % 3 != 0:
            # Off-cadence: run the v21 attacker but *skip* cooldown defer
            # — we must keep pressing or the turtle wins on HP at T100.
            self._attack_phase(gs, force_no_defer=True)
            return

        # Press turn: 8 Demolishers from best center spawn + 2 Interceptors.
        demo_spawn_opts = [s for s in ([13, 0], [14, 0])
                           if gs.game_map.in_arena_bounds(s)
                           and not gs.contains_stationary_unit(s)]
        demo_spawn = self._best_spawn(gs, demo_spawn_opts)
        if demo_spawn is not None:
            gs.attempt_spawn(DEMOLISHER, demo_spawn, 8)

        for spawn in ([10, 3], [17, 3]):
            if not gs.game_map.in_arena_bounds(spawn):
                continue
            if gs.contains_stationary_unit(spawn):
                continue
            if gs.get_resource(MP) < gs.type_cost(INTERCEPTOR)[MP]:
                break
            gs.attempt_spawn(INTERCEPTOR, spawn)

    def _scout_rush_offense(self, gs):
        """Scout-rush anti-wave: hold 2 Interceptors in reserve when enemy MP
        spikes (>=12) on cadence turns. Main offense still via v21 attack.
        """
        enemy_mp = gs.get_resource(MP, 1)
        if enemy_mp < 12:
            return
        if gs.turn_number % 3 != 0:
            return
        for spawn in SCOUT_RUSH_INTERCEPTOR_RESERVE:
            if not gs.game_map.in_arena_bounds(spawn):
                continue
            if gs.contains_stationary_unit(spawn):
                continue
            if gs.get_resource(MP) < gs.type_cost(INTERCEPTOR)[MP]:
                break
            gs.attempt_spawn(INTERCEPTOR, spawn)

    # ------------------------------------------------------------------ v21
    def _update_phase_gate(self, gs):
        """Track opponent MP; detect sharp drop => they just attacked.

        MP decay is `round(MP * 0.75, 0.1) + income`, so a natural no-spend
        turn leaves MP nearly flat or rising (income >= decay loss at low MP).
        A drop of >5 MP turn-over-turn is unambiguously a spend event.
        """
        enemy_mp = gs.get_resource(MP, 1)
        self.enemy_mp_history.append(enemy_mp)
        if len(self.enemy_mp_history) > 5:
            self.enemy_mp_history.pop(0)
        if len(self.enemy_mp_history) >= 2:
            drop = self.enemy_mp_history[-2] - self.enemy_mp_history[-1]
            if drop > 5.0:
                self.attack_cooldown_until = gs.turn_number + 2

    def _phase_gate_defense(self, gs):
        """Pre-empt expected survivor wave one turn before cooldown lifts."""
        if self.attack_cooldown_until <= 0:
            return
        if gs.turn_number != self.attack_cooldown_until - 1:
            return
        interceptor_cost = gs.type_cost(INTERCEPTOR)[MP]
        for spawn in ([10, 3], [17, 3]):
            if gs.get_resource(MP) < interceptor_cost:
                break
            if not gs.game_map.in_arena_bounds(spawn):
                continue
            if gs.contains_stationary_unit(spawn):
                continue
            gs.attempt_spawn(INTERCEPTOR, spawn)

    def _build_defense(self, gs):
        t = gs.turn_number
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        sidelane_deep = [[7, 9], [20, 9]]
        inner_corners = [[1, 13], [26, 13]]
        outer_corners = [[0, 13], [27, 13]]

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

    def _attack_phase(self, gs, force_no_defer=False):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        deferring = (not force_no_defer) and (t < self.attack_cooldown_until)
        counter = (self.attack_cooldown_until > 0
                   and t == self.attack_cooldown_until)
        scout_thresh = 6 * scout_cost if counter else 10 * scout_cost

        enemy_front = self._count_enemy_front(gs)

        if not deferring and enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        if not deferring and mp >= scout_thresh:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
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
        """Parse breach events (v21) + track enemy mobile wave / corner spawn
        counts (v22 classifier inputs).

        In raw action-frame JSON the player-index convention flips: owner=1
        is us, owner=2 is opponent.
        """
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})

        # v21: track breaches against us.
        for b in events.get("breach", []):
            loc, _, _, _, owner = b
            if owner == 2:
                self.scored_on.append(loc)

        # v22: count enemy mobile spawns + flag corner spawns for classifier.
        # Spawn event shape: [location, unit_type_idx, unit_id, owner].
        # Mobile units: index 3 (Scout), 4 (Demolisher), 5 (Interceptor).
        wave_count = 0
        for sp in events.get("spawn", []):
            if len(sp) < 4:
                continue
            loc = sp[0]
            unit_type_idx = sp[1]
            owner = sp[3]
            if owner != 2:
                continue
            if unit_type_idx not in (3, 4, 5):
                continue
            wave_count += 1
            if isinstance(loc, list) and len(loc) >= 1:
                x = loc[0]
                if x in (0, 1, 2, 25, 26, 27):
                    self.opp_corner_spawns += 1
        if wave_count > self.opp_peak_wave:
            self.opp_peak_wave = wave_count


if __name__ == "__main__":
    AlgoStrategy().start()
