#!/usr/bin/env python3
"""Run N games per side with isolated engines and explicit failed-match reporting.

Usage: python3 tools/bestof.py algo_a algo_b [n=5] [--workers=auto] [--serial]
The nominal Wilson interval assumes independent draws; repeated deterministic
opponents/seeds and selection on the same pool do not establish generalization.
"""
import argparse
from concurrent.futures import ProcessPoolExecutor, as_completed
import json
import os
from pathlib import Path
import sys
import time

from match_runner import REPO_ROOT, resolve_algo, run_one_match, summarize_results, wilson_interval


def positive_int(value):
    value = int(value)
    if value <= 0:
        raise argparse.ArgumentTypeError('must be a positive integer')
    return value


def parse_args(argv):
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('algo_a')
    parser.add_argument('algo_b')
    parser.add_argument('n', nargs='?', type=positive_int, default=5)
    parser.add_argument('--workers', default='auto')
    parser.add_argument('--serial', action='store_true')
    args = parser.parse_args(argv[1:])
    workers = min(max((os.cpu_count() or 4)//2, 1), 2*args.n)
    try:
        if args.workers != 'auto':
            workers = positive_int(args.workers)
        return resolve_algo(args.algo_a), resolve_algo(args.algo_b), args.n, 1 if args.serial else workers
    except (ValueError, FileNotFoundError, argparse.ArgumentTypeError) as exc:
        parser.error(str(exc))


def main(argv):
    a, b, n, workers = parse_args(argv)
    out = REPO_ROOT / 'replays' / f'bestof_{a.name}_vs_{b.name}_{time.time_ns()}'
    out.mkdir(parents=True)
    tasks = [(str(a),str(b),str(out),i+1,480) for i in range(n)]
    tasks += [(str(b),str(a),str(out),n+i+1,480) for i in range(n)]
    started = time.monotonic()
    results = []
    print(f'[bestof] {a.name} vs {b.name}: {n} games per side, {workers} workers')
    with ProcessPoolExecutor(max_workers=workers) as pool:
        for future in as_completed([pool.submit(run_one_match, task) for task in tasks]):
            result = future.result()
            results.append(result)
            print(f"  game {result['game_id']}: {result.get('error', result.get('winner'))}", flush=True)
    results.sort(key=lambda row: row['game_id'])
    summary = summarize_results(results, n)
    summary.update(a=a.name,b=b.name,workers=workers,wall_seconds=time.monotonic()-started)
    (out / 'summary.json').write_text(json.dumps({**summary,'matches':results},indent=2)+'\n')
    print(f"Completed {summary['completed']}/{summary['total']}; A wins={summary['a_wins']}, B wins={summary['b_wins']}, ties={summary['ties']}, errors={summary['errors']}")
    print(f"A nominal Wilson 95% interval: [{summary['ci_low']:.3f}, {summary['ci_high']:.3f}]")
    print('Repeated fixed opponents/seeds are not independent evidence; hold out opponents before tuning.')
    if summary['errors']:
        print('Evaluation incomplete: repair failed games before drawing a comparison.')
    print('SUMMARY_JSON: '+json.dumps(summary))
    print(f'Saved match diagnostics: {out / "summary.json"}')
    return 1 if summary['errors'] else 0


if __name__ == '__main__':
    raise SystemExit(main(sys.argv))
