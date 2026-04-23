/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractServerStream;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.netty.shaded.io.grpc.netty.CancelServerStreamCommand;
import io.grpc.netty.shaded.io.grpc.netty.NettyReadableBuffer;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerHandler;
import io.grpc.netty.shaded.io.grpc.netty.NettyWritableBuffer;
import io.grpc.netty.shaded.io.grpc.netty.NettyWritableBufferAllocator;
import io.grpc.netty.shaded.io.grpc.netty.SendGrpcFrameCommand;
import io.grpc.netty.shaded.io.grpc.netty.SendResponseHeadersCommand;
import io.grpc.netty.shaded.io.grpc.netty.StreamIdHolder;
import io.grpc.netty.shaded.io.grpc.netty.Utils;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import java.util.logging.Level;
import java.util.logging.Logger;

class NettyServerStream
extends AbstractServerStream {
    private static final Logger log = Logger.getLogger(NettyServerStream.class.getName());
    private final Sink sink = new Sink();
    private final TransportState state;
    private final Channel channel;
    private final WriteQueue writeQueue;
    private final Attributes attributes;
    private final String authority;
    private final TransportTracer transportTracer;

    public NettyServerStream(Channel channel, TransportState state, Attributes transportAttrs, String authority, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
        super(new NettyWritableBufferAllocator(channel.alloc()), statsTraceCtx);
        this.state = Preconditions.checkNotNull(state, "transportState");
        this.channel = Preconditions.checkNotNull(channel, "channel");
        this.writeQueue = state.handler.getWriteQueue();
        this.attributes = Preconditions.checkNotNull(transportAttrs);
        this.authority = authority;
        this.transportTracer = Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    @Override
    protected TransportState transportState() {
        return this.state;
    }

    @Override
    protected Sink abstractServerStreamSink() {
        return this.sink;
    }

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public static class TransportState
    extends AbstractServerStream.TransportState
    implements StreamIdHolder {
        private final Http2Stream http2Stream;
        private final NettyServerHandler handler;
        private final EventLoop eventLoop;

        public TransportState(NettyServerHandler handler, EventLoop eventLoop, Http2Stream http2Stream, int maxMessageSize, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
            super(maxMessageSize, statsTraceCtx, transportTracer);
            this.http2Stream = Preconditions.checkNotNull(http2Stream, "http2Stream");
            this.handler = Preconditions.checkNotNull(handler, "handler");
            this.eventLoop = eventLoop;
        }

        @Override
        public void runOnTransportThread(Runnable r) {
            if (this.eventLoop.inEventLoop()) {
                r.run();
            } else {
                this.eventLoop.execute(r);
            }
        }

        @Override
        public void bytesRead(int processedBytes) {
            this.handler.returnProcessedBytes(this.http2Stream, processedBytes);
            this.handler.getWriteQueue().scheduleFlush();
        }

        @Override
        public void deframeFailed(Throwable cause) {
            log.log(Level.WARNING, "Exception processing message", cause);
            Status status = Status.fromThrowable(cause);
            this.transportReportStatus(status);
            this.handler.getWriteQueue().enqueue(new CancelServerStreamCommand(this, status), true);
        }

        void inboundDataReceived(ByteBuf frame, boolean endOfStream) {
            super.inboundDataReceived(new NettyReadableBuffer(frame.retain()), endOfStream);
        }

        @Override
        public int id() {
            return this.http2Stream.id();
        }
    }

    private class Sink
    implements AbstractServerStream.Sink {
        private Sink() {
        }

        @Override
        public void request(final int numMessages) {
            if (NettyServerStream.this.channel.eventLoop().inEventLoop()) {
                NettyServerStream.this.transportState().requestMessagesFromDeframer(numMessages);
            } else {
                NettyServerStream.this.channel.eventLoop().execute(new Runnable(){

                    @Override
                    public void run() {
                        NettyServerStream.this.transportState().requestMessagesFromDeframer(numMessages);
                    }
                });
            }
        }

        @Override
        public void writeHeaders(Metadata headers) {
            NettyServerStream.this.writeQueue.enqueue(SendResponseHeadersCommand.createHeaders(NettyServerStream.this.transportState(), Utils.convertServerHeaders(headers)), true);
        }

        @Override
        public void writeFrame(WritableBuffer frame, boolean flush2, final int numMessages) {
            Preconditions.checkArgument(numMessages >= 0);
            if (frame == null) {
                NettyServerStream.this.writeQueue.scheduleFlush();
                return;
            }
            ByteBuf bytebuf = ((NettyWritableBuffer)frame).bytebuf();
            final int numBytes = bytebuf.readableBytes();
            NettyServerStream.this.onSendingBytes(numBytes);
            NettyServerStream.this.writeQueue.enqueue(new SendGrpcFrameCommand(NettyServerStream.this.transportState(), bytebuf, false), NettyServerStream.this.channel.newPromise().addListener(new ChannelFutureListener(){

                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    NettyServerStream.this.transportState().onSentBytes(numBytes);
                    if (future.isSuccess()) {
                        NettyServerStream.this.transportTracer.reportMessageSent(numMessages);
                    }
                }
            }), flush2);
        }

        @Override
        public void writeTrailers(Metadata trailers, boolean headersSent, Status status) {
            Http2Headers http2Trailers = Utils.convertTrailers(trailers, headersSent);
            NettyServerStream.this.writeQueue.enqueue(SendResponseHeadersCommand.createTrailers(NettyServerStream.this.transportState(), http2Trailers, status), true);
        }

        @Override
        public void cancel(Status status) {
            NettyServerStream.this.writeQueue.enqueue(new CancelServerStreamCommand(NettyServerStream.this.transportState(), status), true);
        }
    }
}

