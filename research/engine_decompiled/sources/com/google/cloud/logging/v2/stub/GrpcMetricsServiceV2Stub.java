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
import com.google.cloud.logging.v2.MetricsClient;
import com.google.cloud.logging.v2.stub.GrpcMetricsServiceV2CallableFactory;
import com.google.cloud.logging.v2.stub.MetricsServiceV2Stub;
import com.google.cloud.logging.v2.stub.MetricsServiceV2StubSettings;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.protobuf.Empty;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.ProtoUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
public class GrpcMetricsServiceV2Stub
extends MetricsServiceV2Stub {
    private static final MethodDescriptor<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.MetricsServiceV2/ListLogMetrics").setRequestMarshaller(ProtoUtils.marshaller(ListLogMetricsRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(ListLogMetricsResponse.getDefaultInstance())).build();
    private static final MethodDescriptor<GetLogMetricRequest, LogMetric> getLogMetricMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.MetricsServiceV2/GetLogMetric").setRequestMarshaller(ProtoUtils.marshaller(GetLogMetricRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogMetric.getDefaultInstance())).build();
    private static final MethodDescriptor<CreateLogMetricRequest, LogMetric> createLogMetricMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.MetricsServiceV2/CreateLogMetric").setRequestMarshaller(ProtoUtils.marshaller(CreateLogMetricRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogMetric.getDefaultInstance())).build();
    private static final MethodDescriptor<UpdateLogMetricRequest, LogMetric> updateLogMetricMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.MetricsServiceV2/UpdateLogMetric").setRequestMarshaller(ProtoUtils.marshaller(UpdateLogMetricRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(LogMetric.getDefaultInstance())).build();
    private static final MethodDescriptor<DeleteLogMetricRequest, Empty> deleteLogMetricMethodDescriptor = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName("google.logging.v2.MetricsServiceV2/DeleteLogMetric").setRequestMarshaller(ProtoUtils.marshaller(DeleteLogMetricRequest.getDefaultInstance())).setResponseMarshaller(ProtoUtils.marshaller(Empty.getDefaultInstance())).build();
    private final BackgroundResource backgroundResources;
    private final UnaryCallable<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsCallable;
    private final UnaryCallable<ListLogMetricsRequest, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsPagedCallable;
    private final UnaryCallable<GetLogMetricRequest, LogMetric> getLogMetricCallable;
    private final UnaryCallable<CreateLogMetricRequest, LogMetric> createLogMetricCallable;
    private final UnaryCallable<UpdateLogMetricRequest, LogMetric> updateLogMetricCallable;
    private final UnaryCallable<DeleteLogMetricRequest, Empty> deleteLogMetricCallable;
    private final GrpcStubCallableFactory callableFactory;

    public static final GrpcMetricsServiceV2Stub create(MetricsServiceV2StubSettings settings) throws IOException {
        return new GrpcMetricsServiceV2Stub(settings, ClientContext.create(settings));
    }

    public static final GrpcMetricsServiceV2Stub create(ClientContext clientContext) throws IOException {
        return new GrpcMetricsServiceV2Stub(MetricsServiceV2StubSettings.newBuilder().build(), clientContext);
    }

    public static final GrpcMetricsServiceV2Stub create(ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        return new GrpcMetricsServiceV2Stub(MetricsServiceV2StubSettings.newBuilder().build(), clientContext, callableFactory);
    }

    protected GrpcMetricsServiceV2Stub(MetricsServiceV2StubSettings settings, ClientContext clientContext) throws IOException {
        this(settings, clientContext, new GrpcMetricsServiceV2CallableFactory());
    }

    protected GrpcMetricsServiceV2Stub(MetricsServiceV2StubSettings settings, ClientContext clientContext, GrpcStubCallableFactory callableFactory) throws IOException {
        this.callableFactory = callableFactory;
        GrpcCallSettings<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(listLogMetricsMethodDescriptor).build();
        GrpcCallSettings<GetLogMetricRequest, LogMetric> getLogMetricTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(getLogMetricMethodDescriptor).build();
        GrpcCallSettings<CreateLogMetricRequest, LogMetric> createLogMetricTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(createLogMetricMethodDescriptor).build();
        GrpcCallSettings<UpdateLogMetricRequest, LogMetric> updateLogMetricTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(updateLogMetricMethodDescriptor).build();
        GrpcCallSettings<DeleteLogMetricRequest, Empty> deleteLogMetricTransportSettings = GrpcCallSettings.newBuilder().setMethodDescriptor(deleteLogMetricMethodDescriptor).build();
        this.listLogMetricsCallable = callableFactory.createUnaryCallable(listLogMetricsTransportSettings, settings.listLogMetricsSettings(), clientContext);
        this.listLogMetricsPagedCallable = callableFactory.createPagedCallable(listLogMetricsTransportSettings, settings.listLogMetricsSettings(), clientContext);
        this.getLogMetricCallable = callableFactory.createUnaryCallable(getLogMetricTransportSettings, settings.getLogMetricSettings(), clientContext);
        this.createLogMetricCallable = callableFactory.createUnaryCallable(createLogMetricTransportSettings, settings.createLogMetricSettings(), clientContext);
        this.updateLogMetricCallable = callableFactory.createUnaryCallable(updateLogMetricTransportSettings, settings.updateLogMetricSettings(), clientContext);
        this.deleteLogMetricCallable = callableFactory.createUnaryCallable(deleteLogMetricTransportSettings, settings.deleteLogMetricSettings(), clientContext);
        this.backgroundResources = new BackgroundResourceAggregation(clientContext.getBackgroundResources());
    }

    @Override
    public UnaryCallable<ListLogMetricsRequest, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsPagedCallable() {
        return this.listLogMetricsPagedCallable;
    }

    @Override
    public UnaryCallable<ListLogMetricsRequest, ListLogMetricsResponse> listLogMetricsCallable() {
        return this.listLogMetricsCallable;
    }

    @Override
    public UnaryCallable<GetLogMetricRequest, LogMetric> getLogMetricCallable() {
        return this.getLogMetricCallable;
    }

    @Override
    public UnaryCallable<CreateLogMetricRequest, LogMetric> createLogMetricCallable() {
        return this.createLogMetricCallable;
    }

    @Override
    public UnaryCallable<UpdateLogMetricRequest, LogMetric> updateLogMetricCallable() {
        return this.updateLogMetricCallable;
    }

    @Override
    public UnaryCallable<DeleteLogMetricRequest, Empty> deleteLogMetricCallable() {
        return this.deleteLogMetricCallable;
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

