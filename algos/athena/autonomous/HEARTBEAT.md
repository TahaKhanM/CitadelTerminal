# HEARTBEAT — user-visible progress log

### Cycle 0 @ 2026-04-24 — Agent online
Bootstrapping; spawn RA briefs + build evaluate.py.

### Cycle 1 @ 2026-04-24 — Bootstrap done, wave-1 built
RA-1..5 integrated. Baselines: v13 and v14 TIE each other 8-0-8 (mirror ceiling confirmed). Wave-1 variants built.

### Cycle 2 @ 2026-04-24 — Wave-1 evaluated, v14 identified as barrier
v20_demo_train loses 0-8 vs v14 (ΔHP-40). v20_sidelane + v21_scout_probe tie both mirrors. Pivot to anti-caravan.

---

### 🎯 Cycle 3 @ 2026-04-24 — **CHAMPION CANDIDATE: v21_temporal_phase_gate**
**Status:** wave-2 evaluated; ranked-loss analysis complete; v21 sequentially re-validated.
**Results:**
- **v21_temporal_phase_gate 40/40 @ n=8:** beats v13 ΔHP+7 (T100), v14 ΔHP+42 (T58), plus 8-0 vs every adversary. INVENTION #2 breaks the mirror ceiling.
- v20_burst_scout: 3 real ties vs v14 (5 games crashed in parallel-eval race); archive.
- v20_support_kill: 0-0-8 vs v14; archive.
- **v20_flank_edge: regression** — lost 0-8 vs v14 AND opp_scout_rush (edge-paths are death traps).
- v20_hybrid_turtle_cracker: 0-6 vs v14 (classifier false-positives); needs iteration.

**Ranked replay analysis (47 games, 22W/25L):**
- 12 losses to scout_rush, 7 to oleh-v2 alone (22% WR vs oleh-v2)
- 7 losses to turret_castle (all T100 timeouts — we survive but can't breach)
- 3 losses to demo_line at 1800+ ELO (coin flip)
- 0 losses to support_caravan, flank, sd_bomb (archetypes not encountered)

**Adversary pool:** 3/20. **Inventions:** **2/3** (v21_scout_probe, v21_temporal_phase_gate). **Variants:** 8 built.
**Termination status:** A ☐ B ☐ C ☐ D ☐ E ☐ F ☐ G ☐ — Wilson CI still wide (n=8). Validating at n=15.

**📦 UPLOAD-READY trigger pending:** v21 n=15 validation completes next cycle. If Wilson LB ≥0.85 holds → emit `UPLOAD_READY_v21.md`. v21 offers a real mirror-break + novel tactical case (temporal opponent-MP tracking — no public finalist does this).

**Next cycle priorities:**
1. v22_oleh_counter (anti-scout_rush / anti-oleh-v2) — biggest ranked-ELO gain
2. v22_siege_breaker (anti-turret_castle) — 7 free wins in ranked
3. v22_phase_gate_hybrid (v21 + oleh_counter + siege_breaker)
4. v21_quadrant_adaptive_support (INVENTION #3, last required)

**Concurrency bug:** parallel evaluate.py crashes v13 runs. Working around by running sequentially until a fix lands.
