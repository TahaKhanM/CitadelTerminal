/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.NonCancellableFuture;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.concurrent.Callable;

class CheckingAttemptCallable<RequestT, ResponseT>
implements Callable<ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private volatile RetryingFuture<ResponseT> externalFuture;

    CheckingAttemptCallable(UnaryCallable<RequestT, ResponseT> callable) {
        this.callable = callable;
    }

    public void setExternalFuture(RetryingFuture<ResponseT> externalFuture) {
        this.externalFuture = externalFuture;
    }

    @Override
    public ResponseT call() {
        try {
            this.externalFuture.setAttemptFuture(new NonCancellableFuture());
            if (this.externalFuture.isDone()) {
                return null;
            }
            ApiFuture<ResponseT> internalFuture = this.callable.futureCall(null, null);
            this.externalFuture.setAttemptFuture(internalFuture);
        }
        catch (Throwable e) {
            this.externalFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
        }
        return null;
    }
}

