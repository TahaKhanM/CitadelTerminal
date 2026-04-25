"""Phase 6 milestone J tests — fitness fn + MAP-Elites loop."""
from __future__ import annotations

import random
from pathlib import Path

import pytest

from algos.athena_v3_planner.archive.archive import MAPElitesArchive
from algos.athena_v3_planner.archive.fitness import (
    BaselineOpponent,
    default_baselines,
    evaluate,
)
from algos.athena_v3_planner.archive.genome import (
    Genome,
    random_genome,
)
from algos.athena_v3_planner.archive.map_elites import run_map_elites


def test_default_baselines_have_two():
    bls = default_baselines()
    assert len(bls) == 2
    assert all(isinstance(b, BaselineOpponent) for b in bls)


def test_evaluate_returns_well_formed_tuple():
    rng = random.Random(7)
    g = random_genome(rng)
    fit, beh, results = evaluate(g, n_matches_per_baseline=1, n_rounds=4)
    assert isinstance(fit, float)
    assert isinstance(beh, tuple) and len(beh) == 2
    assert isinstance(results, list) and len(results) == 2  # 2 baselines * 1 match
    for r in results:
        assert "delta_hp_self" in r
        assert "delta_hp_opp" in r
        assert "win" in r


def test_evaluate_is_deterministic_for_same_genome():
    rng = random.Random(0)
    g = random_genome(rng)
    a = evaluate(g, n_matches_per_baseline=2, n_rounds=4)
    b = evaluate(g, n_matches_per_baseline=2, n_rounds=4)
    assert a[0] == pytest.approx(b[0])
    assert a[1][0] == pytest.approx(b[1][0])
    assert a[1][1] == pytest.approx(b[1][1])


def test_run_map_elites_smoke():
    arc = run_map_elites(
        n_iterations=15,
        seed=1,
        n_init=3,
        grid_w=4, grid_h=4,
        n_matches_per_baseline=1,
        n_rounds=4,
        log_fn=lambda *a, **k: None,
    )
    assert isinstance(arc, MAPElitesArchive)
    cov = arc.coverage()
    assert cov[1] == 16
    assert cov[0] >= 1


def test_run_map_elites_writes_checkpoint(tmp_path):
    out = tmp_path / "arch.json"
    arc = run_map_elites(
        n_iterations=20,
        seed=2,
        n_init=3,
        grid_w=4, grid_h=4,
        n_matches_per_baseline=1,
        n_rounds=4,
        checkpoint_path=str(out),
        checkpoint_every=10,
        log_fn=lambda *a, **k: None,
    )
    assert out.exists()
    arc2 = MAPElitesArchive.deserialize(out)
    assert arc2.coverage() == arc.coverage()
