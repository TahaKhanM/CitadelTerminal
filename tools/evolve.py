#!/usr/bin/env python3
"""CMA-ES over v19_evolved's 16-knob CONFIG.

Fitness per individual = sum of win rates across 5 opponents:
    v14_support_caravan, v13_second_ring,
    opp_scout_rush, opp_demo_line, opp_turret_castle
Each opponent is evaluated via `python3 tools/bestof.py v19_evolved <opp> N`,
with the candidate knobs injected via the env var CITADEL_CFG_JSON.

Individuals are evaluated SERIALLY (one at a time), but each bestof call
farms its games across a process pool. This keeps the CITADEL_CFG_JSON
env var unambiguous per game and avoids races over the shared
`algos/v19_evolved/` folder.

Usage:
    python3 tools/evolve.py [--gens=15] [--pop=25] [--n=2] [--sigma=0.3]

Checkpoints every generation to tools/evolve_state.pkl. Prints one status
line per generation: "gen N | best=X.XX | mean=Y.YY | worst=Z.ZZ".
Final best is written to tools/evolve_best.json.
"""
import argparse
import json
import math
import os
import pickle
import re
import subprocess
import sys
import time
from pathlib import Path

import cma

REPO_ROOT = Path(__file__).resolve().parent.parent
ALGO_DIR = REPO_ROOT / "algos" / "v19_evolved"
sys.path.insert(0, str(ALGO_DIR))
from knobs import CONFIG, BOUNDS, KNOB_ORDER  # noqa: E402

OPPONENTS = [
    "v14_support_caravan",
    "v13_second_ring",
    "opp_scout_rush",
    "opp_demo_line",
    "opp_turret_castle",
]

STATE_PATH = REPO_ROOT / "tools" / "evolve_state.pkl"
BEST_JSON = REPO_ROOT / "tools" / "evolve_best.json"
LOG_PATH = REPO_ROOT / "tools" / "evolve_log.txt"

# Regex for parsing SUMMARY_JSON line that bestof.py emits.
SUMMARY_RE = re.compile(r"^SUMMARY_JSON:\s*(\{.*\})\s*$")


def unit_to_config(x):
    """Map a [0,1]-clipped unit vector (ordered by KNOB_ORDER) to a CONFIG dict."""
    cfg = {}
    for i, name in enumerate(KNOB_ORDER):
        lo, hi, is_int = BOUNDS[name]
        v = lo + max(0.0, min(1.0, float(x[i]))) * (hi - lo)
        if is_int:
            v = int(round(v))
        else:
            v = float(round(v, 4))
        cfg[name] = v
    return cfg


def config_to_unit(cfg):
    x = []
    for name in KNOB_ORDER:
        lo, hi, _ = BOUNDS[name]
        x.append((cfg[name] - lo) / (hi - lo) if hi > lo else 0.5)
    return x


def eval_individual(cfg, n_per_opp=2, timeout=180):
    """Return total win rate (0..5) across the 5 opponents."""
    env = os.environ.copy()
    env["CITADEL_CFG_JSON"] = json.dumps(cfg)
    # Silence bestof's apply_competition_config output to keep logs clean.
    env["PYTHONUNBUFFERED"] = "1"
    total = 0.0
    per_opp = {}
    for opp in OPPONENTS:
        try:
            proc = subprocess.run(
                ["python3", str(REPO_ROOT / "tools" / "bestof.py"),
                 "v19_evolved", opp, str(n_per_opp)],
                cwd=str(REPO_ROOT),
                env=env,
                capture_output=True,
                text=True,
                timeout=timeout * n_per_opp * 2,
            )
        except subprocess.TimeoutExpired:
            per_opp[opp] = None
            continue
        rate = None
        for line in proc.stdout.splitlines():
            m = SUMMARY_RE.match(line.strip())
            if m:
                try:
                    data = json.loads(m.group(1))
                    rate = float(data["a_rate"])
                except Exception:
                    rate = None
                break
        if rate is None:
            # Fallback: parse wins manually. Count as 0.
            rate = 0.0
        per_opp[opp] = rate
        total += rate
    return total, per_opp


def save_checkpoint(es, gen, best_x, best_fit, history):
    with STATE_PATH.open("wb") as f:
        pickle.dump({
            "es": es,
            "gen": gen,
            "best_x": best_x,
            "best_fit": best_fit,
            "history": history,
        }, f)


def load_checkpoint():
    if not STATE_PATH.exists():
        return None
    try:
        with STATE_PATH.open("rb") as f:
            return pickle.load(f)
    except Exception:
        return None


def log(msg):
    ts = time.strftime("%H:%M:%S")
    line = f"[{ts}] {msg}"
    print(line, flush=True)
    with LOG_PATH.open("a") as f:
        f.write(line + "\n")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--gens", type=int, default=15)
    ap.add_argument("--pop", type=int, default=25)
    ap.add_argument("--n", type=int, default=2, help="bestof n per opponent (2→4 games)")
    ap.add_argument("--sigma", type=float, default=0.3)
    ap.add_argument("--resume", action="store_true")
    args = ap.parse_args()

    LOG_PATH.write_text("")  # fresh log per run
    log(f"cma={cma.__version__} gens={args.gens} pop={args.pop} n={args.n} sigma0={args.sigma}")
    log(f"opponents: {OPPONENTS}")
    log(f"knobs ({len(KNOB_ORDER)}): {KNOB_ORDER}")

    x0 = config_to_unit(CONFIG)
    opts = {
        "bounds": [[0.0] * len(KNOB_ORDER), [1.0] * len(KNOB_ORDER)],
        "popsize": args.pop,
        "verbose": -9,
        "maxiter": args.gens,
        "seed": 1,
    }
    es = cma.CMAEvolutionStrategy(x0, args.sigma, opts)

    start_gen = 0
    best_x = list(x0)
    best_fit = -1.0
    history = []

    if args.resume:
        ck = load_checkpoint()
        if ck is not None:
            es = ck["es"]
            start_gen = ck["gen"]
            best_x = ck["best_x"]
            best_fit = ck["best_fit"]
            history = ck["history"]
            log(f"resumed from generation {start_gen}, best={best_fit:.3f}")

    for gen in range(start_gen, args.gens):
        t0 = time.time()
        solutions = es.ask()
        fitnesses = []
        per_solution_breakdown = []
        for idx, x in enumerate(solutions):
            cfg = unit_to_config(x)
            total, per_opp = eval_individual(cfg, n_per_opp=args.n)
            fitnesses.append(-total)  # CMA-ES minimises
            per_solution_breakdown.append((total, cfg, per_opp))
            if total > best_fit:
                best_fit = total
                best_x = list(x)
                # live best dump — safe to inspect mid-run
                with BEST_JSON.open("w") as f:
                    json.dump({
                        "fitness": total,
                        "generation": gen,
                        "config": cfg,
                        "per_opponent_win_rate": per_opp,
                    }, f, indent=2)
        es.tell(solutions, fitnesses)
        elapsed = time.time() - t0
        scores = [-f for f in fitnesses]
        hist_entry = {
            "gen": gen,
            "best": max(scores),
            "mean": sum(scores) / len(scores),
            "worst": min(scores),
            "var_top10": _top_variance(scores, 10),
            "elapsed_s": elapsed,
        }
        history.append(hist_entry)
        top_cfg = per_solution_breakdown[max(range(len(scores)), key=lambda i: scores[i])][1]
        top_moves = _top_changed_knobs(CONFIG, top_cfg, 5)
        log(f"gen {gen:2d} | best={hist_entry['best']:.2f} "
            f"mean={hist_entry['mean']:.2f} worst={hist_entry['worst']:.2f} "
            f"top10_var={hist_entry['var_top10']:.3f} "
            f"elapsed={elapsed:.0f}s | moved: {top_moves}")
        save_checkpoint(es, gen + 1, best_x, best_fit, history)

    # Write final best
    final_cfg = unit_to_config(best_x)
    with BEST_JSON.open("w") as f:
        json.dump({
            "fitness": best_fit,
            "config": final_cfg,
            "history": history,
            "knob_changes": _top_changed_knobs(CONFIG, final_cfg, len(KNOB_ORDER)),
        }, f, indent=2)
    log(f"done. best_fitness={best_fit:.3f}")
    log(f"best config: {final_cfg}")
    return 0


def _top_variance(scores, k):
    top = sorted(scores, reverse=True)[:k]
    if len(top) < 2:
        return 0.0
    m = sum(top) / len(top)
    return math.sqrt(sum((s - m) ** 2 for s in top) / len(top))


def _top_changed_knobs(base, new, k):
    moves = []
    for name in KNOB_ORDER:
        lo, hi, _ = BOUNDS[name]
        span = hi - lo
        if span <= 0:
            continue
        delta = abs(new[name] - base[name]) / span
        moves.append((name, delta, base[name], new[name]))
    moves.sort(key=lambda t: t[1], reverse=True)
    return [(n, b, v) for n, d, b, v in moves[:k]]


if __name__ == "__main__":
    raise SystemExit(main())
