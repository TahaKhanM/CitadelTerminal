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
import com.google.cloud.logging.v2.ConfigClient;
import com.google.cloud.logging.v2.stub.ConfigServiceV2StubSettings;
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
import java.util.List;

@BetaApi
public class ConfigSettings
extends ClientSettings<ConfigSettings> {
    public PagedCallSettings<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).listSinksSettings();
    }

    public UnaryCallSettings<GetSinkRequest, LogSink> getSinkSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).getSinkSettings();
    }

    public UnaryCallSettings<CreateSinkRequest, LogSink> createSinkSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).createSinkSettings();
    }

    public UnaryCallSettings<UpdateSinkRequest, LogSink> updateSinkSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).updateSinkSettings();
    }

    public UnaryCallSettings<DeleteSinkRequest, Empty> deleteSinkSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).deleteSinkSettings();
    }

    public PagedCallSettings<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).listExclusionsSettings();
    }

    public UnaryCallSettings<GetExclusionRequest, LogExclusion> getExclusionSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).getExclusionSettings();
    }

    public UnaryCallSettings<CreateExclusionRequest, LogExclusion> createExclusionSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).createExclusionSettings();
    }

    public UnaryCallSettings<UpdateExclusionRequest, LogExclusion> updateExclusionSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).updateExclusionSettings();
    }

    public UnaryCallSettings<DeleteExclusionRequest, Empty> deleteExclusionSettings() {
        return ((ConfigServiceV2StubSettings)this.getStubSettings()).deleteExclusionSettings();
    }

    public static final ConfigSettings create(ConfigServiceV2StubSettings stub) throws IOException {
        return new Builder(stub.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return ConfigServiceV2StubSettings.defaultExecutorProviderBuilder();
    }

    public static String getDefaultEndpoint() {
        return ConfigServiceV2StubSettings.getDefaultEndpoint();
    }

    public static List<String> getDefaultServiceScopes() {
        return ConfigServiceV2StubSettings.getDefaultServiceScopes();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return ConfigServiceV2StubSettings.defaultCredentialsProviderBuilder();
    }

    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
        return ConfigServiceV2StubSettings.defaultGrpcTransportProviderBuilder();
    }

    public static TransportChannelProvider defaultTransportChannelProvider() {
        return ConfigServiceV2StubSettings.defaultTransportChannelProvider();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return ConfigServiceV2StubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected ConfigSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
    }

    public static class Builder
    extends ClientSettings.Builder<ConfigSettings, Builder> {
        protected Builder() throws IOException {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(ConfigServiceV2StubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(ConfigServiceV2StubSettings.newBuilder());
        }

        protected Builder(ConfigSettings settings) {
            super(settings.getStubSettings().toBuilder());
        }

        protected Builder(ConfigServiceV2StubSettings.Builder stubSettings) {
            super(stubSettings);
        }

        public ConfigServiceV2StubSettings.Builder getStubSettingsBuilder() {
            return (ConfigServiceV2StubSettings.Builder)this.getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(this.getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
            return this;
        }

        public PagedCallSettings.Builder<ListSinksRequest, ListSinksResponse, ConfigClient.ListSinksPagedResponse> listSinksSettings() {
            return this.getStubSettingsBuilder().listSinksSettings();
        }

        public UnaryCallSettings.Builder<GetSinkRequest, LogSink> getSinkSettings() {
            return this.getStubSettingsBuilder().getSinkSettings();
        }

        public UnaryCallSettings.Builder<CreateSinkRequest, LogSink> createSinkSettings() {
            return this.getStubSettingsBuilder().createSinkSettings();
        }

        public UnaryCallSettings.Builder<UpdateSinkRequest, LogSink> updateSinkSettings() {
            return this.getStubSettingsBuilder().updateSinkSettings();
        }

        public UnaryCallSettings.Builder<DeleteSinkRequest, Empty> deleteSinkSettings() {
            return this.getStubSettingsBuilder().deleteSinkSettings();
        }

        public PagedCallSettings.Builder<ListExclusionsRequest, ListExclusionsResponse, ConfigClient.ListExclusionsPagedResponse> listExclusionsSettings() {
            return this.getStubSettingsBuilder().listExclusionsSettings();
        }

        public UnaryCallSettings.Builder<GetExclusionRequest, LogExclusion> getExclusionSettings() {
            return this.getStubSettingsBuilder().getExclusionSettings();
        }

        public UnaryCallSettings.Builder<CreateExclusionRequest, LogExclusion> createExclusionSettings() {
            return this.getStubSettingsBuilder().createExclusionSettings();
        }

        public UnaryCallSettings.Builder<UpdateExclusionRequest, LogExclusion> updateExclusionSettings() {
            return this.getStubSettingsBuilder().updateExclusionSettings();
        }

        public UnaryCallSettings.Builder<DeleteExclusionRequest, Empty> deleteExclusionSettings() {
            return this.getStubSettingsBuilder().deleteExclusionSettings();
        }

        @Override
        public ConfigSettings build() throws IOException {
            return new ConfigSettings(this);
        }
    }
}

