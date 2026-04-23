/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.gax.retrying.RetryingExecutor;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.CheckingAttemptCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

class RecheckingCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RetryingExecutor<ResponseT> executor;

    RecheckingCallable(UnaryCallable<RequestT, ResponseT> callable, RetryingExecutor<ResponseT> executor) {
        this.callable = Preconditions.checkNotNull(callable);
        this.executor = Preconditions.checkNotNull(executor);
    }

    @Override
    public RetryingFuture<ResponseT> futureCall(RequestT request, ApiCallContext inputContext) {
        CheckingAttemptCallable<RequestT, ResponseT> checkingAttemptCallable = new CheckingAttemptCallable<RequestT, ResponseT>(this.callable);
        RetryingFuture<ResponseT> retryingFuture = this.executor.createFuture(checkingAttemptCallable);
        checkingAttemptCallable.setExternalFuture(retryingFuture);
        checkingAttemptCallable.call();
        return retryingFuture;
    }

    public String toString() {
        return String.format("rechecking(%s)", this.callable);
    }
}

