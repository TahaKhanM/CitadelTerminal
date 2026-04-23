/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.ThresholdBatchReceiver;
import com.google.api.gax.rpc.Batch;
import com.google.api.gax.rpc.BatchedRequestIssuer;
import com.google.api.gax.rpc.BatchingDescriptor;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;

class BatchExecutor<RequestT, ResponseT>
implements ThresholdBatchReceiver<Batch<RequestT, ResponseT>> {
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final PartitionKey partitionKey;

    public BatchExecutor(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, PartitionKey partitionKey) {
        this.batchingDescriptor = Preconditions.checkNotNull(batchingDescriptor);
        this.partitionKey = Preconditions.checkNotNull(partitionKey);
    }

    @Override
    public void validateBatch(Batch<RequestT, ResponseT> item) {
        PartitionKey itemPartitionKey = this.batchingDescriptor.getBatchPartitionKey(item.getRequest());
        if (!itemPartitionKey.equals(this.partitionKey)) {
            String requestClassName = item.getRequest().getClass().getSimpleName();
            throw new IllegalArgumentException(String.format("For type %s, invalid partition key: %s, should be: %s", requestClassName, itemPartitionKey, this.partitionKey));
        }
    }

    @Override
    public ApiFuture<ResponseT> processBatch(Batch<RequestT, ResponseT> batch) {
        UnaryCallable<RequestT, ResponseT> callable = batch.getCallable();
        RequestT request = batch.getRequest();
        final List<BatchedRequestIssuer<ResponseT>> requestIssuerList = batch.getRequestIssuerList();
        ApiFuture<ResponseT> future = callable.futureCall(request);
        ApiFutures.addCallback(future, new ApiFutureCallback<ResponseT>(){

            @Override
            public void onSuccess(ResponseT result2) {
                BatchExecutor.this.batchingDescriptor.splitResponse(result2, requestIssuerList);
                for (BatchedRequestIssuer requestIssuer : requestIssuerList) {
                    requestIssuer.sendResult();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                BatchExecutor.this.batchingDescriptor.splitException(t, requestIssuerList);
                for (BatchedRequestIssuer requestIssuer : requestIssuerList) {
                    requestIssuer.sendResult();
                }
            }
        }, MoreExecutors.directExecutor());
        return future;
    }
}

