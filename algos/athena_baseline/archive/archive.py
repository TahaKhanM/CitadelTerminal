"""MAPElitesArchive — Phase 6 milestone I3.

A 2-D grid container that keeps the highest-fitness Genome per cell.
The grid dimensions are configurable (default 8x8 = 64 cells).

API contract:
  - ``add(genome, fitness, behavior)``: place into the cell determined by
    ``behavior_to_cell(behavior, grid_w, grid_h)``. Replace incumbent
    only if ``fitness`` strictly exceeds the existing cell's fitness.
    Returns ``True`` if the cell was updated.
  - ``sample(k, rng)``: return up to k random cell-occupants. If the
    archive has fewer than k filled cells, returns all occupants.
  - ``best_overall()``: return the Genome with highest stored fitness
    across all cells (or ``None`` if empty).
  - ``coverage()``: tuple ``(filled_cells, total_cells)``.
  - ``serialize() / deserialize(path)`` — JSON round-trip.

The archive does NOT own the fitness / behavior computation — callers
pass values explicitly. This keeps the container test-friendly and
sim_rs-independent.
"""
from __future__ import annotations

import json
import random
from dataclasses import asdict, dataclass, field
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

from .behavior import behavior_to_cell
from .genome import Genome


@dataclass
class _Cell:
    genome: Genome
    fitness: float
    behavior: Tuple[float, float]
    cell_idx: Tuple[int, int]


class MAPElitesArchive:
    """In-memory 2-D MAP-Elites grid."""

    def __init__(self, grid_w: int = 8, grid_h: int = 8) -> None:
        if grid_w <= 0 or grid_h <= 0:
            raise ValueError("grid dimensions must be positive")
        self.grid_w = int(grid_w)
        self.grid_h = int(grid_h)
        # Map cell (col, row) -> _Cell
        self._cells: Dict[Tuple[int, int], _Cell] = {}

    # ------------------------------------------------------------------
    # Cell ops
    # ------------------------------------------------------------------

    def add(
        self,
        genome: Genome,
        fitness: float,
        behavior: Tuple[float, float],
    ) -> bool:
        cell = behavior_to_cell(
            float(behavior[0]), float(behavior[1]),
            grid_w=self.grid_w, grid_h=self.grid_h,
        )
        prev = self._cells.get(cell)
        if prev is None or fitness > prev.fitness:
            self._cells[cell] = _Cell(
                genome=genome,
                fitness=float(fitness),
                behavior=(float(behavior[0]), float(behavior[1])),
                cell_idx=cell,
            )
            return True
        return False

    def sample(
        self,
        k: int,
        rng: Optional[random.Random] = None,
    ) -> List[Genome]:
        if k <= 0 or not self._cells:
            return []
        rng = rng or random.Random()
        cells = list(self._cells.values())
        if len(cells) <= k:
            return [c.genome for c in cells]
        return [c.genome for c in rng.sample(cells, k)]

    def sample_with_fitness(
        self,
        k: int,
        rng: Optional[random.Random] = None,
    ) -> List[Tuple[Genome, float]]:
        if k <= 0 or not self._cells:
            return []
        rng = rng or random.Random()
        cells = list(self._cells.values())
        if len(cells) <= k:
            return [(c.genome, c.fitness) for c in cells]
        chosen = rng.sample(cells, k)
        return [(c.genome, c.fitness) for c in chosen]

    def best_overall(self) -> Optional[Genome]:
        if not self._cells:
            return None
        best_cell = max(self._cells.values(), key=lambda c: c.fitness)
        return best_cell.genome

    def best_fitness(self) -> Optional[float]:
        if not self._cells:
            return None
        return max(c.fitness for c in self._cells.values())

    def coverage(self) -> Tuple[int, int]:
        return (len(self._cells), self.grid_w * self.grid_h)

    def __len__(self) -> int:
        return len(self._cells)

    def __contains__(self, cell: Tuple[int, int]) -> bool:
        return cell in self._cells

    # ------------------------------------------------------------------
    # Serialization
    # ------------------------------------------------------------------

    def to_dict(self) -> Dict[str, Any]:
        return {
            "version": 1,
            "grid_w": self.grid_w,
            "grid_h": self.grid_h,
            "cells": [
                {
                    "cell": list(c.cell_idx),
                    "fitness": float(c.fitness),
                    "behavior": list(c.behavior),
                    "genome": c.genome.to_dict(),
                }
                for c in self._cells.values()
            ],
        }

    def serialize(self, indent: int = 2) -> str:
        return json.dumps(self.to_dict(), indent=indent, sort_keys=True)

    def save(self, path: str | Path) -> None:
        p = Path(path)
        p.parent.mkdir(parents=True, exist_ok=True)
        p.write_text(self.serialize())

    @classmethod
    def from_dict(cls, d: Dict[str, Any]) -> "MAPElitesArchive":
        gw = int(d.get("grid_w", 8))
        gh = int(d.get("grid_h", 8))
        a = cls(grid_w=gw, grid_h=gh)
        for c in d.get("cells", []):
            cell = (int(c["cell"][0]), int(c["cell"][1]))
            beh = (float(c["behavior"][0]), float(c["behavior"][1]))
            g = Genome.from_dict(c["genome"])
            a._cells[cell] = _Cell(
                genome=g,
                fitness=float(c["fitness"]),
                behavior=beh,
                cell_idx=cell,
            )
        return a

    @classmethod
    def deserialize(cls, path: str | Path) -> "MAPElitesArchive":
        p = Path(path)
        d = json.loads(p.read_text())
        return cls.from_dict(d)


__all__ = ["MAPElitesArchive"]
