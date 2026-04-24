# RANKED — ranked-replay analysis + queued counters

Poll API every ~30 min. For each new replay:
- If win: log here with ELO delta if available
- If loss: run /detailed-replay and /analyze-replay, identify archetype + losing turn, queue counter in IDEAS.md tagged "ranked-specific"

## Endpoints (from memory: citadel_live_api.md)
- `/api/game/algo/mine/{comp}` — our algo IDs (comp=1338 is Citadel)
- `/api/game/algo/{id}/matches` — matches list (public)
- `/api/game/replayexpanded/{match_id}` — full replay (auth)

Known: v13 algo_id=360023, team WICK id=5826, comp_id=1338.

## Log
_(empty — will populate when user uploads and ranked matches run)_
