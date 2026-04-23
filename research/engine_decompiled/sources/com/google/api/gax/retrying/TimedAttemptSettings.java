/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.AutoValue_TimedAttemptSettings;
import com.google.api.gax.retrying.RetrySettings;
import org.threeten.bp.Duration;

@BetaApi
public abstract class TimedAttemptSettings {
    public abstract RetrySettings getGlobalSettings();

    public abstract Duration getRetryDelay();

    public abstract Duration getRpcTimeout();

    public abstract Duration getRandomizedRetryDelay();

    public abstract int getAttemptCount();

    public abstract long getFirstAttemptStartTimeNanos();

    public Builder toBuilder() {
        return new AutoValue_TimedAttemptSettings.Builder(this);
    }

    public static Builder newBuilder() {
        return new AutoValue_TimedAttemptSettings.Builder();
    }

    public static abstract class Builder {
        public abstract Builder setGlobalSettings(RetrySettings var1);

        public abstract Builder setRetryDelay(Duration var1);

        public abstract Builder setRpcTimeout(Duration var1);

        public abstract Builder setRandomizedRetryDelay(Duration var1);

        public abstract Builder setAttemptCount(int var1);

        public abstract Builder setFirstAttemptStartTimeNanos(long var1);

        public abstract TimedAttemptSettings build();
    }
}

