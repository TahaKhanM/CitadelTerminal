/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.AutoValue_RetrySettings;
import java.io.Serializable;
import org.threeten.bp.Duration;

public abstract class RetrySettings
implements Serializable {
    private static final long serialVersionUID = 8258475264439710899L;

    public abstract Duration getTotalTimeout();

    public abstract Duration getInitialRetryDelay();

    public abstract double getRetryDelayMultiplier();

    public abstract Duration getMaxRetryDelay();

    public abstract int getMaxAttempts();

    public abstract boolean isJittered();

    public abstract Duration getInitialRpcTimeout();

    public abstract double getRpcTimeoutMultiplier();

    public abstract Duration getMaxRpcTimeout();

    public static Builder newBuilder() {
        return new AutoValue_RetrySettings.Builder().setTotalTimeout(Duration.ZERO).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0).setMaxRetryDelay(Duration.ZERO).setMaxAttempts(0).setJittered(true).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ZERO);
    }

    public Builder toBuilder() {
        return new AutoValue_RetrySettings.Builder(this);
    }

    public static abstract class Builder {
        public abstract Builder setTotalTimeout(Duration var1);

        public abstract Builder setInitialRetryDelay(Duration var1);

        public abstract Builder setRetryDelayMultiplier(double var1);

        public abstract Builder setMaxRetryDelay(Duration var1);

        public abstract Builder setMaxAttempts(int var1);

        public abstract Builder setJittered(boolean var1);

        public abstract Builder setInitialRpcTimeout(Duration var1);

        public abstract Builder setRpcTimeoutMultiplier(double var1);

        public abstract Builder setMaxRpcTimeout(Duration var1);

        public abstract Duration getTotalTimeout();

        public abstract Duration getInitialRetryDelay();

        public abstract double getRetryDelayMultiplier();

        public abstract int getMaxAttempts();

        public abstract boolean isJittered();

        public abstract Duration getMaxRetryDelay();

        public abstract Duration getInitialRpcTimeout();

        public abstract double getRpcTimeoutMultiplier();

        public abstract Duration getMaxRpcTimeout();

        abstract RetrySettings autoBuild();

        public RetrySettings build() {
            RetrySettings params2 = this.autoBuild();
            if (params2.getTotalTimeout().toMillis() < 0L) {
                throw new IllegalStateException("total timeout must not be negative");
            }
            if (params2.getInitialRetryDelay().toMillis() < 0L) {
                throw new IllegalStateException("initial retry delay must not be negative");
            }
            if (params2.getRetryDelayMultiplier() < 1.0) {
                throw new IllegalStateException("retry delay multiplier must be at least 1");
            }
            if (params2.getMaxRetryDelay().compareTo(params2.getInitialRetryDelay()) < 0) {
                throw new IllegalStateException("max retry delay must not be shorter than initial delay");
            }
            if (params2.getMaxAttempts() < 0) {
                throw new IllegalStateException("max attempts must be non-negative");
            }
            if (params2.getInitialRpcTimeout().toMillis() < 0L) {
                throw new IllegalStateException("initial rpc timeout must not be negative");
            }
            if (params2.getMaxRpcTimeout().compareTo(params2.getInitialRpcTimeout()) < 0) {
                throw new IllegalStateException("max rpc timeout must not be shorter than initial timeout");
            }
            if (params2.getRpcTimeoutMultiplier() < 1.0) {
                throw new IllegalStateException("rpc timeout multiplier must be at least 1");
            }
            return params2;
        }

        public Builder merge(Builder newSettings) {
            if (newSettings.getTotalTimeout() != null) {
                this.setTotalTimeout(newSettings.getTotalTimeout());
            }
            if (newSettings.getInitialRetryDelay() != null) {
                this.setInitialRetryDelay(newSettings.getInitialRetryDelay());
            }
            if (newSettings.getRetryDelayMultiplier() >= 1.0) {
                this.setRetryDelayMultiplier(newSettings.getRetryDelayMultiplier());
            }
            if (newSettings.getMaxRetryDelay() != null) {
                this.setMaxRetryDelay(newSettings.getMaxRetryDelay());
            }
            this.setMaxAttempts(newSettings.getMaxAttempts());
            this.setJittered(newSettings.isJittered());
            if (newSettings.getInitialRpcTimeout() != null) {
                this.setInitialRpcTimeout(newSettings.getInitialRpcTimeout());
            }
            if (newSettings.getRpcTimeoutMultiplier() >= 1.0) {
                this.setRpcTimeoutMultiplier(newSettings.getRpcTimeoutMultiplier());
            }
            if (newSettings.getMaxRpcTimeout() != null) {
                this.setMaxRpcTimeout(newSettings.getMaxRpcTimeout());
            }
            return this;
        }
    }
}

