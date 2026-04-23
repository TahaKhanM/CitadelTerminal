/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import java.util.concurrent.TimeUnit;

public interface BackgroundResource
extends AutoCloseable {
    public void shutdown();

    public boolean isShutdown();

    public boolean isTerminated();

    public void shutdownNow();

    public boolean awaitTermination(long var1, TimeUnit var3) throws InterruptedException;
}

