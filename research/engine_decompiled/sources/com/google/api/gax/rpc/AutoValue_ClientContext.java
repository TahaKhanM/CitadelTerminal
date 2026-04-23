/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.rpc;

import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ClientContext;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.Watchdog;
import com.google.auth.Credentials;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

final class AutoValue_ClientContext
extends ClientContext {
    private final List<BackgroundResource> backgroundResources;
    private final ScheduledExecutorService executor;
    private final Credentials credentials;
    private final TransportChannel transportChannel;
    private final Map<String, String> headers;
    private final Map<String, String> internalHeaders;
    private final ApiClock clock;
    private final ApiCallContext defaultCallContext;
    private final Watchdog streamWatchdog;
    private final Duration streamWatchdogCheckInterval;
    private final String endpoint;

    private AutoValue_ClientContext(List<BackgroundResource> backgroundResources, ScheduledExecutorService executor, @Nullable Credentials credentials, @Nullable TransportChannel transportChannel, Map<String, String> headers, Map<String, String> internalHeaders, ApiClock clock, ApiCallContext defaultCallContext, @Nullable Watchdog streamWatchdog, Duration streamWatchdogCheckInterval, @Nullable String endpoint) {
        if (backgroundResources == null) {
            throw new NullPointerException("Null backgroundResources");
        }
        this.backgroundResources = backgroundResources;
        if (executor == null) {
            throw new NullPointerException("Null executor");
        }
        this.executor = executor;
        this.credentials = credentials;
        this.transportChannel = transportChannel;
        if (headers == null) {
            throw new NullPointerException("Null headers");
        }
        this.headers = headers;
        if (internalHeaders == null) {
            throw new NullPointerException("Null internalHeaders");
        }
        this.internalHeaders = internalHeaders;
        if (clock == null) {
            throw new NullPointerException("Null clock");
        }
        this.clock = clock;
        if (defaultCallContext == null) {
            throw new NullPointerException("Null defaultCallContext");
        }
        this.defaultCallContext = defaultCallContext;
        this.streamWatchdog = streamWatchdog;
        if (streamWatchdogCheckInterval == null) {
            throw new NullPointerException("Null streamWatchdogCheckInterval");
        }
        this.streamWatchdogCheckInterval = streamWatchdogCheckInterval;
        this.endpoint = endpoint;
    }

    @Override
    public List<BackgroundResource> getBackgroundResources() {
        return this.backgroundResources;
    }

    @Override
    public ScheduledExecutorService getExecutor() {
        return this.executor;
    }

    @Override
    @Nullable
    public Credentials getCredentials() {
        return this.credentials;
    }

    @Override
    @Nullable
    public TransportChannel getTransportChannel() {
        return this.transportChannel;
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    protected Map<String, String> getInternalHeaders() {
        return this.internalHeaders;
    }

    @Override
    public ApiClock getClock() {
        return this.clock;
    }

    @Override
    public ApiCallContext getDefaultCallContext() {
        return this.defaultCallContext;
    }

    @Override
    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nullable
    public Watchdog getStreamWatchdog() {
        return this.streamWatchdog;
    }

    @Override
    @BetaApi(value="The surface for streaming is not stable yet and may change in the future.")
    @Nonnull
    public Duration getStreamWatchdogCheckInterval() {
        return this.streamWatchdogCheckInterval;
    }

    @Override
    @Nullable
    public String getEndpoint() {
        return this.endpoint;
    }

    public String toString() {
        return "ClientContext{backgroundResources=" + this.backgroundResources + ", executor=" + this.executor + ", credentials=" + this.credentials + ", transportChannel=" + this.transportChannel + ", headers=" + this.headers + ", internalHeaders=" + this.internalHeaders + ", clock=" + this.clock + ", defaultCallContext=" + this.defaultCallContext + ", streamWatchdog=" + this.streamWatchdog + ", streamWatchdogCheckInterval=" + this.streamWatchdogCheckInterval + ", endpoint=" + this.endpoint + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ClientContext) {
            ClientContext that = (ClientContext)o;
            return this.backgroundResources.equals(that.getBackgroundResources()) && this.executor.equals(that.getExecutor()) && (this.credentials == null ? that.getCredentials() == null : this.credentials.equals(that.getCredentials())) && (this.transportChannel == null ? that.getTransportChannel() == null : this.transportChannel.equals(that.getTransportChannel())) && this.headers.equals(that.getHeaders()) && this.internalHeaders.equals(that.getInternalHeaders()) && this.clock.equals(that.getClock()) && this.defaultCallContext.equals(that.getDefaultCallContext()) && (this.streamWatchdog == null ? that.getStreamWatchdog() == null : this.streamWatchdog.equals(that.getStreamWatchdog())) && this.streamWatchdogCheckInterval.equals(that.getStreamWatchdogCheckInterval()) && (this.endpoint == null ? that.getEndpoint() == null : this.endpoint.equals(that.getEndpoint()));
        }
        return false;
    }

    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.backgroundResources.hashCode();
        h *= 1000003;
        h ^= this.executor.hashCode();
        h *= 1000003;
        h ^= this.credentials == null ? 0 : this.credentials.hashCode();
        h *= 1000003;
        h ^= this.transportChannel == null ? 0 : this.transportChannel.hashCode();
        h *= 1000003;
        h ^= this.headers.hashCode();
        h *= 1000003;
        h ^= this.internalHeaders.hashCode();
        h *= 1000003;
        h ^= this.clock.hashCode();
        h *= 1000003;
        h ^= this.defaultCallContext.hashCode();
        h *= 1000003;
        h ^= this.streamWatchdog == null ? 0 : this.streamWatchdog.hashCode();
        h *= 1000003;
        h ^= this.streamWatchdogCheckInterval.hashCode();
        h *= 1000003;
        return h ^= this.endpoint == null ? 0 : this.endpoint.hashCode();
    }

    static final class Builder
    extends ClientContext.Builder {
        private List<BackgroundResource> backgroundResources;
        private ScheduledExecutorService executor;
        private Credentials credentials;
        private TransportChannel transportChannel;
        private Map<String, String> headers;
        private Map<String, String> internalHeaders;
        private ApiClock clock;
        private ApiCallContext defaultCallContext;
        private Watchdog streamWatchdog;
        private Duration streamWatchdogCheckInterval;
        private String endpoint;

        Builder() {
        }

        Builder(ClientContext source) {
            this.backgroundResources = source.getBackgroundResources();
            this.executor = source.getExecutor();
            this.credentials = source.getCredentials();
            this.transportChannel = source.getTransportChannel();
            this.headers = source.getHeaders();
            this.internalHeaders = source.getInternalHeaders();
            this.clock = source.getClock();
            this.defaultCallContext = source.getDefaultCallContext();
            this.streamWatchdog = source.getStreamWatchdog();
            this.streamWatchdogCheckInterval = source.getStreamWatchdogCheckInterval();
            this.endpoint = source.getEndpoint();
        }

        @Override
        public ClientContext.Builder setBackgroundResources(List<BackgroundResource> backgroundResources) {
            this.backgroundResources = backgroundResources;
            return this;
        }

        @Override
        public ClientContext.Builder setExecutor(ScheduledExecutorService executor) {
            this.executor = executor;
            return this;
        }

        @Override
        public ClientContext.Builder setCredentials(@Nullable Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        @Override
        public ClientContext.Builder setTransportChannel(@Nullable TransportChannel transportChannel) {
            this.transportChannel = transportChannel;
            return this;
        }

        @Override
        public ClientContext.Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        @Override
        public ClientContext.Builder setInternalHeaders(Map<String, String> internalHeaders) {
            this.internalHeaders = internalHeaders;
            return this;
        }

        @Override
        public ClientContext.Builder setClock(ApiClock clock) {
            this.clock = clock;
            return this;
        }

        @Override
        public ClientContext.Builder setDefaultCallContext(ApiCallContext defaultCallContext) {
            this.defaultCallContext = defaultCallContext;
            return this;
        }

        @Override
        public ClientContext.Builder setStreamWatchdog(@Nullable Watchdog streamWatchdog) {
            this.streamWatchdog = streamWatchdog;
            return this;
        }

        @Override
        public ClientContext.Builder setStreamWatchdogCheckInterval(Duration streamWatchdogCheckInterval) {
            this.streamWatchdogCheckInterval = streamWatchdogCheckInterval;
            return this;
        }

        @Override
        public ClientContext.Builder setEndpoint(@Nullable String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        @Override
        public ClientContext build() {
            String missing = "";
            if (this.backgroundResources == null) {
                missing = missing + " backgroundResources";
            }
            if (this.executor == null) {
                missing = missing + " executor";
            }
            if (this.headers == null) {
                missing = missing + " headers";
            }
            if (this.internalHeaders == null) {
                missing = missing + " internalHeaders";
            }
            if (this.clock == null) {
                missing = missing + " clock";
            }
            if (this.defaultCallContext == null) {
                missing = missing + " defaultCallContext";
            }
            if (this.streamWatchdogCheckInterval == null) {
                missing = missing + " streamWatchdogCheckInterval";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            return new AutoValue_ClientContext(this.backgroundResources, this.executor, this.credentials, this.transportChannel, this.headers, this.internalHeaders, this.clock, this.defaultCallContext, this.streamWatchdog, this.streamWatchdogCheckInterval, this.endpoint);
        }
    }
}

