"""Isolated engine execution and validated replay outcomes for local evaluation."""
from __future__ import annotations

import json
import math
import os
from pathlib import Path
import shutil
import signal
import subprocess
import tempfile
import time

REPO_ROOT = Path(__file__).resolve().parents[1]
ENGINE_JAR = REPO_ROOT / 'C1GamesStarterKit-master/engine.jar'
CONFIG_JSON = REPO_ROOT / 'configs/competition-game-configs.json'


def resolve_algo(name: str) -> Path:
    for path in (Path(name), REPO_ROOT / 'algos' / name):
        if path.is_dir() and (path / 'run.sh').is_file():
            return path.resolve()
    raise FileNotFoundError(f"Cannot find an algo with run.sh: {name}")


def replay_outcome(path: Path) -> dict:
    """Require an engine terminal frame; partial logs are not match outcomes."""
    final = None
    with path.open() as stream:
        for line in stream:
            if line.strip():
                final = json.loads(line)
    if not isinstance(final, dict) or final.get('turnInfo', [None])[0] != 2:
        raise ValueError('replay has no terminal frame')
    hp = [float(final[key][0]) for key in ('p1Stats', 'p2Stats')]
    if not all(math.isfinite(value) for value in hp):
        raise ValueError('non-finite terminal HP')
    winner = 'p1' if hp[0] > hp[1] else ('p2' if hp[1] > hp[0] else 'tie')
    return {'winner': winner, 'p1_hp': hp[0], 'p2_hp': hp[1]}


def run_one_match(task):
    """Pickleable worker used by bestof, tournament and evaluate.

    task = (p1_dir, p2_dir, out_dir, game_id, timeout_seconds).
    A worker owns its config and replay directory. Failures retain diagnostic
    tails and never receive a winner. On timeout kill the whole Java/bot group.
    """
    p1, p2, output, game_id, timeout = task
    p1, p2, output = Path(p1), Path(p2), Path(output)
    started = time.monotonic()
    result = {'game_id': game_id, 'p1': p1.name, 'p2': p2.name}
    try:
        with tempfile.TemporaryDirectory(prefix='cit_match_') as scratch:
            scratch = Path(scratch)
            shutil.copyfile(CONFIG_JSON, scratch / 'game-configs.json')
            (scratch / 'replays').mkdir()
            command = ['java', '-jar', str(ENGINE_JAR), 'work', str(p1 / 'run.sh'), str(p2 / 'run.sh')]
            proc = subprocess.Popen(command, cwd=scratch, stdout=subprocess.PIPE,
                                    stderr=subprocess.PIPE, text=True, start_new_session=True)
            try:
                stdout, stderr = proc.communicate(timeout=timeout)
            except subprocess.TimeoutExpired:
                # Supported local platforms are macOS and Linux/WSL.
                try:
                    os.killpg(proc.pid, signal.SIGKILL)
                except ProcessLookupError:
                    pass
                stdout, stderr = proc.communicate()
                result['error'] = f'timeout after {timeout}s'
            result['returncode'] = proc.returncode
            result['stdout_tail'] = stdout.splitlines()[-8:]
            result['stderr_tail'] = stderr.splitlines()[-8:]
            replays = sorted((scratch / 'replays').glob('*.replay'))
            if replays:
                output.mkdir(parents=True, exist_ok=True)
                dest = output / f'game_{game_id:03d}_{p1.name}_vs_{p2.name}.replay'
                shutil.copyfile(replays[-1], dest)
                result['replay'] = str(dest)
            if 'error' not in result:
                if proc.returncode != 0:
                    result['error'] = f'engine exited with status {proc.returncode}'
                elif len(replays) != 1:
                    result['error'] = f'expected one replay, found {len(replays)}'
                else:
                    result.update(replay_outcome(dest))
    except Exception as exc:
        result['error'] = f'{type(exc).__name__}: {exc}'
    result['duration'] = time.monotonic() - started
    return result


def wilson_interval(wins: int, total: int, z: float = 1.96):
    """Nominal Bernoulli interval; repeated deterministic fixtures are not iid."""
    if not (0 <= wins <= total) or z <= 0:
        raise ValueError('require 0 <= wins <= total and z > 0')
    if total == 0:
        return 0.0, 1.0
    p = wins / total
    denom = 1 + z*z/total
    center = (p + z*z/(2*total))/denom
    half = z*math.sqrt(p*(1-p)/total + z*z/(4*total*total))/denom
    return max(0.0, center-half), min(1.0, center+half)


def summarize_results(results, n):
    """A is P1 for games 1..n and P2 thereafter; errors are unobserved games."""
    wins_a = wins_b = ties = errors = 0
    for result in results:
        if 'error' in result:
            errors += 1
        elif result['winner'] == 'tie':
            ties += 1
        elif (result['winner'] == 'p1') == (result['game_id'] <= n):
            wins_a += 1
        else:
            wins_b += 1
    completed = wins_a + wins_b + ties
    lo, hi = wilson_interval(wins_a, completed)
    return {'n': n, 'total': len(results), 'completed': completed, 'a_wins': wins_a,
            'b_wins': wins_b, 'ties': ties, 'errors': errors,
            'a_rate': wins_a/completed if completed else None,
            'ci_low': lo, 'ci_high': hi, 'complete': errors == 0}
