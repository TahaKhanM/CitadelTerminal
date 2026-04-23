/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.stub.GrpcOperationsCallableFactory;
import com.google.longrunning.stub.OperationsStub;
import com.google.longrunning.stub.OperationsStubSettings;
import com.google.protobuf.Empty;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public class GrpcOperationsStub
extends OperationsStub {
    private static final MethodDescriptor<GetOperationRequest, Operation> getOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/GetOperation").setRequestMarshaller(ProtoUtils.marshaller(GetOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Operation.getDefaultInstance())).build();
    private static final MethodDescriptor<ListOperationsRequest, ListOperationsResponse> listOperationsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/ListOperations").setRequestMarshaller(ProtoUtils.marshaller(ListOperationsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListOperationsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<CancelOperationRequest, Empty> cancelOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/CancelOperation").setRequestMarshaller(ProtoUtils.marshaller(CancelOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteOperationRequest, Empty> deleteOperationMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.longrunning.Operations/DeleteOperation").setRequestMarshaller(ProtoUtils.marshaller(DeleteOperationRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<GetOperationRequest, Operation> getOperationCallable;
    private final UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable;
    private final UnaryCallable<ListOperationsRequest, OperationsClient.ListOperationsPagedResponse> listOperationsPagedCallable;
    private final UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable;
    private final UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable;
    private final GrpcStubCallableFactory callableFactory;

    public static final GrpcOperationsStub create(OperationsStubSettings settings) throws IOException {
        return new GrpcOperationsStub(settings, ClientContext.create(settings));
    }

    public static final GrpcOperationsStub create(ClientContext clientContext) throws IOException {
        return new GrpcOperationsStub(OperationsStubSettings.newBuilder().build(), clientContext);
    }

    public static final GrpcOperationsStub create(ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        return new GrpcOperationsStub(OperationsStubSettings.newBuilder().build(), clientContext, callableFactory);
    }

    protected GrpcOperationsStub(OperationsStubSettings settings, ClientContext clientContext) throws IOException {
        this(settings, clientContext, new GrpcOperationsCallableFactory());
    }

    protected GrpcOperationsStub(OperationsStubSettings settings, ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        this.callableFactory = callableFactory;
        GrpcCallSettings<GetOperationRequest, Operation> getOperationTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(getOperationMethodDescriptor).build();
        GrpcCallSettings<ListOperationsRequest, ListOperationsResponse> listOperationsTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listOperationsMethodDescriptor).build();
        GrpcCallSettings<CancelOperationRequest, Empty> cancelOperationTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(cancelOperationMethodDescriptor).build();
        GrpcCallSettings<DeleteOperationRequest, Empty> deleteOperationTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteOperationMethodDescriptor).build();
        this.getOperationCallable = callableFactory.createUnaryCallable(getOperationTransportSettings, settings.getOperationSettings(), clientContext);
        this.listOperationsCallable = callableFactory.createUnaryCallable(listOperationsTransportSettings, settings.listOperationsSettings(), clientContext);
        this.listOperationsPagedCallable = callableFactory.createPagedCallable(listOperationsTransportSettings, settings.listOperationsSettings(), clientContext);
        this.cancelOperationCallable = callableFactory.createUnaryCallable(cancelOperationTransportSettings, settings.cancelOperationSettings(), clientContext);
        this.deleteOperationCallable = callableFactory.createUnaryCallable(deleteOperationTransportSettings, settings.deleteOperationSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override
    public UnaryCallable<GetOperationRequest, Operation> getOperationCallable() {
        return this.getOperationCallable;
    }

    @Override
    public UnaryCallable<ListOperationsRequest, OperationsClient.ListOperationsPagedResponse> listOperationsPagedCallable() {
        return this.listOperationsPagedCallable;
    }

    @Override
    public UnaryCallable<ListOperationsRequest, ListOperationsResponse> listOperationsCallable() {
        return this.listOperationsCallable;
    }

    @Override
    public UnaryCallable<CancelOperationRequest, Empty> cancelOperationCallable() {
        return this.cancelOperationCallable;
    }

    @Override
    public UnaryCallable<DeleteOperationRequest, Empty> deleteOperationCallable() {
        return this.deleteOperationCallable;
    }

    @Override
    public final void close() {
        this.shutdown();
    }

    @Override
    public void shutdown() {
        this.backgroundResources.shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.backgroundResources.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.backgroundResources.isTerminated();
    }

    @Override
    public void shutdownNow() {
        this.backgroundResources.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
        return this.backgroundResources.awaitTermination(duration, unit);
    }
}

