/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiExceptions;

public abstract class UnaryCallable<RequestT, ResponseT> {
    protected UnaryCallable() {
    }

    public abstract ApiFuture<ResponseT> futureCall(RequestT var1, ApiCallContext var2);

    public ApiFuture<ResponseT> futureCall(RequestT request) {
        return this.futureCall(request, null);
    }

    public ResponseT call(RequestT request, ApiCallContext context) {
        return ApiExceptions.callAndTranslateApiException(this.futureCall(request, context));
    }

    public ResponseT call(RequestT request) {
        return ApiExceptions.callAndTranslateApiException(this.futureCall(request));
    }

    public UnaryCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext defaultCallContext) {
        return new UnaryCallable<RequestT, ResponseT>(){

            @Override
            public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext thisCallContext) {
                return UnaryCallable.this.futureCall(request, defaultCallContext.merge(thisCallContext));
            }
        };
    }
}

