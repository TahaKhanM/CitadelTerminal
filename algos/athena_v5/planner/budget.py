"""Per-turn budget watchdog.

Phase 4 task 5.

The 15s deploy-time hard floor (1 dmg/sec penalty after) sets a strict
upper bound on how long ``on_turn`` can run. We aim well under that
(13s soft cap). A Watchdog instance is reset at the top of every
``on_turn``; components call ``check(name)`` to see if their per-component
soft cap has been exceeded.

Per the build plan's recommended budget split:
    SimCore setup       : 0.20 s
    Opponent posterior  : 0.50 s
    DefensePlanner      : 1.50 s
    OffensePlanner      : 8.00 s
    MAP-Elites          : 0.10 s
    GC reserve          : 4.00 s   (reserved against 17.30 s total)

Note: components above are NOT cumulative — each is a wall-clock budget
allocated to that component starting from start_turn(). The watchdog
fires force_fallback() at total_budget_ms (default 13000ms).

Usage::

    wd = Watchdog(total_budget_ms=13000)
    wd.start_turn()
    try:
        ...
        wd.check("DefensePlanner")
        ...
        wd.check("OffensePlanner")
        ...
    except BudgetExceeded:
        # safe-fallback path
"""
from __future__ import annotations

import time
from dataclasses import dataclass, field
from typing import Dict, Optional


# ---------------------------------------------------------------------------
# Soft caps (ms) per component
# ---------------------------------------------------------------------------

DEFAULT_COMPONENT_CAPS_MS: Dict[str, float] = {
    "SimCore setup":       200.0,
    "Opponent posterior":  500.0,
    "DefensePlanner":     1500.0,
    "OffensePlanner":     8000.0,
    "MAP-Elites":          100.0,
    "GC reserve":         4000.0,
}

DEFAULT_TOTAL_BUDGET_MS = 13000.0


class BudgetExceeded(Exception):
    """Raised when a component or the total budget is breached."""

    def __init__(self, component: str, elapsed_ms: float, cap_ms: float):
        self.component = component
        self.elapsed_ms = float(elapsed_ms)
        self.cap_ms = float(cap_ms)
        super().__init__(
            f"BudgetExceeded: {component}: elapsed={elapsed_ms:.1f}ms "
            f"cap={cap_ms:.1f}ms"
        )


@dataclass
class Watchdog:
    total_budget_ms: float = DEFAULT_TOTAL_BUDGET_MS
    component_caps_ms: Dict[str, float] = field(
        default_factory=lambda: dict(DEFAULT_COMPONENT_CAPS_MS)
    )
    _t_start: float = 0.0
    _component_starts: Dict[str, float] = field(default_factory=dict)

    # ---------------------- lifecycle ----------------------

    def start_turn(self) -> None:
        """Reset all timers."""
        self._t_start = time.perf_counter()
        self._component_starts = {}

    def total_elapsed_ms(self) -> float:
        return (time.perf_counter() - self._t_start) * 1000.0

    def remaining_ms(self) -> float:
        """Time left before the total budget is exhausted."""
        return max(0.0, self.total_budget_ms - self.total_elapsed_ms())

    # ---------------------- per-component ------------------

    def begin(self, component: str) -> None:
        """Mark the start of a component's compute window. Optional."""
        self._component_starts[component] = time.perf_counter()

    def component_elapsed_ms(self, component: str) -> float:
        """Wall clock since `begin(component)`. Falls back to total
        elapsed if the component was never explicitly begun."""
        start = self._component_starts.get(component)
        if start is None:
            return self.total_elapsed_ms()
        return (time.perf_counter() - start) * 1000.0

    def check(self, component: str) -> None:
        """Raise BudgetExceeded if either the component's soft cap or
        the total budget has been exceeded.

        ``component`` may be unknown to the cap table — it then falls
        through to the total-budget check only.
        """
        # Hard total-budget check first (always wins)
        total = self.total_elapsed_ms()
        if total >= self.total_budget_ms:
            raise BudgetExceeded(component, total, self.total_budget_ms)
        # Soft per-component check
        cap = self.component_caps_ms.get(component)
        if cap is None:
            return
        elapsed = self.component_elapsed_ms(component)
        if elapsed >= cap:
            raise BudgetExceeded(component, elapsed, cap)

    def force_fallback(self) -> None:
        """Hard-stop: raise BudgetExceeded(total) regardless of component caps."""
        raise BudgetExceeded(
            "TOTAL", self.total_elapsed_ms(), self.total_budget_ms,
        )


__all__ = [
    "BudgetExceeded",
    "Watchdog",
    "DEFAULT_COMPONENT_CAPS_MS",
    "DEFAULT_TOTAL_BUDGET_MS",
]
