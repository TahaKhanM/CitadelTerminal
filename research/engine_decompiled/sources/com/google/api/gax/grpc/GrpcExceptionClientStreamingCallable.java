/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.grpc.GrpcExceptionTranslatingStreamObserver;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

final class GrpcExceptionClientStreamingCallable<RequestT, ResponseT>
extends ClientStreamingCallable<RequestT, ResponseT> {
    private final ClientStreamingCallable<RequestT, ResponseT> innerCallable;
    private final GrpcApiExceptionFactory exceptionFactory;

    GrpcExceptionClientStreamingCallable(ClientStreamingCallable<RequestT, ResponseT> innerCallable, Set<StatusCode.Code> retryableCodes) {
        this.innerCallable = innerCallable;
        this.exceptionFactory = new GrpcApiExceptionFactory(retryableCodes);
    }

    @Override
    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> responseObserver, ApiCallContext context) {
        GrpcExceptionTranslatingStreamObserver<ResponseT> innerObserver = new GrpcExceptionTranslatingStreamObserver<ResponseT>(responseObserver, this.exceptionFactory);
        return this.innerCallable.clientStreamingCall(innerObserver, context);
    }
}

