/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.core;

import com.google.api.gax.core.InstantiatingExecutorProvider;
import java.util.concurrent.ThreadFactory;

final class AutoValue_InstantiatingExecutorProvider
extends InstantiatingExecutorProvider {
    private final int executorThreadCount;
    private final ThreadFactory threadFactory;

    private AutoValue_InstantiatingExecutorProvider(int executorThreadCount, ThreadFactory threadFactory) {
        this.executorThreadCount = executorThreadCount;
        if (threadFactory == null) {
            throw new NullPointerException("Null threadFactory");
        }
        this.threadFactory = threadFactory;
    }

    @Override
    public int getExecutorThreadCount() {
        return this.executorThreadCount;
    }

    @Override
    public ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    public String toString() {
        return "InstantiatingExecutorProvider{executorThreadCount=" + this.executorThreadCount + ", threadFactory=" + this.threadFactory + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof InstantiatingExecutorProvider) {
            InstantiatingExecutorProvider that = (InstantiatingExecutorProvider)o;
            return this.executorThreadCount == that.getExecutorThreadCount() && this.threadFactory.equals(that.getThreadFactory());
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.executorThreadCount;
        h *= 1000003;
        return h ^= this.threadFactory.hashCode();
    }

    static final class Builder
    extends InstantiatingExecutorProvider.Builder {
        private Integer executorThreadCount;
        private ThreadFactory threadFactory;

        Builder() {
        }

        Builder(InstantiatingExecutorProvider source) {
            this.executorThreadCount = source.getExecutorThreadCount();
            this.threadFactory = source.getThreadFactory();
        }

        @Override
        public InstantiatingExecutorProvider.Builder setExecutorThreadCount(int executorThreadCount) {
            this.executorThreadCount = executorThreadCount;
            return this;
        }

        @Override
        public int getExecutorThreadCount() {
            if (this.executorThreadCount == null) {
                throw new IllegalStateException("Property \"executorThreadCount\" has not been set");
            }
            return this.executorThreadCount;
        }

        @Override
        public InstantiatingExecutorProvider.Builder setThreadFactory(ThreadFactory threadFactory) {
            this.threadFactory = threadFactory;
            return this;
        }

        @Override
        public ThreadFactory getThreadFactory() {
            if (this.threadFactory == null) {
                throw new IllegalStateException("Property \"threadFactory\" has not been set");
            }
            return this.threadFactory;
        }

        @Override
        public InstantiatingExecutorProvider build() {
            String missing = "";
            if (this.executorThreadCount == null) {
                missing = missing + " executorThreadCount";
            }
            if (this.threadFactory == null) {
                missing = missing + " threadFactory";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_InstantiatingExecutorProvider(this.executorThreadCount, this.threadFactory);
        }
    }
}

