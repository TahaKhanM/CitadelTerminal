/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.util.concurrent;

import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ProgressivePromise;

public class DefaultProgressivePromise<V>
extends DefaultPromise<V>
implements ProgressivePromise<V> {
    public DefaultProgressivePromise(EventExecutor executor) {
        super(executor);
    }

    protected DefaultProgressivePromise() {
    }

    @Override
    public ProgressivePromise<V> setProgress(long progress, long total2) {
        if (total2 < 0L) {
            total2 = -1L;
            if (progress < 0L) {
                throw new IllegalArgumentException("progress: " + progress + " (expected: >= 0)");
            }
        } else if (progress < 0L || progress > total2) {
            throw new IllegalArgumentException("progress: " + progress + " (expected: 0 <= progress <= total (" + total2 + "))");
        }
        if (this.isDone()) {
            throw new IllegalStateException("complete already");
        }
        this.notifyProgressiveListeners(progress, total2);
        return this;
    }

    @Override
    public boolean tryProgress(long progress, long total2) {
        if (total2 < 0L) {
            total2 = -1L;
            if (progress < 0L || this.isDone()) {
                return false;
            }
        } else if (progress < 0L || progress > total2 || this.isDone()) {
            return false;
        }
        this.notifyProgressiveListeners(progress, total2);
        return true;
    }

    @Override
    public ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> listener) {
        super.addListener((GenericFutureListener)listener);
        return this;
    }

    @Override
    public ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>> ... listeners) {
        super.addListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> listener) {
        super.removeListener((GenericFutureListener)listener);
        return this;
    }

    @Override
    public ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>> ... listeners) {
        super.removeListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ProgressivePromise<V> sync() throws InterruptedException {
        super.sync();
        return this;
    }

    @Override
    public ProgressivePromise<V> syncUninterruptibly() {
        super.syncUninterruptibly();
        return this;
    }

    @Override
    public ProgressivePromise<V> await() throws InterruptedException {
        super.await();
        return this;
    }

    @Override
    public ProgressivePromise<V> awaitUninterruptibly() {
        super.awaitUninterruptibly();
        return this;
    }

    @Override
    public ProgressivePromise<V> setSuccess(V result2) {
        super.setSuccess(result2);
        return this;
    }

    @Override
    public ProgressivePromise<V> setFailure(Throwable cause) {
        super.setFailure(cause);
        return this;
    }
}

