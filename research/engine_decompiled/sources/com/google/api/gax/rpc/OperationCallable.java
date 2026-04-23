/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiExceptions;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public abstract class OperationCallable<RequestT, ResponseT, MetadataT> {
    protected OperationCallable() {
    }

    public abstract OperationFuture<ResponseT, MetadataT> futureCall(RequestT var1, ApiCallContext var2);

    public OperationFuture<ResponseT, MetadataT> futureCall(RequestT request) {
        return this.futureCall(request, null);
    }

    public ResponseT call(RequestT request, ApiCallContext context) {
        return ApiExceptions.callAndTranslateApiException(this.futureCall(request, context));
    }

    public ResponseT call(RequestT request) {
        return ApiExceptions.callAndTranslateApiException(this.futureCall(request));
    }

    public abstract OperationFuture<ResponseT, MetadataT> resumeFutureCall(String var1, ApiCallContext var2);

    public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String operationName) {
        return this.resumeFutureCall(operationName, null);
    }

    public abstract ApiFuture<Void> cancel(String var1, ApiCallContext var2);

    public ApiFuture<Void> cancel(String operationName) {
        return this.cancel(operationName, null);
    }

    public OperationCallable<RequestT, ResponseT, MetadataT> withDefaultCallContext(final ApiCallContext defaultCallContext) {
        return new OperationCallable<RequestT, ResponseT, MetadataT>(){

            @Override
            public OperationFuture<ResponseT, MetadataT> futureCall(RequestT request, ApiCallContext thisCallContext) {
                return OperationCallable.this.futureCall(request, defaultCallContext.merge(thisCallContext));
            }

            @Override
            public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String operationName, ApiCallContext thisCallContext) {
                return OperationCallable.this.resumeFutureCall(operationName, defaultCallContext.merge(thisCallContext));
            }

            @Override
            public ApiFuture<Void> cancel(String operationName, ApiCallContext thisCallContext) {
                return OperationCallable.this.cancel(operationName, defaultCallContext.merge(thisCallContext));
            }
        };
    }
}

