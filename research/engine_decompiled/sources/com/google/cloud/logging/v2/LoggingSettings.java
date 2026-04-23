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
import com.google.api.gax.rpc.BatchingCallSettings;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.cloud.logging.v2.LoggingClient;
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
import java.io.IOException;
import java.util.List;

@BetaApi
public class LoggingSettings
extends ClientSettings<LoggingSettings> {
    public UnaryCallSettings<DeleteLogRequest, Empty> deleteLogSettings() {
        return ((LoggingServiceV2StubSettings)this.getStubSettings()).deleteLogSettings();
    }

    public BatchingCallSettings<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings() {
        return ((LoggingServiceV2StubSettings)this.getStubSettings()).writeLogEntriesSettings();
    }

    public PagedCallSettings<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings() {
        return ((LoggingServiceV2StubSettings)this.getStubSettings()).listLogEntriesSettings();
    }

    public PagedCallSettings<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings() {
        return ((LoggingServiceV2StubSettings)this.getStubSettings()).listMonitoredResourceDescriptorsSettings();
    }

    public PagedCallSettings<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings() {
        return ((LoggingServiceV2StubSettings)this.getStubSettings()).listLogsSettings();
    }

    public static final LoggingSettings create(LoggingServiceV2StubSettings stub) throws IOException {
        return new Builder(stub.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return LoggingServiceV2StubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return LoggingServiceV2StubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return LoggingServiceV2StubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return LoggingServiceV2StubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return LoggingServiceV2StubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return LoggingServiceV2StubSettings.defaultTransportChannelProvider();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return LoggingServiceV2StubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected LoggingSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
    }

    public static class Builder
    extends ClientSettings.Builder<LoggingSettings, Builder> {
        protected Builder() throws IOException {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(LoggingServiceV2StubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(LoggingServiceV2StubSettings.newBuilder());
        }

        protected Builder(LoggingSettings settings) {
            super(settings.getStubSettings().toBuilder());
        }

        protected Builder(LoggingServiceV2StubSettings.Builder stubSettings) {
            super(stubSettings);
        }

        public LoggingServiceV2StubSettings.Builder getStubSettingsBuilder() {
            return (LoggingServiceV2StubSettings.Builder)this.getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(this.getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
            return this;
        }

        public UnaryCallSettings.Builder<DeleteLogRequest, Empty> deleteLogSettings() {
            return this.getStubSettingsBuilder().deleteLogSettings();
        }

        public BatchingCallSettings.Builder<WriteLogEntriesRequest, WriteLogEntriesResponse> writeLogEntriesSettings() {
            return this.getStubSettingsBuilder().writeLogEntriesSettings();
        }

        public PagedCallSettings.Builder<ListLogEntriesRequest, ListLogEntriesResponse, LoggingClient.ListLogEntriesPagedResponse> listLogEntriesSettings() {
            return this.getStubSettingsBuilder().listLogEntriesSettings();
        }

        public PagedCallSettings.Builder<ListMonitoredResourceDescriptorsRequest, ListMonitoredResourceDescriptorsResponse, LoggingClient.ListMonitoredResourceDescriptorsPagedResponse> listMonitoredResourceDescriptorsSettings() {
            return this.getStubSettingsBuilder().listMonitoredResourceDescriptorsSettings();
        }

        public PagedCallSettings.Builder<ListLogsRequest, ListLogsResponse, LoggingClient.ListLogsPagedResponse> listLogsSettings() {
            return this.getStubSettingsBuilder().listLogsSettings();
        }

        @Override
        public LoggingSettings build() throws IOException {
            return new LoggingSettings(this);
        }
    }
}

