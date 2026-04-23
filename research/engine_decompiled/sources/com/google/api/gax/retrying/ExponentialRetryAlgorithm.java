/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiClock;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.common.base.Preconditions;
import java.util.concurrent.ThreadLocalRandom;
import org.threeten.bp.Duration;

public class ExponentialRetryAlgorithm
implements TimedRetryAlgorithm {
    private final RetrySettings globalSettings;
    private final ApiClock clock;

    public ExponentialRetryAlgorithm(RetrySettings globalSettings, ApiClock clock) {
        this.globalSettings = Preconditions.checkNotNull(globalSettings);
        this.clock = Preconditions.checkNotNull(clock);
    }

    @Override
    public TimedAttemptSettings createFirstAttempt() {
        return TimedAttemptSettings.newBuilder().setGlobalSettings(this.globalSettings).setRetryDelay(Duration.ZERO).setRpcTimeout(this.globalSettings.getTotalTimeout()).setRandomizedRetryDelay(Duration.ZERO).setAttemptCount(0).setFirstAttemptStartTimeNanos(this.clock.nanoTime()).build();
    }

    @Override
    public TimedAttemptSettings createNextAttempt(TimedAttemptSettings prevSettings) {
        RetrySettings settings = prevSettings.getGlobalSettings();
        long newRetryDelay = settings.getInitialRetryDelay().toMillis();
        long newRpcTimeout = settings.getInitialRpcTimeout().toMillis();
        if (prevSettings.getAttemptCount() > 0) {
            newRetryDelay = (long)(settings.getRetryDelayMultiplier() * (double)prevSettings.getRetryDelay().toMillis());
            newRetryDelay = Math.min(newRetryDelay, settings.getMaxRetryDelay().toMillis());
            newRpcTimeout = (long)(settings.getRpcTimeoutMultiplier() * (double)prevSettings.getRpcTimeout().toMillis());
            newRpcTimeout = Math.min(newRpcTimeout, settings.getMaxRpcTimeout().toMillis());
        }
        return TimedAttemptSettings.newBuilder().setGlobalSettings(prevSettings.getGlobalSettings()).setRetryDelay(Duration.ofMillis(newRetryDelay)).setRpcTimeout(Duration.ofMillis(newRpcTimeout)).setRandomizedRetryDelay(Duration.ofMillis(this.nextRandomLong(newRetryDelay))).setAttemptCount(prevSettings.getAttemptCount() + 1).setFirstAttemptStartTimeNanos(prevSettings.getFirstAttemptStartTimeNanos()).build();
    }

    @Override
    public boolean shouldRetry(TimedAttemptSettings nextAttemptSettings) {
        RetrySettings globalSettings = nextAttemptSettings.getGlobalSettings();
        long totalTimeSpentNanos = this.clock.nanoTime() - nextAttemptSettings.getFirstAttemptStartTimeNanos() + nextAttemptSettings.getRandomizedRetryDelay().toNanos();
        return totalTimeSpentNanos <= globalSettings.getTotalTimeout().toNanos() && (globalSettings.getMaxAttempts() <= 0 || nextAttemptSettings.getAttemptCount() < globalSettings.getMaxAttempts());
    }

    protected long nextRandomLong(long bound) {
        return bound > 0L && this.globalSettings.isJittered() ? ThreadLocalRandom.current().nextLong(bound) : bound;
    }
}

