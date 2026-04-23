/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ContextRunnable;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.PickSubchannelArgsImpl;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.SerializeReentrantCallsDirectExecutor;
import io.grpc.internal.SerializingExecutor;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

final class ClientCallImpl<ReqT, RespT>
extends ClientCall<ReqT, RespT> {
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(Charset.forName("US-ASCII"));
    private final MethodDescriptor<ReqT, RespT> method;
    private final Executor callExecutor;
    private final CallTracer channelCallsTracer;
    private final Context context;
    private volatile ScheduledFuture<?> deadlineCancellationFuture;
    private final boolean unaryRequest;
    private final CallOptions callOptions;
    private final boolean retryEnabled;
    private ClientStream stream;
    private volatile boolean cancelListenersShouldBeRemoved;
    private boolean cancelCalled;
    private boolean halfCloseCalled;
    private final ClientTransportProvider clientTransportProvider;
    private final Context.CancellationListener cancellationListener = new ContextCancellationListener();
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private boolean fullStreamDecompression;
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();

    ClientCallImpl(MethodDescriptor<ReqT, RespT> method, Executor executor, CallOptions callOptions, ClientTransportProvider clientTransportProvider, ScheduledExecutorService deadlineCancellationExecutor, CallTracer channelCallsTracer, boolean retryEnabled) {
        this.method = method;
        this.callExecutor = executor == MoreExecutors.directExecutor() ? new SerializeReentrantCallsDirectExecutor() : new SerializingExecutor(executor);
        this.channelCallsTracer = channelCallsTracer;
        this.context = Context.current();
        this.unaryRequest = method.getType() == MethodDescriptor.MethodType.UNARY || method.getType() == MethodDescriptor.MethodType.SERVER_STREAMING;
        this.callOptions = callOptions;
        this.clientTransportProvider = clientTransportProvider;
        this.deadlineCancellationExecutor = deadlineCancellationExecutor;
        this.retryEnabled = retryEnabled;
    }

    ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean fullStreamDecompression) {
        this.fullStreamDecompression = fullStreamDecompression;
        return this;
    }

    ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        this.decompressorRegistry = decompressorRegistry;
        return this;
    }

    ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry) {
        this.compressorRegistry = compressorRegistry;
        return this;
    }

    @VisibleForTesting
    static void prepareHeaders(Metadata headers, DecompressorRegistry decompressorRegistry, Compressor compressor, boolean fullStreamDecompression) {
        headers.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            headers.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        headers.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] advertisedEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry);
        if (advertisedEncodings.length != 0) {
            headers.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, advertisedEncodings);
        }
        headers.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        headers.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (fullStreamDecompression) {
            headers.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void start(final ClientCall.Listener<RespT> observer, Metadata headers) {
        boolean deadlineExceeded;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(observer, "observer");
        Preconditions.checkNotNull(headers, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            class ClosedByContext
            extends ContextRunnable {
                ClosedByContext() {
                    super(ClientCallImpl.this.context);
                }

                @Override
                public void runInContext() {
                    ClientCallImpl.this.closeObserver(observer, Contexts.statusFromCancelled(ClientCallImpl.this.context), new Metadata());
                }
            }
            this.callExecutor.execute(new ClosedByContext());
            return;
        }
        final String compressorName = this.callOptions.getCompressor();
        Compressor compressor = null;
        if (compressorName != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressorName);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                class ClosedByNotFoundCompressor
                extends ContextRunnable {
                    ClosedByNotFoundCompressor() {
                        super(ClientCallImpl.this.context);
                    }

                    @Override
                    public void runInContext() {
                        ClientCallImpl.this.closeObserver(observer, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", compressorName)), new Metadata());
                    }
                }
                this.callExecutor.execute(new ClosedByNotFoundCompressor());
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        ClientCallImpl.prepareHeaders(headers, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = this.effectiveDeadline();
        boolean bl = deadlineExceeded = effectiveDeadline != null && effectiveDeadline.isExpired();
        if (!deadlineExceeded) {
            ClientCallImpl.logIfContextNarrowedTimeout(effectiveDeadline, this.callOptions.getDeadline(), this.context.getDeadline());
            if (this.retryEnabled) {
                this.stream = this.clientTransportProvider.newRetriableStream(this.method, this.callOptions, headers, this.context);
            } else {
                ClientTransport transport = this.clientTransportProvider.get(new PickSubchannelArgsImpl(this.method, headers, this.callOptions));
                Context origContext = this.context.attach();
                try {
                    this.stream = transport.newStream(this.method, headers, this.callOptions);
                }
                finally {
                    this.context.detach(origContext);
                }
            }
        } else {
            this.stream = new FailingClientStream(Status.DEADLINE_EXCEEDED.withDescription("deadline exceeded: " + effectiveDeadline));
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        if (this.fullStreamDecompression) {
            this.stream.setFullStreamDecompression(this.fullStreamDecompression);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.stream.start(new ClientStreamListenerImpl(observer));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (effectiveDeadline != null && this.context.getDeadline() != effectiveDeadline && this.deadlineCancellationExecutor != null) {
            this.deadlineCancellationFuture = this.startDeadlineTimer(effectiveDeadline);
        }
        if (this.cancelListenersShouldBeRemoved) {
            this.removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline effectiveDeadline, @Nullable Deadline outerCallDeadline, @Nullable Deadline callDeadline) {
        if (!log.isLoggable(Level.FINE) || effectiveDeadline == null || outerCallDeadline != effectiveDeadline) {
            return;
        }
        long effectiveTimeout = Math.max(0L, effectiveDeadline.timeRemaining(TimeUnit.NANOSECONDS));
        StringBuilder builder = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", effectiveTimeout));
        if (callDeadline == null) {
            builder.append(" Explicit call timeout was not set.");
        } else {
            long callTimeout = callDeadline.timeRemaining(TimeUnit.NANOSECONDS);
            builder.append(String.format(" Explicit call timeout was '%d' ns.", callTimeout));
        }
        log.fine(builder.toString());
    }

    private void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> f = this.deadlineCancellationFuture;
        if (f != null) {
            f.cancel(false);
        }
    }

    private ScheduledFuture<?> startDeadlineTimer(Deadline deadline) {
        long remainingNanos = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(remainingNanos)), remainingNanos, TimeUnit.NANOSECONDS);
    }

    @Nullable
    private Deadline effectiveDeadline() {
        return ClientCallImpl.min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline0, @Nullable Deadline deadline1) {
        if (deadline0 == null) {
            return deadline1;
        }
        if (deadline1 == null) {
            return deadline0;
        }
        return deadline0.minimum(deadline1);
    }

    @Override
    public void request(int numMessages) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkArgument(numMessages >= 0, "Number requested must be non-negative");
        this.stream.request(numMessages);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        if (message == null && cause == null) {
            cause = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", cause);
        }
        if (this.cancelCalled) {
            return;
        }
        this.cancelCalled = true;
        try {
            if (this.stream != null) {
                Status status = Status.CANCELLED;
                status = message != null ? status.withDescription(message) : status.withDescription("Call cancelled without message");
                if (cause != null) {
                    status = status.withCause(cause);
                }
                this.stream.cancel(status);
            }
        }
        finally {
            this.removeContextListenerAndCancelDeadlineFuture();
        }
    }

    @Override
    public void halfClose() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    @Override
    public void sendMessage(ReqT message) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            if (this.stream instanceof RetriableStream) {
                RetriableStream retriableStream = (RetriableStream)this.stream;
                retriableStream.sendMessage(message);
            } else {
                this.stream.writeMessage(this.method.streamRequest(message));
            }
        }
        catch (RuntimeException e) {
            this.stream.cancel(Status.CANCELLED.withCause(e).withDescription("Failed to stream message"));
            return;
        }
        catch (Error e) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e;
        }
        if (!this.unaryRequest) {
            this.stream.flush();
        }
    }

    @Override
    public void setMessageCompression(boolean enabled) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(enabled);
    }

    @Override
    public boolean isReady() {
        return this.stream.isReady();
    }

    @Override
    public Attributes getAttributes() {
        if (this.stream != null) {
            return this.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    private void closeObserver(ClientCall.Listener<RespT> observer, Status status, Metadata trailers) {
        observer.onClose(status, trailers);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("method", this.method).toString();
    }

    private class ClientStreamListenerImpl
    implements ClientStreamListener {
        private final ClientCall.Listener<RespT> observer;
        private boolean closed;

        public ClientStreamListenerImpl(ClientCall.Listener<RespT> observer) {
            this.observer = Preconditions.checkNotNull(observer, "observer");
        }

        @Override
        public void headersRead(final Metadata headers) {
            class HeadersRead
            extends ContextRunnable {
                HeadersRead() {
                    super(ClientCallImpl.this.context);
                }

                @Override
                public final void runInContext() {
                    try {
                        if (ClientStreamListenerImpl.this.closed) {
                            return;
                        }
                        ClientStreamListenerImpl.this.observer.onHeaders(headers);
                    }
                    catch (Throwable t) {
                        Status status = Status.CANCELLED.withCause(t).withDescription("Failed to read headers");
                        ClientCallImpl.this.stream.cancel(status);
                        ClientStreamListenerImpl.this.close(status, new Metadata());
                    }
                }
            }
            ClientCallImpl.this.callExecutor.execute(new HeadersRead());
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer producer) {
            class MessagesAvailable
            extends ContextRunnable {
                MessagesAvailable() {
                    super(ClientCallImpl.this.context);
                }

                @Override
                public final void runInContext() {
                    if (ClientStreamListenerImpl.this.closed) {
                        GrpcUtil.closeQuietly(producer);
                        return;
                    }
                    try {
                        InputStream message;
                        while ((message = producer.next()) != null) {
                            try {
                                ClientStreamListenerImpl.this.observer.onMessage(ClientCallImpl.this.method.parseResponse(message));
                            }
                            catch (Throwable t) {
                                GrpcUtil.closeQuietly(message);
                                throw t;
                            }
                            message.close();
                        }
                    }
                    catch (Throwable t) {
                        GrpcUtil.closeQuietly(producer);
                        Status status = Status.CANCELLED.withCause(t).withDescription("Failed to read message.");
                        ClientCallImpl.this.stream.cancel(status);
                        ClientStreamListenerImpl.this.close(status, new Metadata());
                    }
                }
            }
            ClientCallImpl.this.callExecutor.execute(new MessagesAvailable());
        }

        private void close(Status status, Metadata trailers) {
            this.closed = true;
            ClientCallImpl.this.cancelListenersShouldBeRemoved = true;
            try {
                ClientCallImpl.this.closeObserver(this.observer, status, trailers);
            }
            finally {
                ClientCallImpl.this.removeContextListenerAndCancelDeadlineFuture();
                ClientCallImpl.this.channelCallsTracer.reportCallEnded(status.isOk());
            }
        }

        @Override
        public void closed(Status status, Metadata trailers) {
            this.closed(status, ClientStreamListener.RpcProgress.PROCESSED, trailers);
        }

        @Override
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
            Deadline deadline = ClientCallImpl.this.effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && deadline != null && deadline.isExpired()) {
                status = Status.DEADLINE_EXCEEDED;
                trailers = new Metadata();
            }
            final Status savedStatus = status;
            final Metadata savedTrailers = trailers;
            class StreamClosed
            extends ContextRunnable {
                StreamClosed() {
                    super(ClientCallImpl.this.context);
                }

                @Override
                public final void runInContext() {
                    if (ClientStreamListenerImpl.this.closed) {
                        return;
                    }
                    ClientStreamListenerImpl.this.close(savedStatus, savedTrailers);
                }
            }
            ClientCallImpl.this.callExecutor.execute(new StreamClosed());
        }

        @Override
        public void onReady() {
            class StreamOnReady
            extends ContextRunnable {
                StreamOnReady() {
                    super(ClientCallImpl.this.context);
                }

                @Override
                public final void runInContext() {
                    try {
                        ClientStreamListenerImpl.this.observer.onReady();
                    }
                    catch (Throwable t) {
                        Status status = Status.CANCELLED.withCause(t).withDescription("Failed to call onReady.");
                        ClientCallImpl.this.stream.cancel(status);
                        ClientStreamListenerImpl.this.close(status, new Metadata());
                    }
                }
            }
            ClientCallImpl.this.callExecutor.execute(new StreamOnReady());
        }
    }

    private class DeadlineTimer
    implements Runnable {
        private final long remainingNanos;

        DeadlineTimer(long remainingNanos) {
            this.remainingNanos = remainingNanos;
        }

        @Override
        public void run() {
            ClientCallImpl.this.stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(String.format("deadline exceeded after %dns", this.remainingNanos)));
        }
    }

    static interface ClientTransportProvider {
        public ClientTransport get(LoadBalancer.PickSubchannelArgs var1);

        public <ReqT> RetriableStream<ReqT> newRetriableStream(MethodDescriptor<ReqT, ?> var1, CallOptions var2, Metadata var3, Context var4);
    }

    private final class ContextCancellationListener
    implements Context.CancellationListener {
        private ContextCancellationListener() {
        }

        @Override
        public void cancelled(Context context) {
            ClientCallImpl.this.stream.cancel(Contexts.statusFromCancelled(context));
        }
    }
}

