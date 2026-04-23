/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.BinaryLog;
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
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.BinaryFormat;
import io.opencensus.trace.unsafe.ContextUtils;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

final class CensusTracingModule {
    private static final Logger logger;
    @Nullable
    private static final AtomicIntegerFieldUpdater<ClientCallTracer> callEndedUpdater;
    @Nullable
    private static final AtomicIntegerFieldUpdater<ServerTracer> streamClosedUpdater;
    private final Tracer censusTracer;
    @VisibleForTesting
    final Metadata.Key<SpanContext> tracingHeader;
    private final TracingClientInterceptor clientInterceptor = new TracingClientInterceptor();
    private final ServerTracerFactory serverTracerFactory = new ServerTracerFactory();

    CensusTracingModule(Tracer censusTracer, final BinaryFormat censusPropagationBinaryFormat) {
        this.censusTracer = Preconditions.checkNotNull(censusTracer, "censusTracer");
        Preconditions.checkNotNull(censusPropagationBinaryFormat, "censusPropagationBinaryFormat");
        this.tracingHeader = Metadata.Key.of("grpc-trace-bin", new Metadata.BinaryMarshaller<SpanContext>(){

            @Override
            public byte[] toBytes(SpanContext context) {
                return censusPropagationBinaryFormat.toByteArray(context);
            }

            @Override
            public SpanContext parseBytes(byte[] serialized) {
                try {
                    return censusPropagationBinaryFormat.fromByteArray(serialized);
                }
                catch (Exception e) {
                    logger.log(Level.FINE, "Failed to parse tracing header", e);
                    return SpanContext.INVALID;
                }
            }
        });
    }

    @VisibleForTesting
    ClientCallTracer newClientCallTracer(@Nullable Span parentSpan, MethodDescriptor<?, ?> method) {
        return new ClientCallTracer(parentSpan, method);
    }

    ServerStreamTracer.Factory getServerTracerFactory() {
        return this.serverTracerFactory;
    }

    ClientInterceptor getClientInterceptor() {
        return this.clientInterceptor;
    }

    @VisibleForTesting
    static io.opencensus.trace.Status convertStatus(Status grpcStatus) {
        io.opencensus.trace.Status status;
        switch (grpcStatus.getCode()) {
            case OK: {
                status = io.opencensus.trace.Status.OK;
                break;
            }
            case CANCELLED: {
                status = io.opencensus.trace.Status.CANCELLED;
                break;
            }
            case UNKNOWN: {
                status = io.opencensus.trace.Status.UNKNOWN;
                break;
            }
            case INVALID_ARGUMENT: {
                status = io.opencensus.trace.Status.INVALID_ARGUMENT;
                break;
            }
            case DEADLINE_EXCEEDED: {
                status = io.opencensus.trace.Status.DEADLINE_EXCEEDED;
                break;
            }
            case NOT_FOUND: {
                status = io.opencensus.trace.Status.NOT_FOUND;
                break;
            }
            case ALREADY_EXISTS: {
                status = io.opencensus.trace.Status.ALREADY_EXISTS;
                break;
            }
            case PERMISSION_DENIED: {
                status = io.opencensus.trace.Status.PERMISSION_DENIED;
                break;
            }
            case RESOURCE_EXHAUSTED: {
                status = io.opencensus.trace.Status.RESOURCE_EXHAUSTED;
                break;
            }
            case FAILED_PRECONDITION: {
                status = io.opencensus.trace.Status.FAILED_PRECONDITION;
                break;
            }
            case ABORTED: {
                status = io.opencensus.trace.Status.ABORTED;
                break;
            }
            case OUT_OF_RANGE: {
                status = io.opencensus.trace.Status.OUT_OF_RANGE;
                break;
            }
            case UNIMPLEMENTED: {
                status = io.opencensus.trace.Status.UNIMPLEMENTED;
                break;
            }
            case INTERNAL: {
                status = io.opencensus.trace.Status.INTERNAL;
                break;
            }
            case UNAVAILABLE: {
                status = io.opencensus.trace.Status.UNAVAILABLE;
                break;
            }
            case DATA_LOSS: {
                status = io.opencensus.trace.Status.DATA_LOSS;
                break;
            }
            case UNAUTHENTICATED: {
                status = io.opencensus.trace.Status.UNAUTHENTICATED;
                break;
            }
            default: {
                throw new AssertionError((Object)("Unhandled status code " + (Object)((Object)grpcStatus.getCode())));
            }
        }
        if (grpcStatus.getDescription() != null) {
            status = status.withDescription(grpcStatus.getDescription());
        }
        return status;
    }

    private static EndSpanOptions createEndSpanOptions(Status status, boolean sampledToLocalTracing) {
        return EndSpanOptions.builder().setStatus(CensusTracingModule.convertStatus(status)).setSampleToLocalSpanStore(sampledToLocalTracing).build();
    }

    private static void recordMessageEvent(Span span2, MessageEvent.Type type, int seqNo, long optionalWireSize, long optionalUncompressedSize) {
        MessageEvent.Builder eventBuilder = MessageEvent.builder(type, seqNo);
        if (optionalUncompressedSize != -1L) {
            eventBuilder.setUncompressedMessageSize(optionalUncompressedSize);
        }
        if (optionalWireSize != -1L) {
            eventBuilder.setCompressedMessageSize(optionalWireSize);
        }
        span2.addMessageEvent(eventBuilder.build());
    }

    @VisibleForTesting
    static String generateTraceSpanName(boolean isServer, String fullMethodName) {
        String prefix = isServer ? "Recv" : "Sent";
        return prefix + "." + fullMethodName.replace('/', '.');
    }

    static {
        AtomicIntegerFieldUpdater<ServerTracer> tmpStreamClosedUpdater;
        AtomicIntegerFieldUpdater<ClientCallTracer> tmpCallEndedUpdater;
        logger = Logger.getLogger(CensusTracingModule.class.getName());
        try {
            tmpCallEndedUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientCallTracer.class, "callEnded");
            tmpStreamClosedUpdater = AtomicIntegerFieldUpdater.newUpdater(ServerTracer.class, "streamClosed");
        }
        catch (Throwable t) {
            logger.log(Level.SEVERE, "Creating atomic field updaters failed", t);
            tmpCallEndedUpdater = null;
            tmpStreamClosedUpdater = null;
        }
        callEndedUpdater = tmpCallEndedUpdater;
        streamClosedUpdater = tmpStreamClosedUpdater;
    }

    @VisibleForTesting
    final class TracingClientInterceptor
    implements ClientInterceptor {
        TracingClientInterceptor() {
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next2) {
            final ClientCallTracer tracerFactory = CensusTracingModule.this.newClientCallTracer(ContextUtils.CONTEXT_SPAN_KEY.get(), method);
            ClientCall<ReqT, RespT> call = next2.newCall(method, callOptions.withStreamTracerFactory(tracerFactory).withOption(BinaryLog.CLIENT_CALL_ID_CALLOPTION_KEY, new BinaryLog.CallId(0L, ByteBuffer.wrap(tracerFactory.span.getContext().getSpanId().getBytes()).getLong())));
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
        ServerTracerFactory() {
        }

        @Override
        public ServerStreamTracer newServerStreamTracer(String fullMethodName, Metadata headers) {
            SpanContext remoteSpan = headers.get(CensusTracingModule.this.tracingHeader);
            if (remoteSpan == SpanContext.INVALID) {
                remoteSpan = null;
            }
            return new ServerTracer(fullMethodName, remoteSpan);
        }
    }

    private final class ServerTracer
    extends ServerStreamTracer {
        private final Span span;
        volatile boolean isSampledToLocalTracing;
        volatile int streamClosed;

        ServerTracer(@Nullable String fullMethodName, SpanContext remoteSpan) {
            Preconditions.checkNotNull(fullMethodName, "fullMethodName");
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithRemoteParent(CensusTracingModule.generateTraceSpanName(true, fullMethodName), remoteSpan).setRecordEvents(true).startSpan();
        }

        @Override
        public void serverCallStarted(ServerStreamTracer.ServerCallInfo<?, ?> callInfo) {
            this.isSampledToLocalTracing = callInfo.getMethodDescriptor().isSampledToLocalTracing();
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
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }

        @Override
        public Context filterContext(Context context) {
            return context.withValue(ContextUtils.CONTEXT_SPAN_KEY, this.span);
        }

        @Override
        public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.SENT, seqNo, optionalWireSize, optionalUncompressedSize);
        }

        @Override
        public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.RECEIVED, seqNo, optionalWireSize, optionalUncompressedSize);
        }
    }

    private static final class ClientTracer
    extends ClientStreamTracer {
        private final Span span;

        ClientTracer(Span span2) {
            this.span = Preconditions.checkNotNull(span2, "span");
        }

        @Override
        public void outboundMessageSent(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.SENT, seqNo, optionalWireSize, optionalUncompressedSize);
        }

        @Override
        public void inboundMessageRead(int seqNo, long optionalWireSize, long optionalUncompressedSize) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.RECEIVED, seqNo, optionalWireSize, optionalUncompressedSize);
        }
    }

    @VisibleForTesting
    final class ClientCallTracer
    extends ClientStreamTracer.Factory {
        volatile int callEnded;
        private final boolean isSampledToLocalTracing;
        private final Span span;

        ClientCallTracer(Span parentSpan, MethodDescriptor<?, ?> method) {
            Preconditions.checkNotNull(method, "method");
            this.isSampledToLocalTracing = method.isSampledToLocalTracing();
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithExplicitParent(CensusTracingModule.generateTraceSpanName(false, method.getFullMethodName()), parentSpan).setRecordEvents(true).startSpan();
        }

        @Override
        public ClientStreamTracer newClientStreamTracer(CallOptions callOptions, Metadata headers) {
            headers.discardAll(CensusTracingModule.this.tracingHeader);
            headers.put(CensusTracingModule.this.tracingHeader, this.span.getContext());
            return new ClientTracer(this.span);
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
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }
    }
}

