/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.Attributes;
import io.grpc.InternalMetadata;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.grpc.netty.AbstractNettyHandler;
import io.grpc.netty.shaded.io.grpc.netty.CancelServerStreamCommand;
import io.grpc.netty.shaded.io.grpc.netty.ForcefulCloseCommand;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2HeadersUtils;
import io.grpc.netty.shaded.io.grpc.netty.KeepAliveEnforcer;
import io.grpc.netty.shaded.io.grpc.netty.MaxConnectionIdleManager;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerStream;
import io.grpc.netty.shaded.io.grpc.netty.SendGrpcFrameCommand;
import io.grpc.netty.shaded.io.grpc.netty.SendResponseHeadersCommand;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DecoratingHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Error;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameReader;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameWriter;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2InboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2OutboundFrameLogger;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.WeightedFairQueueByteDistributor;
import io.grpc.netty.shaded.io.netty.handler.logging.LogLevel;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

class NettyServerHandler
extends AbstractNettyHandler {
    private static final Logger logger = Logger.getLogger(NettyServerHandler.class.getName());
    private static final long KEEPALIVE_PING = 57005L;
    private static final long GRACEFUL_SHUTDOWN_PING = 40715087873L;
    private static final long GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS = TimeUnit.SECONDS.toNanos(10L);
    private final Http2Connection.PropertyKey streamKey;
    private final ServerTransportListener transportListener;
    private final int maxMessageSize;
    private final long keepAliveTimeInNanos;
    private final long keepAliveTimeoutInNanos;
    private final long maxConnectionAgeInNanos;
    private final long maxConnectionAgeGraceInNanos;
    private final List<ServerStreamTracer.Factory> streamTracerFactories;
    private final TransportTracer transportTracer;
    private final KeepAliveEnforcer keepAliveEnforcer;
    private Attributes negotiationAttributes;
    private Channelz.Security securityInfo;
    private Attributes attributes;
    private Throwable connectionError;
    private boolean teWarningLogged;
    private WriteQueue serverWriteQueue;
    private AsciiString lastKnownAuthority;
    @CheckForNull
    private KeepAliveManager keepAliveManager;
    @CheckForNull
    private MaxConnectionIdleManager maxConnectionIdleManager;
    @CheckForNull
    private ScheduledFuture<?> maxConnectionAgeMonitor;
    @CheckForNull
    private GracefulShutdown gracefulShutdown;

    static NettyServerHandler newHandler(ServerTransportListener transportListener, ChannelPromise channelUnused, List<ServerStreamTracer.Factory> streamTracerFactories, TransportTracer transportTracer, int maxStreams, int flowControlWindow, int maxHeaderListSize, int maxMessageSize, long keepAliveTimeInNanos, long keepAliveTimeoutInNanos, long maxConnectionIdleInNanos, long maxConnectionAgeInNanos, long maxConnectionAgeGraceInNanos, boolean permitKeepAliveWithoutCalls, long permitKeepAliveTimeInNanos) {
        Preconditions.checkArgument(maxHeaderListSize > 0, "maxHeaderListSize must be positive");
        Http2FrameLogger frameLogger = new Http2FrameLogger(LogLevel.DEBUG, NettyServerHandler.class);
        GrpcHttp2HeadersUtils.GrpcHttp2ServerHeadersDecoder headersDecoder = new GrpcHttp2HeadersUtils.GrpcHttp2ServerHeadersDecoder(maxHeaderListSize);
        Http2InboundFrameLogger frameReader = new Http2InboundFrameLogger(new DefaultHttp2FrameReader(headersDecoder), frameLogger);
        Http2OutboundFrameLogger frameWriter = new Http2OutboundFrameLogger(new DefaultHttp2FrameWriter(), frameLogger);
        return NettyServerHandler.newHandler(channelUnused, frameReader, frameWriter, transportListener, streamTracerFactories, transportTracer, maxStreams, flowControlWindow, maxHeaderListSize, maxMessageSize, keepAliveTimeInNanos, keepAliveTimeoutInNanos, maxConnectionIdleInNanos, maxConnectionAgeInNanos, maxConnectionAgeGraceInNanos, permitKeepAliveWithoutCalls, permitKeepAliveTimeInNanos);
    }

    @VisibleForTesting
    static NettyServerHandler newHandler(ChannelPromise channelUnused, Http2FrameReader frameReader, Http2FrameWriter frameWriter, ServerTransportListener transportListener, List<ServerStreamTracer.Factory> streamTracerFactories, TransportTracer transportTracer, int maxStreams, int flowControlWindow, int maxHeaderListSize, int maxMessageSize, long keepAliveTimeInNanos, long keepAliveTimeoutInNanos, long maxConnectionIdleInNanos, long maxConnectionAgeInNanos, long maxConnectionAgeGraceInNanos, boolean permitKeepAliveWithoutCalls, long permitKeepAliveTimeInNanos) {
        Preconditions.checkArgument(maxStreams > 0, "maxStreams must be positive");
        Preconditions.checkArgument(flowControlWindow > 0, "flowControlWindow must be positive");
        Preconditions.checkArgument(maxHeaderListSize > 0, "maxHeaderListSize must be positive");
        Preconditions.checkArgument(maxMessageSize > 0, "maxMessageSize must be positive");
        DefaultHttp2Connection connection = new DefaultHttp2Connection(true);
        WeightedFairQueueByteDistributor dist = new WeightedFairQueueByteDistributor(connection);
        dist.allocationQuantum(16384);
        DefaultHttp2RemoteFlowController controller = new DefaultHttp2RemoteFlowController((Http2Connection)connection, dist);
        connection.remote().flowController(controller);
        KeepAliveEnforcer keepAliveEnforcer = new KeepAliveEnforcer(permitKeepAliveWithoutCalls, permitKeepAliveTimeInNanos, TimeUnit.NANOSECONDS);
        connection.local().flowController(new DefaultHttp2LocalFlowController(connection, 0.5f, true));
        frameWriter = new WriteMonitoringFrameWriter(frameWriter, keepAliveEnforcer);
        DefaultHttp2ConnectionEncoder encoder = new DefaultHttp2ConnectionEncoder(connection, frameWriter);
        DefaultHttp2ConnectionDecoder decoder = new DefaultHttp2ConnectionDecoder(connection, encoder, frameReader);
        Http2Settings settings = new Http2Settings();
        settings.initialWindowSize(flowControlWindow);
        settings.maxConcurrentStreams(maxStreams);
        settings.maxHeaderListSize(maxHeaderListSize);
        return new NettyServerHandler(channelUnused, connection, transportListener, streamTracerFactories, transportTracer, decoder, encoder, settings, maxMessageSize, keepAliveTimeInNanos, keepAliveTimeoutInNanos, maxConnectionIdleInNanos, maxConnectionAgeInNanos, maxConnectionAgeGraceInNanos, keepAliveEnforcer);
    }

    private NettyServerHandler(ChannelPromise channelUnused, final Http2Connection connection, ServerTransportListener transportListener, List<ServerStreamTracer.Factory> streamTracerFactories, TransportTracer transportTracer, Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings settings, int maxMessageSize, long keepAliveTimeInNanos, long keepAliveTimeoutInNanos, long maxConnectionIdleInNanos, long maxConnectionAgeInNanos, long maxConnectionAgeGraceInNanos, final KeepAliveEnforcer keepAliveEnforcer) {
        super(channelUnused, decoder, encoder, settings);
        final MaxConnectionIdleManager maxConnectionIdleManager = maxConnectionIdleInNanos == Long.MAX_VALUE ? null : new MaxConnectionIdleManager(maxConnectionIdleInNanos){

            @Override
            void close(ChannelHandlerContext ctx) {
                if (NettyServerHandler.this.gracefulShutdown == null) {
                    NettyServerHandler.this.gracefulShutdown = new GracefulShutdown("max_idle", null);
                    NettyServerHandler.this.gracefulShutdown.start(ctx);
                    ctx.flush();
                }
            }
        };
        connection.addListener(new Http2ConnectionAdapter(){

            @Override
            public void onStreamActive(Http2Stream stream) {
                if (connection.numActiveStreams() == 1) {
                    keepAliveEnforcer.onTransportActive();
                    if (maxConnectionIdleManager != null) {
                        maxConnectionIdleManager.onTransportActive();
                    }
                }
            }

            @Override
            public void onStreamClosed(Http2Stream stream) {
                if (connection.numActiveStreams() == 0) {
                    keepAliveEnforcer.onTransportIdle();
                    if (maxConnectionIdleManager != null) {
                        maxConnectionIdleManager.onTransportIdle();
                    }
                }
            }
        });
        Preconditions.checkArgument(maxMessageSize >= 0, "maxMessageSize must be >= 0");
        this.maxMessageSize = maxMessageSize;
        this.keepAliveTimeInNanos = keepAliveTimeInNanos;
        this.keepAliveTimeoutInNanos = keepAliveTimeoutInNanos;
        this.maxConnectionIdleManager = maxConnectionIdleManager;
        this.maxConnectionAgeInNanos = maxConnectionAgeInNanos;
        this.maxConnectionAgeGraceInNanos = maxConnectionAgeGraceInNanos;
        this.keepAliveEnforcer = Preconditions.checkNotNull(keepAliveEnforcer, "keepAliveEnforcer");
        this.streamKey = encoder.connection().newKey();
        this.transportListener = Preconditions.checkNotNull(transportListener, "transportListener");
        this.streamTracerFactories = Preconditions.checkNotNull(streamTracerFactories, "streamTracerFactories");
        this.transportTracer = Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.decoder().frameListener(new FrameListener());
    }

    @Nullable
    Throwable connectionError() {
        return this.connectionError;
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        this.serverWriteQueue = new WriteQueue(ctx.channel());
        if (this.maxConnectionAgeInNanos != Long.MAX_VALUE) {
            this.maxConnectionAgeMonitor = ctx.executor().schedule(new LogExceptionRunnable(new Runnable(){

                @Override
                public void run() {
                    if (NettyServerHandler.this.gracefulShutdown == null) {
                        NettyServerHandler.this.gracefulShutdown = new GracefulShutdown("max_age", NettyServerHandler.this.maxConnectionAgeGraceInNanos);
                        NettyServerHandler.this.gracefulShutdown.start(ctx);
                        ctx.flush();
                    }
                }
            }), this.maxConnectionAgeInNanos, TimeUnit.NANOSECONDS);
        }
        if (this.maxConnectionIdleManager != null) {
            this.maxConnectionIdleManager.start(ctx);
        }
        if (this.keepAliveTimeInNanos != Long.MAX_VALUE) {
            this.keepAliveManager = new KeepAliveManager(new KeepAlivePinger(ctx), ctx.executor(), this.keepAliveTimeInNanos, this.keepAliveTimeoutInNanos, true);
            this.keepAliveManager.onTransportStarted();
        }
        if (this.transportTracer != null) {
            assert (this.encoder().connection().equals(this.decoder().connection()));
            final Http2Connection connection = this.encoder().connection();
            this.transportTracer.setFlowControlWindowReader(new TransportTracer.FlowControlReader(){
                private final Http2FlowController local;
                private final Http2FlowController remote;
                {
                    this.local = connection.local().flowController();
                    this.remote = connection.remote().flowController();
                }

                @Override
                public TransportTracer.FlowControlWindows read() {
                    assert (ctx.executor().inEventLoop());
                    return new TransportTracer.FlowControlWindows(this.local.windowSize(connection.connectionStream()), this.remote.windowSize(connection.connectionStream()));
                }
            });
        }
        super.handlerAdded(ctx);
    }

    private void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers) throws Http2Exception {
        if (!this.teWarningLogged && !Utils.TE_TRAILERS.equals(headers.get(Utils.TE_HEADER))) {
            logger.warning(String.format("Expected header TE: %s, but %s is received. This means some intermediate proxy may not support trailers", Utils.TE_TRAILERS, headers.get(Utils.TE_HEADER)));
            this.teWarningLogged = true;
        }
        try {
            CharSequence path = headers.path();
            if (path == null) {
                this.respondWithHttpError(ctx, streamId, 404, Status.Code.UNIMPLEMENTED, "Expected path but is missing");
                return;
            }
            if (path.charAt(0) != '/') {
                this.respondWithHttpError(ctx, streamId, 404, Status.Code.UNIMPLEMENTED, String.format("Expected path to start with /: %s", path));
                return;
            }
            String method = path.subSequence(1, path.length()).toString();
            CharSequence contentType = (CharSequence)headers.get(Utils.CONTENT_TYPE_HEADER);
            if (contentType == null) {
                this.respondWithHttpError(ctx, streamId, 415, Status.Code.INTERNAL, "Content-Type is missing from the request");
                return;
            }
            String contentTypeString = contentType.toString();
            if (!GrpcUtil.isGrpcContentType(contentTypeString)) {
                this.respondWithHttpError(ctx, streamId, 415, Status.Code.INTERNAL, String.format("Content-Type '%s' is not supported", contentTypeString));
                return;
            }
            if (!Utils.HTTP_METHOD.equals(headers.method())) {
                this.respondWithHttpError(ctx, streamId, 405, Status.Code.INTERNAL, String.format("Method '%s' is not supported", headers.method()));
                return;
            }
            Http2Stream http2Stream = this.requireHttp2Stream(streamId);
            Metadata metadata = Utils.convertHeaders(headers);
            StatsTraceContext statsTraceCtx = StatsTraceContext.newServerContext(this.streamTracerFactories, method, metadata);
            NettyServerStream.TransportState state = new NettyServerStream.TransportState(this, ctx.channel().eventLoop(), http2Stream, this.maxMessageSize, statsTraceCtx, this.transportTracer);
            String authority = this.getOrUpdateAuthority((AsciiString)headers.authority());
            NettyServerStream stream = new NettyServerStream(ctx.channel(), state, this.attributes, authority, statsTraceCtx, this.transportTracer);
            this.transportListener.streamCreated(stream, method, metadata);
            state.onStreamAllocated();
            http2Stream.setProperty(this.streamKey, state);
        }
        catch (Exception e) {
            logger.log(Level.WARNING, "Exception in onHeadersRead()", e);
            throw this.newStreamException(streamId, e);
        }
    }

    private String getOrUpdateAuthority(AsciiString authority) {
        if (authority == null) {
            return null;
        }
        if (!authority.equals(this.lastKnownAuthority)) {
            this.lastKnownAuthority = authority;
        }
        return this.lastKnownAuthority.toString();
    }

    private void onDataRead(int streamId, ByteBuf data, int padding2, boolean endOfStream) throws Http2Exception {
        this.flowControlPing().onDataRead(data.readableBytes(), padding2);
        try {
            NettyServerStream.TransportState stream = this.serverStream(this.requireHttp2Stream(streamId));
            stream.inboundDataReceived(data, endOfStream);
        }
        catch (Throwable e) {
            logger.log(Level.WARNING, "Exception in onDataRead()", e);
            throw this.newStreamException(streamId, e);
        }
    }

    private void onRstStreamRead(int streamId, long errorCode) throws Http2Exception {
        try {
            NettyServerStream.TransportState stream = this.serverStream(this.connection().stream(streamId));
            if (stream != null) {
                stream.transportReportStatus(Status.CANCELLED.withDescription("RST_STREAM received for code " + errorCode));
            }
        }
        catch (Throwable e) {
            logger.log(Level.WARNING, "Exception in onRstStreamRead()", e);
            throw this.newStreamException(streamId, e);
        }
    }

    @Override
    protected void onConnectionError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception http2Ex) {
        logger.log(Level.FINE, "Connection Error", cause);
        this.connectionError = cause;
        super.onConnectionError(ctx, outbound, cause, http2Ex);
    }

    @Override
    protected void onStreamError(ChannelHandlerContext ctx, boolean outbound, Throwable cause, Http2Exception.StreamException http2Ex) {
        logger.log(Level.WARNING, "Stream Error", cause);
        NettyServerStream.TransportState serverStream = this.serverStream(this.connection().stream(Http2Exception.streamId(http2Ex)));
        if (serverStream != null) {
            serverStream.transportReportStatus(Utils.statusFromThrowable(cause));
        }
        super.onStreamError(ctx, outbound, cause, http2Ex);
    }

    @Override
    public void handleProtocolNegotiationCompleted(Attributes attrs, Channelz.Security securityInfo) {
        this.negotiationAttributes = attrs;
        this.securityInfo = securityInfo;
    }

    Channelz.Security getSecurityInfo() {
        return this.securityInfo;
    }

    @VisibleForTesting
    KeepAliveManager getKeepAliveManagerForTest() {
        return this.keepAliveManager;
    }

    @VisibleForTesting
    void setKeepAliveManagerForTest(KeepAliveManager keepAliveManager) {
        this.keepAliveManager = keepAliveManager;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        try {
            if (this.keepAliveManager != null) {
                this.keepAliveManager.onTransportTermination();
            }
            if (this.maxConnectionIdleManager != null) {
                this.maxConnectionIdleManager.onTransportTermination();
            }
            if (this.maxConnectionAgeMonitor != null) {
                this.maxConnectionAgeMonitor.cancel(false);
            }
            final Status status = Status.UNAVAILABLE.withDescription("connection terminated for unknown reason");
            this.connection().forEachActiveStream(new Http2StreamVisitor(){

                @Override
                public boolean visit(Http2Stream stream) throws Http2Exception {
                    NettyServerStream.TransportState serverStream = NettyServerHandler.this.serverStream(stream);
                    if (serverStream != null) {
                        serverStream.transportReportStatus(status);
                    }
                    return true;
                }
            });
        }
        finally {
            super.channelInactive(ctx);
        }
    }

    WriteQueue getWriteQueue() {
        return this.serverWriteQueue;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof SendGrpcFrameCommand) {
            this.sendGrpcFrame(ctx, (SendGrpcFrameCommand)msg, promise);
        } else if (msg instanceof SendResponseHeadersCommand) {
            this.sendResponseHeaders(ctx, (SendResponseHeadersCommand)msg, promise);
        } else if (msg instanceof CancelServerStreamCommand) {
            this.cancelStream(ctx, (CancelServerStreamCommand)msg, promise);
        } else if (msg instanceof ForcefulCloseCommand) {
            this.forcefulClose(ctx, (ForcefulCloseCommand)msg, promise);
        } else {
            AssertionError e = new AssertionError((Object)("Write called for unexpected type: " + msg.getClass().getName()));
            ReferenceCountUtil.release(msg);
            promise.setFailure((Throwable)((Object)e));
            throw e;
        }
    }

    void returnProcessedBytes(Http2Stream http2Stream, int bytes2) {
        try {
            this.decoder().flowController().consumeBytes(http2Stream, bytes2);
        }
        catch (Http2Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void closeStreamWhenDone(ChannelPromise promise, int streamId) throws Http2Exception {
        final NettyServerStream.TransportState stream = this.serverStream(this.requireHttp2Stream(streamId));
        promise.addListener(new ChannelFutureListener(){

            @Override
            public void operationComplete(ChannelFuture future) {
                stream.complete();
            }
        });
    }

    private void sendGrpcFrame(ChannelHandlerContext ctx, SendGrpcFrameCommand cmd, ChannelPromise promise) throws Http2Exception {
        if (cmd.endStream()) {
            this.closeStreamWhenDone(promise, cmd.streamId());
        }
        this.encoder().writeData(ctx, cmd.streamId(), cmd.content(), 0, cmd.endStream(), promise);
    }

    private void sendResponseHeaders(ChannelHandlerContext ctx, SendResponseHeadersCommand cmd, ChannelPromise promise) throws Http2Exception {
        int streamId = cmd.stream().id();
        Http2Stream stream = this.connection().stream(streamId);
        if (stream == null) {
            this.resetStream(ctx, streamId, Http2Error.CANCEL.code(), promise);
            return;
        }
        if (cmd.endOfStream()) {
            this.closeStreamWhenDone(promise, streamId);
        }
        this.encoder().writeHeaders(ctx, streamId, cmd.headers(), 0, cmd.endOfStream(), promise);
    }

    private void cancelStream(ChannelHandlerContext ctx, CancelServerStreamCommand cmd, ChannelPromise promise) {
        cmd.stream().transportReportStatus(cmd.reason());
        this.encoder().writeRstStream(ctx, cmd.stream().id(), Http2Error.CANCEL.code(), promise);
    }

    private void forcefulClose(final ChannelHandlerContext ctx, final ForcefulCloseCommand msg, ChannelPromise promise) throws Exception {
        this.close(ctx, promise);
        this.connection().forEachActiveStream(new Http2StreamVisitor(){

            @Override
            public boolean visit(Http2Stream stream) throws Http2Exception {
                NettyServerStream.TransportState serverStream = NettyServerHandler.this.serverStream(stream);
                if (serverStream != null) {
                    serverStream.transportReportStatus(msg.getStatus());
                    NettyServerHandler.this.resetStream(ctx, stream.id(), Http2Error.CANCEL.code(), ctx.newPromise());
                }
                stream.close();
                return true;
            }
        });
    }

    private void respondWithHttpError(ChannelHandlerContext ctx, int streamId, int code, Status.Code statusCode, String msg) {
        Metadata metadata = new Metadata();
        metadata.put(InternalStatus.CODE_KEY, statusCode.toStatus());
        metadata.put(InternalStatus.MESSAGE_KEY, msg);
        byte[][] serialized = InternalMetadata.serialize(metadata);
        Http2Headers headers = (Http2Headers)new DefaultHttp2Headers(true, serialized.length / 2).status("" + code).set(Utils.CONTENT_TYPE_HEADER, "text/plain; encoding=utf-8");
        for (int i = 0; i < serialized.length; i += 2) {
            headers.add(new AsciiString(serialized[i], false), new AsciiString(serialized[i + 1], false));
        }
        this.encoder().writeHeaders(ctx, streamId, headers, 0, false, ctx.newPromise());
        ByteBuf msgBuf = ByteBufUtil.writeUtf8(ctx.alloc(), (CharSequence)msg);
        this.encoder().writeData(ctx, streamId, msgBuf, 0, true, ctx.newPromise());
    }

    private Http2Stream requireHttp2Stream(int streamId) {
        Http2Stream stream = this.connection().stream(streamId);
        if (stream == null) {
            throw new AssertionError((Object)("Stream does not exist: " + streamId));
        }
        return stream;
    }

    private NettyServerStream.TransportState serverStream(Http2Stream stream) {
        return stream == null ? null : (NettyServerStream.TransportState)stream.getProperty(this.streamKey);
    }

    private Http2Exception newStreamException(int streamId, Throwable cause) {
        return Http2Exception.streamError(streamId, Http2Error.INTERNAL_ERROR, cause, Strings.nullToEmpty(cause.getMessage()), new Object[0]);
    }

    private static class WriteMonitoringFrameWriter
    extends DecoratingHttp2FrameWriter {
        private final KeepAliveEnforcer keepAliveEnforcer;

        public WriteMonitoringFrameWriter(Http2FrameWriter delegate, KeepAliveEnforcer keepAliveEnforcer) {
            super(delegate);
            this.keepAliveEnforcer = keepAliveEnforcer;
        }

        @Override
        public ChannelFuture writeData(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding2, boolean endStream, ChannelPromise promise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeData(ctx, streamId, data, padding2, endStream, promise);
        }

        @Override
        public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding2, boolean endStream, ChannelPromise promise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeHeaders(ctx, streamId, headers, padding2, endStream, promise);
        }

        @Override
        public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding2, boolean endStream, ChannelPromise promise) {
            this.keepAliveEnforcer.resetCounters();
            return super.writeHeaders(ctx, streamId, headers, streamDependency, weight, exclusive, padding2, endStream, promise);
        }
    }

    private final class GracefulShutdown {
        String goAwayMessage;
        @CheckForNull
        Long graceTimeInNanos;
        boolean pingAckedOrTimeout;
        Future<?> pingFuture;

        GracefulShutdown(@Nullable String goAwayMessage, Long graceTimeInNanos) {
            this.goAwayMessage = goAwayMessage;
            this.graceTimeInNanos = graceTimeInNanos;
        }

        void start(final ChannelHandlerContext ctx) {
            NettyServerHandler.this.goAway(ctx, Integer.MAX_VALUE, Http2Error.NO_ERROR.code(), ByteBufUtil.writeAscii(ctx.alloc(), (CharSequence)this.goAwayMessage), ctx.newPromise());
            long gracefulShutdownPingTimeout = GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS;
            this.pingFuture = ctx.executor().schedule(new Runnable(){

                @Override
                public void run() {
                    GracefulShutdown.this.secondGoAwayAndClose(ctx);
                }
            }, GRACEFUL_SHUTDOWN_PING_TIMEOUT_NANOS, TimeUnit.NANOSECONDS);
            NettyServerHandler.this.encoder().writePing(ctx, false, 40715087873L, ctx.newPromise());
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void secondGoAwayAndClose(ChannelHandlerContext ctx) {
            long savedGracefulShutdownTimeMillis;
            if (this.pingAckedOrTimeout) {
                return;
            }
            this.pingAckedOrTimeout = true;
            Preconditions.checkNotNull(this.pingFuture, "pingFuture");
            this.pingFuture.cancel(false);
            NettyServerHandler.this.goAway(ctx, NettyServerHandler.this.connection().remote().lastStreamCreated(), Http2Error.NO_ERROR.code(), ByteBufUtil.writeAscii(ctx.alloc(), (CharSequence)this.goAwayMessage), ctx.newPromise());
            long gracefulShutdownTimeoutMillis = savedGracefulShutdownTimeMillis = NettyServerHandler.this.gracefulShutdownTimeoutMillis();
            if (this.graceTimeInNanos != null) {
                gracefulShutdownTimeoutMillis = TimeUnit.NANOSECONDS.toMillis(this.graceTimeInNanos);
            }
            try {
                NettyServerHandler.this.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis);
                NettyServerHandler.this.close(ctx, ctx.newPromise());
            }
            catch (Exception e) {
                NettyServerHandler.this.onError(ctx, true, e);
            }
            finally {
                NettyServerHandler.this.gracefulShutdownTimeoutMillis(savedGracefulShutdownTimeMillis);
            }
        }
    }

    private final class KeepAlivePinger
    implements KeepAliveManager.KeepAlivePinger {
        final ChannelHandlerContext ctx;

        KeepAlivePinger(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void ping() {
            ChannelFuture pingFuture = NettyServerHandler.this.encoder().writePing(this.ctx, false, 57005L, this.ctx.newPromise());
            this.ctx.flush();
            if (NettyServerHandler.this.transportTracer != null) {
                pingFuture.addListener(new ChannelFutureListener(){

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            NettyServerHandler.this.transportTracer.reportKeepAliveSent();
                        }
                    }
                });
            }
        }

        @Override
        public void onPingTimeout() {
            try {
                NettyServerHandler.this.forcefulClose(this.ctx, new ForcefulCloseCommand(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone")), this.ctx.newPromise());
            }
            catch (Exception ex) {
                try {
                    NettyServerHandler.this.exceptionCaught(this.ctx, ex);
                }
                catch (Exception ex2) {
                    logger.log(Level.WARNING, "Exception while propagating exception", ex2);
                    logger.log(Level.WARNING, "Original failure", ex);
                }
            }
        }
    }

    private class FrameListener
    extends Http2FrameAdapter {
        private boolean firstSettings = true;

        private FrameListener() {
        }

        @Override
        public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) {
            if (this.firstSettings) {
                this.firstSettings = false;
                NettyServerHandler.this.attributes = NettyServerHandler.this.transportListener.transportReady(NettyServerHandler.this.negotiationAttributes);
            }
        }

        @Override
        public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding2, boolean endOfStream) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onDataRead(streamId, data, padding2, endOfStream);
            return padding2;
        }

        @Override
        public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding2, boolean endStream) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onHeadersRead(ctx, streamId, headers);
        }

        @Override
        public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            NettyServerHandler.this.onRstStreamRead(streamId, errorCode);
        }

        @Override
        public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            if (!NettyServerHandler.this.keepAliveEnforcer.pingAcceptable()) {
                ByteBuf debugData = ByteBufUtil.writeAscii(ctx.alloc(), (CharSequence)"too_many_pings");
                NettyServerHandler.this.goAway(ctx, NettyServerHandler.this.connection().remote().lastStreamCreated(), Http2Error.ENHANCE_YOUR_CALM.code(), debugData, ctx.newPromise());
                Status status = Status.RESOURCE_EXHAUSTED.withDescription("Too many pings from client");
                try {
                    NettyServerHandler.this.forcefulClose(ctx, new ForcefulCloseCommand(status), ctx.newPromise());
                }
                catch (Exception ex) {
                    NettyServerHandler.this.onError(ctx, true, ex);
                }
            }
        }

        @Override
        public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
            if (NettyServerHandler.this.keepAliveManager != null) {
                NettyServerHandler.this.keepAliveManager.onDataReceived();
            }
            if (data == NettyServerHandler.this.flowControlPing().payload()) {
                NettyServerHandler.this.flowControlPing().updateWindow();
                if (logger.isLoggable(Level.FINE)) {
                    logger.log(Level.FINE, String.format("Window: %d", NettyServerHandler.this.decoder().flowController().initialWindowSize(NettyServerHandler.this.connection().connectionStream())));
                }
            } else if (data == 40715087873L) {
                if (NettyServerHandler.this.gracefulShutdown == null) {
                    logger.warning("Received GRACEFUL_SHUTDOWN_PING Ack but gracefulShutdown is null");
                } else {
                    NettyServerHandler.this.gracefulShutdown.secondGoAwayAndClose(ctx);
                }
            } else if (data != 57005L) {
                logger.warning("Received unexpected ping ack. No ping outstanding");
            }
        }
    }
}

