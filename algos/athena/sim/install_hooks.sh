#!/usr/bin/env bash
# Install pre-commit hook that runs the regression runner's `quick` scope
# (float32 propagation + dual-mode parity) before every commit. Target
# runtime: < 60 s on a ranked-laptop (2021 M1).
#
# The hook soft-blocks: on any quick-gate failure it writes a diagnostic
# line and exits non-zero. Bypass with `git commit --no-verify` at your
# own peril.

set -euo pipefail

REPO_ROOT="$(git rev-parse --show-toplevel)"
HOOK_PATH="${REPO_ROOT}/.git/hooks/pre-commit"

cat > "${HOOK_PATH}" <<'HOOK'
#!/usr/bin/env bash
# Auto-installed by algos/athena/sim/install_hooks.sh — do not edit by hand.
set -e
echo "[pre-commit] SimCore quick parity gate"
python3 "${GIT_DIR:-.git}/../algos/athena/sim/regression_runner.py" --scope quick
HOOK

chmod +x "${HOOK_PATH}"
echo "Installed pre-commit hook at ${HOOK_PATH}"
echo "Run  'git commit --no-verify'  to bypass; run  'rm ${HOOK_PATH}'  to uninstall."
