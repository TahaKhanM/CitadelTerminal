# Citadel-delta audit — athena_baseline_lostkids

Date: 2026-04-25
Reviewer: Athena Phase 1.5 agent (Claude Opus 4.7)
Source under audit: `algos/athena_baseline_lostkids/algo_strategy.py` and
`defense-order.json`, ported from
`research/finalist_repos/Terminal-Lostkids/python-2l-aet/`.
Reference: `docs/UNITS_REFERENCE.md` § "Citadel deltas vs base game" and
`algos/athena_v3_planner/data/citadel_config_snapshot.json` (live values).

The four Citadel rule deltas called out by the build plan are reviewed
below. Every numeric is cross-checked against the live config snapshot.

| Live config snapshot value | Citadel | (Base game per docs) |
|---|---|---|
| Wall (FF) base SP cost                | 1   | 1   |
| Wall (FF) upgrade SP cost             | **2**   | 0   |
| Support (EF) base SP cost             | 4   | 4   |
| Support (EF) upgrade SP cost          | **2**   | 4   (docs page incorrectly says +4; live config says +2) |
| Support base startHealth              | 1   | 30  (base game) |
| Support upgraded startHealth          | 40  | 30  |
| Support shieldPerUnit base / upgraded | 3 / 1   | 3 / 3   |
| Support shieldBonusPerY (upgraded)    | **0.7** | 0   |
| Support shieldRange base / upgraded   | 3.5 / **7** | 3.5 / 3.5 |
| Turret base damage / upgraded         | 6 / **20**  | 6 / 16  |
| Turret base HP / upgraded             | 60 / **100**| 60 / 75 |
| MP decay per round                    | **0.25**| 0.25 |

---

## 1. Wall upgrade now costs SP (was free)

**Lostkids behaviour**: `block_left_edge` and `block_right_edge` call
`game_state.attempt_upgrade(location)` on the edge walls when
`enemy_MP > UPGRADE_EDGE_WALL_THRESHOLD = 15`.

**Cost lookup**: `attempt_upgrade` -> `type_cost(unit, upgrade=True)` ->
reads `config["unitInformation"][index]["upgrade"]["cost1"]`. In the
live Citadel config that's **2 SP**. So the cost is config-driven and
already correct.

**Strategic concern** (NOT patched here): in the base game, upgrading a
wall was free, so blocking your edge with an upgraded wall was a tempo
freebie. In Citadel it costs 2 SP per wall upgrade, and Lostkids
upgrades *each* of the two edge walls every time the threshold trips,
so each defensive edge-block costs an extra 4 SP vs base. The
build-order's `min_sp_to_save = 0` budget gating is preserved
unchanged — Lostkids will simply skip later defenses if SP runs out.
This may bleed off SP that should have gone to permanent turrets, but
fixing it would require non-trivial budget logic and is out of scope
for a baseline port.

**Verdict**: NO CODE CHANGE. The port is config-correct. Strategic
sub-optimality flagged for future tuning (Phase 2+ defense engine).

---

## 2. Support shield formula changed

**Lostkids behaviour**: `defense-order.json` places upgraded Supports
at Y=5 and Y=6 (4 of them: `[11,5]`, `[11,6]`, `[16,5]`, `[16,6]`),
plus a later wave at `[10,5]`, `[17,5]`. Lostkids itself does NOT
hardcode any shield numbers; the engine uses the live config formula
(`shield_per_unit = 1 + 0.7 × Y` for upgraded, range 7).

**Citadel-specific concern**: `docs/UNITS_REFERENCE.md` warns
"DON'T upgrade a Support at Y<3 — the per-unit shield would be *worse*
than leaving it at base. Always place upgraded Supports at Y≥7;
ideally Y=13." Lostkids' Y=5/Y=6 placements give:

- Y=5: `1 + 0.7 × 5 = 4.5` shield/Scout (vs base flat 3 — only +1.5)
- Y=6: `1 + 0.7 × 6 = 5.2` shield/Scout (vs base 3 — only +2.2)

These are *better than base* but well below the Y≥7 threshold the
docs recommend. They were optimal for the base game (where upgraded
shield was a flat 3 too, so any Y was fine and Y=5/6 reduced wall
exposure). They are **suboptimal but not broken** for Citadel.

**Verdict**: NO CODE CHANGE in this port (intentional). A future
Athena variant should either (a) push these Supports back to Y=10–13
or (b) add a runtime guard that reads `shieldBonusPerY > 0` and adapts
placement. For a baseline port we keep Lostkids' build order verbatim
so the regression match-up is meaningful (we are measuring Athena vs
known-Lostkids, not Athena vs an "improved Lostkids" we hand-tuned).

---

## 3. Turret upgrade is unusually strong in Citadel

**Lostkids behaviour, two surfaces**:

a. **`defense-order.json` upgrade pattern**: turret-upgrades feature
   prominently — three of seven build phases are turret-upgrade-heavy.
   Phase 4 spawns + immediately upgrades 6 corner turrets in pairs
   (`[2,13]…[24,13]`). Phase 6 upgrades 4 mid-row turrets and adds 2
   more upgraded turrets (`[12,8]`, `[15,8]`). This is consistent with
   Citadel's 20-damage / 100-HP upgrade being the strongest SP
   investment in the game.

b. **`compute_enemy_*_edge_defense_strength` heuristic**: scores
   upgraded turrets as `25 / distance_to_edge` and base turrets as
   `5 / distance_to_edge`. Ratio 5×. Citadel's actual damage ratio is
   20/6 ≈ 3.33×, plus range goes 2.5 → 3.5 (frame-coverage roughly
   `(3.5/2.5)² ≈ 1.96×`), so total impact ≈ 6.5×. Lostkids' 5× weight
   is in the right ballpark; it slightly *under-weights* upgraded
   turrets vs Citadel's true value, which means Lostkids will
   sometimes commit to attack against an upgraded edge it should
   actually defend against. Not a bug, just a calibration drift.

**Verdict**: NO CODE CHANGE. The Citadel boost makes Lostkids' upgrade-
heavy build order *more* aligned with the meta, not less. The threat-
score calibration is conservative (under-weighting upgrades) which is
safer than the opposite and inside the noise floor of baseline tuning.

---

## 4. MP decay is 25 %

**Live config**: `bitDecayPerRound = 0.25` — same as the base game.
This delta is a documentation reminder rather than a Citadel-specific
change.

**Lostkids MP thresholds**:
- `BLOCK_EDGE_ENEMY_MP_THRESHOLD = 12`
- `UPGRADE_EDGE_WALL_THRESHOLD = 15`
- `attack` mode threshold `self.my_MP < 17` -> defend
- `choose_number_of_interceptor_based_on_enemy_MP = max(enemy_MP/4, 3)`
- `choose_number_of_scouts_in_first_group = min(5 + strength/7, 10)`

These are absolute MP thresholds, not decay-coupled. They still hold
under Citadel's 25% decay (which is identical to base game decay).

**Verdict**: NO CODE CHANGE.

---

## 5. Other config reads — sanity sweep

```bash
grep -n "config\[" algos/athena_baseline_lostkids/algo_strategy.py
```

All accesses are inside `on_game_start(config)` and read named keys
from `config["unitInformation"][i]`:
- shorthands (indices 0–5)
- `cost2` for mobile units (Scout/Demolisher/Interceptor)

Nothing else is hardcoded from the unit table. Numeric thresholds in
the strategy (12 / 15 / 17 / 25-vs-5 weights) are intentional design
choices, not config-mirror values.

```bash
grep -n "game-configs.json" algos/athena_baseline_lostkids/algo_strategy.py
```
No matches. The algo has no path to the starter-kit config.

**Verdict**: PASS. The port is fully runtime-config-driven for unit
stats; only strategic thresholds are constants, which is correct.

---

## Summary

| Citadel delta | Action taken | Rationale |
|---|---|---|
| Wall upgrade now costs 2 SP | None | Cost auto-read via `type_cost`. Strategic sub-optimality is out of scope. |
| Support shield formula `1 + 0.7Y` | None | Engine uses live config; Lostkids' Y=5/6 placements are suboptimal-for-Citadel but kept verbatim for faithful baseline. |
| Turret upgrade dmg=20 / HP=100 | None | Lostkids already builds upgrade-heavy; threat-score weight (5×) slightly under-weights upgraded turrets but stays inside calibration noise. |
| MP decay 0.25 / round | None | No delta vs base game. |

Net result: zero patches. The port relies entirely on the live
`config["unitInformation"]` for unit stats and the only Lostkids
constants are intentional strategic thresholds. The audit notes flag
two future-tuning candidates (Support Y placement, threat-score
upgrade-weight) that the next Athena variant can address, but neither
is a port-correctness bug.

---

## How to re-verify

1. Live config values: see
   `algos/athena_v3_planner/data/citadel_config_snapshot.json`
   produced by the `/inspect-config` skill.
2. Spot-check at runtime: `gamelib.debug_write` SP/HP/range from
   `config["unitInformation"][i]["upgrade"]` inside
   `AlgoStrategy.on_game_start`. Output goes to `replays/*.replay`
   debug stream.
3. Build-order semantics: `defense-order.json` is interpreted by
   `build_default_defences`, which calls `attempt_spawn` /
   `attempt_upgrade`. Both query `type_cost` -> live config.
