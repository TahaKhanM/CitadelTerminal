"""Archetype enum + loader.

Single source of truth: `archetypes.json` in the same directory.
Code that needs the enum imports `ARCHETYPES` (a tuple) and
`DEFAULT_ARCHETYPE` (a str) from this module.

Adding a new archetype = edit `archetypes.json` only. This module
re-reads it at import time.
"""
from __future__ import annotations

import json
from pathlib import Path
from typing import Tuple

_PATH = Path(__file__).resolve().parent / "archetypes.json"
with _PATH.open("r") as _f:
    _DATA = json.load(_f)

ARCHETYPES: Tuple[str, ...] = tuple(_DATA["archetypes"])
DEFAULT_ARCHETYPE: str = _DATA["default"]
ARCHETYPES_VERSION: int = int(_DATA.get("version", 1))

assert DEFAULT_ARCHETYPE in ARCHETYPES, (
    f"default archetype {DEFAULT_ARCHETYPE!r} missing from {ARCHETYPES}"
)


def archetype_index(name: str) -> int:
    """Return the index of `name` in the canonical ARCHETYPES tuple.

    Raises ``ValueError`` if `name` is not a known archetype. Use
    ``DEFAULT_ARCHETYPE`` as a fallback for unknown opponents.
    """
    try:
        return ARCHETYPES.index(name)
    except ValueError as exc:
        raise ValueError(
            f"Unknown archetype {name!r}; known: {ARCHETYPES}"
        ) from exc


__all__ = [
    "ARCHETYPES",
    "DEFAULT_ARCHETYPE",
    "ARCHETYPES_VERSION",
    "archetype_index",
]
