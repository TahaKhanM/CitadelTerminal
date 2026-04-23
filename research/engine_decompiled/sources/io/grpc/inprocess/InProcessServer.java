/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ServerStreamTracer;
import io.grpc.inprocess.InProcessTransport;
import io.grpc.internal.Channelz;
import io.grpc.internal.Instrumented;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerTransportListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class InProcessServer
implements InternalServer {
    private static final ConcurrentMap<String, InProcessServer> registry = new ConcurrentHashMap<String, InProcessServer>();
    private final String name;
    private final List<ServerStreamTracer.Factory> streamTracerFactories;
    private ServerListener listener;
    private boolean shutdown;
    private final ObjectPool<ScheduledExecutorService> schedulerPool;
    private ScheduledExecutorService scheduler;

    static InProcessServer findServer(String name) {
        return (InProcessServer)registry.get(name);
    }

    InProcessServer(String name, ObjectPool<ScheduledExecutorService> schedulerPool, List<ServerStreamTracer.Factory> streamTracerFactories) {
        this.name = name;
        this.schedulerPool = schedulerPool;
        this.streamTracerFactories = Collections.unmodifiableList(Preconditions.checkNotNull(streamTracerFactories, "streamTracerFactories"));
    }

    @Override
    public void start(ServerListener serverListener) throws IOException {
        this.listener = serverListener;
        this.scheduler = this.schedulerPool.getObject();
        if (registry.putIfAbsent(this.name, this) != null) {
            throw new IOException("name already registered: " + this.name);
        }
    }

    @Override
    public int getPort() {
        return -1;
    }

    @Override
    public List<Instrumented<Channelz.SocketStats>> getListenSockets() {
        return Collections.emptyList();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void shutdown() {
        if (!registry.remove(this.name, this)) {
            throw new AssertionError();
        }
        this.scheduler = this.schedulerPool.returnObject(this.scheduler);
        InProcessServer inProcessServer = this;
        synchronized (inProcessServer) {
            this.shutdown = true;
            this.listener.serverShutdown();
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).toString();
    }

    synchronized ServerTransportListener register(InProcessTransport transport) {
        if (this.shutdown) {
            return null;
        }
        return this.listener.transportCreated(transport);
    }

    ObjectPool<ScheduledExecutorService> getScheduledExecutorServicePool() {
        return this.schedulerPool;
    }

    List<ServerStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }
}

