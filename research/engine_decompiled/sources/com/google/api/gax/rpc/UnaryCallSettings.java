/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Set;
import org.threeten.bp.Duration;

@InternalExtensionOnly
public class UnaryCallSettings<RequestT, ResponseT> {
    private final ImmutableSet<StatusCode.Code> retryableCodes;
    private final RetrySettings retrySettings;

    public final Set<StatusCode.Code> getRetryableCodes() {
        return this.retryableCodes;
    }

    public final RetrySettings getRetrySettings() {
        return this.retrySettings;
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newUnaryCallSettingsBuilder() {
        return new Builder();
    }

    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder(this);
    }

    protected UnaryCallSettings(Builder<RequestT, ResponseT> builder) {
        this.retryableCodes = ImmutableSet.copyOf(((Builder)builder).retryableCodes);
        this.retrySettings = ((Builder)builder).retrySettings;
    }

    public static class Builder<RequestT, ResponseT> {
        private Set<StatusCode.Code> retryableCodes;
        private RetrySettings retrySettings;

        protected Builder() {
            this.retryableCodes = Sets.newHashSet();
            this.retrySettings = RetrySettings.newBuilder().build();
        }

        protected Builder(UnaryCallSettings<RequestT, ResponseT> unaryCallSettings) {
            this.setRetryableCodes(((UnaryCallSettings)unaryCallSettings).retryableCodes);
            this.setRetrySettings(unaryCallSettings.getRetrySettings());
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> retryableCodes) {
            this.retryableCodes = Sets.newHashSet(retryableCodes);
            return this;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code ... codes) {
            this.setRetryableCodes(Sets.newHashSet(codes));
            return this;
        }

        public Builder<RequestT, ResponseT> setRetrySettings(RetrySettings retrySettings) {
            this.retrySettings = Preconditions.checkNotNull(retrySettings);
            return this;
        }

        public Builder<RequestT, ResponseT> setSimpleTimeoutNoRetries(Duration timeout) {
            this.setRetryableCodes(new StatusCode.Code[0]);
            this.setRetrySettings(RetrySettings.newBuilder().setTotalTimeout(timeout).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0).setMaxRetryDelay(Duration.ZERO).setInitialRpcTimeout(timeout).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(timeout).setMaxAttempts(1).build());
            return this;
        }

        public Set<StatusCode.Code> getRetryableCodes() {
            return this.retryableCodes;
        }

        public RetrySettings getRetrySettings() {
            return this.retrySettings;
        }

        public UnaryCallSettings<RequestT, ResponseT> build() {
            return new UnaryCallSettings(this);
        }
    }
}

