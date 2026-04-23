/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.BackgroundResource;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorAsBackgroundResource
implements BackgroundResource {
    private final ExecutorService executor;

    public ExecutorAsBackgroundResource(ExecutorService executor) {
        this.executor = Preconditions.checkNotNull(executor);
    }

    @Override
    public void shutdown() {
        this.executor.shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.executor.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.executor.isTerminated();
    }

    @Override
    public void shutdownNow() {
        this.executor.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return this.executor.awaitTermination(duration, unit);
    }

    @Override
    public void close() throws Exception {
        this.shutdown();
    }
}

