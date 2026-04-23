/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.ListenableFutureToApiFuture;
import com.google.api.gax.retrying.CallbackChainRetryingFuture;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetryingExecutor;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledRetryingExecutor<ResponseT>
implements RetryingExecutor<ResponseT> {
    private final RetryAlgorithm<ResponseT> retryAlgorithm;
    private final ListeningScheduledExecutorService scheduler;

    public ScheduledRetryingExecutor(RetryAlgorithm<ResponseT> retryAlgorithm, ScheduledExecutorService scheduler) {
        this.retryAlgorithm = retryAlgorithm;
        this.scheduler = MoreExecutors.listeningDecorator(scheduler);
    }

    @Override
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable) {
        return new CallbackChainRetryingFuture<ResponseT>(callable, this.retryAlgorithm, this);
    }

    @Override
    public ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> retryingFuture) {
        try {
            ListenableScheduledFuture<ResponseT> attemptFuture = this.scheduler.schedule(retryingFuture.getCallable(), retryingFuture.getAttemptSettings().getRandomizedRetryDelay().toMillis(), TimeUnit.MILLISECONDS);
            return new ListenableFutureToApiFuture<ResponseT>(attemptFuture);
        }
        catch (RejectedExecutionException e) {
            return ApiFutures.immediateFailedFuture(e);
        }
    }
}

