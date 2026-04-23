#!/usr/bin/env python3
"""
scrape_ranked_replays.py — download your uploaded algos' ranked replays from
terminal.c1games.com.

Verified against the live site 2026-04-23:
  PUBLIC (no cookie needed):
    GET /api/game/leaderboard               → list algos (global; excludes
                                              private algos like yours)
    GET /api/game/competition               → list competitions
    GET /api/game/algo/{id}/matches         → any algo's match history
  AUTH-GATED (need your cookie):
    GET /api/game/user                      → whoami (401 unauth)
    GET /api/game/algo/{id}                 → algo detail (403 unauth)
    (replay-download endpoint not yet known — use `harvest` to discover)
  405/unclear:
    GET /api/game/algo                      → 405 (exists but wrong verb)

Workflow:
  1. You tell us your algo's ID (it's in the URL on terminal.c1games.com
     after you click into one of your algos on My Algos).
  2. `list-matches --algo <id>` works RIGHT NOW, no login required — your
     own algo's match history is public.
  3. For `download`, cookies ARE required. `login` first, then `download`.
     If the current guesses for the replay-download path return 404/403,
     run `harvest --browser chrome`, click into a match and hit the replay
     viewer once; we log the real URL. Paste it into ENDPOINTS['download_replay']
     and re-run download.

Subcommands:
  login [--paste | --browser chrome|firefox]
      One-time: capture the logged-in session cookie.
  whoami
      GET /api/game/user — verifies the cookie.
  discover
      Probe the ENDPOINTS dict; flag drift.
  harvest --browser chrome [--out data/c1_api_harvest.json]
      Open Chrome with your cookies; click around; we log every XHR/fetch.
  find-algos [--contains SUBSTR | --user DISPLAY_NAME | --all]
      Leaderboard-only (public). Useful for looking up *opponent* algo IDs,
      NOT your own algos (private algos don't appear there).
  list-matches --algo ALGO_ID [--ranked-only] [--limit N]
      List matches. Public endpoint — works without cookies.
  download --algo ALGO_ID [--ranked-only] [--limit N] [--out DIR]
      Download each match's replay. Requires cookies.
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
# Endpoints verified against the live site on 2026-04-23 (see docstring for details).
# Paths marked "public" respond without any cookie; "auth" returns 401/403 unauth.
ENDPOINTS = {
    "whoami":        "/api/game/user",                       # auth; public returns 401 JSON
    "leaderboard":   "/api/game/leaderboard",                # public
    "algo_detail":   "/api/game/algo/{algo_id}",             # auth (private fields)
    "list_matches":  "/api/game/algo/{algo_id}/matches",     # public! works for any algo id
    # Replay-download endpoint needs an authenticated session. We confirmed
    # /api/game/match exists and returns 403 unauthenticated. We try these
    # variants in order until one yields a JSON/newline-delimited replay.
    "download_replay":  "/api/game/match/{match_id}",
    "_alt_replay_post": "/api/game/match",                   # POST with {"id": ...}
    "_alt_replay_1":    "/api/game/match/{match_id}/replay",
    "_alt_replay_2":    "/api/game/replay/{match_id}",
    "_alt_replay_3":    "/api/game/algo/{algo_id}/matches/{match_id}",
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
        if r.status_code == 401:
            raise AuthError(f"GET {path} → 401 (session expired or missing). "
                            f"Run `login` to refresh.")
        if r.status_code == 403:
            raise AuthError(f"GET {path} → 403 (forbidden for this user). "
                            f"Body: {r.text[:120]!r}")
        if r.status_code != 200:
            raise ApiError(f"GET {path} → {r.status_code} {ct} body[:200]={r.text[:200]!r}")
        if "application/json" not in ct:
            raise ApiError(f"GET {path} → 200 but not JSON; body[:200]={r.text[:200]!r}. "
                           "Endpoint path may have changed — run `discover`.")
        return r.json()


class AuthError(RuntimeError):
    pass


class ApiError(RuntimeError):
    pass


# ------------------------------------------------------------------ helpers

def _first_matching(d: Any, keys: List[str], default=None):
    if not isinstance(d, dict):
        return default
    for k in keys:
        if k in d:
            return d[k]
    return default


def _normalize_match_row(row: Dict[str, Any], focus_algo_id: Optional[int] = None) -> Dict[str, Any]:
    """Turn a /matches row into {id, ranked, date, won, opp_name, opp_elo, crashed, replay}.
    Verified schema 2026-04-23: winning_algo/losing_algo dicts, subComp (None=ranked), turns, crashed, replay."""
    rid = row.get("id")
    sub = row.get("subComp")
    # Ranked = not part of a tournament/subcompetition.
    ranked = sub is None
    date = row.get("date")
    wa = row.get("winning_algo") or {}
    la = row.get("losing_algo") or {}
    # Who won from focus_algo's perspective?
    won = None
    if focus_algo_id is not None:
        if isinstance(wa, dict) and wa.get("id") == focus_algo_id:
            won = True
        elif isinstance(la, dict) and la.get("id") == focus_algo_id:
            won = False
    # Opponent = the algo that isn't us.
    if focus_algo_id is not None and isinstance(wa, dict) and wa.get("id") == focus_algo_id:
        opp = la
    else:
        opp = wa if (focus_algo_id is not None and isinstance(la, dict) and la.get("id") == focus_algo_id) else la
    opp_name = (opp or {}).get("name") if isinstance(opp, dict) else None
    opp_elo  = (opp or {}).get("rating") if isinstance(opp, dict) else None
    return {
        "id": rid, "ranked": bool(ranked), "date": date, "won": won,
        "opp_name": opp_name, "opp_elo": opp_elo,
        "turns": row.get("turns"), "crashed": bool(row.get("crashed")),
        "has_replay": bool(row.get("replay")),
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
    try:
        data = c.get_json(ENDPOINTS["whoami"])
    except AuthError as e:
        print(f"Not authenticated: {e}")
        return 2
    except ApiError as e:
        print(f"API error: {e}")
        return 3
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
    """List uploaded algos. The C1 site does NOT expose a `my-algos` JSON
    endpoint publicly; we approximate by pulling the global leaderboard
    (public, no auth required) and filtering by --user or --contains.
    With --all we dump the full leaderboard."""
    jar_path = Path(args.cookie_file)
    jar = {} if args.no_cookie else (load_cookie_jar(jar_path) if jar_path.exists() else {})
    c = Client(jar)
    try:
        data = c.get_json(ENDPOINTS["leaderboard"])
    except (AuthError, ApiError) as e:
        print(f"leaderboard fetch failed: {e}")
        return 3
    algos = data.get("data", {}).get("algos", []) if isinstance(data, dict) else []
    if not algos:
        print("leaderboard returned no algos; raw[:400]:", json.dumps(data)[:400])
        return 3

    user = (args.user or "").lower().strip()
    contains = (args.contains or "").lower().strip()
    filtered = []
    for a in algos:
        u = ((a.get("user") or {}).get("displayName") if isinstance(a.get("user"), dict) else a.get("user")) or ""
        name = a.get("name") or ""
        if user and u.lower() != user:
            continue
        if contains and contains not in name.lower():
            continue
        filtered.append(a)
    if not args.all and not user and not contains:
        print("Pass one of: --user DISPLAY_NAME, --contains SUBSTRING, or --all")
        print(f"(leaderboard has {len(algos)} algos total)")
        return 1
    if args.all:
        filtered = algos
    print(f"{'algo_id':<10} {'rating':<7} {'lang':<7} {'user':<24} {'name':<30} created")
    print("-" * 100)
    for a in filtered:
        u = (a.get("user") or {}).get("displayName") if isinstance(a.get("user"), dict) else (a.get("user") or "?")
        print(f"{str(a.get('id')):<10} {str(a.get('rating',''))[:6]:<7} "
              f"{str(a.get('language',''))[:6]:<7} {str(u)[:24]:<24} "
              f"{str(a.get('name',''))[:30]:<30} {str(a.get('createdAt',''))[:10]}")
    print(f"\n{len(filtered)} algo(s) shown (of {len(algos)} total).")
    return 0


def _fetch_matches(c: Client, algo_id: str, limit: Optional[int]) -> List[Dict[str, Any]]:
    """GET /api/game/algo/{id}/matches. Public — no auth required."""
    path = ENDPOINTS["list_matches"].format(algo_id=algo_id)
    if limit:
        sep = "&" if "?" in path else "?"
        path = f"{path}{sep}limit={limit}"
    data = c.get_json(path)
    inner = data.get("data", data) if isinstance(data, dict) else data
    rows = inner.get("matches", []) if isinstance(inner, dict) else (inner if isinstance(inner, list) else [])
    return rows


def cmd_list_matches(args) -> int:
    jar_path = Path(args.cookie_file)
    jar = {} if args.no_cookie else (load_cookie_jar(jar_path) if jar_path.exists() else {})
    c = Client(jar)
    try:
        raw_rows = _fetch_matches(c, args.algo, args.limit)
    except (AuthError, ApiError) as e:
        print(f"list-matches failed: {e}")
        return 3
    try:
        algo_int = int(args.algo)
    except ValueError:
        algo_int = None
    rows = [_normalize_match_row(r, algo_int) for r in raw_rows]
    if args.ranked_only:
        rows = [r for r in rows if r["ranked"]]
    if not rows:
        print("No matches found (after ranked-only filter).")
        return 0
    print(f"{'match_id':<10} {'date':<22} {'ranked':<7} {'won':<5} vs {'opp':<18} elo  turns  crashed  replay")
    print("-" * 105)
    for r in rows:
        print(f"{str(r['id']):<10} {str(r['date'])[:22]:<22} {str(r['ranked']):<7} "
              f"{str(r['won']):<5} vs {str(r['opp_name'] or '?')[:18]:<18} "
              f"{str(r['opp_elo'] or ''):<5} {str(r['turns']):<6} "
              f"{str(r['crashed']):<8} {str(r['has_replay'])}")
    print(f"\n{len(rows)} row(s).")
    return 0


def _looks_like_replay(text: str) -> bool:
    """Replay files are newline-delimited JSON; first line must be an object."""
    head = text[:200].lstrip()
    if not head:
        return False
    if head.startswith(("<!DOCTYPE", "<html", "<?xml")):
        return False
    return head.startswith("{")


def _download_one(c: Client, algo_id: str, match_id: str, dest: Path) -> tuple[bool, str]:
    """Try each candidate replay endpoint. Returns (ok, diagnostic)."""
    last = "no candidates tried"
    # GET variants
    for key in ("download_replay", "_alt_replay_1", "_alt_replay_2", "_alt_replay_3"):
        path = ENDPOINTS[key].format(match_id=match_id, algo_id=algo_id)
        r = c.get(path)
        if r.status_code == 200 and _looks_like_replay(r.text):
            dest.parent.mkdir(parents=True, exist_ok=True)
            with open(dest, "wb") as f:
                f.write(r.content)
            return True, f"ok via {key}={path}"
        ct = r.headers.get("Content-Type", "?").split(";")[0]
        last = f"{key}: {r.status_code} {ct}"
    # POST variants
    path = ENDPOINTS["_alt_replay_post"]
    for body in ({"id": int(match_id)}, {"match_id": int(match_id)}, {"matchId": int(match_id)}):
        r = c.s.post(BASE + path, json=body, timeout=30)
        if r.status_code == 200 and _looks_like_replay(r.text):
            dest.parent.mkdir(parents=True, exist_ok=True)
            with open(dest, "wb") as f:
                f.write(r.content)
            return True, f"ok via POST {path} body={body}"
        ct = r.headers.get("Content-Type", "?").split(";")[0]
        last = f"POST {path} body={body}: {r.status_code} {ct}"
    return False, last


def cmd_download(args) -> int:
    jar_path = Path(args.cookie_file)
    if not jar_path.exists():
        print(f"No cookies at {jar_path}. Replay downloads require auth — run `login` first.")
        return 2
    jar = load_cookie_jar(jar_path)
    c = Client(jar)
    try:
        raw_rows = _fetch_matches(c, args.algo, args.limit)
    except (AuthError, ApiError) as e:
        print(f"list-matches failed: {e}")
        return 3
    try:
        algo_int = int(args.algo)
    except ValueError:
        algo_int = None
    rows = [_normalize_match_row(r, algo_int) for r in raw_rows]
    if args.ranked_only:
        rows = [r for r in rows if r["ranked"]]
    rows = [r for r in rows if r["has_replay"]]
    if args.limit:
        rows = rows[: args.limit]
    if not rows:
        print("No matches to download after filters.")
        return 0

    out_dir = Path(args.out)
    out_dir.mkdir(parents=True, exist_ok=True)
    print(f"Downloading {len(rows)} replays to {out_dir}…")
    ok = fail = 0
    first_failure_diag = None
    for i, r in enumerate(rows, 1):
        if not r["id"]:
            print(f"[{i}] skip: no match id in row {r['_raw']}")
            fail += 1
            continue
        ts = _safe_name((r["date"] or datetime.utcnow().isoformat()).replace(":", "-"), "unknown")
        opp = _safe_name(r["opp_name"] or "opp", "opp")
        elo = _safe_name(str(r["opp_elo"] or ""), "")
        res = "win" if r["won"] else ("loss" if r["won"] is False else "r")
        name = f"algo{args.algo}_{ts}_vs_{opp}_{elo}_{res}_m{r['id']}.replay"
        dest = out_dir / name
        if dest.exists() and not args.overwrite:
            print(f"[{i}/{len(rows)}] skip existing {dest.name}")
            continue
        ok_one, diag = _download_one(c, args.algo, str(r["id"]), dest)
        if ok_one:
            ok += 1
            print(f"[{i}/{len(rows)}] ✓ {dest.name}  [{diag}]")
        else:
            fail += 1
            if first_failure_diag is None:
                first_failure_diag = diag
            print(f"[{i}/{len(rows)}] ✗ match {r['id']}  [last: {diag}]")
            if fail >= 3 and ok == 0:
                print("Aborting: first 3 downloads all failed. Likely endpoint mismatch.")
                print("Run `harvest --browser chrome` to see the real replay URL, then")
                print("patch ENDPOINTS['download_replay'] in this file.")
                break
        time.sleep(args.sleep)
    print(f"\nDone: {ok} downloaded, {fail} failed, attempted {ok+fail}.")
    if first_failure_diag:
        print(f"First failure diag: {first_failure_diag}")
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

    pa = sub.add_parser("find-algos", help="search the public leaderboard (opponent lookup; does NOT show your private algos)")
    pa.add_argument("--user", help="filter by displayName (exact, case-insensitive)")
    pa.add_argument("--contains", help="filter by algo name substring")
    pa.add_argument("--all", action="store_true", help="show the whole leaderboard")
    pa.add_argument("--no-cookie", action="store_true", help="don't require a saved cookie (leaderboard is public)")
    pa.set_defaults(func=cmd_list_algos)

    pm = sub.add_parser("list-matches", help="list matches for one algo (public — no auth required)")
    pm.add_argument("--algo", required=True)
    pm.add_argument("--limit", type=int)
    pm.add_argument("--ranked-only", action="store_true")
    pm.add_argument("--no-cookie", action="store_true")
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
