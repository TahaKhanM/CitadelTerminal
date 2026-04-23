/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import java.util.Collections;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

@Immutable
final class RetryPolicy {
    final int maxAttempts;
    final long initialBackoffNanos;
    final long maxBackoffNanos;
    final double backoffMultiplier;
    final Set<Status.Code> retryableStatusCodes;
    static final RetryPolicy DEFAULT = new RetryPolicy(1, 0L, 0L, 1.0, Collections.<Status.Code>emptySet());

    RetryPolicy(int maxAttempts, long initialBackoffNanos, long maxBackoffNanos, double backoffMultiplier, @Nonnull Set<Status.Code> retryableStatusCodes) {
        this.maxAttempts = maxAttempts;
        this.initialBackoffNanos = initialBackoffNanos;
        this.maxBackoffNanos = maxBackoffNanos;
        this.backoffMultiplier = backoffMultiplier;
        this.retryableStatusCodes = ImmutableSet.copyOf(retryableStatusCodes);
    }

    public int hashCode() {
        return Objects.hashCode(this.maxAttempts, this.initialBackoffNanos, this.maxBackoffNanos, this.backoffMultiplier, this.retryableStatusCodes);
    }

    public boolean equals(Object other) {
        if (!(other instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy that = (RetryPolicy)other;
        return this.maxAttempts == that.maxAttempts && this.initialBackoffNanos == that.initialBackoffNanos && this.maxBackoffNanos == that.maxBackoffNanos && Double.compare(this.backoffMultiplier, that.backoffMultiplier) == 0 && Objects.equal(this.retryableStatusCodes, that.retryableStatusCodes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos).add("backoffMultiplier", this.backoffMultiplier).add("retryableStatusCodes", this.retryableStatusCodes).toString();
    }

    static interface Provider {
        @Nonnull
        public RetryPolicy get();
    }
}

