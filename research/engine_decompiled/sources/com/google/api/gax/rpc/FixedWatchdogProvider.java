/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.Watchdog;
import com.google.api.gax.rpc.WatchdogProvider;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public class FixedWatchdogProvider
implements WatchdogProvider {
    @Nullable
    private final Watchdog watchdog;

    public static WatchdogProvider create(Watchdog watchdog) {
        return new FixedWatchdogProvider(watchdog);
    }

    private FixedWatchdogProvider(Watchdog watchdog) {
        this.watchdog = watchdog;
    }

    @Override
    public boolean needsClock() {
        return false;
    }

    @Override
    public WatchdogProvider withClock(@Nonnull ApiClock clock) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need a clock");
    }

    @Override
    public boolean needsCheckInterval() {
        return false;
    }

    @Override
    public WatchdogProvider withCheckInterval(Duration checkInterval) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need a checkInterval");
    }

    @Override
    public boolean needsExecutor() {
        return false;
    }

    @Override
    public WatchdogProvider withExecutor(ScheduledExecutorService executor) {
        throw new UnsupportedOperationException("FixedWatchdogProvider doesn't need an executor");
    }

    @Override
    public Watchdog getWatchdog() {
        return this.watchdog;
    }
}

