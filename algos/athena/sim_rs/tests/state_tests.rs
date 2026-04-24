//! Integration tests for `sim_rs::state` + `sim_rs::config`.
//!
//! These cover:
//! * Round-trip Debug / Clone on every field of every state struct.
//! * SimState methods: struct_at, mobiles_at, is_occupied, player_stats,
//!   enemy_player, remove_structure.
//! * SimConfig::load against the real
//!   `algos/athena/data/citadel_config_snapshot.json`, with three acceptance
//!   assertions nailing down the copy-then-patch semantics:
//!     - EF upgrade cost = 4.0 (INHERITED from base — upgrade JSON has no cost1)
//!     - FF upgrade cost = 2.0 (EXPLICIT in upgrade JSON)
//!     - DF upgrade attack_range = 3.5 (vs base 2.5)

use std::path::PathBuf;

use sim_rs::config::{
    SimConfig, IDX_DEMOLISHER, IDX_INTERCEPTOR, IDX_SCOUT, IDX_SUPPORT, IDX_TURRET, IDX_WALL,
    SH_DEMOLISHER, SH_INTERCEPTOR, SH_SCOUT, SH_SUPPORT, SH_TURRET, SH_WALL,
};
use sim_rs::state::{ActionResult, Mobile, PlayerStats, SimState, Structure};

fn snapshot_path() -> PathBuf {
    // tests/ runs with CARGO_MANIFEST_DIR = sim_rs/. Walk up to repo root,
    // then into algos/athena/data/. Four `..` takes us
    // sim_rs → athena → algos → CitadelTerminal (repo root).
    let mut p = PathBuf::from(env!("CARGO_MANIFEST_DIR"));
    p.push("..");
    p.push("data");
    p.push("citadel_config_snapshot.json");
    p
}

// ------------------------------------------------------------- state structs

#[test]
fn structure_roundtrip_debug_and_clone() {
    let s = Structure {
        xy: (13, 5),
        type_idx: IDX_TURRET,
        upgraded: true,
        hp: 99.5,
        uid: "u-42".to_string(),
        player: 1,
        turn_start_removal: Some(17),
        shielded_already: vec!["s-1".to_string(), "s-2".to_string()],
    };
    let c = s.clone();
    assert_eq!(s.xy, c.xy);
    assert_eq!(s.type_idx, c.type_idx);
    assert_eq!(s.upgraded, c.upgraded);
    assert_eq!(s.hp, c.hp);
    assert_eq!(s.uid, c.uid);
    assert_eq!(s.player, c.player);
    assert_eq!(s.turn_start_removal, c.turn_start_removal);
    assert_eq!(s.shielded_already, c.shielded_already);
    // Debug formatter must not panic and must include the UID string.
    let dbg = format!("{:?}", s);
    assert!(dbg.contains("u-42"));
}

#[test]
fn mobile_roundtrip_debug_and_clone() {
    let m = Mobile {
        xy: (3, 4),
        type_idx: IDX_SCOUT,
        hp: 12.5,
        shield: 3.25,
        uid: "m-7".to_string(),
        player: 2,
        spawn_xy: (0, 13),
        target_edge: 3,
        steps_taken: 9,
        move_buildup: 0.5,
        last_move: 1,
        finished_navigating: true,
        reached_target: false,
        breached: true,
    };
    let c = m.clone();
    assert_eq!(m.xy, c.xy);
    assert_eq!(m.type_idx, c.type_idx);
    assert_eq!(m.hp, c.hp);
    assert_eq!(m.shield, c.shield);
    assert_eq!(m.uid, c.uid);
    assert_eq!(m.player, c.player);
    assert_eq!(m.spawn_xy, c.spawn_xy);
    assert_eq!(m.target_edge, c.target_edge);
    assert_eq!(m.steps_taken, c.steps_taken);
    assert_eq!(m.move_buildup, c.move_buildup);
    assert_eq!(m.last_move, c.last_move);
    assert_eq!(m.finished_navigating, c.finished_navigating);
    assert_eq!(m.reached_target, c.reached_target);
    assert_eq!(m.breached, c.breached);
    let dbg = format!("{:?}", m);
    assert!(dbg.contains("m-7"));
}

#[test]
fn player_stats_roundtrip() {
    let p = PlayerStats { hp: 40.0, sp: 8.0, mp: 1.0 };
    let c = p.clone();
    assert_eq!(p.hp, c.hp);
    assert_eq!(p.sp, c.sp);
    assert_eq!(p.mp, c.mp);
    let dbg = format!("{:?}", p);
    assert!(dbg.contains("40"));
}

#[test]
fn sim_state_new_defaults() {
    let s = SimState::new(0);
    assert_eq!(s.turn, 0);
    assert_eq!(s.p1.hp, 40.0);
    assert_eq!(s.p1.sp, 8.0);
    assert_eq!(s.p1.mp, 1.0);
    assert_eq!(s.p2.hp, 40.0);
    assert_eq!(s.structures.len(), 0);
    assert_eq!(s.mobiles.len(), 0);
    assert_eq!(s.pending_removal_xys.len(), 0);
}

#[test]
fn sim_state_helpers_and_cloning() {
    let mut s = SimState::new(3);

    // Seed one structure and two mobiles (one colocated).
    let turret = Structure {
        xy: (13, 5),
        type_idx: IDX_TURRET,
        upgraded: false,
        hp: 60.0,
        uid: "t-1".to_string(),
        player: 1,
        turn_start_removal: None,
        shielded_already: Vec::new(),
    };
    s.structures.insert((13, 5), turret.clone());

    s.mobiles.push(Mobile {
        xy: (4, 4),
        type_idx: IDX_SCOUT,
        hp: 15.0,
        shield: 0.0,
        uid: "m-a".to_string(),
        player: 1,
        spawn_xy: (0, 13),
        target_edge: 3,
        steps_taken: 0,
        move_buildup: 0.0,
        last_move: 0,
        finished_navigating: false,
        reached_target: false,
        breached: false,
    });
    s.mobiles.push(Mobile {
        xy: (4, 4),
        type_idx: IDX_SCOUT,
        hp: 15.0,
        shield: 0.0,
        uid: "m-b".to_string(),
        player: 1,
        spawn_xy: (0, 13),
        target_edge: 3,
        steps_taken: 0,
        move_buildup: 0.0,
        last_move: 0,
        finished_navigating: false,
        reached_target: false,
        breached: false,
    });

    assert!(s.is_occupied((13, 5)));
    assert!(!s.is_occupied((0, 0)));
    assert_eq!(s.struct_at((13, 5)).unwrap().uid, "t-1");
    assert!(s.struct_at((0, 0)).is_none());

    let colocated = s.mobiles_at((4, 4));
    assert_eq!(colocated.len(), 2);
    assert_eq!(colocated[0].uid, "m-a");
    assert_eq!(colocated[1].uid, "m-b");

    assert_eq!(s.player_stats(1).hp, 40.0);
    assert_eq!(s.player_stats(2).hp, 40.0);
    s.player_stats_mut(1).hp = 20.0;
    assert_eq!(s.player_stats(1).hp, 20.0);

    assert_eq!(s.enemy_player(1), 2);
    assert_eq!(s.enemy_player(2), 1);

    // Clone: cross_validate.py snapshot use-case.
    let snapshot = s.clone();

    // Mutate the original; the snapshot must remain unchanged.
    s.remove_structure(&turret);
    assert!(!s.is_occupied((13, 5)));
    assert!(snapshot.is_occupied((13, 5)));
    assert_eq!(snapshot.structures.len(), 1);
    assert_eq!(s.structures.len(), 0);
}

#[test]
fn action_result_winner() {
    let mut s = SimState::new(0);
    // p1 ahead on HP.
    s.p1.hp = 30.0;
    s.p2.hp = 20.0;
    let ar = ActionResult {
        final_state: s.clone(),
        p1_damage_dealt: 20.0,
        p2_damage_dealt: 10.0,
        structure_damage_by_player: Default::default(),
        frame_events: Vec::new(),
        frame_count: 1,
    };
    assert_eq!(ar.winner(), Some(1));

    // Draw.
    let mut s2 = SimState::new(0);
    s2.p1.hp = 20.0;
    s2.p2.hp = 20.0;
    let ar2 = ActionResult {
        final_state: s2,
        p1_damage_dealt: 20.0,
        p2_damage_dealt: 20.0,
        structure_damage_by_player: Default::default(),
        frame_events: Vec::new(),
        frame_count: 1,
    };
    assert_eq!(ar2.winner(), None);

    // p2 killed p1.
    let mut s3 = SimState::new(0);
    s3.p1.hp = 0.0;
    s3.p2.hp = 5.0;
    let ar3 = ActionResult {
        final_state: s3,
        p1_damage_dealt: 35.0,
        p2_damage_dealt: 40.0,
        structure_damage_by_player: Default::default(),
        frame_events: Vec::new(),
        frame_count: 1,
    };
    assert_eq!(ar3.winner(), Some(2));
}

// ------------------------------------------------------------------ config

#[test]
fn config_loads_from_real_snapshot() {
    let path = snapshot_path();
    assert!(
        path.exists(),
        "missing {}: run /inspect-config to regenerate",
        path.display()
    );
    let cfg = SimConfig::load(&path).expect("config load must succeed");

    // Sanity on resources block.
    assert_eq!(cfg.resources.starting_hp, 40.0);
    assert_eq!(cfg.resources.starting_sp, 8.0);
    assert_eq!(cfg.resources.starting_mp, 1.0);
    assert_eq!(cfg.resources.mp_cap, 150.0);
    assert_eq!(cfg.resources.mp_decay_per_round, 0.25);
    assert_eq!(cfg.resources.mp_income_per_round, 1.0);
    assert_eq!(cfg.resources.sp_income_per_round, 4.0);
    assert_eq!(cfg.resources.bit_ramp_interval, 5);
}

#[test]
fn config_support_upgrade_cost_is_4_sp() {
    // THIS is the core copy-then-patch test.
    // citadel_config_snapshot.json's EF.upgrade block has NO cost1 key, so the
    // Java engine (Config.java:184-189) copies the base cost1=4 into the
    // upgrade config. A naive loader would default missing upgrade fields to
    // 0 and report a 0-SP upgrade; that would silently break defense-budget
    // math across the entire sim.
    let cfg = SimConfig::load(&snapshot_path()).unwrap();

    let ef_base = cfg.structure_spec(IDX_SUPPORT, false);
    let ef_upg = cfg.structure_spec(IDX_SUPPORT, true);

    assert_eq!(ef_base.cost_sp, 4.0, "EF base cost should be 4 SP");
    assert_eq!(ef_upg.cost_sp, 4.0, "EF upgrade MUST inherit base 4 SP (copy-then-patch)");
    // Spot-check other inherited/overridden fields on the upgrade.
    assert_eq!(ef_upg.hp, 40.0, "EF upgrade HP is explicitly 40");
    assert_eq!(ef_upg.shield_range, 7.0, "EF upgrade shieldRange is explicitly 7.0");
    assert_eq!(ef_upg.shield_per_unit, 1.0, "EF upgrade shieldPerUnit is explicitly 1.0");
    assert_eq!(ef_upg.shield_bonus_per_y, 0.7, "EF upgrade shieldBonusPerY is explicitly 0.7");
}

#[test]
fn config_wall_upgrade_cost_is_2_sp() {
    // FF.upgrade has EXPLICIT cost1 = 2.0, so no inheritance needed. This
    // test guards the reverse: we don't accidentally drop the explicit
    // override and fall back to base cost1 = 1.0.
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let ff_base = cfg.structure_spec(IDX_WALL, false);
    let ff_upg = cfg.structure_spec(IDX_WALL, true);

    assert_eq!(ff_base.cost_sp, 1.0, "FF base cost is 1 SP");
    assert_eq!(ff_upg.cost_sp, 2.0, "FF upgrade cost is explicitly 2 SP");
    assert_eq!(ff_base.hp, 60.0);
    assert_eq!(ff_upg.hp, 200.0);
}

#[test]
fn config_turret_upgrade_range_is_35() {
    // DF.upgrade.attackRange = 3.5 is an EXPLICIT override. Citadel's
    // turret-upgrade range buff (2.5 → 3.5) is unusually strong vs the
    // base game; regressing this would break every defense-coverage
    // calculation in the sim.
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    let df_base = cfg.structure_spec(IDX_TURRET, false);
    let df_upg = cfg.structure_spec(IDX_TURRET, true);

    assert_eq!(df_base.attack_range, 2.5);
    assert_eq!(df_upg.attack_range, 3.5);
    assert_eq!(df_base.attack_damage_walker, 6.0);
    assert_eq!(df_upg.attack_damage_walker, 20.0);
    assert_eq!(df_upg.hp, 100.0);
    assert_eq!(df_upg.cost_sp, 4.0);
}

#[test]
fn config_mobile_specs_are_correct() {
    let cfg = SimConfig::load(&snapshot_path()).unwrap();
    // Scout (PI)
    let scout = cfg.mobile_spec(IDX_SCOUT);
    assert_eq!(scout.hp, 15.0);
    assert_eq!(scout.cost_mp, 1.0);
    assert_eq!(scout.speed, 1.0);
    assert_eq!(scout.attack_damage_walker, 2.0);
    assert_eq!(scout.attack_range, 3.5);
    assert_eq!(scout.self_destruct_damage_walker, 15.0);
    assert_eq!(scout.self_destruct_steps_required, 5);

    // Demolisher (EI)
    let demo = cfg.mobile_spec(IDX_DEMOLISHER);
    assert_eq!(demo.hp, 5.0);
    assert_eq!(demo.cost_mp, 3.0);
    assert_eq!(demo.speed, 0.5);
    assert_eq!(demo.attack_range, 4.5);
    assert_eq!(demo.attack_damage_walker, 8.0);

    // Interceptor (SI)
    let intr = cfg.mobile_spec(IDX_INTERCEPTOR);
    assert_eq!(intr.hp, 40.0);
    assert_eq!(intr.cost_mp, 1.0);
    assert_eq!(intr.speed, 0.25);
    assert_eq!(intr.attack_damage_walker, 20.0);
}

#[test]
fn config_shorthand_constants_match_python() {
    // Guard against silent renames; the live server uses pre-rename codes.
    assert_eq!(SH_WALL, "FF");
    assert_eq!(SH_SUPPORT, "EF");
    assert_eq!(SH_TURRET, "DF");
    assert_eq!(SH_SCOUT, "PI");
    assert_eq!(SH_DEMOLISHER, "EI");
    assert_eq!(SH_INTERCEPTOR, "SI");
}
