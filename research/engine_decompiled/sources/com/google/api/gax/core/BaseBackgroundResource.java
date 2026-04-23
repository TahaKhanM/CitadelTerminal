/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.BackgroundResource;
import java.util.concurrent.TimeUnit;

public class BaseBackgroundResource
implements BackgroundResource {
    @Override
    public void shutdown() {
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public void shutdownNow() {
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void close() throws Exception {
        this.shutdown();
    }
}

