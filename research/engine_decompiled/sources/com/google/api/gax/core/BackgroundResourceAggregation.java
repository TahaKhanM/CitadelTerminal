/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.BackgroundResource;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BackgroundResourceAggregation
implements BackgroundResource {
    private final List<BackgroundResource> resources;

    public BackgroundResourceAggregation(List<BackgroundResource> resources) {
        this.resources = resources;
    }

    @Override
    public void shutdown() {
        for (BackgroundResource resource : this.resources) {
            resource.shutdown();
        }
    }

    @Override
    public boolean isShutdown() {
        for (BackgroundResource resource : this.resources) {
            if (resource.isShutdown()) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean isTerminated() {
        for (BackgroundResource resource : this.resources) {
            if (resource.isTerminated()) continue;
            return false;
        }
        return true;
    }

    @Override
    public void shutdownNow() {
        for (BackgroundResource resource : this.resources) {
            resource.shutdownNow();
        }
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        for (BackgroundResource resource : this.resources) {
            boolean awaitResult = resource.awaitTermination(duration, unit);
            if (awaitResult) continue;
            return false;
        }
        return true;
    }

    @Override
    public final void close() throws Exception {
        this.shutdown();
    }
}

