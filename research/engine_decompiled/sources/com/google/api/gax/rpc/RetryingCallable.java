/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.retrying.RetryingExecutor;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.AttemptCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

class RetryingCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final ApiCallContext callContextPrototype;
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RetryingExecutor<ResponseT> executor;

    RetryingCallable(ApiCallContext callContextPrototype, UnaryCallable<RequestT, ResponseT> callable, RetryingExecutor<ResponseT> executor) {
        this.callContextPrototype = Preconditions.checkNotNull(callContextPrototype);
        this.callable = Preconditions.checkNotNull(callable);
        this.executor = Preconditions.checkNotNull(executor);
    }

    @Override
    public RetryingFuture<ResponseT> futureCall(RequestT request, ApiCallContext inputContext) {
        ApiCallContext context = this.callContextPrototype.nullToSelf(inputContext);
        AttemptCallable<RequestT, ResponseT> retryCallable = new AttemptCallable<RequestT, ResponseT>(this.callable, request, context);
        RetryingFuture<ResponseT> retryingFuture = this.executor.createFuture(retryCallable);
        retryCallable.setExternalFuture(retryingFuture);
        retryCallable.call();
        return retryingFuture;
    }

    public String toString() {
        return String.format("retrying(%s)", this.callable);
    }
}

