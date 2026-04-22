# gamelib API Reference

This is a hands-on reference for the Python starter-kit `gamelib/` module. It lives under `C1GamesStarterKit-master/python-algo/gamelib/` — identical copies are bundled with every new algo you create from the template.

## Top-level import

```python
import gamelib
from gamelib import AlgoCore, GameState, GameMap, GameUnit, debug_write
```

## `AlgoCore` — the main loop

Subclass `AlgoCore` (or `gamelib.AlgoCore`) and override:

```python
class AlgoStrategy(gamelib.AlgoCore):
    def on_game_start(self, config: dict) -> None:
        """Called once at game start with server-delivered config JSON.
           Read unit stats from `config["unitInformation"]` here — do NOT hardcode."""

    def on_turn(self, turn_state: str) -> None:
        """Called each turn. turn_state is the raw JSON string.
           Wrap it: game_state = gamelib.GameState(self.config, turn_state)
           Build your moves, then call game_state.submit_turn()."""

    def on_action_frame(self, turn_string: str) -> None:
        """Optional. Called for EVERY frame of the action phase (can be 100s per turn).
           Use for learning from events (e.g., where enemy scored). Keep this FAST."""
```

Then at module bottom:

```python
if __name__ == "__main__":
    AlgoStrategy().start()
```

`start()` reads stdin forever, dispatches to the overrides, and exits when the engine sends the end-game message.

## `GameState` — per-turn wrapper

Construct with `GameState(self.config, turn_string)` at the top of each `on_turn`. Key attributes:

| Attribute | Meaning |
|---|---|
| `game_state.config` | Original server config (dict) |
| `game_state.game_map` | The current `GameMap` object (with units populated) |
| `game_state.turn_number` | Integer turn, 0-indexed |
| `game_state.my_health` / `enemy_health` | Float HP values |
| `game_state.my_time` / `enemy_time` | Last-turn compute time in ms |
| `game_state.ARENA_SIZE` | 28 |
| `game_state.HALF_ARENA` | 14 |
| `game_state.MP` | 1 (resource index for Mobile Points) |
| `game_state.SP` | 0 (resource index for Structure Points) |

### Resource queries

```python
game_state.get_resource(MP)                    # your MP
game_state.get_resource(SP, player_index=1)    # opponent's SP
game_state.get_resources()                     # [your_SP, your_MP]
game_state.get_resources(1)                    # [opp_SP, opp_MP]
game_state.number_affordable(SCOUT)            # int count you can afford right now
game_state.type_cost(TURRET)                   # [SP_cost, MP_cost] = [2, 0]
game_state.type_cost(TURRET, upgrade=True)     # upgrade cost delta = [4, 0]
game_state.project_future_MP(turns_in_future=3) # predicts future MP with decay + income
```

### Deployment

```python
# stationary units: SP cost, anywhere in your half on empty tiles
game_state.attempt_spawn(WALL, [[8, 12], [19, 12]])       # returns int spawned count
game_state.attempt_spawn(TURRET, [13, 11])                # single location also OK
game_state.attempt_spawn(SUPPORT, [13, 3], num=1)         # num only matters for mobiles

# mobile units: MP cost, edge tiles only, can stack
game_state.attempt_spawn(SCOUT, [14, 0], num=1000)        # spawns as many as you can afford

# upgrading & removing
game_state.attempt_upgrade([[0, 13], [27, 13]])           # upgrades if possible, returns count
game_state.attempt_remove([[13, 11]])                     # marks for removal, refund next turn

# checking before spawning
game_state.can_spawn(DEMOLISHER, [13, 0])                 # bool — full validity check
game_state.contains_stationary_unit([13, 11])             # returns the GameUnit or False
```

⚠️ `attempt_spawn(num=1000)` will spawn as many as resources allow at the given location. This is the idiomatic "spawn everything I can" pattern for rush turns.

### Pathing & simulation

```python
path = game_state.find_path_to_edge([14, 0])             # list of [x,y] tiles
edge = game_state.get_target_edge([14, 0])                # game_map.TOP_LEFT (an int)
attackers = game_state.get_attackers([13, 14], 0)         # list of enemy turrets that could hit YOUR unit there
target = game_state.get_target(some_GameUnit)             # who some_GameUnit would shoot this frame
```

### Committing the turn

```python
game_state.submit_turn()   # MUST call, or the algo hangs and you take timeout damage
```

Also useful:

```python
game_state.suppress_warnings(True)   # silences the stderr spam from attempted invalid spawns
```

## `GameMap` — spatial queries

```python
gm = game_state.game_map

gm[x, y]                                   # list of GameUnits at that tile (empty list if none)
for loc in gm: ...                          # iterate every playable tile
gm.in_arena_bounds([x, y])                  # bool
gm.get_edge_locations(gm.BOTTOM_LEFT)       # list of [x,y] on that edge
gm.get_edges()                              # [top_right, top_left, bottom_left, bottom_right]
gm.distance_between_locations(a, b)         # Euclidean float
gm.get_locations_in_range([x, y], r)        # tiles within Euclidean radius r (diamond-bounded)

# Mutation (for hypotheticals only — do not call on the live state):
gm.add_unit(TURRET, [13, 11], player_index=0)
gm.remove_unit([13, 11])
```

Edge constants:

```
gm.TOP_RIGHT   = 0
gm.TOP_LEFT    = 1
gm.BOTTOM_LEFT = 2
gm.BOTTOM_RIGHT = 3
```

## `GameUnit` — per-unit info

```python
u = game_state.game_map[13, 11][0]          # example: first unit at that tile

u.unit_type         # "DF" etc.
u.player_index      # 0 (you) or 1 (enemy)
u.x, u.y            # location
u.health            # current HP (can exceed max_health if shielded)
u.max_health        # base starting HP
u.stationary        # bool — True for structures
u.speed             # frames-per-tile inverse (Scout 1.0, Demolisher 0.5, Interceptor 0.25)
u.damage_i          # attackDamageWalker (vs mobile)
u.damage_f          # attackDamageTower  (vs structures)
u.attackRange       # float
u.shieldRange       # float (Supports only)
u.shieldPerUnit     # float
u.shieldBonusPerY   # float (upgraded Support)
u.cost              # [SP, MP]
u.upgraded          # bool
u.pending_removal   # bool — marked for removal this turn
```

`u.upgrade()` applies the upgrade stats locally — useful for hypotheticals on a copied map. Doesn't send a real upgrade command.

## `gamelib.util.debug_write(*msg)`

Prints to stderr; the engine captures stderr and surfaces it in replays/playground. Use it generously while developing:

```python
gamelib.debug_write("Turn", game_state.turn_number, "MP:", game_state.get_resource(MP))
```

## Action-frame parsing snippet

```python
def on_action_frame(self, turn_string):
    state = json.loads(turn_string)
    events = state["events"]

    for breach in events["breach"]:
        loc, damage, *_ , unit_owner = breach
        if unit_owner == 2:   # enemy scored on you
            self.scored_on_locations.append(loc)

    for death in events["death"]:
        loc, unit_type, _, _, player = death
        # learn from casualties
```

## Common pitfalls

1. **Forgetting `submit_turn()`** — turn hangs and you take 1 dmg/sec timeout.
2. **Hardcoding unit stats** — breaks on any patch. Always reach into `self.config`.
3. **Spawning without checking `can_spawn`** — silently no-ops and wastes your logic's assumption that it worked. Always check the return count of `attempt_spawn`.
4. **Walls blocking your own edges** — placing a Wall on `[14, 0]` means you can't spawn mobiles there next turn. Keep your edge spawn tiles open.
5. **Mutating `game_state.game_map` directly** — desyncs your view from reality. Copy first (`copy.deepcopy(game_state.game_map)`) for simulations.
6. **Using `player_index == 0 / 1`** convention when parsing raw JSON in `on_action_frame` — raw frames use 1 / 2 instead.
7. **Walls before Turrets**: spawn Turrets FIRST, then Walls in front. Otherwise a Wall you place at [x, y+1] before the Turret at [x, y] still succeeds (they don't overlap), but if you're using `attempt_upgrade` on the turret and ran out of SP on walls, your upgrade silently fails.
