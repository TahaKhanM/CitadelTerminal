/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BidiStreamingCallable;
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
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;

@BetaApi(value="The surface for use by generated code is not stable yet and may change in the future.")
public interface GrpcStubCallableFactory {
    public <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createUnaryCallable(GrpcCallSettings<RequestT, ResponseT> var1, UnaryCallSettings<RequestT, ResponseT> var2, ClientContext var3);

    public <RequestT, ResponseT, PagedListResponseT> UnaryCallable<RequestT, PagedListResponseT> createPagedCallable(GrpcCallSettings<RequestT, ResponseT> var1, PagedCallSettings<RequestT, ResponseT, PagedListResponseT> var2, ClientContext var3);

    @BetaApi(value="The surface for batching is not stable yet and may change in the future.")
    public <RequestT, ResponseT> UnaryCallable<RequestT, ResponseT> createBatchingCallable(GrpcCallSettings<RequestT, ResponseT> var1, BatchingCallSettings<RequestT, ResponseT> var2, ClientContext var3);

    @BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
    public <RequestT, ResponseT, MetadataT> OperationCallable<RequestT, ResponseT, MetadataT> createOperationCallable(GrpcCallSettings<RequestT, Operation> var1, OperationCallSettings<RequestT, ResponseT, MetadataT> var2, ClientContext var3, OperationsStub var4);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public <RequestT, ResponseT> BidiStreamingCallable<RequestT, ResponseT> createBidiStreamingCallable(GrpcCallSettings<RequestT, ResponseT> var1, StreamingCallSettings<RequestT, ResponseT> var2, ClientContext var3);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public <RequestT, ResponseT> ServerStreamingCallable<RequestT, ResponseT> createServerStreamingCallable(GrpcCallSettings<RequestT, ResponseT> var1, ServerStreamingCallSettings<RequestT, ResponseT> var2, ClientContext var3);

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    public <RequestT, ResponseT> ClientStreamingCallable<RequestT, ResponseT> createClientStreamingCallable(GrpcCallSettings<RequestT, ResponseT> var1, StreamingCallSettings<RequestT, ResponseT> var2, ClientContext var3);
}

