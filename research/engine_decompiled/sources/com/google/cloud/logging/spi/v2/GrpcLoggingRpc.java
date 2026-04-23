/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.spi.v2;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.NoCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.grpc.GrpcTransportOptions;
import com.google.cloud.logging.LoggingException;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.spi.v2.LoggingRpc;
import com.google.cloud.logging.v2.ConfigClient;
import com.google.cloud.logging.v2.ConfigSettings;
import com.google.cloud.logging.v2.LoggingClient;
import com.google.cloud.logging.v2.LoggingSettings;
import com.google.cloud.logging.v2.MetricsClient;
import com.google.cloud.logging.v2.MetricsSettings;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.CreateSinkRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.DeleteSinkRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.GetSinkRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.ListSinksRequest;
import com.google.logging.v2.ListSinksResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;
import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public class GrpcLoggingRpc
implements LoggingRpc {
    private final ConfigClient configClient;
    private final LoggingClient loggingClient;
    private final MetricsClient metricsClient;
    private final ScheduledExecutorService executor;
    private final ClientContext clientContext;
    private final GrpcTransportOptions.ExecutorFactory<ScheduledExecutorService> executorFactory;
    private boolean closed;

    public GrpcLoggingRpc(final LoggingOptions options) throws IOException {
        GrpcTransportOptions transportOptions = (GrpcTransportOptions)options.getTransportOptions();
        this.executorFactory = transportOptions.getExecutorFactory();
        this.executor = this.executorFactory.get();
        try {
            if (options.getHost().contains("localhost") || NoCredentials.getInstance().equals(options.getCredentials())) {
                ManagedChannel managedChannel = ((ManagedChannelBuilder)((ManagedChannelBuilder)ManagedChannelBuilder.forTarget(options.getHost()).usePlaintext(true)).executor(this.executor)).build();
                GrpcTransportChannel transportChannel = GrpcTransportChannel.create(managedChannel);
                this.clientContext = ClientContext.newBuilder().setCredentials(null).setExecutor(this.executor).setTransportChannel(transportChannel).setDefaultCallContext(GrpcCallContext.of(managedChannel, CallOptions.DEFAULT)).setBackgroundResources(Collections.singletonList(transportChannel)).build();
            } else {
                LoggingSettingsBuilder settingsBuilder = new LoggingSettingsBuilder(LoggingSettings.newBuilder().build());
                settingsBuilder.setCredentialsProvider(GrpcTransportOptions.setUpCredentialsProvider(options));
                settingsBuilder.setTransportChannelProvider(GrpcTransportOptions.setUpChannelProvider(LoggingSettings.defaultGrpcTransportProviderBuilder(), options));
                ApiClientHeaderProvider internalHeaderProvider = LoggingSettings.defaultApiClientHeaderProviderBuilder().setClientLibToken(ServiceOptions.getGoogApiClientLibName(), GaxProperties.getLibraryVersion(options.getClass())).build();
                HeaderProvider headerProvider = options.getMergedHeaderProvider(internalHeaderProvider);
                settingsBuilder.setInternalHeaderProvider(headerProvider);
                this.clientContext = ClientContext.create(settingsBuilder.build());
            }
            ApiFunction retrySettingsSetter = new ApiFunction<UnaryCallSettings.Builder<?, ?>, Void>(){

                @Override
                public Void apply(UnaryCallSettings.Builder<?, ?> builder) {
                    builder.setRetrySettings(options.getRetrySettings());
                    return null;
                }
            };
            ConfigSettings.Builder confBuilder = ConfigSettings.newBuilder(this.clientContext).applyToAllUnaryMethods(retrySettingsSetter);
            LoggingSettings.Builder logBuilder = LoggingSettings.newBuilder(this.clientContext).applyToAllUnaryMethods(retrySettingsSetter);
            MetricsSettings.Builder metricsBuilder = MetricsSettings.newBuilder(this.clientContext).applyToAllUnaryMethods(retrySettingsSetter);
            BatchingSettings oldBatchSettings = logBuilder.writeLogEntriesSettings().getBatchingSettings();
            logBuilder.writeLogEntriesSettings().setBatchingSettings(oldBatchSettings.toBuilder().setFlowControlSettings(oldBatchSettings.getFlowControlSettings().toBuilder().setLimitExceededBehavior(FlowController.LimitExceededBehavior.Block).build()).build());
            this.configClient = ConfigClient.create(confBuilder.build());
            this.loggingClient = LoggingClient.create(logBuilder.build());
            this.metricsClient = MetricsClient.create(metricsBuilder.build());
        }
        catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    private static <V> ApiFuture<V> translate(ApiFuture<V> from2, boolean idempotent, StatusCode.Code ... returnNullOn) {
        final Set<Object> returnNullOnSet = returnNullOn.length > 0 ? EnumSet.of(returnNullOn[0], returnNullOn) : Collections.emptySet();
        return ApiFutures.catching(from2, ApiException.class, new ApiFunction<ApiException, V>(){

            @Override
            public V apply(ApiException exception) {
                if (returnNullOnSet.contains((Object)exception.getStatusCode().getCode())) {
                    return null;
                }
                throw new LoggingException(exception);
            }
        });
    }

    @Override
    public ApiFuture<LogSink> create(CreateSinkRequest request) {
        return GrpcLoggingRpc.translate(this.configClient.createSinkCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<LogSink> update(UpdateSinkRequest request) {
        return GrpcLoggingRpc.translate(this.configClient.updateSinkCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<LogSink> get(GetSinkRequest request) {
        return GrpcLoggingRpc.translate(this.configClient.getSinkCallable().futureCall(request), true, StatusCode.Code.NOT_FOUND);
    }

    @Override
    public ApiFuture<ListSinksResponse> list(ListSinksRequest request) {
        return GrpcLoggingRpc.translate(this.configClient.listSinksCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<Empty> delete(DeleteSinkRequest request) {
        return GrpcLoggingRpc.translate(this.configClient.deleteSinkCallable().futureCall(request), true, StatusCode.Code.NOT_FOUND);
    }

    @Override
    public ApiFuture<Empty> delete(DeleteLogRequest request) {
        return GrpcLoggingRpc.translate(this.loggingClient.deleteLogCallable().futureCall(request), true, StatusCode.Code.NOT_FOUND);
    }

    @Override
    public ApiFuture<WriteLogEntriesResponse> write(WriteLogEntriesRequest request) {
        return GrpcLoggingRpc.translate(this.loggingClient.writeLogEntriesCallable().futureCall(request), false, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<ListLogEntriesResponse> list(ListLogEntriesRequest request) {
        return GrpcLoggingRpc.translate(this.loggingClient.listLogEntriesCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<ListMonitoredResourceDescriptorsResponse> list(ListMonitoredResourceDescriptorsRequest request) {
        return GrpcLoggingRpc.translate(this.loggingClient.listMonitoredResourceDescriptorsCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<LogMetric> create(CreateLogMetricRequest request) {
        return GrpcLoggingRpc.translate(this.metricsClient.createLogMetricCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<LogMetric> update(UpdateLogMetricRequest request) {
        return GrpcLoggingRpc.translate(this.metricsClient.updateLogMetricCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<LogMetric> get(GetLogMetricRequest request) {
        return GrpcLoggingRpc.translate(this.metricsClient.getLogMetricCallable().futureCall(request), true, StatusCode.Code.NOT_FOUND);
    }

    @Override
    public ApiFuture<ListLogMetricsResponse> list(ListLogMetricsRequest request) {
        return GrpcLoggingRpc.translate(this.metricsClient.listLogMetricsCallable().futureCall(request), true, new StatusCode.Code[0]);
    }

    @Override
    public ApiFuture<Empty> delete(DeleteLogMetricRequest request) {
        return GrpcLoggingRpc.translate(this.metricsClient.deleteLogMetricCallable().futureCall(request), true, StatusCode.Code.NOT_FOUND);
    }

    @Override
    public void close() throws Exception {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.configClient.close();
        this.loggingClient.close();
        this.metricsClient.close();
        for (BackgroundResource resource : this.clientContext.getBackgroundResources()) {
            resource.close();
        }
        this.executorFactory.release(this.executor);
    }

    private static class LoggingSettingsBuilder
    extends LoggingSettings.Builder {
        private LoggingSettingsBuilder(LoggingSettings settings) {
            super(settings);
        }

        @Override
        protected LoggingSettings.Builder setInternalHeaderProvider(HeaderProvider internalHeaderProvider) {
            return (LoggingSettings.Builder)super.setInternalHeaderProvider(internalHeaderProvider);
        }
    }
}

