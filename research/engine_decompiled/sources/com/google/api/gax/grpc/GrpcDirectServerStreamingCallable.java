/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcClientCalls;
import com.google.api.gax.grpc.GrpcDirectStreamController;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;

class GrpcDirectServerStreamingCallable<RequestT, ResponseT>
extends ServerStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    GrpcDirectServerStreamingCallable(MethodDescriptor<RequestT, ResponseT> descriptor) {
        this.descriptor = Preconditions.checkNotNull(descriptor);
    }

    @Override
    public void call(RequestT request, ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        Preconditions.checkNotNull(request);
        Preconditions.checkNotNull(responseObserver);
        ClientCall<RequestT, ResponseT> call = GrpcClientCalls.newCall(this.descriptor, context);
        GrpcDirectStreamController<RequestT, ResponseT> controller = new GrpcDirectStreamController<RequestT, ResponseT>(call, responseObserver);
        controller.start(request);
    }
}

