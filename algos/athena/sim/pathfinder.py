"""SimCore pathfinder.

The engine uses 4 PathFinder instances (one per target edge) — each computes
idealness-first BFS: find the most-ideal reachable tile (endpoint preferred,
else best self-destruct dead-end), then validate by propagating pathlengths,
then walk back along a stable tiebreaker.

This is the algorithm from the starter's gamelib.navigation (and hence
identical to the engine). We port it here as a pure function over a
`blocked: Set[(x,y)]` without a GameState dependency so the sim can run
thousands of paths per frame cheaply.
"""

from __future__ import annotations

from collections import deque
from typing import Dict, List, Optional, Set, Tuple

from .map import (
    ARENA,
    EDGE_BOTTOM_LEFT,
    EDGE_BOTTOM_RIGHT,
    EDGE_TOP_LEFT,
    EDGE_TOP_RIGHT,
    edge_tiles,
    on_diamond,
)

HORIZONTAL = 1
VERTICAL = 2

# endpoint lists cached once
_EDGE_POINTS = {
    EDGE_TOP_RIGHT: edge_tiles(EDGE_TOP_RIGHT),
    EDGE_TOP_LEFT: edge_tiles(EDGE_TOP_LEFT),
    EDGE_BOTTOM_LEFT: edge_tiles(EDGE_BOTTOM_LEFT),
    EDGE_BOTTOM_RIGHT: edge_tiles(EDGE_BOTTOM_RIGHT),
}


def _direction(edge: int) -> Tuple[int, int]:
    """[x_dir, y_dir] matching gamelib _get_direction_from_endpoints."""
    if edge == EDGE_TOP_RIGHT:
        return (1, 1)
    if edge == EDGE_TOP_LEFT:
        return (-1, 1)
    if edge == EDGE_BOTTOM_LEFT:
        return (-1, -1)
    if edge == EDGE_BOTTOM_RIGHT:
        return (1, -1)
    raise ValueError(f"bad edge: {edge}")


def _neighbors(xy: Tuple[int, int]) -> List[Tuple[int, int]]:
    x, y = xy
    return [(x, y + 1), (x, y - 1), (x + 1, y), (x - 1, y)]


def _idealness(xy: Tuple[int, int], edge: int, end_points: Set[Tuple[int, int]]) -> int:
    """Larger = better. Endpoints get INF. Otherwise prefer moving in the
    target direction: 28 * progress_in_y + progress_in_x."""
    if xy in end_points:
        return 1 << 30
    dx, dy = _direction(edge)
    x, y = xy
    v = 0
    if dy == 1:
        v += 28 * y
    else:
        v += 28 * (27 - y)
    if dx == 1:
        v += x
    else:
        v += (27 - x)
    return v


def _pick_endpoint(start: Tuple[int, int], edge: int, blocked: Set[Tuple[int, int]]) -> Tuple[int, int]:
    """BFS from start over ALL reachable non-blocked tiles, track the most
    ideal tile seen. If an actual edge endpoint is reachable, its idealness is
    INF, so we pick it. Otherwise we get the best dead-end for self-destruct."""
    end_set = set(_EDGE_POINTS[edge])
    visited = {start}
    best = start
    best_id = _idealness(start, edge, end_set)
    q = deque([start])
    while q:
        cur = q.popleft()
        for nb in _neighbors(cur):
            if nb in visited:
                continue
            if not on_diamond(*nb):
                continue
            if nb in blocked:
                continue
            visited.add(nb)
            idv = _idealness(nb, edge, end_set)
            if idv > best_id:
                best_id = idv
                best = nb
            q.append(nb)
    return best


def _bfs_pathlengths(seeds: List[Tuple[int, int]], blocked: Set[Tuple[int, int]]) -> Dict[Tuple[int, int], int]:
    """Multi-source BFS: return {tile: pathlength_to_nearest_seed}."""
    dist: Dict[Tuple[int, int], int] = {s: 0 for s in seeds}
    q = deque(seeds)
    while q:
        cur = q.popleft()
        d = dist[cur]
        for nb in _neighbors(cur):
            if nb in dist:
                continue
            if not on_diamond(*nb):
                continue
            if nb in blocked:
                continue
            dist[nb] = d + 1
            q.append(nb)
    return dist


def _better_direction(prev_tile: Tuple[int, int], new_tile: Tuple[int, int],
                      prev_best: Tuple[int, int], prev_dir: int, edge: int) -> bool:
    """Port of gamelib's _better_direction. Prefers alternating movement axis
    if possible, then breaks ties by whichever direction is ideal."""
    if prev_dir == HORIZONTAL and new_tile[0] != prev_best[0]:
        return prev_tile[1] != new_tile[1]
    if prev_dir == VERTICAL and new_tile[1] != prev_best[1]:
        return prev_tile[0] != new_tile[0]
    if prev_dir == 0:
        return prev_tile[1] != new_tile[1]
    dx, dy = _direction(edge)
    if new_tile[1] == prev_best[1]:  # both moved horizontally
        if dx == 1 and new_tile[0] > prev_best[0]:
            return True
        if dx == -1 and new_tile[0] < prev_best[0]:
            return True
        return False
    if new_tile[0] == prev_best[0]:  # both moved vertically
        if dy == 1 and new_tile[1] > prev_best[1]:
            return True
        if dy == -1 and new_tile[1] < prev_best[1]:
            return True
        return False
    return True


def _choose_next(current: Tuple[int, int], prev_dir: int,
                 dist: Dict[Tuple[int, int], int], edge: int,
                 blocked: Set[Tuple[int, int]]) -> Tuple[int, int]:
    best = current
    best_pl = dist.get(current, 1 << 30)
    for nb in _neighbors(current):
        if not on_diamond(*nb):
            continue
        if nb in blocked:
            continue
        if nb not in dist:
            continue
        cur_pl = dist[nb]
        if cur_pl > best_pl:
            continue
        new_best = cur_pl < best_pl
        if not new_best and not _better_direction(current, nb, best, prev_dir, edge):
            continue
        best = nb
        best_pl = cur_pl
    return best


def navigate(start: Tuple[int, int], target_edge: int,
             blocked: Set[Tuple[int, int]]) -> List[Tuple[int, int]]:
    """Return the list of tiles a unit at `start` would walk, starting at
    `start`, ending at an edge tile (breach) or dead-end (self-destruct).
    Matches gamelib.navigation.navigate_multiple_endpoints semantics.

    `blocked` is the set of structure positions treated as obstacles. The
    unit's own spawn tile is allowed even if a structure is nominally there
    (start-from-blocked tile logic is handled elsewhere; this function
    assumes start is walkable)."""
    if start in blocked:
        return []

    end_points = _EDGE_POINTS[target_edge]
    ideal_tile = _pick_endpoint(start, target_edge, blocked)

    # Multi-source BFS seeds: the edge if it's reachable, else just `ideal_tile`
    if ideal_tile in set(end_points):
        dist = _bfs_pathlengths(list(end_points), blocked)
    else:
        dist = _bfs_pathlengths([ideal_tile], blocked)

    if start not in dist:
        # Start is walled in; return empty path → caller self-destructs.
        return [start]

    path = [start]
    cur = start
    prev_dir = 0
    while dist[cur] != 0:
        nxt = _choose_next(cur, prev_dir, dist, target_edge, blocked)
        if nxt == cur:
            # Trapped — return what we have; caller handles self-destruct.
            break
        prev_dir = HORIZONTAL if nxt[0] != cur[0] else VERTICAL
        path.append(nxt)
        cur = nxt
    return path
