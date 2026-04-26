"""Offense template loader + validator.

A template encodes a discrete spawn pattern: which mobile units, at
which spawn-edge tiles, in what counts. A live offense plan is built
by binding a template to a (side, MP-cap) at planning time.

JSON schema (one file per template under ``offense/templates/``)::

    {
      "name": "scout_rush_left",
      "description": "...optional...",
      "side": "BL" | "BR" | "CENTER" | "BOTH",   // intent
      "min_mp": int,                             // required MP threshold
      "spawns": [
        {
          "unit": "SCOUT" | "DEMOLISHER" | "INTERCEPTOR",
          "loc":  [int, int],
          "count": int
        },
        ...
      ]
    }

Validation rules:
    - ``unit`` ∈ {SCOUT, DEMOLISHER, INTERCEPTOR}.
    - ``loc`` is a tile on a *bottom* spawn edge (BL or BR diagonal).
      The 28 valid bottom-spawn tiles are
        BL: (0,13), (1,12), ..., (13,0)
        BR: (14,0), (15,1), ..., (27,13)
    - ``count`` >= 1.
    - ``min_mp`` >= 0.

We do NOT validate cost-vs-min_mp at load time — it depends on the
runtime config and is checked at candidate-generation time.
"""
from __future__ import annotations

import json
from dataclasses import dataclass, field
from pathlib import Path
from typing import Iterable, List, Sequence, Tuple


VALID_UNITS = ("SCOUT", "DEMOLISHER", "INTERCEPTOR")
VALID_SIDES = ("BL", "BR", "CENTER", "BOTH")


def _bottom_spawn_tiles() -> set:
    """Return the 28 tiles on the two bottom deploy edges."""
    bl = {(i, 13 - i) for i in range(14)}      # (0,13)..(13,0)
    br = {(14 + i, i) for i in range(14)}      # (14,0)..(27,13)
    return bl | br


SPAWN_EDGE_TILES = _bottom_spawn_tiles()


@dataclass(frozen=True)
class SpawnGroup:
    unit: str
    loc: Tuple[int, int]
    count: int


@dataclass(frozen=True)
class OffenseTemplate:
    name: str
    description: str
    side: str
    min_mp: int
    spawns: Tuple[SpawnGroup, ...]
    source_path: str = ""

    @property
    def n_units(self) -> int:
        return sum(g.count for g in self.spawns)


# ---------------------------------------------------------------------------
# Loaders
# ---------------------------------------------------------------------------

def validate_template(raw: dict, source_path: str = "<inline>") -> None:
    """Raise ValueError if the template dict is malformed."""
    for key in ("name", "side", "min_mp", "spawns"):
        if key not in raw:
            raise ValueError(f"{source_path}: missing required key '{key}'")
    if not isinstance(raw["name"], str) or not raw["name"]:
        raise ValueError(f"{source_path}: 'name' must be non-empty string")
    if raw["side"] not in VALID_SIDES:
        raise ValueError(
            f"{source_path}: 'side'={raw['side']!r} not in {VALID_SIDES}"
        )
    if not isinstance(raw["min_mp"], (int, float)) or raw["min_mp"] < 0:
        raise ValueError(f"{source_path}: 'min_mp' must be a non-negative number")
    spawns = raw["spawns"]
    if not isinstance(spawns, list) or not spawns:
        raise ValueError(f"{source_path}: 'spawns' must be a non-empty list")
    for i, sp in enumerate(spawns):
        if not isinstance(sp, dict):
            raise ValueError(f"{source_path}: spawns[{i}] must be a dict")
        for key in ("unit", "loc", "count"):
            if key not in sp:
                raise ValueError(
                    f"{source_path}: spawns[{i}] missing key '{key}'"
                )
        if sp["unit"] not in VALID_UNITS:
            raise ValueError(
                f"{source_path}: spawns[{i}].unit={sp['unit']!r} "
                f"not in {VALID_UNITS}"
            )
        loc = sp["loc"]
        if (not isinstance(loc, (list, tuple))
                or len(loc) != 2
                or not all(isinstance(v, int) for v in loc)):
            raise ValueError(
                f"{source_path}: spawns[{i}].loc must be [int,int]"
            )
        if tuple(loc) not in SPAWN_EDGE_TILES:
            raise ValueError(
                f"{source_path}: spawns[{i}].loc={tuple(loc)} is "
                f"not a bottom-edge spawn tile"
            )
        if not isinstance(sp["count"], int) or sp["count"] < 1:
            raise ValueError(
                f"{source_path}: spawns[{i}].count must be int >= 1"
            )


def load_template(path: Path) -> OffenseTemplate:
    """Read & validate a single template file."""
    path = Path(path)
    with path.open("r") as fh:
        raw = json.load(fh)
    validate_template(raw, source_path=str(path))
    spawns = tuple(
        SpawnGroup(unit=str(sp["unit"]),
                   loc=(int(sp["loc"][0]), int(sp["loc"][1])),
                   count=int(sp["count"]))
        for sp in raw["spawns"]
    )
    return OffenseTemplate(
        name=str(raw["name"]),
        description=str(raw.get("description", "")),
        side=str(raw["side"]),
        min_mp=int(raw["min_mp"]),
        spawns=spawns,
        source_path=str(path),
    )


def load_all_templates(directory: Path | None = None) -> List[OffenseTemplate]:
    """Load every ``*.json`` template under ``directory`` (default: package's
    ``templates/`` subdir).
    """
    if directory is None:
        directory = Path(__file__).resolve().parent / "templates"
    directory = Path(directory)
    paths = sorted(directory.glob("*.json"))
    return [load_template(p) for p in paths]


__all__ = [
    "VALID_UNITS",
    "VALID_SIDES",
    "SPAWN_EDGE_TILES",
    "SpawnGroup",
    "OffenseTemplate",
    "validate_template",
    "load_template",
    "load_all_templates",
]
