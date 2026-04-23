/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2.stub;

import com.google.api.MonitoredResourceDescriptor;
import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.FlowControlSettings;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.RequestBuilder;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.BatchedRequestIssuer;
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.BatchingDescriptor;
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
import com.google.cloud.logging.v2.LoggingClient;
import com.google.cloud.logging.v2.stub.GrpcLoggingServiceV2Stub;
import com.google.cloud.logging.v2.stub.LoggingServiceV2Stub;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.logging.v2.DeleteLogRequest;
import com.google.logging.v2.ListLogEntriesRequest;
import com.google.logging.v2.ListLogEntriesResponse;
import com.google.logging.v2.ListLogsRequest;
import com.google.logging.v2.ListLogsResponse;
import com.google.logging.v2.ListMonitoredResourceDescriptorsRequest;
import com.google.logging.v2.ListMonitoredResourceDescriptorsResponse;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.WriteLogEntriesRequest;
import com.google.logging.v2.WriteLogEntriesResponse;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.threeten.bp.Duration;

@BetaApi
public class LoggingServiceV2StubSettings
extends StubSettings<LoggingServiceV2StubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)ImmutableList.builder().add("https://www.googleapis.com/auth/cloud-platform")).add("https://www.googleapis.com/auth/cloud-platform.read-only")).add("https://www.googleapis.com/auth/logging.admin")).add("https://www.googleapis.com/auth/logging.read")).add("https://www.googleapis.com/auth/logging.write")).build();
    private final UnaryCallSettings<DeleteLogRequest, Empty> deleteLogSettings;
    private final BatchingCallSettings<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings;
    private final PagedCallSettings<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings;
    private final PagedCallSettings<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings;
    private final PagedCallSettings<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings;
    private static final PagedListDescriptor<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> LIST_LOG_ENTRIES_PAGE_STR_DESC = new PagedListDescriptor<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListLogEntriesRequest injectToken(ListLogEntriesRequest payload, String token) {
            return ListLogEntriesRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListLogEntriesRequest injectPageSize(ListLogEntriesRequest payload, int pageSize) {
            return ListLogEntriesRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListLogEntriesRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListLogEntriesResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<LogEntry> extractResources(ListLogEntriesResponse payload) {
            return payload.getEntriesList();
        }
    };
    private static final PagedListDescriptor<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> LIST_MONITORED_RESOURCE_DESCRIPTORS_PAGE_STR_DESC = new PagedListDescriptor<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListMonitoredResourceDescriptorsRequest injectToken(ListMonitoredResourceDescriptorsRequest payload, String token) {
            return ListMonitoredResourceDescriptorsRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListMonitoredResourceDescriptorsRequest injectPageSize(ListMonitoredResourceDescriptorsRequest payload, int pageSize) {
            return ListMonitoredResourceDescriptorsRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListMonitoredResourceDescriptorsRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListMonitoredResourceDescriptorsResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<MonitoredResourceDescriptor> extractResources(ListMonitoredResourceDescriptorsResponse payload) {
            return payload.getResourceDescriptorsList();
        }
    };
    private static final PagedListDescriptor<ListLogsRequest, ListLogsResponse, String> LIST_LOGS_PAGE_STR_DESC = new PagedListDescriptor<ListLogsRequest, ListLogsResponse, String>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListLogsRequest injectToken(ListLogsRequest payload, String token) {
            return ListLogsRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListLogsRequest injectPageSize(ListLogsRequest payload, int pageSize) {
            return ListLogsRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListLogsRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListLogsResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<String> extractResources(ListLogsResponse payload) {
            return payload.getLogNamesList();
        }
    };
    private static final PagedListResponseFactory<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> LIST_LOG_ENTRIES_PAGE_STR_FACT = new PagedListResponseFactory<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse>(){

        @Override
        public ApiFuture<LoggingClient.ListLogEntriesPagedResponse> getFuturePagedResponse(UnaryCallable<ListLogEntriesRequest, ListLogEntriesResponse> callable, ListLogEntriesRequest request, ApiCallContext context, ApiFuture<ListLogEntriesResponse> futureResponse) {
            PageContext<ListLogEntriesRequest, ListLogEntriesResponse, LogEntry> pageContext = PageContext.create(callable, LIST_LOG_ENTRIES_PAGE_STR_DESC, request, context);
            return LoggingClient.ListLogEntriesPagedResponse.createAsync(pageContext, futureResponse);
        }
    };
    private static final PagedListResponseFactory<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> LIST_MONITORED_RESOURCE_DESCRIPTORS_PAGE_STR_FACT = new PagedListResponseFactory<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse>(){

        @Override
        public ApiFuture<LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> getFuturePagedResponse(UnaryCallable<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse> callable, ListMonitoredResourceDescriptorsRequest request, ApiCallContext context, ApiFuture<ListMonitoredResourceDescriptorsResponse> futureResponse) {
            PageContext<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, MonitoredResourceDescriptor> pageContext = PageContext.create(callable, LIST_MONITORED_RESOURCE_DESCRIPTORS_PAGE_STR_DESC, request, context);
            return LoggingClient.ListMonitoredResourceDescriptorsPagedResponse.createAsync(pageContext, futureResponse);
        }
    };
    private static final PagedListResponseFactory<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> LIST_LOGS_PAGE_STR_FACT = new PagedListResponseFactory<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse>(){

        @Override
        public ApiFuture<LoggingClient.ListLogsPagedResponse> getFuturePagedResponse(UnaryCallable<ListLogsRequest, ListLogsResponse> callable, ListLogsRequest request, ApiCallContext context, ApiFuture<ListLogsResponse> futureResponse) {
            PageContext<ListLogsRequest, ListLogsResponse, String> pageContext = PageContext.create(callable, LIST_LOGS_PAGE_STR_DESC, request, context);
            return LoggingClient.ListLogsPagedResponse.createAsync(pageContext, futureResponse);
        }
    };
    private static final BatchingDescriptor<WriteLogEntriesRequest, WriteLogEntriesResponse> WRITE_LOG_ENTRIES_BATCHING_DESC = new BatchingDescriptor<WriteLogEntriesRequest, WriteLogEntriesResponse>(){

        @Override
        public PartitionKey getBatchPartitionKey(WriteLogEntriesRequest request) {
            return new PartitionKey(request.getLogName(), request.getResource(), request.getLabelsMap());
        }

        @Override
        public RequestBuilder<WriteLogEntriesRequest> getRequestBuilder() {
            return new RequestBuilder<WriteLogEntriesRequest>(){
                private WriteLogEntriesRequest.Builder builder;

                @Override
                public void appendRequest(WriteLogEntriesRequest request) {
                    if (this.builder == null) {
                        this.builder = request.toBuilder();
                    } else {
                        this.builder.addAllEntries(request.getEntriesList());
                    }
                }

                @Override
                public WriteLogEntriesRequest build() {
                    return this.builder.build();
                }
            };
        }

        @Override
        public void splitResponse(WriteLogEntriesResponse batchResponse, Collection<? extends BatchedRequestIssuer<WriteLogEntriesResponse>> batch) {
            boolean batchMessageIndex = false;
            for (BatchedRequestIssuer<WriteLogEntriesResponse> batchedRequestIssuer : batch) {
                WriteLogEntriesResponse response = WriteLogEntriesResponse.newBuilder().build();
                batchedRequestIssuer.setResponse(response);
            }
        }

        @Override
        public void splitException(Throwable throwable, Collection<? extends BatchedRequestIssuer<WriteLogEntriesResponse>> batch) {
            for (BatchedRequestIssuer<WriteLogEntriesResponse> batchedRequestIssuer : batch) {
                batchedRequestIssuer.setException(throwable);
            }
        }

        @Override
        public long countElements(WriteLogEntriesRequest request) {
            return request.getEntriesCount();
        }

        @Override
        public long countBytes(WriteLogEntriesRequest request) {
            return request.getSerializedSize();
        }
    };

    public UnaryCallSettings<DeleteLogRequest, Empty> deleteLogSettings() {
        return this.deleteLogSettings;
    }

    public BatchingCallSettings<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings() {
        return this.writeLogEntriesSettings;
    }

    public PagedCallSettings<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings() {
        return this.listLogEntriesSettings;
    }

    public PagedCallSettings<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings() {
        return this.listMonitoredResourceDescriptorsSettings;
    }

    public PagedCallSettings<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings() {
        return this.listLogsSettings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public LoggingServiceV2Stub createStub() throws IOException {
        if (this.getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcLoggingServiceV2Stub.create(this);
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
        return LoggingServiceV2StubSettings.defaultGrpcTransportProviderBuilder().build();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(LoggingServiceV2StubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected LoggingServiceV2StubSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
        this.deleteLogSettings = settingsBuilder.deleteLogSettings().build();
        this.writeLogEntriesSettings = settingsBuilder.writeLogEntriesSettings().build();
        this.listLogEntriesSettings = settingsBuilder.listLogEntriesSettings().build();
        this.listMonitoredResourceDescriptorsSettings = settingsBuilder.listMonitoredResourceDescriptorsSettings().build();
        this.listLogsSettings = settingsBuilder.listLogsSettings().build();
    }

    public static class Builder
    extends StubSettings.Builder<LoggingServiceV2StubSettings, Builder> {
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<DeleteLogRequest, Empty> deleteLogSettings;
        private final BatchingCallSettings.Builder<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings;
        private final PagedCallSettings.Builder<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings;
        private final PagedCallSettings.Builder<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings;
        private final PagedCallSettings.Builder<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings;
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;

        protected Builder() {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.deleteLogSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.writeLogEntriesSettings = BatchingCallSettings.newBuilder(WRITE_LOG_ENTRIES_BATCHING_DESC).setBatchingSettings(BatchingSettings.newBuilder().build());
            this.listLogEntriesSettings = PagedCallSettings.newBuilder(LIST_LOG_ENTRIES_PAGE_STR_FACT);
            this.listMonitoredResourceDescriptorsSettings = PagedCallSettings.newBuilder(LIST_MONITORED_RESOURCE_DESCRIPTORS_PAGE_STR_FACT);
            this.listLogsSettings = PagedCallSettings.newBuilder(LIST_LOGS_PAGE_STR_FACT);
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.deleteLogSettings, this.writeLogEntriesSettings, this.listLogEntriesSettings, this.listMonitoredResourceDescriptorsSettings, this.listLogsSettings);
            Builder.initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext)null);
            builder.setTransportChannelProvider(LoggingServiceV2StubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(LoggingServiceV2StubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(LoggingServiceV2StubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(LoggingServiceV2StubSettings.getDefaultEndpoint());
            return Builder.initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.deleteLogSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.writeLogEntriesSettings().setBatchingSettings(BatchingSettings.newBuilder().setElementCountThreshold(1000L).setRequestByteThreshold(0x100000L).setDelayThreshold(Duration.ofMillis(50L)).setFlowControlSettings(FlowControlSettings.newBuilder().setMaxOutstandingElementCount(100000L).setMaxOutstandingRequestBytes(0xA00000L).setLimitExceededBehavior(FlowController.LimitExceededBehavior.ThrowException).build()).build());
            ((BatchingCallSettings.Builder)builder.writeLogEntriesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("non_idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            ((PagedCallSettings.Builder)builder.listLogEntriesSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            ((PagedCallSettings.Builder)builder.listMonitoredResourceDescriptorsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            ((PagedCallSettings.Builder)builder.listLogsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(LoggingServiceV2StubSettings settings) {
            super(settings);
            this.deleteLogSettings = settings.deleteLogSettings.toBuilder();
            this.writeLogEntriesSettings = settings.writeLogEntriesSettings.toBuilder();
            this.listLogEntriesSettings = settings.listLogEntriesSettings.toBuilder();
            this.listMonitoredResourceDescriptorsSettings = settings.listMonitoredResourceDescriptorsSettings.toBuilder();
            this.listLogsSettings = settings.listLogsSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.deleteLogSettings, this.writeLogEntriesSettings, this.listLogEntriesSettings, this.listMonitoredResourceDescriptorsSettings, this.listLogsSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, settingsUpdater);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public UnaryCallSettings.Builder<DeleteLogRequest, Empty> deleteLogSettings() {
            return this.deleteLogSettings;
        }

        public BatchingCallSettings.Builder<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings() {
            return this.writeLogEntriesSettings;
        }

        public PagedCallSettings.Builder<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings() {
            return this.listLogEntriesSettings;
        }

        public PagedCallSettings.Builder<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings() {
            return this.listMonitoredResourceDescriptorsSettings;
        }

        public PagedCallSettings.Builder<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings() {
            return this.listLogsSettings;
        }

        public LoggingServiceV2StubSettings build() throws IOException {
            return new LoggingServiceV2StubSettings(this);
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
            settings = RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(20000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(20000L)).setTotalTimeout(Duration.ofMillis(600000L)).build();
            definitions.put("list", settings);
            RETRY_PARAM_DEFINITIONS = definitions.build();
        }
    }
}

