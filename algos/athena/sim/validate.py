"""SimCore Phase 1.B validator — per-frame, 23-column parity table.

Extends the Phase 1.A HP-delta gate to a full-state bit-exact gate. For
every action-phase frame of every ranked replay, SimCore's generated
frame is compared field-by-field against the engine's replay frame.

Columns (23; see ATHENA_BUILD_PLAN and the orientation checkpoint):
   stats: p1_hp, p2_hp, p1_sp, p2_sp, p1_mp, p2_mp          (6)
   units: p{1,2}_structures, p{1,2}_mobiles,
          p{1,2}_removal_pseudo, p{1,2}_upgrade_pseudo       (8)
   events: attack, breach, damage, death, melee, move,
           selfDestruct, shield, spawn                       (9)
Plus 1 turn-level metric: frame_count.

Per-column metric per (replay, turn, frame):
  None       = exact match
  (s, r)     = (sim_value, replay_value) — mismatch summary

Aggregate (per replay, and overall):
  max_err, mean_err, n_nonzero_frames

Gate: all 23 columns report 0 non-zero frames across the entire ranked
corpus.

Back-compat: the __main__ entry still runs the Phase 1.A HP-delta summary
(with the same output format) so existing regression-check scripts
continue to work. Pass --full to get the 23-column Phase 1.B table.
"""

from __future__ import annotations

import json
import sys
import time
from dataclasses import dataclass, field
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import (  # noqa: E402
    IDX_SUPPORT,
    IDX_TURRET,
    IDX_WALL,
    SimConfig,
)
from sim.pysim import (  # noqa: E402
    apply_deploy_actions,
    simulate_action_phase,
    simulate_action_phase_iter,
)
from sim.state import PlayerStats, SimState, Structure  # noqa: E402


# Replay HP/shield/damage/SP/MP values parse from JSON as Python doubles,
# but engine emits them as Java 32-bit floats — the string "10.799999" in
# the replay wire format represents a float32 bit pattern whose exact
# double is 10.799999237060547, not 10.799999 (which is a different
# double). SimCore stores everything as np.float32 (see config.FP32 and
# state.py notes); to compare, the validator quantizes replay float
# slots through np.float32 so both sides align on the float32 bit
# pattern. UIDs are Java long (cast to string here) and coordinates are
# int — those bypass this coercion entirely.
def _fp32_slots(entry: list, positions: Tuple[int, ...]) -> list:
    """Return a copy of `entry` with the given positions re-quantized
    through numpy.float32. No-op on positions outside the entry length."""
    if not isinstance(entry, (list, tuple)):
        return entry
    out = list(entry)
    for i in positions:
        if 0 <= i < len(out) and out[i] is not None:
            out[i] = np.float32(out[i])
    return out


# ---------------------------------------------------------------- loaders

def _parse_replay(path: Path) -> Tuple[List[dict], dict]:
    """Return (game_frames, config_frame). Strips the trailing summary line."""
    frames = []
    cfg = {}
    with open(path, "r", encoding="utf-8") as f:
        for ln in f:
            s = ln.strip()
            if not s.startswith("{"):
                continue
            try:
                d = json.loads(s)
            except Exception:
                continue
            if "unitInformation" in d and not cfg:
                cfg = d
            else:
                frames.append(d)
    return frames, cfg


def _index_deploy_frames(frames: List[dict]) -> Dict[int, dict]:
    """Return {turn_number: deploy_frame_dict}."""
    out = {}
    for f in frames:
        ti = f.get("turnInfo") or []
        if len(ti) >= 3 and ti[0] == 0 and ti[2] == -1:
            out[int(ti[1])] = f
    return out


def _index_action_frames(frames: List[dict]) -> Dict[int, List[dict]]:
    """Return {turn_number: [action_frame_0, action_frame_1, ...]}."""
    out: Dict[int, List[dict]] = {}
    for f in frames:
        ti = f.get("turnInfo") or []
        if len(ti) >= 3 and ti[0] == 1:
            out.setdefault(int(ti[1]), []).append(f)
    # Sort each turn's frames by actionFrame
    for t in out:
        out[t].sort(key=lambda f: f["turnInfo"][2])
    return out


def _index_first_action_frames(frames: List[dict]) -> Dict[int, dict]:
    """Return {turn_number: first phase-1 frame_num=0 for that turn}."""
    out = {}
    for f in frames:
        ti = f.get("turnInfo") or []
        if len(ti) >= 3 and ti[0] == 1 and ti[2] == 0 and ti[1] not in out:
            out[int(ti[1])] = f
    return out


# ------------------------------------------------------ state construction

def _build_state_from_deploy_frame(df: dict, config: SimConfig,
                                    upgraded_uids: Optional[set] = None) -> SimState:
    """Turn a replay deploy-phase snapshot into a SimState (structures + stats
    ONLY).

    `upgraded_uids` is a set of unit_ids that have been upgraded at any point
    before this deploy frame. A structure whose uid is in the set is
    instantiated as upgraded regardless of current HP (upgraded structures
    can be damaged below base-max-HP). If None, we fall back to the HP
    heuristic (hp > base max ⇒ upgraded), which misclassifies damaged
    upgraded structures.

    Structure insertion order matters: engine's DisplayUnitsSystem iterates
    game.gameObjects (single list, no per-type split) in insertion order,
    and Parser.parseVisiblesListToJson then buckets by type for wire
    emission. gameObjects is monotonically populated — every `new Gameobject`
    gets a fresh uid from Game.getNewID(), and gameObjects.remove(this) on
    destroy means the remaining items stay ordered by uid. So the faithful
    reconstruction of engine's gameObjects order for a mid-game snapshot
    is: take all live structures across all types and sort ascending by
    integer uid. This order drives state.structures dict insertion →
    _snapshot_units iteration → per-type bucket emission order."""
    p1 = df.get("p1Stats") or [40, 8, 1, 0]
    p2 = df.get("p2Stats") or [40, 8, 1, 0]
    # PlayerStats fields are np.float32 throughout the action phase; cast
    # at load so subsequent arithmetic stays in float32 (Cluster I).
    state = SimState(
        turn=int(df.get("turnInfo", [0, 0])[1] if df.get("turnInfo") else 0),
        structures={}, mobiles=[],
        p1=PlayerStats(hp=np.float32(p1[0]), sp=np.float32(p1[1]), mp=np.float32(p1[2])),
        p2=PlayerStats(hp=np.float32(p2[0]), sp=np.float32(p2[1]), mp=np.float32(p2[2])),
    )
    all_structures: List[Structure] = []
    for player, key in ((1, "p1Units"), (2, "p2Units")):
        units = df.get(key) or []
        for type_idx in (IDX_WALL, IDX_SUPPORT, IDX_TURRET):
            if type_idx >= len(units):
                continue
            for u in units[type_idx]:
                if not isinstance(u, (list, tuple)) or len(u) < 4:
                    continue
                x, y = int(u[0]), int(u[1])
                hp = np.float32(u[2])  # Structure.hp stored as np.float32 (Cluster I)
                uid = str(u[3])
                base_spec = config.structure_spec(type_idx, upgraded=False)
                if upgraded_uids is not None:
                    upgraded = uid in upgraded_uids
                else:
                    upgraded = hp > base_spec.hp
                all_structures.append(Structure(
                    xy=(x, y), type_idx=type_idx, upgraded=upgraded,
                    hp=hp, uid=uid, player=player,
                ))
    # Sort by integer uid to reproduce engine's gameObjects order.
    all_structures.sort(key=lambda s: int(s.uid))
    for s in all_structures:
        state.structures[s.xy] = s

    # Reconstruct turn_start_removal from the deploy frame's p{1,2}Units[6]
    # pseudo-unit entries. Engine's DisplayUnitsSystem.java:32 emits:
    #   hp_slot = turns_required_to_remove - (current_turn - turn_start_removal)
    # Back-compute:
    #   turn_start_removal = current_turn - (turns_required_to_remove - hp_slot)
    # The pseudo-unit's (x,y) is the target structure's position; join by tile.
    current_turn = state.turn
    for player, key in ((1, "p1Units"), (2, "p2Units")):
        units = df.get(key) or []
        if len(units) <= 6:
            continue
        for u in units[6] or []:
            if not isinstance(u, (list, tuple)) or len(u) < 4:
                continue
            x, y = int(u[0]), int(u[1])
            hp_slot = int(float(u[2]))
            s = state.structures.get((x, y))
            if s is None or s.player != player:
                continue
            spec = config.structure_spec(s.type_idx, upgraded=s.upgraded)
            s.turn_start_removal = current_turn - (spec.turns_required_to_remove - hp_slot)
            state.pending_removal_xys.add((x, y))
    return state


def _extract_deploy_actions(action_frame: dict) -> Tuple[List[list], List[list], List[list], List[list]]:
    """Return (p1_spawns, p1_upgrades, p2_spawns, p2_upgrades) from the first
    action-frame's spawn events. Type 7 = upgrade; types 0..5 = spawns.

    Each entry carries the engine-assigned uid (spawn event's 3rd slot) so
    apply_deploy_actions can stamp new Structures/Mobiles with the same uid
    the engine used — a prerequisite for uid-joined columns (events, units,
    pseudo-units) to match.

    Spawn entry shape:    [x, y, type_idx, uid]
    Upgrade entry shape:  [x, y, new_uid]         (engine reassigns uid on
                                                   upgrade — see Cluster A
                                                   notes / SpawnUnitsSystem
                                                   .attemptSpawn line 147)

    Preserves the event order within each player so that game-object
    insertion order for tie-breaking in targeting matches the engine's.
    """
    p1_sp: List[list] = []; p2_sp: List[list] = []
    p1_up: List[list] = []; p2_up: List[list] = []
    for ev in (action_frame or {}).get("events", {}).get("spawn", []):
        if not isinstance(ev, (list, tuple)) or len(ev) < 4:
            continue
        xy = ev[0]
        if not isinstance(xy, (list, tuple)) or len(xy) < 2:
            continue
        x, y, utype, player = int(xy[0]), int(xy[1]), int(ev[1]), int(ev[3])
        uid = str(ev[2])
        if utype == 7:  # UPGRADE — uid is the NEW (post-upgrade) uid
            (p1_up if player == 1 else p2_up).append([x, y, uid])
        elif utype in (0, 1, 2, 3, 4, 5):
            (p1_sp if player == 1 else p2_sp).append([x, y, utype, uid])
    return p1_sp, p1_up, p2_sp, p2_up


def _extract_deploy_events_in_order(action_frame: dict) -> List[Tuple[int, List[list]]]:
    """Return ordered list of (player, event_payload) for every spawn event
    in the first action frame. Preserves engine-issued spawn order.

    event_payload shape: [x, y, type_idx, uid] for every spawn (including
    upgrades with type_idx=7; apply_deploy_actions filters by type)."""
    ordered: List[Tuple[int, List]] = []
    for ev in (action_frame or {}).get("events", {}).get("spawn", []):
        if not isinstance(ev, (list, tuple)) or len(ev) < 4:
            continue
        xy = ev[0]
        if not isinstance(xy, (list, tuple)) or len(xy) < 2:
            continue
        x, y, utype, player = int(xy[0]), int(xy[1]), int(ev[1]), int(ev[3])
        uid = str(ev[2])
        ordered.append((player, [x, y, utype, uid]))
    return ordered


def _collect_upgraded_uids(frames: List[dict]) -> Dict[int, set]:
    """Walk every action-frame UPGRADE event and collect the set of POST-
    upgrade uids that the engine assigns to upgraded structures."""
    deploys = _index_deploy_frames(frames)
    upgraded_uids_at_turn: Dict[int, list] = {}
    for f in frames:
        ti = f.get("turnInfo") or []
        if len(ti) < 3 or ti[0] != 1 or ti[2] != 0:
            continue
        turn = int(ti[1])
        for ev in f.get("events", {}).get("spawn", []):
            if not isinstance(ev, (list, tuple)) or len(ev) < 4:
                continue
            if int(ev[1]) != 7:  # not UPGRADE
                continue
            new_uid = str(ev[2])
            upgraded_uids_at_turn.setdefault(turn, []).append(new_uid)

    result: Dict[int, set] = {}
    upgraded_so_far: set = set()
    for t in sorted(deploys.keys()):
        for uid in upgraded_uids_at_turn.get(t, []):
            upgraded_so_far.add(uid)
        result[t] = set(upgraded_so_far)
    return result


# --------------------------------------------------------- per-turn HP validate
#
# Back-compat Phase 1.A summary (used when validator is invoked without --full
# or from existing diff tools). Exact code from the pre-Phase-1.B validator.

@dataclass
class TurnValidation:
    turn: int
    replay_p1_hp_delta: float
    replay_p2_hp_delta: float
    replay_p1_sp_delta: float
    replay_p2_sp_delta: float
    sim_p1_hp_delta: float
    sim_p2_hp_delta: float
    sim_p1_sp_delta: float
    sim_p2_sp_delta: float
    sim_struct_damage_p1: float
    sim_struct_damage_p2: float

    @property
    def p1_hp_err(self) -> float:
        return abs(self.sim_p1_hp_delta - self.replay_p1_hp_delta)

    @property
    def p2_hp_err(self) -> float:
        return abs(self.sim_p2_hp_delta - self.replay_p2_hp_delta)


def validate_replay(path: Path, config: SimConfig) -> List[TurnValidation]:
    """Phase 1.A: run SimCore once per turn and compare to the next deploy
    frame. Used for fast regression checks."""
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    rows: List[TurnValidation] = []
    turns = sorted(deploys.keys())
    for t in turns:
        if t + 1 not in deploys:
            break
        df_t = deploys[t]
        df_next = deploys[t + 1]
        af_t = actions.get(t)
        if af_t is None:
            continue
        p1_sp, p1_up, p2_sp, p2_up = _extract_deploy_actions(af_t)
        ordered = _extract_deploy_events_in_order(af_t)
        state = _build_state_from_deploy_frame(df_t, config, upgraded_pre.get(t, set()))
        pre_p1_hp, pre_p2_hp = state.p1.hp, state.p2.hp
        pre_p1_sp, pre_p2_sp = state.p1.sp, state.p2.sp
        apply_deploy_actions(state, config, p1_sp, p1_up, p2_sp, p2_up, ordered_events=ordered)
        simulate_action_phase(state, config)
        sim_p1_hp_d = pre_p1_hp - state.p1.hp
        sim_p2_hp_d = pre_p2_hp - state.p2.hp
        sim_p1_sp_d = state.p1.sp - pre_p1_sp
        sim_p2_sp_d = state.p2.sp - pre_p2_sp
        rp1_hp = pre_p1_hp - float(df_next["p1Stats"][0])
        rp2_hp = pre_p2_hp - float(df_next["p2Stats"][0])
        rp1_sp = float(df_next["p1Stats"][1]) - pre_p1_sp
        rp2_sp = float(df_next["p2Stats"][1]) - pre_p2_sp
        rows.append(TurnValidation(
            turn=t,
            replay_p1_hp_delta=rp1_hp, replay_p2_hp_delta=rp2_hp,
            replay_p1_sp_delta=rp1_sp, replay_p2_sp_delta=rp2_sp,
            sim_p1_hp_delta=sim_p1_hp_d, sim_p2_hp_delta=sim_p2_hp_d,
            sim_p1_sp_delta=sim_p1_sp_d, sim_p2_sp_delta=sim_p2_sp_d,
            sim_struct_damage_p1=0.0, sim_struct_damage_p2=0.0,
        ))
    return rows


def _aggregate(rows: List[TurnValidation]) -> Dict[str, float]:
    if not rows:
        return {"turns": 0}
    p1_errs = [r.p1_hp_err for r in rows]
    p2_errs = [r.p2_hp_err for r in rows]
    all_errs = p1_errs + p2_errs
    return {
        "turns": len(rows),
        "max_hp_err": max(all_errs),
        "mean_hp_err": sum(all_errs) / len(all_errs),
        "p1_max": max(p1_errs),
        "p2_max": max(p2_errs),
        "p1_mean": sum(p1_errs) / len(p1_errs),
        "p2_mean": sum(p2_errs) / len(p2_errs),
    }


def validate_corpus(replays_dir: Path, config: SimConfig) -> Dict[str, dict]:
    """Phase 1.A corpus gate — HP-delta only."""
    paths = sorted(replays_dir.glob("*.replay"))
    report = {}
    t0 = time.perf_counter()
    total_sims = 0
    for p in paths:
        rows = validate_replay(p, config)
        agg = _aggregate(rows)
        report[p.name] = agg
        total_sims += len(rows)
    t1 = time.perf_counter()
    return {
        "per_replay": report,
        "timing": {"wall_s": t1 - t0, "total_sims": total_sims,
                   "sims_per_s": total_sims / max(1e-6, t1 - t0)},
    }


# ==========================================================================
# PHASE 1.B — 23-column frame-level parity table
# ==========================================================================

# -------------------------------------- Column definitions & extractors ---

# Each column extracts a comparable representation from either a sim frame
# or a replay frame. Frames have the same schema (emitted by
# pysim.build_frame_observation / engine's Parser.parseVisiblesListToJson),
# so most extractors are identical on both sides.
#
# Unit buckets:
#   structures  → p{1,2}Units[0..2]
#   mobiles     → p{1,2}Units[3..5]
#   removal_ps  → p{1,2}Units[6]
#   upgrade_ps  → p{1,2}Units[7]
#
# Each unit entry is [x, y, hp+shield, uid]. Compared order-sensitive
# (engine's per-type bucket order is game-object insertion order; SimCore
# mirrors that deterministically).

STAT_COLS = ("p1_hp", "p2_hp", "p1_sp", "p2_sp", "p1_mp", "p2_mp")
UNIT_COLS = (
    "p1_structures", "p1_mobiles", "p1_removal_pseudo", "p1_upgrade_pseudo",
    "p2_structures", "p2_mobiles", "p2_removal_pseudo", "p2_upgrade_pseudo",
)
EVENT_COLS = ("event_attack", "event_breach", "event_damage", "event_death",
              "event_melee", "event_move", "event_selfDestruct",
              "event_shield", "event_spawn")
ALL_COLS = STAT_COLS + UNIT_COLS + EVENT_COLS


def _units_raw(frame: dict, player: int, type_idx: int) -> list:
    """Raw unit list for (player, type_idx); returns the underlying list from
    the frame dict. Both sim-emitted and replay-parsed frames use the same
    shape — list of [x, y, hp+shield, uid] — so raw == raw comparison works
    without normalization."""
    units = frame.get(f"p{player}Units") or []
    if type_idx >= len(units):
        return []
    return units[type_idx] or []


def _structures_raw(frame: dict, player: int) -> list:
    return (_units_raw(frame, player, 0)
            + _units_raw(frame, player, 1)
            + _units_raw(frame, player, 2))


def _mobiles_raw(frame: dict, player: int) -> list:
    return (_units_raw(frame, player, 3)
            + _units_raw(frame, player, 4)
            + _units_raw(frame, player, 5))


# -------------------------------------- Per-frame diff ---------------------

@dataclass
class FrameDiff:
    replay: str
    turn: int
    frame: int
    # Mismatches: {col_name: (sim_value, replay_value)}; omit cols that match.
    mismatches: Dict[str, Tuple[Any, Any]] = field(default_factory=dict)


# Per-bucket float-slot positions for float32-aware comparison.
# Cluster I: engine stores HP/damage/shield-amount as Java 32-bit floats;
# replay JSON round-trips them as Python doubles whose bit patterns don't
# exactly match sim's np.float32. Quantizing both sides through np.float32
# at compare time aligns the bit representation. Positions NOT listed are
# integer (uid, typeID, srcPID, coords) or boolean (removed flag) — those
# bypass coercion.
_EVENT_FLOAT_SLOTS: Dict[str, Tuple[int, ...]] = {
    "attack": (2,),        # [[src], [tgt], DMG, typeID, src_uid, tgt_uid, pid]
    "breach": (1,),        # [[xy], DMG, typeID, uid, pid]
    "damage": (1,),        # [[xy], DMG, typeID, uid, pid]
    "selfDestruct": (2,),  # [[src], [targets], DMG, typeID, uid, pid]
    "shield": (2,),        # [[src], [tgt], AMOUNT, typeID, src_uid, tgt_uid, pid]
    # death/move/spawn/melee have no float slots.
}
_UNIT_FLOAT_SLOTS = (2,)   # [x, y, HP(+shield), uid]


def _normalize_units(entries: list) -> list:
    return [_fp32_slots(e, _UNIT_FLOAT_SLOTS) for e in entries]


def _normalize_events(entries: list, bucket: str) -> list:
    slots = _EVENT_FLOAT_SLOTS.get(bucket)
    if not slots:
        return list(entries)
    return [_fp32_slots(e, slots) for e in entries]


def _cmp_unit_lists(sv: list, rv: list) -> bool:
    """True iff lists of unit entries match. Fast-path: raw == first;
    if that fails, re-compare after quantizing HP slots through np.float32.
    For integer-valued HPs the raw compare succeeds (exact representation
    in both dtypes); for fractional HPs sim np.float32 and rep Python-float
    differ at double precision but agree at float32 — the normalized pass
    catches those."""
    if sv == rv:
        return True
    return _normalize_units(sv) == _normalize_units(rv)


def _cmp_event_lists(sv: list, rv: list, bucket: str) -> bool:
    if sv == rv:
        return True
    return _normalize_events(sv, bucket) == _normalize_events(rv, bucket)


def _cmp_stat(sv, rv) -> bool:
    """Stat scalar equality with float32 fallback."""
    if sv == rv:
        return True
    if sv is None or rv is None:
        return False
    return np.float32(sv) == np.float32(rv)


def _diff_frame(sim_frame: dict, rep_frame: dict) -> Dict[str, Tuple[Any, Any]]:
    """Return {col: (sim_val, replay_val)} for every non-matching column.

    Two-pass comparison: raw == first (common case — integer-valued HPs
    and matching ordered events pass without allocation), then float32-
    normalized compare on raw-fail (quantizes HP/shield/damage slots on
    both sides so sim's np.float32 bit pattern aligns with replay's JSON-
    double decoding). Engine's Java-float 32-bit arithmetic is ground
    truth; replay JSON decimals ("10.799999") round-trip to Python
    doubles whose bit patterns drift from the underlying float32 by
    ~1e-7.

    Float-slot positions per bucket in _EVENT_FLOAT_SLOTS /
    _UNIT_FLOAT_SLOTS; non-float slots (uids, coords, PIDs, removed flag)
    bypass coercion."""
    out: Dict[str, Tuple[Any, Any]] = {}

    # --- stats (6) ---
    s1 = sim_frame["p1Stats"]; r1 = rep_frame["p1Stats"]
    s2 = sim_frame["p2Stats"]; r2 = rep_frame["p2Stats"]
    for col, ply_pair, idx in (
        ("p1_hp", (s1, r1), 0), ("p2_hp", (s2, r2), 0),
        ("p1_sp", (s1, r1), 1), ("p2_sp", (s2, r2), 1),
        ("p1_mp", (s1, r1), 2), ("p2_mp", (s2, r2), 2),
    ):
        sv_raw = ply_pair[0][idx]
        rv_raw = ply_pair[1][idx]
        if not _cmp_stat(sv_raw, rv_raw):
            out[col] = (sv_raw, rv_raw)

    # --- unit buckets (8) ---
    for ply in (1, 2):
        sv = _structures_raw(sim_frame, ply); rv = _structures_raw(rep_frame, ply)
        if not _cmp_unit_lists(sv, rv):
            out[f"p{ply}_structures"] = (sv, rv)
        sv = _mobiles_raw(sim_frame, ply); rv = _mobiles_raw(rep_frame, ply)
        if not _cmp_unit_lists(sv, rv):
            out[f"p{ply}_mobiles"] = (sv, rv)
        sv = _units_raw(sim_frame, ply, 6); rv = _units_raw(rep_frame, ply, 6)
        if not _cmp_unit_lists(sv, rv):
            out[f"p{ply}_removal_pseudo"] = (sv, rv)
        sv = _units_raw(sim_frame, ply, 7); rv = _units_raw(rep_frame, ply, 7)
        if not _cmp_unit_lists(sv, rv):
            out[f"p{ply}_upgrade_pseudo"] = (sv, rv)

    # --- events (9) — ORDER-SENSITIVE ---
    sim_ev = sim_frame.get("events") or {}
    rep_ev = rep_frame.get("events") or {}
    for bucket in ("attack", "breach", "damage", "death", "melee", "move",
                   "selfDestruct", "shield", "spawn"):
        sv = sim_ev.get(bucket) or []
        rv = rep_ev.get(bucket) or []
        if not _cmp_event_lists(sv, rv, bucket):
            out[f"event_{bucket}"] = (sv, rv)

    return out


def validate_replay_full(path: Path, config: SimConfig) -> Tuple[List[FrameDiff], Dict[str, int]]:
    """Run SimCore turn-by-turn and diff every action-phase frame against
    the replay. Returns (diffs, turn_stats).

    turn_stats: {"total_turns": N, "total_frames": M,
                 "sim_frame_count_mismatches": K}
    """
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions_all = _index_action_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    all_diffs: List[FrameDiff] = []
    total_turns = 0
    total_frames = 0
    frame_count_mismatches = 0

    for t in sorted(deploys.keys()):
        af_first = actions_first.get(t)
        if af_first is None:
            continue
        replay_frames = actions_all.get(t, [])
        if not replay_frames:
            continue
        total_turns += 1

        p1_sp, p1_up, p2_sp, p2_up = _extract_deploy_actions(af_first)
        ordered = _extract_deploy_events_in_order(af_first)
        state = _build_state_from_deploy_frame(deploys[t], config, upgraded_pre.get(t, set()))
        # Collect deploy-phase spawn events to seed into frame 0 (engine
        # emits GlobalSpawn from processInputBuild + processInputDeploy and
        # those appear in the first action frame's event bucket).
        deploy_events: List[dict] = []
        apply_deploy_actions(state, config, p1_sp, p1_up, p2_sp, p2_up,
                             ordered_events=ordered, events=deploy_events)

        # Seed total_frame_number from the replay's first action frame so
        # the turnInfo column matches (if sim produces the right frame count).
        total_frame_start = int(replay_frames[0]["turnInfo"][3])

        sim_iter = simulate_action_phase_iter(
            state, config, perspective=1, total_frame_start=total_frame_start,
            seed_events=deploy_events,
        )
        rep_i = 0
        for sim_obs in sim_iter:
            if rep_i >= len(replay_frames):
                # Sim produced more frames than replay
                all_diffs.append(FrameDiff(
                    replay=path.name, turn=t, frame=rep_i,
                    mismatches={"frame_count": ("sim_excess", "")},
                ))
                frame_count_mismatches += 1
                break
            rep_frame = replay_frames[rep_i]
            mism = _diff_frame(sim_obs, rep_frame)
            if mism:
                all_diffs.append(FrameDiff(
                    replay=path.name, turn=t, frame=rep_i, mismatches=mism,
                ))
            total_frames += 1
            rep_i += 1
        # Drain iter if replay had fewer frames
        extra = 0
        for _ in sim_iter:
            extra += 1
        if extra:
            all_diffs.append(FrameDiff(
                replay=path.name, turn=t, frame=rep_i,
                mismatches={"frame_count": (f"sim_excess_{extra}", "")},
            ))
            frame_count_mismatches += 1
        if rep_i < len(replay_frames):
            missing = len(replay_frames) - rep_i
            all_diffs.append(FrameDiff(
                replay=path.name, turn=t, frame=rep_i,
                mismatches={"frame_count": ("sim_short", f"missing_{missing}")},
            ))
            frame_count_mismatches += 1

    return all_diffs, {
        "total_turns": total_turns,
        "total_frames": total_frames,
        "frame_count_mismatches": frame_count_mismatches,
    }


def validate_corpus_full(replays_dir: Path, config: SimConfig) -> dict:
    paths = sorted(replays_dir.glob("*.replay"))
    t0 = time.perf_counter()
    all_diffs: List[FrameDiff] = []
    per_replay_frame_counts: Dict[str, int] = {}
    total_turns_all = 0
    total_frames_all = 0
    frame_count_mismatches_all = 0
    for p in paths:
        diffs, stats = validate_replay_full(p, config)
        all_diffs.extend(diffs)
        per_replay_frame_counts[p.name] = stats["total_frames"]
        total_turns_all += stats["total_turns"]
        total_frames_all += stats["total_frames"]
        frame_count_mismatches_all += stats["frame_count_mismatches"]
    t1 = time.perf_counter()
    wall = t1 - t0

    # Per-column aggregation.
    col_nonzero_frames: Dict[str, int] = {c: 0 for c in ALL_COLS}
    col_nonzero_replays: Dict[str, set] = {c: set() for c in ALL_COLS}
    # First example: {col: (replay, turn, frame, sim_val, rep_val)}
    col_first_example: Dict[str, tuple] = {}
    frame_count_nonzero_replays: set = set()

    for d in all_diffs:
        for col, (sv, rv) in d.mismatches.items():
            if col == "frame_count":
                frame_count_nonzero_replays.add(d.replay)
                continue
            if col in col_nonzero_frames:
                col_nonzero_frames[col] += 1
                col_nonzero_replays[col].add(d.replay)
                if col not in col_first_example:
                    col_first_example[col] = (d.replay, d.turn, d.frame, sv, rv)

    return {
        "timing": {
            "wall_s": wall,
            "total_turns": total_turns_all,
            "total_frames": total_frames_all,
            "frames_per_s": total_frames_all / max(1e-6, wall),
            "sims_per_s": total_turns_all / max(1e-6, wall),
        },
        "columns": {
            c: {
                "nonzero_frames": col_nonzero_frames[c],
                "nonzero_replays": len(col_nonzero_replays[c]),
                "first_example": col_first_example.get(c),
            }
            for c in ALL_COLS
        },
        "frame_count_nonzero_replays": sorted(frame_count_nonzero_replays),
        "frame_count_mismatches_total": frame_count_mismatches_all,
    }


# ---------------------------------------------------------------- CLI ------

def _print_phase1a_report(report: dict) -> None:
    print(json.dumps(report["timing"], indent=2))
    all_errs_max = []
    all_errs_mean = []
    for name, agg in sorted(report["per_replay"].items()):
        if "max_hp_err" not in agg:
            continue
        print(f"  {name:65s} turns={agg['turns']:3d} max={agg['max_hp_err']:5.1f} mean={agg['mean_hp_err']:5.2f}")
        all_errs_max.append(agg["max_hp_err"])
        all_errs_mean.append(agg["mean_hp_err"])
    if all_errs_max:
        overall_max = max(all_errs_max)
        overall_mean = sum(all_errs_mean) / len(all_errs_mean)
        print()
        print(f"OVERALL (across {len(all_errs_max)} replays):")
        print(f"  max hp err = {overall_max:.2f}  (gate: ≤2.0 HP = 5% of 40)")
        print(f"  mean hp err = {overall_mean:.2f} (gate: ≤0.4 HP = 1% of 40)")
        gate = "PASS" if (overall_max <= 2.0 and overall_mean <= 0.4) else "FAIL"
        print(f"  Phase 1 gate: {gate}")


def _fmt_example(ex: tuple) -> str:
    if ex is None:
        return ""
    replay, turn, frame, sv, rv = ex
    rid = replay[:38] + "…" if len(replay) > 40 else replay
    if isinstance(sv, list) and isinstance(rv, list):
        return f"{rid} T{turn}F{frame}  |sim|={len(sv)} |rep|={len(rv)}"
    return f"{rid} T{turn}F{frame}  sim={sv!r} rep={rv!r}"


def _print_phase1b_report(report: dict) -> None:
    tm = report["timing"]
    print("=" * 78)
    print(f"Phase 1.B — full-state parity | {tm['total_frames']} frames across "
          f"{tm['total_turns']} turns | wall={tm['wall_s']:.2f}s")
    print(f"  throughput: {tm['sims_per_s']:6.1f} sims/s  |  "
          f"{tm['frames_per_s']:6.0f} frames/s")
    print()
    print(f"  {'column':<24} {'#frames_diff':>12} {'#replays':>9}  first_example")
    print(f"  {'-' * 24} {'-' * 12} {'-' * 9}  {'-' * 40}")
    any_fail = False
    for c in ALL_COLS:
        ci = report["columns"][c]
        nz = ci["nonzero_frames"]
        nzr = ci["nonzero_replays"]
        ex = _fmt_example(ci["first_example"])
        mark = "" if nz == 0 else "  FAIL"
        if nz > 0:
            any_fail = True
        print(f"  {c:<24} {nz:>12} {nzr:>9}  {ex}{mark}")
    # Frame count
    fcnr = report["frame_count_nonzero_replays"]
    if report["frame_count_mismatches_total"]:
        print()
        print(f"  frame_count mismatches: {report['frame_count_mismatches_total']} "
              f"across {len(fcnr)} replays")
        any_fail = True
    print()
    print(f"Phase 1.B GATE: {'FAIL' if any_fail else 'PASS'}")
    thr = tm["sims_per_s"]
    thr_gate = "PASS" if thr >= 50 else "FAIL"
    print(f"Throughput GATE (≥50 sims/s): {thr_gate} ({thr:.1f})")


if __name__ == "__main__":
    repo = Path(__file__).resolve().parent.parent.parent.parent
    replays_dir = repo / "replays" / "ranked"
    config = SimConfig.load()

    argv = [a for a in sys.argv[1:] if not a.startswith("--")]
    flags = [a for a in sys.argv[1:] if a.startswith("--")]

    if "--full" in flags:
        # Phase 1.B: 23-column frame-level gate.
        if argv:
            # Single replay or list of replays.
            paths = [Path(a) for a in argv]
            all_diffs: List[FrameDiff] = []
            for p in paths:
                diffs, _ = validate_replay_full(p, config)
                all_diffs.extend(diffs)
            # Mini summary
            cols = {}
            for d in all_diffs:
                for c, (sv, rv) in d.mismatches.items():
                    cols.setdefault(c, 0)
                    cols[c] += 1
            print(f"diffs={len(all_diffs)}  cols_touched={len(cols)}")
            for c, n in sorted(cols.items(), key=lambda kv: -kv[1]):
                print(f"  {c:<24} {n:>6}")
        else:
            report = validate_corpus_full(replays_dir, config)
            _print_phase1b_report(report)
    elif "--one" in flags:
        p = Path(argv[0])
        rows = validate_replay(p, config)
        print(f"{p.name}: {len(rows)} turns validated")
        for r in rows[:10]:
            print(f"  turn {r.turn:3d}: p1dmg sim={r.sim_p1_hp_delta:5.1f} replay={r.replay_p1_hp_delta:5.1f} err={r.p1_hp_err:4.1f} | "
                  f"p2dmg sim={r.sim_p2_hp_delta:5.1f} replay={r.replay_p2_hp_delta:5.1f} err={r.p2_hp_err:4.1f}")
        agg = _aggregate(rows)
        print(f"aggregate: {agg}")
    else:
        # Default = Phase 1.A HP-delta summary (back-compat).
        report = validate_corpus(replays_dir, config)
        _print_phase1a_report(report)
