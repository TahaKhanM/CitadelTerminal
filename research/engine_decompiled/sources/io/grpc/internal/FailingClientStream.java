/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.NoopClientStream;

public final class FailingClientStream
extends NoopClientStream {
    private boolean started;
    private final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;

    public FailingClientStream(Status error2) {
        this(error2, ClientStreamListener.RpcProgress.PROCESSED);
    }

    public FailingClientStream(Status error2, ClientStreamListener.RpcProgress rpcProgress) {
        Preconditions.checkArgument(!error2.isOk(), "error must not be OK");
        this.error = error2;
        this.rpcProgress = rpcProgress;
    }

    @Override
    public void start(ClientStreamListener listener) {
        Preconditions.checkState(!this.started, "already started");
        this.started = true;
        listener.closed(this.error, this.rpcProgress, new Metadata());
    }

    @VisibleForTesting
    Status getError() {
        return this.error;
    }
}

