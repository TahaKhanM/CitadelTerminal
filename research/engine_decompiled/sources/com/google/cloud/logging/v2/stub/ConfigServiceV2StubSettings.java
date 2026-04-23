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
import com.google.cloud.logging.v2.ConfigClient;
import com.google.cloud.logging.v2.stub.ConfigServiceV2Stub;
import com.google.cloud.logging.v2.stub.GrpcConfigServiceV2Stub;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.logging.v2.CreateExclusionRequest;
import com.google.logging.v2.CreateSinkRequest;
import com.google.logging.v2.DeleteExclusionRequest;
import com.google.logging.v2.DeleteSinkRequest;
import com.google.logging.v2.GetExclusionRequest;
import com.google.logging.v2.GetSinkRequest;
import com.google.logging.v2.ListExclusionsRequest;
import com.google.logging.v2.ListExclusionsResponse;
import com.google.logging.v2.ListSinksRequest;
import com.google.logging.v2.ListSinksResponse;
import com.google.logging.v2.LogExclusion;
import com.google.logging.v2.LogSink;
import com.google.logging.v2.UpdateExclusionRequest;
import com.google.logging.v2.UpdateSinkRequest;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.threeten.bp.Duration;

@BetaApi
public class ConfigServiceV2StubSettings
extends StubSettings<ConfigServiceV2StubSettings> {
    private static final ImmutableList<String> DEFAULT_SERVICE_SCOPES = ((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)ImmutableList.builder().add("https://www.googleapis.com/auth/cloud-platform")).add("https://www.googleapis.com/auth/cloud-platform.read-only")).add("https://www.googleapis.com/auth/logging.admin")).add("https://www.googleapis.com/auth/logging.read")).add("https://www.googleapis.com/auth/logging.write")).build();
    private final PagedCallSettings<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings;
    private final UnaryCallSettings<GetSinkRequest, LogSink> getSinkSettings;
    private final UnaryCallSettings<CreateSinkRequest, LogSink> createSinkSettings;
    private final UnaryCallSettings<UpdateSinkRequest, LogSink> updateSinkSettings;
    private final UnaryCallSettings<DeleteSinkRequest, Empty> deleteSinkSettings;
    private final PagedCallSettings<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings;
    private final UnaryCallSettings<GetExclusionRequest, LogExclusion> getExclusionSettings;
    private final UnaryCallSettings<CreateExclusionRequest, LogExclusion> createExclusionSettings;
    private final UnaryCallSettings<UpdateExclusionRequest, LogExclusion> updateExclusionSettings;
    private final UnaryCallSettings<DeleteExclusionRequest, Empty> deleteExclusionSettings;
    private static final PagedListDescriptor<ListSinksRequest, ListSinksResponse, LogSink> LIST_SINKS_PAGE_STR_DESC = new PagedListDescriptor<ListSinksRequest, ListSinksResponse, LogSink>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListSinksRequest injectToken(ListSinksRequest payload, String token) {
            return ListSinksRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListSinksRequest injectPageSize(ListSinksRequest payload, int pageSize) {
            return ListSinksRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListSinksRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListSinksResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<LogSink> extractResources(ListSinksResponse payload) {
            return payload.getSinksList();
        }
    };
    private static final PagedListDescriptor<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> LIST_EXCLUSIONS_PAGE_STR_DESC = new PagedListDescriptor<ListExclusionsRequest, ListExclusionsResponse, LogExclusion>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListExclusionsRequest injectToken(ListExclusionsRequest payload, String token) {
            return ListExclusionsRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListExclusionsRequest injectPageSize(ListExclusionsRequest payload, int pageSize) {
            return ListExclusionsRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListExclusionsRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListExclusionsResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<LogExclusion> extractResources(ListExclusionsResponse payload) {
            return payload.getExclusionsList();
        }
    };
    private static final PagedListResponseFactory<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> LIST_SINKS_PAGE_STR_FACT = new PagedListResponseFactory<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse>(){

        @Override
        public ApiFuture<ConfigClient.ListSinksPagedResponse> getFuturePagedResponse(UnaryCallable<ListSinksRequest, ListSinksResponse> callable, ListSinksRequest request, ApiCallContext context, ApiFuture<ListSinksResponse> futureResponse) {
            PageContext<ListSinksRequest, ListSinksResponse, LogSink> pageContext = PageContext.create(callable, LIST_SINKS_PAGE_STR_DESC, request, context);
            return ConfigClient.ListSinksPagedResponse.createAsync(pageContext, futureResponse);
        }
    };
    private static final PagedListResponseFactory<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> LIST_EXCLUSIONS_PAGE_STR_FACT = new PagedListResponseFactory<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse>(){

        @Override
        public ApiFuture<ConfigClient.ListExclusionsPagedResponse> getFuturePagedResponse(UnaryCallable<ListExclusionsRequest, ListExclusionsResponse> callable, ListExclusionsRequest request, ApiCallContext context, ApiFuture<ListExclusionsResponse> futureResponse) {
            PageContext<ListExclusionsRequest, ListExclusionsResponse, LogExclusion> pageContext = PageContext.create(callable, LIST_EXCLUSIONS_PAGE_STR_DESC, request, context);
            return ConfigClient.ListExclusionsPagedResponse.createAsync(pageContext, futureResponse);
        }
    };

    public PagedCallSettings<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings() {
        return this.listSinksSettings;
    }

    public UnaryCallSettings<GetSinkRequest, LogSink> getSinkSettings() {
        return this.getSinkSettings;
    }

    public UnaryCallSettings<CreateSinkRequest, LogSink> createSinkSettings() {
        return this.createSinkSettings;
    }

    public UnaryCallSettings<UpdateSinkRequest, LogSink> updateSinkSettings() {
        return this.updateSinkSettings;
    }

    public UnaryCallSettings<DeleteSinkRequest, Empty> deleteSinkSettings() {
        return this.deleteSinkSettings;
    }

    public PagedCallSettings<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings() {
        return this.listExclusionsSettings;
    }

    public UnaryCallSettings<GetExclusionRequest, LogExclusion> getExclusionSettings() {
        return this.getExclusionSettings;
    }

    public UnaryCallSettings<CreateExclusionRequest, LogExclusion> createExclusionSettings() {
        return this.createExclusionSettings;
    }

    public UnaryCallSettings<UpdateExclusionRequest, LogExclusion> updateExclusionSettings() {
        return this.updateExclusionSettings;
    }

    public UnaryCallSettings<DeleteExclusionRequest, Empty> deleteExclusionSettings() {
        return this.deleteExclusionSettings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public ConfigServiceV2Stub createStub() throws IOException {
        if (this.getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcConfigServiceV2Stub.create(this);
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
        return ConfigServiceV2StubSettings.defaultGrpcTransportProviderBuilder().build();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(ConfigServiceV2StubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected ConfigServiceV2StubSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
        this.listSinksSettings = settingsBuilder.listSinksSettings().build();
        this.getSinkSettings = settingsBuilder.getSinkSettings().build();
        this.createSinkSettings = settingsBuilder.createSinkSettings().build();
        this.updateSinkSettings = settingsBuilder.updateSinkSettings().build();
        this.deleteSinkSettings = settingsBuilder.deleteSinkSettings().build();
        this.listExclusionsSettings = settingsBuilder.listExclusionsSettings().build();
        this.getExclusionSettings = settingsBuilder.getExclusionSettings().build();
        this.createExclusionSettings = settingsBuilder.createExclusionSettings().build();
        this.updateExclusionSettings = settingsBuilder.updateExclusionSettings().build();
        this.deleteExclusionSettings = settingsBuilder.deleteExclusionSettings().build();
    }

    public static class Builder
    extends StubSettings.Builder<ConfigServiceV2StubSettings, Builder> {
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final PagedCallSettings.Builder<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings;
        private final UnaryCallSettings.Builder<GetSinkRequest, LogSink> getSinkSettings;
        private final UnaryCallSettings.Builder<CreateSinkRequest, LogSink> createSinkSettings;
        private final UnaryCallSettings.Builder<UpdateSinkRequest, LogSink> updateSinkSettings;
        private final UnaryCallSettings.Builder<DeleteSinkRequest, Empty> deleteSinkSettings;
        private final PagedCallSettings.Builder<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings;
        private final UnaryCallSettings.Builder<GetExclusionRequest, LogExclusion> getExclusionSettings;
        private final UnaryCallSettings.Builder<CreateExclusionRequest, LogExclusion> createExclusionSettings;
        private final UnaryCallSettings.Builder<UpdateExclusionRequest, LogExclusion> updateExclusionSettings;
        private final UnaryCallSettings.Builder<DeleteExclusionRequest, Empty> deleteExclusionSettings;
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;

        protected Builder() {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.listSinksSettings = PagedCallSettings.newBuilder(LIST_SINKS_PAGE_STR_FACT);
            this.getSinkSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createSinkSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateSinkSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteSinkSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.listExclusionsSettings = PagedCallSettings.newBuilder(LIST_EXCLUSIONS_PAGE_STR_FACT);
            this.getExclusionSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.createExclusionSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.updateExclusionSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteExclusionSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.listSinksSettings, this.getSinkSettings, this.createSinkSettings, this.updateSinkSettings, this.deleteSinkSettings, this.listExclusionsSettings, this.getExclusionSettings, this.createExclusionSettings, this.updateExclusionSettings, this.deleteExclusionSettings);
            Builder.initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext)null);
            builder.setTransportChannelProvider(ConfigServiceV2StubSettings.defaultTransportChannelProvider());
            builder.setCredentialsProvider(ConfigServiceV2StubSettings.defaultCredentialsProviderBuilder().build());
            builder.setInternalHeaderProvider(ConfigServiceV2StubSettings.defaultApiClientHeaderProviderBuilder().build());
            builder.setEndpoint(ConfigServiceV2StubSettings.getDefaultEndpoint());
            return Builder.initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            ((PagedCallSettings.Builder)builder.listSinksSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getSinkSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createSinkSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateSinkSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteSinkSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            ((PagedCallSettings.Builder)builder.listExclusionsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.getExclusionSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.createExclusionSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.updateExclusionSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("non_idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteExclusionSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(ConfigServiceV2StubSettings settings) {
            super(settings);
            this.listSinksSettings = settings.listSinksSettings.toBuilder();
            this.getSinkSettings = settings.getSinkSettings.toBuilder();
            this.createSinkSettings = settings.createSinkSettings.toBuilder();
            this.updateSinkSettings = settings.updateSinkSettings.toBuilder();
            this.deleteSinkSettings = settings.deleteSinkSettings.toBuilder();
            this.listExclusionsSettings = settings.listExclusionsSettings.toBuilder();
            this.getExclusionSettings = settings.getExclusionSettings.toBuilder();
            this.createExclusionSettings = settings.createExclusionSettings.toBuilder();
            this.updateExclusionSettings = settings.updateExclusionSettings.toBuilder();
            this.deleteExclusionSettings = settings.deleteExclusionSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.listSinksSettings, this.getSinkSettings, this.createSinkSettings, this.updateSinkSettings, this.deleteSinkSettings, this.listExclusionsSettings, this.getExclusionSettings, this.createExclusionSettings, this.updateExclusionSettings, this.deleteExclusionSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, settingsUpdater);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public PagedCallSettings.Builder<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings() {
            return this.listSinksSettings;
        }

        public UnaryCallSettings.Builder<GetSinkRequest, LogSink> getSinkSettings() {
            return this.getSinkSettings;
        }

        public UnaryCallSettings.Builder<CreateSinkRequest, LogSink> createSinkSettings() {
            return this.createSinkSettings;
        }

        public UnaryCallSettings.Builder<UpdateSinkRequest, LogSink> updateSinkSettings() {
            return this.updateSinkSettings;
        }

        public UnaryCallSettings.Builder<DeleteSinkRequest, Empty> deleteSinkSettings() {
            return this.deleteSinkSettings;
        }

        public PagedCallSettings.Builder<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings() {
            return this.listExclusionsSettings;
        }

        public UnaryCallSettings.Builder<GetExclusionRequest, LogExclusion> getExclusionSettings() {
            return this.getExclusionSettings;
        }

        public UnaryCallSettings.Builder<CreateExclusionRequest, LogExclusion> createExclusionSettings() {
            return this.createExclusionSettings;
        }

        public UnaryCallSettings.Builder<UpdateExclusionRequest, LogExclusion> updateExclusionSettings() {
            return this.updateExclusionSettings;
        }

        public UnaryCallSettings.Builder<DeleteExclusionRequest, Empty> deleteExclusionSettings() {
            return this.deleteExclusionSettings;
        }

        public ConfigServiceV2StubSettings build() throws IOException {
            return new ConfigServiceV2StubSettings(this);
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
            definitions.put("write_sink", settings);
            RETRY_PARAM_DEFINITIONS = definitions.build();
        }
    }
}

