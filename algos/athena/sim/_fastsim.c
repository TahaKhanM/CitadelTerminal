/*
 * _fastsim.c — C-level hot-path kernels for SimCore FAST mode.
 *
 * Exports:
 *   _fastsim.system_attack_c(state, config, events)
 *
 * Semantics match pysim.system_attack exactly (dual-mode parity gate
 * at algos/athena/sim/tests/test_mode_parity.py must still pass). The
 * function walks state.structures.values() / state.mobiles once,
 * builds per-player candidate lists, and for each attacker runs the
 * same priority-order pick + damage-apply the pure-Python port does.
 *
 * Float32 discipline is preserved by routing every HP/shield/damage
 * mutation through np.float32 arithmetic — we call the numpy C API
 * in only one place (damage application) and otherwise compare as C
 * doubles since the comparison result is equivalent across fp32/fp64.
 *
 * All object I/O goes through PyObject * — the structure/mobile
 * dataclass instances are still Python-level; the win is in the
 * inner filtering + picker loop, which this C code executes without
 * any mypyc type-boxing overhead.
 */

#define PY_SSIZE_T_CLEAN
#include <Python.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>


/* --- module-level cached state ---------------------------------------- */
/* Pre-interned attribute names so PyObject_GetAttr doesn't re-intern on
 * every call. Built once in PyInit_ below. */
static PyObject *PY_HP;
static PyObject *PY_SHIELD;
static PyObject *PY_XY;
static PyObject *PY_UID;
static PyObject *PY_PLAYER;
static PyObject *PY_TYPE_IDX;
static PyObject *PY_BREACHED;
static PyObject *PY_UPGRADED;
static PyObject *PY_STRUCTURES;
static PyObject *PY_MOBILES;
static PyObject *PY_SP_STRUCTS;  /* config._struct_specs */
static PyObject *PY_SP_MOBS;     /* config._mobile_specs */
static PyObject *PY_ATK_RANGE;
static PyObject *PY_ATK_DMG_W;
static PyObject *PY_ATK_DMG_T;
static PyObject *PY_STRUCT_GEN;
static PyObject *PY_ATK_CACHE;
static PyObject *PY_ATK_SPLIT_CACHE;

/* Event-dict keys, pre-interned. */
static PyObject *PY_TYPE;
static PyObject *PY_ATTACKER_UID;
static PyObject *PY_VICTIM_UID;
static PyObject *PY_DMG;
static PyObject *PY_FROM_XY;
static PyObject *PY_TO_XY;
static PyObject *PY_XY_KEY;
static PyObject *PY_UID_KEY;
static PyObject *PY_TYPE_IDX_KEY;
static PyObject *PY_PLAYER_KEY;
static PyObject *PY_REMOVED;
static PyObject *PY_EV_ATTACK;
static PyObject *PY_EV_DEATH;
static PyObject *PY_FALSE;

static PyObject *NP_FLOAT32;   /* np.float32 ctor */
static PyObject *PY_F32_ZERO;  /* FP32(0.0) */
static double F32_DEATH_EPS = 0.001;

/* Turret type index (IDX_TURRET = 2 in config.py). */
#define IDX_TURRET 2


/* Extract (x, y) ints from a 2-tuple. Returns 0 on success. */
static int xy_from(PyObject *xy_obj, long *x, long *y) {
    PyObject *xi = PyTuple_GetItem(xy_obj, 0);
    PyObject *yi = PyTuple_GetItem(xy_obj, 1);
    if (!xi || !yi) return -1;
    *x = PyLong_AsLong(xi);
    *y = PyLong_AsLong(yi);
    return 0;
}


/* Return double from a numeric PyObject (int, float, np.float32). */
static double as_double(PyObject *o) {
    return PyFloat_AsDouble(o);
}


/* Pick walker target — port of pysim._pick_target_walker.
 * Takes caller-collected candidate list. Returns new reference (or None). */
static PyObject *pick_walker(long ax, long ay, int att_player,
                              PyObject *candidates) {
    Py_ssize_t n = PyList_GET_SIZE(candidates);
    if (n == 0) { Py_RETURN_NONE; }
    double start_pos = (att_player == 1) ? 0.0 : 28.0;
    double closest_distance = 1.0e10;
    double closest_health = 1.0e10;
    double distance_to_player_start = 1.0e10;
    double distance_to_center = 0.0;
    long target_gid_int = -1;
    int has_winner = 0;
    PyObject *best = NULL;

    for (Py_ssize_t i = 0; i < n; i++) {
        PyObject *cand = PyList_GET_ITEM(candidates, i);
        PyObject *hp_obj = PyObject_GetAttr(cand, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *xy_obj = PyObject_GetAttr(cand, PY_XY);
        long cx, cy;
        if (xy_from(xy_obj, &cx, &cy) < 0) { Py_DECREF(xy_obj); return NULL; }
        Py_DECREF(xy_obj);
        long dx = cx - ax;
        long dy = cy - ay;
        double new_dist = sqrt((double)(dx*dx + dy*dy));
        int closer = new_dist < closest_distance;
        int equal_distance = new_dist == closest_distance;
        PyObject *sh_obj = PyObject_GetAttr(cand, PY_SHIELD);
        double shield_d = as_double(sh_obj);
        Py_DECREF(sh_obj);
        double new_hp_d = hp + shield_d;
        int less_hp = new_hp_d < closest_health;
        int equal_hp = new_hp_d == closest_health;
        double new_dist_start = fabs((double)cy - start_pos);
        int closer_to_start = new_dist_start < distance_to_player_start;
        int equal_to_start = new_dist_start == distance_to_player_start;
        double new_dist_center = fabs((double)cx - 13.5);
        int farther_from_center = new_dist_center > distance_to_center;
        int equal_from_center = new_dist_center == distance_to_center;
        int first_cond = closer
            || (equal_distance && less_hp)
            || (equal_distance && equal_hp && closer_to_start)
            || (equal_distance && equal_hp && equal_to_start && farther_from_center);
        if (first_cond) {
            best = cand;
            closest_distance = new_dist;
            closest_health = new_hp_d;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            PyObject *uid_obj = PyObject_GetAttr(cand, PY_UID);
            PyObject *uid_int = PyNumber_Long(uid_obj);
            if (uid_int) {
                target_gid_int = PyLong_AsLong(uid_int);
                Py_DECREF(uid_int);
            } else {
                PyErr_Clear();
                target_gid_int = -1;
            }
            Py_DECREF(uid_obj);
            has_winner = 1;
            continue;
        }
        if (!(equal_distance && equal_hp && equal_to_start && equal_from_center)) {
            continue;
        }
        long cand_gid_int;
        PyObject *uid_obj = PyObject_GetAttr(cand, PY_UID);
        PyObject *uid_int = PyNumber_Long(uid_obj);
        if (uid_int) {
            cand_gid_int = PyLong_AsLong(uid_int);
            Py_DECREF(uid_int);
        } else {
            PyErr_Clear();
            cand_gid_int = -1;
        }
        Py_DECREF(uid_obj);
        if (!has_winner) {
            best = cand;
            closest_distance = new_dist;
            closest_health = new_hp_d;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            target_gid_int = cand_gid_int;
            has_winner = 1;
            continue;
        }
        if (cand_gid_int > target_gid_int) {
            best = cand;
            closest_distance = new_dist;
            closest_health = new_hp_d;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            target_gid_int = cand_gid_int;
        }
    }
    if (best == NULL) { Py_RETURN_NONE; }
    Py_INCREF(best);
    return best;
}


/* Pick tower target — same as walker but structures have no shield. */
static PyObject *pick_tower(long ax, long ay, int att_player,
                             PyObject *candidates) {
    Py_ssize_t n = PyList_GET_SIZE(candidates);
    if (n == 0) { Py_RETURN_NONE; }
    double start_pos = (att_player == 1) ? 0.0 : 28.0;
    double closest_distance = 1.0e10;
    double closest_health = 1.0e10;
    double distance_to_player_start = 1.0e10;
    double distance_to_center = 0.0;
    long target_gid_int = -1;
    int has_winner = 0;
    PyObject *best = NULL;

    for (Py_ssize_t i = 0; i < n; i++) {
        PyObject *cand = PyList_GET_ITEM(candidates, i);
        PyObject *hp_obj = PyObject_GetAttr(cand, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *xy_obj = PyObject_GetAttr(cand, PY_XY);
        long cx, cy;
        if (xy_from(xy_obj, &cx, &cy) < 0) { Py_DECREF(xy_obj); return NULL; }
        Py_DECREF(xy_obj);
        long dx = cx - ax;
        long dy = cy - ay;
        double new_dist = sqrt((double)(dx*dx + dy*dy));
        int closer = new_dist < closest_distance;
        int equal_distance = new_dist == closest_distance;
        int less_hp = hp < closest_health;
        int equal_hp = hp == closest_health;
        double new_dist_start = fabs((double)cy - start_pos);
        int closer_to_start = new_dist_start < distance_to_player_start;
        int equal_to_start = new_dist_start == distance_to_player_start;
        double new_dist_center = fabs((double)cx - 13.5);
        int farther_from_center = new_dist_center > distance_to_center;
        int equal_from_center = new_dist_center == distance_to_center;
        int first_cond = closer
            || (equal_distance && less_hp)
            || (equal_distance && equal_hp && closer_to_start)
            || (equal_distance && equal_hp && equal_to_start && farther_from_center);
        if (first_cond) {
            best = cand;
            closest_distance = new_dist;
            closest_health = hp;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            PyObject *uid_obj = PyObject_GetAttr(cand, PY_UID);
            PyObject *uid_int = PyNumber_Long(uid_obj);
            if (uid_int) {
                target_gid_int = PyLong_AsLong(uid_int);
                Py_DECREF(uid_int);
            } else {
                PyErr_Clear();
                target_gid_int = -1;
            }
            Py_DECREF(uid_obj);
            has_winner = 1;
            continue;
        }
        if (!(equal_distance && equal_hp && equal_to_start && equal_from_center)) {
            continue;
        }
        long cand_gid_int;
        PyObject *uid_obj = PyObject_GetAttr(cand, PY_UID);
        PyObject *uid_int = PyNumber_Long(uid_obj);
        if (uid_int) {
            cand_gid_int = PyLong_AsLong(uid_int);
            Py_DECREF(uid_int);
        } else {
            PyErr_Clear();
            cand_gid_int = -1;
        }
        Py_DECREF(uid_obj);
        if (!has_winner) {
            best = cand;
            closest_distance = new_dist;
            closest_health = hp;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            target_gid_int = cand_gid_int;
            has_winner = 1;
            continue;
        }
        if (cand_gid_int > target_gid_int) {
            best = cand;
            closest_distance = new_dist;
            closest_health = hp;
            distance_to_player_start = new_dist_start;
            distance_to_center = new_dist_center;
            target_gid_int = cand_gid_int;
        }
    }
    if (best == NULL) { Py_RETURN_NONE; }
    Py_INCREF(best);
    return best;
}


/* Apply damage: target.hp/shield mutated in place. Returns 1 if died.
 * Mirrors _apply_damage. dmg_f32_in is the pre-allocated fp32 dmg
 * value from the attacker's spec — we reuse it rather than building
 * a new np.float32 per hit. The caller holds one ref; we incref the
 * returned *out_dmg_f32 (which we just alias to dmg_f32_in). */
static int apply_damage(PyObject *target, PyObject *dmg_f32_in,
                         double dmg, int is_walker,
                         PyObject **out_dmg_f32) {
    PyObject *hp_obj = PyObject_GetAttr(target, PY_HP);
    double hp = as_double(hp_obj);
    Py_INCREF(dmg_f32_in);
    *out_dmg_f32 = dmg_f32_in;
    if (dmg <= 0.0) {
        Py_DECREF(hp_obj);
        return 0;
    }

    int died = 0;
    if (is_walker) {
        PyObject *sh_obj = PyObject_GetAttr(target, PY_SHIELD);
        double shield_d = as_double(sh_obj);
        if (shield_d >= dmg) {
            /* Shield absorbs — new_shield = shield - dmg (in fp32). */
            PyObject *new_sh = PyNumber_Subtract(sh_obj, dmg_f32_in);
            PyObject_SetAttr(target, PY_SHIELD, new_sh);
            Py_DECREF(new_sh);
            Py_DECREF(sh_obj);
        } else {
            /* Shield wiped; remainder hits HP. new_hp = hp - (dmg - shield) in fp32. */
            double new_hp_d = hp - (dmg - shield_d);
            died = (new_hp_d <= F32_DEATH_EPS) && (hp > 0.0);
            PyObject *diff = PyNumber_Subtract(dmg_f32_in, sh_obj);
            Py_DECREF(sh_obj);
            PyObject *new_hp = PyNumber_Subtract(hp_obj, diff);
            Py_DECREF(diff);
            PyObject_SetAttr(target, PY_HP, new_hp);
            Py_DECREF(new_hp);
            PyObject_SetAttr(target, PY_SHIELD, PY_F32_ZERO);
        }
    } else {
        /* Tower — no shield. */
        double new_hp_d = hp - dmg;
        died = (new_hp_d <= F32_DEATH_EPS) && (hp > 0.0);
        PyObject *new_hp = PyNumber_Subtract(hp_obj, dmg_f32_in);
        PyObject_SetAttr(target, PY_HP, new_hp);
        Py_DECREF(new_hp);
    }
    Py_DECREF(hp_obj);
    return died;
}


/* Emit attack event (and optional death). dmg_f32 is stolen. */
static int emit_attack(PyObject *events, PyObject *att_uid_str,
                        PyObject *target, long ax, long ay, PyObject *dmg_f32,
                        int died) {
    PyObject *tgt_uid = PyObject_GetAttr(target, PY_UID);
    PyObject *tgt_xy = PyObject_GetAttr(target, PY_XY);
    PyObject *from_xy = PyTuple_Pack(2, PyLong_FromLong(ax), PyLong_FromLong(ay));
    PyObject *attack = PyDict_New();
    PyDict_SetItem(attack, PY_TYPE, PY_EV_ATTACK);
    PyDict_SetItem(attack, PY_ATTACKER_UID, att_uid_str);
    PyDict_SetItem(attack, PY_VICTIM_UID, tgt_uid);
    PyDict_SetItem(attack, PY_DMG, dmg_f32);
    PyDict_SetItem(attack, PY_FROM_XY, from_xy);
    PyDict_SetItem(attack, PY_TO_XY, tgt_xy);
    PyList_Append(events, attack);
    Py_DECREF(attack);
    Py_DECREF(from_xy);
    Py_DECREF(dmg_f32);
    if (died) {
        PyObject *tgt_type = PyObject_GetAttr(target, PY_TYPE_IDX);
        PyObject *tgt_player = PyObject_GetAttr(target, PY_PLAYER);
        PyObject *death = PyDict_New();
        PyDict_SetItem(death, PY_TYPE, PY_EV_DEATH);
        PyDict_SetItem(death, PY_XY_KEY, tgt_xy);
        PyDict_SetItem(death, PY_UID_KEY, tgt_uid);
        PyDict_SetItem(death, PY_TYPE_IDX_KEY, tgt_type);
        PyDict_SetItem(death, PY_PLAYER_KEY, tgt_player);
        PyDict_SetItem(death, PY_REMOVED, PY_FALSE);
        PyList_Append(events, death);
        Py_DECREF(tgt_type);
        Py_DECREF(tgt_player);
        Py_DECREF(death);
    }
    Py_DECREF(tgt_uid);
    Py_DECREF(tgt_xy);
    return 0;
}


/* Filter candidates in range: for each item in src, check hp > 0 and
 * dx²+dy² <= r_sq, and if so append to dst. dst must be a mutable list. */
static int filter_in_range(PyObject *src_list, long ax, long ay, double r_sq,
                             PyObject *dst_list, PyObject *skip) {
    Py_ssize_t n = PyList_GET_SIZE(src_list);
    for (Py_ssize_t i = 0; i < n; i++) {
        PyObject *item = PyList_GET_ITEM(src_list, i);
        if (item == skip) continue;
        PyObject *hp_obj = PyObject_GetAttr(item, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *xy_obj = PyObject_GetAttr(item, PY_XY);
        long cx, cy;
        if (xy_from(xy_obj, &cx, &cy) < 0) { Py_DECREF(xy_obj); return -1; }
        Py_DECREF(xy_obj);
        long dx = cx - ax;
        long dy = cy - ay;
        double d_sq = (double)(dx*dx + dy*dy);
        if (d_sq > r_sq) continue;
        PyList_Append(dst_list, item);
    }
    return 0;
}


/* system_attack_c(state, config, events) — C port of pysim.system_attack.
 * Preserves float32 arithmetic at damage-application sites and emits
 * the same attack/death event shapes the Python version does.
 *
 * Perf: per-frame split builds parallel C arrays of struct_xys and
 * struct_hps alongside the PyList, so the inner range-check + alive
 * filter avoids a PyObject_GetAttr per probe. */
static PyObject *
system_attack_c(PyObject *self, PyObject *args) {
    PyObject *state, *config, *events;
    if (!PyArg_ParseTuple(args, "OOO", &state, &config, &events)) return NULL;

    PyObject *structs_dict = PyObject_GetAttr(state, PY_STRUCTURES);
    PyObject *mobiles_list = PyObject_GetAttr(state, PY_MOBILES);
    PyObject *struct_specs = PyObject_GetAttr(config, PY_SP_STRUCTS);
    PyObject *mobile_specs = PyObject_GetAttr(config, PY_SP_MOBS);
    /* turret_base_spec, turret_upg_spec = struct_specs[IDX_TURRET] */
    PyObject *turret_pair = PyList_GetItem(struct_specs, IDX_TURRET);
    PyObject *turret_base_spec = PyTuple_GetItem(turret_pair, 0);
    PyObject *turret_upg_spec = PyTuple_GetItem(turret_pair, 1);

    PyObject *turrets_p1 = PyList_New(0);
    PyObject *turrets_p2 = PyList_New(0);
    PyObject *structs_p1 = PyList_New(0);
    PyObject *structs_p2 = PyList_New(0);
    PyObject *mobiles_p1 = PyList_New(0);
    PyObject *mobiles_p2 = PyList_New(0);

    /* Iterate structures.values() */
    PyObject *values = PyDict_Values(structs_dict);
    Py_ssize_t n_structs = PyList_GET_SIZE(values);
    for (Py_ssize_t i = 0; i < n_structs; i++) {
        PyObject *s = PyList_GET_ITEM(values, i);
        PyObject *hp_obj = PyObject_GetAttr(s, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *type_obj = PyObject_GetAttr(s, PY_TYPE_IDX);
        long tidx = PyLong_AsLong(type_obj);
        Py_DECREF(type_obj);
        PyObject *player_obj = PyObject_GetAttr(s, PY_PLAYER);
        long player = PyLong_AsLong(player_obj);
        Py_DECREF(player_obj);
        if (tidx == IDX_TURRET) {
            if (player == 1) PyList_Append(turrets_p1, s);
            else PyList_Append(turrets_p2, s);
        }
        if (player == 1) PyList_Append(structs_p1, s);
        else PyList_Append(structs_p2, s);
    }
    Py_DECREF(values);


    Py_ssize_t n_mobs = PyList_GET_SIZE(mobiles_list);
    for (Py_ssize_t i = 0; i < n_mobs; i++) {
        PyObject *m = PyList_GET_ITEM(mobiles_list, i);
        PyObject *hp_obj = PyObject_GetAttr(m, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *player_obj = PyObject_GetAttr(m, PY_PLAYER);
        long player = PyLong_AsLong(player_obj);
        Py_DECREF(player_obj);
        if (player == 1) PyList_Append(mobiles_p1, m);
        else PyList_Append(mobiles_p2, m);
    }

    /* Access / rebuild per-turret struct cache. */
    PyObject *struct_gen_obj = PyObject_GetAttr(state, PY_STRUCT_GEN);
    long struct_gen = PyLong_AsLong(struct_gen_obj);
    Py_DECREF(struct_gen_obj);
    PyObject *cache = PyObject_GetAttr(state, PY_ATK_CACHE);
    if (cache == Py_None) {
        Py_DECREF(cache);
        cache = PyDict_New();
        PyObject_SetAttr(state, PY_ATK_CACHE, cache);
    } else {
        PyObject *cache_gen = PyDict_GetItemString(cache, "_gen");
        if (cache_gen == NULL || PyLong_AsLong(cache_gen) != struct_gen) {
            Py_DECREF(cache);
            cache = PyDict_New();
            PyObject_SetAttr(state, PY_ATK_CACHE, cache);
        }
    }
    PyObject *cache_gen_obj = PyLong_FromLong(struct_gen);
    PyDict_SetItemString(cache, "_gen", cache_gen_obj);
    Py_DECREF(cache_gen_obj);

    /* Pre-read turret spec scalars (2 specs: base + upgraded) so the
     * per-turret loop doesn't repeat 3 PyObject_GetAttr calls per fire.
     * We cache both the double value (for C-level comparisons) and the
     * np.float32 object (for damage arithmetic via PyNumber_Subtract). */
    PyObject *tb_dw_obj = PyObject_GetAttr(turret_base_spec, PY_ATK_DMG_W);
    PyObject *tb_dt_obj = PyObject_GetAttr(turret_base_spec, PY_ATK_DMG_T);
    PyObject *tb_r_obj = PyObject_GetAttr(turret_base_spec, PY_ATK_RANGE);
    PyObject *tu_dw_obj = PyObject_GetAttr(turret_upg_spec, PY_ATK_DMG_W);
    PyObject *tu_dt_obj = PyObject_GetAttr(turret_upg_spec, PY_ATK_DMG_T);
    PyObject *tu_r_obj = PyObject_GetAttr(turret_upg_spec, PY_ATK_RANGE);
    double tb_dw = as_double(tb_dw_obj), tb_dt = as_double(tb_dt_obj), tb_r = as_double(tb_r_obj);
    double tu_dw = as_double(tu_dw_obj), tu_dt = as_double(tu_dt_obj), tu_r = as_double(tu_r_obj);


    /* Turret fires. */
    for (int side = 0; side < 2; side++) {
        PyObject *turret_list = (side == 0) ? turrets_p1 : turrets_p2;
        PyObject *enemy_mobiles = (side == 0) ? mobiles_p2 : mobiles_p1;
        PyObject *enemy_structs = (side == 0) ? structs_p2 : structs_p1;
        Py_ssize_t nt = PyList_GET_SIZE(turret_list);
        for (Py_ssize_t ti = 0; ti < nt; ti++) {
            PyObject *s = PyList_GET_ITEM(turret_list, ti);
            PyObject *upg_obj = PyObject_GetAttr(s, PY_UPGRADED);
            int upg = PyObject_IsTrue(upg_obj);
            Py_DECREF(upg_obj);
            PyObject *spec = upg ? turret_upg_spec : turret_base_spec;
            double dmg_walker = upg ? tu_dw : tb_dw;
            double dmg_tower = upg ? tu_dt : tb_dt;
            double r = upg ? tu_r : tb_r;
            PyObject *spec_dw = upg ? tu_dw_obj : tb_dw_obj;
            PyObject *spec_dt = upg ? tu_dt_obj : tb_dt_obj;
            if (dmg_walker <= 0.0 && dmg_tower <= 0.0) continue;
            PyObject *xy_obj = PyObject_GetAttr(s, PY_XY);
            long ax, ay;
            if (xy_from(xy_obj, &ax, &ay) < 0) { Py_DECREF(xy_obj); goto err; }
            Py_DECREF(xy_obj);
            PyObject *att_uid = PyObject_GetAttr(s, PY_UID);
            double r_sq = r * r + 1e-9;
            PyObject *att_player_obj = PyObject_GetAttr(s, PY_PLAYER);
            int att_player = (int)PyLong_AsLong(att_player_obj);
            Py_DECREF(att_player_obj);

            /* Walker candidates */
            PyObject *walker_candidates = PyList_New(0);
            if (dmg_walker > 0) {
                filter_in_range(enemy_mobiles, ax, ay, r_sq, walker_candidates, NULL);
            }
            PyObject *target = NULL;
            int is_walker = 0;
            if (PyList_GET_SIZE(walker_candidates) > 0) {
                target = pick_walker(ax, ay, att_player, walker_candidates);
                if (target == Py_None) { Py_DECREF(target); target = NULL; }
                is_walker = 1;
            }
            Py_DECREF(walker_candidates);

            if (target == NULL && dmg_tower > 0) {
                /* Enemy-structures-in-range cache, keyed by id(turret). */
                PyObject *turret_key = PyLong_FromVoidPtr((void*)s);
                PyObject *in_range = PyDict_GetItem(cache, turret_key);
                if (in_range == NULL) {
                    in_range = PyList_New(0);
                    /* Build: filter enemy_structs by d_sq <= r_sq, ignoring hp. */
                    Py_ssize_t m = PyList_GET_SIZE(enemy_structs);
                    for (Py_ssize_t k = 0; k < m; k++) {
                        PyObject *es = PyList_GET_ITEM(enemy_structs, k);
                        PyObject *xy_obj2 = PyObject_GetAttr(es, PY_XY);
                        long cx, cy;
                        if (xy_from(xy_obj2, &cx, &cy) < 0) { Py_DECREF(xy_obj2); goto err; }
                        Py_DECREF(xy_obj2);
                        long dx = cx - ax;
                        long dy = cy - ay;
                        double d_sq = (double)(dx*dx + dy*dy);
                        if (d_sq > r_sq) continue;
                        PyList_Append(in_range, es);
                    }
                    PyDict_SetItem(cache, turret_key, in_range);
                    Py_DECREF(in_range);
                    in_range = PyDict_GetItem(cache, turret_key);
                }
                Py_DECREF(turret_key);
                /* Filter to alive */
                PyObject *struct_candidates = PyList_New(0);
                Py_ssize_t k = PyList_GET_SIZE(in_range);
                for (Py_ssize_t j = 0; j < k; j++) {
                    PyObject *es = PyList_GET_ITEM(in_range, j);
                    PyObject *hp_obj = PyObject_GetAttr(es, PY_HP);
                    double hp = as_double(hp_obj);
                    Py_DECREF(hp_obj);
                    if (hp > 0.0) PyList_Append(struct_candidates, es);
                }
                target = pick_tower(ax, ay, att_player, struct_candidates);
                Py_DECREF(struct_candidates);
                if (target == Py_None) { Py_DECREF(target); target = NULL; }
                is_walker = 0;
            }

            if (target != NULL) {
                PyObject *dmg_f32 = NULL;
                /* Reuse the spec's pre-built fp32 damage scalar. */
                PyObject *spec_dmg = is_walker ? spec_dw : spec_dt;
                double dmg_d = is_walker ? dmg_walker : dmg_tower;
                int died = apply_damage(target, spec_dmg, dmg_d, is_walker, &dmg_f32);
                emit_attack(events, att_uid, target, ax, ay, dmg_f32, died);
                Py_DECREF(target);
            }
            Py_DECREF(att_uid);
        }
    }

    /* Mobile attackers. */
    for (Py_ssize_t i = 0; i < n_mobs; i++) {
        PyObject *m = PyList_GET_ITEM(mobiles_list, i);
        PyObject *br_obj = PyObject_GetAttr(m, PY_BREACHED);
        int br = PyObject_IsTrue(br_obj);
        Py_DECREF(br_obj);
        if (br) continue;
        PyObject *type_obj = PyObject_GetAttr(m, PY_TYPE_IDX);
        long tidx = PyLong_AsLong(type_obj);
        Py_DECREF(type_obj);
        PyObject *mspec = PyList_GetItem(mobile_specs, tidx);
        if (mspec == NULL || mspec == Py_None) continue;
        PyObject *spec_mw = PyObject_GetAttr(mspec, PY_ATK_DMG_W);
        PyObject *spec_mt = PyObject_GetAttr(mspec, PY_ATK_DMG_T);
        PyObject *r_obj = PyObject_GetAttr(mspec, PY_ATK_RANGE);
        double dmg_walker = as_double(spec_mw);
        double dmg_tower = as_double(spec_mt);
        double r = as_double(r_obj);
        Py_DECREF(r_obj);
        if (dmg_walker <= 0.0 && dmg_tower <= 0.0) {
            Py_DECREF(spec_mw); Py_DECREF(spec_mt);
            continue;
        }
        PyObject *xy_obj = PyObject_GetAttr(m, PY_XY);
        long ax, ay;
        if (xy_from(xy_obj, &ax, &ay) < 0) { Py_DECREF(xy_obj); goto err; }
        Py_DECREF(xy_obj);
        PyObject *att_uid = PyObject_GetAttr(m, PY_UID);
        double r_sq = r * r + 1e-9;
        PyObject *att_player_obj = PyObject_GetAttr(m, PY_PLAYER);
        int att_player = (int)PyLong_AsLong(att_player_obj);
        Py_DECREF(att_player_obj);

        PyObject *enemy_mobiles = (att_player == 1) ? mobiles_p2 : mobiles_p1;
        PyObject *enemy_structs = (att_player == 1) ? structs_p2 : structs_p1;

        PyObject *walker_candidates = PyList_New(0);
        if (dmg_walker > 0) {
            filter_in_range(enemy_mobiles, ax, ay, r_sq, walker_candidates, m);
        }
        PyObject *target = NULL;
        int is_walker = 0;
        if (PyList_GET_SIZE(walker_candidates) > 0) {
            target = pick_walker(ax, ay, att_player, walker_candidates);
            if (target == Py_None) { Py_DECREF(target); target = NULL; }
            is_walker = 1;
        }
        Py_DECREF(walker_candidates);

        if (target == NULL && dmg_tower > 0) {
            PyObject *struct_candidates = PyList_New(0);
            filter_in_range(enemy_structs, ax, ay, r_sq, struct_candidates, NULL);
            target = pick_tower(ax, ay, att_player, struct_candidates);
            Py_DECREF(struct_candidates);
            if (target == Py_None) { Py_DECREF(target); target = NULL; }
            is_walker = 0;
        }

        if (target != NULL) {
            PyObject *dmg_f32 = NULL;
            PyObject *spec_dmg = is_walker ? spec_mw : spec_mt;
            double dmg_d = is_walker ? dmg_walker : dmg_tower;
            int died = apply_damage(target, spec_dmg, dmg_d, is_walker, &dmg_f32);
            emit_attack(events, att_uid, target, ax, ay, dmg_f32, died);
            Py_DECREF(target);
        }
        Py_DECREF(att_uid);
        Py_DECREF(spec_mw); Py_DECREF(spec_mt);
    }

    Py_DECREF(tb_dw_obj); Py_DECREF(tb_dt_obj); Py_DECREF(tb_r_obj);
    Py_DECREF(tu_dw_obj); Py_DECREF(tu_dt_obj); Py_DECREF(tu_r_obj);
    Py_DECREF(turrets_p1);
    Py_DECREF(turrets_p2);
    Py_DECREF(structs_p1);
    Py_DECREF(structs_p2);
    Py_DECREF(mobiles_p1);
    Py_DECREF(mobiles_p2);
    Py_DECREF(structs_dict);
    Py_DECREF(mobiles_list);
    Py_DECREF(struct_specs);
    Py_DECREF(mobile_specs);
    Py_DECREF(cache);

    Py_RETURN_NONE;

err:
    Py_XDECREF(turrets_p1);
    Py_XDECREF(turrets_p2);
    Py_XDECREF(structs_p1);
    Py_XDECREF(structs_p2);
    Py_XDECREF(mobiles_p1);
    Py_XDECREF(mobiles_p2);
    Py_XDECREF(structs_dict);
    Py_XDECREF(mobiles_list);
    Py_XDECREF(struct_specs);
    Py_XDECREF(mobile_specs);
    Py_XDECREF(cache);
    return NULL;
}


/* system_move_c(state, config, events)
 *
 * C port of pysim.system_move. For each mobile, advances its
 * move_buildup by spec.speed; if buildup >= 0.9999, subtracts 1.0 and
 * calls pathfinder.get_step to advance one tile. Mirrors the
 * finished_navigating + reached_target gates. Uses the per-sim
 * (target_edge, xy, last_move) step cache to skip get_step BFS on
 * repeat visits; cache invalidates on _structure_gen bump.
 */
static PyObject *PY_MOBILE_SPEED;
static PyObject *PY_MOBILE_BUILDUP;
static PyObject *PY_MOBILE_LAST_MOVE;
static PyObject *PY_MOBILE_FIN_NAV;
static PyObject *PY_MOBILE_REACHED;
static PyObject *PY_MOBILE_STEPS;
static PyObject *PY_MOBILE_TARGET_EDGE;
static PyObject *PY_PATHFINDERS;
static PyObject *PY_MOVE_STEP_CACHE;
static PyObject *PY_GET_STEP;
static PyObject *PY_MOVE_EVENT_TYPE;  /* "move" */
static PyObject *PY_MOVE_UID_KEY;     /* "uid" */
static PyObject *PY_MOVE_FROM_KEY;    /* "from" */
static PyObject *PY_MOVE_TO_KEY;      /* "to" */
static PyObject *PY_EV_MOVE;          /* intern "move" */

/* Precomputed edge-tile sets for each target_edge (0..3). Populated
 * lazily on first system_move_c call. Edge 0=TOP_RIGHT, 1=TOP_LEFT,
 * 2=BOTTOM_LEFT, 3=BOTTOM_RIGHT — matches sim.map's EDGE_* constants. */
static PyObject *EDGE_SETS[4] = {NULL, NULL, NULL, NULL};
static int edge_sets_built = 0;

static int build_edge_sets(void) {
    if (edge_sets_built) return 0;
    PyObject *simmap = PyImport_ImportModule("sim.map");
    if (simmap == NULL) return -1;
    PyObject *edge_tiles = PyObject_GetAttrString(simmap, "edge_tiles");
    Py_DECREF(simmap);
    if (edge_tiles == NULL) return -1;
    for (int e = 0; e < 4; e++) {
        PyObject *arg = PyLong_FromLong(e);
        PyObject *tiles = PyObject_CallOneArg(edge_tiles, arg);
        Py_DECREF(arg);
        if (tiles == NULL) { Py_DECREF(edge_tiles); return -1; }
        /* Convert to a set of (x, y) tuples for O(1) membership. */
        PyObject *s = PySet_New(tiles);
        Py_DECREF(tiles);
        if (s == NULL) { Py_DECREF(edge_tiles); return -1; }
        EDGE_SETS[e] = s;
    }
    Py_DECREF(edge_tiles);
    edge_sets_built = 1;
    return 0;
}


#define MOVE_THRESHOLD 0.9999
#define HORIZONTAL 1
#define VERTICAL 2

static PyObject *
system_move_c(PyObject *self, PyObject *args) {
    PyObject *state, *config, *events;
    if (!PyArg_ParseTuple(args, "OOO", &state, &config, &events)) return NULL;

    if (build_edge_sets() < 0) return NULL;

    PyObject *mobile_specs = PyObject_GetAttr(config, PY_SP_MOBS);
    PyObject *mobiles_list = PyObject_GetAttr(state, PY_MOBILES);
    PyObject *pathfinders = PyObject_GetAttr(state, PY_PATHFINDERS);
    if (pathfinders == Py_None) {
        /* Lazily init pathfinders via the Python helper. */
        PyObject *pysim_mod = PyImport_ImportModule("sim.pysim");
        if (pysim_mod == NULL) goto err;
        PyObject *ensure_fn = PyObject_GetAttrString(pysim_mod, "_ensure_pathfinders");
        Py_DECREF(pysim_mod);
        if (ensure_fn == NULL) goto err;
        PyObject *tmp = PyObject_CallOneArg(ensure_fn, state);
        Py_DECREF(ensure_fn);
        if (tmp == NULL) goto err;
        Py_DECREF(tmp);
        Py_DECREF(pathfinders);
        pathfinders = PyObject_GetAttr(state, PY_PATHFINDERS);
    }

    /* Get/build step cache. */
    PyObject *struct_gen_obj = PyObject_GetAttr(state, PY_STRUCT_GEN);
    long struct_gen = PyLong_AsLong(struct_gen_obj);
    Py_DECREF(struct_gen_obj);
    PyObject *step_cache = PyObject_GetAttr(state, PY_MOVE_STEP_CACHE);
    int need_new = 0;
    if (step_cache == Py_None) {
        need_new = 1;
    } else {
        PyObject *gk = PyDict_GetItemString(step_cache, "_gen");
        if (gk == NULL || PyLong_AsLong(gk) != struct_gen) need_new = 1;
    }
    if (need_new) {
        Py_DECREF(step_cache);
        step_cache = PyDict_New();
        PyObject *gk = PyLong_FromLong(struct_gen);
        PyDict_SetItemString(step_cache, "_gen", gk);
        Py_DECREF(gk);
        PyObject_SetAttr(state, PY_MOVE_STEP_CACHE, step_cache);
    }

    Py_ssize_t n_mobs = PyList_GET_SIZE(mobiles_list);
    for (Py_ssize_t i = 0; i < n_mobs; i++) {
        PyObject *m = PyList_GET_ITEM(mobiles_list, i);
        PyObject *hp_obj = PyObject_GetAttr(m, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) continue;
        PyObject *fn_obj = PyObject_GetAttr(m, PY_MOBILE_FIN_NAV);
        int fn = PyObject_IsTrue(fn_obj);
        Py_DECREF(fn_obj);
        if (fn) continue;
        PyObject *tidx_obj = PyObject_GetAttr(m, PY_TYPE_IDX);
        long tidx = PyLong_AsLong(tidx_obj);
        Py_DECREF(tidx_obj);
        PyObject *spec = PyList_GetItem(mobile_specs, tidx);
        if (spec == NULL || spec == Py_None) continue;
        PyObject *speed_obj = PyObject_GetAttr(spec, PY_MOBILE_SPEED);
        double speed = as_double(speed_obj);
        Py_DECREF(speed_obj);
        PyObject *bu_obj = PyObject_GetAttr(m, PY_MOBILE_BUILDUP);
        double bu = as_double(bu_obj);
        Py_DECREF(bu_obj);
        bu += speed;
        if (bu < MOVE_THRESHOLD) {
            PyObject *new_bu = PyFloat_FromDouble(bu);
            PyObject_SetAttr(m, PY_MOBILE_BUILDUP, new_bu);
            Py_DECREF(new_bu);
            continue;
        }
        bu -= 1.0;
        PyObject *new_bu = PyFloat_FromDouble(bu);
        PyObject_SetAttr(m, PY_MOBILE_BUILDUP, new_bu);
        Py_DECREF(new_bu);

        PyObject *te_obj = PyObject_GetAttr(m, PY_MOBILE_TARGET_EDGE);
        long te = PyLong_AsLong(te_obj);
        Py_DECREF(te_obj);
        PyObject *xy_obj = PyObject_GetAttr(m, PY_XY);
        long mx, my;
        if (xy_from(xy_obj, &mx, &my) < 0) { Py_DECREF(xy_obj); goto err; }
        Py_DECREF(xy_obj);
        PyObject *lm_obj = PyObject_GetAttr(m, PY_MOBILE_LAST_MOVE);
        long lm = PyLong_AsLong(lm_obj);
        Py_DECREF(lm_obj);

        /* Step cache key: single int encoding (te, x, y, lm) — saves
         * ~4 allocations (3 tuples + 3 ints) per cache probe. Fits in
         * a long: te ∈ [0,3] (2 bits), x/y ∈ [0, 27] (5 bits each),
         * lm ∈ [0,2] (2 bits). Total 14 bits. */
        long key_val = (te << 12) | (lm << 10) | (mx << 5) | my;
        PyObject *cache_key = PyLong_FromLong(key_val);

        PyObject *cached = PyDict_GetItem(step_cache, cache_key);
        long nx, ny;
        if (cached == NULL) {
            /* Call pf.get_step(mx, my, lm) */
            PyObject *te_arg = PyLong_FromLong(te);
            PyObject *pf = PyObject_GetItem(pathfinders, te_arg);
            Py_DECREF(te_arg);
            if (pf == NULL) { Py_DECREF(cache_key); goto err; }
            PyObject *mx_arg = PyLong_FromLong(mx);
            PyObject *my_arg = PyLong_FromLong(my);
            PyObject *lm_arg = PyLong_FromLong(lm);
            PyObject *result = PyObject_CallMethodObjArgs(pf, PY_GET_STEP, mx_arg, my_arg, lm_arg, NULL);
            Py_DECREF(mx_arg); Py_DECREF(my_arg); Py_DECREF(lm_arg); Py_DECREF(pf);
            if (result == NULL) { Py_DECREF(cache_key); goto err; }
            PyObject *nx_o = PyTuple_GetItem(result, 0);
            PyObject *ny_o = PyTuple_GetItem(result, 1);
            nx = PyLong_AsLong(nx_o);
            ny = PyLong_AsLong(ny_o);
            PyDict_SetItem(step_cache, cache_key, result);
            Py_DECREF(result);
        } else {
            PyObject *nx_o = PyTuple_GetItem(cached, 0);
            PyObject *ny_o = PyTuple_GetItem(cached, 1);
            nx = PyLong_AsLong(nx_o);
            ny = PyLong_AsLong(ny_o);
        }
        Py_DECREF(cache_key);

        PyObject *new_lm = PyLong_FromLong((ny == my) ? HORIZONTAL : VERTICAL);
        PyObject_SetAttr(m, PY_MOBILE_LAST_MOVE, new_lm);
        Py_DECREF(new_lm);

        long dx = nx - mx;
        long dy = ny - my;
        if (dx == 0 && dy == 0) {
            PyObject_SetAttr(m, PY_MOBILE_FIN_NAV, Py_True);
            /* reached_target = m.xy in EDGE_SETS[te] */
            PyObject *cur_xy = PyTuple_Pack(2, PyLong_FromLong(mx), PyLong_FromLong(my));
            int in = (PySet_Contains(EDGE_SETS[te], cur_xy) == 1);
            Py_DECREF(cur_xy);
            PyObject_SetAttr(m, PY_MOBILE_REACHED, in ? Py_True : Py_False);
            continue;
        }
        /* Move body */
        PyObject *prev_xy = PyTuple_Pack(2, PyLong_FromLong(mx), PyLong_FromLong(my));
        PyObject *new_xy = PyTuple_Pack(2, PyLong_FromLong(nx), PyLong_FromLong(ny));
        PyObject_SetAttr(m, PY_XY, new_xy);
        PyObject *st_obj = PyObject_GetAttr(m, PY_MOBILE_STEPS);
        long st = PyLong_AsLong(st_obj);
        Py_DECREF(st_obj);
        PyObject *st_new = PyLong_FromLong(st + 1);
        PyObject_SetAttr(m, PY_MOBILE_STEPS, st_new);
        Py_DECREF(st_new);
        /* Emit move event */
        PyObject *uid_obj = PyObject_GetAttr(m, PY_UID);
        PyObject *ev = PyDict_New();
        PyDict_SetItem(ev, PY_TYPE, PY_EV_MOVE);
        PyDict_SetItem(ev, PY_MOVE_UID_KEY, uid_obj);
        PyDict_SetItem(ev, PY_MOVE_FROM_KEY, prev_xy);
        PyDict_SetItem(ev, PY_MOVE_TO_KEY, new_xy);
        PyList_Append(events, ev);
        Py_DECREF(uid_obj);
        Py_DECREF(ev);
        Py_DECREF(prev_xy);
        Py_DECREF(new_xy);
    }

    Py_DECREF(mobile_specs);
    Py_DECREF(mobiles_list);
    Py_DECREF(pathfinders);
    Py_DECREF(step_cache);
    Py_RETURN_NONE;
err:
    Py_XDECREF(mobile_specs);
    Py_XDECREF(mobiles_list);
    Py_XDECREF(pathfinders);
    Py_XDECREF(step_cache);
    return NULL;
}


/* clear_destroyed_c(state)
 *
 * C port of pysim.clear_destroyed. Scans state.structures for hp<=0,
 * pops them and bumps _structure_gen, then filters state.mobiles to
 * keep only hp>0. Mirrors the Python version exactly. */
static PyObject *
clear_destroyed_c(PyObject *self, PyObject *args) {
    PyObject *state;
    if (!PyArg_ParseTuple(args, "O", &state)) return NULL;

    PyObject *structs_dict = PyObject_GetAttr(state, PY_STRUCTURES);
    PyObject *pathfinders = PyObject_GetAttr(state, PY_PATHFINDERS);
    PyObject *mobiles_list = PyObject_GetAttr(state, PY_MOBILES);

    /* Find dead structures. Fast-path: iterate with PyDict_Next to
     * avoid building an intermediate list; only allocate to_kill if
     * at least one dead structure is found. The common case per frame
     * on mid_game is zero dead structures — this keeps that path
     * allocation-free. */
    PyObject *to_kill = NULL;
    Py_ssize_t pos = 0;
    PyObject *key, *value;
    while (PyDict_Next(structs_dict, &pos, &key, &value)) {
        PyObject *hp_obj = PyObject_GetAttr(value, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) {
            if (to_kill == NULL) to_kill = PyList_New(0);
            PyObject *xy_obj = PyObject_GetAttr(value, PY_XY);
            PyList_Append(to_kill, xy_obj);
            Py_DECREF(xy_obj);
        }
    }

    Py_ssize_t n_dead = (to_kill == NULL) ? 0 : PyList_GET_SIZE(to_kill);
    if (n_dead > 0) {
        /* Bump gen */
        PyObject *gen_obj = PyObject_GetAttr(state, PY_STRUCT_GEN);
        long gen = PyLong_AsLong(gen_obj);
        Py_DECREF(gen_obj);
        PyObject *new_gen = PyLong_FromLong(gen + 1);
        PyObject_SetAttr(state, PY_STRUCT_GEN, new_gen);
        Py_DECREF(new_gen);
        /* Pop each dead structure + remove from pathfinders. */
        PyObject *pf_values = NULL;
        if (pathfinders != Py_None) {
            pf_values = PyDict_Values(pathfinders);
        }
        for (Py_ssize_t i = 0; i < n_dead; i++) {
            PyObject *xy = PyList_GET_ITEM(to_kill, i);
            PyDict_DelItem(structs_dict, xy);
            if (pf_values != NULL) {
                long xx, yy;
                if (xy_from(xy, &xx, &yy) < 0) { Py_XDECREF(pf_values); goto err; }
                Py_ssize_t pn = PyList_GET_SIZE(pf_values);
                for (Py_ssize_t j = 0; j < pn; j++) {
                    PyObject *pf = PyList_GET_ITEM(pf_values, j);
                    PyObject *xa = PyLong_FromLong(xx);
                    PyObject *ya = PyLong_FromLong(yy);
                    PyObject *res = PyObject_CallMethod(pf, "remove", "OO", xa, ya);
                    Py_DECREF(xa); Py_DECREF(ya);
                    Py_XDECREF(res);
                }
            }
        }
        Py_XDECREF(pf_values);
    }
    Py_XDECREF(to_kill);

    /* Filter mobiles: keep hp > 0. */
    Py_ssize_t n_mob = PyList_GET_SIZE(mobiles_list);
    int any_dead = 0;
    for (Py_ssize_t i = 0; i < n_mob; i++) {
        PyObject *m = PyList_GET_ITEM(mobiles_list, i);
        PyObject *hp_obj = PyObject_GetAttr(m, PY_HP);
        double hp = as_double(hp_obj);
        Py_DECREF(hp_obj);
        if (hp <= 0.0) { any_dead = 1; break; }
    }
    if (any_dead) {
        PyObject *new_list = PyList_New(0);
        for (Py_ssize_t i = 0; i < n_mob; i++) {
            PyObject *m = PyList_GET_ITEM(mobiles_list, i);
            PyObject *hp_obj = PyObject_GetAttr(m, PY_HP);
            double hp = as_double(hp_obj);
            Py_DECREF(hp_obj);
            if (hp > 0.0) PyList_Append(new_list, m);
        }
        PyObject_SetAttr(state, PY_MOBILES, new_list);
        Py_DECREF(new_list);
    }

    Py_DECREF(structs_dict);
    Py_DECREF(pathfinders);
    Py_DECREF(mobiles_list);
    Py_RETURN_NONE;
err:
    Py_XDECREF(to_kill);
    Py_XDECREF(structs_dict);
    Py_XDECREF(pathfinders);
    Py_XDECREF(mobiles_list);
    return NULL;
}


static PyMethodDef FastSimMethods[] = {
    {"system_attack_c", system_attack_c, METH_VARARGS,
     "C port of pysim.system_attack."},
    {"system_move_c", system_move_c, METH_VARARGS,
     "C port of pysim.system_move."},
    {"clear_destroyed_c", clear_destroyed_c, METH_VARARGS,
     "C port of pysim.clear_destroyed."},
    {NULL, NULL, 0, NULL}
};


static struct PyModuleDef fastsim_module = {
    PyModuleDef_HEAD_INIT, "_fastsim", NULL, -1, FastSimMethods
};


PyMODINIT_FUNC
PyInit__fastsim(void) {
    PyObject *m = PyModule_Create(&fastsim_module);
    if (m == NULL) return NULL;

    /* Pre-intern attribute names. */
    PY_HP = PyUnicode_InternFromString("hp");
    PY_SHIELD = PyUnicode_InternFromString("shield");
    PY_XY = PyUnicode_InternFromString("xy");
    PY_UID = PyUnicode_InternFromString("uid");
    PY_PLAYER = PyUnicode_InternFromString("player");
    PY_TYPE_IDX = PyUnicode_InternFromString("type_idx");
    PY_BREACHED = PyUnicode_InternFromString("breached");
    PY_UPGRADED = PyUnicode_InternFromString("upgraded");
    PY_STRUCTURES = PyUnicode_InternFromString("structures");
    PY_MOBILES = PyUnicode_InternFromString("mobiles");
    PY_SP_STRUCTS = PyUnicode_InternFromString("_struct_specs");
    PY_SP_MOBS = PyUnicode_InternFromString("_mobile_specs");
    PY_ATK_RANGE = PyUnicode_InternFromString("attack_range");
    PY_ATK_DMG_W = PyUnicode_InternFromString("attack_damage_walker");
    PY_ATK_DMG_T = PyUnicode_InternFromString("attack_damage_tower");
    PY_STRUCT_GEN = PyUnicode_InternFromString("_structure_gen");
    PY_ATK_CACHE = PyUnicode_InternFromString("_attack_struct_cache");
    PY_ATK_SPLIT_CACHE = PyUnicode_InternFromString("_attack_split_cache");
    PY_TYPE = PyUnicode_InternFromString("type");
    PY_ATTACKER_UID = PyUnicode_InternFromString("attacker_uid");
    PY_VICTIM_UID = PyUnicode_InternFromString("victim_uid");
    PY_DMG = PyUnicode_InternFromString("dmg");
    PY_FROM_XY = PyUnicode_InternFromString("from_xy");
    PY_TO_XY = PyUnicode_InternFromString("to_xy");
    PY_XY_KEY = PyUnicode_InternFromString("xy");
    PY_UID_KEY = PyUnicode_InternFromString("uid");
    PY_TYPE_IDX_KEY = PyUnicode_InternFromString("type_idx");
    PY_PLAYER_KEY = PyUnicode_InternFromString("player");
    PY_REMOVED = PyUnicode_InternFromString("removed");
    PY_EV_ATTACK = PyUnicode_InternFromString("attack");
    PY_EV_DEATH = PyUnicode_InternFromString("death");
    PY_MOBILE_SPEED = PyUnicode_InternFromString("speed");
    PY_MOBILE_BUILDUP = PyUnicode_InternFromString("move_buildup");
    PY_MOBILE_LAST_MOVE = PyUnicode_InternFromString("last_move");
    PY_MOBILE_FIN_NAV = PyUnicode_InternFromString("finished_navigating");
    PY_MOBILE_REACHED = PyUnicode_InternFromString("reached_target");
    PY_MOBILE_STEPS = PyUnicode_InternFromString("steps_taken");
    PY_MOBILE_TARGET_EDGE = PyUnicode_InternFromString("target_edge");
    PY_PATHFINDERS = PyUnicode_InternFromString("pathfinders");
    PY_MOVE_STEP_CACHE = PyUnicode_InternFromString("_move_step_cache");
    PY_GET_STEP = PyUnicode_InternFromString("get_step");
    PY_MOVE_UID_KEY = PyUnicode_InternFromString("uid");
    PY_MOVE_FROM_KEY = PyUnicode_InternFromString("from");
    PY_MOVE_TO_KEY = PyUnicode_InternFromString("to");
    PY_EV_MOVE = PyUnicode_InternFromString("move");
    Py_INCREF(Py_False);
    PY_FALSE = Py_False;

    /* Import numpy.float32 for float32 arithmetic. */
    PyObject *np = PyImport_ImportModule("numpy");
    if (np == NULL) return NULL;
    NP_FLOAT32 = PyObject_GetAttrString(np, "float32");
    Py_DECREF(np);
    PyObject *zero = PyFloat_FromDouble(0.0);
    PY_F32_ZERO = PyObject_CallOneArg(NP_FLOAT32, zero);
    Py_DECREF(zero);

    return m;
}
