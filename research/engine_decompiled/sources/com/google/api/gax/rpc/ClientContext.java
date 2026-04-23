/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.NanoClock;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.core.ExecutorAsBackgroundResource;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.AutoValue_ClientContext;
import com.google.api.gax.rpc.ClientSettings;
import com.google.api.gax.rpc.StubSettings;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.api.gax.rpc.Watchdog;
import com.google.api.gax.rpc.WatchdogProvider;
import com.google.auth.Credentials;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

public abstract class ClientContext {
    public abstract List<BackgroundResource> getBackgroundResources();

    public abstract ScheduledExecutorService getExecutor();

    @Nullable
    public abstract Credentials getCredentials();

    @Nullable
    public abstract TransportChannel getTransportChannel();

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public abstract Map<String, String> getHeaders();

    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    protected abstract Map<String, String> getInternalHeaders();

    public abstract ApiClock getClock();

    public abstract ApiCallContext getDefaultCallContext();

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public abstract Watchdog getStreamWatchdog();

    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nonnull
    public abstract Duration getStreamWatchdogCheckInterval();

    @Nullable
    public abstract String getEndpoint();

    public static Builder newBuilder() {
        return new AutoValue_ClientContext.Builder().setBackgroundResources(Collections.emptyList()).setExecutor(Executors.newScheduledThreadPool(0)).setHeaders(Collections.emptyMap()).setInternalHeaders(Collections.emptyMap()).setClock(NanoClock.getDefaultClock()).setStreamWatchdog(null).setStreamWatchdogCheckInterval(Duration.ZERO);
    }

    public Builder toBuilder() {
        return new AutoValue_ClientContext.Builder(this);
    }

    public static ClientContext create(ClientSettings settings) throws IOException {
        return ClientContext.create(settings.getStubSettings());
    }

    public static ClientContext create(StubSettings settings) throws IOException {
        ImmutableList.Builder backgroundResources = ImmutableList.builder();
        ApiClock clock = settings.getClock();
        ExecutorProvider executorProvider = settings.getExecutorProvider();
        ScheduledExecutorService executor = executorProvider.getExecutor();
        if (executorProvider.shouldAutoClose()) {
            backgroundResources.add(new ExecutorAsBackgroundResource(executor));
        }
        Credentials credentials = settings.getCredentialsProvider().getCredentials();
        TransportChannelProvider transportChannelProvider = settings.getTransportChannelProvider();
        if (transportChannelProvider.needsExecutor()) {
            transportChannelProvider = transportChannelProvider.withExecutor(executor);
        }
        ImmutableMap<String, String> headers = ImmutableMap.builder().putAll(settings.getHeaderProvider().getHeaders()).putAll(settings.getInternalHeaderProvider().getHeaders()).build();
        if (transportChannelProvider.needsHeaders()) {
            transportChannelProvider = transportChannelProvider.withHeaders(headers);
        }
        if (transportChannelProvider.needsEndpoint()) {
            transportChannelProvider = transportChannelProvider.withEndpoint(settings.getEndpoint());
        }
        TransportChannel transportChannel = transportChannelProvider.getTransportChannel();
        if (transportChannelProvider.shouldAutoClose()) {
            backgroundResources.add(transportChannel);
        }
        ApiCallContext defaultCallContext = transportChannel.getEmptyCallContext().withTransportChannel(transportChannel);
        if (credentials != null) {
            defaultCallContext = defaultCallContext.withCredentials(credentials);
        }
        WatchdogProvider watchdogProvider = settings.getStreamWatchdogProvider();
        Watchdog watchdog = null;
        if (watchdogProvider != null) {
            if (watchdogProvider.needsCheckInterval()) {
                watchdogProvider = watchdogProvider.withCheckInterval(settings.getStreamWatchdogCheckInterval());
            }
            if (watchdogProvider.needsClock()) {
                watchdogProvider = watchdogProvider.withClock(clock);
            }
            if (watchdogProvider.needsExecutor()) {
                watchdogProvider = watchdogProvider.withExecutor(executor);
            }
            watchdog = watchdogProvider.getWatchdog();
        }
        return ClientContext.newBuilder().setBackgroundResources((List<BackgroundResource>)((Object)backgroundResources.build())).setExecutor(executor).setCredentials(credentials).setTransportChannel(transportChannel).setHeaders(ImmutableMap.copyOf(settings.getHeaderProvider().getHeaders())).setInternalHeaders(ImmutableMap.copyOf(settings.getInternalHeaderProvider().getHeaders())).setClock(clock).setDefaultCallContext(defaultCallContext).setEndpoint(settings.getEndpoint()).setStreamWatchdog(watchdog).setStreamWatchdogCheckInterval(settings.getStreamWatchdogCheckInterval()).build();
    }

    public static abstract class Builder {
        public abstract Builder setBackgroundResources(List<BackgroundResource> var1);

        public abstract Builder setExecutor(ScheduledExecutorService var1);

        public abstract Builder setCredentials(Credentials var1);

        public abstract Builder setTransportChannel(TransportChannel var1);

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        public abstract Builder setHeaders(Map<String, String> var1);

        @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
        protected abstract Builder setInternalHeaders(Map<String, String> var1);

        public abstract Builder setClock(ApiClock var1);

        public abstract Builder setDefaultCallContext(ApiCallContext var1);

        public abstract Builder setEndpoint(String var1);

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public abstract Builder setStreamWatchdog(Watchdog var1);

        @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
        public abstract Builder setStreamWatchdogCheckInterval(Duration var1);

        public abstract ClientContext build();
    }
}

