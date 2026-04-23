/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ForwardingApiFuture<T>
implements ApiFuture<T> {
    private final ApiFuture<T> delegate;

    public ForwardingApiFuture(ApiFuture<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.delegate.cancel(mayInterruptIfRunning);
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return (T)this.delegate.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T)this.delegate.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.delegate.isDone();
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }
}

