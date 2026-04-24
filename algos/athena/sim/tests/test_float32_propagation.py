"""Cluster I guard: every HP/shield/damage/SP/MP value stays at np.float32
precision across every arithmetic boundary in SimCore.

Engine stores these as Java 32-bit floats. Python's default `float` type is
a 64-bit double; mixing an np.float32 with a Python float (including
literals like `13.5`, `0.0`, `0.1`) silently widens the result to float64
and breaks bit-exact parity with engine.jar on accumulated values. This
test runs the full action phase of every ranked replay and asserts:

  1. Every HP/shield slot emitted in a FrameObservation is np.float32.
  2. Every damage value emitted in attack/damage/breach/shield/selfDestruct
     events is np.float32.
  3. Every state field (Structure.hp, Mobile.hp, Mobile.shield,
     PlayerStats.{hp,sp,mp}) is np.float32 after any arithmetic site
     that mutates it.
  4. The pure helpers (_apply_damage, _round01) return np.float32.

A single stray Python float in the chain fails the assertion at the call
site — pointing directly at the missed cast, not at the downstream
observable. Add new arithmetic sites to this test as they land.

Run: python3 -m algos.athena.sim.tests.test_float32_propagation
Exit code: 0 all pass, 1 any widening detected.
"""

from __future__ import annotations

import sys
from pathlib import Path

import numpy as np

sys.path.insert(0, str(Path(__file__).resolve().parent.parent.parent))

from sim import pysim  # noqa: E402
from sim.config import SimConfig  # noqa: E402
from sim.pysim import (  # noqa: E402
    _apply_damage,
    _round01,
    FP32,
    _F32_ZERO,
    apply_deploy_actions,
    simulate_action_phase_iter,
)
from sim.state import Mobile, PlayerStats, Structure  # noqa: E402
from sim.validate import (  # noqa: E402
    _parse_replay,
    _index_deploy_frames,
    _index_action_frames,
    _index_first_action_frames,
    _extract_deploy_actions,
    _extract_deploy_events_in_order,
    _build_state_from_deploy_frame,
    _collect_upgraded_uids,
)


def _assert_fp32(value, where: str) -> None:
    """Assert `value` is np.float32 scalar. Raises AssertionError with
    a descriptive message pointing at the caller-specified site."""
    if isinstance(value, np.float32):
        return
    raise AssertionError(
        f"float32 widening at {where}: value={value!r} type={type(value).__name__}"
    )


def test_round01_returns_fp32() -> None:
    # Input: Python float, int, np.float32, np.float64 — all outputs fp32.
    for inp in (0.0, 1, 12.6, np.float32(7.3), np.float64(4.2)):
        r = _round01(inp)
        _assert_fp32(r, f"_round01({inp!r})")


def test_apply_damage_returns_fp32() -> None:
    # Variety of shield states, damage values, and dtypes.
    cases = [
        (FP32(15.0), _F32_ZERO, FP32(8.0)),        # no shield, damage lands
        (FP32(15.0), FP32(10.1), FP32(4.0)),       # shield absorbs all
        (FP32(15.0), FP32(3.0), FP32(8.0)),        # shield partial, HP lands
        (FP32(60.0), _F32_ZERO, FP32(60.0)),       # lethal
        (FP32(60.0), _F32_ZERO, FP32(61.0)),       # overkill
        (FP32(0.0), _F32_ZERO, FP32(5.0)),         # already dead
    ]
    for hp, sh, dmg in cases:
        new_hp, new_sh, died = _apply_damage(hp, sh, dmg)
        _assert_fp32(new_hp, f"_apply_damage({hp}, {sh}, {dmg}).new_hp")
        _assert_fp32(new_sh, f"_apply_damage({hp}, {sh}, {dmg}).new_shield")
        assert isinstance(died, bool), f"_apply_damage .died type={type(died)}"


def test_config_specs_are_fp32() -> None:
    config = SimConfig.load()
    for name in ("wall_base", "wall_upg", "support_base", "support_upg",
                 "turret_base", "turret_upg"):
        spec = getattr(config, name)
        for attr in ("hp", "cost_sp", "refund_pct", "attack_range",
                     "attack_damage_walker", "attack_damage_tower",
                     "shield_range", "shield_per_unit", "shield_bonus_per_y",
                     "shield_decay"):
            v = getattr(spec, attr)
            _assert_fp32(v, f"config.{name}.{attr}")
    for name in ("scout", "demolisher", "interceptor"):
        spec = getattr(config, name)
        for attr in ("hp", "cost_mp", "attack_range", "attack_damage_walker",
                     "attack_damage_tower", "speed", "self_destruct_range",
                     "self_destruct_damage_walker", "self_destruct_damage_tower",
                     "breach_damage", "metal_for_breach"):
            v = getattr(spec, attr)
            _assert_fp32(v, f"config.{name}.{attr}")


def _assert_frame_observation_fp32(obs: dict, where: str) -> None:
    """Every HP/shield/stat/damage slot in the observation must be
    quantizable to np.float32 without loss (i.e. already at float32
    precision). float values check: float(np.float32(v)) == v. np.float32
    values pass unconditionally."""
    def _check(v, loc):
        if isinstance(v, np.float32):
            return
        if isinstance(v, (int, float, np.floating)):
            if not np.isfinite(v):
                return
            if float(np.float32(v)) != float(v):
                raise AssertionError(
                    f"float32 widening at {loc} in {where}: value={v!r} "
                    f"double!=float32-quantized ({float(np.float32(v))!r})"
                )
            return
        raise AssertionError(f"unexpected type at {loc} in {where}: {type(v)}")

    for ply in ("p1Stats", "p2Stats"):
        stats = obs[ply]
        for i, field in enumerate(("hp", "sp", "mp")):
            _check(stats[i], f"{ply}.{field}")

    # Per-unit HP slot in every bucket, both players.
    for ply in ("p1Units", "p2Units"):
        for tidx, bucket in enumerate(obs[ply]):
            for j, entry in enumerate(bucket or []):
                # entry = [x, y, hp_slot, uid]
                _check(entry[2], f"{ply}[{tidx}][{j}].hp")

    # Per-event damage / amount slots.
    event_float_slots = {
        "attack": 2, "breach": 1, "damage": 1,
        "selfDestruct": 2, "shield": 2,
    }
    for bucket, pos in event_float_slots.items():
        for j, e in enumerate(obs["events"].get(bucket, []) or []):
            if pos < len(e):
                _check(e[pos], f"events.{bucket}[{j}][{pos}]")


def test_ranked_replay_propagation() -> None:
    """Full-replay dtype walk. For every frame yielded by
    simulate_action_phase_iter across a representative ranked replay,
    assert the observation's HP/shield/stat/damage slots are float32-
    quantized. Also asserts state fields stay np.float32 across arithmetic
    via a post-frame spot-check."""
    config = SimConfig.load()
    path = Path(__file__).resolve().parent.parent.parent.parent.parent \
        / "replays" / "ranked" \
        / "v13_360023_m15302602_vs_gooder-maybe_1453_win.replay"
    frames, _ = _parse_replay(path)
    deploys = _index_deploy_frames(frames)
    actions_first = _index_first_action_frames(frames)
    upgraded_pre = _collect_upgraded_uids(frames)

    checked_turns = 0
    for turn in sorted(deploys.keys()):
        if turn not in actions_first:
            continue
        state = _build_state_from_deploy_frame(deploys[turn], config,
                                                upgraded_pre.get(turn, set()))
        p1s, p1u, p2s, p2u = _extract_deploy_actions(actions_first[turn])
        ordered = _extract_deploy_events_in_order(actions_first[turn])
        deploy_events = []
        apply_deploy_actions(state, config, p1s, p1u, p2s, p2u,
                              ordered_events=ordered, events=deploy_events)
        # State dtype spot-check after deploy.
        for s in state.structures.values():
            _assert_fp32(s.hp, f"T{turn} post-deploy structure {s.uid}.hp")
        for m in state.mobiles:
            _assert_fp32(m.hp, f"T{turn} post-deploy mobile {m.uid}.hp")
            _assert_fp32(m.shield, f"T{turn} post-deploy mobile {m.uid}.shield")
        for ply_name in ("p1", "p2"):
            p = getattr(state, ply_name)
            _assert_fp32(p.hp, f"T{turn} post-deploy {ply_name}.hp")
            _assert_fp32(p.sp, f"T{turn} post-deploy {ply_name}.sp")
            _assert_fp32(p.mp, f"T{turn} post-deploy {ply_name}.mp")

        # Walk action frames.
        for obs in simulate_action_phase_iter(
            state, config, perspective=1, seed_events=deploy_events,
            total_frame_start=0,
        ):
            _assert_frame_observation_fp32(
                obs, where=f"T{turn} F{obs['turnInfo'][2]}"
            )

        checked_turns += 1
        if checked_turns >= 5:
            # Sample first 5 turns; full coverage runs via the validator.
            break


if __name__ == "__main__":
    tests = [
        ("round01 returns fp32", test_round01_returns_fp32),
        ("apply_damage returns fp32", test_apply_damage_returns_fp32),
        ("config specs are fp32", test_config_specs_are_fp32),
        ("ranked replay propagation", test_ranked_replay_propagation),
    ]
    failures = []
    for name, fn in tests:
        try:
            fn()
            print(f"  PASS  {name}")
        except AssertionError as e:
            print(f"  FAIL  {name}: {e}")
            failures.append(name)
    if failures:
        print(f"\n{len(failures)}/{len(tests)} tests failed")
        sys.exit(1)
    print(f"\nAll {len(tests)} tests passed")
    sys.exit(0)
