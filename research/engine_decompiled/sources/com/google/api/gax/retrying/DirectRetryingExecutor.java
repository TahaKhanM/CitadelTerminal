/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.BasicRetryingFuture;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetryingExecutor;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;
import java.io.InterruptedIOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.concurrent.Callable;
import org.threeten.bp.Duration;

public class DirectRetryingExecutor<ResponseT>
implements RetryingExecutor<ResponseT> {
    private final RetryAlgorithm<ResponseT> retryAlgorithm;

    public DirectRetryingExecutor(RetryAlgorithm<ResponseT> retryAlgorithm) {
        this.retryAlgorithm = Preconditions.checkNotNull(retryAlgorithm);
    }

    @Override
    public RetryingFuture<ResponseT> createFuture(Callable<ResponseT> callable) {
        return new BasicRetryingFuture<ResponseT>(callable, this.retryAlgorithm);
    }

    @Override
    public ApiFuture<ResponseT> submit(RetryingFuture<ResponseT> retryingFuture) {
        while (!retryingFuture.isDone()) {
            try {
                this.sleep(retryingFuture.getAttemptSettings().getRandomizedRetryDelay());
                ResponseT response = retryingFuture.getCallable().call();
                retryingFuture.setAttemptFuture(ApiFutures.immediateFuture(response));
            }
            catch (InterruptedIOException | InterruptedException | ClosedByInterruptException e) {
                Thread.currentThread().interrupt();
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
            }
            catch (Exception e) {
                retryingFuture.setAttemptFuture(ApiFutures.immediateFailedFuture(e));
            }
        }
        return retryingFuture;
    }

    protected void sleep(Duration delay) throws InterruptedException {
        if (Duration.ZERO.compareTo(delay) < 0) {
            Thread.sleep(delay.toMillis());
        }
    }
}

