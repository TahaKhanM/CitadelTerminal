/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.core;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@InternalApi
public class ApiFutureToListenableFuture<V>
implements ListenableFuture<V> {
    private final ApiFuture<V> apiFuture;

    public ApiFutureToListenableFuture(ApiFuture<V> apiFuture) {
        this.apiFuture = apiFuture;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        this.apiFuture.addListener(listener, executor);
    }

    @Override
    public boolean cancel(boolean b) {
        return this.apiFuture.cancel(b);
    }

    @Override
    public boolean isCancelled() {
        return this.apiFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.apiFuture.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.apiFuture.get();
    }

    @Override
    public V get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.apiFuture.get(l, timeUnit);
    }
}

