/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

interface BackoffPolicy {
    public long nextBackoffNanos();

    public static interface Provider {
        public BackoffPolicy get();
    }
}

