/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2CodecUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Settings;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import java.util.concurrent.TimeUnit;

abstract class AbstractNettyHandler
extends GrpcHttp2ConnectionHandler {
    private static final long GRACEFUL_SHUTDOWN_NO_TIMEOUT = -1L;
    private boolean autoTuneFlowControlOn = false;
    private int initialConnectionWindow;
    private ChannelHandlerContext ctx;
    private final FlowControlPinger flowControlPing = new FlowControlPinger();
    private static final long BDP_MEASUREMENT_PING = 1234L;

    AbstractNettyHandler(ChannelPromise channelUnused, Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings) {
        super(channelUnused, decoder, encoder, initialSettings);
        this.gracefulShutdownTimeoutMillis(-1L);
        this.initialConnectionWindow = initialSettings.initialWindowSize() == null ? -1 : initialSettings.initialWindowSize();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        super.handlerAdded(ctx);
        this.sendInitialConnectionWindow();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.sendInitialConnectionWindow();
    }

    @Override
    public final void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Http2Exception embedded = Http2CodecUtil.getEmbeddedHttp2Exception(cause);
        if (embedded == null) {
            this.onError(ctx, false, cause);
        } else {
            super.exceptionCaught(ctx, cause);
        }
    }

    protected final ChannelHandlerContext ctx() {
        return this.ctx;
    }

    private void sendInitialConnectionWindow() throws Http2Exception {
        if (this.ctx.channel().isActive() && this.initialConnectionWindow > 0) {
            Http2Stream connectionStream = this.connection().connectionStream();
            int currentSize = this.connection().local().flowController().windowSize(connectionStream);
            int delta = this.initialConnectionWindow - currentSize;
            this.decoder().flowController().incrementWindowSize(connectionStream, delta);
            this.initialConnectionWindow = -1;
            this.ctx.flush();
        }
    }

    @VisibleForTesting
    FlowControlPinger flowControlPing() {
        return this.flowControlPing;
    }

    @VisibleForTesting
    void setAutoTuneFlowControl(boolean isOn) {
        this.autoTuneFlowControlOn = isOn;
    }

    final class FlowControlPinger {
        private static final int MAX_WINDOW_SIZE = 0x800000;
        private int pingCount;
        private int pingReturn;
        private boolean pinging;
        private int dataSizeSincePing;
        private float lastBandwidth;
        private long lastPingTime;

        FlowControlPinger() {
        }

        public long payload() {
            return 1234L;
        }

        public int maxWindow() {
            return 0x800000;
        }

        public void onDataRead(int dataLength, int paddingLength) {
            if (!AbstractNettyHandler.this.autoTuneFlowControlOn) {
                return;
            }
            if (!this.isPinging()) {
                this.setPinging(true);
                this.sendPing(AbstractNettyHandler.this.ctx());
            }
            this.incrementDataSincePing(dataLength + paddingLength);
        }

        public void updateWindow() throws Http2Exception {
            if (!AbstractNettyHandler.this.autoTuneFlowControlOn) {
                return;
            }
            ++this.pingReturn;
            long elapsedTime = System.nanoTime() - this.lastPingTime;
            if (elapsedTime == 0L) {
                elapsedTime = 1L;
            }
            long bandwidth = (long)this.getDataSincePing() * TimeUnit.SECONDS.toNanos(1L) / elapsedTime;
            Http2LocalFlowController fc = AbstractNettyHandler.this.decoder().flowController();
            int targetWindow = Math.min(this.getDataSincePing() * 2, 0x800000);
            this.setPinging(false);
            int currentWindow = fc.initialWindowSize(AbstractNettyHandler.this.connection().connectionStream());
            if (targetWindow > currentWindow && (float)bandwidth > this.lastBandwidth) {
                this.lastBandwidth = bandwidth;
                int increase = targetWindow - currentWindow;
                fc.incrementWindowSize(AbstractNettyHandler.this.connection().connectionStream(), increase);
                fc.initialWindowSize(targetWindow);
                Http2Settings settings = new Http2Settings();
                settings.initialWindowSize(targetWindow);
                AbstractNettyHandler.this.frameWriter().writeSettings(AbstractNettyHandler.this.ctx(), settings, AbstractNettyHandler.this.ctx().newPromise());
            }
        }

        private boolean isPinging() {
            return this.pinging;
        }

        private void setPinging(boolean pingOut) {
            this.pinging = pingOut;
        }

        private void sendPing(ChannelHandlerContext ctx) {
            this.setDataSizeSincePing(0);
            this.lastPingTime = System.nanoTime();
            AbstractNettyHandler.this.encoder().writePing(ctx, false, 1234L, ctx.newPromise());
            ++this.pingCount;
        }

        private void incrementDataSincePing(int increase) {
            int currentSize = this.getDataSincePing();
            this.setDataSizeSincePing(currentSize + increase);
        }

        @VisibleForTesting
        int getPingCount() {
            return this.pingCount;
        }

        @VisibleForTesting
        int getPingReturn() {
            return this.pingReturn;
        }

        @VisibleForTesting
        int getDataSincePing() {
            return this.dataSizeSincePing;
        }

        @VisibleForTesting
        void setDataSizeSincePing(int dataSize) {
            this.dataSizeSincePing = dataSize;
        }
    }
}

