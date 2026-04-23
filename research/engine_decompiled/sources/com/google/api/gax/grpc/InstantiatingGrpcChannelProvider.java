/*
 * Decompiled with CFR 0.152.
 */
package com.google.api.gax.grpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.FixedExecutorProvider;
import com.google.api.gax.grpc.ChannelPool;
import com.google.api.gax.grpc.GrpcHeaderInterceptor;
import com.google.api.gax.grpc.GrpcInterceptorProvider;
import com.google.api.gax.grpc.GrpcMetadataHandlerInterceptor;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannel;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

@InternalExtensionOnly
public final class InstantiatingGrpcChannelProvider
implements TransportChannelProvider {
    private final int processorCount;
    private final ExecutorProvider executorProvider;
    private final HeaderProvider headerProvider;
    private final String endpoint;
    @Nullable
    private final GrpcInterceptorProvider interceptorProvider;
    @Nullable
    private final Integer maxInboundMessageSize;
    @Nullable
    private final Duration keepAliveTime;
    @Nullable
    private final Duration keepAliveTimeout;
    @Nullable
    private final Boolean keepAliveWithoutCalls;
    @Nullable
    private final Integer poolSize;

    private InstantiatingGrpcChannelProvider(Builder builder) {
        this.processorCount = builder.processorCount;
        this.executorProvider = builder.executorProvider;
        this.headerProvider = builder.headerProvider;
        this.endpoint = builder.endpoint;
        this.interceptorProvider = builder.interceptorProvider;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.keepAliveTime = builder.keepAliveTime;
        this.keepAliveTimeout = builder.keepAliveTimeout;
        this.keepAliveWithoutCalls = builder.keepAliveWithoutCalls;
        this.poolSize = builder.poolSize;
    }

    @Override
    public boolean needsExecutor() {
        return this.executorProvider == null;
    }

    @Override
    public TransportChannelProvider withExecutor(ScheduledExecutorService executor) {
        return this.toBuilder().setExecutorProvider(FixedExecutorProvider.create(executor)).build();
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public boolean needsHeaders() {
        return this.headerProvider == null;
    }

    @Override
    @BetaApi(value="The surface for customizing headers is not stable yet and may change in the future.")
    public TransportChannelProvider withHeaders(Map<String, String> headers) {
        return this.toBuilder().setHeaderProvider(FixedHeaderProvider.create(headers)).build();
    }

    @Override
    public String getTransportName() {
        return GrpcTransportChannel.getGrpcTransportName();
    }

    @Override
    public boolean needsEndpoint() {
        return this.endpoint == null;
    }

    @Override
    public TransportChannelProvider withEndpoint(String endpoint) {
        InstantiatingGrpcChannelProvider.validateEndpoint(endpoint);
        return this.toBuilder().setEndpoint(endpoint).build();
    }

    @Override
    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public boolean acceptsPoolSize() {
        return this.poolSize == null;
    }

    @Override
    @BetaApi(value="The surface for customizing pool size is not stable yet and may change in the future.")
    public TransportChannelProvider withPoolSize(int size2) {
        Preconditions.checkState(this.acceptsPoolSize(), "pool size already set to %s", (Object)this.poolSize);
        return this.toBuilder().setPoolSize(size2).build();
    }

    @Override
    public TransportChannel getTransportChannel() throws IOException {
        if (this.needsExecutor()) {
            throw new IllegalStateException("getTransportChannel() called when needsExecutor() is true");
        }
        if (this.needsHeaders()) {
            throw new IllegalStateException("getTransportChannel() called when needsHeaders() is true");
        }
        if (this.needsEndpoint()) {
            throw new IllegalStateException("getTransportChannel() called when needsEndpoint() is true");
        }
        return this.createChannel();
    }

    private TransportChannel createChannel() throws IOException {
        ManagedChannel outerChannel;
        if (this.poolSize == null || this.poolSize == 1) {
            outerChannel = this.createSingleChannel();
        } else {
            ImmutableList.Builder channels = ImmutableList.builder();
            for (int i = 0; i < this.poolSize; ++i) {
                channels.add(this.createSingleChannel());
            }
            outerChannel = new ChannelPool((List<ManagedChannel>)((Object)channels.build()));
        }
        return GrpcTransportChannel.create(outerChannel);
    }

    private ManagedChannel createSingleChannel() throws IOException {
        ScheduledExecutorService executor = this.executorProvider.getExecutor();
        GrpcHeaderInterceptor headerInterceptor = new GrpcHeaderInterceptor(this.headerProvider.getHeaders());
        GrpcMetadataHandlerInterceptor metadataHandlerInterceptor = new GrpcMetadataHandlerInterceptor();
        int colon2 = this.endpoint.indexOf(58);
        if (colon2 < 0) {
            throw new IllegalStateException("invalid endpoint - should have been validated: " + this.endpoint);
        }
        int port = Integer.parseInt(this.endpoint.substring(colon2 + 1));
        String serviceAddress = this.endpoint.substring(0, colon2);
        Object builder = ((ManagedChannelBuilder)((ManagedChannelBuilder)((ManagedChannelBuilder)ManagedChannelBuilder.forAddress(serviceAddress, port).intercept(headerInterceptor)).intercept(metadataHandlerInterceptor)).userAgent(headerInterceptor.getUserAgentHeader())).executor(executor);
        if (this.maxInboundMessageSize != null) {
            ((ManagedChannelBuilder)builder).maxInboundMessageSize(this.maxInboundMessageSize);
        }
        if (this.keepAliveTime != null) {
            ((ManagedChannelBuilder)builder).keepAliveTime(this.keepAliveTime.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (this.keepAliveTimeout != null) {
            ((ManagedChannelBuilder)builder).keepAliveTimeout(this.keepAliveTimeout.toMillis(), TimeUnit.MILLISECONDS);
        }
        if (this.keepAliveWithoutCalls != null) {
            ((ManagedChannelBuilder)builder).keepAliveWithoutCalls(this.keepAliveWithoutCalls);
        }
        if (this.interceptorProvider != null) {
            ((ManagedChannelBuilder)builder).intercept(this.interceptorProvider.getInterceptors());
        }
        return ((ManagedChannelBuilder)builder).build();
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public Duration getKeepAliveTime() {
        return this.keepAliveTime;
    }

    public Duration getKeepAliveTimeout() {
        return this.keepAliveTimeout;
    }

    public Boolean getKeepAliveWithoutCalls() {
        return this.keepAliveWithoutCalls;
    }

    @Override
    public boolean shouldAutoClose() {
        return true;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private static void validateEndpoint(String endpoint) {
        int colon2 = endpoint.indexOf(58);
        if (colon2 < 0) {
            throw new IllegalArgumentException(String.format("invalid endpoint, expecting \"<host>:<port>\"", new Object[0]));
        }
        Integer.parseInt(endpoint.substring(colon2 + 1));
    }

    public static final class Builder {
        private int processorCount;
        private ExecutorProvider executorProvider;
        private HeaderProvider headerProvider;
        private String endpoint;
        @Nullable
        private GrpcInterceptorProvider interceptorProvider;
        @Nullable
        private Integer maxInboundMessageSize;
        @Nullable
        private Duration keepAliveTime;
        @Nullable
        private Duration keepAliveTimeout;
        @Nullable
        private Boolean keepAliveWithoutCalls;
        @Nullable
        private Integer poolSize;

        private Builder() {
            this.processorCount = Runtime.getRuntime().availableProcessors();
        }

        private Builder(InstantiatingGrpcChannelProvider provider) {
            this.processorCount = provider.processorCount;
            this.executorProvider = provider.executorProvider;
            this.headerProvider = provider.headerProvider;
            this.endpoint = provider.endpoint;
            this.interceptorProvider = provider.interceptorProvider;
            this.maxInboundMessageSize = provider.maxInboundMessageSize;
            this.keepAliveTime = provider.keepAliveTime;
            this.keepAliveTimeout = provider.keepAliveTimeout;
            this.keepAliveWithoutCalls = provider.keepAliveWithoutCalls;
            this.poolSize = provider.poolSize;
        }

        Builder setProcessorCount(int processorCount) {
            this.processorCount = processorCount;
            return this;
        }

        public Builder setExecutorProvider(ExecutorProvider executorProvider) {
            this.executorProvider = executorProvider;
            return this;
        }

        public Builder setHeaderProvider(HeaderProvider headerProvider) {
            this.headerProvider = headerProvider;
            return this;
        }

        public Builder setEndpoint(String endpoint) {
            InstantiatingGrpcChannelProvider.validateEndpoint(endpoint);
            this.endpoint = endpoint;
            return this;
        }

        public Builder setInterceptorProvider(GrpcInterceptorProvider interceptorProvider) {
            this.interceptorProvider = interceptorProvider;
            return this;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public Builder setMaxInboundMessageSize(Integer max2) {
            this.maxInboundMessageSize = max2;
            return this;
        }

        public Integer getMaxInboundMessageSize() {
            return this.maxInboundMessageSize;
        }

        public Builder setKeepAliveTime(Duration duration) {
            this.keepAliveTime = duration;
            return this;
        }

        public Duration getKeepAliveTime() {
            return this.keepAliveTime;
        }

        public Builder setKeepAliveTimeout(Duration duration) {
            this.keepAliveTimeout = duration;
            return this;
        }

        public Duration getKeepAliveTimeout() {
            return this.keepAliveTimeout;
        }

        public Builder setKeepAliveWithoutCalls(Boolean keepalive) {
            this.keepAliveWithoutCalls = keepalive;
            return this;
        }

        public Boolean getKeepAliveWithoutCalls() {
            return this.keepAliveWithoutCalls;
        }

        public int getPoolSize() {
            if (this.poolSize == null) {
                return 1;
            }
            return this.poolSize;
        }

        public Builder setPoolSize(int poolSize) {
            Preconditions.checkArgument(poolSize > 0, "Pool size must be positive");
            this.poolSize = poolSize;
            return this;
        }

        public Builder setChannelsPerCpu(double multiplier) {
            return this.setChannelsPerCpu(multiplier, 100);
        }

        public Builder setChannelsPerCpu(double multiplier, int maxChannels) {
            Preconditions.checkArgument(multiplier > 0.0, "multiplier must be positive");
            Preconditions.checkArgument(maxChannels > 0, "maxChannels must be positive");
            int channelCount = (int)Math.ceil((double)this.processorCount * multiplier);
            if (channelCount > maxChannels) {
                channelCount = maxChannels;
            }
            return this.setPoolSize(channelCount);
        }

        public InstantiatingGrpcChannelProvider build() {
            return new InstantiatingGrpcChannelProvider(this);
        }
    }
}

