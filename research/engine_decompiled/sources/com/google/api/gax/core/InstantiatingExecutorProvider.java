/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.AutoValue_InstantiatingExecutorProvider;
import com.google.api.gax.core.ExecutorProvider;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class InstantiatingExecutorProvider
implements ExecutorProvider {
    private static final int DEFAULT_EXECUTOR_THREADS = 4;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory(){
        private final AtomicInteger threadCount = new AtomicInteger();

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("Gax-" + this.threadCount.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    };

    InstantiatingExecutorProvider() {
    }

    @Override
    public ScheduledExecutorService getExecutor() {
        return new ScheduledThreadPoolExecutor(this.getExecutorThreadCount(), this.getThreadFactory());
    }

    @Override
    public boolean shouldAutoClose() {
        return true;
    }

    public abstract int getExecutorThreadCount();

    public abstract ThreadFactory getThreadFactory();

    public Builder toBuilder() {
        return new AutoValue_InstantiatingExecutorProvider.Builder(this);
    }

    public static Builder newBuilder() {
        return new AutoValue_InstantiatingExecutorProvider.Builder().setExecutorThreadCount(4).setThreadFactory(DEFAULT_THREAD_FACTORY);
    }

    public static abstract class Builder {
        public abstract Builder setExecutorThreadCount(int var1);

        public abstract int getExecutorThreadCount();

        public abstract Builder setThreadFactory(ThreadFactory var1);

        public abstract ThreadFactory getThreadFactory();

        public abstract InstantiatingExecutorProvider build();
    }
}

