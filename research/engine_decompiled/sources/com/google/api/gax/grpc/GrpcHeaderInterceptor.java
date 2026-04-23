/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.grpc.CallOptionsUtil;
import com.google.common.collect.ImmutableMap;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.internal.GrpcUtil;
import java.util.Map;

@InternalApi
public class GrpcHeaderInterceptor
implements ClientInterceptor {
    private final Map<Metadata.Key<String>, String> staticHeaders;
    private final String userAgentHeader;

    public GrpcHeaderInterceptor(Map<String, String> staticHeaders) {
        ImmutableMap.Builder<Metadata.Key<String>, String> grpcHeaders = ImmutableMap.builder();
        String userAgentStaticHeader = null;
        for (Map.Entry<String, String> header : staticHeaders.entrySet()) {
            Metadata.Key<String> headerKey = Metadata.Key.of(header.getKey(), Metadata.ASCII_STRING_MARSHALLER);
            if (headerKey.equals(GrpcUtil.USER_AGENT_KEY)) {
                userAgentStaticHeader = header.getValue();
                continue;
            }
            grpcHeaders.put(headerKey, header.getValue());
        }
        this.staticHeaders = grpcHeaders.build();
        this.userAgentHeader = userAgentStaticHeader;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, final CallOptions callOptions, Channel next2) {
        ClientCall<ReqT, RespT> call = next2.newCall(method, callOptions);
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(call){

            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                for (Map.Entry staticHeader : GrpcHeaderInterceptor.this.staticHeaders.entrySet()) {
                    headers.put((Metadata.Key)staticHeader.getKey(), staticHeader.getValue());
                }
                Map<Metadata.Key<String>, String> dynamicHeaders = CallOptionsUtil.getDynamicHeadersOption(callOptions);
                for (Map.Entry<Metadata.Key<String>, String> dynamicHeader : dynamicHeaders.entrySet()) {
                    headers.put(dynamicHeader.getKey(), dynamicHeader.getValue());
                }
                super.start(responseListener, headers);
            }
        };
    }

    public String getUserAgentHeader() {
        return this.userAgentHeader;
    }
}

