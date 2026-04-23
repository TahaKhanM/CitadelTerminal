/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.BatchingFlowController;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.BatchingThreshold;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.batching.NumericThreshold;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.ThresholdBatcher;
import com.google.api.gax.rpc.Batch;
import com.google.api.gax.rpc.BatchExecutor;
import com.google.api.gax.rpc.BatchingDescriptor;
import com.google.common.collect.ImmutableList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

@InternalApi
public final class BatcherFactory<RequestT, ResponseT> {
    private final Map<PartitionKey, ThresholdBatcher<Batch<RequestT, ResponseT>>> batchers = new ConcurrentHashMap<PartitionKey, ThresholdBatcher<Batch<RequestT, ResponseT>>>();
    private final ScheduledExecutorService executor;
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final FlowController flowController;
    private final BatchingSettings batchingSettings;
    private final Object lock = new Object();

    public BatcherFactory(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, BatchingSettings batchingSettings, ScheduledExecutorService executor, FlowController flowController) {
        this.batchingDescriptor = batchingDescriptor;
        this.batchingSettings = batchingSettings;
        this.executor = executor;
        this.flowController = flowController;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ThresholdBatcher<Batch<RequestT, ResponseT>> getPushingBatcher(PartitionKey partitionKey) {
        ThresholdBatcher<Batch<RequestT, ResponseT>> batcher = this.batchers.get(partitionKey);
        if (batcher == null) {
            Object object = this.lock;
            synchronized (object) {
                batcher = this.batchers.get(partitionKey);
                if (batcher == null) {
                    batcher = this.createBatcher(partitionKey);
                    this.batchers.put(partitionKey, batcher);
                }
            }
        }
        return batcher;
    }

    @InternalApi
    public BatchingSettings getBatchingSettings() {
        return this.batchingSettings;
    }

    private ThresholdBatcher<Batch<RequestT, ResponseT>> createBatcher(PartitionKey partitionKey) {
        BatchExecutor<RequestT, ResponseT> processor = new BatchExecutor<RequestT, ResponseT>(this.batchingDescriptor, partitionKey);
        BatchingFlowController batchingFlowController = new BatchingFlowController(this.flowController, new Batch.BatchElementCounter<RequestT, ResponseT>(this.batchingDescriptor), new Batch.BatchByteCounter());
        return ThresholdBatcher.newBuilder().setThresholds(this.getThresholds(this.batchingSettings)).setExecutor(this.executor).setMaxDelay(this.batchingSettings.getDelayThreshold()).setReceiver(processor).setFlowController(batchingFlowController).setBatchMerger(new Batch.BatchMergerImpl()).build();
    }

    private ImmutableList<BatchingThreshold<Batch<RequestT, ResponseT>>> getThresholds(BatchingSettings batchingSettings) {
        ImmutableList.Builder listBuilder = ImmutableList.builder();
        if (batchingSettings.getElementCountThreshold() != null) {
            Batch.BatchElementCounter<RequestT, ResponseT> elementCounter = new Batch.BatchElementCounter<RequestT, ResponseT>(this.batchingDescriptor);
            NumericThreshold countThreshold = new NumericThreshold(batchingSettings.getElementCountThreshold(), elementCounter);
            listBuilder.add(countThreshold);
        }
        if (batchingSettings.getRequestByteThreshold() != null) {
            Batch.BatchByteCounter requestByteCounter = new Batch.BatchByteCounter();
            NumericThreshold byteThreshold = new NumericThreshold(batchingSettings.getRequestByteThreshold(), requestByteCounter);
            listBuilder.add(byteThreshold);
        }
        return listBuilder.build();
    }
}

