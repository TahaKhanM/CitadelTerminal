/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.LogId;
import java.util.concurrent.Executor;

class FailingClientTransport
implements ClientTransport {
    @VisibleForTesting
    final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;

    FailingClientTransport(Status error2, ClientStreamListener.RpcProgress rpcProgress) {
        Preconditions.checkArgument(!error2.isOk(), "error must not be OK");
        this.error = error2;
        this.rpcProgress = rpcProgress;
    }

    @Override
    public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
        return new FailingClientStream(this.error, this.rpcProgress);
    }

    @Override
    public void ping(final ClientTransport.PingCallback callback, Executor executor) {
        executor.execute(new Runnable(){

            @Override
            public void run() {
                callback.onFailure(FailingClientTransport.this.error.asException());
            }
        });
    }

    @Override
    public ListenableFuture<Channelz.SocketStats> getStats() {
        SettableFuture<Channelz.SocketStats> ret = SettableFuture.create();
        ret.set(null);
        return ret;
    }

    @Override
    public LogId getLogId() {
        throw new UnsupportedOperationException("Not a real transport");
    }
}

