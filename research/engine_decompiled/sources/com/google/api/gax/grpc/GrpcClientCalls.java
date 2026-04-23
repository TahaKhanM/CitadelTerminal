/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.client.util.Preconditions;
import com.google.api.gax.grpc.ChannelPool;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.rpc.ApiCallContext;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.MethodDescriptor;
import io.grpc.stub.MetadataUtils;

class GrpcClientCalls {
    private GrpcClientCalls() {
    }

    public static <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> descriptor, ApiCallContext context) {
        if (!(context instanceof GrpcCallContext)) {
            throw new IllegalArgumentException("context must be an instance of GrpcCallContext, but found " + context.getClass().getName());
        }
        GrpcCallContext grpcContext = (GrpcCallContext)context;
        Preconditions.checkNotNull(grpcContext.getChannel());
        CallOptions callOptions = grpcContext.getCallOptions();
        Preconditions.checkNotNull(callOptions);
        Channel channel = grpcContext.getChannel();
        if (grpcContext.getChannelAffinity() != null && channel instanceof ChannelPool) {
            channel = ((ChannelPool)channel).getChannel(grpcContext.getChannelAffinity());
        }
        if (!grpcContext.getExtraHeaders().isEmpty()) {
            ClientInterceptor interceptor = MetadataUtils.newAttachHeadersInterceptor(grpcContext.getMetadata());
            channel = ClientInterceptors.intercept(channel, interceptor);
        }
        return channel.newCall(descriptor, callOptions);
    }
}

