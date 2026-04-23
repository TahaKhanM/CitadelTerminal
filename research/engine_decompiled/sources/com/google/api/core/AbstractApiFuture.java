/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiFuture;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public abstract class AbstractApiFuture<V>
implements ApiFuture<V> {
    private final InternalSettableFuture impl = new InternalSettableFuture();

    @Override
    public void addListener(Runnable listener, Executor executor) {
        this.impl.addListener(listener, executor);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.impl.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.impl.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.impl.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return this.impl.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.impl.isDone();
    }

    protected boolean set(V value) {
        return this.impl.set(value);
    }

    protected boolean setException(Throwable throwable) {
        return this.impl.setException(throwable);
    }

    protected void interruptTask() {
    }

    ListenableFuture<V> getInternalListenableFuture() {
        return this.impl;
    }

    private class InternalSettableFuture
    extends AbstractFuture<V> {
        private InternalSettableFuture() {
        }

        @Override
        protected boolean set(@Nullable V value) {
            return super.set(value);
        }

        @Override
        protected boolean setException(Throwable throwable) {
            return super.setException(throwable);
        }

        @Override
        protected void interruptTask() {
            AbstractApiFuture.this.interruptTask();
        }
    }
}

