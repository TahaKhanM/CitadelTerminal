/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.ExecutorProvider;
import java.util.concurrent.ScheduledExecutorService;

public final class FixedExecutorProvider
implements ExecutorProvider {
    private final ScheduledExecutorService executor;

    private FixedExecutorProvider(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public ScheduledExecutorService getExecutor() {
        return this.executor;
    }

    @Override
    public boolean shouldAutoClose() {
        return false;
    }

    public static FixedExecutorProvider create(ScheduledExecutorService executor) {
        return new FixedExecutorProvider(executor);
    }
}

