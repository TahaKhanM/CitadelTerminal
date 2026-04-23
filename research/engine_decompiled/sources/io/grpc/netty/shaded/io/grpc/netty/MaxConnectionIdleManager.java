/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

abstract class MaxConnectionIdleManager {
    private static final Ticker systemTicker = new Ticker(){

        @Override
        public long nanoTime() {
            return System.nanoTime();
        }
    };
    private final long maxConnectionIdleInNanos;
    private final Ticker ticker;
    @CheckForNull
    private ScheduledFuture<?> shutdownFuture;
    private Runnable shutdownTask;
    private ScheduledExecutorService scheduler;
    private long nextIdleMonitorTime;
    private boolean shutdownDelayed;
    private boolean isActive;

    MaxConnectionIdleManager(long maxConnectionIdleInNanos) {
        this(maxConnectionIdleInNanos, systemTicker);
    }

    @VisibleForTesting
    MaxConnectionIdleManager(long maxConnectionIdleInNanos, Ticker ticker) {
        this.maxConnectionIdleInNanos = maxConnectionIdleInNanos;
        this.ticker = ticker;
    }

    void start(ChannelHandlerContext ctx) {
        this.start(ctx, ctx.executor());
    }

    @VisibleForTesting
    void start(final ChannelHandlerContext ctx, final ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
        this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        this.shutdownTask = new LogExceptionRunnable(new Runnable(){

            @Override
            public void run() {
                if (MaxConnectionIdleManager.this.shutdownDelayed) {
                    if (!MaxConnectionIdleManager.this.isActive) {
                        MaxConnectionIdleManager.this.shutdownFuture = scheduler.schedule(MaxConnectionIdleManager.this.shutdownTask, MaxConnectionIdleManager.this.nextIdleMonitorTime - MaxConnectionIdleManager.this.ticker.nanoTime(), TimeUnit.NANOSECONDS);
                        MaxConnectionIdleManager.this.shutdownDelayed = false;
                    }
                } else {
                    MaxConnectionIdleManager.this.close(ctx);
                    MaxConnectionIdleManager.this.shutdownFuture = null;
                }
            }
        });
        this.shutdownFuture = scheduler.schedule(this.shutdownTask, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
    }

    abstract void close(ChannelHandlerContext var1);

    void onTransportActive() {
        this.isActive = true;
        this.shutdownDelayed = true;
    }

    void onTransportIdle() {
        this.isActive = false;
        if (this.shutdownFuture == null) {
            return;
        }
        if (this.shutdownFuture.isDone()) {
            this.shutdownDelayed = false;
            this.shutdownFuture = this.scheduler.schedule(this.shutdownTask, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
        } else {
            this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        }
    }

    void onTransportTermination() {
        if (this.shutdownFuture != null) {
            this.shutdownFuture.cancel(false);
            this.shutdownFuture = null;
        }
    }

    @VisibleForTesting
    static interface Ticker {
        public long nanoTime();
    }
}

