/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.base.Preconditions;

@BetaApi(value="The surface for long-running operations is not stable yet and may change in the future.")
public final class OperationCallSettings<RequestT, ResponseT, MetadataT> {
    private final UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings;
    private final TimedRetryAlgorithm pollingAlgorithm;
    private final ApiFunction<OperationSnapshot, ResponseT> responseTransformer;
    private final ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;

    public final UnaryCallSettings<RequestT, OperationSnapshot> getInitialCallSettings() {
        return this.initialCallSettings;
    }

    public final TimedRetryAlgorithm getPollingAlgorithm() {
        return this.pollingAlgorithm;
    }

    public final ApiFunction<OperationSnapshot, ResponseT> getResponseTransformer() {
        return this.responseTransformer;
    }

    public final ApiFunction<OperationSnapshot, MetadataT> getMetadataTransformer() {
        return this.metadataTransformer;
    }

    private OperationCallSettings(UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings, TimedRetryAlgorithm pollingAlgorithm, ApiFunction<OperationSnapshot, ResponseT> responseTransformer, ApiFunction<OperationSnapshot, MetadataT> metadataTransformer) {
        this.initialCallSettings = Preconditions.checkNotNull(initialCallSettings);
        this.pollingAlgorithm = Preconditions.checkNotNull(pollingAlgorithm);
        this.responseTransformer = Preconditions.checkNotNull(responseTransformer);
        this.metadataTransformer = metadataTransformer;
    }

    public static <RequestT, ResponseT, MetadataT> Builder<RequestT, ResponseT, MetadataT> newBuilder() {
        return new Builder();
    }

    public final Builder<RequestT, ResponseT, MetadataT> toBuilder() {
        return new Builder(this);
    }

    public static class Builder<RequestT, ResponseT, MetadataT> {
        private UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings;
        private TimedRetryAlgorithm pollingAlgorithm;
        private ApiFunction<OperationSnapshot, ResponseT> responseTransformer;
        private ApiFunction<OperationSnapshot, MetadataT> metadataTransformer;

        public Builder() {
        }

        public Builder(OperationCallSettings<RequestT, ResponseT, MetadataT> settings) {
            this.initialCallSettings = ((OperationCallSettings)settings).initialCallSettings.toBuilder().build();
            this.pollingAlgorithm = ((OperationCallSettings)settings).pollingAlgorithm;
            this.responseTransformer = ((OperationCallSettings)settings).responseTransformer;
            this.metadataTransformer = ((OperationCallSettings)settings).metadataTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setPollingAlgorithm(TimedRetryAlgorithm pollingAlgorithm) {
            this.pollingAlgorithm = pollingAlgorithm;
            return this;
        }

        public TimedRetryAlgorithm getPollingAlgorithm() {
            return this.pollingAlgorithm;
        }

        public Builder<RequestT, ResponseT, MetadataT> setInitialCallSettings(UnaryCallSettings<RequestT, OperationSnapshot> initialCallSettings) {
            this.initialCallSettings = initialCallSettings;
            return this;
        }

        public UnaryCallSettings<RequestT, OperationSnapshot> getInitialCallSettings() {
            return this.initialCallSettings;
        }

        public final ApiFunction<OperationSnapshot, ResponseT> getResponseTransformer() {
            return this.responseTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setResponseTransformer(ApiFunction<OperationSnapshot, ResponseT> responseTransformer) {
            this.responseTransformer = responseTransformer;
            return this;
        }

        public final ApiFunction<OperationSnapshot, MetadataT> getMetadataTransformer() {
            return this.metadataTransformer;
        }

        public Builder<RequestT, ResponseT, MetadataT> setMetadataTransformer(ApiFunction<OperationSnapshot, MetadataT> metadataTransformer) {
            this.metadataTransformer = metadataTransformer;
            return this;
        }

        public OperationCallSettings<RequestT, ResponseT, MetadataT> build() {
            return new OperationCallSettings(this.initialCallSettings, this.pollingAlgorithm, this.responseTransformer, this.metadataTransformer);
        }
    }
}

