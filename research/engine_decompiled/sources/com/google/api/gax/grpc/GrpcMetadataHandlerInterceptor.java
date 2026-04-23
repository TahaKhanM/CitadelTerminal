/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.grpc.CallOptionsUtil;
import com.google.api.gax.grpc.ResponseMetadataHandler;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

@InternalApi
class GrpcMetadataHandlerInterceptor
implements ClientInterceptor {
    GrpcMetadataHandlerInterceptor() {
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
        ClientCall<ReqT, RespT> call = next2.newCall(method, callOptions);
        final ResponseMetadataHandler metadataHandler = CallOptionsUtil.getMetadataHandlerOption(callOptions);
        if (metadataHandler == null) {
            return call;
        }
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(call){

            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                ForwardingClientCallListener.SimpleForwardingClientCallListener forwardingResponseListener = new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener){

                    @Override
                    public void onHeaders(Metadata headers) {
                        super.onHeaders(headers);
                        metadataHandler.onHeaders(headers);
                    }

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        super.onClose(status, trailers);
                        metadataHandler.onTrailers(trailers);
                    }
                };
                super.start(forwardingResponseListener, headers);
            }
        };
    }
}

