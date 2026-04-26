"""sim_bridge — drives the static sim_rs binary via stdio IPC.

Why this exists:
  The PyO3 wheel (sim_rs.simulate_action_phase_py) is platform + Python-
  version specific and can't be cross-shipped from local conda to the
  competition server. A statically-linked Linux x64 musl binary CAN be
  bundled (~550 KB, no glibc/Python ABI dependency). This module is the
  glue: at game start, spawn the binary as a long-lived subprocess; per
  sim, send a JSON request line and read a JSON response line.

Performance ceiling: the binary itself runs at ~14.6 K sims/s native
(per algos/athena/sim/SIM_PARITY.md). IPC adds JSON serialize+pipe
roundtrip (~1-3 ms per call for typical state sizes). Net throughput
should be 200-500 sims/s — slower than in-process PyO3 (~14.6 K) but
much faster than the pure-Python pysim path (~376 sims/s) when both
include planner overhead, and crucially: WORKS ON THE LIVE SERVER.

Architecture:
  - Single global process. Started lazily on first call. Shared across
    all sim_eval calls in the algo.
  - Request IDs are monotonic so out-of-order responses are detected
    (currently we read sync — one request, one response — but the
    binary supports interleaving for future batch optimization).
  - On any IPC error, the bridge marks itself unavailable and downstream
    callers (evaluate_action_phase) fall through to pysim.

Public API:
  - sim_bridge_available() -> bool
  - simulate_action_phase_via_bridge(state_dict, config_path) -> dict
  - shutdown_bridge() (test/teardown)
"""
from __future__ import annotations

import json
import os
import platform
import subprocess
import sys
import threading
from pathlib import Path
from typing import Any, Dict, Optional

_HERE = Path(__file__).resolve().parent
_BIN_DIR = _HERE / "bin"


def _detect_binary() -> Optional[Path]:
    """Pick the right binary for this OS/arch.

    On the competition server (Linux x64) we expect sim_stdio_linux_x64.
    Locally on the user's Mac arm64 we use sim_stdio_macos_arm64. Other
    combinations return None — caller falls through to pysim.
    """
    sysname = platform.system().lower()      # "linux" / "darwin" / "windows"
    machine = platform.machine().lower()     # "x86_64" / "amd64" / "arm64" / "aarch64"
    if sysname == "linux" and machine in ("x86_64", "amd64"):
        bin_name = "sim_stdio_linux_x64"
    elif sysname == "darwin" and machine in ("arm64", "aarch64"):
        bin_name = "sim_stdio_macos_arm64"
    else:
        return None
    p = _BIN_DIR / bin_name
    if not p.is_file():
        return None
    if not os.access(p, os.X_OK):
        # Zip extraction can clobber the +x bit. Try to fix.
        try:
            os.chmod(p, 0o755)
        except OSError:
            return None
        if not os.access(p, os.X_OK):
            return None
    return p


_BRIDGE_LOCK = threading.Lock()
_BRIDGE_PROC: Optional[subprocess.Popen] = None
_BRIDGE_STATE: str = "uninit"  # uninit | ready | dead
_BRIDGE_LAST_ERROR: Optional[str] = None
_NEXT_ID: int = 0


def _bridge_log(msg: str) -> None:
    """Best-effort stderr log (live engine surfaces via printBotErrors=True)."""
    try:
        print(f"[sim_bridge] {msg}", file=sys.stderr, flush=True)
    except Exception:  # noqa: BLE001
        pass


def _start_bridge(config_path: str) -> bool:
    """Spawn the static binary as a subprocess. Idempotent."""
    global _BRIDGE_PROC, _BRIDGE_STATE, _BRIDGE_LAST_ERROR

    with _BRIDGE_LOCK:
        if _BRIDGE_STATE == "ready" and _BRIDGE_PROC is not None and _BRIDGE_PROC.poll() is None:
            return True
        if _BRIDGE_STATE == "dead":
            return False  # Earlier failure — don't retry every call.

        bin_path = _detect_binary()
        if bin_path is None:
            _BRIDGE_STATE = "dead"
            _BRIDGE_LAST_ERROR = (
                f"no compatible binary in {_BIN_DIR} for "
                f"{platform.system()}/{platform.machine()}"
            )
            _bridge_log(_BRIDGE_LAST_ERROR)
            return False

        try:
            proc = subprocess.Popen(
                [str(bin_path), str(config_path)],
                stdin=subprocess.PIPE,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                text=True,
                bufsize=1,                    # line-buffered
                # No shell, no env munging; inherit minimal.
            )
        except (OSError, FileNotFoundError) as exc:
            _BRIDGE_STATE = "dead"
            _BRIDGE_LAST_ERROR = f"spawn failed: {exc!r}"
            _bridge_log(_BRIDGE_LAST_ERROR)
            return False

        _BRIDGE_PROC = proc
        _BRIDGE_STATE = "ready"
        _bridge_log(f"bridge ready: {bin_path.name}")
        return True


def sim_bridge_available(config_path: Optional[str] = None) -> bool:
    """Return True iff the bridge is (or can be) started.

    On first call, spawns the subprocess — subsequent calls are O(1)
    after the first. Returns False if no compatible binary exists for
    this platform; callers fall through to pysim.
    """
    if _BRIDGE_STATE == "ready":
        return True
    if _BRIDGE_STATE == "dead":
        return False
    if config_path is None:
        # Need a config path to spawn — caller hasn't provided one yet.
        return False
    return _start_bridge(config_path)


def simulate_action_phase_via_bridge(
    state_dict: Dict[str, Any],
    config_path: str,
) -> Dict[str, Any]:
    """Run one action-phase sim via the bridge subprocess.

    Returns the post-sim state dict (same schema as
    sim_rs.simulate_action_phase_py). Raises ``BridgeError`` on any IPC
    failure — caller should catch and fall through to pysim.

    Thread-safety: serialized by _BRIDGE_LOCK. The bridge supports
    interleaved IDs but our current call sites are single-threaded so
    we keep request/response pairing strict (send → read → return).
    """
    global _NEXT_ID

    if not _start_bridge(config_path):
        raise BridgeError(_BRIDGE_LAST_ERROR or "bridge not started")

    proc = _BRIDGE_PROC
    if proc is None or proc.poll() is not None:
        # Process died unexpectedly. Mark dead, surface error.
        _mark_dead("subprocess exited")
        raise BridgeError("bridge subprocess exited")

    with _BRIDGE_LOCK:
        rid = _NEXT_ID
        _NEXT_ID += 1
        req = json.dumps({"id": rid, "state": state_dict}, separators=(",", ":"))

        try:
            proc.stdin.write(req)
            proc.stdin.write("\n")
            proc.stdin.flush()
        except (BrokenPipeError, OSError) as exc:
            _mark_dead(f"stdin write: {exc!r}")
            raise BridgeError(f"stdin write: {exc!r}") from exc

        try:
            line = proc.stdout.readline()
        except OSError as exc:
            _mark_dead(f"stdout read: {exc!r}")
            raise BridgeError(f"stdout read: {exc!r}") from exc

        if not line:
            _mark_dead("eof on stdout")
            raise BridgeError("eof on stdout (binary crashed?)")

        try:
            resp = json.loads(line)
        except json.JSONDecodeError as exc:
            _mark_dead(f"bad json from binary: {exc!r}")
            raise BridgeError(f"bad json from binary: {line[:200]!r}") from exc

        if resp.get("error"):
            # Per-request error from the binary — sim_rs rejected this state.
            # Don't kill the bridge for this; caller handles fallback.
            raise BridgeError(f"binary returned error: {resp['error']}")

        if resp.get("id") != rid:
            # Out-of-order response — shouldn't happen with sync use, but
            # detect to avoid silent corruption.
            _mark_dead(f"response id {resp.get('id')} != request id {rid}")
            raise BridgeError(f"response id {resp.get('id')} != request id {rid}")

        state = resp.get("state")
        if not isinstance(state, dict):
            raise BridgeError(f"missing 'state' in response: {resp!r}")
        return state


def _mark_dead(reason: str) -> None:
    global _BRIDGE_STATE, _BRIDGE_LAST_ERROR, _BRIDGE_PROC
    _BRIDGE_STATE = "dead"
    _BRIDGE_LAST_ERROR = reason
    _bridge_log(f"bridge died: {reason}")
    if _BRIDGE_PROC is not None:
        try:
            _BRIDGE_PROC.kill()
        except Exception:  # noqa: BLE001
            pass
        _BRIDGE_PROC = None


def shutdown_bridge() -> None:
    """Tear down the bridge subprocess. Idempotent."""
    global _BRIDGE_PROC, _BRIDGE_STATE
    with _BRIDGE_LOCK:
        if _BRIDGE_PROC is not None:
            try:
                _BRIDGE_PROC.stdin.close()
            except Exception:  # noqa: BLE001
                pass
            try:
                _BRIDGE_PROC.terminate()
                _BRIDGE_PROC.wait(timeout=1.0)
            except Exception:  # noqa: BLE001
                try:
                    _BRIDGE_PROC.kill()
                except Exception:  # noqa: BLE001
                    pass
            _BRIDGE_PROC = None
        _BRIDGE_STATE = "uninit"  # allow re-init


class BridgeError(RuntimeError):
    """Bridge IPC failure. Callers should fall through to pysim."""


__all__ = [
    "BridgeError",
    "shutdown_bridge",
    "sim_bridge_available",
    "simulate_action_phase_via_bridge",
]
