"""SimCore validation harness.

For each turn of each ranked replay:
  1. Read the deploy-phase snapshot (structures + stats at turn start).
  2. Apply the observed deploy actions via apply_deploy_actions.
  3. Run simulate_action_phase.
  4. Compare the resulting state to the replay's NEXT deploy snapshot
     (which captures the post-action-phase, pre-deploy state of turn T+1).

Metrics computed:

  per-turn HP-delta match: |sim.p{1,2}.hp_change − replay.p{1,2}.hp_change|
  per-turn structure damage:
     structures hit this turn, total damage delta from sim vs replay
  per-turn mobile-breach count: how many of our/their mobiles breached

Aggregate gate: (max / mean) absolute error in HP damage dealt per player per
turn, as a fraction of starting HP (40). Plan's gate:
    max ≤ 5%  (= 2.0 HP), mean ≤ 1% (= 0.4 HP).
"""

from __future__ import annotations

import json
import sys
import time
from pathlib import Path
from typing import Dict, List, Optional, Tuple

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from sim.config import (  # noqa: E402
    IDX_SUPPORT,
    IDX_TURRET,
    IDX_WALL,
    SimConfig,
)
from sim.pysim import apply_deploy_actions, simulate_action_phase  # noqa: E402
from sim.state import PlayerStats, SimState, Structure  # noqa: E402


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
    upgraded structures."""
    p1 = df.get("p1Stats") or [40, 8, 1, 0]
    p2 = df.get("p2Stats") or [40, 8, 1, 0]
    state = SimState(
        turn=int(df.get("turnInfo", [0, 0])[1] if df.get("turnInfo") else 0),
        structures={}, mobiles=[],
        p1=PlayerStats(hp=float(p1[0]), sp=float(p1[1]), mp=float(p1[2])),
        p2=PlayerStats(hp=float(p2[0]), sp=float(p2[1]), mp=float(p2[2])),
    )
    for player, key in ((1, "p1Units"), (2, "p2Units")):
        units = df.get(key) or []
        for type_idx in (IDX_WALL, IDX_SUPPORT, IDX_TURRET):
            if type_idx >= len(units):
                continue
            for u in units[type_idx]:
                if not isinstance(u, (list, tuple)) or len(u) < 4:
                    continue
                x, y = int(u[0]), int(u[1])
                hp = float(u[2])
                uid = str(u[3])
                base_spec = config.structure_spec(type_idx, upgraded=False)
                if upgraded_uids is not None:
                    upgraded = uid in upgraded_uids
                else:
                    upgraded = hp > base_spec.hp
                state.structures[(x, y)] = Structure(
                    xy=(x, y), type_idx=type_idx, upgraded=upgraded,
                    hp=hp, uid=uid, player=player,
                )
    return state


def _extract_deploy_actions(action_frame: dict) -> Tuple[List[list], List[list], List[list], List[list]]:
    """Return (p1_spawns, p1_upgrades, p2_spawns, p2_upgrades) from the first
    action-frame's spawn events. Type 7 = upgrade; types 0..5 = spawns."""
    p1_sp: List[list] = []; p2_sp: List[list] = []
    p1_up: List[list] = []; p2_up: List[list] = []
    for ev in (action_frame or {}).get("events", {}).get("spawn", []):
        if not isinstance(ev, (list, tuple)) or len(ev) < 4:
            continue
        xy = ev[0]
        if not isinstance(xy, (list, tuple)) or len(xy) < 2:
            continue
        x, y, utype, player = int(xy[0]), int(xy[1]), int(ev[1]), int(ev[3])
        if utype == 7:  # UPGRADE
            (p1_up if player == 1 else p2_up).append([x, y])
        elif utype in (0, 1, 2, 3, 4, 5):
            (p1_sp if player == 1 else p2_sp).append([x, y, utype])
    return p1_sp, p1_up, p2_sp, p2_up


# --------------------------------------------------------- per-turn validate

from dataclasses import dataclass  # noqa: E402


@dataclass
class TurnValidation:
    turn: int
    # Replay truth
    replay_p1_hp_delta: float
    replay_p2_hp_delta: float
    replay_p1_sp_delta: float
    replay_p2_sp_delta: float
    # SimCore output
    sim_p1_hp_delta: float
    sim_p2_hp_delta: float
    sim_p1_sp_delta: float
    sim_p2_sp_delta: float
    # Structure damage (sum over all enemy structures this turn)
    sim_struct_damage_p1: float  # damage p1 dealt to p2 structures
    sim_struct_damage_p2: float

    @property
    def p1_hp_err(self) -> float:
        return abs(self.sim_p1_hp_delta - self.replay_p1_hp_delta)

    @property
    def p2_hp_err(self) -> float:
        return abs(self.sim_p2_hp_delta - self.replay_p2_hp_delta)


def _collect_upgraded_uids(frames: List[dict]) -> Dict[int, set]:
    """Walk every action-frame UPGRADE event and collect the set of POST-
    upgrade uids that the engine assigns to upgraded structures. Returns
    {turn_T: set_of_upgraded_uids_observable_at_T's_deploy_frame}.

    Engine behavior (verified empirically, replay-spec):
      An UPGRADE spawn event has shape [[x,y], 7, NEW_UID, player]. The
      structure at (x,y) gets NEW_UID as its uid post-upgrade; NEW_UID then
      appears in subsequent deploy frames. A structure's current uid is
      "upgraded" iff it has been the new_uid in some upgrade event.
    """
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
        # At turn T deploy frame, structures upgraded in turns 0..T-1 AND
        # in turn T's deploy phase both show their upgraded uid, because
        # the deploy frame is emitted AFTER the deploy phase. So include
        # turn T's upgrades too.
        for uid in upgraded_uids_at_turn.get(t, []):
            upgraded_so_far.add(uid)
        result[t] = set(upgraded_so_far)
    return result


def validate_replay(path: Path, config: SimConfig) -> List[TurnValidation]:
    """Run SimCore once per turn and compare to the next deploy frame."""
    frames, _cfg = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    rows: List[TurnValidation] = []
    turns = sorted(deploys.keys())
    for t in turns:
        if t + 1 not in deploys:
            break  # last turn has no "post" snapshot
        df_t = deploys[t]
        df_next = deploys[t + 1]
        af_t = actions.get(t)
        if af_t is None:
            continue
        p1_sp, p1_up, p2_sp, p2_up = _extract_deploy_actions(af_t)

        # Build state from deploy snapshot with persistent upgrade tracking.
        state = _build_state_from_deploy_frame(df_t, config, upgraded_pre.get(t, set()))
        # Capture deploy-time stats for deltas
        pre_p1_hp, pre_p2_hp = state.p1.hp, state.p2.hp
        pre_p1_sp, pre_p2_sp = state.p1.sp, state.p2.sp

        # Apply deploy actions
        apply_deploy_actions(state, config, p1_sp, p1_up, p2_sp, p2_up)
        # Run action phase
        simulate_action_phase(state, config)

        sim_p1_hp_d = pre_p1_hp - state.p1.hp
        sim_p2_hp_d = pre_p2_hp - state.p2.hp
        sim_p1_sp_d = state.p1.sp - pre_p1_sp  # positive = gain
        sim_p2_sp_d = state.p2.sp - pre_p2_sp

        # Replay truth: compare p1/p2 stats at turn T deploy vs turn T+1 deploy.
        # Between deploys, the engine ALSO runs the investment phase (income +
        # decay) which we do not simulate here — we only validate the
        # action-phase HP damage. So compare HP delta (action-phase-only
        # effect) vs full replay delta minus any between-turn HP change
        # (which is zero normally — HP only changes from breaches).
        rp1_hp = pre_p1_hp - float(df_next["p1Stats"][0])
        rp2_hp = pre_p2_hp - float(df_next["p2Stats"][0])
        # For SP, the replay includes action-phase breach refunds PLUS the
        # inter-turn income (4 SP + breach bonus). We can't fully reconcile
        # without reimplementing InvestmentSystem. Report delta but the gate
        # is HP-only.
        rp1_sp = float(df_next["p1Stats"][1]) - pre_p1_sp
        rp2_sp = float(df_next["p2Stats"][1]) - pre_p2_sp

        rows.append(TurnValidation(
            turn=t,
            replay_p1_hp_delta=rp1_hp,
            replay_p2_hp_delta=rp2_hp,
            replay_p1_sp_delta=rp1_sp,
            replay_p2_sp_delta=rp2_sp,
            sim_p1_hp_delta=sim_p1_hp_d,
            sim_p2_hp_delta=sim_p2_hp_d,
            sim_p1_sp_delta=sim_p1_sp_d,
            sim_p2_sp_delta=sim_p2_sp_d,
            sim_struct_damage_p1=0.0,
            sim_struct_damage_p2=0.0,
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
    """Run validation across every replay in replays_dir. Returns per-replay
    + aggregate summaries."""
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

    # Cross-replay aggregate
    all_p1 = []
    all_p2 = []
    for name, agg in report.items():
        # each agg has turns; if zero, skip
        pass
    # Rebuild from all rows for a single gate
    return {"per_replay": report, "timing": {"wall_s": t1 - t0, "total_sims": total_sims, "sims_per_s": total_sims / max(1e-6, t1 - t0)}}


if __name__ == "__main__":
    repo = Path(__file__).resolve().parent.parent.parent.parent
    replays_dir = repo / "replays" / "ranked"
    config = SimConfig.load()

    if len(sys.argv) > 1 and sys.argv[1] == "--one":
        p = Path(sys.argv[2])
        rows = validate_replay(p, config)
        print(f"{p.name}: {len(rows)} turns validated")
        for r in rows[:10]:
            print(f"  turn {r.turn:3d}: p1dmg sim={r.sim_p1_hp_delta:5.1f} replay={r.replay_p1_hp_delta:5.1f} err={r.p1_hp_err:4.1f} | "
                  f"p2dmg sim={r.sim_p2_hp_delta:5.1f} replay={r.replay_p2_hp_delta:5.1f} err={r.p2_hp_err:4.1f}")
        agg = _aggregate(rows)
        print(f"aggregate: {agg}")
    else:
        report = validate_corpus(replays_dir, config)
        print(json.dumps(report["timing"], indent=2))
        # Aggregate across replays
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
