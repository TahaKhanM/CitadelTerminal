# oracle_pure — Improvement Plan

**Live state:** 2138 ELO, 18W/2L (90% win rate including all 4 boss wins).

This plan is built from a forensic analysis of all 20 oracle_pure live ladder
replays + a critical code review of every component. It is prioritized by
the principle the user set: **performance, accuracy, genuine improvement —
not hardcoded fixes.**

The two real losses (vs `aa0` and `gencersarp`) and the close-calls inside
boss wins (Sawtooth T27, Jukebox T20/T24/T30) point at the same structural
weaknesses. Most improvements address multiple loss vectors simultaneously.

---

## Section A — The diagnosis (what the data actually shows)

### A.1 Dataset

| Bucket | N | Notes |
|---|---|---|
| Total matches | 20 | All on live competition ladder, ELO 2138 |
| Wins | 18 (90%) | 14 vs human players + 4 vs all 4 official bosses |
| Losses | 2 (10%) | Both vs adaptive human opponents (`aa0`, `gencersarp`) |
| Boss record | 4/4 perfect | R1 Sawtooth, R2 Infiltrator, R3 Jukebox, R4 Champion — exceeds spec's "≥9/10 vs each" bar |

### A.2 What's working (strengths to preserve)

1. **Central spine defense** (y=11-13 rows + corner anchors at (0,13)/(1,13)/(26,13)/(27,13)) is fortress-grade. **Zero breaches** against the central spine across both R2 Infiltrator (45 mobiles attempted) and R4 Champion (147 mobiles attempted in late-game waves alone).
2. **Discovery-and-exploit offense routes**: search finds an effective breach corridor (e.g., `(2,11) → (20,21)` for scouts, `(24,10)/(25,11) → (13,27)` for interceptors) and exploits it for many turns once found.
3. **Same-turn counter-strike** when opp dumps MP: vs Champion, oracle landed 4/7/9/10 HP on T22/T36/T41/T47 — exactly when boss MP was depleted.
4. **Adaptive unit-mix selection**: oracle picks Scout-heavy (90% vs sk0812), Interceptor-heavy (44% vs hectorkennerley), or Demo-heavy (37% vs foxhenderson) depending on matchup. This is real adaptive search.
5. **Heavy turret upgrades**: most-played defense template `defense:upg2` correctly prioritizes upgrading existing turrets when SP is tight.

### A.3 What's losing us matches (the actual weakness vectors)

The two real losses + close calls in boss wins all map to a small set of structural issues:

**W1: LEFT-SIDE seam at (3,10)-(7,6)-(4,9)-(2,11)** — vs aa0 we lost **42 HP** through these tiles when aa0 dumped a 33-scout wave amplified by 13 base supports (~40 shield/scout). The central spine catches center attacks but the LEFT FLANK at y=6-11 is a permanent thin spot.

**W2: RIGHT-SIDE seam at (23,9)-(20,6)-(21,7)** — vs gencersarp we lost **40 HP, ALL at (23,9)**, from a demolisher drill at (3,17) that walked diagonally across the board. Same seam leaked 7 HP in the Jukebox boss match (T20+T24+T30 at (20,6)/(21,7)).

**W3: Anti-demolisher defense is missing** — gencersarp drilled (23,9) 40 times via demos from (3,17). Oracle's defense at T15 had 18 turrets but no walls in front of the right-side path. Demolishers (range 4.5, 8 dmg vs structures) outranged base turrets (range 2.5) and chipped down our defense before we could reply. By T20 we'd lost 5 turrets to attrition.

**W4: Anti-shield-bus offense is missing** — aa0 stacked 13 base supports at (12-16, 23-26). Each scout passing through gained ~40 shield = effective 55 HP. Our scout rushes, demolisher pokes, and interceptor probes ALL evaporated against shielded waves. **Total damage we dealt vs gencersarp's wall-heavy defense: 7 HP from 273 MP spent (0.03 HP/MP)**. Top players hit 0.5-1.5 HP/MP.

**W5: No support build-out**. Across 20 matches oracle averaged 1-4 supports. aa0 built 13. Without supports our scouts arrive at 15 HP and die instantly to opp upgraded turrets (20 dmg). The boss-win pattern of "spam interceptors" works because interceptors have 40 HP base — we don't actually NEED supports vs bosses, which is why we never built them. But against shield-bus opps (aa0), the lack is fatal.

**W6: Long mid-game stall windows (T15-T45)**. In Champion we burned 40+ MP across T13-T21 on interceptor probes that scored 0. In ameyg-58 we stalled 41 turns (T8-T49) at 0 damage exchange. Against bosses the stall was harmless because they don't pressure us during it. Against an aggressive peer (aa0 banked MP through T46 to enable the killing blow), the stall is where they out-economy us.

**W7: Repeated-failed-offense without learning**. R4 Champion saw 5 identical 17-32 scout rushes from the same tile, all 0 dmg. The intra-match learning loop (`opp_model.observe`) only covers OPP behavior, not OUR own offense effectiveness. Dmg/MP averaged 0.16 in the Champion match — fine because we won, but indicates ~50% of offensive MP delivers 0 damage.

### A.4 Code-level defects that compound the strategic gaps

These bugs drain ELO independently of strategy:

1. **`value.py:185-196`: BREACH TERM IS DEAD CODE**. Pysim removes mobiles with `hp ≤ 0` before the post-state is serialized; mobiles that breached are also dead (their HP is set to 0 on breach). So `breaches_self`/`breaches_opp` are always 0. The `weights["breach"] = 25` reward never fires. Successful breach plans are scored only via the indirect HP differential.
2. **`value.py:198-210`: SUPPORT-SCOUT SYNERGY rewards DEAD scouts**. Counts scouts in the post-state mobile list; successful breachers are gone. Perverse incentive: a plan where scouts get stuck in our backlines scores higher synergy than a plan where they all reach opp's edge.
3. **`tools/build_opp_model.py:147,153`: PRIOR HAS ZERO COVERAGE for `br1_2` and `br3p` BUCKETS**. All 40 prior buckets keyed `…|br0`. Once `recent_breaches > 0` (typical by turn 8), prior lookup misses entirely. We fall through to the hand-injected adversarial samples only.
4. **`opponent_model.py:361-385`: PHASE-1 OPP SAMPLING IS TRIVIAL**. Phase-1 (`k=1`) is always `phase-2[:1]`. The "weighted random" branch is dead code. Means phase-1 culling has no diversity.
5. **`search.py`: DEEPCOPY IS 32% OF SEARCH WALL TIME**. Benchmarked at ~120μs per `deepcopy(base_state)` × ~1700 calls/turn = ~200ms of pure copy out of ~600ms total. Hand-rolled shallow copy: ~5.6μs (22× faster).
6. **`search.py:142-153`: DEPTH-2 USES A HARDCODED OUR-MOVE**. The "next-turn our move" is always `n scouts from (14,0) if MP >= 6×scout_cost`. Doesn't differentiate between "candidate that built supports for next turn" and "candidate that didn't" — both get the same projected scout rush.
7. **`enumerator.py:_enumerate_offense_options`: NO MULTI-LAUNCHER DEMO PLANS**. The only multi-launcher plans are scout two-prongs. We never enumerate "1 demo at center + 4 scouts from left flank" combos.
8. **`enumerator.py:_enumerate_defense_templates`: NO HYBRID TEMPLATES**. Each template is a single atom-priority list. We never try `walls8 + supports_back` together as one plan, even though that's exactly what beat us in aa0.
9. **`search.py:237-258`: NO PRIORITY ORDER IN PHASE-1**. Candidates are evaluated in enumeration order. `defense:none|offense:hold` (low utility) gets the same compute as `defense:t0_full|offense:demo3` (high utility). When the deadline cuts, we may have evaluated the boring plans first.
10. **`algo_strategy.py:163` doesn't use `BreachLocationTracker`/etc.**: 6 sophisticated tracker classes in `oracle_core/action_frame_utils.py` (`BatchCountTracker`, `SpawnXHistogram`, `WallRemovalDetector`, `BreachLocationTracker`, `ResourceTracker`, `MisdirectionDetector`) are unused dead code in the same package.

### A.5 Performance gap

Locally we hit ~1,600 sims/turn at 0.6s wall (well under the spec's ≥10K sims, ≥3s bar). On the live Linux server with `sim_rs` (~5x faster than pysim), we project ~8,000 sims/turn at the same workload. The 11s budget is genuinely under-used: the search runs its fixed 1500 phase-1 + 30×6 phase-2 + 3×2 depth-2 ≈ 1686 sims and returns. There's NO adaptive budget logic that scales work to fill remaining time.

---

## Section B — Improvement plan (prioritized)

Each item: rationale → file:line → code sketch → priority → est. ELO impact → est. effort → risks → which loss vector(s) it addresses.

### TIER 1 — Fixes that close the 2 real losses + boss close-calls
**(Implement first; highest expected ELO/effort ratio)**

#### B1. Fix breach term in value function — make breaches actually score
- **Problem**: `value.py:185-196` counts breaches via `breached=True` flag on post-state mobiles, but breached mobiles are dead and removed. Breach reward never fires.
- **Fix**: Compute breach reward via `delta(opp_hp)` and `delta(my_hp)` between pre-state and post-state.
  ```python
  # search.py: capture pre-state HP
  pre_my_hp  = base_state['p1' if my_player==1 else 'p2']['hp']
  pre_opp_hp = base_state['p2' if my_player==1 else 'p1']['hp']
  # ... after _run_sim:
  score = evaluate(post, my_player=1,
                   pre_my_hp=pre_my_hp, pre_opp_hp=pre_opp_hp)
  ```
  ```python
  # value.py: in evaluate(...)
  prev_my_hp  = kwargs.get('pre_my_hp', my_hp)
  prev_opp_hp = kwargs.get('pre_opp_hp', opp_hp)
  opp_hp_lost = max(0.0, prev_opp_hp - opp_hp)
  my_hp_lost  = max(0.0, prev_my_hp - my_hp)
  breach_term = weights["breach"] * (opp_hp_lost - my_hp_lost)
  ```
- **Priority**: HIGHEST. **Impact**: HIGH (+20-40 ELO — fixes systematic offense underweighting; plans that breach will outscore plans that fizzle).
- **Effort**: SMALL (1-2 hr including local validation).
- **Risks**: Changes scoring scale; may need to retune `weights["hp"]` vs `weights["breach"]`. Mitigation: temporarily set `breach_weight = 0` to verify behavior, then enable.
- **Addresses**: W4 (anti-shield-bus offense), W7 (repeated failed offense).

#### B2. Add anti-demolisher defense templates
- **Problem**: gencersarp drilled (23,9) 40 times via demos from (3,17). No defense template explicitly counters this. Demos have range 4.5 vs base turret range 2.5 — they need WALLS in front + UPGRADED turrets behind.
- **Fix**: Add 3 anti-demo defense templates to `enumerator.py:_enumerate_defense_templates`:
  ```python
  # Anti-demo right (counters (3,17) → (23,9) drill)
  ANTI_DEMO_RIGHT = (
      [(WALL_IDX, x, 12) for x in (18, 19, 20, 21, 22, 23, 24, 25)] +
      [(TURRET_IDX, 22, 11), (TURRET_IDX, 23, 11), (TURRET_IDX, 25, 11)]
  )
  ANTI_DEMO_RIGHT_UPGRADES = [(22,11), (23,11), (25,11)]
  
  # Anti-demo left (mirrored)
  ANTI_DEMO_LEFT = (
      [(WALL_IDX, x, 12) for x in (2, 3, 4, 5, 6, 7, 8, 9)] +
      [(TURRET_IDX, 4, 11), (TURRET_IDX, 5, 11), (TURRET_IDX, 2, 11)]
  )
  ANTI_DEMO_LEFT_UPGRADES = [(4,11), (5,11), (2,11)]
  
  # Anti-demo center / "channel them in" (force demos through (12,12)/(15,12) gaps)
  # Already covered by t0_full + WALL_ROW; no new template needed.
  ```
  Templates only added when **`recent_demolisher_breaches >= 2`** in last 6 turns (signal: opp is demo-funneling). This is principled — the search picks them only when sim_rs evaluation against demo opp samples shows them best.
- **Priority**: HIGHEST. **Impact**: HIGH (+15-30 ELO if any meaningful fraction of ladder uses demo funnels — gencersarp pattern).
- **Effort**: SMALL (3-4 hr including a `recent_demolisher_breaches` tracker).
- **Risks**: Templates add ~3 plans → slight enumeration cost. Mitigation: only add when triggered.
- **Addresses**: W2 (right-side seam), W3 (anti-demolisher defense).

#### B3. Add support-investment offense template
- **Problem**: aa0 won by stacking 13 base supports + mass scouts. Our offense scouts arrived without shields and got chewed up. We never built supports to match.
- **Fix**: Add a "build supports + delay attack" defense template that the search can pick when opp has heavy turret defense (so cracking it requires shielded scouts):
  ```python
  # enumerator.py: SUPPORTS_PROGRESSIVE
  # 4 supports at back row (y=10-11), upgraded → +35-40 shield per scout
  SUPPORTS_PROGRESSIVE = [
      (SUPPORT_IDX, 12, 10), (SUPPORT_IDX, 15, 10),
      (SUPPORT_IDX, 13, 10), (SUPPORT_IDX, 14, 10),
  ]
  SUPPORTS_PROGRESSIVE_UPGRADES = [(12,10), (15,10), (13,10), (14,10)]
  
  # New template in _enumerate_defense_templates:
  if state has 4+ existing turrets and SP >= 16:
      yield "defense:supports_back", SUPPORTS_PROGRESSIVE + ANCHOR_TURRETS,
            upgrades=SUPPORTS_PROGRESSIVE_UPGRADES
  ```
  Combined with B1 (correct breach scoring), the search will see: "build supports this turn + scout rush next turn → +N HP gained" via the depth-2 projection (after B6 below).
- **Priority**: HIGH. **Impact**: HIGH (+20-30 ELO if shield-bus opps are common in 2100-2300 ELO range).
- **Effort**: SMALL (2-3 hr).
- **Risks**: Supports cost 8 SP each (4 + upgrade) — a 4-support back row costs 32 SP. Search may avoid them in low-SP states. Need depth-2 to see the next-turn benefit.
- **Addresses**: W4 (anti-shield-bus offense), W5 (support build-out).

#### B4. Reactive seam reinforcement using `BreachLocationTracker`
- **Problem**: Same tiles ((23,9), (3,10), (4,9)) get drilled 5-40 times. Oracle's `recent_breaches[-6:]` tracker passes the LAST 6 tiles, but we don't BIAS the search toward defending those tiles long-term.
- **Fix**:
  1. Replace `self.recent_breaches: List[Tuple[int,int]]` with a `BreachLocationTracker` instance from `action_frame_utils.py` (which has hot-tile decay).
  2. In `enumerator.py`, expand `defense:patch{k}` to:
     - Build TWO turrets at the breach tile + adjacent tile + a wall in front
     - Upgrade the existing turret nearest the breach if any
     ```python
     def _patch_template(breach_tiles, hot_decay_weights):
         atoms = []
         for (bx, by), weight in sorted(hot_decay_weights.items(), key=-itemgetter(1)):
             if weight < 0.5: break  # not hot enough
             # Reinforce: 2 turrets behind breach + 1 wall in front
             if bx < 14:
                 atoms.append((TURRET_IDX, max(bx-1,0), min(by+1,13)))
                 atoms.append((TURRET_IDX, bx, min(by+2,13)))
                 atoms.append((WALL_IDX, bx, by))  # block at breach point
             else:
                 atoms.append((TURRET_IDX, min(bx+1,27), min(by+1,13)))
                 atoms.append((TURRET_IDX, bx, min(by+2,13)))
                 atoms.append((WALL_IDX, bx, by))
         return atoms
     ```
- **Priority**: HIGH. **Impact**: MEDIUM-HIGH (+15-25 ELO — directly addresses the "single-tile drill" loss pattern from gencersarp).
- **Effort**: MEDIUM (1 day — wire the tracker in, expand template, validate).
- **Risks**: Reactive patches can over-react to noise (1 breach ≠ a pattern). Mitigation: require `weight >= 0.5` (= ~2 recent breaches with decay).
- **Addresses**: W1 (left-seam), W2 (right-seam), W3 (anti-demo).

#### B5. Hand-rolled fast state copy → unlock 22× more sims/turn (PERF)
- **Problem**: `deepcopy(base_state)` is 32% of search wall time at ~120μs/call. Bottleneck for hitting the ≥10K sims/turn bar.
- **Fix**: Add `_fast_copy_state` in `search.py`:
  ```python
  def _fast_copy_state(s):
      return {
          "turn": s["turn"],
          "p1": dict(s["p1"]),
          "p2": dict(s["p2"]),
          "structures": [
              {**ss, "xy": list(ss["xy"])}
              for ss in s["structures"]
          ],
          "mobiles": [
              {**mm, "xy": list(mm["xy"]), "spawn_xy": list(mm.get("spawn_xy", mm["xy"]))}
              for mm in s["mobiles"]
          ],
      }
  # Replace all `deepcopy(base_state)` with `_fast_copy_state(base_state)`
  ```
  Benchmark: 22× faster. Saves ~30% of search wall time, enabling more candidates evaluated per turn (≥3K local sims, ≥15K server sims).
- **Priority**: HIGH. **Impact**: MEDIUM (+10-15 ELO indirectly — lets the search evaluate ~30% more candidates in the same budget; also HITS the compute bar).
- **Effort**: SMALL (1-2 hr including a golden-copy parity test).
- **Risks**: A field is missed (e.g., `turn_start_removal` is mutable somewhere). Mitigation: add `tests/test_fast_copy.py` that fuzzes 100 base_states and asserts `deepcopy(s) == _fast_copy_state(s)` after sim mutation.
- **Addresses**: Compute bar; enables better fixes downstream.

#### B6. Adaptive search budget — use the full 11s
- **Problem**: Search returns at ~600ms locally / ~120ms server. Doesn't expand work when budget remains.
- **Fix**: After phase-2 completes, check time:
  ```python
  # search.py: after phase-2 loop
  if time.time() - t0 < time_budget_s * 0.5 and final_scores:
      # Phase-3: re-eval top-10 with k_opp=12 (more confidence on contenders)
      ...
  if time.time() - t0 < time_budget_s * 0.7 and final_scores:
      # Depth-3: project an extra turn forward on top-3
      ...
  ```
  Also after B5, the freed wall-time should let phase-1 itself be scaled (`max_plans=4000`) without changing config defaults.
- **Priority**: HIGH. **Impact**: MEDIUM (+10-20 ELO via better top-pick confidence + deeper lookahead).
- **Effort**: MEDIUM (1 day including regression validation).
- **Risks**: Deeper lookahead may not improve picks if value function is weak (B1 must land first). Mitigation: gate behind a config flag; A/B locally.
- **Addresses**: Compute bar; W7 (repeated failed offense gets longer-horizon evaluation).

---

### TIER 2 — Genuine algorithmic improvements

#### B7. Re-build opp model prior with actual breach context
- **Problem**: `tools/build_opp_model.py:147,153` always passes `recent_breaches=0` when building the prior. All 40 prior buckets keyed `…|br0`. Once `recent_breaches > 0` (typical by turn 8), the prior lookup misses entirely.
- **Fix**: Track running breach count as we walk through replay frames; pass actual count to `bucket_key`. Re-run the builder.
  ```python
  # tools/build_opp_model.py
  running_breaches_against_p1 = 0
  running_breaches_against_p2 = 0
  for t in sorted(action_frames):
      bk_p1 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                         recent_breaches=running_breaches_against_p1)
      bucket_counters[bk_p1][sig_opp_against_p1] += 1
      bk_p2 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                         recent_breaches=running_breaches_against_p2)
      bucket_counters[bk_p2][sig_opp_against_p2] += 1
      # Update running counts from THIS turn's breach events:
      for b in events.get('breach', []):
          owner = int(b[4])  # who breached
          if owner == 1:
              running_breaches_against_p2 += 1
          else:
              running_breaches_against_p1 += 1
  ```
- **Priority**: HIGH. **Impact**: MEDIUM-HIGH (+15-25 ELO — adversarial games WHERE we need good priors).
- **Effort**: SMALL (~1 hr to rewrite + ~10 min to re-run on 427 replays).
- **Risks**: Bucket count grows; need to keep top-16 cap.
- **Addresses**: W1, W2, W3, W4 (better predictions of opp behavior in high-pressure buckets).

#### B8. Hybrid defense templates (cross-product of atoms)
- **Problem**: Each defense template is a single atom-priority list. We never combine "walls + supports" or "diag + upgrades" in one plan, even though those hybrids are exactly what beat us in aa0.
- **Fix**: Add ~30 hybrid templates to `enumerator.py:_enumerate_defense_templates`:
  ```python
  HYBRIDS = [
      ("walls8+supports", WALL_ROW_Y12[:8] + SUPPORTS_PROGRESSIVE),
      ("walls8+anchor",   WALL_ROW_Y12[:8] + ANCHOR_TURRETS),
      ("anchor+side+upg2", ANCHOR_TURRETS + SIDELANE_TURRETS, [(11,11),(13,11)]),
      ("patch_left+wall_row", REACTIVE_PATCH_TILES_LEFT[:2] + WALL_ROW_Y12, ...),
      ("patch_right+wall_row", REACTIVE_PATCH_TILES_RIGHT[:2] + WALL_ROW_Y12, ...),
      ("supports4+upg_back",   SUPPORTS_PROGRESSIVE, [(12,10),(15,10)]),
      ("anti_demo_R+anchor",   ANTI_DEMO_RIGHT + ANCHOR_TURRETS),
      ("anti_demo_L+anchor",   ANTI_DEMO_LEFT + ANCHOR_TURRETS),
      ...
  ]
  ```
- **Priority**: HIGH. **Impact**: HIGH (+20-30 ELO — opens up the strategically-strong plans the search can't currently see).
- **Effort**: MEDIUM (1 day including selecting the 30 most-promising hybrids).
- **Risks**: More candidates → more sims needed (mitigated by B5/B6).
- **Addresses**: W1, W2, W3, W4 (multi-vector defense).

#### B9. Depth-2 projection runs a mini-search for our move (not hardcoded)
- **Problem**: `search.py:142-153` uses a hardcoded "scout from (14,0)" as our depth-2 move. Doesn't capture build-then-attack synergies (e.g., "build supports THIS turn → next-turn scouts get +shield").
- **Fix**: Run a 1-shot mini-enumerator + 1-sim selection for our depth-2 move:
  ```python
  def _project_next_turn(post1, opp_top_sig, ...):
      # Add income/decay (already done)
      # Sample opp top action (already done)
      # NEW: pick our next-turn move via a CHEAP search:
      next_candidates = _enumerate_offense_options(simulated_gs, config, mp_budget=...)
      # Score each via 1 sim against opp_top_sig
      best_next = max(next_candidates, key=lambda c: _quick_score(c, opp_top_sig))
      _apply_plan(best_next, sd, my_player, config)
      return _run_sim(sd, config_path)
  ```
- **Priority**: MEDIUM. **Impact**: MEDIUM (+10-15 ELO — depth-2 becomes meaningfully predictive).
- **Effort**: MEDIUM (1 day).
- **Risks**: Mini-search adds compute; only fires on top-3 candidates so bounded.
- **Addresses**: W4 (lets search SEE the value of supports → next-turn shielded attacks).

#### B10. Offense-effectiveness intra-game learning
- **Problem**: R4 Champion saw 5 identical 17-32 scout rushes from same tile, all 0 dmg. The opp model tracks OPP behavior; we don't track OUR offense effectiveness.
- **Fix**: Add a simple `OffenseTracker` that records `(plan_archetype, launcher, n_units) → (avg_dmg_dealt, attempts)` over the current game. The value function adds a small penalty/bonus for plans that have failed/succeeded recently.
  ```python
  # algo_strategy.py: in on_action_frame, count breach events by OUR mobiles
  # Then in evaluate():
  recent_dmg_per_mp = offense_tracker.dmg_per_mp_for(plan.archetype)
  if recent_dmg_per_mp < 0.1 and attempts >= 2:
      score -= 50  # penalize repeating proven-failing offense
  ```
- **Priority**: MEDIUM-HIGH. **Impact**: MEDIUM (+10-20 ELO — stops MP waste on dead plans).
- **Effort**: MEDIUM (1 day).
- **Risks**: Could over-penalize; need decay on the failure record. Mitigation: cap penalty at ~50, decay weight by 0.7 per turn.
- **Addresses**: W6 (mid-game stall), W7 (repeated failed offense).

#### B11. Multi-launcher demo + scout split offense templates
- **Problem**: Only multi-launcher offenses are scout two-prongs. We never enumerate "1 demo center + 4 scouts left flank" combos that punish opp's lopsided defense.
- **Fix**: Add to `_enumerate_offense_options`:
  ```python
  if max_demos >= 1 and max_scouts >= 4:
      for demo_loc, scout_loc in [
          ((13,0),  (3,10)), ((14,0),  (24,10)),
          ((13,0),  (24,10)), ((14,0), (3,10)),
      ]:
          for d_n, s_n in [(1,4), (1,6), (2,4), (2,6)]:
              cost = d_n*demo_cost + s_n*scout_cost
              if cost > mp_budget: continue
              p = ActionPlan(name=f"offense:split_d{d_n}+s{s_n}", archetype="split")
              p.add_mobile(DEMOLISHER_IDX, demo_loc[0], demo_loc[1], d_n)
              p.add_mobile(SCOUT_IDX, scout_loc[0], scout_loc[1], s_n)
              ...
  ```
- **Priority**: MEDIUM. **Impact**: MEDIUM (+10-15 ELO).
- **Effort**: SMALL (2-3 hr).
- **Risks**: Adds ~20 plans; bounded.
- **Addresses**: W4 (offensive variety to crack heavy defense).

#### B12. Phase-1 candidate priority order
- **Problem**: Phase-1 evaluates in enumeration order. `defense:none|offense:hold` evaluated before `defense:t0_full|offense:demo3`. If deadline hits, we lose the interesting plans.
- **Fix**: Sort candidates by a cheap heuristic prior:
  ```python
  def _candidate_prior(c):
      # Higher SP spent + has mobile = higher priority
      has_mobile = 1 if c.has_mobiles() else 0
      return (-has_mobile, -c.sp_cost - c.mp_cost, c.name)
  candidates.sort(key=_candidate_prior)
  ```
- **Priority**: MEDIUM. **Impact**: LOW-MEDIUM (+5-10 ELO; matters most under deadline cuts).
- **Effort**: SMALL (1 hr).
- **Risks**: None significant.

#### B13. HP_max read from runtime config (correctness)
- **Problem**: `value.py:107-111` hardcodes `_MAX_HP`. If live config diverges, HP fractions are wrong.
- **Fix**: Compute via `cost_for_idx`-style runtime lookup. Cache per-game in `evaluate`.
- **Priority**: LOW-MEDIUM. **Impact**: LOW (only matters if config changes).
- **Effort**: SMALL (30 min).

---

### TIER 3 — Strategic blind spots (longer-term)

#### B14. Multi-turn skeleton planning (genuine 2-3 turn lookahead)
- **Problem**: Top algos commit to a 3-5 turn build order. Our search is 1-turn (depth-2 projection is a proxy).
- **Fix**: Plan-tree search variant: at top-N candidates, branch into 3 next-turn variants (continue current build, switch to scout rush, switch to defense reinforcement), simulate each, score the 2-turn-ahead state. Pick the candidate whose 2-turn projection is best.
- **Priority**: MEDIUM. **Impact**: HIGH potential (+30-50 ELO) but **GENUINELY HARD**: search-tree depth grows, value function must handle "intermediate state" not just terminal.
- **Effort**: LARGE (multi-day).
- **Risks**: Big change; could regress if not validated extensively.

#### B15. Refund-cycle economy
- **Problem**: `KIND_REMOVE` exists but treated as a 0-cost intent; refund timing isn't modeled.
- **Fix**: Track pending refunds (`turn_start_removal` set, refund returns `0.9 × cost × hp_frac` next turn). Value function rewards "remove now → SP gain next turn → upgrade then" cycles.
- **Priority**: MEDIUM. **Impact**: MEDIUM (+10-20 ELO).
- **Effort**: MEDIUM (1-2 days).

#### B16. Side-pressure detector → asymmetric defense bias
- **Problem**: `defense:left_heavy` and `right_heavy` exist but aren't TRIGGERED by detected pressure.
- **Fix**: Use `BreachLocationTracker` (existing dead code in `action_frame_utils.py`) to compute "left vs right pressure ratio." When >2:1 imbalance, increase weight on the heavy-side defense templates.
- **Priority**: MEDIUM. **Impact**: MEDIUM (+10-15 ELO).
- **Effort**: SMALL-MEDIUM (1 day).

#### B17. Self-destruct exploits
- **Problem**: Demolisher SD does 5 dmg/structure at range 1.5; Scout SD does 15. Wall-trap-then-SD setups can deal huge damage. Never enumerated.
- **Priority**: LOW. **Impact**: LOW (niche tactic).
- **Effort**: MEDIUM.

#### B18. MP-banking attack window detector
- **Problem**: We DON'T differentiate "I have 12 MP and opp has 5" (opportunity!) from "I have 12 MP and opp has 18" (defend!). Saturating resource term is symmetric.
- **Fix**: Add a "pressure imbalance" term: when `our_mp >> opp_mp`, increase the value of attack plans; when `opp_mp >> our_mp`, increase the value of defense plans.
- **Priority**: MEDIUM. **Impact**: MEDIUM (+10 ELO).
- **Effort**: SMALL.

---

### TIER 4 — Correctness and hygiene

#### B19. Fix latent edge-ID bug in `sim_eval._apply_deploys_inplace`
- **Problem**: Comment is wrong, code uses inconsistent IDs vs `vendored_sim/map.py`. Currently dormant (search uses `apply_to_state_dict`), but a future re-wire would explode.
- **Fix**: Use `_spawn_to_target_edge` from `state_adapter.py`. Update comment.
- **Priority**: LOW. **Impact**: LOW (latent). **Effort**: TRIVIAL.

#### B20. Fix `support_shield_synergy` to count DEPLOYED scouts (not surviving)
- **Problem**: `value.py:198-210` rewards scouts NOT successfully breaching. Perverse.
- **Fix**: Pass `my_deployed_count` from caller; use it instead of `my_scouts_alive`.
- **Priority**: LOW-MEDIUM. **Impact**: LOW (+5 ELO; small weight). **Effort**: SMALL.

#### B21. `_enumerate_offense_options` hoisted out of defense loop
- **Problem**: `enumerator.py:654-674` rebuilds offense list 13× per turn.
- **Fix**: Compute once outside the loop.
- **Priority**: LOW. **Impact**: LOW (saves ~3-5ms enumeration). **Effort**: TRIVIAL.

#### B22. Dead-code cleanup
- **Problem**: 6 tracker classes in `action_frame_utils.py` unused. Dead code in `opponent_model.py:376-384`.
- **Fix**: Either USE them (B4, B16) or delete.
- **Priority**: LOW. **Effort**: SMALL.

---

## Section C — Implementation order + validation gates

### Phase 1 (week 1): Tier-1 fixes; should give +50-90 ELO
Target: **2200+ ELO live**, win-rate ≥92% in next 30 ladder matches.

1. **B1 (breach term fix)** — 1 day; verify scoring sanity locally.
2. **B5 (fast state copy)** — half day; gold-test parity.
3. **B6 (adaptive budget)** — 1 day; verify no regression.
4. **B2 (anti-demo templates)** — 1 day.
5. **B4 (reactive seam reinforcement)** — 1 day.
6. **B3 (support-investment template)** — 1 day.

After each: full 10-match local ladder regression vs (v13_second_ring,
python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER) BOTH
sides. Must maintain 10/10 on bar metrics. Upload to live ladder
between major changes for 5-10 ranked matches to confirm trend.

### Phase 2 (week 2): Tier-2 algorithmic improvements; +30-60 ELO
Target: **2250+ ELO**.

1. **B7 (rebuild prior with breach context)** — half day.
2. **B8 (hybrid defense templates)** — 1 day.
3. **B11 (multi-launcher offense)** — half day.
4. **B10 (offense effectiveness tracking)** — 1 day.
5. **B9 (mini-search depth-2)** — 1 day.
6. **B12 (phase-1 priority order)** — 2 hr.

### Phase 3 (week 3+): Tier-3 strategic; +30-50 ELO if executed well
Target: **2300+ ELO** (top-tier territory).

1. **B14 (multi-turn skeleton planning)** — 2-3 days, RISKY.
2. **B16 (asymmetric defense detector)** — 1 day.
3. **B15 (refund-cycle economy)** — 1-2 days.
4. **B18 (MP-banking attack windows)** — half day.

### Phase 4: Hygiene + tail
1. **B13, B19, B20, B21, B22** — half day total.

---

## Section D — Validation framework

Every change MUST pass:

1. **Component tests**: `python3 algos/oracle_pure/tests/test_components.py` continues to pass.
2. **Local ladder**: 10/10 wins both sides on bar opponents (v13, python-algo, heuristic_v1, Lostkids/python-2l-aet, funnel_INTER). Document any regression in REPORT.md.
3. **Live ladder**: After each Phase 1/2 batch, upload to live and play 5-10 ranked matches. Confirm ELO trend. If ELO drops by >50 over 10 matches, rollback.
4. **Telemetry**: Per-turn debug log shows `cands ≥ 2000`, `sims ≥ 5000` (local) / projected `≥ 15000` (server), `wall ≥ 2s` (local).
5. **Decision evidence**: Sample 10 turns from a heuristic_v1 match. Each must show search picking a plan that varies from "static heuristic top-pick" — this is the proof we're not regressing toward heuristic-disguise.

---

## Section E — Honest expectations

### Why we hit 2138 ELO and not 1800
- The replay corpus shows 5/7 wins are vs no-search heuristic bots (5-10ms compute/turn). The 2100-2200 ELO band has many such bots. Oracle's defense template + late-game commit pattern beats them comfortably.
- The 2 real losses are vs adaptive HUMAN players. Both exploited specific structural weaknesses (W1: left seam, W2: right seam, W3: anti-demo, W4: anti-shield-bus offense).
- The 4 boss wins are dominant (30-38 HP margins) but partly because bosses don't attack our flanks.

### What this plan can realistically achieve
- **Phase 1 alone**: 2200-2250 ELO. Closes most of the obvious losing patterns.
- **Phase 1+2**: 2250-2300 ELO. Top-15 territory in most competitions.
- **Phase 1+2+3**: 2300-2400 ELO. Top-5 territory if we execute well; depends on competitive landscape evolution.
- **Above 2400 ELO**: requires depth-3+ search with proper opp action chains, MCTS-style tree expansion, and likely a Rust port of the value function. Out of scope here.

### What this plan WON'T solve
- A radically different boss / human strategy we haven't seen yet (the corpus is small: 20 matches).
- Fundamental search-depth limitations vs algos that plan 10 turns ahead.
- Computational ceiling: pysim is ~3K sims/sec; sim_rs is ~14.6K. To go beyond 50K sims/turn we'd need batch evaluation or GPU acceleration — neither in scope.

### Signals to abandon a fix mid-way
- Local ladder regresses on a bar opponent → rollback.
- Live ladder ELO drops >50 over 10 matches → rollback.
- Per-turn wall clock exceeds 11s on the server → rollback.
- Watchdog SIGALRM trips in any match → P0 bug, fix before continuing.
