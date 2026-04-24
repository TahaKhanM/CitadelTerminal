"""In-place mypyc compilation of the SimCore hot-path modules.

Run from algos/athena/:
    python3 setup_mypyc.py build_ext --inplace

This generates `sim/pathfinder.cpython-*.so` (and friends) alongside the
.py files. Python's import system prefers .so over .py, so normal imports
(`from sim.pathfinder import PathFinder`) transparently pick up the
compiled extension.

If you need to go back to pure-Python (e.g. for line-level debugging),
delete the .so files and the `build/` directory.
"""

from mypyc.build import mypycify
from setuptools import Extension, setup


TARGETS = [
    "sim/pathfinder.py",
    "sim/state.py",
    "sim/pysim.py",
    "sim/config.py",
]


# Plain C extension for the attack-system hot path — sidesteps mypyc's
# boxed Any attribute-access overhead.
fastsim_ext = Extension(
    "sim._fastsim",
    sources=["sim/_fastsim.c"],
    extra_compile_args=["-O3"],
)


setup(
    name="simcore_mypyc",
    ext_modules=mypycify(
        TARGETS,
        opt_level="3",
        strict_dunder_typing=False,
    ) + [fastsim_ext],
)
