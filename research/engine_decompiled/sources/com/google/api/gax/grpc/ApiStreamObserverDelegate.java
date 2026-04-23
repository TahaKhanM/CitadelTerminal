/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiStreamObserver;
import io.grpc.stub.StreamObserver;

class ApiStreamObserverDelegate<V>
implements StreamObserver<V> {
    private final ApiStreamObserver<V> delegate;

    public ApiStreamObserverDelegate(ApiStreamObserver<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onNext(V v) {
        this.delegate.onNext(v);
    }

    @Override
    public void onError(Throwable throwable) {
        this.delegate.onError(throwable);
    }

    @Override
    public void onCompleted() {
        this.delegate.onCompleted();
    }
}

