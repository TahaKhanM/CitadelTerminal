/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.RetrySettings;
import org.threeten.bp.Duration;

final class AutoValue_RetrySettings
extends RetrySettings {
    private final Duration totalTimeout;
    private final Duration initialRetryDelay;
    private final double retryDelayMultiplier;
    private final Duration maxRetryDelay;
    private final int maxAttempts;
    private final boolean jittered;
    private final Duration initialRpcTimeout;
    private final double rpcTimeoutMultiplier;
    private final Duration maxRpcTimeout;
    private static final long serialVersionUID = 8258475264439710899L;

    private AutoValue_RetrySettings(Duration totalTimeout, Duration initialRetryDelay, double retryDelayMultiplier, Duration maxRetryDelay, int maxAttempts, boolean jittered, Duration initialRpcTimeout, double rpcTimeoutMultiplier, Duration maxRpcTimeout) {
        if (totalTimeout == null) {
            throw new NullPointerException("Null totalTimeout");
        }
        this.totalTimeout = totalTimeout;
        if (initialRetryDelay == null) {
            throw new NullPointerException("Null initialRetryDelay");
        }
        this.initialRetryDelay = initialRetryDelay;
        this.retryDelayMultiplier = retryDelayMultiplier;
        if (maxRetryDelay == null) {
            throw new NullPointerException("Null maxRetryDelay");
        }
        this.maxRetryDelay = maxRetryDelay;
        this.maxAttempts = maxAttempts;
        this.jittered = jittered;
        if (initialRpcTimeout == null) {
            throw new NullPointerException("Null initialRpcTimeout");
        }
        this.initialRpcTimeout = initialRpcTimeout;
        this.rpcTimeoutMultiplier = rpcTimeoutMultiplier;
        if (maxRpcTimeout == null) {
            throw new NullPointerException("Null maxRpcTimeout");
        }
        this.maxRpcTimeout = maxRpcTimeout;
    }

    @Override
    public Duration getTotalTimeout() {
        return this.totalTimeout;
    }

    @Override
    public Duration getInitialRetryDelay() {
        return this.initialRetryDelay;
    }

    @Override
    public double getRetryDelayMultiplier() {
        return this.retryDelayMultiplier;
    }

    @Override
    public Duration getMaxRetryDelay() {
        return this.maxRetryDelay;
    }

    @Override
    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    @Override
    public boolean isJittered() {
        return this.jittered;
    }

    @Override
    public Duration getInitialRpcTimeout() {
        return this.initialRpcTimeout;
    }

    @Override
    public double getRpcTimeoutMultiplier() {
        return this.rpcTimeoutMultiplier;
    }

    @Override
    public Duration getMaxRpcTimeout() {
        return this.maxRpcTimeout;
    }

    public String toString() {
        return "RetrySettings{totalTimeout=" + this.totalTimeout + ", initialRetryDelay=" + this.initialRetryDelay + ", retryDelayMultiplier=" + this.retryDelayMultiplier + ", maxRetryDelay=" + this.maxRetryDelay + ", maxAttempts=" + this.maxAttempts + ", jittered=" + this.jittered + ", initialRpcTimeout=" + this.initialRpcTimeout + ", rpcTimeoutMultiplier=" + this.rpcTimeoutMultiplier + ", maxRpcTimeout=" + this.maxRpcTimeout + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof RetrySettings) {
            RetrySettings that = (RetrySettings)o;
            return this.totalTimeout.equals(that.getTotalTimeout()) && this.initialRetryDelay.equals(that.getInitialRetryDelay()) && Double.doubleToLongBits(this.retryDelayMultiplier) == Double.doubleToLongBits(that.getRetryDelayMultiplier()) && this.maxRetryDelay.equals(that.getMaxRetryDelay()) && this.maxAttempts == that.getMaxAttempts() && this.jittered == that.isJittered() && this.initialRpcTimeout.equals(that.getInitialRpcTimeout()) && Double.doubleToLongBits(this.rpcTimeoutMultiplier) == Double.doubleToLongBits(that.getRpcTimeoutMultiplier()) && this.maxRpcTimeout.equals(that.getMaxRpcTimeout());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.totalTimeout.hashCode();
        h *= 1000003;
        h ^= this.initialRetryDelay.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.retryDelayMultiplier) >>> 32 ^ Double.doubleToLongBits(this.retryDelayMultiplier)));
        h *= 1000003;
        h ^= this.maxRetryDelay.hashCode();
        h *= 1000003;
        h ^= this.maxAttempts;
        h *= 1000003;
        h ^= this.jittered ? 1231 : 1237;
        h *= 1000003;
        h ^= this.initialRpcTimeout.hashCode();
        h *= 1000003;
        h = (int)((long)h ^ (Double.doubleToLongBits(this.rpcTimeoutMultiplier) >>> 32 ^ Double.doubleToLongBits(this.rpcTimeoutMultiplier)));
        h *= 1000003;
        return h ^= this.maxRpcTimeout.hashCode();
    }

    static final class Builder
    extends RetrySettings.Builder {
        private Duration totalTimeout;
        private Duration initialRetryDelay;
        private Double retryDelayMultiplier;
        private Duration maxRetryDelay;
        private Integer maxAttempts;
        private Boolean jittered;
        private Duration initialRpcTimeout;
        private Double rpcTimeoutMultiplier;
        private Duration maxRpcTimeout;

        Builder() {
        }

        Builder(RetrySettings source) {
            this.totalTimeout = source.getTotalTimeout();
            this.initialRetryDelay = source.getInitialRetryDelay();
            this.retryDelayMultiplier = source.getRetryDelayMultiplier();
            this.maxRetryDelay = source.getMaxRetryDelay();
            this.maxAttempts = source.getMaxAttempts();
            this.jittered = source.isJittered();
            this.initialRpcTimeout = source.getInitialRpcTimeout();
            this.rpcTimeoutMultiplier = source.getRpcTimeoutMultiplier();
            this.maxRpcTimeout = source.getMaxRpcTimeout();
        }

        @Override
        public RetrySettings.Builder setTotalTimeout(Duration totalTimeout) {
            this.totalTimeout = totalTimeout;
            return this;
        }

        @Override
        public Duration getTotalTimeout() {
            if (this.totalTimeout == null) {
                throw new IllegalStateException("Property \"totalTimeout\" has not been set");
            }
            return this.totalTimeout;
        }

        @Override
        public RetrySettings.Builder setInitialRetryDelay(Duration initialRetryDelay) {
            this.initialRetryDelay = initialRetryDelay;
            return this;
        }

        @Override
        public Duration getInitialRetryDelay() {
            if (this.initialRetryDelay == null) {
                throw new IllegalStateException("Property \"initialRetryDelay\" has not been set");
            }
            return this.initialRetryDelay;
        }

        @Override
        public RetrySettings.Builder setRetryDelayMultiplier(double retryDelayMultiplier) {
            this.retryDelayMultiplier = retryDelayMultiplier;
            return this;
        }

        @Override
        public double getRetryDelayMultiplier() {
            if (this.retryDelayMultiplier == null) {
                throw new IllegalStateException("Property \"retryDelayMultiplier\" has not been set");
            }
            return this.retryDelayMultiplier;
        }

        @Override
        public RetrySettings.Builder setMaxRetryDelay(Duration maxRetryDelay) {
            this.maxRetryDelay = maxRetryDelay;
            return this;
        }

        @Override
        public Duration getMaxRetryDelay() {
            if (this.maxRetryDelay == null) {
                throw new IllegalStateException("Property \"maxRetryDelay\" has not been set");
            }
            return this.maxRetryDelay;
        }

        @Override
        public RetrySettings.Builder setMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return this;
        }

        @Override
        public int getMaxAttempts() {
            if (this.maxAttempts == null) {
                throw new IllegalStateException("Property \"maxAttempts\" has not been set");
            }
            return this.maxAttempts;
        }

        @Override
        public RetrySettings.Builder setJittered(boolean jittered) {
            this.jittered = jittered;
            return this;
        }

        @Override
        public boolean isJittered() {
            if (this.jittered == null) {
                throw new IllegalStateException("Property \"jittered\" has not been set");
            }
            return this.jittered;
        }

        @Override
        public RetrySettings.Builder setInitialRpcTimeout(Duration initialRpcTimeout) {
            this.initialRpcTimeout = initialRpcTimeout;
            return this;
        }

        @Override
        public Duration getInitialRpcTimeout() {
            if (this.initialRpcTimeout == null) {
                throw new IllegalStateException("Property \"initialRpcTimeout\" has not been set");
            }
            return this.initialRpcTimeout;
        }

        @Override
        public RetrySettings.Builder setRpcTimeoutMultiplier(double rpcTimeoutMultiplier) {
            this.rpcTimeoutMultiplier = rpcTimeoutMultiplier;
            return this;
        }

        @Override
        public double getRpcTimeoutMultiplier() {
            if (this.rpcTimeoutMultiplier == null) {
                throw new IllegalStateException("Property \"rpcTimeoutMultiplier\" has not been set");
            }
            return this.rpcTimeoutMultiplier;
        }

        @Override
        public RetrySettings.Builder setMaxRpcTimeout(Duration maxRpcTimeout) {
            this.maxRpcTimeout = maxRpcTimeout;
            return this;
        }

        @Override
        public Duration getMaxRpcTimeout() {
            if (this.maxRpcTimeout == null) {
                throw new IllegalStateException("Property \"maxRpcTimeout\" has not been set");
            }
            return this.maxRpcTimeout;
        }

        @Override
        public RetrySettings autoBuild() {
            String missing = "";
            if (this.totalTimeout == null) {
                missing = missing + " totalTimeout";
            }
            if (this.initialRetryDelay == null) {
                missing = missing + " initialRetryDelay";
            }
            if (this.retryDelayMultiplier == null) {
                missing = missing + " retryDelayMultiplier";
            }
            if (this.maxRetryDelay == null) {
                missing = missing + " maxRetryDelay";
            }
            if (this.maxAttempts == null) {
                missing = missing + " maxAttempts";
            }
            if (this.jittered == null) {
                missing = missing + " jittered";
            }
            if (this.initialRpcTimeout == null) {
                missing = missing + " initialRpcTimeout";
            }
            if (this.rpcTimeoutMultiplier == null) {
                missing = missing + " rpcTimeoutMultiplier";
            }
            if (this.maxRpcTimeout == null) {
                missing = missing + " maxRpcTimeout";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_RetrySettings(this.totalTimeout, this.initialRetryDelay, this.retryDelayMultiplier, this.maxRetryDelay, this.maxAttempts, this.jittered, this.initialRpcTimeout, this.rpcTimeoutMultiplier, this.maxRpcTimeout);
        }
    }
}

