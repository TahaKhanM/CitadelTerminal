/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class FutureQueue<T> {
    private final Queue<CompletableFuture<T>> producedByRequest = new ArrayDeque<CompletableFuture<T>>();
    private final Queue<CompletableFuture<T>> producedByExcess = new ArrayDeque<CompletableFuture<T>>();

    public synchronized void insert(T elem) {
        CompletableFuture<T> future = this.producedByRequest.poll();
        if (future != null) {
            future.complete(elem);
        } else {
            future = CompletableFuture.completedFuture(elem);
            this.producedByExcess.add(future);
        }
    }

    public synchronized CompletableFuture<T> remove() {
        CompletableFuture<T> future = this.producedByExcess.poll();
        if (future != null) {
            return future;
        }
        future = new CompletableFuture();
        this.producedByRequest.add(future);
        return future;
    }
}

