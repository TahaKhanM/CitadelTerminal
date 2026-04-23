/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.FixedExecutorProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.FixedWatchdogProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.InstantiatingWatchdogProvider;
import com.google.api.gax.rpc.NoHeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.UnaryCallSettings;
import com.google.api.gax.rpc.WatchdogProvider;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

public abstract class StubSettings<SettingsT extends StubSettings<SettingsT>> {
    private final ExecutorProvider executorProvider;
    private final CredentialsProvider credentialsProvider;
    private final HeaderProvider headerProvider;
    private final HeaderProvider internalHeaderProvider;
    private final TransportChannelProvider transportChannelProvider;
    private final ApiClock clock;
    private final String endpoint;
    @Nullable
    private final WatchdogProvider streamWatchdogProvider;
    @Nonnull
    private final Duration streamWatchdogCheckInterval;

    protected StubSettings(Builder builder) {
        this.executorProvider = builder.executorProvider;
        this.transportChannelProvider = builder.transportChannelProvider;
        this.credentialsProvider = builder.credentialsProvider;
        this.headerProvider = builder.headerProvider;
        this.internalHeaderProvider = builder.internalHeaderProvider;
        this.clock = builder.clock;
        this.endpoint = builder.endpoint;
        this.streamWatchdogProvider = builder.streamWatchdogProvider;
        this.streamWatchdogCheckInterval = builder.streamWatchdogCheckInterval;
    }

    public final ExecutorProvider getExecutorProvider() {
        return this.executorProvider;
    }

    public final TransportChannelProvider getTransportChannelProvider() {
        return this.transportChannelProvider;
    }

    public final CredentialsProvider getCredentialsProvider() {
        return this.credentialsProvider;
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public final HeaderProvider getHeaderProvider() {
        return this.headerProvider;
    }

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    protected final HeaderProvider getInternalHeaderProvider() {
        return this.internalHeaderProvider;
    }

    public final ApiClock getClock() {
        return this.clock;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public final WatchdogProvider getStreamWatchdogProvider() {
        return this.streamWatchdogProvider;
    }

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nonnull
    public final Duration getStreamWatchdogCheckInterval() {
        return this.streamWatchdogCheckInterval;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("executorProvider", this.executorProvider).add("transportChannelProvider", this.transportChannelProvider).add("credentialsProvider", this.credentialsProvider).add("headerProvider", this.headerProvider).add("internalHeaderProvider", this.internalHeaderProvider).add("clock", this.clock).add("endpoint", this.endpoint).add("streamWatchdogProvider", this.streamWatchdogProvider).add("streamWatchdogCheckInterval", this.streamWatchdogCheckInterval).toString();
    }

    public abstract Builder toBuilder();

    public static abstract class Builder<SettingsT extends StubSettings<SettingsT>, B extends Builder<SettingsT, B>> {
        private ExecutorProvider executorProvider;
        private CredentialsProvider credentialsProvider;
        private HeaderProvider headerProvider;
        private HeaderProvider internalHeaderProvider;
        private TransportChannelProvider transportChannelProvider;
        private ApiClock clock;
        private String endpoint;
        @Nullable
        private WatchdogProvider streamWatchdogProvider;
        @Nonnull
        private Duration streamWatchdogCheckInterval;

        protected Builder(StubSettings settings) {
            this.executorProvider = settings.executorProvider;
            this.transportChannelProvider = settings.transportChannelProvider;
            this.credentialsProvider = settings.credentialsProvider;
            this.headerProvider = settings.headerProvider;
            this.internalHeaderProvider = settings.internalHeaderProvider;
            this.clock = settings.clock;
            this.endpoint = settings.endpoint;
            this.streamWatchdogProvider = settings.streamWatchdogProvider;
            this.streamWatchdogCheckInterval = settings.streamWatchdogCheckInterval;
        }

        protected Builder(ClientContext clientContext) {
            if (clientContext == null) {
                this.executorProvider = InstantiatingExecutorProvider.newBuilder().build();
                this.transportChannelProvider = null;
                this.credentialsProvider = NoCredentialsProvider.create();
                this.headerProvider = new NoHeaderProvider();
                this.internalHeaderProvider = new NoHeaderProvider();
                this.clock = NanoClock.getDefaultClock();
                this.endpoint = null;
                this.streamWatchdogProvider = InstantiatingWatchdogProvider.create();
                this.streamWatchdogCheckInterval = Duration.ofSeconds(10L);
            } else {
                this.executorProvider = FixedExecutorProvider.create(clientContext.getExecutor());
                this.transportChannelProvider = FixedTransportChannelProvider.create(clientContext.getTransportChannel());
                this.credentialsProvider = FixedCredentialsProvider.create(clientContext.getCredentials());
                this.headerProvider = FixedHeaderProvider.create(clientContext.getHeaders());
                this.internalHeaderProvider = FixedHeaderProvider.create(clientContext.getInternalHeaders());
                this.clock = clientContext.getClock();
                this.endpoint = clientContext.getEndpoint();
                this.streamWatchdogProvider = FixedWatchdogProvider.create(clientContext.getStreamWatchdog());
                this.streamWatchdogCheckInterval = clientContext.getStreamWatchdogCheckInterval();
            }
        }

        protected Builder() {
            this((ClientContext)null);
        }

        protected B self() {
            return (B)this;
        }

        public B setExecutorProvider(ExecutorProvider executorProvider) {
            this.executorProvider = executorProvider;
            return this.self();
        }

        public B setCredentialsProvider(CredentialsProvider credentialsProvider) {
            this.credentialsProvider = Preconditions.checkNotNull(credentialsProvider);
            return this.self();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        public B setHeaderProvider(HeaderProvider headerProvider) {
            this.headerProvider = headerProvider;
            return this.self();
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        protected B setInternalHeaderProvider(HeaderProvider internalHeaderProvider) {
            this.internalHeaderProvider = internalHeaderProvider;
            return this.self();
        }

        public B setTransportChannelProvider(TransportChannelProvider transportChannelProvider) {
            this.transportChannelProvider = transportChannelProvider;
            return this.self();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public B setStreamWatchdogProvider(@Nullable WatchdogProvider streamWatchdogProvider) {
            this.streamWatchdogProvider = streamWatchdogProvider;
            return this.self();
        }

        public B setClock(ApiClock clock) {
            this.clock = clock;
            return this.self();
        }

        public B setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this.self();
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public B setStreamWatchdogCheckInterval(@Nonnull Duration checkInterval) {
            Preconditions.checkNotNull(checkInterval);
            this.streamWatchdogCheckInterval = checkInterval;
            return this.self();
        }

        public ExecutorProvider getExecutorProvider() {
            return this.executorProvider;
        }

        public TransportChannelProvider getTransportChannelProvider() {
            return this.transportChannelProvider;
        }

        public CredentialsProvider getCredentialsProvider() {
            return this.credentialsProvider;
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        public HeaderProvider getHeaderProvider() {
            return this.headerProvider;
        }

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        protected HeaderProvider getInternalHeaderProvider() {
            return this.internalHeaderProvider;
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        @Nullable
        public WatchdogProvider getStreamWatchdogProvider() {
            return this.streamWatchdogProvider;
        }

        public ApiClock getClock() {
            return this.clock;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        @Nonnull
        public Duration getStreamWatchdogCheckInterval() {
            return this.streamWatchdogCheckInterval;
        }

        protected static void applyToAllUnaryMethods(Iterable<UnaryCallSettings.Builder<?, ?>> methodSettingsBuilders, ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) {
            for (UnaryCallSettings.Builder<?, ?> settingsBuilder : methodSettingsBuilders) {
                settingsUpdater.apply(settingsBuilder);
            }
        }

        public abstract <B extends StubSettings<B>> StubSettings<B> build() throws IOException;

        public String toString() {
            return MoreObjects.toStringHelper(this).add("executorProvider", this.executorProvider).add("transportChannelProvider", this.transportChannelProvider).add("credentialsProvider", this.credentialsProvider).add("headerProvider", this.headerProvider).add("internalHeaderProvider", this.internalHeaderProvider).add("clock", this.clock).add("endpoint", this.endpoint).add("streamWatchdogProvider", this.streamWatchdogProvider).add("streamWatchdogCheckInterval", this.streamWatchdogCheckInterval).toString();
        }
    }
}

