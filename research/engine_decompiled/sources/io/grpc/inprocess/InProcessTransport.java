/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.inprocess.InProcessServer;
import io.grpc.inprocess.InProcessSocketAddress;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class InProcessTransport
implements ServerTransport,
ConnectionClientTransport {
    private static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final String name;
    private final String authority;
    private final String userAgent;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    private ScheduledExecutorService serverScheduler;
    private ServerTransportListener serverTransportListener;
    private Attributes serverStreamAttributes;
    private ManagedClientTransport.Listener clientTransportListener;
    @GuardedBy(value="this")
    private boolean shutdown;
    @GuardedBy(value="this")
    private boolean terminated;
    @GuardedBy(value="this")
    private Status shutdownStatus;
    @GuardedBy(value="this")
    private Set<InProcessStream> streams = new HashSet<InProcessStream>();
    @GuardedBy(value="this")
    private List<ServerStreamTracer.Factory> serverStreamTracerFactories;
    private final Attributes attributes = Attributes.newBuilder().set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).build();

    public InProcessTransport(String name, String authority, String userAgent) {
        this.name = name;
        this.authority = authority;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", userAgent);
    }

    @Override
    @CheckReturnValue
    public synchronized Runnable start(ManagedClientTransport.Listener listener) {
        this.clientTransportListener = listener;
        InProcessServer server = InProcessServer.findServer(this.name);
        if (server != null) {
            this.serverSchedulerPool = server.getScheduledExecutorServicePool();
            this.serverScheduler = this.serverSchedulerPool.getObject();
            this.serverStreamTracerFactories = server.getStreamTracerFactories();
            this.serverTransportListener = server.register(this);
        }
        if (this.serverTransportListener == null) {
            final Status localShutdownStatus = this.shutdownStatus = Status.UNAVAILABLE.withDescription("Could not find server: " + this.name);
            return new Runnable(){

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public void run() {
                    InProcessTransport inProcessTransport = InProcessTransport.this;
                    synchronized (inProcessTransport) {
                        InProcessTransport.this.notifyShutdown(localShutdownStatus);
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            };
        }
        return new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                InProcessTransport inProcessTransport = InProcessTransport.this;
                synchronized (inProcessTransport) {
                    Attributes serverTransportAttrs = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, new InProcessSocketAddress(InProcessTransport.this.name)).build();
                    InProcessTransport.this.serverStreamAttributes = InProcessTransport.this.serverTransportListener.transportReady(serverTransportAttrs);
                    InProcessTransport.this.clientTransportListener.transportReady();
                }
            }
        };
    }

    @Override
    public synchronized ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
        if (this.shutdownStatus != null) {
            final Status capturedStatus = this.shutdownStatus;
            final StatsTraceContext statsTraceCtx = StatsTraceContext.newClientContext(callOptions, headers);
            return new NoopClientStream(){

                @Override
                public void start(ClientStreamListener listener) {
                    statsTraceCtx.clientOutboundHeaders();
                    statsTraceCtx.streamClosed(capturedStatus);
                    listener.closed(capturedStatus, new Metadata());
                }
            };
        }
        headers.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        return new InProcessStream(method, headers, callOptions, this.authority).clientStream;
    }

    @Override
    public synchronized void ping(final ClientTransport.PingCallback callback, Executor executor) {
        if (this.terminated) {
            final Status shutdownStatus = this.shutdownStatus;
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    callback.onFailure(shutdownStatus.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable(){

                @Override
                public void run() {
                    callback.onSuccess(0L);
                }
            });
        }
    }

    @Override
    public synchronized void shutdown(Status reason) {
        if (this.shutdown) {
            return;
        }
        this.shutdownStatus = reason;
        this.notifyShutdown(reason);
        if (this.streams.isEmpty()) {
            this.notifyTerminated();
        }
    }

    @Override
    public synchronized void shutdown() {
        this.shutdown(Status.UNAVAILABLE.withDescription("InProcessTransport shutdown by the server-side"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdownNow(Status reason) {
        ArrayList<InProcessStream> streamsCopy;
        Preconditions.checkNotNull(reason, "reason");
        InProcessTransport inProcessTransport = this;
        synchronized (inProcessTransport) {
            this.shutdown(reason);
            if (this.terminated) {
                return;
            }
            streamsCopy = new ArrayList<InProcessStream>(this.streams);
        }
        for (InProcessStream stream : streamsCopy) {
            stream.clientStream.cancel(reason);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("name", this.name).toString();
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.serverScheduler;
    }

    @Override
    public ListenableFuture<Channelz.SocketStats> getStats() {
        SettableFuture<Channelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    private synchronized void notifyShutdown(Status s2) {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        this.clientTransportListener.transportShutdown(s2);
    }

    private synchronized void notifyTerminated() {
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        if (this.serverScheduler != null) {
            this.serverScheduler = this.serverSchedulerPool.returnObject(this.serverScheduler);
        }
        this.clientTransportListener.transportTerminated();
        if (this.serverTransportListener != null) {
            this.serverTransportListener.transportTerminated();
        }
    }

    private static Status stripCause(Status status) {
        if (status == null) {
            return null;
        }
        return Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
    }

    private static class SingleMessageProducer
    implements StreamListener.MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream message) {
            this.message = message;
        }

        @Override
        @Nullable
        public InputStream next() {
            InputStream messageToReturn = this.message;
            this.message = null;
            return messageToReturn;
        }
    }

    private class InProcessStream {
        private final InProcessClientStream clientStream;
        private final InProcessServerStream serverStream;
        private final Metadata headers;
        private final MethodDescriptor<?, ?> method;
        private volatile String authority;

        private InProcessStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions, String authority) {
            this.method = Preconditions.checkNotNull(method, "method");
            this.headers = Preconditions.checkNotNull(headers, "headers");
            this.authority = authority;
            this.clientStream = new InProcessClientStream(callOptions, headers);
            this.serverStream = new InProcessServerStream(method, headers);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void streamClosed() {
            InProcessTransport inProcessTransport = InProcessTransport.this;
            synchronized (inProcessTransport) {
                boolean justRemovedAnElement = InProcessTransport.this.streams.remove(this);
                if (InProcessTransport.this.streams.isEmpty() && justRemovedAnElement) {
                    InProcessTransport.this.clientTransportListener.transportInUse(false);
                    if (InProcessTransport.this.shutdown) {
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            }
        }

        private class InProcessClientStream
        implements ClientStream {
            final StatsTraceContext statsTraceCtx;
            @GuardedBy(value="this")
            private ServerStreamListener serverStreamListener;
            @GuardedBy(value="this")
            private int serverRequested;
            @GuardedBy(value="this")
            private ArrayDeque<StreamListener.MessageProducer> serverReceiveQueue = new ArrayDeque();
            @GuardedBy(value="this")
            private boolean serverNotifyHalfClose;
            @GuardedBy(value="this")
            private boolean closed;
            @GuardedBy(value="this")
            private int outboundSeqNo;

            InProcessClientStream(CallOptions callOptions, Metadata headers) {
                this.statsTraceCtx = StatsTraceContext.newClientContext(callOptions, headers);
            }

            private synchronized void setListener(ServerStreamListener listener) {
                this.serverStreamListener = listener;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void request(int numMessages) {
                boolean onReady = InProcessStream.this.serverStream.clientRequested(numMessages);
                if (onReady) {
                    InProcessClientStream inProcessClientStream = this;
                    synchronized (inProcessClientStream) {
                        if (!this.closed) {
                            this.serverStreamListener.onReady();
                        }
                    }
                }
            }

            private synchronized boolean serverRequested(int numMessages) {
                if (this.closed) {
                    return false;
                }
                boolean previouslyReady = this.serverRequested > 0;
                this.serverRequested += numMessages;
                while (this.serverRequested > 0 && !this.serverReceiveQueue.isEmpty()) {
                    --this.serverRequested;
                    this.serverStreamListener.messagesAvailable(this.serverReceiveQueue.poll());
                }
                if (this.serverReceiveQueue.isEmpty() && this.serverNotifyHalfClose) {
                    this.serverNotifyHalfClose = false;
                    this.serverStreamListener.halfClosed();
                }
                boolean nowReady = this.serverRequested > 0;
                return !previouslyReady && nowReady;
            }

            private void serverClosed(Status serverListenerStatus, Status serverTracerStatus) {
                this.internalCancel(serverListenerStatus, serverTracerStatus);
            }

            @Override
            public synchronized void writeMessage(InputStream message) {
                if (this.closed) {
                    return;
                }
                this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                ((InProcessStream)InProcessStream.this).serverStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                ((InProcessStream)InProcessStream.this).serverStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                ++this.outboundSeqNo;
                SingleMessageProducer producer = new SingleMessageProducer(message);
                if (this.serverRequested > 0) {
                    --this.serverRequested;
                    this.serverStreamListener.messagesAvailable(producer);
                } else {
                    this.serverReceiveQueue.add(producer);
                }
            }

            @Override
            public void flush() {
            }

            @Override
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.serverRequested > 0;
            }

            @Override
            public void cancel(Status reason) {
                Status serverStatus = InProcessTransport.stripCause(reason);
                if (!this.internalCancel(serverStatus, serverStatus)) {
                    return;
                }
                InProcessStream.this.serverStream.clientCancelled(reason);
                InProcessStream.this.streamClosed();
            }

            private synchronized boolean internalCancel(Status serverListenerStatus, Status serverTracerStatus) {
                StreamListener.MessageProducer producer;
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while ((producer = this.serverReceiveQueue.poll()) != null) {
                    InputStream message;
                    while ((message = producer.next()) != null) {
                        try {
                            message.close();
                        }
                        catch (Throwable t) {
                            log.log(Level.WARNING, "Exception closing stream", t);
                        }
                    }
                }
                ((InProcessStream)InProcessStream.this).serverStream.statsTraceCtx.streamClosed(serverTracerStatus);
                this.serverStreamListener.closed(serverListenerStatus);
                return true;
            }

            @Override
            public synchronized void halfClose() {
                if (this.closed) {
                    return;
                }
                if (this.serverReceiveQueue.isEmpty()) {
                    this.serverStreamListener.halfClosed();
                } else {
                    this.serverNotifyHalfClose = true;
                }
            }

            @Override
            public void setMessageCompression(boolean enable) {
            }

            @Override
            public void setAuthority(String string2) {
                InProcessStream.this.authority = string2;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void start(ClientStreamListener listener) {
                InProcessStream.this.serverStream.setListener(listener);
                InProcessTransport inProcessTransport = InProcessTransport.this;
                synchronized (inProcessTransport) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    InProcessTransport.this.streams.add(InProcessStream.this);
                    if (InProcessTransport.this.streams.size() == 1) {
                        InProcessTransport.this.clientTransportListener.transportInUse(true);
                    }
                    InProcessTransport.this.serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            @Override
            public Attributes getAttributes() {
                return Attributes.EMPTY;
            }

            @Override
            public void setCompressor(Compressor compressor) {
            }

            @Override
            public void setFullStreamDecompression(boolean fullStreamDecompression) {
            }

            @Override
            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            @Override
            public void setMaxInboundMessageSize(int maxSize) {
            }

            @Override
            public void setMaxOutboundMessageSize(int maxSize) {
            }

            @Override
            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                long effectiveTimeout = Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS));
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, effectiveTimeout);
            }
        }

        private class InProcessServerStream
        implements ServerStream {
            final StatsTraceContext statsTraceCtx;
            @GuardedBy(value="this")
            private ClientStreamListener clientStreamListener;
            @GuardedBy(value="this")
            private int clientRequested;
            @GuardedBy(value="this")
            private ArrayDeque<StreamListener.MessageProducer> clientReceiveQueue = new ArrayDeque();
            @GuardedBy(value="this")
            private Status clientNotifyStatus;
            @GuardedBy(value="this")
            private Metadata clientNotifyTrailers;
            @GuardedBy(value="this")
            private boolean closed;
            @GuardedBy(value="this")
            private int outboundSeqNo;

            InProcessServerStream(MethodDescriptor<?, ?> method, Metadata headers) {
                this.statsTraceCtx = StatsTraceContext.newServerContext(InProcessTransport.this.serverStreamTracerFactories, method.getFullMethodName(), headers);
            }

            private synchronized void setListener(ClientStreamListener listener) {
                this.clientStreamListener = listener;
            }

            @Override
            public void setListener(ServerStreamListener serverStreamListener) {
                InProcessStream.this.clientStream.setListener(serverStreamListener);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void request(int numMessages) {
                boolean onReady = InProcessStream.this.clientStream.serverRequested(numMessages);
                if (onReady) {
                    InProcessServerStream inProcessServerStream = this;
                    synchronized (inProcessServerStream) {
                        if (!this.closed) {
                            this.clientStreamListener.onReady();
                        }
                    }
                }
            }

            private synchronized boolean clientRequested(int numMessages) {
                if (this.closed) {
                    return false;
                }
                boolean previouslyReady = this.clientRequested > 0;
                this.clientRequested += numMessages;
                while (this.clientRequested > 0 && !this.clientReceiveQueue.isEmpty()) {
                    --this.clientRequested;
                    this.clientStreamListener.messagesAvailable(this.clientReceiveQueue.poll());
                }
                if (this.closed) {
                    return false;
                }
                if (this.clientReceiveQueue.isEmpty() && this.clientNotifyStatus != null) {
                    this.closed = true;
                    ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.streamClosed(this.clientNotifyStatus);
                    this.clientStreamListener.closed(this.clientNotifyStatus, this.clientNotifyTrailers);
                }
                boolean nowReady = this.clientRequested > 0;
                return !previouslyReady && nowReady;
            }

            private void clientCancelled(Status status) {
                this.internalCancel(status);
            }

            @Override
            public synchronized void writeMessage(InputStream message) {
                if (this.closed) {
                    return;
                }
                this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                ++this.outboundSeqNo;
                SingleMessageProducer producer = new SingleMessageProducer(message);
                if (this.clientRequested > 0) {
                    --this.clientRequested;
                    this.clientStreamListener.messagesAvailable(producer);
                } else {
                    this.clientReceiveQueue.add(producer);
                }
            }

            @Override
            public void flush() {
            }

            @Override
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.clientRequested > 0;
            }

            @Override
            public synchronized void writeHeaders(Metadata headers) {
                if (this.closed) {
                    return;
                }
                ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.clientInboundHeaders();
                this.clientStreamListener.headersRead(headers);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void close(Status status, Metadata trailers) {
                InProcessStream.this.clientStream.serverClosed(Status.OK, status);
                Status clientStatus = InProcessTransport.stripCause(status);
                InProcessServerStream inProcessServerStream = this;
                synchronized (inProcessServerStream) {
                    if (this.closed) {
                        return;
                    }
                    if (this.clientReceiveQueue.isEmpty()) {
                        this.closed = true;
                        ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.streamClosed(clientStatus);
                        this.clientStreamListener.closed(clientStatus, trailers);
                    } else {
                        this.clientNotifyStatus = clientStatus;
                        this.clientNotifyTrailers = trailers;
                    }
                }
                InProcessStream.this.streamClosed();
            }

            @Override
            public void cancel(Status status) {
                if (!this.internalCancel(Status.CANCELLED.withDescription("server cancelled stream"))) {
                    return;
                }
                InProcessStream.this.clientStream.serverClosed(status, status);
                InProcessStream.this.streamClosed();
            }

            private synchronized boolean internalCancel(Status clientStatus) {
                StreamListener.MessageProducer producer;
                if (this.closed) {
                    return false;
                }
                this.closed = true;
                while ((producer = this.clientReceiveQueue.poll()) != null) {
                    InputStream message;
                    while ((message = producer.next()) != null) {
                        try {
                            message.close();
                        }
                        catch (Throwable t) {
                            log.log(Level.WARNING, "Exception closing stream", t);
                        }
                    }
                }
                ((InProcessStream)InProcessStream.this).clientStream.statsTraceCtx.streamClosed(clientStatus);
                this.clientStreamListener.closed(clientStatus, new Metadata());
                return true;
            }

            @Override
            public void setMessageCompression(boolean enable) {
            }

            @Override
            public void setCompressor(Compressor compressor) {
            }

            @Override
            public void setDecompressor(Decompressor decompressor) {
            }

            @Override
            public Attributes getAttributes() {
                return InProcessTransport.this.serverStreamAttributes;
            }

            @Override
            public String getAuthority() {
                return InProcessStream.this.authority;
            }

            @Override
            public StatsTraceContext statsTraceContext() {
                return this.statsTraceCtx;
            }
        }
    }
}

