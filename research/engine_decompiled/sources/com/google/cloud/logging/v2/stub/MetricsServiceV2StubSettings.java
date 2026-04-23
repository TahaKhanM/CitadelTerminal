/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.PageContext;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.PagedListDescriptor;
import com.google.api.gax.rpc.PagedListResponseFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.logging.v2.MetricsClient;
import com.google.cloud.logging.v2.stub.GrpcMetricsServiceV2Stub;
import com.google.cloud.logging.v2.stub.MetricsServiceV2Stub;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.threeten.bp.Duration;

@BetaApi
public class MetricsServiceV2StubSettings
extends StubSettings<MetricsServiceV2StubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)ImmutableList.builder().add("https://www.googleapis.com/auth/cloud-platform")).add("https://www.googleapis.com/auth/cloud-platform.read-only")).add("https://www.googleapis.com/auth/logging.admin")).add("https://www.googleapis.com/auth/logging.read")).add("https://www.googleapis.com/auth/logging.write")).build();
    private final PagedCallSettings<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings;
    private final UnaryCallSettings<GetLogMetricRequest, LogMetric> getLogMetricSettings;
    private final UnaryCallSettings<CreateLogMetricRequest, LogMetric> createLogMetricSettings;
    private final UnaryCallSettings<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings;
    private final UnaryCallSettings<DeleteLogMetricRequest, Empty> deleteLogMetricSettings;
    private static final PagedListDescriptor<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> LIST_LOG_METRICS_PAGE_STR_DESC = new PagedListDescriptor<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListLogMetricsRequest injectToken(ListLogMetricsRequest payload, String token) {
            return ListLogMetricsRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListLogMetricsRequest injectPageSize(ListLogMetricsRequest payload, int pageSize) {
            return ListLogMetricsRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListLogMetricsRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListLogMetricsResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<LogMetric> extractResources(ListLogMetricsResponse payload) {
            return payload.getMetricsList();
        }
    };
    private static final PagedListResponseFactory<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> LIST_LOG_METRICS_PAGE_STR_FACT = new PagedListResponseFactory<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse>(){

        @Override
        public ApiFuture<MetricsClient.ListLogMetricsPagedResponse> getFuturePagedResponse(UnaryCallable<ListLogMetricsRequest, ListLogMetricsResponse> callable, ListLogMetricsRequest request, ApiCallContext context, ApiFuture<ListLogMetricsResponse> futureResponse) {
            PageContext<ListLogMetricsRequest, ListLogMetricsResponse, LogMetric> pageContext = PageContext.create(callable, LIST_LOG_METRICS_PAGE_STR_DESC, request, context);
            return MetricsClient.ListLogMetricsPagedResponse.createAsync(pageContext, futureResponse);
        }
    };

    public PagedCallSettings<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings() {
        return this.listLogMetricsSettings;
    }

    public UnaryCallSettings<GetLogMetricRequest, LogMetric> getLogMetricSettings() {
        return this.getLogMetricSettings;
    }

    public UnaryCallSettings<CreateLogMetricRequest, LogMetric> createLogMetricSettings() {
        return this.createLogMetricSettings;
    }

    public UnaryCallSettings<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings() {
        return this.updateLogMetricSettings;
    }

    public UnaryCallSettings<DeleteLogMetricRequest, Empty> deleteLogMetricSettings() {
        return this.deleteLogMetricSettings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public MetricsServiceV2Stub createStub() throws IOException {
        if (this.getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcMetricsServiceV2Stub.create(this);
        }
        throw new UnsupportedOperationException("Transport not supported: " + this.getTransportChannelProvider().getTransportName());
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return InstantiatingExecutorProvider.newBuilder();
    }

    public static String getDefaultEndpoint() {
        return "logging.googleapis.com:443";
    }

    public static List<String> getDefaultServiceScopes() {
        return DEFAULT_SERVICE_SCOPES;
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return GoogleCredentialsProvider.newBuilder().setScopesToApply(DEFAULT_SERVICE_SCOPES);
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return InstantiatingGrpcChannelProvider.newBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return MetricsServiceV2StubSettings.defaultGrpcTransportProviderBuilder().build();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(MetricsServiceV2StubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
    }

    public static Builder newBuilder() {
        return Builder.createDefault();
    }

    public static Builder newBuilder(ClientContext clientContext) {
        return new Builder(clientContext);
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    protected MetricsServiceV2StubSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
        this.listLogMetricsSettings = settingsBuilder.listLogMetricsSettings().build();
        this.getLogMetricSettings = settingsBuilder.getLogMetricSettings().build();
        this.createLogMetricSettings = settingsBuilder.createLogMetricSettings().build();
        this.updateLogMetricSettings = settingsBuilder.updateLogMetricSettings().build();
        this.deleteLogMetricSettings = settingsBuilder.deleteLogMetricSettings().build();
    }

    public static class Builder
    extends StubSettings.Builder<MetricsServiceV2StubSettings, Builder> {
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final PagedCallSettings.Builder<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings;
        private final UnaryCallSettings.Builder<GetLogMetricRequest, LogMetric> getLogMetricSettings;
        private final UnaryCallSettings.Builder<CreateLogMetricRequest, LogMetric> createLogMetricSettings;
        private final UnaryCallSettings.Builder<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings;
        private final UnaryCallSettings.Builder<DeleteLogMetricRequest, Empty> deleteLogMetricSettings;
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;

        protected Builder() {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.listLogMetricsSettings = PagedCallSettings.newBuilder(LIST_LOG_METRICS_PAGE_STR_FACT);
            this.getLogMetricSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createLogMetricSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateLogMetricSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteLogMetricSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.listLogMetricsSettings, this.getLogMetricSettings, this.createLogMetricSettings, this.updateLogMetricSettings, this.deleteLogMetricSettings);
            Builder.initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext)null);
            builder.setTransportChannelProvider(MetricsServiceV2StubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(MetricsServiceV2StubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(MetricsServiceV2StubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(MetricsServiceV2StubSettings.getDefaultEndpoint());
            return Builder.initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            ((PagedCallSettings.Builder)builder.listLogMetricsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getLogMetricSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createLogMetricSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateLogMetricSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteLogMetricSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(MetricsServiceV2StubSettings settings) {
            super(settings);
            this.listLogMetricsSettings = settings.listLogMetricsSettings.toBuilder();
            this.getLogMetricSettings = settings.getLogMetricSettings.toBuilder();
            this.createLogMetricSettings = settings.createLogMetricSettings.toBuilder();
            this.updateLogMetricSettings = settings.updateLogMetricSettings.toBuilder();
            this.deleteLogMetricSettings = settings.deleteLogMetricSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.listLogMetricsSettings, this.getLogMetricSettings, this.createLogMetricSettings, this.updateLogMetricSettings, this.deleteLogMetricSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, settingsUpdater);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings() {
            return this.listLogMetricsSettings;
        }

        public UnaryCallSettings.Builder<GetLogMetricRequest, LogMetric> getLogMetricSettings() {
            return this.getLogMetricSettings;
        }

        public UnaryCallSettings.Builder<CreateLogMetricRequest, LogMetric> createLogMetricSettings() {
            return this.createLogMetricSettings;
        }

        public UnaryCallSettings.Builder<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings() {
            return this.updateLogMetricSettings;
        }

        public UnaryCallSettings.Builder<DeleteLogMetricRequest, Empty> deleteLogMetricSettings() {
            return this.deleteLogMetricSettings;
        }

        public MetricsServiceV2StubSettings build() throws IOException {
            return new MetricsServiceV2StubSettings(this);
        }

        static {
            ImmutableMap.Builder<String, Serializable> definitions = ImmutableMap.builder();
            definitions.put("idempotent", ImmutableSet.copyOf(Lists.newArrayList(StatusCode.Code.DEADLINE_EXCEEDED, StatusCode.Code.INTERNAL, StatusCode.Code.UNAVAILABLE)));
            definitions.put("non_idempotent", ImmutableSet.copyOf(Lists.newArrayList()));
            RETRYABLE_CODE_DEFINITIONS = definitions.build();
            definitions = ImmutableMap.builder();
            RetrySettings settings = null;
            settings = RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(20000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(20000L)).setTotalTimeout(Duration.ofMillis(600000L)).build();
            definitions.put("default", settings);
            RETRY_PARAM_DEFINITIONS = definitions.build();
        }
    }
}

