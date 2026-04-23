/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import io.grpc.Status;
import io.grpc.internal.Channelz;
import io.grpc.internal.Instrumented;
import java.util.concurrent.ScheduledExecutorService;

public interface ServerTransport
extends Instrumented<Channelz.SocketStats> {
    public void shutdown();

    public void shutdownNow(Status var1);

    public ScheduledExecutorService getScheduledExecutorService();
}

