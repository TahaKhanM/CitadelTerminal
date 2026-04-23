"""Opponent archetype classifier.

After turn 3 we have enough signal (opponent's opening structures + any
mobile spawns observed via on_action_frame) to classify the opponent into
one of ~5 archetypes. Each archetype gets a pre-tuned counter in
v15_adaptive's `on_turn`.

Features extracted on demand from a live GameState + a list of observed
enemy mobile-spawn events:
    turret_count     — enemy DF (Turret) count
    wall_count       — enemy FF (Wall) count
    support_count    — enemy EF (Support) count
    front_density    — fraction of y=14,15 tiles occupied by enemy structures
    left_bias, right_bias — asymmetry of enemy structures across x=13.5
    mobile_types     — multiset of unit types the opponent has spawned
    last_mobile_type — the last observed opponent mobile spawn type

Archetypes:
    "scout_rush"       — light defense + mass Scouts alternating corners
    "demo_line"        — wall line + Demolisher spawns
    "turret_castle"    — dense wall + turret grid, offense late
    "support_caravan"  — places Supports, typical shielded-scout bot
    "balanced"         — v13-like: dense center turrets + mid walls

The tree is hand-crafted; 12 branches, easy to evolve.
"""

from __future__ import annotations

from dataclasses import dataclass, field
from typing import List, Optional


SCOUT_RUSH = "scout_rush"
DEMO_LINE = "demo_line"
TURRET_CASTLE = "turret_castle"
SUPPORT_CARAVAN = "support_caravan"
BALANCED = "balanced"


@dataclass
class EnemyFeatures:
    turret_count: int = 0
    wall_count: int = 0
    support_count: int = 0
    front_density: float = 0.0
    left_bias: int = 0  # enemy structure count on left half
    right_bias: int = 0
    demolisher_seen: int = 0
    scout_seen: int = 0
    interceptor_seen: int = 0
    last_mobile_type: Optional[str] = None
    # Spawn-edge histogram (x values)
    spawn_left_count: int = 0
    spawn_right_count: int = 0


def extract_features(gs, WALL, SUPPORT, TURRET, SCOUT, DEMOLISHER, INTERCEPTOR,
                     mobile_events: List[tuple]) -> EnemyFeatures:
    """Compute features from live GameState + accumulated opponent spawn events.

    `mobile_events` is a list of (x, y, unit_type) tuples — filled by the
    algo's on_action_frame handler whenever a player-2 spawn event fires.
    """
    f = EnemyFeatures()
    gm = gs.game_map
    # Scan enemy (player_index == 1) structures
    for loc in gm:
        x, y = loc
        for u in gm[x, y]:
            if u.player_index != 1:
                continue
            if not u.stationary:
                continue
            if u.unit_type == TURRET:
                f.turret_count += 1
            elif u.unit_type == WALL:
                f.wall_count += 1
            elif u.unit_type == SUPPORT:
                f.support_count += 1
            if x < 14:
                f.left_bias += 1
            else:
                f.right_bias += 1
            if y in (14, 15):
                f.front_density += 1
    # Normalize front_density by the ~30 tiles in y=14,15 that are in bounds
    f.front_density = f.front_density / 30.0
    # Process mobile_events
    for (mx, my, mt) in mobile_events:
        if mt == SCOUT:
            f.scout_seen += 1
        elif mt == DEMOLISHER:
            f.demolisher_seen += 1
        elif mt == INTERCEPTOR:
            f.interceptor_seen += 1
        if mx < 14:
            f.spawn_right_count += 1  # enemy spawn at low x targets BOTTOM_RIGHT (our right)
        else:
            f.spawn_left_count += 1
        f.last_mobile_type = mt
    return f


def classify(f: EnemyFeatures) -> str:
    """Decision tree over features. Default → balanced."""
    # Signal 1: do they place Supports? Require ≥3 so a defensive algo that
    # happens to place 1-2 Supports doesn't trip this counter.
    if f.support_count >= 3:
        return SUPPORT_CARAVAN
    # Signal 2: DEMO_LINE — demolishers observed + walls. Check before
    # TURRET_CASTLE because demo_line has walls too.
    if f.demolisher_seen >= 2 and f.wall_count >= 10:
        return DEMO_LINE
    # Signal 3: SCOUT_RUSH — lots of scouts + sparse walls.
    if f.scout_seen >= 4 and f.wall_count <= 10:
        return SCOUT_RUSH
    # Signal 4: TURRET_CASTLE — heavy walls/turrets AND passive (no mobiles
    # spawned yet). An algo that has dense defense PLUS active offense is
    # a "balanced" v13-like bot, not a castle — over-hoarding against it
    # hurts us. opp_turret_castle is silent until T15.
    if f.turret_count >= 6 and f.wall_count >= 14 and f.scout_seen == 0 and f.demolisher_seen == 0:
        return TURRET_CASTLE
    return BALANCED


__all__ = [
    "EnemyFeatures",
    "extract_features",
    "classify",
    "SCOUT_RUSH",
    "DEMO_LINE",
    "TURRET_CASTLE",
    "SUPPORT_CARAVAN",
    "BALANCED",
]
