"""heuristic_v1 — replay-data-derived heuristic backup algo.

Built as a SAFETY NET in case the search-based Oracle algo has runtime
bugs. Strategy is grounded in aggregate stats over 309+ ranked replays
(see REPORT.md for the data analysis).

Headline insights:
  1. Top breach tiles in v13 LOSSES are LATERAL-DIAGONAL exits:
        (4,9)=296  (23,9)=238  (20,6)=229  (8,5)=163  (6,7)=159
        (9,4)=141  (5,8)=136   (10,3)=133 (2,11)=102  (22,8)=94
     Opps route scouts around our line and exit on the BOTTOM_LEFT
     /BOTTOM_RIGHT diagonals. v13's [11,5]/[16,5] second-ring
     covers the centre-low cluster (18,4)/(11,2) but leaves the
     mid-diagonal weak.
  2. Top opponents (oleh-v2, terminator, python-algo-tnb,
     python-algo-baseline, please-work-man-im-tired) all spawn
     scouts at (14,27)/(13,27) ~75% of the time and route them to
     our LEFT side >60% of the time. Strengthening the LEFT
     dominantly converts losses to wins.
  3. v13 has no Supports. Citadel's upgraded Support shield
     (1 + 0.7 * Y) means 4 back-row Supports add ~40 shield per
     scout — doubling effective HP and pushing more rushes through.

Strategy:
  • Defense: v13 skeleton + EXTRA diagonal turrets at [4,11]/[23,11]
    upgraded by T6, plus left-side wall reinforcement, plus back-row
    upgraded Supports starting T7.
  • Offense: scout rush from side with fewest opp structures (least
    coverage). Big rush at MP>=11.
  • Adapt: track opp spawn corners from on_action_frame. After ~5
    turns, weight offense to the side opp covers least.
  • Pressure response: when opp_mp >= 9, spawn 1 interceptor on the
    side they spawn from most (adaptive plug).
"""
import json
import gamelib


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()

    # ------------------------------------------------------------------
    # Game-start: read shorthand codes (Citadel uses pre-rename codes
    # FF/EF/DF/PI/EI/SI in runtime but doc-level names differ).
    # ------------------------------------------------------------------
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

        # State trackers
        self.scored_on = []           # tiles where opp scored on us
        self.opp_spawn_left = 0       # opp mobile spawns on left half (x<14)
        self.opp_spawn_right = 0      # opp mobile spawns on right half (x>=14)
        self.opp_demo_count = 0
        self.opp_scout_count = 0
        self.path_cache = {}

    # ------------------------------------------------------------------
    # Main turn loop
    # ------------------------------------------------------------------
    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._build_defense(gs)
        self._build_supports(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    # ------------------------------------------------------------------
    # CORE DEFENSE — v13 skeleton with LEFT/RIGHT diagonal reinforcement
    # tuned to the (4,9) / (23,9) hotspots from REPORT.md.
    # ------------------------------------------------------------------
    def _build_defense(self, gs):
        t = gs.turn_number

        # Layer 1: 4 centre turrets (24 SP w/ walls @ T0)
        center_turrets = [[13, 11], [14, 11], [11, 11], [16, 11]]
        # Deep sidelane turrets (cover (4,9)/(23,9) hotspots better than [7,9])
        sidelane_deep = [[7, 9], [20, 9]]
        # Outer wing turrets at y=11 (cover (5,8)-(8,5) and (22,8)-(20,6))
        outer_turrets = [[5, 11], [22, 11], [8, 11], [19, 11]]
        # Inner-corner turrets for (0,13)/(27,13) edge
        inner_corners = [[1, 13], [26, 13]]
        # NEW: full-corner turrets on diagonal — covers (2,11)/(25,11)
        outer_corners = [[0, 13], [27, 13]]
        # NEW: anti-diagonal extra turret to cover (4,9)/(23,9) cluster
        diag_reinforce = [[4, 11], [23, 11]]

        # T0: 12 turrets + wall row + edge walls (24 + 22 + 4 = ~30 SP, plus wall 22)
        gs.attempt_spawn(TURRET, center_turrets)
        gs.attempt_spawn(TURRET, sidelane_deep)
        gs.attempt_spawn(TURRET, outer_turrets)
        gs.attempt_spawn(TURRET, inner_corners)

        # Wall row at y=12 with launch gaps at (12,12)/(15,12) for scout exits
        wall_line = [[x, 12] for x in range(2, 26) if x not in (12, 15)]
        gs.attempt_spawn(WALL, wall_line)
        gs.attempt_spawn(WALL, [[2, 13], [25, 13]])

        # Upgrade schedule
        if t >= 1:
            gs.attempt_upgrade(center_turrets)
        if t >= 2:
            gs.attempt_upgrade(sidelane_deep)
        if t >= 3:
            gs.attempt_upgrade([[8, 11], [19, 11]])
        if t >= 4:
            # PRIORITY: diagonal reinforcement — covers (4,9)/(23,9)
            # which are TOP breach tiles in v13 losses.
            gs.attempt_spawn(TURRET, diag_reinforce)
        if t >= 5:
            gs.attempt_upgrade([[5, 11], [22, 11]])
        if t >= 6:
            # Upgrade diagonals (range 3.5 covers (4,9)/(23,9))
            gs.attempt_upgrade(diag_reinforce)
        if t >= 7:
            gs.attempt_upgrade(inner_corners)
        if t >= 10:
            gs.attempt_spawn(TURRET, outer_corners)
            gs.attempt_upgrade(outer_corners)

        # Auto-reupgrade damaged turrets so they don't sit at 60HP wastefully
        for loc in (center_turrets + sidelane_deep + outer_turrets +
                    inner_corners + outer_corners + diag_reinforce):
            for u in gs.game_map[loc[0], loc[1]]:
                if (u.unit_type == TURRET and not u.upgraded
                        and u.health < 0.5 * u.max_health):
                    gs.attempt_upgrade(loc)
                    break

    # ------------------------------------------------------------------
    # SUPPORTS — back-row upgraded Supports for scout-rush amplification.
    # Citadel: upgraded support at y=13 → 10.1 shield/unit, range 7.
    # 4 supports at back row ≈ +40 shield per scout — doubles HP.
    # ------------------------------------------------------------------
    def _build_supports(self, gs):
        t = gs.turn_number
        # Don't start supports before T7 (defense first)
        if t < 7:
            return
        # Place along the back row (y=13) but well inside, so they're
        # protected by the wall row at y=12.
        support_locs = [[12, 11], [15, 11], [13, 10], [14, 10]]
        # Try y=11/y=10 since y=13 might collide with our wall row
        # Pick a few back-positioned tiles; we'll upgrade for shield.
        # Note: per UNITS_REFERENCE, base supports have HP=1 — so place
        # and upgrade in same turn if possible. Cost 4+4 = 8 SP per.
        for loc in support_locs:
            if not gs.contains_stationary_unit(loc):
                if gs.attempt_spawn(SUPPORT, loc) > 0:
                    gs.attempt_upgrade(loc)
            else:
                # Already there; upgrade if not yet
                for u in gs.game_map[loc[0], loc[1]]:
                    if u.unit_type == SUPPORT and not u.upgraded:
                        gs.attempt_upgrade(loc)
                        break

    # ------------------------------------------------------------------
    # Spend hoard — second-ring + side-aware reinforcement.
    # ------------------------------------------------------------------
    def _spend_hoard(self, gs):
        t = gs.turn_number
        if t < 5:
            return

        # Second-ring covers (18,4)/(9,4)/(19,5)/(11,2) — same as v13.
        second_ring = [[11, 5], [16, 5]]
        gs.attempt_spawn(TURRET, second_ring)
        if t >= 8:
            gs.attempt_upgrade(second_ring)

        # Edge walls
        gs.attempt_spawn(WALL, [[23, 13], [4, 13]])

        # Side-aware reinforcement: if opp attacks dominantly from one side,
        # add a turret on that side first.
        attacked_left = sum(1 for (x, y) in self.scored_on if x < 14)
        attacked_right = len(self.scored_on) - attacked_left

        if t >= 12:
            if attacked_left >= attacked_right:
                gs.attempt_spawn(TURRET, [[6, 10], [23, 10]])
            else:
                gs.attempt_spawn(TURRET, [[23, 10], [6, 10]])
            gs.attempt_upgrade([[23, 10], [6, 10]])

        if t >= 15:
            gs.attempt_upgrade([[5, 12], [22, 12], [4, 12], [23, 12]])
            gs.attempt_upgrade([[23, 13], [4, 13]])

        if t >= 20:
            gs.attempt_spawn(TURRET, [[20, 10], [8, 10]])
            gs.attempt_upgrade([[20, 10], [8, 10]])

        # Late-game: even more diagonal coverage for [3,11]/[24,11]
        if t >= 25:
            gs.attempt_spawn(TURRET, [[3, 11], [24, 11]])
            gs.attempt_upgrade([[3, 11], [24, 11]])

    # ------------------------------------------------------------------
    # Reactive defense — patches scored-on tiles with backing turrets.
    # ------------------------------------------------------------------
    def _reactive_defense(self, gs):
        placed = set()
        for loc in self.scored_on[-8:]:
            x, y = loc
            if x < 14:
                candidates = [[x + 1, y + 1], [x, y + 1],
                              [x + 1, y], [x, y]]
            else:
                candidates = [[x - 1, y + 1], [x, y + 1],
                              [x - 1, y], [x, y]]
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

    # ------------------------------------------------------------------
    # Pressure response — adaptive interceptor plug when rush imminent.
    # Spawn on the side opp historically rushes from.
    # ------------------------------------------------------------------
    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        my_mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        int_cost = gs.type_cost(INTERCEPTOR)[MP]

        # Threshold: opp has enough MP for a 9-scout wave (lower than v13's
        # 6x to react sooner — v13's losses to oleh-v2 came on small rushes)
        if enemy_mp >= 9 * scout_cost and my_mp >= int_cost:
            # Spawn on side opp comes from most often
            total_spawns = self.opp_spawn_left + self.opp_spawn_right
            if total_spawns < 3:
                # Insufficient data; default to centre
                spawn = [13, 0]
            elif self.opp_spawn_left > self.opp_spawn_right:
                spawn = [6, 7]   # left side intercept
            else:
                spawn = [21, 7]  # right side intercept
            if (gs.game_map.in_arena_bounds(spawn)
                    and not gs.contains_stationary_unit(spawn)):
                gs.attempt_spawn(INTERCEPTOR, spawn)

    # ------------------------------------------------------------------
    # OFFENSE — adaptive scout rush. Default to side opp covers least.
    # ------------------------------------------------------------------
    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)

        # Demolisher push: if opp has heavy front (≥10 structures in y=14/15),
        # send demolishers to break the wall from range.
        if enemy_front >= 10 and mp >= 4 * demo_cost:
            spawn_opts = [[13, 0], [14, 0]]
            spawn_opts = [s for s in spawn_opts
                          if gs.game_map.in_arena_bounds(s)
                          and not gs.contains_stationary_unit(s)]
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(DEMOLISHER, spawn, 1000)
                return

        # Scout rush trigger: lowered to 9 (v13 was 10). At MP=9 we have
        # 9 scouts which clears a base turret (60HP) faster than v13.
        if mp >= 9 * scout_cost:
            spawn_opts = self._scout_spawn_options(gs)
            spawn = self._best_spawn(gs, spawn_opts)
            if spawn is not None:
                gs.attempt_spawn(SCOUT, spawn, 1000)

    # ------------------------------------------------------------------
    # Helpers
    # ------------------------------------------------------------------
    def _scout_spawn_options(self, gs):
        """Generate scout spawn options adaptively.

        Default options are the standard pair [13,0]/[14,0]. We add
        diagonal-edge spawns if opp's structure density on one side
        is very low (favouring the opposite side for our offense).
        """
        opts = [[13, 0], [14, 0]]
        # Side-asymmetric extras: try edge corners if we want to flank
        opp_left_struct = self._count_opp_structures_side(gs, 'left')
        opp_right_struct = self._count_opp_structures_side(gs, 'right')

        # Add corner option on weaker-defended side
        if opp_left_struct + 4 < opp_right_struct:
            opts.insert(0, [3, 10])   # punch left
        elif opp_right_struct + 4 < opp_left_struct:
            opts.insert(0, [24, 10])  # punch right

        return [s for s in opts
                if gs.game_map.in_arena_bounds(s)
                and not gs.contains_stationary_unit(s)]

    def _count_opp_structures_side(self, gs, side):
        n = 0
        for y in range(14, 28):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                if side == 'left' and x >= 14:
                    continue
                if side == 'right' and x < 14:
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index == 1 and u.stationary:
                        n += 1
        return n

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
        """Pick spawn with the LOWEST predicted incoming damage."""
        if not options:
            return None
        best = None
        best_dmg = None
        try:
            t_dmg_up = float(self.config["unitInformation"][2].get(
                "upgrade", {}).get("attackDamageWalker",
                self.config["unitInformation"][2].get(
                    "attackDamageWalker", 6)))
        except Exception:
            t_dmg_up = 20.0
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

    # ------------------------------------------------------------------
    # On action frame — track opp behavior
    # ------------------------------------------------------------------
    def on_action_frame(self, turn_string):
        try:
            state = json.loads(turn_string)
        except Exception:
            return
        events = state.get("events", {})

        # Track breaches (so reactive defense + side reinforcement adapts)
        for b in events.get("breach", []):
            if len(b) < 5:
                continue
            loc, _, _, _, owner = b[:5]
            if owner == 2:
                # opp breached us
                self.scored_on.append(loc)

        # Track opp spawn locations (mobile units only)
        # spawn = [loc, unit_type_int, unit_id, owner_player_index_one_based]
        for sp in events.get("spawn", []):
            if len(sp) < 4:
                continue
            loc, utype, _, owner = sp[:4]
            if owner != 2:
                continue  # only count opp's spawns
            # Mobile unit types: 3=scout, 4=demo, 5=interceptor
            if utype not in (3, 4, 5):
                continue
            x, _ = loc
            if x < 14:
                self.opp_spawn_left += 1
            else:
                self.opp_spawn_right += 1
            if utype == 3:
                self.opp_scout_count += 1
            elif utype == 4:
                self.opp_demo_count += 1


if __name__ == "__main__":
    AlgoStrategy().start()
