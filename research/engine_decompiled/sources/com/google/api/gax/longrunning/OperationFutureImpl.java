/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.longrunning;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
@InternalApi
public final class OperationFutureImpl<ResponseT, MetadataT>
implements OperationFuture<ResponseT, MetadataT> {
    private final Object lock = new Object();
    private final RetryingFuture<OperationSnapshot> pollingFuture;
    private final ApiFuture<OperationSnapshot> initialFuture;
    private final ApiFuture<ResponseT> resultFuture;
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;
    private volatile ApiFuture<OperationSnapshot> peekedAttemptResult;
    private volatile ApiFuture<MetadataT> peekedPollResult;
    private volatile ApiFuture<OperationSnapshot> gottenAttemptResult;
    private volatile ApiFuture<MetadataT> gottenPollResult;

    public OperationFutureImpl(RetryingFuture<OperationSnapshot> pollingFuture, ApiFuture<OperationSnapshot> initialFuture, ApiFunction<OperationSnapshot, ResponseT> responseTransformer, ApiFunction<OperationSnapshot, MetadataT> metadataTransformer) {
        this.pollingFuture = Preconditions.checkNotNull(pollingFuture);
        this.initialFuture = Preconditions.checkNotNull(initialFuture);
        this.resultFuture = ApiFutures.transform(pollingFuture, responseTransformer, MoreExecutors.directExecutor());
        this.metadataTransformer = Preconditions.checkNotNull(metadataTransformer);
    }

    public OperationFutureImpl(RetryingFuture<OperationSnapshot> pollingFuture, ApiFuture<OperationSnapshot> initialFuture, ApiFunction<OperationSnapshot, ResponseT> responseTransformer, ApiFunction<OperationSnapshot, MetadataT> metadataTransformer, ApiFunction<Exception, ResponseT> exceptionTransformer) {
        this.pollingFuture = Preconditions.checkNotNull(pollingFuture);
        this.initialFuture = Preconditions.checkNotNull(initialFuture);
        this.resultFuture = ApiFutures.catching(ApiFutures.transform(pollingFuture, responseTransformer, MoreExecutors.directExecutor()), Exception.class, exceptionTransformer, MoreExecutors.directExecutor());
        this.metadataTransformer = Preconditions.checkNotNull(metadataTransformer);
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        this.pollingFuture.addListener(listener, executor);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.pollingFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return this.pollingFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.pollingFuture.isDone();
    }

    @Override
    public ResponseT get() throws InterruptedException, ExecutionException {
        return (ResponseT)this.resultFuture.get();
    }

    @Override
    public ResponseT get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return (ResponseT)this.resultFuture.get(timeout, unit);
    }

    @Override
    public String getName() throws ExecutionException, InterruptedException {
        return ((OperationSnapshot)this.initialFuture.get()).getName();
    }

    @Override
    public ApiFuture<OperationSnapshot> getInitialFuture() {
        return this.initialFuture;
    }

    @Override
    public RetryingFuture<OperationSnapshot> getPollingFuture() {
        return this.pollingFuture;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ApiFuture<MetadataT> peekMetadata() {
        ApiFuture<OperationSnapshot> future = this.pollingFuture.peekAttemptResult();
        Object object = this.lock;
        synchronized (object) {
            if (this.peekedAttemptResult == future) {
                return this.peekedPollResult;
            }
            this.peekedAttemptResult = future;
            this.peekedPollResult = ApiFutures.transform(this.peekedAttemptResult, this.metadataTransformer, MoreExecutors.directExecutor());
            return this.peekedPollResult;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ApiFuture<MetadataT> getMetadata() {
        ApiFuture<OperationSnapshot> future = this.pollingFuture.getAttemptResult();
        Object object = this.lock;
        synchronized (object) {
            if (this.gottenAttemptResult == future) {
                return this.gottenPollResult;
            }
            this.gottenAttemptResult = future;
            this.gottenPollResult = ApiFutures.transform(this.gottenAttemptResult, this.metadataTransformer, MoreExecutors.directExecutor());
            return this.gottenPollResult;
        }
    }
}

