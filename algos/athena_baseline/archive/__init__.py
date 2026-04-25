"""Athena MAP-Elites archive (Phase 6).

Components:
  - ``genome.py``       — Genome dataclass + encode/decode + mutation helpers.
  - ``behavior.py``     — Compute behavior descriptors for binning.
  - ``archive.py``      — MAPElitesArchive grid container.
  - ``fitness.py``      — Sim-evaluated fitness over the genome.
  - ``map_elites.py``   — Top-level run_map_elites driver.

The archive is consumed by ``planner.offense.beam_search`` as a source
of warm-start candidates (see ``planner.offense.generate_candidates``
for integration).
"""

from .genome import (
    DEFENSE_ARCHETYPES,
    DOMINANT_TEMPLATE_NAMES,
    Genome,
    decode_genome,
    encode_genome,
    mutate_genome,
    random_genome,
)
from .behavior import compute_behavior_descriptors
from .archive import MAPElitesArchive

__all__ = [
    "DEFENSE_ARCHETYPES",
    "DOMINANT_TEMPLATE_NAMES",
    "Genome",
    "MAPElitesArchive",
    "compute_behavior_descriptors",
    "decode_genome",
    "encode_genome",
    "mutate_genome",
    "random_genome",
]
