/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.DirectRetryingExecutor;
import com.google.api.gax.retrying.ExponentialPollAlgorithm;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.ResultRetryAlgorithm;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@BetaApi
public class RetryHelper {
    public static <V> V runWithRetries(Callable<V> callable, RetrySettings retrySettings, ResultRetryAlgorithm<?> resultRetryAlgorithm, ApiClock clock) throws RetryHelperException {
        try {
            ResultRetryAlgorithm<?> algorithm = resultRetryAlgorithm;
            return RetryHelper.run(callable, new ExponentialRetryAlgorithm(retrySettings, clock), algorithm);
        }
        catch (Exception e) {
            throw new RetryHelperException(e.getCause());
        }
    }

    public static <V> V poll(Callable<V> callable, RetrySettings pollSettings, ResultRetryAlgorithm<V> resultPollAlgorithm, ApiClock clock) throws ExecutionException, InterruptedException {
        return RetryHelper.run(callable, new ExponentialPollAlgorithm(pollSettings, clock), resultPollAlgorithm);
    }

    private static <V> V run(Callable<V> callable, TimedRetryAlgorithm timedAlgorithm, ResultRetryAlgorithm<V> resultAlgorithm) throws ExecutionException, InterruptedException {
        RetryAlgorithm<V> retryAlgorithm = new RetryAlgorithm<V>(resultAlgorithm, timedAlgorithm);
        DirectRetryingExecutor<V> executor = new DirectRetryingExecutor<V>(retryAlgorithm);
        RetryingFuture<V> retryingFuture = executor.createFuture(callable);
        executor.submit(retryingFuture);
        return retryingFuture.get();
    }

    public static class RetryHelperException
    extends RuntimeException {
        private static final long serialVersionUID = -8519852520090965314L;

        RetryHelperException(Throwable cause) {
            super(cause);
        }
    }
}

