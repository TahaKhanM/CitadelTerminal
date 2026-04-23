/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.internal;

import io.opencensus.common.Clock;
import io.opencensus.common.Timestamp;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class ZeroTimeClock
extends Clock {
    private static final ZeroTimeClock INSTANCE = new ZeroTimeClock();
    private static final Timestamp ZERO_TIMESTAMP = Timestamp.create(0L, 0);

    private ZeroTimeClock() {
    }

    public static ZeroTimeClock getInstance() {
        return INSTANCE;
    }

    @Override
    public Timestamp now() {
        return ZERO_TIMESTAMP;
    }

    @Override
    public long nowNanos() {
        return 0L;
    }
}

