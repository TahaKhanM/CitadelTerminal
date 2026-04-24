//! Config loader — mirror `algos/athena/sim/config.py`.
//!
//! Engine citation: `Config.java:99-176 processUnitInfoInPlace` implements the
//! copy-then-patch semantics for unit upgrades — missing `upgrade.cost1` /
//! `upgrade.startHealth` / etc. fall back to the base value. The Rust loader
//! MUST mirror this exactly; a naive struct-literal default would silently
//! diverge on Support upgrade cost (live config omits `upgrade.cost1` → base
//! `cost1 = 4.0` inherits → upgrade cost is 4 SP, not 2 SP).

#[derive(Debug, Clone)]
pub struct StructureSpec {
    pub hp: f32,
    pub cost_sp: f32,
    pub refund_pct: f32,
    pub turns_required_to_remove: i32,
    pub attack_range: f32,
    pub attack_damage_walker: f32,
    pub attack_damage_tower: f32,
    pub shield_range: f32,
    pub shield_per_unit: f32,
    pub shield_bonus_per_y: f32,
    pub shield_decay: f32,
}

#[derive(Debug, Clone)]
pub struct MobileSpec {
    pub hp: f32,
    pub cost_mp: f32,
    pub attack_range: f32,
    pub attack_damage_walker: f32,
    pub attack_damage_tower: f32,
    pub speed: f32,
    pub self_destruct_range: f32,
    pub self_destruct_damage_walker: f32,
    pub self_destruct_damage_tower: f32,
    pub self_destruct_steps_required: i32,
    pub breach_damage: f32,
    pub metal_for_breach: f32,
}

#[derive(Debug, Clone)]
pub struct Resources {
    pub starting_hp: f32,
    pub starting_sp: f32,
    pub starting_mp: f32,
    pub mp_decay_per_round: f32,
    pub mp_income_per_round: f32,
    pub sp_income_per_round: f32,
    pub mp_cap: f32,
    pub bit_ramp_interval: i32,
    pub bit_ramp_cap_growth: f32,
    pub round_start_bit_ramp: i32,
    pub bit_growth_rate: f32,
}

#[derive(Debug, Clone)]
pub struct SimConfig {
    pub wall_base: StructureSpec,
    pub wall_upg: StructureSpec,
    pub support_base: StructureSpec,
    pub support_upg: StructureSpec,
    pub turret_base: StructureSpec,
    pub turret_upg: StructureSpec,
    pub scout: MobileSpec,
    pub demolisher: MobileSpec,
    pub interceptor: MobileSpec,
    pub resources: Resources,
}

// Loader implementation lands alongside the full system port. A JSON loader
// that reads citadel_config_snapshot.json and applies processUnitInfoInPlace
// semantics is scheduled in Phase 5 R1.
