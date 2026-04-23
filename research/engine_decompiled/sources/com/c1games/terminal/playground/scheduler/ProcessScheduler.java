/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.playground.scheduler;

import com.c1games.terminal.playground.scheduler.PausableProcess;
import com.c1games.terminal.playground.scheduler.PausableProcessTask;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public interface ProcessScheduler {
    public <T> CompletableFuture<T> submit(PausableProcess var1, PausableProcessTask<T> var2, Duration var3);
}

