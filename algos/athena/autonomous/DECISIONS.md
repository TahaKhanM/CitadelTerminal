# DECISIONS — every nontrivial choice + rationale

### D-001 @ cycle 0 — Skip re-building v13 mirror/defensive tuning family
**Choice:** Do not pursue v16-v19-style defensive micro-tunings.
**Rationale:** Memory `v13_mirror_ceiling.md` confirms 40-40 ceiling in the v13 family is offensive-archetype bound. Defensive tweaks waste cycles.
**Implication:** Pivot to offensive archetype diversification + classifier gating as primary path to top-1.

### D-002 @ cycle 0 — Reuse existing tools where present
**Choice:** Build `tools/evaluate.py` as a thin standardizing wrapper over `tools/bestof.py` + profile_turns + analyze_replay; do not reinvent bestof.
**Rationale:** bestof.py already has Wilson CI. Keep scope tight.

### D-003 @ cycle 0 — Autonomous state in algos/athena/autonomous/, not root
**Choice:** State dir under algos/athena/ to keep related artifacts co-located with the champion line.
**Rationale:** Spec says `algos/athena/autonomous/`.
