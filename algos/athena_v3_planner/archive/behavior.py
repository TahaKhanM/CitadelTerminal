"""Behavior descriptors — Phase 6 milestone I2.

MAP-Elites bins each genome by a low-dim behavioral signature so that
the archive preserves diversity (a high-fitness genome doesn't crowd out
a behaviorally-distinct one). We use 2-D descriptors so the archive is
visualizable as a heatmap.

Axes:
  - **mean_mp_at_attack** (proxy for hoarding behavior). Bins:
      0-5, 5-10, 10-15, 15-20, 20+
  - **defense_density** (count of structures placed by turn 30). Bins:
      0-15, 15-25, 25-35, 35+

We default to an 8x8 grid by re-binning these 5x4 raw bins onto an 8x8
index. For an 8x8 grid both axes get 8 bins; we map continuous values
through ``BC_BINS_MP_AT_ATTACK_8`` and ``BC_BINS_DEF_DENSITY_8`` (uniform
within sensible playing ranges).

The function ``compute_behavior_descriptors`` accepts the aggregated
sim-eval results from ``fitness.evaluate`` (a list of per-match dicts
with summary stats) and returns the (bc1, bc2) raw values. Bin
assignment lives in ``archive.MAPElitesArchive``.
"""
from __future__ import annotations

from typing import Any, Dict, List, Sequence, Tuple


# 8-way bin edges chosen so common values map to interior cells. The
# upper bound is inclusive at the rightmost cell.
BC_BINS_MP_AT_ATTACK_8: Tuple[float, ...] = (
    0.0, 3.0, 6.0, 9.0, 12.0, 15.0, 20.0, 30.0, 1e9,
)
BC_BINS_DEF_DENSITY_8: Tuple[float, ...] = (
    0.0, 8.0, 14.0, 20.0, 26.0, 32.0, 38.0, 50.0, 1e9,
)


def _digitize(value: float, edges: Sequence[float]) -> int:
    """Return the index i such that edges[i] <= value < edges[i+1].

    ``edges`` must be sorted. Out-of-range values clamp to the first /
    last interior index.
    """
    i = 0
    n = len(edges) - 1  # number of bins
    if n <= 0:
        return 0
    while i + 1 < n and value >= edges[i + 1]:
        i += 1
    return max(0, min(i, n - 1))


def compute_behavior_descriptors(
    sim_results: Sequence[Dict[str, Any]],
) -> Tuple[float, float]:
    """Aggregate per-match sim summaries into the 2-D behavior signature.

    ``sim_results`` is a list of dicts, each with keys::

        {
          "mean_mp_at_attack":  float,   # avg MP held at start of attack turns
          "defense_density":    int,     # structures placed by turn 30
          ...                            # other stats ignored here
        }

    Returns ``(bc1, bc2)`` averaged across matches. If ``sim_results`` is
    empty we return ``(0.0, 0.0)`` — caller's responsibility to handle.
    """
    if not sim_results:
        return (0.0, 0.0)
    n = float(len(sim_results))
    bc1 = sum(float(r.get("mean_mp_at_attack", 0.0)) for r in sim_results) / n
    bc2 = sum(float(r.get("defense_density", 0.0)) for r in sim_results) / n
    return (bc1, bc2)


def behavior_to_cell(
    bc1: float,
    bc2: float,
    grid_w: int = 8,
    grid_h: int = 8,
) -> Tuple[int, int]:
    """Map raw (bc1, bc2) to a (col, row) cell index in [0, grid_w) x [0, grid_h)."""
    # We use the 8-bin edges by default. For non-default grid sizes we
    # uniformly redistribute over the same value ranges (rough but
    # workable; refining BC space is a Phase 6 followup).
    if grid_w == 8:
        col = _digitize(bc1, BC_BINS_MP_AT_ATTACK_8)
    else:
        # uniform bins between [0, 30]
        col = max(0, min(int(bc1 / 30.0 * grid_w), grid_w - 1))
    if grid_h == 8:
        row = _digitize(bc2, BC_BINS_DEF_DENSITY_8)
    else:
        row = max(0, min(int(bc2 / 50.0 * grid_h), grid_h - 1))
    return (col, row)


__all__ = [
    "BC_BINS_DEF_DENSITY_8",
    "BC_BINS_MP_AT_ATTACK_8",
    "behavior_to_cell",
    "compute_behavior_descriptors",
]
