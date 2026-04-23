/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.ClientTransportLifecycleManager;
import io.grpc.netty.shaded.io.grpc.netty.ForcefulCloseCommand;
import io.grpc.netty.shaded.io.grpc.netty.GracefulCloseCommand;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientHandler;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.grpc.netty.NettyHandlerSettings;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerTransport;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.SendPingCommand;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.netty.bootstrap.Bootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamBufferingEncoder;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.annotation.Nullable;

class NettyClientTransport
implements ConnectionClientTransport {
    private static final Logger log = Logger.getLogger(NettyServerTransport.class.getName());
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final Map<ChannelOption<?>, ?> channelOptions;
    private final SocketAddress address;
    private final Class<? extends Channel> channelType;
    private final EventLoopGroup group;
    private final ProtocolNegotiator negotiator;
    private final AsciiString authority;
    private final AsciiString userAgent;
    private final int flowControlWindow;
    private final int maxMessageSize;
    private final int maxHeaderListSize;
    private KeepAliveManager keepAliveManager;
    private final long keepAliveTimeNanos;
    private final long keepAliveTimeoutNanos;
    private final boolean keepAliveWithoutCalls;
    private final Runnable tooManyPingsRunnable;
    private ProtocolNegotiator.Handler negotiationHandler;
    private NettyClientHandler handler;
    private Channel channel;
    private Status statusExplainingWhyTheChannelIsNull;
    private ClientTransportLifecycleManager lifecycleManager;
    private final TransportTracer transportTracer;

    NettyClientTransport(SocketAddress address, Class<? extends Channel> channelType, Map<ChannelOption<?>, ?> channelOptions, EventLoopGroup group, ProtocolNegotiator negotiator, int flowControlWindow, int maxMessageSize, int maxHeaderListSize, long keepAliveTimeNanos, long keepAliveTimeoutNanos, boolean keepAliveWithoutCalls, String authority, @Nullable String userAgent, Runnable tooManyPingsRunnable, TransportTracer transportTracer) {
        this.negotiator = Preconditions.checkNotNull(negotiator, "negotiator");
        this.address = Preconditions.checkNotNull(address, "address");
        this.group = Preconditions.checkNotNull(group, "group");
        this.channelType = Preconditions.checkNotNull(channelType, "channelType");
        this.channelOptions = Preconditions.checkNotNull(channelOptions, "channelOptions");
        this.flowControlWindow = flowControlWindow;
        this.maxMessageSize = maxMessageSize;
        this.maxHeaderListSize = maxHeaderListSize;
        this.keepAliveTimeNanos = keepAliveTimeNanos;
        this.keepAliveTimeoutNanos = keepAliveTimeoutNanos;
        this.keepAliveWithoutCalls = keepAliveWithoutCalls;
        this.authority = new AsciiString(authority);
        this.userAgent = new AsciiString(GrpcUtil.getGrpcUserAgent("netty", userAgent));
        this.tooManyPingsRunnable = Preconditions.checkNotNull(tooManyPingsRunnable, "tooManyPingsRunnable");
        this.transportTracer = Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    @Override
    public void ping(final ClientTransport.PingCallback callback, final Executor executor) {
        if (this.channel == null) {
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    callback.onFailure(NettyClientTransport.this.statusExplainingWhyTheChannelIsNull.asException());
                }
            });
            return;
        }
        ChannelFutureListener failureListener = new ChannelFutureListener(){

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    Status s2 = NettyClientTransport.this.statusFromFailedFuture(future);
                    Http2Ping.notifyFailed(callback, executor, s2.asException());
                }
            }
        };
        this.handler.getWriteQueue().enqueue(new SendPingCommand(callback, executor), true).addListener(failureListener);
    }

    @Override
    public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
        Preconditions.checkNotNull(method, "method");
        Preconditions.checkNotNull(headers, "headers");
        if (this.channel == null) {
            return new FailingClientStream(this.statusExplainingWhyTheChannelIsNull);
        }
        StatsTraceContext statsTraceCtx = StatsTraceContext.newClientContext(callOptions, headers);
        return new NettyClientStream(new NettyClientStream.TransportState(this.handler, this.channel.eventLoop(), this.maxMessageSize, statsTraceCtx, this.transportTracer){

            @Override
            protected Status statusFromFailedFuture(ChannelFuture f) {
                return NettyClientTransport.this.statusFromFailedFuture(f);
            }
        }, method, headers, this.channel, this.authority, this.negotiationHandler.scheme(), this.userAgent, statsTraceCtx, this.transportTracer);
    }

    @Override
    public Runnable start(ManagedClientTransport.Listener transportListener) {
        this.lifecycleManager = new ClientTransportLifecycleManager(Preconditions.checkNotNull(transportListener, "listener"));
        EventLoop eventLoop = this.group.next();
        if (this.keepAliveTimeNanos != Long.MAX_VALUE) {
            this.keepAliveManager = new KeepAliveManager(new KeepAliveManager.ClientKeepAlivePinger(this), eventLoop, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
        }
        this.handler = NettyClientHandler.newHandler(this.lifecycleManager, this.keepAliveManager, this.flowControlWindow, this.maxHeaderListSize, GrpcUtil.STOPWATCH_SUPPLIER, this.tooManyPingsRunnable, this.transportTracer);
        NettyHandlerSettings.setAutoWindow(this.handler);
        this.negotiationHandler = this.negotiator.newHandler(this.handler);
        Bootstrap b = new Bootstrap();
        b.group(eventLoop);
        b.channel(this.channelType);
        if (NioSocketChannel.class.isAssignableFrom(this.channelType)) {
            b.option(ChannelOption.SO_KEEPALIVE, true);
        }
        for (Map.Entry<ChannelOption<?>, ?> entry : this.channelOptions.entrySet()) {
            b.option(entry.getKey(), entry.getValue());
        }
        b.handler(this.negotiationHandler);
        ChannelFuture regFuture = b.register();
        if (regFuture.isDone() && !regFuture.isSuccess()) {
            this.channel = null;
            Throwable t = regFuture.cause();
            if (t == null) {
                t = new IllegalStateException("Channel is null, but future doesn't have a cause");
            }
            this.statusExplainingWhyTheChannelIsNull = Utils.statusFromThrowable(t);
            return new Runnable(){

                @Override
                public void run() {
                    NettyClientTransport.this.lifecycleManager.notifyTerminated(NettyClientTransport.this.statusExplainingWhyTheChannelIsNull);
                }
            };
        }
        this.channel = regFuture.channel();
        this.handler.startWriteQueue(this.channel);
        this.channel.writeAndFlush(NettyClientHandler.NOOP_MESSAGE).addListener(new ChannelFutureListener(){

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    NettyClientTransport.this.lifecycleManager.notifyTerminated(Utils.statusFromThrowable(future.cause()));
                }
            }
        });
        this.channel.connect(this.address);
        if (this.keepAliveManager != null) {
            this.keepAliveManager.onTransportStarted();
        }
        return null;
    }

    @Override
    public void shutdown(Status reason) {
        if (this.channel == null) {
            return;
        }
        if (this.channel.isOpen()) {
            this.handler.getWriteQueue().enqueue(new GracefulCloseCommand(reason), true);
        }
    }

    @Override
    public void shutdownNow(final Status reason) {
        if (this.channel != null && this.channel.isOpen()) {
            this.handler.getWriteQueue().enqueue(new Runnable(){

                @Override
                public void run() {
                    NettyClientTransport.this.lifecycleManager.notifyShutdown(reason);
                    NettyClientTransport.this.channel.close();
                    NettyClientTransport.this.channel.write(new ForcefulCloseCommand(reason));
                }
            }, true);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).add("channel", this.channel).toString();
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    @Override
    public Attributes getAttributes() {
        return this.handler.getAttributes();
    }

    @Override
    public ListenableFuture<Channelz.SocketStats> getStats() {
        final SettableFuture<Channelz.SocketStats> result2 = SettableFuture.create();
        if (this.channel.eventLoop().inEventLoop()) {
            result2.set(this.getStatsHelper(this.channel));
            return result2;
        }
        this.channel.eventLoop().submit(new Runnable(){

            @Override
            public void run() {
                result2.set(NettyClientTransport.this.getStatsHelper(NettyClientTransport.this.channel));
            }
        }).addListener(new GenericFutureListener<Future<Object>>(){

            @Override
            public void operationComplete(Future<Object> future) throws Exception {
                if (!future.isSuccess()) {
                    result2.setException(future.cause());
                }
            }
        });
        return result2;
    }

    private Channelz.SocketStats getStatsHelper(Channel ch) {
        assert (ch.eventLoop().inEventLoop());
        return new Channelz.SocketStats(this.transportTracer.getStats(), this.channel.localAddress(), this.channel.remoteAddress(), Utils.getSocketOptions(ch), this.handler == null ? null : this.handler.getSecurityInfo());
    }

    @VisibleForTesting
    Channel channel() {
        return this.channel;
    }

    @VisibleForTesting
    KeepAliveManager keepAliveManager() {
        return this.keepAliveManager;
    }

    private Status statusFromFailedFuture(ChannelFuture f) {
        Throwable t = f.cause();
        if (t instanceof ClosedChannelException || t instanceof StreamBufferingEncoder.Http2ChannelClosedException) {
            Status shutdownStatus = this.lifecycleManager.getShutdownStatus();
            if (shutdownStatus == null) {
                return Status.UNKNOWN.withDescription("Channel closed but for unknown reason").withCause(new ClosedChannelException().initCause(t));
            }
            return shutdownStatus;
        }
        return Utils.statusFromThrowable(t);
    }
}

