/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.ExceptionResponseObserver;
import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

class GrpcExceptionServerStreamingCallable<RequestT, ResponseT>
extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> inner;
    private final GrpcApiExceptionFactory exceptionFactory;

    public GrpcExceptionServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> inner2, Set<StatusCode.Code> retryableCodes) {
        this.inner = inner2;
        this.exceptionFactory = new GrpcApiExceptionFactory(retryableCodes);
    }

    @Override
    public void call(RequestT request, ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        this.inner.call(request, new ExceptionResponseObserver(responseObserver, this.exceptionFactory), context);
    }
}

