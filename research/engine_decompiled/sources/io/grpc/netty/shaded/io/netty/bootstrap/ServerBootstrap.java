/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.bootstrap;

import io.grpc.netty.shaded.io.netty.bootstrap.AbstractBootstrap;
import io.grpc.netty.shaded.io.netty.bootstrap.ServerBootstrapConfig;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.EventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.ServerChannel;
import io.grpc.netty.shaded.io.netty.util.AbstractConstant;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ServerBootstrap
extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ServerBootstrap.class);
    private final Map<ChannelOption<?>, Object> childOptions = new LinkedHashMap();
    private final Map<AttributeKey<?>, Object> childAttrs = new LinkedHashMap();
    private final ServerBootstrapConfig config = new ServerBootstrapConfig(this);
    private volatile EventLoopGroup childGroup;
    private volatile ChannelHandler childHandler;

    public ServerBootstrap() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ServerBootstrap(ServerBootstrap bootstrap) {
        super(bootstrap);
        this.childGroup = bootstrap.childGroup;
        this.childHandler = bootstrap.childHandler;
        Map<AbstractConstant, Object> map2 = bootstrap.childOptions;
        synchronized (map2) {
            this.childOptions.putAll(bootstrap.childOptions);
        }
        map2 = bootstrap.childAttrs;
        synchronized (map2) {
            this.childAttrs.putAll(bootstrap.childAttrs);
        }
    }

    @Override
    public ServerBootstrap group(EventLoopGroup group) {
        return this.group(group, group);
    }

    public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup) {
        super.group(parentGroup);
        if (childGroup == null) {
            throw new NullPointerException("childGroup");
        }
        if (this.childGroup != null) {
            throw new IllegalStateException("childGroup set already");
        }
        this.childGroup = childGroup;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public <T> ServerBootstrap childOption(ChannelOption<T> childOption, T value) {
        if (childOption == null) {
            throw new NullPointerException("childOption");
        }
        if (value == null) {
            Map<ChannelOption<?>, Object> map2 = this.childOptions;
            synchronized (map2) {
                this.childOptions.remove(childOption);
            }
        }
        Map<ChannelOption<?>, Object> map3 = this.childOptions;
        synchronized (map3) {
            this.childOptions.put(childOption, value);
        }
        return this;
    }

    public <T> ServerBootstrap childAttr(AttributeKey<T> childKey, T value) {
        if (childKey == null) {
            throw new NullPointerException("childKey");
        }
        if (value == null) {
            this.childAttrs.remove(childKey);
        } else {
            this.childAttrs.put(childKey, value);
        }
        return this;
    }

    public ServerBootstrap childHandler(ChannelHandler childHandler) {
        if (childHandler == null) {
            throw new NullPointerException("childHandler");
        }
        this.childHandler = childHandler;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    void init(Channel channel) throws Exception {
        Map.Entry[] currentChildAttrs;
        Map.Entry[] currentChildOptions;
        Map<AttributeKey<?>, Object> attrs;
        Map<ChannelOption<?>, Object> options;
        Map<ChannelOption<?>, Object> map2 = options = this.options0();
        synchronized (map2) {
            ServerBootstrap.setChannelOptions(channel, options, logger);
        }
        Map<AttributeKey<?>, Object> map3 = attrs = this.attrs0();
        synchronized (map3) {
            for (Map.Entry<AttributeKey<?>, Object> e : attrs.entrySet()) {
                AttributeKey<?> key = e.getKey();
                channel.attr(key).set(e.getValue());
            }
        }
        ChannelPipeline p = channel.pipeline();
        final EventLoopGroup currentChildGroup = this.childGroup;
        final ChannelHandler currentChildHandler = this.childHandler;
        Map<AbstractConstant, Object> map4 = this.childOptions;
        synchronized (map4) {
            currentChildOptions = this.childOptions.entrySet().toArray(ServerBootstrap.newOptionArray(this.childOptions.size()));
        }
        map4 = this.childAttrs;
        synchronized (map4) {
            currentChildAttrs = this.childAttrs.entrySet().toArray(ServerBootstrap.newAttrArray(this.childAttrs.size()));
        }
        p.addLast(new ChannelInitializer<Channel>(){

            @Override
            public void initChannel(final Channel ch) throws Exception {
                final ChannelPipeline pipeline = ch.pipeline();
                ChannelHandler handler = ServerBootstrap.this.config.handler();
                if (handler != null) {
                    pipeline.addLast(handler);
                }
                ch.eventLoop().execute(new Runnable(){

                    @Override
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
                    }
                });
            }
        });
    }

    @Override
    public ServerBootstrap validate() {
        super.validate();
        if (this.childHandler == null) {
            throw new IllegalStateException("childHandler not set");
        }
        if (this.childGroup == null) {
            logger.warn("childGroup is not set. Using parentGroup instead.");
            this.childGroup = this.config.group();
        }
        return this;
    }

    private static Map.Entry<AttributeKey<?>, Object>[] newAttrArray(int size2) {
        return new Map.Entry[size2];
    }

    private static Map.Entry<ChannelOption<?>, Object>[] newOptionArray(int size2) {
        return new Map.Entry[size2];
    }

    @Override
    public ServerBootstrap clone() {
        return new ServerBootstrap(this);
    }

    @Deprecated
    public EventLoopGroup childGroup() {
        return this.childGroup;
    }

    final ChannelHandler childHandler() {
        return this.childHandler;
    }

    final Map<ChannelOption<?>, Object> childOptions() {
        return ServerBootstrap.copiedMap(this.childOptions);
    }

    final Map<AttributeKey<?>, Object> childAttrs() {
        return ServerBootstrap.copiedMap(this.childAttrs);
    }

    public final ServerBootstrapConfig config() {
        return this.config;
    }

    private static class ServerBootstrapAcceptor
    extends ChannelInboundHandlerAdapter {
        private final EventLoopGroup childGroup;
        private final ChannelHandler childHandler;
        private final Map.Entry<ChannelOption<?>, Object>[] childOptions;
        private final Map.Entry<AttributeKey<?>, Object>[] childAttrs;
        private final Runnable enableAutoReadTask;

        ServerBootstrapAcceptor(final Channel channel, EventLoopGroup childGroup, ChannelHandler childHandler, Map.Entry<ChannelOption<?>, Object>[] childOptions, Map.Entry<AttributeKey<?>, Object>[] childAttrs) {
            this.childGroup = childGroup;
            this.childHandler = childHandler;
            this.childOptions = childOptions;
            this.childAttrs = childAttrs;
            this.enableAutoReadTask = new Runnable(){

                @Override
                public void run() {
                    channel.config().setAutoRead(true);
                }
            };
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            final Channel child = (Channel)msg;
            child.pipeline().addLast(this.childHandler);
            AbstractBootstrap.setChannelOptions(child, this.childOptions, logger);
            for (Map.Entry<AttributeKey<?>, Object> e : this.childAttrs) {
                child.attr(e.getKey()).set(e.getValue());
            }
            try {
                this.childGroup.register(child).addListener(new ChannelFutureListener(){

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!future.isSuccess()) {
                            ServerBootstrapAcceptor.forceClose(child, future.cause());
                        }
                    }
                });
            }
            catch (Throwable t) {
                ServerBootstrapAcceptor.forceClose(child, t);
            }
        }

        private static void forceClose(Channel child, Throwable t) {
            child.unsafe().closeForcibly();
            logger.warn("Failed to register an accepted channel: {}", (Object)child, (Object)t);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ChannelConfig config = ctx.channel().config();
            if (config.isAutoRead()) {
                config.setAutoRead(false);
                ctx.channel().eventLoop().schedule(this.enableAutoReadTask, 1L, TimeUnit.SECONDS);
            }
            ctx.fireExceptionCaught(cause);
        }
    }
}

