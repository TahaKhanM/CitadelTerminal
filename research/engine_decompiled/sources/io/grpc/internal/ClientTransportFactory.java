/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.ProxyParameters;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

public interface ClientTransportFactory
extends Closeable {
    public ConnectionClientTransport newClientTransport(SocketAddress var1, String var2, @Nullable String var3, @Nullable ProxyParameters var4);

    public ScheduledExecutorService getScheduledExecutorService();

    @Override
    public void close();
}

