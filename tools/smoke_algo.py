#!/usr/bin/env python3
"""Check an algo's two-line deploy protocol on a synthetic initial state."""
import argparse
import json
import os
from pathlib import Path
import signal
import subprocess
import sys

from match_runner import CONFIG_JSON, resolve_algo


def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('algo')
    args=parser.parse_args()
    algo=resolve_algo(args.algo)
    config=json.loads(CONFIG_JSON.read_text())
    state={'turnInfo':[0,0,-1,0],'p1Stats':[40,8,1,0],'p2Stats':[40,8,1,0],
           'p1Units':[[] for _ in range(8)],'p2Units':[[] for _ in range(8)],'events':{}}
    end={**state,'turnInfo':[2,0,0,0]}
    env={**os.environ,'PYTHON_CMD':os.environ.get('PYTHON_CMD',sys.executable)}
    proc=subprocess.Popen([str(algo/'run.sh')],stdin=subprocess.PIPE,stdout=subprocess.PIPE,
                          stderr=subprocess.PIPE,text=True,start_new_session=True,env=env)
    try:
        stdout,stderr=proc.communicate('\n'.join(map(json.dumps,[config,state,end]))+'\n',timeout=30)
    except subprocess.TimeoutExpired:
        try:os.killpg(proc.pid,signal.SIGKILL)
        except ProcessLookupError:pass
        proc.communicate()
        parser.exit(1,'FAIL: algo exceeded the 30-second smoke limit\n')
    if proc.returncode or any(marker in stderr for marker in ('WATCHDOG fired','on_turn exception','returning identity','safe_fallback failed')):
        print(stderr[-3000:],file=sys.stderr)
        return 1
    try:
        commands=[json.loads(line) for line in stdout.splitlines() if line.strip()]
        if len(commands)!=2 or not all(isinstance(command,list) for command in commands):
            raise ValueError('expected two JSON arrays for build and deploy')
        for phase in commands:
            for command in phase:
                if not isinstance(command,list) or len(command)!=3:
                    raise ValueError(f'invalid action: {command!r}')
    except (ValueError,TypeError) as exc:
        print(f'FAIL: {exc}',file=sys.stderr)
        return 1
    print(f'PASS: {algo.name} returned two valid command arrays without turn fallback')
    return 0


if __name__=='__main__':raise SystemExit(main())
