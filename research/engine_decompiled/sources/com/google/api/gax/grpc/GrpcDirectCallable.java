/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.api.gax.grpc.GrpcClientCalls;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

class GrpcDirectCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    GrpcDirectCallable(MethodDescriptor<RequestT, ResponseT> descriptor) {
        this.descriptor = Preconditions.checkNotNull(descriptor);
    }

    @Override
    public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext inputContext) {
        Preconditions.checkNotNull(request);
        Preconditions.checkNotNull(inputContext);
        return new ListenableFutureToApiFuture<ResponseT>(ClientCalls.futureUnaryCall(GrpcClientCalls.newCall(this.descriptor, inputContext), request));
    }

    public String toString() {
        return String.format("direct(%s)", this.descriptor);
    }
}

