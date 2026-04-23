# Obtaining & Analyzing Replays

Official competition replays are **the single highest-signal artifact you can collect** for iteration. A downloaded `.replay` file contains:

- The **live server config** on the first line — the authoritative competition values.
- Every frame of the deploy and action phases (typically 2,000-4,000 frames in a full game; file sizes 3-6 MB).
- Millisecond-precision compute times per side per turn.
- Unit IDs that track individual units across frames (lets you see which specific Scout survived the longest, etc.).
- Complete `events` arrays: `spawn`, `death`, `attack`, `damage`, `breach`, `shield`, `move`, `selfDestruct`, `melee`.
- Opponent's full strategy as it actually executed.

This doc covers **how to obtain replays** and **why screen recording is not a substitute**.

---

## 1. Screen recording vs. downloading: no contest

**Always download the `.replay` file.** Screen recording is worse in every dimension that matters:

| Axis | Download `.replay` | Screen record |
|---|---|---|
| Get the live server config | ✓ First line of the file | ✗ Not visible in UI |
| Turn-by-turn resource timeline | ✓ Instant Python parse | ✗ Eyeball + pause, OCR text |
| Per-turn compute times | ✓ To the millisecond | ✗ Not shown in UI |
| Event data (deaths, damage, shields) | ✓ Complete, exact | ✗ Inferred from animation |
| Individual unit tracking | ✓ Unique IDs | ✗ Indistinguishable blobs |
| Can re-feed into `test_algo` to reproduce crashes | ✓ | ✗ |
| Parse 20+ games in parallel | ✓ Seconds | ✗ Would take hours |
| File size | 3-6 MB | 50-500 MB (compressed video) |
| Sharing with analysis tools | ✓ Text JSON lines | ✗ Binary video |

Use a screen recording **only** if you want a quick visual to share with a human teammate who doesn't have the repo set up. For any analytical work — tuning strategy, spotting patterns, verifying compute budget — use the `.replay` file.

---

## 2. How to download an official replay

### From a ranked/playground match on the website

1. Log into https://terminal.c1games.com.
2. Go to **"My Global Replays"** (for ranked games) or **"My Algos" → click an algo → its replay list** (for playground).
3. Click the replay you want to examine → this opens the in-browser viewer.
4. Look for the **download button** (usually a ⬇ icon near the replay title). If the UI doesn't expose one, use the browser's Network tab (DevTools → Network → refresh → filter for `.replay`) and right-click → Save As.
5. Save to `replays/` in this repo, with a descriptive name:
   ```
   replays/v11_ranked_vs_topbot_2026-04-23.replay
   replays/v12_loss_vs_funnelking.replay
   replays/v12_win_vs_scoutrusher.replay
   ```

### ⭐ Recommended naming convention (unlocks ELO-aware analysis)

```
<your_algo>_vs_<opponent_ELO>_elo_<win|loss>.replay

# Examples:
final_v11_vs_1841_elo_loss.replay      # lost to a 1841-ELO opponent
final_v11_vs_1756_elo_win.replay       # upset win over a 1756-ELO opponent
final_v11_vs_1472_elo_win.replay       # win vs a weaker opponent
```

Why this format: `tools/batch_replays.py` and `tools/detailed_replay.py` parse the ELO and outcome straight from the filename. When you pass `--my-elo <N>` to batch, it produces:

- **Win rate by ELO bracket** (≤-200, -199..-50, -49..+49, +50..+150, >+150 gap).
- **Losses sorted by opponent strength** — your highest-priority study material.
- **Upset wins** (beating stronger opponents) — what *actually works* against strong play.
- **Breach hot spots IN LOSSES specifically** — where strong opponents pierce you.
- **Enemy spawn tiles IN LOSSES** — how strong opponents deploy against you.

Without the convention, the tools still work — they just skip the ELO analysis. Any filename format is accepted; only the `_vs_<digits>_elo_<win|loss>` substring is required to unlock the extra tier.

The tools work regardless of filename, but a good name saves time when batch-analyzing.

### From a tournament or playground game you played

The flow is the same — click the game, download the `.replay`. The playground's "replay save" setting must be enabled in your account settings (it usually is by default).

### Batch-downloading (many ranked games at once)

The website doesn't offer a bulk-download button. Options:

1. **Manual** — click each, download each. Tedious but reliable for ≤ 20 games.
2. **Browser automation** — if you're willing: use a userscript or Puppeteer to iterate your Global Replays list and auto-download each. Cookie-authenticated.
3. **Network interception** — with DevTools open, click each replay once (triggering the load), then extract all replay URLs from Network log, and `curl` with your session cookie. ~5 minutes for 30 games.

Drop all downloaded files in `replays/ranked/` (create the subdir) and point `/batch-replays` at them.

---

## 3. Running a ranked game to GET a replay

You can't force a ranked match on demand, but you can reliably generate fresh replays by:

- **Playground**: upload your algo and a target opponent (or a boss), start a playground match. Guaranteed replay.
- **Upload an algo**: once compiled, it auto-queues ranked matches. After 10-15 minutes you'll have a handful of fresh ranked replays in "My Global Replays".

For development iteration, playground matches are usually sufficient and faster.

---

## 4. Analyzing one replay deeply

Once downloaded into `replays/`:

```bash
# Full detail report (all sections)
python3 tools/detailed_replay.py replays/my_replay.replay

# Just the live config (useful for verifying current competition values)
python3 tools/detailed_replay.py replays/my_replay.replay --section config

# Deep-dive on a specific turn (the one where everything went sideways)
python3 tools/detailed_replay.py replays/my_replay.replay --turn 47

# Machine-readable for scripting
python3 tools/detailed_replay.py replays/my_replay.replay --json
```

The tool handles both **official replays** (with the live config as line 1) and **local replays** (from `./tools/run.sh`) gracefully — it just tells you which format it detected.

Sections available: `config`, `economy`, `structures`, `upgrades`, `scoring`, `attrition`, `shielding`, `spawns`, `damage`, `compute`.

Or use the skill: `/detailed-replay <path>`.

---

## 5. Analyzing many replays for patterns

When you've collected 5+ ranked games, batch-analyze:

```bash
# Using the recommended convention (unlocks ELO-aware analysis)
python3 tools/batch_replays.py replays/final_v11_vs_*_elo_*.replay \
    --me final_v11 --my-elo 1689 --top 15

# All replays (no ELO analysis if filenames don't match the convention)
python3 tools/batch_replays.py replays/**/*.replay --top 15
```

The output surfaces:

- **Win rate** (overall and per-side).
- **Top tiles where enemy breached your defense** — recurring weak spots you should reinforce.
- **Top tiles where your mobiles scored** — confirms your offense is reliable.
- **Top enemy spawn tiles** — their deploy patterns (lets you angle defense).
- **Compute-time trend** — if max creeps up game over game, your algo is getting slower over a match.
- **Outlier games** — blowouts vs. close calls worth individually reviewing.

This is ~50 replays/second on 10 cores. Don't hesitate to run over everything.

Or: `/batch-replays <paths>`.

---

## 6. Reproducing a bug from an official replay

If the algo you uploaded crashed or behaved oddly in a ranked game, reproduce it locally:

```bash
# Feed the official replay states into your local algo
./tools/test.sh algos/final_v11 replays/official_problem.replay
```

`test_algo` streams the replay's frames into your algo; your algo sees the same states (including the real server config) and you see any stack traces or unusual behavior locally. This is the SINGLE MOST VALUABLE debugging workflow for ranked issues.

---

## 7. Suggested folder layout

```
replays/
├── ranked/                            # downloads from "My Global Replays"
│   ├── v11_loss_vs_funnelking_2026-04-23.replay
│   └── v11_win_vs_scoutrusher_2026-04-23.replay
├── playground/                        # downloads from playground matches
│   └── v11_vs_boss_crowbar.replay
├── bestof_*/                          # created by tools/bestof.py
├── tournament_*/                      # created by tools/tournament.py
└── *.replay                           # created by tools/run.sh
```

Git ignore is set so replays don't get committed (they're large and binary-ish). If you need to share a specific replay, do it out-of-band (Slack, email, a scratch gist).

---

## 8. Summary

- **Always download, never screen-record.** The `.replay` file has an order of magnitude more signal than a video ever could.
- **One command can analyze one replay deeply**: `python3 tools/detailed_replay.py <file>`.
- **One command can analyze many in parallel**: `python3 tools/batch_replays.py replays/**/*.replay`.
- **Official replays carry the authoritative live config** — always prefer them for config-sensitive decisions.
- **Reproducing a crash locally**: `./tools/test.sh <algo> <replay>`.
