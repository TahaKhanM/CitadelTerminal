/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.common;

import io.opencensus.common.Timestamp;

public abstract class Clock {
    public abstract Timestamp now();

    public abstract long nowNanos();
}

