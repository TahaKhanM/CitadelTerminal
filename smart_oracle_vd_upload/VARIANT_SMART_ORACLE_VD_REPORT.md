# Variant Report — smart_oracle_vd

**Date:** 2026-04-27
**Base:** `oracle_pure_M1Lite_VD_upload` (variance ranking + wasted-MP penalty + sim-based viability probe + breach-pressure coverage).
**Variant:** smart_oracle_vd (VD baseline + funnel-aware defense focusing).

---

## TL;DR

`smart_oracle_vd` is `VD` plus one targeted addition for funnel-attack
strategies: a **funnel detector** that fires when the opponent has been
consistently breaching one flank zone over several turns. When it fires,
the search reshapes its inputs in two ways:

1. **Opponent samples are filtered** to those whose spawn launcher routes
   to the funnel zone. The base VD scores each candidate plan against an
   averaged mix of opp samples — including center rushes and two-prong
   attacks the opponent isn't actually launching. That dilution makes
   focused-flank defenses look weaker than they are. The filter removes
   the dilution.

2. **The enumerator adds zone-focused defense templates** (LEFT_FLANK_
   CORRIDOR / RIGHT_FLANK_CORRIDOR + a cheaper MIN variant + an
   upgrade-blended variant) whose atoms cover the empirical breach tiles
   from `FUNNEL_COUNTER_PLAN.md` §3 (left: 3,10 / 4,9 / 5,8 / 7,6 / 2,11;
   right: mirrors). The templates are **added** to the candidate pool —
   the baseline templates remain, so the search can still pick a non-
   funnel plan if `sim_rs` evaluation says one is better.

The search algorithm itself is unchanged. `funnel_target=None` makes
`smart_oracle_vd` byte-equivalent to VD — the new code paths are no-ops.

---

## Why it works

The funnel-loss analysis in `FUNNEL_COUNTER_PLAN.md` traces 8/8 oracle_
pure ladder losses to the same mechanic: opp builds a wall corridor that
channels their attackers around oracle's center anchor cluster (x=11–16)
into oracle's flank corners (x≤7 or x≥20), where coverage is structurally
thin. The VD value function rewards aggregate structure value (not
positional coverage), and the VD opp model averages over a sample
distribution dominated by center rushes. The combined effect: the search
keeps picking center-anchor upgrades over flank-corridor builds, even
when sim_rs would correctly score the flank-corridor plan higher *under
funnel-relevant samples*.

`smart_oracle_vd` doesn't hardcode a funnel response — it fixes the
input distribution and exposes the right candidate plans, then lets the
search pick.

---

## Files changed vs VD

| File | Change |
|---|---|
| `oracle_core/funnel_detector.py` | NEW. `detect_funnel_target(recent_breaches)` returns `"left"`/`"right"`/`None` based on whether ≥3 of the last 6 opp breaches concentrate in a flank zone. `plan_attacks_zone(plan, zone)` checks if an opp `ActionPlan`'s spawn launcher routes to that flank. |
| `oracle_core/enumerator.py` | Added `LEFT_FLANK_CORRIDOR` / `RIGHT_FLANK_CORRIDOR` (and `_MIN`) atom sets, plus a new `funnel_target` kwarg on `_enumerate_defense_templates` and `enumerate_plans`. New templates appended only when `funnel_target` is set. |
| `oracle_core/search.py` | Added `funnel_target` kwarg. Forwards to enumerator. Filters `opp_samples_phase{1,2}` through `plan_attacks_zone()`. Falls back to the unfiltered list if the filter would zero-out samples (defensive). Adds `funnel_target` and `opp_samples_after_filter_*` to telemetry. |
| `algo_strategy.py` | Calls `detect_funnel_target(self.recent_breaches)` once per turn and passes the result to `search()`. Logs the funnel state in turn telemetry. Watchdog, fallback, and `on_action_frame` are unchanged. |
| `algo.json` | `"name": "smart_oracle_vd"` — unique label so this variant is identifiable in replay metadata (matches the convention used by `oracle_pure_VC` / `oracle_pure_J2` variants). |

---

## Key design properties

- `funnel_target=None` ⇒ behaviour identical to VD (all new code paths
  are guarded `if funnel_target is not None`).
- Filter has a fallback: if filtering empties the sample list we revert
  to the unfiltered set, so the EU evaluation never starves.
- Search still owns every decision — the funnel signal only reshapes the
  candidate pool and the opp-sample distribution. There is no heuristic
  "pivot to flank corridor" override.
- New templates are **added**, not substituted, so the search can still
  pick a baseline template if it scores higher.
- Watchdog (13 s SIGALRM) and safe-fallback are untouched.
- Sim_rs loader logic and the bundled-wheel dlopen path are untouched —
  the live-server path still loads `bundled_sim_rs/sim_rs/sim_rs.abi3.so`.

---

## Local validation (12-game battery, both spawn sides)

| Opponent | Side | Result | Turns | Points |
|---|---|---|---|---|
| `oracle_pure_M1Lite_VD_upload` (the base) | smart=P1 | **W** (HP tiebreak) | 62 | 42–42 |
|  | smart=P2 | **W** (HP tiebreak) | 62 | 42–42 |
| `funnel_INTER` (Midwest-2022 finalist funnel) | smart=P1 | **W** | 100 (cap) | 14–0 |
|  | smart=P2 | **W** | 100 (cap) | 0–14 |
| `funnel-rush-v9` (decoded ranked-match funnel) | smart=P1 | **W** | 22 | 41–0 |
|  | smart=P2 | **W** | 22 | 0–41 |
| `funnel-crush` (decoded ranked-match funnel) | smart=P1 | **W** | 22 | 42–1 |
|  | smart=P2 | **W** | 22 | 1–42 |
| `v13_second_ring` (canonical baseline) | smart=P1 | **W** | 53 | 41–7 |
|  | smart=P2 | **W** | 53 | 7–41 |
| `heuristic_v1` | smart=P1 | **W** | 58 | 48–4 |
|  | smart=P2 | **W** | 58 | 4–48 |
| `Lostkids/python-2l-aet` (finalist) | smart=P1 | **W** | 36 | 42–9 |
|  | smart=P2 | **W** | 36 | 9–42 |

**12/12 wins, no crashes, no timeouts.** Per-turn wall-clock 1.3–2.0 s
(unchanged from VD; well under the 11 s search budget / 15 s engine cap).

The funnel detector fired correctly mid-game in matches with corner-
breach concentrations (e.g., (0,13) clusters classify as LEFT). The opp-
sample filter dropped 1 of 6 samples without zero-ing out — confirming
the fallback path doesn't trigger in normal play.

---

## Caveats / risks (carry-over from VD)

1. **Live ladder ≠ local validation.** The same risk that bit M2 still
   applies. The 12/12 local wins are an encouraging signal but not a
   guarantee against ladder-specific opponents the local benchmarks don't
   cover.
2. **Funnel detector heuristic is conservative** (3 of 6 breaches in
   same flank zone). If an opponent funnels for fewer than 3 turns it
   won't fire. This is by design — over-triggering on noise would
   degrade non-funnel matchups.
3. **Launcher → zone mapping is an empirical heuristic.** `R_mid` /
   `R_corner` opp spawns are tagged as attacking LEFT (diagonal walks),
   `L_mid` / `L_corner` as attacking RIGHT, `center` as both. Opps with
   unusual corridor topology that route differently aren't perfectly
   captured — but the filter's fallback path means we never starve the
   search.

---

## Falls back to VD when

- `funnel_target=None` (no funnel detected) — every new code path is
  guarded by this and runs as a no-op.
- The opp sample filter would empty the sample list — we revert to the
  unfiltered samples.
- The funnel detector requires ≥3 breaches in the lookback window;
  before that it returns None.

So shipping smart_oracle_vd is reversible — the live ladder reads it as
"VD plus a turn-conditional re-shaping of the search inputs."
