/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.Deframer;
import io.grpc.internal.Framer;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.GzipInflatingBuffer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.ReadableBuffer;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.Stream;
import io.grpc.internal.StreamListener;
import io.grpc.internal.TransportTracer;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractStream
implements Stream {
    protected abstract Framer framer();

    protected abstract TransportState transportState();

    @Override
    public final void setMessageCompression(boolean enable) {
        this.framer().setMessageCompression(enable);
    }

    @Override
    public final void writeMessage(InputStream message) {
        Preconditions.checkNotNull(message, "message");
        try {
            if (!this.framer().isClosed()) {
                this.framer().writePayload(message);
            }
        }
        finally {
            GrpcUtil.closeQuietly(message);
        }
    }

    @Override
    public final void flush() {
        if (!this.framer().isClosed()) {
            this.framer().flush();
        }
    }

    protected final void endOfMessages() {
        this.framer().close();
    }

    @Override
    public final void setCompressor(Compressor compressor) {
        this.framer().setCompressor(Preconditions.checkNotNull(compressor, "compressor"));
    }

    @Override
    public boolean isReady() {
        if (this.framer().isClosed()) {
            return false;
        }
        return this.transportState().isReady();
    }

    protected final void onSendingBytes(int numBytes) {
        this.transportState().onSendingBytes(numBytes);
    }

    public static abstract class TransportState
    implements ApplicationThreadDeframer.TransportExecutor,
    MessageDeframer.Listener {
        @VisibleForTesting
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private Deframer deframer;
        private final Object onReadyLock = new Object();
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;
        @GuardedBy(value="onReadyLock")
        private int numSentBytesQueued;
        @GuardedBy(value="onReadyLock")
        private boolean allocated;
        @GuardedBy(value="onReadyLock")
        private boolean deallocated;

        protected TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx, TransportTracer transportTracer) {
            this.statsTraceCtx = Preconditions.checkNotNull(statsTraceCtx, "statsTraceCtx");
            this.transportTracer = Preconditions.checkNotNull(transportTracer, "transportTracer");
            this.deframer = new MessageDeframer(this, Codec.Identity.NONE, maxMessageSize, statsTraceCtx, transportTracer);
        }

        protected void setFullStreamDecompressor(GzipInflatingBuffer fullStreamDecompressor) {
            this.deframer.setFullStreamDecompressor(fullStreamDecompressor);
            this.deframer = new ApplicationThreadDeframer(this, this, (MessageDeframer)this.deframer);
        }

        final void setMaxInboundMessageSize(int maxSize) {
            this.deframer.setMaxInboundMessageSize(maxSize);
        }

        protected abstract StreamListener listener();

        @Override
        public void messagesAvailable(StreamListener.MessageProducer producer) {
            this.listener().messagesAvailable(producer);
        }

        protected final void closeDeframer(boolean stopDelivery) {
            if (stopDelivery) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        protected final void deframe(ReadableBuffer frame) {
            try {
                this.deframer.deframe(frame);
            }
            catch (Throwable t) {
                this.deframeFailed(t);
            }
        }

        public final void requestMessagesFromDeframer(int numMessages) {
            try {
                this.deframer.request(numMessages);
            }
            catch (Throwable t) {
                this.deframeFailed(t);
            }
        }

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        protected final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private boolean isReady() {
            Object object = this.onReadyLock;
            synchronized (object) {
                return this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        protected void onStreamAllocated() {
            Preconditions.checkState(this.listener() != null);
            Object object = this.onReadyLock;
            synchronized (object) {
                Preconditions.checkState(!this.allocated, "Already allocated");
                this.allocated = true;
            }
            this.notifyIfReady();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        protected final void onStreamDeallocated() {
            Object object = this.onReadyLock;
            synchronized (object) {
                this.deallocated = true;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void onSendingBytes(int numBytes) {
            Object object = this.onReadyLock;
            synchronized (object) {
                this.numSentBytesQueued += numBytes;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public final void onSentBytes(int numBytes) {
            boolean doNotify;
            Object object = this.onReadyLock;
            synchronized (object) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                boolean belowThresholdBefore = this.numSentBytesQueued < 32768;
                this.numSentBytesQueued -= numBytes;
                boolean belowThresholdAfter = this.numSentBytesQueued < 32768;
                doNotify = !belowThresholdBefore && belowThresholdAfter;
            }
            if (doNotify) {
                this.notifyIfReady();
            }
        }

        protected TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void notifyIfReady() {
            boolean doNotify;
            Object object = this.onReadyLock;
            synchronized (object) {
                doNotify = this.isReady();
            }
            if (doNotify) {
                this.listener().onReady();
            }
        }
    }
}

