/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.WatchdogProvider;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

public abstract class ClientSettings<SettingsT extends ClientSettings<SettingsT>> {
    private final StubSettings stubSettings;

    protected ClientSettings(Builder builder) throws IOException {
        this.stubSettings = builder.stubSettings.build();
    }

    public final StubSettings getStubSettings() {
        return this.stubSettings;
    }

    public final ExecutorProvider getExecutorProvider() {
        return this.stubSettings.getExecutorProvider();
    }

    public final TransportChannelProvider getTransportChannelProvider() {
        return this.stubSettings.getTransportChannelProvider();
    }

    public final CredentialsProvider getCredentialsProvider() {
        return this.stubSettings.getCredentialsProvider();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public final HeaderProvider getHeaderProvider() {
        return this.stubSettings.getHeaderProvider();
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    protected final HeaderProvider getInternalHeaderProvider() {
        return this.stubSettings.getInternalHeaderProvider();
    }

    public final ApiClock getClock() {
        return this.stubSettings.getClock();
    }

    public final String getEndpoint() {
        return this.stubSettings.getEndpoint();
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public final WatchdogProvider getWatchdogProvider() {
        return this.stubSettings.getStreamWatchdogProvider();
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nonnull
    public final Duration getWatchdogCheckInterval() {
        return this.stubSettings.getStreamWatchdogCheckInterval();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("executorProvider", this.getExecutorProvider()).add("transportChannelProvider", this.getTransportChannelProvider()).add("credentialsProvider", this.getCredentialsProvider()).add("headerProvider", this.getHeaderProvider()).add("internalHeaderProvider", this.getInternalHeaderProvider()).add("clock", this.getClock()).add("endpoint", this.getEndpoint()).add("watchdogProvider", this.getWatchdogProvider()).add("watchdogCheckInterval", this.getWatchdogCheckInterval()).toString();
    }

    public abstract <B extends Builder<SettingsT, B>> B toBuilder();

    public static abstract class Builder<SettingsT extends ClientSettings<SettingsT>, B extends Builder<SettingsT, B>> {
        private StubSettings.Builder stubSettings;

        protected Builder(ClientSettings settings) {
            this.stubSettings = settings.stubSettings.toBuilder();
        }

        protected Builder(StubSettings.Builder stubSettings) {
            this.stubSettings = stubSettings;
        }

        protected Builder() {
            this((StubSettings.Builder)null);
        }

        protected B self() {
            return (B)this;
        }

        protected StubSettings.Builder getStubSettings() {
            return this.stubSettings;
        }

        public B setExecutorProvider(ExecutorProvider executorProvider) {
            this.stubSettings.setExecutorProvider(executorProvider);
            return this.self();
        }

        public B setCredentialsProvider(CredentialsProvider credentialsProvider) {
            this.stubSettings.setCredentialsProvider(credentialsProvider);
            return this.self();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        public B setHeaderProvider(HeaderProvider headerProvider) {
            this.stubSettings.setHeaderProvider(headerProvider);
            return this.self();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        protected B setInternalHeaderProvider(HeaderProvider internalHeaderProvider) {
            this.stubSettings.setInternalHeaderProvider(internalHeaderProvider);
            return this.self();
        }

        public B setTransportChannelProvider(TransportChannelProvider transportChannelProvider) {
            this.stubSettings.setTransportChannelProvider(transportChannelProvider);
            return this.self();
        }

        public B setClock(ApiClock clock) {
            this.stubSettings.setClock(clock);
            return this.self();
        }

        public B setEndpoint(String endpoint) {
            this.stubSettings.setEndpoint(endpoint);
            return this.self();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public B setWatchdogProvider(@Nullable WatchdogProvider watchdogProvider) {
            this.stubSettings.setStreamWatchdogProvider(watchdogProvider);
            return this.self();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public B setWatchdogCheckInterval(@Nullable Duration checkInterval) {
            this.stubSettings.setStreamWatchdogCheckInterval(checkInterval);
            return this.self();
        }

        public ExecutorProvider getExecutorProvider() {
            return this.stubSettings.getExecutorProvider();
        }

        public TransportChannelProvider getTransportChannelProvider() {
            return this.stubSettings.getTransportChannelProvider();
        }

        public CredentialsProvider getCredentialsProvider() {
            return this.stubSettings.getCredentialsProvider();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        public HeaderProvider getHeaderProvider() {
            return this.stubSettings.getHeaderProvider();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        protected HeaderProvider getInternalHeaderProvider() {
            return this.stubSettings.getInternalHeaderProvider();
        }

        public ApiClock getClock() {
            return this.stubSettings.getClock();
        }

        public String getEndpoint() {
            return this.stubSettings.getEndpoint();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public WatchdogProvider getWatchdogProvider() {
            return this.stubSettings.getStreamWatchdogProvider();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public Duration getWatchdogCheckInterval() {
            return this.stubSettings.getStreamWatchdogCheckInterval();
        }

        protected static void applyToAllUnaryMethods(Iterable<UnaryCallSettings.Builder<?, ?>> methodSettingsBuilders, ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) {
            StubSettings.Builder.applyToAllUnaryMethods(methodSettingsBuilders, settingsUpdater);
        }

        public abstract SettingsT build() throws IOException;

        public String toString() {
            return MoreObjects.toStringHelper(this).add("executorProvider", this.getExecutorProvider()).add("transportChannelProvider", this.getTransportChannelProvider()).add("credentialsProvider", this.getCredentialsProvider()).add("headerProvider", this.getHeaderProvider()).add("internalHeaderProvider", this.getInternalHeaderProvider()).add("clock", this.getClock()).add("endpoint", this.getEndpoint()).add("watchdogProvider", this.getWatchdogProvider()).add("watchdogCheckInterval", this.getWatchdogCheckInterval()).toString();
        }
    }
}

