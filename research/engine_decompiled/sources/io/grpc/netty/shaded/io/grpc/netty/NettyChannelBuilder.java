/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.grpc.Attributes;
import io.grpc.ExperimentalApi;
import io.grpc.Internal;
import io.grpc.NameResolver;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientTransport;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.ssl.SSLException;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1784")
@CanIgnoreReturnValue
public final class NettyChannelBuilder
extends AbstractManagedChannelImplBuilder<NettyChannelBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 0x100000;
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000L);
    private final Map<ChannelOption<?>, Object> channelOptions = new HashMap();
    private NegotiationType negotiationType = NegotiationType.TLS;
    private OverrideAuthorityChecker authorityChecker;
    private Class<? extends Channel> channelType = NioSocketChannel.class;
    @Nullable
    private EventLoopGroup eventLoopGroup;
    private SslContext sslContext;
    private int flowControlWindow = 0x100000;
    private int maxHeaderListSize = 8192;
    private long keepAliveTimeNanos = Long.MAX_VALUE;
    private long keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
    private boolean keepAliveWithoutCalls;
    private TransportCreationParamsFilterFactory dynamicParamsFactory;

    @CheckReturnValue
    public static NettyChannelBuilder forAddress(SocketAddress serverAddress) {
        return new NettyChannelBuilder(serverAddress);
    }

    @CheckReturnValue
    public static NettyChannelBuilder forAddress(String host, int port) {
        return new NettyChannelBuilder(host, port);
    }

    @CheckReturnValue
    public static NettyChannelBuilder forTarget(String target) {
        return new NettyChannelBuilder(target);
    }

    @CheckReturnValue
    NettyChannelBuilder(String host, int port) {
        this(GrpcUtil.authorityFromHostAndPort(host, port));
    }

    @CheckReturnValue
    NettyChannelBuilder(String target) {
        super(target);
    }

    @CheckReturnValue
    NettyChannelBuilder(SocketAddress address) {
        super(address, NettyChannelBuilder.getAuthorityFromAddress(address));
    }

    @CheckReturnValue
    private static String getAuthorityFromAddress(SocketAddress address) {
        if (address instanceof InetSocketAddress) {
            InetSocketAddress inetAddress = (InetSocketAddress)address;
            return GrpcUtil.authorityFromHostAndPort(inetAddress.getHostString(), inetAddress.getPort());
        }
        return address.toString();
    }

    public NettyChannelBuilder channelType(Class<? extends Channel> channelType) {
        this.channelType = Preconditions.checkNotNull(channelType, "channelType");
        return this;
    }

    public <T> NettyChannelBuilder withOption(ChannelOption<T> option, T value) {
        this.channelOptions.put(option, value);
        return this;
    }

    public NettyChannelBuilder negotiationType(NegotiationType type) {
        this.negotiationType = type;
        return this;
    }

    public NettyChannelBuilder eventLoopGroup(@Nullable EventLoopGroup eventLoopGroup) {
        this.eventLoopGroup = eventLoopGroup;
        return this;
    }

    public NettyChannelBuilder sslContext(SslContext sslContext) {
        if (sslContext != null) {
            Preconditions.checkArgument(sslContext.isClient(), "Server SSL context can not be used for client channel");
            GrpcSslContexts.ensureAlpnAndH2Enabled(sslContext.applicationProtocolNegotiator());
        }
        this.sslContext = sslContext;
        return this;
    }

    public NettyChannelBuilder flowControlWindow(int flowControlWindow) {
        Preconditions.checkArgument(flowControlWindow > 0, "flowControlWindow must be positive");
        this.flowControlWindow = flowControlWindow;
        return this;
    }

    @Deprecated
    public NettyChannelBuilder maxMessageSize(int maxMessageSize) {
        this.maxInboundMessageSize(maxMessageSize);
        return this;
    }

    public NettyChannelBuilder maxHeaderListSize(int maxHeaderListSize) {
        Preconditions.checkArgument(maxHeaderListSize > 0, "maxHeaderListSize must be > 0");
        this.maxHeaderListSize = maxHeaderListSize;
        return this;
    }

    @Override
    @Deprecated
    public NettyChannelBuilder usePlaintext(boolean skipNegotiation) {
        if (skipNegotiation) {
            this.negotiationType(NegotiationType.PLAINTEXT);
        } else {
            this.negotiationType(NegotiationType.PLAINTEXT_UPGRADE);
        }
        return this;
    }

    @Override
    public NettyChannelBuilder usePlaintext() {
        this.negotiationType(NegotiationType.PLAINTEXT);
        return this;
    }

    @Override
    public NettyChannelBuilder useTransportSecurity() {
        this.negotiationType(NegotiationType.TLS);
        return this;
    }

    @Deprecated
    public final NettyChannelBuilder enableKeepAlive(boolean enable) {
        if (enable) {
            return this.keepAliveTime(GrpcUtil.DEFAULT_KEEPALIVE_TIME_NANOS, TimeUnit.NANOSECONDS);
        }
        return this.keepAliveTime(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    @Deprecated
    public final NettyChannelBuilder enableKeepAlive(boolean enable, long keepAliveTime, TimeUnit delayUnit, long keepAliveTimeout, TimeUnit timeoutUnit) {
        if (enable) {
            return this.keepAliveTime(keepAliveTime, delayUnit).keepAliveTimeout(keepAliveTimeout, timeoutUnit);
        }
        return this.keepAliveTime(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    @Override
    public NettyChannelBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTime > 0L, "keepalive time must be positive");
        this.keepAliveTimeNanos = timeUnit.toNanos(keepAliveTime);
        this.keepAliveTimeNanos = KeepAliveManager.clampKeepAliveTimeInNanos(this.keepAliveTimeNanos);
        if (this.keepAliveTimeNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override
    public NettyChannelBuilder keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit) {
        Preconditions.checkArgument(keepAliveTimeout > 0L, "keepalive timeout must be positive");
        this.keepAliveTimeoutNanos = timeUnit.toNanos(keepAliveTimeout);
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(this.keepAliveTimeoutNanos);
        return this;
    }

    @Override
    public NettyChannelBuilder keepAliveWithoutCalls(boolean enable) {
        this.keepAliveWithoutCalls = enable;
        return this;
    }

    @Override
    @CheckReturnValue
    @Internal
    protected ClientTransportFactory buildTransportFactory() {
        return new NettyTransportFactory(this.dynamicParamsFactory, this.channelType, this.channelOptions, this.negotiationType, this.sslContext, this.eventLoopGroup, this.flowControlWindow, this.maxInboundMessageSize(), this.maxHeaderListSize, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls, this.transportTracerFactory.create());
    }

    @Override
    @CheckReturnValue
    protected Attributes getNameResolverParams() {
        int defaultPort;
        switch (this.negotiationType) {
            case PLAINTEXT: 
            case PLAINTEXT_UPGRADE: {
                defaultPort = 80;
                break;
            }
            case TLS: {
                defaultPort = 443;
                break;
            }
            default: {
                throw new AssertionError((Object)((Object)((Object)this.negotiationType) + " not handled"));
            }
        }
        return Attributes.newBuilder().set(NameResolver.Factory.PARAMS_DEFAULT_PORT, defaultPort).build();
    }

    void overrideAuthorityChecker(@Nullable OverrideAuthorityChecker authorityChecker) {
        this.authorityChecker = authorityChecker;
    }

    @CheckReturnValue
    @VisibleForTesting
    static ProtocolNegotiator createProtocolNegotiator(String authority, NegotiationType negotiationType, SslContext sslContext, ProxyParameters proxy) {
        ProtocolNegotiator negotiator = NettyChannelBuilder.createProtocolNegotiatorByType(authority, negotiationType, sslContext);
        if (proxy != null) {
            negotiator = ProtocolNegotiators.httpProxy(proxy.proxyAddress, proxy.username, proxy.password, negotiator);
        }
        return negotiator;
    }

    @CheckReturnValue
    private static ProtocolNegotiator createProtocolNegotiatorByType(String authority, NegotiationType negotiationType, SslContext sslContext) {
        switch (negotiationType) {
            case PLAINTEXT: {
                return ProtocolNegotiators.plaintext();
            }
            case PLAINTEXT_UPGRADE: {
                return ProtocolNegotiators.plaintextUpgrade();
            }
            case TLS: {
                return ProtocolNegotiators.tls(sslContext, authority);
            }
        }
        throw new IllegalArgumentException("Unsupported negotiationType: " + (Object)((Object)negotiationType));
    }

    @Override
    @CheckReturnValue
    @Internal
    protected String checkAuthority(String authority) {
        if (this.authorityChecker != null) {
            return this.authorityChecker.checkAuthority(authority);
        }
        return super.checkAuthority(authority);
    }

    void setDynamicParamsFactory(TransportCreationParamsFilterFactory factory) {
        this.dynamicParamsFactory = Preconditions.checkNotNull(factory, "factory");
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
    NettyChannelBuilder setTransportTracerFactory(TransportTracer.Factory transportTracerFactory) {
        this.transportTracerFactory = transportTracerFactory;
        return this;
    }

    @CheckReturnValue
    private static final class NettyTransportFactory
    implements ClientTransportFactory {
        private final TransportCreationParamsFilterFactory transportCreationParamsFilterFactory;
        private final Class<? extends Channel> channelType;
        private final Map<ChannelOption<?>, ?> channelOptions;
        private final NegotiationType negotiationType;
        private final EventLoopGroup group;
        private final boolean usingSharedGroup;
        private final int flowControlWindow;
        private final int maxMessageSize;
        private final int maxHeaderListSize;
        private final AtomicBackoff keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        private final TransportTracer transportTracer;
        private boolean closed;

        NettyTransportFactory(TransportCreationParamsFilterFactory transportCreationParamsFilterFactory, Class<? extends Channel> channelType, Map<ChannelOption<?>, ?> channelOptions, NegotiationType negotiationType, SslContext sslContext, EventLoopGroup group, int flowControlWindow, int maxMessageSize, int maxHeaderListSize, long keepAliveTimeNanos, long keepAliveTimeoutNanos, boolean keepAliveWithoutCalls, TransportTracer transportTracer) {
            this.channelType = channelType;
            this.negotiationType = negotiationType;
            this.channelOptions = new HashMap(channelOptions);
            this.transportTracer = transportTracer;
            if (transportCreationParamsFilterFactory == null) {
                transportCreationParamsFilterFactory = new DefaultNettyTransportCreationParamsFilterFactory(sslContext);
            }
            this.transportCreationParamsFilterFactory = transportCreationParamsFilterFactory;
            this.flowControlWindow = flowControlWindow;
            this.maxMessageSize = maxMessageSize;
            this.maxHeaderListSize = maxHeaderListSize;
            this.keepAliveTimeNanos = new AtomicBackoff("keepalive time nanos", keepAliveTimeNanos);
            this.keepAliveTimeoutNanos = keepAliveTimeoutNanos;
            this.keepAliveWithoutCalls = keepAliveWithoutCalls;
            this.usingSharedGroup = group == null;
            this.group = this.usingSharedGroup ? SharedResourceHolder.get(Utils.DEFAULT_WORKER_EVENT_LOOP_GROUP) : group;
        }

        @Override
        public ConnectionClientTransport newClientTransport(SocketAddress serverAddress, String authority, @Nullable String userAgent, @Nullable ProxyParameters proxy) {
            Preconditions.checkState(!this.closed, "The transport factory is closed.");
            TransportCreationParamsFilter dparams = this.transportCreationParamsFilterFactory.create(serverAddress, authority, userAgent, proxy);
            final AtomicBackoff.State keepAliveTimeNanosState = this.keepAliveTimeNanos.getState();
            Runnable tooManyPingsRunnable = new Runnable(){

                @Override
                public void run() {
                    keepAliveTimeNanosState.backoff();
                }
            };
            NettyClientTransport transport = new NettyClientTransport(dparams.getTargetServerAddress(), this.channelType, this.channelOptions, this.group, dparams.getProtocolNegotiator(), this.flowControlWindow, this.maxMessageSize, this.maxHeaderListSize, keepAliveTimeNanosState.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls, dparams.getAuthority(), dparams.getUserAgent(), tooManyPingsRunnable, this.transportTracer);
            return transport;
        }

        @Override
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.group;
        }

        @Override
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.usingSharedGroup) {
                SharedResourceHolder.release(Utils.DEFAULT_WORKER_EVENT_LOOP_GROUP, this.group);
            }
        }

        private final class DefaultNettyTransportCreationParamsFilterFactory
        implements TransportCreationParamsFilterFactory {
            private final SslContext sslContext;

            private DefaultNettyTransportCreationParamsFilterFactory(SslContext sslContext) {
                if (NettyTransportFactory.this.negotiationType == NegotiationType.TLS && sslContext == null) {
                    try {
                        sslContext = GrpcSslContexts.forClient().build();
                    }
                    catch (SSLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                this.sslContext = sslContext;
            }

            @Override
            public TransportCreationParamsFilter create(SocketAddress targetServerAddress, String authority, String userAgent, ProxyParameters proxyParams) {
                return new DynamicNettyTransportParams(targetServerAddress, authority, userAgent, proxyParams);
            }

            @CheckReturnValue
            private final class DynamicNettyTransportParams
            implements TransportCreationParamsFilter {
                private final SocketAddress targetServerAddress;
                private final String authority;
                @Nullable
                private final String userAgent;
                private ProxyParameters proxyParams;

                private DynamicNettyTransportParams(SocketAddress targetServerAddress, String authority, String userAgent, ProxyParameters proxyParams) {
                    this.targetServerAddress = targetServerAddress;
                    this.authority = authority;
                    this.userAgent = userAgent;
                    this.proxyParams = proxyParams;
                }

                @Override
                public SocketAddress getTargetServerAddress() {
                    return this.targetServerAddress;
                }

                @Override
                public String getAuthority() {
                    return this.authority;
                }

                @Override
                public String getUserAgent() {
                    return this.userAgent;
                }

                @Override
                public ProtocolNegotiator getProtocolNegotiator() {
                    return NettyChannelBuilder.createProtocolNegotiator(this.authority, NettyTransportFactory.this.negotiationType, DefaultNettyTransportCreationParamsFilterFactory.this.sslContext, this.proxyParams);
                }
            }
        }
    }

    @CheckReturnValue
    static interface TransportCreationParamsFilter {
        public SocketAddress getTargetServerAddress();

        public String getAuthority();

        @Nullable
        public String getUserAgent();

        public ProtocolNegotiator getProtocolNegotiator();
    }

    static interface TransportCreationParamsFilterFactory {
        @CheckReturnValue
        public TransportCreationParamsFilter create(SocketAddress var1, String var2, @Nullable String var3, @Nullable ProxyParameters var4);
    }

    @CheckReturnValue
    static interface OverrideAuthorityChecker {
        public String checkAuthority(String var1);
    }
}

