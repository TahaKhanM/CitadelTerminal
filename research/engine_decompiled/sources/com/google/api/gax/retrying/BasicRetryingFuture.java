/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.retrying.NonCancellableFuture;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.retrying.TimedAttemptSettings;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;

class BasicRetryingFuture<ResponseT>
extends AbstractFuture<ResponseT>
implements RetryingFuture<ResponseT> {
    final Object lock = new Object();
    private final Callable<ResponseT> callable;
    private final RetryAlgorithm<ResponseT> retryAlgorithm;
    private volatile TimedAttemptSettings attemptSettings;
    private volatile ApiFuture<ResponseT> latestCompletedAttemptResult;
    private volatile ApiFuture<ResponseT> attemptResult;

    BasicRetryingFuture(Callable<ResponseT> callable, RetryAlgorithm<ResponseT> retryAlgorithm) {
        this.callable = Preconditions.checkNotNull(callable);
        this.retryAlgorithm = Preconditions.checkNotNull(retryAlgorithm);
        this.attemptSettings = retryAlgorithm.createFirstAttempt();
        super.addListener(new CompletionListener(), MoreExecutors.directExecutor());
    }

    @Override
    public void setAttemptFuture(ApiFuture<ResponseT> attemptFuture) {
        try {
            if (this.isDone()) {
                return;
            }
            Object response = attemptFuture.get();
            this.handleAttempt(null, response);
        }
        catch (ExecutionException e) {
            this.handleAttempt(e.getCause(), null);
        }
        catch (Throwable e) {
            this.handleAttempt(e, null);
        }
    }

    @Override
    public Callable<ResponseT> getCallable() {
        return this.callable;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TimedAttemptSettings getAttemptSettings() {
        Object object = this.lock;
        synchronized (object) {
            return this.attemptSettings;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ApiFuture<ResponseT> peekAttemptResult() {
        Object object = this.lock;
        synchronized (object) {
            return this.latestCompletedAttemptResult;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ApiFuture<ResponseT> getAttemptResult() {
        Object object = this.lock;
        synchronized (object) {
            if (this.attemptResult == null) {
                this.attemptResult = new NonCancellableFuture();
            }
            return this.attemptResult;
        }
    }

    void clearAttemptServiceData() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void handleAttempt(Throwable throwable, ResponseT response) {
        Object object = this.lock;
        synchronized (object) {
            try {
                this.clearAttemptServiceData();
                if (throwable instanceof CancellationException) {
                    super.cancel(false);
                } else if (throwable instanceof RejectedExecutionException) {
                    super.setException(throwable);
                }
                if (this.isDone()) {
                    return;
                }
                TimedAttemptSettings nextAttemptSettings = this.retryAlgorithm.createNextAttempt(throwable, response, this.attemptSettings);
                boolean shouldRetry = this.retryAlgorithm.shouldRetry(throwable, response, nextAttemptSettings);
                if (shouldRetry) {
                    this.attemptSettings = nextAttemptSettings;
                    this.setAttemptResult(throwable, response, true);
                } else if (throwable != null) {
                    super.setException(throwable);
                } else {
                    super.set(response);
                }
            }
            catch (CancellationException e) {
                super.cancel(false);
            }
            catch (Exception e) {
                super.setException(e);
            }
        }
    }

    private void setAttemptResult(Throwable throwable, ResponseT response, boolean shouldRetry) {
        ApiFuture<ResponseT> prevAttemptResult = this.attemptResult;
        try {
            if (throwable instanceof CancellationException) {
                NonCancellableFuture future = new NonCancellableFuture();
                future.cancelPrivately();
                this.latestCompletedAttemptResult = future;
                ApiFuture<ResponseT> apiFuture = this.attemptResult = shouldRetry ? null : this.latestCompletedAttemptResult;
                if (prevAttemptResult instanceof NonCancellableFuture) {
                    ((NonCancellableFuture)prevAttemptResult).cancelPrivately();
                }
            } else if (throwable != null) {
                this.latestCompletedAttemptResult = ApiFutures.immediateFailedFuture(throwable);
                ApiFuture<ResponseT> apiFuture = this.attemptResult = shouldRetry ? null : this.latestCompletedAttemptResult;
                if (prevAttemptResult instanceof NonCancellableFuture) {
                    ((NonCancellableFuture)prevAttemptResult).setExceptionPrivately(throwable);
                }
            } else {
                this.latestCompletedAttemptResult = ApiFutures.immediateFuture(response);
                ApiFuture<ResponseT> apiFuture = this.attemptResult = shouldRetry ? null : this.latestCompletedAttemptResult;
                if (prevAttemptResult instanceof NonCancellableFuture) {
                    ((NonCancellableFuture)prevAttemptResult).setPrivately(response);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private class CompletionListener
    implements Runnable {
        private CompletionListener() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            Object object = BasicRetryingFuture.this.lock;
            synchronized (object) {
                try {
                    BasicRetryingFuture.this.clearAttemptServiceData();
                    Object response = BasicRetryingFuture.this.get();
                    BasicRetryingFuture.this.setAttemptResult(null, response, false);
                }
                catch (ExecutionException e) {
                    BasicRetryingFuture.this.setAttemptResult(e.getCause(), null, false);
                }
                catch (Throwable e) {
                    BasicRetryingFuture.this.setAttemptResult(e, null, false);
                }
            }
        }
    }
}

