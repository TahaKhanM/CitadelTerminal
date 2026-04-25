"""MAP-Elites genome — Phase 6 milestone I1.

A Genome is a fixed-length parameter vector describing one Athena
strategy variant. It spans:

  - **Defense axis** (3 dims):
      * archetype_idx (categorical, 0..3 — v_funnel / two_layer_keep /
        spread_line / v13_inspired)
      * turret_density_mult (real, 0.5..1.5) — multiplies the
        probabilistic_placement turret-y biasing
      * support_weight (real, 10..50) — passed to
        EconomyArbiter.probabilistic_support_weight

  - **Offense axis** (4 dims):
      * dominant_template_idx (categorical, 0..N-1 over the 17 JSON
        templates currently in offense/templates/)
      * mp_hoard_threshold (real, 0.3..1.0) — fraction of running MP
        cap below which the planner prefers `hoard` over committing
      * spawn_side_bias (real, -1..+1) — negative = left preference,
        positive = right preference; multiplies template-side affinity
      * demo_scout_ratio (real, 0..1) — preference for Demolisher mix
        over Scout mix when both fit MP budget

  - **Utility weights** (4 dims):
      * alpha (real, 0.5..2.0) — HP-taken penalty
      * beta (real, 0.0..1.5) — MP-spent penalty
      * gamma (real, 0.0..0.5) — structures-destroyed bonus
      * delta (real, 0.0..0.5) — SP-gained bonus

Total: 11 dimensions. Categorical dims mutate by random replacement
(probability ``CATEGORICAL_MUT_RATE`` per dim per mutation step); real
dims mutate by Gaussian noise with ``REAL_SIGMA_FRAC`` of their range.

The dataclass itself is immutable (frozen=True). Mutation returns a new
Genome.

This module deliberately does NOT depend on sim_rs or gamelib so it
can be unit-tested headless.
"""
from __future__ import annotations

import math
import random
from dataclasses import asdict, dataclass, replace
from pathlib import Path
from typing import Any, Dict, List, Sequence, Tuple


# ---------------------------------------------------------------------------
# Categorical domains
# ---------------------------------------------------------------------------

DEFENSE_ARCHETYPES: Tuple[str, ...] = (
    "v_funnel",
    "two_layer_keep",
    "spread_line",
    "v13_inspired",
)


def _discover_template_names() -> Tuple[str, ...]:
    """Read offense/templates/*.json filenames at import time.

    We use filenames (without .json) as the canonical template name list
    so genome indices match what the offense planner sees. If the
    templates directory is missing (e.g. running unit tests in isolation)
    we fall back to the curated 17-name list from Phase 4.
    """
    here = Path(__file__).resolve()
    tpl_dir = here.parent.parent / "offense" / "templates"
    if tpl_dir.is_dir():
        names = sorted(p.stem for p in tpl_dir.glob("*.json"))
        if names:
            return tuple(names)
    return (
        "corner_dive_left",
        "corner_dive_right",
        "demo_train_left",
        "demo_train_right",
        "dual_flank",
        "escorted_mixed_left",
        "escorted_mixed_right",
        "heavy_demo_left",
        "heavy_demo_right",
        "interceptor_screen",
        "mixed_burst_left",
        "mixed_burst_right",
        "scout_flood",
        "scout_flood_right",
        "scout_rush_center",
        "scout_rush_left",
        "scout_rush_right",
    )


DOMINANT_TEMPLATE_NAMES: Tuple[str, ...] = _discover_template_names()


# ---------------------------------------------------------------------------
# Real-dimension bounds  (lo, hi)
# ---------------------------------------------------------------------------

BOUNDS: Dict[str, Tuple[float, float]] = {
    "turret_density_mult":  (0.5, 1.5),
    "support_weight":       (10.0, 50.0),
    "mp_hoard_threshold":   (0.3, 1.0),
    "spawn_side_bias":      (-1.0, 1.0),
    "demo_scout_ratio":     (0.0, 1.0),
    "alpha":                (0.5, 2.0),
    "beta":                 (0.0, 1.5),
    "gamma":                (0.0, 0.5),
    "delta":                (0.0, 0.5),
}


# Mutation hyperparameters
CATEGORICAL_MUT_RATE = 0.25  # per-categorical-dim probability of replacement
REAL_SIGMA_FRAC = 0.15       # gaussian sigma as fraction of (hi - lo)


# ---------------------------------------------------------------------------
# Genome dataclass
# ---------------------------------------------------------------------------


@dataclass(frozen=True)
class Genome:
    """11-dimensional Athena strategy genome.

    All fields are plain Python scalars so the genome is JSON-serializable.
    """
    # Defense
    archetype_idx: int
    turret_density_mult: float
    support_weight: float
    # Offense
    dominant_template_idx: int
    mp_hoard_threshold: float
    spawn_side_bias: float
    demo_scout_ratio: float
    # Utility weights
    alpha: float
    beta: float
    gamma: float
    delta: float

    # ------------------------------------------------------------------
    # Convenience accessors
    # ------------------------------------------------------------------

    @property
    def archetype_name(self) -> str:
        return DEFENSE_ARCHETYPES[
            max(0, min(self.archetype_idx, len(DEFENSE_ARCHETYPES) - 1))
        ]

    @property
    def template_name(self) -> str:
        return DOMINANT_TEMPLATE_NAMES[
            max(0, min(self.dominant_template_idx,
                       len(DOMINANT_TEMPLATE_NAMES) - 1))
        ]

    def to_dict(self) -> Dict[str, Any]:
        return asdict(self)

    @classmethod
    def from_dict(cls, d: Dict[str, Any]) -> "Genome":
        return cls(
            archetype_idx=int(d["archetype_idx"]),
            turret_density_mult=float(d["turret_density_mult"]),
            support_weight=float(d["support_weight"]),
            dominant_template_idx=int(d["dominant_template_idx"]),
            mp_hoard_threshold=float(d["mp_hoard_threshold"]),
            spawn_side_bias=float(d["spawn_side_bias"]),
            demo_scout_ratio=float(d["demo_scout_ratio"]),
            alpha=float(d["alpha"]),
            beta=float(d["beta"]),
            gamma=float(d["gamma"]),
            delta=float(d["delta"]),
        )


# ---------------------------------------------------------------------------
# Encode / decode helpers
# ---------------------------------------------------------------------------


def encode_genome(g: Genome) -> List[float]:
    """Pack genome into a flat float list (categorical dims as ints in
    float positions)."""
    return [
        float(g.archetype_idx),
        g.turret_density_mult,
        g.support_weight,
        float(g.dominant_template_idx),
        g.mp_hoard_threshold,
        g.spawn_side_bias,
        g.demo_scout_ratio,
        g.alpha,
        g.beta,
        g.gamma,
        g.delta,
    ]


def decode_genome(vec: Sequence[float]) -> Genome:
    if len(vec) != 11:
        raise ValueError(f"genome vector must have 11 dims, got {len(vec)}")
    return Genome(
        archetype_idx=int(vec[0]),
        turret_density_mult=float(vec[1]),
        support_weight=float(vec[2]),
        dominant_template_idx=int(vec[3]),
        mp_hoard_threshold=float(vec[4]),
        spawn_side_bias=float(vec[5]),
        demo_scout_ratio=float(vec[6]),
        alpha=float(vec[7]),
        beta=float(vec[8]),
        gamma=float(vec[9]),
        delta=float(vec[10]),
    )


# ---------------------------------------------------------------------------
# Random / mutation
# ---------------------------------------------------------------------------


def _clip(x: float, lo: float, hi: float) -> float:
    if x < lo:
        return lo
    if x > hi:
        return hi
    return x


def random_genome(rng: random.Random) -> Genome:
    """Sample a uniform-random genome from the full design space."""
    return Genome(
        archetype_idx=rng.randrange(len(DEFENSE_ARCHETYPES)),
        turret_density_mult=rng.uniform(*BOUNDS["turret_density_mult"]),
        support_weight=rng.uniform(*BOUNDS["support_weight"]),
        dominant_template_idx=rng.randrange(len(DOMINANT_TEMPLATE_NAMES)),
        mp_hoard_threshold=rng.uniform(*BOUNDS["mp_hoard_threshold"]),
        spawn_side_bias=rng.uniform(*BOUNDS["spawn_side_bias"]),
        demo_scout_ratio=rng.uniform(*BOUNDS["demo_scout_ratio"]),
        alpha=rng.uniform(*BOUNDS["alpha"]),
        beta=rng.uniform(*BOUNDS["beta"]),
        gamma=rng.uniform(*BOUNDS["gamma"]),
        delta=rng.uniform(*BOUNDS["delta"]),
    )


def _gauss(rng: random.Random, x: float, lo: float, hi: float) -> float:
    sigma = (hi - lo) * REAL_SIGMA_FRAC
    return _clip(x + rng.gauss(0.0, sigma), lo, hi)


def mutate_genome(g: Genome, rng: random.Random) -> Genome:
    """Return a child genome: gaussian noise on real dims, random
    replacement on categorical dims with prob CATEGORICAL_MUT_RATE."""
    arch = g.archetype_idx
    if rng.random() < CATEGORICAL_MUT_RATE:
        arch = rng.randrange(len(DEFENSE_ARCHETYPES))
    tpl = g.dominant_template_idx
    if rng.random() < CATEGORICAL_MUT_RATE:
        tpl = rng.randrange(len(DOMINANT_TEMPLATE_NAMES))
    return Genome(
        archetype_idx=int(arch),
        turret_density_mult=_gauss(rng, g.turret_density_mult,
                                   *BOUNDS["turret_density_mult"]),
        support_weight=_gauss(rng, g.support_weight,
                              *BOUNDS["support_weight"]),
        dominant_template_idx=int(tpl),
        mp_hoard_threshold=_gauss(rng, g.mp_hoard_threshold,
                                  *BOUNDS["mp_hoard_threshold"]),
        spawn_side_bias=_gauss(rng, g.spawn_side_bias,
                               *BOUNDS["spawn_side_bias"]),
        demo_scout_ratio=_gauss(rng, g.demo_scout_ratio,
                                *BOUNDS["demo_scout_ratio"]),
        alpha=_gauss(rng, g.alpha, *BOUNDS["alpha"]),
        beta=_gauss(rng, g.beta, *BOUNDS["beta"]),
        gamma=_gauss(rng, g.gamma, *BOUNDS["gamma"]),
        delta=_gauss(rng, g.delta, *BOUNDS["delta"]),
    )


__all__ = [
    "BOUNDS",
    "CATEGORICAL_MUT_RATE",
    "DEFENSE_ARCHETYPES",
    "DOMINANT_TEMPLATE_NAMES",
    "Genome",
    "REAL_SIGMA_FRAC",
    "decode_genome",
    "encode_genome",
    "mutate_genome",
    "random_genome",
]
