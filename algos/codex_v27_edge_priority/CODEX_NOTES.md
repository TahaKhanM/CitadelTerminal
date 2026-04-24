# codex_v26_edge_keep

Forked from `codex_v25_burst12` after `replays/v21_loss_1.replay` showed the real ranked loss mode:

- v21 as P1 scored 0 and died at turn 62.
- Opponent breaches were concentrated at `(23,9)`, `(21,7)`, `(20,6)`.
- The replay-derived local adversary `codex_opp_edge_caravan` 6-0'd `codex_v25_burst12`, mostly through `(7,6)`, `(20,6)`, `(6,7)`.

Delta:
- Adds `_edge_keep`, building and upgrading low-edge Turrets at `[7,6]`, `[20,6]`, `[6,7]`, `[21,7]`, then outer Turrets `[4,9]`, `[23,9]`.
- Adds upgraded edge walls at `[5,8]`, `[22,8]`, `[8,7]`, `[19,7]`.
- Runs before adaptive Support spending so edge survival has priority.
