/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.internal.Channelz;
import io.grpc.internal.Instrumented;
import io.grpc.internal.ServerListener;
import java.io.IOException;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface InternalServer {
    public void start(ServerListener var1) throws IOException;

    public void shutdown();

    public int getPort();

    public List<Instrumented<Channelz.SocketStats>> getListenSockets();
}

