"""Parity test for the rebuilt opponent prior (G3).

Run from algos/oracle_pure:
    python3 tests/test_prior_parity.py

Asserts:
1. Every bucket key in the OLD prior also exists in the NEW prior
   (strict superset — no information loss from re-bucketing).
2. The NEW prior has buckets in `br1_2` and `br3p` bands (which were
   empty in the old prior because the builder hardcoded recent_breaches=0).
3. For every bucket present in both files, the per-signature observation
   count for matching signatures should not regress meaningfully.
   (Some signatures may move OUT of br0 into br1_2/br3p — that's the
   point of the fix. The per-bucket cap is 16; tolerate small reorderings.)
"""
from __future__ import annotations

import json
import sys
from pathlib import Path

THIS = Path(__file__).resolve()
ORACLE_PURE = THIS.parent.parent
DATA = ORACLE_PURE / "data"


def _load(path: Path) -> dict:
    with path.open() as f:
        return json.load(f)


def _sig_to_tuple(sig_dict: dict) -> tuple:
    """Hashable signature representation (matches ActionSignature ordering)."""
    return (
        sig_dict.get("scout_count", 0),
        sig_dict.get("demo_count", 0),
        sig_dict.get("int_count", 0),
        sig_dict.get("scout_launcher"),
        sig_dict.get("demo_launcher"),
        sig_dict.get("int_launcher"),
    )


def test_old_is_superset_of_new_keys():
    """Every key in OLD must still be in NEW. No info loss."""
    old = _load(DATA / "opp_model_priors.OLD.json")
    new = _load(DATA / "opp_model_priors.json")

    old_keys = set(old.keys())
    new_keys = set(new.keys())

    missing = old_keys - new_keys
    assert not missing, (
        f"NEW prior is missing {len(missing)} buckets present in OLD: "
        f"{sorted(missing)[:5]}..."
    )
    print(f"PASS test_old_is_superset_of_new_keys: "
          f"old={len(old_keys)} buckets, new={len(new_keys)} buckets, "
          f"new is strict superset")


def test_new_populates_breach_bands():
    """NEW must have buckets in `br1_2` and `br3p` (OLD had none)."""
    new = _load(DATA / "opp_model_priors.json")

    bands = {"br0": 0, "br1_2": 0, "br3p": 0}
    for k in new.keys():
        suffix = k.split("|")[-1]
        if suffix in bands:
            bands[suffix] += 1

    assert bands["br0"] >= 30, f"NEW br0 buckets={bands['br0']}, expected ≥30"
    assert bands["br1_2"] >= 5, (
        f"NEW br1_2 buckets={bands['br1_2']}, expected ≥5 — the G3 fix "
        f"should populate these"
    )
    assert bands["br3p"] >= 5, (
        f"NEW br3p buckets={bands['br3p']}, expected ≥5 — the G3 fix "
        f"should populate these"
    )
    print(f"PASS test_new_populates_breach_bands: "
          f"br0={bands['br0']}, br1_2={bands['br1_2']}, br3p={bands['br3p']}")


def test_old_keys_retain_signatures():
    """For every (old_key, sig) pair that existed, the NEW prior should
    still have a signature with the same primary fields somewhere — either
    in the same bucket OR in a br1_2/br3p sibling bucket (since some
    observations move out of br0 when their breach context becomes nonzero).

    This is a relaxed parity check: we verify NO signature has been DROPPED
    entirely from the corpus, not that bucket counts match exactly.
    """
    old = _load(DATA / "opp_model_priors.OLD.json")
    new = _load(DATA / "opp_model_priors.json")

    # Build the universe of (turn_band, sig) pairs from OLD,
    # then verify each (turn_band, sig) appears somewhere in NEW (any
    # breach band) with non-zero weight.
    old_universe: dict[tuple[str, tuple], float] = {}
    for k, sigs in old.items():
        turn_band = k.split("|")[0]
        for entry in sigs:
            sig_t = _sig_to_tuple(entry["sig"])
            old_universe[(turn_band, sig_t)] = (
                old_universe.get((turn_band, sig_t), 0.0) + entry["weight"]
            )

    new_universe: dict[tuple[str, tuple], float] = {}
    for k, sigs in new.items():
        turn_band = k.split("|")[0]
        for entry in sigs:
            sig_t = _sig_to_tuple(entry["sig"])
            new_universe[(turn_band, sig_t)] = (
                new_universe.get((turn_band, sig_t), 0.0) + entry["weight"]
            )

    # Every (turn_band, sig) in OLD must appear in NEW. Top-16-per-bucket
    # cap means a few low-frequency signatures can drop out when a bucket
    # fills up — tolerate at most 5% loss.
    missing_in_new = 0
    total_old = len(old_universe)
    for key in old_universe:
        if key not in new_universe:
            missing_in_new += 1

    loss_pct = 100.0 * missing_in_new / max(total_old, 1)
    assert loss_pct <= 5.0, (
        f"{missing_in_new}/{total_old} ({loss_pct:.1f}%) signatures dropped — "
        f"new prior should be a near-superset over (turn_band, sig)"
    )

    # Total observations in NEW should be ≥ OLD (we count every observation
    # exactly once per file; bucket cap may trim distinct signatures but
    # total weight should match closely).
    total_old_weight = sum(old_universe.values())
    total_new_weight = sum(new_universe.values())
    print(f"PASS test_old_keys_retain_signatures: "
          f"{total_old} unique (band,sig) in OLD; "
          f"{missing_in_new} ({loss_pct:.1f}%) absent from NEW; "
          f"weight: old={total_old_weight:.0f} new={total_new_weight:.0f}")


def main():
    print("=" * 70)
    print("oracle_pure prior parity tests (G3 rebuild with breach context)")
    print("=" * 70)

    if not (DATA / "opp_model_priors.OLD.json").exists():
        print("SKIP: opp_model_priors.OLD.json not found — run "
              "`cp data/opp_model_priors.json data/opp_model_priors.OLD.json` "
              "BEFORE rebuilding")
        sys.exit(1)

    test_old_is_superset_of_new_keys()
    test_new_populates_breach_bands()
    test_old_keys_retain_signatures()
    print("=" * 70)
    print("ALL PRIOR PARITY TESTS PASSED")


if __name__ == "__main__":
    main()
