"""Bayesian archetype classifier — Gaussian Naive Bayes in numpy.

No sklearn dependency. Implements the classic GaussNB likelihood:
P(x | C) = prod_i  N(x_i; mu_{C,i}, sigma^2_{C,i}).

Two classifier methods callers will use:
  - ``predict_proba(features) -> dict[archetype, prob]``
  - ``predict(features) -> archetype``

Plus an online posterior updater for use during a live match:
  - ``update_posterior(prev, new_observation) -> dict[archetype, prob]``

The "online" updater treats each turn's *current* feature vector as a
new independent observation drawn from the same archetype, multiplies
the previous posterior by the per-archetype likelihood, and
renormalizes. With reasonable Phase 0.5 trackers this is approximately
correct because the trackers' state is *cumulative-decayed*, not a
single-frame snapshot.

Numerical safety:
  - Min variance floored to ``var_smoothing * max(var)`` per dim
    (sklearn-style).
  - Likelihoods computed in log-space to avoid underflow.
  - Class priors: empirical class fraction with Laplace +1 smoothing.

The classifier is designed to gracefully handle:
  - 0-sample classes (TURTLE_GRIND etc.) — they get the Laplace prior
    of 1/(N+K) and a uniform-mean Gaussian (estimated from the global
    population).
  - Single-sample classes — variance falls back to global variance.
"""
from __future__ import annotations

import math
from typing import Dict, Iterable, List, Optional, Sequence, Tuple

import numpy as np

from .archetypes import ARCHETYPES, DEFAULT_ARCHETYPE
from .features import FEATURE_NAMES, features_to_vector


# ---------------------------------------------------------------------------
# Gaussian Naive Bayes
# ---------------------------------------------------------------------------

class ArchetypeClassifier:
    """Gaussian Naive Bayes over the FEATURE_NAMES vector.

    `var_smoothing`:
        Fraction of the largest feature variance to add to every per-
        class variance. Mirrors sklearn's `GaussianNB.var_smoothing`
        default (1e-9). Bumped to 1e-3 here because of the very small
        sample size — without it, single-sample classes get
        near-degenerate Gaussians.
    """

    def __init__(
        self,
        archetypes: Sequence[str] = ARCHETYPES,
        var_smoothing: float = 1e-3,
    ) -> None:
        self.archetypes: Tuple[str, ...] = tuple(archetypes)
        self.var_smoothing = float(var_smoothing)
        self.feature_names: Tuple[str, ...] = FEATURE_NAMES
        self._means: Optional[np.ndarray] = None      # shape (K, F)
        self._vars: Optional[np.ndarray] = None       # shape (K, F)
        self._priors: Optional[np.ndarray] = None     # shape (K,)
        self._fitted: bool = False
        self._n_samples: int = 0

    # -- fitting -------------------------------------------------------

    def fit(
        self,
        X: np.ndarray,
        labels: Sequence[str],
    ) -> "ArchetypeClassifier":
        """Estimate per-class Gaussians from `X` (N x F) + `labels`.

        Classes with 0 samples get the global mean & global variance
        and a Laplace-smoothed prior. Classes with >=1 sample get
        empirical mean and either empirical variance (if >=2 samples)
        or global variance (if 1 sample).
        """
        X = np.asarray(X, dtype=np.float64)
        if X.ndim != 2:
            raise ValueError(f"X must be 2D; got shape {X.shape}")
        labels = list(labels)
        if len(labels) != X.shape[0]:
            raise ValueError(
                f"X has {X.shape[0]} rows but {len(labels)} labels supplied"
            )
        n, f = X.shape
        self._n_samples = n

        K = len(self.archetypes)
        means = np.zeros((K, f), dtype=np.float64)
        variances = np.zeros((K, f), dtype=np.float64)
        priors = np.zeros(K, dtype=np.float64)

        global_mean = X.mean(axis=0) if n > 0 else np.zeros(f)
        global_var = X.var(axis=0, ddof=0) if n > 0 else np.ones(f)
        # Floor to avoid zero-variance dims (e.g. constant features)
        global_var = np.maximum(global_var, 1e-9)

        for k_idx, name in enumerate(self.archetypes):
            mask = np.array([lab == name for lab in labels], dtype=bool)
            n_k = int(mask.sum())
            if n_k >= 2:
                means[k_idx] = X[mask].mean(axis=0)
                variances[k_idx] = X[mask].var(axis=0, ddof=0)
            elif n_k == 1:
                means[k_idx] = X[mask][0]
                variances[k_idx] = global_var
            else:
                # 0-sample class: fall back to global stats so it stays
                # a valid distribution, just heavily down-weighted by
                # the prior.
                means[k_idx] = global_mean
                variances[k_idx] = global_var

            # Laplace +1 smoothing on priors
            priors[k_idx] = (n_k + 1.0) / (n + K)

        # Stabilization: floor every per-class variance by var_smoothing
        # times the largest variance across (class, feature). This is
        # the same trick sklearn uses; on 47 samples it noticeably
        # widens single-sample-class Gaussians.
        var_floor = self.var_smoothing * variances.max()
        if var_floor > 0:
            variances = np.maximum(variances, var_floor)
        # Hard floor too (1e-9) in case all variances were zero
        variances = np.maximum(variances, 1e-9)

        self._means = means
        self._vars = variances
        self._priors = priors
        self._fitted = True
        return self

    # -- prediction ----------------------------------------------------

    def _log_likelihoods(self, x: np.ndarray) -> np.ndarray:
        """Per-class log P(x | C) — shape (K,)."""
        assert self._means is not None and self._vars is not None
        # log N(x | mu, sigma^2) = -0.5 * (log(2*pi*sigma^2) + (x - mu)^2 / sigma^2)
        diff2 = (x[None, :] - self._means) ** 2
        return -0.5 * (
            np.log(2.0 * math.pi * self._vars) + diff2 / self._vars
        ).sum(axis=1)

    def predict_proba(self, features) -> Dict[str, float]:
        """Return ``{archetype: P(C | x)}``.

        `features` may be a dict (keyed by FEATURE_NAMES) or a 1-D array.
        """
        if not self._fitted:
            raise RuntimeError("Classifier has not been fit() yet.")
        x = self._coerce(features)
        log_post = self._log_likelihoods(x) + np.log(self._priors)
        # Softmax in log-space for numerical safety
        log_post -= log_post.max()
        unnorm = np.exp(log_post)
        post = unnorm / unnorm.sum()
        return {name: float(post[i]) for i, name in enumerate(self.archetypes)}

    def predict(self, features) -> str:
        """Top-1 archetype for `features`."""
        post = self.predict_proba(features)
        return max(post.items(), key=lambda kv: kv[1])[0]

    def update_posterior(
        self,
        prev_posterior: Dict[str, float],
        new_observation,
    ) -> Dict[str, float]:
        """Multiplicative update: posterior_t+1 ∝ posterior_t * P(obs | C).

        Use during a live match: feed the live feature vector at each
        turn through this method to keep refining the archetype
        posterior. The likelihood is computed in log-space and
        renormalized; values clamped to [1e-9, 1].
        """
        if not self._fitted:
            raise RuntimeError("Classifier has not been fit() yet.")
        x = self._coerce(new_observation)
        log_lik = self._log_likelihoods(x)
        log_prev = np.array(
            [math.log(max(prev_posterior.get(a, 1e-9), 1e-12)) for a in self.archetypes],
            dtype=np.float64,
        )
        log_post = log_prev + log_lik
        log_post -= log_post.max()
        unnorm = np.exp(log_post)
        post = unnorm / unnorm.sum()
        return {a: float(post[i]) for i, a in enumerate(self.archetypes)}

    # -- helpers -------------------------------------------------------

    def _coerce(self, features) -> np.ndarray:
        if isinstance(features, dict):
            return features_to_vector(features)
        x = np.asarray(features, dtype=np.float64).ravel()
        if x.shape[0] != len(FEATURE_NAMES):
            raise ValueError(
                f"feature vector must have {len(FEATURE_NAMES)} elements; "
                f"got {x.shape[0]}"
            )
        return x

    @property
    def is_fitted(self) -> bool:
        return self._fitted

    def uniform_prior(self) -> Dict[str, float]:
        """Convenience: a uniform posterior to seed `update_posterior`."""
        p = 1.0 / len(self.archetypes)
        return {a: p for a in self.archetypes}


# ---------------------------------------------------------------------------
# Convenience: load+fit from the .npz the build_corpus script wrote
# ---------------------------------------------------------------------------

def load_corpus(npz_path) -> Tuple[np.ndarray, List[str], List[str]]:
    """Return ``(X, labels, opponents)`` from the saved .npz."""
    data = np.load(npz_path, allow_pickle=False)
    X = np.asarray(data["X"], dtype=np.float64)
    labels = [str(x) for x in data["labels"]]
    opponents = [str(x) for x in data["opponents"]]
    return X, labels, opponents


def fit_default_classifier(npz_path) -> ArchetypeClassifier:
    """Fit a fresh classifier on the saved corpus."""
    X, labels, _ = load_corpus(npz_path)
    return ArchetypeClassifier().fit(X, labels)


__all__ = [
    "ArchetypeClassifier",
    "load_corpus",
    "fit_default_classifier",
]
