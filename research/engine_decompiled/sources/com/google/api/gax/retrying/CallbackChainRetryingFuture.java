/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.retrying;

import com.google.api.core.ApiFuture;
import com.google.api.gax.retrying.BasicRetryingFuture;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.RetryingExecutor;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class CallbackChainRetryingFuture<ResponseT>
extends BasicRetryingFuture<ResponseT> {
    private final RetryingExecutor<ResponseT> retryingExecutor;
    private volatile AttemptCompletionListener attemptFutureCompletionListener;

    CallbackChainRetryingFuture(Callable<ResponseT> callable, RetryAlgorithm<ResponseT> retryAlgorithm, RetryingExecutor<ResponseT> retryingExecutor) {
        super(callable, retryAlgorithm);
        this.retryingExecutor = Preconditions.checkNotNull(retryingExecutor);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        Object object = this.lock;
        synchronized (object) {
            if (this.attemptFutureCompletionListener == null) {
                return super.cancel(mayInterruptIfRunning);
            }
            this.attemptFutureCompletionListener.attemptFuture.cancel(mayInterruptIfRunning);
            return this.isCancelled();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAttemptFuture(ApiFuture<ResponseT> attemptFuture) {
        if (this.isDone()) {
            return;
        }
        Object object = this.lock;
        synchronized (object) {
            if (this.isDone()) {
                return;
            }
            this.attemptFutureCompletionListener = new AttemptCompletionListener(attemptFuture);
            attemptFuture.addListener(this.attemptFutureCompletionListener, MoreExecutors.directExecutor());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    void clearAttemptServiceData() {
        Object object = this.lock;
        synchronized (object) {
            this.attemptFutureCompletionListener = null;
        }
    }

    private class AttemptCompletionListener
    implements Runnable {
        private final Future<ResponseT> attemptFuture;

        AttemptCompletionListener(Future<ResponseT> attemptFuture) {
            this.attemptFuture = attemptFuture;
        }

        @Override
        public void run() {
            try {
                Object response = this.attemptFuture.get();
                this.handle(null, response);
            }
            catch (ExecutionException e) {
                this.handle(e.getCause(), null);
            }
            catch (Throwable e) {
                this.handle(e, null);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void handle(Throwable t, ResponseT response) {
            if (this != CallbackChainRetryingFuture.this.attemptFutureCompletionListener || CallbackChainRetryingFuture.this.isDone()) {
                return;
            }
            Object object = CallbackChainRetryingFuture.this.lock;
            synchronized (object) {
                if (this != CallbackChainRetryingFuture.this.attemptFutureCompletionListener || CallbackChainRetryingFuture.this.isDone()) {
                    return;
                }
                CallbackChainRetryingFuture.this.handleAttempt(t, response);
                if (!CallbackChainRetryingFuture.this.isDone()) {
                    ApiFuture attempt = CallbackChainRetryingFuture.this.retryingExecutor.submit(CallbackChainRetryingFuture.this);
                    CallbackChainRetryingFuture.this.setAttemptFuture(attempt);
                }
            }
        }
    }
}

