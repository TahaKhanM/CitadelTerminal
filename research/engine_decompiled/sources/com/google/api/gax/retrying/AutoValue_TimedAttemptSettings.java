/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedAttemptSettings;
import org.threeten.bp.Duration;

final class AutoValue_TimedAttemptSettings
extends TimedAttemptSettings {
    private final RetrySettings globalSettings;
    private final Duration retryDelay;
    private final Duration rpcTimeout;
    private final Duration randomizedRetryDelay;
    private final int attemptCount;
    private final long firstAttemptStartTimeNanos;

    private AutoValue_TimedAttemptSettings(RetrySettings globalSettings, Duration retryDelay, Duration rpcTimeout, Duration randomizedRetryDelay, int attemptCount, long firstAttemptStartTimeNanos) {
        if (globalSettings == null) {
            throw new NullPointerException("Null globalSettings");
        }
        this.globalSettings = globalSettings;
        if (retryDelay == null) {
            throw new NullPointerException("Null retryDelay");
        }
        this.retryDelay = retryDelay;
        if (rpcTimeout == null) {
            throw new NullPointerException("Null rpcTimeout");
        }
        this.rpcTimeout = rpcTimeout;
        if (randomizedRetryDelay == null) {
            throw new NullPointerException("Null randomizedRetryDelay");
        }
        this.randomizedRetryDelay = randomizedRetryDelay;
        this.attemptCount = attemptCount;
        this.firstAttemptStartTimeNanos = firstAttemptStartTimeNanos;
    }

    @Override
    public RetrySettings getGlobalSettings() {
        return this.globalSettings;
    }

    @Override
    public Duration getRetryDelay() {
        return this.retryDelay;
    }

    @Override
    public Duration getRpcTimeout() {
        return this.rpcTimeout;
    }

    @Override
    public Duration getRandomizedRetryDelay() {
        return this.randomizedRetryDelay;
    }

    @Override
    public int getAttemptCount() {
        return this.attemptCount;
    }

    @Override
    public long getFirstAttemptStartTimeNanos() {
        return this.firstAttemptStartTimeNanos;
    }

    public String toString() {
        return "TimedAttemptSettings{globalSettings=" + this.globalSettings + ", retryDelay=" + this.retryDelay + ", rpcTimeout=" + this.rpcTimeout + ", randomizedRetryDelay=" + this.randomizedRetryDelay + ", attemptCount=" + this.attemptCount + ", firstAttemptStartTimeNanos=" + this.firstAttemptStartTimeNanos + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof TimedAttemptSettings) {
            TimedAttemptSettings that = (TimedAttemptSettings)o;
            return this.globalSettings.equals(that.getGlobalSettings()) && this.retryDelay.equals(that.getRetryDelay()) && this.rpcTimeout.equals(that.getRpcTimeout()) && this.randomizedRetryDelay.equals(that.getRandomizedRetryDelay()) && this.attemptCount == that.getAttemptCount() && this.firstAttemptStartTimeNanos == that.getFirstAttemptStartTimeNanos();
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.globalSettings.hashCode();
        h *= 1000003;
        h ^= this.retryDelay.hashCode();
        h *= 1000003;
        h ^= this.rpcTimeout.hashCode();
        h *= 1000003;
        h ^= this.randomizedRetryDelay.hashCode();
        h *= 1000003;
        h ^= this.attemptCount;
        h *= 1000003;
        h = (int)((long)h ^ (this.firstAttemptStartTimeNanos >>> 32 ^ this.firstAttemptStartTimeNanos));
        return h;
    }

    static final class Builder
    extends TimedAttemptSettings.Builder {
        private RetrySettings globalSettings;
        private Duration retryDelay;
        private Duration rpcTimeout;
        private Duration randomizedRetryDelay;
        private Integer attemptCount;
        private Long firstAttemptStartTimeNanos;

        Builder() {
        }

        Builder(TimedAttemptSettings source) {
            this.globalSettings = source.getGlobalSettings();
            this.retryDelay = source.getRetryDelay();
            this.rpcTimeout = source.getRpcTimeout();
            this.randomizedRetryDelay = source.getRandomizedRetryDelay();
            this.attemptCount = source.getAttemptCount();
            this.firstAttemptStartTimeNanos = source.getFirstAttemptStartTimeNanos();
        }

        @Override
        public TimedAttemptSettings.Builder setGlobalSettings(RetrySettings globalSettings) {
            this.globalSettings = globalSettings;
            return this;
        }

        @Override
        public TimedAttemptSettings.Builder setRetryDelay(Duration retryDelay) {
            this.retryDelay = retryDelay;
            return this;
        }

        @Override
        public TimedAttemptSettings.Builder setRpcTimeout(Duration rpcTimeout) {
            this.rpcTimeout = rpcTimeout;
            return this;
        }

        @Override
        public TimedAttemptSettings.Builder setRandomizedRetryDelay(Duration randomizedRetryDelay) {
            this.randomizedRetryDelay = randomizedRetryDelay;
            return this;
        }

        @Override
        public TimedAttemptSettings.Builder setAttemptCount(int attemptCount) {
            this.attemptCount = attemptCount;
            return this;
        }

        @Override
        public TimedAttemptSettings.Builder setFirstAttemptStartTimeNanos(long firstAttemptStartTimeNanos) {
            this.firstAttemptStartTimeNanos = firstAttemptStartTimeNanos;
            return this;
        }

        @Override
        public TimedAttemptSettings build() {
            String missing = "";
            if (this.globalSettings == null) {
                missing = missing + " globalSettings";
            }
            if (this.retryDelay == null) {
                missing = missing + " retryDelay";
            }
            if (this.rpcTimeout == null) {
                missing = missing + " rpcTimeout";
            }
            if (this.randomizedRetryDelay == null) {
                missing = missing + " randomizedRetryDelay";
            }
            if (this.attemptCount == null) {
                missing = missing + " attemptCount";
            }
            if (this.firstAttemptStartTimeNanos == null) {
                missing = missing + " firstAttemptStartTimeNanos";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_TimedAttemptSettings(this.globalSettings, this.retryDelay, this.rpcTimeout, this.randomizedRetryDelay, this.attemptCount, this.firstAttemptStartTimeNanos);
        }
    }
}

