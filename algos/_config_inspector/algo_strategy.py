"""
Config Inspector Algo
=====================

A diagnostic algo whose sole job is to dump the live server-delivered
`config["unitInformation"]` to stderr on turn 0, then play a passive game.

Run it against any opponent (e.g., the starter algo) once:
    ./tools/run.sh _config_inspector _config_inspector

Then inspect stderr (or the resulting replay's logs) to see the ACTUAL
values the competition server is delivering for every unit, upgrade,
shield bonus, etc. — the ground truth that resolves any ambiguity in the
written docs.

Primary questions this resolves:
  - Does upgraded Support grant `1 + 0.3 * Y` or a flat `1`? → inspect
    upgrade block for shieldPerUnit and shieldBonusPerY.
  - What is the ACTUAL refundPercentage? (Docs say 0.9, shipped json says 0.75.)
  - Any silent config changes between seasons.

The algo itself does nothing strategic — it prints then submits empty turns.
"""
import json

import gamelib


KEYS_OF_INTEREST = [
    "shorthand", "display", "unitCategory",
    "cost1", "cost2",
    "startHealth",
    "attackRange", "attackDamageWalker", "attackDamageTower",
    "shieldRange", "shieldPerUnit", "shieldBonusPerY",
    "speed",
    "selfDestructRange", "selfDestructDamageWalker", "selfDestructDamageTower",
    "selfDestructStepsRequired",
    "playerBreachDamage",
    "refundPercentage",
    "turnsRequiredToRemove",
    "getHitRadius",
    "generatesResource1", "generatesResource2",
    "metalForBreach",
]


def _prune(d):
    """Keep only fields in KEYS_OF_INTEREST, dropping missing ones."""
    return {k: d[k] for k in KEYS_OF_INTEREST if k in d}


class AlgoStrategy(gamelib.AlgoCore):
    def __init__(self):
        super().__init__()
        self._dumped = False

    def on_game_start(self, config):
        self.config = config
        gamelib.debug_write("=" * 72)
        gamelib.debug_write("CONFIG INSPECTOR — live server config")
        gamelib.debug_write("=" * 72)

        # Resources block
        if "resources" in config:
            gamelib.debug_write("RESOURCES:")
            gamelib.debug_write(json.dumps(config["resources"], indent=2))

        # Unit information — pretty-print the useful fields per unit
        gamelib.debug_write("\nUNITS (pruned to fields that matter):")
        for i, unit in enumerate(config.get("unitInformation", [])):
            shorthand = unit.get("shorthand", f"?{i}")
            display = unit.get("display", "")
            gamelib.debug_write(f"\n[{i}] {shorthand} ({display})")
            base = _prune(unit)
            gamelib.debug_write(f"  base:    {json.dumps(base)}")
            if "upgrade" in unit:
                upg = _prune(unit["upgrade"])
                gamelib.debug_write(f"  upgrade: {json.dumps(upg)}")

        # Whole raw config (for completeness; large)
        gamelib.debug_write("\n" + "=" * 72)
        gamelib.debug_write("RAW CONFIG JSON:")
        gamelib.debug_write(json.dumps(config, indent=2))
        gamelib.debug_write("=" * 72)

    def on_turn(self, turn_state):
        # Inspect dynamics on turn 0 too — compares parsed GameState vs raw config
        game_state = gamelib.GameState(self.config, turn_state)
        game_state.suppress_warnings(True)

        if not self._dumped:
            self._dumped = True
            gamelib.debug_write(f"\nTurn {game_state.turn_number} — "
                                f"HP {game_state.my_health}, "
                                f"SP {game_state.get_resource(game_state.SP)}, "
                                f"MP {game_state.get_resource(game_state.MP)}")

            # Probe a hypothetical upgraded Support's derived stats
            support_unit = gamelib.GameUnit("EF", self.config, 0, None, 13, 13)
            gamelib.debug_write(
                f"Base Support stats: "
                f"shieldPerUnit={support_unit.shieldPerUnit}, "
                f"shieldBonusPerY={support_unit.shieldBonusPerY}, "
                f"shieldRange={support_unit.shieldRange}"
            )
            support_unit.upgrade()
            gamelib.debug_write(
                f"Upgraded Support stats: "
                f"shieldPerUnit={support_unit.shieldPerUnit}, "
                f"shieldBonusPerY={support_unit.shieldBonusPerY}, "
                f"shieldRange={support_unit.shieldRange}"
            )
            # Effective shield at Y=13 given the fields
            eff = support_unit.shieldPerUnit + support_unit.shieldBonusPerY * 13
            gamelib.debug_write(
                f"Derived upgraded-Support shield at Y=13: "
                f"{support_unit.shieldPerUnit} + {support_unit.shieldBonusPerY} * 13 = {eff}"
            )

        game_state.submit_turn()


if __name__ == "__main__":
    AlgoStrategy().start()
