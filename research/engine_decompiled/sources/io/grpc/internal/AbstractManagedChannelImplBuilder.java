/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.BinaryLog;
import io.grpc.ClientInterceptor;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.grpc.internal.CensusStatsModule;
import io.grpc.internal.CensusTracingModule;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ExponentialBackoffPolicy;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.ManagedChannelOrphanWrapper;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.OverrideAuthorityNameResolverFactory;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TimeProvider;
import io.grpc.internal.TransportTracer;
import io.opencensus.trace.Tracing;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public abstract class AbstractManagedChannelImplBuilder<T extends AbstractManagedChannelImplBuilder<T>>
extends ManagedChannelBuilder<T> {
    private static final String DIRECT_ADDRESS_SCHEME = "directaddress";
    @VisibleForTesting
    static final long IDLE_MODE_MAX_TIMEOUT_DAYS = 30L;
    @VisibleForTesting
    static final long IDLE_MODE_DEFAULT_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(30L);
    @VisibleForTesting
    static final long IDLE_MODE_MIN_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(1L);
    private static final ObjectPool<? extends Executor> DEFAULT_EXECUTOR_POOL = SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR);
    private static final NameResolver.Factory DEFAULT_NAME_RESOLVER_FACTORY = NameResolverProvider.asFactory();
    private static final DecompressorRegistry DEFAULT_DECOMPRESSOR_REGISTRY = DecompressorRegistry.getDefaultInstance();
    private static final CompressorRegistry DEFAULT_COMPRESSOR_REGISTRY = CompressorRegistry.getDefaultInstance();
    private static final long DEFAULT_RETRY_BUFFER_SIZE_IN_BYTES = 0x1000000L;
    private static final long DEFAULT_PER_RPC_BUFFER_LIMIT_IN_BYTES = 0x100000L;
    ObjectPool<? extends Executor> executorPool = DEFAULT_EXECUTOR_POOL;
    private final List<ClientInterceptor> interceptors = new ArrayList<ClientInterceptor>();
    private NameResolver.Factory nameResolverFactory = DEFAULT_NAME_RESOLVER_FACTORY;
    final String target;
    @Nullable
    private final SocketAddress directServerAddress;
    @Nullable
    String userAgent;
    @Nullable
    @VisibleForTesting
    String authorityOverride;
    @Nullable
    LoadBalancer.Factory loadBalancerFactory;
    boolean fullStreamDecompression;
    DecompressorRegistry decompressorRegistry = DEFAULT_DECOMPRESSOR_REGISTRY;
    CompressorRegistry compressorRegistry = DEFAULT_COMPRESSOR_REGISTRY;
    long idleTimeoutMillis = IDLE_MODE_DEFAULT_TIMEOUT_MILLIS;
    int maxRetryAttempts = 5;
    int maxHedgedAttempts = 5;
    long retryBufferSize = 0x1000000L;
    long perRpcBufferLimit = 0x100000L;
    boolean retryEnabled = false;
    boolean temporarilyDisableRetry;
    Channelz channelz = Channelz.instance();
    int maxTraceEvents;
    protected TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    private int maxInboundMessageSize = 0x400000;
    @Nullable
    BinaryLog binlog;
    private boolean statsEnabled = true;
    private boolean recordStartedRpcs = true;
    private boolean recordFinishedRpcs = true;
    private boolean tracingEnabled = true;
    @Nullable
    private CensusStatsModule censusStatsOverride;

    public static ManagedChannelBuilder<?> forAddress(String name, int port) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public static ManagedChannelBuilder<?> forTarget(String target) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    @Override
    public T maxInboundMessageSize(int max2) {
        Preconditions.checkArgument(max2 >= 0, "negative max");
        this.maxInboundMessageSize = max2;
        return this.thisT();
    }

    protected final int maxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    protected AbstractManagedChannelImplBuilder(String target) {
        this.target = Preconditions.checkNotNull(target, "target");
        this.directServerAddress = null;
    }

    @VisibleForTesting
    static String makeTargetStringForDirectAddress(SocketAddress address) {
        try {
            return new URI(DIRECT_ADDRESS_SCHEME, "", "/" + address, null).toString();
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected AbstractManagedChannelImplBuilder(SocketAddress directServerAddress, String authority) {
        this.target = AbstractManagedChannelImplBuilder.makeTargetStringForDirectAddress(directServerAddress);
        this.directServerAddress = directServerAddress;
        this.nameResolverFactory = new DirectAddressNameResolverFactory(directServerAddress, authority);
    }

    @Override
    public final T directExecutor() {
        return (T)this.executor(MoreExecutors.directExecutor());
    }

    @Override
    public final T executor(Executor executor) {
        this.executorPool = executor != null ? new FixedObjectPool<Executor>(executor) : DEFAULT_EXECUTOR_POOL;
        return this.thisT();
    }

    @Override
    public final T intercept(List<ClientInterceptor> interceptors) {
        this.interceptors.addAll(interceptors);
        return this.thisT();
    }

    @Override
    public final T intercept(ClientInterceptor ... interceptors) {
        return (T)this.intercept((List)Arrays.asList(interceptors));
    }

    @Override
    public final T nameResolverFactory(NameResolver.Factory resolverFactory) {
        Preconditions.checkState(this.directServerAddress == null, "directServerAddress is set (%s), which forbids the use of NameResolverFactory", (Object)this.directServerAddress);
        this.nameResolverFactory = resolverFactory != null ? resolverFactory : DEFAULT_NAME_RESOLVER_FACTORY;
        return this.thisT();
    }

    @Override
    public final T loadBalancerFactory(LoadBalancer.Factory loadBalancerFactory) {
        Preconditions.checkState(this.directServerAddress == null, "directServerAddress is set (%s), which forbids the use of LoadBalancer.Factory", (Object)this.directServerAddress);
        this.loadBalancerFactory = loadBalancerFactory;
        return this.thisT();
    }

    @Override
    public final T enableFullStreamDecompression() {
        this.fullStreamDecompression = true;
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
    public final T userAgent(@Nullable String userAgent) {
        this.userAgent = userAgent;
        return this.thisT();
    }

    @Override
    public final T overrideAuthority(String authority) {
        this.authorityOverride = this.checkAuthority(authority);
        return this.thisT();
    }

    @Override
    public final T idleTimeout(long value, TimeUnit unit) {
        Preconditions.checkArgument(value > 0L, "idle timeout is %s, but must be positive", value);
        this.idleTimeoutMillis = unit.toDays(value) >= 30L ? -1L : Math.max(unit.toMillis(value), IDLE_MODE_MIN_TIMEOUT_MILLIS);
        return this.thisT();
    }

    @Override
    public final T maxRetryAttempts(int maxRetryAttempts) {
        this.maxRetryAttempts = maxRetryAttempts;
        return this.thisT();
    }

    @Override
    public final T maxHedgedAttempts(int maxHedgedAttempts) {
        this.maxHedgedAttempts = maxHedgedAttempts;
        return this.thisT();
    }

    @Override
    public final T retryBufferSize(long bytes2) {
        Preconditions.checkArgument(bytes2 > 0L, "retry buffer size must be positive");
        this.retryBufferSize = bytes2;
        return this.thisT();
    }

    @Override
    public final T perRpcBufferLimit(long bytes2) {
        Preconditions.checkArgument(bytes2 > 0L, "per RPC buffer limit must be positive");
        this.perRpcBufferLimit = bytes2;
        return this.thisT();
    }

    @Override
    public final T disableRetry() {
        this.retryEnabled = false;
        return this.thisT();
    }

    @Override
    public final T enableRetry() {
        this.retryEnabled = true;
        return this.thisT();
    }

    @Override
    public final T setBinaryLog(BinaryLog binlog) {
        this.binlog = binlog;
        return this.thisT();
    }

    @Override
    public T maxTraceEvents(int maxTraceEvents) {
        Preconditions.checkArgument(maxTraceEvents >= 0, "maxTraceEvents must be non-negative");
        this.maxTraceEvents = maxTraceEvents;
        return this.thisT();
    }

    @VisibleForTesting
    protected final T overrideCensusStatsModule(CensusStatsModule censusStats) {
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

    @VisibleForTesting
    final long getIdleTimeoutMillis() {
        return this.idleTimeoutMillis;
    }

    protected String checkAuthority(String authority) {
        return GrpcUtil.checkAuthority(authority);
    }

    @Override
    public ManagedChannel build() {
        return new ManagedChannelOrphanWrapper(new ManagedChannelImpl(this, this.buildTransportFactory(), new ExponentialBackoffPolicy.Provider(), SharedResourcePool.forResource(GrpcUtil.SHARED_CHANNEL_EXECUTOR), GrpcUtil.STOPWATCH_SUPPLIER, this.getEffectiveInterceptors(), TimeProvider.SYSTEM_TIME_PROVIDER));
    }

    @VisibleForTesting
    final List<ClientInterceptor> getEffectiveInterceptors() {
        ArrayList<ClientInterceptor> effectiveInterceptors = new ArrayList<ClientInterceptor>(this.interceptors);
        this.temporarilyDisableRetry = false;
        if (this.statsEnabled) {
            this.temporarilyDisableRetry = true;
            CensusStatsModule censusStats = this.censusStatsOverride;
            if (censusStats == null) {
                censusStats = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true);
            }
            effectiveInterceptors.add(0, censusStats.getClientInterceptor(this.recordStartedRpcs, this.recordFinishedRpcs));
        }
        if (this.tracingEnabled) {
            this.temporarilyDisableRetry = true;
            CensusTracingModule censusTracing = new CensusTracingModule(Tracing.getTracer(), Tracing.getPropagationComponent().getBinaryFormat());
            effectiveInterceptors.add(0, censusTracing.getClientInterceptor());
        }
        return effectiveInterceptors;
    }

    protected abstract ClientTransportFactory buildTransportFactory();

    protected Attributes getNameResolverParams() {
        return Attributes.EMPTY;
    }

    NameResolver.Factory getNameResolverFactory() {
        if (this.authorityOverride == null) {
            return this.nameResolverFactory;
        }
        return new OverrideAuthorityNameResolverFactory(this.nameResolverFactory, this.authorityOverride);
    }

    private T thisT() {
        AbstractManagedChannelImplBuilder thisT = this;
        return (T)thisT;
    }

    private static class DirectAddressNameResolverFactory
    extends NameResolver.Factory {
        final SocketAddress address;
        final String authority;

        DirectAddressNameResolverFactory(SocketAddress address, String authority) {
            this.address = address;
            this.authority = authority;
        }

        @Override
        public NameResolver newNameResolver(URI notUsedUri, Attributes params2) {
            return new NameResolver(){

                @Override
                public String getServiceAuthority() {
                    return DirectAddressNameResolverFactory.this.authority;
                }

                @Override
                public void start(NameResolver.Listener listener) {
                    listener.onAddresses(Collections.singletonList(new EquivalentAddressGroup(DirectAddressNameResolverFactory.this.address)), Attributes.EMPTY);
                }

                @Override
                public void shutdown() {
                }
            };
        }

        @Override
        public String getDefaultScheme() {
            return AbstractManagedChannelImplBuilder.DIRECT_ADDRESS_SCHEME;
        }
    }
}

