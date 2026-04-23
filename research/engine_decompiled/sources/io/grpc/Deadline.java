/*
 * Decompiled with CFR 0.152.
 */
package io.grpc;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class Deadline
implements Comparable<Deadline> {
    private static final SystemTicker SYSTEM_TICKER = new SystemTicker();
    private static final long MAX_OFFSET = TimeUnit.DAYS.toNanos(36500L);
    private static final long MIN_OFFSET = -MAX_OFFSET;
    private final Ticker ticker;
    private final long deadlineNanos;
    private volatile boolean expired;

    public static Deadline after(long duration, TimeUnit units) {
        return Deadline.after(duration, units, SYSTEM_TICKER);
    }

    static Deadline after(long duration, TimeUnit units, Ticker ticker) {
        Deadline.checkNotNull(units, "units");
        return new Deadline(ticker, units.toNanos(duration), true);
    }

    private Deadline(Ticker ticker, long offset, boolean baseInstantAlreadyExpired) {
        this(ticker, ticker.read(), offset, baseInstantAlreadyExpired);
    }

    private Deadline(Ticker ticker, long baseInstant, long offset, boolean baseInstantAlreadyExpired) {
        this.ticker = ticker;
        offset = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, offset));
        this.deadlineNanos = baseInstant + offset;
        this.expired = baseInstantAlreadyExpired && offset <= 0L;
    }

    public boolean isExpired() {
        if (!this.expired) {
            if (this.deadlineNanos - this.ticker.read() <= 0L) {
                this.expired = true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isBefore(Deadline other) {
        return this.deadlineNanos - other.deadlineNanos < 0L;
    }

    public Deadline minimum(Deadline other) {
        return this.isBefore(other) ? this : other;
    }

    public Deadline offset(long offset, TimeUnit units) {
        if (offset == 0L) {
            return this;
        }
        return new Deadline(this.ticker, this.deadlineNanos, units.toNanos(offset), this.isExpired());
    }

    public long timeRemaining(TimeUnit unit) {
        long nowNanos = this.ticker.read();
        if (!this.expired && this.deadlineNanos - nowNanos <= 0L) {
            this.expired = true;
        }
        return unit.convert(this.deadlineNanos - nowNanos, TimeUnit.NANOSECONDS);
    }

    public ScheduledFuture<?> runOnExpiration(Runnable task, ScheduledExecutorService scheduler) {
        Deadline.checkNotNull(task, "task");
        Deadline.checkNotNull(scheduler, "scheduler");
        return scheduler.schedule(task, this.deadlineNanos - this.ticker.read(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        return this.timeRemaining(TimeUnit.NANOSECONDS) + " ns from now";
    }

    @Override
    public int compareTo(Deadline that) {
        long diff2 = this.deadlineNanos - that.deadlineNanos;
        if (diff2 < 0L) {
            return -1;
        }
        if (diff2 > 0L) {
            return 1;
        }
        return 0;
    }

    private static <T> T checkNotNull(T reference, Object errorMessage2) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage2));
        }
        return reference;
    }

    private static class SystemTicker
    extends Ticker {
        private SystemTicker() {
        }

        @Override
        public long read() {
            return System.nanoTime();
        }
    }

    static abstract class Ticker {
        Ticker() {
        }

        public abstract long read();
    }
}

