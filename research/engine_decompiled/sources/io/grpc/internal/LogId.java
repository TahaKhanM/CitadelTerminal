/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;

public final class LogId {
    private static final AtomicLong idAlloc = new AtomicLong();
    private final String tag;
    private final long id;

    public static LogId allocate(String tag) {
        return new LogId(tag, LogId.getNextId());
    }

    static long getNextId() {
        return idAlloc.incrementAndGet();
    }

    protected LogId(String tag, long id) {
        this.tag = tag;
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public String getTag() {
        return this.tag;
    }

    public String toString() {
        return this.tag + "-" + this.id;
    }
}

