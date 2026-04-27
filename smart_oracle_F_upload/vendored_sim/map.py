"""Diamond-shape map geometry + edge helpers.

The 28x28 arena is a rotated-square diamond. Tiles (x,y) are on-board iff
|(x - 13.5)| + |(y - 13.5)| < 14. Expressed integrally, this means:
    x + y >= 13      AND   x + y <= 41
    y - x <= 14      AND   x - y <= 14

Edges:
  BOTTOM_LEFT  — tiles (x, y) on the bottom-left edge: y == x + (-13) … i.e. y in [0,13], x+y == 13. Actually: x+y = 13.
  BOTTOM_RIGHT — x-y = 14 (y in [0,13])
  TOP_LEFT     — y - x = 14 (y in [14,27])
  TOP_RIGHT    — x + y = 41 (y in [14,27])

This matches the starter gamelib's GameMap edge constants.
"""

from __future__ import annotations

from typing import List, Tuple

ARENA = 28
HALF = 14

# Edge constants — integers matching gamelib.game_map constants for portability.
EDGE_TOP_RIGHT = 0
EDGE_TOP_LEFT = 1
EDGE_BOTTOM_LEFT = 2
EDGE_BOTTOM_RIGHT = 3


def on_diamond(x: int, y: int) -> bool:
    """Is (x, y) a valid arena tile? Matches starter gamelib's
    GameMap.in_arena_bounds exactly."""
    if x < 0 or x >= ARENA or y < 0 or y >= ARENA:
        return False
    if y < HALF:
        # Bottom half: row width = 2*(y+1), centered on x=13.5
        return (HALF - (y + 1)) <= x <= (HALF + y)
    else:
        # Top half: row width = 2*(27-y+1)
        return (y - HALF) <= x <= (41 - y)


def tile_owner(y: int) -> int:
    """Return 1 (p1) if tile is on bottom half, 2 (p2) if top."""
    return 1 if y < HALF else 2


def edge_tiles(edge: int) -> List[Tuple[int, int]]:
    out: List[Tuple[int, int]] = []
    if edge == EDGE_TOP_RIGHT:      # x + y == 41
        for y in range(14, 28):
            x = 41 - y
            out.append((x, y))
    elif edge == EDGE_TOP_LEFT:     # y - x == 14
        for y in range(14, 28):
            x = y - 14
            out.append((x, y))
    elif edge == EDGE_BOTTOM_LEFT:  # x + y == 13
        for y in range(0, 14):
            x = 13 - y
            out.append((x, y))
    elif edge == EDGE_BOTTOM_RIGHT: # x - y == 14
        for y in range(0, 14):
            x = y + 14
            out.append((x, y))
    return out


def spawn_tile_target_edge(spawn_xy: Tuple[int, int]) -> int:
    """Given where a unit is deployed, what edge is its goal? Engine picks
    the opposite edge from the deploy edge. Rule (verified empirically from
    gamelib.GameState.get_target_edge):

      Bottom-left deploy (x<HALF, y<HALF → we're on bottom-left side) → target TOP_RIGHT
      Bottom-right deploy (x>=HALF, y<HALF) → target TOP_LEFT
      Top-left deploy (x<HALF, y>=HALF) → target BOTTOM_RIGHT
      Top-right deploy (x>=HALF, y>=HALF) → target BOTTOM_LEFT
    """
    x, y = spawn_xy
    if y < HALF:
        return EDGE_TOP_RIGHT if x < HALF else EDGE_TOP_LEFT
    else:
        return EDGE_BOTTOM_RIGHT if x < HALF else EDGE_BOTTOM_LEFT


def euclidean(a: Tuple[int, int], b: Tuple[int, int]) -> float:
    dx = a[0] - b[0]
    dy = a[1] - b[1]
    return (dx * dx + dy * dy) ** 0.5


def manhattan(a: Tuple[int, int], b: Tuple[int, int]) -> int:
    return abs(a[0] - b[0]) + abs(a[1] - b[1])
