/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.SimpleStreamResumptionStrategy;
import com.google.api.gax.retrying.StreamResumptionStrategy;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.StreamingCallSettings;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nonnull;
import org.threeten.bp.Duration;

@BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
public final class ServerStreamingCallSettings<RequestT, ResponseT>
extends StreamingCallSettings<RequestT, ResponseT> {
    @Nonnull
    private final Set<StatusCode.Code> retryableCodes;
    @Nonnull
    private final RetrySettings retrySettings;
    @Nonnull
    private final StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;
    @Nonnull
    private final Duration idleTimeout;

    private ServerStreamingCallSettings(Builder<RequestT, ResponseT> builder) {
        this.retryableCodes = ImmutableSet.copyOf(((Builder)builder).retryableCodes);
        this.retrySettings = ((Builder)builder).retrySettings;
        this.resumptionStrategy = ((Builder)builder).resumptionStrategy;
        this.idleTimeout = ((Builder)builder).idleTimeout;
    }

    @Nonnull
    public Set<StatusCode.Code> getRetryableCodes() {
        return this.retryableCodes;
    }

    @Nonnull
    public RetrySettings getRetrySettings() {
        return this.retrySettings;
    }

    @Nonnull
    public StreamResumptionStrategy<RequestT, ResponseT> getResumptionStrategy() {
        return this.resumptionStrategy;
    }

    @Nonnull
    public Duration getIdleTimeout() {
        return this.idleTimeout;
    }

    @Override
    public Builder<RequestT, ResponseT> toBuilder() {
        return new Builder(this);
    }

    public static <RequestT, ResponseT> Builder<RequestT, ResponseT> newBuilder() {
        return new Builder();
    }

    public static class Builder<RequestT, ResponseT>
    extends StreamingCallSettings.Builder<RequestT, ResponseT> {
        @Nonnull
        private Set<StatusCode.Code> retryableCodes;
        @Nonnull
        private RetrySettings retrySettings;
        @Nonnull
        private StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy;
        @Nonnull
        private Duration idleTimeout;

        private Builder() {
            this.retryableCodes = ImmutableSet.of();
            this.retrySettings = RetrySettings.newBuilder().build();
            this.resumptionStrategy = new SimpleStreamResumptionStrategy();
            this.idleTimeout = Duration.ZERO;
        }

        private Builder(ServerStreamingCallSettings<RequestT, ResponseT> settings) {
            super(settings);
            this.retryableCodes = ((ServerStreamingCallSettings)settings).retryableCodes;
            this.retrySettings = ((ServerStreamingCallSettings)settings).retrySettings;
            this.resumptionStrategy = ((ServerStreamingCallSettings)settings).resumptionStrategy;
            this.idleTimeout = ((ServerStreamingCallSettings)settings).idleTimeout;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(StatusCode.Code ... codes) {
            this.setRetryableCodes(Sets.newHashSet(codes));
            return this;
        }

        public Builder<RequestT, ResponseT> setRetryableCodes(Set<StatusCode.Code> retryableCodes) {
            Preconditions.checkNotNull(retryableCodes);
            this.retryableCodes = Sets.newHashSet(retryableCodes);
            return this;
        }

        @Nonnull
        public Set<StatusCode.Code> getRetryableCodes() {
            return this.retryableCodes;
        }

        public Builder<RequestT, ResponseT> setRetrySettings(@Nonnull RetrySettings retrySettings) {
            Preconditions.checkNotNull(retrySettings);
            this.retrySettings = retrySettings;
            return this;
        }

        @Nonnull
        public RetrySettings getRetrySettings() {
            return this.retrySettings;
        }

        public Builder<RequestT, ResponseT> setSimpleTimeoutNoRetries(@Nonnull Duration timeout) {
            this.setRetryableCodes(new StatusCode.Code[0]);
            this.setRetrySettings(RetrySettings.newBuilder().setTotalTimeout(timeout).setInitialRetryDelay(Duration.ZERO).setRetryDelayMultiplier(1.0).setMaxRetryDelay(Duration.ZERO).setInitialRpcTimeout(Duration.ZERO).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ZERO).setMaxAttempts(1).build());
            return this;
        }

        public Builder<RequestT, ResponseT> setResumptionStrategy(@Nonnull StreamResumptionStrategy<RequestT, ResponseT> resumptionStrategy) {
            Preconditions.checkNotNull(resumptionStrategy);
            this.resumptionStrategy = Preconditions.checkNotNull(resumptionStrategy);
            return this;
        }

        @Nonnull
        public StreamResumptionStrategy<RequestT, ResponseT> getResumptionStrategy() {
            return this.resumptionStrategy;
        }

        @Nonnull
        public Duration getIdleTimeout() {
            return this.idleTimeout;
        }

        public Builder<RequestT, ResponseT> setIdleTimeout(@Nonnull Duration idleTimeout) {
            this.idleTimeout = Preconditions.checkNotNull(idleTimeout);
            return this;
        }

        @Override
        public ServerStreamingCallSettings<RequestT, ResponseT> build() {
            return new ServerStreamingCallSettings(this);
        }
    }
}

