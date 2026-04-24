//! Config loader — mirror `algos/athena/sim/config.py`.
//!
//! Engine citation: `research/engine_decompiled/sources/com/c1games/terminal/
//! game/Config.java:99-189`. The `processUnitInfoInPlace` method implements
//! the copy-then-patch semantics for unit upgrades:
//!
//! * Lines 99-176: Each field is populated ONLY when present in the JSON;
//!   fields absent from JSON retain their previous value (which, for the
//!   base-unit pass, is the `UnitConfig` struct-literal zero-default).
//! * Lines 184-189: The upgrade variant is built by copying the fully-
//!   populated base `UnitConfig` and recursively calling
//!   `processUnitInfoInPlace` on the `upgrade` sub-dict. Because the copy
//!   starts from the base values (not zeros), any field missing in the
//!   `upgrade` dict INHERITS the base.
//!
//! The concrete consequence this loader is designed to preserve:
//! * `EF.upgrade` has no `cost1` key in the live Citadel config → upgrade
//!   inherits `EF.base.cost1 = 4.0`, so upgrading a Support costs 4 SP.
//!   A naive "zero default on missing upgrade field" would yield 0 SP.
//! * `FF.upgrade.cost1 = 2.0` IS explicit → Wall upgrade costs 2 SP
//!   (new for the Citadel comp; was free in the base game).
//!
//! Float precision: all f32 end-to-end to mirror engine's Java 32-bit floats
//! (see the Cluster I parity notes in `engine_semantics.md`).

use indexmap::IndexMap;
use serde_json::Value;
use std::fs::File;
use std::io::BufReader;
use std::path::Path;

// Shorthand constants — match the engine's on-wire values. Athena code that
// compares unit-type strings MUST use these, never the doc-level rename.
// See `memory/citadel_runtime_shorthands.md`.
pub const SH_WALL: &str = "FF";
pub const SH_SUPPORT: &str = "EF";
pub const SH_TURRET: &str = "DF";
pub const SH_SCOUT: &str = "PI";
pub const SH_DEMOLISHER: &str = "EI";
pub const SH_INTERCEPTOR: &str = "SI";
pub const SH_REMOVE: &str = "RM";
pub const SH_UPGRADE: &str = "UP";

pub const IDX_WALL: i32 = 0;
pub const IDX_SUPPORT: i32 = 1;
pub const IDX_TURRET: i32 = 2;
pub const IDX_SCOUT: i32 = 3;
pub const IDX_DEMOLISHER: i32 = 4;
pub const IDX_INTERCEPTOR: i32 = 5;
pub const IDX_REMOVE: i32 = 6;
pub const IDX_UPGRADE: i32 = 7;

pub const STRUCTURE_TYPES: [i32; 3] = [IDX_WALL, IDX_SUPPORT, IDX_TURRET];
pub const MOBILE_TYPES: [i32; 3] = [IDX_SCOUT, IDX_DEMOLISHER, IDX_INTERCEPTOR];

pub const ARENA: i32 = 28;
pub const HALF: i32 = 14;

// ------------------------------------------------------------------ specs

/// Gameplay-relevant fields of a structure unit. Engine citation:
/// `SpawnUnitsSystem.UnitConfig` (subset — we drop UI fields, which are
/// cosmetic per the Python loader's `_UI_FIELDS` list).
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

/// Gameplay-relevant fields of a mobile unit. Engine citation:
/// `SpawnUnitsSystem.UnitConfig` — mobiles use the walker-variant fields.
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

/// Resources block. Engine citation:
/// `game/resources/ResourcesComponent.java` + the verbatim JSON stored in
/// `citadel_config_snapshot.json::_resources_block_verbatim`.
#[derive(Debug, Clone)]
pub struct Resources {
    pub starting_hp: f32,
    pub starting_sp: f32,
    pub starting_mp: f32,
    /// Fraction; 0.25 = 25% MP decay per round before income.
    pub mp_decay_per_round: f32,
    /// Base MP income per round (`bitsPerRound`).
    pub mp_income_per_round: f32,
    /// Base SP income per round (`coresPerRound`).
    pub sp_income_per_round: f32,
    /// Hard cap on MP (`maxBits`).
    pub mp_cap: f32,
    /// Rounds per ramp increment (`turnIntervalForBitSchedule`).
    pub bit_ramp_interval: i32,
    pub bit_ramp_cap_growth: f32,
    pub round_start_bit_ramp: i32,
    /// Additional MP added per round as ramp applies (`bitGrowthRate`).
    pub bit_growth_rate: f32,
    /// Legacy pre-season-5 field; unused post-5 but kept for parity.
    pub cores_for_player_damage: f32,
}

/// Frozen view over `citadel_config_snapshot.json` with typed accessors.
///
/// Built from `_raw_unit_information` + `_resources_block_verbatim` only.
/// Derived specs are computed here — never read from any hand-maintained
/// derived lookup block (prevents the silent drift that historically
/// mispriced EF upgrade at 2 SP; see the Python loader docstring).
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

// ----------------------------------------------- processUnitInfoInPlace port
//
// `UnitFields` captures every gameplay-relevant field the Java
// `SpawnUnitsSystem.UnitConfig` holds (we drop UI-only fields: `icon`,
// `iconxScale`, `iconyScale`, `display`, `shorthand`, `unitCategory`).
//
// Defaults match the struct-literal zeros in `UnitConfig` — so a field
// missing from BOTH base JSON and upgrade JSON resolves to `0.0` / `0`,
// exactly as Java does. See Config.java:99-176.
#[derive(Debug, Clone, Default)]
struct UnitFields {
    metal_cost: f32,
    food_cost: f32,
    start_health: f32,
    get_hit_radius: f32,
    attack_range: f32,
    attack_damage_tower: f32,
    attack_damage_walker: f32,
    shield_range: f32,
    shield_per_unit: f32,
    shield_decay: f32,
    shield_bonus_per_y: f32,
    speed: f32,
    player_breach_damage: f32,
    metal_for_breach: f32,
    self_destruct_damage_walker: f32,
    self_destruct_damage_tower: f32,
    self_destruct_range: f32,
    self_destruct_steps_required: i32,
    refund_percentage: f32,
    turns_required_to_remove: i32,
    generates_resource1: f32,
    generates_resource2: f32,
}

/// Coerce a serde_json number (Java emits doubles for everything) to f32,
/// matching the Java `((Double)val).floatValue()` downcast in Config.java.
fn as_f32(v: &Value) -> Option<f32> {
    v.as_f64().map(|d| d as f32)
}

/// Coerce a serde_json number to i32, matching Java's
/// `((Double)val).intValue()` truncation for `turnsRequiredToRemove` and
/// `selfDestructStepsRequired`.
fn as_i32(v: &Value) -> Option<i32> {
    v.as_f64().map(|d| d as i32)
}

/// Mirror of Config.java:99-176 — for every field the Java code patches, we
/// patch IFF the JSON dict contains the key (and the value is not null).
/// Absent keys retain whatever was in `dst` (either zero-default for base or
/// the inherited base value for upgrades).
fn patch_fields(dst: &mut UnitFields, src: &Value) {
    let obj = match src.as_object() {
        Some(o) => o,
        None => return,
    };
    let non_null = |k: &str| -> Option<&Value> {
        match obj.get(k) {
            Some(v) if !v.is_null() => Some(v),
            _ => None,
        }
    };

    // Each `if let Some(...)` corresponds 1:1 to a `if (unitDict.containsKey("..."))`
    // block in Config.java:99-176. Order matches the Java source for auditability.
    if let Some(v) = non_null("cost1") { if let Some(f) = as_f32(v) { dst.metal_cost = f; } }
    if let Some(v) = non_null("cost2") { if let Some(f) = as_f32(v) { dst.food_cost = f; } }
    // startHealth → both startHealth and maxHealth in Java; we track as one field.
    if let Some(v) = non_null("startHealth") { if let Some(f) = as_f32(v) { dst.start_health = f; } }
    if let Some(v) = non_null("getHitRadius") { if let Some(f) = as_f32(v) { dst.get_hit_radius = f; } }
    if let Some(v) = non_null("attackRange") { if let Some(f) = as_f32(v) { dst.attack_range = f; } }
    if let Some(v) = non_null("attackDamageTower") { if let Some(f) = as_f32(v) { dst.attack_damage_tower = f; } }
    if let Some(v) = non_null("attackDamageWalker") { if let Some(f) = as_f32(v) { dst.attack_damage_walker = f; } }
    if let Some(v) = non_null("shieldRange") { if let Some(f) = as_f32(v) { dst.shield_range = f; } }
    if let Some(v) = non_null("shieldPerUnit") { if let Some(f) = as_f32(v) { dst.shield_per_unit = f; } }
    if let Some(v) = non_null("shieldDecay") { if let Some(f) = as_f32(v) { dst.shield_decay = f; } }
    if let Some(v) = non_null("shieldBonusPerY") { if let Some(f) = as_f32(v) { dst.shield_bonus_per_y = f; } }
    if let Some(v) = non_null("speed") { if let Some(f) = as_f32(v) { dst.speed = f; } }
    if let Some(v) = non_null("playerBreachDamage") { if let Some(f) = as_f32(v) { dst.player_breach_damage = f; } }
    if let Some(v) = non_null("metalForBreach") { if let Some(f) = as_f32(v) { dst.metal_for_breach = f; } }
    if let Some(v) = non_null("selfDestructDamageWalker") { if let Some(f) = as_f32(v) { dst.self_destruct_damage_walker = f; } }
    if let Some(v) = non_null("selfDestructDamageTower") { if let Some(f) = as_f32(v) { dst.self_destruct_damage_tower = f; } }
    if let Some(v) = non_null("selfDestructRange") { if let Some(f) = as_f32(v) { dst.self_destruct_range = f; } }
    if let Some(v) = non_null("selfDestructStepsRequired") { if let Some(i) = as_i32(v) { dst.self_destruct_steps_required = i; } }
    if let Some(v) = non_null("refundPercentage") { if let Some(f) = as_f32(v) { dst.refund_percentage = f; } }
    if let Some(v) = non_null("turnsRequiredToRemove") { if let Some(i) = as_i32(v) { dst.turns_required_to_remove = i; } }
    if let Some(v) = non_null("generatesResource1") { if let Some(f) = as_f32(v) { dst.generates_resource1 = f; } }
    if let Some(v) = non_null("generatesResource2") { if let Some(f) = as_f32(v) { dst.generates_resource2 = f; } }
}

/// Fill a fresh `UnitFields` from the base JSON, then (if present) build the
/// upgrade variant by CLONING the fully-populated base and patching the
/// upgrade sub-dict on top. Exactly mirrors Config.java:184-189.
///
/// Returns `(base, Some(upgrade))` when the unit has an `upgrade` block,
/// else `(base, None)` (for mobile units).
fn build_unit(unit_json: &Value) -> (UnitFields, Option<UnitFields>) {
    let mut base = UnitFields::default();
    patch_fields(&mut base, unit_json);

    let upgrade = unit_json
        .as_object()
        .and_then(|o| o.get("upgrade"))
        .filter(|v| v.is_object())
        .map(|upg| {
            // Copy-then-patch: start from fully-populated base, overlay upgrade fields.
            // This is the critical step: missing upgrade fields inherit base values
            // (e.g. EF.upgrade.cost1 absent → support upgrade costs base.cost1 = 4 SP).
            let mut upgraded = base.clone();
            patch_fields(&mut upgraded, upg);
            upgraded
        });
    (base, upgrade)
}

fn struct_from(u: &UnitFields) -> StructureSpec {
    StructureSpec {
        hp: u.start_health,
        cost_sp: u.metal_cost,
        refund_pct: u.refund_percentage,
        turns_required_to_remove: u.turns_required_to_remove,
        attack_range: u.attack_range,
        attack_damage_walker: u.attack_damage_walker,
        attack_damage_tower: u.attack_damage_tower,
        shield_range: u.shield_range,
        shield_per_unit: u.shield_per_unit,
        shield_bonus_per_y: u.shield_bonus_per_y,
        shield_decay: u.shield_decay,
    }
}

fn mobile_from(u: &UnitFields) -> MobileSpec {
    MobileSpec {
        hp: u.start_health,
        cost_mp: u.food_cost,
        attack_range: u.attack_range,
        attack_damage_walker: u.attack_damage_walker,
        attack_damage_tower: u.attack_damage_tower,
        speed: u.speed,
        self_destruct_range: u.self_destruct_range,
        self_destruct_damage_walker: u.self_destruct_damage_walker,
        self_destruct_damage_tower: u.self_destruct_damage_tower,
        self_destruct_steps_required: u.self_destruct_steps_required,
        breach_damage: u.player_breach_damage,
        metal_for_breach: u.metal_for_breach,
    }
}

// ----------------------------------------------------------- SimConfig impl

/// Load error for [`SimConfig::load`].
#[derive(Debug)]
pub enum ConfigError {
    Io(std::io::Error),
    Json(serde_json::Error),
    MissingKey(&'static str),
    MissingShorthand(&'static str),
    BadResource(&'static str),
}

impl std::fmt::Display for ConfigError {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            ConfigError::Io(e) => write!(f, "io error: {}", e),
            ConfigError::Json(e) => write!(f, "json parse error: {}", e),
            ConfigError::MissingKey(k) => write!(f, "citadel_config_snapshot.json missing {}; regenerate via /inspect-config", k),
            ConfigError::MissingShorthand(sh) => write!(f, "no unit with shorthand {} in _raw_unit_information", sh),
            ConfigError::BadResource(k) => write!(f, "_resources_block_verbatim missing or non-numeric {}", k),
        }
    }
}

impl std::error::Error for ConfigError {}

impl From<std::io::Error> for ConfigError {
    fn from(e: std::io::Error) -> Self { ConfigError::Io(e) }
}

impl From<serde_json::Error> for ConfigError {
    fn from(e: serde_json::Error) -> Self { ConfigError::Json(e) }
}

impl SimConfig {
    /// Load from a `citadel_config_snapshot.json` file.
    ///
    /// Engine citation: `Config.java:99-189 processUnitInfoInPlace`. We
    /// build an indexed map of shorthand → (base fields, optional upgrade
    /// fields), then derive typed specs. The JSON file itself is produced
    /// by the `/inspect-config` skill from a live `on_game_start` callback.
    pub fn load(path: &Path) -> Result<Self, ConfigError> {
        let file = File::open(path)?;
        let reader = BufReader::new(file);
        let raw: Value = serde_json::from_reader(reader)?;
        Self::from_json(&raw)
    }

    /// Build a config from an already-parsed JSON value — useful for tests.
    pub fn from_json(raw: &Value) -> Result<Self, ConfigError> {
        let units = raw
            .get("_raw_unit_information")
            .and_then(Value::as_array)
            .ok_or(ConfigError::MissingKey("_raw_unit_information"))?;

        // Shorthand → (base, upgrade) via insertion-ordered IndexMap.
        // IndexMap, NOT HashMap, because iteration order is part of the
        // parity contract for any downstream code that enumerates specs.
        let mut by_sh: IndexMap<String, (UnitFields, Option<UnitFields>)> = IndexMap::new();
        for u in units {
            let sh = match u.get("shorthand").and_then(Value::as_str) {
                Some(s) => s.to_string(),
                None => continue,
            };
            let (base, upg) = build_unit(u);
            by_sh.insert(sh, (base, upg));
        }

        // Helper that pulls the (base, upgrade) pair for a shorthand.
        let get = |sh: &'static str| -> Result<&(UnitFields, Option<UnitFields>), ConfigError> {
            by_sh.get(sh).ok_or(ConfigError::MissingShorthand(sh))
        };

        let (ff_base, ff_upg) = {
            let (b, u) = get(SH_WALL)?;
            (b.clone(), u.clone().unwrap_or_else(|| b.clone()))
        };
        let (ef_base, ef_upg) = {
            let (b, u) = get(SH_SUPPORT)?;
            (b.clone(), u.clone().unwrap_or_else(|| b.clone()))
        };
        let (df_base, df_upg) = {
            let (b, u) = get(SH_TURRET)?;
            (b.clone(), u.clone().unwrap_or_else(|| b.clone()))
        };
        let pi_base = get(SH_SCOUT)?.0.clone();
        let ei_base = get(SH_DEMOLISHER)?.0.clone();
        let si_base = get(SH_INTERCEPTOR)?.0.clone();

        // Resources block — verbatim numeric keys from the live server.
        let r = raw
            .get("_resources_block_verbatim")
            .and_then(Value::as_object)
            .ok_or(ConfigError::MissingKey("_resources_block_verbatim"))?;

        let rf = |k: &'static str| -> Result<f32, ConfigError> {
            r.get(k)
                .and_then(Value::as_f64)
                .map(|d| d as f32)
                .ok_or(ConfigError::BadResource(k))
        };
        let ri = |k: &'static str| -> Result<i32, ConfigError> {
            r.get(k)
                .and_then(Value::as_f64)
                .map(|d| d as i32)
                .ok_or(ConfigError::BadResource(k))
        };
        // coresForPlayerDamage is a legacy field not present in the live
        // Citadel config; default to 1.0 to match the Python loader's
        // `resources.get("coresForPlayerDamage", 1)`.
        let cores_for_player_damage = r
            .get("coresForPlayerDamage")
            .and_then(Value::as_f64)
            .map(|d| d as f32)
            .unwrap_or(1.0);

        let resources = Resources {
            starting_hp: rf("startingHP")?,
            starting_sp: rf("startingCores")?,
            starting_mp: rf("startingBits")?,
            mp_decay_per_round: rf("bitDecayPerRound")?,
            mp_income_per_round: rf("bitsPerRound")?,
            sp_income_per_round: rf("coresPerRound")?,
            mp_cap: rf("maxBits")?,
            bit_ramp_interval: ri("turnIntervalForBitSchedule")?,
            bit_ramp_cap_growth: rf("bitRampBitCapGrowthRate")?,
            round_start_bit_ramp: ri("roundStartBitRamp")?,
            bit_growth_rate: rf("bitGrowthRate")?,
            cores_for_player_damage,
        };

        Ok(Self {
            wall_base: struct_from(&ff_base),
            wall_upg: struct_from(&ff_upg),
            support_base: struct_from(&ef_base),
            support_upg: struct_from(&ef_upg),
            turret_base: struct_from(&df_base),
            turret_upg: struct_from(&df_upg),
            scout: mobile_from(&pi_base),
            demolisher: mobile_from(&ei_base),
            interceptor: mobile_from(&si_base),
            resources,
        })
    }

    /// Structure spec lookup by type index + upgrade state.
    ///
    /// Engine citation: mirrors `sim/config.py::SimConfig.structure_spec`.
    /// Panics (via `ValueError` in Python) when `type_idx` isn't a structure.
    pub fn structure_spec(&self, type_idx: i32, upgraded: bool) -> &StructureSpec {
        match (type_idx, upgraded) {
            (IDX_WALL, false)    => &self.wall_base,
            (IDX_WALL, true)     => &self.wall_upg,
            (IDX_SUPPORT, false) => &self.support_base,
            (IDX_SUPPORT, true)  => &self.support_upg,
            (IDX_TURRET, false)  => &self.turret_base,
            (IDX_TURRET, true)   => &self.turret_upg,
            _ => panic!("not a structure type: {}", type_idx),
        }
    }

    /// Mobile spec lookup by type index.
    ///
    /// Engine citation: mirrors `sim/config.py::SimConfig.mobile_spec`.
    pub fn mobile_spec(&self, type_idx: i32) -> &MobileSpec {
        match type_idx {
            IDX_SCOUT       => &self.scout,
            IDX_DEMOLISHER  => &self.demolisher,
            IDX_INTERCEPTOR => &self.interceptor,
            _ => panic!("not a mobile type: {}", type_idx),
        }
    }
}
