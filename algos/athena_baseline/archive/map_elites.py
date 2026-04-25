"""MAP-Elites driver — Phase 6 milestone J2.

Top-level loop::

    archive = run_map_elites(n_iterations=200, seed=42)

Algorithm:
  1. Initialize 10 random genomes; evaluate each; place each in archive.
  2. Loop N iterations:
       a. Sample a parent from the archive.
       b. Mutate the parent.
       c. Evaluate the child.
       d. Add child to archive (replaces incumbent if higher fitness).
  3. Save the archive every ``checkpoint_every`` iterations.
  4. Return the final archive.

CLI usage::

    python -m algos.athena_v3_planner.archive.map_elites \\
        --iterations 200 --seed 42 \\
        --output algos/athena_v3_planner/data/map_elites_archive.json
"""
from __future__ import annotations

import argparse
import json
import random
import sys
import time
from pathlib import Path
from typing import Optional

from .archive import MAPElitesArchive
from .fitness import evaluate
from .genome import Genome, mutate_genome, random_genome


def run_map_elites(
    *,
    n_iterations: int = 200,
    seed: int = 42,
    n_init: int = 10,
    grid_w: int = 8,
    grid_h: int = 8,
    n_matches_per_baseline: int = 3,
    n_rounds: int = 8,
    checkpoint_path: Optional[str] = None,
    checkpoint_every: int = 50,
    log_fn=None,
) -> MAPElitesArchive:
    """Run MAP-Elites and return the final archive.

    All hyperparameters are exposed for unit tests / Phase 6B sweeping.
    """
    log = log_fn or print
    rng = random.Random(int(seed))
    archive = MAPElitesArchive(grid_w=grid_w, grid_h=grid_h)

    t0 = time.perf_counter()

    # ---- Init: random genomes ----
    log(f"[map-elites] init: evaluating {n_init} random genomes "
        f"(seed={seed})")
    init_replaced = 0
    for i in range(int(n_init)):
        g = random_genome(rng)
        fit, beh, _ = evaluate(
            g,
            n_matches_per_baseline=n_matches_per_baseline,
            n_rounds=n_rounds,
        )
        if archive.add(g, fit, beh):
            init_replaced += 1
    cov = archive.coverage()
    log(f"[map-elites] init done: filled {cov[0]}/{cov[1]} cells, "
        f"replacements={init_replaced}, "
        f"best_fit={archive.best_fitness()}")

    # ---- Main loop ----
    for it in range(int(n_iterations)):
        # Sample a parent
        parents = archive.sample(1, rng=rng)
        if not parents:
            # Cold archive — fall back to random sampling.
            parent = random_genome(rng)
        else:
            parent = parents[0]
        child = mutate_genome(parent, rng)
        fit, beh, _ = evaluate(
            child,
            n_matches_per_baseline=n_matches_per_baseline,
            n_rounds=n_rounds,
        )
        archive.add(child, fit, beh)

        if checkpoint_path and (it + 1) % checkpoint_every == 0:
            archive.save(checkpoint_path)
            cov = archive.coverage()
            log(f"[map-elites] iter={it+1} cov={cov[0]}/{cov[1]} "
                f"best_fit={archive.best_fitness():.3f} "
                f"checkpointed -> {checkpoint_path}")

    # Final save (if requested) and final report.
    if checkpoint_path:
        archive.save(checkpoint_path)
    elapsed = time.perf_counter() - t0
    cov = archive.coverage()
    log(f"[map-elites] DONE: {n_iterations} iters in {elapsed:.1f}s "
        f"({elapsed*1000/max(1, n_iterations):.1f}ms/iter), "
        f"cov={cov[0]}/{cov[1]} ({100.0*cov[0]/max(1, cov[1]):.1f}%), "
        f"best_fit={archive.best_fitness()}")
    return archive


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------


def _default_archive_path() -> str:
    here = Path(__file__).resolve().parents[1]
    return str(here / "data" / "map_elites_archive.json")


def main(argv: Optional[list] = None) -> int:
    p = argparse.ArgumentParser(description="Run MAP-Elites for Athena Phase 6")
    p.add_argument("--iterations", type=int, default=200)
    p.add_argument("--seed", type=int, default=42)
    p.add_argument("--n-init", type=int, default=10)
    p.add_argument("--grid-w", type=int, default=8)
    p.add_argument("--grid-h", type=int, default=8)
    p.add_argument("--rounds", type=int, default=8)
    p.add_argument("--matches-per-baseline", type=int, default=3)
    p.add_argument("--checkpoint-every", type=int, default=50)
    p.add_argument("--output", type=str, default=_default_archive_path())
    args = p.parse_args(argv)

    archive = run_map_elites(
        n_iterations=args.iterations,
        seed=args.seed,
        n_init=args.n_init,
        grid_w=args.grid_w,
        grid_h=args.grid_h,
        n_matches_per_baseline=args.matches_per_baseline,
        n_rounds=args.rounds,
        checkpoint_path=args.output,
        checkpoint_every=args.checkpoint_every,
    )
    return 0 if len(archive) > 0 else 1


if __name__ == "__main__":
    sys.exit(main())


__all__ = ["run_map_elites", "main"]
