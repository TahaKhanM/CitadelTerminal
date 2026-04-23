/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging.v2;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.logging.v2.MetricsClient;
import com.google.cloud.logging.v2.stub.MetricsServiceV2StubSettings;
import com.google.logging.v2.CreateLogMetricRequest;
import com.google.logging.v2.DeleteLogMetricRequest;
import com.google.logging.v2.GetLogMetricRequest;
import com.google.logging.v2.ListLogMetricsRequest;
import com.google.logging.v2.ListLogMetricsResponse;
import com.google.logging.v2.LogMetric;
import com.google.logging.v2.UpdateLogMetricRequest;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;

@BetaApi
public class MetricsSettings
extends ClientSettings<MetricsSettings> {
    public PagedCallSettings<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings() {
        return ((MetricsServiceV2StubSettings)this.getStubSettings()).listLogMetricsSettings();
    }

    public UnaryCallSettings<GetLogMetricRequest, LogMetric> getLogMetricSettings() {
        return ((MetricsServiceV2StubSettings)this.getStubSettings()).getLogMetricSettings();
    }

    public UnaryCallSettings<CreateLogMetricRequest, LogMetric> createLogMetricSettings() {
        return ((MetricsServiceV2StubSettings)this.getStubSettings()).createLogMetricSettings();
    }

    public UnaryCallSettings<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings() {
        return ((MetricsServiceV2StubSettings)this.getStubSettings()).updateLogMetricSettings();
    }

    public UnaryCallSettings<DeleteLogMetricRequest, Empty> deleteLogMetricSettings() {
        return ((MetricsServiceV2StubSettings)this.getStubSettings()).deleteLogMetricSettings();
    }

    public static final MetricsSettings create(MetricsServiceV2StubSettings stub) throws IOException {
        return new Builder(stub.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return MetricsServiceV2StubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return MetricsServiceV2StubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return MetricsServiceV2StubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return MetricsServiceV2StubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return MetricsServiceV2StubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return MetricsServiceV2StubSettings.defaultTransportChannelProvider();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return MetricsServiceV2StubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected MetricsSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
    }

    public static class Builder
    extends ClientSettings.Builder<MetricsSettings, Builder> {
        protected Builder() throws IOException {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(MetricsServiceV2StubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(MetricsServiceV2StubSettings.newBuilder());
        }

        protected Builder(MetricsSettings settings) {
            super(settings.getStubSettings().toBuilder());
        }

        protected Builder(MetricsServiceV2StubSettings.Builder stubSettings) {
            super(stubSettings);
        }

        public MetricsServiceV2StubSettings.Builder getStubSettingsBuilder() {
            return (MetricsServiceV2StubSettings.Builder)this.getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(this.getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
            return this;
        }

        public PagedCallSettings.Builder<ListLogMetricsRequest, ListLogMetricsResponse, MetricsClient.ListLogMetricsPagedResponse> listLogMetricsSettings() {
            return this.getStubSettingsBuilder().listLogMetricsSettings();
        }

        public UnaryCallSettings.Builder<GetLogMetricRequest, LogMetric> getLogMetricSettings() {
            return this.getStubSettingsBuilder().getLogMetricSettings();
        }

        public UnaryCallSettings.Builder<CreateLogMetricRequest, LogMetric> createLogMetricSettings() {
            return this.getStubSettingsBuilder().createLogMetricSettings();
        }

        public UnaryCallSettings.Builder<UpdateLogMetricRequest, LogMetric> updateLogMetricSettings() {
            return this.getStubSettingsBuilder().updateLogMetricSettings();
        }

        public UnaryCallSettings.Builder<DeleteLogMetricRequest, Empty> deleteLogMetricSettings() {
            return this.getStubSettingsBuilder().deleteLogMetricSettings();
        }

        @Override
        public MetricsSettings build() throws IOException {
            return new MetricsSettings(this);
        }
    }
}

