"""Phase 6 milestone I tests — genome + behavior + archive scaffold."""
from __future__ import annotations

import json
import random
from pathlib import Path

import pytest

from algos.athena_v3_planner.archive import (
    DEFENSE_ARCHETYPES,
    DOMINANT_TEMPLATE_NAMES,
    Genome,
    MAPElitesArchive,
    compute_behavior_descriptors,
    decode_genome,
    encode_genome,
    mutate_genome,
    random_genome,
)
from algos.athena_v3_planner.archive.behavior import behavior_to_cell


def test_genome_categorical_domains_are_nonempty():
    assert len(DEFENSE_ARCHETYPES) >= 4
    assert "v13_inspired" in DEFENSE_ARCHETYPES
    # Templates are discovered from the templates/ dir at import time;
    # Phase 4 ships 17 — we tolerate ±5 to allow Phase 6+ template additions.
    assert 12 <= len(DOMINANT_TEMPLATE_NAMES) <= 30


def test_random_genome_within_bounds():
    rng = random.Random(123)
    for _ in range(50):
        g = random_genome(rng)
        assert 0 <= g.archetype_idx < len(DEFENSE_ARCHETYPES)
        assert 0 <= g.dominant_template_idx < len(DOMINANT_TEMPLATE_NAMES)
        assert 0.5 <= g.turret_density_mult <= 1.5
        assert 10.0 <= g.support_weight <= 50.0
        assert 0.3 <= g.mp_hoard_threshold <= 1.0
        assert -1.0 <= g.spawn_side_bias <= 1.0
        assert 0.0 <= g.demo_scout_ratio <= 1.0
        assert 0.5 <= g.alpha <= 2.0
        assert 0.0 <= g.beta <= 1.5
        assert 0.0 <= g.gamma <= 0.5
        assert 0.0 <= g.delta <= 0.5


def test_encode_decode_roundtrip():
    rng = random.Random(7)
    g = random_genome(rng)
    vec = encode_genome(g)
    g2 = decode_genome(vec)
    assert g == g2


def test_mutate_keeps_within_bounds():
    rng = random.Random(0)
    g = random_genome(rng)
    for _ in range(200):
        g = mutate_genome(g, rng)
        assert 0 <= g.archetype_idx < len(DEFENSE_ARCHETYPES)
        assert 0 <= g.dominant_template_idx < len(DOMINANT_TEMPLATE_NAMES)
        assert 0.5 <= g.turret_density_mult <= 1.5
        assert 10.0 <= g.support_weight <= 50.0


def test_genome_to_dict_json_roundtrip():
    rng = random.Random(99)
    g = random_genome(rng)
    d = g.to_dict()
    s = json.dumps(d)
    g2 = Genome.from_dict(json.loads(s))
    assert g == g2


def test_behavior_descriptors_average_across_matches():
    matches = [
        {"mean_mp_at_attack": 5.0, "defense_density": 10},
        {"mean_mp_at_attack": 15.0, "defense_density": 30},
    ]
    bc1, bc2 = compute_behavior_descriptors(matches)
    assert bc1 == pytest.approx(10.0)
    assert bc2 == pytest.approx(20.0)


def test_behavior_to_cell_extremes():
    # very low values map to first cell
    assert behavior_to_cell(0.1, 0.1, 8, 8) == (0, 0)
    # very high values map to last cell
    assert behavior_to_cell(1e6, 1e6, 8, 8) == (7, 7)


def test_archive_keeps_best_per_cell():
    a = MAPElitesArchive(grid_w=8, grid_h=8)
    rng = random.Random(2026)
    g_low = random_genome(rng)
    g_high = random_genome(rng)
    a.add(g_low, fitness=1.0, behavior=(5.0, 10.0))
    assert a.add(g_high, fitness=2.0, behavior=(5.0, 10.0)) is True
    assert a.add(g_low, fitness=0.5, behavior=(5.0, 10.0)) is False
    assert a.coverage() == (1, 64)
    assert a.best_fitness() == pytest.approx(2.0)
    assert a.best_overall() == g_high


def test_archive_serialization_roundtrip(tmp_path):
    a = MAPElitesArchive(grid_w=4, grid_h=4)
    rng = random.Random(13)
    for i in range(20):
        g = random_genome(rng)
        a.add(g, fitness=rng.uniform(-5, 5),
              behavior=(rng.uniform(0, 25), rng.uniform(0, 50)))
    p = tmp_path / "arch.json"
    a.save(p)
    b = MAPElitesArchive.deserialize(p)
    assert a.coverage() == b.coverage()
    assert a.best_fitness() == pytest.approx(b.best_fitness())


def test_archive_sample_capped_at_coverage():
    a = MAPElitesArchive(grid_w=4, grid_h=4)
    rng = random.Random(5)
    g = random_genome(rng)
    a.add(g, fitness=1.0, behavior=(2.0, 5.0))
    samples = a.sample(10, rng)
    assert len(samples) == 1
    assert samples[0] == g


def test_archive_sample_with_fitness_format():
    a = MAPElitesArchive()
    rng = random.Random(1)
    g = random_genome(rng)
    a.add(g, fitness=3.5, behavior=(7.0, 18.0))
    pairs = a.sample_with_fitness(5)
    assert len(pairs) == 1
    assert pairs[0][1] == pytest.approx(3.5)
