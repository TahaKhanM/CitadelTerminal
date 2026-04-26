"""Athena v3 — offense engine package.

Contains:
  - templates/        : JSON offense templates (one file per template)
  - templates.py      : loader + validator
  - sim_eval.py       : sim_rs PyO3 wrapper for action-phase rollout
"""
from .templates import (
    OffenseTemplate,
    SpawnGroup,
    load_template,
    load_all_templates,
    validate_template,
)

__all__ = [
    "OffenseTemplate",
    "SpawnGroup",
    "load_template",
    "load_all_templates",
    "validate_template",
]
