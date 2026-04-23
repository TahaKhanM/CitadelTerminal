#!/usr/bin/env python3
"""
scrape_ranked_replays.py — download your uploaded algos' ranked replays from
terminal.c1games.com.

Auth model:
  - Cookie-based. The tool uses an authenticated session cookie saved at
    ~/.c1_session.json (overridable). You get the cookie once via the
    `login` subcommand (headed Selenium) OR by pasting your browser's cookie
    header directly with `login --paste`.
  - All match/replay downloads are pure `requests` calls — no browser needed
    at run time.

Subcommands:
  login [--paste | --browser chrome|firefox]
      One-time: capture the logged-in session cookie.
  whoami
      Verify the cookie is live (hits a self-info endpoint; prints user).
  discover [--probe]
      Hit a menu of likely API endpoints, print status+truncated body for
      each. Use this first time to confirm current API shape if the
      defaults below change.
  harvest [--browser chrome|firefox] [--pages URL...]
      Open a browser, you click through My Algos / an algo's matches /
      open a replay; we log every XHR/fetch the page issued to a JSON
      file. Use when `discover` shows 404 HTML — the site's API schema
      changes between seasons.
  list-algos
      List your uploaded algos (algo_id + name).
  list-matches --algo ALGO_ID [--ranked-only] [--limit N]
      List matches played by that algo, ranked first.
  download --algo ALGO_ID [--ranked-only] [--limit N] [--out DIR]
      Download each match's replay as <algo>_<date>_vs_<opp>_<elo>_<result>.replay

Examples:
    python3 tools/scrape_ranked_replays.py login --browser firefox
    python3 tools/scrape_ranked_replays.py list-algos
    python3 tools/scrape_ranked_replays.py download --algo 12345 --limit 30 \\
         --ranked-only --out replays/ranked/

If a hard-coded endpoint returns 404, run `discover` and update the
ENDPOINTS dict below — the C1 site has changed path schemes in the past.
"""

from __future__ import annotations

import argparse
import json
import os
import re
import sys
import time
from datetime import datetime
from pathlib import Path
from typing import Any, Dict, List, Optional

try:
    import requests
except ImportError as e:
    sys.stderr.write("This tool requires the `requests` package. Install: pip install requests\n")
    raise

BASE = "https://terminal.c1games.com"
UA = (
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_0) AppleWebKit/605.1.15 "
    "(KHTML, like Gecko) Version/16.0 Safari/605.1.15"
)
DEFAULT_COOKIE_FILE = Path.home() / ".c1_session.json"
DEFAULT_OUT_DIR = Path(__file__).resolve().parent.parent / "replays" / "ranked"

# These are best-guess endpoint paths based on community references
# (forum.c1games.com/t/using-the-terminal-api/806 and prior scrapers that
# hit /api/game/algo/mine). If any returns 404/HTML instead of JSON, run
# `discover` and patch this dict.
ENDPOINTS = {
    "whoami":       "/api/user/mine",
    "list_algos":   "/api/game/algo/mine",
    # For a specific algo, its matches list:
    "list_matches": "/api/game/algo/{algo_id}/matches",
    # For a specific match, the replay file:
    "download_replay": "/api/game/replay/{match_id}",
    # Fallback candidates (some generations of the site):
    "_alt_list_matches_1": "/api/game/match?algo={algo_id}",
    "_alt_list_matches_2": "/api/game/algo/{algo_id}/history",
    "_alt_download_replay_1": "/api/game/match/{match_id}/replay",
    "_alt_download_replay_2": "/api/game/download-replay?match={match_id}",
}


# ------------------------------------------------------------------ cookies

def load_cookie_jar(path: Path) -> Dict[str, str]:
    if not path.exists():
        sys.stderr.write(
            f"No session cookie at {path}. Run `login` subcommand first.\n"
        )
        sys.exit(2)
    with open(path) as f:
        jar = json.load(f)
    return jar


def save_cookie_jar(jar: Dict[str, str], path: Path) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w") as f:
        json.dump(jar, f, indent=2, sort_keys=True)
    os.chmod(path, 0o600)
    print(f"Saved {len(jar)} cookie(s) to {path}")


def build_session(jar: Dict[str, str]) -> requests.Session:
    s = requests.Session()
    s.headers.update({
        "User-Agent": UA,
        "Accept": "application/json, text/plain, */*",
        "Referer": BASE + "/",
        "Origin": BASE,
    })
    for k, v in jar.items():
        s.cookies.set(k, v, domain="terminal.c1games.com")
    return s


# --------------------------------------------------------------- login flows

def login_paste() -> Dict[str, str]:
    """User pastes browser cookie header; we split into a jar."""
    print("Open terminal.c1games.com in your browser while logged in.")
    print("DevTools → Application → Cookies → terminal.c1games.com")
    print("Copy the VALUES of these cookies (csrftoken, sessionid, or whatever")
    print("appears — typically 1–3 cookies) and paste as `name=value; name=value`:")
    raw = input("cookie header: ").strip().strip('"').strip("'")
    jar: Dict[str, str] = {}
    for part in raw.split(";"):
        part = part.strip()
        if not part:
            continue
        if "=" not in part:
            continue
        name, val = part.split("=", 1)
        jar[name.strip()] = val.strip()
    if not jar:
        sys.exit("No cookies parsed. Abort.")
    print("Parsed cookies:", list(jar.keys()))
    return jar


def login_browser(browser: str = "firefox") -> Dict[str, str]:
    """Spawn a headed browser, wait for user to log in, read the session cookies."""
    try:
        from selenium import webdriver
        from selenium.webdriver.common.by import By  # noqa: F401
    except ImportError:
        sys.exit(
            "Selenium login needs `selenium`. `pip install selenium` first, "
            "or use `login --paste` to avoid Selenium."
        )
    if browser == "chrome":
        opts = webdriver.ChromeOptions()
        opts.add_argument("--disable-blink-features=AutomationControlled")
        driver = webdriver.Chrome(options=opts)
    else:
        opts = webdriver.FirefoxOptions()
        driver = webdriver.Firefox(options=opts)

    driver.get(BASE + "/login")
    print("\n*** Log in in the browser window, then navigate to")
    print("*** https://terminal.c1games.com/myAlgos ***")
    input("Press Enter here once you're logged in and on My Algos... ")
    jar = {c["name"]: c["value"] for c in driver.get_cookies()}
    driver.quit()
    return jar


# ------------------------------------------------------------------ API calls

class Client:
    def __init__(self, jar: Dict[str, str]):
        self.s = build_session(jar)

    def get(self, path: str, **kw) -> requests.Response:
        url = path if path.startswith("http") else BASE + path
        return self.s.get(url, timeout=30, **kw)

    def get_json(self, path: str) -> Any:
        r = self.get(path)
        ct = r.headers.get("Content-Type", "")
        if r.status_code != 200:
            raise RuntimeError(
                f"GET {path} → {r.status_code} {ct} body[:200]={r.text[:200]!r}"
            )
        if "application/json" not in ct:
            raise RuntimeError(
                f"GET {path} → 200 but not JSON; body[:200]={r.text[:200]!r} — "
                "cookie likely expired or endpoint path changed."
            )
        return r.json()


# ------------------------------------------------------------------ helpers

def _first_matching(d: Any, keys: List[str], default=None):
    if not isinstance(d, dict):
        return default
    for k in keys:
        if k in d:
            return d[k]
    return default


def _normalize_match_row(row: Dict[str, Any]) -> Dict[str, Any]:
    """Best-effort normalization. The site has returned varying schemas over
    the years; we try common key names and keep the raw row around."""
    rid = _first_matching(row, ["id", "matchId", "match_id", "replayId"])
    ranked = _first_matching(row, ["ranked", "isRanked", "is_ranked"], default=False)
    date = _first_matching(row, ["date", "created", "createdAt", "timestamp"])
    result = _first_matching(row, ["result", "winner", "outcome"])
    p1 = _first_matching(row, ["player1", "p1", "algo1", "my"], default={})
    p2 = _first_matching(row, ["player2", "p2", "algo2", "opponent"], default={})
    p1n = _first_matching(p1 if isinstance(p1, dict) else {}, ["name", "algoName", "user"])
    p2n = _first_matching(p2 if isinstance(p2, dict) else {}, ["name", "algoName", "user"])
    p1e = _first_matching(p1 if isinstance(p1, dict) else {}, ["elo", "rating"])
    p2e = _first_matching(p2 if isinstance(p2, dict) else {}, ["elo", "rating"])
    return {
        "id": rid, "ranked": bool(ranked), "date": date, "result": result,
        "p1_name": p1n, "p2_name": p2n, "p1_elo": p1e, "p2_elo": p2e,
        "_raw": row,
    }


def _safe_name(s: str, fallback: str = "x") -> str:
    if not s:
        return fallback
    s = re.sub(r"[^A-Za-z0-9._-]+", "_", str(s))
    return s[:40] or fallback


# --------------------------------------------------------------- subcommands

def cmd_login(args) -> int:
    if args.paste:
        jar = login_paste()
    else:
        jar = login_browser(browser=args.browser)
    save_cookie_jar(jar, Path(args.cookie_file))
    # sanity probe
    c = Client(jar)
    try:
        data = c.get_json(ENDPOINTS["whoami"])
        print("whoami →", data)
    except Exception as e:
        print(
            f"whoami probe failed: {e}\n"
            "Cookies saved anyway; try `discover` to find current endpoints."
        )
    return 0


def cmd_whoami(args) -> int:
    jar = load_cookie_jar(Path(args.cookie_file))
    c = Client(jar)
    data = c.get_json(ENDPOINTS["whoami"])
    print(json.dumps(data, indent=2))
    return 0


def cmd_discover(args) -> int:
    """Try each endpoint variant, print verdict. This is how we adapt if the
    site changes path schemes between seasons."""
    jar = load_cookie_jar(Path(args.cookie_file))
    c = Client(jar)
    print("Probing endpoints with current cookie…\n")
    any_json = False
    for name, path in ENDPOINTS.items():
        if "{" in path:
            continue  # need id; skip
        r = c.get(path)
        ct = r.headers.get("Content-Type", "").split(";")[0]
        body = r.text[:200].replace("\n", " ")
        if "application/json" in ct:
            any_json = True
        print(f"[{r.status_code} {ct}] {name} {path}\n    {body!r}\n")
    if not any_json:
        print("No endpoint returned JSON — the C1 API shape has likely changed.")
        print("Run: python3 tools/scrape_ranked_replays.py harvest --browser firefox")
        print("then click through My Algos → one of your algos → a match. We'll log")
        print("every XHR/fetch and show you the current endpoints to paste into ENDPOINTS.")
    return 0


def cmd_harvest(args) -> int:
    """Open a browser with the current session cookies; the user clicks
    through My Algos / matches / a replay; we dump all XHR+fetch requests
    to a JSON file so we can identify the current API endpoints.

    Implementation: uses Chrome's Performance Logs (always available on
    Chromium) to capture Network.* events. Falls back to noting that
    Firefox can't do this without mitmproxy.
    """
    try:
        from selenium import webdriver
    except ImportError:
        sys.exit("harvest needs selenium. `pip install selenium` and try again.")

    browser = args.browser
    if browser == "firefox":
        print("Firefox can't export performance logs without a proxy.")
        print("Switch to Chrome: harvest --browser chrome, OR install mitmproxy:")
        print("  brew install mitmproxy && mitmdump -s <script>")
        print("and we can add Firefox support later.")
        return 4

    jar = load_cookie_jar(Path(args.cookie_file))
    opts = webdriver.ChromeOptions()
    opts.add_argument("--disable-blink-features=AutomationControlled")
    opts.set_capability("goog:loggingPrefs", {"performance": "ALL"})
    driver = webdriver.Chrome(options=opts)
    # Apply cookies: Chrome only accepts cookies after we've loaded the domain.
    driver.get(BASE + "/")
    time.sleep(1.0)
    for name, value in jar.items():
        try:
            driver.add_cookie({"name": name, "value": value, "domain": "terminal.c1games.com"})
        except Exception as e:
            print(f"  cookie {name} rejected: {e}")
    driver.get(BASE + "/myAlgos")

    print("\nClick through: My Algos → pick an algo → a match → open replay / download.")
    print("Interact with every page whose network calls you want us to log.")
    input("When done, press Enter here to dump the network log... ")

    events = driver.get_log("performance")
    xhrs: List[Dict[str, Any]] = []
    for e in events:
        try:
            msg = json.loads(e["message"])["message"]
        except Exception:
            continue
        if msg.get("method") != "Network.responseReceived":
            continue
        p = msg["params"]
        resp = p.get("response", {})
        url = resp.get("url", "")
        if not url.startswith(BASE):
            continue
        # Skip static assets
        ct = resp.get("mimeType", "")
        if ct.startswith(("image/", "font/", "text/css")) or url.endswith((".js", ".css", ".png", ".svg", ".ico")):
            continue
        xhrs.append({
            "url": url,
            "method": p.get("type", ""),
            "status": resp.get("status"),
            "mime": ct,
        })
    driver.quit()

    seen = set()
    unique = []
    for x in xhrs:
        key = (x["url"].split("?")[0], x["mime"])
        if key in seen:
            continue
        seen.add(key)
        unique.append(x)

    out = Path(args.out)
    out.parent.mkdir(parents=True, exist_ok=True)
    with open(out, "w") as f:
        json.dump(unique, f, indent=2, sort_keys=True)
    print(f"\nLogged {len(unique)} unique endpoints to {out}:\n")
    for x in unique:
        print(f"  [{x['status']} {x['mime'][:24]:<24}] {x['url']}")
    print("\nFind the ones that look like match/replay APIs and paste their paths")
    print("into the ENDPOINTS dict at the top of tools/scrape_ranked_replays.py.")
    return 0


def cmd_list_algos(args) -> int:
    jar = load_cookie_jar(Path(args.cookie_file))
    c = Client(jar)
    data = c.get_json(ENDPOINTS["list_algos"])
    # Common envelope shapes: {"algos": [...]}, {"data": {"algos": [...]}}, or a bare list
    rows: List[Dict[str, Any]] = []
    for candidate in [data, data.get("data") if isinstance(data, dict) else None]:
        if isinstance(candidate, dict):
            for key in ("algos", "results", "items"):
                if isinstance(candidate.get(key), list):
                    rows = candidate[key]
                    break
        if rows:
            break
    if not rows and isinstance(data, list):
        rows = data
    if not rows:
        print("Couldn't find algo list in response. Raw body:")
        print(json.dumps(data, indent=2)[:2000])
        return 1
    print(f"{'algo_id':<14} {'name':<40} status/elo")
    print("-" * 72)
    for row in rows:
        rid = _first_matching(row, ["id", "algoId", "algo_id"])
        name = _first_matching(row, ["name", "algoName", "title"])
        status = _first_matching(row, ["status", "state"])
        elo = _first_matching(row, ["elo", "rating"])
        print(f"{str(rid):<14} {str(name)[:40]:<40} {status} / {elo}")
    return 0


def _fetch_matches(c: Client, algo_id: str, limit: Optional[int]) -> List[Dict[str, Any]]:
    """Try the primary list-matches endpoint, then each `_alt_list_matches_*` fallback."""
    candidates = [
        ENDPOINTS["list_matches"],
        ENDPOINTS["_alt_list_matches_1"],
        ENDPOINTS["_alt_list_matches_2"],
    ]
    last_err = None
    for tpl in candidates:
        path = tpl.format(algo_id=algo_id)
        if limit:
            sep = "&" if "?" in path else "?"
            path = f"{path}{sep}limit={limit}"
        try:
            data = c.get_json(path)
        except Exception as e:
            last_err = e
            continue
        # Extract list from common envelopes
        rows: List[Dict[str, Any]] = []
        if isinstance(data, list):
            rows = data
        elif isinstance(data, dict):
            for key in ("matches", "results", "items", "data"):
                if isinstance(data.get(key), list):
                    rows = data[key]
                    break
        if rows:
            return rows
        last_err = RuntimeError(f"no rows in {path} ({list(data.keys()) if isinstance(data, dict) else type(data).__name__})")
    raise RuntimeError(f"All list-matches endpoints failed. Last: {last_err}")


def cmd_list_matches(args) -> int:
    jar = load_cookie_jar(Path(args.cookie_file))
    c = Client(jar)
    rows = _fetch_matches(c, args.algo, args.limit)
    rows = [_normalize_match_row(r) for r in rows]
    if args.ranked_only:
        rows = [r for r in rows if r["ranked"]]
    print(f"{'match_id':<14} {'date':<22} {'ranked':<7} vs {'opp':<20} elo  result")
    print("-" * 90)
    for r in rows:
        opp = r["p2_name"] or "?"
        elo = r["p2_elo"] or ""
        res = r["result"] or ""
        print(f"{str(r['id']):<14} {str(r['date'])[:22]:<22} {str(r['ranked']):<7} vs {str(opp)[:20]:<20} {str(elo):<5} {res}")
    return 0


def _download_one(c: Client, match_id: str, dest: Path) -> bool:
    """Try primary + fallback replay endpoints."""
    for tpl_key in ("download_replay", "_alt_download_replay_1", "_alt_download_replay_2"):
        path = ENDPOINTS[tpl_key].format(match_id=match_id)
        r = c.get(path)
        if r.status_code != 200:
            continue
        # Replay files are newline-delimited JSON; reject HTML
        first = r.text[:80].lstrip()
        if first.startswith("<!DOCTYPE") or first.startswith("<html"):
            continue
        if not first.startswith("{"):
            continue
        dest.parent.mkdir(parents=True, exist_ok=True)
        with open(dest, "wb") as f:
            f.write(r.content)
        return True
    return False


def cmd_download(args) -> int:
    jar = load_cookie_jar(Path(args.cookie_file))
    c = Client(jar)
    rows = _fetch_matches(c, args.algo, args.limit)
    rows = [_normalize_match_row(r) for r in rows]
    if args.ranked_only:
        rows = [r for r in rows if r["ranked"]]
    if args.limit:
        rows = rows[: args.limit]
    if not rows:
        print("No matches to download.")
        return 0

    out_dir = Path(args.out)
    out_dir.mkdir(parents=True, exist_ok=True)
    print(f"Downloading {len(rows)} replays to {out_dir}…")
    ok = fail = 0
    for i, r in enumerate(rows, 1):
        if not r["id"]:
            print(f"[{i}] skip: no match id in row {r['_raw']}")
            fail += 1
            continue
        ts = _safe_name(r["date"] or datetime.utcnow().isoformat(), "unknown")
        opp = _safe_name(r["p2_name"] or "opp", "opp")
        elo = _safe_name(str(r["p2_elo"] or ""), "")
        res = _safe_name(str(r["result"] or ""), "res")
        name = f"algo{args.algo}_{ts}_vs_{opp}_{elo}_{res}_{r['id']}.replay"
        dest = out_dir / name
        if dest.exists() and not args.overwrite:
            print(f"[{i}/{len(rows)}] skip existing {dest.name}")
            continue
        ok_one = _download_one(c, str(r["id"]), dest)
        if ok_one:
            ok += 1
            print(f"[{i}/{len(rows)}] ✓ {dest.name}")
        else:
            fail += 1
            print(f"[{i}/{len(rows)}] ✗ match {r['id']} (all endpoints failed)")
        time.sleep(args.sleep)
    print(f"\nDone: {ok} downloaded, {fail} failed, total attempted {len(rows)}.")
    return 0 if fail == 0 else 3


# --------------------------------------------------------------------- main

def main() -> int:
    p = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    p.add_argument("--cookie-file", default=str(DEFAULT_COOKIE_FILE),
                   help=f"(default: {DEFAULT_COOKIE_FILE})")
    sub = p.add_subparsers(dest="cmd", required=True)

    pl = sub.add_parser("login", help="save an authenticated cookie")
    grp = pl.add_mutually_exclusive_group(required=True)
    grp.add_argument("--paste", action="store_true",
                     help="paste the cookie header manually")
    grp.add_argument("--browser", choices=["firefox", "chrome"],
                     help="open a browser, user logs in, we grab cookies")
    pl.set_defaults(func=cmd_login)

    pw = sub.add_parser("whoami", help="verify the cookie is live")
    pw.set_defaults(func=cmd_whoami)

    pd = sub.add_parser("discover", help="probe candidate API endpoints")
    pd.set_defaults(func=cmd_discover)

    ph = sub.add_parser("harvest", help="open a browser and log all XHR/fetch endpoints the site calls")
    ph.add_argument("--browser", choices=["chrome", "firefox"], default="chrome")
    ph.add_argument("--out", default="data/c1_api_harvest.json")
    ph.set_defaults(func=cmd_harvest)

    pa = sub.add_parser("list-algos", help="list your uploaded algos")
    pa.set_defaults(func=cmd_list_algos)

    pm = sub.add_parser("list-matches", help="list matches for one algo")
    pm.add_argument("--algo", required=True)
    pm.add_argument("--limit", type=int)
    pm.add_argument("--ranked-only", action="store_true")
    pm.set_defaults(func=cmd_list_matches)

    pdn = sub.add_parser("download", help="download replay files")
    pdn.add_argument("--algo", required=True)
    pdn.add_argument("--limit", type=int, default=30)
    pdn.add_argument("--ranked-only", action="store_true")
    pdn.add_argument("--out", default=str(DEFAULT_OUT_DIR))
    pdn.add_argument("--sleep", type=float, default=0.5,
                     help="seconds between downloads (default 0.5)")
    pdn.add_argument("--overwrite", action="store_true")
    pdn.set_defaults(func=cmd_download)

    args = p.parse_args()
    return args.func(args)


if __name__ == "__main__":
    sys.exit(main())
