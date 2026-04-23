/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.Watchdog;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import org.threeten.bp.Duration;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public interface WatchdogProvider {
    public boolean needsClock();

    public WatchdogProvider withClock(@Nonnull ApiClock var1);

    public boolean needsCheckInterval();

    public WatchdogProvider withCheckInterval(Duration var1);

    public boolean needsExecutor();

    public WatchdogProvider withExecutor(ScheduledExecutorService var1);

    public Watchdog getWatchdog();
}

