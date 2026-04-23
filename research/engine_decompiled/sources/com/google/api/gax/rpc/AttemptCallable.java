/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.NonCancellableFuture;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.concurrent.Callable;

class AttemptCallable<RequestT, ResponseT>
implements Callable<ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final RequestT request;
    private volatile RetryingFuture<ResponseT> externalFuture;
    private volatile ApiCallContext callContext;

    AttemptCallable(UnaryCallable<RequestT, ResponseT> callable, RequestT request, ApiCallContext callContext) {
        this.callable = callable;
        this.request = request;
        this.callContext = callContext;
    }

    public void setExternalFuture(RetryingFuture<ResponseT> externalFuture) {
        this.externalFuture = externalFuture;
    }

    @Override
    public ResponseT call() {
        try {
            if (this.callContext != null) {
                this.callContext = this.callContext.withTimeout(this.externalFuture.getAttemptSettings().getRpcTimeout());
            }
            this.externalFuture.setAttemptFuture(new NonCancellableFuture());
            if (this.externalFuture.isDone()) {
                return null;
            }
            ApiFuture<ResponseT> internalFuture = this.callable.futureCall(this.request, this.callContext);
            this.externalFuture.setAttemptFuture(internalFuture);
        }
        catch (Throwable e) {
            this.externalFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
        }
        return null;
    }
}

