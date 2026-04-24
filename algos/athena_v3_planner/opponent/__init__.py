"""Athena v3 — opponent modeling utilities.

This package owns lightweight, deterministic action-frame parsers that
build a per-opponent feature record across a match. They run inside
``on_action_frame`` and ``on_turn`` and avoid any sim_rs dependency so
they stay cheap and crash-proof.

Citadel quirk: action-frame JSON uses player_id ``1=self, 2=opponent``,
NOT the starter ``0=self, 1=opponent`` convention used by
``gamelib.GameState`` / ``GameUnit``. Every utility here is built for
the action-frame convention; callers pass ``self_player_id`` (default
1) explicitly.
"""

from .action_frame_utils import (
    BatchCountTracker,
    BreachLocationTracker,
    SpawnXHistogram,
    WallRemovalDetector,
)

__all__ = [
    "BatchCountTracker",
    "BreachLocationTracker",
    "SpawnXHistogram",
    "WallRemovalDetector",
]
