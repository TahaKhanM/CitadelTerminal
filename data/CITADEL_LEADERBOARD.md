# Citadel Terminal Competition — Reconstructed Leaderboard (CORRECTED)

**Snapshot**: 2026-04-26T21:27:15.144063+00:00  
**Competition**: Spring 2026 — Europe Terminal (id=1338, deadline 2026-04-27 21:00 UTC)  
**Rosters source**: `/api/game/competition/1338/teams` (26 formed teams)  
**Match data source**: `/api/game/algo/{aid}/matches` for all 92 currently-active config=1072 algos owned by team members → **5,472 unique matches** in the comp window.

## Why my previous report was wrong

Earlier I ranked teams by current `/api/game/user/algo_info/{uid}` ratings. **That endpoint only returns currently-active algos**, and many comp participants iterate aggressively (upload → play 5–15 games → delete → upload v2). When a deleted algo had a peak rating of e.g. 2237, it disappears from the per-user fetch and my snapshot ranks the user by whatever weak placeholder algo they currently have (e.g., louiebwu shows 1 active algo `doublegap` at 1460, but had 11 demo-siege variants today peaking at 2237).

The match graph (`/algo/{aid}/matches`) preserves the rating-at-time-of-match for both winner and loser, including deleted algos. **That is the correct source for "highest demonstrated rating in the Citadel window".** This corrected report uses it.

## How to read this report

Three rating views per team — pick the one that answers the question you care about:

- **Best currently-active algo** — what they could submit as their tournament entry RIGHT NOW. This is what `/user/algo_info` returns. Underestimates fast iterators.
- **Peak (≥5 games)** — highest rating any of that team's algos achieved over ≥5 matches. Filters out single-game rating-bump variance. Best signal for "true demonstrated strength".
- **Peak (any games)** — absolute highest rating ever seen, including 1-2 game samples. Most generous; can be inflated by lucky early-game ELO swings.

Teams can re-create deleted algos (the source code is theirs), but at deadline they enter ONE current algo, and we still cannot tell which.

## TEAM LEADERBOARD — three views

| # by peak (≥5 games) | Team | Members | Active algos | Best ACTIVE rating | Peak (≥5 games) | Peak (any) |
|---|---|---|---|---|---|---|
| 1 | turrets syndrome | louiebwu, icylapras24 | 1 | 1460 (`doublegap`) | **2237** (`demo-siege` by louiebwu, 11g, DELETED) | 2237 (11g, DEL) |
| 2 | bobby tables | aa0, ameyg | 6 | 1981 (`funnel-rush-v6`) | **2229** (`funnel-rush-v23` by aa0, 9g, DELETED) | 2229 (9g, DEL) |
| 3 | No edge | Olezha, sk0812 | 6 | 1738 (`algo-v4`) | **2207** (`oleh-v2` by Olezha, 5g, DELETED) | 2282 (4g, DEL) |
| 4 | Winnerteam | foxhenderson, pipmy | 4 | 2012 (`ascension`) | **2182** (`hello-world` by foxhenderson, 15g, DELETED) | 2182 (15g, DEL) |
| 5 | Wick **← Wick (us)** | TAHA, Rham | 6 | 2167 (`oracle_pure_upload`) | **2167** (`oracle_pure_upload` by TAHA, 24g) | 2167 (24g) |
| 6 | terminal_velocity | hectorkennerley, NKessler | 6 | 1922 (`SwitchV1`) | **2146** (`SwitchV1` by hectorkennerley, 11g, DELETED) | 2146 (11g, DEL) |
| 7 | 3 RINGS | ayo, integrace | 4 | 1716 (`3-Rings-Race-V2`) | **2119** (`python-algo-jae-3` by jae, 7g, DELETED) | 2119 (7g, DEL) |
| 8 | test run | maxrivaldii, gencersarp | 6 | 2004 (`python-algo-babayaga5`) | **2056** (`python-algo-babayaga2` by gencersarp, 11g, DELETED) | 2056 (11g, DEL) |
| 9 | Ctrl Alt Defeat | max guo, Josh | 2 | 1440 (`it_doesnt_meta`) | **2030** (`jack` by Josh, 6g, DELETED) | 2030 (6g, DEL) |
| 10 | Team1 | diego, n1hal | 5 | 1830 (`python-algo`) | **1976** (`python-algo` by n1hal, 6g, DELETED) | 2162 (2g, DEL) |
| 11 | The Gooners | Orangemath, shrxvxn007 | 6 | 1908 (`goonmaxxing`) | **1933** (`goonmaxxing` by zarifahmed997, 11g, DELETED) | 1933 (11g, DEL) |
| 12 | Lehman Brothers Quant | Purujit_Aggarwal, Denys | 3 | 1722 (`python-algo`) | **1867** (`python-algo` by Purujit_Aggarwal, 5g, DELETED) | 1898 (4g, DEL) |
| 13 | Sk | Egil, krishpatel | 2 | 1760 (`python-algo-v5`) | **1760** (`python-algo-v5` by Egil, 53g) | 1947 (3g, DEL) |
| 14 | citftw | txnujk09, not-tnb | 6 | 1747 (`python-algo`) | **1747** (`python-algo` by suchir-g, 161g) | 1747 (161g) |
| 15 | WFreakS | vik07x, sheli | 6 | 1688 (`python-algo-v9`) | **1724** (`python-algo-v15` by sheli, 5g, DELETED) | 1798 (1g, DEL) |
| 16 | sr | ragd, Stella | 4 | 1712 (`python-algo1`) | **1712** (`python-algo1` by jaminimathur, 17g) | 2059 (2g, DEL) |
| 17 | The OverFiTTERS | pxannnna, Nicole | 5 | 1626 (`V6-A`) | **1697** (`v3` by Nicole, 6g, DELETED) | 1697 (6g, DEL) |
| 18 | (unnamed) | Hera Choi, Arun__ls | 2 | 1697 (`python-algo`) | **1697** (`python-algo` by Arun__ls, 12g) | 1697 (12g) |
| 19 | Better jmc  | sebk, hussain | 2 | 1580 (`python-algo`) | **1580** (`python-algo` by hussain, 132g) | 1580 (132g) |
| 20 | nala | Roshan, octavian202 | 1 | 1449 (`python-algo`) | **1449** (`python-algo` by octavian202, 147g) | 1449 (147g) |
| 21 |   | Vmc, Ryan_Lam | 5 | 1445 (`python-algo`) | **1445** (`python-algo` by mjwtan, 132g) | 1527 (1g, DEL) |
| 22 | sudo rm -rf /* | Krey, Zan | 2 | 1423 (`python-algo`) | **1423** (`python-algo` by Krey, 174g) | 1423 (174g) |
| 23 | thebigwick | angshumang | 1 | 1055 (`python-algo`) | **1055** (`python-algo` by angshumang, 6g) | 1055 (6g) |

## TOP 30 ALGOS BY PEAK MATCH-GRAPH RATING (across the 26-team roster)

| # | id | peak | name | user | team | games | active? |
|---|---|---|---|---|---|---|---|
| 1 | 360639 | **2282** | `oleh-v2` | Olezha | No edge | 4 (2-2) | DELETED |
| 2 | 361148 | **2237** | `demo-siege` | louiebwu | turrets syndrome | 11 (11-0) | DELETED |
| 3 | 360524 | **2229** | `funnel-rush-v23` | aa0 | bobby tables | 9 (9-0) | DELETED |
| 4 | 360539 | **2227** | `funnel-rush-v25` | aa0 | bobby tables | 7 (7-0) | DELETED |
| 5 | 361133 | **2216** | `demo-siege` | louiebwu | turrets syndrome | 13 (12-1) | DELETED |
| 6 | 360704 | **2211** | `funnel-rush-v8` | ameyg | bobby tables | 6 (6-0) | DELETED |
| 7 | 360491 | **2210** | `funnel-rush-v7` | aa0 | bobby tables | 9 (9-0) | DELETED |
| 8 | 360541 | **2208** | `funnel-rush-v6` | ameyg | bobby tables | 7 (7-0) | DELETED |
| 9 | 360454 | **2207** | `oleh-v2` | Olezha | No edge | 5 (4-1) | DELETED |
| 10 | 360425 | **2204** | `funnel-rush` | aa0 | bobby tables | 6 (6-0) | DELETED |
| 11 | 360548 | **2200** | `oleh-v2` | Olezha | No edge | 3 (2-1) | DELETED |
| 12 | 360469 | **2199** | `funnel-rush-v5` | aa0 | bobby tables | 10 (9-1) | DELETED |
| 13 | 360496 | **2196** | `funnel-rush-v9` | aa0 | bobby tables | 8 (7-1) | DELETED |
| 14 | 360038 | **2195** | `python-algo` | aa0 | bobby tables | 5 (4-1) | DELETED |
| 15 | 360572 | **2189** | `funnel-rush-v25b` | aa0 | bobby tables | 8 (8-0) | DELETED |
| 16 | 360513 | **2187** | `funnel-rush-v20` | aa0 | bobby tables | 6 (5-1) | DELETED |
| 17 | 361170 | **2186** | `python-algo` | aa0 | bobby tables | 12 (12-0) | DELETED |
| 18 | 360681 | **2185** | `funnel-rush-v75d` | aa0 | bobby tables | 5 (5-0) | DELETED |
| 19 | 361253 | **2182** | `hello-world` | foxhenderson | Winnerteam | 15 (14-1) | DELETED |
| 20 | 360665 | **2181** | `funnel-rush-v67b` | aa0 | bobby tables | 6 (6-0) | DELETED |
| 21 | 360628 | **2178** | `funnel-rush-v7-1` | ameyg | bobby tables | 9 (9-0) | DELETED |
| 22 | 360547 | **2174** | `funnel-rush-v32` | aa0 | bobby tables | 8 (8-0) | DELETED |
| 23 | 361251 | **2167** | `oracle_pure_upload` **(Wick)** | TAHA | Wick | 24 (21-3) | YES |
| 24 | 359909 | **2162** | `python-algo` | n1hal | Team1 | 2 (2-0) | DELETED |
| 25 | 360520 | **2160** | `funnel-rush-v22` | aa0 | bobby tables | 7 (7-0) | DELETED |
| 26 | 360583 | **2158** | `funnel-rush-v43` | aa0 | bobby tables | 8 (7-1) | DELETED |
| 27 | 360686 | **2151** | `funnel-rush-v75g` | aa0 | bobby tables | 5 (5-0) | DELETED |
| 28 | 360662 | **2150** | `funnel-rush-v67` | aa0 | bobby tables | 6 (6-0) | DELETED |
| 29 | 360509 | **2150** | `funnel-rush-v17` | aa0 | bobby tables | 6 (5-1) | DELETED |
| 30 | 360492 | **2148** | `funnel-rush-v8` | aa0 | bobby tables | 9 (7-2) | DELETED |

## Wick (us) — actual position

- **Rank by best active algo: #1 / 23** (oracle_pure_upload, rating 2167, 24 games)
- **Rank by peak (≥5 games): #5 / 23** — same algo at peak 2167.
- **Currently selected tournament entry: `v13_second_ring` (rating 1530)** — this is what we will play in the round-robin.

### How Wick compares

Above us by peak rating (≥5 games): teams that demonstrated 2167+ in the comp window:
- **#1 turrets syndrome** — peak 2237 via `demo-siege` (louiebwu, 11 games, deleted)
- **#2 bobby tables** — peak 2229 via `funnel-rush-v23` (aa0, 9 games, deleted)
- **#3 No edge** — peak 2207 via `oleh-v2` (Olezha, 5 games, deleted)
- **#4 Winnerteam** — peak 2182 via `hello-world` (foxhenderson, 15 games, deleted)

### Caveat — these are noisy signals, not standings

Most peak-leaders are deleted algos with 5–15 games of history; ELO over a small sample is high variance. Wick's 2167 has 24 games and is still active — a more stable estimate. Without the actual round-robin (which has not started — `participated: 0`), we cannot rank teams definitively.

## Files

- `data/citadel_roster.json` — 26 teams, members, currently-active algos
- `data/citadel_matches.json` — full match graph (5,472 records, includes deleted algos' rating history)
- `data/citadel_leaderboard_team_corrected.json` — this view (3 ratings per team)
- `data/citadel_leaderboard_algo.json` — algo-level (active-only, original)