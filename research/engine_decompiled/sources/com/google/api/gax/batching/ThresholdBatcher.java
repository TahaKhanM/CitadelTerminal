/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.batching;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.BetaApi;
import com.google.api.gax.batching.BatchMerger;
import com.google.api.gax.batching.BatchingFlowController;
import com.google.api.gax.batching.BatchingThreshold;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.batching.ThresholdBatchReceiver;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.threeten.bp.Duration;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
public final class ThresholdBatcher<E> {
    private final Runnable pushCurrentBatchRunnable = new Runnable(){

        @Override
        public void run() {
            ThresholdBatcher.this.pushCurrentBatch();
        }
    };
    private final ArrayList<BatchingThreshold<E>> thresholds;
    private final ScheduledExecutorService executor;
    private final Duration maxDelay;
    private final ThresholdBatchReceiver<E> receiver;
    private final BatchingFlowController<E> flowController;
    private final BatchMerger<E> batchMerger;
    private final ReentrantLock lock = new ReentrantLock();
    private E currentOpenBatch;
    private Future<?> currentAlarmFuture;

    private ThresholdBatcher(Builder<E> builder) {
        this.thresholds = new ArrayList(((Builder)builder).thresholds);
        this.executor = Preconditions.checkNotNull(((Builder)builder).executor);
        this.maxDelay = Preconditions.checkNotNull(((Builder)builder).maxDelay);
        this.receiver = Preconditions.checkNotNull(((Builder)builder).receiver);
        this.flowController = Preconditions.checkNotNull(((Builder)builder).flowController);
        this.batchMerger = Preconditions.checkNotNull(((Builder)builder).batchMerger);
        this.resetThresholds();
    }

    public static <E> Builder<E> newBuilder() {
        return new Builder();
    }

    public void add(E e) throws FlowController.FlowControlException {
        this.flowController.reserve(e);
        this.lock.lock();
        try {
            this.receiver.validateBatch(e);
            boolean anyThresholdReached = this.isAnyThresholdReached(e);
            if (this.currentOpenBatch == null) {
                this.currentOpenBatch = e;
                if (!anyThresholdReached) {
                    this.currentAlarmFuture = this.executor.schedule(this.pushCurrentBatchRunnable, this.maxDelay.toMillis(), TimeUnit.MILLISECONDS);
                }
            } else {
                this.batchMerger.merge(this.currentOpenBatch, e);
            }
            if (anyThresholdReached) {
                this.pushCurrentBatch();
            }
        }
        finally {
            this.lock.unlock();
        }
    }

    @VisibleForTesting
    boolean isEmpty() {
        this.lock.lock();
        try {
            boolean bl = this.currentOpenBatch == null;
            return bl;
        }
        finally {
            this.lock.unlock();
        }
    }

    @VisibleForTesting
    public ApiFuture<Void> pushCurrentBatch() {
        E batch = this.removeBatch();
        if (batch == null) {
            return ApiFutures.immediateFuture(null);
        }
        return ApiFutures.transform(this.receiver.processBatch(batch), new ReleaseResourcesFunction(batch), MoreExecutors.directExecutor());
    }

    private E removeBatch() {
        this.lock.lock();
        try {
            E batch = this.currentOpenBatch;
            this.currentOpenBatch = null;
            if (this.currentAlarmFuture != null) {
                this.currentAlarmFuture.cancel(false);
                this.currentAlarmFuture = null;
            }
            this.resetThresholds();
            E e = batch;
            return e;
        }
        finally {
            this.lock.unlock();
        }
    }

    private boolean isAnyThresholdReached(E e) {
        for (BatchingThreshold<E> threshold : this.thresholds) {
            threshold.accumulate(e);
            if (!threshold.isThresholdReached()) continue;
            return true;
        }
        return false;
    }

    private void resetThresholds() {
        for (int i = 0; i < this.thresholds.size(); ++i) {
            this.thresholds.set(i, this.thresholds.get(i).copyWithZeroedValue());
        }
    }

    public static class Builder<E> {
        private Collection<BatchingThreshold<E>> thresholds;
        private ScheduledExecutorService executor;
        private Duration maxDelay;
        private ThresholdBatchReceiver<E> receiver;
        private BatchingFlowController<E> flowController;
        private BatchMerger<E> batchMerger;

        private Builder() {
        }

        public Builder<E> setExecutor(ScheduledExecutorService executor) {
            this.executor = executor;
            return this;
        }

        public Builder<E> setMaxDelay(Duration maxDelay) {
            this.maxDelay = maxDelay;
            return this;
        }

        public Builder<E> setThresholds(Collection<BatchingThreshold<E>> thresholds) {
            this.thresholds = thresholds;
            return this;
        }

        public Builder<E> setReceiver(ThresholdBatchReceiver<E> receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder<E> setFlowController(BatchingFlowController<E> flowController) {
            this.flowController = flowController;
            return this;
        }

        public Builder<E> setBatchMerger(BatchMerger<E> batchMerger) {
            this.batchMerger = batchMerger;
            return this;
        }

        public ThresholdBatcher<E> build() {
            return new ThresholdBatcher(this);
        }
    }

    private class ReleaseResourcesFunction<T>
    implements ApiFunction<T, Void> {
        private final E batch;

        private ReleaseResourcesFunction(E batch) {
            this.batch = batch;
        }

        @Override
        public Void apply(T input2) {
            ThresholdBatcher.this.flowController.release(this.batch);
            return null;
        }
    }
}

