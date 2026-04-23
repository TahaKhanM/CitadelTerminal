/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.Channelz;
import io.grpc.internal.Instrumented;
import io.grpc.internal.InternalServer;
import io.grpc.internal.LogId;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WithLogId;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerTransport;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrap;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

class NettyServer
implements InternalServer,
WithLogId {
    private static final Logger log = Logger.getLogger(InternalServer.class.getName());
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final SocketAddress address;
    private final Class<? extends ServerChannel> channelType;
    private final Map<ChannelOption<?>, ?> channelOptions;
    private final ProtocolNegotiator protocolNegotiator;
    private final int maxStreamsPerConnection;
    private final boolean usingSharedBossGroup;
    private final boolean usingSharedWorkerGroup;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerListener listener;
    private Channel channel;
    private final int flowControlWindow;
    private final int maxMessageSize;
    private final int maxHeaderListSize;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private final long maxConnectionIdleInNanos;
    private final long maxConnectionAgeInNanos;
    private final long maxConnectionAgeGraceInNanos;
    private final boolean permitKeepAliveWithoutCalls;
    private final long permitKeepAliveTimeInNanos;
    private final ReferenceCounted eventLoopReferenceCounter = new EventLoopReferenceCounter();
    private final List<ServerStreamTracer.Factory> streamTracerFactories;
    private final TransportTracer.Factory transportTracerFactory;
    private final Channelz channelz;
    private volatile ImmutableList<Instrumented<Channelz.SocketStats>> listenSockets = ImmutableList.of();

    NettyServer(SocketAddress address, Class<? extends ServerChannel> channelType, Map<ChannelOption<?>, ?> channelOptions, @Nullable EventLoopGroup bossGroup, @Nullable EventLoopGroup workerGroup, ProtocolNegotiator protocolNegotiator, List<ServerStreamTracer.Factory> streamTracerFactories, TransportTracer.Factory transportTracerFactory, int maxStreamsPerConnection, int flowControlWindow, int maxMessageSize, int maxHeaderListSize, long keepAliveTimeInNanos, long keepAliveTimeoutInNanos, long maxConnectionIdleInNanos, long maxConnectionAgeInNanos, long maxConnectionAgeGraceInNanos, boolean permitKeepAliveWithoutCalls, long permitKeepAliveTimeInNanos, Channelz channelz) {
        this.address = address;
        this.channelType = Preconditions.checkNotNull(channelType, "channelType");
        Preconditions.checkNotNull(channelOptions, "channelOptions");
        this.channelOptions = new HashMap(channelOptions);
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.protocolNegotiator = Preconditions.checkNotNull(protocolNegotiator, "protocolNegotiator");
        this.streamTracerFactories = Preconditions.checkNotNull(streamTracerFactories, "streamTracerFactories");
        this.usingSharedBossGroup = bossGroup == null;
        this.usingSharedWorkerGroup = workerGroup == null;
        this.transportTracerFactory = transportTracerFactory;
        this.maxStreamsPerConnection = maxStreamsPerConnection;
        this.flowControlWindow = flowControlWindow;
        this.maxMessageSize = maxMessageSize;
        this.maxHeaderListSize = maxHeaderListSize;
        this.keepAliveTimeInNanos = keepAliveTimeInNanos;
        this.keepAliveTimeoutInNanos = keepAliveTimeoutInNanos;
        this.maxConnectionIdleInNanos = maxConnectionIdleInNanos;
        this.maxConnectionAgeInNanos = maxConnectionAgeInNanos;
        this.maxConnectionAgeGraceInNanos = maxConnectionAgeGraceInNanos;
        this.permitKeepAliveWithoutCalls = permitKeepAliveWithoutCalls;
        this.permitKeepAliveTimeInNanos = permitKeepAliveTimeInNanos;
        this.channelz = Preconditions.checkNotNull(channelz);
    }

    @Override
    public int getPort() {
        if (this.channel == null) {
            return -1;
        }
        SocketAddress localAddr = this.channel.localAddress();
        if (!(localAddr instanceof InetSocketAddress)) {
            return -1;
        }
        return ((InetSocketAddress)localAddr).getPort();
    }

    @Override
    public List<Instrumented<Channelz.SocketStats>> getListenSockets() {
        return this.listenSockets;
    }

    @Override
    public void start(ServerListener serverListener) throws IOException {
        this.listener = Preconditions.checkNotNull(serverListener, "serverListener");
        this.allocateSharedGroups();
        ServerBootstrap b = new ServerBootstrap();
        b.group(this.bossGroup, this.workerGroup);
        b.channel(this.channelType);
        if (NioServerSocketChannel.class.isAssignableFrom(this.channelType)) {
            b.option(ChannelOption.SO_BACKLOG, 128);
            b.childOption(ChannelOption.SO_KEEPALIVE, true);
        }
        if (this.channelOptions != null) {
            for (Map.Entry<ChannelOption<?>, ?> entry : this.channelOptions.entrySet()) {
                ChannelOption<?> key = entry.getKey();
                b.childOption(key, entry.getValue());
            }
        }
        b.childHandler(new ChannelInitializer<Channel>(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void initChannel(Channel ch) throws Exception {
                ServerTransportListener transportListener;
                ChannelPromise channelDone = ch.newPromise();
                long maxConnectionAgeInNanos = NettyServer.this.maxConnectionAgeInNanos;
                if (maxConnectionAgeInNanos != Long.MAX_VALUE) {
                    maxConnectionAgeInNanos = (long)((0.9 + Math.random() * 0.2) * (double)maxConnectionAgeInNanos);
                }
                NettyServerTransport transport = new NettyServerTransport(ch, channelDone, NettyServer.this.protocolNegotiator, NettyServer.this.streamTracerFactories, NettyServer.this.transportTracerFactory.create(), NettyServer.this.maxStreamsPerConnection, NettyServer.this.flowControlWindow, NettyServer.this.maxMessageSize, NettyServer.this.maxHeaderListSize, NettyServer.this.keepAliveTimeInNanos, NettyServer.this.keepAliveTimeoutInNanos, NettyServer.this.maxConnectionIdleInNanos, maxConnectionAgeInNanos, NettyServer.this.maxConnectionAgeGraceInNanos, NettyServer.this.permitKeepAliveWithoutCalls, NettyServer.this.permitKeepAliveTimeInNanos);
                NettyServer nettyServer = NettyServer.this;
                synchronized (nettyServer) {
                    if (NettyServer.this.channel != null && !NettyServer.this.channel.isOpen()) {
                        ch.close();
                        return;
                    }
                    NettyServer.this.eventLoopReferenceCounter.retain();
                    transportListener = NettyServer.this.listener.transportCreated(transport);
                }
                transport.start(transportListener);
                final class LoopReleaser
                implements ChannelFutureListener {
                    boolean done;

                    LoopReleaser() {
                    }

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!this.done) {
                            this.done = true;
                            NettyServer.this.eventLoopReferenceCounter.release();
                        }
                    }
                }
                LoopReleaser loopReleaser = new LoopReleaser();
                channelDone.addListener(loopReleaser);
                ch.closeFuture().addListener(loopReleaser);
            }
        });
        ChannelFuture future = b.bind(this.address);
        try {
            future.await();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted waiting for bind");
        }
        if (!future.isSuccess()) {
            throw new IOException("Failed to bind", future.cause());
        }
        this.channel = future.channel();
        Future<?> channelzFuture = this.channel.eventLoop().submit(new Runnable(){

            @Override
            public void run() {
                ListenSocket listenSocket = new ListenSocket(NettyServer.this.channel);
                NettyServer.this.listenSockets = ImmutableList.of(listenSocket);
                NettyServer.this.channelz.addListenSocket(listenSocket);
            }
        });
        try {
            channelzFuture.await();
        }
        catch (InterruptedException ex) {
            throw new RuntimeException("Interrupted while registering listen socket to channelz", ex);
        }
    }

    @Override
    public void shutdown() {
        if (this.channel == null || !this.channel.isOpen()) {
            return;
        }
        this.channel.close().addListener(new ChannelFutureListener(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    log.log(Level.WARNING, "Error shutting down server", future.cause());
                }
                for (Instrumented listenSocket : NettyServer.this.listenSockets) {
                    NettyServer.this.channelz.removeListenSocket(listenSocket);
                }
                NettyServer.this.listenSockets = null;
                NettyServer nettyServer = NettyServer.this;
                synchronized (nettyServer) {
                    NettyServer.this.listener.serverShutdown();
                }
                NettyServer.this.eventLoopReferenceCounter.release();
            }
        });
    }

    private void allocateSharedGroups() {
        if (this.bossGroup == null) {
            this.bossGroup = SharedResourceHolder.get(Utils.DEFAULT_BOSS_EVENT_LOOP_GROUP);
        }
        if (this.workerGroup == null) {
            this.workerGroup = SharedResourceHolder.get(Utils.DEFAULT_WORKER_EVENT_LOOP_GROUP);
        }
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    private static final class ListenSocket
    implements Instrumented<Channelz.SocketStats> {
        private final LogId id = LogId.allocate(this.getClass().getName());
        private final Channel ch;

        ListenSocket(Channel ch) {
            this.ch = ch;
        }

        @Override
        public ListenableFuture<Channelz.SocketStats> getStats() {
            final SettableFuture<Channelz.SocketStats> ret = SettableFuture.create();
            if (this.ch.eventLoop().inEventLoop()) {
                ret.set(new Channelz.SocketStats(null, this.ch.localAddress(), null, Utils.getSocketOptions(this.ch), null));
                return ret;
            }
            this.ch.eventLoop().submit(new Runnable(){

                @Override
                public void run() {
                    ret.set(new Channelz.SocketStats(null, ListenSocket.this.ch.localAddress(), null, Utils.getSocketOptions(ListenSocket.this.ch), null));
                }
            }).addListener(new GenericFutureListener<Future<Object>>(){

                @Override
                public void operationComplete(Future<Object> future) throws Exception {
                    if (!future.isSuccess()) {
                        ret.setException(future.cause());
                    }
                }
            });
            return ret;
        }

        @Override
        public LogId getLogId() {
            return this.id;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("logId", this.id.getId()).add("channel", this.ch).toString();
        }
    }

    class EventLoopReferenceCounter
    extends AbstractReferenceCounted {
        EventLoopReferenceCounter() {
        }

        @Override
        protected void deallocate() {
            try {
                if (NettyServer.this.usingSharedBossGroup && NettyServer.this.bossGroup != null) {
                    SharedResourceHolder.release(Utils.DEFAULT_BOSS_EVENT_LOOP_GROUP, NettyServer.this.bossGroup);
                }
            }
            finally {
                NettyServer.this.bossGroup = null;
                try {
                    if (NettyServer.this.usingSharedWorkerGroup && NettyServer.this.workerGroup != null) {
                        SharedResourceHolder.release(Utils.DEFAULT_WORKER_EVENT_LOOP_GROUP, NettyServer.this.workerGroup);
                    }
                }
                finally {
                    NettyServer.this.workerGroup = null;
                }
            }
        }

        @Override
        public ReferenceCounted touch(Object hint) {
            return this;
        }
    }
}

