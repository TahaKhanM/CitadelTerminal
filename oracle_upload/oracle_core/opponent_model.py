"""Empirical opponent action prediction model.

Trains an unconditional + conditional empirical distribution over
opponent ActionPlans by walking ranked replays. At runtime, samples K
plausible opponent action plans for the current state.

NOT a learned model — just empirical distributions over observed
behavior. We don't have time to validate ML in 43 hours.

Key idea: the opponent's action depends on observable features of the
state. We bucket states by features and look up what opponents have
done in similar buckets. If the current bucket is sparse, we widen the
bucket along one axis at a time; if it's still empty, we fall back to
a hand-coded prior over common archetypes (Scout rush, demolisher line,
no-op).

Bucket key (4 axes):
  turn_band   : 0 (turns 0-4), 1 (5-9), 2 (10-19), 3 (20-39), 4 (40+)
  opp_mp_band : 0 (<5), 1 (5-7), 2 (8-12), 3 (13-19), 4 (20+)
  our_mp_band : same as opp_mp_band
  breach_band : 0 (no recent breaches against us), 1 (any recent)

Each bucket holds a list of ActionPlan instances seen in that bucket.
sample_actions returns up to k DISTINCT plans (de-duped by
ActionPlan.signature()). If the exact bucket has < k plans, we widen
breach -> our_mp -> opp_mp -> turn until we have enough, then pad with
the prior archetypes.

Public API mirrors the spec:
    OpponentModel(corpus_dir: str = None, opponent_id: int = None)
    .sample_actions(game_state, k: int = 8) -> List[ActionPlan]
    .update_observation(turn_num: int, observed_opp_action: dict) -> None
"""
from __future__ import annotations

import json
import random
from collections import defaultdict
from pathlib import Path
from typing import Any, Dict, List, Optional, Sequence, Tuple

from .enumerator import ActionPlan

# ---------------------------------------------------------------------------
# Type-index <-> shorthand mappings (matches Citadel live-server shorthands;
# memory note: live runtime still uses pre-rename codes FF/EF/DF/PI/EI/SI).
# ---------------------------------------------------------------------------

_IDX_TO_SHORTHAND: Dict[int, str] = {
    0: "FF",   # Wall
    1: "EF",   # Support
    2: "DF",   # Turret
    3: "PI",   # Scout
    4: "EI",   # Demolisher
    5: "SI",   # Interceptor
}
_SHORTHAND_TO_IDX: Dict[str, int] = {v: k for k, v in _IDX_TO_SHORTHAND.items()}
_MOBILE_TYPE_IDXS = {3, 4, 5}
_STRUCTURE_TYPE_IDXS = {0, 1, 2}

# Mobile costs (MP) — used to compute mp_cost on reconstructed plans and the
# fallback prior. Cross-checked against citadel_config_snapshot.json
# (PI=1, EI=3, SI=1 — same as base game).
_MP_COST_BY_IDX: Dict[int, float] = {3: 1.0, 4: 3.0, 5: 1.0}


# ---------------------------------------------------------------------------
# Bucketing
# ---------------------------------------------------------------------------

def _turn_band(turn: int) -> int:
    if turn < 5:
        return 0
    if turn < 10:
        return 1
    if turn < 20:
        return 2
    if turn < 40:
        return 3
    return 4


def _mp_band(mp: float) -> int:
    if mp < 5:
        return 0
    if mp < 8:
        return 1
    if mp < 13:
        return 2
    if mp < 20:
        return 3
    return 4


def _breach_band(recent_breaches: int) -> int:
    return 0 if recent_breaches <= 0 else 1


# ---------------------------------------------------------------------------
# OpponentModel
# ---------------------------------------------------------------------------

class OpponentModel:
    """Empirical opponent ActionPlan distribution from ranked replays."""

    DEFAULT_CORPUS_DIRS: Tuple[str, ...] = (
        "Ranked Replays",
        "replays/ranked",
        "replays",
    )

    # Fallback prior: common opponent archetypes if the bucket is empty.
    # Tiles are chosen for the OPPONENT's edge — the opponent is the top
    # player (player 2 in raw replay JSON) in every replay we ingest, so
    # spawn-y >= 14. This prior is symmetric (left/right corners + ramps).
    PRIOR_ARCHETYPES: Tuple[Tuple[str, List[Tuple[str, List[int], int]], float, float], ...] = (
        # (description, mobile_actions [(shorthand, [x,y], count)], sp_cost, mp_cost)
        ("scout_rush_left_corner_8",  [("PI", [13, 27], 8)], 0.0, 8.0),
        ("scout_rush_right_corner_8", [("PI", [14, 27], 8)], 0.0, 8.0),
        ("scout_rush_left_edge_8",    [("PI", [3, 16], 8)], 0.0, 8.0),
        ("scout_rush_right_edge_8",   [("PI", [24, 16], 8)], 0.0, 8.0),
        ("demo_line_3",               [("EI", [13, 27], 3)], 0.0, 9.0),
        ("demo_line_2",               [("EI", [13, 27], 2)], 0.0, 6.0),
        ("interceptor_screen_left",   [("SI", [3, 16], 3)], 0.0, 3.0),
        ("interceptor_screen_right",  [("SI", [24, 16], 3)], 0.0, 3.0),
        ("no_op",                     [], 0.0, 0.0),
    )

    # Cache file name (lives next to algo data — e.g. algos/oracle/data/)
    CACHE_FILENAME: str = "opp_model_cache.pkl"

    def __init__(
        self,
        corpus_dir: Optional[str] = None,
        opponent_id: Optional[int] = None,
        cache_path: Optional[str] = None,
        load_cache: bool = True,
        max_load_seconds: Optional[float] = None,
    ) -> None:
        # Bucket -> list[ActionPlan]
        self._observations: Dict[Tuple[int, int, int, int], List[ActionPlan]] = (
            defaultdict(list)
        )
        # Union pool for fallback when even the widened bucket is empty.
        self._all_plans: List[ActionPlan] = []
        # Tracking metadata for diagnostics.
        self._n_replays_loaded: int = 0
        self._n_observations: int = 0
        self._opponent_id: Optional[int] = opponent_id
        # Per-game posterior (populated by update_observation; v1 unused).
        self._game_posterior: List[ActionPlan] = []

        # 1. Try cache first (fast — ~50ms vs ~150s for full corpus parse)
        cache_loaded = False
        if load_cache:
            cache_loaded = self._try_load_cache(cache_path)

        if cache_loaded:
            return

        # 2. Fall back to corpus parsing (slow — only at build time, not runtime)
        import time as _time
        start = _time.time()
        if corpus_dir is not None:
            self._load_corpus(corpus_dir, opponent_id, deadline=
                              start + max_load_seconds if max_load_seconds else None)
        else:
            project_root = self._find_project_root()
            for d in self.DEFAULT_CORPUS_DIRS:
                p = (project_root / d) if project_root else Path(d)
                if p.exists() and p.is_dir():
                    deadline = (start + max_load_seconds) if max_load_seconds else None
                    self._load_corpus(str(p), opponent_id, deadline=deadline)
                    if max_load_seconds and _time.time() > start + max_load_seconds:
                        break

    # --------------------------------------------------------------- cache I/O

    def _resolve_cache_path(self, override: Optional[str]) -> Optional[Path]:
        if override:
            return Path(override)
        # Default: algos/oracle/data/opp_model_cache.pkl
        # Walk from this file's location: oracle_core/.. -> oracle/ -> oracle/data/
        here = Path(__file__).resolve()
        for parent in here.parents:
            if (parent / "data").is_dir():
                return parent / "data" / self.CACHE_FILENAME
        return None

    def _try_load_cache(self, override: Optional[str]) -> bool:
        """Attempt to load precomputed cache. Returns True on success."""
        import pickle
        cache_path = self._resolve_cache_path(override)
        if not cache_path or not cache_path.is_file():
            return False
        try:
            with open(cache_path, "rb") as f:
                blob = pickle.load(f)
            # Schema: {"observations": {bucket: [ActionPlan]}, "all_plans": [...], "n_replays": int, "n_obs": int}
            self._observations = defaultdict(list, blob.get("observations", {}))
            self._all_plans = list(blob.get("all_plans", []))
            self._n_replays_loaded = int(blob.get("n_replays", 0))
            self._n_observations = int(blob.get("n_obs", 0))
            return True
        except Exception:
            return False

    def save_cache(self, override: Optional[str] = None) -> Optional[Path]:
        """Persist current observations to a cache file for fast future loads."""
        import pickle
        cache_path = self._resolve_cache_path(override)
        if not cache_path:
            return None
        cache_path.parent.mkdir(parents=True, exist_ok=True)
        blob = {
            "observations": dict(self._observations),
            "all_plans": self._all_plans,
            "n_replays": self._n_replays_loaded,
            "n_obs": self._n_observations,
        }
        with open(cache_path, "wb") as f:
            pickle.dump(blob, f, protocol=pickle.HIGHEST_PROTOCOL)
        return cache_path

    # ----------------------------------------------------------- corpus load

    @staticmethod
    def _find_project_root() -> Optional[Path]:
        """Walk upward from this file looking for the CitadelTerminal repo root."""
        here = Path(__file__).resolve()
        for parent in here.parents:
            if (parent / "CLAUDE.md").exists() and (parent / "algos").exists():
                return parent
        return None

    def _load_corpus(self, corpus_dir: str, opponent_id: Optional[int],
                     deadline: Optional[float] = None) -> None:
        import time as _time
        root = Path(corpus_dir)
        if not root.exists():
            return
        # Recursively find all .replay files.
        for replay_path in root.rglob("*.replay"):
            if deadline is not None and _time.time() > deadline:
                break
            try:
                self._ingest_replay(replay_path, opponent_id)
                self._n_replays_loaded += 1
            except Exception:
                # Defensive: a malformed replay should never block the model.
                continue

    def _ingest_replay(
        self, replay_path: Path, opponent_id: Optional[int],
    ) -> None:
        """Parse one .replay file and extract per-turn opponent ActionPlans.

        Convention: in our archive ``Ranked Replays/{algo}/*.replay`` the
        file represents a match where OUR algo was player 1 (we always
        spawn at the bottom). The opponent is therefore raw-JSON player 2.
        We use that fixed convention rather than parsing filenames.

        For each turn we:
          1. Read the deploy frame to compute (turn, our_mp, opp_mp).
          2. Read the first action frame to extract the opponent's spawn
             events for this turn — these are emitted before any mobile
             unit moves, so they reflect the opponent's deploy decisions
             1:1.
          3. Walk back over previous turns' breach events to compute the
             "recent breach" bucket axis (any p2-mobile breach in the
             prior 3 turns).
          4. Reconstruct an ActionPlan, register it under its bucket key,
             and append it to _all_plans.
        """
        deploy_frames: Dict[int, dict] = {}
        action_frames_first: Dict[int, dict] = {}
        all_action_frames: Dict[int, List[dict]] = defaultdict(list)
        config_seen = False

        with replay_path.open("r", encoding="utf-8", errors="replace") as f:
            for raw in f:
                s = raw.strip()
                if not s.startswith("{"):
                    continue
                try:
                    d = json.loads(s)
                except json.JSONDecodeError:
                    continue
                if "unitInformation" in d and not config_seen:
                    config_seen = True
                    continue
                ti = d.get("turnInfo")
                if not isinstance(ti, list) or len(ti) < 3:
                    continue
                phase = int(ti[0])
                turn = int(ti[1])
                frame_idx = int(ti[2])
                if phase == 0 and frame_idx == -1:
                    deploy_frames[turn] = d
                elif phase == 1:
                    all_action_frames[turn].append(d)
                    if frame_idx == 0 and turn not in action_frames_first:
                        action_frames_first[turn] = d

        # Pre-compute breach counts per turn (against player 1 = us). A
        # breach event has shape [[x,y], damage, type_idx, uid, attacker_pid].
        # When attacker_pid == 2, the opponent breached us this turn.
        breaches_by_turn: Dict[int, int] = defaultdict(int)
        for t, frames in all_action_frames.items():
            count = 0
            for f in frames:
                evs = (f.get("events") or {}).get("breach") or []
                for ev in evs:
                    try:
                        attacker_pid = int(ev[4])
                    except (IndexError, TypeError, ValueError):
                        continue
                    if attacker_pid == 2:
                        count += 1
            breaches_by_turn[t] = count

        for turn in sorted(deploy_frames.keys()):
            df = deploy_frames[turn]
            af = action_frames_first.get(turn)
            if af is None:
                continue
            # Resource snapshot at the START of this turn (deploy phase).
            try:
                p1 = df.get("p1Stats") or [40, 8, 1, 0]
                p2 = df.get("p2Stats") or [40, 8, 1, 0]
                our_mp = float(p1[2])
                opp_mp = float(p2[2])
            except (TypeError, ValueError, IndexError):
                continue

            # Recent breaches against us = sum over [turn-3, turn-1].
            recent_breaches = sum(
                breaches_by_turn.get(t, 0) for t in range(max(0, turn - 3), turn)
            )

            plan = self._extract_opp_action_plan(af, opponent_pid=2)
            if plan is None:
                continue

            key = (
                _turn_band(turn),
                _mp_band(opp_mp),
                _mp_band(our_mp),
                _breach_band(recent_breaches),
            )
            self._observations[key].append(plan)
            self._all_plans.append(plan)
            self._n_observations += 1

    @staticmethod
    def _extract_opp_action_plan(
        action_frame: dict, opponent_pid: int = 2,
    ) -> Optional[ActionPlan]:
        """Reconstruct an ActionPlan from the opponent's spawn events in
        the first action frame.

        Spawn event shape: ``[[x, y], type_idx, unit_id, player_id]``.
        - type_idx in {0, 1, 2} -> structure (Wall/Support/Turret) spawn.
        - type_idx in {3, 4, 5} -> mobile spawn (Scout/Demolisher/Interceptor).
        - type_idx == 6        -> "remove" pseudo-event (ignored here).
        - type_idx == 7        -> upgrade pseudo-event (ignored — cost
                                  is structural; sim picks it up via the
                                  upgraded flag on existing structures).

        Same-tile mobile spawns are coalesced into a single MobileAction
        with count > 1 — this preserves the engine's actual deploy
        semantics (8 Scouts at one tile = one wave).
        """
        events_root = (action_frame or {}).get("events")
        if not isinstance(events_root, dict):
            events_root = {}
        events = events_root.get("spawn") or []
        if not isinstance(events, list):
            events = []
        if not events:
            return ActionPlan(
                structure_actions=[],
                mobile_actions=[],
                description="no_op",
                sp_cost=0.0,
                mp_cost=0.0,
            )

        # Schema (matches enumerator.StructureAction):
        # (action_type, unit_shorthand, [x, y]) where action_type ∈ {"spawn",
        # "upgrade", "remove"}.
        struct_actions: List[Tuple[str, str, List[int]]] = []
        # Coalesce mobile spawns by (type, x, y).
        mobile_counts: Dict[Tuple[int, int, int], int] = defaultdict(int)

        for ev in events:
            try:
                xy = ev[0]
                type_idx = int(ev[1])
                pid = int(ev[3])
                x = int(xy[0])
                y = int(xy[1])
            except (IndexError, TypeError, ValueError):
                continue
            if pid != opponent_pid:
                continue
            if type_idx in _MOBILE_TYPE_IDXS:
                mobile_counts[(type_idx, x, y)] += 1
            elif type_idx in _STRUCTURE_TYPE_IDXS:
                shorthand = _IDX_TO_SHORTHAND[type_idx]
                struct_actions.append(("spawn", shorthand, [x, y]))
            # 6 (remove) / 7 (upgrade) ignored on the offense-prediction path.

        # Materialize mobile_actions in deterministic order (sorted by count
        # desc so the dominant wave shows up first in the description).
        mobile_actions: List[Tuple[str, List[int], int]] = []
        sorted_keys = sorted(
            mobile_counts.items(),
            key=lambda kv: (-kv[1], kv[0]),
        )
        mp_cost = 0.0
        for (type_idx, x, y), cnt in sorted_keys:
            shorthand = _IDX_TO_SHORTHAND[type_idx]
            mobile_actions.append((shorthand, [x, y], int(cnt)))
            mp_cost += _MP_COST_BY_IDX.get(type_idx, 0.0) * cnt

        if not mobile_actions and not struct_actions:
            description = "no_op"
        else:
            # Compact label: top mobile wave + #structures.
            label_parts: List[str] = []
            if mobile_actions:
                top = mobile_actions[0]
                label_parts.append(f"{top[0]}x{top[2]}@{top[1][0]},{top[1][1]}")
            if struct_actions:
                label_parts.append(f"+{len(struct_actions)}struct")
            description = "obs:" + "|".join(label_parts)

        return ActionPlan(
            structure_actions=list(struct_actions),
            mobile_actions=mobile_actions,
            description=description,
            sp_cost=0.0,
            mp_cost=float(mp_cost),
        )

    # ----------------------------------------------------------- runtime API

    def sample_actions(self, game_state: Any, k: int = 8) -> List[ActionPlan]:
        """Return up to k distinct plausible opponent ActionPlans.

        Algorithm:
          1. Compute the bucket key from `game_state` (turn, opp_mp,
             our_mp, recent breaches).
          2. Look up the exact bucket. If we have >= k distinct plans,
             sample k by signature.
          3. Otherwise widen the bucket: drop breach axis, then our_mp,
             then opp_mp, then turn. At each step, accumulate distinct
             plans until we hit k.
          4. If still short, top up from the global ``_all_plans`` pool.
          5. If the corpus is empty, fall back to ``PRIOR_ARCHETYPES``.

        `game_state` may be a ``gamelib.GameState`` (live-server
        runtime) OR a ``dict`` in the sim_rs schema OR a tuple/dict
        with the same keys. We duck-type to extract turn / mp values.
        """
        if k <= 0:
            return []

        bucket = self._game_state_bucket(game_state)

        # Widening progression: each entry says which axes from `bucket`
        # to keep (others go wild). We always keep `turn_band` last so
        # we don't broaden across the entire game until everything else
        # has been tried.
        progression: List[Tuple[bool, bool, bool, bool]] = [
            (True, True, True, True),     # exact
            (True, True, True, False),    # drop breach
            (True, True, False, False),   # drop breach + our_mp
            (True, False, False, False),  # drop everything except turn
            (False, False, False, False), # global pool
        ]

        seen_sigs: set = set()
        result: List[ActionPlan] = []
        rng = random.Random(0xC17ADE1 ^ bucket[0] ^ (bucket[1] << 4)
                            ^ (bucket[2] << 8) ^ (bucket[3] << 12))

        for keep_turn, keep_opp, keep_our, keep_breach in progression:
            candidates = self._collect_matching(
                bucket, keep_turn, keep_opp, keep_our, keep_breach,
            )
            rng.shuffle(candidates)
            for plan in candidates:
                sig = plan.signature()
                if sig in seen_sigs:
                    continue
                seen_sigs.add(sig)
                result.append(plan)
                if len(result) >= k:
                    return result[:k]

        # Final fallback: hand-coded prior archetypes.
        for desc, mobile_actions, sp_cost, mp_cost in self.PRIOR_ARCHETYPES:
            plan = ActionPlan(
                structure_actions=[],
                mobile_actions=[
                    (sh, list(loc), int(cnt))
                    for (sh, loc, cnt) in mobile_actions
                ],
                description=desc,
                sp_cost=float(sp_cost),
                mp_cost=float(mp_cost),
            )
            sig = plan.signature()
            if sig in seen_sigs:
                continue
            seen_sigs.add(sig)
            result.append(plan)
            if len(result) >= k:
                break

        return result[:k]

    def update_observation(
        self, turn_num: int, observed_opp_action: dict,
    ) -> None:
        """Record an observed opponent action this match (per-game posterior).

        v1: stores the raw observation in ``_game_posterior`` for later
        use; sample_actions does NOT yet bias toward it. Wired up so the
        downstream caller can start logging without coupling to the v2
        algorithm.
        """
        if not isinstance(observed_opp_action, dict):
            return
        plan = self._extract_opp_action_plan(observed_opp_action,
                                              opponent_pid=2)
        if plan is not None:
            self._game_posterior.append(plan)

    # ----------------------------------------------------------- internals

    def _game_state_bucket(self, game_state: Any) -> Tuple[int, int, int, int]:
        """Extract (turn_band, opp_mp_band, our_mp_band, breach_band) from
        a runtime game_state-shaped object.

        Supports three shapes:
          1. ``gamelib.GameState`` — has ``turn_number`` + ``get_resource``.
          2. dict with sim_rs schema — has "turn", "p1": {"mp": ...},
             "p2": {"mp": ...}.
          3. anything with ``turn``, ``opp_mp``, ``our_mp`` attributes.
        """
        turn = 0
        our_mp = 0.0
        opp_mp = 0.0
        recent_breaches = 0

        # gamelib.GameState path
        get_resource = getattr(game_state, "get_resource", None)
        turn_number = getattr(game_state, "turn_number", None)
        if callable(get_resource) and turn_number is not None:
            try:
                turn = int(turn_number)
                # MP=1, SP=0; player_index 0=self, 1=opp.
                our_mp = float(get_resource(1, 0) or 0.0)
                opp_mp = float(get_resource(1, 1) or 0.0)
            except Exception:
                pass
        elif isinstance(game_state, dict):
            try:
                turn = int(game_state.get("turn", 0) or 0)
                our_mp = float(((game_state.get("p1") or {}).get("mp")) or 0.0)
                opp_mp = float(((game_state.get("p2") or {}).get("mp")) or 0.0)
            except (TypeError, ValueError):
                pass
        else:
            turn = int(getattr(game_state, "turn", 0) or 0)
            our_mp = float(getattr(game_state, "our_mp", 0.0) or 0.0)
            opp_mp = float(getattr(game_state, "opp_mp", 0.0) or 0.0)
            recent_breaches = int(getattr(game_state, "recent_breaches", 0) or 0)

        return (
            _turn_band(turn),
            _mp_band(opp_mp),
            _mp_band(our_mp),
            _breach_band(recent_breaches),
        )

    def _collect_matching(
        self,
        target: Tuple[int, int, int, int],
        keep_turn: bool, keep_opp: bool, keep_our: bool, keep_breach: bool,
    ) -> List[ActionPlan]:
        """Scan all observed buckets and collect plans whose key matches
        on the kept axes.

        For the all-False case we just return the global pool — that's
        the cheapest path so we short-circuit it.
        """
        if not (keep_turn or keep_opp or keep_our or keep_breach):
            return list(self._all_plans)

        out: List[ActionPlan] = []
        for key, plans in self._observations.items():
            if keep_turn and key[0] != target[0]:
                continue
            if keep_opp and key[1] != target[1]:
                continue
            if keep_our and key[2] != target[2]:
                continue
            if keep_breach and key[3] != target[3]:
                continue
            out.extend(plans)
        return out

    # ----------------------------------------------------------- diagnostics

    def bucket_distribution(self) -> Dict[Tuple[int, int, int, int], int]:
        """Return ``{bucket_key: n_plans}`` for instrumentation."""
        return {k: len(v) for k, v in self._observations.items()}

    def stats(self) -> Dict[str, Any]:
        return {
            "n_replays_loaded": self._n_replays_loaded,
            "n_observations": self._n_observations,
            "n_buckets": len(self._observations),
            "opponent_id": self._opponent_id,
        }


__all__ = ["OpponentModel"]
