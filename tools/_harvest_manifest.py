#!/usr/bin/env python3
"""One-off: build the manifest for the v13 replay harvest."""
import json, re
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent
OUT = REPO / "data" / "harvest_manifest.json"
OUT.parent.mkdir(parents=True, exist_ok=True)

# Data pulled 2026-04-23 from /api/game/algo/360023/matches via Chrome.
# Order preserved — downloads will fire in this order; do NOT reorder.
matches = [
    {"id":15303276,"opp":"oleh-v2","elo":1700,"turns":43,"won":True},
    {"id":15303255,"opp":"oleh-v2","elo":1763,"turns":82,"won":False},
    {"id":15303183,"opp":"diego_v2","elo":1900,"turns":69,"won":False},  # already in corpus
    {"id":15303134,"opp":"python-algo-jae-2","elo":1772,"turns":62,"won":False},
    {"id":15303107,"opp":"python-algo","elo":1619,"turns":81,"won":True},
    {"id":15303042,"opp":"banana","elo":1926,"turns":78,"won":False},
    {"id":15302827,"opp":"python-algo-baseline","elo":1775,"turns":64,"won":False},
    {"id":15302704,"opp":"python-algo1","elo":1758,"turns":100,"won":True},
    {"id":15302640,"opp":"python-algo","elo":1875,"turns":81,"won":False},
    {"id":15302638,"opp":"python-algo-turtle-new-submis","elo":1846,"turns":100,"won":False},
    {"id":15302627,"opp":"algo4","elo":1757,"turns":82,"won":True},
    {"id":15302625,"opp":"doublegap","elo":1736,"turns":77,"won":True},
    {"id":15302622,"opp":"python-algo3","elo":1629,"turns":100,"won":True},
    {"id":15302620,"opp":"python-algo-master","elo":1556,"turns":31,"won":True},
    {"id":15302616,"opp":"R3_Jukebox","elo":0,"turns":34,"won":True,"boss":True},
    {"id":15302621,"opp":"R4_Champion","elo":0,"turns":30,"won":True,"boss":True},
    {"id":15302614,"opp":"R2_Infiltrator","elo":0,"turns":26,"won":True,"boss":True},
    {"id":15302615,"opp":"R1_Sawtooth","elo":0,"turns":50,"won":True,"boss":True},
    {"id":15302618,"opp":"python-algo-turtle-v4","elo":1609,"turns":78,"won":True},
    {"id":15302611,"opp":"takedown1-algo","elo":1644,"turns":44,"won":False},
    {"id":15302609,"opp":"diego_v2","elo":1486,"turns":52,"won":True},
    {"id":15302606,"opp":"python-algo-v3","elo":1612,"turns":72,"won":False},
    {"id":15302602,"opp":"gooder-maybe","elo":1453,"turns":30,"won":True},
]

def safe(s: str) -> str:
    return re.sub(r"[^A-Za-z0-9._-]+", "_", s)[:30]

records = []
for m in matches:
    res = "win" if m["won"] else "loss"
    boss = "_boss" if m.get("boss") else ""
    tag = f"v13_360023_m{m['id']}_vs_{safe(m['opp'])}_{m['elo']}_{res}{boss}.replay"
    records.append({
        "match_id": m["id"],
        "target": tag,
        "expected_bytes": None,  # we don't know ahead of time
    })

OUT.write_text(json.dumps(records, indent=2))
print(f"wrote {len(records)} records to {OUT}")
# Also emit the JS-consumable minimal array
js_out = REPO / "data" / "harvest_matches.json"
js_out.write_text(json.dumps([{"id": r["match_id"], "name": r["target"]} for r in records]))
print(f"wrote {len(records)} records to {js_out}")
