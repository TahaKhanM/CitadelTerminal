/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.RequestParamsExtractor;
import com.google.api.gax.rpc.RequestUrlParamsEncoder;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

class GrpcUnaryRequestParamCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RequestUrlParamsEncoder<RequestT> paramsEncoder;

    GrpcUnaryRequestParamCallable(UnaryCallable<RequestT, ResponseT> callable, RequestParamsExtractor<RequestT> paramsExtractor) {
        this.callable = Preconditions.checkNotNull(callable);
        this.paramsEncoder = new RequestUrlParamsEncoder<RequestT>(Preconditions.checkNotNull(paramsExtractor), false);
    }

    @Override
    public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext inputContext) {
        GrpcCallContext newCallContext = GrpcCallContext.createDefault().nullToSelf(inputContext).withRequestParamsDynamicHeaderOption(this.paramsEncoder.encode(request));
        return this.callable.futureCall(request, newCallContext);
    }
}

