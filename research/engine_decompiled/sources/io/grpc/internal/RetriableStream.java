/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.RetryPolicy;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

abstract class RetriableStream<ReqT>
implements ClientStream {
    @VisibleForTesting
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    @VisibleForTesting
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    private static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    private final MethodDescriptor<ReqT, ?> method;
    private final Executor callExecutor;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Metadata headers;
    private final RetryPolicy.Provider retryPolicyProvider;
    private RetryPolicy retryPolicy;
    private final Object lock = new Object();
    private final ChannelBufferMeter channelBufferUsed;
    private final long perRpcBufferLimit;
    private final long channelBufferLimit;
    @Nullable
    private final Throttle throttle;
    private volatile State state = new State(new ArrayList<BufferEntry>(8), Collections.<Substream>emptyList(), null, false, false);
    private boolean noMoreTransparentRetry;
    @GuardedBy(value="lock")
    private long perRpcBufferUsed;
    private ClientStreamListener masterListener;
    private Future<?> scheduledRetry;
    private long nextBackoffIntervalNanos;
    private static Random random = new Random();

    RetriableStream(MethodDescriptor<ReqT, ?> method, Metadata headers, ChannelBufferMeter channelBufferUsed, long perRpcBufferLimit, long channelBufferLimit, Executor callExecutor, ScheduledExecutorService scheduledExecutorService, RetryPolicy.Provider retryPolicyProvider, @Nullable Throttle throttle) {
        this.method = method;
        this.channelBufferUsed = channelBufferUsed;
        this.perRpcBufferLimit = perRpcBufferLimit;
        this.channelBufferLimit = channelBufferLimit;
        this.callExecutor = callExecutor;
        this.scheduledExecutorService = scheduledExecutorService;
        this.headers = headers;
        this.retryPolicyProvider = Preconditions.checkNotNull(retryPolicyProvider, "retryPolicyProvider");
        this.throttle = throttle;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    @CheckReturnValue
    private Runnable commit(final Substream winningSubstream) {
        Object object = this.lock;
        synchronized (object) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> savedDrainedSubstreams = this.state.drainedSubstreams;
            this.state = this.state.committed(winningSubstream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            class CommitTask
            implements Runnable {
                CommitTask() {
                }

                @Override
                public void run() {
                    for (Substream substream : savedDrainedSubstreams) {
                        if (substream == winningSubstream) continue;
                        substream.stream.cancel(CANCELLED_BECAUSE_COMMITTED);
                    }
                    RetriableStream.this.postCommit();
                }
            }
            return new CommitTask();
        }
    }

    abstract void postCommit();

    private void commitAndRun(Substream winningSubstream) {
        Runnable postCommitTask = this.commit(winningSubstream);
        if (postCommitTask != null) {
            postCommitTask.run();
        }
    }

    private Substream createSubstream(int previousAttempts) {
        Substream sub = new Substream(previousAttempts);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(sub);
        ClientStreamTracer.Factory tracerFactory = new ClientStreamTracer.Factory(){

            @Override
            public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata headers) {
                return bufferSizeTracer;
            }
        };
        Metadata newHeaders = this.updateHeaders(this.headers, previousAttempts);
        sub.stream = this.newSubstream(tracerFactory, newHeaders);
        return sub;
    }

    abstract ClientStream newSubstream(ClientStreamTracer.Factory var1, Metadata var2);

    @VisibleForTesting
    final Metadata updateHeaders(Metadata originalHeaders, int previousAttempts) {
        Metadata newHeaders = new Metadata();
        newHeaders.merge(originalHeaders);
        if (previousAttempts > 0) {
            newHeaders.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(previousAttempts));
        }
        return newHeaders;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    private void drain(Substream substream) {
        index = 0;
        chunk = 128;
        list = null;
        block3: while (true) {
            var6_6 = this.lock;
            synchronized (var6_6) {
                savedState = this.state;
                if (savedState.winningSubstream != null && savedState.winningSubstream != substream) {
                    break;
                }
                if (index == savedState.buffer.size()) {
                    this.state = savedState.substreamDrained(substream);
                    return;
                }
                if (substream.closed) {
                    return;
                }
                stop = Math.min(index + chunk, savedState.buffer.size());
                if (list == null) {
                    list = new ArrayList<BufferEntry>(savedState.buffer.subList(index, stop));
                } else {
                    list.clear();
                    list.addAll(savedState.buffer.subList(index, stop));
                }
                index = stop;
            }
            var6_6 = list.iterator();
            while (true) {
                if (!var6_6.hasNext()) continue block3;
                bufferEntry = (BufferEntry)var6_6.next();
                savedState = this.state;
                if (savedState.winningSubstream == null || savedState.winningSubstream == substream) ** break;
                continue block3;
                if (savedState.cancelled) {
                    Preconditions.checkState(savedState.winningSubstream == substream, "substream should be CANCELLED_BECAUSE_COMMITTED already");
                    return;
                }
                bufferEntry.runWith(substream);
            }
            break;
        }
        substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
    }

    @CheckReturnValue
    @Nullable
    abstract Status prestart();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void start(ClientStreamListener listener) {
        this.masterListener = listener;
        Status shutdownStatus = this.prestart();
        if (shutdownStatus != null) {
            this.cancel(shutdownStatus);
            return;
        }
        Object object = this.lock;
        synchronized (object) {
            class StartEntry
            implements BufferEntry {
                StartEntry() {
                }

                @Override
                public void runWith(Substream substream) {
                    substream.stream.start(new Sublistener(substream));
                }
            }
            this.state.buffer.add(new StartEntry());
        }
        Substream substream = this.createSubstream(0);
        this.drain(substream);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void cancel(Status reason) {
        Substream noopSubstream = new Substream(0);
        noopSubstream.stream = new NoopClientStream();
        Runnable runnable = this.commit(noopSubstream);
        if (runnable != null) {
            Future<?> savedScheduledRetry = this.scheduledRetry;
            if (savedScheduledRetry != null) {
                savedScheduledRetry.cancel(false);
                this.scheduledRetry = null;
            }
            this.masterListener.closed(reason, new Metadata());
            runnable.run();
            return;
        }
        this.state.winningSubstream.stream.cancel(reason);
        Object object = this.lock;
        synchronized (object) {
            this.state = this.state.cancelled();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> savedDrainedSubstreams;
        Iterator<Substream> iterator2 = this.lock;
        synchronized (iterator2) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            savedDrainedSubstreams = this.state.drainedSubstreams;
        }
        for (Substream substream : savedDrainedSubstreams) {
            bufferEntry.runWith(substream);
        }
    }

    @Override
    public final void writeMessage(InputStream message) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    final void sendMessage(final ReqT message) {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.writeMessage(this.method.streamRequest(message));
            return;
        }
        class SendMessageEntry
        implements BufferEntry {
            SendMessageEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.writeMessage(RetriableStream.this.method.streamRequest(message));
            }
        }
        this.delayOrExecute(new SendMessageEntry());
    }

    @Override
    public final void request(final int numMessages) {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.request(numMessages);
            return;
        }
        class RequestEntry
        implements BufferEntry {
            RequestEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.request(numMessages);
            }
        }
        this.delayOrExecute(new RequestEntry());
    }

    @Override
    public final void flush() {
        State savedState = this.state;
        if (savedState.passThrough) {
            savedState.winningSubstream.stream.flush();
            return;
        }
        class FlushEntry
        implements BufferEntry {
            FlushEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.flush();
            }
        }
        this.delayOrExecute(new FlushEntry());
    }

    @Override
    public final boolean isReady() {
        for (Substream substream : this.state.drainedSubstreams) {
            if (!substream.stream.isReady()) continue;
            return true;
        }
        return false;
    }

    @Override
    public final void setCompressor(final Compressor compressor) {
        class CompressorEntry
        implements BufferEntry {
            CompressorEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        }
        this.delayOrExecute(new CompressorEntry());
    }

    @Override
    public final void setFullStreamDecompression(final boolean fullStreamDecompression) {
        class FullStreamDecompressionEntry
        implements BufferEntry {
            FullStreamDecompressionEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(fullStreamDecompression);
            }
        }
        this.delayOrExecute(new FullStreamDecompressionEntry());
    }

    @Override
    public final void setMessageCompression(final boolean enable) {
        class MessageCompressionEntry
        implements BufferEntry {
            MessageCompressionEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(enable);
            }
        }
        this.delayOrExecute(new MessageCompressionEntry());
    }

    @Override
    public final void halfClose() {
        class HalfCloseEntry
        implements BufferEntry {
            HalfCloseEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        }
        this.delayOrExecute(new HalfCloseEntry());
    }

    @Override
    public final void setAuthority(final String authority) {
        class AuthorityEntry
        implements BufferEntry {
            AuthorityEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setAuthority(authority);
            }
        }
        this.delayOrExecute(new AuthorityEntry());
    }

    @Override
    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        class DecompressorRegistryEntry
        implements BufferEntry {
            DecompressorRegistryEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        }
        this.delayOrExecute(new DecompressorRegistryEntry());
    }

    @Override
    public final void setMaxInboundMessageSize(final int maxSize) {
        class MaxInboundMessageSizeEntry
        implements BufferEntry {
            MaxInboundMessageSizeEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(maxSize);
            }
        }
        this.delayOrExecute(new MaxInboundMessageSizeEntry());
    }

    @Override
    public final void setMaxOutboundMessageSize(final int maxSize) {
        class MaxOutboundMessageSizeEntry
        implements BufferEntry {
            MaxOutboundMessageSizeEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(maxSize);
            }
        }
        this.delayOrExecute(new MaxOutboundMessageSizeEntry());
    }

    @Override
    public final void setDeadline(final Deadline deadline) {
        class DeadlineEntry
        implements BufferEntry {
            DeadlineEntry() {
            }

            @Override
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        }
        this.delayOrExecute(new DeadlineEntry());
    }

    @Override
    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    @VisibleForTesting
    static void setRandom(Random random) {
        RetriableStream.random = random;
    }

    boolean hasHedging() {
        return false;
    }

    private static final class RetryPlan {
        final boolean shouldRetry;
        final long backoffNanos;

        RetryPlan(boolean shouldRetry, long backoffNanos) {
            this.shouldRetry = shouldRetry;
            this.backoffNanos = backoffNanos;
        }
    }

    static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final int tokenRatio;
        final AtomicInteger tokenCount = new AtomicInteger();

        Throttle(float maxTokens, float tokenRatio) {
            this.tokenRatio = (int)(tokenRatio * 1000.0f);
            this.maxTokens = (int)(maxTokens * 1000.0f);
            this.threshold = this.maxTokens / 2;
            this.tokenCount.set(this.maxTokens);
        }

        @VisibleForTesting
        boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        @VisibleForTesting
        boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int decremented;
            int currentCount;
            boolean updated2;
            do {
                if ((currentCount = this.tokenCount.get()) != 0) continue;
                return false;
            } while (!(updated2 = this.tokenCount.compareAndSet(currentCount, Math.max(decremented = currentCount - 1000, 0))));
            return decremented > this.threshold;
        }

        @VisibleForTesting
        void onSuccess() {
            int incremented;
            boolean updated2;
            int currentCount;
            while ((currentCount = this.tokenCount.get()) != this.maxTokens && !(updated2 = this.tokenCount.compareAndSet(currentCount, Math.min(incremented = currentCount + this.tokenRatio, this.maxTokens)))) {
            }
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Throttle)) {
                return false;
            }
            Throttle that = (Throttle)o;
            return this.maxTokens == that.maxTokens && this.tokenRatio == that.tokenRatio;
        }

        public int hashCode() {
            return Objects.hashCode(this.maxTokens, this.tokenRatio);
        }
    }

    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        @VisibleForTesting
        long addAndGet(long newBytesUsed) {
            return this.bufferUsed.addAndGet(newBytesUsed);
        }
    }

    class BufferSizeTracer
    extends ClientStreamTracer {
        private final Substream substream;
        @GuardedBy(value="lock")
        long bufferNeeded;

        BufferSizeTracer(Substream substream) {
            this.substream = substream;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void outboundWireSize(long bytes2) {
            if (((RetriableStream)RetriableStream.this).state.winningSubstream != null) {
                return;
            }
            Runnable postCommitTask = null;
            Object object = RetriableStream.this.lock;
            synchronized (object) {
                if (((RetriableStream)RetriableStream.this).state.winningSubstream != null || this.substream.closed) {
                    return;
                }
                this.bufferNeeded += bytes2;
                if (this.bufferNeeded <= RetriableStream.this.perRpcBufferUsed) {
                    return;
                }
                if (this.bufferNeeded > RetriableStream.this.perRpcBufferLimit) {
                    this.substream.bufferLimitExceeded = true;
                } else {
                    long savedChannelBufferUsed = RetriableStream.this.channelBufferUsed.addAndGet(this.bufferNeeded - RetriableStream.this.perRpcBufferUsed);
                    RetriableStream.this.perRpcBufferUsed = this.bufferNeeded;
                    if (savedChannelBufferUsed > RetriableStream.this.channelBufferLimit) {
                        this.substream.bufferLimitExceeded = true;
                    }
                }
                if (this.substream.bufferLimitExceeded) {
                    postCommitTask = RetriableStream.this.commit(this.substream);
                }
            }
            if (postCommitTask != null) {
                postCommitTask.run();
            }
        }
    }

    private static final class Substream {
        ClientStream stream;
        boolean closed;
        boolean bufferLimitExceeded;
        final int previousAttempts;

        Substream(int previousAttempts) {
            this.previousAttempts = previousAttempts;
        }
    }

    private static final class State {
        final boolean passThrough;
        @Nullable
        final List<BufferEntry> buffer;
        final Collection<Substream> drainedSubstreams;
        @Nullable
        final Substream winningSubstream;
        final boolean cancelled;

        State(@Nullable List<BufferEntry> buffer, Collection<Substream> drainedSubstreams, @Nullable Substream winningSubstream, boolean cancelled, boolean passThrough) {
            this.buffer = buffer;
            this.drainedSubstreams = Preconditions.checkNotNull(drainedSubstreams, "drainedSubstreams");
            this.winningSubstream = winningSubstream;
            this.cancelled = cancelled;
            this.passThrough = passThrough;
            Preconditions.checkState(!passThrough || buffer == null, "passThrough should imply buffer is null");
            Preconditions.checkState(!passThrough || winningSubstream != null, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!passThrough || drainedSubstreams.size() == 1 && drainedSubstreams.contains(winningSubstream) || drainedSubstreams.size() == 0 && winningSubstream.closed, "passThrough should imply winningSubstream is drained");
            Preconditions.checkState(!cancelled || winningSubstream != null, "cancelled should imply committed");
        }

        @CheckReturnValue
        State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.winningSubstream, true, this.passThrough);
        }

        @CheckReturnValue
        State substreamDrained(Substream substream) {
            Collection<Substream> drainedSubstreams;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                drainedSubstreams = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                drainedSubstreams = Collections.singletonList(substream);
            } else {
                drainedSubstreams = new ArrayList<Substream>(this.drainedSubstreams);
                drainedSubstreams.add(substream);
                drainedSubstreams = Collections.unmodifiableCollection(drainedSubstreams);
            }
            boolean passThrough = this.winningSubstream != null;
            List<BufferEntry> buffer = this.buffer;
            if (passThrough) {
                Preconditions.checkState(this.winningSubstream == substream, "Another RPC attempt has already committed");
                buffer = null;
            }
            return new State(buffer, drainedSubstreams, this.winningSubstream, this.cancelled, passThrough);
        }

        @CheckReturnValue
        State substreamClosed(Substream substream) {
            substream.closed = true;
            if (this.drainedSubstreams.contains(substream)) {
                Collection<Substream> drainedSubstreams = new ArrayList<Substream>(this.drainedSubstreams);
                drainedSubstreams.remove(substream);
                drainedSubstreams = Collections.unmodifiableCollection(drainedSubstreams);
                return new State(this.buffer, drainedSubstreams, this.winningSubstream, this.cancelled, this.passThrough);
            }
            return this;
        }

        @CheckReturnValue
        State committed(Substream winningSubstream) {
            Collection<Substream> drainedSubstreams;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            boolean passThrough = false;
            List<BufferEntry> buffer = this.buffer;
            if (this.drainedSubstreams.contains(winningSubstream)) {
                passThrough = true;
                buffer = null;
                drainedSubstreams = Collections.singleton(winningSubstream);
            } else {
                drainedSubstreams = Collections.emptyList();
            }
            return new State(buffer, drainedSubstreams, winningSubstream, this.cancelled, passThrough);
        }
    }

    private final class Sublistener
    implements ClientStreamListener {
        final Substream substream;

        Sublistener(Substream substream) {
            this.substream = substream;
        }

        @Override
        public void headersRead(Metadata headers) {
            RetriableStream.this.commitAndRun(this.substream);
            if (((RetriableStream)RetriableStream.this).state.winningSubstream == this.substream) {
                RetriableStream.this.masterListener.headersRead(headers);
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
            }
        }

        @Override
        public void closed(Status status, Metadata trailers) {
            this.closed(status, ClientStreamListener.RpcProgress.PROCESSED, trailers);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata trailers) {
            Object object = RetriableStream.this.lock;
            synchronized (object) {
                RetriableStream.this.state = RetriableStream.this.state.substreamClosed(this.substream);
            }
            if (this.substream.bufferLimitExceeded) {
                RetriableStream.this.commitAndRun(this.substream);
                if (((RetriableStream)RetriableStream.this).state.winningSubstream == this.substream) {
                    RetriableStream.this.masterListener.closed(status, trailers);
                }
                return;
            }
            if (((RetriableStream)RetriableStream.this).state.winningSubstream == null) {
                if (rpcProgress == ClientStreamListener.RpcProgress.REFUSED && !RetriableStream.this.noMoreTransparentRetry) {
                    RetriableStream.this.noMoreTransparentRetry = true;
                    RetriableStream.this.callExecutor.execute(new Runnable(){

                        @Override
                        public void run() {
                            Substream newSubstream = RetriableStream.this.createSubstream(Sublistener.this.substream.previousAttempts);
                            RetriableStream.this.drain(newSubstream);
                        }
                    });
                    return;
                }
                if (rpcProgress != ClientStreamListener.RpcProgress.DROPPED) {
                    RetriableStream.this.noMoreTransparentRetry = true;
                    if (RetriableStream.this.retryPolicy == null) {
                        RetriableStream.this.retryPolicy = RetriableStream.this.retryPolicyProvider.get();
                        RetriableStream.this.nextBackoffIntervalNanos = ((RetriableStream)RetriableStream.this).retryPolicy.initialBackoffNanos;
                    }
                    RetryPlan retryPlan = this.makeRetryDecision(RetriableStream.this.retryPolicy, status, trailers);
                    if (retryPlan.shouldRetry) {
                        RetriableStream.this.scheduledRetry = RetriableStream.this.scheduledExecutorService.schedule(new Runnable(){

                            @Override
                            public void run() {
                                RetriableStream.this.scheduledRetry = null;
                                RetriableStream.this.callExecutor.execute(new Runnable(){

                                    @Override
                                    public void run() {
                                        Substream newSubstream = RetriableStream.this.createSubstream(Sublistener.this.substream.previousAttempts + 1);
                                        RetriableStream.this.drain(newSubstream);
                                    }
                                });
                            }
                        }, retryPlan.backoffNanos, TimeUnit.NANOSECONDS);
                        return;
                    }
                }
            }
            if (!RetriableStream.this.hasHedging()) {
                RetriableStream.this.commitAndRun(this.substream);
                if (((RetriableStream)RetriableStream.this).state.winningSubstream == this.substream) {
                    RetriableStream.this.masterListener.closed(status, trailers);
                }
            }
        }

        private RetryPlan makeRetryDecision(RetryPolicy retryPolicy, Status status, Metadata trailer) {
            boolean shouldRetry = false;
            long backoffNanos = 0L;
            boolean isRetryableStatusCode = retryPolicy.retryableStatusCodes.contains((Object)status.getCode());
            String pushbackStr = trailer.get(GRPC_RETRY_PUSHBACK_MS);
            Integer pushbackMillis = null;
            if (pushbackStr != null) {
                try {
                    pushbackMillis = Integer.valueOf(pushbackStr);
                }
                catch (NumberFormatException e) {
                    pushbackMillis = -1;
                }
            }
            boolean isThrottled = false;
            if (RetriableStream.this.throttle != null && (isRetryableStatusCode || pushbackMillis != null && pushbackMillis < 0)) {
                boolean bl = isThrottled = !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            }
            if (retryPolicy.maxAttempts > this.substream.previousAttempts + 1 && !isThrottled) {
                if (pushbackMillis == null) {
                    if (isRetryableStatusCode) {
                        shouldRetry = true;
                        backoffNanos = (long)((double)RetriableStream.this.nextBackoffIntervalNanos * random.nextDouble());
                        RetriableStream.this.nextBackoffIntervalNanos = Math.min((long)((double)RetriableStream.this.nextBackoffIntervalNanos * retryPolicy.backoffMultiplier), retryPolicy.maxBackoffNanos);
                    }
                } else if (pushbackMillis >= 0) {
                    shouldRetry = true;
                    backoffNanos = TimeUnit.MILLISECONDS.toNanos(pushbackMillis.intValue());
                    RetriableStream.this.nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
                }
            }
            return new RetryPlan(shouldRetry, backoffNanos);
        }

        @Override
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            State savedState = RetriableStream.this.state;
            Preconditions.checkState(savedState.winningSubstream != null, "Headers should be received prior to messages.");
            if (savedState.winningSubstream != this.substream) {
                return;
            }
            RetriableStream.this.masterListener.messagesAvailable(producer);
        }

        @Override
        public void onReady() {
            if (((RetriableStream)RetriableStream.this).state.drainedSubstreams.contains(this.substream)) {
                RetriableStream.this.masterListener.onReady();
            }
        }
    }

    private static interface BufferEntry {
        public void runWith(Substream var1);
    }
}

