/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.BackgroundResourceAggregation;
import com.google.api.gax.grpc.GrpcCallSettings;
import com.google.api.gax.grpc.GrpcStubCallableFactory;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.LoggingClient;
import com.google.cloud.logging.v2.stub.GrpcLoggingServiceV2CallableFactory;
import com.google.cloud.logging.v2.stub.LoggingServiceV2Stub;
import com.google.cloud.logging.v2.stub.LoggingServiceV2StubSettings;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogsRequest;
import com.google.logging.v2.ListLogsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public class GrpcLoggingServiceV2Stub
extends LoggingServiceV2Stub {
    private static final MethodDescriptor<DeleteLogRequest, Empty> deleteLogMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.LoggingServiceV2/DeleteLog").setRequestMarshaller(ProtoUtils.marshaller(DeleteLogRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private static final MethodDescriptor<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.LoggingServiceV2/WriteLogEntries").setRequestMarshaller(ProtoUtils.marshaller(WriteLogEntriesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(WriteLogEntriesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.LoggingServiceV2/ListLogEntries").setRequestMarshaller(ProtoUtils.marshaller(ListLogEntriesRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListLogEntriesResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.LoggingServiceV2/ListMonitoredResourceDescriptors").setRequestMarshaller(ProtoUtils.marshaller(ListMonitoredResourceDescriptorsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListMonitoredResourceDescriptorsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<ListLogsRequest, ListLogsResponse> listLogsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.LoggingServiceV2/ListLogs").setRequestMarshaller(ProtoUtils.marshaller(ListLogsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListLogsResponse.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<DeleteLogRequest, Empty> deleteLogCallable;
    private final UnaryCallable<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesCallable;
    private final UnaryCallable<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesCallable;
    private final UnaryCallable<ListLogEntriesRequest, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesPagedCallable;
    private final UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsCallable;
    private final UnaryCallable<ListMonitoredResourceDescriptorsRequest, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsPagedCallable;
    private final UnaryCallable<ListLogsRequest, ListLogsResponse> listLogsCallable;
    private final UnaryCallable<ListLogsRequest, LoggingClient.ListLogsPagedResponse> listLogsPagedCallable;
    private final GrpcStubCallableFactory callableFactory;

    public static final GrpcLoggingServiceV2Stub create(LoggingServiceV2StubSettings settings) throws IOException {
        return new GrpcLoggingServiceV2Stub(settings, ClientContext.create(settings));
    }

    public static final GrpcLoggingServiceV2Stub create(ClientContext clientContext) throws IOException {
        return new GrpcLoggingServiceV2Stub(LoggingServiceV2StubSettings.newBuilder().build(), clientContext);
    }

    public static final GrpcLoggingServiceV2Stub create(ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        return new GrpcLoggingServiceV2Stub(LoggingServiceV2StubSettings.newBuilder().build(), clientContext, callableFactory);
    }

    protected GrpcLoggingServiceV2Stub(LoggingServiceV2StubSettings settings, ClientContext clientContext) throws IOException {
        this(settings, clientContext, new GrpcLoggingServiceV2CallableFactory());
    }

    protected GrpcLoggingServiceV2Stub(LoggingServiceV2StubSettings settings, ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        this.callableFactory = callableFactory;
        GrpcCallSettings<DeleteLogRequest, Empty> deleteLogTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteLogMethodDescriptor).build();
        GrpcCallSettings<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(writeLogEntriesMethodDescriptor).build();
        GrpcCallSettings<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listLogEntriesMethodDescriptor).build();
        GrpcCallSettings<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listMonitoredResourceDescriptorsMethodDescriptor).build();
        GrpcCallSettings<ListLogsRequest, ListLogsResponse> listLogsTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listLogsMethodDescriptor).build();
        this.deleteLogCallable = callableFactory.createUnaryCallable(deleteLogTransportSettings, settings.deleteLogSettings(), clientContext);
        this.writeLogEntriesCallable = callableFactory.createBatchingCallable(writeLogEntriesTransportSettings, settings.writeLogEntriesSettings(), clientContext);
        this.listLogEntriesCallable = callableFactory.createUnaryCallable(listLogEntriesTransportSettings, settings.listLogEntriesSettings(), clientContext);
        this.listLogEntriesPagedCallable = callableFactory.createPagedCallable(listLogEntriesTransportSettings, settings.listLogEntriesSettings(), clientContext);
        this.listMonitoredResourceDescriptorsCallable = callableFactory.createUnaryCallable(listMonitoredResourceDescriptorsTransportSettings, settings.listMonitoredResourceDescriptorsSettings(), clientContext);
        this.listMonitoredResourceDescriptorsPagedCallable = callableFactory.createPagedCallable(listMonitoredResourceDescriptorsTransportSettings, settings.listMonitoredResourceDescriptorsSettings(), clientContext);
        this.listLogsCallable = callableFactory.createUnaryCallable(listLogsTransportSettings, settings.listLogsSettings(), clientContext);
        this.listLogsPagedCallable = callableFactory.createPagedCallable(listLogsTransportSettings, settings.listLogsSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override
    public UnaryCallable<DeleteLogRequest, Empty> deleteLogCallable() {
        return this.deleteLogCallable;
    }

    @Override
    public UnaryCallable<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesCallable() {
        return this.writeLogEntriesCallable;
    }

    @Override
    public UnaryCallable<ListLogEntriesRequest, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesPagedCallable() {
        return this.listLogEntriesPagedCallable;
    }

    @Override
    public UnaryCallable<ListLogEntriesRequest, ListLogEntriesResponse> listLogEntriesCallable() {
        return this.listLogEntriesCallable;
    }

    @Override
    public UnaryCallable<ListMonitoredResourceDescriptorsRequest, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsPagedCallable() {
        return this.listMonitoredResourceDescriptorsPagedCallable;
    }

    @Override
    public UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> listMonitoredResourceDescriptorsCallable() {
        return this.listMonitoredResourceDescriptorsCallable;
    }

    @Override
    public UnaryCallable<ListLogsRequest, LoggingClient.ListLogsPagedResponse> listLogsPagedCallable() {
        return this.listLogsPagedCallable;
    }

    @Override
    public UnaryCallable<ListLogsRequest, ListLogsResponse> listLogsCallable() {
        return this.listLogsCallable;
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

