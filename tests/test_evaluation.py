import json
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
from unittest.mock import patch

sys.path.insert(0,str(Path(__file__).resolve().parents[1]/'tools'))
import bestof
import match_runner as runner


class EvaluationTests(unittest.TestCase):
    def test_failures_are_not_losses(self):
        results=[{'game_id':1,'winner':'p1'},{'game_id':2,'error':'timeout'}]
        summary=runner.summarize_results(results,1)
        self.assertEqual(summary['a_rate'],1)
        self.assertEqual(summary['completed'],1)
        self.assertEqual(summary['b_wins'],0)
        self.assertFalse(summary['complete'])

    def test_sides_and_ties(self):
        summary=runner.summarize_results([{'game_id':1,'winner':'p1'}, {'game_id':2,'winner':'tie'}, {'game_id':3,'winner':'p2'}, {'game_id':4,'winner':'p1'}],2)
        self.assertEqual((summary['a_wins'],summary['b_wins'],summary['ties']),(2,1,1))
        self.assertEqual(summary['a_rate'],.5)

    def test_no_completed_games_has_no_rate(self):
        self.assertIsNone(runner.summarize_results([{'game_id':1,'error':'exit'}],1)['a_rate'])
        self.assertEqual(runner.wilson_interval(0,0),(0,1))

    def test_terminal_frame_required(self):
        with tempfile.TemporaryDirectory() as d:
            path=Path(d)/'result.replay'
            frame={'turnInfo':[1,4,0], 'p1Stats':[40,1,1], 'p2Stats':[30,1,1]}
            path.write_text(json.dumps(frame)+'\n')
            with self.assertRaisesRegex(ValueError,'terminal'):
                runner.replay_outcome(path)
            frame['turnInfo'][0]=2
            path.write_text(json.dumps(frame)+'\n')
            self.assertEqual(runner.replay_outcome(path)['winner'],'p1')
            frame['p1Stats'][0]=float('nan')
            path.write_text(json.dumps(frame)+'\n')
            with self.assertRaisesRegex(ValueError,'finite'):
                runner.replay_outcome(path)

    def test_nonzero_engine_exit_never_scores_a_partial_replay(self):
        class FailedEngine:
            returncode=1
            def communicate(self,timeout): return '', 'engine crashed'
        with tempfile.TemporaryDirectory() as output, patch.object(runner.subprocess,'Popen',return_value=FailedEngine()):
            result=runner.run_one_match(('/tmp/a','/tmp/b',output,1,1))
            self.assertIn('error',result)
            self.assertNotIn('winner',result)
            self.assertEqual(result['returncode'],1)

    def test_invalid_match_count_rejected(self):
        with self.assertRaises(SystemExit):
            bestof.parse_args(['bestof','a','b','0'])

    def test_wilson_known_boundary(self):
        low,high=runner.wilson_interval(12,12)
        self.assertAlmostEqual(low,.7575,places=3)
        self.assertAlmostEqual(high,1)

    def test_local_engine_config_matches_raw_simulator_inputs(self):
        raw=json.loads((runner.REPO_ROOT/'algos/athena/data/citadel_config_snapshot.json').read_text())
        local=json.loads(runner.CONFIG_JSON.read_text())
        self.assertEqual(local['unitInformation'],raw['_raw_unit_information'])
        self.assertEqual(local['resources'],raw['_resources_block_verbatim'])


if __name__=='__main__':unittest.main()
