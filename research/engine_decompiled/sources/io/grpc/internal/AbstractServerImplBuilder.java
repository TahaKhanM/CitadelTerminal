/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.BinaryLog;
import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.HandlerRegistry;
import io.grpc.Internal;
import io.grpc.InternalNotifyOnServerBuild;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.internal.CallTracer;
import io.grpc.internal.CensusStatsModule;
import io.grpc.internal.CensusTracingModule;
import io.grpc.internal.Channelz;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalHandlerRegistry;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImpl;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.opencensus.trace.Tracing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public abstract class AbstractServerImplBuilder<T extends AbstractServerImplBuilder<T>>
extends ServerBuilder<T> {
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final HandlerRegistry DEFAULT_FALLBACK_REGISTRY = new HandlerRegistry(){

        @Override
        public List<ServerServiceDefinition> getServices() {
            return Collections.emptyList();
        }

        @Override
        public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
            return null;
        }
    };
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(120L);
    final InternalHandlerRegistry.Builder registryBuilder = new InternalHandlerRegistry.Builder();
    final List<ServerTransportFilter> transportFilters = new ArrayList<ServerTransportFilter>();
    final List<ServerInterceptor> interceptors = new ArrayList<ServerInterceptor>();
    private final List<InternalNotifyOnServerBuild> notifyOnBuildList = new ArrayList<InternalNotifyOnServerBuild>();
    private final List<ServerStreamTracer.Factory> streamTracerFactories = new ArrayList<ServerStreamTracer.Factory>();
    HandlerRegistry fallbackRegistry = DEFAULT_FALLBACK_REGISTRY;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;
    @Nullable
    private CensusStatsModule censusStatsOverride;
    private boolean statsEnabled = true;
    private boolean recordStartedRpcs = true;
    private boolean recordFinishedRpcs = true;
    private boolean tracingEnabled = true;
    @Nullable
    protected BinaryLog binlog;
    protected TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    protected Channelz channelz = Channelz.instance();
    protected CallTracer.Factory callTracerFactory = CallTracer.getDefaultFactory();

    public static ServerBuilder<?> forPort(int port) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    @Override
    public final T directExecutor() {
        return (T)this.executor(MoreExecutors.directExecutor());
    }

    @Override
    public final T executor(@Nullable Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<Executor>(executor) : DEFAULT_EXECUTOR_POOL;
        return this.thisT();
    }

    @Override
    public final T addService(ServerServiceDefinition service) {
        this.registryBuilder.addService(service);
        return this.thisT();
    }

    @Override
    public final T addService(BindableService bindableService) {
        if (bindableService instanceof InternalNotifyOnServerBuild) {
            this.notifyOnBuildList.add((InternalNotifyOnServerBuild)((Object)bindableService));
        }
        return (T)this.addService(bindableService.bindService());
    }

    @Override
    public final T addTransportFilter(ServerTransportFilter filter2) {
        this.transportFilters.add(Preconditions.checkNotNull(filter2, "filter"));
        return this.thisT();
    }

    @Override
    public final T intercept(ServerInterceptor interceptor) {
        this.interceptors.add(interceptor);
        return this.thisT();
    }

    @Override
    public final T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.streamTracerFactories.add(Preconditions.checkNotNull(factory, "factory"));
        return this.thisT();
    }

    @Override
    public final T fallbackHandlerRegistry(HandlerRegistry registry) {
        this.fallbackRegistry = registry != null ? registry : DEFAULT_FALLBACK_REGISTRY;
        return this.thisT();
    }

    @Override
    public final T decompressorRegistry(DecompressorRegistry registry) {
        this.decompressorRegistry = registry != null ? registry : DEFAULT_DECOMPRESSOR_REGISTRY;
        return this.thisT();
    }

    @Override
    public final T compressorRegistry(CompressorRegistry registry) {
        this.compressorRegistry = registry != null ? registry : DEFAULT_COMPRESSOR_REGISTRY;
        return this.thisT();
    }

    @Override
    public final T handshakeTimeout(long timeout, TimeUnit unit) {
        Preconditions.checkArgument(timeout > 0L, "handshake timeout is %s, but must be positive", timeout);
        this.handshakeTimeoutMillis = unit.toMillis(timeout);
        return this.thisT();
    }

    @Override
    public final T setBinaryLog(BinaryLog binaryLog) {
        this.binlog = binaryLog;
        return this.thisT();
    }

    @VisibleForTesting
    protected T overrideCensusStatsModule(CensusStatsModule censusStats) {
        this.censusStatsOverride = censusStats;
        return this.thisT();
    }

    protected void setStatsEnabled(boolean value) {
        this.statsEnabled = value;
    }

    protected void setStatsRecordStartedRpcs(boolean value) {
        this.recordStartedRpcs = value;
    }

    protected void setStatsRecordFinishedRpcs(boolean value) {
        this.recordFinishedRpcs = value;
    }

    protected void setTracingEnabled(boolean value) {
        this.tracingEnabled = value;
    }

    @Override
    public Server build() {
        ServerImpl server = new ServerImpl(this, this.buildTransportServer(Collections.unmodifiableList(this.getTracerFactories())), Context.ROOT);
        for (InternalNotifyOnServerBuild notifyTarget : this.notifyOnBuildList) {
            notifyTarget.notifyOnBuild(server);
        }
        return server;
    }

    @VisibleForTesting
    final List<ServerStreamTracer.Factory> getTracerFactories() {
        ArrayList<ServerStreamTracer.Factory> tracerFactories = new ArrayList<ServerStreamTracer.Factory>();
        if (this.statsEnabled) {
            CensusStatsModule censusStats = this.censusStatsOverride;
            if (censusStats == null) {
                censusStats = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true);
            }
            tracerFactories.add(censusStats.getServerTracerFactory(this.recordStartedRpcs, this.recordFinishedRpcs));
        }
        if (this.tracingEnabled) {
            CensusTracingModule censusTracing = new CensusTracingModule(Tracing.getTracer(), Tracing.getPropagationComponent().getBinaryFormat());
            tracerFactories.add(censusTracing.getServerTracerFactory());
        }
        tracerFactories.addAll(this.streamTracerFactories);
        return tracerFactories;
    }

    @Internal
    protected abstract InternalServer buildTransportServer(List<ServerStreamTracer.Factory> var1);

    private T thisT() {
        AbstractServerImplBuilder thisT = this;
        return (T)thisT;
    }
}

