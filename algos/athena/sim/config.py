"""SimCore config loader — typed accessors over citadel_config_snapshot.json.

The live server config is authoritative; the snapshot file is a verified copy.
All numerics flow through this module; nothing downstream hardcodes values.

The snapshot uses pre-rename shorthands (FF/EF/DF/PI/EI/SI), matching what the
live server delivers. See memory/citadel_runtime_shorthands.md.
"""

from __future__ import annotations

import json
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, Optional

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


@dataclass(frozen=True)
class StructureSpec:
    hp: float
    cost_sp: float
    refund_pct: float
    turns_required_to_remove: int
    # Turret-only
    attack_range: float = 0.0
    attack_damage_walker: float = 0.0
    attack_damage_tower: float = 0.0
    # Support-only
    shield_range: float = 0.0
    shield_per_unit: float = 0.0
    shield_bonus_per_y: float = 0.0
    shield_decay: float = 0.0


@dataclass(frozen=True)
class MobileSpec:
    hp: float
    cost_mp: float
    attack_range: float
    attack_damage_walker: float
    attack_damage_tower: float
    speed: float
    self_destruct_range: float
    self_destruct_damage_walker: float
    self_destruct_damage_tower: float
    self_destruct_steps_required: int
    breach_damage: float
    metal_for_breach: float


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
    cores_for_player_damage: float  # +SP per HP of damage dealt


class SimConfig:
    """Frozen view over citadel_config_snapshot.json with typed accessors."""

    def __init__(self, raw: Dict[str, Any]):
        self._raw = raw
        derived = raw.get("_derived_lookup_for_sim_core", {})
        resources = raw.get("_resources_block_verbatim", {})

        # ---- structures
        ff = derived["FF"]
        self.wall_base = StructureSpec(
            hp=ff["base"]["hp"],
            cost_sp=ff["base"]["cost_sp"],
            refund_pct=ff["base"]["refund_pct"],
            turns_required_to_remove=ff["base"]["turns_required_to_remove"],
        )
        self.wall_upg = StructureSpec(
            hp=ff["upgrade"]["hp"],
            cost_sp=ff["upgrade"]["cost_sp"],
            refund_pct=ff["upgrade"]["refund_pct"],
            turns_required_to_remove=ff["upgrade"]["turns_required_to_remove"],
        )

        ef = derived["EF"]
        self.support_base = StructureSpec(
            hp=ef["base"]["hp"],
            cost_sp=ef["base"]["cost_sp"],
            refund_pct=ef["base"]["refund_pct"],
            turns_required_to_remove=ef["base"]["turns_required_to_remove"],
            shield_range=ef["base"]["shield_range"],
            shield_per_unit=ef["base"]["shield_per_unit"],
            shield_bonus_per_y=ef["base"]["shield_bonus_per_y"],
        )
        self.support_upg = StructureSpec(
            hp=ef["upgrade"]["hp"],
            cost_sp=ef["upgrade"]["cost_sp"],
            refund_pct=ef["upgrade"]["refund_pct"],
            turns_required_to_remove=ef["upgrade"]["turns_required_to_remove"],
            shield_range=ef["upgrade"]["shield_range"],
            shield_per_unit=ef["upgrade"]["shield_per_unit"],
            shield_bonus_per_y=ef["upgrade"]["shield_bonus_per_y"],
        )

        df = derived["DF"]
        self.turret_base = StructureSpec(
            hp=df["base"]["hp"],
            cost_sp=df["base"]["cost_sp"],
            refund_pct=df["base"]["refund_pct"],
            turns_required_to_remove=df["base"]["turns_required_to_remove"],
            attack_range=df["base"]["attack_range"],
            attack_damage_walker=df["base"]["attack_damage_walker"],
            attack_damage_tower=df["base"]["attack_damage_tower"],
        )
        self.turret_upg = StructureSpec(
            hp=df["upgrade"]["hp"],
            cost_sp=df["upgrade"]["cost_sp"],
            refund_pct=df["upgrade"]["refund_pct"],
            turns_required_to_remove=df["upgrade"]["turns_required_to_remove"],
            attack_range=df["upgrade"]["attack_range"],
            attack_damage_walker=df["upgrade"]["attack_damage_walker"],
            attack_damage_tower=df["upgrade"]["attack_damage_tower"],
        )

        # ---- mobile units (not upgradeable)
        def mob(d: Dict[str, Any]) -> MobileSpec:
            return MobileSpec(
                hp=d["hp"], cost_mp=d["cost_mp"],
                attack_range=d["attack_range"],
                attack_damage_walker=d["attack_damage_walker"],
                attack_damage_tower=d["attack_damage_tower"],
                speed=d["speed"],
                self_destruct_range=d["self_destruct_range"],
                self_destruct_damage_walker=d["self_destruct_damage_walker"],
                self_destruct_damage_tower=d["self_destruct_damage_tower"],
                self_destruct_steps_required=d["self_destruct_steps_required"],
                breach_damage=d["breach_damage"],
                metal_for_breach=d["metal_for_breach"],
            )
        self.scout = mob(derived["PI"])
        self.demolisher = mob(derived["EI"])
        self.interceptor = mob(derived["SI"])

        # ---- resources
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
