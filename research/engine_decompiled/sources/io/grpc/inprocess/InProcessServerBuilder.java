/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import io.grpc.ExperimentalApi;
import io.grpc.ServerStreamTracer;
import io.grpc.inprocess.InProcessServer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourcePool;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ExperimentalApi(value="https://github.com/grpc/grpc-java/issues/1783")
public final class InProcessServerBuilder
extends AbstractServerImplBuilder<InProcessServerBuilder> {
    private final String name;
    private ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);

    public static InProcessServerBuilder forName(String name) {
        return new InProcessServerBuilder(name);
    }

    public static InProcessServerBuilder forPort(int port) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(String name) {
        this.name = Preconditions.checkNotNull(name, "name");
        this.setStatsRecordStartedRpcs(false);
        this.setStatsRecordFinishedRpcs(false);
        this.handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool<ScheduledExecutorService>(Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    @Override
    protected InProcessServer buildTransportServer(List<ServerStreamTracer.Factory> streamTracerFactories) {
        return new InProcessServer(this.name, this.schedulerPool, streamTracerFactories);
    }

    @Override
    public InProcessServerBuilder useTransportSecurity(File certChain, File privateKey) {
        throw new UnsupportedOperationException("TLS not supported in InProcessServer");
    }
}

