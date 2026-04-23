/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.grpc.ExperimentalApi;
import io.grpc.Internal;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServer;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.ssl.SSLException;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1784")
@CanIgnoreReturnValue
public final class NettyServerBuilder
extends AbstractServerImplBuilder<NettyServerBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 0x100000;
    static final long MAX_CONNECTION_IDLE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_GRACE_NANOS_INFINITE = Long.MAX_VALUE;
    private static final long MIN_KEEPALIVE_TIME_NANO = TimeUnit.MILLISECONDS.toNanos(1L);
    private static final long MIN_KEEPALIVE_TIMEOUT_NANO = TimeUnit.MICROSECONDS.toNanos(499L);
    private static final long MIN_MAX_CONNECTION_IDLE_NANO = TimeUnit.SECONDS.toNanos(1L);
    private static final long MIN_MAX_CONNECTION_AGE_NANO = TimeUnit.SECONDS.toNanos(1L);
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000L);
    private final SocketAddress address;
    private Class<? extends ServerChannel> channelType = NioServerSocketChannel.class;
    private final Map<ChannelOption<?>, Object> channelOptions = new HashMap();
    @Nullable
    private EventLoopGroup bossEventLoopGroup;
    @Nullable
    private EventLoopGroup workerEventLoopGroup;
    private SslContext sslContext;
    private ProtocolNegotiator protocolNegotiator;
    private int maxConcurrentCallsPerConnection = Integer.MAX_VALUE;
    private int flowControlWindow = 0x100000;
    private int maxMessageSize = 0x400000;
    private int maxHeaderListSize = 8192;
    private long keepAliveTimeInNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIME_NANOS;
    private long keepAliveTimeoutInNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS;
    private long maxConnectionIdleInNanos = Long.MAX_VALUE;
    private long maxConnectionAgeInNanos = Long.MAX_VALUE;
    private long maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
    private boolean permitKeepAliveWithoutCalls;
    private long permitKeepAliveTimeInNanos = TimeUnit.MINUTES.toNanos(5L);

    @CheckReturnValue
    public static NettyServerBuilder forPort(int port) {
        return new NettyServerBuilder(port);
    }

    @CheckReturnValue
    public static NettyServerBuilder forAddress(SocketAddress address) {
        return new NettyServerBuilder(address);
    }

    @CheckReturnValue
    private NettyServerBuilder(int port) {
        this.address = new InetSocketAddress(port);
    }

    @CheckReturnValue
    private NettyServerBuilder(SocketAddress address) {
        this.address = address;
    }

    public NettyServerBuilder channelType(Class<? extends ServerChannel> channelType) {
        this.channelType = Preconditions.checkNotNull(channelType, "channelType");
        return this;
    }

    public <T> NettyServerBuilder withChildOption(ChannelOption<T> option, T value) {
        this.channelOptions.put(option, value);
        return this;
    }

    public NettyServerBuilder bossEventLoopGroup(EventLoopGroup group) {
        this.bossEventLoopGroup = group;
        return this;
    }

    public NettyServerBuilder workerEventLoopGroup(EventLoopGroup group) {
        this.workerEventLoopGroup = group;
        return this;
    }

    public NettyServerBuilder sslContext(SslContext sslContext) {
        if (sslContext != null) {
            Preconditions.checkArgument(sslContext.isServer(), "Client SSL context can not be used for server");
            GrpcSslContexts.ensureAlpnAndH2Enabled(sslContext.applicationProtocolNegotiator());
        }
        this.sslContext = sslContext;
        return this;
    }

    @Internal
    public final NettyServerBuilder protocolNegotiator(@Nullable ProtocolNegotiator protocolNegotiator) {
        this.protocolNegotiator = protocolNegotiator;
        return this;
    }

    @Override
    protected void setTracingEnabled(boolean value) {
        super.setTracingEnabled(value);
    }

    @Override
    protected void setStatsEnabled(boolean value) {
        super.setStatsEnabled(value);
    }

    @Override
    protected void setStatsRecordStartedRpcs(boolean value) {
        super.setStatsRecordStartedRpcs(value);
    }

    @VisibleForTesting
    NettyServerBuilder setTransportTracerFactory(TransportTracer.Factory transportTracerFactory) {
        this.transportTracerFactory = transportTracerFactory;
        return this;
    }

    public NettyServerBuilder maxConcurrentCallsPerConnection(int maxCalls) {
        Preconditions.checkArgument(maxCalls > 0, "max must be positive: %s", maxCalls);
        this.maxConcurrentCallsPerConnection = maxCalls;
        return this;
    }

    public NettyServerBuilder flowControlWindow(int flowControlWindow) {
        Preconditions.checkArgument(flowControlWindow > 0, "flowControlWindow must be positive");
        this.flowControlWindow = flowControlWindow;
        return this;
    }

    @Deprecated
    public NettyServerBuilder maxMessageSize(int maxMessageSize) {
        return this.maxInboundMessageSize(maxMessageSize);
    }

    @Override
    public NettyServerBuilder maxInboundMessageSize(int bytes2) {
        Preconditions.checkArgument(bytes2 >= 0, "bytes must be >= 0");
        this.maxMessageSize = bytes2;
        return this;
    }

    public NettyServerBuilder maxHeaderListSize(int maxHeaderListSize) {
        Preconditions.checkArgument(maxHeaderListSize > 0, "maxHeaderListSize must be > 0");
        this.maxHeaderListSize = maxHeaderListSize;
        return this;
    }

    public NettyServerBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime > 0L, "keepalive time must be positive");
        this.keepAliveTimeInNanos = timeUnit.toNanos(keepAliveTime);
        this.keepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeInNanos);
        if (this.keepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeInNanos = Long.MAX_VALUE;
        }
        if (this.keepAliveTimeInNanos < MIN_KEEPALIVE_TIME_NANO) {
            this.keepAliveTimeInNanos = MIN_KEEPALIVE_TIME_NANO;
        }
        return this;
    }

    public NettyServerBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTimeout > 0L, "keepalive timeout must be positive");
        this.keepAliveTimeoutInNanos = timeUnit.toNanos(keepAliveTimeout);
        this.keepAliveTimeoutInNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutInNanos);
        if (this.keepAliveTimeoutInNanos < MIN_KEEPALIVE_TIMEOUT_NANO) {
            this.keepAliveTimeoutInNanos = MIN_KEEPALIVE_TIMEOUT_NANO;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionIdle(long maxConnectionIdle, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionIdle > 0L, "max connection idle must be positive");
        this.maxConnectionIdleInNanos = timeUnit.toNanos(maxConnectionIdle);
        if (this.maxConnectionIdleInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionIdleInNanos = Long.MAX_VALUE;
        }
        if (this.maxConnectionIdleInNanos < MIN_MAX_CONNECTION_IDLE_NANO) {
            this.maxConnectionIdleInNanos = MIN_MAX_CONNECTION_IDLE_NANO;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionAge(long maxConnectionAge, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionAge > 0L, "max connection age must be positive");
        this.maxConnectionAgeInNanos = timeUnit.toNanos(maxConnectionAge);
        if (this.maxConnectionAgeInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeInNanos = Long.MAX_VALUE;
        }
        if (this.maxConnectionAgeInNanos < MIN_MAX_CONNECTION_AGE_NANO) {
            this.maxConnectionAgeInNanos = MIN_MAX_CONNECTION_AGE_NANO;
        }
        return this;
    }

    public NettyServerBuilder maxConnectionAgeGrace(long maxConnectionAgeGrace, TimeUnit timeUnit) {
        Preconditions.checkArgument(maxConnectionAgeGrace >= 0L, "max connection age grace must be non-negative");
        this.maxConnectionAgeGraceInNanos = timeUnit.toNanos(maxConnectionAgeGrace);
        if (this.maxConnectionAgeGraceInNanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
        }
        return this;
    }

    public NettyServerBuilder permitKeepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime >= 0L, "permit keepalive time must be non-negative");
        this.permitKeepAliveTimeInNanos = timeUnit.toNanos(keepAliveTime);
        return this;
    }

    public NettyServerBuilder permitKeepAliveWithoutCalls(boolean permit) {
        this.permitKeepAliveWithoutCalls = permit;
        return this;
    }

    @Override
    @CheckReturnValue
    protected NettyServer buildTransportServer(List<ServerStreamTracer.Factory> streamTracerFactories) {
        ProtocolNegotiator negotiator = this.protocolNegotiator;
        if (negotiator == null) {
            negotiator = this.sslContext != null ? ProtocolNegotiators.serverTls(this.sslContext) : ProtocolNegotiators.serverPlaintext();
        }
        return new NettyServer(this.address, this.channelType, this.channelOptions, this.bossEventLoopGroup, this.workerEventLoopGroup, negotiator, streamTracerFactories, this.transportTracerFactory, this.maxConcurrentCallsPerConnection, this.flowControlWindow, this.maxMessageSize, this.maxHeaderListSize, this.keepAliveTimeInNanos, this.keepAliveTimeoutInNanos, this.maxConnectionIdleInNanos, this.maxConnectionAgeInNanos, this.maxConnectionAgeGraceInNanos, this.permitKeepAliveWithoutCalls, this.permitKeepAliveTimeInNanos, this.channelz);
    }

    @Override
    public NettyServerBuilder useTransportSecurity(File certChain, File privateKey) {
        try {
            this.sslContext = GrpcSslContexts.forServer(certChain, privateKey).build();
        }
        catch (SSLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public NettyServerBuilder useTransportSecurity(InputStream certChain, InputStream privateKey) {
        try {
            this.sslContext = GrpcSslContexts.forServer(certChain, privateKey).build();
        }
        catch (SSLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}

