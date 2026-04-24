#!/usr/bin/env bash
# Install pre-commit hook that runs the regression runner's `quick` scope
# (float32 propagation + dual-mode parity) before every commit. Target
# runtime: < 60 s on a ranked-laptop (2021 M1).
#
# The hook soft-blocks: on any quick-gate failure it exits non-zero, which
# aborts the commit. Bypass with `git commit --no-verify`.
#
# Python interpreter resolution: hook hunts for a Python ≥3.10 (repo uses
# @dataclass(slots=True) which needs 3.10+). Falls back to `python3` from
# PATH. Record the path in pre-commit so later updates don't silently
# drift to system Python 3.9.

set -euo pipefail

REPO_ROOT="$(git rev-parse --show-toplevel)"
HOOK_PATH="${REPO_ROOT}/.git/hooks/pre-commit"

# Find a Python ≥ 3.10.
FOUND_PY=""
for CAND in \
    "/opt/miniconda3/bin/python3.13" \
    "/opt/miniconda3/bin/python3" \
    "$(command -v python3.13 2>/dev/null || true)" \
    "$(command -v python3.12 2>/dev/null || true)" \
    "$(command -v python3.11 2>/dev/null || true)" \
    "$(command -v python3.10 2>/dev/null || true)" \
    "$(command -v python3 2>/dev/null || true)" \
; do
    [ -z "${CAND}" ] && continue
    [ -x "${CAND}" ] || continue
    VER="$(${CAND} -c 'import sys; print(sys.version_info[:2] >= (3,10))' 2>/dev/null || echo False)"
    if [ "${VER}" = "True" ]; then
        FOUND_PY="${CAND}"
        break
    fi
done

if [ -z "${FOUND_PY}" ]; then
    echo "No Python ≥3.10 found — repo needs @dataclass(slots=True) support" >&2
    echo "Install Python 3.10+ and re-run: algos/athena/sim/install_hooks.sh" >&2
    exit 1
fi

cat > "${HOOK_PATH}" <<HOOK
#!/usr/bin/env bash
# Auto-installed by algos/athena/sim/install_hooks.sh — do not edit by hand.
set -e
echo "[pre-commit] SimCore quick parity gate"
"${FOUND_PY}" "\${GIT_DIR:-.git}/../algos/athena/sim/regression_runner.py" --scope quick
HOOK

chmod +x "${HOOK_PATH}"
echo "Installed pre-commit hook at ${HOOK_PATH}"
echo "Python interpreter: ${FOUND_PY} ($(${FOUND_PY} -c 'import sys; print(sys.version.split()[0])'))"
echo "Bypass: 'git commit --no-verify'"
echo "Uninstall: 'rm ${HOOK_PATH}'"
