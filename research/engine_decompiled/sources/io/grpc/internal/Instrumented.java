/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.internal.WithLogId;

public interface Instrumented<T>
extends WithLogId {
    public ListenableFuture<T> getStats();
}

