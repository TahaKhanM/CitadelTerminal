/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import java.util.concurrent.TimeUnit;

public interface TimeProvider {
    public static final TimeProvider SYSTEM_TIME_PROVIDER = new TimeProvider(){
        final long offsetNanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - System.nanoTime();

        @Override
        public long currentTimeNanos() {
            return System.nanoTime() + this.offsetNanos;
        }
    };

    public long currentTimeNanos();
}

