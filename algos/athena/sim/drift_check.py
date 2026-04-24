"""Drift check: verify citadel_config_snapshot.json matches the live engine.

The live server delivers the game config via the first JSON line of a replay
(`unitInformation` + `resources` blocks). This script compares our snapshot's
`_raw_unit_information` and `_resources_block_verbatim` against one of those
live blocks and fails loudly on any gameplay-relevant divergence.

Gameplay-relevant fields are the ones SimCore's copy-then-patch loader
actually reads — see config._ENGINE_FIELDS. UI fields (iconxScale,
iconyScale, icon, display) are audited but do not fail the gate.

Usage:
  python3 -m algos.athena.sim.drift_check [replay_path]
  python3 -m algos.athena.sim.drift_check [replay_path] --regenerate

  --regenerate overwrites _raw_unit_information and _resources_block_verbatim
  in the snapshot from the replay's live config. Always re-run drift_check
  WITHOUT --regenerate afterward to confirm zero drift.

Exit code: 0 if gameplay-relevant drift == 0, else 1.
"""

from __future__ import annotations

import json
import sys
from pathlib import Path
from typing import Any, Dict, List, Tuple

from .config import _ENGINE_FIELDS, _UI_FIELDS

_REPO = Path(__file__).resolve().parent.parent.parent.parent
_SNAPSHOT_PATH = _REPO / "algos" / "athena" / "data" / "citadel_config_snapshot.json"
_DEFAULT_REPLAY_GLOB = _REPO / "replays" / "ranked" / "*.replay"


def _load_snapshot(path: Path = _SNAPSHOT_PATH) -> Dict[str, Any]:
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def _load_replay_config(replay_path: Path) -> Dict[str, Any]:
    """First JSON line with `unitInformation` is the engine's live config."""
    with open(replay_path, "r", encoding="utf-8") as f:
        for line in f:
            s = line.strip()
            if not s.startswith("{"):
                continue
            try:
                d = json.loads(s)
            except Exception:
                continue
            if "unitInformation" in d:
                return d
    raise ValueError(f"no config line found in {replay_path}")


def _compare_units(
    snap_units: List[Dict[str, Any]],
    live_units: List[Dict[str, Any]],
) -> Tuple[List[str], List[str]]:
    """Compare per-shorthand unit dicts. Returns (gameplay_drifts, ui_drifts).
    Each drift is a human-readable string."""
    gameplay: List[str] = []
    ui: List[str] = []
    snap_by = {u.get("shorthand"): u for u in snap_units}
    live_by = {u.get("shorthand"): u for u in live_units}
    shorthands = sorted(set(snap_by) | set(live_by))
    gameplay_keys = set(_ENGINE_FIELDS.keys())
    for sh in shorthands:
        su = snap_by.get(sh) or {}
        lu = live_by.get(sh) or {}
        if not su:
            gameplay.append(f"{sh}: missing in snapshot")
            continue
        if not lu:
            gameplay.append(f"{sh}: missing in live")
            continue
        # Base-level (excluding nested upgrade)
        for k in sorted(set(su) | set(lu)):
            if k == "upgrade":
                continue
            sv = su.get(k); rv = lu.get(k)
            if sv == rv:
                continue
            line = f"{sh}.{k}: snap={sv} live={rv}"
            if k in gameplay_keys:
                gameplay.append(line)
            else:
                ui.append(line)
        # Upgrade block
        su_up = su.get("upgrade") or {}
        lu_up = lu.get("upgrade") or {}
        if su_up or lu_up:
            for k in sorted(set(su_up) | set(lu_up)):
                sv = su_up.get(k); rv = lu_up.get(k)
                if sv == rv:
                    continue
                line = f"{sh}.upgrade.{k}: snap={sv} live={rv}"
                if k in gameplay_keys:
                    gameplay.append(line)
                else:
                    ui.append(line)
    return gameplay, ui


def _compare_resources(
    snap_res: Dict[str, Any],
    live_res: Dict[str, Any],
) -> List[str]:
    """Compare resources blocks. All resources fields are gameplay-relevant
    except `coresForPlayerDamage` (legacy pre-season-5; unused)."""
    drifts: List[str] = []
    for k in sorted(set(snap_res) | set(live_res)):
        sv = snap_res.get(k); rv = live_res.get(k)
        if sv == rv:
            continue
        if k == "coresForPlayerDamage":
            # Legacy field — engine post-season-5 ignores this. SimCore
            # carries it into Resources for back-compat but never reads it.
            continue
        drifts.append(f"resources.{k}: snap={sv} live={rv}")
    return drifts


def check_drift(replay_path: Path, snapshot_path: Path = _SNAPSHOT_PATH) -> int:
    """Run drift check. Returns 0 on clean, 1 on gameplay-relevant drift."""
    snap = _load_snapshot(snapshot_path)
    live = _load_replay_config(replay_path)

    snap_units = snap.get("_raw_unit_information") or []
    live_units = live.get("unitInformation") or []
    gameplay, ui = _compare_units(snap_units, live_units)

    snap_res = snap.get("_resources_block_verbatim") or {}
    live_res = live.get("resources") or {}
    res_drifts = _compare_resources(snap_res, live_res)
    gameplay.extend(res_drifts)

    print(f"drift_check: snapshot={snapshot_path.name}  live={replay_path.name}")
    if gameplay:
        print(f"\n  GAMEPLAY DRIFT ({len(gameplay)}):")
        for line in gameplay:
            print(f"    ✗ {line}")
    else:
        print("\n  GAMEPLAY DRIFT: none  ✓")
    if ui:
        print(f"\n  UI DRIFT ({len(ui)}):")
        for line in ui:
            print(f"    · {line}")
    print()
    if gameplay:
        print(f"DRIFT_CHECK: FAIL — {len(gameplay)} gameplay-relevant mismatches")
        return 1
    print("DRIFT_CHECK: PASS")
    return 0


def regenerate(replay_path: Path, snapshot_path: Path = _SNAPSHOT_PATH) -> None:
    """Overwrite snapshot's _raw_unit_information + _resources_block_verbatim
    with the live replay's blocks. Preserves all other metadata fields."""
    snap = _load_snapshot(snapshot_path)
    live = _load_replay_config(replay_path)
    snap["_raw_unit_information"] = live.get("unitInformation") or []
    snap["_resources_block_verbatim"] = live.get("resources") or {}
    snap["_verification_source"] = replay_path.name
    # Drop stale derived-lookup block if present.
    snap.pop("_derived_lookup_for_sim_core", None)
    with open(snapshot_path, "w", encoding="utf-8") as f:
        json.dump(snap, f, indent=2)
    print(f"regenerated {snapshot_path} from {replay_path.name}")


def _default_replay() -> Path:
    """First ranked replay — stable canonical choice. Fails loudly if absent."""
    candidates = sorted((_REPO / "replays" / "ranked").glob("*.replay"))
    if not candidates:
        raise FileNotFoundError(f"no replays found under {_REPO / 'replays' / 'ranked'}")
    return candidates[0]


if __name__ == "__main__":
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    flags = [a for a in sys.argv[1:] if a.startswith("--")]
    replay = Path(args[0]) if args else _default_replay()
    if "--regenerate" in flags:
        regenerate(replay)
    else:
        sys.exit(check_drift(replay))
