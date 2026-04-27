# Variant IS4 — Multiple SUPPORT_POSITION_SETS for VB's probe to differentiate

**Status**: Validated locally; recommended for live ladder upload.

**Base**: `oracle_pure_M1Lite_VF_upload` (M1Lite + VA + VB; no VC).

## Goal

Replace the single hardcoded `SUPPORTS_BACK = [(12,11),(15,11),(13,10),(14,10)]`
with **five distinct candidate position sets**, exposed to the search as
separate defense templates (`defense:supports_v0`..`v4`,
`defense:supports_only_v0`..`v4`).

VB's viability probe can then differentiate trap-forming layouts (where
a layout seals the wall-row gaps at `(12,12)/(15,12)` and traps our own
offense) from non-trap layouts: the trap-forming set returns `breach=0`
on the probe and gets a `-250` trap penalty (`5 missing × 50 weight`),
while non-trap sets return `breach=5/5` and get no penalty.

## What changed vs VF

### `oracle_core/enumerator.py`
- Replaced the single `SUPPORTS_BACK` constant with `SUPPORT_POSITION_SETS`
  containing five geometrically-distinct candidate layouts (see "The 5
  position sets" below).
- Kept `SUPPORTS_BACK` as a back-compat alias pointing at v0.
- Replaced the single `defense:supports` template with a loop generating
  `defense:supports_v0`..`v4` (each = supports + ANCHOR_TURRETS).
- Replaced the single `defense:supports_only` template with a loop
  generating `defense:supports_only_v0`..`v4`.
- Added `SUPPORT_POSITION_SETS` to `__all__`.

### `oracle_core/search.py`
- Added one debug log line per turn: `[IS4] support_probes: supports_v0=N supports_v1=N ...`
  to verify the probe is differentiating between trap and non-trap layouts
  in live play.
- Probe caching by `def_name` (e.g., `defense:supports_v0`) is unchanged —
  each variant gets its own cache entry so probe results don't bleed across
  variants.

### `algo_strategy.py`
- Kept `max_plans = 2500` (matching VF). An earlier attempt to bump
  this to 3500 caused a snorkeldink regression via tie-breaking shifts in
  phase-2 top-30 selection — see "Iteration history" below.

## The 5 position sets

```python
SUPPORT_POSITION_SETS = [
    # v0: original (potential trap — sealing both gaps at (12,12)/(15,12))
    [(12, 11), (15, 11), (13, 10), (14, 10)],
    # v1: ALT-OUTSIDE (moves outer supports outside the gap area)
    [(10, 11), (17, 11), (13, 10), (14, 10)],
    # v2: deeper row (well behind walls — minimal trap risk)
    [(12, 9), (15, 9), (13, 9), (14, 9)],
    # v3: mid-flank (y=10 with center supports at anchor positions)
    [(11, 10), (16, 10), (13, 11), (14, 11)],
    # v4: wider outside (uses outer y=11 tiles + center anchors)
    [(8, 11), (19, 11), (13, 11), (14, 11)],
]
```

The sets are derived from board geometry — they cover the natural
support placement options behind the wall row at y=12 (with gaps at
x=12/15) plus deeper-row and outside-of-gap alternatives. **They are
NOT pre-ranked** — the search picks based on probe + sim_eval.

Note: v3 and v4 use anchor-turret positions `(13,11)` and `(14,11)`
for their center supports. Because supports are placed first in the
template's atom order, they take those tiles and the matching anchor
turrets are skipped. This means v3/v4 trade 2 anchor turrets for
supports — a fundamentally different defense shape. Including them
gives the search more options, not fewer; it's free to ignore them
if the value function doesn't favor them.

## Probe differentiation evidence

Standalone test (state: WALL_ROW_Y12 placed, no opp structures, supports
applied per variant):

| Variant | Probe breach | Verdict |
|---|---|---|
| v0 — `[(12,11),(15,11),(13,10),(14,10)]` | **0/5** | TRAP detected |
| v1 — `[(10,11),(17,11),(13,10),(14,10)]` | 5/5 | OK |
| v2 — `[(12,9),(15,9),(13,9),(14,9)]` | 5/5 | OK |
| v3 — `[(11,10),(16,10),(13,11),(14,11)]` | 5/5 | OK |
| v4 — `[(8,11),(19,11),(13,11),(14,11)]` | 5/5 | OK |

This confirms the probe correctly identifies v0 as the trap-forming
layout when wall-row pre-conditions are present, and the other four
variants as viable. The search will give v0 a `-250` trap penalty in
this state, naturally pushing it toward one of the four alternatives.

## Search-uses-diversity evidence

In live local matches, the search picks DIFFERENT variants based on
game state:

| Opponent | Picks observed |
|---|---|
| Lostkids/python-2l-aet | `supports_v0` ×2, `supports_v1` ×1 |
| heuristic_v1 | `supports_v0` ×2, `supports_v2` ×2, `supports_v3` ×2, `supports_only_v1` ×1 |
| python-algo | `supports_v0` ×1, `supports_v2` ×1 |
| snorkeldink-v3-1 | (none — matchup doesn't favor supports) |

Crucially, **vs heuristic_v1 the search picked 4 different variants
across the match** — direct evidence the diversity mechanism is
exercised.

In the test matches, the probe never returned `0` — i.e., no trap
state was actually triggered in normal play. The differentiation is
state-dependent. The trap originally manifested in long
100-turn games against `suchir-g/python-algo-baseline` and
`not-tnb/python-algo-tnb` where the wall row + supports combination
specifically formed during long stable games — exactly the situation
the standalone test reproduces.

## Validation results

All matches run with `C1GamesStarterKit-master/scripts/run_match.py`.
Each match deterministic and reproducible; results below confirmed by
multiple replays where stochasticity was a concern.

### Tier A (must pass 100%)

| Opponent | IS4 as P1 | IS4 as P2 |
|---|---|---|
| v13_second_ring | WIN | WIN |
| python-algo | WIN | WIN |
| heuristic_v1 | WIN | WIN |
| Lostkids/python-2l-aet | WIN | WIN |
| funnel_INTER | WIN | WIN |

**Tier A: 10/10 ✓**

### Tier B (Breakthrough preservation; ≥1/2 required)

| Opponent | IS4 as P1 | IS4 as P2 |
|---|---|---|
| snorkeldink-v3-1 | WIN | WIN |

**Tier B: 2/2 ✓** (Confirmed reproducible: 6/6 across 3 replays each side.)

### H2H vs baselines

| Baseline | IS4 as P1 | IS4 as P2 |
|---|---|---|
| oracle_pure_M1Lite_VF (VF) | WIN | WIN |
| oracle_pure_M1Lite (M1Lite) | WIN | WIN |

**H2H: 4/4 ✓** (Confirmed reproducible: 6/6 vs each baseline across 3 replays.)

**Total local validation: 16/16 wins.**

## Iteration history (what failed before settling on the final config)

### Iteration 1: 5 sets + max_plans bumped to 3500 → snorkeldink regression

First attempt: bumped `max_plans` from 2500 to 3500 to "make room" for
the +8 new defense templates (5 new `defense:supports_v*` + 5 new
`defense:supports_only_v*`).

**Result**: Tier A 10/10 ✓ but Tier B (snorkeldink) 0/2 ✗ — VF wins
2/2, IS4 lost both.

**Root cause**: bumping the cap exposed MORE plans to phase-2 top-30
selection. With many tied scores (a known property of the value
function early-game), the larger candidate pool changed which non-supports
plan tied-and-survived to phase-2. This cascaded into a different game
trajectory that snorkeldink exploited. The trap-fix mechanism (probe
penalty) never even fired in these matches — the regression came purely
from the side-effect of changing the cap.

### Iteration 2: 3 sets only (drop v3, v4) + cap stayed at 3500 → still regressed

Tried reducing to 3 sets (v0, v1, v2 — the ones that don't conflict
with anchor turrets). Still failed snorkeldink.

**Conclusion**: the regression was driven by `max_plans`, not by which
support sets we offered.

### Iteration 3 (FINAL): 5 sets + cap=2500 → all green

Restored `max_plans=2500`. Snorkeldink 2/2 ✓. All other tests pass.

The cap=2500 acts as a stable filter on the cross-product. Some
support templates may get cut off in mid-game when SP allows many
defense templates to be feasible — but this is fine because:
1. The lost templates are at the END of the enumeration order
   (`upg{16}`, `refund{2}`, `patch{2}`, `skeleton_upg`)
2. The new `supports_v*` templates appear EARLIER in enumeration
   (positions 14-16 + 21-23 of ~33)
3. The probe still differentiates among the supports variants that DO
   make it through

## Lesson re-learned (consistent with §15-§16)

The protocol's warning: "If Tier A regresses: too many candidates?
Reduce to 3 most promising sets" was correct, but the actual
mechanism wasn't candidate-count itself — it was the search-cap
interaction. **Side effects of plumbing changes (cap bumps, candidate
ordering, tie-break heuristics) can be larger than the intended logic
change.**

This matches §16's warning that M2's BFS check (a logic addition) was
benign but its hardcoded ALT-OUTSIDE swap (a "necessary triage" tile
patch) caused the regression. The pattern: anything that changes which
plan wins ties is risky.

## What this variant gets RIGHT vs M2

- **No hardcoded tile-list replacement.** The original v0 is still in
  the candidate set; the search isn't forced away from it.
- **Search owns the choice.** v0 is preferred when probe shows no trap
  AND sim_eval favors it; alternatives are preferred when the probe
  says trap OR when sim_eval finds a better placement.
- **Diversity is principled.** All 5 sets are derived from board
  geometry (where supports could plausibly go behind the wall row);
  none are tuned to a specific opponent.
- **No mode-switching.** No archetype detection; no opponent-specific
  branching. The value function + probe drive the choice.

## Risks / caveats

1. **No live data yet.** All claims about live ladder behavior are
   extrapolation from local validation.
2. **Probe never triggered in test matches.** The trap state requires
   specific conditions (wall row built + supports in v0 positions) that
   were rare in the test matchups. Live ladder games against
   `suchir-g/python-algo-baseline`, `not-tnb/python-algo-tnb`, and
   similar long-game opponents are where the probe is expected to
   actually fire and provide the discriminating signal.
3. **v3 and v4 trade 2 anchor turrets for supports.** This is a
   significant defense-shape change. If it turns out to be
   strictly worse, the search will simply never pick those variants —
   no harm done — but they take phase-1 evaluation time (~5 sims each
   per turn).
4. **Cap=2500 means some `defense:supports_only_v*` templates get cut
   off in high-template mid-game turns.** Per analysis of test logs,
   `defense:supports_only_v*` only appears in 2 of ~80 turns due to
   cap filtering. The `defense:supports_v*` (with anchors) variants
   make it through more reliably because they're earlier in
   enumeration order.

## File map

- `oracle_core/enumerator.py` — SUPPORT_POSITION_SETS + the two `for`
  loops that generate the variant templates
- `oracle_core/search.py` — added `[IS4] support_probes:` debug line
- `algo_strategy.py` — note explaining `max_plans=2500` (unchanged from VF)
- `VARIANT_F_REPORT.md` — the prior VF report (kept for context)
- `CONTEXT_HANDOFF.md` — full project history, especially §15-§16

## Conclusion

IS4 is a principled, search-driven extension of VF that gives the
viability probe more candidate layouts to differentiate. It passes all
local validation gates with no regressions. The mechanism is consistent
with the project's anti-pattern rules (no hardcoded tile patches; no
opponent-specific branching). Recommended for live ladder upload as
`oracle_pure_M1Lite_IS4`.
