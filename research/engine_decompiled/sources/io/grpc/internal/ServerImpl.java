/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.InternalServerInterceptors;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerTransportFilter;
import io.grpc.Status;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.CallTracer;
import io.grpc.internal.Channelz;
import io.grpc.internal.ContextRunnable;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Instrumented;
import io.grpc.internal.InternalHandlerRegistry;
import io.grpc.internal.InternalServer;
import io.grpc.internal.LogId;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SerializeReentrantCallsDirectExecutor;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.ServerCallImpl;
import io.grpc.internal.ServerCallInfoImpl;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

public final class ServerImpl
extends Server
implements Instrumented<Channelz.ServerStats> {
    private static final Logger log = Logger.getLogger(ServerImpl.class.getName());
    private static final ServerStreamListener NOOP_LISTENER = new NoopListener();
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final ObjectPool<? extends Executor> executorPool;
    private Executor executor;
    private final InternalHandlerRegistry registry;
    private final HandlerRegistry fallbackRegistry;
    private final List<ServerTransportFilter> transportFilters;
    private final ServerInterceptor[] interceptors;
    private final long handshakeTimeoutMillis;
    @GuardedBy(value="lock")
    private boolean started;
    @GuardedBy(value="lock")
    private boolean shutdown;
    @GuardedBy(value="lock")
    private Status shutdownNowStatus;
    @GuardedBy(value="lock")
    private boolean serverShutdownCallbackInvoked;
    @GuardedBy(value="lock")
    private boolean terminated;
    private final InternalServer transportServer;
    private final Object lock = new Object();
    @GuardedBy(value="lock")
    private boolean transportServerTerminated;
    @GuardedBy(value="lock")
    private final Collection<ServerTransport> transports = new HashSet<ServerTransport>();
    private final Context rootContext;
    private final DecompressorRegistry decompressorRegistry;
    private final CompressorRegistry compressorRegistry;
    private final BinaryLog binlog;
    private final Channelz channelz;
    private final CallTracer serverCallTracer;

    ServerImpl(AbstractServerImplBuilder<?> builder, InternalServer transportServer, Context rootContext) {
        this.executorPool = Preconditions.checkNotNull(builder.executorPool, "executorPool");
        this.registry = Preconditions.checkNotNull(builder.registryBuilder.build(), "registryBuilder");
        this.fallbackRegistry = Preconditions.checkNotNull(builder.fallbackRegistry, "fallbackRegistry");
        this.transportServer = Preconditions.checkNotNull(transportServer, "transportServer");
        this.rootContext = Preconditions.checkNotNull(rootContext, "rootContext").fork();
        this.decompressorRegistry = builder.decompressorRegistry;
        this.compressorRegistry = builder.compressorRegistry;
        this.transportFilters = Collections.unmodifiableList(new ArrayList<ServerTransportFilter>(builder.transportFilters));
        this.interceptors = builder.interceptors.toArray(new ServerInterceptor[builder.interceptors.size()]);
        this.handshakeTimeoutMillis = builder.handshakeTimeoutMillis;
        this.binlog = builder.binlog;
        this.channelz = builder.channelz;
        this.serverCallTracer = builder.callTracerFactory.create();
        this.channelz.addServer(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ServerImpl start() throws IOException {
        Object object = this.lock;
        synchronized (object) {
            Preconditions.checkState(!this.started, "Already started");
            Preconditions.checkState(!this.shutdown, "Shutting down");
            this.transportServer.start(new ServerListenerImpl());
            this.executor = Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
            this.started = true;
            return this;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getPort() {
        Object object = this.lock;
        synchronized (object) {
            Preconditions.checkState(this.started, "Not started");
            Preconditions.checkState(!this.terminated, "Already terminated");
            return this.transportServer.getPort();
        }
    }

    @Override
    public List<ServerServiceDefinition> getServices() {
        List<ServerServiceDefinition> fallbackServices = this.fallbackRegistry.getServices();
        if (fallbackServices.isEmpty()) {
            return this.registry.getServices();
        }
        List<ServerServiceDefinition> registryServices = this.registry.getServices();
        int servicesCount = registryServices.size() + fallbackServices.size();
        ArrayList<ServerServiceDefinition> services = new ArrayList<ServerServiceDefinition>(servicesCount);
        services.addAll(registryServices);
        services.addAll(fallbackServices);
        return Collections.unmodifiableList(services);
    }

    @Override
    public List<ServerServiceDefinition> getImmutableServices() {
        return this.registry.getServices();
    }

    @Override
    public List<ServerServiceDefinition> getMutableServices() {
        return Collections.unmodifiableList(this.fallbackRegistry.getServices());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ServerImpl shutdown() {
        boolean shutdownTransportServer;
        Object object = this.lock;
        synchronized (object) {
            if (this.shutdown) {
                return this;
            }
            this.shutdown = true;
            shutdownTransportServer = this.started;
            if (!shutdownTransportServer) {
                this.transportServerTerminated = true;
                this.checkForTermination();
            }
        }
        if (shutdownTransportServer) {
            this.transportServer.shutdown();
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ServerImpl shutdownNow() {
        boolean savedServerShutdownCallbackInvoked;
        ArrayList<ServerTransport> transportsCopy;
        this.shutdown();
        Status nowStatus = Status.UNAVAILABLE.withDescription("Server shutdownNow invoked");
        Iterator iterator2 = this.lock;
        synchronized (iterator2) {
            if (this.shutdownNowStatus != null) {
                return this;
            }
            this.shutdownNowStatus = nowStatus;
            transportsCopy = new ArrayList<ServerTransport>(this.transports);
            savedServerShutdownCallbackInvoked = this.serverShutdownCallbackInvoked;
        }
        if (savedServerShutdownCallbackInvoked) {
            for (ServerTransport transport : transportsCopy) {
                transport.shutdownNow(nowStatus);
            }
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isShutdown() {
        Object object = this.lock;
        synchronized (object) {
            return this.shutdown;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        Object object = this.lock;
        synchronized (object) {
            long timeoutNanos = unit.toNanos(timeout);
            long endTimeNanos = System.nanoTime() + timeoutNanos;
            while (!this.terminated && (timeoutNanos = endTimeNanos - System.nanoTime()) > 0L) {
                TimeUnit.NANOSECONDS.timedWait(this.lock, timeoutNanos);
            }
            return this.terminated;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void awaitTermination() throws InterruptedException {
        Object object = this.lock;
        synchronized (object) {
            while (!this.terminated) {
                this.lock.wait();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isTerminated() {
        Object object = this.lock;
        synchronized (object) {
            return this.terminated;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void transportClosed(ServerTransport transport) {
        Object object = this.lock;
        synchronized (object) {
            if (!this.transports.remove(transport)) {
                throw new AssertionError((Object)"Transport already removed");
            }
            this.channelz.removeServerSocket(this, transport);
            this.checkForTermination();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void checkForTermination() {
        Object object = this.lock;
        synchronized (object) {
            if (this.shutdown && this.transports.isEmpty() && this.transportServerTerminated) {
                if (this.terminated) {
                    throw new AssertionError((Object)"Server already terminated");
                }
                this.terminated = true;
                this.channelz.removeServer(this);
                if (this.executor != null) {
                    this.executor = this.executorPool.returnObject(this.executor);
                }
                this.lock.notifyAll();
            }
        }
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    @Override
    public ListenableFuture<Channelz.ServerStats> getStats() {
        Channelz.ServerStats.Builder builder = new Channelz.ServerStats.Builder().setListenSockets(this.transportServer.getListenSockets());
        this.serverCallTracer.updateBuilder(builder);
        SettableFuture<Channelz.ServerStats> ret = SettableFuture.create();
        ret.set(builder.build());
        return ret;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("transportServer", this.transportServer).toString();
    }

    @VisibleForTesting
    static final class ContextCloser
    implements Runnable {
        private final Context.CancellableContext context;
        private final Throwable cause;

        ContextCloser(Context.CancellableContext context, Throwable cause) {
            this.context = context;
            this.cause = cause;
        }

        @Override
        public void run() {
            this.context.cancel(this.cause);
        }
    }

    @VisibleForTesting
    static final class JumpToApplicationThreadServerStreamListener
    implements ServerStreamListener {
        private final Executor callExecutor;
        private final Executor cancelExecutor;
        private final Context.CancellableContext context;
        private final ServerStream stream;
        private ServerStreamListener listener;

        public JumpToApplicationThreadServerStreamListener(Executor executor, Executor cancelExecutor, ServerStream stream, Context.CancellableContext context) {
            this.callExecutor = executor;
            this.cancelExecutor = cancelExecutor;
            this.stream = stream;
            this.context = context;
        }

        private ServerStreamListener getListener() {
            if (this.listener == null) {
                throw new IllegalStateException("listener unset");
            }
            return this.listener;
        }

        @VisibleForTesting
        void setListener(ServerStreamListener listener) {
            Preconditions.checkNotNull(listener, "listener must not be null");
            Preconditions.checkState(this.listener == null, "Listener already set");
            this.listener = listener;
        }

        private void internalClose() {
            this.stream.close(Status.UNKNOWN, new Metadata());
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            final class MessagesAvailable
            extends ContextRunnable {
                MessagesAvailable() {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override
                public void runInContext() {
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().messagesAvailable(producer);
                    }
                    catch (RuntimeException e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                    catch (Error e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                }
            }
            this.callExecutor.execute(new MessagesAvailable());
        }

        @Override
        public void halfClosed() {
            final class HalfClosed
            extends ContextRunnable {
                HalfClosed() {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override
                public void runInContext() {
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().halfClosed();
                    }
                    catch (RuntimeException e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                    catch (Error e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                }
            }
            this.callExecutor.execute(new HalfClosed());
        }

        @Override
        public void closed(final Status status) {
            if (!status.isOk()) {
                this.cancelExecutor.execute(new ContextCloser(this.context, status.getCause()));
            }
            final class Closed
            extends ContextRunnable {
                Closed() {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override
                public void runInContext() {
                    JumpToApplicationThreadServerStreamListener.this.getListener().closed(status);
                }
            }
            this.callExecutor.execute(new Closed());
        }

        @Override
        public void onReady() {
            final class OnReady
            extends ContextRunnable {
                OnReady() {
                    super(JumpToApplicationThreadServerStreamListener.this.context);
                }

                @Override
                public void runInContext() {
                    try {
                        JumpToApplicationThreadServerStreamListener.this.getListener().onReady();
                    }
                    catch (RuntimeException e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                    catch (Error e) {
                        JumpToApplicationThreadServerStreamListener.this.internalClose();
                        throw e;
                    }
                }
            }
            this.callExecutor.execute(new OnReady());
        }
    }

    private static final class NoopListener
    implements ServerStreamListener {
        private NoopListener() {
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            InputStream message;
            while ((message = producer.next()) != null) {
                try {
                    message.close();
                }
                catch (IOException e) {
                    while ((message = producer.next()) != null) {
                        try {
                            message.close();
                        }
                        catch (IOException ioException) {
                            log.log(Level.WARNING, "Exception closing stream", ioException);
                        }
                    }
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public void halfClosed() {
        }

        @Override
        public void closed(Status status) {
        }

        @Override
        public void onReady() {
        }
    }

    private final class ServerTransportListenerImpl
    implements ServerTransportListener {
        private final ServerTransport transport;
        private Future<?> handshakeTimeoutFuture;
        private Attributes attributes;

        ServerTransportListenerImpl(ServerTransport transport) {
            this.transport = transport;
        }

        public void init() {
            class TransportShutdownNow
            implements Runnable {
                TransportShutdownNow() {
                }

                @Override
                public void run() {
                    ServerTransportListenerImpl.this.transport.shutdownNow(Status.CANCELLED.withDescription("Handshake timeout exceeded"));
                }
            }
            this.handshakeTimeoutFuture = ServerImpl.this.handshakeTimeoutMillis != Long.MAX_VALUE ? this.transport.getScheduledExecutorService().schedule(new TransportShutdownNow(), ServerImpl.this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS) : new FutureTask<Object>(new Runnable(){

                @Override
                public void run() {
                }
            }, null);
            ServerImpl.this.channelz.addServerSocket(ServerImpl.this, this.transport);
        }

        @Override
        public Attributes transportReady(Attributes attributes) {
            this.handshakeTimeoutFuture.cancel(false);
            this.handshakeTimeoutFuture = null;
            for (ServerTransportFilter filter2 : ServerImpl.this.transportFilters) {
                attributes = Preconditions.checkNotNull(filter2.transportReady(attributes), "Filter %s returned null", (Object)filter2);
            }
            this.attributes = attributes;
            return attributes;
        }

        @Override
        public void transportTerminated() {
            if (this.handshakeTimeoutFuture != null) {
                this.handshakeTimeoutFuture.cancel(false);
                this.handshakeTimeoutFuture = null;
            }
            for (ServerTransportFilter filter2 : ServerImpl.this.transportFilters) {
                filter2.transportTerminated(this.attributes);
            }
            ServerImpl.this.transportClosed(this.transport);
        }

        @Override
        public void streamCreated(final ServerStream stream, final String methodName, final Metadata headers) {
            if (headers.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
                String encoding = headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                Decompressor decompressor = ServerImpl.this.decompressorRegistry.lookupDecompressor(encoding);
                if (decompressor == null) {
                    stream.close(Status.UNIMPLEMENTED.withDescription(String.format("Can't find decompressor for %s", encoding)), new Metadata());
                    return;
                }
                stream.setDecompressor(decompressor);
            }
            final StatsTraceContext statsTraceCtx = Preconditions.checkNotNull(stream.statsTraceContext(), "statsTraceCtx not present from stream");
            final Context.CancellableContext context = this.createContext(stream, headers, statsTraceCtx);
            Executor wrappedExecutor = ServerImpl.this.executor == MoreExecutors.directExecutor() ? new SerializeReentrantCallsDirectExecutor() : new SerializingExecutor(ServerImpl.this.executor);
            final JumpToApplicationThreadServerStreamListener jumpListener = new JumpToApplicationThreadServerStreamListener(wrappedExecutor, ServerImpl.this.executor, stream, context);
            stream.setListener(jumpListener);
            final class StreamCreated
            extends ContextRunnable {
                StreamCreated() {
                    super(cancellableContext);
                }

                @Override
                public void runInContext() {
                    ServerStreamListener listener = NOOP_LISTENER;
                    try {
                        ServerMethodDefinition<?, ?> method = ServerImpl.this.registry.lookupMethod(methodName);
                        if (method == null) {
                            method = ServerImpl.this.fallbackRegistry.lookupMethod(methodName, stream.getAuthority());
                        }
                        if (method == null) {
                            Status status = Status.UNIMPLEMENTED.withDescription("Method not found: " + methodName);
                            stream.close(status, new Metadata());
                            context.cancel(null);
                            return;
                        }
                        listener = ServerTransportListenerImpl.this.startCall(stream, methodName, method, headers, context, statsTraceCtx);
                    }
                    catch (RuntimeException e) {
                        stream.close(Status.fromThrowable(e), new Metadata());
                        context.cancel(null);
                        throw e;
                    }
                    catch (Error e) {
                        stream.close(Status.fromThrowable(e), new Metadata());
                        context.cancel(null);
                        throw e;
                    }
                    finally {
                        jumpListener.setListener(listener);
                    }
                }
            }
            wrappedExecutor.execute(new StreamCreated());
        }

        private Context.CancellableContext createContext(final ServerStream stream, Metadata headers, StatsTraceContext statsTraceCtx) {
            Long timeoutNanos = headers.get(GrpcUtil.TIMEOUT_KEY);
            Context baseContext = statsTraceCtx.serverFilterContext(ServerImpl.this.rootContext);
            if (timeoutNanos == null) {
                return baseContext.withCancellation();
            }
            Context.CancellableContext context = baseContext.withDeadlineAfter(timeoutNanos, TimeUnit.NANOSECONDS, this.transport.getScheduledExecutorService());
            final class ServerStreamCancellationListener
            implements Context.CancellationListener {
                ServerStreamCancellationListener() {
                }

                @Override
                public void cancelled(Context context) {
                    Status status = Contexts.statusFromCancelled(context);
                    if (Status.DEADLINE_EXCEEDED.getCode().equals((Object)status.getCode())) {
                        stream.cancel(status);
                    }
                }
            }
            context.addListener(new ServerStreamCancellationListener(), MoreExecutors.directExecutor());
            return context;
        }

        private <ReqT, RespT> ServerStreamListener startCall(ServerStream stream, String fullMethodName, ServerMethodDefinition<ReqT, RespT> methodDef, Metadata headers, Context.CancellableContext context, StatsTraceContext statsTraceCtx) {
            statsTraceCtx.serverCallStarted(new ServerCallInfoImpl<ReqT, RespT>(methodDef.getMethodDescriptor(), stream.getAttributes(), stream.getAuthority()));
            ServerCallHandler<ReqT, RespT> handler = methodDef.getServerCallHandler();
            for (ServerInterceptor interceptor : ServerImpl.this.interceptors) {
                handler = InternalServerInterceptors.interceptCallHandler(interceptor, handler);
            }
            ServerMethodDefinition<ReqT, RespT> interceptedDef = methodDef.withServerCallHandler(handler);
            ServerMethodDefinition<ReqT, RespT> wMethodDef = ServerImpl.this.binlog == null ? interceptedDef : ServerImpl.this.binlog.wrapMethodDefinition(interceptedDef);
            return this.startWrappedCall(fullMethodName, wMethodDef, stream, headers, context);
        }

        private <WReqT, WRespT> ServerStreamListener startWrappedCall(String fullMethodName, ServerMethodDefinition<WReqT, WRespT> methodDef, ServerStream stream, Metadata headers, Context.CancellableContext context) {
            ServerCallImpl<WReqT, WRespT> call = new ServerCallImpl<WReqT, WRespT>(stream, methodDef.getMethodDescriptor(), headers, context, ServerImpl.this.decompressorRegistry, ServerImpl.this.compressorRegistry, ServerImpl.this.serverCallTracer);
            ServerCall.Listener<WReqT> listener = methodDef.getServerCallHandler().startCall(call, headers);
            if (listener == null) {
                throw new NullPointerException("startCall() returned a null listener for method " + fullMethodName);
            }
            return call.newServerStreamListener(listener);
        }
    }

    private final class ServerListenerImpl
    implements ServerListener {
        private ServerListenerImpl() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public ServerTransportListener transportCreated(ServerTransport transport) {
            Object object = ServerImpl.this.lock;
            synchronized (object) {
                ServerImpl.this.transports.add(transport);
            }
            ServerTransportListenerImpl stli = new ServerTransportListenerImpl(transport);
            stli.init();
            return stli;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void serverShutdown() {
            Status shutdownNowStatusCopy;
            ArrayList copiedTransports;
            Object object = ServerImpl.this.lock;
            synchronized (object) {
                copiedTransports = new ArrayList(ServerImpl.this.transports);
                shutdownNowStatusCopy = ServerImpl.this.shutdownNowStatus;
                ServerImpl.this.serverShutdownCallbackInvoked = true;
            }
            for (ServerTransport transport : copiedTransports) {
                if (shutdownNowStatusCopy == null) {
                    transport.shutdown();
                    continue;
                }
                transport.shutdownNow(shutdownNowStatusCopy);
            }
            object = ServerImpl.this.lock;
            synchronized (object) {
                ServerImpl.this.transportServerTerminated = true;
                ServerImpl.this.checkForTermination();
            }
        }
    }
}

