/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcDirectBidiStreamingCallable;
import com.google.api.gax.grpc.GrpcDirectCallable;
import com.google.api.gax.grpc.GrpcDirectClientStreamingCallable;
import com.google.api.gax.grpc.GrpcDirectServerStreamingCallable;
import com.google.api.gax.grpc.GrpcExceptionBidiStreamingCallable;
import com.google.api.gax.grpc.GrpcExceptionCallable;
import com.google.api.gax.grpc.GrpcExceptionClientStreamingCallable;
import com.google.api.gax.grpc.GrpcExceptionServerStreamingCallable;
import com.google.api.gax.grpc.GrpcLongRunningClient;
import com.google.api.gax.grpc.GrpcOperationSnapshotCallable;
import com.google.api.gax.grpc.GrpcServerStreamingRequestParamCallable;
import com.google.api.gax.grpc.GrpcUnaryRequestParamCallable;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.Callables;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientStreamingCallable;
import com.google.api.gax.rpc.OperationCallSettings;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallSettings;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamingCallSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.collect.ImmutableSet;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;

@BetaApi(value="The surface for use by generated code is not stable yet and may change in the future.")
public class GrpcCallableFactory {
    private GrpcCallableFactory() {
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBaseUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<?, ?> callSettings, ClientContext clientContext) {
        UnaryCallable callable = new GrpcDirectCallable<RequestT, ResponseT>(grpcCallSettings.getMethodDescriptor());
        if (grpcCallSettings.getParamsExtractor() != null) {
            callable = new GrpcUnaryRequestParamCallable<RequestT, ResponseT>(callable, grpcCallSettings.getParamsExtractor());
        }
        callable = new GrpcExceptionCallable<RequestT, ResponseT>(callable, callSettings.getRetryableCodes());
        callable = Callables.retrying(callable, callSettings, clientContext);
        return callable;
    }

    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, UnaryCallSettings<RequestT, ResponseT> callSettings, ClientContext clientContext) {
        UnaryCallable<RequestT, ResponseT> callable = GrpcCallableFactory.createBaseUnaryCallable(grpcCallSettings, callSettings, clientContext);
        return callable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    public static <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> createPagedCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings, ClientContext clientContext) {
        UnaryCallable<RequestT, ResponseT> innerCallable = GrpcCallableFactory.createBaseUnaryCallable(grpcCallSettings, pagedCallSettings, clientContext);
        UnaryCallable<RequestT, PagedListResponseT> pagedCallable = Callables.paged(innerCallable, pagedCallSettings);
        return pagedCallable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi(value="The surface for batching is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBatchingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, BatchingCallSettings<RequestT, ResponseT> batchingCallSettings, ClientContext clientContext) {
        UnaryCallable<RequestT, ResponseT> callable = GrpcCallableFactory.createBaseUnaryCallable(grpcCallSettings, batchingCallSettings, clientContext);
        callable = Callables.batching(callable, batchingCallSettings, clientContext);
        return callable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
    public static <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> createOperationCallable(GrpcCallSettings<RequestT, Operation> grpcCallSettings, OperationCallSettings<RequestT, ResponseT, MetadataT> operationCallSettings, ClientContext clientContext, OperationsStub operationsStub) {
        UnaryCallable<RequestT, Operation> initialGrpcCallable = GrpcCallableFactory.createBaseUnaryCallable(grpcCallSettings, operationCallSettings.getInitialCallSettings(), clientContext);
        GrpcOperationSnapshotCallable<RequestT> initialCallable = new GrpcOperationSnapshotCallable<RequestT>(initialGrpcCallable);
        GrpcLongRunningClient longRunningClient = new GrpcLongRunningClient(operationsStub);
        OperationCallable<RequestT, ResponseT, MetadataT> operationCallable = Callables.longRunningOperation(initialCallable, operationCallSettings, clientContext, longRunningClient);
        return operationCallable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        BidiStreamingCallable callable = new GrpcDirectBidiStreamingCallable<RequestT, ResponseT>(grpcCallSettings.getMethodDescriptor());
        callable = new GrpcExceptionBidiStreamingCallable<RequestT, ResponseT>(callable, ImmutableSet.of());
        return callable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @Deprecated
    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        StreamingCallSettings serverStreamingCallSettings = ServerStreamingCallSettings.newBuilder().build();
        return GrpcCallableFactory.createServerStreamingCallable(grpcCallSettings, serverStreamingCallSettings, clientContext);
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, ServerStreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        ServerStreamingCallable callable = new GrpcDirectServerStreamingCallable<RequestT, ResponseT>(grpcCallSettings.getMethodDescriptor());
        if (grpcCallSettings.getParamsExtractor() != null) {
            callable = new GrpcServerStreamingRequestParamCallable<RequestT, ResponseT>(callable, grpcCallSettings.getParamsExtractor());
        }
        callable = new GrpcExceptionServerStreamingCallable<RequestT, ResponseT>(callable, streamingCallSettings.getRetryableCodes());
        if (clientContext.getStreamWatchdog() != null) {
            callable = Callables.watched(callable, streamingCallSettings, clientContext);
        }
        callable = Callables.retrying(callable, streamingCallSettings, clientContext);
        return callable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public static <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> grpcCallSettings, StreamingCallSettings<RequestT, ResponseT> streamingCallSettings, ClientContext clientContext) {
        ClientStreamingCallable callable = new GrpcDirectClientStreamingCallable<RequestT, ResponseT>(grpcCallSettings.getMethodDescriptor());
        callable = new GrpcExceptionClientStreamingCallable<RequestT, ResponseT>(callable, ImmutableSet.of());
        return callable.withDefaultCallContext(clientContext.getDefaultCallContext());
    }
}

