# Iteration History: v1 → v11 (→ final_v11)

Compact log of what was tried, what worked, and what was dropped during the
initial algo-building session. Preserved here so future iterations don't
repeat failed approaches.

## Progression

| Version | Archetype | Key idea | Outcome |
|---|---|---|---|
| v1_baseline | Central funnel + adaptive Scout rush | Basic defense w/ reactive placement | Beat starter but weak against strong opponents |
| v2_fortress | Heavy opening + enemy-MP-aware defense | Overspent SP early; didn't score enough | Marginal improvement |
| v3_wall | Full-width wall line + 6 evenly-spaced Turrets | Simple horizontal barrier | Solid defense but offense couldn't exit through own walls |
| v4_funnel | Wall line with center gaps for own Scouts | Gaps at [13,12]/[14,12] for Scout exit | Concept sound, but Turrets at [13,11]/[14,11] blocked Scouts approaching the gap |
| v5_econ | Funnel + Support economy + Demolisher vs dense | Added Supports for resource gen; Demo when enemy stacks front | Economy boost helped but Supports died (HP=1) |
| v6_rush | Bigger Scout waves (≥15 MP) + Demo frontliner | Waited for 15+ MP before attacking | Scored well but leaked defense while saving MP |
| v7_hardcorners | Plug (1,15)/(26,15) breach paths | Fixed corner-leak weakness from v1-v6 | Meaningful defense improvement |
| v8_hybrid | v7 defense + Support econ + Demo switch | Combined best of v5+v7 | Solid but still predictable offense |
| v9_aggrodef | v7 base + faster outer-Turret upgrades + Demo vs dense | Aggressive upgrade timing | Better mid-game but side-lane gaps remained |
| v10_sidelanes | Open escape paths at x=4,23 on y=12 for Scouts | Side gaps for offense | Offense worked but defense collapsed at the gaps |
| **v11_centergap** | Gaps at [12,12]/[15,12] covered by 4 upgraded center Turrets | Fixed v4's gap-blocking issue — Scouts approach from [12,11]/[15,11] which are open | **Shipped as final_v11** |

## Key lessons learned

1. **Wall gaps must not be adjacent to Turrets** — Turrets block pathing for own Scouts trying to exit through the gap. Put gaps 1 tile away from Turrets.
2. **Supports at HP=1 die instantly** — don't invest heavily without wall protection. Upgraded Supports (40 HP, 10.1 shield/unit at Y=13) are much better value.
3. **Saving MP loses MP** — 25% decay means a "save for big push" strategy costs ~44% over 2 turns. Spend each turn.
4. **Corner leaks are a common failure** — positions (0,13)/(27,13) and (1,13)/(26,13) need Turrets to prevent free scoring along the diagonal.
5. **Upgrade timing matters** — upgrading Turrets turn-by-turn starting at turn 12 (when income catches up) is better than building more base Turrets.
6. **Side lanes are double-edged** — a gap at y=12 for your Scouts also lets enemy units through. Need upgraded Turret coverage to compensate.

## What final_v11 does

- **Defense**: wall line at y=12 with gaps at [12,12] and [15,12]. 12 Turrets (4 center + 4 outer + 4 corner), all upgraded by turn 24. Corner Turrets at [0,13]/[1,13]/[26,13]/[27,13].
- **Offense**: adaptive Scout rush from the side with fewer enemy Turrets. Demolisher line when enemy stacks structures near y=14.
- **Reactive**: records breach locations via `on_action_frame`; places Turrets above breach tiles.
- **Economy**: spends MP every turn (no hoarding). SP priorities: Turrets → Turret upgrades → Walls → reactive fills.

## Known weaknesses (from ranked replay analysis)

See `docs/REPLAY_ANALYSIS_2026-04-23.md` for the full analysis. Top-3 problems:
1. Tiles (23,9) and (6,7) get breached repeatedly in losses — strong opponents pierce through our mid-defense.
2. No Support-caravan shielding — we leave 10.1 shield/unit on the table at Y=13.
3. Offense is predictable — always attacks from `[13,0]`; strong opponents read and defend it.
