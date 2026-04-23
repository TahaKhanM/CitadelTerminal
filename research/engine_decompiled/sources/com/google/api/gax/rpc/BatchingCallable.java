/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.ThresholdBatcher;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.Batch;
import com.google.api.gax.rpc.BatchedFuture;
import com.google.api.gax.rpc.BatcherFactory;
import com.google.api.gax.rpc.BatchingDescriptor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

@InternalApi(value="For use by transport-specific implementations")
public class BatchingCallable<RequestT, ResponseT>
extends UnaryCallable<RequestT, ResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final BatcherFactory<RequestT, ResponseT> batcherFactory;

    public BatchingCallable(UnaryCallable<RequestT, ResponseT> callable, BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, BatcherFactory<RequestT, ResponseT> batcherFactory) {
        this.callable = Preconditions.checkNotNull(callable);
        this.batchingDescriptor = Preconditions.checkNotNull(batchingDescriptor);
        this.batcherFactory = Preconditions.checkNotNull(batcherFactory);
    }

    @Override
    public ApiFuture<ResponseT> futureCall(RequestT request, ApiCallContext context) {
        if (this.batcherFactory.getBatchingSettings().getIsEnabled().booleanValue()) {
            BatchedFuture result2 = BatchedFuture.create();
            UnaryCallable<RequestT, ResponseT> unaryCallable = this.callable.withDefaultCallContext(context);
            Batch<RequestT, ResponseT> batchableMessage = new Batch<RequestT, ResponseT>(this.batchingDescriptor, request, unaryCallable, result2);
            PartitionKey partitionKey = this.batchingDescriptor.getBatchPartitionKey(request);
            ThresholdBatcher<Batch<RequestT, ResponseT>> batcher = this.batcherFactory.getPushingBatcher(partitionKey);
            try {
                batcher.add(batchableMessage);
                return result2;
            }
            catch (FlowController.FlowControlException e) {
                throw FlowController.FlowControlRuntimeException.fromFlowControlException(e);
            }
        }
        return this.callable.futureCall(request, context);
    }
}

