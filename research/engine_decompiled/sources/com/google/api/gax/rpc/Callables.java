/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationResponsePollAlgorithm;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.ExponentialRetryAlgorithm;
import com.google.api.gax.retrying.RetryAlgorithm;
import com.google.api.gax.retrying.ScheduledRetryingExecutor;
import com.google.api.gax.retrying.StreamingRetryAlgorithm;
import com.google.api.gax.rpc.ApiResultRetryAlgorithm;
import com.google.api.gax.rpc.BatcherFactory;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BatchingCallable;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.LongRunningClient;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.OperationCallableImpl;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.PagedCallable;
import com.google.api.gax.rpc.RetryingCallable;
import com.google.api.gax.rpc.RetryingServerStreamingCallable;
import com.google.api.gax.rpc.ServerStreamingCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.api.gax.rpc.WatchdogServerStreamingCallable;

@BetaApi
public class Callables {
    private Callables() {
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> retrying(UnaryCallable<RequestT, ResponseT> innerCallable, UnaryCallSettings<?, ?> callSettings, ClientContext clientContext) {
        if (callSettings.getRetryableCodes().isEmpty()) {
            return innerCallable;
        }
        RetryAlgorithm retryAlgorithm = new RetryAlgorithm(new ApiResultRetryAlgorithm(), new ExponentialRetryAlgorithm(callSettings.getRetrySettings(), clientContext.getClock()));
        ScheduledRetryingExecutor retryingExecutor = new ScheduledRetryingExecutor(retryAlgorithm, clientContext.getExecutor());
        return new RetryingCallable<RequestT, ResponseT>(clientContext.getDefaultCallContext(), innerCallable, retryingExecutor);
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> retrying(ServerStreamingCallable<RequestT, ResponseT> innerCallable, ServerStreamingCallSettings<RequestT, ResponseT> callSettings, ClientContext clientContext) {
        if (callSettings.getRetryableCodes().isEmpty()) {
            return innerCallable;
        }
        StreamingRetryAlgorithm retryAlgorithm = new StreamingRetryAlgorithm(new ApiResultRetryAlgorithm(), new ExponentialRetryAlgorithm(callSettings.getRetrySettings(), clientContext.getClock()));
        ScheduledRetryingExecutor<Void> retryingExecutor = new ScheduledRetryingExecutor<Void>(retryAlgorithm, clientContext.getExecutor());
        return new RetryingServerStreamingCallable<RequestT, ResponseT>(innerCallable, retryingExecutor, callSettings.getResumptionStrategy());
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> watched(ServerStreamingCallable<RequestT, ResponseT> callable, ServerStreamingCallSettings<RequestT, ResponseT> callSettings, ClientContext clientContext) {
        callable = new WatchdogServerStreamingCallable<RequestT, ResponseT>(callable, clientContext.getStreamWatchdog());
        callable = callable.withDefaultCallContext(clientContext.getDefaultCallContext().withStreamIdleTimeout(callSettings.getIdleTimeout()));
        return callable;
    }

    @BetaApi(value="The surface for batching is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> batching(UnaryCallable<RequestT, ResponseT> innerCallable, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext context) {
        return ((BatchingCreateResult)Callables.batchingImpl(innerCallable, batchingCallSettings, context)).unaryCallable;
    }

    static <RequestT, ResponseT> BatchingCreateResult<RequestT, ResponseT> batchingImpl(UnaryCallable<RequestT, ResponseT> innerCallable, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        BatcherFactory<RequestT, ResponseT> batcherFactory = new BatcherFactory<RequestT, ResponseT>(batchingCallSettings.getBatchingDescriptor(), batchingCallSettings.getBatchingSettings(), clientContext.getExecutor(), batchingCallSettings.getFlowController());
        BatchingCallable<RequestT, ResponseT> callable = new BatchingCallable<RequestT, ResponseT>(innerCallable, batchingCallSettings.getBatchingDescriptor(), batcherFactory);
        return new BatchingCreateResult(batcherFactory, callable);
    }

    public static <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> paged(UnaryCallable<RequestT, ResponseT> innerCallable, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings) {
        return new PagedCallable<RequestT, ResponseT, PagedListResponseT>(innerCallable, pagedCallSettings.getPagedListResponseFactory());
    }

    public static <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> longRunningOperation(UnaryCallable<RequestT, OperationSnapshot> initialCallable, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, LongRunningClient longRunningClient) {
        return Callables.longRunningOperationImpl(initialCallable, operationCallSettings, clientContext, longRunningClient);
    }

    static <RequestT, ResponseT, MetadataT> OperationCallableImpl<RequestT, ResponseT, MetadataT> longRunningOperationImpl(UnaryCallable<RequestT, OperationSnapshot> initialCallable, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, LongRunningClient longRunningClient) {
        RetryAlgorithm<OperationSnapshot> pollingAlgorithm = new RetryAlgorithm<OperationSnapshot>(new OperationResponsePollAlgorithm(), operationCallSettings.getPollingAlgorithm());
        ScheduledRetryingExecutor<OperationSnapshot> scheduler = new ScheduledRetryingExecutor<OperationSnapshot>(pollingAlgorithm, clientContext.getExecutor());
        return new OperationCallableImpl<RequestT, ResponseT, MetadataT>(initialCallable, scheduler, longRunningClient, operationCallSettings);
    }

    static class BatchingCreateResult<RequestT, ResponseT> {
        private final BatcherFactory<RequestT, ResponseT> batcherFactory;
        private final UnaryCallable<RequestT, ResponseT> unaryCallable;

        private BatchingCreateResult(BatcherFactory<RequestT, ResponseT> batcherFactory, UnaryCallable<RequestT, ResponseT> unaryCallable) {
            this.batcherFactory = batcherFactory;
            this.unaryCallable = unaryCallable;
        }

        public BatcherFactory<RequestT, ResponseT> getBatcherFactory() {
            return this.batcherFactory;
        }

        public UnaryCallable<RequestT, ResponseT> getUnaryCallable() {
            return this.unaryCallable;
        }
    }
}

