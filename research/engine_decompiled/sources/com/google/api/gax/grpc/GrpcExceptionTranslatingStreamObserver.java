/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.rpc.ApiStreamObserver;

final class GrpcExceptionTranslatingStreamObserver<ResponseT>
implements ApiStreamObserver<ResponseT> {
    private final ApiStreamObserver<ResponseT> innerObserver;
    private final GrpcApiExceptionFactory exceptionFactory;

    GrpcExceptionTranslatingStreamObserver(ApiStreamObserver<ResponseT> innerObserver, GrpcApiExceptionFactory exceptionFactory) {
        this.innerObserver = innerObserver;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public void onNext(ResponseT value) {
        this.innerObserver.onNext(value);
    }

    @Override
    public void onError(Throwable t) {
        this.innerObserver.onError(this.exceptionFactory.create(t));
    }

    @Override
    public void onCompleted() {
        this.innerObserver.onCompleted();
    }
}

