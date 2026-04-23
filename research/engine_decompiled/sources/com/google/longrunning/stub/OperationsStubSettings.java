/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning.stub;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.GrpcTransportChannel;
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
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.stub.GrpcOperationsStub;
import com.google.longrunning.stub.OperationsStub;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import org.threeten.bp.Duration;

@BetaApi
public class OperationsStubSettings
extends StubSettings<OperationsStubSettings> {
    private final UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings;
    private final PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings;
    private final UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings;
    private final UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings;
    private static final PagedListDescriptor<ListOperationsRequest, ListOperationsResponse, Operation> LIST_OPERATIONS_PAGE_STR_DESC = new PagedListDescriptor<ListOperationsRequest, ListOperationsResponse, Operation>(){

        @Override
        public String emptyToken() {
            return "";
        }

        @Override
        public ListOperationsRequest injectToken(ListOperationsRequest payload, String token) {
            return ListOperationsRequest.newBuilder(payload).setPageToken(token).build();
        }

        @Override
        public ListOperationsRequest injectPageSize(ListOperationsRequest payload, int pageSize) {
            return ListOperationsRequest.newBuilder(payload).setPageSize(pageSize).build();
        }

        @Override
        public Integer extractPageSize(ListOperationsRequest payload) {
            return payload.getPageSize();
        }

        @Override
        public String extractNextToken(ListOperationsResponse payload) {
            return payload.getNextPageToken();
        }

        @Override
        public Iterable<Operation> extractResources(ListOperationsResponse payload) {
            return payload.getOperationsList();
        }
    };
    private static final PagedListResponseFactory<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> LIST_OPERATIONS_PAGE_STR_FACT = new PagedListResponseFactory<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse>(){

        @Override
        public ApiFuture<OperationsClient.ListOperationsPagedResponse> getFuturePagedResponse(UnaryCallable<ListOperationsRequest, ListOperationsResponse> callable, ListOperationsRequest request, ApiCallContext context, ApiFuture<ListOperationsResponse> futureResponse) {
            PageContext<ListOperationsRequest, ListOperationsResponse, Operation> pageContext = PageContext.create(callable, LIST_OPERATIONS_PAGE_STR_DESC, request, context);
            return OperationsClient.ListOperationsPagedResponse.createAsync(pageContext, futureResponse);
        }
    };

    public UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings() {
        return this.getOperationSettings;
    }

    public PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
        return this.listOperationsSettings;
    }

    public UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings() {
        return this.cancelOperationSettings;
    }

    public UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings() {
        return this.deleteOperationSettings;
    }

    @BetaApi(value="A restructuring of stub classes is planned, so this may break in the future")
    public OperationsStub createStub() throws IOException {
        if (this.getTransportChannelProvider().getTransportName().equals(GrpcTransportChannel.getGrpcTransportName())) {
            return GrpcOperationsStub.create(this);
        }
        throw new UnsupportedOperationException("Transport not supported: " + this.getTransportChannelProvider().getTransportName());
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return InstantiatingExecutorProvider.newBuilder();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return GoogleCredentialsProvider.newBuilder();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ApiClientHeaderProvider.newBuilder().setGeneratedLibToken("gapic", GaxProperties.getLibraryVersion(OperationsStubSettings.class)).setTransportToken(GaxGrpcProperties.getGrpcTokenName(), GaxGrpcProperties.getGrpcVersion());
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

    protected OperationsStubSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
        this.getOperationSettings = settingsBuilder.getOperationSettings().build();
        this.listOperationsSettings = settingsBuilder.listOperationsSettings().build();
        this.cancelOperationSettings = settingsBuilder.cancelOperationSettings().build();
        this.deleteOperationSettings = settingsBuilder.deleteOperationSettings().build();
    }

    public static class Builder
    extends StubSettings.Builder<OperationsStubSettings, Builder> {
        private final ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders;
        private final UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings;
        private final PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings;
        private final UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings;
        private final UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings;
        private static final ImmutableMap<String, ImmutableSet<StatusCode.Code>> RETRYABLE_CODE_DEFINITIONS;
        private static final ImmutableMap<String, RetrySettings> RETRY_PARAM_DEFINITIONS;

        protected Builder() {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(clientContext);
            this.getOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.listOperationsSettings = PagedCallSettings.newBuilder(LIST_OPERATIONS_PAGE_STR_FACT);
            this.cancelOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.deleteOperationSettings = UnaryCallSettings.newUnaryCallSettingsBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.getOperationSettings, this.listOperationsSettings, this.cancelOperationSettings, this.deleteOperationSettings);
            Builder.initDefaults(this);
        }

        private static Builder createDefault() {
            Builder builder = new Builder((ClientContext)null);
            return Builder.initDefaults(builder);
        }

        private static Builder initDefaults(Builder builder) {
            builder.getOperationSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            ((PagedCallSettings.Builder)builder.listOperationsSettings().setRetryableCodes(RETRYABLE_CODE_DEFINITIONS.get("idempotent"))).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.cancelOperationSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            builder.deleteOperationSettings().setRetryableCodes((Set<StatusCode.Code>)RETRYABLE_CODE_DEFINITIONS.get("idempotent")).setRetrySettings(RETRY_PARAM_DEFINITIONS.get("default"));
            return builder;
        }

        protected Builder(OperationsStubSettings settings) {
            super(settings);
            this.getOperationSettings = settings.getOperationSettings.toBuilder();
            this.listOperationsSettings = settings.listOperationsSettings.toBuilder();
            this.cancelOperationSettings = settings.cancelOperationSettings.toBuilder();
            this.deleteOperationSettings = settings.deleteOperationSettings.toBuilder();
            this.unaryMethodSettingsBuilders = ImmutableList.of(this.getOperationSettings, this.listOperationsSettings, this.cancelOperationSettings, this.deleteOperationSettings);
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            StubSettings.Builder.applyToAllUnaryMethods(this.unaryMethodSettingsBuilders, settingsUpdater);
            return this;
        }

        public ImmutableList<UnaryCallSettings.Builder<?, ?>> unaryMethodSettingsBuilders() {
            return this.unaryMethodSettingsBuilders;
        }

        public UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings() {
            return this.getOperationSettings;
        }

        public PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
            return this.listOperationsSettings;
        }

        public UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings() {
            return this.cancelOperationSettings;
        }

        public UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings() {
            return this.deleteOperationSettings;
        }

        public OperationsStubSettings build() throws IOException {
            return new OperationsStubSettings(this);
        }

        static {
            ImmutableMap.Builder<String, Serializable> definitions = ImmutableMap.builder();
            definitions.put("idempotent", ImmutableSet.copyOf(Lists.newArrayList(StatusCode.Code.DEADLINE_EXCEEDED, StatusCode.Code.UNAVAILABLE)));
            definitions.put("non_idempotent", ImmutableSet.copyOf(Lists.newArrayList()));
            RETRYABLE_CODE_DEFINITIONS = definitions.build();
            definitions = ImmutableMap.builder();
            RetrySettings settings = null;
            settings = RetrySettings.newBuilder().setInitialRetryDelay(Duration.ofMillis(100L)).setRetryDelayMultiplier(1.3).setMaxRetryDelay(Duration.ofMillis(60000L)).setInitialRpcTimeout(Duration.ofMillis(90000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(90000L)).setTotalTimeout(Duration.ofMillis(600000L)).build();
            definitions.put("default", settings);
            RETRY_PARAM_DEFINITIONS = definitions.build();
        }
    }
}

