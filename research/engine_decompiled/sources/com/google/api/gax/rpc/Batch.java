/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.BatchMerger;
import com.google.api.gax.batching.ElementCounter;
import com.google.api.gax.batching.RequestBuilder;
import com.google.api.gax.rpc.BatchedFuture;
import com.google.api.gax.rpc.BatchedRequestIssuer;
import com.google.api.gax.rpc.BatchingDescriptor;
import com.google.api.gax.rpc.UnaryCallable;
import java.util.ArrayList;
import java.util.List;

@InternalApi
public class Batch<RequestT, ResponseT> {
    private final List<BatchedRequestIssuer<ResponseT>> requestIssuerList;
    private final RequestBuilder<RequestT> requestBuilder;
    private UnaryCallable<RequestT, ResponseT> callable;
    private long byteCount;

    public Batch(BatchingDescriptor<RequestT, ResponseT> descriptor, RequestT request, UnaryCallable<RequestT, ResponseT> callable, BatchedFuture<ResponseT> batchedFuture) {
        this.requestBuilder = descriptor.getRequestBuilder();
        this.requestIssuerList = new ArrayList<BatchedRequestIssuer<ResponseT>>();
        this.requestBuilder.appendRequest(request);
        this.callable = callable;
        this.requestIssuerList.add(new BatchedRequestIssuer<ResponseT>(batchedFuture, descriptor.countElements(request)));
        this.byteCount = descriptor.countBytes(request);
    }

    public RequestT getRequest() {
        return this.requestBuilder.build();
    }

    public UnaryCallable<RequestT, ResponseT> getCallable() {
        return this.callable;
    }

    public List<BatchedRequestIssuer<ResponseT>> getRequestIssuerList() {
        return this.requestIssuerList;
    }

    public long getByteCount() {
        return this.byteCount;
    }

    public void merge(Batch<RequestT, ResponseT> batch) {
        this.requestBuilder.appendRequest(batch.getRequest());
        this.requestIssuerList.addAll(batch.requestIssuerList);
        if (this.callable == null) {
            this.callable = batch.callable;
        }
        this.byteCount += batch.byteCount;
    }

    static class BatchMergerImpl<RequestT, ResponseT>
    implements BatchMerger<Batch<RequestT, ResponseT>> {
        BatchMergerImpl() {
        }

        @Override
        public void merge(Batch<RequestT, ResponseT> batch, Batch<RequestT, ResponseT> newBatch) {
            batch.merge(newBatch);
        }
    }

    static class BatchByteCounter<RequestT, ResponseT>
    implements ElementCounter<Batch<RequestT, ResponseT>> {
        BatchByteCounter() {
        }

        @Override
        public long count(Batch<RequestT, ResponseT> batch) {
            return batch.getByteCount();
        }
    }

    static class BatchElementCounter<RequestT, ResponseT>
    implements ElementCounter<Batch<RequestT, ResponseT>> {
        private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;

        BatchElementCounter(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
            this.batchingDescriptor = batchingDescriptor;
        }

        @Override
        public long count(Batch<RequestT, ResponseT> batch) {
            return this.batchingDescriptor.countElements(batch.getRequest());
        }
    }
}

