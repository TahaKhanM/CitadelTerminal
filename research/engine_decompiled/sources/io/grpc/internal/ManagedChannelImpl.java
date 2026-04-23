/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ClientStreamTracer;
import io.grpc.CompressorRegistry;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AbstractSubchannel;
import io.grpc.internal.AutoConfiguredLoadBalancerFactory;
import io.grpc.internal.BackoffPolicy;
import io.grpc.internal.CallCredentialsApplyingTransportFactory;
import io.grpc.internal.CallTracer;
import io.grpc.internal.ChannelExecutor;
import io.grpc.internal.ChannelTracer;
import io.grpc.internal.Channelz;
import io.grpc.internal.ClientCallImpl;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectivityStateManager;
import io.grpc.internal.DelayedClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.Instrumented;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.LogExceptionRunnable;
import io.grpc.internal.LogId;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.OobChannel;
import io.grpc.internal.PickSubchannelArgsImpl;
import io.grpc.internal.Rescheduler;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.ServiceConfigInterceptor;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.internal.TimeProvider;
import io.grpc.internal.WithLogId;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class ManagedChannelImpl
extends ManagedChannel
implements Instrumented<Channelz.ChannelStats> {
    static final Logger logger = Logger.getLogger(ManagedChannelImpl.class.getName());
    @VisibleForTesting
    static final Pattern URI_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");
    static final long IDLE_TIMEOUT_MILLIS_DISABLE = -1L;
    @VisibleForTesting
    static final long SUBCHANNEL_SHUTDOWN_DELAY_SECONDS = 5L;
    @VisibleForTesting
    static final Status SHUTDOWN_NOW_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdownNow invoked");
    @VisibleForTesting
    static final Status SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Channel shutdown invoked");
    @VisibleForTesting
    static final Status SUBCHANNEL_SHUTDOWN_STATUS = Status.UNAVAILABLE.withDescription("Subchannel shutdown invoked");
    private final LogId logId = LogId.allocate(this.getClass().getName());
    private final String target;
    private final NameResolver.Factory nameResolverFactory;
    private final Attributes nameResolverParams;
    private final LoadBalancer.Factory loadBalancerFactory;
    private final ClientTransportFactory transportFactory;
    private final Executor executor;
    private final ObjectPool<? extends Executor> executorPool;
    private final ObjectPool<? extends Executor> oobExecutorPool;
    private final TimeProvider timeProvider;
    private final int maxTraceEvents;
    private final ChannelExecutor channelExecutor = new ChannelExecutor(){

        @Override
        void handleUncaughtThrowable(Throwable t) {
            super.handleUncaughtThrowable(t);
            ManagedChannelImpl.this.panic(t);
        }
    };
    private boolean fullStreamDecompression;
    private final DecompressorRegistry decompressorRegistry;
    private final CompressorRegistry compressorRegistry;
    private final Supplier<Stopwatch> stopwatchSupplier;
    private final long idleTimeoutMillis;
    private final ConnectivityStateManager channelStateManager = new ConnectivityStateManager();
    private final ServiceConfigInterceptor serviceConfigInterceptor;
    private final BackoffPolicy.Provider backoffPolicyProvider;
    private final Channel interceptorChannel;
    @Nullable
    private final String userAgent;
    private NameResolver nameResolver;
    private boolean nameResolverStarted;
    @Nullable
    private LbHelperImpl lbHelper;
    @Nullable
    private volatile LoadBalancer.SubchannelPicker subchannelPicker;
    private boolean panicMode;
    private final Set<InternalSubchannel> subchannels = new HashSet<InternalSubchannel>(16, 0.75f);
    private final Set<OobChannel> oobChannels = new HashSet<OobChannel>(1, 0.75f);
    private final DelayedClientTransport delayedTransport;
    private final UncommittedRetriableStreamsRegistry uncommittedRetriableStreamsRegistry = new UncommittedRetriableStreamsRegistry();
    private final AtomicBoolean shutdown = new AtomicBoolean(false);
    private boolean shutdownNowed;
    private volatile boolean terminating;
    private volatile boolean terminated;
    private final CountDownLatch terminatedLatch = new CountDownLatch(1);
    private final CallTracer.Factory callTracerFactory;
    private final CallTracer channelCallTracer;
    @CheckForNull
    private final ChannelTracer channelTracer;
    private final Channelz channelz;
    private final RetriableStream.ChannelBufferMeter channelBufferUsed = new RetriableStream.ChannelBufferMeter();
    @Nullable
    private RetriableStream.Throttle throttle;
    private final long perRpcBufferLimit;
    private final long channelBufferLimit;
    private final boolean retryEnabled;
    private final ManagedClientTransport.Listener delayedTransportListener = new ManagedClientTransport.Listener(){

        @Override
        public void transportShutdown(Status s2) {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
        }

        @Override
        public void transportReady() {
        }

        @Override
        public void transportInUse(boolean inUse) {
            ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(ManagedChannelImpl.this.delayedTransport, inUse);
        }

        @Override
        public void transportTerminated() {
            Preconditions.checkState(ManagedChannelImpl.this.shutdown.get(), "Channel must have been shut down");
            ManagedChannelImpl.this.terminating = true;
            ManagedChannelImpl.this.shutdownNameResolverAndLoadBalancer(false);
            ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            ManagedChannelImpl.this.maybeTerminateChannel();
        }
    };
    @VisibleForTesting
    final InUseStateAggregator<Object> inUseStateAggregator = new InUseStateAggregator<Object>(){

        @Override
        void handleInUse() {
            ManagedChannelImpl.this.exitIdleMode();
        }

        @Override
        void handleNotInUse() {
            if (ManagedChannelImpl.this.shutdown.get()) {
                return;
            }
            ManagedChannelImpl.this.rescheduleIdleTimer();
        }
    };
    @Nullable
    private ScheduledFuture<?> nameResolverRefreshFuture;
    @Nullable
    private NameResolverRefresh nameResolverRefresh;
    @Nullable
    private BackoffPolicy nameResolverBackoffPolicy;
    private final ClientCallImpl.ClientTransportProvider transportProvider = new ClientCallImpl.ClientTransportProvider(){

        @Override
        public ClientTransport get(LoadBalancer.PickSubchannelArgs args) {
            LoadBalancer.SubchannelPicker pickerCopy = ManagedChannelImpl.this.subchannelPicker;
            if (ManagedChannelImpl.this.shutdown.get()) {
                return ManagedChannelImpl.this.delayedTransport;
            }
            if (pickerCopy == null) {
                ManagedChannelImpl.this.channelExecutor.executeLater(new Runnable(){

                    @Override
                    public void run() {
                        ManagedChannelImpl.this.exitIdleMode();
                    }
                }).drain();
                return ManagedChannelImpl.this.delayedTransport;
            }
            LoadBalancer.PickResult pickResult = pickerCopy.pickSubchannel(args);
            ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickResult, args.getCallOptions().isWaitForReady());
            if (transport != null) {
                return transport;
            }
            return ManagedChannelImpl.this.delayedTransport;
        }

        @Override
        public <ReqT> RetriableStream<ReqT> newRetriableStream(final MethodDescriptor<ReqT, ?> method, final CallOptions callOptions, Metadata headers, final Context context) {
            Preconditions.checkState(ManagedChannelImpl.this.retryEnabled, "retry should be enabled");
            return new RetriableStream<ReqT>(method, headers, ManagedChannelImpl.this.channelBufferUsed, ManagedChannelImpl.this.perRpcBufferLimit, ManagedChannelImpl.this.channelBufferLimit, ManagedChannelImpl.this.getCallExecutor(callOptions), ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), callOptions.getOption(ServiceConfigInterceptor.RETRY_POLICY_KEY), ManagedChannelImpl.this.throttle){

                @Override
                Status prestart() {
                    return ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.add(this);
                }

                @Override
                void postCommit() {
                    ManagedChannelImpl.this.uncommittedRetriableStreamsRegistry.remove(this);
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                ClientStream newSubstream(ClientStreamTracer.Factory tracerFactory, Metadata newHeaders) {
                    CallOptions newOptions = callOptions.withStreamTracerFactory(tracerFactory);
                    ClientTransport transport = this.get(new PickSubchannelArgsImpl(method, newHeaders, newOptions));
                    Context origContext = context.attach();
                    try {
                        ClientStream clientStream = transport.newStream(method, newHeaders, newOptions);
                        return clientStream;
                    }
                    finally {
                        context.detach(origContext);
                    }
                }
            };
        }
    };
    private final Rescheduler idleTimer;

    private void maybeShutdownNowSubchannels() {
        if (this.shutdownNowed) {
            for (InternalSubchannel subchannel : this.subchannels) {
                subchannel.shutdownNow(SHUTDOWN_NOW_STATUS);
            }
            for (OobChannel oobChannel : this.oobChannels) {
                oobChannel.getInternalSubchannel().shutdownNow(SHUTDOWN_NOW_STATUS);
            }
        }
    }

    @Override
    public ListenableFuture<Channelz.ChannelStats> getStats() {
        final SettableFuture<Channelz.ChannelStats> ret = SettableFuture.create();
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                Channelz.ChannelStats.Builder builder = new Channelz.ChannelStats.Builder();
                ManagedChannelImpl.this.channelCallTracer.updateBuilder(builder);
                if (ManagedChannelImpl.this.channelTracer != null) {
                    ManagedChannelImpl.this.channelTracer.updateBuilder(builder);
                }
                builder.setTarget(ManagedChannelImpl.this.target).setState(ManagedChannelImpl.this.channelStateManager.getState());
                ArrayList<WithLogId> children2 = new ArrayList<WithLogId>();
                children2.addAll(ManagedChannelImpl.this.subchannels);
                children2.addAll(ManagedChannelImpl.this.oobChannels);
                builder.setSubchannels(children2);
                ret.set(builder.build());
            }
        }).drain();
        return ret;
    }

    @Override
    public LogId getLogId() {
        return this.logId;
    }

    private void shutdownNameResolverAndLoadBalancer(boolean verifyActive) {
        if (verifyActive) {
            Preconditions.checkState(this.nameResolver != null, "nameResolver is null");
            Preconditions.checkState(this.lbHelper != null, "lbHelper is null");
        }
        if (this.nameResolver != null) {
            this.cancelNameResolverBackoff();
            this.nameResolver.shutdown();
            this.nameResolver = null;
            this.nameResolverStarted = false;
        }
        if (this.lbHelper != null) {
            this.lbHelper.lb.shutdown();
            this.lbHelper = null;
        }
        this.subchannelPicker = null;
    }

    @VisibleForTesting
    void exitIdleMode() {
        if (this.shutdown.get() || this.panicMode) {
            return;
        }
        if (this.inUseStateAggregator.isInUse()) {
            this.cancelIdleTimer(false);
        } else {
            this.rescheduleIdleTimer();
        }
        if (this.lbHelper != null) {
            return;
        }
        logger.log(Level.FINE, "[{0}] Exiting idle mode", this.getLogId());
        this.lbHelper = new LbHelperImpl(this.nameResolver);
        this.lbHelper.lb = this.loadBalancerFactory.newLoadBalancer(this.lbHelper);
        NameResolverListenerImpl listener = new NameResolverListenerImpl(this.lbHelper);
        try {
            this.nameResolver.start(listener);
            this.nameResolverStarted = true;
        }
        catch (Throwable t) {
            listener.onError(Status.fromThrowable(t));
        }
    }

    private void enterIdleMode() {
        logger.log(Level.FINE, "[{0}] Entering idle mode", this.getLogId());
        this.shutdownNameResolverAndLoadBalancer(true);
        this.delayedTransport.reprocess(null);
        this.nameResolver = ManagedChannelImpl.getNameResolver(this.target, this.nameResolverFactory, this.nameResolverParams);
        this.channelStateManager.gotoState(ConnectivityState.IDLE);
    }

    private void cancelIdleTimer(boolean permanent) {
        this.idleTimer.cancel(permanent);
    }

    private void rescheduleIdleTimer() {
        if (this.idleTimeoutMillis == -1L) {
            return;
        }
        this.idleTimer.reschedule(this.idleTimeoutMillis, TimeUnit.MILLISECONDS);
    }

    private void cancelNameResolverBackoff() {
        if (this.nameResolverRefreshFuture != null) {
            this.nameResolverRefreshFuture.cancel(false);
            this.nameResolverRefresh.cancelled = true;
            this.nameResolverRefreshFuture = null;
            this.nameResolverRefresh = null;
            this.nameResolverBackoffPolicy = null;
        }
    }

    ManagedChannelImpl(AbstractManagedChannelImplBuilder<?> builder, ClientTransportFactory clientTransportFactory, BackoffPolicy.Provider backoffPolicyProvider, ObjectPool<? extends Executor> oobExecutorPool, Supplier<Stopwatch> stopwatchSupplier, List<ClientInterceptor> interceptors, final TimeProvider timeProvider) {
        this.target = Preconditions.checkNotNull(builder.target, "target");
        this.nameResolverFactory = builder.getNameResolverFactory();
        this.nameResolverParams = Preconditions.checkNotNull(builder.getNameResolverParams(), "nameResolverParams");
        this.nameResolver = ManagedChannelImpl.getNameResolver(this.target, this.nameResolverFactory, this.nameResolverParams);
        this.loadBalancerFactory = builder.loadBalancerFactory == null ? new AutoConfiguredLoadBalancerFactory() : builder.loadBalancerFactory;
        this.executorPool = Preconditions.checkNotNull(builder.executorPool, "executorPool");
        this.oobExecutorPool = Preconditions.checkNotNull(oobExecutorPool, "oobExecutorPool");
        this.executor = Preconditions.checkNotNull(this.executorPool.getObject(), "executor");
        this.delayedTransport = new DelayedClientTransport(this.executor, this.channelExecutor);
        this.delayedTransport.start(this.delayedTransportListener);
        this.backoffPolicyProvider = backoffPolicyProvider;
        this.transportFactory = new CallCredentialsApplyingTransportFactory(clientTransportFactory, this.executor);
        this.retryEnabled = builder.retryEnabled && !builder.temporarilyDisableRetry;
        this.serviceConfigInterceptor = new ServiceConfigInterceptor(this.retryEnabled, builder.maxRetryAttempts);
        Channel channel = new RealChannel();
        channel = ClientInterceptors.intercept(channel, this.serviceConfigInterceptor);
        if (builder.binlog != null) {
            channel = builder.binlog.wrapChannel(channel);
        }
        this.interceptorChannel = ClientInterceptors.intercept(channel, interceptors);
        this.stopwatchSupplier = Preconditions.checkNotNull(stopwatchSupplier, "stopwatchSupplier");
        if (builder.idleTimeoutMillis == -1L) {
            this.idleTimeoutMillis = builder.idleTimeoutMillis;
        } else {
            Preconditions.checkArgument(builder.idleTimeoutMillis >= AbstractManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS, "invalid idleTimeoutMillis %s", builder.idleTimeoutMillis);
            this.idleTimeoutMillis = builder.idleTimeoutMillis;
        }
        final class AutoDrainChannelExecutor
        implements Executor {
            AutoDrainChannelExecutor() {
            }

            @Override
            public void execute(Runnable command) {
                ManagedChannelImpl.this.channelExecutor.executeLater(command);
                ManagedChannelImpl.this.channelExecutor.drain();
            }
        }
        this.idleTimer = new Rescheduler(new IdleModeTimer(), new AutoDrainChannelExecutor(), this.transportFactory.getScheduledExecutorService(), stopwatchSupplier.get());
        this.fullStreamDecompression = builder.fullStreamDecompression;
        this.decompressorRegistry = Preconditions.checkNotNull(builder.decompressorRegistry, "decompressorRegistry");
        this.compressorRegistry = Preconditions.checkNotNull(builder.compressorRegistry, "compressorRegistry");
        this.userAgent = builder.userAgent;
        this.channelBufferLimit = builder.retryBufferSize;
        this.perRpcBufferLimit = builder.perRpcBufferLimit;
        this.timeProvider = Preconditions.checkNotNull(timeProvider, "timeProvider");
        this.callTracerFactory = new CallTracer.Factory(){

            @Override
            public CallTracer create() {
                return new CallTracer(timeProvider);
            }
        };
        this.channelCallTracer = this.callTracerFactory.create();
        this.channelz = Preconditions.checkNotNull(builder.channelz);
        this.channelz.addRootChannel(this);
        this.maxTraceEvents = builder.maxTraceEvents;
        if (this.maxTraceEvents > 0) {
            long currentTimeNanos = timeProvider.currentTimeNanos();
            this.channelTracer = new ChannelTracer(builder.maxTraceEvents, currentTimeNanos, "Channel");
        } else {
            this.channelTracer = null;
        }
        logger.log(Level.FINE, "[{0}] Created with target {1}", new Object[]{this.getLogId(), this.target});
    }

    @VisibleForTesting
    static NameResolver getNameResolver(String target, NameResolver.Factory nameResolverFactory, Attributes nameResolverParams) {
        NameResolver resolver;
        URI targetUri = null;
        StringBuilder uriSyntaxErrors = new StringBuilder();
        try {
            targetUri = new URI(target);
        }
        catch (URISyntaxException e) {
            uriSyntaxErrors.append(e.getMessage());
        }
        if (targetUri != null && (resolver = nameResolverFactory.newNameResolver(targetUri, nameResolverParams)) != null) {
            return resolver;
        }
        if (!URI_PATTERN.matcher(target).matches()) {
            try {
                targetUri = new URI(nameResolverFactory.getDefaultScheme(), "", "/" + target, null);
            }
            catch (URISyntaxException e) {
                throw new IllegalArgumentException(e);
            }
            resolver = nameResolverFactory.newNameResolver(targetUri, nameResolverParams);
            if (resolver != null) {
                return resolver;
            }
        }
        throw new IllegalArgumentException(String.format("cannot find a NameResolver for %s%s", target, uriSyntaxErrors.length() > 0 ? " (" + uriSyntaxErrors + ")" : ""));
    }

    @Override
    public ManagedChannelImpl shutdown() {
        logger.log(Level.FINE, "[{0}] shutdown() called", this.getLogId());
        if (!this.shutdown.compareAndSet(false, true)) {
            return this;
        }
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                ManagedChannelImpl.this.channelStateManager.gotoState(ConnectivityState.SHUTDOWN);
            }
        });
        this.uncommittedRetriableStreamsRegistry.onShutdown(SHUTDOWN_STATUS);
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                ManagedChannelImpl.this.cancelIdleTimer(true);
            }
        }).drain();
        logger.log(Level.FINE, "[{0}] Shutting down", this.getLogId());
        return this;
    }

    @Override
    public ManagedChannelImpl shutdownNow() {
        logger.log(Level.FINE, "[{0}] shutdownNow() called", this.getLogId());
        this.shutdown();
        this.uncommittedRetriableStreamsRegistry.onShutdownNow(SHUTDOWN_NOW_STATUS);
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                if (ManagedChannelImpl.this.shutdownNowed) {
                    return;
                }
                ManagedChannelImpl.this.shutdownNowed = true;
                ManagedChannelImpl.this.maybeShutdownNowSubchannels();
            }
        }).drain();
        return this;
    }

    @VisibleForTesting
    void panic(final Throwable t) {
        if (this.panicMode) {
            return;
        }
        this.panicMode = true;
        this.cancelIdleTimer(true);
        this.shutdownNameResolverAndLoadBalancer(false);
        LoadBalancer.SubchannelPicker newPicker = new LoadBalancer.SubchannelPicker(){
            final LoadBalancer.PickResult panicPickResult;
            {
                this.panicPickResult = LoadBalancer.PickResult.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(t));
            }

            @Override
            public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs args) {
                return this.panicPickResult;
            }
        };
        this.updateSubchannelPicker(newPicker);
        this.channelStateManager.gotoState(ConnectivityState.TRANSIENT_FAILURE);
    }

    private void updateSubchannelPicker(LoadBalancer.SubchannelPicker newPicker) {
        this.subchannelPicker = newPicker;
        this.delayedTransport.reprocess(newPicker);
    }

    @Override
    public boolean isShutdown() {
        return this.shutdown.get();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.terminatedLatch.await(timeout, unit);
    }

    @Override
    public boolean isTerminated() {
        return this.terminated;
    }

    public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
        return this.interceptorChannel.newCall(method, callOptions);
    }

    @Override
    public String authority() {
        return this.interceptorChannel.authority();
    }

    private Executor getCallExecutor(CallOptions callOptions) {
        Executor executor = callOptions.getExecutor();
        if (executor == null) {
            executor = this.executor;
        }
        return executor;
    }

    private void maybeTerminateChannel() {
        if (this.terminated) {
            return;
        }
        if (this.shutdown.get() && this.subchannels.isEmpty() && this.oobChannels.isEmpty()) {
            logger.log(Level.FINE, "[{0}] Terminated", this.getLogId());
            this.channelz.removeRootChannel(this);
            this.terminated = true;
            this.terminatedLatch.countDown();
            this.executorPool.returnObject(this.executor);
            this.transportFactory.close();
        }
    }

    @Override
    public ConnectivityState getState(boolean requestConnection) {
        ConnectivityState savedChannelState = this.channelStateManager.getState();
        if (requestConnection && savedChannelState == ConnectivityState.IDLE) {
            this.channelExecutor.executeLater(new Runnable(){

                @Override
                public void run() {
                    ManagedChannelImpl.this.exitIdleMode();
                    if (ManagedChannelImpl.this.subchannelPicker != null) {
                        ManagedChannelImpl.this.subchannelPicker.requestConnection();
                    }
                }
            }).drain();
        }
        return savedChannelState;
    }

    @Override
    public void notifyWhenStateChanged(final ConnectivityState source, final Runnable callback) {
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                ManagedChannelImpl.this.channelStateManager.notifyWhenStateChanged(callback, ManagedChannelImpl.this.executor, source);
            }
        }).drain();
    }

    @Override
    public void resetConnectBackoff() {
        this.channelExecutor.executeLater(new Runnable(){

            @Override
            public void run() {
                if (ManagedChannelImpl.this.shutdown.get()) {
                    return;
                }
                if (ManagedChannelImpl.this.nameResolverRefreshFuture != null) {
                    Preconditions.checkState(ManagedChannelImpl.this.nameResolverStarted, "name resolver must be started");
                    ManagedChannelImpl.this.cancelNameResolverBackoff();
                    ManagedChannelImpl.this.nameResolver.refresh();
                }
                for (InternalSubchannel subchannel : ManagedChannelImpl.this.subchannels) {
                    subchannel.resetConnectBackoff();
                }
                for (OobChannel oobChannel : ManagedChannelImpl.this.oobChannels) {
                    oobChannel.resetConnectBackoff();
                }
            }
        }).drain();
    }

    @Override
    public void enterIdle() {
        class PrepareToLoseNetworkRunnable
        implements Runnable {
            PrepareToLoseNetworkRunnable() {
            }

            @Override
            public void run() {
                if (ManagedChannelImpl.this.shutdown.get() || ManagedChannelImpl.this.lbHelper == null) {
                    return;
                }
                ManagedChannelImpl.this.cancelIdleTimer(false);
                ManagedChannelImpl.this.enterIdleMode();
            }
        }
        this.channelExecutor.executeLater(new PrepareToLoseNetworkRunnable()).drain();
    }

    @Nullable
    private static RetriableStream.Throttle getThrottle(Attributes config) {
        return ServiceConfigUtil.getThrottlePolicy(config.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("target", this.target).toString();
    }

    private final class SubchannelImpl
    extends AbstractSubchannel {
        InternalSubchannel subchannel;
        final Object shutdownLock = new Object();
        final Attributes attrs;
        @GuardedBy(value="shutdownLock")
        boolean shutdownRequested;
        @GuardedBy(value="shutdownLock")
        ScheduledFuture<?> delayedShutdownTask;

        SubchannelImpl(Attributes attrs) {
            this.attrs = Preconditions.checkNotNull(attrs, "attrs");
        }

        @Override
        ClientTransport obtainActiveTransport() {
            return this.subchannel.obtainActiveTransport();
        }

        @Override
        Instrumented<Channelz.ChannelStats> getInternalSubchannel() {
            return this.subchannel;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void shutdown() {
            Object object = this.shutdownLock;
            synchronized (object) {
                block6: {
                    if (this.shutdownRequested) {
                        if (ManagedChannelImpl.this.terminating && this.delayedShutdownTask != null) {
                            this.delayedShutdownTask.cancel(false);
                            this.delayedShutdownTask = null;
                            break block6;
                        } else {
                            return;
                        }
                    }
                    this.shutdownRequested = true;
                }
                if (!ManagedChannelImpl.this.terminating) {
                    this.delayedShutdownTask = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService().schedule(new LogExceptionRunnable(new Runnable(){

                        @Override
                        public void run() {
                            SubchannelImpl.this.subchannel.shutdown(SUBCHANNEL_SHUTDOWN_STATUS);
                        }
                    }), 5L, TimeUnit.SECONDS);
                    return;
                }
            }
            this.subchannel.shutdown(SHUTDOWN_STATUS);
        }

        @Override
        public void requestConnection() {
            this.subchannel.obtainActiveTransport();
        }

        @Override
        public EquivalentAddressGroup getAddresses() {
            return this.subchannel.getAddressGroup();
        }

        @Override
        public Attributes getAttributes() {
            return this.attrs;
        }

        public String toString() {
            return this.subchannel.getLogId().toString();
        }
    }

    private class NameResolverListenerImpl
    implements NameResolver.Listener {
        final LbHelperImpl helper;

        NameResolverListenerImpl(LbHelperImpl helperImpl) {
            this.helper = helperImpl;
        }

        @Override
        public void onAddresses(final List<EquivalentAddressGroup> servers, final Attributes config) {
            if (servers.isEmpty()) {
                this.onError(Status.UNAVAILABLE.withDescription("NameResolver returned an empty list"));
                return;
            }
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "[{0}] resolved address: {1}, config={2}", new Object[]{ManagedChannelImpl.this.getLogId(), servers, config});
            }
            if (ManagedChannelImpl.this.channelTracer != null) {
                ManagedChannelImpl.this.channelTracer.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription("Address resolved: " + servers).setSeverity(Channelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).build());
            }
            final class NamesResolved
            implements Runnable {
                NamesResolved() {
                }

                @Override
                public void run() {
                    if (NameResolverListenerImpl.this.helper != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    ManagedChannelImpl.this.nameResolverBackoffPolicy = null;
                    Map<String, Object> serviceConfig = config.get(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG);
                    if (serviceConfig != null) {
                        try {
                            ManagedChannelImpl.this.serviceConfigInterceptor.handleUpdate(serviceConfig);
                            if (ManagedChannelImpl.this.retryEnabled) {
                                ManagedChannelImpl.this.throttle = ManagedChannelImpl.getThrottle(config);
                            }
                        }
                        catch (RuntimeException re) {
                            logger.log(Level.WARNING, "[" + ManagedChannelImpl.this.getLogId() + "] Unexpected exception from parsing service config", re);
                        }
                    }
                    NameResolverListenerImpl.this.helper.lb.handleResolvedAddressGroups(servers, config);
                }
            }
            this.helper.runSerialized(new NamesResolved());
        }

        @Override
        public void onError(final Status error2) {
            Preconditions.checkArgument(!error2.isOk(), "the error status must not be OK");
            logger.log(Level.WARNING, "[{0}] Failed to resolve name. status={1}", new Object[]{ManagedChannelImpl.this.getLogId(), error2});
            if (ManagedChannelImpl.this.channelTracer != null) {
                ManagedChannelImpl.this.channelTracer.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription("Failed to resolve name").setSeverity(Channelz.ChannelTrace.Event.Severity.CT_WARNING).setTimestampNanos(ManagedChannelImpl.this.timeProvider.currentTimeNanos()).build());
            }
            ManagedChannelImpl.this.channelExecutor.executeLater(new Runnable(){

                @Override
                public void run() {
                    if (NameResolverListenerImpl.this.helper != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    NameResolverListenerImpl.this.helper.lb.handleNameResolutionError(error2);
                    if (ManagedChannelImpl.this.nameResolverRefreshFuture != null) {
                        return;
                    }
                    if (ManagedChannelImpl.this.nameResolverBackoffPolicy == null) {
                        ManagedChannelImpl.this.nameResolverBackoffPolicy = ManagedChannelImpl.this.backoffPolicyProvider.get();
                    }
                    long delayNanos = ManagedChannelImpl.this.nameResolverBackoffPolicy.nextBackoffNanos();
                    if (logger.isLoggable(Level.FINE)) {
                        logger.log(Level.FINE, "[{0}] Scheduling DNS resolution backoff for {1} ns", new Object[]{ManagedChannelImpl.this.logId, delayNanos});
                    }
                    ManagedChannelImpl.this.nameResolverRefresh = new NameResolverRefresh();
                    ManagedChannelImpl.this.nameResolverRefreshFuture = ManagedChannelImpl.this.transportFactory.getScheduledExecutorService().schedule(ManagedChannelImpl.this.nameResolverRefresh, delayNanos, TimeUnit.NANOSECONDS);
                }
            }).drain();
        }
    }

    private class LbHelperImpl
    extends LoadBalancer.Helper {
        LoadBalancer lb;
        final NameResolver nr;

        LbHelperImpl(NameResolver nr) {
            this.nr = Preconditions.checkNotNull(nr, "NameResolver");
        }

        private void handleInternalSubchannelState(ConnectivityStateInfo newState) {
            if (newState.getState() == ConnectivityState.TRANSIENT_FAILURE || newState.getState() == ConnectivityState.IDLE) {
                this.nr.refresh();
            }
        }

        @Override
        public AbstractSubchannel createSubchannel(EquivalentAddressGroup addressGroup, Attributes attrs) {
            Preconditions.checkNotNull(addressGroup, "addressGroup");
            Preconditions.checkNotNull(attrs, "attrs");
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            final SubchannelImpl subchannel = new SubchannelImpl(attrs);
            ChannelTracer subchannelTracer = null;
            long subchannelCreationTime = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            if (ManagedChannelImpl.this.maxTraceEvents > 0) {
                subchannelTracer = new ChannelTracer(ManagedChannelImpl.this.maxTraceEvents, subchannelCreationTime, "Subchannel");
            }
            final InternalSubchannel internalSubchannel = new InternalSubchannel(addressGroup, ManagedChannelImpl.this.authority(), ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.channelExecutor, new InternalSubchannel.Callback(){

                @Override
                void onTerminated(InternalSubchannel is) {
                    ManagedChannelImpl.this.subchannels.remove(is);
                    ManagedChannelImpl.this.channelz.removeSubchannel(is);
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                @Override
                void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
                    LbHelperImpl.this.handleInternalSubchannelState(newState);
                    if (LbHelperImpl.this == ManagedChannelImpl.this.lbHelper) {
                        LbHelperImpl.this.lb.handleSubchannelState(subchannel, newState);
                    }
                }

                @Override
                void onInUse(InternalSubchannel is) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(is, true);
                }

                @Override
                void onNotInUse(InternalSubchannel is) {
                    ManagedChannelImpl.this.inUseStateAggregator.updateObjectInUse(is, false);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), subchannelTracer);
            if (ManagedChannelImpl.this.channelTracer != null) {
                ManagedChannelImpl.this.channelTracer.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription("Child channel created").setSeverity(Channelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(subchannelCreationTime).setSubchannelRef(internalSubchannel).build());
            }
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            subchannel.subchannel = internalSubchannel;
            logger.log(Level.FINE, "[{0}] {1} created for {2}", new Object[]{ManagedChannelImpl.this.getLogId(), internalSubchannel.getLogId(), addressGroup});
            this.runSerialized(new Runnable(){

                @Override
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        internalSubchannel.shutdown(SHUTDOWN_STATUS);
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.subchannels.add(internalSubchannel);
                    }
                }
            });
            return subchannel;
        }

        @Override
        public void updateBalancingState(final ConnectivityState newState, final LoadBalancer.SubchannelPicker newPicker) {
            Preconditions.checkNotNull(newState, "newState");
            Preconditions.checkNotNull(newPicker, "newPicker");
            this.runSerialized(new Runnable(){

                @Override
                public void run() {
                    if (LbHelperImpl.this != ManagedChannelImpl.this.lbHelper) {
                        return;
                    }
                    ManagedChannelImpl.this.updateSubchannelPicker(newPicker);
                    if (newState != ConnectivityState.SHUTDOWN) {
                        ManagedChannelImpl.this.channelStateManager.gotoState(newState);
                    }
                }
            });
        }

        @Override
        public void updateSubchannelAddresses(LoadBalancer.Subchannel subchannel, EquivalentAddressGroup addrs) {
            Preconditions.checkArgument(subchannel instanceof SubchannelImpl, "subchannel must have been returned from createSubchannel");
            ((SubchannelImpl)subchannel).subchannel.updateAddresses(addrs);
        }

        @Override
        public ManagedChannel createOobChannel(EquivalentAddressGroup addressGroup, String authority) {
            Preconditions.checkState(!ManagedChannelImpl.this.terminated, "Channel is terminated");
            long oobChannelCreationTime = ManagedChannelImpl.this.timeProvider.currentTimeNanos();
            ChannelTracer oobChannelTracer = null;
            ChannelTracer subchannelTracer = null;
            if (ManagedChannelImpl.this.channelTracer != null) {
                oobChannelTracer = new ChannelTracer(ManagedChannelImpl.this.maxTraceEvents, oobChannelCreationTime, "OobChannel");
            }
            final OobChannel oobChannel = new OobChannel(authority, ManagedChannelImpl.this.oobExecutorPool, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelExecutor, ManagedChannelImpl.this.callTracerFactory.create(), oobChannelTracer, ManagedChannelImpl.this.channelz);
            if (ManagedChannelImpl.this.channelTracer != null) {
                ManagedChannelImpl.this.channelTracer.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription("Child channel created").setSeverity(Channelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(oobChannelCreationTime).setChannelRef(oobChannel).build());
                subchannelTracer = new ChannelTracer(ManagedChannelImpl.this.maxTraceEvents, oobChannelCreationTime, "Subchannel");
            }
            InternalSubchannel internalSubchannel = new InternalSubchannel(addressGroup, authority, ManagedChannelImpl.this.userAgent, ManagedChannelImpl.this.backoffPolicyProvider, ManagedChannelImpl.this.transportFactory, ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.stopwatchSupplier, ManagedChannelImpl.this.channelExecutor, new InternalSubchannel.Callback(){

                @Override
                void onTerminated(InternalSubchannel is) {
                    ManagedChannelImpl.this.oobChannels.remove(oobChannel);
                    ManagedChannelImpl.this.channelz.removeSubchannel(is);
                    oobChannel.handleSubchannelTerminated();
                    ManagedChannelImpl.this.maybeTerminateChannel();
                }

                @Override
                void onStateChange(InternalSubchannel is, ConnectivityStateInfo newState) {
                    LbHelperImpl.this.handleInternalSubchannelState(newState);
                    oobChannel.handleSubchannelStateChange(newState);
                }
            }, ManagedChannelImpl.this.channelz, ManagedChannelImpl.this.callTracerFactory.create(), subchannelTracer);
            if (oobChannelTracer != null) {
                oobChannelTracer.reportEvent(new Channelz.ChannelTrace.Event.Builder().setDescription("Child channel created").setSeverity(Channelz.ChannelTrace.Event.Severity.CT_INFO).setTimestampNanos(oobChannelCreationTime).setSubchannelRef(internalSubchannel).build());
            }
            ManagedChannelImpl.this.channelz.addSubchannel(oobChannel);
            ManagedChannelImpl.this.channelz.addSubchannel(internalSubchannel);
            oobChannel.setSubchannel(internalSubchannel);
            this.runSerialized(new Runnable(){

                @Override
                public void run() {
                    if (ManagedChannelImpl.this.terminating) {
                        oobChannel.shutdown();
                    }
                    if (!ManagedChannelImpl.this.terminated) {
                        ManagedChannelImpl.this.oobChannels.add(oobChannel);
                    }
                }
            });
            return oobChannel;
        }

        @Override
        public void updateOobChannelAddresses(ManagedChannel channel, EquivalentAddressGroup eag) {
            Preconditions.checkArgument(channel instanceof OobChannel, "channel must have been returned from createOobChannel");
            ((OobChannel)channel).updateAddresses(eag);
        }

        @Override
        public String getAuthority() {
            return ManagedChannelImpl.this.authority();
        }

        @Override
        public NameResolver.Factory getNameResolverFactory() {
            return ManagedChannelImpl.this.nameResolverFactory;
        }

        @Override
        public void runSerialized(Runnable task) {
            ManagedChannelImpl.this.channelExecutor.executeLater(task).drain();
        }
    }

    private final class UncommittedRetriableStreamsRegistry {
        final Object lock = new Object();
        @GuardedBy(value="lock")
        Collection<ClientStream> uncommittedRetriableStreams = new HashSet<ClientStream>();
        @GuardedBy(value="lock")
        Status shutdownStatus;

        private UncommittedRetriableStreamsRegistry() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void onShutdown(Status reason) {
            boolean shouldShutdownDelayedTransport = false;
            Object object = this.lock;
            synchronized (object) {
                if (this.shutdownStatus != null) {
                    return;
                }
                this.shutdownStatus = reason;
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    shouldShutdownDelayedTransport = true;
                }
            }
            if (shouldShutdownDelayedTransport) {
                ManagedChannelImpl.this.delayedTransport.shutdown(reason);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void onShutdownNow(Status reason) {
            ArrayList<ClientStream> streams;
            this.onShutdown(reason);
            Iterator iterator2 = this.lock;
            synchronized (iterator2) {
                streams = new ArrayList<ClientStream>(this.uncommittedRetriableStreams);
            }
            for (ClientStream stream : streams) {
                stream.cancel(reason);
            }
            ManagedChannelImpl.this.delayedTransport.shutdownNow(reason);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Nullable
        Status add(RetriableStream<?> retriableStream) {
            Object object = this.lock;
            synchronized (object) {
                if (this.shutdownStatus != null) {
                    return this.shutdownStatus;
                }
                this.uncommittedRetriableStreams.add(retriableStream);
                return null;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void remove(RetriableStream<?> retriableStream) {
            Status shutdownStatusCopy = null;
            Object object = this.lock;
            synchronized (object) {
                this.uncommittedRetriableStreams.remove(retriableStream);
                if (this.uncommittedRetriableStreams.isEmpty()) {
                    shutdownStatusCopy = this.shutdownStatus;
                    this.uncommittedRetriableStreams = new HashSet<ClientStream>();
                }
            }
            if (shutdownStatusCopy != null) {
                ManagedChannelImpl.this.delayedTransport.shutdown(shutdownStatusCopy);
            }
        }
    }

    private class RealChannel
    extends Channel {
        private RealChannel() {
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> newCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions) {
            return new ClientCallImpl<ReqT, RespT>(method, ManagedChannelImpl.this.getCallExecutor(callOptions), callOptions, ManagedChannelImpl.this.transportProvider, ManagedChannelImpl.this.terminated ? null : ManagedChannelImpl.this.transportFactory.getScheduledExecutorService(), ManagedChannelImpl.this.channelCallTracer, ManagedChannelImpl.this.retryEnabled).setFullStreamDecompression(ManagedChannelImpl.this.fullStreamDecompression).setDecompressorRegistry(ManagedChannelImpl.this.decompressorRegistry).setCompressorRegistry(ManagedChannelImpl.this.compressorRegistry);
        }

        @Override
        public String authority() {
            String authority = ManagedChannelImpl.this.nameResolver.getServiceAuthority();
            return Preconditions.checkNotNull(authority, "authority");
        }
    }

    @VisibleForTesting
    class NameResolverRefresh
    implements Runnable {
        boolean cancelled;

        NameResolverRefresh() {
        }

        @Override
        public void run() {
            if (this.cancelled) {
                return;
            }
            ManagedChannelImpl.this.nameResolverRefreshFuture = null;
            ManagedChannelImpl.this.nameResolverRefresh = null;
            if (ManagedChannelImpl.this.nameResolver != null) {
                ManagedChannelImpl.this.nameResolver.refresh();
            }
        }
    }

    private class IdleModeTimer
    implements Runnable {
        private IdleModeTimer() {
        }

        @Override
        public void run() {
            ManagedChannelImpl.this.enterIdleMode();
        }
    }
}

