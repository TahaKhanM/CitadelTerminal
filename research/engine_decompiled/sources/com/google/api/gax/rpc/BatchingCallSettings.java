/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.batching.BatchingSettings;
import com.google.api.gax.batching.FlowController;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.BatchingDescriptor;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.common.base.Preconditions;
import java.util.Set;

@BetaApi(value="The surface for batching is not stable yet and may change in the future.")
@InternalExtensionOnly
public final class BatchingCallSettings<RequestT, ResponseT>
extends UnaryCallSettings<RequestT, ResponseT> {
    private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
    private final BatchingSettings batchingSettings;
    private final FlowController flowController;

    public BatchingDescriptor<RequestT, ResponseT> getBatchingDescriptor() {
        return this.batchingDescriptor;
    }

    public BatchingSettings getBatchingSettings() {
        return this.batchingSettings;
    }

    public FlowController getFlowController() {
        return this.flowController;
    }

    private BatchingCallSettings(Builder<RequestT, ResponseT> builder) {
        super(builder);
        this.batchingDescriptor = ((Builder)builder).batchingDescriptor;
        this.batchingSettings = Preconditions.checkNotNull(((Builder)builder).batchingSettings);
        FlowController flowControllerToUse = ((Builder)builder).flowController;
        if (flowControllerToUse == null) {
            flowControllerToUse = new FlowController(this.batchingSettings.getFlowControlSettings());
        }
        this.flowController = flowControllerToUse;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
        return new Builder<RequestT, ResponseT>(batchingDescriptor);
    }

    @Override
    public final Builder<RequestT, ResponseT> toBuilder() {
        return new Builder(this);
    }

    public static class Builder<RequestT, ResponseT>
    extends UnaryCallSettings.Builder<RequestT, ResponseT> {
        private BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;
        private BatchingSettings batchingSettings;
        private FlowController flowController;

        public Builder(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
            this.batchingDescriptor = batchingDescriptor;
        }

        public Builder(BatchingCallSettings<RequestT, ResponseT> settings) {
            super(settings);
            this.batchingDescriptor = ((BatchingCallSettings)settings).batchingDescriptor;
            this.batchingSettings = ((BatchingCallSettings)settings).batchingSettings;
            this.flowController = ((BatchingCallSettings)settings).flowController;
        }

        public BatchingDescriptor<RequestT, ResponseT> getBatchingDescriptor() {
            return this.batchingDescriptor;
        }

        public Builder<RequestT, ResponseT> setBatchingSettings(BatchingSettings batchingSettings) {
            this.batchingSettings = batchingSettings;
            return this;
        }

        public BatchingSettings getBatchingSettings() {
            return this.batchingSettings;
        }

        public Builder<RequestT, ResponseT> setFlowController(FlowController flowController) {
            this.flowController = flowController;
            return this;
        }

        public FlowController getFlowController() {
            return this.flowController;
        }

        @Override
        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> retryableCodes) {
            super.setRetryableCodes(retryableCodes);
            return this;
        }

        @Override
        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code ... codes) {
            super.setRetryableCodes(codes);
            return this;
        }

        @Override
        public Builder<RequestT, ResponseT> setRetrySettings(RetrySettings retrySettings) {
            super.setRetrySettings(retrySettings);
            return this;
        }

        @Override
        public BatchingCallSettings<RequestT, ResponseT> build() {
            return new BatchingCallSettings(this);
        }
    }
}

