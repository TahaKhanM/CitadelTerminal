/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.PagedCallSettings;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.longrunning.CancelOperationRequest;
import com.google.longrunning.DeleteOperationRequest;
import com.google.longrunning.GetOperationRequest;
import com.google.longrunning.ListOperationsRequest;
import com.google.longrunning.ListOperationsResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.OperationsClient;
import com.google.longrunning.stub.OperationsStubSettings;
import com.google.protobuf.Empty;
import java.io.IOException;

@BetaApi
public class OperationsSettings
extends ClientSettings<OperationsSettings> {
    public UnaryCallSettings<GetOperationRequest, Operation> getOperationSettings() {
        return ((OperationsStubSettings)this.getStubSettings()).getOperationSettings();
    }

    public PagedCallSettings<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
        return ((OperationsStubSettings)this.getStubSettings()).listOperationsSettings();
    }

    public UnaryCallSettings<CancelOperationRequest, Empty> cancelOperationSettings() {
        return ((OperationsStubSettings)this.getStubSettings()).cancelOperationSettings();
    }

    public UnaryCallSettings<DeleteOperationRequest, Empty> deleteOperationSettings() {
        return ((OperationsStubSettings)this.getStubSettings()).deleteOperationSettings();
    }

    public static final OperationsSettings create(OperationsStubSettings stub) throws IOException {
        return new Builder(stub.toBuilder()).build();
    }

    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
        return OperationsStubSettings.defaultExecutorProviderBuilder();
    }

    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
        return OperationsStubSettings.defaultCredentialsProviderBuilder();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
        return OperationsStubSettings.defaultApiClientHeaderProviderBuilder();
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

    protected OperationsSettings(Builder settingsBuilder) throws IOException {
        super(settingsBuilder);
    }

    public static class Builder
    extends ClientSettings.Builder<OperationsSettings, Builder> {
        protected Builder() throws IOException {
            this((ClientContext)null);
        }

        protected Builder(ClientContext clientContext) {
            super(OperationsStubSettings.newBuilder(clientContext));
        }

        private static Builder createDefault() {
            return new Builder(OperationsStubSettings.newBuilder());
        }

        protected Builder(OperationsSettings settings) {
            super(settings.getStubSettings().toBuilder());
        }

        protected Builder(OperationsStubSettings.Builder stubSettings) {
            super(stubSettings);
        }

        public OperationsStubSettings.Builder getStubSettingsBuilder() {
            return (OperationsStubSettings.Builder)this.getStubSettings();
        }

        public Builder applyToAllUnaryMethods(ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
            ClientSettings.Builder.applyToAllUnaryMethods(this.getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
            return this;
        }

        public UnaryCallSettings.Builder<GetOperationRequest, Operation> getOperationSettings() {
            return this.getStubSettingsBuilder().getOperationSettings();
        }

        public PagedCallSettings.Builder<ListOperationsRequest, ListOperationsResponse, OperationsClient.ListOperationsPagedResponse> listOperationsSettings() {
            return this.getStubSettingsBuilder().listOperationsSettings();
        }

        public UnaryCallSettings.Builder<CancelOperationRequest, Empty> cancelOperationSettings() {
            return this.getStubSettingsBuilder().cancelOperationSettings();
        }

        public UnaryCallSettings.Builder<DeleteOperationRequest, Empty> deleteOperationSettings() {
            return this.getStubSettingsBuilder().deleteOperationSettings();
        }

        @Override
        public OperationsSettings build() throws IOException {
            return new OperationsSettings(this);
        }
    }
}

