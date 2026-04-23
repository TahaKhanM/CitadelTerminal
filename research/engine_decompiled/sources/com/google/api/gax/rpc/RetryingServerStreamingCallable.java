/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.ScheduledRetryingExecutor;
import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingAttemptCallable;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.common.util.concurrent.MoreExecutors;

final class RetryingServerStreamingCallable<RequestT, ResponseT>
extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> innerCallable;
    private final ScheduledRetryingExecutor<Void> executor;
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategyPrototype;

    RetryingServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> innerCallable, ScheduledRetryingExecutor<Void> executor, StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategyPrototype) {
        this.innerCallable = innerCallable;
        this.executor = executor;
        this.resumptionStrategyPrototype = resumptionStrategyPrototype;
    }

    @Override
    public void call(RequestT request, final ResponseObserver<ResponseT> responseObserver, ApiCallContext context) {
        ServerStreamingAttemptCallable<RequestT, ResponseT> attemptCallable = new ServerStreamingAttemptCallable<RequestT, ResponseT>(this.innerCallable, this.resumptionStrategyPrototype.createNew(), request, context, responseObserver);
        RetryingFuture<Void> retryingFuture = this.executor.createFuture(attemptCallable);
        attemptCallable.setExternalFuture(retryingFuture);
        attemptCallable.start();
        ApiFutures.addCallback(retryingFuture, new ApiFutureCallback<Void>(){

            @Override
            public void onFailure(Throwable throwable) {
                if (throwable instanceof ServerStreamingAttemptException) {
                    throwable = throwable.getCause();
                }
                responseObserver.onError(throwable);
            }

            @Override
            public void onSuccess(Void ignored) {
                responseObserver.onComplete();
            }
        }, MoreExecutors.directExecutor());
    }
}

