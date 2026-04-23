# Correlation1 Terminal

[Original Repository](https://github.com/correlation-one/C1GamesStarterKit/).

## Command

See main site.
I'm using Linux so these commands would be for me.
Use `./scripts/test_algo_linux python-algo` to test it works.
Use `./scripts/zipalgo_linux python-algo name_of_strat.zip` to upload.
Testing the code requires the strategy to be named `algo_strategy.py`

The one with the highest elo ended up being `algo_strategy_wang5.py` even though it didn't include all of our desired features

# Starter Algo

## Game State Links
https://docs.c1games.com/
https://www.twitch.tv/videos/483165853


## File Overview

```
python-algo
 │
 ├──gamelib
 │   ├──__init__.py
 │   ├──algocore.py
 │   ├──game_map.py
 │   ├──game_state.py
 │   ├──navigation.py
 │   ├──tests.py
 │   ├──unit.py
 │   └──util.py
 │
 ├──algo_strategy.py
 ├──documentation
 ├──README.md
 ├──run.ps1
 └──run.sh
```

### Creating an Algo

To create an algo, simply rename or copy an `algo_strategy_something.py` file to `algo_strategy.py` and modify it.

### `algo_strategy.py`

This file contains the `AlgoStrategy` class which you should modify to implement
your strategy.

At a minimum you must implement the `on_turn` method which handles responding to
the game state for each turn. Refer to the `starter_strategy` method for inspiration.

If your algo requires initialization then you should also implement the
`on_game_start` method and do any inital setup there.
