# IS6 + smart_oracle_vd — compatibility analysis

## What each variant actually changes

### IS6 = "Variant F" (VA + VB + smart-refund, **VC removed**)

Diffed against M1Lite baseline:

| file | change |
|---|---|
| `oracle_core/value.py` | adds VA-I3 wasted-MP penalty + VB-I2 trap penalty (`defense_viability` param). Docstring explicitly states **"VC (I4) breach-pressure coverage was REMOVED based on live ladder evidence that it caused regression on pipmy/Redemption (VD lost where VA won, the only difference being VC's coverage term)"** |
| `oracle_core/search.py` | adds VA-I7 variance-aware ranking + VB viability probe loop. **Does not accept `breach_pressure` param.** |
| `oracle_core/viability_probe.py` | NEW (same file as VD's) |
| `oracle_core/enumerator.py` | adds `_gen_refund_smart()` — a probe-driven refund generator that for each own structure clones the base state, removes it, re-runs the offensive viability probe, and emits a refund template if removal restores ≥3/5 probe scouts. Plans tagged `archetype="refund_smart"` are sorted to the **front** of the defense list so they get cross-producted with offense templates first. |
| `algo_strategy.py` | inherits VD shell minus the VC breach-pressure tracking (no `self.breach_pressure`, no decay, no telemetry) |

### smart_oracle_vd = VD + funnel addition (**keeps VC**)

Diffed against VD baseline:

| file | change |
|---|---|
| `oracle_core/funnel_detector.py` | NEW — `detect_funnel_target(recent_breaches)` returns `"left"`/`"right"`/`None`; `plan_attacks_zone(plan, zone)` filters opp ActionPlans by launcher routing |
| `oracle_core/enumerator.py` | adds `LEFT_/RIGHT_FLANK_CORRIDOR`(+ `_MIN`) atom sets and a conditional template block gated by `funnel_target` kwarg |
| `oracle_core/search.py` | accepts `funnel_target` kwarg; filters `opp_samples_phase{1,2}` through `plan_attacks_zone()` when set; forwards `funnel_target` to enumerator |
| `algo_strategy.py` | calls `detect_funnel_target(self.recent_breaches)` and passes result to `search()` |

Inherited from VD (still active in smart): VA-I3 wasted-MP penalty, VA-I7 variance ranking, VB viability probe, **VC-I4 breach-pressure coverage**.

---

## Surface-level conflict map

| dimension | IS6 | smart | conflict? |
|---|---|---|---|
| `oracle_core/plan.py` | identical | identical | **NO** |
| `oracle_core/viability_probe.py` | identical | identical | **NO** |
| `defense_viability` field on `ActionPlan` | uses it | uses it | **NO** (same usage) |
| VA-I3 wasted-MP penalty (`value.py`) | present | present | **NO** (same code) |
| VA-I7 variance ranking (`search.py`) | present | present | **NO** (same code) |
| VB viability probe loop (`search.py`) | present | present | **NO** (same code) |
| **VC breach-pressure coverage (`value.py`)** | **deliberately REMOVED** | **present (inherited from VD)** | **YES — direct conflict** |
| Smart-refund template generator (`enumerator.py`) | present | absent | **NO** (additive) |
| Flank-corridor templates (`enumerator.py`) | absent | present | **NO** (additive — different atoms) |
| `funnel_target` kwarg (`enumerator.py`, `search.py`) | absent | present | **NO** (additive — defaults to None) |
| Opp-sample filter (`search.py`) | absent | present | **NO** (additive — only fires when `funnel_target` set) |
| `recent_breaches` tracking (`algo_strategy.py`) | present (IS6 needs it for opp model bucket) | present (smart needs it for funnel detector) | **NO** (same data, both consume) |
| `breach_pressure` map tracking (`algo_strategy.py`) | absent | present | depends on VC decision |

**Only one real conflict: VC.** Everything else is either additive or unchanged.

---

## The VC decision — and why it matters for the merge

IS6 deleted VC because of measured live-ladder regression on `pipmy/Redemption`. Looking at the per-pair replay data I just pulled:

| match | VD (with VC) | IS6 (no VC) |
|---|---|---|
| `Redemption / pipmy` | **L 0/1 hp−16** | **W 1/1 hp39** |

That's a 55-HP swing on the same opponent algo. **smart_oracle_vd inherited the same VC and lost the same matchup:**

| match | smart (with VC) |
|---|---|
| `Redemption / pipmy` | **L** (match 15316994, opp Redemption, 66 turns) |

So **the merge MUST drop VC** — keeping it negates IS6's deliberate fix. The funnel detector smart added does NOT depend on VC; it uses the `recent_breaches` list directly. VC is a separate signal that fed `_coverage_value` in `value.py`. Removing VC has zero effect on funnel detection.

---

## Verdict — **compatible, with one constraint**

Yes, the two algos can be combined cleanly. Their additions are largely orthogonal:

- **IS6's smart-refund** asks the simulator "which of my structures is causing my own scouts to die?" and emits remove-templates. This is a defensive cleanup mechanism.
- **Smart's funnel detector** asks "is the opponent consistently attacking one flank?" and reshapes the opp sample distribution + adds zone-focused defense templates. This is an opponent-modeling fix.

They touch the same files (`enumerator.py`, `search.py`, `algo_strategy.py`) but at different points and via additive new code — neither replaces or undercuts the other.

**The one constraint: drop VC** (don't carry over smart's `breach_pressure` map / `_coverage_value` / `breach_pressure` kwarg). VC and the funnel detector are independent — removing VC keeps the funnel work intact and aligns with IS6's verified live-regression fix.

---

## Concrete merge plan

**Base on IS6** (it's the proven Variant F, currently the highest-rated of the M1Lite line at ELO 2006).

| component | source | change |
|---|---|---|
| `oracle_core/funnel_detector.py` | smart | copy verbatim |
| `oracle_core/enumerator.py` | IS6 | add `LEFT_/RIGHT_FLANK_CORRIDOR` (+ `_MIN`) atom sets near other atom defs; add `funnel_target: Optional[str] = None` kwarg to both `_enumerate_defense_templates` and `enumerate_plans`; add the conditional flank-corridor template block; **keep IS6's smart-refund block as-is** |
| `oracle_core/search.py` | IS6 | add `funnel_target: Optional[str] = None` kwarg + opp-sample filter block + `funnel_target` field on `SearchTelemetry`; forward to enumerator. **Do NOT add `breach_pressure` kwarg** — IS6's value.py doesn't accept it |
| `oracle_core/value.py` | IS6 | unchanged (no VC) |
| `oracle_core/plan.py` | IS6 (= VD = smart) | unchanged |
| `oracle_core/viability_probe.py` | IS6 (= VD = smart) | unchanged |
| `algo_strategy.py` | IS6 | add `from .funnel_detector import detect_funnel_target`; add detection call before `search()` and pass `funnel_target=funnel_target`. **Do NOT add the `breach_pressure` map or its decay**. `recent_breaches` tracking is already there |
| `algo.json` | new | name e.g. `"smart_oracle_F"` or whatever the user prefers |

**Effective behaviour:**
- Non-funnel game with no trap-prone structures → behaves exactly like IS6 (the strongest M1Lite variant).
- Trap detected (probe shows ≥3/5 missing breaches) → IS6's smart-refund fires.
- Funnel detected (≥3/6 recent breaches in same flank) → smart's funnel-target opp-sample filter + flank-corridor templates fire.
- Both fire simultaneously → search evaluates the cross-product of trap-fix-refund × flank-corridor + offense, picks max-EU.

The two new candidate-template generators add ~3 + ~3 plans to a candidate pool already sized at 2,500. No combinatorial blowup, no perf risk.

**Estimated lift over IS6:** the funnel addition's value is concentrated on `funnel-rush-v7 ameyg` (where VD/M1Lite get crushed and IS6 hasn't yet been measured). IS6 hasn't faced ameyg/funnel-rush-v7 in the live data — only earlier `funnel-rush-v6` (which it lost). The merge gives IS6 the funnel-defense it currently lacks while keeping its smart-refund anti-trap edge.

**Risk:** smart's funnel detection adds zone-focused templates with ~21 SP cost each. These templates are only ADDED to the candidate set; the search picks them only if sim_rs scores them best. No risk to non-funnel matchups beyond the negligible enumeration overhead.
