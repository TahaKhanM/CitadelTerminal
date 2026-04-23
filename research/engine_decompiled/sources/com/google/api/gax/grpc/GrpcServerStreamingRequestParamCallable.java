/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.RequestUrlParamsEncoder;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.common.base.Preconditions;

class GrpcServerStreamingRequestParamCallable<RequestT, ResponseT>
extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> callable;
    private final RequestUrlParamsEncoder<RequestT> paramsEncoder;

    GrpcServerStreamingRequestParamCallable(ServerStreamingCallable<RequestT, ResponseT> callable, RequestParamsExtractor<RequestT> paramsExtractor) {
        this.callable = Preconditions.checkNotNull(callable);
        this.paramsEncoder = new RequestUrlParamsEncoder<RequestT>(Preconditions.checkNotNull(paramsExtractor), false);
    }

    @Override
    public void call(RequestT request, ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        this.callable.call(request, responseObserver, this.contextWithParamsEncoder(request, context));
    }

    private ApiCallContext contextWithParamsEncoder(RequestT request, ApiCallContext inputContext) {
        return GrpcCallContext.createDefault().nullToSelf(inputContext).withRequestParamsDynamicHeaderOption(this.paramsEncoder.encode(request));
    }
}

