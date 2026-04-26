"""heuristic_v2 — improvement over heuristic_v1 (incremental additions).

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

        # NEW v2: cached damage values for simulator
        self.t_dmg_base = float(ui[2].get("attackDamageWalker", 6))
        self.t_dmg_up = float(
            ui[2].get("upgrade", {}).get("attackDamageWalker", self.t_dmg_base)
        )
        self.scout_hp = float(ui[3].get("startHealth", 15))

    # ------------------------------------------------------------------
    # Main turn loop
    # ------------------------------------------------------------------
    def on_turn(self, turn_state):
        gs = gamelib.GameState(self.config, turn_state)
        gs.suppress_warnings(True)
        self.path_cache.clear()

        self._edge_block_and_remove(gs)  # NEW v2: block (0,13)/(27,13) when opp loaded
        self._build_defense(gs)
        self._build_supports(gs)
        self._spend_hoard(gs)
        self._reactive_defense(gs)
        self._pressure_response(gs)
        self._attack_phase(gs)

        gs.submit_turn()

    # ------------------------------------------------------------------
    # v2 NEW: Edge-block-and-remove (Lostkids' trick) — wall edge corners
    # when opp has stockpiled enough MP for a major scout rush.
    # Lostkids attacks at MP>=17, so we block at MP>=11 to be safe.
    # ------------------------------------------------------------------
    def _edge_block_and_remove(self, gs):
        t = gs.turn_number
        if t < 1:
            return
        enemy_mp = gs.get_resource(MP, 1)
        block_threshold = 11.0
        if enemy_mp < block_threshold:
            return
        # Block all four corner edge tiles: (0,13), (1,12), (26,12), (27,13).
        # These tiles are normally OPEN in our defense, so opp scouts can
        # step from (0,14)/(1,13)/(26,13)/(27,14) directly onto them and
        # breach in 1 frame (movement happens before turret attack in the
        # frame loop). Walling blocks the move entirely.
        # Order: prefer (27,13)/(26,12) over (0,13)/(1,12) if opp tends to
        # attack right (we go right=lostkids style); but block both sides.
        # Block 4 corner edges first (always when threatened).
        # Upgrade ONLY when opp very loaded (>= 18 MP) to avoid SP starvation
        # in mid-game.
        upgrade_edge = enemy_mp >= 18.0
        edge_locs = [[0, 13], [27, 13], [1, 12], [26, 12]]

        # NEW v2: extend deeper if opp has scored on us heavily on a side.
        # Only wall (25,11)/(2,11) — these aren't typical scout spawn tiles
        # for v2's offense. (24,10)/(3,10) are heavily used spawn tiles
        # so we don't wall them.
        attacked_left = sum(1 for (x, y) in self.scored_on if x < 14)
        attacked_right = len(self.scored_on) - attacked_left
        if attacked_right >= 5:
            edge_locs += [[25, 11]]
        if attacked_left >= 5:
            edge_locs += [[2, 11]]

        for loc in edge_locs:
            existing = gs.contains_stationary_unit(loc)
            if existing:
                continue
            if gs.attempt_spawn(WALL, loc) > 0:
                if upgrade_edge:
                    gs.attempt_upgrade(loc)
                # Mark for removal so it doesn't permanently block our spawn
                gs.attempt_remove(loc)

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

        # Upgrade schedule (v1's order — proven effective)
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

        # Reactive [3,11]/[24,11] only after heavy scored events (these block
        # our spawn pathing more than [1,11]/[26,11], so use sparingly).
        if t >= 8:
            attacked_left = sum(1 for (x, y) in self.scored_on if x < 14)
            attacked_right = len(self.scored_on) - attacked_left
            if attacked_right >= 5:
                gs.attempt_spawn(TURRET, [[24, 11]])
                gs.attempt_upgrade([[24, 11]])
            if attacked_left >= 5:
                gs.attempt_spawn(TURRET, [[3, 11]])
                gs.attempt_upgrade([[3, 11]])

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
        # Original v1 schedule: T7+, 4 supports at [12,11]/[15,11]/[13,10]/[14,10]
        if t < 7:
            return
        support_locs = [[12, 11], [15, 11], [13, 10], [14, 10]]
        for loc in support_locs:
            if not gs.contains_stationary_unit(loc):
                if gs.attempt_spawn(SUPPORT, loc) > 0:
                    gs.attempt_upgrade(loc)
            else:
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
    # v2: SCALED interceptor count based on opp MP (3+ when loaded).
    # ------------------------------------------------------------------
    def _pressure_response(self, gs):
        enemy_mp = gs.get_resource(MP, 1)
        my_mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        int_cost = gs.type_cost(INTERCEPTOR)[MP]

        # Threshold: opp has enough MP for a 9-scout wave
        if enemy_mp < 9 * scout_cost or my_mp < int_cost:
            return

        # v2: scale interceptor count by opp MP (1 per 5 MP, max 4)
        n_interceptors = 1
        if enemy_mp >= 12 * scout_cost:
            n_interceptors = 2
        if enemy_mp >= 16 * scout_cost:
            n_interceptors = 3
        if enemy_mp >= 20 * scout_cost:
            n_interceptors = 4
        # Cap by what we can afford (don't blow our entire MP)
        max_affordable = int(my_mp / int_cost)
        n_interceptors = min(n_interceptors, max_affordable)
        # Also cap so we keep MP for our own offense if we have a lot
        if my_mp >= 14 * scout_cost:
            n_interceptors = min(n_interceptors, 1)

        # Spawn on side opp comes from most often
        total_spawns = self.opp_spawn_left + self.opp_spawn_right
        if total_spawns < 3:
            spawns = [[13, 0]]
        elif self.opp_spawn_left > self.opp_spawn_right * 1.3:
            # Opp spawns from THEIR LEFT (top-left of map),
            # which targets our BOTTOM_RIGHT. Intercept on right.
            spawns = [[21, 7]]
        elif self.opp_spawn_right > self.opp_spawn_left * 1.3:
            spawns = [[6, 7]]
        else:
            # Roughly even spawns — split interceptors both sides
            spawns = [[6, 7], [21, 7]]

        for spawn in spawns:
            if not gs.game_map.in_arena_bounds(spawn):
                continue
            if gs.contains_stationary_unit(spawn):
                continue
            n = max(1, n_interceptors // len(spawns))
            gs.attempt_spawn(INTERCEPTOR, spawn, n)

    # ------------------------------------------------------------------
    # OFFENSE — adaptive scout rush + multi-prong split.
    # v2: stockpile if opp has strong corner defense, then big multi-prong.
    # ------------------------------------------------------------------
    def _attack_phase(self, gs):
        mp = gs.get_resource(MP)
        scout_cost = gs.type_cost(SCOUT)[MP]
        demo_cost = gs.type_cost(DEMOLISHER)[MP]
        t = gs.turn_number
        if t == 0:
            return

        enemy_front = self._count_enemy_front(gs)
        n_scouts = int(mp / scout_cost)
        n_demos = int(mp / demo_cost)

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

        # Scout rush trigger: MP >= 9.
        if mp >= 9 * scout_cost:
            spawn_opts = self._scout_spawn_options(gs)
            # v2: For LARGE waves (≥13 scouts) use simulator to pick spawn
            # that maximizes predicted breaches. For smaller waves, use the
            # original lowest-damage path heuristic (which is reliable for
            # small attacks vs simple opponents).
            if n_scouts >= 13 and spawn_opts:
                spawn = self._sim_pick_spawn(gs, spawn_opts, n_scouts)
            else:
                spawn = self._best_spawn(gs, spawn_opts)
            if spawn is None:
                return

            # NEW v2: Multi-prong scout split when large wave AND opp has
            # strong corner defense. Lower threshold (13) for strong opps
            # since they tend to have hardened corners we need to spread.
            opp_max_corner = max(
                self._compute_opp_corner_strength_v2(gs, 'right_zone'),
                self._compute_opp_corner_strength_v2(gs, 'left_zone'),
            )
            if n_scouts >= 13 and opp_max_corner >= 25 and len(spawn_opts) >= 2:
                second = self._second_best_spawn(gs, spawn_opts, spawn)
                if second is not None:
                    # Try both 65/35 and 50/50 splits via simulator, pick best
                    br_single, _ = self._simulate_attack(gs, spawn, SCOUT, n_scouts)
                    splits = [(0.65, 0.35), (0.5, 0.5), (0.7, 0.3)]
                    best_split_br = 0
                    best_split = None
                    for ratio_a, ratio_b in splits:
                        n_a = max(1, int(n_scouts * ratio_a))
                        n_b = n_scouts - n_a
                        if n_b < 1:
                            continue
                        br_a, _ = self._simulate_attack(gs, spawn, SCOUT, n_a)
                        br_b, _ = self._simulate_attack(gs, second, SCOUT, n_b)
                        if br_a + br_b > best_split_br:
                            best_split_br = br_a + br_b
                            best_split = (n_a, n_b)
                    if best_split and best_split_br > br_single:
                        gs.attempt_spawn(SCOUT, spawn, best_split[0])
                        gs.attempt_spawn(SCOUT, second, best_split[1])
                        return
            gs.attempt_spawn(SCOUT, spawn, 1000)

    def _sim_pick_spawn(self, gs, options, n_scouts):
        """Pick spawn predicted to give MOST breaches via simulator.

        Falls back to lowest-damage path among ties. Used only for large
        waves where simulator accuracy matters most.
        """
        if not options:
            return None
        best = None
        best_score = None
        for opt in options:
            br, _ = self._simulate_attack(gs, opt, SCOUT, n_scouts)
            # Path damage tiebreaker
            path = self._cached_path(gs, opt)
            tile_dmg = 0.0
            for p in (path or []):
                for a in gs.get_attackers(p, 0):
                    tile_dmg += self.t_dmg_up if a.upgraded else self.t_dmg_base
            inv_dmg = 1.0 / max(1.0, tile_dmg)
            score = br * 1000 + inv_dmg
            if best is None or score > best_score:
                best = opt
                best_score = score
        return best

    def _second_best_spawn(self, gs, options, exclude):
        opts = [o for o in options if o != exclude]
        return self._best_spawn(gs, opts)

    def _compute_opp_corner_strength_v2(self, gs, zone):
        """Lostkids-style corner-defense scoring (used for stockpile check)."""
        if zone == 'right_zone':
            cells = [[27, 14], [26, 14], [25, 14], [24, 14],
                     [26, 15], [25, 15], [24, 15], [25, 16]]
            edge_anchor = [26.5, 13]
        else:
            cells = [[0, 14], [1, 14], [2, 14], [3, 14],
                     [1, 15], [2, 15], [3, 15], [2, 16]]
            edge_anchor = [0.5, 13]
        strength = 0.0
        import math as _math
        for loc in cells:
            if not gs.game_map.in_arena_bounds(loc):
                continue
            unit = gs.contains_stationary_unit(loc)
            if not unit:
                continue
            d = max(0.5, _math.dist(edge_anchor, loc))
            if unit.unit_type == TURRET:
                strength += (25.0 if unit.upgraded else 5.0) / d
            elif unit.unit_type == WALL and loc[1] == 14:
                strength += 3.0 if unit.upgraded else 1.0
        return strength

    # ------------------------------------------------------------------
    # Helpers
    # ------------------------------------------------------------------
    def _scout_spawn_options(self, gs):
        """Generate scout spawn options adaptively.

        v2: use Lostkids-style edge-strength scoring to pick attack side,
        then add 2-3 spawn options on that side.
        """
        opts = [[13, 0], [14, 0]]

        # v2 NEW: Lostkids-style edge strength scoring
        # For our LEFT-spawn (BOTTOM_LEFT), scouts target opp's TOP_RIGHT,
        # which means they exit via opp's RIGHT corner zone. Lower opp
        # right-corner strength = better for our LEFT attack.
        opp_left_corner_strength = self._compute_opp_corner_strength(gs, 'right_zone')  # for our LEFT attack
        opp_right_corner_strength = self._compute_opp_corner_strength(gs, 'left_zone')  # for our RIGHT attack

        # Original v1 fallback: structure count
        opp_left_struct = self._count_opp_structures_side(gs, 'left')
        opp_right_struct = self._count_opp_structures_side(gs, 'right')

        # Pick attack side: prefer lower edge strength (Lostkids metric).
        # Append additional spawn options without disrupting [13,0]/[14,0]
        # ordering — _best_spawn picks lowest damage anyway.
        if opp_left_corner_strength + 1.5 < opp_right_corner_strength:
            # opp's right corner weak → add LEFT spawn alternative
            opts.append([3, 10])
        elif opp_right_corner_strength + 1.5 < opp_left_corner_strength:
            # opp's left corner weak → add RIGHT spawn alternative
            opts.append([24, 10])
        elif opp_left_struct + 4 < opp_right_struct:
            opts.append([3, 10])
        elif opp_right_struct + 4 < opp_left_struct:
            opts.append([24, 10])

        return [s for s in opts
                if gs.game_map.in_arena_bounds(s)
                and not gs.contains_stationary_unit(s)]

    def _compute_opp_corner_strength(self, gs, zone):
        """Lostkids-style corner-defense scoring.

        zone='right_zone' → opp's right corner (where our LEFT-spawn scouts exit)
        zone='left_zone' → opp's left corner (where our RIGHT-spawn scouts exit)
        """
        if zone == 'right_zone':
            cells = [[27, 14], [26, 14], [25, 14], [24, 14],
                     [26, 15], [25, 15], [24, 15], [25, 16]]
            edge_anchor = [26.5, 13]
        else:
            cells = [[0, 14], [1, 14], [2, 14], [3, 14],
                     [1, 15], [2, 15], [3, 15], [2, 16]]
            edge_anchor = [0.5, 13]
        strength = 0.0
        import math as _math
        for loc in cells:
            if not gs.game_map.in_arena_bounds(loc):
                continue
            unit = gs.contains_stationary_unit(loc)
            if not unit:
                continue
            d = max(0.5, _math.dist(edge_anchor, loc))
            if unit.unit_type == TURRET:
                strength += (25.0 if unit.upgraded else 5.0) / d
            elif unit.unit_type == WALL and loc[1] == 14:
                strength += 3.0 if unit.upgraded else 1.0
        return strength

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
    # FORWARD SIMULATOR (v2 NEW)
    # Frame-accurate path traversal predicting breach count for any wave.
    # Models:
    #   - per-tile turret damage (base + upgraded), one shot per frame per turret
    #   - unit speed (scout 1, demo 2, interceptor 4 frames per tile)
    #   - support shielding (each support shields once per unit)
    #   - HP accumulation across units (turret kills lowest-HP unit first)
    # ------------------------------------------------------------------
    def _simulate_attack(self, gs, spawn, unit_type, n_units):
        """Return (breaches, opp_struct_dmg) for sending N units of type
        from spawn. Uses gs.find_path_to_edge for path then frame-by-frame
        damage accumulation.
        """
        path = self._cached_path(gs, spawn)
        if not path or n_units <= 0:
            return (0, 0.0)

        # Unit stats
        ui = self.config["unitInformation"]
        if unit_type == SCOUT:
            unit_idx = 3
            speed_frames = 1
        elif unit_type == DEMOLISHER:
            unit_idx = 4
            speed_frames = 2
        elif unit_type == INTERCEPTOR:
            unit_idx = 5
            speed_frames = 4
        else:
            return (0, 0.0)
        unit_hp_base = float(ui[unit_idx].get("startHealth", 15))
        unit_dmg_struct = float(ui[unit_idx].get("attackDamageTower", 0))

        # Compute support shielding for this unit type (each support shields once)
        # Simplified: sum of (1 + 0.7*Y) for each upgraded support whose range
        # covers ANY tile on the path, and (3) for each base support similarly.
        shield_per_unit = 0.0
        try:
            support_range_base = float(ui[1].get("shieldRange", 3.5))
            support_range_up = float(ui[1].get("upgrade", {}).get("shieldRange", 7))
            support_per_unit_base = float(ui[1].get("shieldPerUnit", 3))
            support_per_unit_up_base = float(ui[1].get("upgrade", {}).get("shieldPerUnit", 1))
            support_bonus_per_y = float(ui[1].get("upgrade", {}).get("shieldBonusPerY", 0.7))
        except Exception:
            support_range_base = 3.5
            support_range_up = 7
            support_per_unit_base = 3.0
            support_per_unit_up_base = 1.0
            support_bonus_per_y = 0.7

        path_set = set((p[0], p[1]) for p in path)
        for y in range(0, 14):
            for x in range(0, 28):
                if not gs.game_map.in_arena_bounds([x, y]):
                    continue
                for u in gs.game_map[x, y]:
                    if u.player_index != 0 or u.unit_type != SUPPORT:
                        continue
                    rng = support_range_up if u.upgraded else support_range_base
                    # Check if any tile in path is in range
                    in_range = False
                    for px, py in path_set:
                        if (px - x) ** 2 + (py - y) ** 2 <= rng ** 2:
                            in_range = True
                            break
                    if not in_range:
                        continue
                    if u.upgraded:
                        shield_per_unit += support_per_unit_up_base + support_bonus_per_y * y
                    else:
                        shield_per_unit += support_per_unit_base

        unit_hp = unit_hp_base + shield_per_unit

        # Frame-by-frame simulation. Track HP of each unit in the wave
        # (using a sorted list so turret-targeting "lowest HP" is correct).
        # For efficiency: represent as sorted list of HP values.
        # Frames at each tile = speed_frames.
        unit_hps = sorted([unit_hp] * n_units)
        breaches = 0
        opp_struct_dmg = 0.0
        last_idx = len(path) - 1

        for tile_idx, tile in enumerate(path):
            if not unit_hps:
                break
            # Get attackers at this tile
            attackers = gs.get_attackers(tile, 0)
            # Filter to TURRET type only (other structures don't fire)
            turret_attackers = [a for a in attackers if a.unit_type == TURRET]

            # Frames spent at this tile
            for _frame in range(speed_frames):
                if not unit_hps:
                    break
                # Each turret fires one shot at lowest-HP target
                for tt in turret_attackers:
                    if not unit_hps:
                        break
                    dmg = self.t_dmg_up if tt.upgraded else self.t_dmg_base
                    unit_hps[0] -= dmg
                    if unit_hps[0] <= 0:
                        unit_hps.pop(0)

                # Mobile units in range of this tile may also damage stationary
                # NOTE: enemy mobile units don't typically be in range of our
                # outgoing wave; skipping for simplicity.

                # Demolisher / Scout damage to opp structures within range
                # (only count when targeting structures — assume no enemy
                # mobiles in flight for simplicity).
                if unit_dmg_struct > 0 and unit_hps:
                    # For each unit, damage opp structures in range
                    # Approximate: damage = n_alive * unit_dmg_struct per frame
                    # But only structures get damaged. The actual range is
                    # the unit's attackRange. Simplified: assume we hit one
                    # opp structure per frame if any in range.
                    n_alive = len(unit_hps)
                    # Get attacker structures of opp within unit's attack range
                    opp_struct_dmg += n_alive * unit_dmg_struct * 0.5  # rough estimate

            # Check breach at last tile
            if tile_idx == last_idx and unit_hps:
                breaches = len(unit_hps)
                unit_hps = []
                break

        return (breaches, opp_struct_dmg)

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
