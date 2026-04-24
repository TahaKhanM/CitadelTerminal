"""Bit-exact port of engine.jar `PathFinder` + `CoordQueue`.

Source:
  research/engine_decompiled/sources/com/c1games/terminal/game/systems/
    map/path/PathFinder.java (491 LOC, CFR 0.152 output)
  research/engine_decompiled/sources/com/c1games/terminal/game/systems/
    map/path/CoordQueue.java  (76 LOC)

See research/engine_decompiled/PATHFINDER_SPEC.md for the full spec
with line citations.

Ownership model: 4 `PathFinder` instances per game (one per target edge).
Each maintains persistent state across frames. Walls added via `put(x,y)`,
removed via `remove(x,y)`. `get_step(x, y, prev_direction)` returns the
next tile coordinates — caller also uses it to detect same-tile return
for `finished_navigating`.
"""

from __future__ import annotations

from typing import List, Optional, Sequence, Tuple

# Status byte values (PathFinder.java:10-12)
_INVALID = 0
_OPEN = 1
_WALL = 2

# Direction byte values (PathFinder.java:13-17)
_NONE = 0
_LEFT = 1
_RIGHT = 2
_DOWN = 3
_UP = 4

# Neighbor offsets (PathFinder.java:19-21). ORDER MATTERS for filter #1.
_NEIGHBOR_X = (1, -1, 0, 0)
_NEIGHBOR_Y = (0, 0, 1, -1)
_PARENT_DIRECTION = (_LEFT, _RIGHT, _DOWN, _UP)

# getStep() constants (PathFinder.java:351-353)
_SPAWNED = 0
_HORIZONTAL = 1
_VERTICAL = 2

_INT_MAX = 0x7FFFFFFF
_INT_MIN = -(1 << 31)


class _CoordQueue:
    """Ring buffer port of `CoordQueue`. `push` appends, `next` pops FIFO.
    Mirrors the data[] doubling in CoordQueue.java:14-28."""

    __slots__ = ("_data", "_start", "_end", "_size", "currX", "currY")

    def __init__(self):
        self._data: List[int] = [0] * 200
        self._start = 0
        self._end = 0
        self._size = 0
        self.currX = -559038737
        self.currY = -558907665

    def push(self, x: int, y: int) -> None:
        if (self._size + 1) * 2 >= len(self._data):
            new_data = [0] * (len(self._data) * 2)
            for i in range(self._size * 2):
                new_data[i] = self._data[(i + self._start) % len(self._data)]
            self._data = new_data
            self._start = 0
            self._end = self._size * 2
        self._data[self._end] = x
        self._data[self._end + 1] = y
        self._end = (self._end + 2) % len(self._data)
        self._size += 1

    def next(self) -> bool:  # noqa: A003  (matches Java name)
        if self._start != self._end:
            self.currX = self._data[self._start]
            self.currY = self._data[self._start + 1]
            self._start = (self._start + 2) % len(self._data)
            self._size -= 1
            return True
        return False

    def drain(self) -> None:
        self._start = 0
        self._end = 0
        self._size = 0

    def size(self) -> int:
        return self._size


class PathFinder:
    """Exact port of `PathFinder.java`. One instance per target edge."""

    __slots__ = (
        "_dimension", "_size", "_direction", "_perfect_list",
        "_status", "_parent_direction", "_pathlength",
        "_idealness_arr", "_visited", "_explored", "_perfect_lookup",
        "_current_queue", "_frontier_queue", "_start_search_at",
        "_searched_best_idealness", "_searched_curr_pathlength",
        "board_invalid", "_requires_validation", "_possible_steps",
        "_min_distance_scratch",  # P1 perf: pre-alloc scratch reused by
                                  # _get_min_distance_from_idealness (135k
                                  # calls/run — was 27M int allocs).
    )

    def __init__(
        self,
        dimension: int,
        direction: Tuple[int, int],
        walls: Sequence[Tuple[int, int]],
        perfect: Sequence[Tuple[int, int]],
    ):
        self._dimension = dimension
        self._size = dimension * dimension
        self._direction = direction
        self._perfect_list: List[Tuple[int, int]] = [tuple(p) for p in perfect]
        self._status = bytearray(self._size)
        self._parent_direction = [0] * self._size  # short[]
        self._pathlength = [0] * self._size        # short[]
        self._idealness_arr = [_INT_MAX] * self._size  # int[]; zero(int) fills MAX_VALUE
        self._visited = [_INT_MAX] * self._size        # int[]
        self._explored = [False] * self._size
        self._perfect_lookup = [False] * self._size
        self._current_queue = _CoordQueue()
        self._frontier_queue = _CoordQueue()
        self._start_search_at = _CoordQueue()
        self._searched_best_idealness = 0
        self._searched_curr_pathlength = 0
        self.board_invalid = True
        self._requires_validation = _CoordQueue()
        self._possible_steps = _CoordQueue()
        # P1 perf: single scratch queue reused per _get_min_distance_from_idealness
        # call. Was allocating a new _CoordQueue (200-int list) on every one of
        # ~135k calls / run.
        self._min_distance_scratch = _CoordQueue()

        # Pre-mark the 4 triangular corners as WALL (PathFinder.java:62-70)
        half = dimension // 2
        for i in range(half):
            for j in range(half):
                if i + j >= half - 1:
                    continue
                self._status[self._index(i, j)] = _WALL
                self._status[self._index(dimension - i - 1, j)] = _WALL
                self._status[self._index(dimension - i - 1, dimension - j - 1)] = _WALL
                self._status[self._index(i, dimension - j - 1)] = _WALL

        # Perfect (edge) tiles (PathFinder.java:71-76)
        for coords in self._perfect_list:
            idx = self._index(coords[0], coords[1])
            self._perfect_lookup[idx] = True
            self._status[idx] = _OPEN
            self._pathlength[idx] = 0
            self._idealness_arr[idx] = _INT_MAX

        # Initial walls (PathFinder.java:77-79)
        for coords in walls:
            self._status[self._index(coords[0], coords[1])] = _WALL

    # ------------------------------------------------------------------ helpers

    def _index(self, x: int, y: int) -> int:
        # PathFinder.java:311-316; OOB returns 0 (maps to origin)
        if x < 0 or y < 0 or y >= self._dimension or x >= self._dimension:
            return 0
        return y * self._dimension + x

    def _zero_bool(self, arr: List[bool]) -> None:
        for i in range(len(arr)):
            arr[i] = False

    def _zero_int(self, arr: List[int]) -> None:
        # PathFinder.java:330-334: fills with Integer.MAX_VALUE
        for i in range(len(arr)):
            arr[i] = _INT_MAX

    def _zero_short(self, arr: List[int]) -> None:
        # PathFinder.java:336-340: fills with 0
        for i in range(len(arr)):
            arr[i] = 0

    def _swap_queues(self) -> None:
        self._current_queue, self._frontier_queue = self._frontier_queue, self._current_queue

    # --------------------------------------------------------- idealness maths

    def _idealness(self, x: int, y: int) -> int:
        """PathFinder.java:198-207."""
        if self._perfect_lookup[self._index(x, y)]:
            return _INT_MAX
        x_id = x if self._direction[0] == 1 else (self._dimension - x - 1)
        y_id = y if self._direction[1] == 1 else (self._dimension - y - 1)
        # assert((xIdealness & 0xFFFF0000) == 0)
        return (y_id << 16) | x_id

    def _extract_idealness_coords(self, idealness: int, into: _CoordQueue) -> None:
        """PathFinder.java:209-221."""
        if idealness == _INT_MAX:
            for coords in self._perfect_list:
                into.push(coords[0], coords[1])
        else:
            x_id = idealness & 0xFFFF
            y_id = (idealness & 0xFFFF0000) >> 16
            x = x_id if self._direction[0] == 1 else (self._dimension - x_id)
            y = y_id if self._direction[1] == 1 else (self._dimension - y_id)
            into.push(x, y)

    def _get_min_distance_from_idealness(self, idealness: int, x: int, y: int) -> int:
        """PathFinder.java:223-241.

        Hot path: ~135k calls / full ranked run. Reuses the pre-allocated
        `_min_distance_scratch` queue (drained up-front) rather than
        allocating a new `_CoordQueue` (200-int list) per call — saves
        ~27M int allocations per run. Semantically identical to the
        Java source: the queue is re-pushed within the loop so the
        post-loop state matches CoordQueue.java's FIFO-rotate behaviour."""
        targets = self._min_distance_scratch
        targets.drain()
        self._extract_idealness_coords(idealness, targets)
        best_distance = _INT_MAX
        num_targets = targets.size()
        for _ in range(num_targets):
            targets.next()
            distance = abs(targets.currX - x) + abs(targets.currY - y)
            if distance < best_distance:
                best_distance = distance
            targets.push(targets.currX, targets.currY)
        return best_distance

    # ---------------------------------------------------- parent-direction mask

    @staticmethod
    def _add_direction_to_mask(current_mask: int, new_direction: int) -> int:
        """PathFinder.java:451-457."""
        i = 0
        while i < 4 and (current_mask & (7 << (i * 3))) != 0:
            i += 1
        # assert i < 4
        return current_mask | (new_direction << (i * 3))

    @staticmethod
    def _get_direction_from_mask(mask: int, index: int) -> int:
        """PathFinder.java:459-461."""
        return (mask & (7 << (index * 3))) >> (index * 3)

    @staticmethod
    def _is_direction_in_map(mask: int, direction: int) -> bool:
        """PathFinder.java:463-469."""
        for i in range(4):
            if PathFinder._get_direction_from_mask(mask, i) == direction:
                return True
        return False

    # ------------------------------------------------------ public: mutate walls

    def put(self, target_x: int, target_y: int) -> None:
        """PathFinder.java:82-119 — wall added."""
        idx = self._index(target_x, target_y)
        if self._status[idx] == _WALL:
            return
        self._status[idx] = _WALL
        for i in range(4):
            nx = _NEIGHBOR_X[i] + target_x
            ny = _NEIGHBOR_Y[i] + target_y
            if self._status[self._index(nx, ny)] != _OPEN:
                continue
            self._current_queue.push(nx, ny)
        self._zero_bool(self._explored)
        while self._current_queue.next():
            cell_x = self._current_queue.currX
            cell_y = self._current_queue.currY
            cell_idx = self._index(cell_x, cell_y)
            # edge tile — skip (pathlength==0)
            if self._status[cell_idx] == _OPEN and self._pathlength[cell_idx] == 0:
                continue
            # Is the cell's pathlength still achievable via any neighbor?
            found = False
            cell_pl = self._pathlength[cell_idx]
            for i in range(4):
                nx = _NEIGHBOR_X[i] + cell_x
                ny = _NEIGHBOR_Y[i] + cell_y
                n_idx = self._index(nx, ny)
                if self._status[n_idx] != _OPEN or self._pathlength[n_idx] != cell_pl - 1:
                    continue
                found = True
                break
            if found:
                continue
            # Invalidate; push OPEN neighbors
            self._status[cell_idx] = _INVALID
            for i in range(4):
                nx = _NEIGHBOR_X[i] + cell_x
                ny = _NEIGHBOR_Y[i] + cell_y
                n_idx = self._index(nx, ny)
                if self._status[n_idx] != _OPEN or self._explored[n_idx]:
                    continue
                self._explored[n_idx] = True
                self._current_queue.push(nx, ny)

    def remove(self, target_x: int, target_y: int) -> None:
        """PathFinder.java:121-141 — wall removed."""
        idx = self._index(target_x, target_y)
        if self._status[idx] != _WALL:
            return
        if self._idealness(target_x, target_y) == _INT_MAX:
            self._status[idx] = _OPEN
            self._pathlength[idx] = 0
            self._idealness_arr[idx] = _INT_MAX
        else:
            self._status[idx] = _INVALID
        if not self.board_invalid:
            self.board_invalid = True
            for i in range(self._dimension):
                for j in range(self._dimension):
                    if self._status[self._index(i, j)] != _OPEN:
                        continue
                    if self._idealness(i, j) == _INT_MAX:
                        continue
                    self._status[self._index(i, j)] = _INVALID

    # ----------------------------------------------------------- validation BFS

    def _handle_parent(self, x: int, y: int) -> None:
        """PathFinder.java:191-196."""
        idx = self._index(x, y)
        if not self._explored[idx]:
            self._explored[idx] = True
            self._frontier_queue.push(x, y)

    def _idealness_search(self, target_x: int, target_y: int) -> None:
        """PathFinder.java:243-309.

        Transcribed from the decompiled control-flow labels block5/6/7.
        The cell being processed falls into one of two top-level cases:
          * status == OPEN → we've reached a cell with a known pathlength.
            If we already found one with better score, maybe just add to
            start_search_at (block6); otherwise replace best (block7).
            No neighbor expansion — OPEN cells terminate the BFS branch.
          * status == INVALID → this is an interior cell; expand neighbors
            (block5), update parent_direction and visited, then do the
            idealness-tiebreak bookkeeping if we haven't yet found an
            OPEN cell (openFound == false).
        """
        self._zero_bool(self._explored)
        self._zero_int(self._visited)
        self._zero_short(self._parent_direction)
        self._start_search_at.drain()
        self._searched_best_idealness = _INT_MIN
        self._searched_curr_pathlength = 0
        self._current_queue.push(target_x, target_y)
        self._explored[self._index(target_x, target_y)] = True
        distance_explored = 0
        current_best = _INT_MAX
        open_found = False
        self._visited[self._index(target_x, target_y)] = 0

        while self._current_queue.size() != 0:
            while self._current_queue.next():
                cell_x = self._current_queue.currX
                cell_y = self._current_queue.currY
                cell_idx = self._index(cell_x, cell_y)
                current_idealness = self._idealness_arr[cell_idx]
                # PathFinder.java:267 — the big prune condition.
                if open_found:
                    if self._status[cell_idx] != _INVALID:
                        # OPEN path prune: > currentBest (strict)
                        if self._pathlength[cell_idx] + distance_explored > current_best:
                            continue
                    else:
                        # INVALID path prune: >= currentBest (non-strict)
                        if self._get_min_distance_from_idealness(
                                self._searched_best_idealness, cell_x, cell_y
                        ) + distance_explored >= current_best:
                            continue

                if self._status[cell_idx] == _OPEN:
                    current_pathlength = self._pathlength[cell_idx]
                    if open_found and distance_explored + current_pathlength >= current_best:
                        # block6: tie (==) adds to start_search_at; > does nothing
                        if distance_explored + current_pathlength == current_best:
                            self._start_search_at.push(cell_x, cell_y)
                    else:
                        # block7: strictly better (or first OPEN found)
                        open_found = True
                        self._searched_best_idealness = current_idealness
                        self._start_search_at.drain()
                        self._start_search_at.push(cell_x, cell_y)
                        self._searched_curr_pathlength = current_pathlength
                        current_best = current_pathlength + distance_explored
                    # OPEN cells never expand neighbors and skip idealness
                    # tiebreak at bottom (open_found is true now).
                    continue

                # status == INVALID: neighbor expansion (block5)
                for i in range(4):
                    nx = _NEIGHBOR_X[i] + cell_x
                    ny = _NEIGHBOR_Y[i] + cell_y
                    n_idx = self._index(nx, ny)
                    if distance_explored < self._visited[n_idx] and self._status[n_idx] != _WALL:
                        self._parent_direction[n_idx] = self._add_direction_to_mask(
                            self._parent_direction[n_idx], _PARENT_DIRECTION[i]
                        )
                        self._visited[n_idx] = distance_explored + 1
                    if self._explored[n_idx] or self._status[n_idx] == _WALL:
                        continue
                    self._frontier_queue.push(nx, ny)
                    self._explored[n_idx] = True

                if open_found:
                    continue

                # idealness tiebreak for INVALID cell when no OPEN found yet
                cell_idealness = self._idealness(cell_x, cell_y)
                if cell_idealness > self._searched_best_idealness:
                    self._start_search_at.drain()
                    self._start_search_at.push(cell_x, cell_y)
                    self._searched_best_idealness = cell_idealness
                    continue
                if cell_idealness != self._searched_best_idealness:
                    continue
                self._start_search_at.push(cell_x, cell_y)

            distance_explored += 1
            self._swap_queues()

    def _validate(self, targets: _CoordQueue) -> None:
        """PathFinder.java:143-189."""
        while targets.next():
            target_x = targets.currX
            target_y = targets.currY
            t_idx = self._index(target_x, target_y)
            if self._status[t_idx] == _OPEN:
                continue
            self.board_invalid = False
            self._idealness_search(target_x, target_y)
            current_path_length = self._searched_curr_pathlength
            self._current_queue.drain()
            self._frontier_queue.drain()
            while self._start_search_at.next():
                self._current_queue.push(self._start_search_at.currX, self._start_search_at.currY)
            self._zero_bool(self._explored)
            # Outer BFS across layers
            while self._current_queue.size() != 0:
                break_outer = False
                while self._current_queue.next():
                    cell_x = self._current_queue.currX
                    cell_y = self._current_queue.currY
                    cell_idx = self._index(cell_x, cell_y)
                    if self._status[cell_idx] == _OPEN and self._pathlength[cell_idx] < current_path_length:
                        continue
                    self._idealness_arr[cell_idx] = self._searched_best_idealness
                    self._status[cell_idx] = _OPEN
                    self._pathlength[cell_idx] = current_path_length
                    if cell_idx == t_idx:
                        self._current_queue.drain()
                        self._frontier_queue.drain()
                        break_outer = True
                        break
                    mask = self._parent_direction[self._index(cell_x, cell_y)]
                    if self._is_direction_in_map(mask, _LEFT):
                        self._handle_parent(cell_x - 1, cell_y)
                    if self._is_direction_in_map(mask, _UP):
                        self._handle_parent(cell_x, cell_y + 1)
                    if self._is_direction_in_map(mask, _RIGHT):
                        self._handle_parent(cell_x + 1, cell_y)
                    if self._is_direction_in_map(mask, _DOWN):
                        self._handle_parent(cell_x, cell_y - 1)
                if break_outer:
                    break
                current_path_length += 1
                self._swap_queues()

    # ----------------------------------------------------- public: query + step

    def get_step(self, unit_x: int, unit_y: int, prev_direction: int) -> Tuple[int, int]:
        """PathFinder.java:345-412."""
        self._requires_validation.drain()
        self._possible_steps.drain()
        self._requires_validation.push(unit_x, unit_y)
        self._possible_steps.push(unit_x, unit_y)
        # Enumerate the 4 neighbors (RIGHT, LEFT, DOWN, UP order)
        for i in range(4):
            nx = _NEIGHBOR_X[i] + unit_x
            ny = _NEIGHBOR_Y[i] + unit_y
            n_idx = self._index(nx, ny)
            if self._status[n_idx] == _INVALID:
                self._requires_validation.push(nx, ny)
            if self._status[n_idx] != _WALL:
                self._possible_steps.push(nx, ny)
        self._validate(self._requires_validation)

        # Filter #1: compact by running min pathlength (front-to-back).
        # Bytecode PathFinder.class:139-141 — `s` is initialized to
        # Integer.MAX_VALUE (CFR output's variable naming was misleading;
        # `istore 7` after `ldc MAX_VALUE` confirms this).
        # Every candidate whose pathlength <= running min re-pushes itself
        # AND updates s down. After this pass, s holds the true min and
        # the queue holds a suffix where each entry's pl <= next-seen min.
        s = _INT_MAX
        num = self._possible_steps.size()
        for _ in range(num):
            self._possible_steps.next()
            step_x = self._possible_steps.currX
            step_y = self._possible_steps.currY
            pl = self._pathlength[self._index(step_x, step_y)]
            if pl > s:
                continue
            s = pl
            self._possible_steps.push(step_x, step_y)

        # Filter #2: keep cells whose pathlength equals the final s.
        num = self._possible_steps.size()
        for _ in range(num):
            self._possible_steps.next()
            step_x = self._possible_steps.currX
            step_y = self._possible_steps.currY
            if self._pathlength[self._index(step_x, step_y)] != s:
                continue
            self._possible_steps.push(step_x, step_y)

        # Filter #3: zigzag (only if size > 1)
        if self._possible_steps.size() > 1:
            num = self._possible_steps.size()
            for _ in range(num):
                self._possible_steps.next()
                step_x = self._possible_steps.currX
                step_y = self._possible_steps.currY
                # Drop if on the same axis as previous move
                if (prev_direction == _VERTICAL and step_x == unit_x) or \
                   (prev_direction == _HORIZONTAL and step_y == unit_y) or \
                   (prev_direction == _SPAWNED and step_y == unit_y):
                    continue
                self._possible_steps.push(step_x, step_y)

        # Filter #4: direction-preferred (only if size > 1)
        if self._possible_steps.size() > 1:
            num = self._possible_steps.size()
            for _ in range(num):
                self._possible_steps.next()
                step_x = self._possible_steps.currX
                step_y = self._possible_steps.currY
                if self._direction[0] + unit_x != step_x and \
                   self._direction[1] + unit_y != step_y:
                    continue
                self._possible_steps.push(step_x, step_y)

        self._possible_steps.next()
        return (self._possible_steps.currX, self._possible_steps.currY)


# ---------------------------------------------------------- exports for sim use

# Directions per engine target-edge convention (MapBounds.java:24-43)
EDGE_DIRECTION = {
    0: (1, 1),    # TOP_RIGHT
    1: (-1, 1),   # TOP_LEFT
    2: (-1, -1),  # BOTTOM_LEFT
    3: (1, -1),   # BOTTOM_RIGHT
}


def make_pathfinders(
    dimension: int,
    walls: Sequence[Tuple[int, int]],
    edge_to_perfects: dict,
) -> dict:
    """Factory for the 4 per-edge PathFinders.

    `edge_to_perfects[edge_id]` is the list of perfect (edge) tiles for
    that edge — caller passes the result of `sim.map.edge_tiles(e)`.
    """
    return {
        e: PathFinder(dimension, EDGE_DIRECTION[e], walls, edge_to_perfects[e])
        for e in (0, 1, 2, 3)
    }
