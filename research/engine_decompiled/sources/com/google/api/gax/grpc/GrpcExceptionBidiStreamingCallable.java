/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.ExceptionResponseObserver;
import com.google.api.gax.grpc.GrpcApiExceptionFactory;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StatusCode;
import java.util.Set;

final class GrpcExceptionBidiStreamingCallable<RequestT, ResponseT>
extends BidiStreamingCallable<RequestT, ResponseT> {
    private final BidiStreamingCallable<RequestT, ResponseT> innerCallable;
    private final GrpcApiExceptionFactory exceptionFactory;

    GrpcExceptionBidiStreamingCallable(BidiStreamingCallable<RequestT, ResponseT> innerCallable, Set<StatusCode.Code> retryableCodes) {
        this.innerCallable = innerCallable;
        this.exceptionFactory = new GrpcApiExceptionFactory(retryableCodes);
    }

    @Override
    public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> onReady, ApiCallContext context) {
        return this.innerCallable.internalCall(new ExceptionResponseObserver(responseObserver, this.exceptionFactory), onReady, context);
    }
}

