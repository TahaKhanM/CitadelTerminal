/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.Grpc;
import io.grpc.Internal;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.GrpcUtil;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.JettyTlsUtil;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.netty.channel.ChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ClientUpgradeCodec;
import io.grpc.netty.shaded.io.netty.handler.proxy.HttpProxyHandler;
import io.grpc.netty.shaded.io.netty.handler.proxy.ProxyConnectionEvent;
import io.grpc.netty.shaded.io.netty.handler.proxy.ProxyHandler;
import io.grpc.netty.shaded.io.netty.handler.ssl.OpenSsl;
import io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslEngine;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

@Internal
public final class ProtocolNegotiators {
    private static final Logger log = Logger.getLogger(ProtocolNegotiators.class.getName());

    private ProtocolNegotiators() {
    }

    public static ProtocolNegotiator serverPlaintext() {
        return new ProtocolNegotiator(){

            @Override
            public ProtocolNegotiator.Handler newHandler(final GrpcHttp2ConnectionHandler handler) {
                class PlaintextHandler
                extends ChannelHandlerAdapter
                implements ProtocolNegotiator.Handler {
                    PlaintextHandler() {
                    }

                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                        handler.handleProtocolNegotiationCompleted(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ctx.channel().remoteAddress()).build(), null);
                        ctx.pipeline().replace(this, null, (ChannelHandler)handler);
                    }

                    @Override
                    public AsciiString scheme() {
                        return Utils.HTTP;
                    }
                }
                return new PlaintextHandler();
            }
        };
    }

    public static ProtocolNegotiator serverTls(final SslContext sslContext) {
        Preconditions.checkNotNull(sslContext, "sslContext");
        return new ProtocolNegotiator(){

            @Override
            public ProtocolNegotiator.Handler newHandler(GrpcHttp2ConnectionHandler handler) {
                return new ServerTlsHandler(sslContext, handler);
            }
        };
    }

    public static ProtocolNegotiator httpProxy(final SocketAddress proxyAddress, final @Nullable String proxyUsername, final @Nullable String proxyPassword, final ProtocolNegotiator negotiator) {
        Preconditions.checkNotNull(proxyAddress, "proxyAddress");
        Preconditions.checkNotNull(negotiator, "negotiator");
        class ProxyNegotiator
        implements ProtocolNegotiator {
            ProxyNegotiator() {
            }

            @Override
            public ProtocolNegotiator.Handler newHandler(GrpcHttp2ConnectionHandler http2Handler) {
                HttpProxyHandler proxyHandler = proxyUsername == null || proxyPassword == null ? new HttpProxyHandler(proxyAddress) : new HttpProxyHandler(proxyAddress, proxyUsername, proxyPassword);
                return new BufferUntilProxyTunnelledHandler(proxyHandler, negotiator.newHandler(http2Handler));
            }
        }
        return new ProxyNegotiator();
    }

    public static ProtocolNegotiator tls(SslContext sslContext, String authority) {
        int port;
        String host;
        Preconditions.checkNotNull(sslContext, "sslContext");
        URI uri = GrpcUtil.authorityToUri(Preconditions.checkNotNull(authority, "authority"));
        if (uri.getHost() != null) {
            host = uri.getHost();
            port = uri.getPort();
        } else {
            host = authority;
            port = -1;
        }
        return new TlsNegotiator(sslContext, host, port);
    }

    public static ProtocolNegotiator plaintextUpgrade() {
        return new PlaintextUpgradeNegotiator();
    }

    public static ProtocolNegotiator plaintext() {
        return new PlaintextNegotiator();
    }

    private static RuntimeException unavailableException(String msg) {
        return Status.UNAVAILABLE.withDescription(msg).asRuntimeException();
    }

    @VisibleForTesting
    static void logSslEngineDetails(Level level, ChannelHandlerContext ctx, String msg, @Nullable Throwable t) {
        if (!log.isLoggable(level)) {
            return;
        }
        SslHandler sslHandler = ctx.pipeline().get(SslHandler.class);
        SSLEngine engine = sslHandler.engine();
        StringBuilder builder = new StringBuilder(msg);
        builder.append("\nSSLEngine Details: [\n");
        if (engine instanceof OpenSslEngine) {
            builder.append("    OpenSSL, ");
            builder.append("Version: 0x").append(Integer.toHexString(OpenSsl.version()));
            builder.append(" (").append(OpenSsl.versionString()).append("), ");
            builder.append("ALPN supported: ").append(OpenSsl.isAlpnSupported());
        } else if (JettyTlsUtil.isJettyAlpnConfigured()) {
            builder.append("    Jetty ALPN");
        } else if (JettyTlsUtil.isJettyNpnConfigured()) {
            builder.append("    Jetty NPN");
        } else if (JettyTlsUtil.isJava9AlpnAvailable()) {
            builder.append("    JDK9 ALPN");
        }
        builder.append("\n    TLS Protocol: ");
        builder.append(engine.getSession().getProtocol());
        builder.append("\n    Application Protocol: ");
        builder.append(sslHandler.applicationProtocol());
        builder.append("\n    Need Client Auth: ");
        builder.append(engine.getNeedClientAuth());
        builder.append("\n    Want Client Auth: ");
        builder.append(engine.getWantClientAuth());
        builder.append("\n    Supported protocols=");
        builder.append(Arrays.toString(engine.getSupportedProtocols()));
        builder.append("\n    Enabled protocols=");
        builder.append(Arrays.toString(engine.getEnabledProtocols()));
        builder.append("\n    Supported ciphers=");
        builder.append(Arrays.toString(engine.getSupportedCipherSuites()));
        builder.append("\n    Enabled ciphers=");
        builder.append(Arrays.toString(engine.getEnabledCipherSuites()));
        builder.append("\n]");
        log.log(level, builder.toString(), t);
    }

    private static class BufferingHttp2UpgradeHandler
    extends AbstractBufferingHandler
    implements ProtocolNegotiator.Handler {
        private final GrpcHttp2ConnectionHandler grpcHandler;

        BufferingHttp2UpgradeHandler(ChannelHandler handler, GrpcHttp2ConnectionHandler grpcHandler) {
            super(handler);
            this.grpcHandler = grpcHandler;
        }

        @Override
        public AsciiString scheme() {
            return Utils.HTTP;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            DefaultHttpRequest upgradeTrigger = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
            ctx.writeAndFlush(upgradeTrigger);
            super.channelActive(ctx);
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt == HttpClientUpgradeHandler.UpgradeEvent.UPGRADE_SUCCESSFUL) {
                this.writeBufferedAndRemove(ctx);
                this.grpcHandler.handleProtocolNegotiationCompleted(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ctx.channel().remoteAddress()).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE).build(), null);
            } else if (evt == HttpClientUpgradeHandler.UpgradeEvent.UPGRADE_REJECTED) {
                this.fail(ctx, ProtocolNegotiators.unavailableException("HTTP/2 upgrade rejected"));
            }
            super.userEventTriggered(ctx, evt);
        }
    }

    private static class BufferUntilChannelActiveHandler
    extends AbstractBufferingHandler
    implements ProtocolNegotiator.Handler {
        private final GrpcHttp2ConnectionHandler handler;

        BufferUntilChannelActiveHandler(GrpcHttp2ConnectionHandler handler) {
            super(handler);
            this.handler = handler;
        }

        @Override
        public AsciiString scheme() {
            return Utils.HTTP;
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) {
            this.writeBufferedAndRemove(ctx);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            this.writeBufferedAndRemove(ctx);
            this.handler.handleProtocolNegotiationCompleted(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ctx.channel().remoteAddress()).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE).build(), null);
            super.channelActive(ctx);
        }
    }

    private static class BufferUntilTlsNegotiatedHandler
    extends AbstractBufferingHandler
    implements ProtocolNegotiator.Handler {
        private final GrpcHttp2ConnectionHandler grpcHandler;

        BufferUntilTlsNegotiatedHandler(ChannelHandler bootstrapHandler, GrpcHttp2ConnectionHandler grpcHandler) {
            super(bootstrapHandler);
            this.grpcHandler = grpcHandler;
        }

        @Override
        public AsciiString scheme() {
            return Utils.HTTPS;
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof SslHandshakeCompletionEvent) {
                SslHandshakeCompletionEvent handshakeEvent = (SslHandshakeCompletionEvent)evt;
                if (handshakeEvent.isSuccess()) {
                    SslHandler handler = ctx.pipeline().get(SslHandler.class);
                    if (GrpcSslContexts.NEXT_PROTOCOL_VERSIONS.contains(handler.applicationProtocol())) {
                        ProtocolNegotiators.logSslEngineDetails(Level.FINER, ctx, "TLS negotiation succeeded.", null);
                        ctx.pipeline().addBefore(ctx.name(), null, this.grpcHandler);
                        SSLSession session = handler.engine().getSession();
                        this.grpcHandler.handleProtocolNegotiationCompleted(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_SSL_SESSION, session).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ctx.channel().remoteAddress()).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).build(), new Channelz.Security(new Channelz.Tls(session)));
                        this.writeBufferedAndRemove(ctx);
                    } else {
                        Exception ex = new Exception("Failed ALPN negotiation: Unable to find compatible protocol.");
                        ProtocolNegotiators.logSslEngineDetails(Level.FINE, ctx, "TLS negotiation failed.", ex);
                        this.fail(ctx, ex);
                    }
                } else {
                    this.fail(ctx, handshakeEvent.cause());
                }
            }
            super.userEventTriggered(ctx, evt);
        }
    }

    public static abstract class AbstractBufferingHandler
    extends ChannelDuplexHandler {
        private ChannelHandler[] handlers;
        private Queue<ChannelWrite> bufferedWrites = new ArrayDeque<ChannelWrite>();
        private boolean writing;
        private boolean flushRequested;
        private Throwable failCause;

        protected AbstractBufferingHandler(ChannelHandler ... handlers) {
            this.handlers = handlers;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            if (this.handlers != null) {
                for (ChannelHandler handler : this.handlers) {
                    ctx.pipeline().addBefore(ctx.name(), null, handler);
                }
                ChannelHandler handler0 = this.handlers[0];
                ChannelHandlerContext handler0Ctx = ctx.pipeline().context(this.handlers[0]);
                this.handlers = null;
                if (handler0Ctx != null) {
                    if (handler0 instanceof ChannelInboundHandler) {
                        ((ChannelInboundHandler)handler0).channelRegistered(handler0Ctx);
                    } else {
                        handler0Ctx.fireChannelRegistered();
                    }
                }
            } else {
                super.channelRegistered(ctx);
            }
        }

        @Override
        public void connect(final ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
            super.connect(ctx, remoteAddress, localAddress, promise);
            promise.addListener(new ChannelFutureListener(){

                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        AbstractBufferingHandler.this.fail(ctx, future.cause());
                    }
                }
            });
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            this.fail(ctx, cause);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            this.fail(ctx, ProtocolNegotiators.unavailableException("Connection broken while performing protocol negotiation"));
            super.channelInactive(ctx);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            if (this.failCause != null) {
                promise.setFailure(this.failCause);
                ReferenceCountUtil.release(msg);
            } else if (this.bufferedWrites == null) {
                super.write(ctx, msg, promise);
            } else {
                this.bufferedWrites.add(new ChannelWrite(msg, promise));
            }
        }

        @Override
        public void flush(ChannelHandlerContext ctx) {
            if (this.bufferedWrites == null) {
                ctx.flush();
            } else {
                this.flushRequested = true;
            }
        }

        @Override
        public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
            if (ctx.channel().isActive()) {
                this.fail(ctx, ProtocolNegotiators.unavailableException("Channel closed while performing protocol negotiation"));
            }
            super.close(ctx, future);
        }

        protected final void fail(ChannelHandlerContext ctx, Throwable cause) {
            if (this.failCause == null) {
                this.failCause = cause;
            }
            if (this.bufferedWrites != null) {
                while (!this.bufferedWrites.isEmpty()) {
                    ChannelWrite write = this.bufferedWrites.poll();
                    write.promise.setFailure(cause);
                    ReferenceCountUtil.release(write.msg);
                }
                this.bufferedWrites = null;
            }
            ctx.close();
        }

        protected final void writeBufferedAndRemove(ChannelHandlerContext ctx) {
            if (!ctx.channel().isActive() || this.writing) {
                return;
            }
            this.writing = true;
            while (!this.bufferedWrites.isEmpty()) {
                ChannelWrite write = this.bufferedWrites.poll();
                ctx.write(write.msg, write.promise);
            }
            assert (this.bufferedWrites.isEmpty());
            this.bufferedWrites = null;
            if (this.flushRequested) {
                ctx.flush();
            }
            ctx.pipeline().remove(this);
        }

        private static class ChannelWrite {
            Object msg;
            ChannelPromise promise;

            ChannelWrite(Object msg, ChannelPromise promise) {
                this.msg = msg;
                this.promise = promise;
            }
        }
    }

    static final class PlaintextNegotiator
    implements ProtocolNegotiator {
        PlaintextNegotiator() {
        }

        @Override
        public ProtocolNegotiator.Handler newHandler(GrpcHttp2ConnectionHandler handler) {
            return new BufferUntilChannelActiveHandler(handler);
        }
    }

    static final class PlaintextUpgradeNegotiator
    implements ProtocolNegotiator {
        PlaintextUpgradeNegotiator() {
        }

        @Override
        public ProtocolNegotiator.Handler newHandler(GrpcHttp2ConnectionHandler handler) {
            Http2ClientUpgradeCodec upgradeCodec = new Http2ClientUpgradeCodec(handler);
            HttpClientCodec httpClientCodec = new HttpClientCodec();
            HttpClientUpgradeHandler upgrader = new HttpClientUpgradeHandler(httpClientCodec, upgradeCodec, 1000);
            return new BufferingHttp2UpgradeHandler((ChannelHandler)upgrader, handler);
        }
    }

    static final class TlsNegotiator
    implements ProtocolNegotiator {
        private final SslContext sslContext;
        private final String host;
        private final int port;

        TlsNegotiator(SslContext sslContext, String host, int port) {
            this.sslContext = Preconditions.checkNotNull(sslContext, "sslContext");
            this.host = Preconditions.checkNotNull(host, "host");
            this.port = port;
        }

        @VisibleForTesting
        String getHost() {
            return this.host;
        }

        @VisibleForTesting
        int getPort() {
            return this.port;
        }

        @Override
        public ProtocolNegotiator.Handler newHandler(GrpcHttp2ConnectionHandler handler) {
            ChannelHandlerAdapter sslBootstrap = new ChannelHandlerAdapter(){

                @Override
                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                    SSLEngine sslEngine = TlsNegotiator.this.sslContext.newEngine(ctx.alloc(), TlsNegotiator.this.host, TlsNegotiator.this.port);
                    SSLParameters sslParams = sslEngine.getSSLParameters();
                    sslParams.setEndpointIdentificationAlgorithm("HTTPS");
                    sslEngine.setSSLParameters(sslParams);
                    ctx.pipeline().replace(this, null, (ChannelHandler)new SslHandler(sslEngine, false));
                }
            };
            return new BufferUntilTlsNegotiatedHandler((ChannelHandler)sslBootstrap, handler);
        }
    }

    static final class BufferUntilProxyTunnelledHandler
    extends AbstractBufferingHandler
    implements ProtocolNegotiator.Handler {
        private final ProtocolNegotiator.Handler originalHandler;

        public BufferUntilProxyTunnelledHandler(ProxyHandler proxyHandler, ProtocolNegotiator.Handler handler) {
            super(proxyHandler, handler);
            this.originalHandler = handler;
        }

        @Override
        public AsciiString scheme() {
            return this.originalHandler.scheme();
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof ProxyConnectionEvent) {
                this.writeBufferedAndRemove(ctx);
            }
            super.userEventTriggered(ctx, evt);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            this.fail(ctx, ProtocolNegotiators.unavailableException("Connection broken while trying to CONNECT through proxy"));
            super.channelInactive(ctx);
        }

        @Override
        public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
            if (ctx.channel().isActive()) {
                this.fail(ctx, ProtocolNegotiators.unavailableException("Channel closed while trying to CONNECT through proxy"));
            }
            super.close(ctx, future);
        }
    }

    @VisibleForTesting
    static final class ServerTlsHandler
    extends ChannelInboundHandlerAdapter
    implements ProtocolNegotiator.Handler {
        private final GrpcHttp2ConnectionHandler grpcHandler;
        private final SslContext sslContext;

        ServerTlsHandler(SslContext sslContext, GrpcHttp2ConnectionHandler grpcHandler) {
            this.sslContext = sslContext;
            this.grpcHandler = grpcHandler;
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            super.handlerAdded(ctx);
            SSLEngine sslEngine = this.sslContext.newEngine(ctx.alloc());
            ctx.pipeline().addFirst(new SslHandler(sslEngine, false));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            this.fail(ctx, cause);
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof SslHandshakeCompletionEvent) {
                SslHandshakeCompletionEvent handshakeEvent = (SslHandshakeCompletionEvent)evt;
                if (handshakeEvent.isSuccess()) {
                    if (GrpcSslContexts.NEXT_PROTOCOL_VERSIONS.contains(this.sslHandler(ctx.pipeline()).applicationProtocol())) {
                        SSLSession session = this.sslHandler(ctx.pipeline()).engine().getSession();
                        this.grpcHandler.handleProtocolNegotiationCompleted(Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_SSL_SESSION, session).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, ctx.channel().remoteAddress()).build(), new Channelz.Security(new Channelz.Tls(session)));
                        ctx.pipeline().replace(this, null, (ChannelHandler)this.grpcHandler);
                    } else {
                        this.fail(ctx, new Exception("Failed protocol negotiation: Unable to find compatible protocol."));
                    }
                } else {
                    this.fail(ctx, handshakeEvent.cause());
                }
            }
            super.userEventTriggered(ctx, evt);
        }

        private SslHandler sslHandler(ChannelPipeline pipeline) {
            return pipeline.get(SslHandler.class);
        }

        private void fail(ChannelHandlerContext ctx, Throwable exception) {
            ProtocolNegotiators.logSslEngineDetails(Level.FINE, ctx, "TLS negotiation failed for new client.", exception);
            ctx.close();
        }

        @Override
        public AsciiString scheme() {
            return Utils.HTTPS;
        }
    }
}

