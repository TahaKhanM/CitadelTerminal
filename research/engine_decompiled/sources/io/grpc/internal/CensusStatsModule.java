/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.opencensus.contrib.grpc.metrics.RpcMeasureConstants;
import io.opencensus.stats.MeasureMap;
import io.opencensus.stats.Stats;
import io.opencensus.stats.StatsRecorder;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagValue;
import io.opencensus.tags.Tagger;
import io.opencensus.tags.Tags;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextSerializationException;
import io.opencensus.tags.unsafe.ContextUtils;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class CensusStatsModule {
    private static final Logger logger = Logger.getLogger(CensusStatsModule.class.getName());
    private static final double NANOS_PER_MILLI = TimeUnit.MILLISECONDS.toNanos(1L);
    private static final ClientTracer BLANK_CLIENT_TRACER = new ClientTracer();
    private final Tagger tagger;
    private final StatsRecorder statsRecorder;
    private final Supplier<Stopwatch> stopwatchSupplier;
    @VisibleForTesting
    final Metadata.Key<TagContext> statsHeader;
    private final boolean propagateTags;

    CensusStatsModule(Supplier<Stopwatch> stopwatchSupplier, boolean propagateTags) {
        this(Tags.getTagger(), Tags.getTagPropagationComponent().getBinarySerializer(), Stats.getStatsRecorder(), stopwatchSupplier, propagateTags);
    }

    public CensusStatsModule(final Tagger tagger, final TagContextBinarySerializer tagCtxSerializer, StatsRecorder statsRecorder, Supplier<Stopwatch> stopwatchSupplier, boolean propagateTags) {
        this.tagger = Preconditions.checkNotNull(tagger, "tagger");
        this.statsRecorder = Preconditions.checkNotNull(statsRecorder, "statsRecorder");
        Preconditions.checkNotNull(tagCtxSerializer, "tagCtxSerializer");
        this.stopwatchSupplier = Preconditions.checkNotNull(stopwatchSupplier, "stopwatchSupplier");
        this.propagateTags = propagateTags;
        this.statsHeader = Metadata.Key.of("grpc-tags-bin", new Metadata.BinaryMarshaller<TagContext>(){

            @Override
            public byte[] toBytes(TagContext context) {
                try {
                    return tagCtxSerializer.toByteArray(context);
                }
                catch (TagContextSerializationException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public TagContext parseBytes(byte[] serialized) {
                try {
                    return tagCtxSerializer.fromByteArray(serialized);
                }
                catch (Exception e) {
                    logger.log(Level.FINE, "Failed to parse stats header", e);
                    return tagger.empty();
                }
            }
        });
    }

    @VisibleForTesting
    ClientCallTracer newClientCallTracer(TagContext parentCtx, String fullMethodName, boolean recordStartedRpcs, boolean recordFinishedRpcs) {
        return new ClientCallTracer(this, parentCtx, fullMethodName, recordStartedRpcs, recordFinishedRpcs);
    }

    ServerStreamTracer.Factory getServerTracerFactory(boolean recordStartedRpcs, boolean recordFinishedRpcs) {
        return new ServerTracerFactory(recordStartedRpcs, recordFinishedRpcs);
    }

    ClientInterceptor getClientInterceptor(boolean recordStartedRpcs, boolean recordFinishedRpcs) {
        return new StatsClientInterceptor(recordStartedRpcs, recordFinishedRpcs);
    }

    @VisibleForTesting
    final class StatsClientInterceptor
    implements ClientInterceptor {
        private final boolean recordStartedRpcs;
        private final boolean recordFinishedRpcs;

        StatsClientInterceptor(boolean recordStartedRpcs, boolean recordFinishedRpcs) {
            this.recordStartedRpcs = recordStartedRpcs;
            this.recordFinishedRpcs = recordFinishedRpcs;
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
            TagContext parentCtx = CensusStatsModule.this.tagger.getCurrentTagContext();
            final ClientCallTracer tracerFactory = CensusStatsModule.this.newClientCallTracer(parentCtx, method.getFullMethodName(), this.recordStartedRpcs, this.recordFinishedRpcs);
            ClientCall<ReqT, RespT> call = next2.newCall(method, callOptions.withStreamTracerFactory(tracerFactory));
            return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(call){

                @Override
                public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
                    this.delegate().start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener){

                        @Override
                        public void onClose(Status status, Metadata trailers) {
                            tracerFactory.callEnded(status);
                            super.onClose(status, trailers);
                        }
                    }, headers);
                }
            };
        }
    }

    @VisibleForTesting
    final class ServerTracerFactory
    extends ServerStreamTracer.Factory {
        private final boolean recordStartedRpcs;
        private final boolean recordFinishedRpcs;

        ServerTracerFactory(boolean recordStartedRpcs, boolean recordFinishedRpcs) {
            this.recordStartedRpcs = recordStartedRpcs;
            this.recordFinishedRpcs = recordFinishedRpcs;
        }

        @Override
        public ServerStreamTracer newServerStreamTracer(String fullMethodName, Metadata headers) {
            TagContext parentCtx = headers.get(CensusStatsModule.this.statsHeader);
            if (parentCtx == null) {
                parentCtx = CensusStatsModule.this.tagger.empty();
            }
            parentCtx = CensusStatsModule.this.tagger.toBuilder(parentCtx).put(RpcMeasureConstants.RPC_METHOD, TagValue.create(fullMethodName)).build();
            return new ServerTracer(CensusStatsModule.this, fullMethodName, parentCtx, CensusStatsModule.this.stopwatchSupplier, CensusStatsModule.this.tagger, this.recordStartedRpcs, this.recordFinishedRpcs);
        }
    }

    private static final class ServerTracer
    extends ServerStreamTracer {
        @Nullable
        private static final AtomicIntegerFieldUpdater<ServerTracer> streamClosedUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> outboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> inboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> outboundWireSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> inboundWireSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> outboundUncompressedSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ServerTracer> inboundUncompressedSizeUpdater;
        private final CensusStatsModule module;
        private final String fullMethodName;
        private final TagContext parentCtx;
        private volatile int streamClosed;
        private final Stopwatch stopwatch;
        private final Tagger tagger;
        private final boolean recordFinishedRpcs;
        private volatile long outboundMessageCount;
        private volatile long inboundMessageCount;
        private volatile long outboundWireSize;
        private volatile long inboundWireSize;
        private volatile long outboundUncompressedSize;
        private volatile long inboundUncompressedSize;

        ServerTracer(CensusStatsModule module, String fullMethodName, TagContext parentCtx, Supplier<Stopwatch> stopwatchSupplier, Tagger tagger, boolean recordStartedRpcs, boolean recordFinishedRpcs) {
            this.module = module;
            this.fullMethodName = Preconditions.checkNotNull(fullMethodName, "fullMethodName");
            this.parentCtx = Preconditions.checkNotNull(parentCtx, "parentCtx");
            this.stopwatch = stopwatchSupplier.get().start();
            this.tagger = tagger;
            this.recordFinishedRpcs = recordFinishedRpcs;
            if (recordStartedRpcs) {
                module.statsRecorder.newMeasureMap().put(RpcMeasureConstants.RPC_SERVER_STARTED_COUNT, 1L).record(parentCtx);
            }
        }

        @Override
        public void outboundWireSize(long bytes2) {
            if (outboundWireSizeUpdater != null) {
                outboundWireSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.outboundWireSize += bytes2;
            }
        }

        @Override
        public void inboundWireSize(long bytes2) {
            if (inboundWireSizeUpdater != null) {
                inboundWireSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.inboundWireSize += bytes2;
            }
        }

        @Override
        public void outboundUncompressedSize(long bytes2) {
            if (outboundUncompressedSizeUpdater != null) {
                outboundUncompressedSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.outboundUncompressedSize += bytes2;
            }
        }

        @Override
        public void inboundUncompressedSize(long bytes2) {
            if (inboundUncompressedSizeUpdater != null) {
                inboundUncompressedSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.inboundUncompressedSize += bytes2;
            }
        }

        @Override
        public void inboundMessage(int seqNo) {
            if (inboundMessageCountUpdater != null) {
                inboundMessageCountUpdater.getAndIncrement(this);
            } else {
                ++this.inboundMessageCount;
            }
        }

        @Override
        public void outboundMessage(int seqNo) {
            if (outboundMessageCountUpdater != null) {
                outboundMessageCountUpdater.getAndIncrement(this);
            } else {
                ++this.outboundMessageCount;
            }
        }

        @Override
        public void streamClosed(Status status) {
            if (streamClosedUpdater != null) {
                if (streamClosedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else {
                if (this.streamClosed != 0) {
                    return;
                }
                this.streamClosed = 1;
            }
            if (!this.recordFinishedRpcs) {
                return;
            }
            this.stopwatch.stop();
            long elapsedTimeNanos = this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
            MeasureMap measureMap = this.module.statsRecorder.newMeasureMap().put(RpcMeasureConstants.RPC_SERVER_FINISHED_COUNT, 1L).put(RpcMeasureConstants.RPC_SERVER_SERVER_LATENCY, (double)elapsedTimeNanos / NANOS_PER_MILLI).put(RpcMeasureConstants.RPC_SERVER_RESPONSE_COUNT, this.outboundMessageCount).put(RpcMeasureConstants.RPC_SERVER_REQUEST_COUNT, this.inboundMessageCount).put(RpcMeasureConstants.RPC_SERVER_RESPONSE_BYTES, (double)this.outboundWireSize).put(RpcMeasureConstants.RPC_SERVER_REQUEST_BYTES, (double)this.inboundWireSize).put(RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES, (double)this.outboundUncompressedSize).put(RpcMeasureConstants.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES, (double)this.inboundUncompressedSize);
            if (!status.isOk()) {
                measureMap.put(RpcMeasureConstants.RPC_SERVER_ERROR_COUNT, 1L);
            }
            measureMap.record(this.module.tagger.toBuilder(this.parentCtx).put(RpcMeasureConstants.RPC_STATUS, TagValue.create(status.getCode().toString())).build());
        }

        @Override
        public Context filterContext(Context context) {
            if (!this.tagger.empty().equals(this.parentCtx)) {
                return context.withValue(ContextUtils.TAG_CONTEXT_KEY, this.parentCtx);
            }
            return context;
        }

        static {
            AtomicLongFieldUpdater<ServerTracer> tmpInboundUncompressedSizeUpdater;
            AtomicLongFieldUpdater<ServerTracer> tmpOutboundUncompressedSizeUpdater;
            AtomicLongFieldUpdater<ServerTracer> tmpInboundWireSizeUpdater;
            AtomicLongFieldUpdater<ServerTracer> tmpOutboundWireSizeUpdater;
            AtomicLongFieldUpdater<ServerTracer> tmpInboundMessageCountUpdater;
            AtomicLongFieldUpdater<ServerTracer> tmpOutboundMessageCountUpdater;
            AtomicIntegerFieldUpdater<ServerTracer> tmpStreamClosedUpdater;
            try {
                tmpStreamClosedUpdater = AtomicIntegerFieldUpdater.newUpdater(ServerTracer.class, "streamClosed");
                tmpOutboundMessageCountUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "outboundMessageCount");
                tmpInboundMessageCountUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "inboundMessageCount");
                tmpOutboundWireSizeUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "outboundWireSize");
                tmpInboundWireSizeUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "inboundWireSize");
                tmpOutboundUncompressedSizeUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "outboundUncompressedSize");
                tmpInboundUncompressedSizeUpdater = AtomicLongFieldUpdater.newUpdater(ServerTracer.class, "inboundUncompressedSize");
            }
            catch (Throwable t) {
                logger.log(Level.SEVERE, "Creating atomic field updaters failed", t);
                tmpStreamClosedUpdater = null;
                tmpOutboundMessageCountUpdater = null;
                tmpInboundMessageCountUpdater = null;
                tmpOutboundWireSizeUpdater = null;
                tmpInboundWireSizeUpdater = null;
                tmpOutboundUncompressedSizeUpdater = null;
                tmpInboundUncompressedSizeUpdater = null;
            }
            streamClosedUpdater = tmpStreamClosedUpdater;
            outboundMessageCountUpdater = tmpOutboundMessageCountUpdater;
            inboundMessageCountUpdater = tmpInboundMessageCountUpdater;
            outboundWireSizeUpdater = tmpOutboundWireSizeUpdater;
            inboundWireSizeUpdater = tmpInboundWireSizeUpdater;
            outboundUncompressedSizeUpdater = tmpOutboundUncompressedSizeUpdater;
            inboundUncompressedSizeUpdater = tmpInboundUncompressedSizeUpdater;
        }
    }

    @VisibleForTesting
    static final class ClientCallTracer
    extends ClientStreamTracer.Factory {
        @Nullable
        private static final AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> streamTracerUpdater;
        @Nullable
        private static final AtomicIntegerFieldUpdater<ClientCallTracer> callEndedUpdater;
        private final CensusStatsModule module;
        private final String fullMethodName;
        private final Stopwatch stopwatch;
        private volatile ClientTracer streamTracer;
        private volatile int callEnded;
        private final TagContext parentCtx;
        private final TagContext startCtx;
        private final boolean recordFinishedRpcs;

        ClientCallTracer(CensusStatsModule module, TagContext parentCtx, String fullMethodName, boolean recordStartedRpcs, boolean recordFinishedRpcs) {
            this.module = module;
            this.fullMethodName = Preconditions.checkNotNull(fullMethodName, "fullMethodName");
            this.parentCtx = Preconditions.checkNotNull(parentCtx);
            this.startCtx = module.tagger.toBuilder(parentCtx).put(RpcMeasureConstants.RPC_METHOD, TagValue.create(fullMethodName)).build();
            this.stopwatch = ((Stopwatch)module.stopwatchSupplier.get()).start();
            this.recordFinishedRpcs = recordFinishedRpcs;
            if (recordStartedRpcs) {
                module.statsRecorder.newMeasureMap().put(RpcMeasureConstants.RPC_CLIENT_STARTED_COUNT, 1L).record(this.startCtx);
            }
        }

        @Override
        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata headers) {
            ClientTracer tracer = new ClientTracer();
            if (streamTracerUpdater != null) {
                Preconditions.checkState(streamTracerUpdater.compareAndSet(this, null, tracer), "Are you creating multiple streams per call? This class doesn't yet support this case");
            } else {
                Preconditions.checkState(this.streamTracer == null, "Are you creating multiple streams per call? This class doesn't yet support this case");
                this.streamTracer = tracer;
            }
            if (this.module.propagateTags) {
                headers.discardAll(this.module.statsHeader);
                if (!this.module.tagger.empty().equals(this.parentCtx)) {
                    headers.put(this.module.statsHeader, this.parentCtx);
                }
            }
            return tracer;
        }

        void callEnded(Status status) {
            if (callEndedUpdater != null) {
                if (callEndedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else {
                if (this.callEnded != 0) {
                    return;
                }
                this.callEnded = 1;
            }
            if (!this.recordFinishedRpcs) {
                return;
            }
            this.stopwatch.stop();
            long roundtripNanos = this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
            ClientTracer tracer = this.streamTracer;
            if (tracer == null) {
                tracer = BLANK_CLIENT_TRACER;
            }
            MeasureMap measureMap = this.module.statsRecorder.newMeasureMap().put(RpcMeasureConstants.RPC_CLIENT_FINISHED_COUNT, 1L).put(RpcMeasureConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, (double)roundtripNanos / NANOS_PER_MILLI).put(RpcMeasureConstants.RPC_CLIENT_REQUEST_COUNT, tracer.outboundMessageCount).put(RpcMeasureConstants.RPC_CLIENT_RESPONSE_COUNT, tracer.inboundMessageCount).put(RpcMeasureConstants.RPC_CLIENT_REQUEST_BYTES, (double)tracer.outboundWireSize).put(RpcMeasureConstants.RPC_CLIENT_RESPONSE_BYTES, (double)tracer.inboundWireSize).put(RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES, (double)tracer.outboundUncompressedSize).put(RpcMeasureConstants.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES, (double)tracer.inboundUncompressedSize);
            if (!status.isOk()) {
                measureMap.put(RpcMeasureConstants.RPC_CLIENT_ERROR_COUNT, 1L);
            }
            measureMap.record(this.module.tagger.toBuilder(this.startCtx).put(RpcMeasureConstants.RPC_STATUS, TagValue.create(status.getCode().toString())).build());
        }

        static {
            AtomicIntegerFieldUpdater<ClientCallTracer> tmpCallEndedUpdater;
            AtomicReferenceFieldUpdater<ClientCallTracer, ClientTracer> tmpStreamTracerUpdater;
            try {
                tmpStreamTracerUpdater = AtomicReferenceFieldUpdater.newUpdater(ClientCallTracer.class, ClientTracer.class, "streamTracer");
                tmpCallEndedUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientCallTracer.class, "callEnded");
            }
            catch (Throwable t) {
                logger.log(Level.SEVERE, "Creating atomic field updaters failed", t);
                tmpStreamTracerUpdater = null;
                tmpCallEndedUpdater = null;
            }
            streamTracerUpdater = tmpStreamTracerUpdater;
            callEndedUpdater = tmpCallEndedUpdater;
        }
    }

    private static final class ClientTracer
    extends ClientStreamTracer {
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundMessageCountUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundWireSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundWireSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> outboundUncompressedSizeUpdater;
        @Nullable
        private static final AtomicLongFieldUpdater<ClientTracer> inboundUncompressedSizeUpdater;
        volatile long outboundMessageCount;
        volatile long inboundMessageCount;
        volatile long outboundWireSize;
        volatile long inboundWireSize;
        volatile long outboundUncompressedSize;
        volatile long inboundUncompressedSize;

        private ClientTracer() {
        }

        @Override
        public void outboundWireSize(long bytes2) {
            if (outboundWireSizeUpdater != null) {
                outboundWireSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.outboundWireSize += bytes2;
            }
        }

        @Override
        public void inboundWireSize(long bytes2) {
            if (inboundWireSizeUpdater != null) {
                inboundWireSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.inboundWireSize += bytes2;
            }
        }

        @Override
        public void outboundUncompressedSize(long bytes2) {
            if (outboundUncompressedSizeUpdater != null) {
                outboundUncompressedSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.outboundUncompressedSize += bytes2;
            }
        }

        @Override
        public void inboundUncompressedSize(long bytes2) {
            if (inboundUncompressedSizeUpdater != null) {
                inboundUncompressedSizeUpdater.getAndAdd(this, bytes2);
            } else {
                this.inboundUncompressedSize += bytes2;
            }
        }

        @Override
        public void inboundMessage(int seqNo) {
            if (inboundMessageCountUpdater != null) {
                inboundMessageCountUpdater.getAndIncrement(this);
            } else {
                ++this.inboundMessageCount;
            }
        }

        @Override
        public void outboundMessage(int seqNo) {
            if (outboundMessageCountUpdater != null) {
                outboundMessageCountUpdater.getAndIncrement(this);
            } else {
                ++this.outboundMessageCount;
            }
        }

        static {
            AtomicLongFieldUpdater<ClientTracer> tmpInboundUncompressedSizeUpdater;
            AtomicLongFieldUpdater<ClientTracer> tmpOutboundUncompressedSizeUpdater;
            AtomicLongFieldUpdater<ClientTracer> tmpInboundWireSizeUpdater;
            AtomicLongFieldUpdater<ClientTracer> tmpOutboundWireSizeUpdater;
            AtomicLongFieldUpdater<ClientTracer> tmpInboundMessageCountUpdater;
            AtomicLongFieldUpdater<ClientTracer> tmpOutboundMessageCountUpdater;
            try {
                tmpOutboundMessageCountUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundMessageCount");
                tmpInboundMessageCountUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundMessageCount");
                tmpOutboundWireSizeUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundWireSize");
                tmpInboundWireSizeUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundWireSize");
                tmpOutboundUncompressedSizeUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "outboundUncompressedSize");
                tmpInboundUncompressedSizeUpdater = AtomicLongFieldUpdater.newUpdater(ClientTracer.class, "inboundUncompressedSize");
            }
            catch (Throwable t) {
                logger.log(Level.SEVERE, "Creating atomic field updaters failed", t);
                tmpOutboundMessageCountUpdater = null;
                tmpInboundMessageCountUpdater = null;
                tmpOutboundWireSizeUpdater = null;
                tmpInboundWireSizeUpdater = null;
                tmpOutboundUncompressedSizeUpdater = null;
                tmpInboundUncompressedSizeUpdater = null;
            }
            outboundMessageCountUpdater = tmpOutboundMessageCountUpdater;
            inboundMessageCountUpdater = tmpInboundMessageCountUpdater;
            outboundWireSizeUpdater = tmpOutboundWireSizeUpdater;
            inboundWireSizeUpdater = tmpInboundWireSizeUpdater;
            outboundUncompressedSizeUpdater = tmpOutboundUncompressedSizeUpdater;
            inboundUncompressedSizeUpdater = tmpInboundUncompressedSizeUpdater;
        }
    }
}

