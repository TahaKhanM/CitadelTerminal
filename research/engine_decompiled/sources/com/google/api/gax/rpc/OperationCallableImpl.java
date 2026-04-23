/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationFutureImpl;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.RetryingExecutor;
import com.google.api.gax.retrying.RetryingFuture;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.LongRunningClient;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.OperationCheckingCallable;
import com.google.api.gax.rpc.RecheckingCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.base.Preconditions;

class OperationCallableImpl<RequestT, ResponseT, MetadataT>
extends OperationCallable<RequestT, ResponseT, MetadataT> {
    private final UnaryCallable<RequestT, OperationSnapshot> initialCallable;
    private final RetryingExecutor<OperationSnapshot> executor;
    private final LongRunningClient longRunningClient;
    private final ApiFunction<OperationSnapshot, ResponseT> responseTransformer;
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;

    OperationCallableImpl(UnaryCallable<RequestT, OperationSnapshot> initialCallable, RetryingExecutor<OperationSnapshot> executor, LongRunningClient longRunningClient, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings) {
        this.initialCallable = Preconditions.checkNotNull(initialCallable);
        this.executor = Preconditions.checkNotNull(executor);
        this.longRunningClient = Preconditions.checkNotNull(longRunningClient);
        this.responseTransformer = operationCallSettings.getResponseTransformer();
        this.metadataTransformer = operationCallSettings.getMetadataTransformer();
    }

    @Override
    public OperationFuture<ResponseT, MetadataT> futureCall(RequestT request, ApiCallContext context) {
        return this.futureCall(this.initialCallable.futureCall(request, context));
    }

    @Override
    OperationFutureImpl<ResponseT, MetadataT> futureCall(ApiFuture<OperationSnapshot> initialFuture) {
        RecheckingCallable callable = new RecheckingCallable(new OperationCheckingCallable(this.longRunningClient, initialFuture), this.executor);
        ApiFuture pollingFuture = callable.futureCall((Object)null, (ApiCallContext)null);
        return new OperationFutureImpl<ResponseT, MetadataT>((RetryingFuture<OperationSnapshot>)pollingFuture, initialFuture, this.responseTransformer, this.metadataTransformer);
    }

    @Override
    public OperationFuture<ResponseT, MetadataT> resumeFutureCall(String operationName, ApiCallContext context) {
        return this.futureCall(this.longRunningClient.getOperationCallable().futureCall(operationName, context));
    }

    @Override
    public ApiFuture<Void> cancel(String operationName, ApiCallContext context) {
        return this.longRunningClient.cancelOperationCallable().futureCall(operationName, context);
    }
}

