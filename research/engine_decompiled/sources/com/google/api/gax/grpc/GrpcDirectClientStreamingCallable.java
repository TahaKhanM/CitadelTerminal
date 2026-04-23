/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.ApiStreamObserverDelegate;
import com.google.api.gax.grpc.GrpcClientCalls;
import com.google.api.gax.grpc.StreamObserverDelegate;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

class GrpcDirectClientStreamingCallable<RequestT, ResponseT>
extends ClientStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    GrpcDirectClientStreamingCallable(MethodDescriptor<RequestT, ResponseT> descriptor) {
        this.descriptor = Preconditions.checkNotNull(descriptor);
    }

    @Override
    public ApiStreamObserver<RequestT> clientStreamingCall(ApiStreamObserver<ResponseT> responseObserver, ApiCallContext context) {
        Preconditions.checkNotNull(responseObserver);
        ClientCall<RequestT, ResponseT> call = GrpcClientCalls.newCall(this.descriptor, context);
        return new StreamObserverDelegate<RequestT>(ClientCalls.asyncClientStreamingCall(call, new ApiStreamObserverDelegate<ResponseT>(responseObserver)));
    }
}

