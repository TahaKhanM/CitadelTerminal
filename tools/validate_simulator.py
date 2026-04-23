"""BLOCKED — validator for gamelib_ext/simulator.py (currently non-functional).

Status: `gamelib_ext/simulator.py` was deleted during the Tier 2 cleanup
because the v1 simulator had ~30 % aggregate error — too high for any Tier 2
search strategy (MCTS, minimax, forward-simulation-based CMA-ES) to rely on.
Running this file today will ImportError on the `from gamelib_ext.simulator
import ...` line below.

Kept as-is so that, when a rebuilt Tier 0 simulator lands, this validator
can be re-enabled without rewriting the replay-iteration logic. A rebuilt
simulator must clear the same pass bar before being trusted:

  Pass bar: aggregate |err| < 5 % on each replay's totals.

Usage (post-rebuild only):
  python3 tools/validate_simulator.py <replay1> [replay2 ...]

For each turn T:
  1. Seed a SimState from the first action-phase frame of turn T (which
     already has all structures + mobile units for that turn placed).
  2. Run simulate_action_phase.
  3. Sum the engine's events across all action frames of turn T to get
     ground-truth totals (damage to each side, breaches).
  4. Compare: report per-turn and aggregate error.
"""

import json
import os
import sys
from collections import defaultdict

# Ensure repo root is on path
_HERE = os.path.dirname(os.path.abspath(__file__))
_ROOT = os.path.dirname(_HERE)
sys.path.insert(0, _ROOT)

from gamelib_ext.simulator import (  # noqa: E402
    SimState,
    simulate_action_phase,
)


def iter_frames(path):
    with open(path) as f:
        header = json.loads(f.readline())
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                yield json.loads(line)
            except Exception:
                continue
        return header


def load_replay(path):
    with open(path) as f:
        header = json.loads(f.readline())
        frames = []
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                obj = json.loads(line)
                if "turnInfo" in obj:
                    frames.append(obj)
            except Exception:
                continue
    return header, frames


def group_by_turn(frames):
    """Return dict: turn -> dict(deploy_frame, action_frames)."""
    turns = defaultdict(lambda: {"deploy": None, "action": []})
    for fr in frames:
        ti = fr["turnInfo"]
        phase = ti[0]
        turn = ti[1]
        if phase == 0:
            turns[turn]["deploy"] = fr
        elif phase == 1:
            turns[turn]["action"].append(fr)
    return turns


def seed_state_from_action_frame(config, act_frame) -> SimState:
    """Use the FIRST action-phase frame (turnInfo[2]==0) — it contains all
    structures AND mobiles present at the start of the action phase.
    """
    ui = config["unitInformation"]
    shorthand_by_idx = [u["shorthand"] for u in ui]
    REMOVE_IDX = shorthand_by_idx.index("RM") if "RM" in shorthand_by_idx else 6
    UP_IDX = shorthand_by_idx.index("UP") if "UP" in shorthand_by_idx else 7

    state = SimState(config)
    p1_hp, p1_sp, p1_mp, _ = act_frame["p1Stats"][:4]
    p2_hp, p2_sp, p2_mp, _ = act_frame["p2Stats"][:4]
    state.my_hp = float(p1_hp)
    state.enemy_hp = float(p2_hp)
    state.my_sp = float(p1_sp); state.my_mp = float(p1_mp)
    state.enemy_sp = float(p2_sp); state.enemy_mp = float(p2_mp)

    # In the replay JSON, p1 = player 1, p2 = player 2. In SimState we use
    # player 0 (me) for p1 and player 1 (opponent) for p2.
    for side, replay_player in ((act_frame["p1Units"], 0), (act_frame["p2Units"], 1)):
        # First pass: add structures and mobiles (types 0..5)
        for type_idx, unit_list in enumerate(side):
            if type_idx in (REMOVE_IDX, UP_IDX):
                continue
            sh = shorthand_by_idx[type_idx]
            for uinfo in unit_list:
                x, y, hp = uinfo[0], uinfo[1], uinfo[2]
                if sh in state.STRUCTURE_TYPES:
                    state.add_structure(sh, int(x), int(y), replay_player, hp=float(hp))
                else:
                    mob = state.spawn_mobile(sh, int(x), int(y), replay_player)
                    mob.hp = float(hp)
        # Second pass: apply upgrade markers
        up_list = side[UP_IDX] if UP_IDX < len(side) else []
        for uinfo in up_list:
            x, y = int(uinfo[0]), int(uinfo[1])
            state.upgrade_structure_at(x, y)
        # Removal markers — not needed for single-turn simulation
    return state


def sum_events(action_frames):
    """Compute ground-truth totals from the engine's event stream.

    Returns dict:
      dmg_to_p1  — total damage dealt to p1's units (their HP reduction)
      dmg_to_p2  — total damage dealt to p2's units
      breaches_by_p1
      breaches_by_p2
    """
    dmg1 = 0.0
    dmg2 = 0.0
    br1 = 0
    br2 = 0
    for fr in action_frames:
        ev = fr.get("events", {})
        # damage: [[x,y], amount, unit_type_idx, unit_id, victim_player]
        for d in ev.get("damage", []):
            try:
                amount = float(d[1])
                victim = int(d[4])
            except (IndexError, ValueError):
                continue
            if victim == 1:
                dmg1 += amount
            elif victim == 2:
                dmg2 += amount
        # breach: [[x,y], amount, unit_type_idx, unit_id, breacher_player]
        for b in ev.get("breach", []):
            try:
                breacher = int(b[4])
            except (IndexError, ValueError):
                continue
            if breacher == 1:
                br1 += 1
            elif breacher == 2:
                br2 += 1
    return {
        "dmg_to_p1": dmg1,
        "dmg_to_p2": dmg2,
        "breaches_by_p1": br1,
        "breaches_by_p2": br2,
    }


def validate_replay(path, max_turns=100, verbose=False):
    header, frames = load_replay(path)
    config = header  # header is the config itself
    turns = group_by_turn(frames)

    agg_sim = defaultdict(float)
    agg_gt = defaultdict(float)
    per_turn_errs = []
    turns_simulated = 0

    for turn in sorted(turns.keys()):
        if turn >= max_turns:
            break
        turn_data = turns[turn]
        action_frames = turn_data["action"]
        if not action_frames:
            continue
        # First action frame: turnInfo[2] == 0
        first = min(action_frames, key=lambda fr: fr["turnInfo"][2])
        # Seed state
        state = seed_state_from_action_frame(config, first)
        # Skip turns with no mobiles (no action to simulate)
        if not state.mobiles:
            continue
        # Simulate
        res = simulate_action_phase(state, max_frames=2000)
        # Ground truth
        gt = sum_events(action_frames)
        # Our (SimState.player 0 == replay p1)
        sim_vals = {
            "dmg_to_p1": res.dmg_to_me,
            "dmg_to_p2": res.dmg_to_enemy,
            "breaches_by_p1": res.breaches_by_me,
            "breaches_by_p2": res.breaches_by_enemy,
        }
        for k in sim_vals:
            agg_sim[k] += sim_vals[k]
            agg_gt[k] += gt[k]
        # Per-turn err
        per_turn_errs.append((turn, sim_vals, gt))
        turns_simulated += 1
        if verbose:
            print(f"  T{turn}: sim={sim_vals} gt={gt}")

    def pct_err(sim, gt):
        if gt == 0:
            return 0.0 if sim == 0 else float("inf")
        return (sim - gt) / gt * 100.0

    print(f"\n=== {os.path.basename(path)} ===")
    print(f"turns simulated: {turns_simulated}")
    print(f"              sim           gt            err%")
    for k in ("dmg_to_p1", "dmg_to_p2", "breaches_by_p1", "breaches_by_p2"):
        s = agg_sim[k]; g = agg_gt[k]
        print(f"  {k:18s} {s:12.2f}  {g:12.2f}  {pct_err(s,g):+7.2f}")
    # Return aggregate abs errors
    return {k: (agg_sim[k], agg_gt[k]) for k in agg_sim}


def main(argv):
    if len(argv) < 2:
        # Default: pick 3 wins (they tend to have more action)
        default = [
            "replays/final_v11_vs_1472_elo_win.replay",
            "replays/final_v11_vs_1619_elo_win.replay",
            "replays/final_v11_vs_1654_elo_win.replay",
        ]
        argv = [argv[0]] + [os.path.join(_ROOT, p) for p in default]
    results = []
    for path in argv[1:]:
        r = validate_replay(path, verbose=False)
        results.append((path, r))
    # Summary
    print("\n=== aggregate per-replay totals ===")
    for path, r in results:
        print(f"  {os.path.basename(path)}")
        for k, (s, g) in r.items():
            if g == 0:
                err = 0.0 if s == 0 else float("inf")
            else:
                err = (s - g) / g * 100.0
            print(f"    {k:18s} sim={s:10.1f} gt={g:10.1f} err={err:+7.2f}%")


if __name__ == "__main__":
    main(sys.argv)
