/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcClientCalls;
import com.google.api.gax.grpc.GrpcDirectStreamController;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ClientStreamReadyObserver;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.common.base.Preconditions;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;

class GrpcDirectBidiStreamingCallable<RequestT, ResponseT>
extends BidiStreamingCallable<RequestT, ResponseT> {
    private final MethodDescriptor<RequestT, ResponseT> descriptor;

    GrpcDirectBidiStreamingCallable(MethodDescriptor<RequestT, ResponseT> descriptor) {
        this.descriptor = Preconditions.checkNotNull(descriptor);
    }

    @Override
    public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, final ClientStreamReadyObserver<RequestT> onReady, ApiCallContext context) {
        Preconditions.checkNotNull(responseObserver);
        final ClientCall<RequestT, ResponseT> call = GrpcClientCalls.newCall(this.descriptor, context);
        final ClientStream clientStream = new ClientStream<RequestT>(){

            @Override
            public void send(RequestT request) {
                call.sendMessage(request);
            }

            @Override
            public void closeSendWithError(Throwable t) {
                call.cancel(null, t);
            }

            @Override
            public void closeSend() {
                call.halfClose();
            }

            @Override
            public boolean isSendReady() {
                return call.isReady();
            }
        };
        GrpcDirectStreamController<RequestT, ResponseT> controller = new GrpcDirectStreamController<RequestT, ResponseT>(call, responseObserver, new Runnable(){

            @Override
            public void run() {
                onReady.onReady(clientStream);
            }
        });
        controller.startBidi();
        return clientStream;
    }
}

