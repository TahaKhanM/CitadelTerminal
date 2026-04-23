/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;

@Deprecated
class ApiStreamObserverAdapter<T>
extends StateCheckingResponseObserver<T> {
    private final ApiStreamObserver<T> delegate;

    ApiStreamObserverAdapter(ApiStreamObserver<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onStartImpl(StreamController controller) {
    }

    @Override
    protected void onResponseImpl(T response) {
        this.delegate.onNext(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
        this.delegate.onError(t);
    }

    @Override
    protected void onCompleteImpl() {
        this.delegate.onCompleted();
    }
}

