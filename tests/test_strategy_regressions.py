"""Run separately per variant to isolate their intentionally vendored modules."""
import json
import os
from pathlib import Path
import sys
from types import SimpleNamespace
import unittest
from unittest.mock import patch

ROOT=Path(__file__).resolve().parents[1]
ALGO=ROOT/'algos'/os.environ.get('ALGO_UNDER_TEST','smart_oracle_F2')
sys.path.insert(0,str(ALGO))
from algo_strategy import AlgoStrategy
from oracle_core.opponent_model import OpponentModel, bucket_key, ActionSignature
from oracle_core.value import _structure_value_per_side
import oracle_core.search as search_module
from oracle_core.sim_eval import _python_fallback_sim


class StrategyTests(unittest.TestCase):
    def test_removal_penalty_is_local_and_order_independent(self):
        normal={'player':1,'type_idx':2,'hp':60,'upgraded':False}
        removed={'player':1,'type_idx':0,'hp':60,'upgraded':False,'turn_start_removal':1}
        for structures in ([normal,removed],[removed,normal]):
            self.assertEqual(_structure_value_per_side({'structures':structures},1),4+.75)

    def test_previous_action_phase_updates_once_with_pretrade_resources(self):
        bot=AlgoStrategy()
        bot.opp_model=OpponentModel()
        def turn(n,our_mp=7,opp_mp=9):
            return SimpleNamespace(turn_number=n,get_resource=lambda resource,player=0: opp_mp if player else our_mp)
        bot._begin_turn_observation(turn(0))
        bot.on_action_frame(json.dumps({'turnInfo':[1,0,0],'events':{'spawn':[[[13,27],3,'100',2]]}}))
        self.assertEqual(bot.opp_model.observation_count,0)
        bot._begin_turn_observation(turn(1,20,30))
        self.assertEqual(bot.opp_model.observation_count,1)
        recorded=bot.opp_model.posterior[bucket_key(0,9,7,0)]
        self.assertEqual(sum(recorded.values()),1)
        self.assertEqual(next(iter(recorded)).scout_count,1)
        bot._begin_turn_observation(turn(1,20,30))
        self.assertEqual(bot.opp_model.observation_count,1)
        bot._begin_turn_observation(turn(2))
        self.assertEqual(bot.opp_model.observation_count,2)
        self.assertEqual(bot.opp_model.posterior[bucket_key(1,30,20,0)][ActionSignature()],1)

    def test_depth2_resource_ramp_matches_recorded_engine_boundary(self):
        cfg=json.loads((ROOT/'configs/competition-game-configs.json').read_text())
        for previous,expected in [(2,1),(3,2),(7,2),(8,3)]:
            state={'turn':previous,'p1':{'hp':40,'sp':0,'mp':0},'p2':{'hp':40,'sp':0,'mp':0},'structures':[],'mobiles':[]}
            with patch.object(search_module,'_run_sim',side_effect=lambda state,path:state):
                result=search_module._project_next_turn(state,ActionSignature(),cfg,1,'unused')
            self.assertEqual(result['p1']['mp'],expected)
            self.assertEqual(result['p1']['sp'],4)

    def test_failed_simulation_does_not_return_identity(self):
        with self.assertRaisesRegex(RuntimeError,'simulation failed'):
            _python_fallback_sim({},'/missing/config.json')


if __name__=='__main__':unittest.main()
