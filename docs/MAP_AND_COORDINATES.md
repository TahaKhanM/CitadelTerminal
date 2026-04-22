# Map & Coordinates

## Grid layout

The arena is a **28×28 grid** (`x, y ∈ [0, 27]`). Only a **diamond-shaped subset of tiles** is actually playable; the four square corners are off-limits.

```
                       y=27 ┌──────────────┐
                            │    (top)     │
                            │    enemy     │
                            │   territory  │
                       y=14 ├──────────────┤
                            │   (bottom)   │
                            │     your     │
                            │   territory  │
                        y=0 └──────────────┘
                            x=0          x=27
```

## Diamond shape

A tile `[x, y]` is inside the playable area iff (from `game_map.py:in_arena_bounds`):

- **Bottom half** (y < 14): `x ∈ [13 − y, 14 + y]`. Row 0 has exactly 2 tiles (`x ∈ {13, 14}`); row 13 has 28 tiles (`x ∈ [0, 27]`).
- **Top half** (y ≥ 14): `x ∈ [y − 14, 41 − y]`. Row 14 has 28 tiles (`x ∈ [0, 27]`); row 27 has 2 tiles (`x ∈ {13, 14}`).

So the playable region widens from 2 tiles at each pointed end to a full 28-wide band in the middle.

## Your territory

Your half is **y < 14** (rows 0 through 13). You can spawn structures on any unoccupied tile here. You CANNOT spawn structures in the top half (enemy territory).

## Edges (mobile-unit spawn zones)

There are four corner-edge diagonals, one per quadrant. You can only spawn mobile units on the two BOTTOM edges.

```python
game_map.BOTTOM_LEFT  = 2  # tiles [x, y] where y + x == 13, y ∈ [0, 13]
game_map.BOTTOM_RIGHT = 3  # tiles [x, y] where x - y == 14, y ∈ [0, 13]
game_map.TOP_LEFT     = 1  # opponent's edge
game_map.TOP_RIGHT    = 0  # opponent's edge
```

Bottom-left edge coordinates: `[13,0], [12,1], [11,2], …, [0,13]`
Bottom-right edge coordinates: `[14,0], [15,1], [16,2], …, [27,13]`

Every mobile unit spawned on BOTTOM_LEFT targets the opposite edge (TOP_RIGHT); every mobile unit spawned on BOTTOM_RIGHT targets TOP_LEFT. See `GameState.get_target_edge()`.

## Symmetry and the "always code as bottom" rule

When the engine runs your algo on the top half, it **rotates the input** so it still looks like you're on the bottom. You NEVER need to write "am I top or bottom?" logic — always treat yourself as the bottom player with y < 14 as home.

## Distance

The game uses **Euclidean distance** for unit ranges (including attack, shielding, and self-destruct). Not Manhattan distance. `GameMap.distance_between_locations([x1,y1], [x2,y2]) = sqrt((x1-x2)² + (y1-y2)²)`.

Movement is orthogonal only (step cost = Manhattan for BFS), but target *eligibility* is Euclidean.

## Common reference points

| Location | Description |
|---|---|
| `[13, 13]`, `[14, 13]` | Center back row of your territory — highest Y on your side; optimal for upgraded Supports (shield bonus = 1 + 0.3·13 = 4.9 per unit) |
| `[0, 13]`, `[27, 13]` | Your back corners — classic Turret positions to deny enemy scoring |
| `[13, 0]`, `[14, 0]` | Your "closest to enemy" spawn tiles — shortest path to opposing edge |
| `[13, 11]`, `[14, 11]` | Dead-center mid-row positions — popular Turret spots that threaten a wide swath of incoming paths |
| `[13, 14]`, `[14, 14]` | First enemy row (opponent's "closest" spawn) — useful for attackers to aim at |

## Navigation helpers (in starter gamelib)

```python
game_map.get_edge_locations(game_map.BOTTOM_LEFT)   # → list of [x,y]
game_map.get_edges()                                 # all four edges
game_map.in_arena_bounds([x,y])                      # bool
game_map.get_locations_in_range([x,y], radius)       # list of diamond-bounded tiles within Euclidean r
game_map.distance_between_locations(a, b)            # float (Euclidean)
game_state.find_path_to_edge([x,y], target_edge)     # full path the unit would take given current map
game_state.get_target_edge([x,y])                    # which edge a mobile at [x,y] would aim for
game_state.get_attackers([x,y], player_index)        # which enemy turrets could shoot this tile
game_state.get_target(attacking_unit)                # who a given unit would target this frame
```

Iteration pattern over every playable tile:

```python
for location in game_state.game_map:   # GameMap is iterable
    x, y = location
    units = game_state.game_map[x, y]  # list of GameUnit on that tile
```

## Quick map diagnostic snippet

Drop this in `on_turn` during dev to see where your algo is leaving gaps:

```python
for y in range(27, -1, -1):
    row = ""
    for x in range(28):
        if not game_state.game_map.in_arena_bounds([x, y]):
            row += "  "
            continue
        stack = game_state.game_map[x, y]
        if not stack:
            row += " ."
        else:
            u = stack[0]
            mark = {"FF": "W", "EF": "S", "DF": "T"}.get(u.unit_type, "?")
            row += f" {mark.upper() if u.upgraded else mark.lower()}"
    gamelib.debug_write(row)
```
