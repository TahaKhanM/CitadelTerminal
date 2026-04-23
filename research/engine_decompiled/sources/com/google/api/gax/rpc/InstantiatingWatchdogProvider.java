/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.Watchdog;
import com.google.api.gax.rpc.WatchdogProvider;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public final class InstantiatingWatchdogProvider
implements WatchdogProvider {
    @Nullable
    private final ApiClock clock;
    @Nullable
    private final ScheduledExecutorService executor;
    @Nullable
    private final Duration checkInterval;

    public static WatchdogProvider create() {
        return new InstantiatingWatchdogProvider(null, null, null);
    }

    private InstantiatingWatchdogProvider(@Nullable ApiClock clock, @Nullable ScheduledExecutorService executor, @Nullable Duration checkInterval) {
        this.clock = clock;
        this.executor = executor;
        this.checkInterval = checkInterval;
    }

    @Override
    public boolean needsClock() {
        return this.clock == null;
    }

    @Override
    public WatchdogProvider withClock(@Nonnull ApiClock clock) {
        return new InstantiatingWatchdogProvider(Preconditions.checkNotNull(clock), this.executor, this.checkInterval);
    }

    @Override
    public boolean needsCheckInterval() {
        return this.checkInterval == null;
    }

    @Override
    public WatchdogProvider withCheckInterval(@Nonnull Duration checkInterval) {
        return new InstantiatingWatchdogProvider(this.clock, this.executor, Preconditions.checkNotNull(checkInterval));
    }

    @Override
    public boolean needsExecutor() {
        return this.executor == null;
    }

    @Override
    public WatchdogProvider withExecutor(ScheduledExecutorService executor) {
        return new InstantiatingWatchdogProvider(this.clock, Preconditions.checkNotNull(executor), this.checkInterval);
    }

    @Override
    @Nullable
    public Watchdog getWatchdog() {
        Preconditions.checkState(!this.needsClock(), "A clock is needed");
        Preconditions.checkState(!this.needsCheckInterval(), "A check interval is needed");
        Preconditions.checkState(!this.needsExecutor(), "An executor is needed");
        if (this.checkInterval.isZero()) {
            return null;
        }
        Watchdog watchdog = new Watchdog(this.clock);
        this.executor.scheduleAtFixedRate(watchdog, this.checkInterval.toMillis(), this.checkInterval.toMillis(), TimeUnit.MILLISECONDS);
        return watchdog;
    }
}

