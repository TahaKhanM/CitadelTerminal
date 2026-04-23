/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import java.util.concurrent.ScheduledExecutorService;

public interface ExecutorProvider {
    public boolean shouldAutoClose();

    public ScheduledExecutorService getExecutor();
}

