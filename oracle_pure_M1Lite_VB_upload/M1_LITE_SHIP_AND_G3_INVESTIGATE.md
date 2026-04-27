# M1-Lite SHIP + G3 INVESTIGATION — go-ahead and next steps

**Decision (user, 2026-04-26)**: Ship G7 directly as M1-Lite; investigate why
G3 caused regression in parallel.

This document has two halves:
1. **PROMPT — Ship M1-Lite (G7-only)** — exact steps to ship the validated
   G7 fix. This is GREEN-LIT: data clearly supports it (snorkeldink 2/2
   breakthrough + −37% wall clock + clean Tier A 7/7).
2. **PROMPT — G3 investigation** — diagnostic + proposed "G3-Additive"
   reformulation that should preserve oracle's existing wins while still
   adding the breach-context signal.

Both prompts are self-contained. Run them in EITHER order — they don't
depend on each other.

---

## PART 1 — Ship M1-Lite (G7-only)

You are shipping **M1-Lite**: just G7 (fast state copy). G3 was rejected
in the previous M1 attempt (broke 4/10 Tier A matches). G7-only was
validated to deliver:
- 22× speedup per `_fast_copy_state` call vs `deepcopy`
- 37% wall-clock reduction in live matches
- **2/2 conversion of snorkeldink-v3-1** (oracle_pure was 0/2 — Tier B
  breakthrough)

### Pre-flight check (verify state)

The repo should already be in the correct state from the M1 work:
- `oracle_core/search.py` has `_fast_copy_state` defined and 3 `deepcopy`
  calls replaced
- `data/opp_model_priors.json` is the OLD content (G3's NEW prior is
  preserved as `data/opp_model_priors.M1_breach_context.json`)
- `tests/test_fast_copy.py` exists and passes
- `tests/test_prior_parity.py` exists (uses the M1_breach_context backup)
- `tests/test_components.py` passes

Verify:
```bash
cd /Users/tahakhan/Documents/Work/Projects/CitadelTerminal
python3 algos/oracle_pure/tests/test_components.py    # all 8 pass
python3 algos/oracle_pure/tests/test_fast_copy.py     # all 3 pass
ls -la algos/oracle_pure/data/opp_model_priors*.json  # should see all 3
grep -c "deepcopy(base_state)" algos/oracle_pure/oracle_core/search.py  # should be 0
grep -c "_fast_copy_state(base_state)" algos/oracle_pure/oracle_core/search.py  # should be 3
```

If any of these is wrong, stop and report. Don't ship until aligned.

### Ship steps

1. **Run the full Tier A + Tier B suite ONE MORE TIME** to lock in the
   shipping state:
   ```
   For each opp in {v13_second_ring, python-algo (starter), heuristic_v1,
                    Lostkids/python-2l-aet, funnel_INTER, snorkeldink-v3-1}:
     Run oracle_pure as P1 vs opp; record Winner
     Run opp as P1 vs oracle_pure; record Winner
   ```
   Use ABSOLUTE paths in run_match.py invocations. Verify NO `FailedToLoad`
   in any log.
   
   **Expected**: Tier A 10/10 (5 opps × 2 sides); Tier B 2/2.
   
   If anything is different from this, STOP and investigate before shipping.

2. **Create the upload folder `oracle_pure_M1Lite_upload/`**:
   ```bash
   ROOT=/Users/tahakhan/Documents/Work/Projects/CitadelTerminal
   rm -rf $ROOT/oracle_pure_M1Lite_upload
   mkdir -p $ROOT/oracle_pure_M1Lite_upload
   cp -R $ROOT/algos/oracle_pure/oracle_core $ROOT/oracle_pure_M1Lite_upload/
   cp -R $ROOT/algos/oracle_pure/bundled_sim_rs $ROOT/oracle_pure_M1Lite_upload/
   cp -R $ROOT/algos/oracle_pure/vendored_sim $ROOT/oracle_pure_M1Lite_upload/
   cp -R $ROOT/algos/oracle_pure/gamelib $ROOT/oracle_pure_M1Lite_upload/
   cp -R $ROOT/algos/oracle_pure/data $ROOT/oracle_pure_M1Lite_upload/
   cp $ROOT/algos/oracle_pure/algo_strategy.py $ROOT/oracle_pure_M1Lite_upload/
   cp $ROOT/algos/oracle_pure/algo.json $ROOT/oracle_pure_M1Lite_upload/
   cp $ROOT/algos/oracle_pure/run.sh $ROOT/oracle_pure_M1Lite_upload/
   cp $ROOT/algos/oracle_pure/sim_bridge.py $ROOT/oracle_pure_M1Lite_upload/ 2>/dev/null
   chmod +x $ROOT/oracle_pure_M1Lite_upload/run.sh
   ```
   Then EDIT `oracle_pure_M1Lite_upload/data/opp_model_priors.json` to
   ensure it's the OLD content (NOT the M1_breach_context content). Or
   simpler: just copy the OLD-content file:
   ```bash
   cp $ROOT/algos/oracle_pure/data/opp_model_priors.OLD.json \
      $ROOT/oracle_pure_M1Lite_upload/data/opp_model_priors.json
   ```
   Sanity-check the upload folder doesn't contain the M1_breach_context
   backup file (we don't want both shipped):
   ```bash
   rm -f $ROOT/oracle_pure_M1Lite_upload/data/opp_model_priors.M1_breach_context.json
   rm -f $ROOT/oracle_pure_M1Lite_upload/data/opp_model_priors.OLD.json
   ```

3. **Path-portability test in `/tmp/`**:
   ```bash
   rm -rf /tmp/oracle_pure_M1Lite_test
   cp -R $ROOT/oracle_pure_M1Lite_upload /tmp/oracle_pure_M1Lite_test
   python3 $ROOT/C1GamesStarterKit-master/scripts/run_match.py \
     /tmp/oracle_pure_M1Lite_test \
     $ROOT/algos/v13_second_ring 2>&1 | tail -20
   ```
   Expected: `Winner (p1 perspective): 1` and no `FailedToLoad`.

4. **Commit on a branch**:
   ```bash
   cd $ROOT
   git checkout -b oracle-pure-M1-Lite
   git add algos/oracle_pure/oracle_core/search.py
   git add algos/oracle_pure/tests/test_fast_copy.py
   git add algos/oracle_pure/tests/test_prior_parity.py  # keep as artifact
   git add algos/oracle_pure/data/opp_model_priors.M1_breach_context.json  # preserve for G3 investigation
   git add algos/oracle_pure/MILESTONE_M1_RESULTS.md
   git add algos/oracle_pure/M1_LITE_SHIP_AND_G3_INVESTIGATE.md
   git add oracle_pure_M1Lite_upload/
   git commit -m "$(cat <<'EOF'
   ship M1-Lite: G7 fast_copy_state (G3 deferred to investigation)

   M1 (G7+G3) was rejected per the strict Tier A 10/10 rule — G3's prior
   rebuild thinned br0 buckets and broke 4/10 Tier A matches (heuristic_v1
   both sides, python-2l-aet both sides). G3 also killed G7's snorkeldink
   breakthrough (G7-only achieves snorkeldink 2/2; G7+G3 reverts to 0/2).

   Ships G7 ONLY as M1-Lite — validated:
     - Tier A 10/10 vs (v13, python-algo, heuristic_v1, Lostkids/python-2l-aet,
       funnel_INTER) both sides
     - Tier B 2/2 vs snorkeldink-v3-1 (BREAKTHROUGH — was 0/2)
     - Tier D wall-clock -37% on heuristic_v1 P1 (0.63 -> 0.40s avg)
     - Component tests + new test_fast_copy.py all pass

   G3 build script + rebuilt-prior artifact preserved on disk
   (opp_model_priors.M1_breach_context.json) for the G3 investigation
   prompt in M1_LITE_SHIP_AND_G3_INVESTIGATE.md PART 2.

   Co-Authored-By: Claude Opus 4.7 (1M context) <noreply@anthropic.com>
   EOF
   )"
   ```

5. **Write `MILESTONE_M1Lite_SHIPPED.md`** with:
   - Final Tier A 10/10 results (10 matches, all W)
   - Final Tier B 2/2 results (snorkeldink-v3-1 breakthrough!)
   - Tier D telemetry (sims/turn, wall/turn comparison vs pre-G7)
   - Path-portability test result
   - Live-test instructions for the user
   - The exact upload-folder name (`oracle_pure_M1Lite_upload/`)

6. **Hand off to user for live upload**. The branch `oracle-pure-M1-Lite`
   stays unmerged until the live ladder confirms.

### Live ladder validation (user-side)

User uploads `oracle_pure_M1Lite_upload/` to terminal.c1games.com as a new
algo. Watches for ≥10 ranked matches.

**Expected outcomes**:
- ELO ≥ baseline (oracle_pure_upload = ~2150 currently): SUCCESS, merge
  branch to main.
- snorkeldink-v3-1 conversion observable in matchups: confirms local
  result, ELO bump expected.
- ELO drops >40: REJECT retroactively. The local Tier B breakthrough
  didn't generalize. Investigate before doing M2.

If live confirms, the `oracle_pure_upload/` folder becomes the
M1-Lite-shipped artifact.

---

## PART 2 — G3 investigation (deferred fix)

You are investigating why G3 (rebuild prior with breach context) regressed
4 Tier A matches in M1 testing. The hypothesis from the implementer's M1
results doc (`MILESTONE_M1_RESULTS.md`) is:

> G3's rebuild thins br0 weights ~50% in many buckets — observations that
> were previously misclassified as br0 now correctly land in br1_2/br3p.
> Against opponents that rarely breach, oracle stays in br0 with weaker
> predictions.

This is a **sample-density regression**, not a logic bug. Verify it
empirically and propose a fix that preserves oracle's existing wins.

### Step 1 — Empirical verification of the hypothesis

The two priors are on disk:
- `data/opp_model_priors.OLD.json` — original (all observations as br0)
- `data/opp_model_priors.M1_breach_context.json` — G3 rebuild (correctly
  partitioned)

Run a comparative analysis script:

```python
import json
from collections import defaultdict
old = json.load(open('algos/oracle_pure/data/opp_model_priors.OLD.json'))
new = json.load(open('algos/oracle_pure/data/opp_model_priors.M1_breach_context.json'))

# For each OLD br0 bucket, find the corresponding NEW br0 bucket
# and compute the change in total observation weight.
loss = defaultdict(int)
for bk_old, sigs_old in old.items():
    if not bk_old.endswith('|br0'):
        continue
    weight_old = sum(s['weight'] for s in sigs_old)
    weight_new = sum(s['weight'] for s in new.get(bk_old, []))
    loss[bk_old] = (weight_old, weight_new, weight_old - weight_new)

# Sort by absolute weight loss
ranked = sorted(loss.items(), key=lambda kv: -kv[1][2])
print("Top 10 br0 buckets that LOST the most weight in G3 rebuild:")
for bk, (w_old, w_new, diff) in ranked[:10]:
    pct = diff / max(w_old, 1) * 100
    print(f"  {bk}: {w_old:.0f} -> {w_new:.0f} (lost {diff:.0f}, {pct:.0f}%)")

# Also verify what got ADDED to br1_2 and br3p
added_br1_2 = sum(sum(s['weight'] for s in v) for k, v in new.items() if k.endswith('|br1_2'))
added_br3p = sum(sum(s['weight'] for s in v) for k, v in new.items() if k.endswith('|br3p'))
print(f"\nNEW prior added: br1_2 = {added_br1_2:.0f} weight, br3p = {added_br3p:.0f} weight")
```

This tells you **exactly which br0 buckets lost data and how much**.
Likely top losers: `t10_19|mp4_7|mp4_7|br0`, `t20_29|mp8_12|mp8_12|br0`
(mid-game buckets where breach pressure builds).

### Step 2 — Root-cause confirmation

If Step 1 confirms br0 buckets lost ≥30% of their weight on average, the
hypothesis is verified.

Look at one specific regressed match (heuristic_v1 P1) and check what
bucket key oracle is in at the tilt turn. The bucket key is computed in
`algo_strategy.on_action_frame` → `opp_model.observe(...)` and used in
`opp_model.sample(...)` via `_sigs_for_bucket()`.

Likely finding: in heuristic_v1 matches, oracle never gets breached (or
breaches happen very late), so it stays in `…|br0` for the entire game.
The thinner br0 prior degrades opp-action prediction → search picks
wrong defenses → loses the match.

### Step 3 — The G3-Additive fix

The principled fix: **don't move observations between buckets — duplicate
them**. Each observation is recorded at:
1. `…|br0` (always — preserves OLD behavior exactly)
2. `…|br{actual_breach_band}` (if breach band > 0 — adds NEW signal)

Code change to `tools/build_opp_model.py` (replace the per-turn loop):

```python
running_breaches_against_p1 = 0
running_breaches_against_p2 = 0
for t in sorted(action_frames):
    # ALWAYS record at br0 (preserves OLD behavior — strict superset)
    bk_p1_br0 = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp, recent_breaches=0)
    bucket_counters[bk_p1_br0][sig_opp_against_p1] += 1
    bk_p2_br0 = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp, recent_breaches=0)
    bucket_counters[bk_p2_br0][sig_opp_against_p2] += 1

    # ADDITIONALLY record at the actual breach-context bucket (NEW signal)
    if running_breaches_against_p1 > 0:
        bk_p1_real = bucket_key(t, opp_mp=p2_mp, our_mp=p1_mp,
                                recent_breaches=running_breaches_against_p1)
        if bk_p1_real != bk_p1_br0:  # different from br0
            bucket_counters[bk_p1_real][sig_opp_against_p1] += 1
    if running_breaches_against_p2 > 0:
        bk_p2_real = bucket_key(t, opp_mp=p1_mp, our_mp=p2_mp,
                                recent_breaches=running_breaches_against_p2)
        if bk_p2_real != bk_p2_br0:
            bucket_counters[bk_p2_real][sig_opp_against_p2] += 1

    # Update running counts AFTER recording
    events = action_frames[t]  # ALL frames, per the M1 implementer's fix
    for b in events.get('breach', []):
        ...
```

This is **strictly additive**: br0 is unchanged from OLD; br1_2 and br3p
are bonus signal. No br0 observation is moved out.

### Step 4 — Validate G3-Additive

Re-run the build:
```bash
python3 algos/oracle_pure/tools/build_opp_model.py
# This rebuilds opp_model_priors.json with the additive logic.
# IMPORTANT: back up the new artifact under a new name to avoid
# overwriting the existing OLD baseline:
cp algos/oracle_pure/data/opp_model_priors.json \
   algos/oracle_pure/data/opp_model_priors.G3_ADDITIVE.json
```

Then a NEW prior parity test:
```python
# tests/test_prior_additive.py
old = json.load(open('algos/oracle_pure/data/opp_model_priors.OLD.json'))
add = json.load(open('algos/oracle_pure/data/opp_model_priors.G3_ADDITIVE.json'))

# Assertion 1: every OLD br0 bucket is preserved EXACTLY in additive
for bk in old:
    assert bk in add, f"missing bucket: {bk}"
    sigs_old = sorted(old[bk], key=lambda s: str(s['sig']))
    sigs_add = sorted(add[bk], key=lambda s: str(s['sig']))
    # Weights might differ if signatures appear in MULTIPLE bands
    # (the same observation gets recorded at br0 AND its real band),
    # but the CONTRIBUTION to br0 should be unchanged.
    # Actually: br0 weight should be IDENTICAL because we still record
    # there for every turn.
    weight_old = sum(s['weight'] for s in sigs_old)
    weight_add = sum(s['weight'] for s in sigs_add)
    # Allow tiny float drift but NO observation loss
    assert weight_add >= weight_old - 0.01, \
        f"bucket {bk} lost weight: {weight_old} -> {weight_add}"

# Assertion 2: br1_2 and br3p have MORE buckets than OLD did (which had 0)
new_keys = set(add.keys())
old_keys = set(old.keys())
br1_2_buckets = sum(1 for k in new_keys if k.endswith('|br1_2'))
br3p_buckets = sum(1 for k in new_keys if k.endswith('|br3p'))
assert br1_2_buckets > 0, "br1_2 buckets not added"
assert br3p_buckets > 0, "br3p buckets not added"

print(f"PASS: br0 preserved + {br1_2_buckets} br1_2 + {br3p_buckets} br3p added")
```

Then re-run the FULL M1 validation as if G3-Additive were a new milestone:
- Tier A 10/10 BOTH SIDES on (v13, python-algo, heuristic_v1, Lostkids,
  funnel_INTER)
- Tier B vs snorkeldink-v3-1 (target: ≥1/2 ideally 2/2 — must NOT drop
  below the M1-Lite breakthrough)
- Tier C — if G3-Additive populates br1_2/br3p properly, the
  funnel-replay reproduction should now show: when oracle has been
  breached, the opp_model.sample() returns DIFFERENT signatures than the
  br0 fallback. Verify this empirically by loading a funnel-loss state
  with `recent_breaches >= 2` and asserting at least one returned sig
  has `demo_count >= 3` or similar.

### Step 5 — Decision

If G3-Additive passes:
- This becomes "M1.5" or rolls into M2 as a foundation. Ship it.
- The breach-context signal becomes available without breaking br0.

If G3-Additive still regresses:
- Hypothesis was wrong; the regression is in a different mechanism.
- Possible alternatives:
  - `_sigs_for_bucket()` could fall back from br1_2/br3p → br0 if the
    specific bucket has too few signatures (smoothing/backoff)
  - Posterior may be picking up noisy data after a breach that distorts
    predictions even with additive prior
- Document in `G3_INVESTIGATION_RESULTS.md` and abandon G3 for the
  current improvement plan. Move on to M2 (G2 sampling) without it,
  but note that M2's G2 fix relies on populated br1_2/br3p priors so
  may also underperform.

### Output

Final report in `algos/oracle_pure/G3_INVESTIGATION_RESULTS.md`. Include:
- Step 1's bucket-loss table
- Confirmation/refutation of the sample-density hypothesis
- G3-Additive validation results (Tier A, Tier B, Tier C)
- Recommendation: ship G3-Additive as M1.5? Or abandon G3?

---

## How this fits into the master plan

The master `MILESTONE_PROMPTS.md` will be updated to reflect the new state:
- **M1 (original spec, G7+G3)**: REJECTED, archived in MILESTONE_M1_RESULTS.md
- **M1-Lite (G7 only)**: SHIPPED (this document, Part 1)
- **G3-Investigate (G3-Additive variant)**: in progress (this document, Part 2)
  - If G3-Additive passes: becomes "M1.5" foundation for M2
  - If G3-Additive fails: M2 onward proceeds without G3 (degraded)
- **M2-M5**: unchanged in goal, but may need to compensate for missing G3

This keeps forward progress while properly investigating the G3 issue.
