"""In-process forward simulator for the Citadel Terminal action phase.

Correctness spec: docs/TARGETING_AND_PATHING.md
Unit numbers:    docs/UNITS_REFERENCE.md

The simulator replays the engine's per-frame loop:
    1. Shield pass  (each Support shields each in-range mobile once)
    2. Move pass    (each mobile whose speed counter ticks steps, or self-destructs)
    3. Attack pass  (every damaging unit picks a target via the 6-step filter;
                     damage is applied simultaneously after target selection)
    4. Cleanup      (dead units removed; path cache invalidated on structure change)

Inputs: a SimState snapshot (from GameState) + optional pre-action plan commits
        (structures built/upgraded/marked, mobiles spawned).
Output: a SimResult(dmg_to_me, dmg_to_enemy, breaches_by_me, breaches_by_enemy,
        frames, end_state).

Known accuracy limitations (v1):
- Validated against 3 real replays via tools/validate_simulator.py.
- Breach count matches exactly on early/mid turns and for well-defended
  states (replay 1: 40/40, 0/0).
- Damage totals run ~15-40 % low because (a) the engine re-targets within a
  frame as units die and (b) SD timing around dead-end pockets diverges.
- Enemy mobile pathing matches for sparse structure sets; late-game dense
  defenses with self-destruct chains produce over-counted breaches.

For Phases 1 and 2 (Support caravan + archetype classifier) the simulator is
NOT on the critical path. It is the backbone of Phase 3 (expectimax). Before
enabling Phase 3 the damage accuracy must be tightened to < 10 %. Easier
lever: swap to a subprocess-JVM-engine ground-truth simulator at ~200 ms/call
(per the prompt's fallback guidance) and pick top-k plans under a smaller
fan-out budget.
"""

from __future__ import annotations

import math
import queue
from dataclasses import dataclass, field
from typing import Dict, List, Tuple, Optional, Set


# ---------- unit stat tables (built once per config) ----------


def _build_stat_tables(config):
    """Return (base_stats, up_stats) dicts keyed by shorthand."""
    base = {}
    up = {}
    for u in config["unitInformation"]:
        sh = u.get("shorthand")
        if not sh:
            continue
        base[sh] = {
            "startHealth": float(u.get("startHealth", 0) or 0),
            "speed": float(u.get("speed", 0) or 0),
            "damageWalker": float(u.get("attackDamageWalker", 0) or 0),
            "damageTower": float(u.get("attackDamageTower", 0) or 0),
            "attackRange": float(u.get("attackRange", 0) or 0),
            "shieldRange": float(u.get("shieldRange", 0) or 0),
            "shieldPerUnit": float(u.get("shieldPerUnit", 0) or 0),
            "shieldBonusPerY": float(u.get("shieldBonusPerY", 0) or 0),
            "sdDamageWalker": float(u.get("selfDestructDamageWalker", u.get("startHealth", 0)) or 0),
            "sdDamageTower": float(u.get("selfDestructDamageTower", u.get("startHealth", 0)) or 0),
            "sdRange": float(u.get("selfDestructRange", 1.5) or 1.5),
            "sdSteps": int(u.get("selfDestructStepsRequired", 5) or 5),
            "playerBreachDamage": float(u.get("playerBreachDamage", 1) or 1),
            "unitCategory": int(u.get("unitCategory", 0) or 0),
        }
        ov = u.get("upgrade") or {}
        merged = dict(base[sh])
        for k_json, k_local in [
            ("startHealth", "startHealth"),
            ("speed", "speed"),
            ("attackDamageWalker", "damageWalker"),
            ("attackDamageTower", "damageTower"),
            ("attackRange", "attackRange"),
            ("shieldRange", "shieldRange"),
            ("shieldPerUnit", "shieldPerUnit"),
            ("shieldBonusPerY", "shieldBonusPerY"),
        ]:
            if k_json in ov:
                merged[k_local] = float(ov[k_json])
        up[sh] = merged
    return base, up


# ---------- unit record ----------


class SimUnit:
    """A unit (mobile or structure). Uses __slots__ for speed."""

    __slots__ = (
        "uid", "utype", "x", "y", "hp", "max_hp", "player", "upgraded",
        "stationary",
        # attack / shield stats (resolved for current upgrade state)
        "speed", "damage_walker", "damage_tower", "attack_range",
        "shield_range", "shield_per_unit", "shield_bonus_per_y",
        "sd_damage_walker", "sd_damage_tower", "sd_range", "sd_steps",
        "breach_damage",
        # mobile state
        "frames_since_move",
        "tiles_moved",
        "shielded_by",
        "target_edge",
        "scored",
        "dead",
        "pending_sd",
        # Path cache (per unit) — engine follows a stable path with last-move
        # axis tracking. We compute once at spawn, recompute on fingerprint change.
        "path",
        "path_step",
        "path_fp",
        "last_move_dir",  # 0=none, 1=horizontal, 2=vertical
        "pending_removal",
    )

    def __init__(self, uid, utype, x, y, hp, max_hp, player, stats, stationary, upgraded=False):
        self.uid = uid
        self.utype = utype
        self.x = x
        self.y = y
        self.hp = hp
        self.max_hp = max_hp
        self.player = player
        self.upgraded = upgraded
        self.stationary = stationary
        self.speed = stats["speed"]
        self.damage_walker = stats["damageWalker"]
        self.damage_tower = stats["damageTower"]
        self.attack_range = stats["attackRange"]
        self.shield_range = stats["shieldRange"]
        self.shield_per_unit = stats["shieldPerUnit"]
        self.shield_bonus_per_y = stats["shieldBonusPerY"]
        self.sd_damage_walker = stats.get("sdDamageWalker", max_hp)
        self.sd_damage_tower = stats.get("sdDamageTower", max_hp)
        self.sd_range = stats.get("sdRange", 1.5)
        self.sd_steps = stats.get("sdSteps", 5)
        self.breach_damage = stats.get("playerBreachDamage", 1.0)
        self.frames_since_move = 0
        self.tiles_moved = 0
        self.shielded_by = set()
        self.target_edge = -1
        self.scored = False
        self.dead = False
        self.pending_sd = False
        self.path = None
        self.path_step = 0
        self.path_fp = -1
        self.last_move_dir = 0
        self.pending_removal = False


# ---------- plan & result ----------


@dataclass
class Plan:
    """A set of commits the active player makes during a deploy phase."""
    structure_adds: List[Tuple[str, int, int]] = field(default_factory=list)  # (type, x, y)
    upgrades: List[Tuple[int, int]] = field(default_factory=list)            # (x, y)
    removals: List[Tuple[int, int]] = field(default_factory=list)
    mobile_spawns: List[Tuple[str, int, int, int]] = field(default_factory=list)  # (type, x, y, count)


@dataclass
class SimResult:
    dmg_to_me: float = 0.0
    dmg_to_enemy: float = 0.0
    breaches_by_me: int = 0
    breaches_by_enemy: int = 0
    frames: int = 0
    end_hp_me: float = 0.0
    end_hp_enemy: float = 0.0
    # Structural damage dealt (for tie-breaks / scoring heuristics)
    struct_dmg_to_me: float = 0.0
    struct_dmg_to_enemy: float = 0.0


# ---------- state ----------


class SimState:
    """Mutable state consumed by simulate_action_phase.

    After a simulation, inspect `dmg_to_me`, etc., or the SimResult returned.
    """

    __slots__ = (
        "config", "base_stats", "up_stats",
        "WALL", "SUPPORT", "TURRET", "SCOUT", "DEMOLISHER", "INTERCEPTOR",
        "STRUCTURE_TYPES",
        "arena_size", "half_arena", "hit_radius",
        "structures", "mobiles",
        "my_hp", "enemy_hp", "my_sp", "my_mp", "enemy_sp", "enemy_mp",
        "turn", "_next_uid",
        "_blocked_fp",
        "_path_cache",  # (fingerprint, target_edge, start) -> path list
        "_idealness_cache",  # (fingerprint, target_edge, start) -> ideal tile
        # stats
        "dmg_to_me", "dmg_to_enemy", "breaches_by_me", "breaches_by_enemy",
        "struct_dmg_to_me", "struct_dmg_to_enemy",
        "frames",
        # precomputed edge tile sets
        "edges",  # list[set of (x,y)] indexed by edge const
        "edges_list",  # list[list of (x,y)] preserving order for pathfinder
    )

    def __init__(self, config):
        self.config = config
        self.base_stats, self.up_stats = _build_stat_tables(config)
        ui = config["unitInformation"]
        self.WALL = ui[0]["shorthand"]
        self.SUPPORT = ui[1]["shorthand"]
        self.TURRET = ui[2]["shorthand"]
        self.SCOUT = ui[3]["shorthand"]
        self.DEMOLISHER = ui[4]["shorthand"]
        self.INTERCEPTOR = ui[5]["shorthand"]
        self.STRUCTURE_TYPES = {self.WALL, self.SUPPORT, self.TURRET}
        self.arena_size = 28
        self.half_arena = 14
        self.hit_radius = float(ui[0].get("getHitRadius", 0.51))
        self.structures: Dict[Tuple[int, int], SimUnit] = {}
        self.mobiles: List[SimUnit] = []
        self.my_hp = 40.0
        self.enemy_hp = 40.0
        self.my_sp = 0.0
        self.my_mp = 0.0
        self.enemy_sp = 0.0
        self.enemy_mp = 0.0
        self.turn = 0
        self._next_uid = 0
        self._blocked_fp = 0
        self._path_cache: Dict[Tuple[int, int, Tuple[int, int]], List[Tuple[int, int]]] = {}
        self._idealness_cache: Dict[Tuple[int, int, Tuple[int, int]], Tuple[int, int]] = {}
        self.dmg_to_me = 0.0
        self.dmg_to_enemy = 0.0
        self.breaches_by_me = 0
        self.breaches_by_enemy = 0
        self.struct_dmg_to_me = 0.0
        self.struct_dmg_to_enemy = 0.0
        self.frames = 0
        # Edge sets: TOP_RIGHT=0, TOP_LEFT=1, BOTTOM_LEFT=2, BOTTOM_RIGHT=3
        self.edges_list = [[], [], [], []]
        for n in range(self.half_arena):
            self.edges_list[0].append((self.half_arena + n, self.arena_size - 1 - n))
            self.edges_list[1].append((self.half_arena - 1 - n, self.arena_size - 1 - n))
            self.edges_list[2].append((self.half_arena - 1 - n, n))
            self.edges_list[3].append((self.half_arena + n, n))
        self.edges = [set(e) for e in self.edges_list]

    # ---- bounds / geometry ----

    def in_bounds(self, x: int, y: int) -> bool:
        hb = self.half_arena
        if y < 0 or y >= self.arena_size or x < 0 or x >= self.arena_size:
            return False
        if y < hb:
            row = y + 1
        else:
            row = self.arena_size - y
        sx = hb - row
        ex = sx + 2 * row - 1
        return sx <= x <= ex

    def get_target_edge(self, x: int, y: int) -> int:
        """Spawning at (x,y): what edge is the unit targeting?
        Same rule as GameState.get_target_edge.
        """
        left = x < self.half_arena
        bottom = y < self.half_arena
        if left and bottom:
            return 0  # TOP_RIGHT
        if left and not bottom:
            return 3  # BOTTOM_RIGHT
        if not left and bottom:
            return 1  # TOP_LEFT
        return 2  # BOTTOM_LEFT

    # ---- fingerprint / cache ----

    def _invalidate_paths(self):
        self._blocked_fp += 1
        self._path_cache.clear()
        self._idealness_cache.clear()

    # ---- structure mutations ----

    def add_structure(self, utype: str, x: int, y: int, player: int, hp: Optional[float] = None, upgraded: bool = False):
        stats = self.up_stats[utype] if upgraded else self.base_stats[utype]
        if hp is None:
            hp = stats["startHealth"]
        u = SimUnit(
            uid=self._next_uid, utype=utype, x=x, y=y, hp=hp,
            max_hp=stats["startHealth"], player=player,
            stats=stats, stationary=True, upgraded=upgraded,
        )
        self._next_uid += 1
        self.structures[(x, y)] = u
        self._invalidate_paths()
        return u

    def upgrade_structure_at(self, x: int, y: int):
        u = self.structures.get((x, y))
        if u is None or u.upgraded:
            return False
        up = self.up_stats[u.utype]
        # Save missing HP and apply upgraded stats
        missing = u.max_hp - u.hp
        u.max_hp = up["startHealth"]
        u.hp = max(1.0, u.max_hp - missing)
        u.speed = up["speed"]
        u.damage_walker = up["damageWalker"]
        u.damage_tower = up["damageTower"]
        u.attack_range = up["attackRange"]
        u.shield_range = up["shieldRange"]
        u.shield_per_unit = up["shieldPerUnit"]
        u.shield_bonus_per_y = up["shieldBonusPerY"]
        u.upgraded = True
        return True

    def _destroy_structure(self, u: SimUnit):
        key = (u.x, u.y)
        if self.structures.get(key) is u:
            del self.structures[key]
        u.dead = True
        self._invalidate_paths()

    # ---- mobile mutations ----

    def spawn_mobile(self, utype: str, x: int, y: int, player: int, upgraded: bool = False):
        stats = self.up_stats[utype] if upgraded else self.base_stats[utype]
        u = SimUnit(
            uid=self._next_uid, utype=utype, x=x, y=y, hp=stats["startHealth"],
            max_hp=stats["startHealth"], player=player,
            stats=stats, stationary=False, upgraded=upgraded,
        )
        # Determine target edge. For opponent (player 1), we mirror: their
        # (x,y) above half_arena targets the BOTTOM edges.
        u.target_edge = self.get_target_edge(x, y)
        self._next_uid += 1
        self.mobiles.append(u)
        return u

    # ---- pathfinding ----

    def _pathfinder_get_neighbors(self, loc):
        x, y = loc
        return [(x, y + 1), (x, y - 1), (x + 1, y), (x - 1, y)]

    def _idealness_direction(self, end_points):
        # end_points is list of (x,y) tuples; use first
        if not end_points:
            return (1, 1)
        x, y = end_points[0]
        dx = 1 if x >= self.half_arena else -1
        dy = 1 if y >= self.half_arena else -1
        return (dx, dy)

    def _idealness(self, loc, end_points, direction):
        if loc in end_points:
            return 10**9
        x, y = loc
        val = 0
        val += 28 * y if direction[1] == 1 else 28 * (27 - y)
        val += x if direction[0] == 1 else (27 - x)
        return val

    def _find_ideal_tile(self, start, end_points, end_set):
        """BFS to find the most-ideal reachable tile in this pocket."""
        key = (self._blocked_fp, tuple(end_set) if len(end_set) <= 14 else id(end_set), start)
        cached = self._idealness_cache.get(key)
        if cached is not None:
            return cached
        direction = self._idealness_direction(end_points)
        visited = {start}
        best = start
        best_id = self._idealness(start, end_set, direction)
        q = [start]
        head = 0
        while head < len(q):
            cur = q[head]; head += 1
            for nb in self._pathfinder_get_neighbors(cur):
                x, y = nb
                if not self.in_bounds(x, y):
                    continue
                if nb in self.structures:
                    continue
                if nb in visited:
                    continue
                visited.add(nb)
                idv = self._idealness(nb, end_set, direction)
                if idv > best_id:
                    best_id = idv
                    best = nb
                q.append(nb)
        self._idealness_cache[key] = best
        return best

    def _bfs_distances(self, start_tiles):
        """BFS from `start_tiles` over unblocked tiles, return dict tile->dist."""
        dist = {}
        for t in start_tiles:
            dist[t] = 0
        q = list(start_tiles)
        head = 0
        while head < len(q):
            cur = q[head]; head += 1
            cd = dist[cur]
            for nb in self._pathfinder_get_neighbors(cur):
                x, y = nb
                if not self.in_bounds(x, y):
                    continue
                if nb in self.structures:
                    continue
                if nb in dist:
                    continue
                dist[nb] = cd + 1
                q.append(nb)
        return dist

    def _get_path(self, start, end_points, end_set, prev_dir_init: int = 0):
        """Return the path (list of (x,y)) from start, matching gamelib's
        ShortestPathFinder. Cached by (fingerprint, end_set, start, prev_dir).
        """
        key = (self._blocked_fp, tuple(end_points), start, prev_dir_init)
        cached = self._path_cache.get(key)
        if cached is not None:
            return cached
        # Step 1: idealness
        ideal = self._find_ideal_tile(start, end_points, end_set)
        # Step 2: BFS pathlengths from ideal (or from end_points if ideal is in it)
        if ideal in end_set:
            seed = list(end_points)
        else:
            seed = [ideal]
        dist = self._bfs_distances(seed)
        if start not in dist:
            self._path_cache[key] = [start]
            return [start]
        # Step 3: walk from start
        path = [start]
        cur = start
        prev_dir = prev_dir_init
        direction = self._idealness_direction(end_points)
        safety = 0
        while dist.get(cur, 0) != 0 and safety < 1000:
            safety += 1
            neighbors = self._pathfinder_get_neighbors(cur)
            ideal_nb = cur
            best_pl = dist.get(cur, 10**9)
            for nb in neighbors:
                x, y = nb
                if not self.in_bounds(x, y) or nb in self.structures:
                    continue
                if nb not in dist:
                    continue
                cpl = dist[nb]
                if cpl > best_pl:
                    continue
                new_best = False
                if cpl < best_pl:
                    new_best = True
                # direction tiebreaker
                if not new_best:
                    if not self._better_direction(cur, nb, ideal_nb, prev_dir, direction):
                        continue
                ideal_nb = nb
                best_pl = cpl
            if ideal_nb == cur:
                break
            if cur[0] == ideal_nb[0]:
                prev_dir = 2
            else:
                prev_dir = 1
            cur = ideal_nb
            path.append(cur)
        self._path_cache[key] = path
        return path

    def _better_direction(self, prev_tile, new_tile, prev_best, prev_dir, direction):
        # Mirror gamelib.ShortestPathFinder._better_direction
        HORIZONTAL = 1; VERTICAL = 2
        if prev_dir == HORIZONTAL and new_tile[0] != prev_best[0]:
            if prev_tile[1] == new_tile[1]:
                return False
            return True
        if prev_dir == VERTICAL and new_tile[1] != prev_best[1]:
            if prev_tile[0] == new_tile[0]:
                return False
            return True
        if prev_dir == 0:
            if prev_tile[1] == new_tile[1]:
                return False
            return True
        # Same axis as best: pick toward target direction
        if new_tile[1] == prev_best[1]:  # both moved horizontal
            if direction[0] == 1 and new_tile[0] > prev_best[0]:
                return True
            if direction[0] == -1 and new_tile[0] < prev_best[0]:
                return True
            return False
        if new_tile[0] == prev_best[0]:  # both moved vertical
            if direction[1] == 1 and new_tile[1] > prev_best[1]:
                return True
            if direction[1] == -1 and new_tile[1] < prev_best[1]:
                return True
            return False
        return True

    def path_for(self, mob: SimUnit):
        """Return the full path from mob's current tile to its target edge."""
        end_points = self.edges_list[mob.target_edge]
        end_set = self.edges[mob.target_edge]
        return self._get_path((mob.x, mob.y), end_points, end_set)

    # ---- targeting ----

    def _iter_enemies_in_range(self, attacker: SimUnit):
        """Yield enemy units whose distance from attacker is < attack_range + hit_radius."""
        ax, ay = attacker.x, attacker.y
        rplus = attacker.attack_range + self.hit_radius
        rplus_sq = rplus * rplus
        # We only need enemies the attacker can damage:
        can_hit_mobile = attacker.damage_walker > 0
        can_hit_struct = attacker.damage_tower > 0
        if can_hit_mobile:
            for m in self.mobiles:
                if m.dead or m.player == attacker.player:
                    continue
                dx = m.x - ax; dy = m.y - ay
                if dx * dx + dy * dy < rplus_sq:
                    yield m
        if can_hit_struct:
            for key, s in self.structures.items():
                if s.player == attacker.player:
                    continue
                dx = s.x - ax; dy = s.y - ay
                if dx * dx + dy * dy < rplus_sq:
                    yield s

    def pick_target(self, attacker: SimUnit) -> Optional[SimUnit]:
        """6-step target filter mirroring GameState.get_target."""
        target = None
        target_stationary = True  # so any mobile beats initial
        target_distance = float("inf")
        target_health = float("inf")
        target_y = float(self.arena_size)  # for p0 attacker: we want SMALLEST-y targets (into enemy side)
        target_x_dist = -1.0
        target_uid = -1

        ax, ay = attacker.x, attacker.y
        center = self.half_arena - 0.5  # 13.5

        for u in self._iter_enemies_in_range(attacker):
            if u.dead:
                continue
            new_target = False
            u_stat = u.stationary
            dx = u.x - ax; dy = u.y - ay
            u_dist = math.sqrt(dx * dx + dy * dy)
            u_hp = u.hp
            u_y = u.y
            u_xd = abs(center - u.x)

            # Step 1: mobile beats structure
            if target_stationary and not u_stat:
                new_target = True
            elif (not target_stationary) and u_stat:
                continue

            # Step 2: minimum distance
            if not new_target:
                if target_distance > u_dist:
                    new_target = True
                elif target_distance < u_dist:
                    continue
            else:
                # If already better on step 1, still respect the filter ordering:
                # we don't compare further until the new one is committed.
                pass

            # Step 3: minimum HP
            if not new_target:
                if target_health > u_hp:
                    new_target = True
                elif target_health < u_hp:
                    continue

            # Step 4: y — deepest into attacker's side.
            # Attacker's side: for player 0 (me), my territory is y<14; attacker at y<14
            # wants targets with LARGEST y (deepest into my area from the attacker's
            # POV would be opposite, but gamelib's get_target says: for player 0 attacker,
            # target_y > unit_y is new_target — meaning attacker prefers smaller-y targets).
            if not new_target:
                if attacker.player == 0:
                    if target_y > u_y:
                        new_target = True
                    elif target_y < u_y:
                        continue
                else:
                    if target_y < u_y:
                        new_target = True
                    elif target_y > u_y:
                        continue

            # Step 5: farther from x=13.5 centerline (larger |x - 13.5|)
            if not new_target:
                if target_x_dist < u_xd:
                    new_target = True
                # gamelib does NOT continue here on ties; falls through. Safe.

            if new_target:
                target = u
                target_stationary = u_stat
                target_distance = u_dist
                target_health = u_hp
                target_y = u_y
                target_x_dist = u_xd
                target_uid = u.uid

        return target

    # ---- frame passes ----

    def _shield_pass(self):
        for (_, _), s in self.structures.items():
            if s.utype != self.SUPPORT:
                continue
            if s.hp <= 0:
                continue
            rng = s.shield_range + self.hit_radius
            rng_sq = rng * rng
            spu = s.shield_per_unit
            sby = s.shield_bonus_per_y
            sx, sy = s.x, s.y
            for m in self.mobiles:
                if m.dead or m.player != s.player:
                    continue
                if s.uid in m.shielded_by:
                    continue
                dx = m.x - sx; dy = m.y - sy
                if dx * dx + dy * dy < rng_sq:
                    # Engine formula: shield = shieldPerUnit + shieldBonusPerY * support.y
                    # Raw y — per live config (no player flip).
                    shield = spu + sby * sy
                    if shield > 0:
                        m.hp += shield
                        m.shielded_by.add(s.uid)

    def _move_pass(self):
        """Step each mobile whose speed-counter has advanced.

        Engine behaviour: mobile has a cached path. Recompute only when the
        blocking-structure fingerprint changes. Track last_move_dir so the
        axis-alternation tiebreak is stable across steps.
        """
        deferred_sd: List[SimUnit] = []
        for m in list(self.mobiles):
            if m.dead or m.scored:
                continue
            m.frames_since_move += 1
            if m.speed <= 0:
                continue
            fpt = int(round(1.0 / m.speed))
            if fpt < 1:
                fpt = 1
            if m.frames_since_move < fpt:
                continue
            m.frames_since_move = 0
            # Ensure cached path is fresh
            end_points = self.edges_list[m.target_edge]
            end_set = self.edges[m.target_edge]
            if m.path is None or m.path_fp != self._blocked_fp or m.path_step >= len(m.path) - 1:
                # Recompute path from current tile, seeded with last_move_dir
                m.path = self._get_path((m.x, m.y), end_points, end_set, m.last_move_dir)
                m.path_fp = self._blocked_fp
                m.path_step = 0
            # Take next step
            if m.path_step + 1 >= len(m.path):
                # Path exhausted without reaching edge → self-destruct
                deferred_sd.append(m)
                continue
            next_tile = m.path[m.path_step + 1]
            nx, ny = next_tile
            if not self.in_bounds(nx, ny):
                self._score_and_clear(m)
                continue
            # Determine axis of move
            if next_tile[0] == m.x:
                m.last_move_dir = 2  # vertical
            else:
                m.last_move_dir = 1  # horizontal
            m.x = nx
            m.y = ny
            m.path_step += 1
            m.tiles_moved += 1
            if (nx, ny) in end_set:
                self._score_and_clear(m)

        for m in deferred_sd:
            self._self_destruct(m)

    def _score_and_clear(self, m: SimUnit):
        """Unit reached enemy edge — deal breach damage and remove."""
        m.scored = True
        m.dead = True
        if m.player == 0:
            self.enemy_hp -= m.breach_damage
            self.breaches_by_me += 1
        else:
            self.my_hp -= m.breach_damage
            self.breaches_by_enemy += 1

    def _self_destruct(self, m: SimUnit):
        """Self-destruct: if tiles_moved >= sd_steps, damage nearby enemies."""
        if m.tiles_moved >= m.sd_steps:
            rng = m.sd_range + self.hit_radius
            rng_sq = rng * rng
            mx, my = m.x, m.y
            # Damage to nearby enemies (mobiles + structures of opposing player)
            for other in self.mobiles:
                if other.dead or other is m or other.player == m.player:
                    continue
                dx = other.x - mx; dy = other.y - my
                if dx * dx + dy * dy < rng_sq:
                    other.hp -= m.sd_damage_walker
            for key in list(self.structures.keys()):
                s = self.structures[key]
                if s.player == m.player:
                    continue
                dx = s.x - mx; dy = s.y - my
                if dx * dx + dy * dy < rng_sq:
                    dmg = m.sd_damage_tower
                    s.hp -= dmg
                    if m.player == 0:
                        self.struct_dmg_to_enemy += dmg
                    else:
                        self.struct_dmg_to_me += dmg
                    if s.hp <= 0:
                        self._destroy_structure(s)
        m.dead = True

    def _attack_pass(self):
        """Simultaneous-damage attack pass.

        Select targets first for every live attacker; then apply damage.
        """
        assignments: List[Tuple[SimUnit, SimUnit]] = []
        # Turrets
        for key, s in self.structures.items():
            if s.utype != self.TURRET:
                continue
            if s.hp <= 0:
                continue
            tgt = self.pick_target(s)
            if tgt is not None:
                assignments.append((s, tgt))
        # Mobiles (scouts, demolishers, interceptors)
        for m in self.mobiles:
            if m.dead:
                continue
            if m.damage_walker <= 0 and m.damage_tower <= 0:
                continue
            tgt = self.pick_target(m)
            if tgt is not None:
                assignments.append((m, tgt))
        # Apply damage
        for attacker, target in assignments:
            if target.dead:
                continue
            if target.stationary:
                dmg = attacker.damage_tower
            else:
                dmg = attacker.damage_walker
            if dmg <= 0:
                continue
            target.hp -= dmg
            # Track structural damage attributable to each side's attackers
            if target.stationary:
                if attacker.player == 0:
                    self.struct_dmg_to_enemy += dmg
                else:
                    self.struct_dmg_to_me += dmg
            else:
                # mobile damage: "damage to player X" = damage absorbed by their mobiles
                if target.player == 0:
                    self.dmg_to_me += dmg
                else:
                    self.dmg_to_enemy += dmg

    def _cleanup_pass(self):
        """Remove dead mobiles; destroy structures at HP<=0 (invalidates paths)."""
        # Structures
        dead_keys = [k for k, s in self.structures.items() if s.hp <= 0]
        for k in dead_keys:
            s = self.structures.pop(k)
            s.dead = True
        if dead_keys:
            self._invalidate_paths()
        # Mobiles
        self.mobiles = [m for m in self.mobiles if (not m.dead) and m.hp > 0]
        # Any mobile whose hp went <=0 is now dead (also contributes dmg already accounted).
        # Note: the above comprehension drops them; we don't need further bookkeeping.


# ---------- public API ----------


def snapshot_from_game_state(gs) -> SimState:
    """Build a SimState from a live gamelib GameState.

    Only structures and stats are copied. The caller should separately seed
    mobile_spawns via spawn_mobile() (for hypothetical action phases) or
    leave mobiles empty (for pure end-of-deploy-phase state).
    """
    state = SimState(gs.config)
    state.turn = gs.turn_number
    state.my_hp = float(gs.my_health)
    state.enemy_hp = float(gs.enemy_health)
    # Resources
    state.my_sp = float(gs.get_resource(0, 0))
    state.my_mp = float(gs.get_resource(1, 0))
    state.enemy_sp = float(gs.get_resource(0, 1))
    state.enemy_mp = float(gs.get_resource(1, 1))
    # Structures from the live map
    gm = gs.game_map
    for loc in gm:
        x, y = loc
        units = gm[x, y]
        if not units:
            continue
        for u in units:
            if u.stationary:
                # Add structure
                new_u = state.add_structure(
                    utype=u.unit_type, x=x, y=y, player=u.player_index,
                    hp=u.health, upgraded=u.upgraded,
                )
    return state


def apply_plan_preaction(state: SimState, plan_me: Plan, plan_enemy: Optional[Plan] = None):
    """Apply end-of-deploy-phase commits: structure builds, upgrades, mobile spawns.

    Mobile unit spawns use the provided counts; each spawn produces one SimUnit.
    """
    for plan, player in ((plan_me, 0), (plan_enemy, 1)):
        if plan is None:
            continue
        # Structures first (so upgrades see them)
        for (utype, x, y) in plan.structure_adds:
            if (x, y) in state.structures:
                continue
            state.add_structure(utype, x, y, player)
        for (x, y) in plan.upgrades:
            state.upgrade_structure_at(x, y)
        for (x, y) in plan.removals:
            u = state.structures.get((x, y))
            if u:
                u.pending_removal = True  # simulator itself doesn't remove mid-action
        # Mobiles
        for (utype, x, y, count) in plan.mobile_spawns:
            for _ in range(count):
                state.spawn_mobile(utype, x, y, player)


def simulate_action_phase(state: SimState, max_frames: int = 1000) -> SimResult:
    """Run the action phase until all mobiles die/score or max_frames reached."""
    frame = 0
    while frame < max_frames:
        # End condition: no live mobiles left
        any_alive = False
        for m in state.mobiles:
            if not m.dead and not m.scored:
                any_alive = True
                break
        if not any_alive:
            break
        state._shield_pass()
        state._move_pass()
        state._attack_pass()
        state._cleanup_pass()
        frame += 1
    state.frames = frame
    return SimResult(
        dmg_to_me=state.dmg_to_me,
        dmg_to_enemy=state.dmg_to_enemy,
        breaches_by_me=state.breaches_by_me,
        breaches_by_enemy=state.breaches_by_enemy,
        struct_dmg_to_me=state.struct_dmg_to_me,
        struct_dmg_to_enemy=state.struct_dmg_to_enemy,
        frames=frame,
        end_hp_me=state.my_hp,
        end_hp_enemy=state.enemy_hp,
    )
