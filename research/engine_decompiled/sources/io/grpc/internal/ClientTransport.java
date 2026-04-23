/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientStream;
import io.grpc.internal.Instrumented;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ClientTransport
extends Instrumented<Channelz.SocketStats> {
    public ClientStream newStream(MethodDescriptor<?, ?> var1, Metadata var2, CallOptions var3);

    public void ping(PingCallback var1, Executor var2);

    public static interface PingCallback {
        public void onSuccess(long var1);

        public void onFailure(Throwable var1);
    }
}

