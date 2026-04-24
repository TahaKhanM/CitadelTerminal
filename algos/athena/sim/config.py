"""SimCore config loader — typed accessors over citadel_config_snapshot.json.

Canonical source: `_raw_unit_information` (an exact copy of what engine.jar
serves at game start, per unitInformation in replays). All StructureSpec /
MobileSpec values are DERIVED by re-implementing engine.jar's
Config.processUnitInfoInPlace / Config.java:184-190 copy-then-patch
semantics. We do not maintain a hand-curated `_derived_lookup_for_sim_core`
block — that was historically a source of silent drift (notably EF.upgrade
mispriced at 2 SP instead of 4 because its live config has no cost1 key
and the upgrade inherits base.cost1=4).

The snapshot uses pre-rename shorthands (FF/EF/DF/PI/EI/SI), matching what
the live server delivers. See memory/citadel_runtime_shorthands.md.

Drift guard: run `python3 -m algos.athena.sim.drift_check
<path/to/ranked.replay>` (or via the regression harness) to verify every
gameplay-relevant field in `_raw_unit_information` matches the replay's
live `unitInformation`.
"""

from __future__ import annotations

import json
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, Optional

import numpy as np

# Every HP/shield/damage/cost/refund/range/speed/resource float in the
# engine is a Java 32-bit float. SimCore stores them as numpy.float32 end
# to end — Python's default float is a 64-bit double, and mixing them
# silently re-widens intermediate results. See algos/athena/sim/tests/
# test_float32_propagation.py for the invariants that guard this.
FP32 = np.float32

# Shorthand constants — MATCH the engine's on-wire values. Athena code that
# compares unit_type strings must use these, never the doc-level rename.
SH_WALL = "FF"
SH_SUPPORT = "EF"
SH_TURRET = "DF"
SH_SCOUT = "PI"
SH_DEMOLISHER = "EI"
SH_INTERCEPTOR = "SI"
SH_REMOVE = "RM"
SH_UPGRADE = "UP"

IDX_WALL = 0
IDX_SUPPORT = 1
IDX_TURRET = 2
IDX_SCOUT = 3
IDX_DEMOLISHER = 4
IDX_INTERCEPTOR = 5
IDX_REMOVE = 6
IDX_UPGRADE = 7

STRUCTURE_TYPES = (IDX_WALL, IDX_SUPPORT, IDX_TURRET)
MOBILE_TYPES = (IDX_SCOUT, IDX_DEMOLISHER, IDX_INTERCEPTOR)

ARENA = 28
HALF = 14


# ---------------------------------------------------------------- engine mirror

# Gameplay-relevant fields in engine.jar's SpawnUnitsSystem.UnitConfig that
# Config.processUnitInfoInPlace (Config.java:99-176) populates from JSON.
# Map: JSON key (camelCase from server) → (Python attr, default).
#
# Fields NOT listed are UI-only (icons, scales, display names) and are
# ignored; drift on those is cosmetic, not gameplay-relevant.
_ENGINE_FIELDS: Dict[str, Any] = {
    # JSON key              Python attr          default (engine's UnitConfig)
    "cost1":                 ("metal_cost",              0.0),
    "cost2":                 ("food_cost",               0.0),
    "startHealth":           ("start_health",            0.0),  # also sets maxHealth
    "getHitRadius":          ("get_hit_radius",          0.0),
    "attackRange":           ("attack_range",            0.0),
    "attackDamageTower":     ("attack_damage_tower",     0.0),
    "attackDamageWalker":    ("attack_damage_walker",    0.0),
    "shieldRange":           ("shield_range",            0.0),
    "shieldPerUnit":         ("shield_per_unit",         0.0),
    "shieldDecay":           ("shield_decay",            0.0),
    "shieldBonusPerY":       ("shield_bonus_per_y",      0.0),
    "speed":                 ("speed",                   0.0),
    "playerBreachDamage":    ("player_breach_damage",    0.0),
    "metalForBreach":        ("metal_for_breach",        0.0),
    "selfDestructDamageWalker": ("self_destruct_damage_walker", 0.0),
    "selfDestructDamageTower":  ("self_destruct_damage_tower",  0.0),
    "selfDestructRange":     ("self_destruct_range",     0.0),
    "selfDestructStepsRequired": ("self_destruct_steps_required", 0),
    "refundPercentage":      ("refund_percentage",       0.0),
    "turnsRequiredToRemove": ("turns_required_to_remove", 0),
    "generatesResource1":    ("generates_resource1",     0.0),
    "generatesResource2":    ("generates_resource2",     0.0),
}

# Fields considered cosmetic (UI) — tracked for audit but not part of spec.
_UI_FIELDS = frozenset([
    "iconxScale", "iconyScale", "icon", "display", "shorthand", "unitCategory",
])


def _fill_unit_config(unit_dict: Dict[str, Any]) -> Dict[str, Any]:
    """Mirror Config.processUnitInfoInPlace (Config.java:99-176): populate a
    fresh UnitConfig with defaults, then overwrite each field that is present
    in `unit_dict`. Fields absent from `unit_dict` retain the default."""
    out: Dict[str, Any] = {}
    for _json_key, (attr, default) in _ENGINE_FIELDS.items():
        out[attr] = default
    for json_key, (attr, _default) in _ENGINE_FIELDS.items():
        if json_key in unit_dict and unit_dict[json_key] is not None:
            v = unit_dict[json_key]
            # Engine stores startHealth → both startHealth and maxHealth.
            out[attr] = v
    return out


def _patch_upgrade(base_cfg: Dict[str, Any],
                   upgrade_dict: Dict[str, Any]) -> Dict[str, Any]:
    """Mirror Config.java:184-190: upgrade variant is a COPY of the base with
    fields from the upgrade sub-dict overriding only where present. Critical
    for fields like EF.upgrade.cost1 (missing in live config → inherits base
    metalCost=4, not "defaults to 0")."""
    cfg = dict(base_cfg)  # shallow copy; values are scalars
    for json_key, (attr, _default) in _ENGINE_FIELDS.items():
        if json_key in upgrade_dict and upgrade_dict[json_key] is not None:
            cfg[attr] = upgrade_dict[json_key]
    return cfg


# ---------------------------------------------------------------- specs

@dataclass(frozen=True)
class StructureSpec:
    # Every numeric field below is np.float32 at runtime (engine-parity).
    # Annotated Any so mypy/mypyc won't reject assignments from float32
    # factories. Strongly-typed callers hold np.float32 values throughout.
    hp: Any
    cost_sp: Any
    refund_pct: Any
    turns_required_to_remove: int
    attack_range: Any = 0.0
    attack_damage_walker: Any = 0.0
    attack_damage_tower: Any = 0.0
    shield_range: Any = 0.0
    shield_per_unit: Any = 0.0
    shield_bonus_per_y: Any = 0.0
    shield_decay: Any = 0.0


@dataclass(frozen=True)
class MobileSpec:
    # Same dtype discipline as StructureSpec.
    hp: Any
    cost_mp: Any
    attack_range: Any
    attack_damage_walker: Any
    attack_damage_tower: Any
    speed: Any
    self_destruct_range: Any
    self_destruct_damage_walker: Any
    self_destruct_damage_tower: Any
    self_destruct_steps_required: int
    breach_damage: Any
    metal_for_breach: Any


@dataclass(frozen=True)
class Resources:
    starting_hp: float
    starting_sp: float
    starting_mp: float
    mp_decay_per_round: float      # fraction; 0.25 = 25%
    mp_income_per_round: float     # base bits added each round start
    sp_income_per_round: float     # cores added each round start
    mp_cap: float
    bit_ramp_interval: int         # rounds per ramp increment for MP cap
    bit_ramp_cap_growth: float
    round_start_bit_ramp: int
    bit_growth_rate: float         # additional MP added per round as ramp applies
    cores_for_player_damage: float  # legacy pre-season-5 field; unused post-5


def _struct_spec_from(cfg: Dict[str, Any]) -> StructureSpec:
    return StructureSpec(
        hp=FP32(cfg["start_health"]),
        cost_sp=FP32(cfg["metal_cost"]),
        refund_pct=FP32(cfg["refund_percentage"]),
        turns_required_to_remove=int(cfg["turns_required_to_remove"]),
        attack_range=FP32(cfg["attack_range"]),
        attack_damage_walker=FP32(cfg["attack_damage_walker"]),
        attack_damage_tower=FP32(cfg["attack_damage_tower"]),
        shield_range=FP32(cfg["shield_range"]),
        shield_per_unit=FP32(cfg["shield_per_unit"]),
        shield_bonus_per_y=FP32(cfg["shield_bonus_per_y"]),
        shield_decay=FP32(cfg["shield_decay"]),
    )


def _mobile_spec_from(cfg: Dict[str, Any]) -> MobileSpec:
    return MobileSpec(
        hp=FP32(cfg["start_health"]),
        cost_mp=FP32(cfg["food_cost"]),
        attack_range=FP32(cfg["attack_range"]),
        attack_damage_walker=FP32(cfg["attack_damage_walker"]),
        attack_damage_tower=FP32(cfg["attack_damage_tower"]),
        speed=FP32(cfg["speed"]),
        self_destruct_range=FP32(cfg["self_destruct_range"]),
        self_destruct_damage_walker=FP32(cfg["self_destruct_damage_walker"]),
        self_destruct_damage_tower=FP32(cfg["self_destruct_damage_tower"]),
        self_destruct_steps_required=int(cfg["self_destruct_steps_required"]),
        breach_damage=FP32(cfg["player_breach_damage"]),
        metal_for_breach=FP32(cfg["metal_for_breach"]),
    )


# ---------------------------------------------------------------- SimConfig

class SimConfig:
    """Frozen view over citadel_config_snapshot.json with typed accessors.

    Loaded from `_raw_unit_information` + `_resources_block_verbatim` only.
    Derived specs are computed here — never read from any hand-maintained
    derived lookup block, to prevent silent drift."""

    def __init__(self, raw: Dict[str, Any]):
        self._raw = raw
        raw_units = raw.get("_raw_unit_information")
        if not raw_units:
            raise ValueError(
                "citadel_config_snapshot.json is missing _raw_unit_information; "
                "regenerate via /inspect-config."
            )
        # Build fully-patched base + upgrade configs for every shorthand.
        by_sh: Dict[str, Dict[str, Dict[str, Any]]] = {}
        for u in raw_units:
            sh = u.get("shorthand")
            if not sh:
                continue
            base_cfg = _fill_unit_config(u)
            upg_cfg: Optional[Dict[str, Any]] = None
            if "upgrade" in u and isinstance(u["upgrade"], dict):
                upg_cfg = _patch_upgrade(base_cfg, u["upgrade"])
            by_sh[sh] = {"base": base_cfg, "upgrade": upg_cfg or {}}

        # Structures
        self.wall_base = _struct_spec_from(by_sh["FF"]["base"])
        self.wall_upg = _struct_spec_from(by_sh["FF"]["upgrade"])
        self.support_base = _struct_spec_from(by_sh["EF"]["base"])
        self.support_upg = _struct_spec_from(by_sh["EF"]["upgrade"])
        self.turret_base = _struct_spec_from(by_sh["DF"]["base"])
        self.turret_upg = _struct_spec_from(by_sh["DF"]["upgrade"])

        # Mobile units
        self.scout = _mobile_spec_from(by_sh["PI"]["base"])
        self.demolisher = _mobile_spec_from(by_sh["EI"]["base"])
        self.interceptor = _mobile_spec_from(by_sh["SI"]["base"])

        # Perf: direct-index spec tables. `_struct_specs[type_idx][upgraded]`
        # and `_mobile_specs[type_idx]` let hot-path callers skip the
        # branch tower in structure_spec() / mobile_spec(). None for
        # indices that don't correspond to the given category.
        self._struct_specs: list = [
            (self.wall_base, self.wall_upg),
            (self.support_base, self.support_upg),
            (self.turret_base, self.turret_upg),
            None, None, None, None, None,
        ]
        self._mobile_specs: list = [
            None, None, None,
            self.scout, self.demolisher, self.interceptor,
            None, None,
        ]

        # Resources block — verbatim from engine
        resources = raw.get("_resources_block_verbatim", {})
        self.resources = Resources(
            starting_hp=float(resources["startingHP"]),
            starting_sp=float(resources["startingCores"]),
            starting_mp=float(resources["startingBits"]),
            mp_decay_per_round=float(resources["bitDecayPerRound"]),
            mp_income_per_round=float(resources["bitsPerRound"]),
            sp_income_per_round=float(resources["coresPerRound"]),
            mp_cap=float(resources["maxBits"]),
            bit_ramp_interval=int(resources["turnIntervalForBitSchedule"]),
            bit_ramp_cap_growth=float(resources["bitRampBitCapGrowthRate"]),
            round_start_bit_ramp=int(resources["roundStartBitRamp"]),
            bit_growth_rate=float(resources["bitGrowthRate"]),
            cores_for_player_damage=float(resources.get("coresForPlayerDamage", 1)),
        )

    # ------------------------------------------------- lookups by unit type

    def structure_spec(self, type_idx: int, upgraded: bool) -> StructureSpec:
        if type_idx == IDX_WALL:
            return self.wall_upg if upgraded else self.wall_base
        if type_idx == IDX_SUPPORT:
            return self.support_upg if upgraded else self.support_base
        if type_idx == IDX_TURRET:
            return self.turret_upg if upgraded else self.turret_base
        raise ValueError(f"not a structure type: {type_idx}")

    def mobile_spec(self, type_idx: int) -> MobileSpec:
        if type_idx == IDX_SCOUT:
            return self.scout
        if type_idx == IDX_DEMOLISHER:
            return self.demolisher
        if type_idx == IDX_INTERCEPTOR:
            return self.interceptor
        raise ValueError(f"not a mobile type: {type_idx}")

    # ---------------------------------------------------- factory

    @classmethod
    def load(cls, path: Optional[Path] = None) -> "SimConfig":
        if path is None:
            here = Path(__file__).resolve().parent
            path = here.parent / "data" / "citadel_config_snapshot.json"
        with open(path, "r", encoding="utf-8") as f:
            return cls(json.load(f))
